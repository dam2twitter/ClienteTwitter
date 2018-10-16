/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author gnord
 */
public class Carrera implements Serializable{
    private String identificador;
    private String nombre;
    private String lugar;
    private int numParticipantes;
    private Date fecha;
    private List<Corredor> corredores;

    public Carrera( String nombre, String lugar,String identificador) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.lugar = lugar;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public int getNumParticipantes() {
        return numParticipantes;
    }

    public void setNumParticipantes(int numParticipantes) {
        this.numParticipantes = numParticipantes;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<Corredor> getCorredores() {
        return corredores;
    }

    public void setCorredores(List<Corredor> corredores) {
        this.corredores = corredores;
    }

    @Override
    public String toString() {
        return "Carrera{" + "identificador=" + identificador + ", nombre=" + nombre + ", lugar=" + lugar + ", numParticipantes=" + numParticipantes + '}';
    }
    
    
}
