<%--
    Author     : wazer
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MUATNAIK GAMBAR</title>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script src="<%= request.getContextPath()%>/pub/js/ui.datepicker-ms.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<s:errors/>
<s:messages/>
<body>
    <script>
        // Allow the user to be warned by default.
        var allowPrompt = true;
        window.onbeforeunload = WarnUser;
        function WarnUser()
        {
            if (allowPrompt)
                refreshpage();
            if (allowPrompt)
            {
                event.returnValue = "Segala Maklumat tidak akan disimpan, anda pasti?";
            }
            else
            {
                // Reset the flag to its default value.
                allowPrompt = true;
            }
        }
        function NoPrompt() {
            doBlockUI();
            allowPrompt = false;
            doUnBlockUI();
//            self.close();
        }

        function refreshPage() {
            var url = '${pageContext.request.contextPath}/laporanTanah?lotSempadan';
            $.get(url,
                    function(data) {
                        $('#page_div').html(data);
                    }, 'html');
        }
    </script>
    <s:form beanclass="etanah.view.laporanTanah.laporanTanahV3" name="form">
        <s:errors/>
        <s:messages/>

        <s:hidden name="idPermohonan" id="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
        <s:hidden name="idMohonHakmilik" id="idMohonHakmilik" value="${actionBean.hakmilikPermohonan.id}"/>

        <div class="subtitle">
            <fieldset class="aras1">
                <div id="perihaltanah">
                    <legend>Ulasan :</legend>
                    <s:hidden name="id2" value="id"/>

                    <table class="tablecloth" border="0" align="center">
                        <tr id="sebab">
                            <td align="left">Kemasukan ${actionBean.syorLaporanTanah.item} :</td>
                            <td align="left" style="color:black">
                                <s:textarea name="ulasanPerihal" value="${actionBean.ulasanPerihal}" id="ulasanPerihal" rows="5" cols="100" />
                            </td>
                        </tr>
                    </table>
                    <tr>
                        <td align="center" colspan="3">
                            <s:hidden name="idLaporTanah" id="idLaporTanah" value="${actionBean.syorLaporanTanah.id}"/>
                            <s:hidden name="idMohonHakmilik" id="idMohonHakmilik" value="${actionBean.hakmilikPermohonan.id}"/>
                            <s:submit name="simpanKemaskini" value="Simpan" class="btn" onclick="NoPrompt();"/>    
                            <s:button name="tutup" value="Tutup" class="btn" onclick="self.close()"/>

                        </td>
                    </tr>
                </div>
            </fieldset>
        </div>
    </s:form>
</body>

