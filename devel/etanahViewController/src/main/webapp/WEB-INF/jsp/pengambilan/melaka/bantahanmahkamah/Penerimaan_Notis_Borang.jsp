<%--
    Document   : Penerimaan_Notis_Borang
    Created on : May 9, 2011, 12:17:50 PM
    Author     : Rajesh
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<script type="text/javascript">


   <%-- function validate(){
        var bil1 = '${fn:length(actionBean.permohonan.senaraiPemohon)}';
        var bil = '${fn:length(actionBean.hakmilik.senaraiPihakBerkepentingan)}';

        for (var i = 0; i < bil1; i++){

            if($('#namaPengahantarP'+i).val() == "" ){
                alert("Sila Masukkan Nama Penghantar Notis");
                $('#namaPengahantarP'+i).focus();
                return false;
            }
            if($('#kodStatusTerimaP'+i).val() == "" ){
                alert("Sila Pilih Status Penyampaian");
                $('#kodStatusTerimaP'+i).focus();
                return false;
            }
            if($('#kodPenghantaranP'+i).val() == "" ){
                alert("Sila Pilih Cara Penghantaran");
                $('#kodPenghantaranP'+i).focus();
                return false;
            }
            if($('#tarikhDihantarP'+i).val() == "" ){
                alert("Sila Pilih Tarikh Hantar");
                $('#tarikhDihantarP'+i).focus();
                return false;
            }
            if($('#tarikhTerimaP'+i).val() == "" ){
                alert("Sila Pilih Tarikh Terima");
                $('#tarikhTerimaP'+i).focus();
                return false;
            }
            if($('#catatanPenerimaanP'+i).val() == "" ){
                alert("Sila Masukkan Catatan");
                $('#catatanPenerimaanP'+i).focus();
                return false;
            }
        }

        for (var i = 0; i < bil; i++){

            if($('#namaPengahantar'+i).val() == "" ){
                alert("Sila Masukkan Nama Penghantar Notis");
                $('#namaPengahantar'+i).focus();
                return false;
            }
            if($('#kodStatusTerima'+i).val() == "" ){
                alert("Sila Pilih Status Penyampaian");
                $('#kodStatusTerima'+i).focus();
                return false;
            }
            if($('#kodPenghantaran'+i).val() == "" ){
                alert("Sila Pilih Cara Penghantaran");
                $('#kodPenghantaran'+i).focus();
                return false;
            }
            if($('#tarikhDihantar'+i).val() == "" ){
                alert("Sila Pilih Tarikh Hantar");
                $('#tarikhDihantar'+i).focus();
                return false;
            }
            if($('#tarikhTerima'+i).val() == "" ){
                alert("Sila Pilih Tarikh Terima");
                $('#tarikhTerima'+i).focus();
                return false;
            }
            if($('#catatanPenerimaan'+i).val() == "" ){
                alert("Sila Masukkan Catatan");
                $('#catatanPenerimaan'+i).focus();
                return false;
            }
        }
        return true;
    }--%>

    function validateMahkamah(){

        if($('#namaPengahantarM').val() == "" ){
            alert("Sila Masukkan Nama Penghantar Notis");
            $('#namaPengahantarM').focus();
            return false;
        }
        if($('#kodStatusTerimaM').val() == "" ){
            alert("Sila Pilih Status Penyampaian");
            $('#kodStatusTerimaM').focus();
            return false;
        }
        if($('#kodPenghantaranM').val() == "" ){
            alert("Sila Pilih Cara Penghantaran");
            $('#kodPenghantaranM').focus();
            return false;
        }
        if($('#tarikhDihantarM').val() == "" ){
            alert("Sila Pilih Tarikh Hantar");
            $('#tarikhDihantarM').focus();
            return false;
        }
        if($('#tarikhTerimaM').val() == "" ){
            alert("Sila Pilih Tarikh Terima");
            $('#tarikhTerimaM').focus();
            return false;
        }
        if($('#catatanPenerimaanM').val() == "" ){
            alert("Sila Masukkan Catatan");
            $('#catatanPenerimaanM').focus();
            return false;
        }
        return true;
    }


    function muatNaikForm(idPihak) {
        var idHakmilik = $('#idHakmilik').val();
        var showPP = $('#showPP').val();
        var showHP = $('#showHP').val();
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/2)-(150/2);
        var url = '${pageContext.request.contextPath}/pengambilan/penerimaan_notis_borang_BantahanMLK?popupUpload&idPihak='+idPihak+'&idHakmilik='+idHakmilik+'&showPP='+showPP+'&showHP='+showHP;
        window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
    }

    function muatNaikFormPemohon(idPihak) {
        var idHakmilik = $('#idHakmilik').val();
        var showPP = $('#showPP').val();
        var showHP = $('#showHP').val();
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/2)-(150/2);
        var url = '${pageContext.request.contextPath}/pengambilan/penerimaan_notis_borang_BantahanMLK?popupUpload&idPihak='+idPihak+'&idHakmilik='+idHakmilik+'&showPP='+showPP+'&showHP='+showHP+'&isPemohonPihak=true';
        window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
    }

    function muatNaikFormMahkamah() {
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/2)-(150/2);
        var url = '${pageContext.request.contextPath}/pengambilan/penerimaan_notis_borang_BantahanMLK?popupUpload&isMahkamah=true';
        window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
    }
    function scanMahkamah() {
        var url = '${pageContext.request.contextPath}/pengambilan/penerimaan_notis_borang_BantahanMLK?popupScanMahkamah&isMahkamah=true';
        window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=800,height=700");
    }

    function scanPemohon(idPihak) {
        var idHakmilik = $('#idHakmilik').val();
        var showPP = $('#showPP').val();
        var showHP = $('#showHP').val();
        var url = '${pageContext.request.contextPath}/pengambilan/penerimaan_notis_borang_BantahanMLK?popupScanPemohon&idPihak='+idPihak+'&idHakmilik='+idHakmilik+'&showPP='+showPP+'&isPemohonPihak=true';
        window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=800,height=700");
    }


    function doViewReport(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }

    function addPenghantarNotis(){
        var idHakmilik = $('#idHakmilik').val();
        var showPP = $('#showPP').val();
        var showHP = $('#showHP').val();
        window.open("${pageContext.request.contextPath}/pengambilan/penerimaan_notis_borang_BantahanMLK?popupPenghantarNotis&idHakmilik="+idHakmilik+'&showPP='+showPP+'&showHP='+showHP, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=900,height=400");
    }

    function addPenghantarNotisM(){
        window.open("${pageContext.request.contextPath}/pengambilan/penerimaan_notis_borang_BantahanMLK?popupPenghantarNotis&isMahkamah=true", "eTanah","status=0,toolbar=0,location=0,menubar=0,width=900,height=400");
    }

    function scan(idPihak) {
        var idHakmilik = $('#idHakmilik').val();
        var showPP = $('#showPP').val();
        var showHP = $('#showHP').val();
        var url = '${pageContext.request.contextPath}/pengambilan/penerimaan_notis_borang_BantahanMLK?popupScan&idPihak='+idPihak+'&idHakmilik='+idHakmilik;
        window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=800,height=700");
    }


    function refreshPagePenerimaanBorang(){
        var url = '${pageContext.request.contextPath}/pengambilan/penerimaan_notis_borang_BantahanMLK?refreshpage';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function notis(h){
        var url = '${pageContext.request.contextPath}/pengambilan/penerimaan_notis_borang_BantahanMLK?notisPopup&idPihak='+h;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
    }

    function ajaxLink(link, update) {
        $.get(link, function(data) {
            $(update).html(data);
            $(update).show();
        });
        return false;
    }

    $(document).ready(function() {
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    });


    function refreshNotis(idHakmilik,showPP,showHP,isMahkamah){
        if(showHP == 'true'){
            if(idHakmilik !=''){
                var url = '${pageContext.request.contextPath}/pengambilan/penerimaan_notis_borang_BantahanMLK?hakmilikDetails&idHakmilik='+idHakmilik+"&showPP="+showPP;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
            }else{
                if(showPP == 'true'){
                    var url = '${pageContext.request.contextPath}/pengambilan/penerimaan_notis_borang_BantahanMLK?showFormPP';
                    $.get(url,
                    function(data){
                        $('#page_div').html(data);
                    },'html');
                }

            }

        }else if(showHP == 'false' && showPP == 'true'){
            var url = '${pageContext.request.contextPath}/pengambilan/penerimaan_notis_borang_BantahanMLK?showFormPemohon';
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }else if(isMahkamah == 'true'){
            var url = '${pageContext.request.contextPath}/pengambilan/penerimaan_notis_borang_BantahanMLK?showFormMahkamah';
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
    }

</script>
<STYLE>
    .red {background:red}
    .green {background:green}
    .yellow {background:yellow}
    .white {background:white}
</STYLE>

<s:form beanclass="etanah.view.stripes.pengambilan.PenerimaanNotisBorangBantahanMahkamah_MLKActionBean">
    <div  id="hakmilik_details">
        <s:messages/>
        <s:errors/>
        <s:hidden name="showPP" value="${actionBean.showPP}" id="showPP"/>
        <s:hidden name="showHP" value="${actionBean.showHP}" id="showHP"/>
        <s:hidden name="idHakmilik" value="${actionBean.hakmilik.idHakmilik}" id="idHakmilik"/>
        <fieldset class="aras1"><br/>
            <legend align="center">
                <b>PENERIMAAN NOTIS/BORANG</b>
            </legend><br/>
            <%--<c:if test="${actionBean.permohonanMahkamah ne null && actionBean.notisMahkamahNBO ne null && actionBean.notisMahkamahNBN ne null}">--%>
            <c:if test="${actionBean.permohonanMahkamah ne null && actionBean.notisMahkamahNBO ne null}">
                <div align="center" >
                    <display:table class="tablecloth" name="${actionBean.permohonanMahkamah}"
                                   cellpadding="0" cellspacing="0" requestURI="/pengambilan/penerimaan_notis_borang_BantahanMLK" id="line">
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column property="namaPenolongKananPendaftar" title="Nama"/>
                        <display:column title="Nama Penghantar Notis " >
                            <s:select name="namaPengahantarM" id="namaPengahantarM" style="width:150px;">
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${listUtil.senaraiPenghantarNotis}" label="nama" value="idPenghantarNotis"/>
                            </s:select>
                        </display:column>
                        <display:column title="Status Penyampaian" >
                            <s:select name="kodStatusTerimaM" id="kodStatusTerimaM" >
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodStatusTerima}" label="nama" value="kod"/>
                            </s:select>
                        </display:column>
                        <display:column title="Cara Penghantaran" >
                            <s:select name="kodPenghantaranM" id="kodPenghantaranM" >
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodCaraPenghantaran}" label="nama" value="kod"/>
                            </s:select>
                        </display:column>
                        <display:column title="Tarikh" >
                            <p>H : <s:text class="datepicker" name="tarikhHantarM" id="tarikhDihantarM" formatPattern="dd/MM/yyyy" style="width:90px;" /><br><font size="1" color="red"> (cth : hh / bb / tttt)</font></p>
                            <p>T : <s:text class="datepicker" name="tarikhTerimaM" id="tarikhTerimaM" formatPattern="dd/MM/yyyy" style="width:90px;" /><br><font size="1" color="red"> (cth : hh / bb / tttt)</font></p>
                        </display:column>
                        <display:column  title="Catatan" >
                            <s:textarea name="catatanPenerimaanM" id="catatanPenerimaanM" rows="3" cols="20" />
                        </display:column>
                        <display:column title="Tindakan">
                        <p align="center">
                            <c:if test="${actionBean.idDokumenM eq ''}">
                                <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png" height="30" width="30" alt="Muat Naik"
                                     onmouseover="this.style.cursor='pointer';" onclick="muatNaikFormMahkamah();return false;" title="Muat Naik Dokumen"/>
                                /
                                <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png" height="30" width="30" alt="Imbas"
                                     onmouseover="this.style.cursor='pointer';" onclick="scanMahkamah();return false;" title="Imbas Dokumen"/>
                            </c:if>
                            <c:if test="${actionBean.idDokumenM ne ''}">
                                <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png" height="30" width="30" alt="Muat Naik"
                                     onmouseover="this.style.cursor='pointer';" onclick="muatNaikFormMahkamah();return false;" title="Muat Naik Dokumen"/>
                                /
                                <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png" height="30" width="30" alt="Imbas"
                                     onmouseover="this.style.cursor='pointer';" onclick="scanMahkamah();return false;" title="Imbas Dokumen"/>
                                /
                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" height="30" width="30" alt="papar"
                                     onclick="doViewReport('${actionBean.idDokumenM}');" onmouseover="this.style.cursor='pointer';" title="Papar Dokumen"/>
                            </c:if>
                        </p>
                        </display:column>
                    </display:table><br /><br />
                </div>
            </c:if>
            <c:if test="${actionBean.showHP eq 'true'}">
            <p align="center">
                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="5" cellpadding="0" cellspacing="0"
                               requestURI="/pengambilan/penerimaan_notis_borang_BantahanMLK" id="line">
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column title="ID Hakmilik">
                        <s:link beanclass="etanah.view.stripes.pengambilan.PenerimaanNotisBorangBantahanMahkamah_MLKActionBean"
                                event="hakmilikDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                            <s:param name="showPP" value="${actionBean.showPP}"/>
                            <s:param name="showHP" value="${actionBean.showHP}"/>
                            <s:param name="idHakmilik" value="${line.hakmilik.idHakmilik}"/>${line.hakmilik.idHakmilik}
                        </s:link>
                    </display:column>
                    <display:column title="No Lot/No PT" >${line.hakmilik.lot.nama}&nbsp;${line.hakmilik.noLot}</display:column>
                    <%--<display:column title="Luas"><fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>--%>
                    <display:column title="Luas Keseluruhan" style="vertical-align:baseline"><fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}
                        <c:if test="${line.hakmilik.luas eq null}">Tiada</c:if>
                    </display:column>
                    <display:column title="Luas Diambil"><fmt:formatNumber pattern="#,##0.00" value="${line.luasTerlibat}"/>&nbsp;${line.kodUnitLuas.nama}
                        <c:if test="${line.luasTerlibat eq null}">Tiada</c:if>
                    </display:column>
                    <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                    <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                </display:table>
            </p>
            </c:if>

            <br/>
            <br/>
            <c:if test="${actionBean.showPP eq true && ((fn:length(actionBean.listNotisPemohonASB) > 0) ||(fn:length(actionBean.listNotisPemohonPB) > 0))}">
                <div align="center" id="pemohonList">
                    <display:table class="tablecloth" name="${actionBean.permohonan.senaraiPemohon}"
                                   cellpadding="0" cellspacing="0" requestURI="/pengambilan/penerimaan_notis_borang_BantahanMLK" id="line">
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column property="pihak.nama" title="Nama"/>
                        <display:column title="Nama Penghantar Notis " >
                            <s:select name="namaPengahantarP[${line_rowNum-1}]" id="namaPengahantarP${line_rowNum-1}" style="width:150px;">
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${listUtil.senaraiPenghantarNotis}" label="nama" value="idPenghantarNotis"/>
                            </s:select>
                        </display:column>
                        <display:column title="Status Penyampaian" >
                            <s:select name="kodStatusTerimaP[${line_rowNum-1}]" id="kodStatusTerimaP{line_rowNum-1}" >
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodStatusTerima}" label="nama" value="kod"/>
                            </s:select>
                        </display:column>
                        <display:column title="Cara Penghantaran" >
                            <s:select name="kodPenghantaranP[${line_rowNum-1}]" id="kodPenghantaranP${line_rowNum-1}" >
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodCaraPenghantaran}" label="nama" value="kod"/>
                            </s:select>
                        </display:column>
                        <display:column title="Tarikh" >
                            <p>H : <s:text class="datepicker" name="tarikhHantarP[${line_rowNum-1}]" id="tarikhDihantarP${line_rowNum-1}" formatPattern="dd/MM/yyyy" style="width:90px;" /><br><font size="1" color="red"> (cth : hh / bb / tttt)</font></p>
                            <p>T : <s:text class="datepicker" name="tarikhTerimaP[${line_rowNum-1}]" id="tarikhTerimaP${line_rowNum-1}" formatPattern="dd/MM/yyyy" style="width:90px;" /><br><font size="1" color="red"> (cth : hh / bb / tttt)</font></p>
                        </display:column>
                        <display:column  title="Catatan" >
                            <s:textarea name="catatanPenerimaanP[${line_rowNum-1}]" id="catatanPenerimaanP${line_rowNum-1}" rows="3" cols="20" />
                        </display:column>
                        <display:column title="Tindakan">
                        <p align="center">
                            <c:if test="${actionBean.idDokumenListP[line_rowNum-1] eq ''}">
                                <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png" height="30" width="30" alt="Muat Naik"
                                     onmouseover="this.style.cursor='pointer';" onclick="muatNaikFormPemohon('${line.pihak.idPihak}');return false;" title="Muat Naik Dokumen"/>
                                /
                                <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png" height="30" width="30" alt="Imbas"
                                     onmouseover="this.style.cursor='pointer';" onclick="scanPemohon('${line.pihak.idPihak}');return false;" title="Imbas Dokumen"/>
                            </c:if>
                            <c:if test="${actionBean.idDokumenListP[line_rowNum-1] ne ''}">
                                <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png" height="30" width="30" alt="Muat Naik"
                                     onmouseover="this.style.cursor='pointer';" onclick="muatNaikFormPemohon('${line.pihak.idPihak}');return false;" title="Muat Naik Dokumen"/>
                                /
                                <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png" height="30" width="30" alt="Imbas"
                                     onmouseover="this.style.cursor='pointer';" onclick="scanPemohon('${line.pihak.idPihak}');return false;" title="Imbas Dokumen"/>
                                /
                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" height="30" width="30" alt="papar"
                                     onclick="doViewReport('${actionBean.idDokumenListP[line_rowNum-1]}');" onmouseover="this.style.cursor='pointer';" title="Papar Dokumen"/>
                            </c:if>
                        </p>
                        </display:column>
                    </display:table><br /><br />
                </div>
            </c:if>
            <br/>
            <div  align="center">
                <c:if test="${actionBean.hakmilik ne null}">
                    <s:errors/>
                    <c:if test="${actionBean.show eq true }">

                        <div align="center">
                            <display:table class="tablecloth" name="${actionBean.hakmilik.senaraiPihakBerkepentingan}"
                                           cellpadding="0" cellspacing="0" requestURI="/pengambilan/penerimaan_notis_borang_BantahanMLK" id="line">
                                <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                                <display:column property="pihak.nama" title="Nama"/>
                                <display:column title="Nama Penghantar Notis " >
                                    <s:select name="namaPengahantar[${line_rowNum-1}]" id="namaPengahantar${line_rowNum-1}" style="width:150px;">
                                        <s:option value="">--Sila Pilih--</s:option>
                                        <s:options-collection collection="${listUtil.senaraiPenghantarNotis}" label="nama" value="idPenghantarNotis"/>
                                    </s:select>
                                        <%--&nbsp;
                                        <img src="${pageContext.request.contextPath}/pub/images/tambah.png" height="15" width="15" alt="Tambah Penghantar Notis"
                                             onmouseover="this.style.cursor='pointer';" onclick="addPenghantarNotis('${actionBean.hakmilik.idHakmilik}');return false;" title="Tambah Penghantar Notis"/>--%>
                                </display:column>
                                <display:column title="Status Penyampaian" >
                                    <s:select name="kodStatusTerima[${line_rowNum-1}]" id="kodStatusTerima{line_rowNum-1}" >
                                        <s:option value="">--Sila Pilih--</s:option>
                                        <s:options-collection collection="${listUtil.senaraiKodStatusTerima}" label="nama" value="kod"/>
                                    </s:select>
                                </display:column>
                                <display:column title="Cara Penghantaran" >
                                    <s:select name="kodPenghantaran[${line_rowNum-1}]" id="kodPenghantaran${line_rowNum-1}" >
                                        <s:option value="">--Sila Pilih--</s:option>
                                        <s:options-collection collection="${listUtil.senaraiKodCaraPenghantaran}" label="nama" value="kod"/>
                                    </s:select>
                                </display:column>
                                <display:column title="Tarikh" >
                                    <p>H : <s:text class="datepicker" name="tarikhHantar[${line_rowNum-1}]" id="tarikhDihantar${line_rowNum-1}" formatPattern="dd/MM/yyyy" style="width:90px;" /><br><font size="1" color="red"> (cth : hh / bb / tttt)</font></p>
                                    <p>T : <s:text class="datepicker" name="tarikhTerima[${line_rowNum-1}]" id="tarikhTerima${line_rowNum-1}" formatPattern="dd/MM/yyyy" style="width:90px;" /><br><font size="1" color="red"> (cth : hh / bb / tttt)</font></p>
                                </display:column>
                                <display:column  title="Catatan" >
                                    <s:textarea name="catatanPenerimaan[${line_rowNum-1}]" id="catatanPenerimaan${line_rowNum-1}" rows="3" cols="20" />
                                </display:column>
                                <display:column title="Tindakan">
                                <p align="center">
                                    <c:if test="${actionBean.idDokumenList[line_rowNum-1] eq ''}">
                                        <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png" height="30" width="30" alt="Muat Naik"
                                             onmouseover="this.style.cursor='pointer';" onclick="muatNaikForm('${line.pihak.idPihak}');return false;" title="Muat Naik Dokumen"/>
                                        /
                                        <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png" height="30" width="30" alt="Imbas"
                                             onmouseover="this.style.cursor='pointer';" onclick="scan('${line.pihak.idPihak}');return false;" title="Imbas Dokumen"/>
                                    </c:if>
                                    <c:if test="${actionBean.idDokumenList[line_rowNum-1] ne ''}">
                                        <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png" height="30" width="30" alt="Muat Naik"
                                             onmouseover="this.style.cursor='pointer';" onclick="muatNaikForm('${line.pihak.idPihak}');return false;" title="Muat Naik Dokumen"/>
                                        /
                                        <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png" height="30" width="30" alt="Imbas"
                                             onmouseover="this.style.cursor='pointer';" onclick="scan('${line.pihak.idPihak}');return false;" title="Imbas Dokumen"/>
                                        /
                                        <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" height="30" width="30" alt="papar"
                                             onclick="doViewReport('${actionBean.idDokumenList[line_rowNum-1]}');" onmouseover="this.style.cursor='pointer';" title="Papar Dokumen"/>
                                    </c:if>
                                </p>
                                </display:column>
                        </display:table><br /><br />
                        </div>
                        <%--<p align="right">
                            <s:button class="btn"  name="simpan" value="Simpan" onclick="if(validate())doSubmit(this.form, this.name,'page_div')"/>
                        </p>--%>
                    </c:if>
                </c:if>
            </div>
            <c:if test="${(actionBean.hakmilik ne null && actionBean.show eq true ) || (actionBean.showPP eq true && actionBean.show eq true )}">
                <div align="center">
                    <s:button class="longbtn"  style="font-size:10px" name="Tambah Penghantar Notis" value="Tambah Penghantar Notis" onclick="addPenghantarNotis();return false;"/>
                    <s:button class="btn"  name="simpan" value="Simpan" onclick="if(validate())doSubmit(this.form, this.name,'page_div')"/>
                </div>
            </c:if>
            <%--<c:if test="${actionBean.permohonanMahkamah ne null && actionBean.notisMahkamahNBO ne null && actionBean.notisMahkamahNBN ne null}">--%>
            <c:if test="${actionBean.permohonanMahkamah ne null && actionBean.notisMahkamahNBO ne null }">
                <div align="center">
                    <s:button class="longbtn"  style="font-size:10px" name="Tambah Penghantar Notis" value="Tambah Penghantar Notis" onclick="addPenghantarNotisM();return false;"/>
                    <s:button class="btn"  name="simpanMahkamah" value="Simpan" onclick="if(validateMahkamah())doSubmit(this.form, this.name,'page_div')"/>
                </div>
            </c:if>
        </fieldset>
    </div>
</s:form>
