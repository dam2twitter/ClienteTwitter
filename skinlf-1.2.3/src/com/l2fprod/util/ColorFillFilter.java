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
package com.l2fprod.util;

import java.awt.Color;
import java.awt.image.RGBImageFilter;

/**
 * @author    $Author: l2fprod $
 * @created   27 avril 2002
 * @version   $Revision: 1.1 $, $Date: 2002/04/27 11:29:26 $
 */
public class ColorFillFilter extends RGBImageFilter {

  /**
   * Description of the Field
   */
  public int m_Red, m_Green, m_Blue;

  /**
   * Constructor for the ColorFillFilter object
   *
   * @param r  Description of Parameter
   * @param g  Description of Parameter
   * @param b  Description of Parameter
   */
  public ColorFillFilter(int r, int g, int b) {
    m_Red = r;
    m_Green = g;
    m_Blue = b;
    canFilterIndexColorModel = true;
  }

  /**
   * Constructor for the ColorFillFilter object
   *
   * @param color  Description of Parameter
   */
  public ColorFillFilter(Color color) {
    this(100 * color.getRed() / 255,
        100 * color.getGreen() / 255,
        100 * color.getBlue() / 255);
  }

  /**
   * Constructor for the ColorFillFilter object
   */
  public ColorFillFilter() {
    this(100, 100, 100);
  }

  /**
   * Sets the Color attribute of the ColorFillFilter object
   *
   * @param r  The new Color value
   * @param g  The new Color value
   * @param b  The new Color value
   */
  public void setColor(int r, int g, int b) {
    m_Red = r;
    m_Green = g;
    m_Blue = b;
  }

  /**
   * Sets the Red attribute of the ColorFillFilter object
   *
   * @param r  The new Red value
   */
  public void setRed(int r) {
    m_Red = r;
  }

  /**
   * Sets the Green attribute of the ColorFillFilter object
   *
   * @param g  The new Green value
   */
  public void setGreen(int g) {
    m_Green = g;
  }

  /**
   * Sets the Blue attribute of the ColorFillFilter object
   *
   * @param b  The new Blue value
   */
  public void setBlue(int b) {
    m_Blue = b;
  }

  /**
   * Description of the Method
   *
   * @param x    Description of Parameter
   * @param y    Description of Parameter
   * @param rgb  Description of Parameter
   * @return     Description of the Returned Value
   */
  public int filterRGB(int x, int y, int rgb) {
    if (rgb == 0) {
      return rgb;
    }

    int r = ((rgb & 0x00ff0000) >> 16) * m_Red / 100;
    if (r < 0) {
      r = 0;
    }
    if (r > 255) {
      r = 255;
    }
    int g = ((rgb & 0x0000ff00) >> 8) * m_Green / 100;
    if (g < 0) {
      g = 0;
    }
    if (g > 255) {
      g = 255;
    }
    int b = ((rgb & 0x000000ff)) * m_Blue / 100;
    if (b < 0) {
      b = 0;
    }
    if (b > 255) {
      b = 255;
    }

    return 0xff000000 | (r << 16) | (g << 8) | (b << 0);
  }

}
