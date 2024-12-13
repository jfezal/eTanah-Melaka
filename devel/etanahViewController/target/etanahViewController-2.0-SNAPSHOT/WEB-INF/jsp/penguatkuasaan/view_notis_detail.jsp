<%-- 
    Document   : view_notis_detail
    Created on : Mar 22, 2011, 11:35:10 PM
    Author     : latifah.iskak
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

<s:form  beanclass="etanah.view.penguatkuasaan.NotisBuktiPenyampaianActionBean">

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Penghantaran Notis Siasatan
            </legend>

            <div class="content">
                <p>
                    <label>Jenis Notis :</label>&nbsp;
                    ${actionBean.jenisNotis}&nbsp;
                </p>
                <p>
                    <label>Nama Penghantar Notis :</label>&nbsp;
                    ${actionBean.penghantarNotis}&nbsp;
                    [No.K/P:${actionBean.noPengenalan}]

                </p>
                <p>
                    <label>Nama Penerima Notis :</label>&nbsp;
                    ${actionBean.penerimaNotis}&nbsp;
                </p>
                <p>
                    <label>Status Penyampaian :</label>&nbsp;
                    ${actionBean.statusPenyampaian}&nbsp;
                </p>
                <p>
                    <label>Cara Penghantaran :</label>&nbsp;
                    ${actionBean.caraPenghantaran}&nbsp;
                </p>
                <p>
                    <label>Tarikh Hantar :</label>&nbsp;
                    ${actionBean.tarikhHantar}&nbsp;
                </p>
                <p>
                    <label>Tarikh Terima :</label>&nbsp;
                    ${actionBean.tarikhTerima}&nbsp;
                </p>
                <c:if test="${actionBean.kodNegeri eq '04' && actionBean.permohonan.kodUrusan.kod ne '49'}">
                    <p>
                        <label>Tarikh Tampal :</label>&nbsp;
                        ${actionBean.tarikhTampal}&nbsp;
                    </p>
                    <p>
                        <label>Nama Penampal :</label>&nbsp;
                        ${actionBean.namaTampal}&nbsp;
                    </p>
                    <p>
                        <label>Lokasi Penampal :</label>&nbsp;
                        ${actionBean.tempatTampal}&nbsp;
                    </p>
                </c:if>
                <c:if test="${actionBean.kodNegeri eq '05' && (actionBean.permohonan.kodUrusan.kod eq '351' || actionBean.permohonan.kodUrusan.kod eq '352')}">
                    <p>
                        <label>Tarikh Tampal :</label>&nbsp;
                        ${actionBean.tarikhTampal}&nbsp;
                    </p>
                    <p>
                        <label>Nama Penampal :</label>&nbsp;
                        ${actionBean.namaTampal}&nbsp;
                    </p>
                    <p>
                        <label>Lokasi Penampal :</label>&nbsp;
                        ${actionBean.tempatTampal}&nbsp;
                    </p>
                </c:if>

                <p>
                    <label>Catatan :</label>&nbsp;
                    ${actionBean.catatan}&nbsp;
                </p>


                <p><label>&nbsp;</label>
                    <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                </p>
                <br>
            </div>
        </fieldset>
    </div>
</s:form>
