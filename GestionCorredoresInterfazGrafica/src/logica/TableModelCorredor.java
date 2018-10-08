/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import modelo.Corredor;

/**
 *
 * @author gnord
 */
public class TableModelCorredor extends AbstractTableModel {

    private final List<Corredor> listaCorredor;
    private final String[] columnas = {"Nombre", "Apellidos", "dni"};

    public TableModelCorredor(List<Corredor> listaCorredor) {
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
                return listaCorredor.get(filas).getNombre();
            case 1:
                return listaCorredor.get(filas).getApellidos();
            case 2:
                return listaCorredor.get(filas).getDni();
        }
        return null;
    }

}
