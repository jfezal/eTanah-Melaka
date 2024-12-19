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
        $("#trh_mula2").val(date.getDate()+"/"+(date.getMonth()+1)+"/"+date.getFullYear());
        $("#trh_tamat2").val(date.getDate()+"/"+(date.getMonth()+1)+"/"+date.getFullYear());
        $("#trh_pungutan").val(date.getDate()+"/"+(date.getMonth()+1)+"/"+date.getFullYear());
        $("#tahun").val(date.getFullYear());
        $("#daerah").val(kodDaerah);
        //$("#caw").val(kodCaw);
        $('#pindah_milik1').hide();  
        $('#pindah_milik2').hide();     
        $('#pindah_milik3').hide();
        $('#pindah_milik4').show();
        $('#pindah_milik5').hide();
        $('#lapor_mohon1').show();  
        $('#lapor_mohon2').hide();
        $('#btntarikh').hide();
        $('#btnpindahmilik').hide();
        if( report == 'STRLaporMohon_NS.rdf'){
            $('#btntahun').show();
            $('#btnPapar').hide();
        }
        else{
            $('#btnPapar').show();
            $('#btntahun').hide();
        }
        if( report == 'STRLaporMohon_NS.rdf'){
            $('#trh_mula').hide();
            $('#trh_mula').val() == "";
            $('#trh_tamat').val()== "";
            $('#trh_tamat').hide();
        }
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
        
        
    <%--if((report == 'SPOCLaporanPenjenisanHasil_NS.rdf'
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
        || report == 'HSL_R_41.rdf'
        || report == 'HSL_R_03.rdf'
        || report == 'HSL_R_04.rdf'
        || report == 'HSL_R_05.rdf'
        || report == 'HSL_R_21.rdf'
        || report == 'HSL_R_30.rdf'

            || report == 'HSL_R_01_MLK.rdf'
            || report == 'HSL_R_01_FED_MLK.rdf'
            || report == 'HSL_R_01_STATE_MLK.rdf'
            || report == 'HSL_R_02_MLK.rdf'
            || report == 'HSL_R_03_MLK.rdf'
            || report == 'HSL_R_04_MLK.rdf'
            || report == 'HSL_PP_MLK.rdf'
            || report == 'HSL_R_05_MLK.rdf'
            || report == 'HSL_R_06_MLK.rdf'
            || report == 'HSL_R_07_MLK.rdf'
            || report == 'HSL_R_09_MLK.rdf'
            || report == 'HSL_R_10_MLK.rdf'
            || report == 'HSL_R_14_MLK.rdf'
            || report == 'HSL_R_16_MLK.rdf'
            || report == 'HSL_R_17_MLK.rdf') && $('#caw').val() == ""){
            alert("Sila pilih Cawangan terlebih dahulu.");
            $('#caw').focus();
            $.unblockUI();
        }else{--%>
                var url = form.replace(/&/g, "%26");
                window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url="+url, "eTanah",
                "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
    <%--f.action = "${pageContext.request.contextPath}/reportGeneratorServlet?"+form;
    f.submit();--%>
            $.unblockUI();
    <%--}--%>        
        }
        function doSubmittarikh(f){
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
            var urusan = document.getElementById("urusan").value;
            var mula = document.getElementById("trh_mula").value;
            var tamat = document.getElementById("trh_tamat").value;
            var pilihan = document.getElementById("lapor_tarikh").value;
        
            if ((report == 'STRLaporMohon_NS.rdf') && $('#trh_mula').val() == "") {
                alert("Sila pilih tarikh mula terlebih dahulu.");
                $('#trh_mula').focus();
                $.unblockUI();
            } else {
                if(pilihan == 'tarikh'){
                    //var url = form.replace(/&/g, "%26");
                    var url = form.replace('STRLaporMohon_NS.rdf', "STRLaporMohon2_NS.rdf");
                    //alert(url);
                    //            window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url=" + url, "eTanah",
                    //            "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
                    //            if ($('#tahun2').val() == ""){
                
                    window.open("${pageContext.request.contextPath}/reportGeneratorServlet?"+"&reportName="+"STRLaporMohon2_NS.rdf"+"&report_p_urusan="+urusan+"&report_p_tarikhmula="+mula+"&report_p_tarikhakhir="+tamat, "eTanah",
                    "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");}
            
                else {
                    window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url=" + url, "eTanah",
                    "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
                }
            
            

            
                $.unblockUI();
            } 
        }
        function doSubmittahun(f){
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
            var urusan = document.getElementById("urusan").value;
            var tahun = document.getElementById("tahun").value;
           
            var pilihan = document.getElementById("lapor_tahun").value;
            
            
            if ((report == 'STRLaporMohon_NS.rdf') ) {
                //  if ((report == 'STRLaporMohon_NS.rdf') && $('#tahun').val() == "") {
                // alert("Sila pilih tahun terlebih dahulu.");
                // $('#tahun').focus();
                //  $.unblockUI();
                //  } else {
                if(pilihan == 'tahun'){
                    //var url = form.replace(/&/g, "%26");
                    var url = form.replace(/&/g, "%26");
                    //alert(url);
                    //            window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url=" + url, "eTanah",
                    //            "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
                    //            if ($('#tahun2').val() == ""){
                
    <%--$.post("${pageContext.request.contextPath}/strata/laporanstrata?")
        .succss(function(a,b,c){
            if(a){
                
            }else{
                
            }
        });--%>
                        window.open("${pageContext.request.contextPath}/reportGeneratorServlet?"+"&reportName="+"STRLaporMohon_NS.rdf"+"&report_p_urusan="+urusan+"&report_p_tahun="+tahun, "eTanah",
                        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");}
            
                    else {
                        window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url=" + url, "eTanah",
                        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
                    }
                               
                    $.unblockUI();
                } 
            }
            
            function doSubmitPindahMilik(f){
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
                                                         
                var pilihan = document.getElementById("lapor_pmilik").value;
                
                if (pilihan == '' || pilihan == null ) {
                    var pilihan = 'PeratusanPindahMilik';
                }
                       
                if ((report == 'STRPindahMilik_NS.rdf') ) {
                 
                    if(pilihan == 'PerinciPindahMilik'){
                        //var url = form.replace(/&/g, "%26");
                        //var url = form.replace(/&/g, "%26");
                        var kodHakmilik = '';
                        var noHakmilik = '';
                        var no_skim = '';
                        var nobuku = '';
                        
                        var trh_mula = document.getElementById("trh_mula2").value;
                        var trh_tamat = document.getElementById("trh_tamat2").value; 
                        
                        var url = form.replace('STRPindahMilik_NS.rdf', "STRPerinciPindahMilik_NS.rdf");
                  
                        window.open("${pageContext.request.contextPath}/reportGeneratorServlet?"+"&reportName="+"STRPerinciPindahMilik_NS.rdf"+"&report_p_tarikhmula="+trh_mula+"&report_p_tarikhakhir="+trh_tamat, "eTanah",
                        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
                    
                    }  else if(pilihan == 'PeratusanPindahMilik') {
                       
                        var url = form.replace(/&/g, "%26"); 
                                         
                        window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url=" + url, "eTanah",
                        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
                    }
                    else {
                        
                        var kodHakmilik = document.getElementById("kodHakmilik").value;
                        var noHakmilik = document.getElementById("noHakmilik").value;
                        var no_skim = document.getElementById("no_skim").value;
                        var nobuku = document.getElementById("nobuku").value; 
                        var kodcaw = document.getElementById("caw").value;
                        var bpm = document.getElementById("bpm").value; 
                        
                        if(pilihan == 'hakmilik'){
                            var nobuku = '';
                            var no_skim = '';
                            var kodcaw = '';
                            var bpm = '';
                        }
                        if(pilihan == 'skim'){
                            var kodHakmilik = '';
                            var noHakmilik = '';
                            var nobuku = '';
                            var kodcaw = '';
                            var bpm = '';
                        }
                        if(pilihan == 'nobuku'){
                            var kodHakmilik = '';
                            var noHakmilik = '';
                            var no_skim = '';
                        }
                        
                        window.open("${pageContext.request.contextPath}/reportGeneratorServlet?"+"&reportName="+"STRPindahMilikSkim_NS.rdf"+"&report_p_kod_hakmilik="+kodHakmilik+"&report_p_nohm="+noHakmilik+"&report_p_noskim="+no_skim+"&report_p_nobuku="+nobuku+"&report_p_kodcaw="+kodcaw+"&report_p_bpm="+bpm, "eTanah",
                        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
                        
                    }
                               
                    $.unblockUI();
                } 
            }

               
            function changeLaporMohon(){
                var lapor=document.getElementById("lapor_tahun").value;
                if(lapor == 'tahun')
                {
                    $('#lapor_mohon1').show();  
                    $('#lapor_mohon2').hide();  
                    $('#btnPapar').hide();  
                    $('#btntarikh').hide();  
                    $('#btntahun').show();  
                }            
    <%-- else if(value == "lapormohon2")
     {
         $('#lapor_mohon1').hide();  
         $('#lapor_mohon2').show();      
     }      --%>   
         }  
               
         function changeLaporMohontarikh(){
             var lapor=document.getElementById("lapor_tarikh").value;
             if(lapor == 'tarikh')
             {
                 $('#lapor_mohon1').hide();  
                 $('#lapor_mohon2').show(); 
                 $('#btnPapar').hide(); 
                 $('#btntahun').hide(); 
                 $('#btntarikh').show(); 
                 $('#trh_mula').show();
                 $('#trh_tamat').show();                 
             }
         }

         function changebpindahmilik(value){
             
             $('#btnPapar').hide();  
             $('#btntarikh').hide(); 
             $('#btntahun').hide();
             $('#btnpindahmilik').show();
          
             if(value == "hakmilik")
             {
                 $('#pindah_milik1').show();  
                 $('#pindah_milik2').hide();     
                 $('#pindah_milik3').hide();
                 $('#pindah_milik4').hide();     
                 $('#pindah_milik5').hide();               
             }            
             else if(value == "skim")
             {
                 $('#pindah_milik1').hide();
                 $('#pindah_milik2').show();
                 $('#pindah_milik3').hide();
                 $('#pindah_milik4').hide();     
                 $('#pindah_milik5').hide();
             }
             else if(value == "nobuku")
             {
                 $('#pindah_milik1').hide();
                 $('#pindah_milik2').hide();
                 $('#pindah_milik3').show();
                 $('#pindah_milik4').hide();     
                 $('#pindah_milik5').hide();
             }
             else if(value == "PeratusanPindahMilik")
             {
                 $('#pindah_milik1').hide();
                 $('#pindah_milik2').hide();
                 $('#pindah_milik3').hide();
                 $('#pindah_milik4').show();     
                 $('#pindah_milik5').hide();
             }
             else if(value == "PerinciPindahMilik")
             {
                 $('#pindah_milik1').hide();
                 $('#pindah_milik2').hide();
                 $('#pindah_milik3').hide();
                 $('#pindah_milik4').hide();     
                 $('#pindah_milik5').show();
             }
             else
             {
                 $('#pindah_milik1').hide();  
                 $('#pindah_milik2').hide();     
                 $('#pindah_milik3').hide();     
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

         function validateYearLength(value){
             var plength = value.length;
             if(plength != 4){
                 alert('"Tahun" yang dimasukkan salah.');
                 $('#tahun').val("");
                 $('#tahun').focus();
             }
         }
        
         function doFilterKodLot(kodLot){
             var report = $("#reportname").val();
             if(kodLot != ""){
                 var url = '${pageContext.request.contextPath}/strata/laporanstrata?doCollectKaunter&kodCawangan=' + kodCaw+'&reportNama='+report;
                 $.get(url,
                 function(data){
                     $('#display').html(data);
                     $('#caw').val(kodCaw);
                     $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                 });
             }
         }
        

         function doFilterKaunter(kodCaw){
             var report = $("#reportname").val();
             if(kodCaw != ""){
                 var url = '${pageContext.request.contextPath}/strata/laporanstrata?doCollectKaunter&kodCawangan=' + kodCaw+'&reportNama='+report;
                 $.get(url,
                 function(data){
                     $('#display').html(data);
                     $('#caw').val(kodCaw);
                     $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                 });
             }
         }

         function doFilterDaerah(kodCaw2){
             var report = $("#reportname").val();
             if(kodCaw2 != null){
                 var url = '${pageContext.request.contextPath}/strata/laporanstrata?doFilterDaerahBPM&kodCawangan=' + kodCaw2+'&reportNama='+report;
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
                var url = '${pageContext.request.contextPath}/strata/laporanstrata?doFilterBPM&kodDaerah=' + kodDaerah1+'&reportNama='+report+'&kodUrusan'+report;
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
        
    
</script>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif"
     width="50" height="50" style="display: none" alt=""/>

<s:form beanclass="etanah.view.strata.laporan.StrataLaporanActionBean">
    <div id="display" class="subtitle">
        <s:hidden id="reportname" name="reportName"/>
        <fieldset class="aras1">

            <lagend>
                <c:if test="${actionBean.reportName eq 'STRLaporanPrestasi_NS.rdf'}">
                    Laporan Prestasi Permohonan Hakmilik Strata
                </c:if>

                <c:if test="${actionBean.reportName eq 'STRLaporPerinciPrestasi_NS.rdf'}">
                    Laporan Terperinci Prestasi Permohonan Hakmilik Strata
                </c:if>

                <c:if test="${actionBean.reportName eq 'STRLaporMohon_NS.rdf'}">
                    Laporan Keseluruhan Permohonan Hakmilik Strata 
                </c:if>

                <c:if test="${actionBean.reportName eq 'STRLaporKosRendah_NS.rdf'}">
                    Laporan Statistik Hakmilik Strata Kos Rendah
                </c:if>

                <c:if test="${actionBean.reportName eq 'STRPindahMilik_NS.rdf'}">
                    Laporan Statistik Pindah Milik Strata
                </c:if>

                <c:if test="${actionBean.reportName eq 'STRPeratusPindahMilik_NS.rdf'}">
                    Laporan Status Pindahmilik yang telah mencapai 25%
                </c:if>

                <c:if test="${actionBean.reportName eq 'STRBukuDaftar_NS.rdf'}">
                    Laporan Buku Daftar Strata
                </c:if>

                <c:if test="${actionBean.reportName eq 'STRLaporanPenguatkuasaan_NS.rdf'}">
                    Laporan Penguatkuasaan Akta Hakmilik Strata 1985
                </c:if>

                <c:if test="${actionBean.reportName eq 'STRPenamatanStrata_NS.rdf'}">
                    Senarai Hakmilik Strata Yang Telah Ditamatkan
                </c:if>

                <c:if test="${actionBean.reportName eq 'STRBayarKelulusan_NS.rdf'}">
                    Senarai Pemohon Yang Belum Menjelaskan Bayaran Kelulusan
                </c:if>

                <c:if test="${actionBean.reportName eq 'STRStatusHakmilik_NS.rdf'}">
                    Senarai Pemohon Yang Belum Mengambil Hakmilik Strata
                </c:if>

                <c:if test="${actionBean.reportName eq 'STRBdnUrus_NS.rdf'}">
                    Senarai Nama Perbadanan Pengurusan
                </c:if>
            </lagend>         
            <legend></legend>
            <c:set value="${actionBean.reportName}" var="reportname"/>
            <%--<s:hidden name="lapormohon" value="null" id="lapor_mohon"/>--%>
            <c:set var="disable" value="false"/>
            <c:if test="${actionBean.pengguna.kodCawangan.kod ne '00'}">
                <c:set var="disable" value="true"/>
            </c:if>

            <c:if test="${reportname eq 'STRLaporanPrestasi_NS.rdf' }">    
                <p>
                    <label>Tahun :</label>
                    <s:select id="tahun" name="report_p_tahun" style="width:260px;">

                        <s:options-collection collection="${actionBean.listYear}"/>
                    </s:select>
                </p>
            </c:if>

            <c:if test="${reportname eq 'STRLaporPerinciPrestasi_NS.rdf'
                          or reportname eq 'STRLaporKosRendah_NS.rdf'           
                          or reportname eq 'STRBukuDaftar_NS.rdf' 
                          or reportname eq 'STRLaporanPenguatkuasaan_NS.rdf' 
                          or reportname eq 'STRPenamatanStrata_NS.rdf'
                          or reportname eq 'STRBayarKelulusan_NS.rdf'
                          or reportname eq 'STRStatusHakmilik_NS.rdf'
                          or reportname eq 'STRBdnUrus_NS.rdf' 
                          or reportname eq 'STRPeratusPindahMilik_NS.rdf' }">
                  <p>
                      <label>Tarikh Mula :</label>
                      <s:text id="trh_mula" name="report_p_tarikhmula" class="datepicker" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                  </p>
                  <p>
                      <label>Tarikh Tamat :</label>
                      <s:text id="trh_tamat" name="report_p_tarikhakhir" class="datepicker" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                  </p>
            </c:if>

            <%--c:if test="${reportname eq 'STRBukuDaftar_NS.rdf' 
                          or reportname eq 'STRLaporanPenguatkuasaan_NS.rdf' 
                          or reportname eq 'STRPenamatanStrata_NS.rdf' 
                          or reportname eq 'STRBayarKelulusan_NS.rdf' 
                          or reportname eq 'STRStatusHakmilik_NS.rdf' 
                  }">    
                <p>
                    <label>Sehingga Tarikh :</label>
                    <s:text id="trh" name="report_p_tarikh" class="datepicker" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>
            </c:if--%>

            <c:if test="${ reportname eq 'STRLaporMohon_NS.rdf' }">  
                <%--<s:hidden name="lapormohon" value="lapormohon1" id="lapor_mohon"/>--%>
                <%--   <p> <s:radio name="lapormohon" value="tahun" id="lapor_mohon" onclick ="changeLaporMohon()"/> Tahun
                           <s:radio name="lapormohon" value="tarikh" id="lapor_mohon2" onclick ="changeLaporMohontarikh()"/> Tarikh                  
                       </p>--%>
                <p>
                    <label>Mengikut :</label>
                    <input type="radio" name="lapormohon" checked="checked" value="tahun" id="lapor_tahun" onclick ="changeLaporMohon()"/> Tahun
                    <input type="radio" name="lapormohon" value="tarikh" id="lapor_tarikh" onclick ="changeLaporMohontarikh()"/> Tarikh

                </p>
                <%--     <input type="radio" checked="checked" name="lapormohon" value="tahun" id="lapor_mohon" onclick ="changeLaporMohon()"/> Tahun
                     <input type="radio" name="lapormohon" value="tarikh" id="lapor_mohon2" onclick ="changeLaporMohontarikh()"/> Tarikh  --%>                
                <p>
                    <label>Jenis Permohonan :</label>
                    <s:select id="urusan" name="report_p_urusan" style="width:640px;">                      
                        <%--<s:option value="">Semua</s:option>--%>
                        <s:option value="PBBS">Permohonan Pecah Bahagi Bangunan</s:option>
                        <s:option value="PBBD">Permohonan Pecah Bahagi Bangunan dan Tanah</s:option>
                        <s:option value="PBS">Permohonan Pecah Bahagi Bangunan Sementara</s:option>
                        <s:option value="PBTS">Permohonan Pecah Bahagi Bangunan atau Bangunan dan Tanah bagi Bangunan Sementara</s:option>
                        <s:option value="PBBSS">Permohonan Pecah Bahagi Bangunan bagi Bangunan Sementara Apabila Bangunan Siap</s:option>
                        <s:option value="PHPP">Permohonan Penyatuan Petak</s:option>
                        <s:option value="PHPC">Permohonan Pecah Petak</s:option>                      
                    </s:select>
                </p>

                <div id="lapor_mohon1" class="subtitle">
                    <p>
                        <label>Tahun :</label>
                        <s:select id="tahun" name="report_p_tahun" style="width:260px;">
                            <%--<s:option value="">--Sila Pilih--</s:option>--%>
                            <s:options-collection collection="${actionBean.listYear}"/>
                        </s:select>
                    </p>
                </div>

                <div id="lapor_mohon2" class="subtitle">
                    <p>
                        <label>Tarikh Mula :</label>
                        <s:text id="trh_mula" name="report_p_tarikhmula" class="datepicker" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>

                    <p>
                        <label>Tarikh Tamat :</label>
                        <s:text id="trh_tamat" name="report_p_tarikhakhir" class="datepicker" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                </div>
            </c:if>

            <c:if test="${reportname eq 'STRLaporanPrestasi_NS.rdf'
                          or reportname eq 'STRLaporPerinciPrestasi_NS.rdf'
                          or reportname eq 'STRLaporKosRendah_NS.rdf'
                          or reportname eq 'STRBukuDaftar_NS.rdf' 
                          or reportname eq 'STRLaporanPenguatkuasaan_NS.rdf' 
                          or reportname eq 'STRPenamatanStrata_NS.rdf' 
                          or reportname eq 'STRBayarKelulusan_NS.rdf' 
                          or reportname eq 'STRStatusHakmilik_NS.rdf' 
                          or reportname eq 'STRPeratusPindahMilik_NS.rdf'
                  }">
                <p>
                    <label>Cawangan :</label>
                    <s:select id="caw" name="report_p_kodcaw" style="width:260px;" disabled="${disable}">
                        <%--     <s:option value="">Semua</s:option>--%>
                        <s:option value="">Semua</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>
                    </s:select>
                    <s:hidden name="report_p_kodcaw" value="${actionBean.kodCaw}"/>
                </p>
            </c:if> 


            <c:if test="${reportname eq 'STRPindahMilik_NS.rdf'  }">

                <p>
                    <label>Jenis Laporan :</label>
                    <s:select id="lapor_pmilik" name="report_p_urusan" style="width:640px;" onchange="javaScript:changebpindahmilik(this.value)">                      
                        <%--<s:option value="">Semua</s:option>--%>
                        <s:option value="PeratusanPindahMilik" selected="selected">Laporan Peratusan Pindah Milik Bagi Skim Strata</s:option>
                        <s:option value="PerinciPindahMilik">Laporan Terperinci Peratusan Pindah Milik Bagi Skim Strata</s:option>
                        <%--<s:option value="Pidahmilik25">Laporan Status Pindah Milik yang telah Mencapai 25%</s:option>--%>                                                        
                        <s:option value="hakmilik">Laporan Statistik Pindah Milik Strata Mengikut Hakmilik</s:option> 
                        <s:option value="skim">Laporan Statistik Pindah Milik Strata Mengikut Nama Skim</s:option>
                        <%--<s:option value="nobuku">Laporan Statistik Pindah Milik Strata Mengikut No Buku Daftar Strata</s:option>--%>                                      
                    </s:select>
                </p>

                <!--                
                           
                                <p>
                                    <label>Mengikut :</label>
                                    <input type="radio" name="pindahmilik" value="hakmilik" checked="checked" id="lpr1" onclick ="javaScript:changebpindahmilik(this.value)"/> No Hakmilik
                                    <input type="radio" name="pindahmilik" value="skim" id="lpr2" onclick ="javaScript:changebpindahmilik(this.value)"/> Nama Skim
                                    <input type="radio" name="pindahmilik" value="nobuku" id="lpr3" onclick ="javaScript:changebpindahmilik(this.value)"/> No Buku Daftar Strata
                
                                </p>-->

                <div id="pindah_milik1" class="subtitle">
                    <!--<p align="center"><font color='red' size ="+1"> atau    </font></p>-->                
                    <p>
                        <label>Jenis Hakmilik :</label>
                        <s:select id="kodHakmilik" name="report_p_kod_hakmilik" style="width:290px;">
                            <s:option value="">--Sila Pilih--</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodHakmilikTetapNS}" label="nama" value="kod"/>
                        </s:select> <br/>
                        <label>No Hakmilik :</label>
                        <s:text id="noHakmilik" name="report_p_nohm" onblur="this.value = this.value.toUpperCase();"/>&nbsp;
                    </p>
                    <!--<p align="center"><font color='red' size ="+1"> atau    </font></p>-->  
                </div>
                <div id="pindah_milik2" class="subtitle">
                    <p>
                        <label>Nama Skim :</label>
                        <s:text id="no_skim" name="report_p_noskim" onblur="this.value = this.value.toUpperCase();" size="40" maxlength="50"/>&nbsp;
                    </p>
                </div>

                <div id="pindah_milik3" class="subtitle">

                    <%--<s:hidden name="report_p_kodLot" value="${actionBean.kodLot}"/>--%>
                    <p>
                        <label>Cawangan :</label>
                        <s:select id="caw" name="report_p_kodcaw" style="width:260px;" disabled="${disable}">
                            <%--     <s:option value="">Semua</s:option>--%>
                            <s:option value="">Semua</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>
                        </s:select>
                        <s:hidden name="report_p_kodcaw" value="${actionBean.kodCaw}"/>
                    </p>
                    <p>
                        <label>Bandar/Pekan/Mukim :</label>
                        <s:select id="bpm" name="report_p_bpm" style="width:200px;">
                            <s:option value="">Semua</s:option>
                            <%--<s:options-collection collection="${listUtil.senaraiKodBandarMukim}" label="nama" value="kod"/>--%>
                            <s:options-collection collection="${actionBean.senaraiBPM}" label="nama" value="kod"/>
                        </s:select>
                    </p>


                    <p>
                        <label>No Buku Daftar Strata :</label>      
                        <s:text id="nobuku" name="report_p_nobuku" onblur="this.value = this.value.toUpperCase();"/>&nbsp;
                    </p>
                </div>

                <div id="pindah_milik4" class="subtitle">
                    <!--<p align="center"><font color='red' size ="+1"> atau    </font></p>-->                

                    <p>
                        <label>Tarikh Mula :</label>
                        <s:text id="trh_mula" name="report_p_tarikhmula" class="datepicker" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                    <p>
                        <label>Tarikh Tamat :</label>
                        <s:text id="trh_tamat" name="report_p_tarikhakhir" class="datepicker" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                    <!--<p align="center"><font color='red' size ="+1"> atau    </font></p>-->  
                </div>


                <div id="pindah_milik5" class="subtitle">
                    <!--<p align="center"><font color='red' size ="+1"> atau    </font></p>-->                
                    <p>
                        <label>Cawangan :</label>
                        <s:select id="caw" name="report_p_kodcaw" style="width:260px;" disabled="${disable}">
                            <%--     <s:option value="">Semua</s:option>--%>
                            <s:option value="">Semua</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>
                        </s:select>
                        <s:hidden name="report_p_kodcaw" value="${actionBean.kodCaw}"/>

                    </p>

                    <p>
                        <label>Tarikh Mula :</label>
                        <s:text id="trh_mula2" name="report_p_tarikhmula" class="datepicker" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                    <p>
                        <label>Tarikh Tamat :</label>
                        <s:text id="trh_tamat2" name="report_p_tarikhakhir" class="datepicker" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>
                </div>

            </c:if>

            <c:if test="${reportname eq 'STRLaporanPenguatkuasaan_NS.rdf' }">
                <p>
                    <label>Urusan :</label>
                    <s:select name="report_p_urusan" style="width:640px;">                      
                        <%--<s:option value="">Semua</s:option>--%>
                        <s:option value="P8" selected="selected">Penguatkuasaan Seksyen 8 Akta Hakmilik Strata 1985</s:option>
                        <s:option value="P14A">Penguatkuasaan Seksyen 14A Akta Hakmilik Strata 1985</s:option>      
                        <s:option value="P22A">Penguatkuasaan Seksyen 22A Akta Hakmilik Strata 1985</s:option>   
                        <s:option value="P22B">Penguatkuasaan Seksyen 22B Akta Hakmilik Strata 1985</s:option>      
                    </s:select>
                </p>
            </c:if>

            <c:if test="${reportname eq 'STRLaporanPrestasi_NS.rdf'
                          or reportname eq 'STRLaporPerinciPrestasi_NS.rdf'                                    
                          or reportname eq 'STRBayarKelulusan_NS.rdf' 
                          or reportname eq 'STRStatusHakmilik_NS.rdf' 
                          or reportname eq 'STRPeratusPindahMilik_NS.rdf'
                  }">
                <!--or reportname eq 'STRBukuDaftar_NS.rdf'--> 
                <p>
                    <label>Jenis Permohonan :</label>
                    <s:select name="report_p_urusan" style="width:640px;">                      
                        <%--<s:option value="">Semua</s:option>--%>
                        <s:option value="PBBS">Permohonan Pecah Bahagi Bangunan</s:option>
                        <s:option value="PBBD">Permohonan Pecah Bahagi Bangunan dan Tanah</s:option>
                        <s:option value="PBS">Permohonan Pecah Bahagi Bangunan Sementara</s:option>
                        <s:option value="PBTS">Permohonan Pecah Bahagi Bangunan atau Bangunan dan Tanah bagi Bangunan Sementara</s:option>
                        <s:option value="PBBSS">Permohonan Pecah Bahagi Bangunan bagi Bangunan Sementara Apabila Bangunan Siap</s:option>
                        <s:option value="PHPP">Permohonan Penyatuan Petak</s:option>
                        <s:option value="PHPC">Permohonan Pecah Petak</s:option>                      
                    </s:select>
                </p>
            </c:if>
            <br>
            <div id="btnPapar">
                <p align="center">
                    <s:button name="" id="simpan" value="Papar" class="btn" onclick="doSubmit(this.form);"/>&nbsp;
                    <s:reset name="" value="Isi Semula" class="btn"/>&nbsp;
                    <s:button name="" id="close" value="Tutup" onclick="self.close();" class="btn"/>&nbsp;
                </p>
            </div>
            <div id="btntarikh">
                <p align="center">
                    <s:button name="" id="simpan" value="Papar" class="btn" onclick="doSubmittarikh(this.form);"/>&nbsp;
                    <s:reset name="" value="Isi Semula" class="btn"/>&nbsp;
                    <s:button name="" id="close" value="Tutup" onclick="self.close();" class="btn"/>&nbsp;
                </p>
            </div>

            <div id="btntahun">
                <p align="center">
                    <s:button name="" id="simpan" value="Papar" class="btn" onclick="doSubmittahun(this.form);"/>&nbsp;
                    <s:reset name="" value="Isi Semula" class="btn"/>&nbsp;
                    <s:button name="" id="close" value="Tutup" onclick="self.close();" class="btn"/>&nbsp;
                </p>
            </div>
            <div id="btnpindahmilik">
                <p align="center">
                    <s:button name="" id="simpan" value="Papar" class="btn" onclick="doSubmitPindahMilik(this.form);"/>&nbsp;
                    <s:reset name="" value="Isi Semula" class="btn"/>&nbsp;
                    <s:button name="" id="close" value="Tutup" onclick="self.close();" class="btn"/>&nbsp;
                </p>
            </div>
        </fieldset >
    </div>
</s:form>
