# Lab 1 — JavaFX: Build the Address Book

Read [the theory](theory.md) first. This tutorial follows the layout → model → table → selection progression of the instructor's [original JavaFX tutorial](https://github.com/nbicocchi/learn-java-javafx/blob/main/slides/E1.2%20-%20JavaFX%20%28Tutorial%29.md), stopping at a smaller first checkpoint.

## 1. Prepare your workspace

Install JDK 25, Maven, and Scene Builder. Check:

```bash
java -version
javac -version
mvn -version
```

All Java commands, including Maven's reported Java runtime, must use JDK 25. The application requires a graphical desktop; a terminal-only SSH session is not enough.

Keep the supplied [`address-book/`](address-book/) project as the completed reference. Create a **separate working directory**, for example `address-book-work`, outside the reference project. Do not nest another Maven project under its `src/` directory.

Copy only the reference [`pom.xml`](address-book/pom.xml) initially. Open the working `pom.xml` in IntelliJ as a Maven project, select JDK 25 as the project SDK, and reload Maven. No JavaFX SDK download or manually configured module path is needed.

If using the IDE's JavaFX project wizard instead, replace its generated POM with the supplied one and remove generated Hello classes, controllers, views, and module descriptors before proceeding.

**Command convention:** all remaining commands run from your working project's directory, alongside its `pom.xml`.

## 2. Create the source and resource structure

Create the following directories; names and case matter on Linux:

```text
src/main/java/
 └─ com/nbicocchi/addressbook/
     ├─ App.java
     ├─ controller/PersonOverviewController.java
     └─ model/
         ├─ Person.java
         └─ SampleContacts.java
src/main/resources/
 └─ com/nbicocchi/addressbook/
     ├─ person-overview-view.fxml
     └─ styles/address-book.css
```

We will also add `src/main/java/module-info.java`. Use `javafx` imports for controls and layouts, not Swing or AWT.

## 3. Design the static screen in Scene Builder

Create `person-overview-view.fxml` under the resource directory above. Start with this minimal document:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<?import javafx.scene.layout.BorderPane?>
<BorderPane xmlns="http://javafx.com/javafx/25" xmlns:fx="http://javafx.com/fxml/1"
            prefWidth="960" prefHeight="620"/>
```

Open it with Scene Builder. In IntelliJ this is usually **Open in Scene Builder**; if needed, configure the Scene Builder executable in the IDE's JavaFX settings. A standalone Scene Builder can also open the file directly.

Do not assign a controller class yet: first create the static layout. The completed [FXML](address-book/src/main/resources/com/nbicocchi/addressbook/person-overview-view.fxml) is the reference if a setting is hard to find. Scene Builder versions may use slightly different menu labels.

### 3.1 Header and status

1. Select the root `BorderPane`; set preferred width `960` and height `620` if not already set.
2. Drag a `VBox` into its **top** region. Set spacing `6` and padding `24` on all sides.
3. Add three labels: `ADDRESS BOOK`, `Your contacts, at a glance.`, and `Select a name on the left to explore the contact details.`
4. Drag a `Label` into the **bottom** region. For now set its text to `6 contacts · In memory only · Lab 1`. Give it padding `12` vertically and `24` horizontally.

### 3.2 Main panels

1. Drag a horizontal `SplitPane` into the **center** region.
2. Replace its default content panes with two `VBox` containers. The reference hierarchy is below; if Scene Builder inserted `AnchorPane` wrappers, remove them rather than leaving unused nesting.
3. Give each VBox padding `24`. Set left spacing `12` and minimum width `280`; right spacing `16` and minimum width `380`.
4. Position the divider so the left panel occupies approximately 43% of the width.
5. In the left VBox, add a `Contacts` label, a `TableView`, and a button labelled `Reload sample contacts`.
6. Set the table's **VBox vgrow** constraint to **ALWAYS** so it uses extra height. Set the button's maximum width to Infinity/MAX_VALUE.
7. Rename the table's two columns to `First name` and `Last name`; give each a preferred width of `150`.
8. Set the table's placeholder to a label reading `No contacts available.` if the editor exposes this property; otherwise use the reference FXML's `<placeholder>` element.

### 3.3 Detail panel

1. Add a `Contact details` label and a second label reading `Select a contact to see their details.` Set the hint's **Wrap Text** property.
2. Add a `GridPane` with two columns and six rows. Set horizontal gap `20`, vertical gap `18`.
3. Set the first column's minimum width to `95`, and the second column's horizontal grow constraint to **ALWAYS**.
4. Fill column 0 with `First name`, `Last name`, `Street`, `Postal code`, `City`, and `Birthday`.
5. Put one empty `Label` in column 1 of each row. Assign row indexes `0` through `5`, and column index `1`. These are the values the controller will update.
6. Enable wrapping on the name, street, and city value labels.
7. After the grid, add a `Region` with **VBox vgrow = ALWAYS**, then a button labelled `Clear selection`.

The hierarchy should now be:

```text
BorderPane
 ├─ top: VBox → three heading labels
 ├─ center: SplitPane
 │   ├─ VBox → heading, table, reload button
 │   └─ VBox → heading, hint, grid, flexible region, clear button
 └─ bottom: status label
