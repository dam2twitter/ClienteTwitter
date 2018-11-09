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

import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.event.*;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.*;
import javax.swing.plaf.metal.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created on 05/04/2000 by Frederic Lavigne, fred@L2FProd.com
 *
 * @author    $Author: l2fprod $
 * @created   27 avril 2002
 * @version   $Revision: 1.3 $, $Date: 2002/04/27 11:42:17 $
 */
public class SkinFileChooserUI extends MetalFileChooserUI {

  final static int space = 10;

  /**
   * Constructor for the SkinFileChooserUI object
   *
   * @param filechooser  Description of Parameter
   */
  public SkinFileChooserUI(JFileChooser filechooser) {
    super(filechooser);
  }

  /**
   * Description of the Method
   *
   * @param fc  Description of Parameter
   */
  public void installComponents(JFileChooser fc) {
    super.installComponents(fc);
    // we need to traverse the component tree and replace all combo renderers with our own.
  }

  /**
   * Description of the Method
   *
   * @return   Description of the Returned Value
   */
  protected FilterComboBoxRenderer createFilterComboBoxRenderer() {
    return new MyFilterComboBoxRenderer();
  }

  /**
   * Description of the Method
   *
   * @param fc  Description of Parameter
   * @return    Description of the Returned Value
   */
  protected MyDirectoryComboBoxRenderer createMyDirectoryComboBoxRenderer(JFileChooser fc) {
    return new MyDirectoryComboBoxRenderer();
  }

  /**
   * Description of the Method
   *
   * @param c  Description of Parameter
   * @return   Description of the Returned Value
   */
  public static ComponentUI createUI(JComponent c) {
    return new SkinFileChooserUI((JFileChooser) c);
  }

  /**
   * Render different type sizes and styles.
   *
   * @author    fred
   * @created   27 avril 2002
   */
  public class MyFilterComboBoxRenderer extends FilterComboBoxRenderer {
    /**
     * Constructor for the MyFilterComboBoxRenderer object
     */
    public MyFilterComboBoxRenderer() {
      super();
      setOpaque(false);
      setBorder(null);
    }

    /**
     * Gets the ListCellRendererComponent attribute of the
     * MyFilterComboBoxRenderer object
     *
     * @param list          Description of Parameter
     * @param value         Description of Parameter
     * @param index         Description of Parameter
     * @param isSelected    Description of Parameter
     * @param cellHasFocus  Description of Parameter
     * @return              The ListCellRendererComponent value
     */
    public Component getListCellRendererComponent(JList list,
        Object value, int index, boolean isSelected,
        boolean cellHasFocus) {
      if (index == -1) {
        setOpaque(false);
      }
      else {
        setOpaque(true);
      }

      super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

      FileFilter filter = (FileFilter) value;
      if (filter != null) {
        setText(filter.getDescription());
      }

      return this;
    }
  }

  /**
   * Description of the Class
   *
   * @author    fred
   * @created   27 avril 2002
   */
  class MyDirectoryComboBoxRenderer extends DefaultListCellRenderer {
    IndentIcon ii = new IndentIcon();

    /**
     * Gets the ListCellRendererComponent attribute of the
     * MyDirectoryComboBoxRenderer object
     *
     * @param list          Description of Parameter
     * @param value         Description of Parameter
     * @param index         Description of Parameter
     * @param isSelected    Description of Parameter
     * @param cellHasFocus  Description of Parameter
     * @return              The ListCellRendererComponent value
     */
    public Component getListCellRendererComponent(JList list, Object value,
        int index, boolean isSelected,
        boolean cellHasFocus) {

      if (index == -1) {
        setOpaque(false);
      }
      else {
        setOpaque(true);
      }

      super.getListCellRendererComponent(list, value, index,
          isSelected, cellHasFocus);
      File directory = (File) value;
      if (directory == null) {
        setText("");
        return this;
      }

      String fileName = getFileChooser().getName(directory);
      setText(fileName);

      // Find the depth of the directory
      int depth = 0;
      if (index != -1) {
        File f = directory;
        while (f.getParent() != null) {
          depth++;
          f = getFileChooser().getFileSystemView().createFileObject(
              f.getParent()
              );
        }
      }

      Icon icon = getFileChooser().getIcon(directory);

      ii.icon = icon;
      ii.depth = depth;

      setIcon(ii);

      return this;
    }
  }

  /**
   * Description of the Class
   *
   * @author    fred
   * @created   27 avril 2002
   */
  class IndentIcon implements Icon {

    Icon icon = null;
    int depth = 0;

    /**
     * Gets the IconWidth attribute of the IndentIcon object
     *
     * @return   The IconWidth value
     */
    public int getIconWidth() {
      return icon.getIconWidth() + depth * space;
    }

    /**
     * Gets the IconHeight attribute of the IndentIcon object
     *
     * @return   The IconHeight value
     */
    public int getIconHeight() {
      return icon.getIconHeight();
    }

    /**
     * Description of the Method
     *
     * @param c  Description of Parameter
     * @param g  Description of Parameter
     * @param x  Description of Parameter
     * @param y  Description of Parameter
     */
    public void paintIcon(Component c, Graphics g, int x, int y) {
      icon.paintIcon(c, g, x + depth * space, y);
    }

  }

}
