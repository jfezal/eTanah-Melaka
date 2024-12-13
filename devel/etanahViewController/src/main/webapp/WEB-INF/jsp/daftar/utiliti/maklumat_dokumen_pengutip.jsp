<%-- 
    Document   : maklumat_dokumen_pengutip
    Created on : Dec 4, 2012, 4:50:55 PM
    Author     : ei
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<!--<!DOCTYPE html>-->
<script type="text/javascript">
  $(document).ready(function() {
    $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    $('#tikPenyerah').click(function() {
      if ($('#tikPenyerah').is(':checked')) {
        var nama = $('#pNama').val();
        var add1 = $('#pAlamat1').val();
        var add2 = $('#pAlamat2').val();
        var add3 = $('#pAlamat3').val();
        var bandar = $('#pAlamat4').val();
        var poskod = $('#pPoskod').val();
        var negeri = $('#pNegeri').val();
        var tel = $('#pTel').val();
        $('#kNama').val(nama);
        $('#kAlamat1').val(add1);
        $('#kAlamat2').val(add2);
        $('#kAlamat3').val(add3);
        $('#kBandar').val(bandar);
        $('#kPoskod').val(poskod);
        $('#kNegeri').val(negeri);
        $('#kNoTel').val(tel);
      } else if ($('#tikPenyerah').is(':unchecked')) {
        $('#kNama').val("");
        $('#kAlamat1').val("${actionBean.permohonanKutipanDokumen.alamat.alamat1}");
        $('#kAlamat2').val("");
        $('#kAlamat3').val("");
        $('#kBandar').val("");
        $('#kPoskod').val("");
        $('#kNegeri').val("");
        $('#kNoTel').val("");
      }
    });
  });

  function validateNumber(elmnt, content) {
    //if it is character, then remove it..
    if (isNaN(content)) {
      elmnt.value = removeNonNumeric(content);
      return;
    }
  }

  function removeNonNumeric(strString) {
    var strValidCharacters = "1234567890-";
    var strReturn = "";
    var strBuffer = "";
    var intIndex = 0;
    // Loop through the string
    for (intIndex = 0; intIndex < strString.length; intIndex++) {
      strBuffer = strString.substr(intIndex, 1);
      // Is this a number
      if (strValidCharacters.indexOf(strBuffer) > -1) {
        strReturn += strBuffer;
      }
    }
    return strReturn;
  }
  
  function doSubmit(f,idmohon) {
           
       var form = $(f).formSerialize();
       var report = "REGTerimaanDokumen_MLK.rdf";
       var url = "reportName=" + report + "%26report_p_id_mohon=" + idmohon;
       
       window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url=" + url, "eTanah",
       "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
      
  }    
  

