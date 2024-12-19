<%-- 
    Document   : notifikasiPembetulanWarta
    Created on : 05-Aug-2010, 11:24:49
    Author     : nordiyana
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<s:form beanclass="etanah.view.stripes.pengambilan.notifikasiActionBean">
<div class="subtitle">
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                      <%--<c:if test="${actionBean.permohonan.kodUrusan.kod eq 'SEK4'}">Warta Seksyen 4</c:if>--%>
                       <%--<c:if test="${actionBean.permohonan.kodUrusan.kod eq '831A'}">Pembetulan</c:if>--%>
                       Notifikasi
                    </legend>
                    <div class="content" align="left">
                        <table align="left"  width="100%">
                           <%-- <tr>
                                <td align="left">Notifikasi</td>

                            </tr
                            <br>--%>
                            <tr>
                                <td align="left">Terdapat Pembetulan Warta </td>

                            </tr>

                        </table>
                    </div>
                </fieldset>
            </div>
                    </div>
</s:form>
