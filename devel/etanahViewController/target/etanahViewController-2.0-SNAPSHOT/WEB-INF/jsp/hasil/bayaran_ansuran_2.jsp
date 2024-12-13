<%-- 
    Document   : bayaran_ansuran_2
    Created on : Dec 29, 2010, 12:12:33 PM
    Author     : abdul.hakim
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN"
    "http://www.w3.org/p/html4/loose.dlabel">
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloths.css"/>

<div align="center"><table style="width:99.2%" bgcolor="green">
        <tr>
            <td width="100%" height="20" >
                <div style="background-color: green;" align="center">
                    <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: SPOC Khusus</font>
                </div>
            </td>
        </tr>
    </table></div>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<s:errors />
<s:form beanclass="etanah.view.stripes.hasil.BayaranAnsuranActionBean" id="bayaran_ansuran">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Akaun</legend>
            <p>
                <label>ID Hakmilik :</label>
                <c:if test="${actionBean.akaun.hakmilik eq null}">-</c:if>
                <c:if test="${actionBean.akaun.hakmilik ne null}">${actionBean.akaun.hakmilik.idHakmilik}</c:if>
            </p>

            <p>
                <label>Pemegang :</label>
                ${actionBean.akaun.pemegang.nama}&nbsp;
            </p>

            <%--<p>
                <label>Baki Yang Perlu Dijelaskan :</label>
                <fmt:formatNumber value="${actionBean.balance}" pattern=" RM 0.00"/>&nbsp;
            </p>--%>

            <p>
                <label>Tarikh Mula Ansuran :</label>
                <fmt:formatDate value="${actionBean.permohonan.tarikhMula}" pattern="dd/MM/yyyy"/>&nbsp;
            </p>
            <br>
        </fieldset>
    </div>

    <p></p>
    
    <div class="subtitle" id="schedule">
        <fieldset class="aras1">
            <legend>Jadual Pembayaran</legend>
            <div align="center">
                <table border=0 align="center" class="tablecloth">
                    <tr>
                        <th>Bulan</th>
                        <c:forEach var="i" begin="1" end="${actionBean.tempoh}" >
                            <th  align="center">
                                <%--<s:text style="text-align:center" name="jadual[${i - 1}]" size="10"/>--%>
                                ${actionBean.jadual[i-1]}<br>
                                ${actionBean.year[i-1]}
                            </th>
                        </c:forEach>
                    </tr>

                    <tr>
                        <th>Bayaran (RM)</th>
                        <%--<td align="center">
                            <div align="center"><fmt:formatNumber value="${actionBean.firstPayment}" pattern="#0.00"/></div>
                        </td>--%>
                        <c:forEach var="i" begin="1" end="${(actionBean.tempoh)-1}" >
                            <td  align="center">
                                <div align="center"><fmt:formatNumber value="${actionBean.monthly}" pattern="#0.00"/></div>
                                <%--&nbsp;&nbsp;&nbsp;&nbsp;${actionBean.jadual[i-1]}&nbsp;&nbsp;&nbsp;&nbsp;--%>
                            </td>
                        </c:forEach>
                        <td align="center">
                            <div align="center"><fmt:formatNumber value="${actionBean.lastPayment}" pattern="#0.00"/></div>
                        </td>
                    </tr>
                </table>
            </div>
            <br>
        </fieldset>
    </div><s:text name="akaun.kodAkaun.kod" value="${actionBean.akaun.kodAkaun.kod}" id="kod" style="display:none;"/>

    <p></p>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Transaksi</legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.senaraiTransaksi}" requestURI="/hasil/ansuran" id="line">
                    <display:column title="Bil">${line_rowNum}</display:column>
                    
                    <display:column property="infoAudit.tarikhMasuk" title="Tarikh" format="{0,date,dd/MM/yyyy}"/>

                    <display:column title="Transaksi">
                        ${line.kodTransaksi.kod} - ${line.kodTransaksi.nama}
                    </display:column>

                    <display:column title="Nombor Resit" >
                        <c:if test="${line.dokumenKewangan.idDokumenKewangan eq null}">-</c:if>
                        <c:if test="${line.dokumenKewangan.idDokumenKewangan ne null}">${line.dokumenKewangan.idDokumenKewangan}</c:if>
                    </display:column>

                    <display:column title="Debit (RM)" style="text-align:right">
                        <c:choose>
                            <c:when test="${line.status.kod eq 'A' and line.kodTransaksi.kod ne '99000'and line.kodTransaksi.kod ne '99001'and line.kodTransaksi.kod ne '99002'and line.kodTransaksi.kod ne '99003' and line.kodTransaksi.kod ne '99030'}">
                                <fmt:formatNumber value="${line.amaun}" pattern="#,###,##0.00"/>
                            </c:when>
                            <c:when test="${sline.status.kod eq 'B'}">
                                <fmt:formatNumber value="${line.amaun}" pattern="#,###,##0.00"/>
                            </c:when>
                            <c:otherwise>&nbsp;</c:otherwise>
                        </c:choose>
                    </display:column>

                    <display:column title="Kredit (RM)" style="text-align:right">
                        <c:choose>
                            <c:when test="${line.status.kod ne 'A' and line.status.kod ne 'B'}">
                                <fmt:formatNumber value="${line.amaun}" pattern="#,###,##0.00"/>
                            </c:when>
                            <c:when test="${line.status.kod eq 'A' and line.kodTransaksi.kod eq '99002' or line.kodTransaksi.kod eq '99000'or line.kodTransaksi.kod eq '99001' or line.kodTransaksi.kod eq '99003' or line.kodTransaksi.kod eq '99030'}">
                                <fmt:formatNumber value="${line.amaun}" pattern="#,###,##0.00"/>
                            </c:when>
                            <c:otherwise>&nbsp;</c:otherwise>
                        </c:choose>
                    </display:column>
                    
                    <display:column title="Status" property="status.nama"/>
                    <display:footer>
                        <tr>
                            <td colspan="4"><div align="right"><b>Baki Awal :</b></div></td>
                            <td colspan="2" style="bgcolor:yellow"><div align="right"><b><fmt:formatNumber value="${actionBean.bakiAwal}" pattern="RM #,###,##0.00"/></b></div></td>
                        <tr>
                        <tr>
                            <td colspan="4"><div align="right"><b>Denda Semasa :</b></div></td>
                            <td colspan="2" style="bgcolor:yellow"><div align="right"><b><fmt:formatNumber value="${actionBean.denda}" pattern="RM #,###,##0.00"/></b></div></td>
                        <tr>
                        <tr>
                            <td colspan="4"><div align="right"><b>Jumlah Yang sudah dijelaskan :</b></div></td>
                            <td colspan="2" style="bgcolor:yellow"><div align="right"><b><fmt:formatNumber value="${actionBean.bayar}" pattern="RM #,###,##0.00"/></b></div></td>
                        <tr>
                        <tr>
                            <td colspan="4"><div align="right"><b><i>Baki Yang Perlu Dijelaskan :</i></b></div></td>
                            <td colspan="2" style="bgcolor:yellow"><div align="right" id="bypdj"><b><fmt:formatNumber value="${actionBean.balance}" pattern="RM #,###,##0.00"/></b></div></td>
                        <tr>
                        </display:footer>
                    </display:table>
            </div>
            <br>
        </fieldset>
    </div>

    <p></p>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Cara Bayaran</legend>
            <p class=instr><em><font color="black">Masukkan butir-butir pembayaran.</font></em>
            </p>
            &nbsp;&nbsp;&nbsp;&nbsp;<s:errors field="amaun"/>
            <div align="center">
                <display:table name="${actionBean.senaraiCaraBayaran}" id="row" class="tablecloths">
                    <display:column title="Cara" class="tunai">
                        <s:select name="senaraiCaraBayaran[${row_rowNum - 1}].kodCaraBayaran.kod"
                                  id="senaraiCaraBayaran${row_rowNum - 1}" onchange="javaScript:change(${row_rowNum - 1})">
                            <s:option value="0" label="Pilih ..." />
                            <s:options-collection collection="${listUtil.senaraiKodCaraBayaran}"  label="nama" value="kod" sort="nama"/>
                        </s:select>
                    </display:column>

                    <display:column title="Bank" ><em id="agensi${row_rowNum - 1}">*</em>
                        <s:select name="senaraiCaraBayaran[${row_rowNum - 1}].bank.kod" id="bank${row_rowNum - 1}">
                            <s:option label="Pilih..." value="0" />
                            <s:options-collection collection="${listUtil.senaraiKodBank}"  label="nama" value="kod" />
                        </s:select>
                    </display:column>

                    <display:column title="Cawangan" >
                        <s:text name="senaraiCaraBayaran[${row_rowNum - 1}].bankCawangan" id="caw${row_rowNum - 1}" size="20" onkeyup="this.value=this.value.toUpperCase();"/>
                    </display:column>

                    <display:column title="No. Rujukan" ><em id="noRuj${row_rowNum - 1}">*</em>
                        <s:text name="senaraiCaraBayaran[${row_rowNum - 1}].noRujukan" id="rujukan${row_rowNum - 1}" size="20" />
                    </display:column>

                    <display:column title="Tarikh"><em id="trkhCek${row_rowNum - 1}">*</em>
                        <s:text name="tarikhCek[${row_rowNum - 1}]" id="trkh${row_rowNum - 1}" size="20" readonly="true" maxlength="10" class="datepicker"/>
                    </display:column>

                    <display:column title="Amaun (RM)">
                        <s:text name="senaraiCaraBayaran[${row_rowNum - 1}].amaun" size="12" class="number"
                                onblur="javascript:updateTotal(this, '${row_rowNum - 1}');" id="amaun${row_rowNum - 1}"/>
                    </display:column>
                    <display:footer>
                        <tr>
                            <td colspan="5"><div align="right"><b>Jumlah (RM):</b></div></td>
                            <td><input name="jumCaraBayar" value="0.00" id="jumCaraBayar" size="12" class="number"/></td>
                        <tr>
                        </display:footer>
                    </display:table >
            </div>
            <br>
        </fieldset>
    </div>

    <div align="center"><table style="width:99.2%" bgcolor="green">
            <tr>
                <td align="right">
                    <s:submit name="Step4" value="Bayar" class="btn" onclick="return checking();"/>
                    <s:submit name="Step1" value="Kembali" class="btn"/>&nbsp;
                </td>
            </tr>
        </table></div>
    </s:form>
