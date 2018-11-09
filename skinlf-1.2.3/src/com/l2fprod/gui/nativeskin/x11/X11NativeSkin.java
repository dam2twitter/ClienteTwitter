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
package com.l2fprod.gui.nativeskin.x11;

import java.awt.*;
import java.awt.image.PixelGrabber;
import sun.awt.*;

import com.l2fprod.gui.region.*;
import com.l2fprod.gui.nativeskin.NativeSkin;

/**
 * X11 Implementation. <br>
 *
 *
 * @author    Herve Lemaitre
 * @created   27 avril 2002
 * @version   $Revision: 1.1 $
 */
public class X11NativeSkin extends NativeSkin {

  /**
   * Constructor for the X11NativeSkin object
   */
  public X11NativeSkin() {
  }

  /**
   * Sets the WindowRegion attribute of the X11NativeSkin object
   *
   * @param window  The new WindowRegion value
   * @param region  The new WindowRegion value
   * @param redraw  The new WindowRegion value
   */
  public void setWindowRegion(Window window, Region region, boolean redraw) {
    /*
     *  if (region instanceof ImageRegion) {
     *  / grab pixel of the image
     *  int width    = ((ImageRegion)region).getImage().getWidth(null);
     *  int height   = ((ImageRegion)region).getImage().getHeight(null);
     *  int[] pixels = new int[width * height];
     *  PixelGrabber pg = new PixelGrabber(((ImageRegion)region).getImage(), 0, 0, width, height,
     *  pixels, 0, width);
     *  try {
     *  pg.grabPixels();
     *  } catch (InterruptedException e) {
     *  System.err.println("interrupted waiting for pixels!");
     *  e.printStackTrace();
     *  }
     *  / Get the drawing surface
     *  DrawingSurfaceInfo drawingSurfaceInfo =
     *  ((DrawingSurface)(window.getPeer())).getDrawingSurfaceInfo();
     *  if (drawingSurfaceInfo != null) {
     *  drawingSurfaceInfo.lock();
     *  X11DrawingSurface x11DrawingSurface =
     *  (X11DrawingSurface)drawingSurfaceInfo.getSurface();
     *  setWindowRegion0(x11DrawingSurface.getDisplay(),
     *  x11DrawingSurface.getDrawable(),
     *  pixels,
     *  width,
     *  height);
     *  drawingSurfaceInfo.unlock();
     *  }
     *  }
     */
  }

  /**
   * Sets the WindowRegion0 attribute of the X11NativeSkin class
   *
   * @param display   The new WindowRegion0 value
   * @param drawable  The new WindowRegion0 value
   * @param pixels    The new WindowRegion0 value
   * @param width     The new WindowRegion0 value
   * @param height    The new WindowRegion0 value
   */
  private native static void setWindowRegion0(int display,
      int drawable,
      int[] pixels,
      int width,
      int height);

}
// end class Region
