<%--
    Document   : laporan_pemantauan
    Created on : Oct 7, 2011, 5:49:02 PM
    Author     : sitifariza.hanim
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<!--<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>-->


<!--<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>-->
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<!--<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/datepicker.css"/>-->
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/src/main/webapp/pub/scripts/datepicker.js"></script>
<!--<script language="javascript" type="text/javascript"src="${pageContext.request.contextPath}/src/main/webapp/pub/js/datetimepicker.js"></script>-->

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
</style>

<script language="javascript" type="text/javascript">
    $(document).ready(function() {
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yyyy'});

    });
    function Save(id) {
        if (confirm('Adakah anda pasti simpan?')) {
            var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_awal?simpanEdit&idKertas=' + id;
            alert("id" + id);
            $.get(url,
            function(data) {
                $('#page_div').html(data);
                //                        refreshPage();
            }, 'html');
        }
    }

    function editRecord(id) {
        //        var d = $('.x'+i).val();
        //        alert(id);
        window.open("${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_awal?showFromEditRecord&idKertas=" + id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1100,height=600,scrollbars=yes");
    }

    //    function editRecord(id) {
    //        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_awal?showFromEditRecord&idKertas=' + id;
    //        params = 'width=' + screen.width;
    //        params += ', height=' + screen.height;
    //        params += ', top=0, left=0'
    //        params += ', fullscreen=no';
    //        params += ', directories=no';
    //        params += ', location=no';
    //        params += ', menubar=no';
    //        params += ', resizable=no';
    //        params += ', scrollbars=yes';
    //        params += ', status=no';
    //        params += ', toolbar=no';
    //        newwin = window.open(url, 'PopUp', params);
    //        if (window.focus) {
    //            newwin.focus()
    //        }
    //        return false;
    //        //window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1100,height=600,scrollbars=yes");
    //    }

    function deleteRecord(id) {
        if (confirm('Adakah anda pasti untuk hapus?')) {
            var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_awal?deletePenguatkuasaanPasukan&idKertas=' + id;
            $.get(url,
            function(data) {
                $('#page_div').html(data);
                //                refreshPage();
            }, 'html');



        }
    }

    //    function refreshPage(event, f) {
    //    alert ("masuk ke x");
    //        if (validation()) {
    //        }
    //        else {
    //            var q = $(f).formSerialize();
    //            var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_awal?refreshPage=' ;
    //            var url = f.action + '?' + event;
    //            $.post(url, q,
    //                    function(data) {
    //                        $('#page_div', opener.document).html(data);
    //                        self.opener.refreshEdit();
    //                        self.close();
    //                    }, 'html');
    //        }
    //    }

    function refreshEdit() {
        var id = '${actionBean.permohonan.idPermohonan}';
        var url = '${pageContext.request.contextPath}/utiliti/laporanTanah?checkPermohonan&idPermohonan=' + id;
        $.get(url,
        function(data) {
            $("#editLaporan").replaceWith($('#editLaporan', $(data)));
        }, 'html');
    }



    function addRecord() {
        window.open("${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_awal?popupTambahLaporan", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1100,height=600,scrollbars=yes");
    }

    function View(id) {
        //        var d = $('.x'+i).val();
        //        alert(id);
        window.open("${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_awal?showForm4&idKertas=" + id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1100,height=600,scrollbars=yes");
    }

    function validateForm() {

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
        for (var i = 0; i < bil; i++) {

            var kandungan = document.getElementById('kandungan' + i);
            if (kandungan.value == "") {
                alert("Sila isikan butiran laporan terlebih dahulu");
                $('#kandungan' + i).focus();
                return false;
            }

        }
        return true;
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

        var idKandungan;
        try {
            var table = document.getElementById(tableid);

            var rowCount = table.rows.length;
            var j = 0;
            for (var i = 0; i < rowCount; i++) {
                var row = table.rows[i];
                var chkbox = row.cells[0].childNodes[0];
                if (null != chkbox && true == chkbox.checked) {

                    idKandungan = $("#idKertas" + (i + j - 1)).val();

                    if (document.getElementById("idKertas" + (i + j - 1)) != null) {
                        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_awal?deleteLaporan&idKandungan='
                            + idKandungan;

                        $.get(url,
                        function(data) {
                            $('#page_div').html(data);
                        }, 'html');
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


       

        //        function refreshPage() {
        //            var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_awal?refreshPage';
        ////            alert ("laaa");
        //            $.get(url,
        //                    function(data) {
        //                        $("#ulasanDiv").replaceWith($('#ulasanDiv', $(data)));
        //                    }, 'html');
        //            self.close();
        //        }

        function saveUlasan() {
            self.opener.refreshMainPage();
            self.close();
        }

        function refreshMainPage() {
            //            alert("y");
            var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_awal?refreshPage';
            $.get(url,
            function(data) {
                $('#page_div').html(data);
            }, 'html');
        }
        
        function muatNaikForm(folderId, dokumenId, idPermohonan, dokumenKod, idLaporan, idHakmilik, idRujukan) {
            var left = (screen.width/2)-(1000/2);
            var top = (screen.height/2)-(150/2);
            var url = '${pageContext.request.contextPath}/penguatkuasaan/upload_action?popupUpload&folderId=' + folderId + '&dokumenId='
                + dokumenId + '&idPermohonan=' + idPermohonan + '&dokumenKod=' + dokumenKod + '&idLaporan=' + idLaporan + '&idHakmilik=' + idHakmilik + '&idRujukan=' + idRujukan;
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
        }
        
        function doViewReport(v) {
            var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }
        
        function removeImej(idImej) {
            if(confirm('Adakah anda pasti untuk hapus?')) {
                var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_awal?deleteSelected&idImej='+idImej;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                    refreshMainPage();
                },'html');}
        }

</script>

<s:form beanclass="etanah.view.penguatkuasaan.MaklumatLaporanAwalActionBean" name="form2">
    <s:messages/>
    <s:errors/>
    <s:hidden name="permohonan.idPermohonan" />

    <c:if test="${edit}">
        <div class="subtitle">

            <fieldset class="aras1">
                <legend>Laporan Tanah Susulan</legend>
                <p>
                    <label><em>*</em>Tajuk :</label>
                    <s:textarea class="normal_text" name="tajuk" id="tajuk" cols="70" rows="10" onkeydown="limitText(this,4000);" onkeyup="limitText(this,4000);"> </s:textarea>
                </p>
                <p>
                    <label>Tarikh :</label>
                    <s:text name="tarikhLaporan" class="datepicker" formatPattern="dd/MM/yyyy" id="tarikhLaporan" />&nbsp;
                    <font color="red" size="1">cth : hh / bb / tttt</font>
                </p>
                <p>
                    <label>Masa :</label>
                    <s:select name="jam" id="jam" style="width:50px;">
                        <s:option value=""> Jam </s:option>
                        <c:forEach var="jam" begin="1" end="12">
                            <s:option value="${jam}">${jam}</s:option>
                        </c:forEach>
                    </s:select>
                    <s:select name="minit" id="minit" style="width:70px;">
                        <s:option value=""> Minit </s:option>
                        <c:forEach var="minit" begin="00" end="59" >
                            <c:choose>
                                <c:when test="${minit > 9}"><s:option value="${minit}">${minit}</s:option></c:when>
                                <c:otherwise><s:option value="0${minit}">0${minit}</s:option></c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </s:select>
                    <s:select name="ampm" id="ampm" style="width:80px">
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

                                <td><s:checkbox name="pilih${i}" id="pilih${i}" /> </td>
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
                                <s:button class="btn" value="Tambah" name="add" onclick="addRow('tbl')" />
                                <%--<s:button name="simpan" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>--%>
                                <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                                <s:button class="btn" value="Hapus" name="delete" onclick="deleteRow1('tbl')"/>
                    </table>

                </div>
            </fieldset>

        </div>
    </c:if>



    <c:if test="${viewTajuk}">
        <div class="subtitle">

            <fieldset class="aras1">
                <legend>Laporan Tanah Susulan</legend>
                <table>
                    <tr>
                        <td valign="top">
                            <p><label style="margin-top: -5px;">Tajuk :</label></p></td>
                        <td valign="top" align="justify">
                            <font size="2px;" color="black" style="font-style: normal; font-weight: normal;">
                                ${actionBean.kertas.tajuk}&nbsp;</font></td>
                    </tr>
                </table>
                <p>
                    <label>Tarikh :</label>
                    <c:if test="${actionBean.kertas.tarikhSidang ne null}">
                        <s:format value="${actionBean.kertas.tarikhSidang}" formatPattern="dd/MM/yyyy"/>
                    </c:if>
                    <c:if test="${actionBean.kertas.tarikhSidang eq null}"> Tiada Data </c:if>
                </p>
                <p>
                    <label>Masa :</label>
                    <c:if test="${actionBean.kertas.tarikhSidang ne null}">
                        <fmt:formatDate pattern="hh:mm" value="${actionBean.kertas.tarikhSidang}"/>
                        <fmt:formatDate pattern="aaa" value="${actionBean.kertas.tarikhSidang}" var="time"/>
                        <c:if test="${time eq 'AM'}">PAGI</c:if>
                        <c:if test="${time eq 'PM'}">PETANG</c:if>
                    </c:if>
                    <c:if test="${actionBean.kertas.tarikhSidang eq null}"> Tiada Data </c:if>
                </p>
                <div class="content" align="center"  >
                    <display:table name="${actionBean.senaraiKertas}" id="line" class="tablecloth" style="width:75%">
                        <display:column title="Bil" style="width:3%">${line_rowNum}</display:column>
                        <display:column property="kandungan" title="Laporan" />
                    </display:table>    
                </div>
            </fieldset>

        </div>

    </c:if>

    <c:if test="${view}">
        <br>
        <div class="content" align="center">
            <div id="ulasanDiv">
                <display:table name="${actionBean.listKertas}" id="line" class="tablecloth" cellpadding="0" cellspacing="0">
                    <%--<display:table name="${actionBean.senaraiOperasiPenguatkuasaan}" id="line" class="tablecloth" pagesize="10" cellpadding="0" cellspacing="0"
                                   requestURI="${pageContext.request.contextPath}/maklumat_laporan_operasi">--%>
                    <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                    <display:column title="Maklumat Laporan Operasi">
                        <table width="10%" cellpadding="1">
                            <%--                        <tr>
                                                        <td width="20%"><font class="infoLP">Id Operasi :</font></td>
                                                        <td width="20%"><a class="popup" onclick="viewRecordOP(${line.idOperasi})">${line.idOperasi}</a></td>
                                                    </tr>--%>
                            <p>
                            <td width="20%"><font class="infoLP">Tarikh :</font></td>
                            <td width="20%">
                                <c:if test="${line.tarikhSidang ne null}"><fmt:formatDate pattern="dd/mm/yy" value="${line.tarikhSidang}"/>&nbsp;</c:if>
                                <c:if test="${line.tarikhSidang eq null}"> Tiada Data </c:if>
                                </p>
                            <tr>
                                <td width="20%"><font class="infoLP">Masa laporan :</font></td>
                                <td width="20%">
                                    <fmt:formatDate pattern="hh:mm" value="${line.tarikhSidang}"/>
                                    <fmt:formatDate pattern="aaa" value="${line.tarikhSidang}" var="time"/>
                                    <c:if test="${time eq 'AM'}">PAGI</c:if>
                                    <c:if test="${time eq 'PM'}">PETANG</c:if>
                                </td>
                            </tr>

                        </table>
                    </display:column>  
                    <display:column title="Tajuk"  style="width:50%;">

                        <a href="" onclick="View(${line.idKertas});
                            return false;"> <font style="text-transform:uppercase;"> ${line.tajuk} </font></a>

                    </display:column>

                    <display:column title="Hapus">                 
                        <p align="center">
                            <c:set value="1" var="count"/>                               
                            <img src='${pageContext.request.contextPath}/images/not_ok.gif'
                                 onclick="deleteRecord(${line.idKertas});" height="15" width="15" alt="Hapus"
                                 onmouseover="this.style.cursor = 'pointer';" title="Hapus untuk Dokumen"/>
                            <c:set value="${count+1}" var="count"/>
                        </p>
                    </display:column>
                    <display:column title="Kemaskini">
                        <div align="center">
                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                 id='rem_${line_rowNum}' onmouseover="this.style.cursor = 'pointer';" onclick="editRecord('${line.idKertas}')"/>
                        </div>
                    </display:column>
                    <display:column title="Muat Naik/Papar">
                        <c:set value="1" var="count"/>
                        <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png"
                             onclick="muatNaikForm('${actionBean.permohonan.folderDokumen.folderId}','',
                                 '${actionBean.permohonan.idPermohonan}','LAWAL','','',${line.idKertas});return false;" height="30" width="30" alt="Muat Naik"
                             onmouseover="this.style.cursor='pointer';" title="Muat Naik untuk Dokumen Laporan Tanah Susulan"/><br>
                        <c:forEach items="${actionBean.permohonan.folderDokumen.senaraiKandungan}" var="senarai">
                            <c:if test="${line.idKertas eq senarai.dokumen.perihal}">
                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                     onclick="doViewReport('${senarai.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                     onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${senarai.dokumen.tajuk}"/> <font size="1">${count}-${senarai.dokumen.tajuk}</font>
                                /
                                <img src='${pageContext.request.contextPath}/images/not_ok.gif'
                                     onclick="removeImej('${senarai.dokumen.idDokumen}');" height="15" width="15" alt="Hapus"
                                     onmouseover="this.style.cursor='pointer';" title="Hapus untuk Dokumen ${senarai.dokumen.kodDokumen.nama}"/>
                                <br/>
                                <c:set value="${count+1}" var="count"/>
                            </c:if>
                        </c:forEach>

                    </display:column>

                </display:table>
            </div>

            <s:button class="btn" value="Tambah" name="new" id="new" onclick="addRecord();"/>
            <br>
        </div>
    </c:if>

    <c:if test="${viewonly}">
        <br>
        <div class="content" align="center">
            <div id="ulasanDiv">
                <display:table name="${actionBean.listKertas}" id="line" class="tablecloth" cellpadding="0" cellspacing="0">
                    <%--<display:table name="${actionBean.senaraiOperasiPenguatkuasaan}" id="line" class="tablecloth" pagesize="10" cellpadding="0" cellspacing="0"
                                   requestURI="${pageContext.request.contextPath}/maklumat_laporan_operasi">--%>
                    <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                    <display:column title="Maklumat Laporan Operasi">
                        <table width="10%" cellpadding="1">
                            <%--                        <tr>
                                                        <td width="20%"><font class="infoLP">Id Operasi :</font></td>
                                                        <td width="20%"><a class="popup" onclick="viewRecordOP(${line.idOperasi})">${line.idOperasi}</a></td>
                                                    </tr>--%>
                            <p>
                            <td width="20%"><font class="infoLP">Tarikh :</font></td>
                            <td width="20%">
                                <c:if test="${line.tarikhSidang ne null}"><fmt:formatDate pattern="dd/mm/yy" value="${line.tarikhSidang}"/>&nbsp;</c:if>
                                <c:if test="${line.tarikhSidang eq null}"> Tiada Data </c:if>
                                </p>
                            <tr>
                                <td width="20%"><font class="infoLP">Masa laporan :</font></td>
                                <td width="20%">
                                    <fmt:formatDate pattern="hh:mm" value="${line.tarikhSidang}"/>
                                    <fmt:formatDate pattern="aaa" value="${line.tarikhSidang}" var="time"/>
                                    <c:if test="${time eq 'AM'}">PAGI</c:if>
                                    <c:if test="${time eq 'PM'}">PETANG</c:if>
                                </td>
                            </tr>

                        </table>
                    </display:column>  
                    <display:column title="Tajuk" style="width:50%;">

                        <a href="" onclick="View(${line.idKertas});
                            return false;"> <font style="text-transform:uppercase;"> ${line.tajuk} </font></a>

                    </display:column>
                    <display:column title="Papar">
                        <c:set value="1" var="count"/>
                        <c:forEach items="${actionBean.permohonan.folderDokumen.senaraiKandungan}" var="senarai">
                            <c:if test="${line.idKertas eq senarai.dokumen.perihal}">
                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                     onclick="doViewReport('${senarai.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                     onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${senarai.dokumen.tajuk}"/> <font size="1">${count}-${senarai.dokumen.tajuk}</font>
                                <br/>
                                <c:set value="${count+1}" var="count"/>
                            </c:if>
                        </c:forEach>

                    </display:column>

                </display:table>
            </div>

            <br>
        </div>
    </c:if>


</s:form>


