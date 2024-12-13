<%-- 
    Document   : paparan_status_tukar_ganti
    Created on : Jul 7, 2015, 12:59:24 PM
    Author     : faidzal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">
    function cc(a, f) {
        var form = $(f).formSerialize();
//        alert(a);

        var daerah;
        var url = '${pageContext.request.contextPath}/kiosk/tukarganti?filterMukim&daerah=' + a + '&';
        window.location = url + form;
    }

    function seksyenfilter(a, f) {
        var form = $(f).formSerialize();
        var daerah;
        var url = '${pageContext.request.contextPath}/kiosk/tukarganti?filterSeksyen&seksyen=' + a + '&';
        window.location = url + form;
    }






</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.utility.PertanyaanStatusTukarGantiActionBean" name="carianPelan" id="carianPelan">
    <s:messages/>
    <s:errors/>
    <div class ="subtitle">
            <br><br><br><br>
            <div align="center"><h1> SEMAKAN STATUS TUKARGANTI</h1></div>
            
        <div align='right'>
            <a href="${pageContext.request.contextPath}/kiosk/main" title="LAMAN UTAMA">
                <img  src="${pageContext.request.contextPath}/images/Home.png" style="height: 30px; width: 30px" border="0" title="LAMAN UTAMA"></a>
            <a href="${pageContext.request.contextPath}/kiosk/main" title="LAMAN UTAMA">
                <font face="arial">LAMAN UTAMA</font></a>
            &nbsp;&nbsp;&nbsp;
        </div>
        <fieldset class="aras1" style="border-color: white; position:relative ; width: 977px">
            <legend style="font-family:Arial; color: white; font-weight: bold;">STATUS TUKAR GANTI</legend>
            <p>
                <label>ID Hakmilik :</label>
                <font face="verdana" color="black" size='3px'><b>${actionBean.idHakmilikR}</b></font>&nbsp;
            </p>
            <p>
                <label><em style="color: red;">*</em>&nbsp;Status :</label>
                <font face="verdana" color="black" size='3px'><b>${actionBean.msg}
                    <c:if test="${fn:length(actionBean.senaraiHakmilikBerikut) > 0 }">
                        dan disambung ke
                    </c:if>
                </b></font>&nbsp;
            </p>
            <c:if test="${actionBean.idPerserahan ne '-'}">
                <p>
                    <label>ID Permohonan / ID Perserahan :</label>
                    <font face="verdana" color="black" size='3px'><b>${actionBean.idPerserahan}</b></font>&nbsp;
                </p>
            </c:if>
            <c:if test="${fn:length(actionBean.senaraiHakmilikBerikut) > 0 }">
                <p>
                    <label> &nbsp;</label>
                    
                        <display:table class="" name="${actionBean.senaraiHakmilikBerikut}" id="i">
                            <display:column title=""><font face="verdana" color="black" size='3px'><b>${i_rowNum}.</b></font></display:column>
                            <display:column title="" ><font face="verdana" color="black" size='3px'><b><a href="tukarganti?carianHakmilik&idHakmilik=${i.idHakmilik}">${i.idHakmilik}</a></b></font></display:column>
                        </display:table>
                    &nbsp;
                </p>
            </c:if>
            <p>
                <label>&nbsp;</label>
                <s:submit name="kembali" class="btn"  style="btn" value="Kembali" />
            </p>
            <br>
        </fieldset>
        <br><br><br><br><br><br><br><br>
    </div> 
</s:form>