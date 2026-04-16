package Modelo;

import DAO.CamionDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JU_CamionDAO {

    private CamionDAO dao;
    

    private final String PATENTE_PRUEBA = "XXTEST"; 

    @BeforeEach
    void setUp() {
      
        dao = new CamionDAO();
    }

    @Test
    void testInsertarYBuscar() {
       
        Camion c = new Camion();
        c.setPatente(PATENTE_PRUEBA);
        c.setMarca("MarcaTest");
        c.setModelo("ModeloTest");
        c.setAnio(2024);
        c.setKilometraje(0);
        c.setEstadoMantenimiento("Preventivo");

        
        boolean insertado = dao.insertar(c);

      
        assertTrue(insertado, "El método insertar debe devolver true");

 
        List<Camion> encontrados = dao.buscar("patente", PATENTE_PRUEBA);
        assertFalse(encontrados.isEmpty(), "Debe encontrar el camión recién insertado");
        assertEquals(PATENTE_PRUEBA, encontrados.get(0).getPatente());

        if (!encontrados.isEmpty()) {
            boolean borrado = dao.eliminar(encontrados.get(0).getIdCamion());
            assertTrue(borrado, "Debe poder eliminar el camión de prueba");
        }
    }

    @Test
    void testListar() {
        // Act: Ejecutamos el método listar
        List<Camion> lista = dao.listar();

     
        assertNotNull(lista, "El método listar no debe devolver null, al menos una lista vacía");
    }

    @Test
    void testModificar() {
   
        Camion c = new Camion();
        c.setPatente("MODTEST");
        c.setMarca("Original");
        c.setAnio(2000);
        c.setKilometraje(100);
        dao.insertar(c);

  
        List<Camion> tempList = dao.buscar("patente", "MODTEST");
        if (tempList.isEmpty()) {
            fail("No se pudo preparar el dato para la prueba de modificación");
        }
        
        Camion camionInsertado = tempList.get(0);

        // 2. Act: Cambiamos un dato y lo modificamos en la BD
        camionInsertado.setMarca("Modificada");
        boolean modificado = dao.modificar(camionInsertado);

        // 3. Assert: Verificamos
        assertTrue(modificado, "El método modificar debe retornar true");

        // Validamos que el cambio se guardó
        List<Camion> verificacion = dao.buscar("patente", "MODTEST");
        assertEquals("Modificada", verificacion.get(0).getMarca(), "La marca en la BD debe haberse actualizado");

        // 4. Limpieza
        dao.eliminar(camionInsertado.getIdCamion());
    }
}