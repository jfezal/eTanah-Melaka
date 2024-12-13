<%--
    Document   : perTuanTanahGantiRugiReport
    Created on : Aug 23, 2010, 10:07:24 AM
    Author     : Massita
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
<script type="text/javascript">
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
    <%--return false;--%>
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

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.pengambilan.perbicaraanKosGantiRugiActionBean" name="form2">
    <s:errors/>
    <s:messages/>
    <%--<c:if test="${pt}">--%>
    <div  id="hakmilik_details">
        <div class="subtitle">
            <s:errors field="amaun"/>
            <s:errors field="amaunt"/>

                <br />
                <div class="subtitle">
                <fieldset class="aras1">
                    <legend>Maklumat Tuan Tanah Mengikut Jumlah Pampasan</legend>
                    <div class="content" align="center">

                <display:table class="tablecloth" name="${actionBean.senaraiHakmilikPermohonan}" cellpadding="0" cellspacing="0"
                                                id="tbl1">
                    <display:column title="No" sortable="true">${tbl1_rowNum}</display:column>
                    <display:column title="No Hakmilik">
                        ${tbl1.hakmilik.kodHakmilik.kod} ${tbl1.hakmilik.noHakmilik}
                    </display:column>
                    <display:column title="Tuan Tanah">
                        <c:forEach items="${tbl1.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                            <br>
                            ${senarai.pihak.nama}
                            &nbsp;&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        </c:forEach>

                        <display:column title="Syer yang dimiliki" style="text-align=center">
                            <c:forEach items="${tbl1.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                                <br />
                                <c:out value="${senarai.syerPembilang}/${senarai.syerPenyebut}"/>
                                &nbsp;&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            </c:forEach>
                        </display:column>
                    </display:column>
                    <display:column title="Amaun Yang Diluluskan (RM)" style="text-align:right">
                        <c:forEach items="${actionBean.sebenarAmaunList[tbl1_rowNum-1]}" var="senarai">
                                    <br />
                                    <c:out value="${senarai}"/>
                                    &nbsp;&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        </c:forEach>
                    </display:column>
                </display:table>
                    </div>
                    <div align="right">
                    </div>
                </fieldset>
            </div>
        <c:if test="${showMaklumatPemilik}">
        <s:messages/>
        <br />
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>Maklumat Tuan Tanah</legend>
                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.senaraiHakmilikPermohonan}"
                                       pagesize="10" cellpadding="0" cellspacing="0" requestURI="/pengambilan/gantiRugi" id="line">
                            <display:column title="Bil" sortable="true" style="text-align:center">${line_rowNum}</display:column>
                            <display:column title="No Hakmilik" style="vertical-align:center">
                            ${line.hakmilik.kodHakmilik.kod} ${line.hakmilik.noHakmilik}
                            </display:column>
                            <display:column title="Senarai Tuan Tanah" style="vertical-align:center" class="popup hakmilik${line_rowNum}">
                                <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                                 <c:out value="${senarai.pihak.nama}"/><br />
                                </c:forEach>
                            </display:column>
                        </display:table>
                    </div>
                </fieldset>
            </div>
        </c:if>


    </div>

    <div>
        <fieldset class="aras1" id="locate"><br/>

            <br/>
            <br/>
            <br/>

            <c:if test="${showTuanTanah}">
                <legend>Maklumat Tuan Tanah</legend>
                <p align="center">
                    <display:table class="tablecloth" name="${actionBean.hakmilik}" cellpadding="0" cellspacing="0"
                                   requestURI="/pengambilan/gantiRugi" id="line">
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column title="No Hakmilik" style="vertical-align:center">
                            ${line.kodHakmilik.kod} ${line.noHakmilik}
                    </display:column>
                    <display:column title="Tuan Tanah">
                        <c:forEach items="${actionBean.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                            <br>
                            <s:link beanclass="etanah.view.stripes.pengambilan.perbicaraanKosGantiRugiActionBean"
                                    event="pihakDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                                <s:param name="idPihak" value="${senarai.pihak.idPihak}"/>${senarai.pihak.nama}
                            </s:link>
                            &nbsp;&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        </c:forEach>
                    </display:column>
                    <display:column title="Syer yang dimiliki">
                            <c:forEach items="${actionBean.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                                <br />
                                <c:out value="${senarai.syerPembilang}/${senarai.syerPenyebut}"/>
                                &nbsp;&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            </c:forEach>
                     </display:column>
                    <display:column title="Amaun Yang Diminta (RM)" style="text-align:right">
                                <c:forEach items="${actionBean.tuanTanahAmaun}" var="senarai">
                                    <br />
                                    <c:out value="${senarai}"/>
                                    &nbsp;&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                </c:forEach>
                    </display:column>

                    <display:column title="Amaun Yang Diluluskan (RM)" style="text-align:right">
                        <c:forEach items="${actionBean.amaunSebenarTotalList}" var="senarai">
                                    <br />
                                    <c:out value="${senarai}"/>
                                    &nbsp;&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        </c:forEach>
                    </display:column>

                    <display:footer>
                        <tr>
                            <td></td>
                            <td></td>
                            <td><div align="left"><b>Jumlah Perlu Dibayar(RM):</b></div></td>
                            <td><div align="right">
                                <s:text name="jumCaraBayar1" value="jumCaraBayar1" formatPattern="#,##0.00" style="text-align:right" readonly="true"/></div></td>

                            <td><div align="right"><s:text name="amaunSebenarTuanTotal" value="amaunSebenarTuanTotal" formatPattern="#,##0.00" style="text-align:right" readonly="true"/>
                                    </div></td>

                        </tr>
                    </display:footer>
                </display:table>
            </p>
            </c:if>


            <br/>
            <br/>
            <br/>

            <c:if test="${actionBean.permohonanPihak ne null}">
            <legend>Maklumat Tuntutan Kos Gantirugi</legend>
            <p align="center">
                <display:table class="tablecloth" name="${actionBean.hakmilik}" cellpadding="0" cellspacing="0"
                               requestURI="/pengambilan/gantiRugi" id="line">
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column title="No Hakmilik" style="vertical-align:center">
                            ${line.kodHakmilik.kod} ${line.noHakmilik}
                    </display:column>
                    <display:column title="Tuan Tanah">
                        ${actionBean.permohonanPihak.pihak.nama}
                    </display:column>
                    <display:column title="Jenis Kerosakan" style="text-align:left">
                        <c:forEach items="${actionBean.permohonanTuntutanKosList}" var="senarai">
                            <br/>
                            <c:out value="${senarai.item}"/>
                            <br/>
                        </c:forEach>
                    </display:column>
                    <display:column title="Amaun Yang Diminta (RM)" style="text-align:right">
                        <c:forEach items="${actionBean.permohonanTuntutanKosList}" var="senarai">
                            <br/>
                            <c:out value="${senarai.amaunTuntutan}"/>
                            <br/>
                        </c:forEach>
                    </display:column>

                    <display:column title="Amaun Yang Diluluskan (RM)" style="text-align:right">
                        <c:forEach items="${actionBean.permohonanTuntutanKosList}" var="senarai">
                            <br/>
                            <c:out value="${senarai.amaunSebenar}"/>
                            <br/>
                        </c:forEach>
                    </display:column>

                    <display:column title="Hapus">
                        <div align="center">
                        <c:forEach items="${actionBean.permohonanTuntutanKosList}" var="senarai">
                            <br/>
                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                 id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeSingle('${senarai.idKos}');" />
                            <br/>
                         </c:forEach>
                        </div>
                    </display:column>
                    <display:footer>
                    <tr>
                        <td colspan="4"><div align="right"><b>Jumlah Perlu Dibayar(RM):</b></div></td>
                        <td><div align="right">
                            <s:text name="jumCaraBayar" style="text-align:right" readonly="true" formatPattern="#,##0.00" /></div></td>
                                <td><div align="right">
                                <s:text name="amaunSebenarTotal" style="text-align:right" readonly="true" formatPattern="#,##0.00" />

                                    </div></td>

                    </tr>
                    </display:footer>
            </display:table>
               </p>

                    <br />
                    <s:hidden name="idPihak" value="${actionBean.pihak.idPihak}"/>
                    <div align="center">
                        <s:button name="new" value="Tambah" id="new" class="btn" onclick="addNew(${actionBean.permohonanPihak.idPermohonanPihak});" />
                    </div>
            </c:if>
        </fieldset>
    </div>
    </div>
</s:form>





