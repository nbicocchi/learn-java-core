package com.nbicocchi.exercises.oop.basic;

/**
 * Implementare una classe Street, che rappresenta una strada a senso unico. Il
 * costruttore accetta la lunghezza della strada in chilometri.
 * insertCar() accetta un intero (km) come argomento ed aggiunge un’automobile
 * al chilometro km. L’automobile inserita percorrerà la strada alla velocità
 * di un chilometro al minuto, (60 km/h) fino alla fine della stessa.
 * getCarsByPosition() riceve come parametro un intero (km) e restituisce il
 * numero di automobili presenti al chilometro km.
 * progress() simula il passaggio di 1 minuto di tempo (cioè fa avanzare tutte le automobili di un chilometro).
 *
 * @author Nicola Bicocchi
 */
public class Street {
    int[] cars;

    public Street(int km) {
        cars = new int[km];
    }

    public int[] getCars() {
        return cars;
    }

    public void insertCar(int km) {
        cars[km]++;
    }

    public int getCarsByPosition(int km) {
        return cars[km];
    }

    public void progress() {
        for (int i = cars.length - 1; i > 0; i--) {
            cars[i] = cars[i - 1];
        }
        cars[0] = 0;
    }
}
