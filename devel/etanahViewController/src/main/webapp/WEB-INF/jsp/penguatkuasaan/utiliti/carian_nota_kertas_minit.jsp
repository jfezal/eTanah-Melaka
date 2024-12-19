<%-- 
    Document   : carian_nota_kertas_minit
    Created on : Apr 16, 2014, 3:21:44 PM
    Author     : MohammadHafifi
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script language="javascript" type="text/javascript">
    function validateForm2(){

        if(document.getElementById("idPermohonanCarian").value == ""){
            alert("Sila masukkan Id Permohonan yang dikehendaki");
            $('#idPermohonanCarian').focus();
            return false;
        }

        return true;
    }
    function doViewReport(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
    }
</script>
<s:form beanclass="etanah.view.penguatkuasaan.utiliti.UtilitiCetakanNotaKertasMinitActionBean">
    <s:messages/>
    <s:errors/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Carian Nota/Kertas Minit</legend>
            <p><label for="idPermohonan"><em>*</em>Id Permohonan:</label>
                <input type="text" name="idPermohonan" id="idPermohonanCarian" onkeyup="this.value=this.value.toUpperCase();"/>
                <s:submit name="searchNoSerahan" value="Cari" class="btn" onclick="return validateForm2()"/>
            </p>
        </fieldset>
    </div>
    <c:if test="${fn:length(actionBean.listNota) gt 0}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Nota/Kertas Minit</legend>
                <div class="content" align="center">
                    Id Permohonan: ${actionBean.idPermohonan}
                </div>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.listNota}" cellpadding="0" cellspacing="0" id="line">
                        <display:column title="No" sortable="true" style="vertical-align:top;">${line_rowNum}</display:column >
                        <display:column title="Tarikh" style="width:90px;vertical-align:top;">
                            <fmt:formatDate value="${line.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy hh:mm:ss"/>
                        </display:column>
                        <display:column title="Nama" property="infoAudit.dimasukOleh.nama" style="vertical-align:top;"/>
                        <display:column title="Jawatan" property="infoAudit.dimasukOleh.jawatan" style="vertical-align:top;"/>
                        <display:column title="Nota" style="width:80px;vertical-align:top;">
                            <textarea name="nota" style="background: transparent; border: 0px" cols="80" rows="5" readonly="readonly" id="text">${line.nota}</textarea>
                        </display:column>
                    </display:table>
                </div>
                <div class="content" align="center">
                    <s:button name="viewReport" value="Papar Dokumen" class="longbtn" onclick="doViewReport('${actionBean.idDokumen}');"/>&nbsp;
                </div>
            </fieldset>
        </div>
    </c:if>
</s:form>