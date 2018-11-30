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
    private String lookAndFeel;

    public String getLookAndFeel() {
        return lookAndFeel;
    }

    public void setLookAndFeel(String lookAndFeel) {
        this.lookAndFeel = lookAndFeel;
    }

    public int getTiempoEntreCadaCopiaSeguridad() {
        return tiempoEntreCadaCopiaSeguridad;
    }

    public void setTiempoEntreCadaCopiaSeguridad(int tiempoEntreCadaCopiaSeguridad) {
        this.tiempoEntreCadaCopiaSeguridad = tiempoEntreCadaCopiaSeguridad;
    }

    public OpcionesConfiguracion(int tiempoEntreCadaCopiaSeguridad) {
        this.tiempoEntreCadaCopiaSeguridad = tiempoEntreCadaCopiaSeguridad;
    }

   

    
    
    
    
    
}
