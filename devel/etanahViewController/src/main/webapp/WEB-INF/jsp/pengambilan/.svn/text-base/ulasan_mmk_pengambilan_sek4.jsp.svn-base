<%-- 
    Document   : ulasan_mmk_pengambilan_sek4
    Created on : 10-Jun-2010, 13:05:56
    Author     : nordiyana
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

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
<script language="javascript" type="text/javascript">

    function addRow(tableID) {
        document.form1.rowCount.value = 1;
        var table = document.getElementById(tableID);

        var rowCount = table.rows.length;
        var row = table.insertRow(rowCount);

        var cell2 = row.insertCell(0);
        cell2.innerHTML = "<b>"+"2." +(rowCount+1)+"</b>";

        var cell1 = row.insertCell(1);
        var element1 = document.createElement("textarea");
        element1.t = "kandungan"+(rowCount+1);
        element1.rows = 5;
        element1.cols = 150;
        element1.name ="kandungan"+(rowCount+1);
        cell1.appendChild(element1);
        //alert(element1.name)
        document.form1.rowCount.value=rowCount +1;
    }
    function deleteRow()
    {
        var i = document.form1.rowCount.value;
        var x= document.getElementById('dataTable').rows[i-1].cells;
        var idKandungan = x[0].innerHTML;

        document.getElementById('dataTable').deleteRow(i-1);
        document.form1.rowCount.value = i -1;

       var url = '${pageContext.request.contextPath}/kertas_mmkn?deleteSingle&idKandungan='
                +idKandungan;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
   }

</script>

