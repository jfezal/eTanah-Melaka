<%-- 
    Document   : bayaran_jaminan
    Created on : Mar 23, 2011, 2:04:59 PM
    Author     : sitifariza.hanim
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>

<script type="text/javascript">
    
    function test(f) {
        $(f).clearForm();
    }
    function validateForm(){

        if($('#amaunTuntutan').val()=="")
        {
            alert("Sila Masukkan Jumlah Deposit");
            $('#amaunTuntutan').focus();
            return false;
        }

        if($('#pembayar').val()=="")
        {
            alert("Sila Masukkan Nama Pembayar");
            $('#pembayar').focus();
            return false;
        }
        return true;
    }

    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }

    function removeNonNumeric( strString )
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

    function updateTotal(){
        document.getElementById("amaunTuntutan").value = parseFloat(document.getElementById("amaunTuntutan").value).toFixed(2);
    }
    
</script>
<s:form beanclass="etanah.view.penguatkuasaan.MaklumatBayaranJaminanActionBean" name="form1">
    <s:messages />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Bayaran Jaminan
            </legend>
            <div class="content">                 
                <p>
                    <label><em>*</em> Jumlah Deposit (RM):</label>
                    <s:text id="jumlahBayaran" name="jumlahBayaran"  class="number" formatPattern="0.00" onkeyup="validateNumber(this,this.value);"/>&nbsp;
                </p>

                <p>
                    <label><em>*</em> Nama Pembayar :</label>
                    <s:text name="pembayar" id="pembayar" size="33" onkeyup="this.value=this.value.toUpperCase();"/>&nbsp;&nbsp;
                </p>
            </div>
        </fieldset>
        <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
      
        <p align="right">
            <s:button  name="reset" value="Isi Semula" class="btn" onclick="return test();"/>
            <s:button  name="simpanBayaranJaminan" value="Simpan" class="btn" onclick="if(validateForm())doSubmit(this.form, this.name, 'page_div');"/>
        </p>
    </div>
</s:form>
