<%--
    Document   : cetak_semula_resit_pelan
    Created on : Mar 24, 2010, 4:32:56 PM
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
    function cetak(f,id1){

        var form = $(f).formSerialize();
        var report = null;
        var negeri = '${actionBean.kodNegeri}';
        if(negeri == 'melaka'){
            report = 'HSLResitJualanPelan_MLK.rdf';
        }else{
            report = 'HSLResitJualanPelan.rdf';
        }

        var url = "reportName="+report+"%26report_p_id_kew_dok="+id1;

        window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url="+url, "eTanah",
        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
    }
    
    function RunProgram(strUserID, strPsswd, strNoResit){
        var objShell = new ActiveXObject("WScript.Shell");
        var sysVar = objShell.Environment("System");
        //alert(sysVar("eTanahGIS"));
        objShell.Run(sysVar("eTanahGIS") + " " + strUserID + " " + strPsswd + " " + strNoResit);
    }

</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<div align="center"><table style="width:99.2%"  bgcolor="green">
    <tr><td width="100%" height="20" ><div style="background-color: green;" align="center">
                <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Cetak Semula Pelan</font>
    </div></td></tr>
</table></div>

<s:form beanclass="etanah.view.stripes.hasil.CetakPelanActionBean" id="cetak_pelan">
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
                        <td><div align="right"><fmt:formatNumber value="${actionBean.dokumenKewangan.amaunBayaran}" pattern="0.00"/></div></td>
                    </tr>
                </table>
            </div>
        </fieldset>
    </div>

    <div align="center"><table style="width:99.2%"  bgcolor="green">
        <tr>
            <td align="right">
                <s:submit name="main" value="Menu Utama" class="btn" />
                <s:button name=" " onclick="cetak(this.form, '${actionBean.dokumenKewangan.idDokumenKewangan}');" value="Cetak" class="btn"/>
                <s:button onclick="RunProgram ('${actionBean.pengguna.idPengguna}','${actionBean.pengguna.password}','${actionBean.dokumenKewangan.idDokumenKewangan}')" class="btn" name="" value="CMS"/>
            </td>
        </tr>
    </table></div>
</s:form>