/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primerejerciciosocket;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daniel
 */
public class Cliente {


    
    
    public static void main(String[] args) {

        String host="localhost";
        int puerto=2000;
        
        try {
            Socket sk= new Socket(host, puerto);//lanza la peticion de conexion contra el server para conectar
            //intercambio de informacion
            InputStream isSk=sk.getInputStream();
            
            DataInputStream skEntradaDatos=new DataInputStream(isSk);
            
            String smg=skEntradaDatos.readUTF();
            System.out.println(smg);
            
            
            
            //fin de intercambio de informacion
            isSk.close();
            skEntradaDatos.close();
            sk.close();
            System.out.println("Finaliza la conexion con el cliente");
            
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    }
    
}
