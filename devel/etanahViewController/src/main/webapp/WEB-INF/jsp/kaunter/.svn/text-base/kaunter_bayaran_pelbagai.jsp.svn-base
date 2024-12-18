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

<div align="center">
     <table style="width:99.2%" bgcolor="green">
            <tr><td width="100%" height="20" ><div style="background-color: green;" align="center">
                        <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Bayaran Pelbagai</font>
            </div></td></tr>
        </table>
</div>
<stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<stripes:form beanclass="etanah.view.kaunter.BayaranPelbagaiActionBean2" id="bayaran_pelbagai">
<stripes:errors />

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Bayaran</legend>
            <label><em>*</em>Jenis Bayaran : </label>
            <select name="mod" onchange="javaScript:changeBayaran(this.value);" id="carian">
                <option value="0">Sila Pilih...</option>
                <option value="pelbagai">Bayaran Pelbagai</option>
                <option value="daerah">Hasil Daerah</option>
            </select>
            <br>
        </fieldset>
    </div>
    
    <p></p>
    
    <div class="subtitle" id="pelbagai">
        <fieldset class="aras1">
            <legend>Maklumat Urusan</legend>
            <p class=instr>Sila pilih Bayaran yang hendak dibuat.</p>
            <div class="content" align="center">
                            <stripes:submit name="showForm" value="Tambah" class="btn"/>
                <display:table class="tablecloth" name="${actionBean.listUrusan}" id="row">
                    <display:column title="Bil."><div align="center">${row_rowNum}.</div></display:column>
                    <display:column title="Urusan" class="tunai">
                        <stripes:hidden name="listUrusan[${row_rowNum - 1}].kategoriUrusan" />
                        <stripes:select name="listUrusan[${row_rowNum - 1}].kodUrusan" id="kod${row_rowNum-1}" onchange="checkCharge(${row_rowNum-1});">
                            <stripes:option value="0" label="Pilih Urusan..." />
                            <c:forEach items="${actionBean.senaraiUrusan}" var="i" >
                                <stripes:option value="${i.kod}" >${i.kod} - ${i.nama}</stripes:option>
                            </c:forEach>
                        </stripes:select>
                    </display:column>
                    <display:column title="Amaun (RM)" class="tunai">
                        <div align="center">
                            <stripes:text name="listUrusan[${row_rowNum - 1}].cajPerUnit" size="4" id="caj${row_rowNum-1}" onblur="updateAmaun(${row_rowNum-1});blur1('caj${row_rowNum-1}')"
                                          style="text-align:right;background:transparent;border:0 px;" maxlength="10" onclick="click1('caj${row_rowNum-1}')"/>
                        </div>
                    </display:column>
                    <display:column title="Kuantiti">
                        <div align="center">
                            <stripes:text name="listUrusan[${row_rowNum - 1}].kuantiti" style="text-align:right" onblur="updateAmaun(${row_rowNum-1});"
                                          size="4" maxlength="2" id="qty${row_rowNum-1}"/>
                        </div>
                    </display:column>
                    <display:column title="Jumlah (RM)">
                        <div align="center">
                            <stripes:text name=" " style="text-align:right" readonly="true" size="12" id="jum${row_rowNum-1}"/>
                        </div>
                    </display:column>
                    <display:footer>
                        <tr>
                            <td colspan="4"><div align="right"><b>Jumlah Besar (RM):</b></div></td>
                            <td><stripes:text name="jumlah" value="0.00" id="jumCaraBayar" size="12" class="number" readonly="true"/></td>
                        </tr>
                    </display:footer>
                </display:table>
            </div>
`            </fieldset>
    </div>

    <div class="subtitle" id="daerah">
        <fieldset class="aras1">
            <legend>Maklumat Urusan </legend>
            <p class=instr>Sila pilih urusan bayaran yang hendak dibuat.</p>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.listTrans}" id="row">
                    <display:column title="Bil."><div align="center">${row_rowNum}.</div></display:column>
                    <display:column title="Urusan" class="tunai">
                        <stripes:hidden name="listTrans[${row_rowNum - 1}].kategoriUrusan" />                    
                        <stripes:select name="listTrans[${row_rowNum - 1}].kodUrusan" id="kodTr${row_rowNum-1}" onchange="checkUrusan('${row_rowNum-1}');">
                            <stripes:option value="0" label="Pilih Urusan Bayaran..." />
                            <c:forEach items="${actionBean.senaraiKodTransaksi}" var="c" >
                                <stripes:option value="${c.kod}" >${c.kod} - ${c.nama}</stripes:option>
                            </c:forEach>
                        </stripes:select>
                    </display:column>
                    <display:column title="Amaun (RM)">
                        <div align="center">
                            <stripes:text name="listTrans[${row_rowNum - 1}].cajPerUnit" style="text-align:right" onblur="totalAmount(this,${row_rowNum - 1});" id="amt${row_rowNum-1}"/>
                        </div>
                    </display:column>
                    <display:footer>
                        <tr>
                            <td colspan="2"><div align="right"><b>Jumlah (RM):</b></div></td>
                            <td><stripes:text name="jumlah" value="0.00" id="jumCaraBayar1" class="number" readonly="true"/></td>
                        </tr>
                    </display:footer>
                </display:table>
            </div>
`            </fieldset>
    </div>

    <div align="center"><table border="0" bgcolor="green" style="width:99.2%">
        <tr>
            <td align="right">
                <stripes:submit name="kembali" value="Batal" class="btn"/>
                <stripes:submit name="collectPayment" value="Seterusnya" class="btn" id="nxt"/>
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
            $('#jum'+i).val("0.00");
            $('#amt'+i).val("0.00");
            $('#qty'+i).attr("disabled", "true");
            $('#jum'+i).attr("disabled", "true");
            $('#amt'+i).attr("disabled", "true");
            $('#caj'+i).attr("disabled", "true");
        }
            $('#pelbagai').hide();
            $('#daerah').hide();
            var type = document.getElementById("carian");
            changeBayaran(type.value);
    });
