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

import java.awt.*;
import javax.swing.*;
import com.l2fprod.gui.plaf.skin.impl.gtk.parser.*;
import com.l2fprod.gui.plaf.skin.*;
import com.l2fprod.util.ImageUtils;

/**
 * @author    $Author: l2fprod $
 * @created   27 avril 2002
 * @version   $Revision: 1.5 $, $Date: 2002/04/27 11:33:25 $
 */
class ArrowButton {

  DefaultButton normal, focus, pressed;

  /**
   * Constructor for the ArrowButton object
   *
   * @param parser         Description of Parameter
   * @param direction      Description of Parameter
   * @exception Exception  Description of Exception
   */
  public ArrowButton(GtkParser parser, String direction) throws Exception {
    normal = GtkUtils.newButton(parser, "arrows",
        new String[]{"function", "state", "arrow_direction"},
        new String[]{"ARROW", "NORMAL", direction});
    normal.setCenterFill(ImageUtils.PAINT_STRETCH);
    focus = GtkUtils.newButton(parser, "arrows",
        new String[]{"function", "state", "arrow_direction"},
        new String[]{"ARROW", "PRELIGHT", direction});
    focus.setCenterFill(ImageUtils.PAINT_STRETCH);
    pressed = GtkUtils.newButton(parser, "arrows",
        new String[]{"function", "shadow", "arrow_direction"},
        new String[]{"ARROW", "IN", direction});
    pressed.setCenterFill(ImageUtils.PAINT_STRETCH);
  }

  /**
   * Gets the Width attribute of the ArrowButton object
   *
   * @return   The Width value
   */
  public int getWidth() {
    return normal.getWidth();
  }

  /**
   * Gets the Height attribute of the ArrowButton object
   *
   * @return   The Height value
   */
  public int getHeight() {
    return normal.getHeight();
  }

  /**
   * Gets the PreferredSize attribute of the ArrowButton object
   *
   * @return   The PreferredSize value
   */
  public Dimension getPreferredSize() {
    return new java.awt.Dimension(getWidth(), getHeight());
  }

  /**
   * Description of the Method
   *
   * @param g  Description of Parameter
   * @param b  Description of Parameter
   */
  public void paint(Graphics g, AbstractButton b) {
    ButtonModel model = b.getModel();
    if (b.isEnabled() == false) {
      normal.paint(g, b);
    }
    else if (model.isArmed() && model.isPressed()) {
      pressed.paint(g, b);
    }
    else if (model.isRollover() && (focus != null)) {
      focus.paint(g, b);
    }
    else {
      normal.paint(g, b);
    }
  }

}
