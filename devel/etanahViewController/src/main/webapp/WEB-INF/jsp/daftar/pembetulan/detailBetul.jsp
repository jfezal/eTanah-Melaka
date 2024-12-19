<%-- 
    Document   : detailBetul
    Created on : Apr 9, 2010, 6:01:40 PM
    Author     : mohd.fairul
--%>
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page import="java.util.List;" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>

<s:errors/>
<s:messages/>
<br>
<div class="subtitle">
    <fieldset class="aras1">
        <legend>
            Maklumat Pembetulan
        </legend>
         <div align="center">
        <table class="tablecloth" align="center">
            <tr><th>Perkara</th><th>Malumat Terperinci</th></tr>
             <tr><td style="color:blue">ID Perserahan</td><td>${actionBean.detailUrusan.idPerserahan}</td></tr>
             <tr><td style="color:blue">ID Hakmilik</td><td>${actionBean.detailUrusan.hakmilik.idHakmilik}</td></tr>
             <tr><td style="color:blue">Urusan</td><td>${actionBean.detailUrusan.kodUrusan.nama}</td></tr>
             <tr><td style="color:blue">Catatan (Pembetulan Dibuat)</td><td>${actionBean.detailUrusan.catatan}</td></tr>
         </table>
         </div>
        <br/>
         <br/>
    </fieldset>
         
</div>
<br>

<br>
