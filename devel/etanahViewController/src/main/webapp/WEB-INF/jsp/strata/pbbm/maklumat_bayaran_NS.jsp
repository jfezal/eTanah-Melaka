<%--
    Document   : maklumat_bayaran_NS
    Created on : July 20, 2012, 10:42:01 AM
    Author     : Murali
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript">

    function popup(b, p, idK) {

        window.open("${pageContext.request.contextPath}/strata/jenis_bayaran?showForm2&item=" + b + "&amaunTuntutan=" + p + "&idKos=" + idK, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=800,height=150");
    }
    function removeKos(val) {

        if (confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/strata/jenis_bayaran?deleteBayaran&idKos=' + val;
            $.get(url,
                    function (data) {
                        $('#page_div').html(data);
                    }, 'html');
        }
    }

    function save(event, f) {

        $.blockUI({
            message: $('#displayBox'),
            css: {
                top: ($(window).height() - 50) / 2 + 'px',
                left: ($(window).width() - 50) / 2 + 'px',
                width: '50px'
            }
        });

        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url, q,
                function (data) {
                    $('#page_div').html(data);
                    $.unblockUI();
                }, 'html');
    }

    function removeCommas(val) {
        return val.replace(/\,/g, '');
    }

    function test() {
        var s = $("#hidden2").val();
        var e = $("#hidden3").val();
        var total = 0;
        for (var a = 0; a < s; a++) {
            var cd = $("#amaun" + a).val();
            total = parseFloat(total) + parseFloat(removeCommas(cd));
        }
        total = total.toString().replace(/\$|\,/g, '');
        if (isNaN(total))
            total = "0";
        sign = (total == (total = Math.abs(total)));
        total = Math.floor(total * 100 + 0.50000000001);
        cents = total % 100;
        total = Math.floor(total / 100).toString();
        if (cents < 10)
            cents = "0" + cents;
        for (var i = 0; i < Math.floor((total.length - (1 + i)) / 3); i++)
            total = total.substring(0, total.length - (4 * i + 3)) + ',' +
                    total.substring(total.length - (4 * i + 3));
        $('#total').val((((sign) ? '' : '-') + total + '.' + cents));
    }
    function changeFormat2(row) {
        var num = document.getElementById('amaun' + row).value;
        num = num.toString().replace(/\$|\,/g, '');
        if (isNaN(num))
            num = "0";
        sign = (num == (num = Math.abs(num)));
        num = Math.floor(num * 100 + 0.50000000001);
        cents = num % 100;
        num = Math.floor(num / 100).toString();
        if (cents < 10)
            cents = "0" + cents;
        for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
            num = num.substring(0, num.length - (4 * i + 3)) + ',' +
                    num.substring(num.length - (4 * i + 3));
        $('#amaun' + row).val((((sign) ? '' : '-') + num + '.' + cents));
    }

    function changeFormat3(row) {
        var num = document.getElementById('kadarbayar' + row).value;
        var jpnum = document.getElementById('jumlahPetak' + row).value;
        num = num.toString().replace(/\$|\,/g, '');
        if (isNaN(num))
            num = "0";
        sign = (num == (num = Math.abs(num)));
        num = Math.floor(num * 100 + 0.50000000001);
        cents = num % 100;
        num = Math.floor(num / 100).toString();
        if (cents < 10)
            cents = "0" + cents;
        var totalamn = num * jpnum;
        totalamn = totalamn.toString().replace(/\$|\,/g, '');
        for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
            num = num.substring(0, num.length - (4 * i + 3)) + ',' +
                    num.substring(num.length - (4 * i + 3));
        for (var j = 0; j < Math.floor((totalamn.length - (1 + j)) / 3); j++)
            totalamn = totalamn.substring(0, totalamn.length - (4 * j + 3)) + ',' +
                    totalamn.substring(totalamn.length - (4 * j + 3));
        $('#amaun' + row).val((((sign) ? '' : '-') + totalamn + '.' + cents))
        $('#kadarbayar' + row).val((((sign) ? '' : '-') + num + '.' + cents));
        test();
    }


    function validateNumber(elmnt, content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }
    function removeNonNumeric(strString)
    {
        var strValidCharacters = "1234567890";
        var strReturn = "";
        var strBuffer = "";
        var intIndex = 0;
        // Loop through the string
        for (intIndex = 0; intIndex < strString.length; intIndex++)
        {
            strBuffer = strString.substr(intIndex, 1);
            // Is this a number
            if (strValidCharacters.indexOf(strBuffer) > -1)
            {
                strReturn += strBuffer;
            }
        }
        return strReturn;
    }

    function isNumberKey(evt)
    {
        var charCode = (evt.which) ? evt.which : event.keyCode
        if (charCode > 31 && (charCode < 48 || charCode > 57))
            return false;

        return true;
    }
    function reset()
    {
        var url = '${pageContext.request.contextPath}/strata/jenis_bayaran?setSemula';
        $.get(url,
                function (data) {
                    $('#page_div').html(data);
                    $.unblockUI();
                }, 'html');
    }

