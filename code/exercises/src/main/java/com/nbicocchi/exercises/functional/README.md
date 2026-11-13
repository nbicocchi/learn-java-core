# Object-Oriented Programming - DIEF/UNIMORE

## Java Exercises (Functional)

**[JustPrint.java]** Write two static generic methods receiving, respectively, a generic List and a generic Map and printing to standard output their content following the template below. Both methods must be 1 line long (see List.forEach(), Map.forEach()).

Examples:

* justPrintList(["Marco", "Matteo", "Luca", "Giovanni"]) → void

Prints:
```text
Hello Marco!
Hello Matteo!
Hello Luca!
Hello Giovanni!
```

* justPrintMap(1: "nicola", 2: "agata", 3: "darma", 4: "marzia") → void

Prints:
```text
k:4, v:marzia
k:3, v:darma
k:2, v:agata
k:1, v:nicola
```

The methods have the following prototypes:

```java
public static <T> void justPrintList(List<T> list);
public static <K,V> void justPrintMap(Map<K, V> map);
```

where:

* **list** is the generic list to be processed.
* **map** is the generic map to be processed.

---

**[JustPrintParametrized.java]** Write a more general version of the JustPrint exercise allowing the two methods (i.e., justPrintList, justPrintMap) not only to receive the collection of values to be printed but also the template for printing them (see Consumer and BiConsumer interfaces). The prototypes are not reported because they are the answer.

---

**[PayMore.java]** A large telecom operator keeps track of the monthly subscription fees associated with each phone number using a Map as the one showed below:

```text
Map<String, Double> fees = {
    "34745..." : 11.75,
    "33367..." : 9.75,
    "34833..." : 7.75,
    "33188..." : 7.75,
    "33112..." : 7.95,
    ...
}
```

Write a (one-line!) method for adding (or removing) a price delta to all numbers of a certain family (see Map.replaceAll()).

Examples:

* payMore(fees, "333", +0.5) -> adds 50 cents of monthly fee to all "333" numbers.
* payMore(fees, "347", -1.0) -> removes 1 euro of monthly fee to all "347" numbers.

The method has the following prototype:

```java
public static void payMore(Map<String, Double> fees, String numberFamily, double costDelta);
```

where:

* **fees** is the map associating each number to a monthly subscription fee.
* **numberFamily** is the family of numbers (i.e., the first 3 digits of every number).
* **costDelta** is the delta to be applied to all the specified monthly fees.

---

**[NoTeen.java]** Given a list of integers, return a list of those numbers, omitting any that are between 13 and 19
inclusive (see Stream.filter(), Stream.collect()).

Examples:

* noTeen([12, 13, 19, 20]) → [12, 20]
* noTeen([1, 14, 1]) → [1, 1]
* noTeen([15]) → []

The method has the following prototype:

```java
public static List<Integer> noTeen(List<Integer> nums);
```

where:

* **nums** is the List of Integers to be processed.

---

**[OneTen.java]** Given a list of integers, return a list where each integer is added to 1 and the result is multiplied
by 10.

Examples:

* oneTen([1, 2, 3]) → [20, 30, 40]
* oneTen([6, 8, 6, 8, 1]) → [70, 90, 70, 90, 20]
* oneTen([10]) → [110]

The method has the following prototype:

```java
public static List<Integer> oneTen(List<Integer> nums);
```

where:

* **nums** is the List of Integers to be processed.

---

**[TwoTwo.java]** Given a list of non-negative integers, return a list of those numbers multiplied by 2, omitting any of the resulting numbers that end in 2 (see Stream.map(), Stream.filter(), Stream.collect()).

Examples:

* twoTwo([1, 2, 3]) → [4, 6]
* twoTwo([2, 6, 11]) → [4]
* twoTwo([0]) → [0]

The method has the following prototype:

```java
public static List<Integer> twoTwo(List<Integer> nums);
```

where:

* **nums** is the List of Integers to be processed.

---

**[SquareFive.java]** Given a list of integers, square those numbers, add 10, omit any of the resulting numbers that end in 5, and return their number.

Examples:

* squareFive([3, 1, 4]) → 3 ([19, 11, 26])
* squareFive([1]) → 1 ([11])
* squareFive([5]) → 0 ([])

The method has the following prototype:

```java
public static long squareFive(List<Integer> nums);
```

where:

* **nums** is the List of Integers to be processed.

---

**[AllPalindrome.java]** Given a list of strings, return true if all strings are palindrome.

Examples:

* allPalindrome(["kayak", "deed", "noon"]) → true
* allPalindrome(["kayak", "hello"]) → false
* allPalindrome(["hello", "world"]) → false

