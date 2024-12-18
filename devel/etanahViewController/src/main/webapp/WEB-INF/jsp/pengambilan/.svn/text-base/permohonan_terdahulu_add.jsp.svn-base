<%-- 
    Document   : permohonan_terdahulu_add
    Created on : Jun 23, 2010, 4:55:21 PM
    Author     : Rajesh
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
        if($("#idpermohonansebelum").val() == ""){
            alert('Sila masukkan " ID Permohonan/NoFail " terlebih dahulu.');
            $("#idpermohonansebelum").focus();
            return true;
        }

    }

        function save1(event, f){
            if(validation()){

            }
            else{
                var q = $(f).formSerialize();
                var url = f.action + '?' + event;
                $.post(url,q,
                function(data){
                    $('#page_div',opener.document).html(data);
                    self.opener.refreshPageTanahRizab();
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

<div class="subtitle">

         <s:form beanclass="etanah.view.stripes.pengambilan.MaklumatTambahanActionBean" >
             <fieldset class="aras1">
            <legend>
                 Permohonan Terdahulu
            </legend>
            <p>
                <label for="idpermohonansebelum">ID Permohonan/NoFail :</label>
                <s:text name="idPermohonanSebelum" size="20" id="idpermohonansebelum"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:button name="simpanPermohonanTerdahulu" value="Simpan" class="btn" onclick="save1(this.name, this.form);" />
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>
        </fieldset>


         </s:form>
</div>