</script>
<div class="subtitle">
  <s:errors/>
  <s:messages/>
  <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
  <s:form beanclass="etanah.view.daftar.utiliti.UtilitiKutipanDokumen">
    <fieldset class="aras1" >
      <div id="pengutip">
        <lagend> Maklumat Pengutip </lagend>                

        <s:hidden name="idPermohonan" id="idPermohonan" />
        <s:hidden name="idHakmilik" id="idHakmilik" value=""/>
        <!----------- maklumat perserahan-->
        <s:hidden name="permohonan.penyerahNama" id="pNama" style="width:210px;" onkeyup="this.value=this.value.toUpperCase();"/>                      
        <s:hidden name="permohonan.penyerahAlamat1" id="pAlamat1" style="width:210px;" onkeyup="this.value=this.value.toUpperCase();"/>  
        <s:hidden name="permohonan.penyerahAlamat2" id="pAlamat2" style="width:210px;" onkeyup="this.value=this.value.toUpperCase();"/>   
        <s:hidden name="permohonan.penyerahAlamat3" id="pAlamat3" style="width:210px;" onkeyup="this.value=this.value.toUpperCase();"/>  
        <s:hidden name="permohonan.penyerahAlamat4" id="pAlamat4" style="width:210px;" onkeyup="this.value=this.value.toUpperCase();"/>   
        <s:hidden name="permohonan.penyerahPoskod" id="pPoskod" style="width:210px;" onkeyup="this.value=this.value.toUpperCase();"/>  
        <s:hidden name="permohonan.penyerahNegeri.kod" id="pNegeri" style="width:210px;" onkeyup="this.value=this.value.toUpperCase();"/>  
        <s:hidden name="permohonan.penyerahNoTelefon1" id="pTel" style="width:210px;" onkeyup="this.value=this.value.toUpperCase();"/>  
        <c:if test="${fn:length(actionBean.senaraiKutipanDokumen) > 0}"> 
          <br>
          <p>
            <label>Tarikh Kutipan :</label>
            <%--<s:text name="tarikhKutip1"  formatPattern="dd/MM/yyyy hh:mm:ss a" id="tarikhKutip1" style="width:210px;"
                    value="${actionBean.permohonanKutipanDokumen.tarikhKutipan}" readonly="true"/>  --%> 
            <fmt:formatDate value="${actionBean.permohonanKutipanDokumen.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy - HHH:MM:SS a"/>
          </p>
          <br>
          <p>
            <label>Jenis Pengenalan :</label>
            <s:select name="kJenisPengenalan1" id="kJenisPengenalan1" style="width:210px;">
              <s:option value="">${actionBean.permohonanKutipanDokumen.jenisPengenalan.nama}</s:option>
            </s:select>                       
          </p>
          <p>
            <label>No Pengenalan :</label> 
            <s:text name="kPengenalan1" id="kPengenalan" style="width:210px;" 
                    value="${actionBean.permohonanKutipanDokumen.noPengenalan}"
                    onkeyup="this.value=this.value.toUpperCase();" readonly="true"/>                     
          </p>
          <br>
          <p>                        
            <label>Nama Pengutip :</label>
            <s:text name="kNama1" id="kNama" style="width:210px;" 
                    value="${actionBean.permohonanKutipanDokumen.nama}" 
                    onkeyup="this.value=this.value.toUpperCase();" readonly="true"/>                       
          </p>
          <p>
            <label>Alamat :</label>
            <s:text name="kAlamat1_1" id="kAlamat1" style="width:210px;" 
                    value="${actionBean.permohonanKutipanDokumen.alamat.alamat1}"
                    onkeyup="this.value=this.value.toUpperCase();" readonly="true"/>                       
          </p>
          <p>
            <label>&nbsp;</label>
            <s:text name="kAlamat2_1" id="kAlamat2" style="width:210px;" 
                    value="${actionBean.permohonanKutipanDokumen.alamat.alamat2}"
                    onkeyup="this.value=this.value.toUpperCase();" readonly="true"/>                       
          </p>
          <p>
            <label>&nbsp;</label>
            <s:text name="kAlamat3_1" id="kAlamat3" style="width:210px;" 
                    value="${actionBean.permohonanKutipanDokumen.alamat.alamat3}"
                    onkeyup="this.value=this.value.toUpperCase();" readonly="true"/>                       
          </p>
          <p>
            <label>Bandar :</label>
            <s:text name="kBandar1" id="kBandar" style="width:210px;" 
                    value="${actionBean.permohonanKutipanDokumen.alamat.alamat4}"
                    onkeyup="this.value=this.value.toUpperCase();" readonly="true"/>                       
          </p>
          <p>
            <label>Poskod :</label>
            <s:text name="kPoskod1" id="kPoskod" style="width:210px;" 
                    value="${actionBean.permohonanKutipanDokumen.alamat.poskod}"
                    onkeyup="validateNumber(this,this.value);" readonly="true"/>                       
          </p>
          <p>
            <label>Negeri :</label>                        
            <s:select name="kNegeri1" id="kNegeri" style="width:210px;">
              <s:option value="">${actionBean.permohonanKutipanDokumen.alamat.negeri.nama}</s:option>
            </s:select>              
          </p>
          <p>
            <label>No Telefon :</label>
            <s:text name="kNoTel1" id="kNoTel" style="width:210px;" value="${actionBean.permohonanKutipanDokumen.telefon}"
                    onkeyup="validateNumber(this,this.value);" readonly="true"/>                       
          </p>
          <br>
          <p>
            <label>&nbsp;</label>
            <s:submit name="kutip" value="Papar Semula" class="btn" title="Simpan Rekod Kutipan Dokumen" 
                      onmouseover="this.style.cursor='pointer';"/>
            <s:button name="cetak" value="Cetak" class="btn" title="Cetak Pengesahan Terimaan" onmouseover="this.style.cursor='pointer';" onclick="doSubmit(this.form, '${actionBean.permohonan.idPermohonan}');"/>           
          </p>
          <br>
        </c:if>
        <c:if test="${fn:length(actionBean.senaraiKutipanDokumen) < 1}">
          <p> Sila isi maklumat pengutip dokumen pada ruangan yang disediakan </p>
          <p>Medan bertanda <em>*</em> adalah mandatori.</p><br>
          <table style="width:100%">   
            <tr>
              <td>
                <p>
                  <label>Jenis Pengenalan :</label>
                  <s:select name="kJenisPengenalan" id="kJenisPengenalan" style="width:210px;">
                    <s:option value="">--- Sila Pilih ---</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod"/>                        
                  </s:select> <em>*</em>
                </p>
                <p>
                  <label>No Pengenalan :</label>
                  <s:text name="kPengenalan" id="kPengenalan" style="width:210px;" 
                          onkeyup="this.value=this.value.toUpperCase();"/>  <em>*</em>                     
                </p>
                <br>                                
              </td>
            </tr>
            <tr class="tablecloth">
              <td>
                <p>
                  <label>&nbsp;</label>
                  <s:checkbox name="tikPenyerah" id="tikPenyerah" onmouseover="this.style.cursor='pointer';"/>&nbsp;
                  <b>Sila Tandakan Jika Dokumen Dikutip Oleh Penyerah.</b>                           
                </p>
              </td>
            </tr>
            <tr>
              <td>                                
                <p>
                  <label>Nama Pengutip :</label>
                  <s:text name="kNama" id="kNama" style="width:210px;" 
                          onkeyup="this.value=this.value.toUpperCase();"/>  <em>*</em>                     
                </p>
                <p>
                  <label>Alamat :</label>
                  <s:text name="kAlamat1" id="kAlamat1" style="width:210px;" 
                          value="${actionBean.permohonanKutipanDokumen.alamat.alamat1}"
                          onkeyup="this.value=this.value.toUpperCase();"/>                       
                </p>
                <p>
                  <label>&nbsp;</label>
                  <s:text name="kAlamat2" id="kAlamat2" style="width:210px;" 
                          value="${actionBean.permohonanKutipanDokumen.alamat.alamat2}"
                          onkeyup="this.value=this.value.toUpperCase();"/>                       
                </p>
                <p>
                  <label>&nbsp;</label>
                  <s:text name="kAlamat3" id="kAlamat3" style="width:210px;" 
                          value="${actionBean.permohonanKutipanDokumen.alamat.alamat3}"
                          onkeyup="this.value=this.value.toUpperCase();"/>                       
                </p>
                <p>
                  <label>Bandar :</label>
                  <s:text name="kBandar" id="kBandar" style="width:210px;" 
                          value="${actionBean.permohonanKutipanDokumen.alamat.alamat4}"
                          onkeyup="this.value=this.value.toUpperCase();"/>                       
                </p>
                <p>
                  <label>Poskod :</label>
                  <s:text name="kPoskod" id="kPoskod" style="width:210px;" 
                          value="${actionBean.permohonanKutipanDokumen.alamat.poskod}"
                          onkeyup="validateNumber(this,this.value);" />                       
                </p>
                <p>
                  <label>Negeri :</label>
                  <s:select name="kNegeri" id="kNegeri" style="width:210px;">
                    <s:option value="">-- Sili Pilih --</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod"/>                        
                  </s:select>                     
                </p>
                <p>
                  <label>No Telefon :</label>
                  <s:text name="kNoTel" id="kNoTel" style="width:210px;" value="${actionBean.permohonanKutipanDokumen.telefon}"
                          onkeyup="validateNumber(this,this.value);"/>                       
                </p>
              </td>
            </tr>
          </table>
          <br>
          <p>
            <label>&nbsp;</label>
            <s:submit name="kutip" value="Kutip Dokumen" class="btn" title="Simpan Rekod Kutipan Dokumen" onmouseover="this.style.cursor='pointer';"/>
         </p>
          <br>
          <br>
        </c:if>
      </div>
    </fieldset>
  </s:form>        
</div>
