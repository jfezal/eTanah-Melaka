<%-- 
    Document   : notasiasatan_popup
    Created on : 01-Jun-2011, 16:07:46
    Author     : nordiyana
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>


<script type="text/javascript">


$(document).ready( function(){
$(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
});
function save(){
        self.opener.refreshPageTanahRizab();
        self.close();}

function validation() {
        if($("#idhakmilik").val() == ""){
            alert('Sila masukkan " No H/M " terlebih dahulu.');
            $("#idhakmilik").focus();
            return true;
        }

    }

        function savePenjaga(event, f){
            if(validation()){

            }
            else{
                var q = $(f).formSerialize();
                var url = f.action + '?' + event;
                $.post(url,q,
                function(data){
                    $('#page_div',opener.document).html(data);
                    <%--self.opener.refreshPageHakmilik();--%>
                    self.close();
                },'html');
            }
        }
          $(document).ready(function() {
        $("#close").click( function(){
            setTimeout(function(){
                self.close();
            }, 100);
        });
    });



  </script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<div class="subtitle">

         <s:form beanclass="etanah.view.stripes.pengambilan.NotaSiasatan1ActionBean" >
             <fieldset class="aras1">
        <legend align="center">
                 PBT Daftar - Maklumat Orang Hadir
            </legend>
            <div  align="center">
                <br>
                <br>

            <table align="center">

                    <tr>
                        <td width="30%"><b>Nama </b></td>
                        <td><b>:</b></td>
                        <td><s:text name="perbicaraanKehadiran.nama" size="40"/></td>
                    </tr>
                     <tr>
                        <td width="30%"><b>No. Pengenalan</b></td>
                        <td><b>:</b></td>
                        <td><s:text name="perbicaraanKehadiran.noPengenalan" id="kp" size="40"/>&nbsp;<em style="font-style:normal">(cth : 800620125177)</em></td>
                    </tr>
                    <tr>
                        <td ><b>Jawatan</b></td>
                         <td><b>:</b></td>
                         <td><s:text name="perbicaraanKehadiran.jawatan" size="40" id="wakil"/></td>

                    </tr>
                    <tr>
                        <td ><b>Ulasan/Catatan</b></td>
                         <td><b>:</b></td>
                         <td><s:textarea name="perbicaraanKehadiran.penilaiUlasan" rows="4" cols="35" id="ulasan"/>&nbsp;
                             </td>
                   <%-- <tr>
                        <td ><b>Pihak Berkepentingan Tidak berdaftar?</b></td>
                         <td><b>:</b></td>
                         <td><s:radio name="perbicaraanKehadiran.catatan" value="1"/>&nbsp;Ya
                             <s:radio name="perbicaraanKehadiran.catatan" value="0"/>&nbsp;Tidak</td>

                    </tr>
                    

                    </tr>--%>
                    
                </table>
                         <br>
                    <table align="center">
                    <tr>
                        <td>&nbsp;</td>
                         <td><s:button name="simpanKehadiran" id="simpan" value="Simpan" class="btn" onclick="savePenjaga(this.name, this.form);"/></td>
                         <td><s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/></td>

                    </tr>
                    </table>
</div>


























            
        </fieldset>



         </s:form>
</div>