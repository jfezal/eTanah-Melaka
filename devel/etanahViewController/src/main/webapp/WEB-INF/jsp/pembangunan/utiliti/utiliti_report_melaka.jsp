<%-- 
    Document   : utiliti_report_melaka
    Created on : Oct 22, 2013, 12:07:14 PM
    Author     : khairul.hazwan
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $('#laporanPPCB').hide();
        $('#laporanTsSama').hide();
        $('#laporanTsLainKatg').hide();
        $('#laporanTsSyaratNyata').hide();
        $('#laporanPPCS').hide();
        $('#laporanPYTN').hide();
        $('#laporanPSMT').hide();
        $('#laporanSBMS').hide();
        $('#laporanSerahBalikTanah').hide();
        $('#laporanTSPSS').hide();
        $('#laporan6Urusan').hide();
        $('#laporanStatusPermohonan').hide();
        $('#laporanKemajuanRayuan').hide();
        $('#laporanPBSK').hide();
        $('#laporan5a').hide();
        $('#laporan7g').hide();
        $('#laporan17').hide();
        $('#laporan18').hide();
        $('#laporan19').hide();
    });

    function changelaporan(value){

        if(value =="0")
        {
            $('#laporanPPCB').hide();
            $('#laporanTsSama').hide();
            $('#laporanTsLainKatg').hide();
            $('#laporanTsSyaratNyata').hide();
            $('#laporanPPCS').hide();
            $('#laporanPYTN').hide();
            $('#laporanPSMT').hide();
            $('#laporanSBMS').hide();
            $('#laporanSerahBalikTanah').hide();
            $('#laporanTSPSS').hide();
            $('#laporan6Urusan').hide();
            $('#laporanStatusPermohonan').hide();
            $('#laporanKemajuanRayuan').hide();
            $('#laporanPBSK').hide();
            $('#laporan5a').hide();
            $('#laporan7g').hide();
            $('#laporan17').hide();
            $('#laporan18').hide();
            $('#laporan19').hide();
        }
        if(value =="1")
        {
            $('#laporanPPCB').show();
            $('#laporanTsSama').hide();
            $('#laporanTsLainKatg').hide();
            $('#laporanTsSyaratNyata').hide();
            $('#laporanPPCS').hide();
            $('#laporanPYTN').hide();
            $('#laporanPSMT').hide();
            $('#laporanSBMS').hide();
            $('#laporanSerahBalikTanah').hide();
            $('#laporanTSPSS').hide();
            $('#laporan6Urusan').hide();
            $('#laporanStatusPermohonan').hide();
            $('#laporanKemajuanRayuan').hide();
            $('#laporanPBSK').hide();
            $('#laporan5a').hide();
            $('#laporan7g').hide();
            $('#laporan17').hide();
            $('#laporan18').hide();
            $('#laporan19').hide();
        }
        if(value =="2")
        {
            $('#laporanPPCB').hide();
            $('#laporanTsSama').show();
            $('#laporanTsLainKatg').hide();
            $('#laporanTsSyaratNyata').hide();
            $('#laporanPPCS').hide();
            $('#laporanPYTN').hide();
            $('#laporanPSMT').hide();
            $('#laporanSBMS').hide();
            $('#laporanSerahBalikTanah').hide();
            $('#laporanTSPSS').hide();
            $('#laporan6Urusan').hide();
            $('#laporanStatusPermohonan').hide();
            $('#laporanKemajuanRayuan').hide();
            $('#laporanPBSK').hide();
            $('#laporan5a').hide();
            $('#laporan7g').hide();
            $('#laporan17').hide();
            $('#laporan18').hide();
            $('#laporan19').hide();
        }
        if(value =="3")
        {
            $('#laporanPPCB').hide();
            $('#laporanTsSama').hide();
            $('#laporanTsLainKatg').show();
            $('#laporanTsSyaratNyata').hide();
            $('#laporanPPCS').hide();
            $('#laporanPYTN').hide();
            $('#laporanPSMT').hide();
            $('#laporanSBMS').hide();
            $('#laporanSerahBalikTanah').hide();
            $('#laporanTSPSS').hide();
            $('#laporan6Urusan').hide();
            $('#laporanStatusPermohonan').hide();
            $('#laporanKemajuanRayuan').hide();
            $('#laporanPBSK').hide();
            $('#laporan5a').hide();
            $('#laporan7g').hide();
            $('#laporan17').hide();
            $('#laporan18').hide();
            $('#laporan19').hide();
        }
        if(value =="4")
        {
            $('#laporanPPCB').hide();
            $('#laporanTsSama').hide();
            $('#laporanTsLainKatg').hide();
            $('#laporanTsSyaratNyata').show();
            $('#laporanPPCS').hide();
            $('#laporanPYTN').hide();
            $('#laporanPSMT').hide();
            $('#laporanSBMS').hide();
            $('#laporanSerahBalikTanah').hide();
            $('#laporanTSPSS').hide();
            $('#laporan6Urusan').hide();
            $('#laporanStatusPermohonan').hide();
            $('#laporanKemajuanRayuan').hide();
            $('#laporanPBSK').hide();
            $('#laporan5a').hide();
            $('#laporan7g').hide();
            $('#laporan17').hide();
            $('#laporan18').hide();
            $('#laporan19').hide();
        }
        if(value =="5")
        {
            $('#laporanPPCB').hide();
            $('#laporanTsSama').hide();
            $('#laporanTsLainKatg').hide();
            $('#laporanTsSyaratNyata').hide();
            $('#laporanPPCS').show();
            $('#laporanPYTN').hide();
            $('#laporanPSMT').hide();
            $('#laporanSBMS').hide();
            $('#laporanSerahBalikTanah').hide();
            $('#laporanTSPSS').hide();
            $('#laporan6Urusan').hide();
            $('#laporanStatusPermohonan').hide();
            $('#laporanKemajuanRayuan').hide();
            $('#laporanPBSK').hide();
            $('#laporan5a').hide();
            $('#laporan7g').hide();
            $('#laporan17').hide();
            $('#laporan18').hide();
            $('#laporan19').hide();
        }
        if(value =="6")
        {
            $('#laporanPPCB').hide();
            $('#laporanTsSama').hide();
            $('#laporanTsLainKatg').hide();
            $('#laporanTsSyaratNyata').hide();
            $('#laporanPPCS').hide();
            $('#laporanPYTN').show();
            $('#laporanPSMT').hide();
            $('#laporanSBMS').hide();
            $('#laporanSerahBalikTanah').hide();
            $('#laporanTSPSS').hide();
            $('#laporan6Urusan').hide();
            $('#laporanStatusPermohonan').hide();
            $('#laporanKemajuanRayuan').hide();
            $('#laporanPBSK').hide();
            $('#laporan5a').hide();
            $('#laporan7g').hide();
            $('#laporan17').hide();
            $('#laporan18').hide();
            $('#laporan19').hide();
        }

        if(value =="7")
        {
            $('#laporanPPCB').hide();
            $('#laporanTsSama').hide();
            $('#laporanTsLainKatg').hide();
            $('#laporanTsSyaratNyata').hide();
            $('#laporanPPCS').hide();
            $('#laporanPYTN').hide();
            $('#laporanPSMT').show();
            $('#laporanSBMS').hide();
            $('#laporanSerahBalikTanah').hide();
            $('#laporanTSPSS').hide();
            $('#laporan6Urusan').hide();
            $('#laporanStatusPermohonan').hide();
            $('#laporanKemajuanRayuan').hide();
            $('#laporanPBSK').hide();
            $('#laporan5a').hide();
            $('#laporan7g').hide();
            $('#laporan17').hide();
            $('#laporan18').hide();
            $('#laporan19').hide();
        }
        if(value =="8")
        {
            $('#laporanPPCB').hide();
            $('#laporanTsSama').hide();
            $('#laporanTsLainKatg').hide();
            $('#laporanTsSyaratNyata').hide();
            $('#laporanPPCS').hide();
            $('#laporanPYTN').hide();
            $('#laporanPSMT').hide();
            $('#laporanSBMS').show();
            $('#laporanSerahBalikTanah').hide();
            $('#laporanTSPSS').hide();
            $('#laporan6Urusan').hide();
            $('#laporanStatusPermohonan').hide();
            $('#laporanKemajuanRayuan').hide();
            $('#laporanPBSK').hide();
            $('#laporan5a').hide();
            $('#laporan7g').hide();
            $('#laporan17').hide();
            $('#laporan18').hide();
            $('#laporan19').hide();
        }   
        if(value =="9")
        {
            $('#laporanPPCB').hide();
            $('#laporanTsSama').hide();
            $('#laporanTsLainKatg').hide();
            $('#laporanTsSyaratNyata').hide();
            $('#laporanPPCS').hide();
            $('#laporanPYTN').hide();
            $('#laporanPSMT').hide();
            $('#laporanSBMS').hide();
            $('#laporanSerahBalikTanah').show();
            $('#laporanTSPSS').hide();
            $('#laporan6Urusan').hide();
            $('#laporanStatusPermohonan').hide();
            $('#laporanKemajuanRayuan').hide();
            $('#laporanPBSK').hide();
            $('#laporan5a').hide();
            $('#laporan7g').hide();
            $('#laporan17').hide();
            $('#laporan18').hide();
            $('#laporan19').hide();
        }  
        if(value =="10")
        {
            $('#laporanPPCB').hide();
            $('#laporanTsSama').hide();
            $('#laporanTsLainKatg').hide();
            $('#laporanTsSyaratNyata').hide();
            $('#laporanPPCS').hide();
            $('#laporanPYTN').hide();
            $('#laporanPSMT').hide();
            $('#laporanSBMS').hide();
            $('#laporanSerahBalikTanah').hide();
            $('#laporanTSPSS').show();
            $('#laporan6Urusan').hide();
            $('#laporanStatusPermohonan').hide();
            $('#laporanKemajuanRayuan').hide();
            $('#laporanPBSK').hide();
            $('#laporan5a').hide();
            $('#laporan7g').hide();
            $('#laporan17').hide();
            $('#laporan18').hide();
            $('#laporan19').hide();
        }    
        if(value =="11")
        {
            $('#laporanPPCB').hide();
            $('#laporanTsSama').hide();
            $('#laporanTsLainKatg').hide();
            $('#laporanTsSyaratNyata').hide();
            $('#laporanPPCS').hide();
            $('#laporanPYTN').hide();
            $('#laporanPSMT').hide();
            $('#laporanSBMS').hide();
            $('#laporanSerahBalikTanah').hide();
            $('#laporanTSPSS').hide();
            $('#laporan6Urusan').show();
            $('#laporanStatusPermohonan').hide();
            $('#laporanKemajuanRayuan').hide();
            $('#laporanPBSK').hide();
            $('#laporan5a').hide();
            $('#laporan7g').hide();
            $('#laporan17').hide();
            $('#laporan18').hide();
            $('#laporan19').hide();
        }  
        if(value =="12")
        {
            $('#laporanPPCB').hide();
            $('#laporanTsSama').hide();
            $('#laporanTsLainKatg').hide();
            $('#laporanTsSyaratNyata').hide();
            $('#laporanPPCS').hide();
            $('#laporanPYTN').hide();
            $('#laporanPSMT').hide();
            $('#laporanSBMS').hide();
            $('#laporanSerahBalikTanah').hide();
            $('#laporanTSPSS').hide();
            $('#laporan6Urusan').hide();
            $('#laporanStatusPermohonan').show();
            $('#laporanKemajuanRayuan').hide();
            $('#laporanPBSK').hide();
            $('#laporan5a').hide();
            $('#laporan7g').hide();
            $('#laporan17').hide();
            $('#laporan18').hide();
            $('#laporan19').hide();
        }  
        if(value =="13")
        {
            $('#laporanPPCB').hide();
            $('#laporanTsSama').hide();
            $('#laporanTsLainKatg').hide();
            $('#laporanTsSyaratNyata').hide();
            $('#laporanPPCS').hide();
            $('#laporanPYTN').hide();
            $('#laporanPSMT').hide();
            $('#laporanSBMS').hide();
            $('#laporanSerahBalikTanah').hide();
            $('#laporanTSPSS').hide();
            $('#laporan6Urusan').hide();
            $('#laporanStatusPermohonan').hide();
            $('#laporanKemajuanRayuan').show();
            $('#laporanPBSK').hide();
            $('#laporan5a').hide();
            $('#laporan7g').hide();
            $('#laporan17').hide();
            $('#laporan18').hide();
            $('#laporan19').hide();
        }  
        if(value =="14")
        {
            $('#laporanPPCB').hide();
            $('#laporanTsSama').hide();
            $('#laporanTsLainKatg').hide();
            $('#laporanTsSyaratNyata').hide();
            $('#laporanPPCS').hide();
            $('#laporanPYTN').hide();
            $('#laporanPSMT').hide();
            $('#laporanSBMS').hide();
            $('#laporanSerahBalikTanah').hide();
            $('#laporanTSPSS').hide();
            $('#laporan6Urusan').hide();
            $('#laporanStatusPermohonan').hide();
            $('#laporanKemajuanRayuan').hide();
            $('#laporanPBSK').show();
            $('#laporan5a').hide();
            $('#laporan7g').hide();
            $('#laporan17').hide();
            $('#laporan18').hide();
            $('#laporan19').hide();
        }  
        if(value =="15")
        {
            $('#laporanPPCB').hide();
            $('#laporanTsSama').hide();
            $('#laporanTsLainKatg').hide();
            $('#laporanTsSyaratNyata').hide();
            $('#laporanPPCS').hide();
            $('#laporanPYTN').hide();
            $('#laporanPSMT').hide();
            $('#laporanSBMS').hide();
            $('#laporanSerahBalikTanah').hide();
            $('#laporanTSPSS').hide();
            $('#laporan6Urusan').hide();
            $('#laporanStatusPermohonan').hide();
            $('#laporanKemajuanRayuan').hide();
            $('#laporanPBSK').hide();
            $('#laporan5a').show();
            $('#laporan7g').hide();
            $('#laporan17').hide();
            $('#laporan18').hide();
            $('#laporan19').hide();
        }  
        if(value =="16")
        {
            $('#laporanPPCB').hide();
            $('#laporanTsSama').hide();
            $('#laporanTsLainKatg').hide();
            $('#laporanTsSyaratNyata').hide();
            $('#laporanPPCS').hide();
            $('#laporanPYTN').hide();
            $('#laporanPSMT').hide();
            $('#laporanSBMS').hide();
            $('#laporanSerahBalikTanah').hide();
            $('#laporanTSPSS').hide();
            $('#laporan6Urusan').hide();
            $('#laporanStatusPermohonan').hide();
            $('#laporanKemajuanRayuan').hide();
            $('#laporanPBSK').hide();
            $('#laporan5a').hide();
            $('#laporan7g').show();
            $('#laporan17').hide();
            $('#laporan18').hide();
            $('#laporan19').hide();
        }  
        if(value =="17")
        {
            $('#laporanPPCB').hide();
            $('#laporanTsSama').hide();
            $('#laporanTsLainKatg').hide();
            $('#laporanTsSyaratNyata').hide();
            $('#laporanPPCS').hide();
            $('#laporanPYTN').hide();
            $('#laporanPSMT').hide();
            $('#laporanSBMS').hide();
            $('#laporanSerahBalikTanah').hide();
            $('#laporanTSPSS').hide();
            $('#laporan6Urusan').hide();
            $('#laporanStatusPermohonan').hide();
            $('#laporanKemajuanRayuan').hide();
            $('#laporanPBSK').hide();
            $('#laporan5a').hide();
            $('#laporan7g').hide();
            $('#laporan17').show();
            $('#laporan18').hide();
            $('#laporan19').hide();
        }  
        if(value =="18")
        {
            $('#laporanPPCB').hide();
            $('#laporanTsSama').hide();
            $('#laporanTsLainKatg').hide();
            $('#laporanTsSyaratNyata').hide();
            $('#laporanPPCS').hide();
            $('#laporanPYTN').hide();
            $('#laporanPSMT').hide();
            $('#laporanSBMS').hide();
            $('#laporanSerahBalikTanah').hide();
            $('#laporanTSPSS').hide();
            $('#laporan6Urusan').hide();
            $('#laporanStatusPermohonan').hide();
            $('#laporanKemajuanRayuan').hide();
            $('#laporanPBSK').hide();
            $('#laporan5a').hide();
            $('#laporan7g').hide();
            $('#laporan17').hide();
            $('#laporan18').show();
            $('#laporan19').hide();
        }  
        if(value =="19")
        {
            $('#laporanPPCB').hide();
            $('#laporanTsSama').hide();
            $('#laporanTsLainKatg').hide();
            $('#laporanTsSyaratNyata').hide();
            $('#laporanPPCS').hide();
            $('#laporanPYTN').hide();
            $('#laporanPSMT').hide();
            $('#laporanSBMS').hide();
            $('#laporanSerahBalikTanah').hide();
            $('#laporanTSPSS').hide();
            $('#laporan6Urusan').hide();
            $('#laporanStatusPermohonan').hide();
            $('#laporanKemajuanRayuan').hide();
            $('#laporanPBSK').hide();
            $('#laporan5a').hide();
            $('#laporan7g').hide();
            $('#laporan17').hide();
            $('#laporan18').hide();
            $('#laporan19').show();
        }  
     }
    
    function popup(url)
    {
        params  = 'width='+screen.width;
        params += ', height='+screen.height;
        params += ', top=0, left=0'
        params += ', fullscreen=yes';
        params += ', directories=no';
        params += ', location=no';
        params += ', menubar=no';
        params += ', resizable=no';
        params += ', scrollbars=yes';
        params += ', status=no';
        params += ', toolbar=no';
        newwin=window.open(url,'PopUp', params);
        if (window.focus) {newwin.focus()}
        return false;
    }
