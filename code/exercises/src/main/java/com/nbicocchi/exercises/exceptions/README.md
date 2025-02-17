# Object-Oriented Programming - DIEF/UNIMORE

## Java Exercises (Exceptions)

**[Division.java]** Write a method receiving two doubles (a, b) and returning their division. If b == 0, the method
throws IllegalArgumentException. *Note well: IllegalArgumentException is an unchecked exception so its declaration in
the method signature is not required!* The method has the following prototype:

```java
public static double division(double a, double b);
```

where:

* **a** is the dividend.
* **b** is the divisor.

---

**[DivisionSquared.java]** Write a method receiving two integers (a, b) and returning their integer division squared (
i.e., (a / b) ^ 2). If b == 0, the method returns 0, instead of an exception. The implementation of this method must
avoid the use of the *if* statement. The method has the following prototype:

```java
public static int divisionSquared(int a, int b);
```

where:

* **a** is the dividend.
* **b** is the divisor.

---

**[CheckIntervalBetweenDates.java]** Write a method receiving two strings representing dates (i.e., yyyy-mm-dd) and
returning true if the first date is before the second date (see java.time.LocalDate).
Eventual exceptions must be delegated to the caller (Remember: not all exceptions are checked). The method has the following prototype:

```java
public static boolean checkIntervalBetweenDates(String begin, String end);
```

where:

* **begin** is the string representing the first date (yyyy-mm-dd format).
* **end** is the string representing the second date (yyyy-mm-dd format).

---

**[CheckIntervalBetweenDatesAlt.java]** Write a method receiving two strings representing dates (i.e., yyyy-mm-dd) and
returning true if the first date is before the second date (see java.time.LocalDate).
Eventual exceptions must be locally intercepted and false is returned in case of errors. The method has the following
prototype:

```java
public static boolean checkIntervalBetweenDatesAlt(String begin, String end);
```

where:

* **begin** is the string representing the first date (yyyy-mm-dd format).
* **end** is the string representing the second date (yyyy-mm-dd format).

---

**[CheckLicencePlate.java]** Write a method to check if a string representing a car licence plate respects 
the italian format (i.e., LLDDDLL where L represents a letter while D a digit). If the string does not respect the
format, the method throws IllegalArgumentException (see Character.isDigit() and Character.isLetter() methods).

```java
public static void checkLicencePlate(String licence);
```

where:

* **licence** is the licence plate to be verified.

---

**[CheckString.java]** Write a method to check if a string is an alternating sequence of letters and numbers (for
example, a0b3h4z1r4). The method delegates ParseException for notifying the caller about eventual malformations (see
Character.isDigit() and Character.isLetter() methods). The method has the following prototype:

```java
public static void checkString(String s) throws ParseException;
```

where:

* **s** is the string to be verified.

---

**[FilterItems.java]** Using the checkString() method, write another method for filtering a List<String>. The method
returns a different List<String> comprising only those strings which are an alternating sequence of letters and numbers (for example a0b3h4z1r4). The original list is not modified. The method has the following prototype:

```java
public static List<String> filterItems(List<String> strings);
```

where:

* **strings** is the List to be processed

---

**[FilterLists.java]** Using the checkString() method, write another method for filtering a List<List<String>> (a list
of lists of strings). The method returns a List<List<String>> comprising only those List<String> containing ONLY
alternating sequences of letters and numbers (for example a0b3h4z1r4). The original List should not be modified. The
method has the following prototype:

```java
public static List<List<String>> filterLists(List<List<String>> lists);
```

where:

* **lists** is the list of lists of strings to be processed

---

**[ReadLineCompleteDelegation.java]** Write a method for reading the first line of a given file (see
java.io.BufferedReader class). In case of errors (IOException), the method applies a complete delegation model. Use the syntax try-with-resources for guaranteeing the proper closing of the file under any circumstance. The method has the following prototype:

```java
public static String readLineCompleteDelegation(String filename) throws IOException;
```

where:

* **filename** filename of the file to read from.

---

**[ReadLinePartialDelegation.java]** Write a method for reading the first line of a given file (see
java.io.BufferedReader class). In case of errors (IOException), the method applies a partial delegation model throwing an unchecked exception (RunTimeException) instead of IOException. The method has the following prototype:

```java
public static String readLinePartialDelegation(String filename);
```

where:

* **filename** filename of the file to read from.

---

**[ReadLineNoDelegation.java]** Write a method reading the first line of a given file (see java.io.BufferedReader
class). In case of errors (IOException), the method applies a no-delegation model and returns null. The method has the following prototype:

```java
public static String readLineNoDelegation(String filename);
```

where:

* **filename** filename of the file to read from.

---

**[RetryWrite.java]** The following RetryWrite class contains a *write()* method for writing a message over a network connection which might fail (in this example it fails 66% of the time). Because we know network connections sometimes fail, we do not want to call it directly. Instead, we use an intermediate *writeWithTries()* which, if *write()* fails, calls it again until the specified number of retries (maxTries) has been reached. The *main()* method receives an exception only if all retries fail. Complete the *writeWithTries()* method to make the code below work.

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
     * Calls the write() method. If it fails, retries the call maxTries times
     * @param maxTries the maximum number of retries
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