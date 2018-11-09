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
package com.l2fprod.contrib.nanoxml;

/**
 * An XMLParseException is thrown when an error occures while parsing an XML
 * string. <P>
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
 * @see       nanoxml.XMLElement
 * @version   1.6
 */
public class XMLParseException
     extends RuntimeException {

  /**
   * Where the error occurred, or -1 if the line number is unknown.
   */
  private int lineNr;


  /**
   * Creates an exception.
   *
   * @param tag      The name of the tag where the error is located.
   * @param message  A message describing what went wrong.
   */
  public XMLParseException(String tag,
      String message) {
    super("XML Parse Exception during parsing of "
        + ((tag == null) ? "the XML definition" : ("a " + tag + "-tag"))
        + ": " + message);
    this.lineNr = -1;
  }


  /**
   * Creates an exception.
   *
   * @param tag      The name of the tag where the error is located.
   * @param lineNr   The number of the line in the input.
   * @param message  A message describing what went wrong.
   */
  public XMLParseException(String tag,
      int lineNr,
      String message) {
    super("XML Parse Exception during parsing of "
        + ((tag == null) ? "the XML definition" : ("a " + tag + "-tag"))
        + " at line " + lineNr + ": " + message);
    this.lineNr = lineNr;
  }


  /**
   * Where the error occurred, or -1 if the line number is unknown.
   *
   * @return   The LineNr value
   */
  public int getLineNr() {
    return this.lineNr;
  }

}
