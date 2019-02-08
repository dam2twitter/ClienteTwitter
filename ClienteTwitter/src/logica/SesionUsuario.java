package logica;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import vista.PantallaPrincipal;
import vista.listas.ListaTweets;
import vista.listas.ListaTweetsAdaptador;

public class SesionUsuario {
    
    public static enum TipoLista {USER,HOME};

    private Twitter usuarioActual;
    private PantallaPrincipal pantallaPrincipal;
    private ListaTweets listaActual;
    private File tweetConMedia;
    private TipoLista tipoLista;

    public SesionUsuario(Twitter usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public void cargaInicialContenido(PantallaPrincipal pantallaPrincipal) {
        this.pantallaPrincipal = pantallaPrincipal;
        cargarLista();
        cargarListeners();
        cargarImagenes();
        refrescarLista();
    }
    
    private void cargarLista() {
        tipoLista = TipoLista.USER;
        pantallaPrincipal.getjRadioButtonUserTimeline().setSelected(true);
    }

    private void cargarImagenes() {
        try {
            JLabel jLabelBanner = pantallaPrincipal.getjLabelBanner();
            JLabel jLabelAvatar = pantallaPrincipal.getjLabelAvatar();
            //banner
            ImageIcon banner = new ImageIcon(new URL(usuarioActual.showUser(usuarioActual.getId()).getProfileBanner600x200URL()));
            Image bannerEscalado = banner.getImage().getScaledInstance(jLabelBanner.getWidth(), jLabelBanner.getHeight(), Image.SCALE_DEFAULT);
            banner.setImage(bannerEscalado);
            jLabelBanner.setIcon(banner);
            //avatar
            ImageIcon avatar = new ImageIcon(new URL(usuarioActual.showUser(usuarioActual.getId()).getBiggerProfileImageURL()));
            Image avatarEscalado = avatar.getImage().getScaledInstance(jLabelAvatar.getWidth(), jLabelAvatar.getHeight(), Image.SCALE_DEFAULT);
            avatar.setImage(avatarEscalado);
            jLabelAvatar.setIcon(avatar);
        } catch (MalformedURLException | TwitterException | IllegalStateException ex) {
            ex.printStackTrace();
        }
    }

    private void cargarListeners() {
        JTextArea jTextAreaTwitter = pantallaPrincipal.getjTextAreaTwitter();
        JLabel jLabelCarateres = pantallaPrincipal.getjLabelCarateres();
        //acondicionamiento del textArea y listeners
        jTextAreaTwitter.setLineWrap(true);
        jTextAreaTwitter.setWrapStyleWord(true);
        jTextAreaTwitter.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                if (jTextAreaTwitter.getText().length() >= 240) {
                    ke.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent ke) {
                jLabelCarateres.setText(String.valueOf(240 - jTextAreaTwitter.getText().length()));
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                jLabelCarateres.setText(String.valueOf(240 - jTextAreaTwitter.getText().length()));
            }
        });
    }

    public void refrescarLista() {
        try {
            JList<Status> jLista = pantallaPrincipal.getjListTweets();
            ResponseList<Status> timelineList = null;
            switch (tipoLista) {
                case USER : timelineList = usuarioActual.timelines().getUserTimeline();
                break;
                case HOME : timelineList = usuarioActual.timelines().getHomeTimeline();
                break;
            }
            listaActual = new ListaTweets(timelineList);
            jLista.setModel(listaActual);
            jLista.setCellRenderer(new ListaTweetsAdaptador());
        } catch (TwitterException ex) {
            ex.printStackTrace();
        }
    }

    public void adjuntarMedia() {
        JLabel jLabelAdjunto = pantallaPrincipal.getjLabelAdjunto();
        JFileChooser chooser = new JFileChooser();
        int showDialog = chooser.showDialog(pantallaPrincipal, "Introduce un archivo");
        if (showDialog == JFileChooser.APPROVE_OPTION) {
            tweetConMedia = chooser.getSelectedFile();
            jLabelAdjunto.setText(tweetConMedia.getName());
            JOptionPane.showMessageDialog(pantallaPrincipal, "Archivo cargado");
        }
    }

    public void enviarTweet() {
        JTextArea jTextAreaTwitter = pantallaPrincipal.getjTextAreaTwitter();
        JLabel jLabelCarateres = pantallaPrincipal.getjLabelCarateres();
        JLabel jLabelAdjunto = pantallaPrincipal.getjLabelAdjunto();
        JList<Status> jLista = pantallaPrincipal.getjListTweets();
        if (jTextAreaTwitter.getText().isEmpty()) {
            JOptionPane.showMessageDialog(pantallaPrincipal, "Debes escribir algo antes de poder enviarlo");
        } else {
            try {
                String texto = jTextAreaTwitter.getText();
                StatusUpdate statusUpdate = new StatusUpdate(texto);
                if (tweetConMedia != null) {
                    statusUpdate.media(tweetConMedia);
                }
                usuarioActual.tweets().updateStatus(statusUpdate);
                jTextAreaTwitter.setText("");
                jLabelCarateres.setText("240");
                jLabelAdjunto.setText("");
                tweetConMedia = null;
                JOptionPane.showMessageDialog(pantallaPrincipal, "Tweet enviado");
                refrescarLista();
            } catch (TwitterException ex) {
                System.out.println("Error 1");
                ex.printStackTrace();
            }
        }
    }

    public void borrarTweet(int selectedIndex) {
        JList<Status> jLista = pantallaPrincipal.getjListTweets();
        if (tipoLista != TipoLista.USER) {
             JOptionPane.showMessageDialog(pantallaPrincipal, "No puedes borrar un Tweet de otro usuario");
        }
        if (selectedIndex > -1) {
            try {
                Status tweet = (Status) listaActual.getElementAt(selectedIndex);
                usuarioActual.tweets().destroyStatus(tweet.getId());
                JOptionPane.showMessageDialog(pantallaPrincipal, "Tweet borrado");
                refrescarLista();
            } catch (TwitterException ex) {
                ex.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(pantallaPrincipal, "Debes seleccionar un Tweet de la lista");
        }
    }
    
    public void cambiarLista(String nombreBoton) {
        if (nombreBoton.equals(pantallaPrincipal.getjRadioButtonUserTimeline().getText())) {
            tipoLista = TipoLista.USER;
        } else if (nombreBoton.equals(pantallaPrincipal.getjRadioButtonHomeTimeline().getText())) {
            tipoLista = TipoLista.HOME;
        }
        refrescarLista();
    }

}
