<%-- 
    Document   : kertas_Pertimbangan_PTD
    Created on : Jul 9, 2010, 10:16:16 AM
    Author     : NageswaraRao
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
    function addRow1(tableid){
        var table = document.getElementById(tableid);
        var rowcount = table.rows.length;

        if(rowcount < 2){
           var row = table.insertRow(rowcount);

           var leftcell = row.insertCell(0);
           var el = document.createElement('textarea');
           el.name = 'latarbelakang' + (rowcount);
           el.rows = 5;
           el.cols = 150;
           el.style.textTransform = 'uppercase';
          leftcell.appendChild(el);
          <%--alert('rowcount: '+rowcount);--%>
        } else{
            alert('Huraian untuk Latar Belakang telah melebihi had yang dibenarkan.');
            $("#perihalTanah").focus();
            return true;
        }

    }

    function addRow2(tableid2){
        var table = document.getElementById(tableid2);
        var rowcount = table.rows.length;

        if(rowcount < 3){
           var row = table.insertRow(rowcount);

           var leftcell = row.insertCell(0);
           var el = document.createElement('textarea');
           el.name = 'perakuanpenolong' + rowcount;
           el.rows = 5;
           el.cols = 150;
           el.style.textTransform = 'uppercase';
          leftcell.appendChild(el);
          <%--alert('rowcount: '+rowcount);--%>
        } else{
            alert('Perakuan Penolong Pegawai Tanah telah melebihi had yang dibenarkan.');
            $("#perakuanpenolong4").focus();
            return true;
        }

    }
    function addRow3(tableid3){
        var table = document.getElementById(tableid3);
        var rowcount = table.rows.length;

        if(rowcount < 3){
           var row = table.insertRow(rowcount);

           var leftcell = row.insertCell(0);
           var el = document.createElement('textarea');
           el.name = 'keputusanptd' + rowcount;
           el.rows = 5;
           el.cols = 150;
           el.style.textTransform = 'uppercase';
          leftcell.appendChild(el);
          <%--alert('rowcount: '+rowcount);--%>
        } else{
            alert('Ulasan Pentadbir Tanah telah melebihi had yang dibenarkan.');
            return true;
        }

    }
</script>

