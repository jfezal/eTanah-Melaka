<%--
    Document   : betulUrusan
    Created on : Feb 23, 2010, 2:05:49 PM
    Author     : wan.fairul
--%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<%-- <script type="text/javascript"
       src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>--%>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/popup.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-impromptu.js"></script>

<style type="text/css">
    td.s{
        font-weight:bold;
        color:blue;
        text-align: right;
    }
</style>

<script type="text/javascript">

    function viewUrusan(id){
        window.open("${pageContext.request.contextPath}/daftar/pembetulan/betul?viewUrusan&idUrusan="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600,scrollbars=yes");
    }
         
    
</script>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form name="form1" beanclass="etanah.view.stripes.nota.pembetulanActionBean">

    <s:messages />
    <s:errors />

    <div class="subtitle">

        <fieldset class="aras1">
            <legend>
                Maklumat Urusan SC/B/N
            </legend>

            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.hakmilikUrusanListByKodserah}" cellpadding="0" cellspacing="0" id="line">
                    <display:column>
                        <s:hidden name="x" class="x${line_rowNum -1}" value="${line.idUrusan}"/>
                    </display:column>
                    <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                    <display:column property="idPerserahan" title="ID Perserahan" class="nama" />
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" />
                    <display:column property="kodUrusan.kod" title="Kod Urusan" />
                    <display:column property="kodUrusan.nama" title="Urusan" />
                    <display:column property="tarikhDaftar" title="Tarikh Daftar" format="{0,date,dd-MM-yyyy}"/>
                    <display:column title="Kemaskini">
                        <p align="center">
                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                 onclick="viewUrusan('${line.idUrusan}');return false;" onmouseover="this.style.cursor='pointer';">
                        </p>
                    </display:column>
                </display:table>
            </div>
            <br/>
        </fieldset>
    </div>

</s:form>