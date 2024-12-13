<%-- 
    Document   : paparan_senarai_pb
    Created on : Jun 30, 2010, 11:17:56 AM
    Author     : khairil
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<s:form  beanclass="etanah.view.stripes.common.MaklumatPermohonanActionBean">
<div class="subtitle">
    <fieldset class="aras1">
        <legend>Senarai Pihak Berkepentingan</legend>
        <div class="content" align="center" id="listpihak">
            <display:table class="tablecloth" style="width:90%;" name="${actionBean.senaraiMohonPihakTerlibat}" cellpadding="0" cellspacing="0" id="linemohonpihak">
                <display:column title="Bil" sortable="true">${linemohonpihak_rowNum}</display:column>
                <display:column property="pihak.nama" title="Nama" style="text-transform:uppercase;"/>
                <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                <display:column property="jenis.nama" title="Jenis PB" />
                <display:column title="Bahagian yang diterima">
                             ${linemohonpihak.syerPembilang}
                             /
                             ${linemohonpihak.syerPenyebut}
                            
                </display:column>
                <display:column title="ID Pihak" property="pihak.idPihak" class="hidden" headerClass="hidden" media="html"/>

            </display:table>
        </div>
    </fieldset>
</div>
<p align="center">
    <s:button name="tutup" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
</p>
</s:form>