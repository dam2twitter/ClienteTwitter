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
package com.l2fprod.gui.plaf.skin;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import com.l2fprod.gui.*;
import com.l2fprod.gui.plaf.skin.impl.gtk.*;
import com.l2fprod.gui.plaf.skin.impl.kde.*;
import com.l2fprod.util.WindowUtils;

/**
 * SkinPreview Window. <br>
 *
 *
 * @author    $Author: l2fprod $
 * @created   27 avril 2002
 * @version   $Revision: 1.3 $, $Date: 2002/04/27 11:43:07 $
 */
public class SkinPreviewWindow extends SkinWindow {

  /**
   * Constructor for the SkinPreviewWindow object
   */
  public SkinPreviewWindow() {
    super();

    JTabbedPane tabs = new JTabbedPane();
    getContentPane().add("Center", tabs);
    tabs.addTab("Buttons", new ButtonTest());
    tabs.addTab("CheckBox", new CheckBoxTest());
    tabs.addTab("Radio", new RadioTest());
    tabs.addTab("Progress", new ProgressTest());
    tabs.addTab("Desktop", new InternalTest());
    tabs.addTab("Scrollbars", new ScrollTest());
    tabs.addTab("List", new ListTest());
    tabs.addTab("Table", new TableTest());

    JMenuBar menubar = new JMenuBar();
    JMenu menu = new JMenu("File");
    menu.add(new JCheckBoxMenuItem("Check Box ?"));
    JMenu submenu = new JMenu("Sub menu");
    submenu.add(new JMenuItem("Hi!"));
    menu.add(submenu);
    menu.add(new JMenuItem("Exit"));
    menubar.add(menu);

    setJMenuBar(menubar);

    WindowUtils.sizeTo(this, 0.5d, 0.5d);
    WindowUtils.centerOnScreen(this);

    addWindowListener(
      new WindowAdapter() {
        public void windowClosing(WindowEvent event) {
          SkinPreviewWindow.this.dispose();
        }
      });
  }