```

Use **Preview → Show Preview in Window**. Resize it and drag the divider. The table should grow vertically; the clear button should remain near the bottom of its panel. Preview does not run the controller, so the table is still empty.

## 4. Add the model and sample contacts

Create [`Person.java`](address-book/src/main/java/com/nbicocchi/addressbook/model/Person.java) in package `com.nbicocchi.addressbook.model`, using the linked reference implementation.

Before copying, identify its six fields and their types. They are private and final, initialized by the constructor and exposed through getters. There are no setters because this lab does not edit contacts. `Objects.requireNonNull` rejects missing field references; it is not complete input validation.

Then create [`SampleContacts.java`](address-book/src/main/java/com/nbicocchi/addressbook/model/SampleContacts.java) in the same package. Its `create()` method builds a **fresh list** of six contacts each time. Keep the supplied order while following the tutorial so the expected results match.

**Check:** Luca Bianchi's postal code is the string `"00118"`. Converting it to an integer would lose information.

## 5. Create the controller

Create `PersonOverviewController` in package `com.nbicocchi.addressbook.controller`. Use the complete [reference controller](address-book/src/main/java/com/nbicocchi/addressbook/controller/PersonOverviewController.java) for imports and field declarations, but build its behavior in these steps:

1. Declare the `@FXML` fields shown in the reference: the table, its columns, eight labels, and the clear-selection button.
2. Add `private final ObservableList<Person> people = FXCollections.observableArrayList();`.
3. Add `showPersonDetails(Person person)` from the reference. For null, clear the six value labels, show the selection hint, and disable the clear button. Otherwise display the selected object's fields and enable the button.
4. Add `handleClearSelection()`, annotated `@FXML`, which clears the table's selection model.
5. Add `handleReloadSamples()`, annotated `@FXML`. Clear selection and sorting, replace the list's contents with `people.setAll(SampleContacts.create())`, reset the details, and update the status text.
6. Add `initialize()` as follows:

```java
@FXML
private void initialize() {
    firstNameColumn.setCellValueFactory(cell ->
            new ReadOnlyStringWrapper(cell.getValue().getFirstName()));
    lastNameColumn.setCellValueFactory(cell ->
            new ReadOnlyStringWrapper(cell.getValue().getLastName()));
    personTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
    personTable.setItems(people);
    personTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldPerson, newPerson) -> showPersonDetails(newPerson));
    handleReloadSamples();
}
```

The listener follows the selected **person**, not an index cached before sorting. The sample reload retains the observable list object and replaces its contents. No database or disk access is involved.

## 6. Connect view and controller

In Scene Builder's **Controller** section, set the controller class to:

```text
com.nbicocchi.addressbook.controller.PersonOverviewController
```

In each component's **Code** section, assign these exact `fx:id` values:

| Component | `fx:id` |
|---|---|
| Table | `personTable` |
| First-name and last-name columns | `firstNameColumn`, `lastNameColumn` |
| Six value labels, in grid row order | `firstNameLabel`, `lastNameLabel`, `streetLabel`, `postalCodeLabel`, `cityLabel`, `birthdayLabel` |
| Selection hint | `selectionLabel` |
| Bottom status | `statusLabel` |
| Clear-selection button | `clearSelectionButton` |
| Reload button | `reloadSamplesButton` |

Assign IDs to the **value** labels, not the static field names. The reload button has an ID for UI testing but does not need a controller field.

Set the buttons' **On Action** values to `#handleClearSelection` and `#handleReloadSamples`. Scene Builder may display the names without `#`; the saved FXML must contain it. If its dropdown is empty, enter the method name manually and verify the controller package and compiled code.

