<%-- 
    Document   : papar_pihak_waris
    Created on : Jan 1, 2015, 12:29:40 AM
    Author     : zairul
--%>

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
<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript">
     function cariWaris(id) {      
         var idhp= id;
            var url = "${pageContext.request.contextPath}/daftar/pembetulan_pihak?cariWaris&idHakmilikPihakBerkepentingan=" + idhp;
            window.open(url, "eTanah", "status=1,toolbar=0,location=1,menubar=0,scrollbars=yes,resizable=1,width=900,height=600");
        }
        
      function validateForm(){
        var nama = document.getElementById('nama');
        var jeniskp = document.getElementById('jeniskp');
        var noP = document.getElementById('noP');
        var penyebut = document.getElementById('penyebut');
        var pembilang = document.getElementById('pembilang');

        if((nama.value == '')||(jeniskp.value=='')||(noP.value=='')||(pembilang.value=='')||(penyebut.value=='')){
            alert('Sila pastikan semua maklumat diisi.');
                return false;            
        }
        else{return true;}
    }  
        
</script>

<s:form beanclass="etanah.view.stripes.nota.pembetulanPihakActionBean" name="form1">
     <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
 <fieldset class="aras1">

              <legend>Kemasukan Maklumat Waris</legend>
              <p>
                <label for="nama">Nama :</label><s:text name="nama" id="nama" size="40" onkeyup="this.value = this.value.toUpperCase();"/>
              </p>
              <p>
                  <label>Kod Pengenalan :</label>
                  <s:select name="jeniskp" id="jeniskp" value="${jeniskp}">
                      <s:option value="">Sila Pilih</s:option>
                      <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                  </s:select>
              </p>  
              <p>
                  <label for="noP">No Pengenalan :</label><s:text name="noP" id="noP" size="40" onkeyup="this.value = this.value.toUpperCase();"/>
              </p>
              <p>
                  <label for="Syer">Syer :</label><s:text name="pembilang" id="pembilang" size="1"/>/<s:text name="penyebut" id="penyebut" size="1"/>
              </p>              
              <s:hidden name="idHP"  value="${actionBean.idHakmilikPihak}" />
              <p>
                <label>&nbsp;</label>
                <s:submit name="simpanWaris" value="Simpan" id="" class="btn" onclick="return validateForm();"/>
                <s:button name="btnpopupPihak"  id="btnpopupPihak"  value="kembali" class="btn" onclick="cariWaris('${actionBean.hakmilikPihak.idHakmilikPihakBerkepentingan}')" />
                    
              </p>
              <br>
            </fieldset>
</s:form>
