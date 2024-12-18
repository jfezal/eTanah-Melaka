<%-- 
    Document   : kpsn_MMMKN
    Created on : Feb 25, 2020, 2:33:25 PM
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
    function doViewReport(v) {alert("ID DOKUMEN>>>"+v);
        var randomnumber = Math.floor((Math.random() * 100) + 1);
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, randomnumber, "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
    }

    function doPopupDetails(val) {
        var url = '${pageContext.request.contextPath}/common/maklumat_permohonan?view&idPermohonan=' + val + '&idHakmilik=' + idHakmilik;
        window.open(url, "eTanah", "status=0,toolbar=0,location=0,menubar=0,width=1000,height=768,scrollbars=yes");
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
    function viewBorang(idPermohonan, idHakmilik) {
        alert("idHakmilik" + idHakmilik);
        doBlockUI();
        var url = '${pageContext.request.contextPath}/pengambilan/common/RekodBorangIActionBean?viewBorangE&idPermohonan=' + idPermohonan + '&idHakmilik=' + idHakmilik;
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
           var url = '${pageContext.request.contextPath}/pengambilan/common/RekodBorangIActionBean?muatNaikForm&folderId=' + folderId + '&dokumenId='
                   + dokumenId + '&idPermohonan=' + idPermohonan + '&kodDokumen=' + kodDokumen;
           window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
       }
</script>


<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<s:form  beanclass="etanah.view.stripes.pengambilan.share.common.RekodBorangIActionBean">



    <c:if test="${actionBean.tuntutan ne 'Y'}">
        <c:if test="${fn:length(actionBean.e.listBorangPerPB) <= 0}">
            <c:if test="${fn:length(actionBean.listHakmilikPermohonan2) > 0}">
               

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
                                                <label>   Jumlah Borang E :</label> 
                                            </p>


                                        </div>
                                    </td>
                                    <td width="60%">
                                        <div>

                                            <display:table class="tablecloth" name="${actionBean.listHakmilikPermohonan2}"
                                                           cellpadding="0" cellspacing="0" requestURI="" id="line">
                                                <display:column title="Bil">${line_rowNum}</display:column>
                                                <display:column title="Hakmilik" >${line.hakmilik.idHakmilik}</display:column>
                                                <display:column title="Jumlah Pihak" >${line.hakmilik.idHakmilik}</display:column>
                                                <display:column title="Papar" sortable="true">
                                                    <s:hidden name="idHakmilik" id="idHakmilik" value="${line.hakmilik.idHakmilik}"/> 

                                                    <%--<s:button name="viewBorangE" id="save" value="Papar Senarai" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>--%>
                                                    <s:button name="viewBorangE" id="save" value="Papar Senarai" class="btn" onclick="viewBorang('${actionBean.idPermohonan}','${line.hakmilik.idHakmilik}')"/>
                                                </display:column>     

                                            </display:table>

                                        </div>
                                    </td>
                                </tr>

                            </table>
                            <s:hidden name="idPermohonan"/>     
                        </fieldset>

                    </div>


               
            </c:if>
        </c:if>
    </c:if>

    <c:if test="${actionBean.tuntutan ne 'Y'}">
        <c:if test="${fn:length(actionBean.e.listBorangPerPB) > 0}">
           
                <div class="subtitle displaytag">

                    <fieldset class="aras1" id="locate">
                        <legend>
                            Perincian Maklumat 2

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
                                <th colspan="2">Maklumat Borang</th><th>Maklumat Tuntutan/Borang F</th>

                            <tr>
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
                                            <display:column title="Kemaskini" >  

                                                <s:button name="popupItem" id="save" value="Tambah" class="btn" onclick="popupItemPampasan('${actionBean.idPermohonan}','${line4.pb.id}')"/>

                                                <s:hidden name="idBorangPerP" id="idBorangPerP" value="${line4.pb.id}"/> 
                                            </display:column>     
                                            <s:hidden name="idPermohonan" id="idPermohonan" value="${actionBean.idPermohonan}"/> 


                                        </display:table>



                                    </div>
                                </td>
                            </tr>

                        </table>
                        <s:hidden name="urusan"/>
                        <s:hidden name="urlKembali"/>
                        <br>         
                        <s:hidden name="idHakmilik" id="idHakmilik" value="${actionBean.idPermohonan}"/> 
                        <s:button name="showFormBorangJ" id="save" value="Kembali" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                       <!--<a href='${pageContext.request.contextPath}/pengambilan/common/RekodBorangIActionBean?${actionBean.urlKembali}&idPermohonan=${actionBean.idPermohonan}'><s:button name="hantar" id="save" value="Kembali" class="longbtn"/></a>--> 
                    </fieldset>

                </div>

          
        </c:if>
    </c:if>

    <c:if test="${actionBean.tuntutan eq 'Y'}">
        

            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>Maklumat Tuntutan</legend>

                    <p>
                        <label>Jenis Tuntutan</label>
                        <s:text name="itemTuntutan"/>
                    </p>
                    <p>
                        <label>Amaun</label>
                        <s:text name="amaunTuntutan"/>
                    </p>
                    <s:hidden name="idBorangPerPb" id="idBorangPerPb" value="${actionBean.pb.id}"/> 
                    <p><label>&nbsp; </label>
                        <s:hidden name="idPermohonan" id="idPermohonan" value="${actionBean.idPermohonan}"/> 
                        <%--<s:button class="btn" value="Simpan" name="savepopupItem"  onclick="save(this.name,this.form);" />--%>
                        <s:button name="savepopupItem" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                        <s:button name="showFormBorangJ" id="" value="Kembali" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </p>
                    <br>
                </fieldset>
                <fieldset class="aras1">
                    <legend>Senarai Item Tuntutan</legend>

                    <display:table class="tablecloth" name="${actionBean.listTuntutan}"
                                   cellpadding="0" cellspacing="0" requestURI="/pengambilan/penerimaan_notis_borang_BantahanMLK" id="line">
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column title="Item Tuntutan " sortable="true">${line.itemTuntutan}</display:column>
                        <display:column title="Amaun Tuntutan " sortable="true">${line.amaun}</display:column>
                    </display:table>

                    <br>
                </fieldset>
            </div>

    </c:if>

    <br>    
    <fieldset class="aras1">

        <p align="center">
        <table class="tablecloth">







        </table>
    </p> 
    <display:table name="${actionBean.senaraiKandungan2}" class="tablecloth" id="row" style="width:100%"
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



