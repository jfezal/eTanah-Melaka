<%-- 
    Document   : cukai_bayaran
    Created on : Feb 13, 2013, 4:26:37 PM
    Author     : abdul.hakim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN"
    "http://www.w3.org/p/html4/loose.dlabel">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloths.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<div id="ak">
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

    <div align="center"><table style="width:99.2%" bgcolor="green">
            <tr>
                <td width="100%" height="20" >
                    <div style="background-color: green;" align="center">
                        <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Kutipan Cukai</font>
                    </div>
                </td>
            </tr>
        </table></div>
    <img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif"
         width="50" height="50" style="display: none" alt=""/>
    <s:form beanclass="etanah.view.stripes.hasil.CukaiActionBean" id="hasil">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Bayaran</legend>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.accList}" requestURI="/hasil/kutipan_hasil" id="line">
                        <display:column title="Bil.">
                            <div align="center">
                                ${line_rowNum}.
                                <s:hidden name="" class="x${line_rowNum-1}" value="${line.noAkaun}" id="idHm${line_rowNum-1}"/>
                            </div>
                        </display:column>
                        <display:column  title="Nombor Akaun" >
                            <a href="#" onclick="edit('${line.noAkaun}');return false;">${line.noAkaun}</a>
                        </display:column>
                        <display:column title="Baki (RM)" style="text-align:right;">
                            <fmt:formatNumber value="${line.baki}" pattern="#,###,##0.00"/>
                            <s:hidden name="" class="y${line_rowNum-1}" value="${line.baki}" id="bal${line_rowNum-1}"/>
                        </display:column>
                        <display:column title="Amaun (RM)" style="text-align:right;">
                            <c:choose>
                                <c:when test="${line.baki < 0}">
                                    <fmt:formatNumber value="0.00" pattern="0.00"/>
                                </c:when>
                                <c:otherwise><fmt:formatNumber value="${line.baki}" pattern="#,###,##0.00"/></c:otherwise>
                            </c:choose>
                        </display:column>
                        <c:if test="${fn:length(actionBean.accList) gt '1'}">
                            <display:column title="Hapus" style="text-align:center"><s:radio name="id" value="${line.noAkaun}" id="chkbox${line_rowNum-1}" style="display:none;"/>
                                <img alt='Klik Untuk Hapus' src='${pageContext.request.contextPath}/images/not_ok.gif'
                                     onclick="chk('${line_rowNum-1}','${line.noAkaun}');" />
                            </display:column>
                        </c:if>
                        <display:footer>
                            <tr>
                                <td colspan="3" align="right"><div align="right"><b>Jumlah (RM) : </b></div></td>
                                <td class="number" align="right"><div align="right"><fmt:formatNumber  value="${actionBean.jumlahCaj}" pattern="#,##0.00"/></div></td>
                            </tr>
                        </display:footer>
                    </display:table>
                    <table border="0"><tr>
                                <td width="100">&nbsp;</td><td width="100">&nbsp;</td>
                                <td width="100"><s:submit name="deleteSelected" value="Hapus" id="del" class="btn" style="display:none;"/></td>
                            </tr></table>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <em><font size="1">**</font>&nbsp;<font size="1" color="black">Sila klik pada ID Hakmilik untuk melihat maklumat terperinci.</font></em>

                    </div>
                    <p>
                        <label >Jumlah Yang Perlu Dibayar :</label>&nbsp;RM
                    <fmt:formatNumber value="${actionBean.jumlahCaj}" currencySymbol="RM " type="currency" pattern="#,##0.00"/>
                    <s:text name="" id="xx" style="display:none"/>
                </p>
                <br>
            </fieldset>
        </div>
        <p></p>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Cara Bayaran</legend>
                &nbsp;&nbsp;&nbsp;&nbsp;<s:errors field="amaun"/>
                <div align="center">
                    <table>
                        <tr>
                            <td align="right">
                                <!--s:submit name="addCaraBayar" value="Tambah" class="btn"/-->
                                <s:button name=" " value="Isi Semula" class="btn" onclick="clearText1('hasil');"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
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

                                    <display:column title="Bank / Agensi Pembayaran" ><em id="b${row_rowNum - 1}">*</em>
                                        <s:select name="senaraiCaraBayaran[${row_rowNum - 1}].bank.kod" id="bank${row_rowNum - 1}" style="width:90%">
                                            <s:option label="Pilih..." value="0" />
                                            <s:options-collection collection="${listUtil.senaraiKodBank}"  label="nama" value="kod" />
                                        </s:select>
                                    </display:column>

                                    <display:column title="Cawangan" >
                                        <s:text name="senaraiCaraBayaran[${row_rowNum - 1}].bankCawangan" id="caw${row_rowNum - 1}" size="20" onkeyup="this.value=this.value.toUpperCase();"/>
                                    </display:column>

                                    <display:column title="No. Rujukan"  style="width:15%;text-align:center"><em id="a${row_rowNum - 1}">*</em>
                                        <s:text name="senaraiCaraBayaran[${row_rowNum - 1}].noRujukan" id="rujukan${row_rowNum - 1}" size="15" />
                                    </display:column>

                                    <display:column title="Tarikh" style="width:15%;text-align:center"><em id="tcek${row_rowNum - 1}">*</em>
                                        <s:text name="tarikhCek[${row_rowNum-1}]" id="trkh${row_rowNum - 1}" size="15" onchange="dateValidation(this.value,${row_rowNum -1})" readonly="true" maxlength="10" class="datepicker"/>
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
                        </td>
                        </tr>
                        <tr align="right" id="baki">
                            <td>
                                <s:checkbox name="bakiFlag" value="true" size="2" id="chxBox"/>
                                <em><font size="2" color="black"><b>Perlu pulangkan baki.</b></font></em>
                            </td>
                        </tr>
                    </table>
                </div>
                <br>
            </fieldset>
        </div>

        <div align="center"><table style="width:99.2%" bgcolor="green">
                <tr> 
                    <td align="right">
                        <s:submit name="Step1" value="Kembali" class="btn"/>&nbsp;
                        <s:submit name="Step3" value="Bayar" class="btn" id="byrS" onclick="return validate(this.form)"/>
                        <s:submit name="Step4" value="Bayar" class="btn" id="byrP" onclick="return validate(this.form)"/>
                    </td>
                </tr>
            </table></div>
        </s:form>
