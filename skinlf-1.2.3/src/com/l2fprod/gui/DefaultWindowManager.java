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
import java.awt.event.*;
import java.beans.PropertyVetoException;
import java.beans.PropertyChangeEvent;
import javax.swing.border.Border;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import com.l2fprod.gui.plaf.skin.SkinUtils;
import com.l2fprod.gui.sound.NoSoundComponent;

/**
 * DefaultWindowManager. <br>
 * This is an implementation of the WindowManager. It currently implements the
 * basic behaviors for managing SkinWindows.
 *
 * @author    $Author: l2fprod $
 * @created   27 avril 2002
 * @version   $Revision: 1.5 $, $Date: 2002/04/27 11:45:57 $
 */
public class DefaultWindowManager extends WindowManager {
  // use ghost window

  int dragMode = DEFAULT_DRAG_MODE;

  private transient Rectangle currentBounds = null;
  private transient Point currentLoc = null;

  private transient Graphics desktopGraphics = null;
  private transient Rectangle desktopBounds = null;
  private transient Rectangle[] floatingItems = {};

  private transient Window ghostWindow = null;
  /**
   * Description of the Field
   */
  public final static String SHADE_BOUNDS_PROPERTY = "windowshadeBounds";

  final static String PREVIOUS_BOUNDS_PROPERTY = "windowpreviousBounds";
  final static String HAS_BEEN_ICONIFIED_PROPERTY = "wasIconOnce";

  final static int DEFAULT_DRAG_MODE = 0;
  //    final static int OUTLINE_DRAG_MODE = 1;
  final static int FASTER_DRAG_MODE = 2;

  private static SkinWindow currentActiveWindow = null;

  /**
   * Sets the BoundsForWindow attribute of the DefaultWindowManager object
   *
   * @param f          The new BoundsForWindow value
   * @param newX       The new BoundsForWindow value
   * @param newY       The new BoundsForWindow value
   * @param newWidth   The new BoundsForWindow value
   * @param newHeight  The new BoundsForWindow value
   */
  public void setBoundsForWindow(final Container f,
      final int newX, final int newY,
      final int newWidth, final int newHeight) {
    Dimension dim = f.getSize();
    boolean didResize = (dim.width != newWidth || dim.height != newHeight);
    f.setBounds(newX, newY, newWidth, newHeight);
    if (didResize) {
      f.validate();
    }
  }

  /**
   * Description of the Method
   *
   * @param f  Description of Parameter
   */
  public void openWindow(SkinWindow f) {
  }

  /**
   * Description of the Method
   *
   * @param f  Description of Parameter
   */
  public void closeWindow(SkinWindow f) {
    removeIconFor(f);
    if (getPreviousBounds(f) != null) {
      setPreviousBounds(f, null);
    }
    if (wasIcon(f)) {
      setWasIcon(f, null);
    }
    SkinWindowList.getSkinWindowList().unregisterWindow(f);
  }

  /**
   * Description of the Method
   *
   * @param f  Description of Parameter
   */
  public void maximizeWindow(SkinWindow f) {
    Rectangle p;
    Dimension newSize = Toolkit.getDefaultToolkit().getScreenSize();
    p = new Rectangle(0, 0, newSize.width, newSize.height);

    if (!f.isIcon()) {
      setPreviousBounds(f, f.getBounds());
    }
    else {
      try {
        f.setIcon(false);
      } catch (PropertyVetoException e2) {
      }
    }
    setBoundsForWindow(f, 0, 0, p.width, p.height);
    try {
      f.setSelected(true);
    } catch (PropertyVetoException e2) {
    }

    removeIconFor(f);
  }

  /**
   * Description of the Method
   *
   * @param f  Description of Parameter
   */
  public void minimizeWindow(SkinWindow f) {
    if (getPreviousBounds(f) != null) {
      Rectangle r = getPreviousBounds(f);
      setPreviousBounds(f, null);
      try {
        f.setSelected(true);
      } catch (PropertyVetoException e2) {
      }
      if (f.isIcon()) {
        try {
          f.setIcon(false);
        } catch (PropertyVetoException e2) {
        }
      }
      setBoundsForWindow(f, r.x, r.y, r.width, r.height);
    }
    removeIconFor(f);
  }

