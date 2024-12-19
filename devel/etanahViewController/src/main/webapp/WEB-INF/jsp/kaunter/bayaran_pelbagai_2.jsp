<%-- 
    Document   : bayaran_pelbagai_2
    Created on : Apr 13, 2010, 2:44:24 PM
    Author     : abdul.hakim
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloths.css"/>


<script type="text/javascript">
    function cetak1(f, id2){
        var queryString = $(f).formSerialize();
        window.open("${pageContext.request.contextPath}/hasil/bayaran_pelbagai?cetakResit&"+queryString+"&idKew="+id2, "eTanah",
                "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=900,height=300");
    }
</script>
<stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<stripes:form beanclass="etanah.view.strata.BayaranUpahUkurActionBean" id="bayaran_pelbagai">
<stripes:errors />

<div class="subtitle">
    <fieldset class="aras1">
        <legend>Maklumat Pembayaran</legend>
        <div class="content" align="center">
            <display:table class="tablecloth" name="${actionBean.dkbList}" requestURI="kutipanHasil" id="line">
                <display:column title="Bil." ><div align="center">${line_rowNum}</div></display:column>
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
                <display:column property="caraBayaran.amaun" title="Amaun (RM)" style="text-align:right" format="{0,number, 0.00}"/>
                <display:column title="Cetak Pengesahan Cek/WP/KW" style="text-align:center">
                    <c:choose >
                        <c:when test="${line.caraBayaran.kodCaraBayaran.kod eq 'T'
                                                or line.caraBayaran.kodCaraBayaran.kod eq 'KK'
                                                or line.caraBayaran.kodCaraBayaran.kod eq 'VS'}">
                            <stripes:button name=" " disabled="true" value="Cetak" class="btn"/>
                        </c:when>
                        <c:otherwise><stripes:button name=" " value="Cetak" class="btn" onclick="cetak(this.form, '${line.caraBayaran.idCaraBayaran}');"/></c:otherwise>
                    </c:choose>
                </display:column>
            </display:table>
        </div>
    </fieldset>
</div>

<div class="subtitle">
    <fieldset class="aras1">
        <legend>Maklumat Pembayaran</legend>
        <div class="content" align="center">
            <display:table class="tablecloth" name="${actionBean.senaraiTransaksi}" requestURI="kutipanHasil" id="line">
                <display:column title="Bil." ><div align="center">${line_rowNum}</div></display:column>
                <display:column property="kodTransaksi.nama" title="Transaksi" style="width:300px"/>
                <display:column property="dokumenKewangan.idDokumenKewangan" title="Nombor Resit"/>
                <display:column title="Tarikh Transaksi" property="infoAudit.tarikhMasuk" format="{0,date,dd/MM/yyyy hh:mm:ss aa}"/>
                <display:column title="Kuantiti" property="kuantiti"/>
                <display:column property="amaun" title="Amaun (RM)" style="text-align:right" format="{0,number, 0.00}"/>
                <display:column title="Cetak">
                    <stripes:button name="cetak" value="Cetak" class="btn" onclick="cetak1(this.form, '${line.dokumenKewangan.idDokumenKewangan}');"/>
                </display:column>
            </display:table>
        </div>
    </fieldset>
</div>


</stripes:form>
