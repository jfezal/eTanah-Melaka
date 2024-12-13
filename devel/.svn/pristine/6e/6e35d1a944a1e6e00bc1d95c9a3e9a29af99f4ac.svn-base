<%--
    Document   : senarai_pihak_berkepentingan
    Created on : 15-Oct-2009, 03:41:03
    Author     : wazer
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">
    function doOpen(val1) {
        var idHakmilik = $('#hakmilik').val();
        var url = '${pageContext.request.contextPath}/daftar/pembetulan/TambahPihakBerkepentinganActionBean?caripihak2&idUrusan=' + val1;
        window.open(url, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes");
    }
    function doOpenHakmilikPihak(val1) {
        var idHakmilik = $('#hakmilik').val();
        var url = '${pageContext.request.contextPath}/daftar/pembetulan/TambahPihakBerkepentinganActionBean?kemaskiniHakmilikPihak&idHakmilikPihak=' + val1;
        window.open(url, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes");
    }
    function addHakmilikPihak(val1) {
        var idHakmilik = $('#hakmilik').val();
        var url = '${pageContext.request.contextPath}/daftar/pembetulan/TambahPihakBerkepentinganActionBean?addNewPihak&idUrusan=' + val1;
        window.open(url, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes");
    }

    function removePihak(val) {

        if (confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/daftar/pembetulan/TambahPihakBerkepentinganActionBean?deletePihak&idAtasPihak=' + val;
            $.get(url,
                    function(data) {
                        $('#page_div').html(data);
                    }, 'html');
        }
    }
    function removePihakPemohon(val) {

        if (confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/daftar/pembetulan/TambahPihakBerkepentinganActionBean?deletePihakPemohon&idAtasPihak=' + val;
            $.get(url,
                    function(data) {
                        $('#page_div').html(data);
                    }, 'html');
        }
    }

    function deleteHakmilikPihak(val) {

        if (confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/daftar/pembetulan/TambahPihakBerkepentinganActionBean?deleteHakmilikPihak&idAtasPihak=' + val;
            $.get(url,
                    function(data) {
                        $('#page_div').html(data);
                    }, 'html');
        }
    }

    function removeHakmilikPihak(val) {

        if (confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/daftar/pembetulan/TambahPihakBerkepentinganActionBean?deletePihakPemohon&idAtasPihak=' + val;
            $.get(url,
                    function(data) {
                        $('#page_div').html(data);
                    }, 'html');
        }
    }
    function deletePihakHakmilikPihak(val) {

        if (confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/daftar/pembetulan/TambahPihakBerkepentinganActionBean?deletePihakHakmilikPihak&idAtasPihak=' + val;
            $.get(url,
                    function(data) {
                        $('#page_div').html(data);
                    }, 'html');
        }
    }
    function removePihak(val) {

        if (confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/daftar/pembetulan/TambahPihakBerkepentinganActionBean?deletePihak&idAtasPihak=' + val;
            $.get(url,
                    function(data) {
                        $('#page_div').html(data);
                    }, 'html');
        }
    }
    function updateSyerPemohon(idpihak, id) {
        var s1 = $('#syer3' + id).val();
        var s2 = $('#syer4' + id).val();

        if (s1 == '' || s2 == '') {
            return;
        }

        if (isNaN(s1) && isNaN(s2)) {
            return;
        }
        var url = '${pageContext.request.contextPath}/daftar/pembetulan/TambahPihakBerkepentinganActionBean?updateSyerPemohon&idpihak=' + idpihak
                + '&syer3=' + s1 + '&syer4=' + s2;
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top: ($(window).height() - 50) / 2 + 'px',
                left: ($(window).width() - 50) / 2 + 'px',
                width: '50px'
            }
        });
        $.post(url,
                function(data) {
                    $.unblockUI();
                }, 'html');

    }

    function updateSyerHakmilikPihak(idHakmilikPihakBerkepentingan, id) {
        var s1 = $('#syer3' + id).val();
        var s2 = $('#syer4' + id).val();

        if (s1 == '' || s2 == '') {
            return;
        }

        if (isNaN(s1) && isNaN(s2)) {
            return;
        }
        var url = '${pageContext.request.contextPath}/daftar/pembetulan/TambahPihakBerkepentinganActionBean?updateSyerHakmilikPihak&idpihak=' + idHakmilikPihakBerkepentingan
                + '&syer3=' + s1 + '&syer4=' + s2;
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top: ($(window).height() - 50) / 2 + 'px',
                left: ($(window).width() - 50) / 2 + 'px',
                width: '50px'
            }
        });
        $.post(url,
                function(data) {
                    $.unblockUI();
                }, 'html');

    }


    function updateSyerMohonPihak(idpihak, id) {
        var s1 = $('#syer1' + id).val();
        var s2 = $('#syer2' + id).val();

        if (s1 == '' || s2 == '') {
            return;
        }

        if (isNaN(s1) && isNaN(s2)) {
            return;
        }
        var url = '${pageContext.request.contextPath}/daftar/pembetulan/TambahPihakBerkepentinganActionBean?updateSyerMohonPihak&idpihak=' + idpihak
                + '&syer1=' + s1 + '&syer2=' + s2;
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top: ($(window).height() - 50) / 2 + 'px',
                left: ($(window).width() - 50) / 2 + 'px',
                width: '50px'
            }
        });
        $.post(url,
                function(data) {
                    $.unblockUI();
                }, 'html');

    }

    function doOpen2(val1, val2) {
        var idHakmilik = $('#hakmilik').val();
        alert(val1);
//        alert(val2);
        var url = '${pageContext.request.contextPath}/daftar/pembetulan/TambahPihakBerkepentinganActionBean?addNewKetuanPunyaan&idMohon=' + val1;
        window.open(url, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes");
    }

</script>
<div class="subtitle displaytag">

    <s:form name="form1" beanclass="etanah.view.stripes.nota.TambahPihakBerkepentinganActionBean">

        <fieldset class="aras1">
            <legend>Senarai Hakmilik Terlibat</legend>
            <p>
                <label>Hakmilik :</label>
                <s:select name="idHakmilik" onchange="reload(this.value);" id="hakmilik">
                    <c:forEach items="${actionBean.senaraiHakmilikTerlibat}" var="item" varStatus="line">
                        <s:option value="${item.hakmilik.idHakmilik}" style="width:400px">
                            ${item.hakmilik.idHakmilik} ( ${item.hakmilik.daerah.nama} - ${item.hakmilik.bandarPekanMukim.nama} )
                        </s:option>
                    </c:forEach>
                </s:select>
            </p>
            <br/>
        </fieldset>

        <fieldset class="aras1">
            <legend>Tambah Rekod Ketuanpunyaan</legend>
            <p>
                <display:table style="width:90%;" class="tablecloth" name="${actionBean.senaraiKeempunyaan}"
                               cellpadding="0" cellspacing="0" id="line">

                    <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                    <display:column property="nama" title="nama" class="nama" />
                    <display:column property="jenis.nama" title="Jenis Pihak"/>
                    <display:column property="perserahan.idPerserahan" title="ID Perserahan" />
                    <display:column property="jenisPengenalan.nama" title="Pengenalan" />
                    <display:column property="noPengenalan" title="Nombor Pengenalan" />
                    <display:column title="Bahagian yang diterima">
                    <center>
                        ${line.syerPembilang} / ${line.syerPenyebut}

                    </center>
                </display:column>

            </display:table>
            <p>
                <label>&nbsp;</label>
                <s:button name="add" onclick="doOpen2('${actionBean.permohonan.idPermohonan}');" value="tambah" class="btn"/>
            </p>
            </p>
            <br/>
        </fieldset>

        <fieldset class="aras1">
            <legend>
                Senarai Urusan 
            </legend>            
            <p align="center">
                <display:table style="width:90%;" class="tablecloth" name="${actionBean.hakmilikUrusanListY}"
                               cellpadding="0" cellspacing="0" id="line">
                    <s:hidden name="line.idUrusan" id="idUrusan"/>
                    <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                    <display:column property="idPerserahan" title="ID Perserahan" class="nama" />
                    <display:column property="idUrusan" title="ID Urusan" class="idUrusan"/>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" />
                    <display:column property="kodUrusan.kod" title="Kod Urusan" />
                    <display:column property="kodUrusan.nama" title="Urusan" />
                    <display:column property="tarikhDaftar" title="Tarikh Daftar" format="{0,date,dd-MM-yyyy}"/>
                    <display:column title="Kemaskini">
                    <div align="center">
                        <img alt='Klik Untuk Tambah' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                             id='rem_${line_rowNum}' onclick="doOpen('${line.idUrusan}')" onmouseover="this.style.cursor = 'pointer';">
                    </div>
                </display:column>
            </display:table>

            </p>
            <br/>   
        </fieldset>



        <fieldset class="aras1">
            <legend>Senarai Pihak Terlibat Baru</legend>
            <div class="content" align="center">

                <%--<c:when test="${fn:length(actionBean.senaraiMohonPihakBaru) > 0}">--%>
                <display:table style="width:80%;" class="tablecloth" name="${actionBean.senaraiMohonPihakBaru}"
                               cellpadding="0" cellspacing="0" id="line1">
                    <c:if test="${edit}">
                    </c:if>
                    <display:column title="Bil">${line1_rowNum}</display:column>
                    <display:column title="Nama Pihak" property="nama" class="remove"/>
                    <display:column title="Id Perserahan" property="permohonan.idPermohonan" class="remove"/>
                    <display:column title="Jenis Pemilikan" property="jenis.nama" class="remove"/>
                    <display:column title="Alamat Surat Menyurat">
                        ${line1.alamat.alamat1}
                        <c:if test="${line1.alamat.alamat1 ne null && line1.alamat.alamat1 ne null}"> , </c:if>
                        ${line1.alamat.alamat2}
                        <c:if test="${line1.alamat.alamat2 ne null && line1.alamat.alamat2 ne null}"> , </c:if>
                        ${line1.alamat.alamat3}
                        <c:if test="${line1.alamat.alamat3 ne null && line1.alamat.alamat3 ne null}"> , </c:if>
                        ${line1.alamat.alamat4}
                        <c:if test="${line1.alamat.alamat4 ne null && line1.alamat.poskod ne null}">,</c:if>
                        ${line1.alamat.poskod}
                        <c:if test="${line1.alamat.poskod ne null && line1.alamat.poskod ne null}">,</c:if>
                        ${line1.alamat.negeri.nama}
                    </display:column>
                    <display:column title="Bahagian yang diterima">

                        <s:text name="syerPembilang[${line1_rowNum-1}]" size="5" id="syer1${line1_rowNum-1}"
                                onblur="updateSyerMohonPihak('${line1.idPermohonanPihak}', '${line1_rowNum-1}')"
                                onchange="updateSyerMohonPihak('${line1.idPermohonanPihak}', '${line1_rowNum-1}')" class="pembilang"/> /
                        <s:text name="syerPenyebut[${line1_rowNum-1}]" size="5" id="syer2${line1_rowNum-1}"
                                onblur="updateSyerMohonPihak('${line1.idPermohonanPihak}', '${line1_rowNum-1}')"
                                onchange="updateSyerMohonPihak('${line1.idPermohonanPihak}', '${line1_rowNum-1}')" class="penyebut"/>

                    </display:column>
                    <c:if test="${edit}">
                        <display:column title="Hapus">
                            <div align="center">
                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                     id='rem_${line_rowNum}' onclick="removePihak('${line1.idPermohonanPihak}')" onmouseover="this.style.cursor = 'pointer';">
                            </div>
                        </display:column>     
                    </c:if>

                </display:table>
                <%--</c:when>--%>     
            </div>
        </fieldset>
        <br/>
        <fieldset class="aras1">
            <legend>Senarai Pihak pemohon Baru</legend>
            <div class="content" align="center">
                <display:table style="width:80%;" class="tablecloth" name="${actionBean.senaraiPemohonkBaru}"
                               cellpadding="0" cellspacing="0" id="line1">
                    <display:column title="Bil">${line1_rowNum}</display:column>
                    <display:column title="Nama Pihak" property="nama" class="remove"/>
                    <display:column title="Id Perserahan" property="permohonan.idPermohonan" class="remove"/>
                    <display:column title="Jenis Pemilikan" property="jenis.nama" class="remove"/>
                    <display:column title="Alamat Surat Menyurat">
                        ${line1.alamat.alamat1}
                        <c:if test="${line1.alamat.alamat1 ne null && line1.alamat.alamat1 ne null}"> , </c:if>
                        ${line1.alamat.alamat2}
                        <c:if test="${line1.alamat.alamat2 ne null && line1.alamat.alamat2 ne null}"> , </c:if>
                        ${line1.alamat.alamat3}
                        <c:if test="${line1.alamat.alamat3 ne null && line1.alamat.alamat3 ne null}"> , </c:if>
                        ${line1.alamat.alamat4}
                        <c:if test="${line1.alamat.alamat4 ne null && line1.alamat.poskod ne null}">,</c:if>
                        ${line1.alamat.poskod}
                        <c:if test="${line1.alamat.poskod ne null && line1.alamat.poskod ne null}">,</c:if>
                        ${line1.alamat.negeri.nama}
                    </display:column>
                    <display:column title="Bahagian yang diterima">

                        <s:text name="syerPembilang2[${line1_rowNum-1}]" size="5" id="syer3${line1_rowNum-1}"
                                onblur="updateSyerPemohon('${line1.idPemohon}', '${line1_rowNum-1}')"
                                onchange="updateSyerPemohon('${line1.idPemohon}', '${line1_rowNum-1}')" class="pembilang"/> /
                        <s:text name="syerPenyebut2[${line1_rowNum-1}]" size="5" id="syer4${line1_rowNum-1}"
                                onblur="updateSyerPemohon('${line1.idPemohon}', '${line1_rowNum-1}')"
                                onchange="updateSyerPemohon('${line1.idPemohon}', '${line1_rowNum-1}')" class="penyebut"/>

                    </display:column>
                    <c:if test="${edit}">
                        <display:column title="Hapus">
                            <div align="center">
                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                     id='rem_${line_rowNum}' onclick="removePihakPemohon('${line1.idPemohon}')" onmouseover="this.style.cursor = 'pointer';">
                            </div>
                        </display:column>     
                    </c:if>

                </display:table>  

            </div>
        </fieldset>


        <fieldset class="aras1">
            <legend>Rekod Ketuanpunyaan Baru</legend>
            <div class="content" align="center">
                <p>
                    <display:table style="width:90%;" class="tablecloth" name="${actionBean.senaraiKeempunyaanBETPB}"
                                   cellpadding="0" cellspacing="0" id="line">
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column property="nama" title="nama" class="nama" />
                        <display:column property="jenis.nama" title="Jenis Pihak"/>
                        <display:column property="perserahan.idPerserahan" title="urusan" />
                        <display:column property="jenisPengenalan.nama" title="Pengenalan" />
                        <display:column property="noPengenalan" title="No Pengenalan" />

                        <display:column title="Bahagian yang diterima">
                        <center>
                            <s:text name="syerPembilang3[${line_rowNum-1}]" size="5" id="syer3${line_rowNum-1}"
                                    onblur="updateSyerHakmilikPihak('${line.idHakmilikPihakBerkepentingan}', '${line_rowNum-1}')"
                                    onchange="updateSyerHakmilikPihak('${line.idHakmilikPihakBerkepentingan}', '${line_rowNum-1}')" class="pembilang">${line.syerPembilang}</s:text> /
                            <s:text name="syerPenyebut3[${line_rowNum-1}]" size="5" id="syer4${line_rowNum-1}"
                                    onblur="updateSyerHakmilikPihak('${line.idHakmilikPihakBerkepentingan}', '${line_rowNum-1}')"
                                    onchange="updateSyerHakmilikPihak('${line.idHakmilikPihakBerkepentingan}', '${line_rowNum-1}')" class="penyebut">${line.syerPenyebut}</s:text>
                        </center>
                    </display:column>


                    <display:column title="Kemaskini">
                        <div align="center">
                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                 id='rem_${line_rowNum}' onclick="doOpenHakmilikPihak('${line.idHakmilikPihakBerkepentingan}')" onmouseover="this.style.cursor = 'pointer';">
                        </div>
                    </display:column>
                    <display:column title="Hapus">
                        <div align="center">
                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                 id='rem_${line_rowNum}' onclick="deleteHakmilikPihak('${line.idHakmilikPihakBerkepentingan}')" onmouseover="this.style.cursor = 'pointer';">
                        </div>
                    </display:column>
                </display:table>
            </div>
        </fieldset>

        <fieldset class="aras1">
            <legend>Rekod Pihak Kongsi</legend>
            <div class="content" align="center">
                <p>
                <s:button class="btn" value="Pihak Kongsi" name="pihakKongsi" id="pihakKongsi" onclick="pihakKongsiPopup();"/>    
                </p>
            </div>
        </fieldset>
        <br/>
    </div>
</fieldset>
</s:form>



</div>
