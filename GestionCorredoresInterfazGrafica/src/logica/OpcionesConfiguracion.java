/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.Serializable;

/**
 *
 * @author daniel
 */
public class OpcionesConfiguracion implements Serializable{
    
    private int tiempoEntreCadaCopiaSeguridad;

    public int getTiempoEntreCadaCopiaSeguridad() {
        return tiempoEntreCadaCopiaSeguridad;
    }

    public void setTiempoEntreCadaCopiaSeguridad(int tiempoEntreCadaCopiaSeguridad) {
        this.tiempoEntreCadaCopiaSeguridad = tiempoEntreCadaCopiaSeguridad;
    }

    public OpcionesConfiguracion(int tiempoEntreCadaCopiaSeguridad) {
        this.tiempoEntreCadaCopiaSeguridad = tiempoEntreCadaCopiaSeguridad;
    }

    @Override
    public String toString() {
        return "OpcionesConfiguracion{" + "tiempoEntreCadaCopiaSeguridad=" + tiempoEntreCadaCopiaSeguridad + '}';
    }

    
    
    
    
    
}
