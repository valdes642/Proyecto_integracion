/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class GestorVistas {

    // 1. EL MÉTODO DE ATAJOS (Actualizado para volver al menú)
    public static void configurarAtajosGlobales(JFrame ventana, JButton botonPorDefecto, boolean salirDelPrograma) {
        JRootPane rootPane = ventana.getRootPane();

        // Configurar ENTER
        if (botonPorDefecto != null) {
            rootPane.setDefaultButton(botonPorDefecto);
        }

        // Configurar ESCAPE
        KeyStroke escape = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escape, "cerrarVentana");
        rootPane.getActionMap().put("cerrarVentana", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (salirDelPrograma) {
                    System.exit(0);
                } else {
                    volverAlMenu(ventana); // Cambio necesario para que ESC realmente "vuelva"
                }
            }
        });
    }

    // NUEVO MÉTODO: Se encarga de abrir el menú y cerrar la ventana actual
    public static void volverAlMenu(JFrame ventanaActual) {
    Menu menu = new Menu();
    menu.setLocationRelativeTo(null);
    menu.setVisible(true);
    ventanaActual.dispose();
    }

    // 2. EL MÉTODO ORIGINAL PARA ABRIR VISTAS
    public static void abrirVista(JFrame actual, JFrame nueva) {
        nueva.setLocationRelativeTo(null); 
        nueva.setVisible(true);
        if (actual != null) {
            actual.dispose(); 
        }
    }

    // 3. CONFIGURACIÓN BASE DE VENTANA: Título, tamaño fijo y centrado
    public static void configurarVentanaBase(JFrame ventana, String titulo) {
        ventana.setTitle(titulo);
        ventana.setResizable(false); 
        ventana.setLocationRelativeTo(null);
    }

    // 4. LIMPIAR FORMULARIOS (Mejorado para buscar incluso dentro de sub-paneles)
    public static void limpiarCampos(java.awt.Container contenedor) {
        for (java.awt.Component c : contenedor.getComponents()) {
            if (c instanceof javax.swing.JTextField) {
                ((javax.swing.JTextField) c).setText("");
            } else if (c instanceof javax.swing.JComboBox) {
                ((javax.swing.JComboBox<?>) c).setSelectedIndex(0);
            } else if (c instanceof java.awt.Container) {
                // Si encuentra un panel dentro de la ventana, lo revisa también
                limpiarCampos((java.awt.Container) c); 
            }
        }
    }

    // 5. ALERTAS ESTANDARIZADAS
    public static void mostrarMensaje(Component padre, String mensaje, String titulo, boolean esError) {
        int tipo = esError ? JOptionPane.ERROR_MESSAGE : JOptionPane.INFORMATION_MESSAGE;
        JOptionPane.showMessageDialog(padre, mensaje, titulo, tipo);
    }

    // 6. ESTILO DE TABLAS
    public static void configurarTabla(JTable tabla) {
        tabla.getTableHeader().setReorderingAllowed(false); 
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
        
        tabla.getTableHeader().setBackground(new Color(40, 40, 40)); 
        tabla.getTableHeader().setForeground(Color.WHITE);
        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tabla.setRowHeight(25); 
    }
}