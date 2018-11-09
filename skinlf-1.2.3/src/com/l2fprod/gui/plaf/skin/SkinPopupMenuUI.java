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
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Description of the Class
 *
 * @author    fred
 * @created   27 avril 2002
 */
public class SkinPopupMenuUI extends BasicPopupMenuUI {

  PopupMenuListener m_PopupListener;
  AncestorListener m_AncestorListener;

  private Skin skin = SkinLookAndFeel.getSkin();

  /**
   * Description of the Method
   */
  public void installDefaults() {
    super.installDefaults();
    skin.getPersonality().installSkin(popupMenu);
    if (Boolean.TRUE.equals(UIManager.get("PopupMenu.animation"))) {
      popupMenu.setOpaque(false);
    }
  }

  /**
   * Description of the Method
   */
  public void installListeners() {
    super.installListeners();
    if (Boolean.TRUE.equals(UIManager.get("PopupMenu.animation"))) {
      popupMenu.addAncestorListener(m_AncestorListener = new SkinPopupAncestorListener());
      popupMenu.addPopupMenuListener(m_PopupListener = new SkinPopupMenuListener());
    }
  }

  /**
   * Description of the Method
   *
   * @param g  Description of Parameter
   * @param c  Description of Parameter
   */
  public void paint(Graphics g, JComponent c) {
    Graphics2D g2d = (Graphics2D) g;
    AlphaComposite alpha = (AlphaComposite) c.getClientProperty("alpha");
    if (alpha != null) {
      g2d.setComposite(alpha);
    }
    skin.getPersonality().paintDialog(g, c);
    super.paint(g, c);
  }

  /**
   * Description of the Method
   */
  protected void uninstallDefaults() {
    super.uninstallDefaults();
    popupMenu.setOpaque(true);
  }

  /**
   * Description of the Method
   */
  protected void uninstallListeners() {
    super.uninstallListeners();
    if (m_AncestorListener != null) {
      popupMenu.removeAncestorListener(m_AncestorListener);
    }
    if (m_PopupListener != null) {
      popupMenu.removePopupMenuListener(m_PopupListener);
    }
    popupMenu.putClientProperty("alpha", null);
  }

  /**
   * Description of the Method
   *
   * @param c  Description of Parameter
   * @return   Description of the Returned Value
   */
  public static ComponentUI createUI(JComponent c) {
    return new SkinPopupMenuUI();
  }

  public JPopupMenu popupMenu() {
    return popupMenu;
  }

  /**
   * Description of the Class
   *
   * @author    fred
   * @created   27 avril 2002
   */
  class SkinPopupAncestorListener implements AncestorListener {
    /**
     * Description of the Method
     *
     * @param event  Description of Parameter
     */
    public void ancestorAdded(AncestorEvent event) {
      Component parent = event.getComponent().getParent();
      if (parent instanceof JComponent) {
        ((JComponent) parent).setDoubleBuffered(false);
        ((JComponent) parent).setOpaque(false);
      }
      // end of if (parent instanceof JComponent)
    }

    /**
     * Description of the Method
     *
     * @param event  Description of Parameter
     */
    public void ancestorMoved(AncestorEvent event) {
    }

    /**
     * Description of the Method
     *
     * @param event  Description of Parameter
     */
    public void ancestorRemoved(AncestorEvent event) {
    }
  }

  /**
   * Description of the Class
   *
   * @author    fred
   * @created   27 avril 2002
   */
  class SkinPopupMenuListener implements PopupMenuListener {
    Thread popupAnimator = null;

    /**
     * Description of the Method
     *
     * @param e  Description of Parameter
     */
    public void popupMenuCanceled(PopupMenuEvent e) {
      if (popupAnimator != null) {
        popupAnimator.interrupt();
      }
      // end of if (popupAnimator != null)
      popupMenu().putClientProperty("alpha", null);
    }

    /**
     * Description of the Method
     *
     * @param e  Description of Parameter
     */
    public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
      if (popupAnimator != null) {
        popupAnimator.interrupt();
      }
      // end of if (popupAnimator != null)
      popupMenu().putClientProperty("alpha", null);
    }

    /**
     * Description of the Method
     *
     * @param e  Description of Parameter
     */
    public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
      popupMenu().putClientProperty("alpha",
          AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.0f));
      if (popupAnimator != null) {
        popupAnimator.interrupt();
      }
      // end of if (popupAnimator != null)
      popupAnimator =
        new Thread("PopupAnimator") {
          public void run() {
            try {
              AlphaComposite current =
                  (AlphaComposite) popupMenu().getClientProperty("alpha");
              while (current != null && current.getAlpha() < 0.75) {
                Thread.sleep(25);
                current = AlphaComposite.getInstance(current.getRule(), current.getAlpha() + 0.05f);
                popupMenu().putClientProperty("alpha", current);
                popupMenu().repaint();
              }
              // end of if (current.floatValue() < 1.0)
            } catch (InterruptedException e) {
            }
            popupAnimator = null;
          }
        };
      popupAnimator.start();
    }
  }

}
