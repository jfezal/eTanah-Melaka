<%--
    Document   : nilaianTanah_dan_PremiunBaru
    Created on : Feb 22, 2011, 09:24:40 AM
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
    
    function jpph(val,radioButton) {
        if(radioButton == "Y"){
            alert("Pemohon Dan Penyerah Adalah Orang Yang Sama !!!");
        }
    
        var url = '${pageContext.request.contextPath}/pembangunan/melaka/nilaianTanahDanPremiumBaru?simpanHantarJPPH&idPermohonan=' + val +'&radio1='+radioButton;
        $.get(url,
        function(data) {
            $('#page_div').html(data);
        }, 'html');
            
    }

</script>

<stripes:form beanclass="etanah.view.stripes.pembangunan.NilaianTanahDanPremiumBaruActionBean">
    <s:messages/>
    <div class="subtitle">
        <fieldset class="aras1">

            <legend>
                Nilaian
            </legend>

            <div align ="center">
                <td><legend><b>Hantar Ke JPPH ?  </b></legend></td>
                <td><s:radio name="radio1" value="Y" onclick="jpph('${actionBean.idPermohonan}','Y')"/> Ya &nbsp;
                    <s:radio name="radio1" value="T" onclick="jpph('${actionBean.idPermohonan}','T')"/>Tidak                   
                </td>

                <br><br>
                <td>                    
                </td>
            </div>

            <legend>
                Surat Kelulusan Syarat Baru dan Pengesahan Bayaran Premium
            </legend>
            <div class="content" align="center">
                <table border="0" width="35%">
                    <c:if test="${(actionBean.radio1 eq 'Y') || (actionBean.radio1 eq null)}">
                        <tr>
                            <td width="50%"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Tarikh Nilaian</font></td>
                            <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>
                            <td><s:text name="tarikhNilaian" id="datepicker" class="datepicker" size="12" style="width:130px;"/></td>
                        </tr>
                        <tr>
                            <td width="50%"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">No. Rujukan Surat JPPH</font></td>
                            <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>
                            <td><s:text name="nomborRujukan" id="nomborRujukan" size="12" style="width:130px;" maxlength="30"/></td>
                        </tr>

                        <tr>
                            <td width="50%"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Surat Kelulusan Syarat Baru </font></td>
                            <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                            <td><s:text name="nilaianTanah" id="nilaianTanah"  value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:130px;" size="12" /></td>
                        </tr>
                    </c:if>
                    <tr>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Pengesahan Bayaran Premium</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                        <td><s:text name="premium" id="premiumBaru"  value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:130px;" size="10"/></td>
                    </tr>                    
                </table><br/>
                <p>
                    <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>&nbsp;
                </p>
            </div>
        </fieldset>
    </div>
    <br/>               
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Surat Kelulusan Syarat Baru
            </legend>
            <div class="content" align="center">
                <table border="0" width="35%">
                    <td width="50%"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Premium </font></td>
                    <td><b><s:textarea name="premiumBaru" rows="5" cols="150" class="normal_text"/></b></td> 

                </table><br/>
                <p>
                    <s:button name="simpanPremiumBaru" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>&nbsp;
                </p>
            </div>
        </fieldset>
    </div>

</stripes:form>


