<%-- 
    Document   : bayaran_ansuran_1
    Created on : Dec 28, 2010, 4:12:43 PM
    Author     : abdul.hakim
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
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
    <s:form beanclass="etanah.view.stripes.hasil.BayaranAnsuranActionBean" id="bayaran_ansuran">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Carian Akaun</legend>
            <p>
                <em><font color="black">Sila Masukkan ID Hakmilik untuk membuat carian.</font></em>
            </p>

            <p>
                <label>ID Hakmilik :</label>
                <s:text name="hakmilik.idHakmilik" id="hakmilik" size="25" maxlength="20" onblur="this.value=this.value.toUpperCase();"/>
            </p>
            <div align="right">
                <s:submit name="Step2" value="Cari" class="btn"/>
                <s:button name=" " value="Isi Semula" class="btn" onclick="clearText('bayaran_ansuran');"/>&nbsp;
            </div>
            <br>
        </fieldset>
    </div>
    <p></p>
    <%--<c:if test="${actionBean.flag}">--%>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Akaun</legend>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.senaraiAkaun}" pagesize="10" cellpadding="0" cellspacing="0" requestURI="/hasil/bayaran_ansuran" id="line">
                        <display:column title="Bil">${line_rowNum}</display:column>
                        <display:column title="Pilih">
                            <div align="center"><s:radio name="radio" value="${line.hakmilik.idHakmilik}"/></div>
                        </display:column>
                        <display:column title="ID Hakmilik">
                            <c:if test="${line.hakmilik eq null}">-</c:if>
                            <c:if test="${line.hakmilik ne null}">${line.hakmilik.idHakmilik}</c:if>
                        </display:column>
                        <%--<display:column property="noAkaun" title="Nombor Akaun" class="akaun"/>--%>
                        <display:column title="Tarikh Terakhir Pembayaran" style="width:150;">
                            <fmt:formatDate value="${line.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy hh:mm aa" />
                        </display:column>
                        <display:column property="baki" title="Amaun Yang Perlu Dibayar (RM)" format="{0,number, #,###,##0.00}" style="text-align:right;width:150;"/>
                        <display:column title="Status Ansuran">
                            ${line.baki <= 0 ? 'Selesai' : 'Belum selesai'}
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
            </table>
        </div>
    <%--</c:if>--%>
</s:form>