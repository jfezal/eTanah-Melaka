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
    function popupBorangH(idMohon, idBorangPerPB) {
//            alert(idMH);

        window.open("${pageContext.request.contextPath}/pengambilan/sesi_perbicaraan?tambahBorangH&idPermohonan=" + idMohon + "&idBorangPerPB=" + idBorangPerPB, "eTanah",
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
    function kemaskiniTuntutanA(idMohon, idPerPb) {
//            alert(idMH);

        window.open("${pageContext.request.contextPath}/pengambilan/sesi_perbicaraan?kemaskiniTuntutan&idPermohonan=" + idMohon + "&idPerPb=" + idPerPb, "eTanah",
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
    
    function simpan(idPermohonan,idBorangPerHm,idHakmilik) {
        alert("idPermohonan" + idPermohonan);
        doBlockUI();
        var url = '${pageContext.request.contextPath}/pengambilan/sesi_perbicaraan?hantar&idPermohonan=' + idPermohonan +'&idBorangPerHm='+idBorangPerHm+'&idHakmilik='+idHakmilik;
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

<s:form beanclass="etanah.view.stripes.pengambilan.sek8.PerbicaraanActionBean" name="AB" id="AB">

    <s:messages/>
    <c:if test="${fn:length(actionBean.listBorangE) > 0}">
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
                                <display:table class="tablecloth" name="${actionBean.listBorangE}"
                                               cellpadding="0" cellspacing="0" requestURI="/pengambilan/penerimaan_notis_borang_BantahanMLK" id="line">
                                    <display:column title="Bil " sortable="true">${line_rowNum}</display:column>
                                    <display:column title="Hakmilik" sortable="true">${line.hm.hakmilik.idHakmilik}</display:column>
                                    <display:column title="Jumlah Pihak " sortable="true">${line.jumlahPihak}</display:column>
                                    <display:column title="Papar" sortable="true">

                                        <s:button name="senaraiF" id="save" value="senarai" class="btn" onclick="viewSenaraiF('${line.hm.permohonan.idPermohonan}','${line.hm.hakmilik.idHakmilik}','${line.id}')"/>

                                    </display:column>        

                                </display:table>

                            </div>
                        </td>
                    </tr>

                </table>
                <s:hidden name="idPermohonan"/>

                <br>                

                </div>
            </c:if>


            <c:if test="${fn:length(actionBean.listBorangF) > 0}">

                <s:form beanclass="etanah.view.stripes.pengambilan.sek8.PerbicaraanActionBean" name="AB" id="AB">

                    <s:messages/>
                    <div class="subtitle displaytag">
                        <br>
                        <br>
                        <fieldset class="aras1" id="locate">
                            <legend>
                                Perincian Maklumat

                            </legend>

                            <p align="center">

                            <table class="tablecloth">
                                <tr><th colspan="2">Maklumat Permohonan</th>
                                    <td width ="80%">
                                        <p>
                                            <label>    ID Permohonan :</label> ${actionBean.idPermohonan}
                                        </p>
                                        <p>
                                            <label>Urusan :</label> ${actionBean.permohonan.kodUrusan.nama}${actionBean.kategoriAPT}&nbsp;
                                        </p>
                                        <p>
                                            <label>Tujuan Permohonan :</label> ${actionBean.tujuanAPT} &nbsp;
                                        </p>

                                    </td></tr>
                                <tr>
                                    <th colspan="2">Maklumat Borang</th><th>Borang F</th>

                                <tr>
                                <tr>
                                    <th>Borang E</th>
                                    <td width="30%"><div class="content">
                                            <p>
                                                Di tandatangan oleh :${actionBean.borangPerHakmilik.ditandatangan}
                                            </p>

                                            <p>
                                                Dokumen Borang E:<img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                                      onclick="doViewReport('${actionBean.dokumenBE.idDokumen}');" height="30" width="30" alt="papar"
                                                                      onmouseover="this.style.cursor = 'pointer';" title="Papar Dokumen "/>

                                            </p>
                                            <c:if test="${actionBean.dokumenBG!=null}">
                                                <p>
                                                    Dokumen Borang G:<img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                                          onclick="doViewReport('${actionBean.dokumenBG.idDokumen}');" height="30" width="30" alt="papar"
                                                                          onmouseover="this.style.cursor = 'pointer';" title="Papar Dokumen "/>

                                                </p></c:if>
                                            <c:if test="${actionBean.dokumenBM !=null}">
                                                <p>
                                                    Dokumen Borang M:<img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                                          onclick="doViewReport('${actionBean.dokumenBM.idDokumen}');" height="30" width="30" alt="papar"
                                                                          onmouseover="this.style.cursor = 'pointer';" title="Papar Dokumen "/>

                                                </p></c:if>


                                            </div>
                                        </td>
                                        <td width="100%">
                                            <div>
                                            <display:table class="tablecloth" name="${actionBean.listBorangF}"
                                                           cellpadding="0" cellspacing="0" requestURI="/pengambilan/penerimaan_notis_borang_BantahanMLK" id="line">
                                                <display:column title="Bil" >${line_rowNum}</display:column>
                                                <display:column title="Nama ">
                                                    <p>${line.bppb.nama}</p>
                                                </display:column>  

                                                <display:column title="Borang F "><img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                     onclick="doViewReport('${line.bppb.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                                     onmouseover="this.style.cursor = 'pointer';" title="Papar Dokumen "/></display:column>
                                                <display:column title="Borang H ">
                                                    <c:if test="${line.bpH.dokumen != null}"><img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                         onclick="doViewReport('${line.bpH.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                                         onmouseover="this.style.cursor = 'pointer';" title="Papar Dokumen "/></c:if>
                                                </display:column>
                                                <display:column title="Status Perbicaraan ">
                                                    <c:if test="${line.statusBicara eq 'SL'}">
                                                        <p>SELESAI</p>
                                                    </c:if>
                                                    <c:if test="${line.statusBicara eq 'PT'}">
                                                        <p>PERTIKAIAN</p>
                                                    </c:if>
                                                    <c:if test="${line.statusBicara eq 'TG'}">
                                                        <p>TANGGUH</p>
                                                    </c:if>
                                                    <c:if test="${line.statusBicara eq 'BH'}">
                                                        <p>BANTAHAN</p>
                                                    </c:if>
                                                </display:column>  
                                                <display:column title="Jumlah Tuntutan ">
                                                    <p>${line.jumlahTuntutan}</p>
                                                </display:column>  
                                                <display:column title="Kemaskini Bicara ">  
                                                    <s:button name="kemaskiniBicara" id="save" value="Tambah" class="longbtn" onclick="kemaskiniBicaraA('${actionBean.idPermohonan}','${line.bppb.id}')"/>
                                                </display:column>  
                                                <display:column title="Kemaskini Tuntutan ">  
                                                    <s:button name="tambahBorangA" id="save" value="Tambah" class="longbtn" onclick="kemaskiniTuntutanA('${actionBean.idPermohonan}','${line.bppb.id}')"/>
                                                </display:column> 
                                                <c:if test="${actionBean.penyampaian}">
                                                    <display:column title="Penyampaian">  
                                                        <s:button name="tambahBorangA" id="save" value="Tambah" class="longbtn" onclick="popupBorangH('${actionBean.idPermohonan}','${line.bpH.id}')"/>
                                                    </display:column>
                                                </c:if>
                                            </display:table>

                                        </div>
                                    </td>
                                </tr>

                            </table><s:hidden name="idPermohonan" id="idPermohonan" value="${actionBean.idPermohonan}"/>
                            <s:hidden name="idHakmilik" id="idHakmilik"/><s:hidden name="idPerPB" id="idPerPB"/><s:hidden name="idBorangPerHm" id="idBorangPerHm"/>
                            <s:hidden name="urusan"/>
                            <s:hidden name="urlKembali"/>
                            <br>    
                            <p style="color:red">
                                *Keputusan Perbicaraan ini adalah untuk keseluruhan<br/>
                            </p>
                            <p>
                                <label for="kpsnBicara">Keputusan Perbicaraan :</label>
                                <s:radio name="kpsnBicara" value="L"/>&nbsp;Lulus
                                <s:radio name="kpsnBicara" value="T"/>&nbsp;Tangguh
                                <s:radio name="kpsnBicara" value="M"/>&nbsp;Mahkamah

                            </p>
                            <p>
                                <br>
                                <label>Ulasan :</label>&nbsp;
                                <s:textarea id = "ketPerbincgn" name="ketPerbincgn" rows="10" cols="100"/>&nbsp;
                            </p>
                            <label>&nbsp;</label>&nbsp;&nbsp;   
                            <s:button name="showForm" id="save" value="Kembali" class="btn" onclick="main('${actionBean.idPermohonan}')"/> 
                            <s:button name="hantar" id="hantar" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')" />   
                        </fieldset>


                </fieldset>

            </div>

        </s:form> 

    </c:if>



</s:form>
