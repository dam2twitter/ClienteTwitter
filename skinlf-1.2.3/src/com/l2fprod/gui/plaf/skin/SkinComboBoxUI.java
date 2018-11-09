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

import javax.swing.plaf.basic.*;
import javax.swing.*;
import javax.swing.plaf.*;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

/**
 * @author    $Author: l2fprod $
 * @version   $Revision: 1.8 $, $Date: 2002/06/11 14:52:47 $
 */
public class SkinComboBoxUI extends BasicComboBoxUI {

  /**
   * Description of the Field
   */
  protected PropertyChangeListener editableChangeListener;
  private Skin skin = SkinLookAndFeel.getSkin();

  /**
   * Gets the PreferredSize attribute of the SkinComboBoxUI object
   *
   * @param c  Description of Parameter
   * @return   The PreferredSize value
   */
  public Dimension getPreferredSize(JComponent c) {
    Dimension prefs = super.getMinimumSize(c);
    Dimension combo = skin.getPersonality().getComboBoxPreferredSize(comboBox);
    prefs.width = Math.max(prefs.width, combo.width);
    prefs.height = Math.max(prefs.height, combo.height);
    return prefs;
  }

  /**
   * Gets the MinimumSize attribute of the SkinComboBoxUI object
   *
   * @param c  Description of Parameter
   * @return   The MinimumSize value
   */
  public Dimension getMinimumSize(JComponent c) {
    return getPreferredSize(c);
  }

  /**
   * Description of the Method
   *
   * @param g  Description of Parameter
   * @param c  Description of Parameter
   */
  public void paint(Graphics g, JComponent c) {
    hasFocus = comboBox.hasFocus();
    if (comboBox.getRenderer() instanceof SkinComboBoxRenderer) {
      if (!comboBox.isEditable()) {
        Rectangle r = rectangleForCurrentValue();
        skin.getPersonality().paintComboBox(g, comboBox, r, hasFocus);
        paintCurrentValue(g, r, false);
      }
      else {
        g.setColor(Color.black);
        g.drawRect(0, 0, comboBox.getWidth(), comboBox.getHeight());
      }
    }
    else {
      super.paint(g, c);
    }
  }

  /**
   * Gets the Insets attribute of the SkinComboBoxUI object
   *
   * @return   The Insets value
   */
  protected Insets getInsets() {
    return skin.getPersonality().getComboBoxInsets();
  }

  /**
   * Description of the Method
   *
   * @return   Description of the Returned Value
   */
  protected JButton createArrowButton() {
    return new SkinArrowButton(SkinArrowButton.SOUTH);
  }

  /**
   * Description of the Method
   */
  protected void installComponents() {
    super.installComponents();
    arrowButton.setVisible(comboBox.isEditable());
  }

  /**
   * Description of the Method
   */
  protected void installListeners() {
    super.installListeners();
    if ((editableChangeListener = createEditableChangeListener()) != null) {
      comboBox.addPropertyChangeListener(editableChangeListener);
    }
  }

  /**
   * Description of the Method
   */
  protected void uninstallListeners() {
    super.uninstallListeners();
    comboBox.removePropertyChangeListener(editableChangeListener);
    editableChangeListener = null;
  }

  /**
   * Description of the Method
   *
   * @return   Description of the Returned Value
   */
  protected Rectangle rectangleForCurrentValue() {
    int width = comboBox.getWidth();
    int height = comboBox.getHeight();
    Insets insets = getInsets();
    int buttonSize = 0;
    if ((arrowButton != null) && arrowButton.isVisible()) {
      buttonSize = arrowButton.getWidth();
    }
    return new Rectangle(insets.left, insets.top,
        width - (insets.left + insets.right + buttonSize),
        height - (insets.top + insets.bottom));
  }

  /**
   * Description of the Method
   *
   * @return   Description of the Returned Value
   */
  protected LayoutManager createLayoutManager() {
    return new SkinComboBoxLayoutManager();
  }

  /**
   * Description of the Method
   *
   * @return   Description of the Returned Value
   */
  protected ListCellRenderer createRenderer() {
    return new SkinComboBoxRenderer();
  }

  /**
   * Description of the Method
   *
   * @return   Description of the Returned Value
   */
  protected PropertyChangeListener createEditableChangeListener() {
    return new EditableChangeHandler();
  }

  /**
   * Description of the Method
   *
   * @return   Description of the Returned Value
   */
  protected JButton arrowButton() {
    return arrowButton;
  }

  protected JComboBox comboBox() {
    return comboBox;
  }

