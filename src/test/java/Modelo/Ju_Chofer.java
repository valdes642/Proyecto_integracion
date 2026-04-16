package Modelo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Victor Vergara
 */
public class Ju_Chofer {
    
    private Chofer chofer;
    
    public Ju_Chofer() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        // Se inicializa un objeto vacío antes de cada prueba
        chofer = new Chofer();
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testConstructorConParametros() {
        Chofer c = new Chofer("12345678-9", "Juan", "Perez", "A4", "+56912345678");
        
        assertEquals("12345678-9", c.getRut());
        assertEquals("Juan", c.getNombre());
        assertEquals("Perez", c.getApellidos());
        assertEquals("A4", c.getLicencia());
        assertEquals("+56912345678", c.getTelefono());
    }
    
    @Test
    public void testSettersAndGetters() {
        chofer.setRut("98765432-1");
        chofer.setNombre("Pedro");
        chofer.setApellidos("Gomez");
        chofer.setLicencia("A5");
        chofer.setTelefono("+56987654321");

        assertEquals("98765432-1", chofer.getRut());
        assertEquals("Pedro", chofer.getNombre());
        assertEquals("Gomez", chofer.getApellidos());
        assertEquals("A5", chofer.getLicencia());
        assertEquals("+56987654321", chofer.getTelefono());
    }
    
    @Test
    public void testToString() {
        chofer = new Chofer("12345678-9", "Juan", "Perez", "A4", "+56912345678");
        String expected = "Chofer{rut=12345678-9, nombre=Juan, apellidos=Perez, licencia=A4, telefono=+56912345678}";
        
        assertEquals(expected, chofer.toString());
    }
}