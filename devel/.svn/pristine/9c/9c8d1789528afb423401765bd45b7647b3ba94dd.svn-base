<%-- 
    Document   : laporan_param_strata
    Created on : 20 SEPT 2013, 12:45:21 AM
    Author     : abu.mansur
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    var report = '${actionBean.reportName}';
    var date = new Date();
    var kodCaw = '${actionBean.kodCaw}';
    var kodDaerah = '${actionBean.kodDaerah}';
    $(document).ready(function () {
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        $("#trh_mula").val(date.getDate() + "/" + (date.getMonth() + 1) + "/" + date.getFullYear());
        $("#trh_tamat").val(date.getDate() + "/" + (date.getMonth() + 1) + "/" + date.getFullYear());
        $("#daerah").val(kodDaerah);
    });

    function doSubmitFull(f) {
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top: ($(window).height() - 50) / 2 + 'px',
                left: ($(window).width() - 50) / 2 + 'px',
                width: '50px'
            }
        });

        var form = $(f).formSerialize();
        var caw = $("#caw").val();

        var report = '${actionBean.reportName}';


        var url = form.replace(/&/g, "%26");
        window.open("${pageContext.request.contextPath}/reportGeneratorServlet?" + "&reportName=" + report + "&report_p_kod_caw=" + caw, "eTanah",
                "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");

        $.unblockUI();

    }
    function doCetak(f) {
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top: ($(window).height() - 50) / 2 + 'px',
                left: ($(window).width() - 50) / 2 + 'px',
                width: '50px'
            }
        });

        var form = $(f).formSerialize();
        var idHakmilik = $("#idHakmilik").val();
        var noPelan = $("#no_pelan").val();
        var noSkim = $("#no_skim").val();

        var report = '${actionBean.reportName}';


        var url = form.replace(/&/g, "%26");
        window.open("${pageContext.request.contextPath}/reportGeneratorServlet?" + "&reportName=" + report + "&report_p_id_hakmilik=" 
                + idHakmilik+ "&report_p_no_pelan=" + noPelan+ "&report_p_no_skim=" + noSkim, "eTanah",
                "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");

        $.unblockUI();

    }

    function doSubmitHarian(f) {
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top: ($(window).height() - 50) / 2 + 'px',
                left: ($(window).width() - 50) / 2 + 'px',
                width: '50px'
            }
        });

        var form = $(f).formSerialize();

        var report = '${actionBean.reportName}';
        var caw = $("#caw").val();
        var trh_mula = $("#trh_mula").val();
        var trh_tamat = $("#trh_tamat").val();
        var id_pguna = $("#id_pguna").val();


        var url = form.replace(/&/g, "%26");
        window.open("${pageContext.request.contextPath}/reportGeneratorServlet?" + "&reportName=" + report + "&report_p_kod_caw=" + caw + "&report_p_trh_mula=" + trh_mula + "&report_p_trh_tamat=" + trh_tamat + "&report_p_id_pguna=" + id_pguna, "eTanah",
                "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");

        $.unblockUI();

    }

</script>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif"
     width="50" height="50" style="display: none" alt=""/>

<s:form beanclass="etanah.view.strata.laporan.StrataCetakSKPSK">
    <div id="display" class="subtitle">
        <s:hidden id="reportname" name="reportName"/>
        <fieldset class="aras1">

            <lagend>
                <c:if test="${actionBean.reportName eq 'UTILITIBSKDHDK_MLK.rdf' || actionBean.reportName eq 'UTILITIBSKDHDK_MLK.rdf'}">
                    Cetakan Semula Borang SK (DHDK)
                </c:if>
                <c:if test="${actionBean.reportName eq 'UTILITIBSKDHKK_MLK.rdf' || actionBean.reportName eq 'UTILITIBSKDHKK_NS.rdf'}">
                    Cetakan Semula Borang SK (DHKK)
                </c:if>
                <c:if test="${actionBean.reportName eq 'UTILITIPSK_MLK.rdf' || actionBean.reportName eq 'UTILITIPSK_NS.rdf'}">
                    Cetakan Semula Pelan 
                </c:if>
            </lagend>




            <p>
                <label>Id Hakmilik :</label>
                <s:text name="idHakmilik" id="idHakmilik" size="40"/>
            </p>
            <c:if test="${actionBean.reportName eq 'UTILITIPSK_MLK.rdf' || actionBean.reportName eq 'UTILITIPSK_NS.rdf'}">
                atau
                <p>
                    <label>No Pelan (PA(B)):</label>
                    <s:text name="no_pelan" id="no_pelan" size="40"/>
                </p>
                atau
                <p>
                    <label>No Skim :</label>
                    <s:text name="no_skim" id="no_skim" size="40"/>
                </p>
            </c:if>
            <p align="center">
                <s:button name="" id="simpan" value="Papar" class="btn" onclick="doCetak(this.form);"/>&nbsp;
            </p>



            <br>
            <div id="btnPapar">
                <p align="center">


                    <s:reset name="" value="Isi Semula" class="btn"/>&nbsp;
                    <s:button name="" id="close" value="Tutup" onclick="self.close();" class="btn"/>&nbsp;
                </p>
            </div>
        </fieldset>
    </div>
</s:form>
