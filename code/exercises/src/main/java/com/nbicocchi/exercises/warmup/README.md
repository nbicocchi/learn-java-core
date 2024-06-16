# Object-Oriented Programming - DIEF/UNIMORE

## Java Exercises (Warmup)

**[XMasTree.java]** Print to screen the little story below. 
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

**[GuessNumber.java]** Write a program implementing a simple quiz game. The computer selects a random number between 0 and 3 then ask the user to guess the number. The game ends when the user correctly guesses the number 0.
(see java.util.random.RandomGenerator, java.util.Scanner).

**[MonkeyTrouble.java]** We have two monkeys, a and b, and two boolean values (aSmile and bSmile) indicate if each is smiling. We are in trouble if they are both smiling or if neither of them is smiling. 
Write a program asking the user if monkeys are smiling and print an alert if we are in trouble (see java.util.Scanner).

**[TheRightPrice.java]** The computer selects a random number between [0, 100] representing the price of an imaginary item. Two users are asked to guess the right price. The user which proposed the number closer to the right price wins. Note that Math.abs(n) returns the absolute value of a number (see java.util.random.RandomGenerator, java.util.Scanner).

**[InterestRate.java]** Write a program that prints the balance of an account after the first, second, and third year. 
The account has an initial balance of $1,000 and earns 5 percent interest per year.

**[EstimatePI.java]** It is possible to get an estimate of the mathematical constant π by using a random process. The idea is based on the fact that the area of a circle of radius 1 is equal to π, and the area of a quarter of that circle is π/4. 

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

Write a program producing increasingly accurate estimates of PI as reported below (see System.out.printf()):

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



