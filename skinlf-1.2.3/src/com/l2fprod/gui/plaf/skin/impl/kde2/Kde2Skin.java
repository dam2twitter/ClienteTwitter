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

import java.awt.Toolkit;
import java.awt.AWTEvent;
import java.awt.event.*;
import java.applet.AudioClip;
import java.applet.Applet;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import javax.swing.JWindow;

import com.l2fprod.util.IniFile;
import com.l2fprod.util.LazyTable;
import com.l2fprod.gui.event.SkinWindowEvent;
import com.l2fprod.gui.plaf.skin.*;
import com.l2fprod.gui.plaf.skin.impl.AbstractSkin;
import com.l2fprod.gui.sound.*;
import com.l2fprod.gui.*;

/**
 * KDE 2 (The K Desktop Environment) Skin Support. <BR>
 * Kde2Skin can be used in conjunction with a kde.themerc file.<BR>
 * You can find skins at:
 * <LI> <A HREF="http://kde.themes.org">kde.themes.org</A> <BR>
 * <BR>
 * Simply extract the skin file in a directory and use:<BR>
 * <BR>
 * <B> <CODE>
 * SkinLookAndFeel.setSkin(new Kde2Skin("c:\downloads\myskin\kde\kde.themerc"));<BR>
 * UIManager.setLookAndFeel("com.l2fprod.gui.plaf.skin.SkinLookAndFeel");
 * </CODE> </B> <BR>
 * <BR>
 * to enable skins in your application ! <BR>
 * <BR>
 * <BR>
 * <BR>
 *
 *
 * @author    $Author: l2fprod $
 * @created   27 avril 2002
 * @version   $Revision: 1.3 $, $Date: 2002/04/27 09:54:57 $
 */
public class Kde2Skin extends AbstractSkin {

  String[] colors;

  static Object[] swingToKde = {
      "desktop", new String[]{"desktop", "background"},
      "activeCaption", new String[]{"activeBackground", "background"},
      "activeCaptionText", new String[]{"activeForeground", "foreground"},
      "activeCaptionBorder", new String[]{""},
      "inactiveCaption", new String[]{"inactiveBackground", "background"},
      "inactiveCaptionText", new String[]{"inactiveForeground", "foreground"},
      "inactiveCaptionBorder", new String[]{""},
      "window", new String[]{"windowBackground", "background"},
      "windowBorder", new String[]{""},
      "windowText", new String[]{"windowForeground", "foreground"},
      "menu", new String[]{"background"},
      "menuPressedItemB", new String[]{"selectBackground"},
      "menuPressedItemF", new String[]{"selectForeground"},
      "menuText", new String[]{"foreground"},
      "text", new String[]{"background"},
      "textText", new String[]{"foreground"},
      "textHighlight", new String[]{"selectBackground"},
      "textHighlightText", new String[]{"selectForeground"},
      "textInactiveText", new String[]{""},
      "control", new String[]{"background"},
      "controlText", new String[]{"foreground"},
      "controlHighlight", new String[]{""},
      "controlLtHighlight", new String[]{""},
      "controlShadow", new String[]{""},
      "controlDkShadow", new String[]{""},
      "scrollbar", new String[]{""},
      "info", new String[]{""},
      "infoText", new String[]{""},
      };

  /**
   * Construct a new KDE skin with the given filename
   *
   * @param filename       path to a kde (themerc) skin file
   * @exception Exception  Description of Exception
   */
  public Kde2Skin(String filename) throws Exception {
    this(SkinUtils.toURL(new File(filename)));
  }

  /**
   * Constructor for the Kde2Skin object
   *
   * @param skinURL        Description of Parameter
   * @exception Exception  Description of Exception
   */
  public Kde2Skin(URL skinURL) throws Exception {
    IniFile ini = new IniFile(skinURL);
    personality = new Kde2Personality(ini, skinURL);
    button = new Kde2Button(ini, skinURL);
    tab = new Kde2Tab(ini, skinURL);
    scrollbar = new Kde2Scrollbar(ini, skinURL);
    progress = new Kde2Progress(ini, skinURL);

    java.util.Vector colorList = new java.util.Vector();
    for (int i = 0, c = swingToKde.length / 2; i < swingToKde.length; i = i + 2) {
      // "swingcolor", { "c1", "c2" }
      String[] locals = (String[]) swingToKde[i + 1];
      if (locals != null && locals.length > 0) {
        for (int j = 0, d = locals.length; j < d; j++) {
          if (ini.getKeyValue("General", locals[j]) != null) {
            colorList.addElement(swingToKde[i]);
            colorList.addElement(SkinUtils.decodeColor(ini.getKeyValue("General", locals[j])));
            break;
          }
        }
      }
    }
    colors = new String[colorList.size()];
    colorList.copyInto(colors);
  }

  /**
   * Gets the Colors attribute of the Kde2Skin object
   *
   * @return   The Colors value
   */
  public String[] getColors() {
    return colors;
  }

}
