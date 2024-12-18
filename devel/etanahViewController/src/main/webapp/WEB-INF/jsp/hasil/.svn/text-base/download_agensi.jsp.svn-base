<%-- 
    Document   : download_agensi
    Created on : Feb 10, 2014, 1:13:47 PM
    Author     : haqqiem
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN"
    "http://www.w3.org/p/html4/loose.dlabel">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript">
    var DELIM = "/";
    function papar(url, format) {
        var t = (document.getElementById("type")).value;
        var p = url.split(DELIM);
        if(t == 'dariAgensi'){
            $('#namaFail').val(p[2]);
        }else{
            $('#namaFail').val(p[3]);
        }
        $('#fmtFail').val(format);
        $('#path').val(url);
        $('#dwnload').click()();
      }
</script>

<s:form beanclass="etanah.view.stripes.hasil.DownloadAgensiActionBean" id="download">
    <s:text name="kodNegeri" id="negeri" style="display:none;" />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Download Fail Agensi</legend>
            <p></p>
            <p>
                <label><em>*</em>Pilihan :</label>
                <s:select name="jenisPilihan" id="type" style="width:250px;">
                    <s:option value="untukAgensi">Untuk Agensi</s:option>
                    <s:option value="dariAgensi">Dari Agensi</s:option>
                </s:select>
            </p>

            <p>
                <label>&nbsp;</label>
                <s:submit name="Step2" class="btn" value="Cari" />
            </p>
        </fieldset>
                <s:text name="filename" id="namaFail" style="display:none;"/>
                <s:text name="formatFile" id="fmtFail" style="display:none;"/>
                <s:text name="downloadLink" id="path" style="display:none;"/>
    </div>

    <div class="subtitle" align="center">
        <fieldset class="aras1">
            <legend>Hasil Carian </legend>
            <display:table class="tablecloth" name="${actionBean.senaraiDokumen}" id="line">
                <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                <display:column title="Agensi">${line.kodAgensiKutipan.kod} - ${line.kodAgensiKutipan.nama}</display:column>
                <display:column title="Tajuk" property="idDokumen.tajuk"/>
                <display:column title="Format" property="idDokumen.format"/>
                <display:column title="Direktori" property="idDokumen.namaFizikal"/>
                <display:column title="Tarikh Masuk">
                    <fmt:formatDate type="date" pattern="dd/MM/yyyy HH:mm:ss a" value="${line.infoAudit.tarikhMasuk}"/>  
                </display:column>
                <display:column title="Muat Turun">
                    <div align="center" >
                        <img alt='Klik Untuk Muat Turun' border='0' src='${pageContext.request.contextPath}/pub/images/icons/download.png' class='rem'
                             id='rem_${line_rowNum}' onmouseover="this.style.cursor = 'pointer';" 
                             onclick="papar('${line.idDokumen.namaFizikal}','${line.idDokumen.format}')" title="Muat Turun">
                    </div>
                </display:column>
            </display:table>
            <s:submit name="Step3" class="btn" value="Download" id="dwnload" style="display:none;"/>
        </fieldset>
    </div>
</s:form>