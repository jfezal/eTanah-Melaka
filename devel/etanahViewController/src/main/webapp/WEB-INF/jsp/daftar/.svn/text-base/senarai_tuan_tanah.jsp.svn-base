<%-- 
    Document   : senarai_tuan_tanah
    Created on : Aug 12, 2010, 4:22:53 PM
    Author     : fikri
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript">
    function doSave(event, form) {
        doBlockUI();
        var q = $(form).formSerialize();        
        var url = form.action + '?' + event;
        $.ajax({
            type:"POST",
            url : url,
            dataType : 'html',
            data : q,
            error : function (xhr) {},
            success : function (data) {
                $('#page_div',opener.document).html(data);
                self.close();
            }
        });        
    }

    function doBlockUI () {
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top:  ($(window).height() - 50) /2 + 'px',
                left: ($(window).width() - 50) /2 + 'px',
                width: '50px'
            }
        });
    }

    function doUnBlockUI (){
        $.unblockUI();
    }
</script>

<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
<div class="subtitle displaytag">
    <s:errors/>
    <s:form beanclass="etanah.view.stripes.common.PemohonActionBean">
        <s:hidden name="idHakmilik"/>

        <fieldset class="aras1">
            <legend>Senarai Pemilik</legend>
            <div align="center">
                 <c:if test="${!empty moreThanOneHakmilik}">
                    <p>
                        <label></label>&nbsp;
                        <font color="red" size="2">
                            <input type="checkbox" name="copyToAll" value="1" id="copyToAll"/>
                            <b><em>Sila klik jika sama untuk semua hakmilik.</em></b>
                        </font>
                    </p>
                </c:if>
                <p align="center">
                    <label></label>&nbsp;
                    <c:choose>
                        <c:when test="${fn:length(actionBean.senaraiPemohonBerangkai) > 0}">
                            <display:table class="tablecloth" name="${actionBean.senaraiPemohonBerangkai}" cellpadding="0" cellspacing="0" id="line1"
                                           requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">
                                <display:column><s:checkbox name="rb" value="${line1.pihak.idPihak}"/></display:column>
                                <display:column title="Bil" sortable="true">${line1_rowNum}</display:column>
                                <c:choose>
                                    <c:when test="${empty line1.nama}">
                                        <display:column property="pihak.nama" title="Nama" class="nama"/>
                                    </c:when>
                                    <c:otherwise>
                                        <display:column property="nama" title="Nama" class="nama"/>
                                    </c:otherwise>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${empty line1.noPengenalan}">
                                        <display:column property="pihak.noPengenalan" title="Nama" class="nama"/>
                                    </c:when>
                                    <c:otherwise>
                                        <display:column property="noPengenalan" title="Nama" class="nama"/>
                                    </c:otherwise>
                                </c:choose>                                
                                <display:column title="Bahagian yang dimiliki" >${line1.syerPembilang}/${line1.syerPenyebut}</display:column>
                                <%--display:column title="Tarikh Pemilikan Tanah"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.infoAudit.tarikhMasuk}"/></display:column--%>
                                <display:column property="jenis.nama" title="Jenis Pihak"/>
                            </display:table>
                        </c:when>
                        <c:when test="${fn:length(actionBean.senaraiPihakTerlibat) > 0}">
                             <display:table class="tablecloth" name="${actionBean.senaraiPihakTerlibat}" cellpadding="0" cellspacing="0" id="line2"
                                           requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">
                                <display:column><s:radio name="rb" value="${line2.idPihak}"/></display:column>
                                <display:column title="Bil" sortable="true">${line2_rowNum}</display:column>
                                <display:column property="nama" title="Nama" class="nama"/>
                                <display:column property="noPengenalan" title="Nombor Pengenalan" />                               
                            </display:table>
                        </c:when>
                        <c:otherwise>
                            <display:table class="tablecloth" name="${actionBean.senaraiTuanTanah}" cellpadding="0" cellspacing="0" id="line"
                                           requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">
                                <display:column><s:checkbox name="rb" value="${line.idHakmilikPihakBerkepentingan}"/></display:column>
                                <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                                <display:column property="pihak.nama" title="Nama" class="nama"/>
                                <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                                <display:column title="Bahagian yang dimiliki" >${line.syerPembilang}/${line.syerPenyebut}</display:column>
                                <%--display:column title="Tarikh Pemilikan Tanah"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.infoAudit.tarikhMasuk}"/></display:column--%>
                                <display:column property="jenis.nama" title="Jenis Pihak"/>
                            </display:table>
                        </c:otherwise>
                    </c:choose>
                </p>
            </div>
            <br/>

            <p>
                <label>&nbsp;</label>                
                <c:if test="${fn:length(actionBean.senaraiTuanTanah) > 0 }">
                    <s:button class="btn" name="saveTuanTanahAsMohonPihak" value="Simpan" onclick="doSave(this.name, this.form);" onmouseover="this.style.cursor='pointer';"/>&nbsp;
                </c:if>
                <c:if test="${fn:length(actionBean.senaraiPemohonBerangkai) > 0}">
                    <s:button class="btn" name="saveMohonPihakAsMohonPihak" value="Simpan" onclick="doSave(this.name, this.form);" onmouseover="this.style.cursor='pointer';"/>&nbsp;
                </c:if>
                 <c:if test="${fn:length(actionBean.senaraiPihakTerlibat) > 0}">
                    <s:button class="btn" name="savePihakAsMohonPihak" value="Simpan" onclick="doSave(this.name, this.form);" onmouseover="this.style.cursor='pointer';"/>&nbsp;
                </c:if>

                <s:button class="btn" name="close" value="Tutup" onclick="self.close();" onmouseover="this.style.cursor='pointer';"/>
            </p>


        </fieldset>
        <br>
    </s:form>
</div>


