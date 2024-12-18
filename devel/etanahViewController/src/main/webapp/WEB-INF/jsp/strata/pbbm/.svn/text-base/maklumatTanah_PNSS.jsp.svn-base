<%--
    Document   : maklumatTanah_PNSS
    Created on : Nov 27, 2013, 10:42:01 AM
    Author     : Murali
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<style type="text/css">

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
        width:40em;
    }
</style>
<script type="text/javascript">    
    function popUpHakmilik(w){
        var url = '${pageContext.request.contextPath}/strata/maklumat_tanah?showFormPopupPNSS&idHakmilik='+w;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1500,height=800");
    }
</script>
<s:form beanclass="etanah.view.strata.MaklumatTanahActionBean">

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Hakmilik
            </legend>
            <p>
                <label>ID Hakmilik :</label>
            <table border="0"><tr><td>   
                        <%--<a href="#" title="Sila klik untuk perincian maklumat" onclick="popUpHakmilik('${actionBean.idHakmilikinduk}')" >${actionBean.idHakmilikinduk}</a> <br />--%>
                        <c:forEach items="${actionBean.hakmilikList}" var="line">
                            <c:if test="${line.kodStatusHakmilik.kod eq 'D'}">
                                <c:if test="${line.idHakmilik ne null}"> <a href="#" title="Sila klik untuk perincian maklumat" onclick="popUpHakmilik('${line.idHakmilik}')" >${line.idHakmilik}</a></c:if> <br />
                                <c:if test="${line.idHakmilik eq null}"> Tiada Data </c:if>
                            </c:if>
                        </c:forEach>
                    </td></tr></table>
            </p>  
            <br>
        </fieldset>
    </div>
    <div id="perincianPihak">
        <p></p>
    </div>
</s:form>