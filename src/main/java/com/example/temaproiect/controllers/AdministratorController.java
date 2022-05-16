package com.example.temaproiect.controllers;

import Domain.Employee;
import Domain.EmployeeRank;
import Service.ServiceException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class AdministratorController extends ScreenController {


    @FXML
    TableView<Employee> employeeTable;

    @FXML
    TableColumn<Employee, String> table_cnp;

    @FXML
    TableColumn<Employee, String> table_firstName;

    @FXML
    TableColumn<Employee, String> table_lastName;

    @FXML
    TableColumn<Employee, String> table_username;

    @FXML
    TableColumn<Employee, String> table_password;

    @FXML
    TableColumn<Employee, EmployeeRank> table_rank;

    @FXML
    TextField cnpField;

    @FXML
    TextField firstNameField;

    @FXML
    TextField lastNameField;

    @FXML
    TextField userField;

    @FXML
    TextField passwordField;

    @FXML
    RadioButton administratorRadioButton;

    @FXML
    RadioButton bossRadioButton;

    @FXML
    RadioButton workerRadioButton;

    @FXML
    ToggleGroup employeeRank;

    ObservableList<Employee> employees = FXCollections.observableArrayList();

    public AdministratorController(){
        super();
    }

    @FXML
    public void initialize(){
        table_cnp.setCellValueFactory(
                new PropertyValueFactory<Employee, String>("cnp")
        );
        table_username.setCellValueFactory(
                new PropertyValueFactory<Employee, String>("username")
        );
        table_password.setCellValueFactory(
                new PropertyValueFactory<Employee, String>("password")
        );
        table_firstName.setCellValueFactory(
                new PropertyValueFactory<Employee, String>("firstName")
        );
        table_lastName.setCellValueFactory(
                new PropertyValueFactory<Employee, String>("lastName")
        );

        table_rank.setCellValueFactory(
                new PropertyValueFactory<Employee, EmployeeRank>("rank")
        );

        employeeTable.setRowFactory(tv -> new TableRow<Employee>() {
            @Override
            protected void updateItem(Employee item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null)
                    setStyle("");
                else if (!isEmpty() && Objects.equals(item.getRank(), EmployeeRank.ADMINISTRATOR))
                    setStyle("-fx-border-color:#0500ff");
                else if (!isEmpty() && Objects.equals(item.getRank(), EmployeeRank.BOSS))
                    setStyle("-fx-border-color:#ff0000");
                else if (!isEmpty() && Objects.equals(item.getRank(), EmployeeRank.WORKER))
                    setStyle("-fx-border-color:white");
            }
        });

        refreshTable();
    }

    private void refreshTable() {
        try {
            List<Employee> employeesList = service.getEmployees();
            employees = FXCollections.observableArrayList(employeesList);
            employeeTable.setItems(employees);
        }
        catch (ServiceException ex) {
            giveWarning(ex.getMessage());
        }
    }

    @FXML
    protected void onSelectTableRow (){
        TableSelectionModel<Employee> selectionModel = employeeTable.getSelectionModel();
        if (selectionModel.getSelectedItems().size() == 0)
            return;

        Employee selectedEmployee = selectionModel.getSelectedItems().get(0);
        cnpField.setText(selectedEmployee.getCnp());
        userField.setText(selectedEmployee.getUsername());
        passwordField.setText(selectedEmployee.getPassword());
        firstNameField.setText(selectedEmployee.getFirstName());
        lastNameField.setText(selectedEmployee.getLastName());

        if (selectedEmployee.getRank() == EmployeeRank.ADMINISTRATOR){
            administratorRadioButton.setSelected(true);
        }
        else if  (selectedEmployee.getRank() == EmployeeRank.BOSS){
            bossRadioButton.setSelected(true);
        }
        else if (selectedEmployee.getRank() == EmployeeRank.WORKER){
            workerRadioButton.setSelected(true);
        }
    }

    @FXML
    protected void onAddAccountButtonClick() {
        try{
            RadioButton selectedRadioButton = (RadioButton) employeeRank.getSelectedToggle();
            String selectedRadioText = selectedRadioButton.getText();

            service.saveEmployee(cnpField.getText(), userField.getText(), passwordField.getText(),
                    firstNameField.getText(), lastNameField.getText(), selectedRadioText.toUpperCase(Locale.ROOT));

            refreshTable();
        } catch (ServiceException ex){
            giveWarning(ex.getMessage());
        }
    }
    @FXML
    protected void onRemoveAccountButtonClick() {
        try{
            if (Objects.equals(cnpField.getText(), currentEmployee.getCnp())){
                giveWarning("You cannot delete yourself!");
                return;
            }

            service.deleteEmployee((cnpField.getText()));

            refreshTable();
        } catch (ServiceException ex){
            giveWarning(ex.getMessage());
        }
    }

    @FXML
    protected void onUpdateAccountButtonClick() {
        try{
            RadioButton selectedRadioButton = (RadioButton) employeeRank.getSelectedToggle();
            String selectedRadioText = selectedRadioButton.getText();

            service.updateEmployee(cnpField.getText(), userField.getText(), passwordField.getText(),
                    firstNameField.getText(), lastNameField.getText(), selectedRadioText.toUpperCase(Locale.ROOT));

            refreshTable();
        } catch (ServiceException ex){
            giveWarning(ex.getMessage());
        }
    }

}
