<%-- 
    Document   : menentukan_hakmilik_umpukan
    Created on : Jan 5, 2010, 10:18:52 AM
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
        <title>Menentukan Jenis Hakmilik dan Umpukan</title>
    </head>
    <body>
        <stripes:form beanclass="etanah.view.stripes.pembangunan.PermohonanPembangunanActionBean">

            <div class="subtitle">
                <fieldset class ="aras1">
                    <legend>Jenis Hakmilik dan Umpukan</legend>
                    <div class="content" align="center">
                        <display:table  class="tablecloth" name="${actionBean.lpp}"
                                        cellpadding="0" cellspacing="0" requestURI="/pembangunan/hakmilikUmpukan" id="line">
                            <display:column title="No" sortable="true">${line_rowNum}
                        <s:hidden name="x" class="x${line_rowNum -1}" value="${line.hakmilikPermohonan.id}"/></display:column>
                            <display:column title="Hakmilik">
                                ${line.hakmilikPermohonan.hakmilik.idHakmilik}
                            </display:column>
                            <display:column title="Luas Plot">
                                ${line.luas}
                            </display:column>
                            <display:column title="Unit Luas">
                                ${line.kodUnitLuas.nama}
                            </display:column>
                        </display:table>
                    </div>
                </fieldset>
            </div>
            <%--   <p align="center">
                   <s:submit name="simpan" value="Simpan" class="btn"/> &nbsp;
               </p>--%>
        </stripes:form>
    </body>
</html>
