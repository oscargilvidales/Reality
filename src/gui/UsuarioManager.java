package gui;

import javax.
import javax.json.bind.*;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class UsuarioManager {
    private Map<String, Usuario> usuarios; // Almacena usuarios por documentoID
    private Jsonb jsonb;

    public UsuarioManager() {
        this.usuarios = new HashMap<>();
        this.jsonb = JsonbBuilder.create();
    }
    
    public void cargarUsuariosDesdeJson(String archivo) {
        try (FileReader reader = new FileReader(archivo)) {
            Usuario[] usuariosArray = jsonb.fromJson(reader, Usuario[].class);
            for (Usuario usuario : usuariosArray) {
                usuarios.put(usuario.getDocumentoID(), usuario);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para verificar las credenciales del usuario
    public boolean verificarCredenciales(String documentoID, String contraseña) {
        Usuario usuario = usuarios.get(documentoID);
        return usuario != null && usuario.getContraseña().equals(contraseña);
    }

    // Método para agregar un usuario
    public void agregarUsuario(Usuario usuario) {
        usuarios.put(usuario.getDocumentoID(), usuario);
    }

    // Método para obtener un usuario por documentoID
    public Usuario obtenerUsuario(String documentoID) {
        return usuarios.get(documentoID);
    }

    // Método para convertir un usuario a JSON
    public String usuarioToJson(Usuario usuario) {
        try {
            return jsonb.toJson(usuario);
        } catch (JsonbException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para convertir JSON a un objeto Usuario
    public Usuario jsonToUsuario(String json) {
        try {
            return jsonb.fromJson(json, Usuario.class);
        } catch (JsonbException e) {
            e.printStackTrace();
            return null;
        }
    }


}
