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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import logica.LogicaNegocio;

import twitter4j.PagableResponseList;
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
import vista.listas.ListaUsuarios;
import vista.listas.ListaUsuariosAdaptador;

/**
 *
 * @author Ainhoa
 */
public class PantallaPrincipal extends javax.swing.JDialog {

    private Twitter usuarioActual = LogicaNegocio.getInstance().getAdmin();
    private File tweetConMedia;
    ResponseList<Status> listaTweetsUsuario;
    ResponseList<Status> listaTweetsHome;
    ResponseList<Status> listaTweetsFavoritos;
    List<Status> listaTweetsTendencia;
    ResponseList<Status> listaTweetsBusqueda;
    ResponseList<Status> listaTweetsSeguidor;
    ResponseList<Status> listaTweetsSiguiendo;
    private Trend[] listaTrendingTopics = new Trend[10];
    private PagableResponseList<User> listaSeguidores;
    private PagableResponseList<User> listaSiguiendo;
    private boolean seleccionadoTweet = true;

    public static enum TipoLista {
        USER, HOME, TRENDING, FAVORITE, SEARCH, FOLLOWERS, FRIENDS
    }
    TipoLista listaActiva;

    /**
     * Creates new form PantallaPrincipal
     */
    public PantallaPrincipal(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        cargarContenidoInicial();
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

    private void metodoTemporal() {
        jLayeredPaneBanner.setLayer(jLabelAvatar, 0);
        jLayeredPaneBanner.setLayer(jLabelBanner, -1);
    }

    private void cargarLista() {
        listaActiva = TipoLista.USER;
        jRadioButtonUserTimeline.setSelected(true);
        DefaultComboBoxModel<String> defaultComboBoxModel = new DefaultComboBoxModel<String>();
        defaultComboBoxModel.addElement("@usuario");
        defaultComboBoxModel.addElement("#Hashtag");
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
        jListSeleccion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2 && !me.isConsumed()) {
                    refrescarSeleccion();
                }
            }
        });
        jListTweets.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2 && !me.isConsumed()) {
                    extraerScreenNames();
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
                    timelineList = listaTweetsUsuario = usuarioActual.timelines().getUserTimeline();
                    break;
                case HOME:
                    timelineList = listaTweetsUsuario = usuarioActual.timelines().getHomeTimeline();
                    break;
                case FAVORITE:
                    timelineList = listaTweetsFavoritos = usuarioActual.favorites().getFavorites();
                    break;
                case TRENDING:
                case FOLLOWERS:
                case FRIENDS:
                    jListTweets.setSelectedIndex(-1);
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
            int selectedIndex = jListSeleccion.getSelectedIndex();
            String queryBusqueda = listaTrendingTopics[selectedIndex].getQuery();
            QueryResult searchResult = usuarioActual.search(new Query(queryBusqueda));
            listaTweetsTendencia = searchResult.getTweets();
            if (listaTweetsTendencia.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay tweets asociados para esta búsqueda");
            } else {
                ListaTweets listaActual = new ListaTweets(listaTweetsTendencia);
                jListTweets.setModel(listaActual);
                jListTweets.setCellRenderer(new ListaTweetsAdaptador());
            }
        } catch (TwitterException ex) {
            ex.printStackTrace();
        } finally {
            jListTweets.setSelectedIndex(-1);
        }
    }

    private void buscarSeguidor() {
        if (listaSeguidores != null && !listaSeguidores.isEmpty()) {
            try {
                int selectedIndex = jListSeleccion.getSelectedIndex();
                long id = listaSeguidores.get(selectedIndex).getId();
                listaTweetsSeguidor = usuarioActual.timelines().getUserTimeline(id);
                ListaTweets listaActual = new ListaTweets(listaTweetsSeguidor);
                jListTweets.setModel(listaActual);
                jListTweets.setCellRenderer(new ListaTweetsAdaptador());
            } catch (TwitterException ex) {
                ex.printStackTrace();
            } finally {
                jListTweets.setSelectedIndex(-1);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No hay seguidores para esta cuenta");
        }
    }

    private void buscarSiguiendo() {
        if (listaSiguiendo != null && !listaSiguiendo.isEmpty()) {
            try {
                int selectedIndex = jListSeleccion.getSelectedIndex();
                long id = listaSiguiendo.get(selectedIndex).getId();
                listaTweetsSiguiendo = usuarioActual.timelines().getUserTimeline(id);
                ListaTweets listaActual = new ListaTweets(listaTweetsSiguiendo);
                jListTweets.setModel(listaActual);
                jListTweets.setCellRenderer(new ListaTweetsAdaptador());
            } catch (TwitterException ex) {
                ex.printStackTrace();
            } finally {
                jListTweets.setSelectedIndex(-1);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No estás siguiendo a nadie");
        }
    }

    private void refrescarSeleccion() {
        int indiceSeleccionado = jComboBoxSeleccion.getSelectedIndex();
        if (indiceSeleccionado != -1) {
            jListTweets.setSelectedIndex(-1);
            switch (indiceSeleccionado) {
                case 0:
                    listaActiva = TipoLista.TRENDING;
                    buscarTrendingTopic();
                    break;
                case 1:
                    listaActiva = TipoLista.FOLLOWERS;
                    buscarSeguidor();
                    break;
                case 2:
                    listaActiva = TipoLista.FRIENDS;
                    buscarSiguiendo();
                    break;
            }
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
            jListSeleccion.setModel(defaultListModel);
            jListTweets.setSelectedIndex(-1);
        } catch (TwitterException ex) {
            ex.printStackTrace();
        }
    }

    private void refrescarListaSeguidores() {
        try {
            listaSeguidores = usuarioActual.getFollowersList(usuarioActual.getId(), -1);
            DefaultListModel<String> defaultListModel = new DefaultListModel<String>();
            if (listaSeguidores != null && listaSeguidores.size() > 0) {
                for (User seguidor : listaSeguidores) {
                    defaultListModel.addElement("@" + seguidor.getScreenName());
                }
                ListaUsuarios listaUsuarios = new ListaUsuarios(listaSeguidores);
                jListTweets.setModel(listaUsuarios);
                jListTweets.setCellRenderer(new ListaUsuariosAdaptador());
            } else {
                JOptionPane.showMessageDialog(this, "Actualmente tu cuenta no dispone de ningún seguidor");
            }
            jListSeleccion.setModel(defaultListModel);
            jListTweets.setSelectedIndex(-1);
        } catch (TwitterException ex) {
            ex.printStackTrace();
        } catch (IllegalStateException ex) {
            ex.printStackTrace();
        }
    }

    private void refrescarListaSiguiendo() {
        try {
            listaSiguiendo = usuarioActual.getFriendsList(usuarioActual.getId(), -1);
            DefaultListModel<String> defaultListModel = new DefaultListModel<String>();
            if (listaSiguiendo != null && listaSiguiendo.size() > 0) {
                for (User seguidor : listaSiguiendo) {
                    defaultListModel.addElement("@" + seguidor.getScreenName());
                }
                ListaUsuarios listaUsuarios = new ListaUsuarios(listaSiguiendo);
                jListTweets.setModel(listaUsuarios);
                jListTweets.setCellRenderer(new ListaUsuariosAdaptador());
            } else {
                JOptionPane.showMessageDialog(this, "Actualmente tu cuenta no sigue a nadie");
            }
            jListSeleccion.setModel(defaultListModel);
            jListTweets.setSelectedIndex(-1);
        } catch (TwitterException ex) {
            ex.printStackTrace();
        } catch (IllegalStateException ex) {
            ex.printStackTrace();
        }
    }

    private void reiniciarBotones() {
        List<JButton> botones = Arrays.asList(jButtonRetweet, jButtonFavorito, jButtonBorrar, jButtonSeguir);
        for (JButton boton : botones) {
            boton.setEnabled(false);
        }
        String[] textos = new String[]{"RETWEETEAR", "FAVORITO", "BORRAR", "FOLLOW"};
        for (int i = 0; i < botones.size(); i++) {
            botones.get(i).setText(textos[i]);
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
        jListSeleccion = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListTweets = new javax.swing.JList<>();
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
        jComboBoxSeleccion = new javax.swing.JComboBox<>();
        jButtonSeguir = new javax.swing.JButton();
        jRadioButtonBusqueda = new javax.swing.JRadioButton();
        jLayeredPaneBanner = new javax.swing.JLayeredPane();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabelAvatar = new javax.swing.JLabel();
        jLabelBanner = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jListSeleccion.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jListSeleccion.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jListSeleccion);

        jListTweets.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jListTweets.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jListTweets.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListTweetsValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jListTweets);

        jButtonPublicar.setText("PUBLICAR");
        jButtonPublicar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPublicarActionPerformed(evt);
            }
        });

        jButtonBorrar.setText("BORRAR");
        jButtonBorrar.setEnabled(false);
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
        jRadioButtonUserTimeline.setText("  Mis Tweets");
        jRadioButtonUserTimeline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonUserTimelineActionPerformed(evt);
            }
        });

        buttonGroupTipoLista.add(jRadioButtonHomeTimeline);
        jRadioButtonHomeTimeline.setText(" Siguiendo");
        jRadioButtonHomeTimeline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonHomeTimelineActionPerformed(evt);
            }
        });

        buttonGroupTipoLista.add(jRadioButtonTendencias);
        jRadioButtonTendencias.setText(" Tendencias");
        jRadioButtonTendencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonTendenciasActionPerformed(evt);
            }
        });

        jButtonBusqueda.setText("BÚSQUEDA");
        jButtonBusqueda.setEnabled(false);
        jButtonBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBusquedaActionPerformed(evt);
            }
        });

        jButtonRetweet.setText("RETWITTEAR");
        jButtonRetweet.setEnabled(false);
        jButtonRetweet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRetweetActionPerformed(evt);
            }
        });

        jButtonFavorito.setText("FAVORITO");
        jButtonFavorito.setEnabled(false);
        jButtonFavorito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFavoritoActionPerformed(evt);
            }
        });

        jComboBoxBusqueda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        buttonGroupTipoLista.add(jRadioButtonFavoritos);
        jRadioButtonFavoritos.setText(" Favoritos");
        jRadioButtonFavoritos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonFavoritosActionPerformed(evt);
            }
        });

        jComboBoxSeleccion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TENDENCIAS", "SEGUIDORES", "SIGUIENDO" }));
        jComboBoxSeleccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSeleccionActionPerformed(evt);
            }
        });

        jButtonSeguir.setText("FOLLOW");
        jButtonSeguir.setEnabled(false);
        jButtonSeguir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSeguirActionPerformed(evt);
            }
        });

        buttonGroupTipoLista.add(jRadioButtonBusqueda);
        jRadioButtonBusqueda.setText(" Búsqueda");
        jRadioButtonBusqueda.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButtonBusquedaItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                            .addComponent(jComboBoxSeleccion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextFieldBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonFavorito, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButtonRetweet))
                            .addComponent(jButtonBorrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonSeguir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jButtonPublicar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, Short.MAX_VALUE)
                                    .addComponent(jLabelCarateres, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabelAdjunto, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonAdjuntar)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButtonBusqueda)
                            .addComponent(jRadioButtonHomeTimeline)
                            .addComponent(jRadioButtonUserTimeline)
                            .addComponent(jRadioButtonTendencias)
                            .addComponent(jRadioButtonFavoritos))
                        .addGap(35, 35, 35))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jRadioButtonUserTimeline)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButtonHomeTimeline)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButtonTendencias)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButtonFavoritos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButtonBusqueda))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabelCarateres, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addComponent(jButtonPublicar, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelAdjunto, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonAdjuntar))))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBusqueda)
                    .addComponent(jComboBoxBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxSeleccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonFavorito)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonRetweet)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonBorrar)
                        .addGap(106, 106, 106)
                        .addComponent(jButtonSeguir)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLayeredPaneBanner, javax.swing.GroupLayout.PREFERRED_SIZE, 675, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPaneBanner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        if (selectedIndex > -1) {
            try {
                Status tweet = (Status) jListTweets.getModel().getElementAt(selectedIndex);
                if (listaTweetsUsuario.contains(tweet)) {
                    usuarioActual.tweets().destroyStatus(tweet.getId());
                    JOptionPane.showMessageDialog(this, "Tweet borrado");
                    refrescarLista();
                } else {
                    JOptionPane.showMessageDialog(this, "Debes seleccionar un Tweet de la lista");
                }
            } catch (TwitterException ex) {
                ex.printStackTrace();
            } finally {
                jListTweets.setSelectedIndex(-1);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Debes seleccionar un Tweet de la lista");
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
            } finally {
                jListTweets.setSelectedIndex(-1);
            }
        }
    }//GEN-LAST:event_jButtonPublicarActionPerformed


    private void jRadioButtonUserTimelineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonUserTimelineActionPerformed
        jListTweets.setSelectedIndex(-1);
        listaActiva = TipoLista.USER;
        refrescarLista();
    }//GEN-LAST:event_jRadioButtonUserTimelineActionPerformed

    private void jRadioButtonHomeTimelineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonHomeTimelineActionPerformed
        jListTweets.setSelectedIndex(-1);
        listaActiva = TipoLista.HOME;
        refrescarLista();
    }//GEN-LAST:event_jRadioButtonHomeTimelineActionPerformed

    private void jRadioButtonTendenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonTendenciasActionPerformed
        jListTweets.setSelectedIndex(-1);
        int selectedIndex = jListSeleccion.getSelectedIndex();
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
                listaTweetsUsuario = usuarioActual.timelines().getUserTimeline();
            } catch (TwitterException ex) {
                ex.printStackTrace();
            } finally {
                jListTweets.setSelectedIndex(-1);
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
                listaTweetsFavoritos = usuarioActual.favorites().getFavorites();
                for (Status favorite : listaTweetsFavoritos) {
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
                listaTweetsFavoritos = usuarioActual.favorites().getFavorites();
            } catch (TwitterException ex) {
                ex.printStackTrace();
            } finally {
                jListTweets.setSelectedIndex(-1);
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
                JOptionPane.showMessageDialog(this, "No hay resultados para la búsqueda " + jTextFieldBusqueda.getText());
            } finally {
                jListTweets.setSelectedIndex(-1);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Escribe algo en el campo de búsqueda");
        }
    }//GEN-LAST:event_jButtonBusquedaActionPerformed

    private void jRadioButtonFavoritosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonFavoritosActionPerformed
        jListTweets.setSelectedIndex(-1);
        listaActiva = TipoLista.FAVORITE;
        refrescarLista();
    }//GEN-LAST:event_jRadioButtonFavoritosActionPerformed

    private void jComboBoxSeleccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSeleccionActionPerformed
        int indiceSeleccionado = jComboBoxSeleccion.getSelectedIndex();
        jListTweets.setSelectedIndex(-1);
        if (indiceSeleccionado != -1) {
            switch (indiceSeleccionado) {
                case 0:
                    listaActiva = TipoLista.TRENDING;
                    jRadioButtonTendencias.setSelected(true);
                    refrescarTrendingTopics();
                    break;
                case 1:
                    listaActiva = TipoLista.FOLLOWERS;
                    refrescarListaSeguidores();
                    break;
                case 2:
                    listaActiva = TipoLista.FRIENDS;
                    refrescarListaSiguiendo();
                    break;
            }
        }
    }//GEN-LAST:event_jComboBoxSeleccionActionPerformed

    private void jListTweetsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListTweetsValueChanged
        try {
            seleccionadoTweet = !seleccionadoTweet;
            if (seleccionadoTweet) {
                reiniciarBotones();
                int indiceSeleccionado = -1;
                if (jListTweets.getModel() instanceof ListaUsuarios) {
                    System.out.println("Es de tipo usuario");
                    indiceSeleccionado = jComboBoxSeleccion.getSelectedIndex();
                    jButtonSeguir.setEnabled(true);
                    User usuarioSeleccionado = null;
                    switch (indiceSeleccionado) {
                        case 1:
                            //el usuario procede de la lista de seguidores
                            usuarioSeleccionado = listaSeguidores.get(jListTweets.getSelectedIndex());
                            break;
                        case 2:
                            //...de la lista de siguiendo
                            usuarioSeleccionado = listaSiguiendo.get(jListTweets.getSelectedIndex());
                            break;
                        default: //usuarioSeleccionado = listaBusqueda.get(jListTweets.getSelectedIndex());
                            break;
                    }
                    if (listaSiguiendo == null) {
                        listaSiguiendo = usuarioActual.getFriendsList(usuarioActual.getId(), -1);
                    }
                    if (listaSiguiendo != null && !listaSiguiendo.isEmpty() && listaSiguiendo.contains(usuarioSeleccionado)) {
                        jButtonSeguir.setText("UNFOLLOW");
                    }
                } else {
                    System.out.println("Es de tipo tweet");
                    indiceSeleccionado = jListTweets.getSelectedIndex();
                    jButtonRetweet.setEnabled(true);
                    jButtonFavorito.setEnabled(true);
                    jButtonBorrar.setEnabled(true);
                    Status TweetSeleccionado = null;
                    switch (listaActiva) {
                        case USER:
                            TweetSeleccionado = listaTweetsUsuario.get(indiceSeleccionado);
                            break;
                        case HOME:
                            if (listaTweetsHome == null) {
                                listaTweetsHome = usuarioActual.timelines().getHomeTimeline();
                            }
                            if (listaTweetsHome != null && listaTweetsHome.size() > 1) {
                                TweetSeleccionado = listaTweetsHome.get(indiceSeleccionado);
                            } else {
                                JOptionPane.showMessageDialog(this, "Actualmente no existen tweets en tu cronología compartida");
                                return;
                            }
                            break;
                        case FAVORITE:
                            if (listaTweetsFavoritos == null) {
                                listaTweetsFavoritos = usuarioActual.favorites().getFavorites();
                            }
                            if (listaTweetsFavoritos != null && listaTweetsFavoritos.size() > 1) {
                                TweetSeleccionado = listaTweetsFavoritos.get(indiceSeleccionado);
                            } else {
                                JOptionPane.showMessageDialog(this, "No tienes marcado ningún tweet como favorito");
                                return;
                            }
                            break;
                        case TRENDING:
                            TweetSeleccionado = listaTweetsTendencia.get(indiceSeleccionado);
                            break;
                        case FOLLOWERS:
                            TweetSeleccionado = listaTweetsSeguidor.get(indiceSeleccionado);
                            break;
                        case FRIENDS:
                            TweetSeleccionado = listaTweetsSiguiendo.get(indiceSeleccionado);
                            break;
                        case SEARCH:
                            TweetSeleccionado = listaTweetsBusqueda.get(indiceSeleccionado);
                            break;
                    }
                    if (listaTweetsFavoritos == null) {
                        listaTweetsFavoritos = usuarioActual.favorites().getFavorites();
                    }
                    if (listaTweetsFavoritos != null && !listaTweetsFavoritos.isEmpty() && listaTweetsFavoritos.contains(TweetSeleccionado)) {
                        jButtonFavorito.setText("NO FAVORITO");
                    }
                    if (TweetSeleccionado.isRetweetedByMe()) {
                        jButtonRetweet.setText("UNRETWEET");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jListTweetsValueChanged

    private void jButtonSeguirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSeguirActionPerformed
        int selectedIndex = jListTweets.getSelectedIndex();
        if (selectedIndex > -1) {
            try {
                User user = null;
                if (listaActiva == TipoLista.FOLLOWERS) {
                    user = listaSeguidores.get(selectedIndex);
                } else if (listaActiva == TipoLista.FRIENDS) {
                    user = listaSiguiendo.get(selectedIndex);
                } else {
                    // user = listaBusqueda.get(selectedIndex);
                }
                if (listaSiguiendo.contains(user)) {
                    usuarioActual.friendsFollowers().destroyFriendship(user.getId());
                    JOptionPane.showMessageDialog(this, "Has dejado de seguir a " + usuarioActual.getScreenName());
                } else {
                    usuarioActual.friendsFollowers().createFriendship(user.getId(), true);
                    JOptionPane.showMessageDialog(this, "Has comenzado a seguir a " + usuarioActual.getScreenName());
                }
            } catch (TwitterException ex) {
                ex.printStackTrace();
            } finally {
                jListTweets.setSelectedIndex(-1);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Debes seleccionar un Tweet de la lista");
        }
    }//GEN-LAST:event_jButtonSeguirActionPerformed

    private void jRadioButtonBusquedaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButtonBusquedaItemStateChanged
        jListTweets.setSelectedIndex(-1);
        if (jRadioButtonBusqueda.isSelected()) {
            jButtonBusqueda.setEnabled(true);
        } else {
            jButtonBusqueda.setEnabled(false);
        }
    }//GEN-LAST:event_jRadioButtonBusquedaItemStateChanged

    private void extraerScreenNames() {
        if (jListTweets.getModel() instanceof ListaTweets) {
            int selectedIndex = jListTweets.getSelectedIndex();
            if (selectedIndex > -1) {
                Status tweet = null;
                switch(listaActiva) {
                    case FOLLOWERS : tweet = listaTweetsSeguidor.get(selectedIndex);
                    break;
                    case FRIENDS : tweet = listaTweetsSiguiendo.get(selectedIndex);
                    break;
                    case HOME : tweet = listaTweetsHome.get(selectedIndex);
                    break;
                    case FAVORITE : tweet = listaTweetsUsuario.get(selectedIndex);
                    break;
                    case TRENDING : tweet = listaTweetsTendencia.get(selectedIndex);
                    break;
                    case USER : tweet = listaTweetsUsuario.get(selectedIndex);
                    break;
                    default: return;
                }
                String texto = tweet.getText();
                if (texto.contains("@")) {
                    String screenNames = "";
                    String[] tokens = texto.replaceAll("[¿?¡!,\\.:]","").split(" ");
                    for (String token : tokens) {
                        if (token.contains("@")) {
                            screenNames += (token.replaceAll("@","") + ",");
                        }
                    };
                    if (!screenNames.isEmpty()) {
                        try {
                            ResponseList<User> usuarios = usuarioActual.users().lookupUsers(screenNames.substring(0, screenNames.length() - 1));
                            if (!usuarios.isEmpty()) {
                                ListaUsuarios listaUsuarios = new ListaUsuarios(usuarios);
                                jListTweets.setModel(listaUsuarios);
                                jListTweets.setCellRenderer(new ListaUsuariosAdaptador());
                            }
                        } catch (TwitterException ex) {
                            Logger.getLogger(PantallaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupTipoLista;
    private javax.swing.JButton jButtonAdjuntar;
    private javax.swing.JButton jButtonBorrar;
    private javax.swing.JButton jButtonBusqueda;
    private javax.swing.JButton jButtonFavorito;
    private javax.swing.JButton jButtonPublicar;
    private javax.swing.JButton jButtonRetweet;
    private javax.swing.JButton jButtonSeguir;
    private javax.swing.JComboBox<String> jComboBoxBusqueda;
    private javax.swing.JComboBox<String> jComboBoxSeleccion;
    private javax.swing.JLabel jLabelAdjunto;
    private javax.swing.JLabel jLabelAvatar;
    private javax.swing.JLabel jLabelBanner;
    private javax.swing.JLabel jLabelCarateres;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPaneBanner;
    private javax.swing.JList<String> jListSeleccion;
    private javax.swing.JList<Status> jListTweets;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButtonBusqueda;
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
