<%-- 
    Document   : cetak_semula_surat_akuan
    Created on : Apr 16, 2010, 5:08:42 PM
    Author     : abdul.hakim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN"
    "http://www.w3.org/p/html4/loose.dlabel">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>

<script type="text/javascript">
    function cetak2(f, x){
        var queryString = $(f).formSerialize()
        window.open("${pageContext.request.contextPath}/hasil/cetak_semula_surat_akuan?cetak&"+queryString+"&idMohon="+x, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }

    function cetak(f, id1){
        var form = $(f).formSerialize();
        var report = null;
        var negeri = '${actionBean.kodNegeri}';
        if(negeri == 'melaka'){
            report = 'SPOCCetakanSemulaSuratAkuan_MLK.rdf';
        }else{
            report = 'SPOCCetakanSemulaResitPOMO.rdf';
        }

        window.open("${pageContext.request.contextPath}/reportGeneratorServlet?"+form+"&reportName="+report+"&report_p_id_kew_dok="+id1, "eTanah",
        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700")
    }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.hasil.CetakSemulaSuratAkuanActionBean">
    <s:errors />
    <div class="subtitle">
        <table style="width:99.2%" bgcolor="green">
            <tr><td width="100%" height="20" ><div style="background-color: green;" align="center">
                        <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Cetak Semula Resit</font>
            </div></td></tr>
        </table>
        <fieldset class="aras1">
            <legend>Maklumat Resit</legend>
            <p>
                <label>Daerah Terimaan :</label>
                ${actionBean.pengguna.kodCawangan.daerah.nama}&nbsp;
            </p>

            <p>
                <label>Nombor Resit :</label>
                ${actionBean.dokumenKewangan.idDokumenKewangan}&nbsp;
            </p>

            <%--<p>
                <label>Nombor Akaun Cukai Tanah :</label>
                <c:if test="${actionBean.akaun eq null}"> - </c:if>
                <c:if test="${actionBean.akaun ne null}">${actionBean.akaun.noAkaun}</c:if>
            </p>--%>

            <%--<p>
                <label>ID Hakmilik :</label>
                <c:if test="${actionBean.akaun eq null}"> - </c:if>
                <c:if test="${actionBean.akaun ne null}">${actionBean.akaun.hakmilik.idHakmilik}</c:if>
            </p>--%>

            <p>
                <label>Nombor Kaunter :</label>
                ${actionBean.pengguna.idKaunter}&nbsp;
            </p>

            <p>
                <label>Operator :</label>
                ${actionBean.pengguna.idPengguna}&nbsp;
            </p>

            <p>
                <label>Nama Operator :</label>
                ${actionBean.pengguna.nama}&nbsp;
            </p>

            <p>
                <label>Amaun Diterima :</label>
                <fmt:formatNumber value="${actionBean.dokumenKewangan.amaunBayaran}" type="currency" currencySymbol="RM"/>
                &nbsp;
            </p>

            <p>
                <label>Cara Bayaran :</label>
                <table>
                    <c:forEach items="${actionBean.senaraiCaraBayaran}" var="senarai">
                        <tr>
                            <c:if test="${senarai.caraBayaran.kodCaraBayaran eq null}"><td><font size="2">-</font></td></c:if>
                            <c:if test="${senarai.caraBayaran.kodCaraBayaran.nama ne null}">
                                <td><font size="2"><c:out value="${senarai.caraBayaran.kodCaraBayaran.nama}"/></font></td>
                            </c:if>
                        <tr>
                    </c:forEach>
                </table>&nbsp;
            </p>

            <p>
                <label>Tarikh :</label>
                <fmt:formatDate value="${actionBean.dokumenKewangan.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy"/>
                &nbsp;
            </p>

            <p>
                <label>Masa :</label>
                <fmt:formatDate value="${actionBean.dokumenKewangan.infoAudit.tarikhMasuk}" pattern="hh:mm:ss aa"/>
                &nbsp;
            </p>
            <br>
            <div align="center">
                <display:table class="tablecloth" name="${actionBean.senaraiTransaksi}" id="line">
                    <display:column title="Bil." style="text-align:center">
                        ${line_rowNum}.
                    </display:column>
                    <display:column  title="Transaksi" style="width:800;">
                       ${line.kodTransaksi.kod} - ${line.kodTransaksi.nama}
                    </display:column>
                    <display:column  title="Amaun (RM)" style="text-align:right">
                        <fmt:formatNumber value="${line.amaun}" pattern="0.00" />
                    </display:column>
                </display:table>
               <br>
            </div>
        </fieldset>
    </div>
    <table border="0" bgcolor="green" style="width:99.2%">
        <tr>
            <td align="right">
                <s:button name=" " value="Cetak" class="btn" onclick="cetak(this.form, '${actionBean.permohonan.idPermohonan}')"/>
                <s:submit name="main" value="Kembali" class="btn"/>
            </td>
        </tr>
    </table>
</s:form>
