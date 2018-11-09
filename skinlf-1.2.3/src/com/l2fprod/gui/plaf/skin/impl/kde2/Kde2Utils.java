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
import java.net.URL;
import javax.swing.ImageIcon;

import com.l2fprod.gui.plaf.skin.*;
import com.l2fprod.util.*;

/**
 * Created on 07/01/2001 by Frederic Lavigne, fred@L2FProd.com
 *
 * @author    $Author: l2fprod $
 * @created   27 avril 2002
 * @version   $Revision: 1.3 $, $Date: 2002/04/27 09:54:57 $
 */
class Kde2Utils {

  /**
   * Description of the Method
   *
   * @param ini            Description of Parameter
   * @param skinURL        Description of Parameter
   * @param section        Description of Parameter
   * @return               Description of the Returned Value
   * @exception Exception  Description of Exception
   */
  public static DefaultButton newButton(IniFile ini, URL skinURL, String section) throws Exception {
    if (ini.getKeyValue(section, "CopyWidget") != null) {
      return newButton(ini, skinURL, ini.getKeyValue(section, "CopyWidget"));
    }

    String path = ini.getKeyValue(section, "PixmapBorder");
    if (path == null) {
      path = ini.getKeyValue(section, "Pixmap");
    }

    Image image = SkinUtils.loadImage(new URL(skinURL, "../pixmaps/" + path));

    int border = ini.getKeyIntValue(section, "PixmapBWidth", 0);
    return new DefaultButton(image, image.getWidth(null), image.getHeight(null),
        border, border, border, border);
  }

  /**
   * Description of the Method
   *
   * @param ini            Description of Parameter
   * @param skinURL        Description of Parameter
   * @param section        Description of Parameter
   * @return               Description of the Returned Value
   * @exception Exception  Description of Exception
   */
  public static ImageIcon newIcon(IniFile ini, URL skinURL, String section) throws Exception {
    if (ini.getKeyValue(section, "CopyWidget") != null) {
      return newIcon(ini, skinURL, ini.getKeyValue(section, "CopyWidget"));
    }

    String path = ini.getKeyValue(section, "Pixmap");
    Image image = SkinUtils.loadImage(new URL(skinURL, "../pixmaps/" + path));

    return new ImageIcon(image);
  }
}
