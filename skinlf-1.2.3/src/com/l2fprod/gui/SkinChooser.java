/* ====================================================================
 *
 * Skin Look And Feel 1.2.3 License.
 *
 * Copyright (c) 2000-2002 L2FProd.com.  All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowlegement:
 *       "This product includes software developed by L2FProd.com
 *        (http://www.L2FProd.com/)."
 *    Alternately, this acknowlegement may appear in the software itself,
 *    if and wherever such third-party acknowlegements normally appear.
 *
 * 4. The names "Skin Look And Feel", "SkinLF" and "L2FProd.com" must not
 *    be used to endorse or promote products derived from this software
 *    without prior written permission. For written permission, please
 *    contact info@L2FProd.com.
 *
 * 5. Products derived from this software may not be called "SkinLF"
 *    nor may "SkinLF" appear in their names without prior written
 *    permission of L2FProd.com.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL L2FPROD.COM OR ITS CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * ====================================================================
 */
package com.l2fprod.gui;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import java.util.ResourceBundle;
import javax.swing.*;

import com.l2fprod.util.OS;
import com.l2fprod.gui.plaf.skin.*;

/**
 * Skin Chooser. <br>
 *
 *
 * @author    $Author: l2fprod $
 * @created   27 avril 2002
 * @version   $Revision: 1.5 $, $Date: 2002/04/27 11:45:57 $
 */
public class SkinChooser extends JPanel {

  private JList skinList;
  private String[] directories;

  private JCheckBox backgroundCheckBox, scrollBarCheckBox;

  private ResourceBundle bundle;

  private boolean themePackMode = false;

  final static String REFRESH_CMD = "refresh";
  final static String PREVIEW_CMD = "preview";
  final static String GETSKINS_CMD = "getskins";

  /**
   * Construct a new SkinChooser pane.
   */
  public SkinChooser() {
    loadResourceBundle();

    setLayout(new BorderLayout(3, 3));

    JPanel listPane = new JPanel(new BorderLayout(3, 3));

    JPanel buttonPane = new JPanel(new GridLayout(1, 3, 3, 3));

    JButton button = new JButton(bundle.getString("SkinChooser.getskins"));
    buttonPane.add(button);
    button.addActionListener(new GetSkinsAction());
    button.setToolTipText(bundle.getString("SkinChooser.getskins.tip"));

    button = new JButton(bundle.getString("SkinChooser.preview"));
    buttonPane.add(button);
    button.addActionListener(new PreviewAction());

    button = new JButton(bundle.getString("SkinChooser.refresh"));
    buttonPane.add(button);
    button.addActionListener(new RefreshAction());

    listPane.add("Center", new JScrollPane(skinList = new JList()));
    listPane.add("South", buttonPane);

    add("Center", listPane);

    Box optionPane = Box.createVerticalBox();
    optionPane.add(backgroundCheckBox = new JCheckBox(bundle.getString("SkinChooser.enableBackground")));
    optionPane.add(scrollBarCheckBox = new JCheckBox(bundle.getString("SkinChooser.enableScrollBar")));
    add("East", optionPane);
  }

  /**
   * Set search paths
   *
   * @param directories  search paths
   */
  public void setSkinLocations(String[] directories) {
    this.directories = directories;
    Vector skins = new Vector();
    for (int i = 0, c = directories.length; i < c; i++) {
      buildSkinList(skins, new File(directories[i]));
    }
    skinList.setListData(skins);
  }

  /**
   * Set theme pack mode to true if you want to select a theme pack from the
   * chooser.
   *
   * @param b  the new value
   */
  public void setThemePackMode(boolean b) {
    themePackMode = b;
  }

  /**
   * @return   search paths
   */
  public String[] getSkinLocations() {
    return directories;
  }

  /**
   * @return   true if the chooser shows only theme packs
   */
  public boolean getThemePackMode() {
    return themePackMode;
  }

  /**
   * @return   the currently selected skins
   */
  public String[] getSelectedSkins() {
    return (String[]) skinList.getSelectedValues();
  }

  /**
   * Refresh the skin list.
   *
   * @see   #setSkinLocations
   */
  public void refreshList() {
    if ((directories != null) && (directories.length > 0)) {
      setSkinLocations(directories);
    }
  }

