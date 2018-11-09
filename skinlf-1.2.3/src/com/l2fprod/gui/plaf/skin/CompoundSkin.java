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
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.*;

import com.l2fprod.gui.plaf.skin.impl.AbstractSkin;

/**
 * Assembles two skins to create a new one. <br>
 * This can be used to combine features from two skins.
 *
 * @author    $Author: l2fprod $
 * @created   27 avril 2002
 * @version   $Revision: 1.11 $, $Date: 2002/06/11 18:04:51 $
 */
public class CompoundSkin extends AbstractSkin {

  private Skin skina, skinb;

  /**
   * Construct a new Skin by merging two skins.<BR>
   * If a feature is missing in the first skin, the second skin is used.
   *
   * @param a  Description of Parameter
   * @param b  Description of Parameter
   */
  public CompoundSkin(Skin a, Skin b) {
    skina = a;
    skinb = b;

    if ((a == null) || (b == null)) {
      throw new IllegalArgumentException("Skins must not be null!");
    }

    button = new CompoundButton();
    frame = new CompoundFrame();
    personality = new CompoundPersonality();
    progress = new CompoundProgress();
    scrollbar = new CompoundScrollbar();
    slider = new CompoundSlider();
    tab = new CompoundTab();
    splitpane = new CompoundSplitPane();
    separator = new CompoundSeparator();
  }

  /**
   * Gets the Colors attribute of the CompoundSkin object
   *
   * @return   The Colors value
   */
  public String[] getColors() {
    Vector v = new Vector();
    int colorSize = 0;
    String[] result = skina.getColors();
    if (result != null) {
      addColors(result, v);
    }
    result = skinb.getColors();
    if (result != null) {
      addColors(result, v);
    }
    result = new String[v.size()];
    v.copyInto(result);
    return result;
  }

  /**
   * Gets the Resource attribute of the CompoundSkin object
   *
   * @param key  Description of Parameter
   * @return     The Resource value
   */
  public Object getResource(Object key) {
    Object value = skina.getResource(key);
    return (value != null ? value : skinb.getResource(key));
  }

  /**
   * Adds a feature to the Colors attribute of the CompoundSkin object
   *
   * @param colors  The feature to be added to the Colors attribute
   * @param v       The feature to be added to the Colors attribute
   */
  protected void addColors(String[] colors, Vector v) {
    for (int i = 0, c = colors.length; i < c; i = i + 2) {
      if ("".equals(colors[i + 1])) {
        continue;
      }
      v.addElement(colors[i]);
      v.addElement(colors[i + 1]);
    }
  }

  private class CompoundComponent implements SkinComponent {
    public boolean status() {
      return true;
    }
    public boolean installSkin(javax.swing.JComponent c) {
      return false;
    }
    public void uninstallSkin(javax.swing.JComponent c) {
    }
  }

  /**
   * Description of the Class
   *
   * @author    fred
   * @created   27 avril 2002
   */
  private class CompoundButton extends CompoundComponent implements SkinButton {
    /**
     * Gets the CheckBoxIconSize attribute of the CompoundButton object
     *
     * @return   The CheckBoxIconSize value
     */
    public Dimension getCheckBoxIconSize() {
      Dimension dimension = null;
      if (skina.getButton() != null) {
        dimension = skina.getButton().getCheckBoxIconSize();
      }
      if ((dimension == null) && (skinb.getButton() != null)) {
        dimension = skinb.getButton().getCheckBoxIconSize();
      }
      return dimension;
    }

    /**
     * Gets the RadioButtonIconSize attribute of the CompoundButton object
     *
     * @return   The RadioButtonIconSize value
     */
    public Dimension getRadioButtonIconSize() {
      Dimension dimension = null;
      if (skina.getButton() != null) {
        dimension = skina.getButton().getRadioButtonIconSize();
      }
      if ((dimension == null) && (skinb.getButton() != null)) {
        dimension = skinb.getButton().getRadioButtonIconSize();
      }
      return dimension;
    }

