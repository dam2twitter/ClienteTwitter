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

import java.awt.Dimension;
import java.awt.Image;
import java.net.URL;
import javax.swing.*;

import com.l2fprod.util.*;
import com.l2fprod.gui.plaf.skin.*;
import com.l2fprod.gui.plaf.skin.impl.*;

/**
 * @author    $Author: l2fprod $
 * @created   27 avril 2002
 * @version   $Revision: 1.3 $, $Date: 2002/04/27 09:54:57 $
 */
class Kde2Button extends AbstractSkinButton {

  DefaultButton pushButton, pushButtonDown;
  ImageIcon radio, radioDown;
  ImageIcon checkBox, checkBoxDown;

  /**
   * Constructor for the Kde2Button object
   *
   * @param ini            Description of Parameter
   * @param skinURL        Description of Parameter
   * @exception Exception  Description of Exception
   */
  public Kde2Button(IniFile ini, URL skinURL) throws Exception {
    // normal button
    pushButton = Kde2Utils.newButton(ini, skinURL, "PushButton");
    pushButtonDown = Kde2Utils.newButton(ini, skinURL, "PushButtonDown");

    // radio button
    radio = Kde2Utils.newIcon(ini, skinURL, "Radio");
    radioDown = Kde2Utils.newIcon(ini, skinURL, "RadioDown");

    // check box
    checkBox = Kde2Utils.newIcon(ini, skinURL, "CheckBox");
    checkBoxDown = Kde2Utils.newIcon(ini, skinURL, "CheckBoxDown");
  }

  /**
   * Gets the CheckBoxIconSize attribute of the Kde2Button object
   *
   * @return   The CheckBoxIconSize value
   */
  public Dimension getCheckBoxIconSize() {
    if (checkBox != null) {
      return new Dimension(checkBox.getIconWidth(), checkBox.getIconHeight());
    }
    else {
      return new Dimension(13, 13);
    }
  }

  /**
   * Gets the RadioIcon attribute of the Kde2Button object
   *
   * @param b  Description of Parameter
   * @return   The RadioIcon value
   */
  public javax.swing.Icon getRadioIcon(javax.swing.AbstractButton b) {
    ButtonModel model = b.getModel();
    if (b instanceof JRadioButton) {
      if (model.isSelected() || (model.isPressed() && model.isArmed())) {
        return radioDown;
      }
      else {
        return radio;
      }
      /*
       *  if (model.isEnabled() == false) {
       *  if (model.isSelected()) {
       *  return optionOUT;
       *  } else {
       *  return optionOUT;
       *  }
       *  } else {
       *  return optionIN;
       *  }
       */
    }
    else if ((b instanceof JCheckBox) || (b instanceof JCheckBoxMenuItem)) {
      if (model.isSelected() || (model.isPressed() && model.isArmed())) {
        return checkBoxDown;
      }
      else if (model.isEnabled() == false) {
        if (model.isSelected()) {
          return checkBox;
        }
        else {
          return checkBox;
        }
      }
      else {
        return checkBox;
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
    if (b.isEnabled() == false) {
      pushButton.paint(g, b);
    }
    else {
      //PENDING(fred): should handle disabledINButton,
      // when the toggle button is disabled but pressed (armed)
      if (b instanceof JToggleButton) {
        if ((model.isArmed() && model.isPressed()) || model.isSelected()) {
          pushButtonDown.paint(g, b);
        }
        else if (model.isRollover()) {
          pushButton.paint(g, b);
        }
        else {
          pushButton.paint(g, b);
        }
      }
      else if (b instanceof JButton) {
        if (model.isPressed()) {
          pushButtonDown.paint(g, b);
        }
        else if (!model.isArmed() && !model.isPressed() && b.hasFocus()) {
          pushButton.paint(g, b);
        }
        else if (model.isRollover()) {
          pushButton.paint(g, b);
        }
        else {
          pushButton.paint(g, b);
        }
      }
    }

    return true;
  }

}
