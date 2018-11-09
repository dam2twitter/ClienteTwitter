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

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.awt.Component;
import java.awt.Image;
import java.awt.image.MemoryImageSource;

import com.l2fprod.util.ImageUtils;
import com.l2fprod.gui.plaf.skin.impl.gtk.parser.*;
import com.l2fprod.gui.plaf.skin.*;

/**
 * @author    $Author: l2fprod $
 * @created   27 avril 2002
 * @version   $Revision: 1.10 $, $Date: 2002/04/27 11:33:13 $
 */
class GtkUtils {

  /**
   * Description of the Field
   */
  public final static boolean DEBUG = "true".equals(com.l2fprod.util.AccessUtils.getProperty("debug.gtk"));

  /**
   * Description of the Method
   *
   * @param parser         Description of Parameter
   * @param style          Description of Parameter
   * @param keys           Description of Parameter
   * @param values         Description of Parameter
   * @return               Description of the Returned Value
   * @exception Exception  Description of Exception
   */
  public static DefaultButton newButton(GtkParser parser,
      String style, String[] keys, String[] values) throws Exception {
    return newButton(parser, style, keys, values, false, false);
  }

  /**
   * Description of the Method
   *
   * @param parser         Description of Parameter
   * @param style          Description of Parameter
   * @param keys           Description of Parameter
   * @param values         Description of Parameter
   * @param useOverlay     Description of Parameter
   * @return               Description of the Returned Value
   * @exception Exception  Description of Exception
   */
  public static DefaultButton newButton(GtkParser parser,
      String style, String[] keys, String[] values,
      boolean useOverlay) throws Exception {
    return newButton(parser, style, keys, values, useOverlay, false);
  }

  /**
   * Description of the Method
   *
   * @param parser         Description of Parameter
   * @param style          Description of Parameter
   * @param keys           Description of Parameter
   * @param values         Description of Parameter
   * @param useOverlay     Description of Parameter
   * @param exactMatch     Description of Parameter
   * @return               Description of the Returned Value
   * @exception Exception  Description of Exception
   */
  public static DefaultButton newButton(GtkParser parser,
      String style, String[] keys, String[] values,
      boolean useOverlay, boolean exactMatch) throws Exception {
    if (DEBUG) {
      System.out.println("Looking in " + style + " for ");
      for (int i = 0, c = keys.length; i < c; i++) {
        System.out.println("\t" + keys[i] + " = " + values[i]);
      }
    }

    try {
      DefaultButton button = null;
      // parser.getClass(style).getStyles
      GtkStyle[] styles;
      if (parser.getClass(style) == null) {
        styles = new GtkStyle[]{parser.getStyle(style)};
      }
      else {
        if (DEBUG) {
          System.out.println("style " + style + " exists as class");
        }
        styles = parser.getClass(style).getStyles();
      }

      if (DEBUG) {
        System.out.println("Style " + style + " (count:" + styles.length + ")");
      }

      for (int i = 0, c = styles.length; i < c; i++) {
        GtkStyle gtkstyle = styles[i];

        if (DEBUG) {
          System.out.println("\tSub: " + styles[i]);
        }

        if (gtkstyle != null) {
          GtkImage image = gtkstyle.getEngine().findImage(keys, values, exactMatch);
          if (DEBUG) {
            System.out.println("\t\tImage is " + image);
          }
          if (image != null) {
            Image bitmap = null;
            GtkBorder border = (GtkBorder) image.getProperty(useOverlay ? "overlay_border" : "border");
            if (useOverlay && border == null) {
              border = (GtkBorder) image.getProperty("border");
            }
            if (useOverlay == false && border == null) {
              border = (GtkBorder) image.getProperty("overlay_border");
            }

            if (border == null) {
              border = new GtkBorder(0, 0, 0, 0);
            }

            bitmap = image.getImage(parser.getDirectory(), useOverlay ? "overlay_file" : "file");

            if (useOverlay && bitmap == null) {
              bitmap = image.getImage(parser.getDirectory(), "file");
            }
            if (useOverlay == false && bitmap == null) {
              bitmap = image.getImage(parser.getDirectory(), "overlay_file");
            }

            button = new DefaultButton(bitmap,
                bitmap.getWidth(ImageUtils.producer),
                bitmap.getHeight(ImageUtils.producer),
                border.top, border.right, border.bottom, border.left);
            button.setCenterFill("TRUE".equals(image.getProperty(useOverlay ? "overlay_stretch" : "stretch")) ?
                ImageUtils.PAINT_STRETCH : ImageUtils.PAINT_TILE);
            break;
          }
        }
      }

      // if the button is still null and style != default
      // try more general style, this can give unpredictable result,
      // keys must be sorted by importance
      if (button == null && (!"default".equals(style))) {
        button = newButton(parser, "default", keys, values);

        int length = keys.length;
        while ((length > 0) && (button == null)) {
          length--;
          String[] subkeys = new String[length];
          System.arraycopy(keys, 0, subkeys, 0, length);
          button = newButton(parser, "default", subkeys, values);
        }

        // if the button is still null, our latest try is exactMatch in default
        if (button == null) {
          if (DEBUG) {
            System.out.println("defaulting to exactMatch");
          }
          button = newButton(parser, "default", keys, values, false, true);
        }
      }

      if (DEBUG && button == null) {
        System.out.println("button not found for " + style);
        System.out.println("image {");
        for (int i = 0, c = keys.length; i < c; i++) {
          System.out.println("\t" + keys[i] + " = " + values[i]);
        }
        System.out.println("}");
        Thread.dumpStack();
      }

      return button;
    } catch (java.io.FileNotFoundException e) {
      // can be thrown if the image specified in gtkrc points to a wrong file
      return null;
    } catch (RuntimeException e) {
      return null;
    }
  }

}
