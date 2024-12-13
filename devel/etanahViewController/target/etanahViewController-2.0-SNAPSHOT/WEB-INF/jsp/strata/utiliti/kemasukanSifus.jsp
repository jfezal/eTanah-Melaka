<%-- 
    Document   : kemasukanSifus
    Created on : Aug 18, 2016, 4:39:27 PM
    Author     : siti.mudmainnah
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt"    prefix="c" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script language="javascript" type="text/javascript">
    function doCetak(f) {
        var idHakmilik = $("#idHakmilik").val();
        var noRujukan = $("#noRujukan").val();
        var report = 'STRSijilSIFUS_MLK.rdf';

        window.open("${pageContext.request.contextPath}/reportGeneratorServlet?" + "&reportName=" + report + "&report_p_id_hakmilik="
                + idHakmilik + "&report_p_no_ruj_fail=" + noRujukan, "eTanah",
                "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");

        $.unblockUI();

    }
    function popup(idProjek, idHakmilik)
    {
        window.open("${pageContext.request.contextPath}/strata/KemasukkanSifus?edit&idProjek=" + idProjek + "&idHakmilik=" + idHakmilik, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,scrollbars=1,width=910,height=600");
    }

    function popup2(idHakmilik)
    {
        window.open("${pageContext.request.contextPath}/strata/KemasukkanSifus?addNew&idHakmilik=" + idHakmilik, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,scrollbars=1,width=1000,height=600");
    }
    function hapus(idProjek, idHakmilik)
    {
        if (confirm('Adakah anda pasti untuk hapus Sijil MC ini')) {
            var url = '${pageContext.request.contextPath}/strata/KemasukkanSifus?hapusSifus&idProjek=' + idProjek + '&idHakmilik=' + idHakmilik;
            $.get(url,
                    function (data) {
                        $('#page_div').html(data);
                    }, 'html');
            alert('Sijil MC berjaya dihapuskan. Sila tekan butang "Refresh" untuk refresh skrin.');
        }
    }

    function reset1()
    {
        $("#idHakmilik").val('');
        $("#noRujukan").val('');
    }
</script>

<s:messages />
<s:errors />
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>
<s:form beanclass="etanah.view.strata.utiliti.KemasukanSifus" id="form1">


    <fieldset class="aras1">
        <legend>Kemasukan Sijil SiFUS (Manual)</legend>

        <p>
            <label>Id Hakmilik :</label>
            <s:text name="idHakmilik" onkeyup="this.value=this.value.toUpperCase();" id="idHakmilik"/>

        </p>
        <p>
            <label>atau</label>
        </p>
        <br />
        <p>
            <label>No Rujukan Fail :</label>
            <s:text name="noRujukan" onkeyup="this.value=this.value.toUpperCase();" id="noRujukan"/>

        </p>
        <p>
            <label>&nbsp;</label><s:submit name="cari" value="Cari" class="btn"/>
            <c:if test="${fn:length(actionBean.listSifus) > 0}">
                <s:submit name="cari" value="Refresh" class="btn"/>
            </c:if>
            <s:submit name="rehydrate" value="Isi Semula" class="btn" onclick="reset1();" />
            <c:if test="${add}">
                <br />
                <br />
                <br />
            <p>
                <label>&nbsp;</label><s:button name="addNew" value="Tambah" class="btn" onclick="popup2('${actionBean.idHakmilik}');"/>
            </p>
        </c:if>
    </p>

    <c:if test="${fn:length(actionBean.listSifus) > 0}">
        <br />
        <p>
        <center>
            <display:table style="width:80%" class="tablecloth" name="${actionBean.listSifus}" pagesize="100" cellpadding="0" cellspacing="0" id="line">
                <display:column title="Id Hakmilik" property="idHakmilik" />
                <display:column title="Pemohon" property="namaPemaju" />
                <display:column title="Pemilik" property="pemilik" />
                <display:column title="Nama Skim" property="jenisProjek" />
                <display:column title="No.Siri" property="maksimumUnit" />
                <display:column title="No.Rujukan Fail" property="noRujFail" />
                <display:column title="Status">
                    <c:if test = "${line.aktif eq 'T'}">
                        Tidak Aktif
                    </c:if>
                    <c:if test = "${line.aktif eq 'Y'}">
                        Aktif
                    </c:if>
                    <c:if test = "${line.aktif eq 'M'}">
                        Mohon
                    </c:if>
                    <c:if test = "${line.aktif eq 'B'}">
                        Batal
                    </c:if>
                </display:column>
                <display:column title="Tindakan">
                    <c:if test = "${line.aktif ne 'M'}">
                        <div align="center">
                            <p align="center">
                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                     onclick="popup('${line.idProjek}', '${line.idHakmilik}');" onmouseover="this.style.cursor = 'pointer';">
                                |
                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif'
                                     onclick="hapus('${line.idProjek}', '${line.idHakmilik}');" onmouseover="this.style.cursor = 'pointer';">
                            </p>
                        </div>
                    </c:if>
                </display:column>
                <c:set value="${count +1}" var="count"/>
            </display:table>
            <s:button name="" value="Papar SiFUS" onclick="doCetak(this.form);" class="longbtn"/>
            <s:submit name="rehydrate" value="Kembali" class="longbtn"/>
        </c:if>
    </center>
</p>

</fieldset>
</s:form>
