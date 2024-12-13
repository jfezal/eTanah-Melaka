<%-- 
    Document   : kutipan_dokumen_tab
    Created on : Dec 4, 2012, 11:36:34 AM
    Author     : ei
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://stripes.sourceforge.net/stripes-security.tld" prefix="ss"%>

<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.tabs.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<link type="text/css" href="pub/styles/tabNavList.css" rel="stylesheet"/>

<style type="text/css">
  .cursor_pointer {
    cursor:pointer;
  }
</style>
<!DOCTYPE html>
<script type="text/javascript">
  /*THIS JAVASCRIPT FIX DISPLAY TAG BUG TO OPEN PROPER TAB AFTER CLICK PAGE NUMEBR ON DISPLAY TAG*/
  $(document).ready(function() {
    $("#tab_dokumen").tabs();
    $("#tab_dokumen").tabs('select', '#' + '${actionBean.selectedTab}');
  });

</script>
<div id="aa">
  <div class="subtitle">
    <%--<s:errors/>
    <s:messages/>--%>
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <s:form beanclass="etanah.view.daftar.utiliti.UtilitiKutipanDokumen">
      <%--<s:text name="kodDaerah" value="${actionBean.kodDaerah}"/>--%>
      <%--<s:text name="kodHakmilik" value="${actionBean.kodHakmilik}"/>--%>
      <%--<s:text name="caw" value="${actionBean.caw}"/>--%>
      <fieldset class="aras1">
        <legend>Kutipan Dokumen (Pertanyaan dan Cetakan Serahan Dokumen)</legend><br>
        <p>
          <label for="idPermohonan">Id Permohonan/ID Perserahan :</label>
          ${actionBean.permohonan.idPermohonan}
          <s:hidden name="idPermohonan" id="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>                        
        </p>
        <br>
        <p>
          <label>Id Penyerah / Jenis Penyerah :</label>          
          <c:if test="${actionBean.permohonan.idPenyerah ne null }">
            ${actionBean.permohonan.idPenyerah}      
          </c:if>
          <c:if test="${actionBean.permohonan.idPenyerah ne null && actionBean.permohonan.kodPenyerah.nama ne null}">
            &nbsp;/&nbsp;     
          </c:if>
          <c:if test="${actionBean.permohonan.kodPenyerah.nama ne null}">
            ${actionBean.permohonan.kodPenyerah.nama} 
          </c:if>
          &nbsp;
        </p>
        <p>
          <label>Nama Penyerah :</label>  
          ${actionBean.permohonan.penyerahNama}         
          &nbsp;
        </p>
        <p>
          <label for="alamat">Alamat :</label>          
          ${actionBean.permohonan.penyerahAlamat1}          
          &nbsp;
        </p>
        <c:if test="${actionBean.permohonan.penyerahAlamat2 ne null}">
          <p>
            <label >&nbsp;</label>
            ${actionBean.permohonan.penyerahAlamat2}
          </p>
        </c:if> 
        <c:if test="${actionBean.permohonan.penyerahAlamat3 ne null}">
          <p>
            <label >&nbsp;</label>
            ${actionBean.permohonan.penyerahAlamat3}                    
          </p>
        </c:if>
        <c:if test="${actionBean.permohonan.penyerahAlamat4 ne null}">
          <p>
            <label>Bandar :</label>
            ${actionBean.permohonan.penyerahAlamat4}               
          </p>
        </c:if>
        <p>
          <label>Poskod :</label>
          ${actionBean.permohonan.penyerahPoskod} 
          &nbsp;
        </p>
        <p>
          <label>Negeri :</label>
          ${actionBean.permohonan.penyerahNegeri.nama}       
          &nbsp;
        </p>                
        <br><br>
        <!--        BACK BUTTON FUNCTION... -->
        <s:form beanclass="etanah.view.daftar.utiliti.UtilitiKutipanDokumen" name="form2">          
          <center>                    
            <c:if test="${actionBean.back eq '1'}">            
              <s:hidden name="idPermohonan" id="cariIdPermohonan"  style="width:210px;" />   
            </c:if>
            <c:if test="${actionBean.back eq '2'}">
              <s:hidden name="idHakmilik" id="cariIdHakmilik" style="width:210px;"/>
            </c:if>
            <c:if test="${actionBean.back eq '3'}">
              <s:hidden name="jenisPenyerah" value="${actionBean.jenisPenyerah}"/>
              <s:hidden name="idPenyerah" id="idPenyerah" onkeyup="this.value=this.value.toUpperCase();" style="width:210px;"/>
            </c:if>
            <s:submit name="searchDokumen" value="Kembali" class="btn" id="searchDokumen"  
                      title="Klik Untuk Cari" onmouseover="this.style.cursor='pointer';"/>
            <br><br>
          </center>        
        </s:form>  
      </fieldset>  
    </div>    

    <!--        TAB VIEW... -->
    <div id="tab_dokumen">
      <ul>
        <li><a href="#maklumat_permohonan" id="tab1">Maklumat Permohonan</a></li>
        <li><a href="#maklumat_pengutip" id="tab2">Maklumat Pengutip</a></li>
      </ul>
      <div id="maklumat_permohonan" >
        <%@ include file="/WEB-INF/jsp/daftar/utiliti/maklumat_dokumen_pemohon.jsp" %>
      </div>
      <div id="maklumat_pengutip">
        <%@ include file="/WEB-INF/jsp/daftar/utiliti/maklumat_dokumen_pengutip.jsp" %>
      </div>           
    </div> 
  </div>  
</s:form>
</div>
