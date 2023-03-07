Lambda expressions
------------------

### Functional programming in Java

In Java, methods are the main way to represent the behavior of objects, classes, and whole programs. You can write any code inside their bodies and then invoke this code from other parts of your program using the names of the methods. This approach allows developers to create structured and readable programs, but sometimes it's not enough.

Like methods, functions are used to decompose code into small pieces. Sometimes these concepts are even interchangeable. However, unlike methods, functions can also behave like regular Java objects (e.g., be passed to/returned from a method).

### Lambda expressions

By **lambda expression** (or just "a lambda"), we mean a **function** that isn't bound to its name (an anonymous function) but can be assigned to a variable.

The most general form of a lambda expression looks like this: `(parameters) -> { body };`. Here, the part before `->` is the list of parameters (like in methods), and the part after that is the body that can return a value. The brackets `{ }` are required only for multi-line lambda expressions.

Sometimes, lambdas don't have parameters or return values or even both. Even if a lambda doesn't have a value to return, it has a body that does some useful actions (e.g. prints or saves something).

Like a regular Java object, a lambda expression has a type. There are a lot of types presented in the Java Standard Library. In this topic, we will only mention two of them: `Function` and `BiFunction`. Both of the classes are located in the `java.util.function`.

Let's consider a single-line lambda expression that just checks whether the first number is divisible by the second one.

```
BiFunction<Integer, Integer, Boolean> isDivisible = (x, y) -> x % y == 0;
```

The expression has the type `BiFunction<Integer, Integer, Boolean>` which means, that it takes two `Integer` values and returns a `Boolean` value.

There are a lot of ways to write lambda expressions. Let's consider more examples.

```
// if it has only one argument "()" are optional
Function<Integer, Integer> adder1 = x -> x + 1;

// with type inference
Function<Integer, Integer> mult2 = (Integer x) -> x * 2;

// with multiple statements
Function<Integer, Integer> adder5 = (x) -> {
    x += 2;
    x += 3;
    return x;
};
```

### Invoking lambda expressions

Once a lambda expression is created, it can be used in other places of your program like a regular Java object. You can invoke the body of an expression using special methods like `apply` as many times as you need. The name of the method depends on the type of lambda expression.

```
boolean result4Div2 = isDivisible.apply(4, 2); // true
boolean result3Div5 = isDivisible.apply(3, 5); // false
```

So, we can invoke a lambda expression like a regular method passing arguments and obtaining results!

### Passing lambda expressions to methods

One of the most popular cases is to pass a lambda expression to a method and then call it there.

Look at the method below. It takes an object of the standard generic `Function` type.

```
private static void printResultOfLambda(Function<String, Integer> function) {
    System.out.println(function.apply("HAPPY NEW YEAR 3000!"));
}
```

This function can take a `String` argument and return an `Integer` result. To test the method, let's create an object and pass it into the method:

```
// it returns the length of a string
Function<String, Integer> f = s -> s.length();
printResultOfLambda(f); // it prints 20
```

You can also pass a lambda expression to the method directly without an intermediate reference:

```
// passing without a reference
printResultOfLambda(s -> s.length()); // the result is the same: 20
```

As you can see, we can present our function as an object and pass it to a method as its argument, if the method takes an object of that type. Then, inside the method, the given function will be invoked.

But why do we need it? First, let's look at another example, which uses a more complex lambda expression and calculates the number of digits in the string.

```
// It prints the number of digits: 4
printResultOfLambda(s -> {
    int count = 0;
    for (char c : s.toCharArray()) {
        if (Character.isDigit(c)) {
            count++;
        }
    }
    return count;
});
```

What is important here? We do not pass data to the `printResultOfLambda`, but rather some piece of code as data. So, we can parameterize the same method with a different behavior at runtime. This is what typical uses of lambda expressions look like. Many standard methods can accept lambda expressions. This will be discussed in more detail in the following topics.

Let's introduce an important term according to the examples. In functional programming theory, a function that accepts or returns another function is called a **higher-order function**. In terms of Java, we're talking about methods or functions which take / return `Function<T, R>`, `BiFunction<T, U, R>` or other types we will consider soon.

### Closures

Another important trick with lambda expressions is the possibility to capture values from a context where the lambda is defined and use the values within the body. This technique is called **closure**.

Capturing is possible only if a context variable has the `final` keyword or it's effectively final, i.e. the variable isn't changed in further code. Otherwise, an error happens.

Let's see an example.

```
final String hello = "Hello, ";
Function<String, String> helloFunction = (name) -> hello + name;

System.out.println(helloFunction.apply("John"));
System.out.println(helloFunction.apply("Anastasia"));
```

The lambda expression captured the final variable `hello`.

The result of this code is:

```
Hello, John
Hello, Anastasia
```

Let's consider an example with an effective final variable.

```
int constant = 100;
Function<Integer, Integer> adder100 = x -> x + constant;

System.out.println(adder100.apply(200)); // 300
System.out.println(adder100.apply(300)); // 400
```

The variable `constant` is effectively final and is captured by the lambda expression.

### Conclusion

In this topic, you started learning a new programming paradigm. You've seen the syntax of lambda expressions and how to pass them to methods to vary their behavior at runtime. You've also learned how lambda expressions can use context variables within the body. While you are still at the beginning of the journey in the world of functional programming, you will soon see how many new possibilities this paradigm brings to solve complex practical problems.

