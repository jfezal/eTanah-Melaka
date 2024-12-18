<%-- 
    Document   : borangN
    Created on : Sep 21, 2020, 1:36:17 PM
    Author     : zipzap
--%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html;" import="java.util.*"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
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


    function simpanPBView(idBorangPerPb, idPermohonan) {
//        alert("idBorangPerPb" + idBorangPerPb);
        doBlockUI();
//        var url = '${pageContext.request.contextPath}/pengambilan/sek8/sediaBorangN?simpanPB&idBorangPerPb=' + idBorangPerPb;
        var url = '${pageContext.request.contextPath}/pengambilan/sek8/sediaBorangN?simpanPB&idBorangPerPb=' + idBorangPerPb + '&idPermohonan=' + idPermohonan;
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
    function hapusBorangPBNBN(idBorangPerPb, idPermohonan) {
//        alert("idBorangPerPb" + idBorangPerPb);
        doBlockUI();
//        var url = '${pageContext.request.contextPath}/pengambilan/sek8/sediaBorangN?simpanPB&idBorangPerPb=' + idBorangPerPb;
        var url = '${pageContext.request.contextPath}/pengambilan/sek8/sediaBorangN?hapusPB&idBorangPerPb=' + idBorangPerPb + '&idPermohonan=' + idPermohonan;
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
    function simpanBantahan(idBorangPerPb, idPermohonan) {
//        alert("idBorangPerPb" + idBorangPerPb);
        doBlockUI();
//        var url = '${pageContext.request.contextPath}/pengambilan/sek8/sediaBorangN?simpanPB&idBorangPerPb=' + idBorangPerPb;
        var url = '${pageContext.request.contextPath}/pengambilan/sek8/sediaBorangN?hapusPB&idBorangPerPb=' + idBorangPerPb + '&idPermohonan=' + idPermohonan;
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

    function muatNaikForm(folderId, dokumenId, idPermohonan, kodDokumen) {
//           alert (kodDokumen);
        var left = (screen.width / 2) - (1000 / 2);
        var top = (screen.height / 2) - (150 / 2);
        var url = '${pageContext.request.contextPath}/pengambilan/sek8/sediaBorangN?muatNaikForm&folderId=' + folderId + '&dokumenId='
                + dokumenId + '&idPermohonan=' + idPermohonan + '&kodDokumen=' + kodDokumen;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
    }

    function doViewReport(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
    }
</script>


<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<%--<s:form  beanclass="etanah.view.stripes.pengambilan.share.common.RekodBorangIActionBean">--%>
<s:form  beanclass="etanah.view.stripes.pengambilan.sek8.sediaBorangN">





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
                        <p>
                            <label>Tujuan Permohonan :</label>
                        </p>

                    </td></tr>
                <tr>
                    <th colspan="2">Maklumat Borang</th><th>Tuntutan Dibuat Oleh</th>

                <tr>

                    <c:if test="${fn:length(actionBean.listBorangPerPB) <= 0}">
                    <tr>
                        <th>Borang E</th>
                        <td><div class="content" align="left">
                                <p>
                                    <label>    Di tandatangan oleh :</label>
                                </p>

                                <p>
                                    <label> Dokumen :</label><img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                                  onclick="doViewReport('${actionBean.e.dok.idDokumen}');" height="30" width="30" alt="papar"
                                                                  onmouseover="this.style.cursor = 'pointer';" title="Papar Dokumen ${actionBean.e.dok.kodDokumen.nama}"/>

                                </p>
                            </div>
                        </td>
                        <td width="60%">
                            <div>
                                <display:table class="tablecloth" name="${actionBean.e.listBorangPerPB}"
                                               cellpadding="0" cellspacing="0" requestURI="" id="line4">
                                    <display:column title="Bil" sortable="true">${line4_rowNum}</display:column>
                                    <display:column title="Nama " sortable="true">${line4.pb.nama}</display:column>  
                                    <display:column title="Jumlah Item Tuntutan " sortable="true">${line4.totalItem}</display:column>
                                    <display:column title="Jumlah Tuntutan " sortable="true">${line4.amaun}</display:column>
                                    <display:column title="Kemaskini " >  
                                        <center>
                                            <s:radio name="idBorangPerPb" value="${line.idPermohonan}" onclick="simpanPBView('${line4.pb.id}','${actionBean.idPermohonan}')" id="idBorangPerPb"/>
                                            <s:hidden name="idBorangPerP" id="idBorangPerP" value="${line4.pb.id}"/> 
                                        </center>
                                    </display:column>     
                                    <s:hidden name="idPermohonan" id="idPermohonan" value="${actionBean.idPermohonan}"/> 


                                </display:table>



                            </div>
                        </td>
                    </tr>
                </c:if>
                <c:if test="${fn:length(actionBean.listBorangPerPB) > 0}">
                    <tr>
                        <th>Borang N</th>
                        <td><div class="content" align="left">
                                <p>
                                    <label>    Di tandatangan oleh :</label>
                                </p>

                                <p>
                                    <label> Dokumen :</label><img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                                  onclick="doViewReport('${actionBean.e.dok.idDokumen}');" height="30" width="30" alt="papar"
                                                                  onmouseover="this.style.cursor = 'pointer';" title="Papar Dokumen ${actionBean.e.dok.kodDokumen.nama}"/>

                                </p>
                            </div>
                        </td>
                        <td width="60%">
                            <div>
                                <display:table class="tablecloth" name="${actionBean.listBorangPerPB}"
                                               cellpadding="0" cellspacing="0" requestURI="" id="line4">
                                    <display:column title="Bil" sortable="true">${line4_rowNum}</display:column>
                                    <display:column title="Nama " sortable="true">${line4.nama}</display:column>  
                                    <display:column title="No Pengenalan " sortable="true">${line4.noPengenalan}</display:column>  
                                    <display:column title="Jenis Pengenalan " sortable="true">${line4.jenisPengenalan.nama}</display:column>  
                                    <display:column title="Alamat " sortable="true">
                                        ${line4.alamat.alamat1}
                                        ${line4.alamat.alamat2}
                                        ${line4.alamat.alamat3}
                                        ${line4.alamat.alamat4}
                                    </display:column>  

                                    <%--<display:column title="Jumlah Item Tuntutan " sortable="true">${line4.totalItem}</display:column>--%>
                                    <%--<display:column title="Jumlah Tuntutan " sortable="true">${line4.amaun}</display:column>--%>
                                    <display:column title="HAPUS" >  
                                        <center>
                                            <s:radio name="idBorangPerPb" value="${line.idPermohonan}" onclick="hapusBorangPBNBN('${line4.id}','${actionBean.idPermohonan}')" id="idBorangPerPb"/>
                                            <s:hidden name="idBorangPerP" id="idBorangPerP" value="${line4.id}"/> 
                                        </center>
                                    </display:column>     
                                    <s:hidden name="idPermohonan" id="idPermohonan" value="${actionBean.idPermohonan}"/> 
                                </display:table>
                            </div>
                        </td>
                    </tr>
                </c:if>
            </table>
        </fieldset>
    </div>
    <c:if test="${fn:length(actionBean.listBorangPerPB) > 0}">
        <fieldset class="aras1">
            <legend>
                Permohonan Supaya Bantahan Dirujukkan Kepada Mahkamah
            </legend>
            <div class="content" align="left">
                <table align="left"  width="100%">
                    <tr>
                        <td align="left" width="30%">Kepentingan terhadap tanah:</td>
                        <td align="left" class="input1" width="70%"><s:textarea rows="4" cols="35" id="kepetingan" name="kepentingan"/></td>
                    </tr>
                    <tr>
                        <td align="left" width="30%">Jenis Bantahan :${actionBean.bantah1}</td>
                        <td align="left" width="70%">
                            <c:if test="${actionBean.bantah1 eq 'Y'}">
                                <input type="checkbox" name="bantah1" value="Y" checked="checked" id="bantah1"/>Ukuran Tanah<br>
                            </c:if>
                            <c:if test="${actionBean.bantah1 eq 'T' || actionBean.bantah1 eq null}">
                                <input type="checkbox" name="bantah1" value="Y"  id="bantah1"/>Ukuran Tanah<br>
                            </c:if>
                            <c:if test="${actionBean.bantah2 eq 'Y'}">
                                <input type="checkbox" name="bantah2" value="Y" checked="checked" id="bantah2" />Amaun Pampasan<br>
                            </c:if>
                            <c:if test="${actionBean.bantah2 eq 'T'|| actionBean.bantah2 eq null}">
                                <input type="checkbox" name="bantah2" value="Y" id="bantah2" />Amaun Pampasan<br>
                            </c:if>
                            <c:if test="${actionBean.bantah3 eq 'Y'}">
                                <input type="checkbox" name="bantah3" value="Y" checked="checked" id="bantah3"/>Orang yang kepadanya pampasan itu kena dibayar<br>
                            </c:if>
                            <c:if test="${actionBean.bantah3 eq 'T'|| actionBean.bantah3 eq null}">
                                <input type="checkbox" name="bantah3" value="Y" id="bantah3"/>Orang yang kepadanya pampasan itu kena dibayar<br>
                            </c:if>
                            <c:if test="${actionBean.bantah4 eq 'Y'}">
                                <input type="checkbox" name="bantah4" value="Y" checked="checked" id="bantah4" />pembahagian pampasan itu<br>
                            </c:if>
                            <c:if test="${actionBean.bantah4 eq 'T'|| actionBean.bantah4 eq null}">
                                <input type="checkbox" name="bantah4" value="Y" id="bantah4" />pembahagian pampasan itu<br>
                            </c:if>



                        </td>
                    </tr>
                    <tr>
                        <td align="left" width="30%" rowspan="1">Ulasan Alasan :</td>
                        <td align="left" width="70%"><s:textarea name="alasan" id="alasan" rows="4" cols="35"/></td>
                    </tr>

                </table>
                <br>
                <br>
                <br>
                <p>
                    <label>&nbsp;</label>
                    <%--<s:button name="savePengambilanInfo" value="Simpan" class="btn" onclick=""/>--%>
                    <s:hidden name="idBorangPerP" id="idBorangPerP" value="${actionBean.borangPerPB.id}"/> 
                    <s:button name="simpanBantahan" id="" value="simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </p>
                <%--<s:submit name="simpan" value="Simpan" class="btn"/>--%>
            </div>
        </fieldset>
    </c:if>



    <br>    
    <fieldset class="aras1">
        <p align="center">
        <table class="tablecloth">
        </table>
    </p> 
    <display:table name="${actionBean.senaraiKandungan}" class="tablecloth" id="row" style="width:100%"
                   requestURI="${pageContext.request.contextPath}/dokumen/folder">
        <display:column title="Bil">${row_rowNum}</display:column>
        <display:column title="Kod Dokumen" group="1">
            <div id="t" title="${row.dokumen.kodDokumen.nama}">${row.dokumen.kodDokumen.kod}</div>
        </display:column>

        <display:column title="Tajuk /<br/> No Rujukan" property="dokumen.kodDokumen.nama" />
        <display:column title="Klas." property="dokumen.klasifikasi.nama" />
        <display:column title="Dimasuk Oleh" property="dokumen.infoAudit.dimasukOleh.nama" />
        <display:column title="Tarikh" property="dokumen.infoAudit.tarikhMasuk" format="{0,date,dd/MM/yyyy hh:mm:ss}" sortable="true"/>
        <display:column title="Tindakan">
            <p align="center">
                <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png"
                     onclick="muatNaikForm('${row.folder.folderId}', '${row.dokumen.idDokumen}'
                                     , '${actionBean.permohonan.idPermohonan}', '${row.dokumen.kodDokumen.kod}');
                             return false;" height="30" width="30" alt="Muat Naik"
                     onmouseover="this.style.cursor = 'pointer';" title="Muat Naik untuk Dokumen ${row.dokumen.kodDokumen.nama}"/> <b>|</b>
                <c:if test="${row.dokumen.namaFizikal != null}">
                    <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                         onclick="doViewReport('${row.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                         onmouseover="this.style.cursor = 'pointer';" title="Papar Dokumen ${row.dokumen.kodDokumen.nama}"/>
                    <c:if test="${row.dokumen.baru eq 'Y' || row.dokumen.baru eq ''}">
                        <img src="${pageContext.request.contextPath}/pub/images/baharu.gif" alt="baru"/>
                    </c:if>
                </c:if>
            </p>
        </display:column>     
    </display:table>

</fieldset>

</s:form>

