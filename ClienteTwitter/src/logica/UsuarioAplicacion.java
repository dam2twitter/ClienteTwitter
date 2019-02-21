/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import twitter4j.Twitter;

/**
 *
 * @author daniel
 */
public class UsuarioAplicacion {

    private String password;
    private String usuario;
    private Twitter cuenta;

    public UsuarioAplicacion(String password, String usuario, Twitter cuenta) {
        this.password = password;
        this.usuario = usuario;
        this.cuenta = cuenta;
        EncriptableFactory.getEncriptableSha().encriptar(this.password);
    }

    public Twitter validar(String usuario, String password) {
         String c=EncriptableFactory.getEncriptableSha().encriptar(password);
        if (this.usuario.equals(usuario) && this.password.equals(c)) {

            return cuenta;
        } else {
            return null;
            
        }
    }
}
