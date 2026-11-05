package com.nbicocchi.javafx.temperature;

public class TemperatureConverter {
    public static double celsiusToFahrenheit(double celsius) {
        validate(celsius, -273.15);
        return checkResult(celsius * 1.8 + 32);
    }

    public static double fahrenheitToCelsius(double fahrenheit) {
        validate(fahrenheit, -459.67);
        return checkResult((fahrenheit - 32) / 1.8);
    }

    private static void validate(double value, double minimum) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Temperature must be finite.");
        }
        if (value < minimum) {
            throw new IllegalArgumentException("Temperature cannot be below absolute zero.");
        }
    }

    private static double checkResult(double result) {
        if (!Double.isFinite(result)) {
            throw new IllegalArgumentException("Result is outside the supported numeric range.");
        }
        return result;
    }
}
