/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controle;

import DAO.UsuarioDAO;
import Modelo.Usuarios;
import Vista.Menu;
import Vista.Login;
import javax.swing.JOptionPane;

public class UsuarioControlador {
    private UsuarioDAO dao = new UsuarioDAO();

    public void iniciarSesion(String user, String pass, Login vistaLogin) {
        Usuarios u = dao.validarLogin(user, pass);
        if (u != null) {
            Menu menu = new Menu(u); 
            menu.setVisible(true);
            vistaLogin.dispose(); 
        } else {
            JOptionPane.showMessageDialog(vistaLogin, "Usuario o Contraseña incorrectos");
        }
    }
}