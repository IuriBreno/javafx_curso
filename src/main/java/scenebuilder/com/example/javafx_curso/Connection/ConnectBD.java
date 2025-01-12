package scenebuilder.com.example.javafx_curso.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectBD {


    private static final String URL = "jdbc:mysql://localhost:3306/curso_scnebuilder_javafx";
    private static final String USER = "root";
    private static final String PASSWORD = "Iblf@2024";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
