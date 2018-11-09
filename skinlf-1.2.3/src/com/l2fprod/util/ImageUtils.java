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

import java.awt.*;
import java.awt.Color;
import java.awt.color.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;

import javax.swing.CellRendererPane;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.plaf.ColorUIResource;

import com.l2fprod.contrib.freehep.PanelArtistUtilities;

/**
 * @author    $Author: l2fprod $
 * @created   27 avril 2002
 * @version   $Revision: 1.15 $, $Date: 2002/06/11 14:27:05 $
 */
public class ImageUtils implements SwingConstants {

  /**
   * Description of the Field
   */
  public final static Component producer =
    new java.awt.Component() {
    };

  /**
   * Description of the Field
   */
  public final static int PAINT_NORMAL = 0;
  /**
   * Description of the Field
   */
  public final static int PAINT_STRETCH = 1;
  /**
   * Description of the Field
   */
  public final static int PAINT_TILE = 2;
  /**
   * Description of the Field
   */
  public final static int PAINT_CENTERED = 3;
  /**
   * Description of the Field
   */
  public final static int PAINT_NONE = 5;

  /**
   * Description of the Field
   */
  public final static int TRANSPARENT_RED = 255;
  /**
   * Description of the Field
   */
  public final static int TRANSPARENT_GREEN = 0;
  /**
   * Description of the Field
   */
  public final static int TRANSPARENT_BLUE = 255;
  /**
   * Description of the Field
   */
  public final static int TRANSPARENT_TO_REMOVE = new Color(255, 0, 255).getRGB();
  /**
   * Description of the Field
   */
  public final static int TRANSPARENT_PIXEL = new Color(255, 0, 0, 0).getRGB();

  /**
   * Description of the Method
   *
   * @param s  Description of Parameter
   * @return   Description of the Returned Value
   */
  private Color decodeColor(String s) {
    int val = 0;
    try {
      if (s.startsWith("0x")) {
        val = Integer.parseInt(s.substring(2), 16);
      }
      else if (s.startsWith("#")) {
        val = Integer.parseInt(s.substring(1), 16);
      }
      else if (s.startsWith("0") && s.length() > 1) {
        val = Integer.parseInt(s.substring(1), 8);
      }
      else {
        val = Integer.parseInt(s, 10);
      }
      return new Color(val);
    } catch (NumberFormatException e) {
      return null;
    }
  }

  /**
   * Gets the DisabledIcon attribute of the ImageUtils class
   *
   * @param anIcon  Description of Parameter
   * @return        The DisabledIcon value
   */
  public static ImageIcon getDisabledIcon(ImageIcon anIcon) {
    return getDisabledIcon(anIcon.getImage());
  }

  /**
   * Gets the DisabledIcon attribute of the ImageUtils class
   *
   * @param anImage  Description of Parameter
   * @return         The DisabledIcon value
   */
  public static ImageIcon getDisabledIcon(Image anImage) {
    return new ImageIcon(getDisabledImage(anImage));
  }

  /**
   * Gets the DisabledImage attribute of the ImageUtils class
   *
   * @param anImage  Description of Parameter
   * @return         The DisabledImage value
   */
  public static Image getDisabledImage(Image anImage) {
    if (anImage == null) {
      return null;
    }
    else {
      return filterImage(new javax.swing.GrayFilter(true, 75), anImage);
    }
  }

  /**
   * Description of the Method
   *
   * @param filter  Description of Parameter
   * @param image   Description of Parameter
   * @return        Description of the Returned Value
   */
  public static Image filterImage(RGBImageFilter filter, Image image) {
    return producer.createImage(new FilteredImageSource(image.getSource(), filter));
  }

  /**
   * Description of the Method
   *
   * @param anImage  Description of Parameter
   * @return         Description of the Returned Value
   */
  public static Image rotateImage(Image anImage) {
    int w = anImage.getWidth(null);
    int h = anImage.getHeight(null);
    int[] pixels = new int[w * h];

    PixelGrabber pixel = new PixelGrabber(anImage, 0, 0, w, h, pixels, 0, w);
    try {
      pixel.grabPixels();
    } catch (Exception e) {
      //	    e.printStackTrace();
    }

    int[] rot = new int[h * w];
    int pos = 0;

    for (int i = w; i > 0; i--) {
      for (int j = 0; j < h; j++) {
        rot[pos] = pixels[i + (w * j) - 1];
        pos++;
      }
    }

    return convertBytesToImage(producer, rot, h, w);
  }

