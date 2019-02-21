/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import logica.LogicaNegocio;
import twitter4j.Location;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Trend;
import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;
import vista.listas.ListaTweets;
import vista.listas.ListaTweetsAdaptador;

/**
 *
 * @author Ainhoa
 */
public class PantallaPrincipal extends javax.swing.JDialog {

    private Twitter usuarioActual = LogicaNegocio.getInstance().getAdmin();
    private File tweetConMedia;
    private Trend[] listaTrendingTopics = new Trend[10];

    public static enum TipoLista {
        USER, HOME, TRENDING, FAVORITE
    }
    TipoLista listaActiva;

    /**
     * Creates new form PantallaPrincipal
     */
    public PantallaPrincipal(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        cargarContenidoInicial();
       // ponerAyuda();
    }

    private void cargarContenidoInicial() {
        setLocationRelativeTo(null);
        metodoTemporal();
        cargarLista();
        cargarListeners();
        cargarImagenes();
        refrescarTrendingTopics();
        refrescarLista();
    }
    
    
//        private void ponerAyuda() {
//        try {
//            File ficheroAyuda = new File("help" + File.separator + "help_set.hs");
//            URL hsURL = ficheroAyuda.toURI().toURL();
//
//            //Crea el HelpSet y el HelpBroker
//            HelpSet helpset = new HelpSet(getClass().getClassLoader(), hsURL);
//            HelpBroker hb = helpset.createHelpBroker();
//
//            hb.enableHelpOnButton(jButtonAyuda, "pantallaTwitter", helpset);
//            //Al pulsar F1 salta la ayuda
//            hb.enableHelpKey(getRootPane(), "pantallaTwitter", helpset);
//
//        } catch (MalformedURLException ex) {
//            Exceptions.printStackTrace(ex);
//        } catch (HelpSetException ex) {
//            Exceptions.printStackTrace(ex);
//        }
//
//    }
    
    
    

    private void metodoTemporal() {
        jLayeredPaneBanner.setLayer(jLabelAvatar, 0);
        jLayeredPaneBanner.setLayer(jLabelBanner, -1);
    }

    private void cargarLista() {
        listaActiva = TipoLista.USER;
        jRadioButtonUserTimeline.setSelected(true);
        DefaultComboBoxModel<String> defaultComboBoxModel = new DefaultComboBoxModel<String>();
        defaultComboBoxModel.addElement("@");
        defaultComboBoxModel.addElement("#");
        jComboBoxBusqueda.setModel(defaultComboBoxModel);
        jComboBoxBusqueda.setSelectedIndex(0);
    }

