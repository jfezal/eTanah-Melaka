<%-- 
    Document   : laporan_param_pembangunan
    Created on : 17 DEC 2013, 12:45:21 PM
    Author     : syafiq adam
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
    $(document).ready( function(){
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        //$("#trh_mula").val(date.getDate()+"/"+(date.getMonth()+1)+"/"+date.getFullYear());
        //$("#trh_tamat").val(date.getDate()+"/"+(date.getMonth()+1)+"/"+date.getFullYear());
    });

    function doSubmit(f){
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top:  ($(window).height() - 50) /2 + 'px',
                left: ($(window).width() - 50) /2 + 'px',
                width: '50px'
            }
        });

        var form = $(f).formSerialize();
        f.action = "${pageContext.request.contextPath}/reportGeneratorServlet?" + form;
        f.submit();
        
        //        var report = '${actionBean.reportName}';
        //        
        //       
        //        var url = form.replace(/&/g, "%26");
        //        window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url="+url, "eTanah",
        //        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
        //        $.unblockUI();     
    }
                  
    function dateValidation(id, value){
        var vsplit = value.split('/');
        var fulldate = vsplit[1]+'/'+vsplit[0]+'/'+vsplit[2];
        var today = new Date();
        var sdate = new Date(fulldate);
        if (sdate > today){
            alert("Tarikh yang dimasukkan tidak sesuai.");
            $('#'+id).val("");
        }
    }       
</script>

<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif"
     width="50" height="50" style="display: none" alt=""/>

<s:form beanclass="etanah.view.pembangunan.laporan.PembangunanLaporanActionBean">
    <div id="display" class="subtitle">
        <s:hidden id="reportname" name="reportName"/>
        <s:hidden id="report_p_urusan" name="report_p_urusan" value="${actionBean.urusan}"/>
        <fieldset class="aras1">

            <lagend>
                <c:if test="${actionBean.reportName eq 'dev_laporan_tsptd.rdf'}">
                    Laporan Permohonan Tukar Syarat Kelulusan PTD (TSPTD)
                </c:if>
                <c:if test="${actionBean.reportName eq 'dev_laporan_tsptg.rdf'}">
                    Laporan Permohonan Tukar Syarat Kelulusan PTG (TSPTG)
                </c:if>
                <c:if test="${actionBean.reportName eq 'dev_laporan_tsmmk.rdf'}">
                    Laporan Permohonan Tukar Syarat Kelulusan MMK (TSMMK)
                </c:if>
                <c:if test="${actionBean.reportName eq 'dev_laporan_pytn.rdf'}">
                    Laporan Permohonan Penyatuan Tanah (PYTN)
                </c:if>
                <c:if test="${actionBean.reportName eq 'dev_laporan_ppcb.rdf'}">
                    Laporan Permohonan Pecah Bahagi (PPCB)
                </c:if>
                <c:if test="${actionBean.reportName eq 'dev_laporan_ppcba.rdf'}">
                    Laporan Permohonan Pecah Bahagi Seksyen 141A (PPCBA)
                </c:if>
                <c:if test="${actionBean.reportName eq 'dev_laporan_ppcs.rdf'}">
                    Laporan Permohonan Pecah Sempadan (PPCS)
                </c:if>
                <c:if test="${actionBean.reportName eq 'dev_laporan_tspss.rdf'}">
                    Laporan Permohonan Tukar Syarat Pecah Sempadan Serentak (TSPSS)
                </c:if>
                <c:if test="${actionBean.reportName eq 'dev_laporan_sbps.rdf'}">
                    Laporan Permohonan Serahbalik Pohon Semula (SBPS)
                </c:if>
                <c:if test="${actionBean.reportName eq 'dev_laporan_sbms.rdf'}">
                    Laporan Permohonan Serahbalik Berimilik Semula (SBMS)
                </c:if>
                <c:if test="${actionBean.reportName eq 'dev_laporan_psmt.rdf'}">
                    Laporan Permohonan Serahbalik Seluruh Tanah (PSMT)
                </c:if>
                <c:if test="${actionBean.reportName eq 'dev_laporan_psbt.rdf'}">
                    Laporan Permohonan Serahbalik Sebahagian Tanah (PSBT)
                </c:if>
                <c:if test="${actionBean.reportName eq 'dev_laporan_rltb.rdf'}">
                    Laporan Rayuan Lanjut Tempoh Bayaran (RLTB)
                </c:if>
                <c:if test="${actionBean.reportName eq 'dev_laporan_rps.rdf'}">
                    Laporan Rayuan Pertimbangan Semula (RPS)
                </c:if>
                <c:if test="${actionBean.reportName eq 'dev_laporan_rpp.rdf'}">
                    Laporan Rayuan Pengurangan Premium (RPP)
                </c:if>
                <c:if test="${actionBean.reportName eq 'dev_laporan_statistik.rdf'}">
                    Statistik Untuk Setiap Urusan (Statistik)
                </c:if>
                <c:if test="${actionBean.reportName eq 'dev_laporan_kemajuanbulanan.rdf'}">
                    Laporan Kemajuan Bulanan (Laporan)
                </c:if>
            </lagend>         
            <c:set value="${actionBean.reportName}" var="reportname"/>
            
            <%--
            <c:set var="disable" value="false"/>
            <c:if test="${actionBean.pengguna.kodCawangan.kod eq '00'}">
                <c:set var="disable" value="true"/>
            </c:if>
            --%>

            <p>
                <label>Cawangan :</label>
                <c:if test="${actionBean.pengguna.kodCawangan.kod eq '00'}">
                    <s:select name="report_p_kodcaw" value="${actionBean.pengguna.kodCawangan.kod}">
                        <s:option value="">Semua</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>
                    </s:select>
                </c:if>

                <c:if test="${actionBean.pengguna.kodCawangan.kod ne '00'}">
                    ${actionBean.pengguna.kodCawangan.name}
                    <s:hidden name="report_p_kodcaw" value="${actionBean.pengguna.kodCawangan.kod}"/>
                </c:if>             
            </p>
            <%--
            <s:select id="caw" name="report_p_kodcaw" style="width:260px;" disabled="${disable}">
                <s:option value="">Semua</s:option>
                <s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>
            </s:select>
            --%>
            <p>
                <label>Tarikh Mula :</label>
                <s:text id="trh_mula" name="report_p_tarikhmula" class="datepicker" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
            </p>
            <p>
                <label>Tarikh Tamat :</label>
                <s:text id="trh_tamat" name="report_p_tarikhakhir" class="datepicker" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
            </p>
            <br>
            <p align="center">
                <s:button name="" id="simpan" value="Papar" class="btn" onclick="doSubmit(this.form);"/>&nbsp;
                <s:reset name="" value="Isi Semula" class="btn"/>&nbsp;
                <s:button name="" id="close" value="Tutup" onclick="self.close();" class="btn"/>&nbsp;
            </p>
        </fieldset>
    </div>
</s:form>
