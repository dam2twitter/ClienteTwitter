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
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.*;

import com.l2fprod.gui.region.*;
import com.l2fprod.gui.nativeskin.*;

/**
 * NativeSplashScreen.
 * A SkinRegion demo
 */
public class NativeSplashScreen extends Window
  implements KeyListener, MouseListener, ActionListener {
  
  public NativeSplashScreen(Frame parent, ImageIcon icon, int timeout) {
    super(parent);

    setLayout(new BorderLayout());
    JLayeredPane layer = new JLayeredPane();
    JLabel picture = new JLabel(icon);
    layer.add(picture, JLayeredPane.DEFAULT_LAYER);
    layer.setPreferredSize(picture.getPreferredSize());
    picture.setSize(picture.getPreferredSize());
    add("Center", layer);

    // only use the region if it is supported by the platform
    if (NativeSkin.isSupported()) {
      // get the Region builder
      final NativeSkin builder = NativeSkin.getInstance();
      // create a region object from the icon image
      Region region = builder.createRegion(icon.getImage());
      // set the Region to the window
      builder.setWindowRegion(this, region, true);

      builder.setAlwaysOnTop(this, true);

      final JSlider slider = new JSlider(10, 255);
      layer.add(slider, JLayeredPane.PALETTE_LAYER);
      slider.setMajorTickSpacing(20);
      slider.setSnapToTicks(true);
      slider.setBounds(327, 147, 150, 20);
      slider.setOpaque(false);
      slider.setDoubleBuffered(false);
      builder.setWindowTransparency(NativeSplashScreen.this,
                                    slider.getValue());
      slider.addChangeListener(new ChangeListener() {
          public void stateChanged(ChangeEvent e) {
            final int value = slider.getValue();
            builder.setWindowTransparency(NativeSplashScreen.this,
                                          slider.getValue());
          }
        });

      Thread thread = new Thread() {
          public void run() {
            builder.setWindowTransparency(NativeSplashScreen.this, 0);
            try {
              Thread.sleep(1000);
              int transparency = 0;
              int direction = 10;
              while (true) {
                builder.setWindowTransparency(NativeSplashScreen.this, transparency);
                Thread.sleep(25);
                transparency+=direction;

                if (transparency < 0) {
                  transparency = 0;
                  direction = -direction;
                }                
                if (transparency > 255) {
                  transparency = 255;
                  direction = -direction;
                }                
              }
            } catch (Exception e) {
              
            } // end of try-catch
          }
        };
      //      thread.start();

    } else {
      System.err.println("NativeSkin not supported on this platform");
    }
    

    pack();

    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    setLocation((screen.width - getWidth())/2,
                (screen.height - getHeight())/2);

    // Listen for key strokes
    addKeyListener(this);

    // Listen for mouse events from here and parent
    addMouseListener(this);
    if (parent != null) {
    	parent.addMouseListener(this);
    }

    // Timeout after a while
    Timer timer = new Timer(0, this);
    timer.setRepeats(false);
    timer.setInitialDelay(timeout);
    timer.start();
  }

  public synchronized void block() {
    while(isVisible()) {
      try {         
        wait(5);
      } catch (Exception e) {        
      }      
    }
  }

  public void setVisible(boolean b) {
    if (!b) {
      NativeSkin.getInstance().setWindowTransparency(this, 255);
    }
    super.setVisible(b);
  }

  // Dismiss the window on a key press
  public void keyTyped(KeyEvent event) {}
  public void keyReleased(KeyEvent event) {}
  public void keyPressed(KeyEvent event) {
    setVisible(false);
    dispose();
  }

  // Dismiss the window on a mouse click
  public void mousePressed(MouseEvent event) {}
  public void mouseReleased(MouseEvent event) {}
  public void mouseEntered(MouseEvent event) {}
  public void mouseExited(MouseEvent event) {}
  public void mouseClicked(MouseEvent event) {
    setVisible(false);
    dispose();
  }

  // Dismiss the window on a timeout
  public void actionPerformed(ActionEvent event) {
    setVisible(false);
    dispose();
  }

}
