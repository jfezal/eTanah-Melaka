<%-- 
    Document   : laporan_lelong_param_MLK
    Created on : Sep 19, 2010, 8:56:45 PM
    Author     : mazurahayati.yusop
--%>

<%--<%@page contentType="text/html" pageEncoding="UTF-8"%>--%>
<%--<%@page contentType="text/html" pageEncoding="windows-1252"%>--%>
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

    var date = new Date();
    var kodCaw = '${actionBean.kodCaw}';
    $(document).ready(function() {
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        $("#caw").val(kodCaw);
    });

    function doFilterDaerah(kodCaw2) {
        var report = $("#reportname").val();
        if (kodCaw2 != null) {
            var url = '${pageContext.request.contextPath}/lelong/laporanlelongMLK?masuk&kodCawangan=' + kodCaw2 + '&reportNama=' + report;
            $.get(url,
                    function(data) {
                        $('#display').html(data);
                        $('#caw').val(kodCaw2);
                        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                    });
        }
    }
    function doSubmit(f) {
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
        if ((report == 'LLGSuratKpdPelelong_MLK.rdf'
                || report == 'LLGLaporanBulananPerintahJual_MLK.rdf'
                || report == 'LLGJumlahKomisyenPTD_MLK.rdf') && $('#bulan').val() == "") {
            alert("Sila masukkan Bulan terlebih dahulu.");
            $('#bulan').focus();
            $.unblockUI();
        }
        else {
            f.action = "${pageContext.request.contextPath}/reportGeneratorServlet?" + form;
            f.submit();

        }
    }
    function dateValidation(id, value) {
        var vsplit = value.split('/');
        var fulldate = vsplit[1] + '/' + vsplit[0] + '/' + vsplit[2];
        var today = new Date();
        var sdate = new Date(fulldate);
        if (sdate > today) {
            alert("Tarikh yang dimasukkan tidak sesuai.");
            $('#' + id).val("");
        }
    }

    function validateNumber(elmnt, content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = RemoveNonNumeric(content);
            return;
        }
    }

    function RemoveNonNumeric(strString)
    {
        var strValidCharacters = "1234567890";
        var strReturn = "";
        var strBuffer = "";
        var intIndex = 0;
        // Loop through the string
        for (intIndex = 0; intIndex < strString.length; intIndex++)
        {
            strBuffer = strString.substr(intIndex, 1);
            // Is this a number
            if (strValidCharacters.indexOf(strBuffer) > -1)
            {
                strReturn += strBuffer;
            }
        }
        return strReturn;
    }
</script>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif"
     width="50" height="50" style="display: none" alt=""/>

