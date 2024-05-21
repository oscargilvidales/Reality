package main;

import main.resources.DatabaseConnection;
import main.resources.UsuarioDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class main {
    public static void main(String[] args) {
        try (Connection connection1 = DatabaseConnection.getConnection()) {
            UsuarioDAO usuarioDAO = new UsuarioDAO(connection1);
            System.out.println(usuarioDAO.getUsuarioById(1).getNombre());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}