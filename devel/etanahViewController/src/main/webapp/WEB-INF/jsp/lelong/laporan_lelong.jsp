<%-- 
    Document   : laporan_lelong
    Created on : Sep 2, 2010, 12:54:59 PM
    Author     : mazurahayati.yusop
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript">

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
    <s:form beanclass="etanah.view.stripes.lelong.LaporanLelongActionBean">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Senarai Laporan
                </legend>
                <p style="color:red">
                    *Sila klik pada (<img alt='Klik Untuk Papar' title="Klik Untuk Papar" border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem' id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';"/>) untuk memaparkan laporan
                    <!--                </p>-->
                <div align="center">
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
                                            <a onclick="popup('${pageContext.request.contextPath}/lelong/laporanlelong/requestParam?namaReport=${actionBean.senaraiReportName[count-1]}');" onmouseover="this.style.cursor='pointer';">
                                                <img alt='Klik Untuk Papar' title="Klik Untuk Papar" border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif'/>
                                            </a>
                                        </p>
                                    </div>
                                </td>
                                <c:set value="${count +1}" var="count"/>
                            </tr>
                        </c:forEach>
                        </tr>
                    </table>
                </div>
                </p>
            </fieldset>
        </div>
    </s:form>
</div>
