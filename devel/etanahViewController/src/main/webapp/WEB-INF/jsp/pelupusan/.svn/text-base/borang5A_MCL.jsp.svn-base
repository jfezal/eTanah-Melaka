<%-- 
    Document   : borang5A_MCL
    Created on : Aug 24, 2010, 3:08:26 PM
    Author     : afham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">

    function validateNumber(elmnt, content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = RemoveNonNumeric(content);
            return;
        }
    }
    function RemoveNonNumeric(strString)
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

    function calc(value) {
        var total;
        var a = document.kira.premiumRM.value;
        if (a == '') {
            a = 0;
            document.kira.premiumRM.value = parseFloat(0).toFixed(2);
        }
        else
            document.kira.premiumRM.value = parseFloat(a).toFixed(2);

        var b = document.kira.cukaiTahunPertamaRM.value;
        if (b == '') {
            b = 0;
            document.kira.cukaiTahunPertamaRM.value = parseFloat(0).toFixed(2);
        }
        else
            document.kira.cukaiTahunPertamaRM.value = parseFloat(b).toFixed(2);


        var c = document.kira.bayaranPelanRM.value;
        if (c == '') {
            c = 0;
            document.kira.bayaranPelanRM.value = parseFloat(0).toFixed(2);
        }
        else
            document.kira.bayaranPelanRM.value = parseFloat(c).toFixed(2);

        var d = document.kira.pendaftaranHMSrm.value;
        if (d == '') {
            d = 0;
            document.kira.pendaftaranHMSrm.value = parseFloat(0).toFixed(2);
        }
        else
            document.kira.pendaftaranHMSrm.value = parseFloat(d).toFixed(2);

        var e = document.kira.pendaftaranHMKrm.value;
        if (e == '') {
            e = 0;
            document.kira.pendaftaranHMKrm.value = parseFloat(0).toFixed(2);
        }
        else
            document.kira.pendaftaranHMKrm.value = parseFloat(e).toFixed(2);

        var f = document.kira.notis.value;
        if (f == '') {
            f = 0;
            document.kira.notis.value = parseFloat(0).toFixed(2);
        }
        else
            document.kira.notis.value = parseFloat(f).toFixed(2);

    <%--var e = document.kira.pendaftaranHMKrm.value ;--%>
    <%--total = parseFloat(a) + parseFloat(b) + parseFloat(c) + parseFloat(d) + parseFloat(e) ;--%>
        total = parseFloat(a) + parseFloat(b) + parseFloat(c) + parseFloat(d) + parseFloat(e) + parseFloat(f);
        document.kira.jumlah1.value = parseFloat(total).toFixed(2);
    <%-- var jum = $("#jumlah1").val();
     var jumlah = parseFloat(Number(value)) + parseFloat(Number(jum));
     $("#jumlah1").val(jumlah);--%>

    }
    function getHakmilikDetails(val) {

        doBlockUI();
        var edit = $('#edit').val();
        var url = '${pageContext.request.contextPath}/pelupusan/borang5A_MCL?searchHakmilik&idHakmilik=' + val + '&edit=' + edit;
        $.ajax({
            type: "GET",
            url: url,
            dataType: 'html',
            error: function(xhr, ajaxOptions, thrownError) {
                alert("error=" + xhr.responseText);
                doUnBlockUI();
            },
            success: function(data) {
                $('#page_div').html(data);
                doUnBlockUI();
            }
        });

        //        $.get(url,
        //        function(data){
        //            $('#page_div').html(data);
        //        },'html');
    }

