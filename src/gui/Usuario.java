package gui;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.regex.Pattern;

public class Usuario {
    private final String id;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String documentoID;
    private int tipoDeUsuario; // 0 - Usuario normal, 1 - Usuario Administrador
    private final LocalDateTime fechaDeCreacion;
    private String telefono;
    private String direccion;
    private String codigoPostal;
    private String provincia;
    private String ciudad;
    private String contraseña;

    public Usuario(String nombre, String apellido1, String apellido2, String documentoID, int tipoDeUsuario, String telefono, String direccion, String codigoPostal, String provincia, String ciudad, String contraseña) {
        this.id = UUID.randomUUID().toString(); // Unique ID
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.documentoID = validateDocumentoID(documentoID);
        this.tipoDeUsuario = tipoDeUsuario;
        this.fechaDeCreacion = LocalDateTime.now(); // Fecha de creación actual
        this.telefono = validateTelefono(telefono);
        this.direccion = direccion;
        this.codigoPostal = validateCodigoPostal(codigoPostal);
        this.provincia = provincia;
        this.ciudad = ciudad;
        this.contraseña = hashPassword(contraseña);
    }

    // Validation methods
    private String validateDocumentoID(String documentoID) {
        // Add your validation logic here
        return documentoID;
    }

    private String validateTelefono(String telefono) {
        if (!Pattern.matches("\\+?[0-9]{10,15}", telefono)) {
            throw new IllegalArgumentException("Invalid phone number");
        }
        return telefono;
    }

    private String validateCodigoPostal(String codigoPostal) {
        // Add your validation logic here
        return codigoPostal;
    }

    private String hashPassword(String password) {
        // Implement a hashing mechanism, for example using BCrypt
        // return BCrypt.hashpw(password, BCrypt.gensalt());
        return password; // Placeholder: replace with actual hash
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getDocumentoID() {
        return documentoID;
    }

    public void setDocumentoID(String documentoID) {
        this.documentoID = validateDocumentoID(documentoID);
    }

    public int getTipoDeUsuario() {
        return tipoDeUsuario;
    }

    public void setTipoDeUsuario(int tipoDeUsuario) {
        this.tipoDeUsuario = tipoDeUsuario;
    }

    public LocalDateTime getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = validateTelefono(telefono);
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = validateCodigoPostal(codigoPostal);
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = hashPassword(contraseña);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido1='" + apellido1 + '\'' +
                ", apellido2='" + apellido2 + '\'' +
                ", documentoID='" + documentoID + '\'' +
                ", tipoDeUsuario=" + tipoDeUsuario +
                ", fechaDeCreacion=" + fechaDeCreacion +
                ", telefono='" + telefono + '\'' +
                ", direccion='" + direccion + '\'' +
                ", codigoPostal='" + codigoPostal + '\'' +
                ", provincia='" + provincia + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", contraseña='" + contraseña + '\'' +
                '}';
    }
}
