/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author daniel regueiro
 */
public class CorredorCarrera implements Serializable{
    private Corredor corredor;
    private int dorsal;
    private Date tiempo;

    public CorredorCarrera(Corredor corredor, int dorsal) {
        this.corredor = corredor;
        this.dorsal = dorsal;
        
    }

    public Corredor getCorredor() {
        return corredor;
    }

    public void setCorredor(Corredor corredor) {
        this.corredor = corredor;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public Date getTiempo() {
        return tiempo;
    }

    public void setTiempo(Date tiempo) {
        this.tiempo = tiempo;
    }

    @Override
    public String toString() {
        return "CorredorCarrera{" + "corredor=" + corredor + ", dorsal=" + dorsal + ", tiempo=" + tiempo + '}';
    }
    
    
    
}
