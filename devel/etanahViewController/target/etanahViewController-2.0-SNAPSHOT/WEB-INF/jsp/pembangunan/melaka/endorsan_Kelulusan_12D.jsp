<%-- 
    Document   : endorsan_Kelulusan
    Created on : Jul 15, 2010, 9:38:04 AM
    Author     : NageswaraRao
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252" language="java"%>
<link type="text/css" href="${pageContext.request.contextPath}/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ui.datetimepicker.js"></script>




<s:form beanclass="etanah.view.stripes.pembangunan.EndorsanKelulusan12DActionBean">
    <s:messages/>
    <s:errors/>
    <s:hidden name="kandunganK.kertas.idKertas"/>
    
    <div class="subtitle">
        <fieldset class="aras1">
            <legend> Endorsan Borang 12D</legend>
            <div class="content">
                 <p>
            <label for="nolot">Diendors Oleh :</label>
            <s:text name="idPengguna" size="20" id="idPengguna" class="normal_text"/>
             <p>
            <label for="nolot">Tarikh Endorsan :</label>
            <s:text name="tarikhEndors" size="20"  id="datepicker" class="datepicker" formatPattern="dd/MM/yyyy"/>
             <p>
            <label for="nolot">Keputusan :</label>
            <s:radio name="kpsn" id="radio_" value="EN"/> Endors
             <p>
            <label for="nolot">Catatan :</label>
            <s:textarea name="catatan"  id="catatan"/>
        </p>            
        <br>
        <label for="nolot">&nbsp;</label>
   <s:button name="simpan" id="endorsan" value="Simpan " class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                                             <s:button name="selesai" id="endorsan" value="selesai " class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>

            </div>
        </fieldset>
    </div>
    <%--<br><br>--%>
    <%--<hr/><br>--%>
    
</s:form>