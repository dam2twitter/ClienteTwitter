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
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.JTextComponent;

import com.l2fprod.contrib.nanoxml.*;
import com.l2fprod.gui.plaf.skin.impl.gtk.GtkSkin;
import com.l2fprod.gui.plaf.skin.impl.kde.KdeSkin;
import com.l2fprod.util.*;

/**
 * Skin Look And Feel Main Class. <br>
 * Use this class to set the current skin or to load skins. <br>
 * See <a href="http://www.L2FProd.com/software/skinlf/themepack.html">
 * L2FProd.com website</a> for the complete description of a theme pack.
 *
 * @author    $Author: l2fprod $
 * @version   $Revision: 1.25 $, $Date: 2002/06/11 14:45:59 $
 */
public class SkinLookAndFeel extends BasicLookAndFeel {

  /**
   * Description of the Field
   */
  public final static String VERSION = "1.2.3";

  final static String SKIN_KEY = new String("SkinLookAndFeel.Skin");
  final static String SKIN_LOADER_KEY = new String("SkinLookAndFeel.SkinLoader");

  /**
   * Gets the Name attribute of the SkinLookAndFeel object
   *
   * @return   The Name value
   */
  public String getName() {
    return "SkinLF";
  }

  /**
   * Gets the Description attribute of the SkinLookAndFeel object
   *
   * @return   The Description value
   */
  public String getDescription() {
    return "Skin Look and Feel";
  }

  /**
   * Gets the ID attribute of the SkinLookAndFeel object
   *
   * @return   The ID value
   */
  public String getID() {
    return "SkinLF";
  }

  /**
   * Gets the NativeLookAndFeel attribute of the SkinLookAndFeel object
   *
   * @return   The NativeLookAndFeel value
   */
  public boolean isNativeLookAndFeel() {
    return false;
  }

  /**
   * Gets the SupportedLookAndFeel attribute of the SkinLookAndFeel object
   *
   * @return   The SupportedLookAndFeel value
   */
  public boolean isSupportedLookAndFeel() {
    return true;
  }

  /**
   * Description of the Method
   *
   * @param table  Description of Parameter
   */
  protected void initClassDefaults(UIDefaults table) {
    super.initClassDefaults(table);

    String skinPackageName = "com.l2fprod.gui.plaf.skin.";
    java.util.Vector list = new java.util.Vector();

    if (getSkin().getProgress() != null && getSkin().getProgress().status()) {
      list.addElement("ProgressBarUI");
      list.addElement(skinPackageName + "SkinProgressBarUI");
    }

    if (getSkin().getTab() != null && getSkin().getTab().status()) {
      list.addElement("TabbedPaneUI");
      list.addElement(skinPackageName + "SkinTabbedPaneUI");
    }

    if (getSkin().getFrame() != null && getSkin().getFrame().status()) {
      list.addElement("InternalFrameUI");
      list.addElement(skinPackageName + "SkinInternalFrameUI");
      list.addElement("WindowButtonUI");
      list.addElement(skinPackageName + "SkinWindowButtonUI");
    }

    if (getSkin().getSlider() != null && getSkin().getSlider().status()) {
      list.addElement("SliderUI");
      list.addElement(skinPackageName + "SkinSliderUI");
    }

    if (getSkin().getScrollbar() != null && getSkin().getScrollbar().status()) {
      list.addElement("ScrollBarUI");
      list.addElement(skinPackageName + "SkinScrollBarUI");
    }

    if (getSkin().getButton() != null && getSkin().getButton().status()) {
      list.addElement("ButtonUI");
      list.addElement(skinPackageName + "SkinButtonUI");
      list.addElement("ToggleButtonUI");
      list.addElement(skinPackageName + "SkinToggleButtonUI");
    }

    if (getSkin().getSeparator() != null && getSkin().getSeparator().status()) {
      list.addElement("SeparatorUI");
      list.addElement(skinPackageName + "SkinSeparatorUI");
    }

    Object[] uiDefaults = {
      "CheckBoxUI", skinPackageName + "SkinCheckBoxUI",
      "ComboBoxUI", skinPackageName + "SkinComboBoxUI",
      "CheckBoxMenuItemUI", skinPackageName + "SkinCheckBoxMenuItemUI",
      "MenuItemUI", skinPackageName + "SkinMenuItemUI",
      "MenuUI", skinPackageName + "SkinMenuUI",
      "MenuBarUI", skinPackageName + "SkinMenuBarUI",
      "ToolBarUI", skinPackageName + "SkinToolBarUI",
      //	    "ListUI", skinPackageName + "SkinListUI",
      "PopupMenuUI", skinPackageName + "SkinPopupMenuUI",
      "RadioButtonUI", skinPackageName + "SkinRadioButtonUI",
      "RadioButtonMenuItemUI", skinPackageName + "SkinRadioButtonMenuItemUI",
      "PanelUI", skinPackageName + "SkinPanelUI",
      "DesktopPaneUI", skinPackageName + "SkinDesktopPaneUI",
      "DesktopIconUI", skinPackageName + "SkinDesktopIconUI",
      "TableHeaderUI", skinPackageName + "SkinTableHeaderUI",
      // there is no basic filechooser ui!
      // so we use the filechooser from metal :(
      "FileChooserUI", skinPackageName + "SkinFileChooserUI",
      "TextFieldUI", "javax.swing.plaf.metal.MetalTextFieldUI",
      "SplitPaneUI",
      (Boolean.TRUE.equals(UIManager.get("JSplitPane.alternateUI"))?
      (skinPackageName + "SkinSplitPaneUI"):
      "javax.swing.plaf.basic.BasicSplitPaneUI"),
      "TreeUI", skinPackageName + "SkinTreeUI"
    };
    for (int i = 0; i < uiDefaults.length; i++) {
      list.addElement(uiDefaults[i]);
    }

    String current;
    for (int i = 0, c = list.size(); i < c; i++) {
      try {
        if ((current = (String) list.elementAt(i)).startsWith(skinPackageName)) {
          Class.forName(current);
          list.addElement(current);
          list.addElement(Class.forName(current));
        }
      } catch (Throwable e) {
      }
    }
    current = null;

    Object[] results = new Object[list.size()];
    list.copyInto(results);
    table.putDefaults(results);
  }

