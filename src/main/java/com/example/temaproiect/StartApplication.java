package com.example.temaproiect;

import com.example.temaproiect.controllers.ScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StartApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        ScreenController.stage = stage;

        FXMLLoader fxmlLoader = new FXMLLoader(StartApplication.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 409, 364);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    //validator

    public static void main(String[] args) {
        launch();
    }
}