</div>
<script language="javascript" >
    $(document).ready(function() {
        // set the first default payment to Tunai
        $('#senaraiCaraBayaran0').val('T');
        $('#xx').val('');
        // focus on the first payment
        $('#amaun0').val((${actionBean.jumlahCaj}).toFixed(2));
        $('#jumCaraBayar').val((${actionBean.jumlahCaj}).toFixed(2));
        $("#bank0").hide();
        $("#caw0").hide();
        $("#rujukan0").hide();
        $("#trkh0").hide();
        $("#a0").hide();
        $("#b0").hide();
        $("#tcek0").hide();
        $('#baki').attr("disabled", "true");
        var bil = parseInt(${actionBean.bil});
        var baki = ${actionBean.jumlahCaj};
        var bilAkaun = ${fn:length(actionBean.accList)}
        if(bilAkaun==1){$('#byrP').hide();}
        else{$('#byrS').hide();}
            
        for (var i = 1; i < bil; i++){
            $("#a"+i).hide();
            $("#b"+i).hide();
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
</script>
<script type="text/javascript">
    function updateTotal (inputTxt,row){
        var total = 0;
        var bil = parseInt(${actionBean.bil});
    <%--for (var i = 0; i <bil; i++){--%>
            var a = document.getElementById('amaun' + row)
            if ((isNaN(a.value))||((a.value) =="")){
                alert("Nombor tidak sah");
                inputTxt.value = RemoveNonNumeric(a);
                $('#jumCaraBayar').val("0.00");
                return;
            }
            total += parseFloat(a.value);
    <%--}--%>
            var t = document.getElementById('jumCaraBayar');
            t.value = total.toFixed(2);
            inputTxt.value = parseFloat(inputTxt.value).toFixed(2);
            updtTot();
            var yy = row - 1;
            if(yy>=0){
                var t = document.getElementById('jumCaraBayar');
                var bal = (parseFloat(${actionBean.jumlahCaj}) - parseFloat(t.value)).toFixed(2);
                if(bal > 0)
                    $('#amaun' + (row+1)).val(bal);
                else{
                    $('#amaun' + (row+1)).val('0');
                    updtTot();
                }
            }else{
                var t = document.getElementById('jumCaraBayar');
                var bal = (parseFloat(${actionBean.jumlahCaj}) - parseFloat(t.value)).toFixed(2);
                if(bal > 0)
                    $('#amaun' + (row+1)).val(bal);
                else{
                    $('#amaun' + (row+1)).val('0');
                    updtTot();
                }
            }
            appearChxbox(row);
        }

        function doSubmits(f){
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
                    width: '50px'
                }
            });
            f.submit();
        }

        function appearChxbox(row){
            var nett = ${actionBean.jumlahCaj};
            var pay = document.getElementById("jumCaraBayar");

            var bil = parseInt(${actionBean.bil});
            for (var i = 0; i < bil; i++){
                var a = document.getElementById('senaraiCaraBayaran'+i);
                if(((a.value != '0')&&(a.value == 'T'))&&(pay.value > nett)){
                    $('#baki').removeAttr("disabled");
                }
                if(nett >= pay.value){
                    $('#baki').attr("disabled", "true");
                }
            }
        }
        function updtTot(){
            var total = 0;
            var m = ${actionBean.bil};
            for (var i=0; i<m; i++){
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

        function edit(id){
            window.open("${pageContext.request.contextPath}/hasil/kutipan_hasil?showEditPemohon&idHakmilik="+id, "eTanah",
            "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=900,height=335");
        }

        function remove(id){
            alert("Hapus ID Hakmilik : "+id+"?");
            var url = "${pageContext.request.contextPath}/hasil/kutipan_hasil?delete&idHakmilik="+id;
            $.get(url,
            function(data){
                $('#ak').html(data);
            },'html');
        }

        function chk(row,id){
            if(confirm("Hapus ID Hakmilik "+id+" ?")){
                $("#chkbox"+row).click();
                $('#del').click();
            }
        }

        function autoBalance(row, ax, curr){
            if(row != 0){
                if(ax.value != '0'){
                    var t = document.getElementById('jumCaraBayar');
                    var bal = (parseFloat(t.value) + parseFloat(curr.value)).toFixed(2);
                    $("#jumCaraBayar").val(bal);
                }
            }
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
                        $('#b'+i).hide();
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
                        $('#b'+i).show();
                        $('#tcek'+i).show();
                        if(a.value == '0'){
                            $('#a'+i).hide();
                            $('#b'+i).hide();
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
                        $('#b'+i).show();
                        $('#tcek'+i).show();
                        if(a.value == '0'){
                            $('#a'+i).hide();
                            $('#b'+i).hide();
                            $('#tcek'+i).hide();
                        }
                    }
                }else{
                    $("#a"+i).hide();
                    $("#b"+i).hide();
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
            autoBalance(row, ax, curr);
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
        function validate(f){
            var t = parseFloat($('#jumCaraBayar').val());
            var u = parseFloat(${actionBean.jumlahCaj});
            var bil = parseInt(${actionBean.bil});
            var kodNegeri = document.getElementById('kodNegeri');
            var kodCawangan = document.getElementById('kodCaw');
            var kodCawHm = (document.getElementById('idHm0')).value;
            var kod = kodCawHm.substr(2, 2);
            if(u > t){
                var bal = u -t;
                if(kodNegeri.value == 'melaka'){
                    alert("Bayaran anda kurang RM "+(bal).toFixed(2)+". Sila klik butang hapus untuk menghapuskan ID Hakmilik yang tidak terlibat.");
                    return false;
                }else{
                    if(confirm("Bayaran anda kurang RM "+(bal).toFixed(2)+". Sila klik butang Hapus untuk menghapuskan ID Hakmilik yang tidak terlibat.")){
                        if(validateMandatoryField(bil)){
                            if(kodCawangan.value != kod){
                                alert("Bayaran Kurang tidak dibenarkan bagi Hakmilik daerah lain.");
                                return false;
                            }else{
                                doSubmits(f);
                                return true;}
                        }else{
                            return false;}
                    }else
                        return false;
                }
            }if(t > u){
                if(kodCawangan.value != kod){
                    alert("Bayaran Lebih tidak dibenarkan bagi Hakmilik daerah lain.");
                    return false;
                }}
            if(validateMandatoryField(bil)){
                doSubmits(f);
                return true;
            }else{
                return false;}
        }

        function validateByr(f){
            var t = parseFloat($('#jumCaraBayar').val());
            var u = parseFloat(${actionBean.jumlahCaj});
            var bil = parseInt(${actionBean.bil});
            var l = parseInt(${fn:length(actionBean.accList)});
            var m = l-1;
        
            var kodCaw = (document.getElementById('kodCaw')).value;
            var kodCawHm = (document.getElementById('idHm'+m)).value;
            var kod = kodCawHm.substr(2, 2);

            if(u > t){
                var bal = u -t;
                if(confirm("Bayaran anda kurang RM "+(bal).toFixed(2)+". Sila klik butang Hapus untuk menghapuskan ID Hakmilik yang tidak terlibat.")){
                    if(validateMandatoryField(bil)){
                        if(kodCaw != kod){
                            alert("Bayaran Kurang tidak dibenarkan bagi Hakmilik daerah lain.");
                            return false;
                        }else{
                            doSubmits(f);
                            return true;}
                    }else{
                        return false;}
                }else
                    return false;
            }
            if(t > u){
                var bal = t - u;
                var x = document.getElementById('xx');
                var m = document.getElementById('chxBox');
                if(m.checked){
                    doSubmits(f);
                    return true;
                }
                if((x.value == '')&&(!m.checked)){
                    window.open("${pageContext.request.contextPath}/hasil/kutipan_hasil?chooseIDHakmilik&balance="+bal, "eTanah",
                    "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=900,height=600");
                    return false;
                }
            }
            if(validateMandatoryField(bil)){
                doSubmits(f);
                return true;
            }else{
                return false;}
        }

        function validateMandatoryField(bil){
            for (var i = 0; i < bil; i++){
                var a = document.getElementById('senaraiCaraBayaran'+i);
                var c = $('#rujukan'+i).val();
                var d = $('#trkh'+i).val();
                var bank = $('#bank'+i).val();
                if((a.value != '0')&&(a.value != 'T')){
                    if(c == ""){
                        alert("Sila Masukkan Nombor Rujukan.");
                        $('#rujukan'+i).focus();
                        return false;
                    }
                    if(d == ""){
                        alert("Sila Masukkan Tarikh.");
                        $('#trkh'+i).focus();
                        return false;
                    }
                    if(bank == "0"){
                        alert("Sila Masukkan Bank / Agensi Pembayaran.");
                        $('#bank'+i).focus();
                        return false;
                    }
                }
            }return true;
        }

        function addRows(akaun){
            $("#xx").val(akaun);
            document.getElementById("byr1").click();
        }

        function reset1(){
            $('#senaraiCaraBayaran0').val('T');
            $('#amaun0').val((${actionBean.jumlahCaj}).toFixed(2));
            $('#jumCaraBayar').val((${actionBean.jumlahCaj}).toFixed(2));
            $("#bank0").hide();
            $("#caw0").hide();
            $("#rujukan0").hide();
            $("#trkh0").hide();
            $("#a0").hide();
            $("#b0").hide();
            $("#tcek0").hide();
            var bil = parseInt(${actionBean.bil});
            for (var i = 1; i < bil; i++){
                $('#bank'+i).show();
                $('#caw'+i).show();
                $('#rujukan'+i).show();
                $('#trkh'+i).show();
                $("#a"+i).hide();
                $("#b"+i).hide();
                $("#tcek"+i).hide();
                $('#bank'+i).attr("disabled", "true");
                $('#caw'+i).attr("disabled", "true");
                $('#rujukan'+i).attr("disabled", "true");
                $('#trkh'+i).attr("disabled", "true");
                $('#amaun'+i).attr("disabled", "true");
                $('#amaun'+i).val("0");
            }
            $('#baki').attr("disabled", "true");
        }

        function clearText1(id) {
            $("#"+id+" input:text").each( function(el) {
                $(this).val('');
            });

            $("#" + id +" select").each( function(el) {
                $(this).val('0');
            });
            reset1();
        }
</script>