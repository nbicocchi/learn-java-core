# E2 — Java Basics

**Java 25 · Practice without AI-generated code**

Implement the exercises yourself and validate your work with the supplied tests where available. The sections follow the order of the covered course topics; exercises may combine earlier skills.

[Course slides and module index](../../../../../../../../../README.md#slides) · [AI exercises](../../../../../../../../exercises-ai/E2%20-%20Java%20Basics.md)

## Contents

- [Types and expressions](#types-and-expressions)
- [Control flow and methods](#control-flow-and-methods)
- [Arrays](#arrays)
- [Strings](#strings)
- [Text input and output](#text-input-and-output)

## Running tests

From `exercises/exercises-no-ai/`:

```sh
mvn test
mvn -Dtest="com.nbicocchi.exercises.e2_basics.**" test
```

## Types and expressions

### Exercise 1 — InterestRate

Source: [InterestRate.java](InterestRate.java). No automated tests are currently supplied for this exercise.

Write a program that prints the balance of an account after the first, second, and third year. 
The account has an initial balance of $1,000 and earns 5 percent interest per year.

---

## Control flow and methods

### Exercise 2 — MonkeyTrouble

Source: [MonkeyTrouble.java](MonkeyTrouble.java). No automated tests are currently supplied for this exercise.

We have two monkeys, a and b, and two boolean values (aSmile and bSmile) indicate if each is smiling. We are in trouble if they are both smiling or if neither of them is smiling. 
Write a program asking the user if monkeys are smiling and print an alert if we are in trouble (see java.util.Scanner).

---

### Exercise 3 — EstimatePI

Source: [EstimatePI.java](EstimatePI.java). No automated tests are currently supplied for this exercise.

It is possible to get an estimate of the mathematical constant π by using a random process. The idea is based on the fact that the area of a circle of radius 1 is equal to π, and the area of a quarter of that circle is π/4. 

```text
 <-- 1 -->
 ---------
|x        |
|     x   |      
|       x |
|        x|  
 ---------    
 ```

The area of the square is 1, while the area of the part inside the circle is π/4. If we choose a point in the square at random, the probability that it is inside the circle is π/4. If we choose N points in the square at random (see Math.random()), and if C of them are inside the circle, we expect the fraction C/N of points that fall inside the circle to be about π/4. If N is large enough, we can expect 4 * C/N to be a good estimate for π.

Write a program estimating π with increasing numbers of trials, as illustrated below (see System.out.printf()). Results vary between runs; increasing the sample size improves accuracy statistically, not necessarily at every step.

```text
[trials=2^3] [error=27.32395447pc] [estimate=4.0000000000000000]
[trials=2^4] [error=11.40846016pc] [estimate=3.5000000000000000]
[trials=2^5] [error=3.45071301pc] [estimate=3.2500000000000000]
[trials=2^6] [error=1.46127622pc] [estimate=3.1875000000000000]
[trials=2^7] [error=-0.52816057pc] [estimate=3.1250000000000000]
[trials=2^8] [error=1.46127622pc] [estimate=3.1875000000000000]
[trials=2^9] [error=6.18618859pc] [estimate=3.3359375000000000]
[trials=2^10] [error=0.09353843pc] [estimate=3.1445312500000000]
[trials=2^11] [error=0.77740732pc] [estimate=3.1660156250000000]
[trials=2^12] [error=-0.12405622pc] [estimate=3.1376953125000000]
```

---

## Arrays

### Exercise 4 — CopyArray

Source: [CopyArray.java](CopyArray.java). Tests: [CopyArrayTest.java](../../../../../../test/java/com/nbicocchi/exercises/e2_basics/CopyArrayTest.java).

Write a method accepting a double[] and returning a copy of the initial double[] (see
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

### Exercise 5 — FillArray

Source: [FillArray.java](FillArray.java). Tests: [FillArrayTest.java](../../../../../../test/java/com/nbicocchi/exercises/e2_basics/FillArrayTest.java).

Write a method returning a double[] already filled with values (see java.util.Arrays,
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
* **addNoise** indicates whether each element should vary randomly within ±5% of **value**. The noisy outputs above are illustrative.

---

### Exercise 6 — DivideArray

Source: [DivideArray.java](DivideArray.java). Tests: [DivideArrayTest.java](../../../../../../test/java/com/nbicocchi/exercises/e2_basics/DivideArrayTest.java).

Write a method accepting a double[] and a double value returning a different double[]
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

### Exercise 7 — DivideArrays

Source: [DivideArrays.java](DivideArrays.java). Tests: [DivideArraysTest.java](../../../../../../test/java/com/nbicocchi/exercises/e2_basics/DivideArraysTest.java).

Write a method accepting two double[] returning a different double[] representing the first array divided by the second array. Every element of the first array is divided by the corresponding element of the second array. The two arrays must have the same size. Returns an empty array otherwise.

Examples:

* divideArrays([1, 1, 1], [3, 3, 3]) → [0.3333333333333333, 0.3333333333333333, 0.3333333333333333]
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

### Exercise 8 — Fibonacci

Source: [Fibonacci.java](Fibonacci.java). Tests: [FibonacciTest.java](../../../../../../test/java/com/nbicocchi/exercises/e2_basics/FibonacciTest.java).

Write a method accepting an int and returning a long[] composed of the first n numbers of the
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

### Exercise 9 — BubbleSort

Source: [BubbleSort.java](BubbleSort.java). Tests: [BubbleSortTest.java](../../../../../../test/java/com/nbicocchi/exercises/e2_basics/BubbleSortTest.java).

Write two methods for ordering an int[]. The only difference between the two methods is that the
first one orders the original int[] directly and returns void, while the second one returns a sorted COPY of the
original int[] (you can use the Bubble Sort algorithm).

Examples (the arrays on the right show the final contents, not the return value of the void method):

* bubbleSort([6, 3, 4, 2]) → [2, 3, 4, 6]
* bubbleSort([9, 8, 7, 4]) → [4, 7, 8, 9]
* bubbleSort([1, 0, 0, 0]) → [0, 0, 0, 1]

The methods have the following prototypes:

```java
public static void bubbleSort(int[] v);
public static int[] bubbleSortCopy(int[] v);
```

where:

* **v** is the int[] array to be sorted.

---

### Exercise 10 — MoveZerosEnd

Source: [MoveZerosEnd.java](MoveZerosEnd.java). Tests: [MoveZerosEndTest.java](../../../../../../test/java/com/nbicocchi/exercises/e2_basics/MoveZerosEndTest.java).

Write a method accepting an int[] that may contain zeros and returning an int[] in which all the zeros are at the end of the array. Maintain the relative order of the other (non-zero) elements.

Examples:

* moveZerosEnd([0, 0, 4, 2]) → [4, 2, 0, 0]
* moveZerosEnd([9, 0, 0, 4]) → [9, 4, 0, 0]
* moveZerosEnd([1, 0, 0, 0]) → [1, 0, 0, 0]

The method has the following prototype:

```java
public static int[] moveZerosEnd(int[] v);
```

where:

* **v** is the int[] array to be processed.

---

### Exercise 11 — Splitter

Source: [Splitter.java](Splitter.java). Tests: [SplitterTest.java](../../../../../../test/java/com/nbicocchi/exercises/e2_basics/SplitterTest.java).

Write a method accepting an int and returning the sequence of the digits as an int[].

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

### Exercise 12 — IsEveryWhere

Source: [IsEveryWhere.java](IsEveryWhere.java). Tests: [IsEveryWhereTest.java](../../../../../../test/java/com/nbicocchi/exercises/e2_basics/IsEveryWhereTest.java).

We'll say that a value is "everywhere" in an array if for every pair of adjacent elements in the
array, at least one of the pair is that value. Return true if the given value is everywhere in the array.

Examples:

* isEveryWhere([1, 2, 1, 3], 1) → true
* isEveryWhere([1, 2, 1, 3], 2) → false
* isEveryWhere([1, 2, 1, 3, 4], 1) → false

The method has the following prototype:

```java
public static boolean isEveryWhere(int[] v, int value);
```

where:

* **v** is the int[] array to be processed.
* **value** is the value to be verified (if it is everywhere).

---

### Exercise 13 — TenRun

Source: [TenRun.java](TenRun.java). Tests: [TenRunTest.java](../../../../../../test/java/com/nbicocchi/exercises/e2_basics/TenRunTest.java).

For each multiple of 10 in the given array, change all the values following it to be that multiple of
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

### Exercise 14 — FizzBuzz

Source: [FizzBuzz.java](FizzBuzz.java). Tests: [FizzBuzzTest.java](../../../../../../test/java/com/nbicocchi/exercises/e2_basics/FizzBuzzTest.java).

Consider the series of numbers beginning at **start** and running up to but not including **end**. For example start=1 and end=5 gives the series 1, 2, 3, 4. Return a new String[] array (see Integer.toString()) containing:

* *Fizz* for multiples of 3;
* *Buzz* for multiples of 5;
* *FizzBuzz* for multiples of both 3 and 5;
* the String version of the number in all the other cases.

**start** has to be less than **end** and both must be greater than or equal to 0. Otherwise, the method returns an empty String array.

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

### Exercise 15 — MatchUp

Source: [MatchUp.java](MatchUp.java). Tests: [MatchUpTest.java](../../../../../../test/java/com/nbicocchi/exercises/e2_basics/MatchUpTest.java).

Given two int[] v1 and v2 of the same length, for every element in v1, consider the corresponding
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

### Exercise 16 — CanBalance

Source: [CanBalance.java](CanBalance.java). Tests: [CanBalanceTest.java](../../../../../../test/java/com/nbicocchi/exercises/e2_basics/CanBalanceTest.java).

Given a non-empty array, return true if it can be split into two non-empty parts so that the sum of the
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

### Exercise 17 — SearchArray

Source: [SearchArray.java](SearchArray.java). Tests: [SearchArrayTest.java](../../../../../../test/java/com/nbicocchi/exercises/e2_basics/SearchArrayTest.java).

Given a String[] and a String return true if the String is contained within the String[].
If the String is not present return false. Write two different versions:
(a) uses linear search, examining elements until a match is found or the array ends;
(b) sorts a copy of the array and uses binary search (see java.util.Arrays).

Neither version should modify the original array. Linear search takes O(n) time in the worst case. Binary search takes O(log n) on an already sorted array, but sorting the copy first makes the second version O(n log n) overall for a single query.

Examples:

* containsBruteForce(["car", "boat", "bike", "plane"], "bike") → true
* containsBruteForce(["car", "boat", "bike", "plane"], "helicopter") → false
* containsBinary(["car", "boat", "bike", "plane"], "bike") → true
* containsBinary(["car", "boat", "bike", "plane"], "helicopter") → false

The methods have the following prototypes:

```java
public static boolean containsBruteForce(String[] strings, String searched);
public static boolean containsBinary(String[] strings, String searched);
```

where:

* **strings** is the array
* **searched** is the string to be searched

---

### Exercise 18 — DeepCopy

Source: [DeepCopy.java](DeepCopy.java). Tests: [DeepCopyTest.java](../../../../../../test/java/com/nbicocchi/exercises/e2_basics/DeepCopyTest.java).

Write a method accepting an int[][] and returning a deep copy of the original. Duplicate both the outer array and each row, rather than copying only the outer array and sharing references to the original rows.

The method has the following prototype:

```java
public static int[][] deepCopy(int[][] original);
```

---

## Strings

### Exercise 19 — Concatenate

Source: [Concatenate.java](Concatenate.java). Tests: [ConcatenateTest.java](../../../../../../test/java/com/nbicocchi/exercises/e2_basics/ConcatenateTest.java).

Write a method accepting a String[] returning a single string representing the concatenation of all strings of the array (see StringBuilder class).

Examples:

* concatenate(["Hello", " ", "World!"]) → "Hello World!"
* concatenate(["H", "e", "llo", " ", "World!"]) → "Hello World!"
* concatenate(["Hello World", "!"]) → "Hello World!"

The method has the following prototype:

```java
public static String concatenate(String[] strings);
```

where:

* **strings** is the String array to be concatenated

---

### Exercise 20 — Reverse

Source: [Reverse.java](Reverse.java). Tests: [ReverseTest.java](../../../../../../test/java/com/nbicocchi/exercises/e2_basics/ReverseTest.java).

Write two methods accepting a string and returning the reversed string. The first one has to use only String's methods (see String.charAt()), while the second one has to use StringBuilder's methods (see StringBuilder.reverse()).

Examples:

* reverseCharAt("String test") → "tset gnirtS"
* reverseCharAt("John Doe") → "eoD nhoJ"
* reverseCharAt("Hello World!") → "!dlroW olleH"

The same results are expected from `reverseStringBuilder`. The methods have the following prototypes:

```java
public static String reverseCharAt(String string);
public static String reverseStringBuilder(String string);
```

where:

* **string** is the string to be reversed

---

### Exercise 21 — RemoveFirstTwoChars

Source: [RemoveFirstTwoChars.java](RemoveFirstTwoChars.java). Tests: [RemoveFirstTwoCharsTest.java](../../../../../../test/java/com/nbicocchi/exercises/e2_basics/RemoveFirstTwoCharsTest.java).

Write two methods accepting a string and returning a new string without the first two characters. Return an empty string if the input has fewer than two characters. The first method must use String.charAt(), while the second must use StringBuilder.delete().

Examples:

* removeFirstTwoCharsCharAt("Hello World!") → "llo World!"
* removeFirstTwoCharsCharAt("No") → ""
* removeFirstTwoCharsCharAt("Y") → ""

The same results are expected from `removeFirstTwoCharsStringBuilder`. The methods have the following prototypes:

```java
public static String removeFirstTwoCharsCharAt(String string);
public static String removeFirstTwoCharsStringBuilder(String string);
```

where:

* **string** is the string to be processed

---

### Exercise 22 — RemoveFirstTwoCharsIf

Source: [RemoveFirstTwoCharsIf.java](RemoveFirstTwoCharsIf.java). Tests: [RemoveFirstTwoCharsIfTest.java](../../../../../../test/java/com/nbicocchi/exercises/e2_basics/RemoveFirstTwoCharsIfTest.java).

Write two methods accepting a string and returning the same string without the first two characters. Keep the first character if it is 'H' and keep the second character if it is 'e'. The first method has to use String.charAt(), while the second one has to use StringBuilder.deleteCharAt().

Examples:

* removeFirstTwoCharsIfCharAt("Hello World!") → "Hello World!"
* removeFirstTwoCharsIfCharAt("Good World!") → "od World!"
* removeFirstTwoCharsIfCharAt("Ho") → "H"
* removeFirstTwoCharsIfCharAt("Ne") → "e"
* removeFirstTwoCharsIfCharAt("Y") → ""

The same results are expected from `removeFirstTwoCharsIfStringBuilder`. The methods have the following prototypes:

```java
public static String removeFirstTwoCharsIfCharAt(String string);
public static String removeFirstTwoCharsIfStringBuilder(String string);
```

where:

* **string** is the string to be processed

---

### Exercise 23 — GoodAtTheBeginning

Source: [GoodAtTheBeginning.java](GoodAtTheBeginning.java). Tests: [GoodAtTheBeginningTest.java](../../../../../../test/java/com/nbicocchi/exercises/e2_basics/GoodAtTheBeginningTest.java).

Write a method accepting a string and returning a boolean value. The function returns
true if "good" appears at the beginning of the string (see String.charAt() method).

Examples:

* goodAtTheBeginning("good Sweet") → true
* goodAtTheBeginning(" good Sweet") → false
* goodAtTheBeginning("goo") → false

The method has the following prototype:

```java
public static boolean goodAtTheBeginning(String s);
```

where:

* **s** is the string to be processed

---

### Exercise 24 — GoodAroundTheBeginning

Source: [GoodAroundTheBeginning.java](GoodAroundTheBeginning.java). Tests: [GoodAroundTheBeginningTest.java](../../../../../../test/java/com/nbicocchi/exercises/e2_basics/GoodAroundTheBeginningTest.java).

Write a method accepting a string and returning true if "good" appears either at index 0 or 1 of the given string (see String.startsWith() method).

Examples:

* goodAroundTheBeginning("good Sweet") → true
* goodAroundTheBeginning("_good Sweet") → true
* goodAroundTheBeginning("__good Sweet") → false
* goodAroundTheBeginning("goo") → false

The method has the following prototype:

```java
public static boolean goodAroundTheBeginning(String s);
```

where:

* **s** is the string to be processed

---

### Exercise 25 — RemoveFirstLast

Source: [RemoveFirstLast.java](RemoveFirstLast.java). Tests: [RemoveFirstLastTest.java](../../../../../../test/java/com/nbicocchi/exercises/e2_basics/RemoveFirstLastTest.java).

Write a method accepting a string and returning the same string trimmed of the first and
last characters if the first and last characters are equal and the string has at least two characters. Otherwise, the original string is returned (see
String.substring(), String.charAt() methods).

Examples:

* removeFirstLast("google") → "google"
* removeFirstLast("aenema") → "enem"
* removeFirstLast("FF") → ""
* removeFirstLast("F") → "F"

The method has the following prototype:

```java
public static String removeFirstLast(String s);
```

where:

* **s** is the string to be processed

---

### Exercise 26 — DuplicateChars

Source: [DuplicateChars.java](DuplicateChars.java). Tests: [DuplicateCharsTest.java](../../../../../../test/java/com/nbicocchi/exercises/e2_basics/DuplicateCharsTest.java).

Write a method accepting a string and returning each recurring character once, in alphabetical order, as a char[] (see String.valueOf(), String.contains(), String.toCharArray()). For example: headmistressship → [e, h, i, s].

Examples:

* duplicateChars("whistleblower") → [e, l, w]
* duplicateChars("ss") → [s]
* duplicateChars("s") → []
* duplicateChars("") → []

The method has the following prototype:

```java
public static char[] duplicateChars(String input);
```

where:

* **input** is the string to be processed

---

### Exercise 27 — Palindrome

Source: [Palindrome.java](Palindrome.java). Tests: [PalindromeTest.java](../../../../../../test/java/com/nbicocchi/exercises/e2_basics/PalindromeTest.java).

Write a method accepting a string and returning true if the string is a palindrome, ignoring case considerations (see StringBuilder class).

Examples:

* isPalindrome("whistleblower") → false
* isPalindrome("radar") → true
* isPalindrome("r") → true
* isPalindrome("") → true
* isPalindrome("RaceCar") → true

The method has the following prototype:

```java
public static boolean isPalindrome(String s);
```

where:

* **s** is the string to be processed

---

### Exercise 28 — CountYZ

Source: [CountYZ.java](CountYZ.java). Tests: [CountYZTest.java](../../../../../../test/java/com/nbicocchi/exercises/e2_basics/CountYZTest.java).

Write a method counting the words ending in 'y' or 'z', ignoring case. Words are whitespace-separated tokens, as defined by the default delimiters of StringTokenizer. Thus, "heavy" and "fez" count, but "yellow" does not (see java.util.StringTokenizer and String.endsWith()).

Examples:

* countYZ("fez day") → 2
* countYZ("day fez") → 2
* countYZ("day fyyyz") → 2

The method has the following prototype:

```java
public static int countYZ(String string);
```

where:

* **string** is the string to be processed

---

### Exercise 29 — EqualIsNot

Source: [EqualIsNot.java](EqualIsNot.java). Tests: [EqualIsNotTest.java](../../../../../../test/java/com/nbicocchi/exercises/e2_basics/EqualIsNotTest.java).

Write a method accepting a string, and returning true if the number of appearances of "is" anywhere in the string is equal to the number of appearances of "not" anywhere in the string (case-sensitive) (See String.indexOf()).

Examples:

* equalIsNot("is not") → true
* equalIsNot("This is not") → false
* equalIsNot("This is notnot") → true
* equalIsNot("noisxxnotyynotxisi") → true

The method has the following prototype:

```java
public static boolean equalIsNot(String string);
```

where:

* **string** is the string to be processed

---

### Exercise 30 — SumDigits

Source: [SumDigits.java](SumDigits.java). Tests: [SumDigitsTest.java](../../../../../../test/java/com/nbicocchi/exercises/e2_basics/SumDigitsTest.java).

Given a string, return the sum of the digits 0-9 that appear in the string, ignoring all other characters. Return 0 if there are no digits in the string. (see Character class)

Examples:

* sumDigits("aa1bc2d3") → 6
* sumDigits("aa11b33") → 8
* sumDigits("Chocolate") → 0

The method has the following prototype:

```java
public static int sumDigits(String string);
```

where:

* **string** is the string to be processed

---

### Exercise 31 — HammingDistance

Source: [HammingDistance.java](HammingDistance.java). Tests: [HammingDistanceTest.java](../../../../../../test/java/com/nbicocchi/exercises/e2_basics/HammingDistanceTest.java).

In information theory, the [Hamming distance](https://en.wikipedia.org/wiki/Hamming_distance) between two strings **of equal length** is the number of positions at which the corresponding symbols are different. Given two strings, return their Hamming distance. If the two strings have different lengths, return -1.

Examples:

* hammingDistance("nicola", "n1cola") → 1
* hammingDistance("nicola", "nicola") → 0
* hammingDistance("nicola", "zzzzzz") → 6
* hammingDistance("nicola", "nicol") → -1
* hammingDistance("", "nicol") → -1

The method has the following prototype:

```java
public static int hammingDistance(String s1, String s2);
```

where:

* **s1** is the first string
* **s2** is the second string

---

## Text input and output

### Exercise 32 — XMasTree

Source: [XMasTree.java](XMasTree.java). No automated tests are currently supplied for this exercise.

Print to screen the little story below. 
Remember: strings can be [multi-line](https://www.baeldung.com/java-multiline-string)!

```text
+--------------[The Christmass Child Eater Monster]-------------------+
|                               /\                                    |
|                               ||                                    |
|                               ||                                    |
|                              //\\\\                                 |
|                             /////<>\________/\                      |
|                            /////~~~ _________:)                     |
|                           ///// ())/\/\/\/\/\/                      |
|                          ////()()((            /)                   |
|         _____           ///)()()()(\/\/\/\/\/\//\   ________________|
|        /(__/           //()()()()()(`````````````) (AIIIIIIiiieee!!!)
|       /)/             /()()()()()()_____________//  """/""""""""""""|
|      /(|             /()()(/####\ /          //// \|||/  /)         |
|     /()|           ,"()()((######)          ///   (O.O) //          |
|    |()()\---------")()()()(\####/#\        //    __|o|_//           |
|    |)()()()()()()()()()()()(|##|\##\      /     //|###|/            |
|     \)()()()()()()()()()()(/|##| \##\  ___     // |=+=|             |
|      \)()()()()()()()()()(/ |##|  \##\/==     (/  || ||             |
|       \)()()()()()()()(),' /####\_ \###/          || ||             |
|        ",_____________,'  (#######\\\#/          (_) (_)            |
+---------------------------------------------------------------------+
He eats kids, good or bad ones. They all taste the same to him. He comes
once a year and eats kids. Mostly retards and waterheads but he eats
some regular ones too. He likes to shrink himself and hide inside kids
pillows. Then he wait for kid to go to sleep, then *CRUNCH* he bites off
head. Yummy!!! I like head. He come Christmass eve and eat till full,
usualy 7-9 kids a year then he go to sleep and wait till next time.
Just because you aint scared of monster dont mean he wont eat ya.
Some times eats parents too, forgot to say that earlier.

                          Credits: Krogg <krogg.no.to.spam@inspace.net>
```

---

### Exercise 33 — GuessNumber

Source: [GuessNumber.java](GuessNumber.java). No automated tests are currently supplied for this exercise.

Write a program implementing a simple quiz game. In each round, the computer selects a new random integer from 0 to 3 inclusive, then asks the user to guess it. Continue after a wrong answer or a correct nonzero guess. The game ends only when both the selected number and the user's guess are 0 (see java.util.random.RandomGenerator and java.util.Scanner).

---

### Exercise 34 — TheRightPrice

Source: [TheRightPrice.java](TheRightPrice.java). No automated tests are currently supplied for this exercise.

The computer selects a random integer in [0, 100] representing the price of an imaginary item. Two users are asked to guess the price. The user whose guess is closer wins; if both guesses are equally close, declare a tie. Math.abs(n) returns the absolute value of a number (see java.util.random.RandomGenerator and java.util.Scanner).

---
