<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>

<stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/pub/styles/styles.css" />
<title>Kaunter: Bayaran</title>

</head>
<body>

<script type="text/javascript" language="javascript" >

function updateTotal(inputTxt){
    var total = 0;
    for (var i = 0; i < 5; i++){
    	var a = document.getElementById('amaun' + i)
    	if ((isNaN(a.value))||((a.value) =="")){
    	    alert("Nombor tidak sah");
            inputTxt.value = RemoveNonNumeric(a);
    	    return;
        }
        total += parseFloat(a.value);
    }
    var t = document.getElementById('jumCaraBayar');
    t.value = total.toFixed(2);
    inputTxt.value = parseFloat(inputTxt.value).toFixed(2);
}

function RemoveNonNumeric( strString ){
        var strValidCharacters = "1234567890.";
        var strReturn = "0.00";
        var strBuffer = "0";
        var intIndex = 0.00;
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
</script>
<c:set var="action" value="/kaunter/permohonan"/>
<c:set var="nextStep" value="Step6"/>
<c:set var="word" value="Langkah 5"/>
<c:set var="prevStep" value="Step4"/>
<c:if test="${!empty carian}">
    <c:set var="action" value="/daftar/carian"/>
    <c:set var="nextStep" value="Step5"/>
    <c:set var="word" value="Langkah 4"/>
    <c:set var="prevStep" value="Step3"/>
</c:if>
<c:if test="${!empty prev}">
    <c:set var="prevStep" value="${prev}"/>
</c:if>
<c:set value="Hakmilik" var="word2"/>
<c:if test="${!empty perserahan}">
    <c:set value="Perserahan" var="word2"/>
</c:if>

<p class=title>${word}: Terima Bayaran</p>

<stripes:messages />
<stripes:errors />

<p class=instr>Pastikan maklumat dibawah adalah betul dan masukkan butir-butir pembayaran. PERINGATAN: Tidak dibenarkan menggunakan cara pembayaran yang lain
bersama dengan Cek dan hanya satu Cek dibenarkan.</p>

<stripes:form action="${action}" >

<fieldset class="aras1">

<display:table name="${actionBean.senaraiKodUrusan}" id="row1" class="tablecloth" style="width:95%;" >

    <display:column title="Bil">
        ${row1_rowNum}
    </display:column>

	<display:column title="Urusan" >
	   ${row1.kod == "0" ? "-" : row1.kod} - ${row1.kod == "0" ? "" : row1.nama}
	</display:column>

	<display:column title="${word2} Terlibat">
	   ${actionBean.senaraiHakmilikUrusan[row1_rowNum - 1]}
	</display:column>

	<display:column property="caj" title="Caj(RM)" style="width:100px;text-align:right;"  />

	<display:footer>
		<tr>
			<td colspan="3" align="left">Jumlah Perlu Dibayar (RM):</td>
			<td><stripes:text name="jumlahCaj" class="number"
                                      readonly="readonly" style="background:transparent;border:0 px;" id="total"/></td>
		</tr>
	</display:footer>

</display:table>

<c:if test="${actionBean.jumlahCaj > 0}" >
    <div align="center">
        <display:table name="${actionBean.senaraiCaraBayaran}" id="row" style="width:85%;" class="tablecloth">

            <display:column title="Bil">
                ${row_rowNum}
            </display:column>

            <display:column title="Cara Bayaran" >
                <stripes:select name="senaraiCaraBayaran[${row_rowNum - 1}].kodCaraBayaran.kod"
                                id="senaraiCaraBayaran${row_rowNum - 1}" onchange="javaScript:change(this)">
                    <stripes:option value="0" label="Pilih ..." />
                    <stripes:options-collection collection="${listUtil.senaraiKodCaraBayaran}"  label="nama" value="kod" sort="nama"/>
                </stripes:select>
            </display:column>

            <display:column title="Bank / Agensi Pembayaran" >
                <stripes:select name="senaraiCaraBayaran[${row_rowNum - 1}].bank.kod" id="bank${row_rowNum - 1}">
                    <stripes:option label="Pilih..." value="0" />
                    <stripes:options-collection collection="${listUtil.senaraiKodBank}"  label="nama" value="kod" />
                </stripes:select>
            </display:column>

            <display:column title="Cawangan" >
                <stripes:text name="senaraiCaraBayaran[${row_rowNum - 1}].bankCawangan" size="20" id="caw${row_rowNum - 1}"/>
            </display:column>

            <display:column title="No. Rujukan" >
                <em id="field${row_rowNum - 1}">*</em><stripes:text name="senaraiCaraBayaran[${row_rowNum - 1}].noRujukan" size="20" id="rujukan${row_rowNum - 1}"/>
            </display:column>

            <display:column title="Amaun (RM)" >
                <stripes:text name="senaraiCaraBayaran[${row_rowNum - 1}].amaun" size="12" class="number"
                              onblur="javascript:updateTotal(this);" id="amaun${row_rowNum - 1}" />
            </display:column>

            <display:footer>
                <tr>
                    <td colspan="5" align="right">Jumlah (RM):</td>
                    <td><input name="jumCaraBayar" value="0.00" id="jumCaraBayar" size="12"
                               class="number" readonly="readonly" style="background:transparent;border:0 px;" /></td>
                <tr>
                </display:footer>

            </display:table >
    </div>

</c:if>

<p><label>&nbsp;</label>
<stripes:submit name="${prevStep}" value="Kembali" class="btn" />
<stripes:submit name="CancelAll" value="Batal" class="btn" />
<stripes:submit name="${nextStep}" value="Selesai" class="btn" onclick="return validate(this.form);"/>
</p>


</stripes:form>

<script language="javascript" >
	$(document).ready(function() {
	    // set the first default payment to Tunai
	    $('#senaraiCaraBayaran0').val('T');
	    // focus on the first payment
	    $('#amaun0').focus();
            $("#bank0").hide();
            $("#caw0").hide();
            $("#rujukan0").hide();
            $("#field0").hide();
            for (var i = 1; i < 5; i++){
                $("#field"+i).hide();
                $('#bank'+i).attr("disabled", "true");
                $('#caw'+i).attr("disabled", "true");
                $('#rujukan'+i).attr("disabled", "true");
                $('#amaun'+i).attr("disabled", "true");
                $('#amaun'+i).val("0");
            }
    });
</script>
<script type="text/javascript">
    function change(){
        for (var i = 0; i < 5; i++){
            var a = document.getElementById('senaraiCaraBayaran'+i);
            if(a.value != '0'){
                    $('#bank'+i).removeAttr("disabled");
                    $('#caw'+i).removeAttr("disabled");
                    $('#rujukan'+i).removeAttr("disabled");
                    $('#amaun'+i).removeAttr("disabled");
                if (a.value == 'T'){
                    $('#field'+i).hide();
                    $('#bank'+i).hide();
                    $("#caw"+i).hide();
                    $("#rujukan"+i).hide();
                }
                 else if((a.value == 'KW')||(a.value == 'WP')){
                    $('#bank'+i).val("PMB");
                    $('#bank'+i).attr("disabled", "true");
                    $('#bank'+i).show();
                    $('#caw'+i).show();
                    $('#rujukan'+i).show();
                    $('#field'+i).show();
                    if(a.value == '0'){
                        $('#a'+i).hide();
                    }
                }
                else{
                    $('#bank'+i).removeAttr("disabled");
                    $('#bank'+i).show();
                    $('#caw'+i).show();
                    $('#rujukan'+i).show();
                    $('#field'+i).show();
                    if(a.value == '0'){
                        $('#field'+i).hide();
                    }
                }
            }

            var b = document.getElementById('senaraiCaraBayaran'+(i+1));
            if(b.value != '0'){
                if(((a.value == 'CT')||(a.value == 'CL')||(a.value == 'CC')||(a.value == 'BC')||(a.value == 'IB')||(a.value == 'CB'))&&(b.value != '0')){
                    alert("Bayaran Cek tidak boleh disertakan bersama Mod Bayaran lain.");
                    return b.value = '0';
                }
                if(((b.value == 'CT')||(b.value == 'CL')||(b.value == 'CC')||(b.value == 'BC')||(b.value == 'IB')||(b.value == 'CB'))&&(a.value != '0')){
                    alert("Bayaran Cek tidak boleh disertakan bersama Mod Bayaran lain.");
                    return b.value = '0';
                }
                if((a.value == '0')&&(b.value != '0')){
                    alert("Sila masukkan Mod Bayaran mengikut turutan.");
                    return b.value = '0';
                }
                if((a.value == 'DB')&&(b.value != '0')){
                    alert("Bayaran Deraf Bank tidak boleh disertakan bersama Mod Bayaran lain.");
                    return b.value = '0';
                }
                if((b.value == 'DB')&&(a.value != '0')){
                    alert("Bayaran Deraf Bank tidak boleh disertakan bersama Mod Bayaran lain.");
                    return b.value = '0';
                }
                if((a.value == 'EF')&&(b.value != '0')){
                    alert("Bayaran Wang Dalam Pindahan tidak boleh disertakan bersama Mod Bayaran lain.");
                    return b.value = '0';
                }
                if((b.value == 'EF')&&(a.value != '0')){
                    alert("Bayaran Wang Dalam Pindahan tidak boleh disertakan bersama Mod Bayaran lain.");
                    return b.value = '0';
                }
                if((a.value == 'T')&&(b.value == 'T')){
                    alert("Hanya satu mod Bayaran Tunai sahaja dibenarkan.");
                    return b.value = '0';
                }
            }
            for(var j = i+1; j < 5; j++){
                var c = document.getElementById('senaraiCaraBayaran'+j);
                if(c.value != '0'){
                    if((a.value == 'T')&&(c.value == 'T')){
                        alert("Hanya satu mod Bayaran Tunai sahaja dibenarkan.");
                        return c.value = '0';
                    }
                }
            }
        }
    }

    function validate(){
        var u = parseFloat($('#total').val());
        var t = parseFloat($('#jumCaraBayar').val());
        if(u > t){
            var bal = u -t;
            alert("Bayaran anda kurang RM "+(bal).toFixed(2));
            return false;
        }
        for (var i = 0; i < 5; i++){
            var a = document.getElementById('senaraiCaraBayaran'+i);
            var c = $('#rujukan'+i).val();
            if((a.value != '0')&&(a.value != 'T')){
                if(c == ""){
                    alert("Sila Masukkan Nombor Rujukan.");
                    $('#rujukan'+i).focus();
                    return false;
                }
            }
        }
        return true;
    }
</script>

</body>
</html>