<%-- 
    Document   : kemasukan_doc_aduan
    Created on : 07-Apr-2011, 00:24:50
    Author     : nordiyana
--%>

<%@ page contentType="text/html; charset=iso-8859-1" language="java"
import="java.util.*,java.text.*,java.util.Date" errorPage="" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript">
    <%--function refreshPage(){
    var url = '${pageContext.request.contextPath}/penguatkuasaan/kemasukan_aduan?reload';
    $.get(url,
    function(data){
    $('#page_div').html(data);
    },'html');
   }--%>

   function refreshPage(){
        var q = $('#form1').serialize();
        var url = document.form1.action + '?reload&';// + event;
        window.location = url+q;

    }
  function refreshPage1(){
        var q = $('#form1').serialize();
        var url = document.form1.action + '?reload&';// + event;
        window.location = url+q;

    }
    function addNew(v){
        window.open("${pageContext.request.contextPath}/penguatkuasaan/kemasukan_aduan?addDocForm&permohonan.idPermohonan=" + v, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
    }

    function signForm(v){
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v + '?signForm';
        window.open(url, 'etanah', "status=1,toolbar=0,location=0,menubar=0,width=1000,height=600");
    }

    function doViewReport(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }

    function doPrintViaApplet (docId) {
        //alert('tsttt');
        var a = document.getElementById('applet');
        a.doPrint(docId.toString());
    }

    function muatNaikForm1(folderId, dokumenId, idPermohonan) {
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/2)-(150/2);
        var url = '${pageContext.request.contextPath}/penguatkuasaan/upload?muatNaikForm1&folderId=' + folderId + '&dokumenId='
            + dokumenId + '&idPermohonan=' + idPermohonan;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
    }

    function scan(dokumenId, idPermohonan) {
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/2)-(150/2);
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

    <%-- $('.tooltip').tooltip({
         delay: 0,
         showURL: false
     });--%>

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

             //$('#row2').hide();
         });
</script>

<s:form name="form1" id="form1" beanclass="etanah.view.pengambilan.KemasukanAduanActionBean">
    <s:messages/>
    <div class="instr" align="center">
        <s:errors/>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Dokumen Tambahan</legend>
             <s:hidden name="folder.folderId"/>
             <%--<s:hidden name="folderDokumen.folderId"/>--%>
             <s:hidden name="permohonan.idPermohonan"/>
             <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>

            <display:table name="${actionBean.senaraiKandungan}" class="tablecloth" id="row" style="width:100%">

            <display:column title="Pilih"><s:checkbox name="chkbox" id="chkbox" value="${row.dokumen.idDokumen}"/></display:column>
            <%--<display:column title="Folder Id" property="dokumen.idDokumen" />--%>
            <display:column title="Kod Dokumen" property="dokumen.kodDokumen.kod" />
            <display:column title="Nama Dokumen" property="dokumen.kodDokumen.nama" />
            <display:column title="Klasifikasi" property="dokumen.klasifikasi.nama" />
            <display:column title="Catatan" property="catatan" />
            <display:column title="Dimasuk Oleh" property="dokumen.infoAudit.dimasukOleh.nama" />
            <display:column title="Tarikh" property="dokumen.infoAudit.tarikhMasuk" format="{0,date,dd/MM/yyyy hh:mm:ss}" />
            <display:column title="Tindakan">
             <p align="center">
                    <c:if test="${row.dokumen.namaFizikal == null}">
                        <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png"
                             onclick="muatNaikForm1('${row.folder.folderId}','${row.dokumen.idDokumen}'
                            ,'${actionBean.permohonan.idPermohonan}');return false;" height="30" width="30" alt="Muat Naik"
                             onmouseover="this.style.cursor='pointer';" title="Muat Naik untuk Dokumen ${row.dokumen.kodDokumen.nama}"/>
                        <%--/
                        <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png"
                             onclick="scan('${row.dokumen.idDokumen}','${actionBean.permohonan.idPermohonan}');return false;" height="30" width="30" alt="Imbas"
                             onmouseover="this.style.cursor='pointer';" title="Imbas untuk Dokumen ${row.dokumen.kodDokumen.nama}"/>--%>
                    </c:if>
                    <c:if test="${row.dokumen.namaFizikal != null}">
                        <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                             onclick="doViewReport('${row.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                             onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${row.dokumen.kodDokumen.nama}"/>
                        <c:if test="${row.dokumen.baru eq 'Y' || row.dokumen.baru eq ''}">
                            <img src="${pageContext.request.contextPath}/pub/images/baharu.gif" alt="baru"/>
                        </c:if>

                    </c:if>
                </p>
            </display:column>
            <%--<display:column title="Cetak">
                <c:if test="${row.dokumen.namaFizikal != null}">
                    <p align="center">
                        <img src="${pageContext.request.contextPath}/pub/images/icons/_active__print.png"
                             onclick="doPrintViaApplet('${row.dokumen.idDokumen}');" height="30" width="30" alt="cetak"
                             onmouseover="this.style.cursor='pointer';" title="Cetak Dokumen ${row.dokumen.kodDokumen.nama}"/>
                    </p>
                </c:if>
            </display:column>--%>
        </display:table>
        <p><label>&nbsp;</label>
            <s:submit name="deleteSelected" value="Hapus" class="btn" />
            <s:button name="addDocForm" value="Tambah" class="btn" onclick="addNew('${actionBean.permohonan.idPermohonan}');"/>

        </p>
        <br/>
    </fieldset>
            <p align="right">
                <s:submit class="btn" name="setKembali" value="Kembali"/>
                <s:submit class="btn" name="simpan" value="Selesai"/></p>
    </div>
   </s:form>

