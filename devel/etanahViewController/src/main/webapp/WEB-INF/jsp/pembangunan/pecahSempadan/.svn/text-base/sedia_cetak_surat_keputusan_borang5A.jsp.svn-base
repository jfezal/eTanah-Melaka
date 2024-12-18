<%-- 
    Document   : sedia_cetak_surat_kelulusan_borang5A
    Created on : Dec 31, 2009, 10:49:03 AM
    Author     : nursyazwani
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

    function calc(){
        var cukai = $("$cukai").val();
        var premium = $("#premium").val();
        var kos = $("#kos").val();
        var daftartetap = $("#daftartetap").val();
        var daftarsementara = $("#daftarsementara").val();
        var pelantetap = $("#pelantetap").val();
        var pelansementara = $("#pelansementara").val();
        var upah = $("#upah").val();
        var notis = $("#notis").val();
        var jumlah = parseFloat(Number(cukai)) + parseFloat(Number(premium)) + parseFloat(Number(kos)) + parseFloat(Number(daftartetap)) + parseFloat(Number(daftarsementara)) + parseFloat(Number(pelantetap)) + parseFloat(Number(pelansementara)) + parseFloat(Number(upah)) + parseFloat(Number(notis));
        $("#jumlah").val(jumlah);
    }

</script>

<stripes:form beanclass="etanah.view.stripes.pembangunan.SediaSuratBorang5AActionBean">
<s:messages/>
<div class="subtitle">
    <fieldset class="aras1">
        <legend>
            Maklumat Borang 5A
        </legend>
        <div class="content" align="center">
            <table border="0" width="35%">
                <%--<tr><td><p><label><font color="#003194">Cukai Tahun Pertama :</font></label>
                            &nbsp;&nbsp;RM&nbsp;&nbsp;<s:text name="cukai" id="cukai" onchange="calc();"
                                        value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:80px;"/></p></td></tr><br>
                    <tr><td><p><label><font color="#003194">Premium :</font></label>
                                &nbsp;&nbsp;RM&nbsp;&nbsp;<s:text name="premium" id="premium" onchange="calc();"
                                        value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:80px;"/></p></td></tr><br>
                    <tr><td><p><label><font color="#003194">Upah Ukur/Tanda Sempadan :</font></label>
                            &nbsp;&nbsp;RM&nbsp;&nbsp;<s:text name="upahUkur"  id="upah" onchange="calc();"
                                        value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:80px;"/></p></td></tr><br>
                    <tr><td><p><label><font color="#003194">Kos Sumbangan Infrastruktur :</font></label>
                                &nbsp;&nbsp;RM&nbsp;&nbsp;<s:text name="kosInfrastruktur" id="kos" onchange="calc();"
                                        value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:80px;"/></p></td></tr><br>
                    <tr><td><p><label><font color="#003194">Penyediaan Pelan Suratan Hakmilik Tetap :</font></label>
                                &nbsp;&nbsp;RM&nbsp;&nbsp;<s:text name="pelanSuratanTetap" id="pelantetap" onchange="calc();"
                                        value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:80px;"/></p></td></tr><br>
                    <tr><td><p><label><font color="#003194">Pendaftaran Suratan Hakmilik Tetap :</font></label>
                                &nbsp;&nbsp;RM&nbsp;&nbsp;<s:text name="suratanTetap" id="daftartetap" onchange="calc();"
                                        value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:80px;"/></p></td></tr><br>
                    <tr><td><p><label><font color="#003194">Penyediaan Pelan Suratan Hakmilik Sementara :</font></label>
                                &nbsp;&nbsp;RM&nbsp;&nbsp;<s:text name="pelanSuratanSementara" id="pelansementara" onchange="calc();"
                                        value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:80px;"/></p></td></tr><br>
                    <tr><td><p><label><font color="#003194">Pendaftaran Suratan Hakmilik Sementara :</font></label>
                                &nbsp;&nbsp;RM&nbsp;&nbsp;<s:text name="suratanSementara" id="daftarsementara" onchange="calc();"
                                        value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:80px;"/></p></td></tr><br>
                    <tr><td><p><label><font color="#003194">Notis :</font></label>
                                &nbsp;&nbsp;RM&nbsp;&nbsp;<s:text name="notis" id="notis" onchange="calc();"
                                        value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:80px;"/></p></td></tr><br>
                    <tr><td><p><label><font color="#003194">Jumlah :</font></label>
                            &nbsp;&nbsp;RM&nbsp;&nbsp;<s:text name="jumlah" id="jumlah" disabled="true"
                                        value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:80px;"/></p></td></tr><br>--%>
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Cukai Tahun Pertama</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td><s:text name="cukai" id="cukai" onchange="calc();" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:80px;"/></td>
                    </tr><br>
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Premium </font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td><s:text name="premium" id="premium" onchange="calc();" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:80px;"/></td>
                    </tr><br>
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Upah Ukur/Tanda Sempadan</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td><s:text name="upahUkur"  id="upah" onchange="calc();" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:80px;"/></td>
                    </tr><br>
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Kos Sumbangan Infrastruktur</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td><s:text name="kosInfrastruktur" id="kos" onchange="calc();" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:80px;"/></td>
                    </tr><br>
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Penyediaan Pelan Suratan Hakmilik Tetap</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td><s:text name="pelanSuratanTetap" id="pelantetap" onchange="calc();" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:80px;"/></td>
                    </tr><br>
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Pendaftaran Suratan Hakmilik Tetap</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td><s:text name="suratanTetap" id="daftartetap" onchange="calc();" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:80px;"/></td>
                    </tr><br>
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Penyediaan Pelan Suratan Hakmilik Sementara</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td><s:text name="pelanSuratanSementara" id="pelansementara" onchange="calc();" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:80px;"/></td>
                    </tr><br>
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Pendaftaran Suratan Hakmilik Sementara</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td><s:text name="suratanSementara" id="daftarsementara" onchange="calc();" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:80px;"/></td>
                    </tr><br>
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Notis</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td><s:text name="notis" id="notis" onchange="calc();" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:80px;"/></td>
                    </tr><br>
                    <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Jumlah</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td><s:text name="jumlah" id="jumlah" disabled="true" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:80px;"/></td>
                    </tr><br>
                </table>
                <p>
                    <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>&nbsp;
                </p>
            </div>
        </fieldset>
    </div>
   
</stripes:form>
   
