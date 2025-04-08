package com.example.ce216_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ArtifactCatalogApp extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader loader = new FXMLLoader(ArtifactCatalogApp.class.getResource("hello-view.fxml"));

        Scene mainScene = new Scene(loader.load(), 320, 240);


        primaryStage.setTitle("Artifact Catalog");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
