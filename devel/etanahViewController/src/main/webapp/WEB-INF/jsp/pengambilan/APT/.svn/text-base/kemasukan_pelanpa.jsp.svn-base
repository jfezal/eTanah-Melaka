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
     function popupBorangH(idMohon,idBorangPerPB) {
//            alert(idMH);

            window.open("${pageContext.request.contextPath}/pengambilan/sesi_perbicaraan?tambahBorangH&idPermohonan=" + idMohon+"&idBorangPerPB="+idBorangPerPB, "eTanah",
                    "status=0,toolbar=0,location=0,menubar=0,width=1000,height=700,scrollbars=yes");
        }

    function save(event, f) {
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url, q,
                function(data) {
                    $('#page_div', opener.document).html(data);

                }, 'html');

    }
    function paparsenarai(event, f) {
        alert(event);
        var q = $(f).formSerialize();
        alert(q);
        var url = f.action + '?' + event;
        $.post(url, q,
                function(data) {
                    $('#page_div', opener.document).html(data);

                }, 'html');

    }
    function doViewReport(v) {
        var randomnumber = Math.floor((Math.random() * 100) + 1);
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, randomnumber, "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
    }

    function popupItemPampasan(idPermohonan, idBorangPerP) {
//        alert("val" + val);
        doBlockUI();
        var url = '${pageContext.request.contextPath}/pengambilan/common/RekodBorangIActionBean?popupItem&idPermohonan=' + idPermohonan + '&idBorangPerP=' + idBorangPerP;
        $.ajax({
            type: "GET",
            url: url,
            dataType: 'html',
            error: function(xhr, ajaxOptions, thrownError) {
                alert("error=" + xhr.responseText);
                doUnBlockUI();
            },
            success: function(data) {
                $('#page_div').html(data);
                doUnBlockUI();
            }
        });
    }
    function viewSenaraiF(idPermohonan, idHakmilik, idBorangPerP) {
//        alert("val" + val);
        doBlockUI();
        var url = '${pageContext.request.contextPath}/pengambilan/sesi_perbicaraan?senaraiF&idPermohonan=' + idPermohonan + '&idHakmilik=' + idHakmilik + '&idBorangPerP=' + idBorangPerP;
        $.ajax({
            type: "GET",
            url: url,
            dataType: 'html',
            error: function(xhr, ajaxOptions, thrownError) {
                alert("error=" + xhr.responseText);
                doUnBlockUI();
            },
            success: function(data) {
                $('#page_div').html(data);
                doUnBlockUI();
            }
        });
    }





    function kemaskiniBicaraA(idMohon, idPerPb) {
//            alert(idMH);

        window.open("${pageContext.request.contextPath}/pengambilan/sesi_perbicaraan?kemaskiniBicara&idPermohonan=" + idMohon + "&idPerPb=" + idPerPb, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1000,height=700,scrollbars=yes");
    }
    function uploadpelan(id) {
//            alert(idMH);

        window.open("${pageContext.request.contextPath}/pengambilan/pelan_pa?kemaskiniBaru&id=" + id, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1000,height=700,scrollbars=yes");
    }
    function edit(idBorangPb) {
        var url = '${pageContext.request.contextPath}/pengambilan/penerima_borang_f?kemaskiniBorangpb&idBorangpb=' + idBorangPb;
        $.getJSON(url, function(data) {
            alert(data.alamat3);
            $("#idPerPB").val(data.idPerPB);
            $("#nama").val(data.nama);
            $("#jenisPengenalan").val(data.jenisPengenalan);
            $("#noPengenalan").val(data.noPengenalan)
            $("#alamat1").val(data.alamat1);
            $("#alamat2").val(data.alamat2);
            $("#alamat3").val(data.alamat3);
            $("#alamat4").val(data.alamat4);
            $("#poskod").val(data.poskod);
            $("#negeri").val(data.negeri);
            $("#jenis_kepentingan").val(data.jenis_kepentingan);


        }, 'html');
    }
    function saveD(event, f) {
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url, q,
                function(data) {
//                            $('#page_div', opener.document).html(data);
//                            self.opener.refreshPage();
//                            self.close();
                }, 'html');

    }
    function doViewReport(v) {
//        alert(v);
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
    }

    $(document).ready(function() {
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});

    });

    function main(idPermohonan) {
//        alert("val" + val);
        doBlockUI();
        var url = '${pageContext.request.contextPath}/pengambilan/sesi_perbicaraan?showForm&idPermohonan=' + idPermohonan;
        $.ajax({
            type: "GET",
            url: url,
            dataType: 'html',
            error: function(xhr, ajaxOptions, thrownError) {
                alert("error=" + xhr.responseText);
                doUnBlockUI();
            },
            success: function(data) {
                $('#page_div').html(data);
                doUnBlockUI();
            }
        });
    }

