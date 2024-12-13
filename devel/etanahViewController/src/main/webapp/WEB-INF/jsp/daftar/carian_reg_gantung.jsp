<%-- 
    Document   : carian_reg_gantung
    Created on : Dec 28, 2009, 3:48:01 PM
    Author     : fikri
--%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.daftar.DokumenTambActionBean">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Carian Perserahan
            </legend>
            <p>
                <label>ID Perserahan :</label>
                <s:text name="permohonan.idPermohonan"/>
            </p>            
            <br/>
            <p>
                <label>&nbsp;</label>
                <s:submit name="searchPermohonanGantung" value="Cari" class="btn"/>
            </p>
        </fieldset>
    </div>
    <br>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Senarai Carian
            </legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.permohonanList}" pagesize="10" cellpadding="0" cellspacing="0"
                               requestURI="${pageContext.request.contextPath}/daftar/senarai_tugas" id="line">                    
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column title="ID Perserahan">
                        <a href="${pageContext.request.contextPath}/dokumen/folder?addDocForm&permohonan.idPermohonan=${line.idPermohonan}">${line.idPermohonan}</a>
                    </display:column>
                    <display:column property="kodUrusan.nama" title="Urusan"/>
                    <display:column property="keputusan.nama" title="Keputusan Pendaftar"/>
                </display:table>
            </div>             
        </fieldset>

    </div>
</s:form>

