# Object Oriented Programming - DIEF/UNIMORE

## Java Exercises (Lists)

**[DownSize]** Write a method that removes every nth String from a ```List<String>```.

* downSize(["A", "B", "C", "D", "E", "F"], 2) -> ["A", "C", "E"] 
* downSize(["A", "B", "C", "D", "E", "F"], 3) -> ["A", "B", "D", "E"]

The method has the following prototype:

```
public static void downsize(List<String> list, int n);
```

where:

* **list** is the list to be downsized
* **n** represents the intensity of the downsizing


**[SortAccount.java]** Given the following class Account:

```
public static class Account {
    double amount;
    double interestRate;
    LocalDate duePayment;
    ...
}
```

Write three methods accepting a ```List<Account>``` and returning void.
Each method sorts the list based on one criterion (i.e., one attribute).

The methods have the following prototype:

```
public static void sortByAmount(List<Account> accounts);
public static void sortByInterestRate(List<Account> accounts);
public static void sortByDuePayment(List<Account> accounts);
```

where:

* **accounts** is the list of Account to be sorted.

---

**[SpeedTest.java]** Demonstrate that the position is which elements are added to a List impacts performance in a significant way.
Write two methods for inserting an element at the beginning (or end) of a List a certain number of times, returning the elapsed time.
Then, write a test for showing that ArrayList is faster than LinkedList when adding elements at the end, while the opposite 
is true when inserting elements at the beginning (see System.nanoTime()).

The methods have the following prototype:

```
public static long insertBeginning(List<String> list, String item, int times);
public static long insertEnd(List<String> list, String item, int times);
```

where:

* **list** is the list used for adding elements.
* **item** is the item to be added.
* **times** is the number of times to repeat the add operation.

## Java Exercises (Deque)

**[Reverse.java]** Write a method reversing the order of words in a sentence (represented as a ```List<String>```) using
an ArrayDeque as a stack (see .addLast() and .pollLast() methods). Each word has to be pushed to the stack and fetched
later. If the stack is behaving correctly, the order is reversed.

Examples:

* reverse(["Hello", "World!"]) → ["World!", "Hello"]
* reverse(["I", "am"]) → ["am", "I"]

The method has the following prototype:

```
public static List<String> reverse(List<String> sentence);
```

where:

* **sentence** is the list of words to be reversed.

---

## Java Exercises (Sets)

**[Intersection.java]** Write a method returning the intersection of two given sets of Integers (without using the Set.retainAll() method).

Examples:

* intersection([1,2,3], [4,5,6]) → []
* intersection([1,2,3], [3,4,5]) → [3]

The method has the following prototype:

```
public static Set<Integer> intersection(Set<Integer> first, Set<Integer> second);
```

where:

* **first** is the first Set.
* **second** is the second Set.

---

**[RecurringChars.java]** Write a method accepting a string and returning a set comprising all recurring characters of
the string.

Examples:

* recurringChars("Ciao") → []
* recurringChars("Hello") → ['l']
* recurringChars("Hello World!") → ['l', 'o']

The method has the following prototype:

```
public static Set<Character> recurringChars(String string);
```

where:

* **string** is the string to be processed.

---

**[RemoveDuplicates.java]**  Write a method to remove all duplicate elements from a ```List<Integer>```.

Examples:

* removeDuplicates([1,2,3,4,5]) → [1,2,3,4,5]
* removeDuplicates([1,1,1,2,2]) → [1,2]

The method has the following prototype:

```
public static List<Integer> removeDuplicates(List<Integer> list);
```

where:

* **list** is the list to be processed.

---

**[Eratosthenes.java]** Implement the sieve of Eratosthenes: a method for computing prime numbers, known to the ancient
Greeks.
This method will compute all prime numbers up to n. Choose an n. First insert all numbers from 1 to n into a set.
Then erase all multiples of 2 (except 2); that is, 4, 6, 8, 10, 12, . . . .
Erase all multiples of 3; that is, 6, 9, 12, 15, . . . . Go up to sqrt(n). Then return the set.

The method has the following prototype:

```
public static Set<Integer> eratosthenes(int n);
```

where:

