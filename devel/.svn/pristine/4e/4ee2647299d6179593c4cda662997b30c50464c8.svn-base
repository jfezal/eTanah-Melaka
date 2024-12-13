<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/tablecloth.css"/>
<s:form beanclass="etanah.view.dokumen.ViewDocumentAction">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Senarai Capaian</legend>
            <div align="center">
                <display:table name="${actionBean.dokumen.senaraiCapaian}"
                               class="tablecloth" id="row" style="width:80%" cellpadding="1" cellspacing="1">
                    <display:column title="Bil">
                        <div align="right">
                        ${row_rowNum}.
                        </div>
                    </display:column>
                    <display:column title="Sebab Capaian" property="alasan"/>
                    <display:column title="Pengguna">
                        ${fn:toUpperCase(row.infoAudit.dimasukOleh.idPengguna)}
                    </display:column>
                    <display:column title="Tarikh Capaian">
                        <div align="center">
                            <fmt:formatDate value="${row.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy hh:mm:ss aaa"/>
                        </div>
                    </display:column>
                </display:table>
            </div>
            <br/>
            <p>
                <label>&nbsp;</label><s:button class="btn" name="" value="Tutup" onclick="self.close();"/>
            </p>
        </fieldset>
    </div>
</s:form>
