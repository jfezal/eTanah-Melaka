<%-- 
    Document   : keputusanPermohonanHLLP
    Created on : Jun 18, 2010, 9:42:47 AM
    Author     : massita      
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252" language="java"%>
<link type="text/css" href="${pageContext.request.contextPath}/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ui.datetimepicker.js"></script>

<s:form beanclass="etanah.view.stripes.pengambilan.KeputusanPermohonanHLLPActionBean">

    <div class="subtitle">
        <s:errors/>
        <s:messages/>
        <fieldset class="aras1">
            <legend>Keputusan Hak Lalu Lalang Persendirian</legend><br />
        </fieldset >
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <table width="80%">
                <tr align="center">
                    <td><label style=" text-align: left; width:100%; padding: 1em 5em"dir="ltr" >Luluskan Permohonan Hak Lalu Lalang Persendirian</label></td>
                </tr>
                <tr>
                    <td align="center"><s:radio name="keputusan" value="L"/>Ya&nbsp;
                        <s:radio name="keputusan" value="T"/>Tidak&nbsp;</td>
                </tr>
            </table><br />
            
            <p>
                <label>&nbsp;</label>
                    <s:button name="simpanMesyuarat" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
            </p>
        </fieldset >
    </div>
</s:form>
