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

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;

import javax.swing.*;
import javax.swing.event.InternalFrameEvent;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

import com.l2fprod.gui.SkinWindow;

/**
 * Description of the Class
 *
 * @author    fred
 */
public class SkinTitlePane extends BasicInternalFrameTitlePane {

  /**
   * Description of the Field
   */
  //  protected JMenuBar menuBar;

  /**
   * Description of the Field
   */
  //  protected JMenu windowMenu;
  /**
   * Description of the Field
   */
  protected Window frame;

  /**
   * Description of the Field
   */
  //  protected Color selectedTitleColor;
  /**
   * Description of the Field
   */
  //  protected Color selectedTextColor;
  /**
   * Description of the Field
   */
  //  protected Color notSelectedTitleColor;
  /**
   * Description of the Field
   */
  //  protected Color notSelectedTextColor;

  /**
   * Description of the Field
   */
  //  protected PropertyChangeListener propertyChangeListener;

  /**
   * Description of the Field
   */
  //  protected Action closeAction;
  /**
   * Description of the Field
   */
  //  protected Action maximizeAction;
  /**
   * Description of the Field
   */
  //  protected Action iconifyAction;
  /**
   * Description of the Field
   */
  //  protected Action restoreAction;
  /**
   * Description of the Field
   */
  protected Action shadeAction;

  boolean systemMenuAdded = false;

  private Skin skin = SkinLookAndFeel.getSkin();

  /**
   * Description of the Field
   */
  public final static int ICON_OFFSET = 16;

  /**
   * Description of the Field
   */
  public final static int ALIGN_TOP_LEFT = 0;
  // Align button relative to top left of window
  /**
   * Description of the Field
   */
  public final static int ALIGN_TOP_RIGHT = 1;
  // Align button relative to the top right of window

  /**
   * Description of the Field
   */
  public final static int CLOSE_ACTION = 0;

  /**
   * Description of the Field
   */
  public final static int MAXIMIZE_ACTION = 22;
  /**
   * Description of the Field
   */
  public final static int MINIMIZE_ACTION = 23;

  /**
   * Description of the Field
   */
  public final static int NO_ACTION = -1;

  /**
   * Description of the Field
   */
  protected final static String CLOSE_CMD = "Close";
  /**
   * Description of the Field
   */
  protected final static String ICONIFY_CMD = "Minimize";
  /**
   * Description of the Field
   */
  protected final static String RESTORE_CMD = "Restore";
  /**
   * Description of the Field
   */
  protected final static String MAXIMIZE_CMD = "Maximize";
  /**
   * Description of the Field
   */
  protected final static String SHADE_CMD = "Shade";

  /**
   * Constructor for the SkinTitlePane object
   *
   * @param f  Description of Parameter
   */
  public SkinTitlePane(SkinWindow f) {
    this(new Window.SkinWindowWindow(f));
  }

  /**
   * Constructor for the SkinTitlePane object
   *
   * @param f  Description of Parameter
   */
  public SkinTitlePane(JInternalFrame f) {
    this(new Window.InternalFrameWindow(f));
  }

  /**
   * Constructor for the SkinTitlePane object
   *
   * @param f  Description of Parameter
   */
  public SkinTitlePane(Window f) {
    super(null);
    frame = f;
    install();
  }

  /**
   * Gets the Window attribute of the SkinTitlePane object
   *
   * @return   The Window value
   */
  public Window getWindow() {
    return frame;
  }

  /**
   * Gets the PreferredSize attribute of the SkinTitlePane object
   *
   * @return   The PreferredSize value
   */
  public Dimension getPreferredSize() {
    return skin.getFrame().getTopPreferredSize();
  }

  /**
   * Gets the MinimumSize attribute of the SkinTitlePane object
   *
   * @return   The MinimumSize value
   */
  public Dimension getMinimumSize() {
    return skin.getFrame().getTopPreferredSize();
  }


  /**
   * Adds a feature to the Notify attribute of the SkinTitlePane object
   */
  public void addNotify() {
    super.addNotify();
    addSystemMenuItems(windowMenu);
    enableActions();
  }

  /**
   * Description of the Method
   */
  public void removeNotify() {
    super.removeNotify();
    if (windowMenu != null) {
      windowMenu.removeAll();
      systemMenuAdded = false;
    }
    uninstallDefaults();
  }

