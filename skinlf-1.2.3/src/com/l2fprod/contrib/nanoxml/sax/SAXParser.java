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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.Enumeration;
import java.util.Locale;
import org.xml.sax.AttributeList;
import org.xml.sax.helpers.AttributeListImpl;
import org.xml.sax.Parser;
import org.xml.sax.DocumentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.HandlerBase;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import com.l2fprod.contrib.nanoxml.XMLElement;
import com.l2fprod.contrib.nanoxml.XMLParseException;

/**
 * This is the SAX adapter for NanoXML. Note that this adapter is provided to
 * make NanoXML "buzzword compliant". If you're not stuck with SAX
 * compatibility, you should use the basic API (nanoxml.NanoXML) which is much
 * more programmer-friendly as it doesn't require the cumbersome use of event
 * handlers and has more powerful attribute-handling methods, but that is just
 * IMHO. If you really want to use the SAX API, I would like you to point to the
 * currently available native SAX parsers. <P>
 *
 * Here are some important notes:
 * <UL>
 *   <LI> The parser is non-validating.
 *   <LI> The DTD is fully ignored, including <CODE>&lt;!ENTITY...&gt;</CODE>.
 *
 *   <LI> SAXParser is reentrant.
 *   <LI> There is support for a document locator.
 *   <LI> There is no support for mixed content (elements containing both
 *   subelements and CDATA elements)
 *   <LI> There are no ignorableWhitespace() events
 *   <LI> Attribute types are always reported as CDATA
 * </UL>
 * <P>
 *
 * $Revision: 1.3 $<BR>
 * $Date: 2002/04/27 09:42:56 $<P>
 *
 *
 *
 * @author    Marc De Scheemaecker &lt;<A
 *      HREF="mailto:Marc.DeScheemaecker@advalvas.be" >
 *      Marc.DeScheemaecker@advalvas.be</A> &gt;
 * @created   27 avril 2002
 * @see       nanoxml.sax.SAXLocator
 * @see       nanoxml.XMLElement
 * @version   1.6
 */
