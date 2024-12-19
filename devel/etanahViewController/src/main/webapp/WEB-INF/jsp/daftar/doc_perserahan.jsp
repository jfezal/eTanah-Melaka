<%-- 
    Document   : doc_perserahan
    Created on : 08-Oct-2009, 16:43:46
    Author     : md.nurfikri
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>JSP Page</title>
    </head>
    <body>
        <form:form beanclass="etanah.view.stripes.PermohonanPendaftaranActionBean">
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>Maklumat Pembetulan</legend>
                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.listDoc}" pagesize="4" cellpadding="0" cellspacing="0" requestURI="pmohonanPendaftaran" id="line">
                            <display:column title="No" sortable="true">${line_rowNum}</display:column>
                            <display:column property="idMohon" title="ID Mohon" href="pmohonanPendaftaran?kemasukanDaftar&stageId=20003&txnCode=20001005"/>
                            <display:column property="nota" title="Nota" sortable="true" sortName="nota"/>
                            <display:column property="sebelum" title="Maklumat Sebelum"/>
                            <display:column property="selepas" title="Maklumat Selepas"/>
                            <display:column property="dimasuk" title="Kerani Kemasukan"/>
                            <display:column property="trhMasuk" title="Tarikh Kemasukan" sortable="true" sortName="trhMasuk"/>
                        </display:table>
                    </div>
                </fieldset>
            </div>            
        </form:form>
    </body>
</html>
