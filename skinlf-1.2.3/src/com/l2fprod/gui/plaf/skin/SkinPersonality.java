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

/**
 * Skin Personality. <br>
 *
 *
 * @author    $Author: l2fprod $
 * @created   27 avril 2002
 * @version   $Revision: 1.4 $, $Date: 2002/04/27 11:39:17 $
 */
public interface SkinPersonality extends SkinComponent {

  /**
   * Description of the Method
   *
   * @param g  Description of Parameter
   * @param c  Description of Parameter
   * @return   Description of the Returned Value
   */
  boolean paintDialog(java.awt.Graphics g, java.awt.Component c);

  /**
   * Description of the Method
   *
   * @param g  Description of Parameter
   * @param c  Description of Parameter
   * @return   Description of the Returned Value
   */
  boolean paintFocus(java.awt.Graphics g, javax.swing.JComponent c);

  /**
   * Description of the Method
   *
   * @param g  Description of Parameter
   * @param c  Description of Parameter
   * @return   Description of the Returned Value
   */
  boolean paintMenu(java.awt.Graphics g, javax.swing.JMenu c);

  /**
   * Description of the Method
   *
   * @param g  Description of Parameter
   * @param c  Description of Parameter
   * @return   Description of the Returned Value
   */
  boolean paintMenuItem(java.awt.Graphics g, javax.swing.JMenuItem c);

  /**
   * Description of the Method
   *
   * @param g  Description of Parameter
   * @param c  Description of Parameter
   * @return   Description of the Returned Value
   */
  boolean paintBackground(java.awt.Graphics g, java.awt.Component c);

  /**
   * Description of the Method
   *
   * @param g         Description of Parameter
   * @param c         Description of Parameter
   * @param bounds    Description of Parameter
   * @param hasFocus  Description of Parameter
   * @return          Description of the Returned Value
   */
  boolean paintComboBox(java.awt.Graphics g,
      javax.swing.JComboBox c,
      java.awt.Rectangle bounds, boolean hasFocus);

  /**
   * Gets the ComboBoxInsets attribute of the SkinPersonality object
   *
   * @return   The ComboBoxInsets value
   */
  java.awt.Insets getComboBoxInsets();

  /**
   * Gets the ComboBoxPreferredSize attribute of the SkinPersonality object
   *
   * @param c  Description of Parameter
   * @return   The ComboBoxPreferredSize value
   */
  java.awt.Dimension getComboBoxPreferredSize(javax.swing.JComboBox c);

  /**
   * Description of the Method
   *
   * @return   Description of the Returned Value
   */
  javax.swing.ListCellRenderer createListCellRenderer();

  /**
   * @return       Description of the Returned Value
   * @deprecated   replaced by getTableHeaderRenderer which returns a shared
   *      instance
   */
  javax.swing.table.TableCellRenderer createTableHeaderRenderer();

  /**
   * Gets the TableHeaderRenderer attribute of the SkinPersonality object
   *
   * @return   The TableHeaderRenderer value
   */
  javax.swing.table.TableCellRenderer getTableHeaderRenderer();

}
