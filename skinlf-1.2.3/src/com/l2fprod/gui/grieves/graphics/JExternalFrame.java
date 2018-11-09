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
package com.l2fprod.gui.grieves.graphics;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyVetoException;
import java.util.Vector;
import javax.swing.*;
import javax.swing.event.*;

/**
 * <pre>
 * Do not remove these comments under penalty of my displeasure.
 * <p>
 *
 * I'm not smart like those GNU guys, so I'll just say briefly that you can
 * pretty much do what you want with this software, except: 1. Sue me if it
 * doesn't work or causes you any problems. 2. Claim that you (or anyone other
 * than me) wrote it. 3. Sell it to anyone. <p>
 *
 * See <a href="http://www.enteract.com/~grieves/">my website</a> for more info
 * on JExternalFrame. <p>
 *
 * JExternalFrame tries to combine the functionality and pluggable L & F of a
 * JInternalFrame with the top-level status of a Window or Frame. You use it
 * just like a JFrame (except I wouldn't recommend doing any fancy stuff with
 * setContentPane, setGlassPane, or setLayeredPane.) Call getContentPane() and
 * set the layout, add components, etc. Then, throw it out on your screen
 * somewhere with a pack() and a setVisible(true). <p>
 *
 * You can also programmatically minimize it & iconize it just like a
 * JInternalFrame using setIcon() & setMaximum(). Unfortunately, since it's
 * really just a JWindow in disguise, it won't actually go down to the task bar
 * in Windows 95/98/NT like a JFrame will. <p>
 *
 * Because the window is drawn using a JInternalFrame, it will use whatever your
 * current Look & Feel is for a JInternalFrame. This means it can look like a
 * Metal Frame. Pretty cool, huh? </pre>
 *
 * @author    Daniel P Grieves
 * @created   27 avril 2002
 * @version   f
 * @see       javax.swing.JInternalFrame
 */

public class JExternalFrame extends JWindow implements WindowConstants {
  //******************************** Ends Constants **************************


  //******************************** Attributes ******************************
  /**
   * Description of the Field
   */
  protected JInternalFrame realFrame;
  private JDesktopPane framePane;
  private boolean iconified = false;
  //Ends class FocusKeeper
  //******************************** Ends Inner Classes **********************


  //******************************** Constants *******************************
  /**
   * Used for maximizing the frame
   */
  public final static int SCREEN_WIDTH =
      Toolkit.getDefaultToolkit().getScreenSize().width;
  /**
   * Used for maximizing the frame
   */
  public final static int SCREEN_HEIGHT =
      Toolkit.getDefaultToolkit().getScreenSize().height;

  //******************************** Ends Attributes *************************

  //******************************** Constructors ****************************
  /**
   * This method works more like the zero-arg constructor for JFrame than
   * JInternalFrame because I replace JFrames with it. No title, resizable,
   * closable, maximizable, iconifiable
   */
  public JExternalFrame() {
    this("");
  }

  //Ends constructor JExternalFrame

  /**
   * Make a JExternalFrame with the given title; resizable, closable,
   * maximizable, iconifiable
   *
   * @param title  Put this title on top of the frame
   */
  public JExternalFrame(String title) {
    this(title, true);
  }

  //Ends constructor JExternalFrame

  /**
   * Like above, but can control resizability
   *
   * @param title      Put this title on top of the frame
   * @param resizable  Can the user resize the window?
   */
  public JExternalFrame(String title, boolean resizable) {
    this(title, resizable, true);
  }

  //Ends constructor JExternalFrame

  /**
   * Like above, but can control closability
   *
   * @param title      Put this title on top of the frame
   * @param resizable  Can the user resize the window?
   * @param closable   Can the user close the window?
   */
  public JExternalFrame(String title, boolean resizable, boolean closable) {
    this(title, resizable, closable, true);
  }

  //Ends constructor JExternalFrame

  /**
   * Like above, but can control maximizability
   *
   * @param title        Put this title on top of the frame
   * @param resizable    Can the user resize the window?
   * @param closable     Can the user close the window?
   * @param maximizable  Can the user maximize the window?
   */
  public JExternalFrame(String title, boolean resizable, boolean closable,
      boolean maximizable) {
    this(title, resizable, closable, maximizable, true);
  }