<script language="javascript" >
    $(document).ready(function() {
        // set the first default payment to Tunai
        var baki = ${actionBean.akaun.baki};
        var kod = document.getElementById("kod");
        var matang = ${actionBean.akaun.amaunMatang};
        if(((baki*-1) >= matang)&&(kod.value == "ACT")){
            alert("Akaun telah matang. Sila buat pindaan ke Cukai Tanah");
        }
    <%-- if(kod.value != "ACT"){
         $('#schedule').hide();
     }--%>
             $('#senaraiCaraBayaran0').val('T');
             // focus on the first payment
             $("#bank0").hide();
             $("#caw0").hide();
             $("#rujukan0").hide();
             $("#trkh0").hide();
             $("#agensi0").hide();
             $("#noRuj0").hide();
             $("#trkhCek0").hide();
             for (var i = 1; i < 5; i++){
                 $("#agensi"+i).hide();
                 $("#noRuj"+i).hide();
                 $("#trkhCek"+i).hide();
                 $('#bank'+i).attr("disabled", "true");
                 $('#caw'+i).attr("disabled", "true");
                 $('#rujukan'+i).attr("disabled", "true");
                 $('#trkh'+i).attr("disabled", "true");
                 $('#amaun'+i).attr("disabled", "true");
                 $('#amaun'+i).val("0");
             }
             blinkFont();
         });
