/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.validation.api.builtin.stringvalidation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.netbeans.validation.api.Problems;

/**
 *
 * @author daniel
 */
public class DniValidator extends StringValidator {

    @Override
    public void validate(Problems problems, String string, String nif) {

        if (!"".equals(nif)) {
            boolean correcto = false;

            Pattern pattern = Pattern.compile("(\\d{1,8})([TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke])");

            Matcher matcher = pattern.matcher(nif);

            if (matcher.matches()) {

                String letra = matcher.group(2);

                String letras = "TRWAGMYFPDXBNJZSQVHLCKE";

                int index = Integer.parseInt(matcher.group(1));

                index = index % 23;

                String reference = letras.substring(index, index + 1);

                if (reference.equalsIgnoreCase(letra)) {

                    correcto = true;

                } else {

                    correcto = false;

                }

            } else {

                correcto = false;

            }

         if(!correcto){
         problems.add("El "+string+" no es correcto");
         
         }

        }
    }
}

