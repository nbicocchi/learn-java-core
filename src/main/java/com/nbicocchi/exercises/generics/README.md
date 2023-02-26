# Object Oriented Programming - DIEF/UNIMORE

## Java Exercises (Generics)

---

**[PrintIterable.java]** Write a generic static method returning a string representing all the elements of an object
implementing the Iterable<E> interface. The elements should be separated by commas.

Examples:

* printIterable([1, 2, 3]) → "1, 2, 3, "
* printIterable(["a", "b", "c"]) → "a, b, c, "

The method has the following prototype:

```
public static <E> String printIterable(Iterable<E> src);
```

---

**[Divide.java]** Write a generic static method returning the floating point division of any two numbers regardless of
their specific type e.g. int, double, etc.. (see java.lang.Number class).

Examples:

* divide(4, 2) → 2.0
* divide(4.0, 2) → 2.0
* divide(4, 2.0) → 2.0

The method has the following prototype:

```
public static <T extends Number> double divide(T a, T b);
```

---

**[Append.java]** Write a generic static method that appends the elements of one list to another list of the same type.

Examples:

* append([], [1, 2]) → [1, 2]
* append([1, 2], [3, 4]) → [1, 2, 3, 4]

The method has the following prototype:

```
public static <T> void append(List<T> a, List<T> b);
```

---

**[AppendBounded.java]** Modify the exercise Append.java so that the b list can contain elements of a subclass. For
example, if people is a list of Person objects and students is a list of Student objects, then append(people, students)
should compile but append(students, people) should not. The prototype of the method is not reported because it is the
answer.

---

**[Reverse]** Write a generic static method to reverse a list.

Examples:

* reverse([1, 2]) → [2, 1]
* reverse([1, 2, 3, 4]) → [4, 3, 2, 1]

The method has the following prototype:

```
public static <T> void reverse(List<T> list);
```

---

**[CountOccurrences]** Write a generic static method counting all occurrences of a specific item of type T within a T[].
It the passed item is null, the method should return the number of null values within the array.

Examples:

* countOccurrences([1, 2, 2, 2], 2) → 3
* countOccurrences([null, null, 3, 4], null) → 2

The method has the following prototype:

```
public static <T> int countOccurrences(T[] src, T item);
```

---

**[Pair.java]** Define a generic class Pair<K,V> representing a generic pair of objects with different types. The class
must have two internal attributes named *first* and *second* of type K and V, respectively. Constructor, getters, and
setters are required.

```
public class Pair<K, V> {
    ...
}
```

---

**[PairSameType.java]** Modify the Pair class (see Pair.java) so that both values have the same type.

```
public class Pair<K> {
    ...
}
```

---

**[PairSameTypeSwap.java]** Add a method swap to the PairSameType class (see PairSameType.java) that swaps the first and
second elements of the pair. The method has the following prototype:

```
public void swap();
```

---

**[PairUtil.java]** Write a generic static method whose argument is a Pair object. The method should return a new pair,
with the first and second element swapped. The method has the following prototype:

```
public static <K, V> Pair<V, K> swap(Pair<K, V> src);
```

---

**[MapToPairs.java]** Write a generic static method that, given a Map<K, V>, returns a List<Pair<K, V>> of the key/value
pairs in the map. The method has the following prototype:

```
public static <K, V> List<Pair<K, V>> mapToPairs(Map<K, V> src);
```

---

**[Measure.java]** Make the Measurer interface (see below) into a generic interface. Additionally, implement two static
generic methods returning the maximum and minimum values of the array (see prototype below). The Measurer object
provides the sorting criteria.

```
public interface Measurer {
    double measure(Object obj);
}
```

```
public static <T> T max(T[] values, Measurer<T> measurer);
public static <T> T min(T[] values, Measurer<T> measurer);
```

---

**[MinMax.java]** Write two generic static methods returning the maximum and minimum values of a generic list. An
instance of Comparator (see java.util.Comparator<T>) is used as a sorting criteria. The methods have the following
prototype:

```
public static <T> T max(List<T> list, Comparator<? super T> cmp);
public static <T> T min(List<T> list, Comparator<? super T> cmp);
```

---


**[mycollection package]** Implement a generic version of the minimalist collection framework contained within the
*exercises.collections.mycollections* package.

---