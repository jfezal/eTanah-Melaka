<%--
    Document   : testing1
    Created on : Dec 16, 2009, 10:42:01 AM
    Author     : wan.fairul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">

   function validateNumber(elmnt,content) {
          //if it is character, then remove it..
          if (isNaN(content)) {
              elmnt.value = removeNonNumeric(content);
              return;
          }
      }

      function removeNonNumeric( strString )
      {
          var strValidCharacters = "1234567890";
          var strReturn = "";
          var strBuffer = "";
          var intIndex = 0;
          // Loop through the string
          for( intIndex = 0; intIndex < strString.length; intIndex++ )
          {
              strBuffer = strString.substr( intIndex, 1 );
              // Is this a number
              if( strValidCharacters.indexOf( strBuffer ) > -1 )
              {
                  strReturn += strBuffer;
              }
          }
          return strReturn;
      }

   
    function save(event, f){
        var bilanganPetakAksesori = document.form1.bilanganPetakAksesori.value;
        var lokasiPetakAksesori = document.form1.lokasiPetakAksesori.value;

        if ((bilanganPetakAksesori == ""))
        {
            alert('Sila masukkan bilangan petak Aksesori');
            document.form1.bilanganPetakAksesori.focus();
        }
        else  if ((lokasiPetakAksesori == ""))
        {
            alert('Sila masukkan lokasi petak Aksesori');
            document.form1.lokasiPetakAksesori.focus();
        }
     
        else
        {
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
                $('#page_div',opener.document).html(data);
                self.close();
            },'html');
        }
    }
</script>

<div class="subtitle">
    <s:form  name="form1" beanclass="etanah.view.strata.MaintainBangunanActionBean" >
        <s:messages/>
        <s:errors/>
        <s:hidden name="idBangunan" value="${actionBean.idBangunan}"/>
        <s:hidden name="idTingkat" value="${actionBean.idTingkat}"/>
        <s:hidden name="idPetak" value="${actionBean.idPetak}"/>
        <fieldset class="aras1">

            <legend>Tambah Petak Aksesori</legend>
            <br>
            <p>
                <label>Jumlah Petak Aksesori:</label><s:text name="bilanganPetakAksesori" onkeyup="validateNumber(this,this.value);"/>
            </p>
            <p>
                <label>Jenis Kegunaan:</label> <s:select name="kodkegunaan.kod" value="kGunaPetakAksesoriL">
                    <s:option value="0">Sila Pilih</s:option>
                    <s:options-collection collection="${actionBean.kGunaPetakAksesoriL}" label="nama" value="kod" />
                </s:select>
            </p>
            <p>
                <label>Lokasi Petak Aksesori:</label> <s:select name="lokasiPetakAksesori">
                  <s:option value="0">Sila Pilih</s:option>
                   <s:option value="Luar Bangunan">Luar Bangunan</s:option>
                    <s:option value="Dalam Bangunan">Dalam Bangunan</s:option>
                 </s:select>
                  
            </p>
            <br>
            <label>&nbsp;</label>
            <s:button name="tambahPetakAksesori" id="simpan" value="Simpan" class="btn" onclick="save(this.name, this.form);"/>
            <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            <br>

        </fieldset>
    </div>

    <br>
</s:form>
