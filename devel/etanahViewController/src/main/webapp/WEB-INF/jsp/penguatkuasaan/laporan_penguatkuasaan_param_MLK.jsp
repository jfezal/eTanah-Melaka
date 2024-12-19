<%-- 
    Document   : laporan_penguatkuasaan_param_MLK
    Created on : Apr 16, 2012, 12:46:38 PM
    Author     : sitifariza.hanim
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
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">

    var date = new Date();
    var kodCaw = '${actionBean.cawKod}';
    $(document).ready( function(){
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        $("#caw").val(kodCaw);
    });

    function doSubmitOriginal(f){
        var form = $(f).formSerialize();
        var report = '${actionBean.reportName}';
        if((
        report == 'ENFDS_MLK.rdf'
            || report == 'ENFNK_MLK.rdf'
            || report == 'ENFLPOP_MLK.rdf'
            || report == 'ENFLTNH_MLK.rdf'
            || report == 'ENFSTMAJU_MLK.rdf'
              
    ) && $("#id_mohon").val() == ""){
            alert("Sila masukkan 'ID Permohonan' terlebih dahulu.");
            $("#id_mohon").focus();
        }else{
            f.action = "${pageContext.request.contextPath}/reportGeneratorServlet?"+form;
            f.submit();
        }if((report == 'ENFSTMAJU_MLK.rdf')
            && $('#trh_mula').val()== ""){
            alert("Sila masukkan Tarikh Mula terlebih dahulu.");
            $('#trh_mula').focus();
            $.unblockUI();
        }else{
            f.action = "${pageContext.request.contextPath}/reportGeneratorServlet?"+form;
            f.submit();
        }
        if((report == 'ENFSTMAJU_MLK.rdf')
            && $('#trh_akhir').val()== ""){
            alert("Sila masukkan Tarikh Akhir terlebih dahulu.");
            $('#trh_akhir').focus();
            $.unblockUI();
        }else{
            f.action = "${pageContext.request.contextPath}/reportGeneratorServlet?"+form;
            f.submit();
        }

        if((report == 'ENFSTMAJU_MLK.rdf')
            && $('#caw').val()== ""){
            alert("Sila pilih Cawangan terlebih dahulu.");
            $('#caw').focus();
            $.unblockUI();
        }else{
            f.action = "${pageContext.request.contextPath}/reportGeneratorServlet?"+form;
            f.submit();
        }
            
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

    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = RemoveNonNumeric(content);
            return;
        }
    }

    function RemoveNonNumeric( strString )
    {
        var strValidCharacters = "1234567890";
        var strReturn = "";
        var strBuffer = "";
        var intIndex = 0;
        // Loop through the string
        for( intIndex = 0; intIndex < strString.length; intIndex++ )
        {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                strReturn += strBuffer;
            }
        }
        return strReturn;
    }

    function doSubmit(f){
        var form = $(f).formSerialize();
        var report = '${actionBean.reportName}';
        if((
        report == 'ENFDS_MLK.rdf'
            || report == 'ENFNK_MLK.rdf'
            || report == 'ENFLPOP_MLK.rdf'
            || report == 'ENFLTNH_MLK.rdf'
            || report == 'ENFSTMAJU_MLK.rdf'

    ) && $("#id_mohon").val() == ""){
            alert("Sila masukkan 'ID Permohonan' terlebih dahulu.");
            $("#id_mohon").focus();
            return false;
        }

        if(report == 'ENFSTMAJU_MLK.rdf'){
            if($('#trh_mula').val()== ""){
                alert("Sila masukkan Tarikh Mula terlebih dahulu.");
                $('#trh_mula').focus();
                return false;
            }

            if($('#trh_akhir').val()== ""){
                alert("Sila masukkan Tarikh Akhir terlebih dahulu.");
                $('#trh_akhir').focus();
                return false;
            }

            if($('#caw').val()== ""){
                alert("Sila pilih Cawangan terlebih dahulu.");
                $('#caw').focus();
                return false;
            }
            
        }
        
        if(report == 'ENFAduanDaerah_MLK.rdf'){
            if($('#daerahCarian').val()== ""){
                alert("Sila pilih daerah terlebih dahulu.");
                $('#daerahCarian').focus();
                return false;
            }
            if($('#trh_mula').val()== ""){
                alert("Sila masukkan Tarikh Mula terlebih dahulu.");
                $('#trh_mula').focus();
                return false;
            }

            if($('#trh_akhir').val()== ""){
                alert("Sila masukkan Tarikh Akhir terlebih dahulu.");
                $('#trh_akhir').focus();
                return false;
            }
            
        }
        
        if(report == 'ENFAduanKtn_MLK.rdf'){
            if($('#daerahCarian').val()== ""){
                alert("Sila pilih daerah terlebih dahulu.");
                $('#daerahCarian').focus();
                return false;
            }
            if($('#trh_mula').val()== ""){
                alert("Sila masukkan Tarikh Mula terlebih dahulu.");
                $('#trh_mula').focus();
                return false;
            }

            if($('#trh_akhir').val()== ""){
                alert("Sila masukkan Tarikh Akhir terlebih dahulu.");
                $('#trh_akhir').focus();
                return false;
            }
            
            if($('#kodUrusanCarian').val()== ""){
                alert("Sila pilih kod urusan terlebih dahulu.");
                $('#kodUrusanCarian').focus();
                return false;
            }
            
        }
        
        if(report == 'ENFAduanHarian_MLK.rdf' || report == 'ENFAduanMingguan_MLK.rdf'|| report == 'ENFStatTakSelesai_MLK.rdf' || report == 'ENFAduanTakSelesai_MLK.rdf' || report == 'ENFKompaunTahunan_MLK.rdf'){
            if($('#trh_mula').val()== ""){
                alert("Sila masukkan Tarikh Mula terlebih dahulu.");
                $('#trh_mula').focus();
                return false;
            }

            if($('#trh_akhir').val()== ""){
                alert("Sila masukkan Tarikh Akhir terlebih dahulu.");
                $('#trh_akhir').focus();
                return false;
            }
        }
        
        if(report == 'ENFAduanBulanan_MLK.rdf'){
            if($('#bulanCarian').val()== ""){
                alert("Sila pilih bulan terlebih dahulu.");
                $('#bulanCarian').focus();
                return false;
            }

            if($('#tahun').val()== ""){
                alert("Sila pilih tahun terlebih dahulu.");
                $('#tahun').focus();
                return false;
            }
        }
        
        if(report == 'ENFAduanTahunan_MLK.rdf'){
            if($('#tahun').val()== ""){
                alert("Sila pilih tahun terlebih dahulu.");
                $('#tahun').focus();
                return false;
            }
            
            if($('#daerahCarian').val()== ""){
                alert("Sila pilih daerah terlebih dahulu.");
                $('#daerahCarian').focus();
                return false;
            }
        }
        
        if(report == 'ENFAduanSelesai_MLK.rdf'){
            if($('#trh_mula').val()== ""){
                alert("Sila masukkan Tarikh Mula terlebih dahulu.");
                $('#trh_mula').focus();
                return false;
            }

            if($('#trh_akhir').val()== ""){
                alert("Sila masukkan Tarikh Akhir terlebih dahulu.");
                $('#trh_akhir').focus();
                return false;
            }
            
            if($('#kodKeputusan').val()== "silaPilih"){
                alert("Sila masukkan keputusan terlebih dahulu.");
                $('#kodKeputusan').focus();
                return false;
            }
        }
        
//    ========================== untuk report baru =========================
    
         if(report == 'ENFStatSelesai_MLK.rdf'){
            if($('#trh_mula').val()== ""){
                alert("Sila masukkan Tarikh Mula terlebih dahulu.");
                $('#trh_mula').focus();
                return false;
            }

            if($('#trh_akhir').val()== ""){
                alert("Sila masukkan Tarikh Akhir terlebih dahulu.");
                $('#trh_akhir').focus();
                return false;
            }
            
            if($('#kodKeputusan').val()== "silaPilih"){
                alert("Sila masukkan keputusan terlebih dahulu.");
                $('#kodKeputusan').focus();
                return false;
            }
        }
    
        
//        ============== untuk report baru ===================
        
        if(report == 'ENFStatKompaun_MLK.rdf'){
            if($('#tahun').val()== ""){
                alert("Sila pilih tahun terlebih dahulu.");
                $('#tahun').focus();
                return false;
            }
        }

        f.action = "${pageContext.request.contextPath}/reportGeneratorServlet?"+form;
        f.submit();
    }
