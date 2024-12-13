/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.util;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author fikri
 */
public class DateUtil {
    private Calendar cal = Calendar.getInstance();

    public void setCalendar (Date date) {
        cal.set(date.getYear(), date.getMonth(), date.getDate());
    }

    public static String getDateName(String month) {

        if( month.length() == 1) {
            month = "0" + month;
        }

        if (month.equals("01")) {
            month = "JANUARI";
        } else if (month.equals("02")) {
            month = "FEBRUARI";
        } else if (month.equals("03")) {
            month = "MAC";
        } else if (month.equals("04")) {
            month = "APRIL";
        } else if (month.equals("05")) {
            month = "MEI";
        } else if (month.equals("06")) {
            month = "JUN";
        } else if (month.equals("07")) {
            month = "JULAI";
        } else if (month.equals("08")) {
            month = "OGOS";
        } else if (month.equals("09")) {
            month = "SEPTEMBER";
        } else if (month.equals("10")) {
            month = "OKTOBER";
        } else if (month.equals("11")) {
            month = "NOVEMBER";
        } else if (month.equals("12")) {
            month = "DISEMBER";
        }
        return month;
    }

    public static int getYear(){
        java.util.Calendar c = java.util.Calendar.getInstance();
        return c.get(java.util.Calendar.YEAR);
    }

    public static int getMonth(){
        java.util.Calendar c = java.util.Calendar.getInstance();
        return c.get(java.util.Calendar.MONTH);
    }

    public static int getDay(){
        java.util.Calendar c = java.util.Calendar.getInstance();
        return c.get(java.util.Calendar.DATE);
    }

    public static void main(String[] args) {
        DateUtil du = new DateUtil();
        System.out.println("year " + du.getYear());
        System.out.println("month " + du.getDateName( String.valueOf( du.getMonth()+1  )) );
        System.out.println("month " + du.getDay());
    }
}
