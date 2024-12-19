<%-- 
    Document   : komisyen_jualan
    Created on : Mar 3, 2010, 11:58:59 AM
    Author     : mazurahayati
--%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript">

    function showReport(){
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top:  ($(window).height() - 50) /2 + 'px',
                left: ($(window).width() - 50) /2 + 'px',
                width: '50px'
            }
        });
        var url = '${pageContext.request.contextPath}/lelong/komisyen_Jualan?genReport';
        $.ajax({
            type:"GET",
            url : url,
            dataType : 'html',
            error : function(xhr, ajaxOptions, thrownError) {
                alert('Terdapat masalah teknikal. Sila hubungi Pentadbir Sistem.');
                $.unblockUI();
            },
            success : function(data) {
                if(data.indexOf('Laporan telah dijana')==0){
                    alert(data);
                    $('#folder').click();
                }else {
                    alert(data);
                    $('#urusan').click();
                }
                $.unblockUI();
            }
        });
    }


</script>
<s:form beanclass="etanah.view.stripes.lelong.KomisyenJualanActionBean">
    <s:messages/>
    <s:errors/>
    <%--<c:if test="${actionBean.show eq true}" >--%>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Komisyen Jualan
                </legend><br>

                <c:if test="${actionBean.view eq false}">
                    <p align="center"><br>
                        <object type="application/pdf" width="1000" height="1200" data="${actionBean.iframeURL}#navpanes=0 &toolbar=0&messages=0"/>
                    </p>&nbsp;
                </c:if>
                <c:if test="${actionBean.view eq true}">
                    <p align="center"><br>
                        <object type="application/pdf" data="${pageContext.request.contextPath}/lelong/view/${actionBean.idDokumen}#navpanes=0 &toolbar=0&messages=0" width="1000" height="1200"/>
                        <iframe src="${pageContext.request.contextPath}/lelong/komisyen_Jualan?viewPdf#navpanes=0 &toolbar=0&messages=0" width="1000" height="1200" scrolling=yes/>
                    </p>&nbsp;
                </c:if>
                    <p align="center">
                        <s:button name="genReport" id="report" value="Jana Komisyen Jualan" class="longbtn" onclick="showReport();"/>&nbsp;
                    </p>
            </fieldset>
        </div>
    <%--</c:if>--%>
</s:form>