  /**
   * Description of the Method
   *
   * @param table  Description of Parameter
   */
  protected void initSystemColorDefaults(UIDefaults table) {
    super.initSystemColorDefaults(table);

    String[] skinColors = getSkin().getColors();

    if (skinColors != null) {
      loadSystemColors(table, skinColors, isNativeLookAndFeel());
    }
    else {
      loadSystemColors(table, new String[0], isNativeLookAndFeel());
    }
  }

  /**
   * Description of the Method
   *
   * @param table  Description of Parameter
   */
  protected void initComponentDefaults(UIDefaults table) {
    super.initComponentDefaults(table);

    loadResourceBundle(table);

    // *** Tree
    Object treeExpandedIcon = SkinTreeUI.ExpandedIcon.createExpandedIcon();
    Object treeCollapsedIcon = SkinTreeUI.CollapsedIcon.createCollapsedIcon();

    Object checkIcon = new SkinCheckBoxIcon();

    Object[] defaults = {
      //  	    // Buttons
      "Button.dashedRectGapX", new Integer(5),
      "Button.dashedRectGapY", new Integer(4),
      "Button.dashedRectGapWidth", new Integer(10),
      "Button.dashedRectGapHeight", new Integer(8),
      "Button.textShiftOffset", new Integer(1),
      "Desktop.background", table.get("desktop"),
      "ToggleButton.textShiftOffset", new Integer(1),
      "CheckBoxMenuItem.checkIcon", checkIcon,
      "RadioButtonMenuItem.checkIcon", checkIcon,
      "SplitPane.dividerSize", new Integer(4),
      "SplitPane.background", table.get("control"),
      "ProgressBar.cellLength", new Integer(7),
      "ProgressBar.cellSpacing", new Integer(2),
      "Tree.expandedIcon", treeExpandedIcon,
      "Tree.collapsedIcon", treeCollapsedIcon,
      "Tree.line", Color.black,
      // horiz lines
      "Tree.hash", Color.black,
      // legs
      "Tree.rowHeight", new Integer(0),
      "Tree.textForeground", table.get("textText"),
      "Tree.textBackground", table.get("window"),
      "FileChooser.lookInLabelMnemonic", new Integer(KeyEvent.VK_I),
      "FileChooser.fileNameLabelMnemonic", new Integer(KeyEvent.VK_N),
      "FileChooser.filesOfTypeLabelMnemonic", new Integer(KeyEvent.VK_T),
      "InternalFrame.minimizeIconBackground", table.get("control"),
      "InternalFrame.resizeIconHighlight", table.get("controlHighlight"),
      "InternalFrame.resizeIconShadow", table.get("controlShadow"),
    };
    table.putDefaults(defaults);

    // bug fixed by Christopher R. Staley [Chris.Staley@itpwebsolutions.com]
    // for JDK1.3 we use InputMap and for JDK1.1,1.2 we use KeyBindings
    try {
      Class.forName("javax.swing.InputMap");
      Class uidefaults = Class.forName("javax.swing.UIDefaults");
      Class[] innerClasses = uidefaults.getClasses();
      java.lang.reflect.Constructor c = null;

      for (int i = 0; i < innerClasses.length; i++) {
        if (innerClasses[i].getName().endsWith("LazyInputMap")) {
          c = innerClasses[i].getConstructor(new Class[]{Object[].class});
          break;
        }
      }

      Object[] fieldInputMap = new Object[]{
        "ctrl C", DefaultEditorKit.copyAction,
        "ctrl V", DefaultEditorKit.pasteAction,
        "ctrl X", DefaultEditorKit.cutAction,
        "COPY", DefaultEditorKit.copyAction,
        "PASTE", DefaultEditorKit.pasteAction,
        "CUT", DefaultEditorKit.cutAction,
        "shift LEFT", DefaultEditorKit.selectionBackwardAction,
        "shift KP_LEFT", DefaultEditorKit.selectionBackwardAction,
        "shift RIGHT", DefaultEditorKit.selectionForwardAction,
        "shift KP_RIGHT", DefaultEditorKit.selectionForwardAction,
        "ctrl LEFT", DefaultEditorKit.previousWordAction,
        "ctrl KP_LEFT", DefaultEditorKit.previousWordAction,
        "ctrl RIGHT", DefaultEditorKit.nextWordAction,
        "ctrl KP_RIGHT", DefaultEditorKit.nextWordAction,
        "ctrl shift LEFT", DefaultEditorKit.selectionPreviousWordAction,
        "ctrl shift KP_LEFT", DefaultEditorKit.selectionPreviousWordAction,
        "ctrl shift RIGHT", DefaultEditorKit.selectionNextWordAction,
        "ctrl shift KP_RIGHT", DefaultEditorKit.selectionNextWordAction,
        "ctrl A", DefaultEditorKit.selectAllAction,
        "HOME", DefaultEditorKit.beginLineAction,
        "END", DefaultEditorKit.endLineAction,
        "shift HOME", DefaultEditorKit.selectionBeginLineAction,
        "shift END", DefaultEditorKit.selectionEndLineAction,
        "typed \010", DefaultEditorKit.deletePrevCharAction,
        "DELETE", DefaultEditorKit.deleteNextCharAction,
        "RIGHT", DefaultEditorKit.forwardAction,
        "LEFT", DefaultEditorKit.backwardAction,
        "KP_RIGHT", DefaultEditorKit.forwardAction,
        "KP_LEFT", DefaultEditorKit.backwardAction,
        "ENTER", JTextField.notifyAction,
        "ctrl BACK_SLASH", "unselect",
        "control shift O", "toggle-componentOrientation"
      };

      Object[] multilineInputMap = new Object[]{
        "ctrl C", DefaultEditorKit.copyAction,
        "ctrl V", DefaultEditorKit.pasteAction,
        "ctrl X", DefaultEditorKit.cutAction,
        "COPY", DefaultEditorKit.copyAction,
        "PASTE", DefaultEditorKit.pasteAction,
        "CUT", DefaultEditorKit.cutAction,
        "shift LEFT", DefaultEditorKit.selectionBackwardAction,
        "shift KP_LEFT", DefaultEditorKit.selectionBackwardAction,
        "shift RIGHT", DefaultEditorKit.selectionForwardAction,
        "shift KP_RIGHT", DefaultEditorKit.selectionForwardAction,
        "ctrl LEFT", DefaultEditorKit.previousWordAction,
        "ctrl KP_LEFT", DefaultEditorKit.previousWordAction,
        "ctrl RIGHT", DefaultEditorKit.nextWordAction,
        "ctrl KP_RIGHT", DefaultEditorKit.nextWordAction,
        "ctrl shift LEFT", DefaultEditorKit.selectionPreviousWordAction,
        "ctrl shift KP_LEFT", DefaultEditorKit.selectionPreviousWordAction,
        "ctrl shift RIGHT", DefaultEditorKit.selectionNextWordAction,
        "ctrl shift KP_RIGHT", DefaultEditorKit.selectionNextWordAction,
        "ctrl A", DefaultEditorKit.selectAllAction,
        "HOME", DefaultEditorKit.beginLineAction,
        "END", DefaultEditorKit.endLineAction,
        "shift HOME", DefaultEditorKit.selectionBeginLineAction,
        "shift END", DefaultEditorKit.selectionEndLineAction,
        "UP", DefaultEditorKit.upAction,
        "KP_UP", DefaultEditorKit.upAction,
        "DOWN", DefaultEditorKit.downAction,
        "KP_DOWN", DefaultEditorKit.downAction,
        "PAGE_UP", DefaultEditorKit.pageUpAction,
        "PAGE_DOWN", DefaultEditorKit.pageDownAction,
        "shift PAGE_UP", "selection-page-up",
        "shift PAGE_DOWN", "selection-page-down",
        "ctrl shift PAGE_UP", "selection-page-left",
        "ctrl shift PAGE_DOWN", "selection-page-right",
        "shift UP", DefaultEditorKit.selectionUpAction,
        "shift KP_UP", DefaultEditorKit.selectionUpAction,
        "shift DOWN", DefaultEditorKit.selectionDownAction,
        "shift KP_DOWN", DefaultEditorKit.selectionDownAction,
        "ENTER", DefaultEditorKit.insertBreakAction,
        "typed \010", DefaultEditorKit.deletePrevCharAction,
        "DELETE", DefaultEditorKit.deleteNextCharAction,
        "RIGHT", DefaultEditorKit.forwardAction,
        "LEFT", DefaultEditorKit.backwardAction,
        "KP_RIGHT", DefaultEditorKit.forwardAction,
        "KP_LEFT", DefaultEditorKit.backwardAction,
        "TAB", DefaultEditorKit.insertTabAction,
        "ctrl BACK_SLASH", "unselect",
        "ctrl HOME", DefaultEditorKit.beginAction,
        "ctrl END", DefaultEditorKit.endAction,
        "ctrl shift HOME", DefaultEditorKit.selectionBeginAction,
        "ctrl shift END", DefaultEditorKit.selectionEndAction,
        "ctrl T", "next-link-action",
        "ctrl shift T", "previous-link-action",
        "ctrl SPACE", "activate-link-action",
        "control shift O", "toggle-componentOrientation"
      };

      table.put("TextField.focusInputMap", c.newInstance(new Object[]{fieldInputMap}));
      table.put("PasswordField.focusInputMap", c.newInstance(new Object[]{fieldInputMap}));
      table.put("TextArea.focusInputMap", c.newInstance(new Object[]{multilineInputMap}));
      table.put("TextPane.focusInputMap", c.newInstance(new Object[]{multilineInputMap}));
      table.put("EditorPane.focusInputMap", c.newInstance(new Object[]{multilineInputMap}));
    } catch (Throwable e) {
      JTextComponent.KeyBinding[] fieldBindings = makeKeyBindings(new Object[]{
        "control C", DefaultEditorKit.copyAction,
        "control V", DefaultEditorKit.pasteAction,
        "control X", DefaultEditorKit.cutAction,
        "COPY", DefaultEditorKit.copyAction,
        "PASTE", DefaultEditorKit.pasteAction,
        "CUT", DefaultEditorKit.cutAction,
        "control INSERT", DefaultEditorKit.copyAction,
        "shift INSERT", DefaultEditorKit.pasteAction,
        "shift DELETE", DefaultEditorKit.cutAction,
        "control A", DefaultEditorKit.selectAllAction,
        "control BACK_SLASH", "unselect",
        "shift LEFT", DefaultEditorKit.selectionBackwardAction,
        "shift RIGHT", DefaultEditorKit.selectionForwardAction,
        "control LEFT", DefaultEditorKit.previousWordAction,
        "control RIGHT", DefaultEditorKit.nextWordAction,
        "control shift LEFT", DefaultEditorKit.selectionPreviousWordAction,
        "control shift RIGHT", DefaultEditorKit.selectionNextWordAction,
        "HOME", DefaultEditorKit.beginLineAction,
        "END", DefaultEditorKit.endLineAction,
        "shift HOME", DefaultEditorKit.selectionBeginLineAction,
        "shift END", DefaultEditorKit.selectionEndLineAction,
        "BACK_SPACE", DefaultEditorKit.deletePrevCharAction,
        "DELETE", DefaultEditorKit.deleteNextCharAction,
        "RIGHT", DefaultEditorKit.forwardAction,
        "LEFT", DefaultEditorKit.backwardAction,
        "KP_RIGHT", DefaultEditorKit.forwardAction,
        "KP_LEFT", DefaultEditorKit.backwardAction,
        "ENTER", JTextField.notifyAction,
        "control shift O", "toggle-componentOrientation"
      });

      JTextComponent.KeyBinding[] multilineBindings = makeKeyBindings(new Object[]{
        "control C", DefaultEditorKit.copyAction,
        "control V", DefaultEditorKit.pasteAction,
        "control X", DefaultEditorKit.cutAction,
        "COPY", DefaultEditorKit.copyAction,
        "PASTE", DefaultEditorKit.pasteAction,
        "CUT", DefaultEditorKit.cutAction,
        "control INSERT", DefaultEditorKit.copyAction,
        "shift INSERT", DefaultEditorKit.pasteAction,
        "shift DELETE", DefaultEditorKit.cutAction,
        "shift LEFT", DefaultEditorKit.selectionBackwardAction,
        "shift RIGHT", DefaultEditorKit.selectionForwardAction,
        "control LEFT", DefaultEditorKit.previousWordAction,
        "control RIGHT", DefaultEditorKit.nextWordAction,
        "control shift LEFT", DefaultEditorKit.selectionPreviousWordAction,
        "control shift RIGHT", DefaultEditorKit.selectionNextWordAction,
        "control A", DefaultEditorKit.selectAllAction,
        "control BACK_SLASH", "unselect",
        "HOME", DefaultEditorKit.beginLineAction,
        "END", DefaultEditorKit.endLineAction,
        "shift HOME", DefaultEditorKit.selectionBeginLineAction,
        "shift END", DefaultEditorKit.selectionEndLineAction,
        "control HOME", DefaultEditorKit.beginAction,
        "control END", DefaultEditorKit.endAction,
        "control shift HOME", DefaultEditorKit.selectionBeginAction,
        "control shift END", DefaultEditorKit.selectionEndAction,
        "UP", DefaultEditorKit.upAction,
        "DOWN", DefaultEditorKit.downAction,
        "BACK_SPACE", DefaultEditorKit.deletePrevCharAction,
        "DELETE", DefaultEditorKit.deleteNextCharAction,
        "RIGHT", DefaultEditorKit.forwardAction,
        "LEFT", DefaultEditorKit.backwardAction,
        "KP_RIGHT", DefaultEditorKit.forwardAction,
        "KP_LEFT", DefaultEditorKit.backwardAction,
        "PAGE_UP", DefaultEditorKit.pageUpAction,
        "PAGE_DOWN", DefaultEditorKit.pageDownAction,
        "shift PAGE_UP", "selection-page-up",
        "shift PAGE_DOWN", "selection-page-down",
        "control shift PAGE_UP", "selection-page-left",
        "control shift PAGE_DOWN", "selection-page-right",
        "shift UP", DefaultEditorKit.selectionUpAction,
        "shift DOWN", DefaultEditorKit.selectionDownAction,
        "ENTER", DefaultEditorKit.insertBreakAction,
        "TAB", DefaultEditorKit.insertTabAction,
        "control T", "next-link-action",
        "control shift T", "previous-link-action",
        "control SPACE", "activate-link-action",
        "control shift O", "toggle-componentOrientation"
      });

      table.put("TextField.keyBindings", fieldBindings);
      table.put("PasswordField.keyBindings", fieldBindings);
      table.put("TextArea.keyBindings", multilineBindings);
      table.put("TextPane.keyBindings", multilineBindings);
      table.put("EditorPane.keyBindings", multilineBindings);
    }
  }

