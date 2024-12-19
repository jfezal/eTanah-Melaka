<%-- 
    Document   : laporan_tanahV2TambahKawasan.jsp
    Created on : 07 March, 2012, 3:08:57 PM
    Author     : Rohan
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<title>Ulasan </title>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker-ms.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ageCalculator.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>
<script type="text/javascript">

    function validate()
    {
        a = document.test.id;

        if (document.test.id.value == null || document.test.id.value == "")
        {

            alert("Sila masukkan id permohonan");
            a.focus()
            return false;
        }
        else
        {

            return true;
        }

    }

    function test1() {

        self.close();







    }
    function openLain_lain(val) {
        if (val == '99') {
            $('#catatanKwsn').show();
            $('#catatanKwsnPBT').hide();
        } else if (val == '1') {
            $('#catatanKwsn').hide();
            $('#catatanKwsnPBT').show();
        } else {
            $('#catatanKwsn').hide();
            $('#catatanKwsnPBT').hide();
        }
    }
    function save1(event, f) {

        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url, q,
                function(data) {
                    $('#page_div', opener.document).html(data);
                    //self.opener.refreshRizab();
                    opener.refreshlptn();
                    self.close();
                }, 'html');

    }
    $(document).ready(function() {
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'})
    <c:if test="${actionBean.disLaporTanahKawasan.stringRizab eq null}">
        $('#catatanKwsn').hide();
        $('#catatanKwsnPBT').hide();

    </c:if>
    <c:if test="${actionBean.disLaporTanahKawasan.stringRizab ne null and actionBean.disLaporTanahKawasan.stringRizab eq '99'}">
            $('#catatanKwsn').show();
            $('#catatanKwsnPBT').hide();
    </c:if>
    <c:if test="${actionBean.disLaporTanahKawasan.stringRizab ne null and actionBean.disLaporTanahKawasan.stringRizab eq '1'}">
            $('#catatanKwsnPBT').show();
            $('#catatanKwsn').hide();
    </c:if>    
        });

        function openFrame(type) {
            NoPrompt();
            var idHakmilik = $('#idHakmilik').val();
            //    alert(idHakmilik);
            window.open("${pageContext.request.contextPath}/pelupusan/laporan_tanahV2?openFrame&idHakmilik="
                    + idHakmilik + "&type=" + type, "eTanah",
                    "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
            //        "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
        }
        function refreshpage() {
            NoPrompt();
    <c:choose>
        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PTGSA'}">
            var idHakmilik = $('#idHakmilik').val();
            opener.refreshV2ManyHakmilik('main', idHakmilik);
        </c:when>
        <c:otherwise>
                opener.refreshV2('main');
        </c:otherwise>
    </c:choose>
            self.close();
        }
</script>
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
        function NoPrompt()
        {
            allowPrompt = false;
        }

    </script>
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <s:form beanclass="etanah.view.stripes.common.laporan.tanah.laporantanahNewActionBean" name="form">
        <s:errors/>
        <s:messages/>
        <div class="subtitle">
            <fieldset class="aras1">
                <div id="perihaltanah">
                    <legend>Ulasan :</legend>
                    <s:hidden name="id2" value="id"/>

                    <table class="tablecloth" border="0" align="center">
                        <tr id="sebab">
                            <td align="left">Ulasan :</td>
                            <td align="left" style="color:black">
                                <s:textarea name="ulasanSebab" rows="5" cols="100" />
                            </td>
                        </tr>
                    </table>
                    <tr>
                        <td align="center" colspan="3">
                            <s:hidden name="idLaporTanah" id="idLaporTanah" value="${actionBean.laporanTanah.idLaporan}"/>
                            <s:hidden name="idMohonHakmilik" id="idMohonHakmilik" value="${actionBean.hakmilikPermohonan.id}"/>
                            <c:if test="${actionBean.permohonanLaporanUlasan ne null}">
                                <s:hidden name="idLaporUlasan" id="idLaporUlasan" value="${actionBean.permohonanLaporanUlasan.idLaporUlas}"/>
                                <s:submit name="simpanUlasanKemaskini" value="Simpan" class="btn" onclick="NoPrompt();"/>    
                                <s:button name="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                            </c:if>
                            <c:if test="${actionBean.permohonanLaporanUlasan eq null}">
                                <s:submit name="simpanUlasanBaru" value="Simpan" class="btn" onclick="NoPrompt();"/>    
                                  <s:button name="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                            </c:if>

                        </td>
                    </tr>

                </div>

            </fieldset>
        </div>
    </s:form>
</body>

