<%-- 
    Document   : kutipan_data_cukai
    Created on : Dec 5, 2013, 12:13:31 AM
    Author     : azri
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://stripes.sourceforge.net/stripes-security.tld" prefix="ss"%>
<!DOCTYPE html>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">
  $(document).ready(function() {

  });
</script>
<div class="a">
  <s:errors/>
  <s:messages/>
  <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
  <s:form beanclass="etanah.view.daftar.utiliti.KutipanDataActionBean">
    <div class="subtitle">
      <fieldset class="aras1">
        <lagend>Maklumat Cukai Tanah</lagend>
        <br><br>
        <div align="center">
          <display:table class="tablecloth" style="width:95%;" id="line" cellpadding="0" cellspacing="0"
                         requestURI="/daftar/utiliti/kutipanData" name="${actionBean.listKewAkaun}" >                
            <display:column title="Bil" style="width:1%"><p align="right">${line_rowNum}</p></display:column>
            <display:column title="ID Hakmilik" style="width:17%;">
              <div align="center">${line.hakmilik.idHakmilik}</div>
            </display:column> 
            <display:column title="No Akaun" style="width:17%;">
              <div align="center">${line.noAkaun}</div>
            </display:column>
            <display:column title="Nama Pembayar" property="pemegang.nama"/> 
            <display:column title="Jenis / No Pengenalan" style="width:12%;">
              <div align="center">
                ${line.pemegang.jenisPengenalan.nama}
                <c:if test="${line.pemegang.noPengenalan ne null && line.pemegang.jenisPengenalan.nama ne null}"> / </c:if>
                ${line.pemegang.noPengenalan}
                </div>
            </display:column> 
            <display:column title="Alamat Pembayar">
              ${line.pemegang.suratAlamat1}&nbsp;
              ${line.pemegang.suratAlamat2}&nbsp;
              ${line.pemegang.suratAlamat3}&nbsp;
              ${line.pemegang.suratAlamat4}&nbsp;
              ${line.pemegang.suratPoskod}&nbsp;
              ${line.pemegang.suratNegeri.nama}
            </display:column> 
          </display:table>
        </div>
        <br>
      </fieldset>
    </div>
  </s:form>
</div>