<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>




<s:messages />
<s:errors />



<div class="displaytag">
    <!--s:form action="/dokumen/folder.action" -->
    <s:form beanclass="etanah.view.dokumen.SemakTTActionBean" name="">
        <center>
            <fieldset class="aras1">
                <legend>
                    Hakmilik DHKE Yang Belum Ditanda Tangan
                </legend>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.senaraihakmilikBlmTTKE}"
                                   cellpadding="0" cellspacing="0" id="line1"
                                   requestURI="/dokumen/view/SemakTT"
                                   style="width:50%" pagesize="10">
                        <display:column title="Bil">${line1_rowNum}</display:column>
                        <display:column title="Id Hakmilik" property="idHakmilik"/>
                        <%--<display:column title="Mukim" property="bpm"/>--%>
                    </display:table>

                </div>
            </fieldset>
            <p>
            <fieldset class="aras1">
                <legend>
                    Hakmilik DHDE Yang Belum Ditanda Tangan
                </legend>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.senaraihakmilikBlmTTDE}"
                                   cellpadding="0" cellspacing="0" id="line1"

                                   style="width:50%" pagesize="10">
                        <display:column title="Bil">${line1_rowNum}</display:column>
                        <display:column title="Id Hakmilik" property="idHakmilik"/>
                        <%--<display:column title="Mukim" property="bpm"/>--%>
                    </display:table>
                </div>
            </fieldset>
            <fieldset class="aras1">
                <legend>
                    Hakmilik PELAN B1 Yang Belum Ditanda Tangan
                </legend>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.senaraihakmilikBlmTTB1}"
                                   cellpadding="0" cellspacing="0" id="line1"

                                   style="width:50%" pagesize="10">
                        <display:column title="Bil">${line1_rowNum}</display:column>
                        <display:column title="Id Hakmilik" property="idHakmilik"/>
                        <%--<display:column title="Mukim" property="bpm"/>--%>
                    </display:table>
                </div>
            </fieldset>
            <fieldset class="aras1">
                <legend>
                    Hakmilik PELAN B2 KELUARAN Yang Belum Ditanda Tangan
                </legend>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.senaraihakmilikBlmTTB2KE}"
                                   cellpadding="0" cellspacing="0" id="line1"

                                   style="width:50%" pagesize="10">
                        <display:column title="Bil">${line1_rowNum}</display:column>
                        <display:column title="Id Hakmilik" property="idHakmilik"/>
                        <%--<display:column title="Mukim" property="bpm"/>--%>
                    </display:table>
                </div>
            </fieldset>
            <fieldset class="aras1">
                <legend>
                    Hakmilik PELAN B2 DALAMAN Yang Belum Ditanda Tangan
                </legend>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.senaraihakmilikBlmTTB2DE}"
                                   cellpadding="0" cellspacing="0" id="line1"

                                   style="width:50%" pagesize="10">
                        <display:column title="Bil">${line1_rowNum}</display:column>
                        <display:column title="Id Hakmilik" property="idHakmilik"/>
                        <%--<display:column title="Mukim" property="bpm"/>--%>
                    </display:table>
                </div>
            </fieldset>
        </center>
    </s:form>
</div>
