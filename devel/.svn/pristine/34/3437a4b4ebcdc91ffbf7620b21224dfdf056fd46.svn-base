<%--
    Document   : KertasPermit_RuangUdara
    Created on : Oct 4, 2010, 12:14:31 PM
    Author     : Srinu
--%>

<%--<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>


<%--<style type="text/css">
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
</style>--%>

<script type="text/javascript">

    function addRow4(tableID2) {
        if (validateRow4(tableID2) == true) {
            document.getElementById("rowCount4").value = 1;
            var table = document.getElementById(tableID2);

            var rowCount1 = table.rows.length;
            //if(rowCount1 < 3){
            var row = table.insertRow(rowCount1);

            var cell2 = row.insertCell(0);
            cell2.innerHTML = "<b>" + "4." + (rowCount1 + 1) + "</b>";

            var cell1 = row.insertCell(1);
            var element1 = document.createElement("textarea");


            element1.t = "perakuan" + (rowCount1 + 1);
            element1.rows = 5;
            element1.cols = 100;
            element1.className = "normal_text";
            element1.name = "perakuan" + (rowCount1 + 1);
            element1.id = "perakuan" + (rowCount1 + 1);
            cell1.appendChild(element1);

            document.getElementById("rowCount4").value = rowCount1 + 1;
            var cell2 = row.insertCell(2);
            var e1 = document.createElement("img");
            e1.t = "button" + (rowCount1 + 1);
            e1.src = "${pageContext.request.contextPath}/images/not_ok.gif";
            e1.alt = "klik untuk batal";
            e1.align = "top"
            e1.value = "Hapus";
            e1.id = (rowCount1 + 1);
            e1.onclick = function () {
                cancelRow4(tableID2);
            };
            cell2.appendChild(e1);

        }//else{alert("ape kes ni")}




    }
    function validateRow4(tableID2) {
        var table = document.getElementById(tableID2);
        var rowCount = table.rows.length;
        if (rowCount > 0) {
            var txtarea = "perakuan" + rowCount;
            var test = document.getElementById(txtarea).value;
            if (test == "") {
                var count = rowCount;
                alert("Sila Simpan Maklumat Perakuan 4." + count + " Sebelum Menambah Perakuan Berikutnya.");
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }


    }


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


    function deleteRow4(recordNo, idKandungan)
    {
        // alert("recordNo:"+recordNo);
        if (confirm('Adakah anda pasti untuk menghapus data ini?')) {

            var i = document.getElementById("rowCount4").value;
            var x = document.getElementById('dataTable2').rows[i - 1].cells;
            var idKandungan = x[0].innerHTML;
            // alert(idKandungan);

            if (i == 1) {
                document.getElementById('dataTable2').deleteRow(i - 1);
            } else {
                document.getElementById('dataTable2').deleteRow(recordNo - 1);
            }

            document.getElementById("rowCount4").value = i - 1;
            var url = '${pageContext.request.contextPath}/strata/KertasPermit_RuangUdara?deleteKandungan&idKandungan='
                    + idKandungan;

            $.get(url,
                    function (data) {
                        $('#page_div').html(data);
                    }, 'html');

            regenerateBill('dataTable2');
        }
    }
    function cancelRow4(recordNo)
    {


        var i = document.getElementById("rowCount4").value;
        var x = document.getElementById('dataTable2').rows[i - 1].cells;
        var idKandungan = x[0].innerHTML;
        // alert(idKandungan);

        if (i == 1) {
            document.getElementById('dataTable2').deleteRow(i - 1);
        } else {
            document.getElementById('dataTable2').deleteRow(recordNo - 1);
        }
        var url = '${pageContext.request.contextPath}/strata/KertasPermit_RuangUdara';
        $.get(url,
                function (data) {
                    $('#page_div').html(data);
                }, 'html');

        regenerateBill('dataTable2');

    }

    function addRow3(tableID1) {
        if (validateRow3(tableID1) == true) {
            document.getElementById("rowCount3").value = 1;
            var table = document.getElementById(tableID1);

            var rowCount1 = table.rows.length;
            var row = table.insertRow(rowCount1);

            var cell2 = row.insertCell(0);
            cell2.innerHTML = "<b>" + "3." + (rowCount1 + 3) + "</b>";

            var cell1 = row.insertCell(1);
            var element1 = document.createElement("textarea");
            element1.t = "ulasan" + (rowCount1 + 1);
            element1.rows = 5;
            element1.cols = 100;
            element1.className = "normal_text";
            element1.name = "ulasan" + (rowCount1 + 1);
            element1.id = "ulasan" + (rowCount1 + 1);
            cell1.appendChild(element1);



            document.getElementById("rowCount3").value = rowCount1 + 1;
            var cell2 = row.insertCell(2);
            var e1 = document.createElement("img");
            e1.t = "button" + (rowCount1 + 1);
            e1.src = "${pageContext.request.contextPath}/images/not_ok.gif";
            e1.alt = "klik untuk batal";
            e1.align = "top";
            e1.value = "Batal";
            e1.id = (rowCount1 + 1);
            e1.onclick = function () {
                delete3(e1.id);
            };
            cell2.appendChild(e1);
        }

    }
    function validateRow3(tableID2) {
        var table = document.getElementById(tableID2);
        var rowCount = table.rows.length;
        if (rowCount > 0) {
            var txtarea = "ulasan" + rowCount;
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
            var x = document.getElementById('dataTable1').rows[i - 1].cells;

            if (i == 1) {
                document.getElementById('dataTable1').deleteRow(i - 1);
            } else {
                document.getElementById('dataTable1').deleteRow(recordNo - 1);
            }

            document.getElementById("rowCount3").value = i - 1;

            var url = '${pageContext.request.contextPath}/strata/KertasPermit_RuangUdara';
            $.get(url,
                    function (data) {
                        $('#page_div').html(data);
                    }, 'html');


            regenerateBill('dataTable1');
        }

    }


    function deleteRow3(recordNo, idKandungan)
    {

        if (confirm('Adakah anda pasti untuk menghapus data ini?')) {

            var i = document.getElementById("rowCount3").value;
            var x = document.getElementById('dataTable1').rows[i - 1].cells;
            // var idKandungan = x[0].innerHTML;
            //  alert(idKandungan);
            if (i == 1) {
                document.getElementById('dataTable1').deleteRow(i - 1);
            } else {
                document.getElementById('dataTable1').deleteRow(recordNo - 1);
            }

            document.getElementById("rowCount3").value = i - 1;

            var url = '${pageContext.request.contextPath}/strata/KertasPermit_RuangUdara?deleteKandungan&idKandungan='
                    + idKandungan;

            //     alert(url);

            $.get(url,
                    function (data) {
                        $('#page_div').html(data);
                    }, 'html');

            regenerateBill('dataTable1');

        }

    }


    function clear1() {
        document.getElementById("ulasan").value = " ";
    }

    function remove(val) {
        if (confirm('Adakah anda pasti untuk menghapus data ini?')) {
            var url = '${pageContext.request.contextPath}/strata/KertasPermit_RuangUdara=' + val;
            $.get(url,
                    function (data) {
                        $('#page_div').html(data);
                    }, 'html');
        }

    }
    $(document).ready(function () {

        var value = document.getElementById("perakuan").checked;
        if (value == true)
        {
            $('#lulus').show();
            $('#tolak').hide();

        } else
        {
            $('#tolak').show();
            $('#lulus').hide();
        }

        if ('${actionBean.permohonanPermitButirList ne null}') {
            var o = 0;
            var permit = 0;
            for (var i = 1; i <= ${fn:length(actionBean.permohonanPermitButirList)}; i++) {
                var o = $('#permit' + i).val();
                var permit = parseFloat(permit) + parseFloat(o);
            }
            $('#total').val(permit);
        }
    });
    function changekeputusan(value) {

        if (value == "Y")
        {
            $('#lulus').show();
            $('#tolak').hide();

        }
        if (value == "N")
        {
            $('#tolak').show();
            $('#lulus').hide();
        }
    }
    function simpanperakuan(event, f) {

        var q = $(f).formSerialize();
        var url = '${pageContext.request.contextPath}/strata/KertasPermit_RuangUdara?simpanPerakuan';
        $.post(url, q,
                function (data) {
                    $('#page_div').html(data);

                }, 'html');

    }

</script>

<%--Original <s:form name="form1" beanclass="etanah.view.strata.KertasPermitRuangUdaraActionBean">--%>
<s:form beanclass="etanah.view.strata.KertasPermitRuangUdaraActionBean">
    <s:messages/>
    <s:hidden name="kandunganK.kertas.idKertas"/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend></legend>
            <div class="content" align="left">
                <table width="883" height="586" border="0">
                    <tr>
                        <td height="30" colspan="4"><div align="center"><strong>KERTAS PERTIMBANGAN PERMOHONAN PERMIT RUANG UDARA </strong><strong><br>
                                    BERKAITAN DENGAN HAKMILIK STRATA </strong></div></td>
                    </tr>
                    <tr>
                        <td width="12" height="23">&nbsp;</td>
                        <td width="20">&nbsp;</td>
                        <td width="190">&nbsp;</td>
                        <td width="633">&nbsp;</td>
                    </tr>
                    <tr>
                        <td height="23"><strong>1.</strong></td>
                        <td colspan="3"><strong>TUJUAN</strong></td>
                    </tr>
                    <tr>
                        <td height="23">&nbsp;</td>
                        <td colspan="3">Kertas ini disediakan bagi mempertimbangkan permohonan permit ruang udara daripada ${actionBean.pihak1.nama} 
                            yang beralamat di
                            <c:if test="${actionBean.pihak1.alamat1 ne null}">
                                ${actionBean.pihak1.alamat1},
                            </c:if>
                            <c:if test="${actionBean.pihak1.alamat2 ne null}">
                                ${actionBean.pihak1.alamat2},
                            </c:if>
                            <c:if test="${actionBean.pihak1.alamat3 ne null}">
                                ${actionBean.pihak1.alamat3},
                            </c:if>
                            <c:if test="${actionBean.pihak1.alamat4 ne null}">
                                ${actionBean.pihak1.alamat4},
                            </c:if>
                            ${actionBean.pihak1.poskod} ${actionBean.pihak1.negeri.nama}.

                            <%--
                                                         ${actionBean.pihak1.alamat2} ${actionBean.pihak1.alamat3} ${actionBean.pihak1.alamat4}
                                                        ${actionBean.pihak1.poskod} ${actionBean.pihak1.negeri.nama}--%>

                        </td>
                    </tr>
                    <tr>
                        <td height="23">&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td height="23"><strong>2.</strong></td>
                        <td colspan="3"><strong>PERIHAL TANAH </strong></td>
                    </tr>
                    <tr>
                        <td height="23">&nbsp;</td>
                        <td><strong>2.1</strong></td>
                        <td><strong>Jenis dan no Hakmilik </strong></td>
                        <td>: ${actionBean.hakmilik.kodHakmilik.nama} ${actionBean.nHakmilik}</td>
                    </tr>
                    <tr>
                        <td height="23">&nbsp;</td>
                        <td>&nbsp;</td>
                        <td><strong>Penggunaan Tanah </strong></td>
                        <td>: ${actionBean.hakmilik.kategoriTanah.nama}</td>
                    </tr>
                    <tr>
                        <td height="23">&nbsp;</td>
                        <td>&nbsp;</td>
                        <td><strong>Lot/PT</strong></td>
                        <td>: ${actionBean.hakmilik.lot.nama} ${actionBean.nLot} </td>
                    </tr>
                    <tr>
                        <td height="23">&nbsp;</td>
                        <td>&nbsp;</td>
                        <td><strong>Kawasan Bandar / Mukim </strong></td>
                        <td>: <font style="text-transform: uppercase"> ${actionBean.hakmilik.bandarPekanMukim.nama}
                            <c:if test="${actionBean.hakmilik.seksyen ne null}">
                                , ${actionBean.hakmilik.seksyen.nama}
                            </c:if>
                            </font>
                        </td>
                    </tr>
                    <tr>
                        <td height="23">&nbsp;</td>
                        <td>&nbsp;</td>
                        <td><strong>Hasil Tahunan </strong></td>
                        <td>: RM ${actionBean.hakmilik.cukai}</td>
                    </tr>
                    <tr>
                        <td height="23">&nbsp;</td>
                        <td>&nbsp;</td>
                        <td><strong>Syarat Nyata</strong></td>
                        <td>
                            <c:if test="${actionBean.syarat eq null }">: tiada </c:if>
                            <c:if test="${actionBean.syarat ne null}">: ${actionBean.hakmilik.syaratNyata.syarat}</c:if>
                            </td>
                        </tr>
                        <tr>
                            <td height="23">&nbsp;</td>
                            <td>&nbsp;</td>
                            <td><strong>Sekatan Kepentingan </strong></td>
                            <td>
                            <c:if test="${actionBean.sekatan eq null }">: tiada </c:if>
                            <c:if test="${actionBean.sekatan ne null}">: ${actionBean.hakmilik.sekatanKepentingan.sekatan} </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td height="23">&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td height="23">&nbsp;</td>
                            <td><strong>2.2</strong></td>
                            <td colspan="2"><strong>Tanah-tanah yang bersempadan dengan tanah yang dipohon adalah seperti berikut :-</strong></td>
                        </tr>
                        <tr>
                            <td height="23">&nbsp;</td>
                            <td>&nbsp;</td>
                            <td colspan="2"><table width="50%" border="0">
                                    <tr><td width="50"><strong>Utara</strong> </td>
                                        <td width="20">:</td>
                                        <td><%--${actionBean.laporanTanah.sempadanUtaraJenis} /--%>
                                        ${actionBean.laporanTanah.sempadanUtaraNoLot}
                                        - ${actionBean.laporanTanah.sempadanUtaraKegunaan} </td>
                                </tr>
                                <tr><td width="50"><strong>Selatan</strong></td>
                                    <td width="20">:</td>
                                    <td><%--${actionBean.laporanTanah.sempadanSelatanJenis} /--%>
                                        ${actionBean.laporanTanah.sempadanSelatanNoLot}
                                        - ${actionBean.laporanTanah.sempadanSelatanKegunaan} </td>
                                </tr>
                                <tr><td width="50"><strong>Timur</strong></td>
                                    <td width="20">:</td>
                                    <td><%--${actionBean.laporanTanah.sempadanTimurJenis} /--%>
                                        ${actionBean.laporanTanah.sempadanTimurNoLot}
                                        - ${actionBean.laporanTanah.sempadanTimurKegunaan} </td>
                                </tr>
                                <tr><td width="50"><strong>Barat</strong></td>
                                    <td width="20">:</td>
                                    <td><%--${actionBean.laporanTanah.sempadanBaratJenis} /--%>
                                        ${actionBean.laporanTanah.sempadanBaratNoLot}
                                        - ${actionBean.laporanTanah.sempadanBaratKegunaan} </td>
                                </tr>
                            </table></td>
                    </tr>
                    <tr>
                        <td height="23">&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                    </tr>
                    <c:if test="${actionBean.stage1}">
                        <c:if test="${DI}">
                            <tr>
                                <td height="23"><strong>3.</strong></td>
                                <td colspan="3"><strong>BILANGAN PERMIT YANG PERLU DIKELUARKAN KE ATAS</strong></td>
                            </tr>
                            <tr>
                                <td height="23">&nbsp;</td>
                                <td><strong>3.1</strong></td>
                                <td colspan="2">&nbsp;</td>
                            </tr>
                            <tr>
                                <td height="23">&nbsp;</td>
                                <td></td>
                                <td colspan="2">
                                    <display:table class="tablecloth" name="${actionBean.permohonanPermitButirList}"cellpadding="0" cellspacing="0"
                                                   requestURI="/strata/KertasPermit_RuangUdara" id="line" >

                                        <display:column title="Bil." sortable="true">${line_rowNum}
                                            <s:hidden name="bil2" id="permit${line_rowNum}" value="${line.bilPermit}"/>
                                        </display:column>
                                        <display:column property="noBlok" title="BLOK (M)"/>
                                        <display:column property="noTingkat" title="TINGKAT" />
                                        <display:column property="noPetak" title="PETAK" />
                                        <display:column property="jenisStrukTambah" title="JENIS STRUKTUR" />
                                        <display:column property="kedudukanStrukTambah" title="KEDUDUKAN TERUNJUR" />
                                        <display:column property="bilPermit" title="BIL. PERMIT" /></td>
                                        <display:footer >
                                        <td colspan="6" align="right" style="text-align: right">Jumlah Permit :</td>
                                        <td>
                                            <s:text id="total" name="total" disabled="true"/>
                                        </td>
                                    </display:footer>

                                </display:table>
                            </tr>
                            <tr>
                                <td height="23">&nbsp;</td>
                                <td>&nbsp;</td>
                                <td colspan="2">&nbsp;</td>
                            </tr>
                            <tr>
                                <td height="23">&nbsp;</td>
                                <td><b>3.2</b></td>
                                <td colspan="2">${actionBean.ulasanDefault}</td>
                            </tr>
                            <tr>
                                <td height="23">&nbsp;</td>
                                <td colspan="3">
                                    <s:errors/>
                                    <table id ="dataTable1" border="0">
                                        <c:set var="i" value="1" />
                                        <c:set var="bil" value="2" />


                                        <c:forEach items="${actionBean.senaraiKertasKandungan}" var="line">
                                            <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="3.${bil+1}"/></td>
                                                <td>
                                                    <font style="text-transform: uppercase">
                                                    <c:if test="${edit}">
                                                        <s:textarea name="ulasan${i}" id="ulasan${i}"  rows="5" cols="100" class="normal_text">${line.kandungan}</s:textarea>
                                                    </c:if>
                                                    <c:if test="${read}">
                                                        <s:textarea readonly="${read}"  name="ulasan${i}" id="ulasan${i}"  rows="5" cols="100" class="normal_text">${line.kandungan}</s:textarea>
                                                    </c:if>

                                                    </font>
                                                    <%--    <c:if test="${edit}">
                                                            <s:button class="btn" value="Hapus" name="delete" onclick="deleteRow3(${i},${line.idKandungan})"/>
                                                        </c:if>--%>
                                                </td>
                                                <c:if test="${edit}">
                                                    <td>
                                                        <img alt='Klik Untuk Hapus' title='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                             id="${i}" onclick="deleteRow3(${i},${line.idKandungan})" onmouseover="this.style.cursor = 'pointer';">
                                                    </td>
                                                </c:if>
                                            </tr>
                                            <c:set var="i" value="${i+1}" />
                                            <c:set var="bil" value="${bil+1}" />
                                        </c:forEach>
                                        <s:hidden name="rowCount3" value="${i-1}" id="rowCount3"/>
                                    </table></td>
                            </tr>
                            <tr>
                                <td height="23">&nbsp;</td>
                                <td colspan="3">
                                    <c:if test="${edit}">
                                        <s:button class="btn" value="Tambah" name="add" onclick="addRow3('dataTable1')"/>
                                        <s:button name="SimpanHahal" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form,this.name,'page_div')"/>
                                    </c:if>
                                </td>
                            </tr>
                        </c:if>
                    </c:if>
                    <c:if test="${actionBean.stage2}">
                        <c:if test="${DI}">
                            <tr>
                                <td height="23"><strong>3.</strong></td>
                                <td colspan="3"><strong>BILANGAN PERMIT YANG PERLU DIKELUARKAN KE ATAS</strong></td>
                            </tr>
                            <tr>
                                <td height="23">&nbsp;</td>
                                <td><strong>3.1</strong></td>
                                <td colspan="2">&nbsp;</td>
                            </tr>
                            <tr>
                                <td height="23">&nbsp;</td>
                                <td></td>
                                <td colspan="2">
                                    <display:table class="tablecloth" name="${actionBean.permohonanPermitButirList}"cellpadding="0" cellspacing="0"
                                                   requestURI="/strata/KertasPermit_RuangUdara" id="line" >

                                        <display:column title="Bil." sortable="true">${line_rowNum}
                                            <%--  <s:hidden name="x" class="x${line_rowNum -1}"value="${line.pihak.idPihak}"/>--%>
                                            <s:hidden name="bil2" id="permit${line_rowNum}" value="${line.bilPermit}"/>
                                        </display:column>
                                        <display:column property="noBlok" title="BLOK (M)"/>
                                        <display:column property="noTingkat" title="TINGKAT" />
                                        <display:column property="noPetak" title="PETAK" />
                                        <display:column property="jenisStrukTambah" title="JENIS STRUKTUR" />
                                        <display:column property="kedudukanStrukTambah" title="KEDUDUKAN TERUNJUR" />
                                        <display:column property="bilPermit" title="BIL. PERMIT" /></td>
                                        <display:footer >
                                        <td colspan="6" align="right" style="text-align: right">Jumlah Permit :</td>
                                        <td>
                                            <s:text id="total" name="total" readonly="true"/>
                                        </td>
                                    </display:footer>

                                </display:table>
                            </tr>
                            <tr><td>&nbsp;</td> </tr>
                            <tr>
                                <td height="23">&nbsp;</td>
                                <td><b>3.2</b></td>
                                <td colspan="2">${actionBean.ulasanDefault}</td>
                            </tr>
                            <tr>
                                <td height="23">&nbsp;</td>
                                <td colspan="3">
                                    <s:errors/> <table id ="dataTable1" border="0">
                                        <c:set var="i" value="1" />
                                        <c:set var="bil" value="2" />
                                        <c:forEach items="${actionBean.senaraiKertasKandungan}" var="line">
                                            <tr><td style="display:none">${line.idKandungan}</td><td height="23"  style="font-weight:bold" width="30px"><c:out value="3.${bil+1}"/></td>
                                                <td>


                                                    ${line.kandungan}


                                                </td>
                                            </tr>
                                            <c:set var="i" value="${i+1}" />
                                            <c:set var="bil" value="${bil+1}" />
                                        </c:forEach>
                                        <s:hidden name="rowCount3" value="${i-1}" id="rowCount3"/>
                                    </table></td>
                            </tr>
                        </c:if>
                    </c:if>

                    <c:if test="${actionBean.stage2}">
                        <tr>
                            <td height="23">&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <c:if test="${DI}">
                                <td height="23"><strong>4.</strong></td>
                            </c:if>
                            <c:if test="${!DI}">
                                <td height="23"><strong>3.</strong></td>
                            </c:if>
                            <td colspan="3"><strong>SYOR PENOLONG PENGARAH STRATA </strong></td>
                        </tr>
                        <c:if test="${edit}">
                            <!--                            <tr>
                                                            <td height="23">&nbsp;</td>
                                                            <td colspan="3"></td>
                                                        </tr>-->
                            <tr>
                                <td height="23">&nbsp;</td>
                                <td colspan="3">
                                    <table>
                                        <tr>
                                            <td colspan="3">Keputusan : </td>
                                            <td></td>
                                            <td><s:radio name="perakuan" id="perakuan" value="L" onclick="changekeputusan('Y')"/> Disokong </td>
                                            <td></td>
                                            <td><s:radio name="perakuan" id="perakuan" value="T" onclick="changekeputusan('N')"/> Ditolak </td>
                                        </tr>

                                    </table></td>
                            </tr>
                        </c:if>
                        <c:if test="${read}">
                            <tr>
                                <td height="23">&nbsp;</td>
                                <td colspan="3">
                                    <!--                                    <div style="display:none">-->
                                    <table>
                                        <tr>
                                            <td colspan="3">Keputusan : </td>
                                            <td></td>
                                            <td><s:radio disabled="true"name="perakuan" id="perakuan" value="L" onclick="changekeputusan('Y')"/> Disokong </td>
                                            <td></td>
                                            <td><s:radio  disabled="true"name="perakuan" id="perakuan" value="T" onclick="changekeputusan('N')"/> Ditolak </td>
                                        </tr>

                                    </table></td>
                            </tr>
                        </c:if>
                        <tr>
                            <td height="23">&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <c:if test="${DI}">
                                <td height="23"><strong>5.</strong></td>
                            </c:if>
                            <c:if test="${!DI}">
                                <td height="23"><strong>4.</strong></td>
                            </c:if>
                            <td colspan="3"><strong>PERAKUAN TIMBALAN PENDAFTAR HAKMILIK GERAN TANAH </strong></td>
                        </tr>
                        <tr>
                            <td height="23">&nbsp;</td>
                            <td colspan="3">
                                <div id="tolak">
                                    <table>

                                        <tr><td style="display:none"></td>
                                            <c:if test="${DI}">
                                                <td style="font-weight:bold"><c:out value="5.1"/></td>
                                            </c:if>
                                            <c:if test="${!DI}">
                                                <td style="font-weight:bold"><c:out value="4.1"/></td>
                                            </c:if>
                                            <td height="30"><font>Permohonan daripada <strong>${actionBean.pihak1.nama}</strong> untuk permit ruang udara
                                                bagi ${actionBean.hakmilik.lot.nama} ${actionBean.hakmilik.noLot} ${actionBean.hakmilik.bandarPekanMukim.nama},
                                                ${actionBean.hakmilik.daerah.nama} adalah <strong>ditolak</strong>.
                                                </font>
                                            </td>
                                            <td>

                                            </td>
                                        </tr>
                                    </table>
                                </div>
                                <div id="lulus">
                                    <table>

                                        <tr><td style="display:none"></td>
                                            <c:if test="${DI}">
                                                <td style="font-weight:bold"><c:out value="5.1"/></td>
                                            </c:if>
                                            <c:if test="${!DI}">
                                                <td style="font-weight:bold"><c:out value="4.1"/></td>
                                            </c:if>
                                            <td height="30"><font>Permohonan daripada <strong>${actionBean.pihak1.nama}</strong> untuk permit ruang udara
                                                bagi ${actionBean.hakmilik.lot.nama} ${actionBean.hakmilik.noLot} ${actionBean.hakmilik.bandarPekanMukim.nama},
                                                ${actionBean.hakmilik.daerah.nama} adalah <strong>disokong</strong>.
                                                </font>
                                            </td>
                                            <td>

                                            </td>
                                        </tr>
                                        <tr><td style="display:none"></td>
                                            <c:if test="${DI}">
                                                <td style="font-weight:bold"><c:out value="5.2"/></td>
                                            </c:if>
                                            <c:if test="${!DI}">
                                                <td style="font-weight:bold"><c:out value="4.2"/></td>
                                            </c:if>
                                            <td height="30"><font>Kelulusan adalah selaras seperti arahan PTG bil. 1/90 dan syarat-syarat
                                                lain adalah :-
                                                </font>
                                            </td>
                                        </tr>
                                        <tr><td height="20"></td></tr>
                                        <tr>
                                            <td colspan="3">
                                                <table>
                                                    <tr valign="top"><td width="20">&nbsp;</td>
                                                        <td style="font-weight:bold" width="20" height="30"><c:out value="a)"/></td>
                                                        <td width="300"><font style="font-weight:bold">Permit
                                                            </font>
                                                        </td>
                                                        <td width="20">
                                                            :
                                                        </td>
                                                        <td width="400">
                                                            Ruang Udara
                                                        </td>
                                                    </tr>
                                                    <tr valign="top"><td width="20">&nbsp;</td>
                                                        <td style="font-weight:bold" width="20" height="30"><c:out value="b)"/></td>
                                                        <td width="300"><font style="font-weight:bold">Kelulusan
                                                            </font>
                                                        </td>
                                                        <td width="20">
                                                            :
                                                        </td>
                                                        <td width="400">
                                                            Seperti yang dinyatakan dalam pelan Jurukur Berlesen
                                                        </td>
                                                    </tr>
                                                    <tr valign="top"><td width="20">&nbsp;</td>
                                                        <td style="font-weight:bold" width="20" height="30"><c:out value="c)"/></td>

                                                        <td width="300"><font style="font-weight:bold">Kadar
                                                            </font>
                                                        </td>
                                                        <td width="20">
                                                            :
                                                        </td>
                                                        <td width="400">
                                                            RM 50.00 bagi setiap permit x 21 tahun = RM 1050.00<br>
                                                            <strong>RM 1050.00 x ${actionBean.bilpermit} = RM ${actionBean.jumlahBayaran}</strong>

                                                        </td>
                                                    </tr>
                                                    <tr valign="top"><td width="20">&nbsp;</td>
                                                        <td style="font-weight:bold" width="20" height="30"><c:out value="d)"/></td>
                                                        <td width="300"><font style="font-weight:bold">Pemilikan (Proprietor)
                                                            </font>
                                                        </td>
                                                        <td width="20">
                                                            :
                                                        </td>
                                                        <td width="400">
                                                            ${actionBean.pihak1.nama}
                                                        </td>
                                                    </tr>
                                                    <tr valign="top"><td width="20">&nbsp;</td>
                                                        <td style="font-weight:bold" width="20" height="30"><c:out value="e)"/></td>
                                                        <td width="300"><font style="font-weight:bold">Syarat-syarat lain
                                                            </font>
                                                        </td>
                                                        <td width="20">
                                                            :
                                                        </td>
                                                        <td width="400">&nbsp;
                                                        </td>
                                                    </tr>

                                                </table></td>

                                        </tr>
                                        <tr><td height="10"></td></tr>
                                        <tr>
                                            <td colspan="3">
                                                <table>
                                                    <tr valign="top">
                                                        <td width="100">&nbsp;</td>
                                                        <td style="font-weight:bold" width="30" height="30"><c:out value="i)"/></td>
                                                        <td width="600">
                                                            Permit Ruang Udara ini diberi tempoh maksima 21 tahun.
                                                            Permohonan baru perlu dibuat selepas tamat tempoh (jika struktur berkenaan masih wujud).
                                                        </td>
                                                        <td width="50">&nbsp;</td>

                                                    </tr>
                                                    <tr><td>&nbsp;</td><td></td><td></td><td></td></tr>

                                                    <tr valign="top">
                                                        <td width="100">&nbsp;</td>
                                                        <td style="font-weight:bold" width="30" height="30"><c:out value="ii)"/></td>
                                                        <td width="600">
                                                            Permit Ruang Udara ini untuk tujuan pengeluaran dokumen hakmilik strata.
                                                        </td>
                                                        <td width="50">&nbsp;</td>

                                                    </tr>
                                                    <tr valign="top">
                                                        <td width="100">&nbsp;</td>
                                                        <td style="font-weight:bold" width="30" height="30"><c:out value="iii)"/></td>
                                                        <td width="600">
                                                            Permit Ruang Udara ini tidak boleh dipajak, dijual atau disewa kepada sesiapa jua pun
                                                            kecuali dengan kebenaran Pihak Berkuasa Negeri.
                                                        </td>
                                                        <td width="50">&nbsp;</td>

                                                    </tr>
                                                    <tr><td>&nbsp;</td><td></td><td></td><td></td></tr>
                                                    <tr valign="top">
                                                        <td width="100">&nbsp;</td>
                                                        <td style="font-weight:bold" width="30" height="30"><c:out value="iv)"/></td>
                                                        <td width="600">
                                                            Kelulusan permit ruang udara ini cuma dibenarkan seperti yang dinyatakan untuk tujuan di atas
                                                            dan tidak dibenarkan pengubahsuaian selebih itu.
                                                        </td>
                                                        <td width="50">&nbsp;</td>

                                                    </tr>
                                                    <tr><td>&nbsp;</td><td></td><td></td><td></td></tr>
                                                    <tr valign="top">
                                                        <td width="100">&nbsp;</td>
                                                        <td style="font-weight:bold" width="30" height="30"><c:out value="v)"/></td>
                                                        <td width="600">
                                                            Ruang Udara ini dibenarkan kepada tingkat seperti yang dipohon.
                                                        </td>
                                                        <td width="50">&nbsp;</td>

                                                    </tr>
                                                    <tr valign="top">
                                                        <td width="100">&nbsp;</td>
                                                        <td style="font-weight:bold" width="30" height="30"><c:out value="vi)"/></td>
                                                        <td width="600">
                                                            Tambahan baru pada struktur bangunan yang memasuki rezab Kerajaan akan dikenakan permit baru.
                                                        </td>
                                                        <td width="50">&nbsp;</td>

                                                    </tr>
                                                    <tr valign="top">
                                                        <td width="100">&nbsp;</td>
                                                        <td style="font-weight:bold" width="30" height="30"><c:out value="vii)"/></td>
                                                        <td width="600">
                                                            Permit ini hendaklah diperbaharui semula selepas tamat 21 tahun (Jika struktur berkenaan masih wujud)</td>
                                                        <td width="50">&nbsp;</td>

                                                    </tr>

                                                </table></td>
                                        </tr>

                                    </table>
                                </div>
                            </td>
                        </tr>

                        <%--<tr>
                            <td height="100">&nbsp;</td>
                            <td colspan="3">
                                <c:if test="${edit}">
                                    <s:button class="btn" value="Tambah" name="add" onclick="addRow4('dataTable2')"/>&nbsp;
                                    <s:button name="SimpanHahalA" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form,this.name,'page_div')"/>
                                </c:if>
                            </td>
                        </tr>--%>
                        <%--</c:if>--%>

                        <c:if test="${edit}">
                            <tr>
                                <td height="23">&nbsp;</td>
                                <td colspan="3">

                                    <%--<s:button name="simpanPerakuan" value="Simpan" class="btn" onclick="simpanperakuan(this.name,this.form);"/>--%>
                                    <s:button name="simpanPerakuan" value="Simpan" class="btn" onclick="doSubmit(this.form,this.name,'page_div')"/>
                                </td>

                            </tr>
                        </c:if>
                    </c:if>
                </table>
            </div>
        </fieldset>
    </div>
</s:form>
