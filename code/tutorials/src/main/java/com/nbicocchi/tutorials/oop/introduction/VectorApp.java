package com.nbicocchi.tutorials.oop.introduction;

public class VectorApp {
    public static void main(String[] args) {
        Vector vector = new Vector(100);
        vector.sort();
        System.out.println(vector.search(10));
    }
}
