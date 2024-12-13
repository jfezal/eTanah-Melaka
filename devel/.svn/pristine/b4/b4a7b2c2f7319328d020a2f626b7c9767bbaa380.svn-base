<%-- 
    Document   : ulasan_mmk - draf ulasan MMK
    Created on : Jul 14, 2010, 4:40:36 PM
    Author     : nurul.izza
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">


<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

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

        if(rowcount < 6){
           var row = table.insertRow(rowcount);

           var leftcell = row.insertCell(0);
           var el = document.createElement('textarea');
           el.name = 'huraianPentadbir' + rowcount;
           el.rows = 5;
           el.cols = 160;
           el.style.textTransform = 'uppercase';
          leftcell.appendChild(el);
        } else{
            alert('Huraian Pentadbir Tanah telah lengkap.');
            $("#syorptd").focus();
            return true;
        }

    }

</script>

<s:form beanclass="etanah.view.stripes.pelupusan.UlasanMMKMlkActionBean">
    <s:messages/>
    <s:errors/>
    <s:hidden name="kandunganK.kertas.idKertas"/>

    <div class="subtitle">
        <fieldset class="aras1">          
            	<%--<c:set scope="request" var="senaraiPengarah"  value="${actionBean.listPengarah}"/>--%>
                <%--<c:set scope="request" var="senaraiPemohon"  value="${actionBean.listPemohon}"/>--%>
            <div class="content" align="center">
                <table border="0" width="80%">
                    <c:if test="${edit}">
                        <tr><td align="center"><b>KM No: <s:text name="kmno" id="kmno" size="12"/></b></td></tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td align="center"><b>KM No: ${actionBean.kmno}&nbsp;</b></td></tr>
                    </c:if>
                    <c:if test="${edit}">
                        <tr><td align="center"><b>(MAJLIS MESYUARAT KERAJAAN PADA <s:text name="tarikhMesyuarat" id="datepicker" class="datepicker" size="12" />)</b></td></tr><br>
                        <%--<font color="red">*</font>--%>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td align="center"><b>(MAJLIS MESYUARAT KERAJAAN PADA ${actionBean.tarikhMesyuarat})</b></td></tr><br>
                    </c:if >
                        <tr><td><b><font style="text-transform: uppercase">PERMOHONAN DARIPADA  ${actionBean.pemohon.pihak.nama}  UNTUK  ${actionBean.permohonan.kodUrusan.nama} BAGI ${actionBean.lokasi}
                                       
                                        UNTUK TUJUAN ${actionBean.permohonan.sebab}
                                        <%--<c:set value=" " var="gunaTanah"/>
                                        <c:forEach items="${actionBean.permohonan}" var="senaraiGunaTanah">
                                            <c:if test="${actionBean.permohonan.sebab ne gunaTanah}">
                                                <c:out value="${actionBean.permohonan.sebab}"/>
                                                <c:set value="${actionBean.permohonan.sebab}" var="gunaTanah"/>
                                            </c:if>
                                        </c:forEach>--%>
                                        .</font></b></td></tr><br>
                        <tr><td><b>1. TUJUAN</b></td></tr>
                    <c:if test="${edit}">
                        <tr><td><font style="text-transform: uppercase"><s:textarea rows="5" cols="160" name="tujuan"/></font></td></tr><br>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td><font style="text-transform:capitalize">1.1 &nbsp; ${actionBean.tujuan}</font></td></tr><br>
                    </c:if>
                        <tr><td><b>2. BUTIR-BUTIR PEMOHON</b></td></tr>
                        <tr><td>
                                <display:table class="tablecloth" name="${actionBean.listPemohon}" cellpadding="0" cellspacing="0" id="line">
                                    <display:column title="Bil" sortable="true" style="vertical-align:baseline">${line_rowNum}
                                        <s:hidden name="x" class="x${line_rowNum -1}" value="${line.pihak.idPihak}"/>
                                    </display:column>
                                    <display:column property="pihak.nama" title="Nama" style="vertical-align:baseline"/>
                                    <display:column title="Alamat" style="vertical-align:baseline">${line.pihak.suratAlamat1}
                                        <c:if test="${line.pihak.suratAlamat2 ne null}"> , ${line.pihak.suratAlamat2} </c:if>
                                        <c:if test="${line.pihak.suratAlamat3 ne null}"> , ${line.pihak.suratAlamat3} </c:if>
                                        <c:if test="${line.pihak.suratAlamat4 ne null}"> , ${line.pihak.suratAlamat4} </c:if>
                                        ${line.pihak.suratPoskod}
                                        ${line.pihak.suratNegeri.nama}
                                    </display:column>
                                    <display:column title="Tarikh Permohonan" property="permohonan.infoAudit.tarikhMasuk" format="{0,date,dd/MM/yyyy}" style="vertical-align:baseline"/>
                                    <display:column title="Ahli Lembaga Pengarah" style="vertical-align:baseline">
                                        <c:set value="1" var="count"/>
                                        <c:forEach items="${line.pihak.senaraiPengarah}" var="pengarah">
                                            <c:if test="${pengarah.nama ne null}">
                                                <c:out value="${count}"/>) &nbsp;
                                                <c:out value="${pengarah.nama}"/><br>
                                            </c:if>
                                            <c:set value="${count + 1}" var="count"/>
                                        </c:forEach>
                                    </display:column>
                                    <display:column title="Modal Dibayar" style="vertical-align:baseline"/>
                                    <display:column title="Modal Dibenarkan" style="vertical-align:baseline"/>
                                    <display:column title="No. Pendaftaran" style="vertical-align:baseline">
                                        <c:if test="${line.pihak.noPengenalan ne null}">${line.pihak.noPengenalan}</c:if>
                                        <c:if test="${line.pihak.noPengenalan eq null}">Tiada</c:if>
                                    </display:column>
                                    <display:column title="Tarikh Daftar" format="{0,date,dd/MM/yyyy}" style="vertical-align:baseline"></display:column>
                                </display:table>
                               
                        </td></tr><br>
                        <tr><td><b>3. BUTIR-BUTIR HAKMILIK </b></td></tr>
                        <tr><td>
                                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0" id="tbl1">
                                    <display:column title="Bil" sortable="true" style="vertical-align:baseline">${tbl1_rowNum}</display:column>
                                    <display:column title="Daerah" property="hakmilik.daerah.nama" class="daerah" style="vertical-align:baseline"/>
                                    <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" style="vertical-align:baseline"/>
                                    <display:column title="Nombor Lot/PT" style="vertical-align:baseline">
                                        ${tbl1.hakmilik.lot.nama} ${tbl1.hakmilik.noLot}
                                    </display:column>
                                    <display:column title="Jenis & No Hakmilik" style="vertical-align:baseline">
                                        ${tbl1.hakmilik.lot.nama} ${tbl1.hakmilik.kodHakmilik.kod} ${tbl1.hakmilik.noHakmilik}
                                    </display:column>
                                    <display:column title="Tempoh Hakmilik" style="vertical-align:baseline" property="hakmilik.tempohPegangan">
                                        <c:if test="${tbl1.hakmilik.tempohPegangan ne null}">${tbl1.hakmilik.tempohPegangan}</c:if>
                                        <c:if test="${tbl1.hakmilik.tempohPegangan eq null}">Tiada</c:if>
                                    </display:column>
                                    <display:column title="Luas" style="vertical-align:baseline"><fmt:formatNumber pattern="#,##0.0000" value="${tbl1.hakmilik.luas}"/>&nbsp;${tbl1.hakmilik.kodUnitLuas.nama}</display:column>
                                    <display:column title="Tuan Tanah" style="vertical-align:baseline">
                                        <c:forEach items="${tbl1.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                                            <c:if test="${senarai.jenis.kod eq 'PM'}">
                                                <c:out value="${senarai.pihak.nama}"/>
                                            </c:if>
                                        </c:forEach>
                                    </display:column>
                                    <display:column title="Jenis Kegunaan" style="vertical-align:baseline">
                                        <c:if test="${tbl1.hakmilik.kegunaanTanah.nama ne null}">${tbl1.hakmilik.kegunaanTanah.nama}</c:if>
                                        <c:if test="${tbl1.hakmilik.kegunaanTanah.nama eq null}">Tiada</c:if>
                                    </display:column>
                                    <display:column title="Syarat Nyata" style="vertical-align:baseline">
                                        <c:if test="${tbl1.hakmilik.syaratNyata.syarat ne null}">${tbl1.hakmilik.syaratNyata.syarat}</c:if>
                                        <c:if test="${tbl1.hakmilik.syaratNyata.syarat eq null}">Tiada</c:if>
                                    </display:column>
                                    <display:column title="Sekatan Kepentingan" style="vertical-align:baseline">
                                        <c:if test="${tbl1.hakmilik.sekatanKepentingan.sekatan ne null}">${tbl1.hakmilik.sekatanKepentingan.sekatan}</c:if>
                                        <c:if test="${tbl1.hakmilik.sekatanKepentingan.sekatan eq null}">Tiada</c:if>
                                    </display:column>
                                </display:table>
                                
                            </td></tr><br>
                        
                        <tr><td><b>4. KEADAAN DAN KEDUDUKAN TANAH</b></td></tr>
                            <tr><td>
                                    <table border="0" width="96%">
                                        <tr>
                                            <td id="tdLabel"><font color="black">Kedudukan Tanah :</font></td>
                                            <td id="tdDisplay">
                                                <c:if test="${actionBean.hakmilik.bandarPekanMukim.nama ne null}">${actionBean.hakmilik.bandarPekanMukim.nama} &nbsp;</c:if>
                                                <c:if test="${actionBean.hakmilik.bandarPekanMukim.nama eq null}">Tiada &nbsp;</c:if>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td id="tdLabel"><font color="black">Keadaan Tanah :</font></td>
                                            <td id="tdDisplay">
                                                <c:if test="${actionBean.laporanTanah.kecerunanTanah.nama ne null}">${actionBean.laporanTanah.kecerunanTanah.nama}&nbsp;</c:if>
                                                <c:if test="${actionBean.laporanTanah.kecerunanTanah.nama eq null}">Tiada &nbsp;</c:if>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td id="tdLabel"><font color="black">Jenis Tanaman :</font></td>
                                            <td id="tdDisplay">
                                                <c:if test="${actionBean.laporanTanah.usahaTanam ne null}">${actionBean.laporanTanah.usahaTanam}&nbsp;</c:if>
                                                <c:if test="${actionBean.laporanTanah.usahaTanam eq null}">Tiada</c:if>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td id="tdLabel"><font color="black">Bilangan Bangunan :</font></td>
                                            <td id="tdDisplay">
                                                <c:if test="${actionBean.laporanTanah.bilanganBangunan ne null}">${actionBean.laporanTanah.bilanganBangunan}&nbsp;</c:if>
                                                <c:if test="${actionBean.laporanTanah.bilanganBangunan eq null}">Tiada</c:if>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr><br>
                       <c:if test="${edit}">
                           <tr><td>
                                   <table border="0" width="96%" id="tblhuraian">
                                       <tr><td><b><font style="text-transform: uppercase">5. HURAIAN PENTADBIR DEARAH&nbsp;</font></b></td></tr>
                                       <tr><td><s:textarea name="huraianPentadbir" rows="5" cols="160"/></td></tr>
                                       <c:if test="${actionBean.huraianPentadbir2 ne null}">
                                           <tr><td><s:textarea name="huraianPentadbir2" rows="5" cols="160"/></td></tr><br>
                                       </c:if>
                                       <c:if test="${actionBean.huraianPentadbir3 ne null}">
                                           <tr><td><s:textarea name="huraianPentadbir3" rows="5" cols="160"/></td></tr><br>
                                       </c:if>
                                       <c:if test="${actionBean.huraianPentadbir4 ne null}">
                                           <tr><td><s:textarea name="huraianPentadbir4" rows="5" cols="160"/></td></tr><br>
                                       </c:if>
                                       <c:if test="${actionBean.huraianPentadbir5 ne null}">
                                           <tr><td><s:textarea name="huraianPentadbir5" rows="5" cols="160"/></td></tr><br>
                                       </c:if>
                                   </table>
                                   <c:if test="${actionBean.btn}">
                                       <s:button class="btn" value="Tambah" name="add" onclick="addRow('tblhuraian')"/>&nbsp;
                                   </c:if>
                               </td>
                           </tr><br>
                            <tr><td><b><font style="text-transform: uppercase">6. SYOR PENTADBIR DAERAH&nbsp;</font></b></td></tr>
                            <tr><td><s:textarea name="syorPentadbir" rows="5" cols="160" id="syorptd"/></td></tr><br>
                       </c:if>
                        <c:if test="${!edit}">
                            <tr><td><b><font style="text-transform: uppercase">7. HURAIAN PENTADBIR TANAH  ${actionBean.peng.kodCawangan.daerah.nama}&nbsp;</font></b></td></tr>
                            <tr><td><font style="text-transform:capitalize">6.1 ${actionBean.huraianPentadbir}&nbsp;</font></td></tr><br>
                            <c:if test="${actionBean.huraianPentadbir2 ne null}">
                                <tr><td><font style="text-transform:capitalize">6.2 ${actionBean.huraianPentadbir2}&nbsp;</font></td></tr><br>
                            </c:if>
                            <c:if test="${actionBean.huraianPentadbir3 ne null}">
                                <tr><td><font style="text-transform:capitalize">6.3 ${actionBean.huraianPentadbir3}&nbsp;</font></td></tr><br>
                            </c:if>
                            <c:if test="${actionBean.huraianPentadbir4 ne null}">
                                <tr><td><font style="text-transform:capitalize">6.4 ${actionBean.huraianPentadbir4}&nbsp;</font></td></tr><br>
                            </c:if>
                            <c:if test="${actionBean.huraianPentadbir5 ne null}">
                                <tr><td><font style="text-transform:capitalize">6.5 ${actionBean.huraianPentadbir5}&nbsp;</font></td></tr><br>
                            </c:if>
                            <tr><td><b><font style="text-transform: uppercase">8. SYOR PENTADBIR TANAH&nbsp;</font></b></td></tr>
                            <tr><td><font style="text-transform:capitalize">7.1 ${actionBean.syorPentadbir}&nbsp;</font></td></tr><br>
                        </c:if>
                        <%--<tr><td><p><label><font color="black">Untuk Lot ${actionBean.hakmilik.kegunaanTanah.nama}&nbsp;</font></label></p></td></tr>--%>
                            <tr><td>
                                    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0" id="tbl2">
                                        <display:column title="Bil" sortable="true" style="vertical-align:baseline">${tbl2_rowNum}</display:column>
                                        <display:column title="Nombor Lot/PT" style="vertical-align:baseline">
                                            ${tbl2.hakmilik.lot.nama} ${tbl2.hakmilik.noLot}
                                        </display:column>
                                        <display:column title="Kegunaan Tanah" property="hakmilik.kegunaanTanah.nama" style="vertical-align:baseline">
                                            <c:if test="${tbl2.hakmilik.kegunaanTanah.nama eq null}">Tiada</c:if>
                                        </display:column>
                                        <display:column title="Luas" style="vertical-align:baseline"><fmt:formatNumber pattern="#,##0.0000" value="${tbl2.hakmilik.luas}"/>&nbsp;${tbl2.hakmilik.kodUnitLuas.nama}
                                            <c:if test="${tbl2.hakmilik.luas eq null}">Tiada</c:if>
                                        </display:column>
                                        <display:column title="Tempoh Hakmilik" style="vertical-align:baseline" property="hakmilik.tempohPegangan">
                                            <c:if test="${tbl2.hakmilik.tempohPegangan eq null}">Tiada</c:if>
                                        </display:column>
                                        <display:column title="Kawasan Rizab" style="vertical-align:baseline" property="hakmilik.rizab.nama">
                                            <c:if test="${tbl2.hakmilik.rizab.nama eq null}">Tiada</c:if>
                                        </display:column>
                                        <display:column title="Jenis Hakmilik Bersyarat" property="hakmilik.kodHakmilik.nama" style="vertical-align:baseline">
                                            <c:if test="${tbl2.hakmilik.kodHakmilik.nama eq null}">Tiada</c:if>
                                        </display:column>
                                        <display:column title="Jenis Hakmilik Tetap" style="vertical-align:baseline"></display:column>
                                        <display:column title="Kadar Cukai" style="vertical-align:baseline"></display:column>
                                        <display:column title="Kadar Premium" style="vertical-align:baseline"></display:column>
                                        <display:column title="Kadar Bayaran Ukur Dan Batu Sempadan " style="vertical-align:baseline"></display:column>
                                        <display:column title="Kadar Bayaran Pendaftaran Dan Penyediaan Hakmilik Sementara/Tetap" style="vertical-align:baseline"></display:column>
                                        <display:column title="Kategori" style="vertical-align:baseline" property="hakmilik.kategoriTanah.nama">
                                            <c:if test="${tbl2.hakmilik.kategoriTanah.nama eq null}">Tiada</c:if>
                                        </display:column>
                                        <display:column title="Syarat Nyata" property="hakmilik.syaratNyata.syarat" style="vertical-align:baseline">
                                            <c:if test="${tbl2.hakmilik.syaratNyata.syarat eq null}">Tiada</c:if>
                                        </display:column>
                                        <display:column title="Sekatan Kepentingan" property="hakmilik.sekatanKepentingan.sekatan" style="vertical-align:baseline">
                                            <c:if test="${tbl2.hakmilik.sekatanKepentingan.sekatan eq null}">Tiada</c:if>
                                        </display:column>
                                    </display:table>
                                    
                                   </td>
                            </tr><br>
                        <c:if test="${edit}">
                            <tr><td><b>7. HURAIAN PENGARAH TANAH DAN GALIAN NEGERI</b></td></tr>
                            <tr><td><s:textarea name="huraianPtg" rows="5" cols="160"/></td></tr><br>
                            <tr><td><b>8. SYOR PENGARAH TANAH DAN GALIAN NEGERI</b></td></tr>
                            <tr><td><s:textarea name="syorPtg" rows="5" cols="160"/></td></tr><br>
                        </c:if>
                        <c:if test="${!edit}">
                            <tr><td><b>7. HURAIAN PENGARAH TANAH DAN GALIAN NEGERI</b></td></tr>
                            <tr><td><font style="text-transform:capitalize">8.1 ${actionBean.huraianPtg}&nbsp;</font></td></tr><br>
                            <tr><td><b>8. SYOR PENGARAH TANAH DAN GALIAN NEGERI</b></td></tr>
                            <tr><td><font style="text-transform:capitalize">9.1 ${actionBean.syorPtg}&nbsp;</font></td></tr><br>
                        </c:if>
                            <tr><td><b>9. KEPUTUSAN</b></td></tr>
                            <tr><td width="100%">9.1 Dibentangkan untuk pertimbangan dan keputusan Majlis Mesyuarat Kerajaan Negeri.</td></tr><br>
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
