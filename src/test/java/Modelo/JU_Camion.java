package Modelo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Ricardo Tapia C.
 */
public class JU_Camion {
    
    private Camion camion;
    
    public JU_Camion() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        camion = new Camion();
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testConstructorConParametros() {
        // Updated to include the 10 parameters
        Camion c = new Camion(1, "Scania", "R500", 2020, 150000, "12345678-9", true, "AB1234", "Juan Perez", "En Taller");
        
        assertEquals(1, c.getIdCamion());
        assertEquals("Scania", c.getMarca());
        assertEquals("R500", c.getModelo());
        assertEquals(2020, c.getAnio());
        assertEquals(150000, c.getKilometraje());
        assertEquals("12345678-9", c.getRutConductorAsignado());
        assertTrue(c.isMantenimiento());
        assertEquals("AB1234", c.getPatente());
        assertEquals("Juan Perez", c.getNombreChofer());
        assertEquals("En Taller", c.getEstadoMantenimiento());
    }
    
    @Test
    public void testSettersAndGetters(){
        camion.setIdCamion(2);
        camion.setMarca("Volvo");
        camion.setModelo("FH16");
        camion.setAnio(2019);
        camion.setKilometraje(200000);
        camion.setRutConductorAsignado("87654321-0");
        camion.setMantenimiento(false);
        camion.setPatente("CD5678");
        camion.setNombreChofer("Pedro Gomez");
        camion.setEstadoMantenimiento("Operativo");

        assertEquals(2, camion.getIdCamion());
        assertEquals("Volvo", camion.getMarca());
        assertEquals("FH16", camion.getModelo());
        assertEquals(2019, camion.getAnio());
        assertEquals(200000, camion.getKilometraje());
        assertEquals("87654321-0", camion.getRutConductorAsignado());
        assertFalse(camion.isMantenimiento());
        assertEquals("CD5678", camion.getPatente());
        assertEquals("Pedro Gomez", camion.getNombreChofer());
        assertEquals("Operativo", camion.getEstadoMantenimiento());
    }
    
    @Test
    public void testToString() {
        // Updated to reflect all 10 fields exactly as formatted in your class
        camion = new Camion(1, "Scania", "R500", 2020, 150000, "12345678-9", true, "AB1234", "Juan Perez", "En Taller");
        String expected = "Camion{idCamion=1, marca=Scania, modelo=R500, anio=2020, kilometraje=150000, rutConductorAsignado=12345678-9, mantenimiento=true, patente=AB1234, nombreChofer=Juan Perez, estadoMantenimiento=En Taller}";
        
        assertEquals(expected, camion.toString());
    }
}