The method has the following prototype:

```java
public static boolean allPalindrome(List<String> strings);
```

where:

* **strings** is the List of strings to be processed.

---

**[NoYY.java]** Given a list of strings, return a list where each string has "y" added to its end, omitting any
resulting strings that contain "yy" as a substring anywhere.

Examples:

* noYY(["a", "b", "c"]) → ["ay", "by", "cy"]
* noYY(["a", "b", "cy"]) → ["ay", "by"]
* noYY(["xx", "ya", "zz"]) → ["xxy", "yay", "zzy"]

The method has the following prototype:

```java
public static List<String> noYY(List<String> strings);
```

where:

* **strings** is the List of strings to be processed.

---

**[NoYYZ.java]** Given a list of strings, return a list where each string has "y" added to its end, omitting any
resulting strings that contain "yy" as a substring anywhere. 
Nevertheless, each string starting with "z" must be printed to standard output. (see Stream.peek())

Examples:

* noYYZ(["a", "b", "c"]) → ["ay", "by", "cy"]
* noYYZ(["a", "b", "cy"]) → ["ay", "by"]
* noYYZ(["xx", "ya", "zz"]) → ["xxy", "yay", "zzy"] (prints "zzy")

The method has the following prototype:

```java
public static List<String> noYYZ(List<String> strings);
```

where:

* **strings** is the List of strings to be processed.

---

**[Words.java]** Download the [Ulysses, by James Joyce](https://github.com/laumann/ds/blob/master/hashing/books/) book.
Write two methods, reading all the lines of the file and returning respectively:
(a) how many lines contain a specific substring, (b) how many times a specific word appears in an isolated form (not as
a substring).

The methods have the following prototype:

```java
public static long howManyLines(String filename, String subString) throws IOException;
public static long howManyTimes(String filename, String word) throws IOException;
```

where:

* **filename** is the filename of the book.
* **subString** is the sub string of interest.
* **word** is the word of interest.

For reading the file and transforming it into a ```List<String>```, you can use:

```java
public static List<String> getLines(String filename) throws IOException {
    return Files.readAllLines(Path.of(filename));
}
```

For splitting a line into a list of single words, you can use:


```java
public static List<String> lineToWords(String line) {
    return Arrays.asList(line.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+"));
}
```

---

**[ToString.java]** Write a method turning a Stream<T> into a comma-separated list of its first n elements (see Collectors.joining())

The method has the following prototype:

```java
public static <T> String toString(Stream<T> stream, int n);
```

where:

* **stream** is the stream to be processed.
* **n** is the number of elements to be included.

---

**[BankAccount.java]** Given the following class Account:

```java
public static class Account {
    double amount;
    double interestRate;
    LocalDateTime duePayment;
    //...
}
```

Write a method to: 
* (a) filter all the accounts whose duePayment is expired, 
* (b) apply interests (amount += amount * interestRate),
* (c) sort the accounts based on their amount (in decremental order),
* (d) return a List of the filtered accounts.

The method has the following prototype:

```java
public static List<Account> applyInterest(List<Account> accounts);
```

where:

* **accounts** is the list of Account to be processed.

---

**[PhoneBook.java]** Write a class PhoneBook implementing a phone book as a `List<Person>`.

```java
public static class Person {
    String name;
    String lastname;
    String phone;
    //...
}
```

The class has a single constructor accepting a `List<Person>` and provides two methods *searchByLastname*, *searchByNameAndLastname* returning the first Person instance matching the search criteria. The two methods have the following prototypes:

```java
public Optional<Person> searchByLastname(String lastname);
public Optional<Person> searchByNameAndLastname(String name, String lastname);
```

where:

* **name** is the name to be found.
* **lastname** is the lastname to be found.

---

**[PayOnAverage.java]** A large telecom operator keeps track of the monthly subscription fees associated with each phone number using a Map as the one showed below:

```text
Map<String, Double> fees = {
    "34745..." : 11.75,
    "33367..." : 9.75,
    "34833..." : 7.75,
    "33188..." : 7.75,
    "33112..." : 7.95,
    ...
}
```

Write a method returning a Map<String, Double> having as keys the families of numbers (e.g., "347", "333", "348", etc.) and as values the average subscription fee for that family (see [this guide](https://www.baeldung.com/java-maps-streams), and Collectors.groupingBy()).

Examples:

* payOnAverage(fees) -> {"347" : 11.75, "333" : 9.75, "348" : 7.75, "331" : 7.85}

The method has the following prototype:

```java
public static Map<String, Double> payOnAverage(Map<String, Double> fees);
```

where:

* **fees** is the map associating each number to a monthly subscription fee.

---
