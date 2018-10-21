/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;
import modelo.Carrera;


/**
 *
 * @author daniel regueiro
 */
public class CrearArchivoDatygrabarObjetos {

    /**
     * Crea un archivo y guarda datos en forma de objetos,se pueden guardar los
     * que se necesiten pero si se quieren añadir despues hay que usar la clase
     * AnadirObjetoenDat
     *
     * @param file
     */
    public void crearArchivoYGrabarObjetos(File file, Map carrera) {
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(file);
            ObjectOutputStream escritor = new ObjectOutputStream(fos);

            escritor.writeObject(carrera);
        } catch (FileNotFoundException ex) {
           System.out.println("No se encuentra el archivo, fallo en la clase crearArchivosYGrabarObjetos");
        } catch (IOException ex) {
           System.out.println("No se puede escribir el archivo, fallo en la clase crearArchivosYGrabarObjetos");
        }

    }

}
