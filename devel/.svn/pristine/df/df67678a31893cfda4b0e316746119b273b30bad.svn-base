<%-- 
    Document   : muatNaikMMKN
    Created on : Mar 7, 2021, 10:33:27 AM
    Author     : nns
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

    function doSave(e, f) {
        //var q = $(f).formSerialize();
        var url = f.action + '?' + e;
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top: ($(window).height() - 50) / 2 + 'px',
                left: ($(window).width() - 50) / 2 + 'px',
                width: '50px'
            }
        });
        $.ajax({
            type: "GET",
            url: url,
            //data : q,
            dataType: 'html',
            error: function(xhr, ajaxOptions, thrownError) {
                $('#error').html('<div class=errors>Terdapat Masalah Teknikal. Sila Hubungi Pentadbir Sistem.</div><div id=t><a href="#">[ Lihat Terperinci ]</a></div><br/><div id=error class=b>' + xhr.responseText + '</div>');
                $.unblockUI();
            },
            success: function(data) {
                $('#folder_div').html(data);
                $("#folder_div input:text").each(function(el) {
                    $(this).blur(function() {
                        $(this).val($(this).val().toUpperCase());
                    });
                });
                self.close();
            }
        });
    }
    $(document).ready(function() {
        var url = '${pageContext.request.contextPath}/muatnaik?reload&idPermohonan=${idPermohonan}&prm=${prm}';
        $.ajax({
            type: "GET",
            url: url,
            success: function(data) {
                $('#folder_div', opener.document).html(data);
            }
        });
    });
    function refreshpage(id) {
        self.close();
        opener.refreshPageV2(id);
    }

</script>
<s:errors/>
<s:messages/>
<div id="error"/>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
<s:form beanclass="etanah.view.strata.UploadMMKActionBean">
    <s:hidden name="folderId" value="${folderId}"/>
    <s:hidden name="dokumenId" value="${dokumenId}"/>
    <s:hidden name="idPermohonan" value="${idPermohonan}"/>       
    <s:hidden name="kodDokumen" value="${kodDokumen}"/>       
    <s:hidden name="prm" value="${prm}"/>
    <fieldset class="aras1">
        <legend>Muat Naik ${actionBean.kodDokumen}/${idPermohonan}/${dokumenId}/${folderId}</legend>
        <p>
            <label>&nbsp;</label>&nbsp;
            <s:file name="fileToBeUpload"/>
            <c:if test="${actionBean.fileToBeUpload ne null}">
                Fail yang dimuat naik :${actionBean.fileToBeUpload.fileName}
            </c:if>
        </p>
        <br/>
           
        <p>
            <label>&nbsp;</label>&nbsp;
            <s:submit name="processUpload" value="Simpan" class="btn"/>
              <s:button name="close" value="Tutup" onclick="refreshpage();" class="btn"/>
        </p>
    </fieldset>
</s:form>
