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
        var bilanganPetak = document.form1.bilanganPetak.value;
        var luas = document.form1.luas.value;
        var syer = document.form1.syer.value;
        var kodkegunaan = document.form1.kodkegunaan.value;

        if ((bilanganPetak == ""))
        {
            alert('Sila masukkan bilangan petak ');
            document.form1.bilanganPetak.focus();
        }
        else if ((luas == ""))
        {
            alert('Sila masukkan luas petak ');
            document.form1.luas.focus();
        }
        else if ((syer == ""))
        {
            alert('Sila masukkan syer ');
            document.form1.syer.focus();
        }
         else if ((kodkegunaan == ""))
        {
            alert('Sila pilih jenis kegunaan ');
            document.form1.kodkegunaan.focus();
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
        <fieldset class="aras1">
            <legend>Tambah Petak</legend>
            <br>
            <p>
                <label>Bangunan :</label>${actionBean.namaBangunan}
            </p>
            <p>
                <label>Tingkat :</label>${actionBean.tingkat}<s:hidden name="idTingkat" value="${actionBean.idTingkat}"/>
            </p>
            <p>
                <label>Jumlah Petak :</label><s:text name="bilanganPetak" onkeyup="validateNumber(this,this.value);"/>
            </p>
            <p>
                <label>Luas m2:</label><s:text name="luas" onkeyup="validateNumber(this,this.value);"/>
            </p>
            <p>
                <label>Syer :</label><s:text name="syer" onkeyup="validateNumber(this,this.value);"/>
            </p>
            <p>
                <label>Jenis Kegunaan :</label>
                <s:select name="kegunaan.kod" value="kGunaPetakL" style="width:100px" id="kodkegunaan">
                    <s:option value="0">Sila Pilih</s:option>
                    <s:options-collection collection="${actionBean.kGunaPetakL}" label="nama" value="kod" />
                </s:select>
            </p>
            <br>
            <label>&nbsp;</label>
            <s:button name="tambahPetak" id="simpan" value="Tambah Petak" class="btn" onclick="save(this.name, this.form);"/>
            <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            <br>

        </fieldset>
    </div>

    <br>
</s:form>
