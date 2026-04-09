/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Modelo.Camion;
import Conexion.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CamionDAO {

   public List<Camion> listar() {
    List<Camion> lista = new ArrayList<>();
    // Traemos todos los camiones (c), sus conductores (co) y su mantenimiento (m)
    // Usamos una subconsulta en mantenimiento para evitar que los camiones se dupliquen
    String sql = "SELECT c.id_camion, c.patente, c.marca, c.modelo, c.kilometraje, " +
                 "co.nombre_completo, m.tipo_mantenimiento " +
                 "FROM camion c " +
                 "LEFT JOIN conductor co ON c.id_conductor = co.id_conductor " +
                 "LEFT JOIN (SELECT id_camion, MAX(tipo_mantenimiento) as tipo_mantenimiento " +
                 "           FROM mantenimiento GROUP BY id_camion) m ON c.id_camion = m.id_camion " +
                 "ORDER BY c.id_camion ASC"; 

    try (Connection con = Conexion.getConexion();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        
        while (rs.next()) {
            Camion c = new Camion();
            c.setIdCamion(rs.getInt("id_camion"));
            c.setPatente(rs.getString("patente"));
            c.setMarca(rs.getString("marca"));
            c.setModelo(rs.getString("modelo"));
            c.setKilometraje(rs.getInt("kilometraje"));
            c.setRutConductorAsignado(rs.getString("nombre_completo")); 
            c.setEstadoMantenimiento(rs.getString("tipo_mantenimiento"));
            
            lista.add(c);
        }
    } catch (SQLException e) {
        System.err.println("Error al listar los 9 camiones: " + e.getMessage());
    }
    return lista;
}

    
    
    public boolean insertar(Camion c) {
        String sql = "INSERT INTO camion (patente, marca, modelo, anio, kilometraje, id_conductor) VALUES (?,?,?,?,?,?)";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getPatente());
            ps.setString(2, c.getMarca());
            ps.setString(3, c.getModelo());
            ps.setInt(4, c.getAnio());
            ps.setInt(5, c.getKilometraje());
            // OJO: Aquí deberías usar c.getRutConductorAsignado() si es un ID válido
            ps.setString(6, c.getRutConductorAsignado()); 
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar: " + e.getMessage());
            return false;
        }
    }

    public boolean modificar(Camion c) {
        String sql = "UPDATE camion SET patente=?, marca=?, modelo=?, anio=?, kilometraje=? WHERE id_camion=?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getPatente());
            ps.setString(2, c.getMarca());
            ps.setString(3, c.getModelo());
            ps.setInt(4, c.getAnio());
            ps.setInt(5, c.getKilometraje());
            ps.setInt(6, c.getIdCamion());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al modificar: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM camion WHERE id_camion=?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar: " + e.getMessage());
            return false;
        }
    }

    public List<Camion> buscar(String campo, String valor) {
    List<Camion> lista = new ArrayList<>();
    // Usamos la misma lógica del listar pero con un WHERE dinámico
    String sql = "SELECT c.*, co.nombre_completo " +
                 "FROM camion c " +
                 "LEFT JOIN conductor co ON c.id_conductor = co.id_conductor " +
                 "WHERE c." + campo + " LIKE ? " +
                 "ORDER BY c.id_camion ASC";

    try (Connection con = Conexion.getConexion();
         PreparedStatement ps = con.prepareStatement(sql)) {
        
        ps.setString(1, "%" + valor + "%");
        
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Camion c = new Camion();
                c.setIdCamion(rs.getInt("id_camion"));
                c.setPatente(rs.getString("patente"));
                c.setMarca(rs.getString("marca"));
                c.setModelo(rs.getString("modelo"));
                c.setKilometraje(rs.getInt("kilometraje"));
                c.setRutConductorAsignado(rs.getString("nombre_completo"));
                lista.add(c);
            }
        }
    } catch (SQLException e) {
        System.err.println("Error al buscar: " + e.getMessage());
    }
    return lista;
}
}