  //Ends constructor JExternalFrame

  /**
   * Like above, but can control iconifiability
   *
   * @param title        Put this title on top of the frame
   * @param resizable    Can the user resize the window?
   * @param closable     Can the user close the window?
   * @param maximizable  Can the user maximize the window?
   * @param iconifiable  Can the user iconify the window?
   */
  public JExternalFrame(String title,
      boolean resizable,
      boolean closable,
      boolean maximizable,
      boolean iconifiable) {
    super();
    realFrame = new JInternalFrame(title, resizable, closable, maximizable,
        iconifiable);
    realFrame.addInternalFrameListener(
      new InternalFrameListener() {
        public void internalFrameClosing(InternalFrameEvent evt) {
          forwardWindowEvent(WindowEvent.WINDOW_CLOSING);
        }

        //Ends method internalFrameClosing

        public void internalFrameActivated(InternalFrameEvent evt) {
          forwardWindowEvent(WindowEvent.WINDOW_ACTIVATED);
        }

        //Ends method internalFrameActivated

        public void internalFrameClosed(InternalFrameEvent evt) {
          forwardWindowEvent(WindowEvent.WINDOW_CLOSED);
          dispose();
        }

        //Ends method internalFrameClosed

        public void internalFrameDeactivated(InternalFrameEvent evt) {
          forwardWindowEvent(WindowEvent.WINDOW_DEACTIVATED);
        }

        //Ends method internalFrameDeactivated

        public void internalFrameDeiconified(InternalFrameEvent evt) {
          forwardWindowEvent(WindowEvent.WINDOW_DEICONIFIED);
        }

        //Ends method internalFrameDeiconified

        public void internalFrameIconified(InternalFrameEvent evt) {
          forwardWindowEvent(WindowEvent.WINDOW_ICONIFIED);
        }

        //Ends method internalFrameIconified

        public void internalFrameOpened(InternalFrameEvent evt) {
          forwardWindowEvent(WindowEvent.WINDOW_OPENED);
        }
        //Ends method internalFrameOpened
      });
    //Ends addInternalFrameListener
    FocusKeeper fk = new FocusKeeper();
    Container c = super.getContentPane();
    c.addFocusListener(fk);
    c.addContainerListener(fk);
    c.setLayout(new GridLayout(1, 1, 0, 0));
    //KISS
    framePane = new JDesktopPane();
    framePane.setDesktopManager(new MiniDesktopManager());
    c.add(framePane);
    framePane.add(realFrame);
  }

  //Ends method getDefaultCloseOperation

  /**
   * Tell the frame what action it should take when the user tries to close it.
   * This should be one of the constants from WindowConstants
   *
   * @param dco  The new Default Close Operation
   * @see        javax.swing.WindowConstants
   */
  public void setDefaultCloseOperation(int dco) {
    realFrame.setDefaultCloseOperation(dco);
  }

  //Ends method isSelected

  /**
   * Set whether the frame can be closed.
   *
   * @param Closable  The new Closable value
   */
  public void setClosable(boolean Closable) {
    realFrame.setClosable(Closable);
  }

  /**
   * Close the window. I'm not sure this is a good idea. You should probably use
   * setVisible instead.
   *
   * @param Closed                     The new Closed value
   * @exception PropertyVetoException  Description of Exception
   */
  public void setClosed(boolean Closed) throws PropertyVetoException {
    realFrame.setClosed(Closed);
  }

  //Ends method setClosed

  /**
   * Iconify / Deiconify the frame.
   *
   * @param Icon                       The new Icon value
   * @exception PropertyVetoException  Description of Exception
   */
  public void setIcon(boolean Icon) throws PropertyVetoException {
    realFrame.setIcon(Icon);
  }

  //Ends method setIcon

  /**
   * Maximize / Demaximize the frame.
   *
   * @param Maximum                    The new Maximum value
   * @exception PropertyVetoException  Description of Exception
   */
  public void setMaximum(boolean Maximum) throws PropertyVetoException {
    realFrame.setMaximum(Maximum);
  }

  //Ends method setMaximum

