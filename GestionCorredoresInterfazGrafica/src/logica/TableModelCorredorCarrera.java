/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import modelo.CorredorCarrera;

/**
 *
 * @author daniel regueiro
 */
public class TableModelCorredorCarrera extends AbstractTableModel {

    private final List<CorredorCarrera> listaCorredor;
    private final String[] columnas = {"Nombre", "Apellidos", "Dorsal", "Tiempo"};

    public TableModelCorredorCarrera(List<CorredorCarrera> listaCorredor) {
        this.listaCorredor = listaCorredor;
    }

    @Override
    public int getRowCount() {
        return listaCorredor.size();
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
                return listaCorredor.get(filas).getCorredor().getNombre();
            case 1:
                return listaCorredor.get(filas).getCorredor().getApellidos();
            case 2:
                return listaCorredor.get(filas).getDorsal();
            case 3:
                return listaCorredor.get(filas).getTiempo();
        }
        return null;
    }

}
