/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
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
        Camion c = new Camion(1, "Scania", "R500", 2020, 150000, "12345678-9", true, "AB1234");
        
        assertEquals(1, c.getIdCamion());
        assertEquals("Scania", c.getMarca());
        assertEquals("R500", c.getModelo());
        assertEquals(2020, c.getAnio());
        assertEquals(150000, c.getKilometraje());
        assertEquals("12345678-9", c.getRutConductorAsignado());
        assertTrue(c.isMantenimiento());
        assertEquals("AB1234", c.getPatente());
        
    }
    
    @Test
    public void testConstructorAndGetters(){
        camion.setIdCamion(2);
        camion.setMarca("Volvo");
        camion.setModelo("FH16");
        camion.setAnio(2019);
        camion.setKilometraje(200000);
        camion.setRutConductorAsignado("87654321-0");
        camion.setMantenimiento(false);
        camion.setPatente("CD5678");

        assertEquals(2, camion.getIdCamion());
        assertEquals("Volvo", camion.getMarca());
        assertEquals("FH16", camion.getModelo());
        assertEquals(2019, camion.getAnio());
        assertEquals(200000, camion.getKilometraje());
        assertEquals("87654321-0", camion.getRutConductorAsignado());
        assertFalse(camion.isMantenimiento());
        assertEquals("CD5678", camion.getPatente());
    }
    
    @Test
    public void testToString() {
        camion = new Camion(1, "Scania", "R500", 2020, 150000, "12345678-9", true, "AB1234");
        String expected = "Camion{idCamion=1, marca=Scania, modelo=R500, anio=2020, kilometraje=150000, rutConductorAsignado=12345678-9, mantenimiento=true, patente=AB1234}";
        
        assertEquals(expected, camion.toString());
    }
}
