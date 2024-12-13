<%-- 
    Document   : draf_warta_pembatalan_tanah_rizab
    Created on : May 14, 2010, 2:54:41 PM
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
            <legend>Maklumat Draf Warta Pembatalan Rizab Tanah</legend>
            <p>
                <label>No.Warta Terdahulu :</label>
                <s:text name="warta" size="50" readonly="false"/>
            </p>
            <p>
                <label>Tarikh Asal :</label>
                <s:text name="tarikhAsal" class ="datepicker" readonly="false"/>
            </p>
            <p>
                <label>Tujuan Rizab Asal :</label>
                <s:text name="tujuanRizab" size="50" readonly="true"/>
            </p>
            <p>
                <label>Jawatan :</label>
                <s:text name="peg_penyelengara" size="50"/>
            </p>
            <%--            <p>
                            <label>No.Warta Cadangan  :</label>
                            <s:text name="noWartaCadangan" size="50"/>
                        </p>
                        <p>
                            <label>Tarikh Baru :</label>
                            <s:text name="tarikhBaru" class ="datepicker"/>
                        </p>--%>
            <p>
                <label>Daerah :</label>
                ${actionBean.daerah}
            </p>
            <p>
                <label>Mukim/Pekan/Bandar :</label>
                ${actionBean.mukimBandarPekan}
            </p>
            <p>
                <label>No.Pelan Warta :</label>
                ${actionBean.noPelanAkui}
            </p>
            <p>
                <label>No.Lot :</label>
                ${actionBean.noLot}
            </p>
            <p>
                <label>Keluasan Tanah :</label>
                ${actionBean.keluasanTanah} 
                <c:if test="${actionBean.kod_UOM eq 'H'}">
                    Hektar
                </c:if>
                <c:if test="${actionBean.kod_UOM eq 'M'}">
                    Meter Persegi
                </c:if>
            </p>
            <%--            <p>
                            <label>Tarikh :</label>
                            <s:text name="tarikh"  class="datepicker" id="tarikh" size="20"/>
                        </p><br>
                        <p>--%>
            <label>&nbsp;</label>
            <s:button name="simpanWartaRizabTanah1" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
            </p>
        </fieldset >
    </div>

</s:form>
