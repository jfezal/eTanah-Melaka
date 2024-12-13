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
        if($('#kpsnT').val() == 'T'){
            $('#keputusanLulus').hide();
            $('#ulasanLulus').hide();
            $('#lulus1').hide();
            $('#lulus2').hide();
            $('#lulus3').hide();
        }
            
        if($('#kpsnT').val() == 'Y'){
            $('#keputusanLulus').show();
            $('#ulasanLulus').show();
            $('#tempohPegangan').show();
            $('#lulus1').show();
            $('#lulus2').show();
            $('#lulus3').show();
        }
    });
       
    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }
    <c:choose>
        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'LMCRG'}">
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
        
        function removeNonNumeric( strString )
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

        function ReplaceAll(Source,stringToFind,stringToReplace){
            var temp = Source;
            var index = temp.indexOf(stringToFind);
            while(index != -1){
                temp = temp.replace(stringToFind,stringToReplace);
                index = temp.indexOf(stringToFind);

            }
            return temp;
        }

        function RunProgram(strUserID, strNama, strJawatan, strIDPermohonan, strStageID) {

            var strNama2 = ReplaceAll(strNama," ","_");
            var strJawatan2 = ReplaceAll(strJawatan," ","_");
            var strStageID2 = "g_charting_keputusan";
            var objShell = new ActiveXObject("WScript.Shell");
            var sysVar = objShell.Environment("System");
            //objShell.run("C:\Program Files\EpiWeb\ArcMap_ActiveX_Initializer\InititializeArcMap.exe");
            //objShell.run("C:\Program Files\eTanahStrata\InititializeStrata.exe")


            objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama2 + " " +  strJawatan2 + " " + strIDPermohonan + " " + strStageID2);
        }

        function RunProgram2(strUserID, strNama, strJawatan, strIDPermohonan, strStageID) {

            var strNama2 = ReplaceAll(strNama," ","_");
            var strJawatan2 = ReplaceAll(strJawatan," ","_");
            var strStageID2 = "g_charting_semak";
            var objShell = new ActiveXObject("WScript.Shell");
            var sysVar = objShell.Environment("System");
            //objShell.run("C:\Program Files\EpiWeb\ArcMap_ActiveX_Initializer\InititializeArcMap.exe");
            //objShell.run("C:\Program Files\eTanahStrata\InititializeStrata.exe")


            objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama2 + " " +  strJawatan2 + " " + strIDPermohonan + " " + strStageID2);
        }
        function showKeputusan(f) {
            var q = $(f).formSerialize();
            $.post('${pageContext.request.contextPath}/pengambilan/keputusanMesyuarat?addBilBangunan&status=Y',q,
            function(data){
                //                 $.unblockUI();
                $('#page_div').html(data);
                //                
            }, 'html');
        }
        function licenced(val) {
            if(val == 'F')
                $('#lesen').hide();
            else if(val =='T')
                $('#lesen').show();
        }
        
        function keputusanL(){
            $('#keputusanLulus').show();
            $('#ulasanLulus').show();
        }
        
        function keputusanT(){
            $('#keputusanLulus').hide();
            $('#ulasanLulus').hide();
        }
        
        function jenisHakmilikPegangan(val){
            if(val=='HMM'){
                $('#tempohPegangan').show();
            }else if(val=='HSD'){
                $('#tempohPegangan').show();
            }else if(val=='HSM'){
                $('#tempohPegangan').show();
            }
        }
        
        //Added by Aizuddin
        function doFunc(x){
            
            if(x == "L")
            {
                $('#lulus1').show();
                $('#lulus2').show();
                $('#lulus3').show();
            }
            if(x == "T")
            {
                $('#lulus1').hide();
                $('#lulus2').hide();
                $('#lulus3').hide();
            }                
        }
            
        
</script>

