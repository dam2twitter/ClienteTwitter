/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author gnord
 */
public class Corredor implements Comparable<Corredor> {

    private String nombre;
    private String apellidos;
    private Date fechaNacimiento;
    private String dni;
    private String direccion;

    public Corredor() {
        
    }



    public Corredor(String nombre, String apellidos, Date fechaNacimiento, String dni, String direccion)  {
        this.nombre = nombre;
        this.apellidos = apellidos;
        if (fechaNacimiento == null) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser null");
        }
        this.fechaNacimiento=fechaNacimiento;
        if (dni == null) {
            throw new IllegalArgumentException("El dni no puede ser null");
        }
        this.dni = dni;
        this.direccion = direccion;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        if (dni == null) {
            throw new IllegalArgumentException("El dni no puede ser null");
        }
        this.dni = dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Override
    public String toString() {
        return "Corredor: " + "Nombre=" + nombre + ", Apellidos=" + apellidos + ", FechaNacimiento=" + fechaNacimiento + ", Dni=" + dni + ", Direccion=" + direccion + ".";
    }

    public int compareTo(Corredor t) {
        return this.dni.compareTo(t.dni); //si el dni es null salta un null pointer exception, soluciono en el constructor
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Corredor other = (Corredor) obj;
        if (!Objects.equals(this.dni, other.dni)) {
            return false;
        }
        return true;
    }

}
