<%-- 
    Document   : maklumat_tanah_dipohonV2EditMain2
    Created on : Sep 19, 2013, 3:49:44 PM
    Author     : afham
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>KEMASUKAN MAKLUMAT TANAH</title>
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

    $(document).ready(function() {
        //maximizeWindow();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    <c:if test="${save}">
        $('#simpan').attr("disabled", "true");
    </c:if>
    }); //END OF READY FUNCTION

    function refreshpage2() {
        //        alert('aa');
        NoPrompt();
        opener.refreshV2MaklumatTanah();
        self.close();
    }

    function tutupPapar() {
        //        alert('aa');
        NoPrompt();
        opener.tutupPapar();
        self.close();
    }

    function tutup() {
        NoPrompt();
        opener.refreshV2MaklumatTanah2();
        self.close();
    }

    function refreshpage1() {
        NoPrompt();
        opener.refreshV2MaklumatTanah2();
        self.close();
    }

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
    <s:form beanclass="etanah.view.stripes.pelupusan.maklumat_tanah.MaklumatTanahV2ActionBean" name="form">
        <s:errors/>
        <s:messages/>
        <s:hidden name="idMH" id="idMH"/>
        <s:hidden name="type" id="type"/>
        <s:hidden name="idHakmilik" id="idHakmilik"/>
        <s:hidden name="idHakmilik" value="${actionBean.idHakmilik}"/>
        <div class="subtitle">
            <c:choose>
                <c:when test="${edit}">
                    <fieldset class="aras1">
                        <div id="perihaltanah">
                            <legend>
                                KEMASUKAN MAKLUMAT TANAH                
                            </legend>
                        </div>
                        <div class="subtitle">
                            <table class="tablecloth" align="center">
                                <tr>
                                    <td>Tempat/Lokasi Tanah Dipohon :</td>
                                    <td>
                                        <s:textarea name="hakmilikPermohonan.lokasi" value="${actionBean.disHakmilikPermohonan.hakmilikPermohonan.lokasi}" rows="5" cols="70" id="tempat" class="normal_text"/>
                                    </td>
                                </tr>
                                <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PPRU'}">
                                    <tr>
                                        <td>Luas Tanah Dipohon :</td>
                                        <td>
                                            <s:text name="hakmilikPermohonan.luasTerlibat" value="${actionBean.disHakmilikPermohonan.hakmilikPermohonan.luasTerlibat}" formatPattern="#,###,##0.0000" maxlength="15" onkeyup="validateNumber(this,this.value);" id="Luas"/>
                                            <s:select name="disHakmilikPermohonan.keluasanUOM" id="kULuas">
                                                <s:option value="">Sila Pilih</s:option>
                                                <s:option value="H">Hektar</s:option>
                                                <s:option value="M">Meter Persegi</s:option>
                                            </s:select>
                                        </td>
                                    </tr>
                                </c:if>
                                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPRU'}">
                                    <tr>
                                        <td>Isipadu Terlibat :</td>
                                        <td>
                                            <s:text name="hakmilikPermohonan.luasTerlibat" value="${actionBean.disHakmilikPermohonan.hakmilikPermohonan.luasTerlibat}" formatPattern="#,###,##0.0000" maxlength="15" onkeyup="validateNumber(this,this.value);" id="Luas"/>
                                            <s:select name="disHakmilikPermohonan.keluasanUOM" id="kULuas">
                                                <s:option value="">Sila Pilih</s:option>
                                                <s:option value="MP">Meter Padu</s:option>
                                            </s:select>
                                        </td>
                                    </tr>
                                </c:if>
                                <c:choose>
                                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PRMP'}">
                                        <tr>
                                            <td>Kegunaan Tanah Dipohon :</td>
                                            <td>
                                                <s:select name="kodPermit.kod"  value="${actionBean.disPermohonanPermitItem.kodGunaTanah}"style="width:300px;" id="kodGunaTanah">
                                                    <s:option value="">-- Sila Pilih --</s:option>
                                                    <s:options-collection collection="${listUtil.senaraiKodItemPRMP}" value="kod" label="nama" />
                                                </s:select>
                                            </td>
                                        </tr>
                                    </c:when>
                                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PLPS' or actionBean.permohonan.kodUrusan.kod eq 'LPSP'}">
                                        <tr>
                                            <td>Tujuan LPS :</td>
                                            <td>
                                                <s:select name="kodPermit.kod"  value="${actionBean.disPermohonanPermitItem.kodGunaTanah}"style="width:300px;" id="kodGunaTanah">
                                                    <s:option value="">-- Sila Pilih --</s:option>
                                                    <s:options-collection collection="${listUtil.senaraiKodItemLPS}" value="kod" label="nama" />
                                                </s:select>
                                            </td>
                                        </tr>
                                    </c:when>
                                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PTPBP' }">
                                        <tr>
                                            <td>Tujuan :</td>
                                            <td>
                                                <s:select name="kodPermit.kod"  value="${actionBean.disPermohonanPermitItem.kodGunaTanah}"style="width:300px;" id="kodGunaTanah">
                                                    <s:option value="">-- Sila Pilih --</s:option>
                                                    <s:options-collection collection="${listUtil.senaraiKodItemPRMP}" value="kod" label="nama" />
                                                </s:select>
                                            </td>
                                        </tr>
                                    </c:when>
                                </c:choose>

                                <tr>
                                    <td style="text-align:center;" colspan="3">      
                                        <s:submit name="simpanUntukHakmilik" value="Simpan" id="simpan" class="btn" />
                                        <s:hidden name="idHakmilik" id="idHakmilik"/>
                                        <s:button name="tutup" value="Tutup" class="btn" onclick="refreshpage2()"/>
                                    </td>
                                </tr> 
                            </table>
                        </div>
                    </fieldset>
                </c:when>
                <c:when test="${!edit}">
                    <fieldset class="aras1">
                        <div id="perihaltanah">
                            <legend>
                                PAPARAN MAKLUMAT TANAH                
                            </legend>
                        </div>
                        <div class="subtitle">
                            <table class="tablecloth" align="center">
                                <tr>
                                    <td>Tempat/Lokasi Tanah Dipohon :</td>
                                    <td>
                                        ${actionBean.disHakmilikPermohonan.hakmilikPermohonan.lokasi}&nbsp;
                                    </td>
                                </tr>
                                <tr>
                                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PPRU'}">
                                        <td>Luas Tanah Dipohon :</td>
                                    </c:if>
                                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPRU'}">
                                        <td>Isipadu Dipohon :</td>
                                    </c:if>
                                    <td>
                                        <s:format value="${actionBean.disHakmilikPermohonan.hakmilikPermohonan.luasTerlibat}" formatPattern="#,###,##0.0000"/> &nbsp;
                                        ${actionBean.disHakmilikPermohonan.hakmilikPermohonan.kodUnitLuas.nama}
                                    </td>
                                </tr>
                                <c:choose>
                                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PRMP'}">
                                        <tr>
                                            <td>Kegunaan Tanah Dipohon :</td>
                                            <td>
                                                ${actionBean.disPermohonanPermitItem.mohonPermitItem.kodItemPermit.nama}
                                            </td>
                                        </tr>
                                    </c:when>
                                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PLPS' or actionBean.permohonan.kodUrusan.kod eq 'LPSP'}">
                                        <tr>
                                            <td>Tujuan LPS :</td>
                                            <td>
                                                ${actionBean.disPermohonanPermitItem.mohonPermitItem.kodItemPermit.nama}
                                            </td>
                                        </tr>
                                    </c:when>
                                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PTPBP' }">
                                        <tr>
                                            <td>Tujuan :</td>
                                            <td>
                                                ${actionBean.disPermohonanPermitItem.mohonPermitItem.kodItemPermit.nama}
                                            </td>
                                        </tr>
                                    </c:when>
                                </c:choose>

                                <tr>
                                    <td style="text-align:center;" colspan="3">
                                        <s:button name="tutup" value="Tutup" class="btn" onclick="refreshpage1()"/>
                                    </td>
                                </tr> 
                            </table>

                        </div>
                    </fieldset>
                </c:when>
            </c:choose>
        </div>
    </s:form>
</body>



