<%-- 
    Document   : view_barang_detail
    Created on : Jul 13, 2010, 9:26:59 AM
    Author     : nurshahida.radzi
    Modify by  : sitifariza.hanim (18DIS2010)
    Modify by : ctzainal 16june 2011
    Modified by: ctzainal on 12Sept2011-add field namaPemandu based on senaraiOKS-brg rampas jenis kenderaan-melakaPAT
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>
<style type="text/css">
    label.infoJ {
        display:block;
        clear:both;
        margin-top: -2px;
        margin-left: 98px;
    }

    td.infoJ {
        display:block;
        clear:both;
        margin-top: 1px;
        margin-left: -2px;
        color: black;
        font-size: 13px;
        font-weight: normal;
        padding-bottom: 2px;
        padding-top: 2px;

    }

</style>
<s:form  beanclass="etanah.view.penguatkuasaan.MaklumatBarangTahananActionBean">

    <div class="subtitle">
        <fieldset class="aras1">
            <legend> Maklumat Barang Rampasan </legend>

            <p>
                <label>No.rujukan :</label>&nbsp;
                ${actionBean.barangRampasan.idBarang}&nbsp;
            </p>
            <p>
                <label>Jenis Barang Yang Dirampas :</label>&nbsp;
                <%--<s:radio name="kodKategoriItemRampasan" id="kodKategoriItemRampasan" value="B" disabled="true" /> Bukan Kenderaan
                <s:radio name="kodKategoriItemRampasan" id="kodKategoriItemRampasan" value="K" disabled="true" /> Kenderaan--%>
                ${actionBean.barangRampasan.kodKategoriItemRampasan.nama}&nbsp;

            </p>

            <c:if test="${actionBean.barangRampasan.kodKategoriItemRampasan.kod eq 'K'}">
                <p>
                    <label>Jenis Kenderaan :</label>&nbsp;
                    ${actionBean.item}&nbsp;

                </p>
                <p>
                    <label>No. Pendaftaran :</label>&nbsp;
                    ${actionBean.nomborPendaftaran}&nbsp;
                </p>
                <p>
                    <label>No. Enjin :</label>&nbsp;
                    ${actionBean.nomborEnjin}&nbsp;
                </p>
                <p>
                    <label>No. Casis :</label>&nbsp;
                    ${actionBean.nomborCasis}&nbsp;
                </p>
                <p>
                    <label>Buatan :</label>&nbsp;
                    ${actionBean.buatan}&nbsp;
                </p>
                <p>
                    <label>Nama Model :</label>&nbsp;
                    ${actionBean.namaModel}&nbsp;
                </p>
                <p>
                    <label>Keupayaan Enjin :</label>&nbsp;
                    ${actionBean.kapasitiEnjin}&nbsp;
                </p>
                <p>
                    <label>Bahan Bakar :</label>&nbsp;
                    ${actionBean.barangRampasan.kodBahanBakar.nama}&nbsp;
                </p>
                <p>
                    <label>Warna :</label>&nbsp;
                    ${actionBean.warna}&nbsp;
                </p>
                <p>
                    <label>Kelas Kegunaan :</label>&nbsp;
                    ${actionBean.barangRampasan.kodKegunaanKenderaan.nama}&nbsp;
                </p>
                <p>
                    <label>Jenis Badan :</label>&nbsp;
                    ${actionBean.barangRampasan.kodJenisBadanKenderaan.nama}&nbsp;
                </p>
                <p>
                    <label>Tahun Dibuat :</label>&nbsp;
                    ${actionBean.tahunDibuat}&nbsp;
                </p>
                <p>
                    <label>Tarikh Pendaftaran :</label>&nbsp;
                    <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.tarikhPendaftaran}"/>&nbsp;
                </p>
                <p>
                    <label>Status Pemunya :</label>&nbsp;
                    <c:if test="${actionBean.barangRampasan.statusPemunya eq 'I'}">
                        Individu &nbsp;
                    </c:if>
                    <c:if test="${actionBean.barangRampasan.statusPemunya eq 'S'}">
                        Syarikat &nbsp;
                    </c:if>
                    <%--${actionBean.barangRampasan.statusPemunya}--%>
                    <%--   <s:radio name="statusPemunya" value="S" disabled="true" />&nbsp;Syarikat
                       <s:radio name="statusPemunya" value="I" disabled="true"/>&nbsp;Individu--%>
                </p>
                <p>
                    <label>Muatan Tempat Duduk :</label>&nbsp;
                    ${actionBean.muatanTempatDuduk}&nbsp;
                </p>
                <p>
                    <label>Kadar Lesen Kenderaan Bermotor :</label>&nbsp;
                    ${actionBean.kadarLesen}&nbsp;
                </p>
                <p>
                    <label>Tarikh Kenderaan Dirampas:</label>&nbsp;
                    ${actionBean.tarikhRampas}&nbsp;
                </p>
                <p>
                    <label>Masa Kenderaan Dirampas :</label>&nbsp;
                    ${actionBean.jam}:${actionBean.minit}&nbsp;
                    <c:if test="${actionBean.ampm eq 'AM'}">PAGI</c:if>
                    <c:if test="${actionBean.ampm eq 'PM'}">PETANG</c:if>
                </p>

            </c:if>

            <c:if test="${actionBean.barangRampasan.kodKategoriItemRampasan.kod eq 'B'}">
                <p>
                    <label>Barang Yang Dirampas :</label>&nbsp;
                    ${actionBean.item}&nbsp;
                </p>
                <p>
                    <label>Tarikh Barang Dirampas:</label>&nbsp;
                    ${actionBean.tarikhRampas}&nbsp;
                </p>

                <p>
                    <label>Masa Barang Dirampas :</label>&nbsp;
                    ${actionBean.jam}:${actionBean.minit}&nbsp;${actionBean.ampm}&nbsp;
                </p>
                <p>
                    <label>Kuantiti :</label>&nbsp;
                    ${actionBean.kuantiti} &nbsp;${actionBean.kuantitiUnit}&nbsp;
                </p>
            </c:if>

            <p>
                <label>Tempat Simpanan :</label>&nbsp;
                ${actionBean.tempatSimpanan}&nbsp;
            </p>
            <p>
                <label>Tempat Pengambilan  :</label>&nbsp;
                ${actionBean.tempatPengambilan}&nbsp;
            </p>
            <p>
                <label>Tempat Penghantaran :</label>&nbsp;
                ${actionBean.tempatPenghantaran}&nbsp;
            </p>
            <p>
                <label>Lokasi Tangkapan :</label>&nbsp;
                ${actionBean.tempatTangkap}&nbsp;
            </p>
            <%-- <p>
                 <label>Catatan :</label>&nbsp;
                 ${actionBean.catatan}&nbsp;
             </p>
             <p>
                 <label>Catatan :</label>
                 <s:textarea name="catatan" rows="10" cols="60" id="catatan" readonly="true" />&nbsp;
             </p>--%>
            <p>
                <label>Status :</label>&nbsp;
                ${actionBean.barangRampasan.status.nama}&nbsp;
            </p>
            <p>
                <label>Laporan Polis :</label>&nbsp;
                <c:if test="${actionBean.idOperasiAgensi eq 'T'}">
                    Tiada&nbsp;
                </c:if>
                <c:if test="${actionBean.idOperasiAgensi ne 'T'}">
                    ${actionBean.noRujukan}&nbsp;
                </c:if>
            </p>
            <table>
                <tr>
                    <td valign="top">
                        <p>
                            <label class="infoJ">Lain-lain Hal :</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        </p>
                    </td>
                    <td class="infoJ">${actionBean.catatan}&nbsp;</td>
                </tr>
            </table>

            <br>
        </fieldset>
        <c:if test="${actionBean.barangRampasan.kodKategoriItemRampasan.kod eq 'K'}">
            <fieldset class="aras1">
                <legend>Maklumat Pemegang Permit</legend>

                <p>
                    <label>No Syarikat :</label>&nbsp;
                    ${actionBean.noSyarikat}&nbsp;
                </p>
                <p>
                    <label>Nama Pemegang Permit :</label>&nbsp;
                    ${actionBean.pemegangPermit}&nbsp;
                </p>
                <p>
                    <label>Jenis Pengenalan Pemegang Permit :</label>&nbsp;
                    ${actionBean.barangRampasan.kodJenisPengenalanPemegangPermit.nama}&nbsp;
                </p>
                <p>
                    <label>No.Pengenalan Pemegang Permit :</label>&nbsp;
                    ${actionBean.nomborPengenalanPemegangPermit}&nbsp;
                </p>
                <p>
                    <label>Alamat :</label>
                    ${actionBean.alamat1PemegangPermit}&nbsp;
                </p>
                <p>
                    <label>&nbsp;</label>&nbsp;
                    ${actionBean.alamat2PemegangPermit}&nbsp;
                </p>
                <p>
                    <label>&nbsp;</label>&nbsp;
                    ${actionBean.alamat3PemegangPermit}&nbsp;
                </p>
                <p>
                    <label>&nbsp;</label>&nbsp;
                    ${actionBean.alamat4PemegangPermit}&nbsp;
                </p>
                <p>
                    <label>Poskod :</label>&nbsp;
                    ${actionBean.poskodPemegangPermit}&nbsp;
                </p>
                <p>
                    <label>Negeri :</label>&nbsp;
                    ${actionBean.barangRampasan.kodNegeriPemegangPermit.nama}&nbsp;
                </p>
            </fieldset>
        </c:if>
        <fieldset class="aras1">
            <legend>Maklumat Suspek</legend>

            <p>
                <label>Nama Suspek :</label>&nbsp;
                ${actionBean.namaSuspekTemp}&nbsp;
            </p>
            <p>
                <label>NoTelefon :</label>&nbsp;
                ${actionBean.noTelSuspek}&nbsp;
            </p>
            <p>
                <label>No Pengenalan :</label>&nbsp;
                ${actionBean.noPengenalanSuspek}&nbsp;
            </p>

            <p>
                <label>Alamat :</label>&nbsp;
                ${actionBean.alamat1Suspek}&nbsp;
            </p>
            <p>
                <label>&nbsp;</label>&nbsp;
                ${actionBean.alamat2Suspek}&nbsp;
            </p>
            <p>
                <label>&nbsp;</label>&nbsp;
                ${actionBean.alamat3Suspek}&nbsp;
            </p>
            <p>
                <label>&nbsp;</label>&nbsp;
                ${actionBean.alamat4Penjamin}&nbsp;
            </p>
            <p>
                <label>Poskod :</label>&nbsp;
                ${actionBean.poskodSuspek}&nbsp;
            </p>
        </fieldset>

        <fieldset class="aras1">
            <legend>Maklumat Pemunya</legend>

            <p>
                <label>Nama Pemunya :</label>&nbsp;
                ${actionBean.namaPemunya}&nbsp;
            </p>
            <p>
                <label>No Telefon  :</label>&nbsp;
                ${actionBean.noTelPemunya}&nbsp;
            </p>
            <p>
                <label>No Pengenalan :</label>&nbsp;
                ${actionBean.noPengenalanPemunya}&nbsp;
            </p>

            <p>
                <label>Alamat :</label>&nbsp;
                ${actionBean.alamat1}&nbsp;
            </p>
            <p>
                <label>&nbsp;</label>&nbsp;
                ${actionBean.alamat2}&nbsp;
            </p>
            <p>
                <label>&nbsp;</label>&nbsp;
                ${actionBean.alamat3}&nbsp;
            </p>
            <p>
                <label>&nbsp;</label>&nbsp;
                ${actionBean.alamat4}&nbsp;
            </p>
            <p>
                <label>Poskod :</label>&nbsp;
                ${actionBean.poskod}&nbsp;
            </p>
            <p>
                <label>Negeri :</label>&nbsp;
                ${actionBean.barangRampasan.kodNegeri.nama}&nbsp;
            </p>
        </fieldset>

        <%--to view maklumat penjamin--%>
        <fieldset class="aras1">
            <legend>Maklumat Penjamin</legend>

            <p>
                <label>Nama Penjamin :</label>&nbsp;
                ${actionBean.namaPenjamin}&nbsp;
            </p>

            <p>
                <label>No Pengenalan :</label>&nbsp;
                ${actionBean.noPengenalanPenjamin}&nbsp;
            </p>

            <p>
                <label>Alamat :</label>&nbsp;
                ${actionBean.alamat1Penjamin}&nbsp;
            </p>
            <p>
                <label>&nbsp;</label>&nbsp;
                ${actionBean.alamat2Penjamin}&nbsp;
            </p>
            <p>
                <label>&nbsp;</label>&nbsp;
                ${actionBean.alamat3Penjamin}&nbsp;
            </p>
            <p>
                <label>&nbsp;</label>&nbsp;
                ${actionBean.alamat4Penjamin}&nbsp;
            </p>
            <p>
                <label>Poskod :</label>&nbsp;
                ${actionBean.poskodPenjamin}&nbsp;
            </p>
            <p>
                <label>Negeri :</label>&nbsp;
                ${actionBean.penjaminBarangRampasan.negeri.nama}&nbsp;
            </p>

            <p>
                <label>Jantina :</label>&nbsp;
                ${actionBean.penjaminBarangRampasan.kodJantina.nama}&nbsp;
            </p>
            <p>
                <label>No Telefon  :</label>&nbsp;
                ${actionBean.penjaminBarangRampasan.noTelefon}&nbsp;
            </p>
            <p>
                <label>No Telefon Bimbit :</label>&nbsp;
                ${actionBean.penjaminBarangRampasan.noTelefonBimbit}&nbsp;
            </p>
            <p>
                <label>Hubungan :</label>&nbsp;
                ${actionBean.penjaminBarangRampasan.hubungan}&nbsp;
            </p>

        </fieldset>

        <p><label>&nbsp;</label>
            <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
        </p>
    </div>
</s:form>
