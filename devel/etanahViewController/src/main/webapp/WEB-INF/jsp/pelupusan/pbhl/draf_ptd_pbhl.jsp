
<%-- 
    Document   : draf_mmkn_mlk_PBHL
    Created on : Oct 31, 2012, 3:46:20 PM
    Author     : Navin
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
                window.open("${pageContext.request.contextPath}/pelupusan/draf_ptd_pbhl?popupHakmilikDetail&idHakmilik="+$(this).text(), "eTanah",
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
        $.post('${pageContext.request.contextPath}/pelupusan/draf_ptd_pbhl?simpanKandungan&index='+index+'&kandungan='+kand,q,
        function(data){
            $('#page_div').html(data);
        }, 'html');
  
       
       
    }
    
    function addRow(index,f)
    {
        var q = $(f).formSerialize();
        $.post('${pageContext.request.contextPath}/pelupusan/draf_ptd_pbhl?tambahRow&index='+index,q,
        function(data){
            $('#page_div').html(data);
        }, 'html');
    }
    function deleteRow(idKandungan,f)
    {
        if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
            var q = $(f).formSerialize();
            $.post('${pageContext.request.contextPath}/pelupusan/draf_ptd_pbhl?deleteRow&idKandungan='+idKandungan,q,
            function(data){
                $('#page_div').html(data);
            }, 'html');
        }
    }
    function cariPopup(){
        var url = '${pageContext.request.contextPath}/pelupusan/draf_ptd_pbhl?search';
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=900,height=700 scrollbars=yes");
    }
    function cariPopup2(){
        var url = '${pageContext.request.contextPath}/pelupusan/draf_ptd_pbhl?showFormCariKodSekatan';
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=900,height=700 scrollbars=yes");
    }
    function semakSyor(f,v){
        var q = $(f).formSerialize();
        $.post('${pageContext.request.contextPath}/pelupusan/draf_ptd_pbhl?showsyortolaklulus&syortolaklulus='+v,q,
        function(data){
            $('#page_div').html(data);
        }, 'html');
    }
