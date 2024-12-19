<%-- 
    Document   : draf_warta_tanah_rizab
    Created on : Jul 14, 2012, 1:43:19 PM
    Author     : Orogenic
  edited by shyazli : 09082012
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

<s:form beanclass="etanah.view.stripes.pelupusan.DrafWartaTanahRizabActionBean">

    <s:messages/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Draf Warta Rizab Tanah</legend>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'JMRE'}">
                <p>
                <label>Jadual :</label>
                ${actionBean.tujuanRizab}
                </p> 
            </c:if>
            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'JMRE'}">
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
            
		<c:if test="${actionBean.permohonan.kodUrusan.kod ne 'MMRE' or actionBean.permohonan.kodUrusan.kod ne 'WMRE' or actionBean.permohonan.kodUrusan.kod ne 'BMRE'}">
                    <p>
                        <label>Pegawai Pengawal :</label>
                        <s:text name="peg_pengawal" size="50"/>
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
                <p>
                    <label>No.Warta :</label>
                    <s:text name="warta" size="10"/>
                </p>
            
            
            </c:if>
            <p>
                <label>Tarikh Penyediaan Warta :</label>
                <s:text name="trizabPermohonan.tarikhPenyediaanWarta"  class="datepicker" id="tarikh" formatPattern="dd/MM/yyyy"/>
            </p>
            <p>
                <label>Tarikh Pengesahan Warta :</label>
                <s:text name="trizabPermohonan.tarikhPengesahanWarta"  class="datepicker" id="tarikh1" formatPattern="dd/MM/yyyy"/>
            </p>
            
            
            <br>
            <c:if test="${actionBean.flag}">
            <p>
                <label>&nbsp;</label>
                <s:button name="simpanWartaRizabTanah" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
            </p>
            </c:if>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'JMRE' or actionBean.permohonan.kodUrusan.kod eq 'BMRE'}">
            <p>
                <label>&nbsp;</label>
                <s:button name="simpanWartaRizabTanah" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
            </p>
            </c:if>
        </fieldset >
    </div>

</s:form>