<%-- 
    Document   : hasilspoc_laporan_param_NS
    Created on : Aug 2, 2010, 4:17:54 PM
    Author     : zadhirul.farihim
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
    var date = new Date();
    var kodCaw = '${actionBean.kodCaw}';
    var kodDaerah = '${actionBean.kodDaerah}';
    $(document).ready( function(){
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        $("#trh_mula").val(date.getDate()+"/"+(date.getMonth()+1)+"/"+date.getFullYear());
        $("#trh_tamat").val(date.getDate()+"/"+(date.getMonth()+1)+"/"+date.getFullYear());
        $("#trh_kutipan").val(date.getDate()+"/"+(date.getMonth()+1)+"/"+date.getFullYear());
        $("#bulan").val(date.getMonth()+1);
        $("#tahun").val(date.getFullYear());
        $("#daerah").val(kodDaerah);
        $("#caw").val(kodCaw);
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
        var report = '${actionBean.reportName}';
        if(report == 'HSLSuratAkuanBerkanun.rdf' && $('#no_ruj').val() == ""){
            alert("Sila masukkan No. Rujukan terlebih dahulu.");
            $('#no_ruj').focus();
            $.unblockUI();
        }else if(report == 'hasilPembatalanNotis6A.rdf' && $('#id_hakmilik').val() == ""){
            alert("Sila masukkan ID Hakmilik terlebih dahulu.");
            $('#id_hakmilik').focus();
            $.unblockUI();
        }else if(report == 'hasilSuratGantiCekTakLakuResit.rdf' && $('#id_resit').val() == ""){
            alert("Sila masukkan ID Resit terlebih dahulu.");
            $('#id_resit').focus();
            $.unblockUI();
        }else if((report == 'hasilSuratPeringatan.rdf'
            || report == 'hasilPembatalanNotis6A.rdf') && $('#id_dasar').val() == ""){
            alert("Sila masukkan ID Dasar terlebih dahulu.");
            $('#id_dasar').focus();
            $.unblockUI();
        }else if((report == 'hasilRayuanKurangCukai.rdf'
            || report == 'hasilNotis6A.rdf'
            || report == 'hasilNotis8A.rdf'
            || report == 'SPOCFolder_Page.rdf'
            || report == 'hasilNotisGantian.rdf') && $('#id_mohon').val() == ""){
            alert("Sila masukkan ID Permohonan terlebih dahulu.");
            $('#id_mohon').focus();
            $.unblockUI();
        }
    <%-- else if ((report == 'HSL_R_06.rdf'
         || report == 'HSL_R_07.rdf'
         || report == 'HSL_R_08.rdf'
         || report == 'HSL_R_09.rdf'
         || report == 'HSL_R_10.rdf'
         || report == 'HSL_R_11.rdf'
         || report == 'HSL_R_12.rdf'
         || report == 'HSL_R_13.rdf'
         || report == 'HSL_R_14.rdf'
         || report == 'HSL_R_15.rdf'
         || report == 'HSL_R_16.rdf'
         || report == 'HSL_R_17.rdf'
         || report == 'HSL_R_20.rdf'
         || report == 'HSL_R_31.rdf'
         || report == 'HSL_R_32.rdf'
         || report == 'HSL_R_33.rdf'
         || report == 'HSLLaporanTerimaanPos.rdf') && $('#daerah').val() == ""){
         alert("Sila pilih Daerah terlebih dahulu.");
         $('#daerah').focus();
         $.unblockUI();
     }else if((report == 'SPOCLaporanPenjenisanHasil_NS.rdf'
         || report == 'SPOCLaporanPenjenisanHasil_MLK.rdf'
         || report == 'HSL_R_24.rdf'
         || report == 'HSL_R_26.rdf'
         || report == 'SPOCSenaraiTerimaanHarian(Umum).rdf'
         || report == 'SPOCSenaraiTerimaanHarian(Persekutuan).rdf'
         || report == 'SPOCSenaraiTerimaanHarian(Negeri).rdf'
         || report == 'HSL_PP_NS.rdf'
         || report == 'HSL_R_23.rdf'
         || report == 'HSL_R_25.rdf'
         || report == 'HSL_R_01.rdf'
         || report == 'HSL_R_02.rdf'
         || report == 'HSL_R_03.rdf'
         || report == 'HSL_R_04.rdf'
         || report == 'HSL_R_05.rdf'
         || report == 'HSL_R_21.rdf'
         || report == 'HSL_R_30.rdf'
         || report == 'HSL_R_06.rdf'
         || report == 'HSL_R_07.rdf'
         || report == 'HSL_R_08.rdf'
         || report == 'HSL_R_09.rdf'
         || report == 'HSL_R_10.rdf'
         || report == 'HSL_R_11.rdf'
         || report == 'HSL_R_12.rdf'
         || report == 'HSL_R_13.rdf'
         || report == 'HSL_R_14.rdf'
         || report == 'HSL_R_15.rdf'
         || report == 'HSL_R_16.rdf'
         || report == 'HSL_R_17.rdf'
         || report == 'HSL_R_20.rdf'
         || report == 'HSL_R_31.rdf'
         || report == 'HSL_R_32.rdf'
         || report == 'HSL_R_33.rdf'
         || report == 'HSL_R_36.rdf'
         || report == 'HSL_R_37.rdf'
         || report == 'HSL_R_34.rdf'
         || report == 'HSL_ADD_01.rdf'
         || report == 'HSL_ADD_02.rdf'
         || report == 'HSL_ADD_03.rdf'
         || report == 'HSL_ADD_04.rdf'
         || report == 'HSL_ADD_05.rdf'
         || report == 'HSL_ADD_06.rdf'
         || report == 'HSL_ADD_07.rdf'
         || report == 'HSL_R_40.rdf'
         || report == 'HSL_R_41.rdf'
         || report == 'HSL_ADD_08.rdf'
         || report == 'HSL_ADD_09.rdf') && $('#caw').val() == ""){
         alert("Sila pilih Cawangan terlebih dahulu.");
         $('#caw').focus();
         $.unblockUI();
     }--%>
             else{
                 f.action = "${pageContext.request.contextPath}/reportGeneratorServlet?"+form;
                 f.submit();
    <%--var url = "${pageContext.request.contextPath}/reportGeneratorServlet?"+form;
    $.get(url, function(data){
        $('#display').html(data);
        $.unblockUI();
    }, "html");--%>
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
            alert(strString)
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

        function validateYearLength(value){
            var plength = value.length;
            if(plength != 4){
                alert('"Tahun" yang dimasukkan salah.');
                $('#tahun').val("");
                $('#tahun').focus();
            }
        }

        function doFilterKaunter(kodCaw1){
            var report = $("#reportname").val();
            if(kodCaw1 != ""){
                var url = '${pageContext.request.contextPath}/laporanHasilSpoc_NS?doCollectKaunter&kodCawangan=' + kodCaw1+'&reportNama='+report;
                $.get(url,
                function(data){
                    $('#display').html(data);
                    $('#caw').val(kodCaw1);
                    $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                });
            }
            setField(kodCaw1);
        }

        function setField(caw){
            $('#kCaw').val(caw);
        }

        function doFilterDaerah(kodCaw2){
            var report = $("#reportname").val();
            if(kodCaw2 != null){
                var url = '${pageContext.request.contextPath}/laporanHasilSpoc_NS?doFilterDaerahBPM&kodCawangan=' + kodCaw2+'&reportNama='+report;
                $.get(url,
                function(data){
    <%--alert(data);--%>
                    $('#display').html(data);
                    $('#caw').val(kodCaw2);
                    $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                });
            }
        }
    
        function doFilterBPM(kodDaerah1){
            var report = $("#reportname").val();
            var caw;
            if($('#caw').val() != '')
                caw = $('#caw').val();
            if(kodDaerah1 != ""){
                var url = '${pageContext.request.contextPath}/laporanHasilSpoc_NS?doFilterBPM&kodDaerah=' + kodDaerah1+'&reportNama='+report;
                $.get(url,
                function(data){
    <%--alert(data);--%>
                    $('#display').html(data);
                    $('#daerah').val(kodDaerah1);
                    if(caw != '')
                        $('#caw').val(caw);
                    $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                });
            }
        }

        function fmtNumber(val, id){
            var m = 0;
            if(id == 'min'){
                if(val==''){
                }else{
                    m = parseFloat(val);
                    if (isNaN(m)){
                        $('#min').val('0.00');
                        alert("Nombor tidak sah");
                    }else{$('#min').val(m.toFixed(2));}
                }
            }else{
                if(val==''){}
                else{
                    m = parseFloat(val);
                    if (isNaN(m)){
                        $('#max').val('0.00');
                        alert("Nombor tidak sah");
                    }else{$('#max').val(m.toFixed(2));}
                }
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
</script>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif"
     width="50" height="50" style="display: none" alt=""/>

<s:form beanclass="etanah.view.laporan.NSHasilSpocLaporanActionBean">    
    <div id="display" class="subtitle">
        <s:hidden id="reportname" name="reportName"/>
        <fieldset class="aras1">
            <legend>Parameter</legend>
            <c:set value="${actionBean.reportName}" var="reportname"/>
            <c:set var="disable" value="false"/>
            <c:if test="${actionBean.pengguna.kodCawangan.kod ne '00'}">
                <c:set var="disable" value="true"/>
            </c:if>

            <c:if test="${reportname eq 'HSL_R_10.rdf'
                          or reportname eq 'HSL_R_11.rdf'
                          or reportname eq 'HSL_R_12.rdf'
                          or reportname eq 'HSL_R_14.rdf'
                          or reportname eq 'HSL_R_15.rdf'
                          or reportname eq 'HSL_R_16.rdf'
                          or reportname eq 'HSL_R_17.rdf'
                          or reportname eq 'HSL_R_20.rdf'
                          or reportname eq 'HSL_ADD_05.rdf'
                          or reportname eq 'HSL_ADD_06.rdf'
                          or reportname eq 'HSL_R_40.rdf'
                          or reportname eq 'HSL_ADD_07.rdf'
                          or reportname eq 'HSL_ADD_09.rdf'}">
                  <p>
                      <label><em>*</em>Cawangan :</label>
                      <s:select id="caw" name="report_p_kod_caw" style="width:260px;" disabled="${disable}" onchange="doFilterDaerah(this.value);">
                          <s:option value="">Semua</s:option>
                          <s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>
                      </s:select>
                      <s:hidden name="report_p_kod_caw" value="${actionBean.kodCaw}"/>
                  </p>

            </c:if>
            <c:if test="${reportname eq 'HSL_R_06.rdf'
                          or reportname eq 'HSL_R_07.rdf'
                          or reportname eq 'HSL_R_08.rdf'
                          or reportname eq 'HSL_R_09.rdf'
                          or reportname eq 'HSL_R_10.rdf'
                          or reportname eq 'HSL_R_11.rdf'
                          or reportname eq 'HSL_R_12.rdf'
                          or reportname eq 'HSL_R_13.rdf'
                          or reportname eq 'HSL_R_14.rdf'
                          or reportname eq 'HSL_R_15.rdf'
                          or reportname eq 'HSL_R_16.rdf'
                          or reportname eq 'HSL_R_17.rdf'
                          or reportname eq 'HSL_R_20.rdf'
                          or reportname eq 'HSL_R_31.rdf'
                          or reportname eq 'HSL_R_31_1.rdf'
                          or reportname eq 'HSL_R_32.rdf'
                          or reportname eq 'HSL_R_33.rdf'
                          or reportname eq 'HSL_R_34.rdf'
                          or reportname eq 'HSL_R_37.rdf'
                          or reportname eq 'HSL_ADD_01.rdf'
                          or reportname eq 'HSL_ADD_03.rdf'
                          or reportname eq 'HSL_ADD_05.rdf'
                          or reportname eq 'HSL_ADD_06.rdf'
                          or reportname eq 'HSL_ADD_07.rdf'
                          or reportname eq 'HSL_ADD_09.rdf'
                          or reportname eq 'HSL_R_36.rdf'
                          or reportname eq 'HSL_ADD_04.rdf'
                          or reportname eq 'HSL_ADD_10.rdf'}">
                  <p>
                      <label><em>*</em>Daerah :</label>
                      <s:select name="kodDaerah" style="width:260px;" disabled="${disable}" onchange="doFilterBPM(this.value);">
                          <s:options-collection collection="${listUtil.senaraiKodDaerah}" label="nama" value="kod"/>
                      </s:select>
                      <s:hidden name="report_p_kod_daerah" value="${actionBean.kodDaerah}"/>
                  </p>
            </c:if>
            <c:if test="${reportname eq 'HSL_R_06.rdf'
                          or reportname eq 'HSL_R_07.rdf'
                          or reportname eq 'HSL_R_08.rdf'
                          or reportname eq 'HSL_R_09.rdf'
                          or reportname eq 'HSL_R_10.rdf'
                          or reportname eq 'HSL_R_11.rdf'
                          or reportname eq 'HSL_R_12.rdf'
                          or reportname eq 'HSL_R_13.rdf'
                          or reportname eq 'HSL_R_14.rdf'
                          or reportname eq 'HSL_R_15.rdf'
                          or reportname eq 'HSL_R_16.rdf'
                          or reportname eq 'HSL_R_17.rdf'
                          or reportname eq 'HSL_R_20.rdf'
                          or reportname eq 'HSL_R_31.rdf'
                          or reportname eq 'HSL_R_31_1.rdf'
                          or reportname eq 'HSL_R_32.rdf'
                          or reportname eq 'HSL_R_33.rdf'
                          or reportname eq 'HSL_R_37.rdf'
                          or reportname eq 'HSL_ADD_01.rdf'
                          or reportname eq 'HSL_ADD_03.rdf'
                          or reportname eq 'HSL_ADD_05.rdf'
                          or reportname eq 'HSL_ADD_06.rdf'
                          or reportname eq 'HSL_ADD_07.rdf'
                          or reportname eq 'HSL_ADD_09.rdf'}">
                  <p>
                      <label>Bandar/Pekan/Mukim :</label>
                      <s:select id="bpm" name="report_p_kod_bpm" style="width:200px;">
                          <s:option value="">Semua</s:option>
                          <%--<s:options-collection collection="${listUtil.senaraiKodBandarMukim}" label="nama" value="kod"/>--%>
                          <s:options-collection collection="${actionBean.senaraiBPM}" label="nama" value="kod"/>
                      </s:select>
                  </p>
            </c:if>
            <%--<c:if test="${reportname eq 'HSL_R_33.rdf'}">
                <p>
                    <label><em>*</em>Status :</label>
                    <s:select id="bpm" name="report_p_alamat" style="width:200px;">
                        <s:option value="0">Beralamat</s:option>
                        <s:option value="1">Tidak Beralamat</s:option>
                    </s:select>
                </p>
            </c:if>--%>
            <c:if test="${reportname eq 'SPOCLaporanPenjenisanHasil_NS.rdf'
                          or reportname eq 'SPOCLaporanPenjenisanHasil_MLK.rdf'
                          or reportname eq 'HSL_R_24.rdf'
                          or reportname eq 'HSL_R_26.rdf'
                          or reportname eq 'SPOCSenaraiTerimaanHarian(Umum).rdf'
                          or reportname eq 'SPOCSenaraiTerimaanHarian(Persekutuan).rdf'
                          or reportname eq 'SPOCSenaraiTerimaanHarian(Negeri).rdf'
                          or reportname eq 'HSL_PP_NS.rdf'
                          or reportname eq 'SPOCPenyataPemungut(BankIn).rdf'
                          or reportname eq 'HSL_R_23.rdf'
                          or reportname eq 'HSL_R_25.rdf'
                          or reportname eq 'HSL_R_01.rdf'
                          or reportname eq 'HSL_R_02.rdf'
                          or reportname eq 'HSL_R_03.rdf'
                          or reportname eq 'HSL_R_04.rdf'
                          or reportname eq 'HSL_R_05.rdf'
                          or reportname eq 'HSL_R_21.rdf'
                          or reportname eq 'HSL_R_30.rdf'
                          or reportname eq 'HSL_R_41.rdf'
                          or reportname eq 'HSL_ADD_02.rdf'
                          or reportname eq 'HSL_ADD_08.rdf'
                          or reportname eq 'HSL_R_42.rdf'}">
                  <p>
                      <s:hidden name="report_p_kod_caw" value="${actionBean.kodCaw}" id="kCaw"/>
                      <label><em>*</em>Daerah :</label>
                      <s:select id="caw" name="report_p_kod_caw" style="width:260px;" disabled="${disable}" onchange="doFilterKaunter(this.value);">
                          <s:option value="">Semua</s:option>
                          <s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>
                      </s:select>
                  </p>
            </c:if>
            <c:if test="${reportname eq 'HSL_R_01.rdf'
                          or reportname eq 'HSL_R_02.rdf'
                          or reportname eq 'HSL_R_03.rdf'
                          or reportname eq 'HSL_R_04.rdf'
                          or reportname eq 'HSL_R_41.rdf'
                          or reportname eq 'HSL_PP_NS.rdf'
                          or reportname eq 'HSL_R_42.rdf'}">
                  <p>
                      <label>ID Pengguna :</label>
                      <s:select name="report_p_id_pengguna" style="width:145px;">
                          <s:option value="">Semua</s:option>
                          <c:forEach items="${actionBean.senaraiPenggunaCaw}" var="pengguna" >
                              <s:option value="${pengguna.idPengguna}">${pengguna.idPengguna}</s:option>
                          </c:forEach>
                      </s:select>
                  </p>
            </c:if>
            <c:if test="${reportname eq 'HSL_R_01.rdf'
                          or reportname eq 'HSL_R_02.rdf'
                          or reportname eq 'HSL_R_03.rdf'
                          or reportname eq 'HSL_R_04.rdf'
                          or reportname eq 'HSL_PP_NS.rdf'
                          or reportname eq 'HSL_R_42.rdf'}">
                  <p>
                      <label>Kaunter :</label>
                      <s:select name="report_p_id_kaunter" style="width:145px;">
                          <s:option value="">Semua</s:option>
                          <c:forEach items="${actionBean.senaraiKaunter}" var="kaunter" >
                              <s:option value="${kaunter}">${kaunter}</s:option>
                          </c:forEach>
                          <%--<s:option value="01">01</s:option>
                          <s:option value="02">02</s:option>
                          <s:option value="03">03</s:option>
                          <s:option value="04">04</s:option>
                          <s:option value="05">05</s:option>
                          <s:option value="06">06</s:option>
                          <s:option value="07">07</s:option>
                          <s:option value="08">08</s:option>
                          <s:option value="09">09</s:option>
                          <s:option value="10">10</s:option>--%>
                      </s:select>
                  </p>
            </c:if>
            <c:if test="${reportname eq 'SPOCSenaraiTerimaanHarian(Umum).rdf'
                          or reportname eq 'SPOCSenaraiTerimaanHarian(Persekutuan).rdf'
                          or reportname eq 'SPOCSenaraiTerimaanHarian(Negeri).rdf'
                          or reportname eq 'HSL_R_23.rdf'
                          or reportname eq 'HSL_R_24.rdf'
                          or reportname eq 'HSL_R_25.rdf'
                          or reportname eq 'HSL_R_26.rdf'}">
                  <p>
                      <label>Kaunter Mula :</label>
                      <s:select name="report_p_kaunter_mula" style="width:145px;">
                          <s:option value="">Semua</s:option>
                          <c:forEach items="${actionBean.senaraiKaunter}" var="kaunter" >
                              <s:option value="${kaunter}">${kaunter}</s:option>
                          </c:forEach>
                          <%--<s:option value="01">01</s:option>
                          <s:option value="02">02</s:option>
                          <s:option value="03">03</s:option>
                          <s:option value="04">04</s:option>
                          <s:option value="05">05</s:option>
                          <s:option value="06">06</s:option>
                          <s:option value="07">07</s:option>
                          <s:option value="08">08</s:option>
                          <s:option value="09">09</s:option>
                          <s:option value="10">10</s:option>--%>
                      </s:select>
                  </p>
            </c:if>
            <c:if test="${reportname eq 'SPOCSenaraiTerimaanHarian(Umum).rdf'
                          or reportname eq 'SPOCSenaraiTerimaanHarian(Persekutuan).rdf'
                          or reportname eq 'SPOCSenaraiTerimaanHarian(Negeri).rdf'
                          or reportname eq 'HSL_R_23.rdf'
                          or reportname eq 'HSL_R_24.rdf'
                          or reportname eq 'HSL_R_25.rdf'
                          or reportname eq 'HSL_R_26.rdf'}">
                  <p>
                      <label>Kaunter Akhir :</label>
                      <s:select name="report_p_kaunter_tamat" style="width:145px;">
                          <s:option value="">Semua</s:option>
                          <c:forEach items="${actionBean.senaraiKaunter}" var="kaunter" >
                              <s:option value="${kaunter}">${kaunter}</s:option>
                          </c:forEach>
                          <%--<s:option value="01">01</s:option>
                          <s:option value="02">02</s:option>
                          <s:option value="03">03</s:option>
                          <s:option value="04">04</s:option>
                          <s:option value="05">05</s:option>
                          <s:option value="06">06</s:option>
                          <s:option value="07">07</s:option>
                          <s:option value="08">08</s:option>
                          <s:option value="09">09</s:option>
                          <s:option value="10">10</s:option>--%>
                      </s:select>
                  </p>
            </c:if>
            <c:if test="${reportname eq 'HSL_PP_NS.rdf'
                          or reportname eq 'HSL_R_01.rdf'
                          or reportname eq 'HSL_R_02.rdf'
                          or reportname eq 'HSL_R_03.rdf'
                          or reportname eq 'HSL_R_04.rdf'
                          or reportname eq 'HSL_R_42.rdf'}">
                  <p>
                      <label>Mod Bayar :</label>
                      <s:select name="report_p_mod_bayar" style="width:145px;">
                          <s:option value="">Semua</s:option>
                          <s:option value="B">Biasa</s:option>
                          <s:option value="L">Lewat</s:option>
                      </s:select>
                  </p>
            </c:if>
            <c:if test="${reportname eq 'HSLSenaraiResitBatal.rdf'
                          or reportname eq 'HSL_R_34.rdf'
                          or reportname eq 'HSL_ADD_10.rdf'}">
                  <p>
                      <label>Bulan :</label>
                      <s:select id="bulan" name="report_p_bulan" style="width:145px;">
                          <%--<s:option value="">--Sila Pilih--</s:option>--%>
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
            </c:if>
            <%--<c:if test="${reportname eq 'HSL_R_27.rdf'--%>
            <c:if test="${reportname eq 'HSL_ADD_10.rdf'
                          or reportname eq 'HSL_R_28.rdf'
                          or reportname eq 'HSL_R_29.rdf'
                          or reportname eq 'HSL_R_30.rdf'}">
                  <p>
                      <label>Tahun :</label>
                      <s:select id="tahun" name="report_p_tahun" style="width:145px;">
                          <%--<s:option value="">--Sila Pilih--</s:option>--%>
                          <s:options-collection collection="${actionBean.listYear}"/>
                      </s:select>
                  </p>
            </c:if>
            <c:if test="${reportname eq 'HSL_R_10.rdf'}">
                  <p>
                      <label>Tahun Dari :</label>
                      <s:select id="tahun" name="report_p_thn_dari" style="width:145px;">
                          <%--<s:option value="">--Sila Pilih--</s:option>--%>
                          <s:options-collection collection="${actionBean.listYear}"/>
                      </s:select>
                  </p>
            </c:if>
            <c:if test="${reportname eq 'HSL_R_10.rdf'}">
                  <p>
                      <label>Tahun Hingga :</label>
                      <s:select id="tahun" name="report_p_thn_hingga" style="width:145px;">
                          <%--<s:option value="">--Sila Pilih--</s:option>--%>
                          <s:options-collection collection="${actionBean.listYear}"/>
                      </s:select>
                  </p>
            </c:if>
            <c:if test="${reportname eq 'HSL_R_06.rdf'
                          or reportname eq 'HSL_R_09.rdf'
                          or reportname eq 'HSL_R_10.rdf'
                          or reportname eq 'HSL_R_31.rdf'
                          or reportname eq 'HSL_R_31_1.rdf'
                          or reportname eq 'HSL_R_32.rdf'
                          or reportname eq 'HSL_ADD_01.rdf'}">
                  <p>
                      <label>Jenis Hakmilik :</label>
                      <s:select name="report_p_kod_hakmilik" style="width:200px;">
                          <s:option value="">SEMUA</s:option>
                          <s:options-collection collection="${listUtil.senaraiKodHakmilik}" label="nama" value="kod"/>
                      </s:select>
                  </p>
            </c:if>
            <c:if test="${reportname eq 'HSL_PP_NS.rdf'
                          or reportname eq 'HSL_R_01.rdf'
                          or reportname eq 'HSL_R_02.rdf'
                          or reportname eq 'HSL_R_03.rdf'
                          or reportname eq 'HSL_R_04.rdf'
                          or reportname eq 'HSL_R_05.rdf'
                          or reportname eq 'SPOCSenaraiTerimaanHarian(Umum).rdf'
                          or reportname eq 'SPOCSenaraiTerimaanHarian(Persekutuan).rdf'
                          or reportname eq 'SPOCSenaraiTerimaanHarian(Negeri).rdf'
                          or reportname eq 'HSL_R_21.rdf'
                          or reportname eq 'HSL_R_23.rdf'
                          or reportname eq 'HSL_R_24.rdf'
                          or reportname eq 'HSL_R_25.rdf'
                          or reportname eq 'HSL_R_26.rdf'
                          or reportname eq 'HSL_R_31.rdf'
                          or reportname eq 'HSL_R_31_1.rdf'
                          or reportname eq 'HSL_R_36.rdf'
                          or reportname eq 'HSL_ADD_02.rdf'
                          or reportname eq 'HSL_R_40.rdf'
                          or reportname eq 'HSL_R_41.rdf'
                          or reportname eq 'HSL_ADD_05.rdf'
                          or reportname eq 'HSL_ADD_09.rdf'
                          or reportname eq 'HSL_R_42.rdf'}">
                  <p>
                      <label>Tarikh Mula :</label>
                      <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                  </p>
            </c:if>
            <c:if test="${reportname eq 'HSL_PP_NS.rdf'
                          or reportname eq 'HSL_R_01.rdf'
                          or reportname eq 'HSL_R_02.rdf'
                          or reportname eq 'HSL_R_03.rdf'
                          or reportname eq 'HSL_R_04.rdf'
                          or reportname eq 'HSL_R_05.rdf'
                          or reportname eq 'SPOCSenaraiTerimaanHarian(Umum).rdf'
                          or reportname eq 'SPOCSenaraiTerimaanHarian(Persekutuan).rdf'
                          or reportname eq 'SPOCSenaraiTerimaanHarian(Negeri).rdf'
                          or reportname eq 'HSL_R_21.rdf'
                          or reportname eq 'HSL_R_23.rdf'
                          or reportname eq 'HSL_R_24.rdf'
                          or reportname eq 'HSL_R_25.rdf'
                          or reportname eq 'HSL_R_26.rdf'
                          or reportname eq 'HSL_R_31.rdf'
                          or reportname eq 'HSL_R_31_1.rdf'
                          or reportname eq 'HSL_R_36.rdf'
                          or reportname eq 'HSL_ADD_02.rdf'
                          or reportname eq 'HSL_R_40.rdf'
                          or reportname eq 'HSL_R_41.rdf'
                          or reportname eq 'HSL_ADD_05.rdf'
                          or reportname eq 'HSL_ADD_09.rdf'
                          or reportname eq 'HSL_R_42.rdf'}">
                  <p>
                      <label>Tarikh Tamat :</label>
                      <s:text id="trh_tamat" name="report_p_trh_tamat" class="datepicker" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                  </p>
            </c:if>
            <c:if test="${reportname eq 'HSLLaporanLogoffKutipan.rdf'}">
                <p>
                    <label>Tarikh Kutipan / Pungutan :</label>
                    <s:text id="trh_kutipan" name="report_p_trh_kutipan" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>
            </c:if>
            <%--<c:if test="${reportname eq 'HSL_R_01.rdf'
                          or reportname eq 'HSL_R_03.rdf'
                          or reportname eq 'HSL_R_04.rdf'}">
                  <p>
                      <label>Status :</label>
                      <s:select name="report_p_sts" style="width:145px;">
                          <s:option value="">Semua</s:option>
                          <s:option value="LUAR">Luar</s:option>
                          <s:option value="TEMPATAN">Tempatan</s:option>
                      </s:select>
                  </p>
            </c:if>--%>
            <c:if test="${reportname eq 'HSL_R_12.rdf'}">
                <p>
                    <label>Status Bayaran :</label>
                    <s:select name="report_p_sts_bayaran" style="width:145px;">
                        <s:option value="BELUM_BAYAR">Belum Bayar</s:option>
                        <s:option value="SUDAH_BAYAR">Sudah Bayar</s:option>
                    </s:select>
                </p>
            </c:if>
            <c:if test="${reportname eq 'HSL_PP_NS.rdf'}">
                <p>
                    <label>Cara Bayaran :</label>
                    <s:select name="report_p_kod_cara_bayar" style="width:145px;">
                        <s:option value="">Semua</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodCaraBayaran}" label="nama" value="kod"/>
                    </s:select>
                </p>
            </c:if>
            <c:if test="${reportname eq 'hasilRayuanKurangCukai.rdf'
                          or reportname eq 'hasilNotis6A.rdf'
                          or reportname eq 'hasilNotis8A.rdf'
                          or reportname eq 'SPOCFolder_Page.rdf'
                          or reportname eq 'hasilNotisGantian.rdf'}">
                  <p>
                      <label><em>*</em>ID Permohonan :</label>
                      <s:text id="id_mohon" name="report_p_id_mohon" onblur="this.value = this.value.toUpperCase();"/>&nbsp;
                  </p>
            </c:if>
            <c:if test="${reportname eq 'hasilSuratPeringatan.rdf'
                          or reportname eq 'hasilPembatalanNotis6A.rdf'}">
                  <p>
                      <label><em>*</em>ID Dasar :</label>
                      <s:text id="id_dasar" name="report_p_id_dasar" onblur="this.value = this.value.toUpperCase();"/>&nbsp;
                  </p>
            </c:if>
            <c:if test="${reportname eq 'hasilSuratGantiCekTakLakuResit.rdf'}">
                <p>
                    <label><em>*</em>ID Resit :</label>
                    <s:text id="id_resit" name="report_p_id_resit" onkeyup="validateNumber(this,this.value);"/>&nbsp;
                </p>
            </c:if>
            <c:if test="${reportname eq 'hasilPembatalanNotis6A.rdf'}">
                <p>
                    <label><em>*</em>ID Hakmilik :</label>
                    <s:text id="id_hakmilik" name="report_p_id_hakmilik" onblur="this.value = this.value.toUpperCase();"/>&nbsp;
                </p>
            </c:if>
            <c:if test="${reportname eq 'HSLSuratAkuanBerkanun.rdf'}">
                <p>
                    <label><em>*</em>No. Rujukan :</label>
                    <s:text id="no_ruj" name="report_p_no_ruj" onblur="this.value = this.value.toUpperCase();"/>&nbsp;
                </p>
            </c:if>
            <c:if test="${reportname eq 'HSL_R_21.rdf'}">
                <p>
                    <label>Agensi :</label>
                    <s:select name="report_p_kod_agensi" style="width:260px;">
                        <s:option value="">Semua</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodAgensiKutipan}" label="nama" value="kod"/>
                    </s:select>
                </p>
            </c:if>
            <c:if test="${reportname eq 'HSL_R_40.rdf'}">
                <p> <%--agensi khas untuk permohonan--%>
                    <label>Agensi :</label>
                    <s:select name="report_p_kod_agensi" style="width:450px;">
                        <s:option value="">Semua</s:option>
                        <s:options-collection collection="${actionBean.senaraiAgensiPermohonan}" label="nama" value="kod"/>
                    </s:select>
                </p>
            </c:if>
            <c:if test="${reportname eq 'HSL_ADD_01.rdf'}">
                <p>
                    <label>Tunggakan Dari :</label>
                    <s:text id="tahunTunggakan1" name="report_p_thn_dari" size="6"/>&nbsp; Hingga
                    <s:text id="tahunTunggakan2" name="report_p_thn_hingga" size="6"/>&nbsp; Tahun
                </p>
            </c:if>
            <c:if test="${reportname eq 'HSL_ADD_01.rdf'}">
                <p>
                    <label>Amaun Dari (RM) :</label>
                    <s:text name="report_p_min_amt" value="0.00" id="min" onkeyup="validateNumber(this, this.value)" onblur="fmtNumber(this.value,'min');"/>&nbsp;
                </p>
                <p>
                    <label>Amaun Hingga (RM) :</label>
                    <s:text name="report_p_max_amt" value="0.00" id="max" onblur="fmtNumber(this.value,'max');" onkeyup="validateNumber(this, this.value)"/>&nbsp;
                </p>
            </c:if>
            <c:if test="${reportname eq 'HSL_ADD_09.rdf'}">
                <p>
                    <label>Status Tuntutan :</label>
                    <s:select name="report_p_sts" style="width:185px;">
                        <s:option value="">Semua</s:option>
                        <s:option value="ST">Sedang Dituntut</s:option>
                        <s:option value="BC">Telah Bayar Cukai</s:option>
                    </s:select>
                </p>
            </c:if>
            <%--c:if test="${reportname eq 'HSL_R_02.rdf'}">
                <p>
                    <label>Status Kutipan :</label>
                    <s:select name="report_p_sts_kutip" style="width:185px;">
                        <s:option value="K">Keseluruhan</s:option>
                        <s:option value="T">Tempatan</s:option>
                    </s:select>
                </p>
            </c:if--%>
            <%--<c:if test="${reportname eq 'HSL_ADD_10.rdf'}">
                <p>
                    <label>Jenis Transaksi :</label>
                    <s:select name="report_p_kod_trans" style="width:260px;">
                        <s:options-collection collection="${actionBean.senaraiKodTransaksi}" label="nama" value="kod"/>
                    </s:select>
                </p>
            </c:if>--%>

            <br>
            <p align="center">
                <s:button name="" id="simpan" value="Papar" class="btn" onclick="doSubmit(this.form);"/>&nbsp;
                <s:reset name="" value="Isi Semula" class="btn"/>&nbsp;
                <s:button name="" id="close" value="Tutup" onclick="self.close();" class="btn"/>&nbsp;
            </p>
        </fieldset >
    </div>
</s:form>
