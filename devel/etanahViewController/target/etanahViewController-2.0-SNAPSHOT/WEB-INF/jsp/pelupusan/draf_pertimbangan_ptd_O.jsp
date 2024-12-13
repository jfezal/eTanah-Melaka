<%-- 
    Document   : Draf Pertimbangan PTD
    Created on : July 12 2012
    Author     : Orogenic Group
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

    function menyimpan(index,i,f)
    {
        //alert('masuk menyimpan ' + index);
       // alert('i ' + i);
        var kand;
        var idKand = 0;

        if(index == 2){
            kand = document.getElementById("kandungan2"+i).value;
        }
        if(index == 3){
            
            kand = document.getElementById("kandungan3"+i).value;
            idKand = document.getElementById("kandungan3hidden"+i).value;
         //   alert(kand);
        }
        if(index==4){
            kand = document.getElementById("kandungan4"+i).value;
            idKand = document.getElementById("kandungan4hidden"+i).value;
           // alert(kand);
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
        //alert(kand);
        var q = $(f).formSerialize();
        //alert(q);
        $.post('${pageContext.request.contextPath}/pelupusan/draf_jkbb_O?simpanKandungan&index='+index+'&kandungan='+kand+"&idKandungan="+idKand,q,
        function(data){
            $('#page_div').html(data);
        }, 'html');
  
       
       
    }
    function addRow(index,f)
    {
//        alert('masuk addRow ' + index);
        var q = $(f).formSerialize();
        
//        alert('q ' + q);
        $.post('${pageContext.request.contextPath}/pelupusan/draf_jkbb_O?tambahRow&index='+index,q,
        function(data){
            $('#page_div').html(data);
        }, 'html');
    }
    function deleteRow(idKandungan,f)
    {
        if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
            var q = $(f).formSerialize();
            $.post('${pageContext.request.contextPath}/pelupusan/draf_jkbb_O?deleteRow&idKandungan='+idKandungan,q,
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
        $.post('${pageContext.request.contextPath}/pelupusan/draf_jkbb_O?showsyortolaklulus&syortolaklulus='+v,q,
        function(data){
            $('#page_div').html(data);
        }, 'html');
    }
    ///aded
    function refreshPage(v){
        var syortolaklulusdata = v;
        var url = '${pageContext.request.contextPath}/pelupusan/draf_jkbb_O?showsyortolaklulus&syortolaklulus='+syortolaklulusdata;
        $.post(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

</script>
        
<s:form beanclass="etanah.view.stripes.pelupusan.DrafJKBBActionBean_O">
    <s:errors/>
    <s:messages/>
    <s:errors/>
    <s:hidden name="kertasK.idKertas"/>
    
    <div class="subtitle">
        <fieldset class="aras1">

            
            
            <div class="content" align="center">
                    <legend>(KERTAS PERTIMBANGAN PENTADBIR TANAH SEREMBAN)          </legend>


                <p>&nbsp;</p>
                <table width="50%" border="0" align="center" class="tablecloth" >
                        <tr>
                        <td colspan="2">PERMOHONAN DARIPADA <em>${actionBean.namaSyarikat}</em> UNTUK MERIZABKAN TANAH 
                            KERAJAAN DI Lot <em>${actionBean.lot}</em>, <em>${actionBean.mukim}</em>, DAERAH SEREMBAN SELUAS

                            LEBIH KURANG <em><s:format value="${actionBean.luas}" formatPattern="#,###,##0.0000"/> </em>    <em> <c:if test="${actionBean.hakmilikPermohonan.kodUnitLuas.kod eq 'M'}"> Meter </c:if> </em>
                            <c:if test="${actionBean.hakmilikPermohonan.kodUnitLuas.kod eq 'H'}"> Hektar </c:if> </em>
                            PERSEGI UNTUK TUJUAN  <em>${actionBean.tujuan}</em> DI
                            BAWAH SEKSYEN 62(1) KANUN TANAH NEGARA 1965
                        </td>


                    </tr>


                </table>

                <p>&nbsp;</p>


                <legend>1.TUJUAN</legend>   
                <table width="50%" border="0" align="center" class="tablecloth" >
                    <tr>
                        <td>1.1</td>
                        <td width="93%">Kertas ini bertujuan untuk mendapatkan pertimbangan Pentadbir Tanah Seremban mengenai permohonan daripada <em>${actionBean.namaSyarikat}</em>  seperti berikut:</td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td>ii) Merizab Tanah Kerajaan.</td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td>iii) Seluas <em>${actionBean.luas}</em> meter persegi</td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td>iv) Di <em>${actionBean.hakmilikPermohonan.lot.nama}</em>: <em>${actionBean.lot}</em></td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td>v) Untuk Kegunaan: <em>${actionBean.tujuan}</em></td>
                    </tr>
                </table>

                    
               
                    
                    
                    

                <p>&nbsp;</p>
                <legend>2. LATAR BELAKANG </legend>  
                <table width="50%" border="0" align="center" class="tablecloth" >
                    <tr>
                        <td width="20" valign="top">2.1</td>
                        <td width="638">  Permohonan ini telah diterima pada <em><fmt:formatDate value="${actionBean.permohonan.infoAudit.tarikhMasuk}" pattern="dd/MM/yyy" /></em> </td>


                    </tr>

                    <tr>
                        <td valign="top"></td>
                    </tr>
                    <tr>
                        <td valign="top"></td>
                    </tr>
                    <tr>
                        <td width="20" valign="top">2.2</td>
                        <td width="638">  Tanah yang dimaksudkan itu seperti bertanda merah  </td>
                    </tr>
                    
                    <tr>
                        <td valign="top">&nbsp;</td>
                        <td>i) Mukim: <em>${actionBean.mukim}</em></td>
                    </tr>
                    <tr>
                        <td valign="top">&nbsp;</td>
                        <td>ii)Tempat : <em>${actionBean.hakmilikPermohonan.lot.nama}</em>  <em>${actionBean.lot}</em></td>
                    </tr>
                    <tr>
                        <td valign="top">&nbsp;</td>
                        <td>iii) Jenis Tanah :<em>${actionBean.jenisTanah}</em></td>
                    </tr>
                     <tr>
                        <td valign="top">&nbsp;</td>
                        <td>iv) Dalam kawasan :-</td>
                    </tr>
                    
                </table>
                    
                                        <div id="dalamkawasan" align="center">
                <display:table  name="${actionBean.senaraiLaporanKawasan}" id="line9" class="tablecloth">
                    <s:hidden name="" class="${line9_rowNum -1}" value="${line9.idMohonlaporKws}"/>
                    <display:column title="Jenis Rizab"  property="kodRizab.nama"/>
                    <display:column title="Catatan">
                        <c:if test="${line9.catatan ne null}">
                            ${line9.catatan}
                        </c:if>
                        <c:if test="${line9.catatan eq null}">
                            -
                        </c:if>
                    </display:column>
                    <display:column title="No Warta" property="noWarta"/>
                    <display:column title="Tarikh Warta" property="tarikhWarta" format="{0,date,dd-MM-yyyy}"/>
                    <display:column title="No Pelan Warta" property="noPelanWarta" />                    
                </display:table>
                <br/>
            </div>
                    
                    <table width="50%" border="0" align="center" class="tablecloth" >
                    <tr>
                        <td valign="top"></td>
                    </tr>
                    <tr>
                        <td valign="top"></td>
                    </tr>

                    <tr>
                        <td width="20" valign="top">2.3</td>
                        <td width="638">  Tanah ini terletak lebih kurang  <em>${actionBean.hakmilikPermohonan.jarak}</em>  
                            <c:if test="${actionBean.hakmilikPermohonan.unitJarak.kod eq 'KM'}">Kilometer</c:if><c:if test="${actionBean.hakmilikPermohonan.unitJarak.kod eq 'JM'}">Meter</c:if>
                            
                            dari <em>${actionBean.hakmilikPermohonan.jarakDari}</em>  . Keadaaan tanah adalah <em>${actionBean.laporanTanah.kodKeadaanTanah.nama}</em></td>
                    </tr>

                    <tr>
                        <td valign="top"></td>
                    </tr>
                    <tr>
                        <td valign="top"></td>
                    </tr>
                    <tr>

                        <td width="20" valign="top">2.4</td>
                        <td width="633">Keadaan Sekeliling tanah adalah seperti berikut :-</td>
                    </tr>

                    <%--UTARA--%>
                    <c:if test="${!empty actionBean.disLaporanTanahSempadan.listLaporTanahSpdnU}">
                        <c:set var="i" value="1" />
                        <tr>
                            <td rowspan="${actionBean.disLaporanTanahSempadan.uSize}">
                                Utara:
                            </td>
                            <c:forEach items="${actionBean.disLaporanTanahSempadan.listLaporTanahSpdnU}" var="line">

                                <td>
                                    Tanah
                                    
                                    <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'Y'}">Kerajaan</c:if>
                                    <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'T'}">Milik</c:if>
                                    <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'N'}">--</c:if>
                                
                                    <em>${line.laporanTanahSmpdn.kodLot.nama}</em>  
                                    
                                    ${line.laporanTanahSmpdn.noLot}
                                    <s:hidden  id="kandunganSpdnUNLot${i}" name="disLaporanTanahSempadan.listLaporTanahSpdnU[${i-1}].laporanTanahSmpdn.noLot" />

                                    -
                                    ${line.laporanTanahSmpdn.guna}
                                    <s:hidden   id="kandunganSpdnUKEG${i}" name="listLaporTanahSpdnU[${i-1}].laporanTanahSmpdn.guna" />

                                    
                                    
                                    
                                    <s:hidden  name="disLaporanTanahSempadan.listLaporTanahSpdnU[${i-1}].laporanTanahSmpdn.milikKerajaan" id="kandunganSpdnUMK${i}"/>

                                </td>

                            </tr>

                            <c:set var="i" value="${i+1}" />        
                        </c:forEach>
                    </c:if>
                    <c:if test="${empty actionBean.disLaporanTanahSempadan.listLaporTanahSpdnU}">

                        <tr>
                            <td width="20" valign="top">Utara</td>
                            <td width="638">  tiada</td>
                        </tr>    

                    </c:if>

                    <%--END OF UTARA--%>                             
                    <%--SELATAN--%>
                    <c:if test="${!empty actionBean.disLaporanTanahSempadan.listLaporTanahSpdnS}">
                        <c:set var="i" value="1" />
                        <tr>
                            <td rowspan="${actionBean.disLaporanTanahSempadan.sSize}">
                                Selatan :
                            </td>
                            <c:forEach items="${actionBean.disLaporanTanahSempadan.listLaporTanahSpdnS}" var="line">

                                <td>
                                   Tanah
                                     <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'Y'}">Kerajaan</c:if>
                                    <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'T'}">Milik</c:if>
                                    <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'N'}">--</c:if>
                                    
                                    <em>${line.laporanTanahSmpdn.kodLot.nama}</em>  
                                    ${line.laporanTanahSmpdn.noLot}
                                    <s:hidden  id="kandunganSpdnSNLot${i}" name="disLaporanTanahSempadan.listLaporTanahSpdnS[${i-1}].laporanTanahSmpdn.noLot" />
                                    -
                                    ${line.laporanTanahSmpdn.guna}
                                    <s:hidden  id="kandunganSpdnSKEG${i}" name="listLaporTanahSpdnS[${i-1}].laporanTanahSmpdn.guna" />
                                    
                                    <s:hidden name="disLaporanTanahSempadan.listLaporTanahSpdnS[${i-1}].laporanTanahSmpdn.milikKerajaan" id="kandunganSpdnSMK${i}"/>

                                </td>

                            </tr>

                            <c:set var="i" value="${i+1}" />        
                        </c:forEach>
                    </c:if>

                    <c:if test="${empty actionBean.disLaporanTanahSempadan.listLaporTanahSpdnS}">

                        <tr>
                            <td width="20" valign="top">Selatan</td>
                            <td width="638">  tiada</td>
                        </tr>    

                    </c:if>

                    <%--END OF SELATAN--%>
                    <%--TIMUR--%>
                    <c:if test="${!empty actionBean.disLaporanTanahSempadan.listLaporTanahSpdnT}">
                        <c:set var="i" value="1" />
                        <tr>
                            <td rowspan="${actionBean.disLaporanTanahSempadan.tSize}">
                                Timur:
                            </td>
                            <c:forEach items="${actionBean.disLaporanTanahSempadan.listLaporTanahSpdnT}" var="line">


                                <td>
                                    Tanah
                                    <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'Y'}">Kerajaan</c:if>
                                    <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'T'}">Milik</c:if>
                                    <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'N'}">--</c:if>
                                    
                                    <em>${line.laporanTanahSmpdn.kodLot.nama}</em>  
                                    ${line.laporanTanahSmpdn.noLot}
                                    <s:hidden  id="kandunganSpdnTNLot${i}" name="disLaporanTanahSempadan.listLaporTanahSpdnT[${i-1}].laporanTanahSmpdn.noLot" />
                                    -
                                    ${line.laporanTanahSmpdn.guna}
                                    <s:hidden  id="kandunganSpdnTKEG${i}" name="listLaporTanahSpdnT[${i-1}].laporanTanahSmpdn.guna"  />

                                    
                                    <s:hidden name="disLaporanTanahSempadan.listLaporTanahSpdnT[${i-1}].laporanTanahSmpdn.milikKerajaan" id="kandunganSpdnTMK${i}" />

                                </td>

                            </tr>

                            <c:set var="i" value="${i+1}" />        
                        </c:forEach>

                    </c:if>

                    <c:if test="${empty actionBean.disLaporanTanahSempadan.listLaporTanahSpdnT}">

                        <tr>
                            <td width="20" valign="top">Timur</td>
                            <td width="638">  tiada</td>
                        </tr>    

                    </c:if>
                    <%--END OF TIMUR--%>
                    <%--BARAT--%>
                    <c:if test="${!empty actionBean.disLaporanTanahSempadan.listLaporTanahSpdnB}">
                        <c:set var="i" value="1" />
                        <tr>
                            <td rowspan="${actionBean.disLaporanTanahSempadan.bSize}">
                                Barat:
                            </td>
                            <c:forEach items="${actionBean.disLaporanTanahSempadan.listLaporTanahSpdnB}" var="line">


                                <td>
                                    Tanah 
                                     <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'Y'}">Kerajaan</c:if>
                                    <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'T'}">Milik</c:if>
                                    <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'N'}">--</c:if>
                                    
                                    <em>${line.laporanTanahSmpdn.kodLot.nama}</em>  
                                    
                                    ${line.laporanTanahSmpdn.noLot}
                                    <s:hidden  id="kandunganSpdnBNLot${i}" name="disLaporanTanahSempadan.listLaporTanahSpdnB[${i-1}].laporanTanahSmpdn.noLot" />
                                   -
                                    ${line.laporanTanahSmpdn.guna}
                                   

                                    <s:hidden name="disLaporanTanahSempadan.listLaporTanahSpdnB[${i-1}].laporanTanahSmpdn.milikKerajaan" id="kandunganSpdnBMK${i}" />

                                </td>

                            </tr>

                            <c:set var="i" value="${i+1}" />        
                        </c:forEach>

                    </c:if>


                    <c:if test="${empty actionBean.disLaporanTanahSempadan.listLaporTanahSpdnB}">

                        <tr>
                            <td width="20" valign="top">Barat</td>
                            <td width="638">  tiada</td>
                        </tr>    

                    </c:if>
                    <%--END OF BARAT--%>

                    <tr>
                        <td valign="top"></td>
                    </tr>
                    <tr>
                        <td valign="top"></td>
                    </tr>

                    <tr>

                        <td width="20" valign="top">2.5</td>
                        <td width="406"> Perancangan Kerajaan :${actionBean.laporanTanah.rancanganKerajaan}</td>
                    </tr>

                    <tr>
                        <td valign="top"></td>
                    </tr>
                    <tr>
                        <td valign="top"></td>
                    </tr>
                </table>




                <p>&nbsp;</p>
                <p>&nbsp;</p>



                <!-- beginning ulasan - ahmadshyazli-->

                <table width="50%" border="0" >



                    <tr>
                        <td width="1%"><b>&nbsp;</b></td>
                        <td colspan="3"><div align="left"><b><font style="text-transform: capitalize">3. Huraian  Pembantu/Timbalan Pentadbir Tanah <em>${actionBean.hakmilikPermohonan.bandarPekanMukimBaru.daerah.nama}</em></font></b></div></td>
                    </tr>
                    <c:set var="i" value="1" />
                    <c:forEach items="${actionBean.senaraiLaporanKandunganptg1}" var="line">
                        <tr>
                            <td>&nbsp;</td>
                            <td valign="top"><c:out value="3.${i}"/></td>
                            <td colspan="2">
                                <s:hidden id="kandungan3hidden${i}" name="kandungan3hidden${i}" value="${line.idKandungan}"></s:hidden>
                                <s:textarea  id="kandungan3${i}" name="senaraiLaporanKandunganptg1[${i-1}].kandungan" cols="150"  rows="5" class="normal_text"/>
                                <s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan},this.form)"></s:button>
                            </td>
                        </tr>
                        <c:set var="i" value="${i+1}" />        
                    </c:forEach>
                    <s:hidden name="rowCount3" value="${fn:length(actionBean.senaraiLaporanKandunganptg1)}" id="rowCount3"/>
                    <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td colspan="2">

                            <s:button value="Tambah Ulasan" class="btn" name="simpan1"  onclick="addRow('3',this.form)"></s:button> 
                            <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('3',${i-1},this.form)"></s:button>


                        </td>                                
                    </tr>
                    <tr>
                        <td colspan="4">&nbsp;</td>
                    </tr>



                </table>

                <!-- ending ulasan - ahmadshyazli-->




                <p>&nbsp;</p>
                <p>&nbsp;</p>



                <!-- beginning ulasan - ahmadshyazli-->

                <table width="50%" border="0" >



                    <tr>
                        <td width="1%"><b>&nbsp;</b></td>
                        <td colspan="3"><div align="left"><b><font style="text-transform: capitalize">4. Syor Pembantu/Timbalan Pentadbir Tanah <em>${actionBean.hakmilikPermohonan.bandarPekanMukimBaru.daerah.nama}</em></font></b></div></td>
                    </tr>
                    <c:set var="i" value="1" />
                    <c:forEach items="${actionBean.senaraiLaporanKandunganptg2}" var="line">
                        <tr>
                            <td>&nbsp;</td>
                            <td valign="top"><c:out value="4.${i}"/></td>
                            <td colspan="2">
                                <s:hidden id="kandungan4hidden${i}" name="kandungan4hidden${i}" value="${line.idKandungan}"></s:hidden>
                                <s:textarea  id="kandungan4${i}"name="senaraiLaporanKandunganptg2[${i-1}].kandungan" cols="150"  rows="5" class="normal_text"/>
                                <s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan},this.form)"></s:button>
                            </td>
                        </tr>
                        <c:set var="i" value="${i+1}" />        
                    </c:forEach>
                    <s:hidden name="rowCount4" value="${fn:length(actionBean.senaraiLaporanKandunganptg2)}" id="rowCount4"/>
                    <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td colspan="2">

                            <s:button value="Tambah Ulasan" class="btn" name="simpan1"  onclick="addRow('4',this.form)"></s:button> 
                            <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('4',${i-1},this.form)"></s:button>


                        </td>                                
                    </tr>
                    <tr>
                        <td colspan="4">&nbsp;</td>
                    </tr>



                </table>




            </div>
        </fieldset>
        <br/>
        <label>&nbsp</label>
    </div>
</s:form>

