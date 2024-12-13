<%-- 
    Document   : view_barang_detail
    Created on : Jul 13, 2010, 9:26:59 AM
    Author     : nurshahida.radzi
    Modify by  : sitifariza.hanim (18DIS2010)
    Modify by : ctzainal 16june 2011
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>

<s:form  beanclass="etanah.view.penguatkuasaan.MaklumatBantahanActionBean">

    <div class="subtitle">
        <fieldset class="aras1">
            <legend> Maklumat Bantahan</legend>

            <p>
                <label>Kategori  :</label>&nbsp;
                <c:if test="${actionBean.permohonanPihakMembantah.kategoriPihakMembantah eq 'L'}">
                    Bukan Pihak Berkepentingan&nbsp;
                </c:if>
                <c:if test="${actionBean.permohonanPihakMembantah.kategoriPihakMembantah eq 'P'}">
                    Pihak Berkepentingan&nbsp;
                </c:if>
            </p>
            <p>
                <label>Nama :</label>&nbsp;
                ${actionBean.permohonanPihakMembantah.nama}&nbsp;
            </p>
            <p>
                <label>Jantina :</label>&nbsp;
                ${actionBean.permohonanPihakMembantah.jantina.nama}&nbsp;

            </p>
            <p>
                <label>Jenis Pengenalan :</label>&nbsp;
                ${actionBean.permohonanPihakMembantah.jenisPengenalan.nama}&nbsp;
            </p>
            <p>
                <label>No. Pengenalan :</label>&nbsp;
                ${actionBean.permohonanPihakMembantah.noPengenalan}&nbsp;
            </p>
            <p>
                <label>Tarikh Bantah :</label>&nbsp;
                <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.permohonanPihakMembantah.tarikhBantahan}"/>&nbsp;
            </p>
            <p>
                <label>Telefon Bimbit :</label>&nbsp;
                ${actionBean.permohonanPihakMembantah.noTelefon1}
            </p>
            <p>
                <label>Telefon Pejabat :</label>&nbsp;
                ${actionBean.permohonanPihakMembantah.noTelefon2}
            </p>
            <p>
                <label>Alamat :</label>&nbsp;
                ${actionBean.permohonanPihakMembantah.alamat1}
            </p>&nbsp;
            <p>
                <label>&nbsp;</label>&nbsp;
                ${actionBean.permohonanPihakMembantah.alamat2}
            </p>&nbsp;
            <p>
                <label>&nbsp;</label>&nbsp;
                ${actionBean.permohonanPihakMembantah.alamat3}
            </p>&nbsp;
            <p>
                <label>&nbsp;</label>&nbsp;
                ${actionBean.permohonanPihakMembantah.alamat4}
            </p>&nbsp;
            <p>
                <label>Poskod :</label>&nbsp;
                ${actionBean.permohonanPihakMembantah.poskod}
            </p>&nbsp;
            <p>
                <label>Negeri :</label>&nbsp;
                ${actionBean.permohonanPihakMembantah.negeri.nama}
            </p>&nbsp;



            <br>
        </fieldset>

    </div>
</s:form>
