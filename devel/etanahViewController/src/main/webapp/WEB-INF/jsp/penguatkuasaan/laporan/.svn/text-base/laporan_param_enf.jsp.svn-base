<%-- 
    Document   : laporan_param_enf
    Created on : 17 DIS 2013, 12:45:21 AM
    Author     : ida
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
        //alert(url);
        window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url="+url, "eTanah",
        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
    <%--f.action = "${pageContext.request.contextPath}/reportGeneratorServlet?"+form;
    f.submit();--%>
            $.unblockUI();
    <%--}--%>        
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
                var url = '${pageContext.request.contextPath}/enf/laporanEnforcement?doCollectKaunter&kodCawangan=' + kodCaw+'&reportNama='+report;
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
                var url = '${pageContext.request.contextPath}/enf/laporanEnforcement?doCollectKaunter&kodCawangan=' + kodCaw+'&reportNama='+report;
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
                var url = '${pageContext.request.contextPath}/enf/laporanEnforcement?doFilterDaerahBPM&kodCawangan=' + kodCaw2+'&reportNama='+report;
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
                var url = '${pageContext.request.contextPath}/enf/laporanEnforcement?doFilterBPM&kodDaerah=' + kodDaerah1+'&reportNama='+report+'&kodUrusan'+report;
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

