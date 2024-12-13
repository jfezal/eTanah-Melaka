<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.7.3.custom.min.js"></script>
<script src="${pageContext.request.contextPath}/pub/js/ui.datepicker-ms.js" type="text/javascript"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy',
            changeMonth: true,
            changeYear: true});
        
        var url = '${pageContext.request.contextPath}/upload?reload&idPermohonan=${idPermohonan}&prm=${prm}';
        $.ajax({
            type:"GET",
            url : url,
            success : function(data) {
               $('#folder_div',opener.document).html(data);
            }
        });
    });
</script>
<s:messages/>
<s:form beanclass="etanah.view.dokumen.ViewDocumentAction">
    <div class="subtitle">
        <fieldset class="aras1">
            <s:hidden name="idPermohonan"/>            
            <legend>Kemaskini ${actionBean.dokumen.kodDokumen.nama}</legend>
            <p>
                <label>Tajuk :</label>
                <s:text name="dokumen.tajuk" size="50"/>
                <s:hidden name="dokumen.idDokumen"/>
            </p>
            <p>
                <label>No Rujukan :</label><s:text name="dokumen.noRujukan"/>
            </p>
            <p>
                <label>Tarikh Dokumen :</label>
                <s:text name="dokumen.tarikhDokumen" class="datepicker" formatType="date" formatPattern="dd/MM/yyyy" maxlength="10" size="10"/>
            </p>
            <br/>
            <p>
                <label>&nbsp;</label>
                <s:submit class="btn" name="saveEditDoc" value="Simpan"/>
                <s:button class="btn" name="" value="Tutup" onclick="self.close();"/>
            </p>
        </fieldset>
    </div>
</s:form>
