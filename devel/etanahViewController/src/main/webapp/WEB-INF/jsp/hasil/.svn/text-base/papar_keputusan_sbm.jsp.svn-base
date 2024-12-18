<%-- 
    Document   : papar_keputusan_sbm
    Created on : Nov 20, 2009, 2:27:21 PM
    Author     : abu.mansur
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page import="java.util.List;" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.stripes.hasil.KeputusanPaparRemSBMActionBean">
    <div class="subtitle">
            <%--<table width="100%" border="0" bgcolor="green">
                <tr>
                    <td><h5>&nbsp;</h5></td>
                    <td align="right">&nbsp;</td>
                </tr>
            </table>--%>
        <fieldset class="aras1">
            <legend>
                Maklumat Maklumat Hakmilik
            </legend>
            <p>
                <label>ID Hakmilik :</label>
                ${actionBean.hakmilikPermohonan.hakmilik.idHakmilik}&nbsp;
            </p>
            <p>
                <label>Nombor Lot :</label>
                ${actionBean.hakmilikPermohonan.hakmilik.noLot}&nbsp;
            </p>
            <p>
                <label>Luas :</label>
                <s:format formatPattern="#,##0.0000" value="${actionBean.hakmilikPermohonan.hakmilik.luas}" />&nbsp;${actionBean.hakmilikPermohonan.hakmilik.kodUnitLuas.nama}
            </p>
            <p>
                <label>Cukai Tanah (RM) :</label>
                <s:format formatPattern="#,##0.00" value="${actionBean.hakmilikPermohonan.hakmilik.cukai}" />&nbsp;
            </p>
            <p>
                <label>Daerah :</label>
               ${actionBean.hakmilikPermohonan.hakmilik.daerah.nama}&nbsp;
            </p>
            <p>
                <label>Bandar / Pekan / Mukim :</label>
               ${actionBean.hakmilikPermohonan.hakmilik.bandarPekanMukim.nama}&nbsp;
            </p>
            <p>
                <label>Kategori Tanah :</label>
              ${actionBean.hakmilikPermohonan.hakmilik.kategoriTanah.nama}&nbsp;
            </p>
            <p>
                <label>Syarat Nyata :</label>
                <s:textarea name="hakmilikPermohonan.hakmilik.syaratNyata.syarat" style="overflow-x: hidden;" cols="60" rows="4" readonly="true"/>&nbsp;
            </p>
        </fieldset>
            <br>
        <fieldset class="aras1">
            <legend>
                Maklumat Sekolah Bantuan Modal
            </legend>
            <p>
                <label>Nama Sekolah :</label>
                ${actionBean.hakmilikAlamat.namaInst}&nbsp;
            </p>
            <p>
                <label>Alamat :</label>
                ${actionBean.hakmilikAlamat.alamat1}&nbsp;
            </p>
            <p>
                <label>&nbsp;</label>
                ${actionBean.hakmilikAlamat.alamat2}&nbsp;
            </p>
            <p>
                <label>&nbsp;</label>
                ${actionBean.hakmilikAlamat.alamat3}&nbsp;
            </p>
            <p>
                <label>&nbsp;</label>
                ${actionBean.hakmilikAlamat.alamat4}&nbsp;
            </p>
            <p>
                <label>Poskod :</label>
                ${actionBean.hakmilikAlamat.poskod}&nbsp;
            </p>
            <p>
                <label>Negeri :</label>
               ${actionBean.hakmilikAlamat.alamatNegeri.nama}&nbsp;
            </p>
        </fieldset>
            <br>
        <fieldset class="aras1">
            <legend>
                Maklumat Pemohon
            </legend>
            <div class="content" align="center">

                <display:table name="${actionBean.listPemohon}" id="line1" class="tablecloth" requestURI="/common/maklumat_pemohon">
                    <%--<display:column><s:checkbox name="arrayCheckBox" id="arrayCheckBox${line1_rowNum -1}" value="${line1.pihak.idPihak}" class="chkbox"/></display:column>--%>
                    <display:column title="No">
                        ${line1_rowNum}
                        <s:hidden name="x" class="x${line1_rowNum -1}" value="${line1.pihak.idPihak}"/>
                    </display:column>
                    <%--<display:column property="pihak.idPihak" title="Id Pihak" />--%>
                    <display:column property="pihak.nama" title="Nama"/>
                    <display:column property="pihak.noPengenalan" title="No. Pengenalan" />
                    <display:column title="Alamat" class="alamat">
                        ${line1.pihak.suratAlamat1}<c:if test="${line1.pihak.suratAlamat2 ne null}">,</c:if>
                        ${line1.pihak.suratAlamat2}<c:if test="${line1.pihak.suratAlamat3 ne null}">,</c:if>
                        ${line1.pihak.suratAlamat3}<c:if test="${line1.pihak.suratAlamat4 ne null}">,</c:if>
                        ${line1.pihak.suratAlamat4}<c:if test="${line1.pihak.suratPoskod ne null}">,</c:if>
                        ${line1.pihak.suratPoskod}<c:if test="${line1.pihak.suratNegeri.nama ne null}">,</c:if>
                        ${line1.pihak.suratNegeri.nama}
                    </display:column>
                    <display:column property="pihak.noTelefon1" title="No. Telefon" />
                </display:table>
            </div>
        </fieldset>
            <br>
        <fieldset class="aras1">
            <legend>
                Maklumat Sokongan
            </legend>
                <p>
                    <label>Disahkan Oleh :</label>
                    ${actionBean.permohonanRujukanLuar.agensi.nama}&nbsp;
                </p>
                <p>
                    <label>Nombor Rujukan :</label>
                    ${actionBean.permohonanRujukanLuar.noRujukan}&nbsp;
                    <%--<s:text name="permohonanUlasan.noRujukan" style="width:160px;" /><s:hidden name="permohonanUlasan.idUlasan" />&nbsp;--%>
                </p>
                <p>
                    <label>Tarikh :</label>
                    <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.permohonanRujukanLuar.tarikhRujukan}"/>&nbsp;
                    <%--<s:text name="permohonanUlasan.tarikhUlasan" id="datepicker" class="datepicker" formatPattern="dd/MM/yyyy"/>--%>
                </p>
                <p>
                    <label>Ulasan :</label>
                    ${actionBean.permohonanRujukanLuar.ulasan}&nbsp;
                    <%--<s:textarea name="permohonanUlasan.ulasan" cols="30" rows="3"/>&nbsp;--%>
                </p>
        </fieldset>
            <br>
        <fieldset class="aras1">
            <legend>
                Maklumat Perihal Cukai
            </legend>
            <p>
                <label>Tempoh Tunggakan : </label>
                <s:text name="tunggakan" value="${actionBean.tahunTunggakan}" disabled="true"/>
            </p>
            <p>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.listTransaksi}"  cellpadding="0" cellspacing="0" id="line">
                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                        <display:column title="Tarikh"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.kodTransaksi.infoAudit.tarikhMasuk}"/></display:column>
                        <display:column title="Transaksi" >${line.kodTransaksi.kod} - ${line.kodTransaksi.nama}</display:column>
                        <display:column title="Amaun (RM)"><div align="right"><fmt:formatNumber pattern="#,##0.00" value="${line.amaun}"/></div></display:column>
                        <display:footer>
                            <tr>
                                <td colspan="3"><div align="right">Jumlah (RM):</div></td>
                                <td><div align="right"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.akaunBaki.baki}"/></div></td>
                            </tr>
                        </display:footer>
                    </display:table>
                </div>
            </p>
        </fieldset>
        <fieldset class="aras1">
            <legend>Notis 6A</legend>
            <p>
                <label>Tarikh Disampaikan :</label>
                <s:text name="tarikh_sampai" disabled="true" />
            </p>
            <p>
                <label>Tarikh Didaftarkan :</label>
                <s:text name="tarikh_daftar" disabled="true" />
            </p>
        </fieldset>
        <br>
        <table width="100%">
            <tr>
                <td align="right"><s:button style="display:none;" class="btn" onclick="doSubmit(this.form, this.name, 'page_div');" name="saveOrUpdate" value="Simpan"/>&nbsp;<s:reset style="display:none;" class="btn" name="reset" value="Isi Semula"/></td>
            </tr>
        </table>
    </div>
</s:form>
