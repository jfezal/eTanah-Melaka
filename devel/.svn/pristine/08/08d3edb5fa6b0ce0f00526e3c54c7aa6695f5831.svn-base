<%-- 
    Document   : semak_transaksi_1
    Created on : Apr 15, 2010, 3:10:37 PM
    Author     : abdul.hakim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("#close").click( function(){
            setTimeout(function(){
                self.close();
            }, 100);
        });
    });
</script>

<s:form beanclass="etanah.view.stripes.hasil.SemakanTransaksiActionBean">

    <fieldset class="aras1">
        <legend>Semakan Transaksi Terperinci</legend>
        <p>
            <label>ID Hakmilik :</label>
            ${actionBean.transaksi.akaunKredit.noAkaun}&nbsp;
        </p>
        <p>
            <c:choose>
                <c:when test="${actionBean.dokumenKewangan.noRujukan ne null}">
                    <label>Nombor Resit FPX :</label>
                    ${actionBean.dokumenKewangan.noRujukan}&nbsp;
                </c:when>
                <c:when test="${actionBean.dokumenKewangan.noRujukanManual ne null}">
                    <label>Nombor Resit Kew. 38 :</label>
                    ${actionBean.dokumenKewangan.noRujukanManual}&nbsp;
                </c:when>
                <c:otherwise>
                    <label>Nombor Resit :</label>
                    ${actionBean.dokumenKewangan.idDokumenKewangan}&nbsp;
                </c:otherwise>
            </c:choose>
        </p>
        <p>
            <label>Status Pembayaran :</label>
            <c:choose>
                <c:when test="${actionBean.transaksi.status.kod eq 'T'}">Sah</c:when>
                <c:otherwise>Tidak Sah</c:otherwise>
            </c:choose>
        </p>
        <p>
            <label>Tarikh Bayaran :</label>
            <fmt:formatDate value="${actionBean.transaksi.dokumenKewangan.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy hh:mm aa"/>
            &nbsp;
        </p>
        <p>
            <label>Daerah Bayaran :</label>
            ${actionBean.transaksi.dokumenKewangan.cawangan.daerah.nama}&nbsp;
        </p>
        <p>
            <label>Jumlah (RM) :</label>
            <fmt:formatNumber value="${actionBean.transaksi.dokumenKewangan.amaunBayaran}" pattern="0.00" />&nbsp;
        </p>
        <br>
        <div class="content" align="center">
            <display:table class="tablecloth" name="${actionBean.dkbList}" requestURI="kutipanHasil" id="line">
                <display:column title="Bil." ><div align="center">${line_rowNum}.</div></display:column>
                <display:column property="caraBayaran.kodCaraBayaran.nama" title="Cara Bayaran"/>
                <display:column title="Bank / Agensi Pembayaran">
                    <c:if test="${line.caraBayaran.bank.nama eq null}">-</c:if>
                    <c:if test="${line.caraBayaran.bank.nama ne null}">${line.caraBayaran.bank.nama}</c:if>
                </display:column>
                <display:column title="Cawangan">
                    <c:if test="${line.caraBayaran.bankCawangan eq null}">-</c:if>
                    <c:if test="${line.caraBayaran.bankCawangan ne null}">${line.caraBayaran.bankCawangan}</c:if>
                </display:column>
                <display:column title="Nombor Rujukan">
                    <c:if test="${line.caraBayaran.noRujukan eq null}">-</c:if>
                    <c:if test="${line.caraBayaran.noRujukan ne null}">${line.caraBayaran.noRujukan}</c:if>
                </display:column>
                <display:column property="dokumenKewangan.infoAudit.tarikhMasuk" title="Tarikh Bayaran" format="{0,date,dd/MM/yyyy hh:mm:ss a}"/>
                <display:column property="caraBayaran.amaun" title="Amaun (RM)" style="text-align:right" format="{0,number, 0.00}"/>
            </display:table>
        </div>
        <br>
        <table border="0" bgcolor="green" width="100%">
            <tr>
                <td align="right">
                    <s:button name="" value="Tutup" class="btn" id="close"/>
                </td>
            </tr>
        </table>
    </fieldset>

</s:form>