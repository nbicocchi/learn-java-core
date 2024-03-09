package com.nbicocchi.tutorials.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Comparing {

    public static class Car implements Comparable<Car> {
        String brand;

        public Car(String brand) {
            this.brand = brand;
        }

        @Override
        public String toString() {
            return "Car{" + "brand='" + brand + '\'' + '}';
        }

        @Override
        public int compareTo(Car o) {
            return brand.compareTo(o.brand);
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
        Collections.sort(cars);
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
