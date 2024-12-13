<%-- 
    Document   : maklumKeputusanBicara
    Created on : 29-03-2011, 18:53:28
    Author     : massita
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript">

         function showReport(){
             $.blockUI({
                 message: $('#displayBox'),
                 css: {
                     top:  ($(window).height() - 50) /2 + 'px',
                     left: ($(window).width() - 50) /2 + 'px',
                     width: '50px'
                 }
             });
             var url = '${pageContext.request.contextPath}/pengambilan/KeputusanPerbicaraan?genReport';
             $.ajax({
                 type:"GET",
                 url : url,
                 dataType : 'html',
                 error : function(xhr, ajaxOptions, thrownError) {
                     alert('Terdapat masalah teknikal. Sila hubungi Pentadbir Sistem.');
                     $.unblockUI();
                 },
                 success : function(data) {
                     if(data.indexOf('Laporan telah dijana')==0){
                         alert(data);
                         $('#folder').click();
                     }else {
                         alert(data);
                         $('#urusan').click();
                     }
                     $.unblockUI();
                 }
             });
         }
</script>
<s:form beanclass="etanah.view.stripes.pengambilan.JanaReportSuratKeputusanMahkamah">
    <s:messages/>
    <s:errors/>
    <c:if test="${form1}">
        <div class="subtitle">
            <div class="subtitle">
                <fieldset class="aras1">

                    <legend>
                        Makluman Permohonan Pengambilan Tanah Secara Pendudukan atau Penggunaan Sementara
                    </legend>
                    <div class="content" align="left">
                        <table align="left"  width="100%">
                            <c:if test="${HideJanaReport}">
                                <tr>
                                    <td align="left">Keputusan adalah Lulus</td>
                                </tr>
                            </c:if>

                            <c:if test="${JanaReport}">
                                <tr>
                                    <td><label style="text-align:left; color:black; width:100%; padding: 1em 5em"dir="rtl" >Keputusan Perbicaraan tidak Setuju Sila klik pada button jana dokumen untuk Surat Keputusan Mahkamah <br />
                                            <br /><center><font style="font-family: Arial; font-size: x-small; font-weight: bold;"><s:button name="genReport" id="report" value="Jana Dokumen" class="longbtn" onclick="showReport();"/></font></center></label>
                                    </td>
                                </tr>
                            </c:if>

                        </table>

                    </div>
                </fieldset>


            </div>
        </div>
    </c:if>

    <c:if test="${form2}">
        <div class="subtitle">
            <div class="subtitle">
                <fieldset class="aras1">

                    <legend>
                        Makluman Permohonan Pengambilan Tanah Secara Pendudukan atau Penggunaan Sementara - Pemulihan Tanah
                    </legend>
                    <div class="content" align="left">
                        <table align="left"  width="100%">
                            <c:if test="${lulus}">
                                <tr>
                                    <td align="left">Keputusan adalah Lulus</td>
                                </tr>
                            </c:if>

                            <c:if test="${tidak_Lengkap}">
                                <tr>
                                    <td align="left">Keputusan adalah tidak lengkap</td>
                                </tr>
                            </c:if>
                        </table>

                    </div>
                </fieldset>


            </div>
        </div>
    </c:if>
</s:form>