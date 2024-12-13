<%-- 
    Document   : bypass_keutamaan
    Created on : Apr 6, 2010, 9:39:45 AM
    Author     : fikri
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%--<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/pub/styles/styles.css" />--%>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript">
    function doSubmit(frm){
        var msg;
         $.blockUI({
                message: $('#displayBox'),
                    css: {
                        top:  ($(window).height() - 50) /2 + 'px',
                        left: ($(window).width() - 50) /2 + 'px',
                        width: '50px'
                    }
        });
        $.ajax({
            type:"GET",
            url : '${pageContext.request.contextPath}/stage?nextStage&bypass=true',
            dataType : 'html',
            error : function(xhr, ajaxOptions, thrownError) {
                //alert('Terdapat masalah teknikal. Sila hubungi Pentadbir Sistem.');
                msg = xhr.statusText + '<br/>Tugasan tidak dapat dihantar ke peringkat seterusnya. Sila hubungi Pentadbir Sistem';
                frm.action = '${pageContext.request.contextPath}/senaraiTugasan?error=' + msg;
                frm.submit();
            },
            success : function(data) {
                if(data == '1'){
                    msg = 'Tugasan tidak dapat dihantar ke peringkat seterusnya. Sila hubungi Pentadbir Sistem';
                    frm.action = '${pageContext.request.contextPath}/senaraiTugasan?error=' + msg;
                    frm.submit();
                }else if(data == '2'){
                    alert('Sila buat keputusan terlebih dahulu.');
                    $('#status').click();
                }else if(data == '3') {
                    msg = 'Terdapat urusan sebelum yang masih belum selesai.';
                    frm.action = '${pageContext.request.contextPath}/senaraiTugasan?error=' + msg;
                    frm.submit();
                }else{
                    frm.action = '${pageContext.request.contextPath}/' + data;
                    frm.submit();
                }
            }
        });
    }
    function goToSenaraiTugasan(frm){
        $.blockUI({
                message: $('#displayBox'),
                    css: {
                        top:  ($(window).height() - 50) /2 + 'px',
                        left: ($(window).width() - 50) /2 + 'px',
                        width: '50px'
                    }
        });
        frm.action = '${pageContext.request.contextPath}/senaraiTugasan';
        frm.submit();
    }

</script>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
<s:form action="/stage">
    <div class="content">
        <fieldset class="aras1">
            <legend>&nbsp;</legend>            
            <p align="center">
                <label></label>&nbsp;
                <!--Terdapat Urusan sebelum yang masih belum SELESAI. Adakah anda ingin teruskan DAFTAR?-->
                <!--Tukar Ke :-->
                Hakmilik yang ingin anda daftarkan ini, mengandungi beberapa
                urusan lain yang masih dalam proses. 
                <br/>Adakah anda pasti untuk terus mendaftarkan
                urusan yang sedang anda selesaikan ini?                
            </p>
            <br/>
            <br/>
            <%--Hide Dulu Sementara--%>
            <%--<p align="center">                
                <display:table name="${actionBean.senaraiPermohonanBelumSelesai}" class="tablecloth" cellpadding="0" cellspacing="0" id="line">
                    <display:column title="No">${line_rowNum}</display:column>                    
                    <display:column title="ID Permohonan" property="idPermohonan"/>            
                    <display:column title="Kod Urusan" property="kodUrusan.nama"/>                     
                </display:table>
            </p>--%>
            <p align="center">
                <label>&nbsp;</label>
                <br/><br/><s:button name="showForm" value="Tidak" onclick="goToSenaraiTugasan(this.form);" class="btn"/>
                <s:button name="nextStage" value="Daftar" onclick="doSubmit(this.form);" class="btn"/>
            </p>
        </fieldset>
    </div>    
</s:form>