  /**
   * Description of the Method
   *
   * @param table  Description of Parameter
   */
  private void loadResourceBundle(UIDefaults table) {
    ResourceBundle bundle = ResourceBundle.getBundle("com.l2fprod.gui.plaf.skin.resources.skin");
    Enumeration iter = bundle.getKeys();
    while (iter.hasMoreElements()) {
      String key = (String) iter.nextElement();
      table.put(key, bundle.getObject(key));
    }
  }

  /**
   * Set the skin used by the Skin Look And Feel
   *
   * @param skin  a skin
   */
  public static void setSkin(Skin skin) {
    UIManager.put(SKIN_KEY, skin);
  }

  /**
   * Return the current skin. <BR>
   * If the current skin has not yet been set, Skin Look And Feel will attempt
   * to load a themepack from the property skinlf.themepack (set on the command
   * line using -Dskinlf.themepack=<path-to-themepack>
   *
   * , then the user theme pack located in <user.home>/.skinlf/themepack.zip. If
   * this theme pack is not found, an error will be thrown.
   *
   * @return   the current skin
   */
  public static Skin getSkin() {
    Skin s = (Skin) UIManager.get(SKIN_KEY);
    if (s == null) {
      try {
        // first try to load the theme from the skinlf.themepack property
        // if the property is not found, try to load the user look and feel is ~/.skinlf/themepack.zip
        String themepack = System.getProperty("skinlf.themepack");
        if (themepack != null) {
          s = loadThemePack(themepack);
        }
        else {
          s = loadThemePack(System.getProperty("user.home") + File.separator +
                            ".skinlf" + File.separator +
                            "themepack.zip");
        }
        setSkin(s);
      } catch (Throwable th) {
        throw new Error("Skin was null and an error occurs while trying to load the user theme pack <user.home>/.skinlf/themepack.zip. Source exception message is " + th.getMessage());
      }
    }
    return s;
  }