<s:form beanclass="etanah.view.stripes.lelong.LaporanLelongMLKActionBean">
    <s:hidden name="reportName"/>
    <div class="subtitle">
        <fieldset class="aras1">


            <legend>
                <c:if test="${actionBean.reportName eq 'LLGLaporanBulananPerintahJual_MLK.rdf'}">
                    Laporan Bulanan Perintah Jual
                </c:if>
                <c:if test="${actionBean.reportName eq 'LLGLaporanLelongan_MLK.rdf'}">
                    Laporan Lelongan Awam
                </c:if>
                <c:if test="${actionBean.reportName eq 'LLGJumlahKomisyenPTD_MLK.rdf'}">
                    Laporan Komisyen Jualan
                </c:if>
                <c:if test="${actionBean.reportName eq 'LLG_Stat_01.rdf'}">
                    Statistik Permohonan Perintah Jual (16G)
                </c:if>
                <c:if test="${actionBean.reportName eq 'LLG_Stat_02.rdf'}">
                    Statistik Kadar Bayaran Perintah Jual (16H)
                </c:if>
                <c:if test="${actionBean.reportName eq 'LLG_stat_pembida_MLK.rdf'}">
                    Laporan Daftar Pembida
                </c:if>
                <c:if test="${actionBean.reportName eq 'LLG_stat_plb_MLK.rdf'}">
                    Laporan Senarai Pelelong Berlesen
                </c:if>
                <c:if test="${actionBean.reportName eq 'LLGSuratKpdPelelong_MLK.rdf'}">
                    Laporan Senarai Lelongan Untuk Pelelong Berlesen
                </c:if>
                <c:if test="${actionBean.reportName eq 'LLGLaporanPemohon_MLK.rdf'}">
                    Laporan Permohonan Perintah Jual
                </c:if>

            </legend>
            <%--<legend>Parameter</legend>--%>
            <div>
                <p style="color:red">
                    *Klik Butang Papar Atau Pilih Parameter Yang Dikehendaki<br/>
                </p>
            </div>
            <c:set value="${actionBean.reportName}" var="reportname"/>
            <c:set var="disable" value="false"/>
            <c:if test="${actionBean.pengguna.kodCawangan.kod ne '00'}">
                <c:set var="disable" value="true"/>
            </c:if>
            <c:if test="${
                  reportname eq 'LLGDokSiasatan_MLK.rdf'
                      or reportname eq 'LLGSuratBayarBaki_MLK.rdf'
                      or reportname eq 'LLGSrtAkuanBersumpahPPT_MLK.rdf'
                      or reportname eq 'LLGPhtrNotis_MLK.rdf'
                      or reportname eq 'LLGBorangAmPin_MLK.rdf'
                  }">
                <p>
                    <label><em>*</em>ID Permohonan :</label>
                    <s:text id="id_mohon" name="report_p_id_mohon" onblur="this.value = this.value.toUpperCase();"/>
                </p>
            </c:if>

            <%--<c:if test="${
                  reportname eq 'stat_pembida_MLK.rdf'}">
                <p>
                    <label>ID Permohonan :</label>
                    <s:text id="id_mohon" name="report_p_id_mohon" onblur="this.value = this.value.toUpperCase();"/>
                </p>
                <c:if test="${reportname eq 'stat_pembida_MLK.rdf'}">
                    <p>
                        <label>ID Hakmilik :</label>
                        <s:text id="id_hakmilik" name="report_p_id_hakmilik" onblur="this.value = this.value.toUpperCase();"/>
                    </p>
                </c:if>
            </c:if>--%>

            <%--    <c:if test="${reportname eq 'LLGJumlahKomisyenPTD_MLK.rdf'}">
                    <p>
                        <label>Tarikh Mula :</label>
                        <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy" />&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                    <p>
                        <label>Tarikh Tamat :</label>
                        <s:text id="trh_akhir" name="report_p_trh_akhir" class="datepicker" formatPattern="dd/MM/yyyy" />&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                    <p>
                        <label><em>*</em>Cawangan :</label>
                        <s:select id="caw" name="report_p_kod_caw" style="width:145px;" onchange="doFilterKaunter(this.value);">
                            <s:option value="">--Sila Pilih--</s:option>
                            <s:option value="01">PTD MELAKA TENGAH</s:option>
                            <s:option value="02">PTD JASIN</s:option>
                            <s:option value="03">PTD ALOR GAJAH</s:option>
                        </s:select>
                    </p>
                </c:if>--%>
            <c:if test="${reportname eq 'LLGLaporanBulananPerintahJual_MLK.rdf'
                          or reportname eq 'LLG_stat_senarai_hitam_pembida_MLK.rdf'
                          or reportname eq 'LLG_Stat_01.rdf'
                          or reportname eq 'LLG_Stat_02.rdf'
                          or reportname eq 'LLGJumlahKomisyenPTD_MLK.rdf'}">

                  <p>
                      <label><em>*</em>Cawangan :</label>
                      <s:select id="caw" name="report_p_kod_caw" style="width:260px;" disabled="${disable}" onchange="doFilterDaerah(this.value);">
                          <s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>
                      </s:select>
                      <s:hidden name="report_p_kod_caw" value="${actionBean.kodCaw}"/>
                  </p>
            </c:if>

            <c:if test="${reportname eq 'LLG_stat_hakmilik_plb_MLK.rdf'
                          or reportname eq 'LLG_stat_senarai_hitam_pembida_MLK.rdf'
                          or reportname eq 'LLG_stat_plb_MLK.rdf'}">
                  <p>
                      <label>Tarikh Mula :</label>
                      <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                  </p>
                  <p>
                      <label>Tarikh Tamat :</label>

                      <s:text id="trh_akhir" name="report_p_trh_akhir" class="datepicker" formatPattern="dd/MM/yyyy" />&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                  </p>
            </c:if>

            <%--ni yg xd date validation--%>    
            <c:if test="${reportname eq 'LLGLaporanPemohon_MLK.rdf'}">
                <p>
                    <label>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>
                <p>
                    <label>Tarikh Tamat :</label>
                    <s:text id="trh_akhir" name="report_p_trh_akhir" class="datepicker" formatPattern="dd/MM/yyyy" />&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>
                <p>
                    <label><em>*</em>Cawangan :</label>
                    <s:select id="caw" name="report_p_kod_caw" style="width:260px;" disabled="${disable}" onchange="doFilterDaerah(this.value);">
                        <s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>
                    </s:select>
                    <s:hidden name="report_p_kod_caw" value="${actionBean.kodCaw}"/>
                </p>
            </c:if>
            <c:if test="${reportname eq 'LLGLaporanLelongan_MLK.rdf'
                          or reportname eq 'LLG_stat_pembida_MLK.rdf'}">
                  <p>
                      <label>Tarikh Mula Lelongan :</label>
                      <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                  </p>
                  <p>
                      <label>Tarikh Akhir Lelongan :</label>
                      <s:text id="trh_akhir" name="report_p_trh_akhir" class="datepicker" formatPattern="dd/MM/yyyy" />&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                  </p>

            </c:if>
            <c:if test="${reportname eq 'LLGJumlahKomisyenPTD_MLK.rdf'}">
                <p>
                    <label>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_bulan_mula" class="datepicker" formatPattern="dd/MM/yyyy"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>
                <p>
                    <label>Tarikh Tamat :</label>
                    <s:text id="trh_akhir" name="report_p_bulan_akhir" class="datepicker" formatPattern="dd/MM/yyyy" />&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>
                <p>
                    <label><em>*</em>Cawangan :</label>
                    <s:select id="caw" name="report_p_kod_caw" style="width:260px;" disabled="${disable}" onchange="doFilterDaerah(this.value);">
                        <s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>
                    </s:select>
                    <s:hidden name="report_p_kod_caw" value="${actionBean.kodCaw}"/>
                </p>
            </c:if>

            <c:if test="${reportname eq 'LLGSuratKpdPelelong_MLK.rdf'
                          or reportname eq 'LLGLaporanBulananPerintahJual_MLK.rdf'}">
                  <p>
                      <label><em>*</em>Bulan :</label>
                      <s:select id="bulan" name="report_p_bulan" style="width:145px;">
                          <s:option value="">--Sila Pilih--</s:option>
                          <s:option value="01">Januari</s:option>
                          <s:option value="02">Februari</s:option>
                          <s:option value="03">Mac</s:option>
                          <s:option value="04">April</s:option>
                          <s:option value="05">May</s:option>
                          <s:option value="06">Jun</s:option>
                          <s:option value="07">Julai</s:option>
                          <s:option value="08">Ogos</s:option>
                          <s:option value="09">September</s:option>
                          <s:option value="10">Oktober</s:option>
                          <s:option value="11">November</s:option>
                          <s:option value="12">Disember</s:option>
                      </s:select>
                  </p>
