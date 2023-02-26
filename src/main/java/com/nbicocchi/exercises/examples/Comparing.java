package com.nbicocchi.exercises.examples;

import java.util.*;

public class Comparing {

    public static class Car {
        String brand;

        public Car(String brand) {
            this.brand = brand;
        }

        @Override
        public String toString() {
            return "Car{" + "brand='" + brand + '\'' + '}';
        }
    }

    public static class ECar extends Car {
        String batteryBrand;

        public ECar(String brand, String batteryBrand) {
            super(brand);
            this.batteryBrand = batteryBrand;
        }

        @Override
        public String toString() {
            return "ECar{" + "batteryBrand='" + batteryBrand + '\'' + ", brand='" + brand + '\'' + '}';
        }
    }


    public static void main(String[] args) {
        List<ECar> eCars = new ArrayList<>(List.of(
                new ECar("Tesla", "Tesla"),
                new ECar("Mercedes", "Panasonic")));
        List<Car> cars = new ArrayList<>(List.of(
                new Car("Toyota"),
                new Car("BMW"),
                new ECar("Tesla", "Tesla"),
                new ECar("Mercedes", "Panasonic")));
        cars.sort((o1, o2) -> o1.brand.compareTo(o2.brand));
        eCars.sort((o1, o2) -> o1.batteryBrand.compareTo(o2.batteryBrand));
        max(eCars, (o1, o2) -> o1.brand.compareTo(o2.brand));
        max(eCars, new Comparator<Car>() {
            @Override
            public int compare(Car o1, Car o2) {
                return o1.brand.compareTo(o2.brand);
            }
        });
    }

    public static <T> T max(List<T> list, Comparator<? super T> cmp) {
        T max = list.get(0);
        for (T next : list) {
            if (cmp.compare(max, next) < 0) {
                max = next;
            }
        }
        return max;
    }
}
