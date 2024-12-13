<%-- 
    Document   : isytihar_jualan
    Created on : Apr 5, 2011, 3:57:00 PM
    Author     : mdizzat.mashrom
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
        var url = '${pageContext.request.contextPath}/lelong/isytihar_jual?genReport';
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
    
    function doViewReport(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }


</script>
<s:form beanclass="etanah.view.stripes.lelong.PrisytiharanJualanActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Perisytiharan Jualan
            </legend><br>
            <c:if test="${actionBean.view eq false}">
                <p align="center"><br>
                    <object type="application/pdf" width="1000" height="1200" data="${actionBean.iframeURL}#navpanes=0 &toolbar=0&messages=0"/>
                </p>&nbsp;
            </c:if>
            <c:if test="${actionBean.view eq true}">
                <p align="center"><br>
                    <c:if test="${semak16H eq false}">
                        <small><s:button name="" value="Papar Perisytiharan Jualan" class="longbtn"  onclick="doViewReport('${actionBean.idDokumen}');"/></small>
                    </c:if>
                    <c:if test="${semak16H eq true}">
                        <object type="application/pdf" data="${pageContext.request.contextPath}/lelong/view/${actionBean.idDokumen}#navpanes=0 &toolbar=0&messages=0" width="1000" height="1200"/>
                    </c:if>
                    <%--<iframe src="${pageContext.request.contextPath}/lelong/isytihar_jual?viewPdf#navpanes=0 &toolbar=0&messages=0" width="1000" height="1200" scrolling=yes/>--%>
                </p>&nbsp;
            </c:if>
            <c:if test="${semak16H eq true}">
                <p align="center">
                    <s:button name="" value="Jana" class="btn"  onclick="showReport()"/>
                    <s:button name="kembali" value="Isi Semula" class="btn"  onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </p>
            </c:if>
        </fieldset>
    </div>
</s:form>