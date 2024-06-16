# Object-Oriented Programming - DIEF/UNIMORE

## Java Exercises (Arrays)

**[CopyArray.java]** Write a method accepting a double[] and returning a copy of the initial double[] (see
System.arraycopy()).

Examples:

* copyArray([1, 2, 3]) → [1, 2, 3]
* copyArray([2, 1, 1]) → [2, 1, 1]

The method has the following prototype:

```java
public static double[] copyArray(double[] v);
```

where:

* **v** is the array to be copied.

---

**[FillArray.java]** Write a method returning a double[] already filled with values (see java.util.Arrays,
java.util.random.RandomGenerator).

Examples:

* fillArray(3, 0, false) → [0, 0, 0]
* fillArray(5, 1, false) → [1, 1, 1, 1, 1]
* fillArray(5, 1, true) → [0.99, 1.02, 1.01, 0.97, 0.95]
* fillArray(3, 100, true) → [102.45, 99.33, 96.34]

The method has the following prototype:

```java
public static double[] fillArray(int size, double value, boolean addNoise);
```

where:

* **size** is the size of the new array.
* **value** is the value to be assigned to all the elements of the new array.
* **addNoise** if true, all the elements have to be randomized between plus or minus 5 percent of **value**.

---

**[DivideArray.java]** Write a method accepting a double[] and a double value returning a different double[]
representing the original double[] divided by factor.

Examples:

* divideArray([1, 1, 1], 2.0) → [0.5, 0.5, 0.5]
* divideArray([2, 1, 1], 1.0) → [2.0, 1.0, 1.0]
* divideArray([10, 10], 10.0) → [1.0, 1.0]

The method has the following prototype:

```java
public static double[] divideArray(double[] v, double factor);
```

where:

* **v** is the double[] array to be processed.
* **factor** is the divisor to be used.

---

**[DivideArrays.java]** Write a method accepting two double[] returning a different double[] representing the first array divided by the second array. Every element of the first array is divided by the corresponding element of the second array. The two arrays must have the same size. Returns an empty array otherwise.

Examples:

* divideArrays([1, 1, 1], [3, 3, 3]) → [0.33, 0.33, 0.33]
* divideArrays([2, 1, 1], [10, 10, 10]) → [0.2, 0.1, 0.1]
* divideArrays([10, 10], [5, 5]) → [2.0, 2.0]
* divideArrays([10, 10], [5, 5, 5]) → []

The method has the following prototype:

```java
public static double[] divideArrays(double[] v1, double[] v2);
```

where:

* **v1** is the array of the dividends.
* **v2** is the array of the divisors.

---

**[Fibonacci.java]** Write a method accepting an int and returning a long[] composed of the first n numbers of the
Fibonacci series.

Examples:

* fibonacci(1) → [0]
* fibonacci(2) → [0, 1]
* fibonacci(5) → [0, 1, 1, 2, 3]

The method has the following prototype:

```java
public static long[] fibonacci(int n);
```

where:

* **n** is the number of Fibonacci numbers to be generated.

---

**[BubbleSort.java]** Write two methods for ordering an int[]. The only difference between the two methods is that the
first one orders the original int[] directly and returns void, while the second one returns a sorted COPY of the
original int[] (you can use the Bubble Sort algorithm).

Examples:

* bubbleSort([6, 3, 4, 2]) → [2, 3, 4, 6]
* bubbleSort([9, 8, 7, 4]) → [4, 7, 8, 9]
* bubbleSort([1, 0, 0, 0]) → [0, 0, 0, 1]

The method has the following prototype:

```java
public static void bubbleSort(int[] v);
public static int[] bubbleSortCopy(int[] v);
```

where:

* **v** is the int[] array to be sorted.

---

**[MoveZerosEnd.java]** Write a method accepting an int[] eventually containing zeros and returning an int[] in which all the zeros are at the end of the array. Maintain the relative order of the other (non-zero) elements.

Examples:

* moveZeroEnd([0, 0, 4, 2]) → [4, 2, 0, 0]
* moveZeroEnd([9, 0, 0, 4]) → [9, 4, 0, 0]
* moveZeroEnd([1, 0, 0, 0]) → [1, 0, 0, 0]

The method has the following prototype:

```java
public static int[] moveZerosEnd(int[] v);
```

where:

* **v** is the int[] array to be processed.

---

**[Splitter.java]** Write a method accepting an int and returning the sequence of the digits as an int[].

Examples:

* 321 → [3, 2, 1]
* 123 → [1, 2, 3]

The method has the following prototype:

```java
public static int[] splitter(int input);
```

