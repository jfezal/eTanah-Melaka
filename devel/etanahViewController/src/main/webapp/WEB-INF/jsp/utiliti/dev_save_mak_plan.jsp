
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt"    prefix="c" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" rel="stylesheet"
      href="${pageContext.request.contextPath}/pub/styles/jquery.tooltip.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script>

    function validateNumber(index, value) {
    //if it is character, then remove it..
    var content = document.getElementById("luas" + index).value;
    if (isNaN(content)) {
    document.getElementById("luas" + index).value = RemoveNonNumeric(content);
    }
    if (content !== ''){
    var totalLuas = 0;
    var bil = $("#bil").val();
    for (var i = 0; i < bil; i++){ //alert("MASUK FOR"+i);
    var luas = $("#luas" + i).val();
    totalLuas = (totalLuas + (parseFloat(Number(luas))));
    $("#jumLuas").val(totalLuas);
    $("#kodUOMTotal").val("M");
    // alert("total:"+totalLuas);
    }
    }
    }

    function validateNumberTotal(index) {
    //if it is character, then remove it..
    var content = document.getElementById("jumLuas").value;
    if (isNaN(content)) {
    document.getElementById("jumLuas").value = RemoveNonNumeric(content);
    return;
    }
    }

    function RemoveNonNumeric(strString)
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
    if (strValidCharacters.indexOf(strBuffer) > - 1)
    {
    strReturn += strBuffer;
    }
    }
    return strReturn;
    }


    function validateSave(event, frm) {
    var bil = $("#bil").val();
    if (validateLuas(bil)){//alert("masuk validate");
    var url = "${pageContext.request.contextPath}/utiliti/validateHakmilik?saveLuasHakmilik&bil=" + bil;
    //alert(url);
    if (confirm("Adakah anda pasti untuk Simpan?")){//alert(url+"\nform:"+frm);
    frm.action = url;
    frm.submit();
    }
    $('#penyerah').removeAttr("disabled");
    }
    //}
    }
    function validateSave1(event, f, idHakmilik) {
    alert("idHakmilik>>>>" + idHakmilik);
    if (validateLuas(idHakmilik)){
    //alert("11111");                     
    $.blockUI({
    message: $('#displayBox'),
            css: {
            top: ($(window).height() - 50) / 2 + 'px',
                    left: ($(window).width() - 50) / 2 + 'px',
                    width: '50px'
            }
    });
    var q = $(f).formSerialize();
    var url = f.action + '?' + event + idHakmilik;
    $.post(url, q,
            function(data) {
            $('#page_div', this.document).html(data);
            $.unblockUI();
            }, 'html');
    return true;
    }
    }

    function validateLuas(bil) {
    var luasTotal = $("#jumLuas").val();
    var unitLuasTotal = $("#kodUOMTotal").val();
    for (var i = 0; i < bil; i++){

    var luas = $("#luas" + i).val();
    var unitLuas = $("#kodUOM" + i).val();
    var pelanPA = $("#pelanPA" + i).val();
    var tarikhSah = $("#tarikhSah" + i).val();
    //alert("luas"+luas);
    //alert("unitLuas"+unitLuas);

    if (unitLuas === '') {
    alert('Sila masukkan unit luas');
    return false;
    }

    if (luas === '') {
    alert('Sila masukkan luas');
    return false;
    }

    if (pelanPA === '') {
    alert('Sila masukkan No Pelan PA');
    return false;
    }

    if (tarikhSah === '') {
    alert('Sila masukkan Tarikh Sah');
    return false;
    }
    if (luasTotal === '') {
    alert('Sila masukkan Juamlah Luas Keseluruhan');
    return false;
    }
    if (unitLuasTotal === '') {
    alert('Sila masukkan Unit Luas Juamlah Luas Keseluruhan');
    return false;
    }
    }
    return true;
    }

    function filterTotal(kodUOM){
    // alert("kodUOM:"+kodUOM);
    if (kodUOM !== ''){
    var totalLuas = 0;
    var bil = $("#bil").val();
    for (var i = 0; i < bil; i++){
    //alert("MASUK FOR"+i);
    var luas = $("#luas" + i).val();
    var unitLuas = $("#kodUOM" + i).val();
    if (unitLuas === 'H')
    {
    luas = luas * 10000;
    }
    totalLuas = (totalLuas + (parseFloat(Number(luas))));
    $("#jumLuas").val(totalLuas);
    $("#kodUOMTotal").val("M");
    //alert("total:"+totalLuas);
    }
    return true;
    }

    }

    function filterUnitLuas(kodUOM){
    if (kodUOM !== ''){
    $("#kodUOMTotal").val(kodUOM);
    return true;
    }
    }

    function returnBack(event, frm){

    var url = "${pageContext.request.contextPath}/utiliti/validateHakmilik?back";
    frm.action = url;
    frm.submit();
    }

    function popupPenyerah() {
    var hakmilikArry = $("#hakmilikArry").val();
    var url = "${pageContext.request.contextPath}/utiliti/validateHakmilik?popupPenyerah&hakmilikArry=" + hakmilikArry;
    window.open(url, "eTanah", "status=1,toolbar=0,location=1,menubar=0,scrollbars=yes,resizable=1,width=900,height=600");
    }


