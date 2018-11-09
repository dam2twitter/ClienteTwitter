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
import java.awt.image.*;
import java.io.*;
import java.net.URL;
import java.util.Enumeration;
import javax.swing.*;
import javax.swing.plaf.*;
import com.l2fprod.util.ImageUtils;
import com.l2fprod.util.AccessUtils;

/**
 * @author    $Author: l2fprod $
 * @created   27 avril 2002
 * @version   $Revision: 1.13 $, $Date: 2002/04/27 11:45:08 $
 */
public class SkinUtils {

  /**
   * Description of the Field
   */
  public final static boolean DEBUG = "true".equals(com.l2fprod.util.AccessUtils.getProperty("debug.skinlf"));

  /*
   *  Font f = null;
   *  Font[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
   *  for (int i = 0, c = fonts.length; i < c; i++) {
   *  if (fonts[i].getFontName().equals(name)) {
   *  f = fonts[i].deriveFont(type, size);
   *  break;
   *  }
   *  }
   *  return f;
   *  }
   */
  /**
   * Sets the Font attribute of the SkinUtils class
   *
   * @param f  The new Font value
   */
  public static void setFont(Font f) {
    UIDefaults defs = UIManager.getDefaults();
    for (Enumeration keys = defs.keys(); keys.hasMoreElements(); ) {
      Object o = keys.nextElement();
      if (o instanceof String) {
        String aKey = (String) o;
        if (aKey.endsWith(".font") || aKey.endsWith(".titleFont") || aKey.endsWith(".acceleratorFont")) {
          if (defs.get(aKey) instanceof FontUIResource) {
            UIManager.put(aKey, f);
          }
        }
      }
    }
  }

  /**
   * Gets the Font attribute of the SkinUtils class
   *
   * @param name  Description of Parameter
   * @param type  Description of Parameter
   * @param size  Description of Parameter
   * @return      The Font value
   */
  public static Font getFont(String name, int type, int size) {
    return new Font(name, type, size);
  }

  /**
   * Description of the Method
   *
   * @param filename       Description of Parameter
   * @return               Description of the Returned Value
   * @exception Exception  Description of Exception
   */
  public static Image loadImage(String filename) throws Exception {
    return loadImage(toURL(new File(filename)));
  }

  /**
   * Description of the Method
   *
   * @param url            Description of Parameter
   * @return               Description of the Returned Value
   * @exception Exception  Description of Exception
   */
  public static Image loadImage(URL url) throws Exception {
    Image img = null;
    if (url.toString().toLowerCase().endsWith(".png")) {
      InputStream input = SkinLookAndFeel.getInputStream(url);
      img = PngUtils.loadImage(input);
    }
    else {
      byte[] data = SkinLookAndFeel.getURLContent(url);
      img = Toolkit.getDefaultToolkit().createImage(data);
    }
    return ImageUtils.transparent(img);
  }

  /**
   * Description of the Method
   *
   * @param f                                   Description of Parameter
   * @return                                    Description of the Returned
   *      Value
   * @exception java.net.MalformedURLException  Description of Exception
   */
  public static URL toURL(File f) throws java.net.MalformedURLException {
    String path = f.getAbsolutePath();
    if (File.separatorChar != '/') {
      path = path.replace(File.separatorChar, '/');
    }
    if (!path.startsWith("/")) {
      path = "/" + path;
    }
    if (!path.endsWith("/") && f.isDirectory()) {
      path = path + "/";
    }
    return new URL("file", "", path);
  }

  /**
   * Description of the Method
   *
   * @param container  Description of Parameter
   * @param x          Description of Parameter
   * @param y          Description of Parameter
   * @return           Description of the Returned Value
   */
  public static Component findComponentAt(Container container, int x, int y) {
    if (!container.contains(x, y)) {
      return null;
    }
    int ncomponents = container.getComponentCount();
    Component component[] = container.getComponents();
    for (int i = 0; i < ncomponents; i++) {
      Component comp = component[i];
      if (comp != null) {
        Point p = comp.getLocation();
        if (comp instanceof Container) {
          comp = findComponentAt((Container) comp, x - p.x, y - p.y);
        }
        else {
          comp = comp.locate(x - p.x, y - p.y);
        }
        if (comp != null && comp.isVisible()) {
          return comp;
        }
      }
    }
    return container;
  }

  // color is R,G,B
  // supported format of the string are
  // { r, g, b }
  // r g b
  // r,g,b
  // r.r, g.g, b.b
  /**
   * Description of the Method
   *
   * @param color  Description of Parameter
   * @return       Description of the Returned Value
   */
  public static String decodeColor(String color) {
    if (color.startsWith("#")) {
      return color;
    }
    java.util.StringTokenizer token = new java.util.StringTokenizer(color, ", {}");
    String result = "#";
    String r = token.nextToken();
    String g = token.nextToken();
    String b = token.nextToken();
    if (is0to1color(r) && is0to1color(g) && is0to1color(b)) {
      r = (int) (Float.parseFloat(r) * 255) + "";
      g = (int) (Float.parseFloat(g) * 255) + "";
      b = (int) (Float.parseFloat(b) * 255) + "";
    }
    else {
      r = (int) Float.parseFloat(r) + "";
      g = (int) Float.parseFloat(g) + "";
      b = (int) Float.parseFloat(b) + "";
    }
    result += toHexString(Integer.parseInt(r));
    result += toHexString(Integer.parseInt(g));
    result += toHexString(Integer.parseInt(b));
    return result;
  }

  /**
   * Description of the Method
   *
   * @param composite  Description of Parameter
   * @return           Description of the Returned Value
   */
  static boolean is0to1color(String composite) {
    int index = composite.indexOf(".");
    if (index != -1 && composite.substring(0, index).length() <= 1) {
      return true;
    }
    else {
      return false;
    }
  }

  /**
   * Description of the Method
   *
   * @param i  Description of Parameter
   * @return   Description of the Returned Value
   */
  static String toHexString(int i) {
    if (i == 0) {
      return "00";
    }
    else {
      return Integer.toHexString(i).toUpperCase();
    }
  }

}
