<%--
    Document   : maklumat_hakmilik_pengambilan
    Created on : 12-Jan-2010, 18:31:55
    Author     : nordiyana
--%>

<%@page import="java.text.DecimalFormat"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<%
    DecimalFormat dcf = new DecimalFormat("#0.0000");
%>
<script type="text/javascript">
    $(document).ready(function() {


        var len = $(".daerah").length;

        for (var i = 0; i <= len; i++) {
            $('.hakmilik' + i).click(function() {
                window.open("${pageContext.request.contextPath}/pengambilan/maklumat_hakmilikpengambilan?popup&idHakmilik=" + $(this).text(), "eTanah",
                        "status=0,toolbar=0,location=0,menubar=0,width=890,height=600").focus();
            });
        }
    });

    function validateLuas(idVar, rowNo) {

        var str = idVar.value;
        var luasTerlibat = parseFloat(idVar.value)
        //alert('LuasTerlibat Id: '+ luasTerlibat);

        var luas = parseFloat($('#luas' + rowNo).val());
        //var luas = parseFloat(document.getElementById("luas"+rowNo).value);
        //alert('Luas Id: '+ luas);
        //alert(luas);
        // var luas = parseInt(document.getElementById("luas"+rowNo).value);
        //  var luas = parseInt(document.getElementById("luas"+rowNo).value);
        //  alert('Luas'+luas);

        if (luasTerlibat > luas) {
            alert("Pastikan Luas Diambil tidak melebihi Luas");
            idVar.value = str.substring(0, str.length - 1);
            idVar.focus();
            return true;
        }
    }

    function validateKodUnitLuas(idVar, rowNo) {
    <%--alert(idVar.value);--%>
            if (idVar.value == 'M') {
                var unitLuasDiambil = "mp";
                alert(unitLuasDiambil);
            }
            if (idVar.value == 'H') {
                var unitLuasDiambil = "Ha";
                alert(unitLuasDiambil);
            }

            var unitLuas = document.getElementById("unitLuas" + rowNo).value;
            alert(unitLuas);

        }

        function save(event, f) {
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url, q,
                    function(data) {
                        $('#page_div', opener.document).html(data);

                    }, 'html');

        }

//        function select(id) {
//            doBlockUI();
//            frm = document.form1;
//            var url = '${pageContext.request.contextPath}/pengambilan/common/borangA?kemaskiniBorangA&idPihak=' + id;
//            frm.action = url;
//            frm.submit();
//        }

        function select(idMH) {
//            alert(idMH);

            window.open("${pageContext.request.contextPath}/pengambilan/common/borangA?kemaskiniBorangA&idMH=" + idMH, "eTanah",
                    "status=0,toolbar=0,location=0,menubar=0,width=1000,height=700,scrollbars=yes");
        }



</script>

<s:form beanclass="etanah.view.stripes.pengambilan.share.common.NotisBorangAActionBean">

    <s:messages/>
    <div class="subtitle displaytag">

        <fieldset class="aras1" id="locate">
            <legend>
                Perincian Borang A

            </legend>

            <p align="center">

                <display:table class="tablecloth" name="${actionBean.listHakmilikPermohonanForm}" pagesize="30" cellpadding="0" cellspacing="0"
                               requestURI="/pengambilan/maklumat_hakmilik" id="line">
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <c:if test="${line.mohonHakmilik.hakmilik.idHakmilik eq null}">
                        <display:column title="ID Hakmilik" class="popup hakmilik">  Hakmilik Tidak Dikenalpasti</display:column> 
                    </c:if>
                    <c:if test="${line.mohonHakmilik.hakmilik.idHakmilik ne null}">
                        <display:column title="ID Hakmilik" class="popup hakmilik${line_rowNum}">${line.mohonHakmilik.hakmilik.idHakmilik}</display:column> 
                    </c:if>

                    <display:column title="No Lot/No PT" >${line.mohonHakmilik.hakmilik.noLot}</display:column>
                    <display:column title="Luas Diambil">
                        <fmt:formatNumber pattern="#,##0.0000" value="${line.mohonHakmilik.hakmilik.luas}"/>&nbsp;
                        <c:if test="${line.mohonHakmilik.hakmilik.luas eq 'Meter Persegi'}">mp</c:if>
                        <c:if test="${line.mohonHakmilik.hakmilik.luas eq 'Hektar'}">Ha</c:if>
                    </display:column>


                    <display:column title="BilTempat Tampal" class="daerah"><a href="#" onclick="select('${line.mohonHakmilik.id}', 'tuanTanah');
            return false;">${line.total}</a></display:column>

                </display:table>

                <br>                

        </fieldset>

    </div>

</s:form>
