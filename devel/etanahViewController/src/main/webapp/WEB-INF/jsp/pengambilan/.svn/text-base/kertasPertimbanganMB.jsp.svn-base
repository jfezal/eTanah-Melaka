<%-- 
    Document   : kertasPertimbanganMB
    Created on : May 26, 2010, 4:13:01 PM
    Author     : MASSITA
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

        var table = document.getElementById(tableID);

        var rowCount = table.rows.length;
        var row = table.insertRow(rowCount);

         var cell2 = row.insertCell(0);
        cell2.innerHTML = "<b>"+"2.1." +(rowCount+1)+"</b>";

        var cell1 = row.insertCell(1);
        var element1 = document.createElement("textarea");
        element1.t = "latarBelakang";
         element1.rows = 5;
        element1.cols = 150;
        cell1.appendChild(element1);
    }

    function addRow1(tableID1) {

        var table = document.getElementById(tableID1);

        var rowCount = table.rows.length;
        var row = table.insertRow(rowCount);

        var cell2 = row.insertCell(0);
        cell2.innerHTML = "<b>"+"3.1." + (rowCount+1)+"</b>";

        var cell1 = row.insertCell(1);
        var element1 = document.createElement("textarea");
        element1.t = "perakuanPentadbirTanah";
        element1.rows = 5;
        element1.cols = 150;
        cell1.appendChild(element1);


        <%--cell2.innerHTML += "<s:textarea name='perakuanPentadbirTanah' rows='5' cols='150'/>";
        cell2.innerHTML +="<p><input type='textarea' name='perakuanPentadbirTanah' rows='5' cols='150'>";--%>
    }
</script>

