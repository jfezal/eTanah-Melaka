<%-- 
    Document   : batalPermit
    Created on : Oct 1, 2015, 3:30:07 PM
    Author     : Hazwan
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
    
    function validateForm() {
        
        if ($("#noFailRuj").val() == "") {
            alert('Sila Masukkan No. Fail Rujukan !!!');
            return false;
        }else if ($("#noPermit").val() == "") {
            alert('Sila Masukkan No. Permit !!!');
            return false;
        }else {
            if (confirm('Adakah Pasti Untuk Hapuskan Maklumat Permit Ini ?')){
                return true;
            }else{
                return false;
            }
        }
    }
    
    function zeroPad(num,count)
    {
        var numZeropad = num + '';
        while(numZeropad.length < count) {
            numZeropad = "0" + numZeropad;
        }
        return numZeropad;
    }
    
    function formatNoPermit(){
        var num = $('#noPermit').val();
        var noPermit = zeroPad(num,'8');
        $('#noPermit').val(noPermit);
    }
    
 
</script>
<s:useActionBean beanclass = "etanah.view.ListUtil" var="list"/>
<stripes:form beanclass="etanah.view.stripes.permit.CancelPermitActionBean">
    <s:messages/>
    <div class="subtitle">
        <fieldset class="aras1">

            <legend>
                <td align="center"><font color="#001848"><b> Sila Pilih Jenis Lesen/Permit Dan Isikan No. Fail Rujukan Untuk Dihapuskan</b></font></td>
            </legend>
            <br/>

            <div class="content" align="center">
                <table border="0" width="30%">
                    <tr>
                        <td>
                            <font color="#001848"><b> No. Fail Rujukan  </b></font>
                        </td>
                        <td>
                            :
                        </td>
                        <td>
                            <s:text name="noFailRuj" id="noFailRuj" size="12" style="width:200px;" maxlength="30" onblur="this.value=this.value.toUpperCase();"/>
                        </td>
                    </tr>                                                   
                    <tr>
                        <td>
                            <font color="#001848"><b> No. Permit/Lesen  </b></font>     
                        </td>
                        <td>
                            :
                        </td>
                        <td>
                            <s:text name="noPermit" id="noPermit" size="12" style="width:200px;" maxlength="8" onkeyup="validateNumber(this,this.value);" onblur="formatNoPermit()"/>
                        </td>
                    </tr> 
                </table>
                <table border="0" width="20%">
                    <tr>
                        <td>
                            <br/>
                            <div align="center">
                                <s:submit name="hapusPermit" id="save" value="Batal" class="btn" onclick="return validateForm();"/>&nbsp;              
                            </div>
                        </td>
                    </tr>
                </table>
            </div>
        </fieldset>
    </div>


</stripes:form>



