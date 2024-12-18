<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN"
    "http://www.w3.org/p/html4/loose.dlabel">
<div align="center"><table style="width:99.2%" bgcolor="green">
        <tr>
            <td width="100%" height="20" >
                <div style="background-color: green;" align="center">
                    <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Pindahan Akaun Amanah</font>
                </div>
            </td>
        </tr>
    </table></div>
<s:form beanclass="etanah.view.stripes.hasil.PindaanAkaunAmanahActionBean">
    <fieldset class="aras1">
        <legend>Maklumat Kutipan Baru</legend>
        <div class="content" align="center">
            <p>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.transList}" id="line">
                    <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                    <display:column property="kodTransaksi.nama" title="Jenis Urusan" sortable="true"/>
                    <display:column property="akaunKredit.hakmilik.idHakmilik" title="Nombor Hakmilik"/>
                    <display:column property="dokumenKewangan.idDokumenKewangan" title="Nombor Resit" />
                    <display:column property="dokumenKewangan.infoAudit.tarikhMasuk" title="Tarikh Bayar" format="{0,date,dd/MM/yyyy}"/>
                    <display:column style="text-align:right" property="dokumenKewangan.amaunBayaran" title="Amaun (RM)" format="{0,number, 0.00}"/>
                    <%--<display:column>
                        <s:button name=" " value="Cetak" class="btn"/>
                    </display:column>--%>
                </display:table>
            </div>
            </p>
        </div>
        <br>
    </fieldset>

    <fieldset class="aras1">
        <legend></legend>
        <div align="center"><table style="width:99.2%" bgcolor="green">
            <tr>
                <td align="right"><s:submit name="kembali" value="Keluar" class="btn"/></td>
            </tr>
        </table></div>
    </fieldset>

</s:form>