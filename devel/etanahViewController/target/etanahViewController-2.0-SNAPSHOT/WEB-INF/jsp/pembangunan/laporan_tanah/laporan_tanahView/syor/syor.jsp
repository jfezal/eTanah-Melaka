<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<div id="syorPPT" align="center">
    <table class="tablecloth" align="center">
        <tr>
            <td>
                <table border="0">
                    <tr>
                        <td>Urusan Pembangunan Tanah : </td>
                        <c:if test="${actionBean.laporanTanah.syorUlasanPilihan eq '1'}" >
                            <td>Tukar Syarat / Pengubahan Jenis Penggunaan Sek 124 KTN</td>
                        </c:if>
                        <c:if test="${actionBean.laporanTanah.syorUlasanPilihan eq '2'}" >
                            <td>Letak Syarat / Meletak Jenis Penggunaan Sek 124 KTN</td>
                        </c:if>
                        <c:if test="${actionBean.laporanTanah.syorUlasanPilihan eq '3'}" >
                            <td>Pecah Sempadan Sek 135 KTN</td>
                        </c:if>
                        <c:if test="${actionBean.laporanTanah.syorUlasanPilihan eq '4'}" >
                            <td>Pecah Bahagian Sek 140 KTN</td>
                        </c:if>
                        <c:if test="${actionBean.laporanTanah.syorUlasanPilihan eq '5'}" >
                            <td>Penyatuan Tanah Sek 146 KTN</td>
                        </c:if>
                        <c:if test="${actionBean.laporanTanah.syorUlasanPilihan eq '6'}" >
                            <td>Mengubah Syarat dan Kategori Berkenaan dengan Bahagian Umpukan Sempadan Sek 124A KTN</td>
                        </c:if>    
                        <c:if test="${actionBean.laporanTanah.syorUlasanPilihan eq '7'}" >
                            <td>Penyerahan Balik dan Pemberimilikan Semula Sek 204A KTN</td>
                        </c:if>
                        <c:if test="${actionBean.laporanTanah.syorUlasanPilihan eq '8'}" >
                            <td>Serahbalik Keseluruhan Menurut Sek 197 KTN. Kemudian Diberimilik Semula Menurut Sek 76 KTN</td>
                        </c:if>
                        <c:if test="${actionBean.laporanTanah.syorUlasanPilihan eq '9'}" >
                            <td>Serahbalik Keseluruhan Tanah (12A)</td>
                        </c:if>
                        <c:if test="${actionBean.laporanTanah.syorUlasanPilihan eq '10'}" >
                            <td>Serahbalik Sebahagian Tanah (12B)</td>
                        </c:if>
                    </tr>
                </table>
                <br>
                <table border="0">
                    <tr>
                        <td colspan="2">Pelan tanah ini seperti bertanda&nbsp;${actionBean.laporanTanah.syorUrusan}&nbsp;di lampiran&nbsp;${actionBean.laporanTanah.syorLampiran}&nbsp;mengikut pelan yang dikemukakan oleh pemohon.</td>
                    </tr>
                    <c:if test="${actionBean.laporanTanah.syorPelanTanahPilihan eq '1'}" >
                        <tr>    
                            <td><s:checkbox name="check" checked="true" disabled="true"/></td>
                            <td>hendak dibelah bagi kepada ${actionBean.laporanTanah.syorNilaiBahagi}. 
                                <c:if test="${actionBean.laporanTanah.syorLotPertanian ne null}">
                                    Lot pertanian ${actionBean.laporanTanah.syorLotPertanian},
                                </c:if>
                                <c:if test="${actionBean.laporanTanah.syorLotKediaman ne null}">
                                    lot kediaman ${actionBean.laporanTanah.syorLotKediaman},
                                </c:if>
                                <c:if test="${actionBean.laporanTanah.syorLotPerniagaan ne null}">
                                    lot perniagaan ${actionBean.laporanTanah.syorLotPerniagaan},
                                </c:if>
                                <c:if test="${actionBean.laporanTanah.syorLotIndustri ne null}">
                                    lot perindustrian ${actionBean.laporanTanah.syorLotIndustri},
                                </c:if>
                                <c:if test="${actionBean.laporanTanah.syorLotLain ne null}">
                                    dan lot lain-lain ${actionBean.laporanTanah.syorLotLain}.
                                </c:if>
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${actionBean.laporanTanah.syorPelanTanah1 eq '2'}" >
                        <tr>    
                            <td><s:checkbox name="check" checked="true" disabled="true"/></td>
                            <td>hendak dicantum kepada&nbsp;${actionBean.laporanTanah.syorCantumBahagian}&nbsp;bahagian.</td>
                        </tr>
                    </c:if>
                    <c:if test="${actionBean.laporanTanah.syorPelanTanah2 eq '3'}" >
                        <td><s:checkbox name="check" checked="true" disabled="true"/></td>
                        <td>hendak ditukar syarat/pengubahan jenis penggunaan dari&nbsp;${actionBean.laporanTanah.syorTukarDari}&nbsp;kepada&nbsp;${actionBean.laporanTanah.syorTukarKepada}.</td>
                    </c:if>
                    <c:if test="${actionBean.laporanTanah.syorPelanTanah3 eq '4'}" >
                        <tr>    
                            <td><s:checkbox name="check" checked="true" disabled="true"/></td>
                            <td>hendak diserahbalik keseluruhan dan berimilik semula kepada 
                                <c:if test="${actionBean.laporanTanah.syorSerahLotPertanian ne null}">
                                    ${actionBean.laporanTanah.syorSerahLotPertanian} lot pertanian,  
                                </c:if>
                                <c:if test="${actionBean.laporanTanah.syorSerahLotKediaman ne null}">
                                    ${actionBean.laporanTanah.syorSerahLotKediaman} lot kediaman,
                                </c:if>
                                <c:if test="${actionBean.laporanTanah.syorSerahLotPerniagaan ne null}">
                                    ${actionBean.laporanTanah.syorSerahLotPerniagaan} lot perniagaan,
                                </c:if>
                                <c:if test="${actionBean.laporanTanah.syorSerahLotIndustri ne null}">
                                    ${actionBean.laporanTanah.syorSerahLotIndustri} lot industri
                                </c:if>
                                <c:if test="${actionBean.laporanTanah.syorSerahLotKemajuan ne null}">
                                    dan ${actionBean.laporanTanah.syorSerahLotKemajuan} lot kemajuan akan datang.
                                </c:if>
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${actionBean.laporanTanah.syorPelanTanah4 eq '5'}" >
                        <tr>    
                            <td><s:checkbox name="check" checked="true" disabled="true"/></td>
                            <td>serahbalik sebahagian tanah kepada kerajaan seluas ${actionBean.laporanTanah.syorSebahagianLuas} ${actionBean.laporanTanah.syorSebahagianOum} untuk tujuan ${actionBean.laporanTanah.syorSebahagianTujuan}.</td>
                        </tr>
                    </c:if>
                </table>

                <c:if test = "${!(actionBean.permohonan.kodUrusan.kod eq 'SBMS' || actionBean.permohonan.kodUrusan.kod eq 'TSPSS')}">
                    <c:if test="${actionBean.laporanTanah.syor eq 'Y'}">
                        <table border="0">
                            <tr>
                                <td><label>Diperakukan :</label></td>
                                <td><b>Ya</b>
                                </td>
                            </tr>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSPSS' || actionBean.permohonan.kodUrusan.kod eq 'SBMS'}">
                                <tr>
                                    <td><label>Premium tambahan :</label></td>
                                    <td><c:if test="${actionBean.hakmilikPermohonan.keteranganKadarPremium ne null}">RM ${actionBean.hakmilikPermohonan.keteranganKadarPremium}&nbsp; </c:if>
                                        <c:if test="${actionBean.hakmilikPermohonan.keteranganKadarPremium eq null}"> Tiada Data </c:if>
                                    </td>
                                </tr>
                            </c:if>
                            <tr>
                                <td><label>Denda premium(jika ada) :</label></td>
                                <td><c:if test="${actionBean.hakmilikPermohonan.dendaPremium ne null}">RM ${actionBean.hakmilikPermohonan.dendaPremium}&nbsp; </c:if>
                                    <c:if test="${actionBean.hakmilikPermohonan.dendaPremium eq null}"> Tiada Data </c:if>
                                </td>
                            </tr>
                            <tr>
                                <td><label>Sewa Tahunan Baru :</label></td>
                                <td><c:if test="${actionBean.hakmilikPermohonan.keteranganCukaiBaru ne null}">RM ${actionBean.hakmilikPermohonan.keteranganCukaiBaru}&nbsp; </c:if>
                                    <c:if test="${actionBean.hakmilikPermohonan.keteranganCukaiBaru eq null}"> Tiada Data </c:if>
                                </td>
                            </tr>
                            <%--
                            <tr>
                                <td><label>Luas (jika ada perubahan) :</label></td>
                                <td><c:if test="${actionBean.hakmilikPermohonan.luasTerlibat ne null}"> ${actionBean.hakmilikPermohonan.luasTerlibat}&nbsp;${actionBean.hakmilikPermohonan.kodUnitLuas.nama} </c:if>
                                <c:if test="${actionBean.hakmilikPermohonan.luasTerlibat eq null}"> Tiada Data </c:if>
                                </td>
                            </tr>
                            --%>


                            <c:choose>
                                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'TSPTD' || actionBean.permohonan.kodUrusan.kod eq 'TSPTG' || actionBean.permohonan.kodUrusan.kod eq 'TSMMK'}">
                                    <tr>
                                        <td><label>Kategori :</label></td>
                                        <td>${actionBean.hakmilikPermohonan.kategoriTanahBaru.nama}</td>
                                    </tr> 
                                    <tr>
                                        <td><label>Syarat Nyata : </label></td>
                                        <td><c:if test="${actionBean.hakmilikPermohonan.syaratNyataBaru.syarat ne null}">${actionBean.hakmilikPermohonan.syaratNyataBaru.syarat}</c:if>
                                            <c:if test="${actionBean.hakmilikPermohonan.syaratNyataBaru.syarat eq null}"> Tiada Data </c:if>
                                        </td>
                                        <s:hidden name="syaratBaru1" id="kod1" value="${actionBean.hakmilikPermohonan.syaratNyataBaru.kod}" />
                                    </tr>
                                    <tr>
                                        <td valign="top"><label>Sekatan Milik : </label></td>
                                        <td><c:if test="${actionBean.hakmilikPermohonan.sekatanKepentinganBaru.sekatan ne null}">${actionBean.hakmilikPermohonan.sekatanKepentinganBaru.sekatan}</c:if>
                                            <c:if test="${actionBean.hakmilikPermohonan.sekatanKepentinganBaru.sekatan eq null}"> Tiada Data </c:if>
                                        </td>
                                        <s:hidden name="sekatanBaru1" id="kod1" value="${actionBean.hakmilikPermohonan.sekatanKepentinganBaru.kod}" />
                                    </tr>
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        <td><label>Kategori :</label></td>
                                        <td>${actionBean.hakmilik.kategoriTanah.nama}</td>
                                    </tr> 
                                    <tr>
                                        <td><label>Syarat Nyata : </label></td>
                                        <td><c:if test="${actionBean.hakmilik.syaratNyata.syarat ne null}">${actionBean.hakmilik.syaratNyata.syarat}</c:if>
                                            <c:if test="${actionBean.hakmilik.syaratNyata.syarat eq null}"> Tiada Data </c:if>
                                        </td>
                                        <s:hidden name="syaratBaru1" id="kod1" value="${actionBean.hakmilik.syaratNyata.kod}" />
                                    </tr>
                                    <tr>
                                        <td valign="top"><label>Sekatan Milik : </label></td>
                                        <td><c:if test="${actionBean.hakmilik.sekatanKepentingan.sekatan ne null}">${actionBean.hakmilik.sekatanKepentingan.sekatan}</c:if>
                                            <c:if test="${actionBean.hakmilik.sekatanKepentingan.sekatan eq null}"> Tiada Data </c:if>
                                        </td>
                                        <s:hidden name="sekatanBaru1" id="kod1" value="${actionBean.hakmilik.sekatanKepentingan.kod}" />
                                    </tr>
                                </c:otherwise>
                            </c:choose>
                            <tr>
                                <td><label>Semua bayaran dijelaskan dalam tempoh:</label></td>
                                <c:choose>
                                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'TSPTD' || actionBean.permohonan.kodUrusan.kod eq 'TSPTG' || actionBean.permohonan.kodUrusan.kod eq 'TSMMK'}">
                                        <td><b>3 bulan dari tarikh Borang 7G.</b></td>
                                    </c:when>
                                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'SBMS'}">
                                        <td><b>3 bulan dari tarikh Borang 5A.</b></td>
                                    </c:when>
                                    <c:otherwise>
                                        <td><b>3 bulan.</b></td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                            <c:if test="${actionBean.textSyor}">
                                <tr>
                                    <td><label>&nbsp; </label></td>
                                    <td><b>Pemohon disyaratkan untuk membina rumah kos rendah kerana pembangunan melebihi 8 ekar.</b></td>
                                </tr>
                            </c:if>
                        </table>
                    </c:if>
                    <br>
                    <c:if test="${actionBean.laporanTanah.syor eq 'T'}">
                        <div align="center"><label>Diperakukan : Tidak</label></div>
                    </c:if>
                </c:if>
            </td>
        </tr>
    </table>
</div>