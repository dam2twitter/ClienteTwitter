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
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author    $Author: l2fprod $
 * @created   27 avril 2002
 * @version   $Revision: 1.8 $, $Date: 2002/07/28 16:50:16 $
 */
public class SkinInternalFrameUI extends BasicInternalFrameUI {

  private Skin skin = SkinLookAndFeel.getSkin();

  /**
   * Constructor for the SkinInternalFrameUI object
   *
   * @param b  Description of Parameter
   */
  public SkinInternalFrameUI(JInternalFrame b) {
    super(b);
  }

  /**
   * Description of the Method
   *
   * @param c  Description of Parameter
   */
  public void installUI(JComponent c) {
    super.installUI(c);
    skin.getFrame().installSkin(c);
  }

  /**
   * Description of the Method
   *
   * @param w  Description of Parameter
   * @return   Description of the Returned Value
   */
  protected JComponent createNorthPane(JInternalFrame w) {
    titlePane = new SkinTitlePane(w);
    return titlePane;
  }

  /**
   * Description of the Method
   *
   * @param w  Description of Parameter
   * @return   Description of the Returned Value
   */
  protected MouseInputAdapter createBorderListener(JInternalFrame w) {
    return new MyBorderListener();
  }

  /**
   * Description of the Method
   *
   * @param b  Description of Parameter
   * @return   Description of the Returned Value
   */
  public static ComponentUI createUI(JComponent b) {
    return new SkinInternalFrameUI((JInternalFrame) b);
  }

  /**
   * Description of the Class
   *
   * @author    fred
   * @created   27 avril 2002
   */
  protected class MyBorderListener extends BorderListener {

    /**
     * Description of the Method
     *
     * @param e  Description of Parameter
     */
    public void mouseMoved(MouseEvent e) {
      JComponent pane = getNorthPane();
      if (pane instanceof SkinTitlePane && ((SkinTitlePane) pane).getWindow().isShaded()) {
        return;
      }
      super.mouseMoved(e);
    }

    /**
     * Description of the Method
     *
     * @param e  Description of Parameter
     */
    public void mouseDragged(MouseEvent e) {
      try {
        super.mouseDragged(e);
      } catch (NullPointerException ex) {
        // the nullpointerexception may be thrown by bug id 4383371
        // if parentBounds is not set
        // sending the frame back and front should fix the problem as an Ancestor propertychangeevent
        // will be sent
        // this bug has been fixed in JDK1.3
        // http://developer.java.sun.com/developer/bugParade/bugs/4383371.html
        ((JInternalFrame) e.getSource()).toBack();
        ((JInternalFrame) e.getSource()).toFront();
      }
    }
  }

  protected LayoutManager createLayoutManager(){
    return new MyInternalFrameLayout();
  }

  protected class MyInternalFrameLayout extends InternalFrameLayout {
    public Dimension minimumLayoutSize(Container c) {
      // The minimum size of the internal frame only takes into account the
      // title pane since you are allowed to resize the frames to the point
      // where just the title pane is visible.
      Dimension result = new Dimension();
      if (getNorthPane() != null &&
          getNorthPane() instanceof SkinTitlePane) {
        result = new Dimension(getNorthPane().getMinimumSize());
      }
      Insets i = frame.getInsets();
      result.width += i.left + i.right;
      result.height += i.top + i.bottom;
      
      return result;
    }
  }

}
