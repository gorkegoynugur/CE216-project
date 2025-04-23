package com.example.ce216_project;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CatalogController {
    @FXML
    private Label greetingLabel;

    //




    @FXML
    protected void onCatalogButtonClick() {
        greetingLabel.setText("Welcome to the Artifact Catalog!");
    }
}
