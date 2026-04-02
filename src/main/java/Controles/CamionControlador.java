/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controles;

import DAO.CamionDAO;
import Modelo.Camion;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class CamionControlador {
    private CamionDAO dao = new CamionDAO();

    public void cargarTabla(DefaultTableModel modeloTabla) {
        modeloTabla.setRowCount(0);
        List<Camion> lista = dao.listar();
        for (Camion c : lista) {
            modeloTabla.addRow(new Object[]{
                c.getIdCamion(), c.getPatente(), c.getMarca(), c.getModelo(), 
                c.getRutConductorAsignado(), c.getKilometraje(), "No"
            });
        }
    }

    public boolean registrar(String patente, String marca, String modelo, String anio, String km) {
        Camion c = new Camion();
        c.setPatente(patente);
        c.setMarca(marca);
        c.setModelo(modelo);
        c.setAnio(Integer.parseInt(anio));
        c.setKilometraje(Integer.parseInt(km));
        return dao.insertar(c);
    }

    public boolean editar(String id, String patente, String marca, String modelo, String anio, String km) {
        Camion c = new Camion();
        c.setIdCamion(Integer.parseInt(id));
        c.setPatente(patente);
        c.setMarca(marca);
        c.setModelo(modelo);
        c.setAnio(Integer.parseInt(anio));
        c.setKilometraje(Integer.parseInt(km));
        return dao.modificar(c);
    }

    public boolean borrar(String id) {
        return dao.eliminar(Integer.parseInt(id));
    }
}