<%--
    Document   : draf_mmkn_pptr
    Author   : Shazwan
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

        if(index == 2){
            kand = document.getElementById("kandungan2"+i).value;
        }
        if(index == 3){
            kand = document.getElementById("kandungan3"+i).value;
            <%--alert(kand);--%>
        }
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
            <%--alert(kand);--%>
        }
        if(index==8){
            kand = document.getElementById("kandungan8"+i).value;
        }
        if(index==9){
            kand = document.getElementById("kandungan9"+i).value;
        }
        var q = $(f).formSerialize();
        $.post('${pageContext.request.contextPath}/pelupusan/draf_mmkn_pptr?simpanKandungan&index='+index+'&kandungan='+kand,q,
        function(data){
            $('#page_div').html(data);
        }, 'html');
  
       
       
    }
    function addRow(index,f)
    {
        var q = $(f).formSerialize();
        $.post('${pageContext.request.contextPath}/pelupusan/draf_mmkn_pptr?tambahRow&index='+index,q,
        function(data){
            $('#page_div').html(data);
        }, 'html');
    }
    function deleteRow(idKandungan,f)
    {
        if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
            var q = $(f).formSerialize();
            $.post('${pageContext.request.contextPath}/pelupusan/draf_mmkn_pptr?deleteRow&idKandungan='+idKandungan,q,
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
        $.post('${pageContext.request.contextPath}/pelupusan/draf_mmkn_pptr?showsyortolaklulus&syortolaklulus='+v,q,
        function(data){
            $('#page_div').html(data);
        }, 'html');
    }
    ///aded
    function refreshPage(v){
        var syortolaklulusdata = v;
        var url = '${pageContext.request.contextPath}/pelupusan/draf_mmkn_pptr?showsyortolaklulus&syortolaklulus='+syortolaklulusdata;
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
<s:form beanclass="etanah.view.stripes.pelupusan.DrafMMKN_PPTRActionBean">
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
                            <b>(MAJLIS MESYUARAT KERAJAAN NEGERI)</b>
                        </p>
                        <div class="content" align="center">                            
                            <table border="0" width="80%" cellspacing="10%" align="center">
                                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PLPS' or actionBean.permohonan.kodUrusan.kod eq 'PPRU' or actionBean.permohonan.kodUrusan.kod eq 'PPTR'}">
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
        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPTR' }">

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
        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPTR' }">
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


        <%--FOR PERIHAL TANAH--%>
        <c:set var="i" value="2" />
        <c:forEach items="${actionBean.senaraiPerihalTanah}" var="line">

            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>

                <td valign="top"><c:out value="3.${i}"/></td>
                <td>
                    <c:if test="${actionBean.editPTD}">
                        <s:textarea id="kandungan3${i}" name="senaraiPerihalTanah[${i-2}].kandungan" cols="150"  rows="5" class="normal_text"/>
                        <s:button value="Hapus" class="btn" name="hapus3" onclick="deleteRow(${line.idKandungan},this.form)"></s:button>
                        <s:hidden name="rowCount3" value="${fn:length(actionBean.senaraiPerihalTanah)}" id="rowCount3"/>
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
            <td>&nbsp;</td>
            <td>
                <c:if test="${actionBean.editPTD}">
                    <s:button value="Tambah Perihal" class="btn" name="simpan1"  onclick="addRow('3',this.form)"></s:button>
                    <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('3',${i-1},this.form)"></s:button>
                </c:if>
                <c:if test="${!actionBean.editPTD}">
                    &nbsp;
                </c:if>
            </td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td colspan="3">
                3.${i} Tanah-tanah yang bersempadan dengan tanah yang dipohon adalah seperti berikut:-</td>
        </tr>
    </table>
    <div id ="lotsempadan">
        <div class="content" align="center">
            <table class="tablecloth">
                <tr>
                    <th>&nbsp;</th><th>No. Hakmilik</th><th>Kegunaan Tanah</th><th>Keadaan Tanah</th><th>Jarak Dari Tanah Dipohon</th><th>Catatan</th><th>Milik Kerajaan</th>
                    <%--UTARA--%>
                    <c:if test="${!empty actionBean.disLaporanTanahSempadan.listLaporTanahSpdnU}">
                        <c:set var="i" value="1" />
                    <tr>
                        <th rowspan="${actionBean.uSize}">
                            Utara
                        </th>
                        <c:forEach items="${actionBean.disLaporanTanahSempadan.listLaporTanahSpdnU}" var="line">

                            <td>
                                ${line.laporanTanahSmpdn.hakmilik.idHakmilik}
                                <s:hidden  id="kandunganSpdnUHM${i}" name="listLaporTanahSpdnU[${i-1}].laporanTanahSmpdn.hakmilik.idHakmilik" />
                            </td>
                            <td>
                                ${line.laporanTanahSmpdn.guna}
                                <s:hidden   id="kandunganSpdnUKEG${i}" name="listLaporTanahSpdnU[${i-1}].laporanTanahSmpdn.guna" />
                            </td>
                            <td>
                                ${line.laporanTanahSmpdn.keadaanTanah}
                                <s:hidden   id="kandunganSpdnUKEA${i}" name="listLaporTanahSpdnU[${i-1}].laporanTanahSmpdn.keadaanTanah" />
                            </td>
                            <td>
                                <s:format formatPattern="#,###,##0.0000" value="${line.laporanTanahSmpdn.jarak}"/>  ${line.laporanTanahSmpdn.jarakUom.nama}
                                <s:hidden   id="kandunganSpdnUJ${i}" name="disLaporanTanahSempadan.listLaporTanahSpdnU[${i-1}].laporanTanahSmpdn.jarak" />
                            </td>
                            <td>
                                ${line.laporanTanahSmpdn.catatan}
                                <s:hidden   id="kandunganSpdnUCAT${i}" name="listLaporTanahSpdnU[${i-1}].laporanTanahSmpdn.catatan" />
                            </td>
                            <td>
                                <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'Y'}">Kerajaan</c:if>
                                <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'T'}">Milik</c:if>
                                <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'N'}">--</c:if>
                                <s:hidden  name="disLaporanTanahSempadan.listLaporTanahSpdnU[${i-1}].laporanTanahSmpdn.milikKerajaan" id="kandunganSpdnUMK${i}"/>

                            </td>
                        </tr>

                        <c:set var="i" value="${i+1}" />        
                    </c:forEach>
                </c:if>

                <%--END OF UTARA--%>                             
                <%--SELATAN--%>
                <c:if test="${!empty actionBean.disLaporanTanahSempadan.listLaporTanahSpdnS}">
                    <c:set var="i" value="1" />
                    <tr>
                        <th rowspan="${actionBean.sSize}">
                            Selatan
                        </th>
                        <c:forEach items="${actionBean.disLaporanTanahSempadan.listLaporTanahSpdnS}" var="line">

                            <td>
                                ${line.laporanTanahSmpdn.hakmilik.idHakmilik}
                                <s:hidden  id="kandunganSpdnSHM${i}" name="listLaporTanahSpdnS[${i-1}].laporanTanahSmpdn.hakmilik.idHakmilik" />
                            </td>
                            <td>
                                ${line.laporanTanahSmpdn.guna}
                                <s:hidden  id="kandunganSpdnSKEG${i}" name="listLaporTanahSpdnS[${i-1}].laporanTanahSmpdn.guna" />
                            </td>
                            <td>
                                ${line.laporanTanahSmpdn.keadaanTanah}
                                <s:hidden  id="kandunganSpdnSKEA${i}" name="listLaporTanahSpdnS[${i-1}].laporanTanahSmpdn.keadaanTanah" />
                            </td>
                            <td>
                                <s:format formatPattern="#,###,##0.0000" value="${line.laporanTanahSmpdn.jarak}"/>  ${line.laporanTanahSmpdn.jarakUom.nama}
                                <s:hidden   id="kandunganSpdnSJ${i}" name="disLaporanTanahSempadan.listLaporTanahSpdnU[${i-1}].laporanTanahSmpdn.jarak" />
                            </td>
                            <td>
                                ${line.laporanTanahSmpdn.catatan}
                                <s:hidden  id="kandunganSpdnSCAT${i}" name="listLaporTanahSpdnS[${i-1}].laporanTanahSmpdn.catatan" />
                            </td>
                            <td>
                                <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'Y'}">Kerajaan</c:if>
                                <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'T'}">Milik</c:if>
                                <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'N'}">--</c:if>
                                <s:hidden name="disLaporanTanahSempadan.listLaporTanahSpdnS[${i-1}].laporanTanahSmpdn.milikKerajaan" id="kandunganSpdnSMK${i}"/>

                            </td>
                        </tr>

                        <c:set var="i" value="${i+1}" />        
                    </c:forEach>
                </c:if>

                <%--END OF SELATAN--%>
                <%--TIMUR--%>
                <c:if test="${!empty actionBean.disLaporanTanahSempadan.listLaporTanahSpdnT}">
                    <c:set var="i" value="1" />
                    <tr>
                        <th rowspan="${actionBean.tSize}">
                            Timur
                        </th>
                        <c:forEach items="${actionBean.disLaporanTanahSempadan.listLaporTanahSpdnT}" var="line">

                            <td>
                                ${line.laporanTanahSmpdn.hakmilik.idHakmilik}
                                <s:hidden  id="kandunganSpdnTHM${i}" name="listLaporTanahSpdnT[${i-1}].laporanTanahSmpdn.hakmilik.idHakmilik"  />
                            </td>
                            <td>
                                ${line.laporanTanahSmpdn.guna}
                                <s:hidden  id="kandunganSpdnTKEG${i}" name="listLaporTanahSpdnT[${i-1}].laporanTanahSmpdn.guna"  />
                            </td>
                            <td>
                                ${line.laporanTanahSmpdn.keadaanTanah}
                                <s:hidden  id="kandunganSpdnTKEA${i}" name="listLaporTanahSpdnT[${i-1}].laporanTanahSmpdn.keadaanTanah"  />
                            </td>
                            <td>
                                <s:format formatPattern="#,###,##0.0000" value="${line.laporanTanahSmpdn.jarak}"/>  ${line.laporanTanahSmpdn.jarakUom.nama}
                                <s:hidden   id="kandunganSpdnTJ${i}" name="disLaporanTanahSempadan.listLaporTanahSpdnU[${i-1}].laporanTanahSmpdn.jarak" />
                            </td>
                            <td>
                                ${line.laporanTanahSmpdn.catatan}
                                <s:hidden  id="kandunganSpdnTCAT${i}" name="listLaporTanahSpdnT[${i-1}].laporanTanahSmpdn.catatan"  />
                            </td>
                            <td>
                                <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'Y'}">Kerajaan</c:if>
                                <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'T'}">Milik</c:if>
                                <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'N'}">--</c:if>
                                <s:hidden name="disLaporanTanahSempadan.listLaporTanahSpdnT[${i-1}].laporanTanahSmpdn.milikKerajaan" id="kandunganSpdnTMK${i}" />

                            </td>
                        </tr>

                        <c:set var="i" value="${i+1}" />        
                    </c:forEach>

                </c:if>

                <%--END OF TIMUR--%>
                <%--BARAT--%>
                <c:if test="${!empty actionBean.disLaporanTanahSempadan.listLaporTanahSpdnB}">
                    <c:set var="i" value="1" />
                    <tr>
                        <th rowspan="${actionBean.bSize}">
                            Barat
                        </th>
                        <c:forEach items="${actionBean.disLaporanTanahSempadan.listLaporTanahSpdnB}" var="line">

                            <td>
                                ${line.laporanTanahSmpdn.hakmilik.idHakmilik}
                                <s:hidden  id="kandunganSpdnBHM${i}" name="listLaporTanahSpdnB[${i-1}].laporanTanahSmpdn.hakmilik.idHakmilik"  />
                            </td>
                            <td>
                                ${line.laporanTanahSmpdn.guna}
                                <s:hidden  id="kandunganSpdnBKEG${i}" name="listLaporTanahSpdnB[${i-1}].laporanTanahSmpdn.guna"  />
                            </td>
                            <td>
                                ${line.laporanTanahSmpdn.keadaanTanah}
                                <s:hidden  id="kandunganSpdnBKEA${i}" name="listLaporTanahSpdnB[${i-1}].laporanTanahSmpdn.keadaanTanah"  />
                            </td>
                            <td>
                                <s:format formatPattern="#,###,##0.0000" value="${line.laporanTanahSmpdn.jarak}"/>  ${line.laporanTanahSmpdn.jarakUom.nama}
                                <s:hidden   id="kandunganSpdnBJ${i}" name="disLaporanTanahSempadan.listLaporTanahSpdnU[${i-1}].laporanTanahSmpdn.jarak" />
                            </td>
                            <td>
                                ${line.laporanTanahSmpdn.catatan}
                                <s:hidden  id="kandunganSpdnBCAT${i}" name="listLaporTanahSpdnB[${i-1}].laporanTanahSmpdn.catatan"  />
                            </td>
                            <td>
                                <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'Y'}">Kerajaan</c:if>
                                <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'T'}">Milik</c:if>
                                <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'N'}">--</c:if>
                                <s:hidden name="disLaporanTanahSempadan.listLaporTanahSpdnB[${i-1}].laporanTanahSmpdn.milikKerajaan" id="kandunganSpdnBMK${i}" />

                            </td>
                        </tr>

                        <c:set var="i" value="${i+1}" />        
                    </c:forEach>

                </c:if>

                <%--END OF BARAT--%>

            </table>
        </div>
    </div>
    <table width="90%" border="0" >

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
                    <c:if test="${actionBean.pemohonHubungan.kaitan ne null}">4.3</c:if>
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
        </tr>
        <c:set var="numberPage" value="5"/>
        <c:if test="${!empty actionBean.senaraiLaporanKandunganUtil }">
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
                    <td colspan="2">( Ruj. Surat ${line.permohonanRujukanLuar.noRujukan} bertarikh <s:format value="${line.permohonanRujukanLuar.tarikhRujukan}" formatPattern="dd/MM/yyyy"/> )</td>
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
        </c:if>
        <c:if test="${!empty actionBean.senaraiYBAdun}">
            <tr>
                <td width="1%"><b>6.</b></td>
                <td colspan="3"><div align="left"><b><font style="text-transform: capitalize">Ulasan YB. ADUN KAWASAN ${actionBean.kodDun.nama}</font></b></div></td>
            </tr>
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
                    <td colspan="2">( Ruj. Surat ${line.noRujukan} bertarikh <s:format value="${line.tarikhRujukan}" formatPattern="dd/MM/yyyy"/></td>
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
            <c:set var="numberPage" value="${numberPage+1}" />
        </c:if>
        <tr>
            <td><b>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PPTR' }">6.</c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPTR' }">7.</c:if>                    
                </b></td>
            <td colspan="3"><b> ULASAN BADAN PENGAWAL</b></td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td width="1%">&nbsp;</td>
            <td colspan="2">
                ${actionBean.perihalBadanPengawal}
            </td>
        </tr>
        <tr>
            <td colspan="4">&nbsp;</td>
        </tr>
        <c:set var="numberPage" value="${numberPage+1}" />
        <tr>
            <td width="1%"><b>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PPTR' }">${numberPage}.</c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPTR' }">${numberPage+1}.</c:if>
                </b></td>
            <td colspan="3"><b><font style="text-transform: capitalize">PERAKUAN PENTADBIR TANAH DAERAH ${actionBean.permohonan.cawangan.daerah.nama}</font></b></td>
        </tr>
        <tr>
            <td width="1%">&nbsp;</td>
            <td>&nbsp;
            </td>
            <td colspan="2"> Perakuan :                                   
                <c:if test="${actionBean.editPTD}">
                    <s:radio name="syortolaklulus" value="SL" id="syortolaklulus" onclick="semakSyor(this.form,this.value);"/> Sokong   
                    <s:radio name="syortolaklulus" value="ST" id="syortolaklulus" onclick="semakSyor(this.form,this.value);"/> Tidak disokong
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
                <td>&nbsp;</td>
                <td valign="top"><c:out value="8.${i}"/></td>
                <td>
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
            <td>&nbsp;</td>
            <td>
                <c:if test="${actionBean.editPTD}">
                    <s:button value="Tambah Ulasan" class="btn" name="simpan1"  onclick="addRow('7',this.form)"></s:button> 
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
        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PLPS' or actionBean.permohonan.kodUrusan.kod eq 'LPSP'}">
            <c:if test="${actionBean.syortolaklulus eq 'SL'}">
                <tr>
                    <td align="right" id="tdDisplay">Lesen</td>
                    <td align="center">:</td>
                    <td align="left" colspan="2">Lesen Pendudukan Sementara</td>

                </tr>
                <tr>
                    <td align="right" id="tdDisplay">Kegunaan</td>
                    <td align="center">:</td>
                    <td align="left" colspan="2">
                        <c:if test="${actionBean.editPTD}">
                            <%--<s:text id="p" name="nama" size="20"/> &nbsp;--%>
                            ${actionBean.permohonanPermitItem.kodItemPermit.nama}
                            <%-- <s:select name="kodPermit.kod" value="${actionBean.permohonanPermitItem.kodItemPermit.kod}" onchange="openLain_lain(this.value)">
                                 <s:option value="">Sila Pilih</s:option>
                                 <s:options-collection collection="${actionBean.senaraiKodItemPermit}" label="nama" value="kod" />
                             </s:select> --%>
                            <c:if test="${actionBean.permohonanPermitItem.kodItemPermit.kod eq 'LN'}">
                                - ${actionBean.hakmilikPermohonan.catatan}
                            </c:if>                    

                            <%--  <s:textarea name="hakmilikPermohonan.catatan" id="catatan"  rows="5" cols="40"/> --%>


                        </c:if>
                        <c:if test="${!actionBean.editPTD}">
                            <%--${actionBean.senaraiKodItemPermit.nama} &nbsp;--%>
                            ${actionBean.permohonanPermitItem.kodItemPermit.nama} &nbsp;
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td align="right" id="tdDisplay">Keluasan</td>
                    <td align="center">:</td>
                    <td align="left" colspan="2">
                        <c:if test="${actionBean.editPTD}">
                            ${actionBean.hakmilikPermohonan.luasTerlibat} 
                            ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}
                            <%--<s:text id="luas" name="hakmilikPermohonan.luasTerlibat" size="20"/> &nbsp;
                            <s:select name="kodUnitLuas.kod" value="${actionBean.hakmilikPermohonan.kodUnitLuas.kod}" id="kULuas">
                                <s:option value="">Sila Pilih</s:option>
                                <s:option value="H">Hektar</s:option>
                                <s:option value="M">Meter Persegi</s:option>
                            </s:select> --%>
                        </c:if>
                        <c:if test="${!actionBean.editPTD}">
                            ${actionBean.hakmilikPermohonan.luasTerlibat} 
                            ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}&nbsp;
                        </c:if>        
                    </td>
                </tr>
                <tr>
                    <td align="right" id="tdDisplay">Sewa Tahunan</td>
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
                    <td align="right" id="tdDisplay">Syarat-syarat</td>
                    <td align="center">:</td>
                    <td align="left" colspan="2"></td>
                </tr>
                <tr>
                    <td align="right" id="tdDisplay">&nbsp;</td>
                    <td align="center">&nbsp;</td>
                    <td align="left" colspan="2">
                        i) Lesen Pendudukan Sementara ini hendaklah diperbaharui pada setiap awal tahun dan akan tamat tempoh pada 31 Disember tiap-tiap tahun. 
                    </td>
                </tr>
                <tr>
                    <td align="right" id="tdDisplay">&nbsp</td>
                    <td align="center">&nbsp;</td>
                    <td align="left" colspan="2">ii) Tanah ini tidak boleh digunakan bagi apa-apa maksud selain daripada maksud yang dinyatakan di dalamnya.</td>
                </tr>
                <tr>
                    <td align="right" id="tdDisplay">&nbsp</td>
                    <td align="center">&nbsp;</td>
                    <td align="left" colspan="2">iii) Lesen Pendudukan Sementara ini tidak boleh dipajak, dijual atau disewakan kepada sesiapa jua pun.</td>
                </tr>
                <tr>
                    <td align="right" id="tdDisplay">&nbsp</td>
                    <td align="center">&nbsp;</td>
                    <td align="left" colspan="2">iv) Bangunan kekal tidak dibenarkan didirikan di atas tanah ini.
                    </td>
                </tr>
                <tr>
                    <td align="right" id="tdDisplay">&nbsp</td>
                    <td align="center">&nbsp;</td>
                    <td align="left" colspan="2">v)Lesen Pendudukan Sementara ini akan dibatalkan dan tanah ini akan diambil balik tanpa apa-apa gantirugi apabila Kerajaan hendak menggunakan
                        tanah ini kelak atau jika berlaku apa-apa perlanggaran syarat terhadap tanah ini.
                    </td>
                </tr>
                <!--          <tr>
                              <td align="right" id="tdDisplay">&nbsp</td>
                            <td align="center">&nbsp;</td>
                            <td align="left" colspan="2">vi) Lesen ini *tidak boleh diserahak / boleh di bawah Kaedah 68KTN.
                            </td>
                          </tr>
                          <tr>
                              <td align="right" id="tdDisplay">&nbsp</td>
                            <td align="center">&nbsp;</td>
                            <td align="left" colspan="2">vii) Lesen ini hendaklah tamat sekiranya berlaku kematian orang, atau pembubaran badan, yang buat masa ini berhak mendapat faedah darinya.
                            </td>
                          </tr>
                          <tr>
                            <td align="right" id="tdDisplay">&nbsp</td>
                            <td align="center">&nbsp;</td>
                            <td align="left" colspan="2">viii) Tanah di bawah lesen ini tidak boleh digunakan-</td>
                          </tr>
                          <tr>
                              <td align="right" id="tdDisplay">&nbsp</td>
                              <td align="center">&nbsp;</td>
                              <td align="left">&nbsp;</td>
                              <td align="left">&nbsp;&nbsp;&nbsp;a) bagi apa-apa maksud selain dari maksud yang disebut diatas;</td>
                          </tr>
                          <tr>
                              <td align="right" id="tdDisplay">&nbsp</td>
                              <td align="center">&nbsp;</td>
                              <td align="left">&nbsp;</td>
                              <td align="left">&nbsp;&nbsp;&nbsp;b) untuk menanam apa-apa tanaman kekal;</td>
                          </tr>
                          <tr>
                              <td align="right" id="tdDisplay">&nbsp</td>
                              <td align="center">&nbsp;</td>
                              <td align="left">&nbsp;</td>
                              <td align="left">&nbsp;&nbsp;&nbsp;c) untuk mendirikan mana-mana bangunan kekal atau struktur kekal yang lain.</td>
                          </tr>
                          <tr>
                              <td align="right" id="tdDisplay">&nbsp</td>
                              <td align="center">&nbsp;</td>
                              <td align="left" colspan="2">xi) Lesen ini bolehlah dibatalkan-</td>
                          </tr>
                          <tr>
                              <td align="right" id="tdDisplay">&nbsp</td>
                              <td align="center">&nbsp;</td>
                              <td align="left">&nbsp;</td>
                              <td align="left">&nbsp;&nbsp;&nbsp;a) dengan serta-merta, dan tanpa apa-apa bayaran pampasan, jika berlaku perlanggaran mana-mana peruntukan yang kepadanya ia tertakluk;</td>
                          </tr>-->
                <!--          <tr>
                              <td align="right" id="tdDisplay">&nbsp</td>
                              <td align="center">&nbsp;</td>
                              <td align="left">&nbsp;</td>
                              <td align="left">&nbsp;&nbsp;&nbsp;b) dengan bayaran pampasan (yang akan dipersetujui atau ditentukan mengikut peruntukan-peruntukan seksyen 434 Kanun Tanah Negara) pada bila-bila masa sebelum tamatnya tempoh.</td>
                          </tr>-->
                <tr>
                    <td align="right" colspan="4">&nbsp</td>
                </tr>
            </c:if>
        </c:if>
        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPRU'}">
            <c:if test="${actionBean.syortolaklulus eq 'SL'}">
                <tr>
                    <td align="right" id="tdDisplay">Permit</td>
                    <td align="center">:</td>
                    <td align="left" colspan="2">Permit Menggunakan Ruang Udara</td>

                </tr>          
                <tr>
                    <td align="right" id="tdDisplay">Keluasan</td>
                    <td align="center">:</td>
                    <td align="left" colspan="2">
                        <c:if test="${actionBean.editPTD}">
                            ${actionBean.hakmilikPermohonan.luasTerlibat} 
                            ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}
                            <%--<s:text id="luas" name="hakmilikPermohonan.luasTerlibat" size="20"/> &nbsp;
                            <s:select name="kodUnitLuas.kod" value="${actionBean.hakmilikPermohonan.kodUnitLuas.kod}" id="kULuas">
                                <s:option value="">Sila Pilih</s:option>
                                <s:option value="H">Hektar</s:option>
                                <s:option value="M">Meter Persegi</s:option>
                            </s:select> --%>
                        </c:if>
                        <c:if test="${!actionBean.editPTD}">
                            ${actionBean.hakmilikPermohonan.luasTerlibat} 
                            ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}&nbsp;
                        </c:if>        
                    </td>
                </tr>
                <tr>
                    <td align="right" id="tdDisplay">Bayaran</td>
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
                    <td align="right" id="tdDisplay">Isipadu Diluluskan</td>
                    <td align="center">:</td>
                    <td align="left" colspan="2">
                        ${actionBean.hakmilikPermohonan.luasDiluluskan}&nbsp; ${actionBean.hakmilikPermohonan.luasLulusUom.nama}&nbsp;
                    </td>
                </tr>
                <tr>
                    <td align="right" id="tdDisplay">Tempoh</td>
                    <td align="center">:</td>
                    <td align="left" colspan="2">
                        ${actionBean.hakmilikPermohonan.tempohPajakan}&nbsp; Tahun. 
                    </td>
                </tr>
                <tr>
                    <td align="right" id="tdDisplay">Syarat-syarat</td>
                    <td align="center">:</td>
                    <td align="left" colspan="2"></td>
                </tr>
                <!--          <tr>
                              <td align="right" id="tdDisplay">&nbsp;</td>
                            <td align="center">&nbsp;</td>
                            <td align="left" colspan="2">
                                i) Lesen Pendudukan Sementara ini hendaklah diperbaharui pada setiap awal tahun dan akan tamat tempoh pada 31 Disember tiap-tiap tahun. 
                            </td>
                          </tr>-->
                <tr>
                    <td align="right" id="tdDisplay">&nbsp</td>
                    <td align="center">&nbsp;</td>
                    <td align="left" colspan="2">i) Ruang udara atas ${actionBean.hakmilikPermohonan.kodMilik.nama} hendaklah tidak digunakan bagi apa-apa maksud selain dari mendirikan, menyenggara dan menduduki *struktur/struktur-struktur yang diperihalkan.</td>
                </tr>
                <tr>
                    <td align="right" id="tdDisplay">&nbsp</td>
                    <td align="center">&nbsp;</td>
                    <td align="left" colspan="2">ii) Permit ini boleh dibatalkan di bawah seksyen 75G.</td>
                </tr>
                <tr>
                    <td align="right" id="tdDisplay">&nbsp</td>
                    <td align="center">&nbsp;</td>
                    <td align="left" colspan="2">iii) Permit ini tidak boleh diserahak, kecuali dengan kelulusan Pihak Berkuasa Negeri terlebih dahulu.
                    </td>
                </tr>

                <!--          <tr>
                              <td align="right" id="tdDisplay">&nbsp</td>
                            <td align="center">&nbsp;</td>
                            <td align="left" colspan="2">vi) Lesen ini *tidak boleh diserahak / boleh di bawah Kaedah 68KTN.
                            </td>
                          </tr>
                          <tr>
                              <td align="right" id="tdDisplay">&nbsp</td>
                            <td align="center">&nbsp;</td>
                            <td align="left" colspan="2">vii) Lesen ini hendaklah tamat sekiranya berlaku kematian orang, atau pembubaran badan, yang buat masa ini berhak mendapat faedah darinya.
                            </td>
                          </tr>
                          <tr>
                            <td align="right" id="tdDisplay">&nbsp</td>
                            <td align="center">&nbsp;</td>
                            <td align="left" colspan="2">viii) Tanah di bawah lesen ini tidak boleh digunakan-</td>
                          </tr>
                          <tr>
                              <td align="right" id="tdDisplay">&nbsp</td>
                              <td align="center">&nbsp;</td>
                              <td align="left">&nbsp;</td>
                              <td align="left">&nbsp;&nbsp;&nbsp;a) bagi apa-apa maksud selain dari maksud yang disebut diatas;</td>
                          </tr>
                          <tr>
                              <td align="right" id="tdDisplay">&nbsp</td>
                              <td align="center">&nbsp;</td>
                              <td align="left">&nbsp;</td>
                              <td align="left">&nbsp;&nbsp;&nbsp;b) untuk menanam apa-apa tanaman kekal;</td>
                          </tr>
                          <tr>
                              <td align="right" id="tdDisplay">&nbsp</td>
                              <td align="center">&nbsp;</td>
                              <td align="left">&nbsp;</td>
                              <td align="left">&nbsp;&nbsp;&nbsp;c) untuk mendirikan mana-mana bangunan kekal atau struktur kekal yang lain.</td>
                          </tr>
                          <tr>
                              <td align="right" id="tdDisplay">&nbsp</td>
                              <td align="center">&nbsp;</td>
                              <td align="left" colspan="2">xi) Lesen ini bolehlah dibatalkan-</td>
                          </tr>
                          <tr>
                              <td align="right" id="tdDisplay">&nbsp</td>
                              <td align="center">&nbsp;</td>
                              <td align="left">&nbsp;</td>
                              <td align="left">&nbsp;&nbsp;&nbsp;a) dengan serta-merta, dan tanpa apa-apa bayaran pampasan, jika berlaku perlanggaran mana-mana peruntukan yang kepadanya ia tertakluk;</td>
                          </tr>-->
                <!--          <tr>
                              <td align="right" id="tdDisplay">&nbsp</td>
                              <td align="center">&nbsp;</td>
                              <td align="left">&nbsp;</td>
                              <td align="left">&nbsp;&nbsp;&nbsp;b) dengan bayaran pampasan (yang akan dipersetujui atau ditentukan mengikut peruntukan-peruntukan seksyen 434 Kanun Tanah Negara) pada bila-bila masa sebelum tamatnya tempoh.</td>
                          </tr>-->
                <tr>
                    <td align="right" colspan="4">&nbsp</td>
                </tr>
            </c:if>
        </c:if>
        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPTR'}">
            <c:if test="${actionBean.syortolaklulus eq 'SL'}">
                <tr>
                    <td align="right" id="tdDisplay">Permit</td>
                    <td align="center">:</td>
                    <td align="left" colspan="2">Permit Tanah Rizab</td>

                </tr>          
                <tr>
                    <td align="right" id="tdDisplay">Keluasan Dipohon</td>
                    <td align="center">:</td>
                    <td align="left" colspan="2">
                        <c:if test="${actionBean.editPTD}">
                            ${actionBean.hakmilikPermohonan.luasTerlibat} 
                            ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}
                            <%--<s:text id="luas" name="hakmilikPermohonan.luasTerlibat" size="20"/> &nbsp;
                            <s:select name="kodUnitLuas.kod" value="${actionBean.hakmilikPermohonan.kodUnitLuas.kod}" id="kULuas">
                                <s:option value="">Sila Pilih</s:option>
                                <s:option value="H">Hektar</s:option>
                                <s:option value="M">Meter Persegi</s:option>
                            </s:select> --%>
                        </c:if>
                        <c:if test="${!actionBean.editPTD}">
                            ${actionBean.hakmilikPermohonan.luasTerlibat} 
                            ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}&nbsp;
                        </c:if>        
                    </td>
                </tr>
                <tr>
                    <td align="right" id="tdDisplay">Bayaran</td>
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
                    <td align="right" id="tdDisplay">Tempoh</td>
                    <td align="center">:</td>
                    <td align="left" colspan="2">
                        ${actionBean.hakmilikPermohonan.tempohPajakan}&nbsp; Tahun. 
                    </td>
                </tr>
                <tr>
                    <td align="right" id="tdDisplay">Syarat-syarat</td>
                    <td align="center">:</td>
                    <td align="left" colspan="2"></td>
                </tr>
                <!--          <tr>
                              <td align="right" id="tdDisplay">&nbsp;</td>
                            <td align="center">&nbsp;</td>
                            <td align="left" colspan="2">
                                i) Lesen Pendudukan Sementara ini hendaklah diperbaharui pada setiap awal tahun dan akan tamat tempoh pada 31 Disember tiap-tiap tahun. 
                            </td>
                          </tr>-->
                <tr>
                    <td align="right" id="tdDisplay">&nbsp</td>
                    <td align="center">&nbsp;</td>
                    <td align="left" colspan="2">Jika permohonan diluluskan PT hendaklah menyampaikan terma kelulusan seperti yang berikut:</td>
                </tr>
                <tr>
                    <td align="right" id="tdDisplay">&nbsp</td>
                    <td align="center">&nbsp;</td>
                    <td align="left" colspan="2">a) Keluasan yang diluluskan : ${actionBean.hakmilikPermohonan.luasDiluluskan}&nbsp; ${actionBean.hakmilikPermohonan.luasLulusUom.nama}&nbsp;</td>
                </tr>
                <tr>
                    <td align="right" id="tdDisplay">&nbsp</td>
                    <td align="center">&nbsp;</td>
                    <td align="left" colspan="2">b) Bayaran tahunan : RM ${actionBean.permohonanTuntutanKos.amaunTuntutan}&nbsp;.
                    </td>
                </tr>
                <tr>
                    <td align="right" id="tdDisplay">&nbsp</td>
                    <td align="center">&nbsp;</td>
                    <td align="left" colspan="2">c) Tempoh Pajakan : ${actionBean.hakmilikPermohonan.tempohPajakan}&nbsp;Tahun.
                    </td>
                </tr>
                <tr>
                    <td align="right" id="tdDisplay">&nbsp</td>
                    <td align="center">&nbsp;</td>
                    <td align="left" colspan="2">d) Kegunaan Tanah : ${actionBean.permohonan.sebab}&nbsp;.
                    </td>
                </tr>
                <tr>
                    <td align="right" id="tdDisplay">&nbsp</td>
                    <td align="center">&nbsp;</td>
                    <td align="left" colspan="2">e) Syarat-syarat pajakan :
                    </td>
                </tr>
                <tr>
                    <td align="right" id="tdDisplay">&nbsp</td>
                    <td align="center">&nbsp;</td>
                    <td align="left" colspan="2">i) Tanah ini tidak boleh digunakan bagi apa-apa maksud selain daripada maksud yang dinyatakan di dalamnya.</td>
                </tr>
                <tr>
                    <td align="right" id="tdDisplay">&nbsp</td>
                    <td align="center">&nbsp;</td>
                    <td align="left" colspan="2">ii) Permit ini tidak boleh dipajak, dijual atau disewakan kepada sesiapa jua pun.</td>
                </tr>
                <tr>
                    <td align="right" id="tdDisplay">&nbsp</td>
                    <td align="center">&nbsp;</td>
                    <td align="left" colspan="2">iii) Bangunan kekal tidak dibenarkan didirikan di atas tanah ini.
                    </td>
                </tr>
                <tr>
                    <td align="right" id="tdDisplay">&nbsp</td>
                    <td align="center">&nbsp;</td>
                    <td align="left" colspan="2">iv)Permit ini akan dibatalkan dan tanah ini akan diambil balik tanpa apa-apa gantirugi apabila Kerajaan hendak menggunakan
                        tanah ini kelak atau jika berlaku apa-apa perlanggaran syarat terhadap tanah ini.
                    </td>
                </tr>
                <!--          <tr>
                              <td align="right" id="tdDisplay">&nbsp</td>
                            <td align="center">&nbsp;</td>
                            <td align="left" colspan="2">vi) Lesen ini *tidak boleh diserahak / boleh di bawah Kaedah 68KTN.
                            </td>
                          </tr>
                          <tr>
                              <td align="right" id="tdDisplay">&nbsp</td>
                            <td align="center">&nbsp;</td>
                            <td align="left" colspan="2">vii) Lesen ini hendaklah tamat sekiranya berlaku kematian orang, atau pembubaran badan, yang buat masa ini berhak mendapat faedah darinya.
                            </td>
                          </tr>
                          <tr>
                            <td align="right" id="tdDisplay">&nbsp</td>
                            <td align="center">&nbsp;</td>
                            <td align="left" colspan="2">viii) Tanah di bawah lesen ini tidak boleh digunakan-</td>
                          </tr>
                          <tr>
                              <td align="right" id="tdDisplay">&nbsp</td>
                              <td align="center">&nbsp;</td>
                              <td align="left">&nbsp;</td>
                              <td align="left">&nbsp;&nbsp;&nbsp;a) bagi apa-apa maksud selain dari maksud yang disebut diatas;</td>
                          </tr>
                          <tr>
                              <td align="right" id="tdDisplay">&nbsp</td>
                              <td align="center">&nbsp;</td>
                              <td align="left">&nbsp;</td>
                              <td align="left">&nbsp;&nbsp;&nbsp;b) untuk menanam apa-apa tanaman kekal;</td>
                          </tr>
                          <tr>
                              <td align="right" id="tdDisplay">&nbsp</td>
                              <td align="center">&nbsp;</td>
                              <td align="left">&nbsp;</td>
                              <td align="left">&nbsp;&nbsp;&nbsp;c) untuk mendirikan mana-mana bangunan kekal atau struktur kekal yang lain.</td>
                          </tr>
                          <tr>
                              <td align="right" id="tdDisplay">&nbsp</td>
                              <td align="center">&nbsp;</td>
                              <td align="left" colspan="2">xi) Lesen ini bolehlah dibatalkan-</td>
                          </tr>
                          <tr>
                              <td align="right" id="tdDisplay">&nbsp</td>
                              <td align="center">&nbsp;</td>
                              <td align="left">&nbsp;</td>
                              <td align="left">&nbsp;&nbsp;&nbsp;a) dengan serta-merta, dan tanpa apa-apa bayaran pampasan, jika berlaku perlanggaran mana-mana peruntukan yang kepadanya ia tertakluk;</td>
                          </tr>-->
                <!--          <tr>
                              <td align="right" id="tdDisplay">&nbsp</td>
                              <td align="center">&nbsp;</td>
                              <td align="left">&nbsp;</td>
                              <td align="left">&nbsp;&nbsp;&nbsp;b) dengan bayaran pampasan (yang akan dipersetujui atau ditentukan mengikut peruntukan-peruntukan seksyen 434 Kanun Tanah Negara) pada bila-bila masa sebelum tamatnya tempoh.</td>
                          </tr>-->
                <tr>
                    <td align="right" colspan="4">&nbsp</td>
                </tr>
            </c:if>
        </c:if>  
    </table>
    <table width="90%" border="0" >
        <c:if test="${actionBean.openPTG}">
            <c:set var="numberPage" value="${numberPage+1}" />   
            <c:if test="${actionBean.editPTG}">
                <tr>
                    <td width="1%"><b>${numberPage}.</b></td>
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
                            <s:button value="Tambah Ulasan" class="btn" name="simpan1"  onclick="addRow('8',this.form)"></s:button> 
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
                    <td width="1%"><b>${numberPage}.</b></td>
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