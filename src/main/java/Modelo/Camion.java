package Camion;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Victor Vergara
 */
public class Camion {
        private int idCamion;
    private String marca;
    private String modelo;
    private int anio;
    private int kilometraje;
    
    private String rutConductorAsignado; 

    public Camion() {
    }

    public Camion(int idCamion, String marca, String modelo, int anio, int kilometraje, String rutConductorAsignado) {
        this.idCamion = idCamion;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.kilometraje = kilometraje;
        this.rutConductorAsignado = rutConductorAsignado;
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
}

