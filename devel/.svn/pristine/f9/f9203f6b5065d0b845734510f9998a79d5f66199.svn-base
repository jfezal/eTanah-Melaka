<%--
    Document   : pertanyaan_cukai_4
    Created on : Dec 14, 2009, 2:39:10 PM
    Author     : nurfaizati
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

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
            width:15em;
        }

   #tdDisplay {
            color:black;
            font-size:13px;
            font-weight:normal;
            float:left;
            width:60em;
        }
</style>

<div class="subtitle">
    <%--<s:form beanclass="etanah.view.stripes.hasil.PertanyaanHakmilikActionBean">--%>
        <s:form beanclass="etanah.view.stripes.common.CarianHakmilik">


        <%--<s:hidden name="hakmilik.idHakmilik"/>--%>
        <s:hidden name ="dokumenKewangan.idDokumenKewangan"/>

        <br>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Maklumat Bayaran
                </legend>
                <table border="0" width="100%">
                <tr>
                    <td id="tdLabel">No Resit :</td>
                    <c:if test="${actionBean.dokumenKewangan.noRujukanManual eq null}">
                        <td id="tdDisplay">${actionBean.dokumenKewangan.idDokumenKewangan}&nbsp;</td>
                    </c:if>
                    <c:if test="${actionBean.dokumenKewangan.noRujukanManual ne null}">
                        <td id="tdDisplay">${actionBean.dokumenKewangan.noRujukanManual}&nbsp;</td>
                    </c:if>
                </tr>
                <tr>
                    <td id="tdLabel">Tarikh Bayaran :</td>
                    <c:if test="${actionBean.dokumenKewangan.noRujukanManual eq null}">
                        <td id="tdDisplay"><fmt:formatDate value="${actionBean.dokumenKewangan.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy hh:mm aa"/>&nbsp;</td>
                    </c:if>
                    <c:if test="${actionBean.dokumenKewangan.noRujukanManual ne null}">
                        <td id="tdDisplay"><fmt:formatDate value="${actionBean.dokumenKewangan.tarikhTransaksi}" pattern="dd/MM/yyyy hh:mm aa"/>&nbsp;</td>
                    </c:if>
                </tr>
                <c:if test="${actionBean.dokumenKewangan.noRujukanManual ne null and actionBean.dokumenKewangan.agensiKutipan eq null}">
                    <tr>
                        <td id="tdLabel">Juruwang Kew. 38 :</td>
                        <td id="tdDisplay">${actionBean.dokumenKewangan.isuKepada}&nbsp;</td>
                    </tr>
                </c:if>
                <tr>
                    <td id="tdLabel">Agensi Pengutip :</td>
                    <c:set var="akaun" value="-"/>
                    <c:if test="${actionBean.dokumenKewangan.agensiKutipan ne null}">
                        <c:set var="akaun" value="${actionBean.dokumenKewangan.agensiKutipan.agensiKutipan.nama}"/>
                    </c:if>
                    <td id="tdDisplay">${akaun}&nbsp;</td>
                </tr>
                <c:if test="${actionBean.dokumenKewangan.agensiKutipan ne null}">
                    <tr>
                        <td id="tdLabel">Lokasi Agensi Pengutip :</td>
                        <td id="tdDisplay">${actionBean.dokumenKewangan.agensiKutipan.nama}&nbsp;</td>
                    </tr>
                </c:if>
                <tr>
                    <td id="tdLabel">Catatan :</td>
                    <td id="tdDisplay">
                        <c:if test="${actionBean.dokumenKewangan.catatan ne null}">${actionBean.dokumenKewangan.catatan}&nbsp;</c:if>
                        <c:if test="${actionBean.dokumenKewangan.catatan eq null}">- &nbsp;</c:if>
                    </td>
                </tr>
                </table>

                <p>
                    <label>&nbsp;</label>
                    <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                </p>

            </fieldset>

        </div>
    </s:form>
</div>



