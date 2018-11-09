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

/**
 * Description of the Class
 *
 * @author    fred
 * @created   27 avril 2002
 */
public class TokenMgrError extends Error {

  /**
   * Indicates the reason why the exception is thrown. It will have one of the
   * above 4 values.
   */
  int errorCode;
  /*
   *  Ordinals for various reasons why an Error of this type can be thrown.
   */
  /**
   * Lexical error occured.
   */
  final static int LEXICAL_ERROR = 0;

  /**
   * An attempt wass made to create a second instance of a static token manager.
   */
  final static int STATIC_LEXER_ERROR = 1;

  /**
   * Tried to change to an invalid lexical state.
   */
  final static int INVALID_LEXICAL_STATE = 2;

  /**
   * Detected (and bailed out of) an infinite loop in the token manager.
   */
  final static int LOOP_DETECTED = 3;

  /*
   *  Constructors of various flavors follow.
   */
  /**
   * Constructor for the TokenMgrError object
   */
  public TokenMgrError() {
  }

  /**
   * Constructor for the TokenMgrError object
   *
   * @param message  Description of Parameter
   * @param reason   Description of Parameter
   */
  public TokenMgrError(String message, int reason) {
    super(message);
    errorCode = reason;
  }

  /**
   * Constructor for the TokenMgrError object
   *
   * @param EOFSeen      Description of Parameter
   * @param lexState     Description of Parameter
   * @param errorLine    Description of Parameter
   * @param errorColumn  Description of Parameter
   * @param errorAfter   Description of Parameter
   * @param curChar      Description of Parameter
   * @param reason       Description of Parameter
   */
  public TokenMgrError(boolean EOFSeen, int lexState, int errorLine, int errorColumn, String errorAfter, char curChar, int reason) {
    this(LexicalError(EOFSeen, lexState, errorLine, errorColumn, errorAfter, curChar), reason);
  }

  /**
   * You can also modify the body of this method to customize your error
   * messages. For example, cases like LOOP_DETECTED and INVALID_LEXICAL_STATE
   * are not of end-users concern, so you can return something like : "Internal
   * Error : Please file a bug report .... " from this method for such cases in
   * the release version of your parser.
   *
   * @return   The Message value
   */
  public String getMessage() {
    return super.getMessage();
  }

  /**
   * Replaces unprintable characters by their espaced (or unicode escaped)
   * equivalents in the given string
   *
   * @param str  The feature to be added to the Escapes attribute
   * @return     Description of the Returned Value
   */
  protected final static String addEscapes(String str) {
    StringBuffer retval = new StringBuffer();
    char ch;
    for (int i = 0; i < str.length(); i++) {
      switch (str.charAt(i)) {
        case 0:
          continue;
        case '\b':
          retval.append("\\b");
          continue;
        case '\t':
          retval.append("\\t");
          continue;
        case '\n':
          retval.append("\\n");
          continue;
        case '\f':
          retval.append("\\f");
          continue;
        case '\r':
          retval.append("\\r");
          continue;
        case '\"':
          retval.append("\\\"");
          continue;
        case '\'':
          retval.append("\\\'");
          continue;
        case '\\':
          retval.append("\\\\");
          continue;
        default:
          if ((ch = str.charAt(i)) < 0x20 || ch > 0x7e) {
            String s = "0000" + Integer.toString(ch, 16);
            retval.append("\\u" + s.substring(s.length() - 4, s.length()));
          }
          else {
            retval.append(ch);
          }
          continue;
      }
    }
    return retval.toString();
  }

  /**
   * Returns a detailed message for the Error when it is thrown by the token
   * manager to indicate a lexical error. Parameters : EOFSeen : indicates if
   * EOF caused the lexicl error curLexState : lexical state in which this error
   * occured errorLine : line number when the error occured errorColumn : column
   * number when the error occured errorAfter : prefix that was seen before this
   * error occured curchar : the offending character Note: You can customize the
   * lexical error message by modifying this method.
   *
   * @param EOFSeen      Description of Parameter
   * @param lexState     Description of Parameter
   * @param errorLine    Description of Parameter
   * @param errorColumn  Description of Parameter
   * @param errorAfter   Description of Parameter
   * @param curChar      Description of Parameter
   * @return             Description of the Returned Value
   */
  private final static String LexicalError(boolean EOFSeen, int lexState, int errorLine, int errorColumn, String errorAfter, char curChar) {
    return ("Lexical error at line " +
        errorLine + ", column " +
        errorColumn + ".  Encountered: " +
        (EOFSeen ? "<EOF> " : ("\"" + addEscapes(String.valueOf(curChar)) + "\"") + " (" + (int) curChar + "), ") +
        "after : \"" + addEscapes(errorAfter) + "\"");
  }
}
