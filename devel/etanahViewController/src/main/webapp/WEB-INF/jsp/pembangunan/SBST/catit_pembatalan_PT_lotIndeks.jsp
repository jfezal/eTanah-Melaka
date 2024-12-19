<%-- 
    Document   : catit_pembatalan_PT_lotIndeks
    Created on : Jan 5, 2010, 11:24:23 AM
    Author     : nursyazwani
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page import="java.util.List;" %>
<%@ taglib prefix="stripes"
           uri="http://stripes.sourceforge.net/stripes-dynattr.tld"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Mencatitkan Pembatalan Dalam PT/Lot Indeks</title>
    </head>
    <body>
        <stripes:form beanclass="etanah.view.stripes.pembangunan.BatalPTLotIndeksActionBean">
         
           <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                       Catatan Pembatalan Dalam PT/Lot Indeks
                    </legend>
                    <div class="content" align="center">
                        <table border="0" width="80%">
                            <tr>
                                <td><p><label><font color="black">Catatan Pembatalan : </font></label>
                                    <s:textarea cols="50" rows="8" name="catatanPembatalan"/></p></td>
                            </tr>
                        </table>
                    </div>
                </fieldset>
           </div>
            <p align="center">
                <s:button name="charting" value="Charting" class="btn"/>&nbsp;
                <s:button name="simpan" value="Simpan" class="btn"/>&nbsp;
            </p>
        </stripes:form>
    </body>
</html>
