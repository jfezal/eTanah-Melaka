<%-- 
    Document   : laporan_maklumaturusan_param
    Created on : May 18, 2010, 5:02:51 PM
    Author     : wan.fairul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<html>
    <head>
        <link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
        <link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
        <script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
        <link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
        <link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>


        <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">

    <script type="text/javascript">
        $(document).ready( function(){
            $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
            var m = document.getElementById('rn');
//            alert(m.value);
            $("#pendaftarDummy").show();
            $("#pendaftarMT").hide();
            $("#pendaftarJasin").hide();
            $("#pendaftarAG").hide();        
        });        
        

        function ReplaceAll(Source,stringToFind,stringToReplace){
            var temp = Source;
            var index = temp.indexOf(stringToFind);
            while(index != -1){
                temp = temp.replace(stringToFind,stringToReplace);
                index = temp.indexOf(stringToFind);
            }
            return temp;
        }

      function changePejabat( val ){
            var no = val;
            if(no == '01'){
                $("#pendaftarDummy").hide();
                $("#pendaftarMT").show();
                $("#pendaftarJasin").hide();
                $("#pendaftarAG").hide();
        }
        else if(no == '02'){
            $("#pendaftarDummy").hide();
            $("#pendaftarMT").hide();
            $("#pendaftarJasin").show();
            $("#pendaftarAG").hide();
        }
        else if(no == '03'){
            $("#pendaftarDummy").hide();
            $("#pendaftarMT").hide();
            $("#pendaftarJasin").hide();
            $("#pendaftarAG").show();
        }
        else{
            $("#pendaftarDummy").show();
            $("#pendaftarMT").hide();
            $("#pendaftarJasin").hide();
            $("#pendaftarAG").hide();
        }
    }

        function doSubmit(f){
          var report = '${actionBean.reportName}';
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
                    width: '50px'
                }
            });
            if($('#trh_mula').val() == ''){
                alert("Sila Masukkan Tarikh Mula");
                $.unblockUI();
            }
            else if($('#trh_tamat').val() == ''){
                alert("Sila Masukkan Tarikh Tamat");
                $.unblockUI();
            }
            else if($('#bulan').val() == ''){
                alert("Sila Masukkan Bulan");
                $.unblockUI();
            }
            else if($('#tahun').val() == ''){
                alert("Sila Masukkan Tahun");
                $.unblockUI();
            }
            else if($('#bil_pemilik').val() == ''){
                alert("Sila Masukkan Bilangan Pemilik");
                $.unblockUI();
            } 
            else if($('#bil_kaveat').val() == ''){
                alert("Sila Masukkan Bilangan Kaveat");
                $.unblockUI();
            }
            else if((report == 'ETMIS11_1.rdf')  || (report == 'ETMIS42_1.rdf'))
            {
                var strNama = ReplaceAll( $('#nama').val()," ","_");
                var form = $(f).formSerialize();
                f.action = "${pageContext.request.contextPath}/reportGeneratorServlet?report_p_nama_pemilik="+strNama+"&"+form;
                f.submit();
            }

            else
            {
                var form = $(f).formSerialize();
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
        function dateValidation2(id, value){
            var tMula = $('#trh_mula').val();
            var tTamat = $('#trh_tamat').val();
            var tMa = tMula.split("/");
            var tTm = tTamat.split("/");
            if (tMula == ''){
                alert("Sila masukkan Tarikh Mula");
                $('#'+id).val("");
            }
            else if (tTamat < tMula){
                alert("Tarikh Tamat tidak sesuai.");
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
    
        function doFilterDaerah(kodCaw2){
            if(kodCaw2 != null){
                var url = '${pageContext.request.contextPath}/laporanMaklumatUrusanMis?doFilterDaerahBPM&kodCawangan=' + kodCaw2;
                $.get(url,
                function(data){
                    $('#display').html(data);
                    $('#caw').val(kodCaw2);
                });
            }
        }
        function filterDaerah(kodDaerah, report, reportName){
//            alert(kodDaerah);
            var url = '${pageContext.request.contextPath}/laporanMaklumatUrusanMis?penyukatanBPM&daerah='+ kodDaerah + '&namaReport=' + reportName +'&report=' + report;
//            alert(url);
            $.get(url,
            function(data){
//                alert(data);
                $('#daerah4').html(data);
               },'html');
//            alert(daerah1);
        }

    </script>
</head>
<body>
    <img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif"
         width="50" height="50" style="display: none" alt=""/>
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>
    <%--${actionBean.reportName}--%>
    <div id="daerah4">   
        <s:form beanclass="etanah.view.laporan.LaporanPengeluaranKertasHakmilik" id="laporanPengeluaranKertasHakmilik">
        <s:hidden name="reportName" id="rn"/>
            <%--${actionBean.reportName}--%>
            <div class="subtitle"> 
                <fieldset class="aras1">
                    <legend>${actionBean.report}</legend>
                    <c:set value="${actionBean.reportName}" var="reportname" /> 
                    <%--${reportname}--%>
                         
                                                <c:if test="${reportname eq 'ETMIS_ADD_09.rdf'}">
                            <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                                <p>
                                    <label>Pejabat :</label>
                                    <b>Pejabat Pendaftar</b>
                                </p>
                                <c:if test="${actionBean.kodNeg ne 'n9'}">
                                    <p>
                                        <label>Daerah :</label>
                                        <s:select name="report_p_kod_caw" style="width:150px;" onchange="changePejabat(this.value);">
                                            <s:option value ="">--Sila Pilih--</s:option>
                                            <s:option value = "01">PTD Melaka Tengah</s:option>
                                            <s:option value = "02">PTD Jasin</s:option>
                                            <s:option value = "03">PTD Alor Gajah</s:option>
                                        </s:select>
                                    </p>
                                </c:if>
                                <c:if test="${actionBean.kodNeg ne 'n9'}">
                                    <p>
                                        <label>Nama Pendaftar :</label>
                                        <s:select name="" style="width:150px;" id="pendaftarDummy">
                                            <s:option value="">--Sila Pilih--</s:option>
                                        </s:select>
                                            
                                        <s:select name ="report_p_id_pguna" style="width:150px;" id="pendaftarMT">
                                            <s:option value = "">Sila Pilih</s:option>
                                            <c:forEach items="${listUtil.senaraiPenggunaPendaftarMT}" var="item">
                                                <s:option value = "${item.idPengguna}">${item.nama}</s:option>
                                            </c:forEach>
                                        </s:select>
                                            
                                        <s:select name ="report_p_id_pguna" style="width:150px;" id="pendaftarJasin">
                                            <s:option value = "">Sila Pilih</s:option>
                                            <c:forEach items="${listUtil.senaraiPenggunaPendaftarJasin}" var="item">
                                                <s:option value = "${item.idPengguna}">${item.nama}</s:option>
                                            </c:forEach>
                                        </s:select>
                                            
                                        <s:select name ="report_p_id_pguna" style="width:150px;" id="pendaftarAG">
                                            <s:option value = "">Sila Pilih</s:option>
                                            <c:forEach items="${listUtil.senaraiPenggunaPendaftarAG}" var="item">
                                                <s:option value = "${item.idPengguna}">${item.nama}</s:option>
                                            </c:forEach>
                                        </s:select>
                                    </p>
                                </c:if>
                            </c:if>                                
                                                                      
                            <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                                <p>
                                    <label>Pejabat :</label>
                                    ${actionBean.peng.kodCawangan.name}
                                    <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                                </p>
                                <c:if test="${actionBean.kodNeg ne 'n9'}">
                                    <p>
                                        <label>Nama Pendaftar :</label> 
                                        <c:if test="${actionBean.peng.kodCawangan.kod eq '01'}">
                                            <s:select name="report_p_id_pguna" style="width:150px;">
                                                <s:option value="">--Sila Pilih--</s:option>
                                                <s:options-collection collection="${listUtil.senaraiPenggunaPendaftarMT}" label="nama" value="idPengguna" />
                                            </s:select>
                                        </c:if>
                                                
                                        <c:if test="${actionBean.peng.kodCawangan.kod eq '02'}">
                                            <s:select name="report_p_id_pguna" style="width:150px;">
                                                <s:option value="">--Sila Pilih--</s:option>
                                                <s:options-collection collection="${listUtil.senaraiPenggunaPendaftarJasin}" label="nama" value="idPengguna" />
                                            </s:select>
                                        </c:if>
                                                
                                        <c:if test="${actionBean.peng.kodCawangan.kod eq '03'}">
                                            <s:select name="report_p_id_pguna" style="width:150px;">
                                                <s:option value="">--Sila Pilih--</s:option>
                                                <s:options-collection collection="${listUtil.senaraiPenggunaPendaftarAG}" label="nama" value="idPengguna" />
                                            </s:select>
                                        </c:if>
                                    </p>
                                </c:if>
                            </c:if>
                                    <p>
                                        <label>Tarikh Mula :</label>
                                        <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy"
                                                onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                                    </p>
                                    <p>
                                        <label>Tarikh Tamat :</label>
                                        <s:text id="trh_tamat" name="report_p_trh_tamat" class="datepicker" formatPattern="dd/MM/yyyy"
                                                onchange="dateValidation(this.id, this.value),dateValidation2(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                                    </p>
                        </c:if>
<!--end add -->
                    

<br>
<p align="center">
    <s:button name="" id="simpan" value="Papar" class="btn" onclick="doSubmit(this.form);"/>&nbsp;
    <s:reset name="RESET" value="Isi Semula" class="btn"/>&nbsp;
</p>
                </fieldset>
            </div>
        </s:form > 
    </div>
</body>
</html>
