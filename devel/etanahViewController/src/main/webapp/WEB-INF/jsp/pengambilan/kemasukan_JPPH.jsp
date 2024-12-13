<%-- 
    Document   : kemasukan_warta
    Created on : 18-Jan-2010, 18:39:29
    Author     : nordiyana
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%--<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>--%>
<script type="text/javascript">

function validation() {

    if($("#datepicker2").val() == ""){
            alert('Sila pilih " tarikh " terlebih dahulu.');
            $("#datepicker").focus();
            return false;
        }

        if($("#nowarta").val() == ""){
            alert('Sila masukkan " No Warta " terlebih dahulu.');
            $("#nowarta").focus();
            return false;
        }
        return true;
    }


          $(document).ready(function() {
        $("#close").click( function(){
            setTimeout(function(){
                self.close();
            }, 100);
        });
    });

function test(){
           document.getElementById("datepicker").value ="";
           document.getElementById("nowarta").value ="";
       }


function showUlasan() {
     $('#adakesilapan').show();
}
function hideUlasan() {

    $('#adakesilapan').hide();
    <%--$('#adakesilapan').val("");--%>

}
function removeWarta(idRujukan)
{
    if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pengambilan/terima_warta?deleteWarta&idRujukan='
+idRujukan;
            $.get(url,
            function(data){
            $('#page_div').html(data);
            },'html');
        }
}
  </script>
<s:messages/>
<s:errors/>
<s:form beanclass="etanah.view.stripes.pengambilan.wartaNewActionBean">

             <div class="content">
                <fieldset class="aras1">
                    Sila Jana Dokumen.
                    
                </fieldset>
     </div>
     <div class="subtitle displaytag">
        <fieldset class="aras1" id="locate">
            <legend>
              
            </legend>
            <p align="left"><label>Sila Sertakan bersama surat ini :-</label>
                <BR><BR>
                 i)   Permohonan Pengambilan Tanah daripada Pemohon <BR>
                 ii)  Salinan Sijil Carian Rasmi bagi tanah yang terlibat <br>
                iii) Pelan Pengambilan Tanah
            <br>
            </p>
        </fieldset>
     </div>
     
   </s:form>
