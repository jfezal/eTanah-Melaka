<!--
    Document   : Borang G.jsp
    Created on : May 28, 2012, 5:45 PM
    Author     : Aizuddin Orogenic Group
    Description: Borang G for MPJLB and MLCRG
-->
<%--<%@ taglib prefix="s" uri="http://s.sourceforge.net/s.tld"%>--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>

<body>
<%--<s:form action="/pelupusan/BorangG" >--%>
<%--<s:form action="/pelupusan/BorangG?saveData">--%>
<s:form beanclass="etanah.view.stripes.pelupusan.BorangGActionBean">
  <table width="100%" border="0">
  <tr>
    <th colspan="4" scope="col">Borang G </th>
  </tr>
  <tr>
    <td colspan="4">  <div align="center">Enakmen Mineral (Melaka)2002</div></td>
  </tr>
  <tr>
    <td colspan="4"> <div align="center">(Subseksyen 81(10))</div></td>
  </tr>
  <tr>
    <td colspan="4">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="4"><div align="center">LESEN MELOMBONG TUAN PUNYA</div></td>
  </tr>
  <tr>
    <td colspan="4">&nbsp;</td>
  </tr>
  <tr>
    <td width="27%">&nbsp;</td>
    <td colspan="2">Tempoh Diluluskan (Bulan)</td>
    
        <c:if test="${actionBean.currentStage eq ('sedia' or 'semak')}">
            <td width="43%">
        <s:text name="tempohDiluluskan"  id="tempohDiluluskan" value="${actionBean.tempohDiluluskan}" size="15"/>
            </td>
        </c:if>
    
        <c:if test="${actionBean.currentStage eq 'maklum'}">
        <td width="43%"><s:format value="${actionBean.tempohDiluluskan}" formatPattern="###"/></td>
        </c:if>
    
   </tr>
  <tr>
    <td>&nbsp;</td>
    <td colspan="2">Tarikh Habis Tempoh</td>
    <td width="43%"><s:format value="${actionBean.tarikhHabis}" formatPattern="dd/MM/yyyy"/></td>
   
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td colspan="2">Sewa Tahunan (RM)</td>
    <c:if test="${actionBean.currentStage eq ('sedia' or 'semak')}">
        <td width="43%"><s:text name="sewaTahunan" id="sewaTahunan" formatPattern="###,###,###.00" value="${actionBean.sewaTahunan}" size="15"/></td>
    </c:if>
    <c:if test="${actionBean.currentStage eq 'maklum'}">
        <fmt:setLocale value="ms_MY"/>
        <td width="43%"><fmt:formatNumber value="${actionBean.sewaTahunan}" type="currency"/></td>
    </c:if>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td colspan="2">No Lesen Melombong Tuan Punya/Pendaftaran</td>
    <td width="43%">${actionBean.no_lesen}</td>
   
  </tr>
  <tr>
    <td colspan="4">&nbsp;
      <div align="center">Lesen telah diberbaharui selama ${actionBean.tempohDiluluskan} bulan bermula dari <s:format value="${actionBean.tarikhHabis}" formatPattern="dd/MM/yyyy"/> sehingga <s:format value="${actionBean.tarikhTamat}" formatPattern="dd/MM/yyyy"/>
      </div></td>
  <tr>
    <td colspan="4">&nbsp;</td>
  <tr>
    <td>&nbsp;</td>
    <td width="20%">&nbsp;</td>
    <td width="10%">
        <s:button name="saveData2" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name,'page_div')"/>
    </td>
    <td>&nbsp;</td>
  </table>
</s:form>
<p>&nbsp;</p>
</body>
</html>
