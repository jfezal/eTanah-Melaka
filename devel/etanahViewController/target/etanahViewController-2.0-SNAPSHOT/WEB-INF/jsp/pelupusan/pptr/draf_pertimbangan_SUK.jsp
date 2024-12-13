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
        $.post('${pageContext.request.contextPath}/pelupusan/draf_suk_pptr?simpanKandungan&index='+index+'&kandungan='+kand,q,
        function(data){
            $('#page_div').html(data);
        }, 'html');
  
       
       
    }
    function addRow(index,f)
    {
        var q = $(f).formSerialize();
        $.post('${pageContext.request.contextPath}/pelupusan/draf_suk_pptr?tambahRow&index='+index,q,
        function(data){
            $('#page_div').html(data);
        }, 'html');
    }
    function deleteRow(idKandungan,f)
    {
        if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
             var q = $(f).formSerialize();
            $.post('${pageContext.request.contextPath}/pelupusan/draf_suk_pptr?deleteRow&idKandungan='+idKandungan,q,
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
        $.post('${pageContext.request.contextPath}/pelupusan/draf_suk_pptr?showsyortolaklulus&syortolaklulus='+v,q,
        function(data){
            $('#page_div').html(data);
        }, 'html');
    }
    ///aded
    function refreshPage(v){
        var syortolaklulusdata = v;
        var url = '${pageContext.request.contextPath}/pelupusan/draf_suk_pptr?showsyortolaklulus&syortolaklulus='+syortolaklulusdata;
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
<s:form beanclass="etanah.view.stripes.pelupusan.DrafSUK_PPTRActionBean">
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
                        <p align="center">
                            <b>(KERTAS PERTIMBANGAN 
                                <c:if test="${actionBean.tanahrizabpermohonan1.penjaga eq 'SUK Negeri'}">
                                    Y.B SETIAUSAHA KERAJAAN NEGERI
                                </c:if>
                                <c:if test="${actionBean.tanahrizabpermohonan1.penjaga ne 'SUK Negeri'}">
                                      <c:out value="${fn:toUpperCase(actionBean.tanahrizabpermohonan1.penjaga)}"/> 
                                </c:if>
                                )
                                </b>
                        </p>
                        <div class="content" align="center">                            
                            <table border="0" width="80%" cellspacing="10%" align="center">
                                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PLPS' or actionBean.permohonan.kodUrusan.kod eq 'PPRU' or actionBean.permohonan.kodUrusan.kod eq 'PPTR'}">
                                    <tr><td id="tdLabel" ><b>
                                        <font style="text-transform: capitalize">
                                        <tr><td>
                                            <p><b>
                                                        <span style="text-transform: uppercase">${actionBean.tajukPlps}</span>
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
        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPTR' }">
            
                <tr>
                    <td>&nbsp;</td>
                    <td valign="top">1.1</td>
                    <td colspan="2">
                            ${actionBean.tujuanPlps}
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
                    <td colspan="3"><b> LATAR BELAKANG</b></td>
                </tr>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPTR' }">
                    <tr>
                        <td>&nbsp;</td>
                        <td width="1%">2.1</td>
                        <td colspan="2">
                                ${actionBean.perihalpermohonanPlps}
                        </td>
                    </tr>
                </c:if>
                    <tr>
                        <td>&nbsp;</td>
                        <td width="1%">2.2</td>
                        <td colspan="2">
                                ${actionBean.perihalpermohonan2}
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
<!--                            <tr>
                                <td><b>3.</b></td>
                                <td colspan="3"><b> Perihal Tanah</b></td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td width="1%" valign="top">3.1</td>
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
                                <td colspan="3">3.2 Tanah-tanah yang bersempadan dengan tanah yang dipohon adalah seperti berikut:-</td>
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
                                <td width="1%" valign="top"><c:if test="${actionBean.pemohon.statusKahwin eq 'Berkahwin'}">
                                    4.3
                                    </c:if> 
                                </td>
                                <td>
                                    
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
                                </td>
                            </tr>-->
                                <tr>
                                    <td width="1%"><b>3.</b></td>
                                    <td colspan="3"><div align="left"><b><font style="text-transform: capitalize">ULASAN JABATAN-JABATAN TEKNIKAL</font></b></div></td>
                                </tr>
                                <c:set var="i" value="1" />
                                <c:forEach items="${actionBean.senaraiLaporanKandunganUtil}" var="line">
                                <tr>
                                    <td>&nbsp;</td>
                                    <td valign="top"><c:out value="3.${i}"/></td>
                                    <td colspan="2"><font style="font-weight:bold;">${line.permohonanRujukanLuar.namaAgensi}</font></td>
                                </tr>
                                <tr>
                                    <td>&nbsp;</td>
                                    <td valign="top">&nbsp;</td>
                                    <td colspan="2">( Ruj. Surat ${line.permohonanRujukanLuar.noRujukan} bertarikh <s:format value="${line.permohonanRujukanLuar.tarikhRujukan}" formatPattern="dd/MM/yyyy"/> )</td>
                                </tr>
                                <tr>
                                    <td>&nbsp;</td>
                                    <td>&nbsp;</td>
                                    <td colspan="2">
                                        <%--<c:if test="${!actionBean.edit}">
                                            <s:textarea name="ulasan${i}" id="ulasan${i}" value="${line.permohonanRujukanLuar.ulasan}" class="normal_text" rows="6" cols="100" ></s:textarea>
                                            <s:hidden name="rowAgensi${i}" value="${line.permohonanRujukanLuar.idRujukan}" id="rowAgensi${i}"/>
                                        </c:if>--%>
                                        <%--<c:if test="${actionBean.edit}">--%>
                                            ${line.permohonanRujukanLuar.ulasan}
                                        <%--</c:if>--%>
                                    </td>
                                </tr>

                                <c:set var="i" value="${i+1}" />
                                </c:forEach>
                                <c:if test="${actionBean.ulasanEmpty}">
                                    <tr>
                                        <td>&nbsp;</td>
                                        <td colspan="3">Tiada Ulasan Jabatan Teknikal</td>
                                    </tr>                                    
                                </c:if>
                            <tr>
                                <td colspan="4">&nbsp;</td>
                            </tr>
                            <tr>
                                <td width="1%"><b>4.</b></td>
                                <td colspan="3"><div align="left"><b><font style="text-transform: capitalize">ASAS-ASAS PERTIMBANGAN</font></b></div></td>
                            </tr>
                            <c:set var="i" value="1" />
                            <c:forEach items="${actionBean.senaraiLaporanKandunganptg1}" var="line">
                                <tr>
                                    <td>&nbsp;</td>
                                    <td valign="top"><c:out value="4.${i}"/></td>
                                    <td colspan="2">
                                            <s:textarea  id="kandungan4${i}"name="senaraiLaporanKandunganptg1[${i-1}].kandungan" cols="150"  rows="5" class="normal_text"/>
                                            <s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan},this.form)"></s:button>
                                   </td>
                                 </tr>
                            <c:set var="i" value="${i+1}" />        
                            </c:forEach>
                            <s:hidden name="rowCount4" value="${fn:length(actionBean.senaraiLaporanKandunganptg1)}" id="rowCount8"/>
                            <tr>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td colspan="2">
                                    <c:if test="${actionBean.editPTD}">
                                        <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('4',this.form)"></s:button> 
                                        <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('4',${i-1},this.form)"></s:button>
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
                                <td width="1%"><b>5.</b></td>
                                <td colspan="3"><b><font style="text-transform: capitalize">SYOR</font></b></td>
                            </tr>
                            <tr>
                                <td width="1%">&nbsp;</td>
                                <td>&nbsp;
                                </td>
                                <td colspan="2"> Syor :                                   
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
                            <tr>
                                <td><b>6.</b></td>
                                <td colspan="3"><b>CADANGAN</b></td>
                            </tr>
                                <tr>
                                    <td>&nbsp;</td>
                                    <td colspan="3">
                                        <c:if test="${actionBean.tanahrizabpermohonan1.penjaga eq 'SUK Negeri'}">
                                            Y.B SETIAUSAHA KERAJAAN NEGERI
                                        </c:if>
                                        <c:if test="${actionBean.tanahrizabpermohonan1.penjaga ne 'SUK Negeri'}">
                                            ${actionBean.tanahrizabpermohonan1.penjaga}
                                        </c:if>
                                     adalah dipohon untuk mempertimbangkan semada bersetuju atau sebaliknya dengan syor di para 5 di atas.</td>
                                </tr>
    </table>
</s:form>