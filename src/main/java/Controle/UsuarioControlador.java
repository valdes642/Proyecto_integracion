/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controle;

import DAO.UsuarioDAO;
import Modelo.Usuarios;

public class UsuarioControlador {
    
    // Instanciamos el DAO
    UsuarioDAO dao = new UsuarioDAO();
    
    // Método que servirá de puente entre la Vista y el DAO
    public Usuarios iniciarSesion(String username, String password) {
        return dao.validarLogin(username, password);
    }
}