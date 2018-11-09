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
import javax.swing.border.*;
import javax.swing.event.*;
import java.util.EventListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.beans.VetoableChangeListener;
import java.beans.PropertyVetoException;

import com.l2fprod.gui.SkinWindow;
import com.l2fprod.gui.WindowManager;

/**
 * The class that manages a basic title bar
 *
 * @author    fred
 * @created   27 avril 2002
 */
public class SkinWindowTitlePane extends SkinTitlePane {

  /**
   * Description of the Field
   */
  protected MouseInputListener borderListener;

  /**
   * Constructor for the SkinWindowTitlePane object
   *
   * @param f  Description of Parameter
   */
  public SkinWindowTitlePane(SkinWindow f) {
    super(f);
  }

  /**
   * Description of the Method
   */
  protected void installListeners() {
    super.installListeners();
    borderListener = new BorderListener();
    frame.getContainer().addMouseListener(borderListener);
    frame.getContainer().addMouseMotionListener(borderListener);

    addMouseListener(borderListener);
    addMouseMotionListener(borderListener);

    //Container contentPane = ((JWindow)frame.getContainer()).getContentPane();
    //contentPane.addMouseListener(borderListener);
    //contentPane.addMouseMotionListener(borderListener);

    MouseInputListener glassPaneDispatcher = createGlassPaneDispatcher();
    ((JWindow) frame.getContainer()).getGlassPane().addMouseListener(glassPaneDispatcher);
    ((JWindow) frame.getContainer()).getGlassPane().addMouseMotionListener(glassPaneDispatcher);
  }

  /**
   * Description of the Method
   *
   * @return   Description of the Returned Value
   */
  protected PropertyChangeListener createPropertyChangeListener() {
    return new PropertyChangeHandler();
  }

  /**
   * Description of the Method
   *
   * @return   Description of the Returned Value
   */
  protected MouseInputListener createGlassPaneDispatcher() {
    return new GlassPaneDispatcher();
  }

  /**
   * Description of the Class
   *
   * @author    fred
   * @created   27 avril 2002
   */
  public class PropertyChangeHandler implements PropertyChangeListener {
    /**
     * Description of the Method
     *
     * @param evt  Description of Parameter
     */
    public void propertyChange(PropertyChangeEvent evt) {
      SkinWindow f = (SkinWindow) evt.getSource();
      String prop = evt.getPropertyName();
      Object newValue = evt.getNewValue();
      Object oldValue = evt.getOldValue();

      if (JInternalFrame.IS_CLOSED_PROPERTY.equals(prop)) {
        if (newValue == Boolean.TRUE) {
          WindowManager.getWindowManager().closeWindow(f);
        }
      }
      else if (JInternalFrame.IS_MAXIMUM_PROPERTY.equals(prop)) {
        if (newValue == Boolean.TRUE) {
          WindowManager.getWindowManager().maximizeWindow(f);
        }
        else {
          WindowManager.getWindowManager().minimizeWindow(f);
        }
      }
      else if (JInternalFrame.IS_ICON_PROPERTY.equals(prop)) {
        if (newValue == Boolean.TRUE) {
          WindowManager.getWindowManager().iconifyWindow(f);
        }
        else {
          WindowManager.getWindowManager().deiconifyWindow(f);
        }
      }
      else if (JInternalFrame.IS_SELECTED_PROPERTY.equals(prop)) {
        Component glassPane = f.getGlassPane();
        if (newValue == Boolean.TRUE && oldValue == Boolean.FALSE) {
          WindowManager.getWindowManager().activateWindow(f);
          //	glassPane.removeMouseListener(glassPaneDispatcher);
          //	glassPane.removeMouseMotionListener(glassPaneDispatcher);
          glassPane.setVisible(false);
        }
        else if (newValue == Boolean.FALSE && oldValue == Boolean.TRUE) {
          WindowManager.getWindowManager().deactivateWindow(f);
          //	glassPane.addMouseListener(glassPaneDispatcher);
          //	glassPane.addMouseMotionListener(glassPaneDispatcher);
          glassPane.setVisible(true);
        }
      }
      else if (Window.IS_SHADED_PROPERTY.equals(prop)) {
        if (newValue == Boolean.TRUE) {
          WindowManager.getWindowManager().shadeWindow(f);
        }
        else {
          WindowManager.getWindowManager().unshadeWindow(f);
        }
      }

      enableActions();
    }
  }

