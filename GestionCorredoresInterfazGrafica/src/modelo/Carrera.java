/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author daniel regueiro
 */
public class Carrera implements Serializable{
    private String identificador;
    private String nombre;
    private String lugar;
    private int numParticipantes;
    private Date fecha;
    private List<CorredorCarrera> corredores;
    private boolean abierta;
    private List<Integer> dorsales;
  

    public Carrera( String nombre, String lugar,String identificador,Date fecha,int participantes) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.lugar = lugar;
        this.fecha=fecha;
        this.corredores=new ArrayList<CorredorCarrera>();
        this.numParticipantes=participantes;
    
        this.abierta=true;
        this.dorsales=new ArrayList<Integer>();
    }

 
    public List<Integer> getDorsales() {
        return dorsales;
    }

    public void setDorsales(List<Integer> dorsales) {
        this.dorsales = dorsales;
    }

    public boolean isAbierta() {
        return abierta;
    }

    public void setAbierta(boolean abierta) {
        this.abierta = abierta;
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

    public List<CorredorCarrera> getCorredores() {
        return corredores;
    }

    public void setCorredores(List<CorredorCarrera> corredores) {
        this.corredores = corredores;
    }

    @Override
    public String toString() {
        return "Carrera{" + "identificador=" + identificador + ", nombre=" + nombre + ", lugar=" + lugar + ", numParticipantes=" + numParticipantes + '}';
    }
    
    
}
