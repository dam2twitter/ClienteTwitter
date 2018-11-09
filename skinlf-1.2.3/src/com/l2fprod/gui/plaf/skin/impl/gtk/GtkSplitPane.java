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
package com.l2fprod.gui.plaf.skin.impl.gtk;

import java.awt.Rectangle;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.JComponent;

import com.l2fprod.util.ImageUtils;
import com.l2fprod.gui.plaf.skin.*;
import com.l2fprod.gui.plaf.skin.impl.*;
import com.l2fprod.gui.plaf.skin.impl.gtk.parser.*;

/**
 * @author    $Author: l2fprod $
 * @created   27 avril 2002
 * @version   $Revision: 1.3 $, $Date: 2002/06/11 18:04:53 $
 */
class GtkSplitPane extends AbstractSkinSplitPane implements SkinSplitPane, SwingConstants {

  DefaultButton h_gutter, v_gutter;
  DefaultButton h_thumb, v_thumb;

  DefaultButton up, down, left, right;

  /**
   * Constructor for the GtkSplitPane object
   *
   * @param parser         Description of Parameter
   * @exception Exception  Description of Exception
   */
  public GtkSplitPane(GtkParser parser) throws Exception {

    h_gutter = GtkUtils.newButton(parser, "GtkPaned",
        new String[]{"function", "orientation"},
        new String[]{"BOX", "HORIZONTAL"});

    v_gutter = GtkUtils.newButton(parser, "GtkPaned",
        new String[]{"function", "orientation"},
        new String[]{"BOX", "VERTICAL"});

    h_thumb = GtkUtils.newButton(parser, "GtkPaned",
        new String[]{"function", "orientation"},
        new String[]{"HANDLE", "HORIZONTAL"});
    if (h_thumb == null) {
      h_thumb = h_gutter;
    }

    v_thumb = GtkUtils.newButton(parser, "GtkPaned",
        new String[]{"function", "orientation"},
        new String[]{"HANDLE", "VERTICAL"});
    if (v_thumb == null) {
      v_thumb = v_gutter;
    }

    up = GtkUtils.newButton(parser, "GtkPaned",
        new String[]{"function", "arrow_direction"},
        new String[]{"ARROW", "UP"});

    down = GtkUtils.newButton(parser, "GtkPaned",
        new String[]{"function", "arrow_direction"},
        new String[]{"ARROW", "DOWN"});

    left = GtkUtils.newButton(parser, "GtkPaned",
        new String[]{"function", "arrow_direction"},
        new String[]{"ARROW", "LEFT"});

    right = GtkUtils.newButton(parser, "GtkPaned",
        new String[]{"function", "arrow_direction"},
        new String[]{"ARROW", "RIGHT"});
  }

  /**
   * Gets the PreferredSize attribute of the GtkSplitPane object
   *
   * @param splitpane  Description of Parameter
   * @return           The PreferredSize value
   */
  public Dimension getPreferredSize(JSplitPane splitpane) {
    Insets insets = splitpane.getInsets();
    int width = 0;
    int height = 0;
    if (splitpane.getOrientation() == JSplitPane.HORIZONTAL_SPLIT) {
      width = Math.max(Math.max(up.getWidth(), down.getWidth()), 10);
      height = splitpane.getHeight() + insets.top + insets.bottom;
    }
    else {
      height = Math.max(Math.max(left.getHeight(), right.getHeight()), 10);
      width = splitpane.getWidth() + insets.left + insets.right;
    }
    Dimension d = new Dimension(width, height);
    return (d);
    //return (splitpane.getOrientation() == JSplitPane.VERTICAL_SPLIT)
    //    ? new Dimension(Math.max(10, Math.min(up.getWidth(), v_thumb.getWidth())), 48)
    //	: new Dimension(48, Math.max(10, Math.min(left.getHeight(), h_thumb.getHeight())));
  }

  /**
   * Gets the ArrowPreferredSize attribute of the GtkSplitPane object
   *
   * @param direction  Description of Parameter
   * @return           The ArrowPreferredSize value
   */
  public Dimension getArrowPreferredSize(int direction) {
    switch (direction) {
      case NORTH:
        return new Dimension(up.getWidth(), up.getHeight());
      case SOUTH:
        return new Dimension(down.getWidth(), down.getHeight());
      case WEST:
        return new Dimension(left.getWidth(), left.getHeight());
      case EAST:
        return new Dimension(right.getWidth(), right.getHeight());
      default:
        throw new Error("Invalid direction " + direction);
    }

  }

  /**
   * Description of the Method
   *
   * @return   Description of the Returned Value
   */
  public boolean status() {
    return true;
  }

  /**
   * Description of the Method
   *
   * @param c  Description of Parameter
   * @return   Description of the Returned Value
   */
  public boolean installSkin(JComponent c) {
    return true;
  }

  /**
   * Description of the Method
   *
   * @param g          Description of Parameter
   * @param b          Description of Parameter
   * @param direction  Description of Parameter
   * @return           Description of the Returned Value
   */
  public boolean paintArrow(java.awt.Graphics g, javax.swing.AbstractButton b, int direction) {
    java.awt.Dimension size = b.getSize();
    switch (direction) {

      case NORTH:
        down.paint(g, 0, 0, size.width, size.height, b);
        break;
      case SOUTH:
        up.paint(g, 0, 0, size.width, size.height, b);
        break;
      case WEST:
        left.paint(g, 0, 0, size.width, size.height, b);
        break;
      case EAST:
        right.paint(g, 0, 0, size.width, size.height, b);
    }
    return true;
  }

  // track is under thumb
  /**
   * Description of the Method
   *
   * @param g          Description of Parameter
   * @param splitpane  Description of Parameter
   * @param d          Description of Parameter
   * @return           Description of the Returned Value
   */
  public boolean paintGutter(Graphics g, JSplitPane splitpane, Dimension d) {
    if (splitpane.getOrientation() == JSplitPane.HORIZONTAL_SPLIT) {
      h_gutter.paint(g, 0, 0, d.width, d.height, splitpane);
      return true;
    }
    else {
      v_gutter.paint(g, 0, 0, d.width, d.height, splitpane);
      return true;
    }
  }

  // thumb is the variable area
  /**
   * Description of the Method
   *
   * @param g          Description of Parameter
   * @param splitpane  Description of Parameter
   * @param d          Description of Parameter
   * @return           Description of the Returned Value
   */
  public boolean paintThumb(Graphics g, JSplitPane splitpane, Dimension d) {
    // the UI translate the graphics to thumbBounds.x and .y

    if (splitpane.getOrientation() == JSplitPane.HORIZONTAL_SPLIT && h_thumb != null) {
      h_thumb.paint(g, Math.max(0, (d.width - h_thumb.getWidth()) / 2),
          (d.height - h_thumb.getHeight()) / 2,
          Math.min(d.width, h_thumb.getWidth()),
          h_thumb.getHeight(), splitpane);
    }
    else if (splitpane.getOrientation() == JSplitPane.VERTICAL_SPLIT && v_thumb != null) {
      v_thumb.paint(g, (d.width - v_thumb.getWidth()) / 2,
          Math.max(0, (d.height - v_thumb.getHeight()) / 2),
          v_thumb.getWidth(),
          Math.min(d.height, v_thumb.getHeight()), splitpane);
    }
    else {
      return false;
    }
    return true;
  }

}