  /**
   * Set the look and feel to SkinLookAndFeel. This method must be called to set
   * the look and feel in applets or JavaWebStart enabled applications
   *
   * @exception javax.swing.UnsupportedLookAndFeelException  if an error occurs
   */
  public static void enable() throws javax.swing.UnsupportedLookAndFeelException {

    SkinLookAndFeel lnf = new SkinLookAndFeel();
    UIManager.setLookAndFeel(lnf);

    UIManager.getLookAndFeelDefaults().put("ClassLoader", lnf.getClass().getClassLoader());
  }

  /**
   * Load a skin from the given filename.<BR>
   * SkinLF will use the filename to guess which theme to instanciate
   *
   * @param filename       the given filename
   * @return               Description of the Returned Value
   * @exception Exception  Description of Exception
   */
  public static Skin loadSkin(String filename) throws Exception {
    return loadSkin(SkinUtils.toURL(new java.io.File(filename)));
  }

  /**
   * Load a skin from the given url.<BR>
   * SkinLF will use the url filename to guess which theme to instanciate
   *
   * @param url            Description of Parameter
   * @return               Description of the Returned Value
   * @exception Exception  Description of Exception
   */
  public static Skin loadSkin(java.net.URL url) throws Exception {
    String filename = url.getFile();

    if (filename.endsWith("gtkrc")) {
      return new GtkSkin(url, getInputStream(url));
    }
    else if (filename.endsWith(".themerc")) {
      return new KdeSkin(url, getInputStream(url));
    }
    else {
      throw new Exception("Unable to load this skin " + url + " (by using filename matching), " +
                          " try an explicit constructor");
    }
  }

