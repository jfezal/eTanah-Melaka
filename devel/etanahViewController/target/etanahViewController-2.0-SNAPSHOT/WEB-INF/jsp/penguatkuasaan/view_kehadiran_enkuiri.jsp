<%-- 
    Document   : view_kehadiran_enkuiri
    Created on : June 8, 2011, 3:50:00 PM
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

<s:form beanclass="etanah.view.penguatkuasaan.EnkuiriKehadiranActionBean">

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Kehadiran
            </legend>

            <div class="content" align="center">
                <display:table name="${actionBean.detailsKehadiran}" class="tablecloth" id="line">
                    <display:column title="Bil">${line_rowNum}</display:column>
                    <display:column property="nama" title="Nama"/>
                    <display:column property="noPengenalan" title="No. Pengenalan"/>
                    <display:column title="Status kehadiran">
                        <c:if test="${line.hadir eq 'Y'}">Hadir&nbsp;</c:if>
                        <c:if test="${line.hadir eq 'T'}">Tidah Hadir&nbsp;</c:if>
                    </display:column>
                </display:table>
            </div>
             <legend>
                        Keputusan Enkuiri
                    </legend>
                    <div align="center">
                        <p>
                            <s:textarea id="ulasan" name="ulasan" cols="80" rows="10" readonly="true" />
                        </p>
                    </div>
        </fieldset>
        <p><label>&nbsp;</label>
            <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
        </p>
        <br>
    </div>
</s:form>



