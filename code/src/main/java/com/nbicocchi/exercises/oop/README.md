# Object Oriented Programming - DIEF/UNIMORE

## Java Exercises (OOP Design)

Before starting this module, generate the JavaDoc documentation of the whole oop package.

In IntelliJ, click on the oop package then select Tools -> Generate JavaDoc...

---

**[basic.ClickCounter]** Write a class named ClickCounter representing a simple device to keep track of how many times a button (in this case a method) is clicked.
Internally, the class represents the number of clicks with an int value.
The class provides the following methods:
* public int getValue() returning the current number of clicks.
* public void click() increasing the number of clicks of 1 unit.
* public void undo() decreasing the number of clicks of 1 unit (but preventing negative click values).
* public void reset() setting the number of clicks to 0.

Refer to the UML diagram, JavaDoc documentation, and unit tests for further inspiration.

```mermaid
classDiagram
direction BT
class ClickCounter {
    ~ int value
    + ClickCounter()
    + getValue() int
    + click() void
    + undo() void
    + reset() void
}
```

---

**[basic.RationalNumber]** Write a class named RationalNumber representing a rational number.
RationalNumbers are immutable objects, indeed they cannot be changed after creation.
Internally, the class represents numerator and denominator as int values. RationalNumbers must support equality with other RationalNumbers (see Object.equals(), Object.hashCode()) 
The class provides the following methods:
* public RationalNumber(int numerator, int denominator) creating the rational number. Before creating the object, numerator and denominator have to be simplified (i.e., divided by their greatest common divisor). 
* public getNumerator() returning the numerator.
* public getDenominator() returning the denominator.
* public RationalNumber add(RationalNumber o) returning a RationalNumber object representing the sum of the current number and another number. 
* public RationalNumber multiply(RationalNumber o) returning a RationalNumber object representing the multiplication of the current number and another number.
* public String toString().

You can use the following two methods for computing the least common multiple and the greatest common divisor of two integer numbers. These methods are not part of the public interface of the class.

```
static int greatestCommonDivisor(int a, int b) {
    int max = Math.max(a, b);
    int min = Math.min(a, b);

    int rest = max % min;
    if (max % min == 0) {
        return min;
    } else {
        return greatestCommonDivisor(min, rest);
    }
}

static int leastCommonMultiple(int a, int b) {
    return Math.abs(a * b) / greatestCommonDivisor(a, b);
}
```

Refer to the UML diagram, JavaDoc documentation, and unit tests for further inspiration.

```mermaid
classDiagram
direction BT
class RationalNumber {
  ~ int denominator
  ~ int numerator
  + RationalNumber(int, int) 
  + getNumerator() int
  + getDenominator() int
  + add(RationalNumber) RationalNumber
  + multiply(RationalNumber) RationalNumber
  ~ leastCommonMultiple(int, int) int
  ~ greatestCommonDivisor(int, int) int
  + equals(Object) boolean
  + hashCode() int
  + toString() String
}
```

---

**[basic.Circle]** Write a class named Circle representing a Circle on a 2D plane.
Internally, the class uses a Point object and an int value for representing the center and the radius of the Circle, respectively. 
The class provides the following methods:
* public Circle(Point center, int radius) creating the circle.
* getters and setters.
* public double getPerimeter() returning the perimeter of the circle.
* public double getArea() returning the area of the circle.
* public boolean contains(Point point) returning true if point is contained within the circle.
* public void translate(int dx, int dy) moving the circle on the 2D plane. dx and dy are the x and y components of the translation vector.
* public String toString().

Refer to the UML diagram, JavaDoc documentation, and unit tests for further inspiration.

```mermaid
classDiagram
direction BT
class Circle {
  ~ Point center
  ~ int radius
  + Circle(Point, int) 
  + getCenter() Point
  + getRadius() int
  + setCenter(Point) void
  + setRadius(int) void
  + getPerimeter() double
  + getArea() double
  + contains(Point) boolean
  + translate(int, int) void
  + toString() String
}
```

---

**[basic.Polygon]** Write a class named Polygon representing an irregular polygon.
Internally, the class uses a Point[] for representing the vertices of the polygon.
The class provides the following methods:
* public Polygon(Point[] vertices) creating the polygon.
* public int getVerticesCount() returning the number of vertices.
* public double getPerimeter() returning the perimeter of the polygon. 
* public double getArea() returning the area of the polygon. 
* public String toString().

