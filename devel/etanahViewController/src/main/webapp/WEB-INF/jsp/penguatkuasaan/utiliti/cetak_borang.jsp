<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    function doSubmit(reportName){
        $("#reportname").val(reportName);
        var form = $("#form").formSerialize();
        var url = form.replace(/&/g, "%26");
        window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url="+url, "eTanah",
        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");  
    } 
    function doPrintViaApplet(docId) {
        var a = document.getElementById('applet');
        a.doPrint(docId.toString());
    }
</script>
<div class="subtitile"> 
    <s:errors/>
    <s:messages/>
    <div class="displaytag">
        <s:form beanclass="etanah.view.penguatkuasaan.utiliti.CetakBorangKosongActionBean" id="form">
            <s:hidden id="reportname" name="reportName"/>
            <s:hidden id="reportname" name="report_p_kodcaw" value="${actionBean.pengguna.kodCawangan.kod}"/>
            <fieldset class="aras1">
                <legend>Cetak Borang Kosong - Senarai Dokumen</legend>
                <font size="2" color="black">Petunjuk :</font>
                <p>            
                    <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                         width="20" height="20" /> - <font size="2" color="black">Papar Dokumen</font>
                    &nbsp;<b>|</b>&nbsp;
                    <img src="${pageContext.request.contextPath}/pub/images/icons/_active__print.png"
                         width="20" height="20" /> - <font size="2" color="black">Cetak Dokumen</font>
                    &nbsp;
                </p>        
                <br/>
                <c:set var="rowNum" value="0"/>
                <c:set var="rowNum2" value="0"/>
                <table class="tablecloth" style="width:60%">
                    <thead>
                        <tr>
                            <th width="5%">Bil</th>
                            <th>Nama Dokumen</th>
                            <th width="10%">Papar & Cetak</th>
                            <!--<th width="8%">Cetak</th>-->
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${actionBean.map}" var="rep" varStatus="loop">
                            <tr>
                                <td>${loop.index+1}</td>
                                <td>${rep.key}</td>
                                <td align="center">
                                    <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                         height="30" width="30" alt="papar"
                                         onmouseover="this.style.cursor='pointer';" onclick="doSubmit('${rep.value}')" title="Papar Dokumen"/>
                                </td>
<!--                                <td align="center">
                                    <img src="${pageContext.request.contextPath}/pub/images/icons/_active__print.png"
                                         height="30" width="30" alt="papar"
                                         onmouseover="this.style.cursor='pointer';" onclick="doPrintViaApplet('${row.dokumen.idDokumen}');" title="Papar Dokumen"/>
                                </td>-->
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <br/>
            </fieldset>
            <applet code="PrintApplet.class" ARCHIVE="${pageContext.request.contextPath}/PrintApplet.jar,
                    ${pageContext.request.contextPath}/commons-httpclient-2.0.2.jar,
                    ${pageContext.request.contextPath}/commons-logging.jar,
                    ${pageContext.request.contextPath}/swingx-1.6.jar,
                    ${pageContext.request.contextPath}/log4j-1.2.12.jar,
                    ${pageContext.request.contextPath}/jpedal_demo.jar"
                    codebase = "."
                    name     = "applet"
                    id       = "applet"
                    width    = "1"
                    height   = "1"
                    align    = "middle">
                <param name="uploadAction" value="<%=request.getContextPath()%>/PrintServlet"/>
                <param name ="kodNegeri" value="05">
                <param name ="method" value="pdfp">
                <!--      <param name ="disabledWatermark" value="true"/>--> 
                <!--<param name ="idPengguna" value="${idPengguna}"/>-->
                <%
                    Cookie[] cookies = request.getCookies();
                    StringBuffer sb = new StringBuffer();
                    for (int i = 0; i < cookies.length; i++) {
                        sb.setLength(0);
                        sb.append(cookies[i].getName());
                        sb.append("=");
                        sb.append(cookies[i].getValue());
                %>
                <param name="Cookie<%= i%>" value="<%= sb.toString()%>"><%
                    }
                %>
            </applet>
        </s:form>
    </div>
</div>
