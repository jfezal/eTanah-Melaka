<%-- 
    Document   : carian_pelan
    Created on : Oct 8, 2013, 6:14:01 PM
    Author     : afham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>

<s:form beanclass="etanah.view.utility.CarianPelanActionBean" name="carianPelan" id="carianPelan">
    <s:messages/>
    <s:errors/>
    <div class ="subtitle">
        <div class="content">
            <fieldset class ="aras1">
                <legend>Carian Permohonan</legend>
                <p><label><font color="red">*</font>ID Permohonan :</label>
                        <s:text id="idPermohonan" name="idPermohonan"></s:text>
                    </p>
                    <p align="center"><s:submit name="carianPelan" value="Cari" class="btn"/>&nbsp;<s:button name = "reset" class="btn" value ="Isi Semula" onclick= "clearText('carianPelan')" /></p>

            </fieldset>
        </div>

        <div class="content">
            <fieldset class="aras1">
                <legend>
                    Senarai Permohonan
                </legend>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.listPelanGIS}"
                                   id="line" style="width:95%"
                                   requestURI="${pageContext.request.contextPath}/utiliti/carian_pelan">                    
                        <display:column title="Bil" class="bil${line_rowNum}" style="${bcolor}">      
                            ${line_rowNum}
                        </display:column>
                        <display:column title="ID Permohonan">
                            <c:if test="${line.pelanGISPK.permohonan.idPermohonan ne null}">
                                ${line.pelanGISPK.permohonan.idPermohonan}
                            </c:if>
                        </display:column>
                        <display:column title="Urusan">                        
                            ${line.pelanGISPK.permohonan.kodUrusan.nama}
                        </display:column>
                        <display:column title="Jenis Pelan">                        
                            ${line.jenisPelan}                    
                        </display:column>
                        <display:column title="Papar">
                            <c:if test="${line.jenisPelan eq 'B1'}">
                                <a href="${pageContext.request.contextPath}/reportGeneratorServlet?reportName=${actionBean.idDokumenB1}&report_p_id_mohon=${line.pelanGISPK.permohonan.idPermohonan}&report_p_no_lot=${line.pelanGISPK.noLot}" target="parent" onmouseover="this.style.cursor='pointer';">Papar Pelan B1</a>
                                <c:set var="kodPelan" value="1"/>
                            </c:if>
                            <c:if test="${line.jenisPelan eq 'B2'}">
                                <a href="${pageContext.request.contextPath}/reportGeneratorServlet?reportName=${actionBean.idDokumenB2}&report_p_id_mohon=${line.pelanGISPK.permohonan.idPermohonan}&report_p_no_lot=${line.pelanGISPK.noLot}" target="parent" onmouseover="this.style.cursor='pointer';">Papar Pelan B2</a>
                                <c:set var="kodPelan" value="3"/>
                            </c:if>
                            
                            </display:column>
                        </display:table>
                </div>
            </fieldset>
        </div>
    </div> 
</s:form>