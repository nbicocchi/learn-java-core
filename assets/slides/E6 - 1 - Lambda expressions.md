### Why Java is changing?
The rise of big-data, and affordable multicore architectures made functional programming concepts popular again. 

Languages need to evolve to track changing hardware or programmers expectations. Otherwise, they die (COBOL, LISP, …) 

A typical problem in 1991: model a company employees, departments, salaries, etc.

```
public class Employee {
    String name;
    String lastname;
    double salary;
    Department department;
    ...
}
```

A typical problem in 2023: analyse a 100GB dataset for finding lazy employees!

```
List<Employee> l = employees.stream()
.filter(e -> e.getVacations() > 1)
.collect(Collectors.toList());
```

### Functional vs imperative programming
Because of changing needs, a number of languages (Java, Python, Scala) are introducing ways for supporting functional programming. Haskell is a recent purely functional language.

The functional programming paradigm describes a program by applying and composing functions.
  * Passing functions to functions (**behaviour parametrization**)
  * Functional programs can be very terse and elegant, packing a lot of behaviour into very few lines of code. Functional programmers will make the case that in a multicore world, you need to avoid mutable state in order to scale out your programs.

The imperative programming paradigm allows you to describe a program in terms of a sequence of statements that mutate state.
  * Passing objects to functions (**value parametrization**)
  * Object-oriented programmers will retort that in actual business environments object-oriented programming scales out well in terms of developers, and as an industry, we know how to do it.

### An example: filtering students
We need to select all students with a given average, and also print a string representation of the selected students.

```
public static List<Student> filterStudentsByGrade(List<Student> students, double average) {
    List<Student> result = new ArrayList<>();
    for (Student s : students) {
        if (s.getAverage() == average) {
            System.out.println(String.format("%s_%s_%f", s.getLastname(), s.getName(), s.getAverage()));
            result.add(s);
        }
    }
    return result;
}
```

After a while, requirements change, and you need to select all the students with an average comprised within a given range. 
You can add an alternative method. 

```
public static List<Student> filterStudentsByGradeRange(List<Student> students, double low, double high) {
    List<Student> result = new ArrayList<>();
    for (Student s : students) {
        if (s.getAverage() >= low && s.getAverage() <= high) {
            System.out.println(String.format("%s_%s_%f", s.getLastname(), s.getName(), s.getAverage()));
            result.add(s);
        }
    }
    return result;
}
```

However, this approach breaks the **DRY (Don’t Repeat Yourself)** principle. The two methods vary only in one line!

### Strategy Pattern