    /**
     * Gets the RadioIcon attribute of the CompoundButton object
     *
     * @param b  Description of Parameter
     * @return   The RadioIcon value
     */
    public Icon getRadioIcon(AbstractButton b) {
      Icon icon = null;
      if (skina.getButton() != null) {
        icon = skina.getButton().getRadioIcon(b);
      }
      if ((icon == null) && (skinb.getButton() != null)) {
        icon = skinb.getButton().getRadioIcon(b);
      }
      return icon;
    }

    /**
     * Description of the Method
     *
     * @return   Description of the Returned Value
     */
    public boolean status() {
      boolean result = false;
      if (skina.getButton() != null) {
        result = skina.getButton().status();
      }
      if (!result && (skinb.getButton() != null)) {
        result = skinb.getButton().status();
      }
      return result;
    }

    /**
     * Description of the Method
     *
     * @param c  Description of Parameter
     * @return   Description of the Returned Value
     */
    public boolean installSkin(JComponent c) {
      boolean result = false;
      if (skina.getButton() != null) {
        result = skina.getButton().installSkin(c);
      }
      if (!result && (skinb.getButton() != null)) {
        result = skinb.getButton().installSkin(c);
      }
      return result;
    }

    /**
     * Description of the Method
     *
     * @param g  Description of Parameter
     * @param b  Description of Parameter
     * @return   Description of the Returned Value
     */
    public boolean paintButton(Graphics g, AbstractButton b) {
      boolean result = false;
      if (skina.getButton() != null) {
        result = skina.getButton().paintButton(g, b);
      }
      if (!result && (skinb.getButton() != null)) {
        result = skinb.getButton().paintButton(g, b);
      }
      return result;
    }
  }

  /**
   * Description of the Class
   *
   * @author    fred
   * @created   27 avril 2002
   */
  private class CompoundFrame extends CompoundComponent implements SkinFrame {
    /**
     * Gets the WindowButtons attribute of the CompoundFrame object
     *
     * @param align  Description of Parameter
     * @return       The WindowButtons value
     */
    public SkinWindowButton[] getWindowButtons(int align) {
      SkinWindowButton[] buttons = null;
      if (skina.getFrame() != null) {
        buttons = skina.getFrame().getWindowButtons(align);
      }
      if ((buttons == null) && (skinb.getFrame() != null)) {
        buttons = skinb.getFrame().getWindowButtons(align);
      }
      return buttons;
    }

    /**
     * Gets the TopPreferredSize attribute of the CompoundFrame object
     *
     * @return   The TopPreferredSize value
     */
    public Dimension getTopPreferredSize() {
      Dimension dimension = null;
      if (skina.getFrame() != null) {
        dimension = skina.getFrame().getTopPreferredSize();
      }
      if ((dimension == null) && (skinb.getFrame() != null)) {
        dimension = skinb.getFrame().getTopPreferredSize();
      }
      return dimension;
    }

    /**
     * Description of the Method
     *
     * @return   Description of the Returned Value
     */
    public boolean status() {
      boolean result = false;
      if (skina.getFrame() != null) {
        result = skina.getFrame().status();
      }
      if (!result && (skinb.getFrame() != null)) {
        result = skinb.getFrame().status();
      }
      return result;
    }

    /**
     * Description of the Method
     *
     * @param c  Description of Parameter
     * @return   Description of the Returned Value
     */
    public boolean installSkin(JComponent c) {
      boolean result = false;
      if (skina.getFrame() != null) {
        result = skina.getFrame().installSkin(c);
      }
      if (!result && (skinb.getFrame() != null)) {
        result = skinb.getFrame().installSkin(c);
      }
      return result;
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
      boolean result = false;
      if (skina.getFrame() != null) {
        result = skina.getFrame().paintTop(g, c, isSelected, title);
      }
      if (!result && (skinb.getFrame() != null)) {
        result = skinb.getFrame().paintTop(g, c, isSelected, title);
      }
      return result;
    }
  }

