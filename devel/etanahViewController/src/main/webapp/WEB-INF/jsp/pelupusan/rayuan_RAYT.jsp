<%-- 
    Document   : maklumat_permohonan_RAYT
    Created on : Sep 2, 2010, 10:18:44 AM
    Author     : sitifariza.hanim
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<s:form  beanclass="etanah.view.stripes.pelupusan.PermohonanRAYTActionBean">
<s:messages/>
    <s:errors/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Butir-butir Rayuan</legend>
            <p>
                <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PBHL'}">
                <label>Butir-butir Rayuan :</label>
                </c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBHL'}">
                <label>Sebab-sebab Pembatalan :</label>
                </c:if>
                <s:textarea name="permohonan.sebab" cols="60" rows="10" id="catatan"/>
            </p><br/>
                <p>
                    <label>&nbsp;</label>
                <c:if test="${simpan}">
                    <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div');"/>
                </c:if>
                </p>
        </fieldset >
    </div>
</s:form>