  /**
   * Description of the Class
   *
   * @author    fred
   * @created   27 avril 2002
   */
  protected class GlassPaneDispatcher implements MouseInputListener {

    private Component mouseEventTarget = null;

    /**
     * Description of the Method
     *
     * @param e  Description of Parameter
     */
    public void mousePressed(MouseEvent e) {
      if (borderListener != null) {
        borderListener.mousePressed(e);
      }
      forwardMouseEvent(e);
    }

    /**
     * Description of the Method
     *
     * @param e  Description of Parameter
     */
    public void mouseEntered(MouseEvent e) {
      forwardMouseEvent(e);
    }

    /**
     * Description of the Method
     *
     * @param e  Description of Parameter
     */
    public void mouseMoved(MouseEvent e) {
      forwardMouseEvent(e);
    }

    /**
     * Description of the Method
     *
     * @param e  Description of Parameter
     */
    public void mouseExited(MouseEvent e) {
      forwardMouseEvent(e);
    }

    /**
     * Description of the Method
     *
     * @param e  Description of Parameter
     */
    public void mouseClicked(MouseEvent e) {
    }

    /**
     * Description of the Method
     *
     * @param e  Description of Parameter
     */
    public void mouseReleased(MouseEvent e) {
      forwardMouseEvent(e);
    }

    /**
     * Description of the Method
     *
     * @param e  Description of Parameter
     */
    public void mouseDragged(MouseEvent e) {
    }

    /*
     *  Dispatch an event clone, retargeted for the current mouse target.
     */
    /**
     * Description of the Method
     *
     * @param id  Description of Parameter
     * @param e   Description of Parameter
     */
    void retargetMouseEvent(int id, MouseEvent e) {
      Point p = SwingUtilities.convertPoint(((JWindow) frame.getContainer()).getContentPane(),
          e.getX(), e.getY(),
          mouseEventTarget);
      MouseEvent retargeted = new MouseEvent(mouseEventTarget,
          id,
          e.getWhen(),
          e.getModifiers(),
          p.x,
          p.y,
          e.getClickCount(),
          e.isPopupTrigger());
      mouseEventTarget.dispatchEvent(retargeted);
    }

    /*
     *  Set the child component to which events are forwarded, and
     *  synthesize the appropriate mouseEntered and mouseExited events.
     */
    /**
     * Sets the MouseTarget attribute of the GlassPaneDispatcher object
     *
     * @param target  The new MouseTarget value
     * @param e       The new MouseTarget value
     */
    private void setMouseTarget(Component target, MouseEvent e) {
      if (mouseEventTarget != null) {
        retargetMouseEvent(MouseEvent.MOUSE_EXITED, e);
      }
      mouseEventTarget = target;
      if (mouseEventTarget != null) {
        retargetMouseEvent(MouseEvent.MOUSE_ENTERED, e);
      }
    }

    /**
     * Description of the Method
     *
     * @param e  Description of Parameter
     */
    private void forwardMouseEvent(MouseEvent e) {
      Component target = SkinUtils.findComponentAt(((JWindow) frame.getContainer()).getContentPane(), e.getX(), e.getY());
      if (target != null) {
        if (target != mouseEventTarget) {
          setMouseTarget(target, e);
        }
        retargetMouseEvent(e.getID(), e);
      }
    }

  }

  /**
   * Description of the Class
   *
   * @author    fred
   * @created   27 avril 2002
   */
  protected class BorderListener extends MouseInputAdapter implements SwingConstants {

    /**
     * Description of the Field
     */
    protected final int NO_ACTION = -1;
    /**
     * Description of the Field
     */
    protected final int RESIZE_NONE = 0;
    int startX, startY;

