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
 * Description of the Interface
 *
 * @author    fred
 * @created   27 avril 2002
 */
public interface GtkParserConstants {

  int EOF = 0;
  int SINGLE_LINE_COMMENT = 9;
  int FORMAL_COMMENT = 10;
  int MULTI_LINE_COMMENT = 11;
  int DECIMAL_LITERAL = 13;
  int STRING_LITERAL = 14;
  int BOOLEAN = 15;
  int EXTRA = 16;
  int FONT = 17;
  int STYLE = 18;
  int CLASS = 19;
  int ENGINE = 20;
  int IMAGE = 21;
  int FUNCTION = 22;
  int RECOLORABLE = 23;
  int FILE = 24;
  int BORDER = 25;
  int STRETCH = 26;
  int SHADOW = 27;
  int STATE = 28;
  int DETAIL = 29;
  int GAP_SIDE = 30;
  int GAP_FILE = 31;
  int GAP_BORDER = 32;
  int GAP_START_FILE = 33;
  int GAP_START_BORDER = 34;
  int GAP_END_FILE = 35;
  int GAP_END_BORDER = 36;
  int OVERLAY_FILE = 37;
  int OVERLAY_BORDER = 38;
  int OVERLAY_STRETCH = 39;
  int ORIENTATION = 40;
  int ARROW_DIRECTION = 41;
  int FUNCTION_TYPE = 42;
  int STATE_TYPE = 43;
  int SHADOW_TYPE = 44;
  int ORIENTATION_TYPE = 45;
  int DIRECTION = 46;
  int LCROC = 47;
  int RCROC = 48;
  int LBRACK = 49;
  int RBRACK = 50;
  int ASSIGN = 51;
  int COMMA = 52;
  int OTHER_TOKEN = 53;
  int WIDGET_CLASS = 54;
  int WIDGET = 55;

  int DEFAULT = 0;
  int IN_SINGLE_LINE_COMMENT = 1;
  int IN_FORMAL_COMMENT = 2;
  int IN_MULTI_LINE_COMMENT = 3;

  String[] tokenImage = {
      "<EOF>",
      "\" \"",
      "\"\\t\"",
      "\"\\n\"",
      "\"\\r\"",
      "\"\\f\"",
      "\"#\"",
      "<token of kind 7>",
      "\"/*\"",
      "<SINGLE_LINE_COMMENT>",
      "\"*/\"",
      "\"*/\"",
      "<token of kind 12>",
      "<DECIMAL_LITERAL>",
      "<STRING_LITERAL>",
      "<BOOLEAN>",
      "<EXTRA>",
      "\"font\"",
      "\"style\"",
      "\"class\"",
      "\"engine\"",
      "\"image\"",
      "\"function\"",
      "\"recolorable\"",
      "\"file\"",
      "\"border\"",
      "\"stretch\"",
      "\"shadow\"",
      "\"state\"",
      "\"detail\"",
      "\"gap_side\"",
      "\"gap_file\"",
      "\"gap_border\"",
      "\"gap_start_file\"",
      "\"gap_start_border\"",
      "\"gap_end_file\"",
      "\"gap_end_border\"",
      "\"overlay_file\"",
      "\"overlay_border\"",
      "\"overlay_stretch\"",
      "\"orientation\"",
      "\"arrow_direction\"",
      "<FUNCTION_TYPE>",
      "<STATE_TYPE>",
      "<SHADOW_TYPE>",
      "<ORIENTATION_TYPE>",
      "<DIRECTION>",
      "\"[\"",
      "\"]\"",
      "\"{\"",
      "\"}\"",
      "\"=\"",
      "\",\"",
      "<OTHER_TOKEN>",
      "\"widget_class\"",
      "\"widget\"",
      };

}
