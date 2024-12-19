<%-- 
    Document   : maklumat_pemilik
    Created on : Jan 20, 2010, 3:40:36 PM
    Author     : farah.shafilla
--%>
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.penguatkuasaan.PenguatkuasaanActionBean">
<div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Pemilik Tanah
            </legend>
            <div class="content" align="center">
            </div>
            <div class="content" align="center">
                <display:table class="tablecloth" name="" cellpadding="0" cellspacing="0" id="lineMP">
                    <display:column title="Bil" sortable="true">{lineMP_rowNum}</display:column>
                    <display:column title="Nama Pemilik Tanah"/>
                    <display:column title="Jenis Pengenalan" />
                    <display:column title="Nombor Pengenalan" />
                    <display:column title="Alamat" />
                    <display:column title="Nombor Telefon"/>
                </display:table>
            </div>
       </fieldset>
    </div>
    <br>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Pihak Berkepentingan
            </legend>
            <div class="content" align="center">
            </div>
            <div class="content" align="center">
                <display:table class="tablecloth" name="" cellpadding="0" cellspacing="0" id="lineMP">
                    <display:column title="Bil" sortable="true">{lineMP_rowNum}</display:column>
                    <display:column title="Nama Pihak Berkepentingan"/>
                    <display:column title="Jenis Pengenalan" />
                    <display:column title="Nombor Pengenalan" />
                    <display:column title="Alamat" />
                    <display:column title="Nombor Telefon"/>
                </display:table>
            </div>
       </fieldset>
    </div>
</s:form>