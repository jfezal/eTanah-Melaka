<%--
    Document   : testing1
    Created on : Dec 16, 2009, 10:42:01 AM
    Author     : wan.fairul
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.strata.PermohonanStrataActionBean">

    <div class="content" align="center">
        <table class="tablecloth">
            <tr>
                <th>&nbsp;</th><th>Jenis Tanah</th><th>Nombor Lot</th><th>Kegunaan</th>
            </tr>
            <tr>
                <th>
                    Utara
                </th>
                <td>
                    <c:if test="${actionBean.pemilik.sempadanUtaraMilikKerajaan ne 'T'}">Kerajaan</c:if>
                    <c:if test="${actionBean.pemilik.sempadanUtaraMilikKerajaan eq 'T'}">Milik</c:if>
                </td>
                <td>
                    <c:if test="${actionBean.pemilik.sempadanUtaraNoLot ne null}"> ${actionBean.pemilik.sempadanUtaraNoLot}&nbsp; </c:if>
                    <c:if test="${actionBean.pemilik.sempadanUtaraNoLot eq null}"> Tiada Data </c:if>
                </td>
                <td>
                    <c:if test="${actionBean.pemilik.sempadanUtaraKegunaan ne null}"> ${actionBean.pemilik.sempadanUtaraKegunaan}&nbsp; </c:if>
                    <c:if test="${actionBean.pemilik.sempadanUtaraKegunaan eq null}"> Tiada Data </c:if>
                </td>
            </tr>
            <tr>
                <th>
                    Selatan
                </th>
                <td>
                    <c:if test="${actionBean.pemilik.sempadanSelatanMilikKerajaan ne 'T'}">Kerajaan</c:if>
                    <c:if test="${actionBean.pemilik.sempadanSelatanMilikKerajaan eq 'T'}">Milik</c:if>
                </td>
                <td>
                    <c:if test="${actionBean.pemilik.sempadanSelatanNoLot ne null}"> ${actionBean.pemilik.sempadanSelatanNoLot}&nbsp; </c:if>
                    <c:if test="${actionBean.pemilik.sempadanSelatanNoLot eq null}"> Tiada Data </c:if>
                </td>
                <td>
                    <c:if test="${actionBean.pemilik.sempadanSelatanKegunaan ne null}"> ${actionBean.pemilik.sempadanSelatanKegunaan}&nbsp; </c:if>
                    <c:if test="${actionBean.pemilik.sempadanSelatanKegunaan eq null}"> Tiada Data </c:if>
                </td>
            </tr>
            <tr>
                <th>
                    Timur
                </th>
                <td>
                    <c:if test="${actionBean.pemilik.sempadanTimurMilikKerajaan ne 'T'}">Kerajaan</c:if>
                    <c:if test="${actionBean.pemilik.sempadanTimurMilikKerajaan eq 'T'}">Milik</c:if>
                </td>
                <td>
                    <c:if test="${actionBean.pemilik.sempadanTimurNoLot ne null}"> ${actionBean.pemilik.sempadanTimurNoLot}&nbsp; </c:if>
                    <c:if test="${actionBean.pemilik.sempadanTimurNoLot eq null}"> Tiada Data </c:if>
                </td>
                <td>
                    <c:if test="${actionBean.pemilik.sempadanTimurKegunaan ne null}"> ${actionBean.pemilik.sempadanTimurKegunaan}&nbsp; </c:if>
                    <c:if test="${actionBean.pemilik.sempadanTimurKegunaan eq null}"> Tiada Data </c:if>
                </td>
            </tr><tr>
                <th>
                    Barat
                </th>
                <td>
                    <c:if test="${actionBean.pemilik.sempadanBaratMilikKerajaan ne 'T'}">Kerajaan</c:if>
                    <c:if test="${actionBean.pemilik.sempadanBaratMilikKerajaan eq 'T'}">Milik</c:if>
                </td>
                <td>
                    <c:if test="${actionBean.pemilik.sempadanBaratNoLot ne null}"> ${actionBean.pemilik.sempadanBaratNoLot}&nbsp; </c:if>
                    <c:if test="${actionBean.pemilik.sempadanBaratNoLot eq null}"> Tiada Data </c:if>
                </td>
                <td>
                    <c:if test="${actionBean.pemilik.sempadanBaratKegunaan ne null}"> ${actionBean.pemilik.sempadanBaratKegunaan}&nbsp; </c:if>
                    <c:if test="${actionBean.pemilik.sempadanBaratKegunaan eq null}"> Tiada Data </c:if>
                </td>
            </tr>
        </table>
    </div>
</s:form>