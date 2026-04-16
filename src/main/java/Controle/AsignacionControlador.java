/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controle;

import DAO.AsignacionDAO;
import DAO.CamionDAO;
import DAO.ChoferDAO;
import javax.swing.JComboBox;
import Modelo.Camion;
import Modelo.Chofer;

public class AsignacionControlador {
    private AsignacionDAO daoAsig = new AsignacionDAO();
    private CamionDAO daoCam = new CamionDAO();
    private ChoferDAO daoChof = new ChoferDAO();

    public void cargarCombos(JComboBox cbCamion, JComboBox cbChofer) {
        cbCamion.removeAllItems();
        cbChofer.removeAllItems();
        // Llenar con datos reales de la BD
        for (Camion c : daoCam.listar()) cbCamion.addItem(c.getPatente());
        for (Chofer ch : daoChof.listar()) cbChofer.addItem(ch.getRut());
    }

    public boolean realizarAsignacion(String patente, String rut) {
        return daoAsig.asignar(patente, rut);
    }
}