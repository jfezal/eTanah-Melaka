<%-- 
    Document   : Pengiraan_Baki_Deposit
    Created on : Feb 22, 2011, 10:05:38 AM
    Author     : massita
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>

<s:form beanclass="etanah.view.stripes.pengambilan.PengiraanBakiDepositActionBean">
<s:messages/>
<s:errors/>
   <c:if test="${form1}">
    <div class="subtitle" >
        <br/><br/>
        <fieldset class="aras1">
            <legend>Maklumat Pengiraan Deposit</legend>
            <br/><br/>
            <s:label for="deposit">Deposit :</s:label>
            <s:text name="deposit" formatPattern="#,##0.00" readonly="true"/>
            <br/>
            <s:label for="pampasan">Pampasan :</s:label>
            <s:text name="pampasan" formatPattern="#,##0.00" readonly="true"/>
            <br/>
            <c:set value="${actionBean.deposit - actionBean.pampasan}" var="A"/>
            <s:label for="baki">Baki :</s:label>
            <s:text name="baki" value="${A}" formatPattern="#,##0.00" readonly="true"/>
           <br/><br/>
        </fieldset>
            <c:if test="${A > 0}">
                <p align="center">
                <%--<s:button name="hasil" value="Integrate with Hasil" class="btn" size="40"/>--%>
                <s:button name="hasil" id="save" value="Integrate with Hasil" size="30" class="longbtn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </p>
            </c:if>  
    </div>
  </c:if>
</s:form>