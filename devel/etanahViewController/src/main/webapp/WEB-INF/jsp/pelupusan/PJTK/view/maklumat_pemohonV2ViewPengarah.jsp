<%-- 
    Document   : maklumat_pemohonV2EditPengarah
    Created on : Jul 23, 2013, 11:11:39 AM
    Author     : afham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Maklumat Pengarah</title>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script src="<%= request.getContextPath()%>/pub/js/ui.datepicker-ms.js" type="text/javascript"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>
<script type="text/javascript">

</script>
<body>
   
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <s:form beanclass="etanah.view.stripes.pelupusan.LelongAwamActionBean" name="form">
        <s:errors/>
        <s:messages/>
        <s:hidden name="idPemohon" id="idPemohon"/>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Ahli Lembaga Pengarah</legend>
                <div class="content" align="center">
                    <table class="tablecloth" border="0">
                        <tr>
                            <th>Bil</th><th>Nama</th><th>Nombor Pengenalan</th><th>Alamat</th><th>Poskod</th><th>Negeri</th>
                        </tr>
                        <c:if test="${!empty actionBean.listPengarah}">
                            <c:set var="i" value="1" />
                            <tr>
                                <c:forEach items="${actionBean.listPengarah}" var="line">
                                    <td>
                                        ${i}

                                    </td>
                                    <td>
                                        ${line.nama}

                                    </td>
                                    <td>
                                        ${line.noPengenalan}
                                    </td>
                                    <td>
                                        ${line.alamat1}
                                        <c:if test="${line.alamat2 ne null}">, </c:if>
                                        ${line.alamat2}
                                        <c:if test="${line.alamat3 ne null}">, </c:if>
                                        ${line.alamat3}
                                        <c:if test="${line.alamat4 ne null}">, </c:if>
                                        ${line.alamat4}
                                    </td>
                                    <td>
                                        ${line.poskod}
                                    </td>
                                    <td>
                                        ${line.kodNegeri.nama}
                                    </td>
                                </tr>

                                <c:set var="i" value="${i+1}" />
                            </c:forEach>
                        </c:if>
                    </table>
                </div>
                <br/>

            </fieldset>
        </div>


        <div class="subtitle">
            <fieldset class="aras1">
                <table  width="100%" border="0">
                    <tr>
                        <td align="center">
                            <s:button name="tutup" value="Tutup" class="btn" onclick="self.close();"/>
                        </td>
                    </tr>
                </table>   
            </fieldset>
        </div>

    </s:form>
</body>


