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
            // Si no hay conductor, muestra un aviso en la celda
            (c.getRutConductorAsignado() != null) ? c.getRutConductorAsignado() : "PENDIENTE",
            c.getKilometraje(),
            // Si no hay mantenimiento registrado, muestra "Al día"
            (c.getEstadoMantenimiento() != null) ? c.getEstadoMantenimiento() : "Al día"
        });
    }

}

    /**
     * Procesa el registro de un nuevo camión
     */
    public boolean registrar(String patente, String marca, String modelo, String anio, String km) {
        try {
            Camion c = new Camion();
            c.setPatente(patente);
            c.setMarca(marca);
            c.setModelo(modelo);
            c.setAnio(Integer.parseInt(anio));
            c.setKilometraje(Integer.parseInt(km));
            return dao.insertar(c);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Procesa la modificación de un camión existente
     */
    public boolean editar(String id, String patente, String marca, String modelo, String anio, String km) {
        try {
            Camion c = new Camion();
            c.setIdCamion(Integer.parseInt(id));
            c.setPatente(patente);
            c.setMarca(marca);
            c.setModelo(modelo);
            c.setAnio(Integer.parseInt(anio));
            c.setKilometraje(Integer.parseInt(km));
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
            return false;
        }
    }
    
    // Añadir este método dentro de la clase CamionControlador
public void buscarEnTabla(DefaultTableModel modeloTabla, String campo, String valor) {
    modeloTabla.setRowCount(0);
    List<Camion> lista = dao.buscar(campo, valor);
    
    for (Camion c : lista) {
        modeloTabla.addRow(new Object[]{
            c.getIdCamion(), 
            c.getPatente(), 
            c.getMarca(), 
            c.getModelo(), 
            c.getNombreChofer() != null ? c.getNombreChofer() : "Sin Asignar",
            c.getKilometraje(), 
            c.getEstadoMantenimiento()
        });
    }
}


}