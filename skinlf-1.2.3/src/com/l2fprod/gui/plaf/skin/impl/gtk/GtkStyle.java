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
package com.l2fprod.gui.plaf.skin.impl.gtk;

import javax.swing.tree.TreeNode;
import java.util.Enumeration;
import javax.swing.tree.MutableTreeNode;

import com.l2fprod.gui.plaf.skin.impl.gtk.parser.*;

/**
 * @author    $Author: l2fprod $
 * @created   27 avril 2002
 * @version   $Revision: 1.3 $, $Date: 2002/04/27 11:35:03 $
 */
public class GtkStyle extends GtkProps implements MutableTreeNode {

  /**
   * Description of the Field
   */
  public String name;

  /**
   * Description of the Field
   */
  public GtkEngine engine;

  /**
   * Description of the Field
   */
  public MutableTreeNode parent;

  /**
   * Description of the Field
   */
  public GtkParser parser;

  /**
   * Constructor for the GtkStyle object
   */
  public GtkStyle() {
  }

  /**
   * Sets the Parent attribute of the GtkStyle object
   *
   * @param newParent  The new Parent value
   */
  public void setParent(MutableTreeNode newParent) {
    this.parent = newParent;
  }

  /**
   * Sets the UserObject attribute of the GtkStyle object
   *
   * @param object  The new UserObject value
   */
  public void setUserObject(Object object) {
  }

  /**
   * Gets the Engine attribute of the GtkStyle object
   *
   * @return   The Engine value
   */
  public GtkEngine getEngine() {
    return engine;
  }

  /**
   * Gets the Parser attribute of the GtkStyle object
   *
   * @return   The Parser value
   */
  public GtkParser getParser() {
    return parser;
  }

  /**
   * Gets the AllowsChildren attribute of the GtkStyle object
   *
   * @return   The AllowsChildren value
   */
  public boolean getAllowsChildren() {
    return true;
  }

  /**
   * Gets the ChildAt attribute of the GtkStyle object
   *
   * @param childIndex  Description of Parameter
   * @return            The ChildAt value
   */
  public TreeNode getChildAt(int childIndex) {
    return (TreeNode) engine.getImages().elementAt(childIndex);
  }

  /**
   * Gets the ChildCount attribute of the GtkStyle object
   *
   * @return   The ChildCount value
   */
  public int getChildCount() {
    if (engine == null) {
      return 0;
    }
    else {
      return engine.getImages().size();
    }
  }

  /**
   * Gets the Index attribute of the GtkStyle object
   *
   * @param node  Description of Parameter
   * @return      The Index value
   */
  public int getIndex(TreeNode node) {
    return engine.getImages().indexOf(node);
  }

  /**
   * Gets the Parent attribute of the GtkStyle object
   *
   * @return   The Parent value
   */
  public TreeNode getParent() {
    return parent;
  }

  /**
   * Gets the Leaf attribute of the GtkStyle object
   *
   * @return   The Leaf value
   */
  public boolean isLeaf() {
    return false;
  }

  /**
   * Description of the Method
   *
   * @return   Description of the Returned Value
   */
  public Enumeration children() {
    throw new Error("Not implemented");
  }

  /**
   * Description of the Method
   *
   * @return   Description of the Returned Value
   */
  public String toString() {
    return name;
  }

  /**
   * Description of the Method
   *
   * @param child  Description of Parameter
   * @param index  Description of Parameter
   */
  public void insert(MutableTreeNode child, int index) {
  }

  /**
   * Description of the Method
   *
   * @param index  Description of Parameter
   */
  public void remove(int index) {
  }

  /**
   * Description of the Method
   *
   * @param node  Description of Parameter
   */
  public void remove(MutableTreeNode node) {
  }

  /**
   * Description of the Method
   */
  public void removeFromParent() {
  }

}
