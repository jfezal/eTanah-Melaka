<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">

    $(document).ready(function() {

        if($('#adaBangunan').val() == 'Y')
            $('#bilanganBangunan').show();

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

    function removeNonNumeric( strString )
    {
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
    function removeSingle(idTanahRizabPermohonan)
    {
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

    function showBilBangunan() {
        $('#bilanganBangunan').show();
        $('#bilanganBangunan2').show();


    <%--document.forms[0].amt.stybilanganBangunanle.display = '';--%>
        }
        function hideBilBangunan() {
            $('#bilanganBangunan').hide();
            $('#bilanganBangunan2').hide();

    <%--document.forms[0].amt.style.display = 'none';--%>
        }

        function checkPelan(f){
            //alert(f);
            var noLot = f
            var kodDaerah = $('#kodDaerah').val();
            var kodBPM = $('#kodBPM').val();
            var kodNegeri = $('#kodNegeri').val();
            //alert(kodDaerah);
            //var q = $(e).formSerialize();
            $.post('${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?checkPelan&noLot='+noLot+'&kodDaerah='+kodDaerah+'&kodNegeri='+kodNegeri+'&kodBPM='+kodBPM,
            function(data){
                if(data != '1'){
                    //alert(data);
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
            // alert(idDokumen);
            // var idDokumen = document.getElementById("imej_"+rowNo).options[document.getElementById("imej_"+rowNo).selectedIndex].value;
            var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }
        function restoreOld(sObj){
            alert("Boleh " + sObj);
            sObj.selectedIndex=document.getElementById("s1_old").value;
        }

        function tambahBangunan(){

            //            var id =$('#bngn_idHakmilik').val();
            var id = '${actionBean.permohonan.idPermohonan}';
            alert(id);
            window.open("${pageContext.request.contextPath}/pembangunan/ns/laporanTanah?tambahBangunanPopup&idHakmilik="+id, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=910,height=200");
        }

        function editLaporanBangunan(idLaporBangunan){
            var idHakmilik = '${actionBean.hakmilik.idHakmilik}';
            alert('idLaporBangunan-'+idLaporBangunan+'---idHakmilik='+idHakmilik);
            window.open("${pageContext.request.contextPath}/pembangunan/ns/laporanTanah?editBangunanPopup&idHakmilik="+idHakmilik+'&idLaporBangunan='+idLaporBangunan, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=910,height=200");
        }

        function addImageBngn(idHakmilik,idLapor){
            var idDokumen = document.getElementById("ImageBngn").options[document.getElementById("ImageBngn").selectedIndex].value;
            alert("id Dokumen :"+idDokumen+" id Lapran " +idLapor);
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


</script>

<s:form beanclass="etanah.view.stripes.pembangunan.LaporanTanahNSActionBean">
    <s:messages/>
    <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
    <s:hidden name="kodUrusan" value="${actionBean.permohonan.kodUrusan.kod}"/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Laporan Tanah</legend>
            <c:set scope="request" var="senaraiPBT"  value="${actionBean.pihakKepentinganList}"/>

            <%--<c:if test="${actionBean.permohonan.kodUrusan.jabatan.kod eq 9}">--%>
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
            <%--</c:if>--%>
            <%--<c:if test="${edit}">
                <p>
                    <label>Tarikh Permohonan Didaftar :</label>
                    <s:text name="fromDate" id="" class="datepicker"/>
                </p>
            </c:if>--%>
            <%--            <c:if test="${!edit}">
                            <p>
                                <label>Tarikh Permohonan Didaftar :</label>
                                Tiada Data&nbsp;
                            </p>
                        </c:if>--%>
            <br/><br/>

        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Perihal Tanah</legend>
            <c:if test="${edit}">
                <%--<p>
                    <label>Bandar/Pekan/Mukim  :</label>
                    ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodBandarPekanMukim.nama}
                    ${actionBean.permohonanPengambilan.bandarPekanMukim.nama}&nbsp;
                </p>--%>
                <p>
                    <label>Senarai Pihak Berkepentingan :&nbsp;&nbsp </label>
                    &nbsp;&nbsp;
                </p>
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
                        <display:column title="Hasil (RM)">RM ${line.hakmilik.cukaiSebenar}</display:column>
                        <display:column title="Imej Di Atas Tanah">
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
                        </display:column>
                    </display:table>
                </div>
                <br>
                <%--
                <%-- <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.senaraiPihakBerkepentingan[0].pihak.nama ne null}"> ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.senaraiPihakBerkepentingan[0].pihak.nama}&nbsp; </c:if>
                 <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.senaraiPihakBerkepentingan[0].pihak.nama eq null}"> Tiada Data </c:if>--%>

                <%-- <p>
                     <label>Status Tanah :</label>
                     <s:radio name="rdKategoriTanah" value="kerajaan" onchange="javaScript:changeKategoriTanah(this.value)"/>&nbsp;Tanah Kerajaan
                     <s:radio name="rdKategoriTanah" value="milik" onchange="javaScript:changeKategoriTanah(this.value)"/>&nbsp;Tanah Milik
                     <s:radio name="rdKategoriTanah" value="rizab" onchange="javaScript:changeKategoriTanah(this.value)"/>&nbsp;Tanah Rizab
                 </p>
                 <p id="jenisRizab">
                     <label>Jenis Rizab :</label>
                     <s:text name="permohonan.sebab" size="40"/>
                 </p>--%>
                <%--<p id="noWarta">--%>
                <%--<div align="center">
                    <display:table class="tablecloth" id="line">
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column title="No. Warta"/>
                        <display:column title="Tarikh Warta"/>
                    </display:table>
                </div>--%>
                <p>
                    <label>Nombor Warta Kerajaan :</label>
                    <s:text name="permohonanRujukanLuar.noRujukan" disabled="${actionBean.disabled}"/>
                </p>
                <%-- <p>
                     <label>Gambar Lokasi Tanah :</label>
                     <s:radio name="rdGambar" value="Ada"/>&nbsp;Ada
                     <s:radio name="rdGambar" value="Tiada"/>&nbsp;Tiada
                 </p>--%>
                <p>
                    <label>Jenis Tanah :</label>
                    <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodTanah.nama ne null}"> ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodTanah.nama}&nbsp; </c:if>
                    <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodTanah.nama eq null}"> Tiada Data </c:if>
                </p>
                <p>
                    <label>Lokasi :</label>
                    <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.lokasi ne null}"> ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.lokasi}&nbsp; </c:if>
                    <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.lokasi eq null}"> Tiada Data </c:if>
                </p>
                <p>
                    <label>Kawasan PBT :</label>
                    <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.pbt.nama ne null}"> ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.pbt.nama}&nbsp; </c:if>
                    <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.pbt.nama eq null}"> Tiada Data </c:if>
                </p>
                <br><div class="content" align="center">
                    Pelan tanah seperti bertanda <s:text name="lotPertanian" id="lotPertanian" /> di lampiran <s:text name="lotPertanian" id="lotPertanian" /> mengikut pelan yang dikemukakan oleh pemohon
                </div>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPCB' || actionBean.permohonan.kodUrusan.kod eq 'PPCS'}">
                    <p>
                        <label>Pecah Bahagian : </label>
                        Hendak dibelah bagi kepada lot 
                    <div class="content" align="center">
                        <table class="tablecloth">
                            <tr>
                                <th>Pertanian</th><th>Kediaman</th><th>Perniagaan</th><th>Perindustrian</th><th>Lain-lain</th>
                            </tr>
                            <tr>
                                <td>
                                    <s:text name="lotPertanian" id="lotPertanian" /></td>
                                <td>
                                    <s:text name="lotPertanian" id="lotPertanian" /></td>
                                <td>
                                    <s:text name="lotPertanian" id="lotPertanian" /></td>
                                <td>
                                    <s:text name="lotPertanian" id="lotPertanian" /></td>
                                <td>
                                    <s:text name="lotPertanian" id="lotPertanian" /></td>
                            </tr>
                        </table>
                    </div>
                        <label>Catatan :</label>
                        <s:textarea name="sekatanHakmilikText" id="sekatanHakmilikText" cols="50" rows="3"/>

                    </p>
                </c:if>
                <br>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PYTN'}">
                    <p>
                        <label>Penyatuan : </label>
                        Hendak dicantum kepada <s:text name="lotPertanian" id="lotPertanian" /> bahagian.

                    </p>
                </c:if>
                <br>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSPTD' || actionBean.permohonan.kodUrusan.kod eq 'TSPTG' || actionBean.permohonan.kodUrusan.kod eq 'TSMMK'}">
                    <p>
                        <label>Tukar Syarat: </label>
                        Hendak ditukar syarat/pengubahan jenis penggunaan dari <s:text name="lotPertanian" id="lotPertanian" /> kepada <s:text name="lotPertanian" id="lotPertanian" />

                    </p>
                </c:if>
                <br>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'SBMS'}">
                    <label>Serah Balik Beri Milik Semula : </label>
                    <p>Hendaklah diserahbalik keseluruhan dan berimilik semula kepada lot :
                    <div class="content" align="center">
                        <table class="tablecloth">
                            <tr>
                                <th>Pertanian</th><th>Kediaman</th><th>Perniagaan</th><th>Industrian</th><th>Kemajuan akan datang</th>
                            </tr>
                            <tr>
                                <td>
                                    <s:text name="lotPertanian" id="lotPertanian" /></td>
                                <td>
                                    <s:text name="lotPertanian" id="lotPertanian" /></td>
                                <td>
                                    <s:text name="lotPertanian" id="lotPertanian" /></td>
                                <td>
                                    <s:text name="lotPertanian" id="lotPertanian" /></td>
                                <td>
                                    <s:text name="lotPertanian" id="lotPertanian" /></td>
                            </tr>
                        </table>
                    </div>
                    </p>
                    <p>
                        <label>Serah Balik Beri Milik Semula : </label>
                        Semua mereka yang ada kepentingan berdaftar <s:radio name="pKebenaran" value="telah"/>&nbsp;<b>Telah</b><s:radio name="pKebenaran" value="Belum"/>&nbsp;<b>Belum</b> memberi kebenaran bertulis untuk menukar syarat tersebut.
                    </p>
                </c:if>
                <br>
                <p>
                    <label>Projek Kerajaan(Pengambilan) : </label>
                    <s:radio name="pKebenaran" value="ada"/>&nbsp;<b>Ada</b><s:radio name="pKebenaran" value="tiada"/>&nbsp;<b>Tidak</b> terlibat dengan projek Kerajaan (Pengambilan).Jika ada rujukan fail :
                </p>
                <br>
                <p>
                    <label>Jenis Jalan :</label>
                    <s:select name="laporanTanah.jenisJalan" style="width:200px" id="jabatan" disabled="${actionBean.disabled}">
                        <s:option value="0">Sila Pilih</s:option>
                        <s:option value="Jalan Berturap">Jalan Berturap</s:option>
                        <s:option value="Jalan Leterite">Jalan Leterite</s:option>
                        <s:option value="Jalan Tanah Merah">Jalan Tanah Merah</s:option>
                        <s:option value="Jalan Tanah">Jalan Tanah</s:option>
                    </s:select>
                    <c:if test="${actionBean.stageId eq 'semaklaporantanah'}">
                        <s:hidden name="laporanTanah.jenisJalan"></s:hidden>
                    </c:if>
                </p>
                <p>
                    <label>Jalan Masuk :</label>
                    <s:radio name="laporanTanah.adaJalanMasuk" value="Y" disabled="${actionBean.disabled}"/>&nbsp;Ada
                    <s:radio name="laporanTanah.adaJalanMasuk" value="T" disabled="${actionBean.disabled}"/>&nbsp;Tiada
                </p>
                <p>
                    <label>Catatan :</label>
                    <s:textarea name="laporanTanah.catatanJalanMasuk" cols="50" disabled="${actionBean.disabled}"/>
                </p>
            </c:if>
            <c:if test="${!edit}">
                <p>
                    <label>Keluasan :</label>
                    <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.luas ne null}"><fmt:formatNumber  pattern="#,##0.0000" value="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.luas}"/>
                        ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodUnitLuas.nama}&nbsp;</c:if>
                    <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.luas eq null}"> Tiada Data </c:if>


                </p>
                <p>
                    <label>Status Tanah :</label>
                    <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.rizab.nama ne null}"> ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.rizab.nama}&nbsp; </c:if>
                    <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.rizab.nama eq null}"> Tiada Data </c:if>
                </p>

                <%-- <p>
                     <label>Status Tanah :</label>
                     &nbsp;
                 </p>
                 <p id="jenisRizab">
                     <label>Jenis Rizab :</label>
                     &nbsp;
                 </p>--%>
                <p>
                    <label>Nombor Warta Kerajaan :</label>
                    <c:if test="${actionBean.permohonanRujukanLuar.noRujukan ne null}"> ${actionBean.permohonanRujukanLuar.noRujukan}&nbsp; </c:if>
                    <c:if test="${actionBean.permohonanRujukanLuar.noRujukan eq null}"> Tiada Data </c:if>
                </p>
                <%--<p>
                    <label>Gambar Lokasi Tanah :</label>
                    &nbsp;
                </p>--%>
                <p>
                    <label>Jenis Tanah :</label>
                    <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodTanah.nama ne null}"> ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodTanah.nama}&nbsp; </c:if>
                    <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.kodTanah.nama eq null}"> Tiada Data </c:if>
                </p>
                <p>
                    <label>Lokasi :</label>
                    <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.lokasi ne null}"> ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.lokasi}&nbsp; </c:if>
                    <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.lokasi eq null}"> Tiada Data </c:if>
                </p>
                <p>
                    <label>Kawasan PBT :</label>
                    <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.pbt.nama ne null}"> ${actionBean.permohonan.senaraiHakmilik[0].hakmilik.pbt.nama}&nbsp; </c:if>
                    <c:if test="${actionBean.permohonan.senaraiHakmilik[0].hakmilik.pbt.nama eq null}"> Tiada Data </c:if>
                </p>
                <br>
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
                        </tr><tr>
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
                <p>
                    <label>Jalan Masuk :</label>
                    <c:if test="${actionBean.laporanTanah.adaJalanMasuk eq 'Y'}">Ada</c:if>
                    <c:if test="${actionBean.laporanTanah.adaJalanMasuk ne 'Y'}">Tiada</c:if>
                </p>
                <p>
                    <label>Catatan :</label>
                    <c:if test="${actionBean.laporanTanah.catatanJalanMasuk ne null}"> ${actionBean.laporanTanah.catatanJalanMasuk}&nbsp; </c:if>
                    <c:if test="${actionBean.laporanTanah.catatanJalanMasuk eq null}"> Tiada Data </c:if>
                </p>
            </c:if>
        </fieldset>
    </div>

    <div class="subtitle">
        <fieldset class="aras1">
            <s:hidden name="laporanTanah.idLaporan"/>
            <s:hidden name="laporanTanah.permohonan.idPermohonan"/>
            <legend>Keadaan Tanah</legend><br>
            <c:if test="${edit}">
                <c:if test="${actionBean.stageId eq 'semaklaporantanah'}">
                    <s:hidden name="laporanTanah.kecerunanTanah.kod" value="laporanTanah.kecerunanTanah.kod"/>
                    <s:hidden name="laporanTanah.strukturTanah.kod" value="laporanTanah.strukturTanah.kod"/>
                </c:if>
                <p>
                    <label>Keadaan Tanah :</label>
                    <s:select name="laporanTanah.kecerunanTanah.kod" id="keadaanTanah" onchange="javaScript:changeKeadaanTanah(this.options[selectedIndex].text)" disabled="${actionBean.disabled}">
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:option value="RT">Rata</s:option>
                        <s:option value="BK">Berbukit</s:option>
                        <s:option value="TG">Tinggi</s:option>
                        <s:option value="RD">Rendah</s:option>
                        <s:option value="CR">Curam</s:option>
                        <s:option value="PY">Berpaya</s:option>
                        <%-- <s:options-collection collection="${listUtil.senaraiKodKecerunanTanah}" label="nama" value="kod" />--%>
                    </s:select>
                </p>
                <!--                <p id="tinggi">
                                    <label>Ketinggian Dari Paras Jalan (m) :</label>
                <s:text name="laporanTanah.ketinggianDariJalan" maxlength="3" onkeyup="validateNumber(this,this.value);" disabled="${actionBean.disabled}"/>
            </p>
            <p id="cerun">
                <label>Darjah Kecerunan :</label>
                <s:text name="laporanTanah.kecerunanBukit"  maxlength="3" onkeyup="validateNumber(this,this.value);" disabled="${actionBean.disabled}"/>
            </p>
            <p id="dalam">
                <label>Dalam Paras Air (m) :</label>
                <s:text name="laporanTanah.parasAir" maxlength="3" onkeyup="validateNumber(this,this.value);" disabled="${actionBean.disabled}"/>
            </p>-->
                <p>
                    <label>&nbsp;</label>
                    <s:checkbox name="separasDgnJalan" value="Y" />&nbsp; Separas dengan jalan.<br>

                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:checkbox name="tinggidrArasJalan" value="Y" />&nbsp; Tinggi dari aras jalan.<br>

                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:checkbox name="rendahdrArasJalan" value="Y" />&nbsp; Rendah dari aras jalan.<br>

                </p>
                <p><br>
                    <label>Klasifikasi Tanah :</label>
                    <s:select name="laporanTanah.strukturTanah.kod" disabled="${actionBean.disabled}">
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:option value="BA">Biasa</s:option>
                        <s:option value="LA">Liat</s:option>
                        <s:option value="GB">Gambut</s:option>
                        <s:option value="PS">Berpasir</s:option>
                        <s:option value="BT">Berbatu</s:option>
                        <s:option value="LL">Lain-lain</s:option>
                        <%-- <s:options-collection collection="${listUtil.senaraiKodStrukturTanah}" label="nama" value="kod" />--%>
                    </s:select>
                </p>
                <br>
                <%--<p>
                    <label>Keadaan Atas Tanah :</label>
                    <s:select name="laporanTanah.kelapanganTanah.kod">
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodKelapanganTanah}" label="nama" value="kod" />
                    </s:select>
                </p>--%>
                <br>
                <p>
                    <label>Dilintasi Oleh :</label>
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
                    <s:checkbox name="laporanTanah.dilintasParit" value="Y" />&nbsp; Lain-lain : <s:textarea name="laporanTanah.dilintasiLain" cols="50"/><br>
                </p>
                <br>
                <p>
                    <label>Kedudukan Tanah :</label>
                    <s:text name="laporanTanah.kedudukanTanah" size="40" disabled="${actionBean.disabled}"/>
                </p>
                <br>
                <p>
                    <label>Jauh Dari Pekan/Bandar :</label>
                    <s:text name="laporanTanah.kedudukanTanah" size="40" disabled="${actionBean.disabled}"/>
                </p>
                <br>
                <p>
                    <label>Mercu Tanda(LandMark) :</label>
                    <s:text name="laporanTanah.kedudukanTanah" size="40" disabled="${actionBean.disabled}"/>
                </p>
                <br>
                <p>
                    <label>Lot Tanah Ini Mempunyai Laluan Sah(Access Reserve) :</label>
                    <s:radio name="laporanTanah.adaJalanMasuk" value="Y" disabled="${actionBean.disabled}"/>&nbsp;Ada
                    <s:radio name="laporanTanah.adaJalanMasuk" value="T" disabled="${actionBean.disabled}"/>&nbsp;Tiada
                    <s:text name="laporanTanah.kedudukanTanah" size="40" disabled="${actionBean.disabled}"/>
                </p>
                <br>
                <div class="content" align="center">
                    Bersempadan
                    <table class="tablecloth">
                        <tr>
                            <th>Bersempadan</th><th>Nama</th><th>Jarak (KM)</th>
                        </tr>
                        <tr>
                            <td>
                                Jalan Raya
                            </td>
                            <td>
                                <s:text name="laporanTanah.namaSempadanJalanraya" disabled="${actionBean.disabled}"/>
                            </td>
                            <td>
                                <s:text name="laporanTanah.jarakSempadanJalanraya"  maxlength="3" onkeyup="validateNumber(this,this.value);" disabled="${actionBean.disabled}"/>
                            </td>

                        </tr>
                        <tr>
                            <td>
                                Landasan Keretapi
                            </td>
                            <td>
                                <s:text name="laporanTanah.namaSempadanKeretapi" disabled="${actionBean.disabled}"/>
                            </td>
                            <td>
                                <s:text name="laporanTanah.jarakSempadanKeretapi" maxlength="3" onkeyup="validateNumber(this,this.value);" disabled="${actionBean.disabled}"/>
                            </td>

                        </tr>
                        <tr>
                            <td>
                                Laut
                            </td>
                            <td>
                                <s:text name="laporanTanah.namaSempadanLaut" disabled="${actionBean.disabled}"/>
                            </td>
                            <td>
                                <s:text name="laporanTanah.jarakSempadanLaut"  maxlength="3" onkeyup="validateNumber(this,this.value);" disabled="${actionBean.disabled}"/>
                            </td>
                        </tr><tr>
                            <td>
                                Sungai
                            </td>
                            <td>
                                <s:text name="laporanTanah.namaSempadanSungai" disabled="${actionBean.disabled}"/>
                            </td>
                            <td>
                                <s:text name="laporanTanah.jarakSempadanSungai"  maxlength="3" onkeyup="validateNumber(this,this.value);" disabled="${actionBean.disabled}"/>
                            </td>
                        </tr>
                    </table>
                </div>
            </c:if>
            <c:if test="${!edit}">
                <p>
                    <label>Keadaan Tanah :</label>
                    <c:if test="${actionBean.laporanTanah.kecerunanTanah.nama ne null}"> ${actionBean.laporanTanah.kecerunanTanah.nama}&nbsp; </c:if>
                    <c:if test="${actionBean.laporanTanah.kecerunanTanah.nama eq null}"> Tiada Data </c:if>
                </p>
                <p id="tinggi">
                    <label>Ketinggian Dari Paras Jalan (m) :</label>
                    <c:if test="${actionBean.laporanTanah.ketinggianDariJalan ne null}"> ${actionBean.laporanTanah.ketinggianDariJalan}&nbsp; </c:if>
                    <c:if test="${actionBean.laporanTanah.ketinggianDariJalan eq null}"> Tiada Data </c:if>
                </p>
                <p id="cerun">
                    <label>Darjah Kecerunan :</label>
                    <c:if test="${actionBean.laporanTanah.kecerunanBukit ne null}"> ${actionBean.laporanTanah.kecerunanBukit}&nbsp; </c:if>
                    <c:if test="${actionBean.laporanTanah.kecerunanBukit eq null}"> Tiada Data </c:if>
                </p>
                <p id="dalam">
                    <label>Dalam Paras Air (m) :</label>
                    <c:if test="${actionBean.laporanTanah.parasAir ne null}"> ${actionBean.laporanTanah.parasAir}&nbsp; </c:if>
                    <c:if test="${actionBean.laporanTanah.parasAir eq null}"> Tiada Data </c:if>
                </p>
                <p>
                    <label>Klasifikasi Tanah :</label>
                    <c:if test="${actionBean.laporanTanah.strukturTanah.nama ne null}"> ${actionBean.laporanTanah.strukturTanah.nama}&nbsp; </c:if>
                    <c:if test="${actionBean.laporanTanah.strukturTanah.nama eq null}"> Tiada Data </c:if>
                </p>
                <p>
                    <label>Keadaan Atas Tanah :</label>
                    <c:if test="${actionBean.laporanTanah.kelapanganTanah.nama ne null}"> ${actionBean.laporanTanah.kelapanganTanah.nama}&nbsp; </c:if>
                    <c:if test="${actionBean.laporanTanah.kelapanganTanah.nama eq null}"> Tiada Data </c:if>
                </p>
                <br>
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
                        <c:if test="${actionBean.laporanTanah.dilintasTiangElektrik eq 'Y'}"> Talian Elektrik</c:if>
                    <p>
                        <c:if test="${actionBean.laporanTanah.dilintasTiangTelefon eq 'Y'}">
                            <label>&nbsp;</label>
                            Talian Telefon</c:if>

                        </p>
                        <p>
                        <c:if test="${actionBean.laporanTanah.dilintasLaluanGas eq 'Y'}">
                            <label>&nbsp;</label>
                            Laluan Gas</c:if>

                        </p>
                        <p><c:if test="${actionBean.laporanTanah.dilintasPaip eq 'Y'}">
                            <label>&nbsp;</label>
                            Paip Air</c:if>
                        </p>
                        <p><c:if test="${actionBean.laporanTanah.dilintasTaliar eq 'Y'}">
                            <label>&nbsp;</label>
                            Tali Air</c:if>
                        </p>
                        <p><c:if test="${actionBean.laporanTanah.dilintasSungai eq 'Y'}">
                            <label>&nbsp;</label>
                            Sungai</c:if>
                        </p>
                        <p><c:if test="${actionBean.laporanTanah.dilintasParit eq 'Y'}">
                            <label>&nbsp;</label>
                            Parit</c:if>
                        </p>
                        <p><c:if test="${actionBean.laporanTanah.dilintasiLain ne null}">
                            <label>&nbsp;</label>
                            ${actionBean.laporanTanah.dilintasiLain}</c:if>
                        </p>
                </c:if>


                <c:if test="${actionBean.laporanTanah.dilintasTiangElektrik eq null and
                              actionBean.laporanTanah.dilintasTiangTelefon eq null and
                              actionBean.laporanTanah.dilintasLaluanGas eq null and
                              actionBean.laporanTanah.dilintasPaip eq null and
                              actionBean.laporanTanah.dilintasTaliar eq null and
                              actionBean.laporanTanah.dilintasSungai eq null and
                              actionBean.laporanTanah.dilintasParit eq null and
                              actionBean.laporanTanah.dilintasiLain eq null}"> Tiada Data </c:if>


                <%--                <p>
                                    <c:if test="${actionBean.laporanTanah.dilintasTiangTelefon eq 'Y'}">
                                        <label>&nbsp;</label>
                                        Talian Telefon</c:if>

                </p>
                <p>
                    <c:if test="${actionBean.laporanTanah.dilintasLaluanGas eq 'Y'}">
                        <label>&nbsp;</label>
                        Laluan Gas</c:if>

                </p>
                <p><c:if test="${actionBean.laporanTanah.dilintasPaip eq 'Y'}">
                        <label>&nbsp;</label>
                        Paip Air</c:if>
                </p>
                <p><c:if test="${actionBean.laporanTanah.dilintasTaliar eq 'Y'}">
                        <label>&nbsp;</label>
                        Tali Air</c:if>
                </p>
                <p><c:if test="${actionBean.laporanTanah.dilintasSungai eq 'Y'}">
                        <label>&nbsp;</label>
                        Sungai</c:if>
                </p>
                <p><c:if test="${actionBean.laporanTanah.dilintasParit eq 'Y'}">
                        <label>&nbsp;</label>
                        Parit</c:if>
                </p>--%>



            </c:if>

        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Latar belakang Tanah</legend>
            <br>
            <c:if test="${edit}">
                <%--<p>
                    <label>Latarbelakang Tanah :</label>
                    <s:checkbox name="check" value=""/>&nbsp; Pemberimilikan<br>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:checkbox name="check" value=""/>&nbsp; LPS
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:checkbox name="check" value=""/>&nbsp; Permit
                </p>
                <br>

                <p>
                    <label>Kegunaan :</label>
                    <s:text name="permohonan.id" size="40"/>
                </p>
                <p>
                    <label>Tarikh Dikeluarkan :</label>
                    <s:text name="fromDate" id="" class="datepicker"/>
                </p>
                <p>
                    <label>Nama Pemegang :</label>
                    <s:text name="permohonan.id" size="40"/>
                </p>
                <br>
                <p>
                    <label>Permohonan Awal :</label>
                    <s:radio name="rdAwal" value="Ada"/>&nbsp;Ada
                    <s:radio name="rdAwal" value="Tiada"/>&nbsp;Tiada
                </p>
                <p>
                    <label>Tarikh Permohonan :</label>
                    <s:text name="fromDate" id="" class="datepicker"/>
                </p>
                <p>
                    <label>Nombor Daftar :</label>
                    <s:text name="permohonan.id" size="40"/>
                </p>
                <p>
                    <label>Kedudukan :</label>
                    <s:text name="permohonan.id" size="40"/>
                </p>
                <br>
                <br>--%>
                <p>
                    <label>Diusahakan :</label>
                    <s:radio name="laporanTanah.usaha" value="Y" disabled="${actionBean.disabled}"/>&nbsp;Ya
                    <s:radio name="laporanTanah.usaha" value="T" disabled="${actionBean.disabled}"/>&nbsp;Tidak
                </p><br>
                <p>
                    <label>Oleh :</label>
                    <s:text name="laporanTanah.diusaha" size="40" readonly="${actionBean.disabled}"/>
                </p> <br>
                <p>
                    <label>Tarikh Mula Usaha :</label>
                    <s:text name="laporanTanah.tarikhMulaUsaha2" id="datepicker" class="datepicker" formatPattern="dd/MM/yyyy" disabled="${actionBean.disabled}"/>

                </p><br>
                <p>
                    <label>Jenis Tanaman :</label>
                    <s:text name="laporanTanah.usahaTanam" size="40" disabled="${actionBean.disabled}"/>
                </p><br>
                <p>
                    <label>(Jika Ada)Bangunan :</label>
                    <s:hidden name="adaBangunan" id="adaBangunan" value="${actionBean.laporanTanah.adaBangunan}"/>
                    <s:radio name="laporanTanah.adaBangunan" value="Y" onclick="showBilBangunan();" disabled="${actionBean.disabled}"/>&nbsp;Ada
                    <s:radio name="laporanTanah.adaBangunan" value="T" onclick="hideBilBangunan();" disabled="${actionBean.disabled}"/>&nbsp;Tiada
                    <c:if test="${actionBean.stageId eq 'semaklaporantanah'}">
                        <s:hidden name="laporanTanah.bilanganBangunan"></s:hidden>
                    </c:if>
                    <!--                <table style="display:none" id="bilanganBangunan2" align="center" width="80%" >
                                        <tr><td width="52%" align="right"> <font color="blue" style="font-size: 10pt;"><b>Bil Bangunan :</b></font>&nbsp;</td>
                                            <td><s:select title="Bil Bangunan :" name="laporanTanah.bilanganBangunan" disabled="${actionBean.disabled}">
                        <s:option value="0">--Sila Pilih--</s:option>
                        <s:option>1</s:option>
                        <s:option>2</s:option>
                        <s:option>3</s:option>
                        <s:option>4</s:option>
                        <s:option>5</s:option>
                        <s:option>6</s:option>
                        <s:option>7</s:option>
                        <s:option>8</s:option>
                        <s:option>9</s:option>
                        <s:option>10</s:option>
                    </s:select></td></tr>
        </table>-->
                <div class="content" align="center" id="bilanganBangunan">
                    <p>
                        <label>Hakmilik Bangunan :</label><br>
                        <s:select name="bngn_idHakmilik" id="bngn_idHakmilik" disabled="disabled" style="display : none">
                            <s:option value="">--Sila Pilih--</s:option>
                            <s:options-collection collection="${actionBean.hakmilikPermohonanList}" label="hakmilik.idHakmilik" value="hakmilik.idHakmilik" />
                        </s:select>
                        <s:hidden name="permohonan.idPermohonan" id="bngn_idHakmilik" />
                    </p>
                    <display:table class="tablecloth" name="${actionBean.permohonanLaporanBangunanList}" pagesize="5" cellpadding="0" cellspacing="0"
                                   requestURI="/pembangunan/ns/laporanTanah"  id="line">
                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                        <display:column title="No Hakmilik" property="idLaporBangunan"></display:column>
                        <display:column title="Jenis Bangunan">
                            <c:if test="${line.jenisBangunan eq 'KK'}">Kekal</c:if>
                            <c:if test="${line.jenisBangunan eq 'SK'}">Separuh Kekal</c:if>
                            <c:if test="${line.jenisBangunan eq 'SM'}">Sementara</c:if>
                        </display:column>
                        <display:column property="tahunDibina" title="Tahun Dibina" />
                        <display:column property="namaPemunya" title="Pemilik" />
                        <display:column title="Status">
                            Penyewa Tanah dan Bangunan
                        </display:column>
                        <display:column title="Kemaskini">
                            <div align="center">
                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="alert('edit'+${line.idLaporBangunan});editLaporanBangunan('${line.idLaporBangunan}');"/>
                            </div>
                        </display:column>
                        <display:column title="Imej Bangunan">
                            <c:forEach items="${actionBean.utaraImejLaporanList}" var="senarai" >
                                <c:out value="${senarai.dokumen.tajuk}"/><br/>
                            </c:forEach>
                            <s:select name="ImageBngn" id="ImageBngn" disabled="${actionBean.disabled}">
                                <s:option value="">Sila pilih imej</s:option>
                                <s:options-collection collection="${actionBean.dokumenList}" label="tajuk" value="idDokumen"/>
                            </s:select>
                            <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                 onclick="doViewReportUtara();" height="30" width="30" alt="papar"
                                 onmouseover="this.style.cursor='pointer';" title="Papar Dokumen"/>
                            <s:button class="btn" value="Tambah" name="addBngnImg" id="addBngnImg" onclick="addImageBngn('${actionBean.permohonan.senaraiHakmilik[0].hakmilik.idHakmilik}','${line.idLaporBangunan}');" disabled="${actionBean.disabled}"/>

                        </display:column>
                    </display:table>

                    <s:button class="btn" value="Tambah" name="new" id="bilanganBangunan2" onclick="tambahBangunan();" disabled="${actionBean.disabled}"/>
                </div>
                </p>
                <p style="display: none">
                    <label>Tahun Dibina :</label>
                    <s:text name="laporanTanah.bangunanTahunDibina" maxlength="4" onkeyup="validateNumber(this,this.value);" disabled="${actionBean.disabled}"/>
                </p>
                <p style="display: none">
                    <label>Diduduki :</label>
                    <%--<s:checkbox name="laporanTanah.dilintasTiangTelefon" value="Y"/>&nbsp;Ya
                    <s:checkbox name="laporanTanah.dilintasTiangTelefon" value="Y"/>&nbsp;Tidak--%>
                    <s:radio name="laporanTanah.bangunanDidiami" value="Y" disabled="${actionBean.disabled}"/>&nbsp;Ya
                    <s:radio name="laporanTanah.bangunanDidiami" value="T" disabled="${actionBean.disabled}"/>&nbsp;Tidak
                </p>
                <p style="display: none">
                    <label>Jenis Bangunan :</label>
                    <c:if test="${actionBean.stageId eq 'semaklaporantanah'}">
                        <s:hidden name="laporanTanah.jenisBangunan"></s:hidden>
                    </c:if>
                    <s:select name="laporanTanah.jenisBangunan" disabled="${actionBean.disabled}">
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:option>Sementara</s:option>
                        <s:option>Separuh Kekal</s:option>
                        <s:option>Kekal</s:option>
                        <s:option>Lain-lain</s:option>

                    </s:select>
                </p>

                <%--<p>
                    <label>Terdapat Rancangan Kerajaan :</label>
                    <s:radio name="rRancangan" value="ada" onchange="javaScript:changeRancangan(this.value)"/>&nbsp;Ya
                    <s:radio name="rRancangan" value="tiada" onchange="javaScript:changeRancangan(this.value)"/>&nbsp;Tidak
                </p>--%>

                <%--<p id="nyataRancangan">--%>
                <p>
                    <%--<label>Nyatakan :</label>--%>
                    <label>Rancangan Kerajaan :</label>
                    <s:text name="laporanTanah.rancanganKerajaan" size="40" disabled="${actionBean.disabled}"/>
                </p>
                <p>
                    <label>Keadaan Atas Tanah :</label>
                    <s:textarea name="laporanTanah.keadaanTanah" cols="50" disabled="${actionBean.disabled}"/>
                </p>
                <%--<p>
                    <label>Tanah Milik :</label>
                    <s:radio name="rdHakmilik" value="ya" onchange="javaScript:changeTanahMilik(this.value)"/>&nbsp;Ya
                    <s:radio name="rdHakmilik" value="tidak" onchange="javaScript:changeTanahMilik(this.value)"/>&nbsp;Tidak
                </p>--%>

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

                        <display:column title="Cukai (RM)">
                            <c:if test="${line.hakmilik.cukai ne null}"> <fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.cukai}"/>&nbsp; </c:if>
                            <c:if test="${line.hakmilik.cukai eq null}"> Tiada Data </c:if>
                        </display:column>
                    </display:table>
                </div>
                <br>
                <%--<div class="content" align="center">
                 Tanah Rizab/GSA
                <display:table class="tablecloth" name="${actionBean.tanahRizabList}" pagesize="20" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/laporan_tanah">
                    <display:column title="No" sortable="true">${line_rowNum}
                    <s:hidden name="x" class="x${line_rowNum -1}" value="${line.idTanahRizabPermohonan}"/></display:column>
                    <display:column property="tanahrizab.noLitho" title="No Lembaran Piawai"/>
                    <display:column property="idTanahRizabPermohonan" title="Id"/>
                    <display:column property="noLitho" title="No Lembaran Piawai" />
                    <display:column property="rizab.nama" title="Jenis Rizab" />
                    <display:column property="noLot" title="No Lot" />
                    <display:column property="lokasi" title="Kedudukan Tanah" />
                    <display:column property="noWarta" title="No. Warta"/>
                    <display:column property="tarikhWarta" title="Tarikh Warta" format="{0,date,dd/MM/yyyy}"/>
                    <display:column title="Kemaskini">
                    <div align="center">
                    <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                    id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popup('${line_rowNum -1}');"/>
                    </div>
                    </display:column>
                    <display:column title="Hapus">
                   <div align="center">
                   <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                    id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeSingle('${actionBean.tanahRizabList[line_rowNum-1].idTanahRizabPermohonan}');" />
                   </div>
                    </display:column>
                </display:table>
                        <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahBaru();"/>&nbsp;
                <br>
            </div>--%>
            </c:if>
            <c:if test="${!edit}">
                <%--  <p>
                      <label>Latarbelakang Tanah :</label>
                      &nbsp;
                  </p>

                <p>
                    <label>Kegunaan :</label>
                    &nbsp;
                </p>
                <p>
                    <label>Tarikh Dikeluarkan :</label>
                    &nbsp;
                </p>
                <p>
                    <label>Nama Pemegang :</label>
                    &nbsp;
                </p>
                <br>
                <p>
                    <label>Permohonan Awal :</label>
                    &nbsp;
                </p>
                <p>
                    <label>Tarikh Permohonan :</label>
                    &nbsp;
                </p>
                <p>
                    <label>Nombor Daftar :</label>
                    &nbsp;
                </p>
                <p>
                    <label>Kedudukan :</label>
                    &nbsp;
                </p>
                <br>
                <br>--%>
                <p>
                    <label>Diusahakan :</label>
                    <c:if test="${actionBean.laporanTanah.usaha eq 'Y'}">Ya</c:if>
                    <c:if test="${actionBean.laporanTanah.usaha ne 'Y'}">Tidak</c:if>
                </p>
                <p>
                    <label>Oleh :</label>
                    <c:if test="${actionBean.laporanTanah.diusaha ne null}"> ${actionBean.laporanTanah.diusaha}&nbsp; </c:if>
                    <c:if test="${actionBean.laporanTanah.diusaha eq null}"> Tiada Data </c:if>
                </p>
                <p>
                    <label>Tarikh Mula Usaha :</label>
                    <c:if test="${actionBean.laporanTanah.tarikhMulaUsaha ne null}"><s:format formatPattern="dd/MM/yyyy" value="${actionBean.laporanTanah.tarikhMulaUsaha}" />&nbsp;</c:if>
                    <c:if test="${actionBean.laporanTanah.rancanganKerajaan eq null}"> Tiada Data </c:if>
                </p>
                <p>
                    <label>Jenis Tanaman :</label>
                    <c:if test="${actionBean.laporanTanah.usahaTanam ne null}"> ${actionBean.laporanTanah.usahaTanam}&nbsp; </c:if>
                    <c:if test="${actionBean.laporanTanah.usahaTanam eq null}"> Tiada Data </c:if>
                </p>
                <p>
                    <label>Bangunan :</label>
                    <c:if test="${actionBean.laporanTanah.adaBangunan eq 'Y'}">Ada</c:if>
                    <c:if test="${actionBean.laporanTanah.adaBangunan ne 'Y'}">Tiada</c:if>

                </p>
                <p>
                    <label>Tahun Dibina :</label>
                    <c:if test="${actionBean.laporanTanah.bangunanTahunDibina ne null}"> ${actionBean.laporanTanah.bangunanTahunDibina}&nbsp; </c:if>
                    <c:if test="${actionBean.laporanTanah.bangunanTahunDibina eq null}"> Tiada Data </c:if>
                </p>
                <p>
                    <label>Diduduki :</label>
                    <c:if test="${actionbean.laporanTanah.bangunanDidiami eq 'Y'}">Ya</c:if>
                    <c:if test="${actionbean.laporanTanah.bangunanDidiami ne 'Y'}">Tidak</c:if>

                </p>
                <p>
                    <label>Jenis Bangunan :</label>
                    <c:if test="${actionBean.laporanTanah.jenisBangunan ne null}"> ${actionBean.laporanTanah.jenisBangunan}&nbsp; </c:if>
                    <c:if test="${actionBean.laporanTanah.jenisBangunan eq null}"> Tiada Data </c:if>
                </p>
                <p>
                    <label>Rancangan Kerajaan :</label>

                    <c:if test="${actionBean.laporanTanah.rancanganKerajaan ne null}"> ${actionBean.laporanTanah.rancanganKerajaan}&nbsp; </c:if>
                    <c:if test="${actionBean.laporanTanah.rancanganKerajaan eq null}"> Tiada Data </c:if>

                </p>
                <%--<p>
                    <label>Tanah Milik :</label>
                    &nbsp;
                </p>--%>

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

                        <display:column title="Cukai (RM)">
                            <c:if test="${line.hakmilik.cukai ne null}"> <fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.cukai}"/>&nbsp; </c:if>
                            <c:if test="${line.hakmilik.cukai eq null}"> Tiada Data </c:if>
                        </display:column>
                    </display:table>
                </div>
            </c:if>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1" style="display: none">
            <legend>Perihal Lot-Lot Bersempadan</legend>
            <c:if test="${edit}">
                <div class="content" align="center">
                    <table class="tablecloth">
                        <tr>
                            <th>&nbsp;</th><th>Taraf Tanah</th><th>Nombor Lot</th><th>Kegunaan</th><th style="display:none">Gambar</th>
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
                                <s:text name="laporanTanah.sempadanUtaraKegunaan" disabled="${actionBean.disabled}"/>
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
                                <s:text name="laporanTanah.sempadanSelatanKegunaan" disabled="${actionBean.disabled}"/>
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
                                <s:text name="laporanTanah.sempadanTimurKegunaan" disabled="${actionBean.disabled}"/>
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
                        </tr><tr>
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
                                <s:text name="laporanTanah.sempadanBaratKegunaan" disabled="${actionBean.disabled}"/>
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
            </c:if>
            <c:if test="${!edit}">
                <div class="content" align="center">
                    <table class="tablecloth">
                        <tr>
                            <th>&nbsp;</th><th>Jenis Tanah</th><th>Nombor Lot</th><th>Kegunaan</th>
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
                                <c:if test="${actionBean.laporanTanah.sempadanTimurKegunaan ne null}"> ${actionBean.laporanTanah.sempadanTimurKegunaan}&nbsp; </c:if>
                                <c:if test="${actionBean.laporanTanah.sempadanTimurKegunaan eq null}"> Tiada Data </c:if>
                            </td>
                        </tr><tr>
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
                                <c:if test="${actionBean.laporanTanah.sempadanBaratKegunaan ne null}"> ${actionBean.laporanTanah.sempadanBaratKegunaan}&nbsp; </c:if>
                                <c:if test="${actionBean.laporanTanah.sempadanBaratKegunaan eq null}"> Tiada Data </c:if>
                            </td>
                        </tr>
                    </table>
                </div>
            </c:if>

        </fieldset>
    </div>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Perakuan/Syor</legend><br>
            <c:if test="${edit}">
                <p>
                    <label>Jika diperakukan :-</label>
                </p>
                <br><br>
                <p>
                    <label>Premium tambahan :</label>
                    <s:text name="laporanTanah.kedudukanTanah"  disabled="${actionBean.disabled}"/>
                </p>
                <p>
                    <label>Premium denda(jika ada):</label>
                    <s:text name="laporanTanah.kedudukanTanah"  disabled="${actionBean.disabled}"/>
                </p>
                <p>
                    <label>Hasil :</label>
                    <s:text name="laporanTanah.kedudukanTanah"  disabled="${actionBean.disabled}"/>
                </p>
                <p>
                    <label>Dikenakan Penjenisan :</label>
                    <s:select name="" disabled="${actionBean.disabled}">
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:option value="BA">Bangunan</s:option>
                        <s:option value="LA">Perusahaan</s:option>
                        <s:option value="GB">Pertanian</s:option>
                        <s:option value="LL">Tiada</s:option>
                        <%-- <s:options-collection collection="${listUtil.senaraiKodStrukturTanah}" label="nama" value="kod" />--%>
                    </s:select>
                </p>
                <p>
                    <label>Syarat Lama/Sekatan Milik :</label>
                    <s:textarea name="laporanTanah.kedudukanTanah" cols="50" disabled="${actionBean.disabled}"/>
                </p>
                <p>
                    <label>Sumbangan Saliran:</label>
                    <s:text name="laporanTanah.kedudukanTanah"  disabled="${actionBean.disabled}"/>
                </p>
                <p>
