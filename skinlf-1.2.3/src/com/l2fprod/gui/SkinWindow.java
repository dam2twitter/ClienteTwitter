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

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.plaf.*;
import java.beans.*;
import java.util.Dictionary;
import java.util.Hashtable;

import com.l2fprod.gui.plaf.skin.SkinLookAndFeel;
import com.l2fprod.gui.plaf.skin.SkinWindowTitlePane;
import com.l2fprod.gui.event.*;

/**
 * Skin Window. <BR>
 * By extending javax.swing.JWindow, SkinWindow looks like JInternalFrame when
 * using Skin Look And Feel. <BR>
 * <BR>
 * <BR>
 * <BR>
 * Created on 15/04/2000 by Frederic Lavigne, fred@L2FProd.com
 *
 * @author    $Author: l2fprod $
 * @created   27 avril 2002
 * @version   $Revision: 1.6 $, $Date: 2002/04/27 11:45:57 $
 */
public class SkinWindow extends com.l2fprod.gui.grieves.graphics.JExternalFrame {

  boolean shaded;
  SkinDesktopIcon desktopIcon;

  transient boolean isDragging = false;

  transient SkinWindowListener skinwindowListener;

  /*
   *  static {
   *  / PENDING(fred): this is not the best way to display it
   *  / move the code in show() ??? (ensureWindowListVisible())
   *  / the window list should always be visible
   *  SkinWindowList.getSkinWindowList().setVisible(true);
   *  try { SkinWindowList.getSkinWindowList().setSelected(true);
   *  } catch (PropertyVetoException e) {}
   *  }
   */
  /**
   * Description of the Field
   */
  public final static int NORMAL = 0;
  /**
   * Description of the Field
   */
  public final static int ICONIFIED = 1;

  /**
   * Constructor for the SkinWindow object
   */
  public SkinWindow() {
    super();
  }

  /**
   * Constructor for the SkinWindow object
   *
   * @param title  Description of Parameter
   */
  public SkinWindow(String title) {
    super(title);
  }

  /**
   * Sets the Shaded attribute of the SkinWindow object
   *
   * @param b  The new Shaded value
   */
  public void setShaded(boolean b) {
    Boolean oldValue = shaded ? Boolean.TRUE : Boolean.FALSE;
    Boolean newValue = b ? Boolean.TRUE : Boolean.FALSE;
    shaded = b;
    //        firePropertyChange(com.l2fprod.gui.plaf.skin.Window.IS_SHADED_PROPERTY, oldValue, newValue);
    if (b) {
      fireWindowEvent(new SkinWindowEvent(this, SkinWindowEvent.WINDOW_SHADE));
    }
    else {
      fireWindowEvent(new SkinWindowEvent(this, SkinWindowEvent.WINDOW_UNSHADE));
    }
  }

  /**
   * Sets the state of this frame.
   *
   * @param state  <code>SkinWindow.ICONIFIED</code> if this frame is in iconic
   *      state; <code>SkinWindow.NORMAL</code> if this frame is in normal
   *      state.
   */
  public void setState(int state) {
    switch (state) {
      case SkinWindow.NORMAL:
        try {
          setIcon(false);
        } catch (PropertyVetoException e) {
        }
        break;
      case SkinWindow.ICONIFIED:
        try {
          setIcon(true);
        } catch (PropertyVetoException e) {
        }
        break;
    }
  }

  /**
   * Gets the DesktopIcon attribute of the SkinWindow object
   *
   * @return   The DesktopIcon value
   */
  public SkinDesktopIcon getDesktopIcon() {
    return desktopIcon;
  }

  /**
   * Gets the Shaded attribute of the SkinWindow object
   *
   * @return   The Shaded value
   */
  public boolean isShaded() {
    return shaded;
  }

  /**
   * Gets the state of this frame.
   *
   * @return   <code>Frame.ICONIFIED</code> if frame in iconic state; <code>Frame.NORMAL</code>
   *      if frame is in normal state.
   */
  public int getState() {
    if (isIcon()) {
      return SkinWindow.ICONIFIED;
    }
    else {
      return SkinWindow.NORMAL;
    }
  }

  /**
   * Returns the value of the property with the specified key. Only properties
   * added with <code>putClientProperty</code> will return a non-null value.
   *
   * @param key  Description of Parameter
   * @return     the value of this property or null
   * @see        #putClientProperty
   */
  public final Object getClientProperty(Object key) {
    return realFrame.getClientProperty(key);
  }

