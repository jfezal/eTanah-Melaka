<%-- 
    Document   : form_permohonanRujukanLuar
    Created on : 29 Oktober 2013, 4:31:54 PM
    Author     : tstr
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
  $(document).ready(function() {
  });

</script>
<s:form beanclass="etanah.view.daftar.MohonRujLuar">
  <s:messages/>
  <s:errors/>
  <s:hidden name="permohonanRujukanLuar.idRujukan"/>
  
  <div class="subtitle">
    <fieldset class="aras1">
      <legend>Maklumat Urusniaga</legend>
      <br>
      <p>
        <label for="Mahkamah">No Rujukan Fail :</label>
        <s:text name="noFail" onkeyup="this.value=this.value.toUpperCase();" size="35"/>
      </p>
      <br>
      <p>
        <label>&nbsp;</label>
        <s:button name="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
      </p>
      <br>
    </fieldset>
  </div>
</s:form>

