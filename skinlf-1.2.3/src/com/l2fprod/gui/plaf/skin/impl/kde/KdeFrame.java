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

import java.net.URL;
import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.BorderUIResource;

import com.l2fprod.util.IniFile;
import com.l2fprod.util.ImageUtils;
import com.l2fprod.gui.plaf.skin.*;
import com.l2fprod.gui.plaf.skin.impl.*;

/**
 * @author    $Author: l2fprod $
 * @created   27 avril 2002
 * @version   $Revision: 1.11 $, $Date: 2002/06/13 16:26:22 $
 */
class KdeFrame extends AbstractSkinFrame {

  DefaultButton topSelected, topUnselected;
  int topHeight = 17;
  java.util.Vector buttonList;
  int textShiftLeft = SkinTitlePane.ICON_OFFSET, textShiftRight = 0;
  int textAlignment = LEFT;

  boolean pixmapUnderTitle = false;
  boolean titleFrameShaded = false;

  Border border;

  final static String[] TEXT_ALIGNMENTS = {
      "left", "middle", "right"
      };

  final static int LEFT = 0;
  final static int MIDDLE = 1;
  final static int RIGHT = 2;

  /**
   * Constructor for the KdeFrame object
   *
   * @param ini            Description of Parameter
   * @param skinURL        Description of Parameter
   * @exception Exception  Description of Exception
   */
  public KdeFrame(IniFile ini, URL skinURL) throws Exception {

    String path = ini.getKeyValue("Window Titlebar", "TitlebarPixmapActive");
    if (path != null) {
      Image image = SkinUtils.loadImage(new URL(skinURL, path));
      topSelected =
          new DefaultButton(image,
          image.getWidth(null),
          image.getHeight(null),
          ini.getKeyIntValue("Window Titlebar", "TitlebarPixmapActiveTop"),
          ini.getKeyIntValue("Window Titlebar", "TitlebarPixmapActiveRight"),
          ini.getKeyIntValue("Window Titlebar", "TitlebarPixmapActiveBottom"),
          ini.getKeyIntValue("Window Titlebar", "TitlebarPixmapActiveLeft"));
      topHeight = topSelected.getHeight();
    }

    path = ini.getKeyValue("Window Titlebar", "TitlebarPixmapInactive");
    if (path != null) {
      Image image = SkinUtils.loadImage(new URL(skinURL, path));
      topUnselected =
          new DefaultButton(image,
          image.getWidth(null),
          image.getHeight(null),
          ini.getKeyIntValue("Window Titlebar", "TitlebarPixmapActiveTop"),
          ini.getKeyIntValue("Window Titlebar", "TitlebarPixmapActiveRight"),
          ini.getKeyIntValue("Window Titlebar", "TitlebarPixmapActiveBottom"),
          ini.getKeyIntValue("Window Titlebar", "TitlebarPixmapActiveLeft"));
      //	    topUnselected = SkinUtils.loadImage(new URL(skinURL, path));
      topHeight = Math.max(topHeight, topUnselected.getHeight());
    }

    pixmapUnderTitle = "yes".equals(ini.getKeyValue("Window Titlebar", "PixmapUnderTitleText"));
    titleFrameShaded = "yes".equals(ini.getKeyValue("Window Titlebar", "TitleFrameShaded"));

    String textAlignmentValue = ini.getKeyValue("Window Titlebar", "TitleAlignment");
    if (textAlignmentValue != null) {
      textAlignmentValue = textAlignmentValue.toLowerCase();
      for (int i = 0, c = TEXT_ALIGNMENTS.length; i < c; i++) {
        if (TEXT_ALIGNMENTS[i].equals(textAlignmentValue)) {
          textAlignment = i;
          break;
        }
      }
    }
    buttonList = new java.util.Vector();

    String letters = "ABCDEF";
    if (ini.getSection("Window Button Layout") == null) {
      ini.addSection("Window Button Layout");
      ini.setKeyValue("Window Button Layout", "ButtonA", "Off");
      ini.setKeyValue("Window Button Layout", "ButtonB", "Off");
      ini.setKeyValue("Window Button Layout", "ButtonC", "Off");
      ini.setKeyValue("Window Button Layout", "ButtonD", "Minimize");
      ini.setKeyValue("Window Button Layout", "ButtonE", "Maximize");
      ini.setKeyValue("Window Button Layout", "ButtonF", "Close");
    }

    for (int i = 0, c = letters.length(); i < c; i++) {
      String button = ini.getKeyValue("Window Button Layout", "Button" + letters.charAt(i));
      if ((button != null) && ("Off".equalsIgnoreCase(button) == false)) {
        FrameButton fb = new FrameButton(ini, skinURL, button);
        fb.setAlign((i < c / 2) ? SkinTitlePane.ALIGN_TOP_LEFT :
            SkinTitlePane.ALIGN_TOP_RIGHT);
        if (fb.icon != null) {
          if (i < c / 2) {
            textShiftLeft += fb.icon.getIconWidth();
          }
          else {
            textShiftRight += fb.icon.getIconWidth();
          }
          topHeight = Math.max(topHeight, fb.icon.getIconHeight());
        }
        buttonList.addElement(fb);
      }
    }
    textShiftLeft += 4;
    textShiftRight += 4;

    if ((ini.getSection("Window Border") != null) &&
        (ini.getSection("Window Border").size() > 0)) {
      border = new BorderUIResource(new KdeFrameBorder(ini, skinURL));
    }
    else {
      //border = UIManager.getBorder("InternalFrame.border");
    }
  }

  /**
   * Gets the TopPreferredSize attribute of the KdeFrame object
   *
   * @return   The TopPreferredSize value
   */
  public Dimension getTopPreferredSize() {
    return new Dimension(50, topHeight);
  }