</script>

<div id="laporan">
    <s:form beanclass="etanah.view.stripes.pembangunan.utiliti.UtilitiReportActionBean">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Laporan Utiliti Modul Pembangunan
                </legend>
                <p style="color:red">
                    *Sila Pilih Senarai Bagi Modul Pembangunan<br/>
                </p>
                <br>
                <p>
                <center>
                    <label>Senarai Laporan :</label>                   
                    <br><br>
                        <s:select name="senaraiLaporan"  onchange="javaScript:changelaporan(this.value)">
                            <s:option value="0">--Sila Pilih--</s:option>
                            <s:option value="1">Laporan Kemajuan Pecah Bahagian</s:option>
                            <s:option value="2">Laporan Permohonan Tukar Syarat Sama Kategori</s:option>
                            <s:option value="3">Laporan Permohonan Tukar Syarat Lain Kategori</s:option>
                            <s:option value="4">Laporan Permohonan Tukar Syarat Nyata</s:option>    
                            <s:option value="5">Laporan Kemajuan Permohonan Pecah Sempadan</s:option>  
                            <s:option value="6">Laporan Kemajuan Permohonan Penyatuan Tanah</s:option>  
                            <s:option value="7">Laporan Kemajuan Permohonan Serah Semua Tanah</s:option>  
                            <s:option value="8">Laporan Kemajuan Permohonan Penyerahan Balik Dan Pemberimilikan Semula</s:option>  
                            <s:option value="9">Laporan Kemajuan Permohonan Penyerahan Balik Tanah</s:option>  
                            <s:option value="10">Laporan Kemajuan Permohonan Pecah Sempadan Dan Tukar Syarat Serentak</s:option>  
                            <s:option value="11">Laporan Kemajuan Permohonan Pecah Sempadan Dan 5 Urusan Lain</s:option>  
                            <s:option value="12">Laporan Status Permohonan</s:option>  
                            <s:option value="13">Laporan Kemajuan Permohonan Rayuan Pembangunan Tanah</s:option>  
                            <s:option value="14">Laporan Kemajuan Permohonan Sekatan Kepentingan</s:option>  
                            <s:option value="15">Laporan Pembayaran Premium (5A) Pembangunan Tanah</s:option>  
                            <s:option value="16">Laporan Pembayaran Premium (7G) Pembangunan Tanah</s:option>  
                            <s:option value="17">Laporan Permohonan Meminda/Membatal/Mengena/Menghapus Sekatan Kepentingan Bagi Bilangan Permohonan Mengikut Tahun/Bulan/Minggu</s:option> 
                            <s:option value="18">Laporan Permohonan Meminda/Membatal/Mengena/Menghapus Sekatan Kepentingan Bagi Bilangan Permohonan Mengikut Individu/Syarikat</s:option> 
                            <s:option value="19">Laporan Permohonan Meminda/Membatal/Mengena/Menghapus Bagi Status Permohonan Tugasan Siap Disetiap Peringkat</s:option>  
                        </s:select>  
                </center>
                </p>
                <br>
            </fieldset>    
        </div>
        <br>
        
        <!--Laporan Kemajuan Pecah Bahagian-->               
        <div class="subtitle" id="laporanPPCB">
            <fieldset class="aras1">                 
                <legend>
                    Laporan Kemajuan Pecah Bahagian
                </legend>
                <p style="color:red">
                    *Sila klik pada (<img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem' id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';"/>) untuk memaparkan laporan<br/>
                </p>
                <br>
                <table class="tablecloth">
                    <tr style="width: 100%">
                        <th>Bil</th>
                        <th>Laporan</th>
                        <th>Papar</th>

                        <c:set value="1" var="count"/>
                        <c:forEach items="${actionBean.laporanPPCB}" var="report">
                        <tr>
                            <td>${count}
                            </td>
                            <td>${report}
                            </td>
                            <td><div align="center">
                                    <p align="center">
                                        <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                             onclick="popup('${pageContext.request.contextPath}/utiliti/reports/requestParam?namaReport=${actionBean.laporanPPCBReport[count-1]}&report=${report}');" onmouseover="this.style.cursor='pointer';">
                                    </p>
                                </div>
                            </td>
                        </tr>
                        <c:set value="${count +1}" var="count"/>
                    </c:forEach>
                    </tr>
                </table>
                <br>
            </fieldset>
        </div>
                        
        <!--Laporan Permohonan Tukar Syarat Sama Kategori-->                
        <div class="subtitle" id="laporanTsSama">
            <fieldset class="aras1">
                <legend>
                    Laporan Permohonan Tukar Syarat Sama Kategori
                </legend>
                <p style="color:red">
                    *Sila klik pada (<img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem' id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';"/>) untuk memaparkan laporan<br/>
                </p>
                <br>
                <table class="tablecloth">
                    <tr style="width: 100%">
                        <th>Bil</th>
                        <th>Laporan</th>
                        <th>Papar</th>

                        <c:set value="1" var="count"/>
                        <c:forEach items="${actionBean.laporanTsSama}" var="report">
                        <tr>
                            <td>${count}
                            </td>
                            <td>${report}
                            </td>
                            <td><div align="center">
                                    <p align="center">
                                        <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                             onclick="popup('${pageContext.request.contextPath}/utiliti/reports/requestParam?namaReport=${actionBean.laporanTsSamaReport[count-1]}&report=${report}');" onmouseover="this.style.cursor='pointer';">
                                    </p>
                                </div>
                            </td>
                        </tr>
                        <c:set value="${count +1}" var="count"/>
                    </c:forEach>
                    </tr>
                </table>
                <br>
            </fieldset>
        </div>
                        
        <!--Laporan Permohonan Tukar Syarat Lain Kategori-->
        <div class="subtitle" id="laporanTsLainKatg">
            <fieldset class="aras1">
                <legend>
                    Laporan Permohonan Tukar Syarat Lain Kategori
                </legend>
                <p style="color:red">
                    *Sila klik pada (<img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem' id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';"/>) untuk memaparkan laporan<br/>
                </p>
                <br>
                <table class="tablecloth">
                    <tr style="width: 100%">
                        <th>Bil</th>
                        <th>Laporan</th>
                        <th>Papar</th>

                        <c:set value="1" var="count"/>
                        <c:forEach items="${actionBean.laporanTsLainKatg}" var="report">
                        <tr>
                            <td>${count}
                            </td>
                            <td>${report}
                            </td>
                            <td><div align="center">
                                    <p align="center">
                                        <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                             onclick="popup('${pageContext.request.contextPath}/utiliti/reports/requestParam?namaReport=${actionBean.laporanTsLainKatgReport[count-1]}&report=${report}');" onmouseover="this.style.cursor='pointer';">
                                    </p>
                                </div>
                            </td>
                        </tr>
                        <c:set value="${count +1}" var="count"/>
                    </c:forEach>
                    </tr>
                </table>
                <br>
            </fieldset>
        </div>

        <!--Laporan Permohonan Tukar Syarat Nyata-->
        <div class="subtitle" id="laporanTsSyaratNyata">
            <fieldset class="aras1">
                <legend>
                    Laporan Permohonan Tukar Syarat Nyata
                </legend>
                <p style="color:red">
                    *Sila klik pada (<img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem' id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';"/>) untuk memaparkan laporan<br/>
                </p>
                <br>
                <table class="tablecloth">
                    <tr style="width: 100%">
                        <th>Bil</th>
                        <th>Laporan</th>
                        <th>Papar</th>

                        <c:set value="1" var="count"/>
                        <c:forEach items="${actionBean.laporanTsSyaratNyata}" var="report">
                        <tr>
                            <td>${count}
                            </td>
                            <td>${report}
                            </td>
                            <td><div align="center">
                                    <p align="center">
                                        <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                             onclick="popup('${pageContext.request.contextPath}/utiliti/reports/requestParam?namaReport=${actionBean.laporanTsSyaratNyataReport[count-1]}&report=${report}');" onmouseover="this.style.cursor='pointer';">
                                    </p>
                                </div>
                            </td>
                        </tr>
                        <c:set value="${count +1}" var="count"/>
                    </c:forEach>
                    </tr>
                </table>
                <br>
            </fieldset>
        </div>
                        
        <!--Laporan Kemajuan Permohonan Pecah Sempadan-->                
        <div class="subtitle" id="laporanPPCS">
            <fieldset class="aras1">
                <legend>
                    Laporan Kemajuan Permohonan Pecah Sempadan
                </legend>
                <p style="color:red">
                    *Sila klik pada (<img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem' id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';"/>) untuk memaparkan laporan<br/>
                </p>
                <br>
                <table class="tablecloth">
                    <tr style="width: 100%">
                        <th>Bil</th>
                        <th>Laporan</th>
                        <th>Papar</th>

                        <c:set value="1" var="count"/>
                        <c:forEach items="${actionBean.laporanPPCS}" var="report">
                        <tr>
                            <td>${count}
                            </td>
                            <td>${report}
                            </td>
                            <td><div align="center">
                                    <p align="center">
                                        <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                             onclick="popup('${pageContext.request.contextPath}/utiliti/reports/requestParam?namaReport=${actionBean.laporanPPCSReport[count-1]}&report=${report}');" onmouseover="this.style.cursor='pointer';">
                                    </p>
                                </div>
                            </td>
                        </tr>
                        <c:set value="${count +1}" var="count"/>
                    </c:forEach>
                    </tr>
                </table>
                <br>
            </fieldset>
        </div>
                        
        <!--Laporan Kemajuan Permohonan Penyatuan Tanah-->                
        <div class="subtitle" id="laporanPYTN">
            <fieldset class="aras1">
                <legend>
                    Laporan Kemajuan Permohonan Penyatuan Tanah
                </legend>
                <p style="color:red">
                    *Sila klik pada (<img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem' id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';"/>) untuk memaparkan laporan<br/>
                </p>
                <br>
                <table class="tablecloth">
                    <tr style="width: 100%">
                        <th>Bil</th>
                        <th>Laporan</th>
                        <th>Papar</th>

                        <c:set value="1" var="count"/>
                        <c:forEach items="${actionBean.laporanPYTN}" var="report">
                        <tr>
                            <td>${count}
                            </td>
                            <td>${report}
                            </td>
                            <td><div align="center">
                                    <p align="center">
                                        <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                             onclick="popup('${pageContext.request.contextPath}/utiliti/reports/requestParam?namaReport=${actionBean.laporanPYTNReport[count-1]}&report=${report}');" onmouseover="this.style.cursor='pointer';">
                                    </p>
                                </div>
                            </td>
                        </tr>
                        <c:set value="${count +1}" var="count"/>
                    </c:forEach>
                    </tr>
                </table>
                <br>
            </fieldset>
        </div>
                        
        <!--Laporan Kemajuan Permohonan Serah Semua Tanah-->                
        <div class="subtitle" id="laporanPSMT">
            <fieldset class="aras1">
                <legend>
                    Laporan Kemajuan Permohonan Serah Semua Tanah
                </legend>
                <p style="color:red">
                    *Sila klik pada (<img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem' id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';"/>) untuk memaparkan laporan<br/>
                </p>
                <br>
                <table class="tablecloth">
                    <tr style="width: 100%">
                        <th>Bil</th>
                        <th>Laporan</th>
                        <th>Papar</th>

                        <c:set value="1" var="count"/>
                        <c:forEach items="${actionBean.laporanPSMT}" var="report">
                        <tr>
                            <td>${count}
                            </td>
                            <td>${report}
                            </td>
                            <td><div align="center">
                                    <p align="center">
                                        <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                             onclick="popup('${pageContext.request.contextPath}/utiliti/reports/requestParam?namaReport=${actionBean.laporanPSMTReport[count-1]}&report=${report}');" onmouseover="this.style.cursor='pointer';">
                                    </p>
                                </div>
                            </td>
                        </tr>
                        <c:set value="${count +1}" var="count"/>
                    </c:forEach>
                    </tr>
                </table>
                <br>
            </fieldset>
        </div>
                                  
        <!--Laporan Kemajuan Permohonan Penyerahan Balik Dan Pemberimilikan Semula-->
        <div class="subtitle" id="laporanSBMS">
            <fieldset class="aras1">
                <legend>
                    Laporan Kemajuan Permohonan Penyerahan Balik Dan Pemberimilikan Semula
                </legend>
                <p style="color:red">
                    *Sila klik pada (<img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem' id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';"/>) untuk memaparkan laporan<br/>
                </p>
                <br>
                <table class="tablecloth">
                    <tr style="width: 100%">
                        <th>Bil</th>
                        <th>Laporan</th>
                        <th>Papar</th>

                        <c:set value="1" var="count"/>
                        <c:forEach items="${actionBean.laporanSBMS}" var="report">
                        <tr>
                            <td>${count}
                            </td>
                            <td>${report}
                            </td>
                            <td><div align="center">
                                    <p align="center">
                                        <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                             onclick="popup('${pageContext.request.contextPath}/utiliti/reports/requestParam?namaReport=${actionBean.laporanSBMSReport[count-1]}&report=${report}');" onmouseover="this.style.cursor='pointer';">
                                    </p>
                                </div>
                            </td>
                        </tr>
                        <c:set value="${count +1}" var="count"/>
                    </c:forEach>
                    </tr>
                </table>
                <br>
            </fieldset>
        </div>
                                               
        <!--Laporan Kemajuan Permohonan Penyerahan Balik Tanah-->
        <div class="subtitle" id="laporanSerahBalikTanah">
            <fieldset class="aras1">
                <legend>
                    Laporan Kemajuan Permohonan Penyerahan Balik Tanah
                </legend>
                <p style="color:red">
                    *Sila klik pada (<img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem' id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';"/>) untuk memaparkan laporan<br/>
                </p>
                <br>
                <table class="tablecloth">
                    <tr style="width: 100%">
                        <th>Bil</th>
                        <th>Laporan</th>
                        <th>Papar</th>

                        <c:set value="1" var="count"/>
                        <c:forEach items="${actionBean.laporanSerahBalikTanah}" var="report">
                        <tr>
                            <td>${count}
                            </td>
                            <td>${report}
                            </td>
                            <td><div align="center">
                                    <p align="center">
                                        <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                             onclick="popup('${pageContext.request.contextPath}/utiliti/reports/requestParam?namaReport=${actionBean.laporanSerahBalikTanahReport[count-1]}&report=${report}');" onmouseover="this.style.cursor='pointer';">
                                    </p>
                                </div>
                            </td>
                        </tr>
                        <c:set value="${count +1}" var="count"/>
                    </c:forEach>
                    </tr>
                </table>
                <br>
            </fieldset>
        </div>
                        
        <!--Laporan Kemajuan Permohonan Pecah Sempadan Dan Tukar Syarat Serentak-->
        <div class="subtitle" id="laporanTSPSS">
            <fieldset class="aras1">
                <legend>
                    Laporan Kemajuan Permohonan Pecah Sempadan Dan Tukar Syarat Serentak
                </legend>
                <p style="color:red">
                    *Sila klik pada (<img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem' id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';"/>) untuk memaparkan laporan<br/>
                </p>
                <br>
                <table class="tablecloth">
                    <tr style="width: 100%">
                        <th>Bil</th>
                        <th>Laporan</th>
                        <th>Papar</th>

                        <c:set value="1" var="count"/>
                        <c:forEach items="${actionBean.laporanTSPSS}" var="report">
                        <tr>
                            <td>${count}
                            </td>
                            <td>${report}
                            </td>
                            <td><div align="center">
                                    <p align="center">
                                        <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                             onclick="popup('${pageContext.request.contextPath}/utiliti/reports/requestParam?namaReport=${actionBean.laporanTSPSSReport[count-1]}&report=${report}');" onmouseover="this.style.cursor='pointer';">
                                    </p>
                                </div>
                            </td>
                        </tr>
                        <c:set value="${count +1}" var="count"/>
                    </c:forEach>
                    </tr>
                </table>
                <br>
            </fieldset>
        </div>
                        
        <!--Laporan Kemajuan Permohonan Pecah Sempadan Dan 5 Urusan Lain-->
        <div class="subtitle" id="laporan6Urusan">
            <fieldset class="aras1">
                <legend>
                    Laporan Kemajuan Permohonan Pecah Sempadan/Pecah Bahagian/Penyatuan Tanah/ 
                    Pecah Sempadan Dan Tukar Syarat Serentak/Penyerahan Balik Dan Pemberimilikan Semula/Tukar Syarat
                </legend>
                <p style="color:red">
                    *Sila klik pada (<img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem' id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';"/>) untuk memaparkan laporan<br/>
                </p>
                <br>
                <table class="tablecloth">
                    <tr style="width: 100%">
                        <th>Bil</th>
                        <th>Laporan</th>
                        <th>Papar</th>

                        <c:set value="1" var="count"/>
                        <c:forEach items="${actionBean.laporan6Urusan}" var="report">
                        <tr>
                            <td>${count}
                            </td>
                            <td>${report}
                            </td>
                            <td><div align="center">
                                    <p align="center">
                                        <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                             onclick="popup('${pageContext.request.contextPath}/utiliti/reports/requestParam?namaReport=${actionBean.laporan6UrusanReport[count-1]}&report=${report}');" onmouseover="this.style.cursor='pointer';">
                                    </p>
                                </div>
                            </td>
                        </tr>
                        <c:set value="${count +1}" var="count"/>
                    </c:forEach>
                    </tr>
                </table>
                <br>
            </fieldset>
        </div>
          
        <!--Laporan Status Permohonan-->
        <div class="subtitle" id="laporanStatusPermohonan">
            <fieldset class="aras1">
                <legend>
                    Status Permohonan Pecah Sempadan/Pecah Bahagian/Penyatuan Tanah/Pecah Sempadan Dan Tukar Syarat Serentak/Penyerahan Balik
                    Dan Pemberimilikan Semula/Tukar Syarat/Serahbalik Semua Tanah/Serahbalik Sebahagian Tanah
                </legend>
                <p style="color:red">
                    *Sila klik pada (<img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem' id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';"/>) untuk memaparkan laporan<br/>
                </p>
                <br>
                <table class="tablecloth">
                    <tr style="width: 100%">
                        <th>Bil</th>
                        <th>Laporan</th>
                        <th>Papar</th>

                        <c:set value="1" var="count"/>
                        <c:forEach items="${actionBean.laporanStatusPermohonan}" var="report">
                        <tr>
                            <td>${count}
                            </td>
                            <td>${report}
                            </td>
                            <td><div align="center">
                                    <p align="center">
                                        <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                             onclick="popup('${pageContext.request.contextPath}/utiliti/reports/requestParam?namaReport=${actionBean.laporanStatusPermohonanReport[count-1]}&report=${report}');" onmouseover="this.style.cursor='pointer';">
                                    </p>
                                </div>
                            </td>
                        </tr>
                        <c:set value="${count +1}" var="count"/>
                    </c:forEach>
                    </tr>
                </table>
                <br>
            </fieldset>
        </div>
                        
        <!--Laporan Kemajuan Permohonan Rayuan Pembangunan Tanah-->
        <div class="subtitle" id="laporanKemajuanRayuan">
            <fieldset class="aras1">
                <legend>
                    Laporan Kemajuan Permohonan Rayuan Pembangunan Tanah
                </legend>
                <p style="color:red">
                    *Sila klik pada (<img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem' id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';"/>) untuk memaparkan laporan<br/>
                </p>
                <br>
                <table class="tablecloth">
                    <tr style="width: 100%">
                        <th>Bil</th>
                        <th>Laporan</th>
                        <th>Papar</th>

                        <c:set value="1" var="count"/>
                        <c:forEach items="${actionBean.laporanKemajuanRayuan}" var="report">
                        <tr>
                            <td>${count}
                            </td>
                            <td>${report}
                            </td>
                            <td><div align="center">
                                    <p align="center">
                                        <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                             onclick="popup('${pageContext.request.contextPath}/utiliti/reports/requestParam?namaReport=${actionBean.laporanKemajuanRayuanReport[count-1]}&report=${report}');" onmouseover="this.style.cursor='pointer';">
                                    </p>
                                </div>
                            </td>
                        </tr>
                        <c:set value="${count +1}" var="count"/>
                    </c:forEach>
                    </tr>
                </table>
                <br>
            </fieldset>
        </div>
                        
        <!--Laporan Kemajuan Permohonan Sekatan Kepentingan-->
        <div class="subtitle" id="laporanPBSK">
            <fieldset class="aras1">
                <legend>
                    Laporan Kemajuan Permohonan Sekatan Kepentingan
                </legend>
                <p style="color:red">
                    *Sila klik pada (<img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem' id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';"/>) untuk memaparkan laporan<br/>
                </p>
                <br>
                <table class="tablecloth">
                    <tr style="width: 100%">
                        <th>Bil</th>
                        <th>Laporan</th>
                        <th>Papar</th>

                        <c:set value="1" var="count"/>
                        <c:forEach items="${actionBean.laporanPBSK}" var="report">
                        <tr>
                            <td>${count}
                            </td>
                            <td>${report}
                            </td>
                            <td><div align="center">
                                    <p align="center">
                                        <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                             onclick="popup('${pageContext.request.contextPath}/utiliti/reports/requestParam?namaReport=${actionBean.laporanPBSKReport[count-1]}&report=${report}');" onmouseover="this.style.cursor='pointer';">
                                    </p>
                                </div>
                            </td>
                        </tr>
                        <c:set value="${count +1}" var="count"/>
                    </c:forEach>
                    </tr>
                </table>
                <br>
            </fieldset>
        </div>
                     
        <!--Laporan Pembayaran Premium (5A) Pembangunan Tanah-->
        <div class="subtitle" id="laporan5a">
            <fieldset class="aras1">
                <legend>
                    Laporan Pembayaran Premium (5A) Pembangunan Tanah
                </legend>
                <p style="color:red">
                    *Sila klik pada (<img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem' id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';"/>) untuk memaparkan laporan<br/>
                </p>
                <br>
                <table class="tablecloth">
                    <tr style="width: 100%">
                        <th>Bil</th>
                        <th>Laporan</th>
                        <th>Papar</th>

                        <c:set value="1" var="count"/>
                        <c:forEach items="${actionBean.laporan5a}" var="report">
                        <tr>
                            <td>${count}
                            </td>
                            <td>${report}
                            </td>
                            <td><div align="center">
                                    <p align="center">
                                        <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                             onclick="popup('${pageContext.request.contextPath}/utiliti/reports/requestParam?namaReport=${actionBean.laporan5aReport[count-1]}&report=${report}');" onmouseover="this.style.cursor='pointer';">
                                    </p>
                                </div>
                            </td>
                        </tr>
                        <c:set value="${count +1}" var="count"/>
                    </c:forEach>
                    </tr>
                </table>
                <br>
            </fieldset>
        </div>
                        
        <!--Laporan Pembayaran Premium (7G) Pembangunan Tanah-->
        <div class="subtitle" id="laporan7g">
            <fieldset class="aras1">
                <legend>
                    Laporan Pembayaran Premium (7G) Pembangunan Tanah
                </legend>
                <p style="color:red">
                    *Sila klik pada (<img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem' id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';"/>) untuk memaparkan laporan<br/>
                </p>
                <br>
                <table class="tablecloth">
                    <tr style="width: 100%">
                        <th>Bil</th>
                        <th>Laporan</th>
                        <th>Papar</th>

                        <c:set value="1" var="count"/>
                        <c:forEach items="${actionBean.laporan7g}" var="report">
                        <tr>
                            <td>${count}
                            </td>
                            <td>${report}
                            </td>
                            <td><div align="center">
                                    <p align="center">
                                        <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                             onclick="popup('${pageContext.request.contextPath}/utiliti/reports/requestParam?namaReport=${actionBean.laporan7gReport[count-1]}&report=${report}');" onmouseover="this.style.cursor='pointer';">
                                    </p>
                                </div>
                            </td>
                        </tr>
                        <c:set value="${count +1}" var="count"/>
                    </c:forEach>
                    </tr>
                </table>
                <br>
            </fieldset>
        </div>
                        
        <!--Laporan Permohonan Meminda/Membatal/Mengena/Menghapus Sekatan Kepentingan Bagi Bilangan Permohonan Mengikut Tahun/Bulan/Minggu-->
        <div class="subtitle" id="laporan17">
            <fieldset class="aras1">
                <legend>
                    Laporan Permohonan Meminda/Membatal/Mengena/Menghapus Sekatan Kepentingan Bagi Bilangan Permohonan Mengikut Tahun/Bulan/Minggu
                </legend>
                <p style="color:red">
                    *Sila klik pada (<img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem' id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';"/>) untuk memaparkan laporan<br/>
                </p>
                <br>
                <table class="tablecloth">
                    <tr style="width: 100%">
                        <th>Bil</th>
                        <th>Laporan</th>
                        <th>Papar</th>

                        <c:set value="1" var="count"/>
                        <c:forEach items="${actionBean.laporan17}" var="report">
                        <tr>
                            <td>${count}
                            </td>
                            <td>${report}
                            </td>
                            <td><div align="center">
                                    <p align="center">
                                        <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                             onclick="popup('${pageContext.request.contextPath}/utiliti/reports/requestParam?namaReport=${actionBean.laporan17Report[count-1]}&report=${report}');" onmouseover="this.style.cursor='pointer';">
                                    </p>
                                </div>
                            </td>
                        </tr>
                        <c:set value="${count +1}" var="count"/>
                    </c:forEach>
                    </tr>
                </table>
                <br>
            </fieldset>
        </div>
                        
        <!--Laporan Permohonan Meminda/Membatal/Mengena/Menghapus Sekatan Kepentingan Bagi Bilangan Permohonan Mengikut Individu/Syarikat-->
        <div class="subtitle" id="laporan18">
            <fieldset class="aras1">
                <legend>
                    Laporan Permohonan Meminda/Membatal/Mengena/Menghapus Sekatan Kepentingan Bagi Bilangan Permohonan Mengikut Individu/Syarikat
                </legend>
                <p style="color:red">
                    *Sila klik pada (<img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem' id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';"/>) untuk memaparkan laporan<br/>
                </p>
                <br>
                <table class="tablecloth">
                    <tr style="width: 100%">
                        <th>Bil</th>
                        <th>Laporan</th>
                        <th>Papar</th>

                        <c:set value="1" var="count"/>
                        <c:forEach items="${actionBean.laporan18}" var="report">
                        <tr>
                            <td>${count}
                            </td>
                            <td>${report}
                            </td>
                            <td><div align="center">
                                    <p align="center">
                                        <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                             onclick="popup('${pageContext.request.contextPath}/utiliti/reports/requestParam?namaReport=${actionBean.laporan18Report[count-1]}&report=${report}');" onmouseover="this.style.cursor='pointer';">
                                    </p>
                                </div>
                            </td>
                        </tr>
                        <c:set value="${count +1}" var="count"/>
                    </c:forEach>
                    </tr>
                </table>
                <br>
            </fieldset>
        </div>
                        
        <!--Laporan Permohonan Meminda/Membatal/Mengena/Menghapus Bagi Status Permohonan Tugasan Siap Disetiap Peringkat-->
        <div class="subtitle" id="laporan19">
            <fieldset class="aras1">
                <legend>
                    Laporan Permohonan Meminda/Membatal/Mengena/Menghapus Bagi Status Permohonan Tugasan Siap Disetiap Peringkat
                </legend>
                <p style="color:red">
                    *Sila klik pada (<img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem' id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';"/>) untuk memaparkan laporan<br/>
                </p>
                <br>
                <table class="tablecloth">
                    <tr style="width: 100%">
                        <th>Bil</th>
                        <th>Laporan</th>
                        <th>Papar</th>

                        <c:set value="1" var="count"/>
                        <c:forEach items="${actionBean.laporan19}" var="report">
                        <tr>
                            <td>${count}
                            </td>
                            <td>${report}
                            </td>
                            <td><div align="center">
                                    <p align="center">
                                        <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                             onclick="popup('${pageContext.request.contextPath}/utiliti/reports/requestParam?namaReport=${actionBean.laporan19Report[count-1]}&report=${report}');" onmouseover="this.style.cursor='pointer';">
                                    </p>
                                </div>
                            </td>
                        </tr>
                        <c:set value="${count +1}" var="count"/>
                    </c:forEach>
                    </tr>
                </table>
                <br>
            </fieldset>
        </div>    
    </s:form>
</div>