</script>

<script type="text/javascript">
    $(document).ready(function() {

    $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    });
    $('#penyerah').attr("disabled", "true");
    var hakmilikArry = [];
    var bil = $("#bil").val();
    $("#penyerah").click(function(){

    for (var i = 0; i < bil; i++){
    hakmilikArry += [$("#idHakmilik${i}").val()];
    hakmilikArry = hakmilikArry + ",";
    }
    alert("hakmilikArry:" + hakmilikArry);
    $("#hakmilikArry").val(hakmilikArry);
    }

    $('input:text').each(function() {
    $(this).focus(function() {
    $(this).addClass('focus')
    });
    $(this).blur(function() {
    $(this).removeClass('focus')
    });
    });
    }

function cetak(f,id1){

        var form = $(f).formSerialize();
        var report = 'DEVSP_PA.rdf';
        

        var url = "reportName="+report+"%26p_id_msp="+id1;

        window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url="+url, "eTanah",
        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
     }

</script>


<s:messages />
<s:errors />

<s:form action="/utiliti/validateHakmilik" id="validateHakmilik" name="validateHakmilik">
    <fieldset class="aras1">
        <legend>Maklumat Pelan</legend>
        <div>
            <table cellpadding="0" cellspacing="0" align="center" class="tablecloth" datapagesize="20">

                <tr>
                    <th>ID HAKMILIK</th>
                    <th>JUMLAH LUAS</th>
                    <th>KOD LUAS</th>
                    <th>NO PELAN PA</th>
                    <th>TARIKH SAH</th>
                </tr> 
                <c:set var="i" value="0"/>
                <c:forEach items="${actionBean.list}" var="b" >            
                    <tr>

                        <td>${b}<s:hidden name="idHakmilik${i}" id="idHakmilik${i}" value="${b}"/></td>
                        <td>
                            <s:text name="luas${i}" id="luas${i}" size="15" class="normal_text" formatPattern="0.0000" value="" onkeyup="validateNumber(${i},this.value);"/>
                        </td>
                        <td>
                            <s:select name="kodUOM${i}" id="kodUOM${i}" value="" style="" onchange="filterTotal(this.value);">
                                <s:option label="--Sila Pilih--"  value="" />             
                                <s:option label="HEKTAR"  value="H" />  
                                <s:option label="METER PERSEGI"  value="M" />                                   
                            </s:select>
                        </td>
                        <td>
                            <s:text name="pelanPA${i}" id="pelanPA${i}" size="20" class="normal_text" value=""/>
                        </td>
                        <td>
                            <s:text name="tarikhSah${i}" id="tarikhSah${i}" class="datepicker" size="20" formatPattern="dd/MM/yyyy"/>
                            <!--                    <td>
                                                    <p align="center">
                                                        <img name="saveLuasHakmilik" alt='Klik Untuk Simpan' border='0' src='$//{pageContext.request.contextPath}/images/save.gif'
                                                             onclick="validateSave(this.name, document.forms.validateHakmilik,'$//{b}');"  onmouseover="this.style.cursor='pointer';">
                                                    </p>
                                                </td>-->

                    </tr>
                    <c:set var="i" value="${i+1}" />
                </c:forEach>

            </table>
        </div>
        &nbsp;
        <p><label>Jumlah Luas : </label>
            <s:text name="jumLuas" id="jumLuas" size="15" class="normal_text" formatPattern="0.0000" value="" onkeyup="validateNumberTotal(this,this.value);"/>

            <s:select name="kodUOMTotal" id="kodUOMTotal" value="" style="" onchange="filterUnitLuas(this.value);">
                <s:option label="--Sila Pilih--"  value="" />             
                <s:option label="HEKTAR"  value="H" />  
                <s:option label="METER PERSEGI"  value="M" />                                   
            </s:select>                    
        </p>
        &nbsp;
        <p>
            <!-- <//s:button name="penyerah" id="penyerah" value="Penyerah" class="btn" onclick="popupPenyerah()"/> -->
            <s:hidden name="bil" value="${i}" id="bil" />
            <s:hidden name="hakmilikArry" id="hakmilikArry" />
        </p>
    </fieldset>
    <fieldset class="aras1">
        <legend>Maklumat Penyerah</legend>
<p>
                        <label>Nama :</label>
                        <s:text name="nama" id="nama"/>
                    </p>
                     <p>
                        <label>No Telefon :</label>
                        <s:text name="noTel" id="noTel"/>
                    </p>

        <p>

        </p>
    </fieldset>
    <s:button name="save" value="Simpan" class="btn" onclick="validateSave(this.name, document.forms.validateHakmilik);"/>
    <s:button name="back" id="back" value="Batal" class="btn" onclick="returnBack(this.name, document.forms.validateHakmilik)"/>

</s:form>
