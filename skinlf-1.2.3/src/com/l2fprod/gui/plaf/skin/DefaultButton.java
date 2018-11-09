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
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.*;
import com.l2fprod.util.ImageUtils;

/**
 * Stretch/Tile Button. <br>
 *
 *
 * @author    $Author: l2fprod $
 * @created   27 avril 2002
 * @version   $Revision: 1.13 $, $Date: 2002/05/24 20:47:30 $
 */
public class DefaultButton implements Icon, Border, UIResource, java.io.Serializable {

  /**
   * Description of the Field
   */
  public transient Image topleft;
  /**
   * Description of the Field
   */
  public transient Image topright;
  /**
   * Description of the Field
   */
  public transient Image bottomleft;
  /**
   * Description of the Field
   */
  public transient Image bottomright;
  /**
   * Description of the Field
   */
  public transient Image top, right, bottom, left, center;

  int topHeight, bottomHeight, leftWidth, rightWidth;
  int imageWidth, imageHeight;

  int top_fill = ImageUtils.PAINT_STRETCH;
  int right_fill = ImageUtils.PAINT_STRETCH;
  int bottom_fill = ImageUtils.PAINT_STRETCH;
  int left_fill = ImageUtils.PAINT_STRETCH;
  int center_fill = ImageUtils.PAINT_STRETCH;

  boolean tile;

  Insets insets;

  /**
   * Constructor for the DefaultButton object
   */
  public DefaultButton() {
  }

  /**
   * Constructor for the DefaultButton object
   *
   * @param bitmap        Description of Parameter
   * @param imageWidth    Description of Parameter
   * @param imageHeight   Description of Parameter
   * @param topHeight     Description of Parameter
   * @param rightWidth    Description of Parameter
   * @param bottomHeight  Description of Parameter
   * @param leftWidth     Description of Parameter
   */
  public DefaultButton(Image bitmap, int imageWidth, int imageHeight,
      int topHeight, int rightWidth, int bottomHeight, int leftWidth) {
    this(bitmap, imageWidth, imageHeight,
        topHeight, rightWidth, bottomHeight, leftWidth, false);
  }

  /**
   * Constructor for the DefaultButton object
   *
   * @param top          Description of Parameter
   * @param bottom       Description of Parameter
   * @param left         Description of Parameter
   * @param right        Description of Parameter
   * @param topLeft      Description of Parameter
   * @param topRight     Description of Parameter
   * @param bottomLeft   Description of Parameter
   * @param bottomRight  Description of Parameter
   */
  public DefaultButton(Image top, Image bottom, Image left, Image right,
      Image topLeft, Image topRight, Image bottomLeft, Image bottomRight) {
    this.top = top;
    this.bottom = bottom;
    this.left = left;
    this.right = right;
    this.topleft = topLeft;
    this.bottomleft = bottomLeft;
    this.topright = topRight;
    this.bottomright = bottomRight;

    this.topHeight = top != null ? top.getHeight(null) : (topleft != null ? topleft.getHeight(null) : 0);
    this.rightWidth = right != null ? right.getWidth(null) : 0;
    this.bottomHeight = bottom != null ? bottom.getHeight(null) : 0;
    this.leftWidth = left != null ? left.getWidth(null) : 0;

    insets = new Insets(top != null ? topHeight : 0, rightWidth, bottomHeight, leftWidth);
  }

