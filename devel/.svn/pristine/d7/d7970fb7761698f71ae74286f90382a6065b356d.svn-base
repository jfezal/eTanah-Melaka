<%-- 
    Document   : kemaskini_notis_main
    Created on : Nov 5, 2012, 11:21:03 PM
    Author     : ei
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
  //    function doSearch(e,f) {
  //        var a = $('#idPermohonan').val();
  ////        var b = $('#idHakmilik').val();
  //        if(a == '' ){
  ////        if(a == '' && b == '' && c == ''){
  //            alert('Sila masukan id Perserahan');            
  //            return;
  //        }
  //        f.action = f.action + '?' + e;
  //        f.submit();
  //    }

</script>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/pub/styles/styles.css" />
    <title>Kemaskini Notis</title>
  </head>
  <body>
    <stripes:messages />
    <stripes:errors />
    <!-- KEMASKINI NOTIS -->
    <stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>
    <stripes:useActionBean beanclass="etanah.view.daftar.utiliti.KemaskiniNotisActionBean" var="kemasNotis" />         
    <stripes:form action="/utiliti/kemaskini_Notis">      
      <fieldset class="aras1" style="height:400px;">
        <legend>Utiliti Kemaskini Notis </legend>                
        <p>Sila pilih jenis carian</p>
        <p><label for="idPermohonan">Id Permohonan/ID Perserahan :</label>
          <input type="text" name="permohonan.idPermohonan" id="idPermohonan" onkeyup="this.value = this.value.toUpperCase();" style="width:180px;"/>
          <em>atau</em>
        </p> 
        <c:if test="${actionBean.kodNegeri eq 'N9'}">
          <p>
            <label>Jenis Notis :</label>
            <stripes:select name="jenisNotis" id="jenisNotis" style="width:180px;">
              <stripes:option value="">Sila Pilih</stripes:option>                    
              <stripes:option value="19A">Borang 19A</stripes:option>
              <stripes:option value="19C">Borang 19C</stripes:option>
              <stripes:option value="19F">Borang 19F</stripes:option>
              <stripes:option value="16H">Notis 2A</stripes:option>
              <stripes:option value="2B">Notis 2B</stripes:option>
              <stripes:option value="5A">Notis 5A</stripes:option>
              <stripes:option value="5F">Notis 5F</stripes:option>
              <stripes:option value="6A">Notis 6A</stripes:option>
              <stripes:option value="8A">Notis 8A</stripes:option>
              <%--                    <stripes:option value="">Lain-Lain Notis</stripes:option> --%>
            </stripes:select>
          </p> 
        </c:if>
        <c:if test="${actionBean.kodNegeri eq 'Mlk'}">
          <p>
            <label>Jenis Notis :</label>
            <stripes:select name="jenisNotis" id="jenisNotis" style="width:180px;">
              <stripes:option value="">--Sila Pilih--</stripes:option>
              <c:forEach items="${listUtil.senaraiKodNotis}" var="i" >
                <stripes:option value="${i.kod}">${i.nama}</stripes:option>
              </c:forEach>
            </stripes:select>
          </p>
        </c:if>
        <br>
        <p>
          <label>&nbsp;</label>
          <stripes:hidden name="kodNegeri" value="${actionBean.kodNegeri}"/>
          <stripes:submit name="checkPermohonan" value="Seterusnya" class="btn" onclick="doSearch(this.name, this.form);"/>                     
        </p>
        <br>
      </fieldset>
    </stripes:form>
    <!--    </div>-->
  </body>
</html>
