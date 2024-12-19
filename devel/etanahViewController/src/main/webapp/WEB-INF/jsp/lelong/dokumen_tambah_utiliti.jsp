<%-- 
    Document   : dokumen_tambah_utiliti
    Created on : Oct 20, 2011, 1:12:33 PM
    Author     : mdizzat
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript">
    function simpan(event, f){
        var mohonId=$('#idPermohonan').val();
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.close();
            self.opener.refreshingPagingFolder(mohonId);
        },'html');
    }
    
    
    function tutup(){
        var mohonId=$('#idPermohonan').val();
        self.close() ;
        self.opener.refreshingPagingFolder(mohonId);
    }
    
    $(document).ready( function(){
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    });
</script>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
<s:form beanclass="etanah.view.stripes.lelong.PembatalanPermohonanUtiliti" >
    <s:hidden id="idPermohonan" name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
    <br/>
    <fieldset class="aras1">
        <legend>Dokumen-dokumen tambahan yang diserahkan : </legend>
        <div class="content" align="left">
            <display:table name="${actionBean.kandunganFolderTamb}" id="row1"  class="tablecloth"  >
                <display:column title="Pilih" >
                    <s:select name="kandunganFolderTamb[${row1_rowNum - 1}].dokumen.kodDokumen.kod"
                              style="width:400px;">
                        <s:option value="">Pilih ...</s:option>
                        <s:options-collection collection="${actionBean.listKodDokumen}"
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
            <s:button name="close" value="Tutup" onclick="tutup();" class="btn"/>
        </p>
    </fieldset>
</s:form>
