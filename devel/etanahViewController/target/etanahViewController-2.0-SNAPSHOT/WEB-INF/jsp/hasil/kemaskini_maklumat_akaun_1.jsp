<%-- 
    Document   : kemaskini_maklumat_akaun_1
    Created on : Apr 5, 2011, 3:17:48 PM
    Author     : abdul.hakim
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $("#dialog-box").dialog({
            autoOpen: false,
            height: 250,
            width: 350,
            modal: true,
            buttons: {
                'Ok': function() {
                    var f = document.forms1;
                    doSend1(f);
                    $(this).dialog('close');
                },
                'Tutup': function() {
                    $(this).dialog('close');
                }
            }
        });

        $('#simpan')
                .click(function() {
                    $('#dialog-box').dialog('open');
                });

        if (${actionBean.btn}) {
            $('#back').hide();
            $('#simpan').hide();
            $('#main').show();
        }

        var boo = ${actionBean.checkingBaki};
        if (boo == false) {
            updt
            $('#updt').hide();
        }

        if (${actionBean.btn eq false}) {
            $('#main').hide();
        }
        updtTotal();
    });

    function doSend1(frm) {
        if (confirm('Adakah anda pasti? Catatan akan disimpan.')) {
            var u = $('#msg').val();
            if (u == '') {
                alert("Sila masukkan catatan");
                return false;
            } else {
                $('#mesej1').val(u);
                $('#simpan5').click();
            }
    <%--submits(frm);--%>
        }
    }

</script>
<script type="text/javascript">
    function refreshPagesAddress(f) {
        var queryString = $(f).formSerialize()
        var url = "${pageContext.request.contextPath}/hasil/pertanyaan_hakmilik?refreshpage&" + queryString;
        $.get(url,
                function(data) {
                    $('#aa').html(data);
                }, 'html');

    }
    function refreshT(f) {
        var queryString = $(f).formSerialize()
        var url = "${pageContext.request.contextPath}/hasil/pertanyaan_hakmilik?refreshTambah&" + queryString;
        $.get(url,
                function(data) {
                    $('#aa').html(data);
                }, 'html');

    }

    function refreshB(f) {
        var queryString = $(f).formSerialize()
        var url = "${pageContext.request.contextPath}/hasil/pertanyaan_hakmilik?refreshBaru&" + queryString;
        $.get(url,
                function(data) {
                    $('#aa').html(data);
                }, 'html');

    }

    function editMaklumat(f, id1, id2, noAkaun) {
        // var queryString = $(f).formSerialize()
        window.open("${pageContext.request.contextPath}/hasil/kemaskini_data?pembayarDetail&idPihak=" + id1 + "&idHakmilik=" + id2 + "&noAkaun=" + noAkaun, "eTanah",
                "status=0,toolbar=0,resizable=1,scrollbars=1,location=0,menubar=0,width=1035,height=420");
    }

    function editRow(f, idTrans, noAkaun) {
        window.open("${pageContext.request.contextPath}/hasil/kemaskini_data?kemaskiniTransaksi&idTrans=" + idTrans + "&noAkaun=" + noAkaun, "eTanah",
                "status=0,toolbar=0,resizable=1,scrollbars=1,location=0,menubar=0,width=1130,height=300");
    }

    function addRow(noAkaun) {
        window.open("${pageContext.request.contextPath}/hasil/kemaskini_data?addNewTransaction&noAkaun=" + noAkaun, "eTanah",
                "status=0,toolbar=0,resizable=1,scrollbars=1,location=0,menubar=0,width=1130,height=300");
    }

    function tambahPembayar(f, id1, id2) {
        var queryString = $(f).formSerialize()

        window.open("${pageContext.request.contextPath}/hasil/pertanyaan_hakmilik?tambah&" + queryString + "&idHakmilik=" + id1 + "&noAkaun=" + id2, "eTanah",
                "status=0,toolbar=0,resizable=1,scrollbars=1,location=0,menubar=0,width=1035,height=420");
    }

    function pihakBaru(f, id1, id2) {
        alert(id1);
        var queryString = $(f).formSerialize()

        window.open("${pageContext.request.contextPath}/hasil/pertanyaan_hakmilik?baru&" + queryString + "&idHakmilik=" + id1 + "&noAkaun=" + id2, "eTanah",
                "status=0,toolbar=0,resizable=1,scrollbars=1,location=0,menubar=0,width=1035,height=420");
    }

    function confirmRefresh() {
        setTimeout("location.reload(true);", 200);
    }

    function click1(id) {
        $('#' + id).removeAttr("style");
        $('#' + id).attr("style", "text-align:right");
    }

    function blur1(id) {
        $('#' + id).removeAttr("style");
        $('#' + id).attr("style", "background:transparent;border:0 px;cursor:pointer");
        var x = document.getElementById(id);
        var f = parseFloat(x.value);
        var a = document.getElementById(id)
        if ((isNaN(a.value)) || ((a.value) == "")) {
            alert("Nombor tidak sah");
            $('#' + id).val("0.00");
            return;
        } else {
            $('#' + id).val(f.toFixed(2));
            updtTotal();
        }
    }

    function updtTotal() {
        var tax = parseFloat(document.getElementById('cukaiSebenar').value);
        var outFine = parseFloat(document.getElementById('denda').value);
        var outTax = parseFloat(document.getElementById('tunggakan').value);
        var cr = parseFloat(document.getElementById('kredit').value);
        var dt = parseFloat(document.getElementById('debit').value);
        var rem = parseFloat(document.getElementById('remisyen').value);
        var notice = parseFloat(document.getElementById('notis').value);

        var total = (tax + outFine + outTax + dt + notice) - (cr + rem);
        $("#total").val(total.toFixed(2))
    }

    function submits(f) {
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top: ($(window).height() - 50) / 2 + 'px',
                left: ($(window).width() - 50) / 2 + 'px',
                width: '50px'
            }
        });
        f.submit();
    }

