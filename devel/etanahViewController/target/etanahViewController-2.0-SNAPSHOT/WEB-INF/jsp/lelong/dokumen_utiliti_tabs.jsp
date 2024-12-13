<%-- 
    Document   : dokumen_utiliti_tabs
    Created on : Oct 28, 2011, 12:02:48 PM
    Author     : mazurahayati.yusop
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript">
    function addNew(v){
        window.open("${pageContext.request.contextPath}/lelong/pembatalan_permohonan?dokumenTambahanForm&idPermohonan=" + v, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
    }

    function signForm(v){
        var url = '${pageContext.request.contextPath}/lelong/dokumen/view/' + v + '?signForm';
        window.open(url, 'etanah', "status=1,toolbar=0,location=0,menubar=0,width=1000,height=600");
    }

    function doViewReport(d) {
        window.open("${pageContext.request.contextPath}/lelong/dokumen/view?view&idDokumen=" + d, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
    }

    function doPrintViaApplet (docId) {
        //alert('tsttt');
        var a = document.getElementById('applet');
        //a.doPrint(docId.toString());
        a.printDocument(docId.toString());
    }

    function muatNaikForm(folderId, dokumenId, idPermohonan) {
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/2)-(150/2);
        var url = '${pageContext.request.contextPath}/upload?muatNaikForm&folderId=' + folderId + '&dokumenId='
            + dokumenId + '&idPermohonan=' + idPermohonan;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
    }

    function scan(dokumenId, idPermohonan) {
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/2)-(150/2);
        var url = '${pageContext.request.contextPath}/dokumen/scan/' + dokumenId + '?idPermohonan=' + idPermohonan;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=850,height=600");
    }

    function selectAll(a){
        var len = $('.cetakSemua').length;
        for (var i=0; i<len; i++) {
            var c = document.getElementById("cetaksemua" + i);
            c.checked = a.checked;
        }
    }

    function doViewImage (val) {
        var url = '${pageContext.request.contextPath}lelong/dokumen/view/image/' + val;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=850,height=600,scrollbars=yes");
    }

    function saveInput(val, t) {
        var url = '${pageContext.request.contextPath}/dokumen/folder?saveEditInfo&tajuk=' + val + '&idDok=' + $('#val_' + t.id).val();
        $.post(url,
        function(data){
            if (data == '1') $('#' + t.id).text(val);
            else $('#' + t.id).text($('#old_' + t.id).val());
        });
    }
            
    $(document).ready(function(){
        
        $('#p').click( function(){
            $('#folder').click();
        });
        $("img[title]").tooltip({
            // tweak the position
            //offset: [10, 2],

            // use the "slide" effect
            position:'left',
            effect: 'slide'
        }).dynamic({ bottom: { direction: 'left' } });
       
        $("#t[title]").tooltip({
            position:'left',
            effect: 'slide'
        }).dynamic({ bottom: { direction: 'left' } });


        $('.editable').each(function(i) {
            $(this).dblclick(function() {
                $(this).html('<input id="'+i+'" type=text onblur=\'saveInput(this.value, this);\'/>');
            });
        });

        $('#f1').click(function(){
            if($('#f1').hasClass('open')){
                $('#row1').slideUp('normal');
                $('#f11').attr('src', '${pageContext.request.contextPath}/pub/images/folder.gif');
                $('#f1').removeClass('open');
                $('#f1').addClass('close');
            }else if($('#f1').hasClass('close')){
                $('#row1').slideDown('normal');
                $('#f11').attr('src', '${pageContext.request.contextPath}/pub/images/folderopen.gif');
                $('#f1').removeClass('close');
                $('#f1').addClass('open');
            }
        });
        $('#f11').click(function(){
            if($('#f11').hasClass('open')){
                $('#row1').slideUp('normal');
                $('#f11').attr('src', '${pageContext.request.contextPath}/pub/images/folder.gif');
                $('#f11').removeClass('open');
                $('#f11').addClass('close');
            }else if($('#f11').hasClass('close')){
                $('#row1').slideDown('normal');
                $('#f11').attr('src', '${pageContext.request.contextPath}/pub/images/folderopen.gif');
                $('#f11').removeClass('close');
                $('#f11').addClass('open');
            }
        });

        $('#f2').click(function(){
            if($('#f2').hasClass('open')){
                $('#row2').slideUp('normal');
                $('#f2').attr('src', '${pageContext.request.contextPath}/pub/images/folder.gif');
                $('#f2').removeClass('open');
                $('#f2').addClass('close');
            }else if($('#f2').hasClass('close')){
                $('#row2').slideDown('normal');
                $('#f2').attr('src', '${pageContext.request.contextPath}/pub/images/folderopen.gif');
                $('#f2').removeClass('close');
                $('#f2').addClass('open');
            }
        });
    });
        
