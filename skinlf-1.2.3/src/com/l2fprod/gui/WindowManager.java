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

import java.awt.Container;

/**
 * WindowManager. <br>
 * WindowManager is responsible for implementing L&F specific behaviors for the
 * system. SkinWindow implementations should delegate specific behaviors to the
 * WindowManager. For instance, if a WindowManager was asked to iconify, it
 * should try: <pre>
 *      WindowManager.getWindowManager().iconifyWindow(window);
 * </pre> <br>
 * <br>
 * <br>
 * <br>
 * Created on 27/05/2000 by Frederic Lavigne, fred@L2FProd.com
 *
 * @author    $Author: l2fprod $
 * @created   27 avril 2002
 * @version   $Revision: 1.3 $, $Date: 2002/04/27 11:45:57 $
 */
public abstract class WindowManager {

  static WindowManager manager;

  /**
   * Sets the BoundsForWindow attribute of the WindowManager object
   *
   * @param f          The new BoundsForWindow value
   * @param newX       The new BoundsForWindow value
   * @param newY       The new BoundsForWindow value
   * @param newWidth   The new BoundsForWindow value
   * @param newHeight  The new BoundsForWindow value
   */
  public abstract void setBoundsForWindow(Container f, int newX, int newY, int newWidth, int newHeight);

  /**
   * Description of the Method
   *
   * @param w  Description of Parameter
   */
  public abstract void activateWindow(SkinWindow w);

  /**
   * Description of the Method
   *
   * @param w  Description of Parameter
   */
  public abstract void deactivateWindow(SkinWindow w);

  /**
   * Description of the Method
   *
   * @param w  Description of Parameter
   */
  public abstract void openWindow(SkinWindow w);

  /**
   * Description of the Method
   *
   * @param w  Description of Parameter
   */
  public abstract void closeWindow(SkinWindow w);

  /**
   * Description of the Method
   *
   * @param w  Description of Parameter
   */
  public abstract void iconifyWindow(SkinWindow w);

  /**
   * Description of the Method
   *
   * @param w  Description of Parameter
   */
  public abstract void deiconifyWindow(SkinWindow w);

  /**
   * Description of the Method
   *
   * @param w  Description of Parameter
   */
  public abstract void maximizeWindow(SkinWindow w);

  /**
   * Description of the Method
   *
   * @param w  Description of Parameter
   */
  public abstract void minimizeWindow(SkinWindow w);

  /**
   * Description of the Method
   *
   * @param w  Description of Parameter
   */
  public abstract void shadeWindow(SkinWindow w);

  /**
   * Description of the Method
   *
   * @param w  Description of Parameter
   */
  public abstract void unshadeWindow(SkinWindow w);

  /**
   * Description of the Method
   *
   * @param w  Description of Parameter
   */
  public abstract void beginDraggingWindow(SkinWindow w);

  /**
   * Description of the Method
   *
   * @param w     Description of Parameter
   * @param newX  Description of Parameter
   * @param newY  Description of Parameter
   */
  public abstract void dragWindow(SkinWindow w, int newX, int newY);

  /**
   * Description of the Method
   *
   * @param w  Description of Parameter
   */
  public abstract void endDraggingWindow(SkinWindow w);

  /**
   * Description of the Method
   *
   * @param w          Description of Parameter
   * @param direction  Description of Parameter
   */
  public abstract void beginResizingWindow(SkinWindow w, int direction);

  /**
   * Description of the Method
   *
   * @param w          Description of Parameter
   * @param newX       Description of Parameter
   * @param newY       Description of Parameter
   * @param newWidth   Description of Parameter
   * @param newHeight  Description of Parameter
   */
  public abstract void resizeWindow(SkinWindow w, int newX, int newY, int newWidth, int newHeight);

  /**
   * Description of the Method
   *
   * @param w  Description of Parameter
   */
  public abstract void endResizingWindow(SkinWindow w);

  /**
   * Sets the WindowManager attribute of the WindowManager class
   *
   * @param newManager  The new WindowManager value
   */
  public static void setWindowManager(WindowManager newManager) {
    manager = newManager;
  }

  /**
   * Gets the WindowManager attribute of the WindowManager class
   *
   * @return   The WindowManager value
   */
  public static WindowManager getWindowManager() {
    if (manager == null) {
      manager = new DefaultWindowManager();
    }
    return manager;
  }
}

