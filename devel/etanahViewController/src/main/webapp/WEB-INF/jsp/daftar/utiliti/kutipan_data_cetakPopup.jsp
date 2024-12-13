<%-- 
    Document   : kutipan_data_cetakPopup
    Created on : Nov 22, 2013, 3:04:22 PM
    Author     : azri
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://stripes.sourceforge.net/stripes-security.tld" prefix="ss"%>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<!DOCTYPE html>
<script type="text/javascript">
  $(document).ready(function() {

  });
  function doPrintViaApplet(id) {
//    alert(id);
    var a = document.getElementById('applet');
    a.doPrint(id.toString());
  }

</script>
<div class="a">
  <s:errors/>
  <s:messages/>
  <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
  <s:form beanclass="etanah.view.daftar.utiliti.KutipanDataActionBean">
    <fieldset class="aras1">
      <br>
      <div align='center'>
        <table style="width:90%;">
          <tr>
            <td>
              <font style="font-family: Tahoma; font-size: 12px; " align="center">
              Sila klik "OK" untuk mula mencetak dokumen.
              </font>
              <br><br>
            </td>
          </tr>
          <tr>
            <td>
              <s:button name="cetak" id="cetak" value="OK" class="btn" onclick="doPrintViaApplet('${actionBean.idDokumen}');"/>
              <s:button name="batal" id="batal" value="Batal" class="btn" onclick="self.close();"/>  
            </td>
          </tr>
        </table>          
      </div>
      <br>
      <applet code="PrintApplet.class" ARCHIVE="${pageContext.request.contextPath}/PrintApplet.jar,
              ${pageContext.request.contextPath}/commons-httpclient-2.0.2.jar,
              ${pageContext.request.contextPath}/commons-logging.jar,
              ${pageContext.request.contextPath}/swingx-1.6.jar,
              ${pageContext.request.contextPath}/log4j-1.2.12.jar,
              ${pageContext.request.contextPath}/jpedal_demo.jar"
              codebase = "."
              name     = "applet"
              id       = "applet"
              width    = "1"
              height   = "1"
              align    = "middle">
        <param name="uploadAction" value="<%=request.getContextPath()%>/PrintServlet"/>
        <param name ="kodNegeri" value="05">
        <param name ="method" value="pdfp">
        <!--      <param name ="disabledWatermark" value="true"/>--> 
        <param name ="idPengguna" value="${idPengguna}"/>
        <%
          Cookie[] cookies = request.getCookies();
          StringBuffer sb = new StringBuffer();
          for (int i = 0; i < cookies.length; i++) {
            sb.setLength(0);
            sb.append(cookies[i].getName());
            sb.append("=");
            sb.append(cookies[i].getValue());
        %>
        <param name="Cookie<%= i%>" value="<%= sb.toString()%>"><%
          }
        %>
      </applet>      
      <br>
      <br>
    </fieldset>
  </s:form>
</div>
