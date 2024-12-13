<%-- 
    Document   : senarai_mohon_pihak
    Created on : 03-Nov-2009, 22:09:31
    Author     : md.nurfikri
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<div class="subtitle">
    <s:form beanclass="etanah.view.stripes.common.PermohonanPihakActionBean">
        <fieldset class="aras1">
            <legend>Senarai Permohonan Pihak Kepentingan</legend>

            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.mohonPihakList}" cellpadding="0" cellspacing="0" id="lineMP">
                    <display:column title="Bil" sortable="true">${lineMP_rowNum}</display:column>
                    <display:column property="pihak.nama" title="Nama"/>
                    <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                    <display:column property="syer" title="Syer" />
                    <display:column title="Hapus">
                        <img alt='Click to Delete' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                             id='rem${lineMP_rowNum}' onclick="remove(this.id,'line')">
                         </display:column>
                </display:table>
            </div>
            <p>
                <label>&nbsp;</label>
                <s:button class="btn" value="Carian Pihak" name="searchExisting" id="searchExisting"/>&nbsp;
                <s:button class="btn" value="Tambah Baru" name="addNew" id="addNew"/>
            </p>
        </fieldset>
    </s:form>
</div>
