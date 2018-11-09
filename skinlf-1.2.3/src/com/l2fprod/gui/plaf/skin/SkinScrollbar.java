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
 * Skin Scrollbar. <br>
 *
 *
 * @author    $Author: l2fprod $
 * @created   27 avril 2002
 * @version   $Revision: 1.3 $, $Date: 2002/04/27 11:39:17 $
 */
public interface SkinScrollbar extends SkinComponent {

  /**
   * Gets the PreferredSize attribute of the SkinScrollbar object
   *
   * @param scrollbar  Description of Parameter
   * @return           The PreferredSize value
   */
  java.awt.Dimension getPreferredSize(javax.swing.JScrollBar scrollbar);

  /**
   * Gets the MinimumThumbSize attribute of the SkinScrollbar object
   *
   * @return   The MinimumThumbSize value
   */
  java.awt.Dimension getMinimumThumbSize();

  /**
   * Gets the ArrowPreferredSize attribute of the SkinScrollbar object
   *
   * @param direction  Description of Parameter
   * @return           The ArrowPreferredSize value
   */
  java.awt.Dimension getArrowPreferredSize(int direction);

  /**
   * Description of the Method
   *
   * @param g          Description of Parameter
   * @param b          Description of Parameter
   * @param direction  Description of Parameter
   * @return           Description of the Returned Value
   */
  boolean paintArrow(java.awt.Graphics g, javax.swing.AbstractButton b, int direction);

  /**
   * Description of the Method
   *
   * @param g            Description of Parameter
   * @param scrollbar    Description of Parameter
   * @param thumbBounds  Description of Parameter
   * @return             Description of the Returned Value
   */
  boolean paintThumb(java.awt.Graphics g, javax.swing.JScrollBar scrollbar, java.awt.Rectangle thumbBounds);

  /**
   * Description of the Method
   *
   * @param g            Description of Parameter
   * @param scrollbar    Description of Parameter
   * @param trackBounds  Description of Parameter
   * @return             Description of the Returned Value
   */
  boolean paintTrack(java.awt.Graphics g, javax.swing.JScrollBar scrollbar, java.awt.Rectangle trackBounds);

}