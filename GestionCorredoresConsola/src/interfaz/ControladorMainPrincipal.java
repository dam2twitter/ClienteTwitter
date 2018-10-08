/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import java.text.ParseException;
import logica.GestorCorredores;

/**
 *
 * @author Daniel Regueiro
 */
public class ControladorMainPrincipal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        GestorCorredores gestorCorredores = new GestorCorredores();
        MostradorMenus menus = new MostradorMenus();
        byte entradaMenu;
        gestorCorredores.volcarCSVaColeccion();

        do {
            menus.mostrarMenu();
            entradaMenu = menus.Seguridad();
            switch (entradaMenu) {
                case 1: {
                    try {
                        gestorCorredores.anadirCorredor(menus.crearCorredor());
                    } catch (ParseException ex) {
                        System.out.println("La fecha es incorrecta, use el formato dd/mm/yyyy");
                    }
                }
                break;
                case 2:
                    gestorCorredores.visualizarTodos();
                    break;
                case 3:
                    System.out.println(gestorCorredores.buscarUnCorredorDni(menus.buscarCorredor()));
                    break;
                case 4:
                    System.out.println("Se ha eliminado ha " + gestorCorredores.eleminarUnCorredor(menus.buscarCorredor()));
                    break;
                case 5:
                    menus.submenuModificar(gestorCorredores);
                    break;
                case 6:
                    gestorCorredores.visualizarListaOrdenada();
                    break;
            }
        } while (entradaMenu != 0);
        gestorCorredores.grabarColeccionAcsv();

        System.out.println(
                "Ha salido del programa");

    }

}