* [1, n] is the range in which searching for primes.

---

**[WordAfterWord.java]** Download the [Ulysses, by James Joyce](https://github.com/laumann/ds/blob/master/hashing/books/) book.
Write a method, reading all the unique words of the book (all words have to be converted in lowercase), 
and sorting them in alphabetical order. Given a word, the method returns the next word (in alphabetical order)
contained within the book (See TreeSet.tailSet()).

The method has the following prototype:

```
public static String wordAfterWord(String filename, String word) throws IOException;
```

where:

* **filename** is the filename of the book.
* **string** is the word to be searched.

For reading the file and transforming it into a ```List<String>```, you can use:

```
public static List<String> getLines(String filename) throws IOException {
    return Files.readAllLines(Path.of(filename));
}
```

For splitting a line into a list of single words, you can use:

```
public static List<String> lineToWords(String line) {
    return Arrays.asList(line.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+"));
}
```


## Java Exercises (Maps)

**[MorseCode]** Write a static method accepting a String, converting it to lowercase, and returning its [Morse](https://en.wikipedia.org/wiki/Morse_code) translation. The 26 lowercase letters of the english alphabet have to be supported. 

You have to use a ```Map<Characher, String>``` as a conversion table (dot='.', dash='_'). If the input String contains characters not supported by the conversion table, IllegalArgumentException have to be thrown.

Examples:

* morseCode("hello") -> `.... . ._.. ._.. ___`
* morseCode("world") -> `.__ ___ ._. ._.. _..`
* morseCode("w0rld") -> IllegalArgumentException

The method has the following prototype:

```
public static String morseCode(String string);
```

where:

* **string** is the string to be converted in Morse.

---

**[ValuesKeys.java]** Write a static method accepting a ```Map<String, String>``` and returning true if some values have been used as keys as well.

The method has the following prototype:

```
public static boolean valuesKeys(Map<String, String> map);
```

where:

* **map** is the map to be checked.

---

**[InvertMap]** Write a static method accepting a ```Map<Integer, String>``` and returning a ```Map<String, Integer>``` in which the original keys and values have been inverted.
In case of repeated values (thus associated to multiple keys) you can select one of the keys.

Examples:

* invertMap({1: "Hello", 3: "World"}) → {"Hello": 1, "World": 3}
* invertMap({1: "Hello", 3: "Hello"}) → {"Hello": 3}

The method has the following prototype:

```
public static Map<String, Integer> invertMap(Map<Integer, String> src);
```

where:

* **src** is the map to be inverted.

---

**[WordFrequency.java]** Download
the [Ulysses, by James Joyce](https://github.com/laumann/ds/blob/master/hashing/books/) book.
Write a method, reading all the lines of the file and returning a ```Map<String, Integer>``` having all the unique words
of the book as keys, associated with the number of times they appeared.

The method has the following prototype:

```
public static Map<String, Integer> wordFrequency(String filename) throws IOException;
```

where:

* **filename** is the filename of the book.

For reading the file and transforming it into a ```List<String>```, you can use:

```
public static List<String> getLines(String filename) throws IOException {
    return Files.readAllLines(Path.of(filename));
}
```

For splitting a line into a list of single words, you can use:

```
public static List<String> lineToWords(String line) {
    return Arrays.asList(line.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+"));
}
```

---

**[WordFrequencySorted.java]** Add two methods to WordFrequency.java for returning a new ```Map<String, Integer>```
containing only the n most (or less) frequent words and their frequency. Entries within the returned Map, have to be
sorted by value (i.e. frequency). For sorting, you can transform the ```Map<String, Integer>``` into
a ```List<Map.Entry<String, Integer>>``` which can be sorted using a ```Comparator<Map.Entry<String, Integer>>```.
Remember also that LinkedHashMap is an implementation of Map maintaining the insertion order.

The methods have the following prototype:

```
public static Map<String, Integer> mostFrequent(Map<String, Integer> map, int limit);
public static Map<String, Integer> lessFrequent(Map<String, Integer> map, int limit);
```

where:

* **map** is the Map<String, Integer> to be processed.
* **limit** is the number of most (or less) frequent words to be returned.

