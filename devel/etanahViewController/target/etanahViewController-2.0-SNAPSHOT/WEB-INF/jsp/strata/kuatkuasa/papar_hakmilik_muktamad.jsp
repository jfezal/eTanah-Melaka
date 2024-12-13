<%-- 
    Document   : papar_hakmilik_muktamad
    Created on : Sep 21, 2011, 1:11:15 PM
    Author     : zadhirul.farihim
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<s:form beanclass="etanah.view.strata.LaporanSiasatKuatkuasaStrataActionBean" name="form1">
    <s:messages/>
    <s:errors/>
   
    <div class="subtitle">
            <fieldset class="aras1">
                <table>
                <tr><td colspan="3"><b>3.2.1 Hakmilik Muktamad :</b></td></tr>
                    <tr><td id="idLabel">Jenis dan No. Hakmilik  </td>
                        <td>:</td>
                        <td>${actionBean.hakmilikMuktamad.kodHakmilik.nama} &nbsp; ${actionBean.hakmilikMuktamad.noHakmilik}</td>
                    </tr>

                    <tr><td id="idLabel">No. PT/Lot</td>
                        <td>:</td>
                        <td>${actionBean.hakmilikMuktamad.lot.nama} &nbsp; ${actionBean.hakmilikMuktamad.noLot}</td>
                    </tr>

                    <tr><td id="idLabel">Bandar/Pekan/Mukim</td>
                        <td>:</td>
                        <td>${actionBean.hakmilikMuktamad.bandarPekanMukim.nama}</td>
                    </tr>

                    <tr><td id="idLabel">Daerah</td>
                        <td>:</td>
                        <td>${actionBean.hakmilikMuktamad.daerah.nama}</td>
                    </tr>

                    <tr><td id="idLabel">Kategori Tanah</td>
                        <td> :</td>
                        <td>${actionBean.hakmilikMuktamad.kategoriTanah.nama}</td>

                    </tr>

                    <tr><td id="idLabel">Syarat Nyata Tanah</td>
                        <td> :</td>

                        <c:if test="${actionBean.hakmilikMuktamad.syaratNyata.syarat ne null}">
                            <td>${actionBean.hakmilikMuktamad.syaratNyata.syarat}&nbsp;</td>
                        </c:if>
                        <c:if test="${actionBean.hakmilikMuktamad.syaratNyata.syarat eq null}">
                            <td>Tiada &nbsp;</td>
                        </c:if>
                    </tr>
                
                </table>
                <br>
                
            </fieldset></div>


</s:form>
