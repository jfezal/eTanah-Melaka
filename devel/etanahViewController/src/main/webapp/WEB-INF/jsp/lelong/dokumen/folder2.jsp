<%-- 
    Document   : folder2
    Created on : May 15, 2013, 8:49:13 AM
    Author     : nur.aqilah
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>

<script type="text/javascript">
    function addNew(v){
        if($("#idPermohonan").val() == ""){
            alert('Sila masukkan " ID Permohonan " terlebih dahulu.');
            return;
        }
        window.open("${pageContext.request.contextPath}/lelong/dokumen/folder2?dokumenTambahanForm&permohonan.idPermohonan=" + v, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
    }
    
    function deleteDoc(form){
		var len = $('.noUrusan').length;
        var param = '';
        for(var i=0; i<len; i++){
            var ckd = $('#cek_'+i).is(":checked");
            alert(ckd);
            if(ckd){
                param = 'pilih';
            }
        }
        if(param == ''){
            alert('Sila pilih dokumen untuk dihapuskan.');
            return;
        }
        form = document.form1;
        var answer = confirm("Adakah anda pasti untuk Hapus?");
        if (answer) {
            form.action = "${pageContext.request.contextPath}/lelong/dokumen/folder2?deleteSelected";
            form.submit();
        }
    }
   
    function clearForm() {
                
        $("#idPermohonan").val('');
    }
    
    function doSearch(e,f) {
        var a = $('#idPermohonan').val();
    
        if(a == ''){
            alert('Sila masukan id Permohonan');            
            return;
        }
        f.action = f.action + '?' + e;
        f.submit();
    }
    
    function doSearch2 (idPermohonan) {
        var url = '${pageContext.request.contextPath}/lelong/dokumen/folder2?search&idPermohonan=' + idPermohonan;
        f = document.form1;
        f.action = url;
        f.submit();
    }
    
    function keputusanMahkamah(v){
        window.open("${pageContext.request.contextPath}/lelong/dokumen/folder2?showFormA&permohonan.idPermohonan=" + v, "eTanah",
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
        var url = '${pageContext.request.contextPath}/dokumen/scan/' + dokumenId + '?idPermohonan=' + idPermohonan;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=850,height=600");
    }

    function saveFolderInfoEvent(event, frm) {
        var q = $(frm).formSerialize();
        var url = frm.action + '?' + event;
        $.ajax({
            type:"POST",
            url : url,
            dataType : 'html',
            data : q,
            success : function(data) {
                $('#folder_div').html(data);
            }
        });
    }

    function selectAll(a){
        var len = $('.cetakSemua').length;
        for (var i=0; i<len; i++) {
            var c = document.getElementById("cetaksemua" + i);
            c.checked = a.checked;
        }
    }
    
    function printSelectedDocuments () {
        var documentsList = '';
        var len = $('.cetakSemua').length;
        for (var i=0; i<len; i++) {
            var c = document.getElementById("cetaksemua" + i);
            if (c.checked) {
                documentsList = documentsList +',' + c.value;
            }
        }
        if (documentsList == '') {
            alert('Sila Pilih Dokumen untuk dicetak.');
            return;
        } else {
            doPrintViaApplet(documentsList) ;
        }
    }
    
    function deleteItem(event, frm) {
        var q = $(frm).formSerialize();
        var url = frm.action + '?' + event;
        $.ajax({
            type:"POST",
            url : url,
            dataType : 'html',
            data : q,
            success : function(data) {
                $('#folder_div').html(data);
            }
        });
    }

    function doViewImage (val) {
        var url = '${pageContext.request.contextPath}lelong/dokumen/view/image/' + val;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=850,height=600,scrollbars=yes");
    }

    function saveInput(val, t) {
        var url = '${pageContext.request.contextPath}/dokumen/folder2?saveEditInfo&tajuk=' + val + '&idDok=' + $('#val_' + t.id).val();
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

    function refreshingPagingFolder(idPermohonan){
        var url = "${pageContext.request.contextPath}/lelong/dokumen/folder2?refreshpage&idPermohonan="+idPermohonan;
        $.post(url,
        function(data){
            $("#folderLelong").html(data);
        }, "html");
    }

        
</script>

<div id="folderLelong">
    <p>
        <s:messages />
        <s:errors />
    </p>

    <s:form beanclass="etanah.view.lelong.dokumen.FolderAction2" name="form1">
        <s:hidden name="folderDokumen.folderId"/>

        <div class="subtitle" id ="aa">
            <p class="title open" id="f1">
                Carian
            </p>
            <br/>
            <fieldset class="aras1">
                <br/>
                <p>
                    <label> ID Permohonan :</label>
                    <s:text name="idPermohonan" id = "idPermohonan" maxlength="20" size="31" onblur="this.value=this.value.toUpperCase();"/>
                </p>
                <br/>
                <p align="center">
                    <s:submit name="find" value="Cari" class="btn" onclick="doSearch(this.name, this.form);"/>
                    <s:submit name="reset" value="Isi Semula" class="btn" onclick="clearForm();"/>
                </p>
            </fieldset>
        </div>
        <br/>
        <c:if test="${actionBean.showForm eq true}">
            <c:if test="${fn:length(actionBean.senaraiPermohonan) > 0}">
                <div class="subtitle">
                    <p class="title open" id="f1">
                        Senarai Perserahan
                    </p>
                    <fieldset class="aras1">
                        <p align="center">
                            <label></label>
                            <c:set var="row_num" value="${actionBean.__pg_start}"/>
                            <display:table class="tablecloth" name="${actionBean.senaraiPermohonan}"
                                           cellpadding="0" cellspacing="0" id="line"
                                           requestURI="/lelong/dokumen/folder2"
                                           pagesize="10"                                   
                                           sort="external" size="${actionBean.__pg_total_records}" partialList="true">
                                <c:set var="row_num" value="${row_num+1}"/>
                                <display:column title="Bil">${row_num}</display:column>
                                <display:column title="ID Perserahan">
                                    <a href="#" onclick="doSearch2('${line.idPermohonan}');">${line.idPermohonan}</a>
                                </display:column>
                                <display:column property="kodUrusan.kod" title="Kod Urusan"/>
                                <display:column property="kodUrusan.nama" title="Urusan"/>
                                <display:column title="Tarikh Perserahan">
                                    <fmt:formatDate value="${line.infoAudit.tarikhMasuk}" pattern="d/MM/yyyy hh:mm:ss"/>
                                </display:column>
                            </display:table>
                        </p>               
                    </fieldset>
                </div>
            </c:if>
            <c:if test="${fn:length(actionBean.senaraiPermohonanCarian) > 0}">
                <div class="subtitle">
                    <p class="title open" id="f1">
                        Senarai Carian
                    </p>
                    <fieldset class="aras1">
                        <p align="center">
                            <label></label>
                            <c:set var="row_num" value="${actionBean.__pg_start}"/>
                            <display:table class="tablecloth" name="${actionBean.senaraiPermohonanCarian}"
                                           cellpadding="0" cellspacing="0" id="line"
                                           requestURI="/lelong/dokumen/folder2"
                                           pagesize="10"
                                           sort="external" size="${actionBean.__pg_total_records}" partialList="true">
                                <c:set var="row_num" value="${row_num+1}"/>
                                <display:column title="Bil">${row_num}</display:column>
                                <display:column title="ID Perserahan">
                                    <a href="#" onclick="doSearch2('${line.idCarian}');">${line.idCarian}</a>
                                </display:column>
                                <display:column property="urusan.kod" title="Kod Urusan"/>
                                <display:column property="urusan.nama" title="Urusan"/>
                                <display:column title="Tarikh Perserahan">
                                    <fmt:formatDate value="${line.infoAudit.tarikhMasuk}" pattern="d/MM/yyyy hh:mm:ss"/>
                                </display:column>
                            </display:table>
                        </p>
                    </fieldset>
                </div>
            </c:if>
        </c:if>
        <br/>
        <br/>

        <s:hidden name="permohonan.idPermohonan"/>
        <c:if test="${fn:length(actionBean.senaraiKandungan) > 0}">  
            <br/>
            <p class="title open" id="f1">
                <img src="${pageContext.request.contextPath}/pub/images/folderopen.gif" id="f11" class="open"/>
                Fail ${actionBean.folderDokumen.tajuk}</p>

            <p class=instr>Senarai Dokumen dalam Fail. </p>
            <br/><br/>
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
                    <display:column title="Pilih">                    
                        <s:checkbox name="chkbox" id="cek_${row_rowNum}" value="${row.dokumen.idDokumen}"/>                  
                        <s:hidden name="val" id="val_${row_rowNum-1}" value="${row.dokumen.idDokumen}"/>
                    </display:column>
                    <display:column title="No" class="noUrusan">
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
                    <%--<display:column title="<input type='checkbox' id='semua' name='semua' onclick='javascript:selectAll(this);' /> Cetak">
                         <c:if test="${row.dokumen.namaFizikal != null && (actionBean.pengguna.tahapSekuriti.kod >= row.dokumen.klasifikasi.kod)}">
                    <%--<s:button name="cetak" value="Cetak" onclick="doPrintViaApplet('${row.dokumen.idDokumen}');" class="btn"/>--%>
                    <%--  <p align="center">
                          <input type="checkbox" name="cetaksemua" id="cetaksemua${rowNum}" value="${row.dokumen.idDokumen}" class="cetakSemua"/>
                          <img src="${pageContext.request.contextPath}/pub/images/icons/_active__print.png"
                               onclick="doPrintViaApplet('${row.dokumen.idDokumen}');" height="30" width="30" alt="cetak"
                               onmouseover="this.style.cursor='pointer';" title="Cetak Dokumen ${row.dokumen.kodDokumen.nama}"/>
                      </p>
                      <c:set var="rowNum" value="${rowNum +1}"/>
                  </c:if>
              </display:column> --%>
                </display:table>
                <s:hidden value="${actionBean.permohonan.idPermohonan}" name="permohonan.idPermohonan"/>
                <p><label>&nbsp;</label>
                    <!--s:submit name="signSelected" value="T/tangan" class="btn" /-->
                    <s:submit name="checkbox" value="Hapus" class="btn" onclick="deleteDoc(this.form)"/>

                    <s:button name="addDocForm" value="Tambah" class="btn" onclick="addNew('${actionBean.permohonan.idPermohonan}');"/> 
                    <%--<s:button name="cetakSemua" value="Cetak Semua" class="btn" onclick="printSelectedDocuments();"/> 
                    <%-- <s:button name="paparScanItem" value="Papar Dok. Imbasan" class="longbtn" onclick="doViewImage('${actionBean.permohonan.idPermohonan}');"/>--%>

                    <c:if test="${actionBean.smk}">
                        <s:submit name="showFormA" id="" value="Keputusan Mahkamah" class="longbtn" />
                    </c:if>
                    <%--<s:submit name="seterusnya" id="" value="Seterusnya" class="btn"/>--%>
                </p>
                <br/>

            </fieldset>
        </c:if>
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
                  <%--                      <p>
                                            <label>Carian :</label>
                                            <s:select name="filterBy2" style="width:400" onchange="doSubmit(this.form, 'reload', 'folder_div');">
                                                <s:option value="">Semua dokumen</s:option>
                                                <s:options-map map="${actionBean.kodMapSebelum}"/>
                                            </s:select>
                                        </p>
                  --%>
                  <br/>
                  <display:table name="${actionBean.senaraiKandunganSebelum}" class="tablecloth" id="row2" style="width:100%">
                      <display:column title="Bil">${row2_rowNum}</display:column>
                      <display:column title="Kod Dokumen" property="kodDokumen.kod" />
                      <display:column title="Tajuk / <br/> No Rujukan">
                          ${row2.tajuk}
                          <br/>
                          ( no ruj :${row2.noRujukan} )
                      </display:column>
                      <display:column title="Klas." property="klasifikasi.nama" />
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
                      <%--                          <display:column title="Cetak">
                                                    <c:if test="${row2.namaFizikal != null}">
                      <%--<s:button name="cetak" value="Cetak" onclick="doPrintViaApplet('${row.dokumen.idDokumen}');" class="btn"/>
                      <p align="center">
                          <img src="${pageContext.request.contextPath}/pub/images/icons/_active__print.png"
                               onclick="doPrintViaApplet('${row2.idDokumen}');" height="30" width="30" alt="cetak"
                               onmouseover="this.style.cursor='pointer';" title="Cetak ${row2.kodDokumen.nama}"/>
                      </p>
                  </c:if>
              </display:column>--%>
                  </display:table>
                  <br/>
              </fieldset>
        </c:if>

    </s:form>
</div>
