<%--
    Document   : pembetulanPemilik
    Created on : May 10, 2010, 4:08:06 PM
    Author     : wan.fairul
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
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
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/popup.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-impromptu.js"></script>
<style type="text/css">
    td.s{
        font-weight:normal;
        color:black;
        text-align: left;

    }
    input, select{width:95%}
    td{
        font-size: 100% !important;
    }
    .arrow { background:transparent url(${pageContext.request.contextPath}/pub/images/arrows.png) no-repeat scroll 0px -16px; width:100%; height:16px; display:block;}
    .up { background-position:0px 0px;}
</style>
<script language="javascript">
    var globalFlag = false;
    $(document).ready(function() {
        
    alert("PERINGATAN PENTING\n1.Sila isikan yang berkenaan sahaja untuk pembetulan.\n2.Sekiranya nilai yang baru itu adalah kosong, masukkan nilai seperti '-'.");

    <%--set focus--%>
            $('input').focus(function() {
                $(this).addClass("focus");
            });

            $('input').blur(function() {
                $(this).removeClass("focus");
            });

            $('select').focus(function() {
                $(this).addClass("focus");
            });

            $('select').blur(function() {
                $(this).removeClass("focus");
            });

            $('#copy').click(function() {
                if (this.checked)
                {
                    $('#alamatsurat1').val($('#alamat1').val());
                    $('#alamatsurat2').val($('#alamat2').val());
                    $('#alamatsurat3').val($('#alamat3').val());
                    $('#alamatsurat4').val($('#alamat4').val());
                    $('#poskodsurat').val($('#poskod').val());
                    $('#negerisurat').val($('#negeri').val());

                }
            });


        });


        function getDIVID(idName)
        {
            poskodValidate(idName);
        }

        function poskodValidate(idPoskod)
        {

            var numberRegex = /^[+-]?\d+(\.\d+)?([eE][+-]?\d+)?$/;
            var str = $('#' + idPoskod).val();
            if (str.length == 5) {
                if (!numberRegex.test(str)) {

                    $('.' + idPoskod).html("*Sila betulkan kesalahan");
                    $('#' + idPoskod).focus();
                }
                if (numberRegex.test(str)) {

                    $('.' + idPoskod).html("");
                    globalFlag = true;
                }
            }
        }

        function save(event, f, idHakmilikPihakBerkepentingan, idPihak)
        {

            var q = $(f).formSerialize();
            var url = f.action + '?' + event + '&idHakmilikPihakBerkepentingan=' + idHakmilikPihakBerkepentingan + '&idPihak=' + idPihak;

            $.post(url, q,
                    function(data) {
                        $('#page_div', opener.document).html(data);
                        self.close();
                    }, 'html');

        }

        function semakSyer(id) {
            var url = '${pageContext.request.contextPath}/daftar/pembetulan/PihakBerkepentingan?semakSyer&idpihak=' + id
                    + '&pembilang=' + s1 + '&penyebut=' + s2;
            $.post(url,
                    function(data) {
                    }, 'html');

        }

        function samaRata(f) {
            var q = $(f).formSerialize();
            $.get('${pageContext.request.contextPath}/daftar/pembetulan/PihakBerkepentingan?agihSamaRata', q,
                    function(data) {
                        if (data == null || data.length == 0) {
                            alert('Terdapat Masalah');
                            return;
                        }
                        var p = data.split(DELIM);
                        $('.pembilang').each(function() {
                            $(this).val(p[0]);
                        });
                        $('.penyebut').each(function() {
                            $(this).val(p[1]);
                        });
                    });
        }

        function updateSyer(idpihak) {
            var s1 = $('#pembilang').val();
            var s2 = $('#penyebut').val();

            if (s1 == '' || s2 == '') {
                return;
            }

            if (isNaN(s1) && isNaN(s2)) {
                return;
            }
            var url = '${pageContext.request.contextPath}/daftar/pembetulan/PihakBerkepentingan?updateSyerMohonPihak&idpihak=' + idpihak
                    + '&pembilang=' + s1 + '&penyebut=' + s2;
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

</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form name="form1" beanclass="etanah.view.stripes.nota.PihakBerkepentinganActionBean">
    <!--D:\etanah3\etanahViewController\src\main\java\etanah\view\stripes\nota\PihakBerkepentinganActionBean.java-->
    <div id="page_div">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Maklumat Pihak
                </legend>

                <p style="color:red">
                    *Sila isikan yang berkenaan sahaja untuk pembetulan.Sekiranya nilai yang baru itu adalah kosong, masukkan nilai seperti '-'</p>
                
                <div align="center">

                    <table class="tablecloth" width="70%">
                        <tr><th colspan="3">Butiran Pihak Berkepentingan</th></tr>
                        <tr><th>Perkara</th><th>Maklumat Lama</th><th>Maklumat Baru</th></tr>
                        <tr><td>Nama :</td><td class="s">${actionBean.mhnphk.nama}</td>
                                <td class="s">
                                <s:text name="nama" onkeyup="this.value=this.value.toUpperCase();"/>
                            </td></tr>

                        <tr><td>Jenis Pengenalan :</td><td class="s">${actionBean.mhnphk.jenisPengenalan.nama}</td>
                                <td class="s">
                                <s:select name="jenisPengenalan" id="" value="kod" >
                                    <s:option value="">Pilih ...</s:option>
                                    <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod" />
                                </s:select>
                            </td></tr>

                        <tr><td>No. Pengenalan :</td><td class="s">${actionBean.mhnphk.noPengenalan}</td>
                                <td class="s">
                                <s:text name="noPengenalan" maxlength="25" onkeyup="this.value=this.value.toUpperCase();"/>
                            </td></tr>
                        <tr><td>Warganegara :</td><td class="s">${actionBean.mhnphk.wargaNegara.nama}</td>
                            <td class="s">
                                <s:select name="warganegara" id="pemilikWarganegara" value="kod">
                                    <s:option value="">Pilih ...</s:option>
                                    <s:options-collection collection="${listUtil.senaraiWarganegara}" label="nama" value="kod" />
                                </s:select>
                            </td></tr>
                        <tr><td>Jenis Pihak Berkepentingan :</td><td class="s">${actionBean.mhnphk.jenis.nama}</td>
                            <td class="s">
                                <s:select name="jenisPB" value="kod">
                                    <s:option value="">Pilih ...</s:option>
                                    <s:options-collection collection="${listUtil.senaraiKodJenisPihakBerkepentingan}" label="nama" value="kod" />
                                </s:select>
                            </td></tr>
                            <%--  <tr><td>Kumpulan Pihak Berkepentingan :</td><td class="s">&nbsp;</td>
                                  <td class="s">
                                      <s:select name="kumpulanPB" value="kod">
                                          <s:option value="">Pilih ...</s:option>
                                          <s:options-collection collection="${listUtil.senaraiKodJenisPihakBerkepentingan}" label="nama" value="kod" />
                                      </s:select>
                                  </td></tr>
                              <tr><td>Ruj. Surat Amanah :</td><td class="s">&nbsp;</td>
                                  <td class="s">
                                      <s:text name="noSuratAmanah"/>
                                  </td></tr>--%>
                        <tr><td>Pembilang / Penyebut  :</td><td class="s">${actionBean.mhnphk.syerPembilang}/${actionBean.mhnphk.syerPenyebut}</td>
                            <td class="s">

                                <s:text name="pembilang" class="pembilang" 
                                        onblur="updateSyer('${actionBean.mhnphk.idPermohonanPihak}')" onchange="updateSyer('${actionBean.mhnphk.idPermohonanPihak}'" style="width:46% !important;"/> / <s:text name="penyebut" class="penyebut" onblur="updateSyer('${mhnphk.idPermohonanPihak}')" onchange="updateSyer('${mhnphk.idPermohonanPihak}'" style="width:46% !important;"/>
                            </td></tr>
                        <tr><td>Syarikat Tubuh :</td><td class="s">${actionBean.mhnphk.penubuhanSyarikat.nama}</td>
                            <td class="s">

                                <s:select name="syarikatTubuh" id="" value="kod" >
                                    <s:option value="">Pilih ...</s:option>
                                    <s:options-collection collection="${listUtil.senaraiKodPenubuhanSyarikat}" label="nama" value="kod" />
                                </s:select>
                            </td></tr>
                            <%--  <tr><td colspan="3"><s:button class="btn" value="Semak Syer" name="semak" id="semak" onclick="semakSyer('${pihak.idPihak}');"/>&nbsp;
                                      <s:button class="longbtn" value="Agih Sama Rata" name="" id="_agihSamaRata" onclick="samaRata(this.form);"/>&nbsp;   </td></tr>--%>
                            <%--start alamat--%>
                        <tr><td colspan="3">&nbsp;</td></tr>
                        <tr><th colspan="3">Alamat Pihak Berkepentingan</th></tr>
                        <tr><th colspan="3">Alamat Tetap</th></tr>
                        <tr><td>Alamat :</td><td class="s">${actionBean.mhnphk.alamat.alamat1}</td>
                                <td class="s">
                                <s:text name="alamat1" id="alamat1"/>
                            </td></tr>

                        <tr><td>&nbsp;</td><td class="s">${actionBean.mhnphk.alamat.alamat2}</td>
                                <td class="s">
                                <s:text name="alamat2" id="alamat2"/>
                            </td></tr>

                        <tr><td>&nbsp;</td><td class="s">${actionBean.mhnphk.alamat.alamat3}</td>
                                <td class="s">
                                <s:text name="alamat3" id="alamat3"/>
                            </td></tr>

                        <tr><td>&nbsp;</td><td class="s">${actionBean.mhnphk.alamat.alamat4}</td>
                                <td class="s">
                                <s:text name="alamat4" id="alamat4"/>
                            </td></tr>

                        <tr><td>Poskod :</td><td class="s">${actionBean.mhnphk.alamat.poskod}</td>
                                <td class="s">
                                <s:text name="poskod" id="poskod" maxlength="5" formatType="number" onkeyup="getDIVID(this.id);"/>
                                <div class="poskod" style="color:red; font-size:8pt; margin:0px;"></div>
                            </td></tr>

                        <tr><td>Negeri :</td><td class="s">${actionBean.mhnphk.alamat.negeri.nama}</td>
                                <td class="s">
                                <s:select name="negeri" id="negeri" value="kod">
                                    <s:option value="">Pilih ...</s:option>
                                    <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                                </s:select>
                            </td></tr>
                        <tr><td colspan="3">
                                <div align="center">
                                    <s:button name="saveBetulMohonPihak" value="Simpan" class="btn" onmouseover="this.style.cursor='pointer';" onclick="save(this.name, this.form, '${actionBean.mhnphk.idPermohonanPihak}', '${actionBean.mhnphk.idPermohonanPihak}')"/>
                                    <s:button name="tutup" value="Tutup" class="btn" onclick="javascript:self.close()"/>
                                </div>
                            </td></tr>
                    </table>
                    <br/>
                </div>
            </fieldset>
        </div>
    </div>
</s:form>