<%-- 
    Document   : draf_jkm_mlk
    Author     : Orogenic
--%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript">
    
    function menyimpan(index,i,f){
        var kand;
        if(index==3){
            kand = document.getElementById("kandungan3"+i).value;
        }
        if(index==4){
            kand = document.getElementById("kandungan4"+i).value;
        }
        if(index==9){
            kand = document.getElementById("kandungan9"+i).value;
        }
        if(index==10){
            kand = document.getElementById("kandungan10"+i).value;
        }
        if(index==2){
            kand = document.getElementById("kandungan2"+i).value;
        }
        var viewMMKN = $('#viewMMKN').val();
        var q = $(f).formSerialize();
        $.post('${pageContext.request.contextPath}/pelupusan/draf_jkm_mlcrg?simpanKandungan&index='+index+'&kandungan='+kand+'&viewMMKN='+viewMMKN,q,
        function(data){
            $('#page_div').html(data);
        }, 'html');
    }
    function deleteRow(idKandungan,f){
        if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
            var viewMMKN = $('#viewMMKN').val();
            var q = $(f).formSerialize();
            $.post('${pageContext.request.contextPath}/pelupusan/draf_jkm_mlcrg?deleteRow&idKandungan='+idKandungan+'&viewMMKN='+viewMMKN,q,
            function(data){
                $('#page_div').html(data);
            }, 'html');
        }}
    
      
    function addRow(index){
        var viewMMKN = $('#viewMMKN').val();
        var url = '${pageContext.request.contextPath}/pelupusan/draf_jkm_mlcrg?tambahRow&index='+index+'&viewMMKN='+viewMMKN;

        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
        
    }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.stripes.pelupusan.DrafJKMMLCRGActionBean" name="form" id="form">
    <s:hidden id="viewMMKN" name="viewMMKN" value="${actionBean.viewForMMKN}"/>
    <s:messages/>
    <s:errors/>
    <table width="80%" border="0">
        <!--TajukUtama-->
        <tr>
            <td colspan="4">
                <div class="subtitle" style="text-transform: capitalize">
                    <fieldset class="aras1">
                        <div class="content" align="center">
                            <table border="0" width="70%" cellspacing="10%" align="center">
                                <tr><td id="tdLabel"><b><font style="text-transform: capitalize">
                                                <tr><td>
                                                        <p align="center"><b><span style="text-transform:uppercase">${actionBean.tajukUtama}</span></b></p>
                                                    </td></tr></font></b></td></tr>
                                <tr><td>&nbsp;</td></tr>
                            </table>
                        </div>
                    </fieldset>
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="4">&nbsp;</td>
        </tr>
        <!--TujuanPermohonan-->
        <tr>
            <td><b>1.</b></td>
            <td colspan="3"><div align="left"><b>TUJUAN</b></div></td>
        </tr>
        <tr>
            <td colspan="4">&nbsp;</td>
        </tr>
        <c:set var="i" value="1" />
        <tr>
            <td>&nbsp;</td>
            <td>1.1</td>
            <td></td>
            <td> 
                <c:if test="${!actionBean.editTujuan}">
                    ${actionBean.tujuanPermohonan} 
                </c:if>
                <c:if test="${actionBean.editTujuan}">
                    <s:textarea id="kandungan2${i}" name="tujuanPermohonan" class="normal_text" rows="5" cols="150" readonly="false"/>
                </c:if>
            </td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td></td>
            <td  align="left">
                <c:if test="${actionBean.editTujuan}">
                    <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('2',${i})"></s:button>
                </c:if>
            </td>
        </tr>
        <tr>
            <td colspan="4">&nbsp;</td>
        </tr>
        <tr>
            <td><b>2.</b></td>
            <td colspan="3"><div align="left"><b>LATAR BELAKANG</b></div></td>
        </tr>
        <tr>
            <td colspan="4">&nbsp;</td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td>2.1</td>
            <td></td>
            <td>
                <!--latar belakang -->
                ${actionBean.latarbelakang} 
            </td>
        </tr>

        <!--TujuanPermohonan-->
        <!--tr>
            <td><b>3.</b></td>
            <td colspan="3"><div align="left"><b>PERIHAL TANAH</b></div></td>
        </tr-->

        <!--tr>
            <td colspan="4">&nbsp;</td>
        </tr-->
        <tr>
            <td>&nbsp;</td>
            <td>2.2</td>
            <td></td>
            <td>
                ${actionBean.ulasanPerihalTanah1}    
                <!--                  
                <s:textarea name="ulasanPerihalTanah1" class="normal_text" rows="5" cols="150" readonly="true"/>
                -->
            </td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td>2.3</td>
            <td></td>
            <td colspan="2">
                ${actionBean.ulasanPerihalTanah2}    
                <!--                  
                <s:textarea name="ulasanPerihalTanah2" class="normal_text" rows="5" cols="150" readonly="true"/>
                -->
            </td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td>2.4</td>
            <td></td>
            <td colspan="2">
                ${actionBean.perihalPemohon}    
                <!--                  
                <s:textarea name="ulasanPerihalTanah2" class="normal_text" rows="5" cols="150" readonly="true"/>
                -->
            </td>
        </tr>
        <tr>
            <td colspan="4">&nbsp;</td>
        </tr>
        <!--UlasanYB-->
        <tr>
            <td><b>3.</b></td>
            <td colspan="3"><div align="left"><b><font style="text-transform: capitalize">Ulasan YB. ADUN KAWASAN ${actionBean.kodDun.nama}</font></b></div></td>
        </tr>

        <tr>
            <td colspan="4">&nbsp;</td>
        </tr>
        <c:set var="i" value="1" />
        <c:forEach items="${actionBean.senaraiYBAdun}" var="line">
            <tr>
                <td>&nbsp;</td>
                <td valign="top"><c:out value="5.${i}"/></td>
                <td>&nbsp;</td>
                <td colspan="2"><font style="font-weight:bold;">${line.namaAgensi}</font></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td valign="top">&nbsp;</td>
                <td colspan="2">( Ruj. Surat ${line.noRujukan} bertarikh <s:format value="${line.tarikhRujukan}" formatPattern="dd/MM/yyyy"/> )</td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>                                  
                    ${line.ulasan}
                </td>
            </tr>
            <c:set var="i" value="${i+1}" />
        </c:forEach>
        <tr>
            <td colspan="4">&nbsp;</td>
        </tr>

        <!--UlasanJabatanTeknikal-->
        <tr>
            <td><b>4.</b></td>
            <td colspan="3"><div align="left"><b>ULASAN JABATAN TEKNIKAL</b></div></td>
        </tr>

        <tr>
            <td colspan="4">&nbsp;</td>
        </tr>
        <c:set var="i" value="1" />
        <c:forEach items="${actionBean.senaraiUlasanJabatanTeknikal}" var="line">
            <tr>
                <td>&nbsp;</td>
                <td valign="top"><c:out value="4.${i}"/></td>
                <td>&nbsp;</td>
                <td colspan="2"><font style="font-weight:bold;">${line.namaAgensi}</font></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td valign="top">&nbsp;</td>
                <td colspan="2">( Ruj. Surat ${line.noRujukan} bertarikh <s:format value="${line.tarikhRujukan}" formatPattern="dd/MM/yyyy"/> )</td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>                                  
                    ${line.ulasan}
                </td>
            </tr>
            <c:set var="i" value="${i+1}" />
        </c:forEach>

        <!--Keputusan JKM-->
        <c:if test="${actionBean.viewMMKN}">
            <tr>  
                <td><b>9.</b></td>
                <td colspan="3"><div align="left"><b>KEPUTUSAN JAWATANKUASA SUMBER MINERAL</b></div></td>
            </tr>
            <tr>
                <td colspan="4">&nbsp;</td>
            </tr>

            <tr>  
                <td>&nbsp;</td>
                <td colspan="3">
                    <table border="0">
                        <tr>
                            <td><div align="right">Syor : </div></td>
                            <td colspan="3">
                                <c:choose>
                                    <c:when test="${actionBean.mohonRujLuarJKM.diSokong eq 'L'}">
                                        Diluluskan
                                    </c:when>
                                    <c:when test="${actionBean.mohonRujLuarJKM.diSokong eq 'T'}">
                                        Ditolak
                                    </c:when>
                                </c:choose>                            
                            </td>
                        </tr>
                        <tr>
                            <td><div align="right">Ulasan : </div></td>
                            <td colspan="3">${actionBean.mohonRujLuarJKM.ulasan}</td>
                        </tr>
                        <c:set var="i" value="1" />
                        <c:set var="k" value="1" />
                        <c:forEach items="${actionBean.senaraiLaporanUlas}" var="line">

                            <tr>
                                <td>
                                    <c:choose>
                                        <c:when test="${i eq 1}">
                                            Terma Dan Syarat :
                                        </c:when>
                                        <c:otherwise>
                                            &nbsp;
                                        </c:otherwise>                                                
                                    </c:choose>
                                </td>
                                <td>&nbsp;</td>
                                <td colspan="2">${i}) ${line.ulasan}</td>

                            </tr>
                            <c:set var="i" value="${i+1}" />
                            <c:set var="k" value="${k+1}" />
                        </c:forEach>
                    </table>

                </td>                
            </tr>

            <tr>
                <td colspan="4">&nbsp;</td>
            </tr>
        </c:if>
        <!--Perakuan PTD-->
        <c:if test="${!actionBean.viewMMKN}">
            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'LMCRG' and actionBean.permohonan.kodUrusan.kod ne 'PJLB'}">
                <!--tr>
                    <td><b>9.</b></td>
                    <td colspan="3">
                        <div align="left">
                            <b>PERAKUAN PENTADBIR TANAH
                            </b>
                        </div>
                    </td>
                </tr-->
            </c:if>

        </c:if>
        <c:if test="${actionBean.viewMMKN}">
            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'LMCRG' and actionBean.permohonan.kodUrusan.kod ne 'PJLB'}">
                <!--tr>
                    <td><b>10.</b></td>
                    <td colspan="3"><div align="left"><b>PERAKUAN PENTADBIR TANAH </b></div></td>
                </tr-->
            </c:if>

        </c:if>
        <tr>
            <td colspan="4">&nbsp;</td>
        </tr>
        <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'LMCRG' and actionBean.permohonan.kodUrusan.kod ne 'PJLB'}">
            <c:set var="i" value="1" />
            <c:set var="k" value="1" />
            <c:forEach items="${actionBean.senaraiLaporanKandunganPerakuanPTD}" var="line">

                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td style="display:none" valign="top">${line.idKandungan}</td>
                    <td valign="top">
                        <c:if test="${!actionBean.viewMMKN}">
                            <c:out value="9.${k}"/>
                        </c:if>
                        <c:if test="${actionBean.viewMMKN}">
                            <c:out value="10.${k}"/>
                        </c:if>
                    </td>

                    <!--td><s:textarea  id="kandungan9${i}" name="senaraiLaporanKandunganPerakuanPTD[${i-1}].kandungan" cols="150"  rows="5" class="normal_text"/>
                    <s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan})"></s:button>
                </td-->

                    </tr>
                <c:set var="i" value="${i+1}" />
                <c:set var="k" value="${k+1}" />
            </c:forEach>

            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <!--td  align="left">
                <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('9')"></s:button> <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('9',${i-1})"></s:button>
            </td-->
                </tr>
        </c:if>
        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LMCRG' or actionBean.permohonan.kodUrusan.kod eq 'PJLB'}">
            <c:set var="i" value="1" />
            <c:set var="k" value="1" />
            <c:forEach items="${actionBean.senaraiMohonRujukLuar}" var="line">

                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td style="display:none" valign="top">&nbsp;</td>
                    <td valign="top">
                        <c:if test="${!actionBean.viewMMKN}">
                            <c:out value="9.${k}"/>
                        </c:if>
                        <c:if test="${actionBean.viewMMKN}">
                            <c:out value="10.${k}"/>
                        </c:if>  
                    </td>

                    <td>${line.ulasan}</td>
                    <td>&nbsp;</td>
                </tr>
                <c:set var="i" value="${i+1}" />
                <c:set var="k" value="${k+1}" />
            </c:forEach>
        </c:if>
        <tr>
            <td colspan="4">&nbsp;</td>
        </tr>
        <!--Perakuan PTG-->
        <c:if test="${!actionBean.viewMMKN}">
            <tr>
                <td><b>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LMCRG' or actionBean.permohonan.kodUrusan.kod eq 'PJLB'}">
                            9.
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'LMCRG' and actionBean.permohonan.kodUrusan.kod ne 'PJLB'}">
                            5.
                        </c:if>    

                    </b></td>
                <td colspan="3"><div align="left"><b>PERAKUAN PENGARAH TANAH DAN GALIAN </b></div></td>
            </tr>
        </c:if>
        <c:if test="${actionBean.viewMMKN}">
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LMCRG' or actionBean.permohonan.kodUrusan.kod eq 'PJLB'}">
                <tr>
                    <td><b>5.</b></td>
                    <td colspan="3"><div align="left"><b>PERAKUAN PENGARAH TANAH DAN GALIAN </b></div></td>
                </tr>
            </c:if>
            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'LMCRG' and actionBean.permohonan.kodUrusan.kod ne 'PJLB'}">
                <tr>
                    <td><b>5.</b></td>
                    <td colspan="3"><div align="left"><b>PERAKUAN PENGARAH TANAH DAN GALIAN </b></div></td>
                </tr>
            </c:if>
        </c:if>
        <tr>
            <td colspan="4">&nbsp;</td>
        </tr>
        <c:set var="i" value="1" />
        <c:set var="k" value="1" />
        <c:forEach items="${actionBean.senaraiLaporanKandunganPerakuanPTG}" var="line">
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td valign="top">
                    <c:if test="${actionBean.editPerakuan}">

                        <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'LMCRG' and actionBean.permohonan.kodUrusan.kod ne 'PJLB'}">
                            <c:out value="5.${k}"/>
                        </c:if>

                    </c:if>
                    <c:if test="${actionBean.viewMMKN}">
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LMCRG' or actionBean.permohonan.kodUrusan.kod eq 'PJLB'}">
                            <c:out value="5.${k}"/>
                        </c:if>
                    </c:if>
                    <c:if test="${!actionBean.editPerakuan}">
                        <c:out value="5.${i}"/>
                    </c:if>
                </td>
                
                <td>
                    <c:if test="${actionBean.editPerakuan}">
                        <s:textarea  id="kandungan10${i}" name="senaraiLaporanKandunganPerakuanPTG[${i-1}].kandungan" cols="80"  rows="5" class="normal_text"/>
                        <s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan})"></s:button>
                    </c:if>
                    <c:if test="${!actionBean.editPerakuan}">
                       ${line.kandungan}
                    </c:if>    
                </td>
            </tr>
            <c:set var="i" value="${i+1}" />
            <c:set var="k" value="${k+1}" />
        </c:forEach>

        <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td></td>
            <td align="left">
                <c:if test="${actionBean.editPerakuan}">
                    <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('10')"></s:button> <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('10',${i-1})"></s:button>
                </c:if>   
            </td>
        </tr>

    </table>


</s:form>