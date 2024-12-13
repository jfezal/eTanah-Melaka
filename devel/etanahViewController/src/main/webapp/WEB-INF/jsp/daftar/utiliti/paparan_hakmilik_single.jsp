<%-- 
    Document   : paparan_hakmilik_single
    Created on : Nov 1, 2009, 6:15:42 PM
    Author     : wan.fairul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
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
<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<s:form action="/daftar/kesinambungan" >
    <div class="subtitle">
       
            <fieldset class="aras1">
            <legend>
                Maklumat Hakmilik
            </legend>
            <table border="0">
                <tr> <td>&nbsp;</td></tr>
                <tr><td id="tdLabel"> <label>ID Hakmilik :</label></td>
                    <td id="tdDisplay">&nbsp;${actionBean.hakmilik.idHakmilik}&nbsp;
                    </td>
                </tr>
                
                <tr><td id="tdLabel"> <label>Daerah :</label></td>
                    <td id="tdDisplay">&nbsp;${actionBean.hakmilik.daerah.nama}&nbsp;
                    </td>
                </tr>

                <tr><td id="tdLabel"><label>Seksyen :</label></td>
                    <td id="tdDisplay">&nbsp;${actionBean.hakmilik.seksyen.nama}&nbsp;
                    </td>
                </tr>

                <tr><td id="tdLabel"><label>Bandar/Pekan/Mukim :</label></td>
                    <td id="tdDisplay">&nbsp;${actionBean.hakmilik.bandarPekanMukim.nama}&nbsp;
                    </td>
                </tr>
                <tr><td id="tdLabel"><label>Jenis Hakmilik :</label></td>
                    <td id="tdDisplay">&nbsp;${actionBean.hakmilik.kodHakmilik.nama}&nbsp;
                    </td>
                </tr>
                <tr><td id="tdLabel"><label>Kategori Tanah :</label></td>
                    <td id="tdDisplay">&nbsp;${actionBean.hakmilik.kategoriTanah.nama}&nbsp;
                    </td>
                </tr>
                <tr><td id="tdLabel"><label>Kod Lot/PT :</label></td>
                    <td id="tdDisplay">&nbsp;${actionBean.hakmilik.lot.nama}&nbsp;
                    </td>
                </tr>
                <tr><td id="tdLabel"><label>Nombor Lot/PT :</label></td>
                    <td id="tdDisplay">&nbsp;${actionBean.hakmilik.noLot}&nbsp;

                    </td>
                </tr>
                <tr><td id="tdLabel"><label>Keluasan :</label></td>
                    <td id="tdDisplay">&nbsp;<s:format formatPattern="#,##0.0000" value="${actionBean.hakmilik.luas}"/>&nbsp; ${actionBean.hakmilik.kodUnitLuas.nama}&nbsp;
                    </td>
                </tr>
                <tr><td id="tdLabel"><label>Syarat Nyata :</label></td>
                    <td id="tdDisplay">${actionBean.hakmilik.syaratNyata.syarat}&nbsp;
                    </td>
                </tr>
                <tr><td id="tdLabel"><label>Sekatan Kepentingan :</label></td>
                    <td id="tdDisplay">${actionBean.hakmilik.sekatanKepentingan.sekatan}&nbsp;
                    </td>
                </tr>
                <tr><td id="tdLabel"><label>Nombor Pelan Piawai :</label></td>
                    <td id="tdDisplay">&nbsp;${actionBean.hakmilik.noLitho}&nbsp;
                    </td>
                </tr>
                <tr><td id="tdLabel"><label>Nombor Pelan :</label></td>
                    <td id="tdDisplay">&nbsp;${actionBean.hakmilik.noPelan}&nbsp;
                    </td>
                </tr>
                <tr><td id="tdLabel"><label>Jenis Rizab :</label></td>
                    <td id="tdDisplay">&nbsp;${actionBean.hakmilik.rizab.nama}&nbsp;
                    </td>
                </tr>
                <tr><td id="tdLabel"><label>Cukai Tahunan (RM) :</label></td>
                    <td id="tdDisplay">&nbsp; <s:format formatPattern="##,##0.00" value="${actionBean.hakmilik.cukai}"/>
                    </td>
                </tr>
               
            </table>
            <p align="center">
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>
        </fieldset>
        <br>
    </div>
</s:form>
