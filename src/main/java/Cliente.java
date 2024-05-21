package main.java;
import java.lang.reflect.Array;
import java.time.Instant;
import  java.util.*;

public class Cliente implements Usuario {
    private ArrayList cuentas;
    private int ID, DNI, codigoPostal, telefono;
    private final Date creationDate;
    private String nombre,apellido1,apellido2,correoElectronico,direccion,localidad,provincia;
    public Cliente(int ID,ArrayList<String> cuentas, String nombre, String apellido1, String apellido2,int telefono , String correoElectronico, String direccion, String localidad, String provincia, int codigoPostal, int DNI){
        this.ID = ID;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        if(apellido2 != null) {
            this.apellido2 = apellido2;
        }else this.apellido2 = "";
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
        this.codigoPostal = codigoPostal;
        this.DNI = DNI;
        this.creationDate = Date.from(Instant.now());
        this.localidad = localidad;
        this.direccion =direccion;
        this.provincia = provincia;
        this.cuentas = cuentas;
    }

    @Override
    public int getID() {
        return ID;
    }
    @Override
    public void setID(int id) { ID = id; }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String getApellido1() {
        return apellido1;
    }
    @Override
    public String getApellido2(){ return  apellido2; }


    @Override
    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    @Override
    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    @Override
    public int getDNI() {
        return DNI;
    }

    @Override
    public Date getCreationDate() {
        return creationDate;
    }

    @Override
    public String getCorreo() {
        return correoElectronico;
    }

    @Override
    public void setCorreo(String correo) {
        this.correoElectronico = correo;
    }

    @Override
    public int getTelefono() {
        return telefono;
    }

    @Override
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    @Override
    public String getDireccion() {
        return direccion;
    }

    @Override
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public int getCodigoPostal() {
        return codigoPostal;
    }

    @Override
    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    @Override
    public String getProvincia() {
        return provincia;
    }

    @Override
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    @Override
    public String getLocalidad() {
        return localidad;
    }

    @Override
    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }
    public ArrayList<String> getCuentas(){return cuentas;}
}
