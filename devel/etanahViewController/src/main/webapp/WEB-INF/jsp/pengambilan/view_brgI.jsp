<%-- 
    Document   : view_brgI
    Created on : 15-Nov-2011, 11:14:32
    Author     : nordiyana
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
        var url = '${pageContext.request.contextPath}/pengambilan/view_brgI?genReport';
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
<s:form beanclass="etanah.view.stripes.pengambilan.viewBrgIActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Borang I
            </legend><br>

            <%--<c:if test="${actionBean.view eq true}">--%>
                <p align="center"><br>
                    <object type="application/pdf" data="${pageContext.request.contextPath}/dokumen/view/${actionBean.idDokumen}#navpanes=0 &toolbar=0&messages=0" width="1000" height="1200"/>

                    <%--<iframe src="${pageContext.request.contextPath}/pengambilan/view_BrgI?viewPdf#navpanes=0 &toolbar=0&messages=0" width="1000" height="1200" scrolling=yes/>--%>
                </p>&nbsp;
            <%--</c:if>--%>
        </fieldset>
    </div>
</s:form>
