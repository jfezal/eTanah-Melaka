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
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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

<s:form beanclass="etanah.view.stripes.pembangunan.MaklumatTukarSyaratActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Untuk Mengubah Jenis Penggunaan Tanah, Syarat-Syarat Nyata Atau Sekatan Kepentingan
            </legend>
            <div class="content" align="center">
                <c:set value="i" var="0"/>
                <c:if test="${!edit}">
                    <table border="0" width="80%" cellspacing="15">
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSKKT' || actionBean.permohonan.kodUrusan.kod eq 'TSKSN'|| actionBean.permohonan.kodUrusan.kod eq 'TSPTD' || actionBean.permohonan.kodUrusan.kod eq 'TSPTG' || actionBean.permohonan.kodUrusan.kod eq 'TSMMK' || actionBean.permohonan.kodUrusan.kod eq 'TSPSS'}">
                            <c:set var="i" value="${i+1}"/>
                            <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">${i}.</font></td>
                                <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">  Diubah kategori penggunaan tanah  </font></td>
                            </tr>
                            <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> </font></td>
                                <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> Dari: </font><b> ${actionBean.hakmilikPermohonan.jenisPenggunaanTanah.nama}</b>
                                    <font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> kepada : </font>
                                    <b>${actionBean.hakmilikPermohonan.kategoriTanahBaru.nama}</b>
                                </td>
                            </tr>
                            
                            <c:set var="i" value="${i+1}"/>
                            <tr><td valign="top"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">${i}.</font></td>
                                <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">  Dikenakan kategori penggunaan tanah :
                                    </font><b>${actionBean.hakmilikPermohonan.kategoriTanahBaru.nama}</b>
                                </td>
                            </tr>
                        </c:if>
                        
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSBSN'}">
                            <c:set var="i" value="${i+1}"/>
                            <tr><td valign="top"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> ${i}.</font></td>
                                <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">  Dibatalkan ungkapan "Padi","Getah","Kampung",dsb :
                                    </font><b>
                                            <c:if test="${actionBean.hakmilikPermohonan.catatan eq null}">-</c:if>
                                            <c:if test="${actionBean.hakmilikPermohonan.catatan ne null}">${actionBean.hakmilikPermohonan.catatan}</c:if>
                                     </b>
                                <td>
                            </tr>
                        </c:if>
                                
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSBSN' || actionBean.permohonan.kodUrusan.kod eq 'TSPTD' || actionBean.permohonan.kodUrusan.kod eq 'TSPTG' || actionBean.permohonan.kodUrusan.kod eq 'TSMMK' || actionBean.permohonan.kodUrusan.kod eq 'TSPSS'}">
                            <c:set var="i" value="${i+1}"/>
                            <tr><td valign="top"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">${i}.</font></td>
                                <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">
                                        Dibatalkan syarat nyata :</font><b> ${actionBean.hakmilikPermohonan.syaratNyata.syarat}</b>
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSPSN' ||actionBean.permohonan.kodUrusan.kod eq 'TSKSN'|| actionBean.permohonan.kodUrusan.kod eq 'TSPTD' || actionBean.permohonan.kodUrusan.kod eq 'TSPTG' || actionBean.permohonan.kodUrusan.kod eq 'TSMMK' || actionBean.permohonan.kodUrusan.kod eq 'TSPSS'}">
                            <c:set var="i" value="${i+1}"/>
                            <tr><td valign="top"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">${i}.</font></td>
                                <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">
                                        Dipinda syarat nyata : </font><b>${actionBean.hakmilikPermohonan.syaratNyata.syarat}</b>
                                </td>
                            </tr>
                        </c:if> 
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSBSN' ||actionBean.permohonan.kodUrusan.kod eq 'TSKSN'|| actionBean.permohonan.kodUrusan.kod eq 'TSPSN' || actionBean.permohonan.kodUrusan.kod eq 'TSKSN' || actionBean.permohonan.kodUrusan.kod eq 'TSPTD' || actionBean.permohonan.kodUrusan.kod eq 'TSPTG' || actionBean.permohonan.kodUrusan.kod eq 'TSMMK' || actionBean.permohonan.kodUrusan.kod eq 'TSPSS'}">
                            <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> </font></td>
                                <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> 
                                        Supaya menjadi seperti berikut :                               
                                    </font><b>${actionBean.hakmilikPermohonan.syaratNyataBaru.syarat}</b>
                                </td>
                            </tr>
                        </c:if>
                                
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBSK' || actionBean.permohonan.kodUrusan.kod eq 'TSPTD' || actionBean.permohonan.kodUrusan.kod eq 'TSPTG' || actionBean.permohonan.kodUrusan.kod eq 'TSMMK' || actionBean.permohonan.kodUrusan.kod eq 'TSPSS'}">
                            <c:set var="i" value="${i+1}"/>
                            <tr><td valign="top"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">${i}.</font></td>
                                <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">
                                        Dibatalkan sekatan kepentingan : </font><b> ${actionBean.hakmilikPermohonan.sekatanKepentingan.sekatan}</b>
                                </td>
                            </tr>
                            <c:set var="i" value="${i+1}"/>
                            <tr><td valign="top"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">${i}.</font></td>
                                <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">
                                        Dipinda sekatan kepentingan :</font><b> ${actionBean.hakmilikPermohonan.sekatanKepentingan.sekatan}</b>
                                </td>
                            </tr>
                            <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> </font></td>
                                <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> 
                                        Supaya menjadi seperti berikut :                                
                                    </font><b>${actionBean.hakmilikPermohonan.sekatanKepentinganBaru.sekatan}</b>
                                </td>
                            </tr>
                         </c:if>
                    </table>
                </c:if>
            </div>
        </fieldset>
    </div>
</s:form>
