<%-- 
    Document   : notis_pertanyaan_main
    Created on : Nov 9, 2012, 10:39:11 AM
    Author     : ei
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
//    function doSearch(e,f) {
//        var a = $('#idPermohonan').val();
////        var b = $('#idHakmilik').val();
//        if(a == '' ){
////        if(a == '' && b == '' && c == ''){
//            alert('Sila masukan id Perserahan');            
//            return;
//        }
//        f.action = f.action + '?' + e;
//        f.submit();
//    }
</script>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/pub/styles/styles.css" />
        <title>Pertanyaan Notis</title>
    </head>
    <body>
        <stripes:messages />
        <stripes:errors />
        <!-- PERTANYAAN NOTIS -->
        <stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>
        <stripes:useActionBean beanclass="etanah.view.daftar.utiliti.NotisPertanyaanActionBean" var="pertanyaanNotis" />           
        <stripes:form action="/utiliti/pertanyaanNotis">
            <fieldset class="aras1">
                <legend>Utiliti Pertanyaan Notis </legend>
                <p>Sila pilih jenis carian</p>
                <p><label for="idPermohonan">Id Permohonan/ID Perserahan :</label>
                    <input type="text" name="permohonan.idPermohonan" id="idPermohonan" onkeyup="this.value=this.value.toUpperCase();" style="width:180px;"/>
                    <em>atau</em>
                </p>                
                <p>
                    <label>Jenis Notis :</label>
                    <stripes:select name="jenisNotis" id="jenisNotis" style="width:180px;">
                        <stripes:option value="">--Sila Pilih--</stripes:option>
                        <c:forEach items="${listUtil.senaraiKodNotis}" var="i" >
                            <stripes:option value="${i.kod}">${i.nama}</stripes:option>
                        </c:forEach>
                    </stripes:select>
                </p>                
                <p>
                    <label>&nbsp;</label>
                    <stripes:submit name="checkPermohonan" value="Seterusnya" class="btn" onclick="doSearch(this.name, this.form);"/>                    
                </p>
                <br>
            </fieldset>
        </stripes:form>
<!--    </div>-->
</body>
</html>
