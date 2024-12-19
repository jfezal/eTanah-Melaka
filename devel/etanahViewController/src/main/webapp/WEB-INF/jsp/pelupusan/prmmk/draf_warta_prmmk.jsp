<%-- 
    Document   : draf_warta_prmmk
    Created on : Sept 12, 2012, 11:33:19 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>

<s:form beanclass="etanah.view.stripes.pelupusan.DrafWartaPRMMKActionBean">

    <s:messages/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Draf Warta Rizab Tanah</legend>
            <p>
                <label>Tujuan Rizab :</label>
                <s:text name="tujuanRizab" size="50" readonly="true"/>
            </p>

            <p>
                <label>Daerah :</label>
                <s:text name="daerah" size="50" readonly="true"/>
            </p>
            <p>
                <label>Mukim/Pekan/Bandar :</label>
                <s:text name="mukimBandarPekan" readonly="true" size="50"/>
            </p>
            <p>
                <label>No.Lot :</label>
                <s:text name="noLot" size="10" readonly="true"/>
            </p>
            <p>
                <label>Keluasan Tanah :</label>
                <s:text name="keluasanTanah" size="20" readonly="true"/>

                ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}

            </p>
            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'MMRE'} || ${actionBean.permohonan.kodUrusan.kod ne 'WMRE'} || ${actionBean.permohonan.kodUrusan.kod ne 'BMRE'} ">
                <p>
                    <label>Pegawai Pengawal :</label>
                    <s:text name="peg_pengawal" size="50" readonly="true"/>
                </p>
                <p>
                    <label>Pengawal Penyelengara :</label>
                    <s:text name="peg_penyelengara" size="50"/>
                </p>
            </c:if>
            <p>
                <label>No.Pelan Akui/Pelan Warta :</label>
                <s:text name="noPelanAkui" size="10"/>
            </p>
           <%-- <p>
                <label>Tarikh Penyediaan Warta :</label>
                <s:text name="trizabPermohonan.tarikhPenyediaanWarta"  class="datepicker" id="tarikh" formatPattern="dd/MM/yyyy"/>
            </p>--%>
            <p>
                <label>Tarikh Pengesahan Warta :</label>
                <s:text name="trizabPermohonan.tarikhPengesahanWarta"  class="datepicker" id="tarikh1" formatPattern="dd/MM/yyyy"/>
            </p>
            <br>
            <p>
                <label>&nbsp;</label>
                <s:button name="simpanWartaRizabTanah" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
            </p>
        </fieldset >
    </div>

</s:form>




