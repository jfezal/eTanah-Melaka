<%-- 
    Document   : senarai_kertas_siasatan
    Created on : Aug 14, 2012, 11:24:21 AM
    Author     : latifah.iskak
--%>


<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>

<style type="text/css">

    .tablecloth{
        padding:0px;
        width:60%;
    }

    .infoLP{
        float: right;
        font-weight: bold;
        color:#003194;
        font-family:Tahoma;
        font-size: 13px;

    }
</style>


<script type="text/javascript">

    function addRecord(){
        var url = "${pageContext.request.contextPath}/penguatkuasaan/maklumat_kertas_siasatan?popupTambahKertasSiasatan";
    
        params  = 'width='+screen.width;
        params += ', height='+screen.height;
        params += ', top=0, left=0'
        params += ', fullscreen=no';
        params += ', directories=no';
        params += ', location=no';
        params += ', menubar=no';
        params += ', resizable=no';
        params += ', scrollbars=yes';
        params += ', status=no';
        params += ', toolbar=no';
        newwin=window.open(url,'PopUp', params);
        if (window.focus) {newwin.focus()}
        return false;
    }

    function editRecord(id){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_kertas_siasatan?popupEditKertasSiasatan&id='+id;
        params  = 'width='+screen.width;
        params += ', height='+screen.height;
        params += ', top=0, left=0'
        params += ', fullscreen=no';
        params += ', directories=no';
        params += ', location=no';
        params += ', menubar=no';
        params += ', resizable=no';
        params += ', scrollbars=yes';
        params += ', status=no';
        params += ', toolbar=no';
        newwin=window.open(url,'PopUp', params);
        if (window.focus) {newwin.focus()}
        return false;
    }


    function deleteRecord(id){
        if(confirm('Adakah anda pasti untuk hapus?')) {
            var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_operasi?deleteOperasiPenguatkuasaan&idOperasi='+id;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
           
        }
    }
    
    function refreshPageIP(){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_kertas_siasatan?refreshpage';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function muatNaikForm1(folderId, idPermohonan, dokumenKod, idRujukan) {
        //alert("idRujukan : "+idRujukan);
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/2)-(150/2);
        var url = '${pageContext.request.contextPath}/penguatkuasaan/upload_action?popupUpload&folderId=' + folderId
            + '&idPermohonan=' + idPermohonan + '&dokumenKod=' + dokumenKod + '&idRujukan=' + idRujukan;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
    }
    function doViewReport(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }

    function removeImej(idImej) {
        if(confirm('Adakah anda pasti untuk hapus?')) {
            var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_operasi?deleteSelected&idImej='+idImej;
            $.get(url,
            function(data){
                $('#page_div').html(data);
                refreshPage();
            },'html');
        }
    }
    
    function viewRecord(id){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_operasi?popupViewLaporanOperasi&idOperasi='+id;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
    }

    function viewRecordOP(id){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_operasi?popupViewLaporanOperasi&idOperasi='+id;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
    }
    
    function removeRecord(id){
        if(confirm('Adakah anda pasti untuk hapus?')) {
            var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_kertas_siasatan?deletePermohonan&idPermohonan='+id;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');}
    }




</script>
<s:form beanclass="etanah.view.penguatkuasaan.MaklumatKertasSiasatanActionBean">
    <s:messages />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Senarai Kertas Siasatan
            </legend>
            <c:if test="${edit}">
                <div class="instr-fieldset"><br>
                    Klik butang tambah untuk menambah senarai kertas siasatan
                </div>
                <br>
                <div class="content" align="center">
                    <display:table name="${actionBean.senaraiPermohonanBaru}" id="line" class="tablecloth" cellpadding="0" cellspacing="0">
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column title="ID Kertas Siasatan" property="idPermohonan"/>
                        <display:column title="No.Permohonan" property="permohonanSebelum.idPermohonan"/>
                        <display:column title="Kemaskini">
                            <div align="center">
                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="editRecord('${line.idPermohonan}')"/>
                            </div>
                        </display:column>
                        <display:column title="Hapus">
                            <div align="center">
                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeRecord('${line.idPermohonan}');"/>
                            </div>
                        </display:column>
                    </display:table>
                    <br>
                    <c:if test="${fn:length(actionBean.listAvailableOksForIP) ne 0}">
                        <s:button class="btn" value="Tambah" name="new" id="new" onclick="addRecord();"/>
                    </c:if>
                </div>
            </c:if>

            <c:if test="${view}">
                <br>

            </c:if>


        </fieldset>
    </div>
</s:form>

