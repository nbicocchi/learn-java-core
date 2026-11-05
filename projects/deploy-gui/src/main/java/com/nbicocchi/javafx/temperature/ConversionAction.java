package com.nbicocchi.javafx.temperature;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

// Handles conversion requests and displays input errors.
final class ConversionAction {
    private ConversionAction() {
    }

    static void convert(TextField input, TextField output, boolean fromCelsius) {
        try {
            double value = Double.parseDouble(input.getText().trim());
            double result = fromCelsius
                    ? TemperatureConverter.celsiusToFahrenheit(value)
                    : TemperatureConverter.fahrenheitToCelsius(value);
            output.setText(Double.toString(result));
        } catch (NumberFormatException e) {
            showError(input, "Enter a number using a dot for decimals.");
        } catch (IllegalArgumentException e) {
            showError(input, e.getMessage());
        }
    }

    private static void showError(TextField input, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(input.getScene().getWindow());
        alert.setTitle("Invalid temperature");
        alert.setHeaderText("Cannot convert this value");
        alert.setContentText(message);
        alert.showAndWait();
        input.requestFocus();
        input.selectAll();
    }
}
