<%-- 
    Document   : rekod_scan_doc
    Created on : 27 Julai 2010, 4:28:34 PM
    Author     : abu.mansur
--%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>

<script type="text/javascript">

    function successRedirection() {
        <%--var idDokumen = $('#dokumen').val();
        var idNotis = $('#notis').val();
        var url = "${pageContext.request.contextPath}/hasil/rekod_penghantaran2?updateDTN&idNotis="+idNotis+"&idDokumen="+idDokumen;
        $.get(url,
        function(data){
            if(data == "berjaya"){
                alert("Imbasan berjaya. Bukti penyampaian telah berjaya disimpan.");
            }else if(data == "masalah"){
                alert("Terdapat masalah pada parameter.");
            }else if(data == "gagal"){
                alert("Bukti penyampaian telah gagal diimbas.");
            }
        });--%>
        var hakmilik = '${actionBean.idHakmilik}';
        var dasar = '${actionBean.noDasar}';
        var status = '${actionBean.kodStatus}';
        self.opener.doReloadPagePihak(hakmilik, dasar, status);
        self.close();
        <%--var url = "${pageContext.request.contextPath}/hasil/rekod_penghantaran2?rekodHantar&idHakmilik="+hakmilik+'&noDasar='+dasar+'&kodStatus='+status;
        $.get(url,
        function(data){
            self.close();
            $('#rekod_hantar2',opener.document).html(data);
        }, "html");--%>
    }

    function confirmCancel(){
        if(confirm('Adakah anda pasti?')){
            self.close();
        }
    }

</script>

<s:form beanclass="etanah.view.stripes.hasil.RekodPenghantaran2ActionBean">
    <applet code="etanah.dokumen.scan.DocumentScanner"
        onload="javascript:focus();"
        archive="${pageContext.request.contextPath}/ScanApplet.jar, ${pageContext.request.contextPath}/JTwain.jar, ${pageContext.request.contextPath}/java-image-scaling.jar"
        codebase="${pageContext.request.contextPath}/"
        width="800" height="600">
            <%--<s:hidden id="notis" name="idNotis"/>
            <s:hidden id="dokumen" name="idDokumen"/>--%>
            <param name="idDokumen" value="${actionBean.idDokumen}" />
            <!-- param name="isDialog" value="1" -->
            <%
            Cookie[] cookies = request.getCookies();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < cookies.length; i++) {
                sb.setLength(0);
                sb.append(cookies[i].getName());
                sb.append("=");
                sb.append(cookies[i].getValue());
                %>
                <param name="Cookie<%= i%>" value="<%= sb.toString()%>">
             <%
             }
             %>
    </applet>
</s:form>

TIP: Sila pastikan saiz imej yang sesuai untuk disimpan dengan menyelaraskan perisian pengimbas.
Saiz imej yang terlalu besar akan menjana data yang besar untuk disimpan dan melambatkan proses
muatnaik.