  /**
   * Description of the Method
   *
   * @return   Description of the Returned Value
   */
  protected JComponent createNorthPanel() {
    try {
      Class acClass = javax.swing.JComponent.class;
      UIManager.put("ClassLoader", SkinWindowButtonUI.class.getClassLoader());
      UIManager.put("WindowButtonUI",
          "com.l2fprod.gui.plaf.skin.SkinWindowButtonUI");
      UIManager.put(SkinWindowButtonUI.class,
          SkinWindowButtonUI.class.getMethod("createUI", new Class[]{acClass}));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return new SkinWindowTitlePane(this);
  }

  /**
   * The main program for the SkinPreviewWindow class
   *
   * @param args           The command line arguments
   * @exception Exception  Description of Exception
   */
  public static void main(String[] args) throws Exception {

    Skin skin = null;
    if (args.length > 1) {
      skin = new CompoundSkin(SkinLookAndFeel.loadSkin(args[0]),
          SkinLookAndFeel.loadSkin(args[1]));
    }
    else {
      skin = SkinLookAndFeel.loadSkin(args[0]);
    }

    SkinLookAndFeel.setSkin(skin);

    UIManager.setLookAndFeel("com.l2fprod.gui.plaf.skin.SkinLookAndFeel");

  }

  /**
   * Description of the Class
   *
   * @author    fred
   * @created   27 avril 2002
   */
  static class ButtonTest extends JPanel {
    /**
     * Constructor for the ButtonTest object
     */
    ButtonTest() {
      setLayout(new BorderLayout());
      add("North", new JButton("North"));
      add("East", new JButton("East"));
      add("South", new JButton("South"));
      add("West", new JButton("West"));
      JButton b = new JButton("Center");
      b.setEnabled(false);
      add("Center", b);
    }
  }

  /**
   * Description of the Class
   *
   * @author    fred
   * @created   27 avril 2002
   */
  static class CheckBoxTest extends JPanel {
    /**
     * Constructor for the CheckBoxTest object
     */
    CheckBoxTest() {
      setLayout(new BorderLayout());
      Box p = new Box(BoxLayout.Y_AXIS);
      //	    JPanel p = new JPanel();
      //	    p.setLayout(new GridLayout(4, 1));
      p.add(new JCheckBox("Hello ???"));
      p.add(Box.createVerticalStrut(3));
      p.add(new JComboBox(new String[]{"1", "2", "4", "8"}));
      p.add(Box.createVerticalStrut(3));
      JComboBox editable = new JComboBox(new String[]{"1", "2", "4", "8"});
      editable.setEditable(true);
      p.add(editable);

      p.add(Box.createVerticalStrut(3));
      JPanel toggles = new JPanel();
      toggles.setLayout(new FlowLayout());
      ButtonGroup group = new ButtonGroup();
      JToggleButton button;
      button = new JToggleButton("Hello 1");
      toggles.add(button);
      group.add(button);
      button = new JToggleButton("Hello 2");
      toggles.add(button);
      group.add(button);
      button = new JToggleButton("Hello 3");
      toggles.add(button);
      group.add(button);
      p.add(toggles);

      add("North", p);
    }
  }

  /**
   * Description of the Class
   *
   * @author    fred
   * @created   27 avril 2002
   */
  static class RadioTest extends JPanel {
    /**
     * Constructor for the RadioTest object
     */
    RadioTest() {
      ButtonGroup group = new ButtonGroup();
      setLayout(new GridLayout(3, 1));
      JRadioButton button;
      button = new JRadioButton("Hello 1");
      add(button);
      group.add(button);
      button = new JRadioButton("Hello 2");
      add(button);
      group.add(button);
      button = new JRadioButton("Hello 3");
      add(button);
      group.add(button);
    }
  }

  /**
   * Description of the Class
   *
   * @author    fred
   * @created   27 avril 2002
   */
  static class ProgressTest extends JPanel {
    /**
     * Constructor for the ProgressTest object
     */
    ProgressTest() {
      setLayout(new BorderLayout());
      final BoundedRangeModel model = new DefaultBoundedRangeModel(0, 0, 0, 100);

      JProgressBar progress = new JProgressBar(model);
      add("South", progress);

      JSlider slider = new JSlider(model);
      add("North", slider);

      slider = new JSlider(JSlider.VERTICAL);
      slider.setModel(model);
      add("West", slider);

      progress = new JProgressBar(JProgressBar.VERTICAL);
      progress.setModel(model);
      add("East", progress);

      Thread th =
        new Thread() {
          public void run() {
            try {
              while (true) {
                Thread.sleep(50);
                model.setValue(model.getValue() + 2);
                if (model.getValue() >= 100) {
                  Thread.sleep(1000);
                  model.setValue(0);
                }
              }
            } catch (Exception e) {
            }
          }
        };
      th.start();
    }
  }

  /**
   * Description of the Class
   *
   * @author    fred
   * @created   27 avril 2002
   */
  static class InternalTest extends JPanel {
    /**
     * Constructor for the InternalTest object
     */
    InternalTest() {
      setLayout(new BorderLayout());
      JDesktopPane desk = new JDesktopPane();
      add("Center", new JScrollPane(desk));
      desk.putClientProperty("JDesktopPane.backgroundEnabled", Boolean.TRUE);

      JInternalFrame frame = new JInternalFrame("A Frame", true, true, true, true);
      frame.getContentPane().add(new JButton("Ola"));
      frame.setVisible(true);
      frame.setSize(200, 100);
      desk.add(frame);

      frame = new JInternalFrame("An other Frame", true, true, true, true);
      frame.getContentPane().add(new JButton("Hello"));
      frame.setMaximizable(false);
      frame.setVisible(true);
      frame.setSize(200, 200);
      frame.setLocation(50, 50);
      desk.add(frame);
    }
  }

  /**
   * Description of the Class
   *
   * @author    fred
   * @created   27 avril 2002
   */
  static class ScrollTest extends JPanel {
    /**
     * Constructor for the ScrollTest object
     */
    ScrollTest() {
      setLayout(new BorderLayout());
      add("Center", new JScrollPane(new JTree()));
    }
  }

  /**
   * Description of the Class
   *
   * @author    fred
   * @created   27 avril 2002
   */
  static class ListTest extends JPanel {
    /**
     * Constructor for the ListTest object
     */
    ListTest() {
      setLayout(new BorderLayout());
      String[] values = new String[50];
      for (int i = 0, c = values.length; i < c; i++) {
        values[i] = "Item " + i;
      }
      add("Center", new JScrollPane(new JList(values)));
    }
  }

  /**
   * Description of the Class
   *
   * @author    fred
   * @created   27 avril 2002
   */
  static class TableTest extends JPanel {
    /**
     * Constructor for the TableTest object
     */
    TableTest() {
      setLayout(new BorderLayout());
      String[] columns = new String[4];
      String[][] rowData = new String[10][4];
      for (int i = 0, c = rowData[0].length; i < c; i++) {
        columns[i] = "Column" + i;
        for (int j = 0, d = rowData.length; j < d; j++) {
          rowData[j][i] = "Cell(" + i + ", " + j + ")";
        }
      }

      add("Center", new JScrollPane(new JTable(rowData, columns)));
    }
  }

}

