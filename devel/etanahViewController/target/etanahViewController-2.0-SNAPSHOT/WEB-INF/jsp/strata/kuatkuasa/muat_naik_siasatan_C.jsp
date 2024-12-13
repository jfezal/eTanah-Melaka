<%-- 
    Document   : muat_naik_siasatan
    Created on : Aug 10, 2011, 1:54:51 PM
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
                //self.opener.refreshPage();
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
        var url = '${pageContext.request.contextPath}/strata/SemakKertasSiasatanFoliC?showRefresh';
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
<s:form beanclass="etanah.view.strata.SenaraiKertasSiasatanFoliCActionBean">
    <s:hidden name="folderId" value="${folderId}"/>
    <s:hidden name="dokumenId" value="${dokumenId}"/>
    <s:hidden name="idPermohonan" value="${idPermohonan}"/>
    <s:hidden name="permohonan.idPermohonan" value="${idPermohonan}"/>
    <fieldset class="aras1">
        <legend>Muat Naik</legend>
        <p>
            <label> Fail :</label>&nbsp;
            <s:file name="fileToBeUpload"/>
            <c:if test="${actionBean.fileToBeUpload ne null}">
                ${actionBean.fileToBeUpload.fileName}
            </c:if>
        </p>
        <%--<p>
            <label> Catatan :</label>
            <s:textarea id="catatan" name="catatan" cols="30" rows="2" />
        </p>--%>
        <br/>
        <p>
            <label>&nbsp;</label>&nbsp;
            <s:submit name="processUpload" value="Simpan" class="btn"/>
            <s:button name="close" value="Tutup" onclick="self.close();" class="btn"/>
        </p>
    </fieldset>
</s:form>
