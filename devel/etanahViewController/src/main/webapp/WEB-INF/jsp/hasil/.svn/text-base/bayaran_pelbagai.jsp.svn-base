<%-- 
    Document   : bayaran_pelbagai
    Created on : Apr 5, 2010, 5:51:34 PM
    Author     : abdul.hakim
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>

<div id="jab">
    <div align="center">
        <table style="width:99.2%" bgcolor="green">
            <tr><td width="100%" height="20" ><div style="background-color: green;" align="center">
                        <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Bayaran Pelbagai</font>
                    </div></td></tr>
        </table>
    </div>
    <stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <stripes:form beanclass="etanah.view.stripes.hasil.BayaranPelbagaiActionBean" id="bayaran_pelbagai">
        <stripes:errors />

        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Bayaran
                </legend>
                <c:if test="${actionBean.negeri eq 'melaka'}">    
                    <p>
                        <label>Carian Penyerah: </label>
                        <stripes:select name="penyerahKod.kod" id="penyerahKod" onchange="javascript:updateKodPenyerahInputs();">
                            <stripes:option value="0">INDIVIDU / SYARIKAT</stripes:option>
                            <stripes:options-collection collection="${listUtil.senaraiKodPenyerah}" label="nama" value="kod"/>
                        </stripes:select>

                        <stripes:text name="idPenyerah" size="5" id="idPenyerah" maxlength="7" />
                        <input type="button" id="carianPenyerah"
                               value="Cari" class="btn" onclick="javascript:populatePenyerah(this);" />
                        (Biarkan kosong dan klik "Cari" untuk membuat rujukan)
                    </p>

                    <p>
                        <label for="Jenis Pengenalan">Carian: No. Pengenalan :</label>
                        <stripes:select name="penyerahJenisPengenalan.kod" id="penyerahJenisPengenalan" onchange="clearNoPengenalan();">
                            <stripes:option value="0">Pilih Jenis...</stripes:option>
                            <stripes:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                        </stripes:select>
                        <em>*</em><stripes:text name="penyerahNoPengenalan" id="penyerahNoPengenalan" onkeyup="dodacheck(this.value);"
                                      onblur="doUpperCase(this.id)"/> <font title="No KP Baru: 780901057893, No KP Lama: A2977884, No Syarikat: 127776-V, No Pertubuhan: 432483-U"><em>[cth: 780901-05-7893]</em></font>
                        <input type=button name="searchPenyerah" value="Cari" class="btn" id="carianPihak"
                               onclick="javascript:populatePenyerah(this);" />
                    </p>
                </c:if>

                <p>
                    <label><em>*</em>Nama Pembayar : </label>
                    <stripes:text name="dokumenKewangan.isuKepada" id="nama" size="40" onblur="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label><em id="mhn" value="1">*</em>Nombor Rujukan/Id Permohonan/ID Hakmilik : </label>
                    <stripes:text name="dokumenKewangan.noRujukan" id="idMohon" size="40" onblur="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label><em>*</em>Alamat Pembayar : </label>
                    <stripes:text name="dokumenKewangan.isuKepadaAlamat1" id="add1" size="30" onblur="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <stripes:text name="dokumenKewangan.isuKepadaAlamat2" id="add2" size="30" onblur="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <stripes:text name="dokumenKewangan.isuKepadaAlamat3" id="add3" size="30" onblur="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label><em>*</em>Bandar : </label>
                    <stripes:text name="dokumenKewangan.isuKepadaAlamat4" id="bandar" size="30" onblur="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label><em>*</em>Poskod : </label>
                    <stripes:text name="dokumenKewangan.isuKepadaPoskod" id="poskod" maxlength="5" size="17" onkeyup="validateNumber(this,this.value);"/>
                    <em>5 Digit [cth : 12000]</em>
                </p>
                <p>
                    <label><em>*</em>Negeri</label>
                    <stripes:select name="penyerahNegeri" id="penyerahNegeri" >
                        <stripes:option value="0">Pilih ...</stripes:option>
                        <stripes:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                    </stripes:select>
                </p>
                <p>
                    <label>Nombor Telefon : </label>
                    <stripes:text name="dokumenKewangan.isuKepadaNoTelefon1" id="telefon" size="30" onblur="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label>Emel : </label>
                    <stripes:text name="dokumenKewangan.isuKepadaEmail" id="emel" size="30"/>
                </p>
                <%--<p>
                    <label><em>*</em>Bayaran : </label>
                    <stripes:select name="mod" onchange="javaScript:changeBayaran(this.value)" id="carian">
                        <stripes:option value="0">Sila Pilih...</stripes:option>
                        <stripes:option value="pelbagai">Bayaran Pelbagai</stripes:option>
                        <stripes:option value="daerah">Hasil Daerah</stripes:option>
                    </stripes:select>
                </p>--%>
                <br>
            </fieldset>
        </div>
        <p></p>
        <%--<div class="subtitle" id="pelbagai">
            <fieldset class="aras1">
                <legend>Maklumat Urusan</legend>
                <p class=instr>Sila pilih urusan yang hendak dibuat.</p>
                <div class="content" align="center">
                                <stripes:submit name="showForm" value="Tambah" class="btn"/>
                    <display:table class="tablecloth" name="${actionBean.listUrusan}" id="row">
                        <display:column title="Bil."><div align="center">${row_rowNum}.</div></display:column>
                        <display:column title="Urusan" class="tunai">
                            <stripes:select name="listUrusan[${row_rowNum - 1}].kod" id="kod${row_rowNum-1}" onchange="checkCharge(${row_rowNum-1});">
                                <stripes:option value="0" label="Pilih Urusan..." />
                                <stripes:options-collection collection="${actionBean.senaraiUrusan}"  label="nama" value="kod" sort="nama"/>
                                <c:forEach items="${actionBean.senaraiUrusan}" var="i" >
                                    <stripes:option value="${i.kod}" >${i.kod} - ${i.nama}</stripes:option>
                                </c:forEach>
                            </stripes:select>
                        </display:column>
                        <display:column title="Caj (RM)" class="tunai">
                            <div align="center">
                                <stripes:text name="listUrusan[${row_rowNum - 1}].caj" size="4" id="caj${row_rowNum-1}" onblur="updateAmaun(${row_rowNum-1});blur1('caj${row_rowNum-1}')"
                                              style="text-align:right;background:transparent;border:0 px;" maxlength="10" onclick="click1('caj${row_rowNum-1}')"/>
                            </div>
                        </display:column>
                        <display:column title="Kuantiti">
                            <div align="center">
                                <stripes:text name="kuantiti[${row_rowNum - 1}]" style="text-align:right" onblur="updateAmaun(${row_rowNum-1});"
                                              size="4" maxlength="2" id="qty${row_rowNum-1}"/>
                            </div>
                        </display:column>
                        <display:column title="RM">
                            <div align="center">
                                <stripes:text name=" " style="text-align:right" readonly="true" size="12" id="jum${row_rowNum-1}"/>
                            </div>
                        </display:column>
                        <display:footer>
                            <tr>
                                <td colspan="4"><div align="right"><b>Jumlah (RM):</b></div></td>
                                <td><stripes:text name="jumlah" value="0.00" id="jumCaraBayar" size="12" class="number" readonly="true"/></td>
                            </tr>
                        </display:footer>
                    </display:table>
                </div>
    `            </fieldset>
        </div>--%>

        <div class="subtitle" id="daerah">
            <fieldset class="aras1">
                <legend>Maklumat Urusan </legend>
                <p class=instr>Sila pilih urusan bayaran yang hendak dibuat.</p>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.listTrans}" id="row">
                        <display:column title="Bil."><div align="center">${row_rowNum}.</div></display:column>
                        <%--<display:column title="Urusan" class="tunai">
                            <stripes:select name="listTrans[${row_rowNum - 1}].kodTransaksi.kod" id="kodTr${row_rowNum-1}" onchange="checkUrusan(this.form,'${row_rowNum-1}');">
                                <stripes:option value="0" label="Pilih Urusan Bayaran..." />
                                <c:forEach items="${actionBean.senaraiKodTransaksi}" var="c" >
                                    <stripes:option value="${c.kod}" >${c.kod} - ${c.nama}</stripes:option>
                                </c:forEach>
                            </stripes:select>
                        </display:column>--%>
                        <display:column title="Urusan" class="tunai" style="width:80%">
                            <c:set scope="request" var="pilihanKodUrusan" value="${actionBean.senaraiKodUrusan}" />
                            <c:set scope="request" var="senaraiTrans" value="${actionBean.senaraiKodTransaksi}" />
                            <%--<c:set scope="request" var="kodTransaksi" value="${pilihanKodUrusan[0].kodTransaksi.kod}" />--%>
                            <stripes:select name="listUrusan[${row_rowNum - 1}].kod" id="kodTr${row_rowNum-1}" onchange="checkUrusan(this.form,'${row_rowNum-1}',this.value);" style="width:100%">
                                <stripes:option label="Pilih Urusan..."  value="0" />
                                <%--<optgroup label="${kodTransaksi}" />--%>
                                <c:forEach items="${pilihanKodUrusan}" var="i" >
                                    <c:if test="${kodTransaksi ne i.kodTransaksi.kod}" >
                                        <c:set var="kodTransaksi" value="${i.kodTransaksi.kod}" />
                                        <optgroup label="${kodTransaksi}" /> 
                                    </c:if>
                                    <stripes:option value="${i.kod}" >${i.kod} - ${i.nama}</stripes:option>
                                </c:forEach>
                            </stripes:select>
                        </display:column>


                        <%--<display:column title="Pecahan Urusan" class="tunai">
                            <stripes:select name="pecahanUrusan[${row_rowNum - 1}].kodTransaksi.kod" id="kodTr${row_rowNum-1}" onchange="checkUrusan(this.form,'${row_rowNum-1}');">
                                <stripes:option value="0" label="Pilih Urusan Bayaran..." />
                                <c:forEach items="${actionBean.senaraiKodTransaksi}" var="c" >
                                    <stripes:option value="${c.kod}" >${c.kod} - ${c.nama}</stripes:option>
                                </c:forEach>
                            </stripes:select>
                           <div id="urusan${row_rowNum-1}">
                            <stripes:select name="listUrusan[${row_rowNum - 1}].kod" id="kodUrsn${row_rowNum-1}" onchange="javascript:updateKod(0);">
                                <stripes:option value="0" label="Pilih Pecahan Urusan..." />
                                 <stripes:options-collection collection="${actionBean.pecahanUrusan}"  label="nama" value="kod" sort="nama"/>
                                <c:forEach items="${actionBean.pecahanUrusan}" var="i" >
                                    <stripes:option value="${i.kod}" >${i.kod} - ${i.nama}</stripes:option>
                                </c:forEach>
                            </stripes:select></div>
                            
                        </display:column>--%>
                        <display:column title="Kuantiti">
                            <div align="center">
                                <stripes:text name="kuantiti[${row_rowNum - 1}]" style="text-align:right" onblur="totalAmount(this,${row_rowNum-1});consentCase(${row_rowNum-1},this)"
                                              size="4" maxlength="4" id="qty${row_rowNum-1}"/>
                            </div>
                        </display:column>

                        <display:column title="Caj (RM)">
                            <div align="center">
                                <stripes:text name="listUrusan[${row_rowNum - 1}].caj" style="text-align:right" onblur="totalAmount(this,${row_rowNum - 1});" id="amt${row_rowNum-1}"/>
                            </div>
                        </display:column>

                        <display:column title="RM" style="width:10px">
                            <div align="center">
                                <stripes:text name=" " style="text-align:right;" readonly="true" id="jum${row_rowNum-1}"/>
                            </div>
                        </display:column>
                        <display:footer>
                            <tr>
                                <td colspan="4"><div align="right"><b>Jumlah (RM):</b></div></td>
                                <td><stripes:text name="jumlah" value="0.00" id="jumCaraBayar1" class="number" readonly="true"/></td>
                            </tr>
                        </display:footer>
                    </display:table>
                </div>
            </fieldset>
        </div>

        <div align="center"><table border="0" bgcolor="green" style="width:99.2%">
                <tr>
                    <td align="right">
                        <stripes:submit name="payment" value="Seterusnya" onclick="return validatePembayar();" class="btn" id="nxt"/>
                        <stripes:submit name="kembali" value="Kembali" class="btn"/>
                    </td>
                </tr>
            </table></div>
        </stripes:form>
</div>
<script language="javascript" >
    $(document).ready(function() {
        updateKodPenyerahInputs();
        var id = document.getElementById('idPenyerah');
        if (id.value != '') {
          $('#carianPenyerah').click();
        }
        
        $('#kod0').focus();
        $('#mhn').hide();
        $('#nxt').attr("disabled", "true");
        var b = ${actionBean.bil};
        for (var i = 0; i < b; i++) {
            $('#kod' + i).val("0");
            $('#qty' + i).val("0");
            $('#caj' + i).val("0");
            $('#jum' + i).val("0");
            $('#amt' + i).val("0");
            $('#qty' + i).attr("disabled", "true");
            $('#jum' + i).attr("disabled", "true");
            $('#amt' + i).attr("disabled", "true");
            $('#caj' + i).attr("disabled", "true");
        }
    <%--$('#pelbagai').hide();--%>
    <%--$('#daerah').hide();--%>
        var type = document.getElementById("carian");
        changeBayaran(type.value);
    });
</script>
<script type="text/javascript">
    function checkUrusan(f, m, k) {
        var xx = document.getElementById("kodTr" + m);
        if (xx.value != '0') {
            $('#nxt').removeAttr("disabled");
            $('#amt' + m).removeAttr("disabled");
            $('#amt' + m).removeAttr("readonly");
            $('#qty' + m).removeAttr("disabled");
            $('#jum' + m).removeAttr("disabled");
            $('#qty' + m).val("1");
            $('#amt' + m).focus();

            // onchange function
    <%--alert(xx.value);
    var queryString = $(f).formSerialize();
    var url = "${pageContext.request.contextPath}/hasil/bayaran_pelbagai?filterByKodHasil&"+queryString+"&kodHasil="+xx.value;
    var url = "${pageContext.request.contextPath}/hasil/bayaran_pelbagai?filterByKodHasil&kodHasil="+xx.value;
    $.get(url,
    function(data){
        alert(data);
        $('#urusan'+m).html(data);
        $('#jab').html(data);
    },'html');--%>
        }
        if (xx.value == '0') {
    <%--$('#nxt').attr("disabled", "true");--%>
            $('#qty' + m).val("0");
            $('#amt' + m).val("0");
            $('#jum' + m).val("0");
            $('#amt' + m).attr("disabled", "true");
            $('#qty' + m).attr("disabled", "true");
            $('#jum' + m).attr("disabled", "true");
            updateTot2();
        }
        if (k == '72460') {
            $('#mhn').show();
        }
//                    else {
//                        $('#mhn').hide();
//                    }
        checkCharge(m, k);
    }
    function validateNumber(elmnt, content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }

    function removeNonNumeric(strString)
    {
        var strValidCharacters = "1234567890";
        var strReturn = "";
        var strBuffer = "";
        var intIndex = 0;
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
    function totalAmount(inputTxt, row) {
        var ursn = document.getElementById('kodTr' + row);
        if (ursn.value != 'SSDOK') {
            inputTxt.value = parseFloat(inputTxt.value).toFixed(2);
            var x = document.getElementById('amt' + row);
            var kuantiti = document.getElementById('qty' + row);
            var amt = 0;
            if ((isNaN(kuantiti.value)) || ((kuantiti.value) == "")) {
                alert("Nombor tidak sah");
                $('#qty' + row).val("1");
                $('#qty' + row).focus();
                amt = parseInt(kuantiti.value) * parseInt(x.value);
                $('#jum' + row).val(amt.toFixed(2));
                return;
            }
            if ((isNaN(x.value)) || ((x.value) == "")) {
                alert("Nombor tidak sah");
                $('#amt' + row).val("0.00");
                $('#amt' + row).focus();
                amt = parseInt(kuantiti.value) * parseInt(x.value);
                $('#jum' + row).val(amt.toFixed(2));
                return;
            }
            amt = parseInt(kuantiti.value) * parseFloat(x.value);
            $('#jum' + row).val(parseFloat(amt).toFixed(2));
            var q = parseInt(kuantiti.value);
            $('#qty' + row).val(q);
    <%--$('#amt'+row).val((parseFloat(x.value)).toFixed(2));--%>
            updateTot2();
        }
    }
    function checkCharge(m, k) {
        var x = 0;
        $.get("${pageContext.request.contextPath}/hasil/bayaran_pelbagai?doCheckHakmilik&kod=" + k,
                function(data) {
                    var x = parseFloat(data);
                    $('#caj' + m).val(x.toFixed(2));
                    if (k != "0") {
                        $('#nxt').removeAttr("disabled");
                        $('#qty' + m).removeAttr("disabled");
                        $('#jum' + m).removeAttr("disabled");
                        $('#caj' + m).removeAttr("disabled");
                        $('#qty' + m).val("1");
                        $('#amt' + m).val(x.toFixed(2));
                        $('#jum' + m).val(x.toFixed(2));
                        updateTot();
                    }
                    if (k == "0") {
                        $('#qty' + m).val("0");
                        $('#caj' + m).val("0");
                        $('#jum' + m).val("0");
                        $('#qty' + m).attr("disabled", "true");
                        $('#jum' + m).attr("disabled", "true");
                        updateTot();
                    }
                    var y = m - 1;
                    if (y >= 0) {
                        var kod1 = document.getElementById('kod' + y);
                        if (kod1.value == "0") {
                            alert("Sila Masukkan Urusan Mengikut urutan");
                            $('#qty' + m).val("0");
                            $('#caj' + m).val("0");
                            $('#jum' + m).val("0");
                            $('#kod' + m).val("0");
                            $('#qty' + m).attr("disabled", "true");
                            $('#jum' + m).attr("disabled", "true");
                            $('#nxt').attr("disabled", "true");
                            updateTot();
                        }
                    }
                });
    }

    function consentCase(id, b) {
        var amt = 0;
        var kod = document.getElementById('kodTr' + id);
        var bilangan = parseInt(b.value);
        if (kod.value == 'SSDOK') {
            $('#amt' + id).attr("readonly", "true");
            if (bilangan <= 5) {
                amt = parseInt(50);
            } else {
                amt = ((bilangan - 5) * parseInt(10)) + parseInt(50);
            }
            $('#jum' + id).val(amt.toFixed(2));
            $('#amt' + id).val(amt.toFixed(2));
            updateTot2();
        }
    }

    function updateAmaun(qty) {
        var kuantiti = document.getElementById('qty' + qty);
        var caj = document.getElementById('caj' + qty);
        if ((isNaN(kuantiti.value)) || ((kuantiti.value) == "")) {
            alert("Nombor tidak sah");
            $('#qty' + qty).val("1");
            $('#qty' + qty).focus();
            return;
        }
        var amt = parseInt(kuantiti.value) * parseInt(caj.value);
        $('#jum' + qty).val(amt.toFixed(2));
        updateTot();
    }

    function updateTot() {
        var total = 0;
        for (var i = 0; i < 8; i++) {
            var a = document.getElementById('jum' + i)
            total += parseFloat(a.value);
        }
        var t = document.getElementById('jumCaraBayar1');      
        t.value = total.toFixed(2);       
    }

    function updateTot2() {
        var total = 0;
        for (var i = 0; i < 8; i++) {
            var a = document.getElementById('jum' + i)
            total += parseFloat(a.value);
        }
        var t = document.getElementById('jumCaraBayar1');
        t.value = total.toFixed(2);
        $('#jumCaraBayar1').val(total.toFixed(2));
        
        //alert($('#jumCaraBayar1').val());
    }

    function validatePembayar() {
        var name = document.getElementById('nama');
        var add = document.getElementById('add1');
        var bndar = document.getElementById('bandar');
        var pskod = document.getElementById('poskod');
        var ngri = document.getElementById('penyerahNegeri');

        if (name.value == "") {
            alert("Sila Masukkan Nama Pembayar");
            $("#nama").focus();
            return false;
        }

        for (var i = 0; i < 8; i++) {
            if ($("#kodTr" + i).val() == "72460") {
                if ($("#idMohon").val() == "") {
                    alert("Sila Masukkan Nombor Rujukan/Id Permohonan");
                    $("#idMohon").focus();
                    return false;
                }
            }
        }
        if (add.value == "") {
            alert("Sila Masukkan Alamat Pembayar");
            $("#add1").focus();
            return false;
        }
        if (bndar.value == "") {
            alert("Sila Masukkan Nama Bandar");
            $("#bandar").focus();
            return false;
        }
        if (pskod.value == "") {
            alert("Sila Masukkan Poskod");
            $("#poskod").focus();
            return false;
        }
        if (ngri.value == 0) {
            alert("Sila Masukkan Negeri");
            $("#penyerahNegeri").focus();
            return false;
        }
    }

    <%--function changeBayaran(f){
        if (f == 'pelbagai'){
            $('#pelbagai').show();
            $('#daerah').hide();
            $('#nxt').attr("disabled", "true");
        }
        else if (f == 'daerah'){
            $('#daerah').show();
        }
            $('#pelbagai').hide();
            $('#nxt').attr("disabled", "true");
        else{
            $('#pelbagai').hide();
            $('#daerah').hide();
            $('#nxt').attr("disabled", "true");
        }
    }
    --%>
    function click1(id) {
        $('#' + id).removeAttr("style");
        $('#' + id).attr("style", "text-align:right");
    }

    function blur1(id) {
        $('#' + id).removeAttr("style");
        $('#' + id).attr("style", "background:transparent;border:0 px;cursor:pointer");
        var a = document.getElementById(id)
        if ((isNaN(a.value)) || ((a.value) == "")) {
            alert("Nombor tidak sah");
            $('#' + id).val("0.00");
            return;
        }
    }

    var DELIM = "__^$__";

    function populatePenyerah(btn) {
        var url;
        if (btn.id == "carianPenyerah") {
            $('#kod').val('1');
            var idx = $("#idPenyerah").val();
            var jenis = $('#penyerahKod').val();
            if (idx == null || idx.length == 0) {
                window.open("${pageContext.request.contextPath}/common/req_penyerah_info?showForm&popup=true&jenisPenyerah=" + jenis, "eTanah",
                            "status=0,toolbar=0,location=0,menubar=0,width=900,height=1024");
                return;
            }
            if (jenis == '0') {
                alert('Sila pilih Jenis Penyerah');
                return;
            } else if (jenis == '01') { // PEGUAM
                url = "${pageContext.request.contextPath}/common/req_peguam_info?idPeguam=" + idx;
            } else if (jenis == '02') { // JUBL
                url = "${pageContext.request.contextPath}/common/req_jubl_info?idJUBL=" + idx;
            } else if (jenis == '00') { //Unit dalaman
                url = "${pageContext.request.contextPath}/common/req_unit_info?idUnit=" + idx;
            } else if (jenis == '05') { //Perbadanan Pengurusan
                url = "${pageContext.request.contextPath}/common/req_agensi_info?idAgensi=" + idx;
            } else if (jenis == '06') { //Jabatan Kerajaan
                url = "${pageContext.request.contextPath}/common/req_agensi_info?idAgensi=" + idx;
            } else if (jenis == '07') { //Badan Berkanun
                url = "${pageContext.request.contextPath}/common/req_agensi_info?idAgensi=" + idx;
            } else if (jenis == '03') { //Jururancang Berlesen
                url = "${pageContext.request.contextPath}/common/req_agensi_info?idAgensi=" + idx;
            } else if (jenis == '04') { //Jurulelong Berlesen
                url = "${pageContext.request.contextPath}/common/req_lelong_info?idAgensi=" + idx;
            }
        } else if (btn.id == "carianPihak") {
            $('#kod').val('2');
            var jP = $("#penyerahJenisPengenalan").val();
            var noP = $("#penyerahNoPengenalan").val();
            if (jP == null || jP.length == 0 || noP == null || noP.length == 0) {
                alert("Sila masukkan Jenis Pengenalan/No.Pengenalan.");
                return;
            }
            url = "${pageContext.request.contextPath}/common/req_pihak_info?jenisPengenalan=" + jP
                    + "&noPengenalan=" + noP;
        }

        $.get(url,
                function(data) {
                    if (data == null || data.length == 0) {
                        alert("Maklumat tidak dijumpai");
                        return;
                    }
                    var p = data.split(DELIM);
                    $('#penyerahJenisPengenalan').val(p[0]);
                    $('#penyerahNoPengenalan').val(p[1]);
                    $("#nama").val(p[2]);
                    $("#add1").val(p[3]);
                    $("#add2").val(p[4]);
                    $("#add3").val(p[5]);
                    $("#bandar").val(p[6]);
                    $("#poskod").val(p[7]);
                    $("#penyerahNegeri").val(p[8]);
                    $("#penyerahNoTelefon").val(p[9]);
                    $("#penyerahEmail").val(p[10]);
                });
    }

    function updateKodPenyerahInputs() {
        //alert($("#penyerahKod").val());
        if ($("#penyerahKod").val() == '0' || $("#penyerahKod").val() === '05') {
          // disable button for kod penyerah
          $("#idPenyerah").attr("disabled", "disable");
          $("#carianPenyerah").attr("disabled", "disabled");
          // enable carian pihak
          $("#penyerahJenisPengenalan").attr("disabled", "");
          $("#penyerahJenisPengenalan").focus();
          $("#penyerahNoPengenalan").attr("disabled", "");
          $("#carianPihak").attr("disabled", "");
        } else {
          // disable button for kod penyerah
          $("#idPenyerah").attr("disabled", "");
          $("#idPenyerah").focus();
          $("#carianPenyerah").attr("disabled", "");
          // enable carian pihak
          $("#penyerahJenisPengenalan").attr("disabled", "disabled");
          $("#penyerahNoPengenalan").attr("disabled", "disabled");
          $("#carianPihak").attr("disabled", "disabled");
          // code to clear data
          $('#penyerahJenisPengenalan').val("0");
          $('#penyerahNoPengenalan').val("");
          $("#nama").val("");
          $("#add1").val("");
          $("#add2").val("");
          $("#add3").val("");
          $("#bandar").val("");
          $("#poskod").val("");
          $("#penyerahNegeri").val("0");
          $("#penyerahNoTelefon").val("");
          $("#penyerahEmail").val("");
        }
      }
</script>

