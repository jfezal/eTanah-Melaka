/*
 * Functions.java
 *
 * Created on September 7, 2006, 2:04 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package etanah.view.stripes.pelupusan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.text.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Functions {
    
    /** Creates a new instance of Functions */
    public Functions() {
    }
    
    public static final int VARCHAR = 1;
    public static final int DATETIME = 2;
    public static final int NUMBER = 3;
    public static final int WHERE = 4;
    public static final int LIKE = 5;
    public static final int DATE = 6;
    public static final int PAGESIZE = 100;
    
    public static int pageStartRow(String strPageNo){
        int pageNo = 0;
        int startRow = 0;
        if (strPageNo != null) {
            pageNo = Integer.parseInt(strPageNo);
            startRow = (pageNo * PAGESIZE) - PAGESIZE;
        } else {
            startRow = 0;
        }
        return startRow;
    }
    public static String toURL(String strValue){
        if (strValue==null) return "";
        if (strValue.compareTo("")==0 ) return "";
        return java.net.URLEncoder.encode(strValue);
    }

    public static String toHTML(String value) {
        if (value=="null") return "";
        if (value==null) return "";
        //value = replace(value, "&", "&");
        value = replace(value, "&", "&amp;");
        value = replace(value, "<", "<");
        value = replace(value, ">", ">");
        value = replace(value, "\"", "&" + "quot;");
        value = replace(value, "\n", "<br>");	
        //value = replace(value, "�", "&divide;");	
        value = value.replace("�","&divide;");
        return value;
    }

    public static String toHTML2(String value) {
        if (value==null) return "";
        value = replace(value, "&", "&");
        value = replace(value, "<", "<");
        value = replace(value, ">", ">");
        value = replace(value, "\"", "&" + "quot;");	
        return value;
    }
    public static String toHTML3(String value) {
        if (value==null) return "";
        value = replace(value, "&", "&");
        value = replace(value, "<", "<");
        value = replace(value, ">", ">");
        value = replace(value, "\"", "&" + "quot;");
	value = replace(value, "ý", "�");
	value = replace(value, "�", "�");// + "yacute;"); 
	value = replace(value, "�", "�");
        return value;
    }
    
    public static String toJS(String value) {
        if ( value == null ) return "";
        value = replace(value, "&", "&amp;");
        value = replace(value, "<", "&lt;");
        value = replace(value, ">", "&gt;");
        value = replace(value, "\"", "&" + "quot;");
        value = replace(value, "\n", "<br>");
        value = replace(value, "'", "\\\'");
        return value;
    }

    public static String toText(String value) {
        if (value == null) {
            return "";
        }
        value = replace(value, "&amp;", "&");
        value = replace(value, "&lt;", "<");
        value = replace(value, "&gt;", ">");
        value = replace(value, "&" + "quot;", "\"");
        value = replace(value, "<br>", "\n");
        return value;
    }

    public static boolean isNumber (String param) {
        boolean result;
        if ( param == null || param.equals("")) return true;
        param=param.replace('d','_').replace('f','_');
        try {
          Double dbl = new Double(param);
          result = true;
        }
        catch (NumberFormatException nfe) {
          result = false;
        }
        return result;
    }

    public static String replace(String str, String pattern, String replace) {
        if (replace == null) {
            replace = "";
        }
        int s = 0, e = 0;
        StringBuffer result = new StringBuffer((int) str.length()*2);
        while ((e = str.indexOf(pattern, s)) >= 0) {
            result.append(str.substring(s, e));
            result.append(replace);
            s = e + pattern.length();
        }
        result.append(str.substring(s));
        return result.toString();
    }

    public static String toSQL(String value, int type) {
        if ((value==null) && (type==WHERE)) return " is Null";
        if ((value==null) && ((type!=WHERE)&&(type!=DATE))) return "Null";
        String param = value;
        if(param!=null) param = param.trim();
        if ("".equals(param) && (type == VARCHAR)) {
            return "Null";
        }else if ("".equals(param) && (type == WHERE))
            return "is Null";
        
        //System.out.println("toSQL param: " + param);
        
        switch (type) {
            case VARCHAR: {
                param = replace(param, "'", "''");
                param = replace(param, "&", "&");
				param = replace(param, "�", "\""); // Double quota [�]  from ppt file must be replaced by ["]
				param = replace(param, "�", "\""); // Double quota [�]  from ppt file must be replaced by ["]				
                param = "'" + param + "'";
                break;
            }
            case LIKE: {
                param = replace(param, "'", "''");
                param = "'%" + param + "%'";
                break;
            }
            case WHERE: {
                param = replace(param, "'", "''");
                param = "= '" + param + "'";
            }
            case NUMBER: {
                try {
                    if (! isNumber(value) || "".equals(param)) param="null";
                    else param = value;
                }catch (NumberFormatException nfe) {
                    param = "null";
                }
                break;
            }
            case DATE: {
                if(param==null) param = "Null";
                else if(param.equals("")) param = "Null";
                else param = "'" + param + "'";
                param = "TO_DATE ("+param+",'mm/dd/yyyy')";
                break;      
            }
            case DATETIME: {
                if(param==null) param = "Null";
                else if(param.equals("")) param = "Null";
                else param = "'" + param + "'";
                param = "TO_DATE ("+param+",'mm/dd/yyyy hh24:mi:ss')";
                break;      
            }
        }
        return param;
    }

    public static String[] split(String values, String delim){
        int i = 0;

        StringTokenizer a = new StringTokenizer(values, delim);
        String value[]= new String[a.countTokens()];	 
        while (a.hasMoreTokens()) {
            value[i] = a.nextToken();
            i++;
        }
        return value;
    }
    
    public static String toMMddyyyyDate(Date sqlDate)    {
        
        String strDay = "";
        String strMonth= "";
        String strYear = "";
        String strDate = "";
        
        if(sqlDate != null){
            if(String.valueOf(sqlDate.getDate()).length() < 2){
                strDay = "0" + String.valueOf(sqlDate.getDate());
            }else{
                strDay = String.valueOf(sqlDate.getDate());
            }
            
            if(String.valueOf(sqlDate.getMonth()+1).length() < 2){
                strMonth = "0" + String.valueOf(sqlDate.getMonth()+1);
            }else{
                strMonth = String.valueOf(sqlDate.getMonth()+1);
            }
            
            strYear = String.valueOf(1900 + sqlDate.getYear());
            strDate = strMonth + "/"+ strDay + "/" + strYear;
        }

        //System.out.println("Pre Date: " + sqlDate);
        //System.out.println("Post Date: " + strDate);
        
        return strDate;
    }
    
    public static String DateToString(Date sqlDate, String formatDate )    {
        Date date = new Date ();
        SimpleDateFormat dateformat = new SimpleDateFormat(formatDate);
        //StringBuilder newDate = new StringBuilder( dateformat.format( date ) );
        StringBuilder newDate = new StringBuilder( dateformat.format( sqlDate ) );
        return newDate.toString();
 
    }
    
    public String getSeqNum(Connection conn, String tableName, String fieldName){
        String seqNumber = "";
        PreparedStatement ps = null;
        try{
            ps = conn.prepareStatement("SELECT "+fieldName+"+1 FROM "+tableName);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                seqNumber = rs.getString(1);
            }
            System.out.println(seqNumber);
        }catch(Exception e){
           seqNumber = "";
           System.out.println("-->"+e.toString());
        }    
        
        //increase retnumber
        try{
            ps = conn.prepareStatement("UPDATE "+tableName+" SET "+fieldName+" =  "+fieldName+"+1");
            int g = ps.executeUpdate();
        }catch(Exception e){
            System.out.println(e.toString());
        }finally {
            if(ps != null) {
                try { 
                    ps.close();
                    ps=null;
                } catch (Exception e) { e.printStackTrace(); }
            }
        }   
        
        return seqNumber;
    }
    
    public String getRunNo(String tableName, Connection conn, boolean blnYear, boolean blnMonth, boolean blnDay) throws Exception{
        String strRunNo = "";
        String strQuery = "";
        String strYear = "", strMonth = "", strDay = "";
        String strToday = "";
        java.util.Date dtm = new java.util.Date();
        java.sql.ResultSet rs = null;
        java.sql.Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        
        strYear =  String.valueOf(dtm.getYear() + 1900);
        strMonth = String.valueOf(dtm.getMonth() + 1);
        if(strMonth.length()<2){
            strMonth = "0" + strMonth;
        }
        strDay = String.valueOf(dtm.getDate());
        if(strDay.length()<2){
            strDay = "0" + strDay;
        }
        
        if(blnYear){
            strToday = strYear;
        }
        if(blnMonth){
            strToday = strMonth ;
        }
        if(blnDay){
            strToday = strDay;
        }
        
        //Get Run Number
        strQuery = "SELECT fld_runnum, fld_mm FROM tbl_idrunnum " +
                "where fld_indicator = " + toSQL(tableName, VARCHAR);
        
        try{
            rs = stmt.executeQuery(strQuery);
            if(rs.next()){
                if(strToday.equals(rs.getString("fld_mm"))){
                    strRunNo = toHTML(rs.getString("fld_runnum"));
                    strRunNo = String.valueOf(Integer.parseInt(strRunNo) + 1);
                }else{       
                    if (strToday.indexOf("0")==0) {
                        strToday = String.valueOf(dtm.getMonth() + 1);
                        if(strToday.equals(rs.getString("fld_mm"))){
                            strRunNo = toHTML(rs.getString("fld_runnum"));
                            strRunNo = String.valueOf(Integer.parseInt(strRunNo) + 1);
                            System.out.println("strToday="+strToday+" contain 0--");
                        } else {
                            strRunNo = "1";
                        }
                    } else {                    
                        strRunNo = "1";
                    }
                }
                
                //Update
                strQuery = "update tbl_idrunnum " +
                        "set fld_runnum = " + toSQL(strRunNo, NUMBER) + ", " +
                        "fld_mm = " + toSQL(strToday, VARCHAR) + " " +
                        "where fld_indicator = " + toSQL(tableName, VARCHAR);
                System.out.println("-->" + strQuery);
            }else{
                strRunNo = "1";
                strQuery = "insert into tbl_idrunnum(" +
                        "fld_indicator, fld_mm, fld_runnum) values(" +
                        toSQL(tableName, VARCHAR) + ", " + toSQL(strToday, VARCHAR) + ", " + toSQL(strRunNo, NUMBER) + ")";
            }
            
            strToday = strYear + strMonth;
            if(strRunNo.length() == 1){
                //strRunNo = strYear + strMonth + strDay + "-" +"0000" + strRunNo;
                strRunNo = strToday + "0000" + strRunNo;
            }else if(strRunNo.length() == 2){
                strRunNo = strToday + "000" + strRunNo;
            }else if(strRunNo.length() == 3){
                strRunNo = strToday + "00" + strRunNo;
            }else if(strRunNo.length() == 4){
                strRunNo = strToday + "0" + strRunNo;
            }else{
                strRunNo = strToday + strRunNo;
            }
            stmt.executeUpdate(strQuery);
        }catch(Exception ex){
            strRunNo = "Error : " + ex.getMessage();
            strRunNo += "<br>" + strQuery;
        }
        
        return strRunNo;
    }
    
    public String today(String dateFormat){
        String strYear = "", strMonth = "", strDay = "";

        String strToday = "";
        java.util.Date dtm = new java.util.Date();
        
        strYear =  String.valueOf(dtm.getYear() + 1900);
        strMonth = String.valueOf(dtm.getMonth() + 1);
                
        if(strMonth.length()<2){
            strMonth = "0" + strMonth;
        }
        strDay = String.valueOf(dtm.getDate());
        if(strDay.length()<2){
            strDay = "0" + strDay;
        }
        
        if(dateFormat.equalsIgnoreCase("MM/dd/yyyy")){
            strToday = strMonth + "/" + strDay + "/" + strYear;    
        }else if(dateFormat.equalsIgnoreCase("dd/MM/yyyy")){
            strToday = strDay + "/" + strMonth + "/" + strYear;
        }else{
            strToday = strDay + "/" + strMonth + "/" + strYear;
        }
        return strToday;
    }
    
    public String now() {
        String strTime;
        String strHour24="", strMin="", strSec="";

        java.util.Date dtm = new java.util.Date();

        strHour24 = String.valueOf(dtm.getHours());
        strMin = String.valueOf(dtm.getMinutes());
        strSec = String.valueOf(dtm.getSeconds());

        strTime=strHour24+":"+strMin+":"+strSec;
        
        return strTime;
    }

    public static String DisplayDate(Date date, String strDateFormat)throws java.text.ParseException {
        if(date == null) return "";
        java.text.SimpleDateFormat dataformat = new java.text.SimpleDateFormat(strDateFormat);
        String s4 = dataformat.format(date);
        //System.out.println(dataformat.format(date));
        return dataformat.format(date);
    }

    public static java.util.Date StringToDate(String strDate, String strDateFormat) throws java.text.ParseException {
        //System.out.println("strDate: " + strDate + "--" + "strDateFormat: "+strDateFormat);
        java.util.Date myDate = null;
        if(strDate == null){
            myDate = null;
        }else if(strDate.equals("")){
            myDate = null;
        }else{
            java.text.SimpleDateFormat myDateFormat = new java.text.SimpleDateFormat(strDateFormat);
            myDate = myDateFormat.parse(strDate);
        }
        return myDate;
    }

    public static String distinctList(String list, String separator){
        String[] undistincts = list.split(separator);
        String distinctList = "";
        for(int i=0; i<undistincts.length; i++){
            if(distinctList.indexOf(undistincts[i])<0){
                distinctList = distinctList + undistincts[i] + separator;
            }
        }
        if(distinctList.indexOf(separator)>=0){
            distinctList = distinctList.substring(0, distinctList.length()-separator.length());
        }

        return distinctList;
    }

    public static String partialString(String str){
        if (str.length() > 200) {
            str = str.substring(0, 200);
            str = str.substring(0, str.lastIndexOf(" ")) + " ...";
        }
        return str;
    }

    public static Integer toInteger(Object number){
        Integer num;
        if(number == null){
            num = 0;
        }else{
            num = Integer.parseInt(number.toString());
        }
        return num;
    }

    public static String numberToRank(Integer number){
        String rank = "";
        String strNumber = String.valueOf(number);
        String strNumberTemp = "";


        strNumberTemp = strNumber.substring(strNumber.length()-1, strNumber.length());
        if(strNumberTemp.equals("1")){
            rank = "st";
        }else if(strNumberTemp.equals("2")){
            rank = "nd";
        }else if(strNumberTemp.equals("3")){
            rank = "rd";
        }else{
            rank = "th";
        }

        if(strNumber.length() > 1){
            strNumberTemp = strNumber.substring(strNumber.length()-2, strNumber.length());
            if(strNumberTemp.equals("11")){
                rank = "th";
            }
        }
        
        return rank;
    }

    public Date computeStartQuarterDate(String startQ, String startY) {
        Date startDate=null;
        int iQ=Integer.parseInt(startQ);
        String newDt="";

        switch (iQ) {
            case 1:
                newDt="1/1/"+startY;
                break;
            case 2:
                newDt="4/1/"+startY;
                break;
            case 3:
                newDt="7/1/"+startY;
                break;
            case 4:
                newDt="10/1/"+startY;
                break;
        }
        System.out.println("computeStartQuarterDate="+newDt);
        try {
            startDate=StringToDate(newDt, "MM/dd/yyyy");
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return startDate;
    }

    public Date computeEndQuarterDate(String endQ, String endY) {
        Date endDate=null;

        int iQ=Integer.parseInt(endQ);
        String newDt="";

        switch (iQ) {
            case 1:
                newDt="3/31/"+endY;
                break;
            case 2:
                newDt="6/30/"+endY;
                break;
            case 3:
                newDt="9/30/"+endY;
                break;
            case 4:
                newDt="12/31/"+endY;
                break;
        }
        System.out.println("computeEndQuarterDate="+newDt);
        try {
            endDate=StringToDate(newDt, "MM/dd/yyyy");
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        return endDate;
    }


    public double calculateDateTimeDiff(Date startDt, Date endDt) {

        System.out.println("##Functions.calculateDateTimeDiff()##");
        System.out.println("startDt="+startDt+"--"+startDt.getDate()+"--"+startDt.getMonth()+"--"+startDt.getYear()+"--"+startDt.getHours()+"--"+startDt.getMinutes()+"--"+startDt.getSeconds());
        System.out.println("endDt="+endDt+"--"+endDt.getDate()+"--"+endDt.getMonth()+"--"+endDt.getYear()+"--"+endDt.getHours()+"--"+endDt.getMinutes()+"--"+endDt.getSeconds());
        
        Calendar cal1=Calendar.getInstance();
        Calendar cal2=Calendar.getInstance();

        cal1.setTime(startDt);
        cal2.setTime(endDt);

        long ldate1=startDt.getTime()+cal1.get(Calendar.ZONE_OFFSET);
        long ldate2=endDt.getTime()+cal2.get(Calendar.ZONE_OFFSET);

        double hr1=(double)(ldate1/3600000);
        double hr2=(double)(ldate2/3600000);

        double days1=(double)hr1/24;
        double days2=(double)hr2/24;

        System.out.println("days2 - days1="+days2+" - "+days1);

        Double daysdiff=0.0000000;
        daysdiff=days2-days1;
        System.out.println("daysdiff="+daysdiff);

        return daysdiff;
    }

    public static ArrayList stringToArrayList(String list, String separator) {
        String[] pieces = list.split(separator);
        for (int i = pieces.length - 1; i >= 0; i--) {
            pieces[i] = pieces[i].trim();
        }
        return new ArrayList(Arrays.asList(pieces));
    }

     public String distintCcList(String toList, String ccList){
         toList = distinctList(toList,",");
         ArrayList tos = stringToArrayList(toList,",");
         for(int i=0; i<tos.size(); i++){
            ccList = ccList.replaceAll(tos.get(i).toString(), "");
         }
         
         ArrayList ccs = stringToArrayList(ccList,",");
         ccList = "";
         for(int i=0; i<ccs.size(); i++){
            if(!ccs.get(i).equals(""))
                ccList = ccList + "," + ccs.get(i).toString();
         }//Next

         if(ccList.indexOf(",")>=0)
            ccList = ccList.substring(1,ccList.length());

         return ccList;
     }

    public String stateColor(int stateId){
        String cssClass = "";
        switch (stateId){
            case 1:
               cssClass = "ScheduledStateColor";
               break;
            case 2:
                cssClass = "InProgressStateColor";
                break;
            case 3:
                cssClass = "CapDefStateColor";
                break;
            case 15:
                cssClass = "CapImpStateColor";
                break;
            case 4:
                cssClass = "CapEffStateColor";
                break;
            case 5:
                cssClass = "CloseStateColor";
                break;
        }

        return cssClass;
        
    }

    public static void main(String args[]){
        String toFind = "A";
        String fromString = "ABC";
        System.out.println("indexOf: " + toFind.indexOf(fromString));
        System.out.println("indexOf: " + fromString.indexOf(toFind));

    }

}
