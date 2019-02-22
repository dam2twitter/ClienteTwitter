/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Componentes;

import java.awt.Image;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import logica.LogicaNegocio;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

/**
 *
 * @author Ainhoa
 */
public class Usuario extends javax.swing.JPanel implements Serializable {

    Twitter twitter = LogicaNegocio.getInstance().getAdmin();
    User usuario;

    public Usuario(Long idUsuario) {
        initComponents();
        try {
            usuario = twitter.showUser(idUsuario);
             cargarComponentes();
        } catch (TwitterException ex) {
            ex.printStackTrace();
        }
    }

    public Usuario() {
        initComponents();
        cargarComponentes();
    }
    
  //falta const.
    
    private void cargarComponentes(){
        screenName();
        numeroSeguidores();
        numeroSeguidos();
        numeroTweetsPublicados();
        fotoPerfil();
        fotoPortada();
    }

    public void screenName() {
        try {
            //String screenName = twitter.getScreenName();
            String screenName = usuario.getScreenName();
            jLabelScreenName.setText(screenName);
        } catch (IllegalStateException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void numeroSeguidores() {
        try {
            //Integer followersCount = twitter.users().showUser(twitter.getId()).getFollowersCount();
            Integer followersCount = usuario.getFollowersCount();;
            jLabelNumeroSeguidores.setText(followersCount.toString());
        } catch (IllegalStateException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void numeroSeguidos() {
        try {
            //Integer friendsCount = twitter.users().showUser(twitter.getId()).getFriendsCount();
            Integer friendsCount = usuario.getFriendsCount();
            jLabelNumeroSeguidores.setText(friendsCount.toString());
        } catch (IllegalStateException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void numeroTweetsPublicados() {
        try {
            //Integer statusesCount = twitter.users().showUser(twitter.getId()).getStatusesCount();
            int statusesCount = usuario.getStatusesCount();
            jLabelNumeroTweets.setText(String.valueOf(statusesCount));
        } catch (IllegalStateException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Image fotoPerfil() {
        try {
            //ImageIcon imagen = new ImageIcon(new URL(twitter.users().showUser(twitter.getId()).getBiggerProfileImageURL()));
            ImageIcon imagen = new ImageIcon(new URL(usuario.getOriginalProfileImageURL()));
            imagen.setImage(imagen.getImage().getScaledInstance(fotoRedondaPerfil.getHeight(), fotoRedondaPerfil.getHeight(), Image.SCALE_DEFAULT));
            fotoRedondaPerfil.setIcon(imagen);
            //no se como se verá por el tema del escalado. Preguntar.
        } catch (IllegalStateException | IOException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }

    public void fotoPortada() {
        try {
            //ImageIcon imagenPortada = new ImageIcon(new URL(twitter.users().showUser(twitter.getId()).getProfileBanner600x200URL()));
            ImageIcon imagenPortada = new ImageIcon(new URL(usuario.getProfileBanner600x200URL()));
            jLabelPortadaUsuario.setSize(300, 100);
            imagenPortada.setImage(imagenPortada.getImage().getScaledInstance(jLabelPortadaUsuario.getWidth(), jLabelPortadaUsuario.getHeight(), Image.SCALE_DEFAULT));
            jLabelPortadaUsuario.setIcon(imagenPortada);
        } catch (MalformedURLException | IllegalStateException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fotoRedondaPerfil = new utils.componenteFotoRedonda.FotoRedonda();
        jLabelPortadaUsuario = new javax.swing.JLabel();
        jLabelNumeroSeguidores = new javax.swing.JLabel();
        jLabelSeguidores = new javax.swing.JLabel();
        jLabelSeguidos = new javax.swing.JLabel();
        jLabelNumeroSeguidos = new javax.swing.JLabel();
        jLabelNombreUsuario = new javax.swing.JLabel();
        jLabelScreenName = new javax.swing.JLabel();
        jLabelTweets = new javax.swing.JLabel();
        jLabelNumeroTweets = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        setPreferredSize(new java.awt.Dimension(320, 250));

        jLabelPortadaUsuario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelNumeroSeguidores.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabelSeguidores.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelSeguidores.setText("Seguidores");

        jLabelSeguidos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelSeguidos.setText("Seguidos");

        jLabelNumeroSeguidos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabelNombreUsuario.setText("nombreDeUsuario");

        jLabelScreenName.setText("@screenName");

        jLabelTweets.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTweets.setText("Tweets");

        jLabelNumeroTweets.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(fotoRedondaPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelNumeroSeguidores, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelSeguidores))
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabelNumeroSeguidos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabelSeguidos, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabelTweets, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                                    .addComponent(jLabelNumeroTweets, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jLabelNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelScreenName, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabelPortadaUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelPortadaUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fotoRedondaPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jLabelScreenName, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(jLabelNumeroTweets, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabelSeguidores, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelSeguidos, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelTweets, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelNumeroSeguidores, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelNumeroSeguidos, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(12, 12, 12))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private utils.componenteFotoRedonda.FotoRedonda fotoRedondaPerfil;
    private javax.swing.JLabel jLabelNombreUsuario;
    private javax.swing.JLabel jLabelNumeroSeguidores;
    private javax.swing.JLabel jLabelNumeroSeguidos;
    private javax.swing.JLabel jLabelNumeroTweets;
    private javax.swing.JLabel jLabelPortadaUsuario;
    private javax.swing.JLabel jLabelScreenName;
    private javax.swing.JLabel jLabelSeguidores;
    private javax.swing.JLabel jLabelSeguidos;
    private javax.swing.JLabel jLabelTweets;
    // End of variables declaration//GEN-END:variables
}
