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
public class GtkParserTokenManager implements GtkParserConstants {
  /**
   * Description of the Field
   */
  protected char curChar;
  StringBuffer image;
  int jjimageLen;
  int lengthOfMatch;

  int curLexState = 0;
  int defaultLexState = 0;
  int jjnewStateCnt;
  int jjround;
  int jjmatchedPos;
  int jjmatchedKind;
  private ASCII_UCodeESC_CharStream input_stream;
  private final int[] jjrounds = new int[242];
  private final int[] jjstateSet = new int[484];
  /**
   * Description of the Field
   */
  public final static String[] jjstrLiteralImages = {
      "", null, null, null, null, null, null, null, null, null, null, null, null,
      null, null, null, null, "\146\157\156\164", "\163\164\171\154\145",
      "\143\154\141\163\163", "\145\156\147\151\156\145", "\151\155\141\147\145",
      "\146\165\156\143\164\151\157\156", "\162\145\143\157\154\157\162\141\142\154\145", "\146\151\154\145",
      "\142\157\162\144\145\162", "\163\164\162\145\164\143\150", "\163\150\141\144\157\167",
      "\163\164\141\164\145", "\144\145\164\141\151\154", "\147\141\160\137\163\151\144\145",
      "\147\141\160\137\146\151\154\145", "\147\141\160\137\142\157\162\144\145\162",
      "\147\141\160\137\163\164\141\162\164\137\146\151\154\145", "\147\141\160\137\163\164\141\162\164\137\142\157\162\144\145\162",
      "\147\141\160\137\145\156\144\137\146\151\154\145", "\147\141\160\137\145\156\144\137\142\157\162\144\145\162",
      "\157\166\145\162\154\141\171\137\146\151\154\145", "\157\166\145\162\154\141\171\137\142\157\162\144\145\162",
      "\157\166\145\162\154\141\171\137\163\164\162\145\164\143\150", "\157\162\151\145\156\164\141\164\151\157\156",
      "\141\162\162\157\167\137\144\151\162\145\143\164\151\157\156", null, null, null, null, null, "\133", "\135", "\173", "\175", "\75", "\54",
      null, "\167\151\144\147\145\164\137\143\154\141\163\163",
      "\167\151\144\147\145\164",};
  /**
   * Description of the Field
   */
  public final static String[] lexStateNames = {
      "DEFAULT",
      "IN_SINGLE_LINE_COMMENT",
      "IN_FORMAL_COMMENT",
      "IN_MULTI_LINE_COMMENT",
      };
  /**
   * Description of the Field
   */
  public final static int[] jjnewLexState = {
      -1, -1, -1, -1, -1, -1, 1, 2, 3, 0, 0, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
      -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
      -1, -1, -1, -1, -1, -1,
      };
  final static long[] jjbitVec0 = {
      0xfffffffffffffffeL, 0xffffffffffffffffL, 0xffffffffffffffffL, 0xffffffffffffffffL
      };
  final static long[] jjbitVec2 = {
      0x0L, 0x0L, 0xffffffffffffffffL, 0xffffffffffffffffL
      };
  final static int[] jjnextStates = {
      8, 9, 11, 8, 9, 13, 11, 219, 226, 233, 241, 201, 206, 211, 188, 191,
      199, 162, 171, 176, 183, 139, 146, 132, 134, 121, 126, 105, 112, 116, 98, 100,
      101, 91, 16, 94, 10, 12, 14,
      };
  final static long[] jjtoToken = {
      0xffffffffffe001L,
      };
  final static long[] jjtoSkip = {
      0xe3eL,
      };
  final static long[] jjtoSpecial = {
      0xe00L,
      };
  final static long[] jjtoMore = {
      0x11c0L,
      };

  /**
   * Constructor for the GtkParserTokenManager object
   *
   * @param stream  Description of Parameter
   */
  public GtkParserTokenManager(ASCII_UCodeESC_CharStream stream) {
    if (ASCII_UCodeESC_CharStream.staticFlag) {
      throw new Error("ERROR: Cannot use a static CharStream class with a non-static lexical analyzer.");
    }
    input_stream = stream;
  }

  /**
   * Constructor for the GtkParserTokenManager object
   *
   * @param stream    Description of Parameter
   * @param lexState  Description of Parameter
   */
  public GtkParserTokenManager(ASCII_UCodeESC_CharStream stream, int lexState) {
    this(stream);
    SwitchTo(lexState);
  }

