<%--
    Document   : pampasanPHLL
    Created on : 10-Sep-2012, 17:48:50
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
        $("#nilaiTanahJpph").attr("disabled",false);
    }

    function kerosakanTanahTiada(){
        $("#keteranganKerosakanTanah").attr("disabled",true);
        $("#keteranganKerosakanTanah").val("");
        $("#kosKerosakanTanah").attr("disabled",true);
        $("#kosKerosakanTanah").val("");
        $("#nilaiTanahJpph").attr("disabled",true);
        $("#nilaiTanahJpph").val("");
    }

    function kerosakanBangunanAda(){
        $("#keteranganKerosakanBangunan").attr("disabled",false);
        $("#kosKerosakanBangunan").attr("disabled",false);
        $("#nilaiBngnJpph").attr("disabled",false);
    }

    function kerosakanBangunanTiada(){
        $("#keteranganKerosakanBangunan").attr("disabled",true);
        $("#keteranganKerosakanBangunan").val("");
        $("#kosKerosakanBangunan").attr("disabled",true);
        $("#kosKerosakanBangunan").val("");
        $("#nilaiBngnJpph").attr("disabled",true);
        $("#nilaiBngnJpph").val("");
    }

    function kerosakanPokokAda(){
        $("#keteranganKerosakanPokok").attr("disabled",false);
        $("#kosKerosakanPokok").attr("disabled",false);
        $("#nilaiPokokJpph").attr("disabled",false);
    }

    function kerosakanPokokTiada(){
        $("#keteranganKerosakanPokok").attr("disabled",true);
        $("#keteranganKerosakanPokok").val("");
        $("#kosKerosakanPokok").attr("disabled",true);
        $("#kosKerosakanPokok").val("");
        $("#nilaiPokokJpph").attr("disabled",true);
        $("#nilaiPokokJpph").val("");
    }

    function kerosakanInfrastrukturAda(){
        $("#keteranganKerosakanInfra").attr("disabled",false);
        $("#kosKerosakanInfra").attr("disabled",false);
        $("#nilaiInfraJpph").attr("disabled",false);
    }

    function kerosakanInfrastrukturTiada(){
        $("#keteranganKerosakanInfra").attr("disabled",true);
        $("#keteranganKerosakanInfra").val("");
        $("#kosKerosakanInfra").attr("disabled",true);
        $("#kosKerosakanInfra").val("");
        $("#nilaiInfraJpph").attr("disabled",true);
        $("#nilaiInfraJpph").val("");
    }

    function kerosakanLainLainAda(){
        $("#keteranganKerosakanLain").attr("disabled",false);
        $("#kosKerosakanLain").attr("disabled",false);
        $("#nilaiLainJpph").attr("disabled",false);
    }

    function kerosakanLainLainTiada(){
        $("#keteranganKerosakanLain").attr("disabled",true);
        $("#keteranganKerosakanLain").val("");
        $("#kosKerosakanLain").attr("disabled",true);
        $("#kosKerosakanLain").val("");
        $("#nilaiLainJpph").attr("disabled",true);
        $("#nilaiLainJpph").val("");
    }

    function kecacatanTanahAda(){
        $("#keteranganKecacatanTanah").attr("disabled",false);
        $("#kosKecacatanTanah").attr("disabled",false);
        $("#nilaiCatatJpph").attr("disabled",false);
    }

    function kecacatanTanahTiada(){
        $("#keteranganKecacatanTanah").attr("disabled",true);
        $("#keteranganKecacatanTanah").val("");
        $("#kosKecacatanTanah").attr("disabled",true);
        $("#kosKecacatanTanah").val("");
        $("#nilaiCatatJpph").attr("disabled",true);
        $("#nilaiCatatJpph").val("");
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

<s:form beanclass="etanah.view.stripes.pengambilan.PampasanPHLLActionBean" name="form1">
    <s:messages />
    <s:errors/>
    <div id="hakmilik_details">
        <%--${actionBean.permohonan.permohonanSebelum.idPermohonan}--%>
        <fieldset class="aras1"><br/>
            <div align="center">
                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="5" cellpadding="0" cellspacing="0"
                               requestURI="/pengambilan/pampasanPhlla" id="line">
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column property="hakmilik.noHakmilik" title="No Hakmilik"/>
                    <display:column title="No Lot/No PT" >${line.hakmilik.lot.nama} ${line.hakmilik.noLot}</display:column>
                    <display:column property="hakmilik.daerah.nama" title="Daerah" class="Daerah"/>
                    <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                    <display:column title="Tuan Tanah/Pihak Berkepentingan" >
                        <c:set value="1" var="count"/>
                        <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                            <c:if test="${senarai.aktif eq 'Y'}">
                                <table border="0">
                                    <tr>
                                        <td><c:out value="${count}"/>)</td>

                                        <td>
                                            <s:link beanclass="etanah.view.stripes.pengambilan.PampasanPHLLActionBean"
                                                    event="pihakDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                                                <s:param name="idPihak" value="${senarai.pihak.idPihak}"/>
                                                <s:param name="idHakmilik" value="${line.hakmilik.idHakmilik}"/>
                                                <s:param name="simpanLaporan" value="${actionBean.simpanLaporan}"/>
                                                <font style="text-transform:uppercase;"><c:out value="${senarai.pihak.nama}"/></font>
                                            </s:link>
                                        </td>

                                        <td>(<c:out value="${senarai.syerPembilang}/${senarai.syerPenyebut}"/>)</td>
                                    </tr>
                                </table>
                                    <c:set value="${count + 1}" var="count"/>
                            </c:if>
                            
                        </c:forEach>
                    </display:column>
                </display:table>
            </div><br /><br />
        </fieldset>
        <c:if test="${showDetails}">
            <fieldset class="aras1"><br/>
                <s:hidden name="simpanLaporan" />
                <legend>
                    <%--Laporan pemulihan tanah bagi tuan tanah :---%>
                    <br />
                    <font style="text-transform:uppercase; color: black">
                        <c:out value="${actionBean.permohonanPihak.pihak.nama}"/><br />
                        No Hakmilik : <c:out value="${actionBean.permohonanPihak.hakmilik.noHakmilik}"/><br />
                        No Lot/Pt : <c:out value="${actionBean.permohonanPihak.hakmilik.lot.nama}"/> <c:out value="${actionBean.permohonanPihak.hakmilik.noLot}"/>
                    </font>
                </legend><br />
                <div class="content" align="center">
                    <table class="tablecloth">
                        <tr>
                            <th width="20%">Jenis Kerosakan</th>
                            <th width="10%">Ada</th>
                            <th width="10%">Tiada</th>
                            <th width="10%">Penilaian JPPH (RM)</th>
                            <th width="10%">Award (RM)</th>
                            <th width="40%">Catatan</th>
                        </tr>
                        <tr>
                            <td>
                                <b>1. Tanah</b>
                            </td>
                            <td>
                                <s:radio name="adaKerosakanTanah" value="Y" id="adaKerosakanTanah" onclick="kerosakanTanahAda();"/>&nbsp;Ada
                            </td>
                            <td>
                                <s:radio name="adaKerosakanTanah" value="T" id="adaKerosakanTanah" onclick="kerosakanTanahTiada();"/>&nbsp;Tiada
                            </td>
                            <td><s:text name="nilaiTanahJpph" id="nilaiTanahJpph" disabled="true" formatPattern="#,##0.00" onkeyup="validateNumber(this,this.value);"/></td>
                            <td><s:text name="kosKerosakanTanah" id="kosKerosakanTanah" disabled="true" formatPattern="#,##0.00" onkeyup="validateNumber(this,this.value);"/></td>
                            <td><s:textarea name="keteranganKerosakanTanah" id="keteranganKerosakanTanah" disabled="true" rows="3" cols="90" class="normal_text"/></td>
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
                            <td><s:text name="nilaiBngnJpph" id="nilaiBngnJpph" disabled="true" formatPattern="#,##0.00" onkeyup="validateNumber(this,this.value);"/></td>
                            <td><s:text name="kosKerosakanBangunan" id="kosKerosakanBangunan" disabled="true" formatPattern="#,##0.00" onkeyup="validateNumber(this,this.value);"/></td>
                            <td><s:textarea name="keteranganKerosakanBangunan" id="keteranganKerosakanBangunan" disabled="true" rows="3" cols="90" class="normal_text"/></td>
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
                            <td><s:text name="nilaiPokokJpph" id="nilaiPokokJpph" disabled="true" formatPattern="#,##0.00" onkeyup="validateNumber(this,this.value);"/></td>
                            <td><s:text name="kosKerosakanPokok" id="kosKerosakanPokok" disabled="true" formatPattern="#,##0.00" onkeyup="validateNumber(this,this.value);"/></td>
                            <td><s:textarea name="keteranganKerosakanPokok" id="keteranganKerosakanPokok" disabled="true" rows="3" cols="90" class="normal_text"/></td>
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
                            <td><s:text name="nilaiInfraJpph" id="nilaiInfraJpph" disabled="true" formatPattern="#,##0.00" onkeyup="validateNumber(this,this.value);"/></td>
                            <td><s:text name="kosKerosakanInfra" id="kosKerosakanInfra" disabled="true" formatPattern="#,##0.00" onkeyup="validateNumber(this,this.value);"/></td>
                            <td><s:textarea name="keteranganKerosakanInfra" id="keteranganKerosakanInfra" disabled="true" rows="3" cols="90" class="normal_text"/></td>
                        </tr>
                        <tr>
                            <td>
                                <b>5. Kecacatan Tanah</b>
                            </td>
                            <td>
                                <s:radio name="adaKecacatanTanah" value="Y" id="adaKecacatanTanah" onclick="kecacatanTanahAda();"/>&nbsp;Ada
                            </td>
                            <td>
                                <s:radio name="adaKecacatanTanah" value="T" id="adaKecacatanTanah" onclick="kecacatanTanahTiada();"/>&nbsp;Tiada
                            </td>
                            <td><s:text name="nilaiCatatJpph" id="nilaiCatatJpph" disabled="true" formatPattern="#,##0.00" onkeyup="validateNumber(this,this.value);"/></td>
                            <td><s:text name="kosKecacatanTanah" id="kosKecacatanTanah" disabled="true" formatPattern="#,##0.00" onkeyup="validateNumber(this,this.value);"/></td>
                            <td><s:textarea name="keteranganKecacatanTanah" id="keteranganKecacatanTanah" disabled="true" rows="3" cols="90" class="normal_text"/></td>
                        </tr>
                        <tr>
                            <td>
                                <b>6. Kerosakan Lain-Lain</b>
                            </td>
                            <td>
                                <s:radio name="adaKerosakanLain" value="Y" id="adaKerosakanLain" onclick="kerosakanLainLainAda();"/>&nbsp;Ada
                            </td>
                            <td>
                                <s:radio name="adaKerosakanLain" value="T" id="adaKerosakanLain" onclick="kerosakanLainLainTiada();"/>&nbsp;Tiada
                            </td>
                            <td><s:text name="nilaiLainJpph" id="nilaiLainJpph" disabled="true" formatPattern="#,##0.00" onkeyup="validateNumber(this,this.value);"/></td>
                            <td><s:text name="kosKerosakanLain" id="kosKerosakanLain" disabled="true" formatPattern="#,##0.00" onkeyup="validateNumber(this,this.value);"/></td>
                            <td><s:textarea name="keteranganKerosakanLain" id="keteranganKerosakanLain" disabled="true" rows="3" cols="90" class="normal_text"/></td>
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
                    </table>
                </div>

                <br />

                <div align="center">
                    <tr>
                        <td>
                            <c:if test="${actionBean.simpanLaporan eq true}">
                                <s:hidden name="idPihak" value="${actionBean.idPihak}"/>
                                <s:hidden name="idHakmilik" value="${actionBean.idHakmilik}"/>
                                <s:button class="btn" id="save" onclick="doSubmit(this.form, this.name, 'page_div');" name="simpan" value="Simpan"/>
                            </c:if>
                        </td>
                    </tr>
                </div>
            </fieldset>
        </c:if>
    </div>
</s:form>

