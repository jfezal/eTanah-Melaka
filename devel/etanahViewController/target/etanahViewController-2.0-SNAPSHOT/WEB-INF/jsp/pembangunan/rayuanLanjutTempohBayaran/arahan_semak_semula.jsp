<%-- 
    Document   : arahan_semak_semula
    Created on : Dec 22, 2009, 4:49:39 PM
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
        <title>Arahan Semak Semula</title>
    </head>
    <body>
        <stripes:form beanclass="etanah.view.stripes.pembangunan.KertasRingkasanActionBean">
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Arahan Semak Semula
                    </legend>
                    <div class="content" align="center">
                        <table>
                            <tr>
                                <td>Arahan Kepada : </td>
                                <td><s:radio name="radio1" value="PPTD"/>Pembantu Tadbir PTD &nbsp;
                                    <s:radio name="radio1" value="PPTG"/>Pembantu Tadbir PTD &nbsp;
                                    <s:radio name="radio1" value="PTD"/>Pentadbir Tanah dan Daerah &nbsp;
                                </td>
                            </tr>
                        </table>
                        <display:table class="tablecloth" name="" pagesize="4" cellpadding="0" cellspacing="0" id="line" size="100">
                            <display:column property="pilih" title="Pilih">
                                <s:radio name="radio2" value="pilihan"/>
                            </display:column>
                            <display:column property="idPengguna" title="ID Pengguna"/>
                            <display:column property="nama" title="Nama"/>
                        </display:table>
                        <br>
                        <table>
                            <tr>
                                <td>Arahan : </td>
                                <td><s:textarea name="arahanSemak" cols="87" rows="8"/>
                                </td>
                            </tr>
                        </table>

                    </div>
                </fieldset>
           </div>
           <table bgcolor="#FF9900" width="100%">
                <tr>
                    <td align="right">
                        <s:button name="kembali" value="Kembali" class="btn"/>&nbsp;
                    </td>
                </tr>
          </table>
        </stripes:form>
    </body>
</html>
