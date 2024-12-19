<%-- 
    Document   : view_draft_warta
    Created on : Nov 26, 2013, 12:28:00 PM
    Author     : Hayyan
    TXN        : PWGSA N9
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">

</script>

<s:form beanclass="etanah.view.stripes.pelupusan.SediaDraftWartaActionBean">
    <s:messages/>
    <s:hidden name="permohonanLaporanPelan.idMohonlaporKws" />
    <c:if test="${view and actionBean.permohonan.kodUrusan.kod eq 'PWGSA'}">
        <fieldset class="aras1">
            <legend>Maklumat Draf Warta</legend>
                <div class="content">
                    <table border="0">
                        <tr>
                            <td><label>Nama Rancangan :</label></td>
                            <td>${actionBean.permohonan.sebab}</td>
                        </tr><tr>
                            <td><label>Tarikh Kuat Kuasa :</label></td>
                            <td><s:format formatPattern="dd/MM/yyyy" value="${actionBean.permohonanLaporanPelan.tarikhWarta}"/></td>
                        </tr><tr>
                            <td><label>Seksyen :</label></td>
                            <td>${actionBean.permohonanLaporanPelan.catatan}</td>
                        </tr>
                    </table>
                </div>
        </fieldset>
        <br><br>
        <fieldset class="aras1">
            <legend>Jadual</legend>
                <div class="content" align="center">                    
                    <display:table class="tablecloth" name="${actionBean.mohanHakmilikList}" pagesize="5" requestURI="/pelupusan/sedia_draf_war" id="line">
                        <display:column title="Daerah"><c:if test="${line.permohonan.sebab ne null}"><font style="text-transform: capitalize; text-align: center; width: 100px">${line.permohonan.cawangan.daerah.nama}</font></c:if></display:column>
                        <display:column title="Mukim"><c:if test="${line.permohonan.sebab ne null}"><font style="text-transform: capitalize; text-align: center; width: 150px">${line.bandarPekanMukimBaru.nama}</font></c:if></display:column>
                        <display:column title="No PW"><c:if test="${line.permohonan.sebab ne null}"><font style="text-transform: capitalize; text-align: center; width: 100px">${actionBean.trizabPermohonan.noPW}</font></c:if></display:column>
                        <%--<display:column title="Nama Rancangan" style="width:500px" > <c:if test="${line.permohonan.sebab ne null}">${line.permohonan.sebab}</c:if></display:column>--%>
                        <display:column title="Luas"><c:if test="${line.permohonan.sebab ne null}"><font style="text-transform: capitalize; text-align: center; width: 70px"><fmt:formatNumber pattern="#,##0.0000" value="${line.luasTerlibat}"/></font></c:if>${actionBean.hakmilikPermohonan.kodUnitLuas.nama}</display:column>
                    </display:table>
                </div>
        </fieldset>
    </c:if>
</s:form>