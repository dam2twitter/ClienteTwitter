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

import java.awt.Rectangle;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Graphics;
import java.net.URL;
import javax.swing.JScrollBar;
import javax.swing.SwingConstants;
import javax.swing.JComponent;

import com.l2fprod.util.*;
import com.l2fprod.gui.plaf.skin.*;
import com.l2fprod.gui.plaf.skin.impl.*;

/**
 * @author    $Author: l2fprod $
 * @created   27 avril 2002
 * @version   $Revision: 1.3 $, $Date: 2002/04/27 09:54:57 $
 */
class Kde2Scrollbar extends AbstractSkinScrollbar implements SwingConstants {

  DefaultButton h_track, v_track;
  DefaultButton h_thumb, v_thumb;

  DefaultButton h_handle, v_handle;

  DefaultButton up, down, left, right;

  /**
   * Constructor for the Kde2Scrollbar object
   *
   * @param ini            Description of Parameter
   * @param skinURL        Description of Parameter
   * @exception Exception  Description of Exception
   */
  public Kde2Scrollbar(IniFile ini, URL skinURL) throws Exception {
    h_thumb = Kde2Utils.newButton(ini, skinURL, "HSBarSlider");
    v_thumb = Kde2Utils.newButton(ini, skinURL, "VSBarSlider");

    h_track = Kde2Utils.newButton(ini, skinURL, "HScrollGroove");
    v_track = Kde2Utils.newButton(ini, skinURL, "VScrollGroove");

    /*
     *  h_handle = GtkUtils.newButton(parser, "default",
     *  new String[]{"function", "orientation"},
     *  new String[]{"HANDLE", "HORIZONTAL"}, true);
     *  if (h_handle!=null)
     *  h_handle.setCenterFill(ImageUtils.PAINT_STRETCH);
     *  v_handle = GtkUtils.newButton(parser, "default",
     *  new String[]{"function", "orientation"},
     *  new String[]{"HANDLE", "VERTICAL"}, true);
     *  if (v_handle!=null)
     *  v_handle.setCenterFill(ImageUtils.PAINT_STRETCH);
     */
    up = Kde2Utils.newButton(ini, skinURL, "ArrowUp");
    down = Kde2Utils.newButton(ini, skinURL, "ArrowDown");
    left = Kde2Utils.newButton(ini, skinURL, "ArrowLeft");
    right = Kde2Utils.newButton(ini, skinURL, "ArrowRight");
  }

  /**
   * Gets the PreferredSize attribute of the Kde2Scrollbar object
   *
   * @param scrollbar  Description of Parameter
   * @return           The PreferredSize value
   */
  public Dimension getPreferredSize(JScrollBar scrollbar) {
    return (scrollbar.getOrientation() == JScrollBar.VERTICAL)
        ? new Dimension(Math.max(10, Math.min(up.getWidth(), v_thumb.getWidth())), 48)
        : new Dimension(48, Math.max(10, Math.min(left.getHeight(), h_thumb.getHeight())));
  }

  /**
   * Gets the ArrowPreferredSize attribute of the Kde2Scrollbar object
   *
   * @param direction  Description of Parameter
   * @return           The ArrowPreferredSize value
   */
  public Dimension getArrowPreferredSize(int direction) {
    switch (direction) {
      case NORTH:
        return up.getPreferredSize();
      case SOUTH:
        return down.getPreferredSize();
      case WEST:
        return left.getPreferredSize();
      case EAST:
        return right.getPreferredSize();
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
    switch (direction) {
      case NORTH:
        up.paint(g, b);
        break;
      case SOUTH:
        down.paint(g, b);
        break;
      case WEST:
        left.paint(g, b);
        break;
      case EAST:
        right.paint(g, b);
    }
    return true;
  }

  // track is under thumb
  /**
   * Description of the Method
   *
   * @param g            Description of Parameter
   * @param scrollbar    Description of Parameter
   * @param trackBounds  Description of Parameter
   * @return             Description of the Returned Value
   */
  public boolean paintTrack(Graphics g, JScrollBar scrollbar, Rectangle trackBounds) {
    if (h_track != null) {
      if (scrollbar.getOrientation() == HORIZONTAL) {
        h_track.paint(g, 0, 0, trackBounds.width, trackBounds.height, scrollbar);
      }
      else {
        v_track.paint(g, 0, 0, trackBounds.width, trackBounds.height, scrollbar);
      }
      return true;
    }
    return false;
  }

  // thumb is the variable area
  /**
   * Description of the Method
   *
   * @param g            Description of Parameter
   * @param scrollbar    Description of Parameter
   * @param thumbBounds  Description of Parameter
   * @return             Description of the Returned Value
   */
  public boolean paintThumb(Graphics g, JScrollBar scrollbar, Rectangle thumbBounds) {
    // the UI translate the graphics to thumbBounds.x and .y
    if (h_thumb != null) {
      if (scrollbar.getOrientation() == HORIZONTAL) {
        h_thumb.paint(g, 0, 0, thumbBounds.width, thumbBounds.height, scrollbar);
        if (h_handle != null) {
          h_handle.paint(g, Math.max(0, (thumbBounds.width - h_handle.getWidth()) / 2),
              (thumbBounds.height - h_handle.getHeight()) / 2,
              Math.min(thumbBounds.width, h_handle.getWidth()),
              h_handle.getHeight(), scrollbar);
        }
      }
      else {
        v_thumb.paint(g, 0, 0, thumbBounds.width, thumbBounds.height, scrollbar);
        if (v_handle != null) {
          v_handle.paint(g, (thumbBounds.width - v_handle.getWidth()) / 2,
              Math.max(0, (thumbBounds.height - v_handle.getHeight()) / 2),
              v_handle.getWidth(),
              Math.min(thumbBounds.height, v_handle.getHeight()), scrollbar);
        }
      }
      return true;
    }
    return false;
  }

}