  /**
   * Set whether the frame can be maximized.
   *
   * @param Maximizable  The new Maximizable value
   */
  public void setMaximizable(boolean Maximizable) {
    realFrame.setMaximizable(Maximizable);
  }

  //Ends method setMaximizable

  /**
   * Sets the Iconifiable attribute of the JExternalFrame object
   *
   * @param Iconifiable  The new Iconifiable value
   */
  public void setIconifiable(boolean Iconifiable) {
    realFrame.setIconifiable(Iconifiable);
  }

  /**
   * Set whether the frame can be resized.
   *
   * @param Resizable  The new Resizable value
   */
  public void setResizable(boolean Resizable) {
    realFrame.setResizable(Resizable);
  }

  //Ends method setResizable

  /**
   * Set whether the frame is selected.
   *
   * @param Selected                   The new Selected value
   * @exception PropertyVetoException  Description of Exception
   */
  public void setSelected(boolean Selected) throws PropertyVetoException {
    realFrame.setSelected(Selected);
  }

  //Ends method setSelected

  /**
   * Give the frame a menu bar to use.
   *
   * @param m  The new JMenuBar value
   */
  public void setJMenuBar(JMenuBar m) {
    realFrame.setJMenuBar(m);
  }

  //Ends method setJMenuBar

  /**
   * Change the title of the frame
   *
   * @param title  The new Title value
   */
  public void setTitle(String title) {
    realFrame.setTitle(title);
  }

  //Ends method getFrameIcon

  /**
   * Set the icon the frame should display
   *
   * @param icon  The new FrameIcon value
   */
  public void setFrameIcon(Icon icon) {
    realFrame.setFrameIcon(icon);
  }

  //Ends method pack

  /**
   * Set the size and location of the frame
   *
   * @param x       The new Bounds value
   * @param y       The new Bounds value
   * @param width   The new Bounds value
   * @param height  The new Bounds value
   */
  public void setBounds(int x, int y, int width, int height) {
    super.setBounds(x, y, width, height);
    if (!iconified && realFrame != null) {
      realFrame.setBounds(0, 0, width, height);
    }
  }

  //Ends method setBounds

  /**
   * Show / hide the frame
   *
   * @param visible  The new Visible value
   */
  public void setVisible(boolean visible) {
    try {
      super.setVisible(visible);
      realFrame.setVisible(visible);
      realFrame.setSelected(visible);
    } catch (java.beans.PropertyVetoException e) {
    }
    //Ends try..catch
  }

  //Ends method setIconified

  /**
   * Use the container returned by getContentPane() to put your stuff in.
   *
   * @return   the Container where all your stuff should go.
   */
  public Container getContentPane() {
    return realFrame.getContentPane();
  }

  //Ends method getContentPane

  /**
   * What should the frame do when the user clicks the close button? This should
   * be one of the constants from WindowConstants.
   *
   * @return   What the frame should do when the user tries to close it.
   * @see      javax.swing.WindowConstants
   */
  public int getDefaultCloseOperation() {
    return realFrame.getDefaultCloseOperation();
  }

  //Ends method setDefaultCloseOperation

  /**
   * The JMenuBar this frame is currently showing.
   *
   * @return   The JMenuBar value
   */
  public JMenuBar getJMenuBar() {
    return realFrame.getJMenuBar();
  }

  //Ends method getJMenuBar

  /**
   * The title of the frame
   *
   * @return   The Title value
   */
  public String getTitle() {
    return realFrame.getTitle();
  }

  //Ends method getTitle

  /**
   * Is the frame closable?
   *
   * @return   The Closable value
   */
  public boolean isClosable() {
    return realFrame.isClosable();
  }

  //Ends method isClosable

  /**
   * Is the frame currently closed?
   *
   * @return   The Closed value
   */
  public boolean isClosed() {
    return realFrame.isClosed();
  }

  //Ends method isClosed

  /**
   * Is the frame iconified?
   *
   * @return   The Icon value
   */
  public boolean isIcon() {
    return realFrame.isIcon();
  }

  //Ends method isIcon

  /**
   * Can the frame be iconified?
   *
   * @return   The Iconifiable value
   */
  public boolean isIconifiable() {
    return realFrame.isIconifiable();
  }

  //Ends method isIconifiable

