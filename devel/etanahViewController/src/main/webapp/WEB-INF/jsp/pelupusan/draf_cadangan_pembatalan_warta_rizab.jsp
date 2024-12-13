<%-- 
    Document   : draf_cadangan pembatalan warta_rizab
    Created on : May 14, 2010, 2:55:06 PM
    Author     : sitifariza.hanim
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
            <legend>Maklumat Draf Cadangan Pembatalan Rizab Tanah</legend>
            <p></p>
            <p>
                <label>ID Permohonan Terdahulu :</label>
                <s:text name="id_sblm" size="30" readonly="true"/>
                <%-- <s:button name="carianIdSblm" id="cari" value="Cari" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>--%>
            </p>
            <p>
                <label>No.Warta Terdahulu :</label>
                <s:text name="warta" size="20" />
            </p>            
            <p>
                <label>Tarikh Asal :</label>
                <s:text name="tarikhAsal" class ="datepicker"/>
            </p>
            <p>
                <label>Tujuan Rizab :</label>
                <s:text name="tujuanRizab" size="50"/>
            </p>
            <p>
                <label>Pegawai Pengawal :</label>
                <s:text name="peg_pengawal" size="50"/>
            </p>
            <p>
                <label>Daerah :</label>
                <s:text name="daerah" size="50"/>
            </p>
            <p>
                <label>Mukim/Pekan/Bandar :</label>
                <s:text name="mukimBandarPekan" size="50"/>
            </p>
            <p>
                <label>No.Pelan Warta / No.Pelan Akui:</label>
                <s:text name="noPelanAkui" size="20"/>
            </p>
            <p>
                <label>No.Lot :</label>
                <s:text name="noLot" size="20"/>
            </p>
            <p>
                <label>Keluasan Tanah :</label>
                <s:text name="keluasanTanah" size="20"/>
                ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}
            </p>
<!--            <p>
                <label>Masa :</label>
                <s:text name="masa" size="10"/>AM/PM
            </p>-->
            <p>
                <label>Tarikh :</label>
                <s:text name="tarikhSedia"  class="datepicker" id="tarikhSedia" size="20"/>
            </p><br>
            <p>
                <label>&nbsp;</label>
                
                <s:button name="simpanWartaCadanganPembatalanRizabTanah" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
            </p>
        </fieldset >
    </div>

</s:form>
