<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN"
    "http://www.w3.org/p/html4/loose.dlabel">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloths.css"/>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<div align="center"><table style="width:99.2%" bgcolor="green">
    <tr>
        <td width="100%" height="20" >
            <div style="background-color: green;" align="center">
                <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Kemasukan Resit Manual</font>
            </div>
        </td>
    </tr>
</table></div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>

<s:form beanclass="etanah.view.stripes.hasil.KemasukkanResitManualActionBean" id="resit_manual">
    <s:errors />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Cara Bayaran</legend>
            <p class=instr>
                <font color="black">&nbsp;&nbsp;Masukkan butir-butir pembayaran.<br>&nbsp;
                    <c:if test="${actionBean.kodNegeri ne 'melaka'}">
                        <font color="red">PERINGATAN:</font> Ruangan yang bertanda <em>*</em> adalah mandatori. Sistem ini hanya membenarkan Bayaran Kewangan 38 bagi bayaran yang melibatkan Cukai Tanah Sahaja.
                    </c:if>
                    <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                        <font color="red">PERINGATAN:</font> Ruangan yang bertanda <em>*</em> adalah mandatori.
                    </c:if>
                    <%--Tidak dibenarkan menggunakan cara pembayaran yang lain bersama dengan pembayaran menggunakan Cek ,
                    Deraf Bank dan Wang dalam Pindahan.--%>
                </font>
            </p>
            &nbsp;&nbsp;&nbsp;&nbsp;<s:errors field="amaun"/>
            <div align="center" id="tble">
                <table>
                    <tr>
                        <td align="right">
                            <s:submit name="addCaraBayar" value="Tambah" class="btn"/>
                            <s:button name=" " value="Isi Semula" class="btn" onclick="clearText1('resit_manual');"/>
                        </td>
                    </tr>
                    <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                        <tr>
                            <td>
                                <display:table name="${actionBean.senaraiCaraBayaran}" id="row" class="tablecloths">
                                    <%--<display:column title="Bil."><div align="center">${row_rowNum}.</div></display:column>--%>

                                    <display:column title="Transaksi" >
                                        <s:select name="listTr[${row_rowNum - 1}]" id="tr${row_rowNum - 1}" style="width:350px" onchange="javaScript:change(this)">
                                            <option value="0">Pilih....</option>
                                            <c:forEach items="${actionBean.senaraiKodTransaksi}" var="j" >
                                                <option value="${j.kod}" >${j.kod} - ${j.nama}</option>
                                            </c:forEach>
                                        </s:select>
                                    </display:column>

                                    <display:column title="Cara Bayaran" class="tunai">
                                        <em id="a${row_rowNum - 1}">*</em>
                                        <s:select name="senaraiCaraBayaran[${row_rowNum - 1}].kodCaraBayaran.kod"
                                                  id="senaraiCaraBayaran${row_rowNum - 1}" onchange="javaScript:change(this)" >
                                            <s:option value="0" label="Pilih ..." />
                                            <s:options-collection collection="${listUtil.senaraiKodCaraBayaran}"  label="nama" value="kod" sort="nama"/>
                                        </s:select>
                                    </display:column>

                                    <display:column title="Juruwang" >
                                        <em id="b${row_rowNum - 1}">*</em>
                                        <s:text name="juruwang[${row_rowNum - 1}]" id="wang${row_rowNum - 1}" size="20" onblur="this.value=this.value.toUpperCase();"/>
                                    </display:column>

                                    <display:column title="Nombor Resit Kew. 38" >
                                        <em id="c${row_rowNum - 1}">*</em>
                                        <s:text name="kewangan38[${row_rowNum - 1}]" id="kew38${row_rowNum - 1}" size="20" onblur="checkingNoReceipt(this.value, ${row_rowNum -1})" onkeyup="this.value=this.value.toUpperCase();"/>
                                    </display:column>

                                    <display:column title="Tarikh Resit Kew. 38" >
                                        <em id="d${row_rowNum - 1}">*</em>
                                        <div align="center"><s:text name="trkhWang38[${row_rowNum - 1}]" id="trkhWang38${row_rowNum - 1}" class="datepicker" size="10" onchange="dateValidations(this.value,${row_rowNum -1})"/></div>
                                    </display:column>
                                    <c:choose>
                                        <c:when test="${actionBean.kodNegeri eq 'melaka'}">
                                            <display:column title="Nombor Akaun" >
                                                <em id="s${row_rowNum - 1}">*</em><s:text name="listAkaun[${row_rowNum - 1}]" id="akaun${row_rowNum - 1}" size="14" onblur="javascript:checking(this.value, 'akaun', '${row_rowNum - 1}');" onkeyup="this.value=this.value.toUpperCase();"/>
                                            </display:column>
                                        </c:when>
                                        <c:otherwise>
                                            <display:column title="ID Hakmilik" >
                                                <s:text name="listAkaun[${row_rowNum - 1}]" id="akaun${row_rowNum - 1}" size="14" onblur="javascript:checking(this.value, 'hakmilik', '${row_rowNum - 1}');"/>
                                            </display:column>
                                        </c:otherwise>
                                    </c:choose>
                                    <display:column title="Amaun (RM)">
                                        <%--<em id="field3${row_rowNum - 1}">*</em>--%>
                                        <s:text name="senaraiCaraBayaran[${row_rowNum - 1}].amaun" size="12" class="number"
                                                onblur="javascript:updateTotal(this);" id="amaun${row_rowNum - 1}"/>
                                    </display:column>
                                    <display:footer>
                                        <tr>
                                            <td colspan="6"><div align="right"><b>Jumlah (RM):</b></div></td>
                                            <td><input name="jumCaraBayar" value="0.00" id="jumCaraBayar" size="12" class="number" readonly="true"/></td>
                                        </tr>
                                    </display:footer>
                                </display:table >
                            </td>
                        </tr>
                    </c:if>

                    <c:if test="${actionBean.kodNegeri ne 'melaka'}">
                        <tr>
                            <td>
                                <display:table name="${actionBean.senaraiCaraBayaran}" id="row" class="tablecloths">
                                    <display:column title="Cara Bayaran" class="tunai">
                                        <s:select name="senaraiCaraBayaran[${row_rowNum - 1}].kodCaraBayaran.kod" id="senaraiCaraBayaran${row_rowNum - 1}"
                                                  onchange="javaScript:change1(this.value, ${row_rowNum -1})">
                                            <s:option label="Pilih ..."  value="0" />
                                            <c:forEach items="${listUtil.senaraiKodCaraBayaran}" var="i" >
                                                <s:option value="${i.kod}">${i.kod} - ${i.nama}</s:option>
                                            </c:forEach>
                                        </s:select>
                                        <%--<s:select name="senaraiCaraBayaran[${row_rowNum - 1}].kodCaraBayaran.kod"
                                                  id="senaraiCaraBayaran${row_rowNum - 1}" onchange="javaScript:change1(this,${row_rowNum - 1})" style="width:170px">
                                            <s:option value="0" label="Pilih ..." />
                                            <s:options-collection collection="${listUtil.senaraiKodCaraBayaran}"  label="nama" value="kod" sort="nama"/>
                                        </s:select>--%>
                                    </display:column>

                                    <display:column title="Bank / Agensi Pembayaran" >
                                        <s:select name="senaraiCaraBayaran[${row_rowNum - 1}].bank.kod" id="bank${row_rowNum - 1}" style="width:250px">
                                            <s:option label="Pilih..." value="0" />
                                            <s:options-collection collection="${listUtil.senaraiKodBank}"  label="nama" value="kod" />
                                        </s:select>
                                    </display:column>

                                    <display:column title="Cawangan">
                                        <s:text name="senaraiCaraBayaran[${row_rowNum - 1}].bankCawangan" id="caw${row_rowNum - 1}" size="15" onkeyup="this.value=this.value.toUpperCase();"/>
                                    </display:column>

                                    <display:column title="No. Rujukan" >
                                        <em id="a${row_rowNum - 1}">*</em><s:text name="senaraiCaraBayaran[${row_rowNum - 1}].noRujukan" id="rujukan${row_rowNum - 1}" size="15" onkeyup="this.value=this.value.toUpperCase();"/>
                                    </display:column>

                                    <display:column title="Tarikh">
                                        <em id="g${row_rowNum - 1}">*</em><s:text name="tarikhCek[${row_rowNum - 1}]" id="trkh${row_rowNum - 1}" class="datepicker" size="10" onchange="dateValidations(this.value,${row_rowNum -1})"/>
                                    </display:column>

                                     <display:column title="Nombor Resit Kew. 38" >
                                        <em id="c${row_rowNum - 1}">*</em><s:text name="kewangan38[${row_rowNum - 1}]" id="kew38${row_rowNum - 1}" size="17" onkeyup="this.value=this.value.toUpperCase();" onblur="checkingNoReceipt(this.value, ${row_rowNum -1})"/>
                                    </display:column>

                                    <display:column title="ID Hakmilik" >
                                        <em id="e${row_rowNum - 1}">*</em><s:text name="listAkaun[${row_rowNum - 1}]" id="akaun${row_rowNum - 1}" size="14" onkeyup="this.value=this.value.toUpperCase();" onblur="javascript:checking(this.value, 'hakmilik', '${row_rowNum - 1}');"/>
                                    </display:column>

                                    <display:column title="Tarikh Resit Kew. 38">
                                        <em id="d${row_rowNum - 1}">*</em><s:text name="trkhWang38[${row_rowNum - 1}]" id="trkhWang38${row_rowNum - 1}" class="datepicker" size="10" onchange="dateValidations(this.value,${row_rowNum -1})"/>
                                    </display:column>

                                    <display:column title="Juruwang" >
                                        <em id="b${row_rowNum - 1}">*</em><s:text name="juruwang[${row_rowNum - 1}]" id="wang${row_rowNum - 1}" size="16" onkeyup="this.value=this.value.toUpperCase();"/>
                                    </display:column>

                                    <display:column title="Mod Bayaran" >
                                        <em id="f${row_rowNum - 1}">*</em><s:select name="modBayaran[${row_rowNum - 1}]" id="mod${row_rowNum - 1}">
                                                                              <s:option value="0">Sila Pilih...</s:option>
                                                                              <s:option value="B">Biasa</s:option>
                                                                              <s:option value="L">Lewat</s:option>
                                                                          </s:select>
                        <%--<s:text name="modBayaran[${row_rowNum - 1}]" id="mod${row_rowNum - 1}" size="17" onkeyup="this.value=this.value.toUpperCase();" onblur="checkingNoReceipt(this.value, ${row_rowNum -1})"/>--%>
                                    </display:column>
                                        
                                    <display:column title="Amaun (RM)">
                                        <%--<em id="field3${row_rowNum - 1}">*</em>--%>
                                        <s:text name="senaraiCaraBayaran[${row_rowNum - 1}].amaun" size="10" class="number"
                                                onblur="javascript:updateTotal(this);" id="amaun${row_rowNum - 1}"/>
                                    </display:column>
                                    <display:footer>
                                        <tr>
                                            <td colspan="10"><div align="right"><b>Jumlah (RM):</b></div></td>
                                            <td><input name="jumCaraBayar" value="0.00" id="jumCaraBayar" size="12" class="number" readonly="true"/></td>
                                        </tr>
                                    </display:footer>
                                </display:table >
                            </td>
                        </tr>
                    </c:if>
                </table>
            </div>
            <br>
        </fieldset>
    </div>

    <div align="center"><table style="width:99.2%" bgcolor="green">
        <tr>
            <td align="right">
                <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                    <s:submit name="save" value="Simpan" class="btn" onclick="return validate(this.form)" id="next"/>&nbsp;
                </c:if>
                <c:if test="${actionBean.kodNegeri ne 'melaka'}">
                    <s:submit name="save1" value="Bayar" class="btn" onclick="return validate(this.form)" id="next"/>&nbsp;
                </c:if>
                <s:submit name="cancel" value="Batal" class="btn"/>&nbsp;
            </td>
        </tr>
     </table></div>

    <%--</c:if>--%>
