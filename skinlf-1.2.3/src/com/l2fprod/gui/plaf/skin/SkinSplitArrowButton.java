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

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Insets;
import javax.swing.border.Border;

import javax.swing.*;

/**
 * Description of the Class
 *
 * @author    fred
 * @created   27 avril 2002
 */
public class SkinSplitArrowButton extends JButton implements SwingConstants {

  /**
   * Description of the Field
   */
  protected int direction;
  /**
   * Description of the Field
   */
  protected Skin skin = SkinLookAndFeel.getSkin();

  /**
   * Constructor for the SkinSplitArrowButton object
   *
   * @param direction  Description of Parameter
   */
  public SkinSplitArrowButton(int direction) {
    super();
    setRequestFocusEnabled(false);
    setDirection(direction);
    setBackground(UIManager.getColor("control"));
    Dimension size = getPreferredSize();
    setSize(size.width, size.height);
  }

  /**
   * Sets the Direction attribute of the SkinSplitArrowButton object
   *
   * @param dir  The new Direction value
   */
  public void setDirection(int dir) {
    direction = dir;
  }

  /**
   * Sets the Border attribute of the SkinSplitArrowButton object
   *
   * @param b  The new Border value
   */
  public void setBorder(Border b) {
  }

  /**
   * Gets the Direction attribute of the SkinSplitArrowButton object
   *
   * @return   The Direction value
   */
  public int getDirection() {
    return direction;
  }

  /**
   * Gets the PreferredSize attribute of the SkinSplitArrowButton object
   *
   * @return   The PreferredSize value
   */
  public Dimension getPreferredSize() {
    return skin.getSplitPane().getArrowPreferredSize(direction);
  }

  /**
   * Gets the MinimumSize attribute of the SkinSplitArrowButton object
   *
   * @return   The MinimumSize value
   */
  public Dimension getMinimumSize() {
    return new Dimension(5, 5);
  }

  /**
   * Gets the MaximumSize attribute of the SkinSplitArrowButton object
   *
   * @return   The MaximumSize value
   */
  public Dimension getMaximumSize() {
    return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
  }

  /**
   * Gets the Insets attribute of the SkinSplitArrowButton object
   *
   * @return   The Insets value
   */
  public Insets getInsets() {
    return new Insets(0, 0, 0, 0);
  }

  /**
   * Gets the FocusTraversable attribute of the SkinSplitArrowButton object
   *
   * @return   The FocusTraversable value
   */
  public boolean isFocusTraversable() {
    return false;
  }

  /**
   * Description of the Method
   *
   * @param g  Description of Parameter
   */
  public void paint(Graphics g) {
    skin.getSplitPane().paintArrow(g, this, direction);
  }

}

