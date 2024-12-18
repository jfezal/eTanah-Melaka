<%--
    Document   : testing1
    Created on : Dec 16, 2009, 10:42:01 AM
    Author     : wan.fairul
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
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
  
   

    function removeBngn(val){
        
        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/strata/urusan_pkkr?deleteBngn&idBangunan='+val;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
      
    }

       


    function save(event, f){
        <%--var nama = document.form1.nama.value;
        var bil_tgkt = document.form1.bil_tgkt.value;
        var bil_ptk = document.form1.bil_ptk.value;
        var bil_blk = document.form1.bil_blk.value;


        if ((nama == ""))
        {
            alert('Sila masukkan Nama Bangunan ');
            document.form1.nama.focus();
        }
        else if ((bil_tgkt == ""))
        {
            alert('Sila masukkan bilangan tingkat ');
            document.form1.bil_tgkt.focus();
        }
        else if ((bil_ptk == ""))
        {
            alert('Sila masukkan bilangan petak ');
            document.form1.bil_ptk.focus();
        }
        else if ((bil_blk == ""))
        {
            alert('Sila masukkan bilangan bilik ');
            document.form1.bil_blk.focus();
        }
       
        else
        {--%>
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
                $('#page_div').html(data);

            },'html');
        }
    <%--}--%>



</script>

<s:form  name="form1" beanclass="etanah.view.strata.MaintainBngnKosRendahActionBean">
    <s:messages/>
    <s:errors/>

    <div class="subtitle">

        <fieldset class="aras1">
            <br>
            <a href="#bottom"><s:button name="Tambah" value="Tambah Bangunan" class="longbtn"/></a>
            <br>
            <br>
            <c:if test="${fn:length(actionBean.pBangunanL) > 0}">
                <display:table class="tablecloth" name="${actionBean.pBangunanL}" cellpadding="0" cellspacing="0" id="line">
                    <display:column title="Bil" sortable="true" style="vertical-align:baseline">${line_rowNum}</display:column>
                    <display:column property="nama" title="Nama Bangunan" />
                    <display:column property="bilanganTingkat" title="Bilangan Tingkat" />
                    <display:column property="bilanganPetak" title="Bilangan Petak" />
                    <display:column property="bilanganBilik" title="Bilangan Bilik"/>
                    <display:column title="Hapus">
                        <div align="center">
                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                 onclick="removeBngn('${line.idBangunan}')" onmouseover="this.style.cursor='pointer';" >
                        </div>
                    </display:column>
                </display:table>
            </c:if>
            <br>
        </fieldset>

    </div>
    <br>
    <div class="subtitle">

        <fieldset class="aras1">
            <a name="bottom">
                <legend>Tambah Bangunan</legend>
                <p>
                    <label>Bangunan :</label><s:text name="bangunan.nama" id="nama"/><em>*</em>
                </p>
                <p>
                    <label>Nama Lain :</label><s:text name="bangunan.namaLain" />
                </p>
                <p>
                    <label>Jumlah Tingkat :</label><s:text name="bangunan.bilanganTingkat" id="bil_tgkt" onkeyup="validateNumber(this,this.value);"/><em>*</em>
                </p>
                <p>
                    <label>Bilangan Petak :</label><s:text name="bangunan.bilanganPetak" id="bil_ptk" onkeyup="validateNumber(this,this.value);"/><em>*</em>
                </p>
                <p>
                    <label>Bilangan Bilik :</label><s:text name="bangunan.bilanganBilik" id="bil_blk" onkeyup="validateNumber(this,this.value);"/><em>*</em>
                </p>
                <p>
                    <label>Kegunaan :</label><s:checkbox name="bangunan.untukKediaman" value="Y" /> Kediaman
                </p>
                <p>
                    <label>&nbsp;</label><s:checkbox name="bangunan.untukPejabat" value="Y" /> Pejabat
                </p>
                <p>
                    <label>&nbsp;</label><s:checkbox name="bangunan.untukPerniagaan" value="Y" /> Perniagaan
                </p>
                <p>
                    <label>(Jika lain kegunaan)</label><s:text name="bangunan.untukKegunaanLain" />
                </p>
                <p>
                    <label>Bangunan Sementara :</label><s:radio name="bangunan.kekal" value="Y"></s:radio> Kekal
                    <s:radio name="bangunan.kekal" value="N"></s:radio> Sementara
                </p>

                <br>
                <label>&nbsp;</label>
                <s:button name="tambahBangunan" value="Simpan" class="btn"  onclick="save(this.name, this.form);"/>

                <br>
                <br>
                </fieldset>
                </div>
                <br>
            </s:form>