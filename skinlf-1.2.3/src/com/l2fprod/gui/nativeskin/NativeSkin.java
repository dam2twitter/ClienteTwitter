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
package com.l2fprod.gui.nativeskin;

import java.awt.Window;
import java.awt.Image;

import com.l2fprod.gui.region.Region;
import com.l2fprod.gui.region.ImageRegion;
import com.l2fprod.util.OS;

/**
 * NativeSkin.<BR>
 * SkinLF got native. This class offers methods to help developers
 * build Skinnable applications.
 */
public abstract class NativeSkin {

  private static NativeSkin theInstance;

  /**
   * Return true if NativeSkin is supported on this platform.
   *
   * @return true if NativeSkin is supported on this platform.
   */
  public static boolean isSupported() {
    return OS.isWindows();
  }

  /**
   * Get the instance of the NativeSkin for this platform.
   *
   * @return a <code>NativeSkin</code> value
   */
  public static NativeSkin getInstance() {
    if (theInstance == null) {
      String impl = null;
      String library = null;

      if (OS.isWindows()) {
        impl = "com.l2fprod.gui.nativeskin.win32.Win32NativeSkin";
        if (OS.isOneDotFour()) {
          library = "nativeskinwin32JAWT";
        } else {
          library = "nativeskinwin32";
        }        
      }
      /* NOT YET COMPILED
      else if (OS.isUnix()) {
        impl = "com.l2fprod.gui.nativeskin.x11.X11NativeSkin";
        if (OS.isSolaris()) {
          library = "nativeskinsolaris";
        }
        else if (OS.isLinux()) {
          library = "nativeskinlinux";
        }
      }
      */
      else {
        throw new Error("NativeSkin is not yet available for your platform: "
            + System.getProperty("os.name"));
      }

      try {
        System.loadLibrary(library);

        theInstance = (NativeSkin) Class.forName(impl).newInstance();
      } catch (Throwable th) {
        th.printStackTrace();
        throw new Error("Error while loading the SkinRegion library: " + th.getMessage());
      }
    }
    return theInstance;
  }

  /**
   * Set the transparency of the given Window.
   *
   * @param window a <code>Window</code> value
   * @param transparency an <code>int</code> value
   */
  public void setWindowTransparency(Window window, int transparency) {
    throw new Error("Not Implemented");
  }

  /**
   * Sets the WindowRegion attribute of the RegionBuilder object
   *
   * @param window  The new WindowRegion value
   * @param region  The new WindowRegion value
   * @param redraw  The new WindowRegion value
   */
  public void setWindowRegion(Window window, Region region, boolean redraw) {
    throw new Error("Not Implemented");
  }

  /**
   * Sets the Region for the graphical object identified by
   * the given native handle. This method may be used with
   * environment such as Eclipse/SWT where it is easy
   * to get the native handle of any "Shell" object as it is
   * a public member variable.
   *
   * @param handle an <code>int</code> value
   * @param region a <code>Region</code> value
   * @param redraw a <code>boolean</code> value
   */
  public void setWindowRegion(int handle, Region region, boolean redraw) {
    throw new Error("Not Implemented");
  }

  /**
   * Description of the Method
   *
   * @param x1  Description of Parameter
   * @param y1  Description of Parameter
   * @param x2  Description of Parameter
   * @param y2  Description of Parameter
   * @return    Description of the Returned Value
   */
  public Region createEllipticRegion(int x1, int y1, int x2, int y2) {
    throw new Error("Not Implemented");
  }

  /**
   * Description of the Method
   *
   * @param x1  Description of Parameter
   * @param y1  Description of Parameter
   * @param x2  Description of Parameter
   * @param y2  Description of Parameter
   * @return    Description of the Returned Value
   */
  public Region createRectangleRegion(int x1, int y1, int x2, int y2) {
    throw new Error("Not Implemented");
  }

  /**
   * Description of the Method
   *
   * @param x1  Description of Parameter
   * @param y1  Description of Parameter
   * @param x2  Description of Parameter
   * @param y2  Description of Parameter
   * @param x3  Description of Parameter
   * @param y3  Description of Parameter
   * @return    Description of the Returned Value
   */
  public Region createRoundRectangleRegion(int x1, int y1, int x2, int y2, int x3, int y3) {
    throw new Error("Not Implemented");
  }

  /**
   * Description of the Method
   *
   * @param xpoints   Description of Parameter
   * @param ypoints   Description of Parameter
   * @param fillMode  Description of Parameter
   * @return          Description of the Returned Value
   */
  public Region createPolygonRegion(int[] xpoints, int[] ypoints, int fillMode) {
    throw new Error("Not Implemented");
  }

  /**
   * Description of the Method
   *
   * @param region1      Description of Parameter
   * @param region2      Description of Parameter
   * @param combineMode  Description of Parameter
   * @return             Description of the Returned Value
   */
  public Region combineRegions(Region region1, Region region2, int combineMode) {
    throw new Error("Not Implemented");
  }

  /**
   * Description of the Method
   *
   * @param image  Description of Parameter
   * @return       Description of the Returned Value
   */
  public Region createRegion(Image image) {
    return createRegion(image, image.getWidth(null), image.getHeight(null));
  }

  /**
   * Description of the Method
   *
   * @param image   Description of Parameter
   * @param width   Description of Parameter
   * @param height  Description of Parameter
   * @return        Description of the Returned Value
   */
  public Region createRegion(Image image, int width, int height) {
    return new ImageRegion(image, width, height);
  }

  /**
   * Set the window to be always on top of the others.
   *
   * @param window a <code>Window</code> value
   * @param enable true to put window on top, false to restore the default behaviour
   */
  public void setAlwaysOnTop(Window window, boolean enable) {
    throw new Error("Not Implemented");
  }

}
