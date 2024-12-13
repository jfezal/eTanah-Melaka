<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252" language="java"%>
<link type="text/css" href="${pageContext.request.contextPath}/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">

     $(document).ready(function() {
          $('form').submit(function() {
               var req1 = $('#idmohon').val();
               var req2 = $('#idSptb').val();
               var kodSerah = $('#kodSerah').val();
               if (req1 === '' && req2 === '') {
                    alert('sila masukan ID Permohonan atau ID Perserahan SPTB');
                    return false;
               }
               return true;
          });
     });

     function test(f) {
          $(f).clearForm();
     }

</script>
<s:form beanclass="etanah.view.utility.KemasukanBPEL">
     <s:hidden name="mohon.idPermohonan"/>
     <div class="subtitle">
          <s:errors/>
          <s:messages/>
     </div>
     <div class="subtitle">
          <fieldset class="aras1">
               <legend>Kemasukan Permohonan Hilang</legend>
               <font size="1" color="Red">Sila masukkan ID Permohonan untuk membuat carian.</font>
               <p>
                    <label><font color="red">*</font>ID Permohonan :</label>
                    <s:text name="mohon.idPermohonan" id="idmohon"/>             
               </p>
               <p>
                    <label><em>atau</em> </label>&nbsp;
               </p>     
               <p>
                    <label><font color="red">*</font>ID Perserahan SPTB :</label>
                    <s:select name="mohon.kodUrusan.kodPerserahan.kod" id="kodSerah">                         
                         <s:option value="B">Borang</s:option>
                         <s:option value="N">Nota</s:option>
                         <s:option value="NB">Nota Betul</s:option>
                         <s:option value="SC">Suratcara</s:option>
                         <s:option value="MH">Permohonan Hakmilik</s:option>
                         <s:option value="SA">Surat Amanah</s:option>
                         <s:option value="SB">Surat Kebenaran</s:option>
                         <s:option value="SW">Suratkuasa Wakil</s:option>
                    </s:select>&nbsp;<s:text name="mohon.noPerserahanSptb" id="idSptb"/>
               </p>              
               <p>
                    <label>&nbsp;</label>
                    <c:if test="${cari}">
                         <s:submit name="searchMohon" id="search" value="Cari" class="btn"/>
                    </c:if>
                    <c:if test="${simpan}">
                         <s:submit name="initiateBPEL" id="save" value="Jana" class="btn"/>
                    </c:if>
                    <s:submit name="showForm" value="Isi Semula" class="btn" onclick="test(this.form);"/>
               </p>
          </fieldset>

     </div>
</s:form>
