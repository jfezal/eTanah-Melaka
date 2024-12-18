<%-- 
    Document   : upload_doc
    Created on : Jun 6, 2013, 8:55:44 PM
    Author     : Admin
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">
    function doSave(e, f){
        var q = $(f).formSerialize();
        var url = f.action + '?' + e;
        $.post(url,q,
        function(data){
            $('#folder_div',opener.document).html(data);
            self.opener.refreshPage();
            self.close();

        }
        ,'html');
    }

    function simpan(event, f){
        var mohonId=$('#idPermohonan').val();
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            if(data == "success"){
                self.opener.refreshingPage(mohonId);
            }
            if(data == "fail"){
            }
            self.close();
        },'html');
    }

    function validateForm(){
        if($('#row2').val()=="")
        {
            alert('Sila pilih dari senarai dokumen terlebih dahulu sebelum simpan maklumat');
            $('#row2').focus();
            return false;
        }
        return true;
    }

    function refreshPage(){
        alert("refreshPage");
        var url = '${pageContext.request.contextPath}/pengambilan/carian_aduan?rehydrate';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }
</script>
<s:useActionBean beanclass="etanah.view.stripes.pengambilan.AduanActionBean" var="list"/>

<s:form beanclass="etanah.view.stripes.pengambilan.AduanActionBean">
    <s:hidden name="permohonan.idPermohonan"/>
    <s:hidden name="idPermohonan" id="idPermohonan" value="${actionBean.idPermohonan}"/>
    <%--<s:text name="idPermohonan" id="idPermohonan" size="40" value="${actionBean.idPermohonan}"/>--%>
    <br/>
    <fieldset class="aras1" >
        <legend>Dokumen-dokumen tambahan yang diserahkan:</legend>
        <div class="content" align="left">
            <display:table name="${actionBean.kandunganFolderTamb}" id="row1"  class="tablecloth"  >
                <display:column title="Pilih" >
                    <s:select name="kandunganFolderTamb[${row1_rowNum - 1}].dokumen.kodDokumen.kod"
                              style="width:800px;" id="row2">
                        <s:option value="">Pilih ...</s:option>
                        <s:options-collection collection="${list.kodDokumenNotRequired}"
                                              label="nama" value="kod" />
                    </s:select>
                </display:column>
                <display:column title="Catatan" >
                    <s:text name="kandunganFolderTamb[${row1_rowNum - 1}].catatan" size="50" />
                </display:column>
            </display:table>
        </div>
        <p>
            <label>&nbsp;</label>
            <s:button name="simpanDokumenTambahan" value="Simpan" class="btn" onclick="if(validateForm())simpan(this.name, this.form);"/>
            <s:button name="close" value="Tutup" onclick="self.close();" class="btn"/>
        </p>
    </fieldset>
</s:form>

