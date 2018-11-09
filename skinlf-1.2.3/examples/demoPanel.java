/* ====================================================================
 *
 * @PROJECT.FULLNAME@ @VERSION@ License.
 *
 * Copyright (c) @YEAR@ L2FProd.com.  All rights reserved.
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
 * 4. The names "@PROJECT.FULLNAME@", "SkinLF" and "L2FProd.com" must not
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
import java.awt.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.*;
import java.awt.image.*;
import java.beans.*;
import java.net.URL;
import java.util.Arrays;
import java.util.Enumeration;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.text.*;

import com.l2fprod.gui.*;
import com.l2fprod.gui.plaf.skin.*;
import com.l2fprod.util.*;

/**
 * Simple Test Panel for a Look And Feel
 *
 * @author    $Author: l2fprod $
 * @created   27 avril 2002
 * @version   $Revision: 1.6 $, $Date: 2002/04/28 18:07:08 $
 */
public class demoPanel extends JPanel {

  demoWelcome m_Welcome;

  /**
   * Constructor for the demoPanel object
   *
   * @param themes  Description of Parameter
   */
  public demoPanel(String[] themes) {
    setLayout(new BorderLayout(3, 3));

    // Create the menu bar
    JMenuBar menubar = new JMenuBar();
    JMenu menu = new JMenu("File");
    menu.setMnemonic('f');
    menu.add(new JMenuItem("Save"));
    menu.add(
      new AbstractAction("Open") {
        public void actionPerformed(ActionEvent event) {
          new JFileChooser().showDialog(demoPanel.this, "Ok");
        }
      });
    menu.addSeparator();
    JMenuItem item = new JMenuItem("Exit");
    item.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent event) {
          exit();
        }
      });
    menu.add(item);
    menubar.add(menu);

    menu = new JMenu("Edit");
    menu.setMnemonic('e');
    menu.add(new JCheckBoxMenuItem("CheckBoxMenu Item (selected)", true));
    menu.add(new JCheckBoxMenuItem("CheckBoxMenu Item (unselected)"));
    menu.addSeparator();
    menu.add(new JCheckBoxMenuItem("CheckBoxMenu Item (disabled/selected)", true)).setEnabled(false);
    menu.add(new JCheckBoxMenuItem("CheckBoxMenu Item (disabled/unselected)")).setEnabled(false);
    menu.addSeparator();
    menu.add(new JRadioButtonMenuItem("RadioButtonMenu Item (selected)", true));
    menu.add(new JRadioButtonMenuItem("RadioButtonMenu Item (unselected)"));
    menu.addSeparator();
    menu.add(new JRadioButtonMenuItem("RadioButtonMenu Item (disabled/selected)", true)).setEnabled(false);
    menu.add(new JRadioButtonMenuItem("RadioButtonMenu Item (disabled/unselected)")).setEnabled(false);
    menubar.add(menu);
    add("North", menubar);

    JPanel buttonPane = new JPanel(new VerticalLayout(3));
    JButton button = new JButton("Rollover");
    ButtonModel model =
      new DefaultButtonModel() {
        public boolean isSelected() {
          return true;
        }

        public boolean isRollover() {
          return true;
        }
      };
    button.setModel(model);
    buttonPane.add(button);
    buttonPane.add(button = new JButton("Normal"));
    button.setBackground(Color.red);

    button = new JButton("Pressed");
    model =
      new DefaultButtonModel() {
        public boolean isPressed() {
          return true;
        }

        public boolean isArmed() {
          return true;
        }

        public boolean isSelected() {
          return true;
        }
      };
    button.setModel(model);
    buttonPane.add(button);
    button = new JButton("Disabled");
    button.setEnabled(false);
    buttonPane.add(button);

    buttonPane.add(new JButton("<html><b>With HTML</b></html>"));

    JPanel common = new JPanel(new GridLayout(5, 2));
    JCheckBox check = new JCheckBox("Check", true);
    common.add(check);

    JRadioButton select = new JRadioButton("Select", true);
    common.add(select);

    check = new JCheckBox("<html>Check box<br>with<br>multiple lines", false);
    check.setBackground(Color.green);
    common.add(check);

    select = new JRadioButton("Select", false);
    common.add(select);

    check = new JCheckBox("Check", true);
    check.setEnabled(false);
    common.add(check);

    select = new JRadioButton("Select", true);
    select.setEnabled(false);
    common.add(select);

    check = new JCheckBox("Check", false);
    check.setEnabled(false);
    common.add(check);

    select = new JRadioButton("Select", false);
    select.setEnabled(false);
    common.add(select);

    ButtonGroup toggleGroup = new ButtonGroup();
    JToggleButton toggle = new JToggleButton("Standard Toggle");
    common.add(toggle);
    toggleGroup.add(toggle);

    toggle = new JToggleButton("<html><i>With</i><b>HTML</b>");
    common.add(toggle);
    toggleGroup.add(toggle);

    JProgressBar progress = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
    progress.setValue(75);

    JPanel mainPane = new BasePanel(new BorderLayout(3, 3));
    mainPane.add("North", new MemoryPanel());
    mainPane.add("Center", common);
    mainPane.add("South", progress);
    mainPane.add("East", buttonPane);
    mainPane.setBorder(new EmptyBorder(10, 10, 10, 10));

    final JTabbedPane tabs = new JTabbedPane();
    tabs.addTab("SkinLF", m_Welcome = new demoWelcome(themes));
    tabs.addTab("Common", mainPane);
    tabs.addTab("Unselected", new BasePanel(new JScrollPane(new JTree())));
    tabs.addTab("Disabled", new JPanel());
    tabs.addTab("InternalFrame", new InternalTest());
    tabs.addTab("TextComponent", new TextTest());
    tabs.addTab("Combo and List", new ComboList());
    tabs.addTab("Table", new TablePanel());
    tabs.addTab("List", new ListPanel());
    tabs.addTab("Split", new SplitTest());
    tabs.setEnabledAt(3, false);

    JMenu tabPlacement = new JMenu("Tab Placement");
    tabPlacement.setMnemonic('t');
    tabPlacement.add(
      new AbstractAction("TOP") {
        public void actionPerformed(ActionEvent event) {
          tabs.setTabPlacement(JTabbedPane.TOP);
        }
      });
    tabPlacement.add(
      new AbstractAction("BOTTOM") {
        public void actionPerformed(ActionEvent event) {
          tabs.setTabPlacement(JTabbedPane.BOTTOM);
        }
      });
    tabPlacement.add(
      new AbstractAction("LEFT") {
        public void actionPerformed(ActionEvent event) {
          tabs.setTabPlacement(JTabbedPane.LEFT);
        }
      });
    tabPlacement.add(
      new AbstractAction("RIGHT") {
        public void actionPerformed(ActionEvent event) {
          tabs.setTabPlacement(JTabbedPane.RIGHT);
        }
      });
    menubar.add(tabPlacement);

    add("Center", tabs);

    JScrollBar hsb = new JScrollBar(JScrollBar.HORIZONTAL, 50, 20, 0, 100);
    add("South", hsb);

    JScrollBar vsb = new JScrollBar(JScrollBar.VERTICAL, 50, 20, 0, 100);
    add("East", vsb);

    JProgressBar vprogress = new JProgressBar(JProgressBar.VERTICAL, 0, 100);
    vprogress.setValue(75);
    add("West", vprogress);

    menu = new JMenu("Help");
    menu.setMnemonic('h');
    menu.add(
      new AbstractAction("About " + SkinLookAndFeel.VERSION) {
        public void actionPerformed(ActionEvent event) {
          JOptionPane.showMessageDialog(null,
              "SkinLookAndFeel " + SkinLookAndFeel.VERSION + "\n" +
              "(c) 2000-2002 L2FProd.com");
        }
      });
    menubar.add(menu);
  }

  /**
   * Description of the Method
   */
  void exit() {
    System.exit(0);
  }

  final static Border EMPTY_BORDER = new EmptyBorder(4,4,4,4);

  static class BasePanel extends JPanel {
    public BasePanel() {
      super();
      setBorder(EMPTY_BORDER);
    }
    public BasePanel(LayoutManager p_Layout) {
      super(p_Layout);
      setBorder(EMPTY_BORDER);
    }
    public BasePanel(Component c) {
      this(new BorderLayout());
      add("Center", c);
    }
  }

  /**
   * Description of the Class
   *
   * @author    fred
   * @created   27 avril 2002
   */
  static class MemoryPanel extends JPanel {
    /**
     * Constructor for the MemoryPanel object
     */
    MemoryPanel() {
      setLayout(new FlowLayout(FlowLayout.LEFT));
      JButton b = new JButton("gc()");
      b.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent event) {
            Runtime.getRuntime().gc();
          }
        });
      add(b);

      final JLabel memory;
      add(memory = new JLabel(" "));

      Timer t = new Timer(1000,
        new ActionListener() {
          public void actionPerformed(ActionEvent event) {
            memory.setText(" Free: " + Runtime.getRuntime().freeMemory() +
                " Total: " + Runtime.getRuntime().totalMemory());
          }
        });
      t.setRepeats(true);
      t.start();
    }
  }

  /**
   * Description of the Class
   *
   * @author    fred
   * @created   27 avril 2002
   */
  static class SplitTest extends BasePanel {
    /**
     * Constructor for the SplitTest object
     */
    SplitTest() {
      setLayout(new BorderLayout());
      JButton button;
      final JSplitPane innerSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
          new JScrollPane(new JTree()),
          button = new JButton("Split!"));
      final JSplitPane split1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
          new JScrollPane(new JTree()),
          innerSplit);
      add("Center", split1);
      innerSplit.setBorder(null);
      split1.setBorder(null);
      button.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent event) {
            innerSplit.setOneTouchExpandable(!innerSplit.isOneTouchExpandable());
            split1.setOneTouchExpandable(!split1.isOneTouchExpandable());
          }
        });
    }
  }

  /**
   * Description of the Class
   *
   * @author    fred
   * @created   27 avril 2002
   */
  static class InternalTest extends BasePanel {
    /**
     * Constructor for the InternalTest object
     */
    InternalTest() {
      setLayout(new BorderLayout());
      JDesktopPane desk = new JDesktopPane();
      add("Center", new JScrollPane(desk));
      desk.putClientProperty("JDesktopPane.backgroundEnabled", Boolean.TRUE);
      desk.putClientProperty("JDesktopPane.dragMode", "faster");

      JInternalFrame frame = new JInternalFrame("A Frame (DO_NOTHING_ON_CLOSE)", true, true, true, true);
      frame.getContentPane().add(new JScrollPane(new JTree()));
      frame.setVisible(true);
      frame.setSize(200, 100);
      frame.setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
      frame.addVetoableChangeListener(
        new VetoableChangeListener() {
          public void vetoableChange(PropertyChangeEvent event) throws PropertyVetoException {
            if (event.getPropertyName().equals(JInternalFrame.IS_CLOSED_PROPERTY) &&
                Boolean.TRUE.equals(event.getNewValue())) {
              JOptionPane.showMessageDialog((JInternalFrame) event.getSource(),
                  "You can veto Frame Closing here!");
              throw new PropertyVetoException("don't close!", event);
            }
            // end of if (event.getPropertyName().equals()
          }
        });
      desk.add(frame);

      //
      // this frame has a workaround for a bug in Swing where setIconifiable does not fire any event
      // until JDK1.4
      //
      final JInternalFrame frame2 =
        new JInternalFrame("An other Frame", true, true, true, true) {
          public void setIconifiable(boolean b) {
            if (OS.isOneDotFour()) {
              super.setIconifiable(b);
            }
            else {
              Boolean oldValue = isIconifiable() ? Boolean.TRUE : Boolean.FALSE;
              Boolean newValue = b ? Boolean.TRUE : Boolean.FALSE;
              super.setIconifiable(b);
              this.firePropertyChange("iconable", oldValue, newValue);
            }
          }
        };
      frame2.getContentPane().setLayout(new GridLayout(3, 1));

      JButton button = new JButton("Toggle closable");
      button.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent event) {
            frame2.setClosable(!frame2.isClosable());
          }
        });
      frame2.getContentPane().add(button);
      button = new JButton("Toggle iconifiable");
      button.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent event) {
            frame2.setIconifiable(!frame2.isIconifiable());
          }
        });
      frame2.getContentPane().add(button);

      frame2.setMaximizable(false);
      frame2.setVisible(true);
      frame2.setSize(200, 200);
      frame2.setLocation(50, 50);
      desk.add(frame2);

      JInternalFrame frame3 = new JInternalFrame("Not iconifiable",
          true, true, true, false);
      frame3.setIconifiable(false);
      frame3.setSize(200, 200);
      frame3.setLocation(75, 75);
      frame3.setVisible(true);
      desk.add(frame3);
    }
  }

  /**
   * Description of the Class
   *
   * @author    fred
   * @created   27 avril 2002
   */
  static class TextTest extends BasePanel {
    /**
     * Constructor for the TextTest object
     */
    TextTest() {
      setLayout(new VerticalLayout());
      add(new JLabel("This is a testbed for the key bindinds"));
      add(new JLabel("JTextField:"));
      add(new JTextField("please try the key bindings"));

      add(new JLabel("JPasswordField:"));
      add(new JPasswordField());

      JScrollPane scroll;
      add(new JLabel("JTextArea:"));
      add(scroll = new JScrollPane(new JTextArea("please try the key bindings\nthis is skin look and feel", 4, 50)));
      scroll.setPreferredSize(new Dimension(100, 60));

      add(new JLabel("JTextPane:"));
      add(scroll = new JScrollPane(new JTextPane()));
      scroll.setPreferredSize(new Dimension(100, 60));

      add(new JLabel("JEditorPane:"));
      add(scroll = new JScrollPane(new JEditorPane()));
      scroll.setPreferredSize(new Dimension(100, 60));
    }
  }

  /**
   * Description of the Class
   *
   * @author    fred
   * @created   27 avril 2002
   */
  static class TablePanel extends BasePanel {
    /**
     * Constructor for the TablePanel object
     */
    TablePanel() {
      setLayout(new BorderLayout());
      JTable table = new JTable(
        new AbstractTableModel() {
          public int getRowCount() {
            return 50000;
          }

          public int getColumnCount() {
            return 5;
          }

          public Object getValueAt(int row, int column) {
            if (column == 1) {
              return new Integer((int)(((double)100 * row) / (double)getRowCount()));
            } else {
              return new Integer(row * column);
            }
          }
        });
      add("Center", new JScrollPane(table));

      
      table.getColumnModel().getColumn(1).setCellRenderer(new JProgressBarCellRenderer());

      // here is an example of overriding the default table cellrenderer to provide a custom one while reusing
      // while reusing the skin tablecellrenderer
      final Icon arrowIcon =
        new Icon() {
          public int getIconWidth() {
            return 10;
          }

          public int getIconHeight() {
            return 10;
          }

          public void paintIcon(Component c, Graphics g, int x, int y) {
            Color oldColor = g.getColor();
            g.setColor(Color.black);
            g.drawRect(x, y, 10, 10);
            g.setColor(oldColor);
          }
        };

      TableCellRenderer headerRenderer =
        new TableCellRenderer() {
          public Component getTableCellRendererComponent(JTable table, Object value,
              boolean isSelected, boolean hasFocus,
              int row, int column) {
            DefaultTableCellRenderer c =
                (DefaultTableCellRenderer) SkinLookAndFeel.getSkin().getPersonality().createTableHeaderRenderer();
            c.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (column == 0) {
              c.setForeground(Color.red);
              c.setIcon(arrowIcon);
            }
            else {
              c.setIcon(null);
            }
            return c;
          }
        };
      Enumeration enumeration = table.getColumnModel().getColumns();
      while (enumeration.hasMoreElements()) {
        TableColumn aColumn = (TableColumn) enumeration.nextElement();
        aColumn.setHeaderRenderer(headerRenderer);
      }

      table.getTableHeader().setReorderingAllowed(false);
    }
  }

  /**
   * Description of the Class
   *
   * @author    fred
   * @created   27 avril 2002
   */
  static class ComboList extends BasePanel {
    /**
     * Constructor for the ComboList object
     */
    ComboList() {
      setLayout(new VerticalLayout());
      add(new JLabel("Normal ComboBox:"));
      add(new JComboBox(new String[]{"1", "2", "4", "8"}));
      add(new JLabel("Editable ComboBox"));
      final JComboBox editable = new JComboBox(new String[]{"1", "2", "4", "8"});
      //	    editable.setEditable(true);
      add(editable);
      JButton b = new JButton("Toggle Editable");
      b.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent event) {
            editable.setEditable(!editable.isEditable());
          }
        });
      add(b);
      // JDK1.4 only
      try {
        add((JComponent) Class.forName("javax.swing.JSpinner").newInstance());
      } catch (Exception e) {
      }
    }
  }
  
  static class ListPanel extends BasePanel {
    ListPanel() {
      setLayout(new BorderLayout());
      
      ListModel model = new AbstractListModel() {
          public Object getElementAt(int index) {
            return new Integer((int)(((double)100 * index) / (double)getSize()));
          }
          public int getSize() {
            return 10;
          }
        };
      
      JList list = new JList(model);
      list.setCellRenderer(new ListComponentRenderer());
      list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      list.setFixedCellWidth(272);
      list.setFixedCellHeight(21);
      JScrollPane listScrollPane = new JScrollPane(list);
      listScrollPane.setBorder(BorderFactory.
                               createCompoundBorder(BorderFactory.
                                                    createEtchedBorder(),
                                                    BorderFactory.
                                                    createEmptyBorder(2, 2, 2, 2)));
      add("Center", listScrollPane);
    }
  }

  static class ListComponentRenderer extends BasePanel implements ListCellRenderer {
    JProgressBar progBar;
    public ListComponentRenderer() {
      // Configure this
      setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
      setBorder( BorderFactory.createCompoundBorder(
                                                    BorderFactory.createEtchedBorder(),
                                                    BorderFactory.createEmptyBorder(2, 2, 2, 2)));
      setOpaque(true);
      
      // Configure progBar
      progBar = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
      progBar.setBorder(BorderFactory.createRaisedBevelBorder());
      progBar.setPreferredSize(new Dimension(100, 15));
      progBar.setMinimumSize(new Dimension(100, 15));
      progBar.setMaximumSize(new Dimension(100, 15));
      progBar.setValue(0);
      
      add(new JLabel("Fixed Size Label"));
      add(progBar);
    }

    /**
     *   Comply with ListCellRenderer Interface
     *   @param list the JList doing the renderering
     *   @param value object being rendered
     *   @param index cell index
     *   @param isSelected is selected
     *   @param cellHasFocus cell has focus?
     *   @return Component to render
     */
    public Component getListCellRendererComponent(JList list, Object value, int index,
                                                  boolean isSelected, boolean cellHasFocus) {
      progBar.setValue(((Integer)value).intValue());
      setBackground(isSelected ? Color.red : Color.white);
      setForeground(isSelected ? Color.white : Color.black);
      return this;
    }
  }

  static class JProgressBarCellRenderer extends JProgressBar implements TableCellRenderer {
    public JProgressBarCellRenderer() {
      super();
      Dimension progressSize = new Dimension(50, 8);
      setMaximumSize(progressSize);
      setPreferredSize(progressSize);
      setMinimumSize(progressSize);
    }
    
    public Component getListCellRendererComponent(JList list, Object value, int index,
                                                  boolean isSelected, boolean cellHasFocus) {
      setBackground(list.getBackground());
      handleValue(value);
      return this;
    }
    
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected,
                                                  boolean expanded, boolean leaf, int row, boolean hasFocus) {
      setBackground(tree.getBackground());
      handleValue(value);
      return this;
    }
    
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus,
                                                   int row, int column) {
      setBackground(table.getBackground());
      handleValue(value);
      return this;
    }

    /**
     * Called by getListCellRendererComponent, getTreeCellRendererComponent, getTableCellRendererComponent to render the value.
     * The default implementation checks the type of value and sets the model or the value correctly.
     *
     * @param value an <code>Object</code> value
     */
    protected void handleValue(Object value) {
      if (value instanceof BoundedRangeModel) {
        setModel((BoundedRangeModel)value);
      } else {
        setValue(((Integer)value).intValue());
      }
    }
  }

}
