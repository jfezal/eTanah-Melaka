<%--
    Document   : dev_kertas_pertimbanganPTG
    Created on : Jun 22, 2010, 3:02:37 PM
    Author     : NageswaraRao
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%--<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />--%>

<style type="text/css">
    #tdLabel {
        color:#003194;
        display:block;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        margin-left:30px;
        margin-right:0.5em;
        text-align:right;
        width:32em;
    }

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
        width:20em;
    }
</style>

<s:form beanclass="etanah.view.stripes.pembangunan.KertasPertimbanganPTGActionBean">
    <s:messages/>
    <s:errors/>
    <s:hidden name="kandunganK.kertas.idKertas"/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend></legend>
            <div class="content" align="center">
                <table border="0" width="80%">
                    <tr><td align="center"><b>(KERTAS PERTIMBANGAN PENGARAH TANAH DAN GALIAN NEGERI SEMBILAN)</b></td></tr>
                </table><br><br>
                <table border="0" width="80%">                    
                    <c:if test="${edit}">
                        <tr><td><s:textarea name="tajuk" rows="5" cols="150"/></td></tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td><b><font style="text-transform:uppercase">${actionBean.tajuk} &nbsp;</font></b></td></tr>
                    </c:if>
                    <tr><td><b>1. TUJUAN</b></td></tr>
                    <c:if test="${edit}">
                        <tr><td><s:textarea rows="5" cols="150" name="tujuan"/></td></tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td><font style="text-transform:capitalize">1.1 &nbsp; ${actionBean.tujuan}</font></td></tr>
                    </c:if>
                    <tr><td><b>2. LATAR BELAKANG</b></td></tr>
                    <c:if test="${edit}">
                        <tr><td><s:textarea rows="5" cols="150" name="latarBelakang"/></td></tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td><font style="text-transform:capitalize">2.1 &nbsp; ${actionBean.latarBelakang}</font></td></tr>
                    </c:if>
                    <tr><td><b>3. BUTIR-BUTIR TANAH </b></td></tr>
                    <tr><td>
                            <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0" id="tbl1">
                                <display:column title="Bil" sortable="true" style="vertical-align:baseline">${tbl1_rowNum}</display:column>
                                <display:column title="Daerah" property="hakmilik.daerah.nama" class="daerah" style="vertical-align:baseline"/>
                                <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" style="vertical-align:baseline"/>
                                <display:column title="Jenis & No Hakmilik" style="vertical-align:baseline">
                                    ${tbl1.hakmilik.lot.nama} ${tbl1.hakmilik.kodHakmilik.kod} ${tbl1.hakmilik.noHakmilik}
                                </display:column>
                                <display:column title="Nombor Lot/PT" style="vertical-align:baseline">
                                    ${tbl1.hakmilik.lot.nama} ${tbl1.hakmilik.noLot}
                                </display:column>
                                <display:column title="Luas" style="vertical-align:baseline"><fmt:formatNumber pattern="#,##0.0000" value="${tbl1.hakmilik.luas}"/>&nbsp;${tbl1.hakmilik.kodUnitLuas.nama}</display:column>
                                <%--   <display:column title="Tempoh Hakmilik" style="vertical-align:baseline" property="hakmilik.tempohPegangan"/> --%>
                                <display:column title="Tuan Tanah" style="vertical-align:baseline">
                                    <c:forEach items="${tbl1.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                                        <c:if test="${senarai.jenis.kod eq 'PM'}">
                                            <c:out value="${senarai.pihak.nama}"/>
                                        </c:if>
                                    </c:forEach>
                                </display:column>
                                <%-- newly added  --%>
                                <display:column title="Kategori Kegunaan Tanah" property="hakmilik.kategoriTanah.nama" style="vertical-align:baseline"/>
                                <display:column title="Syarat Nyata Tanah" property="hakmilik.syaratNyata.syarat" style="vertical-align:baseline"/>
                                <display:column title="Sekatan Kepentingan" property="hakmilik.sekatanKepentingan.sekatan" style="vertical-align:baseline"/>
                                <%-- newly added  --%>
                                <display:column title="Cukai Tanah" property="hakmilik.cukai" style="vertical-align:baseline"/>
                            </display:table>
                        </td></tr>
                        <c:if test="${edit}">
                        <tr><td><b><font style="text-transform: uppercase">4. HURAIAN PENTADBIR TANAH  ${actionBean.pejTanah}&nbsp;</font></b></td></tr>
                        <tr><td><s:textarea name="huraianPentadbir" rows="5" cols="150"/></td></tr>
                        <tr><td><b><font style="text-transform: uppercase">5. SYOR PENTADBIR TANAH  ${actionBean.pejTanah}&nbsp;</font></b></td></tr>
                        <tr><td><s:textarea name="syorPentadbir" rows="5" cols="150"/></td></tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td><b><font style="text-transform: uppercase">4. HURAIAN PENTADBIR TANAH  ${actionBean.pejTanah}&nbsp;</font></b></td></tr>
                        <tr><td><font style="text-transform:capitalize">4.1 ${actionBean.huraianPentadbir}&nbsp;</font></td></tr>
                        <tr><td><b><font style="text-transform: uppercase">5. SYOR PENTADBIR TANAH  ${actionBean.pejTanah}&nbsp;</font></b></td></tr>
                        <tr><td><font style="text-transform:capitalize">5.1 ${actionBean.syorPentadbir}&nbsp;</font></td></tr>
                    </c:if>
                    <c:if test="${edit}">
                        <tr><td><b>6. HURAIAN TIMBALAN PENGARAH TANAH DAN GALIAN NEGERI SEMBILAN</b></td></tr>
                        <tr><td><s:textarea name="huraianPtg" rows="5" cols="150" class="normal_text"/></td></tr>
                        <tr><td><b>7. SYOR TIMBALAN PENGARAH TANAH DAN GALIAN NEGERI SEMBILAN</b></td></tr>
                        <tr><td><s:textarea name="syorPtg" rows="5" cols="150" class="normal_text"/></td></tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td><b>6. HURAIAN TIMBALAN PENGARAH TANAH DAN GALIAN NEGERI SEMBILAN</b></td></tr>
                        <tr><td>6.1 ${actionBean.huraianPtg}&nbsp;</td></tr>
                        <tr><td><b>7. SYOR TIMBALAN PENGARAH TANAH DAN GALIAN NEGERI SEMBILAN</b></td></tr>
                        <tr><td>7.1 ${actionBean.syorPtg}&nbsp;</td></tr>
                    </c:if>
                    <tr><td><b>8. KEPUTUSAN PENGARAH TANAH DAN GALIAN NEGERI SEMBILAN</b></td></tr>
                    <c:if test="${edit}">
                        <tr><td>8.1 Permohonan ini : &nbsp; <b><s:radio name="keputusanPTG" value="L"/>&nbsp;Diluluskan / <s:radio name="keputusanPTG" value="T"/>&nbsp;Ditolak</b></td></tr>
                        <tr><td><b><font style="text-transform:uppercase">Catatan : &nbsp;</font></b></td></tr>
                        <tr><td><s:textarea name="ulasanPTG" rows="5" cols="150"/></td></tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <c:if test="${actionBean.keputusanPTG eq 'L'}">
                            <tr><td><b>8.1 Permohonan ini : Diluluskan &nbsp;</b></td></tr>
                        </c:if>
                        <c:if test="${actionBean.keputusanPTG eq 'T'}">
                            <tr><td><b>8.1 Permohonan ini : Ditolak &nbsp;</b></td></tr>
                        </c:if>
                        <tr><td><b><font style="text-transform:uppercase">Catatan : &nbsp;</font></b></td></tr>
                        <tr><td><font style="text-transform:capitalize">${actionBean.ulasanPTG} &nbsp;</font></td></tr> 
                    </c:if>
                </table>
                <c:if test="${edit}">
                    <p align="center">
                        <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </p>
                </c:if>
            </div>
        </fieldset>
    </div>
</s:form>