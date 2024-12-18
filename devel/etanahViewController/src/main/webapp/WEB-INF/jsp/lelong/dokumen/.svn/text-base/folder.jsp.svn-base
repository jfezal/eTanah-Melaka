<%-- 
    Document   : folder
    Created on : Apr 19, 2012, 3:34:54 PM
    Author     : mazurahayati.yusop, nur.aqilah
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.tabs.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<link type="text/css" rel="stylesheet" href="pub/styles/tabNavList.css"/>
<style type="text/css">
  .cursor_pointer {
    cursor:pointer;
  }
</style>
<script type="text/javascript">
    $(document).ready(function() {
        $("#tab_semakanPermohonan").tabs();
        $("#tab_semakanPermohonan").tabs('select', '#' + '${actionBean.selectedTab}');
    });
    function viewHakmilik(id){
        window.open("${pageContext.request.contextPath}/lelong/dokumen/folder?viewhakmilikDetail&idHakmilik="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=450");
    }
            
    function recordKehadiranDanNotis(id2,id1){
        window.open("${pageContext.request.contextPath}/lelong/dokumen/folder?viewRecordDetail&idPermohonan=" + id1 + "&idEnkuiri=" + id2, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=700");
    }
    function viewDetail(id){
        window.open("${pageContext.request.contextPath}/lelong/dokumen/folder?viewDetail&idLelong="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=400");
    }
    
    function viewDetailN9(id){
        window.open("${pageContext.request.contextPath}/lelong/dokumen/folder?viewDetailN9&idLelong="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=400");
    }
    
    function viewPembida(id){
        window.open("${pageContext.request.contextPath}/lelong/dokumen/folder?viewPembida&idLelong="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=300");
    }
    
    function viewSyarikat(id){
        window.open("${pageContext.request.contextPath}/lelong/dokumen/folder?viewSyarikat&idSytJlb="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=400");
    }
</script>
<script type="text/javascript">
    function addNew(v){
        if($("#idPermohonan").val() == ""){
            alert('Sila masukkan " ID Permohonan " terlebih dahulu.');
            return;
        }
        window.open("${pageContext.request.contextPath}/lelong/dokumen/folder?dokumenTambahanForm&permohonan.idPermohonan=" + v, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
    }
    
    function doSearch2 (idPermohonan) {
        var url = '${pageContext.request.contextPath}/lelong/dokumen/folder?search&idPermohonan=' + idPermohonan;
        f = document.form1;
        f.action = url;
        f.submit();
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


    function keputusanMahkamah(v){
        window.open("${pageContext.request.contextPath}/lelong/dokumen/folder?showFormA&permohonan.idPermohonan=" + v, "eTanah",
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
        var a = document.getElementById('applet');
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
            type: "POST",
            url: url,
            dataType: 'html',
            data: q,
            success: function(data) {
                $('#folder_div').html(data);
            }
        });
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

    function refreshingPagingFolder(idPermohonan){
        var url = "${pageContext.request.contextPath}/lelong/dokumen/folder?refreshpage&idPermohonan="+idPermohonan;
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
    <s:form beanclass="etanah.view.lelong.dokumen.FolderAction" name="form1">
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
                                           requestURI="/lelong/dokumen/folder"
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
                                           requestURI="/lelong/dokumen/folder"
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
  <!--edited-->
  <!--add tab for semakan permohonan-->
  <c:if test="${actionBean.showForm1 eq true}">
  <br>
  <div id="tab_semakanPermohonan">
    <ul>
      <c:if test="${fn:length(actionBean.senaraiKandungan) > 0}"> 
      <li><a href="#maklumat_permohonan" id="tab1">Maklumat Permohonan</a></li>
      </c:if>
      <c:if test="${fn:length(actionBean.senaraiKandungan) > 0}">
      <li><a href="#senarai_dokumen" id="tab2">Senarai Dokumen</a></li>
      </c:if>
      <c:if test="${fn:length(actionBean.permohonan.senaraiHakmilik) > 0}">
      <li><a href="#maklumat_hakmilik" id="tab3">Maklumat Hakmilik</a></li>
      </c:if>
      <c:if test="${fn:length(actionBean.permohonan.senaraiFasa) > 0}">
      <li><a href="#proses_permohonan" id="tab4">Proses Permohonan</a></li>
      </c:if>
      <c:if test="${fn:length(actionBean.senaraiEnkuiri2) > 0}">
      <li><a href="#sejarah_siasatan" id="tab5">Sejarah Siasatan</a></li>
      </c:if>
      <c:if test="${fn:length(actionBean.senaraiLelongan) > 0}">
      <li><a href="#sejarah_lelongan" id="tab5">Sejarah Lelongan</a></li>
      </c:if>
    </ul>
      <div id="maklumat_permohonan">
        <c:if test="${fn:length(actionBean.senaraiKandungan) > 0}"> 
            <p class="title open" id="f1">
                Maklumat Permohonan
            </p>
            <br>
            <fieldset class="aras1">

                <p><label for="idPermohonan">ID Permohonan/ID Perserahan :</label>
                    <font style="text-transform:uppercase;">${actionBean.permohonan.idPermohonan}</font>
                </p>
                <c:if test="${actionBean.idSebelum ne null}">
                    <p>
                        <label>ID Permohonan Terdahulu: </label>
                        <font style="text-transform:uppercase;">${actionBean.idSebelum}</font>&nbsp;
                    </p>
                </c:if>
                <p><label for="kodUrusan">Kod Urusan/Urusan :</label>
                    <font style="text-transform:uppercase;">${actionBean.permohonan.kodUrusan.kod} - ${actionBean.permohonan.kodUrusan.nama}</font>
                </p>

                <p><label for="tarikhDaftar">Tarikh Perserahan/Permohonan :</label>
                    <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.permohonan.infoAudit.tarikhMasuk}"/>     <fmt:formatDate pattern="hh:mm aa" value="${actionBean.permohonan.infoAudit.tarikhMasuk}"/>
                </p>

                <p><label for="penyerah">Penyerah :</label>
                    <font style="text-transform:uppercase;">${actionBean.permohonan.penyerahNama}</font>
                </p>

                <p><label for="status">Status Permohonan:</label>
                    <c:if test="${actionBean.kodStatusPermohonan.kod eq 'SD'}">
                        <font style="text-transform:uppercase;">GANTUNG (PERINTAH MAHKAMAH)</font>
                    </c:if>
                    <c:if test="${actionBean.kodStatusPermohonan.kod ne 'SD'}">
                        <font style="text-transform:uppercase;">${actionBean.kodStatusPermohonan.nama}</font>
                    </c:if>

                </p>
                <br/>
            </fieldset>
        </c:if>
    </div>
      <div id="senarai_dokumen">
        <c:if test="${fn:length(actionBean.senaraiKandungan) > 0}">

            <br/>
            <p class="title open" id="f1">
                ID Permohonan : <font color="black">${actionBean.permohonan.idPermohonan}</font><br/>
                Kod Urusan/Urusan : <font color="black">${actionBean.permohonan.kodUrusan.kod} - ${actionBean.permohonan.kodUrusan.nama}</font>
            </p>
            <p class="title open" id="f1">
                <img src="${pageContext.request.contextPath}/pub/images/folderopen.gif" id="f11" class="open"/>
                Fail ${actionBean.folderDokumen.tajuk}</p>

            <p class=instr>Senarai Dokumen dalam Fail. </p>
            <br/>
            <s:hidden name="permohonan.idPermohonan"/>
            <fieldset class="aras1" id="row1">
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
                        <s:checkbox name="chkbox" value="${row.dokumen.idDokumen}"/>                  
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
                        </div>
                    </display:column>
                </display:table>
                <s:hidden value="${actionBean.permohonan.idPermohonan}" name="permohonan.idPermohonan"/>
                <p><label>&nbsp;</label>
                    <s:submit name="deleteSelected" value="Hapus" class="btn" />
                    <s:button name="addDocForm" value="Tambah" class="btn" onclick="addNew('${actionBean.permohonan.idPermohonan}');"/>
                    <c:if test="${actionBean.smk}">
                        <s:submit name="showFormA" id="" value="Keputusan Mahkamah" class="longbtn" />
                    </c:if>
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
              <br/>
              <p class="title open" id="f1">
                  ID Permohonan : <font color="black">${actionBean.permohonan.permohonanSebelum.idPermohonan}</font><br/>
                  Kod Urusan/Urusan : <font color="black">${actionBean.permohonan.permohonanSebelum.kodUrusan.kod} -
                      ${actionBean.permohonan.permohonanSebelum.kodUrusan.nama}</font>
              </p>
              <p class=title><img src="${pageContext.request.contextPath}/images/folderopen.gif" id="f2" class="open"/>
                  Fail ${actionBean.folderDokumenSebelum.tajuk}</p>
              <p class=instr>Senarai Dokumen dalam Fail untuk permohonan terdahulu.</p>
              <br/>
              <fieldset class="aras1" id="row2">
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
                      <display:column title="Tarikh" property="infoAudit.tarikhMasuk" format="{0,date,dd/MM/yyyy hh:mm:ss}" />
                      <display:column title="Tindakan">
                          <p align="center">

                              <c:if test="${row2.namaFizikal != null}">
                                  <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                       onclick="doViewReport('${row2.idDokumen}');" height="30" width="30" alt="papar"
                                       onmouseover="this.style.cursor='pointer';" title="Papar ${row2.kodDokumen.nama}"/>
                                  <c:if test="${row.dokumen.baru eq 'Y' || row.dokumen.baru eq ''}">
                                      <img src="${pageContext.request.contextPath}/pub/images/baharu.gif" alt="baru"/>
                                  </c:if>
                              </c:if>
                          </p>
                      </display:column>
                  </display:table>
                  <br/>
              </fieldset>
        </c:if>
        <br>
    </div>
        <div id="maklumat_hakmilik">
        <c:if test="${fn:length(actionBean.permohonan.senaraiHakmilik) > 0}"> 
            <p class="title open" id="f1">
                Maklumat Hakmilik 
            </p>
            <br/>
            <fieldset class="aras1">
                <p>
                    <display:table class="tablecloth" name="${actionBean.permohonan.senaraiHakmilik}"  cellpadding="0" cellspacing="0" id="line">
                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                        <display:column   title="ID Hakmilik" style="text-transform:uppercase;">
                            <a href="#" onclick="viewHakmilik('${line.hakmilik.idHakmilik}');return false;">${line.hakmilik.idHakmilik}</a>
                        </display:column>
                        <display:column property="hakmilik.noLot" title="No Lot" style="text-transform:uppercase;" />
                        <display:column title="Luas" style="text-transform:uppercase;"><fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                        <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah" style="text-transform:uppercase;"/>
                        <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" style="text-transform:uppercase;"/>
                    </display:table>
                </p>
            </fieldset>
            <br>
        </c:if>
    </div>
        <br>
        <div id="proses_permohonan">
        <c:if test="${fn:length(actionBean.permohonan.senaraiFasa) > 0}"> 
            <p class="title open" id="f1">
                Proses Permohonan:
            </p>
            <br/>
            <fieldset class="aras1">
                <display:table name="${actionBean.permohonan.senaraiFasa}" class="tablecloth" id="line" defaultorder="ascending">

                    <display:column property="infoAudit.tarikhMasuk" title="Tarikh" sortable="true" format="{0,date,dd/MM/yyyy hh:mm aa}"/>

                    <display:column property="infoAudit.dimasukOleh.nama" title="Diproses Oleh" style="text-transform:uppercase;"/>
                    <c:if test="${line.idAliran eq 'kemasukanPerintah'}">
                        <display:column title="ID Aliran" style="text-transform:uppercase;">Kemasukan Permohonan</display:column>
                    </c:if>
                    <c:if test="${line.idAliran eq 'semakanPermohonan'}">
                        <display:column title="ID Aliran" style="text-transform:uppercase;">Semakan Permohonan</display:column>
                    </c:if>
                    <c:if test="${line.idAliran eq 'janaNotisSiasatan'}">
                        <display:column title="ID Aliran" style="text-transform:uppercase;">Jana Notis Siasatan</display:column>
                    </c:if>
                    <c:if test="${line.idAliran eq 'cetakNotisSiasatan'}">
                        <display:column title="ID Aliran" style="text-transform:uppercase;">Cetak Notis Siasatan</display:column>
                    </c:if>
                    <c:if test="${line.idAliran eq 'rekodNotisSiasatan'}">
                        <display:column title="ID Aliran" style="text-transform:uppercase;">Rekod Penghantaran</display:column>
                    </c:if>
                    <c:if test="${line.idAliran eq 'semakRekodPenghantaran'}">
                        <display:column title="ID Aliran" style="text-transform:uppercase;">Semak Rekod Penghantaran</display:column>
                    </c:if>
                    <c:if test="${line.idAliran eq 'drafWarta'}">
                        <display:column title="ID Aliran" style="text-transform:uppercase;">Draf Warta</display:column>
                    </c:if>
                    <c:if test="${line.idAliran eq 'cetakWarta'}">
                        <display:column title="ID Aliran" style="text-transform:uppercase;">Cetak Borang 2A</display:column>
                    </c:if>
                    <c:if test="${line.idAliran eq 'mohonTangguh'}">
                        <display:column title="ID Aliran" style="text-transform:uppercase;">Permohonan Penangguhan</display:column>
                    </c:if>
                    <c:if test="${line.idAliran eq 'kpsnSiasatan'}">
                        <display:column title="ID Aliran" style="text-transform:uppercase;">Rekod Keputusan Siasatan</display:column>
                    </c:if>
                    <c:if test="${line.idAliran eq 'bayaranPerintah'}">
                        <display:column title="ID Aliran" style="text-transform:uppercase;">Bayaran</display:column>
                    </c:if>
                    <c:if test="${line.idAliran eq 'sedia16H'}">
                        <display:column title="ID Aliran" style="text-transform:uppercase;">Penyediaan Borang 16H</display:column>
                    </c:if>
                    <c:if test="${line.idAliran eq 'semak16HLantikJurulelong'}">
                        <display:column title="ID Aliran" style="text-transform:uppercase;">Semak Penyediaan Borang 16H dan Lantikan Jurulelong</display:column>
                    </c:if>
                    <c:if test="${line.idAliran eq'rekodPenghantaran16H'}">
                        <display:column title="ID Aliran" style="text-transform:uppercase;">Rekod Penghantaran Borang 16H</display:column>
                    </c:if>
                    <c:if test="${line.idAliran eq 'cetak16H'}">
                        <display:column title="ID Aliran" style="text-transform:uppercase;">Penyediaan Perisytiharan Jualan</display:column>
                    </c:if>
                    <c:if test="${line.idAliran eq 'sediaPengisytiharan'}">
                        <display:column title="ID Aliran" style="text-transform:uppercase;">Penyediaan Perisytiharan Jualan</display:column>
                    </c:if>
                    <c:if test="${line.idAliran eq 'semakPengisytiharan'}">
                        <display:column title="ID Aliran" style="text-transform:uppercase;">Semak, Cetak Dan Perakukan Perisytiharan Jualan</display:column>
                    </c:if>
                    <c:if test="${line.idAliran eq 'kmskJurulelong'}">
                        <display:column title="ID Aliran" style="text-transform:uppercase;">Kemasukan Jurulelong</display:column>
                    </c:if>
                    <c:if test="${line.idAliran eq 'rekodBidaanJLB'}">
                        <display:column title="ID Aliran" style="text-transform:uppercase;">Merekod Keputusan Lelongan</display:column>
                    </c:if>
                    <c:if test="${line.idAliran eq 'rekodBidaan'}">
                        <display:column title="ID Aliran" style="text-transform:uppercase;">Merekod Keputusan Lelongan</display:column>
                    </c:if>
                    <c:if test="${line.idAliran eq 'semakPembida'}">
                        <display:column title="ID Aliran" style="text-transform:uppercase;">Semak Rekod Bidaan</display:column>
                    </c:if>
                    <c:if test="${line.idAliran eq 'cetakSuratLantikan'}">
                        <display:column title="ID Aliran" style="text-transform:uppercase;">Cetak Surat Lantikan Jurulelong Berlesen</display:column>
                    </c:if>
                    <c:if test="${line.idAliran eq 'sediaSuratBakiBidaan'}">
                        <display:column title="ID Aliran" style="text-transform:uppercase;">Jana Surat Bayaran Baki Harga Bidaan</display:column>
                    </c:if>
                    <c:if test="${line.idAliran eq 'cetakSuratBakiBidaan'}">
                        <display:column title="ID Aliran" style="text-transform:uppercase;">Cetak Surat Bayaran Baki Harga Bidaan</display:column>
                    </c:if>
                    <c:if test="${line.idAliran eq 'cetakSuratLucutHak'}">
                        <display:column title="ID Aliran" style="text-transform:uppercase;">Rekod Pembayaran Baki</display:column>
                    </c:if>
                    <c:if test="${line.idAliran eq 'sedia16I'}">
                        <display:column title="ID Aliran" style="text-transform:uppercase;">Jana Borang 16I</display:column>
                    </c:if>
                    <c:if test="${line.idAliran eq 'sediaCetak16I'}">
                        <display:column title="ID Aliran" style="text-transform:uppercase;">Cetak Borang 16I</display:column>
                    </c:if>
                    <c:if test="${line.idAliran eq 'Cetak16I'}">
                        <display:column title="ID Aliran" style="text-transform:uppercase;">Cetak Borang 16I</display:column>
                    </c:if>
                    <c:if test="${line.idAliran eq 'uploadAkuanPenerimaan'}">
                        <display:column title="ID Aliran" style="text-transform:uppercase;">Muat Naik Akuan Penerimaan</display:column>
                    </c:if>
                    <c:if test="${line.idAliran eq 'sediaSijilRujuk'}">
                        <display:column title="ID Aliran" style="text-transform:uppercase;">Sedia dan Cetak Sijil Rujukan Mahkamah</display:column>
                    </c:if>
                    <c:if test="${line.idAliran eq 'cetakSijilRujuk'}">
                        <display:column title="ID Aliran" style="text-transform:uppercase;">Semak Dan Cetak Sijil Rujukan Mahkamah</display:column>
                    </c:if>
                    <c:if test="${line.idAliran eq 'cetakSuratTolak'}">
                        <display:column title="ID Aliran" style="text-transform:uppercase;">Cetak Surat Tolak</display:column>
                    </c:if>
                    <c:if test="${line.idAliran eq 'suratTolakanPermohonan'}">
                        <display:column title="ID Aliran" style="text-transform:uppercase;">Cetak Surat Tolak</display:column>
                    </c:if>
                    <display:column property="keputusan.nama" title="Status" style="text-transform:uppercase;"/>
                    <c:if test="${line.keputusan.kod eq 'EM'}">
                        <c:forEach items="${actionBean.getCatatan}" var="line2" >
                            <display:column title="Ulasan" style="text-transform:uppercase;">${line2.catatan}</display:column>
                        </c:forEach>
                    </c:if>
                    <c:if test="${line.keputusan.kod eq 'LM'}">
                        <c:forEach items="${actionBean.getCatatan}" var="line2" >
                            <display:column title="Ulasan" style="text-transform:uppercase;">${line2.catatan}</display:column>
                        </c:forEach>
                    </c:if>
                    <c:if test="${keputusan.kod ne 'EM' && keputusan.kod ne 'LM'}">
                        <display:column property="ulasan" title="Ulasan" style="text-transform:uppercase;"/>
                    </c:if>
                </display:table>
            </fieldset>
        </c:if>
        </div>
        <br>
        <div id="sejarah_siasatan">
        <c:if test="${fn:length(actionBean.senaraiEnkuiri2) > 0}"> 
            <p class="title open" id="f1">
                Sejarah Siasatan
            </p>
            <br/>
            <fieldset class="aras1">
                <display:table class="tablecloth" name="${actionBean.senaraiEnkuiri2}" id="line" defaultorder="ascending">
                    <display:column title="Bil" sortable="true" >${line_rowNum}</display:column>
                    <display:column title="Tarikh"> <a href="#" onclick="recordKehadiranDanNotis('${line.idEnkuiri}','${line.permohonan.idPermohonan}' );return false;"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhEnkuiri}"/></a></display:column>
                    <display:column title="Masa"><fmt:formatDate pattern="hh:mm aaa" value="${line.tarikhEnkuiri}"/></display:column>
                    <display:column property="tempat" title="Tempat" style="text-transform:uppercase;" class="nama${line_rowNum}"/>
                    <display:column property="perkara" title="Perkara" style="text-transform:uppercase;" class="nama${line_rowNum}"/>
<!--                    display:column property="status.nama" title="Status" style="text-transform:uppercase;"/>-->
                    <c:if test="${line.status.kod eq 'AK'}">
                        <display:column title="Status" class="" style="text-transform:uppercase;"> </display:column>
                    </c:if>
                    <c:if test="${line.status.kod ne 'AK'}">
                        <display:column property="status.nama" title="Status" class="" style="text-transform:uppercase;"/>
                    </c:if>
                    <display:column property="ulasanPegawai" title="Ulasan Pejabat Tanah" style="text-transform:uppercase;" class="nama${line_rowNum}"/>
                </display:table>              
            </fieldset>
        </c:if>
        </div>
        <br>
        <div id="sejarah_lelongan">
        <c:if test="${fn:length(actionBean.senaraiLelongan) > 0}"> 
            <p class="title open" id="f1">
                Sejarah Lelongan
            </p>
            <br/>
            <fieldset class="aras1">
                <display:table class="tablecloth" name="${actionBean.senaraiLelongan}" id="line" defaultorder="ascending">
                    <s:hidden name="idLelong"/>
                    <display:column title="Bil" sortable="true" >${line_rowNum}</display:column>
                    <display:column title="IDHakmilik" property="hakmilikPermohonan.hakmilik.idHakmilik" style="text-transform:uppercase;"/>
                    <!--FOR KOD NEGERI 04-->
                    <c:if test="${actionBean.senaraiPembida ne null}">
                        <display:column title="Tarikh Lelongan Awam"><a href="#" onclick="viewPembida('${line.idLelong}');return false;"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhLelong}"/></a></display:column>
                    </c:if>
                    <c:if test="${actionBean.pembidaList eq null}">
                        <display:column title="Tarikh Lelongan Awam"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhLelong}"/></display:column>
                    </c:if>
                    <!--FOR KOD NEGERI 05-->
                    <c:if test="${actionBean.senaraiPembeli ne null}">
                        <display:column title="Tarikh Lelongan Awam"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhLelong}"/></display:column>
                    </c:if>
                    <display:column title="Masa"><fmt:formatDate pattern="hh:mm aaa" value="${line.tarikhLelong}"/></display:column>
                    <display:column property="tempat" title="Tempat" class="nama${line_rowNum}" style="text-transform:uppercase;"/>
                    <display:column title="Harga Rizab (RM)"><div align="right">
                            <fmt:formatNumber pattern="#,##0.00" value="${line.hargaRizab}"/>
                        </div></display:column>
                    <c:if test="${line.enkuiri.caraLelong eq 'A'}">
                    <display:column title="Harga Bida (RM)"><div align="right">
                            <fmt:formatNumber pattern="#,##0.00" value="${line.hargaBidaan}"/>
                        </div></display:column>
                    </c:if>
                    <c:if test="${line.enkuiri.caraLelong eq 'B'}">
                    <display:column title="Harga Bida (RM)"><div align="right">
                            <fmt:formatNumber pattern="#,##0.00" value="${line.enkuiri.hargaBida}"/>
                        </div></display:column>
                    </c:if>
                    <display:column title="Deposit (RM)"><div align="right">
                            <fmt:formatNumber pattern="#,##0.00" value="${line.deposit}"/>
                        </div></display:column>
                    <display:column title="Tarikh Akhir Bayaran"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhAkhirBayaran}"/></display:column>            
                    <display:column title="Jurulelong" style="text-transform:uppercase;"><a href="#" onclick="viewSyarikat('${line.jurulelong.sytJuruLelong.idSytJlb}');return false;">${line.jurulelong.nama}</a></display:column>
                    <display:column title="Status"style="text-transform:uppercase;" >${line.kodStatusLelongan.nama}</display:column>
                    <!--FOR KOD NEGERI 04-->
                    <c:if test="${actionBean.melaka eq true}">
                        <c:forEach items="${actionBean.checkListStatusPembida2}" var="line2" >
                            <c:if test="${fn:length(actionBean.checkListStatusPembida2)==1}">
                                <c:if test="${line.idLelong eq line2.lelong.idLelong}">
                                    <display:column title="Pembeli" style="text-transform:uppercase;"><a href="#" onclick="viewDetail('${line.idLelong}');return false;">${line2.pihak.nama}</a></display:column>
                                </c:if>
                                <c:if test="${line.idLelong ne line2.lelong.idLelong}">
                                    <display:column title="Pembeli" style="text-transform:uppercase;"></display:column>
                                </c:if>

                            </c:if>
                            <c:if test="${fn:length(actionBean.checkListStatusPembida2)>=2}">
                                <c:if test="${line.idLelong eq line2.lelong.idLelong}">
                                    <display:column title="Pembeli" style="text-transform:uppercase;"><a href="#" onclick="viewDetail('${line.idLelong}');return false;">${line2.pihak.nama}</a></display:column>
                                </c:if>                            
                            </c:if>                       
                        </c:forEach> 
                    </c:if>
                    <!--FOR KOD NEGERI 05-->
                    <c:if test="${actionBean.melaka eq false}">
                        <c:if test="${actionBean.senaraiPembeli ne null}">
                            <c:if test="${line.pembida.idPihak ne null}">
                                <display:column title="Pembeli" style="text-transform:uppercase;"><a href="#" onclick="viewDetailN9('${line.idLelong}');return false;">${line.pembida.nama}</a></display:column>  
                            </c:if>
                            <c:if test="${line.pembida.idPihak eq null}">
                                <display:column title="Pembeli" style="text-transform:uppercase;">TIADA</display:column>
                            </c:if>
                       </c:if>
                    </c:if>            
                </display:table>
            </fieldset>
        </c:if>
        </div>
     </c:if>
  </s:form>
  </div>
</div>