</script>

<s:form beanclass="etanah.view.stripes.pengambilan.sek8.KemasukanPelanPAActionBean" name="AB" id="AB">

    <s:messages/>
        <div class="subtitle displaytag">

            <fieldset class="aras1" id="locate">
                <legend>
                    Perincian Maklumat

                </legend>

                <p align="center">

                <table class="tablecloth">

                    <th>Maklumat Hakmilik</th>

                    <tr>
                    <tr>

                        <td width="60%">
                            <div>
                                <display:table class="tablecloth" name="${actionBean.listKemasukanPelanPAForm}"
                                               cellpadding="0" cellspacing="0" requestURI="/pengambilan/penerimaan_notis_borang_BantahanMLK" id="line">
                                    <display:column title="Bil " sortable="true">${line_rowNum}</display:column>
                                    <display:column title="Hakmilik Asal" >
                                        ${line.permohonanPengambilanHakmilik.hakmilikPermohonan.hakmilik.idHakmilik}
                                        <p><b>Daerah :</b>${line.permohonanPengambilanHakmilik.hakmilikPermohonan.hakmilik.daerah.nama}</p>
                                        <p><b>Mukim :</b>${line.permohonanPengambilanHakmilik.hakmilikPermohonan.hakmilik.bandarPekanMukim.nama}</p>
                                        <p><b>Seksyen :</b>${line.permohonanPengambilanHakmilik.hakmilikPermohonan.hakmilik.seksyen.nama}</p>
                                        
                                        <p><b>Luas Asal :</b>${line.permohonanPengambilanHakmilik.hakmilikPermohonan.hakmilik.luas}</p>
                                        <p><b>Luas Ambil :</b>${line.permohonanPengambilanHakmilik.luasAmbil}</p>
                                        <s:hidden name="idAh">${line.permohonanPengambilanHakmilik.idAh}</s:hidden>
                                    </display:column>
                                     <display:column title="Warta Semula" >
                                         <s:select name="bezaLuas" value="${line.permohonanPengambilanHakmilik.flagBezaLuas}">
                                             <s:option value="T">Tidak</s:option>
                                             <s:option value="Y">Ya</s:option>
                                         </s:select>
                                    </display:column>
                                    <display:column title="Hakmilik Baru" >
                                        <display:table class="tablecloth" name="${line.listHakmilikBaru}"
                                               cellpadding="0" cellspacing="0" id="line2">
                                    <display:column title="Bil " sortable="true">${line2_rowNum}</display:column>
                                    <display:column title="No Pelan PA" >${line2.noPelanPA}</display:column>
                                    <display:column title="No Lot" >${line2.noLot}</display:column>
                                    <display:column title="Luas" >${line2.luas}</display:column>
                                    <display:column title="Unit Luas" >${line2.kodUom.nama}</display:column>
                                    <display:column title="Kemaskini" ><s:button class="btn" name="muatNaik" value="Kemaskini" onclick="uploadpelan('${line2.id}');"/></display:column>

                                </display:table>
                                    </display:column>

                                </display:table>

                            </div>
                        </td>
                    </tr>

                </table>
                <s:hidden name="idPermohonan"/>
            <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>

                <br>                

                </div>
            


            

</s:form>
