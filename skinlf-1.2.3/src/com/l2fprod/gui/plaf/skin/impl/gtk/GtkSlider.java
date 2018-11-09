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
import javax.swing.JComponent;
import javax.swing.JSlider;
import javax.swing.SwingConstants;

import com.l2fprod.util.ImageUtils;
import com.l2fprod.gui.plaf.skin.*;
import com.l2fprod.gui.plaf.skin.impl.*;
import com.l2fprod.gui.plaf.skin.impl.gtk.parser.*;

/**
 * @author    $Author: l2fprod $
 * @created   27 avril 2002
 * @version   $Revision: 1.4 $, $Date: 2002/06/11 18:04:52 $
 */
class GtkSlider extends AbstractSkinSlider implements SkinSlider, SwingConstants {

  DefaultButton h_track, v_track;
  DefaultButton h_thumb, v_thumb;

  /**
   * Constructor for the GtkSlider object
   *
   * @param parser         Description of Parameter
   * @exception Exception  Description of Exception
   */
  public GtkSlider(GtkParser parser) throws Exception {
    h_thumb = GtkUtils.newButton(parser, "GtkRange",
        new String[]{"function", "orientation"},
        new String[]{"SLIDER", "HORIZONTAL"}, true);
    v_thumb = GtkUtils.newButton(parser, "GtkRange",
        new String[]{"function", "orientation"},
        new String[]{"SLIDER", "VERTICAL"}, true);

    h_track = GtkUtils.newButton(parser, "GtkRange",
        new String[]{"function", "detail", "orientation"},
        new String[]{"BOX", "trough", "HORIZONTAL"});
    v_track = GtkUtils.newButton(parser, "GtkRange",
        new String[]{"function", "detail", "orientation"},
        new String[]{"BOX", "trough", "VERTICAL"});
  }

  /**
   * Gets the PreferredSize attribute of the GtkSlider object
   *
   * @param slider  Description of Parameter
   * @return        The PreferredSize value
   */
  public Dimension getPreferredSize(JSlider slider) {
    return null;
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
    c.setOpaque(false);
    return true;
  }

  /**
   * Description of the Method
   *
   * @param g            Description of Parameter
   * @param slider       Description of Parameter
   * @param trackBounds  Description of Parameter
   * @return             Description of the Returned Value
   */
  public boolean paintTrack(Graphics g, JSlider slider, Rectangle trackBounds) {
    if (h_track != null) {
      if (slider.getOrientation() == HORIZONTAL) {
        h_track.paint(g, 0, 0, trackBounds.width, trackBounds.height, slider);
      }
      else {
        v_track.paint(g, 0, 0, trackBounds.width, trackBounds.height, slider);
      }
      return true;
    }
    else {
      return false;
    }
  }

  /**
   * Description of the Method
   *
   * @param g            Description of Parameter
   * @param slider       Description of Parameter
   * @param thumbBounds  Description of Parameter
   * @return             Description of the Returned Value
   */
  public boolean paintThumb(Graphics g, JSlider slider, Rectangle thumbBounds) {
    // the UI translate the graphics to thumbBounds.x and .y
    if (h_thumb != null) {
      if (slider.getOrientation() == HORIZONTAL) {
        h_thumb.paint(g, 0, 0, thumbBounds.width, thumbBounds.height, slider);
      }
      else {
        v_thumb.paint(g, 0, 0, thumbBounds.width, thumbBounds.height, slider);
      }
      return true;
    }
    else {
      return false;
    }
  }

}
