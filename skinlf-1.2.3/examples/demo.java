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
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
import java.net.URL;
import javax.swing.*;
import com.l2fprod.gui.*;
import com.l2fprod.gui.plaf.skin.*;
import com.l2fprod.util.*;

/**
 * Skin Look And Feel Demo.<BR>
 * Provides information on how to use Skin Look And Feel
 *
 * @author    fred
 * @created   27 avril 2002
 */
public class demo extends JApplet {

  /**
   * Constructor for the demo object
   */
  public demo() {
  }

  /**
   * Description of the Method
   *
   * @param themes  Description of Parameter
   */
  public void createUI(String[] themes) {
    getContentPane().setLayout(new BorderLayout(3, 3));
    getContentPane().add("Center", new demoPanel(themes));
  }

  /**
   * main method.
   *
   * @param args           the list of theme packs available for this demo
   * @exception Exception  Description of Exception
   */
  public static void main(String[] args) throws Exception {
    if (args.length > 0) {
      String themepack = args[0];
      if (themepack.endsWith(".xml")) {
        SkinLookAndFeel.setSkin(SkinLookAndFeel.loadThemePackDefinition(SkinUtils.toURL(new File(args[0]))));
      }
      else {
        SkinLookAndFeel.setSkin(SkinLookAndFeel.loadThemePack(args[0]));
      }
      SkinLookAndFeel.enable();
    }

    demo d = new demo();
    d.createUI(args);

    JFrame f = new JFrame("Skin Look And Feel " + SkinLookAndFeel.VERSION);

    f.getContentPane().setLayout(new BorderLayout());
    f.getContentPane().add("Center", d);

    f.pack();

    WindowUtils.centerOnScreen(f);

    f.setVisible(true);

    f.addWindowListener(
      new WindowAdapter() {
        public void windowClosing(WindowEvent event) {
          System.exit(0);
        }
      });
  }

}