    Rectangle startingBounds;
    Rectangle newBounds = new Rectangle();
    int resizeDir;

    int resizeCornerSize = 5;

    /**
     * Description of the Method
     *
     * @param e  Description of Parameter
     */
    public void mouseClicked(MouseEvent e) {
      if (e.getClickCount() > 1 && e.getSource() == SkinWindowTitlePane.this) {
        if (frame.isIconifiable() && frame.isIcon()) {
          try {
            frame.setIcon(false);
          } catch (PropertyVetoException e2) {
          }
        }
        else if (frame.isMaximizable()) {
          if (!frame.isMaximum()) {
            try {
              frame.setMaximum(true);
            } catch (PropertyVetoException e2) {
            }
          }
          else {
            try {
              frame.setMaximum(false);
            } catch (PropertyVetoException e3) {
            }
          }
        }
      }
    }

    /**
     * Description of the Method
     *
     * @param e  Description of Parameter
     */
    public void mouseReleased(MouseEvent e) {
      switch (resizeDir) {
        case NO_ACTION:
          break;
        case RESIZE_NONE:
          WindowManager.getWindowManager().endDraggingWindow((SkinWindow) frame.getContainer());
          break;
        default:
          WindowManager.getWindowManager().endResizingWindow((SkinWindow) frame.getContainer());
      }

      startingBounds = null;
      resizeDir = NO_ACTION;
    }

    /**
     * Description of the Method
     *
     * @param e  Description of Parameter
     */
    public void mousePressed(MouseEvent e) {
      startX = e.getX();
      startY = e.getY();

      startingBounds = frame.getContainer().getBounds();

      if (!frame.isSelected()) {
        try {
          frame.setSelected(true);
        } catch (PropertyVetoException e1) {
        }
      }
      if ((!frame.isResizable() || frame.isShaded()) || e.getSource() == SkinWindowTitlePane.this) {
        resizeDir = RESIZE_NONE;
        WindowManager.getWindowManager().beginDraggingWindow((SkinWindow) frame.getContainer());
        return;
      }

      if (e.getSource() == frame.getContainer()) {
        Dimension dim = frame.getContainer().getSize();
        if (e.getX() <= resizeCornerSize) {
          if (e.getY() < resizeCornerSize) {
            resizeDir = NORTH_WEST;
          }
          else if (e.getY() > dim.height - resizeCornerSize) {
            resizeDir = SOUTH_WEST;
          }
          else {
            resizeDir = WEST;
          }
        }
        else if (e.getX() >= dim.width - resizeCornerSize) {
          if (e.getY() < resizeCornerSize) {
            resizeDir = NORTH_EAST;
          }
          else if (e.getY() > dim.height - resizeCornerSize) {
            resizeDir = SOUTH_EAST;
          }
          else {
            resizeDir = EAST;
          }
        }
        else if (e.getY() <= resizeCornerSize) {
          if (e.getX() < resizeCornerSize) {
            resizeDir = NORTH_WEST;
          }
          else if (e.getX() > dim.width - resizeCornerSize) {
            resizeDir = NORTH_EAST;
          }
          else {
            resizeDir = NORTH;
          }
        }
        else if (e.getY() >= dim.height - resizeCornerSize) {
          if (e.getX() < resizeCornerSize) {
            resizeDir = SOUTH_WEST;
          }
          else if (e.getX() > dim.width - resizeCornerSize) {
            resizeDir = SOUTH_EAST;
          }
          else {
            resizeDir = SOUTH;
          }
        }
        if ((resizeDir != RESIZE_NONE) && (resizeDir != NO_ACTION)) {
          WindowManager.getWindowManager().beginResizingWindow((SkinWindow) frame.getContainer(),
              resizeDir);
        }
        return;
      }
    }

