package Modelo;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Victor Vergara
 */
public class Camion{
    private int idCamion;
    private String marca;
    private String modelo;
    private int anio;
    private int kilometraje;
    private String rutConductorAsignado; 
    private boolean mantenimiento;
    private String patente; // Añadido para coincidir con tu SQL
    
    public Camion() {
    }

    public Camion(int idCamion, String marca, String modelo, int anio, int kilometraje, String rutConductorAsignado, boolean mantenimiento, String patente) {
        this.idCamion = idCamion;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.kilometraje = kilometraje;
        this.rutConductorAsignado = rutConductorAsignado;
        this.mantenimiento = mantenimiento;
        this.patente = patente;
    }

    public int getIdCamion() {
        return idCamion;
    }

    public void setIdCamion(int idCamion) {
        this.idCamion = idCamion;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(int kilometraje) {
        this.kilometraje = kilometraje;
    }

    public String getRutConductorAsignado() {
        return rutConductorAsignado;
    }

    public void setRutConductorAsignado(String rutConductorAsignado) {
        this.rutConductorAsignado = rutConductorAsignado;
    }

    public boolean isMantenimiento() {
        return mantenimiento;
    }

    public void setMantenimiento(boolean mantenimiento) {
        this.mantenimiento = mantenimiento;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    @Override
    public String toString() {
        return "Camion{" + "idCamion=" + idCamion + ", marca=" + marca + ", modelo=" + modelo + ", anio=" + anio + ", kilometraje=" + kilometraje + ", rutConductorAsignado=" + rutConductorAsignado + ", mantenimiento=" + mantenimiento + ", patente=" + patente + '}';
    }

}

