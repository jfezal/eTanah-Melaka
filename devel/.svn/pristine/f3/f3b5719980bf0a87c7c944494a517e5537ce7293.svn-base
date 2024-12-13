<%-- 
    Document   : pampasan
    Created on : 30-Aug-2010, 11:37:18
    Author     : nordiyana
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN" "http://www.w3.org/p/html4/loose.dlabel">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloths.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<%--<script type="text/javascript">
    $(document).ready(function() {
        $('#senaraiTuntutanKos0').val('T');
        $('#amaun0').val((${actionBean.jumCaraBayar}).toFixed(2));
        $('#amaunt0').val((${actionBean.jumCaraBayar1}).toFixed(2));

        $('#tuanTanahAmaunTotal').val((${actionBean.tuanTanahAmaunTotal}).toFixed(2));

        $('#jumCaraBayar').val((${actionBean.jumCaraBayar}).toFixed(2));
        $('#jumCaraBayar1').val((${actionBean.jumCaraBayar1}).toFixed(2));
        var bil = parseInt(${actionBean.bil});
        var amnt = parseInt(${actionBean.jumCaraBayar});
        var amnt1 = parseInt(${actionBean.jumCaraBayar1});
        if(amnt <= 0){
            $('#next').attr("disabled", "true");
            $('#tble').attr("disabled", "true");
        }
        if(amnt1 <= 0){
            $('#next').attr("disabled", "true");
            $('#tble').attr("disabled", "true");
        }
        for (var i = 1; i < bil; i++){
            $('#amaunt'+i).attr("disabled", "true");
            $('#amaunt'+i).val("0.00");
            $('#amaun'+i).attr("disabled", "true");
            $('#amaun'+i).val("0");
            $("#field"+i).hide();
        }

        var len = $(".daerah").length;
        for (var i=0; i<=len; i++){
            $('.hakmilik'+i).click( function() {
                alert($(this).text());
                window.open("${pageContext.request.contextPath}/pengambilan/gantiRugi?hakmilikDetails&idHakmilik="+$(this).text(),"eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
            });
        }
    });

        var len = $(".daerah").length;
        for (var i=0; i<=len; i++){
            $('.hakmilik'+i).click( function() {
                '${pageContext.request.contextPath}/pengambilan/gantiRugi?hakmilikDetail&idHakmilik='+idHakmilik;
                 $.get(url,
            function(data){
                $('.hakmilik').html(data);
            },'html');}); }});

    function pautanHakmilikDetail(idHakmilik)
    {
        if(confirm('Pautan ke maklumat tanah')) {
            var url = '${pageContext.request.contextPath}/pengambilan/gantiRugi?hakmilikDetail&idHakmilik='
                +idHakmilik;
            $.get(url,
            function(data){
                $('.hakmilik').html(data);
            },'html');}
    }
