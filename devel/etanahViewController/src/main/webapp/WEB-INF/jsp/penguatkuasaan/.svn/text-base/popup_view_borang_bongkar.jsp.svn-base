<%--
    Document   : popup_tambah_laporan_operasi
    Created on : Nov 11, 2011, 11:24:21 AM
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
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<script type="text/javascript">
    




</script>
<s:form beanclass="etanah.view.penguatkuasaan.MaklumatBorangBongkarActionBean">
    <s:messages />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Bongkar
            </legend>
            <div class="content">
                <p>
                    <b>1.&emsp;Butir-butir kenderaan yang dibongkar: </b> &nbsp;
                </p>
                <div class="content" align="center">
                    <display:table name="${actionBean.senaraiKenderaanOks}" id="line" class="tablecloth" cellpadding="0" cellspacing="0">
                        <display:column title="Bil" sortable="true" style="width:5px;">${line_rowNum}</display:column>
                        <display:column title="No.Pendaftaran" property="nomborPendaftaran"/>
                        <display:column title="Warna" property="warna"/>
                        <display:column title="Jenis" property="item"/>
                        <display:column title="No.Casis" property="nomborCasis"/>
                        <display:column title="No.Enjin" property="nomborEnjin"/>
                        <display:column title="No.Siri" property="noSiri"/>
                    </display:table>

                </div>

                <p>
                    <b>2.&emsp;Nama dan alamat suspek kenderaan: </b> &nbsp;
                </p>
                <div class="content" align="center">
                    <display:table name="${actionBean.senaraiKenderaanOks}" id="line" class="tablecloth" cellpadding="0" cellspacing="0">
                        <display:column title="Bil" sortable="true" style="width:5px;">${line_rowNum}</display:column>
                        <display:column title="Nama Suspek" property="namaSuspek" style="text-transform: uppercase"/>
                        <display:column title="Alamat" style="text-transform: uppercase">
                            <c:if test="${line.alamat1Suspek ne null}">${line.alamat1Suspek} ,</c:if>
                            <c:if test="${line.alamat2Suspek ne null}">${line.alamat2Suspek} ,</c:if>
                            <c:if test="${line.poskodSuspek ne null}">${line.poskodSuspek}</c:if>
                            <c:if test="${line.alamat3Suspek ne null}">${line.alamat3Suspek}</c:if>
                        </display:column>
                        <display:column title="No.Pengenalan" property="noPengenalanSuspek"/>
                        <display:column title="No.Telefon" property="noTelSuspek"/>
                    </display:table>
                </div>

                <p>
                    <b>3.&emsp;Anggota polis yang hadir </b> &nbsp;
                </p>
                <div class="content" align="center">
                    <display:table name="${actionBean.senaraiPermohonanRujLuar}" id="line" class="tablecloth" cellpadding="0" cellspacing="0" style="width:50%">
                        <display:column title="Bil" sortable="true" style="width:5px;">${line_rowNum}</display:column>
                        <display:column title="Nama" property="namaPenyedia" style="text-transform: uppercase"/>
                    </display:table>

                </div>

                <p>
                    <b>4.&emsp;Orang yang hadir </b> &nbsp;
                </p>
                <div align="center" >
                    <p>
                        <s:hidden name="rowCountOrangHadir" id="rowCountOrangHadir"/>
                    </p>
                    <display:table name="${actionBean.listBongkarKehadiran}" id="line" class="tablecloth" cellpadding="0" cellspacing="0" style="width:50%">
                        <display:column title="Bil" sortable="true" style="width:5px;">${line_rowNum}</display:column>
                        <display:column title="Nama" property="nama"/>
                        <display:column title="Kod Pengenalan" property="jenisPengenalan.nama" style="text-transform: uppercase"/>
                        <display:column title="No. Pengenalan" property="noPengenalan"/>
                        <display:column title="Hubungan" property="hubungan" />
                    </display:table>
                    <br/>
                </div>
                <p>&nbsp;</p>

                <p>
                    <b>5.&emsp;Tempat, Tarikh dan Masa Bongkar </b> &nbsp;
                </p>
                <p>
                    <label>Tempat Bongkar :</label>
                    ${actionBean.aduanOrangKenaSyak.tempatBongkar}&nbsp;
                </p>
                <p>
                    <label>Tarikh Bongkar :</label>
                    <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.aduanOrangKenaSyak.tarikhBongkar}"/>&nbsp;
                </p>
                <p>
                    <label>Waktu Bongkar :</label>
                    <fmt:formatDate pattern="hh:mm" value="${actionBean.aduanOrangKenaSyak.tarikhBongkar}"/>
                    <fmt:formatDate pattern="aaa" value="${actionBean.aduanOrangKenaSyak.tarikhBongkar}" var="time"/>
                    <c:if test="${time eq 'AM'}">PAGI</c:if>
                    <c:if test="${time eq 'PM'}">PETANG</c:if>
                </p><br>


                <p>
                    <b>6.&emsp;Barang-barang Diambil </b> &nbsp;
                </p>
                <div class="content" align="center">
                    <display:table name="${actionBean.senaraiBarangOks}" id="line" class="tablecloth" cellpadding="0" cellspacing="0" style="width:50%">
                        <display:column title="Bil" sortable="true" style="width:5px;">${line_rowNum}</display:column>
                        <display:column title="Item" property="item"/>
                        <display:column title="Lokasi Tangkapan" property="tempatTangkap"/>
                        <display:column title="Lokasi Bongkar" property="tempatBongkar"/>
                    </display:table>

                </div>


            </div>
            <br/><br/>

        </fieldset>
    </div>
    <p align="center">
        <s:button name="tutup" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
    </p>

</s:form>

