<%--
    Document   : draf_mmkn_ptd/ptg
    Created on : Mar 5, 2011, 11:32:21 AM
    Author     : Murali
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<style type="text/css">
    #tdLabel {
        color:#003194;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
    }

    #tdDisplay {
        color:#003194;
        display:block;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        margin-left:30px;
        margin-right:0.5em;
        width:14em;
    }
    #test {
        text-transform: capitalize;
}
</style>
<script type="text/javascript">

    function addRow(tableid) {

        // alert(tableID);
        // document.getElementById("dataTable1").value = 1;
        var table = document.getElementById(tableid);
        var rowCount = table.rows.length;
        var row = table.insertRow(rowCount);
        var tableNo = tableid.substring(9);

        var cell0 = row.insertCell(0);
        cell0.innerHTML = "2.1"+ "." +(rowCount+3);

        var cell1 = row.insertCell(1);
        var element1 = document.createElement("textarea");
        element1.t = "kandungan1"+(rowCount+3);
        element1.rows = 6;
        element1.cols = 150;
        element1.name ="kandungan1"+(rowCount+3);
        element1.id ="kandungan1"+(rowCount+3);
        cell1.appendChild(element1);

        document.getElementById("rowCount3").value=rowCount+3;
    }

    function addRow1(tableid) {

        // alert(tableid);
        // document.getElementById("dataTable1").value = 1;
        var table = document.getElementById(tableid);
        var rowCount = table.rows.length;
        var row = table.insertRow(rowCount);
        var tableNo = tableid.substring(9);

        var cell0 = row.insertCell(0);
        cell0.innerHTML = "6."+(rowCount+1);

        var cell1 = row.insertCell(1);
        var element1 = document.createElement("textarea");
        element1.t = "kandungan2"+(rowCount+1);
        element1.rows = 6;
        element1.cols = 150;
        element1.name ="kandungan2"+(rowCount+1);
        element1.id ="kandungan2"+(rowCount+1);
        cell1.appendChild(element1);

        document.getElementById("rowCount4").value=rowCount+1;
    }

    function addRow2(tableid) {

        // alert(tableID);
        // document.getElementById("dataTable1").value = 1;
        var table = document.getElementById(tableid);
        var rowCount = table.rows.length;
        var row = table.insertRow(rowCount);
        var tableNo = tableid.substring(9);

        var cell0 = row.insertCell(0);
        cell0.innerHTML = "7."+(rowCount+1);

        var cell1 = row.insertCell(1);
        var element1 = document.createElement("textarea");
        element1.t = "kandungan3"+(rowCount+1);
        element1.rows = 6;
        element1.cols = 150;
        element1.name ="kandungan3"+(rowCount+1);
        element1.id ="kandungan3"+(rowCount+1);
        cell1.appendChild(element1);

        document.getElementById("rowCount5").value=rowCount+1;
    }

    function addRow3(tableid) {

        // alert(tableID);
        // document.getElementById("dataTable1").value = 1;
        var table = document.getElementById(tableid);
        var rowCount = table.rows.length;
        var row = table.insertRow(rowCount);
        var tableNo = tableid.substring(9);

        var cell0 = row.insertCell(0);
        cell0.innerHTML = "5.1"+ "." +(rowCount+2);

        var cell1 = row.insertCell(1);
        var element1 = document.createElement("textarea");
        element1.t = "kandungan5"+(rowCount+2);
        element1.rows = 6;
        element1.cols = 148;
        element1.name ="kandungan5"+(rowCount+2);
        element1.id ="kandungan5"+(rowCount+2);
        cell1.appendChild(element1);

        document.getElementById("rowCount6").value=rowCount+2;
    }


    ///aded

    function cariPopup(){
        // alert("search:"+index);
        var url = '${pageContext.request.contextPath}/pelupusan/draf_mmkn?search';
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=900,height=700 scrollbars=yes");
    }

    function cariPopup2(){
        // alert("search:"+index);
        var url = '${pageContext.request.contextPath}/pelupusan/draf_mmkn?showFormCariKodSekatan';
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=900,height=700 scrollbars=yes");
    }

    function test(){
        var a = "hi" ;

    }

</script>

