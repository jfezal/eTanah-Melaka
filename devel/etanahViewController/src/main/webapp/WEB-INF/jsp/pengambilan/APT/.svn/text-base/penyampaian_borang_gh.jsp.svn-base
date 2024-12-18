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

<s:form beanclass="etanah.view.stripes.pengambilan.sek8.PenyampaianBorangGHActionBean" name="AB" id="AB">

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
                            <label>    ID Permohonan :</label> ${actionBean.idPermohonan}
                        </p>
                        <p>
                            <label>Urusan :</label> ${actionBean.urusan}
                        </p>
                        <!--                        <p>
                                                    <label>Tujuan Permohonan :</label> 
                                                </p>
                                                  <p>
                                                      <label> Jumlah Hakmilik | Luas :</label> 
                                                </p>-->
                    </td></tr>
                <tr>
                    <th colspan="2">Maklumat Borang</th><th>Maklumat Hakmilik</th>

                <tr>
                <tr>
                    <th>Borang E</th>
                    <td><div class="content" align="left">
                            <p>
                                <label>   Jumlah Borang G :</label> 
                            </p>


                        </div>
                    </td>
                    <td width="60%">
                        <div>
                            <display:table class="tablecloth" name="${actionBean.listBorangE}"
                                           cellpadding="0" cellspacing="0" requestURI="/pengambilan/penerimaan_notis_borang_BantahanMLK" id="line">
                                <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                                <display:column title="Hakmilik" sortable="true">${line.hm.hakmilik.idHakmilik}</display:column>
                                <display:column title="Jumlah Pihak" sortable="true">${line.jumlahPihak}</display:column>
                                <display:column title="Papar" sortable="true">
                                    <!--<//c:if test="$//{line.status ne 'Selesai'}">-->
                                        <a href="${pageContext.request.contextPath}/pengambilan/penyampaian_g_h?senaraiG&idPermohonan=${line.hm.permohonan.idPermohonan}&idHakmilik=${line.hm.hakmilik.idHakmilik}&idBorangPerHm=${line.id}&urlKembali=${actionBean.urlKembali}"><s:button name="papar" class="btn" value="Papar Senarai"/></a>
                                   <!-- </c//:if>-->
 
                                 </display:column>        
                            </display:table>

                        </div>
                    </td>
                </tr>

            </table>
            <s:hidden name="idPermohonan"/>

            <br>                
            <label>&nbsp;</label>&nbsp;&nbsp;  <s:submit name="hantar" id="save" value="Hantar" class="longbtn"/> </fieldset>

    </div>

</s:form>
