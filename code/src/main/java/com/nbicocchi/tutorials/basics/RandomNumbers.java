package com.nbicocchi.tutorials.basics;

import java.util.random.RandomGenerator;

public class RandomNumbers {
    public static void main(String[] args) {
        int seed = 42;
        RandomGenerator rnd;
        rnd = RandomGenerator.getDefault();
        //rnd = RandomGenerator.of("Random");
        //rnd = RandomGenerator.of("L32X64MixRandom");
        //rnd = RandomGenerator.of("Xoshiro256PlusPlus");

        /* integer numbers */
        System.out.println(rnd.nextInt());
        System.out.println(rnd.nextLong());

        /* bounded integer numbers */
        System.out.println(rnd.nextInt(100));
        System.out.println(rnd.nextLong(100));

        /* floating point numbers */
        System.out.println(rnd.nextFloat());
        System.out.println(rnd.nextDouble());

        /* bounded floating point numbers */
        System.out.println(rnd.nextFloat(100));
        System.out.println(rnd.nextDouble(100));

        /* boolean values */
        System.out.println(rnd.nextBoolean());
    }
}