<s:form beanclass="etanah.view.stripes.pelupusan.DrafMMKNActionBean">
    <s:messages/>
    <s:errors/>
    <s:hidden name="kertasK.idKertas"/>
    <s:hidden name="edit" id="edit"/>

    <div class="subtitle" style="text-transform: capitalize">
        <fieldset class="aras1">
            <legend> </legend>
            <p align="center">
                <b> (MAJLIS MESYUARAT KERAJAAN NEGERI)</b>
            </p>
            <div class="content" align="center">
                <table border="0" width="80%" cellspacing="10%">

                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT' }">

                        <tr><td id="tdLabel" ><b><font style="text-transform: capitalize">
                                        <tr><td>
                                                <c:if test="${!actionBean.edit}">
                                                    <table width="100%">
                                                        <tr><td valign="top">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                                            <td> <s:textarea rows="6" cols="150" name="tajuk" class="normal_text" />
                                                            </td></tr>
                                                    </table>
                                                </c:if>

                                                <c:if test="${actionBean.edit}">
                                                    <table width="100%">
                                                        <tr><td valign="top">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                                            <td>  ${actionBean.tajuk}</td></tr>
                                                    </table>
                                                </c:if>
                                            </td></tr></font></b></td></tr></c:if>

                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PLPS' }">

                        <tr><td id="tdLabel" ><b><font style="text-transform: capitalize">
                                        <tr><td>
                                                <c:if test="${!actionBean.edit}">
                                                    <table width="100%">
                                                        <tr><td valign="top">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                                            <td> <s:textarea rows="6" cols="150" name="tajukPlps" class="normal_text" />
                                                            </td></tr>
                                                    </table>
                                                </c:if>

                                                <c:if test="${actionBean.edit}">
                                                    <table width="100%">
                                                        <tr><td valign="top">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                                            <td>  ${actionBean.tajukPlps}</td></tr>
                                                    </table>
                                                </c:if>
                                            </td></tr></font></b></td></tr></c:if>


                        <tr><td>&nbsp;</td></tr>
                        <tr ><td id="tdLabel" width="100%"><b>1. TUJUAN</b></td></tr>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT' }">
                        <c:if test="${!actionBean.edit}">
                            <tr><td>
                                    <table width="100%">
                                        <tr><td valign="top">1.1 </td>
                                            <td> <s:textarea rows="6" cols="150" name="tujuan" class="normal_text"/></td></tr>
                                    </table>

                                </td></tr>
                            </c:if>
                            <c:if test="${actionBean.edit}">
                            <tr><td>
                                    <table width="100%">
                                        <tr><td valign="top">1.1 </td>
                                            <td>&nbsp;${actionBean.tujuan}</td></tr>
                                    </table>
                                </td></tr>
                            </c:if></c:if>


                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PLPS' }">
                        <c:if test="${!actionBean.edit}">
                            <tr><td>
                                    <table width="100%">
                                        <tr><td valign="top">1.1 </td>
                                            <td> <s:textarea rows="6" cols="150" name="tujuanPlps" class="normal_text"/></td></tr>
                                    </table>

                                </td></tr>
                            </c:if>
                            <c:if test="${actionBean.edit}">
                            <tr><td>
                                    <table width="100%">
                                        <tr><td valign="top">1.1 </td>
                                            <td>&nbsp;${actionBean.tujuanPlps}</td></tr>
                                    </table>
                                </td></tr>
                            </c:if></c:if>

                    <%--<tr>
                        <td>  1.2 Tanah yang dipohon adalah seperti ditunjuk dengan garis sempadan warna merah dalam pelan yang berkembar.</td>
                        </tr>--%>


                    <tr><td>&nbsp;</td></tr>
                    <tr><td>
                            <table border="0" width="100%" id="tblhuraian" cellspacing="0">
                                <tr><td id="tdLabel"><b><font style="text-transform: capitalize">2. LATAR BELAKANG</font></b></td></tr>
                                <tr><td>&nbsp;</td></tr>

                                <tr><td id="tdLabel"><b>2.1 Perihal Permohonan</b></td></tr>
                                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT' }">
                                    <tr><td>&nbsp;</td></tr>
                                    <c:if test="${!actionBean.edit}">
                                        <tr><td><table width="100%">
                                                    <tr><td valign="top">2.1.1 </td>
                                                        <td> <s:textarea rows="6" cols="150" name="perihalpermohonan" class="normal_text"/></td></tr>
                                                </table></td></tr>
                                            </c:if>

                                    <c:if test="${actionBean.edit}">
                                        <tr><td><table width="100%">
                                                    <tr><td valign="top">2.1.1 </td>
                                                        <td>&nbsp;${actionBean.perihalpermohonan}</td></tr>
                                                </table></td></tr>
                                            </c:if></c:if>

                                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PLPS' }">
                                    <tr><td>&nbsp;</td></tr>
                                    <c:if test="${!actionBean.edit}">
                                        <tr><td><table width="100%">
                                                    <tr><td valign="top">2.1.1 </td>
                                                        <td> <s:textarea rows="6" cols="150" name="perihalpermohonanPlps" class="normal_text"/></td></tr>
                                                </table></td></tr>
                                            </c:if>

                                    <c:if test="${actionBean.edit}">
                                        <tr><td><table width="100%">
                                                    <tr><td valign="top">2.1.1 </td>
                                                        <td>&nbsp;${actionBean.perihalpermohonanPlps}</td></tr>
                                                </table></td></tr>
                                            </c:if></c:if>


                                <c:if test="${!actionBean.edit}">
                                    <tr><td><table width="100%">
                                                <tr><td valign="top">2.1.2 </td>
                                                    <td> <s:textarea rows="6" cols="150" name="perihalpermohonan1" class="normal_text" /></td></tr>
                                            </table></td></tr></table>
                                        </c:if>

                            <c:if test="${actionBean.edit}">
                        <tr><td><table width="100%">
                                    <tr><td valign="top">2.1.2</td>
                                        <td>&nbsp;${actionBean.perihalpermohonan1}</td></tr>
                                </table></td></tr>
                            </c:if>

                    <tr><td>
                            <c:if test="${!actionBean.edit}">
                                <table id ="dataTable1" border="0">
                                    <c:set var="i" value="3" />
                                    <c:set var="k" value="3" />
                                    <c:forEach items="${actionBean.senaraiLaporanKandungan1}" var="line" begin="2">
                                        <tr><td style="display:none" valign="top">${line.idKandungan}</td><td valign="top"><c:out value="2.1.${k}"/></td>
                                            <td valign="top"><font style="text-transform: capitalize"><s:textarea name="kandungan1${i}" id="kandungan1${i}"  rows="6" cols="150" >${line.kandungan}</s:textarea></font>
                                            </td></tr>
                                            <c:set var="i" value="${i+1}" />
                                            <c:set var="k" value="${k+1}" />
                                        </c:forEach>
                                </table></td></tr>
                        <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <s:button class="btn" value="Tambah" name="add" onclick="addRow('dataTable1',3)"/>&nbsp;</td>
                        </tr>
                        <tr><td><s:hidden name="rowCount3" value="${fn:length(actionBean.senaraiLaporanKandungan1)}" id="rowCount3"/>&nbsp;</td></tr>
                    </c:if>

                    <tr><td>
                            <c:if test="${actionBean.edit}">
                                <table id ="dataTable1" border="0" cellspacing="12">
                                    <c:set var="i" value="3" />
                                    <c:set var="k" value="3" />
                                    <c:forEach items="${actionBean.senaraiLaporanKandungan1}" var="line" begin="2">
                                        <tr><td style="display:none" valign="top">${line.idKandungan}</td><td valign="top"><c:out value="2.1.${k}"/> </td>
                                            <td valign="top"><font style="text-transform: capitalize">${line.kandungan}</font>
                                            </td></tr>
                                            <c:set var="i" value="${i+1}" />
                                            <c:set var="k" value="${k+1}" />
                                        </c:forEach>
                                </table>
                            </c:if>
                        </td>
                    </tr>

                    <tr><td id="tdLabel"><b>2.2 Perihal Tanah</b></td></tr>
                    <tr><td><table width="100%" border="0">
                                <c:if test="${!actionBean.edit}">
                                    <tr><td valign="top">2.2.1 </td>
                                        <td> <s:textarea rows="6" cols="150" name="perihaltanah1" class="normal_text" /></td></tr>
                                    </c:if>

                                <c:if test="${actionBean.edit}">
                                    <tr><td>2.2.1</td>
                                        <td>${actionBean.perihaltanah1}</td></tr>
                                    </c:if>
                            </table></td></tr>

                    <tr><td><table width="100%">
                                <c:if test="${!actionBean.edit}">
                                    <tr><td valign="top">2.2.2 </td>
                                        <td> <s:textarea rows="6" cols="150" name="perihaltanah" class="normal_text" /></td></tr>
                                    </c:if>

                                <c:if test="${actionBean.edit}">
                                    <tr><td valign="top">2.2.2 </td>
                                        <td>&nbsp;${actionBean.perihaltanah}</td></tr>
                                    </c:if>
                            </table></td></tr>

                    <tr><td><table width="100%">
                                <tr><td valign="top">2.2.3
                                        Tanah-tanah yang bersempadan dengan tanah yang dipohon adalah seperti berikut:- </td></tr>
                            </table></td></tr>

                    <tr><td colspan="2">
                            <div class="content" align="center">
                                <table class="tablecloth">
                                    <tr><th>Kedudukan</th><th>Tanah Kerajaan/ Rizab/ Lot/ PT</th><th>Apa yang Terdapat Di atas Tanah</th></tr>
                                    <tr><th>Utara</th><td>${actionBean.laporanTanah.sempadanUtaraNoLot}</td><td>${actionBean.laporanTanah.sempadanUtaraKegunaan}</td></tr>
                                    <tr><th>Selatan</th><td>${actionBean.laporanTanah.sempadanSelatanNoLot}</td><td>${actionBean.laporanTanah.sempadanSelatanKegunaan}</td></tr>
                                    <tr><th>Timur</th><td>${actionBean.laporanTanah.sempadanTimurNoLot}</td><td>${actionBean.laporanTanah.sempadanTimurKegunaan}</td></tr>
                                    <tr><th>Barat</th><td>${actionBean.laporanTanah.sempadanBaratNoLot}</td><td>${actionBean.laporanTanah.sempadanBaratKegunaan}</td></tr>

                                </table>

                            </div>
                        </td></tr>

                    <tr><td id="tdLabel"><b>2.3 Perihal Pemohon</b></td></tr>
                    <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L' || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'}">
                        <tr><td><table width="100%">
                                    <c:if test="${!actionBean.edit}">
                                        <tr><td valign="top">2.3.1 </td>
                                            <td> <s:textarea rows="6" cols="150" name="perihalpemohon" class="normal_text" /></td></tr>
                                        </c:if>

                                    <c:if test="${actionBean.edit}">
                                        <tr><td valign="top">2.3.1 </td>
                                            <td>${actionBean.perihalpemohon}</td></tr>
                                        </c:if>
                                </table></td></tr></c:if>

                    <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'S'}">
                        <tr><td><table width="100%">
                                    <c:if test="${!actionBean.edit}">
                                        <tr><td valign="top">2.3.1 </td>
                                            <td> <s:textarea rows="6" cols="150" name="perihalpemohon_s" class="normal_text" /></td></tr>
                                        </c:if>

                                    <c:if test="${actionBean.edit}">
                                        <tr><td valign="top">2.3.1 </td>
                                            <td>${actionBean.perihalpemohon_s}</td></tr>
                                        </c:if>
                                </table></td></tr></c:if>

                        <tr><td><table width="100%">
                                <c:if test="${!actionBean.edit}">
                                    <tr><td valign="top">2.3.2 </td>
                                        <td> <s:textarea rows="6" cols="150" name="perihalpemohon1" class="normal_text" /></td></tr>
                                    </c:if>

                                <c:if test="${actionBean.edit}">
                                    <tr><td valign="top">2.3.2 </td>
                                        <td>${actionBean.perihalpemohon1}</td></tr>
                                    </c:if>
                            </table></td></tr>

                    <c:if test="${actionBean.pemohon.statusKahwin eq 'Berkahwin'}">
                        <c:if test="${actionBean.pemohonHubungan.kaitan eq 'ISTERI'}">
                            <tr><td><table width="100%">
                                        <c:if test="${!actionBean.edit}">
                                            <tr><td valign="top">2.3.3 </td>
                                                <td> <s:textarea rows="6" cols="150" name="perihalpemohon2" class="normal_text" /></td></tr>
                                            </c:if>

                                        <c:if test="${actionBean.edit}">
                                            <tr><td valign="top">2.3.3 </td>
                                                <td>${actionBean.perihalpemohon2}</td></tr>
                                            </c:if>
                                    </table></td></tr>
                                </c:if>

                        <c:if test="${actionBean.pemohonHubungan.kaitan eq 'SUAMI'}">
                            <tr><td><table width="100%">
                                        <c:if test="${!actionBean.edit}">
                                            <tr><td valign="top">2.3.3 </td>
                                                <td> <s:textarea rows="6" cols="150" name="perihalpemohon2suami" class="normal_text" /></td></tr>
                                            </c:if>

                                        <c:if test="${actionBean.edit}">
                                            <tr><td valign="top">2.3.3 </td>
                                                <td>${actionBean.perihalpemohon2suami}</td></tr>
                                            </c:if>
                                    </table></td></tr>
                                </c:if>
                            </c:if>

                    <tr><td>&nbsp;</td></tr>
                    <tr><td id="tdLabel"><b><font style="text-transform: capitalize">3. Ulasan Jabatan Teknikal</font></b></td></tr>
                    <tr><td>
                            <table id ="dataTable2">
                                <c:set var="i" value="1" />
                                <c:forEach items="${actionBean.senaraiLaporanKandungan2}" var="line">
                                    <tr>
                                        <c:if test="${!actionBean.edit}">
                                            <td valign="top">&nbsp;&nbsp;&nbsp; <c:out value="3.${i}"/></td>
                                            <td><font style="text-transform: capitalize" ><s:textarea name="ulasan${i}" id="ulasan${i}"   class="normal_text" rows="6" cols="150" >${line.kandungan}</s:textarea></font></td>
                                        </c:if>

                                        <c:if test="${actionBean.edit}">
                                            <td valign="top">&nbsp;&nbsp;&nbsp; <c:out value="3.${i}"/></td>
                                            <td><font style="text-transform: capitalize">&nbsp;${line.kandungan}</font></td>
                                        </c:if>

                                    </tr>
                                    <c:set var="i" value="${i+1}" />
                                </c:forEach>
                            </table></td></tr>
                    <tr><td><s:hidden name="rowCount1" value="${fn:length(actionBean.senaraiLaporanKandungan2)}" id="rowCount1"/>&nbsp;</td></tr>

                    <tr><td id="tdLabel"><b><font style="text-transform: capitalize">4. Ulasan Yb. Adun Kawasan</font></b></td></tr>
                    <tr><td><table width="100%">
                                <c:if test="${!actionBean.edit}">
                                    <tr><td valign="top">4.1</td>
                                        <td> <font style="text-transform: capitalize"><s:textarea rows="6" cols="150" name="ulasanyb" class="normal_text" /></font></td></tr>
                                    </c:if>

                                <c:if test="${actionBean.edit}">
                                    <tr><td valign="top">4.1</td>
                                        <td>&nbsp;${actionBean.ulasanyb}</td></tr>
                                    </c:if>
                            </table></td></tr>

                    <tr><td>&nbsp;</td></tr>
                    <tr><td id="tdLabel"><b><font style="text-transform: capitalize">5. PERAKUAN PENTADBIR TANAH DAERAH ${actionBean.permohonan.cawangan.daerah.nama}&nbsp;</font></b></td></tr>
                    <tr><td><table width="100%">
                                <c:if test="${!actionBean.edit}">
                                    <tr><td valign="top">5.1 </td>
                                        <td> <s:textarea rows="6" cols="150" name="pentabirtanah1" class="normal_text" /></td></tr>
                                    </c:if>

                                <c:if test="${actionBean.edit}">
                                    <tr><td valign="top">5.1 </td>
                                        <td>&nbsp;${actionBean.pentabirtanah1}</td></tr>
                                    </c:if>

                            </table></td></tr>


                    <tr><td>
                            <c:if test="${!actionBean.edit}">
                                <table id ="dataTable5" border="0">
                                    <c:set var="i" value="2" />
                                    <c:set var="k" value="2" />
                                    <c:forEach items="${actionBean.senaraiLaporanKandunganpbtanah}" var="line">
                                        <tr><td style="display:none" valign="top">${line.idKandungan}</td><td valign="top"><c:out value="5.1.${k}"/></td>
                                            <td valign="top"><font style="text-transform: capitalize"><s:textarea name="kandungan5${i}" id="kandungan5${i}"  rows="6" cols="150" onkeyup="this.value=this.value.toUpperCase();">${line.kandungan}</s:textarea></font>
                                            </td></tr>
                                            <c:set var="i" value="${i+1}" />
                                            <c:set var="k" value="${k+1}" />
                                        </c:forEach>
                                </table></td></tr>
                        <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <s:button class="btn" value="Tambah" name="add" onclick="addRow3('dataTable5',2)"/>&nbsp;</td>
                        </tr>
                        <tr><td><s:hidden name="rowCount6" value="${fn:length(actionBean.senaraiLaporanKandunganpbtanah)}" id="rowCount6"/>&nbsp;</td></tr>

                    </c:if>

                    <tr><td>
                            <c:if test="${actionBean.edit}">
                                <table id ="dataTable5" border="0" cellspacing="12">
                                    <c:set var="i" value="2" />
                                    <c:set var="k" value="2" />
                                    <c:forEach items="${actionBean.senaraiLaporanKandunganpbtanah}" var="line">
                                        <tr><td style="display:none" valign="top">${line.idKandungan}</td><td valign="top"><c:out value="5.1.${k}"/> </td>
                                            <td valign="top"><font style="text-transform: capitalize">${line.kandungan}</font>
                                            </td></tr>
                                            <c:set var="i" value="${i+1}" />
                                            <c:set var="k" value="${k+1}" />
                                        </c:forEach>
                                </table>
                            </c:if>
                        </td>
                    </tr>

                    <tr><td><table width="100%">
                                <c:if test="${!actionBean.edit}">
                                    <tr><td valign="top">5.2 </td>
                                        <td> <s:textarea rows="6" cols="150" name="pentabirtanah2" class="normal_text" /></td></tr>
                                    </c:if>

                                <c:if test="${actionBean.edit}">
                                    <tr><td valign="top">5.2 </td>
                                        <td>&nbsp;${actionBean.pentabirtanah2}</td></tr>
                                    </c:if>

                            </table></td></tr>

                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT' }">

                        <tr><td>&nbsp;</td></tr>
                        <tr><td id="tdLabel"><b>I.<u> Syarat-syarat Hakmilik</u></b></td></tr>
                        <tr><td>&nbsp;</td></tr>

                        <table border="0" width="80%" cellspacing="10" >
                            <tr>
                                <td id="tdDisplay">a)Jenis Hakmilik </td>
                                <td width="2%"><b> : </b></td>
                                <td>
                                    <c:if test="${!actionBean.edit}">
                                        <s:select name="kodHmlk" >
                                            <s:option value="0">Sila Pilih</s:option>
                                            <s:option value="HSM">Hakmilik Sementara (Mukim)</s:option>
                                            <s:option value="HSD">Hakmilik Sementara Daftar</s:option>
                                        </s:select>
                                    </c:if>
                                    <c:if test="${actionBean.edit}">
                                        ${actionBean.kodHmlk} -  ${actionBean.hakmilikPermohonan.kodHakmilik.nama}
                                    </c:if>
                                </td>
                            </tr>


                            <tr>
                                <td id="tdDisplay">b)Tempoh</td>
                                <td width="2%"><b> : </b></td>
                                <td>
                                    <c:if test="${!actionBean.edit}">
                                        <s:text id="tempoh" name="hakmilikPermohonan.tempohHakmilik" size="10"/> &nbsp; tahun
                                    </c:if>
                                    <c:if test="${actionBean.edit}">
                                        ${actionBean.hakmilikPermohonan.tempohHakmilik} &nbsp; tahun
                                    </c:if>
                                </td>
                            </tr>


                            <tr>
                                <td id="tdDisplay">c) Premium</td>
                                <td width="2%"><b> : </b></td>
                                <c:if test="${!actionBean.edit}">
                                    <%--<td><s:text id="premium" name="hakmilikPermohonan.catatan" size="10"/> X harga pasaran X tempoh pajakan</td>--%>
                                    <td><s:select name="hakmilikPermohonan.keteranganKadarPremium" id="nama">
                                            <s:option value="">Sila Pilih</s:option>
                                            <s:options-collection collection="${actionBean.senaraikodKadarPremium}" label="nama" value="nama"/>
                                        </s:select></td>
                                    </c:if>
                                    <c:if test="${actionBean.edit}">
                                        <%--<td> ${actionBean.hakmilikPermohonan.catatan} X harga pasaran X tempoh pajakan </td>--%>
                                    <td> ${actionBean.hakmilikPermohonan.keteranganKadarPremium} </td>
                                </c:if>

                            </tr>


                            <tr>
                                <td id="tdDisplay">d)Hasil </td>
                                <td width="2%"><b> : </b></td>
                                <c:if test="${!actionBean.edit}">
                                    <%--<td>RM<s:text name="hakmilikPermohonan.cukaiPerMeterPersegi" size="5"/>  bagi tiap-tiap 100 m.p atau Sebahagian daripadanya (RM <s:text name="hakmilikPermohonan.cukaiPerLot" size="5"/> bagi setiap hektar)</td>--%>
                                    <td><s:text name="hakmilikPermohonan.keteranganCukaiBaru" size="80" class="normal_text"/></td>
                                </c:if>
                                <c:if test="${actionBean.edit}">
                                    <%--<td> RM  ${actionBean.hakmilikPermohonan.cukaiPerMeterPersegi}  bagi tiap-tiap 100 m.p atau Sebahagian daripadanya (RM ${actionBean.hakmilikPermohonan.cukaiPerLot} bagi setiap hektar)</td>--%>
                                    <td>${actionBean.hakmilikPermohonan.keteranganCukaiBaru}</td>
                                </c:if>

                            </tr>

                            <tr>
                                <td id="tdDisplay">e) Bayaran Ukur</td>
                                <td width="2%"><b> : </b></td>
                                <td> Mengikut P.U (A) 438</td>
                            </tr>

                          <%--  <tr>
                                <td id="tdDisplay" valign="top">f)Syarat Nyata </td>
                                <td width="2%" valign="top"><b> : </b></td>                               
                                <c:if test="${!actionBean.edit}">
                                    <td>
                                        <s:textarea name="syaratNyata" id="syaratNyata" rows="5" cols="80" readonly="yes" class="tooltips" value="${actionBean.hakmilikPermohonan.syaratNyataBaru.kod}--${actionBean.hakmilikPermohonan.syaratNyataBaru.syarat}"></s:textarea>
                                        <s:hidden name="kod" id="kod"/><br>
                                        <s:button name="cari" value="Cari" id="cari" class="btn" onclick="cariPopup()"/></td></c:if>

                                <c:if test="${actionBean.edit}">
                                    <td> ${actionBean.hakmilikPermohonan.syaratNyataBaru.syarat}</td>
                                </c:if>

                                    <c:if test="${!actionBean.edit}">
                                         <c:if test="${actionBean.hakmilikPermohonan.syaratNyataBaru eq 'null' }">
                                    <td>
                                        <s:textarea name="syaratNyata" id="syaratNyata" rows="5" cols="80" readonly="yes" class="tooltips" value=" "></s:textarea>
                                        <s:hidden name="kod" id="kod"/><br>
                                        <s:button name="cari" value="Cari" id="cari" class="btn" onclick="cariPopup()"/></td></c:if></c:if>

                                <c:if test="${actionBean.edit}">
                                    <td> ${actionBean.hakmilikPermohonan.syaratNyataBaru.syarat}</td>
                                </c:if>



                            </tr>--%>
                            <%--<tr>
                                 <c:set value="0" var="x"/>
                                <td id="tdDisplay" valign="top">g)Sekatan Kepentingan </td>
                                <td width="2%" valign="top"><b> : </b></td>                                
                                <c:if test="${!actionBean.edit}">
                                     <c:set value="0" var="i"/>
                                    <td><s:textarea name="syaratNyata1" id="syaratNyata1" rows="5" cols="80" readonly="yes" class="tooltips" value="${actionBean.hakmilikPermohonan.sekatanKepentinganBaru.kod}--${actionBean.hakmilikPermohonan.sekatanKepentinganBaru.sekatan}"></s:textarea>
                                        <s:hidden name="kodSktn" id="kodSktn"/><br>
                                        <s:button name="cari" value="Cari" id="cari" class="btn" onclick="cariPopup2()"/></td></c:if>

                                <c:if test="${actionBean.edit}">
                                    <td>${actionBean.hakmilikPermohonan.sekatanKepentinganBaru.sekatan}</td>
                                </c:if>


                                    <c:if test="${!actionBean.edit}">
                                        <c:if test="${actionBean.hakmilikPermohonan.sekatanKepentinganBaru eq 'null'}">
                                    <td><s:textarea name="syaratNyata1" id="syaratNyata1" rows="5" cols="80" readonly="yes" class="tooltips" value=" "></s:textarea>
                                        <s:hidden name="kodSktn" id="kodSktn"/><br>
                                        <s:button name="cari" value="Cari" id="cari" class="btn" onclick="cariPopup2()"/></td></c:if></c:if>

                                <c:if test="${actionBean.edit}">
                                    <td>${actionBean.hakmilikPermohonan.sekatanKepentinganBaru.sekatan}</td>
                                </c:if>


                            </tr>--%>
                            <tr>
                            <td width="20%" style="color: #003194; font-weight: 700">f)Syarat Nyata </td>
                            <td ><b> : </b></td>
                            <c:if test="${!actionBean.edit}"> <td>
                                    <s:textarea name="syaratNyata" id="syaratNyata" rows="5" cols="80" readonly="yes" class="tooltips" value="${actionBean.hakmilikPermohonan.syaratNyataBaru.kod}--${actionBean.hakmilikPermohonan.syaratNyataBaru.syarat}"></s:textarea>
                                    <s:hidden name="kod" id="kod"/><br>
                                    <s:button name="cari" value="Cari" id="cari" class="btn" onclick="cariPopup()"/></td></c:if>
                                   <c:if test="${actionBean.edit}">
                                    <td> ${actionBean.hakmilikPermohonan.syaratNyataBaru.syarat}</td>
                                </c:if>
                            </tr>

       <tr>
           <c:if test="${!actionBean.edit}"><td width="20%" style="color: #003194; font-weight: 700">g)Sekatan Kepentingan </td>
           <td ><b> : </b></td>
           <td><s:textarea name="syaratNyata1" id="syaratNyata1" rows="5" cols="80" readonly="yes" class="tooltips" value="${actionBean.hakmilikPermohonan.sekatanKepentinganBaru.kod}--${actionBean.hakmilikPermohonan.sekatanKepentinganBaru.sekatan}"></s:textarea>
               <s:hidden name="kodSktn" id="kodSktn"/><br>
               <s:button name="cari" value="Cari" id="cari" class="btn" onclick="cariPopup2()"/></td></c:if>
                 <c:if test="${actionBean.edit}">
                                    <td>${actionBean.hakmilikPermohonan.sekatanKepentinganBaru.sekatan}</td>
                                    </c:if>
       </tr>
                        </table>
                    </c:if>


                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PLPS' }">

                        <tr><td>&nbsp;</td></tr>
                        <table border="0" width="80%" cellspacing="10" >
                            <tr>
                                <td id="tdDisplay">Luas </td>
                                <td width="2%"><b> : </b></td>
                                <td>
                                    <c:if test="${!actionBean.edit}">
                                        LebihKurang  <s:text id="luas" name="hakmilikPermohonan.luasTerlibat" size="20"/> &nbsp;
                                    </c:if>
                                    <c:if test="${actionBean.edit}">
                                        ${actionBean.hakmilikPermohonan.luasTerlibat} &nbsp;
                                    </c:if>
                                </td></tr>

                            <tr>
                                <td id="tdDisplay">Bayaran Lesen </td>
                                <td width="2%"><b> : </b></td>
                                <td>
                                    <c:if test="${!actionBean.edit}">
                                        <%--  <s:text name="permohonanTuntutanKos.idKos" />--%>
                                        RM <s:text id="permohonanTuntutanKos.amaunTuntutan" name="permohonanTuntutanKos.amaunTuntutan" size="20"/> &nbsp;setahun
                                    </c:if>
                                    <c:if test="${actionBean.edit}">
                                        ${actionBean.permohonanTuntutanKos.amaunTuntutan} &nbsp;
                                    </c:if>
                                </td></tr>

                            <tr>
                                <td id="tdDisplay">Maksud Pendudukan </td>
                                <td width="2%"><b> : </b></td>
                                <td>
                                    <c:if test="${!actionBean.edit}">
                                        <%--<s:text id="p" name="nama" size="20"/> &nbsp;--%>
                                        <s:select name="kodPermit.kod" value="${actionBean.permohonanPermitItem.kodItemPermit.kod}">
                                            <s:option value="">Sila Pilih</s:option>
                                            <s:options-collection collection="${actionBean.senaraiKodItemPermit}" label="nama" value="kod" />
                                        </s:select>
                                    </c:if>
                                    <c:if test="${actionBean.edit}">
                                        <%--${actionBean.senaraiKodItemPermit.nama} &nbsp;--%>
                                        ${actionBean.permohonanPermitItem.kodItemPermit.nama} &nbsp;
                                    </c:if>
                                </td></tr>

                            <tr>
                                <td id="tdDisplay">Lokasi </td>
                                <td width="2%"><b> : </b></td>
                                <td>
                                    <c:if test="${!actionBean.edit}">
                                        <s:text id="luas" name="hakmilikPermohonan.lokasi" size="20"/> &nbsp;
                                    </c:if>
                                    <c:if test="${actionBean.edit}">
                                        ${actionBean.hakmilikPermohonan.lokasi} &nbsp;
                                    </c:if>
                                </td></tr>

                            <tr>
                                <td id="tdDisplay">Surat Akuan </td>
                                <td width="2%"><b> : </b></td>
                                <td>
                                    <c:if test="${!actionBean.edit}">
                                        Borang 4A.
                                    </c:if>
                                    <c:if test="${actionBean.edit}">
                                        Borang 4A. &nbsp;
                                    </c:if>
                                </td></tr>

                            <tr>
                                <td id="tdDisplay">Syarat Kuatkuasa Lesen </td>
                                <td width="2%"><b> : </b></td>
                                <td>
                                    i) Mematuhi segala syarat dan arahan Jabatan-Jabatan teknikal yang terlibat serta Pentadbir Tanah Melaka dari masa ke semasa.
                                </td>
                            </tr>

                            <tr>
                                <td id="tdDisplay"> </td>
                                <td width="2%"><b>  </b></td>
                                <td>
                                    ii) Sekiranya tanah yang diduduki ini diperlukan bagi apa-apa maksud awam, maka Pentadbir Tanah Melaka boleh dengan notis <br>
                                    membatalkan lesen ini pada bila-bila masa tanpa membayar sebarang pampasan kepada pemegang lesen.
                                </td>
                            </tr>

                            <tr>
                                <td id="tdDisplay"> </td>
                                <td width="2%"><b>  </b></td>
                                <td>
                                    iii) Lesen ini tidak boleh dipindahmilik, dicagar, dipajak, dibawa dan dijual kepada orang atau badan lain.
                                </td>
                            </tr>

                        </c:if>

                        <table border="0" width="100%" cellspacing="10">
                            <tr>

                                <td id="tdLabel"> II. <u>Penjenisan</u> </td></tr>
                            <tr><td> Tanah ini dikenakan penjenisan " ${actionBean.hakmilikPermohonan.kategoriTanahBaru.nama} " di bawah Seksyen 52(3) Kanun Tanah Negara.</td></tr>

                            <c:if test="${actionBean.pemohon.pihak.bangsa.kod ne 'MEL' }">
                                <tr><td id="tdLabel">III. <u>Syarat Am</u></td></tr>

                                <tr>
                                    <td> - Tiada </td>
                                </tr>
                            </c:if>
                            <c:if test="${actionBean.pemohon.pihak.bangsa.kod eq 'MEL' }">
                                <tr><td id="tdLabel">III. <u>Syarat Am</u></td></tr>

                                <tr>
                                    <td> Setelah hakmilik didaftarkan, pemilik tanah ini dikehendaki membuat permohonan untuk menjadikan hakmiliknya kepada Tanah Adat Melaka (MCL).</td>
                                </tr>
                            </c:if>
                        </table>

                        <c:if test="${actionBean.edit}">
                            <tr><td>&nbsp;</td></tr>
                            <tr><td id="tdLabel"><b><font style="text-transform: capitalize">6. HURAIAN PENGARAH TANAH DAN GALIAN </font></b></td></tr>
                            <%--<tr><td><table width="100%">
                                            <tr><td valign="top">6.1 </td>
                                                <td> <s:textarea rows="6" cols="150" name="hurianpengarah" /></td></tr>
                                    </table></td></tr>--%>
                            <tr><td>
                                    <table id ="dataTable6" border="0">
                                        <c:set var="i" value="1" />
                                        <c:set var="k" value="1" />
                                        <c:forEach items="${actionBean.senaraiLaporanKandunganptg1}" var="line">
                                            <tr><td style="display:none" valign="top">${line.idKandungan}</td><td valign="top"><c:out value="6.${k}"/></td>
                                                <td valign="top"><font style="text-transform: capitalize"><s:textarea name="kandungan2${i}" id="kandungan2${i}"  rows="6" cols="150" onkeyup="this.value=this.value.toUpperCase();">${line.kandungan}</s:textarea></font>
                                                </td></tr>
                                                <c:set var="i" value="${i+1}" />
                                                <c:set var="k" value="${k+1}" />
                                            </c:forEach>
                                    </table></td></tr>
                            <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <s:button class="btn" value="Tambah" name="add" onclick="addRow1('dataTable6',2)"/>&nbsp;</td>
                            </tr>
                            <tr><td><s:hidden name="rowCount4" value="${fn:length(actionBean.senaraiLaporanKandunganptg1)}" id="rowCount4"/>&nbsp;</td></tr>
                        </c:if>


                        <c:if test="${actionBean.edit}">
                            <tr><td>&nbsp;</td></tr>
                            <tr><td id="tdLabel"><b><font style="text-transform: capitalize">7. SYOR PENGARAH TANAH DAN GALIAN </font></b></td></tr>
                            <%--<tr><td><table width="100%">
                                            <tr><td valign="top">7.1 </td>
                                                <td> <s:textarea rows="6" cols="150" name="syorpengarah" /></td></tr>
                                    </table>--%>

                            <tr><td>
                                    <table id ="dataTable7" border="0">
                                        <c:set var="i" value="1" />
                                        <c:set var="k" value="1" />
                                        <c:forEach items="${actionBean.senaraiLaporanKandunganptg2}" var="line">
                                            <tr><td style="display:none" valign="top">${line.idKandungan}</td><td valign="top"><c:out value="7.${k}"/></td>
                                                <td valign="top"><font style="text-transform: capitalize"><s:textarea name="kandungan3${i}" id="kandungan3${i}"  rows="6" cols="150" onkeyup="this.value=this.value.toUpperCase();">${line.kandungan}</s:textarea></font>
                                                </td></tr>
                                                <c:set var="i" value="${i+1}" />
                                                <c:set var="k" value="${k+1}" />
                                            </c:forEach>
                                    </table></td></tr>
                            <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <s:button class="btn" value="Tambah" name="add" onclick="addRow2('dataTable7',3)"/>&nbsp;</td>
                            </tr>
                            <tr><td><s:hidden name="rowCount5" value="${fn:length(actionBean.senaraiLaporanKandunganptg2)}" id="rowCount5"/>&nbsp;</td></tr>
                        </c:if>
                    </table>

                    <%-- <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT' }">--%>
                    <c:if test="${!actionBean.edit}">
                        <p align="center">
                            <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                        </p>
                    </c:if><%--</c:if>--%>

                    <%--<c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PLPS' }">
                        <c:if test="${!actionBean.edit}">
                        <p align="center">
                        <s:button name="simpanPLPS" id="save" value="SimpanPLPS" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </p>
                        </c:if></c:if>--%>

                    <c:if test="${actionBean.edit}">
                        <p align="center">
                            <s:button name="simpanPTG" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                        </p>
                    </c:if>


            </div>
        </fieldset>
    </div>
</s:form>