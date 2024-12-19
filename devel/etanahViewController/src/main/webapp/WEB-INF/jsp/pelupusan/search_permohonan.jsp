<%-- 
    Document   : search_permohonan
    Created on : 15-Sep-2009, 15:37:26
    Author     : md.nurfikri
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <form:form beanclass="com.etanah.stripes.PermohonanActionBean">            
            <fieldset>
                <legend>
                    Kategori Permohonan
                </legend>
                <table width="100%">
                    <tr>
                        <td>&nbsp;</td>
                        <td class="normaltext">Id Permohonan : <s:text name="permohonan.idPermohonan"/> </td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td><s:submit name="search" value="Cari"/></td>
                    </tr>                    
                    <tr>
                        <td>&nbsp;</td>
                        <td>

                            <%-- <table border="0" cellpadding="2" cellspacing="1" bgcolor="#737991" width="100%">
                                 <tr bgcolor="#898EA2" class="normalwhite">
                                     <td>Bil</td>
                                     <td>Id Permohonan</td>
                                     <td>No Permohonan</td>
                                 </tr>
                                 <c:forEach items="${actionBean.myList}" var="info" varStatus="line">
                                     <tr bgcolor="#FFFFFF">
                                         <td width="5%">${line.count}</td>
                                         <td width="25%">${info.idPermohonan}</td>
                                         <td width="25%">${info.noPermohonan}</td>
                                     </tr>
                                 </c:forEach>
                             </table>--%>
                            <display:table name="${actionBean.myList}" id="line" class="normaltext">
                                <display:column title="Bil">${line_rowNum}</display:column>
                                <display:column property="idPermohonan" title="ID Permohonan" href="pmohonan?view&stageId=${actionBean.stageId}&txnCode=${actionBean.txnCode}" paramId="permohonan.idPermohonan" paramProperty="idPermohonan"/>
                            </display:table>
                        </td>
                    </tr>


                    <tr>
                        <td></td>
                        <td>
                            <c:if test="${fn:length(actionBean.myList) > 0}">
                                <table width="724" border="0" align="center" cellpadding="0" cellspacing="0" class="normaltext">
                                    <tr>
                                        <td width="320">&nbsp;</td>
                                        <td width="300">
                                            <div align="center">
                                                <c:set var="prev" value="#" scope="page"/>
                                                <c:if test="${actionBean.__pg_start > 0}">
                                                    <c:set var="prev" value="?__pg_start=${actionBean.__pg_start-1}&__pg_max_records=${actionBean.__pg_max_records}" scope="page"/>
                                                </c:if>
                                                <c:set var="next" value="#" scope="page"/>
                                                <c:if test="${(actionBean.__pg_start*actionBean.__pg_max_records+actionBean.__pg_max_records) < actionBean.__pg_total_records}">
                                                    <c:set var="to" value="${actionBean.__pg_start*actionBean.__pg_max_records+actionBean.__pg_max_records}" scope="page"/>
                                                    <c:set var="next" value="?__pg_start=${actionBean.__pg_start+1}&__pg_max_records=${actionBean.__pg_max_records}" scope="page"/>
                                                </c:if>
                                                <c:if test="${(actionBean.__pg_start*actionBean.__pg_max_records+actionBean.__pg_max_records) > actionBean.__pg_total_records}">
                                                    <c:set var="to" value="${actionBean.__pg_total_records}" scope="page"/>
                                                </c:if>
                                                &nbsp;<a href="${prev}">Prev</a>&nbsp;
										Paparan ${actionBean.__pg_start*actionBean.__pg_max_records+1} - ${to} dari ${actionBean.__pg_total_records} rekod.
                                                &nbsp;<a href="${next}">Next</a>&nbsp;
                                            </div>
                                        </td>
                                        <td width="320">
                                            <div align="right">Rekod per mukasurat
                                                <s:select name="__pg_max_records" onchange="this.form.submit();">
                                                    <s:option>5</s:option>
                                                    <s:option>10</s:option>
                                                    <s:option>15</s:option>
                                                    <s:option>25</s:option>
                                                    <s:option>30</s:option>
                                                    <s:option>50</s:option>
                                                </s:select>
                                            </div>
                                        </td>
                                    </tr>
                                </table>
                            </c:if>
                        </td>
                    </tr>
                </table>
            </fieldset>
        </form:form>
    </body>
</html>
