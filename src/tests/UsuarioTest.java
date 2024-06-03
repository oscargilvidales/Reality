package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import gui.Usuario;

public class UsuarioTest {
    private Usuario usuario;

    @BeforeEach
    public void setUp() {
        usuario = new Usuario("John", "Doe", "Smith", "12345678A", 0, "+1234567890", "123 Main St", "12345", "Provincia", "Ciudad", "password123");
    }
    @Test
    public void testConstructor(){
        String strings = "A";
        assertEquals(strings, usuario.getNombre());
        assertEquals(strings, usuario.getApellido1());
        assertEquals(strings, usuario.getApellido2());
        assertEquals(strings, usuario.getDocumentoID());
        assertEquals(strings, usuario.getDireccion());
        assertEquals(0, usuario.getTipoDeUsuario());
        assertEquals(strings, usuario.getTelefono());
        assertEquals(0, usuario.getCodigoPostal());
        assertEquals(strings, usuario.getCiudad());
        assertEquals(strings, usuario.getProvincia());
        assertEquals("aaa",usuario.getContraseña());
    }
    @Test
    public void testToString() {
        String expected = "Usuario{id='" + usuario.getId() + "', nombre='John', apellido1='Doe', apellido2='Smith', documentoID='12345678A', tipoDeUsuario=0, fechaDeCreacion=" + usuario.getFechaDeCreacion() + ", telefono='+1234567890', direccion='123 Main St', codigoPostal='12345', provincia='Provincia', ciudad='Ciudad', contraseña='password123'}";
        assertEquals(expected, usuario.toString());
    }

    @Test
    public void testToStringIncorrect() {
        String incorrect = "Usuario{id='" + usuario.getId() + "', nombre='Jane', apellido1='Doe', apellido2='Smith', documentoID='12345678A', tipoDeUsuario=0, fechaDeCreacion=" + usuario.getFechaDeCreacion() + ", telefono='+1234567890', direccion='123 Main St', codigoPostal='12345', provincia='Provincia', ciudad='Ciudad', contraseña='password123'}";
        assertNotEquals(incorrect, usuario.toString());
    }

    @Test
    public void testValidateTelefonoInvalid() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Usuario("John", "Doe", "Smith", "12345678A", 0, "invalidPhone", "123 Main St", "12345", "Provincia", "Ciudad", "password123");
        });
        assertEquals("Invalid phone number", exception.getMessage());
    }

}