  /**
   * Description of the Class
   *
   * @author    fred
   * @created   27 avril 2002
   */
  private class CompoundPersonality extends CompoundComponent implements SkinPersonality {

    /**
     * Gets the ComboBoxInsets attribute of the CompoundPersonality object
     *
     * @return   The ComboBoxInsets value
     */
    public java.awt.Insets getComboBoxInsets() {
      Insets insets = null;
      if (skina.getPersonality() != null) {
        insets = skina.getPersonality().getComboBoxInsets();
      }
      if ((insets == null) && (skinb.getPersonality() != null)) {
        insets = skinb.getPersonality().getComboBoxInsets();
      }
      return insets;
    }

    /**
     * Gets the ComboBoxPreferredSize attribute of the CompoundPersonality
     * object
     *
     * @param c  Description of Parameter
     * @return   The ComboBoxPreferredSize value
     */
    public java.awt.Dimension getComboBoxPreferredSize(javax.swing.JComboBox c) {
      Dimension dimension = null;
      if (skina.getPersonality() != null) {
        dimension = skina.getPersonality().getComboBoxPreferredSize(c);
      }
      if ((dimension == null) && (skinb.getPersonality() != null)) {
        dimension = skinb.getPersonality().getComboBoxPreferredSize(c);
      }
      return dimension;
    }

    /**
     * Gets the TableHeaderRenderer attribute of the CompoundPersonality object
     *
     * @return   The TableHeaderRenderer value
     */
    public TableCellRenderer getTableHeaderRenderer() {
      TableCellRenderer renderer = null;
      if (skina.getPersonality() != null) {
        renderer = skina.getPersonality().getTableHeaderRenderer();
      }
      if ((renderer == null) && (skinb.getPersonality() != null)) {
        renderer = skinb.getPersonality().getTableHeaderRenderer();
      }
      return renderer;
    }

    /**
     * Description of the Method
     *
     * @return   Description of the Returned Value
     */
    public boolean status() {
      boolean result = false;
      if (skina.getPersonality() != null) {
        result = skina.getPersonality().status();
      }
      if (!result && (skinb.getPersonality() != null)) {
        result = skinb.getPersonality().status();
      }
      return result;
    }

    /**
     * Description of the Method
     *
     * @param c  Description of Parameter
     * @return   Description of the Returned Value
     */
    public boolean installSkin(JComponent c) {
      boolean result = false;
      if (skina.getPersonality() != null) {
        result = skina.getPersonality().installSkin(c);
      }
      if (!result && (skinb.getPersonality() != null)) {
        result = skinb.getPersonality().installSkin(c);
      }
      return result;
    }

    /**
     * Description of the Method
     *
     * @param g  Description of Parameter
     * @param c  Description of Parameter
     * @return   Description of the Returned Value
     */
    public boolean paintDialog(Graphics g, Component c) {
      boolean result = false;
      if (skina.getPersonality() != null) {
        result = skina.getPersonality().paintDialog(g, c);
      }
      if (!result && (skinb.getPersonality() != null)) {
        result = skinb.getPersonality().paintDialog(g, c);
      }
      return result;
    }

    /**
     * Description of the Method
     *
     * @param g  Description of Parameter
     * @param c  Description of Parameter
     * @return   Description of the Returned Value
     */
    public boolean paintFocus(Graphics g, JComponent c) {
      boolean result = false;
      if (skina.getPersonality() != null) {
        result = skina.getPersonality().paintFocus(g, c);
      }
      if (!result && (skinb.getPersonality() != null)) {
        result = skinb.getPersonality().paintFocus(g, c);
      }
      return result;
    }

    /**
     * Description of the Method
     *
     * @param g  Description of Parameter
     * @param c  Description of Parameter
     * @return   Description of the Returned Value
     */
    public boolean paintMenu(Graphics g, JMenu c) {
      boolean result = false;
      if (skina.getPersonality() != null) {
        result = skina.getPersonality().paintMenu(g, c);
      }
      if (!result && (skinb.getPersonality() != null)) {
        result = skinb.getPersonality().paintMenu(g, c);
      }
      return result;
    }

