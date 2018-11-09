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

import com.l2fprod.gui.plaf.skin.SkinLookAndFeel;
import com.l2fprod.gui.plaf.skin.Skin;

import java.net.URL;
import javax.swing.*;

/**
 * SkinApplet.<BR>
 * Base class for applet that want to use the Skin Look And Feel.
 *
 * @author    <a href="mailto:fred@L2FProd.com">Frederic Lavigne</a>
 * @created   27 avril 2002
 */
public class SkinApplet extends JApplet {

  /**
   * Description of the Field
   */
  public static String THEMEPACK_TAG = "themepack";

  /**
   * Init the SkinLookAndFeel by using the applet parameter "themepack"
   * (THEMEPACK_TAG). if the parameter is not set, the themepack "themepack.zip"
   * is loaded.
   *
   * @exception Exception  if an error occurs while loading the themepack
   */
  public void initSkin() throws Exception {
    initSkin(THEMEPACK_TAG);
  }

  /**
   * Init the SkinLookAndFeel by using the applet parameter themepackTagName. if
   * the parameter is not set, the themepack "/themepack.zip" is loaded.
   *
   * @param themepackTagName  Description of Parameter
   * @exception Exception     if an error occurs while loading the themepack
   */
  public void initSkin(String themepackTagName) throws Exception {
    String themepack = getParameter(themepackTagName);

    URL themepackURL;

    // if no themepack has been provided in the applet tag
    // use the default themepack provided in the jar of the applet
    if (themepack == null) {
      themepackURL = SkinApplet.class.getResource("/themepack.zip");
    }
    else {
      // a themepack has been provided, relative to the codebase
      themepackURL = new URL(getCodeBase(), themepack);
    }

    Skin skin = SkinLookAndFeel.loadThemePack(themepackURL);
    SkinLookAndFeel.setSkin(skin);

    SkinLookAndFeel.enable();
  }

}
