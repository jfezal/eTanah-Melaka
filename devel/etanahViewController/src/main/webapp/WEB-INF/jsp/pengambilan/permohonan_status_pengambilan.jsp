<%-- 
    Document   : permohonan_status_penguatkuasaan
    Created on : May 13, 2011, 03:49:04 PM
    Author     : latifah.iskak
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<%-- <script type="text/javascript"
       src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>--%>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/popup.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-impromptu.js"></script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/styles/styles.css" />
        <title>Status Permohonan</title>

    </head>
    <body>
        <script type="text/javascript">

            function viewHakmilik(id){
                window.open("${pageContext.request.contextPath}/lelong/status?viewhakmilikDetail&idHakmilik="+id, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=700");
            }

            function viewSejarah(id){
                window.open("${pageContext.request.contextPath}/lelong/status?viewSejarah&idPermohonan="+id, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=700");
            }
            
            //            function addNew(v){
            //                if($("#idPermohonan").val() == ""){
            //                    alert('Sila masukkan " ID Permohonan " terlebih dahulu.');
            //                    return;
            //                }
            //                window.open("${pageContext.request.contextPath}/lelong/dokumen/folder?dokumenTambahanForm&permohonan.idPermohonan=" + v, "eTanah",
            //                "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
            //            }
            
            function addNew(v){
                window.open("${pageContext.request.contextPath}/penguatkuasaan/utiliti_senarai_dokumen?addDocForm&permohonan.idPermohonan=" + v, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1500,height=800");
            }
            
            function refreshImejDiv(idPermohonan){
                var url = '${pageContext.request.contextPath}/penguatkuasaan/utiliti_senarai_dokumen?reload&idPermohonan='+idPermohonan;
                $.get(url,
                function(data){
                    //                    alert(data);
                    //$("#ImejDiv").replaceWith($('#ImejDiv', $(data)));
                    $("#imejSenaraiDiv").replaceWith($('#imejSenaraiDiv', $(data)));
                    //                    $('#imejSenaraiDiv').html(data);
                },'html');
            }
            
            function removeDok(){
                var idPermohonan = $('#idPermohonan').val();
                
                var bil =  ${fn:length(actionBean.permohonan.folderDokumen.senaraiKandungan)};
                for (var i = 0; i < bil; i++){
                    var pilih = document.getElementById('chkbox'+i).checked;
                    if(pilih == true){
                        var idDok = document.getElementById('chkbox'+i).value
                        //                        alert(idDok);
                        if(confirm('Adakah anda pasti untuk hapus?')) {
                            var url = '${pageContext.request.contextPath}/penguatkuasaan/utiliti_senarai_dokumen?deleteSelected&id='+idDok;
                            $.get(url,
                            function(data){
                                $("#imejSenaraiDiv").replaceWith($('#imejSenaraiDiv', $(data)));
                                refreshImejDiv(idPermohonan);
                            },'html');
                        }
                    }
                }
             
            }
            
            function doViewReport(d) {
                window.open("${pageContext.request.contextPath}/lelong/dokumen/view?view&idDokumen=" + d, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
            }

            function doPrintViaApplet (docId) {
                var a = document.getElementById('applet');
                a.printDocument(docId.toString());
            }

            function muatNaikForm1(folderId, dokumenId, idPermohonan) {
                var left = (screen.width/2)-(1000/2);
                var top = (screen.height/2)-(150/2);
                var url = '${pageContext.request.contextPath}/upload?muatNaikForm&folderId=' + folderId + '&dokumenId='
                    + dokumenId + '&idPermohonan=' + idPermohonan;
                window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
            }
            
            function muatNaikForm(folderId, dokumenId, idPermohonan, dokumenKod) {
                var left = (screen.width/2)-(1000/2);
                var top = (screen.height/2)-(150/2);
                var url = '${pageContext.request.contextPath}/penguatkuasaan/utiliti_senarai_dokumen?popupUpload&folderId=' + folderId + '&idDokumen='
                    + dokumenId + '&idPermohonan=' + idPermohonan+ '&dokumenKod=' + dokumenKod;
                window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
            }

            function scan(dokumenId, idPermohonan) {
                var url = '${pageContext.request.contextPath}/dokumen/scan/' + dokumenId + '?idPermohonan=' + idPermohonan;
                window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=850,height=600");
            }


        </script>
        <s:messages />
        <s:errors />


        <c:if test="${actionBean.fromPage eq 'statusPermohonan'}">
            <c:set var="callFromPage" value="/pengambilan/status_permohonan"/>
        </c:if>
        <c:if test="${actionBean.fromPage eq 'senaraiDokumen'}">
            <c:set var="callFromPage" value="/penguatkuasaan/utiliti_senarai_dokumen"/>
        </c:if>
        <s:form action="${callFromPage}" >
            <br>
            <fieldset class="aras1">
                <s:hidden name="permohonan.idPermohonan" id="idPermohonan"/>
                <legend>Maklumat Permohonan Pengambilan</legend>
                <br>
                <p><label for="idPermohonan">ID Permohonan/ID Perserahan :</label>
                    ${actionBean.permohonan.idPermohonan}
                </p>



                <p><label for="kodUrusan">Kod Urusan/Urusan :</label>
                    ${actionBean.permohonan.kodUrusan.kod} -
                    ${actionBean.permohonan.kodUrusan.nama}
                </p>

                <p><label for="pguna">Peringkat :</label>
                    ${actionBean.stage == null ? "-" : actionBean.stage}
                </p>

                <c:choose>
                    <c:when test="${actionBean.permohonan.keputusan.kod eq 'SV'}">
                        <p><label for="kodUrusan">Status permohonan :</label>
                            ${actionBean.permohonan.keputusan.nama}
                        </p>
                    </c:when>
                    <c:otherwise>
                        <p><label for="kodUrusan">Status permohonan :</label>
                            Belum Selesai
                        </p>
                    </c:otherwise>
                </c:choose>

            </fieldset>

            <fieldset class="aras1">
                <div class="content" >
                    <legend>
                        Senarai Hakmilik Yang Terlibat
                    </legend>
                    <display:table name="${actionBean.hakmilikMohonList}" class="tablecloth" id="liner">
                        <display:column title="Bil">${liner_rowNum}</display:column>



                        <display:column property="hakmilik.idHakmilik" title="Id Hakmilik"/>

                        <display:column property="hakmilik.noLot" title="No Lot"/>

                        <display:column title="Status">
                            <c:if test="${liner.penarikBalikan eq '1'}">
                                Hakmilik terlibat dengan Penarikan Balik
                            </c:if>
                                <c:if test="${liner.penarikBalikan eq '0'}">
                                Terlibat dengan Proses Pengambilan
                            </c:if>
                        </display:column>



                    </display:table>
                </div></fieldset>

            <fieldset class="aras1">
                <div class="content" >
                    <legend>
                        Proses permohonan:
                    </legend>


                    <display:table name="${actionBean.permohonan.senaraiFasa}" class="tablecloth" id="line">
                        <display:column title="Bil">${line_rowNum}</display:column>

                        <display:column property="infoAudit.tarikhMasuk" title="Tarikh" sortable="true" format="{0,date,dd/MM/yyyy hh:mm aa}"/>

                        <display:column property="infoAudit.dimasukOleh.nama" title="Diproses Oleh"/>

                        <display:column property="idPengguna" title="Id Pengguna"/>

                        <display:column property="idAliran" title="ID Aliran"/>

                        <display:column title="Status">
                            <c:if test="${line.keputusan.kod ne null}">
                                ${line.keputusan.kod} - ${line.keputusan.nama}
                            </c:if>
                        </display:column>
                        <display:column property="ulasan" title="Ulasan"/>

                        <display:column title="Nota/Kertas Minit">
                            <c:forEach items="${actionBean.listNota}" var="n">
                                <c:if test="${n.idAliran eq line.idAliran}">
                                    ${n.nota}
                                </c:if>
                            </c:forEach>
                        </display:column>


                    </display:table>

                    <c:if test="${actionBean.secondLayerPermohonan.idPermohonan ne null}">
                        <fieldset class="aras1">
                            <br>
                            <p><label for="idPermohonan">ID Permohonan/ID Perserahan :</label>
                                ${actionBean.secondLayerPermohonan.idPermohonan}
                            </p>
                        </fieldset>

                        <display:table name="${actionBean.secondLayerPermohonan.senaraiFasa}" class="tablecloth" id="line">
                            <display:column title="Bil">${line_rowNum}</display:column>

                            <display:column property="infoAudit.tarikhMasuk" title="Tarikh" sortable="true" format="{0,date,dd/MM/yyyy hh:mm aa}"/>

                            <display:column property="infoAudit.dimasukOleh.nama" title="Diproses Oleh"/>

                            <display:column property="idPengguna" title="Id Pengguna"/>

                            <display:column property="idAliran" title="ID Aliran"/>

                            <display:column title="Status">
                                <c:if test="${line.keputusan.kod ne null}">
                                    ${line.keputusan.kod} - ${line.keputusan.nama}
                                </c:if>
                            </display:column>
                            <display:column property="ulasan" title="Ulasan"/>

                            <display:column title="Nota/Kertas Minit">
                                <c:forEach items="${actionBean.listNotaPermohonanSebelum}" var="n">
                                    <c:if test="${n.idAliran eq line.idAliran}">
                                        ${n.nota}
                                    </c:if>
                                </c:forEach>
                            </display:column>
                        </display:table>
                    </c:if>


                    <c:if test="${actionBean.thirdLayerPermohonan.idPermohonan ne null}">
                        <fieldset class="aras1">
                            <br>
                            <p><label for="idPermohonan">ID Permohonan/ID Perserahan :</label>
                                ${actionBean.thirdLayerPermohonan.idPermohonan}
                            </p>
                        </fieldset>

                        <display:table name="${actionBean.thirdLayerPermohonan.senaraiFasa}" class="tablecloth" id="line">
                            <display:column title="Bil">${line_rowNum}</display:column>

                            <display:column property="infoAudit.tarikhMasuk" title="Tarikh" sortable="true" format="{0,date,dd/MM/yyyy hh:mm aa}"/>

                            <display:column property="infoAudit.dimasukOleh.nama" title="Diproses Oleh"/>

                            <display:column property="idPengguna" title="Id Pengguna"/>

                            <display:column property="idAliran" title="ID Aliran"/>

                            <display:column title="Status">
                                <c:if test="${line.keputusan.kod ne null}">
                                    ${line.keputusan.kod} - ${line.keputusan.nama}
                                </c:if>
                            </display:column>
                            <display:column property="ulasan" title="Ulasan"/>

                            <display:column title="Nota/Kertas Minit">
                                <c:forEach items="${actionBean.listNotaPermohonanHD}" var="n">
                                    <c:if test="${n.idAliran eq line.idAliran}">
                                        ${n.nota}
                                    </c:if>
                                </c:forEach>
                            </display:column>


                        </display:table>
                    </c:if>


                </div>

            </fieldset>

            <fieldset class="aras1">
                <div class="content" >
                    <c:if test="${actionBean.fromPage eq 'senaraiDokumen'}">
                        <legend>Senarai Dokumen</legend>
                        <br>

                        <p class="title open" id="f1">
                            ID Permohonan : <font color="black">${actionBean.permohonan.idPermohonan}</font><br/>
                            Kod Urusan/Urusan : <font color="black">${actionBean.permohonan.kodUrusan.kod} - ${actionBean.permohonan.kodUrusan.nama}</font>
                        </p>
                        <p class="title open" id="f1">
                            <img src="${pageContext.request.contextPath}/pub/images/folderopen.gif" id="f11" class="open"/>
                            Fail ${actionBean.permohonan.folderDokumen.tajuk}</p>

                        <p class=instr>Senarai Dokumen dalam Fail. </p>

                        <font size="2" color="black">Petunjuk :</font>
                        <p>
                            <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                 width="20" height="20" /> - <font size="2" color="black">Papar Dokumen</font>
                        </p>
                        <p>
                            <img src="${pageContext.request.contextPath}/pub/images/icons/_active__print.png"
                                 width="20" height="20" /> - <font size="2" color="black">Cetak Dokumen</font>
                        </p>
                        <p>
                            <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png"
                                 width="20" height="20" /> - <font size="2" color="black">Muat Naik Dokumen</font>
                        </p>
                        <p>
                            <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png"
                                 width="20" height="20" /> - <font size="2" color="black">Imbas Dokumen</font>
                        </p>
                        <br/>

                        <div id="imejSenaraiDiv">
                            <display:table name="${actionBean.permohonan.folderDokumen.senaraiKandungan}" class="tablecloth" id="row" style="width:100%">
                                <display:column title="Pilih"> 
                                    <c:if test="${actionBean.pengguna.idPengguna eq row.dokumen.infoAudit.dimasukOleh.idPengguna}">
                                        <s:checkbox name="chkbox" value="${row.dokumen.idDokumen}" id="chkbox${row_rowNum-1}"/>
                                    </c:if>
                                    <s:hidden name="val" id="val_${row_rowNum-1}" value="${row.dokumen.idDokumen}"/>
                                </display:column>
                                <display:column title="No">
                                    ${row_rowNum}
                                </display:column>
                                <display:column title="Kod Dokumen">
                                    <div id="t" title="${row.dokumen.kodDokumen.nama}">${row.dokumen.kodDokumen.kod}</div>
                                </display:column>
                                <display:column title="Tajuk /<br/> No Rujukan">
                                    <c:if test="${row.dokumen.kodDokumen.kod eq '0L'}">
                                        <div id="${row_rowNum-1}" class="editable">${row.catatan}</div>
                                        <s:hidden name="x" id="old_${row_rowNum-1}" value="${row.catatan}"/>
                                    </c:if>
                                    <c:if test="${row.dokumen.kodDokumen.kod ne '0L'}">
                                        <div id="${row_rowNum-1}" class="editable">${row.dokumen.tajuk}</div>
                                        <s:hidden name="x" id="old_${row_rowNum-1}" value="${row.dokumen.tajuk}"/>
                                    </c:if>
                                    <br/>  <c:if test="${row.dokumen.noRujukan ne '' && row.dokumen.noRujukan ne null}">(No rujukan : ${row.dokumen.noRujukan})</c:if>
                                </display:column>
                                <display:column title="Klas." property="dokumen.klasifikasi.nama" />
                                <display:column title="Dimasuk Oleh" property="dokumen.infoAudit.dimasukOleh.nama" />
                                <%--<display:column title="Catatan" property="catatan" />--%>
                                <display:column title="Tarikh" property="dokumen.infoAudit.tarikhMasuk" format="{0,date,dd/MM/yyyy hh:mm:ss}" />
                                <display:column title="Tindakan">
                                    <div align="center">
                                        <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png"
                                             onclick="muatNaikForm('${row.folder.folderId}','${row.dokumen.idDokumen}'
                                                 ,'${actionBean.permohonan.idPermohonan}','${row.dokumen.kodDokumen.kod}');return false;" height="30" width="30" alt="Muat Naik"
                                             onmouseover="this.style.cursor='pointer';" title="Muat Naik untuk Dokumen ${row.dokumen.kodDokumen.nama}"/> /
                                        <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png"
                                             onclick="scan('${row.dokumen.idDokumen}','${actionBean.permohonan.idPermohonan}');return false;" height="30" width="30" alt="Imbas"
                                             onmouseover="this.style.cursor='pointer';" title="Imbas untuk Dokumen ${row.dokumen.kodDokumen.nama}"/> /
                                        <c:if test="${row.dokumen.namaFizikal != null}">
                                            <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                 onclick="doViewReport('${row.dokumen.idDokumen}');return false;" height="30" width="30" alt="papar"
                                                 onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${row.dokumen.kodDokumen.nama}"/>
                                            <c:if test="${row.dokumen.baru eq 'Y' || row.dokumen.baru eq ''}">
                                                <img src="${pageContext.request.contextPath}/pub/images/baharu.gif" alt="baru"/>
                                            </c:if>
                                        </c:if>
                                    </div>
                                </display:column>
                            </display:table>

                            <c:if test="${actionBean.secondLayerPermohonan.idPermohonan ne null}">
                                <p class="title open" id="f1">
                                    <img src="${pageContext.request.contextPath}/pub/images/folderopen.gif" id="f11" class="open"/>
                                    Fail ${actionBean.secondLayerPermohonan.folderDokumen.tajuk}</p>

                                <display:table name="${actionBean.secondLayerPermohonan.folderDokumen.senaraiKandungan}" class="tablecloth" id="row" style="width:100%">
                                    <display:column title="Pilih"> 
                                        <c:if test="${actionBean.pengguna.idPengguna eq row.dokumen.infoAudit.dimasukOleh.idPengguna}">
                                            <s:checkbox name="chkbox" value="${row.dokumen.idDokumen}" id="chkbox${row_rowNum-1}"/>
                                        </c:if>
                                        <s:hidden name="val" id="val_${row_rowNum-1}" value="${row.dokumen.idDokumen}"/>
                                    </display:column>
                                    <display:column title="No">
                                        ${row_rowNum}
                                    </display:column>
                                    <display:column title="Kod Dokumen">
                                        <div id="t" title="${row.dokumen.kodDokumen.nama}">${row.dokumen.kodDokumen.kod}</div>
                                    </display:column>
                                    <display:column title="Tajuk /<br/> No Rujukan">
                                        <c:if test="${row.dokumen.kodDokumen.kod eq '0L'}">
                                            <div id="${row_rowNum-1}" class="editable">${row.catatan}</div>
                                            <s:hidden name="x" id="old_${row_rowNum-1}" value="${row.catatan}"/>
                                        </c:if>
                                        <c:if test="${row.dokumen.kodDokumen.kod ne '0L'}">
                                            <div id="${row_rowNum-1}" class="editable">${row.dokumen.tajuk}</div>
                                            <s:hidden name="x" id="old_${row_rowNum-1}" value="${row.dokumen.tajuk}"/>
                                        </c:if>
                                        <br/>  <c:if test="${row.dokumen.noRujukan ne '' && row.dokumen.noRujukan ne null}">(No rujukan : ${row.dokumen.noRujukan})</c:if>
                                    </display:column>
                                    <display:column title="Klas." property="dokumen.klasifikasi.nama" />
                                    <display:column title="Dimasuk Oleh" property="dokumen.infoAudit.dimasukOleh.nama" />
                                    <%--<display:column title="Catatan" property="catatan" />--%>
                                    <display:column title="Tarikh" property="dokumen.infoAudit.tarikhMasuk" format="{0,date,dd/MM/yyyy hh:mm:ss}" />
                                    <display:column title="Tindakan">
                                        <div align="center">
                                            <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png"
                                                 onclick="muatNaikForm('${row.folder.folderId}','${row.dokumen.idDokumen}'
                                                     ,'${actionBean.secondLayerPermohonan.idPermohonan}','${row.dokumen.kodDokumen.kod}');return false;" height="30" width="30" alt="Muat Naik"
                                                 onmouseover="this.style.cursor='pointer';" title="Muat Naik untuk Dokumen ${row.dokumen.kodDokumen.nama}"/> /
                                            <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png"
                                                 onclick="scan('${row.dokumen.idDokumen}','${actionBean.secondLayerPermohonan.idPermohonan}');return false;" height="30" width="30" alt="Imbas"
                                                 onmouseover="this.style.cursor='pointer';" title="Imbas untuk Dokumen ${row.dokumen.kodDokumen.nama}"/> /
                                            <c:if test="${row.dokumen.namaFizikal != null}">
                                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                     onclick="doViewReport('${row.dokumen.idDokumen}');return false;" height="30" width="30" alt="papar"
                                                     onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${row.dokumen.kodDokumen.nama}"/>
                                                <c:if test="${row.dokumen.baru eq 'Y' || row.dokumen.baru eq ''}">
                                                    <img src="${pageContext.request.contextPath}/pub/images/baharu.gif" alt="baru"/>
                                                </c:if>
                                            </c:if>
                                        </div>
                                    </display:column>
                                </display:table>
                            </c:if>


                            <c:if test="${actionBean.thirdLayerPermohonan.idPermohonan ne null}">
                                <p class="title open" id="f1">
                                    <img src="${pageContext.request.contextPath}/pub/images/folderopen.gif" id="f11" class="open"/>
                                    Fail ${actionBean.thirdLayerPermohonan.folderDokumen.tajuk}</p>

                                <display:table name="${actionBean.thirdLayerPermohonan.folderDokumen.senaraiKandungan}" class="tablecloth" id="row" style="width:100%">
                                    <display:column title="Pilih"> 
                                        <c:if test="${actionBean.pengguna.idPengguna eq row.dokumen.infoAudit.dimasukOleh.idPengguna}">
                                            <s:checkbox name="chkbox" value="${row.dokumen.idDokumen}" id="chkbox${row_rowNum-1}"/>
                                        </c:if>
                                        <s:hidden name="val" id="val_${row_rowNum-1}" value="${row.dokumen.idDokumen}"/>
                                    </display:column>
                                    <display:column title="No">
                                        ${row_rowNum}
                                    </display:column>
                                    <display:column title="Kod Dokumen">
                                        <div id="t" title="${row.dokumen.kodDokumen.nama}">${row.dokumen.kodDokumen.kod}</div>
                                    </display:column>
                                    <display:column title="Tajuk /<br/> No Rujukan">
                                        <c:if test="${row.dokumen.kodDokumen.kod eq '0L'}">
                                            <div id="${row_rowNum-1}" class="editable">${row.catatan}</div>
                                            <s:hidden name="x" id="old_${row_rowNum-1}" value="${row.catatan}"/>
                                        </c:if>
                                        <c:if test="${row.dokumen.kodDokumen.kod ne '0L'}">
                                            <div id="${row_rowNum-1}" class="editable">${row.dokumen.tajuk}</div>
                                            <s:hidden name="x" id="old_${row_rowNum-1}" value="${row.dokumen.tajuk}"/>
                                        </c:if>
                                        <br/>  <c:if test="${row.dokumen.noRujukan ne '' && row.dokumen.noRujukan ne null}">(No rujukan : ${row.dokumen.noRujukan})</c:if>
                                    </display:column>
                                    <display:column title="Klas." property="dokumen.klasifikasi.nama" />
                                    <display:column title="Dimasuk Oleh" property="dokumen.infoAudit.dimasukOleh.nama" />
                                    <%--<display:column title="Catatan" property="catatan" />--%>
                                    <display:column title="Tarikh" property="dokumen.infoAudit.tarikhMasuk" format="{0,date,dd/MM/yyyy hh:mm:ss}" />
                                    <display:column title="Tindakan">
                                        <div align="center">
                                            <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png"
                                                 onclick="muatNaikForm('${row.folder.folderId}','${row.dokumen.idDokumen}'
                                                     ,'${actionBean.thirdLayerPermohonan.idPermohonan}','${row.dokumen.kodDokumen.kod}');return false;" height="30" width="30" alt="Muat Naik"
                                                 onmouseover="this.style.cursor='pointer';" title="Muat Naik untuk Dokumen ${row.dokumen.kodDokumen.nama}"/> /
                                            <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png"
                                                 onclick="scan('${row.dokumen.idDokumen}','${actionBean.thirdLayerPermohonan.idPermohonan}');return false;" height="30" width="30" alt="Imbas"
                                                 onmouseover="this.style.cursor='pointer';" title="Imbas untuk Dokumen ${row.dokumen.kodDokumen.nama}"/> /
                                            <c:if test="${row.dokumen.namaFizikal != null}">
                                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                     onclick="doViewReport('${row.dokumen.idDokumen}');return false;" height="30" width="30" alt="papar"
                                                     onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${row.dokumen.kodDokumen.nama}"/>
                                                <c:if test="${row.dokumen.baru eq 'Y' || row.dokumen.baru eq ''}">
                                                    <img src="${pageContext.request.contextPath}/pub/images/baharu.gif" alt="baru"/>
                                                </c:if>
                                            </c:if>
                                        </div>
                                    </display:column>
                                </display:table>

                            </c:if>


                        </div>



                        <p><label>&nbsp;</label>
                            <s:button name="deleteSelected" value="Hapus" class="btn"  onclick="removeDok();"/>
                            <s:button name="addDocForm" value="Tambah" class="btn" onclick="addNew('${actionBean.permohonan.idPermohonan}');"/>
                        </p>

                    </c:if>
                </div>



            </fieldset>
        </s:form>

    </body>
</html>
