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

import java.awt.Dimension;
import java.awt.Image;
import javax.swing.*;

import com.l2fprod.util.ImageUtils;
import com.l2fprod.gui.plaf.skin.*;
import com.l2fprod.gui.plaf.skin.impl.*;
import com.l2fprod.gui.plaf.skin.impl.gtk.parser.*;

/**
 * @author    $Author: l2fprod $
 * @created   27 avril 2002
 * @version   $Revision: 1.12 $, $Date: 2002/06/11 18:04:51 $
 */
class GtkButton extends AbstractSkinButton implements SkinButton {

  DefaultButton checkIN, checkOUT;
  DefaultButton optionIN, optionOUT;

  Icon disabledCheckIN, disabledCheckOUT;
  Icon disabledOptionIN, disabledOptionOUT;

  DefaultButton disabledButton, pressedButton, normalButton, rolloverButton;
  DefaultButton disabledButtonIN;

  DefaultButton toggleIN, toggleOUT, toggleRollover;

  /**
   * Constructor for the GtkButton object
   *
   * @param parser         Description of Parameter
   * @exception Exception  Description of Exception
   */
  public GtkButton(GtkParser parser) throws Exception {
    normalButton = GtkUtils.newButton(parser, "GtkButton",
        new String[]{"function", "state", "shadow"},
        new String[]{"BOX", "NORMAL", "OUT"});

    pressedButton = GtkUtils.newButton(parser, "GtkButton",
        new String[]{"function", "state", "shadow"},
        new String[]{"BOX", "ACTIVE", "IN"});

    /*
     *  focusButton = GtkUtils.newButton(parser, "GtkButton",
     *  new String[]{"function", "state", "shadow"},
     *  new String[]{"BOX", "SELECTED", "IN"});
     *  if (focusButton == null) focusButton=pressedButton;
     */
    disabledButton = GtkUtils.newButton(parser, "GtkButton",
        new String[]{"function", "state", "shadow"},
        new String[]{"BOX", "INSENSITIVE", "OUT"});

    disabledButtonIN = GtkUtils.newButton(parser, "GtkButton",
        new String[]{"function", "state", "shadow"},
        new String[]{"BOX", "INSENSITIVE", "IN"});

    rolloverButton = GtkUtils.newButton(parser, "GtkButton",
        new String[]{"function", "state"},
        new String[]{"BOX", "PRELIGHT"});

    // toggle button
    toggleIN = GtkUtils.newButton(parser, "GtkToggleButton",
        new String[]{"function", "shadow"},
        new String[]{"BOX", "IN"});

    toggleOUT = GtkUtils.newButton(parser, "GtkToggleButton",
        new String[]{"function", "shadow"},
        new String[]{"BOX", "OUT"});

    toggleRollover = GtkUtils.newButton(parser, "GtkToggleButton",
        new String[]{"function", "state"},
        new String[]{"BOX", "PRELIGHT"});

    // the radio button
    //	GtkStyle radioStyle = parser.getClass("GtkRadioButton").getStyle();

    GtkStyle defaultStyle = parser.getStyle("check");
    if (defaultStyle == null) {
      // try the checkradiobutton style
      defaultStyle = parser.getStyle("checkradiobutton");
      if (defaultStyle == null) {
        defaultStyle = parser.getStyle("default");
      }
    }

    checkIN = GtkUtils.newButton(parser, "GtkCheckButton",
        new String[]{"function", "shadow"},
        new String[]{"CHECK", "IN"});
    disabledCheckIN = ImageUtils.getDisabledIcon(checkIN.center);

    checkOUT = GtkUtils.newButton(parser, "GtkCheckButton",
        new String[]{"function", "shadow"},
        new String[]{"CHECK", "OUT"});
    disabledCheckOUT = ImageUtils.getDisabledIcon(checkOUT.center);

    optionIN = GtkUtils.newButton(parser, "GtkRadioButton",
        new String[]{"function", "shadow"},
        new String[]{"OPTION", "IN"});
    disabledOptionIN = ImageUtils.getDisabledIcon(optionIN.center);

    optionOUT = GtkUtils.newButton(parser, "GtkRadioButton",
        new String[]{"function", "shadow"},
        new String[]{"OPTION", "OUT"});
    disabledOptionOUT = ImageUtils.getDisabledIcon(optionOUT.center);
  }

