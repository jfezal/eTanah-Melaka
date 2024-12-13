<%-- 
    Document   : status
    Created on : 15-Sep-2009, 10:49:13
    Author     : md.nurfikri
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<style type="text/css">
    .messages { background-color: pink; }
</style>
<s:form beanclass="etanah.view.stripes.StatusActionBean">
    <s:messages/>
    <s:errors/>
    <s:hidden name="fasaPermohonan.idFasa"/>
    <s:hidden name="stageId"/>
<%--    <s:hidden name="idPermohonan"/>
    <s:hidden name="selectedMainTab" id="selectedMainTab"/>
    <s:hidden name="pageId"/>
    <s:hidden name="txnCode"/>
    <s:hidden name="stageId"/>
    <s:hidden name="mainId" value="1"/>
    <s:hidden name="selectedTab" id="selectedTab"/>--%>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Keputusan
            </legend>
            <p>
                <label>Keputusan :</label>
                <c:forEach items="${actionBean.tabBean.outcome}" var="outcome">
                    <s:radio name="fasaPermohonan.keputusan" value="${outcome.value}"/>Syor ${outcome.label} &nbsp;
                </c:forEach>               
            </p>
            <p>
                <label for="ulasan">Ulasan :</label>
                <s:textarea name="fasaPermohonan.ulasan" cols="100" rows="10" value="${actionBean.fasaPermohonan.ulasan}"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:button name="save" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'status_div')"/>
            </p>
        </fieldset>
    </div>
</s:form>
