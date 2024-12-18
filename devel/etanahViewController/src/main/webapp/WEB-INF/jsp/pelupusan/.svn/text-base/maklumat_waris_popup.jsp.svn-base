<%-- 
    Document   : maklumat_waris_popup
    Created on : Jan 6, 2012, 1:14:14 PM
    Author     : Srinivas
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<head>


<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="/etanah/pub/js/ageCalculator.js"></script>
<script language="javascript" type="text/javascript"
              src="${pageContext.request.contextPath}/src/main/webapp/pub/scripts/datepicker.js"></script>

<script language="javascript" type="text/javascript"
              src="${pageContext.request.contextPath}/src/main/webapp/pub/js/datetimepicker.js"></script>

<script language="javascript" type="text/javascript"
				src="${pageContext.request.contextPath}/JavaScript/datetimepicker.js"></script>

<script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>

<script type="text/javascript">
     $(document).ready(function() {
         maximizeWindow();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});

  });

function reset1(){
    <%--alert("reset");--%>
    document.getElementById("namaPenyedia").value ="";
    document.getElementById("noRujukan").value ="";
    document.getElementById("ulasan").value ="";
    document.getElementById("syorJabatan1").value ="";
}

function validation(){
     if($("#syorJabatan1").val() == "0"){
            alert('Sila Pilih " SyorJabatan " .');
            $("#syorJabatan1").focus();
            return true;
    }
         if($("#ulasan").val() == ""){
            alert('Sila masukkan " ulasan " terlebih dahulu.');
            $("#ulasan").focus();
            return true;
    }
    return false;
}

function saveJabatanTeknikal(event, f){


        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        window.document.forms[0].action = url;
        window.document.forms[0].submit();
    }
    function resetUlasan(event, f){
       var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div').html(data);

        },'html');

    }

    function changeUlasan(f, value){
        var q = $(f).formSerialize();
        var url = f.action + '?changeUlasan&ulasanValue='+value ;
         window.document.forms[0].action = url;
        window.document.forms[0].submit();
        if(value == 'A'){
        $('#penyedia').hide() ;
        }
        else {
             $('#penyedia').show() ;
        }
    }


     jQuery.fn.ForceNumericOnly = function() {
       return this.each(function()     {
           $(this).keydown(function(e)         {
               var key = e.charCode || e.keyCode || 0;             // allow backspace, tab, delete, arrows, numbers and keypad numbers ONLY
                return (
                key == 8 ||
                    key == 9 ||
                    key == 46 ||
                    (key >= 37 && key <= 40) ||
                    (key >= 48 && key <= 57) ||
                    (key >= 96 && key <= 105));
            });
        });
    };
      jQuery('.numbersOnly').ForceNumericOnly();

      
      function closeWindow123(event, f){

        //self.close();

         var q = $(f).formSerialize();
         var url = f.action + '?' + event;
          $.post(url,q,
        function(data){
           $('#page_div',opener.document).html(data);
            self.close();
        },'html');

    }
    function  numeralsOnly(evt) {
                evt = (evt) ? evt : event;
                var charCode = (evt.charCode) ? evt.charCode : ((evt.keyCode) ? evt.keyCode :((evt.which) ? evt.which : 0));
                if (charCode > 31 && (charCode < 48 || charCode > 57)) {
                    return false;
                }else{
                    return true;
                }
            }
</script>

</head>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<%--<s:form beanclass="etanah.view.stripes.pelupusan.SuratUlasanTeknikalActionBean">--%>
<s:form beanclass="etanah.view.stripes.pelupusan.maklumatWarisActionBean">
     <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                 
            </legend>
             <c:if test="${actionBean.edit eq '1'}" >
             <h2 style="color:green">Maklumat telah berjaya disimpan.</h2>
             </c:if>
             
             <c:if test="${actionBean.edit eq '3'}" >
             <h2 style="color:green">Maklumat telah berjaya dikemas kini</h2>
             </c:if>
             <s:hidden  name="permohonanPihak.idPermohonanPihak" id="idPermohonanPihak" value=""/>
            <table>
                <tr>
                    <td><label>Nama :</label>
                    <td> <s:text name="pihak.nama" id="nama" maxlength="250"/>
                    </td>

                </tr>
                 <tr>
                    <td><label>Jenis Waris :</label>
                    <td><s:select name="pihak.kodJantina" id="kodJantina">
                        <s:option value="1">Waris Lelaki</s:option>
                        <s:option value="2">Waris Perempuan</s:option>
                        <s:option value="0">Waris Kadim</s:option>
                        </s:select></td>

                </tr>
                <tr>
                    <td><label>Umur :</label>
                    <td><s:text name="permohonanPihak.umur" id="umur"  class="numbersOnly"   onkeypress="return numeralsOnly(event)"   /></td>

                </tr>
                <tr>
                    <td  valign="top"><label>Alamat :</label></td>
                    <td>
                        <s:text name="pihak.alamat1" id="alamat1" maxlength="40"/><br>
                        <s:text name="pihak.alamat2" id="alamat2" maxlength="40"/><br>
                        <s:text name="pihak.alamat3" id="alamat3" maxlength="40"/>
                    </td>
                </tr>
                 <tr>

                     <td></td>
                     <td align="right"><s:button name="simpanMaklumatWaris" id="save" value="Simpan" class="btn" onclick="saveJabatanTeknikal(this.name, this.form);"/>  </td>
                    <td> <s:button name="closeWindow" value="Tutup" class="btn" onclick="closeWindow123(this.name, this.form)" /> </td>
                </tr>
            </table>
       </fieldset>
    </div>

</s:form>