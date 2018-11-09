/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primercomponente;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.Serializable;
import javax.swing.JLabel;

/**
 *
 * @author daniel
 */
public class PrimerComponente extends JLabel implements Serializable {

    /* es un javaBean que tiene que tener estas caracteristicas:
           1 Constructor sin parametros (no recibe entradas, el constructor puede hacer cosas) y publico
           2 implementar serializable
           3 propiedades con getter y setter nombradas de manera estandar, si lo dejo sin getter y setter pasa a ser un atributo interno 
    
    
    
    
    
     */
    private String nombre;
    private static final String SALUDO = "Hola mundo";
    private Font propiedadFont;
    private Color propiedadColor;
    private boolean propiedadBoolean;
    private int propiedadInt;
    private float propiedadFloat;
    private String propiedadString;
    private File propiedadFile;

    public Font getPropiedadFont() {
        return propiedadFont;
    }

    public void setPropiedadFont(Font propiedadFont) {
        this.propiedadFont = propiedadFont;
    }

    public Color getPropiedadColor() {
        return propiedadColor;
    }

    public void setPropiedadColor(Color propiedadColor) {
        this.propiedadColor = propiedadColor;
    }

    public boolean isPropiedadBoolean() {
        return propiedadBoolean;
    }

    public void setPropiedadBoolean(boolean propiedadBoolean) {
        this.propiedadBoolean = propiedadBoolean;
    }

    public int getPropiedadInt() {
        return propiedadInt;
    }

    public void setPropiedadInt(int propiedadInt) {
        this.propiedadInt = propiedadInt;
    }

    public float getPropiedadFloat() {
        return propiedadFloat;
    }

    public void setPropiedadFloat(float propiedadFloat) {
        this.propiedadFloat = propiedadFloat;
    }

    public String getPropiedadString() {
        return propiedadString;
    }

    public void setPropiedadString(String propiedadString) {
        this.propiedadString = propiedadString;
    }

    public File getPropiedadFile() {
        return propiedadFile;
    }

    public void setPropiedadFile(File propiedadFile) {
        this.propiedadFile = propiedadFile;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
        setText(SALUDO + nombre);
    }

    public PrimerComponente() {
        super();
        setText("Hola mundo");
    }

}
