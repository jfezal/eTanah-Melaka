<%-- 
    Document   : sejarah_kemaskini_maklumat_akaun
    Created on : May 5, 2011, 3:22:32 PM
    Author     : abdul.hakim
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<div align="center"><table style="width:99.2%" bgcolor="green">
        <tr>
            <td width="100%" height="20" >
                <div style="background-color: green;" align="center">
                        <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Kemaskini Maklumat Cukai</font>
                </div>
            </td>
        </tr>
    </table></div>
<s:form beanclass="etanah.view.stripes.hasil.KemaskiniMaklumatAkaunActionBean" id="kemaskini_akaun" name="form1">
<div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Bayaran</legend>
            <div class="content" align="center">
                <%--<s:text name="kodNegeri" value="${actionBean.kodNegeri}" id="kodNegeri" style="display:none;"/>--%>
                <display:table class="tablecloth" name="${actionBean.senaraiSejarah}" pagesize="25" cellpadding="0" cellspacing="0" requestURI="/hasil/kemaskini_data" id="line">
                    <display:column title="Bil."><div align="center"> ${line_rowNum}.</div></display:column>
                    <display:column property="perkara" title="Perkara" style="width:250px;text-align:left;"/>
                    <display:column property="dataLama" title="Nilai Lama"/>
                    <display:column property="dataBaru" title="Nilai Baru"/>
                    <display:column property="catatan" title="Catatan"/>
                    <display:column property="pengguna.nama" title="Dimasuk Oleh"/>
                    <display:column property="tarikhMasuk" title="Tarikh" sortable="true" sortName="tarikhMasuk" format="{0,date,dd/MM/yyyy HH:mm:ss}"/>
                </display:table>

            </div>
        </fieldset>
    </div>
</s:form>