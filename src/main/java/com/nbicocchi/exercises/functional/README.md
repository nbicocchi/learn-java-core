# Object Oriented Programming - DIEF/UNIMORE

## Java Exercises (Functional)

**[JustPrint.java]** Given a list of String, print all the elements using the Stream approach (see Stream.forEach()).

Examples:

* justPrint(["hello", "world"]) → void (prints "hello", "world")

The method has the following prototype:

```
public static void justPrint(List<String> strings);
```

where:

* **strings** is the List of String to be processed.

---

**[NoTeen.java]** Given a list of integers, return a list of those numbers, omitting any that are between 13 and 19
inclusive (see Stream.filter(), Stream.collect()).

Examples:

* noTeen([12, 13, 19, 20]) → [12, 20]
* noTeen([1, 14, 1]) → [1, 1]
* noTeen([15]) → []

The method has the following prototype:

```
public static List<Integer> noTeen(List<Integer> nums);
```

where:

* **nums** is the List of Integers to be processed.

---

**[TwoTwo.java]** Given a list of non-negative integers, return a list of those numbers multiplied by 2, omitting any of
the resulting numbers that end in 2 (see Stream.map(), Stream.filter(), Stream.collect()).

Examples:

* twoTwo([1, 2, 3]) → [4, 6]
* twoTwo([2, 6, 11]) → [4]
* twoTwo([0]) → [0]

The method has the following prototype:

```
public static List<Integer> twoTwo(List<Integer> nums);
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

```
public static List<Integer> oneTen(List<Integer> nums);
```

where:

* **nums** is the List of Integers to be processed.

---

**[SquareFive.java]** Given a list of integers, square those numbers, add 10, omit any of the resulting numbers that end
in 5, and return their number.

Examples:

* squareFive([3, 1, 4]) → 2 ([19, 11, 26])
* squareFive([1]) → 1 ([11])
* squareFive([5]) → 0 ([])

The method has the following prototype:

```
public static List<Integer> squareFive(List<Integer> nums);
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

```
public static boolean allPalindrome(List<String> strings);
```

where:

* **strings** is the List of strings to be processed.

---

**[NoYY.java]** Given a list of strings, return a list where each string has "y" added at its end, omitting any
resulting strings that contain "yy" as a substring anywhere.

Examples:

* noYY(["a", "b", "c"]) → ["ay", "by", "cy"]
* noYY(["a", "b", "cy"]) → ["ay", "by"]
* noYY(["xx", "ya", "zz"]) → ["xxy", "yay", "zzy"]

The method has the following prototype:

```
public static List<String> noYY(List<String> strings);
```

where:

* **strings** is the List of strings to be processed.

---

**[NoYYZ.java]** Given a list of strings, return a list where each string has "y" added at its end, omitting any
resulting strings that contain "yy" as a substring anywhere. 
Nevertheless, each string starting with "z" has to printed to standard output. (see Stream.peek())

Examples:

* noYYZ(["a", "b", "c"]) → ["ay", "by", "cy"]
* noYYZ(["a", "b", "cy"]) → ["ay", "by"]
* noYYZ(["xx", "ya", "zz"]) → ["xxy", "yay", "zzy"] (prints "zzy")

The method has the following prototype:

```
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

```
public static long howManyLines(String filename, String subString);
public static long howManyTimes(String filename, String word);
```

where:

* **filename** is the filename of the book.
* **word** is the word of interest.

For reading the file and transforming it into a ```List<String>```, you can use:

```
public static List<String> getLines(String filename) throws IOException {
    return Files.readAllLines(Path.of(filename));
}
```

For splitting a line into a list of single words, you can use:

```
public static List<String> lineToWords(String line) {
    return Arrays.asList(line.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+"));
}
```

---

**[ToString.java]** Write a method turning a Stream<T> into a comma-separated list of its first n elements (see Collectors.joining())

The method has the following prototype:

```
public static <T> String toString(Stream<T> stream, int n);
```

where:

* **stream** is the stream to be processed.
* **n** is the number of elements to be included.

---

**[BankAccount.java]** Given the following class Account:

```
public static class Account {
    double amount;
    double interestRate;
    LocalDateTime duePayment;
    ...
}
```

Write a method for: 
(a) filtering all the accounts whose duePayment is expired, 
(b) apply interests (amount += amount * interestRate),
(c) sort them based their on amount,
(d) return a List of the filtered accounts.

The method has the following prototype:

```
public static List<Account> applyInterest(List<Account> accounts);
```

where:

* **accounts** is the list of Account to be processed.

---
