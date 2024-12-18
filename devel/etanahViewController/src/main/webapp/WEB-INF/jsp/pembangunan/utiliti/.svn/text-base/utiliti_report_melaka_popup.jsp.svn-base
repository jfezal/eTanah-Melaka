<%-- 
    Document   : utiliti_report_melaka_popup
    Created on : Oct 22, 2013, 12:09:31 PM
    Author     : khairul.hazwan
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
        $(document).ready(function() {
            $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
            var m = document.getElementById('rn');
            
        });


        function ReplaceAll(Source, stringToFind, stringToReplace) {
            var temp = Source;
            var index = temp.indexOf(stringToFind);
            while (index != -1) {
                temp = temp.replace(stringToFind, stringToReplace);
                index = temp.indexOf(stringToFind);
            }
            return temp;
        }

        
        function doSubmit  (f) {
            var report = '${actionBean.reportName}';
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top: ($(window).height() - 50) / 2 + 'px',
                    left: ($(window).width() - 50) / 2 + 'px',
                    width: '50px'
                }
            });

            if ($('#trh_mula').val() == '') {
                alert("Sila Masukkan Tarikh Mula");
                $.unblockUI();
            } else if ($('#trh_tamat').val() == '') {
                alert("Sila Masukkan Tarikh Tamat");
                $.unblockUI();
            } 
            else if ($('#kodUrusan').val() == '') {
                alert("Sila Pilih Urusan");
                $.unblockUI();  
            } else {
                var form = $(f).formSerialize();
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

        function dateValidation2(id, value) {
            var tMula = $('#trh_mula').val();
            var tTamat = $('#trh_tamat').val();
            var tMa = tMula.split('/');
            var tTm = tTamat.split('/');
            var totalM = tMa[2] + tMa[1] + tMa[0];
            var totalT = tTm[2] + tTm[1] + tTm[0];
            if (tMula == '') {
                alert("Sila masukkan Tarikh Mula");
                $('#' + id).val("");
            }
            else if (totalT < totalM) {
                alert("Tarikh Tamat tidak sesuai.");
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

        function validateYearLength(value) {
            var plength = value.length;
            if (plength != 4) {
                alert('"Tahun" yang dimasukkan salah.');
                $('#tahun').val("");
                $('#tahun').focus();
            }
        }

        function doFilterDaerah(kodCaw2) {
            if (kodCaw2 !=   null) {
                var url = '${pageContext.request.contextPath}/utiliti/reports?doFilterDaerahBPM&kodCawangan=' + kodCaw2;
                $.get(url,
                function(data) {
                    $('#display').html(data);
                    $('#caw').val(kodCaw2);
                });
            }
        }

        function filterDaerah(kodDaerah, report, reportName) {
            //            alert(kodDae  rah);
            var url = '${pageContext.request.contextPath}/utiliti/reports?penyukatanBPM&daerah=' + kodDaerah + '&namaReport=' + reportName + '&report=' + report;
            //            alert(url);
            $.get(url,
            function(data) {
                //                alert(data);
                $('#daerah4').html(data);
            }, 'html');
            //            alert(daerah1);
        }

        function padam3(id) {
            var serah = $('#jenisPenyerah').val();
            if (serah != "") {
                $('#idPenyerah').val("");
                $('#namaPenyerah').val("");
                $('#nPenyerah').show();
            } else {
                $('#nPenyerah').hide();
            }
        }
    </script>
</head>
<body>
    <img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif"
         width="50" height="50" style="display: none" alt=""/>
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>

    <div id="daerah4">   
        <s:form beanclass="etanah.view.stripes.pembangunan.utiliti.UtilitiReportActionBean" id="LaporanMaklumatUrusanMis">
            <s:hidden name="reportName" id="rn"/>

            <div class="subtitle"> 
                <fieldset class="aras1">                   
                    <c:set value="${actionBean.reportName}" var="reportname" /> 


                    <p>
                        <label>Pejabat :</label>
                        <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                            <c:if test = "${!(actionBean.reportName eq 'DEV_STAT_TSSamabydaerah.rdf' || actionBean.reportName eq 'DEV_STAT_TSLainbydaerah.rdf')}">
                                <s:select name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}">
                                    <s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>
                                </s:select>
                            </c:if>
                            
                            <c:if test = "${actionBean.reportName eq 'DEV_STAT_TSSamabydaerah.rdf' || actionBean.reportName eq 'DEV_STAT_TSLainbydaerah.rdf'}">
                                <s:select name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}">
                                    <s:options-collection collection="${listUtil.senaraiKodCawanganByDaerah}" label="name" value="kod"/>
                                </s:select>
                            </c:if>
                        </c:if>

                        <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                            ${actionBean.peng.kodCawangan.name}
                            <s:hidden name="report_p_kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>
                        </c:if>
                    </p>   

                    <p>
                        <label>Tarikh Mula :</label>
                        <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy"
                                onchange="dateValidation(this.id, this.value)"/>&nbsp;
                        <font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>

                    <p>
                        <label>Tarikh Tamat :</label>
                        <s:text id="trh_tamat" name="report_p_trh_tamat" class="datepicker" formatPattern="dd/MM/yyyy"
                                onchange="dateValidation2(this.id, this.value)"/>&nbsp;
                        <font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </p>      

                    <c:if test = "${actionBean.reportName eq 'DEV_StatusPermohonan_bydaerah.rdf'}">
                        <p>
                            <label>Urusan :</label>
                            <s:select name="report_p_kod_urusan"  id="kodUrusan" style="width:510px;">
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodUrusanPembangunanLaporanStatusPermohonan}" label="nama" value="kod"/>
                            </s:select>
                        </p>                
                    </c:if>
                    <br>

                    <p align="center">            
                        <s:button name="" id="simpan" value="Papar" class="btn" onclick="doSubmit(this.form);"/>&nbsp;
                        <s:reset name="RESET" value="Isi Semula" class="btn"/>&nbsp;
                        <s:button name="" id="close" value="Tutup" onclick="self.close();" class="btn"/>&nbsp;
                    </p>         
                </fieldset>
            </div>
        </s:form> 
    </div>
</body>
</html>
