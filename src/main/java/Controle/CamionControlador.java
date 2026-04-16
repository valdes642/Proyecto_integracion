/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controle;

import DAO.CamionDAO;
import Modelo.Camion;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class CamionControlador {

    // Instancia del DAO para comunicarse con la base de datos
    private CamionDAO dao = new CamionDAO();

    /**
     * Llena el modelo de la tabla con los datos de la base de datos
     */
    public void cargarTabla(DefaultTableModel modeloTabla) {
        // 1. Limpiar filas existentes
        modeloTabla.setRowCount(0); 

        // 2. Obtener los datos actualizados
        List<Camion> lista = dao.listar();

        // 3. Agregar cada uno de los registros encontrados
        for (Camion c : lista) {
            modeloTabla.addRow(new Object[]{
                c.getIdCamion(),
                c.getPatente(),
                c.getMarca(),
                c.getModelo(),
                // Se unifica para usar getNombreChofer() y mantener consistencia con buscarEnTabla
                (c.getNombreChofer() != null && !c.getNombreChofer().trim().isEmpty()) ? c.getNombreChofer() : "PENDIENTE",
                c.getKilometraje(),
                // Si no hay mantenimiento registrado, muestra "Al día"
                (c.getEstadoMantenimiento() != null && !c.getEstadoMantenimiento().trim().isEmpty()) ? c.getEstadoMantenimiento() : "Al día"
            });
        }
    }

    /**
     * Procesa el registro de un nuevo camión
     */
    // Cambia el método registrar para incluir tipoMantenimiento
    public boolean registrar(String patente, String marca, String modelo, String anio, String km, String tipoMantenimiento) {
        try {
        Camion c = new Camion();
        c.setPatente(patente);
        c.setMarca(marca);
        c.setModelo(modelo);
        c.setAnio(Integer.parseInt(anio));
        c.setKilometraje(Integer.parseInt(km));
        c.setEstadoMantenimiento(tipoMantenimiento); // <--- IMPORTANTE
        return dao.insertar(c);
    } catch (NumberFormatException e) {
        return false;
    }
}

// Cambia el método editar para incluir tipoMantenimiento
    public boolean editar(String id, String patente, String marca, String modelo, String anio, String km, String tipoMantenimiento) {
    try {
        Camion c = new Camion();
        c.setIdCamion(Integer.parseInt(id));
        c.setPatente(patente);
        c.setMarca(marca);
        c.setModelo(modelo);
        c.setAnio(Integer.parseInt(anio));
        c.setKilometraje(Integer.parseInt(km));
        c.setEstadoMantenimiento(tipoMantenimiento); // <--- IMPORTANTE
        return dao.modificar(c);
    } catch (NumberFormatException e) {
        return false;
    }
    }
    
    /**
     * Procesa la eliminación de un camión
     */
    public boolean borrar(String id) {
        try {
            return dao.eliminar(Integer.parseInt(id));
        } catch (NumberFormatException e) {
            System.err.println("Error de formato numérico al borrar: " + e.getMessage());
            return false;
        }
    }

    /**
     * Busca camiones según un campo y valor, actualizando la tabla
     */
    public void buscarEnTabla(DefaultTableModel modeloTabla, String campo, String valor) {
        // 1. Limpiar filas existentes
        modeloTabla.setRowCount(0);
        
        // 2. Obtener los datos filtrados
        List<Camion> lista = dao.buscar(campo, valor);

        // 3. Llenar la tabla
        for (Camion c : lista) {
            modeloTabla.addRow(new Object[]{
                c.getIdCamion(), 
                c.getPatente(), 
                c.getMarca(), 
                c.getModelo(), 
                (c.getNombreChofer() != null && !c.getNombreChofer().trim().isEmpty()) ? c.getNombreChofer() : "Sin Asignar",
                c.getKilometraje(), 
                (c.getEstadoMantenimiento() != null && !c.getEstadoMantenimiento().trim().isEmpty()) ? c.getEstadoMantenimiento() : "Al día"
            });
        }
    }
}