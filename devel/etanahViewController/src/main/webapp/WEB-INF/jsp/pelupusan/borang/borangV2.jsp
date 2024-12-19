<%-- 
    Document   : borangV2
    Created on : Jun 17, 2013, 11:47:35 AM
    Author     : afham
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<%-- carian hakmilik start--%>

<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<%-- carian hakmilik end--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    $(document).ready(function() {
        // carian hakmilik start
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'}),dialogInit('Carian Hakmilik'),$('#catatanKegunaan').hide();
    });


        
    function openFrame(type){
        doBlockUI();
        window.open("${pageContext.request.contextPath}/pelupusan/borangV2?openFrame&type="+type, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
    }
        
    function refreshV2Borang(type){
        var url = '${pageContext.request.contextPath}/pelupusan/borangV2?refreshpage&type='+type;
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
        doUnBlockUI();
    }
        
 
</script>
<s:form beanclass="etanah.view.stripes.pelupusan.BorangPelupusanV2ActionBean" name="form">
    <s:errors/>
    <s:messages/>
    <div class="subtitle">       
        <fieldset class="aras1">
            <legend>Maklumat Borang 
                <span style="float:right"> 
                    <c:if test="${actionBean.disBorangController.tBorang}">
                        <a onclick="openFrame('tBorang');" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                        </c:if>                    
                </span>
            </legend>
                <div class="content" align="center">
                    <table class="tablecloth">
                        <tr>
                            <th>Item</th><th>Amaun Bayaran</th>
                            <c:if test="${!empty actionBean.listPermohonanTuntutanKos}">
                                <c:set var="i" value="1" />
                        </tr>       
                            <tr>
                                <c:forEach items="${actionBean.listPermohonanTuntutanKos}" var="line">
                                    <td>
                                        ${line.item}
                                    </td>
                                    <td>
                                        RM <s:format value="${line.amaunTuntutan}" formatPattern="#,###,##0.00"/>
                                    </td>
                                </tr>

                                <c:set var="i" value="${i+1}" />        
                            </c:forEach>
                                <tr>
                                    <td>Jumlah </td>
                                    <td>RM <s:format value="${actionBean.total}" formatPattern="#,###,##0.00"/></td>
                                </tr>
                        </c:if>
                        <c:if test="${empty actionBean.listPermohonanTuntutanKos}">
                            <tr>
                                <td colspan="2">
                                        Tiada Rekod
                                    </td>
                                </tr>
                        </c:if>
                    </table>
                </div>     
        </fieldset>
    </div>
</s:form>
