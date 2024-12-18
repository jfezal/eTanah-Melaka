<%-- 
    Document   : notis_5A_NS
    Created on : Apr 6, 2011, 1:46:52 PM
    Author     : NageswaraRao
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page import="java.util.List;" %>
<%@ taglib prefix="stripes"
           uri="http://stripes.sourceforge.net/stripes-dynattr.tld"%>
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


    function removeCommas(val) {
        return val.replace(/\,/g, '');
    }


    function calc() {
        // validations

        var cukaiTahun = $("#cukaiTahun").val();
        var premium = $("#premium").val();
        var dendap = $("dendap").val();
        var sumbangan = $("#sumbangan").val();
        var bayaranUkur = $("#bayaranUkur").val();
        var tandaSempadan = $("#tandaSempadan").val();
        var penyediaan = $("#penyediaan").val();
        var pendaftaran = $("#pendaftaran").val();
        var sementara = $("#sementara").val();
        var notis = $("#notis").val();
        var pelan = $("#pelan").val();
        var jumlah = parseFloat(Number(removeCommas(cukaiTahun))) + parseFloat(Number(removeCommas(premium))) + parseFloat(Number(removeCommas(sumbangan))) + parseFloat(Number(removeCommas(bayaranUkur)))
                + parseFloat(Number(removeCommas(tandaSempadan))) + parseFloat(Number(removeCommas(penyediaan))) + parseFloat(Number(removeCommas(pendaftaran))) + parseFloat(Number(removeCommas(sementara)))
                + parseFloat(Number(removeCommas(notis))) + parseFloat(Number(removeCommas(dendap))) + parseFloat(Number(removeCommas(pelan)));
        alert(jumlah);
        $("#jumlah").val(jumlah);
    }

</script>

<stripes:form beanclass="etanah.view.stripes.pembangunan.Notis5ANSActionBean">
    <s:messages/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Borang 5A
            </legend>
            <div class="content" align="center">
                <table border="0" width="35%">
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Cukai Tahun Pertama</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td><s:text name="cukaiTahun" id="cukaiTahun" onchange="calc();" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:130px;"/></td>
                    </tr>
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Bayaran Premium </font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td><s:text name="premium" id="premium" onchange="calc();" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:130px;"/></td>
                    </tr>
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Denda Premium </font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td><s:text name="dendap" id="dendap" onchange="calc();" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:130px;"/></td>
                    </tr>
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Bayaran Kos Sumbangan Infrastruktur</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td><s:text name="sumbangan"  id="sumbangan" onchange="calc();" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:130px;"/></td>
                    </tr>
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Bayaran Ukur </font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td><s:text name="bayaranUkur" id="bayaranUkur" onchange="calc();" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:130px;"/></td>
                    </tr>
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Tanda Sempadan</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td><s:text name="tandaSempadan" id="tandaSempadan" onchange="calc();" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:130px;"/></td>
                    </tr>
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Penyediaan Suratan Hakmilik Tetap</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td><s:text name="penyediaan" id="penyediaan" onchange="calc();" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:130px;"/></td>
                    </tr>
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Pendaftaran Suratan Hakmilik Tetap</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td><s:text name="pendaftaran" id="pendaftaran" onchange="calc();" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:130px;"/></td>
                    </tr>
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Pendaftaran Suratan Hakmilik Sementara</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td><s:text name="sementara" id="sementara" onchange="calc();" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:130px;"/></td>
                    </tr>
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Notis</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td><s:text name="notis" id="notis" onchange="calc();" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:130px;"/></td>
                    </tr>
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Pelan Hakmilik</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td><s:text name="pelan" id="pelan" onchange="calc();" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:130px;"/></td>
                    </tr>
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Jumlah</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td><s:text name="jumlah" id="jumlah" readonly="readonly" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:130px;"/></td>
                    </tr>
                </table>
                <p>
                    <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>&nbsp;
                </p>
            </div>
        </fieldset>
    </div>

</stripes:form>
