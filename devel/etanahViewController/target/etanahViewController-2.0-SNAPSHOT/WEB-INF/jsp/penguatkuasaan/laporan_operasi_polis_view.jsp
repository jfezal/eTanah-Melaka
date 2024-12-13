<%-- 
    Document   : laporan_operasi_polis_view
    Created on : May 17, 2010, 4:52:19 PM
    Author     : nurshahida.radzi
    Modified by : ctzainal - add new column -tempatKerja
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<style type="text/css">
    .tablecloth td{
        text-align:left;padding:0.3em;border:1px solid #fff;background:#e5f1f4;vertical-align: top;text-transform:uppercase;
    }

</style>

<script type="text/javascript">

    $(document).ready(function() {
        if($('#kodNegeri').val() == '05'){
            if($('#kodUrusan').val() == '427' || $('#kodUrusan').val()=='422'){
                document.getElementById("427").style.visibility = 'visible';
                document.getElementById("427").style.display = '';
                document.getElementById("lainlainSeksyen").style.visibility = 'hidden';
                document.getElementById("lainlainSeksyen").style.display = 'none';
            } else{
                document.getElementById("427").style.visibility = 'hidden';
                document.getElementById("427").style.display = 'none';
                document.getElementById("lainlainSeksyen").style.visibility = 'visible';
                document.getElementById("lainlainSeksyen").style.display = '';
            }

        }else{
            //else for MLK
            document.getElementById("427").style.visibility = 'hidden';
            document.getElementById("427").style.display = 'none';
            document.getElementById("lainlainSeksyen").style.visibility = 'visible';
            document.getElementById("lainlainSeksyen").style.display = '';
        }


    });

    function limitText(limitField, limitNum) {
        if (limitField.value.length > limitNum) {
            limitField.value = limitField.value.substring(0, limitNum);
        }
    }

    function validateForm(){

        if($('#ulasanLOP').val() == ''){
            alert("Sila masukkan ulasan.");
            $('#ulasanLOP').focus();
            return false;
        }
        return true;
    }

    function doViewReport(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }
</script>

