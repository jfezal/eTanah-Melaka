
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Muatnaik Fail JomPay</title>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script src="<%= request.getContextPath()%>/pub/js/ui.datepicker-ms.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>

<script type="text/javascript">

    function simpan(event, f) {

        var url = f.action + '?' + event;
        $.post(url,
                function () {
                    self.close();
                }, 'html');
        setTimeout("self.close()", 1000);
    }
</script>

<s:errors/>
<s:messages/>
<body>
    <script>
        // Allow the user to be warned by default.
        var allowPrompt = true;
        window.onbeforeunload = WarnUser;
        function WarnUser()
        {
            if (allowPrompt)
                refreshpage();
            if (allowPrompt)
            {
                event.returnValue = "Segala Maklumat tidak akan disimpan, anda pasti?";
            } else
            {
                // Reset the flag to its default value.
                allowPrompt = true;
            }
        }
        function NoPrompt()
        {
            allowPrompt = false;
        }

        function doSubmit(e, f, g) {
            alert("1111111111");
            var q = $(f).formSerialize();
            f.action = f.action + '?' + e + '&idUploadFile=' + g;
            f.submit();
        }

    </script>
    <div id="error"/>
    <img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>

    <s:form beanclass="etanah.view.stripes.jomPay.JomPaySenaraiTransaksiActionBean" name="form">

        <s:errors/>
        <s:messages/>
        <fieldset class="aras1">
            <legend>Senarai Fail Berjaya Di Proses</legend>
            <p>
                <display:table style="width:100%" class="tablecloth" name="${actionBean.senaraiTransaksi}"  cellpadding="0" cellspacing="0" id="line">
                    <display:column title="Bil">${line_rowNum}</display:column>
                    <display:column title="Nama Fail"><a href="uploadCsvFail?paparAllSuccessFile&idUploadFile=${line.j.idJomPayUpload}">${line.j.namaFail}</a></display:column>
                    <display:column title="Tarikh Muat Naik">${line.tarikhMuatNaik}</display:column>
                    <display:column title="Berjaya">${line.ok}</display:column>
                    <display:column title="Tidak Berjaya"><a href="failed_transaction?showForm&idUploadFile=${line.j.idJomPayUpload}">${line.xok}</a></display:column>
                </display:table>
            </p>
        </p>

    </fieldset>
</s:form>
</body>

