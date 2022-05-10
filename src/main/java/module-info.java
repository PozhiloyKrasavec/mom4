module com.example.mom4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.java;
    requires java.sql;


    opens com.example.mom4 to javafx.fxml;
    exports com.example.mom4;
    exports com.example.mom4.Model;
    opens com.example.mom4.Model to javafx.fxml;
}