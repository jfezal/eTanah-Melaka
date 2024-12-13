<%-- 
    Author     : wazer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tambah Permohonan Terdahulu</title>
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
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script src="<%= request.getContextPath()%>/pub/js/ui.datepicker-ms.js" type="text/javascript"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>
<script type="text/javascript">
    var size = 0;
    function save(event, f) {
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url, q,
                function(data) {
                    $('#page_div', opener.document).html(data);
                    self.close();
                }, 'html');
    }
    function save3(event, f) {
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url, q,
                function(data) {
                    $.get("${pageContext.request.contextPath}/common/laporan/pelan/java/LaporanPelukisPelanNewActionBean?simpanFail=",
                            function(data) {
                            });
                    self.opener.refreshPageTanahRizab()
                    self.close();
                }, 'html');
    }

    function save2() {
        self.opener.refreshPageTanahRizab();
        self.close();
    }





</script>
<body>

    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <s:form beanclass="etanah.view.stripes.common.laporan.pelan.java.LaporanPelukisPelanNewActionBean">
        <s:errors/>
        <s:messages/>

        <s:hidden name="idPermohonan" id="idPermohonan" value="${actionBean.idPermohonan}"/>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Permohonan Terdahulu</legend>
                <div class="content" align="center">
                    <table class="tablecloth" border="0">
                        <tr>
                            <td>No. Fail :</td>
                            <td><s:text name="noFailBaru" id="noFail"/></td>
                        </tr>
                        <tr>
                            <td align="right" colspan="2">

                                <s:button name="simpanFail" value="simpan" class="btn" onclick="save3(this.name, this.form)"/>
                                <s:reset name="IsiSemula" value="Isi Semula" class="btn" onclick=""/>
                                <s:button name="tutup" value="Tutup" class="btn" onclick="save2()"/>

                            </td>
                        </tr>
                    </table>
                </div>
                <br/>

            </fieldset>
        </div>
    </s:form>
</body>