  /**
   * Description of the Method
   */
  protected void installKeyboardActions() {
    super.installKeyboardActions();

    ActionListener downAction =
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          if (comboBox().isEnabled()) {
            if (isPopupVisible()) {
              selectNextPossibleValue();
            }
            else {
              setPopupVisible(comboBox(), true);
            }
          }
        }
      };

    comboBox.registerKeyboardAction(downAction,
        KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0),
        JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    comboBox.registerKeyboardAction(downAction,
        KeyStroke.getKeyStroke("KP_DOWN"),
        JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    ActionListener altAction =
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          if (comboBox().isEnabled() && isPopupVisible()) {
            togglePopup();
          }
        }
      };

    comboBox.registerKeyboardAction(altAction,
        KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, InputEvent.ALT_MASK),
        JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    comboBox.registerKeyboardAction(altAction,
        KeyStroke.getKeyStroke("alt KP_DOWN"),
        JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    comboBox.registerKeyboardAction(altAction,
        KeyStroke.getKeyStroke(KeyEvent.VK_UP, InputEvent.ALT_MASK),
        JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    comboBox.registerKeyboardAction(altAction,
        KeyStroke.getKeyStroke("alt KP_UP"),
        JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    ActionListener upAction =
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          if (comboBox().isEnabled() && isPopupVisible()) {
            selectPreviousPossibleValue();
          }
        }
      };

    comboBox.registerKeyboardAction(upAction,
        KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0),
        JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    comboBox.registerKeyboardAction(upAction,
        KeyStroke.getKeyStroke("KP_UP"),
        JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
  }

  /**
   * Description of the Method
   */
  protected void uninstallKeyboardActions() {
    super.uninstallKeyboardActions();
    comboBox.unregisterKeyboardAction(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0));
    comboBox.unregisterKeyboardAction(KeyStroke.getKeyStroke("KP_DOWN"));

    comboBox.unregisterKeyboardAction(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, InputEvent.ALT_MASK));
    comboBox.unregisterKeyboardAction(KeyStroke.getKeyStroke("alt KP_DOWN"));

    comboBox.unregisterKeyboardAction(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0));
    comboBox.unregisterKeyboardAction(KeyStroke.getKeyStroke("KP_UP"));

    comboBox.unregisterKeyboardAction(KeyStroke.getKeyStroke(KeyEvent.VK_UP, InputEvent.ALT_MASK));
    comboBox.unregisterKeyboardAction(KeyStroke.getKeyStroke("alt KP_UP"));
  }

  /**
   * Description of the Method
   *
   * @return   Description of the Returned Value
   */
  protected Component editor() {
    return editor;
  }

  /**
   * Gets the PopupVisible attribute of the SkinComboBoxUI object
   *
   * @return   The PopupVisible value
   */
  boolean isPopupVisible() {
    return super.isPopupVisible(comboBox);
  }

  /**
   * Description of the Method
   */
  void togglePopup() {
    toggleOpenClose();
  }

  /**
   * Description of the Method
   *
   * @param c  Description of Parameter
   * @return   Description of the Returned Value
   */
  public static ComponentUI createUI(JComponent c) {
    return new SkinComboBoxUI();
  }

  /**
   * Description of the Class
   *
   * @author    fred
   */
  public class EditableChangeHandler implements PropertyChangeListener {
    /**
     * Description of the Method
     *
     * @param e  Description of Parameter
     */
    public void propertyChange(PropertyChangeEvent e) {
      String propertyName = e.getPropertyName();
      if (propertyName.equals("renderer")) {
        if (e.getNewValue() instanceof SkinComboBoxRenderer == false) {
          SkinComboBoxUI.this.arrowButton().setVisible(true);
        }
        else {
          SkinComboBoxUI.this.arrowButton().setVisible(comboBox.isEditable());
        }
      }
      else if (propertyName.equals("editable")) {
        SkinComboBoxUI.this.arrowButton().setVisible(((Boolean) e.getNewValue()).booleanValue());
        SkinComboBoxUI.this.comboBox().revalidate();
        SkinComboBoxUI.this.comboBox().repaint();
      }
    }
  }

  /**
   * Description of the Class
   *
   * @author    fred
   */
  public class SkinComboBoxLayoutManager implements LayoutManager {
    /**
     * Adds a feature to the LayoutComponent attribute of the
     * SkinComboBoxLayoutManager object
     *
     * @param name  The feature to be added to the LayoutComponent attribute
     * @param comp  The feature to be added to the LayoutComponent attribute
     */
    public void addLayoutComponent(String name, Component comp) {
    }

    /**
     * Description of the Method
     *
     * @param comp  Description of Parameter
     */
    public void removeLayoutComponent(Component comp) {
    }

    /**
     * Description of the Method
     *
     * @param parent  Description of Parameter
     * @return        Description of the Returned Value
     */
    public Dimension preferredLayoutSize(Container parent) {
      return parent.getPreferredSize();
    }

    /**
     * Description of the Method
     *
     * @param parent  Description of Parameter
     * @return        Description of the Returned Value
     */
    public Dimension minimumLayoutSize(Container parent) {
      return parent.getMinimumSize();
    }

    /**
     * Description of the Method
     *
     * @param parent  Description of Parameter
     */
    public void layoutContainer(Container parent) {
      JComboBox cb = (JComboBox) parent;
      int width = cb.getWidth();
      int height = cb.getHeight();
      Insets insets = cb.getInsets();
      int buttonSize = height - (insets.top + insets.bottom);
      Rectangle cvb;

      if ((SkinComboBoxUI.this.arrowButton() != null) && SkinComboBoxUI.this.arrowButton().isVisible()) {
        SkinComboBoxUI.this.arrowButton().setBounds(width - (insets.right + buttonSize),
            insets.top,
            buttonSize, buttonSize);
      }
      if (SkinComboBoxUI.this.editor() != null) {
        cvb = rectangleForCurrentValue();
        SkinComboBoxUI.this.editor().setBounds(cvb);
      }
    }
  }

}
