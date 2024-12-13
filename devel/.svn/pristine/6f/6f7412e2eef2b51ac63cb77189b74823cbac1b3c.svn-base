<%--
    Document   : Kertas_Pertimbangan_PTD_Melaka
    Created on : Jun 29, 2010, 12:04:45 PM
    Author     : Rohan
--%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>


<style type="text/css">
    #tdLabel {
        color:#003194;
        display:block;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        margin-left:30px;
        margin-right:0.5em;
        text-align:right;
        width:32em;
    }

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
        width:20em;
    }
</style>

<script type="text/javascript">
    function addRow(tableid){
        var table = document.getElementById(tableid);
        var rowcount = table.rows.length;
        var row = table.insertRow(rowcount);

        var leftcell = row.insertCell(0);
        var bil = document.createTextNode(rowcount);
        leftcell.appendChild(bil);

        var rightcell = row.insertCell(1);
        var el = document.createElement('textarea');
        el.name = 'ulasan' + rowcount;
        el.rows = 5;
        el.cols = 165;
        el.style.textTransform = 'uppercase';
        rightcell.appendChild(el);
    }

</script>

<s:form beanclass="etanah.view.stripes.pembangunan.melaka.KertasPertimbanganPTDMelakaActionBean">
    <s:messages/>
    <s:errors/>
    <s:hidden name="kandunganK.kertas.idKertas"/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend></legend>
            <div class="content" align="center">

                <table border="0" width="80%" >
                    <tr><td align="center"><b><u>S U L I T</u></b></td></tr>
                    <tr><td align="center"><b>(KERTAS UNTUK PERTIMBANGAN PENTADBIR TANAH MELAKA TENGAH)</b></td></tr>

                    <tr><td><b><font style="text-transform: uppercase">Permohonan Serah Dan Berimilik Semula Tanah Pajakan Untuk Mendapatkan Tempoh Kekal Tanah Adat Melaka(MCL) Daripadi  ${actionBean.pemohon.pihak.nama} Atas Tanah
                                    Hakmilik PM Lot${actionBean.hakmilik.lot.nama} ${actionBean.hakmilik.noLot},Seluas ${actionBean.hakmilik.kodUnitLuas.nama}
                                    ${actionBean.hakmilik.bandarPekanMukim.nama} Daerah ${actionBean.hakmilik.daerah.nama},Melaka.</font></b></td></tr>

                    <br><tr><td><b>1. TUJUAN</b></td></tr>
                    <c:if test="${edit}">
                        <tr><td><font style="text-transform: none">1.1<br><s:textarea rows="5" cols="165" name="tujuan"/></font></td></tr>
                                </c:if>
                                <c:if test="${!edit}">
                        <tr><td><font style="text-transform:none">1.1 &nbsp; ${actionBean.tujuan}</font></td></tr>
                    </c:if>

                    <br><tr><td><b>2.LATAR BELAKANG</b></td></tr>
                    <tr><td>
                            <c:if test="${edit}">
                                <table id="tbl" class="tablecloth">
                                    <tr>

                                    </tr>
                                    <c:choose>
                                        <c:when test="${actionBean.ulasan1 eq null}">
                                            <tr>
                                                <td>1</td>
                                                <td><s:textarea name="ulasan1" cols="165" rows="5"/>
                                                </td>
                                            </tr>
                                        </c:when>
                                        <c:otherwise>
                                            <tr>
                                                <td>1</td>
                                                <td><s:textarea name="ulasan1" rows="5" cols="165">${actionBean.ulasan1}</s:textarea>
                                                </td>
                                            </tr>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:if test="${actionBean.ulasan2 ne null}">
                                        <tr>
                                            <td>2</td>
                                            <td><s:textarea name="ulasan2" rows="5" cols="165">${actionBean.ulasan2}</s:textarea>
                                            </td>
                                        </tr>
                                    </c:if>
                                    <c:if test="${actionBean.ulasan3 ne null}">
                                        <tr>
                                            <td>3</td>

                                            <td><s:textarea name="ulasan3" rows="5" cols="165">${actionBean.ulasan3}</s:textarea>
                                            </td>
                                        </tr>
                                    </c:if>
                                    <c:if test="${actionBean.ulasan4 ne null}">
                                        <tr>
                                            <td>4</td>

                                            <td><s:textarea name="ulasan4" rows="5" cols="165">${actionBean.ulasan4}</s:textarea>
                                            </td>
                                        </tr>
                                    </c:if>
                                    <c:if test="${actionBean.ulasan5 ne null}">
                                        <tr>
                                            <td>5</td>

                                            <td><s:textarea name="ulasan5" rows="5" cols="165">${actionBean.ulasan5}</s:textarea>
                                            </td>
                                        </tr>
                                    </c:if>
                                    <c:if test="${actionBean.ulasan6 ne null}">
                                        <tr>
                                            <td>6</td>

                                            <td><s:textarea name="ulasan6" rows="5" cols="165">${actionBean.ulasan6}</s:textarea>
                                            </td>
                                        </tr>
                                    </c:if>
                                    <c:if test="${actionBean.ulasan7 ne null}">
                                        <tr>
                                            <td>7</td>

                                            <td><s:textarea name="ulasan7" rows="5" cols="165">${actionBean.ulasan7}</s:textarea>
                                            </td>
                                        </tr>
                                    </c:if>
                                    <c:if test="${actionBean.ulasan8 ne null}">
                                        <tr>
                                            <td>8</td>
                                            <td><s:textarea name="ulasan8" rows="5" cols="165">${actionBean.ulasan8}</s:textarea>
                                            </td>
                                        </tr>
                                    </c:if>
                                    <c:if test="${actionBean.ulasan9 ne null}">
                                        <tr>
                                            <td>9</td>
                                            <td><s:textarea name="ulasan9" rows="5" cols="165">${actionBean.ulasan9}</s:textarea>
                                            </td>
                                        </tr>
                                    </c:if>
                                    <c:if test="${actionBean.ulasan10 ne null}">
                                        <tr>
                                            <td>10</td>
                                            <td><s:textarea name="ulasan10" rows="5" cols="165">${actionBean.ulasan10}</s:textarea>
                                            </td>
                                        </tr>
                                    </c:if>
                                    <c:if test="${actionBean.ulasan11 ne null}">
                                        <tr>
                                            <td>11</td>
                                            <td><s:textarea name="ulasan11" rows="5" cols="165">${actionBean.ulasan11}</s:textarea>
                                            </td>
                                        </tr>
                                    </c:if>
                                </table>
                            </c:if>
                            <c:if test="${!edit}">
                                <display:table name="${actionBean.listUlasan2}" >

                                </display:table>
                            </c:if>
                            <br> <c:if test="${actionBean.btn}">
                                <s:button class="btn" value="Tambah" name="add" onclick="addRow('tbl')"/>&nbsp;
                            </c:if>
                        </td></tr>

                    <br><tr><td><b><u>3. BUTIR-BUTIR TANAH </u> </b></td></tr>
                    <tr><td>
                            <display:table  class="tablecloth" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0" id="tbl1">
                                <display:column title="No Lot" style="vertical-align:baseline">${tbl1.hakmilik.lot.nama} ${tbl1.hakmilik.noLot} </display:column>
                                <display:column property="hakmilik.bandarPekanMukim.nama" title="Pekan/Bandar" style="vertical-align:baseline"/>
                                <display:column title="Jenis Hakmilik" style="vertical-align:baseline">${actionBean.hakmilik.kodHakmilik.nama}</display:column>
                                <display:column title="Hasil Tahunan" style="vertical-align:baseline"></display:column>
                                <display:column title="Tempoh Pajakan" style="vertical-align:baseline">${actionBean.hakmilik.tempohPegangan}</display:column>
                                <display:column title="Tarikh Tamat Pajakan" style="vertical-align:baseline"></display:column>
                                <display:column title="Luas lot" style="vertical-align:baseline">${actionBean.hakmilik.luas} ${tbl1.hakmilik.kodUnitLuas.nama}</display:column>
                                <display:column title="Bebanan" style="vertical-align:baseline"></display:column>
                                <display:column title="Penjenisan" style="vertical-align:baseline"></display:column>
                                <display:column title="Syarat Nyata" property="hakmilik.syaratNyata.syarat" style="vertical-align:baseline"/>
                                <display:column title="Sekatan Kepentingan" property="hakmilik.sekatanKepentingan.sekatan" style="vertical-align:baseline"/>

                            </display:table>
                        </td></tr>

                    <tr><td align="left">3.1 Tanah-tanah yang bersempadan dengan tanah ini ialah:</td></tr>
                    <br><tr><td>
                            <table border="0" width="95%">
                                <tr><td>Utara:&nbsp;
                                        ${actionBean.laporanTanah.sempadanUtaraKegunaan}</td>
                                </tr>
                                <tr><td>Selatan:&nbsp;
                                        ${actionBean.laporanTanah.sempadanSelatanKegunaan}</td>
                                </tr>
                                <tr><td>Timur:&nbsp;
                                        ${actionBean.laporanTanah.sempadanTimurKegunaan}</td>
                                </tr>
                                <tr><td>Barat:&nbsp;
                                        ${actionBean.laporanTanah.sempadanBaratKegunaan}</td>
                                </tr>
                            </table>
                        </td></tr>


                    <br><tr><td><b>4.PERAKUAN PENOLONG PEGAWAI DAERAH(T1)</b></td></tr>
                    <c:if test="${edit}">
                        <tr><td><font style="text-transform: none">4.1<br><s:textarea rows="5" cols="165" name="perakuanPenolong1"/></font></td></tr><br>
                                </c:if>
                                <c:if test="${!edit}">
                        <tr><td><font style="text-transform:none">4.1 &nbsp;${actionBean.perakuanPenolong1}</font></td></tr>
                    </c:if>

                    <tr><td>
                            <display:table  class="tablecloth" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0" id="tbl1">
                                <display:column title="Jenis Hakmilik" style="vertical-align:baseline">${tbl1.hakmilik.lot.nama} ${tbl1.hakmilik.kodHakmilik.kod} ${tbl1.hakmilik.noHakmilik}</display:column>
                                <display:column title="Premium" style="vertical-align:baseline"/>
                                <display:column title="Cukai" style="vertical-align:baseline">${actionBean.hakmilik.cukai}</display:column>
                                <display:column title=" Bayeran Hakmilik " style="vertical-align:baseline">${actionBean.hakmilik.kodHakmilik.nama}</display:column>
                                <display:column title="Syarat Nyata" style="vertical-align:baseline">${actionBean.hakmilik.syaratNyata.syarat}</display:column>
                                <display:column title="Sekatan Kepentingan" style="vertical-align:baseline">${actionBean.hakmilik.sekatanKepentingan.sekatan}</display:column>
                                <display:column title="Syarat Am" style="vertical-align:baseline"></display:column>
                                <display:column title="Penjenisan" style="vertical-align:baseline"></display:column>
                            </display:table>
                        </td></tr>

                    <br> <c:if test="${edit}">
                        <tr><td><font style="text-transform: none">4.2<br><s:textarea rows="5" cols="165" name="perakuanPenolong2"/></font></td></tr>
                                </c:if>
                                <c:if test="${!edit}">
                        <tr><td><font style="text-transform:none">4.2${actionBean.perakuanPenolong2} &nbsp;</font></td></tr>
                    </c:if>

                    <br><tr><td align="left"><b>5.KEPUTUSAN PENTADBIR TANAH MELAKA TENGAH</b></td></tr>

                    <c:if test="${edit}">
                        <tr><td>Saya Pentadbir Tanah Melaka Tengah melalui Perwakilan Kuasa MMKN 21A/11/96 yang bersidang pada 10 April 1996   ke atas permohonan
                                untuk memberimilik semula Hakmilik PM  LOT ${actionBean.hakmilik.lot.nama} ${actionBean.hakmilik.noLot},
                                Seluas ${actionBean.hakmilik.kodUnitLuas.nama} Mukim ${actionBean.hakmilik.daerah.nama},Daerah Melaka Tengah Melaka di luluskan kepada sebagai Tanah Adat Melaka(MCL)
                                Dengan syarat-syarat seperti berikut:-</td></tr>
                            </c:if>
                            <c:if test="${!edit}">
                        <tr><td>Saya Pentadbir Tanah Melaka Tengah melalui Perwakilan Kuasa MMKN 21A/11/96 yang bersidang pada 10 April 1996   ke atas permohonan
                                untuk memberimilik semula Hakmilik PM  ${actionBean.hakmilik.lot.nama} ${actionBean.hakmilik.noLot},
                                Seluas ${actionBean.hakmilik.kodUnitLuas.nama} Mukim ${actionBean.hakmilik.daerah.nama},Daerah Melaka Tengah Melaka di luluskan kepada sebagai Tanah Adat Melaka(MCL)
                                Dengan syarat-syarat seperti berikut:-</td></tr>
                            </c:if >

                    <br><tr><td><b><u>I.Sayarat:</u></b></td></tr><br>
                    <tr><td>
                            <display:table  class="tablecloth" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0" id="tbl1">
                                <display:column title="Jenis Hakmilik" style="vertical-align:baseline">${tbl1.hakmilik.lot.nama} ${tbl1.hakmilik.kodHakmilik.kod} ${tbl1.hakmilik.noHakmilik}</display:column>
                                <display:column title="Tempoh" style="vertical-align:baseline"/>
                                <display:column title="Premium" style="vertical-align:baseline"/>
                                <display:column title="Cukai" style="vertical-align:baseline">${actionBean.hakmilik.cukai}</display:column>
                                <display:column title=" Bayaran Hakmilik " style="vertical-align:baseline">${actionBean.hakmilik.kodHakmilik.nama}</display:column>
                                <display:column title="Syarat Nyata" style="vertical-align:baseline">${actionBean.hakmilik.syaratNyata.syarat}</display:column>
                                <display:column title="Sekatan Kepentingan" style="vertical-align:baseline">${actionBean.hakmilik.sekatanKepentingan.sekatan}</display:column>
                            </display:table>
                        </td></tr>


                    <br><tr><td><b><u>II.Penjenisan:</u></b></td></tr>
                    <c:if test="${edit}">
                        <tr><td><font style="text-transform: none"><s:textarea rows="5" cols="165" name="penjenisan"/></font></td></tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td><font style="text-transform:none">&nbsp; ${actionBean.penjenisan}</font></td></tr>
                    </c:if>

                    <br><tr><td><b><u>III.Syarat Am:</u></b></td></tr>
                    <c:if test="${edit}">
                        <tr><td><font style="text-transform: none"><s:textarea rows="5" cols="165" name="syaratAm"/></font></td></tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td><font style="text-transform:none">&nbsp; ${actionBean.syaratAm}</font></td></tr>
                    </c:if>

                </table>

                <br><c:if test="${edit}">
                    <td align="center">
                        <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </td>
                </c:if>
            </div>
        </fieldset>
    </div>
</s:form>