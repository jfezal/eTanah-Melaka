<%--
    Document   : laporanPenandaanSempadan
    Created on : Jun 1, 2010, 12:50:07 PM
    Author     : massita
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript">
    function ajaxLink(link, update) {
        $.get(link, function(data) {
            $(update).html(data);
            $(update).show();
        });
        return false;
    }
</script>

<s:form beanclass="etanah.view.stripes.pengambilan.LaporanPenandaanSempadanActionBean">
    <s:messages />
        <s:errors/>
        <div  id="hakmilik_details">
            <fieldset class="aras1">
                <legend >
                    <b>Maklumat Hakmilik Permohonan</b>
                </legend><br/>
                <p align="center">
                    <display:table class="tablecloth" name="${actionBean.permohonan.senaraiHakmilik}" pagesize="5" cellpadding="0" cellspacing="0"
                                   requestURI="/pengambilan/LaporanPenandaanSempadan" id="line">
                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                        <display:column title="ID Hakmilik">
                            <s:link beanclass="etanah.view.stripes.pengambilan.LaporanPenandaanSempadanActionBean"
                                    event="hakmilikDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                                <s:param name="idHakmilik" value="${line.hakmilik.idHakmilik}"/>${line.hakmilik.idHakmilik}
                            </s:link>
                        </display:column>
                        <display:column title="No Lot/No PT" >${line.hakmilik.lot.nama}${line.hakmilik.noLot}</display:column>
                        <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                        <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                    </display:table>
                </p>
                <br/><br/>
            </fieldset>
            <c:if test="${actionBean.hakmilik ne null}">
                <fieldset class="aras1">
                    <legend>Laporan penandaan sempadan</legend><br />
                    <div class="subtitle">
                        <s:label for="ikatan">Ikatan Batu Sempadan :</s:label>
                        <s:radio name="ikatan" value="y" checked="checked"/><font color="#004478" size="2">Ya </font>&nbsp;&nbsp;
                        <s:radio name="ikatan" value="t"/><font color="#004478" size="2">Tidak</font>
                        <br/>
                        <c:if test="${piketTanda}">
                            <s:label for="piketTanda">Piket Tanda :</s:label>
                            <s:radio name="piketTanda" value="y"/><font color="#004478" size="2">Ya </font>&nbsp;&nbsp;
                            <s:radio name="piketTanda" value="t"/><font color="#004478" size="2">Tidak</font>
                        </c:if><br />
                        <c:if test="${offset}">
                            <s:label for="offset">Offset kepada bangunan/pagar :</s:label>
                            <s:radio name="offset" value="y"/><font color="#004478" size="2">Ya </font>&nbsp;&nbsp;
                            <s:radio name="offset" value="t"/><font color="#004478" size="2">Tidak</font>
                        </c:if><br/>
                        <s:label for="catatan">Catatan :</s:label>
                        <s:textarea name="catatan" rows="3" cols="50" />
                    </div>
                    <br /><br />
                </fieldset>
                    <br />
                    <div align="center">
                        <s:hidden name="idHakmilik" value="${actionBean.idHakmilik}"/>
                        <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </div>
            </c:if>
        </div>
</s:form>
