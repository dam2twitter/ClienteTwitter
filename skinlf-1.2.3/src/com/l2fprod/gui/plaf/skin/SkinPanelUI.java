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
import javax.swing.plaf.*;
import javax.swing.plaf.basic.*;
import java.awt.*;

/**
 * Description of the Class
 *
 * @author    fred
 */
public class SkinPanelUI extends BasicPanelUI {

  private Skin skin = SkinLookAndFeel.getSkin();

  /**
   * Description of the Method
   *
   * @param g  Description of Parameter
   * @param c  Description of Parameter
   */
  public void paint(Graphics g, JComponent c) {
    // don't paint a root panel, a popup panel and glasspane
    if (c.getParent() instanceof JRootPane ||
        c.getClass().getName().endsWith("JPanelPopup") ||
        (c.getParent() instanceof RootPaneContainer &&
         ((((RootPaneContainer)c.getParent()).getGlassPane() == c)
          || (((RootPaneContainer)c.getParent()).getLayeredPane() == c)
          //          || (((RootPaneContainer)c.getParent()).getContentPane() == c)
          ))) {
      return;
    }

    // always paint a ContentPane
    if (c.getParent() instanceof RootPaneContainer &&
        (((RootPaneContainer)c.getParent()).getContentPane() == c)) {
      skin.getPersonality().paintDialog(g, c);
      return;
    }
    
    // finally paint a JComponent if it is opaque and if it has not changed
    // its background color
    if (c.isOpaque() && c.getBackground() instanceof ColorUIResource) {
      skin.getPersonality().paintDialog(g, c);
    }
  }

  /**
   * Description of the Method
   *
   * @param p  Description of Parameter
   */
  protected void installDefaults(JPanel p) {
    super.installDefaults(p);
    skin.getPersonality().installSkin(p);
  }

  protected void uninstallDefaults(JPanel p) {
    super.uninstallDefaults(p);
    skin.getPersonality().uninstallSkin(p);
  }

  /**
   * Description of the Method
   *
   * @param c  Description of Parameter
   * @return   Description of the Returned Value
   */
  public static ComponentUI createUI(JComponent c) {
    return new SkinPanelUI();
  }

}
