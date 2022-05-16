package com.example.temaproiect.controllers;

import Domain.Employee;
import Domain.EmployeeRank;
import Service.Service;
import com.example.temaproiect.StartApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public abstract class ScreenController {

    public static Stage stage;
    static Employee currentEmployee;

    Service service;

    public ScreenController(){
        try{
            Properties properties = new Properties();
            properties.load(StartApplication.class.getResourceAsStream("/database.properties"));
            service = new Service(properties);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }


    void changeScene(Employee employee) throws IOException {

        FXMLLoader fxmlLoader = null;
        Scene scene;

        if (Objects.equals(employee.getRank(), EmployeeRank.ADMINISTRATOR)){
            fxmlLoader = new FXMLLoader(StartApplication.class.getResource("Administrator.fxml"));
            scene = new Scene(fxmlLoader.load(), 1184, 434);
        }
        else if (Objects.equals(employee.getRank(), EmployeeRank.BOSS)) {
            fxmlLoader = new FXMLLoader(StartApplication.class.getResource("Boss.fxml"));
            scene = new Scene(fxmlLoader.load(), 409, 364);
        }
        else if (Objects.equals(employee.getRank(), EmployeeRank.WORKER)) {
            fxmlLoader = new FXMLLoader(StartApplication.class.getResource("Worker.fxml"));
            scene = new Scene(fxmlLoader.load(), 409, 364);
        }
        else {
            giveWarning("Invalid employee rank! Contact admin!");
            return;
        }

        currentEmployee = employee;

        stage.setTitle("Logged in as: " + currentEmployee.getFirstName() + " " + currentEmployee.getLastName());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void giveConfirmation(String s) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setHeaderText(null);
        a.setContentText(s);
        a.show();
    }

    @FXML
    protected void giveWarning(String s) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setHeaderText(null);
        a.setContentText(s);
        a.show();
    }
}
