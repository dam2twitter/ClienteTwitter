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
package com.l2fprod.gui.plaf.skin.impl.kde;

import javax.swing.border.*;
import java.net.URL;
import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;

import com.l2fprod.util.IniFile;
import com.l2fprod.util.ImageUtils;
import com.l2fprod.gui.plaf.skin.*;
import com.l2fprod.gui.plaf.skin.impl.*;

/**
 * Created on 08/04/2000 by Frederic Lavigne, fred@L2FProd.com
 *
 * @author    $Author: l2fprod $
 * @created   27 avril 2002
 * @version   $Revision: 1.7 $, $Date: 2002/04/27 11:37:46 $
 */
class KdeFrameBorder extends DefaultButton {

  /**
   * Constructor for the KdeFrameBorder object
   *
   * @param ini            Description of Parameter
   * @param skinURL        Description of Parameter
   * @exception Exception  Description of Exception
   */
  public KdeFrameBorder(IniFile ini, URL skinURL) throws Exception {
    super(ini.isNullOrEmpty("Window Border", "shapePixmapTop") ? null :
        SkinUtils.loadImage(new URL(skinURL, ini.getKeyValue("Window Border", "shapePixmapTop"))),
        ini.isNullOrEmpty("Window Border", "shapePixmapBottom") ? null :
        SkinUtils.loadImage(new URL(skinURL, ini.getKeyValue("Window Border", "shapePixmapBottom"))),
        ini.isNullOrEmpty("Window Border", "shapePixmapLeft") ? null :
        SkinUtils.loadImage(new URL(skinURL, ini.getKeyValue("Window Border", "shapePixmapLeft"))),
        ini.isNullOrEmpty("Window Border", "shapePixmapRight") ? null :
        SkinUtils.loadImage(new URL(skinURL, ini.getKeyValue("Window Border", "shapePixmapRight"))),
        ini.isNullOrEmpty("Window Border", "shapePixmapTopLeft") ? null :
        SkinUtils.loadImage(new URL(skinURL, ini.getKeyValue("Window Border", "shapePixmapTopLeft"))),
        ini.isNullOrEmpty("Window Border", "shapePixmapTopRight") ? null :
        SkinUtils.loadImage(new URL(skinURL, ini.getKeyValue("Window Border", "shapePixmapTopRight"))),
        ini.isNullOrEmpty("Window Border", "shapePixmapBottomLeft") ? null :
        SkinUtils.loadImage(new URL(skinURL, ini.getKeyValue("Window Border", "shapePixmapBottomLeft"))),
        ini.isNullOrEmpty("Window Border", "shapePixmapBottomRight") ? null :
        SkinUtils.loadImage(new URL(skinURL, ini.getKeyValue("Window Border", "shapePixmapBottomRight"))));
  }

}