where:

* **input** is the integer number taken as input.

---

**[IsEveryWhere.java]** We'll say that a value is "everywhere" in an array if for every pair of adjacent elements in the
array, at least one of the pair is that value. Return true if the given value is everywhere in the array.

Examples:

* isEverywhere([1, 2, 1, 3], 1) → true
* isEverywhere([1, 2, 1, 3], 2) → false
* isEverywhere([1, 2, 1, 3, 4], 1) → false

The method has the following prototype:

```java
public static boolean isEveryWhere(int[] v, int value);
```

where:

* **v** is the int[] array to be processed.
* **value** is the value to be verified (if it is everywhere).

---

**[TenRun.java]** For each multiple of 10 in the given array, change all the values following it to be that multiple of
10, until encountering another multiple of 10. So {2, 10, 3, 4, 20, 5} yields {2, 10, 10, 10, 20, 20}.

Examples:

* tenRun([2, 10, 3, 4, 20, 5]) → [2, 10, 10, 10, 20, 20]
* tenRun([10, 1, 20, 2]) → [10, 10, 20, 20]
* tenRun([10, 1, 9, 20]) → [10, 10, 10, 20]

The method has the following prototype:

```java
public static int[] tenRun(int[] v);
```

where:

* **v** is the int[] array to be processed.

---

**[FizzBuzz.java]** Consider the series of numbers beginning at **start** and running up to but not including **end**. For example start=1 and end=5 gives the series 1, 2, 3, 4. Return a new String[] array (see Integer.toString()) containing:

* *Fizz* for multiples of 3;
* *Buzz* for multiples of 5;
* *FizzBuzz* for multiple of both 3 and 5;
* the String version of the number in all the other cases.

**start** has to be less than **end** and both must be greater or equal to 0. Otherwise, the method returns an empty String array.

Examples:

* fizzBuzz(1, 6) → ["1", "2", "Fizz", "4", "Buzz"]
* fizzBuzz(1, 8) → ["1", "2", "Fizz", "4", "Buzz", "Fizz", "7"]
* fizzBuzz(12, 17) → ["Fizz", "13", "14", "FizzBuzz", "16"]
* fizzBuzz(8, 1) → []

The method has the following prototype:

```java
public static String[] fizzBuzz(int start, int end);
```

where:

* **start** is the beginning of the number series (included)
* **end** is the end of the number series (excluded)

---

**[MatchUp.java]** Given two int[] v1 and v2 of the same length, for every element in v1, consider the corresponding
element in v2 (at the same index). Return the count of the number of times that the two elements differ by 2 or less,
but are not equal.

Examples:

* matchUp([1, 2, 3], [2, 3, 10]) → 2
* matchUp([1, 2, 3], [2, 3, 5]) → 3
* matchUp([1, 2, 3], [2, 3, 3]) → 2

The method has the following prototype:

```java
public static int matchUp(int[] v1, int[] v2);
```

where:

* **v1** is the first array
* **v2** is the second array

---

**[CanBalance.java]** Given a non-empty array, return true if there is a place to split the array so that the sum of the
numbers on one side is equal to the sum of the numbers on the other side.

Examples:

* canBalance([1, 1, 1, 2, 1]) → true
* canBalance([2, 1, 1, 2, 1]) → false
* canBalance([10, 10]) → true

The method has the following prototype:

```java
public static boolean canBalance(int[] v);
```

where:

* **v** is the array to be evaluated

---

**[SearchArray.java]** Given a String[] and a String return true if the String is contained within the String[].
If the String is not present return false. Write two different versions:
(a) uses brute-force search (i.e., which is slow! because tests all elements of the array);
(b) sorts the array and uses binary search (which is much faster!) (see java.util.Arrays)

Both versions should not modify the original array!

Examples:

* containsBruteForce(["car", "boat", "bike", "plane"], "bike") → true
* containsBruteForce(["car", "boat", "bike", "plane"], "helicopter") → false
* containsBinary(["car", "boat", "bike", "plane"], "bike") → true
* containsBinary(["car", "boat", "bike", "plane"], "helicopter") → false

The method has the following prototype:

```java
public static boolean containsBruteForce(String[] strings, String searched);
public static boolean containsBinary(String[] strings, String searched);
```

where:

* **strings** is the array
* **searched** is the string to be searched

---

**[DeepCopy.java]** Write a method accepting an int[][] and returning a deep copy of the original. Deep copies differ from shallow copies in that the whole multidimensional array is duplicated instead of the first tier of pointers only.

The method has the following prototype:

```java
public static int[][] deepCopy(int[][] original);
```
