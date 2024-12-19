<%--
    Document   : Deraf_Perintah_ptsp
    Created on : June 11, 2013, 2:34:23 AM
    Author     : Hazirah
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
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
    $(document).ready( function(){
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        $(".datepicker1").datepicker({dateFormat: 'yy'});
    });

    function ajaxLink(link, update) {
        $.get(link, function(data) {
            $(update).html(data);
            $(update).show();
        });
        return false;
    }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.pengambilan.PerintahBersihPtspActionBean">
    <div  id="hakmilik_details">
        <s:messages/>
        <s:errors/>
        <%--<s:hidden name="show" value="${actionBean.show}"/>
        <s:hidden name="hide" value="${actionBean.hide}"/>--%>

        <fieldset class="aras1"><br/>
            <div align="center">
                <legend >
                    <b>Maklumat Hakmilik Permohonan</b>
                </legend><br/>
                <p align="center">
                    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="5" cellpadding="0" cellspacing="0"
                                   requestURI="/pengambilan/nota_siasatan" id="line">
                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                        <display:column title="ID Hakmilik">
                            <s:link beanclass="etanah.view.stripes.pengambilan.PerintahBersihPtspActionBean"
                                    event="hakmilikDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                                <s:param name="idHakmilik" value="${line.hakmilik.idHakmilik}"/>${line.hakmilik.idHakmilik}
                            </s:link>
                        </display:column>
                        <display:column property="hakmilik.noLot" title="No Lot/No PT" />
                        <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                        <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                        <%--<display:column title="Luas Sebenar"><fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                        <display:column title="Luas Diambil"><fmt:formatNumber pattern="#,##0.00" value="${line.luasTerlibat}"/>&nbsp;${line.kodUnitLuas.nama}</display:column>
                        <display:column property="hakmilik.syaratNyata.syarat" title="Syarat Nyata" />--%>
                    </display:table>
                </p>
                <br/><br/>

                <c:if test="${actionBean.hakmilik ne null}">
                    <s:errors/>
                    <c:if test="${showTuanTanah}">
                        <fieldset class="aras1">
                            <legend>Tuan Tanah</legend><br />
                            <div align="center">Sila klik nama tuan tanah.</div>
                            <div align="center">

                                <display:table class="tablecloth" name="${actionBean.hakmilik}" cellpadding="0" cellspacing="0" id="tbl1">
                                    <display:column title="No" sortable="true" style="vertical-align:baseline">${tbl1_rowNum}</display:column>
                                    <display:column property="idHakmilik" title="ID Hakmilik" />
                                    <display:column property="noLot" title="Nombor Lot/PT" />
                                    <display:column title="Daerah" property="daerah.nama" class="daerah" />
                                    <display:column property="bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                                    <display:column title="Orang Berkepentingan" style="vertical-align:baseline">
                                        <c:forEach items="${actionBean.hakmilik.senaraiPihakBerkepentingan}" var="senarai">

                                            <%--<c:if test="${actionBean.perbicaraanKehadiran.bantahElektrik eq '0'}">--%>
                                            <s:link beanclass="etanah.view.stripes.pengambilan.PerintahBersihPtspActionBean"
                                                    event="pihakDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                                                <s:param name="idPihak" value="${senarai.pihak.idPihak}"/>${senarai.pihak.nama}
                                            </s:link>
                                            <br/>
                                            No KP ${senarai.pihak.noPengenalan}
                                            <%--</c:if>--%>

                                            <%--<c:if test="${actionBean.perbicaraanKehadiran.bantahElektrik eq '1'}">

                                    <s:param name="idPihak" value="${senarai.pihak.idPihak}"/>${senarai.pihak.nama}

                                    <br/>
                                    No KP ${senarai.pihak.noPengenalan}

                                </c:if>--%>

                                            <br/>
                                        </c:forEach>
                                    </display:column>
                                    <display:column title="Luas Diambil" style="vertical-align:baseline"><fmt:formatNumber pattern="#,##0.0000" value="${actionBean.hakmilikPermohonan.luasTerlibat}"/>&nbsp;${actionBean.hakmilik.kodUnitLuas.nama}</display:column>
                                </display:table>
                            </div>
                            <br /><br />
                        </fieldset><br />
                    </c:if>
                </c:if>

            </div>
        </fieldset>
        <br/><br/>
        <c:if test="${actionBean.permohonanPihak ne null}">
            <s:hidden name="idPihak" />
            <s:hidden name="idHakmilik"/>
            <s:hidden name="display"/>
            <fieldset class="aras1">
                <div class="content" align="center">
                    <table border="0" width="80%">
                        <tr>
                            <td align="center"><font color="#000000"><b>DALAM MAHKAMAH TINGGI MALAYA DI MELAKA</b></font></td>
                        </tr>
                        <tr>
                            <td align="center"><font color="#000000"><b>SAMAN PEMULA BIL : ${actionBean.permohonanMahkamah.samanPemulaBil}</b></font></td>
                        </tr>
                        <tr>
                            <td align="center"><font color="#000000"><b>___________________________________________________________________________________________________________________________________</b></font></td>
                        </tr>

                        <table align="right" width="30%">
                            <tr>
                                <td><font color="#000000">Dalam perkara seksyen 29 Akta Pengambilan Tanah, 1960</font></td>
                            </tr>
                            <tr>
                                <td align="center"><font color="#000000"><u>DAN</u></font></td>
                            </tr>
                            <tr>
                                <td><font color="#000000">Dalam perkara mengenai tanah yang terkandung dalam Hakmilik ${actionBean.hakmilik.kodHakmilik.kod} ${actionBean.hakmilik.noHakmilik}.</font></td>
                            </tr>
                        </table>
                        <br /><br><br><br><br><br><br>
                        <table align="left" width="40%">
                            <tr>
                                <td><font color="#000000">Pentadbir Tanah Daerah</font></td>
                            </tr>
                            <tr>
                                <td><font color="#000000">${actionBean.hakmilik.daerah.nama}</font></td>
                            </tr>
                        </table>
                        <table align="right" width="30%">
                            <tr>
                                <td><font color="#000000">Pemohon</font></td>
                            </tr>
                        </table>
                        <br><br><br>
                        <table align="left" width="40%">
                            <tr>
                                <td><font color="#000000"><b>DIHADAPAN TUAN/PUAN ${actionBean.permohonanMahkamah.namaPenolongKananPendaftar}</b></font></td>
                            </tr>
                            <tr>
                                <td><font color="#000000"><b>PENOLONG KANAN PENDAFTAR</b></font></td>
                            </tr>
                            <tr>
                                <td><font color="#000000"><b>MAHKAMAH TINGGI</b></font></td>
                            </tr>
                            <tr>
                                <td><font color="#000000"><b>${actionBean.hakmilik.daerah.nama}</b></font></td>
                            </tr>
                        </table>
                        <br><br><br><br><br><br><br>
                        <table align="left" width="40%">
                            <tr>
                                <td><font color="#000000">PADA</font></td>
                                <td>:</td>
                                <td><fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.permohonanMahkamah.tarikhTerimaPerintah}"/></td>
                            </tr>
                        </table>
                        <br>
                        <table align="right" width="30%">
                            <tr>
                                <td><font color="#000000"><u>DALAM KAMAR</u></font></td>
                            </tr>
                        </table>
                        <br>
                        <br>

                        <tr>
                            <td><font color="#000000"><u><b>PERINTAH BERSIH</b></u></font></td>
                        </tr>
                        <br><br>
                        <table align="center" width="50%">
                            <tr>
                                <td><font color="#000000"><u>ATAS PERMOHONAN</u></font><font color="#000000"> Ketua Penolong Pegawai Tanah Daerah ${actionBean.hakmilik.daerah.nama} dan </font>
                                    <font color="#000000"><u>SETELAH MEMBACA</u></font><font color="#000000"> Saman Pemula bertarikh <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.permohonanMahkamah.tarikhSaman}"/> dan Affidavit YM. Raja Baderul Shah Bin Raja Salim yang diikrarkan pada <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.permohonanMahkamah.tarikhIkrar}"/>.</font></td></tr>
                            <tr><td><font color="#000000"><u>MAKA, ADALAH DIPERINTAHKAN</u></font><font color="#000000"> bahawa pemohon dengan ini dibenarkan
                                        <font color="#000000"><b>mendepositkan</b></font></font></td></tr>
                                        <c:set value="${actionBean.permohonanPihak.syerPembilang}" var="a"/>
                                        <c:set value="${actionBean.permohonanPihak.syerPenyebut}" var="b"/>
                                        <c:set value="${actionBean.hakmilikPermohonan.luasTerlibat}" var="c"/>
                                        <c:set value="${(actionBean.hakmilikPerbicaraan.keputusanNilai)*(actionBean.hakmilikPermohonan.luasTerlibat)}" var="d"/>
                                        <%--<c:set value="${a/b*c}" var="e"/>
                                        <c:set value="${e*d}" var="f"/>--%>
                                        <c:set value="${d*(a/b)}" var="e"/>
                                        <c:forEach items="${actionBean.senaraiPerbicaraanKehadiran}" var="list">
                                            <c:if test="${actionBean.permohonanPihak.pihak.idPihak == list.pihak.pihak.idPihak}">
                                                <c:set var="B" value="0"/>
                                                <c:forEach items="${list.senaraiPenilaian}" var="list1">
                                                    <c:set value="${B + list1.amaun}" var="B"/>
                                                </c:forEach>
                                                <c:set value="${B}" var="g"/>
                                            </c:if>
                                        </c:forEach>
                            <tr><td>di Mahkamah ini sejumlah wang sebanyak RM <fmt:formatNumber pattern="#,##0.00" value="${e}"/> </td></tr>
                            <%--<fmt:formatNumber value="${actionBean.am}" pattern="#,##0.00"/>--%>

                            <%--<c:forEach items="${actionBean.hakmilik.senaraiPihakBerkepentingan}" var="list">
                                <c:if test="${line.pihak.pihak.idPihak == list.pihak.idPihak}">
                                    <c:set value="${actionBean.senaraiPerbicaraanKehadiran.pihak.syerPembilang}" var="a"/>
                                    <c:set value="${actionBean.senaraiPerbicaraanKehadiran.pihak.syerPenyebut}" var="b"/>
                                    <c:set value="${(actionBean.hakmilikPermohonan.luasTerlibat)}" var="c"/>
                                    <c:set value="${(actionBean.hakmilikPerbicaraan.keputusanNilai)*(actionBean.hakmilikPermohonan.luasTerlibat)}" var="d"/>
                                    <c:set value="${d*(a/b)}" var="e"/>
                                    <fmt:formatNumber pattern="#,##0.00" value="${e}"/>
                                </c:if>
                            </c:forEach>--%>
                            <tr><td>iaitu bayaran pampasan pengambilan tanah yang ditawarkan kepada tuan punya tanah ${actionBean.hakmilik.lot.nama} ${actionBean.hakmilik.noLot} hakmilik
                                    ${actionBean.hakmilik.kodHakmilik.kod} ${actionBean.hakmilik.noHakmilik} di ${actionBean.hakmilik.bandarPekanMukim.nama}, Daerah ${actionBean.hakmilik.daerah.nama}. </td>
                            </tr>
                        </table>
                        <br><br><br><br><br>
                        <table align="left">
                            <tr>
                                <td><font color="#000000">Bertarikh pada</font></td>
                                <td>:</td>
                                <%--<s:text class="datepicker" id="tarikhTerimaPerintah" name="tarikhTerimaPerintah"></s:text>--%>
                                <td><fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.permohonanMahkamah.tarikhTerimaPerintah}"/></td>
                            </tr>
                        </table>
                        <br><br><br><br><br>
                        <table align="right" width="30%">
                            <tr>
                                <td><font color="#000000"><b>Penolong Kanan Pendaftar</b></font></td>
                            </tr>
                            <tr>
                                <td><font color="#000000"><b>Mahkamah Tinggi</b></font></td>
                            </tr>
                            <tr>
                                <td><font color="#000000"><b>${actionBean.hakmilik.daerah.nama}</b></font></td>
                            </tr>
                        </table>
                        <br><br><br><br>
                        <table align="left">
                            <tr>
                                <td><font color="#000000">Deraf perintah ini difailkan oleh Pentadbir Tanah Daerah ${actionBean.hakmilik.daerah.nama}</font></td>
                            </tr>
                        </table>
                    </table>
                    <br><br><br>
                    <p align="center">

                        <%--<s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>--%>
                    </p>
                </div>
            </fieldset><br/>

            <%--<div align="center">
                <s:hidden name="idPihak" value="${actionBean.idPihak}"/>
                <s:hidden name="idHakmilik" value="${actionBean.idHakmilik}"/>
                <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
            </div>--%>
            <br />
        </c:if>
    </div>
</s:form>



