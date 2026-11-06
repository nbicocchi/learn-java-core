# E5 — Exceptions

**Java 25 · Practice without AI-generated code**

Implement the exercises yourself and validate your work with the supplied tests where available. The sections follow the order of the covered course topics; exercises may combine earlier skills.

[Course slides and module index](../../../../../../../../../README.md#slides) · [AI exercises](../../../../../../../../exercises-ai/E5%20-%20Java%20Exceptions.md)

## Contents

- [Exceptions and contracts](#exceptions-and-contracts)
- [Propagation and resource ownership](#propagation-and-resource-ownership)

## Running tests

From `exercises/exercises-no-ai/`:

```sh
mvn test
mvn -Dtest="com.nbicocchi.exercises.e5_exceptions.**" test
```

## Exceptions and contracts

### Exercise 1 — Division

Source: [Division.java](Division.java). Tests: [DivisionTest.java](../../../../../../test/java/com/nbicocchi/exercises/e5_exceptions/DivisionTest.java).

Write a method receiving two doubles (a, b) and returning their division. If b == 0, the method
throws IllegalArgumentException. *Note well: IllegalArgumentException is an unchecked exception so its declaration in
the method signature is not required!* The method has the following prototype:

```java
public static double division(double a, double b);
```

where:

* **a** is the dividend.
* **b** is the divisor.

---

### Exercise 2 — DivisionSquared

Source: [DivisionSquared.java](DivisionSquared.java). Tests: [DivisionSquaredTest.java](../../../../../../test/java/com/nbicocchi/exercises/e5_exceptions/DivisionSquaredTest.java).

Write a method receiving two integers (a, b) and returning the square of their integer quotient: compute `q = a / b`, then `q * q`. In Java, `^` means bitwise XOR, not exponentiation. If b == 0, return 0 instead of propagating ArithmeticException. Do not use an if statement. The method has the following prototype:

```java
public static int divisionSquared(int a, int b);
```

where:

* **a** is the dividend.
* **b** is the divisor.

---

### Exercise 3 — CheckIntervalBetweenDates

Source: [CheckIntervalBetweenDates.java](CheckIntervalBetweenDates.java). Tests: [CheckIntervalBetweenDatesTest.java](../../../../../../test/java/com/nbicocchi/exercises/e5_exceptions/CheckIntervalBetweenDatesTest.java).

Write a method receiving two strings representing dates (yyyy-MM-dd) and
returning true if the first date is before the second date (see java.time.LocalDate).
Let parsing exceptions propagate to the caller. LocalDate.parse() throws the unchecked DateTimeParseException for invalid date text, so no throws clause is required. The method has the following prototype:

```java
public static boolean checkIntervalBetweenDates(String begin, String end);
```

where:

* **begin** is the string representing the first date (yyyy-MM-dd format).
* **end** is the string representing the second date (yyyy-MM-dd format).

---

### Exercise 4 — CheckIntervalBetweenDatesAlt

Source: [CheckIntervalBetweenDatesAlt.java](CheckIntervalBetweenDatesAlt.java). Tests: [CheckIntervalBetweenDatesAltTest.java](../../../../../../test/java/com/nbicocchi/exercises/e5_exceptions/CheckIntervalBetweenDatesAltTest.java).

Write a method receiving two strings representing dates (yyyy-MM-dd) and
returning true if the first date is before the second date (see java.time.LocalDate).
Catch DateTimeParseException locally and return false for invalid date text. The method has the following prototype:

```java
public static boolean checkIntervalBetweenDatesAlt(String begin, String end);
```

where:

* **begin** is the string representing the first date (yyyy-MM-dd format).
* **end** is the string representing the second date (yyyy-MM-dd format).

---

### Exercise 5 — CheckLicencePlate

Source: [CheckLicencePlate.java](CheckLicencePlate.java). Tests: [CheckLicencePlateTest.java](../../../../../../test/java/com/nbicocchi/exercises/e5_exceptions/CheckLicencePlateTest.java).

Write a method checking a simplified Italian licence-plate format: LLDDDLL, where L is a letter and D is a digit. Only this seven-character structure is checked; actual registration rules are outside the exercise. Throw IllegalArgumentException if the format is invalid (see Character.isDigit() and Character.isLetter()).

```java
public static void checkLicencePlate(String licence);
```

where:

* **licence** is the licence plate to be verified.

---

### Exercise 6 — CheckString

Source: [CheckString.java](CheckString.java). Tests: [CheckStringTest.java](../../../../../../test/java/com/nbicocchi/exercises/e5_exceptions/CheckStringTest.java).

Write a method checking that a string consists of one or more letter–digit pairs, for example a0b3h4z1r4. It must start with a letter, end with a digit, and contain no other characters. Throw `java.text.ParseException` for invalid input (see Character.isDigit() and Character.isLetter()). The method has the following prototype:

```java
public static void checkString(String s) throws ParseException;
```

where:

* **s** is the string to be verified.

---

### Exercise 7 — FilterItems

Source: [FilterItems.java](FilterItems.java). Tests: [FilterItemsTest.java](../../../../../../test/java/com/nbicocchi/exercises/e5_exceptions/FilterItemsTest.java).

Using checkString(), write a method filtering a `List<String>`. Return a new `List<String>` containing only valid sequences of letter–digit pairs (for example a0b3h4z1r4). Do not modify the original list. The method has the following prototype:

```java
public static List<String> filterItems(List<String> strings);
```

where:

* **strings** is the List to be processed

---

### Exercise 8 — FilterLists

Source: [FilterLists.java](FilterLists.java). Tests: [FilterListsTest.java](../../../../../../test/java/com/nbicocchi/exercises/e5_exceptions/FilterListsTest.java).

Using checkString(), write a method filtering a `List<List<String>>`. Return a new outer list containing only the inner lists whose strings are all valid sequences of letter–digit pairs (for example a0b3h4z1r4). Do not modify the input lists. The method has the following prototype:

```java
public static List<List<String>> filterLists(List<List<String>> lists);
```

where:

* **lists** is the list of lists of strings to be processed

---

## Propagation and resource ownership

For the three ReadLine exercises, return null if the file is empty. Close the reader with try-with-resources in every version; only the handling of IOException differs.

### Exercise 9 — ReadLineCompleteDelegation

Source: [ReadLineCompleteDelegation.java](ReadLineCompleteDelegation.java). Tests: [ReadLineCompleteDelegationTest.java](../../../../../../test/java/com/nbicocchi/exercises/e5_exceptions/ReadLineCompleteDelegationTest.java).

Write a method for reading the first line of a given file (see
java.io.BufferedReader class). In case of errors (IOException), the method applies a complete delegation model. Use the syntax try-with-resources for guaranteeing the proper closing of the file under any circumstance. The method has the following prototype:

```java
public static String readLineCompleteDelegation(String filename) throws IOException;
```

where:

* **filename** is the name of the file to read.

---

### Exercise 10 — ReadLinePartialDelegation

Source: [ReadLinePartialDelegation.java](ReadLinePartialDelegation.java). Tests: [ReadLinePartialDelegationTest.java](../../../../../../test/java/com/nbicocchi/exercises/e5_exceptions/ReadLinePartialDelegationTest.java).

Write a method reading the first line of a file with java.io.BufferedReader. Catch IOException and wrap it in `java.io.UncheckedIOException` (a RuntimeException subclass), preserving the original exception as the cause. The method has the following prototype:

```java
public static String readLinePartialDelegation(String filename);
```

where:

* **filename** is the name of the file to read.

---

### Exercise 11 — ReadLineNoDelegation

Source: [ReadLineNoDelegation.java](ReadLineNoDelegation.java). Tests: [ReadLineNoDelegationTest.java](../../../../../../test/java/com/nbicocchi/exercises/e5_exceptions/ReadLineNoDelegationTest.java).

Write a method reading the first line of a given file (see java.io.BufferedReader
class). In case of errors (IOException), the method applies a no-delegation model and returns null. The method has the following prototype:

```java
public static String readLineNoDelegation(String filename);
```

where:

* **filename** is the name of the file to read.

---

### Exercise 12 — RetryWrite

Source: [RetryWrite.java](RetryWrite.java). No automated tests are currently supplied for this exercise.

The following RetryWrite class simulates writing a message over a network connection, with a 66% probability of failure per call. Complete writeWithTries() to attempt `write("Hello world!")` up to maxTries times in total, including the first attempt. Assume maxTries is positive. Return immediately after a successful write; if every attempt fails, throw a RuntimeException to the caller.

```java
import java.io.IOException;
import java.util.random.RandomGenerator;

public class RetryWrite {
    private static final RandomGenerator RND = RandomGenerator.getDefault();

    public static void main(String[] args) {
        try {
            writeWithTries(3);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Attempts write() up to maxTries times, stopping at the first success.
     * @param maxTries the maximum number of attempts, including the first
     */
    public static void writeWithTries(int maxTries) {
        // to be completed
    }

    /**
     * Simulates a write with 66% probability of failing
     *
     * @param message the message to write
     * @throws IOException when write fails
     */
    public static void write(String message) throws IOException {
        if (RND.nextDouble() < 0.66) {
            throw new IOException("Unable to send: " + message);
        }
    }
}
```

---
