<%-- 
    Document   : maklumat_tambahan
    Created on : Jun 18, 2010, 2:26:12 PM
    Author     : Rajesh
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd"> 

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script type="text/javascript">
    $(document).ready( function(){


        $("#noLitho").val=="";
    });
    function removeTanahRizab(idTanahRizabPermohonan)
    {
        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pengambilan/maklumat_tambahan?deleteTanahRizab&idTanahRizabPermohonan='
                +idTanahRizabPermohonan;
            $.get(url,
            function(data){
                $('#page_div').html(data);
                self.opener.refreshPageTanahRizab();
            },'html');
        }
    }
    function ReplaceAll(Source,stringToFind,stringToReplace){
        var temp = Source;
        var index = temp.indexOf(stringToFind);
        while(index != -1){
            temp = temp.replace(stringToFind,stringToReplace);
            index = temp.indexOf(stringToFind);

        }
    <%--alert(temp);--%>
            return temp;
        }

        function RunProgram(strUserID, strNama, strJawatan, strIDPermohonan, strStageID) {
    <%--var stageId = "g_charting_semak";--%>
            // replace " " with "_"

            strNama = ReplaceAll(strNama," ","_");
            strJawatan = ReplaceAll(strJawatan," ","_");
            strStageID = ReplaceAll(strStageID," ","_");
            alert(" user id:" +strUserID + " nama: " +strNama+ " jawatan:" +strJawatan+ " pmohonan id: " +strIDPermohonan+ " stage id: " +strStageID);
            strStageID = "g_kemaskini_hakmilik";
            alert ("stageid:" + strStageID);
            var objShell = new ActiveXObject("WScript.Shell");
            var sysVar = objShell.Environment("System");
            //objShell.run("C:\Program Files\EpiWeb\ArcMap_ActiveX_Initializer\InititializeArcMap.exe");
            //objShell.run("C:\Program Files\eTanahStrata\InititializeStrata.exe")


            objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama + " " +  strJawatan + " " + strIDPermohonan + " " + strStageID);
        }


        function removeTanahMilik(id)
        {
            if(confirm('Adakah anda pasti?')) {
                var url = '${pageContext.request.contextPath}/pengambilan/maklumat_tambahan?deleteTanahMilik&id='
                    +id;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');}
        }

        function removePermohonanTerdahulu(idMohonManual)
        {
            if(confirm('Adakah anda pasti?')) {
                var url = '${pageContext.request.contextPath}/pengambilan/maklumat_tambahan?deletePermohonanTerdahulu&idMohonManual='
                    +idMohonManual;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');}
        }


        function tambahTanahRizab(){
            window.open("${pageContext.request.contextPath}/pengambilan/maklumat_tambahan?tanahRizabPopup", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
        }

        function tambahTanahMilik(){
            window.open("${pageContext.request.contextPath}/pengambilan/maklumat_tambahan?tanahMilikPopup", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
        }

        function tambahPermohonanTerdahulu(){
            window.open("${pageContext.request.contextPath}/pengambilan/maklumat_tambahan?permohonanTerdahuluPopup", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
        }

        function refreshPageTanahRizab(){
            var url = '${pageContext.request.contextPath}/pengambilan/maklumat_tambahan?refreshpage';
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }

        //function popupTanahRizab(h){
        //var url = '${pageContext.request.contextPath}/pengambilan/maklumat_tambahan?showEditTanahRizab&idTanahRizabPermohonan='+h;
        //window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        //}
        function popupTanahRizab(h){
            var url = '${pageContext.request.contextPath}/pengambilan/maklumat_tambahan?showEdittanahKR&idTanahRizabPermohonan='+h;
            window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }

        function popupTanahAA(h){
            var url = '${pageContext.request.contextPath}/pengambilan/maklumat_tambahan?showEdittanahAA&idTanahRizabPermohonan='+h;
            window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }
        function popupPermohonanTerdahulu(h){
            var url = '${pageContext.request.contextPath}/pengambilan/maklumat_tambahan?showEditPermohonanTerdahulu&idMohonManual='+h;
            window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }
        function showUlasan() {
            $('#noWartaPihakBerkuasa').show();
            $('#namaWartaPihakBerkuasa').show();
            $('#kawkuasa').show();
            $('#namaPBT').show();
            $('#namaPihakBerkuasa').show();
            $('#noW').show();

        }
        function hideUlasan() {

            $('#noWartaPihakBerkuasa').hide();
            $('#namaWartaPihakBerkuasa').hide();
            $('#kawkuasa').hide();
            $('#namaPBT').hide();
            $('#noW').hide();
            $('#namaPihakBerkuasa').hide();


        }
        function showJenisTanahBandar() {
            $('#noWartaBandar').show();
            $('#noWartaPekan').hide();
            $('#noWartaSeksyen').hide();
            $('#bandar').show();
            $('#pekan').hide();
            $('#seksyen').hide();
            $('#noWartaBandar').val("");
        }
        function showJenisTanahPekan() {
            $('#noWartaBandar').hide();
            $('#noWartaPekan').show();
            $('#pekan').show();
            $('#noWartaSeksyen').hide();
            $('#bandar').hide();
            $('#seksyen').hide();
            $('#noWartaPekan').val("");
        }
        function showJenisTanahSeksyen() {
            $('#noWartaBandar').hide();
            $('#noWartaPekan').hide();
            $('#noWartaSeksyen').show();
            $('#seksyen').show();
            $('#bandar').hide();
            $('#pekan').hide();
            $('#noWartaSeksyen').val("");
        }
        function tambahTanahKR(){
            window.open("${pageContext.request.contextPath}/pengambilan/maklumat_tambahan?tanahKRPopup", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
        }
        function removeLitho(idLaporan)
        {
            if(confirm('Adakah anda pasti?')) {
                var url = '${pageContext.request.contextPath}/pengambilan/maklumat_tambahan?deleteLitho&idLaporan='
                    +idLaporan;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
            }
        }
        function showJenisTanah(){
            $("#noWarta").attr("disabled",false);
        }

        function hideJenisTanah(){
            $("#noWarta").attr("disabled",true);
            $("#noWarta").val("");
        }

        function validation() {
            if($("#nolitho").val() == ""){
                alert('Sila Masukkan No.Lembaran Piawai/No. Litho');
                $("#nolitho").focus();
                return false;
            }
            return true;
        }
        //        function refreshPageTanahRizab(){
        //            var url = '${pageContext.request.contextPath}/pengambilan/maklumat_tambahan?refreshpage';
        //            $.get(url,
        //            function(data){
        //                $('#page_div').html(data);
        //            },'html');
        //        }

</script>

<s:form beanclass="etanah.view.stripes.pengambilan.MaklumatTambahanActionBean">

    <s:messages/>
    <c:if test="${formPP}">
        <div class="subtitle displaytag">
            <fieldset class="aras1" id="locate">
                <legend>
                    Laporan Pelukis Pelan bagi Tanah Milik
                </legend>
                <p align="center"><label></label>
                    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="20" style="width:100%" cellpadding="0" cellspacing="0" id="line"
                                   requestURI="${pageContext.request.contextPath}/maklumat_tambahan">
                        <display:column title="No">${line_rowNum}</display:column>
                        <display:column property="hakmilik.kodHakmilik.nama" title="Jenis Milik" />
                        <display:column property="hakmilik.daerah.nama" title="Daerah" />
                        <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                        <display:column title="Luas" >
                            <fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp;
                            <c:if test="${line.hakmilik.kodUnitLuas.nama eq 'Meter Persegi'}">mp</c:if>
                            <c:if test="${line.hakmilik.kodUnitLuas.nama eq 'Hektar'}">Ha</c:if>
                        </display:column>
                        <display:column title="Luas Diambil" >
                            <fmt:formatNumber pattern="#,##0.0000" value="${line.luasTerlibat}"/>&nbsp;
                            <c:if test="${line.hakmilik.kodUnitLuas.nama eq 'Meter Persegi'}">mp</c:if>
                            <c:if test="${line.hakmilik.kodUnitLuas.nama eq 'Hektar'}">Ha</c:if>
                        </display:column>
                        <display:column property="hakmilik.noLot" title="No. PT/Lot"/>
                        <display:column property="hakmilik.idHakmilik" title="No. H/M"/>                    
                        <display:column title="Diambil">
                            <s:radio name="diambilHakmilikPermohonan[${line_rowNum-1}]" value="1"/>Ya
                            <s:radio name="diambilHakmilikPermohonan[${line_rowNum-1}]" value="0"/>Tidak
                        </display:column>
                        <display:column title="Ulasan">
                            <s:text name="catatanHakmilikPermohonan[${line_rowNum - 1}]" />
                        </display:column>
                    </display:table>
                    <%--<s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahTanahMilik();"/>&nbsp;--%>
                    <br>

                    <br>
                <div  align="left">
                    <table width="60%">
                        <tr>
                            <%--<td><label >&nbsp;&nbsp;</label></td>--%>
                            <td><label >Jenis Tanah : </label></td>
                        </tr>
                        <tr>
                            <td><label >&nbsp;&nbsp;</label></td>
                            <%--onclick="showTunai();"--%>
                            <td><s:radio name="kodtanah" id="noWartaBandar" value="01" onclick="showJenisTanah();"/> Tanah Bandar <br /></td>
                        </tr>
                        <tr>
                            <td><label >&nbsp;&nbsp;</label></td>
                            <%--onclick="showTunai();"--%>
                            <td><s:radio name="kodtanah" id="noWartaPekan" value="02" onclick="showJenisTanah();"/> Tanah Pekan <br /></td>
                        </tr>
                        <tr>
                            <td><label >&nbsp;&nbsp;</label></td>
                            <td><s:radio name="kodtanah" id="noWartaDesa" value="03" onclick="hideJenisTanah();"/> Tanah Desa <br /></td>
                        </tr>
                        <tr>
                            <td><label >&nbsp;&nbsp;</label></td>
                            <td><s:radio name="kodtanah" id="noWartaSeksyen" value="07" onclick="showJenisTanah();"/> Tanah Seksyen <br /></td>
                        </tr>
                        <tr>
                            <td><label id="no">No. Warta:</label></td>
                            <td><s:text name="noWartaPP" size="20" id="noWarta"/></td>
                        </tr>
                        <tr>
                            <td width="20%"><label for="nama">Dalam Kawasan PBT :</label></td>
                            <td width="20%"><s:radio name="permohonanLaporanPelan.kawasanPihakBerkuasa" value="Y" onclick="showUlasan();"/>&nbsp; Ya
                                <s:radio name="permohonanLaporanPelan.kawasanPihakBerkuasa" value="T" onclick="hideUlasan();"/>&nbsp; Tidak</td>
                        </tr>
                        <tr>
                            <td><label id="noW">No. Warta:</label></td>
                            <td><s:text name="noWartaPihakBerkuasa" size="20" id="noWartaPihakBerkuasa"/></td>
                        </tr>
                        <tr>
                            <td><label id="namaPBT">Nama PBT :</label></td>
                            <td><s:text name="namaPihakBerkuasa" size="20" id="namaPihakBerkuasa"/></td>
                        </tr>
                    </table><br/>

                </div>

                <p>
                    <label for="projekkerajaan">Di tanda untuk projek kerajaan :</label>
                    <s:radio name="projekKerajaan" value="Y"/>&nbsp; Ya
                    <s:radio name="projekKerajaan" value="T"/>&nbsp; Tidak
                </p>
                <p>
                    <label for="catatan">Lain lain hal :</label>
                    <s:textarea name="catatan" rows="5" cols="50" id="catatan"/>
                </p>
                <br>
                <p align="center">
                    <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    <s:button name="Charting" id="lakarPelan" value="Charting" class="btn"  onclick="RunProgram('${actionBean.peng.idPengguna}','${actionBean.peng.nama}','${actionBean.peng.jawatan}','${actionBean.permohonan.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
                </p>
                <br>

            </fieldset>
        </div>
    </c:if>
    <c:if test="${viewPP}">
        <div class="subtitle displaytag">
            <fieldset class="aras1" id="locate">
                <legend>
                    Laporan Pelukis Pelan bagi Tanah Milik
                </legend>
                <p align="center"><label></label>
                    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="20" style="width:100%" cellpadding="0" cellspacing="0" id="line"
                                   requestURI="${pageContext.request.contextPath}/maklumat_tambahan">
                        <display:column title="No">${line_rowNum}</display:column>
                        <display:column property="hakmilik.kodHakmilik.nama" title="Jenis Milik" />
                        <display:column property="hakmilik.daerah.nama" title="Daerah" />
                        <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                        <display:column title="Luas" >${line.hakmilik.luas}&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                        <display:column title="Luas Diambil" >${line.luasTerlibat}&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                        <display:column property="hakmilik.noLot" title="No. PT/Lot"/>
                        <display:column property="hakmilik.idHakmilik" title="No. H/M"/>
                        <display:column title="Diambil">
                            <s:radio  disabled="true" name="diambilHakmilikPermohonan[${line_rowNum-1}]" value="1"/>Ya
                            <s:radio disabled="true" name="diambilHakmilikPermohonan[${line_rowNum-1}]" value="0"/>Tidak
                        </display:column>
                        <display:column title="Ulasan">
                            <s:text readonly="true" name="catatanHakmilikPermohonan[${line_rowNum - 1}]" />
                        </display:column>
                    </display:table>
                    <%--<s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahTanahMilik();"/>&nbsp;--%>
                    <br>

            </fieldset>
        </div>
    </c:if>
    <br/>
    <%--<c:if test="${actionBean.permohonan.kodUrusan.kod eq '831A' && actionBean.kodNegeri eq '05'}">
 <div class="subtitle displaytag">
  <fieldset class="aras1" id="locate">
         <legend>
             Tanah Rizab
         </legend>
         <p align="center"><label></label>
             <display:table class="tablecloth" name="${actionBean.tanahRizabList}" pagesize="20" style="width:100%" cellpadding="0" cellspacing="0" id="line"
                            requestURI="${pageContext.request.contextPath}/maklumat_tambahan">
                 <display:column title="No">${line_rowNum}</display:column>
                 <display:column property="rizab.nama" title="Jenis Rizab" />
                 <display:column property="cawangan.name" title="Cawangan" />
                 <display:column property="daerah.nama" title="Daerah" />
                 <display:column property="bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                 <display:column property="noLot" title="No. PT/Lot"/>
                 <display:column property="noWarta" title="No. Warta"/>
                 <display:column property="tarikhWarta" title="Tarikh Warta" format="{0,date,dd/MM/yyyy}"/>
                 <display:column title="Diambil">
                      <s:radio name="diambilTanahRizab[${line_rowNum-1}]" value="1"/>Ya
                      <s:radio name="diambilTanahRizab[${line_rowNum-1}]" value="0"/>Tidak
                 </display:column>
                 <display:column title="Ulasan">
                     <s:text name="catatanTanahRizab[${line_rowNum - 1}]" />
                 </display:column>
                 <display:column title="Kemaskini">
                 <div align="center">
                     <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                          id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popupTanahRizab('${line.idTanahRizabPermohonan}');"/>
                 </div>
                 </display:column>
                 <display:column title="Hapus">
                 <div align="center">
                     <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                          id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeTanahRizab('${line.idTanahRizabPermohonan}');" />
                 </div>
                 </display:column>
             </display:table>
         <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahTanahKR();"/>
         <s:button name="simpanTanahRizabUlasan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
         <br>
             
     </fieldset>
 </div>
          <br/>
<br/>
          
 </c:if>--%>
    <br/>
    <c:if test="${formPP}">

        <div class="subtitle displaytag">
            <fieldset class="aras1" id="locate">
                <legend>
                    No. Lembaran Piawai/No. Litho 
                </legend>
                <p align="center"><label></label>
                <p>
                    <label for="nolitho">No. Lembaran Piawai :</label>
                    <s:text name="noLitho" size="20" id="nolitho"/>
                    <s:button name="simpanNoLitho" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>
                </p>
                <div align="center">
                    <display:table class="tablecloth" name="${actionBean.lithoList}" pagesize="20" style="width:70%" cellpadding="0" cellspacing="0" id="line"
                                   requestURI="${pageContext.request.contextPath}/maklumat_tambahan">
                        <display:column title="No">${line_rowNum}</display:column>
                        <display:column property="noLitho" title="No Litho" />
                        <display:column title="Hapus">
                            <div align="center">
                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeLitho('${line.idLaporan}');" />
                            </div>
                        </display:column>
                    </display:table>
                </div>
                <br>

            </fieldset>
        </div>
        <br>
        <div class="subtitle displaytag">
            <fieldset class="aras1" id="locate">
                <legend>
                    Permohonan Terdahulu
                </legend>
                <p align="center"><label></label>

                    <display:table class="tablecloth" name="${actionBean.permohonanManualList}" pagesize="20" style="width:70%" cellpadding="0" cellspacing="0" id="line">

                        <display:column title="No">${line_rowNum}</display:column>
                        <display:column property="noFail" title="No Fail" />

                        <display:column title="Kemaskini">
                        <div align="center">
                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                 id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popupPermohonanTerdahulu('${line.idMohonManual}');"/>
                        </div>
                    </display:column>
                    <display:column title="Hapus">
                        <div align="center">
                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                 id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removePermohonanTerdahulu('${line.idMohonManual}');"/>
                        </div>
                    </display:column>
                </display:table>
                <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahPermohonanTerdahulu()"/>&nbsp;
                <br>

            </fieldset>
        </div>
    </c:if>
    <c:if test="${viewPP}">
        <table>
            <tr>
                <td><label for="nama">Jenis Tanah :</label></td>


                <td><s:radio name="kodtanah" value="01" onclick="showJenisTanahBandar();" disabled="true"/>&nbsp; Tanah Bandar<br></td>
                    <c:if test="${actionBean.permohonanLaporanPelan.kodTanah.kod eq null}">
                    <td><label id="bandar" for="nama">No. Warta :</label></td>
                    <td><s:text name="noWartaPP" size="20" id="noWartaBandar" readonly="true"/></td>
                    <td>&nbsp;</td>
                </c:if>
                <c:if test="${actionBean.permohonanLaporanPelan.kodTanah.kod eq '01'}">
                    <td><label id="bandar" for="nama">No. Warta :</label></td>
                    <td><s:text name="noWartaPP" size="20" id="noWartaBandar" readonly="true"/></td>
                    <td>&nbsp;</td>
                </c:if>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td><s:radio name="kodtanah" value="02" onclick="showJenisTanahPekan();" disabled="true"/>&nbsp; Tanah Pekan<br></td>
                    <c:if test="${actionBean.permohonanLaporanPelan.kodTanah.kod eq null}">
                    <td><label id="pekan" for="nama">No. Warta :</label></td>
                    <td><s:text name="noWartaPP" size="20" id="noWartaPekan" readonly="true"/></td>
                    <td>&nbsp;</td>
                </c:if>
                <c:if test="${actionBean.permohonanLaporanPelan.kodTanah.kod eq '02'}">
                    <td><label id="pekan" for="nama">No. Warta :</label></td>
                    <td><s:text name="noWartaPP" size="20" id="noWartaPekan" readonly="true"/></td>
                    <td>&nbsp;</td>
                </c:if>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td><s:radio name="kodtanah" value="03"/>&nbsp; Tanah Desa<br></td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td><s:radio name="kodtanah" value="07" onclick="showJenisTanahSeksyen();" disabled="true"/>&nbsp; Tanah Seksyen</td>
                <c:if test="${actionBean.permohonanLaporanPelan.kodTanah.kod eq null}">
                    <td><label id="seksyen" for="nama">No. Warta :</label></td>
                    <td><s:text name="noWartaPP" size="20" id="noWartaSeksyen" readonly="true"/></td>
                    <td>&nbsp;</td>
                </c:if>
                <c:if test="${actionBean.permohonanLaporanPelan.kodTanah.kod eq '07'}">
                    <td><label id="seksyen" for="nama">No. Warta :</label></td>
                    <td><s:text name="noWartaPP" size="20" id="noWartaSeksyen" readonly="true"/></td>
                    <td>&nbsp;</td>
                </c:if>
            </tr>
            <tr>
                <td><label for="nama">Dalam Kawasan PBT :</label></td>
                <td><s:radio name="permohonanLaporanPelan.kawasanPihakBerkuasa" value="Y" onclick="showUlasan();" disabled="true"/>&nbsp; Ya
                    <s:radio name="permohonanLaporanPelan.kawasanPihakBerkuasa" value="T" onclick="hideUlasan();" disabled="true"/>&nbsp; Tidak</td>
                <td><label id="kawkuasa" for="nama">No. Warta :</label></td>
                <td><s:text name="noWartaPihakBerkuasa" size="20" id="noWartaPihakBerkuasa" readonly="true"/></td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td><label id="namaPBT" for="nama">Nama PBT :</label></td>
                <td><s:text name="namaPihakBerkuasa" size="20" id="namaPihakBerkuasa" readonly="true"/></td>
            </tr>
        </table>
        <br>
        <p>
            <label for="nolitho">No. Lembaran Piawai :</label>
            <s:text name="noLitho" size="20" id="nolitho" readonly="true"/>
        </p>
        <p>
            <label for="projekkerajaan">Di tanda untuk projek kerajaan :</label>
            <s:radio name="projekKerajaan" value="Y" disabled="true" />&nbsp; Ya
            <s:radio name="projekKerajaan" value="T" disabled="true"/>&nbsp; Tidak
        </p>
        <p>
            <label for="catatan">Lain lain hal :</label>
            <s:textarea name="catatan" rows="5" cols="50" id="catatan" readonly="true"/>
        </p>
        <p align="center">
            <s:button name="simpan" id="save" value="Simpan" class="btn" disabled="true" onclick="doSubmit(this.form, this.name, 'page_div')"/>
            <c:if test="${actionBean.permohonan.keputusan.kod eq 'L' && actionBean.keseluruhan eq 'Y'}"> <s:button name="Charting" id="lakarPelan" value="Charting"  class="btn"  onclick="RunProgram('${actionBean.peng.idPengguna}','${actionBean.peng.nama}','${actionBean.peng.jawatan}','${actionBean.permohonan.idPermohonan}','${actionBean.stageId}');"/>&nbsp;</c:if>
            <c:if test="${actionBean.permohonan.keputusan.kod ne 'L'}"> <s:button name="Charting" id="lakarPelan" value="Charting" disabled="disabled"  class="btn"  onclick="RunProgram('${actionBean.peng.idPengguna}','${actionBean.peng.nama}','${actionBean.peng.jawatan}','${actionBean.permohonan.idPermohonan}','${actionBean.stageId}');"/>&nbsp;</c:if>
            
        </p>
        <br>
        <div class="subtitle displaytag">
            <fieldset class="aras1" id="locate">
                <legend>
                    Permohonan Terdahulu
                </legend>
                <p align="center"><label></label>
                    <display:table class="tablecloth" name="${actionBean.permohonanManualList}" pagesize="20" style="width:100%" cellpadding="0" cellspacing="0" id="line">

                        <display:column title="No">${line_rowNum}</display:column>
                        <display:column property="noFail" title="No Fail" />
                    </display:table>
                    <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahPermohonanTerdahulu()" disabled="true"/>&nbsp;
                    <br>

            </fieldset>
        </div>
    </c:if>


</s:form>