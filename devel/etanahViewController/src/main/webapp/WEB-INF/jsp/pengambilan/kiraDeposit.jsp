<%-- 
    Document   : kiraDeposit
    Created on : Feb 23, 2011, 10:56:01 AM
    Author     : massita
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN" "http://www.w3.org/p/html4/loose.dlabel">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloths.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.pengambilan.KiraDepositActionBean">
    <s:errors/>
    <s:messages/>
    <div>
        <div class="subtitle">
            <br />
            <fieldset class="aras1">
                <legend>Pengiraan Deposit</legend>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.senaraiTuntutanKos}" cellpadding="0" cellspacing="0" id="tbl1">
                        <display:column title="Deposit" style="vertical-align:baseactionBean">${tbl1.item}</display:column>
                        <display:column title="Amaun tuntut (RM)" style="text-align:left">
                            <%--<c:out value="${actionBean.hakmilikAmaunList[tbl1_rowNum-1]}"/>--%>
                            ${tbl1.amaunTuntutan}
                        </display:column>
                        <display:footer>
                    </display:footer>
                    </display:table>
                </div>
                <br/>
            </fieldset>
        </div>
            <br/><br/>
    </div>
</s:form>

