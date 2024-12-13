<%-- 
    Document   : syaratPPTR
    Created on : Feb 12, 2014, 12:20:22 AM
    Author     : Afham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SYARAT-SYARAT</title>
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
    function save(event, f) {
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url, q,
                function(data) {
                    $('#page_div', opener.document).html(data);
                    self.close();
                }, 'html');
    }
    $(document).ready(function() {
        //            maximizeWindow();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});

    }); //END OF READY FUNCTION



    function CurrencyFormatted(amount)
    {
        var i = parseFloat(amount);
        if (isNaN(i)) {
            i = 0.00;
        }
        var minus = '';
        if (i < 0) {
            minus = '-';
        }
        i = Math.abs(i);
        i = parseInt((i + .005) * 100);
        i = i / 100;
        s = new String(i);
        if (s.indexOf('.') < 0) {
            s += '.00';
        }
        if (s.indexOf('.') == (s.length - 2)) {
            s += '0';
        }
        s = minus + s;
        return s;
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
    <s:form beanclass="etanah.view.stripes.pelupusan.RekodKeputusanMMKV2ActionBean" name="form">
        <s:hidden name="idHakmilik" id="idHakmilik" value="${actionBean.hakmilikPermohonan.id}"/>
        <s:hidden name="kodU" id="kodU" value="${actionBean.hakmilikPermohonan.kodUnitLuas.kod}"/>
        <c:choose>
            <c:when test="${actionBean.kodNegeri eq '04'}">
                <table class="tablecloth" border="0">
                    <tr>
                        <td>
                            Isipadu Disyorkan :
                        </td>
                        <td>
                            <s:format value="${actionBean.hakmilikPermohonan.luasTerlibat}" formatPattern="#,###,##0.0000"/> ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Isipadu Diluluskan :
                        </td>
                        <td>
                            <s:text name="hakmilikPermohonan.luasDiluluskan" id="luasLulus" formatPattern="#,###,##0.0000"/> 
                            ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}
                            <s:hidden name="kodU" id="kodU" value="${actionBean.hakmilikPermohonan.kodUnitLuas.kod}"/>

                        </td>
                    </tr>
                    <tr>
                        <td>Tempoh Pajakan:</td>
                        <td>
                            <s:text name="hakmilikPermohonan.tempohPajakan" id="tempohPajakan" size="10"/> Tahun
                        </td>
                    </tr>                              
                    <tr>
                        <td>Bayaran : </td>
                        <td>
                            RM <s:text name="amnt" size="10" onchange="CurrencyFormatted(this.value)"/>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align:center;" colspan="2">      
                            <s:submit name="simpanSyarat" value="Simpan" class="btn" onclick="NoPrompt();"/>
                            <s:button name="tutup" value="Tutup" class="btn" onclick="refreshpage()"/>
                        </td>
                    </tr>
                </table>
            </c:when>
            <c:when test="${actionBean.kodNegeri eq '05'}">

            </c:when>
            <c:otherwise></c:otherwise>
        </c:choose>


    </s:form>
