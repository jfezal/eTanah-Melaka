<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    $(document).ready(function() {
        if($('#adaBangunan').val() == 'Y'){
            $('#bilanganBangunan').show();
        }
        if($('#namaSempadanLaut').val() == 'Y'){
            $('#bilanganTanaman').show();
        }
        var v = '${actionBean.laporanTanah.kecerunanTanah.kod}';
        if(v == 'RT'){
            $('#tinggi').hide();
            $('#cerun').hide();
            $('#dalam').hide();
        }
        else if(v == 'BK'){
            $('#tinggi').hide();
            $('#cerun').show();
            $('#dalam').hide();
        }
        else if(v == 'TG'){
            $('#tinggi').show();
            $('#cerun').hide();
            $('#dalam').hide();
        }
        else if(v == 'RD'){
            $('#tinggi').hide();
            $('#cerun').show();
            $('#dalam').hide();
        }
        else if(v == 'CR'){
            $('#tinggi').hide();
            $('#cerun').show();
            $('#dalam').hide();
        }
        else if(v == 'PY'){
            $('#tinggi').hide();
            $('#cerun').hide();
            $('#dalam').show();
        }
        $('#jenisRizab').hide();
        $('#noWarta').hide();
        $('#nyataRancangan').hide();
        $('#tanahMilik').hide();
        $('#tujuan').hide();
        $('#catatanTanahMilik').hide();
        $('#catatanPBT').hide();
        $('#page_effect').fadeIn(2000);
        $('#page_effect2').fadeIn(4000);
    });
    function changeRancangan(value){
        if(value == "ada")
            $('#nyataRancangan').show();
        else
            $('#nyataRancangan').hide();
    }
    function changePBT(value){
        if(value == "dalam")
            $('#catatanPBT').show();
        else
            $('#catatanPBT').hide();
    }
    function changeTanahMilik(value){
        if(value == "ya"){
            $('#tanahMilik').show();
            $('#catatanTanahMilik').show();
        }
        else{
            $('#tanahMilik').hide();
            $('#catatanTanahMilik').hide();
        }
    }
    function changeKategoriTanah(value){
        if(value == "kerajaan"){
            $('#jenisRizab').hide();
            $('#noWarta').hide();
        }
        else if(value == "milik"){
            $('#jenisRizab').hide();
            $('#noWarta').hide();
        }
        else if(value == "rizab"){
            $('#jenisRizab').show();
            $('#noWarta').show();
        }
    }
    function changeTujuan(value){
        if(value == "Lain-lain")
            $('#tujuan').show();
        else
            $('#tujuan').hide();
    }
    function changeKeadaanTanah(text){
        if(text == "Rata"){
            $('#tinggi').hide();
            $('#cerun').hide();
            $('#dalam').hide();
        }
        else if(text == "Berbukit"){
            $('#tinggi').hide();
            $('#cerun').show();
            $('#dalam').hide();
        }
        else if(text == "Tinggi"){
            $('#tinggi').show();
            $('#cerun').hide();
            $('#dalam').hide();
        }
        else if(text == "Rendah"){
            $('#tinggi').hide();
            $('#cerun').show();
            $('#dalam').hide();
        }
        else if(text == "Curam"){
            $('#tinggi').hide();
            $('#cerun').show();
            $('#dalam').hide();
        }
        else if(text == "Berpaya"){
            $('#tinggi').hide();
            $('#cerun').hide();
            $('#dalam').show();
        }
    }
    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }
    function removeNonNumeric( strString ){
        var strValidCharacters = "1234567890";
        var strReturn = "";
        var strBuffer = "";
        var intIndex = 0;
        // Loop through the string
        for( intIndex = 0; intIndex < strString.length; intIndex++ )
        {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                strReturn += strBuffer;
            }
        }
        return strReturn;
    }
    function popup(i){
        var d = $('.x'+i).val();
        window.open("${pageContext.request.contextPath}/pembangunan/ns/laporanTanah?showeditTanahRizab&idTanahRizabPermohonan="+d, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
    }
    function refreshRizab(){
        var url = '${pageContext.request.contextPath}/pembangunan/ns/laporanTanah?refreshpage';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }
    function removeSingle(idTanahRizabPermohonan){
        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pembangunan/ns/laporanTanah?deleteSingle&idTanahRizabPermohonan='
                +idTanahRizabPermohonan;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');}
    }
    function tambahBaru(){
        window.open("${pageContext.request.contextPath}/pembangunan/ns/laporanTanah?hakMilikPopup", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
    }
    function showBilBangunan(value) {
        if(value == "Y")
        {
            $('#bilanganBangunan').show();
        }
        else if(value == "T")
        {
            $('#bilanganBangunan').hide();
        }
    }
    function showBilTanaman(value) {
        if(value == "Y")
        {
            $('#bilanganTanaman').show();
        }
        else if(value == "T")
        {
            $('#bilanganTanaman').hide();
        }
    }
    function showUlasan(value) {
        if(value == "1")
        {
            $('#perakuanSyor').show();
            $('#perakuanSyorUlasan').hide();
        }
        else if(value == "0")
        {
            $('#perakuanSyor').hide();
            $('#perakuanSyorUlasan').show();
        }
    }
    function hideBilBangunan() {
        $('#bilanganBangunan').hide();
        $('#bilanganBangunan2').hide();
    }
    function checkPelan(f){
        var noLot = f
        var kodDaerah = $('#kodDaerah').val();
        var kodBPM = $('#kodBPM').val();
        var kodNegeri = $('#kodNegeri').val();
        $.post('${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?checkPelan&noLot='+noLot+'&kodDaerah='+kodDaerah+'&kodNegeri='+kodNegeri+'&kodBPM='+kodBPM,
        function(data){
            if(data != '1'){
                alert('Pelan untuk no lot '+ noLot +' tiada');
                $("#noLot").val("");
                $("#noLot").focus();
            }
        }, 'html');
    }
    function addImage(idHakmilik,rowNo) {
        var idDokumen = document.getElementById("imej_"+rowNo).options[document.getElementById("imej_"+rowNo).selectedIndex].value;
        $("#imej"+rowNo).val(idDokumen);
        $.post('${pageContext.request.contextPath}/pembangunan/ns/laporanTanah?addHakmilikImage&idDokumen='+idDokumen+'&idHakmilik='+idHakmilik,
        function(data){
            $('#page_div').html(data);
        }, 'html');
    }
    function addImageUtara(idHakmilik){
        var idDokumen = document.getElementById("utaraImej").options[document.getElementById("utaraImej").selectedIndex].value;
        $.post('${pageContext.request.contextPath}/pembangunan/ns/laporanTanah?addLaporanImage&pandanganImej=U&idDokumen='+idDokumen+'&idHakmilik='+idHakmilik,
        function(data){
            $('#page_div').html(data);
        }, 'html');
    }
    function addImageSelatan(idHakmilik){
        var idDokumen = document.getElementById("selatanImej").options[document.getElementById("selatanImej").selectedIndex].value;
        $.post('${pageContext.request.contextPath}/pembangunan/ns/laporanTanah?addLaporanImage&pandanganImej=S&idDokumen='+idDokumen+'&idHakmilik='+idHakmilik,
        function(data){
            $('#page_div').html(data);
        }, 'html');
    }
    function addImageTimur(idHakmilik){
        var idDokumen = document.getElementById("timurImej").options[document.getElementById("timurImej").selectedIndex].value;
        $.post('${pageContext.request.contextPath}/pembangunan/ns/laporanTanah?addLaporanImage&pandanganImej=T&idDokumen='+idDokumen+'&idHakmilik='+idHakmilik,
        function(data){
            $('#page_div').html(data);
        }, 'html');
    }
    function addImageBarat(idHakmilik){
        var idDokumen = document.getElementById("baratImej").options[document.getElementById("baratImej").selectedIndex].value;
        $.post('${pageContext.request.contextPath}/pembangunan/ns/laporanTanah?addLaporanImage&pandanganImej=B&idDokumen='+idDokumen+'&idHakmilik='+idHakmilik,
        function(data){
            $('#page_div').html(data);
        }, 'html');
    }
    function doViewReport(rowNo) {
        var idDokumen = document.getElementById("imej_"+rowNo).options[document.getElementById("imej_"+rowNo).selectedIndex].value;
        var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }
    function doViewReportUtara() {
        var idDokumen = document.getElementById("utaraImej").options[document.getElementById("utaraImej").selectedIndex].value;
        var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }
    function doViewReportSelatan() {
        var idDokumen = document.getElementById("selatanImej").options[document.getElementById("selatanImej").selectedIndex].value;
        var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }
    function doViewReportTimur() {
        var idDokumen = document.getElementById("timurImej").options[document.getElementById("timurImej").selectedIndex].value;
        var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }
    function doViewReportBarat() {
        var idDokumen = document.getElementById("baratImej").options[document.getElementById("baratImej").selectedIndex].value;
        var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }
    function doView(idDokumen) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }
    function restoreOld(sObj){
        alert("Boleh " + sObj);
        sObj.selectedIndex=document.getElementById("s1_old").value;
    }
    function tambahBangunan(TB,f){
        var id = '${actionBean.permohonan.idPermohonan}';
        var q = $(f).formSerialize();
        var url = f.action + '?simpanLaporanTanah';
        $.post(url,q,
        function(data){
            $('#page_div',document).html(data);
            // self.close();
        },'html');
        window.open("${pageContext.request.contextPath}/pembangunan/ns/laporanTanah?tambahBangunanPopup&idPermohonan="+id+"&TB="+TB, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=350");
    }
    function tambahTanaman(){
        var id = '${actionBean.permohonan.idPermohonan}';
        window.open("${pageContext.request.contextPath}/pembangunan/ns/laporanTanah?tambahBangunanPopup&idPermohonan="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=250");
    }
    function rekodUlasan(f){
        var id = '${actionBean.permohonan.idPermohonan}';
        var q = $(f).formSerialize();
        var url = f.action + '?simpanLaporanTanah';
        $.post(url,q,
        function(data){
            $('#page_div',document).html(data);
            //            self.close();
        },'html');
        window.open("${pageContext.request.contextPath}/pembangunan/ns/laporanTanah?rekodUlasanPopup&idPermohonan="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=200");
    }
    function editLaporanBangunan(idLaporBangunan,TB){
        var idHakmilik = '${actionBean.hakmilik.idHakmilik}';
        window.open("${pageContext.request.contextPath}/pembangunan/ns/laporanTanah?editBangunanPopup&idLaporBangunan="+idLaporBangunan+"&TB="+TB, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=300");
    }
    function removeCerunTanah(idLaporCerun)
    {
        var url = '${pageContext.request.contextPath}/pembangunan/ns/laporanTanah?deleteLaporCerun&idLaporCerun='
            +idLaporCerun;
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }
    function addImageBngn(idHakmilik,idLapor){
        var idDokumen = document.getElementById("ImageBngn").options[document.getElementById("ImageBngn").selectedIndex].value;
        $.post('${pageContext.request.contextPath}/pembangunan/ns/laporanTanah?addLaporanImage&idDokumen='+idDokumen+'&idHakmilik='+idHakmilik,
        function(data){
            $('#page_div').html(data);
        }, 'html');
    }
    function showSekHakmilik() {
        $('#sekatanHakmilik').show();
    }
    function hideSekHakmilik() {
        $('#sekatanHakmilik').hide();
    }
    function save1(event, f){
        if(validation()){
        }
        else{
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
                $('#page_div',opener.document).html(data);
                self.close();
            },'html');
        }
    }
    function simpanCeruntanah(f){
        var q = $(f).formSerialize();
        var url = f.action + '?simpanLaporanTanah';
        $.post(url,q,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function simpanLT(f){
        
        //        var tanaman = '${actionBean.laporanTanah.namaSempadanLaut}';
        //        var bangunan = '${actionBean.laporanTanah.adaBangunan}';
        //
        if($("#tanaman").val() == ""){
            alert('Sila Pilih Status Tanaman terlebih dahulu.');
            $("#tanaman").focus();
            return true;
        }
        if($("#bangunan").val() == ""){
            alert('Sila Pilih Status Bangunan terlebih dahulu.');
            $("#bangunan").focus();
            return true;
        }
        //        if(tanaman==''){
        //            alert('Sila Pilih Status Tanaman.');
        //        }
        //        if(bangunan==''){
        //            alert('Sila Pilih Status Bangunan.');
        //        }
        if($("#bangunan").val() != "" && $("#tanaman").val() != ""){
            //            alert('Boleh disimpan');
            var q = $(f).formSerialize();
            var url = f.action + '?simpanLaporanTanah';
            $.post(url,q,
            function(data){
                $('#page_div').html(data);
            },'html');
        }

    }
</script>
<s:form beanclass="etanah.view.stripes.pembangunan.LaporanTanahNSActionBean">
    <s:messages/>
    <s:hidden name="permohonan.idPermohonan" />
    <div class="subtitle" id="page_effect">
        <fieldset class="aras1">
            <legend>Laporan Tanah</legend>
            <c:set scope="request" var="senaraiPBT"  value="${actionBean.pihakKepentinganList}"/>
            <p>
                <label>Maksud Permohonan :</label>
                ${actionBean.permohonan.sebab}&nbsp;
            </p>
            <p>
                <label>Tarikh Permohonan Diterima :</label>
                <c:if test="${actionBean.permohonan.infoAudit.tarikhMasuk ne null}"><s:format formatPattern="dd/MM/yyyy" value="${actionBean.permohonan.infoAudit.tarikhMasuk}"/>&nbsp;</c:if>
                <c:if test="${actionBean.permohonan.infoAudit.tarikhMasuk eq null}"> Tiada Data </c:if>
            </p>
            <p>
                <label>Keluasan :</label>
                <fmt:formatNumber  pattern="#,##0.0000" value="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.luas}"/>
                ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodUnitLuas.nama}&nbsp;
            </p>
            <br>
        </fieldset>
    </div>
    <div class="subtitle" id="page_effect2">
        <fieldset class="aras1">
            <legend>Perihal Tanah</legend>
            <c:if test="${actionBean.stageId eq 'laporantanah'}">
                <p>
                    <label>Senarai Pihak Berkepentingan :&nbsp;&nbsp </label>
                    &nbsp;&nbsp;
                </p>
                <div>
                    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" id="line" >
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column title="No. Hakmilik" >${line.hakmilik.kodHakmilik.kod}${line.hakmilik.noHakmilik}</display:column>
                        <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                        <display:column title="Tuan Tanah">
                            <table>
                                <tr>
                                    <th>&nbsp;</th><th>Syer</th>
                                </tr>
                                <c:set value="1" var="count"/>
                                <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                                    <c:if test="${senarai.jenis.kod eq 'PM'}">
                                        <tr>
                                            <td>
                                                <c:out value="${count}"/>)&nbsp;
                                                <c:out value="${senarai.pihak.nama}"/>&nbsp;&nbsp;<br>
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;No.KP/Co :&nbsp;<c:out value="${senarai.pihak.noPengenalan}"/>

                                            </td>
                                            <td><c:out value="${senarai.syerPembilang}/${senarai.syerPenyebut}"/>
                                            </td>
                                        </tr>
                                        <c:set value="${count + 1}" var="count"/>
                                    </c:if>
                                </c:forEach>
                            </table>
                            <%-- <c:set value="1" var="count"/>
                             <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                                 <br>
                                 <c:if test="${senarai.jenis.kod eq 'PM'}">
                                     <c:out value="${count}"/>)&nbsp;
                                     <c:out value="${senarai.pihak.nama}"/>&nbsp;&nbsp;<br>
                                     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;No.KP/Co :&nbsp;<c:out value="${senarai.pihak.noPengenalan}"/>
                                     <c:out value="${senarai.syerPembilang}/${senarai.syerPenyebut}"/>
                                     <c:set value="${count + 1}" var="count"/>
                                 </c:if>
                             </c:forEach>--%>
                        </display:column>
                        <%--  <display:column title="Syer yang dimiliki">
                              <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                                  <c:if test="${senarai.jenis.kod eq 'PM'}">
                                      <c:out value="${senarai.syerPembilang}/${senarai.syerPenyebut}"/><br>
                                  </c:if>
                              </c:forEach>
                          </display:column>--%>
                        <display:column title="Pihak Berkepentingan">
                            <c:set value="1" var="count"/>
                            <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                                <c:if test="${senarai.jenis.kod ne 'PM'}">
                                    <br>
                                    <c:out value="${count}"/>)&nbsp;
                                    <c:out value="${senarai.pihak.nama}"/>&nbsp;&nbsp;
                                    <c:set value="${count + 1}" var="count"/><br>
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;No.KP/Co:<c:out value="${senarai.pihak.noPengenalan}"/>
                                </c:if>
                            </c:forEach>
                        </display:column>
                        <display:column title="Jenis Pihak Berkepentingan">
                            <c:set value="1" var="count"/>
                            <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                                <c:if test="${senarai.jenis.kod ne 'PM'}">
                                    <c:out value="${count}"/>)&nbsp;
                                    <c:out value="${senarai.jenis.nama}"/><br>
                                    <c:set value="${count + 1}" var="count"/><br>
                                </c:if>
                            </c:forEach>
                        </display:column>
                        <display:column title="Tarikh Pemilikan Didaftar">
                            <fmt:formatDate pattern="dd/MM/yyyy" value="${line.infoAudit.tarikhMasuk}"/>
                        </display:column>
                        <display:column title="Hasil (RM)">RM <fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.cukaiSebenar}"/></display:column>
                        <%--<display:column title="Imej Di Atas Tanah">
                            <c:forEach items="${actionBean.imejLaporanList}" var="senarai">
                                <c:if test="${senarai.hakmilik.idHakmilik eq line.hakmilik.idHakmilik}">
                                    <a href="javascript:doView('${senarai.dokumen.idDokumen}');" >
                                        <c:out value="${senarai.dokumen.tajuk}" />
                                    </a><br/>
                                </c:if>
                            </c:forEach>
                            <s:select name="imej[${line_rowNum-1}]" id="imej_${line_rowNum}" disabled="${actionBean.disabled}">
                                <s:option value="">Sila pilih imej</s:option>
                                <s:options-collection collection="${actionBean.dokumenList}" label="tajuk" value="idDokumen"/>
                            </s:select>
                            <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                 onclick="doViewReport('${line_rowNum}');" height="30" width="30" alt="papar"
                                 onmouseover="this.style.cursor='pointer';" title="Papar Dokumen"/>
                            <s:button class="btn" value="Tambah" name="add_${line_rowNum}" id="add_${line_rowNum}" onclick="addImage('${line.hakmilik.idHakmilik}','${line_rowNum}');" disabled="${actionBean.disabled}"/>
                        </display:column>--%>
                    </display:table>
                </div>
                <br>
                <p>
                <div class="content" align="center">
                    <table border="0" width="100%">
                        <tr>
                            <td><label>Nombor Warta Kerajaan</label></td>
                            <td>:</td>
                            <td><s:text name="permohonanRujukanLuar.noRujukan" disabled="${actionBean.disabled}"/><br></td>
                        </tr>
                        <tr>
                            <td colspan="3"></td>
                        </tr>
                        <tr>
                            <td><label>Jenis Tanah</label></td>
                            <td>:</td>
                            <td> <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodTanah.nama ne null}"> ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodTanah.nama}&nbsp; </c:if>
                                <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodTanah.nama eq null}"> Tiada Data </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3"></td>
                        </tr>
                        <tr>
                            <td><label>Lokaliti</label></td>
                            <td>:</td>
                            <td><c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.lokasi ne null}"> ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.lokasi}&nbsp; </c:if>
                                <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.lokasi eq null}"> Tiada Data </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3"></td>
                        </tr>
                        <tr>
                            <td><label>Kawasan PBT</label></td>
                            <td>:</td>
                            <td><c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.pbt.nama ne null}"> ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.pbt.nama}&nbsp; </c:if>
                                <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.pbt.nama eq null}"> Tiada Data </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3"></td>
                        </tr>
                        <tr>
                            <td><label>Tandatangan</label></td>
                            <td>:</td>
                            <td>Semua pemilik <s:radio name="laporanTanah.dokDitandatangan" value="Y"/>&nbsp;<b>Telah</b><s:radio name="laporanTanah.dokDitandatangan" value="T"/>&nbsp;<b>Belum</b> menandatangani <s:radio name="laporanTanah.jenisDokDitandatangan" value="S"/>&nbsp;<b>Surat</b><s:radio name="laporanTanah.jenisDokDitandatangan" value="B"/>&nbsp;<b>Borang</b> permohonan untuk <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PYTN'}"> penyatuan tanah </c:if> <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSPTD' || actionBean.permohonan.kodUrusan.kod eq 'TSPTG' || actionBean.permohonan.kodUrusan.kod eq 'TSMMK'}"> menukar syarat </c:if> <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPCB' || actionBean.permohonan.kodUrusan.kod eq 'PPCS'}"> pecah bahagian/sempadan </c:if> <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'SBMS'}"> penyerahan dan pemberimilikan semula</c:if> <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSBSN'}"> tukar syarat kategori pembatalan syarat nyata </c:if> tersebut.
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3"></td>
                        </tr>
                        <tr>
                            <td><label>Pengesahan Kepentingan</label></td>
                            <td>:</td>
                            <td>Semua mereka yang ada kepentingan berdaftar <s:radio name="laporanTanah.pengesahanKepentingan" value="Y"/>&nbsp;<b>Telah</b><s:radio name="laporanTanah.pengesahanKepentingan" value="T"/>&nbsp;<b>Belum</b> memberi kebenaran bertulis untuk <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PYTN'}"> penyatuan tanah </c:if> <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSPTD' || actionBean.permohonan.kodUrusan.kod eq 'TSPTG' || actionBean.permohonan.kodUrusan.kod eq 'TSMMK'}"> menukar syarat </c:if> <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPCB' || actionBean.permohonan.kodUrusan.kod eq 'PPCS'}"> pecah bahagian/sempadan </c:if> <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'SBMS'}"> penyerahan dan pemberimilikan semula</c:if> <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSBSN'}"> tukar syarat kategori pembatalan syarat nyata </c:if> tersebut.
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3"></td>
                        </tr>
                        <tr>
                            <td><label>Sekatan Hakmilik (Jika Ada)</label></td>
                            <td>:</td>
                            <td><c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.sekatanKepentingan ne null}"> ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.sekatanKepentingan.sekatan}&nbsp; </c:if>
                                <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.sekatanKepentingan eq null}"> Tiada Data </c:if></td>
                        </tr>
                        <tr>
                            <td colspan="3"></td>
                        </tr>
                        <tr>
                            <td><label>Projek Kerajaan(Pengambilan)</label></td>
                            <td>:</td>
                            <td><s:radio name="laporanTanah.nilaiBangunan" value="0"/>&nbsp;<b>Ada</b><s:radio name="laporanTanah.nilaiBangunan" value="1"/>&nbsp;<b>Tidak</b> terlibat dengan projek Kerajaan (Pengambilan).Jika ada rujukan fail :
                            </td>
                        </tr>
                    </table></div>
                </p>
                <%-- <p>
                     <label>Nombor Warta Kerajaan :</label>
                     <s:text name="permohonanRujukanLuar.noRujukan" disabled="${actionBean.disabled}"/>
                 </p>
                 <p>
                     <label>Jenis Tanah :</label>
                     <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodTanah.nama ne null}"> ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodTanah.nama}&nbsp; </c:if>
                     <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodTanah.nama eq null}"> Tiada Data </c:if>
                 </p>
                 <p>
                     <label>Lokaliti :</label>
                     <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.lokasi ne null}"> ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.lokasi}&nbsp; </c:if>
                     <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.lokasi eq null}"> Tiada Data </c:if>
                 </p>
                 <p>
                     <label>Kawasan PBT :</label>
                     <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.pbt.nama ne null}"> ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.pbt.nama}&nbsp; </c:if>
                     <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.pbt.nama eq null}"> Tiada Data </c:if>
                 </p>
                 <br>
                 <p>
                     <label>Tandatangan : </label>
                     Semua pemilik <s:radio name="laporanTanah.dokDitandatangan" value="Y"/>&nbsp;<b>Telah</b><s:radio name="laporanTanah.dokDitandatangan" value="T"/>&nbsp;<b>Belum</b> menandatangani <s:radio name="laporanTanah.jenisDokDitandatangan" value="S"/>&nbsp;<b>Surat</b><s:radio name="laporanTanah.jenisDokDitandatangan" value="B"/>&nbsp;<b>Borang</b> permohonan untuk <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PYTN'}"> penyatuan tanah </c:if> <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSPTD' || actionBean.permohonan.kodUrusan.kod eq 'TSPTG' || actionBean.permohonan.kodUrusan.kod eq 'TSMMK'}"> menukar syarat </c:if> <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPCB' || actionBean.permohonan.kodUrusan.kod eq 'PPCS'}"> pecah bahagian/sempadan </c:if> <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'SBMS'}"> penyerahan dan pemberimilikan semula</c:if> <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSBSN'}"> tukar syarat kategori pembatalan syarat nyata </c:if> tersebut.
                 </p>
                 <br>
                 <p>
                     <label>Pengesahan Kepentingan : </label>
                     Semua mereka yang ada kepentingan berdaftar <s:radio name="laporanTanah.pengesahanKepentingan" value="Y"/>&nbsp;<b>Telah</b><s:radio name="laporanTanah.pengesahanKepentingan" value="T"/>&nbsp;<b>Belum</b> memberi kebenaran bertulis untuk <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PYTN'}"> penyatuan tanah </c:if> <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSPTD' || actionBean.permohonan.kodUrusan.kod eq 'TSPTG' || actionBean.permohonan.kodUrusan.kod eq 'TSMMK'}"> menukar syarat </c:if> <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPCB' || actionBean.permohonan.kodUrusan.kod eq 'PPCS'}"> pecah bahagian/sempadan </c:if> <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'SBMS'}"> penyerahan dan pemberimilikan semula</c:if> <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSBSN'}"> tukar syarat kategori pembatalan syarat nyata </c:if> tersebut.
                 </p>
                 <br>
                 <p>
                     <label>Sekatan Hakmilik (Jika Ada): </label>
                     <s:textarea name="1" readonly="readonly" rows="3" cols="40" ><c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.sekatanKepentingan ne null}"> ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.sekatanKepentingan.sekatan}&nbsp; </c:if>
                         <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.sekatanKepentingan eq null}"> Tiada Data </c:if>
                     </s:textarea>
                     <s:radio name="laporanTanah.jumlahNilai" value="0" onclick="showSekHakmilik();" disabled="${actionBean.disabled}"/>&nbsp;<b>Ada</b>
 <s:radio name="laporanTanah.jumlahNilai" value="1" onclick="hideSekHakmilik();" disabled="${actionBean.disabled}"/>&nbsp;<b>Tiada</b>
 <br>
 <div class="content" id="sekatanHakmilik">
 <label>Catatan :</label>
                <%--  <s:textarea name="laporanTanah.catatanSekatanHakmilik" id="sekatanHakmilikText" cols="50" rows="3"/>
              </div>
                <br>
            </p>
            <p>
                <label>Projek Kerajaan(Pengambilan) : </label>
                <s:radio name="laporanTanah.nilaiBangunan" value="0"/>&nbsp;<b>Ada</b><s:radio name="laporanTanah.nilaiBangunan" value="1"/>&nbsp;<b>Tidak</b> terlibat dengan projek Kerajaan (Pengambilan).Jika ada rujukan fail :
            </p>--%>
                <br>
            </c:if>
            <c:if test="${actionBean.stageId eq 'semaklaporantanah'}">
                <div>
                    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" id="line">
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column title="No. Hakmilik" >${line.hakmilik.kodHakmilik.kod}${line.hakmilik.noHakmilik}</display:column>
                        <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                        <display:column title="Tuan Tanah">
                            <c:set value="1" var="count"/>
                            <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                                <br>
                                <c:if test="${senarai.jenis.kod eq 'PM'}">
                                    <c:out value="${count}"/>)&nbsp;
                                    <c:out value="${senarai.pihak.nama}"/>&nbsp;&nbsp;<br>
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;No.KP/Co :&nbsp;<c:out value="${senarai.pihak.noPengenalan}"/>
                                    <c:set value="${count + 1}" var="count"/>
                                </c:if>
                            </c:forEach>
                        </display:column>
                        <display:column title="Syer yang dimiliki">
                            <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                                <c:if test="${senarai.jenis.kod eq 'PM'}">
                                    <c:out value="${senarai.syerPembilang}/${senarai.syerPenyebut}"/><br>
                                </c:if>
                            </c:forEach>
                        </display:column>
                        <display:column title="Pihak Berkepentingan">
                            <c:set value="1" var="count"/>
                            <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                                <c:if test="${senarai.jenis.kod ne 'PM'}">
                                    <br>
                                    <c:out value="${count}"/>)&nbsp;
                                    <c:out value="${senarai.pihak.nama}"/>&nbsp;&nbsp;
                                    <c:set value="${count + 1}" var="count"/><br>
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;No.KP/Co:<c:out value="${senarai.pihak.noPengenalan}"/>
                                </c:if>
                            </c:forEach>
                        </display:column>
                        <display:column title="Jenis Pihak Berkepentingan">
                            <c:set value="1" var="count"/>
                            <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                                <c:if test="${senarai.jenis.kod ne 'PM'}">
                                    <c:out value="${count}"/>)&nbsp;
                                    <c:out value="${senarai.jenis.nama}"/><br>
                                    <c:set value="${count + 1}" var="count"/><br>
                                </c:if>
                            </c:forEach>
                        </display:column>
                        <display:column title="Tarikh Pemilikan Didaftar">
                            <fmt:formatDate pattern="dd/MM/yyyy" value="${line.infoAudit.tarikhMasuk}"/>
                        </display:column>
                        <display:column title="Hasil (RM)">RM <fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.cukaiSebenar}"/></display:column>
                        <%-- <display:column title="Imej Di Atas Tanah">
                             <c:forEach items="${actionBean.imejLaporanList}" var="senarai">
                                 <c:if test="${senarai.hakmilik.idHakmilik eq line.hakmilik.idHakmilik}">
                                     <a href="javascript:doView('${senarai.dokumen.idDokumen}');" >
                                         <c:out value="${senarai.dokumen.tajuk}" />
                                     </a><br/>
                                 </c:if>
                             </c:forEach>
                         </display:column>--%>
                    </display:table>
                </div>
                <br>
                <p>
                    <label>Nombor Warta Kerajaan :</label>
                    <c:if test="${actionBean.permohonanRujukanLuar.noRujukan ne null}"> ${actionBean.permohonanRujukanLuar.noRujukan}&nbsp; </c:if>
                    <c:if test="${actionBean.permohonanRujukanLuar.noRujukan eq null}"> Tiada Data </c:if>
                </p>
                <p>
                    <label>Jenis Tanah :</label>
                    <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodTanah.nama ne null}"> ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodTanah.nama}&nbsp; </c:if>
                    <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodTanah.nama eq null}"> Tiada Data </c:if>
                </p>
                <p>
                    <label>Lokaliti :</label>
                    <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.lokasi ne null}"> ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.lokasi}&nbsp; </c:if>
                    <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.lokasi eq null}"> Tiada Data </c:if>
                </p>
                <p>
                    <label>Kawasan PBT :</label>
                    <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.pbt.nama ne null}"> ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.pbt.nama}&nbsp; </c:if>
                    <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.pbt.nama eq null}"> Tiada Data </c:if>
                </p>
                <br>
                <p>
                    <label>Tandatangan : </label><%--bilanganPemilik--%>
                    Semua pemilik <c:if test="${actionBean.laporanTanah.dokDitandatangan eq 'Y'}"><b>Telah</b></c:if><c:if test="${actionBean.laporanTanah.dokDitandatangan eq 'T'}"><b>Belum</b></c:if> menandatangani <c:if test="${actionBean.laporanTanah.jenisDokDitandatangan eq 'S'}"><b>Surat</b></c:if><c:if test="${actionBean.laporanTanah.jenisDokDitandatangan eq 'B'}"><b>Borang</b></c:if> permohonan untuk <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PYTN'}"> penyatuan tanah </c:if> <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSPTD' || actionBean.permohonan.kodUrusan.kod eq 'TSPTG' || actionBean.permohonan.kodUrusan.kod eq 'TSMMK'}"> menukar syarat </c:if> <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPCB' || actionBean.permohonan.kodUrusan.kod eq 'PPCS'}"> pecah bahagian/sempadan </c:if> <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'SBMS'}"> penyerahan dan pemberimilikan semula</c:if> <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSBSN'}"> tukar syarat kategori pembatalan syarat nyata </c:if> tersebut.
                </p>
                <p>
                    <label>Pengesahan Kepentingan : </label><%--jumlahKeluargaKenaPindah--%>
                    Semua mereka yang ada kepentingan berdaftar <c:if test="${actionBean.laporanTanah.pengesahanKepentingan eq 'Y'}"><b>Telah</b></c:if><c:if test="${actionBean.laporanTanah.pengesahanKepentingan eq 'T'}"><b>Belum</b></c:if> memberi kebenaran bertulis untuk <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PYTN'}"> penyatuan tanah </c:if> <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSPTD' || actionBean.permohonan.kodUrusan.kod eq 'TSPTG' || actionBean.permohonan.kodUrusan.kod eq 'TSMMK'}"> menukar syarat </c:if> <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPCB' || actionBean.permohonan.kodUrusan.kod eq 'PPCS'}"> pecah bahagian/sempadan </c:if> <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'SBMS'}"> penyerahan dan pemberimilikan semula</c:if> <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSBSN'}"> tukar syarat kategori pembatalan syarat nyata </c:if> tersebut.
                </p>
                <p>
                    <label>Sekatan Hakmilik (Jika Ada): </label>
                    <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.sekatanKepentingan ne null}"> ${actionBean.permohonan.senaraiHakmilik[0].sekatanKepentingan.sekatan}&nbsp; </c:if>
                    <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.sekatanKepentingan eq null}"> Tiada Data </c:if>
                    <%--<c:if test="${actionBean.laporanTanah.jumlahNilai eq '0'}"><b>Ada</b></c:if><c:if test="${actionBean.laporanTanah.jumlahNilai eq '1'}"><b>Tiada</b></c:if>--%>
                    <br>
                    <label>Catatan: </label>
                    <c:out value="${actionBean.laporanTanah.strukturTambahanJenis}"></c:out>
                    <br>
                </p>
                <p>
                    <label>Projek Kerajaan(Pengambilan) : </label>
                    <c:if test="${actionBean.laporanTanah.nilaiBangunan eq '0'}"><b>Ada</b></c:if><c:if test="${actionBean.laporanTanah.nilaiBangunan eq '1'}"><b>Tiada</b></c:if> terlibat dengan projek Kerajaan (Pengambilan).Jika ada rujukan fail :
                </p>
                <div  align="center" style="display: none" >
                    Bersempadan
                    <table  style="display: none">
                        <tr>
                            <th>Bersempadan</th><th>Nama</th><th width="120">Jarak (KM)</th>
                        </tr>
                        <tr>
                            <td>
                                Jalan Raya
                            </td>
                            <td>
                                <c:if test="${actionBean.laporanTanah.namaSempadanJalanraya ne null}"> ${actionBean.laporanTanah.namaSempadanJalanraya}&nbsp; </c:if>
                                <c:if test="${actionBean.laporanTanah.namaSempadanJalanraya eq null}"> Tiada Data </c:if>
                            </td>
                            <td>
                                <c:if test="${actionBean.laporanTanah.jarakSempadanJalanraya ne null}"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.laporanTanah.jarakSempadanJalanraya}"/></c:if>
                                <c:if test="${actionBean.laporanTanah.jarakSempadanJalanraya eq null}"> Tiada Data </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Landasan Keretapi
                            </td>
                            <td>
                                <c:if test="${actionBean.laporanTanah.namaSempadanKeretapi ne null}"> ${actionBean.laporanTanah.namaSempadanKeretapi}&nbsp; </c:if>
                                <c:if test="${actionBean.laporanTanah.namaSempadanKeretapi eq null}"> Tiada Data </c:if>
                            </td>
                            <td>
                                <c:if test="${actionBean.laporanTanah.jarakSempadanKeretapi ne null}">  <fmt:formatNumber pattern="#,##0.00" value="${actionBean.laporanTanah.jarakSempadanKeretapi}"/>&nbsp; </c:if>
                                <c:if test="${actionBean.laporanTanah.jarakSempadanKeretapi eq null}"> Tiada Data </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Laut
                            </td>
                            <td>
                                <c:if test="${actionBean.laporanTanah.namaSempadanLaut ne null}"> ${actionBean.laporanTanah.namaSempadanLaut}&nbsp; </c:if>
                                <c:if test="${actionBean.laporanTanah.namaSempadanLaut eq null}"> Tiada Data </c:if>
                            </td>
                            <td>
                                <c:if test="${actionBean.laporanTanah.jarakSempadanLaut ne null}"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.laporanTanah.jarakSempadanLaut}"/>&nbsp;</c:if>
                                <c:if test="${actionBean.laporanTanah.jarakSempadanLaut eq null}"> Tiada Data </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Sungai
                            </td>
                            <td>
                                <c:if test="${actionBean.laporanTanah.namaSempadanSungai ne null}"> ${actionBean.laporanTanah.namaSempadanSungai}&nbsp; </c:if>
                                <c:if test="${actionBean.laporanTanah.namaSempadanSungai eq null}"> Tiada Data </c:if>
                            </td>
                            <td>
                                <c:if test="${actionBean.laporanTanah.jarakSempadanSungai ne null}">   <fmt:formatNumber pattern="#,##0.00" value="${actionBean.laporanTanah.jarakSempadanSungai}"/>&nbsp; </c:if>
                                <c:if test="${actionBean.laporanTanah.jarakSempadanSungai eq null}"> Tiada Data </c:if>
                            </td>
                        </tr>
                    </table>
                </div>
            </c:if>
            <br>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <s:hidden name="laporanTanah.idLaporan"/>
            <s:hidden name="laporanTanah.permohonan.idPermohonan"/>
            <legend>Keadaan Tanah</legend><br>
            <c:if test="${actionBean.stageId eq 'laporantanah'}">
                <p>
                    <label>Keadaan Tanah :</label>
                <table border="0" class="tablecloth">
                    <tr style="display : none">
                        <th colspan="2">Keadaan Tanah</th>
                    </tr>
                    <c:forEach items="${actionBean.senaraiKodKecerunanTanah}" var="line">
                        <tr>
                            <td><s:checkbox name="${line.nama}" value="${line.kod}" id="cerun${line.kod}"
                                        /> </td><%-- onclick="simpanCeruntanah(this.form);"--%>
                            <td>${line.nama}</td>
                        </tr>
                    </c:forEach>
                    <c:forEach items="${actionBean.findListlaporCerun}" var="line3">
                        <tr>
                            <td>
                                <div align="center">
                                    <input type="checkbox" name="${line3_rowNum}" value="${line3_rowNum}" checked="checked" onclick="removeCerunTanah('${line3.idLaporCerun}');"/>
                                    <img style="display: none" alt='Klik Untuk Hapus' border='0'
                                         src='${pageContext.request.contextPath}/pub/images/not_ok.gif' class='rem'
                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';"
                                         onclick="removeCerunTanah('${line3.idLaporCerun}');"/>
                                </div>
                            </td>
                            <td>
                                ${line3.kodCerunanTanah.nama}
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                </p>
                <br><br>
                <p>
                    <label>Klasifikasi Tanah :</label>
                    <s:select name="strukturTanahString" disabled="${actionBean.disabled}">
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodStrukturTanah}" label="nama" value="kod" />
                    </s:select>
                </p>
                <br><br>
                <p>
                    <label>Dilintasi Oleh :</label>
                <table class="tablecloth">
                    <tr>
                        <td><s:checkbox name="laporanTanah.dilintasTiangElektrik" value="Y" /></td>
                        <td>Talian Elektrik</td>
                    </tr>
                    <tr>
                        <td><s:checkbox name="laporanTanah.dilintasTiangTelefon" value="Y" /></td>
                        <td>Talian Telefon</td>
                    </tr>
                    <tr>
                        <td><s:checkbox name="laporanTanah.dilintasLaluanGas" value="Y" /></td>
                        <td>Laluan Gas</td>
                    </tr>
                    <tr>
                        <td><s:checkbox name="laporanTanah.dilintasPaip" value="Y" /></td>
                        <td>Paip Air</td>
                    </tr>
                    <tr>
                        <td><s:checkbox name="laporanTanah.dilintasTaliar" value="Y" /></td>
                        <td>Tali Air</td>
                    </tr>
                    <tr>
                        <td><s:checkbox name="laporanTanah.dilintasSungai" value="Y" /></td>
                        <td>Sungai</td>
                    </tr>
                    <tr>
                        <td><s:checkbox name="laporanTanah.dilintasParit" value="Y" /></td>
                        <td>Parit</td>
                    </tr>
                    <tr>
                        <td>Lain-lain</td>
                        <td><s:textarea name="laporanTanah.dilintasiLain" class="normal_text" cols="50"/></td>
                    </tr>
                </table>
                <%--
                                    <s:checkbox name="laporanTanah.dilintasTiangElektrik" value="Y" />&nbsp; Talian Elektrik<br>
                                </p>
                                <p>
                                    <label>&nbsp;</label>
                                    <s:checkbox name="laporanTanah.dilintasTiangTelefon" value="Y" />&nbsp; Talian Telefon<br>
                                </p>
                                <p>
                                    <label>&nbsp;</label>
                                    <s:checkbox name="laporanTanah.dilintasLaluanGas" value="Y" />&nbsp; Laluan Gas<br>
                                </p>
                                <p>
                                    <label>&nbsp;</label>
                                    <s:checkbox name="laporanTanah.dilintasPaip" value="Y" />&nbsp; Paip Air<br>
                                </p>
                                <p>
                                    <label>&nbsp;</label>
                                    <s:checkbox name="laporanTanah.dilintasTaliar" value="Y" />&nbsp; Tali Air<br>
                                </p>
                                <p>
                                    <label>&nbsp;</label>
                                    <s:checkbox name="laporanTanah.dilintasSungai" value="Y" />&nbsp; Sungai<br>
                                </p>
                                <p>
                                    <label>&nbsp;</label>
                                    <s:checkbox name="laporanTanah.dilintasParit" value="Y" />&nbsp; Parit<br>
                                </p>
                                <p>
                                    <label>&nbsp;</label>
                                    &nbsp; Lain-lain : <br>
                                    <label>&nbsp;</label>
                                    <s:textarea name="laporanTanah.dilintasiLain" cols="50"/><br>
                                </p>--%>
                <br>
                <p>
                    <label>Kedudukan Tanah :</label>
                    <s:textarea name="laporanTanah.kedudukanTanah" cols="50" class="normal_text"/>
                </p>
                <br>
                <p>
                    <label>Jauh Dari Pekan/Bandar :</label>
                    <s:textarea name="laporanTanah.namaSempadanJalanraya" cols="50" class="normal_text" disabled="${actionBean.disabled}"/>
                </p>
                <br>
                <p>
                    <label>Mercu Tanda(LandMark) :</label>
                    <s:textarea name="laporanTanah.mercuTanda" cols="50" class="normal_text" disabled="${actionBean.disabled}"/>
                </p>
                <br>
                <p>
                    <label>Lot Tanah Ini Mempunyai Laluan Sah(Access Reserve) :</label>
                    <s:radio name="laporanTanah.adaJalanMasuk" value="Y" disabled="${actionBean.disabled}"/>&nbsp;Ada
                    <s:radio name="laporanTanah.adaJalanMasuk" value="T" disabled="${actionBean.disabled}"/>&nbsp;Tiada
                    <br></p><p>
                    <label>Catatan :</label>
                    <s:textarea name="laporanTanah.catatanJalanMasuk" class="normal_text" cols="50" disabled="${actionBean.disabled}"/>
                </p>
                <br>
            </c:if>
            <c:if test="${actionBean.stageId eq 'semaklaporantanah'}">
                <p>
                    <label>Keadaan Tanah :</label>
                    <c:forEach items="${actionBean.findListlaporCerun}" var="line">
                        ${line.kodCerunanTanah.nama}, 
                    </c:forEach>
                </p>
                </p>
                <p>
                    <label>Klasifikasi Tanah :</label>
                    <c:if test="${actionBean.laporanTanah.strukturTanah.nama ne null}"> ${actionBean.laporanTanah.strukturTanah.nama}&nbsp; </c:if>
                    <c:if test="${actionBean.laporanTanah.strukturTanah.nama eq null}"> Tiada Data </c:if>
                </p>
                <p>
                    <label>Dilintasi Oleh :</label>
                    <c:if test="${actionBean.laporanTanah.dilintasTiangElektrik ne null or
                                  actionBean.laporanTanah.dilintasTiangTelefon ne null or
                                  actionBean.laporanTanah.dilintasLaluanGas ne null or
                                  actionBean.laporanTanah.dilintasPaip ne null or
                                  actionBean.laporanTanah.dilintasTaliar ne null or
                                  actionBean.laporanTanah.dilintasSungai ne null or
                                  actionBean.laporanTanah.dilintasParit ne null or
                                  actionBean.laporanTanah.dilintasiLain ne null}">
                        <c:if test="${actionBean.laporanTanah.dilintasTiangElektrik eq 'Y'}"> Talian Elektrik ,</c:if>
                        <c:if test="${actionBean.laporanTanah.dilintasTiangTelefon eq 'Y'}">Talian Telefon ,</c:if>
                        <c:if test="${actionBean.laporanTanah.dilintasLaluanGas eq 'Y'}">Laluan Gas ,</c:if>
                        <c:if test="${actionBean.laporanTanah.dilintasPaip eq 'Y'}">Paip Air ,</c:if>
                        <c:if test="${actionBean.laporanTanah.dilintasTaliar eq 'Y'}">Tali Air ,</c:if>
                        <c:if test="${actionBean.laporanTanah.dilintasSungai eq 'Y'}">Sungai ,</c:if>
                        <c:if test="${actionBean.laporanTanah.dilintasParit eq 'Y'}">Parit ,</c:if>
                        <c:if test="${actionBean.laporanTanah.dilintasiLain ne null}">${actionBean.laporanTanah.dilintasiLain}.</c:if>
                    </c:if>
                    <br>
                    <c:if test="${actionBean.laporanTanah.dilintasTiangElektrik eq null and
                                  actionBean.laporanTanah.dilintasTiangTelefon eq null and
                                  actionBean.laporanTanah.dilintasLaluanGas eq null and
                                  actionBean.laporanTanah.dilintasPaip eq null and
                                  actionBean.laporanTanah.dilintasTaliar eq null and
                                  actionBean.laporanTanah.dilintasSungai eq null and
                                  actionBean.laporanTanah.dilintasParit eq null and
                                  actionBean.laporanTanah.dilintasiLain eq null}"> Tiada Data </c:if>
                    <br>
                <p>
                    <label>Kedudukan Tanah :</label>
                    <c:if test="${actionBean.laporanTanah.kedudukanTanah ne null}"> ${actionBean.laporanTanah.kedudukanTanah}&nbsp; </c:if>
                    <c:if test="${actionBean.laporanTanah.kedudukanTanah eq null}"> Tiada Data </c:if>
                </p>
                <p>
                    <label>Jauh Dari Pekan/Bandar :</label>
                    <c:if test="${actionBean.laporanTanah.namaSempadanJalanraya ne null}"> ${actionBean.laporanTanah.namaSempadanJalanraya}&nbsp; </c:if>
                    <c:if test="${actionBean.laporanTanah.namaSempadanJalanraya eq null}"> Tiada Data </c:if>
                </p>
                <p>
                    <label>Mercu Tanda(LandMark) :</label>
                    <c:if test="${actionBean.laporanTanah.mercuTanda ne null}"> ${actionBean.laporanTanah.mercuTanda}&nbsp; </c:if>
                    <c:if test="${actionBean.laporanTanah.mercuTanda eq null}"> Tiada Data </c:if>
                </p>
                <p>
                    <label>Lot Tanah Ini Mempunyai Laluan Sah(Access Reserve) :</label>
                    <c:if test="${actionBean.laporanTanah.adaJalanMasuk eq 'Y'}">Ada </c:if>
                    <c:if test="${actionBean.laporanTanah.adaJalanMasuk ne 'Y'}">Tiada </c:if>
                </p>
                <p><label>Catatan :</label>
                    <c:if test="${actionBean.laporanTanah.catatanJalanMasuk ne null}"> ${actionBean.laporanTanah.catatanJalanMasuk}&nbsp; </c:if>
                    <c:if test="${actionBean.laporanTanah.catatanJalanMasuk eq null}"> Tiada Data </c:if>
                </p>
            </c:if>
            <br>
        </fieldset>
    </div>
    <div class="subtitle displaytag">
        <fieldset class="aras1">
            <legend>Latar belakang Tanah</legend>
            <br>
            <c:if test="${actionBean.stageId eq 'laporantanah'}">
                <p>
                    <label>Diusahakan :</label>
                    <s:radio name="laporanTanah.usaha" value="Y" disabled="${actionBean.disabled}"/>&nbsp;Tuan Tanah
                    <s:radio name="laporanTanah.usaha" value="T" disabled="${actionBean.disabled}"/>&nbsp;Penyewa
                </p>
                <br>
                <p>
                    <label>(Jika Ada)Tanaman :</label>
                    <s:radio name="laporanTanah.namaSempadanLaut" id="tanaman" value="Y" onclick="javaScript:showBilTanaman(this.value);doSubmit(this.form, simpanLaporanTanah, 'page_div')" />&nbsp;Ada
                    <s:radio name="laporanTanah.namaSempadanLaut" id="tanaman" value="T" onclick="javaScript:showBilTanaman(this.value);doSubmit(this.form, simpanLaporanTanah, 'page_div')" />&nbsp;Tiada
                    <c:if test="${actionBean.laporanTanah.namaSempadanLaut eq 'Y'}">
                    <div class="content" align="center" id="bilanganTanaman">
                        <display:table class="tablecloth" name="${actionBean.permohonanLaporanTanamanList}" pagesize="10" cellpadding="0" cellspacing="0"
                                       requestURI="/pembangunan/ns/laporanTanah" id="line">
                            <display:column title="No" sortable="true">${line_rowNum}</display:column>
                            <display:column property="namaPemunya" title="Pemilik" />
                            <display:column property="namaPenyewa" title="Penyewa" />
                            <display:column property="namaKetua" title="Jenis Tanaman" />
                            <display:column title="Keadaan Tanaman">
                                <c:if test="${line.jenisBangunan eq 'KK'}">Kekal</c:if>
                                <c:if test="${line.jenisBangunan eq 'SM'}">Sementara</c:if>
                            </display:column>
                            <display:column property="keteranganTahunBinaan" title="Tahun Dibina" />
                            <display:column  title="Kadar Sewa" >RM <fmt:formatNumber pattern="#,##0.00" value="${line.nilai}"/></display:column>
                            <display:column title="Kemaskini">
                                <div align="center">
                                    <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="editLaporanBangunan('${line.idLaporBangunan}','T');"/>
                                </div>
                            </display:column>
                        </display:table>
                        <s:button class="btn" value="Tambah" name="new" id="bilanganTanaman2" onclick="tambahBangunan('T',this.form);" />
                    </div></c:if>
                    <p>
                        <label>(Jika Ada)Bangunan :</label>
                    <s:radio name="laporanTanah.adaBangunan" id="bangunan" value="Y" onclick="javaScript:showBilBangunan(this.value);doSubmit(this.form, simpanLaporanTanah, 'page_div')" />&nbsp;Ada
                    <s:radio name="laporanTanah.adaBangunan" id="bangunan" value="T" onclick="javaScript:showBilBangunan(this.value);doSubmit(this.form, simpanLaporanTanah, 'page_div')" />&nbsp;Tiada
                    <c:if test="${actionBean.stageId eq 'semaklaporantanah'}">
                        <s:hidden name="laporanTanah.bilanganBangunan"></s:hidden>
                    </c:if>
                    <c:if test="${actionBean.laporanTanah.adaBangunan eq 'Y'}">
                    <div class="content" align="center" id="bilanganBangunan">
                        <p>
                            <s:select name="bngn_idHakmilik" id="bngn_idHakmilik" disabled="disabled" style="display : none">
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${actionBean.hakmilikPermohonanList}" label="hakmilik.idHakmilik" value="hakmilik.idHakmilik" />
                            </s:select>
                            <s:hidden name="permohonan.idPermohonan" id="bngn_idHakmilik" />
                        </p>
                        <display:table class="tablecloth" name="${actionBean.permohonanLaporanBangunanList}" pagesize="10" cellpadding="0" cellspacing="0"
                                       requestURI="/pembangunan/ns/laporanTanah"  id="line">
                            <display:column title="No" sortable="true">${line_rowNum}</display:column>
                            <display:column property="namaPemunya" title="Pemilik" />
                            <display:column property="namaPenyewa" title="Penyewa" />
                            <display:column property="namaKetua" title="Kegunaan Bangunan" />
                            <display:column title="Keadaan Bangunan">
                                <c:if test="${line.jenisBangunan eq 'KK'}">Kekal</c:if>
                                <c:if test="${line.jenisBangunan eq 'SK'}">Separuh Kekal</c:if>
                                <c:if test="${line.jenisBangunan eq 'SM'}">Sementara</c:if>
                            </display:column>
                            <display:column property="keteranganTahunBinaan" title="Tahun Dibina" />
                            <display:column  title="Kadar Sewa" >RM <fmt:formatNumber pattern="#,##0.00" value="${line.nilai}"/></display:column>
                            <display:column title="Kemaskini">
                                <div align="center">
                                    <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="editLaporanBangunan('${line.idLaporBangunan}','B');"/>
                                </div>
                            </display:column>
                        </display:table>
                        <s:button class="btn" value="Tambah" name="newbangun" id="bilanganBangunan2" onclick="tambahBangunan('B',this.form);" disabled="${actionBean.disabled}"/>
                    </div></c:if>
                    <div class="content" align="center">
                        Tanah Milik
                    <display:table  name="${actionBean.permohonan.senaraiHakmilik}" id="line" class="tablecloth">
                        <display:column title="Jenis Hakmilik">
                            <c:if test="${line.hakmilik.kodHakmilik.nama ne null}"> ${line.hakmilik.kodHakmilik.nama}&nbsp; </c:if>
                            <c:if test="${line.hakmilik.kodHakmilik.nama eq null}"> Tiada Data </c:if>
                        </display:column>
                        <display:column title="Nombor Hakmilik">
                            <c:if test="${line.hakmilik.idHakmilik ne null}"> ${line.hakmilik.idHakmilik}&nbsp; </c:if>
                            <c:if test="${line.hakmilik.idHakmilik eq null}"> Tiada Data </c:if>
                        </display:column>
                        <display:column title="Nombor Lot/PT" >
                            <c:if test="${line.hakmilik.noLot ne null}"> ${line.hakmilik.noLot}&nbsp; </c:if>
                            <c:if test="${line.hakmilik.noLot eq null}"> Tiada Data </c:if>
                        </display:column>
                        <display:column title="Luas">
                            <c:if test="${line.hakmilik.luas ne null}"> <fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</c:if>
                            <c:if test="${line.hakmilik.luas eq null}"> Tiada Data </c:if>
                        </display:column>
                        <display:column property="hakmilik.kategoriTanah.nama" title="Kegunaan" >
                            <c:if test="${actionBean.laporanTanah.sempadanUtaraNoLot ne null}"> ${actionBean.laporanTanah.sempadanUtaraNoLot}&nbsp; </c:if>
                            <c:if test="${actionBean.laporanTanah.sempadanUtaraNoLot eq null}"> Tiada Data </c:if>
                        </display:column>
                        <display:column title="Cukai">
                            <c:if test="${line.hakmilik.cukai ne null}"> RM <fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.cukai}"/>&nbsp; </c:if>
                            <c:if test="${line.hakmilik.cukai eq null}"> Tiada Data </c:if>
                        </display:column>
                    </display:table>
                </div>
                <br>
            </c:if>
            <c:if test="${actionBean.stageId eq 'semaklaporantanah'}">
                <p>
                    <label>Diusahakan :</label>
                    <c:if test="${actionBean.laporanTanah.usaha eq 'Y'}">Tuan Tanah</c:if>
                    <c:if test="${actionBean.laporanTanah.usaha ne 'Y'}">Penyewa</c:if>
                </p>
                <p>
                    <label>Tanaman :</label>
                    <c:if test="${actionBean.laporanTanah.namaSempadanLaut eq 'Y'}">Ada</c:if>
                    <c:if test="${actionBean.laporanTanah.namaSempadanLaut ne 'Y'}">Tiada</c:if>
                </p>
                <c:if test="${actionBean.laporanTanah.namaSempadanLaut eq 'Y'}">
                    <div class="content" align="center" id="bilanganTanaman">
                        <display:table class="tablecloth" name="${actionBean.permohonanLaporanTanamanList}" pagesize="10" cellpadding="0" cellspacing="0"
                                       requestURI="/pembangunan/ns/laporanTanah"  id="line">
                            <display:column title="No" sortable="true">${line_rowNum}</display:column>
                            <display:column property="namaPemunya" title="Pemilik" />
                            <display:column property="namaPenyewa" title="Penyewa" />
                            <display:column property="namaKetua" title="Jenis Tanaman" />
                            <display:column title="Keadaan Tanaman">
                                <c:if test="${line.jenisBangunan eq 'KK'}">Kekal</c:if>
                                <c:if test="${line.jenisBangunan eq 'SM'}">Sementara</c:if>
                            </display:column>
                            <display:column property="keteranganTahunBinaan" title="Tahun Dibina" />
                            <display:column  title="Kadar Sewa" >RM <fmt:formatNumber pattern="#,##0.00" value="${line.nilai}"/></display:column>
                        </display:table>
                    </div>
                </c:if>
                <p>
                    <label>Bangunan :</label>
                    <c:if test="${actionBean.laporanTanah.adaBangunan eq 'Y'}">Ada</c:if>
                    <c:if test="${actionBean.laporanTanah.adaBangunan ne 'Y'}">Tiada</c:if>
                </p>
                <c:if test="${actionBean.laporanTanah.adaBangunan eq 'Y'}">
                    <div class="content" align="center" id="bilanganBangunan">
                        <p>
                            <s:select name="bngn_idHakmilik" id="bngn_idHakmilik" disabled="disabled" style="display : none">
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${actionBean.hakmilikPermohonanList}" label="hakmilik.idHakmilik" value="hakmilik.idHakmilik" />
                            </s:select>
                            <s:hidden name="permohonan.idPermohonan" id="bngn_idHakmilik" />
                        </p>
                        <display:table class="tablecloth" name="${actionBean.permohonanLaporanBangunanList}" pagesize="10" cellpadding="0" cellspacing="0"
                                       requestURI="/pembangunan/ns/laporanTanah"  id="line">
                            <display:column title="No" sortable="true">${line_rowNum}</display:column>
                            <display:column property="namaPemunya" title="Pemilik" />
                            <display:column property="namaPenyewa" title="Penyewa" />
                            <display:column property="namaKetua" title="Kegunaan Bangunan" />
                            <display:column title="Keadaan Bangunan">
                                <c:if test="${line.jenisBangunan eq 'KK'}">Kekal</c:if>
                                <c:if test="${line.jenisBangunan eq 'SK'}">Separuh Kekal</c:if>
                                <c:if test="${line.jenisBangunan eq 'SM'}">Sementara</c:if>
                            </display:column>
                            <display:column property="keteranganTahunBinaan" title="Tahun Dibina" />

                            <display:column title="Kadar Sewa"><c:if test="${line.nilai ne ''}">RM <fmt:formatNumber pattern="#,##0.00" value="${line.nilai}"/></c:if></display:column>
                        </display:table>
                    </div>
                </c:if>
                <div class="content" align="center">
                    Tanah Milik
                    <display:table  name="${actionBean.permohonan.senaraiHakmilik}" id="line" class="tablecloth">
                        <display:column title="Jenis Hakmilik">
                            <c:if test="${line.hakmilik.kodHakmilik.nama ne null}"> ${line.hakmilik.kodHakmilik.nama}&nbsp; </c:if>
                            <c:if test="${line.hakmilik.kodHakmilik.nama eq null}"> Tiada Data </c:if>
                        </display:column>
                        <display:column title="Nombor Hakmilik">
                            <c:if test="${line.hakmilik.idHakmilik ne null}"> ${line.hakmilik.idHakmilik}&nbsp; </c:if>
                            <c:if test="${line.hakmilik.idHakmilik eq null}"> Tiada Data </c:if>
                        </display:column>
                        <display:column title="Nombor Lot/PT" >
                            <c:if test="${line.hakmilik.noLot ne null}"> ${line.hakmilik.noLot}&nbsp; </c:if>
                            <c:if test="${line.hakmilik.noLot eq null}"> Tiada Data </c:if>
                        </display:column>
                        <display:column title="Luas">
                            <c:if test="${line.hakmilik.luas ne null}"> <fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</c:if>
                            <c:if test="${line.hakmilik.luas eq null}"> Tiada Data </c:if>
                        </display:column>
                        <display:column property="hakmilik.kategoriTanah.nama" title="Kegunaan" >
                            <c:if test="${actionBean.laporanTanah.sempadanUtaraNoLot ne null}"> ${actionBean.laporanTanah.sempadanUtaraNoLot}&nbsp; </c:if>
                            <c:if test="${actionBean.laporanTanah.sempadanUtaraNoLot eq null}"> Tiada Data </c:if>
                        </display:column>
                        <display:column title="Cukai">
                            <c:if test="${line.hakmilik.cukai ne null}">RM <fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.cukai}"/>&nbsp; </c:if>
                            <c:if test="${line.hakmilik.cukai eq null}"> Tiada Data </c:if>
                        </display:column>
                    </display:table>
                </div>
            </c:if>
            <br>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Perihal Lot-Lot Bersempadan</legend>
            <c:if test="${actionBean.stageId eq 'laporantanah'}">
                <div class="content" align="center">
                    <table class="tablecloth">
                        <tr>
                            <th>&nbsp;</th><th>Status Tanah</th><th>Nombor Lot/PT</th><th>Kategori Tanah</th><th>Catatan</th><th style="display : none">Gambar</th>
                        </tr>
                        <tr>
                            <th>
                                Utara
                            </th>
                            <td>
                                <s:radio name="laporanTanah.sempadanUtaraMilikKerajaan" value="Y" disabled="${actionBean.disabled}"/>&nbsp;Kerajaan
                                <s:radio name="laporanTanah.sempadanUtaraMilikKerajaan" value="T" disabled="${actionBean.disabled}"/>&nbsp;Milik
                            </td>
                            <td>
                                <s:text name="laporanTanah.sempadanUtaraNoLot" disabled="${actionBean.disabled}"/>
                            </td>
                            <td>
                                <s:select name="laporanTanah.sempadanUtaraJenis" disabled="${actionBean.disabled}">
                                    <s:option value="">--Sila Pilih--</s:option>
                                    <s:option value="1">Industri</s:option>
                                    <s:option value="2">Bangunan</s:option>
                                    <s:option value="3">Pertanian</s:option>
                                    <s:option value="4">Rizab</s:option>
                                </s:select>
                            </td>
                            <td>
                                <s:textarea rows="2" cols="40" name="laporanTanah.sempadanUtaraKegunaan" disabled="${actionBean.disabled}"/>
                            </td>
                            <td style="display: none">
                                <c:forEach items="${actionBean.utaraImejLaporanList}" var="senarai" >
                                    <c:out value="${senarai.dokumen.tajuk}"/><br/>
                                </c:forEach>
                                <s:select name="utaraImej" id="utaraImej" disabled="${actionBean.disabled}">
                                    <s:option value="">Sila pilih imej</s:option>
                                    <s:options-collection collection="${actionBean.dokumenList}" label="tajuk" value="idDokumen"/>
                                </s:select>
                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                     onclick="doViewReportUtara();" height="30" width="30" alt="papar"
                                     onmouseover="this.style.cursor='pointer';" title="Papar Dokumen"/>
                                <s:button class="btn" value="Tambah" name="addUtaraImej" id="addUtaraImej" onclick="addImageUtara('${actionBean.permohonan.senaraiHakmilik[0].hakmilik.idHakmilik}');" disabled="${actionBean.disabled}"/>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                Selatan
                            </th>
                            <td>
                                <s:radio name="laporanTanah.sempadanSelatanMilikKerajaan" value="Y" disabled="${actionBean.disabled}"/>&nbsp;Kerajaan
                                <s:radio name="laporanTanah.sempadanSelatanMilikKerajaan" value="T" disabled="${actionBean.disabled}"/>&nbsp;Milik
                            </td>
                            <td>
                                <s:text name="laporanTanah.sempadanSelatanNoLot" disabled="${actionBean.disabled}"/>
                            </td>
                            <td>
                                <s:select name="laporanTanah.sempadanSelatanJenis" disabled="${actionBean.disabled}">
                                    <s:option value="">--Sila Pilih--</s:option>
                                    <s:option value="1">Industri</s:option>
                                    <s:option value="2">Bangunan</s:option>
                                    <s:option value="3">Pertanian</s:option>
                                    <s:option value="4">Rizab</s:option>
                                </s:select>
                            </td>
                            <td>
                                <s:textarea rows="2" cols="40" name="laporanTanah.sempadanSelatanKegunaan" disabled="${actionBean.disabled}"/>
                            </td>
                            <td style="display: none">
                                <c:forEach items="${actionBean.selatanImejLaporanList}" var="senarai">
                                    <c:out value="${senarai.dokumen.tajuk}" /><br/>
                                </c:forEach>
                                <s:select name="selatanImej" id="selatanImej" disabled="${actionBean.disabled}">
                                    <s:option value="">Sila pilih imej</s:option>
                                    <s:options-collection collection="${actionBean.dokumenList}" label="tajuk" value="idDokumen"/>
                                </s:select>
                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                     onclick="doViewReportSelatan();" height="30" width="30" alt="papar"
                                     onmouseover="this.style.cursor='pointer';" title="Papar Dokumen"/>
                                <s:button class="btn" value="Tambah" name="addSelatanImej" id="addSelatanImej" onclick="addImageSelatan('${actionBean.permohonan.senaraiHakmilik[0].hakmilik.idHakmilik}');" disabled="${actionBean.disabled}"/>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                Timur
                            </th>
                            <td>
                                <s:radio name="laporanTanah.sempadanTimurMilikKerajaan" value="Y" disabled="${actionBean.disabled}"/>&nbsp;Kerajaan
                                <s:radio name="laporanTanah.sempadanTimurMilikKerajaan" value="T" disabled="${actionBean.disabled}"/>&nbsp;Milik
                            </td>
                            <td>
                                <s:text name="laporanTanah.sempadanTimurNoLot" disabled="${actionBean.disabled}"/>
                            </td>
                            <td>
                                <s:select name="laporanTanah.sempadanTimurJenis" disabled="${actionBean.disabled}">
                                    <s:option value="">--Sila Pilih--</s:option>
                                    <s:option value="1">Industri</s:option>
                                    <s:option value="2">Bangunan</s:option>
                                    <s:option value="3">Pertanian</s:option>
                                    <s:option value="4">Rizab</s:option>
                                </s:select>
                            </td>
                            <td>
                                <s:textarea rows="2" cols="40" name="laporanTanah.sempadanTimurKegunaan" disabled="${actionBean.disabled}"/>
                            </td>
                            <td style="display: none">
                                <c:forEach items="${actionBean.timurImejLaporanList}" var="senarai">
                                    <c:out value="${senarai.dokumen.tajuk}" /><br/>
                                </c:forEach>
                                <s:select name="timurImej" id="timurImej" disabled="${actionBean.disabled}">
                                    <s:option value="">Sila pilih imej</s:option>
                                    <s:options-collection collection="${actionBean.dokumenList}" label="tajuk" value="idDokumen"/>
                                </s:select>
                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                     onclick="doViewReportTimur();" height="30" width="30" alt="papar"
                                     onmouseover="this.style.cursor='pointer';" title="Papar Dokumen"/>
                                <s:button class="btn" value="Tambah" name="addTimurImej" id="addTimurImej" onclick="addImageTimur('${actionBean.permohonan.senaraiHakmilik[0].hakmilik.idHakmilik}');" disabled="${actionBean.disabled}"/>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                Barat
                            </th>
                            <td>
                                <s:radio name="laporanTanah.sempadanBaratMilikKerajaan" value="Y" disabled="${actionBean.disabled}"/>&nbsp;Kerajaan
                                <s:radio name="laporanTanah.sempadanBaratMilikKerajaan" value="T" disabled="${actionBean.disabled}"/>&nbsp;Milik
                            </td>
                            <td>
                                <s:text name="laporanTanah.sempadanBaratNoLot" disabled="${actionBean.disabled}"/>
                            </td>
                            <td>
                                <s:select name="laporanTanah.sempadanBaratJenis" disabled="${actionBean.disabled}">
                                    <s:option value="">--Sila Pilih--</s:option>
                                    <s:option value="1">Industri</s:option>
                                    <s:option value="2">Bangunan</s:option>
                                    <s:option value="3">Pertanian</s:option>
                                    <s:option value="4">Rizab</s:option>
                                </s:select>
                            </td>
                            <td>
                                <s:textarea rows="2" cols="40" name="laporanTanah.sempadanBaratKegunaan" disabled="${actionBean.disabled}"/>
                            </td>
                            <td style="display: none">
                                <c:forEach items="${actionBean.baratImejLaporanList}" var="senarai">
                                    <c:out value="${senarai.dokumen.tajuk}" /><br/>
                                </c:forEach>
                                <s:select name="baratImej" id="baratImej" disabled="${actionBean.disabled}">
                                    <s:option value="">Sila pilih imej</s:option>
                                    <s:options-collection collection="${actionBean.dokumenList}" label="tajuk" value="idDokumen"/>
                                </s:select>
                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                     onclick="doViewReportBarat();" height="30" width="30" alt="papar"
                                     onmouseover="this.style.cursor='pointer';" title="Papar Dokumen"/>
                                <s:button class="btn" value="Tambah" name="addBaratImej" id="addBaratImej" onclick="addImageBarat('${actionBean.permohonan.senaraiHakmilik[0].hakmilik.idHakmilik}');" disabled="${actionBean.disabled}"/>
                            </td>
                        </tr>
                    </table>
                </div>
                <br>
            </c:if>
            <c:if test="${actionBean.stageId eq 'semaklaporantanah'}">
                <div class="content" align="center">
                    <table class="tablecloth">
                        <tr>
                            <th>&nbsp;</th><th>Status Tanah</th><th>Nombor Lot/PT</th><th>Kategori Tanah</th><th>Catatan</th>
                        </tr>
                        <tr>
                            <th>
                                Utara
                            </th>
                            <td>
                                <c:if test="${actionBean.laporanTanah.sempadanUtaraMilikKerajaan ne 'T'}">Kerajaan</c:if>
                                <c:if test="${actionBean.laporanTanah.sempadanUtaraMilikKerajaan eq 'T'}">Milik</c:if>
                            </td>
                            <td>
                                <c:if test="${actionBean.laporanTanah.sempadanUtaraNoLot ne null}"> ${actionBean.laporanTanah.sempadanUtaraNoLot}&nbsp; </c:if>
                                <c:if test="${actionBean.laporanTanah.sempadanUtaraNoLot eq null}"> Tiada Data </c:if>
                            </td>
                            <td>
                                <c:if test="${actionBean.laporanTanah.sempadanUtaraJenis eq '1'}">Industri&nbsp; </c:if>
                                <c:if test="${actionBean.laporanTanah.sempadanUtaraJenis eq '2'}">Bangunan&nbsp; </c:if>
                                <c:if test="${actionBean.laporanTanah.sempadanUtaraJenis eq '3'}">Pertanian&nbsp; </c:if>
                                <c:if test="${actionBean.laporanTanah.sempadanUtaraJenis eq '4'}">Rizab&nbsp; </c:if>
                                <c:if test="${actionBean.laporanTanah.sempadanUtaraJenis eq null}"> Tiada Data </c:if>
                            </td>
                            <td>
                                <c:if test="${actionBean.laporanTanah.sempadanUtaraKegunaan ne null}"> ${actionBean.laporanTanah.sempadanUtaraKegunaan}&nbsp; </c:if>
                                <c:if test="${actionBean.laporanTanah.sempadanUtaraKegunaan eq null}"> Tiada Data </c:if>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                Selatan
                            </th>
                            <td>
                                <c:if test="${actionBean.laporanTanah.sempadanSelatanMilikKerajaan ne 'T'}">Kerajaan</c:if>
                                <c:if test="${actionBean.laporanTanah.sempadanSelatanMilikKerajaan eq 'T'}">Milik</c:if>
                            </td>
                            <td>
                                <c:if test="${actionBean.laporanTanah.sempadanSelatanNoLot ne null}"> ${actionBean.laporanTanah.sempadanSelatanNoLot}&nbsp; </c:if>
                                <c:if test="${actionBean.laporanTanah.sempadanSelatanNoLot eq null}"> Tiada Data </c:if>
                            </td>
                            <td>
                                <c:if test="${actionBean.laporanTanah.sempadanSelatanJenis eq '1'}">Industri&nbsp; </c:if>
                                <c:if test="${actionBean.laporanTanah.sempadanSelatanJenis eq '2'}">Bangunan&nbsp; </c:if>
                                <c:if test="${actionBean.laporanTanah.sempadanSelatanJenis eq '3'}">Pertanian&nbsp; </c:if>
                                <c:if test="${actionBean.laporanTanah.sempadanSelatanJenis eq '4'}">Rizab&nbsp; </c:if>
                                <c:if test="${actionBean.laporanTanah.sempadanSelatanJenis eq null}"> Tiada Data </c:if>
                            </td>
                            <td>
                                <c:if test="${actionBean.laporanTanah.sempadanSelatanKegunaan ne null}"> ${actionBean.laporanTanah.sempadanSelatanKegunaan}&nbsp; </c:if>
                                <c:if test="${actionBean.laporanTanah.sempadanSelatanKegunaan eq null}"> Tiada Data </c:if>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                Timur
                            </th>
                            <td>
                                <c:if test="${actionBean.laporanTanah.sempadanTimurMilikKerajaan ne 'T'}">Kerajaan</c:if>
                                <c:if test="${actionBean.laporanTanah.sempadanTimurMilikKerajaan eq 'T'}">Milik</c:if>
                            </td>
                            <td>
                                <c:if test="${actionBean.laporanTanah.sempadanTimurNoLot ne null}"> ${actionBean.laporanTanah.sempadanTimurNoLot}&nbsp; </c:if>
                                <c:if test="${actionBean.laporanTanah.sempadanTimurNoLot eq null}"> Tiada Data </c:if>
                            </td>
                            <td>
                                <c:if test="${actionBean.laporanTanah.sempadanTimurJenis eq '1'}">Industri&nbsp; </c:if>
                                <c:if test="${actionBean.laporanTanah.sempadanTimurJenis eq '2'}">Bangunan&nbsp; </c:if>
                                <c:if test="${actionBean.laporanTanah.sempadanTimurJenis eq '3'}">Pertanian&nbsp; </c:if>
                                <c:if test="${actionBean.laporanTanah.sempadanTimurJenis eq '4'}">Rizab&nbsp; </c:if>
                                <c:if test="${actionBean.laporanTanah.sempadanTimurJenis eq null}"> Tiada Data </c:if>
                            </td>
                            <td>
                                <c:if test="${actionBean.laporanTanah.sempadanTimurKegunaan ne null}"> ${actionBean.laporanTanah.sempadanTimurKegunaan}&nbsp; </c:if>
                                <c:if test="${actionBean.laporanTanah.sempadanTimurKegunaan eq null}"> Tiada Data </c:if>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                Barat
                            </th>
                            <td>
                                <c:if test="${actionBean.laporanTanah.sempadanBaratMilikKerajaan ne 'T'}">Kerajaan</c:if>
                                <c:if test="${actionBean.laporanTanah.sempadanBaratMilikKerajaan eq 'T'}">Milik</c:if>
                            </td>
                            <td>
                                <c:if test="${actionBean.laporanTanah.sempadanBaratNoLot ne null}"> ${actionBean.laporanTanah.sempadanBaratNoLot}&nbsp; </c:if>
                                <c:if test="${actionBean.laporanTanah.sempadanBaratNoLot eq null}"> Tiada Data </c:if>
                            </td>
                            <td>
                                <c:if test="${actionBean.laporanTanah.sempadanBaratJenis eq '1'}">Industri&nbsp; </c:if>
                                <c:if test="${actionBean.laporanTanah.sempadanBaratJenis eq '2'}">Bangunan&nbsp; </c:if>
                                <c:if test="${actionBean.laporanTanah.sempadanBaratJenis eq '3'}">Pertanian&nbsp; </c:if>
                                <c:if test="${actionBean.laporanTanah.sempadanBaratJenis eq '4'}">Rizab&nbsp; </c:if>
                                <c:if test="${actionBean.laporanTanah.sempadanBaratJenis eq null}"> Tiada Data </c:if>
                            </td>
                            <td>
                                <c:if test="${actionBean.laporanTanah.sempadanBaratKegunaan ne null}"> ${actionBean.laporanTanah.sempadanBaratKegunaan}&nbsp; </c:if>
                                <c:if test="${actionBean.laporanTanah.sempadanBaratKegunaan eq null}"> Tiada Data </c:if>
                            </td>
                        </tr>
                    </table>
                </div>
                <br>
            </c:if>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Perakuan/Syor</legend><br>
            <c:if test="${actionBean.stageId eq 'laporantanah'}">
                <p>
                    <label>Jika diperakukan :-</label>
                    <s:radio name="laporanTanah.syor" value="Y" onclick="javaScript:showUlasan(this.value);doSubmit(this.form, simpanLaporanTanah, 'page_div')"/>&nbsp;Ada
                    <s:radio name="laporanTanah.syor" value="T" onclick="javaScript:showUlasan(this.value);doSubmit(this.form, simpanLaporanTanah, 'page_div')" />&nbsp;Tiada
                </p>
                <c:if test="${actionBean.laporanTanah.syor eq 'Y'}">
                    <div class="content" id="perakuanSyor">
                        <p>
                            <label>Premium tambahan :</label>
                            <s:text name="hakmilikPermohonan.keteranganKadarPremium"  disabled="${actionBean.disabled}"/>
                        </p>
                        <p>
                            <label>Premium denda(jika ada):</label>
                            <s:text name="hakmilikPermohonan.dendaPremium" onkeyup="validateNumber(this,this.value);"  disabled="${actionBean.disabled}"/>
                        </p>
                        <p>
                            <label>Hasil :</label>
                            <s:text name="hakmilikPermohonan.keteranganCukaiBaru"  disabled="${actionBean.disabled}"/>
                        </p>
                        <p>
                            <label>Syarat Lama/Sekatan Milik dihapuskan diganti baru:</label>
                            <s:textarea class="normal_text" name="hakmilikPermohonan.catatan" cols="50" disabled="${actionBean.disabled}"/>
                        </p>
                        <p>
                            <label>Dikenakan Penjenisan :</label>
                            <s:select name="kategoriTanahBaruString" disabled="${actionBean.disabled}">
                                <s:options-collection collection="${listUtil.senaraiKodKategoriTanah}" label="nama" value="kod" />
                            </s:select>
                            <c:if test="${actionBean.stageId eq 'semaklaporantanah'}">
                                <s:hidden name="hakmilikPermohonan.kategoriTanahBaru.kod"></s:hidden>
                            </c:if>
                        </p>
                        <p>
                            <label>Sumbangan Saliran:</label>
                            <s:text name="hakmilikPermohonan.kosInfra"  onkeyup="validateNumber(this,this.value);" disabled="${actionBean.disabled}"/>
                        </p>
                        <p>
                            <label>Semua bayaran dijelaskan dalam tempoh:</label><b>3 bulan selepas tarikh kelulusan.</b>
                        </p>
                    </div>
                </c:if>
                <c:if test="${actionBean.laporanTanah.syor eq 'T'}">
                    <div id="perakuanSyorUlasan">
                        <p>
                            <label>Ulasan :</label>
                            <s:textarea name="permohonanLaporanUlasan.ulasan" cols="50" disabled="${actionBean.disabled}"/>
                        </p>
                    </div>
                </c:if>                  
                <br>  
            </c:if>
            <c:if test="${actionBean.stageId eq 'semaklaporantanah'}">
                <p>
                    <label>Jika diperakukan :-</label>
                    <c:if test="${actionBean.laporanTanah.syor eq 'Y'}"><b>Ada</b></c:if>
                    <c:if test="${actionBean.laporanTanah.syor eq 'T'}"><b>Tiada</b></c:if>
                </p>
                <br>
                <c:if test="${actionBean.laporanTanah.syor eq 'Y'}">
                    <div class="content" id="perakuanSyor">
                        <p>
                            <label>Premium tambahan :</label>
                            <c:if test="${actionBean.hakmilikPermohonan.keteranganKadarPremium ne null}"> ${actionBean.hakmilikPermohonan.keteranganKadarPremium}&nbsp; </c:if>
                            <c:if test="${actionBean.hakmilikPermohonan.keteranganKadarPremium eq null}"> Tiada Data </c:if>
                        </p>
                        <p>
                            <label>Premium denda(jika ada):</label>
                            <c:if test="${actionBean.hakmilikPermohonan.dendaPremium ne null}"> ${actionBean.hakmilikPermohonan.dendaPremium}&nbsp; </c:if>
                            <c:if test="${actionBean.hakmilikPermohonan.dendaPremium eq null}"> Tiada Data </c:if>
                        </p>
                        <p>
                            <label>Hasil :</label>
                            <c:if test="${actionBean.hakmilikPermohonan.keteranganCukaiBaru ne null}"> ${actionBean.hakmilikPermohonan.keteranganCukaiBaru}&nbsp; </c:if>
                            <c:if test="${actionBean.hakmilikPermohonan.keteranganCukaiBaru eq null}"> Tiada Data </c:if>
                        </p>
                        <p>
                            <label>Syarat Lama/Sekatan Milik dihapuskan diganti baru:</label>
                            <c:if test="${actionBean.hakmilikPermohonan.catatan ne null}"> ${actionBean.hakmilikPermohonan.catatan}&nbsp; </c:if>
                            <c:if test="${actionBean.hakmilikPermohonan.catatan eq null}"> Tiada Data </c:if>
                        </p>
                        <p>
                            <label>Dikenakan Penjenisan :</label>
                            ${actionBean.hakmilikPermohonan.kategoriTanahBaru.nama}
                        </p>
                        <p>
                            <label>Sumbangan Saliran:</label>
                            <c:if test="${actionBean.hakmilikPermohonan.kosInfra ne null}"> ${actionBean.hakmilikPermohonan.kosInfra}&nbsp; </c:if>
                            <c:if test="${actionBean.hakmilikPermohonan.kosInfra eq null}"> Tiada Data </c:if>
                        </p>
                        <p>
                            <label>Semua bayaran dijelaskan dalam tempoh:</label><b>3 bulan selepas tarikh kelulusan.</b>
                        </p>
                    </div>
                </c:if>
                <c:if test="${actionBean.laporanTanah.syor eq 'T'}">
                    <div class="content" id="perakuanSyorUlasan">
                        <%--${actionBean.mohonLaporUlasan}--%>
                        <p>
                            <label>Ulasan :</label>
                            <c:if test="${actionBean.permohonanLaporanUlasan.ulasan ne null}"> ${actionBean.permohonanLaporanUlasan.ulasan}&nbsp; </c:if>
                            <c:if test="${actionBean.permohonanLaporanUlasan.ulasan eq null}"> Tiada Data </c:if>
                        </p>
                    </div>
                </c:if>
                <br>
            </c:if>
        </fieldset>
    </div>
    <c:if test="${actionBean.kodNegeri eq '05'}">
        <div class="subtitle displaytag" align="center">
            <fieldset class="aras1">
                <legend>Ulasan Penolong Pegawai Tanah</legend>
                <br>
                <c:if test="${edit}">
                    <p><s:hidden name="fasaPermohonan.idFasa"/>
                        
                        <c:if test="${actionBean.stageId eq 'semaklaporantanah'}">
                            <s:textarea name="fasaPermohonan.ulasan" cols="70" rows="5" disabled="${actionBean.disabled}"/>
                        </c:if>
                        <c:if test="${actionBean.stageId eq 'laporantanah'}">
                            <s:textarea name="fasaPermohonan.ulasan" cols="70" rows="5"/>
                        </c:if>
                    </p>
                </c:if>
                <c:if test="${!edit}">
                    <p>
                        <c:if test="${actionBean.fasaPermohonan.ulasan ne null}"> ${actionBean.fasaPermohonan.ulasan}&nbsp; </c:if>
                        <c:if test="${actionBean.fasaPermohonan.ulasan eq null}"> Tiada Data </c:if>
                    </p>
                </c:if>
                <legend>Ulasan Penolong Pegawai Tanah Kanan</legend>
                <c:if test="${edit}">
                    <p><s:hidden name="fasaPermohonan2.idFasa"/>                    
                       <s:textarea name="fasaPermohonan2.ulasan" cols="70" rows="5"  disabled="${actionBean.disabled2}"/>

                    </p>
                </c:if>
                <c:if test="${!edit}">
                    <p>
                        <c:if test="${actionBean.fasaPermohonan2.ulasan ne null}"> ${actionBean.fasaPermohonan2.ulasan}&nbsp; </c:if>
                        <c:if test="${actionBean.fasaPermohonan2.ulasan eq null}"> Tiada Data </c:if>
                    </p>
                </c:if>
            </fieldset>
        </div>
    </c:if>
    <c:if test="${actionBean.kodNegeri eq '04'}">
        <div class="subtitle displaytag" align="center">
            <fieldset class="aras1">
                <legend>Log Ulasan</legend>
                <display:table class="tablecloth" name="${actionBean.listNota}" pagesize="10" cellpadding="0" cellspacing="0"
                               requestURI="/pembangunan/ns/laporanTanah"  id="line">
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column title="Peringkat">
                        <c:if test="${line.idAliran eq 'laporantanah'}">Sedia Laporan Tanah</c:if>
                        <c:if test="${line.idAliran eq 'semaklaporantanah'}">Semak Laporan Tanah</c:if>
                    </display:column>
                    <display:column title="Ulasan" property="nota"></display:column>
                    <display:column title="Tarikh Dihantar" property="infoAudit.tarikhMasuk" ></display:column>
                </display:table>
                <s:button class="btn" value="Rekod Ulasan" name="simpanLaporanTanah" id="" onclick="rekodUlasan(this.form);"/>
                <br>
                <p>
            </fieldset>
        </div>
    </c:if>
    <br><br>
    <div class="subtitle" align="center">
        <fieldset class="aras1">
        <c:if test="${actionBean.stageId eq 'laporantanah'}">
            <p>
                <s:button name="simpanLaporanTanah" id="save2" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                <s:button name="limGis" id="lim" value="LIM" class="btn"/>
            </p>
        </c:if>
        <c:if test="${actionBean.stageId eq 'semaklaporantanah'}">
            <p>
                <s:button name="simpanLaporanTanah2" id="save3" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
  
            </p>
        </c:if>
        <%--<c:if test="${actionBean.stageId eq 'laporantanah'}">
            <p>
                <s:button name="simpanLaporanTanah" id="save2" value="Simpan" class="btn" onclick="doSubmit(this.form, simpanLaporanTanah, 'page_div')"/>doSubmit(this.form, this.name, 'page_div')
                <s:button name="simpanLaporanTanah" id="save2" value="Simpan2" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                <s:button name="limGis" id="lim" value="LIM" class="btn"/>
            </p>
        </c:if>--%>
        <%--<c:if test="${actionBean.stageId eq 'semaklaporantanah'}">
          <p>
            <s:button name="simpanLaporanTanah2" id="save3" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
              <s:button name="limGis" id="lim" value="LIM" class="btn"/>x
          </p>
</c:if>--%></fieldset>
    </div>
</s:form>

