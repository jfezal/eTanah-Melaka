<%--
    Document   : muat_naik
    Created on : oct 06, 2012, 8:19:28 AM
    Author     : murali
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript">

    function doSave(e, f){
        //var q = $(f).formSerialize();
        var url = f.action + '?' + e;
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
            url : url,
            //data : q,
            dataType : 'html',
            error : function(xhr, ajaxOptions, thrownError) {
                //alert('Terdapat masalah teknikal. Sila hubungi Pentadbir Sistem.');
                $('#error').html('<div class=errors>Terdapat Masalah Teknikal. Sila Hubungi Pentadbir Sistem.</div><div id=t><a href="#">[ Lihat Terperinci ]</a></div><br/><div id=error class=b>' + xhr.responseText + '</div>');
                $.unblockUI();
            },
            success : function(data) {
                $('#folder_div').html(data);
                $("#folder_div input:text").each( function(el) {
                    $(this).blur( function() {
                        $(this).val( $(this).val().toUpperCase());
                    });
                });
                self.close();
            }
        });
    }
    function simpanupload(f){
        var q = $(f).formSerialize();
        var url = f.action + '?showForm';
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);

        },'html');
        return true;
    }
    $(document).ready(function() {    
            var val = $("#idMh").val();            
            var url = '${pageContext.request.contextPath}/strata/permohonanStrata_PHPP?showRefresh&idMh=' +val;
            <%--var url = '${pageContext.request.contextPath}/strata/laporanTanah_PHPC?hakdetails&idMh='+ val;--%>
            $.ajax({
                type:"GET",
                url : url,
                success : function(data) {
                    $('#page_div',opener.document).html(data);
                }
            });
        });
</script>
<s:errors/>
<s:messages/>
<div id="error"/>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
<s:form beanclass="etanah.view.strata.PermohonanStrataPHPPActionBean">
    <s:hidden name="folderId" value="${folderId}"/>
    <s:hidden name="dokumenId" value="${dokumenId}"/>
    <s:hidden name="idPermohonan" value="${idPermohonan}"/>
    <s:hidden name="lokasi" value="${lokasi}"/>
    <s:hidden name="idMh" value="${idMh}" id="idMh"/>
    <fieldset class="aras1">
        <legend>Muat Naik</legend>
        <p> <p>
            <label>Catatan :</label><s:textarea name="catatan" cols="50" rows="5"/>
            <label>&nbsp;</label>&nbsp;
            <s:file name="fileToBeUpload"/>
            <c:if test="${actionBean.fileToBeUpload ne null}">
                ${actionBean.fileToBeUpload.fileName}
            </c:if>
            <br/>
        <p>
            <label>&nbsp;</label>&nbsp;
            <s:submit name="muatNaik" value="Simpan" class="btn"/>
            <s:button name="close" value="Tutup" onclick="self.close();" class="btn"/>
        </p>
    </fieldset>
</s:form>