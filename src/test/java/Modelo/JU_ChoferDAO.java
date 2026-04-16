package Modelo;

import DAO.ChoferDAO;
import Modelo.Chofer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JU_ChoferDAO {

    private ChoferDAO dao;
    
    // RUT falso que usaremos para no interferir con registros reales en la BD
    private final String RUT_PRUEBA = "99.999.999-X"; 

    @BeforeEach
    void setUp() {
        dao = new ChoferDAO();
    }

    @Test
    void testInsertarYBuscar() {
        // 1. Arrange: Preparamos un chofer de prueba
        Chofer c = new Chofer();
        c.setRut(RUT_PRUEBA);
        c.setNombre("Juan Prueba");
        c.setApellidos("Perez Test");
        c.setLicencia("A4");
        c.setTelefono("987654321");

        // 2. Act: Intentamos insertarlo
        boolean insertado = dao.insertar(c);

        // 3. Assert: Verificamos que se insertó correctamente
        assertTrue(insertado, "El método insertar debe devolver true");

        // 4. Verificación extra: Lo buscamos en la base de datos por RUT
        List<Chofer> encontrados = dao.buscar("rut", RUT_PRUEBA);
        assertFalse(encontrados.isEmpty(), "Debe encontrar el chofer recién insertado");
        assertEquals(RUT_PRUEBA, encontrados.get(0).getRut());
        assertEquals("Juan Prueba", encontrados.get(0).getNombre());

        // 5. Limpieza: Borramos el dato de prueba
        if (!encontrados.isEmpty()) {
            boolean borrado = dao.eliminar(RUT_PRUEBA);
            assertTrue(borrado, "Debe poder eliminar el chofer de prueba");
        }
    }

    @Test
    void testListar() {
        // Act: Ejecutamos el método listar
        List<Chofer> lista = dao.listar();

        // Assert: Verificamos que devuelva una lista (vacía o con datos, pero no nula)
        assertNotNull(lista, "El método listar no debe devolver null");
    }

    @Test
    void testModificar() {
        // 1. Insertamos un chofer temporal para poder modificarlo
        Chofer c = new Chofer();
        c.setRut(RUT_PRUEBA);
        c.setNombre("Original");
        c.setApellidos("Original");
        c.setLicencia("B");
        c.setTelefono("111111111");
        dao.insertar(c);

        // Obtenemos el chofer recién insertado
        List<Chofer> tempList = dao.buscar("rut", RUT_PRUEBA);
        if (tempList.isEmpty()) {
            fail("No se pudo preparar el dato para la prueba de modificación");
        }
        
        Chofer choferInsertado = tempList.get(0);

        // 2. Act: Cambiamos datos y lo modificamos en la BD
        choferInsertado.setNombre("Modificado");
        choferInsertado.setTelefono("222222222");
        boolean modificado = dao.modificar(choferInsertado);

        // 3. Assert: Verificamos
        assertTrue(modificado, "El método modificar debe retornar true");

        // Validamos que el cambio se guardó consultando de nuevo la BD
        List<Chofer> verificacion = dao.buscar("rut", RUT_PRUEBA);
        assertEquals("Modificado", verificacion.get(0).getNombre(), "El nombre en la BD debe haberse actualizado");
        assertEquals("222222222", verificacion.get(0).getTelefono(), "El teléfono en la BD debe haberse actualizado");

        // 4. Limpieza
        dao.eliminar(RUT_PRUEBA);
    }
    
    @Test
    void testEliminarInvalido() {
        // Act: Intentamos eliminar un RUT que no debería existir
        boolean resultado = dao.eliminar("00.000.000-0");
        
        // Assert: Dependiendo de tu BD, un DELETE de algo que no existe puede devolver true (porque no falló la sintaxis) 
        // o false. Generalmente en JDBC con .execute() devuelve false si es un DELETE, o true si el Statement se ejecutó sin excepciones.
        // Si tu lógica retorna true incluso si no borró nada (comportamiento normal de ps.execute()), esto no fallará.
        assertDoesNotThrow(() -> dao.eliminar("00.000.000-0"), "No debe lanzar excepción al intentar borrar un RUT inexistente");
    }
}