Save the file. Compare its IDs and action names with the reference FXML.

## 7. Start the application

Create [`App.java`](address-book/src/main/java/com/nbicocchi/addressbook/App.java) and [`module-info.java`](address-book/src/main/java/module-info.java) using the reference implementations. Keep their package and module names aligned with the POM's `mainClass`.

`App` loads the FXML, creates a scene, sets a minimum usable window size, and shows the stage. The module descriptor opens only the controller package to the FXML loader.

Before the stylesheet step, leave the FXML root's `stylesheets` attribute unset. Run:

```bash
mvn clean compile
mvn javafx:run
```

**Checkpoint:** six rows appear, no contact is selected, details are empty, and the clear-selection button is disabled. Select Luca: the detail panel should display `00118` and `1995-07-22`.

## 8. Apply a small stylesheet

Create [`styles/address-book.css`](address-book/src/main/resources/com/nbicocchi/addressbook/styles/address-book.css) using the reference stylesheet. In Scene Builder, select the root and add this file under **Stylesheets**. The FXML root should contain:

```xml
stylesheets="@styles/address-book.css"
```

Add these style classes in Scene Builder's **Properties → Style Class** field (without the dot used in CSS selectors):

| Node | Style class |
|---|---|
| Header VBox | `header` |
| Three heading labels, respectively | `eyebrow`, `title`, `subtitle` |
| Both content VBoxes | `panel` |
| Contacts and Contact details headings | `section-title` |
| Selection hint | `muted` |
| Six static grid field names | `field-name` |
| Bottom status label | `status` |

Keep built-in style classes such as `label` or `button` if the editor displays them. Save and restart. The stylesheet provides a dark header, readable spacing, highlighted selection, and consistent buttons without an external theme library.

## 9. Verify behavior and appearance

Run through this acceptance checklist on the completed application:

1. Launch through `mvn javafx:run`, not only the IDE's Run button.
2. Confirm six contacts and an empty detail panel at startup.
3. Select Anna and Luca in turn: every detail field must change, including the postal code and birthday.
4. Use arrow keys to change selection; details must follow keyboard selection too.
5. Click the last-name column to sort, then select a row: details must match the displayed contact, not its former position.
6. Click **Clear selection**: values disappear and the button becomes disabled.
7. Click **Reload sample contacts** repeatedly: there must still be six rows, with no duplicates or selected contact. Sorting resets too.
8. Resize the window down to its minimum and back up, and move the split divider: controls should remain usable.
9. Close and reopen: the six sample contacts return. Nothing was persisted.

## 10. Run the supplied checks

After the application works, copy the reference `src/test/` directory into your working project and run:

```bash
mvn test
```

Expect **three model/data tests to pass and one GUI smoke test to be skipped**. This is deliberate: the regular test command must work without a graphical display.

In a graphical session, run the additional smoke test:

```bash
mvn -Djavafx.smoke=true test
```

Expect four tests to pass, with none skipped. It loads the real view on the JavaFX thread, briefly displays it, and checks selection, clearing, and reloading. Do not infer visual quality from an automated test alone.

## Troubleshooting

| Symptom | What to check |
|---|---|
| Invalid release / unsupported class version | JDK 25 in `mvn -version`, the project SDK, and the IDE's Maven runner. |
| `Location is not set` or resource lookup fails | FXML path, filename case, and whether Maven copied it into `target/classes`. |
| FXML load exception | Read the deepest `Caused by`: controller name, field type, missing action, or module access may be wrong. |
| Injected field is null | Check the exact `fx:id`; configure fields in `initialize()`, not the constructor. |
| Rows exist but columns are blank | Check both cell-value factories and the getter used by each column. |
| No details after selection | Check the listener and all six value-label IDs. |
| No theme | Check the stylesheet resource path and style classes; do not use a machine-specific absolute path. |
| `Unable to open DISPLAY` | Run on a graphical desktop, or use Xvfb for the smoke test. |
| JavaFX native libraries cannot load on Linux | Check that a supported graphical environment and the distribution's GTK 3 runtime libraries are installed. |

## Small extensions, not requirements

Change the sample contacts, adjust the color palette, or add an email field end-to-end (model, sample data, FXML, and detail rendering). Keep the main checkpoint small. Do not add JDBC, HTTP calls, or unused New/Edit/Delete controls yet.
