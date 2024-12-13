<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>

<script type="text/javascript">

    $(document).ready(function() {

    });


</script>
<s:errors/>
<s:messages/>
<div id="error"/>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
<s:form beanclass="etanah.view.stripes.pelupusan.GISIntegrationActionBean">
    <s:hidden name="path" id="path" value="${path}"/>
    <s:hidden name="result" id="result" value="${result}"/>

    <fieldset class="aras1">
        <legend>Muat Naik</legend>
        <p>
        <label><font color="red">*</font>Pelan :</label>

        <div id="result1"></div>
        <c:if test="${actionBean.resultPelan1 ne true}"> 
            <div id="result0">
                <s:file name="file" id="file"/>
                <br/>
                <p>
                    <label>&nbsp;</label>&nbsp;
                    <s:submit name="simpanP1" value="Simpan" class="btn"/>
                </p>
            </div>
        </c:if>
        <c:if test="${actionBean.resultPelan1 eq true}">
            <p>
                Pelan Telah Di MuatNaik untuk memuatnaik semula, sila HAPUS sebelum memuat naik <br>
            </p>
            <p>
                <label>Fail yang dimuat naik :</label><a href="${pageContext.request.contextPath}${actionBean.path}" </a>Klik Untuk Semak Pelan
            </p>
            <p>
                    <label>&nbsp;</label>
                    <s:submit name="deleteP1" value="Hapus Pelan P1" class="btn"/>
            </p>

        </c:if> 
    </p>                               

</fieldset>


</s:form>
