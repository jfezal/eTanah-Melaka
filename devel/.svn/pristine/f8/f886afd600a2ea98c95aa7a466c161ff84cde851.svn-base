<%--
    Document   : testing1
    Created on : Dec 16, 2009, 10:42:01 AM
    Author     : Sreenivasa Reddy Munagal
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
<s:form beanclass="etanah.view.strata.PermohonanStrataPKKRActionBean">
   <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
         <legend><font size="2" color="Red">Sila Klik Butang Jana Dokumen Untuk Menjana Dokumen.</font></legend>
         <p align="left">
<!--             <s:button name="jana" value="Jana" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')" /><br>-->
<%--             ${actionBean.iframeURL}
    <c:if test="${jana}">--%>
<%--    <c:if test="${actionBean.print}">
    <iframe width="1000" height="1200" src="${actionBean.iframeURL}#navpanes=0 &toolbar=0&messages=0"  scrolling=yes></iframe>
    </c:if>
    ${actionBean.jana}
    ${actionBean.print}
    <c:if test="${actionBean.jana}">
     <iframe width="1000" height="1200" src="${pageContext.request.contextPath}/strata/jana?printReport#navpanes=0 &toolbar=0&messages=0"   scrolling=yes></iframe>
      </c:if>
    </p>&nbsp;--%>
    <%--<embed toolbar="0" src="${actionBean.iframeURL}">--%>
           
        </fieldset>
</div>
            &nbsp;
    &nbsp;&nbsp;
</s:form>