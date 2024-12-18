<%-- 
    Document   : notis5A
    Created on : Feb 18, 2011, 6:24:10 PM
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
    

    function calc(){
        var cukai = $("#cukai").val();
        var premium = $("#premium").val();
        var upah = $("#upah").val();
        var kos = $("#kos").val();
        var pelantetap = $("#pelantetap").val();
        var daftartetap = $("#daftartetap").val();
        var notis = $("#notis").val();
        var jumlah = parseFloat(Number(removeCommas(cukai))) + parseFloat(Number(removeCommas(premium))) + + parseFloat(Number(removeCommas(upah))) + parseFloat(Number(removeCommas(kos)))
                + parseFloat(Number(removeCommas(pelantetap))) + parseFloat(Number(removeCommas(daftartetap))) + parseFloat(Number(removeCommas(notis))) ;
        $("#jumlah").val(jumlah);
    }

</script>

<stripes:form beanclass="etanah.view.stripes.pembangunan.Notis5AActionBean">
<s:messages/>
<div class="subtitle">
    <fieldset class="aras1">
        <legend>
            Maklumat Borang 5A
        </legend>
        <div class="content" align="center">
            <c:if test="${edit}">
            <table border="0" width="35%" cellspacing="5">                    
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Premium </font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td><s:text name="premium" id="premium" onchange="calc();" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:130px;text-align:right"/></td>
                    </tr>
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Sumbangan Saliran</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td><s:text name="sumbangan"  id="upah" onchange="calc();" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:130px;text-align:right"/></td>
                    </tr>
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Hasil Tahun Pertama</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td><s:text name="hasilTahun" id="cukai" onchange="calc();" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:130px;text-align:right"/></td>
                    </tr>
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Bayaran Pendaftaran Hakmilik Tetap</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td><s:text name="hakmilikTetap" id="pelantetap" onchange="calc();" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:130px;text-align:right"/></td>
                    </tr>
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Bayaran Pendaftaran Hakmilik Sementara</font></td>                        
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td><s:text name="hakmilikSemantara" id="daftartetap" onchange="calc();" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:130px;text-align:right"/></td>
                    </tr>
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Bayaran Pelan Hakmilik</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td><s:text name="bayaranPelan" id="kos" onchange="calc();" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:130px;text-align:right"/></td>
                    </tr>
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Notis</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td><s:text name="notis" id="notis" onchange="calc();" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:130px;text-align:right"/></td>
                    </tr>
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Jumlah</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td><s:text name="jumlah" id="jumlah" readonly="readonly" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:130px;text-align:right"/></td>
                    </tr>
                </table>
                <p>
                    <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>&nbsp;
                </p>
            </c:if>
            <c:if test="${!edit}">
                <table border="0" width="35%" cellspacing="5" >                    
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Premium </font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td align="right"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.premium}"/></td>                        
                    </tr>
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Sumbangan Saliran</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td align="right"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.sumbangan}"/></td>                        
                    </tr>
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Hasil Tahun Pertama</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td align="right"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.hasilTahun}"/></td>
                    </tr>                    
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Bayaran Pendaftaran Hakmilik Tetap</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td align="right"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.hakmilikTetap}"/></td>                        
                    </tr>
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Bayaran Pendaftaran Hakmilik Sementara</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td align="right"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.hakmilikSemantara}"/></td>                        
                    </tr>
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Bayaran Pelan Hakmilik</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td align="right"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.bayaranPelan}"/></td>
                    </tr>
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Notis</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td align="right"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.notis}"/></td>
                    </tr>
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Jumlah</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td align="right"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.jumlah}"/></td>
                    </tr>
                </table>
                
            </c:if>
             
            </div>
        </fieldset>
    </div>

</stripes:form>

