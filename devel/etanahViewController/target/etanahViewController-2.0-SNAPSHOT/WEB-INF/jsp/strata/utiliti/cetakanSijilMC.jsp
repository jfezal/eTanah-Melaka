<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/datepicker.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script language="javascript" type="text/javascript">
    
    $(document).ready( function(){
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    });
    function doCetak(f) {
        var idHakmilik = $("#idHakmilik").val();
//
        var report = 'STRSijilMCManual_MLK.rdf';
//
//
        window.open("${pageContext.request.contextPath}/reportGeneratorServlet?" + "&reportName=" + report + "&report_p_id_hakmilik="
                + idHakmilik, "eTanah",
                "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");

        $.unblockUI();

    }
    
    function reset1(f) {
        $("#idHakmilik").val('');
        $("#idPermohonan").val('');

    }
</script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>
<s:form beanclass="etanah.view.strata.utiliti.KemasukanSijilMC">

    <s:messages />
    <s:errors />

    <fieldset class="aras1">
        <legend>Cetakan Semula Sijil MC</legend>

        <p>
            <label>Id Hakmilik :</label>
                <s:text name="idHakmilik" onkeyup="this.value=this.value.toUpperCase();" id="idHakmilik"/>

        </p>
        <p>
            <label>atau</label>
        </p>
        <br />
        <p>
            <label>Id Permohonan :</label>
            <s:text name="idPermohonan" onkeyup="this.value=this.value.toUpperCase();" id="idPermohonan"/>

        </p>
        <br />
           <label>&nbsp;</label><s:button name="" value="Papar Sijil MC" onclick="doCetak(this.form);" class="btn"/><center>
    </fieldset>
</s:form>