</script>

<s:form beanclass="etanah.view.penguatkuasaan.LaporanPenguatkuasaanMLKActionBean">
    <s:hidden name="reportName"/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Parameter</legend>
            <c:set value="${actionBean.reportName}" var="reportname"/>
            <c:if test="${
                  reportname eq 'ENFDS_MLK.rdf'
                      or reportname eq 'ENFNK_MLK.rdf'
                      or reportname eq 'ENFLPOP_MLK.rdf'
                      or reportname eq 'ENFLTNH_MLK.rdf'
                      or reportname eq 'ENFLTNH_KOSONG.rdf'
                  }">
                <p>
                    <label><em>*</em>ID Permohonan :</label>
                    <s:text id="id_mohon" name="report_p_id_mohon" onblur="this.value = this.value.toUpperCase();"/>
                </p>
            </c:if>
            <c:if test="${reportname eq 'ENFSTMAJU_MLK.rdf'}">
                <p>
                    <label><em>*</em>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>
                <p>
                    <label><em>*</em>Tarikh Tamat :</label>
                    <s:text id="trh_akhir" name="report_p_trh_akhir" class="datepicker" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>
                <p>
                    <label><em>*</em>Cawangan :</label>
                    <s:select id="caw" name="report_p_kod_caw" style="width:145px;">
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>
                    </s:select>
                </p>
            </c:if>
            <c:if test="${reportname eq 'ENFAduanDaerah_MLK.rdf'}">
                <input type="hidden" id="report_p_kod_caw" name="report_p_kod_caw" value="${actionBean.cawKod}">
                <p>
                    <label><em>*</em>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>
                <p>
                    <label><em>*</em>Tarikh Tamat :</label>
                    <s:text id="trh_akhir" name="report_p_trh_akhir" class="datepicker" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>
            </c:if>
            <c:if test="${reportname eq 'ENFAduanKtn_MLK.rdf'}">
                <input type="hidden" id="report_p_kod_caw" name="report_p_kod_caw" value="${actionBean.cawKod}">
                <p>
                    <label><em>*</em>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>
                <p>
                    <label><em>*</em>Tarikh Tamat :</label>
                    <s:text id="trh_akhir" name="report_p_trh_akhir" class="datepicker" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>
                <p>
                    <label><em>*</em>Kod Urusan :</label>
                    <s:select id="kodUrusanCarian" name="report_p_kod_urusan" style="width:700px">
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:options-collection collection="${actionBean.senaraiUrusan}" label="nama" value="kod"/>
                    </s:select>
                </p>
            </c:if>
            <c:if test="${reportname eq 'ENFAduanHarian_MLK.rdf'}">
                <input type="hidden" id="report_p_kod_caw" name="report_p_kod_caw" value="${actionBean.cawKod}">
                <p>
                    <label><em>*</em>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>
                <p>
                    <label><em>*</em>Tarikh Tamat :</label>
                    <s:text id="trh_akhir" name="report_p_trh_akhir" class="datepicker" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>
            </c:if>
            <c:if test="${reportname eq 'ENFAduanMingguan_MLK.rdf'}">
                <input type="hidden" id="report_p_kod_caw" name="report_p_kod_caw" value="${actionBean.cawKod}">
                <p>
                    <label><em>*</em>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>
                <p>
                    <label><em>*</em>Tarikh Tamat :</label>
                    <s:text id="trh_akhir" name="report_p_trh_akhir" class="datepicker" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>
            </c:if>
            <c:if test="${reportname eq 'ENFAduanBulanan_MLK.rdf'}">
                <input type="hidden" id="report_p_kod_caw" name="report_p_kod_caw" value="${actionBean.cawKod}">
                <p>
                    <label><em>*</em>Bulan :</label>
                    <s:select id="bulanCarian" name="report_p_bulan_masuk" style="width:145px;">
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:option value="01">Januari</s:option>
                        <s:option value="02">Februari</s:option>
                        <s:option value="03">Mac</s:option>
                        <s:option value="04">April</s:option>
                        <s:option value="05">Mei</s:option>
                        <s:option value="06">Jun</s:option>
                        <s:option value="07">Julai</s:option>
                        <s:option value="08">Ogos</s:option>
                        <s:option value="09">September</s:option>
                        <s:option value="10">Oktober</s:option>
                        <s:option value="11">November</s:option>
                        <s:option value="12">Disember</s:option>

                    </s:select>
                </p>
                <p>
                    <label><em>*</em>Tahun :</label>
                    <s:select id="tahun" name="report_p_tahun_masuk" style="width:145px;">
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:options-collection collection="${actionBean.listYear}"/>
                    </s:select>
                </p>
            </c:if>
            <c:if test="${reportname eq 'ENFAduanTahunan_MLK.rdf'}">
                <p>
                    <label><em>*</em>Tahun :</label>
                    <s:select id="tahun" name="report_p_tahun_masuk" style="width:145px;">
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:options-collection collection="${actionBean.listYear}"/>
                    </s:select>
                </p>
                <input type="hidden" id="report_p_kod_caw" name="report_p_kod_caw" value="${actionBean.cawKod}">
            </c:if>
            <c:if test="${reportname eq 'ENFAduanSelesai_MLK.rdf'}">
                <input type="hidden" id="report_p_kod_caw" name="report_p_kod_caw" value="${actionBean.cawKod}">
                <p>
                    <label><em>*</em>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>
                <p>
                    <label><em>*</em>Tarikh Tamat :</label>
                    <s:text id="trh_akhir" name="report_p_trh_akhir" class="datepicker" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>
                <p>
                    <label><em>*</em>Status :</label>
                    <s:select id="kodKeputusan" name="report_p_kod_kpsn">
                        <s:option value="silaPilih">--Sila Pilih--</s:option>
                        <s:option value="">Semua</s:option>
                        <s:option value="SC">Kes Selesai Kompaun</s:option>
                        <s:option value="SF">Kes Selesai Pendakwaan</s:option>
                        <s:option value="SO">Kes Selesai Tiada Tindakan Lanjut</s:option>
                        <s:option value="SQ">Kes Selesai Barang Dilupus</s:option>
                        <s:option value="SR">Kes Selesai Barang Dijual</s:option>
                        <s:option value="CM">Kes Selesai Tiada Kes</s:option>
                        <s:option value="SW">Kes Selesai Barang Dilepas</s:option>
                        <s:option value="SX">Kes Selesai Buka Halangan</s:option>
                        <s:option value="SY">Kes Selesai Laporan Polis</s:option>
                        <s:option value="SZ">Kes Selesai Hakisan Penuh</s:option>
                        <s:option value="CA">Kes Selesai Hakisan Sebahagian</s:option>
                        <s:option value="CB">Kes Selesai Ada Bantahan</s:option>
                        <s:option value="CC">Kes Selesai Tiada Bantahan</s:option>
                        <s:option value="CU">Kes Selesai Patuh Kosongkan Tanah</s:option>
                        <s:option value="CV">Kes Selesai MMKN Tolak</s:option>
                        <s:option value="CW">Kes Selesai Tiada Denda</s:option>
                        <s:option value="CX">Kes Selesai Henti Langgar Syarat</s:option>
                        <s:option value="CY">Kes Selesai Remedi</s:option>
                        <s:option value="CZ">Kes Selesai Perampasan</s:option>
                        <s:option value="CC">Kes Selesai Bantahan</s:option>

                    </s:select>
                </p>
            </c:if>
                
                
                <c:if test="${reportname eq 'ENFStatSelesai_MLK.rdf'}">
                <input type="hidden" id="report_p_kod_caw" name="report_p_kod_caw" value="${actionBean.cawKod}">
                <p>
                    <label><em>*</em>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>
                <p>
                    <label><em>*</em>Tarikh Tamat :</label>
                    <s:text id="trh_akhir" name="report_p_trh_akhir" class="datepicker" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>
                <p>
                    <label><em>*</em>Status :</label>
                    <s:select id="kodKeputusan" name="report_p_kod_kpsn">
                        <s:option value="silaPilih">--Sila Pilih--</s:option>
                        <s:option value="">Semua</s:option>
                        <s:option value="SC">Kes Selesai Kompaun</s:option>
                        <s:option value="SF">Kes Selesai Pendakwaan</s:option>
                        <s:option value="SO">Kes Selesai Tiada Tindakan Lanjut</s:option>
                        <s:option value="SQ">Kes Selesai Barang Dilupus</s:option>
                        <s:option value="SR">Kes Selesai Barang Dijual</s:option>
                        <s:option value="CM">Kes Selesai Tiada Kes</s:option>
                        <s:option value="SW">Kes Selesai Barang Dilepas</s:option>
                        <s:option value="SX">Kes Selesai Buka Halangan</s:option>
                        <s:option value="SY">Kes Selesai Laporan Polis</s:option>
                        <s:option value="SZ">Kes Selesai Hakisan Penuh</s:option>
                        <s:option value="CA">Kes Selesai Hakisan Sebahagian</s:option>
                        <s:option value="CB">Kes Selesai Ada Bantahan</s:option>
                        <s:option value="CC">Kes Selesai Tiada Bantahan</s:option>
                        <s:option value="CU">Kes Selesai Patuh Kosongkan Tanah</s:option>
                        <s:option value="CV">Kes Selesai MMKN Tolak</s:option>
                        <s:option value="CW">Kes Selesai Tiada Denda</s:option>
                        <s:option value="CX">Kes Selesai Henti Langgar Syarat</s:option>
                        <s:option value="CY">Kes Selesai Remedi</s:option>
                        <s:option value="CZ">Kes Selesai Perampasan</s:option>
                        <s:option value="CC">Kes Selesai Bantahan</s:option>

                    </s:select>
                </p>
            </c:if>
                
                
            <c:if test="${reportname eq 'ENFAduanTakSelesai_MLK.rdf'}">
                <input type="hidden" id="report_p_kod_caw" name="report_p_kod_caw" value="${actionBean.cawKod}">
                <p>
                    <label><em>*</em>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>
                <p>
                    <label><em>*</em>Tarikh Tamat :</label>
                    <s:text id="trh_akhir" name="report_p_trh_akhir" class="datepicker" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>
            </c:if>
            <c:if test="${reportname eq 'ENFStatTakSelesai_MLK.rdf'}">
                <input type="hidden" id="report_p_kod_caw" name="report_p_kod_caw" value="${actionBean.cawKod}">
                <p>
                    <label><em>*</em>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>
                <p>
                    <label><em>*</em>Tarikh Tamat :</label>
                    <s:text id="trh_akhir" name="report_p_trh_akhir" class="datepicker" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>
            </c:if>
            <c:if test="${reportname eq 'ENFKompaunTahunan_MLK.rdf'}">
                <input type="hidden" id="report_p_kod_caw" name="report_p_kod_caw" value="${actionBean.cawKod}">
                <p>
                    <label><em>*</em>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>
                <p>
                    <label><em>*</em>Tarikh Tamat :</label>
                    <s:text id="trh_akhir" name="report_p_trh_akhir" class="datepicker" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>
            </c:if>
            <c:if test="${reportname eq 'ENFStatKompaun_MLK.rdf'}">
                <input type="hidden" id="report_p_kod_caw" name="report_p_kod_caw" value="${actionBean.cawKod}">
                <p>
                    <label><em>*</em>Tahun :</label>
                    <s:select id="tahun" name="report_p_tahun" style="width:145px;">
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:options-collection collection="${actionBean.listYear}"/>
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