  /**
   * Can the frame be maximized?
   *
   * @return   The Maximizable value
   */
  public boolean isMaximizable() {
    return realFrame.isMaximizable();
  }

  //Ends method isMaximizable

  /**
   * Is the frame maximized?
   *
   * @return   The Maximum value
   */
  public boolean isMaximum() {
    return realFrame.isMaximum();
  }

  //Ends method isMaximum

  /**
   * Can the frame be resized?
   *
   * @return   The Resizable value
   */
  public boolean isResizable() {
    return realFrame.isResizable();
  }

  //Ends method isResizable

  /**
   * Is the frame currently selected?
   *
   * @return   The Selected value
   */
  public boolean isSelected() {
    return realFrame.isSelected();
  }

  //Ends method setTitle

  /**
   * What icon is the frame currently displaying?
   *
   * @return   The FrameIcon value
   */
  public Icon getFrameIcon() {
    return realFrame.getFrameIcon();
  }

  //Ends method setFrameIcon

  /**
   * Do that packing thing.
   */
  public void pack() {
    realFrame.pack();
    super.pack();
  }

  //Ends method forwardWindowEvent

  /**
   * Let the window know whether to setBounds on the internal frame or not
   *
   * @param iconified  The new Iconified value
   */
  void setIconified(boolean iconified) {
    this.iconified = iconified;
  }

  //Ends constructor JExternalFrame

  /**
   * Translate an InternalFrameEvent into a WindowEvent and pass it on
   *
   * @param WindowEventID  Description of Parameter
   */
  void forwardWindowEvent(int WindowEventID) {
    WindowEvent NewEvent = new WindowEvent(this, WindowEventID);
    processWindowEvent(NewEvent);
  }

  //******************************** Inner Classes ***************************
  /**
   * Description of the Class
   *
   * @author    fred
   * @created   27 avril 2002
   */
  protected class MiniDesktopManager extends DefaultDesktopManager
       implements SwingConstants {
    //Ends method deiconifyFrame
    private Point LowerRight, UpperLeft;
    private int direction = -1;

    /**
     * Sets the BoundsForFrame attribute of the MiniDesktopManager object
     *
     * @param f          The new BoundsForFrame value
     * @param newX       The new BoundsForFrame value
     * @param newY       The new BoundsForFrame value
     * @param newWidth   The new BoundsForFrame value
     * @param newHeight  The new BoundsForFrame value
     */
    public void setBoundsForFrame(JComponent f,
        int newX,
        int newY,
        int newWidth,
        int newHeight) {
      Dimension d = getSize();
      boolean didResize = ((d.width != newWidth) ||
          (d.height != newHeight));
      Point p = getLocationOnScreen();
      setBounds(p.x + newX, p.y + newY, newWidth, newHeight);
      if (didResize) {
        validate();
      }
      //Ends if
    }

    //Ends method setBoundsForFrame

    /**
     * Description of the Method
     *
     * @param f     Description of Parameter
     * @param newX  Description of Parameter
     * @param newY  Description of Parameter
     */
    public void dragFrame(JComponent f, int newX, int newY) {
      setBoundsForFrame(f, newX, newY, f.getWidth(), f.getHeight());
    }

    //Ends method dragFrame

    /**
     * Description of the Method
     *
     * @param f  Description of Parameter
     */
    public void endDraggingFrame(JComponent f) {
    }

    //Ends method endDraggingFrame

    /**
     * Description of the Method
     *
     * @param f  Description of Parameter
     */
    public void iconifyFrame(JInternalFrame f) {
      setIconified(true);
      super.iconifyFrame(f);
      f.getDesktopIcon().setLocation(0, 0);
      Dimension size = f.getDesktopIcon().getSize();
      setSize(size);
    }

    //Ends method iconifyFrame

    /**
     * Description of the Method
     *
     * @param f  Description of Parameter
     */
    public void deiconifyFrame(JInternalFrame f) {
      setIconified(false);
      super.deiconifyFrame(f);
      Rectangle r = f.getBounds();
      setBoundsForFrame(f, r.x, r.y, r.width, r.height);
    }

    /**
     * Description of the Method
     *
     * @param f          Description of Parameter
     * @param direction  Description of Parameter
     */
    public void beginResizingFrame(JComponent f, int direction) {
      this.direction = direction;
      UpperLeft = getLocationOnScreen();
      Dimension size = f.getSize();
      LowerRight = new Point(UpperLeft.x + size.width,
          UpperLeft.y + size.height);
    }

    //Ends method beginResizingFrame

    /**
     * Description of the Method
     *
     * @param f          Description of Parameter
     * @param newX       Description of Parameter
     * @param newY       Description of Parameter
     * @param newWidth   Description of Parameter
     * @param newHeight  Description of Parameter
     */
    public void resizeFrame(JComponent f, int newX, int newY,
        int newWidth, int newHeight) {
      Point location = getLocation();
      if ((direction == NORTH) || (direction == NORTH_EAST) ||
          (direction == NORTH_WEST)) {
        newHeight = LowerRight.y - location.y;
      }
      //Ends if
      if ((direction == WEST) || (direction == NORTH_WEST) ||
          (direction == SOUTH_WEST)) {
        newWidth = LowerRight.x - location.x;
      }
      //Ends if
      super.resizeFrame(f, newX, newY, newWidth, newHeight);
    }

    //Ends method resizeFrame

    /**
     * Description of the Method
     *
     * @param f  Description of Parameter
     */
    public void endResizingFrame(JComponent f) {
    }

    //Ends method endResizingFrame

    /**
     * Description of the Method
     *
     * @param f  Description of Parameter
     */
    public void closeFrame(JInternalFrame f) {
      super.closeFrame(f);
    }

    //Ends method closeFrame

    /**
     * Description of the Method
     *
     * @param f  Description of Parameter
     */
    public void maximizeFrame(JInternalFrame f) {
      if (!f.isIcon()) {
        setPreviousBounds(f, getBounds());
      }
      else {
        try {
          f.setIcon(false);
        } catch (PropertyVetoException e) {
        }
      }
      //Ends if..else
      setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
      validate();
      try {
        f.setSelected(true);
      } catch (PropertyVetoException e) {
      }
      removeIconFor(f);
    }
    //Ends method maximizeFrame
  }

