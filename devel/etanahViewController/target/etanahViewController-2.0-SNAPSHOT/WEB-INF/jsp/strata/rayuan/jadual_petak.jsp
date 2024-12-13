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

      $('#menara_bgnn').hide();
        $('#tgkt').hide();

  });
  
 function changebgnn(value){

        if(value == "ya")
            {
            $('#menara_bgnn').show();
            $('#tgkt').hide();
               }
        if(value == "tidak")
            {
            $('#tgkt').show();
            $('#menara_bgnn').hide();
            }
 }




</script>

KEMASUKAN MAKLUMAT PERMOHONAN PECAH BAHAGI BANGUNAN (SEKSYEN 10 Akta Hakmilik Strata)
<s:form beanclass="etanah.view.strata.rayuan">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Jadual Petak-petak</legend>
            <p>
                <label>ID Bangunan :</label>

            </p><br>
                    <p>
                        <label>Nama Lain Bagi Bangunan :</label> <s:text name="nama_lain" ></s:text>

            </p>
            <p>
                <label>*Kegunaan Bangunan :</label><s:select name="kegunaan" ></s:select>

            </p>
            <p>
                <label>*Menara :</label><s:radio name="menara" value="ya" onchange="javaScript:changebgnn(this.value)"></s:radio> Ya
                <s:radio name="menara" value="tidak" onchange="javaScript:changebgnn(this.value)"></s:radio> Tidak

            </p>
            <p></p><br>
       </fieldset>
    </div>
<br>
<div id="menara_bgnn" class="subtitle">
        <fieldset class="aras1">
            <legend>Menara Bangunan</legend>
            <p>
                <label>*Bilangan Menara Bagi Bangunan :</label><s:text name="bil_menara" ></s:text>

            </p>
                    <p>
                 <label>*Bilangan Tingkat Bagi Bangunan :</label> <s:text name="bil_tgkt" ></s:text>

            </p>
            <br>
            <label>&nbsp;</label>
              <a href="rayuan?showForm7"<s:submit name="Terus" value="Terus" class="btn"/></a>
                 <a href="rayuan?showForm10"<s:submit name="Keluar" value="Keluar" class="btn"/></a>

            </p>
       </fieldset>
    </div>

                 <div id="tgkt" class="subtitle">
        <fieldset class="aras1">
            <legend>Tingkat Dalam Bangunan Bangunan</legend>
            <p>
                <label>*Bilangan Tingkat Bangunan :</label><s:text name="bil_tgkt" ></s:text>

            </p>
                 <br>
            <label>&nbsp;</label>
              <a href="rayuan?showForm8"<s:submit name="Terus" value="Terus" class="btn"/></a>
                 <a href="rayuan?showForm10"<s:submit name="Keluar" value="Keluar" class="btn"/></a>

            </p>
       </fieldset>
    </div>
    </s:form>