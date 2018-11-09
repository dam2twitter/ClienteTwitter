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

import javax.swing.plaf.basic.*;
import javax.swing.plaf.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import com.l2fprod.gui.plaf.xtra.XTraScrollBarUI;

/**
 * @author    $Author: l2fprod $
 * @created   27 avril 2002
 * @version   $Revision: 1.3 $, $Date: 2002/04/27 11:45:08 $
 */
public class SkinScrollBarUI extends XTraScrollBarUI {

  private Skin skin = SkinLookAndFeel.getSkin();

  /**
   * Gets the PreferredSize attribute of the SkinScrollBarUI object
   *
   * @param c  Description of Parameter
   * @return   The PreferredSize value
   */
  public Dimension getPreferredSize(JComponent c) {
    return skin.getScrollbar().getPreferredSize((JScrollBar) c);
  }

  /**
   * Gets the MinimumThumbSize attribute of the SkinScrollBarUI object
   *
   * @return   The MinimumThumbSize value
   */
  protected Dimension getMinimumThumbSize() {
    return skin.getScrollbar().getMinimumThumbSize();
  }

  /**
   * Description of the Method
   *
   * @param orientation  Description of Parameter
   * @return             Description of the Returned Value
   */
  protected JButton createDecreaseButton(int orientation) {
    return new SkinArrowButton(orientation);
  }

  /**
   * Description of the Method
   *
   * @param orientation  Description of Parameter
   * @return             Description of the Returned Value
   */
  protected JButton createIncreaseButton(int orientation) {
    return new SkinArrowButton(orientation);
  }

  /**
   * Description of the Method
   *
   * @param g            Description of Parameter
   * @param c            Description of Parameter
   * @param trackBounds  Description of Parameter
   */
  protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
    //        g.setColor(trackColor);
    //        g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);

    g.translate(trackBounds.x, trackBounds.y);
    skin.getScrollbar().paintTrack(g, scrollbar, trackBounds);
    g.translate(-trackBounds.x, -trackBounds.y);

    if (trackHighlight == DECREASE_HIGHLIGHT) {
      paintDecreaseHighlight(g);
    }
    else if (trackHighlight == INCREASE_HIGHLIGHT) {
      paintIncreaseHighlight(g);
    }
  }

  /**
   * Description of the Method
   *
   * @param g            Description of Parameter
   * @param c            Description of Parameter
   * @param thumbBounds  Description of Parameter
   */
  protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
    if (thumbBounds.isEmpty() || !scrollbar.isEnabled()) {
      return;
    }

    int w = thumbBounds.width;
    int h = thumbBounds.height;

    g.translate(thumbBounds.x, thumbBounds.y);

    skin.getScrollbar().paintThumb(g, scrollbar, thumbBounds);

    g.translate(-thumbBounds.x, -thumbBounds.y);
  }

  /**
   * Description of the Method
   *
   * @param x  Description of Parameter
   * @return   Description of the Returned Value
   */
  public static ComponentUI createUI(JComponent x) {
    return new SkinScrollBarUI();
  }

}

