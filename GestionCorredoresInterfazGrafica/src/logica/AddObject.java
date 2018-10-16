/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 *
 * @author gnord
 */
public class AddObject extends ObjectOutputStream {
    
    /**
     *creo un objeto outputStream pero sin la funcion de escribir la cabecera
     * Se usa con la clase AnadirObjetoEnDat
     * @param out
     * @throws IOException
     */
    public AddObject(OutputStream out) throws IOException {
super(out);
}
@Override
protected void writeStreamHeader() throws IOException {
reset(); // no escribe la cabecera
}
}