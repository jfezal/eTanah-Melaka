<%--
    Document   : utiliti_kertas_siasatan
    Created on :Nov 15, 2013, 2:03:22 PM
    Author     : latifah.iskak
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/popup.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-impromptu.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>


<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/styles/styles.css" />
        <title>Carian Kertas Siasatan</title>

    </head>
    <body>
        <script type="text/javascript">
         

            function validate(){
                return true;
            }

            function viewRecordOP(id){
                var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_operasi?popupViewLaporanOperasi&idOperasi='+id;
                window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
            }
            
            function popup(idBarang){
                var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_barang_tahanan?viewBarangDetail&idBarang='+idBarang;
                window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=600,scrollbars=yes");
            }
            
            function viewOKS(id){
                var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_orang_disyaki?viewOrangKenaSyak&idOrangKenaSyak='+id;
                window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
            }
            
            function doViewReport(v) {
                var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
                window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
            }



        </script>
        <s:messages />
        <s:errors />

        <s:form beanclass="etanah.view.penguatkuasaan.UtilitiKertasSiasatanActionBean" name="form1">

            <fieldset class="aras1">

                <legend>Carian Fail Lama</legend>
                <br>
                <p><label for="idPermohonan">Id Permohonan/ID Perserahan :</label>
                    <input type="text" name="idPermohonan" id="idMohon" onkeyup="this.value=this.value.toUpperCase();"/>
                    <s:submit name="checkStatusKertasSiasatan" value="Cari" class="btn"/>
                </p>

                <p>
                    <label>Id Permohonan :</label>
                    ${actionBean.idPermohonan} &nbsp;
                </p>

            </fieldset>

            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Maklumat Fail Lama
                    </legend>
                    <div class="content" >
                        <p>&nbsp;</p>


                        <!-- ******************************** view ******************************** -->
                        <p>
                            <b>1.&emsp;Negeri : </b> ${actionBean.kodNegeri.nama}  &nbsp;
                        </p>
                        <p>
                            &emsp;&emsp;<b>Daerah : </b>
                            ${actionBean.permohonan.cawangan.daerah.nama}&nbsp;
                        </p>
                        <p>
                            &emsp;&emsp;<b>Kertas Siasatan Bil:</b>
                            ${actionBean.thirdLayerPermohonan.idPermohonan}&nbsp;
                        </p>
                        <p>&nbsp;</p>
                        <c:if test="${actionBean.kodNegeri.kod eq '04'}">
                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq '426' || actionBean.permohonan.kodUrusan.kod eq '425' || actionBean.permohonan.kodUrusan.kod eq '425A'}">
                                <p>
                                    <b> 2.&emsp;MAKLUMAT PEGAWAI PENYIASAT</b>
                                </p>
                                <div class="content" align="center">
                                    <display:table name="${actionBean.operasiPenguatkuasaanIP}" id="line" class="tablecloth" cellpadding="0" cellspacing="0" style="width:70%;">
                                        <display:column title="Bil" sortable="true" style="width:5%;">${line_rowNum}</display:column>
                                        <display:column title="Maklumat Laporan Operasi" style="width:30%;">
                                            <table width="10%" cellpadding="1">
                                                <tr>
                                                    <td width="3%"><font class="infoLP">Id Operasi :</font></td>
                                                    <td width="3%"><a class="popup" onclick="viewRecordOP(${line.idOperasi})">${line.idOperasi}</a></td>
                                                </tr>
                                                <tr>
                                                    <td width="5%"><font class="infoLP">Tarikh laporan :</font></td>
                                                    <td width="5%">
                                                        <fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhOperasi}"/></td>
                                                </tr>
                                                <tr>
                                                    <td width="5%"><font class="infoLP">Masa laporan :</font></td>
                                                    <td width="5%">
                                                        <fmt:formatDate pattern="hh:mm" value="${line.tarikhOperasi}"/>
                                                        <fmt:formatDate pattern="aaa" value="${line.tarikhOperasi}" var="time"/>
                                                        <c:if test="${time eq 'AM'}">PAGI</c:if>
                                                        <c:if test="${time eq 'PM'}">PETANG</c:if>
                                                    </td>
                                                </tr>

                                            </table>
                                        </display:column>
                                        <display:column title="Pegawai Penyiasat" style="width:60%;">
                                            <c:set var="statusPerRow" value="none"/>
                                            <c:forEach items="${actionBean.listPegawaiSiasat}" var="p">
                                                <c:if test="${p.statusPeranan eq 'K' && p.operasiPenguatkuasaan.idOperasi eq line.idOperasi}">
                                                    <c:set var="statusPerRow" value="exist"/>
                                                    <table width="20%" cellpadding="1">
                                                        <tr>
                                                            <td width="20%"><font class="infoLP">Nama Ketua Penyiasat:</font></td>
                                                            <td width="20%"> 
                                                                ${p.nama}&nbsp;
                                                                <input type="hidden" name="namaKetua${line_rowNum-1}" id="namaKetua${line_rowNum-1}" value="${p.nama}">
                                                                <input type="hidden" name="idPenggunaKetua${line_rowNum-1}" id="idPenggunaKetua${line_rowNum-1}" value="${p.namaJabatan}">
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </c:if>
                                            </c:forEach>


                                            <c:if test="${statusPerRow eq 'exist'}"> 
                                                <table width="20%" cellpadding="1">
                                                    <tr>
                                                        <td width="20%"><font class="infoLP">Nama Pembantu Penyiasat:</font></td>
                                                        <td width="20%">&nbsp; </td>
                                                    </tr>
                                                    <c:set value="1" var="i"/>
                                                    <c:forEach items="${actionBean.listPegawaiSiasat}" var="p">
                                                        <c:if test="${p.statusPeranan eq 'P' && p.operasiPenguatkuasaan.idOperasi eq line.idOperasi}">
                                                            <tr>
                                                                <td width="20%">&nbsp; </td>
                                                                <td width="20%" colspan="2">${i}) ${p.nama}&nbsp; </td>
                                                            </tr>
                                                            <c:set var="i" value="${i+1}" />
                                                        </c:if>
                                                    </c:forEach>
                                                </table>
                                            </c:if> 

                                        </display:column>
                                    </display:table>
                                </div>
                            </c:if>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod ne '426' && actionBean.permohonan.kodUrusan.kod ne '425' && actionBean.permohonan.kodUrusan.kod ne '425A'}">
                                <p>
                                    <b> 2.&emsp;Pegawai Penyiasat :</b>
                                    <font style="text-transform: uppercase"> <c:if test="${actionBean.pegawaiSiasat.nama eq null}">-</c:if>${actionBean.pegawaiSiasat.nama}</font>&nbsp;
                                </p>
                                <p>
                                    &emsp;&emsp;<b>No. Pengenalan : </b> <font style="text-transform: uppercase">${actionBean.noPengenalanKetua}</font>&nbsp;
                                </p>
                            </c:if>


                        </c:if>

                        <p>&nbsp;</p>


                        <c:if test="${actionBean.kodNegeri.kod eq '04'}">
                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq '426' || actionBean.permohonan.kodUrusan.kod eq '425' || actionBean.permohonan.kodUrusan.kod eq '425A'}">
                                <p>
                                    <b> 3.&emsp;LAPORAN POLIS </b>
                                </p>
                                <div class="content" align="center">

                                    <display:table name="${actionBean.operasiPenguatkuasaanIP}" id="line" class="tablecloth" cellpadding="0" cellspacing="0" style="width:70%;">
                                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                                        <display:column title="Maklumat Laporan Operasi">
                                            <table width="20%" cellpadding="1">
                                                <tr>
                                                    <td width="20%"><font class="infoLP">Id Operasi :</font></td>
                                                    <td width="20%"><a class="popup" onclick="viewRecordOP(${line.idOperasi})">${line.idOperasi}</a></td>
                                                </tr>
                                                <tr>
                                                    <td width="20%"><font class="infoLP">Tarikh laporan :</font></td>
                                                    <td width="20%">
                                                        <fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhOperasi}"/></td>
                                                </tr>
                                                <tr>
                                                    <td width="20%"><font class="infoLP">Masa laporan :</font></td>
                                                    <td width="20%">
                                                        <fmt:formatDate pattern="hh:mm" value="${line.tarikhOperasi}"/>
                                                        <fmt:formatDate pattern="aaa" value="${line.tarikhOperasi}" var="time"/>
                                                        <c:if test="${time eq 'AM'}">PAGI</c:if>
                                                        <c:if test="${time eq 'PM'}">PETANG</c:if>
                                                    </td>
                                                </tr>

                                            </table>
                                        </display:column>
                                        <display:column title="Senarai Laporan Polis">
                                            <c:set value="1" var="count"/>
                                            <c:if test="${fn:length(line.senaraiAgensi) ne 0}">
                                                <table width="100%" cellpadding="1">
                                                    <tr>
                                                        <th  width="1%" align="center"><b>Bil</b></th>
                                                        <th  width="1%" align="center"><b>No Laporan Polis dan Balai</b></th>
                                                        <th  width="1%" align="center"><b>Tarikh</b></th>

                                                        <th  width="5%" align="center"><b>Papar</b></th>

                                                    </tr>
                                                    <c:forEach items="${line.senaraiAgensi}" var="agensi">
                                                        <tr>
                                                            <td width="5%">${count}</td>
                                                            <td width="20%"> ${agensi.noRujukan}&nbsp; </td>
                                                            <td width="20%"><fmt:formatDate pattern="dd/MM/yyyy" value="${agensi.tarikhRujukan}"/></td>
                                                            <td width="30%">
                                                                <c:set value="1" var="count"/>
                                                                <c:forEach items="${actionBean.listDokumen}" var="kf">

                                                                    <c:if test="${kf.dokumen.dalamanNilai1 eq agensi.idOperasiAgensi}">
                                                                        <p align="left">

                                                                            <c:if test="${kf.dokumen.kodDokumen.kod eq 'LP'}">
                                                                                <c:if test="${kf.dokumen.namaFizikal != null}">
                                                                                    <br>
                                                                                    -
                                                                                    <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                                                         onclick="doViewReport('${kf.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                                                                         onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${kf.dokumen.kodDokumen.nama}"/>
                                                                                    ${count}-${kf.dokumen.tajuk}/
                                                                                    <c:set value="${count+1}" var="count"/>
                                                                                </c:if>
                                                                            </c:if>
                                                                        </p>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </td>
                                                        </tr>
                                                        <c:set value="${count+1}" var="count"/>
                                                    </c:forEach>
                                                </table>
                                            </c:if>
                                        </display:column>
                                    </display:table>
                                </div>
                            </c:if>
                        </c:if>

                        <c:if test="${actionBean.kodNegeri.kod eq '04'}">
                            <c:if test="${actionBean.permohonan.kodUrusan.kod ne '426' && actionBean.permohonan.kodUrusan.kod ne '425' && actionBean.permohonan.kodUrusan.kod ne '425A'}">
                                <p>
                                    <b>3.&emsp;Balai Polis Dan Bil Aduan :</b>
                                    <c:if test="${actionBean.permohonanRujukanLuar1.noRujukan eq null}">-</c:if>${actionBean.permohonanRujukanLuar1.noRujukan}&nbsp;
                                </p>
                                <p>
                                    &emsp;&emsp;<b>Haribulan dan Jam Aduan :</b>
                                    <font style="text-transform: uppercase"><c:if test="${actionBean.permohonanRujukanLuar1.tarikhRujukan eq null}">-</c:if><fmt:formatDate value="${actionBean.permohonanRujukanLuar1.tarikhRujukan}" pattern="dd/MM/yyyy hh:mm aaa" /></font>&nbsp;
                                </p>
                                <p>
                                    &emsp;&emsp;<b>Tempat Kejadian :</b>
                                    <c:if test="${actionBean.aduanLokasi.lokasi eq null}">-</c:if>${actionBean.aduanLokasi.lokasi}&nbsp;
                                </p>
                                <p>
                                    &emsp;&emsp;<b>Jenis Kesalahan :</b>
                                    <font style="text-transform: uppercase"><c:if test="${actionBean.permohonan.kodUrusan.nama eq null}">-</c:if>${actionBean.permohonan.kodUrusan.nama}</font>&nbsp;
                                </p>
                            </c:if>
                        </c:if>


                        <p>&nbsp;</p>
                        <p>
                            <b>4.&emsp;KETERANGAN RINGKAS : </b>
                            <c:if test="${actionBean.permohonanKertasKandungan.kandungan eq null}">-</c:if> ${actionBean.permohonanKertasKandungan.kandungan}&nbsp;
                        </p>
                        <p>&nbsp;</p>
                        <c:if test="${actionBean.kodNegeri.kod eq '04'}">
                            <p>
                                <b>5.&emsp;KRONOLOGI KES      </b>
                                &nbsp;
                            <div class="content" align="center">
                                <display:table class="tablecloth" name="${actionBean.listAllKronologiKes}" cellpadding="0" cellspacing="0" id="line">
                                    <display:column title="Bil">${line_rowNum}</display:column>
                                    <display:column title="Catatan" property="kandungan"/>
                                    <display:column title="Tarikh & Masa"><fmt:formatDate pattern="dd/MM/yyyy hh:mm aaa" value="${line.infoAudit.tarikhMasuk}"/></display:column>
                                </display:table>
                            </div>
                            <p>&nbsp;</p>
                        </c:if>

                        <p>

                            <c:if test="${actionBean.kodNegeri.kod eq '04'}">
                                <b>6.&emsp;BARANG KES      </b>
                                &nbsp;
                            </c:if>

                        <div class="content" align="center">
                            <c:if test="${actionBean.kodNegeri.kod eq '04'}">
                                <c:if test="${actionBean.permohonan.kodUrusan.kod eq '426' || actionBean.permohonan.kodUrusan.kod eq '425' || actionBean.permohonan.kodUrusan.kod eq '425A'}">
                                    <display:table name="${actionBean.operasiPenguatkuasaanIP}" id="line" class="tablecloth" cellpadding="0" cellspacing="0"  style="width:60%;">
                                        <display:column title="Bil" sortable="true" style="width:3%;">${line_rowNum}</display:column>
                                        <display:column title="Maklumat Laporan Operasi" style="width:30%;">
                                            <table width="10%" cellpadding="1">
                                                <tr>
                                                    <td width="20%"><font class="infoLP">Id Operasi :</font></td>
                                                    <td width="20%"><a class="popup" onclick="viewRecordOP(${line.idOperasi})">${line.idOperasi}</a></td>
                                                </tr>
                                                <tr>
                                                    <td width="20%"><font class="infoLP">Tarikh laporan :</font></td>
                                                    <td width="20%">
                                                        <fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhOperasi}"/></td>
                                                </tr>
                                                <tr>
                                                    <td width="20%"><font class="infoLP">Masa laporan :</font></td>
                                                    <td width="20%">
                                                        <fmt:formatDate pattern="hh:mm" value="${line.tarikhOperasi}"/>
                                                        <fmt:formatDate pattern="aaa" value="${line.tarikhOperasi}" var="time"/>
                                                        <c:if test="${time eq 'AM'}">PAGI</c:if>
                                                        <c:if test="${time eq 'PM'}">PETANG</c:if>
                                                    </td>
                                                </tr>

                                            </table>
                                        </display:column>
                                        <display:column title="Maklumat Barang Rampasan" style="width:50%;">
                                            <c:set value="1" var="count"/>

                                            <table width="100%" cellpadding="1">
                                                <tr>
                                                    <th  width="1%" align="center"><b>Bil</b></th>
                                                    <th  width="1%" align="center"><b>Item</b></th>
                                                    <th  width="5%" align="center"><b>Papar</b></th>

                                                </tr>
                                                <c:forEach items="${line.senaraiBarangRampasan}" var="barang">
                                                    <c:if test="${barang.aduanOrangKenaSyak.permohonanAduanOrangKenaSyak.idPermohonan eq actionBean.permohonan.idPermohonan}">
                                                        <tr>
                                                            <td width="5%">${count}</td>
                                                            <td width="50%"><a class="popup" onclick="popup(${barang.idBarang})">${barang.item}</a></td>
                                                            <td width="30%">
                                                                <c:if test="${barang.imej.namaFizikal != null}">
                                                                    <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                                         onclick="doViewReport('${barang.imej.idDokumen}');" height="30" width="30" alt="papar"
                                                                         onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${barang.imej.kodDokumen.nama}"/>
                                                                </c:if>
                                                            </td>

                                                        </tr>
                                                        <c:set value="${count+1}" var="count"/>
                                                    </c:if>
                                                </c:forEach>
                                            </table>
                                            <%-- <b>Jumlah : ${fn:length(line.senaraiBarangRampasan)}</b>--%>
                                        </display:column>
                                    </display:table>
                                </c:if>
                                <c:if test="${actionBean.permohonan.kodUrusan.kod ne '426' && actionBean.permohonan.kodUrusan.kod ne '425' && actionBean.permohonan.kodUrusan.kod ne '425A'}">
                                    <display:table class="tablecloth" name="${actionBean.senaraiBarangRampasan}" cellpadding="0" cellspacing="0" id="line">
                                        <display:column title="Bil">${line_rowNum}</display:column>
                                        <display:column title="Barang yang Dirampas"><a class="popup" onclick="popup(${line.idBarang})">${line.item}</a></display:column>
                                        <display:column title="Kuantiti">
                                            <c:if test="${line.kodKategoriItemRampasan.kod eq 'K'}">
                                                1
                                            </c:if>
                                            <c:if test="${line.kodKategoriItemRampasan.kod eq 'B'}">
                                                ${line.kuantiti}
                                            </c:if>
                                        </display:column>
                                        <display:column title="Tempat Simpanan" property="tempatSimpanan"></display:column>
                                        <display:column title="Catatan" property="catatan"></display:column>
                                        <display:column title="Status" property="status.nama" />

                                    </display:table>
                                </c:if>
                            </c:if>

                        </div>

                        <p>&nbsp;</p>
                        <p>

                            <c:if test="${actionBean.kodNegeri.kod eq '04'}">
                                <b>7.&emsp;ORANG KENA SYAK       </b>&nbsp;
                            </c:if>

                        </p>

                        <div align="center">
                            <p>
                                &emsp;&emsp;<b>Tuduhan ( Sebut Seksyen ) :</b>
                                <font style="text-transform: uppercase"><c:if test="${actionBean.permohonan.kodUrusan.nama eq null}">-</c:if>${actionBean.permohonan.kodUrusan.nama}</font>&nbsp;

                            </p>
                            <p>&nbsp;</p>
                            <c:if test="${actionBean.kodNegeri.kod eq '04'}">
                                <c:if test="${actionBean.permohonan.kodUrusan.kod eq '426' || actionBean.permohonan.kodUrusan.kod eq '425' || actionBean.permohonan.kodUrusan.kod eq '425A'}">
                                    <display:table name="${actionBean.operasiPenguatkuasaanIP}" id="line" class="tablecloth" cellpadding="0" cellspacing="0" style="width:60%;">
                                        <display:column title="Bil" sortable="true" style="width:3%;">${line_rowNum}</display:column>
                                        <display:column title="Maklumat Laporan Operasi" style="width:30%;">
                                            <table width="10%" cellpadding="1">
                                                <tr>
                                                    <td width="10%"><font class="infoLP">Id Operasi :</font></td>
                                                    <td width="10%"><a class="popup" onclick="viewRecordOP(${line.idOperasi})">${line.idOperasi}</a></td>
                                                </tr>
                                                <tr>
                                                    <td width="10%"><font class="infoLP">Tarikh laporan :</font></td>
                                                    <td width="10%">
                                                        <fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhOperasi}"/></td>
                                                </tr>
                                                <tr>
                                                    <td width="10%"><font class="infoLP">Masa laporan :</font></td>
                                                    <td width="10%">
                                                        <fmt:formatDate pattern="hh:mm" value="${line.tarikhOperasi}"/>
                                                        <fmt:formatDate pattern="aaa" value="${line.tarikhOperasi}" var="time"/>
                                                        <c:if test="${time eq 'AM'}">PAGI</c:if>
                                                        <c:if test="${time eq 'PM'}">PETANG</c:if>
                                                    </td>
                                                </tr>

                                            </table>
                                        </display:column>
                                        <display:column title="Maklumat Orang Disyaki" style="width:50%;">
                                            <c:set value="1" var="count"/>
                                            <table width="100%" cellpadding="1">
                                                <tr>
                                                    <th  width="1%" align="center"><b>Bil</b></th>
                                                    <th  width="1%" align="center"><b>Nama</b></th>
                                                    <th  width="5%" align="center"><b>Papar</b></th>
                                                </tr>
                                                <c:forEach items="${line.senaraiAduanOrangKenaSyak}" var="oks">
                                                    <c:if test="${oks.permohonanAduanOrangKenaSyak.idPermohonan eq actionBean.permohonan.idPermohonan}">
                                                        <tr>
                                                            <td width="5%">${count}</td>
                                                            <td width="50%"><a class="popup" onclick="viewOKS(${oks.idOrangKenaSyak})">${oks.nama}</a></td>

                                                            <td width="30%">
                                                                <c:if test="${oks.dokumen.namaFizikal != null}">
                                                                    <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                                         onclick="doViewReport('${oks.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                                                         onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${oks.dokumen.kodDokumen.nama}"/>
                                                                </c:if>

                                                            </td>
                                                        </tr>
                                                        <c:set value="${count+1}" var="count"/>
                                                    </c:if>
                                                </c:forEach>
                                            </table>
                                        </display:column>

                                    </display:table>
                                </c:if>
                                <c:if test="${actionBean.permohonan.kodUrusan.kod ne '426' && actionBean.permohonan.kodUrusan.kod ne '425'}">
                                    <display:table class="tablecloth" name="${actionBean.senaraiAduanOrangKenaSyak}" cellpadding="0" cellspacing="0" id="line" >
                                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                                        <display:column title="No.Pengenalan"><a class="popup" onclick="viewOKS(${line.idOrangKenaSyak})">${line.noPengenalan}</a></display:column>
                                        <display:column title="Nama" property="nama"></display:column>
                                        <display:column title="Alamat">${line.alamat.alamat1}
                                            <c:if test="${line.alamat.alamat2 ne null}"> , </c:if>
                                            ${line.alamat.alamat2}
                                            <c:if test="${line.alamat.alamat3 ne null}"> , </c:if>
                                            ${line.alamat.alamat3}
                                            <c:if test="${line.alamat.alamat4 ne null}"> , </c:if>
                                            ${line.alamat.alamat4}
                                            ${line.alamat.poskod}
                                            ${line.alamat.negeri.nama}
                                        </display:column>
                                        <display:column title="Keterangan" property="keterangan"></display:column>
                                    </display:table>
                                </c:if>
                            </c:if>

                        </div>
                        <p>&nbsp;</p>

                        <p>&nbsp;</p>
                        <p>

                            <c:if test="${actionBean.kodNegeri.kod eq '04'}">
                                <c:if test="${actionBean.permohonan.kodUrusan.kod ne '426' && actionBean.permohonan.kodUrusan.kod ne '425' && actionBean.permohonan.kodUrusan.kod ne '425A'}">
                                    <b>8.&emsp;SAKSI       </b>&nbsp;
                                </c:if>
                            </c:if>
                        </p>
                        <div class="content" align="center">
                            <c:if test="${actionBean.kodNegeri.kod eq '04'}">
                                &nbsp;
                                <c:if test="${actionBean.permohonan.kodUrusan.kod ne '426' && actionBean.permohonan.kodUrusan.kod ne '425' && actionBean.permohonan.kodUrusan.kod ne '425A'}">
                                    <p>
                                        <b>Senarai saksi dalaman</b>&nbsp;
                                    </p> 
                                </c:if>

                                <c:if test="${actionBean.permohonan.kodUrusan.kod ne '425' && actionBean.permohonan.kodUrusan.kod ne '425A' && actionBean.permohonan.kodUrusan.kod ne '426'}">
                                    &nbsp;
                                    <div id="senaraiPasukanSaksiDiv">
                                        <display:table class="tablecloth" name="${actionBean.senaraiPasukanSaksi}" cellpadding="0" cellspacing="0" id="line" >
                                            <display:column title="Bil">${line_rowNum}</display:column>
                                            <display:column property="noKp" title="No.Pengenalan"></display:column>
                                            <display:column property="nama" title="Nama Saksi" style="text-transform: uppercase"></display:column>
                                            <display:column property="kodPerananOperasi.nama" title="Peranan" style="text-transform: uppercase"></display:column>
                                            <display:column property="kadKuasa" title="Kad Kuasa" style="text-transform: uppercase"></display:column>
                                            <display:column property="tempatKerja" title="Jawatan" style="text-transform: uppercase"></display:column>
                                        </display:table>
                                    </div>

                                    <br>
                                    &nbsp;
                                    <p>
                                        <b>Senarai saksi luar </b>&nbsp;
                                    </p>
                                    <display:table class="tablecloth" name="${actionBean.senaraiPermohonanSaksi}" cellpadding="0" cellspacing="0" id="line" >
                                        <display:column title="Bil">${line_rowNum}</display:column>
                                        <display:column title="No.Pengenalan"><a class="popup" onclick="viewSaksi(${line.idSaksi})">${line.noPengenalan}</a></display:column>
                                        <display:column property="nama" title="Nama Saksi" style="text-transform: uppercase"></display:column>
                                        <display:column title="Alamat" style="text-transform: uppercase">${line.alamat1}
                                            <c:if test="${line.alamat2 ne null}"> , </c:if>
                                            ${line.alamat2}
                                            <c:if test="${line.alamat3 ne null}"> , </c:if>
                                            ${line.alamat3}
                                            <c:if test="${line.alamat4 ne null}"> , </c:if>
                                            ${line.alamat4}
                                            ${line.poskod}
                                            ${line.negeri.nama}
                                        </display:column>

                                    </display:table>

                                </c:if>

                            </c:if>




                            <br>
                        </div>

                        <p>&nbsp;</p>

                        <c:if test="${actionBean.kodNegeri.kod eq '04'}">
                            <p>
                                <c:set value="0" var="bil"/>
                                <c:choose>
                                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq '425' || actionBean.permohonan.kodUrusan.kod eq '425A' || actionBean.permohonan.kodUrusan.kod eq '426'}">
                                        <c:set value="8" var="bil"/>
                                    </c:when>
                                    <c:otherwise>
                                        <c:set value="9" var="bil"/>
                                    </c:otherwise>
                                </c:choose>
                                <b> ${bil}.&emsp;RAKAMAN PERCAKAPAN  :       </b>

                                <c:set value="1" var="count"/>
                                <c:forEach items="${actionBean.permohonan.folderDokumen.senaraiKandungan}" var="senarai">
                                    <c:if test="${senarai.dokumen.kodDokumen.kod eq 'RP'}">
                                        <c:if test="${senarai.dokumen.namaFizikal != null}">
                                            <br>
                                            ${count}  -
                                            <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                 onclick="doViewReport('${senarai.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                                 onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${senarai.dokumen.kodDokumen.nama}"/>
                                            ${senarai.dokumen.tajuk}
                                            <c:set value="${count+1}" var="count"/>
                                        </c:if>
                                    </c:if>
                                </c:forEach>

                                <c:forEach items="${actionBean.senaraiDokumen}" var="k">
                                    <c:if test="${k.dokumen.kodDokumen.kod eq 'RP'}">
                                        <c:if test="${k.dokumen.namaFizikal != null}">
                                            <br>
                                            ${count}  -
                                            <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                 onclick="doViewReport('${k.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                                 onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${k.dokumen.kodDokumen.nama}"/>
                                            ${k.dokumen.tajuk}
                                            <c:set value="${count+1}" var="count"/>
                                        </c:if>

                                    </c:if>
                                </c:forEach>
                            </p>
                        </c:if>


                        <p>&nbsp;</p>
                        <p>

                            <c:if test="${actionBean.kodNegeri.kod eq '04'}">
                                <c:set value="0" var="bil"/>
                                <c:choose>
                                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq '425' || actionBean.permohonan.kodUrusan.kod eq '425A' || actionBean.permohonan.kodUrusan.kod eq '426'}">
                                        <c:set value="9" var="bil"/>
                                    </c:when>
                                    <c:otherwise>
                                        <c:set value="10" var="bil"/>
                                    </c:otherwise>
                                </c:choose>
                                <b> ${bil}.&emsp;MAKLUMAT PENGADU       </b>&nbsp;
                            </c:if>
                        </p>
                        <div class="subtitle">
                            <fieldset class="aras1">

                                <p>
                                    <label>Nama :</label>
                                    ${actionBean.permohonan.penyerahNama}&nbsp;
                                </p>
                                <p>
                                    <label>Jenis Pengenalan :</label>
                                    <font style="text-transform: uppercase">${actionBean.permohonan.penyerahJenisPengenalan.nama}&nbsp;</font>

                                </p>
                                <p>
                                    <label>No.Pengenalan :</label>
                                    ${actionBean.permohonan.penyerahNoPengenalan}&nbsp;
                                </p>

                                <p>
                                    <label>Alamat :</label>
                                    ${actionBean.permohonan.penyerahAlamat1}&nbsp;
                                </p>
                                <p>
                                    <label> &nbsp;</label>
                                    ${actionBean.permohonan.penyerahAlamat2}&nbsp;
                                </p>
                                <p>
                                    <label> &nbsp;</label>
                                    ${actionBean.permohonan.penyerahAlamat3}&nbsp;
                                </p>
                                <p>
                                    <label> &nbsp;</label>
                                    ${actionBean.permohonan.penyerahAlamat4}&nbsp;
                                </p>
                                <p>
                                    <label>Poskod :</label>
                                    ${actionBean.permohonan.penyerahPoskod}&nbsp;
                                </p>
                                <p>
                                    <label>Negeri :</label>
                                    ${actionBean.permohonan.penyerahNegeri.nama}&nbsp;
                                </p>
                                <p>
                                    <label>No.Telefon :</label>
                                    ${actionBean.permohonan.penyerahNoTelefon1}&nbsp;
                                </p>
                                <p>
                                    <label>Email :</label>
                                    ${actionBean.permohonan.penyerahEmail}&nbsp;
                                </p>
                            </fieldset>
                        </div>
                        <p>&nbsp;</p>

                        <p>

                            <c:if test="${actionBean.kodNegeri.kod eq '04'}">
                                <c:set value="0" var="bil"/>
                                <c:choose>
                                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq '425' || actionBean.permohonan.kodUrusan.kod eq '425A' || actionBean.permohonan.kodUrusan.kod eq '426'}">
                                        <c:set value="10" var="bil"/>
                                    </c:when>
                                    <c:otherwise>
                                        <c:set value="11" var="bil"/>
                                    </c:otherwise>
                                </c:choose>
                                <b> ${bil}.&emsp;LAMPIRAN :-      </b>&nbsp;
                            </c:if>


                            <c:forEach items="${actionBean.permohonan.folderDokumen.senaraiKandungan}" var="senarai">
                                <c:if test="${senarai.dokumen.kodDokumen.kod eq 'KS'}">
                                    <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                         onclick="doViewReport('${senarai.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                         onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${senarai.dokumen.kodDokumen.nama}"/>
                                    (${actionBean.permohonanKertas.dokumen.tajuk})
                                </c:if>
                            </c:forEach>

                            <c:forEach items="${actionBean.senaraiDokumen}" var="k">
                                <c:if test="${k.dokumen.kodDokumen.kod eq 'KS'}">
                                    <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                         onclick="doViewReport('${k.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                         onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${k.dokumen.kodDokumen.nama}"/>
                                    (${actionBean.permohonanKertas.dokumen.tajuk})
                                </c:if>
                            </c:forEach>
                        </p>

                        <p>&nbsp;</p>

                        <p>
                            <b>12.&emsp;SENARAI DOKUMEN :    </b>&nbsp;
                        </p>
                        <display:table name="${actionBean.permohonan.folderDokumen.senaraiKandungan}" class="tablecloth" id="row" style="width:100%">
                            <display:column title="Pilih"> 
                                <c:if test="${actionBean.pengguna.idPengguna eq row.dokumen.infoAudit.dimasukOleh.idPengguna}">
                                    <s:checkbox name="chkbox" value="${row.dokumen.idDokumen}" id="chkbox${row_rowNum-1}"/>
                                </c:if>
                                <s:hidden name="val" id="val_${row_rowNum-1}" value="${row.dokumen.idDokumen}"/>
                            </display:column>
                            <display:column title="No">
                                ${row_rowNum}
                            </display:column>
                            <display:column title="Kod Dokumen">
                                <div id="t" title="${row.dokumen.kodDokumen.nama}">${row.dokumen.kodDokumen.kod}</div>
                            </display:column>
                            <display:column title="Tajuk /<br/> No Rujukan">
                                <c:if test="${row.dokumen.kodDokumen.kod eq '0L'}">
                                    <div id="${row_rowNum-1}" class="editable">${row.catatan}</div>
                                    <s:hidden name="x" id="old_${row_rowNum-1}" value="${row.catatan}"/>
                                </c:if>
                                <c:if test="${row.dokumen.kodDokumen.kod ne '0L'}">
                                    <div id="${row_rowNum-1}" class="editable">${row.dokumen.tajuk}</div>
                                    <s:hidden name="x" id="old_${row_rowNum-1}" value="${row.dokumen.tajuk}"/>
                                </c:if>
                                <br/>  <c:if test="${row.dokumen.noRujukan ne '' && row.dokumen.noRujukan ne null}">(No rujukan : ${row.dokumen.noRujukan})</c:if>
                            </display:column>
                            <display:column title="Klas." property="dokumen.klasifikasi.nama" />
                            <display:column title="Dimasuk Oleh" property="dokumen.infoAudit.dimasukOleh.nama" />
                            <%--<display:column title="Catatan" property="catatan" />--%>
                            <display:column title="Tarikh" property="dokumen.infoAudit.tarikhMasuk" format="{0,date,dd/MM/yyyy hh:mm:ss}" />
                            <display:column title="Tindakan">
                                <div align="center">
                                    <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png"
                                         onclick="scan('${row.dokumen.idDokumen}','${actionBean.permohonan.idPermohonan}');return false;" height="30" width="30" alt="Imbas"
                                         onmouseover="this.style.cursor='pointer';" title="Imbas untuk Dokumen ${row.dokumen.kodDokumen.nama}"/> /
                                    <c:if test="${row.dokumen.namaFizikal != null}">
                                        <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                             onclick="doViewReport('${row.dokumen.idDokumen}');return false;" height="30" width="30" alt="papar"
                                             onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${row.dokumen.kodDokumen.nama}"/>
                                        <c:if test="${row.dokumen.baru eq 'Y' || row.dokumen.baru eq ''}">
                                            <img src="${pageContext.request.contextPath}/pub/images/baharu.gif" alt="baru"/>
                                        </c:if>
                                    </c:if>
                                </div>
                            </display:column>
                        </display:table>

                        <c:if test="${actionBean.secondLayerPermohonan.idPermohonan ne null}">
                            <p class="title open" id="f1">
                                <img src="${pageContext.request.contextPath}/pub/images/folderopen.gif" id="f11" class="open"/>
                                Fail ${actionBean.secondLayerPermohonan.folderDokumen.tajuk}</p>

                            <display:table name="${actionBean.secondLayerPermohonan.folderDokumen.senaraiKandungan}" class="tablecloth" id="row" style="width:100%">
                                <display:column title="Pilih"> 
                                    <c:if test="${actionBean.pengguna.idPengguna eq row.dokumen.infoAudit.dimasukOleh.idPengguna}">
                                        <s:checkbox name="chkbox" value="${row.dokumen.idDokumen}" id="chkbox${row_rowNum-1}"/>
                                    </c:if>
                                    <s:hidden name="val" id="val_${row_rowNum-1}" value="${row.dokumen.idDokumen}"/>
                                </display:column>
                                <display:column title="No">
                                    ${row_rowNum}
                                </display:column>
                                <display:column title="Kod Dokumen">
                                    <div id="t" title="${row.dokumen.kodDokumen.nama}">${row.dokumen.kodDokumen.kod}</div>
                                </display:column>
                                <display:column title="Tajuk /<br/> No Rujukan">
                                    <c:if test="${row.dokumen.kodDokumen.kod eq '0L'}">
                                        <div id="${row_rowNum-1}" class="editable">${row.catatan}</div>
                                        <s:hidden name="x" id="old_${row_rowNum-1}" value="${row.catatan}"/>
                                    </c:if>
                                    <c:if test="${row.dokumen.kodDokumen.kod ne '0L'}">
                                        <div id="${row_rowNum-1}" class="editable">${row.dokumen.tajuk}</div>
                                        <s:hidden name="x" id="old_${row_rowNum-1}" value="${row.dokumen.tajuk}"/>
                                    </c:if>
                                    <br/>  <c:if test="${row.dokumen.noRujukan ne '' && row.dokumen.noRujukan ne null}">(No rujukan : ${row.dokumen.noRujukan})</c:if>
                                </display:column>
                                <display:column title="Klas." property="dokumen.klasifikasi.nama" />
                                <display:column title="Dimasuk Oleh" property="dokumen.infoAudit.dimasukOleh.nama" />
                                <%--<display:column title="Catatan" property="catatan" />--%>
                                <display:column title="Tarikh" property="dokumen.infoAudit.tarikhMasuk" format="{0,date,dd/MM/yyyy hh:mm:ss}" />
                                <display:column title="Tindakan">
                                    <div align="center">
                                        <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png"
                                             onclick="scan('${row.dokumen.idDokumen}','${actionBean.secondLayerPermohonan.idPermohonan}');return false;" height="30" width="30" alt="Imbas"
                                             onmouseover="this.style.cursor='pointer';" title="Imbas untuk Dokumen ${row.dokumen.kodDokumen.nama}"/> /
                                        <c:if test="${row.dokumen.namaFizikal != null}">
                                            <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                 onclick="doViewReport('${row.dokumen.idDokumen}');return false;" height="30" width="30" alt="papar"
                                                 onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${row.dokumen.kodDokumen.nama}"/>
                                            <c:if test="${row.dokumen.baru eq 'Y' || row.dokumen.baru eq ''}">
                                                <img src="${pageContext.request.contextPath}/pub/images/baharu.gif" alt="baru"/>
                                            </c:if>
                                        </c:if>
                                    </div>
                                </display:column>
                            </display:table>
                        </c:if>


                        <c:if test="${actionBean.thirdLayerPermohonan.idPermohonan ne null}">
                            <p class="title open" id="f1">
                                <img src="${pageContext.request.contextPath}/pub/images/folderopen.gif" id="f11" class="open"/>
                                Fail ${actionBean.thirdLayerPermohonan.folderDokumen.tajuk}</p>

                            <display:table name="${actionBean.thirdLayerPermohonan.folderDokumen.senaraiKandungan}" class="tablecloth" id="row" style="width:100%">
                                <display:column title="Pilih"> 
                                    <c:if test="${actionBean.pengguna.idPengguna eq row.dokumen.infoAudit.dimasukOleh.idPengguna}">
                                        <s:checkbox name="chkbox" value="${row.dokumen.idDokumen}" id="chkbox${row_rowNum-1}"/>
                                    </c:if>
                                    <s:hidden name="val" id="val_${row_rowNum-1}" value="${row.dokumen.idDokumen}"/>
                                </display:column>
                                <display:column title="No">
                                    ${row_rowNum}
                                </display:column>
                                <display:column title="Kod Dokumen">
                                    <div id="t" title="${row.dokumen.kodDokumen.nama}">${row.dokumen.kodDokumen.kod}</div>
                                </display:column>
                                <display:column title="Tajuk /<br/> No Rujukan">
                                    <c:if test="${row.dokumen.kodDokumen.kod eq '0L'}">
                                        <div id="${row_rowNum-1}" class="editable">${row.catatan}</div>
                                        <s:hidden name="x" id="old_${row_rowNum-1}" value="${row.catatan}"/>
                                    </c:if>
                                    <c:if test="${row.dokumen.kodDokumen.kod ne '0L'}">
                                        <div id="${row_rowNum-1}" class="editable">${row.dokumen.tajuk}</div>
                                        <s:hidden name="x" id="old_${row_rowNum-1}" value="${row.dokumen.tajuk}"/>
                                    </c:if>
                                    <br/>  <c:if test="${row.dokumen.noRujukan ne '' && row.dokumen.noRujukan ne null}">(No rujukan : ${row.dokumen.noRujukan})</c:if>
                                </display:column>
                                <display:column title="Klas." property="dokumen.klasifikasi.nama" />
                                <display:column title="Dimasuk Oleh" property="dokumen.infoAudit.dimasukOleh.nama" />
                                <%--<display:column title="Catatan" property="catatan" />--%>
                                <display:column title="Tarikh" property="dokumen.infoAudit.tarikhMasuk" format="{0,date,dd/MM/yyyy hh:mm:ss}" />
                                <display:column title="Tindakan">
                                    <div align="center">
                                        <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png"
                                             onclick="scan('${row.dokumen.idDokumen}','${actionBean.thirdLayerPermohonan.idPermohonan}');return false;" height="30" width="30" alt="Imbas"
                                             onmouseover="this.style.cursor='pointer';" title="Imbas untuk Dokumen ${row.dokumen.kodDokumen.nama}"/> /
                                        <c:if test="${row.dokumen.namaFizikal != null}">
                                            <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                 onclick="doViewReport('${row.dokumen.idDokumen}');return false;" height="30" width="30" alt="papar"
                                                 onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${row.dokumen.kodDokumen.nama}"/>
                                            <c:if test="${row.dokumen.baru eq 'Y' || row.dokumen.baru eq ''}">
                                                <img src="${pageContext.request.contextPath}/pub/images/baharu.gif" alt="baru"/>
                                            </c:if>
                                        </c:if>
                                    </div>
                                </display:column>
                            </display:table>
                        </c:if>
                    </div>
                </fieldset>
            </div>
        </s:form>