    /**
     * Description of the Method
     *
     * @param g  Description of Parameter
     * @param c  Description of Parameter
     * @return   Description of the Returned Value
     */
    public boolean paintMenuItem(Graphics g, JMenuItem c) {
      boolean result = false;
      if (skina.getPersonality() != null) {
        result = skina.getPersonality().paintMenuItem(g, c);
      }
      if (!result && (skinb.getPersonality() != null)) {
        result = skinb.getPersonality().paintMenuItem(g, c);
      }
      return result;
    }

    /**
     * Description of the Method
     *
     * @param g         Description of Parameter
     * @param c         Description of Parameter
     * @param bounds    Description of Parameter
     * @param hasFocus  Description of Parameter
     * @return          Description of the Returned Value
     */
    public boolean paintComboBox(Graphics g, JComboBox c, Rectangle bounds, boolean hasFocus) {
      boolean result = false;
      if (skina.getPersonality() != null) {
        result = skina.getPersonality().paintComboBox(g, c, bounds, hasFocus);
      }
      if (!result && (skinb.getPersonality() != null)) {
        result = skinb.getPersonality().paintComboBox(g, c, bounds, hasFocus);
      }
      return result;
    }

    /**
     * Description of the Method
     *
     * @param g  Description of Parameter
     * @param c  Description of Parameter
     * @return   Description of the Returned Value
     */
    public boolean paintBackground(Graphics g, Component c) {
      boolean result = false;
      if (skina.getPersonality() != null) {
        result = skina.getPersonality().paintBackground(g, c);
      }
      if (!result && (skinb.getPersonality() != null)) {
        result = skinb.getPersonality().paintBackground(g, c);
      }
      return result;
    }

    /**
     * Description of the Method
     *
     * @return   Description of the Returned Value
     */
    public TableCellRenderer createTableHeaderRenderer() {
      return getTableHeaderRenderer();
    }

    /**
     * Description of the Method
     *
     * @return   Description of the Returned Value
     */
    public ListCellRenderer createListCellRenderer() {
      ListCellRenderer renderer = null;
      if (skina.getPersonality() != null) {
        renderer = skina.getPersonality().createListCellRenderer();
      }
      if ((renderer == null) && (skinb.getPersonality() != null)) {
        renderer = skinb.getPersonality().createListCellRenderer();
      }
      return renderer;
    }
  }

  /**
   * Description of the Class
   *
   * @author    fred
   * @created   27 avril 2002
   */
  private class CompoundProgress extends CompoundComponent implements SkinProgress {
    /**
     * Gets the MinimumSize attribute of the CompoundProgress object
     *
     * @param progress  Description of Parameter
     * @return          The MinimumSize value
     */
    public Dimension getMinimumSize(javax.swing.JProgressBar progress) {
      Dimension dimension = null;
      if (skina.getProgress() != null) {
        dimension = skina.getProgress().getMinimumSize(progress);
      }
      if ((dimension == null) && (skinb.getProgress() != null)) {
        dimension = skinb.getProgress().getMinimumSize(progress);
      }
      return dimension;
    }

    /**
     * Description of the Method
     *
     * @return   Description of the Returned Value
     */
    public boolean status() {
      boolean result = false;
      if (skina.getProgress() != null) {
        result = skina.getProgress().status();
      }
      if (!result && (skinb.getProgress() != null)) {
        result = skinb.getProgress().status();
      }
      return result;
    }

    /**
     * Description of the Method
     *
     * @param c  Description of Parameter
     * @return   Description of the Returned Value
     */
    public boolean installSkin(JComponent c) {
      boolean result = false;
      if (skina.getProgress() != null) {
        result = skina.getProgress().installSkin(c);
      }
      if (!result && (skinb.getProgress() != null)) {
        result = skinb.getProgress().installSkin(c);
      }
      return result;
    }

