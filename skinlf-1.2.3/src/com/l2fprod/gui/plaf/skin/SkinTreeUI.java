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

import java.io.*;
import java.util.*;

import javax.swing.plaf.basic.*;
import javax.swing.*;
import javax.swing.plaf.*;

/**
 * A placeholder for default Tree icons.
 *
 * @author    fred
 * @created   27 avril 2002
 */
public class SkinTreeUI extends BasicTreeUI {

  /**
   * Description of the Field
   */
  protected final static int HALF_SIZE = 4;
  /**
   * Description of the Field
   */
  protected final static int SIZE = 9;

  /**
   * Description of the Method
   *
   * @param g       Description of Parameter
   * @param c       Description of Parameter
   * @param x       Description of Parameter
   * @param top     Description of Parameter
   * @param bottom  Description of Parameter
   */
  protected void paintVerticalLine(Graphics g, JComponent c, int x, int top, int bottom) {
    drawDashedVerticalLine(g, x, top, bottom);
  }

  /**
   * Description of the Method
   *
   * @param g      Description of Parameter
   * @param c      Description of Parameter
   * @param y      Description of Parameter
   * @param left   Description of Parameter
   * @param right  Description of Parameter
   */
  protected void paintHorizontalLine(Graphics g, JComponent c, int y, int left, int right) {
    drawDashedHorizontalLine(g, y, left, right);
  }

  /**
   * Description of the Method
   *
   * @param c  Description of Parameter
   * @return   Description of the Returned Value
   */
  public static ComponentUI createUI(JComponent c) {
    return new SkinTreeUI();
  }

  /**
   * The minus sign button icon
   *
   * @author    fred
   * @created   27 avril 2002
   */
  public static class ExpandedIcon implements Icon, Serializable {
    /**
     * Gets the IconWidth attribute of the ExpandedIcon object
     *
     * @return   The IconWidth value
     */
    public int getIconWidth() {
      return SIZE;
    }

    /**
     * Gets the IconHeight attribute of the ExpandedIcon object
     *
     * @return   The IconHeight value
     */
    public int getIconHeight() {
      return SIZE;
    }

    /**
     * Description of the Method
     *
     * @param c  Description of Parameter
     * @param g  Description of Parameter
     * @param x  Description of Parameter
     * @param y  Description of Parameter
     */
    public void paintIcon(Component c, Graphics g, int x, int y) {
      Color backgroundColor = c.getBackground();

      if (backgroundColor != null) {
        g.setColor(backgroundColor);
      }
      else {
        g.setColor(Color.white);
      }
      g.fillRect(x, y, SIZE - 1, SIZE - 1);
      g.setColor(Color.gray);
      g.drawRect(x, y, SIZE - 1, SIZE - 1);
      g.setColor(Color.black);
      g.drawLine(x + 2, y + HALF_SIZE, x + (SIZE - 3), y + HALF_SIZE);
    }

    /**
     * Description of the Method
     *
     * @return   Description of the Returned Value
     */
    public static Icon createExpandedIcon() {
      return new ExpandedIcon();
    }
  }

  /**
   * The plus sign button icon
   *
   * @author    fred
   * @created   27 avril 2002
   */
  public static class CollapsedIcon extends ExpandedIcon {

    /**
     * Description of the Method
     *
     * @param c  Description of Parameter
     * @param g  Description of Parameter
     * @param x  Description of Parameter
     * @param y  Description of Parameter
     */
    public void paintIcon(Component c, Graphics g, int x, int y) {
      super.paintIcon(c, g, x, y);
      g.drawLine(x + HALF_SIZE, y + 2, x + HALF_SIZE, y + (SIZE - 3));
    }

    /**
     * Description of the Method
     *
     * @return   Description of the Returned Value
     */
    public static Icon createCollapsedIcon() {
      return new CollapsedIcon();
    }
  }

}
