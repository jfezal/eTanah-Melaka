<%-- 
    Document   : history
    Created on : Apr 28, 2015, 10:22:14 AM
    Author     : haqqiem
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>

<div align="center">
    <table style="width:100%" bgcolor="green">
        <tr><td width="100%" height="20" >
                <div style="background-color: green;" align="center">
                    <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Utility Push History Data</font>
                </div>
            </td>
        </tr>
    </table>
</div>
<s:form beanclass="etanah.view.stripes.hasil.HistoryTransactionActionBean" id="history" name="form">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Carian Data</legend>
            <p>
                <label>Nombor Akaun :</label>
                <s:text name="akaun.noAkaun" id="akaun" size="20" />
            </p>
            <p>
                <label>ID Hakmilik :</label>
                <s:text name="hakmilik.idHakmilik" id="idhm" size="20" 
                        onkeyup="this.value=this.value.toUpperCase();" maxlength="17"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:submit name="Step2" class='btn' value="Cari" id='search'/>
                <s:button name="" class='btn' value="Isi Semula" onclick="clearText('history');"/>&nbsp;
            </p><br>
        </fieldset>
    </div><br>
    <c:if test="${actionBean.flag}">
        <div align="center">
            <fieldset class="aras1">
                <legend>Hasil Carian</legend>
                <display:table class="tablecloth" name="${actionBean.senaraiTransaksi}" pagesize="10" cellpadding="0" cellspacing="0" requestURI="/hasil/history" id="line">
                    <display:column title="Bil"> <div align="center">${line_rowNum}</div></display:column>
                    <display:column title="Tarikh Transaksi" >
                        <c:if test="${line.dokumenKewangan.noRujukanManual ne null}">
                            <fmt:formatDate value="${line.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy hh:mm aa"/>
                            <fmt:formatDate value="${line.dokumenKewangan.tarikhTransaksi}" pattern="dd/MM/yyyy hh:mm aa"/>
                        </c:if>
                        <c:if test="${line.dokumenKewangan.noRujukanManual eq null}">
                            <fmt:formatDate value="${line.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy hh:mm aa"/>
                        </c:if>
                    </display:column>
                    <display:column  title="Jenis Transaksi" >${line.kodTransaksi.kod} - ${line.kodTransaksi.nama}</display:column>
                    <c:if test="${line.dokumenKewangan.noRujukanManual ne null}">
                        <display:column  title="No Resit"  >
                            ${line.dokumenKewangan.noRujukanManual}&nbsp;
                        </display:column>
                    </c:if>
                    <c:if test="${line.dokumenKewangan.noRujukanManual eq null}">
                        <display:column  title="No Resit"  >
                            ${line.dokumenKewangan.idDokumenKewangan}&nbsp;
                        </display:column>
                    </c:if>
                    <display:column property="untukTahun" title="Tahun" />
                    <display:column title="Akaun Debit (RM)" style="text-align:right">
                        <c:if test="${line.status.kod eq 'A' and line.kodTransaksi.kod ne '99000'
                                      and line.kodTransaksi.kod ne '99001'and line.kodTransaksi.kod ne '99002'
                                      and line.kodTransaksi.kod ne '99051'and line.kodTransaksi.kod ne '99050'
                                      and line.kodTransaksi.kod ne '99003'and line.kodTransaksi.kod ne '99030' 
                                      and line.kodTransaksi.kod ne '99004' }">
                            <fmt:formatNumber value="${line.amaun}" pattern="#,##0.00"/>
                        </c:if>
                        <c:if test="${line.status.kod ne 'A'}">
                            -
                        </c:if>
                    </display:column>
                    <display:column title="Akaun Kredit (RM)" style="text-align:right">
                        <c:if test="${line.status.kod ne 'A'}">
                            <fmt:formatNumber value="${line.amaun}" pattern="#,##0.00"/>
                        </c:if>
                        <c:if test="${line.status.kod eq 'A' and line.kodTransaksi.kod eq '99002'
                                      or line.kodTransaksi.kod eq '99000'or line.kodTransaksi.kod eq '99001'
                                      or line.kodTransaksi.kod eq '99051'or line.kodTransaksi.kod eq '99050'
                                      or line.kodTransaksi.kod eq '99003'or line.kodTransaksi.kod eq '99030'
                                      or line.kodTransaksi.kod eq '99004'}">
                            <fmt:formatNumber value="${line.amaun}" pattern="#,##0.00"/>
                        </c:if>
                    </display:column>
                    <display:column property="status.nama" title="Status"/>
                    <display:column title="Dimasuk Oleh" >
                        <c:out value="${line.infoAudit.dimasukOleh.nama}"/>
                    </display:column>
                </display:table>
                <table style="width:100%" bgcolor="green">
                    <tr>
                        <td align="right">
                            <s:submit name="Step3" value="Push Data" id="nextBtn" class="btn"/>
                        </td>
                    </tr>
                </table>
            </fieldset>
        </div>
    </c:if>
</s:form>
