/*
 * @(#)Clock2.java	1.5 98/07/09
 *
 * Copyright (c) 1997, 1998 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Sun grants you ("Licensee") a non-exclusive, royalty free, license to use,
 * modify and redistribute this software in source and binary code form,
 * provided that i) this copyright notice and license appear on all copies of
 * the software; and ii) Licensee does not utilize the software in a manner
 * which is disparaging to Sun.
 *
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING ANY
 * IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR
 * NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN AND ITS LICENSORS SHALL NOT BE
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING
 * OR DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL SUN OR ITS
 * LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT,
 * INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER
 * CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE USE OF
 * OR INABILITY TO USE SOFTWARE, EVEN IF SUN HAS BEEN ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES.
 *
 * This software is not designed or intended for use in on-line control of
 * aircraft, air traffic, aircraft navigation or aircraft communications; or in
 * the design, construction, operation or maintenance of any nuclear
 * facility. Licensee represents and warrants that it will not use or
 * redistribute the Software for such purposes.
 */

import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.text.*;
import java.net.*;

import com.l2fprod.gui.region.ImageRegion;
import com.l2fprod.gui.nativeskin.NativeSkin;

/**
 * NativeSkin Demo.<br>
 * It takes the path to an image as first argument.
 * Based on JDK 1.2.2 Clock2 demo.
 * 
 * @author fred
 */
public class Clock extends Component implements Runnable {

  Image backgroundImage;

  Thread timer;                // The thread that displays clock
  int lastxs, lastys, lastxm,
    lastym, lastxh, lastyh;  // Dimensions used to draw hands 
  Date currentDate;            // Used to get date to display
  Color handColor;             // Color of main hands and dial
  Color numberColor;           // Color of second hand and numbers

  public Clock(Image back) throws Exception {
    backgroundImage = back;

    lastxs = lastys = lastxm = lastym = lastxh = lastyh = 0;
    currentDate = new Date();
    handColor = Color.blue;
    numberColor = Color.darkGray;
    setSize(backgroundImage.getWidth(this), backgroundImage.getHeight(this));
  }
  
  // Paint is the main part of the program
  public void paint(Graphics g) {
    // draw the background
    g.drawImage(backgroundImage, 0, 0, this);

    int width = getWidth();
    int height = getHeight();

    int xh, yh, xm, ym, xs, ys, s = 0, m = 10, h = 10, xcenter, ycenter;

    currentDate = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("s",Locale.getDefault());
    try {
      s = Integer.parseInt(formatter.format(currentDate));
    } catch (NumberFormatException n) {
      s = 0;
    }
    formatter.applyPattern("m");
    try {
      m = Integer.parseInt(formatter.format(currentDate));
    } catch (NumberFormatException n) {
      m = 10;
    }    
    formatter.applyPattern("h");
    try {
      h = Integer.parseInt(formatter.format(currentDate));
    } catch (NumberFormatException n) {
      h = 10;
    }
    formatter.applyPattern("EEE MMM dd HH:mm:ss yyyy");
    xcenter = width / 2;
    ycenter = height / 2;
    
    // a= s* pi/2 - pi/2 (to switch 0,0 from 3:00 to 12:00)
    // x = r(cos a) + xcenter, y = r(sin a) + ycenter
    
    xs = (int)(Math.cos(s * 3.14f/30 - 3.14f/2) * 45 + xcenter);
    ys = (int)(Math.sin(s * 3.14f/30 - 3.14f/2) * 45 + ycenter);
    xm = (int)(Math.cos(m * 3.14f/30 - 3.14f/2) * 40 + xcenter);
    ym = (int)(Math.sin(m * 3.14f/30 - 3.14f/2) * 40 + ycenter);
    xh = (int)(Math.cos((h*30 + m/2) * 3.14f/180 - 3.14f/2) * 30 + xcenter);
    yh = (int)(Math.sin((h*30 + m/2) * 3.14f/180 - 3.14f/2) * 30 + ycenter);
    
    // Erase if necessary, and redraw    
    g.setColor(getBackground());
    if (xs != lastxs || ys != lastys) {
      g.drawLine(xcenter, ycenter, lastxs, lastys);
    }
    if (xm != lastxm || ym != lastym) {
      g.drawLine(xcenter, ycenter-1, lastxm, lastym);
      g.drawLine(xcenter-1, ycenter, lastxm, lastym); }
    if (xh != lastxh || yh != lastyh) {
      g.drawLine(xcenter, ycenter-1, lastxh, lastyh);
      g.drawLine(xcenter-1, ycenter, lastxh, lastyh); }
    g.setColor(handColor);
    g.drawLine(xcenter, ycenter, xs, ys);
    g.drawLine(xcenter, ycenter-1, xm, ym);
    g.drawLine(xcenter-1, ycenter, xm, ym);
    g.drawLine(xcenter, ycenter-1, xh, yh);
    g.drawLine(xcenter-1, ycenter, xh, yh);
    lastxs=xs; lastys=ys;
    lastxm=xm; lastym=ym;
    lastxh=xh; lastyh=yh;
    currentDate=null;
  }
  
