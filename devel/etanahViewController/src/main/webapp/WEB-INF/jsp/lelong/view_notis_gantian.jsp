<%-- 
    Document   : view_16H
    Created on : Jan 18, 2011, 10:13:19 AM
    Author     : mdizzat.mashrom
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript">

    function showReport(idPermohonan) {

 
        var url = '${pageContext.request.contextPath}/lelong/utilitiSemakanPembida?genReport&idPermohonan=' + idPermohonan;
        $.ajax({
            type: "GET",
            url: url,
            dataType: 'html',
            error: function(xhr, ajaxOptions, thrownError) {
                alert('Terdapat masalah teknikal. Sila hubungi Pentadbir Sistem.');
                $.unblockUI();
            },
            success: function(data) {
                if (data.indexOf('Laporan telah dijana') == 0) {
                    alert(data);
                    $('#folder').click();
                } else {
                    alert(data);
                    $('#urusan').click();
                }
                $.unblockUI();
            }
        });
    }


</script>
<s:form beanclass="etanah.view.stripes.lelong.UtilitiSemakanPembidaActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Notis Gantian
            </legend><br>
            <p align="center">
                <s:button name="genReport" id="report" value="Jana Notis Gantian" class="longbtn" onclick="showReport('${actionBean.idPermohonan}');"/>&nbsp;
            </p>
            <c:if test="${semak16H eq false}">
                <p>
                    <font size="2" color="Red">*</font> Sila Klik Butang Jana Dokumen Sebelum Selesai
                </p>
            </c:if>
            <c:if test="${actionBean.view eq false}">
                <p align="center"><br>
                    <object type="application/pdf" width="900" height="1100" data="${actionBean.idDokumen}#navpanes=0 &toolbar=0&messages=0"/>
                </p>&nbsp;
            </c:if>
            <c:if test="${actionBean.view eq true}">
                <p align="center"><br>
                    <object type="application/pdf" data="${pageContext.request.contextPath}/lelong/view/${actionBean.idDokumen}#navpanes=0 &toolbar=0&messages=0" width="1000" height="1200"/>
                    <%--<iframe src="${pageContext.request.contextPath}/lelong/view_16H?viewPdf#navpanes=0 &toolbar=0&messages=0" width="1000" height="1200" scrolling=yes/>--%>
                </p>&nbsp;
            </c:if>




        </fieldset>
    </div>
</s:form>
