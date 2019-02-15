
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author daniel
 */
public class EncriptableSha256 implements Encriptable {
         private int codigo = 12;

    @Override
    public String encriptar  (String password) {
  
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

        byte[] hash = md.digest(password.getBytes());
        StringBuffer sb = new StringBuffer();

        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    


    }

    @Override
    public String desEncriptar(String strigADesencriptar) {
        return "no tiene metodo desEncriptar";
    }

}