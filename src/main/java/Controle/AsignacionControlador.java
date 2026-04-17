/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controle;

import DAO.AsignacionDAO;
import DAO.CamionDAO;
import DAO.ChoferDAO;
import Modelo.Camion;
import Modelo.Chofer;
import Modelo.Usuarios; // <--- AGREGA ESTA LÍNEA QUE FALTA
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

public class AsignacionControlador {
    private AsignacionDAO daoAsig = new AsignacionDAO();
    private CamionDAO daoCam = new CamionDAO();
    private ChoferDAO daoChof = new ChoferDAO();

    /**
     * Llena los ComboBox de la vista con los datos actuales de la base de datos.
     */
    public void cargarCombos(JComboBox cbCamion, JComboBox cbChofer) {
        cbCamion.removeAllItems();
        cbChofer.removeAllItems();
        
        // Obtiene la lista de camiones y choferes desde sus respectivos DAOs
        for (Camion c : daoCam.listar()) {
            cbCamion.addItem(c.getPatente());
        }
        
        for (Chofer ch : daoChof.listar()) {
            // Formateamos el String para que el usuario vea el RUT y el Nombre
            cbChofer.addItem(ch.getRut() + " - " + ch.getNombre() + " " + ch.getApellidos());
        }
    }

    /**
     * Limpia y vuelve a cargar la tabla visual con las asignaciones reales de la BD.
     */
    public void actualizarTabla(DefaultTableModel modelo) {
        modelo.setRowCount(0); // Limpiar filas previas
        
        // Obtiene las asignaciones actuales (Requiere el método listarAsignaciones en AsignacionDAO)
        List<Object[]> datos = daoAsig.listarAsignaciones();
        if (datos != null) {
            for (Object[] fila : datos) {
                modelo.addRow(fila);
            }
        }
    }

    /**
     * Procesa la asignación en la base de datos.
     */
    public boolean realizarAsignacionSegura(String patente, String itemSeleccionado, Usuarios usuario) {
    // Bloqueo de seguridade para Condutores e Mantemento
    String rol = usuario.getRol();
    if (rol.equalsIgnoreCase("Revision_Conductores") || rol.equalsIgnoreCase("Revision_Mantenimiento")) {
        return false; 
    }
    
    try {
        String rut = itemSeleccionado.split(" - ")[0].trim(); 
        return daoAsig.asignar(patente, rut);
    } catch (Exception e) {
        System.err.println("Error: " + e.getMessage());
        return false;
    }
    }
}