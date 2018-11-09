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

import java.awt.Image;
import java.net.URL;

import javax.swing.tree.TreeNode;
import java.util.Enumeration;

import com.l2fprod.gui.plaf.skin.SkinUtils;

/**
 * @author    $Author: l2fprod $
 * @created   27 avril 2002
 * @version   $Revision: 1.5 $, $Date: 2002/04/27 11:33:58 $
 */
public class GtkImage extends GtkProps implements TreeNode {

  GtkStyle style;
  Image preloaded;

  /**
   * Constructor for the GtkImage object
   */
  public GtkImage() {
  }

  /**
   * Sets the Preview attribute of the GtkImage object
   *
   * @param image  The new Preview value
   */
  public void setPreview(Image image) {
    preloaded = image;
  }

  /**
   * Gets the Preview attribute of the GtkImage object
   *
   * @param w              Description of Parameter
   * @param h              Description of Parameter
   * @return               The Preview value
   * @exception Exception  Description of Exception
   */
  public Image getPreview(int w, int h) throws Exception {
    if (preloaded == null) {
      preloaded = getImage(style.getParser().getDirectory());
      if (preloaded != null) {
        preloaded = preloaded.getScaledInstance(w, h, 0);
      }
    }
    return preloaded;
  }

  /**
   * Gets the Filename attribute of the GtkImage object
   *
   * @return   The Filename value
   */
  public String getFilename() {
    String filename = (String) getProperty("file");
    if (filename != null) {
      return filename;
    }
    filename = (String) getProperty("overlay_file");
    return filename;
  }

  /**
   * Gets the Image attribute of the GtkImage object
   *
   * @param skinDirectory  Description of Parameter
   * @return               The Image value
   * @exception Exception  Description of Exception
   */
  public Image getImage(URL skinDirectory) throws Exception {
    Image im = null;
    im = getImage(skinDirectory, "file");
    if (im == null) {
      im = getImage(skinDirectory, "overlay_file");
    }
    return im;
  }

  /**
   * Gets the OverlayImage attribute of the GtkImage object
   *
   * @param skinDirectory  Description of Parameter
   * @return               The OverlayImage value
   * @exception Exception  Description of Exception
   */
  public Image getOverlayImage(URL skinDirectory) throws Exception {
    return getImage(skinDirectory, "overlay_file");
  }

  /**
   * Gets the Image attribute of the GtkImage object
   *
   * @param skinDirectory  Description of Parameter
   * @param prop           Description of Parameter
   * @return               The Image value
   * @exception Exception  Description of Exception
   */
  public Image getImage(URL skinDirectory, String prop) throws Exception {
    String filename = (String) getProperty(prop);
    if (filename == null) {
      return null;
    }

    return SkinUtils.loadImage(new URL(skinDirectory, filename));
  }

  /**
   * Gets the AllowsChildren attribute of the GtkImage object
   *
   * @return   The AllowsChildren value
   */
  public boolean getAllowsChildren() {
    return false;
  }

  /**
   * Gets the ChildAt attribute of the GtkImage object
   *
   * @param childIndex  Description of Parameter
   * @return            The ChildAt value
   */
  public TreeNode getChildAt(int childIndex) {
    return null;
  }

  /**
   * Gets the ChildCount attribute of the GtkImage object
   *
   * @return   The ChildCount value
   */
  public int getChildCount() {
    return 0;
  }

  /**
   * Gets the Index attribute of the GtkImage object
   *
   * @param node  Description of Parameter
   * @return      The Index value
   */
  public int getIndex(TreeNode node) {
    return -1;
  }

  /**
   * Gets the Parent attribute of the GtkImage object
   *
   * @return   The Parent value
   */
  public TreeNode getParent() {
    return style;
  }

  /**
   * Gets the Leaf attribute of the GtkImage object
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
    return getProperties().toString();
  }

}
