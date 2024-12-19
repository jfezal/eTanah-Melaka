<%-- 
    Document   : pembetul_main_swsbsa
    Created on : Oct 4, 2012, 5:37:25 PM
    Author     : ei
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<s:messages />
<s:errors />
<s:useActionBean beanclass="etanah.view.daftar.UtilitiBetulPerserahanSWSBSA" var="betulSWSBSA" />           
<s:form action="/daftar/pembetulanPertanyaanSBSWSA">
  <fieldset class="aras1">
    <legend>Pembetulan Permohonan SB/SW/SA </legend>
    <br>
    <p>
      <label for="idPermohonan">Id Permohonan/ID Perserahan :</label>
      <s:text name="permohonan.idPermohonan" id="idPermohonan" onkeyup="this.value = this.value.toUpperCase();" size="35"/>      
    </p>
    <p>
      <label>&nbsp;</label>
      <s:submit name="checkPermohonan" value="Seterusnya" class="btn" />
    </p>
    <br>
  </fieldset>
</s:form>        

