<%--

    Author     : wazer
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>


<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/src/main/webapp/pub/scripts/datepicker.js"></script>
<!--<script language="javascript" type="text/javascript"src="${pageContext.request.contextPath}/src/main/webapp/pub/js/datetimepicker.js"></script>-->
<!--<script language="javascript" type="text/javascript"src="${pageContext.request.contextPath}/JavaScript/datetimepicker.js"></script>-->


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
    p{
        color:black;
        font-weight:normal;
        font-size:13px;
        padding-top:2px;
        padding-bottom:2px;
        line-height: 15pt ;
        text-align: justify;
    }

    .datepicker{

    }


</style>

<script language="javascript" type="text/javascript">

    function editRecord(id) {
        //        var d = $('.x'+i).val();
        //        alert(id);
        window.open("${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_awal?showFromEditRecord&idKertas=" + id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1100,height=600,scrollbars=yes");
    }

    $(document).ready(function() {
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    });

    function refreshTambah() {
        var id = '${actionBean.permohonan.idPermohonan}';
        var url = '${pageContext.request.contextPath}/utiliti/laporanTanah?checkPermohonan&idPermohonan=' + id;
        $.get(url,
        function(data) {
            $("#divLaporan").replaceWith($('#divLaporan', $(data)));
        }, 'html');
    }


    function refreshRekodKeputusan(type) {
        var url = '${pageContext.request.contextPath}/pengambilan/maklumat_laporan_awal.jsp?refreshpage&type=' + type;
        $.get(url,
        function(data) {
            $('#page_div').html(data);
        }, 'html');
        doUnBlockUI();
    }




    function save(id) {
        if (confirm('Adakah anda pasti untuk simpan?')) {
            var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_awal?simpan&idKertas=' + id;
            $.get(url,
            function(data) {
                $('#page_div').html(data);
                refreshPage();
            }, 'html');



        }
    }

    function saveKertas(event, f) {
        //        alert("e");
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url, q,
        function(data) {
            $('#page_div', opener.document).html(data);
            self.opener.refreshMainPage();
            self.close();
        }, 'html');

    }

    function viewRecordOP(id) {
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_operasi?showFormTambah&idKandungan=' + idKandungan;
        window.open(url, "eTanah", "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
    }
    function TambahLaporan() {
        //window.open("${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_operasi?popupTambahLaporanOperasi", "eTanah",
        //"status=0,toolbar=0,location=0,menubar=0,width=1100,height=600,scrollbars=yes");

        var url = "${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_awal?showFormTambah";


    }
    function validateForm() {
        //        alert("j");
        if (confirm('Adakah anda pasti untuk simpan ?')) {
            var bil = document.getElementById("recordCount").value;
            var tajuk = document.getElementById('tajuk');

            if (tajuk.value == "") {
                alert("Sila isikan Tajuk terlebih dahulu");
                $('#tajuk').focus();
                return false;
            }
            if ($('#tarikhLaporan').val() != "")
            {
                if ($('#jam').val() == "") {
                    alert("Sila masukkan jam");
                    $('#jam').focus();
                    return false;
                }
                if ($('#minit').val() == "") {
                    alert("Sila masukkan minit");
                    $('#minit').focus();
                    return false;
                }
                if ($('#ampm').val() == "") {
                    alert("Sila masukkan pagi/petang");
                    $('#ampm').focus();
                    return false;
                }

            }

            if ($('#jam').val() != "" || $('#minit').val() != "" || $('#ampm').val() != "") {
                if ($('#tarikhLaporan').val() == "") {
                    alert("Sila pilih tarikh laporan terlebih dahulu");
                    $('#tarikhLaporan').focus();
                    return false;
                }

            }

            //        if(document.getElementById("recordCount").value == "" ){
            //            alert("Sila isikan  butiran laporan terlebih dahulu");
            //            return false;
            //        }
            //
            for (var i = 0; i < bil; i++) {

                var kandungan = document.getElementById('kandungan' + i);
                if (kandungan.value == "") {
                    alert("Sila isikan butiran laporan terlebih dahulu");
                    $('#kandungan' + i).focus();
                    return false;
                }
                //                self.close();

            }
            return true;
        }


    }
    //    function refreshpage() {
    //        self.close();
    //    }
    function refreshPage() {
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_awal?refreshpage';
        $.get(url,
        function(data) {
            $('#page_div').html(data);
        }, 'html');
    }



    function addRow(tableid) {

        var table = document.getElementById(tableid);
        var rowcount = table.rows.length;
        var row = table.insertRow(rowcount);
        document.getElementById("recordCount").value = rowcount;

        var cell1 = row.insertCell(0);
        var e1 = document.createElement("INPUT");
        e1.setAttribute("type", "checkbox");
        e1.setAttribute("name", "pilih" + (rowcount - 1));
        cell1.appendChild(e1);

        var cell2 = row.insertCell(1);
        var bil = document.createTextNode(rowcount);
        cell2.appendChild(bil);


        var cell3 = row.insertCell(2);
        var e2 = document.createElement("textarea");
        e2.t = "kandungan" + (rowcount - 1);
        e2.rows = 7;
        e2.cols = 100;
        e2.name = "kandungan" + (rowcount - 1);
        e2.id = "kandungan" + (rowcount - 1);
        e2.onchange = function() {
            convertText(this, "kandungan" + (rowcount - 1));
        };
        cell3.appendChild(e2);

    }

    function deleteRow(r, tableid) {
        alert(tableid);
        if (confirm('Adakah anda pasti untuk hapus?')) {
            var i = r.parentNode.parentNode.rowIndex;
            document.getElementById('tbl').deleteRow(i);
        }
        regenerateBill(tableid);

    }

    function deleteRow1(tableid) {
        if (confirm('Adakah anda pasti untuk hapus?')) {

            var idKandungan;
            try {
                var table = document.getElementById(tableid);

                var rowCount = table.rows.length;
                var j = 0;
                for (var i = 0; i < rowCount; i++) {
                    var row = table.rows[i];
                    var chkbox = row.cells[0].childNodes[0];

                    if (null != chkbox && true == chkbox.checked) {

                        alert(i + j - 1);
                        idKandungan = document.getElementById('idKertas' + (i + j - 1));


                        if (idKandungan.value != null) {

                            var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_awal?deleteLaporan&idKandungan=' + idKandungan.value;


                        }

                        table.deleteRow(i);
                        rowCount--;
                        i--;
                        j++;

                    }
                }
            } catch (e) {
    <%--alert(e);--%>
                }

                regenerateBill(tableid);
            }
        }

        function regenerateBill(tableid) {
            try {
                var table = document.getElementById(tableid);
                var rowCount = table.rows.length;
                document.getElementById("recordCount").value = rowCount - 1;

                if (rowCount > 1) {
                    for (var i = 1; i < rowCount; i++) {
                        table.rows[i].cells[1].childNodes[0].data = i;
                        table.rows[i].cells[2].childNodes[0].name = 'kandungan' + (i - 1);
                        table.rows[i].cells[2].childNodes[0].id = 'kandungan' + (i - 1);
                    }
                }

            } catch (e) {
                alert("Error in regenerateBill : " + e);
            }
        }


        function removeDiari(idKandungan) {

            if (confirm('Adakah anda pasti untuk hapus?')) {
                var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_awal?deleteLaporan&idKandungan=' + idKandungan;
                $.get(url,
                function(data) {
                    $('#page_div').html(data);
                }, 'html');
            }
        }


        function convertText(r, t) {
            var i = r.value;
            document.getElementById(t).value = i;
        }


        function limitText(limitField, limitNum) {
            if (limitField.value.length > limitNum) {
                limitField.value = limitField.value.substring(0, limitNum);
            }
        }
        $(document).ready(function() {
            //            $(".datepicker").datepicker({dateFormat: 'dd/mm/yyyy'});
            //            $('#tarikhLaporan').val("");
        });

        function selectCheckBox(f)
        {
            var e = $('#recordCount').val();
            var idKertasTesting = $('#idKertasTesting').val();
            // alert(idKertasTesting);
 
            //                alert(e);
            //                alert(id);
            var cnt = 0;
            var data = new Array();
            for (cnt = 0; cnt < e; cnt++)
            {
                if (e == '1') {
                    if (document.form.pilih.checked) {
                        // alert(document.form.pilih[cnt].value) ;
                        data[cnt] = document.form.pilih.value;
                    }

                }
                else {
                    if (document.form.pilih[cnt].checked) {
                        //alert(document.form.pilih[cnt].value) ;
                        data[cnt] = document.form.pilih[cnt].value;
                    }
                    else {
                        data[cnt] = "T";
                    }
                }
            }
            if (confirm("Adakah anda pasti?")) {
                var q = $(f).formSerialize();
                $.post('${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_awal?deleteLaporan&item=' + data + '&idKertasTesting=' + idKertasTesting, q,
                function(data) {
                    $('#page_div').html(data);
                    //self.refreshjadualtanahKelompok();
                }, 'html');

            }
        }
</script>

<s:form beanclass="etanah.view.penguatkuasaan.MaklumatLaporanAwalActionBean" name="form">
    <div class="subtitle">
        <div id ="page_div">
            <s:messages/>
            <s:errors/>

            <c:if test="${EditRecord}">
                <div class="subtitle">
                    <div id ="editLaporan">
                        <fieldset class="aras1">
                            <legend>Laporan Tanah Susulan</legend>
                            <p>
                                <label><em>*</em>Tajuk :</label>
                                <s:textarea class="normal_text" name="tajukTesting" id="tajuk" cols="70" rows="10" onkeydown="limitText(this,4000);" onkeyup="limitText(this,4000);"> </s:textarea> 
                            </p>
                            <p>
                                <label>Tarikh  :</label>
                                <s:text name="tarikhLaporan" class="datepicker"  formatPattern="dd/MM/yyyy" id="tarikhLaporan" />&nbsp;
                                <font color="red" size="1">cth : hh / bb / tttt</font>
                            </p>
                            <p>
                                <%--to set value for AM/PM--%>
                                <c:set var="waktu" value="${fn:substring(actionBean.kertas.tarikhSidang,11,13)}"/>
                                <c:if test="${waktu > 11}"><c:set var="time" value="PM"/></c:if>
                                <c:if test="${waktu <= 11}"><c:set var="time" value="AM"/></c:if>
                                <fmt:formatDate var="hourVal" value="${actionBean.kertas.tarikhSidang}" pattern="hh" />
                                <%--to set value for minute--%>
                                <fmt:formatDate var="minuteVal" value="${actionBean.kertas.tarikhSidang}" pattern="mm" />
                                <label>Masa :</label>
                                <s:select name="jam" id="jam" style="width:50px;" value="${hourVal}">
                                    <s:option value=""> Jam </s:option>
                                    <c:forEach var="hour" begin="1" end="12">
                                        <c:choose>
                                            <c:when test="${hour > 9}"><s:option value="${hour}">${hour}</s:option></c:when>
                                            <c:otherwise><s:option value="0${hour}">0${hour}</s:option></c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </s:select>
                                <s:select name="minit" id="minit" style="width:70px;" value="${minuteVal}">
                                    <s:option value=""> Minit </s:option>
                                    <c:forEach var="minute" begin="00" end="59" >
                                        <c:choose>
                                            <c:when test="${minute > 9}"><s:option value="${minute}">${minute}</s:option></c:when>
                                            <c:otherwise><s:option value="0${minute}">0${minute}</s:option></c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </s:select>
                                <s:select name="ampm" id="ampm" style="width:80px" value="${time}">
                                    <s:option value="">Pilih</s:option>
                                    <s:option value="AM">PAGI</s:option>
                                    <s:option value="PM">PETANG</s:option>
                                </s:select>
                            </p>

                            <s:hidden name="recordCount" id="recordCount"/>
                            <div class="content" align="center">
                                <table  width="50%" id="tbl" class="tablecloth">
                                    <tr>
                                        <th width="1%" align="center"><b>Pilih</b></th>
                                        <th width="1%" align="center"><b>Bil</b></th>
                                        <th width="10%" align="center"><b><em>*</em>Laporan</b></th>
                                        <%--<b>Laporan :</b>--%>
                                    </tr>
                                    <c:set value="0" var="i"/>
                                    <c:forEach items="${actionBean.senaraiKertas}" var="line">
                                        <tr style="font-weight:bold">

                                            <td><s:checkbox name="pilih" id="pilih" value="${line.idKandungan}"/> </td>
                                            <td><c:out value="${i+1}"/></td>
                                            <td>
                                                <s:textarea name="kandungan${i}" id="kandungan${i}" value="${line.kandungan}" cols="100" rows="7" class="normal_text"/>
                                            </td>
                                            <s:hidden name="idKertas${i}" id="idKertas${i}" value="${line.idKandungan}" />
                                        </tr>
                                        <c:set var="i" value="${i+1}" />
                                    </c:forEach>

                                </table>
                                <br/>
                                <table>
                                    <tr><td align="center">
                                            <p align="center">** Sila tanda di petak Pilih untuk hapus.</p>
                                            <br/>
                                            <s:hidden name="idKertasTesting" id="idKertasTesting" value="${actionBean.idKertasTesting}"/>
                                            <s:button class="btn" value="Tambah" name="add" onclick="addRow('tbl')" />
                                            <s:button name="pilihTanah" value="Hapus" class="btn" onclick="javascript:selectCheckBox(this.form);"/>
                                            <s:button class="btn"  name="simpanEdit" onclick="if(validateForm())saveKertas(this.name,this.form);" value="Simpan"/>
                                            <s:button class="btn" value="Tutup" name="add" onclick="self.close();" />

                                </table>

                            </div>
                        </fieldset>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
</s:form>