    /**
     * Description of the Method
     *
     * @param g         Description of Parameter
     * @param progress  Description of Parameter
     * @return          Description of the Returned Value
     */
    public boolean paintProgress(Graphics g, JProgressBar progress) {
      boolean result = false;
      if (skina.getProgress() != null) {
        result = skina.getProgress().paintProgress(g, progress);
      }
      if (!result && (skinb.getProgress() != null)) {
        result = skinb.getProgress().paintProgress(g, progress);
      }
      return result;
    }
  }

  /**
   * Description of the Class
   *
   * @author    fred
   * @created   27 avril 2002
   */
  private class CompoundScrollbar extends CompoundComponent implements SkinScrollbar {
    /**
     * Gets the PreferredSize attribute of the CompoundScrollbar object
     *
     * @param scrollbar  Description of Parameter
     * @return           The PreferredSize value
     */
    public Dimension getPreferredSize(JScrollBar scrollbar) {
      Dimension dimension = null;
      if (skina.getScrollbar() != null) {
        dimension = skina.getScrollbar().getPreferredSize(scrollbar);
      }
      if ((dimension == null) && (skinb.getScrollbar() != null)) {
        dimension = skinb.getScrollbar().getPreferredSize(scrollbar);
      }
      return dimension;
    }

    /**
     * Gets the MinimumThumbSize attribute of the CompoundScrollbar object
     *
     * @return   The MinimumThumbSize value
     */
    public Dimension getMinimumThumbSize() {
      Dimension dimension = null;
      if (skina.getScrollbar() != null) {
        dimension = skina.getScrollbar().getMinimumThumbSize();
      }
      if ((dimension == null) && (skinb.getScrollbar() != null)) {
        dimension = skinb.getScrollbar().getMinimumThumbSize();
      }
      return dimension;
    }

    /**
     * Gets the ArrowPreferredSize attribute of the CompoundScrollbar object
     *
     * @param direction  Description of Parameter
     * @return           The ArrowPreferredSize value
     */
    public Dimension getArrowPreferredSize(int direction) {
      Dimension dimension = null;
      if (skina.getScrollbar() != null) {
        dimension = skina.getScrollbar().getArrowPreferredSize(direction);
      }
      if ((dimension == null) && (skinb.getScrollbar() != null)) {
        dimension = skinb.getScrollbar().getArrowPreferredSize(direction);
      }
      return dimension;
    }

    /**
     * Description of the Method
     *
     * @return   Description of the Returned Value
     */
    public boolean status() {
      boolean result = false;
      if (skina.getScrollbar() != null) {
        result = skina.getScrollbar().status();
      }
      if (!result && (skinb.getScrollbar() != null)) {
        result = skinb.getScrollbar().status();
      }
      return result;
    }

    /**
     * Description of the Method
     *
     * @param c  Description of Parameter
     * @return   Description of the Returned Value
     */
    public boolean installSkin(JComponent c) {
      boolean result = false;
      if (skina.getScrollbar() != null) {
        result = skina.getScrollbar().installSkin(c);
      }
      if (!result && (skinb.getScrollbar() != null)) {
        result = skinb.getScrollbar().installSkin(c);
      }
      return result;
    }

    /**
     * Description of the Method
     *
     * @param g          Description of Parameter
     * @param b          Description of Parameter
     * @param direction  Description of Parameter
     * @return           Description of the Returned Value
     */
    public boolean paintArrow(Graphics g, AbstractButton b, int direction) {
      boolean result = false;
      if (skina.getScrollbar() != null) {
        result = skina.getScrollbar().paintArrow(g, b, direction);
      }
      if (!result && (skinb.getScrollbar() != null)) {
        result = skinb.getScrollbar().paintArrow(g, b, direction);
      }
      return result;
    }