    private void cargarListeners() {
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
        jListTrendingTopics.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2 && !me.isConsumed()) {
                    buscarTrendingTopic();
                }
            }
        });
    }

    private void cargarImagenes() {
        try {
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

    private void refrescarLista() {
        try {
            ResponseList<Status> timelineList = null;
            switch (listaActiva) {
                case USER:
                    timelineList = usuarioActual.timelines().getUserTimeline();
                    break;
                case HOME:
                    timelineList = usuarioActual.timelines().getHomeTimeline();
                    break;
            }
            ListaTweets listaActual = new ListaTweets(timelineList);
            jListTweets.setModel(listaActual);
            jListTweets.setCellRenderer(new ListaTweetsAdaptador());

        } catch (TwitterException ex) {
            Logger.getLogger(PantallaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void buscarTrendingTopic() {
        try {
            jRadioButtonTendencias.setSelected(true);
            listaActiva = TipoLista.TRENDING;
            int selectedIndex = jListTrendingTopics.getSelectedIndex();
            String queryBusqueda = listaTrendingTopics[selectedIndex].getQuery();
            QueryResult searchResult = usuarioActual.search(new Query(queryBusqueda));
            List<Status> tweetsAsociados = searchResult.getTweets();
            if (tweetsAsociados.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay tweets asociados para esta búsqueda");
            } else {
                ListaTweets listaActual = new ListaTweets(tweetsAsociados);
                jListTweets.setModel(listaActual);
                jListTweets.setCellRenderer(new ListaTweetsAdaptador());
            }
        } catch (TwitterException ex) {
            ex.printStackTrace();
        }
    }

    private void refrescarTrendingTopics() {
        try {
            Trends placeTrends = usuarioActual.trends().getPlaceTrends(23424950);
            Trend[] trends = placeTrends.getTrends();
            DefaultListModel<String> defaultListModel = new DefaultListModel<>();
            for (int i = 0; i < 10; i++) {
                defaultListModel.addElement(trends[i].getName());
                listaTrendingTopics[i] = trends[i];
            }
            jListTrendingTopics.setModel(defaultListModel);
        } catch (TwitterException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT
     * modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupTipoLista = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListTrendingTopics = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListTweets = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jButtonPublicar = new javax.swing.JButton();
        jButtonBorrar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaTwitter = new javax.swing.JTextArea();
        jLabelCarateres = new javax.swing.JLabel();
        jButtonAdjuntar = new javax.swing.JButton();
        jLabelAdjunto = new javax.swing.JLabel();
        jRadioButtonUserTimeline = new javax.swing.JRadioButton();
        jRadioButtonHomeTimeline = new javax.swing.JRadioButton();
        jRadioButtonTendencias = new javax.swing.JRadioButton();
        jButtonBusqueda = new javax.swing.JButton();
        jButtonRetweet = new javax.swing.JButton();
        jButtonFavorito = new javax.swing.JButton();
        jTextFieldBusqueda = new javax.swing.JTextField();
        jComboBoxBusqueda = new javax.swing.JComboBox<>();
        jRadioButtonFavoritos = new javax.swing.JRadioButton();
        jButtonAyuda = new javax.swing.JButton();
        jLayeredPaneBanner = new javax.swing.JLayeredPane();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabelAvatar = new javax.swing.JLabel();
        jLabelBanner = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jListTrendingTopics.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jListTrendingTopics.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jListTrendingTopics);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("#TOP10 TENDENCIAS");
        jLabel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jListTweets.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jListTweets.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(jListTweets);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("ÚLTIMOS TWEETS");
        jLabel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButtonPublicar.setText("PUBLICAR");
        jButtonPublicar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPublicarActionPerformed(evt);
            }
        });

        jButtonBorrar.setText("BORRAR");
        jButtonBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBorrarActionPerformed(evt);
            }
        });

        jTextAreaTwitter.setColumns(20);
        jTextAreaTwitter.setRows(5);
        jScrollPane3.setViewportView(jTextAreaTwitter);

        jLabelCarateres.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelCarateres.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelCarateres.setText("240");
        jLabelCarateres.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButtonAdjuntar.setText("ADJUNTAR");
        jButtonAdjuntar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdjuntarActionPerformed(evt);
            }
        });

        jLabelAdjunto.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        buttonGroupTipoLista.add(jRadioButtonUserTimeline);
        jRadioButtonUserTimeline.setText("Mis Tweets");
        jRadioButtonUserTimeline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonUserTimelineActionPerformed(evt);
            }
        });

        buttonGroupTipoLista.add(jRadioButtonHomeTimeline);
        jRadioButtonHomeTimeline.setText("Siguiendo");
        jRadioButtonHomeTimeline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonHomeTimelineActionPerformed(evt);
            }
        });

        buttonGroupTipoLista.add(jRadioButtonTendencias);
        jRadioButtonTendencias.setText("Tendencias");
        jRadioButtonTendencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonTendenciasActionPerformed(evt);
            }
        });

        jButtonBusqueda.setText("BÚSQUEDA");
        jButtonBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBusquedaActionPerformed(evt);
            }
        });

        jButtonRetweet.setText("RETWITTEAR");
        jButtonRetweet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRetweetActionPerformed(evt);
            }
        });

        jButtonFavorito.setText("FAVORITO");
        jButtonFavorito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFavoritoActionPerformed(evt);
            }
        });

        jComboBoxBusqueda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        buttonGroupTipoLista.add(jRadioButtonFavoritos);
        jRadioButtonFavoritos.setText("Favoritos");
        jRadioButtonFavoritos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonFavoritosActionPerformed(evt);
            }
        });

        jButtonAyuda.setText("Ayuda");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelAdjunto, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButtonPublicar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelCarateres, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonAdjuntar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextFieldBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                                .addComponent(jComboBoxBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jRadioButtonUserTimeline)
                                        .addGap(18, 18, 18)
                                        .addComponent(jRadioButtonHomeTimeline)
                                        .addGap(18, 18, 18)
                                        .addComponent(jRadioButtonTendencias))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jButtonFavorito, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                    .addGap(0, 0, Short.MAX_VALUE)
                                                    .addComponent(jButtonRetweet))
                                                .addComponent(jButtonBorrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addComponent(jRadioButtonFavoritos))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jButtonAyuda)))))))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelCarateres, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonPublicar, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonAdjuntar)
                    .addComponent(jLabelAdjunto, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBusqueda)
                    .addComponent(jComboBoxBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jRadioButtonHomeTimeline)
                    .addComponent(jRadioButtonTendencias)
                    .addComponent(jRadioButtonUserTimeline)
                    .addComponent(jRadioButtonFavoritos))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonFavorito)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonRetweet)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonBorrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonAyuda)))
                .addContainerGap())
        );

        jLayeredPaneBanner.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabelAvatar.setBackground(new java.awt.Color(0, 0, 0));
        jLabelAvatar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelAvatar.setLabelFor(jLayeredPaneBanner);
        jLabelAvatar.setText(" ");
        jLabelAvatar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLayeredPane1.setLayer(jLabelAvatar, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap(524, Short.MAX_VALUE)
                .addComponent(jLabelAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap(86, Short.MAX_VALUE)
                .addComponent(jLabelAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        jLabelBanner.setLabelFor(jLayeredPaneBanner);

        jLayeredPaneBanner.setLayer(jLayeredPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPaneBanner.setLayer(jLabelBanner, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPaneBannerLayout = new javax.swing.GroupLayout(jLayeredPaneBanner);
        jLayeredPaneBanner.setLayout(jLayeredPaneBannerLayout);
        jLayeredPaneBannerLayout.setHorizontalGroup(
            jLayeredPaneBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPaneBannerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPaneBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPaneBannerLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabelBanner, javax.swing.GroupLayout.PREFERRED_SIZE, 661, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jLayeredPaneBannerLayout.setVerticalGroup(
            jLayeredPaneBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPaneBannerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
            .addGroup(jLayeredPaneBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPaneBannerLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabelBanner, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLayeredPaneBanner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPaneBanner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAdjuntarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdjuntarActionPerformed
        JFileChooser chooser = new JFileChooser();
        int showDialog = chooser.showDialog(this, "Introduce un archivo");
        if (showDialog == JFileChooser.APPROVE_OPTION) {
            tweetConMedia = chooser.getSelectedFile();
            jLabelAdjunto.setText(tweetConMedia.getName());
            JOptionPane.showMessageDialog(this, "Archivo cargado");
        }
    }//GEN-LAST:event_jButtonAdjuntarActionPerformed

    private void jButtonBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBorrarActionPerformed
        int selectedIndex = jListTweets.getSelectedIndex();
        if (listaActiva != TipoLista.USER) {
            JOptionPane.showMessageDialog(this, "No puedes borrar un Tweet de otro usuario");
        } else {
            if (selectedIndex > -1) {
                try {
                    Status tweet = (Status) jListTweets.getModel().getElementAt(selectedIndex);
                    usuarioActual.tweets().destroyStatus(tweet.getId());
                    JOptionPane.showMessageDialog(this, "Tweet borrado");
                    refrescarLista();
                } catch (TwitterException ex) {
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Debes seleccionar un Tweet de la lista");
            }
        }
    }//GEN-LAST:event_jButtonBorrarActionPerformed

    private void jButtonPublicarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPublicarActionPerformed
        if (jTextAreaTwitter.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debes escribir algo antes de poder enviarlo");
        } else if (jLabelCarateres.getText().length() > 240) {
            JOptionPane.showMessageDialog(this, "Has sobrepasado el máximo permitido de caracteres");
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
                JOptionPane.showMessageDialog(this, "Tweet enviado");
                refrescarLista();
            } catch (TwitterException ex) {
                System.out.println("Error 1");
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButtonPublicarActionPerformed


    private void jRadioButtonUserTimelineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonUserTimelineActionPerformed
        listaActiva = TipoLista.USER;
        refrescarLista();
    }//GEN-LAST:event_jRadioButtonUserTimelineActionPerformed

    private void jRadioButtonHomeTimelineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonHomeTimelineActionPerformed
        listaActiva = TipoLista.HOME;
        refrescarLista();
    }//GEN-LAST:event_jRadioButtonHomeTimelineActionPerformed

    private void jRadioButtonTendenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonTendenciasActionPerformed
        int selectedIndex = jListTrendingTopics.getSelectedIndex();
        if (selectedIndex != -1) {
            listaActiva = TipoLista.TRENDING;
            buscarTrendingTopic();
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona una de las diez tendencias en la lista de la izquierda");
        }
    }//GEN-LAST:event_jRadioButtonTendenciasActionPerformed

    private void jButtonRetweetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRetweetActionPerformed
        int selectedIndex = jListTweets.getSelectedIndex();
        if (selectedIndex > -1) {
            try {
                Status tweet = (Status) jListTweets.getModel().getElementAt(selectedIndex);
                if (tweet.isRetweetedByMe()) {
                    usuarioActual.tweets().unRetweetStatus(tweet.getId());
                    JOptionPane.showMessageDialog(this, "El Tweet ha dejado de estar retweeteado");
                } else {
                    usuarioActual.tweets().retweetStatus(tweet.getId());
                    JOptionPane.showMessageDialog(this, "El Tweet ha sido retweeteado");
                }
                refrescarLista();
            } catch (TwitterException ex) {
                ex.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Debes seleccionar un Tweet de la lista");
        }
    }//GEN-LAST:event_jButtonRetweetActionPerformed

    private void jButtonFavoritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFavoritoActionPerformed
        int selectedIndex = jListTweets.getSelectedIndex();
        if (selectedIndex > -1) {
            try {    
                Status tweet = (Status) jListTweets.getModel().getElementAt(selectedIndex);
                boolean favorito = false;
                ResponseList<Status> favorites = usuarioActual.favorites().getFavorites();
                for (Status favorite : favorites) {
                    if (favorite.getId() == tweet.getId()) {
                        favorito = true;
                        break;
                    }
                }
                if (favorito) {
                    usuarioActual.favorites().destroyFavorite(tweet.getId());
                    JOptionPane.showMessageDialog(this, "El Tweet ha dejado de estar marcado como favorito");
                } else {
                    usuarioActual.favorites().createFavorite(tweet.getId());
                    JOptionPane.showMessageDialog(this, "El Tweet ha sido marcado como favorito");
                }
                refrescarLista();
            } catch (TwitterException ex) {
                ex.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Debes seleccionar un Tweet de la lista");
        }
    }//GEN-LAST:event_jButtonFavoritoActionPerformed

    private void jButtonBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBusquedaActionPerformed
        if (!jTextFieldBusqueda.getText().isEmpty()) {
            try {
                Query query = null;
                int selectedIndex = jComboBoxBusqueda.getSelectedIndex();
                if (selectedIndex == 0) { //@ búsqueda de usuario
                    User showUser = usuarioActual.users().showUser(jTextFieldBusqueda.getText());
                    jTextFieldBusqueda.setText("");
                    ResponseList<Status> userTimeline = usuarioActual.getUserTimeline(showUser.getId());
                    ListaTweets listaActual = new ListaTweets(userTimeline);
                    jListTweets.setModel(listaActual);
                    jListTweets.setCellRenderer(new ListaTweetsAdaptador());
                } else {
                    query = new Query(jTextFieldBusqueda.getText());
                    jTextFieldBusqueda.setText("");
                    QueryResult search = usuarioActual.search().search(query);
                    List<Status> tweets = search.getTweets();
                    ListaTweets listaActual = new ListaTweets(tweets);
                    jListTweets.setModel(listaActual);
                    jListTweets.setCellRenderer(new ListaTweetsAdaptador());
                }
            } catch (TwitterException ex) {
                JOptionPane.showMessageDialog(this, "No hay resultados que mostrar para el usuario @" + jTextFieldBusqueda.getText());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Escribe algo en el campo de búsqueda");
        }
    }//GEN-LAST:event_jButtonBusquedaActionPerformed

    private void jRadioButtonFavoritosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonFavoritosActionPerformed
        try {
            ResponseList<Status> favorites = usuarioActual.favorites().getFavorites();
            listaActiva = TipoLista.FAVORITE;
            if (!favorites.isEmpty()) {
                
                ListaTweets listaActual = new ListaTweets(favorites);
                jListTweets.setModel(listaActual);
                jListTweets.setCellRenderer(new ListaTweetsAdaptador());
            } else {
            JOptionPane.showMessageDialog(this, "Actualmente no tienes ningún tweet marcado como favorito");
            }
        } catch (TwitterException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jRadioButtonFavoritosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupTipoLista;
    private javax.swing.JButton jButtonAdjuntar;
    private javax.swing.JButton jButtonAyuda;
    private javax.swing.JButton jButtonBorrar;
    private javax.swing.JButton jButtonBusqueda;
    private javax.swing.JButton jButtonFavorito;
    private javax.swing.JButton jButtonPublicar;
    private javax.swing.JButton jButtonRetweet;
    private javax.swing.JComboBox<String> jComboBoxBusqueda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelAdjunto;
    private javax.swing.JLabel jLabelAvatar;
    private javax.swing.JLabel jLabelBanner;
    private javax.swing.JLabel jLabelCarateres;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPaneBanner;
    private javax.swing.JList<String> jListTrendingTopics;
    private javax.swing.JList<Status> jListTweets;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButtonFavoritos;
    private javax.swing.JRadioButton jRadioButtonHomeTimeline;
    private javax.swing.JRadioButton jRadioButtonTendencias;
    private javax.swing.JRadioButton jRadioButtonUserTimeline;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextAreaTwitter;
    private javax.swing.JTextField jTextFieldBusqueda;
    // End of variables declaration//GEN-END:variables

}