</script>
<s:form beanclass="etanah.view.stripes.pelupusan.Borang5aMCL_ActionBean" name="kira">
    <s:messages/>
    <div class="subtitle" align="center">
        <fieldset  class="aras1">
            <legend>
                Senarai Hakmilik Terlibat
            </legend>

            <p>
                Hakmilik :
                <s:select name="idHakmilik" id="idmohon" onchange="getHakmilikDetails(this.value)">
                    <c:forEach items="${actionBean.hakmilikPermohonanList}" var="line">
                        <s:option value="${line.id}">${line.hakmilik.idHakmilik}(${line.hakmilik.bandarPekanMukim.daerah.nama}-${line.hakmilik.bandarPekanMukim.nama})</s:option>
                    </c:forEach>
                </s:select>
            </p>

        </fieldset>
        <fieldset class="aras1">
            <legend>
                Borang 5A
            </legend>
            <table border="0">

                <tr>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td>Premium Tanah 

                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td>RM <s:text id="a1" name="premiumRM" onchange="calc(this.value);" onkeyup="validateNumber(this,this.value);" style="width:80px;" formatPattern="#,##0.00" readonly="false"/>
                        <%--<c:if test="${item}"><s:text name="premiumRM" onchange="calc(this.value);" onkeyup="validateNumber(this,this.value);"  style="width:80px;"/></c:if>--%></td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td>Hasil Tahun Pertama 
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td>RM <s:text id="a2" name="cukaiTahunPertamaRM" onchange="calc(this.value);" onkeyup="validateNumber(this,this.value);" style="width:80px;" formatPattern="#,##0.00" readonly="false"/>
                        <%--<c:if test="${item}"><s:text name="cukaiTahunPertamaRM" onchange="calc(this.value);" onkeyup="validateNumber(this,this.value);" style="width:80px;"/></c:if>--%></td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td>Pelan Geran 
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td>RM <s:text id="a3" name="bayaranPelanRM" onchange="calc(this.value);" onkeyup="validateNumber(this,this.value);" style="width:80px;" formatPattern="#,##0.00" />
                        <%-- <c:if test="${item}"><s:text name="bayaranPelanRM" onchange="calc(this.value);" onkeyup="validateNumber(this,this.value);" style="width:80px;"/></c:if>--%></td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td>Pendaftaran Hakmilik Kekal 
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td>RM <s:text id="a4" name="pendaftaranHMKrm"  onchange="calc(this.value);" onkeyup="validateNumber(this,this.value);" style="width:80px;" formatPattern="#,##0.00" readonly="false"/>
                        <%-- <c:if test="${item}"><s:text name="penyediaanHMSrm" onchange="calc(this.value);" onkeyup="validateNumber(this,this.value);" style="width:80px;"/></c:if>--%></td>
                </tr>

                <tr>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td>Pendaftaran Hakmilik Sementara 
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td>RM <s:text id="a5" name="pendaftaranHMSrm"  onchange="calc(this.value);" onkeyup="validateNumber(this,this.value);" style="width:80px;" formatPattern="#,##0.00" readonly="false"/>
                        <%-- <c:if test="${item}"><s:text name="penyediaanHMSrm" onchange="calc(this.value);" onkeyup="validateNumber(this,this.value);" style="width:80px;"/></c:if>--%></td>

                </tr>
                <tr>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td><c:if test="${!item}">${actionBean.notis20}</c:if>
                        <c:if test="${item}">${actionBean.notis20}</c:if>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                        <td>RM <s:text id="a6" name="notis"  onchange="calc(this.value);" onkeyup="validateNumber(this,this.value);" style="width:80px;" formatPattern="#,##0.00" readonly="false"/>
                        <%-- <c:if test="${item}"><s:text name="penyediaanHMSrm" onchange="calc(this.value);" onkeyup="validateNumber(this,this.value);" style="width:80px;"/></c:if>--%></td>
                </tr>

                <%--</c:if>--%>

                <tr>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;JUMLAH</td>
                    <td>RM <%--<c:if test="${!item}"><s:text name="jumlah" id="jumlah" disabled="true" onchange="calc(this.value);" style="width:80px;"/></c:if>
                        <s:hidden name="jumlah"/>--%>
                        <s:text name="jumlah1" id="jumlah1" readonly="true" style="width:80px;" formatPattern="#,##0.00"/></td>
                </tr>


            </table><br><br>

            <s:button name="simpanMohonTuntutKos" id="save" class="btn" value="Simpan" onclick="doSubmit(this.form, this.name, 'page_div')"/>

        </fieldset>
    </div>

</s:form>