  /**
   * Constructor for the DefaultButton object
   *
   * @param bitmap        Description of Parameter
   * @param imageWidth    Description of Parameter
   * @param imageHeight   Description of Parameter
   * @param topHeight     Description of Parameter
   * @param rightWidth    Description of Parameter
   * @param bottomHeight  Description of Parameter
   * @param leftWidth     Description of Parameter
   * @param tile          Description of Parameter
   */
  public DefaultButton(Image bitmap, int imageWidth, int imageHeight,
      int topHeight, int rightWidth, int bottomHeight, int leftWidth, boolean tile) {
    this.topHeight = topHeight;
    this.rightWidth = rightWidth;
    this.bottomHeight = bottomHeight;
    this.leftWidth = leftWidth;
    this.imageWidth = imageWidth;
    this.imageHeight = imageHeight;
    this.tile = tile;

    insets = new Insets(topHeight, rightWidth, bottomHeight, leftWidth);

    // corners
    topleft = ImageUtils.grab(bitmap, 0, 0,
        leftWidth,
        topHeight);

    topright = ImageUtils.grab(bitmap,
        imageWidth - rightWidth, 0,
        rightWidth,
        topHeight);
    bottomleft = ImageUtils.grab(bitmap,
        0,
        imageHeight - bottomHeight,
        leftWidth,
        bottomHeight);
    bottomright = ImageUtils.grab(bitmap,
        imageWidth - rightWidth,
        imageHeight - bottomHeight,
        rightWidth,
        bottomHeight);
    // borders
    top = ImageUtils.grab(bitmap,
        leftWidth, 0,
        imageWidth - leftWidth - rightWidth,
        topHeight);
    if (rightWidth > 0) {
      right = ImageUtils.grab(bitmap,
          imageWidth - rightWidth, topHeight,
          rightWidth,
          imageHeight - topHeight - bottomHeight);
    }
    if (bottomHeight > 0) {
      bottom = ImageUtils.grab(bitmap,
          leftWidth, imageHeight - bottomHeight,
          imageWidth - leftWidth - rightWidth,
          bottomHeight);
    }
    if (leftWidth > 0) {
      left = ImageUtils.grab(bitmap,
          0,
          topHeight,
          leftWidth,
          imageHeight - topHeight - bottomHeight);
    }
    // center
    center = ImageUtils.grab(bitmap,
        leftWidth,
        topHeight,
        imageWidth - leftWidth - rightWidth,
        imageHeight - topHeight - bottomHeight);
  }

  /**
   * Sets the CenterFill attribute of the DefaultButton object
   *
   * @param mode  The new CenterFill value
   */
  public void setCenterFill(int mode) {
    center_fill = mode;
  }

  /**
   * Gets the Disabled attribute of the DefaultButton object
   *
   * @return   The Disabled value
   */
  public DefaultButton getDisabled() {
    DefaultButton b = new DefaultButton();
    b.topleft = ImageUtils.getDisabledImage(topleft);
    b.topright = ImageUtils.getDisabledImage(topright);
    b.bottomleft = ImageUtils.getDisabledImage(bottomleft);
    b.bottomright = ImageUtils.getDisabledImage(bottomright);
    b.top = ImageUtils.getDisabledImage(top);
    b.right = ImageUtils.getDisabledImage(right);
    b.bottom = ImageUtils.getDisabledImage(bottom);
    b.left = ImageUtils.getDisabledImage(left);
    b.center = ImageUtils.getDisabledImage(center);
    b.topHeight = topHeight;
    b.bottomHeight = bottomHeight;
    b.leftWidth = leftWidth;
    b.rightWidth = rightWidth;
    b.imageWidth = imageWidth;
    b.imageHeight = imageHeight;
    b.top_fill = top_fill;
    b.right_fill = right_fill;
    b.bottom_fill = bottom_fill;
    b.left_fill = left_fill;
    b.center_fill = center_fill;
    b.tile = tile;
    b.insets = insets;
    return b;
  }

  /**
   * Gets the TopToBottom attribute of the DefaultButton object
   *
   * @return   The TopToBottom value
   */
  public DefaultButton getTopToBottom() {
    DefaultButton b = new DefaultButton();
    b.top = ImageUtils.flipHorizontally(bottom);
    b.topHeight = bottomHeight;
    b.top_fill = bottom_fill;

    b.topleft = ImageUtils.flipHorizontally(bottomleft);

    b.bottomleft = ImageUtils.flipHorizontally(topleft);

    b.topright = ImageUtils.flipHorizontally(bottomright);

    b.bottomright = ImageUtils.flipHorizontally(topright);

    b.bottom = ImageUtils.flipHorizontally(top);
    b.bottomHeight = topHeight;
    b.bottom_fill = top_fill;

    b.left = ImageUtils.flipHorizontally(left);
    b.leftWidth = leftWidth;
    b.left_fill = left_fill;

    b.right = ImageUtils.flipHorizontally(right);
    b.rightWidth = rightWidth;
    b.right_fill = right_fill;

    b.center = ImageUtils.flipHorizontally(center);
    b.center_fill = center_fill;

    b.imageWidth = imageWidth;
    b.imageHeight = imageHeight;
    b.tile = tile;
    b.insets = new Insets(insets.bottom, insets.left, insets.top, insets.right);
    return b;
  }

