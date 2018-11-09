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

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Description of the Class
 *
 * @author    fred
 * @created   27 avril 2002
 */
public class ZipResourceLoader {

  Hashtable resources = new Hashtable();

  URLStreamHandler handler =
    new URLStreamHandler() {
      protected URLConnection openConnection(URL u) {
        return new ZipURLConnection(u);
      }

      protected void parseURL(URL u, String spec, int start, int limit) {
        String file = u.getFile();
        int index = file.indexOf("/");
        if (index != -1) {
          String path = file.substring(0, index);
          setURL(u, u.getProtocol(), u.getHost(), u.getPort(),
              path + "/" + spec, u.getRef());
        }
        else {
          setURL(u, u.getProtocol(), u.getHost(), u.getPort(),
              spec, u.getRef());
        }
      }

      protected String toExternalForm(URL u) {
        return "ziploader://" + u.getFile();
      }
    };

  /**
   * Constructor for the ZipResourceLoader object
   *
   * @param p_JarUrl       Description of Parameter
   * @exception Exception  Description of Exception
   */
  public ZipResourceLoader(URL p_JarUrl) throws Exception {
    BufferedInputStream bis = new BufferedInputStream(p_JarUrl.openStream());
    ZipInputStream input = new ZipInputStream(bis);

    ZipEntry entry = null;
    while ((entry = input.getNextEntry()) != null) {
      if (entry.isDirectory()) {
        continue;
      }
      else {
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        int read;
        while ((read = input.read()) != -1) {
          outBuffer.write(read);
        }
        resources.put(entry.getName(), outBuffer.toByteArray());
      }
    }
  }

  /**
   * Gets the ResourceAsStream attribute of the ZipResourceLoader object
   *
   * @param name                       Description of Parameter
   * @return                           The ResourceAsStream value
   * @exception MalformedURLException  Description of Exception
   * @exception IOException            Description of Exception
   */
  public InputStream getResourceAsStream(String name) throws MalformedURLException, IOException {
    return getResource(name).openStream();
  }

  /**
   * Gets the Resource attribute of the ZipResourceLoader object
   *
   * @param name                       Description of Parameter
   * @return                           The Resource value
   * @exception MalformedURLException  Description of Exception
   */
  public URL getResource(final String name) throws MalformedURLException {
    if (resources.get(name) == null) {
      return null;
    }
    return new URL("ziploader", null, -1, name, handler);
  }

  /**
   * Gets the ZipResource attribute of the ZipResourceLoader object
   *
   * @param name  Description of Parameter
   * @return      The ZipResource value
   */
  public ZipResource getZipResource(String name) {
    return new ZipResource(name);
  }

  /**
   * Gets the ZipResource attribute of the ZipResourceLoader object
   *
   * @param name  Description of Parameter
   * @return      The ZipResource value
   */
  public ZipResource getZipResource(URL name) {
    return getZipResource(name.getFile());
  }

  /**
   * Description of the Method
   *
   * @return   Description of the Returned Value
   */
  public Enumeration entries() {
    return resources.keys();
  }

  /**
   * Description of the Method
   */
  public void dump() {
    for (Enumeration e = resources.keys(); e.hasMoreElements(); ) {
      String name = (String) e.nextElement();
      System.out.println(name + " size = " + ((byte[]) resources.get(name)).length);
    }
  }

  /**
   * Description of the Method
   */
  public void release() {
    resources.clear();
    resources = null;
  }

  /**
   * Gets the URLContent attribute of the ZipResourceLoader object
   *
   * @param name  Description of Parameter
   * @return      The URLContent value
   */
  private byte[] getURLContent(String name) {
    byte[] data = (byte[]) resources.get(name);
    if (data == null && name.startsWith("/")) {
      data = (byte[]) resources.get(name.substring(1));
    }
    return data;
  }

  /**
   * The main program for the ZipResourceLoader class
   *
   * @param args           The command line arguments
   * @exception Exception  Description of Exception
   */
  public static void main(String[] args) throws Exception {
    ZipResourceLoader loader = new ZipResourceLoader(new File(args[0]).toURL());
    for (Enumeration e = loader.entries(); e.hasMoreElements(); ) {
      String name = (String) e.nextElement();
      System.out.println(name);
      URL url = loader.getResource(name);
      System.out.println(url);
      System.out.println(url.openStream());
      System.out.println(loader.getZipResource(name).getInputStream());
    }
  }

  /**
   * Description of the Class
   *
   * @author    fred
   * @created   27 avril 2002
   */
  public class ZipResource {
    String m_Name;

    /**
     * Constructor for the ZipResource object
     *
     * @param p_Name  Description of Parameter
     */
    public ZipResource(String p_Name) {
      m_Name = p_Name;
    }

    /**
     * Gets the URL attribute of the ZipResource object
     *
     * @return                                    The URL value
     * @exception java.net.MalformedURLException  Description of Exception
     */
    public URL getURL() throws java.net.MalformedURLException {
      return new URL("http", null, -1, m_Name);
    }

    /**
     * Gets the InputStream attribute of the ZipResource object
     *
     * @return   The InputStream value
     */
    public InputStream getInputStream() {
      ByteArrayInputStream input = new ByteArrayInputStream(ZipResourceLoader.this.getURLContent(m_Name));
      return input;
    }

    /**
     * Gets the URLContent attribute of the ZipResource object
     *
     * @return   The URLContent value
     */
    public byte[] getURLContent() {
      return ZipResourceLoader.this.getURLContent(m_Name);
    }
  }

  /**
   * Description of the Class
   *
   * @author    fred
   * @created   27 avril 2002
   */
  class ZipURLConnection extends URLConnection {
    /**
     * Constructor for the ZipURLConnection object
     *
     * @param p_Url  Description of Parameter
     */
    public ZipURLConnection(URL p_Url) {
      super(p_Url);
    }

    /**
     * Gets the InputStream attribute of the ZipURLConnection object
     *
     * @return                 The InputStream value
     * @exception IOException  Description of Exception
     */
    public InputStream getInputStream() throws IOException {
      ByteArrayInputStream input = new ByteArrayInputStream((byte[]) resources.get(getURL().getFile()));
      return input;
    }

    /**
     * Description of the Method
     *
     * @exception IOException  Description of Exception
     */
    public void connect() throws IOException {
      if (resources == null || resources.get(getURL().getFile()) == null) {
        throw new IOException("No data for " + getURL());
      }
    }
  }

}
