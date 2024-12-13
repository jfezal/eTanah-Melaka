<%-- 
    Document   : muat_imej_popup
    Created on : Jan 30, 2012, 4:07:54 PM
    Author     : Srinivas
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
        var url = '${pageContext.request.contextPath}/pelupusan/muat_naik_imej?showRefresh';
        $.ajax({
            type:"GET",
            url : url,
            success : function(data) {
                $('#page_div',opener.document).html(data);
            }
        });
    });

     function validateFile()
     {
        var fileAndPath=
           document.muatImej.fileToBeUpload.value;
        var lastPathDelimiter=fileAndPath.lastIndexOf("\\");
        var fileNameOnly=fileAndPath.substring(lastPathDelimiter+1);
        var file_extDelimiter=fileNameOnly.lastIndexOf(".");
        var file_ext=fileNameOnly.substring(file_extDelimiter+1).toLowerCase();
        
        if(file_ext=='jpg')
             {
             return true;
             }else if(file_ext=='gif'){
                 return true;
             }else if(file_ext=='png' ){
                 return true;
             }else if(file_ext=='jpeg'){
                 return true;
             }else{
                 alert("Please Upload Image Only");
                 return false;
                 
            }


       // }catch(err)
       // {
         // txt="There was an error on this page.\n\n";
         // txt+="Error description: " + err.description + "\n\n";
         // txt+="Click OK to continue.\n\n";
         // txt+=document.forms[0].fileToBeUpload.value;
         // alert(txt);
         // }
 
    }

</script>
<s:errors/>
<s:messages/>
<div id="error"/>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
<s:form beanclass="etanah.view.stripes.pelupusan.MuatNaikImejActionBean" name="muatImej">
    <s:hidden name="folderId" value="${folderId}"/>
    <s:hidden name="dokumenId" value="${dokumenId}"/>
    <s:hidden name="idPermohonan" value="${idPermohonan}"/>
    <s:hidden name="lokasi" value="${lokasi}"/>
    <fieldset class="aras1">
        <legend>Muat Naik</legend>
        <p> <p>
            <label>Catatan :</label><s:textarea name="catatan" cols="50" rows="5"/>

            <%--   <p>
                   <label> Lokasi :</label>
                   <s:select name="lokasi" value="lokasi" id="foto">
                       <s:option value="">Sila pilih lokasi gambar</s:option>
                       <s:option value="U" label="Utara"></s:option>
                       <s:option value="S" label="Selatan"></s:option>
                       <s:option value="T" label="Timur"></s:option>
                       <s:option value="B" label="Barat"></s:option>

                </s:select>--%>&nbsp;<p></p>
        <label>&nbsp;</label>&nbsp;
        <s:file name="fileToBeUpload" id="fileToBeUpload" accept="image/png, image/gif, image/jpeg, image/jpg"    />
        <%--<input type="file" name="filedokumentobeupload"/>--%>
        <c:if test="${actionBean.fileToBeUpload ne null}">
            ${actionBean.fileToBeUpload.fileName}
        </c:if>
        <br/>
        <p>
            <label>&nbsp;</label>&nbsp;
            <%--<s:submit name="muatNaik" value="Simpan 21" class="btn" onclick="save1(this.name, this.form);"/>--%>
            <s:submit name="muatNaik" value="Simpan" class="btn" onclick="return validateFile() "/>
            <s:button name="close" value="Tutup" onclick="self.close();" class="btn"/>
        </p>
    </fieldset>
</s:form>