<s:form beanclass="etanah.view.stripes.pengambilan.permohonan_pembatalanMMKNActionBean">
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
                        <tr><td align="center"><b>KERTAS PERTIMBANGAN PENARIKAN BALIK</b></td></tr><br>
                        <tr><td align="center"><b>KERTAS BIL :<font color="red">*</font> <s:text name="kertasbil"  size="12" />/2010</b></td></tr><br>
                        <tr><td align="center"><b>TARIKH :<font color="red">*</font> <s:text name="tarikhMesyuarat" id="datepicker" class="datepicker" size="12" /></b></td></tr><br>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td align="center"><b>KERTAS PERTIMBANGAN PENARIKAN BALIK</b></td></tr><br>
                        <tr><td align="center"><b>KERTAS BIL :${actionBean.kertasbil}/2010</b></td></tr><br>
                        <tr><td align="center"><b>TARIKH : <%--${actionBean.tarikhMesyuarat}--%></b></td></tr><br>
                    </c:if >
                    <c:if test="${edit}">
                        <tr><td><b><font style="text-transform: uppercase">PERMOHONAN PEMBATALAN PENGAMBILAN TANAH YANG TERDIRI DARIPADA 14 LOT SELUAS ${actionBean.hakmilik.noLot} HEKTAR DI ${actionBean.hakmilik.bandarPekanMukim.nama} DAN ${actionBean.hakmilik.bandarPekanMukim.nama} DAERAH ${actionBean.hakmilik.daerah.nama} UNTUK TUJUAN ${actionBean.permohonan.sebab}.</font></b></td></tr><br>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td><b><font style="text-transform: uppercase">PERMOHONAN PEMBATALAN PENGAMBILAN TANAH YANG TERDIRI DARIPADA 14 LOT SELUAS ${actionBean.hakmilik.noLot} HEKTAR DI ${actionBean.hakmilik.bandarPekanMukim.nama} DAN ${actionBean.hakmilik.bandarPekanMukim.nama} DAERAH ${actionBean.hakmilik.daerah.nama} UNTUK TUJUAN ${actionBean.permohonan.sebab}.</font></b></td></tr><br>                    </c:if>
                    <tr><td><b>1. TUJUAN</b></td></tr>
                    <c:if test="${edit}">
                        <tr><td><font style="text-transform:capitalize">1.1 &nbsp; ${actionBean.tujuan}</font></td></tr><br>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td><font style="text-transform: uppercase"><s:textarea rows="5" cols="150" name="tujuan"/></font></td></tr><br>
                    </c:if>

                    <tr><td><b>2. LATAR BELAKANG</b></td></tr>
                    <tr><td><b>2.1 Perihal Permohonan</b></td></tr>
                    <tr><td align="right"><s:button name="latarBelakang" value="Tambah" class="btn" onclick="addRow('dataTable')" />
                    <table id ="dataTable">
                        <tr><td><b>2.1.1</b</td>
                            <td><s:textarea name="latarBelakang" id="dataTable" rows="5" cols="150"/></td></tr>
                    </table>
                    <tr><td><b>3. PERIHAL TANAH </b></td></tr>
                    <tr><td>Butir-butir Tanah:-</td></tr>
                          <tr><td>
                                  <table border="0" width="96%">
                                    <tr>
                                        <td id="tdLabel"><font color="black">Jenis Hakmilik :</font></td>
                                        <td id="tdDisplay">${actionBean.hakmilik.bandarPekanMukim.nama} &nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td id="tdLabel"><font color="black">Lot :</font></td>
                                        <td id="tdDisplay">${actionBean.laporanTanah.kecerunanTanah.nama}&nbsp;</td>
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
                    <tr><td><b>3. PERAKUAN PENTADBIR TANAH ALOR GAJAH</b></td></tr>
                    <tr><td><b>3.1 Pentadbir Tanah Alor Gajah</b></td></tr>
                    <tr><td align="right"><s:button name="perakuanPentadbirTanah" value="Tambah" class="btn" onclick="addRow1('dataTable1')" />
                      <table id ="dataTable1">
                          <tr><td><b>3.1.1</b></td>
                              <td><s:textarea name="perakuanPentadbirTanah" id="dataTable1"rows="5" cols="150"/></td></tr>
                      </table>

                   <%-- <c:if test="${edit}">
                        <tr><td><b><font style="text-transform: uppercase">5. SYOR PENTADBIR TANAH ${actionBean.hakmilik.daerah.nama}</font></b></td></tr>
                        <tr><td><s:textarea name="syorPentadbir" rows="5" cols="150"/></td></tr><br>
                        <tr><td><b><font style="text-transform: uppercase">6. HURAIAN PENGARAH TANAH DAN GALIAN,NEGERI SEMBILAN &nbsp;</font></b></td></tr>
                        <tr><td><s:textarea name="huraianPtg" rows="5" cols="150" disabled="true"/></td></tr><br>
                        <tr><td><b><font style="text-transform: uppercase">7. SYOR PENGARAH TANAH DAN GALIAN,NEGERI SEMBILAN&nbsp;</font></b></td></tr>
                        <tr><td><s:textarea name="syorPtg" rows="5" cols="150" disabled="true"/></td></tr><br>
                   </c:if>
                    <c:if test="${!edit}">
                        <tr><td><b><font style="text-transform: uppercase">5. SYOR PENTADBIR TANAH ${actionBean.hakmilik.daerah.nama}</font></b></td></tr>
                        <tr><td><font style="text-transform:capitalize">${actionBean.syorPentadbir}&nbsp;</font></td></tr>
                        <tr><td><b><font style="text-transform: uppercase">6. HURAIAN PENGARAH TANAH DAN GALIAN,NEGERI SEMBILAN&nbsp;</font></b></td></tr>
                        <tr><td><font style="text-transform:capitalize">${actionBean.huraianPtg}&nbsp;</font></td></tr><br>
                        <tr><td><b><font style="text-transform: uppercase">7. SYOR PENGARAH TANAH DAN GALIAN,NEGERI SEMBILAN&nbsp;</font></b></td></tr>
                        <tr><td><font style="text-transform:capitalize">${actionBean.syorPtg}&nbsp;</font></td></tr><br>
                    </c:if>--%>
                    <c:if test="${!edit}">
                        <tr><td><b><font style="text-transform: uppercase">4. SYOR PENGARAH UNIT PERANCANG EKONOMI NEGERI <%--${actionBean.hakmilik.daerah.nama}--%></font></b></td></tr>
                        <tr><td><s:textarea name="syorPentadbir" rows="5" cols="150"/></td></tr><br>
                        <tr><td><b><font style="text-transform: uppercase">5. KEPUTUSAN JAWATANKUASA KHAS PENGAMBILAN TANAH NEGERI MELAKA&nbsp;</font></b></td></tr>
                        <tr><td><s:textarea name="huraianPtg" rows="5" cols="150" disabled="true"/></td></tr><br>
                        <tr><td><b><font style="text-transform: uppercase">6. PERAKAUAN PENGARAH TANAH DAN GALIAN, NEGERI MELAKA&nbsp;</font></b></td></tr>
                        <tr><td><s:textarea name="syorPtg" rows="5" cols="150" disabled="true"/></td></tr><br>
                   </c:if>
                    <c:if test="${edit}">
                        <tr><td><b><font style="text-transform: uppercase">4. SYOR PENGARAH UNIT PERANCANG EKONOMI NEGERI <%--${actionBean.hakmilik.daerah.nama}--%></font></b></td></tr>
                        <tr><td><font style="text-transform:capitalize"><%--${actionBean.syorPentadbir}--%>&nbsp;</font></td></tr>
                        <tr><td><b><font style="text-transform: uppercase">5. KEPUTUSAN JAWATANKUASA KHAS PENGAMBILAN TANAH NEGERI MELAKA&nbsp;</font></b></td></tr>
                        <tr><td><font style="text-transform:capitalize"><%--${actionBean.huraianPtg}--%>&nbsp;</font></td></tr><br>
                        <tr><td><b><font style="text-transform: uppercase">6. PERAKAUAN PENGARAH TANAH DAN GALIAN,NEGERI MELAKA&nbsp;</font></b></td></tr>
                        <tr><td><font style="text-transform:capitalize"><%--${actionBean.perakuanPtg}--%>&nbsp;</font></td></tr><br>
                    </c:if>
                   <%-- <c:if test="${edit}">
                    <tr><td><b>8. KEPUTUSAN</b></td></tr>
                    <tr><td width="100%">8.1 Dikemukan kertas Bil.<s:text name="kertasbil"/>/2009 permohonan pengambilan tanah daripada ${actionBean.pemohon.pihak.nama} seluas lebihkurang<fmt:formatNumber pattern="#,##0.0000" value="${actionBean.hakmilik.luas}"/>&nbsp;${actionBean.hakmilik.kodUnitLuas.nama} untuk ${actionBean.permohonan.sebab} di Lot${actionBean.hakmilik.noLot},di ${actionBean.hakmilik.bandarPekanMukim.nama},daerah ${actionBean.hakmilik.daerah.nama}.Dikemukakan untuk pertimbangan
                        Yang Amat Berhormat Menteri Besar Negeri Sembilan Darul Khusus.</td></tr><br>
                    </c:if>
                    <c:if test="${!edit}">
                    <tr><td><b>8. KEPUTUSAN</b></td></tr>
                    <tr><td width="100%">8.1 Dikemukan kertas Bil. ${actionBean.kertasbil}/2009 permohonan pengambilan tanah daripada ${actionBean.pemohon.pihak.nama} seluas lebihkurang<fmt:formatNumber pattern="#,##0.0000" value="${actionBean.hakmilik.luas}"/>&nbsp;${actionBean.hakmilik.kodUnitLuas.nama} untuk ${actionBean.permohonan.sebab} di Lot${actionBean.hakmilik.noLot},di ${actionBean.hakmilik.bandarPekanMukim.nama},daerah ${actionBean.hakmilik.daerah.nama}.Dikemukakan untuk pertimbangan
                        Yang Amat Berhormat Menteri Besar Negeri Sembilan Darul Khusus.</td></tr><br>
                    </c:if>--%>
                </table>
            </div>
        </fieldset>
    </div>
    <c:if test="${edit}">
        <p align="center">
            <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
        </p>
    </c:if>

            <p align="right">
                    <%--<s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>--%>
                    <s:button class="btn" onclick="doSubmit(this.form, this.name, 'page_div');" name="simpan" value="Simpan"/>
            </p>
</s:form>