</s:form>
<script language="javascript" >
	$(document).ready(function() {
	    $('#amaun0').val((${actionBean.jumCaraBayar}).toFixed(2));
            $('#jumCaraBayar').val((${actionBean.jumCaraBayar}).toFixed(2));
            $("#bank0").hide();
            $("#caw0").hide();
            $("#rujukan0").hide();
            $("#trkh0").hide();
            $("#a0").hide();
            $("#g0").hide();
            $("#tr0").val('61401');
            $('#next').attr("disabled", "true");
            $('#senaraiCaraBayaran0').val("T");
            var bil = parseInt(${actionBean.bil});

            for (var i = 1; i < bil; i++){
                $('#bank'+i).attr("disabled", "true");
                $('#caw'+i).attr("disabled", "true");
                $('#kew38'+i).attr("disabled", "true");
                $('#rujukan'+i).attr("disabled", "true");
                $('#akaun'+i).attr("disabled", "true");
                $('#amaun'+i).attr("disabled", "true");
                $('#wang'+i).attr("disabled", "true");
                $('#trkhWang38'+i).attr("disabled", "true");
                $('#mod'+i).attr("disabled", "true");
                $('#amaun'+i).val("0");
                $("#a"+i).hide();
                $("#b"+i).hide();
                $("#c"+i).hide();
                $("#d"+i).hide();
                $("#e"+i).hide();
                $("#f"+i).hide();
                $("#g"+i).hide();
                $("#s"+i).hide();
            }
    });
