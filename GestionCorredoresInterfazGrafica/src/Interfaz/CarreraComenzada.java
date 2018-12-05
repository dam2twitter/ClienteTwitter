/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import java.io.File;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.JOptionPane;
import logica.EventoLisenerCrono;
import logica.TableModelCorredorCarrera;
import modelo.Carrera;
import modelo.CorredorCarrera;
import org.openide.util.Exceptions;

/**
 *
 * @author daniel
 */
public class CarreraComenzada extends javax.swing.JDialog {

    private Carrera carrera;
    private String strDateFormat = "mm:ss:SSS";
    private Date tiempoCorredor;

    /**
     * Creates new form CarreraComenzada
     */
    public CarreraComenzada(VisualizadorCarreras parent, boolean modal, Carrera carrera) {
        super(parent, modal);
        initComponents();
        ponlaAyuda();
        this.carrera = carrera;
        jLabelTitulo.setText("Participantes de la carrera " + carrera.getNombre() + " " + carrera.getIdentificador());
        cronoCarrera1.anadirLisener(new EventoLisenerCrono() {
            @Override
            public void ejecutar(String dorsal, String tiempo) {
                if (dorsal.equals("") || dorsal == null) {
                    JOptionPane.showMessageDialog(parent, "Introduzca un dorsal", "Error", JOptionPane.ERROR_MESSAGE);
                } else {

                    SimpleDateFormat formato = new SimpleDateFormat(strDateFormat);

                    Iterator iterator = carrera.getCorredores().iterator();
                    while (iterator.hasNext()) {
                        CorredorCarrera corredor = (CorredorCarrera) iterator.next();

                        if (corredor.getDorsal() == Integer.parseInt(dorsal)) {

                            try {
                                tiempoCorredor = formato.parse(tiempo);
                                if (corredor.getTiempo() == null) {
                                    corredor.setTiempo(tiempoCorredor);
                                }
                            } catch (ParseException ex) {
                                Exceptions.printStackTrace(ex);
                            }

                        }

                    }
                    rellenarTableCorredoresCarrera();
                }
            }
        });
        rellenarTableCorredoresCarrera();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCorredoresCarrera = new javax.swing.JTable();
        jButtonCerrar = new javax.swing.JButton();
        jLabelTitulo = new javax.swing.JLabel();
        cronoCarrera1 = new logica.CronoCarrera();
        jButtonVolver = new javax.swing.JButton();
        jButtonAyuda = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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

        jButtonCerrar.setText(org.openide.util.NbBundle.getMessage(CarreraComenzada.class, "CarreraComenzada.jButtonCerrar.text")); // NOI18N
        jButtonCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCerrarActionPerformed(evt);
            }
        });

        jLabelTitulo.setText(org.openide.util.NbBundle.getMessage(CarreraComenzada.class, "CarreraComenzada.jLabelTitulo.text")); // NOI18N

        jButtonVolver.setText(org.openide.util.NbBundle.getMessage(CarreraComenzada.class, "CarreraComenzada.jButtonVolver.text")); // NOI18N
        jButtonVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVolverActionPerformed(evt);
            }
        });

        jButtonAyuda.setText(org.openide.util.NbBundle.getMessage(CarreraComenzada.class, "CarreraComenzada.jButtonAyuda.text")); // NOI18N
        jButtonAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAyudaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(cronoCarrera1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 687, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 698, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jButtonAyuda, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(jButtonCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cronoCarrera1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAyuda, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCerrarActionPerformed
        int comprobante = JOptionPane.showConfirmDialog(rootPane, "Se va a proceder a cerrar la carrera \n Los corredores sin tiempo no seran clasificados");
        if (comprobante == 0) {
            try {

                SimpleDateFormat formato = new SimpleDateFormat(strDateFormat);
                tiempoCorredor = formato.parse("00:00:000");
                System.out.println(comprobante);

                carrera.setAbierta(false);
                Iterator iterator = carrera.getCorredores().iterator();
                while (iterator.hasNext()) {
                    CorredorCarrera corredor = (CorredorCarrera) iterator.next();
                    if (corredor.getTiempo() == null) {
                        corredor.setTiempo(tiempoCorredor);
                    }
                }
                setVisible(false);

            } catch (ParseException ex) {
                Exceptions.printStackTrace(ex);
            }
        }

    }//GEN-LAST:event_jButtonCerrarActionPerformed

    private void jButtonVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVolverActionPerformed
        // TODO add your handling code here:
        Iterator<CorredorCarrera> iterator = carrera.getCorredores().iterator();
        while (iterator.hasNext()) {
            CorredorCarrera corredor = iterator.next();
            corredor.setTiempo(null);

        }

        setVisible(false);


    }//GEN-LAST:event_jButtonVolverActionPerformed
    private void ponlaAyuda() {
        try {
            // Carga el fichero de ayuda
            File fichero = new File("help" + File.separator + "help_set.hs");
            URL hsURL = fichero.toURI().toURL();

            // Crea el HelpSet y el HelpBroker
            HelpSet helpset = new HelpSet(getClass().getClassLoader(), hsURL);
            HelpBroker hb = helpset.createHelpBroker();

            // Pone ayuda a item de menu al pulsarlo y a F1 en ventana
            // principal y secundaria.
            hb.enableHelpOnButton(jButtonAyuda, "comenzar_carrera", helpset);
            hb.enableHelpKey(getRootPane(), "comenzar_carrera", helpset);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void jButtonAyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAyudaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonAyudaActionPerformed

    public void rellenarTableCorredoresCarrera() {
        jTableCorredoresCarrera.setModel(new TableModelCorredorCarrera(carrera.getCorredores()));

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private logica.CronoCarrera cronoCarrera1;
    private javax.swing.JButton jButtonAyuda;
    private javax.swing.JButton jButtonCerrar;
    private javax.swing.JButton jButtonVolver;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableCorredoresCarrera;
    // End of variables declaration//GEN-END:variables

}