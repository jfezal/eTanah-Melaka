<%-- 
    Document   : perintah_pembetulan
    Created on : Jul 28, 2010, 10:05:19 AM
    Author     : fikri
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript">

   function doGetContent(id) {
       if (id == '0'){
           alert('Sila Pilih Maklumat Untuk DiBetulkan.');
           return;
       }
       $.blockUI({
                message: $('#displayBox'),
                    css: {
                        top:  ($(window).height() - 50) /2 + 'px',
                        left: ($(window).width() - 50) /2 + 'px',
                        width: '50px'
                    }
                });

       var url = '${pageContext.request.contextPath}/daftar/perintah?getPartialPage&urusan_pilihan=' + id;
       $.ajax({
           url : url,
           dataType : 'html',
           success : function(data) {$('#div_content').html(data);$.unblockUI();}
       });
   }
</script>
<div class="subtitle displaytag">
    <s:form beanclass="etanah.view.daftar.Perintah">
        <fieldset class="aras1">
            <legend>
                Urusan Pembetulan
            </legend>
            <p>
                <label>Kemaskini :</label>
                <s:select name="urusan_pilihan" onchange="doGetContent(this.value);" style="width:400px">
                    <s:option value="0">Sila Pilih</s:option>
                    <s:option value="1">Maklumat Asas Hakmilik</s:option>
                    <s:option value="2">Maklumat Pihak Berkepentingan</s:option>
                    <s:option value="3">Maklumat Urusan SC/B/N</s:option>
                </s:select>
            </p>
        </fieldset>
        <br/>
        <div id="div_content" class="content" align="center"/>        
    </s:form>
</div>