</script>
<script  language="javascript" >
    function updateTotal(inputTxt){
        var total = 0;
        var bil = ${actionBean.bil};
        for (var i = 0; i <bil; i++){
            var a = document.getElementById('amaun' + i)
            if ((isNaN(a.value))||((a.value) =="")){
                alert("Nombor tidak sah");
                inputTxt.value = RemoveNonNumeric(a);
                $('#jumCaraBayar').val("0.00");
                return;
            }
            total += parseFloat(a.value);
        }
        var t = document.getElementById('jumCaraBayar');
        t.value = total.toFixed(2);
        inputTxt.value = parseFloat(inputTxt.value).toFixed(2);
        var jum = document.getElementById("jumCaraBayar");
        if(jum.value > 0){
            $('#next').removeAttr("disabled");
        }
        if(jum.value < 0){
            $('#next').attr("disabled", "true");
        }
    }

    function dateValidations(value, row){
        var vsplit = value.split('/');
        var fulldate = vsplit[1]+'/'+vsplit[0]+'/'+vsplit[2];
        var today = new Date();
        var sdate = new Date(fulldate);
        if (sdate > today){
            alert("Tarikh yang dimasukkan tidak sesuai.");
            $('#trkhWang38'+row).val("");
        }
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
<script type="text/javascript">
    function edit(id){
        window.open("${pageContext.request.contextPath}/hasil/resit_manual?displayDetails&idHakmilik="+id, "eTanah",
                "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1000,height=690");
    }

    function change(){
        var bil = ${actionBean.bil};
        for (var i = 0; i < bil; i++){
            var a = document.getElementById('tr'+i);
            if(a.value != '0'){
                $('#senaraiCaraBayaran'+i).removeAttr("disabled");
                $('#kew38'+i).removeAttr("disabled");
                $('#akaun'+i).removeAttr("disabled");
                $('#amaun'+i).removeAttr("disabled");
                $('#wang'+i).removeAttr("disabled");
                $('#trkhWang38'+i).removeAttr("disabled");
                $("#a"+i).show();
                $("#b"+i).show();
                $("#c"+i).show();
                $("#d"+i).show();
                $("#s"+i).hide();
           }
           if(a.value == '61401'){
                $('#senaraiCaraBayaran'+i).removeAttr("disabled");
                $('#kew38'+i).removeAttr("disabled");
                $('#akaun'+i).removeAttr("disabled");
                $('#amaun'+i).removeAttr("disabled");
                $('#wang'+i).removeAttr("disabled");
                $('#trkhWang38'+i).removeAttr("disabled");
                $("#a"+i).show();
                $("#b"+i).show();
                $("#c"+i).show();
                $("#d"+i).show();
                $("#s"+i).show();
            }
            if(a.value == '0'){
                $('#senaraiCaraBayaran'+i).attr("disabled", "true");
                $('#kew38'+i).attr("disabled", "true");
                $('#akaun'+i).attr("disabled", "true");
                $('#amaun'+i).attr("disabled", "true");
                $('#wang'+i).attr("disabled", "true");
                $('#trkhWang38'+i).attr("disabled", "true");
                $("#a"+i).hide();
                $("#b"+i).hide();
                $("#c"+i).hide();
                $("#d"+i).hide();
                $("#s"+i).hide();
            }
            var b = document.getElementById('tr'+(i+1));
            if((a.value == '0')&&(b.value != '0')){
                alert("Sila masukkan Transaksi mengikut turutan.");
                return b.value = '0';
            }
        }
    }

    function checkingNoReceipt(resit, row){
        $.get("${pageContext.request.contextPath}/hasil/resit_manual?doCheckNoResit&noResit=" + resit,
        function(data){
            if(data == '1'){
                alert("Nombor resit "+resit+" telah digunakan. Sila masukkan Nombor Resit yang baru.");
                $('#kew38'+row).val("");
            }
        });
    }
    
    function validate(){
        var bil = parseInt(${actionBean.bil});
        for (var i = 0; i < bil; i++){
            var a = document.getElementById('tr'+i);
            var accnt = document.getElementById('akaun'+i);
            var cb = $('#senaraiCaraBayaran'+i).val();
            var kew = $('#kew38'+i).val();
            var acc = $('#trkhWang38'+i).val();
            var jw = $('#wang'+i).val();
            if(a.value != '0'){
                if(cb == '0'){
                    alert("Sila Pilih salah satu cara bayaran.");
                    $('#senaraiCaraBayaran'+i).focus();
                    return false;
                }
                if(kew == ""){
                    alert("Sila Masukkan Nombor Resit Kewangan 38.");
                    $('#kew38'+i).focus();
                    return false;
                }
                if(acc == ""){
                    alert("Sila Masukkan Tarikh Kewangan 38.");
                    $('#trkhWang38'+i).focus();
                    return false;
                }
                if(jw == ""){
                    alert("Sila Nama Juruwang.");
                    $('#wang'+i).focus();
                    return false;
                }
                if((a.value=="61401")&&(accnt.value == "")){
                    alert("Sila Masukkan Nombor Akaun bagi pembayaran Cukai Tanah.");
                    $('#akaun'+i).focus();
                    return false;
                }
            }
        }
        return true;
    }

    function reset1(){
	    $('#amaun0').val("0.00");
            $('#jumCaraBayar').val("0.00");
            $('#tr0').val("61401");
            $('#senaraiCaraBayaran'+i).val("0");
        var bil = parseInt(${actionBean.bil});
        for (var i = 1; i < bil; i++){
            $('#senaraiCaraBayaran'+i).attr("disabled", "true");
            $('#kew38'+i).attr("disabled", "true");
            $('#akaun'+i).attr("disabled", "true");
            $('#trkhWang38'+i).attr("disabled", "true");
            $('#field'+i).attr("disabled", "true");
            $('#field1'+i).attr("disabled", "true");
            $('#field2'+i).attr("disabled", "true");
            $('#field3'+i).attr("disabled", "true");
            $('#amaun'+i).attr("disabled", "true");
            $('#amaun'+i).val("0");
            $('#senaraiCaraBayaran'+i).val("0");
            $('#tr'+i).val("0");
        }
    }

    function hakmilikValidation(){
        var id = document.getElementById('hakmilik');
        var akaun = document.getElementById('akaun');
        if((id.value == "") && (akaun.value == "")){
            alert("Sila Masukkan ID Hakmilik atau Nombor Akaun");
            $('#hakmilik').focus()
            return false;
        }
    }

    function clearText1(id) {
        $("#"+id+" input:text").each( function(el) {
            $(this).val('');
        });

        $("#" + id +" select").each( function(el) {
            $(this).val('');
        });
        reset1();
    }

    function dateValidation(value){
        var vsplit = value.split('/');
        var fulldate = vsplit[1]+'/'+vsplit[0]+'/'+vsplit[2];
        var today = new Date();
        var sdate = new Date(fulldate);
        if (sdate > today){
            alert("Tarikh yang dimasukkan tidak sesuai.");
            $('#date').val("");
        }
    }

    function change1(f, row){
    	var curr = document.getElementById('amaun' + row);
        var ax = document.getElementById('senaraiCaraBayaran'+row);
        $("#trkh"+row).val("");
        for (var i = 0; i < (row+1); i++){
            var a = document.getElementById('senaraiCaraBayaran'+i);
            if(a.value != '0'){
                    $('#bank'+i).removeAttr("disabled");
                    $('#caw'+i).removeAttr("disabled");
                    $('#rujukan'+i).removeAttr("disabled");
                    $('#trkh'+i).removeAttr("disabled");
                    $('#amaun'+i).removeAttr("disabled");
                    $('#kew38'+i).removeAttr("disabled");
                    $('#akaun'+i).removeAttr("disabled");
                    $('#wang'+i).removeAttr("disabled");
                    $('#trkhWang38'+i).removeAttr("disabled");
                    $('#mod'+i).removeAttr("disabled");
                    $("#a"+i).show();
                    $("#b"+i).show();
                    $("#c"+i).show();
                    $("#d"+i).show();
                    $("#e"+i).show();
                    $("#f"+i).show();
                    $("#g"+i).show();
                if (a.value == 'T'){
                    $('#a'+i).hide();
                    $('#g'+i).hide();
                    $('#tcek'+i).hide();
                    $('#bank'+i).hide();
                    $("#caw"+i).hide();
                    $("#rujukan"+i).hide();
                    $("#trkh"+i).hide();
                    $('#a'+i).val("");
                    $('#bank'+i).val('0');
                    $("#caw"+i).val("");
                    $("#rujukan"+i).val("");
                    var today = new Date();
                    $("#trkh"+i).val(today.getDate()+"/"+(today.getMonth()+1)+"/"+today.getYear());
                }
                 else if((a.value == 'KW')||(a.value == 'WP')){
                    $('#bank'+i).val("PMB");
                    $('#bank'+i).attr("disabled", "true");
                    $('#bank'+i).show();
                    $('#caw'+i).show();
                    $('#rujukan'+i).show();
                    $('#trkh'+i).show();
                    $('#a'+i).show();
                    $('#tcek'+i).show();
                    if(a.value == '0'){
                        $('#a'+i).hide();
                        $('#tcek'+i).hide();
                    }
                }
                else{
                    $('#bank'+i).removeAttr("disabled");
                    $('#bank'+i).show();
                    $('#caw'+i).show();
                    $('#rujukan'+i).show();
                    $('#trkh'+i).show();
                    $('#a'+i).show();
                    $('#tcek'+i).show();
                    if(a.value == '0'){
                        $('#a'+i).hide();
                        $('#tcek'+i).hide();
                    }
                }
            }else{
                $("#a"+i).hide();
                $('#tcek'+i).hide();
                $('#bank'+i).attr("disabled", "true");
                $('#caw'+i).attr("disabled", "true");
                $('#rujukan'+i).attr("disabled", "true");
                $('#trkh'+i).attr("disabled", "true");
                $('#amaun'+i).attr("disabled", "true");
                $('#kew38'+i).attr("disabled", "true");
                $('#akaun'+i).attr("disabled", "true");
                $('#wang'+i).attr("disabled", "true");
                $('#trkhWang38'+i).attr("disabled", "true");
                $('#mod'+i).attr("disabled", "true");
                $('#amaun'+i).val("0");
                $('#bank'+i).val("0");
                $('#caw'+i).val("");
                $('#rujukan'+i).val("");
                $('#trkh'+i).val("");
                $('#amaun'+i).val("0");
                $("#b"+i).hide();
                $("#c"+i).hide();
                $("#d"+i).hide();
                $("#e"+i).hide();
                $("#f"+i).hide();
                updateTotal();
            }
            <%--for(var j = i+1; j < 5; j++){
                var c = document.getElementById('senaraiCaraBayaran'+j);
                if(c.value != '0'){
                    if((a.value == 'T')&&(c.value == 'T')){
                        alert("Hanya satu mod Bayaran Tunai sahaja dibenarkan.");
                        return c.value = '0';
                    }
                }
            }--%>
            seqPayment(row);
        }
        autoBalance(row, ax, curr);
    }

    function seqPayment(row){
        if((row-1)>0){
            var x = document.getElementById('senaraiCaraBayaran'+(row-1));
            var y = document.getElementById('senaraiCaraBayaran'+(row));
            if((x.value == '0')&&(y.value != '0')){
                alert("Sila masukkan Mod Bayaran mengikut turutan.");
                return y.value = '0';
            }
        }
    }

    function checking(inputTxt, type, row){
        var a = $("#akaun"+row).val();
        inputTxt = inputTxt.toUpperCase();
        if(inputTxt != ""){
            $.get("${pageContext.request.contextPath}/hasil/check_idhakmilik_accountNo?doCheckHakmilik&idHakmilik=" + inputTxt+"&type="+type,
            function(data){
                if(data == '1'){
                    $("#msg" + inputTxt).html("OK");
                    getAmaunCukai(inputTxt, row);
                    if(type == 'hakmilik')
                        $("#hakmilik").val(inputTxt.toUpperCase());
                }
                else if(data =='0'){
                    $("#akaun"+row).val("");
                    if(type == 'akaun')
                        alert("Nombor Akaun " + inputTxt + " tidak wujud!");
                    if(type == 'hakmilik')
                        alert("ID Hakmilik " + inputTxt + " tidak wujud!");
                    <%--return false;--%>
                }else if(data =='3'){
                    $("#akaun"+row).val("");
                    alert("ID Hakmilik " + a + " masih belum didaftarkan.");
                }
                <%--else{
                    $("#hakmilik").val("");
                    $("#noAkaun").val("");
                    alert("ID Hakmilik " + inputTxt + " tidak wujud!");
                    entsub();
                }--%>
            });
        }
    }

    function getAmaunCukai(id,row){
        $.get("${pageContext.request.contextPath}/hasil/resit_manual?getAmaun&id=" + id,
            function(data){
                if(data != null){
                    var baki = parseFloat(data).toFixed(2);
                    $("#amaun"+row).val(baki);
                    updateTotal(baki);
                }
            });
    }
</script>
