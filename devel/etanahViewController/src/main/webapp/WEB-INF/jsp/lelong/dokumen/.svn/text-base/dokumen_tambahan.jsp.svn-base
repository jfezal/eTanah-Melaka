<%-- 
    Document   : dokumen_tambahan
    Created on : Dec 28, 2009, 4:11:53 PM
    Author     : fikri
--%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript">
    function simpan(event, f){
        var mohonId=$('#idMohon').val();
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            if(data == "success"){
                self.opener.refreshingPagingFolder(mohonId);
            }
            if(data == "fail"){
            }
            self.close();
        },'html');
    }
        
</script>
<s:useActionBean beanclass="etanah.view.lelong.dokumen.FolderAction" var="list"/>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
<s:form beanclass="etanah.view.lelong.dokumen.FolderAction" >
    <s:hidden id="idMohon" name="permohonan.idPermohonan"/>
    <br/>
    <fieldset class="aras1">
        <legend>Dokumen-dokumen tambahan yang diserahkan:</legend>
        <div class="content" align="left">
            <display:table name="${actionBean.kandunganFolderTamb}" id="row1"  class="tablecloth"  >
                <display:column title="Pilih" >
                    <s:select name="kandunganFolderTamb[${row1_rowNum - 1}].dokumen.kodDokumen.kod"
                              style="width:400px;">
                        <s:option value="">Pilih ...</s:option>
                        <s:options-collection collection="${list.kodDokumenNotRequired}"
                                              label="nama" value="kod" />
                    </s:select>
                </display:column>
                <display:column title="Catatan" >
                    <s:text name="kandunganFolderTamb[${row1_rowNum - 1}].catatan" size="50" />                   
                </display:column>
                <display:column title="Tarikh Dokumen">
                    <s:text class="datepicker" id="tarikhDokumen[${row1_rowNum - 1}].dokumen.tarikhDokumen" name="kandunganFolderTamb[${row1_rowNum - 1}].dokumen.tarikhDokumen" formatPattern="dd/MM/yyyy" style="width:100px;"/><br>
                </display:column>
            </display:table>
        </div>
        <p>
            <label>&nbsp;</label>
            <s:button name="simpanDokumenTambahan" value="Tambah" class="btn" onclick="simpan(this.name, this.form);"/>
            <s:button name="close" value="Tutup" onclick="self.close();" class="btn"/>
        </p>
    </fieldset>
</s:form>
