<%-- 
    Document   : notis_7G_NS
    Created on : Apr 6, 2011, 1:47:53 PM
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
      function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = RemoveNonNumeric(content);
            return;
        }
    }

    function RemoveNonNumeric( strString )
    {
        var strValidCharacters = "1234567890";
        var strReturn = "";
        var strBuffer = "";
        var intIndex = 0;
        // Loop through the string
        for( intIndex = 0; intIndex < strString.length; intIndex++ )
        {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                strReturn += strBuffer;
            }
        }
        return strReturn;
    }

    function removeCommas(val){
        return val.replace(/\,/g,'');
    }


    function calc(elmnt,content){
        // validations
        validateNumber(elmnt,content);

        var premium = $("#premium").val();
        var sumbangan = $("#sumbangan").val();
        var penulisan = $("#penulisan").val();
        var sewaBaru = $("#sewaBaru").val();
        var bayaranUpahUkur = $("#bayaranUpahUkur").val();
        var tandaSempadan = $("#tandaSempadan").val();
        var penyediaan = $("#penyediaan").val();
        var pendaftaran = $("#pendaftaran").val();
        var semantara = $("#semantara").val();
        var notis = $("#notis").val();
        var borang7c = $("#borang7c").val();

        var jumlah =  parseFloat(Number(removeCommas(premium))) + parseFloat(Number(removeCommas(sumbangan)))  + parseFloat(Number(removeCommas(penulisan)))
            + parseFloat(Number(removeCommas(sewaBaru))) + parseFloat(Number(removeCommas(bayaranUpahUkur))) + parseFloat(Number(removeCommas(tandaSempadan)))
            + parseFloat(Number(removeCommas(penyediaan))) + parseFloat(Number(removeCommas(pendaftaran))) + parseFloat(Number(removeCommas(semantara)))
            + parseFloat(Number(removeCommas(notis))) + parseFloat(Number(removeCommas(borang7c))) + parseFloat(Number(removeCommas(dendap)));
        <%--alert("jumlah:"+jumlah);--%>
        $("#jumlah").val(jumlah);
    }

</script>

<stripes:form beanclass="etanah.view.stripes.pembangunan.Notis7GNSActionBean">
<s:messages/>
<div class="subtitle">
    <fieldset class="aras1">
        <legend>
            Maklumat Borang 7G
        </legend>
        <div class="content" align="center">
            <table border="0" width="35%">
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Premium</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td><s:text name="premium" id="premium" onkeyup="calc(this,this.value);" value="0.00"  formatPattern="#,##0.00" style="width:130px;"/></td>
                    </tr>
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Bayaran Upah Ukur</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td><s:text name="bayaranUpahUkur" id="bayaranUpahUkur" onkeyup="calc(this,this.value);" value="0.00"  formatPattern="#,##0.00" style="width:130px;"/></td>
                    </tr>
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Tanda Sempadan</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td><s:text name="tandaSempadan" id="tandaSempadan" onkeyup="calc(this,this.value);" value="0.00"  formatPattern="#,##0.00" style="width:130px;"/></td>
                    </tr>
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Kos Sumbangan Infrastruktur</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td><s:text name="sumbangan" id="sumbangan" onkeyup="calc(this,this.value);" value="0.00"  formatPattern="#,##0.00" style="width:130px;"/></td>
                    </tr>
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Pendaftaran dan Penulisan</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td><s:text name="penulisan"  id="penulisan" onkeyup="calc(this,this.value);" value="0.00"  formatPattern="#,##0.00" style="width:130px;"/></td>
                    </tr>
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Notis</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td><s:text name="notis" id="notis" onkeyup="calc(this,this.value);" value="0.00"  formatPattern="#,##0.00" style="width:130px;"/></td>
                    </tr>
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Sewa Tahunan Baru</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td><s:text name="sewaBaru" id="sewaBaru" onkeyup="calc(this,this.value);" value="0.00"  formatPattern="#,##0.00" style="width:130px;"/></td>
                    </tr>
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Bayaran Borang 7C</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td><s:text name="borang7c" id="borang7c" onkeyup="calc(this,this.value);" value="0.00"  formatPattern="#,##0.00" style="width:130px;"/></td>
                    </tr>
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Penyediaan Hakmilik Tetap</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td><s:text name="penyediaan" id="penyediaan" onkeyup="calc(this,this.value);" value="0.00"  formatPattern="#,##0.00" style="width:130px;"/></td>
                    </tr>
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Pendaftaran Hakmilik Tetap</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td><s:text name="pendaftaran" id="pendaftaran" onkeyup="calc(this,this.value);" value="0.00"  formatPattern="#,##0.00" style="width:130px;"/></td>
                    </tr>
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Pendaftaran Hakmilik Semantara</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td><s:text name="semantara" id="semantara" onkeyup="calc(this,this.value);" value="0.00"  formatPattern="#,##0.00" style="width:130px;"/></td>
                    </tr>
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Bayaran Denda Premium </font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td><s:text name="dendap" id="dendap" onchange="calc();" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:130px;"/></td>
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




