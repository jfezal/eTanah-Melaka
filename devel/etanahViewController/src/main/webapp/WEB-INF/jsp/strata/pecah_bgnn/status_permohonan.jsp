<%--
    Document   : testing1
    Created on : Dec 16, 2009, 10:42:01 AM
    Author     : wan.fairul
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">

 $(document).ready(function() {

      $('#semak').hide();


  });

 function change(value){

        if(value == "ya")
           $('#semak').show();


        if(value == "tidak")
           $('#semak').hide();

 }
 </script>

PENYEDIAAN KERTAS PERAKUAN
<s:form beanclass="etanah.view.strata.urusan">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Status Permohonan</legend>
            <p>
                <label>Permohonan Lengkap : </label>
              <s:radio name="mohonlengkap" value="ya" onchange="javaScript:change(this.value)"></s:radio>Ya
              <s:radio name="mohonlengkap" value="tidak" onchange="javaScript:change(this.value)"></s:radio>Tidak

            </p>

        </fieldset>
   </div>

 <div id="semak" class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Semakan</legend>
            <p>
                Saya telah menyemak dan meneliti permohonan ini dan mendapati ianya adalah teratur dan telah memenuhi kehendak-kehendak Seksyen 29 Akta Hakmilik Strata 1985 dan diperlakukan untuk
                

            </p>

        </fieldset>
   </div>
 
               <p> <label>&nbsp;</label>
              <a href=""<s:submit name="hantar" value="Hantar" class="btn"/></a>
                <s:submit name="Kembali" value="Kembali" class="btn"/>
                        </p>


    </s:form>