</script>
<s:form beanclass="etanah.view.stripes.pelupusan.DrafPertimbanganPBHLActionBean">
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

                                                                <span style="text-transform: uppercase">${actionBean.tajuk1}</span>
 
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
            <td colspan="3"><b> LATAR BELAKANG</b></td>
        </tr>
        <tr>
            <td colspan="4">&nbsp;</td>
        </tr>
         <tr>
              <td>&nbsp;</td>
            <td width="1%"><b>2.1</b></td>
            <td colspan="2"><div align="left"><b> Perihal Permohonan</b></div></td>
          </tr>
        <tr>
            <td>&nbsp;</td>
            <td width="1%" valign="top">2.1.1</td>
            <td colspan="2"> ${actionBean.perihalPemohon21}</td>
        </tr>
        <c:if test="${actionBean.openPTD}">
            <c:if test="${actionBean.edit}">                
                <c:set var="i" value="2" />
                <c:forEach items="${actionBean.senaraiLatarBelakang}" var="line">
                    <tr>
                        <td>&nbsp;</td>
                       <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBHL'}">
                                <td valign="top"><c:out value="2.1.${i}"/></td>
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
                        <c:if test="${actionBean.edit}">
                            <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('2',this.form)"></s:button> 
                            <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('2',${i-1},this.form)"></s:button>
                        </c:if>
                        <c:if test="${!actionBean.edit}">
                            &nbsp;
                        </c:if>
                    </td>                                
                </tr>
                </c:if>
                <c:if test="${!actionBean.edit}">                
                <c:set var="i" value="2" />
                <c:forEach items="${actionBean.senaraiLatarBelakang}" var="line">
                    <tr>
                        <td>&nbsp;</td>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBHL'}">
                                <td valign="top"><c:out value="2.1.${i}"/></td>
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
              <td>&nbsp;</td>
            <td width="1%"><b>2.2</b></td>
            <td colspan="2"><div align="left"><b>Perihal Pemohon</b></div></td>
         </tr>
        <tr>
            <td>&nbsp;</td>
            <td>2.2.1</td>
            <td colspan="2">${actionBean.perihalPemohon41}</td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td valign="top">2.2.2</td>
            <td colspan="2">${actionBean.perihalPemohon42}</td>
        </tr>
        <c:if test="${actionBean.openPTD}">
            <c:if test="${actionBean.edit}">
                 
                <c:set var="i" value="3" />
                <c:forEach items="${actionBean.senaraiPerihalPemohon}" var="line">
                    <tr>
                        <td>&nbsp;</td>
                       <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBHL'}">
                                <td valign="top"><c:out value="2.2.${i}"/></td>
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
                        <c:if test="${actionBean.edit}">
                            <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('4',this.form)"></s:button> 
                            <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('4',${i-1},this.form)"></s:button>
                        </c:if>
                        <c:if test="${!actionBean.edit}">
                            &nbsp;
                        </c:if>
                    </td>                                
                </tr>
                </c:if>
                <c:if test="${!actionBean.edit}">                
                <c:set var="i" value="3" />
                <c:forEach items="${actionBean.senaraiPerihalPemohon}" var="line">
                    <tr>
                        <td>&nbsp;</td>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBHL'}">
                                <td valign="top"><c:out value="2.2.${i}"/></td>
                        </c:if>
                        <td colspan="2">
                                ${line.kandungan}<s:hidden name="kandungan4${i}" id="kandungan2${i}"/>
                        </td>
                     </tr>
                <c:set var="i" value="${i+1}" />        
                </c:forEach>
                <s:hidden name="rowCount2" value="${fn:length(actionBean.senaraiPerihalPemohon)}" id="rowCount2"/>                
                <tr>
                    <td colspan="4">&nbsp;</td>
                </tr>                
                </c:if>
          </c:if>   
                <tr>
            <td colspan="4">&nbsp;</td>
        </tr>
           <tr>
                    <td width="1%"><b><c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBHL'}"> 3.</c:if>
                                  </b>
                    </td>
                    <td colspan="3"><div align="left"><b><font style="text-transform: capitalize">HURAIAN PENOLONG PENTADBIR TANAH</font></b></div></td>
                </tr>
        <c:if test="${actionBean.openPTD}">
            <c:if test="${actionBean.edit}">
                 <tr>
                    <td colspan="4">&nbsp;</td>
                 </tr>
                <c:set var="i" value="1" />
                <c:forEach items="${actionBean.senaraiHuraianPenolong}" var="line">
                    <tr>
                        <td>&nbsp;</td>
                       <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBHL'}">
                                <td valign="top"><c:out value="3.${i}"/></td>
                       </c:if>
                        <td colspan="2">
                                <s:textarea  id="kandungan3${i}"name="senaraiHuraianPenolong[${i-1}].kandungan" cols="120"  rows="5" class="normal_text"/>
                                <s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan},this.form)"></s:button>
                       </td>
                     </tr>
                <c:set var="i" value="${i+1}" />        
                </c:forEach>
                <s:hidden name="rowCount3" value="${fn:length(actionBean.senaraiHuraianPenolong)}" id="rowCount3"/>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td colspan="2">
                        <c:if test="${actionBean.edit}">
                            <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('3',this.form)"></s:button> 
                            <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('3',${i-1},this.form)"></s:button>
                        </c:if>
                        <c:if test="${!actionBean.edit}">
                            &nbsp;
                        </c:if>
                    </td>                                
                </tr>
                </c:if>
                <c:if test="${!actionBean.edit}">                
                <c:set var="i" value="1" />
                <c:forEach items="${actionBean.senaraiHuraianPenolong}" var="line">
                    <tr>
                        <td>&nbsp;</td>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBHL'}">
                                <td valign="top"><c:out value="3.${i}"/></td>
                        </c:if>
                        <td colspan="2">
                                ${line.kandungan}<s:hidden name="kandungan3${i}" id="kandungan3${i}"/>
                        </td>
                     </tr>
                <c:set var="i" value="${i+1}" />        
                </c:forEach>
                <s:hidden name="rowCount2" value="${fn:length(actionBean.senaraiHuraianPenolong)}" id="rowCount2"/>                
                <tr>
                    <td colspan="4">&nbsp;</td>
                </tr>                
                </c:if>
          </c:if>
        <tr>
            <td colspan="4">&nbsp;</td>
        </tr>
           <tr>
                    <td width="1%"><b><c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBHL'}"> 4.</c:if>
                                  </b>
                    </td>
                    <td colspan="3"><div align="left"><b><font style="text-transform: capitalize">SYOR PENOLONG PENTADBIR TANAH</font></b></div></td>
                </tr>
        <c:if test="${actionBean.openPTD}">
            <c:if test="${actionBean.edit}">
                 <tr>
                    <td colspan="4">&nbsp;</td>
                 </tr>
                <c:set var="i" value="1" />
                <c:forEach items="${actionBean.senaraiSyorPenolong}" var="line">
                    <tr>
                        <td>&nbsp;</td>
                       <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBHL'}">
                                <td valign="top"><c:out value="4.${i}"/></td>
                       </c:if>
                        <td colspan="2">
                                <s:textarea  id="kandungan6${i}"name="senaraiSyorPenolong[${i-1}].kandungan" cols="120"  rows="5" class="normal_text"/>
                                <s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan},this.form)"></s:button>
                       </td>
                     </tr>
                <c:set var="i" value="${i+1}" />        
                </c:forEach>
                <s:hidden name="rowCount3" value="${fn:length(actionBean.senaraiSyorPenolong)}" id="rowCount3"/>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td colspan="2">
                        <c:if test="${actionBean.edit}">
                            <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('6',this.form)"></s:button> 
                            <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('6',${i-1},this.form)"></s:button>
                        </c:if>
                        <c:if test="${!actionBean.edit}">
                            &nbsp;
                        </c:if>
                    </td>                                
                </tr>
                </c:if>
                <c:if test="${!actionBean.edit}">                
                <c:set var="i" value="1" />
                <c:forEach items="${actionBean.senaraiSyorPenolong}" var="line">
                    <tr>
                        <td>&nbsp;</td>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBHL'}">
                                <td valign="top"><c:out value="4.${i}"/></td>
                        </c:if>
                        <td colspan="2">
                                ${line.kandungan}<s:hidden name="kandungan6${i}" id="kandungan6${i}"/>
                        </td>
                     </tr>
                <c:set var="i" value="${i+1}" />        
                </c:forEach>
                <s:hidden name="rowCount2" value="${fn:length(actionBean.senaraiSyorPenolong)}" id="rowCount2"/>                
                <tr>
                    <td colspan="4">&nbsp;</td>
                </tr>                
                </c:if>
          </c:if>
        
   
            <c:if test="${actionBean.editPTD}">
                <tr>
                    <td width="1%"><b><c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBHL'}"> 5.</c:if>
                                  </b>
                    </td>
                    <td colspan="3"><div align="left"><b><font style="text-transform: capitalize">PERAKUAN PENTADBIR TANAH</font></b></div></td>
                </tr>
                <c:set var="i" value="1" />
                <c:forEach items="${actionBean.senaraiLaporanKandunganPTD}" var="line">
                    <tr>
                        <td>&nbsp;</td>
                       <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBHL'}">
                                <td valign="top"><c:out value="5.${i}"/></td>
                            </c:if>
                          <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PBHL'}">
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
              <%--  <c:if test="${!actionBean.editPTD}">
                <tr>
                    <td width="1%"><b><c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBHL'}"> 5.</c:if>
                                  </b>
                    </td>
                    <td colspan="3"><div align="left"><b><font style="text-transform: capitalize">KEPUTUSAN PENTADBIR TANAH</font></b></div></td>
                </tr>
                <c:set var="i" value="1" />
                <c:forEach items="${actionBean.senaraiLaporanKandunganPTD}" var="line">
                    <tr>
                        <td>&nbsp;</td>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBHL'}">
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
                </c:if> --%>
                
             
    

    
</s:form>


