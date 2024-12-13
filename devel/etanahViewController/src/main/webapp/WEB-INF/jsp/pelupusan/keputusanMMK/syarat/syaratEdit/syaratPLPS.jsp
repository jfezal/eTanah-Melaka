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
                <c:choose>
        <c:when test="${actionBean.keg eq 'LL'}">
                $('#catatanKegunaan').show();
        </c:when>
        <c:when test="${actionBean.keg eq 'LN'}">
                $('#catatanKegunaan').show();
        </c:when>
        <c:otherwise>
                $('#catatanKegunaan').hide();
        </c:otherwise>
    </c:choose>    

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
    
        function openLain_lain(val){
            if(val == 'LL'||val == 'LN'){
                $('#catatanKegunaan').show();
            }else{
                $('#catatanKegunaan').hide();
            }
            $('#kegunaan').val(val);
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
        <c:choose>
            <c:when test="${actionBean.kodNegeri eq '04'}">
                <table class="tablecloth" border="0">
                    <tr>
                        <td>
                            Luas Disyorkan :
                        </td>
                        <td>
                            <s:format value="${actionBean.hakmilikPermohonan.luasTerlibat}" formatPattern="#,###,##0.0000"/> ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}
                        </td>
                    </tr>
                    <tr>
                        <td><font color="red" size="4">*</font>Kegunaan :</td>
                        <td>
                            <s:select name="keg" style="width:250px" id="kegunaan" onchange="openLain_lain(this.value)">
                                <s:option value="0">Sila Pilih</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodItemLPS}" label="nama" value="kod"/>
                            </s:select>
                        </td>
                    </tr>
                    <tr id="catatanKegunaan">
                        <td>Catatan :</td>
                        <td>
                            <s:textarea name="catatan" rows="5" cols="80"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Status Keluasan :
                        </td>
                        <td>
                            <s:radio name="hakmilikPermohonan.statusLuasDiluluskan" value="P" />&nbsp;Keluasan Penuh
                            <s:radio name="hakmilikPermohonan.statusLuasDiluluskan" value="S" />&nbsp;Keluasan Sebahagian
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Luas Diluluskan :
                        </td>
                        <td>
                            <s:text name="hakmilikPermohonan.luasDiluluskan" id="luasLulus" formatPattern="#,###,##0.0000"/> 
                            ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}
                            <s:hidden name="kodU" id="kodU" value="${actionBean.hakmilikPermohonan.kodUnitLuas.kod}"/>

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
