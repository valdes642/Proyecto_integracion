/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

public class Chofer {
    private String rut;
    private String nombre;
    private String apellidos;
    private String licencia;
    private String telefono;

    // Constructor vacío
    public Chofer() {
    }

    // Constructor con parámetros
    public Chofer(String rut, String nombre, String apellidos, String licencia, String telefono) {
        this.rut = rut;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.licencia = licencia;
        this.telefono = telefono;
    }

    // Getters y Setters
    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Chofer{" + "rut=" + rut + ", nombre=" + nombre + ", apellidos=" + apellidos + ", licencia=" + licencia + ", telefono=" + telefono + '}';
    }
}