<!--                    <label>Semua bayaran dijelaskan dalam tempoh:</label><b>3 bulan selepas tarikh kelulusan</b>-->
                </p><br>
            </c:if>
        </fieldset>
    </div>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Ulasan Penolong Pegawai Tanah</legend>
            <br>
            <c:if test="${edit}">
                <p>
                    <label>Ulasan :</label><s:hidden name="fasaPermohonan.idFasa"/>
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
                    <label>Ulasan :</label>
                    <c:if test="${actionBean.fasaPermohonan.ulasan ne null}"> ${actionBean.fasaPermohonan.ulasan}&nbsp; </c:if>
                    <c:if test="${actionBean.fasaPermohonan.ulasan eq null}"> Tiada Data </c:if>
                </p>
            </c:if>
            <legend>Ulasan Penolong Pegawai Tanah Kanan</legend>
            <c:if test="${edit}">
                <p>
                    <label>Ulasan :</label><s:hidden name="fasaPermohonan2.idFasa"/>
                    <s:textarea name="fasaPermohonan2.ulasan" cols="70" rows="5"  disabled="${actionBean.disabled2}"/>

                </p>
                <c:if test="${actionBean.stageId eq 'laporantanah'}">
                    <p>
                        <label>&nbsp;</label>
                        <s:button name="simpanLaporanTanah" id="save2" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                        <s:button name="limGis" id="lim" value="LIM" class="btn"/>
                    </p>
                </c:if>
                <c:if test="${actionBean.stageId eq 'semaklaporantanah'}">
                    <p>
                        <label>&nbsp;</label>
                        <s:button name="simpanLaporanTanah2" id="save3" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                        <s:button name="limGis" id="lim" value="LIM" class="btn"/>
                    </p>
                </c:if>
            </c:if>
            <c:if test="${!edit}">
                <p>
                    <label>Ulasan :</label>
                    <c:if test="${actionBean.fasaPermohonan2.ulasan ne null}"> ${actionBean.fasaPermohonan2.ulasan}&nbsp; </c:if>
                    <c:if test="${actionBean.fasaPermohonan2.ulasan eq null}"> Tiada Data </c:if>
                </p>
            </c:if>
        </fieldset>
    </div>
</s:form>