  /**
   * Load the default theme pack.<br>
   * Skin Look And Feel will look for the resource file named <code>skinlf-themepack.xml</code>
   * in the user classpath (using <code>SkinLookAndFeel.class.getResource("/skinlf-themepack.xml")</code>
   * ).
   *
   * @return               Description of the Returned Value
   * @exception Exception  Description of Exception
   */
  public static Skin loadDefaultThemePack() throws Exception {
    return loadThemePackDefinition(SkinLookAndFeel.class.getResource("/skinlf-themepack.xml"));
  }

  /**
   * Load a Theme Pack from the given zip file. <br>
   * See <a href="http://www.L2FProd.com/">L2FProd.com website</a> for the
   * complete description of a theme pack.
   *
   * @param filename       the theme pack filename
   * @return               Description of the Returned Value
   * @exception Exception  Description of Exception
   */
  public static Skin loadThemePack(String filename) throws Exception {
    if (filename.startsWith("http://") ||
      filename.startsWith("ftp://") ||
      filename.startsWith("file:/") ||
      filename.startsWith("jar:/")) {
      return loadThemePack(new URL(filename));
    }
    else {
      return loadThemePack(SkinUtils.toURL(new File(filename)));
    }
  }

  /**
   * Load a Theme Pack from the given zip url. <br>
   * See <a href="http://www.L2FProd.com/software/skinlf/themepack.html">
   * L2FProd.com website</a> for the complete description of a theme pack.
   *
   * @param url            the theme pack url
   * @return               Description of the Returned Value
   * @exception Exception  Description of Exception
   * @see                  com.l2fprod.util.ZipResourceLoader
   */
  public static Skin loadThemePack(URL url) throws Exception {
    // create a ZipResourceLoader to read the themepack
    ZipResourceLoader loader = new ZipResourceLoader(url);
    UIManager.put(SKIN_LOADER_KEY, loader);
    Skin skin = loadThemePackDefinition(new URL("http://dummyhostforziploader/skinlf-themepack.xml"));
    UIManager.put(SKIN_LOADER_KEY, null);
    return skin;
  }

