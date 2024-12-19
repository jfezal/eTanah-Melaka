<%-- 
    Document   : kemasukan_tarikh_dan_keputusan
    Created on : May 16, 2010, 12:31:56 PM
    Author     : sitifariza.hanim
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252" language="java"%>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<link type="text/css" href="${pageContext.request.contextPath}/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ui.datetimepicker.js"></script>
<script type="text/javascript">

    $(document).ready(function() {
        if ($('#kpsnT').val() == 'T') {
            $('#keputusanLulus').hide();
            $('#ulasanLulus').hide();
            $('#lulus1').hide();
            $('#lulus2').hide();
            $('#lulus3').hide();
        }

        if ($('#kpsnT').val() == 'Y') {
            $('#keputusanLulus').show();
            $('#ulasanLulus').show();
            $('#tempohPegangan').show();
            $('#lulus1').show();
            $('#lulus2').show();
            $('#lulus3').show();
        }
    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBGSA'}">
        <c:if test="${actionBean.kpsn eq 'L'}">
                $('#lulus4').show();
        </c:if>
        <c:if test="${actionBean.kpsn ne 'L'}">
                $('#lulus4').hide();
        </c:if>
    </c:if>  
            if (document.getElementById("kpsnTolak").checked == true) {
                $('#tempohPegangan').hide();
                $('#lulus4').hide();
            }
    <c:choose>
        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PPTR'}">
            <c:if test="${actionBean.kodNegeri ne '04'}">
                <c:if test="${actionBean.kpsn eq 'L'}">
                        $('#lulus4').show();
                </c:if>
                <c:if test="${actionBean.kpsn ne 'L'}">
                        $('#lulus4').hide();
                </c:if>
            </c:if>
            <c:if test="${actionBean.kodNegeri eq '04'}">
                    if ($('#testPPTR').val() == 'L') {
                        $('#lulus4').show();

                    } else {
                        $('#lulus4').hide();
                    }
            </c:if>
        </c:when>
        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PTGSA'}">
            <c:if test="${actionBean.kpsn eq 'L'}">
                    $('#lulus4').show();
            </c:if>
            <c:if test="${actionBean.kpsn ne 'L'}">
                    $('#lulus4').hide();
            </c:if>
        </c:when>
        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PPRU'}">
                var kep = '${actionBean.permohonan.keputusan.kod}';
                if (kep == 'L') {
                    $('#tsidang').show();
                    $('#tsah').show();
                    $('#kpohon').show();
                    $('#klulus').show();
                    $('#lulus4').hide();
                    $('#klgsa').show();
                } else if (kep == 'T') {
                    $('#tsidang').show();
                    $('#tsah').show();
                    $('#kpohon').hide();
                    $('#klulus').hide();
                    $('#lulus4').hide();
                    $('#klgsa').hide();
                    $('#lulus3').hide();
                } else if (kep == 'TG') {
                    $('#tsidang').show();
                    $('#tsah').show();
                    $('#kpohon').hide();
                    $('#klulus').hide();
                    $('#lulus4').hide();
                    $('#klgsa').hide();
                    $('#lulus3').hide();
                } else {
                    $('#tsidang').show();
                    $('#tsah').show();
                    $('#kpohon').show();
                    $('#klulus').show();
                    $('#lulus4').hide();
                    $('#klgsa').show();
                }
        </c:when>
        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PTPBP'}">
            <c:if test="${actionBean.kpsn eq 'L'}">
                    $('#klgsa').show();
                    $('#lulus3').show();

            </c:if>
            <c:if test="${actionBean.kpsn ne 'L'}">
                    $('#klgsa').hide();
                    $('#lulus3').hide();
            </c:if>
        </c:when>
        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PRMMK'}">
            <c:if test="${actionBean.kpsn eq 'L'}">
                    $('#klgsa').show();
                    $('#kpohon').show();

            </c:if>
            <c:if test="${actionBean.kpsn ne 'L'}">
                    $('#klgsa').hide();
                    $('#kpohon').hide();
            </c:if>
        </c:when>
        <c:otherwise>
        </c:otherwise>
    </c:choose>        
        });

        function validateNumber(elmnt, content) {
            //if it is character, then remove it..
            if (isNaN(content)) {
                elmnt.value = removeNonNumeric(content);
                return;
            }
        }
    <c:choose>
        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'LMCRG' or actionBean.permohonan.kodUrusan.kod eq 'PCRG' or actionBean.permohonan.kodUrusan.kod eq 'MPCRG' or actionBean.permohonan.kodUrusan.kod eq 'PCRG'}">
            <c:choose>
                <c:when test="${actionBean.kpsn eq 'L'}">
                    $('#lesen').show();
                    <c:if test="${actionBean.stageId ne 'g_charting_keputusan'}">
                        $('#formLesen').show();
                    </c:if>
                    <c:if test="${actionBean.stageId eq 'g_charting_keputusan'}">
                        $('#formLesen').hide();
                    </c:if>    

                </c:when>
                <c:when test="${actionBean.kpsn eq 'T'}">
                    $('#lesen').hide();
                    <c:if test="${actionBean.stageId ne 'g_charting_keputusan'}">
                        $('#formLesen').show();
                    </c:if>
                    <c:if test="${actionBean.stageId eq 'g_charting_keputusan'}">
                        $('#formLesen').hide();
                    </c:if>
                </c:when>
                <c:otherwise>
                    $('#lesen').hide();
                    <c:if test="${actionBean.stageId ne 'g_charting_keputusan'}">
                        $('#formLesen').show();
                    </c:if>
                    <c:if test="${actionBean.stageId eq 'g_charting_keputusan'}">
                        $('#formLesen').hide();
                    </c:if>
                </c:otherwise>
            </c:choose>
        </c:when>
        <c:otherwise>
            $('#lesen').hide();
            $('#formLesen').show();
        </c:otherwise>
    </c:choose>

        function removeNonNumeric(strString)
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

        function ReplaceAll(Source, stringToFind, stringToReplace) {
            var temp = Source;
            var index = temp.indexOf(stringToFind);
            while (index != -1) {
                temp = temp.replace(stringToFind, stringToReplace);
                index = temp.indexOf(stringToFind);

            }
            return temp;
        }

        function RunProgram(strUserID, strNama, strJawatan, strIDPermohonan, strStageID) {

            var strNama2 = ReplaceAll(strNama, " ", "_");
            var strJawatan2 = ReplaceAll(strJawatan, " ", "_");
            var strStageID2 = "g_charting_keputusan";
            var objShell = new ActiveXObject("WScript.Shell");
            var sysVar = objShell.Environment("System");
            //objShell.run("C:\Program Files\EpiWeb\ArcMap_ActiveX_Initializer\InititializeArcMap.exe");
            //objShell.run("C:\Program Files\eTanahStrata\InititializeStrata.exe")


            objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama2 + " " + strJawatan2 + " " + strIDPermohonan + " " + strStageID2);
        }

        function RunProgram2(strUserID, strNama, strJawatan, strIDPermohonan, strStageID) {

            var strNama2 = ReplaceAll(strNama, " ", "_");
            var strJawatan2 = ReplaceAll(strJawatan, " ", "_");
            var strStageID2 = "g_charting_semak";
            var objShell = new ActiveXObject("WScript.Shell");
            var sysVar = objShell.Environment("System");
            //objShell.run("C:\Program Files\EpiWeb\ArcMap_ActiveX_Initializer\InititializeArcMap.exe");
            //objShell.run("C:\Program Files\eTanahStrata\InititializeStrata.exe")


            objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama2 + " " + strJawatan2 + " " + strIDPermohonan + " " + strStageID2);
        }
        function showKeputusan(f) {
            var q = $(f).formSerialize();
            $.post('${pageContext.request.contextPath}/pelupusan/mesyuarat?addBilBangunan&status=Y', q,
            function(data) {
                //                 $.unblockUI();
                $('#page_div').html(data);
                //
            }, 'html');
        }
        function licenced(val) {
            if (val == 'F')
                $('#lesen').hide();
            else if (val == 'T')
                $('#lesen').show();
        }

        function keputusanL() {
            $('#keputusanLulus').show();
            $('#ulasanLulus').show();
        }

        function keputusanT() {
            $('#keputusanLulus').hide();
            $('#ulasanLulus').hide();
            $('#lulus4').hide();
            $('#tempohPegangan').hide();
        }

        function jenisHakmilikPegangan(val) {
            if (val == 'HMM') {
                $('#tempohPegangan').show();
            } else if (val == 'HSD') {
                $('#tempohPegangan').show();
            } else if (val == 'HSM') {
                $('#tempohPegangan').show();
            } else if (val == 'GM') {
                $('#tempohPegangan').hide();
            } else if (val == 'GMM') {
                $('#tempohPegangan').hide();
            } else if (val == 'GRN') {
                $('#tempohPegangan').hide();
            }
        }

        //Added by Aizuddin
        function doFunc(x) {

            if (x == "L")
            {
                $('#lulus1').show();
                $('#lulus2').show();
                $('#lulus3').show();
            }
            if (x == "T")
            {
                $('#lulus1').hide();
                $('#lulus2').hide();
                $('#lulus3').hide();
            }
            if (x == "TG")
            {
                $('#lulus1').hide();
                $('#lulus2').hide();
                $('#lulus3').hide();
            }
        }

        function doFuncPPRU(x) {

            if (x == "L")
            {
                $('#tsidang').show();
                $('#tsah').show();
                $('#kpohon').show();
                $('#klulus').show();
                $('#lulus4').hide();
                $('#klgsa').show();
            }
            if (x == "T")
            {
                $('#tsidang').show();
                $('#tsah').show();
                $('#kpohon').hide();
                $('#klulus').hide();
                $('#lulus4').hide();
                $('#klgsa').hide();
            }
            if (x == "TG")
            {
                $('#tsidang').show();
                $('#tsah').show();
                $('#kpohon').hide();
                $('#klulus').hide();
                $('#lulus4').hide();
                $('#klgsa').hide();
            }
        }

        function doFuncPPTR(x) {

            if (x == "L")
            {
                $('#lulus1').show();
                $('#lulus2').show();
                $('#lulus4').show();
            }
            if (x == "T")
            {
                $('#lulus1').hide();
                $('#lulus2').hide();
                $('#lulus4').hide();
            }
        }

        function doFuncPPTRNS(x) {

            if (x == "L")
            {
                $('#lulus1').show();
                $('#lulus2').show();
                $('#lulus4').show();
            }
            if (x == "T")
            {
                $('#lulus1').hide();
                $('#lulus2').hide();
                $('#lulus4').hide();
            }
            if (x == "TG")
            {
                $('#lulus1').hide();
                $('#lulus2').hide();
                $('#lulus4').hide();
            }
        }
        function doFuncPTPBP(x) {
            if (x == "T" || x == "TG")
            {
                $('#klgsa').hide();
            }

            if (x == "L")
            {
                $('#klgsa').show();
            }
        }

        function doFuncPRMMK(x) {
            if (x == "T" || x == "TG")
            {
                $('#klgsa').hide();
                $('#kpohon').hide();
            }

            if (x == "L")
            {
                $('#klgsa').show();
                $('#kpohon').show();
            }
        }


