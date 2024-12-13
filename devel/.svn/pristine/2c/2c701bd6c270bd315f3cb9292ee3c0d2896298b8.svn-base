<%-- 
    Document   : borang_5a_Ns
    Created on : Apr 25, 2011, 1:05:49 PM
    Author     : Rohans
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


    function calc(){
        var cukai = $("#cukai").val();
        var premium = $("#premium").val();
        var bayaran = $("#bayaran").val();
        var tandaSempadan = $("#tandaSempadan").val();
        var penyediaanHakmilikTetap = $("#penyediaanHakmilikTetap").val();
        var pendaftaranHakmilikTetap = $("#pendaftaranHakmilikTetap").val();
        var hakmilikSementara = $("#hakmilikSementara").val();
        var notis = $("#notis").val();
        var jumlah = parseFloat(Number(removeCommas(cukai))) + parseFloat(Number(removeCommas(premium))) + + parseFloat(Number(removeCommas(bayaran))) + parseFloat(Number(removeCommas(tandaSempadan)))
                + parseFloat(Number(removeCommas(penyediaanHakmilikTetap))) + parseFloat(Number(removeCommas(pendaftaranHakmilikTetap)))+ parseFloat(Number(removeCommas(hakmilikSementara))+ parseFloat(Number(removeCommas(notis)))) ;
        $("#jumlah").val(jumlah);
    }



</script>

<stripes:form beanclass="etanah.view.stripes.pelupusan.Borang5ANsActionBean">
<s:messages/>
<div class="subtitle">
    <fieldset class="aras1">
        <legend>
            Maklumat Borang 5A
        </legend>
        <div class="content" align="center">
            <table border="0" width="35%">

                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Cukai  </font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td><s:text name="cukai" id="cukai" onchange="calc();" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:130px;"/></td>
                    </tr>
                     <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Premium </font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td><s:text name="premium" id="premium" onchange="calc();" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:130px;"/></td>
                    </tr>
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Bayaran Ukur<br>(tidak termasuk tanda sempadan)</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td><s:text name="bayaran"  id="bayaran" onchange="calc();" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:130px;"/></td>
                    </tr>
                  
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Tanda Sempadan</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td><s:text name="tandaSempadan" id="tandaSempadan" onchange="calc();" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:130px;"/></td>
                    </tr>
                      <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> Penyediaan Suratan Hakmilik Tetap </font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td><s:text name="penyediaanHakmilikTetap" id="penyediaanHakmilikTetap" onchange="calc();" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:130px;"/></td>
                    </tr>
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Pendaftaran Suratan Hakmilik Tetap</font></td>
                        <%--<td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Pendaftaran Suratan Hakmilik Tetap</font></td>--%>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td><s:text name="pendaftaranHakmilikTetap" id="pendaftaranHakmilikTetap" onchange="calc();" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:130px;"/></td>
                    </tr>
                     <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Pendaftaran Suratan Hakmilik Sementara</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td><s:text name="hakmilikSementara" id="hakmilikSementara" onchange="calc();" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:130px;"/></td>
                    </tr>
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Notis</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td><s:text name="notis" id="notis" onchange="calc();" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:130px;"/></td>
                    </tr>
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Jumlah</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td><s:text name="jumlah" id="jumlah" disabled="true" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:130px;"/></td>
                    </tr>
                </table>
                <p>
                    <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>&nbsp;
                </p>
            </div>
        </fieldset>
    </div>

</stripes:form>


