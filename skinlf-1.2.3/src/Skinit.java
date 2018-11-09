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
import java.lang.reflect.Method;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.UIManager;

import com.l2fprod.gui.plaf.skin.SkinLookAndFeel;
import com.l2fprod.gui.plaf.skin.Skin;
import com.l2fprod.gui.plaf.skin.SkinUtils;
import com.l2fprod.gui.plaf.skin.CompoundSkin;

/**
 * Skinit. <br>
 * Skinit is Skin Look And Feel wrapper. It allows you to start any application
 * with SkinLF. Skinit will prevent the application to set its own Look And
 * Feel. Once Skin Look And Feel is set, it can't be removed. <br>
 *
 * @author    $Author: l2fprod $
 * @version   $Revision: 1.8 $, $Date: 2002/06/11 14:59:15 $
 */
public class Skinit extends javax.swing.JApplet {

  /**
   * The main program for the Skinit class
   *
   * @param args           The command line arguments
   * @exception Exception  Description of Exception
   */
  public static void main(String[] args) throws Exception {

    if (args.length == 0) {
      printUsage();
    }

    int mainClassNameIndex = -1;
    String gtktheme = null;
    String kdetheme = null;
    String packtheme = null;

    for (int i = 0, c = args.length; i < c; i++) {
      if (args[i].equals("-gtk")) {
        gtktheme = args[++i];
      }
      else if (args[i].equals("-kde")) {
        kdetheme = args[++i];
      }
      else if (args[i].equals("-pack")) {
        packtheme = args[++i];
      }
      else {
        mainClassNameIndex = i;
        break;
      }
    }

    String[] realArgs = new String[args.length - mainClassNameIndex - 1];
    for (int i = 0, c = realArgs.length; i < c; i++) {
      realArgs[i] = args[mainClassNameIndex + i + 1];
    }

    // First try to find the class
    Class clazz = null;
    try {
      clazz = Class.forName(args[mainClassNameIndex]);       
    } catch (ClassNotFoundException e) {
      System.err.println("The class " + args[mainClassNameIndex] + " was not found in the classpath.");
      System.exit(1);
    } catch (Throwable e) {
      e.printStackTrace();
      System.exit(1);
    }
    
    // if the class exists, get the main method
    Method mainMethod = null;
    try {
      mainMethod = clazz.getMethod("main", new Class[]{String[].class});
    } catch (NoSuchMethodException e) {
      System.err.println("No method public static void main(String[] args) in " + clazz.getName());
      System.exit(1);
    } catch (Throwable e) {
      e.printStackTrace();
      System.exit(1);
    }
    
    // try to make sure the main method is accessible
    try {
      mainMethod.setAccessible(true);
    } catch (Throwable e) {      
    }

    // main class and main method found, time to load the skin

    Skin skin = null;

    if (packtheme != null) {
      if (SkinUtils.DEBUG) {
        System.out.println("Loading themepack " + packtheme);
      }
      skin = SkinLookAndFeel.loadThemePack(packtheme);
    }
    else if (gtktheme != null) {
      if (kdetheme != null) {
        skin = new CompoundSkin(SkinLookAndFeel.loadSkin(gtktheme),
            SkinLookAndFeel.loadSkin(kdetheme));
      }
      else {
        skin = SkinLookAndFeel.loadSkin(gtktheme);
      }
    }

    /*
     *  try to use the user default skin
     */
    if (skin == null) {
      if (SkinUtils.DEBUG) {
        System.out.println("Trying user skin");
      }
      skin = SkinLookAndFeel.getSkin();
    }

    if (skin != null) {
      SkinLookAndFeel.setSkin(skin);
      SkinLookAndFeel lnf = new SkinLookAndFeel();
      UIManager.setLookAndFeel(lnf);

      UIManager.addPropertyChangeListener(
        new PropertyChangeListener() {
          public void propertyChange(PropertyChangeEvent event) {
            Object newLF = event.getNewValue();

            if ((newLF instanceof SkinLookAndFeel) == false) {
              try {
                UIManager.setLookAndFeel(new SkinLookAndFeel());
              } catch (Exception e) {
                e.printStackTrace();
              }
            }

          }
        });

    }
    else {
      System.out.println("No GTK theme provided, defaulting to application Look And Feel");
    }
    
    try {
      mainMethod.invoke(null, new Object[]{realArgs});       
    } catch (IllegalAccessException e) {
      System.err.println("Please make sure the class " + clazz.getName() +
                         " and the method main(String[] args) are public.");
      System.exit(1);
    } catch (Throwable e) {
      e.printStackTrace();
      System.exit(1);
    }
  }

  /**
   * Description of the Method
   */
  static void printUsage() {
    String usage = "Skinit - Skin Look And Feel wrapper\n" +
        "Usage: skinit [options] class [args...]\n" +
        "\n" +
        "where options include:\n" +
        "\t-gtk <name>    GTK Theme Filename\n" +
        "\t-kde <name>    KDE Theme Filename\n" +
        "\t-pack <name>   Theme Pack Filename\n";
    System.out.println(usage);
    System.exit(1);
  }

}
