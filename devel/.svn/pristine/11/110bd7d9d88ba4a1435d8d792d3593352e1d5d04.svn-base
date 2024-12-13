<%--
    Document   : edit_kod
    Created on : Apr 15, 2010, 10:45:46 AM
    Author     : wan.fairul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Utiliti Kod</title>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/pub/css/formdesign.css">
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
        <script src="<%= request.getContextPath()%>/pub/js/ui.datepicker-ms.js" type="text/javascript"></script>
        <script type="text/javascript">

//            $(document).ready(function () {
//                $("#senarai_kod input:text").each(function (el) {
//                    $(this).blur(function () {
//                        $(this).val($(this).val().toUpperCase());
//                    });
//                });
//            });


            function validateNumber(elmnt, content) {
                //if it is character, then remove it..
                if (isNaN(content)) {
                    elmnt.value = removeNonNumeric(content);
                    return;
                }
            }



        </script>
    </head>
    <body>
        <s:errors/>
        <s:messages/>
        <div class="subtitle">
            <s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
            <s:form beanclass="etanah.view.daftar.utiliti.UtilitiKodActionBean" name="form1" id="senarai_kod">
                <fieldset class="aras1">

                    <legend>
                        Edit Kod
                    </legend>
                    <br>
                    <c:if test="${actionBean.table ne 'KodUrusan' && actionBean.table ne 'KodKadarBayaran'
                                  && actionBean.table ne 'KodCawangan' && actionBean.table ne 'KodKadarPremium'
                                  && actionBean.table ne 'KodDokumen' && actionBean.table ne 'UrusanDokumen'}">
                          <p>
                              <label>Kod :</label> ${actionBean.o.kod}
                              <s:hidden name="kod" value="${actionBean.o.kod}"/>
                          </p>
                    </c:if>
                    <c:if test="${actionBean.table eq 'KodKegunaanPetakAksesori' || actionBean.table eq 'KodKegunaanPetak'
                                  || actionBean.table eq 'KodBandarPekanMukim' || actionBean.table eq 'KodSeksyen'
                                  || actionBean.table eq 'KodKegunaanBangunan' || actionBean.table eq 'KodWarganegara' 
                                  || actionBean.table eq 'KodDaerah' || actionBean.table eq 'KodPenubuhanSyarikat'}">
                          <p>
                              <label>Nama lama :</label><s:text name="o.nama" size="40" readonly="readonly"/>&nbsp;
                          </p>
                          <p>
                              <label>Nama baharu :</label><s:text name="nama" size="40"/>&nbsp;
                          </p>
                    </c:if>
                    <c:if test="${actionBean.table ne 'KodKegunaanPetakAksesori' && actionBean.table ne 'KodKegunaanPetak' 
                                  && actionBean.table ne 'KodBandarPekanMukim' && actionBean.table ne 'KodSeksyen'
                                  && actionBean.table ne 'KodKegunaanBangunan'  && actionBean.table ne 'KodWarganegara'
                                  && actionBean.table ne 'KodDaerah' && actionBean.table ne 'KodUrusan'
                                  && actionBean.table ne 'KodKadarBayaran' && actionBean.table ne 'KodBank'
                                  && actionBean.table ne 'KodKadarPremium' && actionBean.table ne 'KodKadarPremium'
                                  && actionBean.table ne 'KodDokumen' && actionBean.table ne 'UrusanDokumen' }" >
                        <c:if test="${actionBean.table ne 'KodCawangan'}">
                            <p>
                                <label>Nama :</label><s:text name="o.nama" size="40"/>&nbsp;
                            </p>
                        </c:if>
                        <c:if test="${actionBean.table eq 'KodAgensi'}">
                            <p>
                                <label>Kod Kementerian :</label><s:text name="o.kodKementerian" size="40"/>&nbsp;
                            </p>
                            <p>
                                <label for="alamat">Alamat  :</label>
                                <s:text name="o.alamat1" id="alamat1" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p>
                                <label> &nbsp;</label>
                                <s:text name="o.alamat2" id="alamat2" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p>
                                <label> &nbsp;</label>
                                <s:text name="o.alamat3" id="alamat3" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p>
                                <label>  &nbsp;</label>
                                <s:text name="o.alamat4" id="alamat4" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p>
                                <label>Poskod :</label>
                                <s:text name="o.poskod"  size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                            </p>
                            <p>
                                <label>Negeri :</label>
                                <s:select name="o.kodNegeri.kod" id="negeri" >
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                                </s:select>
                            </p>
                        </c:if>
                        <c:if test="${actionBean.table eq 'KodAkaun'}">
                            <p>
                                <label>Kategori Akaun :</label>
                                <s:select name="o.kategoriAkaun.kod">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodKategoriAkaun}" label="nama" value="kod"/>
                                </s:select>
                            </p>
                        </c:if>
                        <c:if test="${actionBean.table eq 'KodBandarPekanMukim'}">
                            <p>
                                <label>Daerah :</label><s:text name="o.daerah.nama" size="40"/>&nbsp;
                            </p>
                            <p>
                                <label>Bandar Pekan Mukim  :</label>
                                <s:text name="o.bandarPekanMukim" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p>
                                <label>Kod Tanah  :</label>
                                <s:select name="o.kodTanah.kod">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodTanah}" label="nama" value="kod"/>
                                </s:select>
                            </p>
                        </c:if>
                        <c:if test="${actionBean.table eq 'KodBangsa'}">
                            <p>
                                <label>Untuk Individu :</label>
                                <s:radio name="o.untukIndividu" value="Y"></s:radio> Ya
                                <s:radio name="o.untukIndividu" value="N" ></s:radio> Tidak
                                </p>
                        </c:if>
                        <c:if test="${actionBean.table eq 'KodBilik'}">
                            <p>
                                <label>Kod Cawangan :</label>
                                <s:select name="o.cawangan.kod">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodCawangan}" label="name" value="kod"/>
                                </s:select>
                            </p>
                        </c:if>
                        <c:if test="${actionBean.table eq 'KodCaraPenghantaran'}">
                            <p>
                                <label>Perihal  :</label>
                                <s:text name="o.perihal" size="40" maxlength="40" />
                            </p>
                        </c:if>
                        <c:if test="${actionBean.table eq 'KodHakmilik'}">
                            <p>
                                <label>Kod Dokumen :</label>
                                <s:select name="o.kodDokumen.kod" >
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodDokumen}" label="nama" value="kod"/>
                                </s:select>
                            </p>
                            <p>
                                <label>Milik Daerah  :</label>
                                <s:radio name="o.milikDaerah" value="Y"></s:radio> Ya
                                <s:radio name="o.milikDaerah" value="N" ></s:radio> Tidak
                                </p>
                        </c:if>
                        <c:if test="${actionBean.table eq 'KodJabatan'}">
                            <p>
                                <label>Warna Modul  :</label>
                                <s:text name="o.warnaModul" size="40" maxlength="40" />
                            </p>
                            <p>
                                <label>Akronim  :</label>
                                <s:text name="o.akronim" size="3" maxlength="3" />
                            </p>
                            <p>
                                <label for="alamat">Alamat  :</label>
                                <s:text name="o.alamat1" id="alamat1" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p>
                                <label> &nbsp;</label>
                                <s:text name="o.alamat2" id="alamat2" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p>
                                <label> &nbsp;</label>
                                <s:text name="o.alamat3" id="alamat3" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p>
                                <label>  &nbsp;</label>
                                <s:text name="o.alamat4" id="alamat4" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            </p>
                            <p>
                                <label>Poskod :</label>
                                <s:text name="o.poskod"  size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                            </p>
                            <p>
                                <label>Negeri :</label>
                                <s:select name="o.kodNegeri.kod" id="negeri" >
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                                </s:select>
                            </p>
                            <p>
                                <label>Nombor Telefon 1 :</label>
                                <s:text name="o.noTelefon1"  size="20" maxlength="20" onkeyup="validateNumber(this,this.value);"/>
                            </p>
                            <p>
                                <label>Nombor Telefon 2 :</label>
                                <s:text name="o.noTelefon2"  size="20" maxlength="20" onkeyup="validateNumber(this,this.value);"/>
                            </p>
                        </c:if>
                        <c:if test="${actionBean.table eq 'KodJadualKedua'
                                      or actionBean.table eq 'KodJadualKetiga'}">
                              <p>
                                  <label>No Rujukan  :</label>
                                  <s:text name="o.noRujukan" size="40" maxlength="40" />
                              </p>
                        </c:if>
                        <c:if test="${actionBean.table eq 'KodkadarBayaran'}">
                            <p>
                                <label>Kod Urusan :</label>
                                <s:select name="o.kodUrusan.kod">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodUrusan}" label="nama" value="kod"/>
                                </s:select>
                            </p>
                            <p>
                                <label>Bilangan Hakmilik  :</label>
                                <s:text name="o.bilHakmilik" size="10" />
                            </p>
                        </c:if>
                    </c:if>

                    <c:if test="${actionBean.table eq 'KodKategoriTanah' || actionBean.table eq 'KodKegunaanTanah'}">
                        <p>
                            <label>Nama  Lama:</label>
                            <s:text name="o.nama" size="40" readonly="readonly"/>
                        </p>
                        <p>
                            <label>Nama  Baru:</label>
                            <s:text name="nama" size="40" />
                        </p>
                    </c:if>

                    <c:if test="${actionBean.table eq 'KodNegeri'}">
                        <p>
                            <label>Nama  Lama:</label>
                            <s:text name="o.nama" size="40" readonly="readonly"/>
                        </p>
                        <p>
                            <label>Nama  Baru:</label>
                            <s:text name="nama" size="40" />
                        </p>
                    </c:if>

                    <c:if test="${actionBean.table eq 'KodBank'}">
                        <p>
                            <label>Nama  Lama:</label>
                            <s:text name="o.nama" size="40" readonly="readonly"/>
                        </p>
                        <p>
                            <label>Nama  Baru:</label>
                            <s:text name="nama" size="40" />
                        </p>
                    </c:if>
                    <c:if test="${actionBean.table eq 'KodUrusan'}">
                        <p>
                            <label>Kod :</label> ${actionBean.urusan.kod}
                            <s:hidden name="kod" value="${actionBean.urusan.kod}"/>
                        </p>
                        <p>
                            <label>Nama :</label>
                            <s:text name="urusan.nama" value="${actionBean.urusan.nama}"/>
                        </p>
                        <p>
                            <label>Unit :</label>
                            <s:select name="urusan.jabatan.kod" id="jabatan" >
                                <s:option value="">Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodJabatan}" label="nama" value="kod" />
                            </s:select>
                        </p>
                        <p>
                            <label>Jenis Serahan :</label>
                            <s:select name="urusan.kodPerserahan.kod" id="kodSerah" >
                                <s:option value="">Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodPerserahanAktif}" label="nama" value="kod" />
                            </s:select>
                        </p>
                        <p>
                            <label>Rujukan Kanun (Jika ada):</label>
                            <s:text name="urusan.rujukanKanun" class="textfield" size="40" maxlength="256"/>&nbsp;
                        </p>
                        <p>
                            <label>Urusan Kaunter  :</label>
                            <c:if test="${actionBean.urusan.urusanKaunter eq 'Y'}" >
                                <input type="radio" name="urusan.urusanKaunter" value="Y" checked="checked" /> Ya
                                <input type="radio" name="urusan.urusanKaunter" value="T" /> Tidak
                            </c:if>
                            <c:if test="${actionBean.urusan.urusanKaunter ne 'Y'}" >
                                <input type="radio" name="urusan.urusanKaunter" value="Y" /> Ya
                                <input type="radio" name="urusan.urusanKaunter" value="T" checked="checked" /> Tidak
                            </c:if>
                        </p>
                        <p>
                            <label>Urusan Dalaman  :</label>
                            <c:if test="${actionBean.urusan.urusanDalaman eq 'Y'}" >
                                <input type="radio" name="urusan.urusanDalaman" value="Y" checked="checked" /> Ya
                                <input type="radio" name="urusan.urusanDalaman" value="T" /> Tidak
                            </c:if>
                            <c:if test="${actionBean.urusan.urusanDalaman ne 'Y'}" >
                                <input type="radio" name="urusan.urusanDalaman" value="Y"  /> Ya
                                <input type="radio" name="urusan.urusanDalaman" value="T" checked="checked" /> Tidak
                            </c:if>
                        </p>
                        <p>
                            <label>Urusan Belakang Kaunter  :</label>
                            <c:if test="${actionBean.urusan.urusanBelakangKaunter eq 'Y'}" >
                                <input type="radio" name="urusan.urusanBelakangKaunter" value="Y" checked="checked"/> Ya
                                <input type="radio" name="urusan.urusanBelakangKaunter" value="T" /> Tidak
                            </c:if>
                            <c:if test="${actionBean.urusan.urusanBelakangKaunter ne 'Y'}" >
                                <input type="radio" name="urusan.urusanBelakangKaunter" value="Y" /> Ya
                                <input type="radio" name="urusan.urusanBelakangKaunter" value="T" checked="checked" /> Tidak
                            </c:if>
                        </p>
                        <p>
                            <label>Urusan Bayaran  :</label>
                            <c:if test="${actionBean.urusan.urusanBayaran eq 'Y'}" >
                                <input type="radio" name="urusan.urusanBayaran" value="Y" checked="checked"/> Ya
                                <input type="radio" name="urusan.urusanBayaran" value="T" /> Tidak
                            </c:if>
                            <c:if test="${actionBean.urusan.urusanBayaran ne 'Y'}" >
                                <input type="radio" name="urusan.urusanBayaran" value="Y" /> Ya
                                <input type="radio" name="urusan.urusanBayaran" value="T" checked="checked" /> Tidak
                            </c:if>
                        </p>
                        <p>
                            <label>Kod Transaksi :</label>
                            <s:select name="urusan.kodTransaksi.kod" id="kodTrans" >
                                <s:option value="">Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodTransaksiAktif}" label="nama" value="kod" />
                            </s:select>
                        </p>
                        <p>
                            <label>Kategori Bayaran (Jika ada):</label>
                            <s:select name="urusan.kategoriBayaran.kod" id="kodKatgBayar" >
                                <s:option value="">Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodKategoriBayaranAktif}" label="nama" value="kod" />
                            </s:select>
                        </p>
                        <p>
                            <label>Caj Bayaran :</label>
                            <s:text name="urusan.caj" class="textfield" size="10"/>
                        </p>
                        <p>
                            <label>Id Workflow :</label>
                            <s:text name="urusan.idWorkflow" class="textfield" size="40" maxlength="250"/>
                        </p>
                        <p>
                            <label>Jelaskan Cukai :</label>
                            <c:if test="${actionBean.urusan.perluJelasCukai eq 'Y'}" >
                                <input type="radio" name="urusan.perluJelasCukai" value="Y" checked="checked"/> Ya
                                <input type="radio" name="urusan.perluJelasCukai" value="T" /> Tidak
                            </c:if>
                            <c:if test="${actionBean.urusan.perluJelasCukai ne 'Y'}" >
                                <input type="radio" name="urusan.perluJelasCukai" value="Y" /> Ya
                                <input type="radio" name="urusan.perluJelasCukai" value="T" checked="checked"/> Tidak
                            </c:if>
                        </p>
                        <p>
                            <label>Ke PTG :</label>
                            <c:if test="${actionBean.urusan.kePTG eq 'Y'}" >
                                <input type="radio" name="urusan.kePTG" value="Y"checked="checked" /> Ya
                                <input type="radio" name="urusan.kePTG" value="T" /> Tidak
                            </c:if>
                            <c:if test="${actionBean.urusan.kePTG ne 'Y'}" >
                                <input type="radio" name="urusan.kePTG" value="Y" /> Ya
                                <input type="radio" name="urusan.kePTG" value="T" checked="checked"/> Tidak
                            </c:if>
                        </p>
                        <p>
                            <label>Bil Keutamaan (hari) :</label>
                            <s:text name="urusan.utama" class="textfield" size="10" maxlength="1"/>
                        </p>
                        <p>
                            <label>Bil Sasaran Bulanan :</label>
                            <s:text name="urusan.sasaranBulan" class="textfield" size="10" maxlength="2"/>
                        </p>
                        <p>
                            <label>Minumum Bilangan Hakmilik :</label>
                            <s:text name="urusan.bilMinimumHakmilik" class="textfield" size="10" maxlength="3"/>
                        </p>
                        <p>
                            <label>Maximum Bilangan Hakmilik :</label>
                            <s:text name="urusan.bilMaksimumHakmilik" class="textfield" size="10" maxlength="3"/>
                        </p>
                        <p>
                            <label>Id Workflow Integrasi (Jika Ada):</label>
                            <s:text name="urusan.idWorkflowIntegration" class="textfield" size="40" maxlength="250"/>
                        </p>
                    </c:if>
                    <c:if test="${actionBean.table eq 'KodKadarBayaran'}">
                        <s:hidden name="kod" value="${actionBean.kadarBayar.kod}"/>
                        <p>
                            <label>Nama Urusan :</label>
                            <s:select name="kadarBayar.kodUrusan.kod" id="kodUrusan" >
                                <s:option value="">Pilih</s:option>
                                <c:forEach items="${list.senaraiKodUrusanAktif}" var="items">
                                    <s:option value="${items.kod}">${items.kod} - ${items.nama}</s:option>                            
                                </c:forEach>
                            </s:select>
                        </p>
                        <p>
                            <label>Milik Daerah :</label>
                            <s:radio name="kadarBayar.milikDaerah" value="Y"></s:radio> Ya
                            <s:radio name="kadarBayar.milikDaerah" value="T" ></s:radio> Tidak
                            <s:radio name="kadarBayar.milikDaerah" value="X" ></s:radio> Lain-lain
                            </p>
                            <p>
                                <label>Kategori :</label>
                            <s:text name="kadarBayar.kategori" class="textfield" size="10" maxlength="30"/>
                        </p>
                        <p>
                            <label>Nilai Minimum :</label>
                            <s:text name="kadarBayar.nilaiMinimum" class="textfield" size="10" maxlength="12"/>
                        </p>
                        <p>
                            <label>Nilai Maximum :</label>
                            <s:text name="kadarBayar.nilaiMaksimum" class="textfield" size="10" maxlength="12"/>
                        </p>
                        <p>
                            <label>Bil. Hakmilik :</label>
                            <s:text name="kadarBayar.bilHakmilik" class="textfield" size="10" maxlength="2"/>
                        </p>
                        <p>
                            <label>Kadar Bayaran :</label>
                            <s:text name="kadarBayar.kadar" class="textfield" size="10" maxlength="12"/>
                        </p>
                    </c:if>
                    <c:if test="${actionBean.table eq 'KodDokumen'}">
                        <p>
                            <label>Kod :</label>${actionBean.kodDokumen.kod}
                            <s:hidden name="kod" value="${actionBean.kodDokumen.kod}"/>
                        </p>           
                        <p>
                            <label>Nama :</label>
                            <s:text name="kodDokumen.nama" value="${actionBean.kodDokumen.nama}"/>
                        </p>
                        <p>
                            <label>Kawal Capaian :</label>
                            <s:radio name="kodDokumen.kawalCapaian" value="Y"></s:radio> Ya
                            <s:radio name="kodDokumen.kawalCapaian" value="T" ></s:radio> Tidak
                            </p>
                            <p>
                                <label>Penjana  :</label>
                            <s:text name="kodDokumen.penjana" size="40" maxlength="40" />
                        </p>
                    </c:if>   
                    <c:if test="${actionBean.table eq 'KodKadarPremium'}">
                        <s:hidden name="kod" value="${actionBean.kadarPremium.idKodKadarPremium}"/>
                        <p>
                            <label>Kod Tanah :</label>
                            <s:select name="kadarPremium.kodTanah.kod" id="kodTanah" >
                                <s:option value="">Pilih</s:option>
                                <c:forEach items="${list.senaraiKodTanah}" var="items">
                                    <s:option value="${items.kod}">${items.kod} - ${items.nama}</s:option>                            
                                </c:forEach>
                            </s:select>
                        </p>
                        <p>
                            <label>Kod Kegunaan Tanah :</label>
                            <s:select name="kadarPremium.kodKegunaanTanah.kod" id="kodKegunaanTanah" >
                                <s:option value="">Pilih</s:option>
                                <c:forEach items="${list.senaraiKegunaanTanah}" var="items">
                                    <s:option value="${items.kod}">${items.kod} - ${items.nama}</s:option>                            
                                </c:forEach>
                            </s:select>
                        </p>
                        <p>
                            <label>Peratus Kadar :</label><s:text name="kadarPremium.peratusKadar" size="40" value="${actionBean.kadarPremium.peratusKadar}"/>&nbsp;
                        </p>
                        <p>
                            <label>Nama :</label><s:text name="kadarPremium.nama" size="40" value="${actionBean.kadarPremium.nama}"/>&nbsp;
                        </p>
                    </c:if>
                    <c:if test="${actionBean.table eq 'UrusanDokumen'}">
                        <s:hidden name="kod" value="${actionBean.urusanDokumen.idUrusanDokumen}"/>
                        <p>
                            <label>Kod Urusan:</label><!--${actionBean.urusanDokumen.kodUrusan.kod} - ${actionBean.urusanDokumen.kodUrusan.nama}-->
                        </p>
                        <p>
                            ${actionBean.urusanDokumen.kodUrusan.kod} - ${actionBean.urusanDokumen.kodUrusan.nama}
                        </p>
                        <p>
                            <label>Kod Dokumen:</label>
                            <p>
                                ${actionBean.urusanDokumen.kodDokumen.kod} - ${actionBean.urusanDokumen.kodDokumen.nama}
                            </p>
                        </p>
                        <p>
                            <label>Wajib :</label>
                            <s:radio name="urusanDokumen.wajib" value="Y"></s:radio> Ya
                            <s:radio name="urusanDokumen.wajib" value="T" ></s:radio> Tidak
                            </p>
                            <p>
                                <label>Perlu Disah :</label>
                            <s:radio name="urusanDokumen.perluDisah" value="Y"></s:radio> Ya
                            <s:radio name="urusanDokumen.perluDisah" value="T" ></s:radio> Tidak
                            </p>
                            <p>
                                <label>Perlu Diimbas :</label>
                            <s:radio name="urusanDokumen.perluDiimbas" value="Y"></s:radio> Ya
                            <s:radio name="urusanDokumen.perluDiimbas" value="T" ></s:radio> Tidak
                            </p>
                            <p>
                                <label>Catatan :</label><s:text name="urusanDokumen.catatan" size="10" value="${actionBean.urusanDokumen.catatan}"/>&nbsp;
                        </p>
                        <p>
                            <label>Caj :</label><s:text name="urusanDokumen.caj" size="10" value="${actionBean.urusanDokumen.caj}"/>&nbsp;
                        </p>
                        <p>
                            <label>Kod Transaksi :</label>
                            <s:select name="urusanDokumen.kodTransaksi.kod" id="kodTrans" style="width:350px;">
                                <s:option value="">Pilih</s:option>
                                <c:forEach items="${list.senaraiKodTransaksiAktif}" var="items">
                                    <s:option value="${items.kod}" >${items.kod} - ${items.nama}</s:option>                            
                                </c:forEach>
                            </s:select>
                        </p>
                        <p><label for="kategoriPemohon">Kategori Pemohon</label>
                            <s:select name="urusanDokumen.kategoriPemohon" id="kategoriPemohon" onchange="javaScript:changeKatgPemohon(this.options[selectedIndex].value);">
                                <s:option value="X">X - Tidak dinyatakan</s:option>
                                <s:option value="I">I - Individu(*)</s:option>
                                <s:option value="O">O - Organisasi/Syarikat(**)</s:option>
                                <s:option value="P">P - Pertubuhan(***)</s:option>
                            </s:select>
                        </p>
                    </c:if>
                    <c:if test="${actionBean.table eq 'KodCawangan'}">
                        <p>
                            <label>Kod :</label>${actionBean.kodCawangan.kod}
                            <s:hidden name="kod" value="${actionBean.kodCawangan.kod}" />
                        </p>
                        <p>
                            <label>Nama :</label>
                            <s:text name="kodCawangan.name" size="40" maxlength="50"/>
                        </p>
                        <p>
                            <label>Kod Daerah :</label>&nbsp;
                            <s:select name="kodCawangan.daerah.kod" id="daerah" >
                                <s:option value="">Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodDaerah}" label="nama" value="kod" />
                            </s:select>
                        </p>
                        <p>
                            <label>Nama :</label>&nbsp;
                            <s:text name="kodCawangan.name" maxlength="50" />
                        </p>
                        <p>
                            <label>Alamat :</label>&nbsp;<s:text name="alamat1" maxlength="60" size="30"/>
                        </p>
                        <p>
                            <label>&nbsp;</label><s:text name="alamat2" maxlength="60" size="30"/>
                        </p>
                        <p>
                            <label>&nbsp;</label><s:text name="alamat3" maxlength="60" size="30"/>
                        </p>
                        <p>
                            <label>&nbsp;</label><s:text name="alamat4" maxlength="60" size="30"/>
                        </p>

                        <p>
                            <label>Poskod :</label>&nbsp;<s:text name="poskod" maxlength="5" />
                        </p>
                        <p>
                            <label>Negeri :</label>&nbsp;
                            <s:select name="negeri" id="negeri" >
                                <s:option value="">Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod" />
                            </s:select>
                        </p>
                        <br />
                        <p>
                            <label>No Tel 1 :</label>&nbsp;
                            <s:text name="kodCawangan.noTelefon" maxlength="20" />
                        </p>
                        <p>
                            <label>No Tel 2 :</label>&nbsp;
                            <s:text name="kodCawangan.noTelefon2" maxlength="20" />
                        </p>
                        <p>
                            <label>No Fax :</label>&nbsp;
                            <s:text name="kodCawangan.noFax" maxlength="20" />
                        </p>
                        <p>
                            <label>Kod PTJ :</label>&nbsp;
                            <s:text name="kodCawangan.kodPTJ" maxlength="50" />
                        </p>
                        <p>
                            <label>Kod Jab. SPEK :</label>&nbsp;
                            <s:text name="kodCawangan.kodJabatanSpek" maxlength="5" />
                        </p>
                        <br />
                    </c:if>

                    <p>
                        <label>Aktif  :</label>
                        <s:radio name="aktif" value="Y"></s:radio> Ya
                        <s:radio name="aktif" value="T" ></s:radio> Tidak
                        </p>
                    <c:if test="${actionBean.table eq 'KodCawangan'}">
                        <fieldset class="aras1">
                            <legend>Muat Naik Cop</legend>
                            <p>
                                <label> Fail :</label>&nbsp;
                                <s:file name="fileToBeUpload" id="fileToBeUpload"/>
                            </p>
                            <br>
                            <c:if test="${actionBean.blob ne null}">

                                <p>
                                <center>
                                    <img src ="${pageContext.request.contextPath}/uam/uploadTT?viewCop&idParam=${actionBean.kodCawangan.cop}" height="100" >

                                </center>
                                </p>
                            </c:if>
                            <c:if test="${actionBean.blob eq null}">
                                <p>
                                    <label> Cop : <font size="2" color="red">Belum Dimuat Naik</font></label> 
                                </p>
                            </c:if>
                            <br/>
                        </fieldset>
                    </c:if>
                    <c:if test="${actionBean.table eq 'KodKegunaanPetakAksesori' || actionBean.table eq 'KodKegunaanPetak' 
                                  || actionBean.table eq 'KodBandarPekanMukim' || actionBean.table eq 'KodSeksyen'
                                  || actionBean.table eq 'KodDaerah' || actionBean.table eq 'KodKategoriTanah' 
                                  || actionBean.table eq 'KodKegunaanBangunan' || actionBean.table eq 'KodWarganegara' 
                                  || actionBean.table eq 'KodKegunaanTanah' || actionBean.table eq 'KodNegeri'
                                  || actionBean.table eq 'KodPenubuhanSyarikat' || actionBean.table eq 'KodUrusan'
                                  || actionBean.table eq 'KodKadarBayaran' || actionBean.table eq 'KodCawangan'
                                  || actionBean.table eq 'KodBank' || actionBean.table eq 'KodKadarPremium'
                                  || actionBean.table eq 'KodDokumen' || actionBean.table eq 'UrusanDokumen'}">
                          <center>
                              <s:submit name="updateKod" id="simpan" value="Simpan" class="btn"/>
                              <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                          </center>
                          <s:hidden name="table" value="${actionBean.table}"/>
                    </c:if>
                </s:form>
                <br>
            </fieldset>
        </div>

    </body>
</html>