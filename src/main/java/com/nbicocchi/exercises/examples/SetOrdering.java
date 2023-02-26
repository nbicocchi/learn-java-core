package com.nbicocchi.exercises.examples;

import java.util.*;

/**
 * Class showing how to use basic methods of sets
 *
 * @author Nicola Bicocchi
 */
public class SetOrdering {
    public static void main(String[] args) {
        ArrayList<String> l = new ArrayList<>(Arrays.asList("Nicola", "Agata", "Marzia", "Agata"));
        java.util.Set<String> hs = new HashSet<>(l);
        java.util.Set<String> lhs = new LinkedHashSet<>(l);
        java.util.Set<String> ts = new TreeSet<>(l);
        // original list
        System.out.println(l);
        // initial ordering
        System.out.println(lhs);
        // no particular ordering (because based on hash maps)
        System.out.println(hs);
        // natural ordering
        System.out.println(ts);
    }
}
