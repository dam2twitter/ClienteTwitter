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
import modelo.Carrera;


/**
 *
 * @author daniel Regueiro
 */
public class AnadirCarreraenDat {
    
     public  void grabar(Carrera carrera,File archivo) {

        FileOutputStream fos;
        try {
            fos = new FileOutputStream(archivo, true);
            AddObject escritor = new AddObject(fos);
            escritor.writeObject(carrera);

        } catch (FileNotFoundException ex) {
            System.out.println("no se encuentra el archivo");
        } catch (IOException ex) {
            System.out.println("no se pudo grabar el archivo");
        }

    }
    
}
