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
package com.l2fprod.gui.plaf.skin.impl.gtk.parser;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Hashtable;
import java.util.Enumeration;
import com.l2fprod.gui.plaf.skin.impl.gtk.*;
import com.l2fprod.gui.plaf.skin.*;

/**
 * Description of the Class
 *
 * @author    fred
 * @created   27 avril 2002
 */
public class GtkParser implements GtkParserConstants {

  /**
   * Description of the Field
   */
  public GtkParserTokenManager token_source;
  /**
   * Description of the Field
   */
  public Token token, jj_nt;

  Hashtable styleTable, classTable;
  URL directory;
  ASCII_UCodeESC_CharStream jj_input_stream;
  private int jj_ntk;
  private int jj_gen;
  private final int[] jj_la1 = new int[9];
  private final int[] jj_la1_0 = {0xc0000, 0xc0000, 0x20000, 0x10000, 0x4000, 0x200000, 0x100000, 0xffc00000, 0xffc00000,};
  private final int[] jj_la1_1 = {0xe00000, 0xe00000, 0x0, 0x0, 0x20000, 0x0, 0x0, 0x3ff, 0x3ff,};

  private java.util.Vector jj_expentries = new java.util.Vector();
  private int[] jj_expentry;
  private int jj_kind = -1;


  /**
   * Constructor for the GtkParser object
   *
   * @param filename       Description of Parameter
   * @exception Exception  Description of Exception
   */
  public GtkParser(String filename) throws Exception {
    this(new File(filename));
  }

  /**
   * Constructor for the GtkParser object
   *
   * @param file           Description of Parameter
   * @exception Exception  Description of Exception
   */
  public GtkParser(File file) throws Exception {
    this(SkinUtils.toURL(file));
  }

  /**
   * Constructor for the GtkParser object
   *
   * @param url            Description of Parameter
   * @exception Exception  Description of Exception
   */
  public GtkParser(URL url) throws Exception {
    this(url.openStream());
    directory = url;
    /*
     *  String urlpath = url.toString();
     *  int index = urlpath.lastIndexOf("/");
     *  if (index != -1)
     *  directory = new URL(urlpath.substring(0, index) + "/");
     *  else
     *  directory = new URL(urlpath + "/../");
     */
    //PENDING(fred): we could use directly the URL given because it could act as an url context...
    // need testing...
  }

