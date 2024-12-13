<%-- 
    Document   : maklumat_risalat_mmkn
    Created on : Mar 25, 2011, 5:26:47 PM
    Author     : muhammad.amin
--%>


<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>

<style type="text/css">

    #uLabel {
        width: 15em;
        float: left;
        text-align: left;
        margin-right: 0px;
        display: block;
        color:#003194;
        font-weight: bold;
        font-family:Tahoma;
        font-size: 13px;
        margin-left: -3px;
    }
</style>

<script type="text/javascript">


    $(document).ready(function() {
        var val = $('#premium').val();
        if (val != '') {
            convert(val, 'premium');
        }

    });

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

    function reload(val) {
        doBlockUI();
        var url = '${pageContext.request.contextPath}/consent/kertas_ringkas_mmkn?selectHakmilik&idHakmilik=' + val;
        $.ajax({
            type: "GET",
            url: url,
            dataType: 'html',
            error: function(xhr, ajaxOptions, thrownError) {
                alert("error=" + xhr.responseText);
                doUnBlockUI();
            },
            success: function(data) {
                $('#page_div').html(data);
                doUnBlockUI();
            }
        });
    }

</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.stripes.consent.KertasRingkasMmknActionBean">
    <s:messages/>
    <s:errors/>
    <s:hidden name="hakmilikPermohonan.hakmilik.idHakmilik"/>
    <c:if test="${actionBean.fasaPermohonan ne null && actionBean.mohonUrusanRayuan eq null}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend></legend>
                <br/><br/>
                <p align="center"><font size="4"> Sila masukkan maklumat rayuan pengurangan bayaran terlebih dahulu.</font></p>
                <br/><br/><br/><br/>
            </fieldset>
        </div>
    </c:if>
    <c:if test="${(actionBean.fasaPermohonan ne null && actionBean.mohonUrusanRayuan ne null) || (actionBean.fasaPermohonan eq null && actionBean.mohonUrusanRayuan eq null)}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Kertas Ringkas</legend>
                <table width="100%" border="0">
                    <tr><td width="2%"></td>&nbsp;<td colspan="2">Tajuk </td></tr>
                    <tr><td></td><td colspan="2"><s:textarea name="tajuk" rows="4" style="width:97%;"/> </td></tr>
                    <tr><td></td>&nbsp;</tr>
                    <tr><td></td><td>No. Ruj PTG</td><td>
                            ${actionBean.permohonan.idPermohonan}
                            <c:if test="${actionBean.permohonan.projek ne null && actionBean.permohonan.projek.noRujukanProjek ne null}">
                                - ${actionBean.permohonan.projek.noRujukanProjek}
                            </c:if>
                        </td>
                    </tr>
                    <tr><td></td><td style="vertical-align: baseline" style="width:12%;">Pemohon</td><td>
                            <c:set value="1" var="count"/>
                            <c:forEach items="${actionBean.permohonan.senaraiPemohon}" var="senarai">       
                                <c:out value="${senarai.pihak.nama}"/> (<c:out value="${senarai.pihak.jenisPengenalan.nama}"/>  <c:out value="${senarai.pihak.noPengenalan}"/>)
                                <c:if test="${fn:length(actionBean.permohonan.senaraiPemohon) > 1}"><br/></c:if>    
                                <c:set value="${count + 1}" var="count"/>
                            </c:forEach>&nbsp;</td></tr>
                    <tr><td></td><td style="vertical-align: baseline">Warganegara</td><td>
                            <c:forEach items="${actionBean.permohonan.senaraiPemohon}" var="senarai">       
                                <c:out value="${senarai.pihak.wargaNegara.nama}"/>
                                <c:if test="${fn:length(actionBean.permohonan.senaraiPemohon) > 1}"><br/></c:if>    
                            </c:forEach>&nbsp;</td></tr>

                    <tr><td></td><td colspan="2" style="text-align:center;">
                            <c:if test="${fn:length(actionBean.permohonan.senaraiHakmilik) > 1}">
                                <br/> 
                                <font size="2" color="red">
                                <b>Permohonan Melibatkan Banyak Hakmilik</b>
                                </font>
                            </c:if>
                            <br/> Hakmilik : 
                            <s:select name="idHakmilik" onchange="reload(this.value);" id="hakmilik">
                                <c:forEach items="${actionBean.permohonan.senaraiHakmilik}" var="item" varStatus="line">
                                    <s:option value="${item.hakmilik.idHakmilik}" style="width:400px">
                                        ${item.hakmilik.idHakmilik} ( ${item.hakmilik.daerah.nama} - ${item.hakmilik.bandarPekanMukim.nama} )
                                    </s:option>
                                </c:forEach>
                            </s:select>
                            <br/><br/>
                        </td></tr>

                    <tr><td></td><td>No. Hakmilik</td><td>
                            ${actionBean.hakmilikPermohonan.hakmilik.kodHakmilik.kod} ${actionBean.hakmilikPermohonan.hakmilik.noHakmilik} ${actionBean.hakmilikPermohonan.hakmilik.lot.nama} ${actionBean.hakmilikPermohonan.hakmilik.noLot}
                        </td>
                    </tr>
                    <tr><td></td><td>Keluasan</td><td>
                            <s:text name="hakmilikPermohonan.luasTerlibat" size="20"/>  
                            <s:select name="unitLuas">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodUOMByLuas}" label="nama" value="kod"/>
                            </s:select>
                        </td>
                    </tr>
                    <tr><td></td><td>Bandar/Pekan/Mukim</td><td>
                            ${actionBean.hakmilikPermohonan.hakmilik.bandarPekanMukim.nama}
                        </td>
                    </tr>
                    <c:if test="${(actionBean.permohonan.kodUrusan.kod eq 'PMMK1')}">
                        <tr><td></td><td >Alamat Hartanah</td><td> <s:text name="hakmilikPermohonan.keteranganInfra" size="100"/></td></tr>
                        <tr><td></td><td>Lokasi Projek</td><td> <s:text name="hakmilikPermohonan.lokasi"  size="100"/></td></tr>
                        <tr><td></td><td>Harga Hartanah (RM)</td><td> <s:text name="hakmilikPermohonan.keteranganKadarDaftar"  size="31" id="premium"
                                formatPattern="#,###.00" onkeyup="validateNumber(this,this.value);"/></td></tr>
                            </c:if>
                    <tr><td></td><td>&nbsp;</td></tr>
                    <tr><td></td><td colspan="2">  <c:if test="${(actionBean.permohonan.kodUrusan.kod eq 'PMMK1')}">Keputusan MMKN terdahulu</c:if> <c:if test="${(actionBean.permohonan.kodUrusan.kod eq 'PMMK2')}">Asas-asas pertimbangan</c:if></td></tr>
                    <tr><td></td><td colspan="2"><s:textarea name="keputusanDulu" rows="4" style="width:97%;" class="normal_text"/></td></tr>
                    <tr><td></td><td>&nbsp;</td></tr>
                    <tr><td></td><td colspan="2">Perakuan PTG Melaka</td></tr>
                    <tr><td></td><td colspan="2"><s:textarea name="perakuanPtg" rows="4" style="width:97%;" class="normal_text"/></td></tr>
                </table>
                <p>
                    <label>&nbsp;</label>
                    <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </p>
            </fieldset>
        </div>
    </c:if>
</s:form>