  /**
   * Description of the Method
   *
   * @param w  Description of Parameter
   */
  public void shadeWindow(SkinWindow w) {
    /*
     *  Rectangle p = null;
     *  Rectangle bounds = w.getBounds();
     *  p = new Rectangle(bounds.x, bounds.y, bounds.width, bounds.height);
     *  w.putClientProperty(SHADE_BOUNDS_PROPERTY, p);
     *  setBoundsForWindow(w, p.x, p.y, p.width, w.realFrame.getInsets().top + + w.realFrame.getInsets().bottom + 2);
     */
  }

  /**
   * Description of the Method
   *
   * @param w  Description of Parameter
   */
  public void unshadeWindow(SkinWindow w) {
    /*
     *  Point location = w.getLocation();
     *  Rectangle p = (Rectangle)w.getClientProperty(SHADE_BOUNDS_PROPERTY);
     *  setBoundsForWindow(w, location.x, location.y,
     *  p.width, p.height);
     */
  }

  /**
   * Description of the Method
   *
   * @param f  Description of Parameter
   */
  public void iconifyWindow(SkinWindow f) {
    boolean findNext = f.isSelected();

    try {
      f.setSelected(false);
    } catch (PropertyVetoException e2) {
    }
    f.setVisible(false);

    if (findNext) {
      SkinWindowList.getSkinWindowList().activateNextWindow(f);
    }

    /*
     *  Window.JDesktopIcon desktopIcon;
     *  Container c;
     *  desktopIcon = f.getDesktopIcon();
     *  if(!wasIcon(f)) {
     *  Rectangle r = getBoundsForIconOf(f);
     *  desktopIcon.setBounds(r.x, r.y, r.width, r.height);
     *  setWasIcon(f, Boolean.TRUE);
     *  }
     *  c = f.getParent();
     *  if (c instanceof JLayeredPane) {
     *  JLayeredPane lp = (JLayeredPane)c;
     *  int layer = lp.getLayer(f);
     *  lp.putLayer(desktopIcon, layer);
     *  }
     *  c.remove(f);
     *  c.add(desktopIcon);
     *  c.repaint(f.getX(), f.getY(), f.getWidth(), f.getHeight());
     *  try { f.setSelected(false); } catch (PropertyVetoException e2) { }
     */
  }

  /**
   * Description of the Method
   *
   * @param f  Description of Parameter
   */
  public void deiconifyWindow(SkinWindow f) {
    f.setVisible(true);
    f.toFront();
    try {
      f.setSelected(true);
    } catch (PropertyVetoException e2) {
    }
    /*
     *  Window.JDesktopIcon desktopIcon;
     *  Dimension size;
     *  desktopIcon = f.getDesktopIcon();
     *  if(desktopIcon.getParent() != null) {
     *  desktopIcon.getParent().add(f);
     *  removeIconFor(f);
     *  }
     */
  }

  /**
   * Description of the Method
   *
   * @param f  Description of Parameter
   */
  public void activateWindow(SkinWindow f) {
    // we only need to keep track of the currentActive InternalFrame, if any
    if (currentActiveWindow == null) {
      currentActiveWindow = f;
    }
    else if (currentActiveWindow != f) {
      // if not the same frame as the current active
      // we deactivate the current
      if (currentActiveWindow.isSelected()) {
        try {
          currentActiveWindow.setSelected(false);
        } catch (PropertyVetoException e2) {
        }
      }
      currentActiveWindow = f;
    }
    f.toFront();
  }

  /**
   * Description of the Method
   *
   * @param f  Description of Parameter
   */
  public void deactivateWindow(SkinWindow f) {
    if (currentActiveWindow == f) {
      currentActiveWindow = null;
    }
  }

  /**
   * Description of the Method
   *
   * @param w  Description of Parameter
   */
  public void beginDraggingWindow(SkinWindow w) {
    setupDragMode(w);
    currentLoc = w.getLocation();
    if (dragMode == FASTER_DRAG_MODE) {
      ghostWindow.setBounds(w.getBounds());
    }
    w.isDragging = true;
  }

