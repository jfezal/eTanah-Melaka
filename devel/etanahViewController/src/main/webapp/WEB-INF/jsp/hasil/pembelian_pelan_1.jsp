<%--
    Document   : pembelian_pelan_1
    Created on : Mar 15, 2010, 4:34:42 PM
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
    function edit(f, id1){
        var queryString = $(f).formSerialize()
        window.open("${pageContext.request.contextPath}/hasil/beli_pelan?cetak&"+queryString+"&idKew="+id1, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }

    function runP(x,y,z){
        alert(x);
        alert(y);
        alert(z);
    }
    function RunProgram(strUserID, strJawatan, strNoResit)
    {
        var objShell = new ActiveXObject("WScript.Shell");
        var sysVar = objShell.Environment("System");
        //alert(sysVar("eTanahGIS"));
        objShell.Run(sysVar("eTanahGIS") + " " + strUserID + " " + strJawatan + " " + strNoResit);
    }
    function cetakPelan(f){

        var form = $(f).formSerialize();
        var report = null;
        report = 'HSLResitJualanPelan.rdf';

        var param = document.getElementById('id_kew_dok');

        window.open("${pageContext.request.contextPath}/reportGeneratorServlet?"+form+"&reportName="+report+"&report_p_id_kew_dok="+param.value, "eTanah",
        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700")
    }

</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<table width="100%" bgcolor="green">
    <tr><td width="100%" height="20" ><div style="background-color: green;" align="center">
                <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Pembelian Pelan</font>
            </div></td></tr>
</table>

<s:form beanclass="etanah.view.stripes.hasil.PembelianPelanActionBean" id="beli_pelan">
    <s:messages />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Bayaran</legend>
            <div class="content" align="center">
                <table class="tablecloth">
                    <tr>
                        <th>Bil.</th>
                        <th>Nama Pembeli</th>
                        <th>No. Resit</th>
                        <th>Urusan</th>
                        <th>Kuantiti</th>
                        <th>Caj (RM)</th>
                    </tr>
                    <tr>
                        <td>1.</td>
                        <td>${actionBean.dokumenKewangan.isuKepada}</td>
                        <td>${actionBean.dokumenKewangan.idDokumenKewangan}</td>
                        <td>
                            <c:forEach items="${actionBean.transList}" var="senarai">
                                <c:out value="${senarai.kodTransaksi.kod}"/> -
                                <c:out value="${senarai.kodTransaksi.nama}"/><br>
                            </c:forEach>
                        </td>
                        <td>
                            <c:forEach items="${actionBean.transList}" var="senarai">
                                <c:out value="${senarai.kuantiti}"/><br>
                            </c:forEach>
                        </td>
                        <td>
                            <div align="right">
                                <c:forEach items="${actionBean.transList}" var="senarai">
                                    <fmt:formatNumber pattern="0.00"><c:out value="${senarai.amaun}"/></fmt:formatNumber><br>
                                </c:forEach>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="5"><div align="right"><b>Jumlah (RM)</b></div></td>
                        <td><div align="right">${actionBean.dokumenKewangan.amaunBayaran}</div></td>
                    </tr>
                </table>
                <s:text name="dokumenKewangan.idDokumenKewangan"  value="${actionBean.dokumenKewangan.idDokumenKewangan}" id="id_kew_dok" style="display:none"/>
            </div>
        </fieldset>
    </div>

    <table border="0" bgcolor="green" width="100%">
        <tr>
            <td align="right">
                <s:submit name="main" value="Menu Utama" class="btn" />
                <%--<s:button name=" " onclick="edit(this.form, '${actionBean.dokumenKewangan.idDokumenKewangan}');" value="Cetak" class="btn"/>--%>
                <s:button name=" " onclick="cetakPelan(this.form);" value="Cetak" class="btn"/>
                <button onclick="RunProgram ('${actionBean.pguna.idPengguna}','${actionBean.jawatan}','${actionBean.idKewDok}')" class="btn">CMS</button>
                <%--<s:button name=" " value="GIS" class="btn" onclick="RunProgram '${actionBean.pguna.idPengguna}','${actionBean.jawatan}','${actionBean.idKewDok}'"/>--%>
            </td>
        </tr>
    </table>
</s:form>