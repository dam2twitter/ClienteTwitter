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
package com.l2fprod.gui.plaf.skin.impl;

import com.l2fprod.gui.plaf.skin.*;

/**
 * Default Skin Support. <br>
 *
 *
 * @author    $Author: l2fprod $
 * @created   27 avril 2002
 * @version   $Revision: 1.6 $, $Date: 2002/05/08 15:59:53 $
 */
public class AbstractSkin implements Skin {

  /**
   * Description of the Field
   */
  protected SkinPersonality personality;
  /**
   * Description of the Field
   */
  protected SkinButton button;
  /**
   * Description of the Field
   */
  protected SkinFrame frame;
  /**
   * Description of the Field
   */
  protected SkinTab tab;
  /**
   * Description of the Field
   */
  protected SkinProgress progress;
  /**
   * Description of the Field
   */
  protected SkinScrollbar scrollbar;
  /**
   * Description of the Field
   */
  protected SkinSplitPane splitpane;
  /**
   * Description of the Field
   */
  protected SkinSeparator separator;
  /**
   * Description of the Field
   */
  protected SkinSlider slider;
  /**
   * Description of the Field
   */
  protected java.util.Hashtable resources = new java.util.Hashtable();

  /**
   * Gets the Personality attribute of the AbstractSkin object
   *
   * @return   The Personality value
   */
  public SkinPersonality getPersonality() {
    return personality;
  }

  /**
   * Gets the Button attribute of the AbstractSkin object
   *
   * @return   The Button value
   */
  public SkinButton getButton() {
    return button;
  }

  /**
   * Gets the Frame attribute of the AbstractSkin object
   *
   * @return   The Frame value
   */
  public SkinFrame getFrame() {
    return frame;
  }

  /**
   * Gets the Tab attribute of the AbstractSkin object
   *
   * @return   The Tab value
   */
  public SkinTab getTab() {
    return tab;
  }

  /**
   * Gets the Progress attribute of the AbstractSkin object
   *
   * @return   The Progress value
   */
  public SkinProgress getProgress() {
    return progress;
  }

  /**
   * Gets the Colors attribute of the AbstractSkin object
   *
   * @return   The Colors value
   */
  public String[] getColors() {
    return null;
  }

  /**
   * Gets the Scrollbar attribute of the AbstractSkin object
   *
   * @return   The Scrollbar value
   */
  public SkinScrollbar getScrollbar() {
    return scrollbar;
  }

  /**
   * Gets the Separator attribute of the AbstractSkin object
   *
   * @return   The Separator value
   */
  public SkinSeparator getSeparator() {
    return separator;
  }

  /**
   * Gets the SplitPane attribute of the AbstractSkin object
   *
   * @return   The SplitPane value
   */
  public SkinSplitPane getSplitPane() {
    return splitpane;
  }

  /**
   * Gets the Slider attribute of the AbstractSkin object
   *
   * @return   The Slider value
   */
  public SkinSlider getSlider() {
    return slider;
  }

  /**
   * Gets the Resource attribute of the AbstractSkin object
   *
   * @param key  Description of Parameter
   * @return     The Resource value
   */
  public Object getResource(Object key) {
    return resources.get(key);
  }

  /**
   * Description of the Method
   */
  public void unload() {
  }

}
