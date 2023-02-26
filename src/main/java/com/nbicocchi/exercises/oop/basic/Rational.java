package com.nbicocchi.exercises.oop.basic;

/**
 * Implementare una classe Rational che rappresenta un numero razionale.
 * Il costruttore accetta numeratore e denominatore.
 * Il metodo plus() prende un altro Rational x come argomento e restituisce la somma.
 * Il metodo times() prende un altro Rational x come argomento e restituisce il prodotto.
 * La classe Rational deve inoltre essere ordinabile implementando l’interfaccia Comparable<Rational>
 *
 * @author Nicola Bicocchi
 */
public class Rational implements Comparable<Rational> {
    int numerator;
    int denominator;

    public Rational(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public int getNumerator() {
        return numerator;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int denominator) {
        this.denominator = denominator;
    }

    public Rational plus(Rational o) {
        int num = this.numerator * o.denominator + o.numerator * this.denominator;
        int den = this.denominator * o.denominator;
        return new Rational(num, den);
    }

    public Rational times(Rational o) {
        int num = this.numerator * o.numerator;
        int den = this.denominator * o.denominator;
        return new Rational(num, den);
    }

    @Override
    public int compareTo(Rational o) {
        return Double.compare(numerator / (double) denominator, o.numerator / (double) o.denominator);
    }

    @Override
    public String toString() {
        return "Rational{" + "numerator=" + numerator + ", denominator=" + denominator + '}';
    }
}
