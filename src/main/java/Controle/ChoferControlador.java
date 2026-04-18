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

    private ChoferDAO dao = new ChoferDAO();

    public void cargarTabla(DefaultTableModel modeloTabla) {
        modeloTabla.setRowCount(0); 
        List<Chofer> lista = dao.listar();
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

    // Método registrar corregido con todos los parámetros
    public boolean registrar(String rut, String nombre, String apellidos, String licencia, String telefono) {
        Chofer c = new Chofer();
        c.setRut(rut);
        c.setNombre(nombre);
        c.setApellidos(apellidos);
        c.setLicencia(licencia);
        c.setTelefono(telefono);
        
        return dao.insertar(c);
    }

    public boolean editar(String rut, String nombre, String apellidos, String licencia, String telefono) {
        Chofer c = new Chofer();
        c.setRut(rut);
        c.setNombre(nombre);
        c.setApellidos(apellidos);
        c.setLicencia(licencia);
        c.setTelefono(telefono);
        
        return dao.modificar(c);
    }
    
    public boolean borrar(String rut) {
        return dao.eliminar(rut);
    }

    public void buscarEnTabla(DefaultTableModel modeloTabla, String campo, String valor) {
        modeloTabla.setRowCount(0);
        List<Chofer> lista = dao.buscar(campo, valor);
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