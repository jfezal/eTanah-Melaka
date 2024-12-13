<%-- 
    Document   : keputusan_perbicaraan_kosgantirugi1
    Created on : Apr 27, 2010, 3:32:06 PM
    Author     : massita
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

      <%--  var len = $(".daerah").length;
        for (var i=0; i<=len; i++){
            $('.hakmilik'+i).click( function() {
                '${pageContext.request.contextPath}/pengambilan/gantiRugi?hakmilikDetail&idHakmilik='+idHakmilik;
                 $.get(url,
            function(data){
                $('.hakmilik').html(data);
            },'html');}); }});--%>

  <%--  function pautanHakmilikDetail(idHakmilik)
    {
        if(confirm('Pautan ke maklumat tanah')) {
            var url = '${pageContext.request.contextPath}/pengambilan/gantiRugi?hakmilikDetail&idHakmilik='
                +idHakmilik;
            $.get(url,
            function(data){
                $('.hakmilik').html(data);
            },'html');}
    }--%>
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

<%--    function hakmilikValidation(){
        var id = document.getElementById('hakmilik');
        if(id.value == ""){
            alert("Sila Masukkan ID Hakmilik");
            $('#hakmilik').focus()
            return false;
        }
    }--%>
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
       <%-- <fieldset class="aras1" id="locate">--%>
        <%--<fieldset class="aras1">--%>
           <%-- <legend>Keputusan Perbicaraan Kos Ganti Rugi</legend>--%>
            <s:errors field="amaun"/>
            <s:errors field="amaunt"/>
            <%--<div class="instr-fieldset">
                <font color="red">*</font>Klik butang carian untuk masukkan id hakmilik
            </div>--%>

            <%--<div class="content" align="left">
                <p>
                    <label for=" ID Hakmilik :"><font color="red">*</font> ID Hakmilik :</label>
                    <s:text name="idHakmilik" size="30"/>
                    <s:button name="semakIdHakmilik" value="Cari" class="btn"
                              onclick="if(validateForm())doSubmit(this.form, this.name, 'page_div');"/>
                    <s:reset name="" value="Isi Semula" class="btn"/>
                   
                </p>
            </div>--%>
       <%-- </fieldset>--%>
   <%-- </c:if>--%>

     <%-- <c:if test="${senaraiHakmilikPermohonan ne null}">--%>
                <br />
                <div class="subtitle">
                <fieldset class="aras1">
                    <legend>Maklumat Tanah</legend>
                    <div class="content" align="center">
                               <%-- <display:table class="tablecloth" name="${actionBean.senaraiHakmilikPermohonan}" cellpadding="0" cellspacing="0"
                                                id="tbl1">
                                    <display:column title="Bil" sortable="true" style="vertical-align:center">${tbl1_rowNum}</display:column>                                    
                                    <display:column title="ID Hakmilik">
                                        <s:link beanclass="etanah.view.stripes.pengambilan.perbicaraanKosGantiRugiActionBean"
                                                event="hakmilikDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                                            <s:param name="idHakmilik" value="${tbl1.hakmilik.idHakmilik}"/>${tbl1.hakmilik.kodHakmilik.kod} ${tbl1.hakmilik.noHakmilik}
                                        </s:link>
                                    </display:column>
                                     <display:column title="Nombor Lot" style="vertical-align:baseline">
                                     ${tbl1.hakmilik.noLot}
                                    </display:column>
                                    <display:column title="Daerah" property="hakmilik.daerah.nama" class="daerah" style="vertical-align:baseline"/>
                                    <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" style="vertical-align:baseline"/>
                                    <display:column title="Luas yang diambil" style="vertical-align:baseline"><fmt:formatNumber pattern="#,##0.0000" value="${tbl1.hakmilik.luas}"/>&nbsp;${tbl1.hakmilik.kodUnitLuas.nama}</display:column>
                                    <display:column title="Luas sebenar" style="vertical-align:baseline"><fmt:formatNumber pattern="#,##0.0000" value="${tbl1.hakmilik.luas}"/>&nbsp;${tbl1.hakmilik.kodUnitLuas.nama}</display:column>
                                </display:table>
                     </div>

                    <p align="center">--%>

                <display:table class="tablecloth" name="${actionBean.senaraiHakmilikPermohonan}" cellpadding="0" cellspacing="0"
                                                id="tbl1">
                    <display:column title="No" sortable="true">${tbl1_rowNum}</display:column>
                    <display:column title="No Hakmilik">
                        <s:link beanclass="etanah.view.stripes.pengambilan.perbicaraanKosGantiRugiActionBean"
                                event="hakmilikDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                            <s:param name="idHakmilik" value="${tbl1.hakmilik.idHakmilik}"/>${tbl1.hakmilik.kodHakmilik.kod} ${tbl1.hakmilik.noHakmilik}<%--,  ${tbl1.hakmilik.idHakmilik}--%>
                        </s:link>
                    </display:column>
                    <display:column title="Nombor Lot" style="vertical-align:baseline">
                        ${tbl1.hakmilik.noLot}
                    </display:column>
                    <display:column title="Daerah"  class="daerah" style="vertical-align:baseline">${tbl1.hakmilik.daerah.nama}</display:column>
                    <display:column  title="Bandar/Pekan/Mukim" style="vertical-align:baseline">${tbl1.hakmilik.bandarPekanMukim.nama}</display:column>
                    <display:column title="Luas yang diambil" style="vertical-align:baseline"><fmt:formatNumber pattern="#,##0.0000" value="${tbl1.luasTerlibat}"/>&nbsp;${tbl1.hakmilik.kodUnitLuas.nama}</display:column>
                    <display:column title="Luas sebenar" style="vertical-align:baseactionBean"><fmt:formatNumber pattern="#,##0.0000" value="${tbl1.hakmilik.luas}"/>&nbsp;${tbl1.hakmilik.kodUnitLuas.nama}</display:column>
                    <display:column title="Amaun Yang Dituntut (RM)" style="text-align:right">
                            <c:out value="${actionBean.hakmilikAmaunList[tbl1_rowNum-1]}"/>
                    </display:column>

                    <display:column title="Amaun Yang Diluluskan (RM)" style="text-align:right">
                        <c:out value="${actionBean.hakmilikSebenarList[tbl1_rowNum-1]}"/>
                    </display:column>

                </display:table>
                    </div>

                </fieldset>
            </div>
        <%--</c:if>--%>

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

        <%--<c:if test="${pt}">
            <br />
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>Maklumat Tuan Tanah</legend>
                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.senaraiHakmilikPermohonan}"
                                       pagesize="10" cellpadding="0" cellspacing="0" requestURI="/pengambilan/gantiRugi" id="line">
                            <display:column title="Bil" sortable="true" style="text-align:left">${line_rowNum}</display:column>
                            <display:column title="No Hakmilik" style="vertical-align:baseline">
                            ${tbl1.hakmilik.kodHakmilik.kod} ${tbl1.hakmilik.noHakmilik}
                            </display:column>
                            <display:column title="Senarai Tuan Tanah" style="text-align:center">
                                <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                                    <c:out value="${senarai.pihak.nama}"/>
                                </c:forEach>
                            </display:column>
                        </display:table>
                    </div>
                </fieldset>
            </div>
        </c:if>--%>

            <%--<c:if test="${pt}">
                <br />

                <div class="subtitle">
                    <fieldset class="aras1" id="locate">
                        <legend>Maklumat Tuntutan Kos Ganti Rugi</legend>
                        <div class="instr-fieldset">
                            <font color="red">*</font>Klik butang tambah untuk tambah maklumat permohonan kos ganti rugi
                        </div>
                        <s:errors field="amaun"/>
                        <div class="content" align="center" id="tble">
                            <display:table class="tablecloth" name="${actionBean.senaraiTuntutanKos}"
                                           pagesize="10" cellpadding="0" cellspacing="0" requestURI="/pengambilan/gantiRugi" id="line">
                                <display:column title="Bil" sortable="true" style="text-align:center">${line_rowNum}</display:column>
                                <display:column property="idKos" title="Id Tuntutan Kos" style="text-align:left"></display:column>
                                <display:column property="item" title="Jenis Kerosakan" style="text-align:left" >
                                    <s:text name="senaraiTuntutanKos[${line_rowNum - 2}].item" size="40"/>
                                </display:column>

                                <display:column title="Amaun Yang Dituntut (RM)" property="amaunTuntutan" style="text-align:right">
                                    <div align="center"><s:text name="senaraiTuntutanKos[${line_rowNum - 1}].amaunTuntutan"
                                    style="text-align:right" size="20" class="number" onblur="javascript:updateTotal(this,${line_rowNum - 1});" id="amaun${line_rowNum - 1}"/>
                                    </div>
                                </display:column>
                                <display:column title="Kemaskini">
                                    <div align="center">
                                        <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                             id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popup('${line.idKos}')"/>
                                    </div>
                                </display:column>
                                <display:column title="Hapus">
                                    <div align="center">
                                        <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                             id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeSingle('${actionBean.senaraiTuntutanKos[line_rowNum-1].idKos}');" />
                                    </div>
                                </display:column>

                                <display:footer>

                                   
                                        <tr>
                                            <td colspan="3"><div align="right"><b>Jumlah Perlu Dibayar(RM):</b></div></td>
                                            <td><div align="center"><input name="jumCaraBayar" value="0.00" id="jumCaraBayar" size="20"
                                                                           class="number" readonly="true"/></div></td></tr>

                               
                                </display:footer>
                            </display:table >
                        </c:if>
                        <br />
                        <s:button name="new" value="Tambah" id="new" class="btn" onclick="addNew();"/>
                    </div>
                </fieldset>
            </div>
        </c:if>
    </div>

           <c:if test="${detail}">
            <br />
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>Maklumat Tuan Tanah</legend>
                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.senaraiHakmilikPermohonan}"
                                       pagesize="10" cellpadding="0" cellspacing="0" requestURI="/pengambilan/gantiRugi" id="line">
                            <display:column title="Bil" sortable="true" style="text-align:left">${line_rowNum}</display:column>
                            <display:column title="Senarai Tuan Tanah" style="text-align:center">
                                <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                                    <c:out value="${senarai.pihak.nama}"/>
                                </c:forEach>
                            </display:column>
                            <display:column property="noLot" title="No Lot Ukur" style="text-align:center"/>
                            <display:column title="Luas Dikehendaki" sortable="true" property="luasTerlibat">
                            </display:column>
                            <display:column title="Jenis Kepentingan" style="text-align:center">
                                <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai1">
                                    <c:out value="${senarai1.jenis.kod}"/>
                                </c:forEach>
                            </display:column>
                        </display:table>
                    </div>
                </fieldset>
            </div>
           </c:if>

        <c:if test="${detail}">
            <div class="subtitle">
                <fieldset class="aras1" id="locate">
                    <legend>Maklumat Tuntutan Kos Ganti Rugi</legend>
                     <div class="instr-fieldset">
                            <font color="red">*</font>Klik butang tambah untuk tambah maklumat permohonan kos ganti rugi
                     </div>
                    <div class="content" align="center" id="tble">
                        <display:table class="tablecloth" name="${actionBean.senaraiTuntutanKos}"
                                       pagesize="10" cellpadding="0" cellspacing="0" requestURI="/pengambilan/gantiRugi" id="line">

                            <display:column title="Bil" sortable="true" style="text-align:center">${line_rowNum}</display:column>
                            <display:column property="idKos" title="Id Tuntutan Kos" style="text-align:center"></display:column>
                            <display:column property="item" title="Jenis Kerosakan" style="text-align:left">
                                <s:text name="senaraiTuntutanKos[${line_rowNum - 2}].item" size="40"/>
                            </display:column>

                            <display:column title="Amaun Yang Dituntut (RM)" property="amaunTuntutan" style="text-align:right">
                                <div align="left"><s:text name="senaraiTuntutanKos[${line_rowNum - 1}].amaunTuntutan"
                                 size="20" class="number" onblur="javascript:updateTotal(this,${line_rowNum - 1});" id="amaun${line_rowNum - 1}"/>
                                </div>
                            </display:column>

                            <c:if test="${detail}">
                            <display:column title="Amaun Yang Diluluskan (RM)" property="amaunSebenar" style="text-align:right">
                                <div align="center"><s:text name="senaraiTuntutanKos[${line_rowNum - 1}].amaunSebenar"
                                 size="20" class="number" onblur="javascript:updateTotal1(this,${line_rowNum - 1});" id="amaunt${line_rowNum - 1}"/></div>
                                </display:column>
                             </c:if>

                            <display:column title="Kemaskini">
                                <div align="center">
                                    <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popup1('${line.idKos}')"/>
                                </div>
                            </display:column>
                            <display:column title="Hapus">
                                <div align="center">
                                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeSingle('${actionBean.senaraiTuntutanKos[line_rowNum-1].idKos}');" />
                                </div>
                            </display:column>

                            <display:footer>

                             
                                    <tr>
                                        <td colspan="3"><div align="right"><b>Jumlah Perlu Dibayar(RM):</b></div></td>
                                        <td><div align="center"><input name="jumCaraBayar" value="0.00" id="jumCaraBayar" size="20"
                                                                       class="number" readonly="true"/></div></td>
                                        

                                   
                                        <td><div align="center"><input name="jumCaraBayar1" value="0.00" id="jumCaraBayar1" size="20"
                                                                       class="number" readonly="true"/></div></td>
                                        
                                </tr>
                            </display:footer>
                        </display:table >
                        <br />
                        <s:button name="new" value="Tambah" id="new" class="btn" onclick="addNew1();"/>
                    </div>
                </fieldset>
        </c:if><%----%>
    </div>

    <%--commented previos code--%>

    <div>
        <fieldset class="aras1" id="locate"><br/>
           <%-- <c:if test="${actionBean.hakmilik ne null}">
            <legend>Maklumat Tanah</legend>
            <p align="center">
                
                <display:table class="tablecloth" name="${actionBean.hakmilik}" cellpadding="0" cellspacing="0"
                               requestURI="/pengambilan/gantiRugi" id="line">
                    <display:column title="No" sortable="true">1</display:column>
                    <display:column title="No Hakmilik">
                        <s:link beanclass="etanah.view.stripes.pengambilan.perbicaraanKosGantiRugiActionBean"
                                event="hakmilikDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                            <s:param name="idHakmilik" value="${actionBean.hakmilik.idHakmilik}"/>${actionBean.hakmilik.kodHakmilik.kod} ${actionBean.hakmilik.noHakmilik}
                        </s:link>
                    </display:column>
                    <display:column title="Nombor Lot" style="vertical-align:baseline">
                        ${actionBean.hakmilik.noLot}
                    </display:column>
                    <display:column title="Daerah"  class="daerah" style="vertical-align:baseline">${actionBean.hakmilik.daerah.nama}</display:column>
                    <display:column  title="Bandar/Pekan/Mukim" style="vertical-align:baseline">${actionBean.hakmilik.bandarPekanMukim.nama}</display:column>
                    <display:column title="Luas yang diambil" style="vertical-align:baseline"><fmt:formatNumber pattern="#,##0.0000" value="${actionBean.hakmilik.luas}"/>&nbsp;${actionBean.hakmilik.kodUnitLuas.nama}</display:column>
                    <display:column title="Luas sebenar" style="vertical-align:baseactionBean"><fmt:formatNumber pattern="#,##0.0000" value="${actionBean.hakmilik.luas}"/>&nbsp;${actionBean.hakmilik.kodUnitLuas.nama}</display:column>
                    <display:column title="Amaun Yang Dituntut (RM)" style="text-align:right">
                               ${actionBean.jumCaraBayar1}
                    </display:column>
                    
                    <display:column title="Amaun Yang Diluluskan (RM)" style="text-align:right">
                        ${actionBean.amaunSebenarTuanTotal}
                    </display:column>
                    
                </display:table>
            </p>
            </c:if>--%>
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
                    <%--<display:column title="Senarai Tuan Tanah" style="vertical-align:center" class="popup hakmilik${line_rowNum}">
                        <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                         <c:out value="${senarai.pihak.nama}"/><br />
                        </c:forEach>
                    </display:column>--%>
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

                    <display:column title="Amaun Yang Dituntut (RM)" style="text-align:right">
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
                            <td></td>
                            <td><div align="left"><b>Jumlah Perlu Dibayar(RM):</b></div></td>
                            <td><div align="right"><%--<input name="jumCaraBayar1" value="0.00" id="jumCaraBayar1" size="20"
                                                         class="number" readonly="true" />--%>
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

                    <display:column title="Syer yang dimiliki" style="text-align:center">
                            ${actionBean.permohonanPihak.syerPembilang}/${actionBean.permohonanPihak.syerPenyebut}
                     </display:column>

                    <display:column title="Jenis Kerosakan" style="text-align:left">
                        <c:forEach items="${actionBean.permohonanTuntutanKosList}" var="senarai">
                            <br/>
                            <c:out value="${senarai.item}"/>
                            <br/>
                        </c:forEach>
                    </display:column>
                    <display:column title="Amaun Yang Dituntut (RM)" style="text-align:right">
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
                        <td colspan="5"><div align="right"><b>Jumlah Perlu Dibayar(RM):</b></div></td>
                        <td><div align="right"><%--<input name="jumCaraBayar" value="0.00" id="jumCaraBayar" size="20"
                                                     class="number" readonly="true"/>--%>
                            <s:text name="jumCaraBayar" style="text-align:right" readonly="true" formatPattern="#,##0.00" /></div></td>
                                
                                <td><div align="right"><%--<input name="amaunSebenarTotal" value="0.00" id="amaunSebenarTotal" size="20"
                                                               class="number" readonly="true" />--%>
                                <s:text name="amaunSebenarTotal" style="text-align:right" readonly="true" formatPattern="#,##0.00" />
                                        
                                    </div></td>
                                
                    </tr>
                    </display:footer>
            </display:table>
               </p>

                    <br />
                    <%--<s:hidden name="idPermohonanPihak" value="${actionBean.permohonanPihak.idPermohonanPihak}"/>--%>
                    <s:hidden name="idPihak" value="${actionBean.pihak.idPihak}"/>
                    <div align="center">
                        
                        <s:button name="new" value="Tambah" id="new" class="btn" onclick="addNew(${actionBean.permohonanPihak.idPermohonanPihak});" />
                    
                    
                    <%--<s:button name="simpanTuntutan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>--%>
                    
                    </div>
            
            </c:if>
            
        </fieldset>
    </div>
    </div>
</s:form>