  /**
   * Description of the Method
   *
   * @param g  Description of Parameter
   */
  public void paintComponent(Graphics g) {
    boolean isSelected = frame.isSelected();

    Font f = g.getFont();

    if (frame.getTitle() != null) {
      if (isSelected) {
        g.setColor(selectedTextColor);
      }
      else {
        g.setColor(notSelectedTextColor);
      }
      g.setFont(UIManager.getFont("InternalFrame.titleFont"));
    }

    skin.getFrame().paintTop(g, this, isSelected, frame.getTitle());

    g.setFont(f);

  }

  /**
   * Sets the ButtonIcons attribute of the SkinTitlePane object
   */
  protected void setButtonIcons() {
  }

  /**
   * Description of the Method
   */
  protected void installTitlePane() {
    installDefaults();

    createActions();
    enableActions();
    assembleSystemMenu();
    setLayout(createLayout());

    add(menuBar);

    setOpaque(true);
  }

  protected void install() {
    createButtons();
    enableActions();
    installListeners();
  }

  /**
   * Description of the Method
   */
  protected void createActions() {
    maximizeAction = new MaximizeAction();
    maximizeAction.setEnabled(true);
    iconifyAction = new IconifyAction();
    iconifyAction.setEnabled(true);
    closeAction = new CloseAction();
    closeAction.setEnabled(true);
    restoreAction = new RestoreAction();
    restoreAction.setEnabled(true);
    shadeAction = new ShadeAction();
    shadeAction.setEnabled(true);
  }

  /**
   * Description of the Method
   */
  protected void installListeners() {
    propertyChangeListener = createPropertyChangeListener();
    frame.addPropertyChangeListener(propertyChangeListener);
  }

  protected void uninstallListeners() {
    frame.removePropertyChangeListener(propertyChangeListener);
  }

  /**
   * Description of the Method
   */
  protected void installDefaults() {
    selectedTitleColor = UIManager.getColor("InternalFrame.activeTitleBackground");
    selectedTextColor = UIManager.getColor("InternalFrame.activeTitleForeground");
    notSelectedTitleColor = UIManager.getColor("InternalFrame.inactiveTitleBackground");
    notSelectedTextColor = UIManager.getColor("InternalFrame.inactiveTitleForeground");
  }


  /**
   * Description of the Method
   */
  protected void uninstallDefaults() {
  }

  /**
   * Description of the Method
   */
  protected void createButtons() {
    SkinWindowButton[] buttons =
        skin.getFrame().getWindowButtons(ALIGN_TOP_LEFT);
    if (buttons != null) {
      for (int i = 0, c = buttons.length; i < c; i++) {
        addButton(buttons[i]);
      }
    }

    buttons =
        skin.getFrame().getWindowButtons(ALIGN_TOP_RIGHT);
    if (buttons != null) {
      for (int i = 0, c = buttons.length; i < c; i++) {
        addButton(buttons[i]);
      }
    }
  }

  /**
   * Adds a feature to the Button attribute of the SkinTitlePane object
   *
   * @param button  The feature to be added to the Button attribute
   */
  protected void addButton(SkinWindowButton button) {
    button.setWindow(frame);
    switch (button.getWindowAction()) {
      case CLOSE_ACTION:
        button.addActionListener(closeAction);
        registerButtonForAction(button, closeAction);
        break;
      case MAXIMIZE_ACTION:
        button.addActionListener(maximizeAction);
        registerButtonForAction(button, maximizeAction);
        break;
      case MINIMIZE_ACTION:
        button.addActionListener(iconifyAction);
        registerButtonForAction(button, iconifyAction);
        break;
    }
    add(button);
  }


  /**
   * Description of the Method
   */
  protected void assembleSystemMenu() {
    if (menuBar == null) menuBar = createSystemMenuBar();
    if (windowMenu == null) windowMenu = createSystemMenu();
    menuBar.add(windowMenu);
    // moved to addNotify - addSystemMenuItems(windowMenu);
    enableActions();
  }

  /**
   * Adds a feature to the SystemMenuItems attribute of the SkinTitlePane object
   *
   * @param systemMenu  The feature to be added to the SystemMenuItems attribute
   */
  protected void addSystemMenuItems(JMenu systemMenu) {
    if (!systemMenuAdded) {
      JMenuItem mi = systemMenu.add(restoreAction);
      mi.setMnemonic('R');
      mi = systemMenu.add(iconifyAction);
      mi.setMnemonic('n');
      mi = systemMenu.add(maximizeAction);
      mi.setMnemonic('x');
      systemMenu.add(shadeAction);
      systemMenu.add(new JSeparator());
      mi = systemMenu.add(closeAction);
      mi.setMnemonic('C');
      systemMenuAdded = true;
    }
  }

