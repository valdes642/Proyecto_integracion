/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controle;

import DAO.ChoferDAO;
import Modelo.Chofer;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ChoferControlador {

    // Instancia del DAO para comunicarse con la base de datos
    private ChoferDAO dao = new ChoferDAO();

    /**
     * Llena el modelo de la tabla con los datos de la base de datos
     */
    public void cargarTabla(DefaultTableModel modeloTabla) {
        // 1. Limpiar filas existentes
        modeloTabla.setRowCount(0); 

        // 2. Obtener los datos actualizados
        List<Chofer> lista = dao.listar();

        // 3. Agregar cada uno de los registros encontrados
        for (Chofer c : lista) {
            modeloTabla.addRow(new Object[]{
                c.getRut(),
                c.getNombre(),
                c.getApellidos(),
                c.getLicencia(),
                c.getTelefono()
            });
        }
    }

    /**
     * Procesa el registro de un nuevo chofer
     */
    public boolean registrar(String rut, String nombre, String apellidos, String licencia, String telefono) {
        Chofer c = new Chofer();
        c.setRut(rut);
        c.setNombre(nombre);
        c.setApellidos(apellidos);
        c.setLicencia(licencia);
        c.setTelefono(telefono);
        
        return dao.insertar(c);
    }

    /**
     * Procesa la modificación de un chofer existente
     */
    public boolean editar(String rut, String nombre, String apellidos, String licencia, String telefono) {
        Chofer c = new Chofer();
        c.setRut(rut);
        c.setNombre(nombre);
        c.setApellidos(apellidos);
        c.setLicencia(licencia);
        c.setTelefono(telefono);
        
        return dao.modificar(c);
    }
    
    /**
     * Procesa la eliminación de un chofer (usando el RUT como llave)
     */
    public boolean borrar(String rut) {
        return dao.eliminar(rut);
    }

    /**
     * Busca choferes según un campo y valor, actualizando la tabla
     */
    public void buscarEnTabla(DefaultTableModel modeloTabla, String campo, String valor) {
        // 1. Limpiar filas existentes
        modeloTabla.setRowCount(0);
        
        // 2. Obtener los datos filtrados
        List<Chofer> lista = dao.buscar(campo, valor);

        // 3. Llenar la tabla
        for (Chofer c : lista) {
            modeloTabla.addRow(new Object[]{
                c.getRut(), 
                c.getNombre(), 
                c.getApellidos(), 
                c.getLicencia(), 
                c.getTelefono()
            });
        }
    }
}
