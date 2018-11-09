/* ====================================================================
 *
 * @PROJECT.FULLNAME@ @VERSION@ License.
 *
 * Copyright (c) @YEAR@ L2FProd.com.  All rights reserved.
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
 * 4. The names "@PROJECT.FULLNAME@", "SkinLF" and "L2FProd.com" must not
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
import java.awt.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.*;
import java.awt.image.*;

/**
 * Description of the Class
 *
 * @author    fred
 * @created   27 avril 2002
 */
public class VerticalLayout implements LayoutManager {

  int gap = 0;

  /**
   * Constructor for the VerticalLayout object
   */
  public VerticalLayout() {
  }

  /**
   * Constructor for the VerticalLayout object
   *
   * @param gap  Description of Parameter
   */
  public VerticalLayout(int gap) {
    this.gap = gap;
  }

  /**
   * Adds a feature to the LayoutComponent attribute of the VerticalLayout
   * object
   *
   * @param name  The feature to be added to the LayoutComponent attribute
   * @param c     The feature to be added to the LayoutComponent attribute
   */
  public void addLayoutComponent(String name, Component c) {
  }

  /**
   * Description of the Method
   *
   * @param parent  Description of Parameter
   */
  public void layoutContainer(Container parent) {
    Insets insets = parent.getInsets();
    Dimension size = parent.getSize();
    int width = size.width - insets.left - insets.right;
    int height = insets.top;

    for (int i = 0, c = parent.getComponentCount(); i < c; i++) {
      Component m = parent.getComponent(i);
      if (m.isVisible()) {
        m.setBounds(insets.left, height, width, m.getPreferredSize().height);
        height += m.getSize().height + gap;
      }
    }
  }

  /**
   * Description of the Method
   *
   * @param parent  Description of Parameter
   * @return        Description of the Returned Value
   */
  public Dimension minimumLayoutSize(Container parent) {
    return preferredLayoutSize(parent);
  }

  /**
   * Description of the Method
   *
   * @param parent  Description of Parameter
   * @return        Description of the Returned Value
   */
  public Dimension preferredLayoutSize(Container parent) {
    Insets insets = parent.getInsets();
    Dimension pref = new Dimension(0, 0);

    for (int i = 0, c = parent.getComponentCount(); i < c; i++) {
      Component m = parent.getComponent(i);
      if (m.isVisible()) {
        pref.height += parent.getComponent(i).getPreferredSize().height + gap;
        pref.width = Math.max(pref.width, parent.getComponent(i).getPreferredSize().width);
      }
    }

    pref.height += insets.top + insets.bottom;

    return pref;
  }

  /**
   * Description of the Method
   *
   * @param c  Description of Parameter
   */
  public void removeLayoutComponent(Component c) {
  }

}
