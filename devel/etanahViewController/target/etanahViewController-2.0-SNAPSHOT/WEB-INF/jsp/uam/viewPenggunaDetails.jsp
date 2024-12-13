<%-- 
    Document   : viewPenggunaDetails
    Created on : Aug 1, 2011, 11:11:50 AM
    Author     : Aziz
--%>


<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>

<div id="all">
    <s:form beanclass="etanah.view.uam.ViewPenggunaDetailsBean" name ="viewPenggunaDetails" id ="viewPenggunaDetails">
        <s:messages/>
        <s:errors/>
        <div class="subtitle displaytag">

            <fieldset class="aras1">
                <legend>Butiran Data</legend>

                <p><label>ID Pengguna :</label>&nbsp;${actionBean.vPguna.idPengguna}</p>
                <p><label>Nama :</label>&nbsp;${actionBean.vPguna.nama}</p>
                <p><label>Status :</label>&nbsp;${actionBean.vPguna.status.nama}</p>
                <p><c:if test="${!actionBean.melaka}">
                        <label>Cawangan :</label></c:if>
                    <c:if test="${actionBean.melaka}">
                        <label>Jabatan :</label></c:if>&nbsp;${actionBean.vPguna.kodJabatan.nama}</p>                     
                <p> <c:if test="${!actionBean.melaka}">
                        <label>Jabatan :</label></c:if>
                    <c:if test="${actionBean.melaka}">
                        <label>Unit :</label></c:if>&nbsp;${actionBean.vPguna.kodCawangan.name}</p>                                 
                <p><label>Telefon Pejabat   :</label>&nbsp;${actionBean.vPguna.noTelefon}</p>
                <p><label>Telefon Bimbit   :</label>&nbsp;${actionBean.vPguna.noTelefonBimbit}</p>
                <p><label>Email   :</label>&nbsp;${actionBean.vPguna.email}</p>
                <p><label>Peranan Utama   :</label>&nbsp;${actionBean.vPguna.perananUtama.nama}</p>
                <br>
                <!--              <p>
                                  <label>&nbsp;</label>
                <s:button name = "kembali" class="btn" value="Kembali" onclick="history.go(-2)"/>
            </p>-->
            </fieldset>
        </div>
    </s:form>
</div>
