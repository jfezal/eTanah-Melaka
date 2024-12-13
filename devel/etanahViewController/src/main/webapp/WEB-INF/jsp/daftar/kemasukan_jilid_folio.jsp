<%-- 
    Document   : kemasukan_jilid_folio
    Created on : Mar 25, 2016, 2:45:48 PM
    Author     : Zairul
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">   
    <c:if test="${actionBean.flag}">
                $(document).ready(function(){
                    document.getElementById("allowed").checked = true;
                    $('#tekan').removeAttr("disabled");
                    document.getElementById('noJilid').readOnly = false;
         });
    </c:if>


        function checkFields() {  

            var jilid = document.getElementById("noJilid").value;
            var folder = document.getElementById("noFolder").value;
            
            if(jilid =='' && folder =='' ){
                alert("Sila pastikan semua maklumat diisi");
                return false;
            }
    
            return true;
        }
        
        function allow()
          {
              if($('input[name=allowed]').is(':checked')){     
                  $('#tekan').removeAttr("disabled");
                        document.getElementById('noJilid').readOnly = false;
                  
              }

              else if($('input[name=allowed]').is(':unchecked')){     
                  $("#tekan").attr("disabled", "true");
                      document.getElementById('noJilid').readOnly = true;
              }
          }

</script>
<s:form beanclass="etanah.view.daftar.MaklumatJilidFolio" >
    <s:messages/> 
    <div class="subtitle">
        <fieldset class="aras1">
            <div>
                <legend>Maklumat Kuasa Wakil</legend>
            </div>
            <p class="content" align="center">
                <font  color="red" style="font-family: Tahoma; font-size: 12px; ">
                    PERHATIAN : Maklumat di bawah perlu diisi sekiranya nombor SURAT KUASA WAKIL adalah nombor yang menggunakan nombor JILID FOLIO sahaja
                </font>
                <br><br>
                <s:checkbox name="allowed" id="allowed" onclick="allow();"/>
                <font color="black">Membenarkan kemasukkan maklumat</font>
            </p>       
            <br><br>        
            <p>
                <label>No Jilid dan Folio:</label><s:text name="noJilid" id="noJilid" size="20" readonly="readonly"/><font size ="1" color="red"> [Contoh:  Jil 33 Fol 68]</font>
            </p>           
            <br>
            <p>
                <label>&nbsp;</label>
                <s:button name="save" id="tekan" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')" disabled="true"/>                
            </p>
            </div>
        </fieldset>

    </div>
</s:form>