<s:form beanclass="etanah.view.stripes.pengambilan.UlasanMMKSek4ActionBean">
    <s:messages/>
    <s:errors/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend></legend>
            	<%--<c:set scope="request" var="senaraiPengarah"  value="${actionBean.listPengarah}"/>--%>
                <%--<c:set scope="request" var="senaraiPemohon"  value="${actionBean.listPemohon}"/>--%>

            <div class="content" align="center">
                <table border="0" width="80%">
                    <c:if test="${edit}">
                        <tr><td align="center"><b>DRAF KERTAS PERMOHONAN PENGAMBILAN TANAH NEGERI SEMBILAN DARUL KHUSUS</b></td></tr><br>
                        <tr><td align="center"><b>KERTAS BIL<font color="red">*</font> <s:text name="kertasbil"  size="12" />/2010</b></td></tr><br>
                        <tr><td align="center"><b>TARIKH :<font color="red">*</font> <s:text name="tarikhMesyuarat" id="datepicker" class="datepicker" size="12" /></b></td></tr><br>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td align="center"><b>DRAF KERTAS PERMOHONAN PENGAMBILAN TANAH NEGERI SEMBILAN DARUL KHUSUS</b></td></tr><br>
                        <tr><td align="center"><b>KERTAS BIL ${actionBean.kertasbil}/2010</b></td></tr><br>
                        <tr><td align="center"><b>TARIKH : ${actionBean.tarikhMesyuarat})</b></td></tr><br>
                    </c:if >
                    <%--<c:if test="${edit}">
                        <tr><td><b><font style="text-transform: uppercase">PERMOHONAN PENGAMBILAN TANAH DI ATAS KESELURUHAN/SEBAHAGIAN TANAH LOT ${actionBean.hakmilik.noLot}, ${actionBean.hakmilik.lokasi}, DI ${actionBean.hakmilik.bandarPekanMukim.nama}, DAERAH ${actionBean.hakmilik.daerah.nama}, NEGERI SEMBILAN UNTUK TUJUAN ${actionBean.permohonan.sebab} DARIPADA ${actionBean.pemohon.pihak.nama}, TERHADAP PERINTAH PENTADBIR TANAH ${actionBean.hakmilik.daerah.nama} DI BAWAH SEKSYEN 4 AKTA PENGAMBILAN TANAH 1960.</font></b></td></tr><br>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td><b><font style="text-transform: uppercase">PERMOHONAN PENGAMBILAN TANAH DI ATAS KESELURUHAN/SEBAHAGIAN TANAH LOT ${actionBean.hakmilik.noLot}, ${actionBean.hakmilik.lokasi}, DI ${actionBean.hakmilik.bandarPekanMukim.nama}, DAERAH ${actionBean.hakmilik.daerah.nama}, NEGERI SEMBILAN UNTUK TUJUAN ${actionBean.permohonan.sebab} DARIPADA ${actionBean.pemohon.pihak.nama}, TERHADAP PERINTAH PENTADBIR TANAH ${actionBean.hakmilik.daerah.nama} DI BAWAH SEKSYEN 4 AKTA PENGAMBILAN TANAH 1960.</font></b></td></tr><br></c:if>--%>

                    <c:if test="${edit}">
                        <tr><td><b><font style="text-transform: uppercase">PERMOHONAN PENGAMBILAN TANAH DI ATAS KESELURUHAN/SEBAHAGIAN TANAH LOT
                                        <c:forEach items="${actionBean.hakmilikPermohonanList}" var="senaraiHakmilik" >
                                            <%--<c:out value="${senaraiHakmilik.hakmilik.lot.nama}"/>--%>
                                            <c:out value="${senaraiHakmilik.hakmilik.noLot}"/>, DI
                                            <c:out value="${senaraiHakmilik.hakmilik.bandarPekanMukim.nama}"/>, DAERAH
                                            <c:out value="${senaraiHakmilik.hakmilik.daerah.nama}"/>,
                                        </c:forEach>
                                           <%-- ${actionBean.hakmilik.noLot}, ${actionBean.hakmilik.lokasi}, DI ${actionBean.hakmilik.bandarPekanMukim.nama}, DAERAH ${actionBean.hakmilik.daerah.nama}, --%>
                                           NEGERI SEMBILAN UNTUK TUJUAN ${actionBean.permohonan.sebab} DARIPADA ${actionBean.pemohon.pihak.nama}, TERHADAP PERINTAH PENTADBIR TANAH ${actionBean.hakmilik.daerah.nama} DI BAWAH SEKSYEN 4 AKTA PENGAMBILAN TANAH 1960.</font></b></td></tr><br>

                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td><b><font style="text-transform: uppercase">PERMOHONAN PENGAMBILAN TANAH DI ATAS KESELURUHAN/SEBAHAGIAN TANAH LOT
                                        <c:forEach items="${actionBean.hakmilikPermohonanList}" var="senaraiHakmilik" >
                                            <%--<c:out value="${senaraiHakmilik.hakmilik.lot.nama}"/>--%>
                                            <c:out value="${senaraiHakmilik.hakmilik.noLot}"/>, DI
                                            <c:out value="${senaraiHakmilik.hakmilik.bandarPekanMukim.nama}"/>, DAERAH
                                            <c:out value="${senaraiHakmilik.hakmilik.daerah.nama}"/>,
                                        </c:forEach>
                                           <%-- ${actionBean.hakmilik.noLot}, ${actionBean.hakmilik.lokasi}, DI ${actionBean.hakmilik.bandarPekanMukim.nama}, DAERAH ${actionBean.hakmilik.daerah.nama}, --%>
                                           NEGERI SEMBILAN UNTUK TUJUAN ${actionBean.permohonan.sebab} DARIPADA ${actionBean.pemohon.pihak.nama}, TERHADAP PERINTAH PENTADBIR TANAH ${actionBean.hakmilik.daerah.nama} DI BAWAH SEKSYEN 4 AKTA PENGAMBILAN TANAH 1960.</font></b></td></tr><br>
                        </c:if>

                    <tr><td><b>1. TUJUAN</b></td></tr>
                    <c:if test="${!edit}">
                        <tr><td><font style="text-transform:capitalize">1.1 &nbsp; ${actionBean.tujuan}</font></td></tr><br>
                    </c:if>
                    <c:if test="${edit}">
                        <tr><td><font style="text-transform: uppercase"><s:textarea rows="5" cols="150" name="tujuan"/></font></td></tr><br>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td><b>2. LATAR BELAKANG</b></td></tr>
                        <tr><td align="right">
			        <s:button name="latarBelakang" value="Tambah" class="btn" onclick="addRow('dataTable')" />
                                <s:button name="latarBelakang" value="Hapus" class="btn" onclick="deleteRow()" />
                                <table id ="dataTable">
                                    <c:set var="i" value="1" />
                                    <c:if test="${actionBean.senaraiKertasKandungan eq null}">
                                        <%--<s:hidden name="rowCount" value="1"/>--%>
                                        <tr><td>2.1</td>
                                            <td><s:textarea name="kandungan1" id="kandungan1" rows="5" cols="150"/></td></tr></c:if>
                                        <c:forEach items="${actionBean.senaraiKertasKandungan}" var="line">
                                            <%--<s:hidden name="rowCount" value="${i}"/>--%>
                                        <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                            <td><s:textarea name="kandungan${i}" id="kandungan${i}" rows="5" cols="150">${line.kandungan}</s:textarea>
                                            </td></tr>
                                            <c:set var="i" value="${i+1}" />
                                        </c:forEach>
                                        <s:hidden name="rowCount" value="${i-1}"/>
                                </table>
                        </td></tr>
                        <tr><td>2.1<s:textarea name="latarBelakang" rows="5" cols="150" disabled="true"/></td></tr><br>
                        <tr><td>2.2<s:textarea name="latarBelakang2" rows="5" cols="150" disabled="true"/></td></tr><br>
                        <tr><td>2.3<s:textarea name="latarBelakang3" rows="5" cols="150" disabled="true"/></td></tr><br>
                    </c:if>
                     <c:if test="${edit}">
                         <tr><td><b>2. LATAR BELAKANG</b></td></tr>
                        <tr><td>2.1<s:textarea name="latarBelakang" rows="5" cols="150"/></td></tr><br>
                        <tr><td>2.2<s:textarea name="latarBelakang2" rows="5" cols="150"/></td></tr><br>
                        <tr><td>2.3<s:textarea name="latarBelakang3" rows="5" cols="150"/></td></tr><br>
                     </c:if>
                    
                    <tr><td><b>3. PERIHAL TANAH </b></td></tr>
                    <tr><td>Butir-butir Tanah:-</td></tr>
                          <tr><td>
                                  <table border="0" width="96%">
                                    <tr>
                                        <td id="tdLabel"><font color="black">Jenis Hakmilik :</font></td>
                                        <td id="tdDisplay">
                                            <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodHakmilik.nama ne null}"> ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodHakmilik.nama}&nbsp; </c:if>
                                            <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodHakmilik.nama eq null}}"> Tiada Data </c:if>&nbsp;&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td id="tdLabel"><font color="black">Lot :</font></td>
                                        <td id="tdDisplay">
                                            <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.noLot ne null}"> ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.noLot}&nbsp; </c:if>
                                            <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.noLot eq null}}"> Tiada Data </c:if>&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td id="tdLabel"><font color="black">Luas :</font></td>
                                        <td id="tdDisplay"><fmt:formatNumber pattern="#,##0.0000" value="${actionBean.hakmilik.luas}"/>&nbsp;${actionBean.hakmilik.kodUnitLuas.nama}&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td id="tdLabel"><font color="black">Jenis Tanah :</font></td>
                                        <td id="tdDisplay">${actionBean.hakmilik.kodTanah.nama}&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td id="tdLabel"><font color="black">Mukim :</font></td>
                                        <td id="tdDisplay">${actionBean.hakmilik.bandarPekanMukim.nama}&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td id="tdLabel"><font color="black">Daerah :</font></td>
                                        <td id="tdDisplay">${actionBean.hakmilik.daerah.nama}&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td id="tdLabel"><font color="black">Keadaan Tanah :</font></td>
                                        <td id="tdDisplay">${actionBean.laporanTanah.kelapanganTanah.nama}&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td id="tdLabel"><font color="black">Keadaan Atas Tanah :</font></td>
                                        <td id="tdDisplay">${actionBean.laporanTanah.kecerunanTanah.nama}&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td id="tdLabel"><font color="black">Klasifikasi Atas Tanah :</font></td>
                                        <td id="tdDisplay">${actionBean.laporanTanah.strukturTanah.nama}&nbsp;</td>
                                    </tr>
                                </table>
                        </td></tr><br>
                    <%--<tr><td><b>4. ULASAN JABATAN-JABATAN TEKNIKAL</b></td></tr>--%>
                    <%--<c:if test="${edit}">
                        <tr><td>4.1<s:select name="subtajuk1" style="width:400px" id="jabatan">
                                <s:option value="">Sila Pilih</s:option>
                                <s:option value="Jabatan Kebajikan Masyarakat">Jabatan Kebajikan Masyarakat</s:option>
                                <s:option value="Jabatan Kerja Raya">Jabatan Kerja Raya</s:option>
                                <s:option value="Jabatan Pengairan dan Saliran">Jabatan Pengairan dan Saliran</s:option>
                                <s:option value="Jabatan Perancang Bandar dan Desa">Jabatan Perancang Bandar dan Desa</s:option>
                                <s:option value="Jabatan Tenaga Kerja">Jabatan Tenaga Kerja</s:option>
                                <s:options-collection collection="${list.senaraiKodAgensi}" label="nama" value="kod"/>
                            </s:select></td></tr>
                        <tr><td><s:textarea name="ulasan1" rows="5" cols="150"/></td></tr><br>
                        <tr><td>4.2<s:select name="subtajuk2" style="width:400px" id="jabatan">
                                <s:option value="">Sila Pilih</s:option>
                                <s:option value="Jabatan Kebajikan Masyarakat">Jabatan Kebajikan Masyarakat</s:option>
                                <s:option value="Jabatan Kerja Raya">Jabatan Kerja Raya</s:option>
                                <s:option value="Jabatan Pengairan dan Saliran">Jabatan Pengairan dan Saliran</s:option>
                                <s:option value="Jabatan Perancang Bandar dan Desa">Jabatan Perancang Bandar dan Desa</s:option>
                                <s:option value="Jabatan Tenaga Kerja">Jabatan Tenaga Kerja</s:option>
                                <s:options-collection collection="${list.senaraiKodAgensi}" label="nama" value="kod"/>
                            </s:select></td></tr>
                        <tr><td><s:textarea name="ulasan2" rows="5" cols="150"/></td></tr><br>
                        <tr><td>4.3<s:select name="subtajuk3" style="width:400px" id="jabatan">
                                <s:option value="">Sila Pilih</s:option>
                                <s:option value="Jabatan Kebajikan Masyarakat">Jabatan Kebajikan Masyarakat</s:option>
                                <s:option value="Jabatan Kerja Raya">Jabatan Kerja Raya</s:option>
                                <s:option value="Jabatan Pengairan dan Saliran">Jabatan Pengairan dan Saliran</s:option>
                                <s:option value="Jabatan Perancang Bandar dan Desa">Jabatan Perancang Bandar dan Desa</s:option>
                                <s:option value="Jabatan Tenaga Kerja">Jabatan Tenaga Kerja</s:option>
                                <s:options-collection collection="${list.senaraiKodAgensi}" label="nama" value="kod"/>
                            </s:select></td></tr>
                        <tr><td><s:textarea name="ulasan3" rows="5" cols="150"/></td></tr><br>
                        <tr><td>4.4<s:select name="subtajuk4" style="width:400px" id="jabatan">
                                <s:option value="">Sila Pilih</s:option>
                                <s:option value="Jabatan Kebajikan Masyarakat">Jabatan Kebajikan Masyarakat</s:option>
                                <s:option value="Jabatan Kerja Raya">Jabatan Kerja Raya</s:option>
                                <s:option value="Jabatan Pengairan dan Saliran">Jabatan Pengairan dan Saliran</s:option>
                                <s:option value="Jabatan Perancang Bandar dan Desa">Jabatan Perancang Bandar dan Desa</s:option>
                                <s:option value="Jabatan Tenaga Kerja">Jabatan Tenaga Kerja</s:option>
                                <s:options-collection collection="${list.senaraiKodAgensi}" label="nama" value="kod"/>
                            </s:select></td></tr>
                        <tr><td><s:textarea name="ulasan4" rows="5" cols="150"/></td></tr><br>
                        <tr><td>4.5<s:select name="subtajuk5" style="width:400px" id="jabatan">
                                <s:option value="">Sila Pilih</s:option>
                                <s:option value="Jabatan Kebajikan Masyarakat">Jabatan Kebajikan Masyarakat</s:option>
                                <s:option value="Jabatan Kerja Raya">Jabatan Kerja Raya</s:option>
                                <s:option value="Jabatan Pengairan dan Saliran">Jabatan Pengairan dan Saliran</s:option>
                                <s:option value="Jabatan Perancang Bandar dan Desa">Jabatan Perancang Bandar dan Desa</s:option>
                                <s:option value="Jabatan Tenaga Kerja">Jabatan Tenaga Kerja</s:option>
                                <s:options-collection collection="${list.senaraiKodAgensi}" label="nama" value="kod"/>
                            </s:select></td></tr>
                        <tr><td><s:textarea name="ulasan5" rows="5" cols="150"/></td></tr><br>
                    </c:if>--%>
                    <c:if test="${!edit}">
                        <tr><td><b><font style="text-transform:capitalize">4.1 &nbsp; ${actionBean.subtajuk1}</font></b></td></tr>
                        <tr><td><font style="text-transform:capitalize">${actionBean.ulasan1}&nbsp;</font></td></tr><br>
                        <c:if test="${actionBean.subtajuk2 ne null}">
                            <tr><td><b><font style="text-transform:capitalize">4.2 &nbsp; ${actionBean.subtajuk2}</font></b></td></tr>
                            <tr><td><font style="text-transform:capitalize">${actionBean.ulasan2}&nbsp;</font></td></tr><br>
                        </c:if>
                        <c:if test="${actionBean.subtajuk3 ne null}">
                            <tr><td><b><font style="text-transform:capitalize">4.3 &nbsp; ${actionBean.subtajuk3}</font></b></td></tr>
                            <tr><td><font style="text-transform:capitalize">${actionBean.ulasan3}&nbsp;</font></td></tr><br>
                        </c:if>
                        <c:if test="${actionBean.subtajuk4 ne null}">
                            <tr><td><b><font style="text-transform:capitalize">4.4 &nbsp; ${actionBean.subtajuk4}</font></b></td></tr>
                            <tr><td><font style="text-transform:capitalize">${actionBean.ulasan4}&nbsp;</font></td></tr><br>
                        </c:if>
                        <c:if test="${actionBean.subtajuk5 ne null}">
                            <tr><td><b><font style="text-transform:capitalize">4.5 &nbsp; ${actionBean.subtajuk5}</font></b></td></tr>
                            <tr><td><font style="text-transform:capitalize">${actionBean.ulasan5}&nbsp;</font></td></tr><br>
                        </c:if>
                    </c:if>
                    <c:if test="${edit}">
                        <tr><td><b><font style="text-transform: uppercase">5. SYOR PENTADBIR TANAH ${actionBean.hakmilik.daerah.nama}</font></b></td></tr>
                        <tr><td><s:textarea name="syorPentadbir" rows="5" cols="150" disabled="true"/></td></tr><br>
                        <tr><td><b><font style="text-transform: uppercase">6. HURAIAN PENGARAH TANAH DAN GALIAN,NEGERI SEMBILAN &nbsp;</font></b></td></tr>
                        <tr><td><s:textarea name="huraianPtg" rows="5" cols="150" disabled="true"/></td></tr><br>
                        <tr><td><b><font style="text-transform: uppercase">7. SYOR PENGARAH TANAH DAN GALIAN,NEGERI SEMBILAN&nbsp;</font></b></td></tr>
                        <tr><td><s:textarea name="syorPtg" rows="5" cols="150" disabled="true"/></td></tr><br>
                   </c:if>
                    <c:if test="${!edit}">
                        <tr><td><b><font style="text-transform: uppercase">5. SYOR PENTADBIR TANAH ${actionBean.hakmilik.daerah.nama}</font></b></td></tr>
                        <tr><td><s:textarea name="syorPentadbir" rows="5" cols="150" disabled="false"/></td></tr>
                        <tr><td><b><font style="text-transform: uppercase">6. HURAIAN PENGARAH TANAH DAN GALIAN,NEGERI SEMBILAN&nbsp;</font></b></td></tr>
                        <tr><td><s:textarea name="huraianPtg" rows="5" cols="150" disabled="false"/></td></tr><br>
                        <tr><td><b><font style="text-transform: uppercase">7. SYOR PENGARAH TANAH DAN GALIAN,NEGERI SEMBILAN&nbsp;</font></b></td></tr>
                        <tr><td><s:textarea name="syorPtg" rows="5" cols="150" disabled="false"/></td></tr><br>
                    </c:if>
                    <c:if test="${edit}">
                    <tr><td><b>8. KEPUTUSAN</b></td></tr>
                    <tr><td width="100%">8.1 Dikemukan kertas Bil.<s:text name="kertasbil"/>/2009 permohonan pengambilan tanah daripada ${actionBean.pemohon.pihak.nama} seluas lebihkurang<fmt:formatNumber pattern="#,##0.0000" value="${actionBean.hakmilik.luas}"/>&nbsp;${actionBean.hakmilik.kodUnitLuas.nama} untuk ${actionBean.permohonan.sebab} di Lot${actionBean.hakmilik.noLot},di ${actionBean.hakmilik.bandarPekanMukim.nama},daerah ${actionBean.hakmilik.daerah.nama}.Dikemukakan untuk pertimbangan
                        Yang Amat Berhormat Menteri Besar Negeri Sembilan Darul Khusus.</td></tr><br>
                    </c:if>
                    <c:if test="${!edit}">
                    <tr><td><b>8. KEPUTUSAN</b></td></tr>
                    <tr><td width="100%">8.1 Dikemukan kertas Bil. ${actionBean.kertasbil}/2009 permohonan pengambilan tanah daripada ${actionBean.pemohon.pihak.nama} seluas lebihkurang<fmt:formatNumber pattern="#,##0.0000" value="${actionBean.hakmilik.luas}"/>&nbsp;${actionBean.hakmilik.kodUnitLuas.nama} untuk ${actionBean.permohonan.sebab} di Lot${actionBean.hakmilik.noLot},di ${actionBean.hakmilik.bandarPekanMukim.nama},daerah ${actionBean.hakmilik.daerah.nama}.Dikemukakan untuk pertimbangan
                        Yang Amat Berhormat Menteri Besar Negeri Sembilan Darul Khusus.</td></tr><br>
                    </c:if>
                </table>
            </div>
        </fieldset>
    </div>
    <c:if test="${edit}">
        <p align="center">
            <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
        </p>
    </c:if>
</s:form>