</script>
<script type="text/javascript">
    function addNew(idPermohonanPihak){
        window.open("${pageContext.request.contextPath}/pengambilan/gantiRugi?popup&idPermohonanPihak="+idPermohonanPihak,"eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
    }

    function addNew1(){
        window.open("${pageContext.request.contextPath}/pengambilan/gantiRugi?popupTptd","eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
    }

    function hakmilikValidation(){
        var id = document.getElementById('hakmilik');
        if(id.value == ""){
            alert("Sila Masukkan ID Hakmilik");
            $('#hakmilik').focus()
            return false;
        }
    }
    function removeSingle(idKos)
    {
        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pengambilan/gantiRugi?deleteSingle&idKos='
                +idKos;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');}
    }

    function updateSingle(idKos)
    {
        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pengambilan/gantiRugi?simpanSingle&idKos='
                +idKos;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');}
    }

    function popup(h){
        var url = '${pageContext.request.contextPath}/pengambilan/gantiRugi?popupedit&idKos='+h;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }

    function popup1(h){
        var url = '${pageContext.request.contextPath}/pengambilan/gantiRugi?popupedit1&idKos='+h;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }

    function calculate (t, i){
        var total = 0;
        var b = document.getElementById('bil'+i);
        if ((isNaN(b.value))||((b.value) =="")){
            alert("Nombor tidak sah");
            b.value = RemoveNonNumeric(b);
            $("#total").val("0.00");
            return;
        }
        total = parseFloat(b.value)*parseFloat(t);
        $("#total"+i).val(total.toFixed(2));
    }

    function calc(value){
        var deposit = value * 0.1;
        var baki = value - deposit;
        $("#deposit").val(deposit);
        $("#baki").val(baki);
        $("#deposit1").val(deposit);
        $("#baki1").val(baki);
    }

    function updateTotal (inputTxt,row){
        var total = 0;
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
        var yy = row - 1;
        if(yy>=0){
            var t = document.getElementById('jumCaraBayar');
            var bal = parseInt(${actionBean.jumlahCaj}) + parseInt(t.value);
            if(bal > 0)
                $('#amaun' + (row+1)).val(bal);
        }else{
            var t = document.getElementById('jumCaraBayar');
            var bal = parseInt(${actionBean.jumlahCaj}) + parseInt(a.value);
            if(bal > 0)
                $('#amaun' + (row+1)).val(bal);
        }
    }

    function updtTot(){
        var total = 0;
        for (var i=0; i<total.length(); i++){
            var a = document.getElementById('amaun' + i)
            total += parseFloat(a.value);
        }
        var t = document.getElementById('jumCaraBayar');
        t.value = total.toFixed(2);
    }

    function updateTotal1 (inputTxt,row){
        var total = 0;

        var a = document.getElementById('amaunt' + row)
        if ((isNaN(a.value))||((a.value) =="")){
            alert("Nombor tidak sah");
            inputTxt.value = RemoveNonNumeric(a);
            $('#jumCaraBayar1').val(a);
            return;
        }
        total += parseFloat(a.value);

        var t = document.getElementById('jumCaraBayar1');
        t.value = total.toFixed(2);
        inputTxt.value = parseFloat(inputTxt.value).toFixed(2);
        updtTot1();
        var yy = row - 1;
        if(yy>=0){
            var t = document.getElementById('jumCaraBayar1');
            var bal = parseInt(${actionBean.jumlahCaj}) + parseInt(t.value);
            if(bal > 0)
                $('#amaunt' + (row+1)).val(bal);
        }else{
            var t = document.getElementById('jumCaraBayar1');
            var bal = parseInt(${actionBean.jumlahCaj}) + parseInt(a.value);
            if(bal > 0)
                $('#amaunt' + (row+1)).val(bal);
        }
    }

    function updtTot1(){
        var total = 0;
        for (var i=0; i<total.length(); i++){
            var a = document.getElementById('amaunt' + i)
            total += parseFloat(a.value);
        }
        var t = document.getElementById('jumCaraBayar1');
        t.value = total.toFixed(2);
    }

        function RemoveNonNumeric( strString ){
            var strValidCharacters = "1234567890.";
            var strReturn = "0.00";
            var strBuffer = "0.00";
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

        function clearText1(id) {
            $("#"+id+" input:text").each( function(el) {
                $(this).val('');
            });

            $("#" + id +" select").each( function(el) {
                $(this).val('');
            });
            reset1();
        }

        function validate(){
            var d = ($('#date').val());
            var r = ($('#noResit').val());
            var t = parseFloat($('#jumCaraBayar').val());
            var u = parseFloat(${actionBean.jumCaraBayar});
            var bil = parseInt(${actionBean.bil});
            if(u > t){
                var bal = u -t;
                alert("Bayaran anda kurang RM "+(bal).toFixed(2));
                return false;
            }
            if((d == "")||(r == "")){
                var bal = u -t;
                alert("Sila Masukkan di medan yang bertanda *");
                return false;
            }
            for (var i = 0; i < bil; i++){
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

        function reset1(){
            $('#senaraiTuntutanKos0').val('T');
            $('#amaun0').val((${actionBean.jumCaraBayar}).toFixed(2));
            $('#jumCaraBayar').val((${actionBean.jumCaraBayar}).toFixed(2));
            $("#bank0").hide();
            $("#caw0").hide();
            $("#rujukan0").hide();
            $("#trkh0").hide();
            $("#field0").hide();
            var bil = parseInt(${actionBean.bil});
            for (var i = 1; i < bil; i++){
                $('#bank'+i).show();
                $('#caw'+i).show();
                $('#rujukan'+i).show();
                $('#trkh'+i).show();
                $("#field"+i).hide();
                $('#bank'+i).attr("disabled", "true");
                $('#caw'+i).attr("disabled", "true");
                $('#rujukan'+i).attr("disabled", "true");
                $('#trkh'+i).attr("disabled", "true");
                $('#amaun'+i).attr("disabled", "true");
                $('#amaun'+i).val("0");
            }
        }

       function validateForm(){

           if(document.form2.idHakmilik.value=="")
           {
               alert("Sila isikan ID Hakmilik");
               return false;
           }
           return true;

       }

       function checking(inputTxt){
           var a = $("#idHakmilik").val();
           $.get("${pageContext.request.contextPath}/hasil/check_idhakmilik_accountNo?doCheckHakmilik&idHakmilik=" + a,
           function(data){
               if(data == '1'){
                   $("#msg" + inputTxt).html("OK");
               }
               else if(data =='0'){
                   $("#idHakmilik").val("");
                   alert("ID Hakmilik " + a + " tidak wujud!");
    return false;
                } else if(data =='3'){
                    $("#idHakmilik").val("");
                    alert("ID Hakmilik " + a + " masih belum didaftarkan.");
                }else{
                    entsub();
                }
            });


        }

function ajaxLink(link, update) {
$.get(link, function(data) {
    $(update).html(data);
    $(update).show();
});
return false;
}

function refreshPageganti(){
    alert("---refreshPageganti--");
    var url = '${pageContext.request.contextPath}/pengambilan/gantiRugi?refreshpage';
    $.get(url,
    function(data){
        $('#page_div').html(data);
    },'html');
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

function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }
</script>
--%>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.pengambilan.PampasanActionBean" name="form2">
    <s:errors/>
    <s:messages/>
    <fieldset>
                  <legend align="center">
                <b>Maklumat Pampasan</b>
            </legend><br/>
            <div  align="center">
                <table>
                    <tr>
                        <td>Perbicaraan Pengambilan No: </td>
                        <td>0101
                            <c:if test="${actionBean.hakmilikPerbicaraan.idPerbicaraan ne null}">
                                 ${actionBean.hakmilikPerbicaraan.idPerbicaraan}
                             </c:if>
                             <c:if test="${actionBean.hakmilikPerbicaraan.idPerbicaraan eq null}">
                                 <c:out value="Tiada Maklumat" />
                             </c:if>
                          </td>
                    </tr>
                    <tr>
                        <td>Tarikh Perbicaraan: </td>
                        <td>13/10/2010
                            <c:if test="${actionBean.hakmilikPerbicaraan.tarikhBicara ne null}">
                                <fmt:formatDate pattern="dd-MM-yyyy" value="${actionBean.hakmilikPerbicaraan.tarikhBicara}" />
                             </c:if>
                             <c:if test="${actionBean.hakmilikPerbicaraan.tarikhBicara eq null}">
                                 <c:out value="Tiada Maklumat" />
                             </c:if>
                          </td>
                    </tr>
                    <%--<br>
                    <tr>
                        <td><b>Tanah :</b>
                        <table>
                        <tr>
                            <td>Item :<s:text name="tanah" size="20" id="lain" value="Tanah" disabled="true"/></td>
                        <td>&nbsp;&nbsp;RM :<s:text name="rmtanah" size="20" id="lain"   onkeyup="validateNumber(this,this.value);"/></td>
                        </tr>
                        <tr><td colspan="3">
                         <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="5" cellpadding="0" cellspacing="0"
                               requestURI="/pengambilan/penerimaan_notis" id="line">
                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                        <display:column  title="Item"/>
                        <display:column  title="Amaun (RM)"/>
                        <display:column title="Kemaskini">
                            <div align="center">
                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                            id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popup('${actionBean.tanahRizabList[line_rowNum-1].idTanahRizabPermohonan}');"/>
                            </div>
                        </display:column>
                        </display:table>
                            </td>
                        </tr>
                    <br>
                    <br>
                        <tr>
                        <td><b>Jumlah :RM</b></td>
                        <td>&nbsp;&nbsp;</td>
                        </tr>
                     </table>
                        </td>
                    </tr>
                    <br>
                    <br>--%>
                    <tr>
                        <td><b>Bangunan :</b>
                        <table>
                        <tr>
                        <td>Item :<s:text name="bangunan" size="20" id="lain"/></td>
                        <td>&nbsp;&nbsp;RM :<s:text name="rmbangunan" size="20" id="lain"   onkeyup="validateNumber(this,this.value);"/></td>
                        <td><s:button name="simpanPopup1" id="simpan" value="Simpan" class="btn" onclick="save(this.name, this.form);"/></td>
                        </tr>
                        <tr><td colspan="3">
                         <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="5" cellpadding="0" cellspacing="0"
                               requestURI="/pengambilan/penerimaan_notis" id="line">
                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                        <display:column  title="Item"/>
                        <display:column  title="Amaun (RM)"/>
                        <display:column title="Kemaskini">
                            <div align="center">
                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                            id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="<%--popup('${actionBean.tanahRizabList[line_rowNum-1].idTanahRizabPermohonan}');--%>"/>
                            </div>
                        </display:column>
                        </display:table>
                            </td>
                        </tr>
                         <br>
                         <br>
                        <tr>
                        <td><b>Jumlah :RM</b></td>
                        <td>&nbsp;&nbsp;</td>
                        </tr>
                        </table>
                        </td>

                    </tr>
                    <br>
                    <br>
                    <tr>
                        <td><b>Lain-Lain :</b>
                        <table>
                        <tr>
                        <td>Item :<s:text name="nLain" size="20" id="lain"/></td>
                        <td>&nbsp;&nbsp;RM :<s:text name="rmlain" size="20" id="lain"   onkeyup="validateNumber(this,this.value);"/></td>
                        <td><s:button name="simpanPopup1" id="simpan" value="Simpan" class="btn" onclick="save(this.name, this.form);"/></td>
                        </tr>
                        <tr><td colspan="3">
                         <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="5" cellpadding="0" cellspacing="0"
                               requestURI="/pengambilan/penerimaan_notis" id="line">
                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                        <display:column  title="Item"/>
                        <display:column  title="Amaun (RM)"/>
                        <display:column title="Kemaskini">
                            <div align="center">
                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                            id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="<%--popup('${actionBean.tanahRizabList[line_rowNum-1].idTanahRizabPermohonan}');--%>"/>
                            </div>
                        </display:column>
                        </display:table>
                            </td>
                        </tr>
                        <br>
                        <br>
                        <tr>
                        <td><b>Jumlah :RM</b></td>
                        <td>&nbsp;&nbsp;</td>
                        </tr>
                        </table>
                    </tr>
                </table>

            <p align="center">
                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="5" cellpadding="0" cellspacing="0"
                               requestURI="/pengambilan/penerimaan_notis" id="line">
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column  title="Tuan Tanah"/>
                    <display:column  title="Item Nilaian (RM)" />
                    <display:column  title="Jumlah Keseluruhan(RM)"/>
                    <display:column title="Kemaskini">
                    <div align="center">
                    <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                    id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="<%--popup('${actionBean.tanahRizabList[line_rowNum-1].idTanahRizabPermohonan}');--%>"/>
                    </div>
                    </display:column>
                        <display:column title="Bahagian Pemberian">
                            <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                              <c:set value="${senarai.syerPembilang}" var="a"/>
                              <c:set value="${senarai.syerPenyebut}" var="b"/>
                              <%--<c:set value=" ${actionBean.hakmilikPerbicaraan.penilaiAmaun}" var="c"/>--%>
                              <%--<c:out value="${actionBean.hakmilikPerbicaraan.penilaiAmaun}"/>
                              ${actionBean.hakmilikPerbicaraan.penilaiAmaun}--%>
                             <% try{ %>
                              <br> RM <fmt:formatNumber pattern="#,##0.00" value="${a/b*c}"/>
                             <%-- <br>RM<c:out value="${a/b*c}"/>--%>
                              <%}catch(Exception e){
                                    e.printStackTrace();
                                    }
                                %>
                            </c:forEach>
                        </display:column>
                </display:table>

        </fieldset>


</s:form>

