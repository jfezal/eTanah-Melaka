<%--
    Document   : dev_kertasMMK_full
    Created on : Jan 27, 2010, 3:02:37 PM
    Author     : nursyazwani
Modified By    : NageswaraRao
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<style type="text/css">
    #tdLabel {
        color:#003194;
        display:block;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        margin-left:2px;
        margin-right:0.5em;
        text-align:right;
        width:15em;
    }

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
        width:40em;
    }
</style>
<script type="text/javascript">
    $(document).ready(function() {
        var senaraiplot = $('#senaraiplotsize').val();
        for (var a = 0; a < senaraiplot; a++) {
            var tpegangan = document.getElementById("tpegangan" + a);
            var pegangan = document.getElementById("pegangan" + a);
            if (pegangan.value == 'S') {
                tpegangan.style.display = 'none';
            }
            else {
                tpegangan.style.display = 'block';
            }
        }
    });
    function validateNumber(elmnt, content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = RemoveNonNumeric(content);
            return;
        }
    }

    function RemoveNonNumeric(strString)
    {
        var strValidCharacters = "1234567890";
        var strReturn = "";
        var strBuffer = "";
        var intIndex = 0;
        // Loop through the string
        for (intIndex = 0; intIndex < strString.length; intIndex++)
        {
            strBuffer = strString.substr(intIndex, 1);
            // Is this a number
            if (strValidCharacters.indexOf(strBuffer) > -1)
            {
                strReturn += strBuffer;
            }
        }
        return strReturn;
    }

    function addRow6(tableid) {
        var table = document.getElementById(tableid);
        var rowcount = table.rows.length;
        if (rowcount < 6) {
            var row = table.insertRow(rowcount);
            var cell0 = row.insertCell(0);
            cell0.innerHTML = "<b> 6." + (rowcount + 1) + "</b>";
            var leftcell = row.insertCell(1);
            var el = document.createElement('textarea');
            el.name = 'huraianPentadbir' + (rowcount + 1);
            el.rows = 5;
            el.cols = 150;
            leftcell.appendChild(el);
            // Add Hapus button
            var rightcell = row.insertCell(2);
            var e2 = document.createElement("INPUT");
            e2.setAttribute("type", "button");
            e2.className = "btn";
            e2.value = "Hapus";
            e2.setAttribute("id", "" + (rowcount));
            e2.onclick = function() {
                deleteRow6('', (e2.id));
            };
            rightcell.appendChild(e2);
            document.getElementById("rowCount").value = rowcount + 1;
        } else {
            alert('Huraian Pentadbir Tanah telah melebihi had yang dibenarkan.');
            $("#syorptd").focus();
            return true;
        }
    }

    function addRow7(tableid) {
        var table = document.getElementById(tableid);
        var rowcount = table.rows.length;
        var row = table.insertRow(rowcount);
        var cell0 = row.insertCell(0);
        cell0.innerHTML = "<b> 7." + (rowcount + 1) + "</b>";
        var leftcell = row.insertCell(1);
        var el = document.createElement('textarea');
        el.name = 'syorPentadbir' + (rowcount + 1);
        el.rows = 5;
        el.cols = 150;
        leftcell.appendChild(el);
        // Add Hapus button
        var rightcell = row.insertCell(2);
        var e2 = document.createElement("INPUT");
        e2.setAttribute("type", "button");
        e2.className = "btn";
        e2.value = "Hapus";
        e2.setAttribute("id", "" + (rowcount));
        e2.onclick = function() {
            deleteRow7('', (e2.id));
        };
        rightcell.appendChild(e2);
        document.getElementById("rowCount7").value = rowcount + 1;
    }


    function addRow8(tableid) {
        var table = document.getElementById(tableid);
        var rowcount = table.rows.length;
        var row = table.insertRow(rowcount);
        var cell0 = row.insertCell(0);
        cell0.innerHTML = "<b> 8." + (rowcount + 1) + "</b>";
        var leftcell = row.insertCell(1);
        var el = document.createElement('textarea');
        el.name = 'huraianPtg' + (rowcount + 1);
        el.rows = 5;
        el.cols = 150;
        leftcell.appendChild(el);
        // Add Hapus button
        var rightcell = row.insertCell(2);
        var e2 = document.createElement("INPUT");
        e2.setAttribute("type", "button");
        e2.className = "btn";
        e2.value = "Hapus";
        e2.setAttribute("id", "" + (rowcount));
        e2.onclick = function() {
            deleteRow8('', (e2.id));
        };
        rightcell.appendChild(e2);
        document.getElementById("rowCount8").value = rowcount + 1;
    }

    function addRow8i(tableid) {
        var table = document.getElementById(tableid);
        var rowcount = table.rows.length;
        var row = table.insertRow(rowcount);
        var cell0 = row.insertCell(0);
        cell0.innerHTML = "<b> 7." + (rowcount + 1) + "</b>";
        var leftcell = row.insertCell(1);
        var el = document.createElement('textarea');
        el.name = 'huraianPtg' + (rowcount + 1);
        el.rows = 5;
        el.cols = 150;
        leftcell.appendChild(el);
        // Add Hapus button
        var rightcell = row.insertCell(2);
        var e2 = document.createElement("INPUT");
        e2.setAttribute("type", "button");
        e2.className = "btn";
        e2.value = "Hapus";
        e2.setAttribute("id", "" + (rowcount));
        e2.onclick = function() {
            deleteRow8('', (e2.id));
        };
        rightcell.appendChild(e2);
        document.getElementById("rowCount8").value = rowcount + 1;
    }

    function addRow9(tableid) {
        var table = document.getElementById(tableid);
        var rowcount = table.rows.length;
        var row = table.insertRow(rowcount);
        var cell0 = row.insertCell(0);
        cell0.innerHTML = "<b> 9." + (rowcount + 1) + "</b>";
        var leftcell = row.insertCell(1);
        var el = document.createElement('textarea');
        el.name = 'syorPtg' + (rowcount + 1);
        el.rows = 5;
        el.cols = 150;
        leftcell.appendChild(el);
        // Add Hapus button
        var rightcell = row.insertCell(2);
        var e2 = document.createElement("INPUT");
        e2.setAttribute("type", "button");
        e2.className = "btn";
        e2.value = "Hapus";
        e2.setAttribute("id", "" + (rowcount));
        e2.onclick = function() {
            deleteRow9('', (e2.id));
        };
        rightcell.appendChild(e2);
        document.getElementById("rowCount9").value = rowcount + 1;
    }

    function addRow9i(tableid) {
        var table = document.getElementById(tableid);
        var rowcount = table.rows.length;
        var row = table.insertRow(rowcount);
        var cell0 = row.insertCell(0);
        cell0.innerHTML = "<b> 8." + (rowcount + 1) + "</b>";
        var leftcell = row.insertCell(1);
        var el = document.createElement('textarea');
        el.name = 'syorPtg' + (rowcount + 1);
        el.rows = 5;
        el.cols = 150;
        leftcell.appendChild(el);
        // Add Hapus button
        var rightcell = row.insertCell(2);
        var e2 = document.createElement("INPUT");
        e2.setAttribute("type", "button");
        e2.className = "btn";
        e2.value = "Hapus";
        e2.setAttribute("id", "" + (rowcount));
        e2.onclick = function() {
            deleteRow9('', (e2.id));
        };
        rightcell.appendChild(e2);
        document.getElementById("rowCount9").value = rowcount + 1;
    }

    function deleteRow6(idKandungan, index)
    {
        document.getElementById('dataTable6').deleteRow(index);
        if (idKandungan == '' && index > 0) {
            var rowCount = document.getElementById("rowCount").value;
            document.getElementById("rowCount").value = rowCount - 1;
            regenerateBill('dataTable6', '6', 'huraianPentadbir');
        } else {
            var url = '${pageContext.request.contextPath}/pembangunan/kertas_mmk?deleteSingle&idKandungan=' + idKandungan;
            $.get(url,
                    function(data) {
                        $('#page_div').html(data);
                    }, 'html');
        }
    }

    function deleteRow7(idKandungan, index)
    {
        document.getElementById('dataTable7').deleteRow(index);
        if (idKandungan == '' && index > 0) {
            var rowCount = document.getElementById("rowCount7").value;
            document.getElementById("rowCount7").value = rowCount - 1;
            regenerateBill('dataTable7', '7', 'syorPentadbir');
        } else {
            var url = '${pageContext.request.contextPath}/pembangunan/kertas_mmk?deleteSingle&idKandungan=' + idKandungan;
            $.get(url,
                    function(data) {
                        $('#page_div').html(data);
                    }, 'html');
        }
    }

    function deleteRow8(idKandungan, index)
    {
        document.getElementById('dataTable8').deleteRow(index);
        if (idKandungan == '' && index > 0) {
            var rowCount = document.getElementById("rowCount8").value;
            document.getElementById("rowCount8").value = rowCount - 1;
            regenerateBill('dataTable8', '8', 'huraianPtg');
        } else {
            var url = '${pageContext.request.contextPath}/pembangunan/kertas_mmk?deleteSingle&idKandungan=' + idKandungan;
            $.get(url,
                    function(data) {
                        $('#page_div').html(data);
                    }, 'html');
        }
    }

    function deleteRow8i(idKandungan, index)
    {
        document.getElementById('dataTable8').deleteRow(index);
        if (idKandungan == '' && index > 0) {
            var rowCount = document.getElementById("rowCount8").value;
            document.getElementById("rowCount8").value = rowCount - 1;
            regenerateBill('dataTable8', '7', 'huraianPtg');
        } else {
            var url = '${pageContext.request.contextPath}/pembangunan/kertas_mmk?deleteSingle&idKandungan=' + idKandungan;
            $.get(url,
                    function(data) {
                        $('#page_div').html(data);
                    }, 'html');
        }
    }

    function deleteRow9(idKandungan, index)
    {
        document.getElementById('dataTable9').deleteRow(index);
        if (idKandungan == '' && index > 0) {
            var rowCount = document.getElementById("rowCount9").value;
            document.getElementById("rowCount9").value = rowCount - 1;
            regenerateBill('dataTable9', '9', 'syorPtg');
        } else {
            var url = '${pageContext.request.contextPath}/pembangunan/kertas_mmk?deleteSingle&idKandungan=' + idKandungan;
            $.get(url,
                    function(data) {
                        $('#page_div').html(data);
                    }, 'html');
        }
    }

    function deleteRow9i(idKandungan, index)
    {
        document.getElementById('dataTable9').deleteRow(index);
        if (idKandungan == '' && index > 0) {
            var rowCount = document.getElementById("rowCount9").value;
            document.getElementById("rowCount9").value = rowCount - 1;
            regenerateBill('dataTable9', '8', 'syorPtg');
        } else {
            var url = '${pageContext.request.contextPath}/pembangunan/kertas_mmk?deleteSingle&idKandungan=' + idKandungan;
            $.get(url,
                    function(data) {
                        $('#page_div').html(data);
                    }, 'html');
        }
    }

    function regenerateBill(tableid, tableNo, nama) {
        try {
            var table = document.getElementById(tableid);
            var rowCount = table.rows.length;
            if (rowCount > 1) {
                for (var i = 1; i < rowCount; i++) {
                    var a = table.rows[i].cells[0];
                    a.innerHTML = "<b>" + tableNo + "." + (i + 1) + "</b>";
                    if (i > 0) {
                        table.rows[i].cells[1].childNodes[0].name = nama + (i + 1);
                        table.rows[i].cells[2].childNodes[0].id = i;
                    }
                }
            }
        } catch (e) {
            alert("Error in regenerateBill");
        }
    }


    function validation(event, f) {
        $('#dialog-confirm').dialog('open')
        $(function() {
            $("#dialog-confirm").dialog({
                resizable: false,
                height: 140,
                modal: true,
                buttons: {
                    "Tidak": function() {
                        //  alert('tidak selected');
                        $(this).dialog("close");

                        return false;
                    },
                    "Ya": function() {
                        // alert('ya selected');
                        $(this).dialog("close");

                        var q = $(f).formSerialize();
                        var url = f.action + '?' + event;
                        $.post(url, q,
                                function(data) {
                                    $('#page_div', this.document).html(data);
                                }, 'html');

                        return true;
                    }
                }
            });
        });
    }

    function searchKodSyaratNyata(index) {
        var url = '${pageContext.request.contextPath}/pembangunan/carianSyaratSekatan?showFormCariKodSyaratNyata&index=' + index;
        window.open(url, "eTanah", "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
    }
    function searchKodSekatan(index) {
        var url = '${pageContext.request.contextPath}/pembangunan/carianSyaratSekatan?showFormCariKodSekatan&index=' + index;
        window.open(url, "eTanah", "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
    }

    function tukartukar() {
        var senaraiplot = $('#senaraiplotsize').val();

        for (var a = 0; a < senaraiplot; a++) {
            var tpegangan = document.getElementById("tpegangan" + a);
            var pegangan = document.getElementById("pegangan" + a);


            if (pegangan.value == 'S') {
                tpegangan.style.display = 'none';
            }
            else {
                tpegangan.style.display = 'block';
            }
        }
    }

    function dopopup(idPlot, forEdit) {
        window.open("${pageContext.request.contextPath}/pembangunan/kertas_mmk?editPopup&forEdit=" + forEdit + "&idPlot=" + idPlot, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1400,height=800");
    }

    function doNyata(i, k) {
        var d = i;
        window.open("${pageContext.request.contextPath}/pembangunan/kertas_mmk?editNyata&idPlot=" + d + "&forEdit=" + k, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1200,height=500");
    }

    function doSekatan(i, k) {
        var d = i;
        window.open("${pageContext.request.contextPath}/pembangunan/kertas_mmk?editSekatan&idPlot=" + d + "&forEdit=" + k, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1200,height=900");
    }

    function doKeadaanTanah(i) {
        var d = i;
        window.open("${pageContext.request.contextPath}/pembangunan/kertas_mmk?editKeadaanTanah&idLapor=" + d, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1100,height=500");
    }
</script>
<s:form beanclass="etanah.view.stripes.pembangunan.KertasMMKActionBean">
    <s:messages/>
    <s:errors/>
    <s:hidden name="senaraiplotsize" id="senaraiplotsize"/>
    <s:hidden name="kertasK.kertas.idKertas"/>
    <s:hidden name="btn"/>

    <div id="dialog-confirm" title="Kertas MMK" style="display:none">
        <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>
        <font size="3"> Adakah anda pasti untuk menjana Kertas MMK baru?</font>
    </p>
</div>

<div class="subtitle">
    <fieldset class="aras1">

        <c:if test="${actionBean.nama eq null}">
                <b><font style="text-transform:uppercase">SILA ISIKAN MAKLUMAT DI TAB MAKLUMAT PEMOHON &nbsp;</font></b>
                    </c:if>
                    <c:if test="${actionBean.nama ne null}">
                    
                    <c:choose>
                        <c:when test="${actionBean.permohonan.permohonanSebelum eq null
                                        && (actionBean.permohonan.kodUrusan.kod eq 'RPP'
                                        || actionBean.permohonan.kodUrusan.kod eq 'RPS' 
                                        || actionBean.permohonan.kodUrusan.kod eq 'RLTB')}">
                            <b><font style="text-transform:uppercase">SILA ISIKAN MAKLUMAT DI TAB PERMOHONAN SEBELUM &nbsp;</font></b>
                        </c:when>
                        <c:otherwise><b><font style="text-transform:uppercase">
                    
                        
                        
            <legend></legend>

            <c:if test="${viewAndEditPTD}">
                <div class="content" align="center">
                    <table border="0" width="80%" cellspacing="10">
                        <tr>
                        <td>
                            <table width="100%">
                                <tr>
                                <td align="left" width="50%">&nbsp;&nbsp;&nbsp;&nbsp;${actionBean.idPermohonan}</td>
                                <td align="right" width="50%">Kertas Mesyuarat No ...........................</td>
                                </tr> 
                            </table>
                        </td>
                        </tr>
                        <tr><td align="center"><b>( MAJLIS MESYUARAT KERAJAAN PADA .................... )</b></td></tr>
                        <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font text-align: justify"><s:textarea rows="5" cols="150" name="tajuk" style="text-transform: uppercase"/></font></td></tr>

                        <tr><td><b>1. TUJUAN</b></td></tr>
                        <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:textarea rows="5" cols="150" name="tujuan" class="normal_text" /></td></tr>

                        <tr><td><b>2. BUTIR-BUTIR PEMOHON</b></td></tr>
                        <tr><td>
                            <display:table class="tablecloth" name="${actionBean.listPemohon}" cellpadding="0" cellspacing="0" id="line">
                                <display:column title="Bil" sortable="true">${line_rowNum}
                                    <s:hidden name="x" class="x${line_rowNum -1}" value="${line.pihak.idPihak}"/>
                                </display:column>
                                <display:column property="pihak.nama" title="Nama"/>
                                <display:column title="Alamat">${line.pihak.alamat1}
                                    <c:if test="${line.pihak.alamat2 ne null}"> , ${line.pihak.alamat2} </c:if>
                                    <c:if test="${line.pihak.alamat3 ne null}"> , ${line.pihak.alamat3} </c:if>
                                    <c:if test="${line.pihak.alamat4 ne null}"> , ${line.pihak.alamat4} </c:if>
                                    ${line.pihak.poskod}
                                    ${line.pihak.negeri.nama}
                                </display:column>
                                <display:column title="Tarikh Permohonan" property="permohonan.infoAudit.tarikhMasuk" format="{0,date,dd/MM/yyyy}"/>
                                <c:if test="${line.pihak.jenisPengenalan.kod eq 'S' ||line.pihak.jenisPengenalan.kod eq 'D' ||line.pihak.jenisPengenalan.kod eq 'N' || line.pihak.jenisPengenalan.kod eq 'U'}">
                                    <display:column title="Ahli Lembaga Pengarah">
                                        <c:set value="1" var="count"/>
                                        <c:forEach items="${line.pihak.senaraiPengarah}" var="pengarah">
                                            <c:if test="${pengarah.nama ne null}">
                                                <c:out value="${count}"/>) &nbsp;
                                                <c:out value="${pengarah.nama}"/><br>
                                            </c:if>
                                            <c:set value="${count + 1}" var="count"/>
                                        </c:forEach>
                                    </display:column>
                                    <display:column title="Modal Dibayar">
                                        <c:if test="${line.pihak.modalBerbayar ne null}">${line.pihak.modalBerbayar}</c:if>
                                        <c:if test="${line.pihak.modalBerbayar eq null}">Tiada</c:if>
                                    </display:column>
                                    <display:column title="Modal Dibenarkan">
                                        <c:if test="${line.pihak.modalDibenar ne null}">${line.pihak.modalDibenar}</c:if>
                                        <c:if test="${line.pihak.modalDibenar eq null}">Tiada</c:if>
                                    </display:column>
                                    <display:column title="No. Pendaftaran">
                                        <c:if test="${line.pihak.noPengenalan ne null}">${line.pihak.noPengenalan}</c:if>
                                        <c:if test="${line.pihak.noPengenalan eq null}">Tiada</c:if>
                                    </display:column>
                                    <display:column title="Tarikh Daftar" format="{0,date,dd/MM/yyyy}"></display:column>
                                </c:if>
                            </display:table>
                        </td>
                        </tr>

                        <tr><td><b>3. BUTIR-BUTIR HAKMILIK </b></td></tr>
                        <tr><td>
                            <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0" id="tbl1">
                                <display:column title="Bil" sortable="true" style="vertical-align:top">${tbl1_rowNum}</display:column>
                                <display:column title="Daerah" property="hakmilik.daerah.nama" class="daerah" style="vertical-align:top"/>
                                <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/ Mukim" style="vertical-align:top"/>
                                <display:column title="Nombor Lot/PT" style="vertical-align:top">
                                    ${tbl1.hakmilik.lot.nama} ${tbl1.hakmilik.noLot}
                                </display:column>
                                <display:column title="Jenis & No Hakmilik" style="vertical-align:top">
                                    ${tbl1.hakmilik.kodHakmilik.kod} ${tbl1.hakmilik.noHakmilik}
                                </display:column>
                                <display:column title="Tempoh Hakmilik" style="vertical-align:top">
                                    <c:if test="${tbl1.hakmilik.tempohPegangan ne null && tbl1.hakmilik.tempohPegangan ne 0}">${tbl1.hakmilik.tempohPegangan} tahun</c:if>
                                    <c:if test="${tbl1.hakmilik.tempohPegangan eq null || tbl1.hakmilik.tempohPegangan eq 0}">Tiada</c:if>
                                </display:column>
                                <display:column title="Luas" style="vertical-align:top"><fmt:formatNumber pattern="#,##0.0000" value="${tbl1.hakmilik.luas}"/>&nbsp;${tbl1.hakmilik.kodUnitLuas.nama}</display:column>
                                <display:column title="Kawasan Rizab" style="vertical-align:top">
                                    <c:if test="${tbl1.hakmilik.rizab.nama ne null}">${tbl1.hakmilik.rizab.nama}</c:if>
                                    <c:if test="${tbl1.hakmilik.rizab.nama eq null}">Tiada</c:if>
                                </display:column>
                                <display:column title="Tuan Tanah" style="vertical-align:top">
                                    <display:table class="tablecloth" name="${tbl1.hakmilik.senaraiPihakBerkepentingan}" cellpadding="0" cellspacing="0" id="line2">
                                        <display:column title="Nama">
                                            <c:if test="${line2.jenis.kod eq 'PM' && line2.aktif eq 'Y'}">
                                                ${line2.pihak.nama}<br>No.KP/Syarikat: ${line2.pihak.noPengenalan}
                                            </c:if>
                                        </display:column>
                                        <display:column title="Bahagian Dimiliki">
                                            <c:if test="${line2.jenis.kod eq 'PM' && line2.aktif eq 'Y'}">
                                                ${line2.syerPembilang}/${line2.syerPenyebut}
                                            </c:if>
                                        </display:column>
                                    </display:table>
                                </display:column>
                                <display:column title="Kategori Kegunaan Tanah" style="vertical-align:top">
                                    ${tbl1.hakmilik.kategoriTanah.nama}
                                </display:column>
                                <display:column title="Syarat Nyata" style="vertical-align:top">
                                    <c:if test="${tbl1.hakmilik.syaratNyata.syarat ne null}">${tbl1.hakmilik.syaratNyata.syarat}</c:if>
                                    <c:if test="${tbl1.hakmilik.syaratNyata.syarat eq null}">Tiada</c:if>
                                </display:column>
                                <display:column title="Sekatan Kepentingan" style="vertical-align:top">
                                    <c:if test="${tbl1.hakmilik.sekatanKepentingan.sekatan ne null}">${tbl1.hakmilik.sekatanKepentingan.sekatan}</c:if>
                                    <c:if test="${tbl1.hakmilik.sekatanKepentingan.sekatan eq null}">Tiada</c:if>
                                </display:column>
                                <display:column title="Bebanan Berdaftar" style="vertical-align:top">
                                    <c:set var="flag" value="true" />
                                    <c:forEach items="${tbl1.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                                        <c:if test="${(((senarai.jenis.kod eq 'PG') ||(senarai.jenis.kod eq 'PJ') ||(senarai.jenis.kod eq 'PJK') ||
                                                      (senarai.jenis.kod eq 'PKA') ||(senarai.jenis.kod eq 'PKD') ||(senarai.jenis.kod eq 'PKL') ||
                                                      (senarai.jenis.kod eq 'PKS') ||(senarai.jenis.kod eq 'PMG') ||(senarai.jenis.kod eq 'PI') ||
                                                      (senarai.jenis.kod eq 'PY')) && senarai.aktif eq 'Y') }">
                                            <c:out value="${senarai.jenis.nama}"/><br/><br/>
                                            <c:set var="flag" value="false" />
                                        </c:if>
                                    </c:forEach>
                                    <c:if test="${flag eq 'true'}">
                                        Tiada
                                    </c:if>
                                </display:column>
                            </display:table>

                        </td>
                        </tr>

                        <tr><td><b>4. BUTIR-BUTIR PEMBANGUNAN YANG DICADANGKAN </b></td></tr>
                        <tr>
                        <td>
                            <display:table class="tablecloth" name="${actionBean.listHakmilik}" cellpadding="0" cellspacing="0"  id="line">
                                <display:column title="No" sortable="true">${line_rowNum}</display:column>
                                <display:column property="kategoriTanah.nama" title="Jenis Kategori" />
                                <display:column property="kegunaanTanah.nama" title="Kegunaan Tanah" />
                                <display:column property="bilanganPlot" title="Bilangan Unit"/>
                                <display:column title="Luas Per-Unit"> 
                                    <fmt:formatNumber  pattern="#,##0.0000" value="${line.luas}"/>&nbsp;${line.kodUnitLuas.nama}&nbsp;
                                </display:column> 
                                <display:column property="catatan" title="Butir-Butir Pembangunan" />                                 
                            </display:table>
                        </td>
                        </tr>

                        <c:if test = "${actionBean.permohonan.kodUrusan.kod eq 'SBMS' || actionBean.permohonan.kodUrusan.kod eq 'TSPSS'}">
                            <tr>
                            <td>
                                <display:table class="tablecloth" name="${actionBean.listKerajaanAndRizab}" cellpadding="0" cellspacing="0"  id="line">
                                    <display:column title="No" sortable="true">${line_rowNum}</display:column>                                  
                                    <display:column title="Luas"> 
                                        <fmt:formatNumber  pattern="#,##0.0000" value="${line.luas}"/>&nbsp;${line.kodUnitLuas.nama}&nbsp;
                                    </display:column> 
                                    <display:column property="kegunaanTanahLain" title="Kegunaan Tanah" />                                 
                                </display:table>
                            </td>
                            </tr>
                        </c:if>

                        <c:set var="aa" value="0" />
                        <tr><td><b>5. KEADAAN DAN KEDUDUKAN TANAH </b></td></tr>
                        <tr><td>
                            <table border="0">
                                <c:forEach items="${actionBean.laporanTanah2}" var="line" begin="0">
                                    <c:set var="aa" value="${aa+1}" />
                                    <tr>
                                    <td id="tdLabel"><font color="black">5.${aa})  Kedudukan Tanah :</font></td>
                                    <td id="tdDisplay">
                                        <c:if test="${line.hakmilikPermohonan.lokasi ne null}">${line.hakmilikPermohonan.lokasi}&nbsp;</c:if>
                                        <c:if test="${line.hakmilikPermohonan.lokasi eq null}">Tiada &nbsp;</c:if>
                                        </td>
                                        </tr>
                                        <tr>
                                        <td id="tdLabel"><font color="black">Keadaan Tanah :</font></td>
                                        <td id="tdDisplay">
                                        ${line.kodKeadaanTanah.nama}
                                    </td>
                                    </tr>
                                    <tr>
                                    <td id="tdLabel"><font color="black">Tanaman :</font></td>
                                    <td id="tdDisplay">
                                        <c:if test="${line.usaha eq 'Y'}">Ya</c:if>
                                        <c:if test="${line.usaha eq 'T'}">Tiada</c:if>
                                        </td>
                                        </tr>
                                        <tr>
                                        <td id="tdLabel"><font color="black">Bangunan :</font></td>
                                        <td id="tdDisplay">
                                        <c:if test="${line.adaBangunan eq 'Y'}">Ya</c:if>
                                        <c:if test="${line.adaBangunan eq 'T'}">Tiada</c:if>
                                        </td>
                                        </tr>
                                </c:forEach>
                            </table>
                        </td>
                        </tr>

                        <tr>
                            <c:set var="aa" value="${aa+1}" />
                        <td>
                            <table border="0" width="96%" cellspacing="5">
                                <tr>
                                <td width="20%" valign="top"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5.${aa}) Keadaan sekeliling tanah adalah seperti berikut :</b></td>
                                <td width="80%">
                                    <table class="tablecloth">
                                        <c:set var="aa" value="0" />
                                        <c:forEach items="${actionBean.laporanTanahList2}" var="line" begin="${aa}">
                                            <tr>
                                            <th>&nbsp;</th><th>Jenis Tanah</th><th>Nombor Lot</th><th>Kegunaan</th><th>Catatan</th>
                                            </tr>

                                            <c:forEach items="${actionBean.laporanTanahSempadanList2}" var="line" begin="${aa}">
                                                <c:if test="${line.jenisSempadan eq 'U'}">
                                                    <tr>
                                                    <th>Utara</th>
                                                        <%--<td>${line.hakmilik.idHakmilik}</td> --%>
                                                    <td>
                                                        <c:if test="${line.milikKerajaan eq 'Y'}">Kerajaan</c:if>
                                                        <c:if test="${line.milikKerajaan eq 'T'}">Milik</c:if>
                                                        <c:if test="${line.milikKerajaan eq 'R'}">Rizab</c:if>
                                                        </td>
                                                        <td>${line.kodLot.nama} ${line.noLot}</td>
                                                    <td>${line.kodKategoriTanah.nama}</td>
                                                    <td>${line.catatan}</td>
                                                    </tr>
                                                </c:if>
                                                <c:if test="${line.jenisSempadan eq 'S'}">
                                                    <tr>
                                                    <th>Selatan</th>
                                                        <%--<td>${line.hakmilik.idHakmilik}</td> --%>
                                                    <td>
                                                        <c:if test="${line.milikKerajaan eq 'Y'}">Kerajaan</c:if>
                                                        <c:if test="${line.milikKerajaan eq 'T'}">Milik</c:if>
                                                        <c:if test="${line.milikKerajaan eq 'R'}">Rizab</c:if>
                                                        </td>
                                                        <td>${line.kodLot.nama} ${line.noLot}</td>
                                                    <td>${line.guna}</td>
                                                    <td>${line.catatan}</td>
                                                    </tr>
                                                </c:if>
                                                <c:if test="${line.jenisSempadan eq 'B'}">
                                                    <tr>
                                                    <th>Barat</th>
                                                        <%--<td>${line.hakmilik.idHakmilik}</td> --%>
                                                    <td>
                                                        <c:if test="${line.milikKerajaan eq 'Y'}">Kerajaan</c:if>
                                                        <c:if test="${line.milikKerajaan eq 'T'}">Milik</c:if>
                                                        <c:if test="${line.milikKerajaan eq 'R'}">Rizab</c:if>
                                                        </td>
                                                        <td>${line.kodLot.nama} ${line.noLot}</td>
                                                    <td>${line.kodKategoriTanah.nama}</td>
                                                    <td>${line.catatan}</td>
                                                    </tr>
                                                </c:if>
                                                <c:if test="${line.jenisSempadan eq 'T'}">
                                                    <tr>
                                                    <th>Timur</th>
                                                        <%-- <td>${line.hakmilik.idHakmilik}</td> --%>
                                                    <td>
                                                        <c:if test="${line.milikKerajaan eq 'Y'}">Kerajaan</c:if>
                                                        <c:if test="${line.milikKerajaan eq 'T'}">Milik</c:if>
                                                        <c:if test="${line.milikKerajaan eq 'R'}">Rizab</c:if>
                                                        </td>
                                                        <td>${line.kodLot.nama} ${line.noLot}</td>
                                                    <td>${line.kodKategoriTanah.nama}</td>
                                                    <td>${line.catatan}</td>
                                                    </tr>
                                                </c:if>
                                            </c:forEach>
                                            <c:set var="aa" value="${aa+1}" />
                                        </c:forEach>
                                    </table>
                                </td>
                                </tr>
                            </table>
                        </td></tr>

                        <tr><td><b>6. HURAIAN PENTADBIR TANAH <font style="text-transform: uppercase">${actionBean.pejTanah}</font></b></td></tr>
                        <tr><td>
                            <table id ="dataTable6">
                                <tr>
                                <td><b>6.1 </b></td>
                                <td><s:textarea name="huraianPentadbir1" id="huraianPentadbir1"  rows="5" cols="150" class="normal_text"></s:textarea></td>
                                    </tr>
                                <c:set var="i" value="2" />
                                <c:forEach items="${actionBean.senaraiKandungan}" var="line" begin="1">
                                    <tr><td style="display:none">${line.idKandungan}</td>
                                    <td><b>6.${i}</b></td>
                                    <td><s:textarea name="huraianPentadbir${i}" id="huraianPentadbir${i}"  rows="5" cols="150" class="normal_text">${line.kandungan}</s:textarea></td>
                                    <td><s:button value="Hapus" id="${i-1}" class="btn" name="hapus" onclick="deleteRow6(${line.idKandungan},${i-1})"></s:button> </td>
                                        </tr>
                                    <c:set var="i" value="${i+1}" />
                                </c:forEach>
                            </table>
                        </td>
                        </tr>

                        <tr>
                        <td><s:hidden name="rowCount"  id="rowCount"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <s:button class="btn" value="Tambah" name="add" onclick="addRow6('dataTable6')"/>&nbsp;
                            </tr>

                        <tr><td><b>7. SYOR PENTADBIR TANAH  <font style="text-transform: uppercase">${actionBean.pejTanah}</font></b></td></tr>
                        <tr><td>
                            <table>
                                <tr>
                                <td><b>7.1 </b></td>
                                <td>
                                    <s:textarea name="syorPentadbir1" id="syorPentadbir1"  rows="5" cols="150" class="normal_text"></s:textarea></td>

                                    </tr>
                                </table>
                            </td>
                            </tr>

                        <c:if test="${!plot}">
                            <c:set var="i" value="0"/>
                            <c:forEach items="${actionBean.hakmilikPermohonanList}" var="line" begin="0">
                                <tr><td>
                                    <table border="0" width="97%" cellspacing="5" align="center">
                                        <tr><td>
                                            <c:if test="${i eq 0}">
                                                <b> a) </b>
                                            </c:if>
                                            <c:if test="${i eq 1}">
                                                <b> b) </b>
                                            </c:if>
                                            <c:if test="${i eq 2}">
                                                <b> c) </b>
                                            </c:if>
                                            <c:if test="${i eq 3}">
                                                <b> d) </b>
                                            </c:if>
                                            <c:if test="${i eq 4}">
                                                <b> e) </b>
                                            </c:if>
                                            <c:if test="${i eq 5}">
                                                <b> f) </b>
                                            </c:if>
                                            <c:if test="${i eq 6}">
                                                <b> g) </b>
                                            </c:if>
                                        </td>
                                        <td><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Lot </font></td>
                                        <td><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                        <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">
                                                <s:select name="kodKegunaanTanah${i}" value="${line.kodKegunaanTanah.kod}">
                                                    <s:option value="">--Sila Pilih--</s:option>
                                                    <s:options-collection collection="${listUtil.senaraiKegunaanTanah}" label="nama" value="kod"/>
                                                </s:select></font></td>
                                        </tr>
                                        <tr><td valign="top"></td><td><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Luas</font></td>
                                        <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                        <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">Mengikut Pelan Tatatur <br/>
                                                ${actionBean.pelantatatur}  </font></td>
                                        </tr>
                                        <tr><td></td><td><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Tempoh Pegangan</font></td>
                                        <td><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                        <td>
                                            <c:if test="${line.hakmilik.pegangan eq 'S'}">Selama - lamanya</c:if>
                                            <c:if test="${line.hakmilik.pegangan eq 'P'}">
                                                <c:if test="${line.hakmilik.tempohPegangan ne null && line.hakmilik.tempohPegangan ne 0}">${line.hakmilik.tempohPegangan} <b>tahun </b></c:if>
                                                <c:if test="${line.hakmilik.tempohPegangan eq null || line.hakmilik.tempohPegangan eq 0}">Tiada</c:if>
                                            </c:if>
                                        </td>
                                        </tr>
                                        <tr><td></td>
                                        <td><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Jenis Hakmilik Sementara</font></td>
                                        <td><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                        <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">
                                                <s:select name="kodHakmilikSementara${i}" value="${line.kodHakmilikSementara.kod}">
                                                    <s:option value="">--Sila Pilih--</s:option>
                                                    <s:options-collection collection="${listUtil.senaraiKodHakmilikSementara}" label="nama" value="kod"/>
                                                </s:select></font></td>
                                        </tr>
                                        <tr>
                                        <td></td>
                                        <td><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Jenis Hakmilik Tetap</font></td>
                                        <td><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                        <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">
                                                <s:select name="kodHakmilikTetap${i}" value="${line.kodHakmilikTetap.kod}">
                                                    <s:option value="">--Sila Pilih--</s:option>
                                                    <s:options-collection collection="${listUtil.senaraiKodHakmilikTetapNS}" label="nama" value="kod"/>
                                                </s:select></font></td>
                                        </tr>
                                        <tr>
                                        <td></td>
                                        <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Kadar Cukai</font></td>
                                        <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                        <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">
                                                <s:textarea name="hakmilikPermohonanList[${i}].keteranganCukaiBaru" id="keteranganCukaiBaru" rows="5" cols="100" class="normal_text"/>
                                            </font></td>
                                        </tr>
                                        <c:if test="${!(actionBean.permohonan.kodUrusan.kod eq 'PPCB' || actionBean.permohonan.kodUrusan.kod eq 'PPCBA' || actionBean.permohonan.kodUrusan.kod eq 'PYTN' || actionBean.permohonan.kodUrusan.kod eq 'PPCS')}">
                                            <tr>
                                            <td></td>
                                            <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Keterangan Kadar Premium</font></td>
                                            <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                            <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">
                                                    <s:textarea name="hakmilikPermohonanList[${i}].keteranganKadarPremium" rows="5" cols="100" class="normal_text"/>
                                                </font></td>
                                            </tr>
                                            <tr>
                                            <td></td>
                                            <td><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Kos Sumbangan Infrastuktur</font></td>
                                            <td><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                            <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">
                                                    <s:text name="hakmilikPermohonanList[${i}].kosInfra" id="kosInfra" size="22" onkeyup="validateNumber(this,this.value);"/>
                                                </font> Sehektar</td>
                                            </tr>
                                        </c:if>
                                        <tr>
                                        <td></td>
                                        <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Kadar Bayaran Upah Ukur dan Batu Sempadan</font></td>
                                        <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                        <td valign="top"><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">
                                                <s:textarea name="hakmilikPermohonanList[${i}].keteranganKadarUkur"  value="${line.keteranganKadarUkur}"  rows="5" cols="100" class="normal_text" />
                                            </font></td>
                                        </tr>
                                        <tr>
                                        <td></td>
                                        <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Kadar Bayaran Pendaftaran dan Penyediaan Hakmilik Tetap/Sementara</font></td>
                                        <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                        <td valign="top"><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">
                                                <s:textarea name="hakmilikPermohonanList[${i}].keteranganKadarDaftar" rows="5" cols="100" class="normal_text"/>
                                            </font></td>
                                        </tr>
                                        <tr>
                                        <td></td>
                                        <td><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Kategori</font></td>
                                        <td><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                        <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">
                                                <s:select name="kategoriTanahBaru${i}" value="${line.kategoriTanahBaru.kod}">
                                                    <s:option value="">--Sila Pilih--</s:option>
                                                    <s:options-collection collection="${listUtil.senaraiKodKategoriTanah}" label="nama" value="kod"/>
                                                </s:select></font></td>
                                        </tr>
                                        <tr>
                                        <td></td>
                                        <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Syarat Nyata</font></td>
                                        <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                        <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;"><%--<s:text name="hp.syratNyataBaru.kod" id="syaratNyata" readonly="readonly"/>--%>
                                                <s:textarea name="kodSyaratNyataBaru12" id="syaratNyata${i}" value="${line.syaratNyataBaru.kod} - ${line.syaratNyataBaru.syarat}" readonly="readonly" rows="5" cols="100" class="textBesa" />
                                                <s:hidden name="kodSyaratNyataBaru${i}" id="kod${i}" value="${line.syaratNyataBaru.kod}"/>
                                                <s:button class="btn" value="Cari" name="add" onclick="javascript:searchKodSyaratNyata(${i})" /></font></td>
                                        </tr>
                                        <tr>
                                        <td></td>
                                        <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Sekatan Kepentingan</font></td>
                                        <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                        <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;"><%--<s:text name="hp.sekatanKepentinganBaru.kod" id="sekatan" readonly="readonly"/>--%>
                                                <s:textarea name="kodSekatanKepentinganBaru12" id="sekatan${i}" value="${line.sekatanKepentinganBaru.kod} - ${line.sekatanKepentinganBaru.sekatan}" readonly="readonly" rows="5" cols="100" class="textBesa"/>
                                                <s:hidden name="kodSekatanKepentinganBaru${i}"  id="kodSktn${i}"  value="${line.sekatanKepentinganBaru.kod}"/>
                                                <s:button class="btn" value="Cari" name="add" onclick="javascript:searchKodSekatan(${i})" />
                                            </font>
                                        </td>
                                        </tr>
                                    </table>
                                </td></tr>
                                <c:set var="i" value="${i+1}" />
                            </c:forEach>

                        </c:if>
                        <c:if test="${plot}"> 
                            <tr><td><b>Bangunan </b></td></tr>  
                            <tr>
                            <td>        
                                <div align ="left">                             
                                    <display:table class="tablecloth" name="${actionBean.listKatgBangunanB01}" cellpadding="0" cellspacing="0"  id="line">
                                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                                        <display:column property="kategoriTanah.nama" title="Kategori" />
                                        <display:column property="kegunaanTanah.nama" title="Kegunaan Tanah" />     
                                        <display:column title="Kemaskini Maklumat">
                                            <div align="center">                                      
                                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                                     id='rem_${line_rowNum}' onclick="dopopup('${line.idPlot}', 'Y');
        return false;"  onmouseover="this.style.cursor = 'pointer';">                                                                                    
                                            </div>
                                        </display:column>
                                        <display:column title="Kemaskini Syarat Nyata">
                                            <div align="center">                                          
                                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                                     id='rem_${line_rowNum}' onclick="doNyata('${line.idPlot}', 'NY');
        return false;"  onmouseover="this.style.cursor = 'pointer';">                                                                                 
                                            </div>
                                        </display:column>  
                                        <display:column title="Kemaskini Sekatan Milik">
                                            <div align="center">                                           
                                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                                     id='rem_${line_rowNum}' onclick="doSekatan('${line.idPlot}', 'SK');
        return false;"  onmouseover="this.style.cursor = 'pointer';">                                                                              
                                            </div>
                                        </display:column> 
                                    </display:table>                                         
                                </div>
                            </td>
                            </tr>                          

                            </br>
                            <tr><td><b>Pertanian </b></td></tr>  
                            <tr>
                            <td>        
                                <div align ="left">                             
                                    <display:table class="tablecloth" name="${actionBean.listKatgPertanian}" cellpadding="0" cellspacing="0"  id="line">
                                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                                        <display:column property="kategoriTanah.nama" title="Kategori" />    
                                        <display:column property="kegunaanTanah.nama" title="Kegunaan Tanah" />   
                                        <display:column title="Kemaskini Maklumat">
                                            <div align="center">                                      
                                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                                     id='rem_${line_rowNum}' onclick="dopopup('${line.idPlot}', 'Y');
        return false;"  onmouseover="this.style.cursor = 'pointer';">                                                                                    
                                            </div>
                                        </display:column>
                                        <display:column title="Kemaskini Syarat Nyata">
                                            <div align="center">                                          
                                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                                     id='rem_${line_rowNum}' onclick="doNyata('${line.idPlot}', 'NY');
        return false;"  onmouseover="this.style.cursor = 'pointer';">                                                                                 
                                            </div>
                                        </display:column>  
                                        <display:column title="Kemaskini Sekatan Milik">
                                            <div align="center">                                           
                                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                                     id='rem_${line_rowNum}' onclick="doSekatan('${line.idPlot}', 'SK');
        return false;"  onmouseover="this.style.cursor = 'pointer';">                                                                              
                                            </div>
                                        </display:column> 
                                    </display:table>  
                                </div>
                            </td>
                            </tr>

                            </br>
                            <tr><td><b>Industri/Perusahaan </b></td></tr>  
                            <tr>
                            <td>        
                                <div align ="left">                             
                                    <display:table class="tablecloth" name="${actionBean.listKatgIndustri}" cellpadding="0" cellspacing="0"  id="line">
                                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                                        <display:column property="kategoriTanah.nama" title="Kategori" /> 
                                        <display:column property="kegunaanTanah.nama" title="Kegunaan Tanah" />   
                                        <display:column title="Kemaskini Maklumat">
                                            <div align="center">                                      
                                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                                     id='rem_${line_rowNum}' onclick="dopopup('${line.idPlot}', 'Y');
        return false;"  onmouseover="this.style.cursor = 'pointer';">                                                                                    
                                            </div>
                                        </display:column> 
                                        <display:column title="Kemaskini Syarat Nyata">
                                            <div align="center">                                          
                                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                                     id='rem_${line_rowNum}' onclick="doNyata('${line.idPlot}', 'NY');
        return false;"  onmouseover="this.style.cursor = 'pointer';">                                                                                 
                                            </div>
                                        </display:column>  
                                        <display:column title="Kemaskini Sekatan Milik">
                                            <div align="center">                                           
                                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                                     id='rem_${line_rowNum}' onclick="doSekatan('${line.idPlot}', 'SK');
        return false;"  onmouseover="this.style.cursor = 'pointer';">                                                                              
                                            </div>
                                        </display:column> 
                                    </display:table>  
                                </div>
                            </td>
                            </tr>                          
                        </c:if>

                        <%--
                        <c:if test="${plot}">
                            <c:set var="i" value="0"/>
                            <c:forEach items="${actionBean.senaraiPlot}" var="line" begin="0">
                                <tr><td>
                                        <table border="0" width="97%" cellspacing="5" align="center">
                                            <tr><td>
                                                    <c:if test="${i eq 0}">
                                                        <b> a) </b>
                                                    </c:if>
                                                    <c:if test="${i eq 1}">
                                                        <b> b) </b>
                                                    </c:if>
                                                    <c:if test="${i eq 2}">
                                                        <b> c) </b>
                                                    </c:if>
                                                    <c:if test="${i eq 3}">
                                                        <b> d) </b>
                                                    </c:if>
                                                    <c:if test="${i eq 4}">
                                                        <b> e) </b>
                                                    </c:if>
                                                    <c:if test="${i eq 5}">
                                                        <b> f) </b>
                                                    </c:if>
                                                    <c:if test="${i eq 6}">
                                                        <b> g) </b>
                                                    </c:if>
                                                </td>
                                                
                                                <td><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Lot </font></td>
                                                <td><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                                <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">
                                                    <s:select name="kodKegunaanTanah${i}" value="${line.kegunaanTanah.kod}">
                                                        <s:option value="">--Sila Pilih--</s:option>
                                                        <s:options-collection collection="${listUtil.senaraiKegunaanTanah}" label="nama" value="kod"/>
                                                    </s:select></font></td>
                                            </tr>
                                            <tr><td valign="top"></td><td><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Luas</font></td>
                                                <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                                <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">Mengikut Pelan Tatatur <br/>
                                                    ${actionBean.pelantatatur}</font>
                                                </td>
                                            </tr>
                                            <tr><td></td><td><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Tempoh Pegangan </font></td>
                                                <td><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                                <td>
                                                    <s:select name="pegangan${i}" id="pegangan${i}" value="${line.pegangan}" onchange="tukartukar()">
                                                        <s:option value="">--Sila Pilih--</s:option>
                                                        <s:option value="S">Selama - lamanya</s:option>
                                                        <s:option value="P">Pajakan</s:option>
                                                    </s:select>
                                                    <div id="tpegangan${i}" name="tpegangan${i}" style="display:none"><s:text name="senaraiPlot[${i}].tempohPegangan" id="tempohPegangan" onkeyup="validateNumber(this,this.value);" class="normal_text"/> tahun.</div>

                                                   
                                                </td>
                                            </tr>
                                            
                                            <tr><td></td>
                                                <td><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Jenis Hakmilik Sementara</font></td>
                                                <td><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                                <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">
                                                    <s:select name="kodHakmilikSementara${i}" value="${line.kodHakmilikSementara.kod}">
                                                        <s:option value="">--Sila Pilih--</s:option>
                                                        <s:options-collection collection="${listUtil.senaraiKodHakmilikSementara}" label="nama" value="kod"/>
                                                    </s:select></font></td>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Jenis Hakmilik Tetap</font></td>
                                                <td><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                                <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">
                                                    <s:select name="kodHakmilikTetap${i}" value="${line.kodHakmilikTetap.kod}">
                                                        <s:option value="">--Sila Pilih--</s:option>
                                                        <s:options-collection collection="${listUtil.senaraiKodHakmilikTetapNS}" label="nama" value="kod"/>
                                                    </s:select></font></td>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Kadar Cukai</font></td>
                                                <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                                <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">
                                                    <s:textarea name="senaraiPlot[${i}].keteranganCukaiBaru" id="keteranganCukaiBaru" rows="5" cols="100" class="normal_text"/>
                                                   </font></td>
                                            </tr>
                                            <c:if test="${!(actionBean.permohonan.kodUrusan.kod eq 'PPCB' || actionBean.permohonan.kodUrusan.kod eq 'PPCBA' || actionBean.permohonan.kodUrusan.kod eq 'PYTN' || actionBean.permohonan.kodUrusan.kod eq 'PPCS')}">
                                                <tr>
                                                    <td></td>
                                                    <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Keterangan Kadar Premium</font></td>
                                                    <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                                    <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">
                                                        <s:textarea name="senaraiPlot[${i}].keteranganKadarPremium" rows="5" cols="100" class="normal_text"/>
                                                        </font></td>
                                                </tr>
                                                <tr>
                                                    <td></td>
                                                    <td><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Kos Sumbangan Infrastuktur (RM)</font></td>
                                                    <td><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                                    <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">
                                                        <s:text name="kosInfra${i}" value="${line.kosInfra}" size="30" maxlength="20" onkeyup="validateNumber(this,this.value);"></s:text>
                                                        </font> Sehektar</td>
                                                </tr>
                                            </c:if>
                                            <tr>
                                                <td></td>
                                                <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Kadar Bayaran Upah Ukur dan Batu Sempadan</font></td>
                                                <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                                <td valign="top"><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">
                                                    <s:textarea name="senaraiPlot[${i}].keteranganKadarUkur"  value="${line.keteranganKadarUkur}"  rows="5" cols="100" class="normal_text" />
                                                    </font></td>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Kadar Bayaran Pendaftaran dan Penyediaan Hakmilik Tetap/Sementara</font></td>
                                                <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                                <td valign="top"><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">
                                                    <s:textarea name="senaraiPlot[${i}].keteranganKadarDaftar" rows="5" cols="100" class="normal_text"/>
                                                    </font></td>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Kategori</font></td>
                                                <td><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                                <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">
                                                    <s:select name="kategoriTanahBaru${i}" value="${line.kategoriTanah.kod}">
                                                        <s:option value="">--Sila Pilih--</s:option>
                                                        <s:options-collection collection="${listUtil.senaraiKodKategoriTanah}" label="nama" value="kod"/>
                                                    </s:select>
                                                    </font></td>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Syarat Nyata</font></td>
                                                <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                                <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">
                                                    <s:textarea name="kodSyaratNyataBaru12" id="syaratNyata${i}" value="${line.kodSyaratNyata.kod} - ${line.kodSyaratNyata.syarat}" readonly="readonly" rows="5" cols="100" class="textBesa" />
                                                    <s:hidden name="kodSyaratNyataBaru${i}" id="kod${i}" value="${line.kodSyaratNyata.kod}"/>
                                                    <s:button class="btn" value="Cari" name="add" onclick="javascript:searchKodSyaratNyata(${i})" /></font></td>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Sekatan Kepentingan</font></td>
                                                <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                                <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">
                                                    <s:textarea name="kodSekatanKepentinganBaru12" id="sekatan${i}" value="${line.kodSekatanKepentingan.kod} - ${line.kodSekatanKepentingan.sekatan}" readonly="readonly" rows="5" cols="100" class="textBesa"/>
                                                    <s:hidden name="kodSekatanKepentinganBaru${i}"  id="kodSktn${i}"  value="${line.kodSekatanKepentingan.kod}"/>
                                                    <s:button class="btn" value="Cari" name="add" onclick="javascript:searchKodSekatan(${i})" />
                                                    </font>
                                                </td>
                                            </tr>
                                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'SBMS'}">
                                                <tr>
                                                    <td></td>
                                                    <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Ingatan</font></td>
                                                    <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                                    <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">
                                                        <s:textarea name="senaraiPlot[${i}].peringatan" rows="5" cols="100" class="normal_text"/></font></td>
                                                </tr>
                                            </c:if>
                                        </table>
                                    </td></tr>
                                    <c:set var="i" value="${i+1}" />
                                </c:forEach>
                            </c:if>
                        --%>

                        <tr><td>
                            <table id ="dataTable7">
                                <tr>
                                <td></td>
                                <td></td>
                                </tr>
                                <c:set var="i" value="2" />
                                <c:forEach items="${actionBean.senaraiKandungan7}" var="line" begin="1">
                                    <tr><td style="display:none">${line.idKandungan}</td>
                                    <td><b>7.${i}</b></td>
                                    <td><s:textarea name="syorPentadbir${i}" id="syorPentadbir${i}"  rows="5" cols="150" class="normal_text">${line.kandungan}</s:textarea></td>
                                    <td><s:button value="Hapus" id="${i-1}" class="btn" name="hapus" onclick="deleteRow7(${line.idKandungan},${i-1})" /> </td>
                                    </tr>
                                    <c:set var="i" value="${i+1}" />
                                </c:forEach>
                            </table>
                        </td>
                        </tr>

                        <tr><td><s:hidden name="rowCount7"  id="rowCount7"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <s:button class="btn" value="Tambah" name="add" onclick="addRow7('dataTable7')"/>&nbsp;
                            </tr>

                        <tr><td><b>8. KEPUTUSAN</b></td></tr>
                        <tr>
                        <td>
                            <table cellspacing="10">
                                <tr><td valign="top"><b>8.1 </b></td>
                                <td>Dibentangkan untuk pertimbangan dan keputusan Majlis Mesyuarat Kerajaan Negeri Sembilan Darul Khusus</td></tr>
                            </table>
                        </td>
                        </tr>
                    </table>
                    <br>
                    <p align="center">
                        <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </p>
                </div>
            </c:if>

            <c:if test="${viewAndEditPTG}">
                <div class="content" align="center">
                    <table border="0" width="80%" cellspacing="10">
                        <tr>
                        <td>
                            <table width="100%">
                                <tr>
                                <td align="left" width="50%">&nbsp;&nbsp;&nbsp;&nbsp;${actionBean.idPermohonan}</td>
                                <td align="right" width="50%">Kertas Mesyuarat No ...........................</td>
                                </tr> 
                            </table>
                        </td>
                        </tr>
                        <tr>
                        <td>
                            <table width="100%">
                                <tr>
                                <td align="left" width="50%">&nbsp;&nbsp;&nbsp;&nbsp;<s:text name="noRujukan" id="noRujukan" size="35"/></td>
                                <!--<td align="right"><b>KM No : </b></td>-->
                                </tr> 
                            </table>
                        </td>
                        </tr>
                        <tr><td align="center"><b>( MAJLIS MESYUARAT KERAJAAN PADA .................... )</b></td></tr>
                        <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font style="text-transform: uppercase"><s:textarea rows="5" cols="150" name="tajuk" style="text-align: justify"/></font></td></tr>

                        <tr><td><b>1. TUJUAN</b></td></tr>
                        <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:textarea rows="5" cols="150" name="tujuan" class="normal_text" /></td></tr>

                        <tr><td><b>2. BUTIR-BUTIR PEMOHON</b></td></tr>
                        <tr><td>
                            <display:table class="tablecloth" name="${actionBean.listPemohon}" cellpadding="0" cellspacing="0" id="line">
                                <display:column title="Bil" sortable="true">${line_rowNum}
                                    <s:hidden name="x" class="x${line_rowNum -1}" value="${line.pihak.idPihak}"/>
                                </display:column>
                                <display:column property="pihak.nama" title="Nama"/>
                                <display:column title="Alamat">${line.pihak.alamat1}
                                    <c:if test="${line.pihak.alamat2 ne null}"> , ${line.pihak.alamat2} </c:if>
                                    <c:if test="${line.pihak.alamat3 ne null}"> , ${line.pihak.alamat3} </c:if>
                                    <c:if test="${line.pihak.alamat4 ne null}"> , ${line.pihak.alamat4} </c:if>
                                    ${line.pihak.poskod}
                                    ${line.pihak.negeri.nama}
                                </display:column>
                                <display:column title="Tarikh Permohonan" property="permohonan.infoAudit.tarikhMasuk" format="{0,date,dd/MM/yyyy}"/>
                                <c:if test="${line.pihak.jenisPengenalan.kod eq 'S' ||line.pihak.jenisPengenalan.kod eq 'D' ||line.pihak.jenisPengenalan.kod eq 'N' || line.pihak.jenisPengenalan.kod eq 'U'}">
                                    <display:column title="Ahli Lembaga Pengarah">
                                        <c:set value="1" var="count"/>
                                        <c:forEach items="${line.pihak.senaraiPengarah}" var="pengarah">
                                            <c:if test="${pengarah.nama ne null}">
                                                <c:out value="${count}"/>) &nbsp;
                                                <c:out value="${pengarah.nama}"/><br>
                                            </c:if>
                                            <c:set value="${count + 1}" var="count"/>
                                        </c:forEach>
                                    </display:column>
                                    <display:column title="Modal Dibayar">
                                        <c:if test="${line.pihak.modalBerbayar ne null}">${line.pihak.modalBerbayar}</c:if>
                                        <c:if test="${line.pihak.modalBerbayar eq null}">Tiada</c:if>
                                    </display:column>
                                    <display:column title="Modal Dibenarkan">
                                        <c:if test="${line.pihak.modalDibenar ne null}">${line.pihak.modalDibenar}</c:if>
                                        <c:if test="${line.pihak.modalDibenar eq null}">Tiada</c:if>
                                    </display:column>
                                    <display:column title="No. Pendaftaran">
                                        <c:if test="${line.pihak.noPengenalan ne null}">${line.pihak.noPengenalan}</c:if>
                                        <c:if test="${line.pihak.noPengenalan eq null}">Tiada</c:if>
                                    </display:column>
                                    <display:column title="Tarikh Daftar" format="{0,date,dd/MM/yyyy}"></display:column>
                                </c:if>
                            </display:table>
                        </td>
                        </tr>

                        <tr><td><b>3. BUTIR-BUTIR HAKMILIK </b></td></tr>
                        <tr><td>
                            <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0" id="tbl1">
                                <display:column title="Bil" sortable="true" style="vertical-align:top">${tbl1_rowNum}</display:column>
                                <display:column title="Daerah" property="hakmilik.daerah.nama" class="daerah" style="vertical-align:top"/>
                                <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/ Mukim" style="vertical-align:top"/>
                                <display:column title="Nombor Lot/PT" style="vertical-align:top">
                                    ${tbl1.hakmilik.lot.nama} ${tbl1.hakmilik.noLot}
                                </display:column>
                                <display:column title="Jenis & No Hakmilik" style="vertical-align:top">
                                    ${tbl1.hakmilik.kodHakmilik.kod} ${tbl1.hakmilik.noHakmilik}
                                </display:column>
                                <display:column title="Tempoh Hakmilik" style="vertical-align:top">
                                    <c:if test="${tbl1.hakmilik.tempohPegangan ne null && tbl1.hakmilik.tempohPegangan ne 0}">${tbl1.hakmilik.tempohPegangan} tahun</c:if>
                                    <c:if test="${tbl1.hakmilik.tempohPegangan eq null || tbl1.hakmilik.tempohPegangan eq 0}">Tiada</c:if>
                                </display:column>
                                <display:column title="Luas" style="vertical-align:top"><fmt:formatNumber pattern="#,##0.0000" value="${tbl1.hakmilik.luas}"/>&nbsp;${tbl1.hakmilik.kodUnitLuas.nama}</display:column>
                                <display:column title="Kawasan Rizab" style="vertical-align:top">
                                    <c:if test="${tbl1.hakmilik.rizab.nama ne null}">${tbl1.hakmilik.rizab.nama}</c:if>
                                    <c:if test="${tbl1.hakmilik.rizab.nama eq null}">Tiada</c:if>
                                </display:column>
                                <display:column title="Tuan Tanah" style="vertical-align:top">
                                    <display:table class="tablecloth" name="${tbl1.hakmilik.senaraiPihakBerkepentingan}" cellpadding="0" cellspacing="0" id="line2">
                                        <display:column title="Nama">
                                            <c:if test="${line2.jenis.kod eq 'PM' && line2.aktif eq 'Y'}">
                                                ${line2.pihak.nama}<br>No.KP/Syarikat: ${line2.pihak.noPengenalan}
                                            </c:if>
                                        </display:column>
                                        <display:column title="Bahagian Dimiliki">
                                            <c:if test="${line2.jenis.kod eq 'PM' && line2.aktif eq 'Y'}">
                                                ${line2.syerPembilang}/${line2.syerPenyebut}
                                            </c:if>
                                        </display:column>
                                    </display:table>
                                </display:column>
                                <display:column title="Kategori Kegunaan Tanah" style="vertical-align:top">
                                    ${tbl1.hakmilik.kategoriTanah.nama}
                                </display:column>
                                <display:column title="Syarat Nyata" style="vertical-align:top">
                                    <c:if test="${tbl1.hakmilik.syaratNyata.syarat ne null}">${tbl1.hakmilik.syaratNyata.syarat}</c:if>
                                    <c:if test="${tbl1.hakmilik.syaratNyata.syarat eq null}">Tiada</c:if>
                                </display:column>
                                <display:column title="Sekatan Kepentingan" style="vertical-align:top">
                                    <c:if test="${tbl1.hakmilik.sekatanKepentingan.sekatan ne null}">${tbl1.hakmilik.sekatanKepentingan.sekatan}</c:if>
                                    <c:if test="${tbl1.hakmilik.sekatanKepentingan.sekatan eq null}">Tiada</c:if>
                                </display:column>
                                <display:column title="Bebanan Berdaftar" style="vertical-align:top">
                                    <c:set var="flag" value="true" />
                                    <c:forEach items="${tbl1.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                                        <c:if test="${(((senarai.jenis.kod eq 'PG') ||(senarai.jenis.kod eq 'PJ') ||(senarai.jenis.kod eq 'PJK') ||
                                                      (senarai.jenis.kod eq 'PKA') ||(senarai.jenis.kod eq 'PKD') ||(senarai.jenis.kod eq 'PKL') ||
                                                      (senarai.jenis.kod eq 'PKS') ||(senarai.jenis.kod eq 'PMG') ||(senarai.jenis.kod eq 'PI') ||
                                                      (senarai.jenis.kod eq 'PY')) && senarai.aktif eq 'Y') }">
                                            <c:out value="${senarai.jenis.nama}"/><br/><br/>
                                            <c:set var="flag" value="false" />
                                        </c:if>
                                    </c:forEach>
                                    <c:if test="${flag eq 'true'}">
                                        Tiada
                                    </c:if>
                                </display:column>
                            </display:table>

                        </td>
                        </tr>

                        <tr><td><b>4. BUTIR-BUTIR PEMBANGUNAN YANG DICADANGKAN </b></td></tr>
                        <tr>
                        <td>
                            <display:table class="tablecloth" name="${actionBean.listHakmilik}" cellpadding="0" cellspacing="0"  id="line">
                                <display:column title="No" sortable="true">${line_rowNum}</display:column>
                                <display:column property="kategoriTanah.nama" title="Jenis Kategori" />
                                <display:column property="kegunaanTanah.nama" title="Kegunaan Tanah" />
                                <display:column property="bilanganPlot" title="Bilangan Unit"/>
                                <display:column title="Luas Per-Unit"> 
                                    <fmt:formatNumber  pattern="#,##0.0000" value="${line.luas}"/>&nbsp;${line.kodUnitLuas.nama}&nbsp;
                                </display:column>  
                                <display:column property="catatan" title="Butir-Butir Pembangunan" /> 
                            </display:table>
                        </td>
                        </tr>

                        <c:if test = "${actionBean.permohonan.kodUrusan.kod eq 'SBMS' || actionBean.permohonan.kodUrusan.kod eq 'TSPSS'}">
                            <tr>
                            <td>
                                <display:table class="tablecloth" name="${actionBean.listKerajaanAndRizab}" cellpadding="0" cellspacing="0"  id="line">
                                    <display:column title="No" sortable="true">${line_rowNum}</display:column>                                  
                                    <display:column title="Luas"> 
                                        <fmt:formatNumber  pattern="#,##0.0000" value="${line.luas}"/>&nbsp;${line.kodUnitLuas.nama}&nbsp;
                                    </display:column> 
                                    <display:column property="kegunaanTanahLain" title="Kegunaan Tanah" />                                 
                                </display:table>
                            </td>
                            </tr>
                        </c:if>

                        <c:set var="aa" value="0" />
                        <tr><td><b>5. KEADAAN DAN KEDUDUKAN TANAH </b></td></tr>
                        <tr>
                        <td>        
                            <div align ="left">                             
                                <display:table class="tablecloth" name="${actionBean.laporanTanahKeadaanTanah}" cellpadding="0" cellspacing="0"  id="line">
                                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                                    <display:column property="hakmilikPermohonan.hakmilik.idHakmilik" title="ID Hakmilik" />
                                    <display:column title="Kemaskini Maklumat">
                                        <div align="center">                                      
                                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                                 id='rem_${line_rowNum}' onclick="doKeadaanTanah('${line.idLaporan}');
        return false;"  onmouseover="this.style.cursor = 'pointer';">                                                                                    
                                        </div>
                                    </display:column>
                                </display:table>                                         
                            </div>
                        </td>
                        </tr>                          

                        <%--
                        <tr>
                            <td>
                                <table border="0">
                                    <c:forEach items="${actionBean.laporanTanah2}" var="line" begin="0">
                                        <c:set var="aa" value="${aa+1}" />
                                        <tr>
                                            <td id="tdLabel"><font color="black">5.${aa})  Kedudukan Tanah :</font></td>
                                            <td id="tdDisplay">
                                                <s:text name="kedudukanTanah" id="kedudukanTanah" size="35" class="normal_text"/>
                                                
                                            </td>
                                        </tr>
                                        <tr>
                                            <td id="tdLabel"><font color="black">Keadaan Tanah :</font></td>
                                            <td id="tdDisplay">                                                                                            
                                                <s:select name="keadaanTanah" id="keadaanTanah">
                                                    <s:option value="">--Sila Pilih--</s:option>
                                                    <s:options-collection collection="${listUtil.senaraiKodKeadaanTanah}" label="nama" value="kod" />
                                                </s:select>
                                               
                                            </td>
                                        </tr>
                                        <tr>
                                            <td id="tdLabel"><font color="black">Tanaman :</font></td>
                                            <td id="tdDisplay">
                                                <s:select name="tanaman" id="tanaman" value = "${line.usaha}" style="">                                                               
                                                    <s:option label="Ya"  value="Y" />  
                                                    <s:option label="Tiada"  value="T" />                                   
                                                </s:select>
                                               
                                            </td>
                                        </tr>
                                        <tr>
                                            <td id="tdLabel"><font color="black">Bangunan :</font></td>
                                            <td id="tdDisplay">
                                                <s:select name="bangunan" id="bangunan" value = "${line.adaBangunan}" style="">                                                               
                                                    <s:option label="Ya"  value="Y" />  
                                                    <s:option label="Tiada"  value="T" />                                   
                                                </s:select>

                                                
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <table border="0" width="96%" cellspacing="5">
                                    <tr>
                                        <c:set var="aa" value="${aa+1}" />
                                        <td width="20%" align="top"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5.${aa}) Keadaan sekeliling tanah adalah seperti berikut :</b></td>
                                        <td width="80%">
                                            <table class="tablecloth">
                                                <c:set var="aa" value="0" />
                                                <c:forEach items="${actionBean.laporanTanahList2}" var="line1" begin="0">
                                                    <tr>
                                                        <th colspan="6">Id Hakmilik : ${line1.hakmilikPermohonan.hakmilik.idHakmilik}</th>
                                                    </tr>
                                                    <tr>
                                                        <th>&nbsp;</th><th>Id Hakmilik</th><th>Jenis Tanah</th><th>Nombor Lot</th><th>Kegunaan</th><th>Catatan</th>
                                                    </tr>
                                                    <c:forEach items="${actionBean.laporanTanahSempadanList2}" var="line" begin="0">
                                                        <c:if test="${line1.idLaporan eq line.laporanTanah.idLaporan}">
                                                            <c:if test="${line.jenisSempadan eq 'U'}">
                                                                <tr>
                                                                    <th>Utara</th>
                                                                    <td>${line.hakmilik.idHakmilik}</td>
                                                                    <td>
                                                                        <c:if test="${line.milikKerajaan eq 'Y'}">Kerajaan</c:if>
                                                                        <c:if test="${line.milikKerajaan eq 'T'}">Milik</c:if>
                                                                        <c:if test="${line.milikKerajaan eq 'R'}">Rizab</c:if>
                                                                    </td>
                                                                    <td>${line.kodLot.nama} ${line.noLot}</td>
                                                                    <td>${line.kodKategoriTanah.nama}</td>
                                                                    <td>${line.catatan}</td>
                                                                </tr>
                                                            </c:if>
                                                            <c:if test="${line.jenisSempadan eq 'S'}">
                                                                <tr>
                                                                    <th>Selatan</th>
                                                                    <td>${line.hakmilik.idHakmilik}</td>
                                                                    <td>
                                                                        <c:if test="${line.milikKerajaan eq 'Y'}">Kerajaan</c:if>
                                                                        <c:if test="${line.milikKerajaan eq 'T'}">Milik</c:if>
                                                                        <c:if test="${line.milikKerajaan eq 'R'}">Rizab</c:if>
                                                                    </td>
                                                                    <td>${line.kodLot.nama} ${line.noLot}</td>
                                                                    <td>${line.guna}</td>
                                                                    <td>${line.catatan}</td>
                                                                </tr>
                                                            </c:if>
                                                            <c:if test="${line.jenisSempadan eq 'B'}">
                                                                <tr>
                                                                    <th>Barat</th>
                                                                    <td>${line.hakmilik.idHakmilik}</td>
                                                                    <td>
                                                                        <c:if test="${line.milikKerajaan eq 'Y'}">Kerajaan</c:if>
                                                                        <c:if test="${line.milikKerajaan eq 'T'}">Milik</c:if>
                                                                        <c:if test="${line.milikKerajaan eq 'R'}">Rizab</c:if>
                                                                    </td>
                                                                    <td>${line.kodLot.nama} ${line.noLot}</td>
                                                                    <td>${line.kodKategoriTanah.nama}</td>
                                                                    <td>${line.catatan}</td>
                                                                </tr>
                                                            </c:if>
                                                            <c:if test="${line.jenisSempadan eq 'T'}">
                                                                <tr>
                                                                    <th>Timur</th>
                                                                    <td>${line.hakmilik.idHakmilik}</td>
                                                                    <td>
                                                                        <c:if test="${line.milikKerajaan eq 'Y'}">Kerajaan</c:if>
                                                                        <c:if test="${line.milikKerajaan eq 'T'}">Milik</c:if>
                                                                        <c:if test="${line.milikKerajaan eq 'R'}">Rizab</c:if>
                                                                    </td>
                                                                    <td>${line.kodLot.nama} ${line.noLot}</td>
                                                                    <td>${line.kodKategoriTanah.nama}</td>
                                                                    <td>${line.catatan}</td>
                                                                </tr>
                                                            </c:if>
                                                        </c:if>
                                                    </c:forEach>
                                                    <c:set var="aa" value="${aa+1}" />
                                                </c:forEach>
                                            </table>
                                        </td>
                                    </tr>
                                </table>
                            </td></tr>
                        --%>

                        <tr><td><b>6. HURAIAN PENTADBIR TANAH <font style="text-transform: uppercase">${actionBean.pejTanah}</font></b></td></tr>
                        <tr><td>
                            <table id ="dataTable6">
                                <tr>
                                <td><b>6.1 </b></td>
                                <td><s:textarea name="huraianPentadbir1" id="huraianPentadbir1"  rows="5" cols="150" class="normal_text"></s:textarea></td>
                                    </tr>
                                <c:set var="i" value="2" />
                                <c:forEach items="${actionBean.senaraiKandungan}" var="line" begin="1">
                                    <tr><td style="display:none">${line.idKandungan}</td>
                                    <td><b>6.${i}</b></td>
                                    <td><s:textarea name="huraianPentadbir${i}" id="huraianPentadbir${i}"  rows="5" cols="150" class="normal_text">${line.kandungan}</s:textarea></td>
                                    <td><s:button value="Hapus" id="${i-1}" class="btn" name="hapus" onclick="deleteRow6(${line.idKandungan},${i-1})"></s:button> </td>
                                        </tr>
                                    <c:set var="i" value="${i+1}" />
                                </c:forEach>
                            </table>
                        </td>
                        </tr>

                        <tr>
                        <td><s:hidden name="rowCount"  id="rowCount"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <s:button class="btn" value="Tambah" name="add" onclick="addRow6('dataTable6')"/>&nbsp;
                            </tr>

                        <tr><td><b>7. SYOR PENTADBIR TANAH  <font style="text-transform: uppercase">${actionBean.pejTanah}</font></b></td></tr>
                        <tr><td>
                            <table>
                                <tr>
                                <td><b>7.1 </b></td>
                                <td>
                                    <s:textarea name="syorPentadbir1" id="syorPentadbir1"  rows="5" cols="150" class="normal_text"></s:textarea></td>
                                    </tr>
                                </table>
                            </td>
                            </tr>

                        <c:if test="${!plot}">
                            <c:set var="i" value="0"/>
                            <c:forEach items="${actionBean.hakmilikPermohonanList}" var="line" begin="0">
                                <tr><td>
                                    <table border="0" width="97%" cellspacing="5" align="center">
                                        <tr><td>
                                            <c:if test="${i eq 0}">
                                                <b> a) </b>
                                            </c:if>
                                            <c:if test="${i eq 1}">
                                                <b> b) </b>
                                            </c:if>
                                            <c:if test="${i eq 2}">
                                                <b> c) </b>
                                            </c:if>
                                            <c:if test="${i eq 3}">
                                                <b> d) </b>
                                            </c:if>
                                            <c:if test="${i eq 4}">
                                                <b> e) </b>
                                            </c:if>
                                            <c:if test="${i eq 5}">
                                                <b> f) </b>
                                            </c:if>
                                            <c:if test="${i eq 6}">
                                                <b> g) </b>
                                            </c:if>
                                        </td>
                                        <td><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Lot </font></td>
                                        <td><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                        <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">
                                                <s:select name="kodKegunaanTanah${i}" value="${line.kodKegunaanTanah.kod}">
                                                    <s:option value="">--Sila Pilih--</s:option>
                                                    <s:options-collection collection="${listUtil.senaraiKegunaanTanah}" label="nama" value="kod"/>
                                                </s:select></font></td>
                                        </tr>
                                        <tr><td valign="top"></td><td><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Luas</font></td>
                                        <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                        <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;"><%--<fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp;<b>${line.hakmilik.kodUnitLuas.nama} &nbsp; </b>--%>Mengikut Pelan Tatatur <br/>
                                                ${actionBean.pelantatatur}  </font></td>
                                        </tr>
                                        <tr><td></td><td><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Tempoh Pegangan</font></td>
                                        <td><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                        <td>
                                            <c:if test="${line.hakmilik.pegangan eq 'S'}">Selama - lamanya</c:if>
                                            <c:if test="${line.hakmilik.pegangan eq 'P'}">
                                                <c:if test="${line.hakmilik.tempohPegangan ne null && line.hakmilik.tempohPegangan ne 0}">${line.hakmilik.tempohPegangan} <b>tahun </b></c:if>
                                                <c:if test="${line.hakmilik.tempohPegangan eq null || line.hakmilik.tempohPegangan eq 0}">Tiada</c:if>
                                            </c:if>
                                        </td>
                                        </tr>
                                        <tr><td></td>
                                        <td><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Jenis Hakmilik Sementara</font></td>
                                        <td><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                        <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">
                                                <s:select name="kodHakmilikSementara${i}" value="${line.kodHakmilikSementara.kod}">
                                                    <s:option value="">--Sila Pilih--</s:option>
                                                    <s:options-collection collection="${listUtil.senaraiKodHakmilikSementara}" label="nama" value="kod"/>
                                                </s:select></font></td>
                                        </tr>
                                        <tr>
                                        <td></td>
                                        <td><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Jenis Hakmilik Tetap</font></td>
                                        <td><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                        <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">
                                                <s:select name="kodHakmilikTetap${i}" value="${line.kodHakmilikTetap.kod}">
                                                    <s:option value="">--Sila Pilih--</s:option>
                                                    <s:options-collection collection="${listUtil.senaraiKodHakmilikTetapNS}" label="nama" value="kod"/>
                                                </s:select></font></td>
                                        </tr>
                                        <tr>
                                        <td></td>
                                        <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Kadar Cukai</font></td>
                                        <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                        <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">
                                                <s:textarea name="hakmilikPermohonanList[${i}].keteranganCukaiBaru" id="keteranganCukaiBaru" rows="5" cols="100" class="normal_text"/>
                                            </font></td>
                                        </tr>
                                        <c:if test="${!(actionBean.permohonan.kodUrusan.kod eq 'PPCB' || actionBean.permohonan.kodUrusan.kod eq 'PPCBA' || actionBean.permohonan.kodUrusan.kod eq 'PYTN' || actionBean.permohonan.kodUrusan.kod eq 'PPCS')}">
                                            <tr>
                                            <td></td>
                                            <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Keterangan Kadar Premium</font></td>
                                            <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                            <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">
                                                    <s:textarea name="hakmilikPermohonanList[${i}].keteranganKadarPremium" rows="5" cols="100" class="normal_text"/>
                                                </font></td>
                                            </tr>
                                            <tr>
                                            <td></td>
                                            <td><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Kos Sumbangan Infrastuktur</font></td>
                                            <td><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                            <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">
                                                    <s:text name="hakmilikPermohonanList[${i}].kosInfra" id="kosInfra" size="22" onkeyup="validateNumber(this,this.value);"/>
                                                </font> Sehektar</td>
                                            </tr>
                                        </c:if>
                                        <tr>
                                        <td></td>
                                        <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Kadar Bayaran Upah Ukur dan Batu Sempadan</font></td>
                                        <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                        <td valign="top"><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">
                                                <s:textarea name="hakmilikPermohonanList[${i}].keteranganKadarUkur"  value="${line.keteranganKadarUkur}"  rows="5" cols="100" class="normal_text" />
                                            </font></td>
                                        </tr>
                                        <tr>
                                        <td></td>
                                        <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Kadar Bayaran Pendaftaran dan Penyediaan Hakmilik Tetap/Sementara</font></td>
                                        <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                        <td valign="top"><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">
                                                <s:textarea name="hakmilikPermohonanList[${i}].keteranganKadarDaftar" rows="5" cols="100" class="normal_text"/>
                                            </font></td>
                                        </tr>
                                        <tr>
                                        <td></td>
                                        <td><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Kategori</font></td>
                                        <td><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                        <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">
                                                <s:select name="kategoriTanahBaru${i}" value="${line.kategoriTanahBaru.kod}">
                                                    <s:option value="">--Sila Pilih--</s:option>
                                                    <s:options-collection collection="${listUtil.senaraiKodKategoriTanah}" label="nama" value="kod"/>
                                                </s:select></font></td>
                                        </tr>
                                        <tr>
                                        <td></td>
                                        <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Syarat Nyata</font></td>
                                        <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                        <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;"><%--<s:text name="hp.syratNyataBaru.kod" id="syaratNyata" readonly="readonly"/>--%>
                                                <s:textarea name="kodSyaratNyataBaru12" id="syaratNyata${i}" value="${line.syaratNyataBaru.kod} - ${line.syaratNyataBaru.syarat}" readonly="readonly" rows="5" cols="100" class="textBesa" />
                                                <s:hidden name="kodSyaratNyataBaru${i}" id="kod${i}" value="${line.syaratNyataBaru.kod}"/>
                                                <s:button class="btn" value="Cari" name="add" onclick="javascript:searchKodSyaratNyata(${i})" /></font></td>
                                        </tr>
                                        <tr>
                                        <td></td>
                                        <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Sekatan Kepentingan</font></td>
                                        <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                        <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;"><%--<s:text name="hp.sekatanKepentinganBaru.kod" id="sekatan" readonly="readonly"/>--%>
                                                <s:textarea name="kodSekatanKepentinganBaru12" id="sekatan${i}" value="${line.sekatanKepentinganBaru.kod} - ${line.sekatanKepentinganBaru.sekatan}" readonly="readonly" rows="5" cols="100" class="textBesa"/>
                                                <s:hidden name="kodSekatanKepentinganBaru${i}"  id="kodSktn${i}"  value="${line.sekatanKepentinganBaru.kod}"/>
                                                <s:button class="btn" value="Cari" name="add" onclick="javascript:searchKodSekatan(${i})" />
                                            </font>
                                        </td>
                                        </tr>
                                    </table>
                                </td></tr>
                                <c:set var="i" value="${i+1}" />
                            </c:forEach>

                        </c:if>
                        <c:if test="${plot}"> 
                            <tr><td><b>Bangunan </b></td></tr>  
                            <tr>
                            <td>        
                                <div align ="left">                             
                                    <display:table class="tablecloth" name="${actionBean.listKatgBangunanB01}" cellpadding="0" cellspacing="0"  id="line">
                                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                                        <display:column property="kategoriTanah.nama" title="Kategori" />
                                        <display:column property="kegunaanTanah.nama" title="Kegunaan Tanah" />     
                                        <display:column title="Kemaskini Maklumat">
                                            <div align="center">                                      
                                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                                     id='rem_${line_rowNum}' onclick="dopopup('${line.idPlot}', 'Y');
        return false;"  onmouseover="this.style.cursor = 'pointer';">                                                                                    
                                            </div>
                                        </display:column>
                                        <display:column title="Kemaskini Syarat Nyata">
                                            <div align="center">                                          
                                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                                     id='rem_${line_rowNum}' onclick="doNyata('${line.idPlot}', 'NY');
        return false;"  onmouseover="this.style.cursor = 'pointer';">                                                                                 
                                            </div>
                                        </display:column>  
                                        <display:column title="Kemaskini Sekatan Milik">
                                            <div align="center">                                           
                                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                                     id='rem_${line_rowNum}' onclick="doSekatan('${line.idPlot}', 'SK');
        return false;"  onmouseover="this.style.cursor = 'pointer';">                                                                              
                                            </div>
                                        </display:column> 
                                    </display:table>                                         
                                </div>
                            </td>
                            </tr>                          

                            </br>
                            <tr><td><b>Pertanian </b></td></tr>  
                            <tr>
                            <td>        
                                <div align ="left">                             
                                    <display:table class="tablecloth" name="${actionBean.listKatgPertanian}" cellpadding="0" cellspacing="0"  id="line">
                                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                                        <display:column property="kategoriTanah.nama" title="Kategori" />   
                                        <display:column property="kegunaanTanah.nama" title="Kegunaan Tanah" />   
                                        <display:column title="Kemaskini Maklumat">
                                            <div align="center">                                      
                                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                                     id='rem_${line_rowNum}' onclick="dopopup('${line.idPlot}', 'Y');
        return false;"  onmouseover="this.style.cursor = 'pointer';">                                                                                    
                                            </div>
                                        </display:column>
                                        <display:column title="Kemaskini Syarat Nyata">
                                            <div align="center">                                          
                                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                                     id='rem_${line_rowNum}' onclick="doNyata('${line.idPlot}', 'NY');
        return false;"  onmouseover="this.style.cursor = 'pointer';">                                                                                 
                                            </div>
                                        </display:column>  
                                        <display:column title="Kemaskini Sekatan Milik">
                                            <div align="center">                                           
                                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                                     id='rem_${line_rowNum}' onclick="doSekatan('${line.idPlot}', 'SK');
        return false;"  onmouseover="this.style.cursor = 'pointer';">                                                                              
                                            </div>
                                        </display:column> 
                                    </display:table>  
                                </div>
                            </td>
                            </tr>

                            </br>
                            <tr><td><b>Industri/Perusahaan </b></td></tr>  
                            <tr>
                            <td>        
                                <div align ="left">                             
                                    <display:table class="tablecloth" name="${actionBean.listKatgIndustri}" cellpadding="0" cellspacing="0"  id="line">
                                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                                        <display:column property="kategoriTanah.nama" title="Kategori" />  
                                        <display:column property="kegunaanTanah.nama" title="Kegunaan Tanah" />   
                                        <display:column title="Kemaskini Maklumat">
                                            <div align="center">                                      
                                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                                     id='rem_${line_rowNum}' onclick="dopopup('${line.idPlot}', 'Y');
        return false;"  onmouseover="this.style.cursor = 'pointer';">                                                                                    
                                            </div>
                                        </display:column> 
                                        <display:column title="Kemaskini Syarat Nyata">
                                            <div align="center">                                          
                                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                                     id='rem_${line_rowNum}' onclick="doNyata('${line.idPlot}', 'NY');
        return false;"  onmouseover="this.style.cursor = 'pointer';">                                                                                 
                                            </div>
                                        </display:column>  
                                        <display:column title="Kemaskini Sekatan Milik">
                                            <div align="center">                                           
                                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                                     id='rem_${line_rowNum}' onclick="doSekatan('${line.idPlot}', 'SK');
        return false;"  onmouseover="this.style.cursor = 'pointer';">                                                                              
                                            </div>
                                        </display:column> 
                                    </display:table>  
                                </div>
                            </td>
                            </tr>                          
                        </c:if>

                        <tr><td>
                            <table id ="dataTable7">
                                <tr>
                                <td></td>
                                <td></td>
                                </tr>
                                <c:set var="i" value="2" />
                                <c:forEach items="${actionBean.senaraiKandungan7}" var="line" begin="1">
                                    <tr><td style="display:none">${line.idKandungan}</td>
                                    <td><b>7.${i}</b></td>
                                    <td><s:textarea name="syorPentadbir${i}" id="syorPentadbir${i}"  rows="5" cols="150" class="normal_text">${line.kandungan}</s:textarea></td>
                                    <td><s:button value="Hapus" id="${i-1}" class="btn" name="hapus" onclick="deleteRow7(${line.idKandungan},${i-1})" /> </td>
                                    </tr>
                                    <c:set var="i" value="${i+1}" />
                                </c:forEach>
                            </table>
                        </td>
                        </tr>
                        <tr><td><s:hidden name="rowCount7"  id="rowCount7"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <s:button class="btn" value="Tambah" name="add" onclick="addRow7('dataTable7')"/>&nbsp;
                            </tr>

                        <tr><td><b>8. HURAIAN PENGARAH TANAH DAN GALIAN NEGERI SEMBILAN DARUL KHUSUS</b></td></tr>
                        <tr><td>
                            <table id ="dataTable8">
                                <tr>
                                <td><b>8.1 </b></td>
                                <td><s:textarea name="huraianPtg1" id="huraianPtg1"  rows="5" cols="150" class="normal_text"></s:textarea></td>
                                    </tr>
                                <c:set var="i" value="2" />
                                <c:forEach items="${actionBean.senaraiKandungan8}" var="line" begin="1">
                                    <tr><td style="display:none">${line.idKandungan}</td>
                                    <td><b>8.${i}</b></td>
                                    <td><s:textarea name="huraianPtg${i}" id="huraianPtg${i}"  rows="5" cols="150" class="normal_text">${line.kandungan}</s:textarea></td>
                                    <td><s:button value="Hapus" id="${i-1}" class="btn" name="hapus" onclick="deleteRow8(${line.idKandungan},${i-1})" /> </td>
                                    </tr>
                                    <c:set var="i" value="${i+1}" />
                                </c:forEach>
                            </table>
                        </td>
                        </tr>
                        <tr>
                        <td><s:hidden name="rowCount8"  id="rowCount8"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <s:button class="btn" value="Tambah" name="add" onclick="addRow8('dataTable8')"/>&nbsp;

                        </td>
                        </tr>

                        <tr><td><b>9. SYOR PENGARAH TANAH DAN GALIAN NEGERI SEMBILAN DARUL KHUSUS</b></td></tr>
                        <tr><td>
                            <table id ="dataTable9">
                                <tr>
                                <td><b>9.1 </b></td>
                                <td><s:textarea name="syorPtg1" id="syorPtg1"  rows="5" cols="150" class="normal_text"></s:textarea></td>
                                    </tr>
                                <c:set var="i" value="2" />
                                <c:forEach items="${actionBean.senaraiKandungan9}" var="line" begin="1">
                                    <tr><td style="display:none">${line.idKandungan}</td>
                                    <td><b>9.${i}</b></td>
                                    <td><s:textarea name="syorPtg${i}" id="syorPtg${i}"  rows="5" cols="150" class="normal_text">${line.kandungan}</s:textarea></td>
                                    <td><s:button value="Hapus" id="${i-1}" class="btn" name="hapus" onclick="deleteRow9(${line.idKandungan},${i-1})" /> </td>
                                    </tr>
                                    <c:set var="i" value="${i+1}" />
                                </c:forEach>
                            </table>
                        </td>
                        </tr>
                        <tr>
                        <td><s:hidden name="rowCount9"  id="rowCount9"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <s:button class="btn" value="Tambah" name="add" onclick="addRow9('dataTable9')"/>&nbsp;
                        </td>
                        </tr>

                        <tr><td><b>10. KEPUTUSAN</b></td></tr>
                        <tr>
                        <td>
                            <table cellspacing="10">
                                <tr><td valign="top"><b>10.1 </b></td>
                                <td>Dibentangkan untuk pertimbangan dan keputusan Majlis Mesyuarat Kerajaan Negeri Sembilan Darul Khusus</td></tr>
                            </table>
                        </td>
                        </tr>
                    </table>
                    <br>
                    <p align="center">
                        <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </p>
                </div>
            </c:if>

            <c:if test="${viewOnly}">
                <div class="content" align="center">
                    <table border="0" width="80%" cellspacing="10">
                        <tr>
                        <td>
                            <table width="100%">
                                <tr>
                                <td align="left" width="50%">${actionBean.idPermohonan}</td>
                                <td align="right" width="50%">KM No ..........................</td>
                            </table>
                        </td>
                        </tr>
                        <tr>
                        <td>
                            <table width="100%">
                                <tr>
                                <td align="left" width="50%">${actionBean.noRujukan}</td>
                                <!--<td align="right"><b>KM No:</b> $//{actionBean.kmno}&nbsp; </td>-->
                            </table>
                        </td>
                        </tr>

                        <tr><td align="center"><b>( MAJLIS MESYUARAT KERAJAAN PADA .................... )</b></td></tr>

                        <tr><td><font style="text-transform: uppercase">${actionBean.tajuk}</font></td></tr>

                        <tr><td><b>1. TUJUAN</b></td></tr>
                        <tr>
                        <td>
                            <table cellspacing="10">
                                <tr><td valign="top"><b>1.1 </b></td><td>${actionBean.tujuan} </td> </tr>
                            </table>
                        </td>
                        </tr>

                        <tr><td><b>2. BUTIR-BUTIR PEMOHON</b></td></tr>
                        <tr><td>
                            <display:table class="tablecloth" name="${actionBean.listPemohon}" cellpadding="0" cellspacing="0" id="line">
                                <display:column title="Bil" sortable="true">${line_rowNum}
                                    <s:hidden name="x" class="x${line_rowNum -1}" value="${line.pihak.idPihak}"/>
                                </display:column>
                                <display:column property="pihak.nama" title="Nama"/>
                                <display:column title="Alamat">${line.pihak.alamat1}
                                    <c:if test="${line.pihak.alamat2 ne null}"> , ${line.pihak.alamat2} </c:if>
                                    <c:if test="${line.pihak.alamat3 ne null}"> , ${line.pihak.alamat3} </c:if>
                                    <c:if test="${line.pihak.alamat4 ne null}"> , ${line.pihak.alamat4} </c:if>
                                    ${line.pihak.poskod}
                                    ${line.pihak.negeri.nama}
                                </display:column>
                                <display:column title="Tarikh Permohonan" property="permohonan.infoAudit.tarikhMasuk" format="{0,date,dd/MM/yyyy}"/>
                                <c:if test="${line.pihak.jenisPengenalan.kod eq 'S' ||line.pihak.jenisPengenalan.kod eq 'D' ||line.pihak.jenisPengenalan.kod eq 'N' || line.pihak.jenisPengenalan.kod eq 'U'}">
                                    <display:column title="Ahli Lembaga Pengarah">
                                        <c:set value="1" var="count"/>
                                        <c:forEach items="${line.pihak.senaraiPengarah}" var="pengarah">
                                            <c:if test="${pengarah.nama ne null}">
                                                <c:out value="${count}"/>) &nbsp;
                                                <c:out value="${pengarah.nama}"/><br>
                                            </c:if>
                                            <c:set value="${count + 1}" var="count"/>
                                        </c:forEach>
                                    </display:column>
                                    <display:column title="Modal Dibayar">
                                        <c:if test="${line.pihak.modalBerbayar ne null}">${line.pihak.modalBerbayar}</c:if>
                                        <c:if test="${line.pihak.modalBerbayar eq null}">Tiada</c:if>
                                    </display:column>
                                    <display:column title="Modal Dibenarkan">
                                        <c:if test="${line.pihak.modalDibenar ne null}">${line.pihak.modalDibenar}</c:if>
                                        <c:if test="${line.pihak.modalDibenar eq null}">Tiada</c:if>
                                    </display:column>
                                    <display:column title="No. Pendaftaran">
                                        <c:if test="${line.pihak.noPengenalan ne null}">${line.pihak.noPengenalan}</c:if>
                                        <c:if test="${line.pihak.noPengenalan eq null}">Tiada</c:if>
                                    </display:column>
                                    <display:column title="Tarikh Daftar" format="{0,date,dd/MM/yyyy}"></display:column>
                                </c:if>
                            </display:table>
                        </td></tr>
                        <tr><td><b>3. BUTIR-BUTIR HAKMILIK </b></td></tr>
                        <tr><td>
                            <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0" id="tbl1">
                                <display:column title="Bil" sortable="true" style="vertical-align:top">${tbl1_rowNum}</display:column>
                                <display:column title="Daerah" property="hakmilik.daerah.nama" class="daerah" style="vertical-align:top"/>
                                <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/ Mukim" style="vertical-align:top"/>
                                <display:column title="Nombor Lot/PT" style="vertical-align:top">
                                    ${tbl1.hakmilik.lot.nama} ${tbl1.hakmilik.noLot}
                                </display:column>
                                <display:column title="Jenis & No Hakmilik" style="vertical-align:top">
                                    ${tbl1.hakmilik.kodHakmilik.kod} ${tbl1.hakmilik.noHakmilik}
                                </display:column>
                                <display:column title="Tempoh Hakmilik" style="vertical-align:top">
                                    <c:if test="${tbl1.hakmilik.tempohPegangan ne null && tbl1.hakmilik.tempohPegangan ne 0}">${tbl1.hakmilik.tempohPegangan} tahun</c:if>
                                    <c:if test="${tbl1.hakmilik.tempohPegangan eq null || tbl1.hakmilik.tempohPegangan eq 0}">Tiada</c:if>
                                </display:column>
                                <display:column title="Luas" style="vertical-align:top"><fmt:formatNumber pattern="#,##0.0000" value="${tbl1.hakmilik.luas}"/>&nbsp;${tbl1.hakmilik.kodUnitLuas.nama}</display:column>
                                <display:column title="Kawasan Rizab" style="vertical-align:top">
                                    <c:if test="${tbl1.hakmilik.rizab.nama ne null}">${tbl1.hakmilik.rizab.nama}</c:if>
                                    <c:if test="${tbl1.hakmilik.rizab.nama eq null}">Tiada</c:if>
                                </display:column>
                                <display:column title="Tuan Tanah" style="vertical-align:top">
                                    <display:table class="tablecloth" name="${tbl1.hakmilik.senaraiPihakBerkepentingan}" cellpadding="0" cellspacing="0" id="line2">
                                        <display:column title="Nama">
                                            <c:if test="${line2.jenis.kod eq 'PM' && line2.aktif eq 'Y'}">
                                                ${line2.pihak.nama}<br>No.KP/Syarikat: ${line2.pihak.noPengenalan}
                                            </c:if>
                                        </display:column>
                                        <display:column title="Bahagian Dimiliki">
                                            <c:if test="${line2.jenis.kod eq 'PM' && line2.aktif eq 'Y'}">
                                                ${line2.syerPembilang}/${line2.syerPenyebut}
                                            </c:if>
                                        </display:column>
                                    </display:table>
                                </display:column>
                                <display:column title="Kategori Kegunaan Tanah" style="vertical-align:top">
                                    ${tbl1.hakmilik.kategoriTanah.nama}
                                </display:column>
                                <display:column title="Syarat Nyata" style="vertical-align:top">
                                    <c:if test="${tbl1.hakmilik.syaratNyata.syarat ne null}">${tbl1.hakmilik.syaratNyata.syarat}</c:if>
                                    <c:if test="${tbl1.hakmilik.syaratNyata.syarat eq null}">Tiada</c:if>
                                </display:column>
                                <display:column title="Sekatan Kepentingan" style="vertical-align:top">
                                    <c:if test="${tbl1.hakmilik.sekatanKepentingan.sekatan ne null}">${tbl1.hakmilik.sekatanKepentingan.sekatan}</c:if>
                                    <c:if test="${tbl1.hakmilik.sekatanKepentingan.sekatan eq null}">Tiada</c:if>
                                </display:column>
                                <display:column title="Bebanan Berdaftar" style="vertical-align:top">
                                    <c:set var="flag" value="true" />
                                    <c:forEach items="${tbl1.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                                        <c:if test="${(((senarai.jenis.kod eq 'PG') ||(senarai.jenis.kod eq 'PJ') ||(senarai.jenis.kod eq 'PJK') ||
                                                      (senarai.jenis.kod eq 'PKA') ||(senarai.jenis.kod eq 'PKD') ||(senarai.jenis.kod eq 'PKL') ||
                                                      (senarai.jenis.kod eq 'PKS') ||(senarai.jenis.kod eq 'PMG') ||(senarai.jenis.kod eq 'PI') ||
                                                      (senarai.jenis.kod eq 'PY')) && senarai.aktif eq 'Y') }">
                                            <c:out value="${senarai.jenis.nama}"/><br/><br/>
                                            <c:set var="flag" value="false" />
                                        </c:if>
                                    </c:forEach>
                                    <c:if test="${flag eq 'true'}">
                                        Tiada
                                    </c:if>
                                </display:column>
                            </display:table>

                        </td></tr>
                        <tr><td><b>4. BUTIR-BUTIR PEMBANGUNAN YANG DICADANGKAN </b></td></tr>
                        <tr>
                        <td>
                            <display:table class="tablecloth" name="${actionBean.listHakmilik}" cellpadding="0" cellspacing="0"  id="line">
                                <display:column title="No" sortable="true">${line_rowNum}</display:column>
                                <display:column property="kategoriTanah.nama" title="Jenis Kategori" />
                                <display:column property="kegunaanTanah.nama" title="Kegunaan Tanah" />
                                <display:column property="bilanganPlot" title="Bilangan Unit"/>
                                <display:column title="Luas Per-Unit"> 
                                    <fmt:formatNumber  pattern="#,##0.0000" value="${line.luas}"/>&nbsp;${line.kodUnitLuas.nama}&nbsp;
                                </display:column>  
                                <display:column property="catatan" title="Butir-Butir Pembangunan" /> 
                            </display:table>
                        </td>
                        </tr> 

                        <c:if test = "${actionBean.permohonan.kodUrusan.kod eq 'SBMS' || actionBean.permohonan.kodUrusan.kod eq 'TSPSS'}">
                            <tr>
                            <td>
                                <display:table class="tablecloth" name="${actionBean.listKerajaanAndRizab}" cellpadding="0" cellspacing="0"  id="line">
                                    <display:column title="No" sortable="true">${line_rowNum}</display:column>                                  
                                    <display:column title="Luas"> 
                                        <fmt:formatNumber  pattern="#,##0.0000" value="${line.luas}"/>&nbsp;${line.kodUnitLuas.nama}&nbsp;
                                    </display:column> 
                                    <display:column property="kegunaanTanahLain" title="Kegunaan Tanah" />                                 
                                </display:table>
                            </td>
                            </tr>
                        </c:if>

                        <c:set var="aa" value="0" />
                        <tr><td><b>5. KEADAAN DAN KEDUDUKAN TANAH </b></td></tr>
                        <tr><td>
                            <table border="0">
                                <c:forEach items="${actionBean.laporanTanah2}" var="line" begin="0">
                                    <c:set var="aa" value="${aa+1}" />
                                    <tr>
                                    <td id="tdLabel"><font color="black">5.${aa})  Kedudukan Tanah :</font></td>
                                    <td id="tdDisplay">
                                        <c:if test="${line.hakmilikPermohonan.lokasi ne null}">${line.hakmilikPermohonan.lokasi}&nbsp;</c:if>
                                        <c:if test="${line.hakmilikPermohonan.lokasi eq null}">Tiada &nbsp;</c:if>
                                        </td>
                                        </tr>
                                        <tr>
                                        <td id="tdLabel"><font color="black">Keadaan Tanah :</font></td>
                                        <td id="tdDisplay">
                                        ${line.kodKeadaanTanah.nama}
                                    </td>
                                    </tr>
                                    <tr>
                                    <td id="tdLabel"><font color="black">Tanaman :</font></td>
                                    <td id="tdDisplay">
                                        <c:if test="${line.usaha eq 'Y'}">Ya</c:if>
                                        <c:if test="${line.usaha eq 'T'}">Tiada</c:if>
                                        </td>
                                        </tr>
                                        <tr>
                                        <td id="tdLabel"><font color="black">Bangunan :</font></td>
                                        <td id="tdDisplay">
                                        <c:if test="${line.adaBangunan eq 'Y'}">Ya</c:if>
                                        <c:if test="${line.adaBangunan eq 'T'}">Tiada</c:if>
                                        </td>
                                        </tr>
                                </c:forEach>
                            </table>
                        </td>
                        </tr>

                        <tr>
                        <td>
                            <table border="0" width="96%" cellspacing="5">
                                <tr>
                                    <c:set var="aa" value="${aa+1}" />
                                <td width="20%" valign="top"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5.${aa}) Keadaan sekeliling tanah adalah seperti berikut :</b></td>
                                <td width="80%">
                                    <table class="tablecloth">
                                        <c:set var="aa" value="0" />
                                        <c:forEach items="${actionBean.laporanTanahList2}" var="line1" begin="0">
                                            <tr>
                                            <th colspan="6">Id Hakmilik : ${line1.hakmilikPermohonan.hakmilik.idHakmilik}</th>
                                            </tr>
                                            <tr>
                                            <th>&nbsp;</th><th>Jenis Tanah</th><th>Nombor Lot</th><th>Kegunaan</th><th>Catatan</th>
                                            </tr>
                                            <c:forEach items="${actionBean.laporanTanahSempadanList2}" var="line" begin="0">
                                                <c:if test="${line1.idLaporan eq line.laporanTanah.idLaporan}">
                                                    <c:if test="${line.jenisSempadan eq 'U'}">
                                                        <tr>
                                                        <th>Utara</th>
                                                            <%--<td>${line.hakmilik.idHakmilik}</td> --%>
                                                        <td>
                                                            <c:if test="${line.milikKerajaan eq 'Y'}">Kerajaan</c:if>
                                                            <c:if test="${line.milikKerajaan eq 'T'}">Milik</c:if>
                                                            <c:if test="${line.milikKerajaan eq 'R'}">Rizab</c:if>
                                                            </td>
                                                            <td>${line.kodLot.nama} ${line.noLot}</td>
                                                        <td>${line.kodKategoriTanah.nama}</td>
                                                        <td>${line.catatan}</td>
                                                        </tr>
                                                    </c:if>
                                                    <c:if test="${line.jenisSempadan eq 'S'}">
                                                        <tr>
                                                        <th>Selatan</th>
                                                            <%--<td>${line.hakmilik.idHakmilik}</td> --%>
                                                        <td>
                                                            <c:if test="${line.milikKerajaan eq 'Y'}">Kerajaan</c:if>
                                                            <c:if test="${line.milikKerajaan eq 'T'}">Milik</c:if>
                                                            <c:if test="${line.milikKerajaan eq 'R'}">Rizab</c:if>
                                                            </td>
                                                            <td>${line.kodLot.nama} ${line.noLot}</td>
                                                        <td>${line.guna}</td>
                                                        <td>${line.catatan}</td>
                                                        </tr>
                                                    </c:if>
                                                    <c:if test="${line.jenisSempadan eq 'B'}">
                                                        <tr>
                                                        <th>Barat</th>
                                                            <%--<td>${line.hakmilik.idHakmilik}</td> --%>
                                                        <td>
                                                            <c:if test="${line.milikKerajaan eq 'Y'}">Kerajaan</c:if>
                                                            <c:if test="${line.milikKerajaan eq 'T'}">Milik</c:if>
                                                            <c:if test="${line.milikKerajaan eq 'R'}">Rizab</c:if>
                                                            </td>
                                                            <td>${line.kodLot.nama} ${line.noLot}</td>
                                                        <td>${line.kodKategoriTanah.nama}</td>
                                                        <td>${line.catatan}</td>
                                                        </tr>
                                                    </c:if>
                                                    <c:if test="${line.jenisSempadan eq 'T'}">
                                                        <tr>
                                                        <th>Timur</th>
                                                            <%--<td>${line.hakmilik.idHakmilik}</td> --%>
                                                        <td>
                                                            <c:if test="${line.milikKerajaan eq 'Y'}">Kerajaan</c:if>
                                                            <c:if test="${line.milikKerajaan eq 'T'}">Milik</c:if>
                                                            <c:if test="${line.milikKerajaan eq 'R'}">Rizab</c:if>
                                                            </td>
                                                            <td>${line.kodLot.nama} ${line.noLot}</td>
                                                        <td>${line.kodKategoriTanah.nama}</td>
                                                        <td>${line.catatan}</td>
                                                        </tr>
                                                    </c:if>
                                                </c:if>
                                            </c:forEach>
                                            <c:set var="aa" value="${aa+1}" />
                                        </c:forEach>
                                    </table>
                                </td>
                                </tr>
                            </table>
                        </td></tr>

                        <tr><td><b>6. HURAIAN PENTADBIR TANAH <font style="text-transform: uppercase">${actionBean.pejTanah}</font></b></td></tr>
                        <tr><td>
                            <table cellspacing="10">
                                <c:set var="i" value="1" />
                                <c:forEach items="${actionBean.senaraiKandungan}" var="line">
                                    <tr><td valign="top"><b>6.${i} </b></td>
                                    <td>${line.kandungan} </td>
                                    </tr>
                                    <c:set var="i" value="${i+1}" />
                                </c:forEach>
                            </table>
                        </td>
                        </tr>

                        <tr><td><b>7. SYOR PENTADBIR TANAH  <font style="text-transform: uppercase">${actionBean.pejTanah}</font></b></td></tr>
                        <tr><td>
                            <table cellspacing="10">
                                <tr>
                                <td valign="top"><b>7.1 </b></td>
                                <td>${actionBean.syorPentadbir1}</td>
                                </tr>
                            </table>
                        </td>
                        </tr>

                        <c:if test="${!plot}">
                            <c:set var="i" value="0"/>
                            <c:forEach items="${actionBean.hakmilikPermohonanList}" var="line" begin="0">
                                <tr><td>
                                    <table border="0" width="97%" cellspacing="8" align="center">
                                        <tr><td width="5%">
                                            <c:if test="${i eq 0}">
                                                <b> a) </b>
                                            </c:if>
                                            <c:if test="${i eq 1}">
                                                <b> b) </b>
                                            </c:if>
                                            <c:if test="${i eq 2}">
                                                <b> c) </b>
                                            </c:if>
                                            <c:if test="${i eq 3}">
                                                <b> d) </b>
                                            </c:if>
                                            <c:if test="${i eq 4}">
                                                <b> e) </b>
                                            </c:if>
                                            <c:if test="${i eq 5}">
                                                <b> f) </b>
                                            </c:if>
                                            <c:if test="${i eq 6}">
                                                <b> g) </b>
                                            </c:if>
                                        </td>
                                        <td><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Lot </font></td>
                                        <td><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                        <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">${line.kodKegunaanTanah.nama}&nbsp;</font></td>
                                        </tr>
                                        <tr>
                                        <td></td>
                                        <td width="34%" valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Luas</font></td>
                                        <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                        <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">
                                                Mengikut Pelan Tatatur <br/> ${actionBean.pelantatatur}</font></td>

                                        </tr>
                                        <tr>
                                        <td></td>
                                        <td><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Tempoh Pegangan</font></td>
                                        <td><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                        <td><c:if test="${line.hakmilik.pegangan eq 'S'}">Selama - lamanya</c:if>
                                            <c:if test="${line.hakmilik.pegangan eq 'P'}">
                                                <c:if test="${line.hakmilik.tempohPegangan ne null && line.hakmilik.tempohPegangan ne 0}">${line.hakmilik.tempohPegangan} <b>tahun </b></c:if>
                                                <c:if test="${line.hakmilik.tempohPegangan eq null || line.hakmilik.tempohPegangan eq 0}">Tiada</c:if>
                                            </c:if>
                                        </td>
                                        </tr>
                                        <tr>
                                        <td></td>
                                        <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Jenis Hakmilik Sementara</font></td>
                                        <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                        <td valign="top"><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">${line.kodHakmilikSementara.nama}&nbsp;</font></td>
                                        </tr>
                                        <tr>
                                        <td></td>
                                        <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Jenis Hakmilik Tetap</font></td>
                                        <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                        <td valign="top"><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">${line.kodHakmilikTetap.nama}&nbsp;</font></td>
                                        </tr>
                                        <tr>
                                        <td></td>
                                        <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Kadar Cukai</font></td>
                                        <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                        <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">${line.keteranganCukaiBaru} <%--${actionBean.hp.cukaiPerMeterPersegi} &nbsp; sen semeter persegi atau sebahagian daripadanya tertakluk kepada minima RM ${actionBean.hp.cukaiPerLot}&nbsp; satu lot--%></font></td>
                                        </tr>
                                        <c:if test="${!(actionBean.permohonan.kodUrusan.kod eq 'PPCB' || actionBean.permohonan.kodUrusan.kod eq 'PPCBA' || actionBean.permohonan.kodUrusan.kod eq 'PYTN' || actionBean.permohonan.kodUrusan.kod eq 'PPCS')}">
                                            <tr>
                                            <td></td>
                                            <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Kadar Premium</font></td>
                                            <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                            <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">${line.keteranganKadarPremium} <%--Mengikut nilaian dari Jabatan Penilaian dan Perkhidmatan Harta dan Kaedah-kaedah Tanah Negeri--%></font></td>
                                            </tr>
                                            <tr>
                                            <td></td>
                                            <td><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Kos Sumbangan Infrastuktur</font></td>
                                            <td><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                            <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">
                                                    ${line.kosInfra}
                                                </font> Sehektar</td>
                                            </tr>
                                        </c:if>
                                        <tr>
                                        <td></td>
                                        <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Kadar Bayaran Upah Ukur dan Batu Sempadan</font></td>
                                        <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                        <td valign="top"><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">${line.keteranganKadarUkur}</font></td>
                                        </tr>
                                        <tr>
                                        <td></td>
                                        <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Kadar Bayaran Pendaftaran dan Penyediaan Hakmilik Tetap/Sementara</font></td>
                                        <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                        <td valign="top"><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">${line.keteranganKadarDaftar}</font></td>
                                        </tr>
                                        <tr>
                                        <td></td>
                                        <td><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Kategori</font></td>
                                        <td><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                        <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">${line.kategoriTanahBaru.nama}&nbsp;</font></td>
                                        </tr>
                                        <tr>
                                        <td></td>
                                        <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Syarat Nyata</font></td>
                                        <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                        <td valign="top"><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;"> ${line.syaratNyataBaru.kod} - ${line.syaratNyataBaru.syarat}&nbsp;</font></td>
                                        </tr>
                                        <tr>
                                        <td></td>
                                        <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Sekatan Kepentingan</font></td>
                                        <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                        <td valign="top"><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;"> ${line.sekatanKepentinganBaru.kod} - ${line.sekatanKepentinganBaru.sekatan}&nbsp;</font></td>
                                        </tr>
                                    </table>
                                </td></tr>
                                <c:set var="i" value="${i+1}" />
                            </c:forEach>

                        </c:if>
                        <c:if test="${plot}"> 
                            <tr><td><b>Bangunan </b></td></tr>  
                            <tr>
                            <td>        
                                <div align ="left">                             
                                    <display:table class="tablecloth" name="${actionBean.listKatgBangunanB01}" cellpadding="0" cellspacing="0"  id="line">
                                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                                        <display:column property="kategoriTanah.nama" title="Kategori" />
                                        <display:column property="kegunaanTanah.nama" title="Kegunaan Tanah" />     
                                        <display:column title="Kemaskini Maklumat">
                                            <div align="center">                                      
                                                <img alt='Paparan Maklumat' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                                     id='rem_${line_rowNum}' onclick="dopopup('${line.idPlot}', 'N');
        return false;"  onmouseover="this.style.cursor = 'pointer';">                                                                                    
                                            </div>
                                        </display:column>                                           
                                    </display:table>                                         
                                </div>
                            </td>
                            </tr>                          

                            </br>
                            <tr><td><b>Pertanian </b></td></tr>  
                            <tr>
                            <td>        
                                <div align ="left">                             
                                    <display:table class="tablecloth" name="${actionBean.listKatgPertanian}" cellpadding="0" cellspacing="0"  id="line">
                                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                                        <display:column property="kategoriTanah.nama" title="Kategori" />  
                                        <display:column property="kegunaanTanah.nama" title="Kegunaan Tanah" />   
                                        <display:column title="Kemaskini Maklumat">
                                            <div align="center">                                      
                                                <img alt='Paparan Maklumat' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                                     id='rem_${line_rowNum}' onclick="dopopup('${line.idPlot}', 'N');
        return false;"  onmouseover="this.style.cursor = 'pointer';">                                                                                    
                                            </div>
                                        </display:column>                                            
                                    </display:table>  
                                </div>
                            </td>
                            </tr>

                            </br>
                            <tr><td><b>Industri/Perusahaan </b></td></tr>  
                            <tr>
                            <td>        
                                <div align ="left">                             
                                    <display:table class="tablecloth" name="${actionBean.listKatgIndustri}" cellpadding="0" cellspacing="0"  id="line">
                                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                                        <display:column property="kategoriTanah.nama" title="Kategori" />    
                                        <display:column property="kegunaanTanah.nama" title="Kegunaan Tanah" />   
                                        <display:column title="Kemaskini Maklumat">
                                            <div align="center">                                      
                                                <img alt='Paparan Maklumat' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                                     id='rem_${line_rowNum}' onclick="dopopup('${line.idPlot}', 'N');
        return false;"  onmouseover="this.style.cursor = 'pointer';">                                                                                    
                                            </div>
                                        </display:column>                                            
                                    </display:table>  
                                </div>
                            </td>
                            </tr>                          
                        </c:if>

                        <%--
                        <c:if test="${plot}">
                            <c:set var="i" value="0"/>
                            <c:forEach items="${actionBean.senaraiPlot}" var="line" begin="0">
                                <tr><td>
                                        <table border="0" width="97%" cellspacing="8" align="center">
                                            <tr><td width="5%">
                                                    <c:if test="${i eq 0}">
                                                        <b> a) </b>
                                                    </c:if>
                                                    <c:if test="${i eq 1}">
                                                        <b> b) </b>
                                                    </c:if>
                                                    <c:if test="${i eq 2}">
                                                        <b> c) </b>
                                                    </c:if>
                                                    <c:if test="${i eq 3}">
                                                        <b> d) </b>
                                                    </c:if>
                                                    <c:if test="${i eq 4}">
                                                        <b> e) </b>
                                                    </c:if>
                                                    <c:if test="${i eq 5}">
                                                        <b> f) </b>
                                                    </c:if>
                                                    <c:if test="${i eq 6}">
                                                        <b> g) </b>
                                                    </c:if>
                                                </td>
                                               
                                                <td><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Lot </font></td>
                                                <td><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                                <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">${line.kegunaanTanah.nama}</font></td>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td width="34%" valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Luas</font></td>
                                                <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                                <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">Mengikut Pelan Tatatur <br/>
                                                    ${actionBean.pelantatatur}</font></td>
                                            </tr>

                                            <tr>
                                                <td></td>
                                                <td><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Tempoh Pegangan</font></td>
                                                <td><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                                    <c:if test="${line.pegangan eq 'S'}">
                                                    <td>Selama - lamanya</td>
                                                </c:if>
                                                <c:if test="${line.pegangan eq 'P'}">
                                                    <td>${line.tempohPegangan} tahun</td> 
                                                </c:if>
                                            </tr>

                                            <tr>
                                                <td></td>
                                                <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Jenis Hakmilik Sementara</font></td>
                                                <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                                <td valign="top"><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">${line.kodHakmilikSementara.nama}&nbsp;</font></td>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Jenis Hakmilik Tetap</font></td>
                                                <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                                <td valign="top"><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">${line.kodHakmilikTetap.nama}&nbsp;</font></td>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Kadar Cukai</font></td>
                                                <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                                <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">${line.keteranganCukaiBaru}</font></td>
                                            </tr>
                                            <c:if test="${!(actionBean.permohonan.kodUrusan.kod eq 'PPCB' || actionBean.permohonan.kodUrusan.kod eq 'PPCBA' || actionBean.permohonan.kodUrusan.kod eq 'PYTN' || actionBean.permohonan.kodUrusan.kod eq 'PPCS')}">
                                                <tr>
                                                    <td></td>
                                                    <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Kadar Premium</font></td>
                                                    <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                                    <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">
                                                        <s:textarea name="premium" value="${line.keteranganKadarPremium}" rows="5" cols="100" class="normal_text" style="border:none; background:none; overflow:hidden; readonly:true"/>                                                        
                                                        </font></td>
                                                </tr>
                                                <tr>
                                                    <td></td>
                                                    <td><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Kos Sumbangan Infrastuktur</font></td>
                                                    <td><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                                    <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">
                                                        <c:if test="${line.kosInfra eq null}">
                                                            Tiada
                                                        </c:if>
                                                        <c:if test="${line.kosInfra ne null}">
                                                            ${line.kosInfra}</font> Sehektar
                                                        </c:if>
                                                    </td>
                                                </tr>
                                            </c:if>
                                            <tr>
                                                <td></td>
                                                <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Kadar Bayaran Upah Ukur dan Batu Sempadan</font></td>
                                                <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                                <td valign="top"><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">${line.keteranganKadarUkur}</font></td>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Kadar Bayaran Pendaftaran dan Penyediaan Hakmilik Tetap/Sementara</font></td>
                                                <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                                <td valign="top"><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">${line.keteranganKadarDaftar}</font></td>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Kategori</font></td>
                                                <td><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                                <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">${line.kategoriTanah.nama}&nbsp;</font></td>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Syarat Nyata</font></td>
                                                <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                                <td valign="top"><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;"> ${line.kodSyaratNyata.kod} - ${line.kodSyaratNyata.syarat}&nbsp;</font></td>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Sekatan Kepentingan</font></td>
                                                <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                                <td valign="top"><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;"> ${line.kodSekatanKepentingan.kod} - ${line.kodSekatanKepentingan.sekatan}&nbsp;</font></td>
                                            </tr>
                                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'SBMS'}">
                                                <tr>
                                                    <td></td>
                                                    <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">Ingatan</font></td>
                                                    <td valign="top"><font style="font-size:13px; color:black; font-family:Tahoma; font-weight:bold;">:</font></td>
                                                    <td valign="top"><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;"> ${line.peringatan}&nbsp;</font></td>
                                                </tr>
                                            </c:if>
                                        </table>
                                    </td></tr>
                                    <c:set var="i" value="${i+1}"/>
                                </c:forEach>
                            </c:if>
                        --%>
                        <tr><td>
                            <table cellspacing="10">
                                <c:set var="i" value="2" />
                                <c:forEach items="${actionBean.senaraiKandungan7}" var="line" begin="1">
                                    <tr><td valign="top"><b> 7.${i} </b></td>
                                    <td>${line.kandungan} </td>
                                    </tr>
                                    <c:set var="i" value="${i+1}" />
                                </c:forEach>
                            </table>
                        </td>
                        </tr>

                        <tr><td><b>8. HURAIAN PENGARAH TANAH DAN GALIAN NEGERI SEMBILAN DARUL KHUSUS</b></td></tr>
                        <tr><td>
                            <table cellspacing="10">
                                <c:set var="i" value="1" />
                                <c:forEach items="${actionBean.senaraiKandungan8}" var="line">
                                    <tr><td valign="top"><b> 8.${i} </b></td>
                                    <td>${line.kandungan} </td>
                                    </tr>
                                    <c:set var="i" value="${i+1}" />
                                </c:forEach>
                            </table>
                        </td>
                        </tr>

                        <tr><td><b>9. SYOR PENGARAH TANAH DAN GALIAN NEGERI SEMBILAN DARUL KHUSUS</b></td></tr>
                        <tr><td>
                            <table cellspacing="10">
                                <c:set var="i" value="1" />
                                <c:forEach items="${actionBean.senaraiKandungan9}" var="line">
                                    <tr><td valign="top"><b> 9.${i} </b></td>
                                    <td>${line.kandungan} </td>
                                    </tr>
                                    <c:set var="i" value="${i+1}" />
                                </c:forEach>
                            </table> 
                        </td>
                        </tr>

                        <tr><td><b>10. KEPUTUSAN</b></td></tr>
                        <tr>
                        <td>

                            <table cellspacing="10">
                                <tr><td valign="top"><b>10.1 </b></td>
                                <td>Dibentangkan untuk pertimbangan dan keputusan Majlis Mesyuarat Kerajaan Negeri Sembilan Darul Khusus</td></tr>
                            </table>
                        </td>
                        </tr>
                    </table>
                </div>
            </c:if>
        </fieldset>
    </div>
</c:otherwise>
                    </c:choose>
                    </c:if>
</s:form>
