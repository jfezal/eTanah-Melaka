<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.io.*,etanah.Configuration" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">


    function regenerateBill(tableid) {
        try {
            var table = document.getElementById(tableid);
            var rowCount = table.rows.length;
            for (var i = 1; i < rowCount; i++) {

                //alert(table.rows[i].cells[0].childNodes[0].data);
                //table.rows[i].cells[0].childNodes[0].data= "4."+(i+1);
                table.rows[i].cells[2].childNodes[0].id = i + 1;
            }

        } catch (e) {
            alert("Error in regenerateBill");
        }
    }

    function addRow3(tableID1) {
        if (validateRow3(tableID1) == true) {
            document.getElementById("rowCount3").value = 1;
            var table = document.getElementById(tableID1);

            var rowCount1 = table.rows.length;

            var row = table.insertRow(rowCount1);

            var cell2 = row.insertCell(0);
            cell2.innerHTML = "<b>" + "3." + (rowCount1 + 1) + "</b>";

            var cell1 = row.insertCell(1);
            var element1 = document.createElement("textarea");


            element1.t = "asaspermohonan" + (rowCount1 + 1);
            element1.rows = 5;
            element1.cols = 140;
            element1.className = "normal_text"

            element1.name = "asaspermohonan" + (rowCount1 + 1);
            element1.id = "asaspermohonan" + (rowCount1 + 1);
            cell1.appendChild(element1);

            document.getElementById("rowCount3").value = rowCount1 + 1;
            var cell2 = row.insertCell(2);
            var e1 = document.createElement("img");
            e1.t = "button" + (rowCount1 + 1);
            e1.src = "${pageContext.request.contextPath}/images/not_ok.gif";
            e1.alt = "klik untuk batal";
            e1.align = "top"
            e1.value = "Hapus";
            e1.id = (rowCount1 + 1);
            e1.onclick = function () {
                delete3(tableID1);
            };
            cell2.appendChild(e1);

        }
    }
    function validateRow3(tableID2) {
        var table = document.getElementById(tableID2);
        var rowCount = table.rows.length;
        if (rowCount > 0) {
            var txtarea = "asaspermohonan" + rowCount;
            var test = document.getElementById(txtarea).value;
            if (test == "") {
                alert("Sila Simpan Sebelum Menambah Maklumat Berikutnya.");
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }
    function delete3(recordNo)
    {
        if (confirm('Adakah anda pasti untuk menghapus data ini?')) {
            var i = document.getElementById("rowCount3").value;
            var x = document.getElementById('dataTable3').rows[i - 1].cells;

            if (i == 1) {
                document.getElementById('dataTable3').deleteRow(i - 1);
            } else {
                document.getElementById('dataTable3').deleteRow(recordNo - 1);
            }

            document.getElementById("rowCount3").value = i - 1;

            var url = '${pageContext.request.contextPath}/strata/kertas_MMKNew';
            $.get(url,
                    function (data) {
                        $('#page_div').html(data);
                    }, 'html');


            regenerateBill('dataTable3');
        }

    }
    function deleteRow3(recordNo, idKandungan)
    {
        // alert("recordNo:"+recordNo);
        if (confirm('Adakah anda pasti untuk menghapus data ini?')) {

            var i = document.getElementById("rowCount3").value;
            var x = document.getElementById('dataTable3').rows[i - 1].cells;
            var idKandungan = x[0].innerHTML;
            // alert(idKandungan);

            if (i == 1) {
                document.getElementById('dataTable3').deleteRow(i - 1);
            } else {
                document.getElementById('dataTable3').deleteRow(recordNo - 1);
            }

            document.getElementById("rowCount3").value = i - 1;
            var url = '${pageContext.request.contextPath}/strata/kertas_MMKNew?deleteAsasMohon&idKandungan='
                    + idKandungan;

            $.get(url,
                    function (data) {
                        $('#page_div').html(data);
                    }, 'html');

            regenerateBill('dataTable3');
        }
    }

    function addRow1(tableID1) {
        if (validateRow1(tableID1) == true) {
            document.getElementById("rowCount1").value = 1;
            var table = document.getElementById(tableID1);

            var rowCount1 = table.rows.length;
            var row = table.insertRow(rowCount1);

            var cell2 = row.insertCell(0);
            cell2.innerHTML = "<b>" + "1." + (rowCount1 + 1) + "</b>";

            var cell1 = row.insertCell(1);
            var element1 = document.createElement("textarea");
            element1.t = "tujua" + (rowCount1 + 1);
            element1.rows = 5;
            element1.cols = 140;
            element1.name = "tujua" + (rowCount1 + 1);
            element1.id = "tujua" + (rowCount1 + 1);
            cell1.appendChild(element1);

            document.getElementById("rowCount1").value = rowCount1 + 1;
            var cell2 = row.insertCell(2);
            var e1 = document.createElement("img");
            e1.t = "button" + (rowCount1 + 1);
            e1.src = "${pageContext.request.contextPath}/images/not_ok.gif";
            e1.alt = "klik untuk batal";
            e1.align = "top";
            e1.value = "Batal";
            e1.id = (rowCount1 + 1);
            e1.onclick = function () {
                delete1(e1.id);
            };
            cell2.appendChild(e1);

        }

    }
    function validateRow1(tableID2) {
        var table = document.getElementById(tableID2);
        var rowCount = table.rows.length;
        if (rowCount > 0) {
            var txtarea = "tujua" + rowCount;
            var test = document.getElementById(txtarea).value;
            if (test == "") {
                alert("Sila Simpan Sebelum Menambah Maklumat Berikutnya.");
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }
    function delete1(recordNo)
    {
        if (confirm('Adakah anda pasti untuk menghapus data ini?')) {
            var i = document.getElementById("rowCount1").value;
            var x = document.getElementById('dataTable1').rows[i - 1].cells;

            if (i == 1) {
                document.getElementById('dataTable1').deleteRow(i - 1);
            } else {
                document.getElementById('dataTable1').deleteRow(recordNo - 1);
            }

            document.getElementById("rowCount1").value = i - 1;

            var url = '${pageContext.request.contextPath}/strata/kertas_MMKNew';
            $.get(url,
                    function (data) {
                        $('#page_div').html(data);
                    }, 'html');


            regenerateBill('dataTable1');
        }

    }


    function deleteRow1(recordNo, idKandungan)
    {

        if (confirm('Adakah anda pasti untuk menghapus data ini?')) {

            var i = document.getElementById("rowCount1").value;
            var x = document.getElementById('dataTable1').rows[i - 1].cells;
            // var idKandungan = x[0].innerHTML;
            //  alert(idKandungan);
            if (i == 1) {
                document.getElementById('dataTable1').deleteRow(i - 1);
            } else {
                document.getElementById('dataTable1').deleteRow(recordNo - 1);
            }

            document.getElementById("rowCount1").value = i - 1;

            var url = '${pageContext.request.contextPath}/strata/kertas_MMKNew?deleteTujuan&idKandungan='
                    + idKandungan;

            $.get(url,
                    function (data) {
                        $('#page_div').html(data);
                    }, 'html');

            regenerateBill('dataTable1');
        }

    }
    function addRow2(tableID1) {
        if (validateRow2(tableID1) == true) {
            document.getElementById("rowCount2").value = 1;
            var table = document.getElementById(tableID1);

            var rowCount1 = table.rows.length;
            var row = table.insertRow(rowCount1);

            var cell2 = row.insertCell(0);
            cell2.innerHTML = "<b>" + "2." + (rowCount1 + 1) + "</b>";

            var cell1 = row.insertCell(1);
            var element1 = document.createElement("textarea");
            element1.t = "latarblkg" + (rowCount1 + 1);
            element1.rows = 5;
            element1.cols = 140;
            element1.name = "latarblkg" + (rowCount1 + 1);
            element1.id = "latarblkg" + (rowCount1 + 1);
            cell1.appendChild(element1);

            document.getElementById("rowCount2").value = rowCount1 + 1;
            var cell2 = row.insertCell(2);
            var e1 = document.createElement("img");
            e1.t = "button" + (rowCount1 + 1);
            e1.src = "${pageContext.request.contextPath}/images/not_ok.gif";
            e1.alt = "klik untuk batal";
            e1.align = "top";
            e1.value = "Batal";
            e1.id = (rowCount1 + 1);
            e1.onclick = function () {
                deleteRow2(e1.id);
            };
            cell2.appendChild(e1);
        }

    }
    function validateRow2(tableID2) {
        var table = document.getElementById(tableID2);
        var rowCount = table.rows.length;
        if (rowCount > 0) {
            var txtarea = "latarblkg" + rowCount;
            var test = document.getElementById(txtarea).value;
            if (test == "") {
                alert("Sila Simpan Sebelum Menambah Maklumat Berikutnya.");
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }
    function delete2(recordNo)
    {
        if (confirm('Adakah anda pasti untuk menghapus data ini?')) {
            var i = document.getElementById("rowCount2").value;
            var x = document.getElementById('dataTable2').rows[i - 1].cells;

            if (i == 1) {
                document.getElementById('dataTable2').deleteRow(i - 1);
            } else {
                document.getElementById('dataTable2').deleteRow(recordNo - 1);
            }

            document.getElementById("rowCount2").value = i - 1;

            var url = '${pageContext.request.contextPath}/strata/kertas_MMKNew';
            $.get(url,
                    function (data) {
                        $('#page_div').html(data);
                    }, 'html');


            regenerateBill('dataTable2');
        }

    }

    function deleteRow2(recordNo, idKandungan)
    {

        if (confirm('Adakah anda pasti untuk menghapus data ini?')) {

            var i = document.getElementById("rowCount2").value;
            var x = document.getElementById('dataTable2').rows[i - 1].cells;
            // var idKandungan = x[0].innerHTML;
            //  alert(idKandungan);
            if (i == 1) {
                document.getElementById('dataTable2').deleteRow(i - 1);
            } else {
                document.getElementById('dataTable2').deleteRow(recordNo - 1);
            }

            document.getElementById("rowCount2").value = i - 1;

            var url = '${pageContext.request.contextPath}/strata/kertas_MMKNew?deleteLatarbelakang&idKandungan='
                    + idKandungan;

            $.get(url,
                    function (data) {
                        $('#page_div').html(data);
                    }, 'html');

            regenerateBill('dataTable2');
        }

    }


    function addRow5(tableID1) {
        if (validateRow5(tableID1) == true) {
            document.getElementById("rowCount5").value = 1;
            var table = document.getElementById(tableID1);

            var rowCount1 = table.rows.length;
            var row = table.insertRow(rowCount1);

            var cell2 = row.insertCell(0);
            cell2.innerHTML = "<b>" + "4." + (rowCount1 + 1) + "</b>";

            var cell1 = row.insertCell(1);
            var element1 = document.createElement("textarea");
            element1.t = "syorpngarah" + (rowCount1 + 1);
            element1.rows = 5;
            element1.cols = 140;
            element1.name = "syorpngarah" + (rowCount1 + 1);
            element1.id = "syorpngarah" + (rowCount1 + 1);
            cell1.appendChild(element1);

            document.getElementById("rowCount5").value = rowCount1 + 1;
            var cell2 = row.insertCell(2);
            var e1 = document.createElement("img");
            e1.t = "button" + (rowCount1 + 1);
            e1.src = "${pageContext.request.contextPath}/images/not_ok.gif";
            e1.alt = "klik untuk batal";
            e1.align = "top";
            e1.value = "Batal";
            e1.id = (rowCount1 + 1);
            e1.onclick = function () {
                delete5(e1.id);
            };
            cell2.appendChild(e1);

        }

    }
    function validateRow5(tableID2) {
        var table = document.getElementById(tableID2);
        var rowCount = table.rows.length;
        if (rowCount > 0) {
            var txtarea = "syorpngarah" + rowCount;
            var test = document.getElementById(txtarea).value;
            if (test == "") {
                alert("Sila Simpan Sebelum Menambah Maklumat Berikutnya.");
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }
    function delete5(recordNo)
    {
        if (confirm('Adakah anda pasti untuk menghapus data ini?')) {
            var i = document.getElementById("rowCount5").value;
            var x = document.getElementById('dataTable5').rows[i - 1].cells;

            if (i == 1) {
                document.getElementById('dataTable5').deleteRow(i - 1);
            } else {
                document.getElementById('dataTable5').deleteRow(recordNo - 1);
            }

            document.getElementById("rowCount5").value = i - 1;

            var url = '${pageContext.request.contextPath}/strata/kertas_MMKNew?showForm3';
            $.get(url,
                    function (data) {
                        $('#page_div').html(data);
                    }, 'html');


            regenerateBill('dataTable5');
        }

    }


    function deleteRow5(recordNo, idKandungan)
    {

        if (confirm('Adakah anda pasti untuk menghapus data ini?')) {

            var i = document.getElementById("rowCount5").value;
            var x = document.getElementById('dataTable5').rows[i - 1].cells;
            // var idKandungan = x[0].innerHTML;
            //  alert(idKandungan);
            if (i == 1) {
                document.getElementById('dataTable5').deleteRow(i - 1);
            } else {
                document.getElementById('dataTable5').deleteRow(recordNo - 1);
            }

            document.getElementById("rowCount5").value = i - 1;

            var url = '${pageContext.request.contextPath}/strata/kertas_MMKNew?deleteSyorPengarah&idKandungan='
                    + idKandungan;

            $.get(url,
                    function (data) {
                        $('#page_div').html(data);
                    }, 'html');

            regenerateBill('dataTable5');
        }

    }
    function addRow6(tableID1) {
        if (validateRow6(tableID1) == true) {
            document.getElementById("rowCount6").value = 1;
            var table = document.getElementById(tableID1);

            var rowCount1 = table.rows.length;
            var row = table.insertRow(rowCount1);

            var cell2 = row.insertCell(0);
            cell2.innerHTML = "<b>" + "5." + (rowCount1 + 1) + "</b>";

            var cell1 = row.insertCell(1);
            var element1 = document.createElement("textarea");
            element1.t = "kputusan" + (rowCount1 + 1);
            element1.rows = 5;
            element1.cols = 140;
            element1.name = "kputusan" + (rowCount1 + 1);
            element1.id = "kputusan" + (rowCount1 + 1);
            cell1.appendChild(element1);

            document.getElementById("rowCount6").value = rowCount1 + 1;
            var cell2 = row.insertCell(2);
            var e1 = document.createElement("img");
            e1.t = "button" + (rowCount1 + 1);
            e1.src = "${pageContext.request.contextPath}/images/not_ok.gif";
            e1.alt = "klik untuk batal";
            e1.align = "top";
            e1.value = "Batal";
            e1.id = (rowCount1 + 1);
            e1.onclick = function () {
                deleteRow6(e1.id);
            };
            cell2.appendChild(e1);
        }

    }
    function validateRow6(tableID2) {
        var table = document.getElementById(tableID2);
        var rowCount = table.rows.length;
        if (rowCount > 0) {
            var txtarea = "kputusan" + rowCount;
            var test = document.getElementById(txtarea).value;
            if (test == "") {
                alert("Sila Simpan Sebelum Menambah Maklumat Berikutnya.");
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }
    function delete6(recordNo)
    {
        if (confirm('Adakah anda pasti untuk menghapus data ini?')) {
            var i = document.getElementById("rowCount6").value;
            var x = document.getElementById('dataTable6').rows[i - 1].cells;

            if (i == 1) {
                document.getElementById('dataTable6').deleteRow(i - 1);
            } else {
                document.getElementById('dataTable6').deleteRow(recordNo - 1);
            }

            document.getElementById("rowCount6").value = i - 1;

            var url = '${pageContext.request.contextPath}/strata/kertas_MMKNew?showForm3';
            $.get(url,
                    function (data) {
                        $('#page_div').html(data);
                    }, 'html');


            regenerateBill('dataTable6');
        }

    }

    function deleteRow6(recordNo, idKandungan)
    {

        if (confirm('Adakah anda pasti untuk menghapus data ini?')) {

            var i = document.getElementById("rowCount6").value;
            var x = document.getElementById('dataTable6').rows[i - 1].cells;
            // var idKandungan = x[0].innerHTML;
            //  alert(idKandungan);
            if (i == 1) {
                document.getElementById('dataTable6').deleteRow(i - 1);
            } else {
                document.getElementById('dataTable6').deleteRow(recordNo - 1);
            }

            document.getElementById("rowCount6").value = i - 1;

            var url = '${pageContext.request.contextPath}/strata/kertas_MMKNew?deleteKeputusan&idKandungan='
                    + idKandungan;

            $.get(url,
                    function (data) {
                        $('#page_div').html(data);
                    }, 'html');

            regenerateBill('dataTable6');
        }

    }


    function simpanData(event, f) {
        if (confirm('Adakah anda pasti untuk simpan data ini?')) {
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url, q,
                    function (data) {
                        $('#page_div').html(data);

                    }, 'html');
        }

    }

    function simpan(event, f) {

        if (confirm('Adakah anda pasti untuk simpan data ini?')) {
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url, q,
                    function (data) {
                        $('#page_div').html(data);

                    }, 'html');
        }

    }


</script>

<s:form name="form1" beanclass="etanah.view.strata.KertasMMKNewActionBean">
    <s:messages/>
    <s:errors/> 
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Tajuk</legend>
            <div class="content" align="left">
                <table border="0">
                    <!--<tr><td colspan="4" align="center"><b>( MAJLIS MESYUARAT KERAJAAN )</b></td></tr>-->
                    <tr>
                        <td colspan="4"><strong>&nbsp;</strong></td>
                    </tr>
                    <tr><td width="100px"><strong>&nbsp;</strong></td>
                        <td colspan="2"><b><font style="text-transform: uppercase"><center>${actionBean.tajuk}</center></font></b></td>
                        <td width="100px"><strong>&nbsp;</strong></td>
                    </tr>
                    <tr>
                        <td colspan="4"><strong>&nbsp;</strong></td>
                    </tr>
                </table>
            </div>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>1. Tujuan</legend>
            <div class="content" align="left">
                <table border="0">

                    <tr valign="top">
                        <td height="23">&nbsp;</td>
                        <td colspan="3">

                            <table id ="dataTable1" border="0">

                                <c:set var="i" value="1" />
                                <c:set var="bil" value="0" />
                                <c:if test="${fn:length(actionBean.senaraiLaporanKandungan1) == 0 or actionBean.senaraiLaporanKandungan1 eq null}">
                                    <tr>
                                        <td valign="top"><b>1.1</b></td>
                                        <td>
                                            <font style="text-transform: uppercase">
                                            <s:textarea class="normal_text" name="tujua1" id="tujua1"  rows="5" cols="140" >${actionBean.tujuan1}</s:textarea>
                                                </font>

                                            </td>
                                            <td>
                <!--                                            <img alt='Klik Untuk Hapus' title='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                     id="${i}" onclick="deleteRow1(${i},${line.idKandungan})" onmouseover="this.style.cursor='pointer';">-->
                                        </td>
                                    </tr>
                                    <s:hidden name="rowCount1" value="${i}" id="rowCount1"/>

                                </c:if>

                                <c:forEach items="${actionBean.senaraiLaporanKandungan1}" var="line">
                                    <tr style="font-weight:bold">
                                        <td style="display:none">${line.idKandungan}</td>
                                        <td><c:out value="1.${bil+1}"/></td>
                                        <td>

                                            <font style="text-transform: uppercase">
                                            <s:textarea class="normal_text" name="tujua${i}" id="tujua${i}"  rows="5" cols="140" >${line.kandungan}</s:textarea>
                                                </font>

                                            </td>

                                            <td>
                                                <img alt='Klik Untuk Hapus' title='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                 id="${i}" onclick="deleteRow1(${i},${line.idKandungan})" onmouseover="this.style.cursor = 'pointer';">
                                        </td>

                                    </tr>

                                    <c:set var="i" value="${i+1}" />
                                    <c:set var="bil" value="${bil+1}" />
                                </c:forEach>
                                <s:hidden name="rowCount1" value="${i-1}" id="rowCount1"/>
                            </table></td>
                    </tr>
                    <tr valign="top">
                        <td height="23">&nbsp;</td>
                        <td colspan="3">
                            <c:if test="${maklumat1}">
                        <center>
                            <s:button class="btn" value="Tambah" name="add" onclick="addRow1('dataTable1')"/>
                            <s:button name="simpanTujuan" id="save" value="Simpan" class="btn" onclick="simpanData(this.name, this.form)"/>
                        </center>
                    </c:if>
                    </td>
                    </tr>
                </table>
            </div>
        </fieldset>
    </div>
    <p></p>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>2. Ringkasan Permohonan</legend>
            <div class="content" align="left">

                <tr>
                <p> <label>a.Pemohon :</label>
                    ${actionBean.pemohon.pihak.nama}
                    &nbsp;
                </p>
                <p><label>alamat :</label>   
                    ${actionBean.pemohon.pihak.alamat1}<br>
                </p>
                <c:if test="${actionBean.pemohon.pihak.alamat2 ne null}">
                    <p><label> &nbsp;</label>
                        ${actionBean.pemohon.pihak.alamat2}
                    </p> 
                </c:if>
                <c:if test="${actionBean.pemohon.pihak.alamat3 ne null}">
                    <p><label> &nbsp;</label>
                        ${actionBean.pemohon.pihak.alamat3}
                    </p> 
                </c:if>
                <c:if test="${actionBean.pemohon.pihak.alamat4 ne null}">
                    <p><label> &nbsp;</label>
                        ${actionBean.pemohon.pihak.alamat4}
                    </p> 
                </c:if>
                <c:if test=" ${actionBean.pemohon.pihak.poskod ne null}">
                    <p><label>Poskod :</label>
                        ${actionBean.pemohon.pihak.poskod}
                    </p> 
                </c:if>
                <c:if test="${actionBean.pemohon.pihak.negeri.nama ne null}">
                    <p><label>Negeri :</label>
                        ${actionBean.pemohon.pihak.negeri.nama}
                    </p> 
                </c:if>
                <c:if test="${actionBean.permohonanStrata.namaSkim ne null}">
                    <p><label>b. Nama Skim :</label>
                        ${actionBean.permohonanStrata.namaSkim}
                    </p> 
                </c:if>

                <c:if test="${actionBean.mohonHakmilik.hakmilik.kodHakmilik.kod ne null}">
                    <p><label>c. No. Hakmilik :</label>
                        ${actionBean.mohonHakmilik.hakmilik.kodHakmilik.kod} ${actionBean.mohonHakmilik.hakmilik.noHakmilik}
                    </p> 
                </c:if>
                <c:if test="${actionBean.mohonHakmilik.hakmilik.lot.nama ne null}">
                    <p><label>Lot / PT :</label>
                        ${actionBean.mohonHakmilik.hakmilik.lot.nama} ${actionBean.mohonHakmilik.hakmilik.noLot}
                    </p> 
                </c:if>

                <c:if test="${actionBean.mohonHakmilik.hakmilik.bandarPekanMukim.nama ne null}">
                    <p><label>Mukim :</label>
                        ${actionBean.mohonHakmilik.hakmilik.bandarPekanMukim.nama}
                    </p> 
                </c:if>
                <c:if test="${actionBean.hakmilikPihak ne null}">
                    <p><label>d. Pemilik Tanah Berdaftar :</label>
                        <c:forEach items="${actionBean.hakmilikPihak}" var="line">
                            ${line.nama}
                        </c:forEach>
                    </p> 
                </c:if>
                <c:if test="${actionBean.mohonHakmilik.hakmilik.luas ne null}">
                    <p><label>e. Luas Tanah :</label>
                        ${actionBean.mohonHakmilik.hakmilik.luas} ${actionBean.mohonHakmilik.hakmilik.kodUnitLuas.nama}
                    </p> 
                </c:if>
                <c:if test="${actionBean.mohonHakmilik.hakmilik.cukai ne null}">
                    <p><label>f. Cukai :</label>
                        RM ${actionBean.mohonHakmilik.hakmilik.cukai}
                    </p> 
                </c:if>
                <c:if test=" ${actionBean.tempohPegangan1 ne false}">
                    <p><label>Tempoh Pajakan :</label>
                        ${actionBean.mohonHakmilik.hakmilik.tempohPegangan} Tahun
                    </p> 
                </c:if>
                <c:if test=" ${actionBean.tempohPegangan1 eq true}">
                    <p><label>Tempoh Pajakan :</label>
                        Tiada
                    </p> 
                </c:if>
                <c:if test=" ${actionBean.tempohPegangan1 eq 'true'}">
                    <p><label>Tempoh Pajakan1 :</label>
                        Tiada
                    </p> 
                </c:if>
                <c:if test="${actionBean.mohonHakmilik.hakmilik.kodHakmilik.nama ne null}">
                    <p><label>g. Taraf Pemilikan :</label>
                        ${actionBean.mohonHakmilik.hakmilik.kodHakmilik.nama}
                    </p> 
                </c:if>

                <c:if test="${actionBean.tempohPegangan1 eq false}">
                    <p><label>Tempoh Pajakan :</label>
                        ${actionBean.mohonHakmilik.hakmilik.tempohPegangan} Tahun
                    </p> 
                </c:if>
                <%--<c:if test=" ${actionBean.tempohPegangan1 eq 'true'}">--%>
                <c:if test="${actionBean.tempohPegangan1 eq true}">
                    <p><label>Tempoh Pajakan :</label>
                        Tiada
                    </p> 
                </c:if>
                <c:if test=" ${actionBean.tarikhMesyuarat2 eq true}">
                    <p><label>Habis Tempoh :</label>
                        ${actionBean.tarikhMesyuarat}
                    </p> 
                </c:if>
                <c:if test="${actionBean.tarikhMesyuarat2 ne true}">
                    <p><label>Habis Tempoh :</label>
                        Tiada
                    </p> 
                </c:if>


                <c:if test=" ${actionBean.mohonHakmilik.hakmilik.kegunaanTanah.nama ne null}">
                    <p><label>h. Kategori Kegunaan Tanah :</label>
                        ${actionBean.mohonHakmilik.hakmilik.kegunaanTanah.nama}
                    </p> 
                </c:if>
                <legend>i. Tanggungan</legend>
                <table border="0">
                    <tr valign="top">
                        <td height="23">&nbsp;</td>
                        <td colspan="3">
                    <center>
                        <table id ="dataTable1" border="0">
                            <tr>
                                <td>
                                    <font style="text-transform: uppercase">
                                    <s:textarea class="normal_text" name="tujuan" id="tujuan"  rows="5" cols="150" >${actionBean.mohonKertasKandungan.kandungan}</s:textarea>
                                        </font>
                                    </td>
                                </tr>
                            </table>
                        </center>
                        </td>
                        </tr>
                        <tr valign="top">
                            <td height="23">&nbsp;</td>
                            <td colspan="3">
                        <center>
                        <s:button name="simpanTanggungan" id="save" value="Simpan" class="btn" onclick="simpan(this.name, this.form)"/>
                    </center>
                    </td>
                    </tr>


                </table>
                <legend>j. Bil. Blok/ Tingkat/ Petak</legend>
                <div class="content" align="center">
                    <display:table style="width:80%" class="tablecloth" name="${actionBean.pBangunanL}"  id="line">
                        <display:column title="Bil. Blok" property="nama" />
                        <display:column title="No. Tingkat">1 - ${line.bilanganTingkat}</display:column>
                        <display:column title="Jumlah Tingkat" property="bilanganTingkat" />
                        <display:column title="No. Petak" > 1- ${line.bilanganPetak}</display:column>
                        <display:column title="Jumlah Petak" property="bilanganPetak" />
                    </display:table>
                </div>




                <c:if test=" ${actionBean.mohonHakmilik.hakmilik.sekatanKepentingan.sekatan ne null}">
                    <p><label>Sekatan Kepentingan :</label>
                        ${actionBean.mohonHakmilik.hakmilik.sekatanKepentingan.sekatan}
                    </p> 
                </c:if>
                <c:if test=" ${actionBean.mohonHakmilik.hakmilik.tempohPegangan ne null}">
                    <p><label>Tempoh Pajakan :</label>
                        ${actionBean.mohonHakmilik.hakmilik.tempohPegangan} Tahun
                    </p> 
                </c:if>
                <c:if test=" ${actionBean.pBangunanL[0].kodKegunaanBangunan.nama ne null}">
                    <p><label>k. Jenis Bangunan :</label>
                        ${actionBean.pBangunanL[0].kodKegunaanBangunan.nama}
                    </p> 
                </c:if>
                <c:if test=" ${actionBean.latarbelakang1 ne null}">
                    <p><label>i. Kelulusan CCF/CF :</label>
                        ${actionBean.bngnTotal}
                    </p> 
                    <p><label> &nbsp;</label>
                        ${actionBean.permohonanStrata.cfNoSijil}
                    </p> 
                </c:if>
                <c:if test="${actionBean.bngnTotal ne null}">
                    <p><label>m. Bil. Bangunan :</label>
                        ${actionBean.bngnTotal}

                    </p> 
                </c:if>

                <c:if test="${actionBean.petakTotal ne null}">
                    <p><label>n. Bil. Petak :</label>
                        ${actionBean.petakTotal}
                    </p> 
                </c:if>
                <c:if test="${actionBean.permohonanStrata.cfNoSijil ne null}">
                    <p><label>o. Harga Jualan Petak :</label>
                        RM <fmt:formatNumber value="${actionBean.permohonanStrata.hargaPetak}" pattern="#,##0.00"/>
                    </p> 
                </c:if>

                <br>

            </div>
        </fieldset>
    </div>

    <c:if test="${asas}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>3. Asas-asas Pertimbangan</legend>
                <div class="content" align="left">
                    <table border="0">

                        <tr valign="top">
                            <td height="23">&nbsp;</td>
                            <td colspan="3">
                                <!--                            <s:errors/> -->
                                <table id ="dataTable3" border="0">

                                <c:set var="i" value="1" />
                                <c:set var="bil" value="0" />

                                <c:forEach items="${actionBean.senaraiLaporanKandungan3}" var="line">
                                    <tr style="font-weight:bold">
                                        <td style="display:none">${line.idKandungan}</td>
                                        <td><c:out value="3.${bil+1}"/></td>
                                    <c:if test="${i ne 2}">
                                        <td>
                                            <font style="text-transform: uppercase">
                                        <s:textarea class="normal_text" name="asaspermohonan${i}" id="asaspermohonan${i}"  rows="5" cols="140" >${line.kandungan}</s:textarea>
                                            </font>

                                        </td>
                                    </c:if>
                                    <c:if test="${i eq 2}">
                                        <td>
                                            <font style="text-transform: uppercase">
                                        <s:textarea class="normal_text" name="asaspermohonan${i}" id="asaspermohonan${i}"  rows="8" cols="140" >${line.kandungan}</s:textarea>
                                            </font>

                                        </td>
                                    </c:if>

                                    <td>
                                        <img alt='Klik Untuk Hapus' title='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                             id="${i}" onclick="deleteRow3(${i},${line.idKandungan})" onmouseover="this.style.cursor = 'pointer';">
                                    </td>

                                </tr>

                                    <c:set var="i" value="${i+1}" />
                                    <c:set var="bil" value="${bil+1}" />
                                </c:forEach>
                                <s:hidden name="rowCount3" value="${i-1}" id="rowCount3"/>
                            </table></td>
                    </tr>
                    <tr valign="top">
                        <td height="23">&nbsp;</td>
                        <td colspan="3">
                                <c:if test="${maklumat2}">
                                    <s:button class="btn" value="Tambah" name="add" onclick="addRow3('dataTable3')"/>
                                    <s:button name="simpanAsasMohon" id="save" value="Simpan" class="btn" onclick="simpanData(this.name, this.form)"/>
                                </c:if>
                            </td>
                        </tr>
                    </table>
                </div>
            </fieldset>
        </div>
                            </c:if>
                            <c:if test="${syor}">
                                <fieldset class="aras1">
                                    <legend>4. Syor dan Perakuan Timbalan Pengarah Tanah Dan Galian (Pendaftaran)</legend>
                                    <div class="content" align="left">
                                        <table border="0">
                        
                                            <tr valign="top">
                                                <td height="23">&nbsp;</td>
                                                <td colspan="3">
                        
                                                    <table id ="dataTable5" border="0">
                        
                                <c:set var="i" value="1" />
                                <c:set var="bil" value="0" />
                                <c:if test="${fn:length(actionBean.senaraiLaporanKandungan5) == 0 or actionBean.senaraiLaporanKandungan5 eq null}">
                                    <tr>
                                        <td valign="top"><b>4.1</b></td>
                                        <td>
                                            <font style="text-transform: uppercase">
                                    <s:textarea class="normal_text" name="syorpngarah1" id="syorpngarah1"  rows="5" cols="140" >${actionBean.syorpengarah1}</s:textarea>
                                        </font>

                                    </td>
                                    <td>

                                    </td>
                                </tr>
                                    <s:hidden name="rowCount5" value="${i}" id="rowCount5"/>

                                </c:if>
                                <c:forEach items="${actionBean.senaraiLaporanKandungan5}" var="line">
                                    <tr style="font-weight:bold">
                                        <td style="display:none">${line.idKandungan}</td>
                                        <td><c:out value="4.${bil+1}"/></td>
                                        <td>
                                            <font style="text-transform: uppercase">
                                    <s:textarea class="normal_text" name="syorpngarah${i}" id="syorpngarah${i}"  rows="5" cols="140">${line.kandungan}</s:textarea>
                                        </font>

                                    </td>

                                    <td>
                                        <img alt='Klik Untuk Hapus' title='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                         id="${i}" onclick="deleteRow5(${i},${line.idKandungan})" onmouseover="this.style.cursor = 'pointer';">
                                </td>

                            </tr>

                                    <c:set var="i" value="${i+1}" />
                                    <c:set var="bil" value="${bil+1}" />
                                </c:forEach>
                                <s:hidden name="rowCount5" value="${i-1}" id="rowCount5"/>
                            </table></td>
                    </tr>
                    <tr valign="top">
                        <td height="23">&nbsp;</td>
                        <td colspan="3">
                                <c:if test="${maklumat3}">
                                    <s:button class="btn" value="Tambah" name="add" onclick="addRow5('dataTable5')"/>
                                    <s:button name="simpanSyorPengarah" id="save" value="Simpan" class="btn" onclick="simpanData(this.name,this.form)"/>
                                </c:if>
                            </td>
                        </tr>
                    </table>
                </div>
            </fieldset>
    
                            </c:if>
                            <c:if test="${keputusan}">
                                <fieldset class="aras1">
                                    <legend>5. Keputusan Pengarah Tanah dan Galian Melaka</legend>
                                    <div class="content" align="left">
                                        <table border="0">
                        
                                            <tr valign="top">
                                                <td height="23">&nbsp;</td>
                                                <td colspan="3">
                        
                                                    <table id ="dataTable6" border="0">
                        
                                <c:set var="i" value="1" />
                                <c:set var="bil" value="0" />
                                <c:if test="${fn:length(actionBean.senaraiLaporanKandungan6) == 0 or actionBean.senaraiLaporanKandungan6 eq null}">
                                    <tr>
                                        <td valign="top"><b>5.1</b></td>
                                        <td>
                                            <font style="text-transform: uppercase">
                                    <s:textarea class="normal_text" name="kputusan1" id="kputusan1"  rows="5" cols="140">${actionBean.keputusan1}</s:textarea>
                                        </font>

                                    </td>
                                    <td>

                                    </td>
                                </tr>
                                    <s:hidden name="rowCount6" value="${i}" id="rowCount6"/>

                                </c:if>
                                <c:forEach items="${actionBean.senaraiLaporanKandungan6}" var="line">
                                    <tr style="font-weight:bold">
                                        <td style="display:none">${line.idKandungan}</td>
                                        <td><c:out value="5.${bil+1}"/></td>
                                        <td>
                                            <font style="text-transform: uppercase">
                                    <s:textarea class="normal_text" name="kputusan${i}" id="kputusan${i}"  rows="5" cols="140" >${line.kandungan}</s:textarea>
                                        </font>

                                    </td>

                                    <td>
                                        <img alt='Klik Untuk Hapus' title='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                         id="${i}" onclick="deleteRow6(${i},${line.idKandungan})" onmouseover="this.style.cursor = 'pointer';">
                                </td>

                            </tr>

                                    <c:set var="i" value="${i+1}" />
                                    <c:set var="bil" value="${bil+1}" />
                                </c:forEach>
                                <s:hidden name="rowCount6" value="${i-1}" id="rowCount6"/>
                            </table></td>
                    </tr>
                    <tr valign="top">
                        <td height="23">&nbsp;</td>
                        <td colspan="3">
                                <c:if test="${maklumat4}">
                                    <s:button class="btn" value="Tambah" name="add" onclick="addRow6('dataTable6')"/>
                                    <s:button name="simpanKeputusan" id="save" value="Simpan" class="btn" onclick="simpanData(this.name,this.form)"/>
                                </c:if>
                            </td>
                        </tr>
                    </table>
                </div>
            </fieldset>
    
                            </c:if>
                            <p></p>
                        
                        </s:form>