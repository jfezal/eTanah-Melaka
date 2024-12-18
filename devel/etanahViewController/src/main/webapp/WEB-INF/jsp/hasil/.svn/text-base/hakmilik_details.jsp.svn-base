<%-- 
    Document   : hakmilik_details
    Created on : Dec 29, 2009, 10:37:21 PM
    Author     : abu.mansur
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

 <script type="text/javascript">
    $(document).ready(function() {
        $("#close").click( function(){
            setTimeout(function(){
                self.close();
            }, 100);
        });
    });
</script>

<div class="subtitle">
    <s:form beanclass="etanah.view.stripes.common.MaklumatHakmilikSingleActionBean">
    <fieldset class="aras1">
        <legend>
            Maklumat Hakmilik
        </legend>
        <p>
            <label>ID Hakmilik :</label>
            ${actionBean.hakmilik.idHakmilik}&nbsp;
        </p>
        <%--<p>
            <label>Daerah :</label>
           ${actionBean.hakmilik.daerah.nama}&nbsp;
        </p>--%>
        <%--<p>
            <label>Bandar / Pekan / Mukim :</label>
           ${actionBean.hakmilik.bandarPekanMukim.nama}&nbsp;
        </p>--%>
        <%--<p>
            <label>Nombor Lot :</label>
            ${actionBean.hakmilik.noLot}&nbsp;
        </p>--%>
        <p>
            <label>Luas :</label>
            <%--${actionBean.hakmilikPermohonan.hakmilik.luas}&nbsp;--%>
            <s:format formatPattern="#,##0.0000" value="${actionBean.hakmilik.luas}" />&nbsp;${actionBean.hakmilik.kodUnitLuas.nama}
        </p>
        <p>
            <label>Cukai Tanah (RM) :</label>
            <%--<s:text formatType="number" name="cukai" value="${actionBean.hakmilikPermohonan.hakmilik.kadarCukai}" />--%>
            <%--<s:format formatPattern="RM{0,number,#,##0.00}" value="${actionBean.hakmilikPermohonan.hakmilik.kadarCukai}" />--%>
            <s:format formatPattern="#,##0.00" value="${actionBean.hakmilik.cukai}" />&nbsp;
        </p>
        <p>
            <label>Kategori Tanah :</label>
          ${actionBean.hakmilik.kategoriTanah.nama}&nbsp;
        </p>
        <p>
            <label>Syarat Nyata :</label>
            <s:textarea name="hakmilik.syaratNyata.syarat" style="overflow-x: hidden;" cols="60" rows="4" readonly="true">${actionBean.hakmilik.syaratNyata.syarat}</s:textarea>&nbsp;
        </p>
    </fieldset>
        <br>
        <table width="100%">
            <tr>
                <%--<s:hidden name="permohonanUlasan.tarikhUlasan" value="<%=new java.util.Date()%>" />--%>
                <td align="right"><s:button name="" id="close" value="Tutup" class="btn"/></td>
            </tr>
        </table>
    </s:form>
</div>