We can define a set of interfaces and implement the Strategy Pattern (https://refactoring.guru/design-patterns/strategy). Different classes can then implement different strategies.

Behaviour parameterization is great because it enables you to separate the logic of iterating the collection from the behaviour to be applied on each element of that collection.


```
public interface StudentPredicate {
    boolean test(Student s);
}

public interface StudentFunction {
    String apply(Student s);
}

public interface StudentConsumer {
    void accept(String s);
}

public static List<Student> filterStudents(List<Student> students, StudentPredicate sp, StudentFunction sf, StudentConsumer sc) {
    List<Student> result = new ArrayList<>();
    for (Student s : students) {
        if (sp.test(s)) {
            String str = sf.apply(s);
            sc.accept(str);
            result.add(s);
        }
    }
    return result;
}
```
This code is much more flexible than our first attempt, and at the same time it’s easy to read and to use!

However, when you want to pass new behaviour to your filterStudent method, you’re forced to one of the following two options:

* (a) define classes implementing the interfaces and then instantiate the needed objects (verbose)!
* (b) define anonymous implementations of the interfaces (better but still verbose!)

Verbosity, in general, is bad because verbose code takes a long time to write and maintain.

Example of (a):
```
class StudentGoodPredicate implements StudentPredicate {
	public boolean test(Student p) {
		return s.getAverage() >= 24;
	}
}

class StudentGoodRangePredicate implements StudentPredicate {
	public boolean test(Student p) {
		return s.getAverage() >= 24 && s.average <= 28;
	}
}
```

Example of (b):
```
result = filterStudents(students, new StudentPredicate() {
    @Override
    public boolean test(Student s) {
        return s.getAverage() >= 26 && s.getAverage() <= 30;
    }
}, new StudentFunction() {
    @Override
    public String apply(Student s) {
        return String.format("%s_%s_%f", s.getLastname(), s.getName(), s.getAverage());
    }
}, new StudentConsumer() {
    @Override
    public void accept(String s) {
        System.out.println(s);
    }
});
```

### Lambda expressions
By **lambda expression** (or just "a lambda"), we mean a **function** that isn't bound to its name (an anonymous function) but can be assigned to a variable.

The most general form of a lambda expression looks like this: `(parameters) -> { body };`. Here, the part before `->` is the list of parameters (like in methods), and the part after that is the body that can return a value. The brackets `{ }` are required only for multi-line lambda expressions.

Sometimes, lambdas don't have parameters or return values or even both. Even if a lambda doesn't have a value to return, it has a body that does some useful actions (e.g. prints or saves something).

This syntax was chosen by the Java language designers because it was well received in other languages, such as C###, Scala, JavaScript. The basic syntax of a lambda is either:

`(parameters) -> expression`

or (note the curly braces for statements, this lambda is often called a block-style lambda)

`(parameters) -> { statements; }`



### Lambda expressions (examples)

A boolean expression: `(List<String> list) -> list.isEmpty()`

Creating objects: `() -> new Apple(10)`

Consuming from an object: `(Apple a) -> { System.out.println(a.getWeight()); }`

Select/extract a field from an object: `(String s) -> s.length()`

Multiply two ints: `(int a, int b) -> a * b`

Compare two objects: `(Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight())`


### Functional interfaces

Functional interfaces or interfaces defining only one function (one method) are ideal candidates for making use of lambda expressions.

Instead of using anonymous classes (still verbose!), lambda expressions can be used for providing the implementation of their single method!

```
public interface Comparator<T> {
	int compare(T o1, T o2);
}

public interface Runnable {
	void run(); 
}

public interface ActionListener extends EventListener {
	void actionPerformed(ActionEvent e);
}

public interface Callable<V> {
	V call() throws Exception;
}
```

```
// using anonymous classes
Comparator<Apple> byWeight = 
	new Comparator<Apple>() {
    		public int compare(Apple a1, Apple a2){
        		return a1.getWeight().compareTo(a2.getWeight());
    		}
	};


// using lambda expressions:
Comparator<Apple> byWeight =
    (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
```

```
result = filterStudents(students, new StudentPredicate() {
    @Override
    public boolean test(Student s) {
        return s.getAverage() >= 26 && s.getAverage() <= 30;
    }
}, new StudentFunction() {
    @Override
    public String apply(Student s) {
        return String.format("%s_%s_%f", s.getLastname(), s.getName(), s.getAverage());
    }
}, new StudentConsumer() {
    @Override
    public void accept(String s) {
        System.out.println(s);
    }
});
```

### Strategy Pattern + Lambda expressions

```
result = filterStudents(students, 
    s -> s.getAverage() >= 26 && s.getAverage() <= 30, 
    s -> String.format("%s_%s_%f", s.getLastname(), s.getName(), s.getAverage()), 
    s -> System.out.println(s));
```

### Strategy Pattern + Lambda expressions + Generics
```
public interface Predicate<T> {
    boolean test(T s);
}

public interface Function<T, R> {
    R apply(T s);
}

public interface Consumer<T> {
    void accept(T s);
}
```

``` 
public static <T, R> List<T> filter(List<T> l, Predicate<T> sp, Function<T, R> sf, Consumer<R> sc) {
    List<T> result = new ArrayList<>();
    for (T s : l) {
        if (sp.test(s)) {
            R x = sf.apply(s);
            sc.accept(x);
            result.add(s);
        }
    }
    return result;
}
```

### Behaviour parametrization
![](images/behaviour-parametrization.png)

### Functions
The most simple and general case of a lambda is a functional interface with a method that receives one value and returns another. This function of a single argument is represented by the Function interface, which is parameterized by the types of its argument and a return value:

```
public interface Function<T, R> { … }
```

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

// with two different types
Function<String, String> quote = s -> "'" + s + "'";

// with a method reference
Function<Integer, String> intToString = Object::toString;
```

Let's consider a single-line lambda expression that just checks whether the first number is divisible by the second one.

```
BiFunction<Integer, Integer, Boolean> isDivisible = (x, y) -> x % y == 0;
```

The expression has the type `BiFunction<Integer, Integer, Boolean>` which means, that it takes two `Integer` values and returns a `Boolean` value.

### Primitive Function Specializations

Since a primitive type can’t be a generic type argument, there are versions of the Function interface for the most used primitive types double, int, long, and their combinations in argument and return types:

* IntFunction, LongFunction, DoubleFunction: arguments are of specified type, return type is parameterized
* ToIntFunction, ToLongFunction, ToDoubleFunction: return type is of specified type, arguments are parameterized
* DoubleToIntFunction, DoubleToLongFunction, IntToDoubleFunction, IntToLongFunction, LongToIntFunction, LongToDoubleFunction: having both argument and return type defined as primitive types, as specified by their names

```
@FunctionalInterface
public interface ShortToByteFunction {

    byte applyAsByte(short s);
}
```

### Two-Arity Function Specializations
To define lambdas with two arguments, we have to use additional interfaces that contain “Bi” keyword in their names: BiFunction, ToDoubleBiFunction, ToIntBiFunction, and ToLongBiFunction.

BiFunction has both arguments and a return type generified, while ToDoubleBiFunction and others allow us to return a primitive value.

One of the typical examples of using this interface in the standard API is in the Map.replaceAll method, which allows replacing all values in a `Map<K,V>` with a BiFunction:

```
default void replaceAll(BiFunction<? super K,? super V,? extends V> function)
```

```
Map<String, Integer> salaries = new HashMap<>();
salaries.put("John", 40000);
salaries.put("Freddy", 30000);
salaries.put("Samuel", 50000);

salaries.replaceAll((name, oldValue) ->
name.equals("Freddy") ? oldValue : oldValue + 10000);
```

### Suppliers
The Supplier functional interface is yet another Function specialization that does not take any arguments. We typically use it for lazy generation of values. For instance, let's define a function that squares a double value. It will not receive a value itself, but a Supplier of this value:

```
public double squareLazy(Supplier<Double> lazyValue) {
return Math.pow(lazyValue.get(), 2);
}
```

### Consumers
As opposed to the Supplier, the Consumer accepts a generified argument and returns nothing. It is a function that is representing side effects.

For instance, let’s greet everybody in a list of names by printing the greeting in the console. The lambda passed to the List.forEach method implements the Consumer functional interface:

```
List<String> names = Arrays.asList("John", "Freddy", "Samuel");
names.forEach(name -> System.out.println("Hello, " + name));
```

There are also specialized versions of the Consumer — DoubleConsumer, IntConsumer and LongConsumer — that receive primitive values as arguments. More interesting is the BiConsumer interface. One of its use cases is iterating through the entries of a map:

```
Map<String, Integer> ages = new HashMap<>();
ages.put("John", 25);
ages.put("Freddy", 24);
ages.put("Samuel", 30);

ages.forEach((name, age) -> System.out.println(name + " is " + age + " years old"));
```

### Predicates
In mathematical logic, a predicate is a function that receives a value and returns a boolean value.

The Predicate functional interface is a specialization of a Function that receives a generified value and returns a boolean. A typical use case of the Predicate lambda is to filter a collection of values:

```
List<String> names = Arrays.asList("Angela", "Aaron", "Bob", "Claire", "David");
names.removeIf(s -> s.startsWith("B"));
```

In the code above, we remove from a list the names that start with the letter “B”. The Predicate implementation encapsulates the filtering logic.

As in all of the previous examples, there are IntPredicate, DoublePredicate and LongPredicate versions of this function that receive primitive values.

### Operators
Operator interfaces are special cases of a function that receive and return the same value type. The UnaryOperator interface receives a single argument. One of its use cases in the Collections API is to replace all values in a list with some computed values of the same type:

List<String> names = Arrays.asList("bob", "josh", "megan");
names.replaceAll(name -> name.toUpperCase());

The List.replaceAll function returns void as it replaces the values in place. To fit the purpose, the lambda used to transform the values of a list has to return the same result type as it receives. This is why the UnaryOperator is useful here.

Of course, instead of name -> name.toUpperCase(), we can simply use a method reference: `names.replaceAll(String::toUpperCase);`



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

What is important here? We do not pass data to the `printResultOfLambda`, but rather some piece of code as data. So, we can parameterize the same method with a different behavior at runtime. This is what typical uses of lambda expressions look like. Many standard methods can accept lambda expressions. 

