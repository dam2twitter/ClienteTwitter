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
package com.l2fprod.gui.plaf.skin.impl.kde2;

import java.awt.Image;
import java.awt.Dimension;
import java.net.URL;
import javax.swing.SwingConstants;
import javax.swing.JComponent;

import com.l2fprod.util.*;
import com.l2fprod.gui.plaf.skin.*;
import com.l2fprod.gui.plaf.skin.impl.*;

/**
 * @author    $Author: l2fprod $
 * @created   27 avril 2002
 * @version   $Revision: 1.4 $, $Date: 2002/06/11 18:04:53 $
 */
class Kde2Progress extends AbstractSkinProgress implements SkinProgress, SwingConstants {

  DefaultButton progressBar;
  DefaultButton h_progressBack;
  DefaultButton v_progressBack;

  /**
   * Constructor for the Kde2Progress object
   *
   * @param ini            Description of Parameter
   * @param skinURL        Description of Parameter
   * @exception Exception  Description of Exception
   */
  public Kde2Progress(IniFile ini, URL skinURL) throws Exception {
    //PENDING(fred): progress needs to be improved with vertical and horizontal progress
    progressBar = Kde2Utils.newButton(ini, skinURL, "ProgressBar");
    h_progressBack = Kde2Utils.newButton(ini, skinURL, "HScrollGroove");
    v_progressBack = Kde2Utils.newButton(ini, skinURL, "VScrollGroove");
  }

  /**
   * Gets the MinimumSize attribute of the Kde2Progress object
   *
   * @param progress  Description of Parameter
   * @return          The MinimumSize value
   */
  public java.awt.Dimension getMinimumSize(javax.swing.JProgressBar progress) {
    if (progress.getOrientation() == HORIZONTAL) {
      if (h_progressBack != null) {
        return h_progressBack.getMinimumSize();
      }
      else {
        return new Dimension(50, 17);
      }
    }
    else
        if (v_progressBack != null) {
      return v_progressBack.getMinimumSize();
    }
    else {
      return new Dimension(17, 50);
    }
  }

  /**
   * Description of the Method
   *
   * @return   Description of the Returned Value
   */
  public boolean status() {
    return progressBar != null;
  }

  /**
   * Description of the Method
   *
   * @param c  Description of Parameter
   * @return   Description of the Returned Value
   */
  public boolean installSkin(JComponent c) {
    return true;
  }

  /**
   * Description of the Method
   *
   * @param g         Description of Parameter
   * @param progress  Description of Parameter
   * @return          Description of the Returned Value
   */
  public boolean paintProgress(java.awt.Graphics g, javax.swing.JProgressBar progress) {
    if (progress.getOrientation() == HORIZONTAL) {
      if (h_progressBack != null) {
        h_progressBack.paint(g, progress);
      }
      else
          if (v_progressBack != null) {
        v_progressBack.paint(g, progress);
      }
    }

    if ((progressBar != null) && (progress.getValue() > progress.getMinimum())) {
      if (progress.getOrientation() == HORIZONTAL) {
        progressBar.paint(g, 0, 0,
            (int) ((double) progress.getValue() * progress.getWidth() / (double) progress.getMaximum()),
            progress.getHeight(), progress);
      }
      else {
        progressBar.paint(g, 0, 0,
            progress.getWidth(),
            (int) ((double) progress.getValue() * progress.getHeight() / (double) progress.getMaximum()), progress);
      }
    }
    return true;
  }

}
