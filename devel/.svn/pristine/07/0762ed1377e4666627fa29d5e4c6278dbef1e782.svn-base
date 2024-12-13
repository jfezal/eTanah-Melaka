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
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>

<style type="text/css">
    td.s{
        font-weight:normal;
        color:black;
        text-align: left;
    }
    input , select {
        width: 95%;
    }
    td{
        font-size: 100% !important;
    }
    .arrow { background:transparent url(${pageContext.request.contextPath}/pub/images/arrows.png) no-repeat scroll 0px -16px; width:100%; height:16px; display:block;}
    .up { background-position:0px 0px;}

</style>

<script language="javascript">
    $(document).ready(function () {
        $('.alphanumeric').alphanumeric();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});

        $('input').focus(function () {
            $(this).addClass("focus");
        });

        $('input').blur(function () {
            $(this).removeClass("focus");
        });

        $('select').focus(function () {
            $(this).addClass("focus");
        });

        $('select').blur(function () {
            $(this).removeClass("focus");
        });

    });

    function save(event, f, idH)
    {
        var q = $(f).formSerialize();
        var url = f.action + '?' + event + '&idH=' + idH;

        $.post(url, q,
                function (data) {
                    $('#page_div').html(data);
                }, 'html');
    }

    function showMe(thID) {

        $('#' + thID).toggle();
        $('.' + thID).find(".arrow").toggleClass("up");
    }

    function filterKodGunaTanah() {
        var katTanah = $("#katTanah").val();
        $.post('${pageContext.request.contextPath}/daftar/pembetulan/betul?senaraiKodGunaTanahByKatTanah&kodKatTanah=' + katTanah,
                function (data) {
                    if (data != '') {
                        $('#partialKodGunaTanah').html(data);
                        $.unblockUI();
                    }
                }, 'html');

    }

    function validateNumber(elmnt, content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = RemoveNonNumeric(content);
            return;
        }
    }

</script>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form name="form1" beanclass="etanah.view.stripes.nota.pembetulanSTRActionBean">
    <div id="page_div">
        <s:messages />
        <s:errors />

        <div class="subtitle">

            <fieldset class="aras1">
                <legend>
                    Maklumat Perbadanan PengurusanDSDS
                </legend>
                <p style="color:red">
                    *Isi ruang pembetulan kemudian tekan butang simpan.<br/>

                </p>
                <div align="center">
                    <table class="tablecloth">
                        <tr><th colspan="3">ID Hakmilik - ${actionBean.hakmilik.idHakmilikInduk}</th></tr>
                                <s:hidden name = "idH" value ="${actionBean.hakmilik.idHakmilikInduk}"/>
                        <tr><th colspan="3">Butiran Perbadanan Pengurusan</th></tr>
                        <tr><td colspan="3"><table class="tablecloth">
                                    <tr><th width="10%">Perkara</th><th width="30%">Maklumat Lama</th><th width="80%">Maklumat Baru</th></tr>
                                    <tr><td>Nama Perbadanan Pengurusan:</td><td class="s">${actionBean.badanUrus.nama}</td>
                                        <td><s:text name="namaBdnUrus" value="namaBdnUrus" onkeyup="this.value=this.value.toUpperCase();" maxlength="250"/></td>
                                    </tr>

                        </tr>
                        <tr><td>Alamat Berdaftar:</td><td class="s">${actionBean.badanUrus.alamat1}</td>
                            <td class="s"><s:text name="alamat1BdnUrus" value="alamat1BdnUrus"  maxlength="100"/></td>
                        </tr>
                        <tr><td></td><td class="s">${actionBean.badanUrus.alamat2}</td>
                            <td class="s"><s:text name="alamat2BdnUrus" value="alamat2BdnUrus" maxlength="100" onkeyup="this.value=this.value.toUpperCase();"/></td></tr>
                        <tr><td></td><td class="s">${actionBean.badanUrus.alamat3}</td>
                            <td class="s"><s:text name="alamat3BdnUrus" value="alamat3BdnUrus" maxlength="100" onkeyup="this.value=this.value.toUpperCase();"/></td></tr>
                        <tr><td></td><td class="s">${actionBean.badanUrus.alamat4}</td>
                            <td class="s"><s:text name="alamat4BdnUrus" value="alamat4BdnUrus" maxlength="100" onkeyup="this.value=this.value.toUpperCase();"/></td></tr>
                        <tr><td></td><td class="s">${actionBean.badanUrus.poskod}</td>
                            <td class="s"><s:text name="poskodBdnUrus" value="poskodBdnUrus" maxlength="5" onkeyup="this.value=this.value.toUpperCase();"/></td></tr>
                        <tr><td></td><td class="s">${actionBean.badanUrus.negeri.nama}</td>
                            <td class="s">
                                <s:select name="negeriBdnUrus" value="negeriBdnUrus">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod" />
                                </s:select>
                            </td></tr>

                        <tr><td>Alamat Surat Menyurat:</td><td class="s">${actionBean.badanUrus.suratAlamat1}</td>
                            <td class="s"><s:text name="alamat1SuratBdnUrus" value="alamat1SuratBdnUrus" maxlength="100" onkeyup="this.value=this.value.toUpperCase();"/></td>
                        </tr>
                        <tr><td></td><td class="s">${actionBean.badanUrus.suratAlamat2}</td>
                            <td class="s"><s:text name="alamat2SuratBdnUrus" value="alamat2SuratBdnUrus" maxlength="100" onkeyup="this.value=this.value.toUpperCase();"/></td></tr>
                        <tr><td></td><td class="s">${actionBean.badanUrus.suratAlamat3}</td>
                            <td class="s"><s:text name="alamat3SuratBdnUrus" value="alamat3SuratBdnUrus" maxlength="100" onkeyup="this.value=this.value.toUpperCase();"/></td></tr>
                        <tr><td></td><td class="s">${actionBean.badanUrus.suratAlamat4}</td>
                            <td class="s"><s:text name="alamat4SuratBdnUrus" value="alamat4SuratBdnUrus" maxlength="100" onkeyup="this.value=this.value.toUpperCase();"/></td></tr>
                        <tr><td></td><td class="s">${actionBean.badanUrus.suratPoskod}</td>
                            <td class="s"><s:text name="poskodSuratBdnUrus" value="poskodSuratBdnUrus" maxlength="5" onkeyup="this.value=this.value.toUpperCase();"/></td></tr>
                        <tr><td></td><td class="s">${actionBean.badanUrus.suratNegeri.nama}</td>
                            <td class="s">
                                <s:select name="negeriSuratBdnUrus" value="negeriSuratBdnUrus">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod" />
                                </s:select>
                            </td></tr>
                    </table></td></tr>

                    <tr><td>
                            <div align="center">
                                <s:submit name="saveBdnUrus" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                                <s:button name="tutup" value="Tutup" class="btn" onclick="javascript:self.close()"/>
                            </div>
                        </td></tr>
                    </table>
                </div>
            </fieldset>
        </div>
    </div>
</s:form>


