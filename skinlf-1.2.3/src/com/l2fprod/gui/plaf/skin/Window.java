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

import java.awt.AWTEvent;
import java.awt.Insets;
import java.awt.Container;
import java.awt.Point;
import java.awt.Rectangle;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import javax.swing.Icon;
import javax.swing.JInternalFrame;
import javax.swing.WindowConstants;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import com.l2fprod.gui.SkinWindow;
import com.l2fprod.gui.DefaultWindowManager;
import com.l2fprod.util.OS;

/**
 * Created on 27/05/2000 by Frederic Lavigne, fred@L2FProd.com
 *
 * @author    $Author: l2fprod $
 * @created   27 avril 2002
 * @version   $Revision: 1.10 $, $Date: 2002/07/28 16:50:16 $
 */
public interface Window {

  /**
   * Description of the Field
   */
  public final static String IS_SHADED_PROPERTY = "shaded";

  /**
   * Gets the Container attribute of the Window object
   *
   * @return   The Container value
   */
  Container getContainer();

  /**
   * Adds a feature to the PropertyChangeListener attribute of the Window object
   *
   * @param listener  The feature to be added to the PropertyChangeListener
   *      attribute
   */
  void addPropertyChangeListener(PropertyChangeListener listener);

  /**
   * Remove a feature to the PropertyChangeListener attribute of the Window object
   *
   * @param listener  The feature to be remove from the PropertyChangeListener
   *      attribute
   */
  void removePropertyChangeListener(PropertyChangeListener listener);

  /**
   * Gets the Selected attribute of the Window object
   *
   * @return   The Selected value
   */
  boolean isSelected();

  /**
   * Sets the Selected attribute of the Window object
   *
   * @param b                          The new Selected value
   * @exception PropertyVetoException  Description of Exception
   */
  void setSelected(boolean b) throws PropertyVetoException;

  /**
   * Gets the Icon attribute of the Window object
   *
   * @return   The Icon value
   */
  boolean isIcon();

  /**
   * Sets the Icon attribute of the Window object
   *
   * @param b                          The new Icon value
   * @exception PropertyVetoException  Description of Exception
   */
  void setIcon(boolean b) throws PropertyVetoException;

  /**
   * Gets the Maximum attribute of the Window object
   *
   * @return   The Maximum value
   */
  boolean isMaximum();

  /**
   * Sets the Maximum attribute of the Window object
   *
   * @param b                          The new Maximum value
   * @exception PropertyVetoException  Description of Exception
   */
  void setMaximum(boolean b) throws PropertyVetoException;

  /**
   * Gets the Shaded attribute of the Window object
   *
   * @return   The Shaded value
   */
  boolean isShaded();

  /**
   * Sets the Shaded attribute of the Window object
   *
   * @param b  The new Shaded value
   */
  void setShaded(boolean b);

  /**
   * Gets the Maximizable attribute of the Window object
   *
   * @return   The Maximizable value
   */
  boolean isMaximizable();

  /**
   * Gets the Iconifiable attribute of the Window object
   *
   * @return   The Iconifiable value
   */
  boolean isIconifiable();

  /**
   * Gets the Closable attribute of the Window object
   *
   * @return   The Closable value
   */
  boolean isClosable();

  /**
   * Sets the Closed attribute of the Window object
   *
   * @param b                          The new Closed value
   * @exception PropertyVetoException  Description of Exception
   */
  void setClosed(boolean b) throws PropertyVetoException;

  /**
   * Gets the Resizable attribute of the Window object
   *
   * @return   The Resizable value
   */
  boolean isResizable();

  /**
   * Gets the Title attribute of the Window object
   *
   * @return   The Title value
   */
  String getTitle();

  /**
   * Gets the FrameIcon attribute of the Window object
   *
   * @return   The FrameIcon value
   */
  Icon getFrameIcon();

  /**
   * Description of the Method
   *
   * @param event  Description of Parameter
   */
  void dispatchEvent(AWTEvent event);

  /**
   * Description of the Class
   *
   * @author    fred
   * @created   27 avril 2002
   */
  public static class InternalFrameWindow implements Window {
    JInternalFrame frame;
    boolean shaded = false;

    /**
     * Constructor for the InternalFrameWindow object
     *
     * @param frame  Description of Parameter
     */
    public InternalFrameWindow(JInternalFrame frame) {
      this.frame = frame;
    }

    /**
     * Sets the Selected attribute of the InternalFrameWindow object
     *
     * @param b                          The new Selected value
     * @exception PropertyVetoException  Description of Exception
     */
    public void setSelected(boolean b) throws PropertyVetoException {
      frame.setSelected(b);
    }

    /**
     * Sets the Icon attribute of the InternalFrameWindow object
     *
     * @param b                          The new Icon value
     * @exception PropertyVetoException  Description of Exception
     */
    public void setIcon(boolean b) throws PropertyVetoException {
      frame.setIcon(b);
    }

    /**
     * Sets the Maximum attribute of the InternalFrameWindow object
     *
     * @param b                          The new Maximum value
     * @exception PropertyVetoException  Description of Exception
     */
    public void setMaximum(boolean b) throws PropertyVetoException {
      frame.setMaximum(b);
    }

