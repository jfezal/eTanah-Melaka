<%-- 
    Document   : laporan_tanah_mlk
    Created on : Jun 10, 2011, 12:19:30 PM
    Author     : Akmal
--%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript">
$(document).ready( function(){
// alert(document.getElementById('kpsn2').value) ;

 if(document.getElementById('kpsn2').value == 'DI'){
     showTable();
 }
 else if(document.getElementById('kpsn2').value == 'TI'){
     
     hideTable();
 }
 else {
    $('#tidak').hide();
      $('#hideshow').hide();
 }
            });
 
 function hideTable() { 
     $('#tidak').show();
     $('#hideshow').hide();
 }


function showTable() { 
   $('#hideshow').show();
   $('#tidak').hide();
}


  function uploadForm(pandanganImej) {
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/2)-(150/2);
        var url = '${pageContext.request.contextPath}/pelupusan/laporan_tanah?uploadDoc&pandanganImej='+pandanganImej;
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
 
     function tambahPermohonanTerdahulu(){
                        window.open("${pageContext.request.contextPath}/pelupusan/laporan_pelukis_pelan?permohonanTerdahuluPopup", "eTanah",
                        "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
                    }
                    
      function removePermohonanTerdahulu(idPermohonan)
    {
        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pelupusan/laporan_tanah_batuan?deletePermohonanTerdahulu&idPermohonan='
                +idPermohonan;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');}
    }
                        
</script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.pelupusan.LaporanTanahBatuanActionBean">
    <s:messages/>
    <s:errors/>
    <s:hidden name="kpsn2" id="kpsn2"/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Laporan Tanah</legend><br/>
            <p><label>Tarikh Permohonan : </label> <s:format value="${actionBean.permohonan.infoAudit.tarikhMasuk}" formatPattern="dd/MM/yyyy"/></p> </br>
        </fieldset>
           
     </div>
        <div class="subtitle">
        <fieldset class="aras1">
            <legend>Perihal Tanah</legend><br/>
            
              
            <center><legend>Maklumat Hakmilik Permohonan</legend></center><br/>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0"
                                   requestURI="${pageContext.request.contextPath}/pelupusan/laporan_tanah_batuan" id="line">
                        <display:column title="Bil" sortable="true" style="vertical-align:baseline">${line_rowNum}</display:column>
                        <display:column title="Nama Pemilik" style="vertical-align:baseline">${actionBean.pemohon.pihak.nama}</display:column>
                        <display:column title="Status Tanah"><s:select name="kodMilik" id="status">
                                <s:option value="">Sila Pilih</s:option>
                                <s:option value="H">Tanah Milik</s:option>
                                <s:option value="K">Tanah Kerajaan</s:option>
                            </s:select></display:column>
                        <display:column title="Jenis Tanah" style="vertical-align:baseline"></display:column>                      
                        <display:column title="Nombor Lot/PT" style="vertical-align:baseline">${line.noLot}</display:column>
                        <display:column title="Luas" style="vertical-align:baseline"><fmt:formatNumber pattern="#,##0.00" value="${line.luasTerlibat}"/>&nbsp;${line.kodUnitLuas.nama}</display:column>
                        <display:column title="Daerah" class="daerah" style="vertical-align:baseline">${line.bandarPekanMukimBaru.daerah.nama}</display:column>
                        <display:column title="Bandar/Pekan/Mukim" style="vertical-align:baseline">${line.bandarPekanMukimBaru.nama}</display:column>
                    </display:table>
                        
                        
                </div>
                        </br></br>
            <legend>Keadaan Sekeliling :</legend><br/>
            <br/><br/>
          
            <div align="center">            
           <table class="tablecloth">
                                    <tr>
                                        <th>&nbsp;</th><th>Taraf Tanah</th><th>Nombor Lot</th><th>Kegunaan</th><th>Gambar</th>
                                    </tr>
                                    <tr>
                                        <th>
                                            Utara
                                        </th>
                                        <td>
                                            <s:radio name="laporanTanah.sempadanUtaraMilikKerajaan" value="Y"/>&nbsp;Kerajaan
                                            <s:radio name="laporanTanah.sempadanUtaraMilikKerajaan" value="T"/>&nbsp;Milik
                                        </td>
                                        <td>
                                            <s:text name="laporanTanah.sempadanUtaraNoLot" maxlength="10"/>
                                        </td>
                                        <td>
                                            <s:text name="laporanTanah.sempadanUtaraKegunaan"/>
                                        </td>
                                        <td>
                                           <s:select name="utaraImej" style="width:300px;" id="utaraImej" onchange="doSetDokumenUtara();">
                                               <s:option value="">Sila pilih imej untuk dipaparkan</s:option>
                                               <s:options-collection collection="${actionBean.utaraImejLaporanList}" label="catatan" value="dokumen.idDokumen"/>
                                           </s:select>&nbsp;
                                           <s:hidden name="idDokumen" id="idDokumenU" />
                                           <s:button name="uploadDoc" id="display" value="Tambah" class="btn" onclick="uploadForm('U');return false;"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>
                                            Selatan
                                        </th>
                                        <td>
                                <s:radio name="laporanTanah.sempadanSelatanMilikKerajaan" value="Y"/>&nbsp;Kerajaan
                                <s:radio name="laporanTanah.sempadanSelatanMilikKerajaan" value="T"/>&nbsp;Milik
                            </td>
                                        <td>
                                            <s:text name="laporanTanah.sempadanSelatanNoLot" maxlength="10"/>
                                        </td>
                                        <td>
                                            <s:text name="laporanTanah.sempadanSelatanKegunaan"/>
                                        </td>
                                        <td>
                                            <s:select name="selatanImej" style="width:300px;" id="selatanImej" onchange="doSetDokumenSelatan();">
                                               <s:option value="">Sila pilih imej untuk dipaparkan</s:option>
                                               <s:options-collection collection="${actionBean.selatanImejLaporanList}" label="catatan" value="dokumen.idDokumen"/>
                                           </s:select>&nbsp;
                                           <s:hidden name="idDokumen" id="idDokumenS" />
                                           <s:button name="uploadDoc" id="display" value="Tambah" class="btn" onclick="uploadForm('S');return false;"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>
                                            Timur
                                        </th>
                                        <td>
                                <s:radio name="laporanTanah.sempadanTimurMilikKerajaan" value="Y"/>&nbsp;Kerajaan
                                <s:radio name="laporanTanah.sempadanTimurMilikKerajaan" value="T"/>&nbsp;Milik
                            </td>
                                        <td>
                                            <s:text name="laporanTanah.sempadanTimurNoLot" maxlength="10"/>
                                        </td>
                                        <td>
                                            <s:text name="laporanTanah.sempadanTimurKegunaan"/>
                                        </td>
                                        <td>
                                            <s:select name="timurImej" style="width:300px;" id="timurImej" onchange="doSetDokumenTimur();">
                                               <s:option value="">Sila pilih imej untuk dipaparkan</s:option>
                                               <s:options-collection collection="${actionBean.timurImejLaporanList}" label="catatan" value="dokumen.idDokumen"/>
                                           </s:select>&nbsp;
                                           <s:hidden name="idDokumen" id="idDokumenT" />
                                           <s:button name="uploadDoc" id="display" value="Tambah" class="btn" onclick="uploadForm('T');return false;"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>
                                            Barat
                                        </th>
                                        <td>
                                <s:radio name="laporanTanah.sempadanBaratMilikKerajaan" value="Y"/>&nbsp;Kerajaan
                                <s:radio name="laporanTanah.sempadanBaratMilikKerajaan" value="T"/>&nbsp;Milik
                            </td>
                                        <td>
                                            <s:text name="laporanTanah.sempadanBaratNoLot" maxlength="10"/>
                                        </td>
                                        <td>
                                            <s:text name="laporanTanah.sempadanBaratKegunaan"/>
                                        </td>
                                        <td>
                                            <s:select name="baratImej" style="width:300px;" id="baratImej" onchange="doSetDokumenBarat();">
                                               <s:option value="">Sila pilih imej untuk dipaparkan</s:option>
                                               <s:options-collection collection="${actionBean.baratImejLaporanList}" label="catatan" value="dokumen.idDokumen"/>
                                           </s:select>&nbsp;
                                           <s:hidden name="idDokumen" id="idDokumenB" />
                                           <s:button name="uploadDoc" id="display" value="Tambah" class="btn" onclick="uploadForm('B');return false;"/>
                                        </td>
                                    </tr>
                                </table>
            </div>
            <br/><br/>
        </fieldset>
        </div>
       <div class="subtitle">
        <fieldset class="aras1">
            <legend>Perihal Permohonan</legend><br/>   
            <p><label>Jenis :</label> ${actionBean.permohonanBahanBatuan.jenisBahanBatu.nama}</p>
            <p><label>Jumlah Dipohon : </label> ${actionBean.permohonanBahanBatuan.jumlahIsipaduDipohon} ${actionBean.permohonanBahanBatuan.jumlahIsipaduDipohonUom.nama}</p>
            <p><label>Tempoh : </label> ${actionBean.permohonanBahanBatuan.tempohKeluar} ${actionBean.permohonanBahanBatuan.unitTempohKeluar.nama}
            </p>
            <p><label>Tujuan Pengeluaran :</label>${actionBean.permohonan.sebab}</p>
            </br></br>
                    
            <div class="subtitle" align="center">
                 
                    <display:table class="tablecloth" name="${actionBean.listJentera}" cellpadding="0" cellspacing="0" id="line"
                    requestURI="${pageContext.request.contextPath}/pelupusan/laporan_tanah_batuan">

                    <display:column title="Bil" sortable="true">${line_rowNum}
                        <%--<s:hidden name="x" class="x${line_rowNum -1}" value="${line.pihak.idPihak}"/>--%>
                    <s:hidden name="text" value="${line.id}"/>
                    </display:column>
                    <display:column title="Jenis Kenderaan/Jentera">${line.jenisJentera}</display:column>
                    <display:column title="No. Pendaftaran" >${line.noPendaftaranJentera}</display:column>
                    <display:column title="Kepunyaan">${line.kepunyaan}</display:column>    
                </display:table>
            </div>
            <br/><br/>
        </fieldset>
       </div>
         <div class="subtitle">
        <fieldset class="aras1">
            <legend>Permohonan Terdahulu</legend>
            <div align ="center">
                <display:table class="tablecloth" name="${actionBean.permohonanManualList}" pagesize="20" cellpadding="0" cellspacing="0" id="line">

                    <display:column title="No">${line_rowNum}</display:column>
                    <display:column title="ID Permohanan/No Fail"  property="noFail"/>
                    <display:column title="Status Keputusan">
                    </display:column>

                </display:table>
            </div>
        </fieldset>
         </div>
            <br/>
         <div class="subtitle">
        <fieldset class="aras1">
             <legend>Permohonan Bertindih</legend>
             </br><br/>
             <div class="subtitle" align="center">
            <display:table class="tablecloth" name="" cellpadding="0" cellspacing="0" id="line">
                    <display:column title="No. Fail"></display:column>
                    <display:column title="Tarikh Permohonan" ></display:column>
                    </display:table>
             </div>
             </br>
        </fieldset>
       </div>
            <br/>
        <div class="subtitle">
        <fieldset class="aras1">
            <legend>Perihal Pemohon</legend>
            <br/><br/>
       <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B'|| actionBean.pihak.jenisPengenalan.kod eq 'L'|| actionBean.pihak.jenisPengenalan.kod eq 'P'||actionBean.pihak.jenisPengenalan.kod eq 'T'||actionBean.pihak.jenisPengenalan.kod eq 'I'||actionBean.pihak.jenisPengenalan.kod eq 'O'||actionBean.pihak.jenisPengenalan.kod eq 'N'||actionBean.pihak.jenisPengenalan.kod eq 'F'}">
            <div class="subtitle" align="center">
      
            <display:table class="tablecloth" name="${actionBean.pemohon}" cellpadding="0" cellspacing="0"
                                   requestURI="${pageContext.request.contextPath}/pelupusan/laporan_tanah_batuan" id="line">
               <display:column title="Nama" style="vertical-align:baseline">${actionBean.pemohon.pihak.nama}</display:column>
               <display:column title="No. Pengenalan" sortable="true" style="vertical-align:baseline">${line.pihak.noPengenalan}</display:column>
               <display:column title="Umur" style="vertical-align:baseline">${line.umur}</display:column>
               <display:column title="Warganegara" style="vertical-align:baseline">${line.pihak.wargaNegara.nama}</display:column>
               <display:column title="Alamat" >${line.pihak.alamat1}
                                    <c:if test="${line.pihak.alamat2 ne null}"> , </c:if>
                                    ${line.pihak.alamat2}
                                    <c:if test="${line.pihak.alamat3 ne null}"> , </c:if>
                                    ${line.pihak.alamat3}
                                    <c:if test="${line.pihak.alamat4 ne null}"> , </c:if>
                                    ${line.pihak.alamat4}</display:column>
                       
             </display:table>
            </div>
       </c:if>
       <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'U' || actionBean.pihak.jenisPengenalan.kod eq 'D' || actionBean.pihak.jenisPengenalan.kod eq 'S'}">     
          <div class="subtitle" align="center">
           <display:table class="tablecloth" name="${actionBean.pemohon}" cellpadding="0" cellspacing="0"
                                   requestURI="${pageContext.request.contextPath}/pelupusan/laporan_tanah_batuan" id="line">
               <display:column title="Nama Syarikat" style="vertical-align:baseline">${actionBean.pemohon.pihak.nama}</display:column>
               <display:column title="No. Pendaftaran" sortable="true" style="vertical-align:baseline">${line.pihak.noPengenalan}</display:column>
               <display:column title="Tarikh Penubuhan" style="vertical-align:baseline"><s:format value="${line.pihak.tarikhLahir}" formatPattern="dd/MM/yyyy"/></display:column>
               <display:column title="Alamat" >${line.pihak.suratAlamat1}
                                    <c:if test="${line.pihak.suratAlamat2 ne null}"> , </c:if>
                                    ${line.pihak.suratAlamat2}
                                    <c:if test="${line.pihak.suratAlamat3 ne null}"> , </c:if>
                                    ${line.pihak.suratAlamat3}
                                    <c:if test="${line.pihak.suratAlamat4 ne null}"> , </c:if>
                                    ${line.pihak.suratAlamat4}</display:column>
                       
             </display:table>
          </div>
       </c:if>
            <br/>
            </fieldset>
        </div>
    
        <div class="subtitle">
        <fieldset class="aras1">
            <legend>Syor Penolong Pegawai Tanah</legend><br/>   
            <p><label>Keputusan :</label>
            <s:radio name="kpsn" value="DI" id="kpsn" onclick="showTable()"/> Sokong
            <s:radio name="kpsn" value="TI" id="kpsn" onclick="hideTable()"/> Tidak Sokong</p><br/>
            
            <div class="subtitle" align="center">
            <table id="hideshow">
                <tr>
                    <td>a)Jumlah meter padu</td><td>: <s:text name="permohonanBahanBatuan.jumlahIsipadu" size="10"/>
                    <s:select name="jumlahIsipaduUom.kod" id="isipaduUOM" value="${actionBean.permohonanBahanBatuan.jumlahIsipaduUom.kod}">
                    <s:option value="">Sila Pilih</s:option>
                    <s:option value="MP">Meterpadu</s:option>
                    <s:option value="KB">Ketul Batu</s:option>
                </s:select></td> 
                </tr>
                <tr>   
                    <td>b)Tempoh</td><td>: <s:text name="permohonanBahanBatuan.tempohDisyor" size="20" id="tempoh" maxlength="3" onkeyup="validateNumber(this,this.value);" value="${actionBean.permohonanBahanBatuan.tempohDisyor}"/>
                <s:select name="tempohSyorUOM" id="tempohUOM">
                    <s:option value="">Sila Pilih</s:option>
                    <s:option value="HR">Hari</s:option>
                    <s:option value="B">Bulan</s:option>
                    <s:option value="T">Tahun</s:option>
                </s:select>
                    </td>
            </table>
            
            <table id="tidak">
                <tr>
                    <td>Ulasan :</td><td><s:textarea name="fasaPermohonan.ulasan" rows="6" cols="50"/></td>
                </tr>
            </table>
                </div>
                <br>
                    <p><label>Tarikh :</label><s:text name="fasaPermohonan.tarikhKeputusan"  id= "datepicker" class="datepicker" formatType="date" formatPattern="dd/MM/yyyy"/></p></br>
                  </fieldset>
            <p align="center">
 
                <s:button name="simpanLaporan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
            </p>
        </div>
         <br/>
  
</s:form> 