    /**
     * Description of the Method
     *
     * @param g            Description of Parameter
     * @param scrollbar    Description of Parameter
     * @param thumbBounds  Description of Parameter
     * @return             Description of the Returned Value
     */
    public boolean paintThumb(Graphics g, JScrollBar scrollbar, Rectangle thumbBounds) {
      boolean result = false;
      if (skina.getScrollbar() != null) {
        result = skina.getScrollbar().paintThumb(g, scrollbar, thumbBounds);
      }
      if (!result && (skinb.getScrollbar() != null)) {
        result = skinb.getScrollbar().paintThumb(g, scrollbar, thumbBounds);
      }
      return result;
    }

    /**
     * Description of the Method
     *
     * @param g            Description of Parameter
     * @param scrollbar    Description of Parameter
     * @param trackBounds  Description of Parameter
     * @return             Description of the Returned Value
     */
    public boolean paintTrack(Graphics g, JScrollBar scrollbar, Rectangle trackBounds) {
      boolean result = false;
      if (skina.getScrollbar() != null) {
        result = skina.getScrollbar().paintTrack(g, scrollbar, trackBounds);
      }
      if (!result && (skinb.getScrollbar() != null)) {
        result = skinb.getScrollbar().paintTrack(g, scrollbar, trackBounds);
      }
      return result;
    }
  }

  /**
   * Description of the Class
   *
   * @author    fred
   * @created   27 avril 2002
   */
  private class CompoundSlider extends CompoundComponent implements SkinSlider {
    /**
     * Gets the PreferredSize attribute of the CompoundSlider object
     *
     * @param slider  Description of Parameter
     * @return        The PreferredSize value
     */
    public Dimension getPreferredSize(JSlider slider) {
      Dimension dimension = null;
      if (skina.getSlider() != null) {
        dimension = skina.getSlider().getPreferredSize(slider);
      }
      if ((dimension == null) && (skinb.getSlider() != null)) {
        dimension = skinb.getSlider().getPreferredSize(slider);
      }
      return dimension;
    }

    /**
     * Description of the Method
     *
     * @return   Description of the Returned Value
     */
    public boolean status() {
      boolean result = false;
      if (skina.getSlider() != null) {
        result = skina.getSlider().status();
      }
      if (!result && (skinb.getSlider() != null)) {
        result = skinb.getSlider().status();
      }
      return result;
    }

    /**
     * Description of the Method
     *
     * @param c  Description of Parameter
     * @return   Description of the Returned Value
     */
    public boolean installSkin(JComponent c) {
      boolean result = false;
      if (skina.getSlider() != null) {
        result = skina.getSlider().installSkin(c);
      }
      if (!result && (skinb.getSlider() != null)) {
        result = skinb.getSlider().installSkin(c);
      }
      return result;
    }

    /**
     * Description of the Method
     *
     * @param g            Description of Parameter
     * @param slider       Description of Parameter
     * @param trackBounds  Description of Parameter
     * @return             Description of the Returned Value
     */
    public boolean paintTrack(Graphics g, JSlider slider, Rectangle trackBounds) {
      boolean result = false;
      if (skina.getSlider() != null) {
        result = skina.getSlider().paintTrack(g, slider, trackBounds);
      }
      if (!result && (skinb.getSlider() != null)) {
        result = skinb.getSlider().paintTrack(g, slider, trackBounds);
      }
      return result;
    }

    /**
     * Description of the Method
     *
     * @param g            Description of Parameter
     * @param slider       Description of Parameter
     * @param thumbBounds  Description of Parameter
     * @return             Description of the Returned Value
     */
    public boolean paintThumb(Graphics g, JSlider slider, Rectangle thumbBounds) {
      boolean result = false;
      if (skina.getSlider() != null) {
        result = skina.getSlider().paintThumb(g, slider, thumbBounds);
      }
      if (!result && (skinb.getSlider() != null)) {
        result = skinb.getSlider().paintThumb(g, slider, thumbBounds);
      }
      return result;
    }
  }

