<%--
    Document   : laporanTanahPengambilan_new_n9_v2
    Created on : 4-Mac-2010, 10:32:27
    Author     : Rajesh
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">

    $(document).ready(function() {
    <c:if test="${actionBean.jenisKegunaan eq 'B'}">
            showBangunan();
    </c:if>

    <c:if test="${actionBean.jenisKegunaan eq 'P'}">
            showPertanian();
    </c:if>
    <c:if test="${actionBean.jenisKegunaan eq 'U'}">
            showPerusahaan();
    </c:if>

        });

        function jenisKegunaan(){
    <c:if test="${actionBean.jenisKegunaan eq 'B'}">
            showBangunan();
    </c:if>

    <c:if test="${actionBean.jenisKegunaan eq 'P'}">
            showPertanian();
    </c:if>
    <c:if test="${actionBean.jenisKegunaan eq 'U'}">
            showPerusahaan();
    </c:if>
        }

        function changeTujuan(value){

            if(value == "Lain-lain")
                $('#tujuan').show();
            else
                $('#tujuan').hide();
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
            window.open("${pageContext.request.contextPath}/pengambilan/laporanTanahPengambilan?showeditTanahRizab&idTanahRizabPermohonan="+d, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
        }


        function refreshRizab(){
            var url = '${pageContext.request.contextPath}/pengambilan/laporanTanahPengambilan?refreshpage';
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }

        function showBangunan() {
            $('#bangunan').show();
            $('#pertanian').hide();
            $('#perusahaan').hide();
        }
        function showPertanian() {
            $('#pertanian').show();
            $('#bangunan').hide();
            $('#perusahaan').hide();
        }

        function showPerusahaan(){
            $('#perusahaan').show();
            $('#bangunan').hide();
            $('#pertanian').hide();
        }

        function addImageUtara(){
            var idHakmilik = '${actionBean.hakmilik.idHakmilik}';
            var idDokumen = document.getElementById("utaraImej").options[document.getElementById("utaraImej").selectedIndex].value;
            $.post('${pageContext.request.contextPath}/pengambilan/laporanTanahPengambilan?addLaporanImage&pandanganImej=U&idDokumen='+idDokumen+'&idHakmilik='+idHakmilik,
            function(data){
                $('#page_div').html(data);
            }, 'html');
        }

        function addImageSelatan(){
            var idHakmilik = '${actionBean.hakmilik.idHakmilik}';
            var idDokumen = document.getElementById("selatanImej").options[document.getElementById("selatanImej").selectedIndex].value;
            $.post('${pageContext.request.contextPath}/pengambilan/laporanTanahPengambilan?addLaporanImage&pandanganImej=S&idDokumen='+idDokumen+'&idHakmilik='+idHakmilik,
            function(data){
                $('#page_div').html(data);
            }, 'html');
        }

        function addImageTimur(){
            var idHakmilik = '${actionBean.hakmilik.idHakmilik}';
            var idDokumen = document.getElementById("timurImej").options[document.getElementById("timurImej").selectedIndex].value;
            $.post('${pageContext.request.contextPath}/pengambilan/laporanTanahPengambilan?addLaporanImage&pandanganImej=T&idDokumen='+idDokumen+'&idHakmilik='+idHakmilik,
            function(data){
                $('#page_div').html(data);
            }, 'html');
        }

        function addImageBarat(){
            var idHakmilik = '${actionBean.hakmilik.idHakmilik}';
            var idDokumen = document.getElementById("baratImej").options[document.getElementById("baratImej").selectedIndex].value;
            $.post('${pageContext.request.contextPath}/pengambilan/laporanTanahPengambilan?addLaporanImage&pandanganImej=B&idDokumen='+idDokumen+'&idHakmilik='+idHakmilik,
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

        function tambahBangunan(kategori){
            var idHakmilik = '${actionBean.hakmilik.idHakmilik}';
            window.open("${pageContext.request.contextPath}/pengambilan/laporanTanahPengambilan?tambahBangunanPopup&idHakmilik="+idHakmilik+'&kategori='+kategori, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
        }

        function editLaporanBangunan(idLaporBangunan){
            var idHakmilik = '${actionBean.hakmilik.idHakmilik}';
    <%--alert('idLaporBangunan-'+idLaporBangunan+'---idHakmilik='+idHakmilik);--%>
            window.open("${pageContext.request.contextPath}/pengambilan/laporanTanahPengambilan?editBangunanPopup&idHakmilik="+idHakmilik+'&idLaporBangunan='+idLaporBangunan, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
        }

        function showlist() {
            $('#line').show();
        }
        function hidelist() {

            $('#line').hide();

        }

        function uploadForm(idHakmilik,pandanganImej) {
            var left = (screen.width/2)-(1000/2);
            var top = (screen.height/2)-(150/2);
            var url = '${pageContext.request.contextPath}/pengambilan/laporanTanahPengambilan?uploadDoc&idHakmilik='+idHakmilik+'&pandanganImej='+pandanganImej;
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
        }

        function hapusUtaraImej(idHakmilik,pandanganImej){
            if((confirm("Anda pasti ingin menghapuskan imej ini?"))){
                var idDokumen = document.getElementById("utaraImej").options[document.getElementById("utaraImej").selectedIndex].value;
                //              alert(idDokumen);
                var url = '${pageContext.request.contextPath}/pengambilan/laporanTanahPengambilan?hapusImej&idDokumen='+idDokumen;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');

            }

        }

        function hapusSelatanImej(idHakmilik,pandanganImej){
            if((confirm("Anda pasti ingin menghapuskan imej ini?"))){
                var idDokumen = document.getElementById("selatanImej").options[document.getElementById("selatanImej").selectedIndex].value;
                //              alert(idDokumen);
                var url = '${pageContext.request.contextPath}/pengambilan/laporanTanahPengambilan?hapusImej&idDokumen='+idDokumen;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');

            }

        }

        function hapusTimurImej(idHakmilik,pandanganImej){
            if((confirm("Anda pasti ingin menghapuskan imej ini?"))){
                var idDokumen = document.getElementById("timurImej").options[document.getElementById("timurImej").selectedIndex].value;
                //              alert(idDokumen);
                var url = '${pageContext.request.contextPath}/pengambilan/laporanTanahPengambilan?hapusImej&idDokumen='+idDokumen;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');

            }

        }

        function hapusBaratImej(idHakmilik,pandanganImej){
            if((confirm("Anda pasti ingin menghapuskan imej ini?"))){
                var idDokumen = document.getElementById("baratImej").options[document.getElementById("baratImej").selectedIndex].value;
                //              alert(idDokumen);
                var url = '${pageContext.request.contextPath}/pengambilan/laporanTanahPengambilan?hapusImej&idDokumen='+idDokumen;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');

            }

        }

        function doSetDokumenUtara(){
            var idDokumen = document.getElementById("utaraImej").options[document.getElementById("utaraImej").selectedIndex].value;
            var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }

        function doSetDokumenTimur(){
            var idDokumen = document.getElementById("timurImej").options[document.getElementById("timurImej").selectedIndex].value;
            var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }
        function doSetDokumenBarat(){
            var idDokumen = document.getElementById("baratImej").options[document.getElementById("baratImej").selectedIndex].value;
            var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }
        function doSetDokumenSelatan(){
            var idDokumen = document.getElementById("selatanImej").options[document.getElementById("selatanImej").selectedIndex].value;
            var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }

        function refresh(idHakmilik){
            if(idHakmilik !=''){
                var url = '${pageContext.request.contextPath}/pengambilan/laporanTanahPengambilan?hakmilikDetails&idHakmilik='+idHakmilik;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
            }
        }
        function removeBangunan(idLaporBangunan)
        {
            if(confirm('Adakah anda pasti?')) {
                var url = '${pageContext.request.contextPath}/pengambilan/laporanTanahPengambilan?deleteBangunan&idLaporBangunan='
                    +idLaporBangunan;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
            }
        }
        function ajaxLink(link, update) {
            $.get(link, function(data) {
                $(update).html(data);
                $(update).show();
            });
            return false;
        }

        function validation() {
            if($("#keadaanTanah").val() == ""){
                alert('Sila Masukkan Keadaan Tanah');
                $("#keadaanTanah").focus();
                return false;
            }

            if($("#penolongPegawaiTanah").val() == ""){
                alert('Sila Masukkan Ulasan Penolong Pegawai Tanah');
                $("#penolongPegawaiTanah").focus();
                return false;
            }
            return true;
        }
        function semak(){
           // alert('test');
           // jPertanian = document.getElementById("usahaTanam").valueOf();
           // alert(jPertanian);
            if($("#laporanTanah.usahaTanam").val() == ""){
                alert('Sila Masukkan Jenis Pertanian');
                 return false;
            }
        }
</script>

<s:form beanclass="etanah.view.stripes.pengambilan.LaporanTanahPengambilanActionBean">
    <s:hidden name="idHakmilik" value="${actionBean.hakmilik.idHakmilik}"/>
    <s:messages/>
    <s:errors/>
    <c:if test="${actionBean.view eq 'false'}">
        <div id="hakmilik_details">
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>Senarai Hakmilik Terlibat</legend>
                    <br/>
                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.permohonan.senaraiHakmilik}" pagesize="5" cellpadding="0" cellspacing="0"
                                       requestURI="/pengambilan/laporanTanahPengambilan" id="line">
                            <display:column title="No" sortable="true">${line_rowNum}</display:column>
                            <display:column title="ID Hakmilik">
                                <s:link beanclass="etanah.view.stripes.pengambilan.LaporanTanahPengambilanActionBean"
                                        event="hakmilikDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                                    <s:param name="view" value="${actionBean.view}"/>
                                    <s:param name="idHakmilik" value="${line.hakmilik.idHakmilik}"/>${line.hakmilik.idHakmilik}
                                </s:link>
                            </display:column>
                            <display:column property="hakmilik.noLot" title="No Lot/No PT" />
                            <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                            <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                            <display:column title="Luas Sebenar"><fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                            <display:column title="Luas Yang Diperlukan"><fmt:formatNumber pattern="#,##0.00" value="${line.luasTerlibat}"/>&nbsp;${line.kodUnitLuas.nama}</display:column>
                        </display:table>
                    </div>
                </fieldset>

                <fieldset class="aras1" id="locate">
                    <legend>
                        Kegunaan Tanah (Sila Klik ID Hakmilik untuk paparan kegunaan tanah)
                    </legend>
                    
                    <c:if test="${actionBean.idHakmilik != null}"> 
                        <table  name="${actionBean.permohonanLaporanBangunanPList}"  cellpadding="0" cellspacing="0"
                                id="line">
                            <tr>
                                <td><label>Jenis Kegunaan Tanah :</label></td>
                                <td>
                                    <s:radio name="jenisKegunaan" id="jenisKegunaan" value="B" onclick="showBangunan();"/>&nbsp;Bangunan
                                    <s:radio name="jenisKegunaan" id="jenisKegunaan" value="P" onclick="showPertanian();"/>&nbsp;Pertanian
                                    <s:radio name="jenisKegunaan" id="jenisKegunaan" value="U" onclick="showPerusahaan();"/>&nbsp;Perusahaan
                                </td>
                            </tr>
                        </table>
                    </c:if>
                    <div class="content" align="center" id="pertanian" style="display:none" >
                        <table class="tablecloth" align="center">
                            <th colspan="2">Pertanian</th>
                            <tr>
                                <td>i) Jenis Pertanian  </td>
                                <td><s:text id="usahaTanam" name="laporanTanah.usahaTanam" /></td>
                            </tr>
                            <tr>
                                <td>ii) Luas yang ditanam (meter persegi) </td>
                                <td><s:text name="usahaLuas" onkeyup="validateNumber(this,this.value);"/></td>
                            </tr>
                            <tr>
                                <td>iii) Anggaran bil. Pokok  </td>
                                <td><s:text name="usahaBilanganPokok" onkeyup="validateNumber(this,this.value);"/></td>
                            </tr>
                            <tr>
                                <td>iv) Nilaian Tanah Termasuk Tanaman(RM)  </td>
                                <td><s:text name="usahaHarga" onkeyup="validateNumber(this,this.value);"/></td>
                            </tr>
                        </table>
                        <br>
                        <s:button name="simpanPertanian" id="save" value="Simpan" class="btn" onclick="semak();doSubmit(this.form, this.name, 'page_div')"/>
                        <br><br>
                        <p align="left"><label>Bangunan</label></p>
                        <br><br>
                        <display:table class="tablecloth" name="${actionBean.permohonanLaporanBangunanPList}" pagesize="5" cellpadding="0" cellspacing="0"
                                       requestURI="/pengambilan/laporanTanahPengambilan"  id="line">
                            <display:column title="No" sortable="true">${line_rowNum}</display:column>
                            <display:column title="Jenis Bangunan">
                                <c:if test="${line.jenisBangunan eq 'KK'}">
                                    kekal
                                </c:if>
                                <c:if test="${line.jenisBangunan eq 'SK'}">
                                    separuh kekal
                                </c:if>
                                <c:if test="${line.jenisBangunan eq 'SM'}">
                                    sementara
                                </c:if>
                            </display:column>
                            <display:column title="Ukuran Bangunan">
                                <fmt:formatNumber value="${line.ukuran}"/> meter persegi
                            </display:column>
                            <display:column property="tahunDibina" title="Tahun Dibina" />
                            <display:column title="Nilai Bangunan">
                                RM <fmt:formatNumber pattern="#,##0.00" value="${line.nilai}"/>
                            </display:column>
                            <display:column property="namaPemunya" title="Pemilik" />
                            <display:column property="namaKetua" title="Ketua Keluarga Yang Mendiami"/>
                            <display:column title="Status">
                                <c:if test="${line.jenisPendudukan.kod eq 'TT'}">
                                    Pemilik
                                </c:if>
                                <c:if test="${line.jenisPendudukan.kod eq 'TS'}">
                                    Pemilik dan Penyewa Bangunan
                                </c:if>
                                <c:if test="${line.jenisPendudukan.kod eq 'SS'}">
                                    Penyewa Tanah dan Bangunan
                                </c:if>
                            </display:column>
                            <display:column title="Kemaskini">
                                <div align="center">
                                    <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="<%--alert('edit'+${line.idLaporBangunan});--%>editLaporanBangunan('${line.idLaporBangunan}');"/>
                                </div>
                            </display:column>
                            <display:column title="Hapus">
                                <div align="center">
                                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeBangunan('${line.idLaporBangunan}');" />
                                </div>
                            </display:column>
                        </display:table>
                        <br>
                        <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahBangunan('P');"/>

                    </div>

                    <div class="content" align="center" id="bangunan" style="display:none" >
                        <display:table class="tablecloth" name="${actionBean.permohonanLaporanBangunanList}" pagesize="5" cellpadding="0" cellspacing="0"
                                       requestURI="/pengambilan/laporanTanahPengambilan"  id="line">
                            <display:column title="No" sortable="true">${line_rowNum}</display:column>
                            <display:column title="Jenis Bangunan">
                                <c:if test="${line.jenisBangunan eq 'KK'}">
                                    kekal
                                </c:if>
                                <c:if test="${line.jenisBangunan eq 'SK'}">
                                    separuh kekal
                                </c:if>
                                <c:if test="${line.jenisBangunan eq 'SM'}">
                                    sementara
                                </c:if>
                            </display:column>
                            <display:column title="Ukuran Bangunan">
                                <fmt:formatNumber value="${line.ukuran}"/> meter persegi
                            </display:column>
                            <display:column property="tahunDibina" title="Tahun Dibina" />
                            <display:column title="Nilai Bangunan">
                                RM <fmt:formatNumber pattern="#,##0.00" value="${line.nilai}"/>
                            </display:column>
                            <display:column property="namaPemunya" title="Pemilik" />
                            <display:column property="namaKetua" title="Ketua Keluarga Yang Mendiami"/>
                            <display:column title="Status">
                                <c:if test="${line.jenisPendudukan.kod eq 'TT'}">
                                    Pemilik
                                </c:if>
                                <c:if test="${line.jenisPendudukan.kod eq 'TS'}">
                                    Pemilik dan Penyewa Bangunan
                                </c:if>
                                <c:if test="${line.jenisPendudukan.kod eq 'SS'}">
                                    Penyewa Tanah dan Bangunan
                                </c:if>
                            </display:column>
                            <display:column title="Kemaskini">
                                <div align="center">
                                    <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="<%--alert('edit'+${line.idLaporBangunan});--%>editLaporanBangunan('${line.idLaporBangunan}');"/>
                                </div>
                            </display:column>
                            <display:column title="Hapus">
                                <div align="center">
                                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeBangunan('${line.idLaporBangunan}');" />
                                </div>
                            </display:column>
                        </display:table>
                        <br>
                        <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahBangunan('B');"/>
                    </div>

                    <div class="content" align="center" id="perusahaan" style="display:none"> </div>
                        <display:table class="tablecloth" name="${actionBean.permohonanLaporanBangunanPUList}" pagesize="5" cellpadding="0" cellspacing="0"
                                       requestURI="/pengambilan/laporanTanahPengambilan"  id="line">
                            <display:column title="No" sortable="true">${line_rowNum}</display:column>
                            <display:column title="Jenis Bangunan">
                                <c:if test="${line.jenisBangunan eq 'KK'}">
                                    kekal
                                </c:if>
                                <c:if test="${line.jenisBangunan eq 'SK'}">
                                    separuh kekal
                                </c:if>
                                <c:if test="${line.jenisBangunan eq 'SM'}">
                                    sementara
                                </c:if>
                            </display:column>
                            <display:column title="Ukuran Bangunan">
                                <fmt:formatNumber value="${line.ukuran}"/> meter persegi
                            </display:column>
                            <display:column property="tahunDibina" title="Tahun Dibina" />
                            <display:column title="Nilai Bangunan">
                                RM <fmt:formatNumber pattern="#,##0.00" value="${line.nilai}"/>
                            </display:column>
                            <display:column property="namaPemunya" title="Pemilik" />
                            <display:column property="namaKetua" title="Ketua Keluarga Yang Mendiami"/>
                            <display:column title="Status">
                                <c:if test="${line.jenisPendudukan.kod eq 'TT'}">
                                    Pemilik
                                </c:if>
                                <c:if test="${line.jenisPendudukan.kod eq 'TS'}">
                                    Pemilik dan Penyewa Bangunan
                                </c:if>
                                <c:if test="${line.jenisPendudukan.kod eq 'SS'}">
                                    Penyewa Tanah dan Bangunan
                                </c:if>
                            </display:column>
                            <display:column title="Kemaskini">
                                <div align="center">
                                    <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="alert('edit'+${line.idLaporBangunan});editLaporanBangunan('${line.idLaporBangunan}');"/>
                                </div>
                            </display:column>
                            <display:column title="Hapus">
                                <div align="center">
                                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeBangunan('${line.idLaporBangunan}');" />
                                </div>
                            </display:column>
                        </display:table>
                        <br>
                        <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahBangunan('PU');"/>
                        </fieldset>

                        <c:if test="${actionBean.hakmilik ne null}">
                            <s:hidden name="laporanTanah.idLaporan"/>
                            <s:hidden name="idHakmilik" value="${actionBean.hakmilik.idHakmilik}"/>
                            <fieldset class="aras1">
                                <legend>Maklumat Asas Tanah</legend>
                                <br/><br/>
                                <table align="center">
                                    <tr>
                                        <td><label>Syarat Nyata Tanah :</label></td>
                                        <td>
                                            <c:if test="${actionBean.hakmilik.syaratNyata.syarat ne null}"> ${actionBean.hakmilik.syaratNyata.syarat}&nbsp; </c:if>
                                            <c:if test="${actionBean.hakmilik.syaratNyata.syarat eq null}"> Tiada</c:if>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td><label>Sekatan kepentingan :</label></td>
                                            <td>
                                            <c:if test="${actionBean.hakmilik.sekatanKepentingan.sekatan ne null}"> ${actionBean.hakmilik.sekatanKepentingan.sekatan}&nbsp; </c:if>
                                            <c:if test="${actionBean.hakmilik.sekatanKepentingan.sekatan eq null}"> Tiada</c:if>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td><label>Bebanan :</label></td>
                                            <td>
                                            <c:if test="${actionBean.hakmilik.kodTanah.nama ne null}"> ${actionBean.hakmilik.kodTanah.nama}&nbsp; </c:if>
                                            <c:if test="${actionBean.hakmilik.kodTanah.nama eq null}"> Tiada</c:if>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td><label>Tarikh Pindah Milik Terakhir :</label></td>
                                            <td>
                                            <c:if test="${actionBean.hakmilikUrusan.kodUrusan.kod eq 'PMT'}"><s:format formatPattern="dd/MM/yyyy" value="${actionBean.hakmilikUrusan.infoAudit.tarikhMasuk}"/>&nbsp;</c:if>
                                            <c:if test="${actionBean.hakmilikUrusan.kodUrusan.kod ne 'PMT'}"> Tiada</c:if>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td><label>Pemilik Berdaftar :</label></td>
                                            <td>
                                            <c:forEach items="${actionBean.hakmilik.senaraiPihakBerkepentingan}" var="line">
                                                <c:if test="${line.aktif eq 'Y'}">
                                                    ${line.pihak.nama}<br/>
                                                    <c:set var="berdaftar" value="Y"/>
                                                </c:if>
                                            </c:forEach>
                                            <c:if test="${berdaftar ne 'Y'}"> Tiada</c:if>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td><label>Pengambilan balik yang lalu terhadap tanah ini :</label></td>
                                            <td>
                                            <c:if test="${actionBean.permohonan.permohonanSebelum.idPermohonan ne null}"> ${actionBean.permohonan.permohonanSebelum.idPermohonan}&nbsp; </c:if>
                                            <c:if test="${actionBean.permohonan.permohonanSebelum.idPermohonan eq null}"> Tiada</c:if>
                                            </td>
                                        </tr>
                                    </table>
                                    <br />

                                </fieldset>

                            </div>
                            <div class="subtitle">
                                <fieldset class="aras1">
                                    <legend>Laporan Tanah</legend>
                                    <table>
                                        <tr>
                                            <td><label>Keadaan Tanah :</label></td>
                                            <td>
                                            <s:textarea name="laporanTanah.keadaanTanah" cols="30" rows="3" id="keadaanTanah" class="normal_text"/>
                                        </td>
                                    </tr>
                                </table>
                                <div class="content" align="center">
                                    <table class="tablecloth">
                                        <tr>
                                            <th>&nbsp;</th><th>Nombor Lot</th><th>Kegunaan</th><th>Imej</th>
                                            <%--<th>Gambar</th>--%>
                                        </tr>
                                        <tr>
                                            <th>
                                                Utara
                                            </th>
                                            <td>
                                                <s:text name="laporanTanah.sempadanUtaraNoLot"/>
                                            </td>
                                            <td>
                                                <s:text name="laporanTanah.sempadanUtaraKegunaan" class="normal_text"/>
                                            </td>
                                              <td>
                                                 <s:select name="utaraImej" style="width:300px;" id="utaraImej" onchange="doSetDokumenUtara();">
                                                     <s:option value="">Sila pilih imej untuk dipaparkan</s:option>
                                                     <s:options-collection collection="${actionBean.utaraImejLaporanList}" label="catatan" value="dokumen.idDokumen"/>
                                                 </s:select>&nbsp;
                                                 <s:hidden name="idDokumen" id="idDokumenU" />
                                                 <s:button name="uploadDoc" id="display" value="Tambah" class="btn" onclick="uploadForm('${actionBean.hakmilik.idHakmilik}','U');return false;"/>
                                              </td>
                                        </tr>
                                        <tr>
                                            <th>
                                                Selatan
                                            </th>
                                            <td>
                                                <s:text name="laporanTanah.sempadanSelatanNoLot"/>
                                            </td>
                                            <td>
                                                <s:text name="laporanTanah.sempadanSelatanKegunaan" class="normal_text"/>
                                            </td>
                                              <td>
                                                  <s:select name="selatanImej" style="width:300px;" id="selatanImej" onchange="doSetDokumenSelatan();">
                                                     <s:option value="">Sila pilih imej untuk dipaparkan</s:option>
                                                     <s:options-collection collection="${actionBean.selatanImejLaporanList}" label="catatan" value="dokumen.idDokumen"/>
                                                 </s:select>&nbsp;
                                                 <s:hidden name="idDokumen" id="idDokumenS" />
                                                 <s:button name="uploadDoc" id="display" value="Tambah" class="btn" onclick="uploadForm('${actionBean.hakmilik.idHakmilik}','S');return false;"/>
                                              </td>
                                        </tr>
                                        <tr>
                                            <th>
                                                Timur
                                            </th>
                                            <td>
                                                <s:text name="laporanTanah.sempadanTimurNoLot"/>
                                            </td>
                                            <td>
                                                <s:text name="laporanTanah.sempadanTimurKegunaan" class="normal_text"/>
                                            </td>
                                            <td>
                                                <s:select name="timurImej" style="width:300px;" id="timurImej" onchange="doSetDokumenTimur();">
                                                   <s:option value="">Sila pilih imej untuk dipaparkan</s:option>
                                                   <s:options-collection collection="${actionBean.timurImejLaporanList}" label="catatan" value="dokumen.idDokumen"/>
                                               </s:select>&nbsp;
                                               <s:hidden name="idDokumen" id="idDokumenT" />
                                               <s:button name="uploadDoc" id="display" value="Tambah" class="btn" onclick="uploadForm('${actionBean.hakmilik.idHakmilik}','T');return false;"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>
                                                Barat
                                            </th>
                                            <td>
                                                <s:text name="laporanTanah.sempadanBaratNoLot"/>
                                            </td>
                                            <td>
                                                <s:text name="laporanTanah.sempadanBaratKegunaan" class="normal_text"/>
                                            </td>
                                            <td>
                                                <s:select name="baratImej" style="width:300px;" id="baratImej" onchange="doSetDokumenBarat();">
                                                   <s:option value="">Sila pilih imej untuk dipaparkan</s:option>
                                                   <s:options-collection collection="${actionBean.baratImejLaporanList}" label="catatan" value="dokumen.idDokumen"/>
                                               </s:select>&nbsp;
                                               <s:hidden name="idDokumen" id="idDokumenB" />
                                               <s:button name="uploadDoc" id="display" value="Tambah" class="btn" onclick="uploadForm('${actionBean.hakmilik.idHakmilik}','B');return false;"/>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                                <br>
                                <table>
                                    <tr>
                                        <td><label> Permohonan Pecah Sempadan/Tukar Syarat : </label></td>
                                        <td>
                                            <s:radio name="adaPecahSempadan" value="Y" />&nbsp;ada
                                            <s:radio name="adaPecahSempadan" value="T" />&nbsp;tiada
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><label>Tanah mempunyai jalan/laluan keluar masuk : </label></td>
                                        <td>
                                            <s:radio name="adaJalanMasuk" value="Y" />&nbsp;ada
                                            <s:radio name="adaJalanMasuk" value="T" />&nbsp;tiada
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><label>Jenis Jalan/Laluan : </label></td>
                                        <td>
                                            <s:select name="laporanTanah.catatanJalanMasuk">
                                                <s:option value="">--Sila Pilih--</s:option>
                                                <s:option value="rizab jalan">rizab jalan</s:option>
                                                <s:option value="laluan pemungut">laluan pemungut</s:option>
                                                <s:option value="jalan kampung">jalan kampung</s:option>
                                            </s:select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><label>Ulasan Penolong Pegawai Tanah dengan Pihak berkepentingan(Tidak Berdaftar) :</label></td>
                                        <td>
                                            <s:textarea name="ulasan" cols="30" rows="3" id="penolongPegawaiTanah" class="normal_text"/>
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </div>
                    </c:if>

            <%--</div>--%>
                        <div class="subtitle">
                <fieldset class="aras1">

                    <legend>Ulasan Ketua Penolong Pegawai Tanah dan Penolong Pegawai Tanah</legend>
                    <%-- <c:if test="${edit}">--%>
                    <br>
                    <br>
                    <%--<p><label>--%>&nbsp;&nbsp;&nbsp;&nbsp;Penolong Pegawai Tanah <%--</label></p>--%>

                    <div class="content" align="center">
                        <table>
                            <tr>
                                <td>Ulasan mengenai keluasan tanah-tanah yang <br>hendak dibuat pengambilan samada luasnya <br>mengikut pelan yang dikemukakan pemohon <br>atau berbeza.Jika berbeza, berikan alasan-alasan
                                </td>
                                <td>
                                    :
                                </td>
                                <td>
                                    <s:textarea name="ulasan1" cols="50" rows="5" class="normal_text"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Ulasan samada terdapat masalah sosial atau <br>tidak akibat pengambilan tersebut.Jika ada,<br>berikan syor
                                </td>
                                <td>
                                    :
                                </td>
                                <td>
                                    <s:textarea name="ulasan2" cols="50" rows="5" class="normal_text"/>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <br>
                    <br>
                    <%--<p>  <label>--%>&nbsp;&nbsp;&nbsp;&nbsp;Ketua Penolong Pegawai Tanah<%--</label></p>--%>
                    <br>
                    <br>
                    <div class="content" align="center">
                        <table>
                            <tr>
                                <td>Ulasan
                                </td>
                                <td>
                                    :
                                </td>
                                <td>
                                    <s:textarea name="ulasan3" cols="50" rows="5" class="normal_text"/>
                                </td>
                            </tr>
                        </table>

                    </div><p>
                        <label>&nbsp;</label>
                        <s:button name="simpanLaporanTanah" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>

                    </p>
                    <%--     </c:if>--%>
                    <%--<c:if test="${!edit}">
                        <p>
                            <label>Ulasan :</label>
                            <c:if test="${actionBean.fasaPermohonan.ulasan ne null}"> ${actionBean.fasaPermohonan.ulasan}&nbsp; </c:if>
                            <c:if test="${actionBean.fasaPermohonan.ulasan eq null}"> Tiada Data </c:if>
                        </p>
                    </c:if>--%>

                </fieldset>
            </div>

        </div>
    </c:if>
    <c:if test="${actionBean.view eq 'true'}">
        <div id="hakmilik_details">
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>Perihal Tanah</legend>
                    <br/>
                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.permohonan.senaraiHakmilik}" pagesize="5" cellpadding="0" cellspacing="0"
                                       requestURI="/pengambilan/laporanTanahPengambilan" id="line">
                            <display:column title="No" sortable="true">${line_rowNum}</display:column>
                            <display:column title="ID Hakmilik">
                                <s:link beanclass="etanah.view.stripes.pengambilan.LaporanTanahPengambilanActionBean"
                                        event="hakmilikDetails2" onclick="return ajaxLink(this, '#hakmilik_details');" >
                                    <s:param name="view" value="${actionBean.view}"/>
                                    <s:param name="idHakmilik" value="${line.hakmilik.idHakmilik}"/>${line.hakmilik.idHakmilik}
                                </s:link>
                            </display:column>
                            <display:column property="hakmilik.noLot" title="No Lot/No PT" />
                            <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                            <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                            <display:column title="Luas Sebenar"><fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                            <display:column title="Luas Yang Diperlukan"><fmt:formatNumber pattern="#,##0.00" value="${line.luasTerlibat}"/>&nbsp;${line.kodUnitLuas.nama}</display:column>
                        </display:table>
                    </div>
                </fieldset>

                <c:if test="${actionBean.hakmilik ne null}">
                    <s:hidden name="laporanTanah.idLaporan"/>
                    <s:hidden name="idHakmilik" value="${actionBean.hakmilik.idHakmilik}"/>
                    <fieldset class="aras1">
                        <legend>Maklumat Asas Tanah</legend>
                        <br/><br/>
                        <table align="center">
                            <tr>
                                <td><label>Syarat Nyata Tanah :</label></td>
                                <td>
                                    <c:if test="${actionBean.hakmilik.syaratNyata.syarat ne null}"> ${actionBean.hakmilik.syaratNyata.syarat}&nbsp; </c:if>
                                    <c:if test="${actionBean.hakmilik.syaratNyata.syarat eq null}"> Tiada</c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <td><label>Sekatan kepentingan :</label></td>
                                    <td>
                                    <c:if test="${actionBean.hakmilik.sekatanKepentingan.sekatan ne null}"> ${actionBean.hakmilik.sekatanKepentingan.sekatan}&nbsp; </c:if>
                                    <c:if test="${actionBean.hakmilik.sekatanKepentingan.sekatan eq null}"> Tiada</c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <td><label>Bebanan :</label></td>
                                    <td>
                                    <c:if test="${actionBean.hakmilik.kodTanah.nama ne null}"> ${actionBean.hakmilik.kodTanah.nama}&nbsp; </c:if>
                                    <c:if test="${actionBean.hakmilik.kodTanah.nama eq null}"> Tiada</c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <td><label>Tarikh Pindah Milik Terakhir :</label></td>
                                    <td>
                                    <c:if test="${actionBean.hakmilikUrusan.kodUrusan.kod eq 'PMT'}"><s:format formatPattern="dd/MM/yyyy" value="${actionBean.hakmilikUrusan.infoAudit.tarikhMasuk}"/>&nbsp;</c:if>
                                    <c:if test="${actionBean.hakmilikUrusan.kodUrusan.kod ne 'PMT'}"> Tiada</c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <td><label>Pemilik Berdaftar :</label></td>
                                    <td>
                                    <c:forEach items="${actionBean.hakmilik.senaraiPihakBerkepentingan}" var="line">
                                        <c:if test="${line.aktif eq 'Y'}">
                                            ${line.pihak.nama}<br/>
                                            <c:set var="berdaftar" value="Y"/>
                                        </c:if>
                                    </c:forEach>
                                    <c:if test="${berdaftar ne 'Y'}"> Tiada</c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <td><label>Pengambilan balik yang lalu terhadap tanah ini :</label></td>
                                    <td>
                                    <c:if test="${actionBean.permohonan.permohonanSebelum.idPermohonan ne null}"> ${actionBean.permohonan.permohonanSebelum.idPermohonan}&nbsp; </c:if>
                                    <c:if test="${actionBean.permohonan.permohonanSebelum.idPermohonan eq null}"> Tiada</c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <td><label>Keadaan Tanah :</label></td>
                                    <td>
                                    ${actionBean.laporanTanah.keadaanTanah}
                                    <%--<s:textarea name="laporanTanah.keadaanTanah" cols="30" rows="3"/>--%>
                                </td>
                            </tr>
                            <tr>
                                <td><label>Jenis Kegunaan :</label></td>
                                <td>
                                    <c:if test="${actionBean.jenisKegunaan eq 'B'}"> Bangunan</c:if>
                                    <c:if test="${actionBean.jenisKegunaan eq 'P'}"> Pertanian</c:if>
                                    <c:if test="${actionBean.jenisKegunaan eq 'U'}"> Perusahaan</c:if>
                                    <%--<s:radio name="jenisKegunaan" id="jenisKegunaan" value="B" onclick="showBangunan();"/>&nbsp;Bangunan
                                    <s:radio name="jenisKegunaan" id="jenisKegunaan" value="P" onclick="showPertanian();"/>&nbsp;Pertanian
                                    <s:radio name="jenisKegunaan" id="jenisKegunaan" value="U" onclick="showPerusahaan();"/>&nbsp;Perusahaan--%>
                                </td>
                            </tr>
                        </table>
                        <br /><br />

                        <div class="content" align="center" id="pertanian" style="display:none" >
                            <table class="tablecloth" align="center">
                                <th colspan="2">Pertanian</th>
                                <tr>
                                    <td>i) Jenis Pertanian  </td>
                                    <td>${actionBean.laporanTanah.usahaTanam}<%--<s:text name="laporanTanah.usahaTanam" />--%></td>
                                </tr>
                                <tr>
                                    <td>ii) Luas yang ditanam (meter persegi) </td>
                                    <td>${actionBean.usahaLuas}<%--<s:text name="usahaLuas" onkeyup="validateNumber(this,this.value);"/>--%></td>
                                </tr>
                                <tr>
                                    <td>iii) Anggaran bil. Pokok  </td>
                                    <td>${actionBean.usahaBilanganPokok}<%--<s:text name="usahaBilanganPokok" onkeyup="validateNumber(this,this.value);"/>--%></td>
                                </tr>
                                <tr>
                                    <td>iv) Nilaian Tanah Termasuk Tanaman(RM)  </td>
                                    <td>${actionBean.usahaHarga}<%--<s:text name="usahaHarga" onkeyup="validateNumber(this,this.value);"/>--%></td>
                                </tr>
                            </table>

                            <display:table class="tablecloth" name="${actionBean.permohonanLaporanBangunanPList}" pagesize="5" cellpadding="0" cellspacing="0"
                                           requestURI="/pengambilan/laporanTanahPengambilan"  id="line">
                                <display:column title="No" sortable="true">${line_rowNum}</display:column>
                                <display:column title="Jenis Bangunan">
                                    <c:if test="${line.jenisBangunan eq 'KK'}">
                                        kekal
                                    </c:if>
                                    <c:if test="${line.jenisBangunan eq 'SK'}">
                                        separuh kekal
                                    </c:if>
                                    <c:if test="${line.jenisBangunan eq 'SM'}">
                                        sementara
                                    </c:if>
                                </display:column>
                                <display:column title="Ukuran Bangunan">
                                    <fmt:formatNumber value="${line.ukuran}"/> meter persegi
                                </display:column>
                                <display:column property="tahunDibina" title="Tahun Dibina" />
                                <display:column title="Nilai Bangunan">
                                    RM <fmt:formatNumber pattern="#,##0.00" value="${line.nilai}"/>
                                </display:column>
                                <display:column property="namaPemunya" title="Pemilik" />
                                <display:column property="namaKetua" title="Ketua Keluarga Yang Mendiami"/>
                                <display:column title="Status">
                                    <c:if test="${line.jenisPendudukan.kod eq 'TT'}">
                                        Pemilik
                                    </c:if>
                                    <c:if test="${line.jenisPendudukan.kod eq 'TS'}">
                                        Pemilik dan Penyewa Bangunan
                                    </c:if>
                                    <c:if test="${line.jenisPendudukan.kod eq 'SS'}">
                                        Penyewa Tanah dan Bangunan
                                    </c:if>
                                </display:column>
                            </display:table>
                            <%--<s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahBangunan('P');"/>--%>
                        </div>

                        <div class="content" align="center" id="bangunan" style="display:none" >
                            <display:table class="tablecloth" name="${actionBean.permohonanLaporanBangunanList}" pagesize="5" cellpadding="0" cellspacing="0"
                                           requestURI="/pengambilan/laporanTanahPengambilan"  id="line">
                                <display:column title="No" sortable="true">${line_rowNum}</display:column>
                                <display:column title="Jenis Bangunan">
                                    <c:if test="${line.jenisBangunan eq 'KK'}">
                                        kekal
                                    </c:if>
                                    <c:if test="${line.jenisBangunan eq 'SK'}">
                                        separuh kekal
                                    </c:if>
                                    <c:if test="${line.jenisBangunan eq 'SM'}">
                                        sementara
                                    </c:if>
                                </display:column>
                                <display:column title="Ukuran Bangunan">
                                    <fmt:formatNumber value="${line.ukuran}"/> meter persegi
                                </display:column>
                                <display:column property="tahunDibina" title="Tahun Dibina" />
                                <display:column title="Nilai Bangunan">
                                    RM <fmt:formatNumber pattern="#,##0.00" value="${line.nilai}"/>
                                </display:column>
                                <display:column property="namaPemunya" title="Pemilik" />
                                <display:column property="namaKetua" title="Ketua Keluarga Yang Mendiami"/>
                                <display:column title="Status">
                                    <c:if test="${line.jenisPendudukan.kod eq 'TT'}">
                                        Pemilik
                                    </c:if>
                                    <c:if test="${line.jenisPendudukan.kod eq 'TS'}">
                                        Pemilik dan Penyewa Bangunan
                                    </c:if>
                                    <c:if test="${line.jenisPendudukan.kod eq 'SS'}">
                                        Penyewa Tanah dan Bangunan
                                    </c:if>
                                </display:column>

                            </display:table>
                            <%--<s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahBangunan('B');"/>--%>
                        </div>

                        <div class="content" align="center" id="perusahaan" style="display:none" >
                            <display:table class="tablecloth" name="${actionBean.permohonanLaporanBangunanPUList}" pagesize="5" cellpadding="0" cellspacing="0"
                                           requestURI="/pengambilan/laporanTanahPengambilan"  id="line">
                                <display:column title="No" sortable="true">${line_rowNum}</display:column>
                                <display:column title="Jenis Bangunan">
                                    <c:if test="${line.jenisBangunan eq 'KK'}">
                                        kekal
                                    </c:if>
                                    <c:if test="${line.jenisBangunan eq 'SK'}">
                                        separuh kekal
                                    </c:if>
                                    <c:if test="${line.jenisBangunan eq 'SM'}">
                                        sementara
                                    </c:if>
                                </display:column>
                                <display:column title="Ukuran Bangunan">
                                    <fmt:formatNumber value="${line.ukuran}"/> meter persegi
                                </display:column>
                                <display:column property="tahunDibina" title="Tahun Dibina" />
                                <display:column title="Nilai Bangunan">
                                    RM <fmt:formatNumber pattern="#,##0.00" value="${line.nilai}"/>
                                </display:column>
                                <display:column property="namaPemunya" title="Pemilik" />
                                <display:column property="namaKetua" title="Ketua Keluarga Yang Mendiami"/>
                                <display:column title="Status">
                                    <c:if test="${line.jenisPendudukan.kod eq 'TT'}">
                                        Pemilik
                                    </c:if>
                                    <c:if test="${line.jenisPendudukan.kod eq 'TS'}">
                                        Pemilik dan Penyewa Bangunan
                                    </c:if>
                                    <c:if test="${line.jenisPendudukan.kod eq 'SS'}">
                                        Penyewa Tanah dan Bangunan
                                    </c:if>
                                </display:column>

                            </display:table>
                            <%--<s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahBangunan('PU');"/>--%>
                        </div>
                        <div class="subtitle">
                            <fieldset class="aras1">
                                <legend>Latar belakang Tanah</legend>
                                <div class="content" align="center">
                                    <table class="tablecloth">
                                        <tr>
                                            <th>&nbsp;</th><th>Nombor Lot</th><th>Kegunaan</th><th>Gambar</th>
                                        </tr>
                                        <tr>
                                            <th>
                                                Utara
                                            </th>
                                            <td>
                                                ${actionBean.laporanTanah.sempadanUtaraNoLot}<%--<s:text name="laporanTanah.sempadanUtaraNoLot"/>--%>
                                            </td>
                                            <td>
                                                ${actionBean.laporanTanah.sempadanUtaraKegunaan}<%--<s:text name="laporanTanah.sempadanUtaraKegunaan"/>--%>
                                            </td>
                                            <td>
                                                <s:select name="utaraImej" style="width:300px;" id="utaraImej" onchange="doSetDokumenUtara();">
                                                    <s:option value="">Sila pilih imej untuk dipaparkan</s:option>
                                                    <s:options-collection collection="${actionBean.utaraImejLaporanList}" label="catatan" value="dokumen.idDokumen"/>
                                                </s:select>&nbsp;
                                                <s:hidden name="idDokumen" id="idDokumenU" />
                                                <%--<s:button name="uploadDoc" id="display" value="Tambah" class="btn" onclick="uploadForm('${actionBean.hakmilik.idHakmilik}','U');return false;"/>--%>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>
                                                Selatan
                                            </th>
                                            <td>
                                                ${actionBean.laporanTanah.sempadanSelatanNoLot}<%--<s:text name="laporanTanah.sempadanSelatanNoLot"/>--%>
                                            </td>
                                            <td>
                                                ${actionBean.laporanTanah.sempadanSelatanKegunaan}<%--<s:text name="laporanTanah.sempadanSelatanKegunaan"/>--%>
                                            </td>
                                            <td>
                                                <s:select name="selatanImej" style="width:300px;" id="selatanImej" onchange="doSetDokumenSelatan();">
                                                    <s:option value="">Sila pilih imej untuk dipaparkan</s:option>
                                                    <s:options-collection collection="${actionBean.selatanImejLaporanList}" label="catatan" value="dokumen.idDokumen"/>
                                                </s:select>&nbsp;
                                                <s:hidden name="idDokumen" id="idDokumenS" />
                                                <%--<s:button name="uploadDoc" id="display" value="Tambah" class="btn" onclick="uploadForm('${actionBean.hakmilik.idHakmilik}','S');return false;"/>--%>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>
                                                Timur
                                            </th>
                                            <td>
                                                ${actionBean.laporanTanah.sempadanTimurNoLot}<%--<s:text name="laporanTanah.sempadanTimurNoLot"/>--%>
                                            </td>
                                            <td>
                                                ${actionBean.laporanTanah.sempadanTimurKegunaan}<%--<s:text name="laporanTanah.sempadanTimurKegunaan"/>--%>
                                            </td>
                                            <td>
                                                <s:select name="timurImej" style="width:300px;" id="timurImej" onchange="doSetDokumenTimur();">
                                                    <s:option value="">Sila pilih imej untuk dipaparkan</s:option>
                                                    <s:options-collection collection="${actionBean.timurImejLaporanList}" label="catatan" value="dokumen.idDokumen"/>
                                                </s:select>&nbsp;
                                                <s:hidden name="idDokumen" id="idDokumenT" />
                                                <%--<s:button name="uploadDoc" id="display" value="Tambah" class="btn" onclick="uploadForm('${actionBean.hakmilik.idHakmilik}','T');return false;"/>--%>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>
                                                Barat
                                            </th>
                                            <td>
                                                ${actionBean.laporanTanah.sempadanBaratNoLot}<%--<s:text name="laporanTanah.sempadanBaratNoLot"/>--%>
                                            </td>
                                            <td>
                                                ${actionBean.laporanTanah.sempadanBaratKegunaan}<%--<s:text name="laporanTanah.sempadanBaratKegunaan"/>--%>
                                            </td>
                                            <td>
                                                <s:select name="baratImej" style="width:300px;" id="baratImej" onchange="doSetDokumenBarat();">
                                                    <s:option value="">Sila pilih imej untuk dipaparkan</s:option>
                                                    <s:options-collection collection="${actionBean.baratImejLaporanList}" label="catatan" value="dokumen.idDokumen"/>
                                                </s:select>&nbsp;
                                                <s:hidden name="idDokumen" id="idDokumenB" />
                                                <%--<s:button name="uploadDoc" id="display" value="Tambah" class="btn" onclick="uploadForm('${actionBean.hakmilik.idHakmilik}','B');return false;"/>--%>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </fieldset>
                        </div>

                        <table >
                            <tr>
                                <td><label> Permohonan Pecah Sempadan/Tukar Syarat : </label></td>
                                <td>
                                    <c:if test="${actionBean.adaPecahSempadan eq 'Y'}"> ada</c:if>
                                    <c:if test="${actionBean.adaPecahSempadan eq 'T'}"> tiada</c:if>
                                    <%--<s:radio name="adaPecahSempadan" value="Y" />&nbsp;ya
                                    <s:radio name="adaPecahSempadan" value="T" />&nbsp;tidak--%>
                                </td>
                            </tr>
                            <tr>
                                <td><label>Tanah mempunyai jalan/laluan keluar masuk : </label></td>
                                <td>
                                    <c:if test="${actionBean.adaJalanMasuk eq 'Y'}"> ada</c:if>
                                    <c:if test="${actionBean.adaJalanMasuk eq 'T'}"> tiada</c:if>
                                    <%--<s:radio name="adaJalanMasuk" value="Y" />&nbsp;ya
                                    <s:radio name="adaJalanMasuk" value="T" />&nbsp;tidak--%>
                                </td>
                            </tr>
                            <tr>
                                <td><label>Jenis Jalan/Laluan : </label></td>
                                <td>
                                    ${actionBean.laporanTanah.catatanJalanMasuk}
                                    <%--<s:select name="laporanTanah.catatanJalanMasuk">
                                       <s:option value="">--Sila Pilih--</s:option>
                                       <s:option value="rizab jalan">rizab jalan</s:option>
                                       <s:option value="laluan pemungut">laluan pemungut</s:option>
                                       <s:option value="jalan kampung">jalan kampung</s:option>
                                   </s:select>--%>
                                </td>
                            </tr>
                            <tr>
                                <td><label>Ulasan Penolong Pegawai Tanah dengan Pihak berkepentingan(Tidak Berdaftar) :</label></td>
                                <td>
                                    ${actionBean.ulasan}<%--<s:textarea name="ulasan" cols="30" rows="3"/>--%>
                                </td>
                            </tr>
                        </table>


                        <br/><br/>
                    </c:if>
                </fieldset>
            </div>
            <div class="subtitle">
                <fieldset class="aras1">

                    <legend>Ulasan Ketua Penolong Pegawai Tanah dan Penolong Pegawai Tanah</legend>
                    <%-- <c:if test="${edit}">--%>
                    <br>
                    <br>
                    <%--<p><label>--%>&nbsp;&nbsp;&nbsp;&nbsp;Penolong Pegawai Tanah <%--</label></p>--%>

                    <div class="content" align="center">
                        <table>
                            <tr>
                                <td><b>Ulasan mengenai keluasan tanah-tanah yang <br>hendak dibuat pengambilan samada luasnya <br>mengikutpelan yang dikemukakan pemohon <br>atau berbeza.Jika berbeza, berikan alasan-alasan</b>
                                </td>
                                <td>
                                    :
                                </td>
                                <td>
                                    ${actionBean.ulasan1}<%--<s:textarea name="ulasan1" cols="50" rows="5"/>--%>
                                </td>
                            </tr>
                            <br>
                            <br>
                            <tr>
                                <td>
                                    <b>Ulasan samada terdapat masalah sosial atau <br>tidak akibat pengambilan tersebut.Jika ada,<br>berikan syor</b>
                                </td>
                                <td>
                                    :
                                </td>
                                <td>
                                    ${actionBean.ulasan2}<%--<s:textarea name="ulasan2" cols="50" rows="5"/>--%>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <br>
                    <br>
                    <%--<p>  <label>--%>&nbsp;&nbsp;&nbsp;&nbsp;Ketua Penolong Pegawai Tanah<%--</label></p>--%>
                    <br>
                    <br>
                    <div class="content" align="center">
                        <table>
                            <tr>
                                <td><b>Ulasan</b>
                                </td>
                                <td>
                                    :
                                </td>
                                <td>
                                    ${actionBean.ulasan3}<%--<s:textarea name="ulasan3" cols="50" rows="5"/>--%>
                                </td>
                            </tr>
                        </table>

                    </div><p>
                        <label>&nbsp;</label>
                        <%--<s:button name="simpanLaporanTanah" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>--%>

                    </p>
                    <%--     </c:if>--%>
                    <%--<c:if test="${!edit}">
                        <p>
                            <label>Ulasan :</label>
                            <c:if test="${actionBean.fasaPermohonan.ulasan ne null}"> ${actionBean.fasaPermohonan.ulasan}&nbsp; </c:if>
                            <c:if test="${actionBean.fasaPermohonan.ulasan eq null}"> Tiada Data </c:if>
                        </p>
                    </c:if>--%>

                </fieldset>
            </div>

        </div>
    </c:if>
</s:form>


