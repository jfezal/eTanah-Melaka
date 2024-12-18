<%-- 
    Document   : laporan_tanah
    Created on : Dec 24, 2013, 11:43:26 AM
    Author     : haqqiem
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<script type="text/javascript">
    $(document).ready(function() {
        $("#close").click(function() {
            setTimeout(function() {
                self.close();
            }, 100);
        });
    });
</script>

<div class="subtitle">
    <s:errors />
    <s:messages/>
    <s:form beanclass="etanah.view.stripes.hasil.LporanTanah6AActionBean">
        <fieldset class="aras1">
            <legend>
                A. Maklumat Lot
            </legend>
            <p>
                <label>ID Hakmilik :</label>
                ${actionBean.hakmilik.idHakmilik}&nbsp;
            </p>
            <p>
                <label>Mukim / Kawasan Bandar :</label>
                ${actionBean.hakmilik.bandarPekanMukim.nama}&nbsp;
            </p>
            <p>
                <label>No. Lot :</label>
                ${actionBean.hakmilik.lot.nama}&nbsp;${actionBean.hakmilik.noLot}&nbsp;
            </p>
            <p>
                <label>No. Hakmilik :</label>
                ${actionBean.hakmilik.noHakmilik}&nbsp;
            </p>
            <p>
                <label>Syarat Nyata :</label>
                <s:textarea name="hakmilik.syaratNyata.syarat" style="overflow-x: hidden;" cols="60" rows="4" readonly="true">
                    ${actionBean.hakmilik.syaratNyata.syarat}
                </s:textarea>&nbsp;
            </p>
            <p>
                <label>Luas :</label>
                <s:format formatPattern="#,##0.0000" value="${actionBean.hakmilik.luas}" />&nbsp;${actionBean.hakmilik.kodUnitLuas.nama}
            </p>
            <p>
                <label>Cukai Semasa :</label>
                RM &nbsp; <s:format formatPattern="#,###,##0.00" value="${actionBean.hakmilik.cukaiSebenar}" />&nbsp;
            </p>
            <p>
                <label>Denda Semasa :</label>
                RM &nbsp; <s:format formatPattern="#,###,##0.00" value="${actionBean.dendaSemasa}" />&nbsp;
            </p>
            <p>
                <label>Tunggakan Cukai :</label>
                RM &nbsp; <s:format formatPattern="#,###,##0.00" value="${actionBean.tunggakanCukai}" />&nbsp;
            </p>
            <p>
                <label>Tunggakan Denda :</label>
                RM &nbsp; <s:format formatPattern="#,###,##0.00" value="${actionBean.tunggakanDenda}" />&nbsp;
            </p>
            <p>
                <label>Tunggakan Dari Tahun :</label>
                ${actionBean.tunggakanTahun}&nbsp;
            </p>
        </fieldset>
        <br>
        <fieldset class="aras1">
            <legend>
                B. Laporan Tanah
            </legend>
            <p>
                <label>Lokasi :</label>
                <s:textarea name="lokasiTanah" cols="60" rows="4"/>
            </p>
            <p>
                <label>Keadaan Tanah :</label>
                <%--<s:textarea name="keadaanTanah" cols="60" rows="4"/>--%>
                <s:select name="keadaanTanah" id="condition" style="width:30%">
                    <s:option label="Pilih..." value="0" />
                    <s:options-collection collection="${listUtil.senaraiKodKeadaanTanah}"  label="nama" value="kod" />
                </s:select>
            </p>
            <p>
                <label>Bangunan (Jika Ada) :</label>
                <s:textarea name="bangunan" cols="60" rows="4"/>
            </p>
            <p>
                <label>Pemilik Bangunan :</label>
                <s:textarea name="pemilikBangunan" cols="60" rows="4"/>
            </p>
            <p>
                <label>Keadaan Bangunan :</label>
                <s:textarea name="keadaanBangunan" cols="60" rows="4"/>
            </p>
            <p>
                <label>Ulasan / Catatan :</label>
                <s:textarea name="ulasan" cols="60" rows="4"/>
            </p>
            <table border="0" bgcolor="green" width="100%">
                <tr>
                    <td align="right">
                        <s:submit name="simpanLaporanTanah" value="Simpan" class="btn"/>
                        <s:button name="" value="Tutup" class="btn" onclick="self.close();"/>
                    </td>
                </tr>
            </table>
        </fieldset>
    </s:form>
</div>