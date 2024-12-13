<%-- 
    Document   : kertas_mmk2
    Created on : Jun 14, 2011, 10:09:25 PM
    Author     : zadhirul.farihim
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>


<s:form beanclass="etanah.view.strata.KertasMMKActionBean">
    <s:messages/>
    <s:errors/> 
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Tajuk</legend>
            <div class="content" align="left">
                <table border="0">
                    <tr><td colspan="4" align="center"><b>( MAJLIS MESYUARAT KERAJAAN )</b></td></tr>
                    <tr>
                        <td colspan="4"><strong>&nbsp;</strong></td>
                    </tr>
                    <tr><td width="100px"><strong>&nbsp;</strong></td>
                        <td colspan="2"><b><font style="text-transform: uppercase"><center>${actionBean.tajuk}</center></font></b></td>
                        <td width="100px"><strong>&nbsp;</strong></td>
                    </tr>
                    <tr>
                        <td colspan="4"><strong>&nbsp;</strong></td>
                    </tr>
                </table>
            </div>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>1. Tujuan</legend>
            <div class="content" align="left">
                <table border="0">

                    <tr valign="top">
                        <td height="23">&nbsp;</td>
                        <td colspan="3">

                            <table id ="dataTable1" border="0">

                                <c:set var="i" value="1" />
                                <c:set var="bil" value="0" />
                                <c:forEach items="${actionBean.senaraiLaporanKandungan1}" var="line">
                                    <tr style="font-weight:bold">
                                        <td style="display:none">${line.idKandungan}</td>
                                        <td><c:out value="1.${bil+1}"/></td>
                                        <td>

                                            <font style="text-transform: uppercase">
                                            <s:textarea class="normal_text" readonly="true" name="tujua${i}" id="tujua${i}"  rows="5" cols="140">${line.kandungan}</s:textarea>
                                                </font>

                                            </td>
                                            <td>        
                                            </td>
                                        </tr>
                                    <c:set var="i" value="${i+1}" />
                                    <c:set var="bil" value="${bil+1}" />
                                </c:forEach>
                                <s:hidden name="rowCount1" value="${i-1}" id="rowCount1"/>
                            </table></td>
                    </tr>

                </table>
            </div>
        </fieldset>
    </div>
    <p></p>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>2. Latar Belakang</legend>
            <div class="content" align="left">
                <table border="0">

                    <tr valign="top">
                        <td height="23">&nbsp;</td>
                        <td colspan="3">

                            <table id ="dataTable2" border="0">

                                <c:set var="i" value="1" />
                                <c:set var="bil" value="0" />
                                <c:forEach items="${actionBean.senaraiLaporanKandungan2}" var="line">
                                    <tr style="font-weight:bold">
                                        <td style="display:none">${line.idKandungan}</td>
                                        <td><c:out value="2.${bil+1}"/></td>
                                        <td>
                                            <font style="text-transform: uppercase">
                                            <s:textarea class="normal_text" readonly="true" name="latarblkg${i}" id="latarblkg${i}"  rows="5" cols="140">${line.kandungan}</s:textarea>
                                                </font>

                                            </td>

                                            <td>
                                            </td>

                                        </tr>

                                    <c:set var="i" value="${i+1}" />
                                    <c:set var="bil" value="${bil+1}" />
                                </c:forEach>
                                <s:hidden name="rowCount2" value="${i-1}" id="rowCount2"/>
                            </table></td>
                    </tr>
                </table>
            </div>
        </fieldset>
    </div>
    <p></p>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>3. Asas Permohonan</legend>
            <div class="content" align="left">
                <table border="0">

                    <tr valign="top">
                        <td height="23">&nbsp;</td>
                        <td colspan="3">

                            <table id ="dataTable3" border="0">

                                <c:set var="i" value="1" />
                                <c:set var="bil" value="0" />

                                <c:forEach items="${actionBean.senaraiLaporanKandungan3}" var="line">
                                    <tr style="font-weight:bold">
                                        <td style="display:none">${line.idKandungan}</td>
                                        <td><c:out value="3.${bil+1}"/></td>
                                         <c:if test="${i ne 2}">
                                        <td>
                                            <font style="text-transform: uppercase">
                                            <s:textarea class="normal_text" readonly="true"  name="asaspermohonan${i}" id="asaspermohonan${i}"  rows="5" cols="140" >${line.kandungan}</s:textarea>
                                            </font>

                                        </td>
                                        </c:if>
                                        <c:if test="${i eq 2}">
                                        <td>
                                            <font style="text-transform: uppercase">
                                            <s:textarea class="normal_text" readonly="true"  name="asaspermohonan${i}" id="asaspermohonan${i}"  rows="8" cols="140" >${line.kandungan}</s:textarea>
                                            </font>

                                        </td>
                                        </c:if>

                                            <td>

                                            </td>

                                        </tr>

                                    <c:set var="i" value="${i+1}" />
                                    <c:set var="bil" value="${bil+1}" />
                                </c:forEach>
                                <s:hidden name="rowCount3" value="${i-1}" id="rowCount3"/>
                            </table></td>
                    </tr>
                </table>
            </div>
        </fieldset>
    </div>

</s:form>
