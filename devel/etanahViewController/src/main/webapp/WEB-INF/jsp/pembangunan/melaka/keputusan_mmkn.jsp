<%-- 
    Document   : keputusan_mmkn
    Created on : Dec 22, 2010, 10:23:00 AM
    Author     : nursyazwani
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
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
        text-align:right;
        width:32em;
    }
    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
        width:20em;
    }
</style>

<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>

<s:form beanclass="etanah.view.stripes.pembangunan.KeputusanMMKNActionBean">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Keputusan MMKN</legend>
                <%--<p align="center"><font color="#003194"><b>Keputusan : ${actionBean.fasaPermohonan.keputusan.nama}</b></font></p>--%>
                <br><br>
                <table align="center" width="80%" border="0">
                    <tr>
                        <td align="right" width="50%"><font color="#003194"><b>Keputusan :</b></font></td>
                        <td>${actionBean.permohonan.keputusan.nama} </td>
                    </tr>
                    <tr>
                        <td align="right"><font color="#003194"><b>Ulasan : </b></font></td>
                        <%--<td>${actionBean.fasaPermohonan.ulasan} </td>--%>
                        <td>${actionBean.permohonanRujukanLuar.ulasan} </td>
                    </tr>
                </table>              
                    <br><br>
        </fieldset>
    </div>
</s:form>
