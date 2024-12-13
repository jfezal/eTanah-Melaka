/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Map;

/**
 *
 * @author fikri
 */
public class StringUtils {
    static final String SEPARATOR = ", ";

    public static boolean isNotBlank(String[] str) {
        return !isBlank(str);
    }

    public static boolean isBlank(String[] str) {
        if (str == null) {
            return true;
        }
        if (str.length > 0) {
            return isBlank(str[0]);
        }
        return true;
    }

    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    public static String removeLeadingZeros(String str) {
        String returnVal = "";
        while (str.substring(0, 1).equalsIgnoreCase("0")) {
            str = str.substring(1, str.length());
        }
        returnVal = str;
        return returnVal;
    }

    public static String formatLuas(String luas) {
        NumberFormat df = new DecimalFormat("#,##0.0000");
        if (org.apache.commons.lang.StringUtils.isNotBlank(luas)) {
            Double d = Double.parseDouble(luas);
            return df.format(d);
        }else {
            return "0.00";
        }
    }
    
    /**
     * Pretty print values in Map
     * @param map
     * @return 
     */
    public static String toString(Map<?,?> map) {
        if (map == null) return "null";
        StringBuilder sb = new StringBuilder("[");
        String sep = "";
        for (Object object : map.keySet()) {
            Object value = map.get(object);
            sb.append(sep)
              .append(object.toString())
              .append("=")
              .append(value != null ? value.toString() : "null");
            sep = SEPARATOR;
        }
        return sb.append("]").toString();
    }
}