Refer to [this guide](https://arachnoid.com/area_irregular_polygon/index.html) for computing perimeter and area.

Refer to the UML diagram, JavaDoc documentation, and unit tests for further inspiration.

```mermaid
classDiagram
direction BT
class Polygon {
  ~ Point[] vertices
  + Polygon(Point[]) 
  + getVerticesCount() int
  + getPerimeter() double
  + getArea() double
  + toString() String
}
```

---

**[basic.BankAccount]** Write a class named BankAccount representing a bank account.
Internally, the class uses a double value for representing the balance of the account.
The class provides the following methods:
* public BankAccount() creating an empty account.
* public BankAccount(double balance) creating an account with the specified balance.
* public double getBalance() getting the current balance.
* public void deposit(double amount) depositing the specific amount into the account.
* public void withdraw(double amount) withdrawing the specified amount from the account.

Refer to the UML diagram, JavaDoc documentation, and unit tests for further inspiration.

```mermaid
classDiagram
direction BT
class BankAccount {
     ~ double balance
    + BankAccount()
    + BankAccount(double)
    + getBalance() double
    + deposit(double) void
    + withdraw(double) void
}
```

---

**[basic.EnhancedResizableArray]** Write a class named EnhancedResizableArray representing a resizable array. It internally keeps an int array, enlarges it when needed, and provides its key functionalities via a set of methods:

* public EnhancedResizableArray() creating an empty resizable array (the underlying int[] has a default capacity of 4 items).
* public int get(int index) returning the element at the specified index.
* public void set(int index, int value) setting the element at the specified index with value. If the underlying int[] capacity is smaller than index, a new int[] (with a capacity of index * 2 items) have to be allocated. All the newly allocated elements have to be set to 0.
* public boolean contains(int value) returning true if the specified value is contained within the internal array.
* public void fill(int) setting all the elements of the internal array to the specified value.
* public int length() returning the capacity of the internal array.
* public int[] toArray() returning a copy of the internal array.

Refer to the UML diagram, JavaDoc documentation, and unit tests for further inspiration.

```mermaid
classDiagram
direction BT
class EnhancedResizableArray {
  + static final int DEFAULT_CAPACITY = 4;
  ~ int[] v
  + EnhancedResizableArray() 
  + get(int) int
  + set(int, int) void
  + contains(int) boolean
  + fill(int) void
  + length() int
  + toArray() int[]
}
```

---

**[basic.Letter]** Write a class for authoring a simple letter.
In the constructor, supply the names of the sender and the recipient:

```
public Letter(String from, String to)
```

Supply a method to add a line of text to the body of the letter.

```
public void addLine(String line)
```

Supply a method that returns the entire text of the letter.

```
public String getText()
```

The text has the form:

```
Dear recipient name: 
blank line
first line of the body 
second line of the body 
. . .
last line of the body 
blank line 
Sincerely,
blank line
sender name
```

Refer to the UML diagram, JavaDoc documentation, and unit tests for further inspiration.

```mermaid
classDiagram
direction BT
class Letter {
  ~ String from
  ~ String to
  ~ StringBuilder lines
  + Letter(String, String)
  + addLine(String) void 
  + getText() String
}
```

---

**[reverse package]** Given the Reverser interface defining a single method *reverse* for reversing a string, provide two implementations namely ReverserFast and ReverserSlow providing two different strategies for reversing a String. As a suggestion, ReverserSlow could use a char array (see String.valueOf()), while ReverserFast could use a StringBuilder. Provide also a simple main() in which the Reverser interface is implemented anonymously.

Refer to the UML diagram, JavaDoc documentation, and unit tests for further inspiration.

```mermaid
classDiagram
direction BT
class Reverser {
  <<Interface>>
  + reverse(String) String
}
class ReverserFast {
  + reverse(String) String
}
class ReverserSlow {
  + reverse(String) String
}

ReverserFast  ..|>  Reverser 
ReverserSlow  ..|>  Reverser 
```

---

**[phonebook package]** Define two classes, namely PhoneBookArray and PhoneBookList implementing the PhoneBook interface (reported below). 

* PhoneBookArray internally models the phone book as a ```Person[]```.
* PhoneBookList internally models the phone book as a ```ArrayList<Person>```.

The methods searchByLastname, searchByNameAndLastname of the PhoneBook interface have to return all the instances matching the search criteria. Both implementations limit the number of persons to 256 and do not accept duplicate elements. 

Refer to the UML diagram, JavaDoc documentation, and unit tests for further inspiration.

```mermaid
classDiagram
direction BT
class Person {
  ~ String lastname
  ~ String name
  ~ String phone
  + Person(String, String, String) 
  + getLastname() String
  + getName() String
  + getPhone() String
  + setLastname(String) void
  + setName(String) void
  + setPhone(String) void
  + equals(Object) boolean
  + hashCode() int
  + toString() String
}
class PhoneBook {
  <<Interface>>
  + addPerson(Person) boolean
  + removePerson(Person) boolean
  + searchByLastname(String) Person[]
  + searchByNameAndLastname(String, String) Person[]
}
class PhoneBookArray {
  + static final int MAX_PERSONS = 256
  ~ Person[] phoneBook
  + PhoneBookArray() 
  + addPerson(Person) boolean
  + removePerson(Person) boolean
  + searchByLastname(String) Person[]
  + searchByNameAndLastname(String, String) Person[]
}
class PhoneBookList {
  + static final int MAX_PERSONS = 256
  ~ ArrayList~Person~ phoneBook
  + PhoneBookList() 
  + addPerson(Person) boolean
  + removePerson(Person) boolean
  + searchByLastname(String) Person[]
  + searchByNameAndLastname(String, String) Person[]
}
PhoneBookArray  ..|>  PhoneBook 
PhoneBookList  ..|>  PhoneBook 
```

---

**[bankaccount package]** Define two classes, namely BankAccountEasy and BankAccountPro implementing the BankAccount interface (reported below).
* BankAccountPro represents a fully fledged bank account, allowing international transfers, negative balances, and a 2pc interest rate. However, all this comes with the cost of 1 Euro for each operation (deposit, withdrawal). Note well: the first two characters of IBANs represent a country code.
* BankAccountEasy represents a basic bank account, which does not support negative balances, international transfers, and does not pay any interest. Nevertheless, deposits and withdrawals are free.

Both accounts must refuse to set invalid IBANs or positive fees (money being added for each operation).

Refer to the UML diagram, JavaDoc documentation, and unit tests for further inspiration.

```mermaid
classDiagram
direction BT
class AbstractBankAccount {
  <<abstract>>
  ~ String IBAN
  ~ double balance
  ~ double interestRate
  ~ double operationFee
  + AbstractBankAccount(String, double, double, double)
  + addInterest() void
  ~ applyFee() void
  ~ checkIBAN(String) void
  + deposit(double) void
  + getBalance() double
  + getIBAN() String
  + getInterestRate() double
  + getOperationFee() double
  + setBalance(double) void
  + setIBAN(String) void
  + setInterestRate(double) void
  + setOperationFee(double) void
  + transfer(BankAccount, double) double
  + withdraw(double) double
}
class BankAccount {
  <<Interface>>
  + addInterest() void
  + deposit(double) void
  + getBalance() double
  + getIBAN() String
  + getInterestRate() double
  + getOperationFee() double
  + setBalance(double) void
  + setIBAN(String) void
  + setInterestRate(double) void
  + setOperationFee(double) void
  + transfer(BankAccount, double) double
  + withdraw(double) double
}
class BankAccountEasy {
  + BankAccountEasy(String, double)
  + transfer(BankAccount, double) double
  + withdraw(double) double
}
class BankAccountPro {
  + BankAccountPro(String, double)
  + deposit(double) void
  + withdraw(double) double
}

AbstractBankAccount  ..>  BankAccount
BankAccountEasy  --|>  AbstractBankAccount
BankAccountPro  --|>  AbstractBankAccount
```
---

**[shape package]** Define two classes, namely Circle and Rectangle representing a circle and rectangle on a 2D plane.
* Circle internally uses a Point object and a double value for representing its center and radius.
* Rectangle internally uses two Point objects for representing its upper-left and bottom-right vertices. The edges of the rectangle have to be parallel to the x and y axes.

Both shapes must also support:
* an id (String) for identifying the shape
* a color (String) for coloring the shape (RGB Web Standard #RRGGBB, see https://en.wikipedia.org/wiki/Web_colors)
* the capability of moving on the 2D plane (move() method)
* the capability of resizing (resize() method)
* the capability of computing area and perimeter (getArea(), getPerimeter() methods)

Refer to the UML diagram, JavaDoc documentation, and unit tests for further inspiration.

```mermaid
classDiagram
direction BT
class AbstractShape {
  <<abstract>>
  ~ String color
  ~ String id
  + AbstractShape(String, String) 
  + getArea() double
  + getColor() String
  + getId() String
  + getPerimeter() double
  + move(Point) void
  + resize(double) void
  + setColor(String) void
  + setId(String) void
}
class Circle {
  ~ Point center
  ~ double radius
  + Circle(String, String, Point, double) 
  + getArea() double
  + getCenter() Point
  + getPerimeter() double
  + getRadius() double
  + move(Point) void
  + resize(double) void
  + setCenter(Point) void
  + setRadius(double) void
  + toString() String
}
class Rectangle {
  ~ Point bottomRight
  ~ Point upperLeft
  + Rectangle(String, String, Point, Point) 
  + getArea() double
  + getBottomRight() Point
  + getPerimeter() double
  + getUpperLeft() Point
  + move(Point) void
  + resize(double) void
  + setBottomRight(Point) void
  + setUpperLeft(Point) void
  + toString() String
}
class Computable {
  <<Interface>>
  + getArea() double
  + getPerimeter() double
}
class Movable {
  <<Interface>>
  + move(Point) void
}
class Resizable {
  <<Interface>>
  + resize(double) void
}

AbstractShape  ..>  Computable 
AbstractShape  ..>  Movable 
AbstractShape  ..>  Resizable 
Circle  --|>  AbstractShape 
Rectangle  --|>  AbstractShape 
```

---

**[polynomials package]** Define two classes, namely ArrayPoly and ListPoly providing two implementations of the Poly interface representing a generic polynomial p = c0 + c1 * x^1 + c2 * x^2 + ... + cn * x^n.
* ArrayPoly internally stores the coefficients (c0 ... cn) as a double[].
* ListPoly internally stores the coefficients (c0 ... cn) as an ```ArrayList<Double>```.

As prescribed by the Poly interface, both implementations must provide:
* a method *coefficient(int degree)* returning the coefficient of a given degree (0 ... n).
* a method *coefficients()* returning a double[] containing all the coefficients.
* a method *degree()* returning the degree of the polynomial (the number of coefficients - 1).
* a method *derivative()* returning the derivative polynomial.

Both implementations must also redefine *equals()* and *hashCode()* in order to be compared with other Poly objects.

Refer to the UML diagram, JavaDoc documentation, and unit tests for further inspiration.

```mermaid
classDiagram
direction BT
class AbstractPoly {
  <<abstract>>
  + AbstractPoly() 
  ~ derive() double[]
  + equals(Object) boolean
  + hashCode() int
  + toString() String
}
class ArrayPoly {
  ~ double[] coefficients
  + ArrayPoly(double[]) 
  + coefficient(int) double
  + coefficients() double[]
  + degree() int
  + derivative() Poly
}
class ListPoly {
  ~ ArrayList~Double~ coefficients
  + ListPoly(double[]) 
  + coefficient(int) double
  + coefficients() double[]
  + degree() int
  + derivative() Poly
}
class Poly {
  <<Interface>>
  + coefficient(int) double
  + coefficients() double[]
  + degree() int
  + derivative() Poly
}

AbstractPoly  ..>  Poly 
ArrayPoly  --|>  AbstractPoly 
ListPoly  --|>  AbstractPoly 
```

---

**[library package]** A library needs a software system for managing subscribers, rents of books and dvds, and be notified about late returns. 
* Books can be modelled with a title (String), a publication year (int), and a number of pages (int).
* Dvds can be modelled with a title (String), a publication year (int), and a length in minutes (int).
* People can be modelled with an id (String), a name (String), and a lastname (String).
* Rents can be modelled with an item (a book or a dvd), a person, and two dates representing the beginning and the end of the rent.
* The library itself can be modelled as a `List<Rent>` and provides methods for adding/removing rents a method *getExpired()* returning all the late rents.

Provide and implementation of all the needed classes.

Refer to the UML diagram, JavaDoc documentation, and unit tests for further inspiration.

```mermaid
classDiagram
direction BT
class Item {
    <<Abstract>>
    ~ String title
    ~ int year
    + Item(String, int)
    + getTitle() String
    + getYear() int
    + setTitle(String) void
    + setYear(int) void
    + equals(Object) boolean
    + hashCode() int
}
class Book {
    ~ int pages
    + Book(String, int, int)
    + getPages() int
    + setPages(int) void
    + toString() String
}
class Dvd {
    ~ int length
    + Dvd(String, int, int)
    + getLength() int
    + setLength(int) void
    + toString() String
}
class Person {
    ~ String id
    ~ String lastname
    ~ String name
    + Person(String, String, String)
    + getId() String
    + getLastname() String
    + getName() String
    + setId(String) void
    + setLastname(String) void
    + setName(String) void
    + equals(Object) boolean
    + hashCode() int
    + toString() String
}
class Rent {
    ~ LocalDate begin
    ~ LocalDate end
    ~ Item item
    ~ Person person
    + Rent(Item, Person, LocalDate, LocalDate)
    + getBegin() LocalDate
    + getEnd() LocalDate
    + getItem() Item
    + getPerson() Person
    + setBegin(LocalDate) void
    + setEnd(LocalDate) void
    + setItem(Item) void
    + setPerson(Person) void
    + isExpired(LocalDate) boolean
    + equals(Object) boolean
    + hashCode() int
    + toString() String
}
class Library {
    ~ ArrayList~Rent~ rents
    + Library()
    + addRent(Rent) boolean
    + removeRent(Rent) boolean
    + getExpired(LocalDate) ArrayList~Rent~
}

Book  --|>  Item
Dvd  --|>  Item
Rent "*" --> "1" Person
Rent "*" --> "1" Item
Library "1" --> "*" Rent
```

---

