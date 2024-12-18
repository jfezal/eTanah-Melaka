<%-- 
    Document   : NilaianTanahAduanNS
    Created on : 11-May-2011, 14:43:46
    Author     : nordiyana
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript">
    $(document).ready( function(){
        <%--alert('hi');--%>
        if($("#adaKerosakanTanah").val() == "Y"){
           kerosakanTanahAda();
        }
        if($("#adaKerosakanBangunan").val() == "Y"){
           kerosakanBangunanAda();
        }
        if($("#adaKerosakanPokok").val() == "Y"){
           kerosakanPokokAda();
        }
        if($("#adaKerosakanInfra").val() == "Y"){
           kerosakanInfrastrukturAda();
        }
        if($("#adaKerosakanLain").val() == "Y"){
           kerosakanLainLainAda();
        }
        if($("#adaKecacatanTanah").val() == "Y"){
           kecacatanTanahAda();
        }
    });

    function kerosakanTanahAda(){
        $("#keteranganKerosakanTanah").attr("disabled",false);
        $("#kosKerosakanTanah").attr("disabled",false);
    }

    function kerosakanTanahTiada(){
        $("#keteranganKerosakanTanah").attr("disabled",true);
        $("#keteranganKerosakanTanah").val("");
        $("#kosKerosakanTanah").attr("disabled",true);
        $("#kosKerosakanTanah").val("");
    }

    function kerosakanBangunanAda(){
        $("#keteranganKerosakanBangunan").attr("disabled",false);
        $("#kosKerosakanBangunan").attr("disabled",false);
    }

    function kerosakanBangunanTiada(){
        $("#keteranganKerosakanBangunan").attr("disabled",true);
        $("#keteranganKerosakanBangunan").val("");
        $("#kosKerosakanBangunan").attr("disabled",true);
        $("#kosKerosakanBangunan").val("");
    }

    function kerosakanPokokAda(){
        $("#keteranganKerosakanPokok").attr("disabled",false);
        $("#kosKerosakanPokok").attr("disabled",false);
    }

    function kerosakanPokokTiada(){
        $("#keteranganKerosakanPokok").attr("disabled",true);
        $("#keteranganKerosakanPokok").val("");
        $("#kosKerosakanPokok").attr("disabled",true);
        $("#kosKerosakanPokok").val("");
    }

    function kerosakanInfrastrukturAda(){
        $("#keteranganKerosakanInfra").attr("disabled",false);
        $("#kosKerosakanInfra").attr("disabled",false);
    }

    function kerosakanInfrastrukturTiada(){
        $("#keteranganKerosakanInfra").attr("disabled",true);
        $("#keteranganKerosakanInfra").val("");
        $("#kosKerosakanInfra").attr("disabled",true);
        $("#kosKerosakanInfra").val("");
    }

    function kerosakanLainLainAda(){
        $("#keteranganKerosakanLain").attr("disabled",false);
        $("#kosKerosakanLain").attr("disabled",false);
    }

    function kerosakanLainLainTiada(){
        $("#keteranganKerosakanLain").attr("disabled",true);
        $("#keteranganKerosakanLain").val("");
        $("#kosKerosakanLain").attr("disabled",true);
        $("#kosKerosakanLain").val("");
    }

    function kecacatanTanahAda(){
        $("#keteranganKecacatanTanah").attr("disabled",false);
        $("#kosKecacatanTanah").attr("disabled",false);
    }

    function kecacatanTanahTiada(){
        $("#keteranganKecacatanTanah").attr("disabled",true);
        $("#keteranganKecacatanTanah").val("");
        $("#kosKecacatanTanah").attr("disabled",true);
        $("#kosKecacatanTanah").val("");
    }

    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }

    }

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

    function ajaxLink(link, update) {
        $.get(link, function(data) {
            $(update).html(data);
            $(update).show();
        });
        return false;
    }
</script>

