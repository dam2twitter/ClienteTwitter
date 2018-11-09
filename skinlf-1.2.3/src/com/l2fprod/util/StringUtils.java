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

import java.util.StringTokenizer;

/**
 * Description of the Class
 *
 * @author    fred
 * @created   27 avril 2002
 */
public class StringUtils {

  /**
   * Description of the Method
   *
   * @param txt   Description of Parameter
   * @param from  Description of Parameter
   * @param to    Description of Parameter
   * @return      Description of the Returned Value
   */
  public static String replace(String txt, String from, String to) {
    int index = txt.indexOf(from);

    if (index != -1) {
      txt = txt.substring(0, index) + to + replace(txt.substring(index + from.length()), from, to);
    }

    return txt;
  }

  /**
   * Description of the Method
   *
   * @param s      Description of Parameter
   * @param delim  Description of Parameter
   * @return       Description of the Returned Value
   */
  public static String[] splitString(String s, String delim) {
    StringTokenizer token = new StringTokenizer(s, delim);
    String[] split = new String[token.countTokens()];
    int i = 0;
    while (token.hasMoreTokens()) {
      split[i] = token.nextToken();
      i++;
    }
    return split;
  }

  /**
   * Description of the Method
   *
   * @param s       Description of Parameter
   * @param length  Description of Parameter
   * @param c       Description of Parameter
   * @return        Description of the Returned Value
   */
  public static String format(String s, int length, char c) {
    int len = s.length();
    if (len >= length) {
      return s;
    }
    while (len < length) {
      s += c;
      len++;
    }
    return s;
  }

}
