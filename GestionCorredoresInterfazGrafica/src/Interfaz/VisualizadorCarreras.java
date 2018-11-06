/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import javax.swing.JOptionPane;
import logica.GestorPrincipal;
import logica.TableModelCarrera;
import logica.TableModelCorredor;
import logica.TableModelCorredorCarrera;
import modelo.Carrera;
import modelo.Corredor;
import modelo.CorredorCarrera;

/**
 *
 * @author daniel regueiro
 */
public class VisualizadorCarreras extends javax.swing.JDialog {

    /**
     * Creates new form VisualizadorCarreras
     */
    public VisualizadorCarreras(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        rellenarTable();
        rellenarTableDisponibles();
    }

    public void rellenarTable() {

        jTableCarreras.setModel(new TableModelCarrera(GestorPrincipal.getInstance().devolverColeccionCarreras()));

    }

    public void rellenarTableDisponibles() {

        jTableCorredoresDisponibles.setModel(new TableModelCorredor(GestorPrincipal.getInstance().devolverColeccionCorredores()));

    }

    public void rellenarTableCorredoresCarrera() {

        jTableCorredoresCarrera.setModel(new TableModelCorredorCarrera(carreraSeleccionada().getCorredores()));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelParticipantes = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCorredoresCarrera = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableCarreras = new javax.swing.JTable();
        jLabelTituloCarreras = new javax.swing.JLabel();
        jButtonVolver = new javax.swing.JButton();
        jButtonEliminarCorredor = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableCorredoresDisponibles = new javax.swing.JTable();
        jLabelParticipantes1 = new javax.swing.JLabel();
        jButtonAnadirCorredor = new javax.swing.JButton();
        jButtonEliminarCarrera = new javax.swing.JButton();
        jButtonVerCorredores = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabelParticipantes.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N

        jTableCorredoresCarrera.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTableCorredoresCarrera);

