package com.nbicocchi.javafx.temperature;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class ConverterController {
    @FXML
    private ChoiceBox<String> chSelector;
    @FXML
    private TextField tfC;
    @FXML
    private TextField tfF;

    @FXML
    public void initialize() {
        chSelector.getItems().setAll("C->F", "F->C");
        chSelector.getSelectionModel().select("C->F");
    }

    @FXML
    void onConvertClick(ActionEvent event) {
        if ("C->F".equals(chSelector.getValue())) {
            ConversionAction.convert(tfC, tfF, true);
        } else {
            ConversionAction.convert(tfF, tfC, false);
        }
    }
}