</script>
<script type="text/javascript">
    function checkUrusan(m){
        var xx = document.getElementById("kodTr"+m);
        if(xx.value != '0'){
            $('#nxt').removeAttr("disabled");
            $('#amt'+m).removeAttr("disabled");
            $('#amt'+m).focus();
        }
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
    function totalAmount(inputTxt,row){
        inputTxt.value = parseFloat(inputTxt.value).toFixed(2);
        var x = document.getElementById('amt'+row);
        <%--$('#amt'+row).val((parseFloat(x.value)).toFixed(2));--%>
        updateTot2();
    }
    function checkCharge(m){
        var kod = document.getElementById('kod'+m);
        var x = 0;
        $.get("${pageContext.request.contextPath}/kaunter/bayaran_pelbagai?getCajForKod&kod="+kod.value,
        function(data){
        	if (data == null || data == ""){
        		alert("Kadar untuk urusan tidak diketahui");
        		return;
        	}
            var x = parseFloat(data);
            $('#caj'+m).val(x.toFixed(2));
            if(kod.value != "0"){
                $('#nxt').removeAttr("disabled");
                $('#qty'+m).removeAttr("disabled");
                $('#jum'+m).removeAttr("disabled");
                $('#caj'+m).removeAttr("disabled");
                $('#qty'+m).val("1");
                $('#jum'+m).val(x.toFixed(2));
                updateTot();
            }
            if(kod.value == "0"){
                $('#qty'+m).val("0");
                $('#caj'+m).val("0");
                $('#jum'+m).val("0");
                $('#qty'+m).attr("disabled", "true");
                $('#jum'+m).attr("disabled", "true");
                updateTot();
            }
            var y = m-1;
            if(y >= 0){
                var kod1 = document.getElementById('kod'+y);
                if(kod1.value == "0"){
                    alert("Sila Masukkan Urusan Mengikut urutan");
                    $('#qty'+m).val("0");
                    $('#caj'+m).val("0");
                    $('#jum'+m).val("0");
                    $('#kod'+m).val("0");
                    $('#qty'+m).attr("disabled", "true");
                    $('#jum'+m).attr("disabled", "true");
                    $('#nxt').attr("disabled", "true");
                    updateTot();
                }
            }
        });
    }

    function updateAmaun(qty){
        var kuantiti = document.getElementById('qty'+qty);
        var caj = document.getElementById('caj'+qty);
        if ((isNaN(kuantiti.value))||((kuantiti.value) =="")){
            alert("Nombor tidak sah");
            $('#qty'+qty).val("1");
            $('#qty'+qty).focus();
            return;
        }
        var amt = parseInt(kuantiti.value) * parseInt(caj.value);
        $('#jum'+qty).val(amt.toFixed(2));
        updateTot();
    }
    
    function updateTot(){
        var total = 0;
        for (var i=0; i<5; i++){
            var a = document.getElementById('jum' + i)
            total += parseFloat(a.value);
        }
        var t = document.getElementById('jumCaraBayar');
        t.value = total.toFixed(2);
    }

    function updateTot2(){
        var total = 0;
        for (var i=0; i<5; i++){
            var a = document.getElementById('amt' + i)
            total += parseFloat(a.value);
        }
        var t = document.getElementById('jumCaraBayar1');
        t.value = total.toFixed(2);
        $('#jumCaraBayar1').val(total.toFixed(2));
    }

    function validatePembayar(){
        var name = document.getElementById('nama');
        if(name.value == ""){
            alert("Sila Masukkan Nama Pembayar");
            $("#nama").focus();
            return false;
        }
    }

    function changeBayaran(f){
        if (f == 'pelbagai'){
            $('#pelbagai').show();
            $('#daerah').hide();
            $('#nxt').attr("disabled", "true");
        }
        else if (f == 'daerah'){
            $('#daerah').show();
            $('#pelbagai').hide();
            $('#nxt').attr("disabled", "true");
        }
        else{
            $('#pelbagai').hide();
            $('#daerah').hide();
            $('#nxt').attr("disabled", "true");
        }
    }

    function click1(id){
        $('#'+id).removeAttr("style");
        $('#'+id).attr("style", "text-align:right");
    }

    function blur1(id){
        $('#'+id).removeAttr("style");
        $('#'+id).attr("style", "background:transparent;border:0 px;cursor:pointer");
        var a = document.getElementById(id)
        if ((isNaN(a.value))||((a.value) =="")){
            alert("Nombor tidak sah");
            $('#'+id).val("0.00");
            return;
        }
    }

</script>

