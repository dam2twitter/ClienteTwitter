/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import logica.GestorCorredores;
import modelo.Corredor;

/**
 *
 * @author gnord
 */
public class MostradorMenus {

    /**
     * Imprime las opciones del menu que queremos en el programa principal
     */
    public void mostrarMenu() {
        System.out.println("=====================================================");
        System.out.println("Menu gestion corredores  \n");
        System.out.println("Selecciona una opcion: \n");
        System.out.println("Pulse 1 para introducir un corredor\n");
        System.out.println("Pulse 2 para consultar todos los corredores\n");
        System.out.println("Pulse 3 para visualizar un corredor\n");
        System.out.println("Pulse 4 para borrar un corredor\n");
        System.out.println("Pulse 5 para editar un corredor\n");
        System.out.println("Pulse 6 para ver todos los corredores ordenados por fecha nacimiento");
        System.out.println("Pulse 0 para salir\n");
    }

    public void pedirDniparaModificar() {
        System.out.println("=====================================================");
        System.out.println("Menu modificaciones de corredores  \n");
        System.out.println("Introduzca el Dni del corredor a modificar  \n");

    }

    public void submenuModificar(GestorCorredores g) {
        Byte entradaMenuModificar;
        pedirDniparaModificar();
        String dniCorredor = BufferLeerTeclado.leerCadena();
        do {
            mostrarMenuModificarCorredor();
            entradaMenuModificar = SeguridadSubmenu();
            switch (entradaMenuModificar) {
                case 1:
                    System.out.println("Introduzca el nuevo nombre");
                    String variable = BufferLeerTeclado.leerCadena();
                    g.modificarNombre(dniCorredor, variable);

                    break;
                case 2:
                    System.out.println("Introduzca el nuevo Apellido");
                    variable = BufferLeerTeclado.leerCadena();
                    g.modificarApellido(dniCorredor, variable);
                    break;
                case 3:
                    System.out.println("Introduzca el nuevo dni");
                    variable = BufferLeerTeclado.leerCadena();
                    g.modificarDni(dniCorredor, variable);
                    break;
                case 4:
                    System.out.println("Introduzca la nueva fecha de nacimiento");
                    variable = BufferLeerTeclado.leerCadena();
                     {
                        try {
                            g.modificarFecha(dniCorredor, variable);
                        } catch (ParseException ex) {
                            System.out.println("La fecha es incorrecta, use el formato dd/mm/yyyy");
                        }
                    }
                    break;
                case 5:
                    System.out.println("Introduzca la nueva direccion");
                    variable = BufferLeerTeclado.leerCadena();
                    g.modificarDireccion(dniCorredor, variable);
                    break;
            }
        } while (entradaMenuModificar != 0);
        g.grabarColeccionAcsv();
    }

    public void mostrarMenuModificarCorredor() {

        System.out.println("Selecciona una opcion: \n");
        System.out.println("Pulse 1 para modificar el nombre del corredor\n");
        System.out.println("Pulse 2 para modificar el apellido del corredor\n");
        System.out.println("Pulse 3 para modificar el dni del corredor\n");
        System.out.println("Pulse 4 para modificar la fecha de nacimiento del corredor\n");
        System.out.println("Pulse 5 para modificar la direcccion del corredor\n");
        System.out.println("Pulse 0 para salir\n");

    }

    public Corredor crearCorredor() throws ParseException {
        System.out.println("Introduzca el nombre del corredor");
        String nombre = BufferLeerTeclado.leerCadena();
        System.out.println("introduce el apellido del corredor");
        String apellido = BufferLeerTeclado.leerCadena();
        System.out.println("Introduzca el dni del corredor");
        String dni = BufferLeerTeclado.leerCadena();
        System.out.println("introduce la fecha de nacimiento del corredor 'dd/mm/yyyy' ");
        String fecha = BufferLeerTeclado.leerCadena();
        System.out.println("introduce la direccion del corredor");
        String direccion = BufferLeerTeclado.leerCadena();

        Date dia = null;
        dia = (new SimpleDateFormat("dd/mm/yyyy")).parse(fecha);
        return new Corredor(nombre, apellido, dia, dni, direccion);
    }

    public String buscarCorredor() {
        System.out.println("Introduzca el dni del corredor");

        return BufferLeerTeclado.leerCadena();

    }

    /**
     * comprueba que el valor introducido por teclado sea correcto y este entre
     * los ofrecidos en el metodo menu devuelve un byte que usaremos en el bucle
     * del controlador para activar las distintas opciones
     *
     * @return byte
     */
    public byte Seguridad() {
        byte menu = BufferLeerTeclado.leerByte();
        do {
            if (menu < 0 && menu > 6) {
                System.out.println("El numero introducido no es correcto");
                System.out.println("Intentelo de nuevo");
                System.out.println("Introduzca un numero del 1 al 5");
                menu = BufferLeerTeclado.leerByte();
            }
        } while (menu < 0 && menu > 5);
        return menu;

    }

    public byte SeguridadSubmenu() {
        byte menu = BufferLeerTeclado.leerByte();
        do {
            if (menu < 0 && menu > 5) {
                System.out.println("El numero introducido no es correcto");
                System.out.println("Intentelo de nuevo");
                System.out.println("Introduzca un numero del 1 al 5");
                menu = BufferLeerTeclado.leerByte();
            }
        } while (menu < 0 && menu > 5);
        return menu;

    }
}
