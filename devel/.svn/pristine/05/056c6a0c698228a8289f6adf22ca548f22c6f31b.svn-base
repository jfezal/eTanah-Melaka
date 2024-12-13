<%-- 
    Document   : dev_sebab_pembangunan
    Created on : Nov 4, 2010, 12:24:51 PM
    Author     : nursyazwani
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page import="java.util.List;" %>
<%@ taglib prefix="stripes"
           uri="http://stripes.sourceforge.net/stripes-dynattr.tld"%>

<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<style type="text/css">
    #tdLabel {
        color:#003194;
        display:block;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        margin-left:150px;
        margin-right:0.5em;
        text-align:right;
        width:15em;
        vertical-align:top;
    }

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
        width:40em;
    }
</style>

<s:form beanclass="etanah.view.stripes.pembangunan.SebabPembangunanActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Tujuan Permohonan
            </legend>
            <div class="content" align="center"> 
                <c:if test="${actionBean.kodNegeri eq '05'}">
                    <table width="80%" border="0">
                        <c:if test="${edit}">
                            <tr><td width="16%"><b><font color="#003194">Tujuan Permohonan : </font></b></td>
                                <td><s:textarea name="sebabPermohonan" rows="5" cols="110" class="normal_text"/></td>
                            </tr>
                        </c:if>
                        <c:if test="${!edit}">
                            <tr><td width="16%"><b><font color="#003194"></font></b></td>
                                <td><s:textarea name="sebabPermohonan" rows="5" cols="110" class="normal_text" value = "${actionBean.sebabPermohonan}" readonly = "true" /> &nbsp;</td>
                            </tr>
                        </c:if>
                        <br>
                    </table>
                    <br>
                </c:if>

                <c:if test="${actionBean.kodNegeri eq '04'}">    
                    <%--<c:if test="${(actionBean.mohon.kodUrusan.kod ne 'PSMT' && actionBean.mohon.kodUrusan.kod ne 'PSBT') && actionBean.kodNegeri eq '04'}">--%>
                    <c:if test="${!(actionBean.mohon.kodUrusan.kod eq 'PSMT' || actionBean.mohon.kodUrusan.kod eq 'PSBT')}">
                        <table width="80%" border="0">
                            <c:if test="${edit}">
                                <tr><td width="16%"><b><font color="#003194">Tujuan Permohonan : </font></b></td>
                                    <td><s:textarea name="sebabPermohonan" rows="5" cols="110" class="normal_text"/></td>
                                </tr>
                                <c:if test="${actionBean.mohon.kodUrusan.kod eq 'SBMS'}">
                                    <tr>
                                        <td width="16%"><b><font color="#003194">Lanjut Tempoh Pajakan : </font></b></td>
                                        <td><s:radio name="lanjuttempoh" value="Y"/> Ya
                                            <s:radio name="lanjuttempoh" value="T"/> Tidak                                    
                                        </td>     
                                    </tr>
                                </c:if>
                            </c:if>
                            <c:if test="${!edit}">
                                <tr><td width="16%"><b><font color="#003194">Tujuan Permohonan :</font></b></td>
                                    <td>${actionBean.sebabPermohonan} &nbsp;</td>
                                </tr>
                                <c:if test="${actionBean.mohon.kodUrusan.kod eq 'SBMS'}">
                                    <tr>
                                        <td width="16%"><b><font color="#003194">Lanjut Tempoh Pajakan : </font></b></td>
                                        <td><c:if test="${actionBean.lanjuttempoh eq 'Y'}"> Ya </c:if>
                                            <c:if test="${actionBean.lanjuttempoh eq 'T'}"> Tidak </c:if>
                                            </td>      
                                        </tr>
                                </c:if>
                            </c:if>
                            <br>
                        </table>
                    </c:if>

                    <c:if test="${(actionBean.mohon.kodUrusan.kod eq 'PSMT' || actionBean.mohon.kodUrusan.kod eq 'PSBT')}">
                        <table width="80%" border="0">
                            <c:if test="${edit}">
                                <tr><td width="16%"><b><font color="#003194">Tajuk Permohonan : </font></b></td>
                                    <td><s:textarea name="sebabPermohonan" rows="5" cols="110" class="normal_text"/></td>
                                </tr>
                                <br>
                                <tr>
                                    <td width="16%"><b><font color="#003194">Tujuan Awam : </font></b></td>
                                    <td><s:radio name="catatanMohon" value="0"/> Ya
                                        <s:radio name="catatanMohon" value="1"/> Tidak                                    
                                    </td>
                                </tr>
                            </c:if>
                            <c:if test="${!edit}">
                                <tr><td width="16%"><b><font color="#003194">Tajuk Permohonan :</font></b></td>
                                    <td>${actionBean.sebabPermohonan} &nbsp;</td>
                                </tr>
                                <br>
                                <tr>
                                    <td width="16%"><b><font color="#003194">Tujuan Awam : </font></b></td>
                                    <td><c:if test="${actionBean.catatanMohon eq '0'}"> Ya </c:if>
                                        <c:if test="${actionBean.catatanMohon eq '1'}"> Tidak </c:if>
                                        </td>      
                                    </tr>
                            </c:if>
                            <br>
                        </table>
                    </c:if>
                </c:if>

                <c:if test="${edit}">
                    <p align="center">
                        <s:button class="btn" onclick="doSubmit(this.form, this.name, 'page_div');" name="simpan" value="Simpan"/>
                    </p>
                </c:if>
            </div>
        </fieldset>
    </div>
</s:form>