  /**
   * Description of the Method
   *
   * @return   Description of the Returned Value
   */
  protected JMenu createSystemMenu() {
    return new JMenu("    ");
  }

  /**
   * Description of the Method
   *
   * @return   Description of the Returned Value
   */
  protected JMenuBar createSystemMenuBar() {
    menuBar = new SystemMenuBar();
    menuBar.setBorderPainted(false);
    return menuBar;
  }

  /**
   * Description of the Method
   */
  protected void showSystemMenu() {
    //      windowMenu.setPopupMenuVisible(true);
    //      windowMenu.setVisible(true);
    windowMenu.doClick();
  }

  /**
   * Post a WINDOW_CLOSING-like event to the frame, so that it can be treated
   * like a regular Frame.
   *
   * @param frame  Description of Parameter
   */
  protected void postClosingEvent(JInternalFrame frame) {
    InternalFrameEvent e = new InternalFrameEvent(
        frame, InternalFrameEvent.INTERNAL_FRAME_CLOSING);
    // Try posting event, unless there's a SecurityManager.
    if (JInternalFrame.class.getClassLoader() == null) {
      try {
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(e);
        return;
      } catch (SecurityException se) {
        // Use dispatchEvent instead.
      }
    }
    frame.dispatchEvent(e);
  }


  /**
   * Description of the Method
   */
  protected void enableActions() {
    if (frame == null) {
      return;
    }    
    restoreAction.setEnabled(frame.isMaximum() || frame.isIcon());
    maximizeAction.setEnabled(frame.isMaximizable());
    iconifyAction.setEnabled(frame.isIconifiable() && !frame.isIcon());
    closeAction.setEnabled(frame.isClosable());
    shadeAction.setEnabled(!frame.isMaximum() && !frame.isIcon());
    doLayout();
  }


  /**
   * Description of the Method
   *
   * @return   Description of the Returned Value
   */
  protected PropertyChangeListener createPropertyChangeListener() {
    return new PropertyChangeHandler();
  }

  /**
   * Description of the Method
   *
   * @return   Description of the Returned Value
   */
  protected LayoutManager createLayout() {
    return new TitlePaneLayout();
  }

  /**
   * Description of the Method
   *
   * @param b  Description of Parameter
   * @return   Description of the Returned Value
   */
  protected PropertyChangeListener createActionChangeListener(AbstractButton b) {
    return new ActionChangedListener(b);
  }

  // end TitlePaneLayout

  /**
   * Description of the Method
   *
   * @param b  Description of Parameter
   * @param a  Description of Parameter
   */
  private void registerButtonForAction(AbstractButton b, Action a) {
    PropertyChangeListener actionPropertyChangeListener =
        createActionChangeListener(b);
    a.addPropertyChangeListener(actionPropertyChangeListener);
    b.setEnabled(a.isEnabled());
  }

  /**
   * Description of the Class
   *
   * @author    fred
   */
  public class PropertyChangeHandler implements PropertyChangeListener {
    /**
     * Description of the Method
     *
     * @param evt  Description of Parameter
     */
    public void propertyChange(PropertyChangeEvent evt) {

      String prop = evt.getPropertyName();

      if (JInternalFrame.IS_SELECTED_PROPERTY.equals(prop)) {
        repaint();
        return;
      }

      /*
       *  if(JInternalFrame.IS_ICON_PROPERTY.equals(prop) ||
       *  JInternalFrame.IS_MAXIMUM_PROPERTY.equals(prop)) {
       *  setButtonIcons();
       *  return;
       *  }
       */
      enableActions();
    }

  }

  /**
   * Description of the Class
   *
   * @author    fred
   */
  public class TitlePaneLayout implements LayoutManager {
    /**
     * Adds a feature to the LayoutComponent attribute of the TitlePaneLayout
     * object
     *
     * @param name  The feature to be added to the LayoutComponent attribute
     * @param c     The feature to be added to the LayoutComponent attribute
     */
    public void addLayoutComponent(String name, Component c) {
    }

    /**
     * Description of the Method
     *
     * @param c  Description of Parameter
     */
    public void removeLayoutComponent(Component c) {
    }

    /**
     * Description of the Method
     *
     * @param c  Description of Parameter
     * @return   Description of the Returned Value
     */
    public Dimension preferredLayoutSize(Container c) {
      return new Dimension(100, 18);
    }

    /**
     * Description of the Method
     *
     * @param c  Description of Parameter
     * @return   Description of the Returned Value
     */
    public Dimension minimumLayoutSize(Container c) {
      return preferredLayoutSize(c);
    }

