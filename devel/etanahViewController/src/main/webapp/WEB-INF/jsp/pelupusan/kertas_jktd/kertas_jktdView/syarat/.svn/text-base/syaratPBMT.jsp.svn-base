<%-- 
    Document   : syaratPBMT
    Created on : Mar 13, 2013, 4:09:44 PM
    Author     : afham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.pelupusan.KertasJKTDV2ActionBean" name="form">
    <c:choose>
        <c:when test="${actionBean.kodNegeri eq '04'}">
            <table class="tablecloth" border="0">
                <tr>
                    <td>
                        Luas Disyorkan :
                    </td>
                    <td>
                        <s:format value="${actionBean.hakmilikPermohonan.luasTerlibat}" formatPattern="#,###,##0.0000"/> ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}
                    </td>
                </tr>
                <tr>
                    <td>
                        Luas Diluluskan :
                    </td>
                    <td>
                        <s:format value="${actionBean.hakmilikPermohonan.luasDiluluskan}" formatPattern="#,###,##0.0000"/> ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}

                    </td>
                </tr>
                <tr>
                    <td>Penjenisan</td>
                    <td> 
                        ${actionBean.hakmilikPermohonan.kategoriTanahBaru.nama}
                    </td>
                </tr>
                <tr>
                    <td>Kegunaan Tanah</td>
                    <td>
                        ${actionBean.hakmilikPermohonan.kodKegunaanTanah.nama}
                    </td>
                </tr>
                <tr>
                    <td>Jenis Hakmilik :</td>
                    <td>
                        ${actionBean.hakmilikPermohonan.kodHakmilik.nama}
                    </td>
                </tr>
                <c:if  test="${actionBean.hakmilikPermohonan.kodHakmilik.kod eq 'PM' || actionBean.hakmilikPermohonan.kodHakmilik.kod eq 'PN'}">
                    <tr>
                        <td>Tempoh Pajakan:</td>
                        <td>
                            ${actionBean.hakmilikPermohonan.tempohPajakan}
                        </td>
                    </tr>
                </c:if>                              
                <tr>
                    <td>Premium : </td>
                    <td>
                        ${actionBean.hakmilikPermohonan.keteranganKadarPremium}
                    </td>
                </tr>
                <tr>
                    <td>Hasil (RM) :</td>
                    <td>
                        ${actionBean.hakmilikPermohonan.keteranganCukaiBaru} 
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
            </table>
            <legend>Syarat</legend>
            <table class="tablecloth" align="center">
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
                <tr>
                    <td style="text-align:center;" colspan="2">      
                        <s:button name="tutup" value="Tutup" class="btn" onclick="refreshpage()"/>
                    </td>
                </tr>
            </table>            
        </c:when>
        <c:when test="${actionBean.kodNegeri eq '05'}">
            <table class="tablecloth" border="0">
                <c:if test="${actionBean.kelompok eq false}">
                <tr>
                    <td>
                        Luas Disyorkan :
                    </td>
                    <td>
                        <s:format value="${actionBean.hakmilikPermohonan.luasTerlibat}" formatPattern="#,###,##0.0000"/> ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}
                    </td>
                </tr>
                <tr>
                    <td>
                        Luas Diluluskan :
                    </td>
                    <td>
                        <s:format value="${actionBean.hakmilikPermohonan.luasDiluluskan}" formatPattern="#,###,##0.0000"/> ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}

                    </td>
                </tr>
                </c:if>
                <tr>
                    <td>Penjenisan</td>
                    <td> 
                        ${actionBean.hakmilikPermohonan.kategoriTanahBaru.nama}
                    </td>
                </tr>
                <tr>
                    <td>Kegunaan Tanah</td>
                    <td>
                        ${actionBean.hakmilikPermohonan.kodKegunaanTanah.nama}
                    </td>
                </tr>
                <tr>
                    <td>Jenis Hakmilik :</td>
                    <td>
                        ${actionBean.hakmilikPermohonan.kodHakmilik.nama}
                    </td>
                </tr>
                <c:if  test="${actionBean.hakmilikPermohonan.kodHakmilik.kod eq 'PM' || actionBean.hakmilikPermohonan.kodHakmilik.kod eq 'PN'}">
                    <tr>
                        <td>Tempoh Pajakan:</td>
                        <td>
                            ${actionBean.hakmilikPermohonan.tempohPajakan}
                        </td>
                    </tr>
                </c:if>
                <tr>
                    <td>Premium : </td>
                    <td>
                        RM <s:format value="${actionBean.amnt}" formatPattern="#,###,##0.00"/>
                    </td>
                </tr>
                <tr>
                    <td>Hasil (RM) :</td>
                    <td>
                        ${actionBean.hakmilikPermohonan.keteranganCukaiBaru} 
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
            </table>
        </c:when>
        <c:otherwise></c:otherwise>
    </c:choose>


</s:form>