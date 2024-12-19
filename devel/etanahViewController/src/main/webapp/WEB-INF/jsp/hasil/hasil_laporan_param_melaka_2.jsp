<%-- 
    Document   : hasil_laporan_param_melaka_2
    Created on : Oct 28, 2013, 4:03:21 PM
    Author     : ezat.amir
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
        $("#daerah").val(kodDaerah);
        $("#caw").val(kodCaw);
        $("#trh_mula").val(date.getDate()+"/"+(date.getMonth()+1)+"/"+date.getFullYear());
        $("#trh_tamat").val(date.getDate()+"/"+(date.getMonth()+1)+"/"+date.getFullYear());
        $("#trh_pungutan").val(date.getDate()+"/"+(date.getMonth()+1)+"/"+date.getFullYear());
        $("#trh_masuk").val(date.getDate()+"/"+(date.getMonth()+1)+"/"+date.getFullYear());
        $("#bulan").val(date.getMonth()+1);
        $("#tahun").val(date.getFullYear());
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
        if((report == 'HSL_R_01_MLK.rdf'
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
            || report == 'HSL_R_17_MLK.rdf'

            || report == 'HSL_R_19_MLK.rdf'
            || report == 'HSL_R_22_MLK.rdf'
            || report == 'HSL_R_23_MLK.rdf'
            || report == 'HSL_R_24_MLK.rdf'
            || report == 'HSL_R_25_MLK.rdf'
            || report == 'HSL_R_27_MLK.rdf'
            || report == 'HSL_R_34_MLK.rdf'
            || report == 'HSL_R_40_MLK.rdf') && $('#caw').val()== ""){
            alert("Sila pilih Cawangan terlebih dahulu.");
            $('#caw').focus();
            $.unblockUI();
        }else{
            var url = form.replace(/&/g, "%26");
            window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url="+url, "eTanah",
                    "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
            <%--f.action = "${pageContext.request.contextPath}/reportGeneratorServlet?"+form;
            f.submit();--%>
            $.unblockUI();
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
        var strValidCharacters = "1234567890.";
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

    function doFilterKaunter(kodCaw){
        var report = $("#reportname").val();
        var tm = $("#trh_mula").val()+"";
        var tt = $("#trh_tamat").val()+"";
        if(kodCaw != ""){
            var url = '${pageContext.request.contextPath}/HasilLaporan_MLK?doCollectKaunter&kodCawangan=' + kodCaw+'&reportNama='+report;
            $.get(url,
            function(data){
                $('#display').html(data);
                $('#caw').val(kodCaw);
                $("#trh_mula").val(tm);
                $("#trh_tamat").val(tt);
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
            });            
        }        
    }

    function doFilterDaerah(kodCaw2){
        var report = $("#reportname").val();
        if(kodCaw2 != null){
            var url = '${pageContext.request.contextPath}/HasilLaporan_MLK?doFilterDaerahBPM&kodCawangan=' + kodCaw2+'&reportNama='+report;
            $.get(url,
            function(data){
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
            var url = '${pageContext.request.contextPath}/HasilLaporan_MLK?doFilterBPM&kodDaerah=' + kodDaerah1+'&reportNama='+report;
            $.get(url,
            function(data){
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

<s:form beanclass="etanah.view.laporan.LaporanHasilMelakaActionBean">
    <div id="display" class="subtitle">
        <s:hidden id="reportname" name="reportName"/>
        <fieldset class="aras1">
            <lagend>
            
               
          
          <c:if test="${actionBean.reportName eq 'HSL_R_61_MLK.rdf'}">
          Ringkasan Denda Lewat Semasa
          </c:if>
          </lagend>
            
            
           <legend>Parameter</legend>
            <c:set value="${actionBean.reportName}" var="reportname"/>

            <c:set var="disable" value="false"/>
            <c:if test="${actionBean.pengguna.kodCawangan.kod ne '00'}">
                <c:set var="disable" value="true"/>
            </c:if>

            <%--<c:if test="${reportname  eq 'HSL_R_07_MLK.rdf'
                         or reportname eq 'HSL_R_09_MLK.rdf'
                         or reportname eq 'HSL_R_14_MLK.rdf'
                         or reportname eq 'HSL_R_17_MLK.rdf'
                         or reportname eq 'HSL_R_19_MLK.rdf'
                         or reportname eq 'HSL_R_25_MLK.rdf'
                         or reportname eq 'HSL_R_27_MLK.rdf'
                         }">
                <p>
                    <label><em>*</em>Cawangan :</label>
                    <s:select id="caw" name="report_p_kod_caw" style="width:260px;" disabled="${disable}" onchange="doFilterDaerah(this.value);">
                        <s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>
                    </s:select>
                    <s:hidden name="report_p_kod_caw" value="${actionBean.kodCaw}"/>
                </p>

            </c:if>--%>
            <c:if test="${reportname eq 'HSL_R_61_MLK.rdf'
                          
                  }">
                <p>
                    <label><em>*</em>Daerah :</label>
                    <s:select name="kodDaerah" style="width:260px;" disabled="${disable}" onchange="doFilterBPM(this.value);">
                        <s:options-collection collection="${listUtil.senaraiKodDaerah}" label="nama" value="kod"/>
                    </s:select>
                    <s:hidden name="report_p_kod_daerah" value="${actionBean.kodDaerah}"/>
                </p>
            </c:if>

            <%--<c:if test="${reportname eq 'HSL_R_19_MLK.rdf'
                         or reportname eq 'HSL_R_23_MLK.rdf'
                         or reportname eq 'HSL_R_24_MLK.rdf'
                         or reportname eq 'HSL_R_34_MLK.rdf'
                         or reportname eq 'HSL_R_37_MLK.rdf'
                         or reportname eq 'HSL_R_40_MLK.rdf'
                         or reportname eq 'HSL_R_44_MLK.rdf'
                         or reportname eq 'HSL_R_47_MLK.rdf'
                         
                        
                        }">
                <p>
                    <label>Bandar/Pekan/Mukim :</label>
                    <s:select id="bpm" name="report_p_kod_bpm" style="width:200px;">
                        <s:option value="">SEMUA</s:option>
                        <%--<s:options-collection collection="${listUtil.senaraiKodBandarMukim}" label="nama" value="kod"/>
                        <s:options-collection collection="${actionBean.senaraiBPM}" label="nama" value="kod"/>
                    </s:select>
                </p>
            </c:if>--%>

            <%--<c:if test="${reportname eq 'HSL_R_07_MLK.rdf'
                          or reportname eq 'HSL_R_09_MLK.rdf'
                         }">
                <p>
                    <label>Kaunter Mula :</label>
                    <s:select name="report_p_kaunter_mula" style="width:145px;">
                        <s:option value="">Semua</s:option>
                        <c:forEach items="${actionBean.senaraiKaunter}" var="kaunter" >
                            <s:option value="${kaunter}">${kaunter}</s:option>
                        </c:forEach>
                    </s:select>
                </p>
            </c:if>--%>
           <%-- <c:if test="${reportname eq 'HSL_R_07_MLK.rdf'
                          or reportname eq 'HSL_R_09_MLK.rdf'
                          }">
                <p>
                    <label>Kaunter Akhir :</label>
                    <s:select name="report_p_kaunter_tamat" style="width:145px;">
                        <s:option value="">Semua</s:option>
                        <c:forEach items="${actionBean.senaraiKaunter}" var="kaunter" >
                            <s:option value="${kaunter}">${kaunter}</s:option>
                        </c:forEach>
                    </s:select>
                </p>
            </c:if>--%>
           <%-- <c:if test="${reportname eq 'HSL_R_02_MLK.rdf'
                          or reportname eq 'HSL_R_01_MLK.rdf'
                          or reportname eq 'HSL_R_03_MLK.rdf'
                          or reportname eq 'HSL_R_04_MLK.rdf'
                          or reportname eq 'HSL_PP_MLK.rdf'
                          }">
                <p>
                    <label>Kaunter :</label>
                    <s:select name="report_p_id_kaunter" style="width:145px;">
                        <s:option value="">Semua</s:option>
                        <c:forEach items="${actionBean.senaraiKaunter}" var="kaunter" >
                            <s:option value="${kaunter}">${kaunter}</s:option>
                        </c:forEach>
                    </s:select>
                </p>
            </c:if>--%>
           <%-- <c:if test="${reportname eq 'HSL_R_02_MLK.rdf'
                          or reportname eq 'HSL_R_01_MLK.rdf'
                          or reportname eq 'HSL_R_03_MLK.rdf'
                          or reportname eq 'HSL_R_04_MLK.rdf'
                          or reportname eq 'HSL_PP_MLK.rdf'
                          
                          
                          or reportname eq 'HSL_R_05_MLK.rdf'
                          or reportname eq 'HSL_R_07_MLK.rdf'
                          or reportname eq 'HSL_R_06_MLK.rdf'
                          or reportname eq 'HSL_R_09_MLK.rdf'
                          or reportname eq 'HSL_R_16_MLK.rdf'
                          
                          or reportname eq 'HSL_R_23_MLK.rdf'
                          or reportname eq 'HSL_R_24_MLK.rdf'
                          or reportname eq 'HSL_R_41_MLK.rdf'
                          or reportname eq 'HSL_R_27_MLK.rdf'
                          or reportname eq 'HSL_R_45_MLK.rdf'
                          or reportname eq 'HSL_R_46_MLK.rdf'
                          or reportname eq 'HSL_R_48_MLK.rdf'
                          or reportname eq 'HSL_R_49_MLK.rdf'
                  }">
                <p>
                    <label>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>
            </c:if>--%>
          <%--  <c:if test="${reportname eq 'HSL_R_02_MLK.rdf'
                          or reportname eq 'HSL_R_01_MLK.rdf'
                          or reportname eq 'HSL_R_03_MLK.rdf'
                          or reportname eq 'HSL_R_04_MLK.rdf'
                          or reportname eq 'HSL_PP_MLK.rdf'
                          
                          
                          or reportname eq 'HSL_R_05_MLK.rdf'
                          or reportname eq 'HSL_R_07_MLK.rdf'
                          or reportname eq 'HSL_R_06_MLK.rdf'
                          or reportname eq 'HSL_R_09_MLK.rdf'
                          or reportname eq 'HSL_R_16_MLK.rdf'
                          
                          or reportname eq 'HSL_R_23_MLK.rdf'
                          or reportname eq 'HSL_R_24_MLK.rdf'
                          or reportname eq 'HSL_R_41_MLK.rdf'
                          or reportname eq 'HSL_R_27_MLK.rdf'
                          or reportname eq 'HSL_R_45_MLK.rdf'
                          or reportname eq 'HSL_R_46_MLK.rdf'
                          or reportname eq 'HSL_R_48_MLK.rdf'
                          or reportname eq 'HSL_R_49_MLK.rdf'
                  
                  }">
                <p>
                    <label>Tarikh Tamat :</label>
                    <s:text id="trh_tamat" name="report_p_trh_tamat" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>
            </c:if>--%>
          <%--  <c:if test="${reportname eq 'HSL_R_17_MLK.rdf'}">
                <p>
                    <label>Tarikh Pungutan :</label>
                    <s:text id="trh_pungutan" name="report_p_trh_kutipan" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>
            </c:if>--%>

           <%-- <c:if test="${reportname eq 'HSL_R_25_MLK.rdf'}">
                <p>
                    <label>Bulan :</label>
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
            </c:if>--%>

           <%-- <c:if test="${reportname eq 'HSL_R_12_MLK.rdf'
                          or reportname eq 'HSL_R_13_MLK.rdf'
                          or reportname eq 'HSL_R_14_MLK.rdf'
                          or reportname eq 'HSL_R_37_MLK.rdf'
                          or reportname eq 'HSL_R_25_MLK.rdf'}">
                <p>
                    <label>Tahun :</label>
                    <s:select id="tahun" name="report_p_tahun" style="width:145px;">
                      <s:option value="">--Sila Pilih--</s:option>
                      <s:options-collection collection="${actionBean.listYear}"/>
                    </s:select>
                </p>
            </c:if>--%>
           <%--<c:if test="${reportname eq 'HSL_R_02_MLK.rdf'
                          or reportname eq 'HSL_R_01_MLK.rdf'
                          or reportname eq 'HSL_R_03_MLK.rdf'
                          or reportname eq 'HSL_R_04_MLK.rdf'
                          or reportname eq 'HSL_PP_MLK.rdf'
                          }">
                <p>
                    <label>Mod Bayar :</label>
                    <s:select name="report_p_mod_bayar" style="width:145px;">
                        <s:option value="">Semua</s:option>
                        <s:option value="B">Biasa</s:option>
                        <s:option value="L">Lewat</s:option>
                    </s:select>
                </p>
            </c:if>--%>
            <%--<c:if test="${}">
                <p>
                    <label>Cara Bayaran :</label>
                    <s:select name="report_p_kod_cara_bayar" style="width:145px;">
                        <s:option value="">Semua</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodCaraBayaran}" label="nama" value="kod"/>
                    </s:select>
                </p>
           </c:if>--%> 
            <%--<c:if test="${reportname eq 'HSL_R_02_MLK.rdf'
                          or reportname eq 'HSL_R_01_MLK.rdf'
                          or reportname eq 'HSL_R_03_MLK.rdf'
                          or reportname eq 'HSL_R_04_MLK.rdf'
                          or reportname eq 'HSL_PP_MLK.rdf'
                          }">
                <p>
                    <label>Status :</label>
                    <s:select name="report_p_sts" style="width:145px;">
                        <s:option value="SEMUA">Semua</s:option>
                        <s:option value="LUAR">Luar</s:option>
                        <s:option value="TEMPATAN">Tempatan</s:option>
                    </s:select>
                </p>
            </c:if>--%>
            
                
               <%--<c:if test="${reportname eq 'HSL_R_06_MLK.rdf' 
                          or reportname eq 'HSL_R_41_MLK.rdf'}">
                <p>
                    <label>Agensi :</label>
                    <s:select name="report_p_kod_agensi" style="width:145px;">
                        <s:option value="">Semua</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodAgensiKutipan}" label="nama" value="kod"/>
                    </s:select>
                </p>
            </c:if>--%>

            <%--<c:if test="${reportname eq 'HSL_R_23_MLK.rdf'
                          or reportname eq 'HSL_R_24_MLK.rdf'}">
                <p>
                    <label>Jenis Hakmilik :</label>
                    <s:select name="report_p_kod_hakmilik" style="width:200px;">
                        <s:option value="">SEMUA</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodHakmilik}" label="nama" value="kod"/>
                    </s:select>
                </p>
            </c:if>--%>

          <%--  <c:if test="${reportname eq 'HSL_R_23_MLK.rdf'}">
                <p>
                    <label><em>*</em>Amaun Minimum :</label>
                    <s:text id="amaun" name="report_p_min_amt" onkeyup="validateNumber(this,this.value);"/>&nbsp;
                </p>
                <p>
                    <label>Kategori Pemilik :</label>
                    <s:select name="report_p_katg_pemilik" style="width:145px;">
                        <s:option value="">Semua</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodBangsa}" label="nama" value="kod"/>
                    </s:select>
                </p>
            </c:if>--%>


                <br>
            <p align="center">
                <s:button name="" id="simpan" value="Papar" class="btn" onclick="doSubmit(this.form);"/>&nbsp;
                <%--<s:reset name="" value="Isi Semula" class="btn"/>&nbsp;--%>
                <s:button name="" id="close" value="Tutup" onclick="self.close();" class="btn"/>&nbsp;
            </p>

        </fieldset >
    </div>
             
</s:form>
