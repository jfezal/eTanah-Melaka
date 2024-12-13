<%-- 
    Document   : bayaran_pbt
    Created on : Nov 19, 2012, 3:55:45 PM
    Author     : haqqiem
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>

<div align="center">
    <table style="width:99.2%" bgcolor="green">
        <tr>
            <td width="100%" height="20" >
                <div style="background-color: green;" align="center">
                    <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Bayaran PBT</font>
                </div>
            </td>
        </tr>
    </table>
</div>

<stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<stripes:form beanclass="etanah.view.stripes.hasil.BayaranPBTActionBean" id="bayaran_pbt">
    <stripes:errors />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Bayaran
            </legend>
            <p>
                <label><em>*</em>Nama Pembayar : </label>
                <stripes:text name="dokumenKewangan.isuKepada" id="nama" size="40" onblur="this.value=this.value.toUpperCase();"/>
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
        </fieldset>
    </div>
    <p></p>
    <div class="subtitle" id="daerah">
        <fieldset class="aras1">
            <legend>Maklumat Urusan </legend>
            <p class=instr>Sila pilih urusan bayaran yang hendak dibuat.</p>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.listTrans}" id="row">
                    <display:column title="Bil."><div align="center">${row_rowNum}.</div></display:column>
                    <display:column title="Urusan" class="tunai">
                        <c:set scope="request" var="pilihanKodUrusan" value="${actionBean.senaraiKodUrusan}" />
                        <c:set scope="request" var="senaraiTrans" value="${actionBean.senaraiKodTransaksi}" />
                        <stripes:select name="listUrusan[${row_rowNum - 1}].kod" id="kodTr${row_rowNum-1}" onchange="checkUrusan(this.form,'${row_rowNum-1}');"
                                        style="width:30em">
                            <stripes:option label="Pilih Urusan..."  value="0" />
                            <c:forEach items="${pilihanKodUrusan}" var="i" >
                                <c:if test="${kodTransaksi ne i.kodTransaksi.kod}" >
                                    <c:set var="kodTransaksi" value="${i.kodTransaksi.kod}" />
                                    <optgroup label="${kodTransaksi}" />
                                </c:if>
                                <stripes:option value="${i.kod}" >${i.kod} - ${i.nama}</stripes:option>
                            </c:forEach>
                        </stripes:select>
                    </display:column>

                    <display:column title="Nombor Rujukan">
                        <div align="center">
                            <stripes:text name="rujukan[${row_rowNum - 1}]" size="15" maxlength="20" id="noRuj${row_rowNum-1}"/>
                        </div>
                    </display:column>

                    <display:column title="Kuantiti">
                        <div align="center">
                            <stripes:text name="kuantiti[${row_rowNum - 1}]" style="text-align:right" onblur="totalAmount(this,${row_rowNum-1});" onkeyup="validateNumber(this,this.value);"
                                          size="4" maxlength="4" id="qty${row_rowNum-1}"/>
                        </div>
                    </display:column>

                    <display:column title="Caj (RM)">
                        <div align="center">
                            <stripes:text name="listUrusan[${row_rowNum - 1}].caj" style="text-align:right" onkeyup="validateNumber(this,this.value);"
                                          onblur="totalAmount(this,${row_rowNum - 1});" id="amt${row_rowNum-1}"/>
                        </div>
                    </display:column>

                    <display:column title="RM" style="width:10px">
                        <div align="center">
                            <stripes:text name=" " style="text-align:right;" readonly="true" id="jum${row_rowNum-1}"/>
                        </div>
                    </display:column>
                    <display:footer>
                        <tr>
                            <td colspan="5"><div align="right"><b>Jumlah (RM):</b></div></td>
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
                <stripes:submit name="Step1" value="Seterusnya" onclick="return validatePembayar();" class="btn" id="nxt"/>
                <stripes:submit name="kembali" value="Kembali" class="btn"/>
            </td>
        </tr>
    </table></div>
