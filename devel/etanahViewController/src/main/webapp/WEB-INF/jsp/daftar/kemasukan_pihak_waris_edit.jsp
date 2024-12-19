
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
 
        
</script>

<s:form beanclass="etanah.view.stripes.nota.pembetulanPihakActionBean" name="form1">
     <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
 <fieldset class="aras1">

              <legend>Edit Maklumat Waris</legend>
              <p>
                <label for="nama">Nama :</label><s:text name="nama_Waris" id="nama_Waris" size="40" onkeyup="this.value = this.value.toUpperCase();"/>
              </p>
              <p>
                  <label>Kod Pengenalan :</label>
                  <s:select name="jeniskp_Waris" id="jeniskp_Waris" value="${jeniskp_Waris}">
                      <s:option value="">Sila Pilih</s:option>
                      <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                  </s:select>
              </p>  
              <p>
                  <label for="noP">No Pengenalan :</label><s:text name="noP_Waris" id="noP_Waris" size="40" onkeyup="this.value = this.value.toUpperCase();"/>
              </p>
              <p>
                  <label for="Syer">Syer :</label><s:text name="pembilang_Waris" id="pembilang_Waris" size="1"/>/<s:text name="penyebut_Waris" id="penyebut_Waris" size="1"/>
              </p>              
              <s:hidden name="idHPW_Waris"  value="${actionBean.idHPW_Waris}" />
              <p>
                <label>&nbsp;</label>
                <s:submit name="simpanWarisEdit" value="Simpan" id="" class="btn"/>
                <s:button name="btnpopupPihak"  id="btnpopupPihak"  value="kembali" class="btn" onclick="cariWaris('${actionBean.hakmilikPihak.idHakmilikPihakBerkepentingan}')" />
                    
              </p>
              <br>
            </fieldset>
</s:form>