    /**
     * Description of the Method
     *
     * @param e  Description of Parameter
     */
    public void mouseDragged(MouseEvent e) {

      if (startingBounds == null) {
        return;
      }

      Point p;
      int newX;
      int newY;
      int newW;
      int newH;
      int deltaX;
      int deltaY;
      Dimension min;
      Dimension max;
      p = SwingUtilities.convertPoint((Component) e.getSource(),
          e.getX(), e.getY(), null);

      deltaX = e.getX() - startX;
      deltaY = e.getY() - startY;

      p = frame.getContainer().getLocation();

      // Handle a MOVE
      if (e.getSource() == SkinWindowTitlePane.this) {
        if (frame.isMaximum()) {
          return;
          // don't allow moving of maximized frames.
        }

        WindowManager.getWindowManager().dragWindow((SkinWindow) frame.getContainer(),
            p.x + deltaX, p.y + deltaY);

        /*
         *  Insets i = frame.getContainer().getInsets();
         *  int pWidth, pHeight;
         *  Dimension s = frame.getContainer().getSize();
         *  pWidth = s.width;
         *  pHeight = s.height;
         *  newX = startingBounds.x - (_x - p.x);
         *  newY = startingBounds.y - (_y - p.y);
         *  / Make sure we stay in-bounds
         *  if(newX + i.left <= -__x)
         *  newX = -__x - i.left;
         *  if(newY + i.top <= -__y)
         *  newY = -__y - i.top;
         *  if(newX + __x + i.right > pWidth)
         *  newX = pWidth - __x - i.right;
         *  if(newY + __y + i.bottom > pHeight)
         *  newY =  pHeight - __y - i.bottom;
         */
        return;
      }

      if (!frame.isResizable() || frame.isShaded()) {
        return;
      }

      min = frame.getContainer().getMinimumSize();
      max = frame.getContainer().getMaximumSize();

      newX = p.x;
      newY = p.y;
      newW = frame.getContainer().getSize().width;
      newH = frame.getContainer().getSize().height;

      switch (resizeDir) {
        case RESIZE_NONE:
          return;
        case NORTH:
          /*
           *  if(startingBounds.height + deltaY < min.height)
           *  deltaY = -(startingBounds.height - min.height);
           *  else if(startingBounds.height + deltaY > max.height)
           *  deltaY = (startingBounds.height - min.height);
           */
          newX = startingBounds.x;
          newY = startingBounds.y + deltaY;
          newW = startingBounds.width;
          newH = startingBounds.height - deltaY;
          break;
        case NORTH_EAST:
          /*
           *  if(startingBounds.height + deltaY < min.height)
           *  deltaY = -(startingBounds.height - min.height);
           *  else if(startingBounds.height + deltaY > max.height)
           *  deltaY = (startingBounds.height - min.height);
           *  if(startingBounds.width - deltaX < min.width)
           *  deltaX = (startingBounds.width - min.width);
           *  else if(startingBounds.width - deltaX > max.width)
           *  deltaX = -(startingBounds.width - min.width);
           */
          newX = startingBounds.x;
          newY = startingBounds.y + deltaY;
          newW = startingBounds.width + deltaX;
          newH = startingBounds.height - deltaY;
          break;
        case EAST:
          /*
           *  if(startingBounds.width - deltaX < min.width)
           *  deltaX = (startingBounds.width - min.width);
           *  else if(startingBounds.width - deltaX > max.width)
           *  deltaX = -(startingBounds.width - min.width);
           */
          newW = startingBounds.width + deltaX;
          newH = startingBounds.height;
          break;
        case SOUTH_EAST:
          /*
           *  if(startingBounds.width - deltaX < min.width)
           *  deltaX = (startingBounds.width - min.width);
           *  else if(startingBounds.width - deltaX > max.width)
           *  deltaX = -(startingBounds.width - min.width);
           *  if(startingBounds.height - deltaY < min.height)
           *  deltaY = (startingBounds.height - min.height);
           *  else if(startingBounds.height - deltaY > max.height)
           *  deltaY = -(startingBounds.height - min.height);
           */
          newW = startingBounds.width + deltaX;
          newH = startingBounds.height + deltaY;
          break;
        case SOUTH:
          /*
           *  if(startingBounds.height - deltaY < min.height)
           *  deltaY = (startingBounds.height - min.height);
           *  else if(startingBounds.height - deltaY > max.height)
           *  deltaY = -(startingBounds.height - min.height);
           */
          newW = startingBounds.width;
          newH = startingBounds.height + deltaY;
          break;
        case SOUTH_WEST:
          /*
           *  if(startingBounds.height - deltaY < min.height)
           *  deltaY = (startingBounds.height - min.height);
           *  else if(startingBounds.height - deltaY > max.height)
           *  deltaY = -(startingBounds.height - min.height);
           *  if(startingBounds.width + deltaX < min.width)
           *  deltaX = -(startingBounds.width - min.width);
           *  else if(startingBounds.width + deltaX > max.width)
           *  deltaX = (startingBounds.width - min.width);
           */
          newX = startingBounds.x + deltaX;
          newY = startingBounds.y;
          newW = startingBounds.width - deltaX;
          newH = startingBounds.height + deltaY;
          break;
        case WEST:
          /*
           *  if(startingBounds.width + deltaX < min.width)
           *  deltaX = -(startingBounds.width - min.width);
           *  else if(startingBounds.width + deltaX > max.width)
           *  deltaX = (startingBounds.width - min.width);
           */
          newX = startingBounds.x + deltaX;
          newY = startingBounds.y;
          newW = startingBounds.width - deltaX;
          newH = startingBounds.height;
          break;
        case NORTH_WEST:
          /*
           *  if(startingBounds.width + deltaX < min.width)
           *  deltaX = -(startingBounds.width - min.width);
           *  else if(startingBounds.width + deltaX > max.width)
           *  deltaX = (startingBounds.width - min.width);
           *  if(startingBounds.height + deltaY < min.height)
           *  deltaY = -(startingBounds.height - min.height);
           *  else if(startingBounds.height + deltaY > max.height)
           *  deltaY = (startingBounds.height - min.height);
           */
          newX = startingBounds.x + deltaX;
          newY = startingBounds.y + deltaY;
          newW = startingBounds.width - deltaX;
          newH = startingBounds.height - deltaY;
          break;
        default:
          return;
      }

      newBounds.x = newX;
      newBounds.y = newY;
      newBounds.width = newW;
      newBounds.height = newH;

      WindowManager.getWindowManager().resizeWindow((SkinWindow) frame.getContainer(),
          newX, newY, newW, newH);
    }