  /**
   * Description of the Method
   *
   * @param anImage  Description of Parameter
   * @param angle    Description of Parameter
   * @return         Description of the Returned Value
   */
  public static Image rotateImage(Image anImage, double angle) {
    int width = anImage.getWidth(null);
    int height = anImage.getHeight(null);
    int[] pixels = new int[width * height];

    PixelGrabber pixel = new PixelGrabber(anImage, 0, 0, width, height, pixels, 0, width);
    try {
      pixel.grabPixels();
    } catch (Exception e) {
      //    e.printStackTrace();
    }

    int[] pixels2 = new int[width * height];

    int i = 0;
    double radians = -angle / (180 / Math.PI);
    double cos = Math.cos(radians);
    double sin = Math.sin(radians);
    int centerX = width >> 1;
    int centerY = height >> 1;
    for (int oldY = -centerY; oldY < centerY; oldY++) {
      for (int oldX = -centerX; oldX < centerX; oldX++) {
        int newX = centerX + (int) ((oldX * cos) - (oldY * sin));
        int newY = centerY + (int) ((oldX * sin) + (oldY * cos));
        if (newX > 0 && newX < width && newY > 0 && newY < height) {
          pixels2[i++] = pixels[(width * newY) + newX];
        }
        else {
          pixels2[i++] = pixels[0];
        }
      }
    }
    return producer.createImage(new MemoryImageSource(width, height, pixels2, 0, width));
  }

  /**
   * Description of the Method
   *
   * @param c       Description of Parameter
   * @param pixels  Description of Parameter
   * @param w       Description of Parameter
   * @param h       Description of Parameter
   * @return        Description of the Returned Value
   */
  public static Image convertBytesToImage(Component c, int[] pixels, int w, int h) {
    return c.createImage(new MemoryImageSource(w, h, pixels, 0, w));
  }

  /**
   * Description of the Method
   *
   * @param c      Description of Parameter
   * @param g      Description of Parameter
   * @param image  Description of Parameter
   */
  public static void paint(Component c, Graphics g, Image image) {
    paint(c, g, image, true);
  }

  /**
   * Description of the Method
   *
   * @param c          Description of Parameter
   * @param g          Description of Parameter
   * @param image      Description of Parameter
   * @param paintType  Description of Parameter
   */
  public static void paint(Component c, Graphics g, Image image, int paintType) {
    paint(c, g, image, true, paintType);
  }

  /**
   * Description of the Method
   *
   * @param c                Description of Parameter
   * @param g                Description of Parameter
   * @param image            Description of Parameter
   * @param alignWithParent  Description of Parameter
   */
  public static void paint(Component c, Graphics g, Image image,
      boolean alignWithParent) {
    paint(c, g, image, alignWithParent, PAINT_STRETCH);
  }

  /**
   * Description of the Method
   *
   * @param c                Description of Parameter
   * @param g                Description of Parameter
   * @param image            Description of Parameter
   * @param alignWithParent  Description of Parameter
   * @param paintType        Description of Parameter
   */
  public static void paint(Component c, Graphics g, Image image,
      boolean alignWithParent, int paintType) {
    paint(c, g, image, 0, 0,
        ((JComponent) c).getWidth(), ((JComponent) c).getHeight(),
        alignWithParent, paintType);
  }

  /**
   * Description of the Method
   *
   * @param c       Description of Parameter
   * @param g       Description of Parameter
   * @param image   Description of Parameter
   * @param x       Description of Parameter
   * @param y       Description of Parameter
   * @param width   Description of Parameter
   * @param height  Description of Parameter
   */
  public static void paint(Component c, Graphics g, Image image,
      int x, int y, int width, int height) {
    paint(c, g, image, x, y, width, height, true, PAINT_STRETCH);

  }

  /**
   * Description of the Method
   *
   * @param c          Description of Parameter
   * @param g          Description of Parameter
   * @param image      Description of Parameter
   * @param x          Description of Parameter
   * @param y          Description of Parameter
   * @param width      Description of Parameter
   * @param height     Description of Parameter
   * @param paintType  Description of Parameter
   */
  public static void paint(Component c, Graphics g, Image image,
      int x, int y, int width, int height, int paintType) {
    paint(c, g, image, x, y, width, height, true, paintType);
  }

