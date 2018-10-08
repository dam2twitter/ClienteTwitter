/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

/**
 *
 * @author gnord
 */
public class Validadores {

    public static boolean validarNombre(String cadena) {
        boolean valida = false;

        for (int i = 0; i < cadena.length(); i++) {
            int asciiValue = (int) cadena.charAt(i);
            if (asciiValue > 64 && asciiValue < 91 || asciiValue > 96
                    && asciiValue < 123) {
            } else {
                valida = true;
            }
        }
        return valida;
    }

    public static boolean validarDni(int dniNumero) {
        boolean valida = false;
        String dni = Integer.toString(dniNumero);
        if (dni.length() == 8||dni.length()==7) {
            valida = true;
        }
        return valida;
    }

    public static boolean validarTelefono(int telefono) {
        boolean valida = false;

        String telf = Integer.toString(telefono);
        if (telf.length() == 9) {
            valida = true;
        }
        return valida;
    }
//hacer validador de email
}
