<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://stripes.sourceforge.net/stripes-security.tld" prefix="ss"%>
<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.tabs.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<link type="text/css" rel="stylesheet" href="pub/styles/tabNavList.css"/>
<!DOCTYPE html>

<script type="text/javascript">
    $(document).ready(function() {
        $("#tab_strata").tabs();
        $("#tab_strata").tabs('select','#'+'${actionBean.selectedTab}');
    });
    
    function clearForm(f) {
        $('#idPermohonan').val('');
    }
            
    function doSearch(f,e){       
        var v = $('#idPermohonan').val();
        if(v == ''){
            alert('Sila masukkan ID Permohonan.');
            $('#idPermohonan').focus();
            return;
        }
        f.action = f.action + '?' + e;        
        f.submit();
    }
</script>

<div class="subtitle">

    <%--<s:messages />--%>
    <s:errors />

    <s:form beanclass="etanah.view.strata.utiliti.KemaskiniDataPermohonanStrataActionBean">

        <fieldset class="aras1">
            <legend>Kemaskini Data Permohonan Strata</legend>
            <p>
                <label>ID Permohonan :</label>
                <s:text name="permohonan.idPermohonan" size="34" maxlength="20" id="idPermohonan" onkeyup="this.value=this.value.toUpperCase();"/>
            </p> 
            <p>
                <label>&nbsp;</label>
                <s:submit name="search" value="Cari" onclick="" class="btn"/>
                <s:submit name="reset" value="Isi Semula" class="btn" onclick=""/>&nbsp;
            </p>
            <br/>
        </fieldset>
            
    </s:form>
</div>



