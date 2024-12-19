<%--
    Document   : maklumat_hakmilik_pengambilan
    Created on : 12-Jan-2010, 18:31:55
    Author     : nordiyana
--%>

<%@page import="java.text.DecimalFormat"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<%
    DecimalFormat dcf = new DecimalFormat("#0.0000");
%>
<script type="text/javascript">
    $(document).ready(function() {


        var len = $(".daerah").length;

        for (var i = 0; i <= len; i++) {
            $('.hakmilik' + i).click(function() {
                window.open("${pageContext.request.contextPath}/pengambilan/maklumat_hakmilikpengambilan?popup&idHakmilik=" + $(this).text(), "eTanah",
                        "status=0,toolbar=0,location=0,menubar=0,width=890,height=600").focus();
            });
        }
    });

    function hapusA(frm, value1, value2) {
        var url = '${pageContext.request.contextPath}/pengambilan/borang_a_b?hapusA&idPermohonan=' + value1 + '&id=' + value2;
        if (confirm("Adakah anda pasti untuk hapus dokumen?")) {
            frm.action = url;
            frm.submit();
        }
    }
    function hapusB(frm, value1, value2) {
        var url = '${pageContext.request.contextPath}/pengambilan/borang_a_b?hapusB&idPermohonan=' + value1 + '&id=' + value2;
        if (confirm("Adakah anda pasti untuk hapus dokumen?")) {
            frm.action = url;
            frm.submit();
        }
    }

    function save(event, f) {
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url, q,
                function(data) {
                    $('#page_div', opener.document).html(data);

                }, 'html');

    }

