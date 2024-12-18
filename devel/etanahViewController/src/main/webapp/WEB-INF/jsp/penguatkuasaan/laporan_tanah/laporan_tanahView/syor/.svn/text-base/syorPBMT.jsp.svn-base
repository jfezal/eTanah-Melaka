<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<div id="syorPPT" align="center">
    <table class="tablecloth" align="center">
        <c:if test="${actionBean.kodNegeri eq '04'}">
            <tr>
                <td>
                    Syor : 
                </td>
                <td>
                    <c:choose>
                        <c:when test="${actionBean.fasaPermohonan.keputusan.kod eq 'SL'}">Sokong Permohonan</c:when>
                        <%--c:when test="${actionBean.fasaPermohonan.keputusan.kod eq 'SU'}">Syor Lulus LPS</c:when--%>
                        <c:when test="${actionBean.fasaPermohonan.keputusan.kod eq 'ST'}">Tidak Sokong</c:when>
                        <c:otherwise>
                            ${actionBean.fasaPermohonan.keputusan.nama}&nbsp;
                        </c:otherwise>                                                    
                    </c:choose>                                             
                </td>
            </tr>
            <c:choose>
                <c:when test="${actionBean.fasaPermohonan.keputusan.kod eq 'SL' }">
                    
                    <tr>
                        <td>
                            Luas Disyorkan:
                        </td>
                        <td>
                            <s:format formatPattern="#,###,##0.0000" value="${actionBean.hakmilikPermohonan.luasDiluluskan}"/>&nbsp;${actionBean.noPT.kodUOM.nama}&nbsp;
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Kategori:
                        </td>
                        <td>
                            <c:if test="${actionBean.hakmilikPermohonan.kategoriTanahBaru.kod eq '1'}">Pertanian</c:if>
                            <c:if test="${actionBean.hakmilikPermohonan.kategoriTanahBaru.kod eq '2'}">Bangunan</c:if>
                            <c:if test="${actionBean.hakmilikPermohonan.kategoriTanahBaru.kod eq '3'}">Perusahaan</c:if>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Kegunaan Tanah:
                        </td>
                        <td>
                            <c:if test="${actionBean.hakmilikPermohonan.kodKegunaanTanah.nama eq null}">Tiada<br/></c:if>
                            <c:if test="${actionBean.hakmilikPermohonan.kodKegunaanTanah.nama ne null}">${actionBean.hakmilikPermohonan.kodKegunaanTanah.nama}<br/></c:if>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Jenis Hakmilik :
                        </td>
                        <td>
                            ${actionBean.hakmilikPermohonan.kodHakmilik.nama}&nbsp;
                        </td>
                    </tr>
                    <c:if test="${actionBean.hakmilikPermohonan.kodHakmilik.kod eq 'PN'}">
                    <tr>
                        <td>
                            Tempoh Pajakan :
                        </td>
                        <td>
                            ${actionBean.hakmilikPermohonan.tempohPajakan} tahun&nbsp;
                        </td>
                    </tr>
                    </c:if>
                    <c:if test="${actionBean.hakmilikPermohonan.kodHakmilik.kod eq 'PM'}">
                    <tr>
                        <td>
                            Tempoh Pajakan :
                        </td>
                        <td>
                            ${actionBean.hakmilikPermohonan.tempohPajakan} tahun&nbsp;
                        </td>
                    </tr>
                    </c:if> 
                    <tr>
                        <td>
                            Premium :
                        </td>
                        <td>
                            ${actionBean.hakmilikPermohonan.keteranganKadarPremium}&nbsp;
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Hasil (RM) :
                        </td>
                        <td>
                            ${actionBean.hakmilikPermohonan.keteranganCukaiBaru} &nbsp;
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Upah Ukur :
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${actionBean.hakmilikPermohonan.agensiUpahUkur eq 'JUP'}">
                                    Mengikut PU(A)438
                                </c:when>
                                <c:when test="${actionBean.hakmilikPermohonan.agensiUpahUkur eq 'JUB'}">
                                    Juru Ukur Berlesen
                                </c:when>    
                            </c:choose>
                        </td>
                    </tr>
                    <tr>
                    <td>
                        Syarat Nyata :
                    </td>
                    <td>
                        <s:textarea name="sekatan" value="${actionBean.hakmilikPermohonan.syaratNyataBaru.syarat}" class="normal_text" readonly="true" cols="80" rows="5"/>&nbsp;           
                    </td>
                </tr>
                <tr>
                    <td>
                        Sekatan Kepentingan :
                    </td>
                    <td>
                        <s:textarea name="sekatan" value="${actionBean.hakmilikPermohonan.sekatanKepentinganBaru.sekatan}" class="normal_text" readonly="true" cols="80" rows="5"/>&nbsp;
                    </td>
                </tr>
                </c:when>
                <c:when test="${actionBean.fasaPermohonan.keputusan.kod eq 'ST' }">
                    <tr>
                        <td>
                            Sebab :
                        </td>
                        <td>
                            ${actionBean.fasaPermohonan.ulasan}&nbsp;
                        </td>
                    </tr>
                </c:when>
                <c:when test="${actionBean.fasaPermohonan.keputusan.kod eq 'SU' }">
                    <tr>
                        <td>
                            Kegunaan :
                        </td>
                        <td>
                            ${actionBean.permohonanPermitItem.kodItemPermit.nama}&nbsp;
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Bayaran (RM) :
                        </td>
                        <td>
                            ${actionBean.permohonanTuntutanKos.amaunTuntutan}&nbsp; Setahun
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Luas :
                        </td>
                        <td>
                            <s:format formatPattern="#,###,##0.0000" value="${actionBean.hakmilikPermohonan.luasTerlibat}"/> &nbsp; ${actionBean.noPT.kodUOM.nama}&nbsp;
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Syarat Tambahan :
                        </td>
                        <td>
                            ${actionBean.ulsn}&nbsp;
                        </td>
                    </tr>
                </c:when>
                <c:otherwise>
                </c:otherwise>
            </c:choose>
        </c:if>

        <c:if test="${actionBean.kodNegeri eq '05'}">
            <tr>
                <td>
                    Syor : 
                </td>
                <td>
                    <c:choose>
                        <c:when test="${actionBean.fasaPermohonan.keputusan.kod eq 'SL'}">Sokong Permohonan</c:when>
                        <%--c:when test="${actionBean.fasaPermohonan.keputusan.kod eq 'SU'}">Syor Lulus LPS</c:when--%>
                        <c:when test="${actionBean.fasaPermohonan.keputusan.kod eq 'ST'}">Tidak Sokong</c:when>
                        <c:otherwise>
                            ${actionBean.fasaPermohonan.keputusan.nama}&nbsp;
                        </c:otherwise>                                                    
                    </c:choose>                                             
                </td>
            </tr>
            <c:choose>
                <c:when test="${actionBean.fasaPermohonan.keputusan.kod eq 'SL' }">
                    
                    <tr>
                        <td>
                            Luas Disyorkan:
                        </td>
                        <td>
                            <s:format formatPattern="#,###,##0.0000" value="${actionBean.hakmilikPermohonan.luasDiluluskan}"/>&nbsp;${actionBean.noPT.kodUOM.nama}&nbsp;
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Kategori:
                        </td>
                        <td>
                            <c:if test="${actionBean.hakmilikPermohonan.kategoriTanahBaru.kod eq '1'}">Pertanian</c:if>
                            <c:if test="${actionBean.hakmilikPermohonan.kategoriTanahBaru.kod eq '2'}">Bangunan</c:if>
                            <c:if test="${actionBean.hakmilikPermohonan.kategoriTanahBaru.kod eq '3'}">Perusahaan</c:if>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Kegunaan Tanah:
                        </td>
                        <td>
                            <c:if test="${actionBean.hakmilikPermohonan.kodKegunaanTanah.nama eq null}">Tiada<br/></c:if>
                            <c:if test="${actionBean.hakmilikPermohonan.kodKegunaanTanah.nama ne null}">${actionBean.hakmilikPermohonan.kodKegunaanTanah.nama}<br/></c:if>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Jenis Hakmilik :
                        </td>
                        <td>
                            ${actionBean.hakmilikPermohonan.kodHakmilik.nama}&nbsp;
                        </td>
                    </tr>
                    <c:if test="${actionBean.hakmilikPermohonan.kodHakmilik.kod eq 'PN'}">
                    <tr>
                        <td>
                            Tempoh Pajakan :
                        </td>
                        <td>
                            ${actionBean.hakmilikPermohonan.tempohPajakan} tahun&nbsp;
                        </td>
                    </tr>
                    </c:if>
                    <c:if test="${actionBean.hakmilikPermohonan.kodHakmilik.kod eq 'PM'}">
                    <tr>
                        <td>
                            Tempoh Pajakan :
                        </td>
                        <td>
                            ${actionBean.hakmilikPermohonan.tempohPajakan} tahun&nbsp;
                        </td>
                    </tr>
                    </c:if> 
                    <tr>
                        <td>
                            Premium :
                        </td>
                        <td>
                            ${actionBean.hakmilikPermohonan.keteranganKadarPremium}&nbsp;
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Hasil (RM) :
                        </td>
                        <td>
                            ${actionBean.hakmilikPermohonan.keteranganCukaiBaru} &nbsp;
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Upah Ukur :
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${actionBean.hakmilikPermohonan.agensiUpahUkur eq 'JUP'}">
                                    Mengikut PU(A)438
                                </c:when>
                                <c:when test="${actionBean.hakmilikPermohonan.agensiUpahUkur eq 'JUB'}">
                                    Juru Ukur Berlesen
                                </c:when>    
                            </c:choose>
                        </td>
                    </tr>
                    <tr>
                    <td>
                        Syarat Nyata :
                    </td>
                    <td>
                        <s:textarea name="sekatan" value="${actionBean.hakmilikPermohonan.syaratNyataBaru.syarat}" class="normal_text" readonly="true" cols="80" rows="5"/>&nbsp;           
                    </td>
                </tr>
                <tr>
                    <td>
                        Sekatan Kepentingan :
                    </td>
                    <td>
                        <s:textarea name="sekatan" value="${actionBean.hakmilikPermohonan.sekatanKepentinganBaru.sekatan}" class="normal_text" readonly="true" cols="80" rows="5"/>&nbsp;
                    </td>
                </tr>
                </c:when>
                <c:when test="${actionBean.fasaPermohonan.keputusan.kod eq 'ST' }">
                    <tr>
                        <td>
                            Sebab :
                        </td>
                        <td>
                            ${actionBean.fasaPermohonan.ulasan}&nbsp;
                        </td>
                    </tr>
                </c:when>
                <c:when test="${actionBean.fasaPermohonan.keputusan.kod eq 'SU' }">
                    <tr>
                        <td>
                            Kegunaan :
                        </td>
                        <td>
                            ${actionBean.permohonanPermitItem.kodItemPermit.nama}&nbsp;
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Bayaran (RM) :
                        </td>
                        <td>
                            ${actionBean.permohonanTuntutanKos.amaunTuntutan}&nbsp; Setahun
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Luas :
                        </td>
                        <td>
                            <s:format formatPattern="#,###,##0.0000" value="${actionBean.hakmilikPermohonan.luasTerlibat}"/> &nbsp; ${actionBean.noPT.kodUOM.nama}&nbsp;
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Syarat Tambahan :
                        </td>
                        <td>
                            ${actionBean.ulsn}&nbsp;
                        </td>
                    </tr>
                </c:when>
                <c:otherwise>
                </c:otherwise>
            </c:choose>
        </c:if>
    </table>
</div>