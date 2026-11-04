package com.nbicocchi.app;

import com.nbicocchi.app.utils.TemperatureConverter;

import java.util.Locale;

public class App {
    public static void main(String[] args) {
        if (args.length == 1 && args[0].equals("--help")) {
            printUsage();
            return;
        }
        if (args.length != 3) {
            System.err.println("Expected a temperature, a source scale and a target scale.");
            printUsage();
            System.exit(1);
            return;
        }

        try {
            double value = Double.parseDouble(args[0]);
            String from = normalizeScale(args[1]);
            String to = normalizeScale(args[2]);
            double result = convert(value, from, to);
            System.out.printf(Locale.ROOT, "%.2f %s = %.2f %s%n",
                    value, from, result, to);
        } catch (NumberFormatException e) {
            System.err.println("Error: temperature must be a number (use a dot for decimals).");
            System.exit(1);
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }

    private static String normalizeScale(String scale) {
        String normalized = scale.toUpperCase(Locale.ROOT);
        if (!normalized.equals("C") && !normalized.equals("F") && !normalized.equals("K")) {
            throw new IllegalArgumentException("Scale must be C, F or K: " + scale);
        }
        return normalized;
    }

    private static double convert(double value, String from, String to) {
        return switch (from + to) {
            case "CF" -> TemperatureConverter.celsiusToFahrenheit(value);
            case "FC" -> TemperatureConverter.fahrenheitToCelsius(value);
            case "CK" -> TemperatureConverter.celsiusToKelvin(value);
            case "KC" -> TemperatureConverter.kelvinToCelsius(value);
            case "FK" -> TemperatureConverter.fahrenheitToKelvin(value);
            case "KF" -> TemperatureConverter.kelvinToFahrenheit(value);
            // A same-scale request still needs numeric validation.
            case "CC" -> {
                TemperatureConverter.celsiusToKelvin(value);
                yield value;
            }
            case "FF" -> {
                TemperatureConverter.fahrenheitToCelsius(value);
                yield value;
            }
            case "KK" -> {
                TemperatureConverter.kelvinToCelsius(value);
                yield value;
            }
            default -> throw new IllegalArgumentException("Unsupported conversion.");
        };
    }

    private static void printUsage() {
        System.out.println("Usage: java -jar deploy-cli-1.0-SNAPSHOT.jar <value> <from> <to>");
        System.out.println("Scales: C (Celsius), F (Fahrenheit), K (Kelvin), case-insensitive.");
        System.out.println("Example: java -jar deploy-cli-1.0-SNAPSHOT.jar 25 C F");
    }
}
