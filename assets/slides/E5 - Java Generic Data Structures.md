# Generic programming

### Reuse of code
There are situations when methods and classes do not depend on the data types on which they operate. For example, the algorithm to find a specific element in an array can process arrays of strings, integers or custom classes. It does not matter what the array stores: the algorithm is always the same. Yet we cannot write this algorithm as a single method, because it requires different arguments (`int[]`, `String[]`, etc).

Since version 5, Java has supported generic programming that introduces abstraction over types. Generic methods and classes can handle different types in the same general way. A concrete type is determined only when a developer creates an object of the class or invokes the method. 


### An int-only resizable array
```
public class EnhancedResizableArray {
    int size;
    int[] v;
    
    public EnhancedResizableArray() {
        this.v = new int[8];
        this.size = 0;
    }

    public void add(int value) {
        if (size >= v.length) {
            int[] tmp = new int[v.length * 2];
            System.arraycopy(v, 0, tmp, 0, v.length);
            v = tmp;
        }
        set(size++, value);
    }

    public void remove(int index) {
        System.arraycopy(v, index + 1, v, index, v.length - index - 1);
        size--;
    }

    public int get(int index) {
        return v[index];
    }

    public void set(int index, int value) {
        v[index] = value;
    }
    
    public int size() {
        return size;
    }
}
```

