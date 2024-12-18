<%--
    Document   : permohonan_penarikanbalik1
    Created on : Apr 14, 2010, 5:06:28 PM
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
    function validateForm(){

        if(document.form2.idSblm.value=="")
        {
            alert("Sila masukkan Id Permohonan Terdahulu");
            return false;
        }
    <%-- if(document.form2.namaProjek.value=="")
     {
         alert("Sila masukkan Nama Projek Terdahulu");
         return false;
     }--%>
             return true;
         }

         function search(){
             // alert("search:"+index);
             var url = '${pageContext.request.contextPath}/pengambilan/penarikanBalik?IdMohonOSebabPopup=';
             window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
         }

      
         function test(){
             document.getElementById("idSblm").value ="";
             document.getElementById("namaProjek").value ="";
         }

         function hideMaklumatPemohon(){
             $('#MaklumatPemohon').hide();
             $('#errors').hide();
         }

         function showReport(){
             $.blockUI({
                 message: $('#displayBox'),
                 css: {
                     top:  ($(window).height() - 50) /2 + 'px',
                     left: ($(window).width() - 50) /2 + 'px',
                     width: '50px'
                 }
             });
             var url = '${pageContext.request.contextPath}/pengambilan/penarikanBalik?genReport';
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

         var message="Tidak boleh copy dan paste data lakukan carian dahulu!";
         function clickIE4(){ if (event.button==2){ alert(message); return false; } }
         function clickNS4(e){ if (document.layers||document.getElementById&&!document.all)
             { if (e.which==2||e.which==3){ alert(message); return false; } } }
         if (document.layers){ document.captureEvents(Event.MOUSEDOWN); document.onmousedown=clickNS4; }
         else if (document.all&&!document.getElementById){ document.onmousedown=clickIE4; }
         document.oncontextmenu=new Function("alert(message);return false")
</script>

<div class="subtitle">
    <s:form beanclass="etanah.view.stripes.pengambilan.permohonan_penarikanbalikActionBean" name="form2">
        <s:messages/>
        <div id="errors">
            <s:errors/>
        </div>
        <s:hidden id="hakmilikList" name="hakmilikList" />
        <fieldset class="aras1">
            <legend>Maklumat Permohonan Terdahulu</legend><br />
            <div class="content" align="center">
                <table border="0" cellspacing="5" width="80%">
                    <tr>
                        <td align="left" width="30%" valign="top"><label style="clear: right"  ><font color="red">*</font>Id permohonan yang terdahulu :</label></td>
                        <td><s:text name="idSblm" size="30" id="idSblm" readonly="true" onkeyup="this.value=this.value.toUpperCase();" value="${actionBean.idPermohonan}"/>
                            <s:button class="btn" value="Cari" name="add" onclick="javascript:search()"/>
                        </td>
                    </tr>

                    <tr>
                        <td align="right"><label>Tujuan Permohonan :</label></td>
                        <td><s:textarea name="namaProjek" rows="3" cols="40" id="namaProjek" readonly="true" onkeyup="this.value=this.value.toUpperCase();"/>
                        <td></td>
                    </tr>

                    <tr>
                        <td align="right"><s:button name="checkSebelum" value="Semak" class="btn" onclick="if(validateForm())doSubmit(this.form, this.name, 'page_div');"/></td>
                        <td><s:button name="reset" value="Isi Semula" class="btn" onclick="test();"/></td>
                    </tr>
                </table>

                <c:if test="${Borang_K}">
                    <p>
                        <label style="text-align:left; color:black; width:100%; padding: 1em 5em"dir="rtl" >Borang K&nbsp telah dikeluarkan dan didaftarkan klik pada jana dokumen untuk untuk surat penolakan penarikan balik
                            <br /><center><font style="font-family: Arial; font-size: x-small; font-weight: bold;"><s:button name="genReport" id="report" value="Jana Dokumen" class="longbtn" onclick="showReport();"/></font></center></label>
                        <br />
                    </p>

                </c:if>
            </div>
        </fieldset> <br />
        <div id="MaklumatPemohon">
            <c:if test="${Maklumat_Pemohon}">
                <fieldset class="aras1">
                    <legend>Maklumat Pemohon</legend>
                    <div class="content" align="center">
                        <table border="0" width="80%"  cellspacing="5">
                            <tr><td align="left" width="30%" valign="top"><label style="clear: right"  >Pemohon :</label></td>
                                <td>
                                    <font style="text-transform: uppercase">
                                        <c:set var="count" value="1"/>
                                        <c:forEach items="${actionBean.pemohonList}" var="list">
                                            <c:out value="${count}" />)
                                            <c:out value="${list.pihak.nama}"/><br/>
                                            <c:set var="count" value="${count+1}"/>
                                        </c:forEach>
                                    </font>
                                </td></tr>&nbsp;

                            <tr><td align="left"><label >Urusan :</label></td>
                                <td><font style="text-transform: uppercase">${actionBean.kodUrusanSblm.nama}</font></td></tr>&nbsp;
                            <tr><td align="left"><label >Nama Projek :</label></td>
                                <td><font style="text-transform: uppercase">${actionBean.namaProjek}</font></td></tr>&nbsp;
                            <c:if test="${actionBean.kodUrusan eq 'PHL'}">
                            <tr><td align="left"><label>No Warta Borang D :</label></td>
                                <td>
                                     <c:if test="${actionBean.permohonanRujukanLuar.item eq null}">
                                         <font style="text-transform: uppercase">Borang D belum diwartakan</font>
                                     </c:if><br/>
                                     <c:if test="${actionBean.permohonanRujukanLuar.item ne null}">
                                         <font style="text-transform: uppercase">${actionBean.permohonanRujukanLuar.item}</font>
                                     </c:if>
                                    </td></tr>&nbsp;
                            
                            <tr><td align="left"><label>Tarikh Warta Borang D :</label></td>
                                 
                                <td><c:if test="${actionBean.tarikhLulus eq null}">
                                         <font style="text-transform: uppercase">Tarikh Warta belum dikeluarkan</font>
                                     </c:if><br/>
                                     <c:if test="${actionBean.tarikhLulus ne null}">
                                         <font style="text-transform: uppercase">${actionBean.tarikhLulus}</font>
                                     </c:if></td></tr>&nbsp;
                            <tr>
                                <td align="left"><label>Tarikh Bicara Borang E :</label></td>
                                <td> 
                                    <c:if test="${actionBean.size eq 0}">
                                         <font style="text-transform: uppercase">Borang E belum dikeluarkan</font>
                                     </c:if><br/>
                                     <c:if test="${actionBean.size ne 0}">
                                         <c:set var="count1" value="1"/>
                                    <c:forEach items="${actionBean.tarikhBicaraList}" var="result">
                                        <c:out value="${count1}" />)
                                        <c:out value="${result}"/><br />
                                        <c:set var="count1" value="${count1+1}"/>
                                    </c:forEach><br/>
                                     </c:if></td></tr>&nbsp;
                            </tr>
                            <tr>
                                <td align="left"><label>Bayaran pampasan telah dibuat atau belum :</label></td>
                                <td>
                                    <c:if test="${actionBean.dokumenK eq null}"><font style="text-transform: uppercase">Bayaran pampasan belum dibuat</font></c:if><br />
                                </td>
                                <td>
                                    <c:if test="${actionBean.dokumenK ne null}"><font style="text-transform: uppercase">Bayaran pampasan telah dibuat</font></c:if><br />
                                </td>
                            </tr>
                            <tr>
                                <td align="left"><label>Borang K telah/belum didaftarkan :</label></td>
                                <td>
                                    <c:if test="${actionBean.dokumenK eq null}">Belum didaftarkan</c:if><br />
                                </td>
                                <td>
                                    <c:if test="${actionBean.dokumenK ne null}"><font style="text-transform: uppercase">Telah Didaftarkan</font></c:if><br />
                                </td>
                            </tr>
                            </c:if>
                        </table>
                    </div>
                </fieldset>
            </c:if>
        </div>
    </div>
</s:form>
