<%-- 
    Document   : pertanyaan_notis
    Created on : Nov 9, 2012, 10:49:53 AM
    Author     : ei
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">        
        <title>Pertanyaan Notis</title>        
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
        <link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
        <link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
        <link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
        <link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/popup.css"/>
        <link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
        <link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
        
        <script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>        
        <script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>        
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<!--        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>-->
<!--        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>-->
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
        <script>
            function editNotis(idNotis,idPermohonan){
    <%--alert(idPermohonan);--%>
            var left = (screen.width/2)-(1000/2);
            var top = (screen.height/6)-(150/6);
            var url = '${pageContext.request.contextPath}/utiliti/kemaskini_Notis?editNotisPopup&idNotis='+idNotis+'&idPermohonan='+idPermohonan;
            window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=500, left=" + left + ",top=" + top);
        }
        </script>        
    </head>
<body>
    <s:messages />
    <s:errors />
    <s:useActionBean beanclass="etanah.view.daftar.utiliti.NotisPertanyaanActionBean" var="pertanyaanNotis" />
    <s:form action="/utiliti/pertanyaanNotis">
        <c:if test="${actionBean.idMohonNotis ne null}">
                <fieldset class="aras1">
                    <legend>Maklumat Notis : ${actionBean.permohonan.idPermohonan}</legend><br>            
                    <p>
                        <label for="idPermohonan">Id Permohonan/ID Perserahan :</label>
                        ${actionBean.permohonan.idPermohonan}
                        <s:hidden name="idPermohonan" id="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
                    </p>
                    <p>
                        <label for="kodUrusan">Kod Urusan / Urusan :</label>
                        ${actionBean.permohonan.kodUrusan.kod} / ${actionBean.permohonan.kodUrusan.nama}
                    </p>
                    <p>
                        <label for="penyerah">Nama Penyerah :</label>
                        ${actionBean.permohonan.penyerahNama}
                    </p><br>
                </fieldset>
            </c:if>

            <c:if test="${actionBean.idMohonNotis eq null}">
                <fieldset class="aras1">
                    <legend>Maklumat Notis</legend><br>            
                    <p>
                        <label for="namaNotis">Jenis Notis :</label>
                        ${actionBean.senaraiNotis.kodNotis.nama}
                        <s:hidden name="idPermohonan" id="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
                    </p>                
                    <p>
                        <label for="kodNotis">Kod Notis :</label>
                        ${actionBean.kodNotis}
                    </p>
                    <br>
                </fieldset>                
            </c:if>

            <fieldset class="aras1">            
                <legend>Carian Maklumat Notis</legend><br>
                <div align="center">                
                    <p>
                        <display:table class="tablecloth" name="${actionBean.listNotis}" 
                                       cellpadding="0" cellspacing="0" pagesize="10" 
                                       requestURI="/utiliti/pertanyaanNotis" id="line" >
                            <%--<display:column property="idNotis" title="Id Notis" class="${line_rowNum}"/>--%>
                            <display:column title="Bil">${line_rowNum}</display:column>
                            <c:if test="${actionBean.idMohonNotis eq null}">
                                <display:column property="permohonan.idPermohonan" title="Id Permohonan" class="${line_rowNum}"/>
                            </c:if>
                            <display:column title="Id Hakmilik">
                                ${line.warta.hakmilik.idHakmilik}<br>
                            </display:column>
                            <display:column title="Jenis Notis" class="rowCount">
                                <a class="popup" onclick="popup(${line.idNotis})">${line.kodNotis.nama}</a>
                            </display:column>
                            <display:column title="Nama Penghantar Notis">
                                ${line.penghantarNotis.nama}<br>
                                <c:if test="${line.penghantarNotis.noPengenalan ne null}">No.K/P:(${line.penghantarNotis.noPengenalan})</c:if>
                            </display:column>
                            <display:column property="kodStatusTerima.nama" title="Status Penyampaian" class="${line_rowNum}"/>
                            <display:column title="Tarikh Notis" class="${line_rowNum}" sortable="true">
                                <fmt:formatDate pattern="hh:mm:ss a - dd/MM/yyyy" value="${line.tarikhNotis}"/>
                            </display:column>
                            <display:column title="Tarikh" class="${line_rowNum}">
                                Hantar :   <fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhHantar}"/> <br>
                                Terima :   <fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhTerima}"/>                                
                            </display:column>
                                <display:column title="Tarikh Luput Notis" class="${line_rowNum}">
                                   <fmt:formatDate pattern="dd/MM/yyyy" value="${line.warta.tarikhTamat}"/>
                                </display:column>
                            <%--<display:column title="Hapus">
                                <div align="center">
                                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                        id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeNotis('${line.idNotis}');"/>
                                </div>
                            </display:column>--%>
                            <%--<display:column title="Kemaskini">
                            <div align="center">
                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="editNotis('${line.idNotis}','${line.permohonan.idPermohonan}')"/>
                            </div>
                        </display:column>--%>                          
                    </display:table>
                    </p>
                    <p align="center"><br>
                        <s:hidden name="permohonan.idPermohonan" id="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
                        <s:hidden name="jenisNotis" value="${actionBean.kodNotis}"/>
                        <%--<s:submit name="checkPermohonan" value="Papar Semula" class="btn" />--%>
                    </p>      
                </div><br>
            </fieldset>        
        </s:form>
    </body>
</html>