  /**
   * Description of the Method
   *
   * @param c                Description of Parameter
   * @param g                Description of Parameter
   * @param image            Description of Parameter
   * @param x                Description of Parameter
   * @param y                Description of Parameter
   * @param width            Description of Parameter
   * @param height           Description of Parameter
   * @param alignWithParent  Description of Parameter
   * @param paintType        Description of Parameter
   */
  public static void paint(Component c, Graphics g, Image image,
      int x, int y, int width, int height,
      boolean alignWithParent, int paintType) {
    if (image == null) {
      return;
    }

    //     Color color = c.getBackground();
    //     if (color != null && !(color instanceof ColorUIResource)) {
    //       ColorFillFilter filter = new ColorFillFilter(color);
    //       image = producer.createImage(new FilteredImageSource(image.getSource(), filter));
    //     }

    switch (paintType) {
      case PAINT_NORMAL:
        g.drawImage(image, x, y, c);
        break;
      case PAINT_STRETCH:
        g.drawImage(image, x, y, width, height, c);
        break;
      case PAINT_TILE:
        paintTile(c, g, image, x, y, width, height, alignWithParent);
        break;
      case PAINT_CENTERED:
        g.drawImage(image, (width - image.getWidth(c)) / 2,
            (height - image.getHeight(c)) / 2, c);
        break;
      case PAINT_NONE:
        break;
    }

  }

  /**
   * Description of the Method
   *
   * @param component  Description of Parameter
   * @param g          Description of Parameter
   * @param image      Description of Parameter
   * @param x          Description of Parameter
   * @param y          Description of Parameter
   * @param width      Description of Parameter
   * @param height     Description of Parameter
   */
  public static void paintTile(Component component, Graphics g, Image image, int x, int y, int width, int height) {
    paintTile(component, g, image, x, y, width, height, true);
  }

  /**
   * Description of the Method
   *
   * @param image  Description of Parameter
   * @return       Description of the Returned Value
   */
  public static Image transparent(Image image) {
    return toBufferedImage(image);
  }

  /**
   * Description of the Method
   *
   * @param image   Description of Parameter
   * @param x       Description of Parameter
   * @param y       Description of Parameter
   * @param width   Description of Parameter
   * @param height  Description of Parameter
   * @return        Description of the Returned Value
   */
  public static Image grab(Image image, int x, int y, int width, int height) {
    if (width * height <= 0) {
      return null;
    }

    if (image instanceof BufferedImage) {
      return ((BufferedImage) image).getSubimage(x, y, width, height);
    }

    int[] pixels = new int[width * height];
    PixelGrabber grabber = new PixelGrabber(image, x, y, width, height, pixels, 0, width);
    try {
      grabber.grabPixels();
    } catch (Exception e) {
      e.printStackTrace();
    }

    int pixel;

    int alpha;

    int red;

    int green;

    int blue;
    for (int j = 0; j < height; j++) {
      for (int i = 0; i < width; i++) {
        pixel = pixels[j * width + i];
        alpha = (pixel >> 24) & 0xff;
        red = (pixel >> 16) & 0xff;
        green = (pixel >> 8) & 0xff;
        blue = (pixel) & 0xff;
        if ((red == TRANSPARENT_RED) &&
            (green == TRANSPARENT_GREEN) &&
            (blue == TRANSPARENT_BLUE)) {
          pixels[j * width + i] = TRANSPARENT_PIXEL;
        }
      }
    }

    Image newImage = producer.createImage(new MemoryImageSource(width, height, pixels, 0, width));
    return toBufferedImage(newImage);
  }

