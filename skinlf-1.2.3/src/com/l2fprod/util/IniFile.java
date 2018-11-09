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

import java.io.*;
import java.util.*;
import java.net.URL;

/**
 * @author    $Author: l2fprod $
 * @created   27 avril 2002
 * @version   $Revision: 1.5 $, $Date: 2002/04/27 11:27:42 $
 */
public class IniFile {

  Hashtable sections;

  /**
   * Constructor for the IniFile object
   */
  public IniFile() {
    sections = new Hashtable();
  }

  /**
   * Constructor for the IniFile object
   *
   * @param filename                   Description of Parameter
   * @exception FileNotFoundException  Description of Exception
   */
  public IniFile(String filename) throws FileNotFoundException {
    this();
    load(filename);
  }

  /**
   * Constructor for the IniFile object
   *
   * @param url              Description of Parameter
   * @exception IOException  Description of Exception
   */
  public IniFile(URL url) throws IOException {
    this();
    load(url.openStream());
  }

  /**
   * Constructor for the IniFile object
   *
   * @param input            Description of Parameter
   * @exception IOException  Description of Exception
   */
  public IniFile(InputStream input) throws IOException {
    this();
    load(input);
  }

  /**
   * Sets the KeyValue attribute of the IniFile object
   *
   * @param section  The new KeyValue value
   * @param key      The new KeyValue value
   * @param value    The new KeyValue value
   */
  public void setKeyValue(String section, String key, String value) {
    try {
      getSection(section).put(key.toLowerCase(), value);
    } catch (NullPointerException e) {
      e.printStackTrace();
    }
  }

  /**
   * Gets the Sections attribute of the IniFile object
   *
   * @return   The Sections value
   */
  public Hashtable getSections() {
    return sections;
  }

  /**
   * Gets the Section attribute of the IniFile object
   *
   * @param section  Description of Parameter
   * @return         The Section value
   */
  public Hashtable getSection(String section) {
    return (Hashtable) (sections.get(section.toLowerCase()));
  }

  /**
   * Gets the NullOrEmpty attribute of the IniFile object
   *
   * @param section  Description of Parameter
   * @param key      Description of Parameter
   * @return         The NullOrEmpty value
   */
  public boolean isNullOrEmpty(String section, String key) {
    String value = getKeyValue(section, key);
    return (value == null || value.length() == 0);
  }

  /**
   * Gets the KeyValue attribute of the IniFile object
   *
   * @param section  Description of Parameter
   * @param key      Description of Parameter
   * @return         The KeyValue value
   */
  public String getKeyValue(String section, String key) {
    try {
      return (String) getSection(section).get(key.toLowerCase());
    } catch (NullPointerException e) {
      return null;
    }
  }

  /**
   * Gets the KeyIntValue attribute of the IniFile object
   *
   * @param section  Description of Parameter
   * @param key      Description of Parameter
   * @return         The KeyIntValue value
   */
  public int getKeyIntValue(String section, String key) {
    return getKeyIntValue(section, key, 0);
  }

  /**
   * Gets the KeyIntValue attribute of the IniFile object
   *
   * @param section       Description of Parameter
   * @param key           Description of Parameter
   * @param defaultValue  Description of Parameter
   * @return              The KeyIntValue value
   */
  public int getKeyIntValue(String section, String key, int defaultValue) {
    String value = getKeyValue(section, key.toLowerCase());
    if (value == null) {
      return defaultValue;
    }
    else {
      try {
        return Integer.parseInt(value);
      } catch (NumberFormatException e) {
        return 0;
      }
    }
  }

  /**
   * Gets the KeysAndValues attribute of the IniFile object
   *
   * @param aSection  Description of Parameter
   * @return          The KeysAndValues value
   */
  public String[][] getKeysAndValues(String aSection) {
    Hashtable section = getSection(aSection);
    if (section == null) {
      return null;
    }
    String[][] results = new String[section.size()][2];
    int i = 0;
    for (Enumeration f = section.keys(), g = section.elements(); f.hasMoreElements(); i++) {
      results[i][0] = (String) f.nextElement();
      results[i][1] = (String) g.nextElement();
    }
    return results;
  }

  /**
   * Description of the Method
   *
   * @param filename                   Description of Parameter
   * @exception FileNotFoundException  Description of Exception
   */
  public void load(String filename) throws FileNotFoundException {
    load(new FileInputStream(filename));
  }

  /**
   * Description of the Method
   *
   * @param filename         Description of Parameter
   * @exception IOException  Description of Exception
   */
  public void save(String filename) throws IOException {
    save(new FileOutputStream(filename));
  }

  /**
   * Description of the Method
   *
   * @param in  Description of Parameter
   */
  public void load(InputStream in) {
    try {
      BufferedReader input = new BufferedReader(new InputStreamReader(in));
      String read;
      Hashtable section = null;
      String section_name;
      while ((read = input.readLine()) != null) {
        if (read.startsWith(";") || read.startsWith("#")) {
          continue;
        }
        else if (read.startsWith("[")) {
          // new section
          section_name = read.substring(1, read.indexOf("]")).toLowerCase();
          section = (Hashtable) sections.get(section_name);
          if (section == null) {
            section = new Hashtable();
            sections.put(section_name, section);
          }
        }
        else if (read.indexOf("=") != -1 && section != null) {
          // new key
          String key = read.substring(0, read.indexOf("=")).trim().toLowerCase();
          String value = read.substring(read.indexOf("=") + 1).trim();
          section.put(key, value);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Description of the Method
   *
   * @param out  Description of Parameter
   */
  public void save(OutputStream out) {
    try {
      PrintWriter output = new PrintWriter(out);
      String section;
      for (Enumeration e = sections.keys(); e.hasMoreElements(); ) {
        section = (String) e.nextElement();
        output.println("[" + section + "]");
        for (Enumeration f = getSection(section).keys(), g = getSection(section).elements();
            f.hasMoreElements(); ) {
          output.println(f.nextElement() + "=" + g.nextElement());
        }
      }
      output.flush();
      output.close();
      out.flush();
      out.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Adds a feature to the Section attribute of the IniFile object
   *
   * @param section  The feature to be added to the Section attribute
   */
  public void addSection(String section) {
    sections.put(section.toLowerCase(), new Hashtable());
  }

  /**
   * Description of the Method
   *
   * @param section  Description of Parameter
   */
  public void removeSection(String section) {
  }

  /**
   * Simple test function
   *
   * @param args           The command line arguments
   * @exception Exception  Description of Exception
   */
  public static void main(String[] args) throws Exception {
    (new IniFile()).load(new FileInputStream(args[0]));
  }
}
