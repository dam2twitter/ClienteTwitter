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

import java.awt.Window;
import java.awt.Rectangle;
import java.awt.event.WindowListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Vector;
import javax.swing.SwingConstants;

/**
 * WindowSnapping. <br>
 * Created on 15/06/2000 by Frederic Lavigne, fred@L2FProd.com
 *
 * @author    $Author: l2fprod $
 * @created   27 avril 2002
 * @version   $Revision: 1.4 $, $Date: 2002/04/27 11:45:57 $
 */
public class WindowSnapping {

  static SnapListener sharedSnap = new SnapListener();

  /**
   * Description of the Method
   *
   * @param snap      Description of Parameter
   * @param position  Description of Parameter
   */
  public static void snap(Window snap, int position) {
    // snap to active window
    sharedSnap.snaps.addElement(new Snap(snap, position));
  }

  /**
   * Description of the Method
   *
   * @param snap      Description of Parameter
   * @param position  Description of Parameter
   * @param target    Description of Parameter
   */
  public static void snap(Window snap, int position, Window target) {
    // snap to target
    SnapListener l = new SnapListener();
    l.snaps.addElement(new Snap(snap, position));
    target.addWindowListener(l);
  }

  /**
   * Description of the Method
   *
   * @param target  Description of Parameter
   */
  public static void registerSnapping(Window target) {
    if (target != null) {
      target.addWindowListener(sharedSnap);
    }
  }

  /**
   * Description of the Method
   *
   * @param target  Description of Parameter
   */
  public static void unregisterSnapping(Window target) {
    if (target != null) {
      target.removeWindowListener(sharedSnap);
    }
  }

  /**
   * Description of the Class
   *
   * @author    fred
   * @created   27 avril 2002
   */
  private static class SnapListener extends WindowAdapter implements ComponentListener {
    Vector snaps = new Vector();
    Window lastActivate = null;
    boolean ignoreEvents = false;

    /**
     * Description of the Method
     *
     * @param e  Description of Parameter
     */
    public void windowActivated(WindowEvent e) {
      if (ignoreEvents) {
        // ok the next event will be processed
        ignoreEvents = false;
        return;
      }

      if (lastActivate != null) {
        lastActivate.removeComponentListener(this);
      }
      attachSnapTo(e.getWindow());
      lastActivate = e.getWindow();
      lastActivate.addComponentListener(this);
    }

    /**
     * Description of the Method
     *
     * @param e  Description of Parameter
     */
    public void componentHidden(ComponentEvent e) {
    }

    /**
     * Description of the Method
     *
     * @param e  Description of Parameter
     */
    public void componentMoved(ComponentEvent e) {
      attachSnapTo(lastActivate);
    }

    /**
     * Description of the Method
     *
     * @param e  Description of Parameter
     */
    public void componentShown(ComponentEvent e) {
    }

    /**
     * Description of the Method
     *
     * @param e  Description of Parameter
     */
    public void componentResized(ComponentEvent e) {
    }

    /**
     * Description of the Method
     *
     * @param target  Description of Parameter
     */
    public void attachSnapTo(Window target) {
      synchronized (snaps) {
        // notify the listener that it should not handle the next windowActivateEvent
        ignoreEvents = true;
        for (int i = 0, c = snaps.size(); i < c; i++) {
          ((Snap) snaps.elementAt(i)).attachTo(target);
        }
        // restore focus to target
        target.requestFocus();
        target.toFront();
      }
    }
  }

  /**
   * Description of the Class
   *
   * @author    fred
   * @created   27 avril 2002
   */
  private static class Snap {
    Window snap;
    int position;

    /**
     * Constructor for the Snap object
     *
     * @param snap      Description of Parameter
     * @param position  Description of Parameter
     */
    public Snap(Window snap, int position) {
      this.snap = snap;
      this.position = position;
    }

    /**
     * Description of the Method
     *
     * @param target  Description of Parameter
     */
    public void attachTo(Window target) {
      // i'm a snap window, i should follow my target
      Rectangle targetBounds = target.getBounds();
      int x;
      int y;
      switch (position) {
        default:
        case SwingConstants.NORTH_WEST:
          // top left corner
          x = targetBounds.x;
          y = targetBounds.y - snap.getSize().height;
          break;
        case SwingConstants.NORTH_EAST:
          // top right corner
          x = targetBounds.x + targetBounds.width - snap.getSize().width;
          y = targetBounds.y - snap.getSize().height;
          break;
        case SwingConstants.SOUTH_WEST:
          // bottom left corner
          x = targetBounds.x;
          y = targetBounds.y + targetBounds.height;
          break;
        case SwingConstants.SOUTH_EAST:
          x = targetBounds.x + targetBounds.width - snap.getSize().width;
          y = targetBounds.y + targetBounds.height;
          break;
        case SwingConstants.WEST:
          x = targetBounds.x - snap.getSize().width;
          y = targetBounds.y;
          break;
        case SwingConstants.EAST:
          x = targetBounds.x + targetBounds.width;
          y = targetBounds.y;
          break;
      }
      snap.setLocation(x, y);
      //	    snap.toFront();
    }
  }

}

