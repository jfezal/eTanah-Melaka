<%--
    Document   : maklumat_tukar_syarat
    Created on : Mar 8, 2010, 11:07:38 AM
    Author     : nursyazwani
   Modified By : NageswaraRao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<%@ page import="etanah.view.etanahActionBeanContext" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
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
        text-align:left;
    }

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
    }
</style>
<%
    etanah.Configuration conf = new etanah.Configuration();
    String kodNegeri = conf.getProperty("kodNegeri");
%>
<s:form beanclass="etanah.view.stripes.pembangunan.MaklumatTukarSyaratActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend></legend>
            <div class="content" align="center">
                <table width="80%" cellspacing="10" border="0">
                    <tr>
                        <td align="center" colspan="3"><font style="font-size:14px; color:#003194; font-family:Tahoma; font-weight:bold;"> Borang 7C </font></td>
                    </tr>
                    <tr>
                        <td align="center" colspan="3"><font style="font-size:14px; color:#003194; font-family:Tahoma; font-weight:bold;"> (Seksyen 124) </font></td>
                    </tr>
                    <tr>
                        <td align="center" colspan="3"><font style="font-size:14px; color:#003194; font-family:Tahoma; font-weight:bold;">  MEMORANDUM PENGUBAHAN SYARAT-SYARAT SEKATAN-SEKATAN DAN KATEGORI PENGGUNAAN </font></td>
                    </tr><br>
                    <tr>
                        <td colspan="3"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> Pihak Berkuasa Negeri telah meluluskan :- </font></td>
                </table>
                <%
                    if (kodNegeri.equals("05")) {
                %>
                <table border="0" width="80%" cellspacing="15">


                    <tr><td>
                            <font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">(i) Penggubahan kategori penggunaan tanah daripada</font>
                            <c:if test="${(actionBean.permohonan.kodUrusan.kod eq 'TSMMK'||actionBean.permohonan.kodUrusan.kod eq 'TSPTD'||actionBean.permohonan.kodUrusan.kod eq 'TSPTG') && actionBean.hakmilik.kategoriTanah ne null}">
                                <font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma; font-weight:bold;">
                                    ${actionBean.hakmilik.kategoriTanah.nama}
                                </font>
                            </c:if>
                            <c:if test="${(actionBean.permohonan.kodUrusan.kod eq 'TSMMK'||actionBean.permohonan.kodUrusan.kod eq 'TSPTD'||actionBean.permohonan.kodUrusan.kod eq 'TSPTG') && actionBean.hakmilik.kategoriTanah eq null}">
                                <font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma; font-weight:bold;">
                                    Tiada
                                </font>
                            </c:if>
                            <font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">kepada</font>
                            <c:if test="${(actionBean.permohonan.kodUrusan.kod eq 'TSMMK' || actionBean.permohonan.kodUrusan.kod eq 'TSPTD'||actionBean.permohonan.kodUrusan.kod eq 'TSPTG') && actionBean.hp.kategoriTanahBaru ne null}">
                                <font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma; font-weight:bold;">${actionBean.hp.kategoriTanahBaru.nama}</font>
                            </c:if>
                        </td></tr>
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">(ii) Pengenaan Kategori penggunaan tanah</font>
                            <c:if test="${(actionBean.permohonan.kodUrusan.kod eq 'TSMMK' || actionBean.permohonan.kodUrusan.kod eq 'TSPTD'||actionBean.permohonan.kodUrusan.kod eq 'TSPTG') && actionBean.hp.kategoriTanahBaru ne null}">
                                <font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma; font-weight:bold;">${actionBean.hp.kodKegunaanTanah.nama}</font>
                            </c:if>
                        </td></tr>




                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">(iii) Pembatalan syarat nyata</font>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSMMK' || actionBean.permohonan.kodUrusan.kod eq 'TSKKT' || actionBean.permohonan.kodUrusan.kod eq 'TSPTD'||actionBean.permohonan.kodUrusan.kod eq 'TSPTG'}">
                                <font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma; font-weight:bold;">${actionBean.hakmilik.syaratNyata.syarat}</font>
                            </c:if>
                        </td></tr>

                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">(iv) Pembatalan ungkapan "Padi","Getah","Kampung",dsb:</font>
                            <%--<font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma; font-weight:bold;">${actionBean.hp.catatan}</font>--%>

                        </td></tr>



                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">(v) Pindaan syarat-syarat nyata seperti berikut </font>

                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSMMK' || actionBean.permohonan.kodUrusan.kod eq 'TSPTD' || actionBean.permohonan.kodUrusan.kod eq 'TSPTG'}">
                                <font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma; font-weight:bold;">${actionBean.hakmilik.syaratNyata.syarat}</font>
                            </c:if>   
                        </td></tr>


                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> (vi) Pengenaan syarat-syarat nyata yang baru </font>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSMMK' || actionBean.permohonan.kodUrusan.kod eq 'TSPTD' || actionBean.permohonan.kodUrusan.kod eq 'TSPTG'}">
                                <font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma; font-weight:bold;">${actionBean.hp.syaratNyataBaru.syarat}</font>
                            </c:if>
                        </td></tr>


                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">(vii) Pembatalan sekatan kepentingan </font>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSMMK' || actionBean.permohonan.kodUrusan.kod eq 'TSKKT' || actionBean.permohonan.kodUrusan.kod eq 'TSPTD'||actionBean.permohonan.kodUrusan.kod eq 'TSPTG'}">
                                <font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma; font-weight:bold;">${actionBean.hakmilik.sekatanKepentingan.sekatan}</font>
                            </c:if>
                        </td></tr>

                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">(viii) Pindaan sekatan kepentingan seperti berikut </font>
                            <c:if test="${(actionBean.permohonan.kodUrusan.kod eq 'TSMMK' || actionBean.permohonan.kodUrusan.kod eq 'TSPTD'||actionBean.permohonan.kodUrusan.kod eq 'TSPTG') && actionBean.hakmilik.sekatanKepentingan ne null}">

                                <font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma; font-weight:bold;">${actionBean.hakmilik.sekatanKepentingan.sekatan}</font>
                            </c:if>
                        </td></tr>

                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">(ix) Pengenaan sekatan kepentingan baru </font>
                            <c:if test="${(actionBean.permohonan.kodUrusan.kod eq 'TSMMK' || actionBean.permohonan.kodUrusan.kod eq 'TSPTD'||actionBean.permohonan.kodUrusan.kod eq 'TSPTG') && actionBean.hp.sekatanKepentinganBaru ne null}">
                                <font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma; font-weight:bold;">${actionBean.hp.sekatanKepentinganBaru.sekatan}</font>
                            </c:if>
                        </td></tr>

                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Dengan tertakluk kepada bayaran sebanyak RM </font>
                            <font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma; font-weight:bold;"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.premium}"/></font>
                            <%--<font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma; font-weight:bold;">${actionBean.jumlah}</font> --%>
                        </td></tr>

                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">dan pengenaan cukai tanah baru RM </font>
                            <font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma; font-weight:bold;"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.hasilThnPertama}"/></font>
                            <%--<font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma; font-weight:bold;">${actionBean.item2}</font>--%>
                        </td></tr>
                </table>
                <%                                } else if (kodNegeri.equals("04")) {
                %>
                <table border="0" width="80%" cellspacing="15">


                    <tr><td>
                            <font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">(i) Penggubahan kategori penggunaan tanah daripada</font>
                            <c:if test="${(actionBean.permohonan.kodUrusan.kod eq 'TSKKT') && actionBean.hakmilik.kategoriTanah ne null}">
                                <font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma; font-weight:bold;">
                                    ${actionBean.hakmilik.kategoriTanah.nama}
                                </font>
                            </c:if>
                            <c:if test="${(actionBean.permohonan.kodUrusan.kod eq 'TSKKT') && actionBean.hakmilik.kategoriTanah eq null}">
                                <font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma; font-weight:bold;">
                                    Tiada
                                </font>
                            </c:if>
                            <font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">kepada</font>
                            <c:if test="${(actionBean.permohonan.kodUrusan.kod eq 'TSKKT') && actionBean.hp.kategoriTanahBaru ne null}">
                                <font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma; font-weight:bold;">${actionBean.hp.kategoriTanahBaru.nama}</font>
                            </c:if>
                        </td></tr>
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">(ii) Pengenaan Kategori penggunaan tanah</font>
                            <c:if test="${(actionBean.permohonan.kodUrusan.kod eq 'TSKKT') && actionBean.hp.kategoriTanahBaru ne null}">
                                <font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma; font-weight:bold;">${actionBean.hp.kodKegunaanTanah.nama}</font>
                            </c:if>
                        </td></tr>




                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">(iii) Pembatalan syarat nyata</font>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSBSN'}">
                                <font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma; font-weight:bold;">${actionBean.hakmilik.syaratNyata.syarat}</font>
                            </c:if>
                        </td></tr>

                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">(iv) Pembatalan ungkapan "Padi","Getah","Kampung",dsb:</font>
                            <%--<font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma; font-weight:bold;">${actionBean.hp.catatan}</font>--%>

                        </td></tr>



                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">(v) Pindaan syarat-syarat nyata seperti berikut </font>
                            <%--<font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma; font-weight:bold;">${actionBean.hakmilik.syaratNyata.syarat}</font>--%>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSPSN'||actionBean.permohonan.kodUrusan.kod eq 'TSKKT'}">
                                <font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma; font-weight:bold;">${actionBean.hp.syaratNyataBaru.syarat}</font>
                            </c:if>   
                        </td></tr>

                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> (vi) Pengenaan syarat-syarat nyata yang baru </font>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSKSN'}">
                                <font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma; font-weight:bold;">${actionBean.hp.syaratNyataBaru.syarat}</font>
                            </c:if>
                        </td></tr>

                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">(vii) Pembatalan sekatan kepentingan </font>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBSK'}">
                                <font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma; font-weight:bold;">${actionBean.hakmilik.sekatanKepentingan.sekatan}</font>
                            </c:if>
                        </td></tr>

                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">(viii) Pindaan sekatan kepentingan seperti berikut </font>
                            <c:if test="${(actionBean.permohonan.kodUrusan.kod eq 'PBSK') && actionBean.hakmilik.sekatanKepentingan ne null}">
                                <%--<font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma; font-weight:bold;">${actionBean.hakmilik.sekatanKepentingan.sekatan}</font>--%>
                                <font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma; font-weight:bold;">${actionBean.hakmilik.sekatanKepentingan.sekatan}</font>
                            </c:if>
                        </td></tr>

                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">(ix) Pengenaan sekatan kepentingan baru </font>
                            <c:if test="${(actionBean.permohonan.kodUrusan.kod eq 'PBSK') && actionBean.hp.sekatanKepentinganBaru ne null}">
                                <font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma; font-weight:bold;">${actionBean.hp.sekatanKepentinganBaru.sekatan}</font>
                            </c:if>
                        </td></tr>
                        <%--</c:if>--%>
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Dengan tertakluk kepada bayaran sebanyak RM </font>
                            <font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma; font-weight:bold;"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.premium}"/></font>
                            <%--<font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma; font-weight:bold;">${actionBean.jumlah}</font> --%>
                        </td></tr>

                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">dan pengenaan cukai tanah baru RM </font>
                            <font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma; font-weight:bold;"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.hasilThnPertama}"/></font>
                            <%--<font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma; font-weight:bold;">${actionBean.item2}</font>--%>
                        </td></tr>
                </table>
                <%                            }
                %>
            </div>
        </fieldset>
    </div>
</s:form>