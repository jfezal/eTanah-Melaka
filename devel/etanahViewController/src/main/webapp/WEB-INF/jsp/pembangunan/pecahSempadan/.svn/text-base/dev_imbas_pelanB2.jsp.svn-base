<%-- 
    Document   : imbas_pelanB2
    Created on : Jan 4, 2010, 3:51:42 PM
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
        <title>Mengimbas Pelan B2</title>
    </head>
    <body>
        <stripes:form beanclass="etanah.view.stripes.pembangunan.PecahSempadanActionBean">
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Imbas Pelan B2
                    </legend>
                    <div class="content" align="center">
                       <table>
                            <tr>
                                <td align="center">
                                    <s:submit name="imbas" value="Imbas" class="btn"/> &nbsp;
                                    <s:submit name="hapus" value="Hapus" class="btn"/> &nbsp;
                                </td>
                            </tr>
                        </table>
                        <br>
                        <display:table class="tablecloth" name="pelanB2" pagesize="4" cellpadding="0" cellspacing="0" id="line" size="100">
                           <display:column title="Pilih" sortable="true"><div align="center"><s:checkbox name="chk1" value="${line_rowNum}" /></div></display:column>
                           <display:column title="No">${line_rowNum}</display:column>
                           <display:column property="dokumen" title="Dokumen"/>
                           <display:column property="nama" title="Nama"/>
                           <display:column property="catatan" title="Catatan"/>
                           <display:column property="imbas" title="Imbas"/>
                           <display:column property="idDokumen" title="ID Dokumen"/>
                        </display:table>
                    </div>
                </fieldset>
            </div>
            <p align="center">
                  <s:submit name="simpan" value="Simpan" class="btn"/> 
            </p>
        </stripes:form>
    </body>
</html>
