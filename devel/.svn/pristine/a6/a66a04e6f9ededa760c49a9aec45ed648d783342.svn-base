<%-- 
    Document   : draf_mmkn_mlk
    Created on : Sep 21, 2011, 8:18:40 PM
    Author     : Admin
--%>

<%--
    Document   : draf_mmkn_ptd/ptg
    Created on : Mar 5, 2011, 11:32:21 AM
    Author     : Murali
    Modified   : Shazwan
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<style type="text/css">
    #tdLabel {
        color:#003194;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
    }

    #tdDisplay {
        color:#003194;
        display:block;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        margin-left:30px;
        margin-right:0.5em;
        width:14em;
    }
    #test {
        text-transform: capitalize;
}
</style>
<script type="text/javascript">
     $(document).ready( function() {   
        if('${actionBean.permohonan.kodUrusan.kod}' == "PLPS"){
             if('${actionBean.permohonanPermitItem.kodItemPermit.kod}' == "LN"){
                
                  $('#catatanKwsn').show();
             }else
                 $('#catatanKwsn').hide();
               
        } 
           
    });
    function menyimpan(index,i,f)
    {
        var kand;
        if(index == 2)
            kand = document.getElementById("kandungan2"+i).value;
        if(index == 3)
            kand = document.getElementById("kandungan3"+i).value;
        if(index==4){
            kand = document.getElementById("kandungan4"+i).value;
        }
        if(index==5){
            kand = document.getElementById("kandungan5"+i).value;
        }
        if(index==6){
            kand = document.getElementById("kandungan6"+i).value;
        }
        if(index==7){
            kand = document.getElementById("kandungan7"+i).value;
        }
        if(index==8){
            kand = document.getElementById("kandungan8"+i).value;
        }
        if(index==9){
            kand = document.getElementById("kandungan9"+i).value;
        }
        var q = $(f).formSerialize();
        $.post('${pageContext.request.contextPath}/pelupusan/draf_mmkn_prmp?simpanKandungan&index='+index+'&kandungan='+kand,q,
        function(data){
            $('#page_div').html(data);
        }, 'html');
  
       
       
    }
    function addRow(index,f)
    {
        var q = $(f).formSerialize();
        $.post('${pageContext.request.contextPath}/pelupusan/draf_mmkn_prmp?tambahRow&index='+index,q,
        function(data){
            $('#page_div').html(data);
        }, 'html');
    }
    function deleteRow(idKandungan,f)
    {
        if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
             var q = $(f).formSerialize();
            $.post('${pageContext.request.contextPath}/pelupusan/draf_mmkn_prmp?deleteRow&idKandungan='+idKandungan,q,
            function(data){
                $('#page_div').html(data);
            }, 'html');
        }
    }
    function addSyorTolak(v){
        
        self.refreshPage(v);
    }
    function semakSyor(f,v){
        var q = $(f).formSerialize();
        $.post('${pageContext.request.contextPath}/pelupusan/draf_mmkn_prmp?showsyortolaklulus&syortolaklulus='+v,q,
        function(data){
            $('#page_div').html(data);
        }, 'html');
    }
    ///aded
    function refreshPage(v){
        var syortolaklulusdata = v;
        var url = '${pageContext.request.contextPath}/pelupusan/draf_mmkn_prmp?showsyortolaklulus&syortolaklulus='+syortolaklulusdata;
        $.post(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }
    function cariPopup(){
        // alert("search:"+index);
        var url = '${pageContext.request.contextPath}/pelupusan/draf_mmkn?search';
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=900,height=700 scrollbars=yes");
    }

    function cariPopup2(){
        // alert("search:"+index);
        var url = '${pageContext.request.contextPath}/pelupusan/draf_mmkn?showFormCariKodSekatan';
        window.open(url, "eTUanah","status=0,toolbar=0,location=0,menubar=0,width=900,height=700 scrollbars=yes");
    }
    
    function openLain_lain(val){
      if(val == 'LN'){
           $('#catatanKwsn').show();
      }else{
           $('#catatanKwsn').hide();
      }
    }

</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.stripes.pelupusan.DrafMMKN_PRMPActionBean">
    <s:messages/>
    <s:errors/>
    <s:hidden name="kertasK.idKertas"/>
    <s:hidden name="edit" id="edit"/>
    <s:hidden name="editPTD" id="editPTD"/>
     <s:hidden name="editPTG" id="editPTG"/>
     <s:hidden name="openPTG" id="openPTG"/>
     <s:hidden name="firstTimeOpen" id="firstTimeOpen"/>
     
    <table width="90%" border="0" >
        <tr>
            <td colspan="4">
              <div class="subtitle" style="text-transform: capitalize">
                    <fieldset class="aras1">
                        <legend> </legend>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTPBP'}">
                            <c:if test="${actionBean.stageId eq '21TerimaDraf'}">
                                 No. Rujukan : <s:text name="no_laporan" id="no_laporan" size="20"/><br><br>
                            </c:if>
                            <c:if test="${actionBean.stageId eq '22SemakDraf' or actionBean.stageId eq '23SemakDraf' or actionBean.stageId eq '24SemakSyorDraf' or actionBean.stageId eq '25PerakuMMK' or actionBean.stageId eq '26CetakMMK' or actionBean.stageId eq '27TerimaKeputusan'  or actionBean.stageId eq '29Siasatan' or actionBean.stageId eq '30TerimaKeputusanSiasatan' or actionBean.stageId eq '31PerakuMMK' or actionBean.stageId eq '29Siasatan' or actionBean.stageId eq '32CetakMMK'}">
                                No. Rujukan : ${actionBean.permohonankertas.noLaporan}<br><br>
                            </c:if>
                        </c:if>
                        <p align="center">
                            <b>(MAJLIS MESYUARAT KERAJAAN NEGERI)</b>
                        </p>
                        <div class="content" align="center">                            
                            <table border="0" width="80%" cellspacing="10%" align="center">
                                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PRMP' or actionBean.permohonan.kodUrusan.kod eq 'PTPBP'}">
                                    <tr><td id="tdLabel" ><b>
                                        <font style="text-transform: capitalize">
                                        <tr><td>
                                            <p><b>
                                                    <c:if test="${!actionBean.edit}">
                                                        <s:textarea rows="6" cols="150" name="tajukPlps" class="normal_text" style="text-transform: uppercase"/>
                                                    </c:if>
                                                    <c:if test="${actionBean.edit}">
                                                        <span style="text-transform: uppercase">${actionBean.tajukPlps}</span>
                                                    </c:if>
                                              </b></p>
                                             </td>
                                        </tr>
                                        </font></b></td></tr>
                                </c:if>
                                <tr><td>&nbsp;</td></tr>
                            </table>                            
                        </div>
                    </fieldset>
              </div>
            </td>
        </tr>
        <tr>
            <td width="1%"><b>1.</b></td>
            <td colspan="3"><div align="left"><b>TUJUAN</b></div></td>
        </tr>
        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PLPS' || actionBean.permohonan.kodUrusan.kod eq 'PRMP' || actionBean.permohonan.kodUrusan.kod eq 'PTPBP'}">
            
                <tr>
                    <td>&nbsp;</td>
                    <td valign="top">1.1</td>
                    <td colspan="2">
                        <c:if test="${!actionBean.edit}">
                            <s:textarea rows="6" cols="150" name="tujuanPlps" class="normal_text"/>
                        </c:if>
                        <c:if test="${actionBean.edit}">
                            ${actionBean.tujuanPlps}
                        </c:if>
                    </td>
                </tr>
                <tr>  
                    <td colspan="4">
                      &nbsp;
                    </td>
                </tr>
                
        </c:if>
                <tr>
                    <td><b>2.</b></td>
                    <td colspan="3"><b> Perihal Permohonan</b></td>
                </tr>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PLPS' || actionBean.permohonan.kodUrusan.kod eq 'PRMP' || actionBean.permohonan.kodUrusan.kod eq 'PTPBP'}">
                    <tr>
                        <td>&nbsp;</td>
                        <td width="1%">2.1</td>
                        <td colspan="2">
                            <c:if test="${!actionBean.edit}">
                                <s:textarea rows="6" cols="150" name="perihalpermohonanPlps" class="normal_text"/>
                            </c:if>
                            <c:if test="${actionBean.edit}">
                                ${actionBean.perihalpermohonanPlps}
                            </c:if>
                        </td>
                    </tr>
                </c:if>
                    <tr>
                        <td>&nbsp;</td>
                        <td width="1%">2.2</td>
                        <td colspan="2">
                            <c:if test="${!actionBean.edit}">
                                <s:textarea rows="6" cols="150" name="perihalpermohonan2" class="normal_text" />
                            </c:if>
                            <c:if test="${actionBean.edit}">
                                ${actionBean.perihalpermohonan2}
                            </c:if>
                        </td>
                    </tr>
                    
                        <c:set var="i" value="1" />
                        <c:set var="num" value="3"/>
                        <c:forEach items="${actionBean.senaraiLaporanKandungan1}" var="line">
                            <tr>
                                <td>&nbsp;</td>
                                <td valign="top"><c:out value="2.${num}"/></td>
                                <td colspan="2">
                                    <c:if test="${!actionBean.edit}">
                                        <s:textarea  id="kandungan2${i}"name="senaraiLaporanKandungan1[${i-1}].kandungan" cols="150"  rows="5" class="normal_text"/>
                                        <s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan},this.form)"></s:button>
                                    </c:if>
                                    <c:if test="${actionBean.edit}">
                                        ${line.kandungan}
                                    </c:if>
                                </td>
                             </tr>
                        <c:set var="i" value="${i+1}" />
                         <c:set var="num" value="${num+1}"/>
                        </c:forEach>
                            <tr>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td colspan="2">
                                    <c:if test="${!actionBean.edit}">
                                        <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('2',this.form)"></s:button> 
                                        <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('2',${i-1},this.form)"></s:button>
                                    </c:if>
                                    <c:if test="${actionBean.edit}">
                                        &nbsp;
                                    </c:if>
                                </td>                                
                            </tr>
                            <tr>
                                <td colspan="4">&nbsp;</td>
                            </tr>
                            <tr>
                                <td><b>3.</b></td>
                                <td colspan="3"><b> Perihal Tanah</b></td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td>3.1</td>
                                <td colspan="2">Butir-butir Tanah</td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td width="15%">Jenis dan No. Hakmilik </td>
                                <td>: ${actionBean.hakmilikPermohonan.hakmilik.kodHakmilik.kod} ${actionBean.hakmilikPermohonan.hakmilik.noHakmilik}
                                </td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>No. Lot </td>
                                <td>: ${actionBean.hakmilikPermohonan.hakmilik.noLot} 
                                </td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>Mukim :</td>
                                <td>: ${actionBean.hakmilikPermohonan.hakmilik.bandarPekanMukim.nama} 
                                </td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>Daerah </td>
                                <td>: ${actionBean.hakmilikPermohonan.hakmilik.daerah.nama} 
                                </td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>Hasil Tahunan </td>
                                <td>: RM <s:format formatPattern="###,###,###.00" value="${actionBean.hakmilikPermohonan.hakmilik.cukaiSebenar}"/> &nbsp;
                                </td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>Luas </td>
                                <td>: ${actionBean.hakmilikPermohonan.hakmilik.luas}  ${actionBean.hakmilikPermohonan.hakmilik.kodUnitLuas.nama}
                                </td>
                            </tr>
                             <tr>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>Penjenisan </td>
                                <td>: Pertanian
                                </td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>Syarat Nyata </td>
                                <td>: 
                                    <c:if test="${actionBean.hakmilikPermohonan.syaratNyataBaru ne null}">
                                        ${actionBean.hakmilikPermohonan.syaratNyataBaru.syarat}
                                    </c:if>
                                    <c:if test="${actionBean.hakmilikPermohonan.syaratNyataBaru eq null}">
                                        Tiada
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>Sekatan Kepentingan </td>
                                <td>: 
                                    <c:if test="${actionBean.hakmilikPermohonan.sekatanKepentinganBaru ne null}">
                                        ${actionBean.hakmilikPermohonan.sekatanKepentinganBaru.sekatan}
                                    </c:if>
                                    <c:if test="${actionBean.hakmilikPermohonan.sekatanKepentinganBaru eq null}">
                                        Tiada
                                    </c:if>    
                                </td>
                            </tr>
                            <c:set var="i" value="1" />
                            <c:forEach items="${actionBean.senaraiHakmilikPihak}" var="line">
                            <tr>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td valign="top">
                                    <c:if test="${i eq '1'}">
                                        Tuan Tanah 
                                    </c:if>
                                    <c:if test="${i ne '1'}">
                                        &nbsp;
                                    </c:if>
                                </td>
                                <td>: ${line.pihak.nama}                                    
                                </td>
                             </tr>
                             <tr>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>
                                    &nbsp;&nbsp; ${line.syerPembilang}/${line.syerPenyebut} Bhg.                                    
                                </td>
                             </tr>
                            <c:set var="i" value="${i+1}" />
                            </c:forEach>
                            <tr>
                                <td>&nbsp;</td>
                                <td width="1%" valign="top">3.2</td>
                                <td colspan="2">
                                    <c:if test="${!actionBean.edit}">
                                        <s:textarea rows="6" cols="150" name="perihaltanah" class="normal_text" />
                                    </c:if>
                                    <c:if test="${actionBean.edit}">
                                        ${actionBean.perihaltanah}
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td colspan="3">3.3 Tanah-tanah yang bersempadan dengan tanah yang dipohon adalah seperti berikut:-</td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td colspan="3">
                                    <div class="content" align="center">
                                    <table class="tablecloth">
                                        <tr><th>Kedudukan</th><th>Tanah Kerajaan/ Rizab/ Lot/ PT</th><th>Apa yang Terdapat Di atas Tanah</th></tr>
                                        <tr><th>Utara</th><td>${actionBean.laporanTanah.sempadanUtaraNoLot}</td><td>${actionBean.laporanTanah.sempadanUtaraKegunaan}</td></tr>
                                        <tr><th>Selatan</th><td>${actionBean.laporanTanah.sempadanSelatanNoLot}</td><td>${actionBean.laporanTanah.sempadanSelatanKegunaan}</td></tr>
                                        <tr><th>Timur</th><td>${actionBean.laporanTanah.sempadanTimurNoLot}</td><td>${actionBean.laporanTanah.sempadanTimurKegunaan}</td></tr>
                                        <tr><th>Barat</th><td>${actionBean.laporanTanah.sempadanBaratNoLot}</td><td>${actionBean.laporanTanah.sempadanBaratKegunaan}</td></tr>

                                    </table>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td><b>4.</b></td>
                                <td colspan="3"><b> Perihal Pemohon</b></td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td width="1%" valign="top">4.1</td>
                                <td colspan="2">
                                    <c:if test="${!actionBean.edit}">
                                        <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B'|| actionBean.pihak.jenisPengenalan.kod eq 'L'|| actionBean.pihak.jenisPengenalan.kod eq 'P'||actionBean.pihak.jenisPengenalan.kod eq 'T'||actionBean.pihak.jenisPengenalan.kod eq 'I'||actionBean.pihak.jenisPengenalan.kod eq 'O'||actionBean.pihak.jenisPengenalan.kod eq 'N'||actionBean.pihak.jenisPengenalan.kod eq 'F'}">
                                            <s:textarea rows="6" cols="150" name="perihalpemohon" class="normal_text" />
                                        </c:if>
                                        <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'U' || actionBean.pihak.jenisPengenalan.kod eq 'D' || actionBean.pihak.jenisPengenalan.kod eq 'S'}">
                                            <s:textarea rows="6" cols="150" name="perihalpemohon_s" class="normal_text" />
                                        </c:if>                                        
                                    </c:if>
                                    <c:if test="${actionBean.edit}">
                                        <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B'|| actionBean.pihak.jenisPengenalan.kod eq 'L'|| actionBean.pihak.jenisPengenalan.kod eq 'P'||actionBean.pihak.jenisPengenalan.kod eq 'T'||actionBean.pihak.jenisPengenalan.kod eq 'I'||actionBean.pihak.jenisPengenalan.kod eq 'O'||actionBean.pihak.jenisPengenalan.kod eq 'N'||actionBean.pihak.jenisPengenalan.kod eq 'F'}">
                                            ${actionBean.perihalpemohon}
                                        </c:if>
                                        <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'U' || actionBean.pihak.jenisPengenalan.kod eq 'D' || actionBean.pihak.jenisPengenalan.kod eq 'S'}">
                                            ${actionBean.perihalpemohon_s}
                                        </c:if>
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td width="1%" valign="top">4.2</td>
                                <td colspan="2">
                                    <c:if test="${!actionBean.edit}">
                                        <s:textarea rows="6" cols="150" name="perihalpemohon1" class="normal_text" />
                                    </c:if>
                                    <c:if test="${actionBean.edit}">
                                        ${actionBean.perihalpemohon1}
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td width="1%" valign="top">
                                    <c:if test="${actionBean.pemohon.statusKahwin eq 'Berkahwin'}">
                                    4.3
                                    </c:if>
                                     <c:if test="${actionBean.pemohon.statusKahwin ne 'Berkahwin'}">
                                    &nbsp;
                                    </c:if> 
                                </td>
                                <td colspan="2">
                                    
                                         <c:if test="${actionBean.pemohon.statusKahwin eq 'Berkahwin'}">
                                             <c:if test="${actionBean.pemohonHubungan.kaitan eq 'ISTERI'}">
                                                 <c:if test="${!actionBean.edit}">
                                                    <s:textarea rows="6" cols="150" name="perihalpemohon2" class="normal_text" />
                                                 </c:if>
                                                 <c:if test="${actionBean.edit}">
                                                    ${actionBean.perihalpemohon2}
                                                </c:if>
                                             </c:if>
                                             <c:if test="${actionBean.pemohonHubungan.kaitan eq 'SUAMI'}">
                                                 <c:if test="${!actionBean.edit}">
                                                    <s:textarea rows="6" cols="150" name="perihalpemohon2suami" class="normal_text" />
                                                 </c:if>
                                                 <c:if test="${actionBean.edit}">
                                                    ${actionBean.perihalpemohon2suami}
                                                </c:if>
                                             </c:if>
                                         </c:if>
                                         <c:if test="${actionBean.pemohon.statusKahwin ne 'Berkahwin'}">
                                             &nbsp;
                                         </c:if>
                                </td>
                            </tr>
                            <tr>
                                <td width="1%"><b>5.</b></td>
                                <td colspan="3"><div align="left"><b><font style="text-transform: capitalize">ULASAN JABATAN-JABATAN TEKNIKAL</font></b></div></td>
                            </tr>
                            <c:set var="i" value="1" />
                            <c:forEach items="${actionBean.senaraiLaporanKandunganUtil}" var="line">
                            <tr>
                                <td>&nbsp;</td>
                                <td valign="top"><c:out value="5.${i}"/></td>
                                <td colspan="2"><font style="font-weight:bold;">${line.permohonanRujukanLuar.namaAgensi}</font></td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td valign="top">&nbsp;</td>
                                <td colspan="2">( Ruj. Surat ${line.permohonanRujukanLuar.noRujukan} bertarikh <s:format value="${line.permohonanRujukanLuar.tarikhTerima}" formatPattern="dd/MM/yyyy"/> )</td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td colspan="2">
                                    <c:if test="${!actionBean.edit}">
                                        <s:textarea name="ulasan${i}" id="ulasan${i}"   class="normal_text" rows="6" cols="100" ></s:textarea>
                                        <s:hidden name="rowAgensi${i}" value="${line.permohonanRujukanLuar.idRujukan}" id="rowAgensi${i}"/>
                                    </c:if>
                                    <c:if test="${actionBean.edit}">
                                        ${line.permohonanRujukanLuar.ulasan}
                                    </c:if>
                                </td>
                            </tr>
                            
                            <c:set var="i" value="${i+1}" />
                            </c:forEach>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PTPBP'}">
                            <tr>
                                <td width="1%"><b>6.</b></td>
                                <td colspan="3"><div align="left"><b><font style="text-transform: capitalize">Ulasan YB. ADUN KAWASAN ${actionBean.kodDun.nama}</font></b></div></td>
                            </tr>
                            </c:if>
                             <c:set var="i" value="1" />
                            <c:forEach items="${actionBean.senaraiYBAdun}" var="line">
                            <tr>
                                <td>&nbsp;</td>
                                <td valign="top"><c:out value="6.${i}"/></td>
                                <td colspan="2"><font style="font-weight:bold;">${line.namaAgensi}</font></td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td valign="top">&nbsp;</td>
                                <td colspan="2">( Ruj. Surat ${line.noRujukan} bertarikh <s:format value="${line.tarikhTerima}" formatPattern="dd/MM/yyyy"/></td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td colspan="2">
                                    <c:if test="${!actionBean.edit}">
                                        <s:textarea name="ulasanyb${i}" id="ulasanyb${i}"   class="normal_text" rows="6" cols="100" ></s:textarea>
                                        <s:hidden name="rowAgensi${i}" value="${line.idRujukan}" id="rowAgensi${i}"/>
                                    </c:if>
                                    <c:if test="${actionBean.edit}">
                                        ${line.ulasan}
                                    </c:if>
                                </td>
                            </tr>
                            <c:set var="i" value="${i+1}" />
                            </c:forEach>
                            <tr>
                                <td colspan="4">&nbsp;<s:hidden name="rowCount1" value="${fn:length(actionBean.senaraiYBAdun)}" id="rowCount1"/></td>
                            </tr>
                            
                            <tr>
                                <td colspan="4">&nbsp;</td>
                            </tr>
                            <tr>
                                <td width="1%"><b>7.</b></td>
                                <td colspan="3"><b><font style="text-transform: capitalize">PERAKUAN PENTADBIR TANAH DAERAH ${actionBean.permohonan.cawangan.daerah.nama}</font></b></td>
                            </tr>
                            <tr>
                                <td width="1%">&nbsp;</td>
                                <td>&nbsp;
                                </td>
                                <td colspan="2"> Perakuan :                                   
                                    <c:if test="${actionBean.editPTD}">
                                        <s:radio name="syortolaklulus" value="SL" id="syortolaklulus" onclick="semakSyor(this.form,this.value);"/> Syor Lulus   
                                        <s:radio name="syortolaklulus" value="ST" id="syortolaklulus" onclick="semakSyor(this.form,this.value);"/> Syor Tolak
                                    </c:if>
                                    <c:if test="${!actionBean.editPTD}">
                                        <c:if test="${actionBean.syortolaklulus eq 'SL'}">
                                            Syor Lulus
                                        </c:if>
                                        <c:if test="${actionBean.syortolaklulus eq 'ST'}">
                                            Syor Tolak
                                        </c:if>
                                    </c:if>
                                </td>
                            </tr>
                            <c:set var="i" value="1" />
                            <c:forEach items="${actionBean.senaraiLaporanKandunganpbtanah}" var="line">
                            <tr>
                                <td>&nbsp;</td>
                                <td><c:out value="7.${i}"/></td>
                                <td colspan="2">
                                    <c:if test="${actionBean.editPTD}">
                                        <s:textarea  id="kandungan7${i}" name="senaraiLaporanKandunganpbtanah[${i-1}].kandungan" cols="150"  rows="5" class="normal_text"/>
                                        <s:button value="Hapus" class="btn" name="hapus7" onclick="deleteRow(${line.idKandungan},this.form)"></s:button>
                                        <s:hidden name="rowCount7" value="${fn:length(actionBean.senaraiLaporanKandunganpbtanah)}" id="rowCount7"/>
                                    </c:if>
                                    <c:if test="${!actionBean.editPTD}">
                                        ${line.kandungan}
                                    </c:if>
                                </td>
                             </tr>
                        <c:set var="i" value="${i+1}" />
                        </c:forEach>
                            <tr>
                                <td valign="top">&nbsp;</td>
                                <td>&nbsp;</td>
                                <td colspan="2">
                                    <c:if test="${actionBean.editPTD}">
                                        <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('7',this.form)"></s:button> 
                                        <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('7',${i-1},this.form)"></s:button>
                                    </c:if>
                                   <c:if test="${!actionBean.editPTD}">
                                        &nbsp;
                                    </c:if>
                                </td>                                
                            </tr>
                            <tr>
                                <td colspan="4">&nbsp;</td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td colspan="3">
                                    
                                        <c:if test="${actionBean.syortolaklulus eq 'SL'}">
                                            ${actionBean.pentabirtanah2}
                                        </c:if>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="4">&nbsp;</td>
                            </tr>
    </table>
    <table width="90%" border="0" align="center">
         <c:if test="${actionBean.syortolaklulus eq 'SL'}">
          <tr>
              <td align="right" id="tdDisplay">Lesen</td>
            <td align="center">:</td>
            <td align="left" colspan="2">Tanah Pertanian</td>            
          </tr>
          <tr>
              <td align="right" id="tdDisplay">Kegunaan</td>
            <td align="center">:</td>
            <td align="left" colspan="2">${actionBean.permohonanPermitItem.kodItemPermit.nama}</td>            
          </tr>
          <tr>
              <td align="right" id="tdDisplay">Luas</td>
            <td align="center">:</td>
            <td align="left" colspan="2">${actionBean.hakmilikPermohonan.luasDiluluskan}  ${actionBean.hakmilikPermohonan.luasLulusUom.nama}</td>            
          </tr>
          <tr>
              <td align="right" id="tdDisplay">Sewa Tahunan</td>
            <td align="center">:</td>
            <td align="left" colspan="2">RM <s:format formatPattern="###,###,###.00" formatType="number" value="${actionBean.hakmilikPermohonan.luasDiluluskan * actionBean.permitItem.kodItemPermit.royaltiTanahKerajaan}"/>  setahun (RM <s:format formatPattern="###,###,###.00" value="${actionBean.permitItem.kodItemPermit.royaltiTanahKerajaan}"/>&nbsp;setiap ${actionBean.permitItem.kodItemPermit.royaltiTanahKerajaanUom.nama})</td>            
          </tr>
          <tr>
              <td align="right" id="tdDisplay">Syarat-syarat Khas</td>
            <td align="center">:</td>
            <td align="left" colspan="2"></td>
          </tr>
          <tr>
              <td align="right" id="tdDisplay">&nbsp;</td>
            <td align="center">&nbsp;</td>
            <td align="left" colspan="2">
                i) Permit pertanian ini hendaklah diperbaharui pada setiap awal tahun dan akan tamat tempoh pada 31 Disember tiap-tiap tahun. 
            </td>
          </tr>
          <tr>
              <td align="right" id="tdDisplay">&nbsp</td>
            <td align="center">&nbsp;</td>
            <td align="left" colspan="2">ii) Permit pertanian ini tidak boleh digunakan bagi apa-apa maksud selain daripada maksud yang dinyatakan di dalamnya.</td>
          </tr>
          <tr>
              <td align="right" id="tdDisplay">&nbsp</td>
              <td align="center">&nbsp;</td>
              <td align="left" colspan="2">iii) Permit pertanian ini tidak boleh dipajak, dijual atau disewakan kepada sesiapa pun.</td>
          </tr>
          <tr>
              <td align="right" id="tdDisplay">&nbsp</td>
            <td align="center">&nbsp;</td>
            <td align="left" colspan="2">iv) Permit ini akan terbatal sekiranya berlaku apa-apa pelanggaran syarat terhadap tanah ini.
            </td>
          </tr>
          <tr>
              <td align="right" colspan="4">&nbsp</td>
          </tr>
          </c:if>

    </table>
    <table width="90%" border="0" >
        <c:if test="${actionBean.openPTG}">
            <c:if test="${actionBean.editPTG}">
                <tr>
                    <td width="1%"><b>8.</b></td>
                    <td colspan="3"><div align="left"><b><font style="text-transform: capitalize">PERAKUAN PENGARAH TANAH DAN GALIAN</font></b></div></td>
                </tr>
                <c:set var="i" value="1" />
                <c:forEach items="${actionBean.senaraiLaporanKandunganptg1}" var="line">
                    <tr>
                        <td>&nbsp;</td>
                        <td valign="top"><c:out value="8.${i}"/></td>
                        <td colspan="2">
                                <s:textarea  id="kandungan8${i}"name="senaraiLaporanKandunganptg1[${i-1}].kandungan" cols="150"  rows="5" class="normal_text"/>
                                <s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan},this.form)"></s:button>
                       </td>
                     </tr>
                <c:set var="i" value="${i+1}" />        
                </c:forEach>
                <s:hidden name="rowCount8" value="${fn:length(actionBean.senaraiLaporanKandunganptg1)}" id="rowCount8"/>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td colspan="2">
                        <c:if test="${actionBean.editPTG}">
                            <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('8',this.form)"></s:button> 
                            <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('8',${i-1},this.form)"></s:button>
                        </c:if>
                        <c:if test="${!actionBean.editPTG}">
                            &nbsp;
                        </c:if>
                    </td>                                
                </tr>
                <tr>
                    <td colspan="4">&nbsp;</td>
                </tr>
                </c:if>
                <c:if test="${!actionBean.editPTG}">
                <tr>
                    <td width="1%"><b>8.</b></td>
                    <td colspan="3"><div align="left"><b><font style="text-transform: capitalize">PERAKUAN PENGARAH TANAH DAN GALIAN</font></b></div></td>
                </tr>
                <c:set var="i" value="1" />
                <c:forEach items="${actionBean.senaraiLaporanKandunganptg1}" var="line">
                    <tr>
                        <td>&nbsp;</td>
                        <td valign="top"><c:out value="8.${i}"/></td>
                        <td colspan="2">
                                ${line.kandungan}<s:hidden name="kandungan2${i}" id="kandungan2${i}"/>
                        </td>
                     </tr>
                <c:set var="i" value="${i+1}" />        
                </c:forEach>
                <s:hidden name="rowCount8" value="${fn:length(actionBean.senaraiLaporanKandunganptg1)}" id="rowCount8"/>
                
                <tr>
                    <td colspan="4">&nbsp;</td>
                </tr>
                </c:if>
          </c:if>
          <%--<c:if test="${actionBean.openPTG}">
                <c:if test="${actionBean.edit}">
                    <c:if test="${actionBean.editPTG}">
                            <tr>
                                <td width="1%"><b>9.</b></td>
                                <td colspan="3"><div align="left"><b><font style="text-transform: capitalize">SYOR PENGARAH TANAH DAN GALIAN</font></b></div></td>
                            </tr>
                            <c:set var="i" value="1" />
                            <c:forEach items="${actionBean.senaraiLaporanKandunganptg2}" var="line">
                                <tr>
                                    <td>&nbsp;</td>
                                    <td valign="top"><c:out value="9.${i}"/></td>
                                    <td colspan="2">
                                            <s:textarea  id="kandungan9${i}"name="senaraiLaporanKandunganptg2[${i-1}].kandungan" cols="150"  rows="5" class="normal_text"/>
                                            <s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan},this.form)"></s:button>
                                   </td>
                                 </tr>
                            <c:set var="i" value="${i+1}" />        
                            </c:forEach>
                            <s:hidden name="rowCount9" value="${fn:length(actionBean.senaraiLaporanKandunganptg2)}" id="rowCount9"/>
                            <tr>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td colspan="2">
                                    <c:if test="${actionBean.editPTG}">
                                        <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('9',this.form)"></s:button> 
                                        <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('9',${i-1},this.form)"></s:button>
                                    </c:if>
                                    <c:if test="${!actionBean.editPTG}">
                                        &nbsp;
                                    </c:if>
                                </td>                                
                            </tr>
                            <tr>
                                <td colspan="4">&nbsp;</td>
                            </tr>
                    </c:if> 
                    <c:if test="${!actionBean.editPTG}">
                        <tr>
                                <td width="1%"><b>9.</b></td>
                                <td colspan="3"><div align="left"><b><font style="text-transform: capitalize">SYOR PENGARAH TANAH DAN GALIAN</font></b></div></td>
                            </tr>
                            <c:set var="i" value="1" />
                            <c:forEach items="${actionBean.senaraiLaporanKandunganptg2}" var="line">
                                <tr>
                                    <td>&nbsp;</td>
                                    <td valign="top"><c:out value="9.${i}"/></td>
                                    <td colspan="2">
                                            ${line.kandungan}<s:hidden name="kandungan3${i}" id="kandungan3${i}"/>                                            
                                   </td>
                                 </tr>
                            <c:set var="i" value="${i+1}" />        
                            </c:forEach>
                            <s:hidden name="rowCount9" value="${fn:length(actionBean.senaraiLaporanKandunganptg2)}" id="rowCount9"/>
                            
                            <tr>
                                <td colspan="4">&nbsp;</td>
                            </tr>
                    </c:if>
                </c:if>
          </c:if>--%>
          <%--<c:if test="${actionBean.editPTD}">
            <tr>
              <td colspan="4" align="center">
                  <s:button name="simpanNew2" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
              </td>
            </tr>
          </c:if> --%>
           <%--<c:if test="${actionBean.edit}">
               <c:if test="${!actionBean.ptd or !actionBean.ptg}">
                   <tr>
                      <td colspan="4" align="center">
                          <s:button name="simpanPTG" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                      </td>
                    </tr>
               </c:if>   
           </c:if>--%>
          
                                
    </table> 
</s:form>