  /**
   * Load a Theme Pack from the given theme pack definition. <br>
   * URLs in the definition must be relative
   *
   * @param url            the theme pack definition url
   * @return               Description of the Returned Value
   * @exception Exception  Description of Exception
   */
  public static Skin loadThemePackDefinition(java.net.URL url) throws Exception {
    Skin skin = null;

    XMLElement element = new XMLElement();
    element.parseFromReader(new java.io.InputStreamReader(getInputStream(url)));

    checkRequiredVersion(element.getProperty("REQUIRE"));

    // reset any custom properties that may be set in the skin
    UIManager.put("JDesktopPane.backgroundEnabled", Boolean.FALSE);
    UIManager.put("PopupMenu.animation", Boolean.FALSE);
    UIManager.put("ScrollBar.alternateLayout", Boolean.FALSE);
    UIManager.put("JSplitPane.alternateUI", Boolean.FALSE);

    Enumeration enum = element.enumerateChildren();
    while (enum.hasMoreElements()) {
      element = (XMLElement) enum.nextElement();
      String tagName = element.getTagName().toLowerCase();
      if ("skin".equals(tagName)) {
        skin = buildSkin(url, element);
      }
      else if ("property".equals(tagName)) {
        String type = element.getProperty("TYPE");

        // Take the boolean class if the typeclass is not specified for backward compatibility.
        if (type == null || "".equals(type) ||
          "boolean".equalsIgnoreCase(type) || "java.lang.Boolean".equalsIgnoreCase(type)) {
          UIManager.put(element.getProperty("NAME"), Boolean.valueOf(element.getProperty("VALUE")));
        }
        else if ("int".equalsIgnoreCase(type) || "java.lang.Integer".equalsIgnoreCase(type)) {
          UIManager.put(element.getProperty("NAME"), Integer.valueOf(element.getProperty("VALUE")));
        }
        else if ("String".equalsIgnoreCase(type) || "java.lang.String".equalsIgnoreCase(type)) {
          UIManager.put(element.getProperty("NAME"), element.getProperty("VALUE"));
        }
        else if ("Color".equalsIgnoreCase(type) || "java.awt.Color".equalsIgnoreCase(type)) {
          Color color = Color.decode(element.getProperty("VALUE"));
          UIManager.put(element.getProperty("NAME"), color);
        }
        else if ("Insets".equalsIgnoreCase(type) || "java.awt.Insets".equalsIgnoreCase(type)) {
          Insets insets = parseInsets(element.getProperty("VALUE"));
          UIManager.put(element.getProperty("NAME"), insets);
        }
        else if ("Dimension".equalsIgnoreCase(type) || "java.awt.Dimension".equalsIgnoreCase(type)) {
          Dimension dim = parseDimension(element.getProperty("VALUE"));
          UIManager.put(element.getProperty("NAME"), dim);
        }
        else if ("LineBorder".equalsIgnoreCase(type) || "javax.swing.border.LineBorder".equalsIgnoreCase(type)) {

          boolean rounded = false;
          Color color = Color.black;
          int thickness = 1;
          String temp;

          temp = element.getProperty("ROUNDED");

          if (temp != null) {
            rounded = (Boolean.getBoolean(temp));
          }

          temp = element.getProperty("THICKNESS");
          if (temp != null) {
            thickness = Integer.parseInt(temp);
          }

          temp = element.getProperty("COLOR");
          if (temp != null) {
            color = Color.decode(temp);
          }
          Border border = new com.l2fprod.gui.border.LineBorder(color, thickness, rounded);
          UIManager.put(element.getProperty("NAME"), border);
        }
        else if ("EmptyBorder".equalsIgnoreCase(type) || "javax.swing.border.EmptyBorder".equalsIgnoreCase(type)) {
          Insets insets = parseInsets(element.getProperty("VALUE"));
          Border border = new javax.swing.border.EmptyBorder(insets);
          UIManager.put(element.getProperty("NAME"), border);
        }
      }
      else if ("font".equalsIgnoreCase(tagName)) {
        String[] fontStyle = StringUtils.splitString(element.getProperty("VALUE"), ",");
        Font f = SkinUtils.getFont(fontStyle[0],
                                   Integer.parseInt(fontStyle[1]),
                                   Integer.parseInt(fontStyle[2]));
        if (f != null) {
          if ("Global".equalsIgnoreCase(element.getProperty("NAME"))) {
            SkinUtils.setFont(new FontUIResource(f));
          }
          else {
            UIManager.put(element.getProperty("NAME"), new FontUIResource(f));
          }
        }
      }
      else if ("icon".equalsIgnoreCase(tagName)) {
        final URL iconURL = new URL(url, element.getProperty("VALUE"));
        ImageIcon icon = new ImageIcon(SkinUtils.loadImage(iconURL));
        UIManager.put(element.getProperty("NAME"), new IconUIResource(icon));
        // put the default internal icon at work for JOptionPane too
        if ("InternalFrame.icon".equals(element.getProperty("NAME"))) {
          JOptionPane.getRootFrame().setIconImage(icon.getImage());
        }
      }
    }
    return skin;
  }