<s:form beanclass="etanah.view.stripes.pengambilan.RekodKeputusanMMKActionBean">

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
                        <label><font color="red">*</font>Bilangan Mesyuarat :</label>
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
                <c:if test="${keputusan}">
                    <p>
                        <label><font color="red">*</font>Keputusan :</label>

                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PWGSA'}">
                            LULUS
                            <s:hidden name="permohonan.keputusan" value="L"/>
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PWGSA'}">
                            <c:choose>
                                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'LMCRG'}">
                                    <s:radio name="kpsn" value="L" onclick="licenced('T');"/>Lulus&nbsp;
                                    <s:radio name="kpsn" value="T" onclick="licenced('F')"/>Tolak&nbsp;
                                </c:when>
                                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
                                    <s:radio name="kpsn" value="L" onclick="doFunc(value);"/>Lulus&nbsp;
                                    <s:radio name="kpsn" value="T" onclick="doFunc(value);"/>Tolak&nbsp;
                                </c:when>
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
                                    <s:radio name="kpsn" value="L" onclick="doFunc(value);"/>Lulus&nbsp;
                                    <s:radio name="kpsn" value="T" onclick="doFunc(value);"/>Tolak&nbsp;
                                    <s:radio name="kpsn" value="TG" onclick="doFunc(value);"/>Tangguh&nbsp;
                                </c:when>
                                <c:otherwise>
                                    <s:radio name="kpsn" value="L"/>Lulus&nbsp;
                                    <s:radio name="kpsn" value="T"/>Tolak&nbsp;
                                </c:otherwise>
                            </c:choose>

                        </c:if>

                    </p>
                </c:if>
                <c:if test="${ulasanRAYT}">
                    <p>
                        <label><font color="red">*</font>Keputusan :</label>
                        <input name="kpsnT" id="kpsnT" value="T" type="hidden">
                        <s:radio id="kpsn" name="kpsn" value="L" onclick="keputusanL();"/>Lulus&nbsp;
                        <s:radio id="kpsn" name="kpsn" value="T" onclick="keputusanT()"/>Tolak&nbsp;
                    <div class="subtitle" id="keputusanLulus">
                        <label>Jenis Hakmilik :</label>
                        <s:select name="jenisHakmilik" id="jenisHakmilik" onchange="javaScript:jenisHakmilikPegangan(this.options[selectedIndex].text)">
                            <s:option label="Sila Pilih" value="" />                                                    
                            <s:options-collection collection="${listUtil.senaraiKodHakmilikDIS}" label="nama" value="kod" sort="nama"/>
                        </s:select>
                    </div>
                    </p>
                    <div class="subtitle" id="tempohPegangan">
                        <label>Tempoh Pegangan :</label>
                        <s:text name="hakmilikPermohonan.tempohPajakan" />&nbsp;Tahun
                    </div>
                    <div class="subtitle" id="ulasanLulus">
                        <label>Ulasan :</label>
                        <s:textarea name="permohonan.ulasan" cols="50" rows="5"/>
                    </div>
                </c:if>
                <c:if test="${tarikhMasa}">
                    <p>
                        <label><font color="red" id="lulus1">*</font>Tarikh Bersidang :</label>
                            <s:text name="permohonanKertas.tarikhSidang" id="datepicker" class="datepicker" formatPattern="dd/MM/yyyy" />
                    </p>

                    <p>
                        <label><font color="red" id="lulus2">*</font>Tarikh Disahkan :</label>
                            <s:text name="permohonanKertas.tarikhSahKeputusan" id="datepicker1" class="datepicker" formatPattern="dd/MM/yyyy"/>
                    </p>

                    <p>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPRU'}">
                            <label>Keluasan Dipohon:</label>
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PPRU' and actionBean.permohonan.kodUrusan.kod ne 'PTGSA' and actionBean.permohonan.kodUrusan.kod ne 'PBGSA'}">
                            <label>Keluasan Dipohon :</label>
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PTGSA' and actionBean.permohonan.kodUrusan.kod ne 'PBGSA' and actionBean.permohonan.kodUrusan.kod ne 'PRMMK'}">
                            ${actionBean.hakmilikPermohonan.luasTerlibat} ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PRMMK'}">
                            ${actionBean.trizabPermohonan.luasTerlibat} ${actionBean.trizabPermohonan.kodUnitLuas.nama} 
                        </c:if>

                    </p>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PRMP'}">
                        <p>
                            <label>Keluasan Disyor :</label>
                            ${actionBean.hakmilikPermohonan.luasDiluluskan} ${actionBean.hakmilikPermohonan.luasLulusUom.nama}
                        </p>
                    </c:if>


                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT' and actionBean.kpsn eq 'L' or actionBean.kpsn eq null}"> 

                        <p id="lulus3">
                            <label><font color="red">*</font>Keluasan diluluskan:</label>

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

                    <p>

                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PLPS' or actionBean.permohonan.kodUrusan.kod eq 'PPTR' or actionBean.permohonan.kodUrusan.kod eq 'PTGSA' or actionBean.permohonan.kodUrusan.kod eq 'PRIZ'}">
                            <label><font color="red">*</font>Keluasan diluluskan :</label>

                            <%--  <s:hidden name="noPt.idPt" />--%>
                            <%-- <s:text name="noPt.hakmilikPermohonan.id" value="${actionBean.hakmilikPermohonan.id}"/>--%>
                            <s:text name="hakmilikPermohonan.luasDiluluskan"  id="hakmilikPermohonan.luasDiluluskan" />
                            <s:select name="luasLulusUom.kod" value="${actionBean.hakmilikPermohonan.luasLulusUom.kod}">
                                <s:option value="">Sila Pilih</s:option>
                                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPTR'}">
                                    <s:option value="M">Meter Persegi</s:option>
                                    <s:option value="H">Hektar</s:option>
                                </c:if>
                                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PRIZ' or actionBean.permohonan.kodUrusan.kod eq 'PRMMK'}">
                                    <s:option value="M">Meter Persegi</s:option>
                                    <s:option value="H">Hektar</s:option>
                                </c:if>
                                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PLPS'}">
                                    <s:option value="H">Hektar</s:option>
                                    <s:option value="M">Meter Persegi</s:option>
                                </c:if>
                                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTGSA'}">
                                    <s:option value="H">Hektar</s:option>
                                    <s:option value="M">Meter Persegi</s:option>
                                </c:if>
                            </s:select>
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPRU'}">
                            <label><font color="red">*</font>Keluasan diluluskan (Isipadu) :</label>

                            <%--  <s:hidden name="noPt.idPt" />--%>
                            <%-- <s:text name="noPt.hakmilikPermohonan.id" value="${actionBean.hakmilikPermohonan.id}"/>--%>
                            <s:text name="hakmilikPermohonan.luasDiluluskan"  id="hakmilikPermohonan.luasDiluluskan" />
                            <s:select name="luasLulusUom.kod" value="${actionBean.hakmilikPermohonan.luasLulusUom.kod}">
                                <s:option value="">Sila Pilih</s:option>
                                <s:option value="M">Meter Persegi</s:option>
                            </s:select>
                        </c:if>

                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PRMMK'}">
                            <label>Keluasan diluluskan :</label>
                            <s:text name="hakmilikPermohonan.luasDiluluskan"  id="hakmilikPermohonan.luasDiluluskan" formatPattern="#,###,##0"/>
                            ${actionBean.hakmilikPermohonan.luasLulusUom.nama}
                        </c:if>
                    </p>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LMCRG' or actionBean.permohonan.kodUrusan.kod eq 'PJLB'}">
                        <p>
                            <label>Bayaran (RM) :</label>
                            <c:if test="${!view}">
                                <s:text name="amnt" size="20" onkeyup="validateNumber(this,this.value);"/> &nbsp; Setahun
                            </c:if>

                            <c:if test="${view}">
                                ${actionBean.permohonanTuntutanKos.amaunTuntutan}&nbsp; Setahun
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
                        <p>
                            <label>Luas Diluluskan:</label>
                            <c:if test="${!view}">
                                <s:text name="hakmilikPermohonan.luasDiluluskan" size="15"/>
                            </c:if>
                            <c:if test="${view}">
                                ${actionBean.hakmilikPermohonan.luasDiluluskan}&nbsp;
                            </c:if>
                            <c:if test="${!view}">                    
                                <s:select name="kodU" id="koduom">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:option value="M">Meter Persegi</s:option>
                                    <s:option value="H">Hektar</s:option>
                                </s:select>                       
                            </c:if>
                            <c:if test="${view}">
                                ${actionBean.hakmilikPermohonan.luasLulusUom.nama}&nbsp;
                            </c:if>
                        </p>
                    </c:if>
                </c:if>
                <c:if test="${ulasan}">
                    <p>
                        <label>Ulasan :</label>
                        <s:textarea name="permohonanRujukanLuar.ulasan" cols="50" rows="5"/>
                    </p>
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
                        <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'LMCRG'}">
                            <s:button name="simpanMesyuarat" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
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
                    <label>Bilangan Mesyuarat :</label>
                    ${actionBean.permohonanKertas.nomborRujukan}&nbsp;
                </p>
                <%--<p>
                    <label>Tarikh :</label>
                    ${actionBean.permohonanKertas.tarikhSidang}&nbsp;
                </p>--%>
                <p>
                    <label>Keputusan :</label>
                    ${actionBean.kpsn} (${actionBean.kpsnnama})&nbsp;
                </p>
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
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PPRU' and actionBean.permohonan.kodUrusan.kod ne 'PTGSA' and actionBean.permohonan.kodUrusan.kod ne 'PBGSA'}">
                        <label>Keluasan Dipohon :</label>
                    </c:if>                        
                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PTGSA' and actionBean.permohonan.kodUrusan.kod ne 'PBGSA' and actionBean.permohonan.kodUrusan.kod ne 'PRMMK'}">    
                        ${actionBean.hakmilikPermohonan.luasTerlibat} ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}
                    </c:if>  
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PRMMK'}">
                        ${actionBean.trizabPermohonan.luasTerlibat} ${actionBean.trizabPermohonan.kodUnitLuas.nama} 
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
                        <label>Keluasan diluluskan:</label>

                        <s:hidden name="noPt.idPt" />

                        ${actionBean.hakmilikPermohonan.luasDiluluskan}
                        ${actionBean.hakmilikPermohonan.luasLulusUom.nama}
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PLPS' or actionBean.permohonan.kodUrusan.kod eq 'LPSP' or actionBean.permohonan.kodUrusan.kod eq 'PPTR' or actionBean.permohonan.kodUrusan.kod eq 'PRIZ'}">

                        <c:if test="${actionBean.kpsn ne 'T'}">
                            <label>Keluasan diluluskan:</label>
                            ${actionBean.hakmilikPermohonan.luasDiluluskan}
                            ${actionBean.hakmilikPermohonan.luasLulusUom.nama}
                        </c:if>

                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPRU' or actionBean.permohonan.kodUrusan.kod eq 'PRMMK'}">
                        <label>Keluasan diluluskan :</label>
                        ${actionBean.hakmilikPermohonan.luasDiluluskan}
                        ${actionBean.hakmilikPermohonan.luasLulusUom.nama}
                    </c:if>

                </p>

                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LMCRG' or actionBean.permohonan.kodUrusan.kod eq 'PJLB'}">
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
    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LMCRG' or actionBean.permohonan.kodUrusan.kod eq 'PJLB'}">
        <div class="subtitle" id="lesen">
            <fieldset class="aras1" align="center">
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LMCRG'}">
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
                <c:if test="${actionBean.stageId ne 'rekod_keputusan_mmkn' and actionBean.permohonan.kodUrusan.kod ne 'LMCRG'}">
                    <s:button name="simpanMesyuaratNLesen" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </c:if>                    
                <c:if test="${simpanMesyuarat}">           
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LMCRG' and actionBean.stageId eq 'rekod_keputusan_mmkn'}">
                        <s:button name="simpanMesyuaratNLesen" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </c:if>
                </c:if>
            </p>

        </div>

    </c:if>
    <c:if test="${view eq true}">
        <br>
        <c:if test="${actionBean.stageId eq 'g_charting_keputusan' or actionBean.stageId eq 'semak_charting_keputusan' or actionBean.stageId eq 'semakan_ck' or actionBean.stageId eq 'semak_charting2'}">
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
