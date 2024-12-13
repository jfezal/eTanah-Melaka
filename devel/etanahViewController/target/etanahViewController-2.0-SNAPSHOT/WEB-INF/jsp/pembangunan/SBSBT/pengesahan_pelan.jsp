<%-- 
    Document   : pengesahan_pelan
    Created on : Jan 5, 2010, 2:42:16 PM
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
        <title>Pengesahan Pelan</title>
    </head>
    <body>
        <s:form beanclass="etanah.view.stripes.pembangunan.SerahBalikBerimilikSemula">
            
           <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Pelan Tanah
                    </legend>
                    <div class="content" align="center">
                        <display:table class="tablecloth" name="pelanTanah" pagesize="4" cellpadding="0" cellspacing="0" id="line" size="100">
                           <display:column title="No">${line_rowNum}</display:column>
                           <display:column property="dokumen" title="Dokumen"/>
                           <display:column property="nama" title="Nama"/>
                           <display:column property="catatan" title="Catatan"/>
                           <display:column property="idDokumen" title="ID Dokumen"/>
                        </display:table>
                    </div>
                </fieldset>
            </div>
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Pengesahan Pelan Tanah Oleh Pengarah Tanah dan Galian
                    </legend>
                    <div class="content" align="center">
                        <table>
                            <tr>
                                <td>Disahkan oleh : </td>
                                <td><s:select name="jawatan">
                                                <s:option>Pilih..</s:option>
                                                <s:option>Timbalan Pengarah Tanah dan Galian</s:option>
                                                <s:option>Pengarah Tanah dan Galian</s:option>
                                                <%--<s:options-collection collection="" value="" label=""/>--%>
                                                </s:select></td>&nbsp;
                            </tr>
                            <tr>
                                <td>Nama : </td>
                                <td><s:text name="ttPTG"/></td>&nbsp;
                            </tr>
                        </table>
                    </div>
                </fieldset>
           </div>
                            <p align="center">
                                <s:button name="simpan" value="Simpan" class="btn"/>&nbsp;
                            </p>
        </s:form>
    </body>
</html>