  /**
   * Description of the Method
   *
   * @param required       Description of Parameter
   * @exception Exception  Description of Exception
   */
  public static void checkRequiredVersion(String required) throws Exception {
    if ((required == null) || ("".equals(required))) {
      return;
    }
    checkRequiredVersion(VERSION, required);
  }

  /**
   * Gets the InputStream attribute of the SkinLookAndFeel class
   *
   * @param url            Description of Parameter
   * @return               The InputStream value
   * @exception Exception  Description of Exception
   */
  static InputStream getInputStream(URL url) throws Exception {
    ZipResourceLoader loader = (ZipResourceLoader) UIManager.get(SKIN_LOADER_KEY);
    if (loader != null) {
      return loader.getZipResource(url).getInputStream();
    }
    else {
      return url.openStream();
    }
  }

  /**
   * Gets the URLContent attribute of the SkinLookAndFeel class
   *
   * @param url            Description of Parameter
   * @return               The URLContent value
   * @exception Exception  Description of Exception
   */
  static byte[] getURLContent(URL url) throws Exception {
    ZipResourceLoader loader = (ZipResourceLoader) UIManager.get(SKIN_LOADER_KEY);
    if (loader == null) {
      BufferedInputStream input = new BufferedInputStream(url.openStream());
      ByteArrayOutputStream output = new ByteArrayOutputStream();
      int read;
      while ((read = input.read()) != -1) {
        output.write(read);
      }
      return output.toByteArray();
    }
    else {
      return loader.getZipResource(url).getURLContent();
    }
  }

