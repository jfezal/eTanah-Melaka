<%--
    Document   : testing1
    Created on : Dec 16, 2009, 10:42:01 AM
    Author     : faidzal
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">

    function addNewPemohon(){
        window.open("${pageContext.request.contextPath}/strata/maklumat_pemohon?pemohonPopup", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=750");

    }
    function editPemohon(val){
        window.open("${pageContext.request.contextPath}/strata/maklumat_pemohon?editpemohonPopup&idPihak="+val, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=800,height=300");
    }

</script>
<s:form beanclass="etanah.view.strata.pageForViewJana">
   <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
         <%--<legend><font size="2" color="Red">Sila klik butang jana dokumen sebelum selesai.</font></legend>--%>
         <p align="center"><br>
<%--             ${actionBean.iframeURL}
    <c:if test="${jana}">--%>
    <c:if test="${actionBean.print}">
    <iframe width="1000" height="1200" src="${actionBean.iframeURL}#navpanes=0 &toolbar=0&messages=0"  scrolling=yes></iframe>
    </c:if>
    <c:if test="${actionBean.jana}">
    <object data="${pageContext.request.contextPath}/dokumen/view/${actionBean.idDokumen}#navpanes=0 &toolbar=0&messages=0" type="application/pdf" width="1000" height="1200">
 </c:if>
<!--      <iframe width="1000" height="1200" src="${pageContext.request.contextPath}/dokumen/view/${actionBean.idDokumen}#navpanes=0 &toolbar=0&messages=0"   scrolling=yes></iframe>-->

    </p>&nbsp;
    <%--<embed toolbar="0" src="${actionBean.iframeURL}">--%>
           
        </fieldset>
</div>
            &nbsp;
    &nbsp;&nbsp;
</s:form>