<%-- 
    Document   : paparan_status_permohonan
    Created on : Jul 10, 2015, 11:41:08 AM
    Author     : faidzal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.utility.KioskMainActionBean" name="carianPelan" id="carianPelan">
    <s:messages/>
    <s:errors/>

<!--created by haqqiem-->
    <div >
        <br>  <br>
        <br>
        <br>

        <table  align ="center" >
            <tr>
                <td></td>&nbsp;
            </tr>    <tr>
                <td></td>&nbsp;
            </tr>    <tr>
                <td></td>&nbsp;
            </tr>    <tr>
                <td></td>
            </tr>    <tr>
                <td></td>
            </tr>    <tr>
                <td></td>
            </tr>    <tr>
                <td></td>
            </tr>    <tr>
                <td></td>
            </tr>    <tr>
                <td></td>
            </tr>    <tr>
                <td></td>
            </tr>    <tr>
                <td></td>
            </tr>    <tr>
                <td></td>
            </tr>    <tr>
                <td></td>
            </tr>    <tr>
                <td></td>
            </tr>    <tr>
                <td></td>
            </tr>    <tr>
                <td></td>
            </tr>    <tr>
                <td></td>
            </tr>    <tr>
                <td></td>
            </tr>    <tr>
                <td></td>
            </tr>    <tr>
                <td></td>
            </tr>    <tr>
                <td></td>
            </tr>    <tr>
                <td></td>
            </tr>    <tr>
                <td></td>
            </tr>
            <tr>
                <td>
                    <a href="${pageContext.request.contextPath}/kiosk/status_permohonan" ><img src="${pageContext.request.contextPath}/images/btn_mohon.png" height="130" width="330" border="0"></a></td>
            </tr>    <tr>
                <td></td>
            </tr>    <tr>
                <td></td>
            </tr>    
            <tr>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="${pageContext.request.contextPath}/kiosk/tukarganti" ><img src="${pageContext.request.contextPath}/images/btn_tukar.png" height="125" width="330" border="0"></a></td>
            </tr>
        </table>



    </div>

    <!--    <div class ="subtitle">
            <div class="content">
                <fieldset class ="aras1">
                     <p align="center">
                    <table  align ="center">
                        <tr>
                            <td><a href="${pageContext.request.contextPath}/kiosk/status_permohonan" ><img src="${pageContext.request.contextPath}/images/stsmohon.png" height="100" width="300"></a></td>
                        </tr>
                        <tr><td></td></tr>
                        <tr>
                            <td><a href="${pageContext.request.contextPath}/kiosk/tukarganti" ><img src="${pageContext.request.contextPath}/images/tkrganti.png" height="100" width="300"></a></td>
                        </tr>
                    </table>
    
                </p>
                   
                </fieldset>
            </div>
    
        </div> -->
</s:form>
