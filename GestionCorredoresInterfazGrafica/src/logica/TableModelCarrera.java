/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import modelo.Carrera;

/**
 *
 * @author gnord
 */
public class TableModelCarrera extends AbstractTableModel {

    private final List<Carrera> listaCarrera;
    private final String[] columnas = {"Nombre", "Identificador", "Lugar", "Fecha"};

    public TableModelCarrera(List<Carrera> listaCarrera) {
        this.listaCarrera = listaCarrera;
    }

    @Override
    public int getRowCount() {
        return listaCarrera.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override //Este no es obligatorio asi que lo tenemos que implementar nosotros
    public String getColumnName(int i) {
        return columnas[i];
    }

    @Override
    public Object getValueAt(int filas, int columnas) {
        switch (columnas) {

            case 0:
                return listaCarrera.get(filas).getNombre();
            case 1:
                return listaCarrera.get(filas).getIdentificador();
            case 2:
                return listaCarrera.get(filas).getLugar();
            case 3:
                return listaCarrera.get(filas).getFecha();
        }
        return null;
    }

}
