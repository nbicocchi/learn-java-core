# Lab 1 — JavaFX: Theory

## From a console program to a desktop application

A console program often asks a question, reads an answer, and continues. A graphical application displays a window and waits for events: a selection, a click, a key press, or a window closing.

The address book is our running example. In this first iteration, it displays contacts stored in memory. Later iterations will add editing, persistence, and a remote backend. We do not need those technologies to build the first usable screen.

After this lab, you should be able to:

* identify the window, scene, layouts, and controls in a JavaFX application;
* design a resizable master–detail interface using Scene Builder;
* connect FXML nodes to a controller;
* populate a table and react to selection changes;
* run the application from Maven without relying on IDE-specific settings.

## What JavaFX provides

[JavaFX](https://openjfx.io/) is a desktop UI toolkit. It provides controls, layout containers, graphics, CSS styling, and an event system.

**JavaFX is not bundled with JDK 25.** Maven downloads the OpenJFX dependencies, including platform-specific native libraries. Installing a JDK alone does not install JavaFX. Scene Builder is a separate visual editor; users do not need it to run the application.

For this lab we use JDK 25 and JavaFX 25.0.1, matching the course's JavaFX deployment example. No preview features are needed.

## Application lifecycle

Our `App` class extends `javafx.application.Application`:

```java
public static void main(String[] args) {
    launch(args);
}
```

`launch` starts the toolkit. JavaFX constructs the application and invokes `start(Stage stage)`, where we load the interface and show the window. We do not call `start` ourselves.

| Element | Role in this application |
|---|---|
| `Stage` | The top-level window, including title and window controls. |
| `Scene` | Holds the visual content placed inside the window. |
| Root node | The `BorderPane` at the top of our scene graph. |
| Layout nodes | Arrange their children: `VBox`, `GridPane`, and others. |
| Controls | Present information and accept interaction: labels, tables, buttons. |

A `Scene` is **not** a node in the scene graph. It owns a root node. A `Stage` holds the scene.

```text
Stage
 └─ Scene
     └─ BorderPane (root node)
         ├─ top: VBox (heading)
         ├─ center: SplitPane
         │   ├─ VBox (table and reload button)
         │   └─ VBox (details and clear-selection button)
         └─ bottom: Label (status)
```

## Layout before coordinates

Avoid positioning every control with fixed screen coordinates. A layout container computes positions and sizes according to its children's needs and available space.

| Container | Use here |
|---|---|
| `BorderPane` | Header at the top, main content in the center, status at the bottom. |
| `SplitPane` | Two resizable panels separated by a draggable divider. |
| `VBox` | Controls arranged vertically with spacing and padding. |
| `GridPane` | Field names and values arranged in rows and columns. |
| `Region` | A flexible spacer before the clear-selection button. |

`prefWidth` is a preferred width, not a guarantee. Minimum sizes prevent unusable shrinking. `VBox.vgrow="ALWAYS"` lets the table or spacer absorb extra height. Column constraints let the detail values use the space left after the field names.

**Predict:** if the table's vertical grow constraint is removed, what happens when the window becomes taller? Verify using Scene Builder's preview.

## Model, view, and controller

We separate three responsibilities without introducing a large framework:

* **Model:** `Person` represents contact data. It has no JavaFX imports.
* **View:** `person-overview-view.fxml` describes the visual tree.
* **Controller:** `PersonOverviewController` connects controls and responds to interaction.

`App` starts the application. `SampleContacts` creates fresh demo data. The controller owns the in-memory observable list for this checkpoint; it does not contain SQL or HTTP calls.

Keeping `Person` independent of JavaFX makes it easier to reuse data concepts when a backend is introduced. This does not mean the eventual frontend and backend must share a compiled model module.

### A deliberately small model

The first model has read-only fields for first name, last name, street, postal code, city, and birthday. It is a plain class with a constructor and getters. No setters are needed because editing is outside this iteration.

* Postal codes are **strings**, not quantities: `"00118"` must retain its leading zeros.
* Birthdays use `LocalDate`, a date without a time of day or time zone. Here `LocalDate.of(1995, 7, 22)` constructs a fixed date, and `toString()` displays it as `1995-07-22`.
* Fields must be non-null. `Objects.requireNonNull` enforces this minimal contract. Form validation belongs to a later iteration.

## FXML and resource loading

FXML is an XML description of objects, their properties, and their relationships. It is not a screenshot or generated Java source. `FXMLLoader` reads it at runtime and constructs the object graph.

```xml
<Label fx:id="firstNameLabel"/>
<Button text="Clear selection" onAction="#handleClearSelection"/>
```

These are fragments inside an FXML document with the JavaFX imports and `fx` namespace declared.

* `fx:controller` on the root identifies the controller's fully qualified class name.
* `fx:id` connects a node to a controller field of the same name.
* `onAction="#..."` connects a control's action to a controller method.
* `@FXML` permits the loader to access annotated private fields and methods, subject to module access rules.

The loader constructs the controller, injects its fields, and then calls `initialize()`. **Injected fields are not ready in the controller's constructor.** Configure the table and listeners in `initialize()` instead.

In `App`, this resource lookup is relative to the package of `App`:

```java
App.class.getResource("person-overview-view.fxml")
```

Maven copies `src/main/resources/` into the runtime classpath/module. Consequently, our file lives under `src/main/resources/com/nbicocchi/addressbook/`. Resources do not intrinsically have to mirror Java packages; that is the convention chosen for this relative lookup. Do not use absolute filesystem paths tied to your computer.

## Modules: only what we need

The project includes a small `module-info.java`:

```java
module com.nbicocchi.addressbook {
    requires javafx.controls;
    requires javafx.fxml;

    exports com.nbicocchi.addressbook;
    opens com.nbicocchi.addressbook.controller to javafx.fxml;
}
```

`requires` declares dependencies, `exports` makes the application package accessible, and `opens ... to javafx.fxml` permits reflective injection in the controller package. There is no need to open every package or require JDBC before using a database.

Treat this descriptor as supplied infrastructure for now. The lab is about the UI, not a complete introduction to the Java module system.

## Tables and observable data

A `TableView<Person>` displays rows backed by `Person` objects. Its columns need instructions for extracting the value displayed in each cell:

```java
firstNameColumn.setCellValueFactory(cell ->
        new ReadOnlyStringWrapper(cell.getValue().getFirstName()));
```

Read the lambda as “for this row, return the person's first name wrapped as an observable value.” Its general syntax is covered in E7; here it is a small callback supplied to JavaFX. Generic types such as `TableView<Person>` specify the kind of data used by a control; we use them before studying how to define generic classes in E6.

The original address-book example uses `PropertyValueFactory`, which looks up getters by name. Here an explicit callback avoids string-based getter lookup and additional reflective access to the model package.

`ObservableList<Person>` notifies the table about changes to the **list**. `people.setAll(...)` replaces its contents while retaining the same observable list object. A plain object's internal field changes are not automatically made observable by putting it in this list. Our read-only model avoids that distinction becoming an editing problem in lab1.

## Selection and actions

A selection listener receives the previous and new selected object:

```java
personTable.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldPerson, newPerson) -> showPersonDetails(newPerson));
```

The new selection can be `null`. This is an ordinary UI state, not necessarily an error. Our controller empties all detail values, displays a selection hint, and disables the clear-selection button.

Listen to the **selected object**, not only its row index: sorting changes row positions. Selection also works through the keyboard, not just mouse clicks.

## The JavaFX Application Thread

JavaFX delivers UI events and updates the interface on the JavaFX Application Thread. Our sample-data operations are tiny and can run there.

Do not place database queries, HTTP requests, or long calculations in these handlers. They would block interaction and painting. Later labs will introduce background work and return UI changes to the JavaFX thread. There is no reason to add threads to load six local sample contacts.

## CSS and Scene Builder

Scene Builder edits FXML visually. Its preview is useful for layout and resizing, but does not run our Java controller or load our sample data.

JavaFX CSS controls appearance separately from layout and behavior. The FXML root loads `@styles/address-book.css`, relative to the FXML file. Classes such as `section-title` are reusable style hooks; `fx:id` identifies nodes used by the controller. JavaFX CSS resembles web CSS but has its own properties, commonly prefixed with `-fx-`.

## Check your understanding

1. Why is `initialize()` a better place than the constructor to configure injected controls?
2. Why is `"00118"` stored as a string?
3. What should the detail panel show when the selection becomes null?
4. Why can a sorted row have a different index while still representing the same contact?
5. Which parts would need to change to load contacts from a database? Which could stay the same?

Continue with the [tutorial](tutorial.md).

## References and source material

* [Original course theory](https://github.com/nbicocchi/learn-java-javafx/blob/main/slides/E1.1%20-%20JavaFX.md) and [address-book tutorial](https://github.com/nbicocchi/learn-java-javafx/blob/main/slides/E1.2%20-%20JavaFX%20%28Tutorial%29.md), adapted for this checkpoint.
* [OpenJFX getting started](https://openjfx.io/openjfx-docs/)
* [JavaFX 25 API](https://openjfx.io/javadoc/25/)
* [Scene Builder](https://gluonhq.com/products/scene-builder/)
