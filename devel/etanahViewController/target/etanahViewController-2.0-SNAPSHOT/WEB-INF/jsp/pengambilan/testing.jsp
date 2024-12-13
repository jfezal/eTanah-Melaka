<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">

    $(document).ready(function() {

        var v = '${actionBean.laporanTanah.kecerunanTanah.kod}';
        if(v == 'RT'){
            $('#tinggi').hide();
            $('#cerun').hide();
            $('#dalam').hide();
        }

        else if(v == 'BK'){
            $('#tinggi').hide();
            $('#cerun').show();
            $('#dalam').hide();
        }

        else if(v == 'TG'){
            $('#tinggi').show();
            $('#cerun').hide();
            $('#dalam').hide();
        }

        else if(v == 'RD'){
            $('#tinggi').hide();
            $('#cerun').show();
            $('#dalam').hide();
        }

        else if(text == 'CR'){
            $('#tinggi').hide();
            $('#cerun').show();
            $('#dalam').hide();
        }

        else if(text == 'PY'){
            $('#tinggi').hide();
            $('#cerun').hide();
            $('#dalam').show();
        }


        $('#jenisRizab').hide();
        $('#noWarta').hide();

        $('#nyataRancangan').hide();
        $('#tanahMilik').hide();
        $('#tujuan').hide();

        $('#catatanTanahMilik').hide();
        $('#catatanPBT').hide();

    });

    function changeRancangan(value){

        if(value == "ada")
            $('#nyataRancangan').show();

        else
            $('#nyataRancangan').hide();
    }

    function changePBT(value){

        if(value == "dalam")
            $('#catatanPBT').show();

        else
            $('#catatanPBT').hide();
    }

    function changeTanahMilik(value){

        if(value == "ya"){
            $('#tanahMilik').show();
            $('#catatanTanahMilik').show();
        }

        else{
            $('#tanahMilik').hide();
            $('#catatanTanahMilik').hide();
        }
    }

    function changeKategoriTanah(value){

        if(value == "kerajaan"){
            $('#jenisRizab').hide();
            $('#noWarta').hide();
        }
        else if(value == "milik"){
            $('#jenisRizab').hide();
            $('#noWarta').hide();
        }
        else if(value == "rizab"){
            $('#jenisRizab').show();
            $('#noWarta').show();
        }
    }

    function changeTujuan(value){

        if(value == "Lain-lain")
            $('#tujuan').show();
        else
            $('#tujuan').hide();
    }

    function changeKeadaanTanah(text){

        if(text == "Rata"){
            $('#tinggi').hide();
            $('#cerun').hide();
            $('#dalam').hide();
        }

        else if(text == "Berbukit"){
            $('#tinggi').hide();
            $('#cerun').show();
            $('#dalam').hide();
        }

        else if(text == "Tinggi"){
            $('#tinggi').show();
            $('#cerun').hide();
            $('#dalam').hide();
        }

        else if(text == "Rendah"){
            $('#tinggi').hide();
            $('#cerun').show();
            $('#dalam').hide();
        }

        else if(text == "Curam"){
            $('#tinggi').hide();
            $('#cerun').show();
            $('#dalam').hide();
        }

        else if(text == "Berpaya"){
            $('#tinggi').hide();
            $('#cerun').hide();
            $('#dalam').show();
        }
    }

</script>

