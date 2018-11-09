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
package com.l2fprod.contrib.nanoxml.sax;

import org.xml.sax.Locator;

/**
 * A SAXLocator allows applications to associate a SAX event with a document
 * location. <P>
 *
 * Note that the results returned by such locator is valid only during the scope
 * of each document handler method: the application will receive unpredictable
 * results if it attempts to use the locator at any other time. <P>
 *
 * NanoXML only supports line numbers and system IDs, hence <CODE>getColumnNumber()</CODE>
 * always returns <CODE>-1</CODE> and <CODE>getPublicId()</CODE> always returns
 * <CODE>null</CODE>. <P>
 *
 * $Revision: 1.2 $<BR>
 * $Date: 2002/04/27 09:42:56 $<P>
 *
 *
 *
 * @author    Marc De Scheemaecker &lt;<A
 *      HREF="mailto:Marc.DeScheemaecker@advalvas.be" >
 *      Marc.DeScheemaecker@advalvas.be</A> &gt;
 * @created   27 avril 2002
 * @see       nanoxml.sax.SAXParser
 * @see       nanoxml.XMLElement
 * @version   1.6
 */
class SAXLocator
     implements Locator {

  /**
   * The associated system ID. This ID is only set if the application calls the
   * parse method with a system ID.
   *
   * @see   org.xml.sax.Parser#parse(java.lang.String)
   */
  private String systemId;

  /**
   * The current line number.
   */
  private int lineNr;


  /**
   * Creates a new locator.
   *
   * @param systemId  the associated system ID
   */
  SAXLocator(String systemId) {
    this.systemId = systemId;
    this.lineNr = -1;
  }


  /**
   * Returns the public identifier for the current document event. As there is
   * no support for public identifiers in NanoXML, this method simply returns
   * <CODE>null</CODE>.
   *
   * @return   The PublicId value
   */
  public String getPublicId() {
    return null;
  }


  /**
   * Returns the system identifier for the current document event. This ID is
   * only set if the application calls the parse method with a system ID.
   *
   * @return   The SystemId value
   * @see      org.xml.sax.Parser#parse(java.lang.String)
   */
  public String getSystemId() {
    return this.systemId;
  }


  /**
   * Returns the line number associated with the current document event. As
   * NanoXML reports the line number at the <I>beginning</I> of the element
   * (which make more sense IMHO), this number might not be what you expect. For
   * this reason, endElement() and endDocument() events are never associated
   * with a line number.
   *
   * @return   The LineNumber value
   */
  public int getLineNumber() {
    return this.lineNr;
  }


  /**
   * Returns the column number where the current event ends. As NanoXML has no
   * support for columns, this method simply returns -1.
   *
   * @return   The ColumnNumber value
   */
  public int getColumnNumber() {
    return -1;
  }


  /**
   * Sets the current line number.
   *
   * @param lineNr  the new line number
   */
  void setLineNr(int lineNr) {
    this.lineNr = lineNr;
  }

}