</script>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif"
     width="50" height="50" style="display: none" alt=""/>
<div id="dialog-box" title="Sila masukan catatan" style="display: none" class="subtitle">
    <s:form beanclass="etanah.view.stripes.hasil.KemaskiniMaklumatAkaunActionBean">
        <table>
            <tr>
                <td><em>*</em></td>
                <td><s:textarea name="catatan" cols="38" rows="10" id="msg"/></td>
            </tr>
        </table>
    </s:form>
</div>
<div align="center"><table style="width:99.2%" bgcolor="green">
        <tr>
            <td width="100%" height="20" >
                <div style="background-color: green;" align="center">
                    <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Kemaskini Maklumat Cukai</font>
                </div>
            </td>
        </tr>
    </table></div>
    <s:form beanclass="etanah.view.stripes.hasil.KemaskiniMaklumatAkaunActionBean" id="kemaskini_akaun" name="forms1">
        <s:hidden name="totalAmount"/>
        <s:hidden name="rbtAkaun"/>
    <div id="bayar">

        <div class="subtitle">

            <fieldset class="aras1">
                <legend>Maklumat Asas</legend>

                <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                    <p>
                        <label> No Akaun :</label>
                        ${actionBean.akaun.noAkaun}&nbsp;
                    </p>
                    <p>
                        <label> Status Akaun :</label>
                        ${actionBean.akaun.status.nama}&nbsp;
                        <c:if test="${actionBean.akaun.status.kod eq 'A'}">
                            <s:button name="Step9" value="Batal" class="btn" id="simpan" onclick="javaScript:simpan(this.form)"/>
                        </c:if>
                        <c:if test="${actionBean.akaun.status.kod eq 'A'}">
                            <s:submit name="StepBeku" value="beku" class="btn" id="simpan"/>
                        </c:if>
                        <c:if test="${actionBean.akaun.status.kod eq 'F' || actionBean.akaun.status.kod eq 'B'}">
                            <s:button name="Step9" value="Aktif" class="btn" id="simpan" onclick="javaScript:simpan(this.form)"/>
                        </c:if>
                    </p>
                </c:if><br>
                <p><label>ID Hakmilik :</label>
                    ${actionBean.hakmilik.idHakmilik}&nbsp;
                </p>
                <p><label>Status Hakmilik :</label>
                    ${actionBean.hakmilik.kodStatusHakmilik.nama}&nbsp;
                </p><br>
                <p><label>Daerah :</label>
                    ${actionBean.hakmilik.daerah.nama}&nbsp;
                </p>

                <p>
                    <label>Bandar/Pekan/Mukim :</label>
                    ${actionBean.hakmilik.bandarPekanMukim.nama}&nbsp;
                </p>

                <p>
                    <label>Tempat :</label>
                    ${actionBean.hakmilik.lokasi}&nbsp;
                </p>
                <p>
                    <label>Jenis Hakmilik :</label>
                    ${actionBean.hakmilik.kodHakmilik.nama}&nbsp;
                </p>

                <p>
                    <label>Jenis Penggunaan Tanah :</label>
                    ${actionBean.hakmilik.kategoriTanah.nama}&nbsp;
                </p>

                <p>
                    <label>Kod Lot :</label>
                    ${actionBean.hakmilik.lot.nama}&nbsp;

                </p>
                <p>
                    <label>No Lot/PT :</label>
                    ${actionBean.hakmilik.noLot}&nbsp;

                </p>

                <p>
                    <label>Tarikh Daftar :</label>
                    <fmt:formatDate type="date"
                                    pattern="dd/MM/yyyy"
                                    value="${actionBean.hakmilik.tarikhDaftar}"/>&nbsp;
                </p>

                <p>
                    <label>Tanah Rezab :</label>
                    ${actionBean.hakmilik.rizab.nama}&nbsp;
                </p>
                <p>
                    <label>Pihak Berkuasa Tempatan :</label>
                    ${actionBean.hakmilik.pbt.nama}&nbsp;
                </p>

                <p>
                    <label>Taraf Pegangan :</label>
                    <c:if test="${actionBean.hakmilik.pegangan eq 'P'}">
                        Pajakan
                    </c:if>
                    <c:if test="${actionBean.hakmilik.pegangan eq 'S'}">
                        Selama - lama
                    </c:if>
                    &nbsp;
                </p>
                <p>
                    <label>Tempoh :</label>
                    <c:if test="${actionBean.hakmilik.tempohPegangan ne null && actionBean.hakmilik.tempohPegangan ne 0 }">
                        ${actionBean.hakmilik.tempohPegangan} Tahun&nbsp;
                    </c:if>
                    <c:if test="${actionBean.hakmilik.tempohPegangan eq null}">
                        -
                    </c:if>
                    <c:if test="${actionBean.hakmilik.tempohPegangan eq 0}">
                        -
                    </c:if>
                </p>
                <p>
                    <label>Tarikh Luput :</label>
                    <c:if test="${actionBean.hakmilik.tarikhLuput eq null}">
                        Tiada
                    </c:if>

                    <fmt:formatDate type="date"
                                    pattern="dd/MM/yyyy"
                                    value="${actionBean.hakmilik.tarikhLuput}"/>&nbsp;
                </p>
                <p>
                    <label for="noPu">No Permohonan Ukur :</label>
                    ${actionBean.hakmilik.noPu}&nbsp;
                </p>

                <p>
                    <label >Syarat Nyata :</label>
                    ${actionBean.hakmilik.syaratNyata.kod}&nbsp;
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:textarea name="syaratNyata" rows="5" cols="60" id="syarat" readonly="true">${actionBean.hakmilik.syaratNyata.syarat}</s:textarea>
                    </p>

                    <p>
                        <label >Sekatan :</label>
                    ${actionBean.hakmilik.sekatanKepentingan.kod}&nbsp;
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:textarea name="sekatanKepentingan" rows="5" cols="60" id="sekatan" readonly="true">${actionBean.hakmilik.sekatanKepentingan.sekatan}</s:textarea>
                    </p>

                    <p>
                        <label>No Pelan Piawai :</label>
                    ${actionBean.hakmilik.noLitho}&nbsp;
                </p>
                <p>
                    <label>No Pelan Diperakui :</label>
                    ${actionBean.hakmilik.noPelan}&nbsp;
                </p>
                <p>
                    <label >Keluasan  :</label>
                    <s:format formatPattern="#,##0.0000" value="${actionBean.hakmilik.luas}"/>&nbsp; ${actionBean.hakmilik.kodUnitLuas.nama}
                </p>
                <p>
                    <label >Cukai Asal (RM)  :</label>
                    <fmt:formatNumber value="${actionBean.hakmilik.cukai}" pattern="0.00"/>&nbsp;
                </p>
                <p>
                    <label >Cukai Tanah Tahunan (RM)  :</label>

                    <fmt:formatNumber value="${actionBean.hakmilik.cukaiSebenar}" pattern="0.00"/>&nbsp;
                </p>
                <p>
                    <label>Status Hakmilik :</label>
                    ${actionBean.hakmilik.kodStatusHakmilik.nama}&nbsp;

                    <%--c:if test="${actionBean.hakmilik.kodStatusHakmilik.kod eq 'B' && fn:length(actionBean.senaraiHakmilikBerikut) > 0 }">
                        dan disambung ke
                    </c:if>
                </p>
                <c:if test="${actionBean.hakmilik.kodStatusHakmilik.kod eq 'B' && fn:length(actionBean.senaraiHakmilikBerikut) > 0 }">
                    <p>
                        <label>&nbsp;</label>
                        <display:table class="" name="${actionBean.senaraiHakmilikBerikut}" id="i">
                            <display:column title="">${i_rowNum})</display:column>
                            <display:column title="" ><a href="carian_hakmilik?papar&idHakmilik=${i.idHakmilik}">${i.idHakmilik}</a>
                            </display:column>
                        </display:table>
                        &nbsp;
                    </p>
                </c:if--%>
                <p>
                    <label>Tarikh Daftar Asal :</label>
                    <fmt:formatDate type="date"
                                    pattern="dd/MM/yyyy" value="${actionBean.hakmilik.tarikhDaftarAsal}"/>&nbsp;
                </p>
                <c:if test="${actionBean.hakmilik.kodStatusHakmilik.kod eq 'B'}" >
                    <p>
                        <label>Tarikh Batal :</label>
                        <fmt:formatDate type="date" pattern="dd/MM/yyyy" value="${actionBean.hakmilik.tarikhBatal}"/>&nbsp;
                    </p>
                </c:if>

            </fieldset>

        </div>
        <br>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Maklumat Pembayar
                </legend>

                <p><label>Nama :</label>

                    ${actionBean.pihak.nama}&nbsp;
                    <%--<c:if test="${actionBean.pengguna.perananUtama.kod eq '2'}">
                        <a id="" onclick="editMaklumat(this.form, '${actionBean.pihak.idPihak}','${actionBean.hakmilik.idHakmilik}','${actionBean.akaun.noAkaun}');" onmouseover="this.style.cursor='pointer';">
                            <img alt="Sila Klik Untuk Kemaskini Alamat Pembayar" src='${pageContext.request.contextPath}/pub/images/edit.gif' />
                        </a>&nbsp;
                        <a onmouseover="this.style.cursor='pointer';" onclick="tambahPembayar(this.form,'${actionBean.hakmilik.idHakmilik}','${actionBean.akaun.noAkaun}');">
                            <img alt="Sila Klik Untuk Pilih Nama Pembayar Yang Baru" src='${pageContext.request.contextPath}/pub/images/not_ok.gif' />&nbsp;
                        </a>&nbsp;&nbsp;
                        <a onmouseover="this.style.cursor='pointer';" onclick="pihakBaru(this.form,'${actionBean.hakmilik.idHakmilik}','${actionBean.akaun.noAkaun}');">
                            <img alt="Sila Klik Untuk Tambah Pembayar Yang Baru" src='${pageContext.request.contextPath}/pub/images/baharu.gif' />&nbsp;
                        </a>&nbsp;
                    </c:if>--%>
                </p>
                <p>
                    <label>Alamat Tetap :</label>
                    ${actionBean.pihak.alamat1}&nbsp;
                </p>
                <p>
                    <label> &nbsp;</label>
                    ${actionBean.pihak.alamat2}&nbsp;
                </p>
                <p>
                    <label> &nbsp;</label>
                    ${actionBean.pihak.alamat3}&nbsp;
                </p>
                <p>
                    <label> &nbsp;</label>
                    ${actionBean.pihak.alamat4}&nbsp;
                </p>

                <p>
                    <label>Poskod :</label>
                    ${actionBean.pihak.poskod}&nbsp;
                </p>
                <p>
                    <label>Negeri :</label>
                    ${actionBean.pihak.negeri.nama}&nbsp;

                </p>
                <p>
                    <label>Alamat Surat Menyurat :</label>
                    ${actionBean.pihak.suratAlamat1}&nbsp;
                </p>
                <p>
                    <label> &nbsp;</label>
                    ${actionBean.pihak.suratAlamat2}&nbsp;
                </p>
                <p>
                    <label> &nbsp;</label>
                    ${actionBean.pihak.suratAlamat3}&nbsp;
                </p>
                <p>
                    <label> &nbsp;</label>
                    ${actionBean.pihak.suratAlamat4}&nbsp;
                </p>

                <p>
                    <label>Poskod :</label>
                    ${actionBean.pihak.suratPoskod}&nbsp;
                </p>
                <p>
                    <label>Negeri :</label>
                    ${actionBean.pihak.suratNegeri.nama}&nbsp;

                </p>
                <p>
                    <label>Nombor Telefon :</label>
                    ${actionBean.pihak.noTelefon1}&nbsp;

                </p>
                <p>
                    <label>Nombor Telefon Bimbit:</label>
                    ${actionBean.pihak.noTelefonBimbit}&nbsp;
                </p>
                <p>
                    <label>Email:</label>
                    ${actionBean.pihak.email}
                </p>

            </fieldset>
        </div>

        <br>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Perihal Cukai Semasa</legend>
                <%--<p class=instr><em><font color="black">Klik amaun yang berkenaan untuk mengemaskini maklumat cukai.<br>&nbsp;--%>
                <%--<font color="red">PERINGATAN:</font> Column amaun bagi Debit dan Kredit tidak boleh dikemaskini sekiranya pembayaran belum dibuat.</font></em>--%>
                <%--<div align="center">
                    <table align="center" border="0">
                        <tr>
                            <td width="130"><font color="#003194" size="2px" style="Tahoma"><b>Status Pembayaran </b></font></td>
                            <td width="2"><font color="#003194" size="2px" style="Tahoma"><b>:</b></font></td>
                            <c:choose>
                                <c:when test="${actionBean.checking}">
                                    <td width="130">
                                        <font size="2">
                                            <b><marquee onmouseover="this.setAttribute('scrollamount', 0, 0);" onmouseout="this.setAttribute('scrollamount', 2, 0);">Sudah Dijelaskan</marquee></b>
                                        </font>
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td width="130">
                                        <font size="2">
                                            <b><marquee onmouseover="this.setAttribute('scrollamount', 0, 0);" onmouseout="this.setAttribute('scrollamount', 2, 0);">Belum Dijelaskan</marquee></b>
                                        </font>
                                    </td>
                                </c:otherwise>
                            </c:choose>
                        </tr>
                    </table>
                    <p></p>
                    <table align="center" border="0">
                        <tr>
                            <td width="170"><font color="#003194" size="2px" style="Tahoma"><b>Cukai Semasa </b></font></td>
                            <td width="2"><font color="#003194" size="2px" style="Tahoma"><b>:</b></font></td>
                            <td><font size="2">RM <s:text name="cukai" id="cukaiSebenar"  formatPattern="#,##0.00"
                                    onclick="click1('cukaiSebenar')" onblur="blur1('cukaiSebenar')"
                                    style="background:transparent;border:0 px;cursor:pointer" size="15"/></font></td>
                            <td width="5">&nbsp;</td>
                            <c:choose>
                                <c:when test="${actionBean.checking}">
                                    <td width="170"><font color="#003194" size="2px" style="Tahoma"><b>Debit </b></font></td>
                                    <td width="2"><font color="#003194" size="2px" style="Tahoma"><b>:</b></font></td>
                                    <td><font size="2">RM <s:text name="debit" formatPattern="#,##0.00"
                                            onclick="click1('debit')" onblur="blur1('debit')" id="debit"
                                            style="background:transparent;border:0 px;cursor:pointer" size="15"/></font></td>
                                </c:when>
                                <c:otherwise>
                                    <td width="170"><font color="#003194" size="2px" style="Tahoma"><b>Debit </b></font></td>
                                    <td width="2"><font color="#003194" size="2px" style="Tahoma"><b>:</b></font></td>
                                    <td><font size="2">RM <s:text name="debit" formatPattern="#,##0.00" id="debit"
                                            readonly="true" style="background:transparent;border:0 px;" size="15"/></font></td>
                                </c:otherwise>
                            </c:choose>
                        </tr>
                        <tr>
                            <c:choose>
                                <c:when test="${actionBean.checking}">
                                    <td width="170"><font color="#003194" size="2px" style="Tahoma"><b>Kredit</b></font></td>
                                    <td width="2"><font color="#003194" size="2px" style="Tahoma"><b>:</b></font></td>
                                    <td><font size="2">RM <s:text name="kredit" formatPattern="#,##0.00"
                                            onclick="click1('kredit')" onblur="blur1('kredit')" id="kredit"
                                            style="background:transparent;border:0 px;cursor:pointer" size="15"/></font></td>
                                </c:when>
                                <c:otherwise>
                                    <td width="170"><font color="#003194" size="2px" style="Tahoma"><b>Kredit </b></font></td>
                                    <td width="2"><font color="#003194" size="2px" style="Tahoma"><b>:</b></font></td>
                                    <td><font size="2">RM <s:text name="kredit" formatPattern="#,##0.00" id="kredit"
                                            readonly="true" style="background:transparent;border:0 px;" size="15"/></font></td>
                                </c:otherwise>
                            </c:choose>
                            <td width="5">&nbsp;</td>
                            <td width="170"><font color="#003194" size="2px" style="Tahoma"><b>Tunggakan Denda Lewat </b></font></td>
                            <td width="2"><font color="#003194" size="2px" style="Tahoma"><b>:</b></font></td>
                            <td><font size="2">RM <s:text name="denda" formatPattern="#,##0.00"
                                    onclick="click1('denda')" onblur="blur1('denda')" id="denda"
                                    style="background:transparent;border:0 px;cursor:pointer" size="15"/></font></td>
                        </tr>
                        <tr>
                            <td width="170"><font color="#003194" size="2px" style="Tahoma"><b>Tunggakan Cukai Tanah</b></font></td>
                            <td width="2"><font color="#003194" size="2px" style="Tahoma"><b>:</b></font></td>
                            <td><font size="2">RM <s:text name="tunggakan" formatPattern="#,##0.00"
                                    onclick="click1('tunggakan')" onblur="blur1('tunggakan')" id="tunggakan"
                                    style="background:transparent;border:0 px;cursor:pointer;" size="15"/></font></td>
                            <td width="5">&nbsp;</td>
                            <td width="170"><font color="#003194" size="2px" style="Tahoma"><b>Remisyen </b></font></td>
                            <td width="2"><font color="#003194" size="2px" style="Tahoma"><b>:</b></font></td>
                            <td><font size="2">RM <s:text name="remisyen" formatPattern="#,##0.00"
                                    onclick="click1('remisyen')" onblur="blur1('remisyen')" id="remisyen"
                                    style="background:transparent;border:0 px;" size="15"/></font></td>
                        </tr>
                        <tr>
                            <td width="170"><font color="#003194" size="2px" style="Tahoma"><b>Notis 6A </b></font></td>
                            <td width="2"><font color="#003194" size="2px" style="Tahoma"><b>:</b></font></td>
                            <td><font size="2">RM <s:text name="notis" formatPattern="#,##0.00"
                                    onclick="click1('notis')" onblur="blur1('notis')" id="notis"
                                    style="background:transparent;border:0 px;" size="15"/></font></td>
                            <td width="5">&nbsp;</td>
                            <td width="170"><font color="#003194" size="2px" style="Tahoma"><b>&nbsp; </b></font></td>
                            <td width="2"><font color="#003194" size="2px" style="Tahoma"><b>&nbsp;</b></font></td>
                            <td><font size="2">&nbsp;</font></td>
                        </tr>
                    </table>
                    <hr width="60%">
                    <table align="center" border="0">
                        <tr>
                            <td width="90"><font color="#003194" size="2px" style="Tahoma"><b>Jumlah Besar </b></font></td>
                            <td width="2"><font color="#003194" size="2px" style="Tahoma"><b>:</b></font></td>
                            <td><font size="2">RM <s:text name="total" id="total" formatPattern="0.00" style="background:transparent;border:0 px;" readonly="true"/></font></td>
                        </tr>
                    </table>
                    <hr width="60%">
                </div>--%>
                <p></p>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <a onmouseover="this.style.cursor = 'pointer';" onclick="addRow('${actionBean.rbtAkaun}');">
                    <img src='${pageContext.request.contextPath}/pub/images/tambah.png' title="Sila Klik Untuk Tambah Transaksi Baru"  />
                    Sila Klik Untuk Tambah Transaksi Baru
                </a>
                <p>
                    <label> Cukai yang perlu dijelaskan (RM) : </label>
                    <fmt:formatNumber value="${actionBean.akaun.baki}" pattern="#,###,##0.00" />&nbsp;
                    <s:text name="totalAmaun" value="${actionBean.akaun.baki}" style="display:none;"/>
                    <c:if test="${actionBean.checkingBaki}">
                        <s:submit name="Step8" value="Kemaskini" class="btn" id="updt"/>
                    </c:if>
                </p>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.transList}" id="line">
                        <c:set var="row_num" value="${row_num+1}"/>
                        <display:column title="Bil" sortable="true"><div align="center">${row_num}.</div></display:column>
                        <display:column title="Tarikh Transaksi">
                            <fmt:formatDate value="${line.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy hh:mm:ss a"/>
                        </display:column>
                        <display:column title="Transaksi Penyata" property="kodTransaksi.nama" style="width:300px;"/>
                        <c:if test="${line.dokumenKewangan.noRujukanManual ne null}">
                            <display:column  title="No Resit"  >
                                ${line.dokumenKewangan.noRujukanManual}&nbsp;
                            </display:column>
                        </c:if>
                        <c:if test="${line.dokumenKewangan.noRujukanManual eq null}">
                            <display:column  title="No Resit"  >
                                ${line.dokumenKewangan.idDokumenKewangan}&nbsp;
                            </display:column>
                        </c:if>
                        <%--<display:column title="No. Resit" property="dokumenKewangan.idDokumenKewangan"/>--%>
                        <display:column title="Tahun" property="untukTahun"/>
                        <display:column title="Akaun Debit (RM)" style="text-align:right;">
                            <c:if test="${line.status.kod eq 'A' and line.kodTransaksi.kod ne '99000' and line.kodTransaksi.kod ne '99001'
                                          and line.kodTransaksi.kod ne '99002' and line.kodTransaksi.kod ne '99003' and line.kodTransaksi.kod ne '99030' }">
                                <fmt:formatNumber value="${line.amaun}" pattern="#,###,##0.00"/>
                                <%--<c:set var="amt" value="${line.amaun}"/>
                                <s:text name="line.amaun" formatPattern="#,##0.00" value="${amt}"
                                onclick="click1('notis')" onblur="blur1('notis')" id="notis"
                                style="background:transparent;border:0 px;" size="10"/>--%>
                                <a id="" onclick="editRow(this.form, '${line.idTransaksi}', '${actionBean.rbtAkaun}');" onmouseover="this.style.cursor = 'pointer';">
                                    <img title="Sila Klik Untuk Kemaskini Amaun" src='${pageContext.request.contextPath}/pub/images/edit.gif' />
                                </a>
                            </c:if>
                        </display:column>
                        <display:column title="Akaun Kredit (RM)" style="text-align:right;">
                            <%--<c:if test="${line.status.kod ne 'A'}">--%>
                            <c:if test="${(line.status.kod eq 'A' and line.kodTransaksi.kod eq '99030' or line.kodTransaksi.kod eq '99000' or line.kodTransaksi.kod eq '99001'
                                          or line.kodTransaksi.kod eq '99002' or line.kodTransaksi.kod eq '99003') or  line.status.kod eq 'T' or  line.status.kod eq 'B'}">
                                <fmt:formatNumber value="${line.amaun}" pattern="#,###,##0.00"/>
                                <%--<c:if test="${line.dokumenKewangan.idDokumenKewangan eq null}">--%>
                                <a id="" onclick="editRow(this.form, '${line.idTransaksi}', '${actionBean.rbtAkaun}');" onmouseover="this.style.cursor = 'pointer';">
                                    <img title="Sila Klik Untuk Kemaskini Amaun" src='${pageContext.request.contextPath}/pub/images/edit.gif' />
                                </a>
                            </c:if>
                            <%--<c:if test="${line.status.kod eq 'A' and line.kodTransaksi.kod eq '99002' or line.kodTransaksi.kod eq '99000'
                                                                                                       or line.kodTransaksi.kod eq '99001' or line.kodTransaksi.kod eq '99003'}">
                                <fmt:formatNumber value="${line.amaun}" pattern="#,###,##0.00"/>
                            </c:if>--%>
                        </display:column>
                        <display:column title="Status" property="status.nama"></display:column>

                    </display:table>
                </div>
            </fieldset>

        </div><s:text id="total" name="sum" style="display:none;" />
        <br><s:textarea name="catatan" cols="38" rows="10" id="mesej1" style="display:none;"/>
    </div>
    <div align="center"><table style="width:99.2%" bgcolor="green">
            <tr>
                <td align="right">
                    <%--<s:button name="" value="Simpan" class="btn" id="simpan" onclick="javaScript:simpan(this.form)"/>--%>
                    <s:submit name="Step9" value="Simpan" class="btn" id="simpan5" style="display:none;"/>
                    <s:submit name="Step5" value="Menu Utama" class="btn" id="main"/>
                    <s:submit name="Step1" value="Kembali" class="btn" id="back"/>
                </td>
            </tr>
        </table></div>
    </s:form>