  /**
   * Description of the Class
   *
   * @author    fred
   * @created   27 avril 2002
   */
  private class CompoundSeparator extends CompoundComponent implements SkinSeparator {
    public boolean paint(java.awt.Graphics g, javax.swing.JSeparator separator) {
      boolean result = false;
      if (skina.getSeparator() != null) {
        result = skina.getSeparator().paint(g, separator);
      }
      if (!result && (skinb.getSeparator() != null)) {
        result = skinb.getSeparator().paint(g, separator);
      }
      return result;
    }
    public java.awt.Dimension getPreferredSize(javax.swing.JSeparator separator) {
      Dimension dimension = null;
      if (skina.getSeparator() != null) {
        dimension = skina.getSeparator().getPreferredSize(separator);
      }
      if ((dimension == null) && (skinb.getSlider() != null)) {
        dimension = skinb.getSeparator().getPreferredSize(separator);
      }
      return dimension;
    }
    /**
     * Description of the Method
     *
     * @return   Description of the Returned Value
     */
    public boolean status() {
      boolean result = false;
      if (skina.getSeparator() != null) {
        result = skina.getSeparator().status();
      }
      if (!result && (skinb.getSeparator() != null)) {
        result = skinb.getSeparator().status();
      }
      return result;
    }

    /**
     * Description of the Method
     *
     * @param c  Description of Parameter
     * @return   Description of the Returned Value
     */
    public boolean installSkin(JComponent c) {
      boolean result = false;
      if (skina.getSeparator() != null) {
        result = skina.getSeparator().installSkin(c);
      }
      if (!result && (skinb.getSeparator() != null)) {
        result = skinb.getSeparator().installSkin(c);
      }
      return result;
    }
  }

  /**
   * Description of the Class
   *
   * @author    fred
   * @created   27 avril 2002
   */
  private class CompoundSplitPane extends CompoundComponent implements SkinSplitPane {
    /**
     * Gets the ArrowPreferredSize attribute of the CompoundSplitPane object
     *
     * @param direction  Description of Parameter
     * @return           The ArrowPreferredSize value
     */
    public Dimension getArrowPreferredSize(int direction) {
      Dimension dimension = null;
      if (skina.getSplitPane() != null) {
        dimension = skina.getSplitPane().getArrowPreferredSize(direction);
      }
      if ((dimension == null) && (skinb.getSlider() != null)) {
        dimension = skinb.getSplitPane().getArrowPreferredSize(direction);
      }
      return dimension;
    }

    /**
     * Gets the PreferredSize attribute of the CompoundSplitPane object
     *
     * @param splitpane  Description of Parameter
     * @return           The PreferredSize value
     */
    public Dimension getPreferredSize(JSplitPane splitpane) {
      Dimension dimension = null;
      if (skina.getSplitPane() != null) {
        dimension = skina.getSplitPane().getPreferredSize(splitpane);
      }
      if ((dimension == null) && (skinb.getSlider() != null)) {
        dimension = skinb.getSplitPane().getPreferredSize(splitpane);
      }
      return dimension;
    }

    /**
     * Description of the Method
     *
     * @return   Description of the Returned Value
     */
    public boolean status() {
      boolean result = false;
      if (skina.getSplitPane() != null) {
        result = skina.getSplitPane().status();
      }
      if (!result && (skinb.getSplitPane() != null)) {
        result = skinb.getSplitPane().status();
      }
      return result;
    }

    /**
     * Description of the Method
     *
     * @param c  Description of Parameter
     * @return   Description of the Returned Value
     */
    public boolean installSkin(JComponent c) {
      boolean result = false;
      if (skina.getSplitPane() != null) {
        result = skina.getSplitPane().installSkin(c);
      }
      if (!result && (skinb.getSplitPane() != null)) {
        result = skinb.getSplitPane().installSkin(c);
      }
      return result;
    }

    /**
     * Description of the Method
     *
     * @param g          Description of Parameter
     * @param b          Description of Parameter
     * @param direction  Description of Parameter
     * @return           Description of the Returned Value
     */
    public boolean paintArrow(Graphics g, AbstractButton b, int direction) {
      boolean result = false;
      if (skina.getSplitPane() != null) {
        result = skina.getSplitPane().paintArrow(g, b, direction);
      }
      if (!result && (skinb.getSplitPane() != null)) {
        result = skinb.getSplitPane().paintArrow(g, b, direction);
      }
      return result;
    }