<s:form beanclass="etanah.view.penguatkuasaan.laporan.enfLaporanActionBean">
    <div id="display" class="subtitle">
        <s:hidden id="reportname" name="reportName"/>
        <fieldset class="aras1">

            <lagend>
                <c:if test="${actionBean.reportName eq 'ENFRekodAduan_NS.rdf' || actionBean.reportName eq 'ENFRekodAduan_425_NS.rdf' || actionBean.reportName eq 'ENFRekodAduan_351_NS.rdf'}">
                    Rekod Aduan
                </c:if>

                <c:if test="${actionBean.reportName eq 'ENFKeseluruhanRekod_NS.rdf'}">
                    Rekod Keseluruhan Aduan Diterima
                </c:if>

                <c:if test="${actionBean.reportName eq 'ENFLaporanKemajuan_NS.rdf'}">
                    Laporan Kemajuan
                </c:if>

                <c:if test="${actionBean.reportName eq 'ENFRekodKesalahan_NS.rdf'}">
                    Rekod Kesalahan Orang Disyaki
                </c:if>

                <c:if test="${actionBean.reportName eq 'ENFRekodPBN_NS.rdf'}">
                    Rekod Tanah Yang dikembalikan kepada PBN
                </c:if>

                <c:if test="${actionBean.reportName eq 'ENFRekodBaitulmal_NS.rdf'}">
                    Rekod Tanah Yang Terletakhak kepada Baitulmal
                </c:if>

                <c:if test="${actionBean.reportName eq 'ENFRekodMBI_NS.rdf'}">
                    Rekod Tanah Yang Terletakhak kepada MBI
                </c:if>

                <c:if test="${actionBean.reportName eq 'ENFKutipanKompaun_NS.rdf'}">
                    Laporan Kutipan Kompaun
                </c:if>

                <c:if test="${actionBean.reportName eq 'ENFKutipanDenda_NS.rdf'}">
                    Laporan Kutipan Denda
                </c:if>

            </lagend>         
            <legend></legend>
            <c:set value="${actionBean.reportName}" var="reportname"/>            

            <s:hidden name="report_p_id" value="${actionBean.pengguna.idPengguna}"/>

            <c:if test="${actionBean.reportName eq 'ENFRekodAduan_NS.rdf' || actionBean.reportName eq 'ENFRekodAduan_425_NS.rdf' || actionBean.reportName eq 'ENFRekodAduan_351_NS.rdf'}">
                <p>
                    <label>Rekod Aduan  :</label>
                    <s:select id="urusan" name="report_p_urusan" style="width:280px;">                      
                        <%--<s:option value="">Semua</s:option>--%>
                        <c:if test="${actionBean.reportName eq 'ENFRekodAduan_351_NS.rdf'}">
                            <s:option value="49">Di bawah Seksyen 49 KTN</s:option>
                            <s:option value="100">Di bawah Seksyen 100 KTN</s:option>
                            <s:option value="127">Di bawah Seksyen 127, 128, 129, 130 KTN</s:option>
                            <s:option value="351">Di bawah Seksyen 351 KTN</s:option>
                            <s:option value="352">Di bawah Seksyen 352 KTN</s:option>
                            <s:option value="426">Di bawah Seksyen 426 KTN</s:option>
                        </c:if>
                        <c:if test="${actionBean.reportName eq 'ENFRekodAduan_425_NS.rdf'}">
                            <s:option value="422">Di bawah Seksyen 422 KTN</s:option> 
                            <s:option value="423">Di bawah Seksyen 423 KTN</s:option> 
                            <s:option value="424">Di bawah Seksyen 424 KTN</s:option> 
                            <s:option value="425">Di bawah Seksyen 425 KTN</s:option>
                            <s:option value="425A">Di bawah Seksyen 425A KTN</s:option>
                            <s:option value="427">Di bawah Seksyen 427 KTN</s:option> 
                            <s:option value="428">Di bawah Seksyen 428 KTN</s:option> 
                            <s:option value="429">Di bawah Seksyen 429 KTN</s:option>
                        </c:if>
                    </s:select>
                </p>
            </c:if>

            <c:if test="${actionBean.reportName eq 'ENFRekodKesalahan_NS.rdf'                        
                  }"> 
                <p>
                    <label>No Kad Pengenalan :</label>
                    <s:text id="nokp" name="report_p_nokp" size="20" maxlength="12" onkeypress="javascript:validateNumber(this,this.value);"/>&nbsp;<font size="1" color="red"> (cth : 790511015112)</font>
                </p>

            </c:if>



            <c:if test="${actionBean.reportName eq 'ENFRekodAduan_NS.rdf'
                          || actionBean.reportName eq 'ENFRekodAduan_425_NS.rdf' 
                          || actionBean.reportName eq 'ENFRekodAduan_351_NS.rdf'
                          || actionBean.reportName eq 'ENFKeseluruhanRekod_NS.rdf'     
                          || actionBean.reportName eq 'ENFLaporanKemajuan_NS.rdf'
                          || actionBean.reportName eq 'ENFRekodPBN_NS.rdf'
                          || actionBean.reportName eq 'ENFRekodBaitulmal_NS.rdf'
                          || actionBean.reportName eq 'ENFRekodMBI_NS.rdf'
                          || actionBean.reportName eq 'ENFKutipanKompaun_NS.rdf'
                          || actionBean.reportName eq 'ENFKutipanDenda_NS.rdf'
                  }">
                <p>
                    <label>Cawangan :</label>
                    <c:choose>
                        <c:when test="${actionBean.pengguna.kodCawangan.kod eq '00'}">
                            <s:select id="caw" name="report_p_kodcaw" style="width:260px;">
                                <s:option value="">Semua</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>
                            </s:select>
                        </c:when>
                        <c:otherwise>
                            ${actionBean.pengguna.kodCawangan.kod} - ${actionBean.pengguna.kodCawangan.name}
                            <s:hidden id="caw" name="report_p_kodcaw" value="${actionBean.pengguna.kodCawangan.kod}"/>
                        </c:otherwise>
                    </c:choose>
                    <%-- <s:hidden name="report_p_kodcaw" value="${actionBean.kodCaw}"/> --%>
                </p>
            </c:if>


            <c:if test="${actionBean.reportName eq 'ENFRekodAduan_NS.rdf'
                          || actionBean.reportName eq 'ENFRekodAduan_425_NS.rdf' 
                          || actionBean.reportName eq 'ENFRekodAduan_351_NS.rdf'
                          || actionBean.reportName eq 'ENFKeseluruhanRekod_NS.rdf'
                          || actionBean.reportName eq 'ENFLaporanKemajuan_NS.rdf'
                          || actionBean.reportName eq 'ENFRekodPBN_NS.rdf'
                          || actionBean.reportName eq 'ENFRekodBaitulmal_NS.rdf'
                          || actionBean.reportName eq 'ENFRekodMBI_NS.rdf'
                          || actionBean.reportName eq 'ENFKutipanKompaun_NS.rdf'
                          || actionBean.reportName eq 'ENFKutipanDenda_NS.rdf'
                  }"> 
                <p>
                    <label>Tempat :</label>
                    <s:textarea id="tempat" name="report_p_tempat"/>&nbsp;<font size="1" color="red"> (cth : Kampung Rasah)</font>
                </p>

            </c:if>


            <c:if test="${actionBean.reportName eq 'ENFRekodAduan_NS.rdf'
                          || actionBean.reportName eq 'ENFRekodAduan_425_NS.rdf' 
                          || actionBean.reportName eq 'ENFRekodAduan_351_NS.rdf'
                          || actionBean.reportName eq 'ENFKeseluruhanRekod_NS.rdf'    
                          || actionBean.reportName eq 'ENFLaporanKemajuan_NS.rdf'
                          || actionBean.reportName eq 'ENFRekodPBN_NS.rdf'
                          || actionBean.reportName eq 'ENFRekodBaitulmal_NS.rdf'
                          || actionBean.reportName eq 'ENFRekodMBI_NS.rdf'
                          || actionBean.reportName eq 'ENFKutipanKompaun_NS.rdf'
                          || actionBean.reportName eq 'ENFKutipanDenda_NS.rdf'
                  }">
                <p>
                    <label>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_tarikhmula" class="datepicker" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>

                <p>
                    <label>Tarikh Tamat :</label>
                    <s:text id="trh_tamat" name="report_p_tarikhakhir" class="datepicker" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
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


        </fieldset >
    </div>
</s:form>
