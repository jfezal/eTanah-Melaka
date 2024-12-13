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
    <c:choose>
        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PPTR'}">
                if($('#testPPTR').val() == 'L'){
                    $('#lulus4').show() ;
                }else{
                    $('#lulus4').hide() ;
                }
        </c:when>
            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PPRU'}">                    
                    var kep = '${actionBean.permohonan.keputusan.kod}';                    
                    if (kep == 'L') {
                        $('#tsidang').show();
                        $('#tsah').show();
                        $('#kpohon').show();
                        $('#klulus').show();
                        $('#lulus4').hide();
                    } else if (kep == 'T') {
                        alert("x1");
                        $('#tsidang').hide();
                        $('#tsah').hide();
                        $('#kpohon').hide();
                        $('#klulus').hide();
                        $('#lulus4').hide();
                    } else if (kep == 'TG') {
                        $('#tsidang').hide();
                        $('#tsah').hide();
                        $('#kpohon').hide();
                        $('#klulus').hide();
                        $('#lulus4').hide();                    
                    } else {
                        $('#tsidang').show();
                        $('#tsah').show();
                        $('#kpohon').show();
                        $('#klulus').show();
                        $('#lulus4').hide();
                    }
        </c:when>
        <c:otherwise>
        </c:otherwise>
    </c:choose>        
        });
       
        function validateNumber(elmnt,content) {
            //if it is character, then remove it..
            if (isNaN(content)) {
                elmnt.value = removeNonNumeric(content);
                return;
            }
        }
    <c:choose>
        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'LMCRG' or actionBean.permohonan.kodUrusan.kod eq 'PCRG' or actionBean.permohonan.kodUrusan.kod eq 'MPCRG'}">
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
            $.post('${pageContext.request.contextPath}/pelupusan/mesyuaratpenyelarasan?addBilBangunan&status=Y',q,
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
            if(x == "TG")
            {
                $('#lulus1').hide();
                $('#lulus2').hide();
                $('#lulus3').hide();
            }
        }
        
        function doFuncPPRU(x){
            
            if(x == "L")
            {
                $('#tsidang').show();
                $('#tsah').show();
                $('#kpohon').show();
                $('#klulus').show();
                $('#lulus4').hide();
            }
            if(x == "T")
            {
                $('#tsidang').hide();
                $('#tsah').hide();
                $('#kpohon').hide();
                $('#klulus').hide();
                $('#lulus4').hide();
            }
            if(x == "TG")
            {
                $('#tsidang').hide();
                $('#tsah').hide();
                $('#kpohon').hide();
                $('#klulus').hide();
                $('#lulus4').hide();
            }
        }
        
        function doFuncPPTR(x){
            
            if(x == "L")
            {
                $('#lulus1').show();
                $('#lulus2').show();
                $('#lulus4').show();
            }
            if(x == "T")
            {
                $('#lulus1').hide();
                $('#lulus2').hide();
                $('#lulus4').hide();
            }
        }
            
        
</script>

<s:form beanclass="etanah.view.stripes.pelupusan.RekodKeputusanMesyuaratPenyelarasanActionBean">

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
    <c:if test="${keputusan eq true}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Mesyuarat</legend>
                
                <c:if test="${keputusan}">
                    <p>
                        <label><font color="red">*</font>Keputusan :</label>

                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPTR'}">
                            <s:radio name="kpsn" id="testPPTR" value="L" onclick="doFuncPPTR(value);"/>Lulus&nbsp;
                            <s:radio name="kpsn" id="testPPTR" value="T" onclick="doFuncPPTR(value);"/>Tolak&nbsp;
                        </c:if>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBGSA'}">
                            <s:radio name="kpsn" id="testPPTR" value="L" onclick="doFuncPPTR(value);"/>Lulus&nbsp;
                            <s:radio name="kpsn" id="testPPTR" value="T" onclick="doFuncPPTR(value);"/>Tolak&nbsp;
                        </c:if>
                    </p>
                    
                    <p>
                        <label><font color="red">*</font>Bilangan Mesyuarat :</label>
                            <s:text name="permohonanKertas.nomborRujukan"/>
                            <%--<s:text name="permohonanRujukanLuar.noSidang" onkeyup="validateNumber(this,this.value);" maxlength="3"/>--%>
                            <%--<s:hidden name="permohonanRujukanLuar.idRujukan"/>--%>
                    </p>
                    <p>
                            <label><font color="red" id="lulus1">*</font>Tarikh Bersidang :</label>
                                <s:text name="permohonanKertas.tarikhSidang" id="tarikhSidang" class="datepicker" formatPattern="dd/MM/yyyy" />
                        </p>

                    <p>
                            <label><font color="red" id="lulus2">*</font>Tarikh Disahkan :</label>
                                <s:text name="permohonanKertas.tarikhSahKeputusan" id="datepicker1" class="datepicker" formatPattern="dd/MM/yyyy"/>
                    </p>
                    <br>
                    <p>
                        <label>&nbsp;</label>
                        <s:button name="simpanMesyuarat" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </p>
                </c:if>
     </c:if>          
                
    <c:if test="${view eq true}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Mesyuarat</legend>
                
                <p>
                    <label>Keputusan :</label>
                    ${actionBean.kpsn} (${actionBean.kpsnnama})&nbsp;
                </p>
                
            </fieldset>
        </div>

    </c:if>
    
</s:form>
