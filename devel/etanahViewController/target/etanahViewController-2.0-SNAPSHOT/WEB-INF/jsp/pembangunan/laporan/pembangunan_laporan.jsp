<%-- 
    Document   : pembangunan_laporan
    Created on : 17 DEC 2013, 11:32:28 AM
    Author     : syafiq adam
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript">
    <%--function popupParam(nama){
        window.open("${pageContext.request.contextPath}/laporanSpoc/requestParam?namaReport="+nama, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }--%>
        function popup(url)
        {
            params  = 'width='+screen.width;
            params += ', height='+screen.height;
            params += ', top=0, left=0'
            params += ', fullscreen=yes';
            params += ', directories=no';
            params += ', location=no';
            params += ', menubar=no';
            params += ', resizable=no';
            params += ', scrollbars=yes';
            params += ', status=no';
            params += ', toolbar=no';
            newwin=window.open(url,'PopUp', params);
            if (window.focus) {newwin.focus()}
            return false;
        }
</script>

<div id="laporan">
    <s:form beanclass="etanah.view.pembangunan.laporan.PembangunanLaporanActionBean">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Senarai Laporan
                </legend>
                <p style="color:red">
                    *Sila klik pada (<img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem' id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';"/>) untuk memaparkan laporan<br/>
                </p>
                <br>
                <table class="tablecloth">
                    <tr style="width: 100%">
                        <th>Bil</th>
                        <th>Laporan</th>
                        <th>Papar</th>

                        <c:set value="1" var="count"/>
                        <c:forEach items="${actionBean.senaraiReport}" var="report">
                        <tr>
                            <td>${count}
                            </td>
                            <td>${report}
                            </td>
                            <td><div align="center">
                                    <p align="center">
                                        <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                             onclick="popup('${pageContext.request.contextPath}/dev/laporanpembangunan/requestParam?namaReport=${actionBean.senaraiReportName[count-1]}&urusan=${report}');" onmouseover="this.style.cursor='pointer';">
                                    </p>
                                </div>
                            </td>
                        </tr>
                        <c:set value="${count +1}" var="count"/>
                    </c:forEach>
                    </tr>
                </table>
                <br>
            </fieldset>
        </div>
    </s:form>
</div>
