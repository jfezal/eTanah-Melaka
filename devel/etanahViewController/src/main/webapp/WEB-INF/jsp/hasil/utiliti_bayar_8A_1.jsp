<%-- 
    Document   : utiliti_bayar_8A_1
    Created on : Mar 22, 2011, 3:22:56 PM
    Author     : abdul.hakim
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloths.css"/>
<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN"
    "http://www.w3.org/p/html4/loose.dlabel">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<div align="center"><table style="width:99.2%" bgcolor="green">
        <tr>
            <td width="100%" height="20" >
                <div style="background-color: green;" align="center">
                    <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Utiliti Pembayaran Notis 8A</font>
                </div>
            </td>
        </tr>
    </table></div>

<stripes:form beanclass="etanah.view.stripes.hasil.UtilitiPembayaranNotis8A" id="utiliti_bayar_8A">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Akaun</legend>

            <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                <p>
                    <label>Nombor Akaun : </label>${actionBean.akaun.noAkaun} &nbsp;
                </p>
            </c:if>

            <p>
                <label>ID Hakmilik : </label>${actionBean.akaun.hakmilik.idHakmilik} &nbsp;
            </p>

            <p>
                <label>Daerah : </label>${actionBean.akaun.hakmilik.daerah.nama} &nbsp;
            </p>

            <p>
                <label>Mukim : </label>${actionBean.akaun.hakmilik.bandarPekanMukim.nama} &nbsp;
            </p>

            <p>
                <label>Pembayar : </label>${actionBean.akaun.pemegang.nama} &nbsp;
            </p>

            <p>
                <label>Alamat Pembayar : </label>${actionBean.akaun.pemegang.suratAlamat1} &nbsp;
            </p>

            <p>
                <label>&nbsp; </label>${actionBean.akaun.pemegang.suratAlamat2} &nbsp;
            </p>

            <p>
                <label>&nbsp; </label>${actionBean.akaun.pemegang.suratAlamat3} &nbsp;
            </p>

            <p>
                <label>&nbsp; </label>${actionBean.akaun.pemegang.suratAlamat4} &nbsp;
            </p>

            <p>
                <label>Poskod : </label>${actionBean.akaun.pemegang.suratPoskod} &nbsp;
            </p>

            <p>
                <label>Negeri : </label>${actionBean.akaun.pemegang.suratNegeri.nama} &nbsp;
            </p>

            <%--<p>
                <label>Jumlah Perlu Dibayar (RM) : </label>
                <fmt:formatNumber value="${actionBean.akaun.baki}" pattern="#,###.00"/> &nbsp;
            </p>--%>
        </fieldset>
    </div>
    <p></p>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Transaksi Akaun</legend>
            <div align="center">
                <display:table class="tablecloths" name="${actionBean.senaraiTransaksi}" pagesize="10" cellpadding="0" cellspacing="0" requestURI="/hasil/bayaran_8A" id="line">
                    <display:column title="Bil."><div align="center"> ${line_rowNum}.</div></display:column>
                    <display:column  title="Transaksi">
                        ${line.kodTransaksi.kod} - ${line.kodTransaksi.nama}
                    </display:column>
                    <display:column title="Amaun (RM)" style="text-align:right">
                        <fmt:formatNumber value="${line.amaun}" pattern="#,###0.00"/>
                    </display:column>
                </display:table>
            </div><br>
        </fieldset>
    </div>
    <p></p>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Cara Bayar</legend>
            <div align="center">
                <display:table name="${actionBean.senaraiCaraBayaran}" id="row" class="tablecloths">
                    <display:column title="Bil."><div align="center">${row_rowNum}.</div></display:column>
                    <display:column title="Cara Bayaran" class="tunai">
                        <s:select name="senaraiCaraBayaran[${row_rowNum - 1}].kodCaraBayaran.kod" id="senaraiCaraBayaran${row_rowNum - 1}"
                                  onchange="javaScript:change(${row_rowNum -1})">
                            <s:option label="Pilih ..."  value="0" />
                            <c:forEach items="${listUtil.senaraiKodCaraBayaran}" var="i" >
                                <s:option value="${i.kod}">${i.kod} - ${i.nama}</s:option>
                            </c:forEach>
                        </s:select>
                    </display:column>

                    <display:column title="Bank / Agensi Pembayaran" >
                        <s:select name="senaraiCaraBayaran[${row_rowNum - 1}].bank.kod" id="bank${row_rowNum - 1}">
                            <s:option label="Pilih..." value="0" />
                            <s:options-collection collection="${listUtil.senaraiKodBank}"  label="nama" value="kod" />
                        </s:select>
                    </display:column>

                    <display:column title="Cawangan" >
                        <s:text name="senaraiCaraBayaran[${row_rowNum - 1}].bankCawangan" id="caw${row_rowNum - 1}" size="20" onkeyup="this.value=this.value.toUpperCase();"/>
                    </display:column>

                    <display:column title="No. Rujukan" >
                        <em id="a${row_rowNum - 1}">*</em><s:text name="senaraiCaraBayaran[${row_rowNum - 1}].noRujukan" id="rujukan${row_rowNum - 1}" size="20" />
                    </display:column>

                    <display:column title="Tarikh">
                        <em id="tcek${row_rowNum - 1}">*</em><s:text name="tarikhCek[${row_rowNum-1}]" id="trkh${row_rowNum - 1}" size="20" onchange="dateValidation(this.value,${row_rowNum -1})" readonly="true" maxlength="10" class="datepicker"/>
                    </display:column>

                    <display:column title="Amaun (RM)">
                        <s:text name="senaraiCaraBayaran[${row_rowNum - 1}].amaun" size="12" class="number"
                                onblur="javascript:updateTotal(this,${row_rowNum - 1});" id="amaun${row_rowNum - 1}"/>
                    </display:column>
                    <display:footer>
                        <tr>
                            <td colspan="6"><div align="right"><b>Jumlah (RM):</b></div></td>
                            <td><input name="jumCaraBayar" value="0.00" id="jumCaraBayar" size="12" class="number" readonly="true"/></td>
                        </tr>
                    </display:footer>
                </display:table >
            </div>
        </fieldset>
    </div>

    <div align="center"><table style="width:99.2%" bgcolor="green">
        <tr>
            <td align="right">
                <s:submit name="back" value="Kembali" class="btn"/>&nbsp;
                <s:submit name="save" value="Bayar" class="btn" id="byr" onclick="return validateByr()"/>
            </td>
        </tr>
    </table></div>
