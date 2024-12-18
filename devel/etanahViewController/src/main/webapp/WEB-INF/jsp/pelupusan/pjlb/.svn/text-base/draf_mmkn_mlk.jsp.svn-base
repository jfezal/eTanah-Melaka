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
        if('${actionBean.permohonan.kodUrusan.kod}' == "PCRG"){
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
        $.post('${pageContext.request.contextPath}/pelupusan/draf_mmk_pjlb?simpanKandungan&index='+index+'&kandungan='+kand,q,
        function(data){
            $('#page_div').html(data);
        }, 'html');
  
       
       
    }
    function addRow(index,f)
    {
        var q = $(f).formSerialize();
        $.post('${pageContext.request.contextPath}/pelupusan/draf_mmk_pjlb?tambahRow&index='+index,q,
        function(data){
            $('#page_div').html(data);
        }, 'html');
    }
    function deleteRow(idKandungan,f)
    {
        if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
            var q = $(f).formSerialize();
            $.post('${pageContext.request.contextPath}/pelupusan/draf_mmk_pjlb?deleteRow&idKandungan='+idKandungan,q,
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
        $.post('${pageContext.request.contextPath}/pelupusan/draf_mmk_pjlb?showsyortolaklulus&syortolaklulus='+v,q,
        function(data){
            $('#page_div').html(data);
        }, 'html');
    }
    ///aded
    function refreshPage(v){
        var syortolaklulusdata = v;
        var url = '${pageContext.request.contextPath}/pelupusan/draf_mmk_pjlb?showsyortolaklulus&syortolaklulus='+syortolaklulusdata;
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
    function openImage(type,i,imageU){
        var idDokumen;
        if(type=='U')
            idDokumen = document.getElementById("imejU"+i).options[document.getElementById("imejU"+i).selectedIndex].value;                            
        else if(type=='S')
            idDokumen = document.getElementById("imejS"+i).options[document.getElementById("imejS"+i).selectedIndex].value;
        else if(type=='T')
            idDokumen = document.getElementById("imejT"+i).options[document.getElementById("imejT"+i).selectedIndex].value;
        else if(type=='B')
            idDokumen = document.getElementById("imejT"+i).options[document.getElementById("imejB"+i).selectedIndex].value;
        var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.stripes.pelupusan.DrafMMK_PJLBActionBean">
    <s:messages/>
    <s:errors/>
    <s:hidden name="kertasK.idKertas"/>
    <s:hidden name="edit" id="edit"/>
    <s:hidden name="editPTD" id="editPTD"/>
    <s:hidden name="editPTG" id="editPTG"/>
    <s:hidden name="openPTG" id="openPTG"/>
    <s:hidden name="firstTimeOpen" id="firstTimeOpen"/>

    <table width="90%" border="0">
        <tr>
            <td colspan="4">
                <div class="subtitle" style="text-transform: capitalize">
                    <fieldset class="aras1">
                        <legend> </legend>
                        
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PCRG'}">
                            <c:if test="${actionBean.stageId eq '25SedDrafKrtsMMK'}">
                                 No. Rujukan : <s:text name="no_ruj" id="no_ruj" size="20"/><br><br>
                            </c:if>
                            <c:if test="${actionBean.stageId eq '26SmkDrafMMK' or actionBean.stageId eq '27SmkDrafMMK' or actionBean.stageId eq '28PerakukanKrtsMMK'
                                  or actionBean.stageId eq '29SmkPerakuanPTG' or actionBean.stageId eq '30SmkPerakuanPTG' or actionBean.stageId eq '30ATrmFailSmkdanArh' or actionBean.stageId eq '30BTrmFailSmkdanArh' or actionBean.stageId eq '30CSmkdanKmskniKrtsMMK'}">
                                No. Rujukan : ${actionBean.permohonankertas.noLaporan}<br><br>
                            </c:if>
                        </c:if>
                                
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'MPCRG'}">
                            <c:if test="${actionBean.stageId eq '04PenydanDraf'}">
                                 No. Rujukan : <s:text name="no_ruj" id="no_ruj" size="20"/><br><br>
                            </c:if>
                            <c:if test="${actionBean.stageId eq '05SmkDrafMMK' or actionBean.stageId eq '06SmkdanSyor' or actionBean.stageId eq '07SmkdanSyor'
                                  or actionBean.stageId eq '08SmkdanPerakukan' or actionBean.stageId eq '09SmkDrafMMK' or actionBean.stageId eq '10SmkPerakuan' or actionBean.stageId eq '11SmkPerakuanPTG' or actionBean.stageId eq '11ATerimaFail' 
                                  or actionBean.stageId eq '11BTerimaFail' or actionBean.stageId eq '12CtkKrtsMMK' or actionBean.stageId eq '17MngmblTndkn'
                                  or actionBean.stageId eq '17ATrmUlasanJbtnTknkl' or actionBean.stageId eq '18SmkdanMsknSyor' or actionBean.stageId eq '19SmkdanMsknSyor' or actionBean.stageId eq '20TrmdanPerakukan'}">
                                No. Rujukan : ${actionBean.permohonankertas.noLaporan}<br><br>
                            </c:if>
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PJLB'}">
                            <c:if test="${actionBean.stageId eq '31SediakanDrafMMK'}">
                                 No. Rujukan : <s:text name="no_ruj" id="no_ruj" size="20"/><br><br>
                            </c:if>
                            <c:if test="${actionBean.stageId eq '32SmkDrfKrtsMMK' or actionBean.stageId eq '33SmkDrfKrtsMMK' or actionBean.stageId eq '34PerakukanKrtsMMK'
                                  or actionBean.stageId eq '35SmkPerakuanPTG' or actionBean.stageId eq '36SmkPerakuanPTG' or actionBean.stageId eq '37SmkPerakuanPTG' or actionBean.stageId eq '48SmkdanMklmMklmtKmskni' or actionBean.stageId eq '49TrmdanPerakukanJKSMN' 
                                  or actionBean.stageId eq '38TrmFail' or actionBean.stageId eq '39TrmFail' or actionBean.stageId eq '40SmkdanKmskniKrtsMMK'
                                  or actionBean.stageId eq '45MengemaskiniKrtsMMK' or actionBean.stageId eq '45ATrmUlsnTknkl' or actionBean.stageId eq '46SmkMklmtKmskni' or actionBean.stageId eq '47SmkdanMklmMklmtKmskni'}">
                                No. Rujukan : ${actionBean.permohonankertas.noLaporan}<br><br>
                            </c:if>
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'MPJLB'}">
                            <c:if test="${actionBean.stageId eq '62SedDrafKrtsMMK'}">
                                 No. Rujukan : <s:text name="no_ruj" id="no_ruj" size="20"/><br><br>
                            </c:if>
                            <c:if test="${actionBean.stageId eq '63SmkKrtsMMK' or actionBean.stageId eq '64SmkKrtsMMK' or actionBean.stageId eq '65SmkDraf'
                                  or actionBean.stageId eq '66PerakukanKrtsMMK' or actionBean.stageId eq '67SmkPerakuanPTG' or actionBean.stageId eq '68SmkPerakuanPTG' or actionBean.stageId eq '69TrmFail' or actionBean.stageId eq '70TrmFail' 
                                  or actionBean.stageId eq '72SmkKmskniKrtsMMK' or actionBean.stageId eq '33ATrmFail' or actionBean.stageId eq '34ATrmFail'
                                  or actionBean.stageId eq '35ASmkdanKmskniKrtsMMK' or actionBean.stageId eq '54MengambilTndkn' or actionBean.stageId eq '55TrmUlsnTeknikal' or actionBean.stageId eq '56SemakKrtsMMK' or actionBean.stageId eq '57SmkdanMklm'
                                  or actionBean.stageId eq '58SmkdanMklm' or actionBean.stageId eq '59TrmdanPerakukanMMK'}">
                                No. Rujukan : ${actionBean.permohonankertas.noLaporan}<br><br>
                            </c:if>
                        </c:if>
                        <p align="center">
                            <b>DOKUMEN INI ADALAH HAKMILIK KERAJAAN NEGERI SEMBILAN<BR>KERTAS MAJLIS MESYUARAT KERAJAAN NEGERI SEMBILAN</b>
                        </p>
                        <div class="content" align="center">                            
                            <table border="0" width="80%" cellspacing="10%" align="center">
                                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PCRG' || actionBean.permohonan.kodUrusan.kod eq 'MPCRG' || actionBean.permohonan.kodUrusan.kod eq 'PJLB' || actionBean.permohonan.kodUrusan.kod eq 'MPJLB'}">
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
        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PCRG' or actionBean.permohonan.kodUrusan.kod eq 'MPCRG' or actionBean.permohonan.kodUrusan.kod eq 'PJLB' or actionBean.permohonan.kodUrusan.kod eq 'MPJLB' or actionBean.permohonan.kodUrusan.kod eq 'LSTP'}">

            <tr>
                <td>&nbsp;</td>
                <td valign="top">1.1</td>
                <td colspan="2">
                    <c:if test="${actionBean.edit}">
                        <s:textarea rows="6" cols="150" name="tujuanPlps" class="normal_text"/>
                    </c:if>
                    <c:if test="${!actionBean.edit}">
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
            <td colspan="3"><b> LATAR BELAKANG</b></td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td width="1%" valign="top">2.1</td>
            <td colspan="2">
                <c:if test="${actionBean.edit}">
                    <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B'|| actionBean.pihak.jenisPengenalan.kod eq 'L'|| actionBean.pihak.jenisPengenalan.kod eq 'P'||actionBean.pihak.jenisPengenalan.kod eq 'T'||actionBean.pihak.jenisPengenalan.kod eq 'I'||actionBean.pihak.jenisPengenalan.kod eq 'O'||actionBean.pihak.jenisPengenalan.kod eq 'N'||actionBean.pihak.jenisPengenalan.kod eq 'F'}">
                        <s:textarea rows="6" cols="150" name="perihalpemohon" class="normal_text" />
                    </c:if>
                    <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'U' || actionBean.pihak.jenisPengenalan.kod eq 'D' || actionBean.pihak.jenisPengenalan.kod eq 'S'}">
                        <s:textarea rows="6" cols="150" name="perihalpemohon_s" class="normal_text" />
                    </c:if>                                        
                </c:if>
                
                <c:if test="${!actionBean.edit}">
                    <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B'|| actionBean.pihak.jenisPengenalan.kod eq 'L'|| actionBean.pihak.jenisPengenalan.kod eq 'P'||actionBean.pihak.jenisPengenalan.kod eq 'T'||actionBean.pihak.jenisPengenalan.kod eq 'I'||actionBean.pihak.jenisPengenalan.kod eq 'O'||actionBean.pihak.jenisPengenalan.kod eq 'N'||actionBean.pihak.jenisPengenalan.kod eq 'F'}">
                        ${actionBean.perihalpemohon}
                    </c:if>
                    <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'U' || actionBean.pihak.jenisPengenalan.kod eq 'D' || actionBean.pihak.jenisPengenalan.kod eq 'S'}">
                        ${actionBean.perihalpemohon_s}
                    </c:if>
                </c:if>
            </td>
        </tr>

        <%--<c:set var="i" value="3" />
        <c:set var="num" value="2"/>
        <c:forEach items="${actionBean.senaraiLaporanKandungan1}" var="line">
            <tr>
                <td>&nbsp;</td>
                <td valign="top"><c:out value="2.${num}"/></td>
                <td colspan="2">
                    <c:if test="${actionBean.edit}">
                        <s:textarea  id="kandungan2${i}" name="kandungan2[${i-1}].kandungan" cols="150"  rows="5" class="normal_text"/><br>
                        <s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan},this.form)"></s:button>
                    </c:if>
                    <c:if test="${!actionBean.edit}">
                        ${line.kandungan}
                    </c:if>
                </td>
            </tr>
            <c:set var="i" value="${i+1}" />
            <c:set var="num" value="${num+1}"/>
        </c:forEach>--%>

       <c:set var="i" value="1" />
                <c:forEach items="${actionBean.senaraiLaporanKandungan1}" var="line">
                    <tr>
                        <td>&nbsp;</td>
                        <td valign="top">

                            <c:if test="${actionBean.kodNegeri eq '05'}">
                                <c:out value="2.${i}"/>
                            </c:if>
                            </td>
                        <td colspan="2">
                            <c:if test="${actionBean.edit}">
                                <s:textarea  id="kandungan2${i}"name="senaraiLaporanKandungan1[${i-1}].kandungan" cols="150"  rows="5" class="normal_text"/><br>
                                <s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan},this.form)"></s:button>
                            </c:if>
                            <c:if test="${!actionBean.edit}">
                                ${line.kandungan}
                            </c:if>

                        </td>
                    </tr>
                    <c:set var="i" value="${i+1}" />
                </c:forEach>
                <s:hidden name="rowCount5" value="${fn:length(actionBean.senaraiLaporanKandungan1)}" id="rowCount5"/>

        <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td colspan="2">
                <c:if test="${actionBean.edit}">
                    <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('2',this.form)"></s:button> 
                    <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('2',${i-1},this.form)"></s:button>
                </c:if>
                <c:if test="${!actionBean.edit}">
                    &nbsp;
                </c:if>
            </td>                                
        </tr>
        <tr>
            <td colspan="4">&nbsp;</td>
        </tr>
        <tr>
            <td><b>3.</b></td>
            <td colspan="3"><b> PERIHAL TANAH</b></td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td width="1%" valign="top">3.1</td>
            <td colspan="2">
                <c:if test="${actionBean.edit}">
                    <s:textarea rows="6" cols="150" name="perihaltanah" class="normal_text" />
                </c:if>
                <c:if test="${!actionBean.edit}">
                    ${actionBean.perihaltanah}
                </c:if>
            </td>
        </tr>
        <tr>
            <td colspan="4">&nbsp;</td>
        </tr>
        
       
        <tr>
            <td width="1%"><b>4.</b></td>
            <td colspan="3"><div align="left"><b><font style="text-transform: capitalize">ULASAN JABATAN-JABATAN TEKNIKAL</font></b></div></td>
        </tr>
        <c:set var="i" value="1" />
        <c:forEach items="${actionBean.senaraiLaporanKandunganUtil}" var="line">
            <tr>
                <td>&nbsp;</td>
                <td valign="top"><c:out value="4.${i}"/></td>
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
                 <!--   <c:if test="${!actionBean.edit}">
                        <s:textarea name="ulasan${i}" id="ulasan${i}"   class="normal_text" rows="6" cols="100" ></s:textarea>
                        <s:hidden name="rowAgensi${i}" value="${line.permohonanRujukanLuar.idRujukan}" id="rowAgensi${i}"/>
                    </c:if> -->
                    
                        ${line.permohonanRujukanLuar.ulasan}
                    
                </td>
            </tr>
            <c:set var="i" value="${i+1}" />        
                </c:forEach>

            <tr>
                <td colspan="4">&nbsp;</td>
            </tr>
            
          
                <tr>
                    <td width="1%"><b>
                            
                            <c:if test="${actionBean.kodNegeri eq '05'}">
                                5.
                            </c:if>
                        </b></td>
                    <td colspan="3"><div align="left"><b><font style="text-transform: capitalize">ULASAN TIMBALAN PENGARAH TANAH DAN GALIAN NEGERI SEMBILAN DARUL KHUSUS</font></b></div></td>
                </tr>
                <c:set var="i" value="1" />
                <c:forEach items="${actionBean.senaraiLaporanKandunganptg1}" var="line">
                    <tr>
                        <td>&nbsp;</td>
                        <td valign="top">
                            
                            <c:if test="${actionBean.kodNegeri eq '05'}">
                                <c:out value="5.${i}"/>
                            </c:if>
                            </td>
                        <td colspan="2">
                            <c:if test="${actionBean.edit}">
                                <s:textarea  id="kandungan5${i}"name="senaraiLaporanKandunganptg1[${i-1}].kandungan" cols="150"  rows="5" class="normal_text"/><br>
                                <s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan},this.form)"></s:button>
                            </c:if>
                            <c:if test="${!actionBean.edit}">
                                ${line.kandungan}
                            </c:if>
                        
                        </td>
                    </tr>
                    <c:set var="i" value="${i+1}" />        
                </c:forEach>
                <s:hidden name="rowCount5" value="${fn:length(actionBean.senaraiLaporanKandunganptg1)}" id="rowCount5"/>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td colspan="2">
                        <c:if test="${actionBean.edit}">
                            <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('5',this.form)"></s:button> 
                            <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('5',${i-1},this.form)"></s:button>
                        </c:if>
                        
                    </td>                                
                </tr>
                <tr>
                    <td colspan="4">&nbsp;</td>
                </tr>
       
           
        <tr>
            <td width="1%"><b>6.</b></td>
            <td colspan="3"><b><font style="text-transform: capitalize"> SYOR JAWATANKUASA SUMBER MINERAL NEGERI SEMBILAN DARUL KHUSUS</font></b></td>
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
                    <%--<c:if test="${actionBean.fasaPermohonan.keputusan.kod eq 'SL'}">
                        Syor Lulus
                    </c:if>
                    <c:if test="${actionBean.fasaPermohonan.keputusan.kod eq 'ST'}">
                        Syor Tolak
                    </c:if>--%>
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
               
                <td valign="top">
                    <c:out value="6.${i}"/>
                </td>
                <td>
                    <c:if test="${actionBean.editPTD}">
                        <s:textarea  id="kandungan6${i}" name="senaraiLaporanKandunganpbtanah[${i-1}].kandungan" cols="150"  rows="5" class="normal_text"/><br>
                        <s:button value="Hapus" class="btn" name="hapus7" onclick="deleteRow(${line.idKandungan},this.form)"></s:button>
                        <s:hidden name="rowCount6" value="${fn:length(actionBean.senaraiLaporanKandunganpbtanah)}" id="rowCount7"/>
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
                    <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('6',this.form)"></s:button> 
                    <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('6',${i-1},this.form)"></s:button>
                </c:if>
                <c:if test="${!actionBean.editPTD}">
                    &nbsp;
                </c:if>
            </td>                                
        </tr>
        <tr>
            <td colspan="4">&nbsp;</td>
        </tr>
