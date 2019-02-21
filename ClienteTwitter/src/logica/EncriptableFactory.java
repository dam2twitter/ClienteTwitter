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
public class EncriptableFactory {
    
    public static Encriptable getEncriptableAES(){
    return new EncriptableAES();
    
    
    }
    
    public static Encriptable getEncriptableDelCesar(){
    return new EncriptableDelCesar();
    
    }
    
     public static Encriptable getEncriptableSha(){
    return new EncriptableSha256();
    
    }
    
    
}
