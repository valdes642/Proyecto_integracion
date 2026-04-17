/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Conexion.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AsignacionDAO {
    public boolean asignar(String patente, String rutChofer) {
        // El SQL asocia al conductor con el camión
        String sql = "UPDATE camion SET id_conductor = (SELECT id_conductor FROM conductor WHERE rut = ?) WHERE patente = ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, rutChofer);
            ps.setString(2, patente);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    // NUEVO MÉTODO: Para listar las asignaciones guardadas
    public List<Object[]> listarAsignaciones() {
        List<Object[]> lista = new ArrayList<>();
        // Unimos el camión con su conductor asignado
        String sql = "SELECT co.nombre_completo, c.patente " +
                     "FROM camion c " +
                     "INNER JOIN conductor co ON c.id_conductor = co.id_conductor";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Object[]{ rs.getString("nombre_completo"), rs.getString("patente") });
            }
        } catch (SQLException e) {
            System.err.println("Error al listar asignaciones: " + e.getMessage());
        }
        return lista;
    }
}