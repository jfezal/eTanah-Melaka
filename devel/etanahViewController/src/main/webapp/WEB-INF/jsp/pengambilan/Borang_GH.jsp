<%-- 
    Document   : Borang_GH
    Created on : Sep 29, 2010, 4:39:35 PM
    Author     : Rajesh Reddy
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ page contentType="text/html;charset=windows-1252" import="java.util.*" import="java.text.SimpleDateFormat"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script type="text/javascript">

    function ajaxLink(link, update) {
        $.get(link, function(data) {
            $(update).html(data);
            $(update).show();
        });
        return false;
    }

</script>

<s:form name="notaForm" beanclass="etanah.view.stripes.pengambilan.BorangGHActionBean">
    <s:messages/>

    <div  id="hakmilik_details">
        <fieldset class="aras1">
            <legend >
                <b>Maklumat Hakmilik Permohonan</b>
            </legend><br/>
            <p align="center">
                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="5" cellpadding="0" cellspacing="0"
                               requestURI="/pengambilan/borangGH" id="line">
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column title="ID Hakmilik">
                        <s:link beanclass="etanah.view.stripes.pengambilan.BorangGHActionBean"
                                event="hakmilikDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                            <s:param name="idHakmilik" value="${line.hakmilik.idHakmilik}"/>${line.hakmilik.idHakmilik}
                        </s:link>
                    </display:column>
                    <display:column property="hakmilik.noLot" title="No Lot/No PT" />
                    <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                    <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                </display:table>
            </p>
            <br/><br/><br/>
        </fieldset>
    </div>
    <c:if test="${actionBean.hakmilik ne null}">
        <fieldset class="aras1">
            <legend >
                <b>Butiran Maklumat : ID Hakmilik : ${actionBean.hakmilik.idHakmilik}</b>
            </legend><br/>

            <p align="center">

            <table align="center" width="100%">
                <tr>
                    <td align="right" width="50%"><b>Perbicaraan Pengambilan No :&nbsp;&nbsp;</b> </td>
                    <td>
                        <c:if test="${actionBean.hakmilikPerbicaraan.idPerbicaraan ne null}">
                            ${actionBean.hakmilikPerbicaraan.idPerbicaraan}
                        </c:if>
                        <c:if test="${actionBean.hakmilikPerbicaraan.idPerbicaraan eq null}">
                            <c:out value="Tiada Maklunat" />
                        </c:if>
                    </td>
                </tr
                <tr>
                    <td align="right" width="50%"><b>Tarikh Perbicaraan :&nbsp;&nbsp;</b> </td>
                    <td>
                        <c:if test="${actionBean.hakmilikPerbicaraan.tarikhBicara ne null}">
                            <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.hakmilikPerbicaraan.tarikhBicara}" />
                        </c:if>
                        <c:if test="${actionBean.hakmilikPerbicaraan.tarikhBicara eq null}">
                            <c:out value="Tiada Maklunat" />
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td align="right" width="50%"><b>Maklumat Warta :&nbsp;&nbsp; </b></td>
                    <td><display:table class="tablecloth" name="${actionBean.permohonanRujukanLuarList}" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">
                    <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                    <display:column title="Jenis">
                        <c:if test="${line.namaPenyedia eq null}">Warta Seksyen 4</c:if>
                        <%--<c:if test="${line.namaPenyedia eq null || line.catatan eq 'WARTA PEMBETULAN'}">Warta Pembetulan Seksyen 4</c:if>--%>
                        <c:if test="${line.namaPenyedia ne null}">Warta Borang D</c:if>
                        <%--<c:if test="${line.namaPenyedia ne null || line.catatan eq 'WARTA PEMBETULAN'}">Warta Pembetulan Borang D</c:if>--%>
                    </display:column>
                    <display:column title="No. Warta" >${line.item}</display:column>
                    <display:column title="Tarikh Warta"><fmt:formatDate value="${line.tarikhLulus}" pattern="dd/MM/yyyy"/></display:column>
                    </display:table>
                    </td>
                </tr>
                <%--<tr>
                    <td align="right" width="50%"><b>Tarikh Warta :&nbsp;&nbsp; </b></td>
                    <td><c:if test="${actionBean.permohonanPengambilan.tarikhWarta ne null}">
                            <fmt:formatDate pattern="dd-MM-yyyy" value="${actionBean.permohonanPengambilan.tarikhWarta}" />
                        </c:if>
                        <c:if test="${actionBean.permohonanPengambilan.tarikhWarta eq null}">
                            <c:out value="Tiada Maklunat" />
                        </c:if>
                    </td>
                </tr>--%>
                <tr>
                    <td align="right" width="50%"><b>Id Hakmilik :&nbsp;&nbsp; </b></td>
                    <td>${actionBean.hakmilik.idHakmilik}   </td>
                </tr>
                <tr>
                    <td align="right" width="50%"><b>No Lot/No PT :&nbsp;&nbsp; </b></td>
                    <td>${actionBean.hakmilik.noLot}   </td>
                </tr>
                <tr>
                    <td align="right" width="50%"><b>Luas Diambil :&nbsp;&nbsp; </b></td>
                    <td>${actionBean.hakmilikPermohonan.luasTerlibat}   </td>
                </tr>

            </table>
            <br/><br/><br/>

            <div align="center">
                <%--actionBean.hakmilik.senaraiPihakBerkepentingan--%>
                <display:table class="tablecloth" name="${actionBean.senaraiPermohonanPihak}" cellpadding="0" cellspacing="0"
                               requestURI="/pengambilan/borangGH" id="line2">
                    <display:column title="No" sortable="true">${line2_rowNum}</display:column>
                    <display:column title="Orang yang berkepentingan" >
                        <%--<c:if test="${line2.syerPembilang ne null}">--%>
                        ${line2.pihak.nama}<br/>
                        No.KP/Co : ${line2.pihak.noPengenalan}
                        <%--</c:if>--%>
                    </display:column>
                    <display:column title="Jenis Kepentingan">
                        T/Tanah  ${line2.syerPembilang}/${line2.syerPenyebut}
                    </display:column>
                    <display:column  title="Pengumpukan Award (RM)" >
                        
                        <c:set var="JUM" value="0"/>
                        <c:set var="JUMx" value="0"/>
                        <c:forEach items="${actionBean.senaraiPerbicaraanKehadiran}" var="list" varStatus="loop">

                            <c:if test="${line2.pihak.idPihak == list.pihak.pihak.idPihak}">

                                <c:set var="T" value="0"/>
                                <c:set var="B" value="0"/>
                                <c:set var="L" value="0"/>

                                <c:forEach items="${list.senaraiPenilaian}" var="list1">
                                    <c:if test="${list1.jenis eq 'T'}">
                                        <c:set value="${T + list1.amaun}" var="T"/>
                                    </c:if>
                                    <c:if test="${list1.jenis eq 'B'}">
                                        <c:set value="${B + list1.amaun}" var="B"/>
                                    </c:if>
                                    <c:if test="${list1.jenis eq 'L'}">
                                        <c:set value="${L + list1.amaun}" var="L"/>
                                    </c:if>

                                </c:forEach>

                                <c:set value="${line2.syerPembilang}" var="a"/>
                                <c:set value="${line2.syerPenyebut}" var="b"/>
                                <c:set value="${(actionBean.hakmilikPerbicaraan.nilaiHutang)}" var="c"/>
                                <c:set value="${(actionBean.hakmilikPerbicaraan.nilaiHutang)*(actionBean.hakmilikPermohonan.luasTerlibat)}" var="d"/>
                                <c:set value="${c*(a/b)}" var="e"/>
                                <fmt:formatNumber pattern="###0.0" value="${d*(a/b)}" var="k"/>
                                <fmt:formatNumber pattern="###0.0" value="${JUM +k}" var="JUM"/>

                                <c:if test="${JUM == k}">
                                    <b>Tanah : RM<fmt:formatNumber pattern="#,##0.00" value="${e}"/></b><br/>
                                    <%--<b>Tanah : RM <c:out value="${T}"/><br/></b>--%>
                                    <b>Bangunan : RM <c:out value="${B}"/><br/></b>
                                    <b>Lain - lain : RM <c:out value="${L}"/></b>
                                </c:if>
                            </c:if>

                        </c:forEach>
                    </display:column>
                    <display:column  title="Jumlah Keseluruhan (RM)" >
                        <div align="center">
                            <c:set value="${line2.syerPembilang}" var="a"/>
                            <c:set value="${line2.syerPenyebut}" var="b"/>
                            <c:set value=" ${((actionBean.hakmilikPerbicaraan.nilaiHutang)/ 1000)*(actionBean.hakmilikPermohonan.luasTerlibat)}" var="c"/>
                            <c:set value="${a/b*c}" var="d"/>
                            <c:forEach items="${actionBean.senaraiPerbicaraanKehadiran}" var="list">
                                <c:if test="${line2.pihak.idPihak == list.pihak.pihak.idPihak}">
                                    <c:set var="T" value="0"/>
                                    <c:set var="B" value="0"/>
                                    <c:set var="L" value="0"/>
                                    <c:forEach items="${list.senaraiPenilaian}" var="list1">
                                        <c:if test="${list1.jenis eq 'T'}">
                                            <c:set value="${T + list1.amaun}" var="T"/>
                                        </c:if>
                                        <c:if test="${list1.jenis eq 'B'}">
                                            <c:set value="${B + list1.amaun}" var="B"/>
                                        </c:if>
                                        <c:if test="${list1.jenis eq 'L'}">
                                            <c:set value="${L + list1.amaun}" var="L"/>
                                        </c:if>
                                    </c:forEach>
                                    <%--<c:set value="${T+B+L}" var="e"/>--%>
                                    <c:set value="${line2.syerPembilang}" var="a"/>
                                    <c:set value="${line2.syerPenyebut}" var="b"/>
                                    <c:set value="${(actionBean.hakmilikPerbicaraan.nilaiHutang)}" var="c"/>
                                    <c:set value="${(actionBean.hakmilikPerbicaraan.nilaiHutang)*(actionBean.hakmilikPermohonan.luasTerlibat)}" var="d"/>
                                    <c:set value="${c*(a/b)}" var="e"/>
                                    <fmt:formatNumber pattern="###0.0" value="${d*(a/b)}" var="y"/>
                                    <fmt:formatNumber pattern="###0.0" value="${JUMx +y}" var="JUMx"/>
                                    <c:if test="${JUMx == y}">
                                    <b>RM <fmt:formatNumber pattern="#,##0.00" value="${(e)+B+L}"/><br/></b>
                                    </c:if>
                                    </c:if>
                                </c:forEach>
                        </div>
                    </display:column>
                    <%--<display:column title="Nilaian Tanah (RM)"><fmt:formatNumber pattern="#,##0.00" value="${line2.penilaiAmaun}"/>&nbsp;${line2.penilaiKodUOM.nama}</display:column>
                    <display:column title="Nilaian Bangunan (RM)"><fmt:formatNumber pattern="#,##0.00" value="${line2.penilaiBangunan}"/></display:column>
                    <display:column title="Nilaian Lain lain (RM)"><fmt:formatNumber pattern="#,##0.00" value="${line2.penilaiLain2}"/></display:column>
                    <display:column title="Elaun Kehadiran (RM)"><fmt:formatNumber pattern="#,##0.00" value="${line2.penilaiElaun}"/></display:column>--%>
                </display:table>


                <br>
            </div>
                <div align="center">
                Pemegang Gadaian/Kaveator
                <display:table class="tablecloth" name="${actionBean.senaraiNOTPM}" cellpadding="0" cellspacing="0" id="tbl1">
                    <display:column title="No" sortable="true" style="vertical-align:baseline">${tbl1_rowNum}</display:column>
                    <display:column title="Tuan Tanah">
                        ${tbl1.pihak.pihak.nama}
                        <%--<c:if test="${(tbl1.permohonanPihakTidakBerkepentingan ne null)}">${tbl1.pihak.pihak.nama}</c:if>--%>
                    </display:column>
                    <display:column  title="Jumlah Keseluruhan (RM)" >
                        <b>RM <fmt:formatNumber pattern="#,##0.00" value="${actionBean.itemNilaianPinjaman}"/></b>
                       <%-- <c:forEach items="${actionBean.penilaianPinjamanList}" var="list">
                                <c:if test="${tbl1.idKehadiran == list.kehadiran.idKehadiran}">
                                    <c:set var="P" value="0"/>
                                    <c:set var="B" value="0"/>
                                    <c:set var="L" value="0"/>
                                        <c:if test="${list1.jenis eq 'P'}">
                                            <c:set value="${P + list1.amaun}" var="P"/>
                                        </c:if>
                                    <b>RM <fmt:formatNumber pattern="#,##0.00" value="${P}"/><br/></b>

                                    </c:if>
                                </c:forEach>--%>
                    </display:column>
                </display:table>


                <br>
            </div>
                <br>
            <div align="center">
                Pihak Berkepentingan Tidak Berdaftar
                <display:table class="tablecloth" name="${actionBean.senaraiHadirPBT}" cellpadding="0" cellspacing="0" id="tbl1">
                    <display:column title="No" sortable="true" style="vertical-align:baseline">${tbl1_rowNum}</display:column>
                    <display:column title="Tuan Tanah">
                        <c:if test="${(tbl1.permohonanPihakTidakBerkepentingan ne null)}">${tbl1.nama}</c:if>
                    </display:column>
                    <display:column  title="Item Nilaian (RM)" >
                        <b>Bangunan : RM ${actionBean.itemNilaianBngn}</b><br/>
                        <b>Lain - lain : RM ${actionBean.itemNilaianLain}</b>
                    </display:column>
                    <display:column  title="Jumlah Keseluruhan (RM)" >
                        <c:set value="${actionBean.itemNilaianBngn}" var="a"/>
                        <c:set value="${actionBean.itemNilaianLain}" var="b"/>
                        <b>RM <fmt:formatNumber pattern="#,##0.00" value="${a+b}"/></b>
                    </display:column>
                </display:table>


                <br>
            </div>


            <%--
       
      <br/><br/>
       
       <br/><br/>

                <table >


                    <tr>
                        <td width="30%"><b>1) Tarikh permilikan tanah atau</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:radio name="tujuan" id="tujuan1" value="F" onclick="javascript:test();"/>&nbsp;<s:text name="hakmilikPerbicaraan.tarikhPemilikan" id="trkDate" class="datepicker" disabled="true" formatPattern="dd/MM/yyyy"/>
                                <s:hidden name="hakmilikPerbicaraan.tarikhPemilikan" /></b></td>
                    </tr>
                    <tr>
                        <td width="30%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tahun pemilikan tanah</b></td>
                        <td><b>:</b></td>
                        <td><b><s:radio name="tujuan" id="tujuan2" value="S" onclick="javascript:test();"/>&nbsp;
                                <s:text name="hakmilikPerbicaraan.tarikhPemilikan" id="trkYear" class="datepicker1" disabled="true" />

                                <s:text name="tarikhPemilikan" id="datepicker2" class="datepicker" formatPattern="yyyy" size="12" disabled="false" onclick=""/></b></td>
                    </tr>
                    <br/>
                    <tr>
                        <td width="30%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Cara Pemilikan tanah</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:text name="hakmilikPerbicaraan.caraPemilikan" size="20"/></b></td>
                    </tr>
                    <tr>
                        <td width="30%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Harga pambelian tanah(RM)</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:text name="hakmilikPerbicaraan.hargaPembelian" size="20"/></b><br/><br/></td>
                    </tr>
                    <tr>
                        <td width="30%"><b>2) Lokasi tanah</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:text name="hakmilikPerbicaraan.lokasi" size="20"/></b></td>
                    </tr>
                    <tr>
                        <td width="30%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Jauh dengan jalan dan pekan/bandar</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:text name="hakmilikPerbicaraan.jarakKeBandar" size="20" onkeyup="validateNumber(this,this.value);"/></b>
                        <s:select name="hakmilikPerbicaraan.kodUOM.kod"><font color="red">*</font>
                                    <s:option value="">--Sila Pilih--</s:option>
                                    <s:option value="M">Meter</s:option>
                                    <s:option value="KM">Kilometer</s:option>
                                </s:select><br/><br/>
                        </td>
                    </tr>
                    <tr>
                        <td width="30%"><b>3) Keadaan tanah</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:textarea name="hakmilikPerbicaraan.keadaanTanah" rows="3" cols="50" /></b><br/><br/></td>
                    </tr>
                    <tr>
                        <td width="30%"><b>4) Jenis Tanaman</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:textarea name="hakmilikPerbicaraan.tanaman" rows="3" cols="50" /></b></td>
                    </tr>
                    <tr>
                        <td width="30%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Bangunan</b></td>
                        <td><b>:</b></td>
                        <td><b><s:textarea name="hakmilikPerbicaraan.bangunan" rows="3" cols="50" /></b><br/><br/></td>
                    </tr>

                </table>
                <table >
                    <tr>
                        <td ><b>5) Permohonan tukar syarat/pecah sempadan.Jika ada,bila dan bagaimana kedudukan sekarang.</b></td>

                    </tr>
                </table>
                <table border="0" width="50%">
                    <tr>
                        <td ><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tarikh Permohonan Tukar syarat/pecah sempadan</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:text name="permohonanRujukanLuar.noSidang" size="20"/></b></td>
                    </tr>
                    <tr>
                        <td ><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Kedudukan terkini</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:text name="permohonanRujukanLuar.noSidang" size="20"/></b><br/><br/></td>
                    </tr>
                </table>   
                <table>
                    <tr><td><b>6) Gadaian/Pajakan/Perjanjian Jualbeli</b></td>
                    </tr>
                    <tr>
                        <td width=30%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Komen</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:textarea name="catatan" rows="3" cols="50" id="catatan"/></b><br/><br/></td>
                    </tr>
                    <tr>
                        <td width="30%"><b>7) Tuntutan jumlah pampasan (RM)</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:text name="hakmilikPerbicaraan.amaunDituntut" size="20"/></b></td>
                    </tr>
                    <tr>
                        <td width=30%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Alasan</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:textarea name="hakmilikPerbicaraan.alasanTuntutan" rows="3" cols="50" id="catatan"/></b></td>
                    </tr>
                    <tr>
                        <td width=30%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B. Keterangan Agensi Pemohon</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:textarea name="hakmilikPerbicaraan.pemohonUlasan" rows="3" cols="50" id="catatan"/></b></td>
                    </tr>
                    <tr>
                        <td width=30%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;C. Keterangan Pegawai Penilaian</b></td>
                    </tr>
                    <tr>
                        <td width=40%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Bilangan Surat</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:text name="penilaiNoRujukan" size="20"/></b></td>
                    </tr>
                    <tr>
                        <td width=40%"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tarikh Surat</b></td>
                        <td><b>:</b></td>
                        <td><b> <s:text name="penilaiTarikh" id="datepicker1" class="datepicker" size="12" /></b></td>
                    </tr>
                    </table>
                    <br>


               <div align="center">
                <display:table class="tablecloth" name="${actionBean.hakmilik.senaraiPihakBerkepentingan}" pagesize="5" cellpadding="0" cellspacing="0"
                               requestURI="/pengambilan/nota_siasatan" id="line2">
                <display:table class="tablecloth" name="hi" pagesize="5" style="width:100%" cellpadding="0" cellspacing="0"
                               requestURI="/pengambilan/nota_siasatan" id="line2">
                    <display:column title="No" sortable="true">${line2_rowNum}</display:column>
                    <display:column property="pihak.nama" title="Tuan Tanah" />
                    <display:column  title="Item Nilaian (RM)" >
                        <b>Tanah : RM ${actionBean.itemNilaianTanahList[line2_rowNum-1]}</b><br/>
                        <b>Kos Jurunilai : RM ${actionBean.itemNilaianBngnList[line2_rowNum-1]}</b><br/>
                        <b>Elaun Kehadiran : RM ${actionBean.itemNilaianLainList[line2_rowNum-1]}</b>
                    </display:column>
                    <display:column  title="Jumlah Keseluruhan (RM)" >

                        <c:set var="total" value="${actionBean.itemNilaianTanahList[line2_rowNum-1]+actionBean.itemNilaianBngnList[line2_rowNum-1]+actionBean.itemNilaianLainList[line2_rowNum-1]}"/>
                        <b>RM <c:out value="${total}"/></b>
                    </display:column>
                    <display:column title="Nilaian Tanah (RM)"><fmt:formatNumber pattern="#,##0.00" value="${line2.penilaiAmaun}"/>&nbsp;${line2.penilaiKodUOM.nama}</display:column>
                    <display:column title="Nilaian Bangunan (RM)"><fmt:formatNumber pattern="#,##0.00" value="${line2.penilaiBangunan}"/></display:column>
                    <display:column title="Nilaian Lain lain (RM)"><fmt:formatNumber pattern="#,##0.00" value="${line2.penilaiLain2}"/></display:column>
                    <display:column title="Elaun Kehadiran (RM)"><fmt:formatNumber pattern="#,##0.00" value="${line2.penilaiElaun}"/></display:column>
                </display:table>

            <br>

                        </div>

          
            --%></c:if>

        </fieldset>


</s:form>

