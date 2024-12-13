<%-- 
    Document   : kertas_siasaatn_N9
    Created on : Jan 11, 2012, 3:50:04 PM
    Author     : sitinorajar
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"

   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">

        <script type="text/javascript">
                function addRow(index,f)
                {
                    var q = $(f).formSerialize();
                    $.post('${pageContext.request.contextPath}/pelupusan/minit_mesyuarat_jksmn?tambahRow&index='+index,q,
                    function(data){
                        $('#page_div').html(data);
                    }, 'html');
                }

                function addRow1(index,f)
                {
                    var q = $(f).formSerialize();
                    $.post('${pageContext.request.contextPath}/pelupusan/minit_mesyuarat_jksmn?tambahRow1&index='+index,q,
                    function(data){
                        $('#page_div').html(data);
                    }, 'html');
                }

                function deleteRow(idKandungan,f)
                {
                    if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
                         var q = $(f).formSerialize();
                        $.post('${pageContext.request.contextPath}/pelupusan/minit_mesyuarat_jksmn?deleteRow&idKandungan='+idKandungan,q,
                        function(data){
                            $('#page_div').html(data);
                        }, 'html');
                    }
                }
         </script>
    </head>
    <body>
       <s:form beanclass="etanah.view.stripes.pelupusan.MinitMesyuaratJKSMNActionBean">
           <s:messages/>
           <fieldset class="aras1">
               <table width="100%" border="0">
                   <tr>
                       <td colspan="4">&nbsp;</td>
                   </tr>
                   <tr>
                       <td colspan="4" align="center">
                           <p>
                               <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PCRG'}">
                                   <span style="text-transform: uppercase;color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">MINIT MESYUARAT JKSMN BAGI PERMOHONAN PERMIT MENCARIGALI DARIPADA ${actionBean.pemohon.pihak.nama} UNTUK ${actionBean.permohonan.sebab} <br> ${actionBean.hakmilikPermohonan.hakmilik.lot.nama} ${actionBean.hakmilikPermohonan.hakmilik.noLot} ${actionBean.hakmilikPermohonan.bandarPekanMukimBaru.nama} DAERAH ${actionBean.hakmilikPermohonan.hakmilik.cawangan.daerah.nama} MENGIKUT SEKSYEN 41 ENAKMEN MINERAL NEGERI 2002</span>
                               </c:if>
                               <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PJLB'}">
                                   <span style="text-transform: uppercase;color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">MINIT MESYUARAT JKSMN BAGI PERMOHONAN LESEN PAJAKAN LOMBONG DARIPADA ${actionBean.pemohon.pihak.nama} UNTUK ${actionBean.permohonan.sebab} <br> ${actionBean.hakmilikPermohonan.hakmilik.lot.nama} ${actionBean.hakmilikPermohonan.hakmilik.noLot} ${actionBean.hakmilikPermohonan.bandarPekanMukimBaru.nama} DAERAH ${actionBean.hakmilikPermohonan.hakmilik.cawangan.daerah.nama} MENGIKUT SEKSYEN 63 ENAKMEN MINERAL NEGERI 2002</span>
                               </c:if>
                           </p>
                       </td>
                   </tr>
                   <tr>
                       <td colspan="4">&nbsp;</td>
                   </tr>
                   <tr>
                       <td width="1%"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">1.</b></td>
                       <td colspan="3"><div align="left"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">TUJUAN</b></div></td>
                   </tr>
                    
                   <c:set var="i" value="1" />
                   <c:forEach items="${actionBean.mohonKertasKandListOne}" var="line" begin="0">
                   <s:hidden  name="1idMohonKertas${i}" id="1idMohonKertas${i}" value="${line.idKandungan}"/>
                       <tr>
                           <td>&nbsp;</td>
                           <td valign="top"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">1.${i}</b></td>
                           <td width="50%"><s:textarea rows="6" cols="150" name="1kandungan${i}" class="normal_text" value="${line.kandungan}"/></td>
                           <td align="left"><s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan},this.form)"></s:button></td>
                       </tr>
                   <c:set var="i" value="${i+1}" />
                   </c:forEach>
                        
                   <c:if test="${actionBean.edit}">
                       <tr>
                           <td>&nbsp;</td>
                           <td valign="top"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">1.${fn:length(actionBean.mohonKertasKandListOne)+1}</b></td>
                           <td width="50%"><s:textarea rows="6" cols="150" name="1kandungan${fn:length(actionBean.mohonKertasKandListOne)+1}" class="normal_text"/></td>
                           <td align="left"><s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow('1')"></s:button></td>
                       </tr>
                   </c:if>
                       
                    <tr>
                         <td>&nbsp;</td>
                         <td>&nbsp;</td>
                         <td colspan="2"> 
                             <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('1',this.form)"></s:button>
                             <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="addRow1('1',this.form)"></s:button>
                         </td>
                    </tr>
                    
                    <tr>
                        <td colspan="4">&nbsp;</td>
                    </tr>
                    <tr>
                        <td width="1%"> <b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">2.</b></td>
                        <td colspan="3"><div align="left"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">KEDUDUKAN</b></div></td>
                    </tr>
                    <c:set var="i" value="1" />
                    <c:forEach items="${actionBean.mohonKertasKandListTwo}" var="line1" begin="0">
                    <s:hidden  name="2idMohonKertas${i}" id="2idMohonKertas${i}" value="${line1.idKandungan}"/>
                    <tr>
                        <td>&nbsp;</td>
                        <td valign="top"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">2.${i}</b></td>
                        <td width="50%"><s:textarea rows="6" cols="150" name="2kandungan${i}" class="normal_text" value="${line1.kandungan}"/></td>
                        <td align="left"><s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line1.idKandungan},this.form)"></s:button></td>
                    </tr>
                    <c:set var="i" value="${i+1}" />
                    </c:forEach>
                    <c:if test="${actionBean.edit1}">
                    <tr>
                        <td>&nbsp;</td>
                        <td valign="top"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">2.${fn:length(actionBean.mohonKertasKandListTwo)+1}</b></td>
                        <td width="50%"><s:textarea rows="6" cols="150" name="2kandungan${fn:length(actionBean.mohonKertasKandListTwo)+1}" class="normal_text"/></td>
                        <td align="left"><s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow('2')"></s:button></td>
                    </tr>
                    </c:if>
                    <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td colspan="2"> 
                            <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('2',this.form)"></s:button>
                            <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="addRow1('2',this.form)"></s:button>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4">&nbsp;</td>
                    </tr>
                    <tr>
                        <td width="1%"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">3.</b></td>
                        <td colspan="3"><div align="left"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">PERSEKITARAN</b></div></td>
                    </tr>

                   
                    <c:set var="i" value="1" />
                    <c:forEach items="${actionBean.mohonKertasKandListThree}" var="line2" begin="0">
                    <s:hidden  name="3idMohonKertas${i}" id="3idMohonKertas${i}" value="${line2.idKandungan}"/>
                    <tr>
                        <td>&nbsp;</td>
                        <td valign="top"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">3.${i}</b></td>
                        <td width="50%"><s:textarea rows="6" cols="150" name="3kandungan${i}" class="normal_text" value="${line2.kandungan}"/></td>
                        <td align="left"><s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line2.idKandungan},this.form)"></s:button></td>
                    </tr>
                    <c:set var="i" value="${i+1}" />
                    </c:forEach>
                    <c:if test="${actionBean.edit2}">
                    <tr>
                        <td>&nbsp;</td>
                        <td valign="top"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">3.${fn:length(actionBean.mohonKertasKandListThree)+1}</b></td>
                        <td width="50%"><s:textarea rows="6" cols="150" name="3kandungan${fn:length(actionBean.mohonKertasKandListThree)+1}" class="normal_text"/></td>
                        <td align="left"><s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow('3')"></s:button></td>
                    </tr>
                    </c:if>
                    <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td colspan="2"> 
                            <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('3',this.form)"></s:button>
                            <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="addRow1('3',this.form)"></s:button>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4">&nbsp;</td>
                    </tr>