</script>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
<s:form id="form1 "name="form1" beanclass="etanah.view.strata.BayaranUpahUkurActionBean">
    <s:messages/>
    <s:errors/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Kemasukan Maklumat Bayaran</legend>
            <p align="center">
                <c:set value="0" var="object"></c:set>

                <display:table   class="tablecloth" name="${actionBean.listTuntutanKos}" cellpadding="0" cellspacing="0" id="line" ><c:set  value="${object+1}" var="object" ></c:set>
                    <display:column title="Bil" sortable="true" style="vertical-align:baseline">${object}<s:hidden id="hidden3" name="hidden3" value="${line_rowNum-1}"/></display:column>
                    <display:column property="kodTuntut.nama" title="Perihal Bayaran" />

                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PHPP' || actionBean.permohonan.kodUrusan.kod eq 'PHPC'}">
                        <display:column  title="Jumlah Hakmilik">
                            <c:if test="${line.kodTuntut.kod ne 'STR03' && line.kodTuntut.kod ne 'STR02'}">
                            <p align="center"> -</p>
                        </c:if>
                        <c:if test="${line.kodTuntut.kod eq 'STR03' || line.kodTuntut.kod eq 'STR02'}">
                            <s:text readonly="true"  name="listTuntutanKos[${line_rowNum-1}].jumlahPetak" id="jumlahPetak${line_rowNum-1}" ></s:text>
                        </c:if>
                    </display:column>
                </c:if>

                <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PHPP' && actionBean.permohonan.kodUrusan.kod ne 'PHPC'}">
                    <display:column  title="Jumlah Petak">
                        <c:if test="${line.kodTuntut.kod ne 'STR03' && line.kodTuntut.kod ne 'STR02'}">
                            <p align="center"> -</p>
                        </c:if>
                        <c:if test="${line.kodTuntut.kod eq 'STR02'}">
                            <s:text name="listTuntutanKos[${line_rowNum-1}].jumlahPetak" id="jumlahPetak${line_rowNum-1}" ></s:text>
                        </c:if>
                        <c:if test="${line.kodTuntut.kod eq 'STR03'}">
                            <s:text name="listTuntutanKos[${line_rowNum-1}].jumlahPetak" id="jumlahPetak${line_rowNum-1}" ></s:text>
                        </c:if>
                    </display:column>
                </c:if>

                <display:column  title="Kadar Bayar">
                    <c:if test="${line.kodTuntut.kod ne 'STR03' && line.kodTuntut.kod ne 'STR02'}">
                        <p align="center"> -</p>
                    </c:if>
                    <c:if test="${line.kodTuntut.kod eq 'STR03' || line.kodTuntut.kod eq 'STR02'}">
                        <c:if test="${line.kodTuntut.kod eq 'STR01'}">
                            RM: <s:text name="listTuntutanKos[${line_rowNum-1}].mohonTuntutKos.amaunSeunit" id="kadarbayar${line_rowNum - 1}" onkeypress="return isNumberKey(event)" onblur="changeFormat3('${line_rowNum - 1}')" formatPattern="#,##0.00"></s:text>
                        </c:if>
                        <c:if test="${line.kodTuntut.kod ne 'STR01'}">

                            RM: <s:text name="listTuntutanKos[${line_rowNum-1}].mohonTuntutKos.amaunSeunit" id="kadarbayar${line_rowNum - 1}" onkeypress="return isNumberKey(event)" onblur="changeFormat3('${line_rowNum - 1}')" formatPattern="#,##0.00"></s:text>
                        </c:if>
                    </c:if>
                </display:column>

                <display:column   title="Amaun (RM)">

                    <c:if test="${line.kodTuntut.kod eq 'STR01'}">
                        <div align="right"> <s:text id="amaun${line_rowNum - 1}" onchange="test(${object})" onkeypress="return isNumberKey(event)" onblur="changeFormat2('${line_rowNum - 1}')"  name="listTuntutanKos[${line_rowNum-1}].mohonTuntutKos.amaunTuntutan" formatPattern="#,##0.00"></s:text></div>
                    </c:if>
                    <c:if test="${line.kodTuntut.kod ne 'STR01'}">
                        <c:if test="${line.kodTuntut.kod eq 'STR07'}">
                            <div align="right"> <s:text id="amaun${line_rowNum - 1}" onchange="test(${object})" onkeypress="return isNumberKey(event)" onblur="changeFormat2('${line_rowNum - 1}')"  name="listTuntutanKos[${line_rowNum-1}].mohonTuntutKos.amaunTuntutan" formatPattern="#,##0.00"></s:text></div>
                        </c:if>
                        <c:if test="${line.kodTuntut.kod ne 'STR07'}">
                            <div align="right"> <s:text id="amaun${line_rowNum - 1}" readonly="true" name="listTuntutanKos[${line_rowNum-1}].mohonTuntutKos.amaunTuntutan" formatPattern="#,##0.00"></s:text></div>
                        </c:if>
                    </c:if>
                </display:column>

                <display:footer> <tr>
                        <td colspan="3" align="left">Jumlah Perlu Dibayar (RM):</td>
                        <td></td>
                        <td>
                            <div align="right"><s:text name="jumlahAmaun" id="total" readonly="true" formatPattern="#,##0.00" /></div>
                        </td>
                    </tr></display:footer>
            </display:table>
            <s:hidden id="hidden2" name="hidden2" value="${object}"/>
            <br>
            <s:button name="SimpanMaklumatBayaranNS" id="simpan" value="Simpan" class="btn" onclick="save(this.name, this.form);"/>

            <!--c:if test="$actionBean.kodNegeri eq '05'"-->
            <!--s:button name="setSemulaBayaranNS" id="setSemula" value="Set Semula" class="btn" onclick="reset();"/-->

        </fieldset>
    </div>
</s:form>