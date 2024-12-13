<%-- 
    Document   : borang_5A_mlk
    Created on : Jun 23, 2011, 4:56:29 PM
    Author     : afham
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
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
        var hakmilikSementara = $("#hakmilikSementara").val();
        var jumlah = parseFloat(Number(removeCommas(cukai))) + parseFloat(Number(removeCommas(premium))) + + parseFloat(Number(removeCommas(upah))) + parseFloat(Number(removeCommas(kos)))
                + parseFloat(Number(removeCommas(pelantetap))) + parseFloat(Number(removeCommas(daftartetap)))+ parseFloat(Number(removeCommas(hakmilikSementara))) ;
        $("#jumlah").val(jumlah);
    }

</script>
<s:form beanclass="etanah.view.stripes.pelupusan.Borang5ARayuanActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
    <fieldset class="aras1">
        <legend>
            Maklumat Borang 5A
        </legend>
        <div class="content" align="center">
            <table border="0" width="35%">

                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Premium </font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td><s:hidden name="premium" id="premium" onchange="calc();" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:130px;"/>
                            <s:format value="${actionBean.premium}" formatPattern="#,##0.00"/></td>
                    </tr>
                     <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Cukai bagi tahun yang pertama</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td><s:text name="cukaiBagiTahunYangPertama" id="cukai" onchange="calc();" value="0.00" onkeyup="validateNumber(this,this.value);"  formatPattern="#,##0.00" style="width:130px;"/>
                          </td>
                    </tr>
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Bayaran Wang Pelan</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td><s:text name="bayaranWangPelan"  id="upah" onchange="calc();" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:130px;"/>
                        </td>
                    </tr>
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Bayaran Ukur</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td><s:text name="bayaranUkur" id="kos" onchange="calc();" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:130px;"/>
                        </td>
                    </tr>
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Tanda Sempadan</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td><s:text name="tandaSempadan" id="pelantetap" onchange="calc();" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:130px;"/>
                        </td>
                    </tr>
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Hakmilik Kekal</font></td>
                        <%--<td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Pendaftaran Suratan Hakmilik Tetap</font></td>--%>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td><s:text name="hakmilikKekal" id="daftartetap" onchange="calc();" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:130px;"/>
                        </td>
                    </tr>
                     <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Hakmilik Sementara</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td><s:text name="hakmilikSementara" id="hakmilikSementara" onchange="calc();" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:130px;"/>
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
</s:form>