</stripes:form>

<script type="text/javascript">
    $(document).ready(function() {
	    // set the first default payment to Tunai
	    $('#senaraiCaraBayaran0').val('T');
            $('#xx').val('');
	    // focus on the first payment
            $("#bank0").hide();
            $("#caw0").hide();
            $("#rujukan0").hide();
            $("#trkh0").hide();
            $("#a0").hide();
            $("#tcek0").hide();
            $('#baki').attr("disabled", "true");
            var bil = parseInt(${actionBean.bil});

            for (var i = 1; i < bil; i++){
                $("#a"+i).hide();
                $("#tcek"+i).hide();
                $('#bank'+i).attr("disabled", "true");
                $('#caw'+i).attr("disabled", "true");
                $('#rujukan'+i).attr("disabled", "true");
                $('#trkh'+i).attr("disabled", "true");
                $('#amaun'+i).attr("disabled", "true");
                $('#amaun'+i).val("0");
            }

            function stopRKey(evt) {
               var evt = (evt) ? evt : ((event) ? event : null);
               var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null);
               if ((evt.keyCode == 13) && (node.type=="text")) {return false;}
            }
           document.onkeypress = stopRKey;
    });

    function updateTotal (inputTxt,row){
        var total = 0;
        var bil = parseInt(${actionBean.bil});
    	var a = document.getElementById('amaun' + row)
            if ((isNaN(a.value))||((a.value) =="")){
                alert("Nombor tidak sah");
                inputTxt.value = RemoveNonNumeric(a);
                $('#jumCaraBayar').val("0.00");
                return;
            }
            total += parseFloat(a.value);
        var t = document.getElementById('jumCaraBayar');
        t.value = total.toFixed(2);
        inputTxt.value = parseFloat(inputTxt.value).toFixed(2);
        updtTot();
    }

    function updtTot(){
        var total = 0;
        for (var i=0; i<5; i++){
            var a = document.getElementById('amaun' + i)
            total += parseFloat(a.value);
        }
        var t = document.getElementById('jumCaraBayar');
        t.value = total.toFixed(2);
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

    function dateValidation(value, row){
        var vsplit = value.split('/');
        var fulldate = vsplit[1]+'/'+vsplit[0]+'/'+vsplit[2];
        var today = new Date();
        var sdate = new Date(fulldate);
        if (sdate > today){
            alert("Tarikh yang dimasukkan tidak sesuai.");
            $('#trkh'+row).val("");
        }
    }

    function change(row){
    	var curr = document.getElementById('amaun' + row);
        var bil = parseInt(${actionBean.bil});
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
                    <%--$("#trkh"+i).val("");--%>
                if (a.value == 'T'){
                    $('#a'+i).hide();
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
                $('#amaun'+i).val("0");
                $('#bank'+i).val("0");
                $('#caw'+i).val("");
                $('#rujukan'+i).val("");
                $('#trkh'+i).val("");
                updateTotal();
            }
            
            for(var j = i+1; j < bil; j++){
                var c = document.getElementById('senaraiCaraBayaran'+j);
                if(c.value != '0'){
                    if((a.value == 'T')&&(c.value == 'T')){
                        alert("Hanya satu mod Bayaran Tunai sahaja dibenarkan.");
                        return c.value = '0';
                    }
                }
            }
            sequencePayment(row);
        }
    }

    function sequencePayment(row){
        if((row-1)>0){
            var x = document.getElementById('senaraiCaraBayaran'+(row-1));
            var y = document.getElementById('senaraiCaraBayaran'+(row));
            if((x.value == '0')&&(y.value != '0')){
                alert("Sila masukkan Mod Bayaran mengikut turutan.");
                return y.value = '0';
            }
        }
    }

    function validateByr(){
        var min = parseFloat(${actionBean.akaun.baki});
        var jumBayar = parseFloat((document.getElementById('jumCaraBayar')).value);
        if(jumBayar < min){
            alert("Amaun yang dimasukkan kurang dari RM "+min.toFixed(2));
            return false;
        }else{return true;}
    }
</script>
