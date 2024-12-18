        
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="etanah.model.Pengguna"%>
<html>
    <head><title>Carian Hakmilik</title>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
        <script src="<%= request.getContextPath()%>/pub/js/ui.datepicker-ms.js" type="text/javascript"></script>

        <script type="text/javascript">


            function popupBorangE(idMohon, idBorangPerHakmilik) {
//            alert(idMH);

                window.open("${pageContext.request.contextPath}/pengambilan/penyampaian_e_f?tambahBorangE&idPermohonan=" + idMohon + "&idBorangPerHakmilikA=" + idBorangPerHakmilik, "eTanah",
                        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=700,scrollbars=yes");
            }
            function popupBorangF(idMohon, idBorangPerPb) {
//            alert(idMH);

                window.open("${pageContext.request.contextPath}/pengambilan/penyampaian_e_f?tambahBorangF&idPermohonan=" + idMohon + "&idBorangPerPBA=" + idBorangPerPb, "eTanah",
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
//        doBlockUI();
                var url = '${pageContext.request.contextPath}/pengambilan/sesi_perbicaraan?showForm&idPermohonan=' + idPermohonan ;
                $.ajax({
                    type: "GET",
                    url: url,
                    dataType: 'html',
                    error: function(xhr, ajaxOptions, thrownError) {
                        alert("error=" + xhr.responseText);
//                doUnBlockUI();
                    },
                    success: function(data) {
                        $('#page_div').html(data);
//                doUnBlockUI();
                    }
                });
            }
        </script>
    </head>
    <body>
        <s:messages/>
        <s:errors/>
        <s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
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
                                    <label>Urusan :</label> ${actionBean.permohonan.kodUrusan.nama}&nbsp;
                                </p>
                                <p>
                                    <label>Tujuan Permohonan :</label> &nbsp;
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
                                        Dokumen :<img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                      onclick="doViewReport('${actionBean.dokumenBE.idDokumen}');" height="30" width="30" alt="papar"
                                                      onmouseover="this.style.cursor = 'pointer';" title="Papar Dokumen "/>

                                    </p>
                                    <p>
                                        <display:table class="tablecloth" name="${actionBean.listTampalBorangE}"
                                                       cellpadding="0" cellspacing="0" requestURI="/pengambilan/penerimaan_notis_borang_BantahanMLK" id="line">
                                            <display:column title="Bil" >${line_rowNum}</display:column>
                                            <display:column title="Tarikh Tampal ">
                                            <p>${line.tarikhTampal}</p>
                                        </display:column>  
                                        <display:column title="Tempat Tampal ">
                                            <p>${line.tempatTampal}</p>
                                        </display:column> 
                                        <display:column title="Nama Penghantar">
                                            <p>${line.penghantarNotis}</p>
                                        </display:column>                        
                                    </display:table>
                                    <s:button name="tambahBorangE" id="save" value="Tambah" class="longbtn" onclick="popupBorangE('${actionBean.idPermohonan}','${actionBean.borangPerHakmilik.id}')"/>

                                    </p>
                                </div>
                            </td>
                            <td width="100%">
                                <div>
                                    <display:table class="tablecloth" name="${actionBean.listBorangF}"
                                                   cellpadding="0" cellspacing="0" requestURI="/pengambilan/penerimaan_notis_borang_BantahanMLK" id="line">
                                        <display:column title="Bil" >${line_rowNum}</display:column>
                                        <display:column title="Nama ">
                                            <p>${line.bppb.nama}</p>
                                            <p style="font-size: x-small ">${line.bppb.alamat.alamat1}&nbsp;${line.bppb.alamat.alamat2}&nbsp;${line.bppb.alamat.alamat3}&nbsp;${line.bppb.alamat.alamat4}</p>
                                            <p style="font-size: x-small ">${line.bppb.alamat.poskod}&nbsp;${line.bppb.alamat.negeri.nama}</p>
                                        </display:column>  

                                        <display:column title="Borang F "><img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                             onclick="doViewReport('${line.bppb.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                             onmouseover="this.style.cursor = 'pointer';" title="Papar Dokumen "/></display:column>
                                        <display:column title="Status Penghantaran ">
                                            <p>${line.hantar}</p>
                                        </display:column>  
                                        <display:column title="Kemaskini ">  

                                            <s:button name="tambahBorangA" id="save" value="Tambah" class="longbtn" onclick="popupBorangF('${actionBean.idPermohonan}','${line.bppb.id}')"/>
                                        </display:column>                            
                                    </display:table>


                                </div>
                            </td>
                        </tr>

                    </table><s:hidden name="idPermohonan" id="idPermohonan"/>
                    <s:hidden name="idHakmilik" id="idHakmilik"/><s:hidden name="idPerPB" id="idPerPB"/><s:hidden name="idBorangPerHm" id="idBorangPerHm"/>
                    <s:hidden name="urusan"/>
                    <s:hidden name="urlKembali"/>
                    <br>                
                </fieldset>
                <%--<s:button name="showForm" id="save" value="Kembali" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>--%>
                <s:button name="showForm" id="save" value="Kembali" class="btn" onclick="main('${actionBean.idPermohonan}')"/>

            </div>

        </s:form>
    </body>
</html>
