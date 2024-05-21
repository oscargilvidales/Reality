package main.java;
import java.util.*;

public interface Usuario {
    //ID
    int getID();
    void setID(int ID);
    //Nombre
    String getNombre();
    void setNombre(String nombre);
    //Apellidos
    String getApellido1();
    String getApellido2();
    void setApellido1(String apellido1);
    void setApellido2(String apellido2);
    //DNI
    int getDNI();
    //FechaCreacion
    Date getCreationDate();
    //Correo
    String getCorreo();
    void setCorreo(String correo);
    //Telefono
    int getTelefono();
    void setTelefono(int telefono);
    //Dirección
    String getDireccion();
    void setDireccion(String direccion);
    int getCodigoPostal();
    void setCodigoPostal(int codigoPostal);
    String getProvincia();
    void setProvincia(String provincia);
    String getLocalidad();
    void setLocalidad(String localidad);
}
