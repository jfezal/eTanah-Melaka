<%--
    Document   : sijil_pengecualian_ukur_view
    Created on : Jun 22, 2011, 10:10:49 PM
    Author     : latifah.iskak
--%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/src/main/webapp/pub/js/datetimepicker.js"></script>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/JavaScript/datetimepicker.js"></script>
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
        width:20em;
    }

    #tdAyat {
        color:#003194;
        display:block;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        margin-left:30px;
        margin-right:0.5em;
        text-align:justify;
        width:25em;
    }

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
        width:20em;
    }
</style>
<%
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
    String date_show = sdf1.format(new Date());
%>
<s:form beanclass="etanah.view.penguatkuasaan.SijilPengecualianUkurActionBean">
    <s:messages/>
    <s:errors/>
    <%@page contentType="text/html" import="java.util.*" %>
    <%--    <s:hidden name="kandunganK.kertas.idKertas"/>--%>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend></legend>
            <%--<div class="content"  align="center">--%>
            <table border="0" width="80%" align="center">
                <tr><td align="center"><b><font color="#003194">SIJIL PENGECUALIAN BAYARAN UKUR</font></b></td></tr>
            </table><br>
            <table border="0" width="80%" align="center">
                <tr><td>
                        <table border="0" width="60%" align="center" cellspacing="5">

                            <tr>
                                <td id="tdLabel"><b>Permohonan Ukur No. : </b></td>
                                <s:hidden name="permohonanUkur.idMohonUkur"/>
                                <td id="tdDisplay">${actionBean.permohonanUkur.noPermohonanUkur} &nbsp;</td>
                            </tr>
                            <tr>
                                <td id="tdLabel"><b>Mukim/Pekan/Bandar : </b></td>
                                <td id="tdDisplay">${actionBean.hakmilik.bandarPekanMukim.nama} &nbsp;</td>
                            </tr>
                            <tr>
                                <td id="tdLabel"><b>Daerah : </b></td>
                                <td id="tdDisplay">${actionBean.hakmilik.daerah.nama} &nbsp;</td>
                            <tr>
                                <td id="tdLabel"><b>Fail Pejabat Tanah : </b></td>
                                <td id="tdDisplay">${actionBean.permohonan.idPermohonan} &nbsp;</td>
                            </tr>
                            <tr>
                                <td id="tdLabel"><b>Fail Pejabat Tanah dan Galian : </b></td>
                                <td id="tdDisplay">${actionBean.permohonanUkur.noFailISO}</td>
                            </tr>
                            <tr>
                                <td id="tdLabel"><b>Tujuan Kegunaan Tanah : </b></td>
                                <td id="tdDisplay">${actionBean.hakmilik.kegunaanTanah.nama}</td>
                            </tr>
                            <tr>
                                <td id="tdLabel"><b>Tarikh Perakuan : </b></td>
                                <td id="tdDisplay"><fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.permohonanUkur.tarikhPerakuan}"/>&nbsp;</td>
                            </tr>
                            <tr>
                                <td id="tdLabel"><b>Tujuan : </b></td>
                                <td id="tdDisplay">${actionBean.ulasan}</td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td>
                        <table border="0" width="60%" align="center">
                            <tr>
                                <td id="tdAyat"><b><font color="black">Pada menjalankan kuasa-kuasa yang diberi kepada saya oleh Seksyen 4(2) Perintah Kanun Tanah Negara (Bayaran Ukur)1965, saya dengan ini mengesahkan bahawa permohonan ukur tersebut di atas adalah untuk tujuan awam, maka dengan ini dikecualikan daripada semua bayaran ukur di bawah perintah ini.</font></b></td>
                            </tr>

                        </table>
                    </td>
                </tr>
            </table>

        </fieldset>
    </div>
</s:form>