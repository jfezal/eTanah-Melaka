<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

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
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">

    $(document).ready(function() {
        $('.alphanumeric').alphanumeric();

    });

    function popupParam(f) {

        doBlockUI();
        var form = $(f).formSerialize();
        window.open("${pageContext.request.contextPath}/reportGeneratorServlet?" + form, "eTanah", "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
        $.unblockUI();
    }

    function doBlockUI() {
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top: ($(window).height() - 50) / 2 + 'px',
                left: ($(window).width() - 50) / 2 + 'px',
                width: '50px'
            }
        });

    }

</script>
<img alt="" id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
<s:form beanclass="etanah.view.stripes.consent.LaporanPermohonanPerakuanTPTG">

    <div id="display" class="subtitle">
        <fieldset class="aras1">
            <legend>Senarai Laporan
            </legend>
            <br/>
            <font color="black">
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Ringkasan Permohonan PTG (Perakuan TPTG) 
            </font>
            <br/> <br/>
            <p>
                <label>&nbsp;</label>
                <s:button name="" value="Papar" class="btn" onclick="popupParam(this.form)"/>
            </p>
            <br/>
            <br/> <br/><br/> <br/><br/> <br/><br/> <br/><br/>
        </fieldset>
        <s:hidden name="report_p_id_mohon"/>
        <s:hidden name="reportName" id="reportname"/> 

    </div>

</s:form>

