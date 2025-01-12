module scenebuilder.com.example.javafx_curso {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires mysql.connector.j;


    opens scenebuilder.com.example.javafx_curso to javafx.fxml;

    opens scenebuilder.com.example.javafx_curso.Controller to javafx.fxml;
    exports scenebuilder.com.example.javafx_curso;
}