<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN"
    "http://www.w3.org/p/html4/loose.dlabel">
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<table width="100%" bgcolor="green">
    <tr>
        <td width="50%" height="20" ><div style="float:left;" class="formtitle">KUTIPAN CUKAI</div></td>
        <td width="50%"height="20" ><div style="float:right;" class="formtitle">REV001-01-31</div></td>
    </tr>
</table>
<s:form beanclass="etanah.view.stripes.KutipanHasilActionBean">
<s:hidden name="akaun.noAkaun"/>
<s:hidden name="hakmilik.idHakmilik"/>
<s:hidden name="${actionBean.jumCaraBayar}"/>
<font color="red"><b><s:messages/></b></font>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Bayaran</legend>
            <p>
                <label>Nombor Akaun :</label>
                ${actionBean.account}&nbsp;
            </p>
            <p>
                <label>Nombor Hakmilik :</label>
                ${actionBean.idHakmilik}&nbsp;
            </p>
            <p>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.transList}" pagesize="4" cellpadding="0" cellspacing="0" requestURI="kutipanHasil" id="line">
                        <display:column title="No." sortable="true"><div align="center">${line_rowNum}</div></display:column>
                        <display:column property="infoAudit.tarikhMasuk" title="Tarikh" sortable="true" sortName="infoAudit.tarikhMasuk" format="{0,date,dd/MM/yyyy}"/>
                        <display:column property="kodTransaksi.nama" title="Jenis Transaksi" sortable="true" sortName="kodTransaksi.nama"/>
                        <display:column property="kodTransaksi.kod" title="Kod Transaksi" sortable="true" sortName="kodTransaksi.kod"/>
                        <display:column class="number" property="amaun" title="Amaun (RM)" sortable="true" sortName="baki" format="{0,number, 0.00}"/>
                        <display:footer>
                            <tr>
                                <td colspan="4" align="right"><div align="right"><b>Jumlah (RM) : </b></div></td>
                                <td class="number"><fmt:formatNumber value="${actionBean.jumlahCaj}" pattern="0.00"/></td>
                            </tr>
                            <tr>
                                <td colspan="4" align="right"><div align="right"><b>Jumlah Yang Dibayar (RM) : </b></div></td>
                                <td class="number"><fmt:formatNumber value="${actionBean.jumCaraBayar}" pattern="0.00"/></td>
                            </tr>
                            <tr>
                                <td colspan="4" align="right"><div align="right"><b>Baki Yang Perlu Dipulangkan (RM) : </b></div></td>
                                <td class="number"><fmt:formatNumber value="${actionBean.total}" pattern="0.00"/></td>
                            </tr>
                        </display:footer>
                    </display:table>
                </div>
            </p>
        </fieldset>
    </div>

    <table border="0" bgcolor="green" width="100%">
        <tr>
            <td align="right">
                <s:submit name=" " value=" Cetak Resit " class="btn" onclick="doSubmit(this.form, this.name, 'page_div');"/>
                <s:submit name="kembali" value="Kembali" class="btn"/>
            </td>
        </tr>
    </table>
</s:form>