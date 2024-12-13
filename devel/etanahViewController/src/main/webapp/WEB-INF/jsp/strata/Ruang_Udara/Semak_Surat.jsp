<%-- 
    Document   : Semak Surat
    Created on : Jun 28, 2011, 11:05:51 PM
    Author     : zadhirul.farihim
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<script type="text/javascript">
    $(document).ready(function(){
        if(${actionBean.permohonan.kodUrusan.kod eq 'RBHS'}){    
    <c:choose>
        <c:when test="${actionBean.isJanaDokumen}">
                    $("#hantar").attr("disabled", false);
        </c:when>
        <c:otherwise>
                    $("#hantar").attr("disabled", true);
        </c:otherwise>
    </c:choose>
            }
        });
        function paparReport() {
            var url = "${pageContext.request.contextPath}/strata/jana?viewReport";
        
            var params  = 'width='+screen.width;
            params += ', height='+screen.height;
            params += ', scrollbars=yes';
            params += ', top=0, left=0'
            params += ', fullscreen=no';
        
            window.open(url, "etanah", params);
            if (window.focus) {newwin.focus()}
            return false;
               
        }
</script>
<%--original <s:form name="form1" beanclass="etanah.view.strata.pageForViewJana">--%>
<s:form beanclass="etanah.view.strata.pageForViewJana">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <c:if test="${disablePapar}">
            <fieldset class="aras1">

                <font size="2" color="red">Sila Semak Surat Lantikan.</font>

            </fieldset>
        </c:if>
        <c:if test="${!disablePapar}">
            <fieldset class="aras1">

                <font size="2" color="red">Sila klik butang 'Jana Dokumen' dan semak dokumen di tab 'Dokumen'.</font>

            </fieldset>
        </c:if>
    </div>
</s:form>

