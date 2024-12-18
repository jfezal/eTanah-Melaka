<%-- 
    Document   : draft_mmk_penamatan_mlk
    Created on : Nov 16, 2011, 2:34:57 PM
    Author     : sitinorajar
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<style type="text/css">
    #tdLabel {
        color:#003194;
        display:block;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        margin-left:30px;
        margin-right:0.5em;
        text-align:right;
        width:32em;
    }

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
        width:20em;
    }
</style>
<script type="text/javascript">

    $(document).ready(function() {
        var v = '${actionBean.fasaPermohonan.keputusan.kod}';
        if(v =="T"){
            $('#suku').hide();
            $('#ta').show();
        }else if(v == "L"){
            $('#suku').show();
            $('#ta').hide();
        }else{
            $('#suku').hide();
            $('#ta').hide();
        }
        
        var len = $(".daerah").length;

        for (var i=0; i<=len; i++){
            $('.hakmilik'+i).click( function() {
                window.open("${pageContext.request.contextPath}/pelupusan/draf_mmk_mlk?popupHakmilikDetail&idHakmilik="+$(this).text(), "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
            });
        }

    });
    
    function changeHideSuku(value){
        if(value == "T"){
            $('#suku').hide();
            $('#ta').show();
        }else if(value == "L"){
            $('#suku').show();
            $('#ta').hide();
        }else{
            $('#suku').hide();
            $('#ta').hide();
        }
    }

  
    function menyimpan(index,i,f){
        
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
        $.post('${pageContext.request.contextPath}/pelupusan/draf_mmk_mlk?simpanKandungan&index='+index+'&kandungan='+kand,q,
        function(data){
            $('#page_div').html(data);
        }, 'html');
  
       
       
    }
    
    function addRow(index,f)
    {
        var q = $(f).formSerialize();
        $.post('${pageContext.request.contextPath}/pelupusan/draf_mmk_mlk?tambahRow&index='+index,q,
        function(data){
            $('#page_div').html(data);
        }, 'html');
    }
    function deleteRow(idKandungan,f)
    {
        if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
            var q = $(f).formSerialize();
            $.post('${pageContext.request.contextPath}/pelupusan/draf_mmk_mlk?deleteRow&idKandungan='+idKandungan,q,
            function(data){
                $('#page_div').html(data);
            }, 'html');
        }
    }
    function cariPopup(){
        var url = '${pageContext.request.contextPath}/pelupusan/draf_mmk_mlk?search';
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=900,height=700 scrollbars=yes");
    }
    function cariPopup2(){
        var url = '${pageContext.request.contextPath}/pelupusan/draf_mmk_mlk?showFormCariKodSekatan';
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=900,height=700 scrollbars=yes");
    }
    function semakSyor(f,v){
        var q = $(f).formSerialize();
        $.post('${pageContext.request.contextPath}/pelupusan/draf_mmk_mlk?showsyortolaklulus&syortolaklulus='+v,q,
        function(data){
            $('#page_div').html(data);
        }, 'html');
    }
