<%--
    Document   : permohonan_terdahulu_edit
    Created on : July 07, 2010, 4:14:55 PM
    Author     : Rajesh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

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
$('.alphanumeric').alphanumeric();
$(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
});
function save(){
        self.opener.refreshPageTanahRizab();
        self.close();}

function validation() {
         if($("#noFail").val() == ""){
            alert('Sila pilih " Fail " terlebih dahulu.');
            $("#noFail").focus();
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

       <%-- function validationForm(){
             self.opener.refreshRizab();
             self.close();


        }--%>



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


         <s:form beanclass="etanah.view.stripes.pengambilan.MaklumatTambahanActionBean" >
             <s:text name="idMohonManual" value="${actionBean.permohonanManual.idMohonManual}" />

             <fieldset class="aras1">
            <legend>
                Permohonan Terdahulu
            </legend>
            <p>
                <label for="noFail">NoFail :</label>
                <s:text name="noFail" size="20" id="noFail"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:button name="editPermohonanTerdahulu" value="Kemaskini" class="btn" onclick="save1(this.name, this.form);" />
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>
        </fieldset>

         </s:form>
</div>
