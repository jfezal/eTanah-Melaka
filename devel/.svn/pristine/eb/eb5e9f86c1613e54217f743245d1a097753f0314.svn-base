<%-- 
    Document   : ansuran_1
    Created on : Jan 19, 2010, 9:51:30 AM
    Author     : abdul.hakim
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>

<stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<div align="center"><table style="width:99.2%" bgcolor="green">
    <tr>
        <td width="100%" height="20" >
            <div style="background-color: green;" align="center">
                <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: SPOC Khusus</font>
            </div>
        </td>
    </tr>
</table></div>
<s:errors />
<s:form beanclass="etanah.view.stripes.hasil.AnsuranActionBean" id="ansuran">
    <%--<s:hidden name="hakmilik.idHakmilik" />--%>
    <%--<div><font color="black"><b>Langkah 1: Carian Nombor Hakmilik</b></font></div><br>--%>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Carian Akaun</legend>
            <p>
                <em><font color="black">Sila Masukkan Nombor Akaun atau ID Hakmilik</font></em>
            </p>

            <%--<c:if test="${actionBean.kodNegeri eq 'melaka'}">--%>
                <p>
                    <label>Nombor Akaun :</label>
                    <s:text name="akaun.noAkaun" size="25" maxlength="20" onblur="this.value=this.value.toUpperCase();"/>
                </p>
            <%--</c:if>--%>

            <p>
                <label>ID Hakmilik :</label>
                <s:text name="hakmilik.idHakmilik" id="hakmilik" size="25" maxlength="20" onblur="this.value=this.value.toUpperCase();"/>
            </p>
            <div align="right">
                <s:submit name="Step2" value="Cari" class="btn"/>
                <s:button name=" " value="Isi Semula" class="btn" onclick="clearText('ansuran');"/>&nbsp;
            </div>
            <br>
        </fieldset>
    </div>
    <p></p>
    <c:if test="${actionBean.flag}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Akaun</legend>
                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.senaraiAkaun}" requestURI="/hasil/ansuran" id="line">
                            <display:column title="Bil">${line_rowNum}</display:column>
                            <display:column title="Pilih">
                                <div align="center"><s:radio name="radio" value="${line.noAkaun}"/></div>
                            </display:column>
                            <display:column title="ID Hakmilik">
                                <c:if test="${line.hakmilik eq null}">-</c:if>
                                <c:if test="${line.hakmilik ne null}">${line.hakmilik.idHakmilik}</c:if>
                            </display:column>
                            <display:column property="noAkaun" title="Nombor Akaun" class="akaun"/>
                            <display:column property="baki" title="Amaun Semasa (RM)" format="{0,number, 0.00}" style="text-align:right;"/>
                            <display:column property="amaunMatang" title="Amaun Matang (RM)" format="{0,number, 0.00}" style="text-align:right;"/>
                            <display:column title="Status">
                                ${line.baki >= line.amaunMatang ? 'Matang' : 'Tidak Matang'}
                            </display:column>
                        </display:table>
                    </div>
                <br>
            </fieldset>
        </div>

        <div align="center"><table style="width:99.2%" bgcolor="green">
            <tr>
                <td width="50%" height="20" align="right">
                    <s:submit name="Step3" value="Seterusnya" class="btn"/>&nbsp
                </td>
            </tr>
        </table></div>
    </c:if>
</s:form>
