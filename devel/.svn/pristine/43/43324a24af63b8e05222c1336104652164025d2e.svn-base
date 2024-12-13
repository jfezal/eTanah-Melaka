<%-- 
    Document   : laporan_utiliti
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
        $("#trh_pungutan").val(date.getDate()+"/"+(date.getMonth()+1)+"/"+date.getFullYear());
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
        
            if ((report == 'STRLaporMohon_NS.rdf') && $('#tahun').val() == "") {
                alert("Sila pilih tahun terlebih dahulu.");
                $('#tahun').focus();
                $.unblockUI();
            } else {
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
          
             if(value == "lot")
             {
                 $('#pindah_milik1').show();  
                 $('#pindah_milik2').hide();     
                 $('#pindah_milik3').hide();     
             }            
             else if(value == "hakmilik")
             {
                 $('#pindah_milik1').hide();
                 $('#pindah_milik2').show();
                 $('#pindah_milik3').hide();
             }
             else if(value == "skim")
             {
                 $('#pindah_milik1').hide();
                 $('#pindah_milik2').hide();
                 $('#pindah_milik3').show();
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

<s:form beanclass="etanah.view.pengambilan.LaporanStatistikActionBean">
    <div id="display" class="subtitle">
        <s:hidden id="reportname" name="reportName"/>
        <fieldset class="aras1">

            <lagend>
                <%--<c:if test="${actionBean.reportName eq 'ACQkenyataankemajuan.rdf'}">--%>
                <c:if test="${actionBean.reportName eq 'ACQLU831A_MLK.rdf.rdf'}">
                    Kenyataan Kemajuan Pengambilan Tanah (Tanah 188) - 3(1)(a)
                </c:if>

                <c:if test="${actionBean.reportName eq 'ACQLU831B_MLK.rdf.rdf'}">
                    Kenyataan Kemajuan Pengambilan Tanah (Tanah 188) - 3(1)(b)
                </c:if>
                <c:if test="${actionBean.reportName eq 'ACQLU831C_MLK.rdf.rdf'}">
                    Kenyataan Kemajuan Pengambilan Tanah (Tanah 188) - 3(1)(c)
                </c:if>

                <c:if test="${actionBean.reportName eq 'ACQtanahkePTG.rdf'}">
                    Laporan  Permohonan Pengambilan Tanah  Ke PTG
                </c:if>

                <c:if test="${actionBean.reportName eq 'ACQlaporanteknikal.rdf'}">
                    Laporan Teknikal
                </c:if>

                <c:if test="${actionBean.reportName eq 'ACQlaporanEFT.rdf'}">
                    Laporan Pembayaran oleh Agensi melalui EFT kepada berkepentingan
                </c:if>

                <c:if test="${actionBean.reportName eq 'ACQtanahkePTG.rdf'}">
                    Laporan Permohonan Pengambilan Tanah Daerah (Mesyuarat Pengurusan)
                </c:if>

            </lagend>         
            <legend></legend>
            <c:set value="${actionBean.reportName}" var="reportname"/>
            <%--<s:hidden name="lapormohon" value="null" id="lapor_mohon"/>--%>
            <c:set var="disable" value="false"/>
            <c:if test="${actionBean.pengguna.kodCawangan.kod ne '00'}">
                <c:set var="disable" value="true"/>
            </c:if>

            <br/><br/>
            <c:if test="${reportname eq 'ACQlaporanteknikal.rdf' or reportname eq 'ACQtanahkePTG.rdf'
                          or reportname eq 'ACQlaporanEFT.rdf' or reportname eq 'ACQLU831A_MLK.rdf' or reportname eq 'ACQLU831B_MLK.rdf' or reportname eq 'ACQLU831C_MLK.rdf'
                  }">    
                <p>
                    <label>Tahun :</label>
                    <s:select id="tahun" name="report_p_tahun" style="width:260px;">
                        <%--<s:option value="">--Sila Pilih--</s:option>--%>
                        <s:options-collection collection="${actionBean.listYear}"/>
                    </s:select>
                </p>
            </c:if>       
            <br>
            <%--   <div id="btnPapar">
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
               </div>--%>
            <%-- <div id="btntahun">--%>
            <p align="center">
                <s:button name="" id="simpan" value="Papar" class="btn" onclick="doSubmit(this.form);"/>&nbsp;
                <s:reset name="" value="Isi Semula" class="btn"/>&nbsp;
                <s:button name="" id="close" value="Tutup" onclick="self.close();" class="btn"/>&nbsp;
            </p>
            <%--   </div>--%>
        </fieldset >
    </div>
</s:form>
