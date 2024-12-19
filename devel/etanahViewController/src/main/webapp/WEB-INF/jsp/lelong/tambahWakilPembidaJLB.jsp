<%-- 
    Document   : tambahPembidaBaru
    Created on : Apr 27, 2012, 3:55:20 PM
    Author     : mazurahayati.yusop, nur.aqilah
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script>
    $(document).ready(function() {
        $("#simpan").click(function(evt) {

            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top: ($(window).height() - 50) / 2 + 'px',
                    left: ($(window).width() - 50) / 2 + 'px',
                    width: '50px'
                }
            });
            var mohonId = $('#idPermohonan').val();
            var q = $("form").serialize();
            var url = '?' + $(this).attr("name");

            $.post(url, q, function(data) {
                if (data == '1') {
                    alert("Maklumat Telah berjaya di simpan");
                    self.close();
                }
                self.opener.refreshingPagingFolder(mohonId);

            });
        });
    });

    $(document).ready(function() {
        $("#tarikPermohonan").hide();
        $("#nokp2").hide();
        $("#close").click(function() {
            setTimeout(function() {
                self.close();
            }, 100);
        });
    });

    function validateNewICLength(value) {
        var plength = value.length;
        if (plength != 12) {
            alert('No Kad Pengenalan yang dimasukkan salah.');
            $('#nokp1').val("");
            $('#nokp1').focus();
        }
    }

    function validation() {
        if ($("#nama").val() == "") {
            alert('Sila masukkan " Nama " terlebih dahulu.');
            $("#nama").focus();
            return false;
        }
        if ($("#jenis").val() == "") {
            alert('Sila pilih " Jenis Kad Pengenalan " terlebih dahulu.');
            $("#jenis").focus();
            return false;
        }

        if ($("#jenis").val() == "B") {
            if ($("#nokp1").val() == "") {
                alert('Sila masukkan " Nombor Pengenalan " terlebih dahulu.');
                $("#nokp1").focus();
                return false;
            }
        }
        if ($("#jenis").val() != "B") {
            if ($("#nokp2").val() == "") {
                alert('Sila masukkan " Nombor Pengenalan " terlebih dahulu.');
                $("#nokp2").focus();
                return false;
            }
        }
        return true;
    }

    function validateNumber(elmnt, content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = RemoveNonNumeric(content);
            return;
        }
    }

    function validateTelLength(value) {
        var plength = value.length;
        if (plength < 7) {
            alert('No. Telefon yang dimasukkan salah.');
            $('#telefon').val("");
            $('#telefon').focus();
        }
    }


    function changeNOKP(val) {
        var no = val;
        if (no == 'B') {
            $("#nokp2").hide();
            $("#nokp1").show();
        } else {
            $("#nokp2").show();
            $("#nokp1").hide();
        }
    }

    function validateIC(icno, idPermohonan) {

        $.get("${pageContext.request.contextPath}/lelong/senaraipembida?doCheckIC&icno=" + icno, "&idPermohonan=" + idPermohonan,
                function(data) {
                    if (data == '1') {
                        //                alert("Kad Pengenalan No " + icno + " telah wujud!");
                        alert("Kad Pengenalan No " + icno + " telah di Senarai Hitamkan");
                        $("#nokp1").val("");
                        $("#nokp1").focus();
                        return false;
                    }
                });
        return true;
    }

    function save(event, f, idPembida, idPermohonan) {

        $.blockUI({
            message: $('#displayBox'),
            css: {
                top: ($(window).height() - 50) / 2 + 'px',
                left: ($(window).width() - 50) / 2 + 'px',
                width: '50px'
            }
        });

        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url, q,
                function(data) {
                    $('#page_div', opener.document).html(data);
                    $.unblockUI();
                    self.close();
                }, 'html');
    }
</script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif"
     width="50" height="50" style="display: none" alt=""/>
<s:form name="pemohon" id="pemohon"  beanclass="etanah.view.stripes.lelong.UtilitiSenaraiPermohonanLelonganPembidaJLBActionBean">

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Pihak Terlibat</legend>

            <c:if test="${!empty moreThanOneHakmilik}">
                <p>
                    <label>&nbsp;</label>
                    <font color="red" size="2">
                    <input type="checkbox" name="copyToAll" value="1"/>
                    <em>Sila klik jika sama untuk semua hakmilik.</em>
                    </font>
                </p>
            </c:if>

            <div class="content" align="center">
                <table class="tablecloth" cellpadding="0" cellspacing="0">
                    <thead>
                        <tr>
                            <th>Bil</th>
                            <th>Jenis Medan</th>

                            <th>Maklumat Pembida</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr class="odd">
                            <td>1.</td>
                            <td>Nama</td><s:hidden name="idHakmilik"/>
                            <s:hidden name="namaLama" value="${actionBean.pembida.wakilPihak.nama}"/>
                            <s:hidden name="idPihak" value="${actionBean.pihak.idPihak}"/></td>
                            <td>
                                <s:textarea name="nama" onkeyup="this.value=this.value.toUpperCase()" rows="5" cols="50" value="${actionBean.pembida.wakilPihak.nama}"/>
                            </td>
                        </tr>
                        <tr class="even">
                            <td>2.</td>
                            <td>Jenis Pengenalan</td>
                            <s:hidden name="jeniskpLama" value="${actionBean.pembida.wakilPihak.jenisPengenalan.kod}"/>
                            <td>
                                <s:select name="jeniskp" value="${actionBean.pembida.wakilPihak.jenisPengenalan.kod}">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                                </s:select>
                            </td>
                        </tr>
                        <tr class="odd">
                            <td>3.</td>
                            <td>No Pengenalan</td>
                            <s:hidden name="nokpLama" value="${actionBean.pembida.wakilPihak.noPengenalan}"/>
                            <td><s:text name="nokp" onkeyup="this.value=this.value.toUpperCase();" value="${actionBean.pembida.wakilPihak.noPengenalan}"/></td>
                        </tr>  
                        <tr class="odd">
                            <td>4.</td>
                            <td>No Telefon Bimbit</td>
                            <s:hidden name="noHPLama" value="${actionBean.pembida.wakilPihak.pihak.noTelefonBimbit}"/>
                            <td><s:text name="noHP" onkeyup="this.value=this.value.toUpperCase();" value="${actionBean.pembida.wakilPihak.pihak.noTelefonBimbit}"/></td>
                        </tr>  
                        <tr class="odd">
                            <td>4.</td>
                            <td>No. Bank Draf</td>
                            <s:hidden name="noBanklama" value="${actionBean.pembida.noRujukan}"/>
                            <td><s:text name="noBank" onkeyup="this.value=this.value.toUpperCase();" value="${actionBean.pembida.noRujukan}"/></td>
                        </tr>
                        ${actionBean.pembida.hakmilikPermohonan.permohonan.idPermohonan}
                        <s:hidden name="idPembida" value="${actionBean.pembida.idPembida}"/>
                        <s:hidden name="idPermohonan" id="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
                    </tbody>
                </table>
            </div>


            <center>

                <s:hidden name="idWakil" value="${actionBean.pembida.wakilPihak.idWakil}"/>
                <s:button name="simpanWakil" id="simpan" value="Simpan" class="btn" onclick="save(this.name, this.form, '${actionBean.pembida.idPembida}', '${actionBean.permohonan.idPermohonan}');"/>
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </center>
        </fieldset >
    </div>

</s:form>

