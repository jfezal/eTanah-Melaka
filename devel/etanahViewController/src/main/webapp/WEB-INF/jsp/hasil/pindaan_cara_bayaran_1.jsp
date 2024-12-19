<%--
    Document   : pindahan_cara_bayaran
    Created on : Jan 28, 2010, 9:33:01 AM
    Author     : abdul.hakim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<table width="100%" bgcolor="green">
    <tr>
        <td width="100%" height="20" >
            <div style="background-color: green;" align="center">
                <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Pindaan Cara Bayaran</font>
            </div>
        </td>
    </tr>
</table>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.hasil.PindaanCaraBayaranActionBean">
<s:errors />

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Bayaran</legend>
            <p>
                <label>Tarikh Bayar :</label>
                <fmt:formatDate value="${actionBean.dokumenKewangan.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy" />&nbsp;
            </p>

            <p>
                <label>Nombor Resit :</label>
                ${actionBean.dokumenKewangan.idDokumenKewangan}&nbsp;
            </p>

            <p>
                <label>ID Hakmilik :</label>
                ${actionBean.akaun.hakmilik.idHakmilik}&nbsp;
            </p>

            <p>
                <label>Jumlah Bayaran :</label>
                <fmt:formatNumber value="${actionBean.dokumenKewangan.amaunBayaran}" type="currency" currencySymbol="RM "/>
                &nbsp;
            </p>

            <p>
                <label>Di Bayar Oleh : </label>
                ${actionBean.akaun.pemegang.nama}&nbsp;
            <br>
        </fieldset>
    </div>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Transaksi</legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.senaraiTrans}" id="line">
                    <display:column title="No"><div align="center">${line_rowNum}</div></display:column>
                    <display:column property="infoAudit.tarikhMasuk" title="Tarikh" format="{0,date,dd/MM/yyyy}"/>
                    <display:column title="Transaksi">
                        ${line.kodTransaksi.kod} - ${line.kodTransaksi.nama}
                    </display:column>
                    <display:column property="amaun" title="Amaun (RM)" style="text-align:right" format="{0,number, 0.00}"/>
                    <display:column property="status.nama" title="Status"/>
                </display:table>
            </div>
        </fieldset>
    </div>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Cara Bayaran</legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.senaraiBayaran}" id="line">
                        <display:column title="Pilih" >
                            <div align="center">
                                <s:radio name="idCaraBayar" value="${line.caraBayaran.idCaraBayaran}" />
                            </div>
                        </display:column>
                        <display:column property="caraBayaran.kodCaraBayaran.nama" title="Cara Bayaran"/>
                        <display:column title="Bank">
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
                        <display:column property="caraBayaran.amaun" title="Amaun (RM)" style="text-align:right" format="{0,number, 0.00}"/>
                        <display:column title="Status">
                            <c:if test="${line.caraBayaran.aktif eq 'Y'}">Terima</c:if>
                            <c:if test="${line.caraBayaran.aktif eq 'X'}">Batal</c:if>
                        </display:column>
                    </display:table>
            </div>
        </fieldset>

        <table border="0" bgcolor="green" width="100%">
            <tr>
                <td align="right">
                    <s:submit name="Step4" value="Seterusnya" class="btn"/>
                    <s:submit name="Step2" value="Kembali" class="btn"/>
                </td>
            </tr>
        </table>
    </div>

    
</s:form>