<s:form beanclass="etanah.view.stripes.pengambilan.NilaianTanahAduanActionBean" name="form1">
    <s:messages />
    <s:errors/>
    <%--------------------${fn:length(actionBean.hakmilikPermohonanList)}--%>
    <div  id="hakmilik_details">
        <fieldset class="aras1"><br/>
            <div align="center">
                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="5" cellpadding="0" cellspacing="0"
                               requestURI="/pengambilan/nilaianTanahAduan" id="line">
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column property="hakmilik.noHakmilik" title="No Hakmilik"/>
                    <%--<display:column property="hakmilik.noLot" title="No Lot" />--%>
                    <display:column title="No Lot/No PT" >${line.hakmilik.lot.nama}  ${line.hakmilik.noLot}</display:column>
                    <display:column property="hakmilik.daerah.nama" title="Daerah" class="Daerah"/>
                    <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                    <display:column title="Tuan Tanah" >
                        <c:forEach items="${actionBean.pp}" var="senarai">
                               <s:link beanclass="etanah.view.stripes.pengambilan.NilaianTanahAduanActionBean"
                                    event="pihakDetails2" onclick="return ajaxLink(this,'#hakmilik_details');" >
                                <s:param name="idPihak" value="${senarai.pihak.idPihak}"/>
                                <s:param name="idHakmilik" value="${line.hakmilik.idHakmilik}"/>
                                ${senarai.pihak.nama}
                            </s:link><br/>
                        </c:forEach>
                        <%--<c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                            <s:link beanclass="etanah.view.stripes.pengambilan.LaporanPemulihanTanahSblmActionBean"
                                    event="pihakDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                                <s:param name="idPihak" value="${senarai.pihak.idPihak}"/>
                                <s:param name="idHakmilik" value="${line.hakmilik.idHakmilik}"/>
                                ${senarai.pihak.nama}
                            </s:link>
                            <br />
                        </c:forEach>--%>
                    </display:column><%--
                    <display:column title="Syer">
                        <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                            <c:out value="${senarai.syerPembilang}/${senarai.syerPenyebut}"/><br/>
                        </c:forEach>
                    </display:column>--%>
                    <%--<display:column title="Kemaskini">
                        <c:forEach items="" var="senarai">
                            <div align="center">
                                <br/>
                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';"/>
                                onclick="editTuntutanKos('${senarai.idKos}','${senarai.pihak.idPermohonanPihak}');"/>
                            </div>
                        </c:forEach>
                    </display:column>--%>
                </display:table>
            </div><br /><br />
        </fieldset>
        <c:if test="${showDetails}">
            <fieldset class="aras1"><br/>
                
            <legend>Nilaian Kerosakan Tanah</legend><br />
            Nama : ${actionBean.permohonanPihak.pihak.nama}
            <div class="content" align="center">
                <table class="tablecloth">
                    <tr>
                        <th width="20%">Jenis Kerosakan</th><th width="10%">Ada</th><th width="10%">Tiada</th><th width="40%">Ulasan Jika Ada</th>
                        <th width="10%">Kos Kerosakan (RM)</th>
                    </tr>
                    <tr>
                        <td>
                            <b>1. Kerosakan Tanah</b>
                        </td>
                        <td>
                            <s:radio name="adaKerosakanTanah" value="Y" id="adaKerosakanTanah" onclick="kerosakanTanahAda();"/>&nbsp;Ada
                        </td>
                        <td>
                            <s:radio name="adaKerosakanTanah" value="T" id="adaKerosakanTanah" onclick="kerosakanTanahTiada();"/>&nbsp;Tiada
                        </td>
                        <td><s:textarea name="keteranganKerosakanTanah" id="keteranganKerosakanTanah" disabled="true" rows="3" cols="90"/></td>
                        <td><s:text name="kosKerosakanTanah" id="kosKerosakanTanah" disabled="true" formatPattern="#,##0.00" onkeyup="validateNumber(this,this.value);"/></td>
                    </tr>
                    <tr>
                        <td>
                            <b>2. Kerosakan Bangunan</b>
                        </td>
                        <td>
                            <s:radio name="adaKerosakanBangunan" value="Y" id="adaKerosakanBangunan" onclick="kerosakanBangunanAda();"/>&nbsp;Ada
                        </td>
                        <td>
                            <s:radio name="adaKerosakanBangunan" value="T" id="adaKerosakanBangunan" onclick="kerosakanBangunanTiada();"/>&nbsp;Tiada
                        </td>
                        <td><s:textarea name="keteranganKerosakanBangunan" id="keteranganKerosakanBangunan" disabled="true" rows="3" cols="90"/></td>
                        <td><s:text name="kosKerosakanBangunan" id="kosKerosakanBangunan" disabled="true" formatPattern="#,##0.00" onkeyup="validateNumber(this,this.value);"/></td>
                    </tr>
                    <tr>
                        <td>
                            <b>3. Kerosakan Pokok</b>
                        </td>
                        <td>
                            <s:radio name="adaKerosakanPokok" value="Y" id="adaKerosakanPokok" onclick="kerosakanPokokAda();"/>&nbsp;Ada
                        </td>
                        <td>
                            <s:radio name="adaKerosakanPokok" value="T" id="adaKerosakanPokok" onclick="kerosakanPokokTiada();"/>&nbsp;Tiada
                        </td>
                        <td><s:textarea name="keteranganKerosakanPokok" id="keteranganKerosakanPokok" disabled="true" rows="3" cols="90"/></td>
                        <td><s:text name="kosKerosakanPokok" id="kosKerosakanPokok" disabled="true" formatPattern="#,##0.00" onkeyup="validateNumber(this,this.value);"/></td>
                    </tr>
                    <tr>
                        <td>
                            <b>4. Kerosakan Infrastruktur</b>
                        </td>
                        <td>
                            <s:radio name="adaKerosakanInfra" value="Y" id="adaKerosakanInfra" onclick="kerosakanInfrastrukturAda();"/>&nbsp;Ada
                        </td>

                        <td>
                            <s:radio name="adaKerosakanInfra" value="T" id="adaKerosakanInfra" onclick="kerosakanInfrastrukturTiada();"/>&nbsp;Tiada
                        </td>
                        <td><s:textarea name="keteranganKerosakanInfra" id="keteranganKerosakanInfra" disabled="true" rows="3" cols="90"/></td>
                        <td><s:text name="kosKerosakanInfra" id="kosKerosakanInfra" disabled="true" formatPattern="#,##0.00" onkeyup="validateNumber(this,this.value);"/></td>
                    </tr>
                    <tr>
                        <td>
                            <b>5. Kerosakan Lain-Lain</b>
                        </td>
                        <td>
                            <s:radio name="adaKerosakanLain" value="Y" id="adaKerosakanLain" onclick="kerosakanLainLainAda();"/>&nbsp;Ada
                        </td>
                        <td>
                            <s:radio name="adaKerosakanLain" value="T" id="adaKerosakanLain" onclick="kerosakanLainLainTiada();"/>&nbsp;Tiada
                        </td>
                        <td><s:textarea name="keteranganKerosakanLain" id="keteranganKerosakanLain" disabled="true" rows="3" cols="90"/></td>
                        <td><s:text name="kosKerosakanLain" id="kosKerosakanLain" disabled="true" formatPattern="#,##0.00" onkeyup="validateNumber(this,this.value);"/></td>
                    </tr>
                    <tr>
                        <td>
                            <b>6. Kecacatan Tanah</b>
                        </td>
                        <td>
                            <s:radio name="adaKecacatanTanah" value="Y" id="adaKecacatanTanah" onclick="kecacatanTanahAda();"/>&nbsp;Ada
                        </td>
                        <td>
                            <s:radio name="adaKecacatanTanah" value="T" id="adaKecacatanTanah" onclick="kecacatanTanahTiada();"/>&nbsp;Tiada
                        </td>
                        <td><s:textarea name="keteranganKecacatanTanah" id="keteranganKecacatanTanah" disabled="true" rows="3" cols="90"/></td>
                        <td><s:text name="kosKecacatanTanah" id="kosKecacatanTanah" disabled="true" formatPattern="#,##0.00" onkeyup="validateNumber(this,this.value);"/></td>
                    </tr>
                    <tr>
                        <td ></td>
                        <td ></td>
                        <td ></td>
                        <td><div align="right"><b>Jumlah Keseluruhan (RM) : </b></div></td>
                        <td>
                            <s:text name="a" value="${(actionBean.kosKerosakanTanah)+(actionBean.kosKerosakanBangunan)+(actionBean.kosKerosakanPokok)+(actionBean.kosKerosakanInfra)+(actionBean.kosKerosakanLain)+(actionBean.kosKecacatanTanah)}" formatPattern="#,##0.00" readonly="true"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <b>Ulasan</b>
                        </td>
                        <td>
                            
                        </td>
                        <td>
                            
                        </td>
                        <td><s:textarea name="ulasan" id="ulasan" rows="3" cols="90"/></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>
                            <b>Syor KPPT</b>
                        </td>
                        <td>
                            
                        </td>
                        <td>
                            
                        </td>
                        <td><s:textarea name="syor" id="syor" rows="3" cols="90"/></td>
                        <td></td>
                    </tr>
                </table>
            </div>

            <br />

            <div align="center">
                <tr>
                    <td>
                        <s:hidden name="idPihak" value="${actionBean.idPihak}"/>
                        <s:hidden name="idHakmilik" value="${actionBean.idHakmilik}"/>
                        <%--<s:button class="btn" id="save" onclick="doSubmit(this.form, this.name, 'page_div');" name="simpan" value="Simpan"/>--%>
                    </td>
                </tr>
            </div>
            </fieldset>
        </c:if>
    </div>
</s:form>


