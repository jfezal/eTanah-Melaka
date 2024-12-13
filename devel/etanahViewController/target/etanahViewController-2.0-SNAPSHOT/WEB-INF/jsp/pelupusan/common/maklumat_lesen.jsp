<%-- 
    Document   : maklumat_lesen
    Created on : Jun 22, 2011, 7:08:53 PM
    Author     : Akmal
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.pelupusan.PerihalTanahActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Lesen</legend><br/>
              <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.permit}" cellpadding="0" cellspacing="0"
                                   requestURI="${pageContext.request.contextPath}/pelupusan/maklumat_lesen_common" id="line">
                        <display:column title="ID Permohonan" sortable="true" style="vertical-align:baseline">${actionBean.idPermohonan}</display:column>
                        <display:column title="No. Lesen" style="vertical-align:baseline">${actionBean.permit.noPermit}</display:column>
                        <display:column title="Tarikh Dikeluarkan" style="vertical-align:baseline"><fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.permitSahLaku.tarikhPermit}"/></display:column>                      
                        <display:column title="Tarikh Mula" style="vertical-align:baseline"><fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.permitSahLaku.tarikhPermitMula}"/></display:column>
                        <display:column title="Tarikh Tamat" style="vertical-align:baseline"><fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.permitSahLaku.tarikhPermitTamat}"/></display:column>
                        <display:column title="Tujuan" sortable="true" style="vertical-align:baseline">${actionBean.permitItem.kodItemPermit.nama}</display:column>
                    </display:table>
                </div>    
            <center>
            <s:button name="tutup" class="btn" value="Tutup" onclick="self.close();"/>
            </center>
        </fieldset>
    </div>
</s:form>