    /**
     * Sets the Shaded attribute of the InternalFrameWindow object
     *
     * @param b  The new Shaded value
     */
    public void setShaded(boolean b) {
      if (b == shaded) {
        return;
      }

      if (b == true) {
        Rectangle bounds = frame.getBounds();
        Rectangle p = new Rectangle(bounds.x, bounds.y, bounds.width, bounds.height);
        frame.putClientProperty(DefaultWindowManager.SHADE_BOUNDS_PROPERTY, p);
        frame.setBounds(p.x, p.y, p.width,
            frame.getMinimumSize().height - 2);
      }
      else {
        Point location = frame.getLocation();
        Rectangle p = (Rectangle) frame.getClientProperty(DefaultWindowManager.SHADE_BOUNDS_PROPERTY);
        frame.getDesktopPane().getDesktopManager().setBoundsForFrame(frame, location.x, location.y,
            p.width, p.height);
        frame.putClientProperty(DefaultWindowManager.SHADE_BOUNDS_PROPERTY, null);
      }
      shaded = b;
    }

    /**
     * Sets the Closed attribute of the InternalFrameWindow object
     *
     * @param b                          The new Closed value
     * @exception PropertyVetoException  Description of Exception
     */
    public void setClosed(boolean b) throws PropertyVetoException {
      if (OS.isOneDotThreeOrMore()) {
        try {
          Class.forName("javax.swing.JInternalFrame").getMethod("doDefaultCloseAction", new Class[0]).invoke(frame, null);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      else {
        if (b == false) {
          frame.setClosed(b);
          return;
        }
        doDefaultCloseAction();
      }
    }

    /**
     * Gets the Container attribute of the InternalFrameWindow object
     *
     * @return   The Container value
     */
    public Container getContainer() {
      return frame;
    }

    /**
     * Gets the Selected attribute of the InternalFrameWindow object
     *
     * @return   The Selected value
     */
    public boolean isSelected() {
      return frame.isSelected();
    }

    /**
     * Gets the Icon attribute of the InternalFrameWindow object
     *
     * @return   The Icon value
     */
    public boolean isIcon() {
      return frame.isIcon();
    }

    /**
     * Gets the Maximum attribute of the InternalFrameWindow object
     *
     * @return   The Maximum value
     */
    public boolean isMaximum() {
      return frame.isMaximum();
    }

    /**
     * Gets the Shaded attribute of the InternalFrameWindow object
     *
     * @return   The Shaded value
     */
    public boolean isShaded() {
      return shaded;
    }

    /**
     * Gets the Maximizable attribute of the InternalFrameWindow object
     *
     * @return   The Maximizable value
     */
    public boolean isMaximizable() {
      return frame.isMaximizable();
    }

    /**
     * Gets the Iconifiable attribute of the InternalFrameWindow object
     *
     * @return   The Iconifiable value
     */
    public boolean isIconifiable() {
      return frame.isIconifiable();
    }

    /**
     * Gets the Closable attribute of the InternalFrameWindow object
     *
     * @return   The Closable value
     */
    public boolean isClosable() {
      return frame.isClosable();
    }

    /**
     * Gets the Resizable attribute of the InternalFrameWindow object
     *
     * @return   The Resizable value
     */
    public boolean isResizable() {
      return isShaded() == false && frame.isResizable();
    }

    /**
     * Gets the Title attribute of the InternalFrameWindow object
     *
     * @return   The Title value
     */
    public String getTitle() {
      return frame.getTitle();
    }

    /**
     * Gets the FrameIcon attribute of the InternalFrameWindow object
     *
     * @return   The FrameIcon value
     */
    public Icon getFrameIcon() {
      return frame.getFrameIcon();
    }

    /**
     * Adds a feature to the PropertyChangeListener attribute of the
     * InternalFrameWindow object
     *
     * @param listener  The feature to be added to the PropertyChangeListener
     *      attribute
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
      frame.addPropertyChangeListener(listener);
    }

    /**
     * Adds a feature to the PropertyChangeListener attribute of the
     * InternalFrameWindow object
     *
     * @param listener  The feature to be added to the PropertyChangeListener
     *      attribute
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
      frame.removePropertyChangeListener(listener);
    }

    /**
     * Description of the Method
     *
     * @param event  Description of Parameter
     */
    public void dispatchEvent(AWTEvent event) {
      frame.dispatchEvent(event);
    }

    // workaround for JDK1.2
    // seems to work as expected in most case
    /**
     * Description of the Method
     */
    private void doDefaultCloseAction() {
      int defaultCloseOperation = frame.getDefaultCloseOperation();
      frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
      switch (defaultCloseOperation) {
        case WindowConstants.HIDE_ON_CLOSE:
          try {
            frame.setClosed(true);
            frame.setVisible(false);
            if (frame.isSelected()) {
              frame.setSelected(false);
            }
          } catch (PropertyVetoException pve) {
          }
          break;
        case WindowConstants.DISPOSE_ON_CLOSE:
          try {
            frame.setClosed(true);
            frame.dispose();
            // only executes if close wasn't vetoed.
          } catch (PropertyVetoException pve) {
          }
          break;
        case 3:
          // EXIT_ON_CLOSE:
          System.exit(0);
          break;
        case WindowConstants.DO_NOTHING_ON_CLOSE:
          try {
            frame.setClosed(true);
          } catch (PropertyVetoException pve) {
          }
        default:
          break;
      }
      frame.setDefaultCloseOperation(defaultCloseOperation);
    }
  }

  /**
   * Description of the Class
   *
   * @author    fred
   * @created   27 avril 2002
   */
  public static class SkinWindowWindow implements Window {
    SkinWindow frame;

    /**
     * Constructor for the SkinWindowWindow object
     *
     * @param frame  Description of Parameter
     */
    public SkinWindowWindow(SkinWindow frame) {
      this.frame = frame;
    }

    /**
     * Sets the Selected attribute of the SkinWindowWindow object
     *
     * @param b                          The new Selected value
     * @exception PropertyVetoException  Description of Exception
     */
    public void setSelected(boolean b) throws PropertyVetoException {
      frame.setSelected(b);
    }

    /**
     * Sets the Icon attribute of the SkinWindowWindow object
     *
     * @param b                          The new Icon value
     * @exception PropertyVetoException  Description of Exception
     */
    public void setIcon(boolean b) throws PropertyVetoException {
      frame.setIcon(b);
    }

    /**
     * Sets the Maximum attribute of the SkinWindowWindow object
     *
     * @param b                          The new Maximum value
     * @exception PropertyVetoException  Description of Exception
     */
    public void setMaximum(boolean b) throws PropertyVetoException {
      frame.setMaximum(b);
    }

    /**
     * Sets the Shaded attribute of the SkinWindowWindow object
     *
     * @param b  The new Shaded value
     */
    public void setShaded(boolean b) {
      frame.setShaded(b);
    }

    /**
     * Sets the Closed attribute of the SkinWindowWindow object
     *
     * @param b                          The new Closed value
     * @exception PropertyVetoException  Description of Exception
     */
    public void setClosed(boolean b) throws PropertyVetoException {
      frame.setClosed(b);
    }

    /**
     * Gets the Container attribute of the SkinWindowWindow object
     *
     * @return   The Container value
     */
    public Container getContainer() {
      return frame;
    }

    /**
     * Gets the Selected attribute of the SkinWindowWindow object
     *
     * @return   The Selected value
     */
    public boolean isSelected() {
      return frame.isSelected();
    }

    /**
     * Gets the Icon attribute of the SkinWindowWindow object
     *
     * @return   The Icon value
     */
    public boolean isIcon() {
      return frame.isIcon();
    }

    /**
     * Gets the Maximum attribute of the SkinWindowWindow object
     *
     * @return   The Maximum value
     */
    public boolean isMaximum() {
      return frame.isMaximum();
    }

    /**
     * Gets the Maximizable attribute of the SkinWindowWindow object
     *
     * @return   The Maximizable value
     */
    public boolean isMaximizable() {
      return frame.isMaximizable();
    }

    /**
     * Gets the Shaded attribute of the SkinWindowWindow object
     *
     * @return   The Shaded value
     */
    public boolean isShaded() {
      return frame.isShaded();
    }

    /**
     * Gets the Iconifiable attribute of the SkinWindowWindow object
     *
     * @return   The Iconifiable value
     */
    public boolean isIconifiable() {
      return frame.isIconifiable();
    }

    /**
     * Gets the Closable attribute of the SkinWindowWindow object
     *
     * @return   The Closable value
     */
    public boolean isClosable() {
      return frame.isClosable();
    }

    /**
     * Gets the Resizable attribute of the SkinWindowWindow object
     *
     * @return   The Resizable value
     */
    public boolean isResizable() {
      return isShaded() == false && frame.isResizable();
    }

    /**
     * Gets the Title attribute of the SkinWindowWindow object
     *
     * @return   The Title value
     */
    public String getTitle() {
      return frame.getTitle();
    }

    /**
     * Gets the FrameIcon attribute of the SkinWindowWindow object
     *
     * @return   The FrameIcon value
     */
    public Icon getFrameIcon() {
      return frame.getFrameIcon();
    }

    /**
     * Adds a feature to the PropertyChangeListener attribute of the
     * SkinWindowWindow object
     *
     * @param listener  The feature to be added to the PropertyChangeListener
     *      attribute
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
      frame.addPropertyChangeListener(listener);
    }

    /**
     * Removes a feature to the PropertyChangeListener attribute of the
     * SkinWindowWindow object
     *
     * @param listener  The feature to be added to the PropertyChangeListener
     *      attribute
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
      frame.removePropertyChangeListener(listener);
    }

    /**
     * Description of the Method
     *
     * @param event  Description of Parameter
     */
    public void dispatchEvent(AWTEvent event) {
      frame.dispatchEvent(event);
    }
  }

}