</script>

<div id="folderLelong">
    <p class="title open" id="f1">
        <img src="${pageContext.request.contextPath}/pub/images/folderopen.gif" id="f11" class="open"/>
        Fail ${actionBean.folderDokumen.tajuk}</p>
        <%--<div id="folderLelong">--%>
    <p class=instr>Senarai Dokumen dalam Fail. </p>

    <!--s:form action="/dokumen/folder.action" -->
    <s:form beanclass="etanah.view.stripes.lelong.UtilitiTabsTarikhdanJurulelong">
        <s:hidden name="folderDokumen.folderId"/>

        <br/>

        <s:hidden name="permohonan.idPermohonan"/>
        <fieldset class="aras1" id="row1">
            <br/>
            <br/>
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
            <c:set var="rowNum" value="0"/>
            <display:table name="${actionBean.senaraiKandungan}" class="tablecloth" id="row" style="width:100%">
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
                    <p align="center">
                        <%--<c:if test="${row.dokumen.namaFizikal == null}">
                            <a href="#" onclick="muatNaikForm('${row.folder.folderId}','${row.dokumen.idDokumen}'
                                ,'${actionBean.permohonan.idPermohonan}');return false;">Muatnaik</a>
                            <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png"
                                 onclick="muatNaikForm('${row.folder.folderId}','${row.dokumen.idDokumen}'
                                ,'${actionBean.permohonan.idPermohonan}');return false;" height="30" width="30" alt="Muat Naik"
                                 onmouseover="this.style.cursor='pointer';" title="Muat Naik untuk Dokumen ${row.dokumen.kodDokumen.nama}"/> /
                            <a href="#" onclick="scan('${row.dokumen.idDokumen}','${actionBean.permohonan.idPermohonan}');return false;">Imbas</a>
                            <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png"
                                 onclick="scan('${row.dokumen.idDokumen}','${actionBean.permohonan.idPermohonan}');return false;" height="30" width="30" alt="Imbas"
                                 onmouseover="this.style.cursor='pointer';" title="Imbas untuk Dokumen ${row.dokumen.kodDokumen.nama}"/>
                        </c:if>--%>
                        <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png"
                             onclick="muatNaikForm('${row.folder.folderId}','${row.dokumen.idDokumen}'
                                 ,'${actionBean.permohonan.idPermohonan}');return false;" height="30" width="30" alt="Muat Naik"
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
                    </p>
                </display:column>
                <display:column title="<input type='checkbox' id='semua' name='semua' onclick='javascript:selectAll(this);' /> Cetak">
                    <c:if test="${row.dokumen.namaFizikal != null}">
                        <%--<s:button name="cetak" value="Cetak" onclick="doPrintViaApplet('${row.dokumen.idDokumen}');" class="btn"/>--%>
                        <p align="center">
                            <input type="checkbox" name="cetaksemua" id="cetaksemua${rowNum}" value="${row.dokumen.idDokumen}" class="cetakSemua"/>
                            <img src="${pageContext.request.contextPath}/pub/images/icons/_active__print.png"
                                 onclick="doPrintViaApplet('${row.dokumen.idDokumen}');" height="30" width="30" alt="cetak"
                                 onmouseover="this.style.cursor='pointer';" title="Cetak Dokumen ${row.dokumen.kodDokumen.nama}"/>
                        </p>
                        <c:set var="rowNum" value="${rowNum +1}"/>
                    </c:if>
                </display:column>
            </display:table>
            <s:hidden value="${actionBean.permohonan.idPermohonan}" name="permohonan.idPermohonan"/>
            <p><label>&nbsp;</label>
                <!--s:submit name="signSelected" value="T/tangan" class="btn" /-->
                <%--<s:button name="deleteSelected" value="Hapus" class="btn" onclick="if(confirm('Adakah anda pasti?')) {doSubmit(this.form,this.name,'folder_div');}"/>--%>
                <s:button name="addDocForm" value="Tambah" class="btn" onclick="addNew('${actionBean.permohonan.idPermohonan}');"/>
                <s:submit name="kembali" value="Kembali" class="btn"/>
                <%--<s:submit name="seterusnya" id="" value="Seterusnya" class="btn"/>--%>
            </p>
            <br/>

        </fieldset>
        <br/>
        <applet code="etanah.dokumen.print.DocumentPrinter" ARCHIVE="${pageContext.request.contextPath}/PrintApplet.jar,
                ${pageContext.request.contextPath}/commons-httpclient-2.0.2.jar,
                ${pageContext.request.contextPath}/commons-logging.jar,
                ${pageContext.request.contextPath}/swingx-1.6.jar,
                ${pageContext.request.contextPath}/log4j-1.2.12.jar,
                ${pageContext.request.contextPath}/jpedal_trial.jar,
                ${pageContext.request.contextPath}/PDFRenderer.jar"
                codebase = "${pageContext.request.contextPath}/"
                name     = "applet"
                id       = "applet"
                width    = "1"
                height   = "1"
                align    = "middle">
            <param name="uploadAction" value="<%=request.getContextPath()%>/PrintServlet"/>
            <param name ="method" value="pdfp">
            <%
                Cookie[] cookies = request.getCookies();
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < cookies.length; i++) {
                    sb.setLength(0);
                    sb.append(cookies[i].getName());
                    sb.append("=");
                    sb.append(cookies[i].getValue());
            %>
            <param name="Cookie<%= i%>" value="<%= sb.toString()%>"><%
                }
            %>
        </applet>

        <c:if test="${fn:length(actionBean.folderDokumenSebelum.senaraiKandungan) > 0
                      && actionBean.folderDokumenSebelum.folderId ne actionBean.folderDokumen.folderId}">
              <p class=title><img src="${pageContext.request.contextPath}/images/folderopen.gif" id="f2" class="open"/>
                  Fail ${actionBean.folderDokumenSebelum.tajuk}</p>
              <p class=instr>Senarai Dokumen dalam Fail untuk permohonan terdahulu.</p>
              <br/>
              <fieldset class="aras1" id="row2">
                  <legend>
                      Maklumat Fail Permohonan Sebelum
                  </legend>
                  <p>
                      <label>Carian :</label>
                      <s:select name="filterBy2" style="width:400" onchange="doSubmit(this.form, 'reload', 'folder_div');">
                          <s:option value="">Semua dokumen</s:option>
                          <s:options-map map="${actionBean.kodMapSebelum}"/>
                      </s:select>
                  </p>
                  <br/>
                  <display:table name="${actionBean.senaraiKandunganSebelum}" class="tablecloth" id="row2" style="width:100%">
                      <display:column title="Bil">${row2_rowNum}</display:column>
                      <display:column title="Kod Dokumen" property="kodDokumen.kod" />
                      <display:column title="Tajuk / <br/> No Rujukan">
                          ${row2.tajuk}
                          <br/>
                          ( no ruj :${row2.noRujukan} )
                      </display:column>
                      <display:column title="Klasifikasi" property="klasifikasi.nama" />
                      <display:column title="Dimasuk Oleh" property="infoAudit.dimasukOleh.nama" />
                      <%--<display:column title="Catatan" property="catatan" />--%>
                      <display:column title="Tarikh" property="infoAudit.tarikhMasuk" format="{0,date,dd/MM/yyyy hh:mm:ss}" />
                      <display:column title="Tindakan">
                          <p align="center">

                              <c:if test="${row2.namaFizikal != null}">
                                  <%--<a href="#" onclick="doViewReport('${row.dokumen.idDokumen}');return false;">Papar</a>--%>

                                  <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                       onclick="doViewReport('${row2.idDokumen}');" height="30" width="30" alt="papar"
                                       onmouseover="this.style.cursor='pointer';" title="Papar ${row2.kodDokumen.nama}"/>
                                  <c:if test="${row.dokumen.baru eq 'Y' || row.dokumen.baru eq ''}">
                                      <img src="${pageContext.request.contextPath}/pub/images/baharu.gif" alt="baru"/>
                                  </c:if>

                              </c:if>
                          </p>
                      </display:column>
                      <display:column title="Cetak">
                          <c:if test="${row2.namaFizikal != null}">
                              <%--<s:button name="cetak" value="Cetak" onclick="doPrintViaApplet('${row.dokumen.idDokumen}');" class="btn"/>--%>
                              <p align="center">
                                  <img src="${pageContext.request.contextPath}/pub/images/icons/_active__print.png"
                                       onclick="doPrintViaApplet('${row2.idDokumen}');" height="30" width="30" alt="cetak"
                                       onmouseover="this.style.cursor='pointer';" title="Cetak ${row2.kodDokumen.nama}"/>
                              </p>
                          </c:if>
                      </display:column>
                  </display:table>
                  <br/>
              </fieldset>
        </c:if>
    </s:form>
</div>