  /**
   * Gets the MinimumSize attribute of the DefaultButton object
   *
   * @return   The MinimumSize value
   */
  public Dimension getMinimumSize() {
    return new Dimension(imageWidth, imageHeight);
  }

  /**
   * Gets the PreferredSize attribute of the DefaultButton object
   *
   * @return   The PreferredSize value
   */
  public Dimension getPreferredSize() {
    return getMinimumSize();
  }

  /**
   * Gets the Width attribute of the DefaultButton object
   *
   * @return   The Width value
   */
  public int getWidth() {
    return imageWidth;
  }

  /**
   * Gets the IconWidth attribute of the DefaultButton object
   *
   * @return   The IconWidth value
   */
  public int getIconWidth() {
    return getWidth();
  }

  /**
   * Gets the Height attribute of the DefaultButton object
   *
   * @return   The Height value
   */
  public int getHeight() {
    return imageHeight;
  }

  /**
   * Gets the IconHeight attribute of the DefaultButton object
   *
   * @return   The IconHeight value
   */
  public int getIconHeight() {
    return getHeight();
  }

  /**
   * Gets the Insets attribute of the DefaultButton object
   *
   * @return   The Insets value
   */
  public Insets getInsets() {
    return insets;
  }

  // Implements BORDER

  /**
   * Gets the BorderInsets attribute of the DefaultButton object
   *
   * @param c  Description of Parameter
   * @return   The BorderInsets value
   */
  public Insets getBorderInsets(Component c) {
    return insets;
  }

  /**
   * Gets the BorderOpaque attribute of the DefaultButton object
   *
   * @return   The BorderOpaque value
   */
  public boolean isBorderOpaque() {
    return false;
  }

  /**
   * Description of the Method
   *
   * @return   Description of the Returned Value
   */
  public DefaultButton rotateCounterClockWise() {
    DefaultButton b = new DefaultButton();
    b.top = ImageUtils.rotateLeft(right);
    b.topHeight = rightWidth;
    b.top_fill = right_fill;

    b.topleft = ImageUtils.rotateLeft(topright);

    b.bottomleft = ImageUtils.rotateLeft(topleft);

    b.topright = ImageUtils.rotateLeft(bottomright);

    b.bottomright = ImageUtils.rotateLeft(bottomleft);

    b.bottom = ImageUtils.rotateLeft(left);
    b.bottomHeight = leftWidth;
    b.bottom_fill = left_fill;

    b.left = ImageUtils.rotateLeft(top);
    b.leftWidth = topHeight;
    b.left_fill = top_fill;

    b.right = ImageUtils.rotateLeft(bottom);
    b.rightWidth = bottomHeight;
    b.right_fill = bottom_fill;

    b.center = ImageUtils.rotateLeft(center);
    b.center_fill = center_fill;

    b.imageWidth = imageHeight;
    b.imageHeight = imageWidth;
    b.tile = tile;
    b.insets = new Insets(insets.left, insets.top, insets.right, insets.bottom);
    return b;
  }

  /**
   * Description of the Method
   *
   * @return   Description of the Returned Value
   */
  public String toString() {
    return "DefaultButton(" + getWidth() + "x" + getHeight() + ",center_fill=" + center_fill + ")";
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
    if (center != null) {
      g.drawImage(center, x, y, c);
    }
    //	paint(g, x, y, c);
  }