</script>
<s:form beanclass="etanah.view.stripes.pelupusan.DrafMMKNMlkActionBean">
    <s:messages/>
    <s:errors/>
    <s:hidden name="kertasK.idKertas"/>
    <s:hidden name="edit" id="edit"/>
    <s:hidden name="editPTD" id="editPTD"/>
    <s:hidden name="editPTG" id="editPTG"/>
    <s:hidden name="openPTD" id="openPTD"/>
    <s:hidden name="openPTG" id="openPTG"/>
    <s:hidden name="firstTimeOpen" id="firstTimeOpen"/>
    <table width="90%" border="0" >
        <tr>
            <td colspan="4">
                <div class="subtitle" style="text-transform: capitalize">
                    <fieldset class="aras1">
                        <legend> </legend>
                        <div class="content" align="center">                            
                            <table border="0" width="80%" cellspacing="10%" align="center">

                                <tr><td id="tdLabel" ><b>
                                            <font style="text-transform: capitalize">
                                            <tr><td>
                                                    <p><b>
                                                            <c:if test="${!actionBean.edit}">
                                                                <s:textarea rows="6" cols="150" name="tajuk1" class="normal_text" style="text-transform: uppercase"/>
                                                            </c:if>
                                                            <c:if test="${actionBean.edit}">
                                                                <span style="text-transform: uppercase">${actionBean.tajuk1}</span>
                                                            </c:if>
                                                        </b></p>
                                                </td>
                                            </tr>
                                            </font></b></td></tr>
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
        <tr>
            <td colspan="4">&nbsp;</td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td valign="top">1.1</td>
            <td colspan="2">
                ${actionBean.tujuan}
            </td>
        </tr>
        <tr>

            <td colspan="4">
                &nbsp;
            </td>
        </tr>
        <tr>
            <td><b>2.</b></td>
            <td colspan="3"><b> LATAR BELAKANG TANAH</b></td>
        </tr>
        <tr>
            <td colspan="4">&nbsp;</td>
        </tr>

        <tr>
            <td>&nbsp;</td>
            <td width="1%" valign="top">2.1</td>
            <td colspan="2"> ${actionBean.perihalPemohon21}</td>
        </tr>
        <c:if test="${actionBean.openPTD}">
            <c:if test="${actionBean.editPTD}">                
                <c:set var="i" value="2" />
                <c:forEach items="${actionBean.senaraiLatarBelakang}" var="line">
                    <tr>
                        <td>&nbsp;</td>
                       <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTGSA'}">
                                <td valign="top"><c:out value="2.${i}"/></td>
                       </c:if>
                        <td colspan="2">
                                <s:textarea  id="kandungan2${i}"name="senaraiLatarBelakang[${i-2}].kandungan" cols="120"  rows="5" class="normal_text"/>
                                <s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan},this.form)"></s:button>
                       </td>
                     </tr>
                <c:set var="i" value="${i+1}" />        
                </c:forEach>
                <s:hidden name="rowCount2" value="${fn:length(actionBean.senaraiLatarBelakang)}" id="rowCount2"/>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td colspan="2">
                        <c:if test="${actionBean.editPTD}">
                            <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('2',this.form)"></s:button> 
                            <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('2',${i-1},this.form)"></s:button>
                        </c:if>
                        <c:if test="${!actionBean.editPTD}">
                            &nbsp;
                        </c:if>
                    </td>                                
                </tr>
                <tr>
                    <td colspan="4">&nbsp;</td>
                </tr>
                </c:if>
                <c:if test="${!actionBean.editPTD}">                
                <c:set var="i" value="2" />
                <c:forEach items="${actionBean.senaraiLatarBelakang}" var="line">
                    <tr>
                        <td>&nbsp;</td>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTGSA'}">
                                <td valign="top"><c:out value="2.${i}"/></td>
                        </c:if>
                        <td colspan="2">
                                ${line.kandungan}<s:hidden name="kandungan2${i}" id="kandungan2${i}"/>
                        </td>
                     </tr>
                <c:set var="i" value="${i+1}" />        
                </c:forEach>
                <s:hidden name="rowCount2" value="${fn:length(actionBean.senaraiLatarBelakang)}" id="rowCount2"/>                
                <tr>
                    <td colspan="4">&nbsp;</td>
                </tr>                
                </c:if>
          </c:if>
        <tr>
            <td colspan="4">&nbsp;</td>
        </tr>
        <tr>
            <td><b>3.</b></td>
            <td colspan="3"><b> PERIHAL TANAH</b></td>
        </tr>
        <tr>
            <td colspan="4">&nbsp;</td>
        </tr>

        <tr>
            <td>&nbsp;</td>
            <td width="1%">3.1</td>
            <td colspan="2"> Butir-butir tanah adalah seperti berikut :</td>
        </tr>
        
        <tr>
            <td>&nbsp;</td>
             <td width="1%"> </td>
            <td width="1%"></td>
            <td><display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="20" cellpadding="0" cellspacing="0" id="line"
                           requestURI="/pelupusan/draf_mmk_mlk">
                <c:if test="${line.hakmilik ne null}">
                    <display:column title="No" sortable="true">${line_rowNum}
                    <s:hidden name="x" class="x${line_rowNum -1}" value="${line.id}"/></display:column>
                    <a><display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="popup hakmilik${line_rowNum}" style="vertical-align:baseline"/></a>
                    <display:column title="No.Lot/PT" property="hakmilik.noLot"/>
                    <display:column title="Luas" style="vertical-align:baseline"><fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                    <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah" style="vertical-align:baseline"/>
                    <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" style="vertical-align:baseline"/>
                </c:if>
            </display:table> </td>
        </tr>
        
        
        <c:if test="${actionBean.openPTD}">
            <c:if test="${actionBean.editPTD}">
                 <tr>
                    <td colspan="4">&nbsp;</td>
                 </tr>
                <c:set var="i" value="2" />
                <c:forEach items="${actionBean.senaraiPerihalTanah}" var="line">
                    <tr>
                        <td>&nbsp;</td>
                       <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTGSA'}">
                                <td valign="top"><c:out value="3.${i}"/></td>
                       </c:if>
                        <td colspan="2">
                                <s:textarea  id="kandungan3${i}"name="senaraiPerihalTanah[${i-2}].kandungan" cols="120"  rows="5" class="normal_text"/>
                                <s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan},this.form)"></s:button>
                       </td>
                     </tr>
                <c:set var="i" value="${i+1}" />        
                </c:forEach>
                <s:hidden name="rowCount3" value="${fn:length(actionBean.senaraiPerihalTanah)}" id="rowCount3"/>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td colspan="2">
                        <c:if test="${actionBean.editPTD}">
                            <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('3',this.form)"></s:button> 
                            <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('3',${i-1},this.form)"></s:button>
                        </c:if>
                        <c:if test="${!actionBean.editPTD}">
                            &nbsp;
                        </c:if>
                    </td>                                
                </tr>
                <tr>
                    <td colspan="4">&nbsp;</td>
                </tr>
                </c:if>
                <c:if test="${!actionBean.editPTD}">                
                <c:set var="i" value="2" />
                <c:forEach items="${actionBean.senaraiLatarBelakang}" var="line">
                    <tr>
                        <td>&nbsp;</td>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTGSA'}">
                                <td valign="top"><c:out value="2.${i}"/></td>
                        </c:if>
                        <td colspan="2">
                                ${line.kandungan}<s:hidden name="kandungan2${i}" id="kandungan2${i}"/>
                        </td>
                     </tr>
                <c:set var="i" value="${i+1}" />        
                </c:forEach>
                <s:hidden name="rowCount2" value="${fn:length(actionBean.senaraiLatarBelakang)}" id="rowCount2"/>                
                <tr>
                    <td colspan="4">&nbsp;</td>
                </tr>                
                </c:if>
          </c:if>
        <tr>
            <td colspan="4">&nbsp;</td>
        </tr>
        
        <tr>
            <td><b>4.</b></td>
            <td colspan="3"><b> PERIHAL PEMOHON</b></td>
        </tr>
        <tr>
            <td colspan="4">&nbsp;</td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td>4.1</td>
            <td colspan="2">${actionBean.perihalPemohon41}</td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td valign="top">4.2</td>
            <td colspan="2">${actionBean.perihalPemohon42}</td>
        </tr>
        <c:if test="${actionBean.openPTD}">
            <c:if test="${actionBean.editPTD}">
                 
                <c:set var="i" value="3" />
                <c:forEach items="${actionBean.senaraiPerihalPemohon}" var="line">
                    <tr>
                        <td>&nbsp;</td>
                       <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTGSA'}">
                                <td valign="top"><c:out value="4.${i}"/></td>
                       </c:if>
                        <td colspan="2">
                                <s:textarea  id="kandungan4${i}"name="senaraiPerihalPemohon[${i-3}].kandungan" cols="120"  rows="5" class="normal_text"/>
                                <s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan},this.form)"></s:button>
                       </td>
                     </tr>
                <c:set var="i" value="${i+1}" />        
                </c:forEach>
                <s:hidden name="rowCount4" value="${fn:length(actionBean.senaraiPerihalPemohon)}" id="rowCount4"/>
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
                </c:if>
                <c:if test="${!actionBean.editPTD}">                
                <c:set var="i" value="2" />
                <c:forEach items="${actionBean.senaraiLatarBelakang}" var="line">
                    <tr>
                        <td>&nbsp;</td>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTGSA'}">
                                <td valign="top"><c:out value="2.${i}"/></td>
                        </c:if>
                        <td colspan="2">
                                ${line.kandungan}<s:hidden name="kandungan2${i}" id="kandungan2${i}"/>
                        </td>
                     </tr>
                <c:set var="i" value="${i+1}" />        
                </c:forEach>
                <s:hidden name="rowCount2" value="${fn:length(actionBean.senaraiLatarBelakang)}" id="rowCount2"/>                
                <tr>
                    <td colspan="4">&nbsp;</td>
                </tr>                
                </c:if>
          </c:if>
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
         <tr>
            <td colspan="4">&nbsp;</td>
        </tr>
        <c:if test="${actionBean.openPTD}">
            <c:if test="${actionBean.editPTD}">
                <tr>
                    <td width="1%"><b><c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTGSA'}"> 5.</c:if>
                                  </b>
                    </td>
                    <td colspan="3"><div align="left"><b><font style="text-transform: capitalize">PERAKUAN PENTADBIR TANAH MELAKA TENGAH</font></b></div></td>
                </tr>
                <c:set var="i" value="1" />
                <c:forEach items="${actionBean.senaraiLaporanKandunganPTD}" var="line">
                    <tr>
                        <td>&nbsp;</td>
                       <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTGSA'}">
                                <td valign="top"><c:out value="5.${i}"/></td>
                            </c:if>
                          <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PTGSA'}">
                              <td valign="top"><c:out value="5.${i}"/></td>
                          </c:if>
                        <td colspan="2">
                                <s:textarea  id="kandungan5${i}"name="senaraiLaporanKandunganPTD[${i-1}].kandungan" cols="120"  rows="5"/>
                                <s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan},this.form)"></s:button>
                       </td>
                     </tr>
                <c:set var="i" value="${i+1}" />        
                </c:forEach>
                <s:hidden name="rowCount5" value="${fn:length(actionBean.senaraiLaporanKandunganPTD)}" id="rowCount5"/>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td colspan="2">
                        <c:if test="${actionBean.editPTD}">
                            <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('5',this.form)"></s:button> 
                            <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('5',${i-1},this.form)"></s:button>
                        </c:if>
                        <c:if test="${!actionBean.editPTD}">
                            &nbsp;
                        </c:if>
                    </td>                                
                </tr>
                <tr>
                    <td colspan="4">&nbsp;</td>
                </tr>
                </c:if>
                <c:if test="${!actionBean.editPTD}">
                <tr>
                    <td width="1%"><b>
                                      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTGSA'}">5</c:if>
                                  </b>
                    </td>
                    <td colspan="3"><div align="left"><b><font style="text-transform: capitalize">PERAKUAN PENTADBIR TANAH MELAKA TENGAH</font></b></div></td>
                </tr>
                <c:set var="i" value="1" />
                <c:forEach items="${actionBean.senaraiLaporanKandunganPTD}" var="line">
                    <tr>
                        <td>&nbsp;</td>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTGSA'}">
                                <td valign="top"><c:out value="5.${i}"/></td>
                            </c:if>
                        <td colspan="2">
                                ${line.kandungan}<s:hidden name="kandungan5${i}" id="kandungan5${i}"/>
                        </td>
                     </tr>
                <c:set var="i" value="${i+1}" />        
                </c:forEach>
                <s:hidden name="rowCount5" value="${fn:length(actionBean.senaraiLaporanKandunganPTD)}" id="rowCount5"/>
                
                <tr>
                    <td colspan="4">&nbsp;</td>
                </tr>
                
                </c:if>
                 <c:if test="${!actionBean.editPTD}">