public class SAXParser
     implements Parser {

  /**
   * The associated document handler.
   */
  private DocumentHandler documentHandler;

  /**
   * The associated error handler.
   */
  private ErrorHandler errorHandler;


  /**
   * Initializes the SAX parser adapter.
   */
  public SAXParser() {
    this.documentHandler = new HandlerBase();
    this.errorHandler = new HandlerBase();
  }


  /**
   * Sets the locale. Only locales using the language english are accepted.
   *
   * @param locale                        The new Locale value
   * @exception SAXException              Description of Exception
   */
  public void setLocale(Locale locale)
       throws SAXException {
    if ((locale == null) || (!locale.getLanguage().equals("en"))) {
      throw new SAXException("NanoXML/SAX doesn't support locale: "
          + locale);
    }
  }


  /**
   * Sets the entity resolver. As the DTD is ignored, this resolver is never
   * called.
   *
   * @param resolver  The new EntityResolver value
   */
  public void setEntityResolver(EntityResolver resolver) {
    // nothing to do
  }


  /**
   * Sets the DTD handler. As the DTD is ignored, this handler is never called.
   *
   * @param handler  The new DTDHandler value
   */
  public void setDTDHandler(DTDHandler handler) {
    // nothing to do
  }


  /**
   * Allows an application to register a document event handler.
   *
   * @param handler  The new DocumentHandler value
   */
  public void setDocumentHandler(DocumentHandler handler) {
    this.documentHandler = handler;
  }


  /**
   * Allows an applicaiton to register an error event handler.
   *
   * @param handler  The new ErrorHandler value
   */
  public void setErrorHandler(ErrorHandler handler) {
    this.errorHandler = handler;
  }


  /**
   * Parses an XML document.
   *
   * @param source                        Description of Parameter
   * @exception SAXException              Description of Exception
   * @exception IOException               Description of Exception
   */
  public void parse(InputSource source)
       throws SAXException,
      IOException {
    XMLElement topElement = this.createTopElement();
    Reader reader = source.getCharacterStream();
    SAXLocator locator = new SAXLocator(source.getSystemId());
    this.documentHandler.setDocumentLocator(locator);

    if (reader == null) {
      InputStream stream = source.getByteStream();
      String encoding = source.getEncoding();

      if (stream == null) {
        String systemId = source.getSystemId();

        if (systemId == null) {
          SAXParseException saxException
               = new SAXParseException("Invalid input source",
              locator);
          this.errorHandler.fatalError(saxException);
          return;
        }

        try {
          URL url = new URL(systemId);
          stream = url.openStream();
        } catch (MalformedURLException exception) {
          try {
            stream = new FileInputStream(systemId);
          } catch (FileNotFoundException exception2) {
            SAXParseException saxException
                 = new SAXParseException(null, locator,
                exception2);
            this.errorHandler.fatalError(saxException);
            return;
          } catch (SecurityException exception2) {
            SAXParseException saxException
                 = new SAXParseException(null, locator,
                exception2);
            this.errorHandler.fatalError(saxException);
            return;
          }
        }
      }

      if (encoding == null) {
        reader = new InputStreamReader(stream);
      }
      else {
        try {
          reader = new InputStreamReader(stream, encoding);
        } catch (UnsupportedEncodingException exception) {
          SAXParseException saxException
               = new SAXParseException(null, locator, exception);
          this.errorHandler.fatalError(saxException);
          return;
        }
      }
    }

    try {
      topElement.parseFromReader(reader);
    } catch (XMLParseException exception) {
      locator.setLineNr(exception.getLineNr());
      SAXParseException saxException
           = new SAXParseException(null, locator, exception);
      this.errorHandler.fatalError(saxException);
      this.documentHandler.endDocument();
      return;
    }

    locator.setLineNr(topElement.getLineNr());
    this.documentHandler.startDocument();
    this.handleSubTree(topElement, locator);
    this.documentHandler.endDocument();
  }


  /**
   * Parses an XML document from a system identifier (URI).
   *
   * @param systemId                      Description of Parameter
   * @exception SAXException              Description of Exception
   * @exception IOException               Description of Exception
   */
  public void parse(String systemId)
       throws SAXException,
      IOException {
    this.parse(new InputSource(systemId));
  }


  /**
   * Creates the top XML element. Override this method if you need a different
   * parsing behaviour.<P>
   *
   * The default behaviour is:
   * <UL>
   *   <LI> Case insensitive tag and attribute names, names converted to
   *   uppercase
   *   <LI> The only initial entities are amp, lt, gt, apos and quot.
   *   <LI> Skip formatting whitespace in PCDATA elements.
   * </UL>
   *
   *
   * @return   Description of the Returned Value
   */
  protected XMLElement createTopElement() {
    return new XMLElement();
  }


  /**
   * Handles a subtree of the parsed XML data structure.
   *
   * @param element                       Description of Parameter
   * @param locator                       Description of Parameter
   * @exception SAXException              Description of Exception
   */
  private void handleSubTree(XMLElement element,
      SAXLocator locator)
       throws SAXException {
    AttributeListImpl attrList = new AttributeListImpl();
    locator.setLineNr(element.getLineNr());
    Enumeration enum = element.enumeratePropertyNames();

    while (enum.hasMoreElements()) {
      String key = (String) (enum.nextElement());
      String value = element.getProperty(key);
      attrList.addAttribute(key, "CDATA", value);
    }

    this.documentHandler.startElement(element.getTagName(), attrList);

    if (element.getContents() == null) {
      enum = element.enumerateChildren();

      while (enum.hasMoreElements()) {
        this.handleSubTree((XMLElement) (enum.nextElement()),
            locator);
      }
    }
    else {
      char[] chars = element.getContents().toCharArray();
      this.documentHandler.characters(chars, 0, chars.length);
    }

    locator.setLineNr(-1);
    this.documentHandler.endElement(element.getTagName());
  }

}
