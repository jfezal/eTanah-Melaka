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
<style type="text/css">
    body { font-family: sans-serif; }
    #confirmBox
    {
        display: none;
        background-color: #eee;
        border-radius: 5px;
        border: 3px solid red;
        position: absolute;
        width: 500px;
        left: 20%;
        margin-left: 100px;
        margin-top: 30px;
        padding: 10px 8px 8px;
        box-sizing: border-box;
        text-align: left;
    }
    #button {
        background-color: #aaa;
        display: inline-block;
        border-radius: 10px;
        padding: 2px;
        padding: 10px 8px 8px;
        text-align: center;
        width: 80px;
        cursor: pointer;
        border: 3px solid #aaa;
        position: center;
    }
    #button:hover
    {
        text-decoration-style: bold;
        background-color: #ddd;
        border: 3px solid #ddd;
    }
    #message
    {
        text-decoration-color: red;
        text-align: center;
        margin-bottom: 8px;
    }
</style>
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
    <s:form beanclass="etanah.view.stripes.hasil.KutipanHasilActionBean" id="hasil">
        <div id="confirmBox">
            <table border="0">
                <tr>
                    <td><img src='${pageContext.request.contextPath}/images/url.png'/></td>
                    <td><div class="message">Terdapat Lebihan Bayaran. Perlu Pulangkan baki?</div><br><br></td>
                </tr>
            </table>        
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <span class="yes" id="button">Ya</span>
            <span class="no" id="button">Tidak</span>
            <span class="cancel" id="button">Batal</span>
        </div>

        <s:hidden name="${actionBean.list}" />
        <s:hidden name="${actionBean.hakmilikList}" />
        <%--<s:hidden name="${actionBean.account}" />--%>
        <s:hidden name="hakmilik.idHakmilik" />
        <s:hidden name="${actionBean.hList}" />
        <s:hidden name="${actionBean.pguna.kodCawangan.kod}" value="${actionBean.pguna.kodCawangan.kod}" id="kodCaw"/>
        <s:text name="notis6a" style="display:none;" id="notis6a"/>
        <s:text name="validateCek" id="cek" value="${actionBean.validateCek}" style="display:none;"/>
        <s:text name="validatePos" id="pos" value="${actionBean.validatePos}" style="display:none;"/>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Bayaran</legend>
                <div class="content" align="center">
                    <s:text name="kodNegeri" value="${actionBean.kodNegeri}" id="kodNegeri" style="display:none;"/>
                    <%--<display:table class="tablecloth" name="${actionBean.list}" cellpadding="0" cellspacing="0" pagesize="10" requestURI="/hasil/kutipan_hasil" id="line">--%>
                    <display:table class="tablecloth" name="${actionBean.list}" requestURI="/hasil/kutipan_hasil" id="line">
                        <display:column title="Bil.">
                            <div align="center">
                                ${line_rowNum}.
                                <s:hidden name="x" class="x${line_rowNum-1}" value="${line.hakmilik.idHakmilik}" id="idHm${line_rowNum-1}"/>
                            </div>
                        </display:column>
                        <display:column  title="ID Hakmilik" >
                            <a href="#" onclick="edit('${line.hakmilik.idHakmilik}');
                                    return false;">${line.hakmilik.idHakmilik}</a>
                        </display:column>
                        <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                            <display:column property="noAkaun" title="Nombor Akaun" class="akaun"/>
                        </c:if>
                        <display:column title="Baki (RM)" style="text-align:right;">
                            <fmt:formatNumber value="${line.baki}" pattern="#,###,##0.00"/>
                            <s:hidden name="y" class="y${line_rowNum-1}" value="${line.baki}" id="bal${line_rowNum-1}"/>
                        </display:column>
                        <display:column title="Amaun (RM)" style="text-align:right;">
                            <c:choose>
                                <c:when test="${line.baki < 0}">
                                    <fmt:formatNumber value="0.00" pattern="0.00"/>
                                </c:when>
                                <c:otherwise><fmt:formatNumber value="${line.baki}" pattern="#,###,##0.00"/></c:otherwise>
                            </c:choose>
                        </display:column>
                        <c:if test="${actionBean.del}">
                            <display:column title="Hapus" style="text-align:center"><s:checkbox name="chkbox" value="${line.hakmilik.idHakmilik}" id="chkbox${line_rowNum-1}" style="display:none;"/>
                                <%--<a href="#">--%>
                                <img alt='Klik Untuk Hapus' src='${pageContext.request.contextPath}/images/not_ok.gif'
                                     onclick="chk('${line_rowNum-1}', '${line.hakmilik.idHakmilik}');" />
                                <%--</a>--%>
                            </display:column>
                        </c:if>
                        <display:footer>
                            <tr>
                                <c:choose>
                                    <c:when test="${actionBean.kodNegeri eq 'melaka'}">
                                        <td colspan="4" align="right"><div align="right"><b>Jumlah (RM) : </b></div></td>
                                    </c:when>
                                    <c:otherwise>
                                        <td colspan="3" align="right"><div align="right"><b>Jumlah (RM) : </b></div></td>
                                    </c:otherwise>
                                </c:choose>
                                <td class="number" align="right"><div align="right"><fmt:formatNumber  value="${actionBean.jumlahCaj}" pattern="#,##0.00"/></div></td>
                            </tr>
                        </display:footer>
                    </display:table>
                    <c:if test="${actionBean.del}"><table border="0"><tr>
                                <td width="100">&nbsp;</td><td width="100">&nbsp;</td>
                                <c:if test="${actionBean.kodNegeri eq 'melaka'}"><td width="100">&nbsp;</td></c:if>
                                <td width="100"><s:submit name="deleteSelected" value="Hapus" id="del" class="btn" style="display:none;"/></td>
                            </tr></table></c:if>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <em><font size="1">**</font>&nbsp;<font size="1" color="black">Sila klik pada ID Hakmilik untuk melihat maklumat terperinci.</font></em>

                    </div>
                    <p id="diBayar">
                        <label >Jumlah Yang Perlu Dibayar :</label>&nbsp;RM
                    <fmt:formatNumber value="${actionBean.jumlahCaj}" currencySymbol="RM " type="currency" pattern="#,##0.00"/>
                    <s:text name="amn" id="xx" style="display:none"/>
                </p>
                <br>
            </fieldset>
        </div>
        <p></p>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Cara Bayaran</legend>
                <%--<p class=instr><em><font color="black">Masukkan butir-butir pembayaran.<br>&nbsp;
                            <font color="red">PERINGATAN:</font> Tidak dibenarkan menggunakan cara pembayaran yang lain bersama dengan pembayaran menggunakan Cek ,
                            Deraf Bank dan Wang dalam Pindahan.</font></em>
                </p>--%>
                &nbsp;&nbsp;&nbsp;&nbsp;<s:errors field="amaun"/>
                <div align="center">
                    <table>
                        <tr>
                            <td align="right">
                                <s:submit name="addCaraBayar" value="Tambah" class="btn"/>
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
                                        <%--<s:select name="senaraiCaraBayaran[${row_rowNum - 1}].kodCaraBayaran.kod"
                                                  id="senaraiCaraBayaran${row_rowNum - 1}" onchange="javaScript:change(${row_rowNum -1})">
                                            <s:option value="0" label="Pilih ..." />
                                            <s:options-collection collection="${listUtil.senaraiKodCaraBayaran}"  label="nama" value="kod" sort="nama"/>
                                        </s:select>--%>
                                    </display:column>

                                    <display:column title="Bank / Agensi Pembayaran" ><em id="b${row_rowNum - 1}">*</em>
                                        <s:select name="senaraiCaraBayaran[${row_rowNum - 1}].bank.kod" id="bank${row_rowNum - 1}" style="width:90%">
                                            <s:option label="Pilih..." value="0" />
                                            <s:options-collection collection="${listUtil.senaraiKodBank}"  label="nama" value="kod" />
                                        </s:select>
                                    </display:column>

                                    <display:column title="Cawangan" >
                                        <em id="tCawangan${row_rowNum - 1}">*</em><s:text name="senaraiCaraBayaran[${row_rowNum - 1}].bankCawangan" id="caw${row_rowNum - 1}" size="20" onkeyup="this.value=this.value.toUpperCase();"/>
                                    </display:column>

                                    <display:column title="No. Rujukan"  style="width:15%;text-align:center"><em id="a${row_rowNum - 1}">*</em>
                                        <s:text name="senaraiCaraBayaran[${row_rowNum - 1}].noRujukan" id="rujukan${row_rowNum - 1}" size="15" />
                                    </display:column>

                                    <display:column title="No. Akaun Pembayar"  style="width:15%;text-align:center"><em id="v${row_rowNum - 1}">*</em>
                                        <s:text name="senaraiCaraBayaran[${row_rowNum - 1}].noAkaunCek" id="akaunpembayar${row_rowNum - 1}" size="15" class="number" onblur="javascript:numberValidation(this,${row_rowNum - 1});"/>
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
                        <tr align="right" id="baki" style="display:none;">
                            <td>
                                <s:checkbox name="bakiFlag" value="true" size="2" id="chxBox"/>
                                <em><font size="2" color="black" id="bb"><b>Perlu pulangkan baki.</b></font></em>
                            </td>
                        </tr>
                    </table>
                    <%--<s:submit name="addCaraBayar" value="Tambah" class="btn"/>--%>
                </div>
                <br>
            </fieldset>
        </div>
        <s:text name="" id="checking" style="display:none;"/>
        <div align="center"><table style="width:99.2%" bgcolor="green">
                <tr>
                    <td align="right">
                        <s:submit name="back" value="Kembali" class="btn"/>&nbsp;
                        <s:submit name="main" value="Batal" class="btn" style="display:${actionBean.flag}"/>&nbsp;
                        <s:button name="" value="Bayar" class="btn" disabled="${actionBean.visible}" id="byr" onclick="return validate(this.form)"/>
                        <s:button name="" value="Bayar" class="btn" disabled="${actionBean.visible}" id="byr1" onclick="return validateByr(this.form)"/>
                        <s:submit name="save" value="Bayar" class="btn" style="display:none;" id="proceed" onclick="tryTestTengok(this.form)"/>
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
        $("#akaunpembayar0").hide();
        $("#a0").hide();
        $("#b0").hide();
        $("#tcek0").hide();
        $('#tCawangan0').hide();
        $('#baki').attr("disabled", "true");
        var bil = parseInt(${actionBean.bil});
        var baki = ${actionBean.jumlahCaj};

        for (var i = 1; i < bil; i++) {
            $("#a" + i).hide();
            $("#b" + i).hide();
            $("#tcek" + i).hide();
            $('#tCawangan0').hide();
            $('#bank' + i).attr("disabled", "true");
            $('#caw' + i).attr("disabled", "true");
            $('#rujukan' + i).attr("disabled", "true");
            $('#akaunpembayar' + i).attr("disabled", "true");
            $('#trkh' + i).attr("disabled", "true");
            $('#amaun' + i).attr("disabled", "true");
            $('#amaun' + i).val("0");
        }
        if (${actionBean.button} == true) {
            $("#byr").hide();
            $('#baki').removeAttr("style");
        }
        if (${actionBean.button} == false) {
            $("#byr1").hide();
        }
        blinkFont();

        function stopRKey(evt) {
            var evt = (evt) ? evt : ((event) ? event : null);
            var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null);
            if ((evt.keyCode == 13) && (node.type == "text")) {
                return false;
            }
        }

        document.onkeypress = stopRKey;
        var confirmBox = $("#confirmBox");
        confirmBox.hide();
    });
