
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

        else if(v == 'CR'){
            $('#tinggi').hide();
            $('#cerun').show();
            $('#dalam').hide();
        }

        else if(v == 'PY'){
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
                <c:if test="${actionBean.permohonan.infoAudit.tarikhMasuk ne null}"><s:format formatPattern="dd/MM/yyyy" value="${actionBean.permohonan.infoAudit.tarikhMasuk}"/>&nbsp;</c:if>
                <c:if test="${actionBean.permohonan.infoAudit.tarikhMasuk eq null}"> Tiada Data </c:if>

            </p>
            <%--<c:if test="${edit}">
                <p>
                    <label>Tarikh Permohonan Didaftar :</label>
                    <s:text name="fromDate" id="" class="datepicker"/>
                </p>
            </c:if>--%>
<%--            <c:if test="${!edit}">
                <p>
                    <label>Tarikh Permohonan Didaftar :</label>
                    Tiada Data&nbsp;
                </p>
            </c:if>--%>

        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Perihal Tanah</legend>
            <c:if test="${edit}">
                <p>
                    <label>Keluasan :</label>
                    <fmt:formatNumber  pattern="#,##0.0000" value="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.luas}"/>
                    ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodUnitLuas.nama}&nbsp;
                </p>
                <p>
                    <label>Status Tanah :</label>
                    <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.rizab.nama ne null}"> ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.rizab.nama}&nbsp; </c:if>
                    <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.rizab.nama eq null}"> Tiada Data </c:if>
                </p>
                <%-- <p>
                     <label>Status Tanah :</label>
                     <s:radio name="rdKategoriTanah" value="kerajaan" onchange="javaScript:changeKategoriTanah(this.value)"/>&nbsp;Tanah Kerajaan
                     <s:radio name="rdKategoriTanah" value="milik" onchange="javaScript:changeKategoriTanah(this.value)"/>&nbsp;Tanah Milik
                     <s:radio name="rdKategoriTanah" value="rizab" onchange="javaScript:changeKategoriTanah(this.value)"/>&nbsp;Tanah Rizab
                 </p>
                 <p id="jenisRizab">
                     <label>Jenis Rizab :</label>
                     <s:text name="permohonan.sebab" size="40"/>
                 </p>--%>
                <%--<p id="noWarta">--%>
                <p>
                    <label>Nombor Warta Kerajaan :</label>
                    <s:text name="permohonanRujukanLuar.noRujukan" />
                </p>
                <%-- <p>
                     <label>Gambar Lokasi Tanah :</label>
                     <s:radio name="rdGambar" value="Ada"/>&nbsp;Ada
                     <s:radio name="rdGambar" value="Tiada"/>&nbsp;Tiada
                 </p>--%>
                <p>
                    <label>Jenis Tanah :</label>
                    <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodTanah.nama ne null}"> ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodTanah.nama}&nbsp; </c:if>
                    <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodTanah.nama eq null}"> Tiada Data </c:if>
                </p>
                <p>
                    <label>Lokaliti :</label>
                    <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.lokasi ne null}"> ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.lokasi}&nbsp; </c:if>
                    <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.lokasi eq null}"> Tiada Data </c:if>
                </p>
                <p>
                    <label>Kawasan PBT :</label>
                    <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.pbt.nama ne null}"> ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.pbt.nama}&nbsp; </c:if>
                    <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.pbt.nama eq null}"> Tiada Data </c:if>
                </p>
                <p>
                    <label>Jarak dari tempat tinggal (KM):</label>
                    <s:text name="jarak1" />
                </p>
                <p>
                    <label>Jarak dari bandar/pekan terhampir (KM):</label>
                    <s:text name="jarak2" />
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
                                <s:text name="laporanTanah.jarakSempadanJalanraya"  maxlength="3" onkeyup="validateNumber(this,this.value);"/>
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
                                <s:text name="laporanTanah.jarakSempadanKeretapi" maxlength="3" onkeyup="validateNumber(this,this.value);"/>
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
                                <s:text name="laporanTanah.jarakSempadanLaut"  maxlength="3" onkeyup="validateNumber(this,this.value);"/>
                            </td>
                        </tr><tr>
                            <td>
                                Sungai
                            </td>
                            <td>
                                <s:text name="laporanTanah.namaSempadanSungai" />
                            </td>
                            <td>
                                <s:text name="laporanTanah.jarakSempadanSungai"  maxlength="3" onkeyup="validateNumber(this,this.value);"/>
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
                    <s:textarea name="laporanTanah.catatanJalanMasuk" cols="50"/>
                </p>
            </c:if>
            <c:if test="${!edit}">
                <p>
                    <label>Keluasan :</label>
                    <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.luas ne null}"><fmt:formatNumber  pattern="#,##0.0000" value="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.luas}"/>
                        ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodUnitLuas.nama}&nbsp;</c:if>
                    <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.luas eq null}"> Tiada Data </c:if>


                </p>
                <p>
                    <label>Status Tanah :</label>
                    <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.rizab.nama ne null}"> ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.rizab.nama}&nbsp; </c:if>
                    <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.rizab.nama eq null}"> Tiada Data </c:if>
                </p>

                <%-- <p>
                     <label>Status Tanah :</label>
                     &nbsp;
                 </p>
                 <p id="jenisRizab">
                     <label>Jenis Rizab :</label>
                     &nbsp;
                 </p>--%>
                <p>
                    <label>Nombor Warta Kerajaan :</label>
                    <c:if test="${actionBean.permohonanRujukanLuar.noRujukan ne null}"> ${actionBean.permohonanRujukanLuar.noRujukan}&nbsp; </c:if>
                    <c:if test="${actionBean.permohonanRujukanLuar.noRujukan eq null}"> Tiada Data </c:if>
                </p>
                <%--<p>
                    <label>Gambar Lokasi Tanah :</label>
                    &nbsp;
                </p>--%>
                <p>
                    <label>Jenis Tanah :</label>
                    <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodTanah.nama ne null}"> ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodTanah.nama}&nbsp; </c:if>
                    <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodTanah.nama eq null}"> Tiada Data </c:if>
                </p>
                <p>
                    <label>Lokaliti :</label>
                    <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.lokasi ne null}"> ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.lokasi}&nbsp; </c:if>
                    <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.lokasi eq null}"> Tiada Data </c:if>
                </p>
                <p>
                    <label>Kawasan PBT :</label>
                    <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.pbt.nama ne null}"> ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.pbt.nama}&nbsp; </c:if>
                    <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.pbt.nama eq null}"> Tiada Data </c:if>
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
                                <c:if test="${actionBean.laporanTanah.namaSempadanJalanraya ne null}"> ${actionBean.laporanTanah.namaSempadanJalanraya}&nbsp; </c:if>
                                <c:if test="${actionBean.laporanTanah.namaSempadanJalanraya eq null}"> Tiada Data </c:if>
                            </td>
                            <td>
                                <c:if test="${actionBean.laporanTanah.jarakSempadanJalanraya ne null}"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.laporanTanah.jarakSempadanJalanraya}"/></c:if>
                                <c:if test="${actionBean.laporanTanah.jarakSempadanJalanraya eq null}"> Tiada Data </c:if>
                            </td>

                        </tr>
                        <tr>
                            <td>
                                Landasan Keretapi
                            </td>
                            <td>
                                <c:if test="${actionBean.laporanTanah.namaSempadanKeretapi ne null}"> ${actionBean.laporanTanah.namaSempadanKeretapi}&nbsp; </c:if>
                                <c:if test="${actionBean.laporanTanah.namaSempadanKeretapi eq null}"> Tiada Data </c:if>
                            </td>
                            <td>
                                <c:if test="${actionBean.laporanTanah.jarakSempadanKeretapi ne null}">  <fmt:formatNumber pattern="#,##0.00" value="${actionBean.laporanTanah.jarakSempadanKeretapi}"/>&nbsp; </c:if>
                                <c:if test="${actionBean.laporanTanah.jarakSempadanKeretapi eq null}"> Tiada Data </c:if>
                            </td>

                        </tr>
                        <tr>
                            <td>
                                Laut
                            </td>
                            <td>
                                <c:if test="${actionBean.laporanTanah.namaSempadanLaut ne null}"> ${actionBean.laporanTanah.namaSempadanLaut}&nbsp; </c:if>
                                <c:if test="${actionBean.laporanTanah.namaSempadanLaut eq null}"> Tiada Data </c:if>
                            </td>
                            <td>
                                <c:if test="${actionBean.laporanTanah.jarakSempadanLaut ne null}"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.laporanTanah.jarakSempadanLaut}"/>&nbsp;</c:if>
                                <c:if test="${actionBean.laporanTanah.jarakSempadanLaut eq null}"> Tiada Data </c:if>

                            </td>
                        </tr><tr>
                            <td>
                                Sungai
                            </td>
                            <td>
                                <c:if test="${actionBean.laporanTanah.namaSempadanSungai ne null}"> ${actionBean.laporanTanah.namaSempadanSungai}&nbsp; </c:if>
                                <c:if test="${actionBean.laporanTanah.namaSempadanSungai eq null}"> Tiada Data </c:if>
                            </td>
                            <td>
                                <c:if test="${actionBean.laporanTanah.jarakSempadanSungai ne null}">   <fmt:formatNumber pattern="#,##0.00" value="${actionBean.laporanTanah.jarakSempadanSungai}"/>&nbsp; </c:if>
                                <c:if test="${actionBean.laporanTanah.jarakSempadanSungai eq null}"> Tiada Data </c:if>

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
                    <c:if test="${actionBean.laporanTanah.catatanJalanMasuk ne null}"> ${actionBean.laporanTanah.catatanJalanMasuk}&nbsp; </c:if>
                    <c:if test="${actionBean.laporanTanah.catatanJalanMasuk eq null}"> Tiada Data </c:if>
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
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodKecerunanTanah}" label="nama" value="kod" />
                    </s:select>
                </p>
                <p id="tinggi">
                    <label>Ketinggian Dari Paras Jalan (m) :</label>
                    <s:text name="laporanTanah.ketinggianDariJalan" maxlength="3" onkeyup="validateNumber(this,this.value);"/>
                </p>
                <p id="cerun">
                    <label>Darjah Kecerunan :</label>
                    <s:text name="laporanTanah.kecerunanBukit"  maxlength="3" onkeyup="validateNumber(this,this.value);"/>
                </p>
                <p id="dalam">
                    <label>Dalam Paras Air (m) :</label>
                    <s:text name="laporanTanah.parasAir" maxlength="3" onkeyup="validateNumber(this,this.value);"/>
                </p>
                <p>
                    <label>Klasifikasi Tanah :</label>
                    <s:select name="laporanTanah.strukturTanah.kod">
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodStrukturTanah}" label="nama" value="kod" />
                    </s:select>
                </p>
                <p>
                    <label>Keadaan Atas Tanah :</label>
                    <s:select name="laporanTanah.kelapanganTanah.kod">
                        <s:option value="">--Sila Pilih--</s:option>
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
                    <c:if test="${actionBean.laporanTanah.kecerunanTanah.nama ne null}"> ${actionBean.laporanTanah.kecerunanTanah.nama}&nbsp; </c:if>
                    <c:if test="${actionBean.laporanTanah.kecerunanTanah.nama eq null}"> Tiada Data </c:if>
                </p>
                <p id="tinggi">
                    <label>Ketinggian Dari Paras Jalan (m) :</label>
                    <c:if test="${actionBean.laporanTanah.ketinggianDariJalan ne null}"> ${actionBean.laporanTanah.ketinggianDariJalan}&nbsp; </c:if>
                    <c:if test="${actionBean.laporanTanah.ketinggianDariJalan eq null}"> Tiada Data </c:if>
                </p>
                <p id="cerun">
                    <label>Darjah Kecerunan :</label>
                    <c:if test="${actionBean.laporanTanah.kecerunanBukit ne null}"> ${actionBean.laporanTanah.kecerunanBukit}&nbsp; </c:if>
                    <c:if test="${actionBean.laporanTanah.kecerunanBukit eq null}"> Tiada Data </c:if>
                </p>
                <p id="dalam">
                    <label>Dalam Paras Air (m) :</label>
                    <c:if test="${actionBean.laporanTanah.parasAir ne null}"> ${actionBean.laporanTanah.parasAir}&nbsp; </c:if>
                    <c:if test="${actionBean.laporanTanah.parasAir eq null}"> Tiada Data </c:if>
                </p>
                <p>
                    <label>Klasifikasi Tanah :</label>
                    <c:if test="${actionBean.laporanTanah.strukturTanah.nama ne null}"> ${actionBean.laporanTanah.strukturTanah.nama}&nbsp; </c:if>
                    <c:if test="${actionBean.laporanTanah.strukturTanah.nama eq null}"> Tiada Data </c:if>
                </p>
                <p>
                    <label>Keadaan Atas Tanah :</label>
                    <c:if test="${actionBean.laporanTanah.kelapanganTanah.nama ne null}"> ${actionBean.laporanTanah.kelapanganTanah.nama}&nbsp; </c:if>
                    <c:if test="${actionBean.laporanTanah.kelapanganTanah.nama eq null}"> Tiada Data </c:if>
                </p>
                <br>
                <p>
                    <label>Dilintasi Oleh :</label>

                    <c:if test="${actionBean.laporanTanah.dilintasTiangElektrik ne null or
                                  actionBean.laporanTanah.dilintasTiangTelefon ne null or
                                  actionBean.laporanTanah.dilintasLaluanGas ne null or
                                  actionBean.laporanTanah.dilintasPaip ne null or
                                  actionBean.laporanTanah.dilintasTaliar ne null or
                                  actionBean.laporanTanah.dilintasSungai ne null or
                                  actionBean.laporanTanah.dilintasParit ne null}">
                        <c:if test="${actionBean.laporanTanah.dilintasTiangElektrik eq 'Y'}"> Talian Elektrik</c:if>
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


                <c:if test="${actionBean.laporanTanah.dilintasTiangElektrik eq null and
                              actionBean.laporanTanah.dilintasTiangTelefon eq null and
                              actionBean.laporanTanah.dilintasLaluanGas eq null and
                              actionBean.laporanTanah.dilintasPaip eq null and
                              actionBean.laporanTanah.dilintasTaliar eq null and
                              actionBean.laporanTanah.dilintasSungai eq null and
                              actionBean.laporanTanah.dilintasParit eq null}"> Tiada Data </c:if>


                <%--                <p>
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
                </p>--%>



            </c:if>

        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Latar belakang Tanah</legend>
            <c:if test="${edit}">
                <%--<p>
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
                </p>
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
                <br>--%>
                <p>
                    <label>Diusahakan :</label>
                    <s:radio name="laporanTanah.usaha" value="Y"/>&nbsp;Ya
                    <s:radio name="laporanTanah.usaha" value="T"/>&nbsp;Tidak
                </p>
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
                    <s:text name="laporanTanah.bangunanTahunDibina" maxlength="4" onkeyup="validateNumber(this,this.value);"/>
                </p>
                <p>
                    <label>Diduduki :</label>
                    <s:radio name="laporanTanah.bangunanDidiami" value="Y"/>&nbsp;Ya
                    <s:radio name="laporanTanah.bangunanDidiami" value="T"/>&nbsp;Tidak
                </p>
                <p>
                    <label>Jenis Bangunan :</label>
                    <s:select name="laporanTanah.jenisBangunan">
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:option>Sementara</s:option>
                        <s:option>Separuh Kekal</s:option>
                        <s:option>Kekal</s:option>
                        <s:option>Lain-lain</s:option>

                    </s:select>
                </p>

                <%--<p>
                    <label>Terdapat Rancangan Kerajaan :</label>
                    <s:radio name="rRancangan" value="ada" onchange="javaScript:changeRancangan(this.value)"/>&nbsp;Ya
                    <s:radio name="rRancangan" value="tiada" onchange="javaScript:changeRancangan(this.value)"/>&nbsp;Tidak
                </p>--%>

                <%--<p id="nyataRancangan">--%>
                <p>    
                    <%--<label>Nyatakan :</label>--%>
                    <label>Rancangan Kerajaan :</label>
                    <s:text name="laporanTanah.rancanganKerajaan" size="40"/>
                </p>
                <%--<p>
                    <label>Tanah Milik :</label>
                    <s:radio name="rdHakmilik" value="ya" onchange="javaScript:changeTanahMilik(this.value)"/>&nbsp;Ya
                    <s:radio name="rdHakmilik" value="tidak" onchange="javaScript:changeTanahMilik(this.value)"/>&nbsp;Tidak
                </p>--%>

                <div class="content" align="center">
                    Tanah Milik
                    <display:table  name="${actionBean.permohonan.senaraiHakmilik}" id="line" class="tablecloth">
                        <display:column title="Jenis Hakmilik">
                            <c:if test="${line.hakmilik.kodHakmilik.nama ne null}"> ${line.hakmilik.kodHakmilik.nama}&nbsp; </c:if>
                            <c:if test="${line.hakmilik.kodHakmilik.nama eq null}"> Tiada Data </c:if>
                        </display:column>

                        <display:column title="Nombor Hakmilik">
                            <c:if test="${line.hakmilik.idHakmilik ne null}"> ${line.hakmilik.idHakmilik}&nbsp; </c:if>
                            <c:if test="${line.hakmilik.idHakmilik eq null}"> Tiada Data </c:if>
                        </display:column>

                        <display:column title="Nombor Lot/PT" >
                            <c:if test="${line.hakmilik.noLot ne null}"> ${line.hakmilik.noLot}&nbsp; </c:if>
                            <c:if test="${line.hakmilik.noLot eq null}"> Tiada Data </c:if>

                        </display:column>

                        <display:column title="Luas">
                            <c:if test="${line.hakmilik.luas ne null}"> <fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</c:if>
                            <c:if test="${line.hakmilik.luas eq null}"> Tiada Data </c:if>
                        </display:column>

                        <display:column property="hakmilik.kategoriTanah.nama" title="Kegunaan" >
                            <c:if test="${actionBean.laporanTanah.sempadanUtaraNoLot ne null}"> ${actionBean.laporanTanah.sempadanUtaraNoLot}&nbsp; </c:if>
                            <c:if test="${actionBean.laporanTanah.sempadanUtaraNoLot eq null}"> Tiada Data </c:if>
                        </display:column>

                        <display:column title="Cukai (RM)">
                            <c:if test="${line.hakmilik.cukai ne null}"> <fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.cukai}"/>&nbsp; </c:if>
                            <c:if test="${line.hakmilik.cukai eq null}"> Tiada Data </c:if>
                        </display:column>
                    </display:table>
                </div>
            </c:if>
            <c:if test="${!edit}">
                <%--  <p>
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
                </p>
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
                <br>--%>
                <p>
                    <label>Diusahakan :</label>
                    <c:if test="${actionBean.laporanTanah.usaha eq 'Y'}">Ya</c:if>
                    <c:if test="${actionBean.laporanTanah.usaha ne 'Y'}">Tidak</c:if>
                </p>
                <p>
                    <label>Oleh :</label>
                    <c:if test="${actionBean.laporanTanah.diusaha ne null}"> ${actionBean.laporanTanah.diusaha}&nbsp; </c:if>
                    <c:if test="${actionBean.laporanTanah.diusaha eq null}"> Tiada Data </c:if>
                </p>
                <p>
                    <label>Tarikh Mula Usaha :</label>
                    <c:if test="${actionBean.laporanTanah.tarikhMulaUsaha ne null}"><s:format formatPattern="dd/MM/yyyy" value="${actionBean.laporanTanah.tarikhMulaUsaha}" />&nbsp;</c:if>
                    <c:if test="${actionBean.laporanTanah.rancanganKerajaan eq null}"> Tiada Data </c:if>
                </p>
                <p>
                    <label>Jenis Tanaman :</label>
                    <c:if test="${actionBean.laporanTanah.usahaTanam ne null}"> ${actionBean.laporanTanah.usahaTanam}&nbsp; </c:if>
                    <c:if test="${actionBean.laporanTanah.usahaTanam eq null}"> Tiada Data </c:if>
                </p>
                <p>
                    <label>Bangunan :</label>
                    <c:if test="${actionBean.laporanTanah.adaBangunan eq 'Y'}">Ada</c:if>
                    <c:if test="${actionBean.laporanTanah.adaBangunan ne 'Y'}">Tiada</c:if>

                </p>
                <p>
                    <label>Tahun Dibina :</label>
                    <c:if test="${actionBean.laporanTanah.bangunanTahunDibina ne null}"> ${actionBean.laporanTanah.bangunanTahunDibina}&nbsp; </c:if>
                    <c:if test="${actionBean.laporanTanah.bangunanTahunDibina eq null}"> Tiada Data </c:if>
                </p>
                <p>
                    <label>Diduduki :</label>
                    <c:if test="${actionbean.laporanTanah.bangunanDidiami eq 'Y'}">Ya</c:if>
                    <c:if test="${actionbean.laporanTanah.bangunanDidiami ne 'Y'}">Tidak</c:if>

                </p>
                <p>
                    <label>Jenis Bangunan :</label>
                    <c:if test="${actionBean.laporanTanah.jenisBangunan ne null}"> ${actionBean.laporanTanah.jenisBangunan}&nbsp; </c:if>
                    <c:if test="${actionBean.laporanTanah.jenisBangunan eq null}"> Tiada Data </c:if>
                </p>
                <p>
                    <label>Rancangan Kerajaan :</label>

                    <c:if test="${actionBean.laporanTanah.rancanganKerajaan ne null}"> ${actionBean.laporanTanah.rancanganKerajaan}&nbsp; </c:if>
                    <c:if test="${actionBean.laporanTanah.rancanganKerajaan eq null}"> Tiada Data </c:if>

                </p>
                <%--<p>
                    <label>Tanah Milik :</label>
                    &nbsp;
                </p>--%>

                <div class="content" align="center">
                    Tanah Milik
                    <display:table  name="${actionBean.permohonan.senaraiHakmilik}" id="line" class="tablecloth">
                        <display:column title="Jenis Hakmilik">
                            <c:if test="${line.hakmilik.kodHakmilik.nama ne null}"> ${line.hakmilik.kodHakmilik.nama}&nbsp; </c:if>
                            <c:if test="${line.hakmilik.kodHakmilik.nama eq null}"> Tiada Data </c:if>
                        </display:column>

                        <display:column title="Nombor Hakmilik">
                            <c:if test="${line.hakmilik.idHakmilik ne null}"> ${line.hakmilik.idHakmilik}&nbsp; </c:if>
                            <c:if test="${line.hakmilik.idHakmilik eq null}"> Tiada Data </c:if>
                        </display:column>

                        <display:column title="Nombor Lot/PT" >
                            <c:if test="${line.hakmilik.noLot ne null}"> ${line.hakmilik.noLot}&nbsp; </c:if>
                            <c:if test="${line.hakmilik.noLot eq null}"> Tiada Data </c:if>

                        </display:column>

                        <display:column title="Luas">
                            <c:if test="${line.hakmilik.luas ne null}"> <fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</c:if>
                            <c:if test="${line.hakmilik.luas eq null}"> Tiada Data </c:if>
                        </display:column>

                        <display:column property="hakmilik.kategoriTanah.nama" title="Kegunaan" >
                            <c:if test="${actionBean.laporanTanah.sempadanUtaraNoLot ne null}"> ${actionBean.laporanTanah.sempadanUtaraNoLot}&nbsp; </c:if>
                            <c:if test="${actionBean.laporanTanah.sempadanUtaraNoLot eq null}"> Tiada Data </c:if>
                        </display:column>

                        <display:column title="Cukai (RM)">
                            <c:if test="${line.hakmilik.cukai ne null}"> <fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.cukai}"/>&nbsp; </c:if>
                            <c:if test="${line.hakmilik.cukai eq null}"> Tiada Data </c:if>
                        </display:column>
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
                                <c:if test="${actionBean.laporanTanah.sempadanUtaraNoLot ne null}"> ${actionBean.laporanTanah.sempadanUtaraNoLot}&nbsp; </c:if>
                                <c:if test="${actionBean.laporanTanah.sempadanUtaraNoLot eq null}"> Tiada Data </c:if>
                            </td>
                            <td>
                                <c:if test="${actionBean.laporanTanah.sempadanUtaraKegunaan ne null}"> ${actionBean.laporanTanah.sempadanUtaraKegunaan}&nbsp; </c:if>
                                <c:if test="${actionBean.laporanTanah.sempadanUtaraKegunaan eq null}"> Tiada Data </c:if>
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
                                <c:if test="${actionBean.laporanTanah.sempadanSelatanNoLot ne null}"> ${actionBean.laporanTanah.sempadanSelatanNoLot}&nbsp; </c:if>
                                <c:if test="${actionBean.laporanTanah.sempadanSelatanNoLot eq null}"> Tiada Data </c:if>
                            </td>
                            <td>
                                <c:if test="${actionBean.laporanTanah.sempadanSelatanKegunaan ne null}"> ${actionBean.laporanTanah.sempadanSelatanKegunaan}&nbsp; </c:if>
                                <c:if test="${actionBean.laporanTanah.sempadanSelatanKegunaan eq null}"> Tiada Data </c:if>
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
                                <c:if test="${actionBean.laporanTanah.sempadanTimurNoLot ne null}"> ${actionBean.laporanTanah.sempadanTimurNoLot}&nbsp; </c:if>
                                <c:if test="${actionBean.laporanTanah.sempadanTimurNoLot eq null}"> Tiada Data </c:if>
                            </td>
                            <td>
                                <c:if test="${actionBean.laporanTanah.sempadanTimurKegunaan ne null}"> ${actionBean.laporanTanah.sempadanTimurKegunaan}&nbsp; </c:if>
                                <c:if test="${actionBean.laporanTanah.sempadanTimurKegunaan eq null}"> Tiada Data </c:if>
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
                                <c:if test="${actionBean.laporanTanah.sempadanBaratNoLot ne null}"> ${actionBean.laporanTanah.sempadanBaratNoLot}&nbsp; </c:if>
                                <c:if test="${actionBean.laporanTanah.sempadanBaratNoLot eq null}"> Tiada Data </c:if>
                            </td>
                            <td>
                                <c:if test="${actionBean.laporanTanah.sempadanBaratKegunaan ne null}"> ${actionBean.laporanTanah.sempadanBaratKegunaan}&nbsp; </c:if>
                                <c:if test="${actionBean.laporanTanah.sempadanBaratKegunaan eq null}"> Tiada Data </c:if>
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
                    <label><font color="red">*</font>Ulasan :</label>
                    <s:textarea name="fasaPermohonan.ulasan"rows="15" cols="50" onkeyup="this.value=this.value.toUpperCase();"/>

                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:button name="simpanLaporanTanah" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </p>
            </c:if>
            <c:if test="${!edit}">
                <p>
                    <label>Ulasan :</label>
                    <c:if test="${actionBean.fasaPermohonan.ulasan ne null}"> ${actionBean.fasaPermohonan.ulasan}&nbsp; </c:if>
                    <c:if test="${actionBean.fasaPermohonan.ulasan eq null}"> Tiada Data </c:if>
                </p>
            </c:if>

        </fieldset>
    </div>
</s:form>
