<%-- 
    Document   : papar_pihak_waris
    Created on : Jan 1, 2015, 12:29:40 AM
    Author     : zairul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>


<s:form beanclass="etanah.view.daftar.PihakKepentinganAction" name="form1">
 <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                <c:if test = "${actionBean.hakmilikPihak.jenis.kod eq 'PA'}" >Pemegang Amanah Bagi Pihak</c:if>                
                <c:if test = "${actionBean.hakmilikPihak.jenis.kod eq 'PP'}" >Pentadbir Bagi Pihak</c:if>  
            </legend>
            <br>
            <div align="center">                
                    <display:table class="tablecloth" name="${actionBean.senaraiPihakWaris}"
                                   cellpadding="0" cellspacing="0" id="line">
                        <display:column title="Bil"><div align="right">${line_rowNum}</div></display:column>
                        <display:column title="Nama">
                            ${line.nama}
                        </display:column>
                        <display:column title="No Pengenalan">
                            ${line.noPengenalan}
                        </display:column>
                        <display:column title="Syer" style="width:5%;">
                            <div align="center">
                                <c:if test = "${line.syerPembilang ne null}" >
                                    ${line.syerPembilang}/${line.syerPenyebut}
                                </c:if>
                            </div>
                        </display:column>
                    </display:table>                
            </div>
            <br/>
        </fieldset>
        <br>
        <div  align="center"><p><s:button name="tutup" value="Tutup" class="btn" onclick="javascript:window.close();"/></p></div>
    </div>
</s:form>
