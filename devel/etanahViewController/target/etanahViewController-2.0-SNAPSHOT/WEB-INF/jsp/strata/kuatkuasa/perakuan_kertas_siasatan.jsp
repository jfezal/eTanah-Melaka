<%-- 
    Document   : perakuan_kertas_siasatan
    Created on : Jul 13, 2011, 3:47:51 PM
    Author     : zadhirul.farihim
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<s:form beanclass="etanah.view.strata.PerakuanActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Perakuan Kertas Siasatan</legend>
            <div class="content" align="center">
                <table>
                    <c:if test="${edit}">
                    <tr>
                        <td valign="top">
                            <b>Perakuan :</b></td>
                        <td><s:textarea  rows="7" cols="80" name="ulasan" class="normal_text"></s:textarea></td>
                    </tr>
                    <tr><td>&nbsp;</td></tr>
                    <tr>
                        <td colspan="2"><center><s:button name="simpanPerakuan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form,this.name,'page_div')"/></center></td>
                    </tr>
                    </c:if>
                    <c:if test="${display}">
                    <tr>
                        <td valign="top">
                            <b>Perakuan :</b></td>
                        <td><s:textarea  rows="7" cols="80" name="ulasan" class="normal_text" readonly="readonly"></s:textarea></td>
                    </tr>
                    <tr><td>&nbsp;</td></tr>
                  
                    </c:if>
                </table>
            </div>
        </fieldset>
    </div>
</s:form>
