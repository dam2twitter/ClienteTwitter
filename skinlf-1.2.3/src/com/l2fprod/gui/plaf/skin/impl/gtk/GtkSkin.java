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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Vector;

import com.l2fprod.gui.plaf.skin.*;
import com.l2fprod.gui.plaf.skin.impl.AbstractSkin;
import com.l2fprod.gui.plaf.skin.impl.gtk.parser.*;

/**
 * GTK (The Gimp Toolkit) Skin Support. <BR>
 * GtkSkin can be used in conjunction with a gtkrc file.<BR>
 * You can find skins at:
 * <LI> <A HREF="http://gtk.themes.org">gtk.themes.org</A> <BR>
 * <BR>
 * Simply extract the skin file in a directory and use:<BR>
 * <BR>
 * <B> <CODE>
 * SkinLookAndFeel.setSkin(new GtkSkin("c:\downloads\myskin\gtk\gtkrc"));<BR>
 * UIManager.setLookAndFeel("com.l2fprod.gui.plaf.skin.SkinLookAndFeel");
 * </CODE> </B> <BR>
 * <BR>
 * to enable skins in your application ! <BR>
 * <BR>
 * <BR>
 * <BR>
 * Created on 28/01/2000 by Frederic Lavigne, fred@L2FProd.com
 *
 * @author    $Author: l2fprod $
 * @created   27 avril 2002
 * @version   $Revision: 1.9 $, $Date: 2002/05/08 15:59:53 $
 */
public class GtkSkin extends AbstractSkin {

  String[] colors;

  static String[] swingToGtk = {
      "desktop", "",
      "activeCaption", "",
      "activeCaptionText", "",
      "activeCaptionBorder", "",
      "inactiveCaption", "",
      "inactiveCaptionText", "",
      "inactiveCaptionBorder", "",
      "window", "window.bg[NORMAL]",
      "windowBorder", "window.bg[NORMAL]",
      "windowText", "window.fg[NORMAL]",
      "menu", "menu.bg[NORMAL]",
      "menuPressedItemB", "bg[ACTIVE]",
      "menuPressedItemF", "fg[ACTIVE]",
      "menuText", "fg[NORMAL]",
      "text", "bg[NORMAL]",
      "textText", "fg[NORMAL]",
      "textHighlight", "bg[SELECTED]",
      "textHighlightText", "fg[SELECTED]",
      "textInactiveText", "fg[INSENSITIVE]",
      "control", "button.bg[NORMAL]",
      "controlText", "button.fg[NORMAL]",
      "controlHighlight", "",
      "controlLtHighlight", "",
      "controlShadow", "",
      "controlDkShadow", "",
      "scrollbar", "",
      "info", "",
      "infoText", "",
      };

  /**
   * Construct a new GtkSkin using the given filename
   *
   * @param filename       path to a gtk skin (gtkrc) file
   * @exception Exception  Description of Exception
   */
  public GtkSkin(String filename) throws Exception {
    this(SkinUtils.toURL(new File(filename)));
  }

  /**
   * Construct a new GtkSkin using the given url
   *
   * @param url            path to a gtk skin (gtkrc) file
   * @exception Exception  Description of Exception
   */
  public GtkSkin(java.net.URL url) throws Exception {
    GtkParser parser = new GtkParser(url);
    parser.buildStructure();

    init(parser);
  }

  /**
   * Constructor for the GtkSkin object
   *
   * @param url            Description of Parameter
   * @param input          Description of Parameter
   * @exception Exception  Description of Exception
   */
  public GtkSkin(java.net.URL url, java.io.InputStream input) throws Exception {
    GtkParser parser = new GtkParser(input);
    parser.setDirectory(url);
    parser.buildStructure();

    init(parser);
  }

  /**
   * Constructor for the GtkSkin object
   *
   * @param parser         Description of Parameter
   * @exception Exception  Description of Exception
   */
  public GtkSkin(GtkParser parser) throws Exception {
    init(parser);
  }

  /**
   * Gets the Personality attribute of the GtkSkin object
   *
   * @return   The Personality value
   */
  public SkinPersonality getPersonality() {
    return personality;
  }

