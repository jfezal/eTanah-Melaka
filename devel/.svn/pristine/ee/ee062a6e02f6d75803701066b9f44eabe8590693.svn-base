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


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SYARAT-SYARAT</title>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script src="<%= request.getContextPath()%>/pub/js/ui.datepicker-ms.js" type="text/javascript"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>
<body>
    <script>
        // Allow the user to be warned by default.
        var allowPrompt = true;
                
        window.onbeforeunload = WarnUser;
        function WarnUser()
        {   
            if(allowPrompt)
                refreshpage();
            if(allowPrompt)
            {
                event.returnValue = "Segala Maklumat tidak akan disimpan, anda pasti?";
            }
            else
            {
                // Reset the flag to its default value.
                allowPrompt = true;
            }
        }
        function NoPrompt()
        {
            allowPrompt = false;
        }
        
    </script>
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <s:form beanclass="etanah.view.stripes.pelupusan.RekodKeputusanMMKV2ActionBean" name="form">
        <s:hidden name="idHakmilik" id="idHakmilik" value="${actionBean.hakmilikPermohonan.id}"/>
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
                            ${actionBean.hakmilikPermohonan.cukaiPerMeterPersegi}
                            <c:choose>
                                <c:when test="${actionBean.hakmilikPermohonan.keteranganCukaiBaru eq '1'}">
                                    Bagi setiap 100 meter persegi (Bangunan)
                                </c:when>
                                <c:when test="${actionBean.hakmilikPermohonan.keteranganCukaiBaru eq '2'}">
                                    Kurang 5 Hektar (Pertanian)
                                </c:when>    
                                    <c:when test="${actionBean.hakmilikPermohonan.keteranganCukaiBaru eq '3'}">
                                    Lebih 5 Hektar (Pertanian)
                                </c:when> 
                            </c:choose> 
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
                            ${actionBean.amnt}
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
            <c:otherwise></c:otherwise>
        </c:choose>
        

</s:form>