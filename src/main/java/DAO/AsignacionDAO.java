/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Conexion.Conexion;
import java.sql.*;

public class AsignacionDAO {
    public boolean asignar(String patente, String rutChofer) {
        // En tu DB, el camión tiene el id_conductor
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
}