  /**
   * Gets the Button attribute of the GtkSkin object
   *
   * @return   The Button value
   */
  public SkinButton getButton() {
    return button;
  }

  /**
   * Gets the Frame attribute of the GtkSkin object
   *
   * @return   The Frame value
   */
  public SkinFrame getFrame() {
    return null;
  }

  /**
   * Gets the Tab attribute of the GtkSkin object
   *
   * @return   The Tab value
   */
  public SkinTab getTab() {
    return tab;
  }

  /**
   * Gets the Progress attribute of the GtkSkin object
   *
   * @return   The Progress value
   */
  public SkinProgress getProgress() {
    return progress;
  }

  /**
   * Gets the Colors attribute of the GtkSkin object
   *
   * @return   The Colors value
   */
  public String[] getColors() {
    return colors;
  }

  /**
   * Gets the Scrollbar attribute of the GtkSkin object
   *
   * @return   The Scrollbar value
   */
  public SkinScrollbar getScrollbar() {
    return scrollbar;
  }

  /**
   * Gets the SplitPane attribute of the GtkSkin object
   *
   * @return   The SplitPane value
   */
  public SkinSplitPane getSplitPane() {
    return splitpane;
  }

  /**
   * Gets the Slider attribute of the GtkSkin object
   *
   * @return   The Slider value
   */
  public SkinSlider getSlider() {
    return slider;
  }

  /**
   * Description of the Method
   *
   * @param parser         Description of Parameter
   * @exception Exception  Description of Exception
   */
  private void init(GtkParser parser) throws Exception {
    personality = new GtkPersonality(parser);
    button = new GtkButton(parser);
    tab = new GtkTab(parser);
    progress = new GtkProgress(parser);
    scrollbar = new GtkScrollbar(parser);
    splitpane = new GtkSplitPane(parser);
    slider = new GtkSlider(parser);
    separator = new GtkSeparator(parser);

    java.util.Vector colorList = new java.util.Vector();
    for (int i = 0, c = swingToGtk.length / 2; i < swingToGtk.length; i = i + 2) {
      String colorName = swingToGtk[i + 1];
      String color = null;

      if ("".equals(colorName)) {
        continue;
      }

      int index = colorName.indexOf(".");
      if (index != -1) {
        if (parser.getStyle(colorName.substring(0, index)) != null) {
          color = (String) parser.getStyle(colorName.substring(0, index)).getProperty(colorName.substring(index + 1));
        }
        if (color == null) {
          colorName = colorName.substring(index + 1);
        }
      }

      if (color == null) {
        color = (String) parser.getStyle("default").getProperty(colorName);
      }

      if (color != null) {
        colorList.addElement(swingToGtk[i]);
        colorList.addElement(SkinUtils.decodeColor(color));
      }
    }
    colors = new String[colorList.size()];
    colorList.copyInto(colors);
  }

  /**
   * Get the user's current GTK skin location.<br>
   * This could be used on a Linux platform. It looks for the user theme in the
   * ~/.gtkrc user file.
   *
   * @return                              the skin location or null if the
   *      user's current skin can't be found
   * @exception GtkSkinNotFoundException  Description of Exception
   */
  public static String getDefaultSkinLocation() throws GtkSkinNotFoundException {
    String home = System.getProperty("user.home");
    if (home == null) {
      return null;
    }

    String gtkrc = home + File.separator + ".gtkrc";
    File f = new File(gtkrc);
    if (!f.exists()) {
      throw new GtkSkinNotFoundException();
    }

    try {
      BufferedReader br = new BufferedReader(new FileReader(f));
      String s = br.readLine();
      while (s != null) {
        if (s.indexOf("gtkrc") != -1) {
          String s2 = s.substring(9, s.length() - 1);
          File f2 = new File(s2);
          if (f2.exists()) {
            return f2.getCanonicalPath();
          }
        }
        s = br.readLine();
      }
    } catch (Exception e) {
      throw new GtkSkinNotFoundException();
    }
    throw new GtkSkinNotFoundException();
  }

}
