<%--<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>--%>
<%--<%@page contentType="text/html" pageEncoding="windows-1252"%>--%>
<!--<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"-->
<!--"http://www.w3.org/TR/html4/loose.dtd">-->

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<!--<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>-->
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript">
    function muatNaikPelan() {

        var left = (screen.width / 2) - (1000 / 2);
        var top = (screen.height / 2) - (150 / 2);
        var url = '${pageContext.request.contextPath}/strata/pelan?uploadPelan';
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=300, left=" + left + ",top=" + top);
    }

    function popupParam(nama, noPelan) {
        window.open("${pageContext.request.contextPath}/reportGeneratorServlet?" + "&reportName=" + nama + "&report_p_no_pelan=" + noPelan, "eTanah",
                "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
    }

    function refreshpage() {
        var url = '${pageContext.request.contextPath}/strata/pelan?refresh';
        $.get(url,
                function (data) {
                    $('#page_div').html(data);
                }, 'html');
    }
</script>

<s:form beanclass="etanah.view.strata.PelanStrata" id="form1">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <c:if test="${jupem}">
                <c:if test = "${actionBean.permohonan.kodUrusan.kod eq 'SUBMC'}">
                    <font color="red">Sila Jana Surat Sah kepada JUPEM</font>
                </c:if>
                <c:if test = "${actionBean.permohonan.kodUrusan.kod ne 'SUBMC'}">
                    <font color="red">Sila Jana Surat Permohonan kepada JUPEM</font>
                </c:if>
            </c:if>
            <c:if test="${pelan}">
                Sila Muat Naik Pelan PA(B)${actionBean.senaraiPelanString}
                <em>Pastikan nama fail adalah nama pelan cth:1234-01</em>
                <br><br>
                <p><label><font color="red">*</font>Pelan :</label>
                        <s:file name="file"/>
                    <br />
                <center>
                    <s:button name="muatNaik" value="Muat Naik Pelan" class="longbtn"  onclick="muatNaikPelan();" />
                    <%--<s:submit name="simpanPelanStr" value="Simpan" class="btn"/>--%>
                    <s:button name="refresh" value="Refresh" class="longbtn" onclick="refreshpage();"/>
                </center>
                <br />


                <br>
                <fieldset class="aras1">
                    <p><label><em>*</em>Senarai Pelan Belum Dimuat Naik : </label>
                        <display:table class="tablecloth" name="${actionBean.senaraiPelanUpload}" cellpadding="0" cellspacing="0"
                                       requestURI="/strata/pelan" id="line">
                            <display:column title="Bil" sortable="true" style="vertical-align:baseline">${line_rowNum}</display:column>
                            <display:column title="Nombor Lot/PT" style="vertical-align:baseline">
                                ${(actionBean.senaraiPelanUpload[line_rowNum-1])} 
                            </display:column>
                        </display:table>
                    </p>

                </fieldset>

                <br />

                <p>Klik ikon papar untuk menyemak pelan yang sudah dimuatnaik. Jika pelan tidak boleh dipaparkan, 
                    mohon kecilkan resolution pelan.
                    <br />
                <center>
                    <display:table class="tablecloth" name="${actionBean.senaraiPelanString}" cellpadding="0" cellspacing="0"
                                   requestURI="/strata/pelan" id="line">
                        <display:column title="Bil" sortable="true" style="vertical-align:baseline">${line_rowNum}</display:column>
                        <display:column title="Nama Pelan" style="vertical-align:baseline">
                            ${(actionBean.senaraiPelanString[line_rowNum-1])} 
                        </display:column>
                        <display:column title="Tindakan" >
                            <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                 onclick="popupParam('STRPELAN_MLK', '${(actionBean.senaraiPelanString[line_rowNum-1])}');" onmouseover="this.style.cursor = 'pointer';">
                        </display:column>
                    </display:table>
                    <br>
                    </p> 
                </center>
            </c:if>
        </fieldset>
    </div>
</s:form>

