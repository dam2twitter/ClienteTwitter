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
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.*;
import java.beans.*;
import java.util.EventListener;
import java.io.Serializable;

/**
 * Description of the Class
 *
 * @author    fred
 * @created   27 avril 2002
 */
public class SkinDesktopIconUI extends BasicDesktopIconUI {
  /**
   * Description of the Field
   */
  protected PropertyChangeListener propertyChangeListener;

  JLabel iconPane;
  MouseInputListener mouseInputListener;

  /**
   * Gets the MinimumSize attribute of the SkinDesktopIconUI object
   *
   * @param c  Description of Parameter
   * @return   The MinimumSize value
   */
  public Dimension getMinimumSize(JComponent c) {
    return iconPane.getMinimumSize();
  }

  /**
   * Gets the MaximumSize attribute of the SkinDesktopIconUI object
   *
   * @param c  Description of Parameter
   * @return   The MaximumSize value
   */
  public Dimension getMaximumSize(JComponent c) {
    return iconPane.getMaximumSize();
  }

  /**
   * Gets the PreferredSize attribute of the SkinDesktopIconUI object
   *
   * @param c  Description of Parameter
   * @return   The PreferredSize value
   */
  public Dimension getPreferredSize(JComponent c) {
    return iconPane.getPreferredSize();
  }

  /**
   * Description of the Method
   */
  protected void installComponents() {
    frame = desktopIcon.getInternalFrame();
    desktopIcon.setBorder(null);
    iconPane = new JLabel(frame.getTitle(),
        frame.getFrameIcon(),
        JLabel.CENTER);
    iconPane.setHorizontalTextPosition(JLabel.CENTER);
    iconPane.setVerticalTextPosition(JLabel.BOTTOM);
    iconPane.setHorizontalAlignment(JLabel.CENTER);

    desktopIcon.setLayout(new BorderLayout());
    desktopIcon.add(iconPane, BorderLayout.CENTER);
    desktopIcon.setOpaque(true);
  }

  /**
   * Description of the Method
   */
  protected void installListeners() {
    mouseInputListener = createMouseInputListener();
    iconPane.addMouseMotionListener(mouseInputListener);
    iconPane.addMouseListener(mouseInputListener);
    if (propertyChangeListener == null) {
      propertyChangeListener = createPropertyChangeListener();
    }
    desktopIcon.getInternalFrame().addPropertyChangeListener(propertyChangeListener);
  }

  /**
   * Description of the Method
   */
  protected void uninstallListeners() {
    super.uninstallListeners();
    desktopIcon.getInternalFrame().removePropertyChangeListener(propertyChangeListener);
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
   */
  protected void uninstallComponents() {
    desktopIcon.setLayout(null);
    desktopIcon.remove(iconPane);
  }

  /**
   * Description of the Method
   *
   * @param c  Description of Parameter
   * @return   Description of the Returned Value
   */
  public static ComponentUI createUI(JComponent c) {
    return new SkinDesktopIconUI();
  }

  /**
   * Description of the Class
   *
   * @author    fred
   * @created   27 avril 2002
   */
  public class PropertyChangeHandler implements PropertyChangeListener {
    /**
     * Description of the Method
     *
     * @param evt  Description of Parameter
     */
    public void propertyChange(PropertyChangeEvent evt) {
      String prop = evt.getPropertyName();
      if (JInternalFrame.TITLE_PROPERTY.equals(prop)) {
        // PENDING(fred): we need to trim the title or define a maximum size
        // or display it on multiple lines ?
        iconPane.setText((String) evt.getNewValue());
      }
      else if (JInternalFrame.FRAME_ICON_PROPERTY.equals(prop)) {
        iconPane.setIcon((Icon) evt.getNewValue());
      }
    }
  }

}

