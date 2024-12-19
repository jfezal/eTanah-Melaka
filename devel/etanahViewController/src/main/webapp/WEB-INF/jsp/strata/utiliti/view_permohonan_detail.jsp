<%--
    Document   : view_permohonan_detail
    Created on : May 13, 2011, 03:49:04 PM
    Author     : latifah.iskak
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/popup.css"/>

<s:form action="/strata/status_permohonan" >

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Proses permohonan:
            </legend>

            <div class="content" align="center">

                <display:table name="${actionBean.permohonan.senaraiFasa}" class="tablecloth" id="line">
                    <display:column title="Bil">${line_rowNum}</display:column>

                    <display:column property="infoAudit.tarikhMasuk" title="Tarikh" sortable="true" format="{0,date,dd/MM/yyyy hh:mm aa}"/>

                    <display:column property="infoAudit.dimasukOleh.nama" title="Diproses Oleh"/>


                    <display:column property="idAliran" title="ID Aliran"/>

                    <display:column title="Status">
                        <c:if test="${line.keputusan.kod ne null}">
                            ${line.keputusan.kod} - ${line.keputusan.nama}
                        </c:if>
                    </display:column>
                    <display:column property="ulasan" title="Ulasan"/>

                    <display:column title="Nota/Kertas Minit">
                        <c:forEach items="${actionBean.listNota}" var="n">
                            <c:if test="${n.idAliran eq line.idAliran}">
                                ${n.nota}
                            </c:if>
                        </c:forEach>
                    </display:column>


                </display:table>
            </div>
        </fieldset>
        <p><label>&nbsp;</label>
            <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
        </p>
        <br>
    </div>
</s:form>
