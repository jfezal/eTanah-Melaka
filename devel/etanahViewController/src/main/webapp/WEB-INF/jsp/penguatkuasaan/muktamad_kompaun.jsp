<%--
    Document   : muktamad_kompaun
    Created on : Sep 14, 2011, 6:00:22 PM
    Author     : latifah.iskak
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
                a += parseFloat(amount);
                if (a != null){
                    document.getElementById('jumCaraBayar').value=a;
                }
            }
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
        var kodUrusan =  $('#kodUrusan').val();
        for (var i = 0; i < bil; i++){

            var amountSyor = document.getElementById('amountSyor'+i).value;
            var tempohBayarKompaun = document.getElementById('tempohBayarKompaun'+i).value;

            if(amountSyor == ""){
                alert("Sila masukkan nilai muktamad kompaun");
                $('#amountSyor'+i).focus();
                return false;
            }

            if(tempohBayarKompaun == ""){
                alert("Sila masukkan tempoh bayaran kompaun");
                $('#tempohBayarKompaun'+i).focus();
                return false;
            }

            if(kodUrusan == "427"){
                if(amountSyor < 50){
                    alert("Amaun tidak boleh kurang dari RM50");
                    $('#amountSyor'+i).focus();
                    return false;
                }
                if(amountSyor > 100){
                    alert("Amaun tidak boleh lebih dari RM100");
                    $('#amountSyor'+i).focus();
                    return false;
                }
            }


        }

        return true;
    }

</script>
<s:form beanclass="etanah.view.penguatkuasaan.OKSMaklumatKompaunActionBean" name="form1">
    <s:messages/>
    <s:errors/>
    <s:hidden name="permohonan.kodUrusan.kod" id="kodUrusan"/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Tawaran Kompaun 
            </legend>
            <div class="content">
                <p>
                <fieldset class="aras2">

                    <div class="instr-fieldset">
                        <font color="red">PERINGATAN:</font>Sila masukkan jumlah muktamad kompaun untuk setiap OKS yang akan dikenakan kompaun
                    </div>
                    <br>

                    <div align="center" >

                        <display:table class="tablecloth" name="${actionBean.senaraiAduanOrangKenaSyak}" cellpadding="0" cellspacing="0" id="line" >
                            <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                            <display:column title="Nama" property="nama"/>
                            <display:column title="No.KP">
                                <c:if test="${line.noPengenalan ne null}">
                                    ${line.noPengenalan}
                                </c:if>
                                <c:if test="${line.noPengenalan eq null}">
                                    Tiada data
                                </c:if>
                            </display:column>
                            <display:column title="Muktamad Kompaun(RM)">
                                <s:text name="muktamadKompaun${line_rowNum-1}" value ="${line.amaunKompaunSyor}" formatPattern="0.00" id="amountSyor${line_rowNum-1}" size="20" maxlength="14" onblur="totalAmount();" onkeyup="validateNumber(this,this.value);" />
                            </display:column>
                            <display:column title="Tempoh Hari">
                                <s:text name="tempohBayarKompaun${line_rowNum-1}" value ="${line.tempohBayarKompaun}"  id="tempohBayarKompaun${line_rowNum-1}" size="10" maxlength="5"  onkeyup="validateNumber(this,this.value);" />
                            </display:column>
                            <display:footer>
                                <tr>
                                    <td colspan="4" align="right">Jumlah Syor(RM):</td>
                                    <td><s:text name="jumCaraBayar" value="0.00" id="jumCaraBayar" size="12" formatPattern="0.00"
                                            class="number" readonly="readonly" style="background:transparent;border:0px;" /></td>
                                <tr>
                                </display:footer>

                            </display:table>
                    </div>
                    <p>&nbsp;</p>

                    <p align="center">
                        <s:button class="btn" name="simpanMuktamadKompaunOKS" value="Simpan"  onclick="if(validateForm())doSubmit(this.form, this.name, 'page_div');"/>
                    </p>
                </fieldset>


            </div>
        </fieldset>
    </div>
</s:form>
