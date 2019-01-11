/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primerejerciciosocket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daniel
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {

            // TODO code application logic here
            ServerSocket skServidor = new ServerSocket(2000); //socket del servidor

            for (int i = 1; i < 4; i++) {

                Socket skDinamicoComunicacionCliente = skServidor.accept();

                System.out.println("Ha llegado peticion del cliente numero " + i);

                //....enviar informacion al cliente
                String msg = "Hola cliente " + i;

                OutputStream fileSalidaSockect = skDinamicoComunicacionCliente.getOutputStream(); //creo el flujo de salida de datos con el output del socket dinamico
                DataOutputStream skDatosSalida = new DataOutputStream(fileSalidaSockect);//envolvemos el flujo de salida en un flujo de datos primitivos para no tener que partir en bites
                skDatosSalida.writeUTF(msg);//esto envia la informacion por el socket dinamico
                fileSalidaSockect.close();
                skDatosSalida.close();
                skDinamicoComunicacionCliente.close();
                //... hasta aqui la comunicacion con el cliente

            }

            skServidor.close();

        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
