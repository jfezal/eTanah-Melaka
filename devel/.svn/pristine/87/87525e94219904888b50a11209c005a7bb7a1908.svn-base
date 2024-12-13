<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN"
    "http://www.w3.org/p/html4/loose.dlabel">

<div align="center"><table style="width:99.2%" bgcolor="green">
        <tr>
            <td width="100%" height="20" >
                <div style="background-color: green;" align="center">
                    <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Pindahan Akaun Amanah</font>
                </div>
            </td>
        </tr>
    </table></div>

<stripes:errors />
<stripes:form beanclass="etanah.view.stripes.hasil.PindaanAkaunAmanahActionBean" id="akaun_amanah">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Kutipan</legend>
            <p>
                <label>Nombor Akaun Amanah :</label>
                <stripes:text name="akaun.noAkaun" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label>ID Hakmilik :</label>
                <stripes:text name="hakmilik.idHakmilik" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <stripes:submit name="search" value="Cari" class="btn"/>
                <stripes:button name=" " value="Isi Semula" class="btn" onclick="clearText('akaun_amanah');"/>
            </p>
            <br>
        </fieldset>
    </div>
    <p></p>
    <%--<c:if test="${actionBean.visible}">--%>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Hasil Carian</legend>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.accList}" id="line">
                        <display:column title="Bil" style="text-align:center">${line_rowNum}</display:column>
                        <display:column title="Pilih" sortable="true"><div align="center"><stripes:radio name="noAkaun" value="${line.noAkaun}"/></div></display:column>
                        <display:column property="noAkaun" title="Nombor Akaun Amanah"/>
                        <display:column property="hakmilik.idHakmilik" title="ID Hakmilik"/>
                        <%--<display:column property="kodAkaun.nama" title="Jenis Akaun"/>--%>
                        <display:column property="infoAudit.tarikhMasuk" style="text-align:center" title="Tarikh Bayar" format="{0,date,dd/MM/yyyy}"/>
                        <display:column class="number" property="baki" title="Amaun (RM)" format="{0,number, 0.00}" style="text-align:right"/>
                    </display:table>
                </div>
            </fieldset>
        </div>

        <div align="center"><table style="width:99.2%" bgcolor="green">
            <tr>
                <td align="right">
                    <stripes:submit name="details" value="Terus" disabled="${actionBean.btn}" class="btn"/>
                    <stripes:submit name="kembali" value="Kembali" class="btn"/>
                </td>
            </tr>
            </table></div>
    <%--</c:if>--%>
</stripes:form>