  /**
   * Constructor for the GtkParser object
   *
   * @param stream  Description of Parameter
   */
  public GtkParser(java.io.InputStream stream) {
    jj_input_stream = new ASCII_UCodeESC_CharStream(stream, 1, 1);
    token_source = new GtkParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 9; i++) {
      jj_la1[i] = -1;
    }
  }

  /**
   * Constructor for the GtkParser object
   *
   * @param stream  Description of Parameter
   */
  public GtkParser(java.io.Reader stream) {
    jj_input_stream = new ASCII_UCodeESC_CharStream(stream, 1, 1);
    token_source = new GtkParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 9; i++) {
      jj_la1[i] = -1;
    }
  }

  /**
   * Constructor for the GtkParser object
   *
   * @param tm  Description of Parameter
   */
  public GtkParser(GtkParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 9; i++) {
      jj_la1[i] = -1;
    }
  }

  /**
   * Sets the Directory attribute of the GtkParser object
   *
   * @param dir  The new Directory value
   */
  public void setDirectory(URL dir) {
    directory = dir;
  }

  /**
   * Gets the Directory attribute of the GtkParser object
   *
   * @return   The Directory value
   */
  public URL getDirectory() {
    return directory;
  }

  /**
   * Gets the Styles attribute of the GtkParser object
   *
   * @return   The Styles value
   */
  public Hashtable getStyles() {
    return styleTable;
  }

  /**
   * Gets the Classes attribute of the GtkParser object
   *
   * @return   The Classes value
   */
  public Hashtable getClasses() {
    return classTable;
  }

  /**
   * Gets the Style attribute of the GtkParser object
   *
   * @param name  Description of Parameter
   * @return      The Style value
   */
  public GtkStyle getStyle(String name) {
    return (GtkStyle) getStyles().get(name);
  }

  /**
   * Gets the Class attribute of the GtkParser object
   *
   * @param name  Description of Parameter
   * @return      The Class value
   */
  public GtkClass getClass(String name) {
    return (GtkClass) getClasses().get(name);
  }

  /**
   * Gets the NextToken attribute of the GtkParser object
   *
   * @return   The NextToken value
   */
  public final Token getNextToken() {
    if (token.next != null) {
      token = token.next;
    }
    else {
      token = token.next = token_source.getNextToken();
    }
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

  /**
   * Gets the Token attribute of the GtkParser object
   *
   * @param index  Description of Parameter
   * @return       The Token value
   */
  public final Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) {
        t = t.next;
      }
      else {
        t = t.next = token_source.getNextToken();
      }
    }
    return t;
  }

  /**
   * Description of the Method
   *
   * @exception ParseException  Description of Exception
   */
  public final void buildStructure() throws ParseException {
    styleTable = new Hashtable();
    classTable = new Hashtable();
    label_1 :
    while (true) {
      switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
        case STYLE:
        case CLASS:
        case OTHER_TOKEN:
        case WIDGET_CLASS:
        case WIDGET:
          ;
          break;
        default:
          jj_la1[0] = jj_gen;
          break label_1;
      }
      switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
        case STYLE:
          styleDefinition();
          break;
        case CLASS:
          classDefinition();
          break;
        case OTHER_TOKEN:
          whatever();
          break;
        case WIDGET_CLASS:
          widgetClassDefinition();
          break;
        case WIDGET:
          widgetDefinition();
          break;
        default:
          jj_la1[1] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
      }
    }
    for (Enumeration e = getClasses().keys(); e.hasMoreElements(); ) {
      Object key = e.nextElement();
      GtkClass clas = (GtkClass) getClasses().get(key);
      for (int i = 0, c = clas.styles.size(); i < c; i++) {
        clas.styles.setElementAt(
            (GtkStyle) getStyles().get(clas.styles.elementAt(i)),
            i);
      }
    }
  }

  /**
   * Description of the Method
   *
   * @exception ParseException  Description of Exception
   */
  public final void whatever() throws ParseException {
    jj_consume_token(OTHER_TOKEN);
    readString();
  }

  /**
   * Description of the Method
   *
   * @exception ParseException  Description of Exception
   */
  public final void widgetClassDefinition() throws ParseException {
    jj_consume_token(WIDGET_CLASS);
    readString();
    jj_consume_token(STYLE);
    readString();
  }

  /**
   * Description of the Method
   *
   * @exception ParseException  Description of Exception
   */
  public final void widgetDefinition() throws ParseException {
    jj_consume_token(WIDGET);
    readString();
    jj_consume_token(STYLE);
    readString();
  }

  /**
   * Description of the Method
   *
   * @exception ParseException  Description of Exception
   */
  public final void styleDefinition() throws ParseException {
    GtkStyle style = new GtkStyle();
    style.parser = this;
    Token property;
    Token state = null;
    String value = null;
    jj_consume_token(STYLE);
    style.name = readString();
    jj_consume_token(LBRACK);
    label_2 :
    while (true) {
      switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
        case FONT:
          ;
          break;
        default:
          jj_la1[2] = jj_gen;
          break label_2;
      }
      jj_consume_token(FONT);
      jj_consume_token(ASSIGN);
      readString();
    }
    label_3 :
    while (true) {
      switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
        case EXTRA:
          ;
          break;
        default:
          jj_la1[3] = jj_gen;
          break label_3;
      }
      property = jj_consume_token(EXTRA);
      jj_consume_token(LCROC);
      state = jj_consume_token(STATE_TYPE);
      jj_consume_token(RCROC);
      jj_consume_token(ASSIGN);
      switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
        case STRING_LITERAL:
          value = readString();
          break;
        case LBRACK:
          jj_consume_token(LBRACK);
          value = "{";
          value = value + readFloat() + ",";
          jj_consume_token(COMMA);
          value = value + readFloat() + ",";
          jj_consume_token(COMMA);
          value = value + readFloat();
          value += "}";
          jj_consume_token(RBRACK);
          break;
        default:
          jj_la1[4] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
      }
      style.setProperty(property.image + "[" + state + "]",
          value);
    }
    switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
      case ENGINE:
        jj_consume_token(ENGINE);
        readString();
        jj_consume_token(LBRACK);
        GtkEngine engine = new GtkEngine();
        style.engine = engine;
        engine.style = style;
        label_4 :
        while (true) {
          switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
            case IMAGE:
              ;
              break;
            default:
              jj_la1[5] = jj_gen;
              break label_4;
          }
          imageDefinition(engine);
        }
        jj_consume_token(RBRACK);
        break;
      default:
        jj_la1[6] = jj_gen;
        ;
    }
    jj_consume_token(RBRACK);
    styleTable.put(style.name, style);
  }

  /**
   * Description of the Method
   *
   * @param engine              Description of Parameter
   * @exception ParseException  Description of Exception
   */
  public final void imageDefinition(GtkEngine engine) throws ParseException {
    GtkImage image = new GtkImage();
    Token property = null;
    Token value = null;
    jj_consume_token(IMAGE);
    jj_consume_token(LBRACK);
    label_5 :
    while (true) {
      switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
        case FUNCTION:
          property = jj_consume_token(FUNCTION);
          jj_consume_token(ASSIGN);
          value = jj_consume_token(FUNCTION_TYPE);
          image.setProperty(property.image, value.image);
          break;
        case RECOLORABLE:
          property = jj_consume_token(RECOLORABLE);
          jj_consume_token(ASSIGN);
          value = jj_consume_token(BOOLEAN);
          image.setProperty(property.image, value.image);
          break;
        case STATE:
          property = jj_consume_token(STATE);
          jj_consume_token(ASSIGN);
          value = jj_consume_token(STATE_TYPE);
          image.setProperty(property.image, value.image);
          break;
        case DETAIL:
          property = jj_consume_token(DETAIL);
          jj_consume_token(ASSIGN);
          image.setProperty(property.image, readString());
          break;
        case FILE:
          property = jj_consume_token(FILE);
          jj_consume_token(ASSIGN);
          image.setProperty(property.image, readString());
          break;
        case BORDER:
          property = jj_consume_token(BORDER);
          jj_consume_token(ASSIGN);
          image.setProperty(property.image, borderDefinition());
          break;
        case STRETCH:
          property = jj_consume_token(STRETCH);
          jj_consume_token(ASSIGN);
          value = jj_consume_token(BOOLEAN);
          image.setProperty(property.image, value.image);
          break;
        case OVERLAY_FILE:
          property = jj_consume_token(OVERLAY_FILE);
          jj_consume_token(ASSIGN);
          image.setProperty(property.image, readString());
          break;
        case OVERLAY_BORDER:
          property = jj_consume_token(OVERLAY_BORDER);
          jj_consume_token(ASSIGN);
          image.setProperty(property.image, borderDefinition());
          break;
        case OVERLAY_STRETCH:
          property = jj_consume_token(OVERLAY_STRETCH);
          jj_consume_token(ASSIGN);
          value = jj_consume_token(BOOLEAN);
          image.setProperty(property.image, value.image);
          break;
        case SHADOW:
          property = jj_consume_token(SHADOW);
          jj_consume_token(ASSIGN);
          value = jj_consume_token(SHADOW_TYPE);
          image.setProperty(property.image, value.image);
          break;
        case GAP_START_FILE:
          property = jj_consume_token(GAP_START_FILE);
          jj_consume_token(ASSIGN);
          image.setProperty(property.image, readString());
          break;
        case GAP_START_BORDER:
          property = jj_consume_token(GAP_START_BORDER);
          jj_consume_token(ASSIGN);
          image.setProperty(property.image, borderDefinition());
          break;
        case GAP_END_FILE:
          property = jj_consume_token(GAP_END_FILE);
          jj_consume_token(ASSIGN);
          image.setProperty(property.image, readString());
          break;
        case GAP_END_BORDER:
          property = jj_consume_token(GAP_END_BORDER);
          jj_consume_token(ASSIGN);
          image.setProperty(property.image, borderDefinition());
          break;
        case GAP_SIDE:
          property = jj_consume_token(GAP_SIDE);
          jj_consume_token(ASSIGN);
          value = jj_consume_token(DIRECTION);
          image.setProperty(property.image, value.image);
          break;
        case GAP_FILE:
          property = jj_consume_token(GAP_FILE);
          jj_consume_token(ASSIGN);
          image.setProperty(property.image, readString());
          break;
        case GAP_BORDER:
          property = jj_consume_token(GAP_BORDER);
          jj_consume_token(ASSIGN);
          image.setProperty(property.image, borderDefinition());
          break;
        case ORIENTATION:
          property = jj_consume_token(ORIENTATION);
          jj_consume_token(ASSIGN);
          value = jj_consume_token(ORIENTATION_TYPE);
          image.setProperty(property.image, value.image);
          break;
        case ARROW_DIRECTION:
          property = jj_consume_token(ARROW_DIRECTION);
          jj_consume_token(ASSIGN);
          value = jj_consume_token(DIRECTION);
          image.setProperty(property.image, value.image);
          break;
        default:
          jj_la1[7] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
      }
      switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
        case FUNCTION:
        case RECOLORABLE:
        case FILE:
        case BORDER:
        case STRETCH:
        case SHADOW:
        case STATE:
        case DETAIL:
        case GAP_SIDE:
        case GAP_FILE:
        case GAP_BORDER:
        case GAP_START_FILE:
        case GAP_START_BORDER:
        case GAP_END_FILE:
        case GAP_END_BORDER:
        case OVERLAY_FILE:
        case OVERLAY_BORDER:
        case OVERLAY_STRETCH:
        case ORIENTATION:
        case ARROW_DIRECTION:
          ;
          break;
        default:
          jj_la1[8] = jj_gen;
          break label_5;
      }
    }
    jj_consume_token(RBRACK);
    engine.addImage(image);
  }

  /**
   * Description of the Method
   *
   * @return                    Description of the Returned Value
   * @exception ParseException  Description of Exception
   */
  public final GtkBorder borderDefinition() throws ParseException {
    GtkBorder border = new GtkBorder();
    Token tok = null;
    jj_consume_token(LBRACK);
    tok = jj_consume_token(DECIMAL_LITERAL);
    border.left = Integer.parseInt(tok.image);
    jj_consume_token(COMMA);
    tok = jj_consume_token(DECIMAL_LITERAL);
    border.right = Integer.parseInt(tok.image);
    jj_consume_token(COMMA);
    tok = jj_consume_token(DECIMAL_LITERAL);
    border.top = Integer.parseInt(tok.image);
    jj_consume_token(COMMA);
    tok = jj_consume_token(DECIMAL_LITERAL);
    border.bottom = Integer.parseInt(tok.image);
    jj_consume_token(RBRACK);
     {
      if (true) {
        return border;
      }
    }
    throw new Error("Missing return statement in function");
  }

  /**
   * Description of the Method
   *
   * @exception ParseException  Description of Exception
   */
  public final void classDefinition() throws ParseException {
    GtkClass clas;
    jj_consume_token(CLASS);
    String name = readString();
    clas = (GtkClass) classTable.get(name);
    if (clas == null) {
      clas = new GtkClass();
      clas.setName(name);
      classTable.put(clas.name, clas);
    }
    jj_consume_token(STYLE);
    clas.addStyleName(readString());
  }

  /**
   * Description of the Method
   *
   * @return                    Description of the Returned Value
   * @exception ParseException  Description of Exception
   */
  public final String readString() throws ParseException {
    String value = null;
    Token toks = null;
    toks = jj_consume_token(STRING_LITERAL);
    value = new String();
    for (int i = 1; i < toks.image.length() - 1; i++) {
      if (toks.image.charAt(i) == '\\') {
        if ((toks.image.charAt(i + 1) == '\\') || (toks.image.charAt(i + 1) == '"')) {
          i++;
        }
      }
      value = value + toks.image.charAt(i);
    }
     {
      if (true) {
        return (value);
      }
    }
    throw new Error("Missing return statement in function");
  }

  /**
   * Description of the Method
   *
   * @return                    Description of the Returned Value
   * @exception ParseException  Description of Exception
   */
  public final float readFloat() throws ParseException {
    Token toks = null;
    toks = jj_consume_token(DECIMAL_LITERAL);
     {
      if (true) {
        return Float.valueOf(token.image).floatValue();
      }
    }
    throw new Error("Missing return statement in function");
  }

  /**
   * Description of the Method
   *
   * @param stream  Description of Parameter
   */
  public void ReInit(java.io.InputStream stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 9; i++) {
      jj_la1[i] = -1;
    }
  }

  /**
   * Description of the Method
   *
   * @param stream  Description of Parameter
   */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 9; i++) {
      jj_la1[i] = -1;
    }
  }

  /**
   * Description of the Method
   *
   * @param tm  Description of Parameter
   */
  public void ReInit(GtkParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 9; i++) {
      jj_la1[i] = -1;
    }
  }

  /**
   * Description of the Method
   *
   * @return   Description of the Returned Value
   */
  public final ParseException generateParseException() {
    jj_expentries.removeAllElements();
    boolean[] la1tokens = new boolean[56];
    for (int i = 0; i < 56; i++) {
      la1tokens[i] = false;
    }
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 9; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1 << j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1 << j)) != 0) {
            la1tokens[32 + j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 56; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.addElement(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = (int[]) jj_expentries.elementAt(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /**
   * Description of the Method
   */
  public final void enable_tracing() {
  }

  /**
   * Description of the Method
   */
  public final void disable_tracing() {
  }

  /**
   * Description of the Method
   *
   * @param kind                Description of Parameter
   * @return                    Description of the Returned Value
   * @exception ParseException  Description of Exception
   */
  private final Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) {
      token = token.next;
    }
    else {
      token = token.next = token_source.getNextToken();
    }
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  /**
   * Description of the Method
   *
   * @return   Description of the Returned Value
   */
  private final int jj_ntk() {
    if ((jj_nt = token.next) == null) {
      return (jj_ntk = (token.next = token_source.getNextToken()).kind);
    }
    else {
      return (jj_ntk = jj_nt.kind);
    }
  }

  /**
   * The main program for the GtkParser class
   *
   * @param args           The command line arguments
   * @exception Exception  Description of Exception
   */
  public static void main(String[] args) throws Exception {
    GtkParser parser = new GtkParser(new FileInputStream(args[0]));
    parser.buildStructure();
  }

}
