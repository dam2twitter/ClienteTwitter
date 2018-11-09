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
import java.util.EventListener;
import com.l2fprod.gui.event.*;

/**
 * Created on 12/06/2000 by Frederic Lavigne, fred@L2FProd.com
 *
 * @author    $Author: l2fprod $
 * @created   27 avril 2002
 * @version   $Revision: 1.3 $, $Date: 2002/04/27 11:45:57 $
 */
public class SkinEventMulticaster extends AWTEventMulticaster implements SkinWindowListener {

  /**
   * Constructor for the SkinEventMulticaster object
   *
   * @param a  Description of Parameter
   * @param b  Description of Parameter
   */
  protected SkinEventMulticaster(EventListener a, EventListener b) {
    super(a, b);
  }

  /**
   * Description of the Method
   *
   * @param e  Description of Parameter
   */
  public void windowShaded(SkinWindowEvent e) {
    ((SkinWindowListener) a).windowShaded(e);
    ((SkinWindowListener) b).windowShaded(e);
  }

  /**
   * Description of the Method
   *
   * @param e  Description of Parameter
   */
  public void windowUnshaded(SkinWindowEvent e) {
    ((SkinWindowListener) a).windowUnshaded(e);
    ((SkinWindowListener) b).windowUnshaded(e);
  }

  /**
   * Description of the Method
   *
   * @param e  Description of Parameter
   */
  public void windowMaximized(SkinWindowEvent e) {
    ((SkinWindowListener) a).windowMaximized(e);
    ((SkinWindowListener) b).windowMaximized(e);
  }

  /**
   * Description of the Method
   *
   * @param e  Description of Parameter
   */
  public void windowUnmaximized(SkinWindowEvent e) {
    ((SkinWindowListener) a).windowUnmaximized(e);
    ((SkinWindowListener) b).windowUnmaximized(e);
  }

  /**
   * Description of the Method
   *
   * @param oldl  Description of Parameter
   * @return      Description of the Returned Value
   */
  protected EventListener remove(EventListener oldl) {
    if (oldl == a) {
      return b;
    }
    if (oldl == b) {
      return a;
    }
    EventListener a2 = SkinEventMulticaster.removeInternal(a, oldl);
    EventListener b2 = SkinEventMulticaster.removeInternal(b, oldl);
    if (a2 == a && b2 == b) {
      return this;
      // it's not here
    }
    return SkinEventMulticaster.addInternal(a2, b2);
  }

  /**
   * Description of the Method
   *
   * @param a  Description of Parameter
   * @param b  Description of Parameter
   * @return   Description of the Returned Value
   */
  public static SkinWindowListener add(SkinWindowListener a, SkinWindowListener b) {
    return (SkinWindowListener) SkinEventMulticaster.addInternal(a, b);
  }

  /**
   * Description of the Method
   *
   * @param a  Description of Parameter
   * @param b  Description of Parameter
   * @return   Description of the Returned Value
   */
  public static SkinWindowListener remove(SkinWindowListener a, SkinWindowListener b) {
    return (SkinWindowListener) SkinEventMulticaster.removeInternal(a, b);
  }

  /**
   * Adds a feature to the Internal attribute of the SkinEventMulticaster class
   *
   * @param a  The feature to be added to the Internal attribute
   * @param b  The feature to be added to the Internal attribute
   * @return   Description of the Returned Value
   */
  protected static EventListener addInternal(EventListener a, EventListener b) {
    if (a == null) {
      return b;
    }
    if (b == null) {
      return a;
    }
    return new SkinEventMulticaster(a, b);
  }

  /**
   * Description of the Method
   *
   * @param l     Description of Parameter
   * @param oldl  Description of Parameter
   * @return      Description of the Returned Value
   */
  protected static EventListener removeInternal(EventListener l, EventListener oldl) {
    if (l == oldl || l == null) {
      return null;
    }
    else if (l instanceof SkinEventMulticaster) {
      return ((SkinEventMulticaster) l).remove(oldl);
    }
    else {
      return l;
      // it's not here
    }
  }

}

