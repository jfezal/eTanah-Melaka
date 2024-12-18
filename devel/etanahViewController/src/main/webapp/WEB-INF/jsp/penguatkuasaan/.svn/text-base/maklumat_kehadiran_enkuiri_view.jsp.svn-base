<%-- 
    Document   : maklumat_kehadiran_enkuiri_view
    Created on : June 8, 2011, 3:50:00 PM
    Author     : latifah.iskak
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript">
  
    function viewDetail(id){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_enkuiri?viewKehadiranDetail&idEnkuiri='+id;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
    }

    
</script>

<s:form beanclass="etanah.view.penguatkuasaan.EnkuiriKehadiranActionBean">

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Kehadiran
            </legend>


            <c:if test="${not empty actionBean.enkuiri.idEnkuiri}">
                <div class="content">
                    <p>
                        <label>Tarikh :</label>
                        ${actionBean.tarikhMula}&nbsp;
                    </p>
                    <p>
                        <label>Masa :</label>
                        ${actionBean.jam}:${actionBean.minit} ${actionBean.ampm} &nbsp;

                    <p>
                        <label>Hari :</label>
                        <fmt:formatDate pattern="EEEE" value="${actionBean.enkuiri.tarikhMula}"/>&nbsp;
                    </p>
                    <p>
                        <label>Tempat :</label>
                        ${actionBean.tempat} &nbsp;
                    </p>

                </div>

                <br>

                <fieldset class="aras1">
                    <legend>
                        Pihak Terlibat
                    </legend>
                    <div id="kehadiranEnkuiriDiv">
                        <div class="content" align="center">
                            <display:table class="tablecloth" name="${actionBean.senaraiPihakHadir}" cellpadding="0" cellspacing="0" id="line">
                                <display:column title="Bil">${line_rowNum}</display:column>
                                <display:column title="Nama" property="nama" style="text-transform: uppercase"></display:column>
                                <display:column title="No.Pengenalan" property="noPengenalan"></display:column>
                                <display:column title="Status Kehadiran">
                                    <c:if test="${line.hadir eq 'T'}">Tidak Hadir</c:if>
                                    <c:if test="${line.hadir eq 'Y'}">Hadir</c:if>
                                </display:column>

                            </display:table>
                            <p>&nbsp;</p>
                        </div>
                    </div>

                    <legend>
                        Keputusan Enkuiri
                    </legend>
                    <div align="center">
                        <p>
                            <s:textarea id="ulasan" name="ulasan" cols="80" rows="10" readonly="true"/>
                        </p>
                    </div>
                </fieldset>




                <c:if test="${actionBean.stageId eq 'semak_laporan2' || actionBean.stageId eq 'semak2_laporan2'}">
                    <div class="subtitle">
                        <fieldset class="aras1">
                            <legend>
                                Sejarah Enkuiri
                            </legend>
                            <div class="content" align="center">
                                <display:table class="tablecloth" name="${actionBean.senaraiEnkuiri}" cellpadding="0" cellspacing="0" id="line1">
                                    <display:column title="Bil">${line1_rowNum}</display:column>
                                    <display:column title="Tarikh">
                                        <a class="popup" onclick="viewDetail(${line1.idEnkuiri})"><fmt:formatDate pattern="dd/MM/yyyy" value="${line1.tarikhMula}"/></a>&nbsp;
                                    </display:column>
                                    <display:column title="Masa">
                                        <fmt:formatDate pattern="hh:mm aaa" value="${line1.tarikhMula}"/>&nbsp;
                                    </display:column>
                                    <display:column title="Hari"></display:column>
                                    <display:column title="Tempat" property="tempat"></display:column>
                                </display:table>
                                <p>&nbsp;</p>
                            </div>
                        </fieldset>
                    </div>
                    <legend>
                        Keputusan Enkuiri
                    </legend>
                    <div align="center">
                        <p>
                            <s:textarea id="ulasan" name="ulasan" cols="80" rows="10" readonly="true" />
                        </p>
                    </div>
                </c:if>

            </c:if>
        </fieldset>
    </div>
</s:form>
