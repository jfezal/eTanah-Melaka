<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">

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
    <s:hidden name="fasaPermohonan.idFasa" />
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
        </fieldset>
    </div>
    <br/>
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
                <p>
                    <label>Nombor Warta Kerajaan :</label>
                    <s:text name="permohonanRujukanLuar.noRujukan" maxlength="30" size="40"/>
                </p>
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
                            <th>Bersempadan</th><th>Nama</th><th>Jarak (KM)</th>
                        </tr>
                        <tr>
                            <td>
                                Jalan Raya
                            </td>
                            <td>
                                <s:text name="laporanTanah.namaSempadanJalanraya" maxlength="50" size="50"/>
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
                                <s:text name="laporanTanah.namaSempadanKeretapi" maxlength="50" size="50"/>
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
                                <s:text name="laporanTanah.namaSempadanLaut" maxlength="50" size="50"/>
                            </td>
                            <td>
                                <s:text name="laporanTanah.jarakSempadanLaut"  maxlength="3" onkeyup="validateNumber(this,this.value);"/>
                            </td>
                        </tr><tr>
                            <td>
                                Sungai
                            </td>
                            <td>
                                <s:text name="laporanTanah.namaSempadanSungai" maxlength="50" size="50"/>
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
                    <s:textarea name="laporanTanah.catatanJalanMasuk" cols="50" rows="4"/>
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
                <p>
                    <label>Nombor Warta Kerajaan :</label>
                    <c:if test="${actionBean.permohonanRujukanLuar.noRujukan ne null}"> ${actionBean.permohonanRujukanLuar.noRujukan}&nbsp; </c:if>
                    <c:if test="${actionBean.permohonanRujukanLuar.noRujukan eq null}"> Tiada Data </c:if>
                </p>
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
                    <c:if test="${actionBean.laporanTanah.adaJalanMasuk ne null}">
                        <c:if test="${actionBean.laporanTanah.adaJalanMasuk eq 'Y'}">Ada</c:if>
                        <c:if test="${actionBean.laporanTanah.adaJalanMasuk eq 'T'}">Tiada</c:if>
                    </c:if>
                    <c:if test="${actionBean.laporanTanah.adaJalanMasuk eq null}">Tiada Data</c:if>
                </p>
                <p>
                    <label>Catatan :</label>
                    <c:if test="${actionBean.laporanTanah.catatanJalanMasuk ne null}"> ${actionBean.laporanTanah.catatanJalanMasuk}&nbsp; </c:if>
                    <c:if test="${actionBean.laporanTanah.catatanJalanMasuk eq null}"> Tiada Data </c:if>
                </p>
            </c:if>
        </fieldset>
    </div>
    <br/>
    <div class="subtitle">
        <fieldset class="aras1">
            <s:hidden name="laporanTanah.idLaporan"/>
            <s:hidden name="laporanTanah.permohonan.idPermohonan"/>
            <legend>Keadaan Tanah</legend>
            <c:if test="${edit}">
                <p>
                    <label>Keadaan Tanah :</label>
                    <s:textarea name="laporanTanah.keadaanTanah" cols="50" rows="4"/>
                </p>
                <p>
                    <label>Klasifikasi Tanah :</label>
                    <s:select name="laporanTanah.strukturTanah.kod">
                        <s:option value="">SILA PILIH</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodStrukturTanah}" label="nama" value="kod" />
                    </s:select>
                </p>
                <p>
                    <label>Keadaan Atas Tanah :</label>
                    <s:select name="laporanTanah.kelapanganTanah.kod">
                        <s:option value="">SILA PILIH</s:option>
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
                    <c:if test="${actionBean.laporanTanah.keadaanTanah ne null}"> ${actionBean.laporanTanah.keadaanTanah}&nbsp; </c:if>
                    <c:if test="${actionBean.laporanTanah.keadaanTanah eq null}"> Tiada Data </c:if>
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

                <c:if test="${actionBean.laporanTanah.dilintasTiangElektrik ne null or
                              actionBean.laporanTanah.dilintasTiangTelefon ne null or
                              actionBean.laporanTanah.dilintasLaluanGas ne null or
                              actionBean.laporanTanah.dilintasPaip ne null or
                              actionBean.laporanTanah.dilintasTaliar ne null or
                              actionBean.laporanTanah.dilintasSungai ne null or
                              actionBean.laporanTanah.dilintasParit ne null}">

                      <c:if test="${actionBean.laporanTanah.dilintasTiangElektrik eq 'Y'}">
                          <p>
                              <label>Dilintasi Oleh :</label>
                              Talian Elektrik
                          </p>
                      </c:if>

                      <c:if test="${actionBean.laporanTanah.dilintasTiangTelefon eq 'Y'}">
                          <p>
                              <c:if test="${actionBean.laporanTanah.dilintasTiangElektrik ne 'Y'}">
                                  <label>Dilintasi Oleh :</label>
                              </c:if>
                              <c:if test="${actionBean.laporanTanah.dilintasTiangElektrik eq 'Y'}">
                                  <label>&nbsp;</label>
                              </c:if>
                              Talian Telefon
                          </p>
                      </c:if>

                      <c:if test="${actionBean.laporanTanah.dilintasLaluanGas eq 'Y'}">
                          <p>
                              <c:if test="${actionBean.laporanTanah.dilintasTiangElektrik ne 'Y' and actionBean.laporanTanah.dilintasTiangTelefon ne 'Y' }">
                                  <label>Dilintasi Oleh :</label>
                              </c:if>
                              <c:if test="${actionBean.laporanTanah.dilintasTiangElektrik eq 'Y' or actionBean.laporanTanah.dilintasTiangTelefon eq 'Y' }">
                                  <label>&nbsp;</label>
                              </c:if>
                              Laluan Gas
                          </p>
                      </c:if>

                      <c:if test="${actionBean.laporanTanah.dilintasPaip eq 'Y'}">
                          <p>
                              <c:if test="${actionBean.laporanTanah.dilintasTiangElektrik ne 'Y' and actionBean.laporanTanah.dilintasTiangTelefon ne 'Y' and actionBean.laporanTanah.dilintasLaluanGas ne 'Y' }">
                                  <label>Dilintasi Oleh :</label>
                              </c:if>
                              <c:if test="${actionBean.laporanTanah.dilintasTiangElektrik eq 'Y' or actionBean.laporanTanah.dilintasTiangTelefon eq 'Y' or actionBean.laporanTanah.dilintasLaluanGas eq 'Y' }">
                                  <label>&nbsp;</label>
                              </c:if>
                              Paip Air </p>
                          </c:if>

                      <c:if test="${actionBean.laporanTanah.dilintasTaliar eq 'Y'}">
                          <p>
                              <c:if test="${actionBean.laporanTanah.dilintasTiangElektrik ne 'Y' and actionBean.laporanTanah.dilintasTiangTelefon ne 'Y' and actionBean.laporanTanah.dilintasLaluanGas ne 'Y' and actionBean.laporanTanah.dilintasPaip ne 'Y' }">
                                  <label>Dilintasi Oleh :</label>
                              </c:if>
                              <c:if test="${actionBean.laporanTanah.dilintasTiangElektrik eq 'Y' or actionBean.laporanTanah.dilintasTiangTelefon eq 'Y' or actionBean.laporanTanah.dilintasLaluanGas eq 'Y' or actionBean.laporanTanah.dilintasPaip eq 'Y' }">
                                  <label>&nbsp;</label>
                              </c:if>
                              Tali Air
                          </p>
                      </c:if>

                      <c:if test="${actionBean.laporanTanah.dilintasSungai eq 'Y'}">
                          <p>

                              <c:if test="${actionBean.laporanTanah.dilintasTiangElektrik ne 'Y' and actionBean.laporanTanah.dilintasTiangTelefon ne 'Y' and actionBean.laporanTanah.dilintasLaluanGas ne 'Y' and actionBean.laporanTanah.dilintasPaip ne 'Y' and actionBean.laporanTanah.dilintasTaliar ne 'Y' }">
                                  <label>Dilintasi Oleh :</label>
                              </c:if>
                              <c:if test="${actionBean.laporanTanah.dilintasTiangElektrik eq 'Y' or actionBean.laporanTanah.dilintasTiangTelefon eq 'Y' or actionBean.laporanTanah.dilintasLaluanGas eq 'Y' or actionBean.laporanTanah.dilintasPaip eq 'Y' or actionBean.laporanTanah.dilintasTaliar eq 'Y' }">
                                  <label>&nbsp;</label>
                              </c:if>
                              Sungai
                          </p></c:if>

                      <c:if test="${actionBean.laporanTanah.dilintasParit eq 'Y'}">
                          <p>
                              <c:if test="${actionBean.laporanTanah.dilintasTiangElektrik ne 'Y' and actionBean.laporanTanah.dilintasTiangTelefon ne 'Y' and actionBean.laporanTanah.dilintasLaluanGas ne 'Y' and actionBean.laporanTanah.dilintasPaip ne 'Y' and actionBean.laporanTanah.dilintasTaliar ne 'Y' and actionBean.laporanTanah.dilintasSungai ne 'Y'}">
                                  <label>Dilintasi Oleh :</label>
                              </c:if>
                              <c:if test="${actionBean.laporanTanah.dilintasTiangElektrik eq 'Y' or actionBean.laporanTanah.dilintasTiangTelefon eq 'Y' or actionBean.laporanTanah.dilintasLaluanGas eq 'Y' or actionBean.laporanTanah.dilintasPaip eq 'Y' or actionBean.laporanTanah.dilintasTaliar eq 'Y' or actionBean.laporanTanah.dilintasSungai eq 'Y'}">
                                  <label>&nbsp;</label>
                              </c:if>
                              Parit
                          </p>
                      </c:if>
                </c:if>

                <c:if test="${actionBean.laporanTanah.dilintasTiangElektrik eq null and
                              actionBean.laporanTanah.dilintasTiangTelefon eq null and
                              actionBean.laporanTanah.dilintasLaluanGas eq null and
                              actionBean.laporanTanah.dilintasPaip eq null and
                              actionBean.laporanTanah.dilintasTaliar eq null and
                              actionBean.laporanTanah.dilintasSungai eq null and
                              actionBean.laporanTanah.dilintasParit eq null}"> <p>
                          <label>Dilintasi Oleh :</label>
                          Tiada Data
                      </p>
                </c:if>

            </c:if>

        </fieldset>
    </div>
    <br/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Latar belakang Tanah</legend>
            <c:if test="${edit}">
                <p>
                    <label>Diusahakan :</label>
                    <s:radio name="laporanTanah.usaha" value="Y"/>&nbsp;Ya
                    <s:radio name="laporanTanah.usaha" value="T"/>&nbsp;Tidak
                </p>
                <p>
                    <label>Diusahakan Oleh :</label>
                    <s:text name="laporanTanah.diusaha" size="40" maxlength="100"/>
                </p>
                <p>
                    <label>Tahun Mula Usaha :</label>
                    <s:text name="laporanTanah.tarikhMulaUsaha" maxlength="4" onkeyup="validateNumber(this,this.value);"/>

                </p>
                <p>
                    <label>Jenis Tanaman :</label>
                    <s:text name="laporanTanah.usahaTanam" size="40" maxlength="100"/>
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
                        <s:option value="">SILA PILIH</s:option>
                        <s:option>Sementara</s:option>
                        <s:option>Separuh Kekal</s:option>
                        <s:option>Kekal</s:option>
                        <s:option>Lain-lain</s:option>
                    </s:select>
                </p>

                <p>

                    <label>Rancangan Kerajaan :</label>
                    <s:text name="laporanTanah.rancanganKerajaan" size="40" maxlength="100"/>
                </p>

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

                <p>
                    <label>Diusahakan :</label>
                    <c:if test="${actionBean.laporanTanah.usaha ne null}">
                        <c:if test="${actionBean.laporanTanah.usaha eq 'Y'}">Ya</c:if>
                        <c:if test="${actionBean.laporanTanah.usaha ne 'Y'}">Tidak</c:if>
                    </c:if>
                    <c:if test="${actionBean.laporanTanah.usaha eq null}">Tiada Data</c:if>

                </p>
                <p>
                    <label>Diusahakan Oleh :</label>
                    <c:if test="${actionBean.laporanTanah.diusaha ne null}"> ${actionBean.laporanTanah.diusaha}&nbsp; </c:if>
                    <c:if test="${actionBean.laporanTanah.diusaha eq null}"> Tiada Data </c:if>
                </p>
                <p>
                    <label>Tahun Mula Usaha :</label>
                    <c:if test="${actionBean.laporanTanah.tarikhMulaUsaha ne null}">${actionBean.laporanTanah.tarikhMulaUsaha}&nbsp;</c:if>
                    <c:if test="${actionBean.laporanTanah.tarikhMulaUsaha eq null}"> Tiada Data </c:if>
                </p>
                <p>
                    <label>Jenis Tanaman :</label>
                    <c:if test="${actionBean.laporanTanah.usahaTanam ne null}"> ${actionBean.laporanTanah.usahaTanam}&nbsp; </c:if>
                    <c:if test="${actionBean.laporanTanah.usahaTanam eq null}"> Tiada Data </c:if>
                </p>
                <p>
                    <label>Bangunan :</label>
                    <c:if test="${actionBean.laporanTanah.adaBangunan ne null}">
                        <c:if test="${actionBean.laporanTanah.adaBangunan eq 'Y'}">Ada</c:if>
                        <c:if test="${actionBean.laporanTanah.adaBangunan ne 'Y'}">Tiada</c:if>
                    </c:if>
                    <c:if test="${actionBean.laporanTanah.adaBangunan eq null}">Tiada Data</c:if>

                </p>
                <p>
                    <label>Tahun Dibina :</label>
                    <c:if test="${actionBean.laporanTanah.bangunanTahunDibina ne null}"> ${actionBean.laporanTanah.bangunanTahunDibina}&nbsp; </c:if>
                    <c:if test="${actionBean.laporanTanah.bangunanTahunDibina eq null}"> Tiada Data </c:if>
                </p>
                <p>
                    <label>Diduduki :</label>
                    <c:if test="${actionBean.laporanTanah.bangunanDidiami ne null}">
                        <c:if test="${actionBean.laporanTanah.bangunanDidiami eq 'Y'}">Ya</c:if>
                        <c:if test="${actionBean.laporanTanah.bangunanDidiami ne 'Y'}">Tidak</c:if>
                    </c:if>
                    <c:if test="${actionBean.laporanTanah.bangunanDidiami eq null}">Tiada Data</c:if>

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
    <br/>
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
                                <s:text name="laporanTanah.sempadanUtaraNoLot" maxlength="10"/>
                            </td>
                            <td>
                                <s:text name="laporanTanah.sempadanUtaraKegunaan" maxlength="50" size="50"/>
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
                                <s:text name="laporanTanah.sempadanSelatanNoLot" maxlength="10"/>
                            </td>
                            <td>
                                <s:text name="laporanTanah.sempadanSelatanKegunaan" maxlength="50" size="50"/>
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
                                <s:text name="laporanTanah.sempadanTimurNoLot" maxlength="10"/>
                            </td>
                            <td>
                                <s:text name="laporanTanah.sempadanTimurKegunaan" maxlength="50" size="50"/>
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
                                <s:text name="laporanTanah.sempadanBaratNoLot" maxlength="10"/>
                            </td>
                            <td>
                                <s:text name="laporanTanah.sempadanBaratKegunaan" maxlength="50" size="50"/>
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
                                <c:if test="${actionBean.laporanTanah.sempadanUtaraMilikKerajaan ne null}">
                                    <c:if test="${actionBean.laporanTanah.sempadanUtaraMilikKerajaan ne 'T'}">Kerajaan</c:if>
                                    <c:if test="${actionBean.laporanTanah.sempadanUtaraMilikKerajaan eq 'T'}">Milik</c:if>
                                </c:if>
                                <c:if test="${actionBean.laporanTanah.sempadanUtaraMilikKerajaan eq null}">Tiada Data</c:if>
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
                                <c:if test="${actionBean.laporanTanah.sempadanSelatanMilikKerajaan ne null}">
                                    <c:if test="${actionBean.laporanTanah.sempadanSelatanMilikKerajaan ne 'T'}">Kerajaan</c:if>
                                    <c:if test="${actionBean.laporanTanah.sempadanSelatanMilikKerajaan eq 'T'}">Milik</c:if>
                                </c:if>
                                <c:if test="${actionBean.laporanTanah.sempadanSelatanMilikKerajaan eq null}">Tiada Data</c:if>
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
                                <c:if test="${actionBean.laporanTanah.sempadanTimurMilikKerajaan ne null}">
                                    <c:if test="${actionBean.laporanTanah.sempadanTimurMilikKerajaan ne 'T'}">Kerajaan</c:if>
                                    <c:if test="${actionBean.laporanTanah.sempadanTimurMilikKerajaan eq 'T'}">Milik</c:if>
                                </c:if>
                                <c:if test="${actionBean.laporanTanah.sempadanTimurMilikKerajaan eq null}">Tiada Data</c:if>
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
                                <c:if test="${actionBean.laporanTanah.sempadanBaratMilikKerajaan ne null}">
                                    <c:if test="${actionBean.laporanTanah.sempadanBaratMilikKerajaan ne 'T'}">Kerajaan</c:if>
                                    <c:if test="${actionBean.laporanTanah.sempadanBaratMilikKerajaan eq 'T'}">Milik</c:if>
                                </c:if>
                                <c:if test="${actionBean.laporanTanah.sempadanBaratMilikKerajaan eq null}">Tiada Data</c:if>
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
    <br/>
    <div class="subtitle">
        <fieldset class="aras1">

            <legend>Ulasan Pegawai</legend>
            <c:if test="${edit}">
                <p>
                    <label>Ulasan :</label>
                    <s:textarea name="fasaPermohonan.ulasan" cols="70" rows="4"/>

                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:button name="simpanLaporanTanah" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    <s:reset name="" value="Isi Semula" class="btn"/>&nbsp;
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