  /**
   * Apply current selection. <br>
   * The method sets the current skin (SkinLookAndFeel.setSkin) then calls
   * UIManager.setLookAndFeel.
   *
   * @exception Exception  Description of Exception
   */
  public void apply() throws Exception {
    Object[] values = skinList.getSelectedValues();
    if ((values == null) ||
        (themePackMode && values.length != 1)
        || (!themePackMode && values.length != 2)) {
      return;
    }

    UIManager.put("JDesktopPane.backgroundEnabled",
        backgroundCheckBox.isSelected() ? Boolean.TRUE : null);
    UIManager.put("ScrollBar.alternateLayout",
        scrollBarCheckBox.isSelected() ? Boolean.TRUE : null);

    Skin skin = null;

    if (themePackMode) {
      skin = SkinLookAndFeel.loadThemePack((String) values[0]);
    }
    else {
      skin = new CompoundSkin(SkinLookAndFeel.loadSkin((String) values[0]),
          SkinLookAndFeel.loadSkin((String) values[1]));
    }

    SkinLookAndFeel.setSkin(skin);

    UIManager.setLookAndFeel("com.l2fprod.gui.plaf.skin.SkinLookAndFeel");
  }

  /**
   * Recursively traverse <code>directory</code> and add skin files to <code>v</code>
   * . <br>
   * Skin files are added if <code>accept(skinFile)</code> returns <code>true</code>
   *
   * @param v          vector to store skin list
   * @param directory  the directory to list for skin files
   */
  protected void buildSkinList(Vector v, File directory) {
    if (!directory.isDirectory() || !directory.exists()) {
      return;
    }

    String[] files = directory.list();
    File f;
    for (int i = 0, c = files.length; i < c; i++) {
      f = new File(directory, files[i]);
      if (f.isDirectory()) {
        buildSkinList(v, f);
      }
      else if (accept(f)) {
        try {
          v.addElement(f.getCanonicalPath());
        } catch (IOException e) {
        }
      }
    }
  }

  /**
   * Check if a given file is a skin file. <br>
   * Subclasses can override this method to provide better handling of skin
   * files. <br>
   * The default implementation checks if the file ends with gtkrc or themerc.
   *
   * @param f  the file to check
   * @return   true if the file is a valid skin file
   */
  protected boolean accept(File f) {
    return (f.isDirectory() == false &&
        ((themePackMode && f.getName().endsWith(".zip")) ||
        (f.getName().endsWith("gtkrc") || f.getName().endsWith("themerc"))));
  }

  /**
   * Description of the Method
   */
  protected void showPreviewWindow() {
    Skin oldSkin = SkinLookAndFeel.getSkin();
    LookAndFeel oldLAF = UIManager.getLookAndFeel();

    try {
      Object[] values = skinList.getSelectedValues();
      if ((values == null) || (values.length != 2)) {
        return;
      }

      Skin skin = new CompoundSkin(SkinLookAndFeel.loadSkin((String) values[0]),
          SkinLookAndFeel.loadSkin((String) values[1]));

      SkinLookAndFeel.setSkin(skin);
      UIManager.setLookAndFeel("com.l2fprod.gui.plaf.skin.SkinLookAndFeel");

      SkinWindow window = new SkinPreviewWindow();
      window.setVisible(true);

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      SkinLookAndFeel.setSkin(oldSkin);
      try {
        UIManager.setLookAndFeel(oldLAF);
      } catch (UnsupportedLookAndFeelException e) {
      }
    }
  }

  /**
   * Description of the Method
   */
  private void loadResourceBundle() {
    bundle = ResourceBundle.getBundle("com.l2fprod.gui.plaf.skin.resources.skin");
  }

  /**
   * Description of the Class
   *
   * @author    fred
   * @created   27 avril 2002
   */
  private class RefreshAction extends AbstractAction {
    /**
     * Constructor for the RefreshAction object
     */
    public RefreshAction() {
      super(REFRESH_CMD);
    }

    /**
     * Description of the Method
     *
     * @param event  Description of Parameter
     */
    public void actionPerformed(ActionEvent event) {
      refreshList();
    }
  }

  /**
   * Description of the Class
   *
   * @author    fred
   * @created   27 avril 2002
   */
  private class GetSkinsAction extends AbstractAction {
    /**
     * Constructor for the GetSkinsAction object
     */
    public GetSkinsAction() {
      super(GETSKINS_CMD);
    }

    /**
     * Description of the Method
     *
     * @param event  Description of Parameter
     */
    public void actionPerformed(ActionEvent event) {
      try {
        OS.openDocument(bundle.getString("SkinChooser.getskins.url"));
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Description of the Class
   *
   * @author    fred
   * @created   27 avril 2002
   */
  private class PreviewAction extends AbstractAction {
    /**
     * Constructor for the PreviewAction object
     */
    public PreviewAction() {
      super(PREVIEW_CMD);
    }

    /**
     * Description of the Method
     *
     * @param event  Description of Parameter
     */
    public void actionPerformed(ActionEvent event) {
      showPreviewWindow();
    }
  }

}
