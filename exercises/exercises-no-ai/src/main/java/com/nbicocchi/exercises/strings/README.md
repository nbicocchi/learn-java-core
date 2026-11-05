# Object-Oriented Programming - DIEF/UNIMORE

## Java Exercises (Strings)

**[Concatenate.java]** Write a method accepting a String[] returning a single string representing the concatenation of all strings of the array (see StringBuilder class).

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

**[Reverse.java]** Write two methods accepting a string and returning the reversed string. The first one has to use only String's methods (see String.charAt()), while the second one has to use StringBuilder's methods (see StringBuilder.reverse()).

Examples:

* reverse("String test") → "tset gnirtS"
* reverse("John Doe") → "eoD nhoJ"
* reverse("Hello World!") → "!dlroW olleH"

Methods have the following prototype:

```java
public static String reverseCharAt(String string);
public static String reverseStringBuilder(String string);
```

where:

* **string** is the string to be reversed

---

**[RemoveFirstTwoChars.java]** Write two methods accepting a string and returning the same string without the first two characters. The first one has to use String.charAt(), while the second one has to use StringBuilder.delete().

Examples:

* removeFirstTwoChars("Hello World!") → "llo World!"
* removeFirstTwoChars("No") → ""
* removeFirstTwoChars("Y") → ""

The methods have the following prototype:

```java
public static String removeFirstTwoCharsCharAt(String string);
public static String removeFirstTwoCharsStringBuilder(String string);
```

where:

* **string** is the string to be processed

---

**[RemoveFirstTwoCharsIf.java]** Write two methods accepting a string and returning the same string without the first two characters. Keep the first character if it is 'H' and keep the second character if it is 'e'. The first method has to use String.charAt(), while the second one has to use StringBuilder.deleteCharAt().

Examples:

* removeFirstTwoCharsIf("Hello World!") → "Hello World!"
* removeFirstTwoCharsIf("Good World!") → "od World!"
* removeFirstTwoCharsIf("Ho") → "H"
* removeFirstTwoCharsIf("Ne") → "e"
* removeFirstTwoCharsIf("Y") → ""

The methods have the following prototype:

```java
public static String removeFirstTwoCharsIfCharAt(String string);
public static String removeFirstTwoCharsIfStringBuilder(String string);
```

where:

* **string** is the string to be processed

---

**[GoodAtTheBeginning.java]** Write a method accepting a string and returning a boolean value. The function returns
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

* **string** is the string to be processed

---

**[GoodAroundTheBeginning.java]** Write a method accepting a string and returning true if "good" appears either at index 0 or 1 of the given string (see String.startsWith() method).

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

* **string** is the string to be processed

---

**[RemoveFirstLast.java]** Write a method accepting a string and returning the same string trimmed of the first and
last characters if the first and last characters are equal. Otherwise, the original string is returned (see
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

* **string** is the string to be processed

---

**[DuplicateChars.java]** Write a method accepting a string and returning all recurring characters contained into the string as a char[]. More specifically, recurring characters have to be returned in alphabetical order (see String.valueOf(), String.contains(), String.toCharArray()). For example: headmistressship -> [e,h,i,s].

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

* **string** is the string to be processed

---

**[Palindrome.java]** Write a method accepting a string and returning true if the string is a palindrome, ignoring case considerations (see StringBuilder class).

Examples:

* palindrome("whistleblower") → false
* palindrome("radar") → true
* palindrome("r") → true
* palindrome("") → true
* palindrome("RaceCar") → true

The method has the following prototype:

```java
public static boolean isPalindrome(String s);
```

where:

* **string** is the string to be processed

---

**[CountYZ.java]** Write a method accepting a string, and counting the number of words ending in 'y' or 'z' so the 'y' in "heavy" and the 'z' in "fez" count, but not the 'y' in "yellow" (not case-sensitive) (see java.util.StringTokenizer class, String.endsWith() method).

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

**[EqualIsNot.java]** Write a method accepting a string, and returning true if the number of appearances of "is" anywhere in the string is equal to the number of appearances of "not" anywhere in the string (case-sensitive) (See String.indexOf()).

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

**[SumDigits.java]** Given a string, return the sum of the digits 0-9 that appear in the string, ignoring all other characters. Return 0 if there are no digits in the string. (see Character class)

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

**[HammingDistance.java]** In information theory, the [Hamming distance](https://en.wikipedia.org/wiki/Hamming_distance) between two strings **of equal length** is the number of positions at which the corresponding symbols are different. Given two strings, return their Hamming distance. If the two strings have different lengths, return -1.

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
