# Object-Oriented Programming - DIEF/UNIMORE

## Java Exercises (Generics)

**[IterableToString.java]** Write a generic static method returning a string representing all the elements of an object
implementing the Iterable<E> interface. The elements should be separated by commas.

Examples:

* iterableToString([1, 2, 3]) → "1, 2, 3, "
* iterableToString(["a", "b", "c"]) → "a, b, c, "

The method has the following prototype:

```java
public static <E> String iterableToString(Iterable<E> src);
```

where:

* **src** is the Iterable object to be converted to String.

---

**[Divide.java]** Write a generic static method returning the floating point division of any two numbers regardless of their specific type (see java.lang.Number class). Despite the prototype below seem to indicate that both arguments are required to have the same type *T*, remember that the inference algorithm tries to find the **most specific** type that works with all the arguments. Read more [here](https://docs.oracle.com/javase/tutorial/java/generics/genTypeInference.html).

Examples:

* divide(4, 2) → 2.0
* divide(4.0, 2.0) → 2.0
* divide(4, 2.0) → 2.0
* divide(4.0, 2) → 2.0

The method has the following prototype:

```java
public static <T extends Number> double divide(T a, T b);
```

where:

* **a** is the divided.
* **b** is the divisor.

---

**[Append.java]** Write a generic static method that appends the elements of one list to another list of the same type.

Examples:

* append([], [1, 2]) → [1, 2]
* append([1, 2], [3, 4]) → [1, 2, 3, 4]
* append(["a", "c"], ["c", "d"]) → ["a", "c", "c", "d"]

The method has the following prototype:

```java
public static <T> void append(List<T> dst, List<T> src);
```

where:

* **dst** is the destination to which append values.
* **src** is the source of the values.

---

**[AppendBounded.java]** Modify the exercise Append.java so that *src* can contain elements of a subclass. For
example, if people is a list of Person objects and students is a list of Student objects, then append(people, students)
should compile but append(students, people) should not. The prototype is not reported because it is the answer.

---

**[Reverse.java]** Write a generic static method to reverse a list (see List.get() and List.set() methods).

Examples:

* reverse([1, 2]) → [2, 1]
* reverse([1, 2, 3, 4]) → [4, 3, 2, 1]
* reverse(["hello", "world"]) → ["world", "hello"]

The method has the following prototype:

```java
public static <T> void reverse(List<T> list);
```

where:

* **list** is the list to be reversed.

---

**[CountOccurrences.java]** Write a generic static method counting all occurrences of a specific item of type T within a T[]. If item is null, the method should return the number of null values within the array. Write two versions of the same method: the former in plain Java, the latter using *Objects.equals()* which provides null-safety.

Examples:

* countOccurrences([1, 2, 2, 2], 2) → 3
* countOccurrences([null, null, 3, 4], null) → 2
* countOccurrences(["hello", "world", null, null], "hello") → 1

The methods have the following prototypes:

```java
public static <T> int countOccurrencesPlain(T[] src, T item);
public static <T> int countOccurrencesObjects(T[] src, T item);
```

where:

* **src** is the array to be searched within.
* **item** is the item to be searched.

---

**[BinarySearch.java]**  Binary search is one of the most popular algorithms. It finds the position of a target value within a sorted array. Write a generic static method implementing binary search within a T[], where T is any Comparable type. The method returns -1 if the target value is not found.

Examples:

* find([0, 1, 2, 3, 4, 5], 2) → 2
* find([0, 1, 2, 3, 4, 5], 3) → 3
* find([0, 1, 2, 3, 4, 5], -2) → -1
* find([0, 1, 2, 3, 4, 5], 10) → -1

The method has the following prototype:

```java
public static <T extends Comparable<T>> int find(T[] array, T key);
```

where:

* **array** is the array to be searched within.
* **key** is the key to be searched.

---

**[Pair.java]** Define a generic class Pair<K,V> representing a generic pair of objects with different types. The class must have two internal attributes named *first* and *second* of type K and V, respectively. Constructor, getters, and setters are also required.


```java
public class Pair<K, V> {
    //...
}
```

---

**[PairSameType.java]** Modify the Pair class (see Pair.java) so that both values have the same type. Furthermore, add a method *void swap()* that swaps the first and second elements of the pair. 


```java
public class PairSameType<T> {
    //...
    public void swap();
    //...
}
```

---

**[PairUtil.java]** Write a generic static method whose argument is a Pair object. The method returns a new pair,
with the first and second element swapped. The method has the following prototype:

```java
public static <K, V> Pair<V, K> swap(Pair<K, V> src);
```

where:

* **src** is a generic Pair of two objects with different types.

---

**[MapToPairs.java]** Write a generic static method that, given a Map<K, V>, returns a List<Pair<K, V>> of the key/value pairs in the map (see Map.entrySet()). The method has the following prototype:

```java
public static <K, V> List<Pair<K, V>> mapToPairs(Map<K, V> src);
```

where:

* **src** is generic key/value map.

---

**[Measure.java]** Transform the Measurer interface (reported below) into a generic interface. Then, implement in the Measure class two static generic methods returning the maximum and minimum values of an array (see prototype below), using a Measurer object as the sorting criterion. Note well: The *measure()* method of the Measurer interface associates every object to an easy to sort double value!

```java
public interface Measurer {
    double measure(Object obj);
}
```

```java
public static <T> T max(T[] array, Measurer<T> measurer);
public static <T> T min(T[] array, Measurer<T> measurer);
```

where:

* **array** is the array in which searching for minimum and maximum
* **measurer** is an object implementing the Measurer<T> interface

---

**[MinMax.java]** Write two generic static methods returning the maximum and minimum values of a generic list. An
instance of Comparator (see java.util.Comparator<T>) is used as a sorting criterion. The methods have the following
prototype:

```java
public static <T> T max(List<T> list, Comparator<? super T> cmp);
public static <T> T min(List<T> list, Comparator<? super T> cmp);
```

where:

* **list** is the list in which searching for minimum and maximum
* **cmp** is an object implementing the Comparator interface

---

**[mycollections package]** Implement a generic version of the minimalist collection framework contained within the
*exercises.collections.mycollections* package.

---