  /**
   * Description of the Method
   *
   * @param g  Description of Parameter
   * @param b  Description of Parameter
   */
  public void paint(Graphics g, Component b) {
    paint(g, 0, 0, b);
  }

  /**
   * Description of the Method
   *
   * @param g  Description of Parameter
   * @param x  Description of Parameter
   * @param y  Description of Parameter
   * @param b  Description of Parameter
   */
  public void paint(Graphics g, int x, int y, Component b) {
    paint(g, x, y, ((JComponent) b).getWidth(), ((JComponent) b).getHeight(), b);
  }

  /**
   * Description of the Method
   *
   * @param b       Description of Parameter
   * @param g       Description of Parameter
   * @param x       Description of Parameter
   * @param y       Description of Parameter
   * @param width   Description of Parameter
   * @param height  Description of Parameter
   */
  public void paintBorder(Component b, Graphics g, int x, int y, int width, int height) {

    // PENDING(fred): borders and center should be drawn as tiles!!!
    // borders
    // center
    ImageUtils.paint(b, g, top,
        x + (topleft != null ? leftWidth : 0), y,
        width - (topleft != null ? leftWidth : 0) - (topright != null ? rightWidth : 0), topHeight,
        false,
        top_fill);

    ImageUtils.paint(b, g, right,
        x + width - rightWidth, y + topHeight,
        rightWidth, height - topHeight - bottomHeight,
        false,
        right_fill);

    ImageUtils.paint(b, g, bottom,
        x + (topleft != null ? leftWidth : 0), y + height - bottomHeight,
        width - (topleft != null ? leftWidth : 0) - (topright != null ? rightWidth : 0),
        bottomHeight,
        false,
        bottom_fill);

    ImageUtils.paint(b, g, left,
        x, y + topHeight,
        leftWidth, height - topHeight - bottomHeight,
        false,
        left_fill);

    // finally, draw corners
    if (topleft != null) {
      ImageUtils.paint(b, g, topleft, x, y, 0, 0, false, ImageUtils.PAINT_NORMAL);
    }
    if (topright != null) {
      ImageUtils.paint(b, g, topright, x + width - topright.getWidth(null), y, 0, 0, false, ImageUtils.PAINT_NORMAL);
    }
    if (bottomleft != null) {
      ImageUtils.paint(b, g, bottomleft, x, y + height - bottomleft.getHeight(null), 0, 0, false, ImageUtils.PAINT_NORMAL);
    }
    if (bottomright != null) {
      ImageUtils.paint(b, g, bottomright, x + width - bottomright.getWidth(null), y + height - bottomleft.getHeight(null),
          0, 0, false, ImageUtils.PAINT_NORMAL);
    }
  }

  /**
   * Description of the Method
   *
   * @param g       Description of Parameter
   * @param x       Description of Parameter
   * @param y       Description of Parameter
   * @param width   Description of Parameter
   * @param height  Description of Parameter
   * @param b       Description of Parameter
   */
  public void paint(Graphics g, int x, int y, int width, int height, Component b) {
    if (SkinUtils.DEBUG) {
      System.out.println("Painting (" + x + "," + y + "," + width + "x" + height + ") for " + b);
    }
    ImageUtils.paint(b, g, center,
        x + leftWidth, y + topHeight,
        width - leftWidth - rightWidth,
        height - topHeight - bottomHeight,
        false,
        center_fill);

    paintBorder(b, g, x, y, width, height);

    if (SkinUtils.DEBUG) {
      g.setColor(Color.black);
      g.drawRect(x + leftWidth, y + topHeight,
          width - leftWidth - rightWidth,
          height - topHeight - bottomHeight);

      g.drawLine(x + leftWidth, y, x + leftWidth, y + height);
      g.drawLine(x + width - rightWidth, y, x + width - rightWidth, y + height);
      g.drawLine(x, y + topHeight, x + width, y + topHeight);
      g.drawLine(x, y + height - bottomHeight, x + width, y + height - bottomHeight);
    }
  }

}
