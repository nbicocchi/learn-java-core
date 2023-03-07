Function composition
--------------------

 ### Theory

In this topic, you will learn a new technique for working with functions called **function composition**. It is a mechanism for combining functions to obtain more complicated functions that originally comes from math. In a sense, it can be considered as a **design pattern** in functional programming. You can use this pattern to compose standard functions, operators, predicates and consumers (but not suppliers). Let's take a look at examples.

 ### Composing functions

The functional interface`Function<T, R>`has two default methods `compose` and `andThen` for composing new functions. The main difference between these methods lies in the execution order.

Generally, `f.compose(g).apply(x)` is the same as `f(g(x))`**,** and `f.andThen(g).apply(x)` is the same as `g(f(x))`.

Here is an example with two functions: `adder` and `multiplier`.

```
Function<Integer, Integer> adder = x -> x + 10;
Function<Integer, Integer> multiplier = x -> x * 5;

// compose: adder(multiplier(5))
System.out.println("result: " + adder.compose(multiplier).apply(5));

// andThen: multiplier(adder(5))
System.out.println("result: " + adder.andThen(multiplier).apply(5));
```

In this case, the `compose` method returns a composed function that first applies `multiplier`to its input, and then applies `adder` to the result. The `andThen`method returns a composed function that first applies`adder` to its input, and then applies `multiplier` to the result.

Here is the output:

```
result: 35
result: 75
```

Operators can be used in the same way as functions.

The methods `compose` and `andThen` do not modify the functions that are combined. Instead, they return new functions. This is true for all the next examples.

 ### Composing predicates

All functional interfaces representing predicates (`Predicate<T>`, `IntPredicate` and others) have three methods for composing new predicates: `and`, `or` and `negate`.

There are two predicates in the example below:`isOdd` and `lessThan11`.

```
IntPredicate isOdd = n -> n % 2 != 0; // it's true for 1, 3, 5, 7, 9, 11 and so on

System.out.println(isOdd.test(10)); // prints "false"
System.out.println(isOdd.test(11)); // prints "true"

IntPredicate lessThan11 = n -> n < 11; // it's true for all numbers < 11

System.out.println(lessThan11.test(10)); // prints "true"
System.out.println(lessThan11.test(11)); // prints "false"
```

Let's negate the first predicate:

```
IntPredicate isEven = isOdd.negate(); // it's true for 0, 2, 4, 6, 8, 10 and so on
System.out.println(isEven.test(10)); // prints "true"
System.out.println(isEven.test(11)); // prints "false"
```

Here we have a new predicate that tests whether the value is even rather than odd.

Now let's combine both `isOdd` and `lessThan11` predicates together by using `or` and `and` methods:

```
IntPredicate isOddOrLessThan11 = isOdd.or(lessThan11);

System.out.println(isOddOrLessThan11.test(10)); // prints "true"
System.out.println(isOddOrLessThan11.test(11)); // prints "true"
System.out.println(isOddOrLessThan11.test(12)); // prints "false"
System.out.println(isOddOrLessThan11.test(13)); // prints "true"

IntPredicate isOddAndLessThan11 = isOdd.and(lessThan11);

System.out.println(isOddAndLessThan11.test(8));  // prints "false"
System.out.println(isOddAndLessThan11.test(9));  // prints "true"
System.out.println(isOddAndLessThan11.test(10)); // prints "false"
System.out.println(isOddAndLessThan11.test(11)); // prints "false"
```

As you can see, these methods are equivalent to logical operators `&&` and `||`, but they work with functions rather than their values.

 ### Composing consumers

It can be a little surprising, but it is also possible to combine consumers by using the `andThen` method. It just returns a new consumer that consumes the given value several times in a chain.

In the following example, we use `andThen` to print a value two times, but it is possible to do it more times.

```
Consumer<String> consumer = System.out::println;
Consumer<String> doubleConsumer = consumer.andThen(System.out::println);
doubleConsumer.accept("Hi!");
```

Here is the output:

```
Hi!
Hi!
```

In a real situation, you could use it to output a value to different destinations, like a database or a logger.

Although it is possible to combine consumers, it is not possible to combine suppliers.

 ### Conclusion

Function composition allows developers to build new functions from existing ones and use them whenever you want. All kinds of predicates, functions, operators, and consumers (except for suppliers) have methods for that purpose.