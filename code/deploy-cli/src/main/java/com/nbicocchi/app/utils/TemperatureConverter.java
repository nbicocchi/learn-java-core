package com.nbicocchi.app.utils;

public class TemperatureConverter {
    public static double celsiusToFahrenheit(double celsius) {
        validate(celsius, -273.15);
        return checkResult(celsius * 1.8 + 32);
    }

    public static double fahrenheitToCelsius(double fahrenheit) {
        validate(fahrenheit, -459.67);
        return checkResult((fahrenheit - 32) / 1.8);
    }

    public static double celsiusToKelvin(double celsius) {
        validate(celsius, -273.15);
        return checkResult(celsius + 273.15);
    }

    public static double kelvinToCelsius(double kelvin) {
        validate(kelvin, 0);
        return checkResult(kelvin - 273.15);
    }

    public static double fahrenheitToKelvin(double fahrenheit) {
        validate(fahrenheit, -459.67);
        return checkResult((fahrenheit + 459.67) / 9.0 * 5.0);
    }

    public static double kelvinToFahrenheit(double kelvin) {
        validate(kelvin, 0);
        return checkResult(kelvin / 5.0 * 9.0 - 459.67);
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
