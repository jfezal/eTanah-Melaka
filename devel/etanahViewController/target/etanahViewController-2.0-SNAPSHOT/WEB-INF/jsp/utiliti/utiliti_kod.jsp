<%--
    Document   : utiliti_kod
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

            function validateForm() {

                var kod = document.form1.kod.value;

                if ((kod == ""))
                {
                    alert('Sila Pilih Kod ');
                    document.form1.kod.focus();
                    return false
                }
                return true;

            }

            function dopopup(i, n) {
                window.open("${pageContext.request.contextPath}/utiliti/kod?showPopup&kod=" + i + "&table=" + n, "eTanah",
                        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=600,scrollbars=yes");
            }
            function dopopup2(n) {
                window.open("${pageContext.request.contextPath}/utiliti/kod?tambahKod&table=" + n, "eTanah",
                        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=400,scrollbars=yes");
            }
        </script>
    </head>
    <body>
        <s:errors/>
        <s:messages/>
        <div class="subtitle">
            <s:form beanclass="etanah.view.daftar.utiliti.UtilitiKodActionBean" name="form1" id="senarai_kod">
                <fieldset class="aras1">
                    <p style="color:red">
                        *Sila Pilih Kod<br/>

                    </p>
                    <legend>
                        Utiliti Kod
                    </legend>

                    <p>
                        <label>Jenis Kod :</label>
                        <s:select name="table" id="" value="table">
                            <s:option value="">Pilih ...</s:option>
                            <s:option value="KodAgensi">Kod Agensi</s:option>
                            <s:option value="KodAkaun">Kod Akaun</s:option>
                            <s:option value="KodBahanBakar">Kod Bahan Bakar</s:option>
                            <s:option value="KodBahanBatu">Kod Bahan Batu</s:option>
                            <s:option value="KodBandarPekanMukim">Kod Bandar/Pekan/Mukim</s:option>
                            <s:option value="KodBank">Kod Bank</s:option>
                            <s:option value="KodBatalDokumenKewangan">Kod Batal Dokumen Kewangan</s:option>
                            <s:option value="KodBilik">Kod Bilik</s:option>
                            <s:option value="KodBangsa">Kod Bangsa</s:option>
                            <s:option value="KodCaraBayaran">Kod Cara Bayaran</s:option>
                            <s:option value="KodCaraPermohonan">Kod Cara Permohonan</s:option>
                            <s:option value="KodCawangan">Kod Cawangan</s:option>
                            <s:option value="KodCaraPenghantaran">Kod Cara Penghantaran</s:option>
                            <s:option value="KodDaerah">Kod Daerah</s:option>
                            <s:option value="KodDokumen">Kod Dokumen</s:option>
                            <s:option value="KodHakmilik">Kod Hakmilik</s:option>
                            <s:option value="KodJabatan">Kod Jabatan</s:option>
                            <s:option value="KodJadualKedua">Kod Jadual Kedua</s:option>
                            <s:option value="KodJadualKetiga">Kod Jadual Ketiga</s:option>
                            <s:option value="KodJantina">Kod Jantina</s:option>
                            <s:option value="KodJenisBadanKenderaan">Kod Jenis Badan Kenderaan</s:option>
                            <s:option value="KodJenisPengenalan">Kod Jenis Pengenalan</s:option>
                            <s:option value="KodJenisPihakBerkepentingan">Kod Jenis Pihak Berkepentingan</s:option>
                            <s:option value="KodKadarBayaran">Kod Kadar Bayaran</s:option>
                            <s:option value="KodKadarCukai">Kod Kadar Cukai</s:option>
                            <s:option value="KodKadarPremium">Kod Kadar Premium</s:option>
                            <s:option value="KodKategoriAkaun">Kod Kategori Akaun</s:option>
                            <s:option value="KodKategoriBayaran">Kod Kategori Bayaran</s:option>
                            <s:option value="KodKategoriItemRampasan">Kod Kategori Item Rampasan</s:option>
                            <s:option value="KodKategoriPampasan">Kod Kategori Pampasan</s:option>
                            <s:option value="KodKategoriTanah">Kod Kategori Tanah</s:option>
                            <s:option value="KodKategoriTransaksi">Kod Kategori Transaksi</s:option>
                            <s:option value="KodKlasifikasi">Kod Klasifikasi</s:option>
                            <s:option value="KodKeputusan">Kod Keputusan</s:option>
                            <s:option value="KodKecerunanTanah">Kod Kecerunan Tanah</s:option>
                            <s:option value="KodKelapanganTanah">Kod Kelapangan Tanah</s:option>
                            <s:option value="KodKegunaanBangunan">Kod Kegunaan Bangunan</s:option>
                            <s:option value="KodKegunaanTanah">Kod Kegunaan Tanah</s:option>
                            <s:option value="KodKegunaanKenderaan">Kod Kegunaan Kenderaan</s:option>
                            <s:option value="KodKegunaanPetak">Kod Kegunaan Petak</s:option>
                            <s:option value="KodKegunaanPetakAksesori">Kod Kegunaan Petak Aksesori</s:option>
                            <s:option value="KodKumpulanHakmilik">Kod Kumpulan Hakmilik</s:option>
                            <s:option value="KodKutipan">Kod Kutipan</s:option>
                            <s:option value="KodLot">Kod Lot</s:option>
                            <s:option value="KodNegeri">Kod Negeri</s:option>
                            <s:option value="KodNotis">Kod Notis</s:option>
                            <s:option value="KodPBT">Kod PBT</s:option>
                            <s:option value="KodPemilikan">Kod Pemilikan</s:option>
                            <s:option value="KodPenyerah">Kod Penyerah</s:option>
                            <s:option value="KodPenubuhanSyarikat">Kod Penubuhan Syarikat</s:option>
                            <s:option value="KodPerhubunganHakmilik">Kod Perhubungan Hakmilik</s:option>
                            <s:option value="KodPeranan">Kod Peranan</s:option>
                            <s:option value="KodPerintah">Kod Perintah</s:option>
                            <s:option value="KodPerserahan">Kod Perserahan</s:option>
                            <s:option value="KodRujukan">Kod Rujukan</s:option>
                            <s:option value="KodRizab">Kod Rizab</s:option>
                            <s:option value="KodSekatanKepentingan">Kod Sekatan Kepentingan</s:option>
                            <s:option value="KodSeksyen">Kod Seksyen</s:option>
                            <s:option value="KodSenarai">Kod Senarai</s:option>
                            <s:option value="KodStatusAkaun">Kod Status Akaun</s:option>
                            <s:option value="KodStatusBarangRampasan">Kod Status Barang Rampasan</s:option>
                            <s:option value="KodStatusDokumenKewangan">Kod Status Dokumen Kewangan</s:option>
                            <s:option value="KodStatusEnkuiri">Kod Status Enkuiri</s:option>
                            <s:option value="KodStatusLelongan">Kod Status Lelongan</s:option>
                            <s:option value="KodStatusPengguna">Kod Status Pengguna</s:option>
                            <s:option value="KodStatusTerima">Kod Status Terima</s:option>
                            <s:option value="KodStatusTransaksiKewangan">KodStatus Transaksi Kewangan</s:option>
                            <s:option value="KodStatusTuntutanCukai">Kod Status Tuntutan Cukai</s:option>
                            <s:option value="KodStatusHakmilik">Kod Status Hakmilik</s:option>
                            <s:option value="KodStatusMohonPihak">Kod Status Mohon Pihak</s:option>
                            <s:option value="KodSyaratNyata">Kod Syarat Nyata</s:option>
                            <s:option value="KodStrukturTanah">Kod Struktur Tanah</s:option>
                            <s:option value="KodSuku">Kod Suku</s:option>
                            <s:option value="KodTanah">Kod Tanah</s:option>
                            <s:option value="KodTindakanPenguatkuasaan">Kod Tindakan Penguatkuasaan</s:option>
                            <s:option value="KodTransaksi">Kod Transaksi</s:option>
                            <s:option value="KodTuntut" >Kod Tuntut</s:option>
                            <s:option value="KodTransaksiTuntut" >Kod Transaksi Tuntut</s:option>
                            <s:option value="KodUOM">Kod UOM</s:option>
                            <s:option value="KodUrusan">Kod Urusan</s:option>
                            <s:option value="KodUrusanHalang">Kod Urusan Halang</s:option>
                            <s:option value="KodWarganegara">Kod Warganegara</s:option>
                            <s:option value="UrusanDokumen">Urusan Dokumen</s:option>
                        </s:select>
                    </p>
                    <c:if test="${fn:length(actionBean.results) > 0}">
                        <c:if test="${actionBean.table eq 'KodKegunaanPetakAksesori' || actionBean.table eq 'KodKegunaanPetak'
                                      || actionBean.table eq 'KodKegunaanBangunan'}">
                              <p>
                                  <label>Kod :</label> <s:text name="kod1" class="text" size="40"/>
                                  atau 
                              </p>
                              <p>
                                  <label>Nama :</label> <s:text name="nama" class="text" size="40"/>
                              </p>
                        </c:if>
                    </c:if>

                    <p>
                        <label>&nbsp;</label>
                        <s:submit name="cariKod" value="Cari Kod" class="btn" onclick="return validateForm();"/>
                        <c:if test="${fn:length(actionBean.results) > 0}">
                            <c:if test="${actionBean.table eq 'KodKegunaanPetakAksesori' 
                                          || actionBean.table eq 'KodKegunaanPetak' 
                                          || actionBean.table eq 'KodBank' 
                                          || actionBean.table eq 'KodKegunaanBangunan' 
                                          || actionBean.table eq 'KodWarganegara' 
                                          || actionBean.table eq 'KodDaerah' 
                                          || actionBean.table eq 'KodBandarPekanMukim' 
                                          ||  actionBean.table eq 'KodSeksyen'
                                          ||  actionBean.table eq 'KodKategoriTanah'
                                          ||  actionBean.table eq 'KodKegunaanTanah'
                                          ||  actionBean.table eq 'KodNegeri'
                                          ||  actionBean.table eq 'KodUrusan'
                                          ||  actionBean.table eq 'KodCawangan'
                                          ||  actionBean.table eq 'KodKadarBayaran'
                                          ||  actionBean.table eq 'KodPenubuhanSyarikat'
                                          ||  actionBean.table eq 'KodBank'
                                          ||  actionBean.table eq 'KodTransaksi'
                                          ||  actionBean.table eq 'KodKadarPremium'
                                          ||  actionBean.table eq 'UrusanDokumen'
                                          ||  actionBean.table eq 'KodDokumen'}">
                                <s:button name="addNew" value="Tambah" class="btn" onclick="dopopup2('${actionBean.table}');"/>
                            </c:if>
                            <br><br>
                            <c:if test="${actionBean.table eq 'KodKadarBayaran' || actionBean.table eq 'KodUrusan' || actionBean.table eq 'UrusanDokumen' }">
                            <center>
                                Kod Urusan <s:text name="parameter" id="parameter" value ="parameter"/> &nbsp;&nbsp;
                                <s:submit name="cariByParameter" value="Cari" class="btn" onclick="return validateForm();"/>
                            </center>
                            </c:if>
                            <br />
                            <br><br>
                            <c:if test="${actionBean.table eq 'KodDokumen'}">
                            <center>
                                Kod Dokumen <s:text name="parameter" id="parameter" value ="parameter"/> &nbsp;&nbsp;
                                <s:submit name="cariByParameter" value="Cari" class="btn" onclick="return validateForm();"/>
                            </center>
                            </c:if>
                            <br />
                    </c:if>
                    </p>
                    <br>
                </fieldset>
            </div>
            <div class="subtitle" align="center">
                <fieldset class="aras1">
                    <c:if test="${fn:length(actionBean.results) > 0}">
                        <display:table name="${actionBean.results}"  class="tablecloth"  requestURI="/utiliti/kod"
                                       pagesize="15" cellpadding="0" cellspacing="0" id="line">
                            <s:hidden name="table" value="${actionBean.table}"/>
                            <display:column title="No" sortable="true">${line_rowNum}</display:column>
                            <c:if test="${actionBean.table ne 'KodKadarBayaran' && actionBean.table ne 'KodKadarPremium' && actionBean.table ne 'UrusanDokumen'}">
                                <display:column title="Kod" property="kod" sortable="true"/>
                            </c:if>
                            <c:if test="${actionBean.table eq 'KodKadarBayaran'}">
                                <display:column title="Kod" property="kodUrusan.kod" sortable="true"/>
                                <display:column title="Nama Urusan" property="kodUrusan.nama" />
                                <display:column title="Milik Daerah" >
                                    <c:if test="${line.milikDaerah eq 'Y'}">
                                        Ya
                                    </c:if>
                                    <c:if test="${line.milikDaerah eq 'T'}">
                                        Tidak
                                    </c:if>
                                </display:column>
                                <display:column title="Nilai Minimum Hakmilik" property="nilaiMinimum"/>
                                <display:column title="Nilai Maksimum Hakmilik" property="nilaiMaksimum"/>
                                <display:column title="Bil.Hakmilik" property="bilHakmilik"/>
                                <display:column title="Kadar Bayaran" property="kadar" />
                            </c:if>
                            <c:if test="${actionBean.table eq 'KodKadarPremium'}">
                                <s:hidden name="idKodKadarPremium"/>
                                <display:column title="Kod Tanah" property="kodTanah.nama" />
                                <display:column title="Kod Kegunaan Tanah" property="kodKegunaanTanah.nama" />
                                <display:column title="Peratus Kadar" property="peratusKadar" />
                            </c:if>
                            <c:if test="${actionBean.table eq 'UrusanDokumen'}">
                                <s:hidden name="idUrusanDokumen"/>
                                <display:column title="Kod Urusan" property="kodUrusan.kod" />
                                <display:column title="Dokumen" >${line.kodDokumen.kod} - ${line.kodDokumen.nama}</display:column>
                                <display:column title="Wajib" property="wajib" />
                                <display:column title="Perlu Disah" property="perluDisah" />
                                <display:column title="Perlu Diimbas" property="perluDiimbas" />
                                <display:column title="Caj" property="caj" />
                                <display:column title="Kod Transaksi" property="kodTransaksi.kod" />
                                <display:column title="Kategori Pemohon" property="kategoriPemohon" />
                            </c:if>
                            <c:if test="${actionBean.table ne 'KodCawangan' && actionBean.table ne 'KodKadarBayaran' && actionBean.table ne 'UrusanDokumen'}">
                                <display:column title="Nama" property="nama" /></c:if>
                            <c:if test="${actionBean.table eq 'KodCawangan'}">
                                <display:column title="Nama" property="name" /></c:if>
                            <display:column title="Dimasukkan" property="infoAudit.dimasukOleh.nama"/>
                            <display:column property="infoAudit.tarikhMasuk" title="Tarikh Dimasukkan" format="{0,date,dd-MM-yyyy hh:mm aa}"/>
                            <c:if test="${actionBean.table eq 'KodKegunaanPetakAksesori' || actionBean.table eq 'KodKegunaanPetak'
                                          || actionBean.table eq 'KodKategoriTanah' || actionBean.table eq 'KodKegunaanTanah' 
                                          || actionBean.table eq 'KodKegunaanBangunan' || actionBean.table eq 'KodWarganegara' 
                                          || actionBean.table eq 'KodNegeri' || actionBean.table eq 'KodPenubuhanSyarikat'
                                          || actionBean.table eq 'KodKadarBayaran' || actionBean.table eq 'KodBank' || actionBean.table eq 'KodKadarPremium'
                                          || actionBean.table eq 'KodDokumen' || actionBean.table eq 'UrusanDokumen'}">
                                <display:column title="Status">
                                    <c:if test="${line.aktif eq 'N' || line.aktif eq 'T'}">
                                        Tidak Aktif
                                    </c:if>
                                    <c:if test="${line.aktif eq 'Y'}">
                                        Aktif
                                    </c:if>
                                </display:column>
                            </c:if>
                            <c:if test="${actionBean.table eq 'KodKadarPremium'}">
                                <display:column title="Kemaskini">
                                    <p align="center">
                                        <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                             onclick="dopopup('${line.idKodKadarPremium}', '${actionBean.table}');
                                                     return false;"  onmouseover="this.style.cursor = 'pointer';">
                                    </p>
                                </display:column>            
                            </c:if>
                            <c:if test="${actionBean.table eq 'UrusanDokumen'}">
                                <display:column title="Kemaskini">
                                    <p align="center">
                                        <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                             onclick="dopopup('${line.idUrusanDokumen}', '${actionBean.table}');
                                                     return false;"  onmouseover="this.style.cursor = 'pointer';">
                                    </p>
                                </display:column>            
                            </c:if>
                            <c:if test="${actionBean.table ne 'KodKadarPremium' && actionBean.table ne 'UrusanDokumen'}">
                                <display:column title="Kemaskini">
                                    <p align="center">
                                        <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                             onclick="dopopup('${line.kod}', '${actionBean.table}');
                                                     return false;"  onmouseover="this.style.cursor = 'pointer';">
                                    </p>
                                </display:column>
                            </c:if>
                        </display:table>
                    </c:if>
                    <c:if test="${fn:length(actionBean.results) == 0 && actionBean.table ne null}">
                        <font color="red">Tidak dijumpai</font>
                        </c:if>
                    </s:form>
                <br>
            </fieldset>
        </div>
    </body>
</html>