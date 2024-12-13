<%--
    Document   : testing1
    Created on : Dec 16, 2009, 10:42:01 AM
    Author     : wan.fairul
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<s:form  name="form1" beanclass="etanah.view.strata.RTHSMaintainBangunanActionBean">


    <c:if test="${fn:length(actionBean.pBangunanL) > 0}">
        <div class="subtitle">
           
            <fieldset class="aras1">
                <legend>Senarai Jadual Petak-petak Bagi Bangunan</legend>
                <br>
                <p>
                <table class="tablecloth">
                    <tr style="width: 100%">
                        <th>Bangunan</th>
                        <th>Senarai Tingkat</th>
                        <th>Petak</th>
                        <th>Keluasan m2</th>
                        <th>Unit-unit Syer</th>
                        <th>Jenis Kegunaan</th>

                        <th>Petak Aksesori</th>
                        <th>Jenis Kegunaan</th>
                        <th>Lokasi Petak Aksesori</th>

                    </tr>
                    <%--<c:if test="${fn:length(actionBean.pBangunanL) > 0}">--%>
                    <c:set var="items" value="0"/>
                    <c:set var="items2" value="0"/>
                    <c:set var="items3" value="0"/>



                    <c:forEach items="${actionBean.pBangunanL}" var="bgn" varStatus="statusB">

                        <tr>
                            <td>${bgn.nama}</td>
                            <c:forEach items="${bgn.senaraiTingkat}" var="tgkt" varStatus="status">

                                <td>${tgkt.tingkat}</td>

                                <c:if test="${fn:length(tgkt.senaraiPetak) > 0}">

                                    <c:forEach items="${tgkt.senaraiPetak}" var="petak" varStatus="statusP">

                                        <td>${petak.nama}</td>
                                        <td>${petak.luas}</td>
                                        <td>${petak.syer}</td>
                                        <td>${petak.kegunaan.nama}</td>



                                        <c:forEach items="${petak.senaraiPetakAksesori}" var="petakAksesori" varStatus="statusPA">
                                            <td>${petakAksesori.nama}</td>
                                            <td>${petakAksesori.kegunaan.nama}</td>
                                            <td>${petakAksesori.lokasi}</td>


                                        <tr>
                                            <c:if test="${not statusPA.last}">

                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </c:if>

                                            <c:if test="${statusPA.last}">
                                            </tr>

                                        </c:if>
                                        <c:set var="items3" value="${items3+1}"/>
                                    </c:forEach>


                                    </tr>

                                    <c:if test="${not statusP.last}">

                                        <td>&nbsp;</td>
                                        <td> <c:if test="${statusP.count eq 1}">

                                                <s:checkbox name="tingkatMezanin[${items2}]" value="${bgn.tingkatMezanin.idTingkat}" disabled="true"></s:checkbox> Mezanin

                                            </c:if>
                                        </td>

                                    </c:if>

                                    <c:if test="${ statusP.last}">


                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td
                                        <td>&nbsp;</td>


                                    </c:if>

                                    <c:if test="${statusP.last}">
                                        <td>&nbsp;</td>
                                        <td> <c:if test="${statusPA.count eq 1}">
                                                <s:checkbox name="tingkatMezanin[${items2}]" value="${bgn.tingkatMezanin.idTingkat}" disabled="true"></s:checkbox> Mezanin
                                            </c:if>
                                        </td>
                                    </c:if>
                                    <c:set var="items" value="${items+1}"/>
                                </c:forEach>
                            </c:if>

                            <c:if test="${fn:length(tgkt.senaraiPetak) == 0}">
                                <tr>
                                    <td>&nbsp;</td>
                                    <td>&nbsp;</td>
                                    <td>&nbsp;</td>

                                </c:if>
                            </tr>
                            <c:if test="${not status.last}">
                                <tr>
                                    <td>&nbsp;</td>
                                </c:if>

                                <c:set var="items2" value="${items2+1}"/>

                            </c:forEach>
                        </tr>
                    </c:forEach>
                </table>

            </fieldset>

        </div>
    </c:if>


</s:form>