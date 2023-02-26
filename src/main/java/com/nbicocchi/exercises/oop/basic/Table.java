package com.nbicocchi.exercises.oop.basic;

/**
 * Scrivere una classe Table per rappresentare tavole pitagoriche.
 * Metodi:
 * Table()      costruttore senza parametri che configura una tavola 10x10
 * Table(a, b)  costruttore che configura una tavola axb
 * toString()   ritorna una stringa che rappresenta la tavola
 * double sum() ritorna la somma di tutti i numeri presenti nella tavola
 * double avg() ritorna la media di tutti i numeri presenti nella tavola
 */
public class Table {
    int a, b;

    public Table() {
        super();
        this.a = 10;
        this.b = 10;
    }

    public Table(int a, int b) {
        super();
        this.a = a;
        this.b = b;
    }

    public double avg() {
        return sum() / (a * b);
    }

    public double sum() {
        double sum = 0;
        for (int i = 1; i <= a; i++) {
            for (int j = 1; j <= b; j++) {
                sum += i * j;
            }
        }
        return sum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= a; i++) {
            for (int j = 1; j <= b; j++) {
                sb.append(i * j);
                sb.append("\t");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
