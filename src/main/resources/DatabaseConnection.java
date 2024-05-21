package main.resources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:oracle:thin:@afrodita.lcc.uma.es:1521:apolo";
    private static final String USER = "UBD4533";
    private static final String PASSWORD = "DMpiedpiper23";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}