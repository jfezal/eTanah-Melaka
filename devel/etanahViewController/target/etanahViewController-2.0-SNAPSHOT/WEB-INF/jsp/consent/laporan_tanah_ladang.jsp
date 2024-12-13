<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

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

    function reload(val) {
        doBlockUI();
        var url = '${pageContext.request.contextPath}/consent/laporan_tanah_ladang?selectHakmilik&idHakmilik=' + val;
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

    function reloadEdit(val) {
        doBlockUI();
        var url = '${pageContext.request.contextPath}/consent/laporan_tanah_ladang?selectHakmilikEdit&idHakmilik=' + val;
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

    function doSetDokumenHakmilik() {
        var idDokumen = document.getElementById("hmImej").options[document.getElementById("hmImej").selectedIndex].value;
        if (idDokumen != '') {
            var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }
    }

    function openFrame(type) {
        doBlockUI();
        var idHakmilik = $('#idHakmilik').val();
        var idLaporan = $('#idLaporan').val();
        if (idLaporan != '') {
            window.open("${pageContext.request.contextPath}/consent/laporan_tanah_ladang?openFrame&idHakmilik="
                + idHakmilik + "&type=" + type, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
        }
        else {
            alert("Sila simpan maklumat terlebih dahulu");
            doUnBlockUI();
        }
    }

    function refreshV2(type) {
        var url = '${pageContext.request.contextPath}/consent/laporan_tanah_ladang?refreshpage&type=' + type;
        $.get(url,
        function(data) {
            $('#page_div').html(data);
        }, 'html');
        doUnBlockUI();
    }

</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.stripes.consent.LaporanTanahLadangActionBean">
    <s:messages/>
    <s:hidden name="permohonan.idPermohonan" />
    <s:hidden name="laporanTanah.idLaporan" id="idLaporan"/>
    <s:hidden name="hakmilikPermohonan.id"/>
    <s:hidden name="hakmilikPermohonan.hakmilik.idHakmilik" id="idHakmilik"/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Laporan Tanah</legend>
            <br/>
            <P>1. PERIHAL PERMOHONAN</P>
            <p>
                <label>PTG terima permohonan :</label>
                <c:if test="${actionBean.permohonan.infoAudit.tarikhMasuk ne null}"><s:format formatPattern="dd/MM/yyyy" value="${actionBean.permohonan.infoAudit.tarikhMasuk}"/>&nbsp;</c:if>
                <c:if test="${actionBean.permohonan.infoAudit.tarikhMasuk eq null}"> Tiada Data </c:if>

            </p>
            <p>
                <label>Jenis Permohonan :</label>
                Pindahmilik Tanah
            </p>
            <p>
                <label>Tujuan :</label>
                Kebenaran Pindahmilik tanah ladang
            </p>
            <p>
                <label>Luas tapak dipohon :</label>
                <c:set value="1" var="count"/>
                <c:forEach items="${actionBean.permohonan.senaraiHakmilik}" var="senarai">   
                    <c:out value="${senarai.hakmilik.lot.nama}"/>&nbsp;<c:out value="${senarai.hakmilik.noLot}"/> :&nbsp;<c:out value="${senarai.hakmilik.luas}"/>&nbsp;<c:out value="${senarai.hakmilik.kodUnitLuas.nama}"/>
                    <c:set value="${count + 1}" var="count"/>

                </c:forEach>
            </p>
            <p>
                <label>Seksyen :</label>
                <c:set value="1" var="count"/>
                <c:forEach items="${actionBean.permohonan.senaraiHakmilik}" var="senarai">   
                    <c:if test="${senarai.hakmilik.seksyen ne null}">
                        <c:out value="${senarai.hakmilik.seksyen.nama}"/>
                    </c:if>
                    <c:if test="${senarai.hakmilik.seksyen eq null}">
                        -
                    </c:if>
                    <c:set value="${count + 1}" var="count"/>
                </c:forEach>
            </p>
            <br/>
            <P>2. BUTIR-BUTIR PEMOHON</P>
            <p>
                <label>Pemohon :</label>
                <c:set value="1" var="count"/>
                <c:forEach items="${actionBean.permohonan.senaraiPemohon}" var="senarai">       
                    <c:out value="${senarai.pihak.nama}"/>        
                    <c:set value="${count + 1}" var="count"/>
                </c:forEach>&nbsp;
            </p>
            <p>
                <label>No. Pengenalan :</label>
                <c:set value="1" var="count"/>
                <c:forEach items="${actionBean.permohonan.senaraiPemohon}" var="senarai">       
                    <c:out value="${senarai.pihak.noPengenalan}"/>        
                    <c:set value="${count + 1}" var="count"/>
                </c:forEach>&nbsp;
            </p>
            <p>
                <label>Warganegara/Negara Syarikat :</label>
                <c:set value="1" var="count"/>
                <c:forEach items="${actionBean.permohonan.senaraiPemohon}" var="senarai">       
                    <c:out value="${senarai.pihak.wargaNegara.nama}"/>        
                    <c:set value="${count + 1}" var="count"/>
                </c:forEach>&nbsp;
            </p>
            <p>
                <label>Keturunan/Jenis Syarikat :</label>
                <c:set value="1" var="count"/>
                <c:forEach items="${actionBean.permohonan.senaraiPemohon}" var="senarai">       
                    <c:out value="${senarai.pihak.bangsa.nama}"/>        
                    <c:set value="${count + 1}" var="count"/>
                </c:forEach>&nbsp;
            </p>
            <p>
                <label>Alamat :</label>
                <c:set value="1" var="count"/>
                <c:forEach items="${actionBean.permohonan.senaraiPemohon}" var="senarai">       
                    <c:out value="${senarai.alamatSurat.alamatSurat1}"/>
                    <c:out value="${senarai.alamatSurat.alamatSurat2}"/>
                    <c:out value="${senarai.alamatSurat.alamatSurat3}"/>
                    <c:out value="${senarai.alamatSurat.alamatSurat4}"/>
                    <c:out value="${senarai.alamatSurat.poskodSurat}"/>
                    <c:out value="${senarai.alamatSurat.negeriSurat.nama}"/> 
                    <c:set value="${count + 1}" var="count"/>
                </c:forEach>
            </p>
            <br/>
            <p>3. PERIHAL TANAH</p>
            <div align="center">
                <c:if test="${fn:length(actionBean.permohonan.senaraiHakmilik) > 1}">
                    <p>
                        <font size="2" color="red">
                            <b>Permohonan Melibatkan Banyak Hakmilik</b>
                        </font>
                    </p>
                </c:if>
            </div>
            <p align="center">
                Hakmilik :
                <c:if test="${edit}">
                    <s:select name="idHakmilik" onchange="reloadEdit(this.value);" id="hakmilik">
                        <c:forEach items="${actionBean.permohonan.senaraiHakmilik}" var="item" varStatus="line">
                            <s:option value="${item.hakmilik.idHakmilik}" style="width:400px">
                                ${item.hakmilik.idHakmilik} ( ${item.hakmilik.daerah.nama} - ${item.hakmilik.bandarPekanMukim.nama} )
                            </s:option>
                        </c:forEach>
                    </s:select>
                </c:if>
                <c:if test="${!edit}">
                    <s:select name="idHakmilik" onchange="reload(this.value);" id="hakmilik">
                        <c:forEach items="${actionBean.permohonan.senaraiHakmilik}" var="item" varStatus="line">
                            <s:option value="${item.hakmilik.idHakmilik}" style="width:400px">
                                ${item.hakmilik.idHakmilik} ( ${item.hakmilik.daerah.nama} - ${item.hakmilik.bandarPekanMukim.nama} )
                            </s:option>
                        </c:forEach>
                    </s:select>
                </c:if>
            </p>
            <br/>
            <c:if test="${edit}">
                <p>
                    <label>Status Tanah :</label>
                    <s:select name="pemilikan" id="">
                        <s:option value="H">Hakmilik</s:option>
                        <%-- <s:options-collection collection="${list.senaraiKodPemilikan}" label="nama" value="kod"/>--%>
                    </s:select>
                </p>
                <p>
                    <label>Kategori Tanah :</label>
                    <s:select name="jenisPenggunaanTanah" id="">
                        <s:option value="1">Pertanian</s:option>
                        <%--<s:options-collection collection="${list.senaraiKodKategoriTanah}" label="nama" value="kod"/>--%>
                    </s:select>
                </p>
                <p>
                    <label>DUN :</label>
                    <s:select name="dun" id="">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodDUN}" label="nama" value="kod"/>
                    </s:select>
                </p>
                <p>
                    <label>Pihak Berkuasa Tempatan :</label>
                    <s:select name="pbt" id="">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodPBT}" label="nama" value="kod"/>
                    </s:select>

                </p>
                <p>
                    <label>Rancangan Tempatan :</label>
                    <s:text name="laporanTanah.rancanganKerajaan" size="40"/>
                </p>
                <p>
                    <label>Zon :</label>
                    <s:text name="laporanTanah.keteranganTanahBertumpu" size="40"/>
                </p>
                <p>
                    <label>Landmark terhampir :</label>
                    <s:text name="laporanTanah.mercuTanda" size="40"/>
                </p>
                <p>
                    <label>Bandar/Pekan terhampir :</label>
                    <s:text name="hakmilikPermohonan.jarakDariNama" size="40"/>
                </p>
                <p>
                    <label>Keadaan Tanah :</label>
                    <s:textarea name="laporanTanah.keadaanTanah" cols="95" rows="4" class="normal_text"/>
                </p>
            </c:if>
            <c:if test="${!edit}">
                <p>
                    <label>Status Tanah :</label>
                    <c:if test="${actionBean.hakmilikPermohonan.kodMilik.nama ne null}">  <font style="text-transform:uppercase;">${actionBean.hakmilikPermohonan.kodMilik.nama}&nbsp;</font> </c:if>
                    <c:if test="${actionBean.hakmilikPermohonan.kodMilik.nama eq null}">Tiada Data </c:if>
                    </p>
                    <p>
                        <label>Kategori Tanah :</label>
                    <c:if test="${actionBean.hakmilikPermohonan.jenisPenggunaanTanah.nama ne null}">  <font style="text-transform:uppercase;">${actionBean.hakmilikPermohonan.jenisPenggunaanTanah.nama}&nbsp;</font> </c:if>
                    <c:if test="${actionBean.hakmilikPermohonan.jenisPenggunaanTanah.nama eq null}">Tiada Data </c:if>
                    </p>
                    <p>
                        <label>DUN :</label>
                    <c:if test="${actionBean.hakmilikPermohonan.kodDUN.nama ne null}">  <font style="text-transform:uppercase;">${actionBean.hakmilikPermohonan.kodDUN.nama }&nbsp;</font> </c:if>
                    <c:if test="${actionBean.hakmilikPermohonan.kodDUN.nama eq null}">Tiada Data </c:if>
                    </p>
                    <p>
                        <label>Pihak Berkuasa Tempatan :</label>
                    <c:if test="${actionBean.hakmilikPermohonan.kodPbt.nama ne null}">  <font style="text-transform:uppercase;">${actionBean.hakmilikPermohonan.kodPbt.nama}&nbsp;</font> </c:if>
                    <c:if test="${actionBean.hakmilikPermohonan.kodPbt.nama eq null}">Tiada Data </c:if>
                    </p>
                    <p>
                        <label>Rancangan Tempatan :</label>
                    <c:if test="${actionBean.laporanTanah.rancanganKerajaan ne null}">  <font style="text-transform:uppercase;">${actionBean.laporanTanah.rancanganKerajaan}&nbsp;</font> </c:if>
                    <c:if test="${actionBean.laporanTanah.rancanganKerajaan eq null}">Tiada Data </c:if>
                    </p>
                    <p>
                        <label>Zon :</label>
                    <c:if test="${actionBean.laporanTanah.keteranganTanahBertumpu ne null}">  <font style="text-transform:uppercase;">${actionBean.laporanTanah.keteranganTanahBertumpu }&nbsp;</font> </c:if>
                    <c:if test="${actionBean.laporanTanah.keteranganTanahBertumpu eq null}">Tiada Data </c:if>
                    </p>
                    <p>
                        <label>Landmark terhampir :</label>
                    <c:if test="${actionBean.laporanTanah.mercuTanda ne null}">  <font style="text-transform:uppercase;">${actionBean.laporanTanah.mercuTanda}&nbsp;</font> </c:if>
                    <c:if test="${actionBean.laporanTanah.mercuTanda eq null}">Tiada Data </c:if>
                    </p>
                    <p>
                        <label>Bandar/Pekan terhampir :</label>
                    <c:if test="${actionBean.hakmilikPermohonan.jarakDariNama ne null}">  <font style="text-transform:uppercase;">${actionBean.hakmilikPermohonan.jarakDariNama}&nbsp;</font> </c:if>
                    <c:if test="${actionBean.hakmilikPermohonan.jarakDariNama eq null}">Tiada Data </c:if>
                    </p>
                    <p>
                        <label>Keadaan Tanah :</label>
                    <c:if test="${actionBean.laporanTanah.keadaanTanah ne null}">  <font style="text-transform:uppercase;">${actionBean.laporanTanah.keadaanTanah}&nbsp;</font> </c:if>
                    <c:if test="${actionBean.laporanTanah.keadaanTanah eq null}">Tiada Data </c:if>
                    </p>
            </c:if>
            <p>4. BUTIR-BUTIR TANAH</p>
            <p>
                <label>No. Lot :</label>
                ${actionBean.hakmilikPermohonan.hakmilik.lot.nama}  ${actionBean.hakmilikPermohonan.hakmilik.noLot}&nbsp;
            </p>
            <p>
                <label>Hakmilik :</label>
                ${actionBean.hakmilikPermohonan.hakmilik.kodHakmilik.kod}  ${actionBean.hakmilikPermohonan.hakmilik.noHakmilik}&nbsp;
            </p>
            <p>
                <label>Mukim :</label>
                ${actionBean.hakmilikPermohonan.hakmilik.bandarPekanMukim.nama}&nbsp;
            </p>
            <p>
                <label>Daerah :</label>
                ${actionBean.hakmilikPermohonan.hakmilik.daerah.nama}&nbsp;
            </p>
            <p>
                <label>Luas :</label>
                ${actionBean.hakmilikPermohonan.hakmilik.luas}      ${actionBean.hakmilikPermohonan.hakmilik.kodUnitLuas.nama}  &nbsp;  
            </p>
            <c:if test="${edit}">
                <p>5. LOT-LOT BERSEMPADANAN</p>
                <p>
                    <label>Lot-lot bersempadanan :</label>
                    <s:textarea name="laporanTanah.catatanSempadanBarat" cols="95" rows="4" class="normal_text"/>
                </p>
                <p>6. MAKLUMAT LAIN</p>
                <p>
                    <label>Maklumat lain :</label>
                    <s:textarea name="hakmilikPermohonan.keteranganInfra" cols="95" rows="4" class="normal_text"/>
                </p>

                <p>7. GAMBAR DAN PELAN</p>

                <p>
                    <label>Gambar dan pelan :</label>
                    <s:select name="" style="width:300px;" id="hmImej" onchange="doSetDokumenHakmilik();">
                        <s:option value="">Sila pilih imej untuk dipaparkan</s:option>
                        <s:options-collection collection="${actionBean.hakmilikImejLaporanList}" label="catatan" value="dokumen.idDokumen"/>
                    </s:select>&nbsp;&nbsp;
                    <a onclick="openFrame('pTanah');" onmouseover="this.style.cursor = 'pointer';"><font color="red" size="2" style="font-style:normal"><b><u>KEMASKINI</u></b></font> </a>
                </p>
                <c:if test="${actionBean.laporanTanah.idLaporan eq null}">
                    <p>
                        <label>&nbsp;</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        (Sila simpan maklumat terlebih dahulu)
                    </p>
                </c:if>
                <p>8. SYOR/PERAKUAN</p>
                <p>
                    <label>Syor/Perakuan :</label>
                    <s:textarea name="laporanTanah.sebabTidakBolehBerimilik" cols="95" rows="4" class="normal_text"/>
                </p>
                <br/>
                <c:if test="${actionBean.fasaPermohonan.keputusan ne null}">
                    <p>
                        <label>Ulasan PTG Melaka :</label>
                        <c:if test="${actionBean.fasaPermohonan.keputusan.kod eq 'P'}">Diperakukan Lulus</c:if>
                        <c:if test="${actionBean.fasaPermohonan.keputusan.kod ne 'P'}">Tidak Diperakukan</c:if>
                        </p>
                        <br/>
                </c:if>
                <p>
                    <label>&nbsp;</label>
                    <s:button name="simpanLaporanTanah" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </p>
            </c:if>
            <c:if test="${!edit}">
                <p>5. LOT-LOT BERSEMPADANAN</p>
                <p>
                    <label>Lot-lot bersempadanan :</label>
                    <c:if test="${actionBean.laporanTanah.catatanSempadanBarat ne null}">  <font style="text-transform:uppercase;">${actionBean.laporanTanah.catatanSempadanBarat }&nbsp;</font> </c:if>
                    <c:if test="${actionBean.laporanTanah.catatanSempadanBarat eq null}">Tiada Data </c:if>
                    </p>
                    <br/>
                    <p>6. MAKLUMAT LAIN</p>
                    <p>
                        <label>Maklumat lain :</label>
                    <c:if test="${actionBean.hakmilikPermohonan.keteranganInfra ne null}">  <font style="text-transform:uppercase;">${actionBean.hakmilikPermohonan.keteranganInfra }&nbsp;</font> </c:if>
                    <c:if test="${actionBean.hakmilikPermohonan.keteranganInfra eq null}">Tiada Data </c:if>
                    </p>
                    <br/>

                    <p>7. GAMBAR DAN PELAN</p>
                    <p>
                        <label>Gambar dan pelan :</label>
                    <s:select name="" style="width:300px;" id="hmImej" onchange="doSetDokumenHakmilik();">
                        <s:option value="">Sila pilih imej untuk dipaparkan</s:option>
                        <s:options-collection collection="${actionBean.hakmilikImejLaporanList}" label="catatan" value="dokumen.idDokumen"/>
                    </s:select>
                </p>
                <br/>
                <p>8. SYOR/PERAKUAN</p>
                <p>
                    <label>Syor/Perakuan :</label>
                    <c:if test="${actionBean.laporanTanah.sebabTidakBolehBerimilik ne null}">  <font style="text-transform:uppercase;">${actionBean.laporanTanah.sebabTidakBolehBerimilik }&nbsp;</font> </c:if>
                    <c:if test="${actionBean.laporanTanah.sebabTidakBolehBerimilik eq null}">Tiada Data </c:if>
                    </p>
                    <br/>
                    <br/>
                <c:if test="${actionBean.fasaPermohonan.keputusan ne null}">
                    <p>
                        <label>Ulasan PTG Melaka :</label>
                        <c:if test="${actionBean.fasaPermohonan.keputusan.kod eq 'P'}">Diperakukan Lulus</c:if>
                        <c:if test="${actionBean.fasaPermohonan.keputusan.kod ne 'P'}">Tidak Diperakukan</c:if>
                        </p>
                        <br/>
                </c:if>

            </c:if>
        </fieldset>
    </div>
</s:form>