<%--                 <s:select id="tahun" name="p_tahun" style="width:145px;">
                      <%--<s:option value="">--Sila Pilih--</s:option>
                      <s:options-collection collection="${actionBean.listYear}"/>
                  </s:select>--%>
            </c:if>
            <c:if test="${reportname eq  'LLG_stat_pembida_MLK.rdf' }">
                <p>
                    <label>ID Permohonan :</label>
                    <s:text id="id_mohon" name="report_p_id_mohon" onblur="this.value = this.value.toUpperCase();"/>
                </p>
            </c:if>
            <c:if test="${reportname eq 'LLG_stat_pembida_MLK.rdf'}">
                <p>
                    <label>No Pengenalan :</label>
                    <s:text id="id_mohon" name="report_p_no_pengenalan" onblur="this.value = this.value.toUpperCase();"/>
                </p>
                <p>
                    <label>Status :</label>
                    <s:select id="pembida" name="report_p_sts" style="width:145px;">
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:option value="BJ">Berjaya</s:option>
                        <s:option value="TB">Tidak Berjaya</s:option>
                        <s:option value="SH">Senarai Hitam</s:option>
                    </s:select>
                </p>
            </c:if>

            <c:if test="${reportname eq 'LLGSuratKpdPelelong_MLK.rdf'
                  }">
                <%--or reportname eq 'LLG_stat_pembida_MLK.rdf'--%>
                <p>
                    <label><em>*</em>Cawangan :</label>
                    <s:select id="caw" name="report_p_kod_caw" style="width:260px;" disabled="${disable}" onchange="doFilterDaerah(this.value);">
                        <s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>
                    </s:select>
                    <s:hidden name="report_p_kod_caw" value="${actionBean.kodCaw}"/>
                </p>
            </c:if>

            <c:if test="${reportname eq 'LLG_Stat_02.rdf'
                          or reportname eq 'LLG_Stat_01.rdf'
                          or reportname eq 'LLGLaporanBulananPerintahJual_MLK.rdf'
                          or reportname eq 'LLGSuratKpdPelelong_MLK.rdf'}">
                  <p>
                      <label>Tahun :</label>
                      <s:select id="tahun" name="report_p_tahun" style="width:145px;">
                          <%--<s:option value="">--Sila Pilih--</s:option>--%>
                          <s:options-collection collection="${actionBean.listYear}"/>
                      </s:select>
                  </p>
            </c:if>

            <c:if test="${reportname eq 'LLGLaporanLelongan_MLK.rdf'
                          or reportname eq 'LLGLaporanPemohon_MLK.rdf'}">
                  <p>
                      <label>Kategori :</label>
                      <s:select id="tanah" name="report_p_KOD_KATG_tanah" style="width:200px;">
                          <s:option value="">-Sila Pilih-</s:option>
                          <s:options-collection collection="${listUtil.senaraiKodKategoriTanah}" label="nama" value="kod" />
                      </s:select>
                  </p>
            </c:if>

            <c:if test="${reportname eq 'LLGLaporanLelongan_MLK.rdf'}">
                <p>
                    <label>Jurulelong :</label>
                    <s:select id="jurulelong" name="report_p_JL" style="width:145px;">
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:option value="PH">Pentadbir Tanah</s:option>
                        <s:option value="JL">Jurulelong Berlesen</s:option>
                    </s:select>
                </p>
            </c:if>



            <c:if test="${reportname eq 'LLGLaporanLelongan_MLK.rdf'}">
                <p>
                    <label>Status Bayaran :</label>
                    <s:select id="jurulelong" name="report_p_sts_byr" style="width:145px;">
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:option value="DB">Sudah Jelas</s:option>
                        <s:option value="LB">Belum Jelas</s:option>
                    </s:select>
                </p>
            </c:if>
            <c:if test="${reportname eq 'LLGLaporanPemohon_MLK.rdf'}">
                <p>
                    <label>Status Permohonan :</label>
                    <s:select id="jurulelong" name="report_p_sts_mohon" style="width:145px;">
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:option value="AK">Belum Selesai</s:option>
                        <s:option value="SL">Selesai Lelongan</s:option>
                        <s:option value="RM">Rujuk Mahkamah</s:option>
                        <s:option value="TL">Tolak</s:option>
                        <s:option value="SD">Gantung(Perintah Mahkamah)</s:option>
                        <s:option value="MK">Batal Perintah Jual Oleh Mahkamah</s:option>
                    </s:select>
                </p>
            </c:if>

            <c:if test="${reportname eq 'LLG_stat_plb_MLK.rdf'}">
                <p>
                    <label>Status :</label>
                    <s:select id="status" name="report_p_status" style="width:145px;">
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:option value="Y">Aktif</s:option>
                        <s:option value="TK">Tidak Aktif </s:option>
                    </s:select>
                </p>
            </c:if>



            <br>
            <p align="center">
                <s:button name="" id="simpan" value="Papar" class="btn" onclick="doSubmit(this.form);"/>&nbsp;
                <s:button name="" id="close" value="Tutup" onclick="self.close();" class="btn"/>&nbsp;
            </p>
        </fieldset>
    </div>
</s:form>

