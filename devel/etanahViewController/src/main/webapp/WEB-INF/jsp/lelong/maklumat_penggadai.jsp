<%-- 
    Document   : maklumat_penggadai
    Created on : Mar 10, 2010, 4:40:31 PM
    Author     : mazurahayati
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<s:form action="">
<div class="subtitle">
        <fieldset class="aras1">
  <legend>Senarai Tuan Tanah</legend>
            <div class="content" align="center">

                    <display:table class="tablecloth" name="" cellpadding="0" cellspacing="0" id="line">
<%--                    <display:column title="">
                        <s:checkbox name="" id="" class="checkbox"/>
                    </display:column>--%>
                    <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                    <display:column property="pihak.nama" title="Nama" class="nama"/>
                    <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                    <display:column title="ID Hakmilik" />
                    <display:column title="Bahagian yang dimiliki" >${line.syerPembilang}/${line.syerPenyebut}</display:column>
                    <%--<display:column title="Tarikh Pemilikan Tanah">
                        <fmt:formatDate pattern="dd/MM/yyyy" value="${line.infoAudit.tarikhMasuk}"/>
                    </display:column>--%>
                    <display:column property="jenis.nama" title="Jenis Pihak"/>
                </display:table>
                        <p>
                            <%--<s:button class="btn" value="Carian Pihak" name="searchExisting" id="searchExisting" onclick="popupExisting();"/>&nbsp;--%>
                            <s:button class="btn" value="Pilih" name="pilih" id="new" onclick="addNew();"/>&nbsp;
                        </p>
                         </div>
       </fieldset>
            </div>
 </s:form>