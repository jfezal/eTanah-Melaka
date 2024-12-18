<%--
    Document   : muat_naikimej
    Created on : Nov 12, 2010, 8:19:28 AM
    Author     : Murali
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
    $(document).ready(function() {
       var url = '${pageContext.request.contextPath}/strata/permohonanStrata?reload&idPermohonan=${idPermohonan}';
        $.ajax({
            type:"GET",
            url : url,
            success : function(data) {
               $('#page_div',opener.document).html(data);
            }
        });
    });

    //$(window).bind("beforeunload", function(e){alert('aaaa') });


   <%-- window.onbeforeunload= function (evt) {
        alert('test');
    }--%>


</script>
 <s:errors/>
 <s:messages/>
<div id="error"/>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none" alt=""/>
<s:form beanclass="etanah.view.strata.PermohonanStrataActionBean">
    <s:hidden name="folderId" value="${folderId}"/>
    <s:hidden name="dokumenId" value="${dokumenId}"/>
    <s:hidden name="idPermohonan" value="${idPermohonan}"/>
    <fieldset class="aras1">
        <legend>Muat Naik Imej</legend>
        <p>
            <label> Catatan: </label>&nbsp;&nbsp;<s:textarea  name="catatan" cols="34" rows="5" class="normal_text"/>
        </p> <br>
        <p>
            <label>&nbsp;</label>&nbsp;
            <s:file name="fileToBeUpload"/>
            <%--<input type="file" name="filedokumentobeupload"/>--%>
            <c:if test="${actionBean.fileToBeUpload ne null}">
                ${actionBean.fileToBeUpload.fileName}
            </c:if>
        </p>
        <br/>
         <p>
            <label>&nbsp;</label>&nbsp;
              <s:submit name="muatNaik" value="Simpan" class="btn" />
              <s:button name="close" value="Tutup" onclick="self.close();" class="btn"/>
        </p>
   </fieldset>
</s:form>
