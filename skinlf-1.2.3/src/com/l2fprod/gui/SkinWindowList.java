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
package com.l2fprod.gui;

import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.UIManager;

import com.l2fprod.gui.sound.NoSoundComponent;

/**
 * Skin Window List. <BR>
 * Skin Window List acts as Window pager. <BR>
 * It maintains the list of existing windows. When a window is iconified, it can
 * be deiconified by double-clicking its name in the list. <BR>
 * <BR>
 * <BR>
 * <BR>
 * Created on 27/05/2000 by Frederic Lavigne, fred@L2FProd.com
 *
 * @author    $Author: l2fprod $
 * @created   27 avril 2002
 * @version   $Revision: 1.3 $, $Date: 2002/04/27 11:45:57 $
 */
public class SkinWindowList extends SkinWindow implements NoSoundComponent {

  JList list;
  DefaultListModel model;

  static SkinWindowList shared;

  /**
   * Constructor for the SkinWindowList object
   */
  private SkinWindowList() {
    // WindowList should be a property!
    super(UIManager.getString("WindowList.title"));

    getContentPane().add("Center", list = new JList(model = new DefaultListModel()));

    // nor closable nor iconifiable by the user
    setIconifiable(false);
    setClosable(false);

    MouseListener mouseListener =
      new MouseAdapter() {
        public void mouseClicked(MouseEvent event) {
          if (event.getClickCount() == 2) {
            int index = list.locationToIndex(event.getPoint());
            if (index != -1) {
              try {
                ((SkinWindow.SkinDesktopIcon) model.get(index)).getWindow().setIcon(false);
                ((SkinWindow.SkinDesktopIcon) model.get(index)).getWindow().setSelected(true);
              } catch (PropertyVetoException e) {
              }
            }
          }
        }
      };
    list.addMouseListener(mouseListener);

    setSize(200, 200);
  }

  /**
   * Description of the Method
   *
   * @param w  Description of Parameter
   */
  public void registerWindow(SkinWindow w) {
    model.addElement(w.getDesktopIcon());
  }

  /**
   * Description of the Method
   *
   * @param w  Description of Parameter
   */
  public void unregisterWindow(SkinWindow w) {
    model.removeElement(w.getDesktopIcon());
  }

  /**
   * Description of the Method
   *
   * @param w  Description of Parameter
   */
  public void activateNextWindow(SkinWindow w) {
    int index = model.indexOf(w);
    SkinWindow nextWindow = null;
    if (index > 0) {
      // !=-1 and !=0
      // next is
      index = (index + 1 < model.size()) ? index + 1 : 0;
      nextWindow = (SkinWindow) model.get(index);
    }
    else {
      nextWindow = this;
    }
    try {
      nextWindow.setSelected(true);
    } catch (PropertyVetoException e) {
    }
  }

  /**
   * Gets the SkinWindowList attribute of the SkinWindowList class
   *
   * @return   The SkinWindowList value
   */
  public static SkinWindowList getSkinWindowList() {
    if (shared == null) {
      shared = new SkinWindowList();
    }
    return shared;
  }

}

