<%-- 
    Document   : dev_laporan_MLK
    Created on : Sep 22, 2010, 10:57:44 PM
    Author     : nursyazwani
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript">
    function popupParam(nama){
        window.open("${pageContext.request.contextPath}/pembangunan/laporanpembangunanMLK/requestParam?namaReport="+nama, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }
</script>

<div id="laporan">
    <s:form beanclass="etanah.view.stripes.pembangunan.LaporanPembangunanMLKActionBean">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Senarai Laporan
                </legend>
                <br>
                <c:set value="1" var="count"/>
                <c:forEach items="${actionBean.senaraiReport}" var="report">
                    <c:out value="${count}"/>) <a href="#" onclick="popupParam('${actionBean.senaraiReportName[count-1]}');"><c:out value="${report}"/></a>
                    <c:set value="${count +1}" var="count"/>
                    <br>
                </c:forEach>
                    <br>
            </fieldset>
        </div>
    </s:form>
</div>