        jTableCarreras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTableCarreras);

        jLabelTituloCarreras.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabelTituloCarreras.setText("Carrera:");

        jButtonVolver.setText("Volver");
        jButtonVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVolverActionPerformed(evt);
            }
        });

        jButtonEliminarCorredor.setText("Eliminar Corredor");
        jButtonEliminarCorredor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarCorredorActionPerformed(evt);
            }
        });

        jTableCorredoresDisponibles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTableCorredoresDisponibles);

        jLabelParticipantes1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabelParticipantes1.setText("Corredores disponibles:");

        jButtonAnadirCorredor.setText("Añadir Corredor");
        jButtonAnadirCorredor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAnadirCorredorActionPerformed(evt);
            }
        });

        jButtonEliminarCarrera.setText("Eleminar Carrera");
        jButtonEliminarCarrera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarCarreraActionPerformed(evt);
            }
        });

        jButtonVerCorredores.setText("Ver Corredores");
        jButtonVerCorredores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVerCorredoresActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabelParticipantes, javax.swing.GroupLayout.PREFERRED_SIZE, 632, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 632, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelParticipantes1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 632, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabelTituloCarreras, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 632, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButtonAnadirCorredor)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonVerCorredores, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonEliminarCarrera))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButtonVolver, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonEliminarCorredor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabelTituloCarreras, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonVerCorredores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonEliminarCarrera, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelParticipantes1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonAnadirCorredor, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelParticipantes, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonEliminarCorredor, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVolverActionPerformed
        setVisible(false);
    }//GEN-LAST:event_jButtonVolverActionPerformed

    private void jButtonEliminarCarreraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarCarreraActionPerformed
        int seleccionado = jTableCarreras.getSelectedRow();
        if (seleccionado >= 0) {
            Carrera carrera = carreraSeleccionada();
            GestorPrincipal.getInstance().eleminarCarrera(carrera.getIdentificador());
            this.rellenarTable();
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una carrera", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonEliminarCarreraActionPerformed
    public Carrera carreraSeleccionada() {
        Carrera carrera = null;
        int seleccionad = jTableCarreras.getSelectedRow();
        if (seleccionad >= 0) {
            carrera = GestorPrincipal.getInstance().devolverColeccionCarreras().get(seleccionad);

        }
        return carrera;
    }

    public Corredor corredorSeleccionado() {
        Corredor corredor = null;
        int corredorSeleccionado = jTableCorredoresDisponibles.getSelectedRow();
        if (corredorSeleccionado >= 0) {
            corredor = GestorPrincipal.getInstance().devolverColeccionCorredores().get(corredorSeleccionado);
        }
        return corredor;
    }

    private void jButtonAnadirCorredorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAnadirCorredorActionPerformed
        int corredorSeleccionadoTablet = jTableCorredoresDisponibles.getSelectedRow();
        int carreraSeleccionada = jTableCarreras.getSelectedRow();
        int dorsal = 0;
        Corredor corredor = null;
        CorredorCarrera corrCarr;
        boolean anadir = true;
        if (carreraSeleccionada < 0 || corredorSeleccionadoTablet < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione una carrera y un corredor", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            corredor = corredorSeleccionado();
            Carrera carrera = carreraSeleccionada();
            for (CorredorCarrera cor : carrera.getCorredores()) {
                if (cor.getCorredor().getDni() == corredor.getDni()) {
                    JOptionPane.showMessageDialog(this, "El corredor ya esta apuntado", "Error", JOptionPane.ERROR_MESSAGE);
                    anadir = false;
                }
            }
            if (anadir) {
                dorsal = carrera.getCorredores().size() + 1;
                corrCarr = new CorredorCarrera(corredor, dorsal);
                carrera.getCorredores().add(corrCarr);
                JOptionPane.showMessageDialog(this, "Se ha añadido un corredor", "Informacion", JOptionPane.INFORMATION_MESSAGE);
            }
            jTableCorredoresCarrera.setModel(new TableModelCorredorCarrera(carreraSeleccionada().getCorredores()));
        }
        this.rellenarTable();
        this.rellenarTableDisponibles();
  
    }//GEN-LAST:event_jButtonAnadirCorredorActionPerformed

    private void jButtonEliminarCorredorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarCorredorActionPerformed
        int corredorSeleccionado = jTableCorredoresCarrera.getSelectedRow();
        int carreraSeleccionada = jTableCarreras.getSelectedRow();
        if (carreraSeleccionada >= 0 && corredorSeleccionado >= 0) {
            if (jLabelParticipantes.getText().endsWith(carreraSeleccionada().getIdentificador())) {
                carreraSeleccionada().getCorredores().remove(corredorSeleccionado);
                 rellenarTableCorredoresCarrera();
            } else {
                JOptionPane.showMessageDialog(this, "Los corredores no pertenecen a la carrera seleccionada", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una carrera y un corredor", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonEliminarCorredorActionPerformed

    private void jButtonVerCorredoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVerCorredoresActionPerformed

        int carreraSeleccionadaTablet = jTableCarreras.getSelectedRow();
        if (carreraSeleccionadaTablet >= 0) {
            Carrera carrera = carreraSeleccionada();
            jLabelParticipantes.setText("Participantes de la carrera " + carrera.getNombre() + " " + carrera.getIdentificador());
            jTableCorredoresCarrera.setModel(new TableModelCorredorCarrera(carreraSeleccionada().getCorredores()));
        }
    }//GEN-LAST:event_jButtonVerCorredoresActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAnadirCorredor;
    private javax.swing.JButton jButtonEliminarCarrera;
    private javax.swing.JButton jButtonEliminarCorredor;
    private javax.swing.JButton jButtonVerCorredores;
    private javax.swing.JButton jButtonVolver;
    private javax.swing.JLabel jLabelParticipantes;
    private javax.swing.JLabel jLabelParticipantes1;
    private javax.swing.JLabel jLabelTituloCarreras;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableCarreras;
    private javax.swing.JTable jTableCorredoresCarrera;
    private javax.swing.JTable jTableCorredoresDisponibles;
    // End of variables declaration//GEN-END:variables
}
