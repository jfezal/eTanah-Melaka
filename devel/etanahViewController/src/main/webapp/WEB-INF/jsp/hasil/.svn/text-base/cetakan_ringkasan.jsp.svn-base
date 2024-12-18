<%-- 
    Document   : cetakan_ringkasan
    Created on : Jun 15, 2011, 11:07:16 AM
    Author     : abdul.hakim
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN"
    "http://www.w3.org/p/html4/loose.dlabel">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<script type="text/javascript">
    $(document).ready(function() {
        var pguna = ${actionBean.pguna.perananUtama.kod};
        if((pguna == '2')||(pguna == '5')){
            $.get("${pageContext.request.contextPath}/hasil/mod_kutip?checkSession",
            function(data){
                var m = data.charAt(1);
                if(m == 'B'){$('#modKutip').val('B');}
                if(m == 'L'){$('#modKutip').val('L');}
            });
        }
    });

    function doPrintResit(){
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top:  ($(window).height() - 50) /2 + 'px',
                left: ($(window).width() - 50) /2 + 'px',
                width: '50px'
            }
        });
        var a = document.getElementById('applet2');
        var trkh = document.getElementById('tarikh');
        var mod = document.getElementById('modKutip');
        var kaunter = document.getElementById('kaunter');
        var idPguna = document.getElementById('idPguna');

        <%--alert(kaunter.value+"    "+idPguna.value+"    "+trkh.value+"    "+mod.value);--%>
        a.printSummary(trkh.value,mod.value,kaunter.value,idPguna.value);
        $.unblockUI();
    }
</script>

<div align="center">
    <table style="width:99.2%" bgcolor="green">
        <tr><td width="100%" height="20" ><div style="background-color: green;" align="center">
                    <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Cetakan Ringkasan</font>
                </div></td></tr>
    </table></div>
    <stripes:errors />
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif"
     width="50" height="50" style="display: none" alt=""/>
<stripes:form beanclass="etanah.view.stripes.hasil.CetakanRingkasanActionBean" id="cetak_ringkasan">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Cetakan</legend>
            <p>
                <label>Tarikh :</label>
                <stripes:text name="tarikh" id="tarikh" readonly="true"/>
            </p>
            <p>
                <label>Pilih Mod Kutipan :</label>
                <stripes:select name="mod" id="modKutip" disabled="true">
                    <stripes:option label="Pilih Mod Kutipan..." value="" />
                    <stripes:option label="Biasa"  value="B" />
                    <stripes:option label="Lewat" value="L" />
                </stripes:select>
            </p>
            <p>
                <label>Id Pengguna :</label>
                ${actionBean.pguna.idPengguna}&nbsp;
                <stripes:text name="pguna.idPengguna" id="idPguna" style="display:none;"/>
            </p>
            <p>
                <label>Kaunter :</label>
                ${actionBean.pguna.idKaunter}&nbsp;
                <stripes:text name="pguna.idKaunter" id="kaunter" style="display:none;"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <stripes:button name="" value="Cetak" class="btn" onclick="doPrintResit()"/>
                <%--<stripes:button name=" " value="Isi Semula" class="btn" onclick="clearText('cetak_ringkasan');"/>--%>
            </p>
            <br>
        </fieldset>
    </div>

    <c:set value="05" var="negeri"/>
    <c:if test="${actionBean.kodNegeri eq 'melaka'}">
        <c:set value="04" var="negeri"/>
    </c:if>
    <applet code="etanah.dokumen.print.DocumentPrinter" ARCHIVE="${pageContext.request.contextPath}/PrintApplet.jar,
            ${pageContext.request.contextPath}/commons-httpclient-2.0.2.jar,
            ${pageContext.request.contextPath}/commons-logging.jar,
            ${pageContext.request.contextPath}/swingx-1.6.jar,
            ${pageContext.request.contextPath}/log4j-1.2.12.jar,
            ${pageContext.request.contextPath}/jpedal_trial.jar,
            ${pageContext.request.contextPath}/PDFRenderer.jar"
                codebase = "${pageContext.request.contextPath}/"
                name     = "applet"
                id       = "applet"
                width    = "1"
                height   = "1"
                align    = "middle">
        <param name="uploadAction" value="<%=request.getContextPath()%>/PrintServlet"/>
        <param name ="method" value="pdfp">
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
    <applet code="etanah.dokumen.print.PrinterHasil" ARCHIVE="${pageContext.request.contextPath}/PrintApplet.jar,
            ${pageContext.request.contextPath}/commons-httpclient-2.0.2.jar,
            ${pageContext.request.contextPath}/commons-logging.jar,
            ${pageContext.request.contextPath}/swingx-1.6.jar,
            ${pageContext.request.contextPath}/log4j-1.2.12.jar,
            ${pageContext.request.contextPath}/jpedal_demo.jar"
            codebase = "${pageContext.request.contextPath}/"
            name     = "applet2"
            id       = "applet2"
            width    = "1"
            height   = "1"
            align    = "middle">
        <param name="uploadAction" value="<%=request.getContextPath()%>/PrintServlet"/>
        <param name="kod_negeri" value="${negeri}"/>
        <param name ="method" value="pdfp">
        <%
       Cookie[] cookies2 = request.getCookies();
       StringBuffer sb2 = new StringBuffer();
       for (int i = 0; i < cookies2.length; i++) {
          sb2.setLength(0);
          sb2.append(cookies2[i].getName());
          sb2.append("=");
          sb2.append(cookies2[i].getValue());
        %>
        <param name="Cookie<%= i %>" value="<%= sb2.toString() %>"><%
      }
        %>
    </applet>
</stripes:form>