module com.example.temaproiect {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.hibernate.orm.core;

    exports Domain;
    exports com.example.temaproiect;

    opens com.example.temaproiect to javafx.fxml;
    exports com.example.temaproiect.controllers;
    opens com.example.temaproiect.controllers to javafx.fxml;
}