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
    $(document).ready( function(){
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        $("#trh_mula").val(date.getDate()+"/"+(date.getMonth()+1)+"/"+date.getFullYear());
        $("#trh_tamat").val(date.getDate()+"/"+(date.getMonth()+1)+"/"+date.getFullYear());
        $("#daerah").val(kodDaerah);
    });

    function doSubmitFull(f){
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top:  ($(window).height() - 50) /2 + 'px',
                left: ($(window).width() - 50) /2 + 'px',
                width: '50px'
            }
        });

        var form = $(f).formSerialize();
        var caw = $("#caw").val();
        
        var report = '${actionBean.reportName}';
        
   
        var url = form.replace(/&/g, "%26");
        window.open("${pageContext.request.contextPath}/reportGeneratorServlet?"+"&reportName="+report+"&report_p_kod_caw="+caw, "eTanah",
        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
    
        $.unblockUI();
      
    }
    function doCetak(f){
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top:  ($(window).height() - 50) /2 + 'px',
                left: ($(window).width() - 50) /2 + 'px',
                width: '50px'
            }
        });

        var form = $(f).formSerialize();
        var idHakmilik = $("#idHakmilik").val();

        var report = '${actionBean.reportName}';


        var url = form.replace(/&/g, "%26");
        window.open("${pageContext.request.contextPath}/reportGeneratorServlet?"+"&reportName="+report+"&report_p_id_hakmilik="+idHakmilik, "eTanah",
        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");

        $.unblockUI();

    }

    function doSubmitHarian(f){
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top:  ($(window).height() - 50) /2 + 'px',
                left: ($(window).width() - 50) /2 + 'px',
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
        window.open("${pageContext.request.contextPath}/reportGeneratorServlet?"+"&reportName="+report+"&report_p_kod_caw="+caw+"&report_p_trh_mula="+trh_mula+"&report_p_trh_tamat="+trh_tamat+"&report_p_id_pguna="+id_pguna, "eTanah",
        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");

        $.unblockUI();

    }
        
</script>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif"
     width="50" height="50" style="display: none" alt=""/>

<s:form beanclass="etanah.view.strata.laporan.StrataTukargantiActionBean">
    <div id="display" class="subtitle">
        <s:hidden id="reportname" name="reportName"/>
        <fieldset class="aras1">

            <lagend>
                <c:if test="${actionBean.reportName eq 'STRLaporanKeseluruhanTG_MLK.rdf' || actionBean.reportName eq 'STRLaporanKeseluruhanTG_NS.rdf'}">
                    Laporan Keseluruhan Tukarganti Hakmilik Strata
                </c:if>
                <c:if test="${actionBean.reportName eq 'STRLaporanHarianTG_MLK.rdf' || actionBean.reportName eq 'STRLaporanHarianTG_NS.rdf'}">
                    Laporan Harian Tukarganti Hakmilik Strata
                </c:if>
            </lagend>



            <c:if test="${actionBean.reportName eq 'STRLaporanKeseluruhanTG_MLK.rdf' || actionBean.reportName eq 'STRLaporanKeseluruhanTG_NS.rdf'}">
                <p>
                    <label>Cawangan :</label>
                    <s:select id="caw" name="report_p_kod_caw" style="width:260px;" value="${actionBean.kodCaw}" disabled="true">
                        <%--     <s:option value="">Semua</s:option>--%>
                        <s:option value="">Semua</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>
                    </s:select>
                    <s:hidden name="report_p_kod_caw" value="${actionBean.kodCaw}"/>
                </p>
                <p align="center">
                <s:button name="" id="simpan" value="Papar" class="btn" onclick="doSubmitFull(this.form);"/>&nbsp;
                </p>
            </c:if>

            <c:if test="${actionBean.reportName eq 'STRLaporanHarianTG_MLK.rdf' || actionBean.reportName eq 'STRLaporanHarianTG_NS.rdf'}">
                <p>
                    <label>Cawangan :</label>
                    <s:select id="caw" name="report_p_kod_caw" style="width:260px;" value="${actionBean.kodCaw}" disabled="true">
                        <%--     <s:option value="">Semua</s:option>--%>
                        <s:option value="">Semua</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>
                    </s:select>
                    <s:hidden name="report_p_kod_caw" value="${actionBean.kodCaw}"/>
                </p>
                <p>
                    <label>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>
                <p>
                    <label>Tarikh Tamat :</label>
                    <s:text id="trh_tamat" name="report_p_trh_tamat" class="datepicker" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>
                <p>
                    <label>Id Pengguna:</label>
                    <s:select id="id_pguna" name="report_p_id_pguna" style="width:260px;">
                        <s:option value="">Semua</s:option>
                        <s:options-collection collection="${actionBean.senaraiPenggunaCaw}" value="idPengguna" label="nama"/>
                    </s:select>
                </p>
                <p align="center">
                <s:button name="" id="simpan" value="Papar" class="btn" onclick="doSubmitHarian(this.form);"/>&nbsp;
                </p>
            </c:if>

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
