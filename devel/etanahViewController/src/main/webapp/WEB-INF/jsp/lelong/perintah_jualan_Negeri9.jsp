<%-- 
    Document   : perintah_jualan_Negeri9
    Created on : Jun 3, 2010, 5:20:39 PM
    Author     : mdizzat.mashrom
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<s:form beanclass="etanah.view.stripes.lelong.PerintahJualanNegeri9ActionBean">

    <p>
        <s:messages />
        <s:errors />&nbsp;
    </p>
    <div align="center">
        <fieldset class="aras1">
            <legend>
                Perisytiharaan Jualan
            </legend><br><br>

            <p style="font-size: 11pt">
                <b><font style="font-size: 14pt">PERISYTIHARAAN JUALAN<br></font>
                    Perintah Jualan Atas Permintaan Pemegang Gadaian<br>
                    (PTS.369/196/07/03)</b><br>
                Dalam perkara mengenai Seksyen <b> 265(1)(a)</b> Kanun Tanah Negara,1965<br><br>
                Antara<br><br>
                <b> <c:forEach var="i" items="${actionBean.listPermohonanPihak}" >

                        <c:if test='${i.jenis.kod eq "PG"}'>
                            ${i.pihak.nama}&nbsp;.............${i.jenis.nama}
                        </c:if>
                        &nbsp;<br></c:forEach></b>

                    

                    Dan<br><br>
                    <b> <c:forEach var="i" items="${actionBean.listPermohonanPihak}" >

                        <c:if test='${i.jenis.kod ne "PG"}'>
                            ${i.pihak.nama}&nbsp;.............
                        </c:if>
                        <c:if test='${i.jenis.kod ne "PG"}'>
                            <c:out value="Penggadai"></c:out>
                        </c:if>
                        &nbsp;<br></c:forEach></b>

                    <br>
                </p>

                <p style="font-size: 11pt" align="left">
                    &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;Menurut Perintah <b>Pentadbir Tanah Daerah ${actionBean.hakmilik.daerah.nama}</b>
                yang diperbuat dalam perkara yang diatas bertarikh 
                <b><fmt:formatDate value="${actionBean.permohonan.infoAudit.tarikhMasuk}" pattern="dd MMMM yyyy" /></b> adalah ini
                diisytiharkan bahawa <b>Pentadbir Tanah Daerah ${actionBean.hakmilik.daerah.nama}</b>
                dengan dibantu oleh Pelelong yang tersebut di bawah
            </p><br>

            <p align="center" style="font-size: 11pt">
                <b>Akan Menjual Secara<br><br>
                    <font style="font-size: 14pt">Lelongan Awam</font><br><br>

                    PADA HARI <b><fmt:formatDate value="${actionBean.lelongan.tarikhLelong}" pattern="EEEE"/></b>
                    TARIKH <b><fmt:formatDate value="${actionBean.lelongan.tarikhLelong}" pattern="dd MMMM yyyy"/></b>
                    JAM <b><fmt:formatDate value="${actionBean.lelongan.tarikhLelong}" pattern="hh:mm aa"/></b><br>
                    DI <b>${actionBean.lelongan.tempat}</b>

                </b>
            </p><br>
            <p style="font-size: 9pt">
                <em>*</em><b>Nota : <u>Bakal-bakal pembeli adalah dinasihatkan agar membuat carian Hakmilik </u> secara rasmi di
                    Pejabat Tanah dan memeriksa hartanah tersebut sebelum jualan lelong.</b>
            </p><br>


            <table class="tablecloth" border="1" width="1100"  cellpadding="10000" cellspacing="0" style=""  pattern="#,##0.0000">

                <tr>
                    <th colspan="3">Butir-butir Hakmilik :</th>
                </tr>
                <tr align="left">
                    <td> <b> No.Hakmilik </b> </td>
                    <td><b>:</b></td>
                    <td> <b>&nbsp; ${actionBean.hakmilik.noHakmilik}</b> </td>
                </tr>
                <tr align="left" >
                    <td> <b> No.Lot/PT </b> </td>
                    <td><b>:</b></td>
                    <td> <b> &nbsp; ${actionBean.hakmilik.lot.nama}&nbsp;${actionBean.hakmilik.noLot}</b> </td>
                </tr>
                <tr align="left">
                    <td> <b> Mukim/Daerah/Negeri </b></td>
                    <td><b>:</b></td>
                    <td> <b> &nbsp; ${actionBean.hakmilik.cawangan.name}/&nbsp;${actionBean.hakmilik.daerah.nama}/&nbsp;${actionBean.hakmilik.bandarPekanMukim.nama}</b></td>
                </tr>
                <tr align="left">
                    <td> <b> Tempoh Pegangan Milik </b> </td>
                    <td><b>:</b></td>
                    <td> <b> &nbsp; ${actionBean.hakmilik.kodHakmilik.nama} </b> </td>
                </tr>
                <tr align="left">
                    <td> <b> Anggaran Keluasan Tanah </b> </td>
                    <td><b>:</b></td>
                    <td><b> &nbsp; <fmt:formatNumber pattern="#,##0.0000" value="${actionBean.hakmilik.luas}"/>&nbsp;${actionBean.hakmilik.kodUnitLuas.nama}</b></td>
                </tr>
                <tr align="left">
                    <td> <b> Cukai Tahunan </b><br><br> </td>
                    <td><b>:</b></td>
                    <td> <b> &nbsp; RM  <fmt:formatNumber pattern="#,##0.00" value="${actionBean.hakmilik.cukai}"/> </b> </td>
                </tr>
                <tr align="left">
                    <td> <b> Ketegori Kegunaan Tanah </b><br><br> </td>
                    <td><b>:</b></td>
                    <td> <b> &nbsp; ${actionBean.hakmilik.kategoriTanah.nama}</b> </td>
                </tr>
                <tr align="left">
                    <td> <b> Pemilik Berdaftar </b><br><br> </td>
                    <td><b>:</b></td>
                    <td> <b> <c:forEach var="i" items="${actionBean.listPermohonanPihak}" >

                        <c:if test='${i.jenis.kod ne "PG"}'>
                            ${i.pihak.nama}
                        </c:if>
                       
                        <br></c:forEach></b>
                        </td>
                    </tr>
                    <tr align="left">
                        <td> <b> Syarat nyata </b><br><br> </td>
                        <td><b>:</b></td>
                        <td> <b> &nbsp; ${actionBean.hakmilik.syaratNyata.syarat}</b> </td>
                </tr>
                <tr align="left">
                    <td> <b> Sekatan Kepentingan </b><br><br> </td>
                    <td><b>:</b></td>
                    <td align="justify"> <b> &nbsp; ${actionBean.hakmilik.sekatanKepentingan.sekatan}</b> </td>
                </tr>
                <tr align="left">
                    <td> <b> Bebanan-bebanan </b><br><br> </td>
                    <td><b>:</b></td>
                    <td> Digadai Kepada <b><c:forEach var="i" items="${actionBean.listPermohonanPihak}" ><c:if test='${i.jenis.kod == "PG"}'>${i.pihak.nama}</c:if></c:forEach></b> <br>
                        Melalui Perserahan No :- <b>${actionBean.permohonan.penyerahNoRujukan}/${actionBean.permohonan.untukTahun},&nbsp;
                            <br>Volume No.${actionBean.permohonan.folderDokumen.noJilid},
                            &nbsp;Folio No.${actionBean.permohonan.folderDokumen.noFolio},<br>
                            Bertarikh : <fmt:formatDate value="${actionBean.permohonan.folderDokumen.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy"/>
                            <br></b>
                    </td>
                </tr>

            </table><br>

            <p style="font-size: 11pt" align="left">
                <b>Lokasi Dan Keterangan : <br>  </b><br>
                    ${actionBean.hakmilik.kegunaanTanah.perihal}
            </p><br>

            <p style="font-size: 11pt" align="left">
                <b>Harga Rizab : </b><br><br>
                Hartanah tersebut akan dijual tertakluk kepada syarat-syarat jualan dan harga rizab sebanyak 
                <b>RM <fmt:formatNumber pattern="#,##0.00" value="${actionBean.lelongan.hargaRizab}"/>&nbsp;(${actionBean.lelongan.ejaanHarga})</b>.
                Bagi penawar-penawar yang berminat adalah dikehendaki mendeposit <b>10%</b> daripada harga rizab dalam bentuk <b>Draf Bank</b>
                atas nama <b><c:forEach var="i" items="${actionBean.listPermohonanPihak}" ><c:if test='${i.jenis.kod == "PG"}'>${i.pihak.nama}</c:if></c:forEach></b> pada hari lelongan dan baki harga belian hendaklah dibayar dalam tempoh
                <b>120 Hari(Seratus Dua Puluh)</b> dari tarikh jualan
                kepada <b><c:forEach var="i" items="${actionBean.listPermohonanPihak}" ><c:if test='${i.jenis.kod == "PG"}'>${i.pihak.nama}</c:if></c:forEach></b>.<br><br>

                Untuk butir-butir lanjut, sila berhubung dengan <b>Pentadbir Tanah Daerah ${actionBean.hakmilik.daerah.nama}</b>
                atau Peguamcara bagi Pememgang Gadaian <b>${actionBean.permohonan.penyerahNama}</b>
                beralamat di <b>${actionBean.permohonan.penyerahAlamat1},${actionBean.permohonan.penyerahAlamat2},${actionBean.permohonan.penyerahAlamat3},
                    ${actionBean.permohonan.penyerahAlamat4},${actionBean.permohonan.penyerahPoskod},${actionBean.permohonan.penyerahNegeri.nama}</b>


            </p>

            <br>
        </fieldset>
    </div>
</s:form>