  /**
   * Description of the Method
   *
   * @param str  Description of Parameter
   * @return     Description of the Returned Value
   */
  private static Dimension parseDimension(String str) {
    int[] dim = new int[2];
    Arrays.fill(dim, 0);
    String[] dimStrings = StringUtils.splitString(str, "{,}");
    for (int i = 0; i < 4; i++) {
      dim[i] = Integer.parseInt(dimStrings[i]);
    }
    return new Dimension(dim[0], dim[1]);
  }

  /**
   * Description of the Method
   *
   * @param str  Description of Parameter
   * @return     Description of the Returned Value
   */
  private static Insets parseInsets(String str) {
    int[] insets = new int[4];
    Arrays.fill(insets, 0);
    String[] insetsString = StringUtils.splitString(str, "{,}");
    for (int i = 0; i < 4; i++) {
      insets[i] = Integer.parseInt(insetsString[i]);
    }
    return new Insets(insets[0], insets[1], insets[2], insets[3]);
  }

  /**
   * Description of the Method
   *
   * @param current        Description of Parameter
   * @param required       Description of Parameter
   * @exception Exception  Description of Exception
   */
  private static void checkRequiredVersion(String current, String required) throws Exception {
    java.util.StringTokenizer currentToken = new java.util.StringTokenizer(current, ".");
    java.util.StringTokenizer requiredToken = new java.util.StringTokenizer(required, ".");

    int currentCount = currentToken.countTokens();
    int requiredCount = requiredToken.countTokens();

    int min = Math.min(currentCount, requiredCount);

    for (int i = 0; i < min; i++) {
      String cTok = currentToken.nextToken();
      String rTok = requiredToken.nextToken();
      // the current version is bigger than the required
      if (cTok.compareTo(rTok) > 0) {
        break;
      }
      if (cTok.compareTo(rTok) < 0) {
        throw new IncorrectVersionException(required, current);
      }
      if ((i == min - 1) && (currentCount < requiredCount)) {
        throw new IncorrectVersionException(required, current);
      }
    }

  }

  /**
   * Description of the Method
   *
   * @param context        Description of Parameter
   * @param element        Description of Parameter
   * @return               Description of the Returned Value
   * @exception Exception  Description of Exception
   */
  private static Skin buildSkin(URL context, XMLElement element) throws Exception {
    Skin result = null;
    if (element.countChildren() == 0) {
      result = loadSkin(new URL(context, element.getProperty("URL")));
    }
    else if (element.countChildren() == 2) {
      // it's a compound skin
      result =
        new CompoundSkin(buildSkin(context, (XMLElement) element.getChildren().elementAt(0)),
                         buildSkin(context, (XMLElement) element.getChildren().elementAt(1)));
    }
    return result;
  }

}
