<%--
    Document   : lelong_tggh_view
    Created on : Mar 8, 2011, 6:39:59 PM
    Author     : mdizzat.mashrom
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<s:form beanclass="etanah.view.stripes.lelong.TangguhTetapTarikhLelongActionBean">
    <p>
        <s:messages />
        <s:errors />&nbsp;
    </p>


    <fieldset class="aras1">
        <legend>
            Maklumat Perintah Jualan
        </legend>


        <c:if test="${actionBean.lelong.bil ne null}">
            <p>
                <label>Status lelongan :</label>
                <c:if test="${actionBean.lelong.bil eq '1'}">
                    Kali Pertama
                </c:if>
                <c:if test="${actionBean.lelong.bil eq '2'}">
                    Kali Kedua
                </c:if>
                <c:if test="${actionBean.lelong.bil eq '3'}">
                    Kali Ketiga
                </c:if>
            </p><br>
        </c:if>
        <table>
            <tr>
                <td>
                    <label>Tarikh Lelongan Awam :</label>
                </td>
                <td>
                    ${actionBean.tarikhLelong} &nbsp;
                    &nbsp;
                </td>
            </tr>
            <tr>
                <td>
                    <label>Masa Lelongan Awam :</label>
                </td>
                <td>
                    ${actionBean.jam}:${actionBean.minit}:${actionBean.ampm}
                </td>
            </tr>
            <tr>
                <td valign="top">
                    <label>Tempat :</label>
                </td>
                <td>
                    ${actionBean.lelong.tempat}
                </td>
            </tr>

            <tr>
                <td>
                    <label>Tarikh Akhir Bayaran : </label>
                </td>
                <td>
                    ${actionBean.tarikhAkhirBayaran}
                </td>
            </tr>
        </table>
        <br>

        <%--for negori Melako jo--%>
        <c:if test="${actionBean.negori eq true}">
            <p>
                <label>Jumlah Keseluruhan Hutang: </label>RM
                <s:format formatPattern="#,##0.00" value="${actionBean.amaunTunggakan}" />
            </p><br>
        </c:if>
        <p align="center">
            <c:if test="${same eq false}">
                <display:table class="tablecloth" name="${actionBean.listLel}" id="line">
                    <display:column title="Bil" sortable="true" >${line_rowNum}</display:column>
                    <display:column title="IDHakmilik" property="hakmilikPermohonan.hakmilik.idHakmilik" />
                    <display:column title="Harga Rizab" >
                        RM <s:format formatPattern="#,##0.00" value="${line.hargaRizab}" />
                    </display:column>
                    <display:column title="Deposit">
                        RM <s:format formatPattern="#,##0.00" value="${line.deposit}" />
                    </display:column>
                </display:table>
            </c:if>
            <c:if test="${same eq true}">
                <display:table class="tablecloth" name="${actionBean.listLelongan}" id="line">
                    <display:column title="Bil" sortable="true" >${line_rowNum}</display:column>
                    <display:column title="IDHakmilik" value="${actionBean.idHak}"/>
                    <display:column title="Harga Rizab" >
                        RM <s:format formatPattern="#,##0.00" value="${actionBean.enkuiri.hargaRizab}" />
                    </display:column>
                    <display:column title="Deposit">
                        RM <s:format formatPattern="#,##0.00" value="${actionBean.enkuiri.deposit}" />
                    </display:column>
                </display:table>
            </c:if>
        </p>
    </fieldset>

    <c:if test="${fn:length(actionBean.senaraiEnkuiri3)>0}">
        <fieldset class="aras1">
            <legend>
                Sejarah Siasatan
            </legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.senaraiEnkuiri3}" id="line">
                    <display:column title="Bil" sortable="true" >${line_rowNum}</display:column>
                    <display:column title="Tarikh"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhEnkuiri}"/></display:column>
                    <display:column title="Masa"><fmt:formatDate pattern="hh:mm aaa" value="${line.tarikhEnkuiri}"/></display:column>
                    <display:column property="tempat" title="Tempat" class="nama${line_rowNum}"/>
                    <display:column property="perkara" title="Perkara" class="nama${line_rowNum}"/>
                    <display:column property="status.nama" title="Status" class=""/>
                    <display:column property="ulasanPegawai" title="Ulasan" class="nama${line_rowNum}"/>
                </display:table>
            </div>
        </fieldset>
    </c:if>
    <c:if test="${fn:length(actionBean.senaraiLelongan3)>0}">
        <fieldset class="aras1">
            <legend>
                Sejarah Lelongan
            </legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.senaraiLelongan3}" id="line" >
                    <display:column title="Bil" sortable="true" >${line_rowNum}</display:column>
                    <display:column title="IDHakmilik" property="hakmilikPermohonan.hakmilik.idHakmilik"/>
                    <display:column title="Tarikh Lelongan Awam"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhLelong}"/></display:column>
                    <display:column title="Masa"><fmt:formatDate pattern="hh:mm aaa" value="${line.tarikhLelong}"/></display:column>
                    <display:column property="tempat" title="Tempat" class="nama${line_rowNum}"/>
                    <display:column title="Harga Rizab (RM)"><div align="right">
                            <fmt:formatNumber pattern="#,##0.00" value="${line.hargaRizab}"/>
                        </div></display:column>
                    <display:column title="Deposit (RM)"><div align="right">
                            <fmt:formatNumber pattern="#,##0.00" value="${line.deposit}"/>
                        </div></display:column>
                    <display:column title="Tarikh Akhir Bayaran"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhAkhirBayaran}"/></display:column>
                    <display:column property="kodStatusLelongan.nama" title="Status" class=""/>
                    <%--<display:column title="Ulasan" class=""/>--%>
                </display:table>
            </div>
        </fieldset>
    </c:if>
</s:form>