</script>
<script type="text/javascript">
    function updateTotal(inputTxt, row) {
        var total = 0;
        var bil = parseInt(${actionBean.bil});
    <%--for (var i = 0; i <bil; i++){--%>
        var a = document.getElementById('amaun' + row)
        if ((isNaN(a.value)) || ((a.value) == "")) {
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
        if (yy >= 0) {
            var t = document.getElementById('jumCaraBayar');
            var bal = (parseFloat(${actionBean.jumlahCaj}) - parseFloat(t.value)).toFixed(2);
            if (bal > 0)
                $('#amaun' + (row + 1)).val(bal);
            else {
                $('#amaun' + (row + 1)).val('0');
                updtTot();
            }
        } else {
            var t = document.getElementById('jumCaraBayar');
            var bal = (parseFloat(${actionBean.jumlahCaj}) - parseFloat(t.value)).toFixed(2);
            if (bal > 0)
                $('#amaun' + (row + 1)).val(bal);
            else {
                $('#amaun' + (row + 1)).val('0');
                updtTot();
            }
        }
        appearChxbox(row);
    }

    function tryTestTengok(f) {
        doSubmits(f);
    }

    function doSubmits(f) {
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top: ($(window).height() - 50) / 2 + 'px',
                left: ($(window).width() - 50) / 2 + 'px',
                width: '50px'
            }
        });
        f.submit();
    }

    function doConfirm(msg, yesFn, noFn, canFn) {
        var confirmBox = $("#confirmBox");
        confirmBox.find(".message").text(msg);
        confirmBox.find(".yes,.no,.cancel").unbind().click(function() {
            confirmBox.hide();
        });
        confirmBox.find(".yes").click(yesFn);
        confirmBox.find(".no").click(noFn);
        confirmBox.find(".cancel").click(canFn);
        confirmBox.show();
    }

    function baki(bal) {
//        $("#byr").click(function(e) {
//            e.preventDefault();
        var form = this;
        var msg = "Terdapat Lebihan Bayaran sebanyak RM " + bal.toFixed(2) + ". Perlu Pulangkan baki?";
        doConfirm(msg, function yes() {
            $("#chxBox").click();
            $("#proceed").click();
            doSubmits(form);
        }, function no() {
            $("#proceed").click();
            doSubmits(form);
        }, function cancel() {
            return false;
        });
//        });
    }

    function appearChxbox(row) {
        var nett = ${actionBean.jumlahCaj};
        var pay = document.getElementById("jumCaraBayar");
        var bal = (parseFloat(pay.value) - parseFloat(${actionBean.jumlahCaj})).toFixed(2);

        var bil = parseInt(${actionBean.bil});
        for (var i = 0; i < bil; i++) {
            var a = document.getElementById('senaraiCaraBayaran' + i);
            if (((a.value != '0') && (a.value == 'T')) && (pay.value > nett)) {
                alert("Terdapat lebihan bayaran sebanyak RM " + bal + ".");
                blinkFont1();
                $("#checking").val("Y");
                $('#baki').removeAttr("disabled");
            }
            if (nett >= pay.value) {
                $("#checking").val("");
                $('#baki').attr("disabled", "true");
            }
        }
    }
    function updtTot() {
        var total = 0;
        var m = ${actionBean.bil};
        for (var i = 0; i < m; i++) {
            var a = document.getElementById('amaun' + i)
            total += parseFloat(a.value);
        }
        var t = document.getElementById('jumCaraBayar');
        t.value = total.toFixed(2);
    }

    function RemoveNonNumeric(strString) {
        var strValidCharacters = "1234567890.";
        var strReturn = "0.00";
        var strBuffer = "0";
        var intIndex = 0.00;
        // Loop through the string
        for (intIndex = 0; intIndex < strString.length; intIndex++)
        {
            strBuffer = strString.substr(intIndex, 1);
            // Is this a number
            if (strValidCharacters.indexOf(strBuffer) > -1)
            {
                strReturn += strBuffer;
            }
        }
        return strReturn;
    }

    function edit(id) {
        window.open("${pageContext.request.contextPath}/hasil/kutipan_hasil?showEditPemohon&idHakmilik=" + id, "eTanah",
                "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=900,height=335");
    }

    function remove(id) {
        alert("Hapus ID Hakmilik : " + id + "?");
        var url = "${pageContext.request.contextPath}/hasil/kutipan_hasil?delete&idHakmilik=" + id;
        $.get(url,
                function(data) {
                    $('#ak').html(data);
                }, 'html');
    }

    function chk(row, id) {
        if (confirm("Hapus ID Hakmilik " + id + " ?")) {
            $("#chkbox" + row).click();
            $('#del').click();
        }
    }

    function autoBalance(row, ax, curr) {
        if (row != 0) {
            if (ax.value != '0') {
                var t = document.getElementById('jumCaraBayar');
                var bal = (parseFloat(t.value) + parseFloat(curr.value)).toFixed(2);
                $("#jumCaraBayar").val(bal);
            }
        }
    }

    function dateValidation(value, row) {
        var vsplit = value.split('/');
        var fulldate = vsplit[1] + '/' + vsplit[0] + '/' + vsplit[2];
        var cb = document.getElementById('senaraiCaraBayaran' + row);
        var today = new Date();
        var sdate = new Date(fulldate);
        if (sdate > today) {
            alert("Tarikh yang dimasukkan tidak sesuai.");
            $('#trkh' + row).val("");
        }
        chequeValidation(cb.value, sdate, today, row)
    }

    function numberValidation(inputTxt, row) {
        var a = document.getElementById('akaunpembayar' + row)
        if ((isNaN(a.value)) || ((a.value) == "")) {
            alert("Nombor tidak sah");
            inputTxt.value = '';
            return;
        }

    }

    function chequeValidation(caraBayar, inputDate, today, row) {
        var dCek = document.getElementById("cek");
        var dPos = document.getElementById("pos");
        var cDate;
    <%--||(caraBayar=='CT')--%>
        if ((caraBayar == 'CB') || (caraBayar == 'CL') || (caraBayar == 'CS')) {
            var vsplit = dCek.value.split('/');
            var fulldate = vsplit[1] + '/' + vsplit[0] + '/' + vsplit[2];

    <%--cDate = new Date(fulldate);
    if(inputDate<cDate){
        alert('Tarikh Cek yang dimasukkan melebihi tempoh 6 Bulan. Cek tidak sah.');
        $('#trkh'+row).val("");
    }--%>
        }
    <%--if((caraBayar=='KW')||(caraBayar=='WP')){
         var vsplit = dPos.value.split('/');
         var fulldate = vsplit[1]+'/'+vsplit[0]+'/'+vsplit[2];
             
//            cDate = new Date(fulldate);
//            if(inputDate<cDate){
//                alert('Tarikh yang dimasukkan melebihi tempoh 3 Bulan.');
//                $('#trkh'+row).val("");
//            }
    }        --%>

    }

    function change(row) {
        var curr = document.getElementById('amaun' + row);
        var bil = parseInt(${actionBean.bil});
        var ax = document.getElementById('senaraiCaraBayaran' + row);
        $("#trkh" + row).val("");
        for (var i = 0; i < (row + 1); i++) {
            var a = document.getElementById('senaraiCaraBayaran' + i);
            if (a.value != '0') {
                $('#bank' + i).removeAttr("disabled");
                $('#caw' + i).removeAttr("disabled");
                $('#rujukan' + i).removeAttr("disabled");
                $('#trkh' + i).removeAttr("disabled");
                $('#amaun' + i).removeAttr("disabled");
                $('#akaunpembayar' + i).removeAttr("disabled");
    <%--$("#trkh"+i).val("");--%>
                if (a.value == 'T') {
                    $('#a' + i).hide();
                    $('#b' + i).hide();
                    $('#tcek' + i).hide();
                    $('#tCawangan' + i).hide();
                    $('#bank' + i).hide();
                    $("#caw" + i).hide();
                    $("#rujukan" + i).hide();
                    $("#akaunpembayar" + i).hide();
                    $("#trkh" + i).hide();
                    $('#a' + i).val("");
                    $('#bank' + i).val('0');
                    $("#caw" + i).val("");
                    $("#rujukan" + i).val("");
                    var today = new Date();
                    $("#trkh" + i).val(today.getDate() + "/" + (today.getMonth() + 1) + "/" + today.getYear());
                }
                else if ((a.value == 'KW') || (a.value == 'WP')) {
                    $('#bank' + i).val("PMB");
                    $('#bank' + i).attr("disabled", "true");
                    $('#bank' + i).show();
                    $('#caw' + i).show();
                    $('#rujukan' + i).show();
                    $('#akaunpembayar' + i).hide();
                    $('#trkh' + i).show();
                    $('#a' + i).show();
                    $('#b' + i).show();
                    $('#v' + i).hide();
                    $('#tcek' + i).show();
                    $('#tCawangan' + i).show();
                    $('#checking').val("N");
                    if (a.value == '0') {
                        $('#a' + i).hide();
                        $('#b' + i).hide();
                        $('#v' + i).hide();
                        $('#tcek' + i).hide();
                    }
                }
                else if ((a.value == 'C') || (a.value == 'CT') || (a.value == 'CL') || (a.value == 'CT') || (a.value == 'CS')) {
                    $('#bank' + i).removeAttr("disabled");
                    $('#bank' + i).show();
                    $('#caw' + i).show();
                    $('#rujukan' + i).show();
                    $('#akaunpembayar' + i).show();
                    $('#trkh' + i).show();
                    $('#a' + i).show();
                    $('#b' + i).show();
                    $('#v' + i).show();
                    $('#tcek' + i).show();
                    $('#tCawangan' + i).show();
                    $('#checking').val("N");
                    if (a.value == '0') {
                        $('#a' + i).hide();
                        $('#b' + i).hide();
                        $('#v' + i).hide();
                        $('#tcek' + i).hide();
                    }
                }
                else {
                    $('#bank' + i).removeAttr("disabled");
                    $('#bank' + i).show();
                    $('#caw' + i).show();
                    $('#rujukan' + i).show();
                    $('#akaunpembayar' + i).hide();
                    $('#trkh' + i).show();
                    $('#a' + i).show();
                    $('#b' + i).show();
                    $("#v" + i).hide();
                    $('#tcek' + i).show();
                    $('#tCawangan' + i).show();
                    $('#checking').val("N");
                    if (a.value == '0') {
                        $('#a' + i).hide();
                        $('#b' + i).hide();
                        $("#v" + i).hide();
                        $('#tcek' + i).hide();
                    }
                }
            } else {
                $("#a" + i).hide();
                $("#b" + i).hide();
                $("#v" + i).hide();
                $('#tcek' + i).hide();
                $('#tCawangan' + i).hide();
                $('#bank' + i).attr("disabled", "true");
                $('#caw' + i).attr("disabled", "true");
                $('#rujukan' + i).attr("disabled", "true");
                $('#akaunpembayar' + i).attr("disabled", "true");
                $('#trkh' + i).attr("disabled", "true");
                $('#amaun' + i).attr("disabled", "true");
                $('#amaun' + i).val("0");
                $('#bank' + i).val("0");
                $('#caw' + i).val("");
                $('#rujukan' + i).val("");
                $('#trkh' + i).val("");
                $('#checking').val("N");
                updateTotal();
            }

    <%--var b = document.getElementById('senaraiCaraBayaran'+(i+1));
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
    }--%>
            for (var j = i + 1; j < bil; j++) {
                var c = document.getElementById('senaraiCaraBayaran' + j);
                if (c.value != '0') {
                    if ((a.value == 'T') && (c.value == 'T')) {
                        alert("Hanya satu mod Bayaran Tunai sahaja dibenarkan.");
                        return c.value = '0';
                    }
                }
            }
            sequencePayment(row);
        }
        autoBalance(row, ax, curr);
    }
    function sequencePayment(row) {
        if ((row - 1) > 0) {
            var x = document.getElementById('senaraiCaraBayaran' + (row - 1));
            var y = document.getElementById('senaraiCaraBayaran' + (row));
            if ((x.value == '0') && (y.value != '0')) {
                alert("Sila masukkan Mod Bayaran mengikut turutan.");
                return y.value = '0';
            }
        }
    }
    function validate(f) {
        var t = parseFloat($('#jumCaraBayar').val());
        var u = parseFloat(${actionBean.jumlahCaj});
        var bil = parseInt(${actionBean.bil});
        var kodNegeri = document.getElementById('kodNegeri');
        var kodCawangan = document.getElementById('kodCaw');
        var kodCawHm = (document.getElementById('idHm0')).value;
        var notis = (document.getElementById('notis6a')).value;
        var val = (document.getElementById('checking')).value;

        var kod = kodCawHm.substr(2, 2);
        if (u > t) {
            var bal = u - t;
            if (kodNegeri.value == 'melaka') {
                if (confirm("Bayaran anda kurang RM " + (bal).toFixed(2) + ". Adakah anda ingin meneruskan transaksi?.")) {
                    if (kodCawangan.value != kod) {
                        alert("Bayaran Kurang tidak dibenarkan bagi Hakmilik daerah lain.");
                        return false;
                    } else {
//                            doSubmits(f);
                        $("#proceed").click();
                        return true;
                    }
                } else {
                    return false;
                }
//                alert("Bayaran anda kurang RM "+(bal).toFixed(2)+". Sila klik butang hapus untuk menghapuskan ID Hakmilik yang tidak terlibat.");
//                return false;
            } else {
                if (confirm("Bayaran anda kurang RM " + (bal).toFixed(2) + ". Sila klik butang Hapus untuk menghapuskan ID Hakmilik yang tidak terlibat.")) {
                    if (validateMandatoryField(bil)) {
                        if (kodCawangan.value != kod) {
                            alert("Bayaran Kurang tidak dibenarkan bagi Hakmilik daerah lain.");
                            return false;
                        }
                        else if (notis == 'true') {
                            alert("Hakmilik ini telah dikenakan Notis 6A. Bayaran Kurang tidak dibenarkan.");
                            return false;
                        }
                        else {
//                            doSubmits(f); 
                            $("#proceed").click();
                            return true;
                        }
                    } else {
                        return false;
                    }
                } else
                    return false;
            }
        }
        if (t > u) {
            if (kodCawangan.value != kod) {
                alert("Bayaran Lebih tidak dibenarkan bagi Hakmilik daerah lain.");
                return false;
            }
        }
        if (validateMandatoryField(bil)) {
            if (t > u) {
                if (val == 'Y') {
                    baki(t - u);
                }
                else {
                    $("#proceed").click();
//                    doSubmits(f);
                    return true;
                }
            } else {
                $("#proceed").click();
                return true;
            }
        } else {
            return false;
        }
    }

    function validateByr(f) {
        var t = parseFloat($('#jumCaraBayar').val());
        var u = parseFloat(${actionBean.jumlahCaj});
        var bil = parseInt(${actionBean.bil});
        var l = parseInt(${fn:length(actionBean.list)});
        var m = l - 1;

        var kodCaw = (document.getElementById('kodCaw')).value;
        var kodCawHm = (document.getElementById('idHm' + m)).value;
        var kod = kodCawHm.substr(2, 2);

        if (u > t) {
            var bal = u - t;
            if (confirm("Bayaran anda kurang RM " + (bal).toFixed(2) + ". Sila klik butang Hapus untuk menghapuskan ID Hakmilik yang tidak terlibat.")) {
                if (validateMandatoryField(bil)) {
                    if (kodCaw != kod) {
                        alert("Bayaran Kurang tidak dibenarkan bagi Hakmilik daerah lain.");
                        return false;
                    } else {
//                    doSubmits(f);
                        $("#proceed").click();
                        return true;
                    }
                } else {
                    return false;
                }
            } else
                return false;
        }
        if (t > u) {
            var bal = t - u;
            var x = document.getElementById('xx');
    <%--for (var i = 0; i < bil; i++){
        var crByr = document.getElementById('senaraiCaraBayaran'+i);
        if(crByr.value == 'T'){
            return true;
        }
    }--%>
            var m = document.getElementById('chxBox');
            if (m.checked) {
//                doSubmits(f);
                $("#proceed").click();
                return true;
            }
            if ((x.value == '') && (!m.checked)) {
                window.open("${pageContext.request.contextPath}/hasil/kutipan_hasil?chooseIDHakmilik&balance=" + bal, "eTanah",
                        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=900,height=600");
                return false;
            }
        }
        if (validateMandatoryField(bil)) {
            $("#proceed").click();
            return true;
        } else {
            return false;
        }
    }

    function validateMandatoryField(bil) {
        for (var i = 0; i < bil; i++) {
            var a = document.getElementById('senaraiCaraBayaran' + i);
            var c = $('#rujukan' + i).val();
            var d = $('#trkh' + i).val();
            var bank = $('#bank' + i).val();
            var caw = $('#caw' + i).val();
            var akaunpembayar = $('#akaunpembayar' + i).val();

            if ((a.value != '0') && (a.value != 'T')) {
                if (caw == "" && ((a.value != 'KK') && (a.value != 'DK') && (a.value != 'WP') && (a.value != 'KW') && (a.value != 'AM'))) {
                    alert("Sila Masukkan Cawangan.");
                    $('#caw' + i).focus();
                    return false;
                }
                if (c == "") {
                    alert("Sila Masukkan Nombor Rujukan.");
                    $('#rujukan' + i).focus();
                    return false;
                }
                if (d == "") {
                    alert("Sila Masukkan Tarikh.");
                    $('#trkh' + i).focus();
                    return false;
                }
                if (a.value != 'KW') {
                    if (bank == "0") {
                        alert("Sila Masukkan Bank / Agensi Pembayaran.");
                        $('#bank' + i).focus();
                        return false;
                    }
                }
                if (a.value == 'C' || a.value == 'CT' || a.value == 'CS' || a.value == 'CL') {
                    if (akaunpembayar == "") {
                        alert("Sila Masukkan No Akaun Pembayar.");
                        $('#akaunpembayar' + i).focus();
                        return false;
                    }
                }
            }
        }
        return true;
    }

    function addRows(akaun) {
        $("#xx").val(akaun);
        document.getElementById("byr1").click();
    }

    function reset1() {
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
        for (var i = 1; i < bil; i++) {
            $('#bank' + i).show();
            $('#caw' + i).show();
            $('#rujukan' + i).show();
            $('#trkh' + i).show();
            $("#a" + i).hide();
            $("#b" + i).hide();
            $("#tcek" + i).hide();
            $('#bank' + i).attr("disabled", "true");
            $('#caw' + i).attr("disabled", "true");
            $('#rujukan' + i).attr("disabled", "true");
            $('#trkh' + i).attr("disabled", "true");
            $('#amaun' + i).attr("disabled", "true");
            $('#amaun' + i).val("0");
        }
        $('#baki').attr("disabled", "true");
    }

    function clearText1(id) {
        $("#" + id + " input:text").each(function(el) {
            $(this).val('');
        });

        $("#" + id + " select").each(function(el) {
            $(this).val('0');
        });
        reset1();
    }

    function blinkFont1() {
        document.getElementById("bb").style.color = "red";
        setTimeout("setblinkFont1()", 300);
    }

    function setblinkFont1() {
        document.getElementById("bb").style.color = "orange";
        setTimeout("blinkFont1()", 300);
    }

    function blinkFont() {
        document.getElementById("diBayar").style.color = "red"
        setTimeout("setblinkFont()", 300);
    }

    function setblinkFont() {
        document.getElementById("diBayar").style.color = ""
        setTimeout("blinkFont()", 300);
    }
</script>