Writing a slightly different version of the EnhancedResizableArray class for each possible type it could manage (in this case, int) would represent a massive violation of the **DRY (Don't Repeat Yourself)** principle.

### An Object resizable array

```
public class EnhancedResizableArrayObject {
    int size;
    Object[] v;

    public EnhancedResizableArrayObject() {
        this.v = new Object[4];
        this.size = 0;
    }

    public void add(Object value) {
        if (size >= v.length) {
            Object[] tmp = new Object[v.length * 2];
            System.arraycopy(v, 0, tmp, 0, v.length);
            v = tmp;
        }
        set(size++, value);
    }

    public void remove(int index) {
        System.arraycopy(v, index + 1, v, index, v.length - index - 1);
        size--;
    }

    public Object get(int index) {
        return v[index];
    }

    public void set(int index, Object value) {
        v[index] = value;
    }

    public boolean contains(Object value) {
        for (Object i : v) {
            if (i.equals(value)) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }
}
```

Now we are talking! We can store every kind of Object within the same container!


```
public static void main(String[] args) {
    EnhancedResizableArrayObject earray = new EnhancedResizableArrayObject();
    earray.add(new String("Hello"));
    earray.add(new String("World"));
    String s = ea.get(0);
}
```

Surprisingly, the compiler will complain about the last line. It doesn't know what data type is returned. The compiler will require an explicit casting:

```
String s = (String) earray.get(0);
```

There is no contract that could guarantee that the return type of the list is a *String*. The array could hold any object. It can only guarantee that it is an *Object* and therefore requires an explicit cast to ensure that the type is safe.

This cast can be annoying, is also clutters our code, and It can cause type-related runtime errors if a programmer makes a mistake with the explicit casting (e.g., forget to use instanceof).

```
public static void main(String[] args) {
    EnhancedResizableArrayObject ea = new EnhancedResizableArrayObject();
    ea.add(new String("Hello"));
    ea.add(new Point(2, 3));
    String s = (String) ea.get(1); // run-time error!
}
```

It would be much easier if programmers could express their intention to use specific types and the compiler ensured the correctness of such types. This is the core idea behind generics.

```
List<Integer> list = new ArrayList<>();
```

By adding the diamond operator <> containing the type, we narrow the specialization of this list to only *Integer* type. In other words, we specify the type held inside the list. The compiler can enforce the type at compile time. In small programs, this might seem like a trivial addition. But in larger programs, this can add significant robustness and makes the program easier to read.

### An generic resizable array

```
public class EnhancedResizableArrayGeneric<T> {
    int size;
    T[] v;

    public EnhancedResizableArrayGeneric() {
        this.v = (T[]) new Object[4];
        this.size = 0;
    }

    public void add(T value) {
        if (size >= v.length) {
            T[] tmp = (T[]) new Object[v.length * 2];
            System.arraycopy(v, 0, tmp, 0, v.length);
            v = tmp;
        }
        set(size++, value);
    }

    public void remove(int index) {
        System.arraycopy(v, index + 1, v, index, v.length - index - 1);
        size--;
    }

    public T get(int index) {
        return v[index];
    }

    public void set(int index, T value) {
        v[index] = value;
    }

    public boolean contains(T value) {
        for (T i : v) {
            if (i.equals(value)) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }
}
```

```
public static void main(String[] args) {
    EnhancedResizableArrayGeneric<String> ea = new EnhancedResizableArrayGeneric<>();
    ea.add(new String("Hello"));
    ea.add(new Point(2, 3)); // compile-time error!
    String s = ea.get(0);
}
```



### Type parameters

A generic type is a generic class (or interface) that is parameterized over types. To declare a generic class, we need to declare a class with the type parameter section delimited by angle brackets `<` `>` following the class name.

In the following example, the class `GenericType` has a single type parameter named `T`. We assume that the type `T` is "some type" and write the class body regardless of the concrete type.

```
class GenericType<T> {

    /**
     * A field of "some type"
     */
    private T t;

    /**
     * Takes a value of "some type" and assigns it to the field
     */
    public GenericType(T t) {
        this.t = t;
    }

    /**
     * Returns a value of "some type"
     */
    public T get() {
        return t;
    }

    /**
     * Takes a value of "some type", assigns it to a field and then returns it
     */
    public T set(T t) {
        this.t = t;
        return this.t;
    }
}
```

After being declared, a type parameter can be used inside the class body as an ordinary type. For instance, the above example uses the type parameter `T` as:

-   a type for a *field*
-   a *constructor* argument type
-   an *instance method* argument and return type

The behavior of both instance methods does not depend on the concrete type of `T`; it can take/return a string or a number in the same way.

```
GenericType<Integer> obj1 = new GenericType<>(10);

GenericType<String> obj2 = new GenericType<>("abc");
```

A class can have any number of type parameters. For example, the following class has two. Most generic classes have just one or two type parameters.

```
public class Pair<K, V> {
    K first;
    V second;

    public Pair(K first, V second) {
        this.first = first;
        this.second = second;
    }

    public K getFirst() {
        return first;
    }

    public void setFirst(K first) {
        this.first = first;
    }

    public V getSecond() {
        return second;
    }

    public void setSecond(V second) {
        this.second = second;
    }
}
```

### The naming convention for type parameters

There is a naming convention that restricts type parameter name choices to single uppercase letters. Without this convention, it would be difficult to tell the difference between a type variable and an ordinary class name.

The most commonly used type parameter names are:

-   `T` -- Type
-   `S`, `U`, `V` etc. -- 2nd, 3rd, 4th types
-   `E` -- Element (used extensively by different collections)
-   `K` -- Key
-   `V` -- Value
-   `N` -- Number

### Creating objects of generic classes

To create an object of a generic class (standard or custom), we need to specify the type argument following the type name. Java 7 made it possible to replace the type arguments required to invoke the constructor of a generic class with an empty set of type arguments, as long as the compiler can *infer* the type arguments from the context.

```
GenericType<Integer> obj1 = new GenericType<>(10);

GenericType<String> obj2 = new GenericType<>("abc");
```

The pair of angle brackets `<>` is informally called the diamond operator.

After we have created an object with a specified type argument, we can invoke methods of the class that take or return the type parameter:

```
Integer number = obj1.get(); // 10
System.out.println(obj1.set(20));    // prints the number 20

String string = obj2.get();  // "abc"
System.out.println(obj2.set("def")); // prints the string "def"
```

If a class has multiple type parameters, we need to specify all of them when creating instances:

```
GenericType<Type1, Type2, ..., TypeN> obj = new GenericType<>(...);
```

For example:

```
Map<Integer, String> map = new HashMap<>();
```


### Custom generic array

As a more complicated example, let us consider the following class which represents a generic immutable array. It has one field to store items of type `T`, a constructor to set items, a method to get an item by its index, and another method to get the length of the internal array. The class is immutable because it does not provide methods to modify the items array.

```
public class ImmutableArray<T> {

    private final T[] items;

    public ImmutableArray(T[] items) {
        this.items = items.clone();
    }

    public T get(int index) {
        return items[index];
    }

    public int length() {
        return items.length;
    }
}
```

This class shows that a generic class can have methods (like length) that do not use the parameter type at all.

The following code creates an instance of `ImmutableArray` to store three strings and then output the items to the standard output.

```
ImmutableArray<String> stringArray = new ImmutableArray<>(new String[] {"item1", "item2", "item3"});

for (int i = 0; i < stringArray.length(); i++) {
    System.out.print(stringArray.get(i) + " ");
}
```

It is possible to parameterize `ImmutableArray` with any reference type, including arrays, standard classes, or your own classes.

```
ImmutableArray<Double> doubleArray = new ImmutableArray<>(new Double[] {1.03, 2.04});
```


### Conclusion

A class can declare one or more type parameters and use them inside the class body as types for fields, method arguments, return values, and local variables. In this case, the class does not know the concrete type on which it operates. The concrete type must be specified when creating instances of the class. This approach allows you to write classes and methods that can process many different types in the same way.

Remember that only a reference type (an array, a standard class, a custom class) can be used as a concrete type for generics. This means that instead of primitive types, we use wrapper classes such as `Integer`, `Double`, `Boolean`, and so on when creating an object of a generic class.


---


## Generics and Object

As you know, **generics** enable us to parameterize types when defining classes (or interfaces) and methods. Parameterized types make it possible to reuse the same code while processing different concrete types.

### Reusing code with generics

Let's consider a generic class named `GenericType` that stores a value of "some type".

```
class GenericType<T> {

    private T t;

    public GenericType(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }
}
```

It is possible to create an object with a concrete type (e.g., `String`):

```
GenericType<String> instance1 = new GenericType<>("abc");
String str = instance1.get();
```

We can also create instances with other types (`Integer`, `Character`) and then invoke the `get` method to access the internal field. In this manner, generics allow us to use the same class and methods for processing different types.

### Reusing code with Object

But there is another way to reuse code. If we declare a field of type `Object`, we can assign a value of any reference type to it. This approach was widely used before the introduction of generics inJava 5.

The following class demonstrates this concept:

```
class NonGenericClass {

    private Object val;

    public NonGenericClass(Object val) {
        this.val = val;
    }

    public Object get() {
        return val;
    }
}
```

Now we can create an instance of this class with the same string as in the previous example (see `GenericType`).

```
NonGenericClass instance2 = new NonGenericClass("abc");
```

It is also possible to create an instance by passing in a value of type `Integer`, `Character`, or any other reference type.

Using the `Object` class this way allows us to reuse the same class to store different data types.

### The advantage of generics: from run-time to compile-time

After an invocation of the `get()` method we obtain an `Object`, not a `String` or an `Integer`. We cannot get a string directly from the method.

```
NonGenericClass instance2 = new NonGenericClass("abc");
String str = instance2.get(); // Compile-time error: Incompatible types
```

To get the string back, we must perform an explicit typecast to the `String` class.

```
String str = (String) instance2.get(); // "abc"
```

This works because a string was passed into `instance2`. But what if the instance does not store a string? If this is the case, the code throws an exception. Here is an example:

```
NonGenericClass instance3 = new NonGenericClass(123);
String str = (String) instance3.get(); // throws java.lang.ClassCastException
```

Now we can see the main advantage of generics over the `Object` class. Because there is no need to perform an explicit typecast, we never get a runtime exception. If we do something wrong, we can see it at compile time.

```
GenericType<String> instance4 = new GenericType<>("abc");

String str = instance4.get(); // There is no typecasting here
Integer num = instance4.get(); // It does not compile
```

A compile-time error will be detected by the programmer, not a user of the program. Because generics let the compiler take care of typecasting, generics are both safer and more flexible compared to the `Object` class.


---


## Generic methods

In previous topics we have discussed generic classes and how one can use them. But Java also has generic methods that can be very useful. Generic methods allow type parameters to be passed to a method and used in its logic. They also allow a type parameter to be the return type.

All methods can declare their own type parameters, regardless of the class they belong to. This means that a non-generic class can contain generic methods.

Static methods cannot use type parameters of their class! Type parameters of the class these methods belong to can only be used in instance methods. If you want to use type parameters in a static method, declare this method's own type parameters.

Let's take a look at examples of generic static and instance methods and find out how they are used.

### Generic static methods

The following static method is declared as generic. The declaration of the generic type `T` surrounded by angle brackets allows us to use this type in the method. We remind you that it can belong to a generic or a non-generic class because it does not matter for generic methods.

```
public static <T> T doSomething(T t) {
    return t;
}
```

The type parameter `T` can be used to declare the return type and the type of the method's arguments. A generic method can take or return values of non-generic types as well. For instance, the following method takes a generic array and returns its size as an `int`.

```
public static <E> int length(E[] array) {
    return array.length;
}
```

A generic method's body is declared like that of any other method.

We can pass an array of integers to the method we defined earlier and find its length:

```
Integer[] array = { 1, 2, 3, 4 };
int len = length(array); // pass an array of Integers
```

We could use this same method to find the length of an array of Strings (or any other reference type).

```
String[] stringArray = { "a", "b", "c", "d" };
len = length(stringArray);
```

Recall that type parameters can represent only reference types, not primitive types.

As another example of a generic method, take a look at one that prints the elements of a generic array.

```
public static <E> void print(E[] array) {
    for (int i = 0; i < array.length; i++) {
        System.out.print(array[i] + " ");
    }
    System.out.println();
}
```

Let's create an array and print it using this method.

```
Character[] characters = { 'a', 'b', 'c' };
print(characters);
```

The output will be:

```
a b c
```

In this example, we used the `void` keyword in the declaration of the method because the method does not return anything.

Just like in generic classes, the type parameter section can contain multiple type parameters separated by commas.

For instance, the following method declares two type parameters.

```
public static <T, U> void method(T t, U u) {
    // do something
}
```

Static generic methods are often used to write generic algorithms that do not depend on the type they operate on. This can be convenient when the method has to be used independently from the class it belongs to. We frequently use generic static methods for different operations with arrays and collections: sorting an array, searching for a value in a collection, reversing an array, and so on.

### Generic instance methods

Just like static methods, instance methods can have their own type parameters. There is no difference in their declaration compared to static methods, excluding the absence of the `static` keyword.

```
class SimpleClass {

    public <T> T getParameterizedObject(T t) {
        return t;
    }
}
```

The class above does not provide a type parameter, so we have to specify the type parameter in the method declaration to make the method `getParameterizedObject` generic.

Note that in this example we cannot use `T` as the type for a field in the class, because it belongs to the method rather than the class itself.

Now we can create an instance of the class and invoke the method. It will return a value that has the same type as the value that was passed in.

```
SimpleClass instance = new SimpleClass();
Integer value = instance.getParameterizedObject(601); // this will return 601
```

Although generic methods can belong to non-generic classes, it is more common for a generic method to belong to a class that is also generic. Below is an example of a generic class that contains a generic instance method.

```
class SimpleClass<T> {

    public <U> T getParameterizedObject(T t, U u) {
        return t;
    }
}
```

The method receives arguments of both the class's type (`T`) and the method's own type (`U`). Because `T` was already declared in the class header, the method only has to declare the generic type `U`. The method returns the variable of type `T`.

### Conclusion

Generic methods are a must-have for programmers since they allow us to restrict the scope of type parameters to a single function. Type parameters in generic methods can be used as return types or as types for arguments passed to the function. Both static and instance methods can be generic.


---


## Type bounds

In previous articles, we have mentioned that generics can accept any type of parameter and make it possible to reuse some code. Let's now consider an example that will reveal another aspect of generics. Imagine that we have a generic `Storage<T>` class that can contain objects of any class. But there are some situations in which we want to restrict these objects. We can say, for example, that the storage has to be able to contain only books. In these types of situations, we should use type bounds.

### Usage

Let us take a closer look at type bounds. Consider this code:

```
class Storage<T> {
    private T nameOfElements;
    //other methods
}
```

We can put any type of object inside `Storage<T>`. As stated earlier, we would like to limit this class to be able to store only books. Let's assume we have a `Books` class to represent all books. Then we can implement our limitation by adding `<T extends Books>`:

```
class Storage<T extends Books> {
    private T nameOfElements;
    //other methods
}
```

Let us create three classes:

```
public class Books { }
public class Brochures extends Books { }
public class Computers { }
```

Now creating three `Storage` objects will lead to different results:

```
Storage<Books> storage1 = new Storage<>(); //no problem
Storage<Brochures> storage2 = new Storage<>(); //no problem
Storage<Computers> storage3 = new Storage<>(); //a compile-time error
```

The first two lines will compile without problems. The third one, however, will return an error: `Type parameter 'Computers' is not within its bound; should extend Books.` Since this is a compile-time error, we catch this problem before it can appear in a real application. For this reason, type bounds are safe to use.

Note that `extends` can mean not only an extension of a certain class but also an implementation of an interface. Generally speaking, this word is used as a replacement for an extension of normal classes, not generic classes. Trying to extend a generic class (for example, `Storage<Brochures> extends Storage<Books>`) will lead to an error.

### Principles

Type bounding involves two keywords, "extends" and "super", each with their own rules regulating their utilization. In this topic, however, we deal with the most common use of type bounds: setting an upper bound with the "extends" keyword. We will learn more about the principles underlying these keywords in the "Wildcards" topic.

Note that under the hood, every type variable declared as a type parameter has a bound. If no bound is declared, `Object` is the bound. For this reason,

```
class SomeClass<T> {...}
```

is equivalent to

```
class SomeClass<T extends Object> {...}
```

### Multiple bounds

A type variable may have a single type bound:

```
<T extends A>
```

or have multiple bounds:

```
<T extends A & B & C & ...>
```

The first type bound ("A") can be a class or an interface. The rest of the type bounds ("B" onwards) must be interfaces.

Note: if `T` has a bound that is a class, this class must be specified first! Otherwise, a compile-time error arises:

```
<T extends B & C & A & ...> //an error if A is a class
```

### Conclusion

Type bounds are widely used to restrict type parameters. The most common use of type bounds is to set upper bounds with the `extends` keyword. Certain situations, however, require the use of wildcards, a topic closely related to type bounds. You will learn about wildcards in the next article.