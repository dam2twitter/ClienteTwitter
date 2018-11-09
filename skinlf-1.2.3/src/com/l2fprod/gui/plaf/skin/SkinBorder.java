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

import javax.swing.border.*;
import java.net.URL;
import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;

import com.l2fprod.util.IniFile;
import com.l2fprod.util.ImageUtils;
import com.l2fprod.gui.plaf.skin.*;
import com.l2fprod.gui.plaf.skin.impl.*;

/**
 * Created on 08/04/2000 by Frederic Lavigne, fred@L2FProd.com
 *
 * @author    $Author: l2fprod $
 * @created   27 avril 2002
 * @version   $Revision: 1.4 $, $Date: 2002/04/27 11:38:38 $
 */
public class SkinBorder implements Border {

  final ImageIcon top, bottom, left, right, topleft, topright, bottomleft, bottomright;
  final Insets insets;

  /**
   * Constructor for the SkinBorder object
   *
   * @param top          Description of Parameter
   * @param bottom       Description of Parameter
   * @param left         Description of Parameter
   * @param right        Description of Parameter
   * @param topleft      Description of Parameter
   * @param topright     Description of Parameter
   * @param bottomleft   Description of Parameter
   * @param bottomright  Description of Parameter
   */
  public SkinBorder(ImageIcon top, ImageIcon bottom,
      ImageIcon left, ImageIcon right,
      ImageIcon topleft, ImageIcon topright,
      ImageIcon bottomleft, ImageIcon bottomright) {
    this.top = top;
    this.bottom = bottom;
    this.left = left;
    this.right = right;
    this.topleft = topleft;
    this.topright = topright;
    this.bottomleft = bottomleft;
    this.bottomright = bottomright;

    insets = new Insets(top.getIconHeight(), right.getIconWidth(),
        bottom.getIconHeight(), left.getIconWidth());
  }

  /**
   * Gets the BorderInsets attribute of the SkinBorder object
   *
   * @param c  Description of Parameter
   * @return   The BorderInsets value
   */
  public Insets getBorderInsets(Component c) {
    return insets;
  }

  /**
   * Gets the BorderOpaque attribute of the SkinBorder object
   *
   * @return   The BorderOpaque value
   */
  public boolean isBorderOpaque() {
    return true;
  }

  /**
   * Description of the Method
   *
   * @param c       Description of Parameter
   * @param g       Description of Parameter
   * @param x       Description of Parameter
   * @param y       Description of Parameter
   * @param width   Description of Parameter
   * @param height  Description of Parameter
   */
  public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {

    // top
    topleft.paintIcon(c, g, x, y);
    topright.paintIcon(c, g, x + width - topright.getIconWidth(), y);

    // bottom
    bottomleft.paintIcon(c, g, x, y + height - bottomleft.getIconHeight());
    bottomright.paintIcon(c, g, x + width - bottomright.getIconWidth(),
        y + height - bottomright.getIconWidth());

    // left
    ImageUtils.paint(c, g, left.getImage(),
        x,
        y + topleft.getIconHeight(),
        left.getIconWidth(),
        height - topleft.getIconHeight() - bottomleft.getIconHeight(), false, ImageUtils.PAINT_TILE);

    // right
    ImageUtils.paint(c, g, right.getImage(),
        x + width - right.getIconWidth(),
        y + topleft.getIconHeight(),
        right.getIconWidth(),
        height - topright.getIconHeight() - bottomright.getIconHeight(), false, ImageUtils.PAINT_TILE);

    // top
    ImageUtils.paint(c, g, top.getImage(),
        x + topleft.getIconWidth(),
        y,
        width - topleft.getIconWidth() - topright.getIconWidth(),
        top.getIconHeight(), false, ImageUtils.PAINT_TILE);

    // bottom
    ImageUtils.paint(c, g, bottom.getImage(),
        x + bottomleft.getIconWidth(),
        y + height - bottom.getIconHeight(),
        width - bottomleft.getIconWidth() - bottomright.getIconWidth(),
        bottom.getIconHeight(), false, ImageUtils.PAINT_TILE);
  }

  /**
   * Gets the Icon attribute of the SkinBorder class
   *
   * @param skinURL        Description of Parameter
   * @param path           Description of Parameter
   * @return               The Icon value
   * @exception Exception  Description of Exception
   */
  public static ImageIcon getIcon(URL skinURL, String path) throws Exception {
    if (path != null && path.length() > 0) {
      return new ImageIcon(SkinUtils.loadImage(new URL(skinURL, path)));
    }
    else {
      return
        new ImageIcon() {
          public int getIconHeight() {
            return 0;
          }

          public int getIconWidth() {
            return 0;
          }

          public void paintIcon(Component c, Graphics g, int x, int y) {
          }
        };
    }
  }

}
