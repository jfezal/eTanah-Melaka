<%-- 
    Document   : release
    Created on : Jan 26, 2015, 4:35:46 PM
    Author     : haqqiem
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript">
</script>
<div align="center"><table style="width:99.2%" bgcolor="green">
        <tr>
            <td width="100%" height="20" >
                <div style="background-color: green;" align="center">
                    <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Release User</font>
                </div>
            </td>
        </tr>
    </table></div>
<s:form beanclass="etanah.view.stripes.hasil.ReleaseResitActionBean" id="release" name="form">
    <s:messages/>
    <s:errors/>
    <s:text name="dokumenKewanganSiri.idSiri" value="${actionBean.dokumenKewanganSiri.idSiri}" style="display:none;"/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Pengguna</legend>
            <p><label>ID Pengguna :</label>
                <s:text name="pengguna.idPengguna"/>
                <s:submit name="Step2" value="Cari"  class="btn"/>
            </p><br>
            <c:if test="${actionBean.flag}">
                <p>
                    <label>&nbsp;</label>
                    Sila klik kemaskini. <s:submit name="Step3" value="Kemaskini"  class="btn"/>
                </p>
            </c:if>
        </fieldset>
    </div>
</s:form>