  /**
   * Gets the CheckBoxIconSize attribute of the GtkButton object
   *
   * @return   The CheckBoxIconSize value
   */
  public Dimension getCheckBoxIconSize() {
    if (checkIN != null) {
      return new Dimension(checkIN.getIconWidth(), checkIN.getIconHeight());
    }
    else {
      return new Dimension(13, 13);
    }
  }

  /**
   * Gets the RadioButtonIconSize attribute of the GtkButton object
   *
   * @return   The RadioButtonIconSize value
   */
  public Dimension getRadioButtonIconSize() {
    if (optionIN != null) {
      return new Dimension(optionIN.getIconWidth(), optionIN.getIconHeight());
    }
    else {
      return new Dimension(13, 13);
    }
  }

  /**
   * Gets the RadioIcon attribute of the GtkButton object
   *
   * @param b  Description of Parameter
   * @return   The RadioIcon value
   */
  public javax.swing.Icon getRadioIcon(javax.swing.AbstractButton b) {
    ButtonModel model = b.getModel();
    if ((b instanceof JRadioButton) || (b instanceof JRadioButtonMenuItem)) {
      if (model.isSelected() || (model.isPressed() && model.isArmed())) {
        return b.isEnabled() ? optionIN : disabledOptionIN;
        //} else
        //return optionOUT;
      }
      else if (model.isEnabled() == false) {
        if (model.isSelected()) {
          return b.isEnabled() ? optionOUT : disabledOptionOUT;
        }
        else {
          return b.isEnabled() ? optionOUT : disabledOptionOUT;
        }
      }
      else {
        return b.isEnabled() ? optionOUT : disabledOptionOUT;
      }
    }
    else if ((b instanceof JCheckBox) || (b instanceof JCheckBoxMenuItem)) {
      if (model.isSelected() || (model.isPressed() && model.isArmed())) {
        return b.isEnabled() ? checkIN : disabledCheckIN;
      }
      else if (model.isEnabled() == false) {
        if (model.isSelected()) {
          return b.isEnabled() ? checkOUT : disabledCheckOUT;
        }
        else {
          return b.isEnabled() ? checkOUT : disabledCheckOUT;
        }
      }
      else {
        return b.isEnabled() ? checkOUT : disabledCheckOUT;
      }
    }
    else {
      return null;
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
   * @param g  Description of Parameter
   * @param b  Description of Parameter
   * @return   Description of the Returned Value
   */
  public boolean paintButton(java.awt.Graphics g, javax.swing.AbstractButton b) {
    ButtonModel model = b.getModel();

    if (GtkUtils.DEBUG) {
      System.out.println("Button " + b.getClass().getName() + " " + b.getText());
      System.out.println("\tisEnabled() = " + model.isEnabled());
      System.out.println("\tisOpaque() = " + b.isOpaque());
      System.out.println("\tisArmed() = " + model.isArmed());
      System.out.println("\tisPressed() = " + model.isPressed());
      System.out.println("\tisSelected() = " + model.isSelected());
      System.out.println("\thasFocus() = " + b.hasFocus());
      System.out.println("\tisRollover() = " + model.isRollover());
    }

    if (b.isEnabled() == false) {
      disabledButton.paint(g, b);
    }
    else {
      //PENDING(fred): should handle disabledINButton,
      // when the toggle button is disabled but pressed (armed)
      if (b instanceof JToggleButton) {
        if ((model.isArmed() && model.isPressed()) || model.isSelected()) {
          toggleIN.paint(g, b);
        }
        else if (model.isRollover()) {
          toggleRollover.paint(g, b);
        }
        else {
          toggleOUT.paint(g, b);
        }
      }
      else if (b instanceof JButton) {
        if (model.isPressed() && (b.isRolloverEnabled() == false || (b.isRolloverEnabled() && model.isRollover()))) {
          pressedButton.paint(g, b);
        }
        else if (model.isRollover()) {
          rolloverButton.paint(g, b);
        }
        else {
          normalButton.paint(g, b);
        }
      }
    }

    return true;
  }

}
