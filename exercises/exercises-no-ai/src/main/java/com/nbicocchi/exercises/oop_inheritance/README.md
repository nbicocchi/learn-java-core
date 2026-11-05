# Object-Oriented Programming - DIEF/UNIMORE

---

## [employees package]

### Specification

**Employee**

* `name` (String)
* `salary` (double)
* `getDetails()` →

  ```
  Employee{name='name', salary=salary}
  ```
* `toString()` → delegates to `getDetails()`

**Manager**

* `teamSize` (int)
* overrides `getDetails()` →

  ```
  Manager{name='name', salary=salary, teamSize=teamSize}
  ```

**Developer**

* `programmingLanguage` (String)
* overrides `getDetails()` →

  ```
  Developer{name='name', salary=salary, language='language'}
  ```

```mermaid
classDiagram
class Employee {
  # String name
  # double salary
  + Employee(String, double)
  + getName() String
  + getSalary() double
  + getDetails() String
  + toString() String
}
class Manager {
  - int teamSize
  + Manager(String, double, int)
  + getDetails() String
}
class Developer {
  - String programmingLanguage
  + Developer(String, double, String)
  + getDetails() String
}
Manager --|> Employee
Developer --|> Employee
```

---

## [animals package]

### Specification

**Animal**

* `name` (String)
* `makeSound()` → `"generic"`

**Dog**

* overrides `makeSound()` → `"woof"`

**Cat**

* overrides `makeSound()` → `"meow"`

```mermaid
classDiagram
class Animal {
  # String name
  + Animal(String)
  + getName() String
  + makeSound() String
}
class Dog {
  + Dog(String)
  + makeSound() String
}
class Cat {
  + Cat(String)
  + makeSound() String
}
Dog --|> Animal
Cat --|> Animal
```

---

## [shapes package]

### Specification

**Shape**

* `color` (String)
* `getArea()` → `0`
* `getDescription()` →

  ```
  Shape{color='color'}
  ```
* `toString()` → delegates to `getDescription()`

**Circle**

* `radius` (double)
* overrides `getArea()` → `Math.PI * radius^2`
* overrides `getDescription()` →

  ```
  Circle{color='color', radius=radius}
  ```

**Rectangle**

* `width`, `height` (double)
* overrides `getArea()` → `width * height`
* overrides `getDescription()` →

  ```
  Rectangle{color='color', width=width, height=height}
  ```

```mermaid
classDiagram
class Shape {
  # String color
  + Shape(String)
  + getColor() String
  + getArea() double
  + getDescription() String
  + toString() String
}
class Circle {
  - double radius
  + Circle(String, double)
  + getArea() double
  + getDescription() String
}
class Rectangle {
  - double width
  - double height
  + Rectangle(String, double, double)
  + getArea() double
  + getDescription() String
}
Circle --|> Shape
Rectangle --|> Shape
```

---

## [vehicles package]

### Specification

**Transport**

* `name` (String)
* `maxPassengers` (int)
* `getCapacityInfo()` →

  ```
  Transport{name='name', maxPassengers=maxPassengers}
  ```
* `toString()` → delegates to `getCapacityInfo()`

**Bus**

* `hasDoubleDeck` (boolean)
* overrides `getCapacityInfo()` →

  ```
  Bus{name='name', maxPassengers=maxPassengers, doubleDeck=true/false}
  ```

**Motorcycle**

* `hasSidecar` (boolean)
* overrides `getCapacityInfo()` →

  ```
  Motorcycle{name='name', maxPassengers=maxPassengers, sidecar=true/false}
  ```

```mermaid
classDiagram
class Transport {
  # String name
  # int maxPassengers
  + Transport(String, int)
  + getName() String
  + getMaxPassengers() int
  + getCapacityInfo() String
  + toString() String
}
class Bus {
  - boolean hasDoubleDeck
  + Bus(String, int, boolean)
  + getCapacityInfo() String
}
class Motorcycle {
  - boolean hasSidecar
  + Motorcycle(String, int, boolean)
  + getCapacityInfo() String
}
Bus --|> Transport
Motorcycle --|> Transport
```

---

## [electronics package]

### Specification

**Gadget**

* `brand` (String)
* `batteryLife` (double)
* `getStatus()` →

  ```
  Gadget{brand='brand', batteryLife=batteryLife}
  ```
* `toString()` → delegates to `getStatus()`

**Tablet**

* `screenSize` (double)
* overrides `getStatus()` →

  ```
  Tablet{brand='brand', batteryLife=batteryLife, screenSize=screenSize}
  ```

**Smartwatch**

* `supportsHeartRate` (boolean)
* overrides `getStatus()` →

  ```
  Smartwatch{brand='brand', batteryLife=batteryLife, heartRate=true/false}
  ```

```mermaid
classDiagram
class Gadget {
  # String brand
  # double batteryLife
  + Gadget(String, double)
  + getBrand() String
  + getBatteryLife() double
  + getStatus() String
  + toString() String
}
class Tablet {
  - double screenSize
  + Tablet(String, double, double)
  + getStatus() String
}
class Smartwatch {
  - boolean supportsHeartRate
  + Smartwatch(String, double, boolean)
  + getStatus() String
}
Tablet --|> Gadget
Smartwatch --|> Gadget
```

