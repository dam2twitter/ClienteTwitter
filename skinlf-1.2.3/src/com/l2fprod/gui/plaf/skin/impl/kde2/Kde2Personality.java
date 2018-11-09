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
package com.l2fprod.gui.plaf.skin.impl.kde2;

import java.awt.*;
import java.net.URL;
import javax.swing.*;
import javax.swing.border.*;

import com.l2fprod.util.*;
import com.l2fprod.gui.plaf.skin.*;
import com.l2fprod.gui.plaf.skin.impl.*;
import com.l2fprod.gui.*;

/**
 * Created on 18/02/2001 by Frederic Lavigne, fred@L2FProd.com
 *
 * @author    $Author: l2fprod $
 * @created   27 avril 2002
 * @version   $Revision: 1.3 $, $Date: 2002/04/27 09:54:57 $
 */
class Kde2Personality extends AbstractSkinPersonality {

  Image background;

  DefaultButton comboBox;
  DefaultButton menuitemSelected;

  Border menuBarBorder;

  static int MINIMAL_SIZE = 50;

  /**
   * Constructor for the Kde2Personality object
   *
   * @param ini            Description of Parameter
   * @param skinURL        Description of Parameter
   * @exception Exception  Description of Exception
   */
  public Kde2Personality(IniFile ini, URL skinURL) throws Exception {
    try {
      background = Kde2Utils.newIcon(ini, skinURL, "Background").getImage();
      if (background != null) {
        int width = background.getWidth(ImageUtils.producer);
        int height = background.getHeight(ImageUtils.producer);
        int factor = Math.max(MINIMAL_SIZE / height, MINIMAL_SIZE / width);
        if (factor > 1) {
          background = ImageUtils.buildTile(background, factor);
        }
      }
    } catch (Exception e) {
    }

    comboBox = Kde2Utils.newButton(ini, skinURL, "ComboBox");
    menuitemSelected = Kde2Utils.newButton(ini, skinURL, "MenuItemDown");

    menuBarBorder = Kde2Utils.newButton(ini, skinURL, "MenuBar");
  }

  /**
   * Description of the Method
   *
   * @param c  Description of Parameter
   * @return   Description of the Returned Value
   */
  public boolean installSkin(JComponent c) {
    if ((c instanceof JMenuBar || c instanceof JToolBar) && (menuBarBorder != null)) {
      c.setBorder(menuBarBorder);
    }
    return true;
  }

  /**
   * Description of the Method
   *
   * @param g  Description of Parameter
   * @param c  Description of Parameter
   * @return   Description of the Returned Value
   */
  public boolean paintDialog(java.awt.Graphics g, java.awt.Component c) {
    if (background != null) {
      ImageUtils.paint(c, g, background, ImageUtils.PAINT_TILE);
      return true;
    }
    else {
      if (!((JComponent) c).isOpaque()) {
        Color holdC = g.getColor();
        g.setColor(c.getBackground());
        g.fillRect(0, 0, ((JComponent) c).getWidth(), ((JComponent) c).getHeight());
        g.setColor(holdC);
      }
      return true;
    }
  }

  /**
   * Description of the Method
   *
   * @param g  Description of Parameter
   * @param c  Description of Parameter
   * @return   Description of the Returned Value
   */
  public boolean paintMenuItem(java.awt.Graphics g, javax.swing.JMenuItem c) {
    if (menuitemSelected != null) {
      if (c.isArmed() || (c instanceof JMenu && c.getModel().isSelected())) {
        menuitemSelected.paint(g, c);
      }
      /*
       *  else {
       *  paintDialog(g, c);
       *  }
       */
      return true;
    }
    else {
      return false;
    }
  }

  /**
   * Description of the Method
   *
   * @param g         Description of Parameter
   * @param c         Description of Parameter
   * @param bounds    Description of Parameter
   * @param hasFocus  Description of Parameter
   * @return          Description of the Returned Value
   */
  public boolean paintComboBox(java.awt.Graphics g,
      javax.swing.JComboBox c,
      java.awt.Rectangle bounds, boolean hasFocus) {
    if (comboBox != null) {
      comboBox.paint(g, c);
      return true;
    }
    return false;
  }

}
