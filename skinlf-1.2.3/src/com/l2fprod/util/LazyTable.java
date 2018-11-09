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

import java.util.Hashtable;

/**
 * Description of the Class
 *
 * @author    fred
 * @created   27 avril 2002
 */
public class LazyTable extends Hashtable {

  private final static Object PENDING = new String("Pending");

  /**
   * Description of the Method
   *
   * @param key  Description of Parameter
   * @return     Description of the Returned Value
   */
  public Object get(Object key) {
    /*
     *  Quickly handle the common case, without grabbing
     *  a lock.
     */
    Object value = super.get(key);
    if ((value != PENDING) &&
        !(value instanceof ActiveValue) &&
        !(value instanceof LazyValue)) {
      return value;
    }

    /*
     *  If the LazyValue for key is being constructed by another
     *  thread then wait and then return the new value, otherwise drop
     *  the lock and construct the ActiveValue or the LazyValue.
     *  We use the special value PENDING to mark LazyValues that
     *  are being constructed.
     */
    synchronized (this) {
      value = super.get(key);
      if (value == PENDING) {
        do {
          try {
            this.wait();
          } catch (InterruptedException e) {
          }
          value = super.get(key);
        } while (value == PENDING);
        return value;
      }
      else if (value instanceof LazyValue) {
        super.put(key, PENDING);
      }
      else if (!(value instanceof ActiveValue)) {
        return value;
      }
    }

    /*
     *  At this point we know that the value of key was
     *  a LazyValue or an ActiveValue.
     */
    if (value instanceof LazyValue) {
      try {
        /*
         *  If an exception is thrown we'll just put the LazyValue
         *  back in the table.
         */
        value = ((LazyValue) value).createValue(this);
      } finally {
        synchronized (this) {
          if (value == null) {
            super.remove(key);
          }
          else {
            super.put(key, value);
          }
          this.notify();
        }
      }
    }
    else {
      value = ((ActiveValue) value).createValue(this);
    }

    return value;
  }

  /**
   * Description of the Interface
   *
   * @author    fred
   * @created   27 avril 2002
   */
  public interface LazyValue {
    /**
     * Description of the Method
     *
     * @param table  Description of Parameter
     * @return       Description of the Returned Value
     */
    Object createValue(LazyTable table);
  }

  /**
   * Description of the Interface
   *
   * @author    fred
   * @created   27 avril 2002
   */
  public interface ActiveValue {
    /**
     * Description of the Method
     *
     * @param table  Description of Parameter
     * @return       Description of the Returned Value
     */
    Object createValue(LazyTable table);
  }
}

