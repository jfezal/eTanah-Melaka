<%-- 
    Document   : minit_mesyuarat_jksmn_view
    Created on : Jul 4, 2013, 8:35:25 PM
    Author     : User
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
                        <td colspan="4" align="center" >
                            <p>
                              <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PCRG'}">
                                   <span style="text-transform: uppercase;color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">MINIT MESYUARAT JKSMN BAGI PERMOHONAN PERMIT MENCARIGALI DARIPADA ${actionBean.pemohon.pihak.nama} UNTUK ${actionBean.permohonan.sebab} <br> ${actionBean.hakmilikPermohonan.hakmilik.lot.nama} ${actionBean.hakmilikPermohonan.hakmilik.noLot} ${actionBean.hakmilikPermohonan.bandarPekanMukimBaru.nama} DAERAH ${actionBean.hakmilikPermohonan.hakmilik.cawangan.daerah.nama} MENGIKUT SEKSYEN 41 ENAKMEN MINERAL NEGERI 2002</span>
                               </c:if>
                               <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PJLB'}">
                                   <span style="text-transform: uppercase;color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">MINIT MESYUARAT JKSMN BAGI PERMOHONAN LESEN PAJAKAN LOMBONG DARIPADA ${actionBean.pemohon.pihak.nama} UNTUK ${actionBean.permohonan.sebab} <br> ${actionBean.hakmilikPermohonan.hakmilik.lot.nama} ${actionBean.hakmilikPermohonan.hakmilik.noLot} ${actionBean.hakmilikPermohonan.bandarPekanMukimBaru.nama} DAERAH ${actionBean.hakmilikPermohonan.hakmilik.cawangan.daerah.nama} MENGIKUT SEKSYEN 41 ENAKMEN MINERAL NEGERI 2002</span>
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
                            <td width="5%" valign="top"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">1.${i}</b></td>
                            <td colspan="2">${line.kandungan}</td>
                        </tr>
                        <c:set var="i" value="${i+1}" />
                        </c:forEach>                  
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
                        <td width="5%" valign="top"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">2.${i}</b></td>
                        <td colspan="2">${line1.kandungan}</td>       
                    </tr>
                    <c:set var="i" value="${i+1}" />
                    </c:forEach>
                             
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
                        <td valign="top" width="5%"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">3.${i}</b></td>
                        <td colspan="2">${line2.kandungan}</td>      
                    </tr>
                    <c:set var="i" value="${i+1}" />
                    </c:forEach>
                    
                    <tr>
                        <td colspan="4">&nbsp;</td>
                    </tr>                  
                </table>
      </fieldset>
       </s:form>
    </body>
</html>