    /**
     * Description of the Method
     *
     * @param e  Description of Parameter
     */
    public void mouseMoved(MouseEvent e) {
      if (!frame.isResizable()) {
        return;
      }

      if (e.getSource() == frame.getContainer()) {
        Component comp = frame.getContainer();
        Dimension dim = comp.getSize();
        if (e.getX() <= resizeCornerSize) {
          if (e.getY() < resizeCornerSize) {
            comp.setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
          }
          else if (e.getY() > dim.height - resizeCornerSize) {
            comp.setCursor(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));
          }
          else {
            comp.setCursor(Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR));
          }
        }
        else if (e.getX() >= dim.width - resizeCornerSize) {
          if (e.getY() < resizeCornerSize) {
            comp.setCursor(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));
          }
          else if (e.getY() > dim.height - resizeCornerSize) {
            comp.setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
          }
          else {
            comp.setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
          }
        }
        else if (e.getY() <= resizeCornerSize) {
          if (e.getX() < resizeCornerSize) {
            comp.setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
          }
          else if (e.getX() > dim.width - resizeCornerSize) {
            comp.setCursor(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));
          }
          else {
            comp.setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
          }
        }
        else if (e.getY() >= dim.height - resizeCornerSize) {
          if (e.getX() < resizeCornerSize) {
            comp.setCursor(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));
          }
          else if (e.getX() > dim.width - resizeCornerSize) {
            comp.setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
          }
          else {
            comp.setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
          }
        }
        else {
          comp.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        return;
      }

      frame.getContainer().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    /**
     * Description of the Method
     *
     * @param e  Description of Parameter
     */
    public void mouseExited(MouseEvent e) {
      frame.getContainer().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }
  }
  /// End BorderListener Class

}
// End Title Pane Class

