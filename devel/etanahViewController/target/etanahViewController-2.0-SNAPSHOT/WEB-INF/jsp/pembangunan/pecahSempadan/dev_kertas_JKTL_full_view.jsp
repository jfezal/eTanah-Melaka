<%-- 
    Document   : dev_kertas_JKTL_full
    Created on : Apr 13, 2010, 10:10:56 AM
    Author     : nursyazwani
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>

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

<s:form beanclass="etanah.view.stripes.pembangunan.KertasJKTLViewActionBean">
    <s:messages/>
    <s:errors/>
    <s:hidden name="kandunganK.kertas.idKertas"/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend></legend>
            <div class="content" align="center">
                <table border="0" width="80%" cellspacing="10">
                    <tr>
                        <td align="right">
                            <b>KM No: ${actionBean.kmno}</b>
                        </td>
                    </tr>
                    <tr>
                        <td align="center"><b>( MESYUARAT JAWATANKUASA LEMBAGA TANAH LADANG NEGERI SEMBILAN PADA  ${actionBean.tarikhMesyuarat} )</b>
                        </td>
                    </tr>
                    <tr><td><b><font style="text-transform: uppercase">${actionBean.tajuk}</font></b></td></tr>

                    <tr><td><b>1. TUJUAN</b></td></tr>
                    <tr><td>${actionBean.tujuan}</td></tr>

                    <tr><td><b>2. LATAR BELAKANG</b></td></tr>
                    <tr><td>
                            <table cellspacing="10">
                                <tr>
                                    <td><b>2.1 </b></td>
                                    <td>${actionBean.latarBelakang}</td>
                                </tr>
                            </table>
                        </td>
                    </tr>

                    <tr><td><b>3. BUTIR-BUTIR TANAH</b></td></tr>
                    <tr><td>
                            <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0" id="tbl1">
                                <display:column title="Bil" sortable="true" style="vertical-align:baseline">${tbl1_rowNum}</display:column>
                                <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/ Mukim" style="vertical-align:baseline"/>
                                <display:column title="Nombor Lot/PT" style="vertical-align:baseline">
                                    ${tbl1.hakmilik.lot.nama} ${tbl1.hakmilik.noLot}
                                </display:column>
                                <display:column title="No. Hakmilik" property="hakmilik.noHakmilik" style="vertical-align:baseline"/>
                                <display:column title="Tempoh Hakmilik" style="vertical-align:baseline" property="hakmilik.tempohPegangan"/>
                                <display:column title="Luas" style="vertical-align:baseline"><fmt:formatNumber pattern="#,##0.0000" value="${tbl1.hakmilik.luas}"/>&nbsp;${tbl1.hakmilik.kodUnitLuas.nama}</display:column>
                                <display:column title="Kategori" style="vertical-align:baseline">
                                    <c:if test="${tbl1.hakmilik.kategoriTanah.nama ne null}">${tbl1.hakmilik.kategoriTanah.nama}</c:if>
                                    <c:if test="${tbl1.hakmilik.kategoriTanah.nama eq null}">Tiada</c:if>
                                </display:column>                                    
                                <display:column title="Kawasan Rizab" style="vertical-align:baseline">
                                    <c:if test="${tbl1.hakmilik.rizab.nama ne null}">${tbl1.hakmilik.rizab.nama}</c:if>
                                    <c:if test="${tbl1.hakmilik.rizab.nama eq null}">Tiada</c:if>
                                </display:column>
                                <display:column title="Tuan Tanah" style="vertical-align:baseline">
                                    <c:set var="flag1" value="true" />
                                    <c:forEach items="${tbl1.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                                        <c:if test="${senarai.jenis.kod eq 'PM'}">
                                            <c:out value="${senarai.pihak.nama}"/>
                                            <c:set var="flag1" value="false" />
                                        </c:if>                                            
                                    </c:forEach>
                                    <c:if test="${flag1 eq 'true'}">
                                        Tiada
                                    </c:if>
                                </display:column>
                                <display:column title="Syarat Nyata" style="vertical-align:baseline">
                                    <c:if test="${tbl1.hakmilik.syaratNyata.syarat ne null}">${tbl1.hakmilik.syaratNyata.syarat}</c:if>
                                    <c:if test="${tbl1.hakmilik.syaratNyata.syarat eq null}">Tiada</c:if>
                                </display:column>
                                <display:column title="Sekatan Kepentingan" style="vertical-align:baseline">
                                    <c:if test="${tbl1.hakmilik.sekatanKepentingan.sekatan ne null}">${tbl1.hakmilik.sekatanKepentingan.sekatan}</c:if>
                                    <c:if test="${tbl1.hakmilik.sekatanKepentingan.sekatan eq null}">Tiada</c:if>
                                </display:column>
                                <display:column title="Bebanan Berdaftar" style="vertical-align:baseline">
                                    <c:set var="flag" value="true" />
                                    <c:forEach items="${tbl1.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                                        <c:if test="${(((senarai.jenis.kod eq 'PG') ||(senarai.jenis.kod eq 'PJ') ||(senarai.jenis.kod eq 'PJK') ||
                                                      (senarai.jenis.kod eq 'PKA') ||(senarai.jenis.kod eq 'PKD') ||(senarai.jenis.kod eq 'PKL') ||
                                                      (senarai.jenis.kod eq 'PKS') ||(senarai.jenis.kod eq 'PMG') ||(senarai.jenis.kod eq 'PI') ||
                                                      (senarai.jenis.kod eq 'PY')) && senarai.aktif eq 'Y') }">
                                            <c:out value="${senarai.jenis.nama}"/><br/><br/>
                                            <c:set var="flag" value="false" />
                                        </c:if>
                                    </c:forEach>
                                    <c:if test="${flag eq 'true'}">
                                        Tiada
                                    </c:if>
                                </display:column>
                            </display:table>
                        </td></tr>
                        <c:set var="j" value="4" />
                        <tr><td><b>${j}. ULASAN JABATAN-JABATAN TEKNIKAL</b></td></tr>
                        <tr><td>
                                <display:table name="${actionBean.listUlasan2}" id="line1" class="tablecloth" size="100">
                                    <display:column title="Bil" sortable="true">${line1_rowNum}</display:column>
                                    <display:column title="Jabatan" style="width:400px" property="agensi.nama"></display:column>
                                    <display:column title="Ulasan" style="width:600px" property="ulasan"></display:column>
                                </display:table>
                            </td></tr>
                            <c:set var="j" value="${j+1}" />

                    <tr><td><b><font style="text-transform:uppercase">${j}. HURAIAN PENTADBIR TANAH  ${actionBean.pejTanah}&nbsp;</font></b></td></tr>
                    <tr><td>
                            <table cellspacing="10">
                                <c:set var="i" value="1" />
                                <c:forEach items="${actionBean.senaraiKandungan5}" var="line">
                                    <tr>
                                        <td valign="top"><b>${j}.${i}</b></td>
                                        <td>${line.kandungan}</td>
                                    </tr>
                                    <c:set var="i" value="${i+1}" />
                                </c:forEach>
                            </table>
                        </td>
                    </tr>
                    <c:set var="j" value="${j+1}" />                 

                    <tr><td><b><font style="text-transform:uppercase">${j}. SYOR PENTADBIR TANAH ${actionBean.pejTanah}</font></b></td></tr>
                    <tr><td>
                            <table cellspacing="10">
                                <c:set var="i" value="1" />
                                <c:forEach items="${actionBean.senaraiKandungan6}" var="line">
                                    <tr>
                                        <td valign="top"><b>${j}.${i}</b></td>
                                        <td>${line.kandungan}</td>
                                    </tr>
                                    <c:set var="i" value="${i+1}" />
                                </c:forEach>
                            </table>
                        </td>
                    </tr>
                    <c:set var="j" value="${j+1}" />

                    <tr><td><b>${j}. HURAIAN PENGARAH TANAH DAN GALIAN NEGERI SEMBILAN</b></td></tr>
                    <tr><td>
                            <table id ="dataTable7" cellspacing="10">
                                <c:set var="i" value="1" />
                                <c:forEach items="${actionBean.senaraiKandungan7}" var="line" >
                                    <tr>
                                        <td valign="top"><b>${j}.${i}</b></td>
                                        <td>${line.kandungan}</td>
                                    </tr>
                                    <c:set var="i" value="${i+1}" />
                                </c:forEach>
                            </table>
                        </td>
                    </tr>
                    <c:set var="j" value="${j+1}" />

                    <tr><td><b>${j}. SYOR PENGARAH TANAH DAN GALIAN NEGERI SEMBILAN</b></td></tr>
                    <tr><td>
                            <table id ="dataTable8" cellspacing="10">
                                <c:set var="i" value="1" />
                                <c:forEach items="${actionBean.senaraiKandungan8}" var="line" >
                                    <tr>
                                        <td><b>${j}.${i}</b></td>
                                        <td>${line.kandungan}</td>
                                    </tr>
                                    <c:set var="i" value="${i+1}" />
                                </c:forEach>
                            </table>
                        </td>
                    </tr>
                    <c:set var="j" value="${j+1}" />

                    <tr><td><b>${j}. KEPUTUSAN</b></td></tr>
                    <tr><td>
                            <table cellspacing="10">
                                <tr>
                                    <td><b>${j}.1 </b></td>
                                    <td>Pengarah Tanah Dan Galian Negeri Sembilan dengan ini 
                                        <c:if test="${actionBean.fasaPermohonan.keputusan.kod eq 'L'}">
                                            <b>MELULUSKAN</b> 
                                        </c:if>
                                        <c:if test="${actionBean.fasaPermohonan.keputusan.kod eq 'T'}">
                                            <b>MENOLAK</b> 
                                        </c:if>
                                        <c:if test="${actionBean.fasaPermohonan.keputusan.kod eq 'TG'}">
                                            <b>MENANGGUH</b> 
                                        </c:if>
                                        <c:if test="${actionBean.fasaPermohonan.keputusan.kod eq null}">
                                            <b>meluluskan / menolak</b> 
                                        </c:if>
                                        permohonan ini.</td>
                                </tr>
                            </table>
                        </td></tr>
                </table>
            </div>
        </fieldset>
    </div>
</s:form>