    /**
     * Description of the Method
     *
     * @param g          Description of Parameter
     * @param splitpane  Description of Parameter
     * @param d          Description of Parameter
     * @return           Description of the Returned Value
     */
    public boolean paintGutter(Graphics g, JSplitPane splitpane, Dimension d) {
      boolean result = false;
      if (skina.getSplitPane() != null) {
        result = skina.getSplitPane().paintGutter(g, splitpane, d);
      }
      if (!result && (skinb.getSplitPane() != null)) {
        result = skinb.getSplitPane().paintGutter(g, splitpane, d);
      }
      return result;
    }

    /**
     * Description of the Method
     *
     * @param g          Description of Parameter
     * @param splitpane  Description of Parameter
     * @param d          Description of Parameter
     * @return           Description of the Returned Value
     */
    public boolean paintThumb(Graphics g, JSplitPane splitpane, Dimension d) {
      boolean result = false;
      if (skina.getSplitPane() != null) {
        result = skina.getSplitPane().paintThumb(g, splitpane, d);
      }
      if (!result && (skinb.getSplitPane() != null)) {
        result = skinb.getSplitPane().paintThumb(g, splitpane, d);
      }
      return result;
    }
  }

  /**
   * Description of the Class
   *
   * @author    fred
   * @created   27 avril 2002
   */
  private class CompoundTab extends CompoundComponent implements SkinTab {
    /**
     * Description of the Method
     *
     * @return   Description of the Returned Value
     */
    public boolean status() {
      boolean result = false;
      if (skina.getTab() != null) {
        result = skina.getTab().status();
      }
      if (!result && (skinb.getTab() != null)) {
        result = skinb.getTab().status();
      }
      return result;
    }

    /**
     * Description of the Method
     *
     * @param c  Description of Parameter
     * @return   Description of the Returned Value
     */
    public boolean installSkin(JComponent c) {
      boolean result = false;
      if (skina.getTab() != null) {
        result = skina.getTab().installSkin(c);
      }
      if (!result && (skinb.getTab() != null)) {
        result = skinb.getTab().installSkin(c);
      }
      return result;
    }

    /**
     * Description of the Method
     *
     * @param g             Description of Parameter
     * @param tabPlacement  Description of Parameter
     * @param isSelected    Description of Parameter
     * @param x             Description of Parameter
     * @param y             Description of Parameter
     * @param w             Description of Parameter
     * @param h             Description of Parameter
     * @return              Description of the Returned Value
     */
    public boolean paintTab(Graphics g, int tabPlacement,
        boolean isSelected, int x, int y, int w, int h) {
      boolean result = false;
      if (skina.getTab() != null) {
        result = skina.getTab().paintTab(g, tabPlacement, isSelected, x, y, w, h);
      }
      if (!result && (skinb.getTab() != null)) {
        result = skinb.getTab().paintTab(g, tabPlacement, isSelected, x, y, w, h);
      }
      return result;
    }

    /**
     * Description of the Method
     *
     * @param g              Description of Parameter
     * @param tabPlacement   Description of Parameter
     * @param selectedIndex  Description of Parameter
     * @param x              Description of Parameter
     * @param y              Description of Parameter
     * @param w              Description of Parameter
     * @param h              Description of Parameter
     * @return               Description of the Returned Value
     */
    public boolean paintContent(java.awt.Graphics g, int tabPlacement, int selectedIndex,
        int x, int y, int w, int h) {
      boolean result = false;
      if (skina.getTab() != null) {
        result = skina.getTab().paintContent(g, tabPlacement, selectedIndex, x, y, w, h);
      }
      if (!result && (skinb.getTab() != null)) {
        result = skinb.getTab().paintContent(g, tabPlacement, selectedIndex, x, y, w, h);
      }
      return result;
    }
  }

}