    /**
     * Description of the Method
     *
     * @param c  Description of Parameter
     */
    public void layoutContainer(Container c) {
      int w = getWidth();
      int nmembers = c.getComponentCount();
      int atlX = 2;
      int atrX = 0;

      menuBar.setBounds(atlX, (getHeight() - 16) / 2, 16, 16);
      atlX += 18;

      for (int i = 1
      /*
       *  skip menubar
       */
          ; i < nmembers; i++) {
        SkinWindowButton m = (SkinWindowButton) c.getComponent(i);
        m.setVisible(m.isEnabled());
        if (m.isEnabled()) {
          if (m.getAlign() == ALIGN_TOP_LEFT) {
            if (m.getXCoord() == -1) {
              m.setLocation(atlX, Math.max(m.getYCoord(), 1));
              atlX += m.getWidth();
            }
            else {
              m.setLocation(m.getXCoord(), m.getYCoord());
            }
          }
          else if (m.getAlign() == ALIGN_TOP_RIGHT) {
            if (m.getXCoord() == -1) {
              m.setLocation(w - atrX - m.getWidth(), Math.max(m.getYCoord(), 1));
              atrX += m.getWidth();
            }
            else {
              m.setLocation(w - m.getXCoord(), m.getYCoord());
            }
          }
        }
      }
    }
  }

  /**
   * This inner class is marked &quot;public&quot; due to a compiler bug. This
   * class should be treated as a &quot;protected&quot; inner class. Instantiate
   * it only within subclasses of <Foo>.
   *
   * @author    fred
   */
  public class CloseAction extends AbstractAction {
    /**
     * Constructor for the CloseAction object
     */
    public CloseAction() {
      super(CLOSE_CMD);
    }

    /**
     * Description of the Method
     *
     * @param e  Description of Parameter
     */
    public void actionPerformed(ActionEvent e) {
      if (frame.isClosable()) {
        try {
          frame.setClosed(true);
        } catch (PropertyVetoException e0) {
        }
      }
    }
  }

  // end CloseAction

  /**
   * This inner class is marked &quot;public&quot; due to a compiler bug. This
   * class should be treated as a &quot;protected&quot; inner class. Instantiate
   * it only within subclasses of <Foo>.
   *
   * @author    fred
   */
  public class MaximizeAction extends AbstractAction {
    /**
     * Constructor for the MaximizeAction object
     */
    public MaximizeAction() {
      super(MAXIMIZE_CMD);
    }

    /**
     * Description of the Method
     *
     * @param e  Description of Parameter
     */
    public void actionPerformed(ActionEvent e) {
      if (frame.isMaximizable()) {
        if (!frame.isMaximum()) {
          try {
            frame.setMaximum(true);
          } catch (PropertyVetoException e5) {
          }
        }
        else {
          try {
            frame.setMaximum(false);
            if (frame.isIconifiable() && frame.isIcon()) {
              frame.setIcon(false);
            }
          } catch (PropertyVetoException e6) {
          }
        }
      }
    }
  }

  // MaximizeAction

  /**
   * This inner class is marked &quot;public&quot; due to a compiler bug. This
   * class should be treated as a &quot;protected&quot; inner class. Instantiate
   * it only within subclasses of <Foo>.
   *
   * @author    fred
   * @created   27 avril 2002
   */
  public class IconifyAction extends AbstractAction {
    /**
     * Constructor for the IconifyAction object
     */
    public IconifyAction() {
      super(ICONIFY_CMD);
    }

    /**
     * Description of the Method
     *
     * @param e  Description of Parameter
     */
    public void actionPerformed(ActionEvent e) {
      if (frame.isIconifiable()) {
        if (!frame.isIcon()) {
          try {
            frame.setIcon(true);
          } catch (PropertyVetoException e1) {
          }
        }
        else {
          try {
            frame.setIcon(false);
            if (frame.isMaximizable() && frame.isMaximum()) {
              frame.setMaximum(false);
            }
          } catch (PropertyVetoException e1) {
          }
        }
      }
    }
  }

  // end IconifyAction

  /**
   * This inner class is marked &quot;public&quot; due to a compiler bug. This
   * class should be treated as a &quot;protected&quot; inner class. Instantiate
   * it only within subclasses of <Foo>.
   *
   * @author    fred
   * @created   27 avril 2002
   */
  public class RestoreAction extends AbstractAction {
    /**
     * Constructor for the RestoreAction object
     */
    public RestoreAction() {
      super(RESTORE_CMD);
    }

