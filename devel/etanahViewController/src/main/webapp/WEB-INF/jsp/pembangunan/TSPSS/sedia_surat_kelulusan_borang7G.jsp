<%-- 
    Document   : sedia_surat_kelulusan_borang7G
    Created on : Dec 31, 2009, 11:51:17 AM
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
        var premium = $("#premium").val();
        var kos = $("#kos").val();
        var daftar = $("#daftar").val();
        var sewa = $("#sewa").val();
        var upah = $("#upah").val();
        var notis = $("#notis").val();
        var jumlah = parseFloat(Number(premium)) + parseFloat(Number(kos)) + parseFloat(Number(daftar)) + parseFloat(Number(sewa)) + parseFloat(Number(upah)) + parseFloat(Number(notis));
        $("#jumlah").val(jumlah);
    }
</script>
<stripes:form beanclass="etanah.view.stripes.pembangunan.SediaNotis7GActionBean">
    <s:messages/>
    <s:hidden name="permohonan.idPermohonan" />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
              Maklumat Notis 7G
            </legend>
            <div class="content" align="center">
                <table border="0" width="80%">
                    <tr><td><p><label><font color="black">Premium Tambahan :</font></label>
                                &nbsp;&nbsp;RM&nbsp;&nbsp;<s:text name="premium" id="premium" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:80px;" onchange="calc();"/></p></td></tr><br>
                    <tr><td><p><label><font color="black">Kos Sumbangan Infrastruktur :</font></label>
                                &nbsp;&nbsp;RM&nbsp;&nbsp;<s:text name="kos"  id="kos" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:80px;" onchange="calc();"/></p></td></tr><br>
                    <tr><td><p><label><font color="black">Pendaftaran/Penulisan :</font></label>
                                &nbsp;&nbsp;RM&nbsp;&nbsp;<s:text name="daftar" id="daftar" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:80px;" onchange="calc();"/></p></td></tr><br>
                    <tr><td><p><label><font color="black">Sewa Baru :</font></label>
                                &nbsp;&nbsp;RM&nbsp;&nbsp;<s:text name="sewa" id="sewa" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:80px;" onchange="calc();"/></p></td></tr><br>
                    <tr><td><p><label><font color="black">Bayaran Upah Ukur :</font></label>
                                &nbsp;&nbsp;RM&nbsp;&nbsp;<s:text name="upah" id="upah" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:80px;" onchange="calc();"/></p></td></tr><br>
                    <tr><td><p><label><font color="black">Notis :</font></label>
                                &nbsp;&nbsp;RM&nbsp;&nbsp;<s:text name="notis" id="notis" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:80px;" onchange="calc();"/></p></td></tr><br>
                    <tr><td><p><label><font color="black">Jumlah :</font></label>
                                &nbsp;&nbsp;RM&nbsp;&nbsp;<s:text name="jumlah" id="jumlah" disabled="true" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:80px;"/>
                           </p></td></tr><br>
                </table>
            </div>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Perakuan Pentadbir Tanah ${actionBean.peng.kodCawangan.daerah.nama}</legend>
            <br>
            <p>
                <label>Perakuan :</label>
                <s:textarea name="ulasan" cols="50"/>
            </p><br>
            <p>
                <label>&nbsp; &nbsp; &nbsp;</label>
                <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>&nbsp;
            </p>
        </fieldset>
    </div>
    
</stripes:form>
  