</script>

<script  language="javascript" >

    function updateTotal (inputTxt,row){
        var total = 0;
        var a = document.getElementById('amaun' + row)
        if ((isNaN(a.value))||((a.value) =="")){
            alert("Nombor tidak sah");
            $('#amaun'+row).val("0");
            inputTxt.value = RemoveNonNumeric(a);
            updtTot();
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

    function checking (){
        var amaun = document.getElementById('jumCaraBayar');
        if(amaun.value == '0.00'){
            alert("Sila masukkan amaun bayaran.");
            return false;
        }
        for (var i = 0; i < 5; i++){
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
        }
        return true;
    }

    function RemoveNonNumeric( strString )
    {
        var strValidCharacters = "1234567890.";
        var strReturn = "0";
        var strBuffer = "0";
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

    function change(row){
        for (var i = 0; i < (row+1); i++){
            var a = document.getElementById('senaraiCaraBayaran'+i);
            if(a.value != '0'){
                $('#bank'+i).removeAttr("disabled");
                $('#caw'+i).removeAttr("disabled");
                $('#rujukan'+i).removeAttr("disabled");
                $('#trkh'+i).removeAttr("disabled");
                $('#amaun'+i).removeAttr("disabled");
                if (a.value == 'T'){
                    $('#a'+i).hide();
                    $('#bank'+i).hide();
                    $("#caw"+i).hide();
                    $("#rujukan"+i).hide();
                    $("#trkh"+i).hide();
                    $('#agensi'+i).hide();
                    $('#noRuj'+i).hide();
                    $('#trkhCek'+i).hide();
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
                    $('#agensi'+i).show();
                    $('#noRuj'+i).show();
                    $('#trkhCek'+i).show();
                    $('#a'+i).show();
                    if(a.value == '0'){
                        $('#a'+i).hide();
                        $('#agensi'+i).hide();
                        $('#noRuj'+i).hide();
                        $('#trkhCek'+i).hide();
                    }
                }
                else{
                    $('#bank'+i).removeAttr("disabled");
                    $('#bank'+i).show();
                    $('#caw'+i).show();
                    $('#rujukan'+i).show();
                    $('#trkh'+i).show();
                    $('#agensi'+i).show();
                    $('#noRuj'+i).show();
                    $('#trkhCek'+i).show();
                    $('#a'+i).show();
                    if(a.value == '0'){
                        $('#a'+i).hide();
                        $('#agensi'+i).hide();
                        $('#noRuj'+i).hide();
                        $('#trkhCek'+i).hide();
                    }
                }
            }else{
                $("#a"+i).hide();
                $('#bank'+i).attr("disabled", "true");
                $('#caw'+i).attr("disabled", "true");
                $('#rujukan'+i).attr("disabled", "true");
                $('#trkh'+i).attr("disabled", "true");
                $('#amaun'+i).attr("disabled", "true");
                $('#amaun'+i).val("0");
                        $('#agensi'+i).hide();
                        $('#noRuj'+i).hide();
                        $('#trkhCek'+i).hide();
                updtTot();
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
    
    function blinkFont()
{
  document.getElementById("bypdj").style.color="red"
  setTimeout("setblinkFont()",300);
}

function setblinkFont()
{
  document.getElementById("bypdj").style.color=""
  setTimeout("blinkFont()",300);
}
</script>