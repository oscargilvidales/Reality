package main.resources;
import main.java.*;
import java.sql.*;
import java.util.ArrayList;

public class UsuarioDAO {
    private Connection connection;

    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para obtener un usuario por ID
    public Usuario getUsuarioById(int id) throws SQLException {
        String query = "SELECT * FROM CLIENTE WHERE ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    ArrayList<String> cuentas = getCuentasByUsuarioId(id);
                    return new Cliente(
                            resultSet.getInt("id"),
                            cuentas,
                            resultSet.getString("nombre"),
                            resultSet.getString("apellido1"),
                            resultSet.getString("apellido2"),
                            resultSet.getInt("telefono"),
                            resultSet.getString("correo_electronico"),
                            resultSet.getString("direccion"),
                            resultSet.getString("localidad"),
                            resultSet.getString("provincia"),
                            resultSet.getInt("codigo_postal"),
                            resultSet.getInt("dni")
                    );
                }
            }
        }
        return null;
    }

    // Método para obtener las cuentas de un usuario por su ID
    private ArrayList<String> getCuentasByUsuarioId(int usuarioId) throws SQLException {
        ArrayList<String> cuentas = new ArrayList<>();
        String query = "SELECT cuenta FROM cuentas WHERE usuario_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, usuarioId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    cuentas.add(resultSet.getString("cuenta"));
                }
            }
        }
        return cuentas;
    }

    // Método para guardar un usuario en la base de datos
    public void guardarUsuario(Cliente usuario) throws SQLException {
        String query = "INSERT INTO usuarios (nombre, apellido1, apellido2, telefono, correo_electronico, direccion, localidad, provincia, codigo_postal, dni, fecha_creacion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getApellido1());
            statement.setString(3, usuario.getApellido2());
            statement.setInt(4, usuario.getTelefono());
            statement.setString(5, usuario.getCorreo());
            statement.setString(6, usuario.getDireccion());
            statement.setString(7, usuario.getLocalidad());
            statement.setString(8, usuario.getProvincia());
            statement.setInt(9, usuario.getCodigoPostal());
            statement.setInt(10, usuario.getDNI());
            statement.setDate(11, new java.sql.Date(usuario.getCreationDate().getTime()));
            statement.executeUpdate();

            // Obtener la clave generada automáticamente
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    usuario.setID(generatedKeys.getInt(1));
                }
            }
        }

        // Guardar las cuentas del usuario
        for (String cuenta : usuario.getCuentas()) {
            guardarCuenta(usuario.getID(), cuenta);
        }
    }

    // Método para guardar una cuenta en la base de datos
    private void guardarCuenta(int usuarioId, String cuenta) throws SQLException {
        String query = "INSERT INTO cuentas (usuario_id, cuenta) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, usuarioId);
            statement.setString(2, cuenta);
            statement.executeUpdate();
        }
    }
}