<!--        <tr>
            <td>&nbsp;</td>
            <td colspan="3">

                <c:if test="${actionBean.syortolaklulus eq 'SL'}">
                    ${actionBean.pentabirtanah2}
                </c:if>
            </td>
        </tr>-->
       
    </table>
    <table width="90%" border="0">
        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PCRG' or actionBean.permohonan.kodUrusan.kod eq 'MPCRG'}">
            <c:if test="${actionBean.syortolaklulus eq 'SL'}">
                
                <tr>
                    <td align="right" id="tdDisplay">Kegunaan</td>
                    <td align="center">:</td>
                    <td align="left" colspan="2">
                        <c:if test="${actionBean.editPTD}">
                          
                            ${actionBean.permohonanPermitItem.kodItemPermit.nama}
                           
                            <c:if test="${actionBean.permohonanPermitItem.kodItemPermit.kod eq 'LN'}">
                                - ${actionBean.hakmilikPermohonan.catatan}
                            </c:if>                    
                        </c:if>
                        <c:if test="${!actionBean.editPTD}">
                            ${actionBean.permohonanPermitItem.kodItemPermit.nama}
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td align="right" id="tdDisplay">Luas</td>
                    <td align="center">:</td>
                    <td align="left" colspan="2">
                        <c:if test="${actionBean.editPTD}">
                            ${actionBean.hakmilikPermohonan.luasTerlibat} 
                            ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}
                            
                        </c:if>
                        <c:if test="${!actionBean.editPTD}">
                            ${actionBean.hakmilikPermohonan.luasTerlibat} 
                            ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}
                        </c:if>        
                    </td>
                </tr>
                <tr>
                    <td align="right" id="tdDisplay">Fi Pengeluaran</td>
                    <td align="center">:</td>
                    <td align="left" colspan="2">
                        <c:if test="${actionBean.editPTD}">
                            <%--     <s:text name="permohonanTuntutanKos.idKos" /> --%>
                            RM <s:format value="${actionBean.permohonanTuntutanKos.amaunTuntutan}" formatPattern="###,###.00"/> &nbsp;setahun
                        </c:if>
                        <c:if test="${!actionBean.editPTD}">    
                            RM <s:format value="${actionBean.permohonanTuntutanKos.amaunTuntutan}" formatPattern="###,###.00"/> &nbsp;setahun
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td align="right" id="tdDisplay">Fi Pegangan Tahunan</td>
                    <td align="center">:</td>
                    <td align="left" colspan="2">
                        <c:if test="${actionBean.editPTD}">
                            <%--     <s:text name="permohonanTuntutanKos.idKos" /> --%>
                            RM <s:format value="${actionBean.permohonanTuntutanKos.amaunTuntutan}" formatPattern="###,###.00"/> &nbsp;setahun
                        </c:if>
                        <c:if test="${!actionBean.editPTD}">    
                            RM <s:format value="${actionBean.permohonanTuntutanKos.amaunTuntutan}" formatPattern="###,###.00"/> &nbsp;setahun
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td align="right" id="tdDisplay">Surat Akuan</td>
                    <td align="center">:</td>
                    <td align="left" colspan="2">Seperti yang dinyatakan di dalam Borang D, Enakmen Mineral Negeri 2002 dan syarat-syarat lain yang berkuatkuasa.</td>
                </tr>
                <tr>
                    <td align="right" id="tdDisplay">Syarat Tambahan</td>
                    <td align="center">:</td>
                    <td align="left" colspan="2">
                        i) Pemegang Lesen hendaklah mematuhi syarat-syarat yang akan ditetapkan oleh Jabatan-jabatan Teknikal serta Pejabat Pengarah Tanah dan Galian Negeri Sembilan Darul Khusus. 
                    </td>
                </tr>
                <tr>
                    <td align="right" id="tdDisplay">&nbsp;</td>
                    <td align="center">&nbsp;</td>
                    <td align="left" colspan="2">ii) Pemegang Lesen adalah tertakluk kepada Peruntukan-peruntukan di bawah Enakmen Mineral Negeri 2002 dan Peraturan-peraturan Mineral Negeri 2007.</td>
                </tr>
                <tr>
                    <td align="right" id="tdDisplay">&nbsp</td>
                    <td align="center">&nbsp;</td>
                    <td align="left" colspan="2">iii) Pemohon perlu mendapatkan surat kebenaran bertulis daripada pihak untuk menggunakan laluan sedia ada dalam kawasan tersebut sebelum lesen dikeluaran.</td>
                </tr>
                
                <tr>
                    <td align="right" colspan="4">&nbsp</td>
                </tr>
            </c:if>
        </c:if>
        
    </table>
    
              
</s:form>