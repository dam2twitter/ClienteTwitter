/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Roge
 */
public class Tema {

    private List<Color> paleta;
    public Colores colores;
    public Dimension resolucion;

    public List<Color> getPaleta() {
        return paleta;
    }

    public void setPaleta(List<Color> paleta) {
        this.paleta = paleta;
    }

    public Colores getColores() {
        return colores;
    }

    public void setColores(Colores colores) {
        this.colores = colores;
    }

    public Dimension getResolucion() {
        return resolucion;
    }

    public void setResolucion(Dimension resolucion) {
        this.resolucion = resolucion;
    }

    public Tema() {
        paleta = new ArrayList<>();
        resolucion = new Dimension();
    }

    public void cambiarPaleta(int tipoColor, Color color) {
        
        Color colorModificar = paleta.get(tipoColor);
        colorModificar = color;

    }

    /**
     * Cambiar del atributo resolución el ancho (width) y el alto(heigth) a
     * través de dos int que se le pasan como parámetro.
     *
     * @param ancho
     * @param alto
     */
    public void cambiarResolucion(int ancho, int alto) {
        resolucion.setSize(alto, ancho);
    }

}
