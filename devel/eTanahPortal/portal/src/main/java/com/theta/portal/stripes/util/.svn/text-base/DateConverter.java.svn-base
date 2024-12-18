/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.theta.portal.stripes.util;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import net.sourceforge.stripes.validation.TypeConverter;
import net.sourceforge.stripes.validation.ValidationError;

/**
 *
 * @author wan.fairul
 */
public class DateConverter implements TypeConverter<Date> {

    @Override
    public void setLocale(Locale locale) {
        locale.setDefault(new Locale("ms_MY"));
    }

    @Override
    public Date convert(String string, Class<? extends Date> type, Collection<ValidationError> clctn) {
        String timeDelimeter = ":";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try{
//            Date date1 = sdf.parse(string);            
//            date = new Date(date1.getTime());
            Calendar cal = new GregorianCalendar();
            int hour = cal.get(Calendar.HOUR);
            int minute = cal.get(Calendar.MINUTE);
            int second = cal.get(Calendar.SECOND);
            string = new StringBuilder(string).append(" ")
                    .append(hour).append(timeDelimeter).append(minute)
                    .append(timeDelimeter).append(second).toString();            
            sdf.applyPattern("dd/MM/yyyy hh:mm:ss");
            Date date1 = sdf.parse(string);            
            date = new Date(date1.getTime());
        }catch (Exception e){
            e.printStackTrace();
        }
        return date;
    }
    
}