//        function select(id) {
//            doBlockUI();
//            frm = document.form1;
//            var url = '${pageContext.request.contextPath}/pengambilan/common/borangA?kemaskiniBorangA&idPihak=' + id;
//            frm.action = url;
//            frm.submit();
//        }

    function popupBorangA(idMohon, idBorangPerPermohonanA) {
//            alert(idMH);

        window.open("${pageContext.request.contextPath}/pengambilan/borang_a_b?tambahBorangA&idPermohonan=" + idMohon + "&idBorangPerPermohonanA=" + idBorangPerPermohonanA, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1000,height=700,scrollbars=yes");
    }
    function popupBorangB(idMohon, idBorangPerPermohonanB) {
//            alert(idMH);

        window.open("${pageContext.request.contextPath}/pengambilan/borang_a_b?tambahBorangB&idPermohonan=" + idMohon + "&idBorangPerPermohonanB=" + idBorangPerPermohonanB, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1000,height=700,scrollbars=yes");
    }
    function selesai(frm,idMohon) {
        //alert(idMohon);
       var url = '${pageContext.request.contextPath}/pengambilan/borang_a_b?selesai&idPermohonan=' + idMohon;
        if (confirm("Adakah anda pasti untuk Selesai?")) {
            frm.action = url;
            frm.submit();
        }
    }
    function doViewReport(v) {
        var randomnumber = Math.floor((Math.random() * 100) + 1);
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, randomnumber, "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
    }

    function doPopupDetails(val) {
        var url = '${pageContext.request.contextPath}/common/maklumat_permohonan?view&idPermohonan=' + val + '&idHakmilik=' + idHakmilik;
        window.open(url, "eTanah", "status=0,toolbar=0,location=0,menubar=0,width=1000,height=768,scrollbars=yes");
    }



</script>

<s:form beanclass="etanah.view.stripes.pengambilan.share.common.PenyampaianBorangAActionBean" name="AB" id="AB">

    <s:messages/>
    <div class="subtitle displaytag">

        <fieldset class="aras1" id="locate">
            <legend>
                Perincian Maklumat

            </legend>

            <p align="center">

            <table class="tablecloth">
                <tr><th colspan="2">Maklumat Permohonan</th>
                    <td>
                        <p>
                            <label>    ID Permohonan :</label> ${actionBean.borangABForm.idPermohonan}
                        </p>
                        <p>
                            <label>Urusan :</label> ${actionBean.borangABForm.urusan}
                        </p>
                        <p>
                            <label>Tujuan Permohonan :</label> ${actionBean.borangABForm.tujuanPermohonan}
                        </p>
                        <p>
                            <label> Jumlah Hakmilik | Luas :</label> ${actionBean.borangABForm.jumlahHakmilik}|${actionBean.borangABForm.jumlahLuas} ${actionBean.borangABForm.luas}
                        </p>
                    </td></tr>
                <tr>
                    <th colspan="2">Maklumat Borang</th><th>Maklumat Penyampaian/Penampalan</th>

                <tr>
                <tr>
                    <th>Borang A</th>
                    <td><div class="content" align="left">
                            <p>
                                <label>    Di tandatangan oleh :</label> ${actionBean.borangABForm.tandatanganA}
                            </p>
                            <p>
                                <label>Tarikh Kelulusan MMKN :</label> ${actionBean.borangABForm.tarikhKelulusanMMKN}
                            </p>
                            <p>
                                <label>Tarikh Warta :</label> ${actionBean.borangABForm.tarikhWarta}
                            </p>
                            <p>
                                <label> Jumlah Tempat Tampal :</label> ${actionBean.borangABForm.jumTempatTampal}
                            </p>
                            <p>
                                <label> Dokumen :</label><img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                              onclick="doViewReport('${actionBean.borangABForm.borangA.idDokumen}');" height="30" width="30" alt="papar"
                                                              onmouseover="this.style.cursor = 'pointer';" title="Papar Dokumen ${actionBean.borangABForm.borangA.kodDokumen.nama}"/>

                            </p>
                        </div>
                    </td>
                    <td width="60%">
                        <div>
                            <display:table class="tablecloth" name="${actionBean.listBorangA}"
                                           cellpadding="0" cellspacing="0" requestURI="/pengambilan/penerimaan_notis_borang_BantahanMLK" id="line">
                                <display:caption>Maklumat Penampalan</display:caption>
                                <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                                <display:column title="Tarikh Tampal" sortable="true">${line.tarikhTampal}</display:column>
                                <display:column title="Tempat Tampal" sortable="true">${line.tempatTampal}</display:column>
                                <display:column title="Nama " sortable="true">${line.namaPenampal}</display:column>

                                <display:column title="Hapus"><s:button name="deleteA" id="save" value="Hapus" class="longbtn" onclick="hapusA(document.forms.AB,'${actionBean.borangABForm.idPermohonan}','${line.id}')"/>
                                </display:column>

                            </display:table>
                            <s:button name="tambahBorangA" id="save" value="Tambah" class="longbtn" onclick="popupBorangA('${actionBean.borangABForm.idPermohonan}','${actionBean.borangABForm.idBorangPerPermohonanA}')"/>


                        </div>
                    </td>
                </tr>
                <tr>
                    <th>Borang B</th>
                    <td>
                        <p>
                            <label> Di tandatangan oleh :</label> ${actionBean.borangABForm.tandatanganB}
                        </p>

                        <p>
                            <label>Dokumen :</label>
                            <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                 onclick="doViewReport('${actionBean.borangABForm.borangB.idDokumen}');" height="30" width="30" alt="papar"
                                 onmouseover="this.style.cursor = 'pointer';" title="Papar Dokumen ${actionBean.borangABForm.borangB.kodDokumen.nama}"/>
                        </p>
                    </td>
                    <td>
                        <div>
                            <display:table class="tablecloth" name="${actionBean.listBorangB}"
                                           cellpadding="0" cellspacing="0"  id="line">
                                <display:caption>Maklumat Penyampaian</display:caption>
                                <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                                <%--<display:column title="Alamat Tampal" sortable="true">${line.alamatHantar}</display:column>--%>
                                <%--<display:column title="Penerima Nama" sortable="true">${line.penerimaNama}</display:column>--%>
                                <%--<display:column title="Penerima No KP" sortable="true">${line.penerimaNokp}</display:column>--%>
                                <display:column title="Tarikh Hantar" sortable="true">${line.tarikhHantar}</display:column>
                                <display:column title="Nama Penghantar" sortable="true">${line.namaPenghantar}</display:column>
                                <%--<display:column title="Bil" sortable="true">${line.tarikhHantar}</display:column>--%>
                                <display:column title="Hapus"><s:button name="deleteB" id="save" value="Hapus" class="longbtn" onclick="hapusB(document.forms.AB,'${actionBean.borangABForm.idPermohonan}','${line.id}')"/>
                                </display:column>

                            </display:table>
                            <s:button name="tambahBorangB" id="save" value="Tambah" class="longbtn" onclick="popupBorangB('${actionBean.borangABForm.idPermohonan}','${actionBean.borangABForm.idBorangPerPermohonanB}')"/>

                        </div>
                    </td>
                </tr>

            </table>

            <br>  


            <div align="center">
                <s:button name="selesai1" id="selesai1" value="Selesai" class="longbtn" onclick="selesai(document.forms.AB,'${actionBean.borangABForm.idPermohonan}')"/>
            </div>


        </fieldset>

    </div>

</s:form>
