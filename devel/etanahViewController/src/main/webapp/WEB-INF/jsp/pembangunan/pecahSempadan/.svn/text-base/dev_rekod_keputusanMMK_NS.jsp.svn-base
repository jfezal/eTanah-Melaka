<%-- 
    Document   : dev_rekod_keputusanMMK_NS
    Created on : Jan 31, 2012, 11:46:29 AM
    Author     : NageswaraRao
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252" language="java"%>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<link type="text/css" href="${pageContext.request.contextPath}/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ui.datetimepicker.js"></script>
<script type="text/javascript">

    function validateNumber(elmnt, content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }

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


</script>

<s:form beanclass="etanah.view.stripes.pembangunan.RekodKeputusanMMKNSActionBean">

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
            <p>
            <label for="Permohonan">Keputusan Terdahulu :</label>
            <%--${actionBean.permohonan.keputusan.nama}&nbsp;--%>
            ${actionBean.keputusanTerdahulu}&nbsp;
            </p>
        </fieldset >
    </div>

    <c:if test="${edit eq true}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Mesyuarat</legend>
                <c:if test="${bilMesyuarat}">
                    <p>
                    <c:if test="${actionBean.kodNegeri eq '04'}"><label><font color="red">*</font>Bilangan Mesyuarat :</label></c:if>
                            <c:if test="${actionBean.kodNegeri eq '05'}">
                        <label><font color="red">*</font>No. Kertas Mesyuarat :</label></c:if>                        
                            <%--<c:if test="${!tangguh}">
                                ${actionBean.permohonanKertas.tajuk} &nbsp;
                                <s:hidden name="permohonanKertas.tajuk"/>
                            </c:if>
                            <c:if test="${tangguh}">
                                <s:text name="permohonanKertas.tajuk"/>
                            </c:if>--%>
                            <c:choose>
                                <c:when test="${tangguh}">
                                    <%--${actionBean.permohonanKertas.tajuk} &nbsp;
                                    <s:hidden name="permohonanKertas.tajuk"/>--%>
                                    <s:text name="permohonanKertas.tajuk"/>
                                </c:when>
                                <c:otherwise>
                                    <s:text name="permohonanKertas.tajuk"/>
                                </c:otherwise>
                            </c:choose>

                    </p>
                </c:if>

                <c:if test="${displayTarikhMasa}">
                    <p>
                    <label>Tarikh :</label>
                    ${actionBean.permohonanKertas.tarikhSidang}&nbsp;
                    </p>

                </c:if>
                <c:if test="${tarikhMasa}">
                    <p>
                    <c:if test="${actionBean.kodNegeri eq '04'}"><label><font color="red">*</font>Tarikh Bersidang :</label></c:if>
                    <c:if test="${actionBean.kodNegeri eq '05'}"><label><font color="red">*</font>Tarikh Majlis Mesyuarat Kerajaan :</label></c:if>

                    <s:text name="permohonanKertas.tarikhSidang" id="datepicker" class="datepicker" formatPattern="dd/MM/yyyy" />
                    </p>

                    <p>
                    <label><font color="red">*</font>Tarikh Disahkan :</label>
                            <s:text name="permohonanKertas.tarikhSahKeputusan" id="datepicker1" class="datepicker" formatPattern="dd/MM/yyyy"/>
                    </p>

                    <p>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PYTN'}">
                        <label>Hakmilik Lama :</label>
                        <table>
                            <c:forEach items="${actionBean.hpList}" var="line" begin="0">
                                <tr>
                                <td>${line.hakmilik.idHakmilik}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:if>
                    </p>
                    <p>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PYTN'}">
                        <label><font color="red">*</font>Hakmilik Baru :</label>
                                <s:select name="permohonanKertas.kodHakmilikBaru.kod" value="${actionBean.permohonanKertas.kodHakmilikBaru.kod}">
                                    <s:option value="">--Sila Pilih--</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodHakmilikTetapNS}" label="nama" value="kod"/>
                        </s:select>
                    </c:if>
                    </p>                   

                    <p>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
                        <label><font color="red">*</font>Keluasan diluluskan:</label>

                        <%--  <s:hidden name="noPt.idPt" />--%>
                        <%-- <s:text name="noPt.hakmilikPermohonan.id" value="${actionBean.hakmilikPermohonan.id}"/>--%>
                        <s:text name="noPt.luasDilulus"  id="noPt.luasDilulus" />
                        <s:select name="noPt.kodUOM.kod" value="${actionBean.noPt.kodUOM.kod}">
                            <s:option value="">Sila Pilih</s:option>
                            <s:option value="H">Hektar</s:option>
                            <s:option value="M">Meter Persegi</s:option>
                        </s:select>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PLPS'}">
                        <label><font color="red">*</font>Keluasan diluluskan :</label>

                        <%--  <s:hidden name="noPt.idPt" />--%>
                        <%-- <s:text name="noPt.hakmilikPermohonan.id" value="${actionBean.hakmilikPermohonan.id}"/>--%>
                        <s:text name="hakmilikPermohonan.luasDiluluskan"  id="hakmilikPermohonan.luasDiluluskan" />
                        <s:select name="luasLulusUom.kod" value="${actionBean.hakmilikPermohonan.luasLulusUom.kod}">
                            <s:option value="">Sila Pilih</s:option>
                            <s:option value="H">Hektar</s:option>
                            <s:option value="M">Meter Persegi</s:option>
                        </s:select>
                    </c:if>
                    </p>


                </c:if>

                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'RPMMK'}">
                    <p>
                    <label>Keluasan Dipohon :</label>
                    ${actionBean.hakmilikPermohonan.luasTerlibat} ${actionBean.hakmilikPermohonan.kodUnitLuas.nama} &nbsp;
                    </p>
                </c:if>

                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBSK'}">
                    <p>
                    <table>
                        <tr>
                        <td valign="top"><label>Dibatalkan dan Dipinda Kepada :</label></td>
                        <td>${actionBean.hakmilikPermohonan.sekatanKepentinganBaru.sekatan} &nbsp; </td>
                        </tr>
                    </table>
                    </p>
                </c:if>

                <c:if test="${keputusan}">
                    <p>
                    <label><font color="red">*</font>Keputusan :</label>

                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PWGSA'}">
                        LULUS
                        <s:hidden name="permohonan.keputusan" value="L"/>
                    </c:if>

                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSPSS' 
                                  || actionBean.permohonan.kodUrusan.kod eq 'SBMS'
                                  || actionBean.permohonan.kodUrusan.kod eq 'SBPS'}">
                        <s:radio name="kpsn" value="L"/>Lulus&nbsp;
                        <s:radio name="kpsn" value="T"/>Tolak&nbsp;
                        <s:radio name="kpsn" value="TG"/>Tangguh&nbsp;
                        <s:radio name="kpsn" value="KS"/>Lulus Bersyarat&nbsp;
                    </c:if>  

                    <c:if test="${!(actionBean.permohonan.kodUrusan.kod eq 'TSPSS'||
                                  actionBean.permohonan.kodUrusan.kod eq 'SBMS'||
                                  actionBean.permohonan.kodUrusan.kod eq 'SBPS'||
                                  actionBean.permohonan.kodUrusan.kod eq 'PWGSA')}">             
                        <s:radio name="kpsn" value="L"/>Lulus&nbsp;
                        <s:radio name="kpsn" value="T"/>Tolak&nbsp;
                        <s:radio name="kpsn" value="TG"/>Tangguh&nbsp;
                    </c:if>
                    </p>
                </c:if>

                <c:if test="${keputusan}">
                    <p>
                    <label>Ulasan :</label>
                    <s:textarea name="ulasan" value="${actionBean.permohonanRujukanLuar.ulasan}" cols="50" rows="5"/>
                    
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
                    <s:button name="simpanMesyuarat" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </c:if>
                <c:if test="${simpanTangguh}">
                    <s:button name="simpanTangguh" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </c:if>
                <c:if test="${simpanMesyuaratRayuan}">
                    <s:button name="simpanMesyuaratRayuan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
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
                <c:if test="${actionBean.kodNegeri eq '04'}"><label>Bilangan Mesyuarat :</label></c:if>
                <c:if test="${actionBean.kodNegeri eq '05'}"><label>No. Kertas Mesyuarat :</label></c:if>
                <%--s:text name="permohonanKertas.tajuk" id="permohonanKertas.tajuk"/>--%>
                ${actionBean.tajuk}&nbsp;
                </p>

                <p>
                <c:if test="${actionBean.kodNegeri eq '04'}"><label>Tarikh Bersidang :</label></c:if>
                <c:if test="${actionBean.kodNegeri eq '05'}"><label>Tarikh Majlis Mesyuarat Kerajaan :</label></c:if>                  
                <%--<fmt:formatDate value="${actionBean.permohonanKertas.tarikhSidang}" pattern="dd/MM/yyyy "/>&nbsp; --%>
                <fmt:formatDate value="${actionBean.trhSidang}" pattern="dd/MM/yyyy "/>&nbsp; 
                </p>

                <p>
                <label>Tarikh Disahkan :</label>

                <%--  <fmt:formatDate value="${actionBean.permohonanKertas.tarikhSahKeputusan}" pattern="dd/MM/yyyy "/>&nbsp; --%> 
                <fmt:formatDate value="${actionBean.trhSahKeputusan}" pattern="dd/MM/yyyy "/>&nbsp; 
                </p>

                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'RPMMK'}">
                    <p>
                    <label>Keluasan Dipohon :</label>
                    ${actionBean.hakmilikPermohonan.luasTerlibat} ${actionBean.hakmilikPermohonan.kodUnitLuas.nama} &nbsp;
                    </p>
                </c:if>

                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBSK'}">
                    <p>
                    <table>
                        <tr>
                        <td valign="top"><label>Dibatalkan dan Dipinda Kepada :</label></td>
                        <td>${actionBean.hakmilikPermohonan.sekatanKepentinganBaru.sekatan} &nbsp; </td>
                        </tr>
                    </table>
                    </p>
                </c:if>

                <p>
                <label>Keputusan :</label>
                ${actionBean.kpsn} (${actionBean.kpsnnama})&nbsp;
                </p>
                <%--<c:if test="${ulasan}"> BAK--%>
                <c:if test="${keputusan}">
                    <p>
                    <label>Ulasan :</label>
                    ${actionBean.permohonanRujukanLuar.ulasan}&nbsp;
                    </p>
                </c:if>
            </fieldset >
        </div>
        <br>
        <c:if test="${actionBean.stageId eq 'g_charting_keputusan'}">
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