  /**
   * Description of the Method
   *
   * @param w     Description of Parameter
   * @param newX  Description of Parameter
   * @param newY  Description of Parameter
   */
  public void dragWindow(SkinWindow w, int newX, int newY) {
    currentLoc.x = newX;
    currentLoc.y = newY;
    if (dragMode == FASTER_DRAG_MODE) {
      ghostWindow.setVisible(true);
      ghostWindow.toFront();
      ghostWindow.setLocation(currentLoc.x, currentLoc.y);
    }
    else {
      w.setLocation(currentLoc.x, currentLoc.y);
    }
  }

  /**
   * Description of the Method
   *
   * @param w  Description of Parameter
   */
  public void endDraggingWindow(SkinWindow w) {
    w.isDragging = false;
    if (dragMode == FASTER_DRAG_MODE) {
      ghostWindow.setVisible(false);
      //	    ghostWindow = null;
    }
    if (currentLoc != null) {
      w.setLocation(currentLoc.x, currentLoc.y);
      currentLoc = null;
    }
  }

  /**
   * Description of the Method
   *
   * @param w          Description of Parameter
   * @param direction  Description of Parameter
   */
  public void beginResizingWindow(SkinWindow w, int direction) {
    setupDragMode(w);
    if (dragMode == FASTER_DRAG_MODE) {
      ghostWindow.setBounds(w.getBounds());
      ghostWindow.setVisible(true);
      ghostWindow.toFront();
    }
    w.isDragging = true;
  }

  /**
   * Description of the Method
   *
   * @param w          Description of Parameter
   * @param newX       Description of Parameter
   * @param newY       Description of Parameter
   * @param newWidth   Description of Parameter
   * @param newHeight  Description of Parameter
   */
  public void resizeWindow(SkinWindow w, int newX, int newY, int newWidth, int newHeight) {
    currentBounds = new Rectangle(newX, newY, newWidth, newHeight);
    if (dragMode == FASTER_DRAG_MODE) {
      ghostWindow.setBounds(newX, newY, newWidth, newHeight);
    }
    else {
      setBoundsForWindow(w, newX, newY, newWidth, newHeight);
    }
  }

  /**
   * Description of the Method
   *
   * @param w  Description of Parameter
   */
  public void endResizingWindow(SkinWindow w) {
    w.isDragging = false;
    if (dragMode == FASTER_DRAG_MODE) {
      ghostWindow.setVisible(false);
      ghostWindow = null;
    }
    if (currentBounds != null) {
      setBoundsForWindow(w, currentBounds.x, currentBounds.y,
          currentBounds.width, currentBounds.height);
      currentBounds = null;
    }
  }

  /**
   * Sets the PreviousBounds attribute of the DefaultWindowManager object
   *
   * @param f  The new PreviousBounds value
   * @param r  The new PreviousBounds value
   */
  protected void setPreviousBounds(SkinWindow f, Rectangle r) {
    if (r != null) {
      f.putClientProperty(PREVIOUS_BOUNDS_PROPERTY, r);
    }
  }

  /**
   * Sets that the component has been iconized and the bounds of the desktopIcon
   * are valid.
   *
   * @param f      The new WasIcon value
   * @param value  The new WasIcon value
   */
  protected void setWasIcon(SkinWindow f, Boolean value) {
    if (value != null) {
      f.putClientProperty(HAS_BEEN_ICONIFIED_PROPERTY, value);
    }
  }

  /**
   * The iconifyWindow() code calls this to determine the proper bounds for the
   * desktopIcon.
   *
   * @param f  Description of Parameter
   * @return   The BoundsForIconOf value
   */