</stripes:form>
<script language="javascript" >
    $(document).ready(function() {
        $('#kod0').focus();
        $('#nxt').attr("disabled", "true");
        var b = ${actionBean.bil};
        for (var i = 0; i < b; i++){
            $('#kod'+i).val("0");
            $('#qty'+i).val("0");
            $('#caj'+i).val("0");
            $('#jum'+i).val("0");
            $('#amt'+i).val("0");
            $('#noRuj'+i).val("");
            $('#qty'+i).attr("disabled", "true");
            $('#noRuj'+i).attr("disabled", "true");
            $('#jum'+i).attr("disabled", "true");
            $('#amt'+i).attr("disabled", "true");
            $('#caj'+i).attr("disabled", "true");
        }
            <%--$('#pelbagai').hide();--%>
            <%--$('#daerah').hide();--%>
            var type = document.getElementById("carian");
            changeBayaran(type.value);
    });
</script>
<script type="text/javascript">
    function checkUrusan(f,m){
        var xx = document.getElementById("kodTr"+m);
        if(xx.value != '0'){
            $('#nxt').removeAttr("disabled");
            $('#amt'+m).removeAttr("disabled");
            $('#amt'+m).removeAttr("readonly");
            $('#qty'+m).removeAttr("disabled");
            $('#noRuj'+m).removeAttr("disabled");
            $('#jum'+m).removeAttr("disabled");
            $('#qty'+m).val("1");
            <%--$('#amt'+m).focus();--%>
        }
        if(xx.value == '0'){
            <%--$('#nxt').attr("disabled", "true");--%>
            $('#qty'+m).val("0");
            $('#amt'+m).val("0");
            $('#jum'+m).val("0");
            $('#noRuj'+m).val("");
            $('#noRuj'+m).attr("disabled", "true");
            $('#amt'+m).attr("disabled", "true");
            $('#qty'+m).attr("disabled", "true");
            $('#jum'+m).attr("disabled", "true");
            updateTot2();
        }
    }
    
    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = RemoveNonNumeric(content);
            return;
        }
    }

    function RemoveNonNumeric( strString ){
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

    function totalAmount(inputTxt,row){
        var ursn = document.getElementById('kodTr'+row);
        if(ursn.value != 'SSDOK'){
            inputTxt.value = parseFloat(inputTxt.value).toFixed(2);
            var x = document.getElementById('amt'+row);
            var kuantiti = document.getElementById('qty'+row);
            var amt = 0;
            if ((isNaN(kuantiti.value))||((kuantiti.value) =="")){
                alert("Nombor tidak sah");
                $('#qty'+row).val("1");
                $('#qty'+row).focus();
                amt = parseFloat(kuantiti.value) * parseFloat(x.value);
                $('#jum'+row).val(amt.toFixed(2));
                return;
            }
            if ((isNaN(x.value))||((x.value) =="")){
                alert("Nombor tidak sah");
                $('#amt'+row).val("0.00");
                $('#amt'+row).focus();
                amt = parseFloat(kuantiti.value) * parseFloat(x.value);
                $('#jum'+row).val(amt.toFixed(2));
                return;
            }
            amt = parseFloat(kuantiti.value) * parseFloat(x.value);
            $('#jum'+row).val(amt.toFixed(2));
            var q = parseFloat(kuantiti.value);
            $('#qty'+row).val(q);
            updateTot2();
        }
    }

    function updateTot2(){
        var total = 0;
        for (var i=0; i<5; i++){
            var a = document.getElementById('jum' + i)
            total += parseFloat(a.value);
        }
        var t = document.getElementById('jumCaraBayar1');
        t.value = total.toFixed(2);
        $('#jumCaraBayar1').val(total.toFixed(2));
    }

    function validatePembayar(){
        var name = document.getElementById('nama');
        var add = document.getElementById('add1');
        var bndar = document.getElementById('bandar');
        var pskod = document.getElementById('poskod');
        var ngri = document.getElementById('penyerahNegeri');
        if(name.value == ""){
            alert("Sila Masukkan Nama Pembayar");
            $("#nama").focus();
            return false;
        }
        if(add.value == ""){
            alert("Sila Masukkan Alamat Pembayar");
            $("#add1").focus();
            return false;
        }
        if(bndar.value == ""){
            alert("Sila Masukkan Nama Bandar");
            $("#bandar").focus();
            return false;
        }
        if(pskod.value == ""){
            alert("Sila Masukkan Poskod");
            $("#poskod").focus();
            return false;
        }if(ngri.value == 0){
            alert("Sila Masukkan Negeri");
            $("#penyerahNegeri").focus();
            return false;
        }
    }
</script>