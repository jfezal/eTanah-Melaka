<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN"
    "http://www.w3.org/p/html4/loose.dlabel">
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<script type="text/javascript">
    $(document).ready(function() {
        var tr = ${fn:length(actionBean.transList)};
        var rs = ${fn:length(actionBean.senaraiResit)};
        if(tr > 0){$('#trans').show();$('#resit').hide()}
        else if(rs > 0){$('#resit').show();$('#trans').hide()}
        else{$('#resit').hide();$('#trans').hide()}
    });
</script>

<s:form beanclass="etanah.view.stripes.hasil.PelarasanCukaiActionBean" id="pelarasan_cukai">
    <s:errors />
    <div align="center"><table style="width:99.2%" bgcolor="green">
        <tr><td width="100%" height="20" >
                <div style="background-color: green;" align="center">
                        <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Pelarasan Kod Cukai</font>
                </div>
            </td></tr>
    </table></div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Carian Resit</legend>
            <p class=instr>Sila Masukkan Nombor Resit atau Tarikh untuk membuat carian.</p>
            <p>
                <label for=""><em>*</em>Nombor Resit :</label>
                <s:text name="dokumenKewangan.idDokumenKewangan" maxlength="20"/>
            </p>

            <p><label for="">&nbsp;</label>ATAU</p>

            <p>
                <label for=""><em>*</em>Tarikh Dari :</label>
                <s:text name="dateFrom" maxlength="20" class="datepicker"/>
            </p>

            <p>
                <label for=""><em>*</em>Tarikh Hingga :</label>
                <s:text name="dateTo" maxlength="20" class="datepicker"/>
            </p>

            <div align="right">
                <s:submit name="search" value="Cari" class="btn"/>
                <s:button name=" " value="Isi Semula" class="btn" onclick="clearText('pelarasan_cukai');"/>&nbsp;
            </div>
            <br>
        </fieldset>
    </div>
    <p></p>
    <div class="subtitle" id="resit">
        <fieldset class="aras1">
            <legend>Hasil Carian</legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.senaraiResit}" cellpadding="0" cellspacing="0" pagesize="10" requestURI="/hasil/pelarasan_cukai" id="line">
                    <display:column title="Pilih">
                        <div align="center"><s:radio name="idResit" value="${line.idDokumenKewangan}" /></div>
                    </display:column>
                    <display:column title="Bil">${line_rowNum}</display:column>
                    <display:column title="Nombor Resit" property="idDokumenKewangan"/>
                    <display:column title="Dibayar Oleh" property="isuKepada"/>
                    <display:column title="Amaun (RM)" style="text-align:right;">
                        <fmt:formatNumber value="${line.amaunBayaran}" pattern="#,###,##0.00"/>
                    </display:column>
                    <display:column title="Tarikh Masuk">
                        <fmt:formatDate value="${line.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy hh:mm:ss a"/>
                    </display:column>
                    <display:column property="status.nama" title="Status"/>
                </display:table>
            </div>
            <table border="0" align="right" bgcolor="green" width="100%">
                <tr>
                    <td align="right">
                        <s:submit name="next" value="Seterusnya" class="btn"/>
                        <s:submit name="kembali" value="Kembali" class="btn"/>
                    </td>
                </tr>
            </table>
        </fieldset>
        
    </div>

    <div class="subtitle" id="trans">
        <fieldset class="aras1">
            <legend>Hasil Carian</legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.transList}" id="line">
                    <display:column title="Pilih">
                        <div align="center"><s:radio name="idTransaksi" value="${line.idTransaksi}" /></div>
                    </display:column>
                    <display:column title="Bil">${line_rowNum}</display:column>
                    <display:column title="Jenis Transaksi">
                        ${line.kodTransaksi.kod} - ${line.kodTransaksi.nama}
                    </display:column>
                    <display:column title="Amaun (RM)" style="text-align:right">
                        <c:if test="${line.dokumenKewangan ne null}">
                            <fmt:formatNumber value="${line.amaun}" pattern="#,###,##0.00"/>
                        </c:if>
                    </display:column>
                    <display:column title="Tarikh Masuk">
                        <fmt:formatDate value="${line.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy hh:mm:ss a"/>
                    </display:column>
                    <display:column property="status.nama" title="Status"/>
                </display:table>
            </div>

            <table border="0" align="right" bgcolor="green" width="100%">
                <tr>
                    <td align="right">
                        <s:submit name="terus" value="Seterusnya" class="btn"/>
                        <s:submit name="kembali" value="Kembali" class="btn"/>
                    </td>
                </tr>
            </table>
        </fieldset>
    </div>
</s:form>