  public void start() {
    timer = new Thread(this);
    timer.start();
  }
  
  public void stop() {
    timer = null;
  }
  
  public void run() {
    Thread me = Thread.currentThread();
    while (timer == me) {
      try {
        Thread.currentThread().sleep(500);
      } catch (InterruptedException e) {
      }
      repaint();
    }
  }
  
  public void update(Graphics g) {
    paint(g);
  }

  public static void main(String[] args) throws Exception {
    // Create a window which will hold our clock
    final Window window = new Window(new Frame()) {
        public void setVisible(boolean b) {
          System.out.println(b);
          super.setVisible(b);
        }
        public void pack() {          
        }
      };
    window.setLayout(new BorderLayout());

    // Load the clock image
    Image back;
    if (args[0].startsWith("http://")) {
      back = Toolkit.getDefaultToolkit().getImage(new URL(args[0]));       
    } else {
      back = Toolkit.getDefaultToolkit().getImage(args[0]);
    }
    
    try {
      MediaTracker tracker = new MediaTracker(window);
      tracker.addImage(back, 0);
      tracker.waitForAll();      
    } catch (InterruptedException e) {      
    }

    // Create a new clock
    final Clock clock = new Clock(back);
    // start animate the ticks
    clock.start();

    // put the clock in the window and resize
    window.add("Center", clock);
    window.setSize(clock.getSize());

    // create an ImageRegion from the background image
    // so we can use a rounded clock
    ImageRegion region = new ImageRegion(back);
    NativeSkin.getInstance().setWindowRegion(window, region, true);

    // A clock should always be on top of other windows
    NativeSkin.getInstance().setAlwaysOnTop(window, true);

    // but transparent so you can see through
    NativeSkin.getInstance().setWindowTransparency(window, 127);

    // Add a PopupMenu to switch transparency and quit the clock
    final PopupMenu popup = new PopupMenu();
    for (int i = 0; i < 100; i = i + 10) {
      final int t = i;
      MenuItem item = new MenuItem(i + "%");
      popup.add(item);
      ActionListener listener = new ActionListener() {
          public void actionPerformed(ActionEvent event) {
            int transparency = (int)(255 * ((double)(100 - t) / (double)100));
            NativeSkin.getInstance().setWindowTransparency(window, transparency);
            NativeSkin.getInstance().setAlwaysOnTop(window, true);
          }
        };
      item.addActionListener(listener);
    }
    popup.addSeparator();
    popup.add(new MenuItem("Exit")).
      addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent event) {
            System.exit(0);
          }
        });

    clock.addMouseListener(new MouseAdapter() {
        public void mouseReleased(MouseEvent event) {
          if (event.isPopupTrigger()) {
            popup.show(clock, event.getX(), event.getY());
          }
        }
      });
    clock.add(popup);

    // Add a listener to move the window around
    WindowMover mover = new WindowMover(window);
    clock.addMouseListener(mover);
    clock.addMouseMotionListener(mover);

    // finally show the clock.
    window.show();
  }
  
  static class WindowMover extends MouseAdapter implements MouseMotionListener {
    int clickX, clickY;
    Window window;
    public WindowMover(Window w) {
      window = w;
    }
    public void mousePressed(MouseEvent event) {
      clickX = event.getX();
      clickY = event.getY();
    }
    public void mouseDragged(MouseEvent event) {
      int dX = event.getX() - clickX;
      int dY = event.getY() - clickY;
      
      window.setLocation(window.getX() + dX,
                         window.getY() + dY);
    }
    public void mouseMoved(MouseEvent event) {
    }
  }

}
