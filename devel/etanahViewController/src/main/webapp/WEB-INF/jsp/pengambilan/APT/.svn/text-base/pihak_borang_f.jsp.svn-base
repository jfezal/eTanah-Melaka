        
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
            function edit(idBorangPb) {
                var url = '${pageContext.request.contextPath}/pengambilan/penerima_borang_f?kemaskiniBorangpb&idBorangpb=' + idBorangPb;
                $.getJSON(url, function(data) {
                    //alert(data.alamat3);
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
        </script>
    </head>
    <body>
        <s:messages/>
        <s:errors/>
        <s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
        <s:form beanclass="etanah.view.stripes.pengambilan.sek8.PenerimaBorangFActionBean" name="AB" id="AB">

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
                                        Dokumen :<c:if test="${actionBean.dokumenBE.idDokumen ne null}">
                                            <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                      onclick="doViewReport('${actionBean.dokumenBE.idDokumen}');" height="30" width="30" alt="papar"
                                                      onmouseover="this.style.cursor = 'pointer';" title="Papar Dokumen "/>
                                        </c:if> 

                                    </p>
                                </div>
                            </td>
                            <td width="100%">
                                <div>
                                    <display:table class="tablecloth" name="${actionBean.listBorangF}"
                                                   cellpadding="0" cellspacing="0" requestURI="/pengambilan/penerimaan_notis_borang_BantahanMLK" id="line">
                                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                                        <display:column title="Nama " sortable="true">
                                            <p>${line.bppb.nama}</p>
                                            <p style="font-size: x-small ">${line.bppb.alamat.alamat1}&nbsp;${line.bppb.alamat.alamat2}&nbsp;${line.bppb.alamat.alamat3}&nbsp;${line.bppb.alamat.alamat4}</p>
                                            <p style="font-size: x-small ">${line.bppb.alamat.poskod}&nbsp;${line.bppb.alamat.negeri.nama}</p>
                                        </display:column>  
                                        <display:column title="Jenis Kepentingan " sortable="true">${line.bppb.jenis_kepentingan}</display:column>
                                        <display:column title="Borang F " sortable="true">
                                            <c:if test="${line.bppb.dokumen.idDokumen ne null}">
                                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                     onclick="doViewReport('${line.bppb.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                                     onmouseover="this.style.cursor = 'pointer';" title="Papar Dokumen "/>
                                            </c:if>

                                        </display:column>
                                        <display:column title="Kemaskini " sortable="true">  
                                            <s:button name="tambahBorangA" id="save" value="Kemaskini" class="longbtn" onclick="edit('${line.bppb.id}')"/>
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

            </div>
            <fieldset class="aras1">
                <legend>Maklumat Pihak Kepentingan</legend>

                <p>
                    <label>Nama :</label>
                    <s:text id="nama" name="nama" size="40" maxlength="40" />
                </p>
                <p>
                    <label><em>*</em>Jenis Pengenalan :</label>
                    <s:select id="jenisPengenalan" name="jenisPengenalan" style="width:139px;">
                        <s:option value="">Pilih ...</s:option>
                        <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod" />
                    </s:select>
                </p>
                <p>
                    <label>Nombor Pengenalan :</label>
                    <s:text id="noPengenalan" name="noPengenalan" size="40" maxlength="40" />
                </p>

                <p>
                    <label><em>*</em>Alamat :</label>
                    <s:text id="alamat1" name="alamat1" size="40" maxlength="40" />
                </p>
                <p>
                    <label> &nbsp;</label>
                    <s:text name="alamat2" id="alamat2" size="40" maxlength="40" />
                </p>
                <p>
                    <label> &nbsp;</label>
                    <s:text name="alamat3" id="alamat3" size="40" maxlength="40" />
                </p>
                <p>
                    <label>Bandar :</label>
                    <s:text name="alamat4" id="alamat4"size="40" maxlength="40" />
                </p>
                <p>
                    <label><em>*</em>Poskod :</label>
                    <s:text id="poskod" name="poskod"  size="19" maxlength="5"/>
                </p>
                <p>
                    <label><em>*</em>Negeri :</label>
                    <s:select id="negeri" name="negeri" style="width:139px;">
                        <s:option value="">Pilih ...</s:option>
                        <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod" />
                    </s:select>
                </p>
                <p>
                    <label>Jenis Kepentingan :</label>
                    <s:text id="jenis_kepentingan" name="jenis_kepentingan" size="19" maxlength="12"/>
                </p>

                <p>
                    <label>&nbsp;</label>
                    &nbsp;
                </p>

                <p>
                    <label>&nbsp;</label>
                    <s:button name="simpanBorangF" id="simpan" value="Simpan" class="btn" onclick="saveD(this.name, this.form);"/>&nbsp;
                    <%--<s:reset name="" value="Isi Semula" class="btn"/>&nbsp;--%>
                    <s:button name="" id="close" value="Tutup" class="btn"/>&nbsp;
                </p>

            </fieldset >
        </s:form>
    </body>
</html>