<!--                     <tr>
                        <td width="1%">
                            <b><c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTGSA'}">6</c:if>
                            </b>
                        </td>
                        <td colspan="3"><div align="left"><b><font style="text-transform: capitalize">SYOR PENTADBIR TANAH MELAKA TENGAH</font></b></div></td>
                     </tr>-->
<!--                     <tr>
                        <td>&nbsp;</td>
                        <td>Syor</td>
                        <td colspan="2">
                            <c:if test="${actionBean.kodKepu eq 'L'}">Diluluskan</c:if>
                            <c:if test="${actionBean.kodKepu eq 'T'}">Ditolak</c:if>                        
                        </td>
                     </tr>-->
                    <tr>
                        <td colspan="4"></td>
                    </tr>
                </c:if>
          </c:if>         
    </table>
<!--    <table width="90%" border="0" >
        <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td colspan="2"><b> Keputusan </b> :                                   
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
            <td colspan="4"></td>
        </tr>
            <c:if test="${actionBean.syortolaklulus eq 'ST'}">
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td colspan="2">
                        Oleh yang demikian, Pentadbir Tanah Melaka mengesyorkan supaya permohonan ini dikemukakan kepada Majlis Mesyuarat Kerajaan untuk ditolak.
                    </td>
                </tr>
                <tr>
                    <td colspan="4">&nbsp;</td>
                </tr>
            </c:if>
                <tr>
                    <td colspan="4">&nbsp;</td>
                </tr>
    </table>  -->