</script>

<s:form beanclass="etanah.view.stripes.pelupusan.RekodKeputusanMMKActionBean">

    <div class="subtitle">
        <s:errors/>
        <s:messages/>
        <s:hidden name="permohonanRujukanLuar.idPermohonan"/>
        <fieldset class="aras1">
            <legend>Maklumat Permohonan</legend>
            <p>
                <label for="ID Permohonan">ID Permohonan / Perserahan :</label>
                ${actionBean.permohonan.idPermohonan}&nbsp;
            </p>
            <p>
                <label for="Permohonan">Urusan :</label>
                ${actionBean.permohonan.kodUrusan.nama}&nbsp;
            </p>
        </fieldset >
    </div>
    <c:if test="${edit eq true}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Mesyuarat</legend>
                <c:if test="${bilMesyuarat}">
                    <p>
                        <c:choose>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PCRG' || actionBean.permohonan.kodUrusan.kod eq 'LPJH'}">
                                <label><font color="red">*</font>No. Kertas Mesyuarat :</label>
                                </c:when>
                                <c:otherwise>
                                <label><font color="red">*</font>Bilangan Mesyuarat :</label>
                                </c:otherwise>
                            </c:choose>
                            <s:text name="permohonanKertas.nomborRujukan"/>
                            <%--<s:text name="permohonanRujukanLuar.noSidang" onkeyup="validateNumber(this,this.value);" maxlength="3"/>--%>
                            <%--<s:hidden name="permohonanRujukanLuar.idRujukan"/>--%>
                    </p>
                </c:if>

                <c:if test="${displayTarikhMasa}">
                    <p>
                        <label>Tarikh :</label>
                        ${actionBean.permohonanKertas.tarikhSidang}&nbsp;
                    </p>

                </c:if>
                <!--:::${actionBean.kpsn}-->
                <c:if test="${keputusan}">
                    <p>
                        <label><font color="red">*</font>Keputusan :</label>

                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PWGSA'}">
                            LULUS
                            <s:hidden name="permohonan.keputusan" value="L"/>
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PWGSA'}">
                            <c:choose>
                                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'LMCRG' or actionBean.permohonan.kodUrusan.kod eq 'MLCRG'}">
                                    <s:radio name="kpsn" value="L" onclick="licenced('T');"/>Lulus&nbsp;
                                    <s:radio name="kpsn" value="T" onclick="licenced('F')"/>Tolak&nbsp;
                                </c:when>
                                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PPRU'}">
                                    <s:radio name="kpsn" id="testPPRU" value="L" onclick="doFuncPPRU(value);"/>Lulus&nbsp;
                                    <s:radio name="kpsn" id="testPPRU" value="T" onclick="doFuncPPRU(value);"/>Tolak&nbsp;
                                    <s:radio name="kpsn" id="testPPRU" value="TG" onclick="doFuncPPRU(value);"/>Tangguh&nbsp;
                                </c:when>
                                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PCRG' or actionBean.permohonan.kodUrusan.kod eq 'LPJH' or actionBean.permohonan.kodUrusan.kod eq 'LPJH' or actionBean.permohonan.kodUrusan.kod eq 'MPCRG' or actionBean.permohonan.kodUrusan.kod eq 'PJLB' or actionBean.permohonan.kodUrusan.kod eq 'MPJLB' or actionBean.permohonan.kodUrusan.kod eq 'LMCRG'}">
                                    <s:radio name="kpsn" value="L" onclick="licenced('T');"/>Lulus&nbsp;
                                    <s:radio name="kpsn" value="T" onclick="licenced('F')"/>Tolak&nbsp;
                                    <s:radio name="kpsn" value="TG" onclick="licenced('F')"/>Tangguh&nbsp;
                                </c:when>
                                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
                                    <s:radio name="kpsn" value="L" onclick="doFunc(value);"/>Lulus&nbsp;
                                    <s:radio name="kpsn" value="T" onclick="doFunc(value);"/>Tolak&nbsp;
                                </c:when>
                                <%--LPSP--%>
                                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'LPSP'}">
                                    <s:radio name="kpsn" value="L" onclick="doFunc(value);"/>Lulus&nbsp;
                                    <s:radio name="kpsn" value="T" onclick="doFunc(value);"/>Tolak&nbsp;
                                    <s:radio name="kpsn" value="TG" onclick="doFunc(value);"/>Tangguh&nbsp;
                                </c:when>
                                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PLPS'}">
                                    <s:radio name="kpsn" value="L" onclick="doFunc(value);"/>Lulus&nbsp;
                                    <s:radio name="kpsn" value="T" onclick="doFunc(value);"/>Tolak&nbsp;
                                    <s:radio name="kpsn" value="TG" onclick="doFunc(value);"/>Tangguh&nbsp;
                                </c:when>
                                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PRMMK'}">
                                    <s:radio name="kpsn" value="L" onclick="doFuncPRMMK(value);"/>Lulus&nbsp;
                                    <s:radio name="kpsn" value="T" onclick="doFuncPRMMK(value);"/>Tolak&nbsp;
                                    <s:radio name="kpsn" value="TG" onclick="doFuncPRMMK(value);"/>Tangguh&nbsp;
                                </c:when>
                                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBRZ'}">
                                    <s:radio name="kpsn" value="L" onclick="doFunc(value);"/>Lulus&nbsp;
                                    <s:radio name="kpsn" value="T" onclick="doFunc(value);"/>Tolak&nbsp;
                                </c:when>
                                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'RYKN'}">
                                    <s:radio name="kpsn" value="L" onclick="doFunc(value);"/>Lulus&nbsp;
                                    <s:radio name="kpsn" value="T" onclick="doFunc(value);"/>Tolak&nbsp;
                                    <s:radio name="kpsn" value="TG" onclick="doFunc(value);"/>Tangguh&nbsp;
                                </c:when>
                                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'BMBT'}">
                                    <s:radio name="kpsn" value="L" onclick="doFunc(value);"/>Lulus&nbsp;
                                    <s:radio name="kpsn" value="T" onclick="doFunc(value);"/>Tolak&nbsp;
                                    <s:radio name="kpsn" value="TG" onclick="doFunc(value);"/>Tangguh&nbsp;
                                </c:when>
                                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PTBTC'}">
                                    <s:radio name="kpsn" value="L" onclick="doFunc(value);"/>Lulus&nbsp;
                                    <s:radio name="kpsn" value="T" onclick="doFunc(value);"/>Tolak&nbsp;
                                    <s:radio name="kpsn" value="TG" onclick="doFunc(value);"/>Tangguh&nbsp;
                                </c:when>
                                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PTBTS'}">
                                    <s:radio name="kpsn" value="L" onclick="doFunc(value);"/>Lulus&nbsp;
                                    <s:radio name="kpsn" value="T" onclick="doFunc(value);"/>Tolak&nbsp;
                                    <s:radio name="kpsn" value="TG" onclick="doFunc(value);"/>Tangguh&nbsp;
                                </c:when>
                                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'WMRE' or actionBean.permohonan.kodUrusan.kod eq 'JMRE' or actionBean.permohonan.kodUrusan.kod eq 'BMRE' or actionBean.permohonan.kodUrusan.kod eq 'MMRE'}">
                                    <s:radio name="kpsn" value="L" onclick="doFunc(value);"/>Lulus&nbsp;
                                    <s:radio name="kpsn" value="T" onclick="doFunc(value);"/>Tolak&nbsp;
                                    <s:radio name="kpsn" value="TG" onclick="doFunc(value);"/>Tangguh&nbsp;
                                </c:when>
                                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PTMTA'}">
                                    <s:radio name="kpsn" value="L" onclick="doFunc(value);"/>Lulus&nbsp;
                                    <s:radio name="kpsn" value="T" onclick="doFunc(value);"/>Tolak&nbsp;
                                    <s:radio name="kpsn" value="TG" onclick="doFunc(value);"/>Tangguh&nbsp;
                                </c:when>
                                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PPTR'}">
                                    <c:if test="${actionBean.kodNegeri eq '04'}">
                                        <s:radio name="kpsn" id="testPPTR" value="L" onclick="doFuncPPTR(value);"/>Lulus&nbsp;
                                        <s:radio name="kpsn" id="testPPTR" value="T" onclick="doFuncPPTR(value);"/>Tolak&nbsp;
                                    </c:if>
                                    <c:if test="${actionBean.kodNegeri ne '04'}">
                                        <s:radio name="kpsn" value="L" onclick="doFuncPPTRNS(value);"/>Lulus&nbsp;
                                        <s:radio name="kpsn" value="T" onclick="doFuncPPTRNS(value);"/>Tolak&nbsp;
                                        <s:radio name="kpsn" value="TG" onclick="doFuncPPTRNS(value);"/>Tangguh&nbsp;
                                    </c:if>

                                </c:when>
                                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBGSA'}">

                                    <c:if test="${actionBean.kodNegeri eq '04'}">
                                        <s:radio name="kpsn" value="L" onclick="doFunc(value);"/>Lulus&nbsp;
                                        <s:radio name="kpsn" value="T" onclick="doFunc(value);"/>Tolak&nbsp;
                                    </c:if>
                                    <c:if test="${actionBean.kodNegeri ne '04'}">
                                        <s:radio name="kpsn" id="testPPTR" value="L" onclick="doFuncPPTRNS(value);"/>Lulus&nbsp;
                                        <s:radio name="kpsn" id="testPPTR" value="T" onclick="doFuncPPTRNS(value);"/>Tolak&nbsp;
                                        <s:radio name="kpsn" id="testPPTR" value="TG" onclick="doFuncPPTRNS(value);"/>Tangguh&nbsp;
                                    </c:if>
                                </c:when>
                                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PTGSA'}">
                                    <s:radio name="kpsn" value="L" onclick="doFuncPPTRNS(value);"/>Lulus&nbsp;
                                    <s:radio name="kpsn" value="T" onclick="doFuncPPTRNS(value);"/>Tolak&nbsp;
                                    <s:radio name="kpsn" value="TG" onclick="doFuncPPTRNS(value);"/>Tangguh&nbsp;
                                </c:when>
                                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PTPBP'}">
                                    <s:radio name="kpsn" value="L" id="keputusanPTPBP" onclick="doFuncPTPBP(value);"/>Lulus&nbsp;
                                    <s:radio name="kpsn" value="T" id="keputusanPTPBP" onclick="doFuncPTPBP(value);"/>Tolak&nbsp;
                                    <s:radio name="kpsn" value="TG" id="keputusanPTPBP" onclick="doFuncPTPBP(value);"/>Tangguh&nbsp;
                                </c:when>
                                <c:otherwise>
                                    <s:radio name="kpsn" value="L"/>Lulus&nbsp;
                                    <s:radio name="kpsn" value="T"/>Tolak&nbsp;
                                </c:otherwise>
                            </c:choose>

                        </c:if>

                    </p>
                </c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PBRZ'}">

                    <c:if test="${ulasanRAYT}">
                        <p>
                            <label><font color="red">*</font>Keputusan :</label>
                            <input name="kpsnT" id="kpsnT" value="T" type="hidden">
                            <s:radio id="kpsn" name="kpsn" value="L" onclick="keputusanL();"/>Lulus&nbsp;
                            <s:radio id="kpsnTolak" name="kpsn" value="T" onclick="keputusanT()"/>Tolak&nbsp;
                        <div class="subtitle" id="keputusanLulus">
                            <label>Jenis Hakmilik :</label>
                            <%--<s:select name="jenisHakmilik" id="jenisHakmilik" onchange="javaScript:jenisHakmilikPegangan(this.options[selectedIndex].text)">--%>
                            <s:select name="jenisHakmilik" id="jenisHakmilik" onchange="javaScript:jenisHakmilikPegangan(this.value)">
                                <s:option label="Sila Pilih" value="" />                                                    
                                <s:options-collection collection="${listUtil.senaraiKodHakmilikDIS}" label="nama" value="kod" sort="nama"/>
                            </s:select>
                        </div>
                        <%--</p>--%>
                        <div class="subtitle" id="tempohPegangan">
                            <label>Tempoh Pegangan :</label>
                            <s:text name="hakmilikPermohonan.tempohPajakan" />&nbsp;Tahun
                        </div>
                        <div class="subtitle" id="ulasanLulus">
                            <label>Ulasan :</label>
                            <s:textarea name="permohonan.ulasan" cols="50" rows="5"/>
                        </div>

                    </c:if>
                </c:if>
                <c:if test="${tarikhMasa}">
                    <div id="tsidang"><p>
                            <label><font color="red" id="lulus1">*</font>Tarikh Bersidang :</label>
                                <s:text name="permohonanKertas.tarikhSidang" id="datepicker" class="datepicker" formatPattern="dd/MM/yyyy" />
                        </p></div>

                    <div id="tsah"><p>
                            <label><font color="red" id="lulus2">*</font>Tarikh Disahkan :</label>
                                <s:text name="permohonanKertas.tarikhSahKeputusan" id="datepicker1" class="datepicker" formatPattern="dd/MM/yyyy"/>
                        </p></div>

                    <div id="kpohon"><p>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPRU'}">
                                <label>Keluasan Dipohon :</label>
                            </c:if>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PTBTS' and actionBean.permohonan.kodUrusan.kod ne 'PTBTC' and actionBean.permohonan.kodUrusan.kod ne 'BMBT' and actionBean.permohonan.kodUrusan.kod ne 'PPRU' and actionBean.permohonan.kodUrusan.kod ne 'PTGSA' and actionBean.permohonan.kodUrusan.kod ne 'PLPS' and actionBean.permohonan.kodUrusan.kod ne 'PTPBP' and actionBean.permohonan.kodUrusan.kod ne 'PBGSA' and actionBean.permohonan.kodUrusan.kod ne '831A' and actionBean.permohonan.kodUrusan.kod ne 'ESK4' and actionBean.permohonan.kodUrusan.kod ne 'ESK8' and actionBean.permohonan.kodUrusan.kod ne 'PBA' and actionBean.permohonan.kodUrusan.kod ne 'RYKN' and actionBean.permohonan.kodUrusan.kod ne 'RYKN' and actionBean.permohonan.kodUrusan.kod ne 'JMRE'}">
                                <label>Keluasan Dipohon :</label>
                            </c:if>
                            <%--LPSP masuk sini--%>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PTBTS' and actionBean.permohonan.kodUrusan.kod ne 'PTBTC' and actionBean.permohonan.kodUrusan.kod ne 'BMBT' and actionBean.permohonan.kodUrusan.kod ne 'PTGSA' and actionBean.permohonan.kodUrusan.kod ne 'PBGSA' and actionBean.permohonan.kodUrusan.kod ne 'PLPS' and actionBean.permohonan.kodUrusan.kod ne 'PTPBP' and actionBean.permohonan.kodUrusan.kod ne 'RYKN' }">
                                ${actionBean.hakmilikPermohonan.luasTerlibat}  ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}
                                <br>
                            </c:if>
                            <%--
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PRMMK'}">
                            ${actionBean.trizabPermohonan.luasTerlibat} ${actionBean.trizabPermohonan.kodUnitLuas.nama} 
                        </c:if>--%>
                        </p></div>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PRMP'}">
                        <p>
                            <label>Keluasan Disyor :</label>
                            ${actionBean.hakmilikPermohonan.luasDiluluskan} ${actionBean.hakmilikPermohonan.luasLulusUom.nama}
                        </p>
                    </c:if>

                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'BMBT' && actionBean.permohonan.kodUrusan.kod ne 'PTBTC' && actionBean.permohonan.kodUrusan.kod ne 'PTBTS'}">
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT' or actionBean.permohonan.kodUrusan.kod eq 'MMRE' and actionBean.kpsn eq 'L' or (actionBean.kpsn eq null and actionBean.permohonan.kodUrusan.kod ne '831A' and actionBean.permohonan.kodUrusan.kod ne 'ESK4' and actionBean.permohonan.kodUrusan.kod ne 'ESK8' and actionBean.permohonan.kodUrusan.kod ne 'PBA' and actionBean.permohonan.kodUrusan.kod ne 'PJLB' and actionBean.permohonan.kodUrusan.kod ne 'MPJLB' and actionBean.permohonan.kodUrusan.kod ne 'LMCRG' and actionBean.permohonan.kodUrusan.kod ne 'PRIZ' and actionBean.permohonan.kodUrusan.kod ne 'MLCRG' and actionBean.permohonan.kodUrusan.kod ne 'LSTP' and actionBean.permohonan.kodUrusan.kod ne 'RYKN' and actionBean.permohonan.kodUrusan.kod ne 'PBRZ' and actionBean.permohonan.kodUrusan.kod ne 'PPRU') and  actionBean.permohonan.kodUrusan.kod ne 'PTGSA' and actionBean.permohonan.kodUrusan.kod ne 'PBGSA' and actionBean.permohonan.kodUrusan.kod ne 'PLPS' and actionBean.permohonan.kodUrusan.kod ne 'PTPBP' and actionBean.permohonan.kodUrusan.kod ne 'LPSP' and actionBean.permohonan.kodUrusan.kod ne 'PRMMK' and actionBean.permohonan.kodUrusan.kod ne 'JMRE' and actionBean.permohonan.kodUrusan.kod ne 'BMRE'}">
                            <p id="lulus3">
                                <label><font color="red">*</font>Keluasan Diluluskan :</label>

                                <%--  <s:hidden name="noPt.idPt" />--%>
                                <%-- <s:text name="noPt.hakmilikPermohonan.id" value="${actionBean.hakmilikPermohonan.id}"/>--%>
                                <s:text name="hakmilikPermohonan.luasDiluluskan"  id="hakmilikPermohonan.luasDiluluskan" formatPattern="#,###,##0.0000"/>
                                <s:select name="luasLulusUom.kod" value="${actionBean.hakmilikPermohonan.luasLulusUom.kod}">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:option value="H">Hektar</s:option>
                                    <s:option value="M">Meter Persegi</s:option>
                                </s:select>
                            </p>
                        </c:if>

                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPTR' or (actionBean.permohonan.kodUrusan.kod ne '831A' and actionBean.permohonan.kodUrusan.kod ne 'ESK4' and actionBean.permohonan.kodUrusan.kod ne 'ESK8' and actionBean.permohonan.kodUrusan.kod ne 'PBA' and actionBean.permohonan.kodUrusan.kod ne 'PJLB' and actionBean.permohonan.kodUrusan.kod ne 'MPJLB' and actionBean.permohonan.kodUrusan.kod ne 'PCRG' and actionBean.permohonan.kodUrusan.kod ne 'LPJH' and actionBean.permohonan.kodUrusan.kod ne 'LMCRG' and actionBean.permohonan.kodUrusan.kod ne 'MLCRG' and actionBean.permohonan.kodUrusan.kod ne 'RYKN' and actionBean.permohonan.kodUrusan.kod ne 'PBRZ' and actionBean.permohonan.kodUrusan.kod ne 'PPRU') and  actionBean.permohonan.kodUrusan.kod ne 'PTGSA' and  actionBean.permohonan.kodUrusan.kod ne 'PTMTA' and actionBean.permohonan.kodUrusan.kod ne 'PBGSA' and actionBean.permohonan.kodUrusan.kod ne 'PLPS' and actionBean.permohonan.kodUrusan.kod ne 'PTPBP' and actionBean.permohonan.kodUrusan.kod ne 'LPSP' and actionBean.permohonan.kodUrusan.kod ne 'PRMMK' and actionBean.permohonan.kodUrusan.kod ne 'MMRE' and actionBean.permohonan.kodUrusan.kod ne 'JMRE'}">
                            <p id="lulus4">
                                <label><font color="red">*</font>Keluasan Diluluskan :</label>

                                <%--  <s:hidden name="noPt.idPt" />--%>
                                <%-- <s:text name="noPt.hakmilikPermohonan.id" value="${actionBean.hakmilikPermohonan.id}"/>--%>
                                <s:text name="hakmilikPermohonan.luasDiluluskan"  id="hakmilikPermohonan.luasDiluluskan" formatPattern="#,###,##0.0000"/>
                                <s:select name="luasLulusUom.kod" value="${actionBean.hakmilikPermohonan.luasLulusUom.kod}">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:option value="H">Hektar</s:option>
                                    <s:option value="M">Meter Persegi</s:option>
                                </s:select>
                            </p>
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'ESK4' || actionBean.permohonan.kodUrusan.kod eq 'ESK8'}">
                            <p>
                            <div class="subtitle" id="ulasanLulus">
                                <label>Ulasan :</label>
                                <s:textarea name="permohonan.ulasan" cols="50" rows="5"/>
                            </div>
                        </c:if>
                        <div id="klgsa">
                            <p id="lulus4">
                                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PLPS' or actionBean.permohonan.kodUrusan.kod eq 'PTPBP' or actionBean.permohonan.kodUrusan.kod eq 'LPSP' or actionBean.permohonan.kodUrusan.kod eq 'PTGSA' or actionBean.permohonan.kodUrusan.kod eq 'PBGSA' or actionBean.permohonan.kodUrusan.kod eq 'PBRZ' or (actionBean.permohonan.kodUrusan.kod ne '831A' and actionBean.permohonan.kodUrusan.kod ne 'ESK4' and actionBean.permohonan.kodUrusan.kod ne 'ESK8'  and actionBean.permohonan.kodUrusan.kod ne 'PBA' and actionBean.permohonan.kodUrusan.kod ne 'PJLB' and actionBean.permohonan.kodUrusan.kod ne 'PCRG' and actionBean.permohonan.kodUrusan.kod ne 'LPJH' and actionBean.permohonan.kodUrusan.kod ne 'LSTP' and actionBean.permohonan.kodUrusan.kod ne 'RYKN' and actionBean.permohonan.kodUrusan.kod ne 'PPRU' and actionBean.permohonan.kodUrusan.kod ne 'WMRE' and actionBean.permohonan.kodUrusan.kod ne 'JMRE' and actionBean.permohonan.kodUrusan.kod ne 'BMRE' and actionBean.permohonan.kodUrusan.kod ne 'MMRE' and actionBean.permohonan.kodUrusan.kod ne 'PPTR' and  actionBean.permohonan.kodUrusan.kod ne 'PTMTA' and actionBean.permohonan.kodUrusan.kod ne 'MPJLB' and actionBean.permohonan.kodUrusan.kod ne 'LMCRG' and actionBean.permohonan.kodUrusan.kod ne 'MLCRG' and actionBean.permohonan.kodUrusan.kod ne 'RAYT' and actionBean.permohonan.kodUrusan.kod ne 'PRIZ')}">
                                    <label><font color="red">*</font>Keluasan Diluluskan :</label>

                                    <s:text name="hakmilikPermohonan.luasDiluluskan"  id="hakmilikPermohonan.luasDiluluskan" />
                                    <s:select name="luasLulusUom.kod" value="${actionBean.hakmilikPermohonan.luasLulusUom.kod}">
                                        <s:option value="">Sila Pilih</s:option>
                                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PRIZ' or actionBean.permohonan.kodUrusan.kod eq 'PRMMK' or actionBean.permohonan.kodUrusan.kod eq 'PBRZ'}">
                                            <s:option value="M">Meter Persegi</s:option>
                                            <s:option value="H">Hektar</s:option>
                                        </c:if>
                                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PLPS' or actionBean.permohonan.kodUrusan.kod eq 'PTPBP' or actionBean.permohonan.kodUrusan.kod eq 'LPSP' or actionBean.permohonan.kodUrusan.kod eq 'PJLB' or actionBean.permohonan.kodUrusan.kod eq 'MPJLB' or actionBean.permohonan.kodUrusan.kod eq 'LMCRG'}">
                                            <s:option value="H">Hektar</s:option>
                                            <s:option value="M">Meter Persegi</s:option>
                                        </c:if>
                                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTGSA'}">
                                            <s:option value="H">Hektar</s:option>
                                            <s:option value="M">Meter Persegi</s:option>
                                        </c:if>
                                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBGSA'}">
                                            <s:option value="H">Hektar</s:option>
                                            <s:option value="M">Meter Persegi</s:option>
                                        </c:if>
                                    </s:select>
                                </c:if>
                            </p>
                        </div>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'BMBT' || actionBean.permohonan.kodUrusan.kod eq 'PTBTC' || actionBean.permohonan.kodUrusan.kod eq 'PTBTS'}">
                        <p>
                            <label>Isipadu Dipohon :</label>
                            ${actionBean.hakmilikPermohonan.luasTerlibat} ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}
                        </p>
                        <p id="lulus3">
                            <label><font color="red">*</font>Isipadu Diluluskan :</label>

                            <%--  <s:hidden name="noPt.idPt" />--%>
                            <%-- <s:text name="noPt.hakmilikPermohonan.id" value="${actionBean.hakmilikPermohonan.id}"/>--%>
                            <s:text name="hakmilikPermohonan.luasDiluluskan"  id="hakmilikPermohonan.luasDiluluskan"/>
                            <s:select name="luasLulusUom.kod" value="${actionBean.hakmilikPermohonan.luasLulusUom.kod}">
                                <s:option value="">Sila Pilih</s:option>
                                <s:option value="H">Hektar</s:option>
                                <s:option value="M">Meter Persegi</s:option>
                                <s:option value="MP">Meter Padu</s:option>
                            </s:select>
                        </p>
                        <p>
                            <label>Kedalaman Dipohon :</label>
                            ${actionBean.hakmilikPermohonan.kedalamanTanah} ${actionBean.hakmilikPermohonan.kedalamanTanahUOM.nama}
                        </p>
                        <%--<br>--%>

                        <p id="lulus5">
                            <label><font color="red">*</font>Kedalaman Diluluskan :</label>

                            <s:text name="hakmilikPermohonan.kedalamanTanahDiluluskan"  id="hakmilikPermohonan.kedalamanTanahDiluluskan"/>
                            <s:select name="kedalamanTanahUOMDiLuluskan.kod" value="${actionBean.hakmilikPermohonan.kedalamanTanahUOMDiLuluskan.kod}">
                                <s:option value="">Sila Pilih</s:option>
                                <s:option value="JK">Kaki</s:option>
                                <s:option value="KM">Kilometer</s:option>
                                <s:option value="JM">Meter</s:option>
                            </s:select>
                        </p>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPRU'}">
                        <div id="klulus"><p>
                                <label><font color="red">*</font>Keluasan Diluluskan :</label>

                                <%--  <s:hidden name="noPt.idPt" />--%>
                                <%-- <s:text name="noPt.hakmilikPermohonan.id" value="${actionBean.hakmilikPermohonan.id}"/>--%>
                                <s:text name="hakmilikPermohonan.luasDiluluskan"  id="hakmilikPermohonan.luasDiluluskan" />
                                <s:select name="luasLulusUom.kod" value="${actionBean.hakmilikPermohonan.luasLulusUom.kod}">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:option value="H">Hektar</s:option>
                                    <s:option value="M">Meter Persegi</s:option>
                                    <s:option value="MP">Meter Padu</s:option>
                                </s:select>
                            </p></div>
                        </c:if>
                        <c:if test="${(actionBean.permohonan.kodUrusan.kod ne '831A' and actionBean.permohonan.kodUrusan.kod ne 'ESK4' and actionBean.permohonan.kodUrusan.kod ne 'ESK8' and actionBean.permohonan.kodUrusan.kod ne 'PBA' and actionBean.permohonan.kodUrusan.kod ne 'PCRG' and actionBean.permohonan.kodUrusan.kod ne 'LPJH' and actionBean.permohonan.kodUrusan.kod ne 'PJLB' and actionBean.permohonan.kodUrusan.kod ne 'MPJLB' and actionBean.permohonan.kodUrusan.kod ne 'LMCRG' and actionBean.permohonan.kodUrusan.kod ne 'LSTP' and actionBean.permohonan.kodUrusan.kod ne 'RYKN' and actionBean.permohonan.kodUrusan.kod ne 'PBRZ' and actionBean.permohonan.kodUrusan.kod ne 'PPRU' and actionBean.permohonan.kodUrusan.kod ne 'WMRE' and actionBean.permohonan.kodUrusan.kod ne 'JMRE' and actionBean.permohonan.kodUrusan.kod ne 'BMRE' and actionBean.permohonan.kodUrusan.kod ne 'MMRE') and  actionBean.permohonan.kodUrusan.kod ne 'PTGSA' and  actionBean.permohonan.kodUrusan.kod ne 'BMBT' and  actionBean.permohonan.kodUrusan.kod ne 'PTBTS' and  actionBean.permohonan.kodUrusan.kod ne 'PTBTC' and  actionBean.permohonan.kodUrusan.kod ne 'PPTR' and  actionBean.permohonan.kodUrusan.kod ne 'PTMTA' and  actionBean.permohonan.kodUrusan.kod ne 'PBGSA' and actionBean.permohonan.kodUrusan.kod ne 'PLPS' and actionBean.permohonan.kodUrusan.kod ne 'PTPBP' and actionBean.permohonan.kodUrusan.kod ne 'LPSP' and actionBean.permohonan.kodUrusan.kod ne 'PRMMK' and actionBean.permohonan.kodUrusan.kod ne 'RAYT' and actionBean.permohonan.kodUrusan.kod ne 'PRIZ'}">
                        <label>Keluasan diluluskan :</label>
                        <s:text name="hakmilikPermohonan.luasDiluluskan"  id="hakmilikPermohonan.luasDiluluskan" formatPattern="#,###,##0.00"/>
                        ${actionBean.hakmilikPermohonan.luasLulusUom.nama}
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LMCRG' or actionBean.permohonan.kodUrusan.kod eq 'MLCRG' or actionBean.permohonan.kodUrusan.kod eq 'PJLB' or actionBean.permohonan.kodUrusan.kod eq 'MPJLB' or actionBean.permohonan.kodUrusan.kod eq 'LMCRG' or actionBean.permohonan.kodUrusan.kod eq 'PCRG' or actionBean.permohonan.kodUrusan.kod eq 'MPCRG' or actionBean.permohonan.kodUrusan.kod eq 'LPJH'}">
                        <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PCRG' and actionBean.permohonan.kodUrusan.kod ne 'LPJH'}">
                            <p>
                                <label>Bayaran (RM) :</label>
                                <c:if test="${!view}">
                                    <s:text name="amnt" size="20" onkeyup="validateNumber(this,this.value);"/> &nbsp;
                                </c:if>

                                <c:if test="${view}">
                                    ${actionBean.permohonanTuntutanKos.amaunTuntutan}&nbsp;
                                </c:if>

                            </p>
                            <p>
                                <label>Tempoh :</label>
                                <c:if test="${!view}">
                                    <s:text name="hakmilikPermohonan.tempohPajakan" size="3" onkeyup="validateNumber(this,this.value);"/>
                                </c:if>

                                <c:if test="${view}">
                                    ${actionBean.hakmilikPermohonan.tempohPajakan}&nbsp;
                                </c:if>
                                Tahun.
                            </p>
                        </c:if>
                        <p>
                            <label>Luas Diluluskan:</label>
                            <c:if test="${!view}">
                                <s:text name="hakmilikPermohonan.luasDiluluskan" size="15"/>
                            </c:if>
                            <c:if test="${view}">
                                ${actionBean.hakmilikPermohonan.luasDiluluskan}&nbsp;
                            </c:if>
                            <c:if test="${!view}">
                                <s:select name="luasLulusUom.kod" id="koduom" value="${actionBean.hakmilikPermohonan.luasLulusUom.kod}">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:option value="M">Meter Persegi</s:option>
                                    <s:option value="H">Hektar</s:option>
                                </s:select>
                            </c:if>
                            <c:if test="${view}">
                                ${actionBean.hakmilikPermohonan.luasLulusUom.nama}&nbsp;
                            </c:if>
                        </p>
                        <c:if test="${actionBean.kodNegeri eq '05' and (actionBean.permohonan.kodUrusan.kod eq 'PCRG' or actionBean.permohonan.kodUrusan.kod eq 'LPJH')}">

                            <p>
                                <label>Bahan Dikeluarkan :</label>
                                <c:if test="${!view}">
                                    <s:text name="hakmilikPermohonan.bahanKeluar" size="30"/>
                                </c:if>

                                <c:if test="${view}">
                                    ${actionBean.hakmilikPermohonan.bahanKeluar}&nbsp;
                                </c:if>
                            </p>
                            <p>
                                <label>Fi Pengeluaran Lesen :</label>
                                <c:if test="${!view}">
                                    <%--hakmilikPermohonan.fiPengeluaran--%>
                                    RM <s:text name="fiPengeluaran" size="4" id="f_pengeluaran"/>
                                </c:if>

                                <c:if test="${view}">
                                    RM ${actionBean.hakmilikPermohonan.fiPengeluaran}&nbsp;
                                </c:if>
                            </p>
                            <p>
                                <label>Fi Pegangan Tahunan :</label>
                                <c:if test="${!view}">
                                    <c:set value="${actionBean.hakmilikPermohonan.fiPegangan}" var="a"/>
                                    <%--hakmilikPermohonan.fiPegangan--%>
                                    RM <s:text name="fiPegangan" size="4" id="f_pegangan"/> 
                                </c:if>

                                <c:if test="${view}">
                                    RM ${actionBean.hakmilikPermohonan.fiPegangan}&nbsp;
                                </c:if>
                            </p>
                            <p>
                                <label>Unit Pegangan Tahunan :</label>
                                <c:if test="${!view}">
                                    <c:set value="${actionBean.hakmilikPermohonan.jumlahPegangan}" var="b"/>
                                    <s:text name="hakmilikPermohonan.jumlahPegangan" size="4" id="f_unit"/>
                                </c:if>

                                <c:if test="${view}">
                                    ${actionBean.hakmilikPermohonan.jumlahPegangan}&nbsp;
                                </c:if>
                            </p>
                            <p>
                                <label>Tempoh Maklum Balas :</label>
                                <c:if test="${!view}">
                                    <s:text name="hakmilikPermohonan.tempohPengosongan" size="4"/>
                                </c:if>

                                <c:if test="${view}">
                                    ${actionBean.hakmilikPermohonan.tempohPengosongan}&nbsp;
                                </c:if>
                            </p>
                            <p>
                                <label>Bayaran :</label>
                                <c:if test="${!view}">
                                    <%--<c:set value="${a*b}" var="e"/>
                                    <fmt:formatNumber pattern="#,##0.00" value="${e}"/>--%>
                                    <c:if test="${actionBean.permohonanTuntutanKos.amaunTuntutan ne null}"> <fmt:formatNumber pattern="#,##0.00" value="${actionBean.permohonanTuntutanKos.amaunTuntutan}"/></c:if>
                                    <c:if test="${actionBean.permohonanTuntutanKos.amaunTuntutan eq null}"> - </c:if>
                                </c:if>

                                <c:if test="${view}">
                                    <c:set value="${a*b}" var="e"/>
                                    <fmt:formatNumber pattern="#,##0.00" value="${e}"/>
                                    <%--${actionBean.hakmilikPermohonan.tempohPajakan}&nbsp;--%>
                                </c:if>
                            </p>
                        </c:if>
                    </c:if>
                </c:if>
                <c:if test="${ulasan}">
                    <p>
                        <label>Ulasan :</label>
                        <s:textarea name="permohonanRujukanLuar.ulasan" cols="50" rows="5"/>
                    </p>
                </c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'MLCRG' or actionBean.permohonan.kodUrusan.kod eq 'PJLB'}">
                    <c:if test="${catatan}">
                        <p>
                            <label>Catatan :</label>
                            <s:textarea name="permohonanKertas.noLaporan" cols="100" rows="20"/>
                        </p>
                    </c:if>

                </c:if>


                <p>
                    <label>&nbsp;</label>
                    <c:if test="${simpanUlasan2}">
                        <s:button name="simpanUlasan2" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </c:if>
                    <c:if test="${simpanUlasan}">
                        <s:button name="simpanUlasan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </c:if>
                    <c:if test="${simpanDate}">
                        <s:button name="simpanDate" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </c:if>
                    <c:if test="${simpanTiadaResult}">
                        <s:button name="simpanTiadaResult" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </c:if>
                    <c:if test="${simpanMesyuarat}">
                        <c:if test="${actionBean.kodNegeri eq '04'}">
                            <%--<c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PJLB' and actionBean.permohonan.kodUrusan.kod ne 'MPJLB' and actionBean.permohonan.kodUrusan.kod ne 'MLCRG'}">--%>
                                <s:button name="simpanMesyuarat" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                            <%--</c:if>--%>
                        </c:if>
                        <c:if test="${actionBean.kodNegeri eq '05'}">
                            <%--<c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PJLB' or actionBean.permohonan.kodUrusan.kod eq 'PCRG'}">--%>
                            <s:button name="simpanMesyuarat" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                            <%--</c:if>--%>
                        </c:if>
                    </c:if>


                </p>
            </fieldset >
        </div>
    </c:if>
    <c:if test="${view eq true}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Mesyuarat</legend>
                <p>
                    <c:choose>
                        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PCRG' || actionBean.permohonan.kodUrusan.kod eq 'LPJH'}">
                            <label><font color="red">*</font>No. Kertas Mesyuarat :</label>
                            </c:when>
                            <c:otherwise>
                            <label><font color="red">*</font>Bilangan Mesyuarat :</label>
                            </c:otherwise>
                        </c:choose>
                        ${actionBean.permohonanKertas.nomborRujukan}&nbsp;
                </p>
                <%--<p>
                    <label>Tarikh :</label>
                    ${actionBean.permohonanKertas.tarikhSidang}&nbsp;
                </p --%>
                <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'MMRE'}">
                    <p>
                        <label>Keputusan :</label>
                        ${actionBean.kpsn} (${actionBean.kpsnnama})&nbsp;
                    </p>
                </c:if>

                <p>
                    <label>Tarikh Bersidang :</label>
                    <%--${actionBean.permohonanKertas.tarikhSidang}&nbsp;--%>
                    <fmt:formatDate value="${actionBean.permohonanKertas.tarikhSidang}" pattern="dd/MM/yyyy "/>&nbsp;
                </p>

                <p>
                    <label>Tarikh Disahkan :</label>
                    <%--${actionBean.permohonanKertas.tarikhSahKeputusan}&nbsp;--%>
                    <fmt:formatDate value="${actionBean.permohonanKertas.tarikhSahKeputusan}" pattern="dd/MM/yyyy "/>&nbsp;
                </p>
                <p>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPRU'}">
                        <label>Keluasan Dipohon :</label>
                        ${actionBean.hakmilikPermohonan.luasTerlibat} ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'BMBT' ||  actionBean.permohonan.kodUrusan.kod eq 'PTBTS' ||  actionBean.permohonan.kodUrusan.kod eq 'PTBTC'}">
                        <label>Isipadu Dipohon :</label>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'BMBT' and actionBean.permohonan.kodUrusan.kod ne 'PTBTC' and actionBean.permohonan.kodUrusan.kod ne 'PTBTS' and actionBean.permohonan.kodUrusan.kod ne 'JMRE' and actionBean.permohonan.kodUrusan.kod ne 'PPRU' and actionBean.permohonan.kodUrusan.kod ne 'PTGSA' and actionBean.permohonan.kodUrusan.kod ne 'PBGSA' and actionBean.permohonan.kodUrusan.kod ne '831A' and actionBean.permohonan.kodUrusan.kod ne 'ESK4' and actionBean.permohonan.kodUrusan.kod ne 'ESK8' and actionBean.permohonan.kodUrusan.kod ne 'PBA' and actionBean.permohonan.kodUrusan.kod ne 'RYKN' and  actionBean.permohonan.kodUrusan.kod ne 'BMBT' and  actionBean.permohonan.kodUrusan.kod ne 'PTBTS' and  actionBean.permohonan.kodUrusan.kod ne 'PTBTC' and actionBean.permohonan.kodUrusan.kod ne 'PTPBP'}">
                        <label>Keluasan Dipohon :</label>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PPRU' and actionBean.permohonan.kodUrusan.kod ne 'PTGSA' and actionBean.permohonan.kodUrusan.kod ne 'PBGSA' and actionBean.permohonan.kodUrusan.kod ne 'PRMMK' and actionBean.permohonan.kodUrusan.kod ne '831A' and actionBean.permohonan.kodUrusan.kod ne 'ESK4' and actionBean.permohonan.kodUrusan.kod ne 'ESK8' and actionBean.permohonan.kodUrusan.kod ne 'RYKN' and actionBean.permohonan.kodUrusan.kod ne 'PTPBP'}">
                        ${actionBean.hakmilikPermohonan.luasTerlibat} ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PRMMK'}">
                        <c:choose>
                            <c:when test="${actionBean.trizabPermohonan.luasTerlibat eq null}">
                                ${actionBean.hakmilikPermohonan.luasTerlibat} ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}
                            </c:when>
                            <c:otherwise>
                                ${actionBean.trizabPermohonan.luasTerlibat} ${actionBean.trizabPermohonan.kodUnitLuas.nama}
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTPBP'}">
                        <c:if test="${actionBean.kpsn eq 'L'}">
                            <label>Keluasan Dipohon :</label>
                            ${actionBean.hakmilikPermohonan.luasTerlibat} ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}
                        </c:if>
                    </c:if>
                </p>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PRMP'}">
                    <p>
                        <label>Keluasan Disyor :</label>
                        ${actionBean.hakmilikPermohonan.luasDiluluskan} ${actionBean.hakmilikPermohonan.luasLulusUom.nama}
                    </p>
                </c:if>

                <p id="lulus3">
                    <%--     Added by iskandar, and actionBean.kpsn eq 'L'                   --%>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT' and actionBean.kpsn eq 'L'}">
                        <label>Keluasan diluluskan :</label>

                        <s:hidden name="noPt.idPt" />

                        ${actionBean.hakmilikPermohonan.luasDiluluskan}
                        ${actionBean.hakmilikPermohonan.luasLulusUom.nama}
                    </c:if>

                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'BMBT' && actionBean.permohonan.kodUrusan.kod ne 'PTBTC' && actionBean.permohonan.kodUrusan.kod ne 'PTBTS'}">
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPRU' or actionBean.permohonan.kodUrusan.kod eq 'PTMTA' or actionBean.permohonan.kodUrusan.kod eq 'BMRE' or actionBean.permohonan.kodUrusan.kod eq 'PLPS' or actionBean.permohonan.kodUrusan.kod eq 'PTPBP' or actionBean.permohonan.kodUrusan.kod eq 'LPSP' or actionBean.permohonan.kodUrusan.kod eq 'PPTR' or actionBean.permohonan.kodUrusan.kod eq 'PRIZ' or (actionBean.permohonan.kodUrusan.kod ne '831A' and actionBean.permohonan.kodUrusan.kod ne 'ESK4' and actionBean.permohonan.kodUrusan.kod ne 'ESK8' and actionBean.permohonan.kodUrusan.kod ne 'PBA' and actionBean.permohonan.kodUrusan.kod ne 'RYKN' and actionBean.permohonan.kodUrusan.kod ne 'PRMMK' and actionBean.permohonan.kodUrusan.kod ne 'PTGSA' and actionBean.permohonan.kodUrusan.kod ne 'JMRE')}">
                            <c:if test="${actionBean.kpsn ne 'T'}">
                                <label>Keluasan Diluluskan :</label>
                                ${actionBean.hakmilikPermohonan.luasDiluluskan}
                                ${actionBean.hakmilikPermohonan.luasLulusUom.nama}
                            </c:if>

                        </c:if>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'BMBT' || actionBean.permohonan.kodUrusan.kod eq 'PTBTC' || actionBean.permohonan.kodUrusan.kod eq 'PTBTS'}">
                        <label>Isipadu Diluluskan :</label>
                        ${actionBean.hakmilikPermohonan.luasDiluluskan}
                        ${actionBean.hakmilikPermohonan.luasLulusUom.nama}<p>
                        <label>Kedalaman Dipohon :</label>
                        ${actionBean.hakmilikPermohonan.kedalamanTanah}
                        ${actionBean.hakmilikPermohonan.kedalamanTanahUOM.nama}<p>
                        <label>Kedalaman Diluluskan :</label>
                        ${actionBean.hakmilikPermohonan.kedalamanTanahDiluluskan}
                        ${actionBean.hakmilikPermohonan.kedalamanTanahUOMDiLuluskan.nama}

                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PRMMK'}">
                        <label>Keluasan Diluluskan :</label>
                        ${actionBean.hakmilikPermohonan.luasDiluluskan}
                        ${actionBean.hakmilikPermohonan.luasLulusUom.nama}
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTGSA' and actionBean.kpsn eq 'L'}">
                        <label>Keluasan Diluluskan :</label>
                        ${actionBean.hakmilikPermohonan.luasDiluluskan}
                        ${actionBean.hakmilikPermohonan.luasLulusUom.nama}
                    </c:if>
                </p>

                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LMCRG' or actionBean.permohonan.kodUrusan.kod eq 'PJLB' or actionBean.permohonan.kodUrusan.kod eq 'MPJLB' or actionBean.permohonan.kodUrusan.kod eq 'LMCRG' or actionBean.permohonan.kodUrusan.kod eq 'PCRG' or actionBean.permohonan.kodUrusan.kod eq 'LPJH' or actionBean.permohonan.kodUrusan.kod eq 'MPCRG'}">
                    <p>
                        <label>Bayaran (RM) :</label>
                        ${actionBean.permohonanTuntutanKos.amaunTuntutan}&nbsp; Setahun
                    </p>
                    <p>
                        <label>Tempoh :</label>
                        ${actionBean.hakmilikPermohonan.tempohPajakan}&nbsp;Tahun.
                    </p>
                    <p>
                        <label>Luas Diluluskan:</label>
                        ${actionBean.hakmilikPermohonan.luasDiluluskan}&nbsp;${actionBean.hakmilikPermohonan.luasLulusUom.nama}&nbsp;
                    </p>
                </c:if>
                <c:if test="${ulasan}">
                    <p>
                        <label>Ulasan :</label>
                        ${actionBean.permohonanRujukanLuar.ulasan}&nbsp;
                    </p>
                </c:if>
            </fieldset >
        </div>

    </c:if>
    <c:if test="${actionBean.kodNegeri eq 04}">

        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LMCRG' or actionBean.permohonan.kodUrusan.kod eq 'PJLB' or actionBean.permohonan.kodUrusan.kod eq 'MPJLB' or actionBean.permohonan.kodUrusan.kod eq 'MLCRG' or actionBean.permohonan.kodUrusan.kod eq 'MPCRG'}">
            <div class="subtitle" id="lesen">
                <fieldset class="aras1" align="center">
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LMCRG' or actionBean.permohonan.kodUrusan.kod eq 'MPCRG'}">
                        <legend>
                            Lesen Mencarigali dan Penjelajahan
                        </legend>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PJLB'}">
                        <legend>
                            Lesen Pajakan Lombong
                        </legend>
                    </c:if>
                    <p>
                        <label>Rujukan Fail :</label>
                        ${actionBean.idPermohonan}
                        &nbsp;

                    </p>
                    <p>
                        <label>No. Pajakan/Pendaftaran :</label>
                        ${actionBean.permit.noPermit}
                        &nbsp;

                    </p>
                    <p>
                        <label>No. Lot/PT :</label>
                        ${actionBean.noLot}
                        &nbsp;

                    </p>
                    <p>
                        <label>Bandar/ Kampung/ Mukim :</label>
                        ${actionBean.bpm}

                        &nbsp;

                    </p>
                    <p>
                        <label>Daerah :</label>
                        ${actionBean.daerah}

                    </p>
                    <p>
                        <label>Tarikh Pendaftaran :</label>
                        <s:format value="${actionBean.tarikhPendaftaran}" formatPattern="dd/MM/yyyy"/>
                        &nbsp;
                    </p>
                    <p>
                        <label>Amaun Bayaran :</label>
                        RM <s:format formatPattern="###,###,###.00" value="${actionBean.mohonTuntutKos.amaunTuntutan}" />
                    </p>
                    <c:if test="${edit eq true}">
                        <p>
                            <label><font color="red">*</font>Tarikh Mula Lesen :</label>
                                <s:text name="permitSahLaku.tarikhPermitMula" class="datepicker" formatPattern="dd/MM/yyyy"/>
                        </p>
                        <p>
                            <label><font color="red">*</font>Tarikh Tamat Lesen :</label>
                                <s:text name="permitSahLaku.tarikhPermitTamat" class="datepicker" formatPattern="dd/MM/yyyy"/>

                        </p>
                        <br/><br/>

                    </c:if>

                </fieldset>
            </div>
            <div class="subtitle" id="formLesen">
                <p>
                    <label>&nbsp;</label>
                    <%--and actionBean.permohonan.kodUrusan.kod ne 'LMCRG'--%>
                    <c:if test="${actionBean.stageId ne 'rekod_keputusan_mmkn'}">
                        <s:button name="simpanMesyuaratNLesen" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </c:if>
                    <c:if test="${simpanMesyuarat}">
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LMCRG' and actionBean.permohonan.kodUrusan.kod eq 'MLCRG' and actionBean.permohonan.kodUrusan.kod eq 'PCRG' and actionBean.permohonan.kodUrusan.kod eq 'LPJH' and actionBean.permohonan.kodUrusan.kod eq 'PJLB' and actionBean.permohonan.kodUrusan.kod eq 'MPJLB' and actionBean.permohonan.kodUrusan.kod eq 'MPCRG' and actionBean.stageId eq 'rekod_keputusan_mmkn'}">
                            <s:button name="simpanMesyuaratNLesen" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                        </c:if>
                    </c:if>
                </p>

            </div>

        </c:if>
    </c:if>
    <c:if test="${view eq true}">
        <br>
        <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'BMBT' and actionBean.permohonan.kodUrusan.kod ne 'PTBTC' and actionBean.permohonan.kodUrusan.kod ne 'PTBTS'}">
            <c:if test="${(actionBean.stageId eq 'g_charting_keputusan' or actionBean.stageId eq 'semak_charting_keputusan' or actionBean.stageId eq 'semakan_ck' or actionBean.stageId eq 'semak_charting2' or actionBean.stageId eq 'semakan_ck_T' ) and actionBean.permohonan.kodUrusan.kod ne 'PLPS' and actionBean.permohonan.kodUrusan.kod ne 'PTPBP' and actionBean.permohonan.kodUrusan.kod ne 'PPRU' and actionBean.permohonan.kodUrusan.kod ne 'PRMMK'}">
                <c:if test="${button2 eq true}">
                    <div class="subtitle">
                        <fieldset class="aras1">
                            <legend>Charting</legend>
                            <p>&nbsp;</p>
                            <p>
                                <s:button name="" id="btnClick" value="Charting" class="btn" onclick="RunProgram('${actionBean.pguna.idPengguna}','${actionBean.pguna.nama}','${actionBean.pguna.jawatan}','${actionBean.permohonan.idPermohonan}','${actionBean.stageId}')"/>
                            </p>
                        </fieldset>
                    </div>
                </c:if>
            </c:if>
        </c:if>
        <c:if test="${actionBean.stageId eq 'arahan_tugas'}">
            <c:if test="${button2 eq false}">
                <div class="subtitle">
                    <fieldset class="aras1">
                        <legend>Charting</legend>
                        <p>&nbsp;</p>
                        <p>
                            <s:button name="" id="btnClick" value="Semak Charting" class="btn" onclick="RunProgram2('${actionBean.pguna.idPengguna}','${actionBean.pguna.nama}','${actionBean.pguna.jawatan}','${actionBean.permohonan.idPermohonan}','${actionBean.stageId}')"/>
                        </p>
                    </fieldset>
                </div>
            </c:if>
        </c:if>
    </c:if>
</s:form>
