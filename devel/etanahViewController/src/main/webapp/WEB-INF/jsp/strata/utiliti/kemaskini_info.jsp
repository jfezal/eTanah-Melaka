<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript">
    function addNew(v){
        window.open("${pageContext.request.contextPath}/strata/utiliti/dokumenTertinggal?addDocForm&permohonan.idPermohonan=" + v, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
    }

    function signForm(v){
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v + '?signForm';
        window.open(url, 'etanah', "status=1,toolbar=0,location=0,menubar=0,width=1000,height=600");
    }

    function doViewReport(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
    }
    
    function refreshPage(){
        window.location.reload();
    }

    function doViewLog(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v + '?viewLogDocument';
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
    }

    function doEditDoc(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v + '?showEditForm';
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=400,scrollbars=yes");
    }

    function doPrintViaApplet (docId) {
        //alert('tsttt');
        var a = document.getElementById('applet');        
        a.doPrint(docId.toString());
        //a.printDocument(docId.toString());
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
        var len = $('.cetakSemua2').length;        
        for (var i=0; i<len; i++) {
            var c = document.getElementById("cetaksemua2" + i);
            c.checked = a.checked;
        }	
    }

    function printSelectedDocuments () {
        var documentsList = '';
        var len = $('.cetakSemua2').length;    
        //alert("length : "+len);
        for (var i=0; i<len; i++) {
            var c = document.getElementById("cetaksemua2" + i);            
            if (c.checked) {                
                documentsList = documentsList +',' + c.value;
                //     alert("document List : "+documentsList);
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
        var url = '${pageContext.request.contextPath}/dokumen/view/image/' + val;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=850,height=600,scrollbars=yes");
    }

    function saveInput(val, t) {        
        var url = '${pageContext.request.contextPath}/strata/utiliti/dokumenTertinggal?saveEditInfo&tajuk=' + val + '&idDok=' + $('#val_' + t.id).val();        
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

        //$('#row2').hide();
    });
    
    function doSearch(f,e){       
        var v = $('#idPermohonan').val();
        if(v == ''){
            alert('Sila masukkan ID Permohonan.');
            $('#idPermohonan').focus();
            return;
        }
        f.action = f.action + '?' + e;        
        f.submit();
    }
    
    function clearForm(f) {
        $('#idPermohonan').val('');
    }
</script>
<div class="subtitile"> 
    <s:errors/>
    <s:messages/>
    <div class="displaytag">
        <s:form beanclass="etanah.view.strata.utiliti.UpdateInfoActionBean" id="tambah">
            <fieldset class="aras1">
                <s:hidden name="folderDokumen.folderId"/> 
                <legend>Kemaskini Data Permohonan</legend>
                <p>
                    <label>ID Permohonan :</label>
                    <s:text name="permohonan.idPermohonan" size="34" maxlength="20" id="idPermohonan" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>        
                <p>
                    <label>&nbsp;</label>
                    <s:submit name="search" value="Cari" class="btn" onclick=""/>&nbsp;
                    <s:submit name="reset" value="Isi Semula" class="btn" onclick=""/>
                </p>
                <br/>
            </fieldset>

            <c:if test="${actionBean.flag}">
                <fieldset class="aras1">
                    <legend>Data Permohonan</legend>
                    <p>
                        <!--<font color="green" size="-1"> Arahan : Sila pilih dokumen dan klik butang 'Cetak' </font><br/><br/>-->
                        <label>ID Permohonan :</label>
                        ${actionBean.permohonan.idPermohonan}                
                    </p>

                    <p>
                        <label>Kod Urusan/Urusan :</label>
                        ${actionBean.permohonan.kodUrusan.kod} - 
                        ${actionBean.permohonan.kodUrusan.nama}
                    </p>
                    <br/>

                    <c:set var="rowNum" value="0"/>
                    <c:set var="rowNum2" value="0"/>

                    <br/>
                </fieldset><br/>

                <display:table name="${actionBean.senaraiKandungan}" class="tablecloth" id="row" style="width:100%">

                    <display:column title="Bil">${row_rowNum}</display:column>
                    <display:column title="Kategori">${row}</display:column>
                    <display:column title="Data Permohonan" group="1">
                        <div id="t" title="Penyerah">Penyerah</div>
                        <div id="ta" title="Pemohon">Pemohon</div>
                        <div id="tb" title="Nama Projek">Nama Projek</div>
                        <div id="tc" title="Perbadanan Pengurusan">Perbadanan Pengurusan</div>
                    </display:column>
                   
                        <display:column title="Kemaskini">
                        <p align="center">
                            
                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                      height="30" width="30" alt="papar"
                                     onmouseover="this.style.cursor='pointer';" title="Papar Dokumen"/>
                                
                        </p>
                    </display:column>
                          
                </display:table><br>  


            </c:if>
        </s:form>
    </div>
</div>