<s:form beanclass="etanah.view.penguatkuasaan.LaporanOperasiPolisActionBean">
    <s:messages/>
    <s:errors/>
    <s:hidden name="kodUrusan" id="kodUrusan" value="${actionBean.permohonan.kodUrusan.kod}"/>
    <s:hidden name="kodNegeri" id="kodNegeri"/>
    <c:if test="${!ulasanPPTK}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Laporan Siasatan
                </legend>

                <c:if test="${actionBean.senaraiMohonHakmilik eq true}">
                    <div class="subtitle">
                        <fieldset class="aras1">
                            <legend>
                                Maklumat Hakmilik
                            </legend>

                            <div class="content" align="left">
                                <p>Klik butang tambah untuk masukkan maklumat hakmilik</p>
                            </div>
                            <br/>

                            <div class="content">
                                <div class="subtitle">
                                    <fieldset class="aras1">
                                        <div class="content" align="center">
                                            <div id="multipleHakmilikDiv">
                                                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0"
                                                               id="line">
                                                    <display:column title="Bil" sortable="true" style="vertical-align:baseline">
                                                        <c:if test="${line.hakmilik.idHakmilik eq null}">
                                                            Tiada rekod
                                                        </c:if>
                                                        <c:if test="${line.hakmilik.idHakmilik ne null}">
                                                            ${line_rowNum}
                                                        </c:if>
                                                    </display:column>
                                                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="popup hakmilik${line_rowNum}" style="vertical-align:baseline"/>
                                                    <display:column property="hakmilik.noLot" title="Nombor Lot/PT" style="vertical-align:baseline"/>
                                                    <display:column title="Luas" style="vertical-align:baseline"><fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                                                    <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah" style="vertical-align:baseline"/>
                                                    <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" style="vertical-align:baseline"/>
                                                    <display:column property="hakmilik.maklumatAtasTanah" title="Jenis Penggunaan Tanah" />
                                                </display:table>

                                                <br>

                                                Senarai Pemilik  <br>
                                                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" id="line">
                                                    <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                                                    <display:column title="No. Hakmilik" >${line.hakmilik.kodHakmilik.kod}${line.hakmilik.noHakmilik}</display:column>
                                                    <display:column title="Nama">
                                                        <c:set value="1" var="count"/>
                                                        <c:forEach items="${actionBean.listPemilik}" var="senarai">
                                                            <c:if test="${line.hakmilik.idHakmilik eq senarai.hakmilik.idHakmilik}">
                                                                <c:out value="${count}"/>)&nbsp;
                                                                <c:out value="${senarai.pihak.nama}"/>&nbsp;&nbsp;<br>
                                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;No.KP/Co :&nbsp;<c:out value="${senarai.pihak.noPengenalan}"/>
                                                                <c:set value="${count + 1}" var="count"/>
                                                                <br>   
                                                            </c:if>
                                                        </c:forEach>
                                                    </display:column>
                                                    <display:column title="Jenis Pihak Berkepentingan">
                                                        <c:set value="1" var="count"/>
                                                        <c:forEach items="${actionBean.listPemilik}" var="senarai">
                                                            <c:if test="${line.hakmilik.idHakmilik eq senarai.hakmilik.idHakmilik}">
                                                                <c:out value="${count}"/>)&nbsp;
                                                                <c:out value="${senarai.jenis.nama}"/><br>
                                                                <c:set value="${count + 1}" var="count"/><br>
                                                            </c:if>
                                                        </c:forEach>
                                                    </display:column>
                                                    <display:column title="Syer yang dimiliki">
                                                        <c:set value="1" var="count"/>
                                                        <c:forEach items="${actionBean.listPemilik}" var="senarai">
                                                            <c:if test="${line.hakmilik.idHakmilik eq senarai.hakmilik.idHakmilik}">
                                                                <c:out value="${count}"/>)&nbsp;
                                                                <c:out value="${senarai.syerPembilang}/${senarai.syerPenyebut}"/><br>
                                                                <c:set value="${count + 1}" var="count"/><br>
                                                            </c:if>
                                                        </c:forEach>
                                                    </display:column>
                                                    <display:column title="Tarikh Pemilikan Didaftar">
                                                        <fmt:formatDate pattern="dd/MM/yyyy" value="${line.hakmilik.tarikhDaftar}"/>
                                                    </display:column>
                                                </display:table>
                                                <br>

                                                Senarai Pihak Berkepentingan <br>
                                                <div id="DocHakmilikDiv">
                                                    <display:table class="tablecloth" name="${actionBean.listPihakBerkepentingan}" id="line">
                                                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                                                        <display:column title="No. Hakmilik" >${line.hakmilik.kodHakmilik.kod}${line.hakmilik.noHakmilik}</display:column>
                                                        <display:column title="Nama">
                                                            <c:set value="1" var="count"/>
                                                            <c:if test="${line.jenis.kod ne 'PM'}">
                                                                <c:out value="${count}"/>)&nbsp;
                                                                <c:out value="${line.pihak.nama}"/>&nbsp;&nbsp;<br>
                                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;No.KP/Co :&nbsp;<c:out value="${line.pihak.noPengenalan}"/>
                                                                <c:set value="${count + 1}" var="count"/>
                                                                <br>
                                                            </c:if>
                                                        </display:column>
                                                        <display:column title="Jenis Pihak Berkepentingan">
                                                            <c:set value="1" var="count"/>
                                                            <c:if test="${line.jenis.kod ne 'PM'}">
                                                                <c:out value="${count}"/>)&nbsp;
                                                                <c:out value="${line.jenis.nama}"/><br>
                                                                <c:set value="${count + 1}" var="count"/><br>
                                                            </c:if>
                                                        </display:column>
                                                        <display:column title="Syer yang dimiliki">
                                                            <c:set value="1" var="count"/>
                                                            <c:out value="${count}"/>)&nbsp;
                                                            <c:out value="${line.syerPembilang}/${line.syerPenyebut}"/><br>
                                                            <c:set value="${count + 1}" var="count"/><br>
                                                        </display:column>
                                                        <display:column title="Tarikh Pemilikan Didaftar">
                                                            <fmt:formatDate pattern="dd/MM/yyyy" value="${line.hakmilik.tarikhDaftar}"/>
                                                        </display:column>
                                                    </display:table>
                                                </div>

                                                Senarai Waris <br>


                                                <display:table name="${actionBean.listWaris}" id="line2" class="tablecloth">
                                                    <display:column title="Bil">
                                                        ${line2_rowNum}
                                                        <s:hidden name="x" class="x${line2_rowNum -1}" value=""/>
                                                    </display:column>
                                                    <display:column property="nama" title="Nama" />
                                                    <display:column property="noPengenalan" title="No. Pengenalan" />
                                                    <display:column title="Syer"> ${line2.syerPembilang }/${line2.syerPenyebut} </display:column>
                                                </display:table>

                                                Tanah Milik<br>
                                                <div class="content" align="center">
                                                    <display:table  name="${actionBean.permohonan.senaraiHakmilik}" id="line" class="tablecloth">
                                                        <display:column title="Jenis Hakmilik">
                                                            <c:if test="${line.hakmilik.kodHakmilik.nama ne null}"> ${line.hakmilik.kodHakmilik.nama}&nbsp; </c:if>
                                                            <c:if test="${line.hakmilik.kodHakmilik.nama eq null}"> Tiada </c:if>
                                                        </display:column>

                                                        <display:column title="Nombor Hakmilik">
                                                            <c:if test="${line.hakmilik.idHakmilik ne null}"> ${line.hakmilik.idHakmilik}&nbsp; </c:if>
                                                            <c:if test="${line.hakmilik.idHakmilik eq null}"> Tiada </c:if>
                                                        </display:column>

                                                        <display:column title="Nombor Lot/PT" >
                                                            <c:if test="${line.hakmilik.noLot ne null}"> ${line.hakmilik.noLot}&nbsp; </c:if>
                                                            <c:if test="${line.hakmilik.noLot eq null}"> Tiada </c:if>
                                                        </display:column>
                                                        <display:column title="Luas">
                                                            <c:if test="${line.hakmilik.luas ne null}"> <fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</c:if>
                                                            <c:if test="${line.hakmilik.luas eq null}"> Tiada </c:if>
                                                        </display:column>
                                                        <display:column property="hakmilik.kategoriTanah.nama" title="Kegunaan" >
                                                        </display:column>
                                                        <display:column title="Syarat Nyata">
                                                            <c:if test="${line.hakmilik.syaratNyata.syarat ne null}"> ${line.hakmilik.syaratNyata.syarat}&nbsp; </c:if>
                                                            <c:if test="${line.hakmilik.syaratNyata.syarat eq null}"> Tiada </c:if>
                                                        </display:column>
                                                        <display:column property="hakmilik.rizab.nama" title="Rizab" >
                                                            <c:if test="${actionBean.hakmilik.rizab.nama ne null}"> ${actionBean.hakmilik.rizab.nama}&nbsp; </c:if>
                                                            <c:if test="${actionBean.hakmilik.rizab.nama eq null}"> Tiada </c:if>
                                                        </display:column>

                                                        <display:column title="Cukai (RM)">
                                                            <c:if test="${line.hakmilik.cukai ne null}"> <fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.cukai}"/>&nbsp; </c:if>
                                                            <c:if test="${line.hakmilik.cukai eq null}"> Tiada </c:if>
                                                        </display:column>


                                                    </display:table>
                                                </div>


                                            </div>
                                        </div>
                                    </fieldset>
                                </div>
                            </div>
                        </fieldset>
                    </div>
                </c:if>
                <div class="content" >
                    <div id="lainlainSeksyen">


                        <p>
                            <label>Jenis Tindakan :</label>&nbsp;
                            <c:if test="${actionBean.jenisTindakan ne null}">
                                <s:textarea name="jenisTindakan" id="jenisTindakan" cols="80" rows="5" readonly="true" onchange="this.value=this.value.toUpperCase();" disabled="false" />&nbsp;
                            </c:if>
                            <%--<c:if test="${actionBean.jenisTindakan ne null}"> ${actionBean.jenisTindakan}&nbsp;</c:if>--%>
                            <c:if test="${actionBean.jenisTindakan eq null}"> Tiada Data </c:if>
                        </p>
                        <p>
                            <label>Tarikh Laporan:</label>&nbsp;
                            <c:if test="${actionBean.tarikhOperasi ne null}">${actionBean.tarikhOperasi}&nbsp;</c:if>
                            <c:if test="${actionBean.tarikhOperasi eq null}"> Tiada Data </c:if>


                        </p>
                        <p>
                            <label>Masa Laporan :</label>&nbsp;
                            <c:if test="${actionBean.tarikhOperasi ne null}">${actionBean.jam}:${actionBean.minit}&nbsp;${actionBean.ampm}&nbsp;</c:if>
                            <c:if test="${actionBean.tarikhOperasi eq null}"> Tiada Data </c:if>

                        </p>
                        <p>
                            <label>Tarikh Penahanan</label>&nbsp;
                            <c:if test="${actionBean.tarikhPenahanan ne null}">${actionBean.tarikhPenahanan}&nbsp;</c:if>
                            <c:if test="${actionBean.tarikhPenahanan eq null}"> Tiada Data </c:if>


                        </p>
                        <p>
                            <label>Masa Penahanan :</label>&nbsp;
                            <c:if test="${actionBean.tarikhPenahanan ne null}">${actionBean.hour}:${actionBean.minute}&nbsp;${actionBean.ampm1}&nbsp;&nbsp;</c:if>
                            <c:if test="${actionBean.tarikhPenahanan eq null}"> Tiada Data </c:if>

                        </p>
                        <p>
                            <label>Kawasan Rondaan :</label>&nbsp;
                            <c:if test="${actionBean.lokasi ne null}">${actionBean.lokasi}&nbsp;</c:if>
                            <c:if test="${actionBean.lokasi eq null}"> Tiada Data </c:if>
                        </p>
                        <p>
                            <label>Kenderaan Operasi :</label>&nbsp;
                            <c:if test="${actionBean.platKenderaan ne null}">${actionBean.platKenderaan}&nbsp;</c:if>
                            <c:if test="${actionBean.platKenderaan eq null}"> Tiada Data </c:if>
                        </p>
                        <p>
                            <label>Jumlah Tangkapan :</label>&nbsp;
                            <c:if test="${actionBean.jumlahTangkapan ne null}">${actionBean.jumlahTangkapan}&nbsp;</c:if>
                            <c:if test="${actionBean.jumlahTangkapan eq null}"> Tiada Data </c:if>
                        </p>
                        <p>
                            <label>Nilai Anggaran(RM):</label>&nbsp;
                            <c:if test="${actionBean.nilaiKecurian ne null}">
                                <s:format formatPattern="#,##0.00" value="${actionBean.nilaiKecurian}"/></c:if>
                            <c:if test="${actionBean.nilaiKecurian eq null}"> Tiada Data </c:if>
                        </p>
                        <p>
                            <label>Jumlah Rampasan/Sitaan :</label>&nbsp;
                            <c:if test="${actionBean.jumlahTangkapan ne null}">${actionBean.jumlahTangkapan}&nbsp;</c:if>
                            <c:if test="${actionBean.jumlahTangkapan eq null}"> Tiada Data </c:if>
                        </p>
                        <p>
                            <label>Laporan Operasi :</label>&nbsp;
                            <c:if test="${actionBean.catatan ne null}">
                                <s:textarea name="catatan" id="catatan" rows="7" cols="80" readonly="true" onchange="this.value=this.value.toUpperCase();" disabled="false" />&nbsp;
                            </c:if>
                            <%--<c:if test="${actionBean.catatan ne null}"> ${actionBean.catatan}&nbsp;</c:if>--%>
                            <c:if test="${actionBean.catatan eq null}"> Tiada Data </c:if>
                        </p>
                        <p>
                            <label>Senarai Pasukan :</label>&nbsp;
                        </p>
                        <br>
                        <br>

                        <p align="center"><u><label>Ketua Operasi/Ketua Serbuan</label></u></p><br/>

                        <p>
                            <label>Nama :</label>
                            <c:if test="${actionBean.namaKetuaPasukan ne null}">${actionBean.namaKetuaPasukan}&nbsp;</c:if>
                            <c:if test="${actionBean.namaKetuaPasukan eq null}"> Tiada Data </c:if>
                        </p>

                        <p>
                            <label>No.pengenalan :</label>
                            <c:if test="${actionBean.noPengenalanKetua ne null}">${actionBean.noPengenalanKetua}&nbsp;</c:if>
                            <c:if test="${actionBean.noPengenalanKetua eq null}"> Tiada Data </c:if>
                        </p>

                        <p>
                            <label>Jawatan :</label>
                            <c:if test="${actionBean.tempatKerjaKetua ne null}">${actionBean.tempatKerjaKetua}&nbsp;</c:if>
                            <c:if test="${actionBean.tempatKerjaKetua eq null}"> Tiada Data </c:if>
                        </p>

                        <p>
                            <label>No.Kad Kuasa :</label>
                            <c:if test="${actionBean.kadKuasaKetua ne null}">${actionBean.kadKuasaKetua}&nbsp;</c:if>
                            <c:if test="${actionBean.kadKuasaKetua eq null}"> Tiada Data </c:if>
                            <%--<s:hidden name="idKetua" id="idKetua"/>--%>
                        </p>
                        <br>
                        <br>
                        <p align="center">
                            <display:table name="${actionBean.senaraiPasukanWithoutKetua}" id="line" class="tablecloth" pagesize="10">
                                <display:column title="Bil">
                                    ${line_rowNum}
                                </display:column>
                                <display:column property="nama" title="Nama"/>
                                <display:column property="noKp" title="No. Pengenalan" />
                                <display:column property="kodPerananOperasi.nama" title="Peranan" />
                                <display:column property="kadKuasa" title="Kad Kuasa" />
                                <display:column property="tempatKerja" title="Jabatan" />
                            </display:table>
                        </p>


                        <p>
                            <label>Lampiran :</label>
                        </p>

                        <p align="center">
                            <c:set value="1" var="count"/>
                            <c:forEach items="${actionBean.permohonan.folderDokumen.senaraiKandungan}" var="senarai">
                                <c:if test="${senarai.dokumen.kodDokumen.kod eq 'LO'}">
                                    <c:if test="${senarai.dokumen.namaFizikal != null}">
                                        <br>
                                        -
                                        <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                             onclick="doViewReport('${senarai.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                             onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${senarai.dokumen.kodDokumen.nama}"/>
                                        <%--${senarai.dokumen.kodDokumen.nama} ${count} (--%>${senarai.dokumen.tajuk}<%--)--%>
                                        <c:set value="${count+1}" var="count"/>
                                    </c:if>
                                </c:if>
                            </c:forEach>
                        </p>

                        <br/>
                    </div>

                    <div id="427">


                        <p>
                            <label>Tarikh Siasatan:</label>&nbsp;
                            <c:if test="${actionBean.tarikhOperasi ne null}">${actionBean.tarikhOperasi}&nbsp;</c:if>
                            <c:if test="${actionBean.tarikhOperasi eq null}"> Tiada Data </c:if>


                        </p>
                        <p>
                            <label>Masa Siasatan :</label>&nbsp;
                            <c:if test="${actionBean.tarikhOperasi ne null}">${actionBean.jam}:${actionBean.minit}&nbsp;${actionBean.ampm}&nbsp;</c:if>
                            <c:if test="${actionBean.tarikhOperasi eq null}"> Tiada Data </c:if>

                        </p>

                        <p>
                            <label>Lokasi Siasatan :</label>&nbsp;
                            <c:if test="${actionBean.lokasi ne null}">${actionBean.lokasi}&nbsp;</c:if>
                            <c:if test="${actionBean.lokasi eq null}"> Tiada Data </c:if>
                        </p>




                        <p>
                            <label>Laporan Keseluruhan Siasatan :</label>&nbsp;
                            <c:if test="${actionBean.catatan ne null}">
                                <s:textarea name="catatan" id="catatan" rows="7" cols="80" readonly="true" onchange="this.value=this.value.toUpperCase();"/>&nbsp;
                            </c:if>
                            <%--<c:if test="${actionBean.catatan ne null}"> ${actionBean.catatan}&nbsp;</c:if>--%>
                            <c:if test="${actionBean.catatan eq null}"> Tiada Data </c:if>
                        </p>
                        <p>
                            <label>Senarai Pasukan :</label>&nbsp;
                        </p>

                        <br>
                        <br>

                        <p align="center"><u><label>Ketua Operasi/Ketua Serbuan</label></u></p><br/>

                        <p>
                            <label>Nama :</label>
                            <c:if test="${actionBean.namaKetuaPasukan ne null}">${actionBean.namaKetuaPasukan}&nbsp;</c:if>
                            <c:if test="${actionBean.namaKetuaPasukan eq null}"> Tiada Data </c:if>
                        </p>

                        <p>
                            <label>No.pengenalan :</label>
                            <c:if test="${actionBean.noPengenalanKetua ne null}">${actionBean.noPengenalanKetua}&nbsp;</c:if>
                            <c:if test="${actionBean.noPengenalanKetua eq null}"> Tiada Data </c:if>
                        </p>

                        <p>
                            <label>Jawatan :</label>
                            <c:if test="${actionBean.tempatKerjaKetua ne null}">${actionBean.tempatKerjaKetua}&nbsp;</c:if>
                            <c:if test="${actionBean.tempatKerjaKetua eq null}"> Tiada Data </c:if>
                        </p>

                        <p>
                            <label>No.Kad Kuasa :</label>
                            <c:if test="${actionBean.kadKuasaKetua ne null}">${actionBean.kadKuasaKetua}&nbsp;</c:if>
                            <c:if test="${actionBean.kadKuasaKetua eq null}"> Tiada Data </c:if>
                            <%--<s:hidden name="idKetua" id="idKetua"/>--%>
                        </p>

                        <p align="center">
                            <display:table name="${actionBean.senaraiPasukanWithoutKetua}" id="line" class="tablecloth" pagesize="10">
                                <display:column title="Bil">
                                    ${line_rowNum}
                                </display:column>
                                <display:column property="nama" title="Nama"/>
                                <display:column property="noKp" title="No. Pengenalan" />
                                <display:column property="kodPerananOperasi.nama" title="Peranan" />
                                <display:column property="kadKuasa" title="Kad Kuasa" />
                                <display:column property="tempatKerja" title="Jabatan" />
                            </display:table>
                        </p>


                        <p>
                            <label>Lampiran :</label>
                        </p>

                        <p align="center">
                            <c:set value="1" var="count"/>
                            <c:forEach items="${actionBean.permohonan.folderDokumen.senaraiKandungan}" var="senarai">
                                <c:if test="${senarai.dokumen.kodDokumen.kod eq 'LO'}">
                                    <c:if test="${senarai.dokumen.namaFizikal != null}">
                                        <br>
                                        -
                                        <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                             onclick="doViewReport('${senarai.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                             onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${senarai.dokumen.kodDokumen.nama}"/>
                                        <%--${senarai.dokumen.kodDokumen.nama} ${count} (--%>${senarai.dokumen.tajuk}<%--)--%>
                                        <c:set value="${count+1}" var="count"/>
                                    </c:if>
                                </c:if>
                            </c:forEach>
                        </p>

                        <br/>
                    </div>

                </div>

                <c:if test="${actionBean.permohonan.kodUrusan.kod eq '426' && actionBean.kodNegeri eq '04'}">
                    <div class="subtitle">
                        <fieldset class="aras1">
                            <p>
                                <label>Ulasan Penolong Pegawai Tanah Kanan : </label>
                                <c:if test="${actionBean.ulasan ne null}"> ${actionBean.ulasan}&nbsp; </c:if>
                                <c:if test="${actionBean.ulasan eq null}"> Tiada Data </c:if>

                            </p>

                        </fieldset>
                    </div>
                </c:if>
            </fieldset>
        </div>
    </c:if>

    <c:if test="${ulasanPPTK}">

        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Laporan Operasi
                </legend>
                <div class="content" >
                    <p>
                        <label>Jenis Tindakan :</label>&nbsp;
                        <c:if test="${actionBean.jenisTindakan ne null}">
                            <s:textarea name="jenisTindakan" id="jenisTindakan" cols="80" rows="5" readonly="true" onchange="this.value=this.value.toUpperCase();" />&nbsp;
                        </c:if>
                        <c:if test="${actionBean.jenisTindakan eq null}"> Tiada Data </c:if>
                    </p>
                    <%--<p>
                        <label>Jenis Tindakan :</label>&nbsp;
                        <c:if test="${actionBean.jenisTindakan ne null}">${actionBean.jenisTindakan}&nbsp;</c:if>
                        <c:if test="${actionBean.jenisTindakan eq null}"> Tiada Data </c:if>
                    </p>--%>

                    <p>
                        <label>Tarikh Laporan:</label>&nbsp;
                        <c:if test="${actionBean.tarikhOperasi ne null}">${actionBean.tarikhOperasi}&nbsp;</c:if>
                        <c:if test="${actionBean.tarikhOperasi eq null}"> Tiada Data </c:if>


                    </p>
                    <p>
                        <label>Masa Laporan :</label>&nbsp;
                        <c:if test="${actionBean.tarikhOperasi ne null}">${actionBean.jam}:${actionBean.minit}&nbsp;${actionBean.ampm}&nbsp;</c:if>
                        <c:if test="${actionBean.tarikhOperasi eq null}"> Tiada Data </c:if>

                    </p>
                    <p>
                        <label>Tarikh Penahanan</label>&nbsp;
                        <c:if test="${actionBean.tarikhPenahanan ne null}">${actionBean.tarikhPenahanan}&nbsp;</c:if>
                        <c:if test="${actionBean.tarikhPenahanan eq null}"> Tiada Data </c:if>


                    </p>
                    <p>
                        <label>Masa Penahanan :</label>&nbsp;
                        <c:if test="${actionBean.tarikhPenahanan ne null}">${actionBean.hour}:${actionBean.minute}&nbsp;${actionBean.ampm1}&nbsp;&nbsp;</c:if>
                        <c:if test="${actionBean.tarikhPenahanan eq null}"> Tiada Data </c:if>

                    </p>
                    <p>
                        <label>Kawasan Rondaan :</label>&nbsp;
                        <c:if test="${actionBean.lokasi ne null}">${actionBean.lokasi}&nbsp;</c:if>
                        <c:if test="${actionBean.lokasi eq null}"> Tiada Data </c:if>
                    </p>
                    <p>
                        <label>Kenderaan Operasi :</label>&nbsp;
                        <c:if test="${actionBean.platKenderaan ne null}">${actionBean.platKenderaan}&nbsp;</c:if>
                        <c:if test="${actionBean.platKenderaan eq null}"> Tiada Data </c:if>
                    </p>
                    <p>
                        <label>Jumlah Tangkapan :</label>&nbsp;
                        <c:if test="${actionBean.jumlahTangkapan ne null}">${actionBean.jumlahTangkapan}&nbsp;</c:if>
                        <c:if test="${actionBean.jumlahTangkapan eq null}"> Tiada Data </c:if>
                    </p>
                    <p>
                        <label>Nilai Anggaran(RM):</label>&nbsp;
                        <c:if test="${actionBean.nilaiKecurian ne null}">
                            <s:format formatPattern="#,##0.00" value="${actionBean.nilaiKecurian}"/>
                        </c:if>
                        <c:if test="${actionBean.nilaiKecurian eq null}"> Tiada Data </c:if>
                    </p>

                    <p>
                        <label>Laporan Operasi :</label>&nbsp;
                        <c:if test="${actionBean.catatan ne null}">
                            <s:textarea name="catatan" id="catatan" rows="7" cols="80" readonly="true" onchange="this.value=this.value.toUpperCase();" />&nbsp;
                        </c:if>
                        <c:if test="${actionBean.catatan eq null}"> Tiada Data </c:if>
                    </p>

                    <p>
                        <label>Senarai Pasukan :</label>&nbsp;
                    </p>

                    <br>
                    <br>

                    <p align="center"><u><label>Ketua Operasi/Ketua Serbuan</label></u></p><br/>

                    <p>
                        <label>Nama :</label>
                        <c:if test="${actionBean.namaKetuaPasukan ne null}">${actionBean.namaKetuaPasukan}&nbsp;</c:if>
                        <c:if test="${actionBean.namaKetuaPasukan eq null}"> Tiada Data </c:if>
                    </p>

                    <p>
                        <label>No.pengenalan :</label>
                        <c:if test="${actionBean.noPengenalanKetua ne null}">${actionBean.noPengenalanKetua}&nbsp;</c:if>
                        <c:if test="${actionBean.noPengenalanKetua eq null}"> Tiada Data </c:if>
                    </p>

                    <p>
                        <label>Jawatan :</label>
                        <c:if test="${actionBean.tempatKerjaKetua ne null}">${actionBean.tempatKerjaKetua}&nbsp;</c:if>
                        <c:if test="${actionBean.tempatKerjaKetua eq null}"> Tiada Data </c:if>
                    </p>

                    <p>
                        <label>No.Kad Kuasa :</label>
                        <c:if test="${actionBean.kadKuasaKetua ne null}">${actionBean.kadKuasaKetua}&nbsp;</c:if>
                        <c:if test="${actionBean.kadKuasaKetua eq null}"> Tiada Data </c:if>
                        <%--<s:hidden name="idKetua" id="idKetua"/>--%>
                    </p>
                    <br>
                    <p align="center">
                        <display:table name="${actionBean.senaraiPasukanWithoutKetua}" id="line" class="tablecloth" pagesize="10">
                            <display:column title="Bil">
                                ${line_rowNum}
                            </display:column>
                            <display:column property="nama" title="Nama"/>
                            <display:column property="noKp" title="No. Pengenalan" />
                            <display:column title="Peranan" property="kodPerananOperasi.nama"/>
                            <display:column property="kadKuasa" title="Kad Kuasa" />
                            <display:column property="tempatKerja" title="Jabatan" />
                        </display:table>
                    </p>


                    <p>
                        <label>Lampiran :</label>
                    </p>

                    <p align="center">
                        <c:set value="1" var="count"/>
                        <c:forEach items="${actionBean.permohonan.folderDokumen.senaraiKandungan}" var="senarai">
                            <c:if test="${senarai.dokumen.kodDokumen.kod eq 'LO'}">
                                <c:if test="${senarai.dokumen.namaFizikal != null}">
                                    <br>
                                    -
                                    <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                         onclick="doViewReport('${senarai.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                         onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${senarai.dokumen.kodDokumen.nama}"/>
                                    <%--${senarai.dokumen.kodDokumen.nama} ${count} (--%>${senarai.dokumen.tajuk}<%--)--%>
                                    <c:set value="${count+1}" var="count"/>
                                </c:if>
                            </c:if>
                        </c:forEach>
                    </p>

                    <br/>

                    <p>
                        <label><em>*</em>Ulasan Penolong Pegawai Tanah Kanan : </label>
                        <s:textarea name="ulasan" id ="ulasanLOP"  cols="70" rows="10" onkeydown="limitText(this,1000);" onkeyup="limitText(this,1000);" onchange="this.value=this.value.toUpperCase();"/>&nbsp;

                    </p>

                    <p align="center">
                        <s:button name="simpanUlasan" id="save"  value="Simpan" class="btn"   onclick="if(validateForm())doSubmit(this.form, this.name, 'page_div');" />
                    </p>

                </div>
            </fieldset>
        </div>

    </c:if>
</s:form>
