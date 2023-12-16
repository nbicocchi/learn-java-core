# Generic Data Structures (Generics)

### Reuse of code
There are situations when methods and classes do not depend on the data types on which they operate. For example, the algorithm to find a specific element in an array can process arrays of strings, integers or custom classes (java.util.Collections.sort()). **It does not matter what the array stores: the algorithm is always the same**. 

Since version 5, Java has supported generic programming that introduces abstraction over types. Generic methods and classes can handle different types in the same general way. A concrete type is determined only when a developer creates an object of the class or invokes the method. 


### An int-based resizable array

Let's start from an enhanced array for integers.

Writing a slightly different version of the EnhancedResizableArray class for each possible type it could manage would represent a massive violation of the **DRY (Don't Repeat Yourself)** principle.

```
public class EnhancedResizableArray {
    public static final int DEFAULT_CAPACITY = 4;
    int[] v;

    public EnhancedResizableArray() {
        this.v = new int[DEFAULT_CAPACITY];
    }

    public int get(int index) {
        return v[index];
    }

    public void set(int index, int value) {
        if (index >= v.length) {
            int[] tmp = new int[index * 2];
            System.arraycopy(v, 0, tmp, 0, v.length);
            v = tmp;
        }
        v[index] = value;
    }

    public boolean contains(int value) {
        for (int i : v) {
            if (i == value) {
                return true;
            }
        }
        return false;
    }

    public void fill(int value) {
        Arrays.fill(v, value);
    }

    public int[] toArray() {
        return Arrays.copyOf(v, v.length);
    }

    public int length() {
        return v.length;
    }
}
```

### An Object-based resizable array
To mitigate this issue we could use Object[] instead of int[]!

```
public class EnhancedResizableArrayObject {
    public static final int DEFAULT_CAPACITY = 4;
    Object[] v;

    public EnhancedResizableArrayObject() {
        this.v = new Object[DEFAULT_CAPACITY];
    }

    public Object get(int index) {
        return v[index];
    }

    public void set(int index, Object value) {
        if (index >= v.length) {
            Object[] tmp = new Object[index * 2];
            System.arraycopy(v, 0, tmp, 0, v.length);
            v = tmp;
        }
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

    public void fill(Object value) {
        Arrays.fill(v, value);
    }

    public Object[] toArray() {
        return Arrays.copyOf(v, v.length);
    }

    public int length() {
        return v.length;
    }
}

```

Now we are talking! We can store every kind of Object within the same container!

```
public static void main(String[] args) {
    EnhancedResizableArrayObject resizableArrayObject = new EnhancedResizableArrayObject();
    resizableArrayObject.set(0, new String("Hello"));
    resizableArrayObject.set(1, new String("World"));
    String s = resizableArrayObject.get(0);
}
```

However, the compiler complains about the last line. It doesn't know what data type is returned. The compiler requires an explicit casting:

```
String s = (String) resizableArrayObject.get(0);
```

There is no contract that could guarantee that the return type of the list is a *String*. The array could hold any object. It can only guarantee that it is an *Object* and therefore requires an explicit cast.

This cast can be annoying, it clutters our code, and can cause type-related runtime errors if a programmer makes a mistake with the explicit casting (e.g., forget to use instanceof).

```
public static void main(String[] args) {
    EnhancedResizableArrayObject resizableArrayObject = new EnhancedResizableArrayObject();
    resizableArrayObject.set(0, new String("Hello"));
    resizableArrayObject.set(1, new Point(2,3));
    String s = (String)resizableArrayObject.get(1); // java.lang.ClassCastException
}
```

It would be much easier if programmers could express their intention to use specific types and the compiler ensured the correctness of such types. This is the core idea behind generics.

```
List<Integer> list = new ArrayList<>();
```

By adding the diamond operator `<` `>` containing the type, we specify the type held inside the list. The compiler can enforce the type at compile time. 

In small programs, this might seem like a trivial addition. But in larger programs, this can add significant robustness and makes the program easier to read.

### A generic resizable array
```
public class EnhancedResizableArrayGeneric<T> {
    public static final int DEFAULT_CAPACITY = 4;
    T[] v;

    public EnhancedResizableArrayGeneric() {
        this.v = (T[])new Object[DEFAULT_CAPACITY];
    }

    public T get(int index) {
        return v[index];
    }

    public void set(int index, T value) {
        if (index >= v.length) {
            T[] tmp = (T[])new Object[index * 2];
            System.arraycopy(v, 0, tmp, 0, v.length);
            v = tmp;
        }
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

    public void fill(T value) {
        Arrays.fill(v, value);
    }

    public T[] toArray() {
        return Arrays.copyOf(v, v.length);
    }

    public int length() {
        return v.length;
    }
}
```

```
public static void main(String[] args) {
    EnhancedResizableArrayGeneric<String> resizableArrayGeneric = new EnhancedResizableArrayGeneric<>();
    resizableArrayGeneric.set(0, new String("Hello"));
    resizableArrayGeneric.set(1, new Point(2,3));   // Compile-time error!
    String s = (String)resizableArrayGeneric.get(1);
}
```

We traded a run-time error for a compile-time error! Nice! 

---

### Type parameters
A generic type is a class (or interface) that is parameterized over types. To declare a generic class, we need to declare a class with the type parameter section delimited by angle brackets `<` `>` following the class name.

A class can declare one or more type parameters and use them inside the class body as types for fields, method arguments, return values, and local variables. 

Remember that only a reference type (an array, a standard class, a custom class) can be used as a concrete type for generics. This means that instead of primitive types, we use wrapper classes such as `Integer`, `Double`, `Boolean`, and so on.

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

Both *get()* and *set()* methods do not depend on the concrete type of `T`. They can take/return a string or a number in the same way.

```
GenericType<Integer> obj1 = new GenericType<>(10);
Integer i = obj1.get();

GenericType<String> obj2 = new GenericType<>("abc");
String s = obj2.get();
```

### Multiple type parameters
A class can have any number of type parameters. For example, the following class has two. Most generic classes have just one or two type parameters.

```
public class Pair<K, V> {
    K key;
    V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
```

### The naming convention for type parameters
There is a naming convention that restricts type parameter names to **single uppercase letters**. Without this convention, it would be difficult to tell the difference between a type variable and an ordinary class name.

The most commonly used type parameter names are:

-   `T` -- Type
-   `S`, `U`, `V` etc. -- 2nd, 3rd, 4th types
-   `E` -- Element (used extensively by different collections)
-   `K` -- Key
-   `V` -- Value
-   `N` -- Number

Some examples from the Java API:

```
interface List<E> {
    boolean add(E e);
    E remove(int index);
    ...
}

interface Map<K,V> {
    Set<K> keySet();
    Collection<V> values();
    ...
}

interface Function<T,R> {
    R apply(T t);
}

interface BiFunction<T,U,R> {
    R apply(T t, U u);
}

interface ToIntBiFunction<T,U> {
    int apply(T t, U u);
}
```


### Creating objects of generic classes
To create an object of a generic class, we need to specify the type argument following the type name. 

Java 7 made it possible to replace the type arguments required to invoke the constructor of a generic class with an empty set of type arguments, as long as the compiler can *infer* the type arguments from the context.

```
List<Person> persons = new ArrayList<>();
```

If a class has multiple type parameters, we need to specify all of them when creating instances. For example:

```
Map<Integer, String> map = new HashMap<>();
```

---

### Generic static methods
**Static methods cannot use type parameters of their class!** Type parameters of the class these methods belong to can only be used in instance methods. If you want to use type parameters in a static method, declare this method's own type parameters.

The declaration of the generic type `T` surrounded by angle brackets allows us to use this type in the method. We remind you that it can belong to a generic or a non-generic class because it does not matter for generic methods.

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

As another example, take a look at one that prints the elements of a generic array.

```
public static <E> void print(E[] array) {
    for (int i = 0; i < array.length; i++) {
        System.out.print(array[i] + " ");
    }
    System.out.println();
}
```

Just like in generic classes, the type parameter section can contain multiple type parameters separated by commas.

For instance, the following method declares two type parameters.

```
public static <T, U> void method(T t, U u) {
    // do something
}
```

Static generic methods are often used to write generic algorithms that do not depend on the type they operate on. This can be convenient when the method has to be used independently of the class it belongs to. We frequently use generic static methods for different operations with arrays and collections: sorting an array, searching for a value in a collection, reversing an array, and so on.

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

The method receives arguments of both the class's type (`T`) and the method's own type (`U`). Because `T` was already declared in the class header, the method only has to declare the generic type `U`. The method returns a variable of type `T`.

As another example, consider to method *toArray()* defined within the java.util.List interface.

```
interface List<E> {
    boolean add(E e);
    E remove(int index);
    <T> T[] toArray(T[] a);
    ...
}
```

---

### Type bounds

Let's now consider an example that will reveal another aspect of generics. Imagine that we have a generic `Storage<T>` class that can contain objects of any class. But there are some situations in which we want to restrict these objects. We can say, for example, that the storage has to be able to contain only books. In these types of situations, we should use type bounds. 

Consider this code:

```
class Storage<T> {
    private T nameOfElements;
    // other methods
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

The third line returns an error: `Type parameter 'Computers' is not within its bound; should extend Books.` Since this is a compile-time error, we catch this problem before it can appear in a real application. For this reason, type bounds are safe to use.

Note that `extends` can mean not only an extension of a certain class but also an implementation of an interface. Generally speaking, this word is used as a replacement for an extension of normal classes, not generic classes. Trying to extend a generic class (for example, `Storage<Brochures> extends Storage<Books>`) will lead to an error.

### Multiple bounds

Note that under the hood, every type variable declared as a type parameter has a bound. If no bound is declared, `Object` is the bound. For this reason,

```
class SomeClass<T> {...}
```

is equivalent to

```
class SomeClass<T extends Object> {...}
```

A type variable may have a single type bound:

```
<T extends A>
```

or have multiple bounds:

```
<T extends A & B & C & ...>
```

The first type bound ("A") can be a class or an interface. The rest of the type bounds ("B" onwards) must be interfaces.


### Constraining Type Parameters (extends)
It is often necessary to specify what types can be used in a generic class or method. Consider a generic method that finds the average of the values in an ArrayList of objects. How can you compute averages when you know nothing about the element type? You need to have a mechanism for measuring the elements. 

```
public interface Measurable {
    double getMeasure();
}
```

We can constrain the type of the elements, requiring that the type implement the Measurable type. In Java, this is achieved by adding the clause extends Measurable after the type parameter:

```
public static <E extends Measurable> double average(ArrayList<E> objects)
```

This means, "E extends or implements Measurable". In this situation, we say that E is a subtype of the Measurable type. Here is the complete average method:

```
public static <E extends Measurable> double average(ArrayList<E> objects) {
    if (objects.size() == 0) { 
        return 0; 
    } 
    double sum = 0;
    for (E obj : objects) {
        sum = sum + obj.getMeasure(); 
    }
    return sum / objects.size(); 
}
```

Now consider the task of finding the minimum in an ArrayList. We can return the element with the smallest measure. 

We will use the Comparable interface type (instead of Measurable).
The Comparable interface is another generic type:
```
public interface Comparable<T> {
    int compareTo(T other);
}
```

For example, String implements Comparable<String>. You can compare strings with other strings, but not with objects of different classes.
If the ArrayList has elements of type E, then we want to require that E implements Comparable<E>:

```
public static <E extends Comparable<E>> E min(ArrayList<E> objects) {
    E smallest = objects.get(0);
    for (int i = 1; i < objects.size(); i++) {
        E obj = objects.get(i);
        if (obj.compareTo(smallest) < 0) {
            smallest = obj;
        } 
    }
    return smallest; 
}
```

### Constraining Type Parameters (super)

```
public static <E extends Comparable<E>> E min(ArrayList<E> objects) // compiles but too restrictive!
```

However, this bound is too restrictive. Suppose the BankAccount class implements Comparable<BankAccount>. Then the subclass SavingsAccount also implements `Comparable<BankAccount>` and not `Comparable<SavingsAccount>`. If you want to use the min method with a SavingsAccount list, then the type parameter of the Comparable interface should be any supertype of the array list’s element type:

```
// from java.util.Collections
static <T extends Object & Comparable<? super T>> T min(Collection<? extends T> coll)
static <T extends Object & Comparable<? super T>> T max(Collection<? extends T> coll)
```

### Josh Bloch’s Rule
* Use <? extends T> when the generic instance need to read values
* Use <? super T> when the generic instance need to write values

```
public interface Shop<T> {
    void buy(T item);
    T sell();
    void buy(Collection<? extends T> cart);
    void sell(Collection<? super T> cart, int n);
}
```

### Wildcard Types (Summary)
* Wildcard with upper bound *? extends B* -> Any subtype of B (B included)
* Wildcard with lower bound *? super B* -> Any supertype of B (B included)
* Unbounded wildcard *?* -> Any type

### Two ways of writing
Use wildcards whenever constraints among parameters and return values are absent

```
// from java.util.Collections
public static void reverse(List<?> list) {}
public static void shuffle(List<?> list, Random rnd) {}
public static void swap(List<?> list, int i, int j) {}
```

Use <T> whenever constraints among parameters and return values are present

```
// from java.util.Collections
public static <T> void sort(List<T> list, Comparator<? super T> c) {}
public static <T> void fill(List<? super T> list, T obj) {}
public static <T> void copy(List<? super T> dest, List<? extends T> src) {}
```

### Type Erasure
Because generic types are a fairly recent addition to the Java language, the virtual machine that executes Java programs does not work with generic classes or methods. Instead, type parameters are “erased”, that is, they are replaced with ordinary Java types. **Each type parameter is replaced with its bound, or with Object if it is not bounded.**

```
public class Pair<K, V> {
    K key;
    V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
```
After type erasure, it becomes:

```
public class Pair {
    Object key;
    Object value;

    public Pair(Object key, Object value) {
        this.key = key;
        this.value = value;
    }

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
```

As you can see, the type parameters K and V have been replaced by Object. The result is an ordinary class.

The same process is applied to generic methods. Consider this method:

```
public static <E extends Measurable> E min(E[] objects) {
    E smallest = objects[0];
    for (int i = 1; i < objects.length; i++) {
        E obj = objects[i];
        if (obj.getMeasure() < smallest.getMeasure()) {
            smallest = obj;
        } 
    }
    return smallest; 
}
```

```
public static Measurable min(Measurable[] objects) {
    Measurable smallest = objects[0];
    for (int i = 1; i < objects.length; i++) {
        Measurable obj = objects[i];
        if (obj.getMeasure() < smallest.getMeasure()) {
            smallest = obj;
        } 
    }
    return smallest; 
}
```

# Wisdom pills
*If you ask the business managers, they’ll often say that it’s more important for the software system to work than being easy to be modified. Developers, in turn, often go along with this attitude. But it’s the wrong attitude. I can prove that it is wrong with the simple logical tool of examining the extremes. If you give me a program that works perfectly but is impossible to change, then it won’t work when the requirements change, and I won’t be able to make it work. Therefore, the program will become useless. If you give me a program that does not work but is easy to change, then I can make it work, and keep it working as requirements change. Therefore, the program will remain continually useful.*

Clean Architecture, Robert C. Martin


