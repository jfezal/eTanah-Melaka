
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt"    prefix="c" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script language="javascript" type="text/javascript">
    function doViewReport(v) {
            var idmohon = '${actionBean.mohon.idPermohonan}';
            var report = 'STRJadualPetak_MLK.rdf';
            var url = "reportName="+report+"%26report_p_id_mohon="+idmohon;
            window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url=" + url, "eTanah",
            "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
        }
</script>


<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>
<s:form beanclass="etanah.view.strata.JadualPetakManual">

    <s:messages />
    <s:errors />

    <center>
        <s:button name="" value="Papar Jadual Petak" class="longbtn" onclick="doViewReport('${actionBean.mohon.idPermohonan}');" />
        <%--<display:table style="width:40%" class="tablecloth" name="${actionBean.cekMhnBngn}" pagesize="100" cellpadding="0" cellspacing="0" id="line">
            <display:column title="Petak">L ${line.nama}</display:column>
            <display:column title="Syer">${line.syer}</display:column>
            <display:column title="Luas (Meter Persegi)">${line.luas}</display:column>
        </display:table>--%>
    </center>
</s:form>