<s:form beanclass="etanah.view.stripes.common.BorangLaporanTanahActionBean">
    <s:messages/>
    <s:hidden name="permohonan.idPermohonan" />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Permohonan</legend>
            <p>
                <label>Tujuan Permohonan :</label>
                ${actionBean.permohonan.kodUrusan.nama}&nbsp;
            </p>

            <p>
                <label>Tarikh Permohonan Diterima :</label>
                <s:format formatPattern="dd/MM/yyyy" value="${actionBean.permohonan.infoAudit.tarikhMasuk}" />&nbsp;

            </p>
            <c:if test="${edit}">
                <p>
                    <label>Tarikh Permohonan Didaftar :</label>
                    <s:text name="fromDate" id="" class="datepicker"/>
                </p>
            </c:if>
            <c:if test="${!edit}">
                <p>
                    <label>Tarikh Permohonan Didaftar :</label>
                    &nbsp;
                </p>
            </c:if>

        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Perihal Tanah</legend>
            <c:if test="${edit}">
                <p>
                    <label>Keluasan :</label>
                    <s:text name="permohonan.senaraiHakmilik[0].hakmilik.luas" />
                    <s:select name="permohonan.senaraiHakmilik[0].hakmilik.kodUnitLuas.kod">
                        <s:option>--Sila Pilih--</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodUOM}" label="nama" value="kod" />
                    </s:select>
                </p>
                <p>
                    <label>Status Tanah :</label>
                    ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.rizab.nama}&nbsp;
                </p>
                <p id="noWarta">
                    <label>Nombor Warta Kerajaan :</label>
                    <s:text name="permohonan.id" />
                </p>
                <p>
                    <label>Gambar Lokasi Tanah :</label>
                    <s:radio name="rdGambar" value="Ada"/>&nbsp;Ada
                    <s:radio name="rdGambar" value="Tiada"/>&nbsp;Tiada
                </p>
                <p>
                    <label>Jenis Tanah :</label>

                    ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodTanah.nama}&nbsp;
                <p>
                    <label>Lokaliti :</label>
                    <s:text name="permohonan.id" size="40"/>
                </p>
                <br>
                <p>
                    <label>Kawasan PBT :</label>
                    <s:radio name="rdPBT" value="dalam" onchange="javaScript:changePBT(this.value)"/>&nbsp;Dalam
                    <s:radio name="rdPBT" value="luar" onchange="javaScript:changePBT(this.value)"/>&nbsp;Luar
                </p>
                <p id="catatanPBT">
                    <label>Catatan :</label>
                    <s:textarea name="permohonan.id" />
                </p>
                <br>
                <div class="content" align="center">
                    Bersempadan
                    <table class="tablecloth">
                        <tr>
                            <th>Bersempadan</th><th>Nama</th><th>Jarak (KM)</th>
                        </tr>
                        <tr>
                            <td>
                                Jalan Raya
                            </td>
                            <td>
                                <s:text name="laporanTanah.namaSempadanJalanraya" />
                            </td>
                            <td>
                                <s:text name="laporanTanah.jarakSempadanJalanraya" />
                            </td>

                        </tr>
                        <tr>
                            <td>
                                Landasan Keretapi
                            </td>
                            <td>
                                <s:text name="laporanTanah.namaSempadanKeretapi" />
                            </td>
                            <td>
                                <s:text name="laporanTanah.jarakSempadanKeretapi" />
                            </td>

                        </tr>
                        <tr>
                            <td>
                                Laut
                            </td>
                            <td>
                                <s:text name="laporanTanah.namaSempadanLaut" />
                            </td>
                            <td>
                                <s:text name="laporanTanah.jarakSempadanLaut" />
                            </td>
                        </tr><tr>
                            <td>
                                Sungai
                            </td>
                            <td>
                                <s:text name="laporanTanah.namaSempadanSungai" />
                            </td>
                            <td>
                                <s:text name="laporanTanah.jarakSempadanSungai" />
                            </td>
                        </tr>
                    </table>
                </div>
                <p>
                    <label>Jalan Masuk :</label>
                    <s:radio name="laporanTanah.adaJalanMasuk" value="Y"/>&nbsp;Ada
                    <s:radio name="laporanTanah.adaJalanMasuk" value="T"/>&nbsp;Tiada
                </p>
                <p>
                    <label>Catatan :</label>
                    <s:textarea name="laporanTanah.catatanJalanMasuk"/>
                </p>
            </c:if>
            <c:if test="${!edit}">
                <p>
                    <label>Keluasan :</label>
                    <fmt:formatNumber  pattern="#,##0.00" value="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.luas}"/>
                    ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodUnitLuas.nama}&nbsp;
                </p>
                <p>
                    <label>Status Tanah :</label>
                    ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.rizab.nama}&nbsp;
                </p>
                <p id="noWarta">
                    <label>Nombor Warta Kerajaan :</label>
                    &nbsp;
                </p>
                <p>
                    <label>Gambar Lokasi Tanah :</label>
                    &nbsp;
                </p>
                <p>
                    <label>Jenis Tanah :</label>
                    ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodTanah.nama}&nbsp;
                </p>
                <p>
                    <label>Lokaliti :</label>
                    &nbsp;
                </p>
                <br>
                <p>
                    <label>Kawasan PBT :</label>
                    &nbsp;
                </p>
                <p id="catatanPBT">
                    <label>Catatan :</label>
                    <%--<s:textarea name="permohonan.id" />--%>
                </p>
                <br>
                <div class="content" align="center">
                    Bersempadan
                    <table class="tablecloth">
                        <tr>
                            <th>Bersempadan</th><th>Nama</th><th width="120">Jarak (KM)</th>
                        </tr>
                        <tr>
                            <td>
                                Jalan Raya
                            </td>
                            <td>
                                ${actionBean.laporanTanah.namaSempadanJalanraya}&nbsp;
                            </td>
                            <td>
                                <fmt:formatNumber pattern="#,##0.00" value="${actionBean.laporanTanah.jarakSempadanJalanraya}"/>
                                &nbsp;
                            </td>

                        </tr>
                        <tr>
                            <td>
                                Landasan Keretapi
                            </td>
                            <td>
                                ${actionBean.laporanTanah.namaSempadanKeretapi}&nbsp;
                            </td>
                            <td>
                                <fmt:formatNumber pattern="#,##0.00" value="${actionBean.laporanTanah.jarakSempadanKeretapi}"/>
                                &nbsp;
                            </td>

                        </tr>
                        <tr>
                            <td>
                                Laut
                            </td>
                            <td>
                                ${actionBean.laporanTanah.namaSempadanLaut}&nbsp;
                            </td>
                            <td>
                                <fmt:formatNumber pattern="#,##0.00" value="${actionBean.laporanTanah.jarakSempadanLaut}"/>&nbsp;
                            </td>
                        </tr><tr>
                            <td>
                                Sungai
                            </td>
                            <td>
                                ${actionBean.laporanTanah.namaSempadanSungai}&nbsp;
                            </td>
                            <td>
                                <fmt:formatNumber pattern="#,##0.00" value="${actionBean.laporanTanah.jarakSempadanSungai}"/>
                                &nbsp;
                            </td>
                        </tr>
                    </table>
                </div>
                <p>
                    <label>Jalan Masuk :</label>
                    <c:if test="${actionbean.laporanTanah.adaJalanMasuk eq 'Y'}">Ada</c:if>
                    <c:if test="${actionbean.laporanTanah.adaJalanMasuk ne 'Y'}">Tiada</c:if>
                </p>
                <p>
                    <label>Catatan :</label>
                    ${actionBean.laporanTanah.catatanJalanMasuk}&nbsp;
                </p>
            </c:if>
        </fieldset>
    </div>

    <div class="subtitle">
        <fieldset class="aras1">
            <s:hidden name="laporanTanah.idLaporan"/>
            <s:hidden name="laporanTanah.permohonan.idPermohonan"/>
            <legend>Keadaan Tanah</legend>
            <c:if test="${edit}">
                <p>
                    <label>Keadaan Tanah :</label>

                    <s:select name="laporanTanah.kecerunanTanah.kod" id="keadaanTanah" onchange="javaScript:changeKeadaanTanah(this.options[selectedIndex].text)">
                        <s:option>--Sila Pilih--</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodKecerunanTanah}" label="nama" value="kod" />
                    </s:select>
                </p>
                <p id="tinggi">
                    <label>Ketinggian Dari Paras Jalan (m) :</label>
                    <s:text name="laporanTanah.ketinggianDariJalan" />
                </p>
                <p id="cerun">
                    <label>Darjah Kecerunan :</label>
                    <s:text name="laporanTanah.kecerunanBukit" />
                </p>
                <p id="dalam">
                    <label>Dalam Paras Air (m) :</label>
                    <s:text name="laporanTanah.parasAir"/>
                </p>
                <p>
                    <label>Klasifikasi Tanah :</label>
                    <s:select name="laporanTanah.strukturTanah.kod">
                        <s:option>--Sila Pilih--</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodStrukturTanah}" label="nama" value="kod" />
                    </s:select>
                </p>
                <p>
                    <label>Keadaan Atas Tanah :</label>
                    <s:select name="laporanTanah.kelapanganTanah.kod">
                        <s:option>--Sila Pilih--</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodKelapanganTanah}" label="nama" value="kod" />
                    </s:select>
                </p>
                <br>
                <p>
                    <label>Dilintasi Oleh :</label>
                    <s:checkbox name="laporanTanah.dilintasTiangElektrik" value="Y"/>&nbsp; Talian Elektrik<br>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:checkbox name="laporanTanah.dilintasTiangTelefon" value="Y"/>&nbsp; Talian Telefon<br>

                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:checkbox name="laporanTanah.dilintasLaluanGas" value="Y"/>&nbsp; Laluan Gas<br>

                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:checkbox name="laporanTanah.dilintasPaip" value="Y"/>&nbsp; Paip Air<br>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:checkbox name="laporanTanah.dilintasTaliar" value="Y"/>&nbsp; Tali Air<br>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:checkbox name="laporanTanah.dilintasSungai" value="Y"/>&nbsp; Sungai<br>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:checkbox name="laporanTanah.dilintasParit" value="Y"/>&nbsp; Parit<br>
                </p>
            </c:if>
            <c:if test="${!edit}">
                <p>
                    <label>Keadaan Tanah :</label>
                    ${actionBean.laporanTanah.kecerunanTanah.nama}&nbsp;

                </p>
                <p id="tinggi">
                    <label>Ketinggian Dari Paras Jalan (m) :</label>
                    ${actionBean.laporanTanah.ketinggianDariJalan}&nbsp;
                </p>
                <p id="cerun">
                    <label>Darjah Kecerunan :</label>
                    ${actionBean.laporanTanah.kecerunanBukit}&nbsp;
                </p>
                <p id="dalam">
                    <label>Dalam Paras Air (m) :</label>
                    ${actionBean.laporanTanah.parasAir}&nbsp;
                </p>
                <p>
                    <label>Klasifikasi Tanah :</label>
                    ${actionBean.laporanTanah.strukturTanah.nama}&nbsp;

                </p>
                <p>
                    <label>Keadaan Atas Tanah :</label>
                    ${actionBean.laporanTanah.kelapanganTanah.nama}&nbsp;
                </p>
                <br>
                <p>
                    <label>Dilintasi Oleh :</label>
                    <c:if test="${actionBean.laporanTanah.dilintasTiangElektrik eq 'Y'}"> Talian Elektrik</c:if>
                </p>
                <p>
                    <c:if test="${actionBean.laporanTanah.dilintasTiangTelefon eq 'Y'}">
                        <label>&nbsp;</label>
                        Talian Telefon</c:if>

                </p>
                <p>
                    <c:if test="${actionBean.laporanTanah.dilintasLaluanGas eq 'Y'}">
                        <label>&nbsp;</label>
                        Laluan Gas</c:if>

                </p>
                <p><c:if test="${actionBean.laporanTanah.dilintasPaip eq 'Y'}">
                        <label>&nbsp;</label>
                        Paip Air</c:if>
                </p>
                <p><c:if test="${actionBean.laporanTanah.dilintasTaliar eq 'Y'}">
                        <label>&nbsp;</label>
                        Tali Air</c:if>
                </p>
                <p><c:if test="${actionBean.laporanTanah.dilintasSungai eq 'Y'}">
                        <label>&nbsp;</label>
                        Sungai</c:if>
                </p>
                <p><c:if test="${actionBean.laporanTanah.dilintasParit eq 'Y'}">
                        <label>&nbsp;</label>
                        Parit</c:if>
                </p>
            </c:if>

        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Latarbelakang Tanah</legend>
            <c:if test="${edit}">
                <p>
                    <label>Latarbelakang Tanah :</label>
                    <s:checkbox name="check" value=""/>&nbsp; Pemberimilikan<br>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:checkbox name="check" value=""/>&nbsp; LPS
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:checkbox name="check" value=""/>&nbsp; Permit
                </p>
                <br>

                <p>
                    <label>Kegunaan :</label>
                    <s:text name="permohonan.id" size="40"/>
                </p>
                <p>
                    <label>Tarikh Dikeluarkan :</label>
                    <s:text name="fromDate" id="" class="datepicker"/>
                </p>
                <p>
                    <label>Nama Pemegang :</label>
                    <s:text name="permohonan.id" size="40"/>
                </p
                <br>
                <p>
                    <label>Permohonan Awal :</label>
                    <s:radio name="rdAwal" value="Ada"/>&nbsp;Ada
                    <s:radio name="rdAwal" value="Tiada"/>&nbsp;Tiada
                </p>
                <p>
                    <label>Tarikh Permohonan :</label>
                    <s:text name="fromDate" id="" class="datepicker"/>
                </p>
                <p>
                    <label>Nombor Daftar :</label>
                    <s:text name="permohonan.id" size="40"/>
                </p>
                <p>
                    <label>Kedudukan :</label>
                    <s:text name="permohonan.id" size="40"/>
                </p>
                <br>
                <br>
                <p>
                    <label>Diusahakan :</label>
                    <s:radio name="laporanTanah.usaha" value="Y"/>&nbsp;Ya
                    <s:radio name="laporanTanah.usaha" value="T"/>&nbsp;Tidak
                </p
                <p>
                    <label>Oleh :</label>
                    <s:text name="laporanTanah.diusaha" size="40"/>
                </p>
                <p>
                    <label>Tarikh Mula Usaha :</label>
                    <s:text name="date" id="datepicker" class="datepicker" formatPattern="dd/MM/yyyy"/>

                </p>
                <p>
                    <label>Jenis Tanaman :</label>
                    <s:text name="laporanTanah.usahaTanam" size="40"/>
                </p>
                <p>
                    <label>Bangunan :</label>
                    <s:radio name="laporanTanah.adaBangunan" value="Y"/>&nbsp;Ada
                    <s:radio name="laporanTanah.adaBangunan" value="T"/>&nbsp;Tiada
                </p>
                <p>
                    <label>Tahun Dibina :</label>
                    <s:text name="laporanTanah.bangunanTahunDibina"/>
                </p>
                <p>
                    <label>Diduduki :</label>
                    <s:radio name="laporanTanah.bangunanDidiami" value="Y"/>&nbsp;Ya
                    <s:radio name="laporanTanah.bangunanDidiami" value="T"/>&nbsp;Tidak
                </p
                <p>
                    <label>Jenis Bangunan :</label>
                    <s:select name="laporanTanah.jenisBangunan">
                        <s:option>--Sila Pilih--</s:option>
                        <s:option>Sementara</s:option>
                        <s:option>Separuh Kekal</s:option>
                        <s:option>Kekal</s:option>
                        <s:option>Lain-lain</s:option>

                    </s:select>
                </p>
                <br>
                <p>
                    <label>Rancangan Kerajaan :</label>
                    <s:text name="laporanTanah.rancanganKerajaan" size="40"/>
                </p>
                <div class="content" align="center" id="">
                    Tanah Milik
                    <display:table  name="${actionBean.permohonan.senaraiHakmilik}" id="line" class="tablecloth">

                        <display:column property="hakmilik.kodHakmilik.nama" title="Jenis Hakmilik"/>
                        <display:column property="hakmilik.idHakmilik" title="No Hakmilik" />
                        <display:column property="hakmilik.noLot"title="No Lot/PT" />
                        <display:column title="Luas"><fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                        <display:column property="hakmilik.kategoriTanah.nama" title="Kegunaan" />
                        <display:column title="Cukai (RM)"> <fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.cukai}"/> </display:column>
                    </display:table>
                </div>
            </c:if>
            <c:if test="${!edit}">
                <p>
                    <label>Latarbelakang Tanah :</label>
                    &nbsp;
                </p>

                <p>
                    <label>Kegunaan :</label>
                    &nbsp;
                </p>
                <p>
                    <label>Tarikh Dikeluarkan :</label>
                    &nbsp;
                </p>
                <p>
                    <label>Nama Pemegang :</label>
                    &nbsp;
                </p
                <br>
                <p>
                    <label>Permohonan Awal :</label>
                    &nbsp;
                </p>
                <p>
                    <label>Tarikh Permohonan :</label>
                    &nbsp;
                </p>
                <p>
                    <label>Nombor Daftar :</label>
                    &nbsp;
                </p>
                <p>
                    <label>Kedudukan :</label>
                    &nbsp;
                </p>
                <br>
                <br>
                <p>
                    <label>Diusahakan :</label>
                    <c:if test="${actionBean.laporanTanah.usaha eq 'Y'}">Ya</c:if>
                    <c:if test="${actionBean.laporanTanah.usaha ne 'Y'}">Tidak</c:if>
                </p
                <p>
                    <label>Oleh :</label>
                    ${actionBean.laporanTanah.diusaha}&nbsp;

                </p>
                <p>
                    <label>Tarikh Mula Usaha :</label>
                    <s:format formatPattern="dd/MM/yyyy" value="${actionBean.laporanTanah.tarikhMulaUsaha}" />&nbsp;
                </p>
                <p>
                    <label>Jenis Tanaman :</label>
                    ${actionBean.laporanTanah.usahaTanam}&nbsp;
                </p>
                <p>
                    <label>Bangunan :</label>
                    <c:if test="${actionBean.laporanTanah.adaBangunan eq 'Y'}">Ada</c:if>
                    <c:if test="${actionBean.laporanTanah.adaBangunan ne 'Y'}">Tiada</c:if>

                </p>
                <p>
                    <label>Tahun Dibina :</label>
                    ${actionBean.laporanTanah.bangunanTahunDibina}&nbsp;
                </p>
                <p>
                    <label>Diduduki :</label>
                    <c:if test="${actionbean.laporanTanah.bangunanDidiami eq 'Y'}">Ya</c:if>
                    <c:if test="${actionbean.laporanTanah.bangunanDidiami ne 'Y'}">Tidak</c:if>

                </p
                <p>
                    <label>Jenis Bangunan :</label>
                    ${actionBean.laporanTanah.jenisBangunan}&nbsp;

                </p>
                <br>

                </p
                <p >
                    <label>Rancangan Kerajaan :</label>
                    ${actionBean.laporanTanah.rancanganKerajaan}&nbsp;
                </p>
                <%--<p>
                    <label>Tanah Milik :</label>
                    &nbsp;
                </p>--%>

                <div class="content" align="center">
                    Tanah Milik
                    <display:table  name="${actionBean.permohonan.senaraiHakmilik}" id="line" class="tablecloth">
                        <display:column property="hakmilik.kodHakmilik.nama" title="Jenis Hakmilik"/>
                        <display:column property="hakmilik.idHakmilik" title="No Hakmilik" />
                        <display:column property="hakmilik.noLot"title="No Lot/PT" />
                        <display:column title="Luas"><fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                        <display:column property="hakmilik.kategoriTanah.nama" title="Kegunaan" />
                        <display:column title="Cukai (RM)"> <fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.cukai}"/> </display:column>
                    </display:table>
                </div>
            </c:if>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Tanah Sekeliling</legend>
            <c:if test="${edit}">
                <div class="content" align="center">
                    <table class="tablecloth">
                        <tr>
                            <th>&nbsp;</th><th>Jenis Tanah</th><th>Nombor Lot</th><th>Kegunaan</th>
                        </tr>
                        <tr>
                            <th>
                                Utara
                            </th>
                            <td>
                                <s:radio name="laporanTanah.sempadanUtaraMilikKerajaan" value="Y"/>&nbsp;Kerajaan
                                <s:radio name="laporanTanah.sempadanUtaraMilikKerajaan" value="T"/>&nbsp;Milik
                            </td>
                            <td>
                                <s:text name="laporanTanah.sempadanUtaraNoLot" />
                            </td>
                            <td>
                                <s:text name="laporanTanah.sempadanUtaraKegunaan" />
                            </td>
                        </tr>
                        <tr>
                            <th>
                                Selatan
                            </th>
                            <td>
                                <s:radio name="laporanTanah.sempadanSelatanMilikKerajaan" value="Y"/>&nbsp;Kerajaan
                                <s:radio name="laporanTanah.sempadanSelatanMilikKerajaan" value="T"/>&nbsp;Milik
                            </td>
                            <td>
                                <s:text name="laporanTanah.sempadanSelatanNoLot" />
                            </td>
                            <td>
                                <s:text name="laporanTanah.sempadanSelatanKegunaan" />
                            </td>
                        </tr>
                        <tr>
                            <th>
                                Timur
                            </th>
                            <td>
                                <s:radio name="laporanTanah.sempadanTimurMilikKerajaan" value="Y"/>&nbsp;Kerajaan
                                <s:radio name="laporanTanah.sempadanTimurMilikKerajaan" value="T"/>&nbsp;Milik
                            </td>
                            <td>
                                <s:text name="laporanTanah.sempadanTimurNoLot" />
                            </td>
                            <td>
                                <s:text name="laporanTanah.sempadanTimurKegunaan" />
                            </td>
                        </tr><tr>
                            <th>
                                Barat
                            </th>
                            <td>
                                <s:radio name="laporanTanah.sempadanBaratMilikKerajaan" value="Y"/>&nbsp;Kerajaan
                                <s:radio name="laporanTanah.sempadanBaratMilikKerajaan" value="T"/>&nbsp;Milik
                            </td>
                            <td>
                                <s:text name="laporanTanah.sempadanBaratNoLot" />
                            </td>
                            <td>
                                <s:text name="laporanTanah.sempadanBaratKegunaan" />
                            </td>
                        </tr>
                    </table>
                </div>
            </c:if>
            <c:if test="${!edit}">
                <div class="content" align="center">
                    <table class="tablecloth">
                        <tr>
                            <th>&nbsp;</th><th>Jenis Tanah</th><th>Nombor Lot</th><th>Kegunaan</th>
                        </tr>
                        <tr>
                            <th>
                                Utara
                            </th>
                            <td>
                                <c:if test="${actionBean.laporanTanah.sempadanUtaraMilikKerajaan ne 'T'}">Kerajaan</c:if>
                                <c:if test="${actionBean.laporanTanah.sempadanUtaraMilikKerajaan eq 'T'}">Milik</c:if>
                            </td>
                            <td>
                                ${actionBean.laporanTanah.sempadanUtaraNoLot}&nbsp;

                            </td>
                            <td>
                                ${actionBean.laporanTanah.sempadanUtaraKegunaan}&nbsp;
                            </td>
                        </tr>
                        <tr>
                            <th>
                                Selatan
                            </th>
                            <td>
                                <c:if test="${actionBean.laporanTanah.sempadanSelatanMilikKerajaan ne 'T'}">Kerajaan</c:if>
                                <c:if test="${actionBean.laporanTanah.sempadanSelatanMilikKerajaan eq 'T'}">Milik</c:if>
                            </td>
                            <td>
                                ${actionBean.laporanTanah.sempadanSelatanNoLot}&nbsp;
                            </td>
                            <td>
                                ${actionBean.laporanTanah.sempadanSelatanKegunaan}&nbsp;
                            </td>
                        </tr>
                        <tr>
                            <th>
                                Timur
                            </th>
                            <td>
                                <c:if test="${actionBean.laporanTanah.sempadanTimurMilikKerajaan ne 'T'}">Kerajaan</c:if>
                                <c:if test="${actionBean.laporanTanah.sempadanTimurMilikKerajaan eq 'T'}">Milik</c:if>
                            </td>
                            <td>
                                ${actionBean.laporanTanah.sempadanTimurNoLot}&nbsp;
                            </td>
                            <td>
                                ${actionBean.laporanTanah.sempadanTimurKegunaan}&nbsp;
                            </td>
                        </tr><tr>
                            <th>
                                Barat
                            </th>
                            <td>
                                <c:if test="${actionBean.laporanTanah.sempadanBaratMilikKerajaan ne 'T'}">Kerajaan</c:if>
                                <c:if test="${actionBean.laporanTanah.sempadanBaratMilikKerajaan eq 'T'}">Milik</c:if>
                            </td>
                            <td>
                                ${actionBean.laporanTanah.sempadanBaratNoLot}&nbsp;
                            </td>
                            <td>
                                ${actionBean.laporanTanah.sempadanBaratKegunaan}&nbsp;
                            </td>
                        </tr>
                    </table>
                </div>
            </c:if>

        </fieldset>
    </div>

    <div class="subtitle">
        <fieldset class="aras1">

            <legend>Ulasan Pegawai</legend>
            <c:if test="${edit}">
                <p>
                    <label>Ulasan :</label>

                    <s:textarea name="permohonan.id" cols="50"/>

                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:button name="simpanLaporanTanah" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </p>
            </c:if>
            <c:if test="${!edit}">
                <p>
                    <label>Ulasan :</label>
                    &nbsp;
                </p>
            </c:if>

        </fieldset>
    </div>
</s:form>