  /**
   * Add an arbitrary key/value "client property" to this component. <p>
   *
   * The <code>get/putClientProperty<code> methods provide access to
   * a small per-instance hashtable. Callers can use get/putClientProperty
   * to annotate components that were created by another module, e.g. a
   * layout manager might store per child constraints this way.  For example:
   * <pre>
   * componentA.putClientProperty("to the left of", componentB);
   * </pre> <p>
   *
   * If value is null this method will remove the property. Changes to client
   * properties are reported with PropertyChange events. The name of the
   * property (for the sake of PropertyChange events) is <code>key.toString()</code>
   * . <p>
   *
   * The clientProperty dictionary is not intended to support large scale
   * extensions to JComponent nor should be it considered an alternative to
   * subclassing when designing a new component.
   *
   * @param key    Description of Parameter
   * @param value  Description of Parameter
   * @see          #getClientProperty
   * @see          #addPropertyChangeListener
   */
  public final void putClientProperty(Object key, Object value) {
    realFrame.putClientProperty(key, value);
  }

  /**
   * Adds the specified window listener to receive window events from this
   * window. If l is null, no exception is thrown and no action is performed.
   *
   * @param l  the window listener
   */
  public synchronized void addWindowListener(SkinWindowListener l) {
    if (l == null) {
      return;
    }
    skinwindowListener = SkinEventMulticaster.add(skinwindowListener, l);
    super.addWindowListener(l);
  }

  /**
   * Removes the specified window listener so that it no longer receives window
   * events from this window. If l is null, no exception is thrown and no action
   * is performed.
   *
   * @param l  the window listener
   */
  public synchronized void removeWindowListener(SkinWindowListener l) {
    if (l == null) {
      return;
    }
    skinwindowListener = SkinEventMulticaster.remove(skinwindowListener, l);
    super.removeWindowListener(l);
  }

  /*
   *  protected void windowInit() {
   *  super.windowInit();
   *  shaded = false;
   *  desktopIcon = new SkinDesktopIcon(this);
   *  if (this instanceof SkinWindowList == false) {
   *  SkinWindowList.getSkinWindowList().registerWindow(this);
   *  }
   *  putClientProperty("Window.dragMode", "faster");
   *  }
   */
  /**
   * Description of the Method
   *
   * @exception Throwable  Description of Exception
   */
  protected void finalize() throws Throwable {
    // is this really good ?
    // windowlist retains an instance of window
    // so "this" can't be garbage collected ?
    // finalize is never called ?
    SkinWindowList.getSkinWindowList().unregisterWindow(this);
    super.finalize();
  }

  /**
   * Description of the Method
   *
   * @param id  Description of Parameter
   */
  protected synchronized void fireWindowEvent(int id) {
    fireWindowEvent(new WindowEvent(this, id));
  }

  /**
   * Description of the Method
   *
   * @param event  Description of Parameter
   */
  protected synchronized void fireWindowEvent(AWTEvent event) {
    Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(event);
  }

  /**
   * Processes window events occurring on this component. Hides the window or
   * disposes of it, as specified by the setting of the <code>defaultCloseOperation</code>
   * property.
   *
   * @param e  the window event
   * @see      #setDefaultCloseOperation
   * @see      java.awt.Window#processWindowEvent
   */
  protected void processWindowEvent(WindowEvent e) {
    if (skinwindowListener != null) {
      switch (e.getID()) {
        case SkinWindowEvent.WINDOW_SHADE:
          skinwindowListener.windowShaded((SkinWindowEvent) e);
          break;
        case SkinWindowEvent.WINDOW_UNSHADE:
          skinwindowListener.windowUnshaded((SkinWindowEvent) e);
          break;
        default:
          super.processWindowEvent(e);
      }
    }

  }

  /**
   * Description of the Class
   *
   * @author    fred
   * @created   27 avril 2002
   */
  public class SkinDesktopIcon extends JComponent {
    SkinWindow window;

    /**
     * Constructor for the SkinDesktopIcon object
     *
     * @param window  Description of Parameter
     */
    public SkinDesktopIcon(SkinWindow window) {
      this.window = window;
    }

    //	public String getUIClassID() { return "SkinDesktopIconUI"; }
    /**
     * Gets the Window attribute of the SkinDesktopIcon object
     *
     * @return   The Window value
     */
    public SkinWindow getWindow() {
      return window;
    }

    /**
     * Description of the Method
     *
     * @return   Description of the Returned Value
     */
    public String toString() {
      return window.getTitle();
    }
  }

}