  /**
   * Gets the WindowButtons attribute of the KdeFrame object
   *
   * @param align  Description of Parameter
   * @return       The WindowButtons value
   */
  public SkinWindowButton[] getWindowButtons(int align) {
    java.util.Vector buttons = new java.util.Vector();
    for (int i = 0, c = buttonList.size(); i < c; i++) {
      FrameButton newB = (FrameButton) buttonList.elementAt(i);
      if (newB.getAlign() == align) {
        buttons.addElement(newB.createButton());
      }
    }
    SkinWindowButton[] results = new SkinWindowButton[buttons.size()];
    buttons.copyInto(results);
    return results;
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
    if (border != null) {
      c.setBorder(border);
      c.setOpaque(false);
      return true;
    }
    else {
      return false;
    }
  }

  /**
   * Description of the Method
   *
   * @param g           Description of Parameter
   * @param c           Description of Parameter
   * @param isSelected  Description of Parameter
   * @param title       Description of Parameter
   * @return            Description of the Returned Value
   */
  public boolean paintTop(Graphics g, Component c, boolean isSelected, String title) {
    if (topSelected != null && topUnselected != null) {
      if (isSelected) {
        topSelected.paint(g, 0, 0, c);
      }
      else {
        topUnselected.paint(g, 0, 0, c);
      }
    }
    else {
      // fill a rectangle
      Color oldColor = g.getColor();
      if (isSelected) {
        g.setColor(UIManager.getColor("InternalFrame.activeTitleBackground"));
      }
      else {
        g.setColor(UIManager.getColor("InternalFrame.inactiveTitleBackground"));
      }
      g.fillRect(0, 0, ((JComponent) c).getWidth(), ((JComponent) c).getHeight());
      g.setColor(oldColor);
    }

    if (title != null) {
      FontMetrics fm = g.getFontMetrics();
      int fmHeight = fm.getHeight() - fm.getLeading();
      int baseline = (topHeight - fmHeight) / 2 + fm.getAscent() + fm.getLeading();
      int width = fm.stringWidth(title);

      int x = 0;

      switch (textAlignment) {
        case LEFT:
          x = textShiftLeft;
          break;
        case MIDDLE:
          x = (((JComponent) c).getWidth() - textShiftLeft - textShiftRight) / 2 + textShiftLeft - width / 2;
          break;
        case RIGHT:
          x = ((JComponent) c).getWidth() - width - textShiftRight;
          break;
      }

      if (pixmapUnderTitle == false) {
        Color oldColor = g.getColor();
        if (isSelected) {
          g.setColor(UIManager.getColor("InternalFrame.activeTitleBackground"));
        }
        else {
          g.setColor(UIManager.getColor("InternalFrame.inactiveTitleBackground"));
        }
        g.fillRect(x, 0, width, ((JComponent) c).getHeight());
        g.setColor(oldColor);
      }
      if (isSelected && titleFrameShaded) {
        Color oldColor = g.getColor();
        g.setColor(oldColor.darker().darker());
        g.drawString(title, x + 1, baseline + 1);
        g.setColor(oldColor);
      }
      g.drawString(title, x, baseline);
    }

    return true;
  }

  /**
   * Description of the Class
   *
   * @author    fred
   * @created   27 avril 2002
   */
  class FrameButton {
    ImageIcon icon, downIcon, inactiveIcon;
    int align;
    int action = SkinTitlePane.NO_ACTION;

    /**
     * Constructor for the FrameButton object
     *
     * @param ini            Description of Parameter
     * @param skinURL        Description of Parameter
     * @param command        Description of Parameter
     * @exception Exception  Description of Exception
     */
    FrameButton(IniFile ini, URL skinURL, String command) throws Exception {
      if ("Iconify".equals(command)) {
        command = "Minimize";
      }

      String path = ini.getKeyValue("Window Titlebar", command + "Button");
      if (path != null) {
        icon = new ImageIcon(SkinUtils.loadImage(new URL(skinURL, path)));
        inactiveIcon = icon;
        downIcon = icon;
      }
      path = ini.getKeyValue("Window Titlebar", command + "DownButton");
      if (path != null) {
        downIcon = new ImageIcon(SkinUtils.loadImage(new URL(skinURL, path)));
      }
      path = ini.getKeyValue("Window Titlebar", command + "InactiveButton");
      if (path != null) {
        inactiveIcon = new ImageIcon(SkinUtils.loadImage(new URL(skinURL, path)));
      }

      if ("Maximize".equalsIgnoreCase(command)) {
        action = SkinTitlePane.MAXIMIZE_ACTION;
      }
      else if ("Minimize".equalsIgnoreCase(command)) {
        action = SkinTitlePane.MINIMIZE_ACTION;
      }
      else if ("Close".equalsIgnoreCase(command)) {
        action = SkinTitlePane.CLOSE_ACTION;
      }
    }

    /**
     * Sets the Align attribute of the FrameButton object
     *
     * @param align  The new Align value
     */
    public void setAlign(int align) {
      this.align = align;
    }

    /**
     * Gets the Align attribute of the FrameButton object
     *
     * @return   The Align value
     */
    public int getAlign() {
      return align;
    }

    /**
     * Description of the Method
     *
     * @return   Description of the Returned Value
     */
    public SkinWindowButton createButton() {
      SkinWindowButton button = new SkinWindowButton(-1, (topHeight - icon.getIconHeight()) / 2, align, action);
      if (icon != null) {
        button.setSize(icon.getIconWidth(), icon.getIconHeight());
        button.setNoFocusIcon(inactiveIcon);
        button.setNoFocusRolloverIcon(inactiveIcon);
        button.setIcon(icon);
        button.setRolloverIcon(icon);
        button.setPressedIcon(downIcon);
      }
      return button;
    }

  }

}
