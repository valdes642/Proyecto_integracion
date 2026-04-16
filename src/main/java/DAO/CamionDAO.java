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

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Camion c = new Camion();
                c.setIdCamion(rs.getInt("id_camion"));
                c.setPatente(rs.getString("patente"));
                c.setMarca(rs.getString("marca"));
                c.setModelo(rs.getString("modelo"));
                c.setKilometraje(rs.getInt("kilometraje"));
                c.setNombreChofer(rs.getString("nombre_completo")); // Ajustado para coincidir con el Controlador
                c.setEstadoMantenimiento(rs.getString("tipo_mantenimiento"));
                
                lista.add(c);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar los camiones: " + e.getMessage());
        }
        return lista;
    }

    public boolean insertar(Camion c) {
        // 1. Consulta para insertar el camión (sin conductor por ahora)
        String sqlCamion = "INSERT INTO camion (patente, marca, modelo, anio, kilometraje) VALUES (?,?,?,?,?)";
        // 2. Consulta para insertar el mantenimiento vinculado a ese camión
        String sqlMantenimiento = "INSERT INTO mantenimiento (id_camion, fecha_mantenimiento, tipo_mantenimiento, kilometraje_al_momento) VALUES (?, CURDATE(), ?, ?)";
        
        Connection con = null;
        try {
            con = Conexion.getConnection();
            // Desactivamos el autocommit para hacer una transacción segura
            con.setAutoCommit(false); 

            // --- PASO 1: INSERTAR CAMIÓN ---
            // Le decimos a Java que nos devuelva las claves generadas (el id_camion)
            PreparedStatement psCamion = con.prepareStatement(sqlCamion, Statement.RETURN_GENERATED_KEYS);
            psCamion.setString(1, c.getPatente());
            psCamion.setString(2, c.getMarca());
            psCamion.setString(3, c.getModelo());
            psCamion.setInt(4, c.getAnio());
            psCamion.setInt(5, c.getKilometraje());
            psCamion.executeUpdate();

            // Capturamos el ID del camión recién creado
            ResultSet rs = psCamion.getGeneratedKeys();
            int idCamionGenerado = 0;
            if (rs.next()) {
                idCamionGenerado = rs.getInt(1);
            }

            // --- PASO 2: INSERTAR MANTENIMIENTO ---
            PreparedStatement psMant = con.prepareStatement(sqlMantenimiento);
            psMant.setInt(1, idCamionGenerado); // Usamos el ID recién creado
            psMant.setString(2, c.getEstadoMantenimiento()); // Aquí va "Preventivo" o "Correctivo"
            psMant.setInt(3, c.getKilometraje());
            psMant.executeUpdate();

            // Si todo salió bien, confirmamos los cambios en la base de datos
            con.commit();
            return true;

        } catch (SQLException e) {
            // Si hubo un error (ej. patente duplicada), deshacemos todo para no dejar datos a medias
            try {
                if (con != null) con.rollback();
            } catch (SQLException ex) {
                System.err.println("Error al hacer rollback: " + ex.getMessage());
            }
            System.err.println("Error al insertar: " + e.getMessage());
            return false;
        } finally {
            // Cerramos conexiones y restauramos el autocommit
            try {
                if (con != null) {
                    con.setAutoCommit(true);
                    con.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar conexión: " + e.getMessage());
            }
        }
    }

    public boolean modificar(Camion c) {
        String sqlCamion = "UPDATE camion SET patente=?, marca=?, modelo=?, anio=?, kilometraje=? WHERE id_camion=?";
        // Actualizamos el mantenimiento asociado a ese camión
        String sqlMantenimiento = "UPDATE mantenimiento SET tipo_mantenimiento=? WHERE id_camion=?";
        
        Connection con = null;
        try {
            con = Conexion.getConnection();
            con.setAutoCommit(false); // Iniciar transacción segura

            // 1. Modificar Camión
            PreparedStatement psCamion = con.prepareStatement(sqlCamion);
            psCamion.setString(1, c.getPatente());
            psCamion.setString(2, c.getMarca());
            psCamion.setString(3, c.getModelo());
            psCamion.setInt(4, c.getAnio());
            psCamion.setInt(5, c.getKilometraje());
            psCamion.setInt(6, c.getIdCamion());
            psCamion.executeUpdate();

            // 2. Modificar Mantenimiento
            PreparedStatement psMant = con.prepareStatement(sqlMantenimiento);
            psMant.setString(1, c.getEstadoMantenimiento());
            psMant.setInt(2, c.getIdCamion());
            psMant.executeUpdate();

            con.commit(); // Confirmar cambios
            return true;
        } catch (SQLException e) {
            try {
                if (con != null) con.rollback();
            } catch (SQLException ex) {
                System.err.println("Error al revertir: " + ex.getMessage());
            }
            System.err.println("Error al modificar: " + e.getMessage());
            return false;
        } finally {
            try {
                if (con != null) {
                    con.setAutoCommit(true);
                    con.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar: " + e.getMessage());
            }
        }
    }

    public List<Camion> buscar(String campo, String valor) {
        List<Camion> lista = new ArrayList<>();
        
        // Reconstruimos la consulta para incluir JOINs, igual que en el listar()
        String sql = "SELECT c.id_camion, c.patente, c.marca, c.modelo, c.kilometraje, " +
                     "co.nombre_completo, m.tipo_mantenimiento " +
                     "FROM camion c " +
                     "LEFT JOIN conductor co ON c.id_conductor = co.id_conductor " +
                     "LEFT JOIN (SELECT id_camion, MAX(tipo_mantenimiento) as tipo_mantenimiento " +
                     "           FROM mantenimiento GROUP BY id_camion) m ON c.id_camion = m.id_camion ";

        // Evaluamos a qué tabla pertenece el campo que estamos buscando
        if (campo.equals("tipo_mantenimiento")) {
            sql += "WHERE m.tipo_mantenimiento LIKE ? ";
        } else if (campo.equals("id_conductor")) {
            sql += "WHERE c.id_conductor LIKE ? "; 
        } else {
            sql += "WHERE c." + campo + " LIKE ? ";
        }
        
        sql += "ORDER BY c.id_camion ASC";

        try (Connection con = Conexion.getConnection();
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
                    c.setNombreChofer(rs.getString("nombre_completo")); // Ajustado para coincidir con el Controlador
                    c.setEstadoMantenimiento(rs.getString("tipo_mantenimiento"));
                    lista.add(c);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Elimina un camión y sus registros de mantenimiento asociados.
     */
    public boolean eliminar(int idCamion) {
        String sqlMantenimiento = "DELETE FROM mantenimiento WHERE id_camion = ?";
        String sqlCamion = "DELETE FROM camion WHERE id_camion = ?";
        
        Connection con = null;
        try {
            con = Conexion.getConnection();
            con.setAutoCommit(false); // Iniciar transacción

            // 1. Eliminar primero los registros dependientes (Mantenimiento)
            try (PreparedStatement psMant = con.prepareStatement(sqlMantenimiento)) {
                psMant.setInt(1, idCamion);
                psMant.executeUpdate();
            }

            // 2. Eliminar el registro principal (Camión)
            try (PreparedStatement psCamion = con.prepareStatement(sqlCamion)) {
                psCamion.setInt(1, idCamion);
                psCamion.executeUpdate();
            }

            con.commit(); // Confirmar cambios si todo sale bien
            return true;

        } catch (SQLException e) {
            try {
                if (con != null) con.rollback(); // Revertir en caso de error
            } catch (SQLException ex) {
                System.err.println("Error al revertir eliminación: " + ex.getMessage());
            }
            System.err.println("Error al eliminar el camión: " + e.getMessage());
            return false;
        } finally {
            try {
                if (con != null) {
                    con.setAutoCommit(true);
                    con.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar conexión en eliminar: " + e.getMessage());
            }
        }
    }
}