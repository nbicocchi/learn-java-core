# E7 — Functional Programming

**Java 25 · Practice without AI-generated code**

Implement the exercises yourself and validate your work with the supplied tests where available. The sections follow the order of the covered course topics; exercises may combine earlier skills.

[Course slides and module index](../../../../../../../../../README.md#slides) · [AI exercises](../../../../../../../../exercises-ai/E7%20-%20Java%20Functional%20Programming.md)

## Contents

- [Lambdas and functional interfaces](#lambdas-and-functional-interfaces)
- [Stream pipelines](#stream-pipelines)
- [Stream collection and reduction](#stream-collection-and-reduction)

## Running tests

From `exercises/exercises-no-ai/`:

```sh
mvn test
mvn -Dtest="com.nbicocchi.exercises.e7_functional.**" test
```

## Lambdas and functional interfaces

### Exercise 1 — JustPrint

Source: [JustPrint.java](JustPrint.java). Tests: [JustPrintTest.java](../../../../../../test/java/com/nbicocchi/exercises/e7_functional/JustPrintTest.java).

Write two static generic methods receiving a generic List and a generic Map, respectively, and printing their contents to standard output using the formats below. Each method body should contain a single forEach call (see List.forEach() and Map.forEach()).

Examples:

* justPrintList(["Marco", "Matteo", "Luca", "Giovanni"]) → void

Prints:
```text
Hello Marco!
Hello Matteo!
Hello Luca!
Hello Giovanni!
```

* justPrintMap({1: "nicola", 2: "agata", 3: "darma", 4: "marzia"}) → void

Prints:
```text
k:4, v:marzia
k:3, v:darma
k:2, v:agata
k:1, v:nicola
```

The map output shows one possible iteration order. Do not require this order unless the chosen Map implementation guarantees it.

The methods have the following prototypes:

```java
public static <T> void justPrintList(List<T> list);
public static <K,V> void justPrintMap(Map<K, V> map);
```

where:

* **list** is the generic list to be processed.
* **map** is the generic map to be processed.

---

### Exercise 2 — JustPrintParametrized

Source: [JustPrintParametrized.java](JustPrintParametrized.java). Tests: [JustPrintParametrizedTest.java](../../../../../../test/java/com/nbicocchi/exercises/e7_functional/JustPrintParametrizedTest.java).

Generalize JustPrint so that justPrintList and justPrintMap receive both the data and the printing action, supplied as a Consumer or BiConsumer. The action determines the output format. Define the method signatures yourself.

---

## Stream pipelines

### Exercise 3 — PayMore

Source: [PayMore.java](PayMore.java). Tests: [PayMoreTest.java](../../../../../../test/java/com/nbicocchi/exercises/e7_functional/PayMoreTest.java).

A large telecom operator keeps track of the monthly subscription fees associated with each phone number using a Map like the one shown below (illustrative notation, not a Java initializer):

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

### Exercise 4 — NoTeen

Source: [NoTeen.java](NoTeen.java). Tests: [NoTeenTest.java](../../../../../../test/java/com/nbicocchi/exercises/e7_functional/NoTeenTest.java).

Given a list of integers, return a list of those numbers, omitting any that are between 13 and 19
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

### Exercise 5 — OneTen

Source: [OneTen.java](OneTen.java). Tests: [OneTenTest.java](../../../../../../test/java/com/nbicocchi/exercises/e7_functional/OneTenTest.java).

Given a list of integers, return a list obtained by adding 1 to each integer and multiplying the result by 10.

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

### Exercise 6 — TwoTwo

Source: [TwoTwo.java](TwoTwo.java). Tests: [TwoTwoTest.java](../../../../../../test/java/com/nbicocchi/exercises/e7_functional/TwoTwoTest.java).

Given a list of non-negative integers, return a list of those numbers multiplied by 2, omitting any of the resulting numbers that end in 2 (see Stream.map(), Stream.filter(), Stream.collect()).

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

### Exercise 7 — SquareFive

Source: [SquareFive.java](SquareFive.java). Tests: [SquareFiveTest.java](../../../../../../test/java/com/nbicocchi/exercises/e7_functional/SquareFiveTest.java).

Given a list of integers, square each number, add 10, omit results ending in 5, and return the count of the remaining results.

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

### Exercise 8 — AllPalindrome

Source: [AllPalindrome.java](AllPalindrome.java). Tests: [AllPalindromeTest.java](../../../../../../test/java/com/nbicocchi/exercises/e7_functional/AllPalindromeTest.java).

Given a list of strings, return true if every string is a palindrome. Comparisons are case-sensitive. An empty list returns true.

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

### Exercise 9 — NoYY

Source: [NoYY.java](NoYY.java). Tests: [NoYYTest.java](../../../../../../test/java/com/nbicocchi/exercises/e7_functional/NoYYTest.java).

Given a list of strings, return a list where each string has "y" added to its end, omitting any
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

### Exercise 10 — NoYYZ

Source: [NoYYZ.java](NoYYZ.java). Tests: [NoYYZTest.java](../../../../../../test/java/com/nbicocchi/exercises/e7_functional/NoYYZTest.java).

Append "y" to each string and omit results containing "yy" anywhere. Among the retained results, print each string starting with "z" to standard output (see Stream.peek()). Strings removed by the filter are not printed.

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

## Stream collection and reduction

### Exercise 11 — Words

Source: [Words.java](Words.java). Tests: [WordsTest.java](../../../../../../test/java/com/nbicocchi/exercises/e7_functional/WordsTest.java).

Use [Ulysses by James Joyce](https://github.com/laumann/ds/blob/master/hashing/books/) (a copy is supplied in `src/main/resources/james-joyce-ulysses.txt`). Write two methods reading the lines and returning, respectively:
(a) how many lines contain a specific substring, (b) how many times a specific word appears in an isolated form (not as
a substring).

The methods have the following prototypes:

```java
public static long howManyLines(String filename, String subString) throws IOException;
public static long howManyTimes(String filename, String word) throws IOException;
```

where:

* **filename** is the filename of the book.
* **subString** is the substring of interest.
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

### Exercise 12 — ToString

Source: [ToString.java](ToString.java). Tests: [ToStringTest.java](../../../../../../test/java/com/nbicocchi/exercises/e7_functional/ToStringTest.java).

Write a method returning the first n elements of a `Stream<T>` as a comma-separated string, using each element's toString() representation. Use commas without spaces and no trailing comma (see Collectors.joining()). Assume n is non-negative; if the stream contains fewer than n elements, include all of them.

The method has the following prototype:

```java
public static <T> String toString(Stream<T> stream, int n);
```

where:

* **stream** is the stream to be processed.
* **n** is the number of elements to be included.

---

### Exercise 13 — BankAccount

Source: [BankAccount.java](BankAccount.java). Tests: [BankAccountTest.java](../../../../../../test/java/com/nbicocchi/exercises/e7_functional/BankAccountTest.java).

Given the following class Account:

```java
public static class Account {
    double amount;
    double interestRate;
    LocalDateTime duePayment;
    //...
}
```

Write a method to: 
* (a) retain accounts whose duePayment is before the current time,
* (b) apply interest to those accounts (amount += amount * interestRate),
* (c) sort them by their updated amount in descending order,
* (d) return a new List containing those accounts. The Account objects themselves are updated.

The method has the following prototype:

```java
public static List<Account> applyInterest(List<Account> accounts);
```

where:

* **accounts** is the list of Account objects to process.

---

### Exercise 14 — PhoneBook

Source: [PhoneBook.java](PhoneBook.java). Tests: [PhoneBookTest.java](../../../../../../test/java/com/nbicocchi/exercises/e7_functional/PhoneBookTest.java).

Write a class PhoneBook implementing a phone book as a `List<Person>`.

```java
public static class Person {
    String name;
    String lastname;
    String phone;
    //...
}
```

The class has a constructor accepting a `List<Person>` and provides searchByLastname and searchByNameAndLastname. Each method returns an Optional containing the first matching Person in list order, or Optional.empty() if there is no match. The two methods have the following prototypes:

```java
public Optional<Person> searchByLastname(String lastname);
public Optional<Person> searchByNameAndLastname(String name, String lastname);
```

where:

* **name** is the name to be found.
* **lastname** is the last name to find.

---

### Exercise 15 — PayOnAverage

Source: [PayOnAverage.java](PayOnAverage.java). Tests: [PayOnAverageTest.java](../../../../../../test/java/com/nbicocchi/exercises/e7_functional/PayOnAverageTest.java).

A large telecom operator keeps track of the monthly subscription fees associated with each phone number using a Map like the one shown below (illustrative notation, not a Java initializer):

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

Write a method returning a `Map<String, Double>` whose keys are three-digit phone prefixes (e.g., "347", "333", "348") and whose values are the average subscription fee for each prefix (see [this guide](https://www.baeldung.com/java-maps-streams) and Collectors.groupingBy()).

Examples:

* payOnAverage(fees) -> {"347" : 11.75, "333" : 9.75, "348" : 7.75, "331" : 7.85}

The method has the following prototype:

```java
public static Map<String, Double> payOnAverage(Map<String, Double> fees);
```

where:

* **fees** is the map associating each number to a monthly subscription fee.

---
