package com.nbicocchi.tutorials.basics;

import java.util.random.RandomGenerator;

public class RandomNumbers {
    public static void main(String[] args) {
        RandomGenerator rnd = RandomGenerator.getDefault();
        //RandomGenerator rnd = RandomGenerator.of("Random");
        //RandomGenerator rnd = RandomGenerator.of("L32X64MixRandom");
        //RandomGenerator rnd = RandomGenerator.of("Xoshiro256PlusPlus");

        /* integer numbers */
        System.out.println(rnd.nextInt());
        System.out.println(rnd.nextLong());

        /* integer numbers (upper bound) */
        System.out.println(rnd.nextInt(100));
        System.out.println(rnd.nextLong(100));

        /* integer numbers (lower and upper bound) */
        System.out.println(rnd.nextInt(50, 60));
        System.out.println(rnd.nextLong(50, 60));

        /* floating point numbers */
        System.out.println(rnd.nextFloat());
        System.out.println(rnd.nextDouble());

        /* floating point numbers (upper bound) */
        System.out.println(rnd.nextFloat(100.0F));
        System.out.println(rnd.nextDouble(100.0));

        /* floating point numbers (lower and upper bound) */
        System.out.println(rnd.nextFloat(0.1F,0.4F));
        System.out.println(rnd.nextDouble(0.1, 0.4));

        /* boolean values */
        System.out.println(rnd.nextBoolean());
    }
}