<!--    <div id="ta">
    <table width="90%" border="0" >
        <tr>
            <td width="1%">&nbsp;</td>
           <td colspan="3">
                <c:if test="${actionBean.editPTD}">
                     <s:textarea name="syor" rows="7" cols="150"/>
                </c:if>
                <c:if test="${!actionBean.editPTD}">
                    <span>${actionBean.syor}</span> 
                </c:if>
            </td>
        </tr>
    </table>     
    </div>-->
    
<!--            
    <c:if test="${actionBean.editPTD}">
        <p align="center">
           <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
        </p>
    </c:if>    -->
    <c:if test="${actionBean.openPTG}">
        <table width="90%" border="0">
            <c:if test="${actionBean.editPTG}">
                <tr>
                    <td width="1%"><b><c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTGSA'}">6</c:if>
                                  </b>
                    </td>
                    <td colspan="3"><div align="left"><b><font style="text-transform: capitalize">PERAKUAN PENGARAH TANAH DAN GALIAN</font></b></div></td>
                </tr>
                <c:set var="i" value="1" />
                <c:forEach items="${actionBean.senaraiLaporanKandunganPTG}" var="line">
                    <tr>
                        <td>&nbsp;</td>                        
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTGSA'}">
                                <td valign="top"><c:out value="6.${i}"/></td>
                            </c:if>
                          
                        </b>                                            
                        <td colspan="2">
                                <s:textarea  id="kandungan6${i}"name="senaraiLaporanKandunganPTG[${i-1}].kandungan" cols="120"  rows="5"/>
                                <s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan},this.form)"></s:button>
                       </td>
                     </tr>
                <c:set var="i" value="${i+1}" />        
                </c:forEach>
                <s:hidden name="rowCount6" value="${fn:length(actionBean.senaraiLaporanKandunganPTG)}" id="rowCount6"/>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td colspan="2">
                        <c:if test="${actionBean.editPTG}">
                            <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('6',this.form)"></s:button> 
                            <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('6',${i-1},this.form)"></s:button>
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
                    <td width="1%"><b>
                                      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTGSA'}"> 6</c:if>
                                      
                                  </b>
                    </td>
                    <td colspan="3"><div align="left"><b><font style="text-transform: capitalize">PERAKUAN PENGARAH TANAH DAN GALIAN</font></b></div></td>
                </tr>
                <c:set var="i" value="1" />
                <c:forEach items="${actionBean.senaraiLaporanKandunganPTG}" var="line">
                    <tr>
                        <td>&nbsp;</td>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTGSA'}">
                                <td width="1%"><c:out value="6.${i}"/></td>
                        </c:if>                          
                        <td colspan="2">
                                <div align="left">${line.kandungan}</div><s:hidden name="kandungan6${i}" id="kandungan6${i}"/>
                        </td>
                     </tr>
                <c:set var="i" value="${i+1}" />        
                </c:forEach>
                <s:hidden name="rowCount6" value="${fn:length(actionBean.senaraiLaporanKandunganPTG)}" id="rowCount6"/>                
                <tr>
                    <td colspan="4">&nbsp;</td>
                </tr>                
                </c:if>
        </table>
    </c:if>                
</s:form>
