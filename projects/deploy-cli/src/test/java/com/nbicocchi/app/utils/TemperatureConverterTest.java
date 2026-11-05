package com.nbicocchi.app.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TemperatureConverterTest {
    private static final double DELTA = 1e-9;

    @Test
    void convertsKnownTemperatures() {
        assertEquals(32, TemperatureConverter.celsiusToFahrenheit(0), DELTA);
        assertEquals(100, TemperatureConverter.fahrenheitToCelsius(212), DELTA);
        assertEquals(273.15, TemperatureConverter.celsiusToKelvin(0), DELTA);
        assertEquals(0, TemperatureConverter.kelvinToCelsius(273.15), DELTA);
        assertEquals(273.15, TemperatureConverter.fahrenheitToKelvin(32), DELTA);
        assertEquals(32, TemperatureConverter.kelvinToFahrenheit(273.15), DELTA);
        assertEquals(-40, TemperatureConverter.celsiusToFahrenheit(-40), DELTA);
    }

    @Test
    void convertsBackToOriginalScale() {
        assertEquals(25, TemperatureConverter.fahrenheitToCelsius(
                TemperatureConverter.celsiusToFahrenheit(25)), DELTA);
        assertEquals(25, TemperatureConverter.kelvinToCelsius(
                TemperatureConverter.celsiusToKelvin(25)), DELTA);
        assertEquals(25, TemperatureConverter.kelvinToFahrenheit(
                TemperatureConverter.fahrenheitToKelvin(25)), DELTA);
    }

    @Test
    void acceptsAbsoluteZero() {
        assertEquals(0, TemperatureConverter.celsiusToKelvin(-273.15), DELTA);
        assertEquals(0, TemperatureConverter.fahrenheitToKelvin(-459.67), DELTA);
        assertEquals(-273.15, TemperatureConverter.kelvinToCelsius(0), DELTA);
        assertEquals(-459.67, TemperatureConverter.kelvinToFahrenheit(0), DELTA);
    }

    @Test
    void rejectsTemperaturesBelowAbsoluteZero() {
        assertThrows(IllegalArgumentException.class,
                () -> TemperatureConverter.celsiusToFahrenheit(-273.16));
        assertThrows(IllegalArgumentException.class,
                () -> TemperatureConverter.celsiusToKelvin(-273.16));
        assertThrows(IllegalArgumentException.class,
                () -> TemperatureConverter.fahrenheitToCelsius(-459.68));
        assertThrows(IllegalArgumentException.class,
                () -> TemperatureConverter.fahrenheitToKelvin(-459.68));
        assertThrows(IllegalArgumentException.class,
                () -> TemperatureConverter.kelvinToCelsius(-0.01));
        assertThrows(IllegalArgumentException.class,
                () -> TemperatureConverter.kelvinToFahrenheit(-0.01));
    }

    @Test
    void rejectsNonFiniteValues() {
        for (double value : new double[]{Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY}) {
            assertThrows(IllegalArgumentException.class, () -> TemperatureConverter.celsiusToFahrenheit(value));
            assertThrows(IllegalArgumentException.class, () -> TemperatureConverter.fahrenheitToCelsius(value));
            assertThrows(IllegalArgumentException.class, () -> TemperatureConverter.celsiusToKelvin(value));
            assertThrows(IllegalArgumentException.class, () -> TemperatureConverter.kelvinToCelsius(value));
            assertThrows(IllegalArgumentException.class, () -> TemperatureConverter.fahrenheitToKelvin(value));
            assertThrows(IllegalArgumentException.class, () -> TemperatureConverter.kelvinToFahrenheit(value));
        }
    }

    @Test
    void rejectsOverflow() {
        assertThrows(IllegalArgumentException.class,
                () -> TemperatureConverter.celsiusToFahrenheit(Double.MAX_VALUE));
        assertThrows(IllegalArgumentException.class,
                () -> TemperatureConverter.kelvinToFahrenheit(Double.MAX_VALUE));
    }
}
