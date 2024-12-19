<%-- 
    Document   : pertanyaan_status_permohonan
    Created on : Jul 7, 2015, 1:54:34 PM
    Author     : faidzal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script language="javascript">
    function checking(){
        var id = document.getElementById('idmohon');
        if(id.value == ''){
            alert('Sila masukkan ID Permohonan/ ID Perserahan');
            $('#idmohon').focus();
            return false;
        }else
            return true;
    }
</script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.utility.PertanyaanStatusPermohonanActionBean" name="carianPelan" id="carianPelan">
    <s:messages/>
    <s:errors/>
    <div class ="subtitle">
        <br><br><br><br><br><br><br>
            <div align="center"><h1> SEMAKAN STATUS PERMOHONAN</h1></div>
        <div align='right'>
            <a href="${pageContext.request.contextPath}/kiosk/main" title="LAMAN UTAMA">
                <img  src="${pageContext.request.contextPath}/images/Home.png" style="height: 30px; width: 30px" border="0" title="LAMAN UTAMA"></a>
            <a href="${pageContext.request.contextPath}/kiosk/main" title="LAMAN UTAMA">
                <font face="arial">LAMAN UTAMA</font></a>
            &nbsp;&nbsp;&nbsp;
        </div>
        <fieldset class="aras1" style="border-color: white; position:relative ; width: 977px">
            <legend style="font-family:Arial; color: white; font-weight: bold;">STATUS PERMOHONAN</legend>
            <p>
                
                <label> <em style="color: red">*</em> ID Permohonan / ID Perserahan :</label>
                <s:text id="idmohon" style="padding: 0;
                        height: 20px;
                        position: relative;
                        left: 0;
                        outline: none;
                        border: 1px solid #cdcdcd;
                        border-color: rgba(0,0,0,.15);
                        background-color: #ccc;
                        font-size: 17px;" name="idPermohonan"></s:text><em style="color: red">&nbsp; Contoh : </em><em style="color: black">0401MH2015150583</em>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:submit name="cari" style="btn" class="btn"  value="Cari" onclick="return checking();"/>&nbsp;
                    <s:button name = "reset" class="btn" value ="Isi Semula" onclick= "clearText('carianPelan')" />
                </p>
            <br>
        </fieldset>
            <br><br><br><br><br><br>
    </div> 
</s:form>