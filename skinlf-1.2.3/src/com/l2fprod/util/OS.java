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
package com.l2fprod.util;

/**
 * @author    $Author: l2fprod $
 * @created   27 avril 2002
 * @version   $Revision: 1.6 $, $Date: 2002/06/13 16:28:27 $
 */
public final class OS {

  /**
   * Description of the Field
   */
  public final static int JDK1_1 = 11;
  /**
   * Description of the Field
   */
  public final static int JDK1_2 = 12;
  /**
   * Description of the Field
   */
  public final static int JDK1_3 = 13;
  /**
   * Description of the Field
   */
  public final static int JDK1_4 = 14;

  private static boolean isWindows95;
  private static boolean isWindowsNT;
  private static boolean isWindows2000;
  private static boolean isMacintosh;
  private static boolean isSolaris;
  private static boolean isLinux;
  private static boolean isCaseSensitive;

  private static int jdkVersion = JDK1_1;

  /**
   * Constructor for the OS object
   */
  private OS() {
  }

  /**
   * Gets the Windows attribute of the OS class
   *
   * @return   The Windows value
   */
  public static boolean isWindows() {
    return isWindows95() || isWindowsNT() || isWindows2000();
  }

  /**
   * Gets the Windows95 attribute of the OS class
   *
   * @return   The Windows95 value
   */
  public static boolean isWindows95() {
    return isWindows95;
  }

  /**
   * Gets the WindowsNT attribute of the OS class
   *
   * @return   The WindowsNT value
   */
  public static boolean isWindowsNT() {
    return isWindowsNT;
  }

  /**
   * Gets the Windows2000 attribute of the OS class
   *
   * @return   The Windows2000 value
   */
  public static boolean isWindows2000() {
    return isWindows2000;
  }

  /**
   * Gets the Macintosh attribute of the OS class
   *
   * @return   The Macintosh value
   */
  public static boolean isMacintosh() {
    return isMacintosh;
  }

  /**
   * Gets the Solaris attribute of the OS class
   *
   * @return   The Solaris value
   */
  public static boolean isSolaris() {
    return isSolaris;
  }

  /**
   * Gets the Linux attribute of the OS class
   *
   * @return   The Linux value
   */
  public static boolean isLinux() {
    return isLinux;
  }

  /**
   * Gets the Unix attribute of the OS class
   *
   * @return   The Unix value
   */
  public static boolean isUnix() {
    return isSolaris() || isLinux();
  }

  /**
   * Gets the CaseSensitive attribute of the OS class
   *
   * @return   The CaseSensitive value
   */
  public static boolean isCaseSensitive() {
    return isCaseSensitive;
  }

  /**
   * Gets the JDKVersion attribute of the OS class
   *
   * @return   The JDKVersion value
   */
  public static int getJDKVersion() {
    return jdkVersion;
  }

  /**
   * Gets the OneDotOne attribute of the OS class
   *
   * @return   The OneDotOne value
   */
  public static boolean isOneDotOne() {
    return jdkVersion == JDK1_1;
  }

  /**
   * Gets the OneDotTwo attribute of the OS class
   *
   * @return   The OneDotTwo value
   */
  public static boolean isOneDotTwo() {
    return jdkVersion == JDK1_2;
  }

  /**
   * Gets the OneDotThree attribute of the OS class
   *
   * @return   The OneDotThree value
   */
  public static boolean isOneDotThree() {
    return jdkVersion == JDK1_3;
  }

  /**
   * Gets the OneDotFour attribute of the OS class
   *
   * @return   The OneDotFour value
   */
  public static boolean isOneDotFour() {
    return jdkVersion == JDK1_4;
  }

  /**
   * Gets the OneDotThreeOrMore attribute of the OS class
   *
   * @return   The OneDotThreeOrMore value
   */
  public static boolean isOneDotThreeOrMore() {
    return jdkVersion >= JDK1_3;
  }

  /**
   * Description of the Method
   *
   * @param path           Description of Parameter
   * @exception Exception  Description of Exception
   */
  public static void openDocument(String path) throws Exception {
    if (isWindows2000()) {
      Runtime.getRuntime().exec(new String[]{"cmd /c start", path});
    }
    else if (isWindows()) {
      Runtime.getRuntime().exec(new String[]{"start", path});
    }
    else {
      System.err.println("OS.openDocument() not supported on this platform (" + System.getProperty("os.name"));
    }
  }

  static {
    String s = System.getProperty("os.name").toLowerCase();
    String version = System.getProperty("os.version").toLowerCase();

    if ("windows nt".equals(s) && "5.0".equals(version)) {
      isWindows2000 = true;
    }
    else if (s.equals("windows nt")) {
      isWindowsNT = true;
    }
    else if (s.startsWith("windows")) {
      // win95 or win98
      isWindows95 = true;
    }
    else if (s.equals("macintosh") || s.equals("macos") || s.equals("mac os")) {
      isMacintosh = true;
    }
    else if (s.equals("sunos") || s.equals("solaris")) {
      isSolaris = true;
      isCaseSensitive = true;
    }
    else if (s.equals("linux")) {
      isLinux = true;
      isCaseSensitive = true;
    }
  }

  // JDK version
  static {
    try {
      Class.forName("java.lang.ref.WeakReference");
      jdkVersion = JDK1_2;
    } catch (Exception e) {
    }
    try {
      Class.forName("javax.swing.UIDefaults$LazyInputMap");
      jdkVersion = JDK1_3;
    } catch (Exception e) {
    }
    try {
      Class.forName("java.lang.CharSequence");
      jdkVersion = JDK1_4;
    } catch (Exception e) {
    }
  }

}