  protected Rectangle getBoundsForIconOf(SkinWindow f) {
    return null;
    /*
     *  /
     *  / Get the parent bounds and child components.
     *  /
     *  Container c = f.getParent();
     *  Rectangle parentBounds = c.getBounds();
     *  Component [] components = c.getComponents();
     *  /
     *  / Get the icon for this internal frame and its preferred size
     *  /
     *  Window.JDesktopIcon icon = f.getDesktopIcon();
     *  Dimension prefSize = icon.getPreferredSize();
     *  /
     *  / Iterate through valid default icon locations and return the
     *  / first one that does not intersect any other icons.
     *  /
     *  Rectangle availableRectangle = null;
     *  Window.JDesktopIcon currentIcon = null;
     *  int x = 0;
     *  int y = parentBounds.height - prefSize.height;
     *  int w = prefSize.width;
     *  int h = prefSize.height;
     *  boolean found = false;
     *  while (!found) {
     *  availableRectangle = new Rectangle(x,y,w,h);
     *  found = true;
     *  for ( int i=0; i<components.length; i++ ) {
     *  /
     *  / Get the icon for this component
     *  /
     *  if ( components[i] instanceof Window ) {
     *  currentIcon = ((Window)components[i]).getDesktopIcon();
     *  }
     *  else if ( components[i] instanceof Window.JDesktopIcon ){
     *  currentIcon = (Window.JDesktopIcon)components[i];
     *  }
     *  /
     *  / If this icon intersects the current location, get next location.
     *  /
     *  if ( !currentIcon.equals(icon) ) {
     *  if ( availableRectangle.intersects(currentIcon.getBounds()) ) {
     *  found = false;
     *  break;
     *  }
     *  }
     *  }
     *  x += currentIcon.getBounds().width;
     *  if ( x + w > parentBounds.width ) {
     *  x = 0;
     *  y -= h;
     *  }
     *  }
     *  return(availableRectangle);
     */
  }

  /**
   * Gets the PreviousBounds attribute of the DefaultWindowManager object
   *
   * @param f  Description of Parameter
   * @return   The PreviousBounds value
   */
  protected Rectangle getPreviousBounds(SkinWindow f) {
    return (Rectangle) f.getClientProperty(PREVIOUS_BOUNDS_PROPERTY);
  }

  /**
   * Description of the Method
   *
   * @param f  Description of Parameter
   */
  protected void removeIconFor(SkinWindow f) {
    /*
     *  Window.JDesktopIcon di = f.getDesktopIcon();
     *  Container c = di.getParent();
     *  if(c != null) {
     *  c.remove(di);
     *  c.repaint(di.getX(), di.getY(), di.getWidth(), di.getHeight());
     *  }
     */
  }

  /**
   * Description of the Method
   *
   * @param f  Description of Parameter
   * @return   Description of the Returned Value
   */
  protected boolean wasIcon(SkinWindow f) {
    return (f.getClientProperty(HAS_BEEN_ICONIFIED_PROPERTY) == Boolean.TRUE);
  }

  /**
   * Description of the Method
   *
   * @param f  Description of Parameter
   */
  private void setupDragMode(SkinWindow f) {
    String mode = (String) f.getClientProperty("Window.dragMode");
    if (mode != null && mode.equals("faster")) {
      dragMode = FASTER_DRAG_MODE;
      if (ghostWindow == null) {
        ghostWindow = new NoEventWindow(new Frame("GhostWindow"));
      }
    }
    else {
      dragMode = DEFAULT_DRAG_MODE;
    }
  }

  /**
   * Description of the Class
   *
   * @author    fred
   * @created   27 avril 2002
   */
  class NoEventWindow extends Window implements NoSoundComponent {
    /**
     * Constructor for the NoEventWindow object
     *
     * @param f  Description of Parameter
     */
    public NoEventWindow(Frame f) {
      super(f);
      disableEvents(AWTEvent.WINDOW_EVENT_MASK);
      setBackground(javax.swing.UIManager.getColor("Panel.background"));
      setForeground(javax.swing.UIManager.getColor("Panel.foreground"));
    }

    // draw a border around the window
    /**
     * Description of the Method
     *
     * @param g  Description of Parameter
     */
    public void paint(Graphics g) {
      g.drawRect(0, 0, this.getSize().width - 1, this.getSize().height - 1);
    }
  }

  /*
   *  JDesktopPane getDesktopPane( JComponent frame ) {
   *  JDesktopPane pane = null;
   *  Component c = frame.getParent();
   *  / Find the JDesktopPane
   *  while ( pane == null ) {
   *  if ( c instanceof JDesktopPane ) {
   *  pane = (JDesktopPane)c;
   *  }
   *  else if ( c == null ) {
   *  break;
   *  }
   *  else {
   *  c = c.getParent();
   *  }
   *  }
   *  return pane;
   *  }
   */
}

