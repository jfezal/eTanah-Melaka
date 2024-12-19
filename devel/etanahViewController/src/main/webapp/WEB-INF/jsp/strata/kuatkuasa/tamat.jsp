<%--
    Document   : tamat
    Created on : Apr 7, 2010, 11:36:04 AM
    Author     : aminah.abdmutalib
--%>
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript">

    function doViewReport(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }

</script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<s:form beanclass="etanah.view.strata.PenguatkuasaanStrataActionBean">
    <s:hidden name="senaraiPermohonan"/>
    <s:messages />
    <s:errors />

    <div class="subtitle" align="center">
        <fieldset class="aras1">
            <legend>Maklumat Urusan</legend>
            <p>
                <%--<display:table  name="${actionBean.senaraiPermohonan}" id="row" class="tablecloth" >--%>
                <display:table  name="${actionBean.permohonan}" id="row" class="tablecloth" >
                    <display:column title="Bil" >${row_rowNum}</display:column>
                    <display:column title="ID Aduan">${actionBean.permohonan.idPermohonan}</display:column>
                    <display:column title="Urusan"> ${actionBean.permohonan.kodUrusan.nama} </display:column>
                </display:table>
            </p><br/><br/>
            <p align="right">
                <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
                <%--<s:submit name="genReport" id="report" value="Jana Terimaan Aduan" class="longbtn"/>&nbsp;--%>
                <s:button name="" value="Papar Aduan" class="longbtn" onclick="doViewReport('${actionBean.idDokumen}');" />
                <s:submit name="setAduan" value="Tambah Aduan" class="btn" />
            </p>
        </fieldset>
    </div>
</s:form>