  /**
   * Gets the NextToken attribute of the GtkParserTokenManager object
   *
   * @return   The NextToken value
   */
  public final Token getNextToken() {
    int kind;
    Token specialToken = null;
    Token matchedToken;
    int curPos = 0;

    EOFLoop :
    for (; ; ) {
      try {
        curChar = input_stream.BeginToken();
      } catch (java.io.IOException e) {
        jjmatchedKind = 0;
        matchedToken = jjFillToken();
        matchedToken.specialToken = specialToken;
        return matchedToken;
      }
      image = null;
      jjimageLen = 0;

      for (; ; ) {
        switch (curLexState) {
          case 0:
            try {
              while (curChar <= 32 && (0x100003600L & (1L << curChar)) != 0L) {
                curChar = input_stream.BeginToken();
              }
            } catch (java.io.IOException e1) {
              continue EOFLoop;
            }
            jjmatchedKind = 0x7fffffff;
            jjmatchedPos = 0;
            curPos = jjMoveStringLiteralDfa0_0();
            break;
          case 1:
            jjmatchedKind = 0x7fffffff;
            jjmatchedPos = 0;
            curPos = jjMoveStringLiteralDfa0_1();
            if (jjmatchedPos == 0 && jjmatchedKind > 12) {
              jjmatchedKind = 12;
            }
            break;
          case 2:
            jjmatchedKind = 0x7fffffff;
            jjmatchedPos = 0;
            curPos = jjMoveStringLiteralDfa0_2();
            if (jjmatchedPos == 0 && jjmatchedKind > 12) {
              jjmatchedKind = 12;
            }
            break;
          case 3:
            jjmatchedKind = 0x7fffffff;
            jjmatchedPos = 0;
            curPos = jjMoveStringLiteralDfa0_3();
            if (jjmatchedPos == 0 && jjmatchedKind > 12) {
              jjmatchedKind = 12;
            }
            break;
        }
        if (jjmatchedKind != 0x7fffffff) {
          if (jjmatchedPos + 1 < curPos) {
            input_stream.backup(curPos - jjmatchedPos - 1);
          }
          if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L) {
            matchedToken = jjFillToken();
            matchedToken.specialToken = specialToken;
            if (jjnewLexState[jjmatchedKind] != -1) {
              curLexState = jjnewLexState[jjmatchedKind];
            }
            return matchedToken;
          }
          else if ((jjtoSkip[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L) {
            if ((jjtoSpecial[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L) {
              matchedToken = jjFillToken();
              if (specialToken == null) {
                specialToken = matchedToken;
              }
              else {
                matchedToken.specialToken = specialToken;
                specialToken = (specialToken.next = matchedToken);
              }
              SkipLexicalActions(matchedToken);
            }
            else {
              SkipLexicalActions(null);
            }
            if (jjnewLexState[jjmatchedKind] != -1) {
              curLexState = jjnewLexState[jjmatchedKind];
            }
            continue EOFLoop;
          }
          MoreLexicalActions();
          if (jjnewLexState[jjmatchedKind] != -1) {
            curLexState = jjnewLexState[jjmatchedKind];
          }
          curPos = 0;
          jjmatchedKind = 0x7fffffff;
          try {
            curChar = input_stream.readChar();
            continue;
          } catch (java.io.IOException e1) {
          }
        }
        int error_line = input_stream.getEndLine();
        int error_column = input_stream.getEndColumn();
        String error_after = null;
        boolean EOFSeen = false;
        try {
          input_stream.readChar();
          input_stream.backup(1);
        } catch (java.io.IOException e1) {
          EOFSeen = true;
          error_after = curPos <= 1 ? "" : input_stream.GetImage();
          if (curChar == '\n' || curChar == '\r') {
            error_line++;
            error_column = 0;
          }
          else {
            error_column++;
          }
        }
        if (!EOFSeen) {
          input_stream.backup(1);
          error_after = curPos <= 1 ? "" : input_stream.GetImage();
        }
        throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
      }
    }
  }

  /**
   * Description of the Method
   *
   * @param stream  Description of Parameter
   */
  public void ReInit(ASCII_UCodeESC_CharStream stream) {
    jjmatchedPos = jjnewStateCnt = 0;
    curLexState = defaultLexState;
    input_stream = stream;
    ReInitRounds();
  }

  /**
   * Description of the Method
   *
   * @param stream    Description of Parameter
   * @param lexState  Description of Parameter
   */
  public void ReInit(ASCII_UCodeESC_CharStream stream, int lexState) {
    ReInit(stream);
    SwitchTo(lexState);
  }

  /**
   * Description of the Method
   *
   * @param lexState  Description of Parameter
   */
  public void SwitchTo(int lexState) {
    if (lexState >= 4 || lexState < 0) {
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
    }
    else {
      curLexState = lexState;
    }
  }

  /**
   * Description of the Method
   *
   * @param matchedToken  Description of Parameter
   */
  final void SkipLexicalActions(Token matchedToken) {
    switch (jjmatchedKind) {
      default:
        break;
    }
  }

  /**
   * Description of the Method
   */
  final void MoreLexicalActions() {
    jjimageLen += (lengthOfMatch = jjmatchedPos + 1);
    switch (jjmatchedKind) {
      case 7:
        if (image == null) {
          image = new StringBuffer(new String(input_stream.GetSuffix(jjimageLen)));
        }
        else {
          image.append(input_stream.GetSuffix(jjimageLen));
        }
        jjimageLen = 0;
        input_stream.backup(1);
        break;
      default:
        break;
    }
  }

  /**
   * Description of the Method
   *
   * @param pos   Description of Parameter
   * @param kind  Description of Parameter
   * @return      Description of the Returned Value
   */
  private final int jjStopAtPos(int pos, int kind) {
    jjmatchedKind = kind;
    jjmatchedPos = pos;
    return pos + 1;
  }

  /**
   * Description of the Method
   *
   * @return   Description of the Returned Value
   */
  private final int jjMoveStringLiteralDfa0_3() {
    switch (curChar) {
      case 42:
        return jjMoveStringLiteralDfa1_3(0x800L);
      default:
        return 1;
    }
  }

  /**
   * Description of the Method
   *
   * @param active0  Description of Parameter
   * @return         Description of the Returned Value
   */
  private final int jjMoveStringLiteralDfa1_3(long active0) {
    try {
      curChar = input_stream.readChar();
    } catch (java.io.IOException e) {
      return 1;
    }
    switch (curChar) {
      case 47:
        if ((active0 & 0x800L) != 0L) {
          return jjStopAtPos(1, 11);
        }
        break;
      default:
        return 2;
    }
    return 2;
  }

  /**
   * Description of the Method
   *
   * @return   Description of the Returned Value
   */
  private final int jjMoveStringLiteralDfa0_2() {
    switch (curChar) {
      case 42:
        return jjMoveStringLiteralDfa1_2(0x400L);
      default:
        return 1;
    }
  }

  /**
   * Description of the Method
   *
   * @param active0  Description of Parameter
   * @return         Description of the Returned Value
   */
  private final int jjMoveStringLiteralDfa1_2(long active0) {
    try {
      curChar = input_stream.readChar();
    } catch (java.io.IOException e) {
      return 1;
    }
    switch (curChar) {
      case 47:
        if ((active0 & 0x400L) != 0L) {
          return jjStopAtPos(1, 10);
        }
        break;
      default:
        return 2;
    }
    return 2;
  }

  /**
   * Description of the Method
   *
   * @return   Description of the Returned Value
   */
  private final int jjMoveStringLiteralDfa0_1() {
    return jjMoveNfa_1(0, 0);
  }

  /**
   * Description of the Method
   *
   * @param state  Description of Parameter
   */
  private final void jjCheckNAdd(int state) {
    if (jjrounds[state] != jjround) {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
    }
  }

  /**
   * Description of the Method
   *
   * @param start  Description of Parameter
   * @param end    Description of Parameter
   */
  private final void jjAddStates(int start, int end) {
    do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
    } while (start++ != end);
  }

  /**
   * Description of the Method
   *
   * @param state1  Description of Parameter
   * @param state2  Description of Parameter
   */
  private final void jjCheckNAddTwoStates(int state1, int state2) {
    jjCheckNAdd(state1);
    jjCheckNAdd(state2);
  }

  /**
   * Description of the Method
   *
   * @param start  Description of Parameter
   * @param end    Description of Parameter
   */
  private final void jjCheckNAddStates(int start, int end) {
    do {
      jjCheckNAdd(jjnextStates[start]);
    } while (start++ != end);
  }

  /**
   * Description of the Method
   *
   * @param start  Description of Parameter
   */
  private final void jjCheckNAddStates(int start) {
    jjCheckNAdd(jjnextStates[start]);
    jjCheckNAdd(jjnextStates[start + 1]);
  }

  /**
   * Description of the Method
   *
   * @param startState  Description of Parameter
   * @param curPos      Description of Parameter
   * @return            Description of the Returned Value
   */
  private final int jjMoveNfa_1(int startState, int curPos) {
    int[] nextStates;
    int startsAt = 0;
    jjnewStateCnt = 3;
    int i = 1;
    jjstateSet[0] = startState;
    int j;
    int kind = 0x7fffffff;
    for (; ; ) {
      if (++jjround == 0x7fffffff) {
        ReInitRounds();
      }
      if (curChar < 64) {
        long l = 1L << curChar;
        MatchLoop :
        do {
          switch (jjstateSet[--i]) {
            case 0:
              if ((0x2400L & l) != 0L) {
                if (kind > 9) {
                  kind = 9;
                }
              }
              if (curChar == 13) {
                jjstateSet[jjnewStateCnt++] = 1;
              }
              break;
            case 1:
              if (curChar == 10 && kind > 9) {
                kind = 9;
              }
              break;
            case 2:
              if (curChar == 13) {
                jjstateSet[jjnewStateCnt++] = 1;
              }
              break;
            default:
              break;
          }
        } while (i != startsAt);
      }
      else if (curChar < 128) {
        long l = 1L << (curChar & 077);
        MatchLoop :
        do {
          switch (jjstateSet[--i]) {
            default:
              break;
          }
        } while (i != startsAt);
      }
      else {
        int hiByte = (int) (curChar >> 8);
        int i1 = hiByte >> 6;
        long l1 = 1L << (hiByte & 077);
        int i2 = (curChar & 0xff) >> 6;
        long l2 = 1L << (curChar & 077);
        MatchLoop :
        do {
          switch (jjstateSet[--i]) {
            default:
              break;
          }
        } while (i != startsAt);
      }
      if (kind != 0x7fffffff) {
        jjmatchedKind = kind;
        jjmatchedPos = curPos;
        kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 3 - (jjnewStateCnt = startsAt))) {
        return curPos;
      }
      try {
        curChar = input_stream.readChar();
      } catch (java.io.IOException e) {
        return curPos;
      }
    }
  }

  /**
   * Description of the Method
   *
   * @param pos      Description of Parameter
   * @param active0  Description of Parameter
   * @return         Description of the Returned Value
   */
  private final int jjStopStringLiteralDfa_0(int pos, long active0) {
    switch (pos) {
      case 0:
        if ((active0 & 0x1420000L) != 0L) {
          return 16;
        }
        if ((active0 & 0x100L) != 0L) {
          return 2;
        }
        if ((active0 & 0x200000L) != 0L) {
          return 71;
        }
        if ((active0 & 0x2000000L) != 0L) {
          return 91;
        }
        return -1;
      case 1:
        if ((active0 & 0x100L) != 0L) {
          return 0;
        }
        return -1;
      default:
        return -1;
    }
  }

  /**
   * Description of the Method
   *
   * @param pos      Description of Parameter
   * @param active0  Description of Parameter
   * @return         Description of the Returned Value
   */
  private final int jjStartNfa_0(int pos, long active0) {
    return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0), pos + 1);
  }

  /**
   * Description of the Method
   *
   * @param pos    Description of Parameter
   * @param kind   Description of Parameter
   * @param state  Description of Parameter
   * @return       Description of the Returned Value
   */
  private final int jjStartNfaWithStates_0(int pos, int kind, int state) {
    jjmatchedKind = kind;
    jjmatchedPos = pos;
    try {
      curChar = input_stream.readChar();
    } catch (java.io.IOException e) {
      return pos + 1;
    }
    return jjMoveNfa_0(state, pos + 1);
  }

  /**
   * Description of the Method
   *
   * @return   Description of the Returned Value
   */
  private final int jjMoveStringLiteralDfa0_0() {
    switch (curChar) {
      case 35:
        return jjStopAtPos(0, 6);
      case 44:
        return jjStopAtPos(0, 52);
      case 47:
        return jjMoveStringLiteralDfa1_0(0x100L);
      case 61:
        return jjStopAtPos(0, 51);
      case 91:
        return jjStopAtPos(0, 47);
      case 93:
        return jjStopAtPos(0, 48);
      case 97:
        return jjMoveStringLiteralDfa1_0(0x20000000000L);
      case 98:
        return jjMoveStringLiteralDfa1_0(0x2000000L);
      case 99:
        return jjMoveStringLiteralDfa1_0(0x80000L);
      case 100:
        return jjMoveStringLiteralDfa1_0(0x20000000L);
      case 101:
        return jjMoveStringLiteralDfa1_0(0x100000L);
      case 102:
        return jjMoveStringLiteralDfa1_0(0x1420000L);
      case 103:
        return jjMoveStringLiteralDfa1_0(0x1fc0000000L);
      case 105:
        return jjMoveStringLiteralDfa1_0(0x200000L);
      case 111:
        return jjMoveStringLiteralDfa1_0(0x1e000000000L);
      case 114:
        return jjMoveStringLiteralDfa1_0(0x800000L);
      case 115:
        return jjMoveStringLiteralDfa1_0(0x1c040000L);
      case 119:
        return jjMoveStringLiteralDfa1_0(0xc0000000000000L);
      case 123:
        return jjStopAtPos(0, 49);
      case 125:
        return jjStopAtPos(0, 50);
      default:
        return jjMoveNfa_0(3, 0);
    }
  }

  /**
   * Description of the Method
   *
   * @param active0  Description of Parameter
   * @return         Description of the Returned Value
   */
  private final int jjMoveStringLiteralDfa1_0(long active0) {
    try {
      curChar = input_stream.readChar();
    } catch (java.io.IOException e) {
      jjStopStringLiteralDfa_0(0, active0);
      return 1;
    }
    switch (curChar) {
      case 42:
        if ((active0 & 0x100L) != 0L) {
          return jjStartNfaWithStates_0(1, 8, 0);
        }
        break;
      case 97:
        return jjMoveStringLiteralDfa2_0(active0, 0x1fc0000000L);
      case 101:
        return jjMoveStringLiteralDfa2_0(active0, 0x20800000L);
      case 104:
        return jjMoveStringLiteralDfa2_0(active0, 0x8000000L);
      case 105:
        return jjMoveStringLiteralDfa2_0(active0, 0xc0000001000000L);
      case 108:
        return jjMoveStringLiteralDfa2_0(active0, 0x80000L);
      case 109:
        return jjMoveStringLiteralDfa2_0(active0, 0x200000L);
      case 110:
        return jjMoveStringLiteralDfa2_0(active0, 0x100000L);
      case 111:
        return jjMoveStringLiteralDfa2_0(active0, 0x2020000L);
      case 114:
        return jjMoveStringLiteralDfa2_0(active0, 0x30000000000L);
      case 116:
        return jjMoveStringLiteralDfa2_0(active0, 0x14040000L);
      case 117:
        return jjMoveStringLiteralDfa2_0(active0, 0x400000L);
      case 118:
        return jjMoveStringLiteralDfa2_0(active0, 0xe000000000L);
      default:
        break;
    }
    return jjStartNfa_0(0, active0);
  }

  /**
   * Description of the Method
   *
   * @param old0     Description of Parameter
   * @param active0  Description of Parameter
   * @return         Description of the Returned Value
   */
  private final int jjMoveStringLiteralDfa2_0(long old0, long active0) {
    if (((active0 &= old0)) == 0L) {
      return jjStartNfa_0(0, old0);
    }
    try {
      curChar = input_stream.readChar();
    } catch (java.io.IOException e) {
      jjStopStringLiteralDfa_0(1, active0);
      return 2;
    }
    switch (curChar) {
      case 97:
        return jjMoveStringLiteralDfa3_0(active0, 0x18280000L);
      case 99:
        return jjMoveStringLiteralDfa3_0(active0, 0x800000L);
      case 100:
        return jjMoveStringLiteralDfa3_0(active0, 0xc0000000000000L);
      case 101:
        return jjMoveStringLiteralDfa3_0(active0, 0xe000000000L);
      case 103:
        return jjMoveStringLiteralDfa3_0(active0, 0x100000L);
      case 105:
        return jjMoveStringLiteralDfa3_0(active0, 0x10000000000L);
      case 108:
        return jjMoveStringLiteralDfa3_0(active0, 0x1000000L);
      case 110:
        return jjMoveStringLiteralDfa3_0(active0, 0x420000L);
      case 112:
        return jjMoveStringLiteralDfa3_0(active0, 0x1fc0000000L);
      case 114:
        return jjMoveStringLiteralDfa3_0(active0, 0x20006000000L);
      case 116:
        return jjMoveStringLiteralDfa3_0(active0, 0x20000000L);
      case 121:
        return jjMoveStringLiteralDfa3_0(active0, 0x40000L);
      default:
        break;
    }
    return jjStartNfa_0(1, active0);
  }

  /**
   * Description of the Method
   *
   * @param old0     Description of Parameter
   * @param active0  Description of Parameter
   * @return         Description of the Returned Value
   */
  private final int jjMoveStringLiteralDfa3_0(long old0, long active0) {
    if (((active0 &= old0)) == 0L) {
      return jjStartNfa_0(1, old0);
    }
    try {
      curChar = input_stream.readChar();
    } catch (java.io.IOException e) {
      jjStopStringLiteralDfa_0(2, active0);
      return 3;
    }
    switch (curChar) {
      case 95:
        return jjMoveStringLiteralDfa4_0(active0, 0x1fc0000000L);
      case 97:
        return jjMoveStringLiteralDfa4_0(active0, 0x20000000L);
      case 99:
        return jjMoveStringLiteralDfa4_0(active0, 0x400000L);
      case 100:
        return jjMoveStringLiteralDfa4_0(active0, 0xa000000L);
      case 101:
        if ((active0 & 0x1000000L) != 0L) {
          return jjStopAtPos(3, 24);
        }
        return jjMoveStringLiteralDfa4_0(active0, 0x10004000000L);
      case 103:
        return jjMoveStringLiteralDfa4_0(active0, 0xc0000000200000L);
      case 105:
        return jjMoveStringLiteralDfa4_0(active0, 0x100000L);
      case 108:
        return jjMoveStringLiteralDfa4_0(active0, 0x40000L);
      case 111:
        return jjMoveStringLiteralDfa4_0(active0, 0x20000800000L);
      case 114:
        return jjMoveStringLiteralDfa4_0(active0, 0xe000000000L);
      case 115:
        return jjMoveStringLiteralDfa4_0(active0, 0x80000L);
      case 116:
        if ((active0 & 0x20000L) != 0L) {
          return jjStopAtPos(3, 17);
        }
        return jjMoveStringLiteralDfa4_0(active0, 0x10000000L);
      default:
        break;
    }
    return jjStartNfa_0(2, active0);
  }

  /**
   * Description of the Method
   *
   * @param old0     Description of Parameter
   * @param active0  Description of Parameter
   * @return         Description of the Returned Value
   */
  private final int jjMoveStringLiteralDfa4_0(long old0, long active0) {
    if (((active0 &= old0)) == 0L) {
      return jjStartNfa_0(2, old0);
    }
    try {
      curChar = input_stream.readChar();
    } catch (java.io.IOException e) {
      jjStopStringLiteralDfa_0(3, active0);
      return 4;
    }
    switch (curChar) {
      case 98:
        return jjMoveStringLiteralDfa5_0(active0, 0x100000000L);
      case 101:
        if ((active0 & 0x40000L) != 0L) {
          return jjStopAtPos(4, 18);
        }
        else if ((active0 & 0x200000L) != 0L) {
          return jjStopAtPos(4, 21);
        }
        else if ((active0 & 0x10000000L) != 0L) {
          return jjStopAtPos(4, 28);
        }
        return jjMoveStringLiteralDfa5_0(active0, 0xc0001802000000L);
      case 102:
        return jjMoveStringLiteralDfa5_0(active0, 0x80000000L);
      case 105:
        return jjMoveStringLiteralDfa5_0(active0, 0x20000000L);
      case 108:
        return jjMoveStringLiteralDfa5_0(active0, 0xe000800000L);
      case 110:
        return jjMoveStringLiteralDfa5_0(active0, 0x10000100000L);
      case 111:
        return jjMoveStringLiteralDfa5_0(active0, 0x8000000L);
      case 115:
        if ((active0 & 0x80000L) != 0L) {
          return jjStopAtPos(4, 19);
        }
        return jjMoveStringLiteralDfa5_0(active0, 0x640000000L);
      case 116:
        return jjMoveStringLiteralDfa5_0(active0, 0x4400000L);
      case 119:
        return jjMoveStringLiteralDfa5_0(active0, 0x20000000000L);
      default:
        break;
    }
    return jjStartNfa_0(3, active0);
  }

  /**
   * Description of the Method
   *
   * @param old0     Description of Parameter
   * @param active0  Description of Parameter
   * @return         Description of the Returned Value
   */
  private final int jjMoveStringLiteralDfa5_0(long old0, long active0) {
    if (((active0 &= old0)) == 0L) {
      return jjStartNfa_0(3, old0);
    }
    try {
      curChar = input_stream.readChar();
    } catch (java.io.IOException e) {
      jjStopStringLiteralDfa_0(4, active0);
      return 5;
    }
    switch (curChar) {
      case 95:
        return jjMoveStringLiteralDfa6_0(active0, 0x20000000000L);
      case 97:
        return jjMoveStringLiteralDfa6_0(active0, 0xe000000000L);
      case 99:
        return jjMoveStringLiteralDfa6_0(active0, 0x4000000L);
      case 101:
        if ((active0 & 0x100000L) != 0L) {
          return jjStopAtPos(5, 20);
        }
        break;
      case 105:
        return jjMoveStringLiteralDfa6_0(active0, 0xc0400000L);
      case 108:
        if ((active0 & 0x20000000L) != 0L) {
          return jjStopAtPos(5, 29);
        }
        break;
      case 110:
        return jjMoveStringLiteralDfa6_0(active0, 0x1800000000L);
      case 111:
        return jjMoveStringLiteralDfa6_0(active0, 0x100800000L);
      case 114:
        if ((active0 & 0x2000000L) != 0L) {
          return jjStopAtPos(5, 25);
        }
        break;
      case 116:
        if ((active0 & 0x80000000000000L) != 0L) {
          jjmatchedKind = 55;
          jjmatchedPos = 5;
        }
        return jjMoveStringLiteralDfa6_0(active0, 0x40010600000000L);
      case 119:
        if ((active0 & 0x8000000L) != 0L) {
          return jjStopAtPos(5, 27);
        }
        break;
      default:
        break;
    }
    return jjStartNfa_0(4, active0);
  }

  /**
   * Description of the Method
   *
   * @param old0     Description of Parameter
   * @param active0  Description of Parameter
   * @return         Description of the Returned Value
   */
  private final int jjMoveStringLiteralDfa6_0(long old0, long active0) {
    if (((active0 &= old0)) == 0L) {
      return jjStartNfa_0(4, old0);
    }
    try {
      curChar = input_stream.readChar();
    } catch (java.io.IOException e) {
      jjStopStringLiteralDfa_0(5, active0);
      return 6;
    }
    switch (curChar) {
      case 95:
        return jjMoveStringLiteralDfa7_0(active0, 0x40000000000000L);
      case 97:
        return jjMoveStringLiteralDfa7_0(active0, 0x10600000000L);
      case 100:
        return jjMoveStringLiteralDfa7_0(active0, 0x21840000000L);
      case 104:
        if ((active0 & 0x4000000L) != 0L) {
          return jjStopAtPos(6, 26);
        }
        break;
      case 108:
        return jjMoveStringLiteralDfa7_0(active0, 0x80000000L);
      case 111:
        return jjMoveStringLiteralDfa7_0(active0, 0x400000L);
      case 114:
        return jjMoveStringLiteralDfa7_0(active0, 0x100800000L);
      case 121:
        return jjMoveStringLiteralDfa7_0(active0, 0xe000000000L);
      default:
        break;
    }
    return jjStartNfa_0(5, active0);
  }

  /**
   * Description of the Method
   *
   * @param old0     Description of Parameter
   * @param active0  Description of Parameter
   * @return         Description of the Returned Value
   */
  private final int jjMoveStringLiteralDfa7_0(long old0, long active0) {
    if (((active0 &= old0)) == 0L) {
      return jjStartNfa_0(5, old0);
    }
    try {
      curChar = input_stream.readChar();
    } catch (java.io.IOException e) {
      jjStopStringLiteralDfa_0(6, active0);
      return 7;
    }
    switch (curChar) {
      case 95:
        return jjMoveStringLiteralDfa8_0(active0, 0xf800000000L);
      case 97:
        return jjMoveStringLiteralDfa8_0(active0, 0x800000L);
      case 99:
        return jjMoveStringLiteralDfa8_0(active0, 0x40000000000000L);
      case 100:
        return jjMoveStringLiteralDfa8_0(active0, 0x100000000L);
      case 101:
        if ((active0 & 0x40000000L) != 0L) {
          return jjStopAtPos(7, 30);
        }
        else if ((active0 & 0x80000000L) != 0L) {
          return jjStopAtPos(7, 31);
        }
        break;
      case 105:
        return jjMoveStringLiteralDfa8_0(active0, 0x20000000000L);
      case 110:
        if ((active0 & 0x400000L) != 0L) {
          return jjStopAtPos(7, 22);
        }
        break;
      case 114:
        return jjMoveStringLiteralDfa8_0(active0, 0x600000000L);
      case 116:
        return jjMoveStringLiteralDfa8_0(active0, 0x10000000000L);
      default:
        break;
    }
    return jjStartNfa_0(6, active0);
  }

  /**
   * Description of the Method
   *
   * @param old0     Description of Parameter
   * @param active0  Description of Parameter
   * @return         Description of the Returned Value
   */
  private final int jjMoveStringLiteralDfa8_0(long old0, long active0) {
    if (((active0 &= old0)) == 0L) {
      return jjStartNfa_0(6, old0);
    }
    try {
      curChar = input_stream.readChar();
    } catch (java.io.IOException e) {
      jjStopStringLiteralDfa_0(7, active0);
      return 8;
    }
    switch (curChar) {
      case 98:
        return jjMoveStringLiteralDfa9_0(active0, 0x5000800000L);
      case 101:
        return jjMoveStringLiteralDfa9_0(active0, 0x100000000L);
      case 102:
        return jjMoveStringLiteralDfa9_0(active0, 0x2800000000L);
      case 105:
        return jjMoveStringLiteralDfa9_0(active0, 0x10000000000L);
      case 108:
        return jjMoveStringLiteralDfa9_0(active0, 0x40000000000000L);
      case 114:
        return jjMoveStringLiteralDfa9_0(active0, 0x20000000000L);
      case 115:
        return jjMoveStringLiteralDfa9_0(active0, 0x8000000000L);
      case 116:
        return jjMoveStringLiteralDfa9_0(active0, 0x600000000L);
      default:
        break;
    }
    return jjStartNfa_0(7, active0);
  }

  /**
   * Description of the Method
   *
   * @param old0     Description of Parameter
   * @param active0  Description of Parameter
   * @return         Description of the Returned Value
   */
  private final int jjMoveStringLiteralDfa9_0(long old0, long active0) {
    if (((active0 &= old0)) == 0L) {
      return jjStartNfa_0(7, old0);
    }
    try {
      curChar = input_stream.readChar();
    } catch (java.io.IOException e) {
      jjStopStringLiteralDfa_0(8, active0);
      return 9;
    }
    switch (curChar) {
      case 95:
        return jjMoveStringLiteralDfa10_0(active0, 0x600000000L);
      case 97:
        return jjMoveStringLiteralDfa10_0(active0, 0x40000000000000L);
      case 101:
        return jjMoveStringLiteralDfa10_0(active0, 0x20000000000L);
      case 105:
        return jjMoveStringLiteralDfa10_0(active0, 0x2800000000L);
      case 108:
        return jjMoveStringLiteralDfa10_0(active0, 0x800000L);
      case 111:
        return jjMoveStringLiteralDfa10_0(active0, 0x15000000000L);
      case 114:
        if ((active0 & 0x100000000L) != 0L) {
          return jjStopAtPos(9, 32);
        }
        break;
      case 116:
        return jjMoveStringLiteralDfa10_0(active0, 0x8000000000L);
      default:
        break;
    }
    return jjStartNfa_0(8, active0);
  }

  /**
   * Description of the Method
   *
   * @param old0     Description of Parameter
   * @param active0  Description of Parameter
   * @return         Description of the Returned Value
   */
  private final int jjMoveStringLiteralDfa10_0(long old0, long active0) {
    if (((active0 &= old0)) == 0L) {
      return jjStartNfa_0(8, old0);
    }
    try {
      curChar = input_stream.readChar();
    } catch (java.io.IOException e) {
      jjStopStringLiteralDfa_0(9, active0);
      return 10;
    }
    switch (curChar) {
      case 98:
        return jjMoveStringLiteralDfa11_0(active0, 0x400000000L);
      case 99:
        return jjMoveStringLiteralDfa11_0(active0, 0x20000000000L);
      case 101:
        if ((active0 & 0x800000L) != 0L) {
          return jjStopAtPos(10, 23);
        }
        break;
      case 102:
        return jjMoveStringLiteralDfa11_0(active0, 0x200000000L);
      case 108:
        return jjMoveStringLiteralDfa11_0(active0, 0x2800000000L);
      case 110:
        if ((active0 & 0x10000000000L) != 0L) {
          return jjStopAtPos(10, 40);
        }
        break;
      case 114:
        return jjMoveStringLiteralDfa11_0(active0, 0xd000000000L);
      case 115:
        return jjMoveStringLiteralDfa11_0(active0, 0x40000000000000L);
      default:
        break;
    }
    return jjStartNfa_0(9, active0);
  }

  /**
   * Description of the Method
   *
   * @param old0     Description of Parameter
   * @param active0  Description of Parameter
   * @return         Description of the Returned Value
   */
  private final int jjMoveStringLiteralDfa11_0(long old0, long active0) {
    if (((active0 &= old0)) == 0L) {
      return jjStartNfa_0(9, old0);
    }
    try {
      curChar = input_stream.readChar();
    } catch (java.io.IOException e) {
      jjStopStringLiteralDfa_0(10, active0);
      return 11;
    }
    switch (curChar) {
      case 100:
        return jjMoveStringLiteralDfa12_0(active0, 0x5000000000L);
      case 101:
        if ((active0 & 0x800000000L) != 0L) {
          return jjStopAtPos(11, 35);
        }
        else if ((active0 & 0x2000000000L) != 0L) {
          return jjStopAtPos(11, 37);
        }
        return jjMoveStringLiteralDfa12_0(active0, 0x8000000000L);
      case 105:
        return jjMoveStringLiteralDfa12_0(active0, 0x200000000L);
      case 111:
        return jjMoveStringLiteralDfa12_0(active0, 0x400000000L);
      case 115:
        if ((active0 & 0x40000000000000L) != 0L) {
          return jjStopAtPos(11, 54);
        }
        break;
      case 116:
        return jjMoveStringLiteralDfa12_0(active0, 0x20000000000L);
      default:
        break;
    }
    return jjStartNfa_0(10, active0);
  }

  /**
   * Description of the Method
   *
   * @param old0     Description of Parameter
   * @param active0  Description of Parameter
   * @return         Description of the Returned Value
   */
  private final int jjMoveStringLiteralDfa12_0(long old0, long active0) {
    if (((active0 &= old0)) == 0L) {
      return jjStartNfa_0(10, old0);
    }
    try {
      curChar = input_stream.readChar();
    } catch (java.io.IOException e) {
      jjStopStringLiteralDfa_0(11, active0);
      return 12;
    }
    switch (curChar) {
      case 101:
        return jjMoveStringLiteralDfa13_0(active0, 0x5000000000L);
      case 105:
        return jjMoveStringLiteralDfa13_0(active0, 0x20000000000L);
      case 108:
        return jjMoveStringLiteralDfa13_0(active0, 0x200000000L);
      case 114:
        return jjMoveStringLiteralDfa13_0(active0, 0x400000000L);
      case 116:
        return jjMoveStringLiteralDfa13_0(active0, 0x8000000000L);
      default:
        break;
    }
    return jjStartNfa_0(11, active0);
  }

  /**
   * Description of the Method
   *
   * @param old0     Description of Parameter
   * @param active0  Description of Parameter
   * @return         Description of the Returned Value
   */
  private final int jjMoveStringLiteralDfa13_0(long old0, long active0) {
    if (((active0 &= old0)) == 0L) {
      return jjStartNfa_0(11, old0);
    }
    try {
      curChar = input_stream.readChar();
    } catch (java.io.IOException e) {
      jjStopStringLiteralDfa_0(12, active0);
      return 13;
    }
    switch (curChar) {
      case 99:
        return jjMoveStringLiteralDfa14_0(active0, 0x8000000000L);
      case 100:
        return jjMoveStringLiteralDfa14_0(active0, 0x400000000L);
      case 101:
        if ((active0 & 0x200000000L) != 0L) {
          return jjStopAtPos(13, 33);
        }
        break;
      case 111:
        return jjMoveStringLiteralDfa14_0(active0, 0x20000000000L);
      case 114:
        if ((active0 & 0x1000000000L) != 0L) {
          return jjStopAtPos(13, 36);
        }
        else if ((active0 & 0x4000000000L) != 0L) {
          return jjStopAtPos(13, 38);
        }
        break;
      default:
        break;
    }
    return jjStartNfa_0(12, active0);
  }

  /**
   * Description of the Method
   *
   * @param old0     Description of Parameter
   * @param active0  Description of Parameter
   * @return         Description of the Returned Value
   */
  private final int jjMoveStringLiteralDfa14_0(long old0, long active0) {
    if (((active0 &= old0)) == 0L) {
      return jjStartNfa_0(12, old0);
    }
    try {
      curChar = input_stream.readChar();
    } catch (java.io.IOException e) {
      jjStopStringLiteralDfa_0(13, active0);
      return 14;
    }
    switch (curChar) {
      case 101:
        return jjMoveStringLiteralDfa15_0(active0, 0x400000000L);
      case 104:
        if ((active0 & 0x8000000000L) != 0L) {
          return jjStopAtPos(14, 39);
        }
        break;
      case 110:
        if ((active0 & 0x20000000000L) != 0L) {
          return jjStopAtPos(14, 41);
        }
        break;
      default:
        break;
    }
    return jjStartNfa_0(13, active0);
  }

  /**
   * Description of the Method
   *
   * @param old0     Description of Parameter
   * @param active0  Description of Parameter
   * @return         Description of the Returned Value
   */
  private final int jjMoveStringLiteralDfa15_0(long old0, long active0) {
    if (((active0 &= old0)) == 0L) {
      return jjStartNfa_0(13, old0);
    }
    try {
      curChar = input_stream.readChar();
    } catch (java.io.IOException e) {
      jjStopStringLiteralDfa_0(14, active0);
      return 15;
    }
    switch (curChar) {
      case 114:
        if ((active0 & 0x400000000L) != 0L) {
          return jjStopAtPos(15, 34);
        }
        break;
      default:
        break;
    }
    return jjStartNfa_0(14, active0);
  }

  /**
   * Description of the Method
   *
   * @param startState  Description of Parameter
   * @param curPos      Description of Parameter
   * @return            Description of the Returned Value
   */
  private final int jjMoveNfa_0(int startState, int curPos) {
    int[] nextStates;
    int startsAt = 0;
    jjnewStateCnt = 242;
    int i = 1;
    jjstateSet[0] = startState;
    int j;
    int kind = 0x7fffffff;
    for (; ; ) {
      if (++jjround == 0x7fffffff) {
        ReInitRounds();
      }
      if (curChar < 64) {
        long l = 1L << curChar;
        MatchLoop :
        do {
          switch (jjstateSet[--i]) {
            case 3:
              if ((0x3ff000000000000L & l) != 0L) {
                if (kind > 13) {
                  kind = 13;
                }
                jjCheckNAddTwoStates(4, 5);
              }
              else if (curChar == 34) {
                jjCheckNAddStates(0, 2);
              }
              else if (curChar == 47) {
                jjstateSet[jjnewStateCnt++] = 2;
              }
              break;
            case 0:
              if (curChar == 42) {
                jjstateSet[jjnewStateCnt++] = 1;
              }
              break;
            case 1:
              if ((0xffff7fffffffffffL & l) != 0L && kind > 7) {
                kind = 7;
              }
              break;
            case 2:
              if (curChar == 42) {
                jjstateSet[jjnewStateCnt++] = 0;
              }
              break;
            case 4:
              if ((0x3ff000000000000L & l) == 0L) {
                break;
              }
              if (kind > 13) {
                kind = 13;
              }
              jjCheckNAddTwoStates(4, 5);
              break;
            case 5:
              if (curChar == 46) {
                jjCheckNAdd(6);
              }
              break;
            case 6:
              if ((0x3ff000000000000L & l) == 0L) {
                break;
              }
              if (kind > 13) {
                kind = 13;
              }
              jjCheckNAdd(6);
              break;
            case 7:
              if (curChar == 34) {
                jjCheckNAddStates(0, 2);
              }
              break;
            case 8:
              if ((0xfffffffbffffdbffL & l) != 0L) {
                jjCheckNAddStates(0, 2);
              }
              break;
            case 10:
              if ((0x8400000000L & l) != 0L) {
                jjCheckNAddStates(0, 2);
              }
              break;
            case 11:
              if (curChar == 34 && kind > 14) {
                kind = 14;
              }
              break;
            case 12:
              if ((0xff000000000000L & l) != 0L) {
                jjCheckNAddStates(3, 6);
              }
              break;
            case 13:
              if ((0xff000000000000L & l) != 0L) {
                jjCheckNAddStates(0, 2);
              }
              break;
            case 14:
              if ((0xf000000000000L & l) != 0L) {
                jjstateSet[jjnewStateCnt++] = 15;
              }
              break;
            case 15:
              if ((0xff000000000000L & l) != 0L) {
                jjCheckNAdd(13);
              }
              break;
            default:
              break;
          }
        } while (i != startsAt);
      }
      else if (curChar < 128) {
        long l = 1L << (curChar & 077);
        MatchLoop :
        do {
          switch (jjstateSet[--i]) {
            case 91:
              if (curChar == 97) {
                jjstateSet[jjnewStateCnt++] = 93;
              }
              else if (curChar == 103) {
                if (kind > 16) {
                  kind = 16;
                }
              }
              if (curChar == 103) {
                jjstateSet[jjnewStateCnt++] = 90;
              }
              break;
            case 3:
              if (curChar == 69) {
                jjAddStates(7, 10);
              }
              else if (curChar == 66) {
                jjAddStates(11, 13);
              }
              else if (curChar == 72) {
                jjAddStates(14, 16);
              }
              else if (curChar == 83) {
                jjAddStates(17, 20);
              }
              else if (curChar == 73) {
                jjCheckNAddTwoStates(156, 157);
              }
              else if (curChar == 86) {
                jjAddStates(21, 22);
              }
              else if (curChar == 79) {
                jjAddStates(23, 24);
              }
              else if (curChar == 65) {
                jjAddStates(25, 26);
              }
              else if (curChar == 70) {
                jjAddStates(27, 29);
              }
              else if (curChar == 84) {
                jjAddStates(30, 32);
              }
              else if (curChar == 98) {
                jjCheckNAddStates(33, 35);
              }
              else if (curChar == 112) {
                jjstateSet[jjnewStateCnt++] = 81;
              }
              else if (curChar == 105) {
                jjstateSet[jjnewStateCnt++] = 71;
              }
              else if (curChar == 109) {
                jjstateSet[jjnewStateCnt++] = 64;
              }
              else if (curChar == 85) {
                jjCheckNAdd(53);
              }
              else if (curChar == 82) {
                jjstateSet[jjnewStateCnt++] = 51;
              }
              else if (curChar == 76) {
                jjstateSet[jjnewStateCnt++] = 47;
              }
              else if (curChar == 68) {
                jjstateSet[jjnewStateCnt++] = 43;
              }
              else if (curChar == 80) {
                jjstateSet[jjnewStateCnt++] = 39;
              }
              else if (curChar == 78) {
                jjstateSet[jjnewStateCnt++] = 31;
              }
              else if (curChar == 67) {
                jjstateSet[jjnewStateCnt++] = 25;
              }
              else if (curChar == 116) {
                jjstateSet[jjnewStateCnt++] = 20;
              }
              else if (curChar == 102) {
                jjCheckNAdd(16);
              }
              break;
            case 1:
              if (kind > 7) {
                kind = 7;
              }
              break;
            case 8:
              if ((0xffffffffefffffffL & l) != 0L) {
                jjCheckNAddStates(0, 2);
              }
              break;
            case 9:
              if (curChar == 92) {
                jjAddStates(36, 38);
              }
              break;
            case 10:
              if ((0x14404410000000L & l) != 0L) {
                jjCheckNAddStates(0, 2);
              }
              break;
            case 16:
              if (curChar == 103 && kind > 16) {
                kind = 16;
              }
              break;
            case 17:
              if (curChar == 102) {
                jjCheckNAdd(16);
              }
              break;
            case 18:
              if (curChar == 116 && kind > 16) {
                kind = 16;
              }
              break;
            case 19:
              if (curChar == 120) {
                jjstateSet[jjnewStateCnt++] = 18;
              }
              break;
            case 20:
              if (curChar == 101) {
                jjstateSet[jjnewStateCnt++] = 19;
              }
              break;
            case 21:
              if (curChar == 116) {
                jjstateSet[jjnewStateCnt++] = 20;
              }
              break;
            case 22:
              if (curChar == 75 && kind > 42) {
                kind = 42;
              }
              break;
            case 23:
              if (curChar == 67) {
                jjstateSet[jjnewStateCnt++] = 22;
              }
              break;
            case 24:
              if (curChar == 69) {
                jjstateSet[jjnewStateCnt++] = 23;
              }
              break;
            case 25:
              if (curChar == 72) {
                jjstateSet[jjnewStateCnt++] = 24;
              }
              break;
            case 26:
              if (curChar == 67) {
                jjstateSet[jjnewStateCnt++] = 25;
              }
              break;
            case 27:
              if (curChar == 76 && kind > 43) {
                kind = 43;
              }
              break;
            case 28:
              if (curChar == 65) {
                jjstateSet[jjnewStateCnt++] = 27;
              }
              break;
            case 29:
              if (curChar == 77) {
                jjstateSet[jjnewStateCnt++] = 28;
              }
              break;
            case 30:
              if (curChar == 82) {
                jjstateSet[jjnewStateCnt++] = 29;
              }
              break;
            case 31:
              if (curChar == 79) {
                jjstateSet[jjnewStateCnt++] = 30;
              }
              break;
            case 32:
              if (curChar == 78) {
                jjstateSet[jjnewStateCnt++] = 31;
              }
              break;
            case 33:
              if (curChar == 84 && kind > 43) {
                kind = 43;
              }
              break;
            case 34:
              if (curChar == 72) {
                jjstateSet[jjnewStateCnt++] = 33;
              }
              break;
            case 35:
              if (curChar == 71) {
                jjstateSet[jjnewStateCnt++] = 34;
              }
              break;
            case 36:
              if (curChar == 73) {
                jjstateSet[jjnewStateCnt++] = 35;
              }
              break;
            case 37:
              if (curChar == 76) {
                jjstateSet[jjnewStateCnt++] = 36;
              }
              break;
            case 38:
              if (curChar == 69) {
                jjstateSet[jjnewStateCnt++] = 37;
              }
              break;
            case 39:
              if (curChar == 82) {
                jjstateSet[jjnewStateCnt++] = 38;
              }
              break;
            case 40:
              if (curChar == 80) {
                jjstateSet[jjnewStateCnt++] = 39;
              }
              break;
            case 41:
              if (curChar == 78 && kind > 46) {
                kind = 46;
              }
              break;
            case 42:
              if (curChar == 87) {
                jjstateSet[jjnewStateCnt++] = 41;
              }
              break;
            case 43:
              if (curChar == 79) {
                jjstateSet[jjnewStateCnt++] = 42;
              }
              break;
            case 44:
              if (curChar == 68) {
                jjstateSet[jjnewStateCnt++] = 43;
              }
              break;
            case 45:
              if (curChar == 84 && kind > 46) {
                kind = 46;
              }
              break;
            case 46:
              if (curChar == 70) {
                jjCheckNAdd(45);
              }
              break;
            case 47:
              if (curChar == 69) {
                jjstateSet[jjnewStateCnt++] = 46;
              }
              break;
            case 48:
              if (curChar == 76) {
                jjstateSet[jjnewStateCnt++] = 47;
              }
              break;
            case 49:
              if (curChar == 72) {
                jjCheckNAdd(45);
              }
              break;
            case 50:
              if (curChar == 71) {
                jjstateSet[jjnewStateCnt++] = 49;
              }
              break;
            case 51:
              if (curChar == 73) {
                jjstateSet[jjnewStateCnt++] = 50;
              }
              break;
            case 52:
              if (curChar == 82) {
                jjstateSet[jjnewStateCnt++] = 51;
              }
              break;
            case 53:
              if (curChar == 80 && kind > 46) {
                kind = 46;
              }
              break;
            case 54:
              if (curChar == 85) {
                jjCheckNAdd(53);
              }
              break;
            case 55:
              if (curChar == 104 && kind > 53) {
                kind = 53;
              }
              break;
            case 56:
            case 73:
              if (curChar == 116) {
                jjCheckNAdd(55);
              }
              break;
            case 57:
              if (curChar == 97) {
                jjstateSet[jjnewStateCnt++] = 56;
              }
              break;
            case 58:
              if (curChar == 112) {
                jjstateSet[jjnewStateCnt++] = 57;
              }
              break;
            case 59:
              if (curChar == 95) {
                jjstateSet[jjnewStateCnt++] = 58;
              }
              break;
            case 60:
              if (curChar == 101) {
                jjstateSet[jjnewStateCnt++] = 59;
              }
              break;
            case 61:
              if (curChar == 108) {
                jjstateSet[jjnewStateCnt++] = 60;
              }
              break;
            case 62:
              if (curChar == 117) {
                jjstateSet[jjnewStateCnt++] = 61;
              }
              break;
            case 63:
              if (curChar == 100) {
                jjstateSet[jjnewStateCnt++] = 62;
              }
              break;
            case 64:
              if (curChar == 111) {
                jjstateSet[jjnewStateCnt++] = 63;
              }
              break;
            case 65:
              if (curChar == 109) {
                jjstateSet[jjnewStateCnt++] = 64;
              }
              break;
            case 66:
              if (curChar == 101 && kind > 53) {
                kind = 53;
              }
              break;
            case 67:
              if (curChar == 100) {
                jjstateSet[jjnewStateCnt++] = 66;
              }
              break;
            case 68:
              if (curChar == 117) {
                jjstateSet[jjnewStateCnt++] = 67;
              }
              break;
            case 69:
              if (curChar == 108) {
                jjstateSet[jjnewStateCnt++] = 68;
              }
              break;
            case 70:
              if (curChar == 99) {
                jjstateSet[jjnewStateCnt++] = 69;
              }
              break;
            case 71:
              if (curChar == 110) {
                jjstateSet[jjnewStateCnt++] = 70;
              }
              break;
            case 72:
              if (curChar == 105) {
                jjstateSet[jjnewStateCnt++] = 71;
              }
              break;
            case 74:
              if (curChar == 97) {
                jjstateSet[jjnewStateCnt++] = 73;
              }
              break;
            case 75:
              if (curChar == 112) {
                jjstateSet[jjnewStateCnt++] = 74;
              }
              break;
            case 76:
              if (curChar == 95) {
                jjstateSet[jjnewStateCnt++] = 75;
              }
              break;
            case 77:
              if (curChar == 112) {
                jjstateSet[jjnewStateCnt++] = 76;
              }
              break;
            case 78:
              if (curChar == 97) {
                jjstateSet[jjnewStateCnt++] = 77;
              }
              break;
            case 79:
              if (curChar == 109) {
                jjstateSet[jjnewStateCnt++] = 78;
              }
              break;
            case 80:
              if (curChar == 120) {
                jjstateSet[jjnewStateCnt++] = 79;
              }
              break;
            case 81:
              if (curChar == 105) {
                jjstateSet[jjnewStateCnt++] = 80;
              }
              break;
            case 82:
              if (curChar == 112) {
                jjstateSet[jjnewStateCnt++] = 81;
              }
              break;
            case 83:
              if (curChar == 98) {
                jjCheckNAddStates(33, 35);
              }
              break;
            case 84:
              if (curChar == 112 && kind > 16) {
                kind = 16;
              }
              break;
            case 85:
              if (curChar == 97) {
                jjstateSet[jjnewStateCnt++] = 84;
              }
              break;
            case 86:
              if (curChar == 109) {
                jjstateSet[jjnewStateCnt++] = 85;
              }
              break;
            case 87:
              if (curChar == 120) {
                jjstateSet[jjnewStateCnt++] = 86;
              }
              break;
            case 88:
              if (curChar == 105) {
                jjstateSet[jjnewStateCnt++] = 87;
              }
              break;
            case 89:
              if (curChar == 112) {
                jjstateSet[jjnewStateCnt++] = 88;
              }
              break;
            case 90:
              if (curChar == 95) {
                jjstateSet[jjnewStateCnt++] = 89;
              }
              break;
            case 92:
              if (curChar == 101 && kind > 16) {
                kind = 16;
              }
              break;
            case 93:
              if (curChar == 115) {
                jjstateSet[jjnewStateCnt++] = 92;
              }
              break;
            case 94:
              if (curChar == 97) {
                jjstateSet[jjnewStateCnt++] = 93;
              }
              break;
            case 95:
              if (curChar == 84) {
                jjAddStates(30, 32);
              }
              break;
            case 96:
              if (curChar == 69 && kind > 15) {
                kind = 15;
              }
              break;
            case 97:
              if (curChar == 85) {
                jjCheckNAdd(96);
              }
              break;
            case 98:
              if (curChar == 82) {
                jjstateSet[jjnewStateCnt++] = 97;
              }
              break;
            case 99:
              if (curChar == 66 && kind > 42) {
                kind = 42;
              }
              break;
            case 100:
              if (curChar == 65) {
                jjstateSet[jjnewStateCnt++] = 99;
              }
              break;
            case 101:
              if (curChar == 79) {
                jjCheckNAdd(53);
              }
              break;
            case 102:
              if (curChar == 70) {
                jjAddStates(27, 29);
              }
              break;
            case 103:
              if (curChar == 83) {
                jjCheckNAdd(96);
              }
              break;
            case 104:
              if (curChar == 76) {
                jjstateSet[jjnewStateCnt++] = 103;
              }
              break;
            case 105:
              if (curChar == 65) {
                jjstateSet[jjnewStateCnt++] = 104;
              }
              break;
            case 106:
              if (curChar == 88 && kind > 42) {
                kind = 42;
              }
              break;
            case 107:
            case 201:
              if (curChar == 79) {
                jjCheckNAdd(106);
              }
              break;
            case 108:
              if (curChar == 66) {
                jjstateSet[jjnewStateCnt++] = 107;
              }
              break;
            case 109:
              if (curChar == 95) {
                jjstateSet[jjnewStateCnt++] = 108;
              }
              break;
            case 110:
              if (curChar == 84) {
                jjstateSet[jjnewStateCnt++] = 109;
              }
              break;
            case 111:
              if (curChar == 65) {
                jjstateSet[jjnewStateCnt++] = 110;
              }
              break;
            case 112:
              if (curChar == 76) {
                jjstateSet[jjnewStateCnt++] = 111;
              }
              break;
            case 113:
              if (curChar == 83 && kind > 42) {
                kind = 42;
              }
              break;
            case 114:
              if (curChar == 85) {
                jjstateSet[jjnewStateCnt++] = 113;
              }
              break;
            case 115:
              if (curChar == 67) {
                jjstateSet[jjnewStateCnt++] = 114;
              }
              break;
            case 116:
              if (curChar == 79) {
                jjstateSet[jjnewStateCnt++] = 115;
              }
              break;
            case 117:
              if (curChar == 65) {
                jjAddStates(25, 26);
              }
              break;
            case 118:
              if (curChar == 87 && kind > 42) {
                kind = 42;
              }
              break;
            case 119:
            case 159:
              if (curChar == 79) {
                jjCheckNAdd(118);
              }
              break;
            case 120:
              if (curChar == 82) {
                jjstateSet[jjnewStateCnt++] = 119;
              }
              break;
            case 121:
              if (curChar == 82) {
                jjstateSet[jjnewStateCnt++] = 120;
              }
              break;
            case 122:
              if (curChar == 69 && kind > 43) {
                kind = 43;
              }
              break;
            case 123:
            case 148:
              if (curChar == 86) {
                jjCheckNAdd(122);
              }
              break;
            case 124:
              if (curChar == 73) {
                jjstateSet[jjnewStateCnt++] = 123;
              }
              break;
            case 125:
              if (curChar == 84) {
                jjstateSet[jjnewStateCnt++] = 124;
              }
              break;
            case 126:
              if (curChar == 67) {
                jjstateSet[jjnewStateCnt++] = 125;
              }
              break;
            case 127:
              if (curChar == 79) {
                jjAddStates(23, 24);
              }
              break;
            case 128:
              if (curChar == 78 && kind > 42) {
                kind = 42;
              }
              break;
            case 129:
            case 213:
            case 220:
              if (curChar == 79) {
                jjCheckNAdd(128);
              }
              break;
            case 130:
              if (curChar == 73) {
                jjstateSet[jjnewStateCnt++] = 129;
              }
              break;
            case 131:
              if (curChar == 84) {
                jjstateSet[jjnewStateCnt++] = 130;
              }
              break;
            case 132:
              if (curChar == 80) {
                jjstateSet[jjnewStateCnt++] = 131;
              }
              break;
            case 133:
              if (curChar == 84 && kind > 44) {
                kind = 44;
              }
              break;
            case 134:
            case 234:
              if (curChar == 85) {
                jjCheckNAdd(133);
              }
              break;
            case 135:
              if (curChar == 86) {
                jjAddStates(21, 22);
              }
              break;
            case 136:
              if (curChar == 69 && kind > 42) {
                kind = 42;
              }
              break;
            case 137:
            case 189:
              if (curChar == 78) {
                jjCheckNAdd(136);
              }
              break;
            case 138:
              if (curChar == 73) {
                jjstateSet[jjnewStateCnt++] = 137;
              }
              break;
            case 139:
              if (curChar == 76) {
                jjstateSet[jjnewStateCnt++] = 138;
              }
              break;
            case 140:
              if (curChar == 76 && kind > 45) {
                kind = 45;
              }
              break;
            case 141:
            case 192:
              if (curChar == 65) {
                jjCheckNAdd(140);
              }
              break;
            case 142:
              if (curChar == 67) {
                jjstateSet[jjnewStateCnt++] = 141;
              }
              break;
            case 143:
              if (curChar == 73) {
                jjstateSet[jjnewStateCnt++] = 142;
              }
              break;
            case 144:
              if (curChar == 84) {
                jjstateSet[jjnewStateCnt++] = 143;
              }
              break;
            case 145:
              if (curChar == 82) {
                jjstateSet[jjnewStateCnt++] = 144;
              }
              break;
            case 146:
              if (curChar == 69) {
                jjstateSet[jjnewStateCnt++] = 145;
              }
              break;
            case 147:
              if (curChar == 73) {
                jjCheckNAddTwoStates(156, 157);
              }
              break;
            case 149:
              if (curChar == 73) {
                jjstateSet[jjnewStateCnt++] = 148;
              }
              break;
            case 150:
              if (curChar == 84) {
                jjstateSet[jjnewStateCnt++] = 149;
              }
              break;
            case 151:
              if (curChar == 73) {
                jjstateSet[jjnewStateCnt++] = 150;
              }
              break;
            case 152:
              if (curChar == 83) {
                jjstateSet[jjnewStateCnt++] = 151;
              }
              break;
            case 153:
              if (curChar == 78) {
                jjstateSet[jjnewStateCnt++] = 152;
              }
              break;
            case 154:
              if (curChar == 69) {
                jjstateSet[jjnewStateCnt++] = 153;
              }
              break;
            case 155:
              if (curChar == 83) {
                jjstateSet[jjnewStateCnt++] = 154;
              }
              break;
            case 156:
              if (curChar == 78) {
                jjstateSet[jjnewStateCnt++] = 155;
              }
              break;
            case 157:
              if (curChar == 78 && kind > 44) {
                kind = 44;
              }
              break;
            case 158:
              if (curChar == 83) {
                jjAddStates(17, 20);
              }
              break;
            case 160:
              if (curChar == 68) {
                jjstateSet[jjnewStateCnt++] = 159;
              }
              break;
            case 161:
              if (curChar == 65) {
                jjstateSet[jjnewStateCnt++] = 160;
              }
              break;
            case 162:
              if (curChar == 72) {
                jjstateSet[jjnewStateCnt++] = 161;
              }
              break;
            case 163:
              if (curChar == 80 && kind > 42) {
                kind = 42;
              }
              break;
            case 164:
            case 202:
              if (curChar == 65) {
                jjCheckNAdd(163);
              }
              break;
            case 165:
              if (curChar == 71) {
                jjstateSet[jjnewStateCnt++] = 164;
              }
              break;
            case 166:
              if (curChar == 95) {
                jjstateSet[jjnewStateCnt++] = 165;
              }
              break;
            case 167:
              if (curChar == 87) {
                jjstateSet[jjnewStateCnt++] = 166;
              }
              break;
            case 168:
              if (curChar == 79) {
                jjstateSet[jjnewStateCnt++] = 167;
              }
              break;
            case 169:
              if (curChar == 68) {
                jjstateSet[jjnewStateCnt++] = 168;
              }
              break;
            case 170:
              if (curChar == 65) {
                jjstateSet[jjnewStateCnt++] = 169;
              }
              break;
            case 171:
              if (curChar == 72) {
                jjstateSet[jjnewStateCnt++] = 170;
              }
              break;
            case 172:
              if (curChar == 82 && kind > 42) {
                kind = 42;
              }
              break;
            case 173:
              if (curChar == 69) {
                jjstateSet[jjnewStateCnt++] = 172;
              }
              break;
            case 174:
              if (curChar == 68) {
                jjstateSet[jjnewStateCnt++] = 173;
              }
              break;
            case 175:
              if (curChar == 73) {
                jjstateSet[jjnewStateCnt++] = 174;
              }
              break;
            case 176:
              if (curChar == 76) {
                jjstateSet[jjnewStateCnt++] = 175;
              }
              break;
            case 177:
              if (curChar == 68 && kind > 43) {
                kind = 43;
              }
              break;
            case 178:
              if (curChar == 69) {
                jjstateSet[jjnewStateCnt++] = 177;
              }
              break;
            case 179:
              if (curChar == 84) {
                jjstateSet[jjnewStateCnt++] = 178;
              }
              break;
            case 180:
              if (curChar == 67) {
                jjstateSet[jjnewStateCnt++] = 179;
              }
              break;
            case 181:
              if (curChar == 69) {
                jjstateSet[jjnewStateCnt++] = 180;
              }
              break;
            case 182:
              if (curChar == 76) {
                jjstateSet[jjnewStateCnt++] = 181;
              }
              break;
            case 183:
              if (curChar == 69) {
                jjstateSet[jjnewStateCnt++] = 182;
              }
              break;
            case 184:
              if (curChar == 72) {
                jjAddStates(14, 16);
              }
              break;
            case 185:
              if (curChar == 76) {
                jjCheckNAdd(136);
              }
              break;
            case 186:
              if (curChar == 68) {
                jjstateSet[jjnewStateCnt++] = 185;
              }
              break;
            case 187:
              if (curChar == 78) {
                jjstateSet[jjnewStateCnt++] = 186;
              }
              break;
            case 188:
              if (curChar == 65) {
                jjstateSet[jjnewStateCnt++] = 187;
              }
              break;
            case 190:
              if (curChar == 73) {
                jjstateSet[jjnewStateCnt++] = 189;
              }
              break;
            case 191:
              if (curChar == 76) {
                jjstateSet[jjnewStateCnt++] = 190;
              }
              break;
            case 193:
              if (curChar == 84) {
                jjstateSet[jjnewStateCnt++] = 192;
              }
              break;
            case 194:
              if (curChar == 78) {
                jjstateSet[jjnewStateCnt++] = 193;
              }
              break;
            case 195:
              if (curChar == 79) {
                jjstateSet[jjnewStateCnt++] = 194;
              }
              break;
            case 196:
              if (curChar == 90) {
                jjstateSet[jjnewStateCnt++] = 195;
              }
              break;
            case 197:
              if (curChar == 73) {
                jjstateSet[jjnewStateCnt++] = 196;
              }
              break;
            case 198:
              if (curChar == 82) {
                jjstateSet[jjnewStateCnt++] = 197;
              }
              break;
            case 199:
              if (curChar == 79) {
                jjstateSet[jjnewStateCnt++] = 198;
              }
              break;
            case 200:
              if (curChar == 66) {
                jjAddStates(11, 13);
              }
              break;
            case 203:
              if (curChar == 71) {
                jjstateSet[jjnewStateCnt++] = 202;
              }
              break;
            case 204:
              if (curChar == 95) {
                jjstateSet[jjnewStateCnt++] = 203;
              }
              break;
            case 205:
              if (curChar == 88) {
                jjstateSet[jjnewStateCnt++] = 204;
              }
              break;
            case 206:
              if (curChar == 79) {
                jjstateSet[jjnewStateCnt++] = 205;
              }
              break;
            case 207:
              if (curChar == 77 && kind > 46) {
                kind = 46;
              }
              break;
            case 208:
              if (curChar == 79) {
                jjstateSet[jjnewStateCnt++] = 207;
              }
              break;
            case 209:
              if (curChar == 84) {
                jjstateSet[jjnewStateCnt++] = 208;
              }
              break;
            case 210:
              if (curChar == 84) {
                jjstateSet[jjnewStateCnt++] = 209;
              }
              break;
            case 211:
              if (curChar == 79) {
                jjstateSet[jjnewStateCnt++] = 210;
              }
              break;
            case 212:
              if (curChar == 69) {
                jjAddStates(7, 10);
              }
              break;
            case 214:
              if (curChar == 73) {
                jjstateSet[jjnewStateCnt++] = 213;
              }
              break;
            case 215:
              if (curChar == 83) {
                jjstateSet[jjnewStateCnt++] = 214;
              }
              break;
            case 216:
              if (curChar == 78) {
                jjstateSet[jjnewStateCnt++] = 215;
              }
              break;
            case 217:
              if (curChar == 69) {
                jjstateSet[jjnewStateCnt++] = 216;
              }
              break;
            case 218:
              if (curChar == 84) {
                jjstateSet[jjnewStateCnt++] = 217;
              }
              break;
            case 219:
              if (curChar == 88) {
                jjstateSet[jjnewStateCnt++] = 218;
              }
              break;
            case 221:
              if (curChar == 73) {
                jjstateSet[jjnewStateCnt++] = 220;
              }
              break;
            case 222:
              if (curChar == 83) {
                jjstateSet[jjnewStateCnt++] = 221;
              }
              break;
            case 223:
              if (curChar == 78) {
                jjstateSet[jjnewStateCnt++] = 222;
              }
              break;
            case 224:
              if (curChar == 69) {
                jjstateSet[jjnewStateCnt++] = 223;
              }
              break;
            case 225:
              if (curChar == 84) {
                jjstateSet[jjnewStateCnt++] = 224;
              }
              break;
            case 226:
              if (curChar == 88) {
                jjstateSet[jjnewStateCnt++] = 225;
              }
              break;
            case 227:
              if (curChar == 73) {
                jjCheckNAdd(157);
              }
              break;
            case 228:
              if (curChar == 95) {
                jjstateSet[jjnewStateCnt++] = 227;
              }
              break;
            case 229:
              if (curChar == 68) {
                jjstateSet[jjnewStateCnt++] = 228;
              }
              break;
            case 230:
              if (curChar == 69) {
                jjstateSet[jjnewStateCnt++] = 229;
              }
              break;
            case 231:
              if (curChar == 72) {
                jjstateSet[jjnewStateCnt++] = 230;
              }
              break;
            case 232:
              if (curChar == 67) {
                jjstateSet[jjnewStateCnt++] = 231;
              }
              break;
            case 233:
              if (curChar == 84) {
                jjstateSet[jjnewStateCnt++] = 232;
              }
              break;
            case 235:
              if (curChar == 79) {
                jjstateSet[jjnewStateCnt++] = 234;
              }
              break;
            case 236:
              if (curChar == 95) {
                jjstateSet[jjnewStateCnt++] = 235;
              }
              break;
            case 237:
              if (curChar == 68) {
                jjstateSet[jjnewStateCnt++] = 236;
              }
              break;
            case 238:
              if (curChar == 69) {
                jjstateSet[jjnewStateCnt++] = 237;
              }
              break;
            case 239:
              if (curChar == 72) {
                jjstateSet[jjnewStateCnt++] = 238;
              }
              break;
            case 240:
              if (curChar == 67) {
                jjstateSet[jjnewStateCnt++] = 239;
              }
              break;
            case 241:
              if (curChar == 84) {
                jjstateSet[jjnewStateCnt++] = 240;
              }
              break;
            default:
              break;
          }
        } while (i != startsAt);
      }
      else {
        int hiByte = (int) (curChar >> 8);
        int i1 = hiByte >> 6;
        long l1 = 1L << (hiByte & 077);
        int i2 = (curChar & 0xff) >> 6;
        long l2 = 1L << (curChar & 077);
        MatchLoop :
        do {
          switch (jjstateSet[--i]) {
            case 1:
              if (jjCanMove_0(hiByte, i1, i2, l1, l2) && kind > 7) {
                kind = 7;
              }
              break;
            case 8:
              if (jjCanMove_0(hiByte, i1, i2, l1, l2)) {
                jjAddStates(0, 2);
              }
              break;
            default:
              break;
          }
        } while (i != startsAt);
      }
      if (kind != 0x7fffffff) {
        jjmatchedKind = kind;
        jjmatchedPos = curPos;
        kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 242 - (jjnewStateCnt = startsAt))) {
        return curPos;
      }
      try {
        curChar = input_stream.readChar();
      } catch (java.io.IOException e) {
        return curPos;
      }
    }
  }

  /**
   * Description of the Method
   */
  private final void ReInitRounds() {
    int i;
    jjround = 0x80000001;
    for (i = 242; i-- > 0; ) {
      jjrounds[i] = 0x80000000;
    }
  }

  /**
   * Description of the Method
   *
   * @return   Description of the Returned Value
   */
  private final Token jjFillToken() {
    Token t = Token.newToken(jjmatchedKind);
    t.kind = jjmatchedKind;
    String im = jjstrLiteralImages[jjmatchedKind];
    t.image = (im == null) ? input_stream.GetImage() : im;
    t.beginLine = input_stream.getBeginLine();
    t.beginColumn = input_stream.getBeginColumn();
    t.endLine = input_stream.getEndLine();
    t.endColumn = input_stream.getEndColumn();
    return t;
  }

  /**
   * Description of the Method
   *
   * @param hiByte  Description of Parameter
   * @param i1      Description of Parameter
   * @param i2      Description of Parameter
   * @param l1      Description of Parameter
   * @param l2      Description of Parameter
   * @return        Description of the Returned Value
   */
  private final static boolean jjCanMove_0(int hiByte, int i1, int i2, long l1, long l2) {
    switch (hiByte) {
      case 0:
        return ((jjbitVec2[i2] & l2) != 0L);
      default:
        if ((jjbitVec0[i1] & l1) != 0L) {
          return true;
        }
        return false;
    }
  }
}
