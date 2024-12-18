<%-- 
    Document   : jualan_pelan_1
    Created on : May 20, 2010, 4:45:33 PM
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
        var form = $(f).formSerialize();
        var report = 'HSLResitJualanPelan_MLK.rdf';
        window.open("${pageContext.request.contextPath}/reportGeneratorServlet?"+form+"&reportName="+report+"&report_p_id_kew_dok="+id1, "eTanah",
        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700")
    }
    
    function RunProgram(strUserID, strJawatan, strNoResit){
        var objShell = new ActiveXObject("WScript.Shell");
        var sysVar = objShell.Environment("System");
        //alert(sysVar("eTanahGIS"));
        objShell.Run(sysVar("eTanahGIS") + " " + strUserID + " " + strJawatan + " " + strNoResit);
    }

</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<div align="center"><table style="width:99.2%" bgcolor="green">
    <tr><td width="100%" height="20" ><div style="background-color: green;" align="center">
                <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Pembelian Pelan</font>
    </div></td></tr>
</table></div>

<s:form beanclass="etanah.view.stripes.hasil.JualanPelanActionBean" id="jualan_pelan">
    <s:messages />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Bayaran</legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.senaraiTransaksi}" id="line">
                    <display:column title="Bil."><div align="center">${line_rowNum}.</div></display:column>
                    <display:column title="Nama Pembayar" property="dokumenKewangan.isuKepada" style="width:300"/>
                    <display:column title="No. Resit" property="dokumenKewangan.idDokumenKewangan"/>
                    <display:column title="Urusan" style="width:250">${line.kodTransaksi.kod} - ${line.kodTransaksi.nama}</display:column>
                    <%--<display:column title="Kuantiti" property="kuantiti"/>--%>
                    <display:column title="Caj (RM)" property="amaun"/>
                </display:table>
            </div>
        </fieldset>
    </div>

    <div align="center"><table style="width:99.2%" bgcolor="green">
        <tr>
            <td align="right">
                <s:submit name="main" value="Menu Utama" class="btn" />
                <s:button name=" " onclick="edit(this.form, '${actionBean.receipt}');" value="Cetak" class="btn"/>
                <s:button onclick="RunProgram ('${actionBean.pguna.idPengguna}','${line.dokumenKewangan.idDokumenKewangan}','${actionBean.idKewDok}')" name="" value="CMS" class="btn"/>
            </td>
        </tr>
    </table></div>
</s:form>