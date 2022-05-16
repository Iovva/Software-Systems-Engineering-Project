package com.example.temaproiect.controllers;

import Domain.Employee;
import Service.ServiceException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController extends ScreenController {

    @FXML
    public TextField usernameField;

    @FXML public TextField passwordField;

    @FXML
    protected void onLoginButtonClick() {
        try{
            Employee employee = service.login(usernameField.getText(), passwordField.getText());
            if (employee == null) {
                giveWarning("Invalid username/password!");
            }
            else{
                changeScene(employee);
            }
        } catch (IOException | ServiceException ex){
            giveWarning(ex.getMessage());
        }
    }
}