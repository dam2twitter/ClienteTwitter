/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

/**
 *
 * @author Roge
 */
public class LoginUsuario {

    private static LoginUsuario INSTANCE;

    public static LoginUsuario getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LoginUsuario();
        }
        return INSTANCE;
    }

    public boolean buscarUsuarioRegistrado(String usuario, String contraseña) {
        return true;
    }

    public String hola() {
        return "hola";
    }
}
