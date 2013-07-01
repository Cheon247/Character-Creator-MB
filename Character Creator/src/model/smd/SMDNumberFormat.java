/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.smd;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 *
 * @author Chingo
 */
public class SMDNumberFormat {
// source: http://stackoverflow.com/questions/16309189/java-use-decimalformat-to-format-doubles-and-integers-but-keep-integers-without

    public static String format(Number n) {
        NumberFormat format = DecimalFormat.getInstance();
        format.setRoundingMode(RoundingMode.FLOOR);
        format.setMinimumFractionDigits(6);
        format.setMaximumFractionDigits(6);
        String s = format.format(n);

        return s.replaceAll(",", ".");
    }
}
