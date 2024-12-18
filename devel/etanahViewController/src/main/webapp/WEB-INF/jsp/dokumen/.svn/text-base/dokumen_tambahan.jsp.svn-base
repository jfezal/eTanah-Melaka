<%-- 
    Document   : dokumen_tambahan
    Created on : Dec 28, 2009, 4:11:53 PM
    Author     : fikri
--%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.7.3.custom.min.js"></script>
<script src="${pageContext.request.contextPath}/pub/js/ui.datepicker-ms.js" type="text/javascript"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy',
            changeMonth: true,
            changeYear: true});
    });
    function doSave(e, f) {

        $.blockUI({
            message: $('#displayBox'),
            css: {
                top: ($(window).height() - 50) / 2 + 'px',
                left: ($(window).width() - 50) / 2 + 'px',
                width: '50px'
            }
        });

        var q = $(f).formSerialize();
        var url = f.action + '?' + e;
        $.post(url, q,
                function(data) {
                    $('#folder_div', opener.document).html(data);
                    $.unblockUI();
                    self.close();
                }
        , 'html');
    }
</script>
<%--<s:useActionBean beanclass="etanah.view.dokumen.FolderAction" var="list"/>--%>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
<s:form beanclass="etanah.view.dokumen.FolderAction" >
    <s:hidden name="permohonan.idPermohonan"/>    
    <br/>
    <fieldset class="aras1">
        <legend>Dokumen-dokumen tambahan yang diserahkan:</legend>
        <div class="content" align="left">
            <display:table name="${actionBean.kandunganFolderTamb}" id="row1"  class="tablecloth"  >
                <display:column title="Pilih" >
                    <s:select name="kandunganFolderTamb[${row1_rowNum - 1}].dokumen.kodDokumen.kod"
                              style="width:400px;">
                        <s:option value="">Pilih ...</s:option>
                        <s:options-collection collection="${list.senaraiKodDokumen}"
                                              label="nama" value="kod" />
                        <%--<s:options-collection collection="${list.kodDokumenNotRequired}"
                                              label="nama" value="kod" />--%>
                    </s:select>
                </display:column>
                <display:column title="Catatan" >
                    <s:text name="kandunganFolderTamb[${row1_rowNum - 1}].catatan" size="50" />                   
                </display:column>
                <display:column title="No Rujukan">
                    <s:text name="kandunganFolderTamb[${row1_rowNum - 1}].dokumen.noRujukan"/>
                </display:column>
                <display:column title="Tarikh Dokumen">
                    <s:text name="kandunganFolderTamb[${row1_rowNum - 1}].dokumen.tarikhDokumen" class="datepicker" formatType="date" formatPattern="dd/MM/yyyy" maxlength="10" size="10"/>
                </display:column>
            </display:table>
        </div>
        <p>
            <label>&nbsp;</label>
            <s:button name="simpanDokumenTambahan" value="Simpan" class="btn" onclick="doSave(this.name, this.form);"/>
            <s:button name="close" value="Tutup" onclick="self.close();" class="btn"/>
        </p>
    </fieldset>
</s:form>
