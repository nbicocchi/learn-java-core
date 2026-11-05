package com.nbicocchi.javafx.temperature;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TemperatureConverterTest {
    private static final double DELTA = 1e-9;

    @Test
    void convertsKnownCelsiusValues() {
        assertEquals(32, TemperatureConverter.celsiusToFahrenheit(0), DELTA);
        assertEquals(212, TemperatureConverter.celsiusToFahrenheit(100), DELTA);
        assertEquals(77, TemperatureConverter.celsiusToFahrenheit(25), DELTA);
        assertEquals(-40, TemperatureConverter.celsiusToFahrenheit(-40), DELTA);
    }

    @Test
    void convertsKnownFahrenheitValues() {
        assertEquals(0, TemperatureConverter.fahrenheitToCelsius(32), DELTA);
        assertEquals(100, TemperatureConverter.fahrenheitToCelsius(212), DELTA);
        assertEquals(-40, TemperatureConverter.fahrenheitToCelsius(-40), DELTA);
    }

    @Test
    void roundTripPreservesTemperature() {
        for (double value : new double[]{-273.15, -40, 0, 25, 100}) {
            assertEquals(value, TemperatureConverter.fahrenheitToCelsius(
                    TemperatureConverter.celsiusToFahrenheit(value)), DELTA);
        }
    }

    @Test
    void acceptsAbsoluteZero() {
        assertEquals(-459.67, TemperatureConverter.celsiusToFahrenheit(-273.15), DELTA);
        assertEquals(-273.15, TemperatureConverter.fahrenheitToCelsius(-459.67), DELTA);
    }

    @Test
    void rejectsValuesBelowAbsoluteZero() {
        assertThrows(IllegalArgumentException.class,
                () -> TemperatureConverter.celsiusToFahrenheit(-273.16));
        assertThrows(IllegalArgumentException.class,
                () -> TemperatureConverter.fahrenheitToCelsius(-459.68));
    }

    @Test
    void rejectsNonFiniteValues() {
        for (double value : new double[]{Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY}) {
            assertThrows(IllegalArgumentException.class,
                    () -> TemperatureConverter.celsiusToFahrenheit(value));
            assertThrows(IllegalArgumentException.class,
                    () -> TemperatureConverter.fahrenheitToCelsius(value));
        }
    }

    @Test
    void rejectsOverflow() {
        assertThrows(IllegalArgumentException.class,
                () -> TemperatureConverter.celsiusToFahrenheit(Double.MAX_VALUE));
    }
}
