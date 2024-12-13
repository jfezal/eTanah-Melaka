<%--
    Document   : laporan_tanah_new_n9_sblm
    Created on : 11-May-2011, 10:32:27
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


        function refreshRizab(){
            var url = '${pageContext.request.contextPath}/pengambilan/laporanTanahPengambilan_sblm?refreshpage';
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
            $.post('${pageContext.request.contextPath}/pengambilan/laporanTanahPengambilan_sblm?addLaporanImage&pandanganImej=U&idDokumen='+idDokumen+'&idHakmilik='+idHakmilik,
            function(data){
                $('#page_div').html(data);
            }, 'html');
        }

        function addImageSelatan(){
            var idHakmilik = '${actionBean.hakmilik.idHakmilik}';
            var idDokumen = document.getElementById("selatanImej").options[document.getElementById("selatanImej").selectedIndex].value;
            $.post('${pageContext.request.contextPath}/pengambilan/laporanTanahPengambilan_sblm?addLaporanImage&pandanganImej=S&idDokumen='+idDokumen+'&idHakmilik='+idHakmilik,
            function(data){
                $('#page_div').html(data);
            }, 'html');
        }

        function addImageTimur(){
            var idHakmilik = '${actionBean.hakmilik.idHakmilik}';
            var idDokumen = document.getElementById("timurImej").options[document.getElementById("timurImej").selectedIndex].value;
            $.post('${pageContext.request.contextPath}/pengambilan/laporanTanahPengambilan_sblm?addLaporanImage&pandanganImej=T&idDokumen='+idDokumen+'&idHakmilik='+idHakmilik,
            function(data){
                $('#page_div').html(data);
            }, 'html');
        }

        function addImageBarat(){
            var idHakmilik = '${actionBean.hakmilik.idHakmilik}';
            var idDokumen = document.getElementById("baratImej").options[document.getElementById("baratImej").selectedIndex].value;
            $.post('${pageContext.request.contextPath}/pengambilan/laporanTanahPengambilan_sblm?addLaporanImage&pandanganImej=B&idDokumen='+idDokumen+'&idHakmilik='+idHakmilik,
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
            window.open("${pageContext.request.contextPath}/pengambilan/laporanTanahPengambilan_sblm?tambahBangunanPopup&idHakmilik="+idHakmilik+'&kategori='+kategori, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
        }

        function ajaxLink(link, update) {
            $.get(link, function(data) {
                $(update).html(data);
                $(update).show();
            });
            return false;
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
            var url = '${pageContext.request.contextPath}/pengambilan/laporanTanahPengambilan_sblm?uploadDoc&idHakmilik='+idHakmilik+'&pandanganImej='+pandanganImej;
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
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
                var url = '${pageContext.request.contextPath}/pengambilan/laporanTanahPengambilan_sblm?hakmilikDetails&idHakmilik='+idHakmilik;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
            }
        }
</script>

<s:form beanclass="etanah.view.stripes.pengambilan.LaporanTanahPengambilanSblmActionBean">
    <s:messages/>
    <div id="hakmilik_details">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Senarai Hakmilik Terlibat</legend>
                <br/>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.permohonan.senaraiHakmilik}" pagesize="5" cellpadding="0" cellspacing="0"
                                   requestURI="/pengambilan/laporanTanahPengambilan_sblm" id="line">
                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                        <display:column title="ID Hakmilik">
                            <s:link beanclass="etanah.view.stripes.pengambilan.LaporanTanahPengambilanSblmActionBean"
                                    event="hakmilikDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                                <s:param name="idHakmilik" value="${line.hakmilik.idHakmilik}"/>${line.hakmilik.idHakmilik}
                            </s:link>
                        </display:column>
                        <display:column title="No Lot/No PT" >${line.hakmilik.lot.nama} ${line.hakmilik.noLot}</display:column>
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
                <fieldset class="aras1" id="locate">
                    <legend>
                        Kegunaan Tanah
                    </legend>
                    <table align="center">
                        <tr>
                            <td><label>Jenis Kegunaan Tanah :</label></td>
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
                                <td>ii) Luas yang ditanam  </td>
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
                                       requestURI="/pengambilan/laporanTanahPengambilan_sblm"  id="line">
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
                                       requestURI="/pengambilan/laporanTanahPengambilan_sblm"  id="line">
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
                                       requestURI="/pengambilan/laporanTanahPengambilan_sblm"  id="line">
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
                </fieldset>

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
                                <c:if test="${actionBean.p.permohonanSebelum.idPermohonan ne null}"> ${actionBean.p.permohonanSebelum.idPermohonan}&nbsp; </c:if>
                                <c:if test="${actionBean.p.permohonanSebelum.idPermohonan eq null}"> Tiada</c:if>
                            </td>
                        </tr>
                    </table><br /><br />




                    <%--<s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahBangunan('PU');"/>--%>
            </div>

            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>Laporan Tanah</legend>
                    <div class="content" align="center">

                        <tr>
                            <td><label>Keadaan Tanah :</label></td>
                            <td>
                                ${actionBean.laporanTanah.keadaanTanah}
                                <%--<s:textarea name="laporanTanah.keadaanTanah" cols="30" rows="3"/>--%>
                            </td>
                        </tr><br /><br />

                        <table class="tablecloth" align="center">


                            <tr>
                                <th>&nbsp;</th><th>Nombor Lot</th><th>Kegunaan</th>
                            </tr>
                            <tr>
                                <th>
                                    Utara
                                </th>
                                <td>
                                    ${actionBean.laporanTanah.sempadanUtaraNoLot}
                                    <%--<s:text name="laporanTanah.sempadanUtaraNoLot"/>--%>
                                </td>
                                <td>
                                    ${actionBean.laporanTanah.sempadanUtaraKegunaan}
                                    <%--<s:text name="laporanTanah.sempadanUtaraKegunaan"/>--%>
                                </td>
                                <%--<td>
                                   <s:select name="utaraImej" style="width:300px;" id="utaraImej" onchange="doSetDokumenUtara();">
                                       <s:option value="">Sila pilih imej untuk dipaparkan</s:option>
                                       <s:options-collection collection="${actionBean.utaraImejLaporanList}" label="catatan" value="dokumen.idDokumen"/>
                                   </s:select>&nbsp;
                                   <s:hidden name="idDokumen" id="idDokumenU" />
                                   <s:button name="uploadDoc" id="display" value="Tambah" class="btn" onclick="uploadForm('${actionBean.hakmilik.idHakmilik}','U');return false;"/>
                                </td>--%>
                            </tr>
                            <tr>
                                <th>
                                    Selatan
                                </th>
                                <td>
                                    ${actionBean.laporanTanah.sempadanSelatanNoLot}
                                    <%--<s:text name="laporanTanah.sempadanSelatanNoLot"/>--%>
                                </td>
                                <td>
                                    ${actionBean.laporanTanah.sempadanSelatanKegunaan}
                                    <%--<s:text name="laporanTanah.sempadanSelatanKegunaan"/>--%>
                                </td>
                                <%--<td>
                                    <s:select name="selatanImej" style="width:300px;" id="selatanImej" onchange="doSetDokumenSelatan();">
                                       <s:option value="">Sila pilih imej untuk dipaparkan</s:option>
                                       <s:options-collection collection="${actionBean.selatanImejLaporanList}" label="catatan" value="dokumen.idDokumen"/>
                                   </s:select>&nbsp;
                                   <s:hidden name="idDokumen" id="idDokumenS" />
                                   <s:button name="uploadDoc" id="display" value="Tambah" class="btn" onclick="uploadForm('${actionBean.hakmilik.idHakmilik}','S');return false;"/>
                                </td>--%>
                            </tr>
                            <tr>
                                <th>
                                    Timur
                                </th>
                                <td>
                                    ${actionBean.laporanTanah.sempadanTimurNoLot}
                                    <%--<s:text name="laporanTanah.sempadanTimurNoLot"/>--%>
                                </td>
                                <td>
                                    ${actionBean.laporanTanah.sempadanTimurKegunaan}
                                    <%--<s:text name="laporanTanah.sempadanTimurKegunaan"/>--%>
                                </td>
                                <%--<td>
                                    <s:select name="timurImej" style="width:300px;" id="timurImej" onchange="doSetDokumenTimur();">
                                       <s:option value="">Sila pilih imej untuk dipaparkan</s:option>
                                       <s:options-collection collection="${actionBean.timurImejLaporanList}" label="catatan" value="dokumen.idDokumen"/>
                                   </s:select>&nbsp;
                                   <s:hidden name="idDokumen" id="idDokumenT" />
                                   <s:button name="uploadDoc" id="display" value="Tambah" class="btn" onclick="uploadForm('${actionBean.hakmilik.idHakmilik}','T');return false;"/>
                                </td>--%>
                            </tr>
                            <tr>
                                <th>
                                    Barat
                                </th>
                                <td>
                                    ${actionBean.laporanTanah.sempadanBaratNoLot}
                                    <%--<s:text name="laporanTanah.sempadanBaratNoLot"/>--%>
                                </td>
                                <td>
                                    ${actionBean.laporanTanah.sempadanBaratKegunaan}
                                    <%--<s:text name="laporanTanah.sempadanBaratKegunaan"/>--%>
                                </td>
                                <%--<td>
                                    <s:select name="baratImej" style="width:300px;" id="baratImej" onchange="doSetDokumenBarat();">
                                       <s:option value="">Sila pilih imej untuk dipaparkan</s:option>
                                       <s:options-collection collection="${actionBean.baratImejLaporanList}" label="catatan" value="dokumen.idDokumen"/>
                                   </s:select>&nbsp;
                                   <s:hidden name="idDokumen" id="idDokumenB" />
                                   <s:button name="uploadDoc" id="display" value="Tambah" class="btn" onclick="uploadForm('${actionBean.hakmilik.idHakmilik}','B');return false;"/>
                                </td>--%>
                            </tr>
                        </table>
                    </div>



                    <table>
                        <tr>
                            <td><label> Permohonan Pecah Sempadan/Tukar Syarat : </label></td>
                            <td>
                                <c:if test="${actionBean.adaPecahSempadan eq 'Y'}"> ya</c:if>
                                <c:if test="${actionBean.adaPecahSempadan eq 'T'}"> tidak</c:if>
                                <%--<s:radio name="adaPecahSempadan" value="Y" />&nbsp;ya--%>
                                <%--<s:radio name="adaPecahSempadan" value="T" />&nbsp;tidak--%>
                            </td>
                        </tr>
                        <tr>
                            <td><label>Tanah mempunyai jalan/laluan keluar masuk : </label></td>
                            <td>
                                <c:if test="${actionBean.adaJalanMasuk eq 'Y'}"> ya</c:if>
                                <c:if test="${actionBean.adaJalanMasuk eq 'T'}"> tidak</c:if>
                                <%--<s:radio name="adaJalanMasuk" value="Y" />&nbsp;ya--%>
                                <%--<s:radio name="adaJalanMasuk" value="T" />&nbsp;tidak--%>
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
                            <td><label>Ulasan Penolong Pegawai Tanah dengan Pihak Berkepentingan (Tidak Berdaftar) :</label></td>
                            <td>
                                ${actionBean.ulasan}
                                <%--<s:textarea name="ulasan" cols="30" rows="3"/>--%>
                            </td>
                        </tr>
                    </table><br /><br /><br />
                </fieldset>
            </div>
            <%--<br/><br/>--%>
        </c:if>
        <%--</fieldset>--%>
    </div>
    <%--<div class="subtitle">--%>
        <fieldset class="aras1">
            <legend>Ulasan Ketua Penolong Pegawai Tanah dan Penolong Pegawai Tanah</legend>
            <%-- <c:if test="${edit}">--%>
            <br>
            <br>
            <%--<p><label>--%>&nbsp;&nbsp;&nbsp;&nbsp;Penolong Pegawai Tanah <%--</label></p>--%>

            <div class="content" align="center">
                <table>
                    <%--<tr>--%>
                    <td>Ulasan mengenai keluasan tanah-tanah yang <br>hendak dibuat pengambilan samada luasnya <br>mengikut pelan yang dikemukakan pemohon <br>atau berbeza. Jika berbeza, berikan alasan-alasan
                    </td>
                    <td>
                        :
                    </td>
                    <td>
                        ${actionBean.ulasan1}
                        <%--<s:textarea name="ulasan1" cols="50" rows="5"/>--%>
                    </td>
                    <%--</tr>--%>
                    <%--<tr>--%>
                </table><br /><br />
                <table>
                    <td>
                        Ulasan samada terdapat masalah sosial atau <br>tidak akibat pengambilan tersebut. Jika ada,<br> berikan syor
                    </td>
                    <td>
                        :
                    </td>
                    <td>
                        ${actionBean.ulasan2}
                        <%--<s:textarea name="ulasan2" cols="50" rows="5"/>--%>
                    </td>
                    <%--</tr>--%>
                </table>
            </div>
            <br>
            <br>
            <%--<p>  <label>--%>&nbsp;&nbsp;&nbsp;&nbsp;Ketua Penolong Pegawai Kanan<%--</label></p>--%>
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
                            ${actionBean.ulasan3}
                            <%--<s:textarea name="ulasan3" cols="50" rows="5"/>--%>
                        </td>
                    </tr>
                </table>

            </div><p>
                <label>&nbsp;</label>
                <%--<s:button name="simpanLaporanTanah" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>--%>

            </p>

        </fieldset>
    <%--</div>--%>

</div>
</s:form>