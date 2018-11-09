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
package com.l2fprod.gui.plaf.skin.impl.kde;

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
 * KDE (The K Desktop Environment) Skin Support. <BR>
 * KdeSkin can be used in conjunction with a kde.themerc file.<BR>
 * You can find skins at:
 * <LI> <A HREF="http://kde.themes.org">kde.themes.org</A> <BR>
 * <BR>
 * Simply extract the skin file in a directory and use:<BR>
 * <BR>
 * <B> <CODE>
 * SkinLookAndFeel.setSkin(new KdeSkin("c:\downloads\myskin\kde\kde.themerc"));<BR>
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
 * @version   $Revision: 1.7 $, $Date: 2002/04/27 11:37:46 $
 */
public class KdeSkin extends AbstractSkin {

  SkinPersonality personality;
  SkinButton button;
  SkinFrame frame;
  SkinTab tab;
  SkinProgress progress;
  SkinScrollbar scrollbar;
  SkinSlider slider;
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
  public KdeSkin(String filename) throws Exception {
    this(SkinUtils.toURL(new File(filename)));
  }

  /**
   * Constructor for the KdeSkin object
   *
   * @param skinURL        Description of Parameter
   * @exception Exception  Description of Exception
   */
  public KdeSkin(URL skinURL) throws Exception {
    this(skinURL, skinURL.openStream());
  }

  /**
   * Constructor for the KdeSkin object
   *
   * @param skinURL        Description of Parameter
   * @param input          Description of Parameter
   * @exception Exception  Description of Exception
   */
  public KdeSkin(URL skinURL, InputStream input) throws Exception {
    IniFile ini = new IniFile(input);

    personality = new KdePersonality(ini, skinURL);
    frame = new KdeFrame(ini, skinURL);

    java.util.Vector colorList = new java.util.Vector();
    for (int i = 0, c = swingToKde.length / 2; i < swingToKde.length; i = i + 2) {
      // "swingcolor", { "c1", "c2" }
      String[] locals = (String[]) swingToKde[i + 1];
      if (locals != null && locals.length > 0) {
        for (int j = 0, d = locals.length; j < d; j++) {
          if (ini.getKeyValue("Colors", locals[j]) != null) {
            colorList.addElement(swingToKde[i]);
            colorList.addElement(SkinUtils.decodeColor(ini.getKeyValue("Colors", locals[j])));
            break;
          }
        }
      }
    }
    colors = new String[colorList.size()];
    colorList.copyInto(colors);

  }

  /**
   * Gets the Personality attribute of the KdeSkin object
   *
   * @return   The Personality value
   */
  public SkinPersonality getPersonality() {
    return personality;
  }

  /**
   * Gets the Button attribute of the KdeSkin object
   *
   * @return   The Button value
   */
  public SkinButton getButton() {
    return button;
  }

  /**
   * Gets the Frame attribute of the KdeSkin object
   *
   * @return   The Frame value
   */
  public SkinFrame getFrame() {
    return frame;
  }

  /**
   * Gets the Tab attribute of the KdeSkin object
   *
   * @return   The Tab value
   */
  public SkinTab getTab() {
    return tab;
  }

  /**
   * Gets the Progress attribute of the KdeSkin object
   *
   * @return   The Progress value
   */
  public SkinProgress getProgress() {
    return progress;
  }

  /**
   * Gets the Colors attribute of the KdeSkin object
   *
   * @return   The Colors value
   */
  public String[] getColors() {
    return colors;
  }

  /**
   * Gets the Scrollbar attribute of the KdeSkin object
   *
   * @return   The Scrollbar value
   */
  public SkinScrollbar getScrollbar() {
    return scrollbar;
  }

  /**
   * Gets the Slider attribute of the KdeSkin object
   *
   * @return   The Slider value
   */
  public SkinSlider getSlider() {
    return slider;
  }

}
