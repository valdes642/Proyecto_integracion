/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

// Modelo para los Usuarios (Login)
public class Usuarios {
    private int id;
    private String nombre;
    private String clave;

    public Usuarios() {}
    public Usuarios(int id, String nombre, String clave) {
        this.id = id;
        this.nombre = nombre;
        this.clave = clave;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getClave() { return clave; }
    public void setClave(String clave) { this.clave = clave; }
}

// Modelo para los Camiones
public class Camion {
    private String patente;
    private String marca;

    public Camion(String patente, String marca) {
        this.patente = patente;
        this.marca = marca;
    }
    public String getPatente() { return patente; }
    @Override
    public String toString() { return patente; } // Para que se vea bien en el JComboBox
}

// Modelo para los Choferes
public class Chofer {
    private String rut;
    private String nombre;

    public Chofer(String rut, String nombre) {
        this.rut = rut;
        this.nombre = nombre;
    }
    public String getNombre() { return nombre; }
    @Override
    public String toString() { return nombre; } // Para que se vea bien en el JComboBox
}