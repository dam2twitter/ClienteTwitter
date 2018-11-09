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
import javax.swing.*;
import javax.swing.plaf.*;
import javax.swing.text.View;

import java.awt.*;

/**
 * Description of the Class
 *
 * @author    fred
 * @created   27 avril 2002
 */
public class SkinRadioButtonUI extends BasicRadioButtonUI {

  /**
   * Description of the Field
   */
  protected int dashedRectGapX;
  /**
   * Description of the Field
   */
  protected int dashedRectGapY;
  /**
   * Description of the Field
   */
  protected int dashedRectGapWidth;
  /**
   * Description of the Field
   */
  protected int dashedRectGapHeight;

  /**
   * Description of the Field
   */
  protected Color focusColor;

  /**
   * Description of the Field
   */
  protected Skin skin = SkinLookAndFeel.getSkin();

  private boolean initialized = false;

  final static SkinRadioButtonUI buttonUI = new SkinRadioButtonUI();

  // ********************************
  //          Paint Methods
  // ********************************
  /*
   *  These Dimensions/Rectangles are allocated once for all
   *  RadioButtonUI.paint() calls.  Re-using rectangles
   *  rather than allocating them in each paint call substantially
   *  reduced the time it took paint to run.  Obviously, this
   *  method can't be re-entered.
   */
  private static Dimension size = new Dimension();
  private static Rectangle viewRect = new Rectangle();
  private static Rectangle iconRect = new Rectangle();
  private static Rectangle textRect = new Rectangle();

  // ********************************
  //           Defaults
  // ********************************
  /**
   * Description of the Method
   *
   * @param b  Description of Parameter
   */
  public void installDefaults(AbstractButton b) {
    super.installDefaults(b);
    if (!initialized) {
      //    dashedRectGapX = ((Integer)UIManager.get("Button.dashedRectGapX")).intValue();
      //	    dashedRectGapY = ((Integer)UIManager.get("Button.dashedRectGapY")).intValue();
      //	    dashedRectGapWidth = ((Integer)UIManager.get("Button.dashedRectGapWidth")).intValue();
      //	    dashedRectGapHeight = ((Integer)UIManager.get("Button.dashedRectGapHeight")).intValue();
      focusColor = Color.black;
      //UIManager.getColor(getPropertyPrefix() + "focus");
      initialized = true;
    }
    b.setOpaque(false);
  }

  /**
   * paint the radio button
   *
   * @param g  Description of Parameter
   * @param c  Description of Parameter
   */
  public synchronized void paint(Graphics g, JComponent c) {
    AbstractButton b = (AbstractButton) c;
    ButtonModel model = b.getModel();

    Font f = c.getFont();
    g.setFont(f);
    FontMetrics fm = g.getFontMetrics();

    size = b.getSize(size);
    viewRect.x = viewRect.y = 0;
    viewRect.width = size.width;
    viewRect.height = size.height;
    iconRect.x = iconRect.y = iconRect.width = iconRect.height = 0;
    textRect.x = textRect.y = textRect.width = textRect.height = 0;

    Icon icon;
    if (b.isSelected()) {
      icon = b.getSelectedIcon();
    }
    else {
      icon = b.getIcon();
    }

    if (b.isRolloverEnabled() && model.isRollover()) {
      if (b.isSelected()) {
        icon = b.getRolloverSelectedIcon();
      }
      else {
        icon = b.getRolloverIcon();
      }
    }

    if (icon == null) {
      icon = skin.getButton().getRadioIcon(b);
    }

    String text =
        SwingUtilities.layoutCompoundLabel(
        c, fm, b.getText(), icon,
        b.getVerticalAlignment(), b.getHorizontalAlignment(),
        b.getVerticalTextPosition(), b.getHorizontalTextPosition(),
        viewRect, iconRect, textRect, getDefaultTextIconGap(b));

    // fill background
    if (c.isOpaque()) {
      g.setColor(b.getBackground());
      g.fillRect(0, 0, size.width, size.height);
    }

    if (icon != null) {
      icon.paintIcon(c, g, iconRect.x, iconRect.y);
    }

    // Draw the Text
    if (text != null) {
      View v = (View) c.getClientProperty("html");
      // BasicHTML.propertyKey
      if (v != null) {
        v.paint(g, textRect);
      }
      else {
        if (model.isEnabled()) {
          // *** paint the text normally
          g.setColor(b.getForeground());
          BasicGraphicsUtils.drawString(g, text, model.getMnemonic(),
              textRect.x,
              textRect.y + fm.getAscent());
        }
        else {
          // *** paint the text disabled
          g.setColor(b.getBackground().brighter());
          BasicGraphicsUtils.drawString(g, text, model.getMnemonic(),
              textRect.x + 1,
              textRect.y + fm.getAscent() + 1);
          g.setColor(b.getBackground().darker());
          BasicGraphicsUtils.drawString(g, text, model.getMnemonic(),
              textRect.x,
              textRect.y + fm.getAscent());
        }
      }
    }

    if (b.hasFocus() && b.isFocusPainted() &&
        textRect.width > 0 && textRect.height > 0) {
      paintFocus(g, textRect, size);
    }
  }

  /**
   * Gets the FocusColor attribute of the SkinRadioButtonUI object
   *
   * @return   The FocusColor value
   */
  protected Color getFocusColor() {
    return focusColor;
  }

  /**
   * Description of the Method
   *
   * @param g         Description of Parameter
   * @param textRect  Description of Parameter
   * @param d         Description of Parameter
   */
  protected void paintFocus(Graphics g, Rectangle textRect, Dimension d) {
    g.setColor(getFocusColor());
    BasicGraphicsUtils.drawDashedRect(g, textRect.x, textRect.y, textRect.width, textRect.height);
  }

  // ********************************
  //          Create PLAF
  // ********************************
  /**
   * Description of the Method
   *
   * @param c  Description of Parameter
   * @return   Description of the Returned Value
   */
  public static ComponentUI createUI(JComponent c) {
    buttonUI.skin = SkinLookAndFeel.getSkin();
    buttonUI.initialized = false;
    return buttonUI;
  }

}