  //Ends class MiniDesktopManager

  /**
   * Description of the Class
   *
   * @author    fred
   * @created   27 avril 2002
   */
  protected class FocusKeeper implements FocusListener, ContainerListener {
    /**
     * Description of the Method
     *
     * @param evt  Description of Parameter
     */
    public void focusGained(FocusEvent evt) {
      try {
        setSelected(true);
      } catch (PropertyVetoException e) {
      }
    }

    //Ends method focusGained

    /**
     * Description of the Method
     *
     * @param evt  Description of Parameter
     */
    public void focusLost(FocusEvent evt) {
      try {
        setSelected(false);
      } catch (PropertyVetoException e) {
      }
    }

    //Ends method recursiveAdd

    /**
     * Description of the Method
     *
     * @param evt  Description of Parameter
     */
    public void componentAdded(ContainerEvent evt) {
      recursiveAdd(evt.getChild());
    }

    //Ends method recursiveRemove

    /**
     * Description of the Method
     *
     * @param evt  Description of Parameter
     */
    public void componentRemoved(ContainerEvent evt) {
      recursiveRemove(evt.getChild());
    }

    //Ends method setSelected

    /**
     * Description of the Method
     *
     * @param c  Description of Parameter
     */
    private void recursiveAdd(Component c) {
      c.addFocusListener(this);
      if (c instanceof Container) {
        ((Container) c).addContainerListener(this);
        Component[] children = ((Container) c).getComponents();
        for (int i = 0; i < children.length; i++) {
          recursiveAdd(children[i]);
        }
      }
      //Ends if
    }

    //Ends method componentAdded

    /**
     * Description of the Method
     *
     * @param c  Description of Parameter
     */
    private void recursiveRemove(Component c) {
      c.removeFocusListener(this);
      if (c instanceof Container) {
        ((Container) c).removeContainerListener(this);
        Component[] children = ((Container) c).getComponents();
        for (int i = 0; i < children.length; i++) {
          recursiveRemove(children[i]);
        }
      }
      //Ends if
    }
    //Ends method componentRemoved
  }
  //Ends method setVisible

  //******************************** Ends Methods ****************************

}
//Ends class JExternalFrame
