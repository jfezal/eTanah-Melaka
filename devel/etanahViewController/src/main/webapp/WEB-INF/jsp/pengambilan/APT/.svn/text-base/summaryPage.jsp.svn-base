<%-- 
    Document   : summaryPage
    Created on : Jan 16, 2020, 3:56:32 PM
    Author     : zipzap
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    $(document).ready(function() {

        var kodNegeri = $('#kodNegeri').val();
        var stageId = $('#stageId').val();
        var kodUrusan = $('#kodUrusan').val();
        var resultCase = $('#keputusanKes').val();

        if (kodNegeri == '04' && (stageId == '17ArahanMaklumanKpsn')) {
            if (kodUrusan == 'PTPT') {
                if ($("#keputusanId").val() == 'PV') {
                    $(".warning").html("Arahan : Terdapat " + $("#keputusanName").val() + ". Sila rujuk Tab Laporan Kerosakan Tanah");
                } else {
                    $(".warning").html("Arahan : " + $("#keputusanName").val() + ". Tiada perubahan");
                }
            }
        }
        else if (resultCase == "T") {
            if (kodUrusan == 'JMRE') {
                $('#page_id_006').hide();//hide tab draf MMK
            }
        }

    });
</script>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form  beanclass="etanah.view.stripes.pengambilan.share.common.summaryPermohonan">
    <!--E:\Etanah\devel\etanahViewController\src\main\java\etanah\view\stripes\pengambilan\share\common\summaryPermohonanActioanBean.java-->

    <s:errors/>
    <s:messages/>
    <s:hidden name="kodKeputusan.kod" id="keputusanId" />
    <s:hidden name="kodKeputusan.nama" id="keputusanName" />
    <s:hidden name="kodNegeri" id="kodNegeri"/>
    <s:hidden name="stageId" id="stageId"/>
    <s:hidden name="permohonan.kodUrusan.kod" id="kodUrusan"/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Permohonan </legend>
            <p>
                <label for="Permohonan">Permohonan :</label>
                <font style="text-transform:uppercase;"> ${actionBean.namaUrusan}</font>&nbsp;
            </p>
            <p>
                <label for="ID Permohonan">ID Permohonan :</label>
                ${actionBean.permohonan.idPermohonan}&nbsp;
            </p>
        </fieldset>
    </div>

    <%---------------FOR URUSAN PMMAT CONSENT------------------%>
    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PMMAT' && edit}">
        <br/>
        <div class="subtitle">
            <fieldset class="aras1">
                <s:hidden name="permohonanRujukanLuar.idRujukan"/>
                <legend>Maklumat Perintah</legend>
                <p>
                    <label> <font color="red">*</font>Jenis Perintah :</label>
                            <s:select name="permohonanRujukanLuar.catatan">
                                <s:option value="">Sila Pilih</s:option>
                        <s:option>PERINTAH PENTADBIR TANAH</s:option>
                        <s:option>PERINTAH MAHKAMAH</s:option>
                    </s:select>
                </p>
                <p>
                    <label><font color="red">*</font>Tarikh Perintah :</label>
                            <s:text name="tarikhPerintah" id="datepicker" class="datepicker"/>
                </p>
                <p>
                    <label><font color="red">*</font>Nombor Perintah :</label>
                            <s:text name="permohonanRujukanLuar.noRujukan" size="20"/>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:button name="simpanPermohonanRujukanLuar" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </p>
            </fieldset>
        </div>
    </c:if>

    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PMMAT' && !edit}">
        <br/>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Perintah</legend>
                <p>
                    <label>Jenis Perintah :</label>
                    ${actionBean.permohonanRujukanLuar.catatan}
                </p>
                <p>
                    <label>Tarikh Perintah :</label>
                    ${actionBean.tarikhPerintah}
                </p>
                <p>
                    <label>Nombor Perintah :</label>
                    ${actionBean.permohonanRujukanLuar.noRujukan}
                </p>

            </fieldset>
        </div>
    </c:if>

    <%---------------FOR URUSAN BANTAHAN TANAH ADAT------------------%>
    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'BTADT' && edit}">
        <br/>
        <div class="subtitle">
            <fieldset class="aras1">
                <s:hidden name="permohonanUrusan.idUrusan"/>
                <legend>Maklumat Permohonan Terdahulu</legend>
                <p>
                    <label> <font color="red">*</font>Nama Permohonan :</label>
                            <s:select name="permohonanUrusan.catatan">
                                <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodUrusanBantahanTanahAdat}" label="nama" value="kod"/>
                    </s:select>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:button name="simpanPermohonanUrusan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </p>
            </fieldset>
        </div>
    </c:if>
    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'BTADT' && !edit}">
        <br/>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Permohonan Terdahulu</legend>
                <p>
                    <label>Nama Permohonan :</label>
                    <font style="text-transform:uppercase;"> ${actionBean.kodUrusan.nama}</font>&nbsp;
                </p>
            </fieldset>
        </div>
    </c:if>
    <br/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Penyerah</legend>
            <p>
                <label>Nama :</label>
                <font style="text-transform:uppercase;"> ${actionBean.permohonan.penyerahNama}</font>&nbsp;
            </p>
            <p>
                <label>Jenis Penyerah :</label>
                <font style="text-transform:uppercase;">
                    <c:if test="${actionBean.permohonan.kodPenyerah.nama ne null}"> ${actionBean.permohonan.kodPenyerah.nama}&nbsp; </c:if>
                    <c:if test="${actionBean.permohonan.kodPenyerah eq null && actionBean.permohonan.penyerahJenisPengenalan ne null}">
                        <c:if test="${actionBean.permohonan.penyerahJenisPengenalan.kod eq 'B'}"> INDIVIDU </c:if><%--K/P Baru --%>
                        <c:if test="${actionBean.permohonan.penyerahJenisPengenalan.kod eq 'D'}"> SYARIKAT </c:if><%-- Pendaftaran--%>
                        <c:if test="${actionBean.permohonan.penyerahJenisPengenalan.kod eq 'F'}">  INDIVIDU</c:if><%-- Paksa--%>
                        <c:if test="${actionBean.permohonan.penyerahJenisPengenalan.kod eq 'I'}">  INDIVIDU</c:if><%--Polis --%>
                        <c:if test="${actionBean.permohonan.penyerahJenisPengenalan.kod eq 'L'}"> INDIVIDU </c:if><%-- K/P Lama--%>
                        <c:if test="${actionBean.permohonan.penyerahJenisPengenalan.kod eq 'N'}"> SYARIKAT </c:if><%--Bank --%>
                        <c:if test="${actionBean.permohonan.penyerahJenisPengenalan.kod eq 'P'}"> INDIVIDU </c:if><%--Pasport --%>
                        <c:if test="${actionBean.permohonan.penyerahJenisPengenalan.kod eq 'S'}"> SYARIKAT </c:if><%--Syarikat --%>
                        <c:if test="${actionBean.permohonan.penyerahJenisPengenalan.kod eq 'U'}"> PERTUBUHAN </c:if><%--Pertubuhan --%>
                        <c:if test="${actionBean.permohonan.penyerahJenisPengenalan.kod eq 'X'}"> TIADA </c:if><%-- Tiada --%>
                        <c:if test="${actionBean.permohonan.penyerahJenisPengenalan.kod eq 'Z'}"> Badan Kerajaan </c:if><%--Badan Kerajaan --%>
                        <c:if test="${actionBean.permohonan.penyerahJenisPengenalan.kod eq 'O'}"> Tidak Berkenaan </c:if><%--Tidak Berkenaan --%>
                    </c:if>
                </font>
            </p>
            <c:if test="${actionBean.permohonan.penyerahAlamat1 ne null}">
                <p>
                    <label>Alamat :</label>
                    <font style="text-transform:uppercase;"> ${actionBean.permohonan.penyerahAlamat1}</font>&nbsp;
                </p>
            </c:if>
            <c:if test="${actionBean.permohonan.penyerahAlamat2 ne null}">
                <p>
                    <label> &nbsp;</label>
                    <font style="text-transform:uppercase;">${actionBean.permohonan.penyerahAlamat2}</font> &nbsp;
                </p>
            </c:if>
            <c:if test="${actionBean.permohonan.penyerahAlamat3 ne null}">
                <p>
                    <label> &nbsp;</label>
                    <font style="text-transform:uppercase;"> ${actionBean.permohonan.penyerahAlamat3} </font> &nbsp;
                </p>
            </c:if>
            <c:if test="${actionBean.permohonan.penyerahAlamat4 ne null}">
                <p>
                    <label>Bandar :</label>
                    <font style="text-transform:uppercase;">${actionBean.permohonan.penyerahAlamat4}</font>&nbsp;
                </p>
            </c:if>
            <p>
                <label>Poskod :</label>
                ${actionBean.permohonan.penyerahPoskod}&nbsp;
            </p>
            <p>
                <label>Negeri :</label>
                <font style="text-transform:uppercase;">${actionBean.permohonan.penyerahNegeri.nama} </font>&nbsp;
            </p>
        </fieldset>

        <fieldset class="aras1" id="locate">
            <legend>
                Maklumat Tanah

            </legend>
            <c:if test="${edit}">

                <label>Meter Persegi :</label>${actionBean.amountMH}
                </p>
                <p>
                    <label>Hektar :</label><fmt:formatNumber maxFractionDigits="3" value="${actionBean.convH}"/>
                </p>--%>
                <p align="center">

                    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanListNew}" pagesize="30" cellpadding="0" cellspacing="0"
                                   requestURI="/pengambilan/maklumat_hakmilikpengambilan" id="line">
                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                        <c:if test="${line.hakmilik ne null}">
                            <display:column title="ID Hakmilik" class="popup hakmilik${line_rowNum}">${line.hakmilik.idHakmilik}</display:column>
                        </c:if>
                        <c:if test="${line.hakmilik eq null}">
                            <display:column title="ID Hakmilik">-</display:column>
                        </c:if>
                        <c:if test="${line.hakmilik ne null}">
                            <display:column title="No Lot/No PT" >${line.hakmilik.lot.nama}${line.hakmilik.noLot}</display:column>
                        </c:if>
                        <c:if test="${line.hakmilik eq null}">
                            <display:column title="No Lot/No PT" >${line.lot.nama}${line.noLot}</display:column>
                        </c:if>
                        <c:if test="${line.hakmilik ne null}">
                            <display:column title="Luas">
                                <s:hidden formatPattern="#0.0000" name="luas1" value="${line.hakmilik.luas}" id="luas${line_rowNum-1}" />
                                <s:hidden name="unitLuas" value="${line.hakmilik.kodUnitLuas.nama}" id="unitLuas${line_rowNum-1}" />
                                <fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp;
                                <c:if test="${line.hakmilik.kodUnitLuas.nama eq 'Meter Persegi'}">mp</c:if>
                                <c:if test="${line.hakmilik.kodUnitLuas.nama eq 'Hektar'}">Ha</c:if>
                            </display:column>
                        </c:if>
                        <c:if test="${line.hakmilik eq null}">
                            <display:column title="Luas">
                                <s:hidden formatPattern="#0.0000" name="luas1" value="${line.luasBaru}" id="luas${line_rowNum-1}" />
                                <s:hidden name="unitLuas" value="${line.kodUnitLuasBaru.nama}" id="unitLuas${line_rowNum-1}" />
                                <fmt:formatNumber pattern="#,##0.0000" value="${line.luasBaru}"/>&nbsp;
                                <c:if test="${line.kodUnitLuasBaru.nama eq 'Meter Persegi'}">mp</c:if>
                                <c:if test="${line.kodUnitLuasBaru.nama eq 'Hektar'}">Ha</c:if>
                            </display:column>
                        </c:if>
                        <c:if test="${line.hakmilik ne null}">
                            <display:column title="Daerah" class="daerah">${line.hakmilik.daerah.nama}</display:column>
                        </c:if>
                        <c:if test="${line.hakmilik eq null}"> 
                            <display:column title="Daerah" class="daerah">${actionBean.kodDaerah}</display:column>
                        </c:if>
                        <c:if test="${line.hakmilik ne null}">
                            <display:column title="Bandar/Pekan/Mukim">${line.hakmilik.bandarPekanMukim.nama} ${line.hakmilik.seksyen.nama}</display:column><%--${fn:toLowerCase}--%>
                        </c:if>
                        <c:if test="${line.hakmilik eq null}">
                            <display:column title="Bandar/Pekan/Mukim">${line.bandarPekanMukimBaru.nama} ${line.kodSeksyen.nama}</display:column>
                        </c:if>

                        <display:column title="Baki Luas">
                            <c:if test="${line.hakmilik ne null}">
                                <c:if test="${line.luasTerlibat ne null}">
                                    <c:set value="${line.luasTerlibat}" var="a"/>
                                    <c:set value="${line.hakmilik.luas}" var="b"/>
                                    <fmt:formatNumber pattern="#,##0.0000" value="${b-a}"/>
                                </c:if>
                                <c:if test="${line.luasTerlibat eq null}">0</c:if>
                            </c:if>
                            <c:if test="${line.hakmilik eq null}">
                                <c:if test="${line.luasBaru ne null}">
                                    <c:set value="${line.luasTerlibat}" var="a"/>
                                    <c:set value="${line.luasBaru}" var="b"/>
                                    <fmt:formatNumber pattern="#,##0.0000" value="${b-a}"/>
                                </c:if>
                                <c:if test="${line.luasBaru eq null}">0</c:if>
                            </c:if>
                        </display:column>
                        <%--hakmilik.kegunaanTanah.nama--%>
                        <display:column property="hakmilik.syaratNyata.syarat" title="Kegunaan Tanah" />
                    </display:table>
                    <br>
                    <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahBaru();"/>&nbsp;
                    <s:button name="simpanHakmilik" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>


                </c:if>

                <br>
            <p align="center">
                <display:table class="tablecloth" name="${actionBean.listmaklumatTanahPengambilanForms}" pagesize="30" cellpadding="0" cellspacing="0"
                               requestURI="/pengambilan/maklumat_hakmilikpengambilan" id="line">
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <c:if test="${line.mohonHakmilik.hakmilik ne null}">
                        <display:column title="ID Hakmilik">${line.mohonHakmilik.hakmilik.idHakmilik}</display:column>
                    </c:if>
                    <c:if test="${line.mohonHakmilik.hakmilik eq null}">
                        <display:column title="ID Hakmilik">Tidak Dapat Dikesan</display:column>
                    </c:if>
                    <c:if test="${line.mohonHakmilik.hakmilik ne null}">
                        <display:column title="No Lot/No PT" >${line.mohonHakmilik.hakmilik.lot.nama}${line.mohonHakmilik.noLot}</display:column>
                    </c:if>
                    <c:if test="${line.mohonHakmilik.hakmilik eq null}">
                        <display:column title="No Lot/No PT" >${line.mohonHakmilik.lot.nama}${line.mohonHakmilik.noLot}</display:column>
                    </c:if>
                    <display:column title="Luas">
                        <s:hidden formatPattern="#0.0000" name="luas1" value="${line.mohonHakmilik.luasTerlibat}" id="luas${line_rowNum-1}" />
                        <s:hidden name="unitLuas" value="${line.mohonHakmilik.kodUnitLuas.nama}" id="unitLuas${line_rowNum-1}" />
                        <fmt:formatNumber pattern="#,##0.0000" value="${line.mohonHakmilik.luasTerlibat}"/>&nbsp;
                        <c:if test="${line.mohonHakmilik.kodUnitLuas.nama eq 'Meter Persegi'}">mp</c:if>
                        <c:if test="${line.mohonHakmilik.kodUnitLuas.nama eq 'Hektar'}">Ha</c:if>
                    </display:column>
                    <%-- </c:if>--%>
                    <c:if test="${line.mohonHakmilik.hakmilik ne null}">
                        <display:column title="Daerah" class="daerah">${line.mohonHakmilik.hakmilik.daerah.nama}</display:column>
                    </c:if>
                    <c:if test="${line.mohonHakmilik.hakmilik eq null}"> 
                        <display:column title="Daerah" class="daerah">${line.mohonHakmilik.cawangan.daerah.nama}</display:column>
                    </c:if>
                    <c:if test="${line.mohonHakmilik.hakmilik ne null}">
                        <display:column title="Bandar/Pekan/Mukim">${line.mohonHakmilik.hakmilik.bandarPekanMukim.nama} ${line.mohonHakmilik.hakmilik.seksyen.nama}</display:column><%--${fn:toLowerCase}--%>
                    </c:if>
                    <c:if test="${line.mohonHakmilik.hakmilik eq null}">
                        <display:column title="Bandar/Pekan/Mukim">${line.mohonHakmilik.bandarPekanMukimBaru.nama} ${line.mohonHakmilik.kodSeksyen.nama}</display:column>
                    </c:if>

                    <display:column title="Luas Diambil">
                        <fmt:formatNumber pattern="#,##0.0000" value="${line.luasAmbil}"/>&nbsp;
                        <c:if test="${line.mohonHakmilik.kodUnitLuas.nama eq 'Meter Persegi'}">mp</c:if>
                        <c:if test="${line.mohonHakmilik.kodUnitLuas.nama eq 'Hektar'}">Ha</c:if>

                    </display:column>

                    <display:column title="Baki Luas">

                        <c:if test="${line.mohonHakmilik.luasTerlibat ne null}">
                            <c:set value="${line.luasAmbil}" var="a"/>
                            <c:set value="${line.mohonHakmilik.luasTerlibat}" var="b"/>
                            <fmt:formatNumber pattern="#,##0.0000" value="${b-a}"/>&nbsp;
                            <c:if test="${line.mohonHakmilik.kodUnitLuas.nama eq 'Meter Persegi'}">mp</c:if>
                            <c:if test="${line.mohonHakmilik.kodUnitLuas.nama eq 'Hektar'}">Ha</c:if>
                        </c:if>
                        <c:if test="${line.mohonHakmilik.luasTerlibat eq null}">0</c:if>
                        <%-- </c:if>--%>
                    </display:column>
                    <%--hakmilik.kegunaanTanah.nama--%>
                    <display:column property="mohonHakmilik.hakmilik.syaratNyata.syarat" title="Kegunaan Tanah" />
                </display:table>
        </fieldset>

        <fieldset class="aras1">
            <legend>
                Senarai Pemohon 
            </legend>


            <%--second layer start--%>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.pemohonList}" cellpadding="0" cellspacing="0" id="line1"
                               requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">

                    <display:column title="Bil" sortable="true">${line1_rowNum}
                        <s:hidden name="x" class="x${line1_rowNum -1}" value="${line1.pihak.idPihak}"/>
                    </display:column>
                    <display:column  title="Nama" >
                        <%--<a href="#" onclick="viewPihak('${line1.pihak.idPihak}', 'pemohon');return false;">${line1.pihak.nama}</a>--%>
                        ${line1.pihak.nama}
                    </display:column>
                    <display:column property="pihak.noPengenalan" title="No. Pengenalan/No.Syarikat" />${line1.pihak.noPengenalan}
                    <display:column title="Alamat 2">${line1.pihak.alamat1}
                        <c:if test="${line1.pihak.alamat2 ne null}">,</c:if>
                        ${line1.pihak.alamat2}
                        <c:if test="${line1.pihak.alamat3 ne null}"> </c:if>
                        ${line1.pihak.alamat3}
                        <c:if test="${line1.pihak.alamat4 ne null}">,</c:if>
                        ${line1.pihak.alamat4}
                        <c:if test="${line1.pihak.poskod ne null}">,</c:if>
                        ${line1.pihak.poskod}
                        <c:if test="${line1.pihak.negeri.kod ne null}">,</c:if>
                        <font style="text-transform:uppercase;">${line1.pihak.negeri.nama}</font>
                        </display:column>
                        <display:column property="pihak.noTelefon1" title="No.Telefon" />
                        <display:column property="pihak.noTelefon2" title="No.Fax" />
                        <display:column property="pihak.email" title="Email" />
                        <%--<c:if test="${edit}">--%>
                        <%--<display:column title="Kemaskini">
                           <p align="center">
                               <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                    onclick="dopopup('${line_rowNum -1}','${actionBean.p.kodUrusan.kod}');return false;" onmouseover="this.style.cursor='pointer';">
                           </p>
                       </display:column>--%>
                        <%--</c:if>--%>
                    </display:table>
            </div>
            <p align="center">
                <%--<c:if test="${edit}">--%>
                <c:if test="${fn:length(actionBean.pemohonList) < 0}" >
                    <s:button class="btn" value="Tambah Baru" name="new" id="new" onclick="addNewPemohon();"/>&nbsp;
                    <label>&nbsp;</label>

                </c:if>
                <%--<c:if test="${edit && fn:length(actionBean.pemohonList) > 0}" >
                    <s:button class="btn" value="Hapus" name="new" id="new" onclick="removeMultiplePemohon();"/>&nbsp;
                </c:if>--%>
            </p>
            <%--</fieldset>--%>
            <br/>

        </fieldset>

        <fieldset class="aras1">
            <legend>Maklumat Pengambilan</legend>
            <c:if test="${edit}">
                <%--  <c:if test="${actionBean.permohonan.kodUrusan.kod eq '831A'}">
                      <p>
                   <label for="status permohonan">Jenis Permohonan :</label>
                   <s:radio name="" value="Segera"/>&nbsp;Segera
                   <s:radio name="" value="Tidak Segera"/>&nbsp;Tidak Segera
                   </p>
                  </c:if>--%>

                <p>
                    <label for="Maklumat Pengambilan">No Rujukan Surat :</label>
                    <s:text name="noRujukan" size="30" id="norujukan" />
                </p>
                <%-- <p>
                     <label for="Maklumat Pengambilan">Kod Rujukan :</label>
                     <s:select name="kodRujukan.kod" style="width:400px" id="jabatan">
                                         <s:option value="">Sila Pilih</s:option>
                                         <s:option value="FL">Fail</s:option>
                                         <s:option value="NF">No.Fail</s:option>
                                     </s:select>
                 </p>--%>
                <p>
                    <label for="Trh_Surat_ Memohon">Tarikh Surat Memohon :</label>
                    <s:text name="tarikhRujukan" id="datepicker" class="datepicker" />
                </p>

                <%--<p>
                    <label for="Maksud_Pengambilan">Maksud Pengambilan :</label>
                   <s:textarea name="permohonan.sebab" rows="4" cols="35" id="maksudpengambilan"/>
                </p>--%>
                <p>
                    <label for="Maksud_Pengambilan">Tujuan Pengambilan :</label>
                    <s:textarea name="permohonan.sebab" rows="4" cols="35" id="maksudpengambilan"/>
                </p>
                <p>
                    <label for="Tanah Asal">Setelah Pengambilan Tanah :</label>
                    <s:checkbox name="hakmilikSambungan" value="HS"/>&nbsp; Hakmilik<br>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:checkbox name="tanahRizab" value="TR"/>&nbsp; Rizab<br>

                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:checkbox name="tanahKerajaan" value="TK"/>&nbsp; Tanah Kerajaan<br>

                </p>
                <br>
                <p>
                    <label>&nbsp;</label>
                    <s:button name="savePengambilanInfo" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"  />
                    <%-- onclick="save1(this.name, this.form);"--%>
                </p>
                <%-- ${actionBean.hakmilikSambungan}
                 ${actionBean.tanahRizab}
                 ${actionBean.tanahKerajaan}--%>
                <c:if test="${actionBean.statusLepasPengambilan.kodStatusTanahLepasPengambilan eq 'HS'}">${actionBean.hakmilikSambungan}yyy</c:if>
                <c:if test="${actionBean.statusLepasPengambilan.kodStatusTanahLepasPengambilan eq 'TR'}">${actionBean.tanahRizab}rrrr</c:if>
                <c:if test="${actionBean.statusLepasPengambilan.kodStatusTanahLepasPengambilan eq 'TK'}">${actionBean.tanahKerajaan}kkk</c:if>

            </c:if>
            <c:if test="${!edit}">
                <%--<c:if test="${actionBean.permohonan.kodUrusan.kod eq '831A'}">
                <p>
                  <label for="Maklumat Pengambilan">Jenis Permohonan :</label>Segera&nbsp;
                </p>
                </c:if>--%>
                <p>
                    <label for="Maklumat Pengambilan">No Rujukan Surat :</label><c:if test="${actionBean.noRujukan ne null}">${actionBean.noRujukan}</c:if>&nbsp;

                    </p>
                    <p>
                        <label for="Trh_Surat_ Memohon">Tarikh Surat Memohon :</label><c:if test="${actionBean.tarikhRujukan ne null}">${actionBean.tarikhRujukan} </c:if> &nbsp;

                    </p>
                    <p>
                        <label for="Maksud_Pengambilan">Tujuan Pengambilan :</label><c:if test="${actionBean.permohonanPengambilan.tujuanPermohonan ne null}"> ${actionBean.permohonanPengambilan.tujuanPermohonan}</c:if> &nbsp;

                    <%--<s:textarea name="permohonan.sebab" rows="4" cols="35" --%>
                </p>
                <p>
                    <label for="Tanah Asal">Setelah Pengambilan Balik :</label>
                    <c:if test="${actionBean.hakmilikSambungan eq 'HS'}">Hakmilik Sambungan,</c:if>
                    <c:if test="${actionBean.tanahRizab eq 'TR'}">Tanah Rizab,</c:if>
                    <c:if test="${actionBean.tanahKerajaan eq 'TK'}">Tanah Kerajaan</c:if><br>

                        <label for="Tanah Asal">Borang I ( Kedesakan ) : </label>  
                    <c:if test="${actionBean.permohonanPengambilan.kedesakan eq 'Y'}">Terdapat Kedesakan</c:if> 
                    <c:if test="${actionBean.permohonanPengambilan.kedesakan eq 'T'}">Tiada Kedesakan</c:if>
                    <%-- ${actionBean.hakmilikSambungan}
               ${actionBean.tanahRizab}
               ${actionBean.tanahKerajaan}--%>
                </p>

            </c:if>      
        </fieldset>
    </div>
</s:form>