<!--                    <tr>
                            <td width="1%"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">4.</b></td>
                            <td colspan="3"><div align="left"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">SYOR</b></div></td>
                    </tr>
                    <c:set var="i" value="1" />
                    <c:forEach items="${actionBean.mohonKertasKandListFour}" var="line3" begin="0">
                    <s:hidden  name="4idMohonKertas${i}" id="4idMohonKertas${i}" value="${line3.idKandungan}"/>
                    <tr>
                            <td>&nbsp;</td>
                            <td valign="top"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">4.${i}</b></td>
                            <td width="50%"><s:textarea rows="6" cols="150" name="4kandungan${i}" class="normal_text" value="${line3.kandungan}"/></td>
                            <td align="left"><s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line3.idKandungan},this.form)"></s:button></td>
                    </tr>
                    <c:set var="i" value="${i+1}" />
                    </c:forEach>
                    <c:if test="${actionBean.edit3}">
                    <tr>
                            <td>&nbsp;</td>
                            <td valign="top"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">4.${fn:length(actionBean.mohonKertasKandListFour)+1}</b></td>
                            <td width="50%"><s:textarea rows="6" cols="150" name="4kandungan${fn:length(actionBean.mohonKertasKandListFour)+1}" class="normal_text"/></td>
                            <td align="left"><s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow('4')"></s:button></td>
                    </tr>
                    </c:if>
                    <tr>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td colspan="2"> <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('4',this.form)"></s:button>
                                        <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="addRow1('4',this.form)"></s:button></td>
                    </tr>-->
                                        
  
                </table>
      </fieldset>
       </s:form>
    </body>
</html>
