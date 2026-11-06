# E6 — Generics

**Java 25 · Practice without AI-generated code**

Implement the exercises yourself and validate your work with the supplied tests where available. The sections follow the order of the covered course topics; exercises may combine earlier skills.

[Course slides and module index](../../../../../../../../../README.md#slides) · [AI exercises](../../../../../../../../exercises-ai/E6%20-%20Java%20Generics.md)

## Contents

- [Generic classes and methods](#generic-classes-and-methods)
- [Bounds and wildcards](#bounds-and-wildcards)
- [Putting generics into practice](#putting-generics-into-practice)

## Running tests

From `exercises/exercises-no-ai/`:

```sh
mvn test
mvn -Dtest="com.nbicocchi.exercises.e6_generics.**" test
```

## Generic classes and methods

### Exercise 1 — IterableToString

Source: [IterableToString.java](IterableToString.java). Tests: [IterableToStringTest.java](../../../../../../test/java/com/nbicocchi/exercises/e6_generics/IterableToStringTest.java).

Write a generic static method returning a string representing all the elements of an object
implementing the `Iterable<E>` interface. Append each element followed by a comma and a space, including the last element, as shown below. Return an empty string for an empty iterable.

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

### Exercise 2 — Append

Source: [Append.java](Append.java). Tests: [AppendTest.java](../../../../../../test/java/com/nbicocchi/exercises/e6_generics/AppendTest.java).

Write a generic static method that appends the elements of one list to another list of the same type.

Examples:

* append([], [1, 2]) → [1, 2]
* append([1, 2], [3, 4]) → [1, 2, 3, 4]
* append(["a", "c"], ["c", "d"]) → ["a", "c", "c", "d"]

The examples show the destination list after the call; the method modifies it in place and returns void.

The method has the following prototype:

```java
public static <T> void append(List<T> dst, List<T> src);
```

where:

* **dst** is the destination list to which values are appended.
* **src** is the source of the values.

---

### Exercise 3 — Reverse

Source: [Reverse.java](Reverse.java). Tests: [ReverseTest.java](../../../../../../test/java/com/nbicocchi/exercises/e6_generics/ReverseTest.java).

Write a generic static method to reverse a list (see List.get() and List.set() methods).

Examples:

* reverse([1, 2]) → [2, 1]
* reverse([1, 2, 3, 4]) → [4, 3, 2, 1]
* reverse(["hello", "world"]) → ["world", "hello"]

The examples show the list contents after the call; the method modifies the list in place and returns void.

The method has the following prototype:

```java
public static <T> void reverse(List<T> list);
```

where:

* **list** is the list to be reversed.

---

### Exercise 4 — CountOccurrences

Source: [CountOccurrences.java](CountOccurrences.java). Tests: [CountOccurrencesTest.java](../../../../../../test/java/com/nbicocchi/exercises/e6_generics/CountOccurrencesTest.java).

Write a generic static method counting all occurrences of a specific item of type T within a T[]. If item is null, the method should return the number of null values within the array. Write two versions of the same method: the former in plain Java, the latter using *Objects.equals()* which provides null-safety.

Examples:

* countOccurrencesPlain([1, 2, 2, 2], 2) → 3
* countOccurrencesPlain([null, null, 3, 4], null) → 2
* countOccurrencesPlain(["hello", "world", null, null], "hello") → 1

The same results are expected from `countOccurrencesObjects`.

The methods have the following prototypes:

```java
public static <T> int countOccurrencesPlain(T[] src, T item);
public static <T> int countOccurrencesObjects(T[] src, T item);
```

where:

* **src** is the array to be searched within.
* **item** is the item to be searched.

---

### Exercise 5 — Pair

Source: [Pair.java](Pair.java). Tests: [PairTest.java](../../../../../../test/java/com/nbicocchi/exercises/e6_generics/PairTest.java).

Define a generic class `Pair<K, V>` whose two components may have different types. The class must have two internal attributes named *first* and *second* of types K and V, respectively. A constructor, getters, and setters are also required.


```java
public class Pair<K, V> {
    //...
}
```

---

### Exercise 6 — PairSameType

Source: [PairSameType.java](PairSameType.java). Tests: [PairSameTypeTest.java](../../../../../../test/java/com/nbicocchi/exercises/e6_generics/PairSameTypeTest.java).

Create a separate PairSameType class based on Pair.java, with both components using the same type parameter. Add a method `void swap()` that swaps the first and second components.


```java
public class PairSameType<T> {
    //...
    public void swap() {
        // Implement this method.
    }
    //...
}
```

---

### Exercise 7 — PairUtil

Source: [PairUtil.java](PairUtil.java). Tests: [PairUtilTest.java](../../../../../../test/java/com/nbicocchi/exercises/e6_generics/PairUtilTest.java).

Write a generic static method whose argument is a Pair object. The method returns a new pair,
with the first and second components swapped, leaving the original pair unchanged. The method has the following prototype:

```java
public static <K, V> Pair<V, K> swap(Pair<K, V> src);
```

where:

* **src** is a generic Pair of two objects with different types.

---

### Exercise 8 — MapToPairs

Source: [MapToPairs.java](MapToPairs.java). Tests: [MapToPairsTest.java](../../../../../../test/java/com/nbicocchi/exercises/e6_generics/MapToPairsTest.java).

Write a generic static method that, given a `Map<K, V>`, returns a `List<Pair<K, V>>` of the key/value pairs in the map (see Map.entrySet()). The method has the following prototype:

```java
public static <K, V> List<Pair<K, V>> mapToPairs(Map<K, V> src);
```

where:

* **src** is a generic key/value map.

---

### Exercise 9 — Measure

Source: [Measure.java](Measure.java). Tests: [MeasureTest.java](../../../../../../test/java/com/nbicocchi/exercises/e6_generics/MeasureTest.java).

Transform the Measurer interface below into a generic interface. In Measure, implement two static generic methods returning the array elements with the greatest and smallest measured values, respectively. The `measure()` method maps each element to a double; return the selected element, not its measured value. Assume the array is non-empty and measurements are not NaN.

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

* **array** is the array to search for the minimum and maximum
* **measurer** is an object implementing the `Measurer<T>` interface

---

## Bounds and wildcards

### Exercise 10 — Divide

Source: [Divide.java](Divide.java). Tests: [DivideTest.java](../../../../../../test/java/com/nbicocchi/exercises/e6_generics/DivideTest.java).

Write a generic static method returning the floating-point quotient of two Number objects. The arguments may have different concrete types: type inference can choose a common supertype satisfying the Number bound. Use their double values for division. Read more about [type inference](https://docs.oracle.com/javase/tutorial/java/generics/genTypeInference.html).

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

* **a** is the dividend.
* **b** is the divisor.

---

### Exercise 11 — AppendBounded

Source: [AppendBounded.java](AppendBounded.java). Tests: [AppendBoundedTest.java](../../../../../../test/java/com/nbicocchi/exercises/e6_generics/AppendBoundedTest.java).

Modify the exercise Append.java so that *src* can contain elements of a subclass. For
example, if Student extends Person, people is a list of Person objects, and students is a list of Student objects, then append(people, students)
should compile but append(students, people) should not. The prototype is not reported because it is the answer.

---

### Exercise 12 — BinarySearch

Source: [BinarySearch.java](BinarySearch.java). Tests: [BinarySearchTest.java](../../../../../../test/java/com/nbicocchi/exercises/e6_generics/BinarySearchTest.java).

Binary search is one of the most popular algorithms. It finds the position of a target value within a sorted array. Write a generic static method implementing binary search within a T[], where T is any Comparable type. The method returns -1 if the target value is not found.

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

### Exercise 13 — MinMax

Source: [MinMax.java](MinMax.java). Tests: [MinMaxTest.java](../../../../../../test/java/com/nbicocchi/exercises/e6_generics/MinMaxTest.java).

Write two generic static methods returning the maximum and minimum elements of a non-empty list according to a supplied comparator (see `java.util.Comparator<T>`). The methods have the following prototypes:

```java
public static <T> T max(List<T> list, Comparator<? super T> cmp);
public static <T> T min(List<T> list, Comparator<? super T> cmp);
```

where:

* **list** is the list to search for the minimum and maximum
* **cmp** is an object implementing the Comparator interface

---

## Putting generics into practice

### Exercise 14 — mycollections

Source framework: [e4_collections.mycollections](../e4_collections/mycollections/). Implement the generic version as requested below; no dedicated implementation or tests are currently supplied for this exercise.

Implement a generic version of the minimalist collection framework contained within the
*com.nbicocchi.exercises.e4_collections.mycollections* package.

---
