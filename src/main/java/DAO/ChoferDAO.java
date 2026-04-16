/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Modelo.Chofer;
import Conexion.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChoferDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public List<Chofer> listar() {
        List<Chofer> lista = new ArrayList<>();
        // Cambiamos 'choferes' por 'conductor'
        String sql = "SELECT * FROM conductor"; 
        try {
            con = Conexion.getConnection(); 
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Chofer c = new Chofer();
                c.setRut(rs.getString("rut"));
                // Mapeamos el nombre_completo de la BD al setNombre de Java
                c.setNombre(rs.getString("nombre_completo")); 
                c.setApellidos(rs.getString("apellidos"));
                c.setLicencia(rs.getString("licencia"));
                c.setTelefono(rs.getString("telefono"));
                lista.add(c);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar choferes: " + e.getMessage());
        } finally {
            try { if (con != null) con.close(); } catch (SQLException e) {}
        }
        return lista;
    }

    public boolean insertar(Chofer c) {
        String sql = "INSERT INTO conductor (rut, nombre_completo, apellidos, licencia, telefono) VALUES (?, ?, ?, ?, ?)";
        try {
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, c.getRut());
            ps.setString(2, c.getNombre());
            ps.setString(3, c.getApellidos());
            ps.setString(4, c.getLicencia());
            ps.setString(5, c.getTelefono());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al insertar chofer: " + e.getMessage());
            return false;
        }
    }

    public boolean modificar(Chofer c) {
        String sql = "UPDATE conductor SET nombre_completo=?, apellidos=?, licencia=?, telefono=? WHERE rut=?";
        try {
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getApellidos());
            ps.setString(3, c.getLicencia());
            ps.setString(4, c.getTelefono());
            ps.setString(5, c.getRut());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al modificar chofer: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(String rut) {
        String sql = "DELETE FROM conductor WHERE rut=?";
        try {
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, rut);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al eliminar chofer: " + e.getMessage());
            return false;
        }
    }

    public List<Chofer> buscar(String campo, String valor) {
        List<Chofer> lista = new ArrayList<>();
        
        // Traducimos el campo "nombre" de Java al "nombre_completo" de SQL para que el buscador no falle
        if (campo.equals("nombre")) {
            campo = "nombre_completo";
        }
        
        String sql = "SELECT * FROM conductor WHERE " + campo + " LIKE ?";
        try {
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + valor + "%"); 
            rs = ps.executeQuery();
            while (rs.next()) {
                Chofer c = new Chofer();
                c.setRut(rs.getString("rut"));
                c.setNombre(rs.getString("nombre_completo"));
                c.setApellidos(rs.getString("apellidos"));
                c.setLicencia(rs.getString("licencia"));
                c.setTelefono(rs.getString("telefono"));
                lista.add(c);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar chofer: " + e.getMessage());
        }
        return lista;
    }
}