  /**
   * Description of the Method
   *
   * @param image   Description of Parameter
   * @param factor  Description of Parameter
   * @return        Description of the Returned Value
   */
  public static Image buildTile(Image image, int factor) {
    int width = image.getWidth(producer);
    int height = image.getHeight(producer);

    int[] pixels = new int[width * height];
    PixelGrabber grabber = new PixelGrabber(image, 0, 0, width, height, pixels, 0, width);
    try {
      grabber.grabPixels();
    } catch (Exception e) {
      e.printStackTrace();
    }

    // do an horizontal tiling
    int[] zoomed = new int[pixels.length * factor];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < factor; j++) {
        System.arraycopy(pixels, width * i, zoomed, (width * factor * i) + width * j, width);
      }
    }

    pixels = zoomed;

    // do a vertical duplication
    int[] zoomed2 = new int[pixels.length * factor];
    for (int i = 0; i < factor; i++) {
      System.arraycopy(pixels, 0, zoomed2, i * pixels.length, pixels.length);
    }

    return producer.createImage(new MemoryImageSource(width * factor,
        height * factor,
        zoomed2, 0, width * factor));
  }

  /**
   * Description of the Method
   *
   * @param image  Description of Parameter
   * @return       Description of the Returned Value
   */
  public static BufferedImage toBufferedImage(Image image) {
    // This code ensures that all the pixels in
    // the image are loaded.
    image = new ImageIcon(image).getImage();

    // Create the buffered image.
    BufferedImage bufferedImage = new BufferedImage(
        image.getWidth(null), image.getHeight(null),
        BufferedImage.TYPE_INT_ARGB);

    // Copy image to buffered image.
    Graphics2D g = bufferedImage.createGraphics();

    // paint the image.
    g.drawImage(image, 0, 0, null);
    g.dispose();

    // Get the DataBuffer from your bufferedImage like
    DataBuffer db = bufferedImage.getRaster().getDataBuffer();
    // then you can just iterate through and convert your chosencolor to a transparent color
    for (int i = 0, c = db.getSize(); i < c; i++) {
      if (db.getElem(i) == TRANSPARENT_TO_REMOVE) {
        // set to transparent
        db.setElem(i, TRANSPARENT_PIXEL);
      }
    }

    return bufferedImage;
  }

  /**
   * Description of the Method
   *
   * @param image  Description of Parameter
   * @return       Description of the Returned Value
   */
  public static Image flipHorizontally(Image image) {
    if (image == null) {
      return null;
    }

    AffineTransform transform =
        PanelArtistUtilities.getYFlipTransform(image.getHeight(producer));
    AffineTransformOp operation =
        new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
    return operation.filter((BufferedImage) image, null);
  }

  /**
   * Description of the Method
   *
   * @param anImage  Description of Parameter
   * @return         Description of the Returned Value
   */
  public static Image rotateLeft(Image anImage) {
    if (anImage == null) {
      return null;
    }

    AffineTransform transform =
        PanelArtistUtilities.getCCWRotateTransform(anImage.getWidth(producer), anImage.getHeight(producer));
    AffineTransformOp operation =
        new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
    return operation.filter((BufferedImage) anImage, null);
  }

  /**
   * Description of the Method
   *
   * @param component  Description of Parameter
   * @param g          Description of Parameter
   * @param image      Description of Parameter
   */
  private static void paintTile(Component component, Graphics g, Image image) {
    paintTile(component, g, image, 0, 0, ((JComponent) component).getWidth(), ((JComponent) component).getHeight(), true);
  }

  /**
   * Description of the Method
   *
   * @param component        Description of Parameter
   * @param g                Description of Parameter
   * @param image            Description of Parameter
   * @param alignWithParent  Description of Parameter
   */
  private static void paintTile(Component component, Graphics g, Image image, boolean alignWithParent) {
    paintTile(component, g, image, 0, 0, ((JComponent) component).getWidth(), ((JComponent) component).getHeight(), alignWithParent);
  }

  /**
   * Description of the Method
   *
   * @param component        Description of Parameter
   * @param g                Description of Parameter
   * @param image            Description of Parameter
   * @param x                Description of Parameter
   * @param y                Description of Parameter
   * @param width            Description of Parameter
   * @param height           Description of Parameter
   * @param alignWithParent  Description of Parameter
   */
  private static void paintTile(Component component, Graphics g, Image image, int x, int y, int width, int height, boolean alignWithParent) {

    if (image == null) {
      return;
    }

    final int dx = image.getWidth(null);
    final int dy = image.getHeight(null);

    // skip a 0 size image
    if (dx <= 0 || dy <= 0) {
      return;
    }

    //work out the offset from (0,0) in the root frame.
    int xoff = 0;
    int yoff = 0;

    Shape oldClip = g.getClip();

    if (oldClip.contains(x, y, width, height) || (width <= 0 || height <= 0)) {
      g.setClip(x, y, width, height);
    } else if (oldClip.intersects(x, y, width, height)) {
      Rectangle currentRect = oldClip.getBounds();
      g.setClip(SwingUtilities.computeIntersection(x,
                                                   y,
                                                   width,
                                                   height,
                                                   (Rectangle)currentRect.clone()));
    }
    
    if (alignWithParent) {
      Component parent = component.getParent();
      xoff = component.getLocation().x;
      yoff = component.getLocation().y;

      while (parent != null && (parent instanceof javax.swing.JInternalFrame == false)) {
        //don't want the screen coords of the topmost container...
        if (parent.getParent() != null) {
          xoff += parent.getLocation().x;
          yoff += parent.getLocation().y;
        }

        parent = parent.getParent();
      }

      x -= (xoff % dx);
      y -= (yoff % dy);
    }

    int maxX = x + width + dx;
    int maxY = y + height + dy;

    for (; x <= maxX; x += dx) {
      for (int j = y; j <= maxY; j += dy) {
        g.drawImage(image, x, j, component);
      }
    }

    g.setClip(oldClip);

  }

}
