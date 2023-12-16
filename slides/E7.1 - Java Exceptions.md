# Exceptions

Exceptions are the errors detected during the program **execution** (at runtime), whereas syntax errors are those detected during **compiling** the program into byte-code. An exception interrupts the normal execution of a program.

### Why exceptions?
Imagine we need to write a method for loading a file in memory:

* First approach (ignore error checking)
  * Short 
  * Readable 
  * **Not reusable**
  * **Not dependable**

```
void loadFile()  {
  open file;
  determine file size;
  allocate memory;
  read file into memory;
  close file;
}

```

* Second approach (error checking with [magic numbers](https://en.wikipedia.org/wiki/Magic_number_(programming)))
  * **Long**
  * **Obscure**
  * Reusable
  * Dependable

```
void loadFile()  {
  open file;
  if (operationFailed) 
    return -1;
  
  determine file size; 
  if (operationFailed) 
    return -2;
  
  allocate memory; 
  if (operationFailed)
    return -3;
  
  read file into memory; 
  if (operationFailed)
    return -4;
  
  close file;
  if (operationFailed) 
    return -5;
}
```

* Third approach (error checking with exceptions)
  * Reusable
  * Dependable
  * Readable

```
void loadFile() {
  try {
      open file;
      determine file size;
      allocate memory; 
      read file into memory; 
      close file;
  } catch (fileOpenFailed) { 
      doSomething; 
  } catch(determineSizeFailed) { 
      doSomething; 
  } catch (memoryAllocationFailed) { 
      doSomething; 
  } catch (readFailed) { 
      doSomething; 
  } catch (fileCloseFailed) { 
      doSomething; 
  }
}
```

### ArithmeticException
Suppose you are writing a program that reads two integers from the standard input and then outputs the result of the integer division of the first number by the second one. Look at the code below.

```
import java.util.Scanner;

public class ArithmeticExceptionDemo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int a = scanner.nextInt();
        int b = scanner.nextInt();

        System.out.println(a / b); // an exception is possible here!
        System.out.println("finished");
    }
}
```

If the user passes `9` and `3` as the input values, the program outputs `3` as well as the `"finished"` string. But if the second number is `0`, the program throws an exception because of the division by zero.

```
Exception in thread "main" java.lang.ArithmeticException: / by zero
  at ArithmeticExceptionDemo.main(ArithmeticExceptionDemo.java:11)
```

As you can see, the program fails with an `ArithmeticException`, and the `"finished"` string is not printed at all. All the code **before** the exception is executed properly, and everything **after** is not.

The displayed message shows the cause of the exception (`"/ by zero"`), the file and the line number where it has occurred (`ArithmeticExceptionDemo.java:11`). The provided information is useful for developers, but it is not very meaningful for the end-users of the program.

To avoid the exception, we can check the value before the division, and, if the value is zero, print a message. Here is another version of the program. If the second number is zero, the program will print the "**Division by zero!**" string.

```
import java.util.Scanner;

public class ArithmeticExceptionDemo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int a = scanner.nextInt();
        int b = scanner.nextInt();

        if (b == 0) {
            System.out.println("Division by zero!");
        } else {
            System.out.println(a / b);
        }
        System.out.println("finished");
    }
}
```

### NumberFormatException
Another situation to consider is when you are trying to convert a string into an integer number. If the string has an unsuitable format, the code will throw an exception.

The following program reads a line from the standard input and then outputs the number that follows it.

```
import java.util.Scanner;

public class NumberFormatExceptionDemo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        int number = Integer.parseInt(input); // an exception is possible here!
        System.out.println(number + 1);
    }
}
```

It works pretty well if the input line is a correct integer number. But if the input is not correct (e.g. `"121a"`), the program will fail:

```
Exception in thread "main" java.lang.NumberFormatException: For input string: "121a"
  at java.base/java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)
  at java.base/java.lang.Integer.parseInt(Integer.java:652)
  at java.base/java.lang.Integer.parseInt(Integer.java:770)
  at NumberFormatExceptionDemo.main(NumberFormatExceptionDemo.java:9)
```

To avoid this exception, it is possible to check the input string by using a regular expression. In case the input is not correct, we can output a warning message. The following program does this:

```
import java.util.Scanner;

public class NumberFormatExceptionDemo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if (input.matches("\\d+")) { // it checks if the input line contains only digits
            int number = Integer.parseInt(input);
            System.out.println(number + 1);
        } else {
            System.out.println("Incorrect number: " + input);
        }
    }
}
```

### ArrayIndexOutOfBoundsException
This is a fairly common exception that occurs while working with arrays. It is caused by attempting to access a non-existent element of the array.

```
int[] array = { 1, 2, 3 }; // an array of ints

int n1 = array[2]; // n1 is 3
int n2 = array[3]; // Exception
```

In this code, the last line produces an `ArrayIndexOutOfBoundsException` since the last index of the array in question is 2. The code will throw the same exception if we try to access an element with a negative index:

```
array[0];  // OK
array[-1]; // Exception
```

Since a string can be considered as a sequence of characters, a similar exception may occur when accessing a non-existing element of a string. It is called `StringIndexOutOfBoundsException`.

To avoid the `ArrayIndexOutOfBoundsException`, we may check if the given index belongs to the interval **[0, length - 1].**

For example, let's take a look at a program, displaying an element of the array by the index, provided in the input. If the index is out of bounds, the program prints a message instead of throwing an exception.

```
public class NoIndexOutOfBoundsExceptions {

    public static void main(String[] args) {
        int[] hardCodedArray = { 3, 2, 4, 5, 1 };

        Scanner scanner = new Scanner(System.in);

        int index = scanner.nextInt();

        if (index < 0 || index > hardCodedArray.length - 1) {
            System.out.println("The index is out of bounds.");
        } else {
            System.out.println(hardCodedArray[index]);
        }
    }
}
```

### NullPointerException (NPE)
Java provides a special value called `null` to indicate that no actual value is assigned to a reference variable. This value may cause one of the most frequent exceptions called `NullPointerException`. It occurs when a program attempts to use a variable with the `null` value. To avoid an **NPE**, the programmer must ensure that the objects are initialized before their use. 

In 2009, Tony Hoare, a British Computer Scientist who invented the concept of `null` reference, described it as a **"billion-dollar mistake"**:


### NPE when invoking a method
Since `String` is a regular reference type, its variables can be `null`. If we invoke a method or apply an operation to such a variable, the code throws an **NPE**.

In the following code, an uninitialized variable of `String` is created and then the method `length()` is invoked. The code throws an **NPE** because the object `someString` is actually `null`.

```
String someString = null; // a reference type can be null

int size = someString.length(); // NullPointerException (NPE)
```

The same exception will occur if we use uninitialized variables of any other reference type, not only `String`. To avoid the exception we should explicitly check whether a string is `null` or not and depending on the result perform different code. It's similar to the default value.


### Comparing strings
A very common situation occurs when we try to compare a `String` variable and a string literal.

```
String str = null;

if (str.equals("abc")) { // it throws an NPE
    System.out.println("The same");
}
```

To avoid an **NPE** here we can use Yoda notation and call the equals method on the literal rather than the object:

```
String str = null;

if ("abc".equals(str)) { // no NPE here
    System.out.println("The same");
}
```

But what if we have two variables of the type `String`? Any of them may happen to be `null`. In this case, we can use the special auxiliary class `java.util.Objects`.

```
String s1 = null;
String s2 = null;

if (Objects.equals(s1, s2)) { // no NPE here
    System.out.println("Strings are the same");
}
```

This approach is recommended in modern Java programming since it is easy for reading and does not throw an **NPE**.

### Rules for avoiding NPE
Here are several general rules on how to avoid an **NPE** in your programs:

-   for reference types, use a conditional statement to check whether the given variable is `null` before using it;

-   try to avoid assigning `null` to variables whenever possible;

-   use NPE-safe features from the standard library.

These simple rules will help to reduce the number of places in your code that could throw this exception.

### Hierarchy of exceptions
Exceptions are considered objects of special classes organized into a class hierarchy. The following picture illustrates the simplified hierarchy of exceptions:

![Hierarchy of exceptions](images/exceptions-hierarchy.svg)

The base class for all exceptions is `java.lang.Throwable`. This class provides a set of common methods for all exceptions:

- `String getMessage()` returns the detailed string message of this exception object;
- `Throwable getCause()` returns the cause of this exception or `null` if the cause is nonexistent or unknown;
- `printStackTrace()` prints the stack trace on the standard error stream.

The `Throwable` class has two direct subclasses: `java.lang.Error` and `java.lang.Exception`.

- subclasses of the `Error` class represent low-level events in the JVM, for example: `OutOfMemoryError`, `StackOverflowError`;
- subclasses of the `Exception` class deal with events inside applications, such as: `RuntimeException`, `IOException`;
- the `RuntimeException` class is a rather special subclass of `Exception`. It represents so-called **unchecked** exceptions, including: `ArithmeticException`, `NumberFormatException`, `NullPointerException`.


### Checked exceptions
**Checked exceptions** are represented by the `Exception` class, excluding the `RuntimeException` subclass. The compiler checks whether the programmer expects the occurrence of such exceptions in a program or not.

If a method throws a checked exception, this must be marked in the declaration using the special `throws` keyword. Otherwise, the program will not compile.

```
public static String readLineFromFile() throws FileNotFoundException {
    Scanner scanner = new Scanner(new File("file.txt")); // java.io.FileNotFoundException
    return scanner.nextLine();
}
```

Here, `FileNotFoundException` is a standard checked exception. This constructor of `Scanner` declares a `FileNotFoundException` exception, because we assume that the specified file may not exist. Most importantly, there is a single line in the method that may throw an exception, so we put the `throws` keyword in the method declaration.

### Unchecked exceptions
**Unchecked exceptions** are represented by the `RuntimeException` class and all its subclasses. The compiler does not check whether the programmer expects the occurrence of such exceptions in a program.

```
public static Long convertStringToLong(String str) {
    return Long.parseLong(str); // It may throw a NumberFormatException
}
```

This code always successfully compiles without the `throws` keyword in the declaration.

Runtime exceptions may occur anywhere in a program. The compiler doesn't require that you specify runtime exceptions in declarations. Adding them to each method's declaration would reduce the clarity of a program.

The `Error` class and its subclasses are also considered as unchecked exceptions. 

### The throw keyword
Any object of the  `Throwable`  class and all its subclasses can be thrown using the **throw** statement. The general form of the statement consists of the `throw`  keyword and an object to be thrown.

```
public class Main {
    public static void main(String args[]) {
        throw new RuntimeException("Something's bad.");
    }
}
```

The program stops and prints the error with the message we provided:

```
Exception in thread "main" java.lang.RuntimeException: Something's bad.
	at Main.main(Main.java:3)
```

It is only possible to throw an object of the  `Throwable`  class or a class that extends `Throwable`. For example, the line `throw new Long(10L); does not compile.

### Throwing checked exceptions
For example, let's take a look at the following method that reads text from a file. In case the file is not found, the method throws an `IOException`:

```
public static String readTextFromFile(String path) throws IOException {
    // find a file by the specified path

    if (!found) {
        throw new IOException("The file " + path + " is not found");
    }

    // read and return text from the file
}
```

Here we can only see a part of the method. The `throws` keyword following the method parameters is required since an  `IOException` is a checked exception.

If a method throws a checked exception, the type of exception must be specified in the method declaration after the  `throws` keyword. Otherwise, the code won't compile.

If a method throws two or more checked exceptions, they must be separated by a comma in the declaration:

```
public static void method() throws ExceptionType1, ExceptionType2, ExceptionType3
```

If a method is declared as throwing an exception (i.e.  `BaseExceptionType`), it can also throw any subclass of the specified exception (i.e.  `SubClassExceptionType`):

```
public static void method() throws BaseExceptionType
```

### Throwing unchecked exceptions
The  `Account`  class contains the method called  `deposit`, that adds the specified amount to the current balance. If the amount is not positive or exceeds the limit, the method throws an `IllegalArgumentException`.

```
class Account {

    private long balance = 0;

    public void deposit(long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Incorrect sum " + amount);
        }

        if (amount >= 100_000_000L) {
            throw new IllegalArgumentException("Too large amount");
        }

        balance += amount;
    }

    public long getBalance() {
        return balance;
    }
}
```

The  `deposit`  method is not declared as throwing an  `IllegalArgumentException`. The same is true for all other unchecked exceptions. If a method throws an unchecked exception, the keyword `throws` is not required in the method declaration (but you still have to use`throw`!)

### When to throw an exception?
Technically, throwing an exception is a rather straightforward task. But the question is, when do you need to do this? The answer is not always obvious.

The common practice is to throw an exception when and only when the method preconditions are broken, that is when it cannot be performed under the current conditions.

Imagine a method that parses the input string in the dd-MM-yyyy format to get a month. Here, if the string is invalid, the method throws an  `InvalidArgumentException`. Another example is reading a non-existing file that will lead to a  `FileNotFoundException`.

It is recommended to throw exceptions that are most relevant (specific) to the problem: it is better to throw an object of `InvalidArgumentException`  than the base `Exception`  class.

### How to handle an exception
After a line of code throws an exception, the Java runtime system attempts to find a suitable handler for it. Such a handler can be located in the same method where the exception occurred or in the calling methods (up to the main method). As soon as a suitable handler is found and executed, the exception is considered as handled and the program runs normally.

Technically, an exception can be handled in the method where it occurs or in the calling method. The best approach to handle an exception is to do it in a method that has sufficient information to make the correct decision based on this exception.

### The try-catch statement
Here is a simple `try-catch` template for handling exceptions:

```
try {
    // code that may throw an exception
} catch (Exception e) {
    // code for handling the exception
}
```

The `try` block is used to wrap the code that may throw an exception. This block can include all lines of code, including method calls.

The `catch` block is a handler for the specified type of exception and all of its subclasses. This block is executed when an exception of the corresponding type occurs in the `try` block. Note that the specified type in a `catch` block must extend the `Throwable` class. The `catch` block can handle exceptions of the `Exception` class and all classes derived from it.

```
System.out.println("before the try-catch block"); // it will be printed

try {
    System.out.println("inside the try block before an exception"); // it will be printed

    System.out.println(2 / 0); // it throws ArithmeticException

    System.out.println("inside the try block after the exception"); // it won't be printed
} catch (Exception e) {
    System.out.println("Division by zero!"); // it will be printed
}

System.out.println("after the try-catch block"); // it will be printed
```

The output:

```
before the try-catch block
inside the try block before an exception
Division by zero!
after the try-catch block
```

The program does not print `"inside the try block after the exception"` since the `ArithmeticException` aborted the normal flow of the execution. Instead, it executes the print statement in the `catch` block. After completion of the `catch` block, the program executes the next statement (printing `"after the try-catch block"`) without returning to the `try` block again.

Replacing `Exception` with `ArithmeticException` or `RuntimeException` in the `catch` statement does not change the execution flow of the program. But replacing it with `NumberFormatException` will make the handler unsuitable for the exception and the program will fail.

As we noted earlier, the `try-catch` statement can handle both checked and unchecked exceptions. But there is a difference: checked exceptions must be wrapped with a `try-catch` block or declared to be thrown in the method, while unchecked exceptions don't have to.


### Catching multiple exceptions
It is always possible to use a single handler for all types of exceptions:

```
try {
    // code that may throw exceptions
} catch (Exception e) {
    System.out.println("Something goes wrong");
}
```

Obviously, this approach does not allow us to perform different actions depending on the type of exception that has occurred. Fortunately, Java supports the use of several handlers inside the same `try` block.

```
try {
    // code that throws exceptions
} catch (IOException e) {
    // handling the IOException and its subclasses
} catch (Exception e) {
    // handling the Exception and its subclasses
}
```

When an exception occurs in the `try` block, the runtime system determines the first suitable `catch` block according to the type of the exception. Matching goes from top to bottom.

Important, the `catch` block with the base class has to be written below all blocks with subclasses. In other words, the more specialized handlers (like `IOException`) must be written before the more general ones (like `Exception`). Otherwise, the code won't compile.

Since Java 7, you can use a **multi-catch** syntax to have several exceptions handled in the same way:

```
try {
    // code that may throw exceptions
} catch (SQLException | IOException e) {
    // handling SQLException, IOException and their subclasses
    System.out.println(e.getMessage());
} catch (Exception e) {
    // handling any other exceptions
    System.out.println("Something goes wrong");
}
```

In the code above `SQLException` and `IOException` (alternatives) are separated by the `|` character. They will be handled in the same way. Note that alternatives in a multi-catch statement cannot be each other's subclasses.

### The finally keyword
There is another possible block called `finally`. All statements present in this block will always execute regardless of whether an exception occurs in the `try` block or not.

```
try {
    // code that may throw an exception
} catch (Exception e) {
    // exception handler
} finally {
    // code that will always be executed
}
```

The following example illustrates the order of execution of the `try-catch-finally` statement.

```
try {
    System.out.println("inside the try block");
    Integer.parseInt("101abc"); // throws a NumberFormatException
} catch (Exception e) {
    System.out.println("inside the catch block");
} finally {
    System.out.println("inside the finally block");
}

System.out.println("after the try-catch-finally block");
```

The output:

```
inside the try block
inside the catch block
inside the finally block
after the try-catch-finally block
```

If we remove the line that throws a `NumberFormatException`, the `finally` block is still executed after the `try` block.

```
inside the try block
inside the finally block
after the try-catch-finally block
```

### How to create and throw a custom exception
To create a custom exception you need to extend the `Exception` (checked) or `RuntimeException` (unchecked) classes.

Here is an example:

```
public class MyAppException extends Exception {

    public MyAppException(String msg) {
        super(msg);
    }

    public MyAppException(Exception cause) {
        super(cause);
    }
}
```

In the example above, a new class of exceptions is declared. It is a checked exception because it extends the `Exception` class. The declared class has two constructors for creating instances, and they call the corresponding constructor of the base class.

Now, we can throw an instance of the class:

```
public static void someMethod() throws MyAppException {
    throw new MyAppException("Something bad");
}
```

### Best practices for custom exceptions
* Make sure that your application will benefit from creating a custom exception. Otherwise, use standard Java exceptions. 
* Follow the naming convention e.g. end the class name with "Exception", for example `MyAppException`
* In general, it is advisable to use standard exceptions whenever possible for a number of reasons, such as:
  - Standard exceptions are widely known by other programmers. One can understand the type of problem just by looking at the name of the exception. 
  - By opting for standard exceptions, you follow the reusability principle. It makes your code clearer and more professional.

### Why close resources ?
When an input stream is created, the JVM notifies the OS about its intention to work with a file. If the JVM process has enough permissions and everything is fine, the OS returns a **file descriptor**. The problem is that the number of file descriptors is limited. This is a reason why it is important to notify the OS that the job is done and the file descriptor that is held can be released for further reusing. 

Resource releasing works if the JVM calls the `close` method, but it is possible that the method will not be called at all. Look at the example:

```
Reader reader = new FileReader("file.txt");
// code which may throw an exception
reader.close();
```

Suppose something goes wrong before the `close` invocation and an exception is thrown. It leads to a situation in which the method will never be called and system resources won't be released. It is possible to solve the problem by using the **try-catch-finally** construction:

```
Reader reader = null;

try {
    reader = new FileReader("file.txt");
    // code which may throw an exception
} finally {
    reader.close();
}
```

In this and the following examples, we assume that `file.txt` exists and do not check the instance of `Reader` for `null` in the `finally` block. It is done to keep the code snippet as simple as possible, but it is not safe in the case of a real application.

### try-with-resources
A more elegant way for avoiding [resource leaks](https://en.wikipedia.org/wiki/Resource_leak) is called **try-with-resources** and was introduced in Java 7.

```
try (Reader reader = new FileReader("file.txt")) {
    // some code
}
```

This construction has two parts enclosed by round and curly brackets. Round brackets contain statements that create an input stream instance. It is possible to create several objects as well. The code below is also fine:

```
try (Reader reader1 = new FileReader("file1.txt");
     Reader reader2 = new FileReader("file2.txt")) {
    // some code
}
```

The second part just contains some code for dealing with the object that was created in the first part.

As you see, there are no explicit calls of the `close` method at all. It is implicitly invoked for all objects declared in the first part. The construction guarantees closing all resources in a proper way.

Surely we do our best to write error-free programs. However, it is difficult to foresee all possible problems. The best practice is to wrap any code dealing with system resources by the try-with-resources construction

You may also use try-with-resources as a part of try-catch-finally like this:

```
try (Reader reader = new FileReader("file.txt")) {
    // some code
} catch(IOException e) {
    ...
} finally {
    ...
}
```

### Closeable resources
We have dealt with a file input stream to demonstrate how try-with-resources is used. However, not only resources based on files should be released. Closing is crucial for other outside sources like web or database connections. Classes that handle them have a `close` method and therefore can be wrapped by the try-with-resources statement.

For example, let's consider `java.util.Scanner`. Earlier we used `Scanner` for reading data from the standard input, but it can read data from a file as well. `Scanner` has a `close` method for releasing outside sources.

Let's consider an example of a program that reads two integers separated by a space from a file and prints them:

```
try (Scanner scanner = new Scanner(new File("file.txt"))) {
    int first = scanner.nextInt();
    int second = scanner.nextInt();
    System.out.println("arguments: " + first + " " + second);
}
```

Suppose something went wrong and the file content is `123 not_number`, where the second argument is a `String`. It leads to a `java.util.InputMismatchException` while parsing the second argument. Try-with-resources guarantees that file-related resources are released properly.

### Exceptions and loops
For errors affecting a single iteration (or items of a collection!), the try-catch blocks is nested in the loop. In case of exception the execution goes to the catch block and then proceed with the next iteration.

```
while(something){
  try {
    // potential    exceptions
  } catch(Exception e){
    //  discard iteration
  }
}
```

For errors compromising the whole loop, the loop is nested within the try block. In case of exception the execution goes to the catch block, thus exiting the loop.

```
try{
  while(something) {
    //  potential exceptions
  }
} catch(Exception e){
  // discard whole loop
}
```


