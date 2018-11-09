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

import java.util.Vector;

/**
 * @author    $Author: l2fprod $
 * @created   27 avril 2002
 * @version   $Revision: 1.4 $, $Date: 2002/04/27 11:33:58 $
 */
public class GtkClass {

  /**
   * Description of the Field
   */
  public String name;

  /**
   * Description of the Field
   */
  public Vector styles;

  /**
   * Constructor for the GtkClass object
   */
  public GtkClass() {
    styles = new Vector();
  }

  /**
   * Sets the Name attribute of the GtkClass object
   *
   * @param s  The new Name value
   */
  public void setName(String s) {
    name = s;
  }

  /**
   * Gets the Style attribute of the GtkClass object
   *
   * @return   The Style value
   */
  public GtkStyle getStyle() {
    return (GtkStyle) styles.elementAt(0);
  }

  /**
   * Gets the Styles attribute of the GtkClass object
   *
   * @return   The Styles value
   */
  public GtkStyle[] getStyles() {
    GtkStyle[] arrayStyles = new GtkStyle[styles.size()];
    styles.copyInto(arrayStyles);
    return arrayStyles;
  }

  /**
   * Adds a feature to the StyleName attribute of the GtkClass object
   *
   * @param stylename  The feature to be added to the StyleName attribute
   */
  public void addStyleName(String stylename) {
    if (styles.contains(stylename) == false) {
      styles.addElement(stylename);
    }
  }

}
