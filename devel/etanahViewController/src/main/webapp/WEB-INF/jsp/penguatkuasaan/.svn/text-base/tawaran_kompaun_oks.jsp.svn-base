<%--
    Document   : tawaran_kompaun_oks
    Created on : July 20, 2011, 7:50:22 PM
    Author     : sitifariza.hanim
--%>


<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/popup.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-impromptu.js"></script>
<script src="<%= request.getContextPath()%>/js/ui.datepicker-ms.js" type="text/javascript"></script>
<script type="text/javascript">
    $(document).ready(function() {
        var a = 0.00;
        var bil = ${actionBean.rowCount};
        for (var i = 0; i < bil; i++){
            var amount = document.getElementById('amountSyor'+i).value;
            if(amount !=""){
                document.getElementById("pilihOks"+i).checked = true;
                a += parseFloat(amount);

            }
        }
        if (a != null){
                    document.getElementById('jumCaraBayar').value=(parseFloat(a).toFixed(2));;
                }
    });

    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }

    function totalAmount(){

        var total = 0;
        var bil = ${actionBean.rowCount};
        for (var i = 0; i < bil; i++){
            var amount = document.getElementById('amountSyor'+i).value;
            if(amount !=""){
                total += parseFloat(amount);
                document.getElementById('jumCaraBayar').value=total;
            }
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

    function test(f) {
        $(f).clearForm();
    }

    function validateForm(){
        var bil =  ${fn:length(actionBean.senaraiAduanOrangKenaSyak)};
        var j = 0;
        for (var i = 0; i < bil; i++){
            var pilih = document.getElementById('pilihOks'+i).checked;
            var amountSyor = document.getElementById('amountSyor'+i).value;
                
               
            if( pilih == false && amountSyor!= ""){
                alert("Sila tanda di dalam kotak pilih sebelum simpan syor kompaun yang dikehendaki");
                $('#pilihOks'+i).focus();
                return false;
            }

            if( pilih == true && amountSyor==""){
                alert("Sila masukkan syor kompaun");
                $('#amountSyor'+i).focus();
                return false;
            }

            if( pilih == true){
                j++;
            }
        }

        if(j == 0 ){
            alert("Sila pilih orang kena syak yang hendak dikenakan kompaun. ");
            return false;
        }
        return true;

    }

</script>
<s:form beanclass="etanah.view.penguatkuasaan.OKSMaklumatKompaunActionBean" name="form1">
    <s:messages />

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Tawaran Kompaun 
            </legend>
            <div class="content">
                <p>
                <fieldset class="aras2">

                    <div class="instr-fieldset">
                        Sila pilih maklumat orang disyaki yang akan dikenakan kompaun
                    </div>

                    <div align="center" >

                        <display:table class="tablecloth" name="${actionBean.senaraiAduanOrangKenaSyak}" pagesize="10" cellpadding="0" cellspacing="0" id="line" >
                            <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                            <display:column title="Pilih">
                                <s:checkbox name="pilihOks${line_rowNum-1}" id="pilihOks${line_rowNum-1}" value="${line.idOrangKenaSyak}"/>
                            </display:column>
                            <display:column title="Nama" property="nama"/>
                            <display:column title="No.KP" property="noPengenalan"/>
                            <display:column title="Syor Kompaun(RM)">
                                <s:text name="amaunKompaunSyor${line_rowNum-1}" value ="${line.amaunKompaunSyor}" formatPattern="###0.00" id="amountSyor${line_rowNum-1}" size="20" maxlength="14" onblur="totalAmount();" onkeyup="validateNumber(this,this.value);" />
                            </display:column>
                            <display:footer>
                                <tr>
                                    <td colspan="4" align="right">Jumlah Syor(RM):</td>
                                   <%-- <td>
                                         <s:text name="jumCaraBayar" readonly="disable" formatPattern="#,##0.00" id="jumCaraBayar" />
                                    </td>--%>
                                    <td><input name="jumCaraBayar" value="0.00" id="jumCaraBayar" size="12"
                                               class="number" readonly="readonly" style="background:transparent;border:0px;" /></td>
                                <tr>
                                </display:footer>

                            </display:table>
                    </div>
                    <p>&nbsp;</p>
               
                <p align="center">
                    <s:button class="btn" name="simpan" value="Simpan" onclick="if(validateForm())doSubmit(this.form, this.name, 'page_div')"/>
                </p>
                 <br>
              </fieldset>
            </div>
        </fieldset>
    </div>
</s:form>