    /**
     * Description of the Method
     *
     * @param e  Description of Parameter
     */
    public void actionPerformed(ActionEvent e) {
      if (frame.isMaximizable() && frame.isMaximum()) {
        try {
          frame.setMaximum(false);
        } catch (PropertyVetoException e4) {
        }
      }
      else if (frame.isIconifiable() && frame.isIcon()) {
        try {
          frame.setIcon(false);
        } catch (PropertyVetoException e4) {
        }
      }
    }
  }

  // end RestoreAction

  /**
   * Description of the Class
   *
   * @author    fred
   * @created   27 avril 2002
   */
  public class ShadeAction extends AbstractAction {
    /**
     * Constructor for the ShadeAction object
     */
    public ShadeAction() {
      super(SHADE_CMD);
    }

    /**
     * Description of the Method
     *
     * @param event  Description of Parameter
     */
    public void actionPerformed(ActionEvent event) {
      frame.setShaded(!frame.isShaded());
    }
  }

  /**
   * This inner class is marked &quot;public&quot; due to a compiler bug. This
   * class should be treated as a &quot;protected&quot; inner class. Instantiate
   * it only within subclasses of <Foo>.
   *
   * @author    fred
   * @created   27 avril 2002
   */
  public class SystemMenuBar extends JMenuBar {
    /**
     * Gets the FocusTraversable attribute of the SystemMenuBar object
     *
     * @return   The FocusTraversable value
     */
    public boolean isFocusTraversable() {
      return false;
    }

    /**
     * Gets the Opaque attribute of the SystemMenuBar object
     *
     * @return   The Opaque value
     */
    public boolean isOpaque() {
      return true;
    }

    /**
     * Description of the Method
     */
    public void requestFocus() {
    }

    /**
     * Description of the Method
     *
     * @param g  Description of Parameter
     */
    public void paint(Graphics g) {
      Icon icon = frame.getFrameIcon();
      if (icon == null) {
        icon = UIManager.getIcon("InternalFrame.icon");
      }
      if (icon != null) {
        // Resize to 16x16 if necessary.
        if (icon instanceof ImageIcon && (icon.getIconWidth() > 16 || icon.getIconHeight() > 16)) {
          Image img = ((ImageIcon) icon).getImage();
          ((ImageIcon) icon).setImage(img.getScaledInstance(16, 16, Image.SCALE_SMOOTH));
        }
        icon.paintIcon(this, g, 0, 0);
      }
    }
  }

  /**
   * Description of the Class
   *
   * @author    fred
   * @created   27 avril 2002
   */
  private class ActionChangedListener implements PropertyChangeListener {
    AbstractButton button;

    /**
     * Constructor for the ActionChangedListener object
     *
     * @param b  Description of Parameter
     */
    ActionChangedListener(AbstractButton b) {
      super();
      setTarget(b);
    }

    /**
     * Sets the Target attribute of the ActionChangedListener object
     *
     * @param b  The new Target value
     */
    public void setTarget(AbstractButton b) {
      this.button = b;
    }

    /**
     * Description of the Method
     *
     * @param e  Description of Parameter
     */
    public void propertyChange(PropertyChangeEvent e) {
      String propertyName = e.getPropertyName();
      if (e.getPropertyName().equals(Action.NAME)) {
        String text = (String) e.getNewValue();
        button.setText(text);
        button.repaint();
      }
      else if (propertyName.equals("enabled")) {
        Boolean enabledState = (Boolean) e.getNewValue();
        button.setEnabled(enabledState.booleanValue());
        button.repaint();
      }
      else if (e.getPropertyName().equals(Action.SMALL_ICON)) {
        Icon icon = (Icon) e.getNewValue();
        button.setIcon(icon);
        button.invalidate();
        button.repaint();
      }
    }
  }

  // end SystemMenuBar

  /**
   * Description of the Class
   *
   * @author    fred
   * @created   27 avril 2002
   */
  private class NoFocusButton extends JButton {
    /**
     * Constructor for the NoFocusButton object
     */
    public NoFocusButton() {
      setFocusPainted(false);
    }

    /**
     * Gets the FocusTraversable attribute of the NoFocusButton object
     *
     * @return   The FocusTraversable value
     */
    public boolean isFocusTraversable() {
      return false;
    }

    /**
     * Gets the Opaque attribute of the NoFocusButton object
     *
     * @return   The Opaque value
     */
    public boolean isOpaque() {
      return true;
    }

    /**
     * Description of the Method
     */
    public void requestFocus() {
    }
  }
  // end NoFocusButton

}
// End Title Pane Class