<s:form beanclass="etanah.view.stripes.pembangunan.KertasPertimbanganPTDMelakaActionBean">
    <s:messages/>
    <s:errors/>
    <s:hidden name="kandunganK.kertas.idKertas"/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend></legend>
            <div class="content" align="center">
                <table border="0" width="80%" align="center">
                    <tr>
                        <td align="center"><b><font  style="text-transform:uppercase">KERTAS UNTUK PERTIMBANGAN PENTADBIR TANAH ${actionBean.pengguna.kodCawangan.daerah.nama}</font></b></td>
                    </tr>
                </table>
                    <br><br>
                    <table border="0" width="80%" cellspacing="10">
                    <%--<tr><td align="center"><b><font style="text-transform:uppercase">KERTAS UNTUK PERTIMBANGAN PENTADBIR TANAH ${actionBean.pengguna.kodCawangan.daerah.nama}</font></b></td></tr>--%>
                    <c:if test="${edit}">
                        <tr><td><b><font style="text-transform:uppercase"><s:textarea name="tajuk" rows="5" cols="150"/></font></b></td></tr>
                    </c:if>
                    <c:if test="${!edit}">
                         <tr><td><b><font style="text-transform:uppercase">${actionBean.tajuk}</font></b></td></tr>
                    </c:if>
                    <tr><td><b>1. TUJUAN</b></td></tr>
                    <c:if test="${edit}">
                        <tr><td><s:textarea name="tujuan" rows="5" cols="150"/></td></tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td><font style="text-transform:capitalize">${actionBean.tujuan}</font></td></tr>
                    </c:if>
                    <tr><td><b>2. LATAR BELAKANG </b></td></tr>
                    <c:if test="${edit}">
                        <tr><td>
                               <table border="0" width="96%" id="tbllatar">
                                   <tr><td><s:textarea name="latarbelakang" rows="5" cols="150"/></td></tr>
                                   <c:if test="${actionBean.latarbelakang1 ne 'TIADA DATA.'}">
                                       <tr><td><s:textarea name="latarbelakang1" rows="5" cols="150"/></td></tr>
                                   </c:if>
                               </table>
                               <c:if test="${actionBean.btn}">
                                   <s:button class="btn" value="Tambah" name="add1" onclick="addRow1('tbllatar')"/>&nbsp;
                               </c:if>
                           </td>
                        </tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td><font style="text-transform:capitalize">2.1 ${actionBean.latarbelakang}&nbsp;</font></td></tr>
                        <c:if test="${actionBean.latarbelakang1 ne 'TIADA DATA.'}">
                            <tr><td><font style="text-transform:capitalize">2.2 ${actionBean.latarbelakang1}&nbsp;</font></td></tr>
                        </c:if>
                    </c:if>
                    <tr><td><b>3. BUTIR-BUTIR TANAH </b></td></tr>
                    <tr><td>
                            <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0" id="tbl1">
                                <display:column title="Bil" sortable="true" style="vertical-align:baseline">${tbl1_rowNum}</display:column>
                                <display:column title="Jenis & No Hakmilik" style="vertical-align:baseline">
                                    ${tbl1.hakmilik.lot.nama} ${tbl1.hakmilik.kodHakmilik.kod} ${tbl1.hakmilik.noHakmilik}
                                </display:column>
                                <display:column title="No.Lot/PT." style="vertical-align:baseline">
                                    ${tbl1.hakmilik.lot.nama} ${tbl1.hakmilik.noLot}
                                </display:column>
                                <display:column title="Bandar/Pekan/Mukim" property="hakmilik.bandarPekanMukim.nama"  style="vertical-align:baseline"/>
                                <display:column title="Daerah" property="hakmilik.daerah.nama" style="vertical-align:baseline"/>
                                <display:column title="Hasil Tahunan" style="vertical-align:baseline">
                                    <c:if test="${tbl1.hakmilik.cukai ne null}">
                                        RM ${tbl1.hakmilik.cukai}
                                    </c:if>
                                </display:column>
                                <display:column title="Tempoh Pajakan" style="vertical-align:baseline"> ${tbl1.hakmilik.tempohPegangan} </display:column>
                                <display:column title="Tarikh Tamat Pajakan" style="vertical-align:baseline">
                                    <fmt:formatDate pattern="dd-MM-yyyy" value="${tbl1.hakmilik.tarikhLuput}" />
                                </display:column>
                                <display:column title="Luas Lot" style="vertical-align:baseline"><fmt:formatNumber pattern="#,##0.0000" value="${tbl1.hakmilik.luas}"/>&nbsp;${tbl1.hakmilik.kodUnitLuas.nama}</display:column>
                                <display:column title="Bebanan" style="vertical-align:baseline"></display:column>
                                <display:column title="Penjenisan" property="hakmilik.kategoriTanah.nama" style="vertical-align:baseline"/>
                                <display:column title="Syarat Nyata" property="hakmilik.syaratNyata.syarat" style="vertical-align:baseline"/>
                                <display:column title="Sekatan Kepentingan" property="hakmilik.sekatanKepentingan.sekatan" style="vertical-align:baseline"/>
                            </display:table>
                        </td></tr>
                    <c:if test="${edit}">
                        <tr><td><s:textarea rows="5" cols="150" name="perihalTanah" id="perihalTanah"/></td></tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td>3.1 &nbsp;<font style="text-transform:capitalize">${actionBean.perihalTanah}</font></td></tr>
                    </c:if>
                    <tr><td>3.2 Tanah-tanah yang bersempadan dengan tanah ini ialah :-</td></tr>
                    <tr><td align="center">
                        <%--<td>--%>
                            <table class="tablecloth">
                                <tr>
                                    <th>&nbsp;</th><th>Jenis Tanah</th><th>Nombor Lot</th><th>Kegunaan</th>
                                </tr>
                                <tr>
                                    <th>
                                        Utara
                                    </th>
                                    <td>
                                        <c:if test="${actionBean.laporanTanah.sempadanUtaraMilikKerajaan ne 'T'}">Kerajaan</c:if>
                                        <c:if test="${actionBean.laporanTanah.sempadanUtaraMilikKerajaan eq 'T'}">Milik</c:if>
                                    </td>
                                    <td>
                                        <c:if test="${actionBean.laporanTanah.sempadanUtaraNoLot ne null}"> ${actionBean.laporanTanah.sempadanUtaraNoLot}&nbsp; </c:if>
                                        <c:if test="${actionBean.laporanTanah.sempadanUtaraNoLot eq null}"> Tiada Data </c:if>
                                    </td>
                                    <td>
                                        <c:if test="${actionBean.laporanTanah.sempadanUtaraKegunaan ne null}"> ${actionBean.laporanTanah.sempadanUtaraKegunaan}&nbsp; </c:if>
                                        <c:if test="${actionBean.laporanTanah.sempadanUtaraKegunaan eq null}"> Tiada Data </c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <th>
                                        Selatan
                                    </th>
                                    <td>
                                        <c:if test="${actionBean.laporanTanah.sempadanSelatanMilikKerajaan ne 'T'}">Kerajaan</c:if>
                                        <c:if test="${actionBean.laporanTanah.sempadanSelatanMilikKerajaan eq 'T'}">Milik</c:if>
                                    </td>
                                    <td>
                                        <c:if test="${actionBean.laporanTanah.sempadanSelatanNoLot ne null}"> ${actionBean.laporanTanah.sempadanSelatanNoLot}&nbsp; </c:if>
                                        <c:if test="${actionBean.laporanTanah.sempadanSelatanNoLot eq null}"> Tiada Data </c:if>
                                    </td>
                                    <td>
                                        <c:if test="${actionBean.laporanTanah.sempadanSelatanKegunaan ne null}"> ${actionBean.laporanTanah.sempadanSelatanKegunaan}&nbsp; </c:if>
                                        <c:if test="${actionBean.laporanTanah.sempadanSelatanKegunaan eq null}"> Tiada Data </c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <th>
                                        Timur
                                    </th>
                                    <td>
                                        <c:if test="${actionBean.laporanTanah.sempadanTimurMilikKerajaan ne 'T'}">Kerajaan</c:if>
                                        <c:if test="${actionBean.laporanTanah.sempadanTimurMilikKerajaan eq 'T'}">Milik</c:if>
                                    </td>
                                    <td>
                                        <c:if test="${actionBean.laporanTanah.sempadanTimurNoLot ne null}"> ${actionBean.laporanTanah.sempadanTimurNoLot}&nbsp; </c:if>
                                        <c:if test="${actionBean.laporanTanah.sempadanTimurNoLot eq null}"> Tiada Data </c:if>
                                    </td>
                                    <td>
                                        <c:if test="${actionBean.laporanTanah.sempadanTimurKegunaan ne null}"> ${actionBean.laporanTanah.sempadanTimurKegunaan}&nbsp; </c:if>
                                        <c:if test="${actionBean.laporanTanah.sempadanTimurKegunaan eq null}"> Tiada Data </c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <th>
                                        Barat
                                    </th>
                                    <td>
                                        <c:if test="${actionBean.laporanTanah.sempadanBaratMilikKerajaan ne 'T'}">Kerajaan</c:if>
                                        <c:if test="${actionBean.laporanTanah.sempadanBaratMilikKerajaan eq 'T'}">Milik</c:if>
                                    </td>
                                    <td>
                                        <c:if test="${actionBean.laporanTanah.sempadanBaratNoLot ne null}"> ${actionBean.laporanTanah.sempadanBaratNoLot}&nbsp; </c:if>
                                        <c:if test="${actionBean.laporanTanah.sempadanBaratNoLot eq null}"> Tiada Data </c:if>
                                    </td>
                                    <td>
                                        <c:if test="${actionBean.laporanTanah.sempadanBaratKegunaan ne null}"> ${actionBean.laporanTanah.sempadanBaratKegunaan}&nbsp; </c:if>
                                        <c:if test="${actionBean.laporanTanah.sempadanBaratKegunaan eq null}"> Tiada Data </c:if>
                                    </td>
                                </tr>
                            </table>
                        </td></tr>
                    <tr><td><b><font style="text-transform:uppercase">4. PERAKUAN PENOLONG PEGAWAI DAERAH ${actionBean.pejTanah}</font></b></td></tr>
                    <c:if test="${edit}">
                        <tr><td>
                               <table border="0" width="96%" id="tblpenolong">
                                   <tr><td><s:textarea name="perakuanpenolong" rows="5" cols="150"/></td></tr>
                                   <c:if test="${actionBean.perakuanpenolong1 ne 'TIADA DATA.'}">
                                       <tr><td><s:textarea name="perakuanpenolong1" rows="5" cols="150"/></td></tr>
                                   </c:if>
                                   <c:if test="${actionBean.perakuanpenolong2 ne 'TIADA DATA.'}">
                                       <tr><td><s:textarea name="perakuanpenolong2" rows="5" cols="150"/></td></tr>
                                   </c:if>
                               </table>
                               <c:if test="${actionBean.btn}">
                                   <s:button class="btn" value="Tambah" name="add2" onclick="addRow2('tblpenolong')"/>&nbsp;
                               </c:if>
                           </td>
                        </tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td><font style="text-transform:capitalize">4.1 &nbsp;${actionBean.perakuanpenolong}&nbsp;</font></td></tr>
                        <c:if test="${actionBean.perakuanpenolong1 ne 'TIADA DATA.'}">
                            <tr><td><font style="text-transform:capitalize">a) &nbsp;${actionBean.perakuanpenolong1}&nbsp;</font></td></tr>
                        </c:if>
                        <c:if test="${actionBean.perakuanpenolong2 ne 'TIADA DATA.'}">
                            <tr><td><font style="text-transform:capitalize">b) &nbsp;${actionBean.perakuanpenolong2}&nbsp;</font></td></tr>
                        </c:if>
                    </c:if>
                    <%--<tr><td>
                       <display:table  class="tablecloth" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0" id="tbl1">
                            <display:column title="Jenis & No Hakmilik" style="vertical-align:baseline">${tbl1.hakmilik.lot.nama} ${tbl1.hakmilik.kodHakmilik.kod} ${tbl1.hakmilik.noHakmilik}</display:column>
                            <display:column title="Premium" style="vertical-align:baseline">RM${tbl1.kadarPremium}</display:column>
                            <display:column title="Cukai" style="vertical-align:baseline">RM${tbl1.cukaiBaru}</display:column>
                            <display:column title="Bayaran Hakmilik " style="vertical-align:baseline">${actionBean.hakmilik.kodHakmilik.nama}</display:column>
                            <display:column title="Syarat Nyata" style="vertical-align:baseline">${tbl1.syaratNyataBaru.syarat}</display:column>
                            <display:column title="Sekatan Kepentingan" style="vertical-align:baseline">${tbl1.sekatanKepentinganBaru.sekatan}</display:column>
                            <display:column title="Syarat Am" style="vertical-align:baseline"></display:column>
                            <display:column title="Penjenisan" property="hakmilik.kategoriTanah.nama" style="vertical-align:baseline"></display:column>
                        </display:table>
                    </td></tr>--%>
                    <c:if test="${edit}">
                        <tr><td><s:textarea name="perakuanpenolong3" rows="5" cols="150" id="perakuanpenolong3"/></td></tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td><font style="text-transform:capitalize">4.2 &nbsp;${actionBean.perakuanpenolong3}</font></td></tr>
                    </c:if>
                    <tr><td align="center"><b><font style="text-transform:uppercase">KEPUTUSAN PENTADBIR TANAH ${actionBean.pejTanah}</font></b></td></tr>
                    <c:if test="${edit}">
                        <tr><td>
                               <table border="0" width="96%" id="tblkeputusan">
                                   <tr><td>Saya Pentadbir Tanah ${actionBean.pejTanah}&nbsp; melalui Perwakilan Kuasa MMKN 21A/11/96 yang bersidang pada 10 April 1996 dan disahkan pada 17 April 1996
                                           <b><c:forEach items="${actionBean.result}" var="listresult">
                                                   <s:radio name="keputusanptd" value="${listresult}"/>${listresult} &nbsp;
                                               </c:forEach></b>
                                    ke atas ${actionBean.permohonan.kodUrusan.nama}&nbsp; di ${actionBean.lokasi}&nbsp; diluluskan kepada ${actionBean.nama}&nbsp; dengan syarat-syarat berikut :</td></tr>
                                   <%--<c:if test="${actionBean.keputusanptd1 ne null}">--%>
                                       <tr><td><s:textarea name="keputusanptd1" rows="5" cols="150"/></td></tr>
                                   <%--</c:if>--%>
                                   <c:if test="${actionBean.keputusanptd2 ne 'TIADA DATA.'}">
                                       <tr><td><s:textarea name="keputusanptd2" rows="5" cols="150"/></td></tr>
                                   </c:if>
                               </table>
                               <c:if test="${actionBean.btn}">
                                   <s:button class="btn" value="Tambah" name="add3" onclick="addRow3('tblkeputusan')"/>&nbsp;
                               </c:if>
                           </td>
                        </tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td>Saya Pentadbir Tanah ${actionBean.pejTanah}&nbsp; melalui Perwakilan Kuasa MMKN 21A/11/96 yang bersidang pada 10 April 1996 dan disahkan pada 17 April 1996
                                <b>${actionBean.keputusan}&nbsp;</b> ke atas ${actionBean.permohonan.kodUrusan.nama}&nbsp; di ${actionBean.lokasi}&nbsp; diluluskan kepada ${actionBean.nama}&nbsp; dengan syarat-syarat berikut :</td></tr>
                        <c:if test="${actionBean.keputusanptd1 ne 'TIADA DATA.'}">
                            <tr><td><font style="text-transform:capitalize">a) &nbsp;${actionBean.keputusanptd1}&nbsp;</font></td></tr>
                        </c:if>
                        <c:if test="${actionBean.keputusanptd2 ne 'TIADA DATA.'}">
                            <tr><td><font style="text-transform:capitalize">b) &nbsp;${actionBean.keputusanptd2}&nbsp;</font></td></tr>
                        </c:if>
                    </c:if>
                   <%-- <tr><td>Syarat-syarat hakmilik:</td></tr>
                    <tr><td>
                       <display:table  class="tablecloth" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0" id="tbl1">
                            <display:column title="Jenis & No Hakmilik" style="vertical-align:baseline">${tbl1.hakmilik.lot.nama} ${tbl1.hakmilik.kodHakmilik.kod} ${tbl1.hakmilik.noHakmilik}</display:column>
                            <display:column title="Premium" style="vertical-align:baseline">RM${tbl1.kadarPremium}</display:column>
                            <display:column title="Cukai" style="vertical-align:baseline">RM${tbl1.cukaiBaru}</display:column>
                            <display:column title="Bayaran Hakmilik " style="vertical-align:baseline">${actionBean.hakmilik.kodHakmilik.nama}</display:column>
                            <display:column title="Syarat Nyata" style="vertical-align:baseline">${tbl1.syaratNyataBaru.syarat}</display:column>
                            <display:column title="Sekatan Kepentingan" style="vertical-align:baseline">${tbl1.sekatanKepentinganBaru.sekatan}</display:column>
                        </display:table>
                    </td></tr>
                    <tr><td>Penjenisan:</td></tr>
                     <c:if test="${edit}">
                        <tr><td><s:textarea name="keputusanptd2" rows="5" cols="160"/></td></tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td><font style="text-transform:capitalize">${actionBean.keputusanptd2}</font></td></tr>
                    </c:if>
                    <tr><td>Syarat Am:</td></tr>
                     <c:if test="${edit}">
                        <tr><td><s:textarea name="keputusanptd3" rows="5" cols="160"/></td></tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td><font style="text-transform:capitalize">${actionBean.keputusanptd3}</font></td></tr>
                    </c:if>--%>
                </table>
                <c:if test="${edit}">
                    <br>
                    <p align="center">
                        <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </p>
                </c:if>
            </div>
        </fieldset>
    </div>
</s:form>
