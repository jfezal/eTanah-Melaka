<%--
    Document   : Penarikan_Balik_MMKN_NS
    Created on : Nov 9, 2010, 9:59:55 AM
    Author     : Rajesh
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<style type="text/css">
    #tdLabel {
        color:#003194;
        display:block;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        margin-left:30px;
        margin-right:0.5em;
        text-align:right;
        width:32em;
    }

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
        width:20em;
    }
</style>
<script language="javascript" type="text/javascript">
    function addTable(divId){

        var id1 = document.getElementById(divId);
        var count = document.getElementById ("count").value;
        // Increment table count by 1
        document.getElementById ("count").value = parseInt(count)+1;
        count = document.getElementById ("count").value;

        // create New table
        var table = document.createElement("TABLE");
        table.id = "table"+count;
        //table.className = "tablecloth";
        table.width="100%";
        //table.border=2;
        var rowCount = table.rows.length;
        var row = table.insertRow(rowCount);

        var cell0 = row.insertCell(0);
        cell0.innerHTML = "<b>7."+(count)+"</b>";
        cell0.style.textAlign = "center";
        <%--cell0.style.backgroundColor = ("#328aa4");--%>
        cell0.width="2%";

        var cell1 = row.insertCell(1);
        var element1 = document.createElement("textarea");
        element1.t = "syor"+(count)+(rowCount+1);
        element1.rows = 5;
        element1.cols = 130;
        element1.name ="syor"+(count)+(rowCount+1);
        element1.id ="syor"+(count)+(rowCount+1);
        <%--element1.value ="syor"+(count)+(rowCount+1);--%>
        cell1.colSpan = 2;
        cell1.appendChild(element1);

        // Add hidden field
        var cell2 = row.insertCell(2);
        var element2 = document.createElement ("input");
        element2.setAttribute("type", "hidden");
        element2.setAttribute("id", "count"+(count));
        element2.setAttribute("name", "count"+(count));
        element2.setAttribute("value", 1);
        cell2.appendChild(element2);

        // Add tambah button
        var cell3 = row.insertCell(3);
        var button = document.createElement('input');
        var buttonName = "tambah7" +(count);
        button.setAttribute('type','button');
        button.className = "btn";
        button.setAttribute('name',buttonName);
        button.setAttribute('value','Tambah 7.'+(count));
        button.onclick=function(){addDynamicRow(table.id,element2.id);};
        cell3.appendChild(button);

        var button1 = document.createElement('input');
        var buttonName1 = "hapus7" +(count);
        button1.setAttribute('type','button');
        button1.className = "btn";
        button1.setAttribute('name',buttonName1);
        button1.setAttribute('value','Hapus 7.'+(count)+' sub');
        button1.onclick=function(){deleteDynamicRow(table.id,element2.id);};
        cell3.appendChild(button1);

        id1.appendChild(table);
        id1.appendChild(document.createElement('br'));
    }

    function deleteTable(formPtg,form1) {
        var bil = 7;
        var temp = $("#temp").val();
        var count = $("#count").val();

         var table = document.getElementById("table"+count);
             var rowCount = table.rows.length;
             for(var i=rowCount-1;i>=0;i--){
                table.deleteRow(i);
                document.getElementById ("count").value = parseInt(count)-1;
             }

        if(parseInt(count) <= parseInt(temp)) {

            var url = '${pageContext.request.contextPath}/pengambilan/penarikan_balik_mmkn_ns?deleteTable&bil='
            +bil+'&formPtg='+formPtg+'&form1='+form1;

        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');

      }


    }

     function addDynamicRow(tableID,countID) {

        var table = document.getElementById(tableID);
        var rowCount = table.rows.length;
        var row = table.insertRow(rowCount);

        document.getElementById(countID).value = parseInt(document.getElementById(countID).value)+1;

        var count = parseInt(tableID.substring(5));

        var cell0 = row.insertCell(0);
         cell0.innerHTML = "";

         var cell1 = row.insertCell(1);
          var arr=['a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'];
         cell1.innerHTML = "<b>"+arr[rowCount-1]+".</b>";

        var cell2 = row.insertCell(2);
        var element2 = document.createElement("textarea");
        element2.t = "syor"+(count)+(rowCount+1);
        element2.rows = 5;
        element2.cols = 130;
        element2.name ="syor"+(count)+(rowCount+1);
        element2.id ="syor"+(count)+(rowCount+1);
        <%--element2.value ="syor"+(count)+(rowCount+1);--%>
        cell2.appendChild(element2);

    }

    function deleteDynamicRow(tableID,countID,formPtg,form1) {
        var table = document.getElementById(tableID);
        var rowCount = table.rows.length;
        var i = tableID.substring(5);

        if(rowCount >1){
            var obj = document.getElementById("kand"+(i)+(rowCount));
            var idKandungan = $("#kand"+(i)+(rowCount)).val();
            table.deleteRow(rowCount-1);
            document.getElementById(countID).value = table.rows.length;
            if (obj != null) {

                var url = '${pageContext.request.contextPath}/pengambilan/penarikan_balik_mmkn_ns?deleteSingle&idKandungan='
                    +idKandungan+'&formPtg='+formPtg+'&form1='+form1;

                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
            }

        }else{
            alert("Cannot delete row .")
        }

    }

    function addTable5(divId){
        var id1 = document.getElementById(divId);
        var count5 = document.getElementById ("count5").value;
        // Increment table count by 1
        document.getElementById ("count5").value = parseInt(count5)+1;
        count5 = document.getElementById ("count5").value;

        // create New table
        var table5 = document.createElement("TABLE");
        table5.id = "table5"+count5;
        //table.className = "tablecloth";
        table5.width="100%";
        //table.border=2;
        var rowCount5 = table5.rows.length;
        var row5 = table5.insertRow(rowCount5);

        var cell0 = row5.insertCell(0);
        cell0.innerHTML = "<b>5."+(count5)+"</b>";
        cell0.style.textAlign = "center";
        <%--cell0.style.backgroundColor = ("#328aa4");--%>
        cell0.width="2%";

        var cell1 = row5.insertCell(1);
        var element1 = document.createElement("textarea");
        element1.t = "syorPentadbir"+(count5)+(rowCount5+1);
        element1.rows = 5;
        element1.cols = 130;
        element1.name ="syorPentadbir"+(count5)+(rowCount5+1);
        element1.id ="syorPentadbir"+(count5)+(rowCount5+1);
        <%--element1.value ="syorPentadbir"+(count5)+(rowCount5+1);--%>
        cell1.colSpan = 2;
        cell1.appendChild(element1);

        // Add hidden field
        var cell2 = row5.insertCell(2);
        var element2 = document.createElement ("input");
        element2.setAttribute("type", "hidden");
        element2.setAttribute("id", "count5"+(count5));
        element2.setAttribute("name", "count5"+(count5));
        element2.setAttribute("value", 1);
        cell2.appendChild(element2);

        // Add tambah button
        var cell3 = row5.insertCell(3);
        var button = document.createElement('input');
        var buttonName = "tambah5" +(count5);
        button.setAttribute('type','button');
        button.className = "btn";
        button.setAttribute('name',buttonName);
        button.setAttribute('value','Tambah 5.'+(count5));
        button.onclick=function(){addDynamicRow5(table5.id,element2.id);};
        cell3.appendChild(button);

        var button1 = document.createElement('input');
        var buttonName1 = "hapus5" +(count5);
        button1.setAttribute('type','button');
        button1.className = "btn";
        button1.setAttribute('name',buttonName1);
        button1.setAttribute('value','Hapus 5.'+(count5)+' sub');
        button1.onclick=function(){deleteDynamicRow5(table5.id,element2.id);};
        cell3.appendChild(button1);

        id1.appendChild(table5);
        id1.appendChild(document.createElement('br'));
    }

    function deleteTable5(formPtg,form1) {
        var bil = 5;
        var temp5 = $("#temp5").val();
        var count5 = $("#count5").val();

         var table5 = document.getElementById("table5"+count5);
             var rowCount5 = table5.rows.length;
             for(var i=rowCount5-1;i>=0;i--){
                table5.deleteRow(i);
                document.getElementById ("count5").value = parseInt(count5)-1;
             }

        if(parseInt(count5) <= parseInt(temp5)) {

            var url = '${pageContext.request.contextPath}/pengambilan/penarikan_balik_mmkn_ns?deleteTable&bil='
            +bil+'&formPtg='+formPtg+'&form1='+form1;

        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');

      }


    }

     function addDynamicRow5(tableID,countID) {

        var table5 = document.getElementById(tableID);
        var rowCount5 = table5.rows.length;
        var row5 = table5.insertRow(rowCount5);

        document.getElementById(countID).value = parseInt(document.getElementById(countID).value)+1;

        var count5 = parseInt(tableID.substring(6));

        var cell0 = row5.insertCell(0);
         cell0.innerHTML = "";

         var cell1 = row5.insertCell(1);
          var arr=['a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'];
         cell1.innerHTML = "<b>"+arr[rowCount5-1]+".</b>";

        var cell2 = row5.insertCell(2);
        var element2 = document.createElement("textarea");
        element2.t = "syorPentadbir"+(count5)+(rowCount5+1);
        element2.rows = 5;
        element2.cols = 130;
        element2.name ="syorPentadbir"+(count5)+(rowCount5+1);
        element2.id ="syorPentadbir"+(count5)+(rowCount5+1);
        <%--element2.value ="syorPentadbir"+(count5)+(rowCount5+1);--%>
        cell2.appendChild(element2);

    }

    function deleteDynamicRow5(tableID,countID,formPtg,form1) {
        var table5 = document.getElementById(tableID);
        var rowCount5 = table5.rows.length;
        var i = tableID.substring(6);

        if(rowCount5 >1){
            var obj = document.getElementById("kand5"+(i)+(rowCount5));
            var idKandungan = $("#kand5"+(i)+(rowCount5)).val();
            table5.deleteRow(rowCount5-1);
            document.getElementById(countID).value = table5.rows.length;
            if (obj != null) {

                var url = '${pageContext.request.contextPath}/pengambilan/penarikan_balik_mmkn_ns?deleteSingle&idKandungan='
                    +idKandungan+'&formPtg='+formPtg+'&form1='+form1;

                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
            }

        }else{
            alert("Cannot delete row .")
        }

    }


    function addRow1(tableID) {
        document.form2.rowCount1.value = 1;
        var table = document.getElementById(tableID);

        var rowCount1 = table.rows.length;
        var row = table.insertRow(rowCount1);

        var cell2 = row.insertCell(0);
        cell2.innerHTML = "<b>"+"2." +(rowCount1+1)+"</b>";

        var cell1 = row.insertCell(1);
        var element1 = document.createElement("textarea");
        element1.t = "kandungan1"+(rowCount1+1);
        element1.rows = 5;
        element1.cols = 150;
        element1.name ="kandungan1"+(rowCount1+1);
        element1.id ="kandungan1"+(rowCount1+1);
        cell1.appendChild(element1);
        document.form2.rowCount1.value=rowCount1 +1;
    }

    function deleteRow1(formPtg,form1)
    {
        var i = document.form2.rowCount1.value;
        var x= document.getElementById('dataTable1').rows[i-1].cells;
        var idKandungan = x[0].innerHTML;

        if (document.getElementById('idKandungan1'+(i)) != null) {
            var url = '${pageContext.request.contextPath}/pengambilan/penarikan_balik_mmkn_ns?deleteSingle&idKandungan='
            +idKandungan+'&formPtg='+formPtg+'&form1='+form1;

        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
        }

        document.getElementById('dataTable1').deleteRow(i-1);
        document.form2.rowCount1.value = i -1;
    }

    function addRow2(tableID2) {
        document.form2.rowCount2.value = 1;
        var table = document.getElementById(tableID2);

        var rowCount2 = table.rows.length;
        var row = table.insertRow(rowCount2);

        var cell2 = row.insertCell(0);
        cell2.innerHTML = "<b>"+"5." +(rowCount2+1)+"</b>";

        var cell1 = row.insertCell(1);
        var element1 = document.createElement("textarea");
        element1.t = "kandungan2"+(rowCount2+1);
        element1.rows = 5;
        element1.cols = 150;
        element1.name ="kandungan2"+(rowCount2+1);
        element1.id ="kandungan2"+(rowCount2+1);
        cell1.appendChild(element1);
        //alert(element1.name)
        document.form2.rowCount2.value=rowCount2 +1;
    }
    function deleteRow2()
    {
        var j = document.form2.rowCount2.value;
        var x= document.getElementById('dataTable2').rows[j-1].cells;
        var idKandungan = x[0].innerHTML;

        document.getElementById('dataTable2').deleteRow(j-1);
        document.form2.rowCount2.value = j -1;

        if (document.getElementById('idKandungan2'+(j)) != null) {
            var url = '${pageContext.request.contextPath}/pengambilan/penarikan_balik_mmkn_ns?deleteSingle&idKandungan='
            +idKandungan;

        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
        }
    }

    function validation() {
    var count1=$("#rowCount1").val();
    for(var i=1;i<=count1;i++){
        var kandungan1= $("#kandungan1"+i).val();
        if(kandungan1 == ""){
            alert('Sila pilih " 2. Latar Belakang " terlebih dahulu.');
            $("#kandungan1"+i).focus();
            return false;
        }
    }

    var count5 = $("#count5").val();
    for(var i=1;i<=count5;i++){
        var recordCount5 = $("#count5"+i).val();
        for(var j=1;j<=recordCount5;j++){
            var syorPentadbir = $("#syorPentadbir"+i+j).val();
            if(syorPentadbir == ""){
                alert('Sila pilih " 5. SYOR PENTADBIR TANAH " terlebih dahulu.');
                $("#syorPentadbir"+i+j).focus();
                return false;
            }
        }

    }

    var count = $("#count").val();
    for(var i=1;i<=count;i++){
        var recordCount = $("#count"+i).val();
        for(var j=1;j<=recordCount;j++){
            var syor = $("#syor"+i+j).val();
            if(syor == ""){
                alert('Sila pilih " 7. SYOR PENGARAH TANAH DAN GALIAN(SYARAT-SYARAT KELULUSAN) " terlebih dahulu.');
                $("#syor"+i+j).focus();
                return false;
            }
        }

    }

    if($("#kertasBil").val() == ""){
            $("#kertasBil").val("Tiada Data");
    }

    if($("#masa").val() == ""){
            $("#masa").val("Tiada Data");
    }

    <%--if($("#tarikhMesyuarat").val() == ""){
            $("#tarikhMesyuarat").val("Tiada Data");
    }--%>

    if($("#tempat").val() == ""){
            $("#tempat").val("Tiada Data");
    }

    if($("#peruntukanKewangan").val() == ""){
            $("#peruntukanKewangan").val("Tiada Data");
    }
    if($("#ulasanPentadbir").val() == ""){
            $("#ulasanPentadbir").val("Tiada Data");
    }
    if($("#ulasanPengarah").val() == ""){
            $("#ulasanPengarah").val("Tiada Data");
    }

    return true;
    }
</script>

<s:form beanclass="etanah.view.stripes.pengambilan.PenarikanBalikMMKNNSActionBean" name="form2">
    <s:messages/>
    <s:errors/>
    <c:if test="${form1}">
    <s:hidden name="form1" value="${form1}"/>
    <s:hidden name="formPtg" value="false"/>
    <div class="subtitle">
        <fieldset class="aras1" id="locate">
            <div class="content" align="center">
                <table border="0" width="80%">
                    <div class="content" align="left">
                        <tr align="left"><td align="left">&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
                                <u><b>MAJLIS MESYUARAT KERAJAAN NEGERI SEMBILAN </b></u><br /></td></tr>
                        <tr><td> &nbsp;</td></tr>
                        <tr><td align="left"><b><label><font color="black">PERSIDANGAN :</font></label><s:text name="kertasBil"  size="10" style="text-align:left" id="kertasBil" disabled="true"/>/${actionBean.kertasTahun}<s:hidden name="kertasTahun" value="${actionBean.kertasTahun}"/></b></td></tr>
                        <%--<tr><td align="left"><b><label><font color="black">MASA        :</font></label><s:text name="masa.kandungan"  size="30" style="text-align:left" id="masa" readonly="true"/></b></td></tr>--%>
                        <tr>
                                   <td><label><font color="black">MASA :</font></label>
                                       <s:select name="jam" disabled="true" style="width:60px">
                                        <s:option value="">Jam</s:option>
                                        <s:option value="00">00</s:option>
                                        <s:option value="01">01</s:option>
                                        <s:option value="02">02</s:option>
                                        <s:option value="03">03</s:option>
                                        <s:option value="04">04</s:option>
                                        <s:option value="05">05</s:option>
                                        <s:option value="06">06</s:option>
                                        <s:option value="07">07</s:option>
                                        <s:option value="08">08</s:option>
                                        <s:option value="09">09</s:option>
                                        <s:option value="10">10</s:option>
                                        <s:option value="11">11</s:option>
                                        <s:option value="12">12</s:option>
                                    </s:select>
                                    <s:select name="minit" disabled="true" style="width:62px">
                                        <s:option value="">Minit</s:option>
                                        <s:option value="00">00</s:option>
                                        <s:option value="01">01</s:option>
                                        <s:option value="02">02</s:option>
                                        <s:option value="03">03</s:option>
                                        <s:option value="04">04</s:option>
                                        <s:option value="05">05</s:option>
                                        <s:option value="06">06</s:option>
                                        <s:option value="07">07</s:option>
                                        <s:option value="08">08</s:option>
                                        <s:option value="09">09</s:option>
                                        <s:option value="10">10</s:option>
                                        <s:option value="11">11</s:option>
                                        <s:option value="12">12</s:option>
                                        <s:option value="13">13</s:option>
                                        <s:option value="14">14</s:option>
                                        <s:option value="15">15</s:option>
                                        <s:option value="16">16</s:option>
                                        <s:option value="17">17</s:option>
                                        <s:option value="18">18</s:option>
                                        <s:option value="19">19</s:option>
                                        <s:option value="20">20</s:option>
                                        <s:option value="21">21</s:option>
                                        <s:option value="22">22</s:option>
                                        <s:option value="23">23</s:option>
                                        <s:option value="24">24</s:option>
                                        <s:option value="25">25</s:option>
                                        <s:option value="26">26</s:option>
                                        <s:option value="27">27</s:option>
                                        <s:option value="28">28</s:option>
                                        <s:option value="29">29</s:option>
                                        <s:option value="30">30</s:option>
                                        <s:option value="31">31</s:option>
                                        <s:option value="32">32</s:option>
                                        <s:option value="33">33</s:option>
                                        <s:option value="34">34</s:option>
                                        <s:option value="35">35</s:option>
                                        <s:option value="36">36</s:option>
                                        <s:option value="37">37</s:option>
                                        <s:option value="38">38</s:option>
                                        <s:option value="39">39</s:option>
                                        <s:option value="40">40</s:option>
                                        <s:option value="41">41</s:option>
                                        <s:option value="42">42</s:option>
                                        <s:option value="43">43</s:option>
                                        <s:option value="44">44</s:option>
                                        <s:option value="45">45</s:option>
                                        <s:option value="46">46</s:option>
                                        <s:option value="47">47</s:option>
                                        <s:option value="48">48</s:option>
                                        <s:option value="49">49</s:option>
                                        <s:option value="50">50</s:option>
                                        <s:option value="51">51</s:option>
                                        <s:option value="52">52</s:option>
                                        <s:option value="53">53</s:option>
                                        <s:option value="54">54</s:option>
                                        <s:option value="55">55</s:option>
                                        <s:option value="56">56</s:option>
                                        <s:option value="57">57</s:option>
                                        <s:option value="58">58</s:option>
                                        <s:option value="59">59</s:option>
                                        <s:option value="60">60</s:option>
                                    </s:select>
                                    <s:select name="pagiPetang" disabled="true" style="width:80px">
                                    <s:option >Pilih</s:option>
                                    <s:option value="PAGI">Pagi</s:option>
                                    <s:option value="PETANG">Petang</s:option>
                                </s:select></td>
                                </tr>
                        <tr><td align="left"><b><label><font color="black">TARIKH :</font></label><s:text name="tarikhmesyuarat" id="tarikhMesyuarat" class="datepicker" size="30" style="text-align:left" disabled="true"/></b></td></tr>
                        <tr><td align="left"><b><label><font color="black">TEMPAT      :</font></label><s:textarea name="tempat" cols="50" rows="5" style="text-align:left" id="tempat" disabled="true"/></b></td></tr>
                    </div>
                    <tr><td></td></tr><tr><td></td></tr><tr><td></td></tr><tr><td></td></tr>

                    <%--<tr><td> &nbsp;</td></tr>
                    <tr><td><font style="text-transform: uppercase"><b>PERMOHONAN PENARIKAN BALIK DARIPADA PENGAMBILAN SEBAHAGIAN TANAH DI LOT ${actionBean.hakmilik.noLot}, ${actionBean.hakmilik.kodHakmilik.kod} ${actionBean.hakmilik.luas}, ${actionBean.hakmilik.bandarPekanMukim.nama}, DAERAH ${actionBean.hakmilik.daerah.nama}, NEGERI SEMBILAN BAGI ${actionBean.permohonan.sebab}.</b></font><br /><br /><hr /><br /></td></tr>--%>

                    <tr><td><b><s:hidden name="heading" />
                    ${actionBean.heading}</b></td></tr><br/>

                    <tr><td><b>1. TUJUAN</b></td></tr>
                    <tr><td><b>&nbsp;&nbsp;&nbsp;1.1</b></td></tr>
                    <tr><tr><tr><td colspan="2"><b> &nbsp;</b><font style="text-transform: uppercase"><s:textarea rows="5" cols="140" name="tujuan"/></font><td>

                    <tr><td> &nbsp;</td></tr>
                    <tr><td><b>2. LATAR BELAKANG</b></td></tr>
                    <tr><td>
                            <table id ="dataTable1">
                                <c:set var="i" value="1" />
                                <c:forEach items="${actionBean.senaraiKertasKandungan1}" var="line">
                                    <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                        <td><font style="text-transform: uppercase"><s:textarea name="kandungan1${i}" id="kandungan1${i}" rows="5" cols="150" onkeyup="this.value=this.value.toUpperCase();">${line.kandungan}</s:textarea></font></td>
                                    </tr>
                                    <s:hidden name="idKandungan1${i}" id="idKandungan1${i}" value="${line.idKandungan}" />
                                    <c:set var="i" value="${i+1}" />
                                </c:forEach>
                                    <s:hidden name="rowCount1" value="${i-1}" id="rowCount1"/>
                            </table>
                    <tr><td align="right"><s:button name="latarBelakang" value="Tambah" class="btn" onclick="addRow1('dataTable1')" />
                            <s:button name="latarBelakang" value="Hapus" class="btn" onclick="deleteRow1('${formPtg}','${form1}')" />

                    <tr><td> &nbsp;</td></tr>
                    <tr><td><b>3. PERUNTUKAN KEWANGAN(INJURIES)</b></td></tr>
                    <tr><td><b> &nbsp;</b><font style="text-transform: uppercase"><s:textarea name="peruntukanKewangan.kandungan" rows="5" cols="150" id="peruntukanKewangan" /></font></td></tr><br>


                    <tr><td> &nbsp;</td></tr>
                    <tr><td><b><font style="text-transform:uppercase">4. ULASAN PENTADBIR TANAH ${actionBean.hakmilik.daerah.nama}</font></b></td></tr>
                    <tr><td><b> &nbsp;</b><font style="text-transform: uppercase"><s:textarea name="ulasanPentadbir.kandungan" rows="5" cols="150" id="ulasanPentadbir" /></font></td></tr><br>

                    <tr><td> &nbsp;</td></tr>
                    <tr><td><b><font style="text-transform:uppercase">5. SYOR PENTADBIR TANAH ${actionBean.hakmilik.daerah.nama}</font></b></td></tr>

                    <c:forEach var="i" begin="1" end="${actionBean.count5}">
                    <tr><td>
                         <table  width="90%" id="table5${i}" >
                          <c:set var="recordCount5" value="0"/>
                             <c:forEach items="${actionBean.senaraiSyorPentadbir[i]}" var="senarai">
                                <c:set var="recordCount5" value="${recordCount5+1}"/>
                                   <c:if test="${recordCount5 eq 1}">
                                   <tr> <td><b>5.${i}</b></td>
                                       <td colspan="2"><s:textarea rows="5" cols="130" name="syorPentadbir${i}${recordCount5}" id="syorPentadbir${i}${recordCount5}" value="${senarai.kandungan}" /></td>
                                       <td><s:hidden name="count5${i}" id="count5${i}" value="${(fn:length(actionBean.senaraiSyorPentadbir[i]))}" /> </td>
                                      <td><s:button class="btn" value="Tambah 5.${i}" name="add" id="tambah5${i}" onclick="addDynamicRow5('table5${i}','count5${i}')"/></td>
                                      <td><s:button class="btn" value="Hapus 5.${i} sub" name="hapus" id="hapus5${i}" onclick="deleteDynamicRow5('table5${i}','count5${i}','${formPtg}','${form1}')"/></td>
                                   </c:if>
                                   <c:if test="${recordCount5 ne 1}">
                                   <tr>  <td></td>
                                       <td><b><c:out value="${actionBean.str[recordCount5-1]}"/>.</b></td>
                                       <td><s:textarea rows="5" cols="130" name="syorPentadbir${i}${recordCount5}" id="syorPentadbir${i}${recordCount5}" value="${senarai.kandungan}" /></td>
                                   </c:if>
                                  <s:hidden name="kand5${i}${recordCount5}" id="kand5${i}${recordCount5}" value="${senarai.idKandungan}" />
                              </c:forEach>
                         </table>
                        </td></tr>

                    </c:forEach>
                    <tr><td align="right">
                    <div id="Tables5">

                    </div>
                    <s:button class="btn" value="Tambah 5" name="add" onclick="addTable5('Tables5')"/>
                    <s:button class="longbtn" value="Hapus 5 rekod terakhir" name="add" onclick="deleteTable5('${formPtg}','${form1}')" style="font-size:12px"/>

                    <tr><td> &nbsp;</td></tr>
                    <tr><td><b>6. ULASAN PENGARAH TANAH DAN GALIAN</b></td></tr>
                    <tr><td><b> &nbsp;</b><font style="text-transform: uppercase">
                                <%--<c:if test="${ptg}">--%>
                                <s:textarea name="ulasanPengarah.kandungan" rows="5" cols="150" id="ulasanPengarah" disabled="true"/>
                                <%--</c:if>--%>
                                <%--<c:if test="${!ptg}">
                                <s:textarea name="ulasanPengarah.kandungan" rows="5" cols="150" id="ulasanPengarah" readonly="true"/>
                                </c:if>--%>
                            </font></td></tr><br>

                    <tr><td> &nbsp;</td></tr>
                    <tr><td><b><font style="text-transform:uppercase">7. SYOR PENGARAH TANAH DAN GALIAN(SYARAT-SYARAT KELULUSAN) </font></b></td></tr>

                    <%--<c:if test="${ptg}">--%>

                    <c:forEach var="i" begin="1" end="${actionBean.count}">
                    <tr><td>
                         <table  width="90%" id="table${i}" >
                          <c:set var="recordCount" value="0"/>
                             <c:forEach items="${actionBean.senaraiSyor[i]}" var="senarai">
                                <c:set var="recordCount" value="${recordCount+1}"/>
                                   <c:if test="${recordCount eq 1}">
                                   <tr> <td><b>7.${i}</b></td>
                                       <td colspan="2"><s:textarea rows="5" cols="130" name="syor${i}${recordCount}" id="syor${i}${recordCount}" value="${senarai.kandungan}" /></td>
                                       <td><s:hidden name="count${i}" id="count${i}" value="${(fn:length(actionBean.senaraiSyor[i]))}" /> </td>
                                       <td><s:button class="btn" disabled="true" value="Tambah 7.${i}" name="add" id="tambah${i}" onclick="addDynamicRow('table${i}','count${i}')"/></td>
                                       <td><s:button class="btn" disabled="true" value="Hapus 7.${i} sub" name="hapus" id="hapus${i}" onclick="deleteDynamicRow('table${i}','count${i}','${formPtg}','${form1}')"/></td>
                                   </c:if>
                                   <c:if test="${recordCount ne 1}">
                                   <tr>  <td></td>
                                       <td><b><c:out value="${actionBean.str[recordCount-1]}"/>.</b></td>
                                       <td><s:textarea rows="5" cols="130" name="syor${i}${recordCount}" id="syor${i}${recordCount}" value="${senarai.kandungan}" /></td>
                                   </c:if>
                                  <s:hidden name="kand${i}${recordCount}" id="kand${i}${recordCount}" value="${senarai.idKandungan}" />
                              </c:forEach>
                         </table>
                        </td></tr>

                    </c:forEach>
                    <tr><td align="right">
                    <div id="Tables7">

                    </div>
                    <s:button class="btn" value="Tambah" name="add" onclick="addTable('Tables7')" disabled="true"/>
                    <s:button class="longbtn" value="Hapus 7 rekod terakhir" name="add" onclick="deleteTable('${formPtg}','${form1}')" disabled="true" style="font-size:11px"/>
                    <%--</c:if>--%>

                    <%--<c:if test="${!ptg}">
                    <c:forEach var="i" begin="1" end="${actionBean.count}">
                    <tr><td>
                         <table  width="90%" id="table${i}" >
                          <c:set var="recordCount" value="0"/>
                             <c:forEach items="${actionBean.senaraiSyor[i]}" var="senarai">
                                <c:set var="recordCount" value="${recordCount+1}"/>
                                   <c:if test="${recordCount eq 1}">
                                   <tr> <td><b>7.${i}</b></td>
                                       <td colspan="2"><s:textarea rows="5" cols="130" name="syor${i}${recordCount}" id="syor${i}${recordCount}" value="${senarai.kandungan}" readonly="true"/></td>
                                       <td><s:hidden name="count${i}" id="count${i}" value="${(fn:length(actionBean.senaraiSyor[i]))}" /> </td>
                                       <td><s:button class="btn" value="Tambah" name="add" id="tambah${i}" onclick="addDynamicRow('table${i}','count${i}')" disabled="true"/></td>
                                      <td><s:button class="btn" value="Hapus" name="hapus" id="hapus${i}" onclick="deleteDynamicRow('table${i}','count${i}')" disabled="true"/></td>
                                   </c:if>
                                   <c:if test="${recordCount ne 1}">
                                   <tr>  <td></td>
                                       <td><b><c:out value="${actionBean.str[recordCount-1]}"/>.</b></td>
                                       <td><s:textarea rows="5" cols="130" name="syor${i}${recordCount}" id="syor${i}${recordCount}" value="${senarai.kandungan}" readonly="true"/></td>
                                   </c:if>
                                  <s:hidden name="kand${i}${recordCount}" id="kand${i}${recordCount}" value="${senarai.idKandungan}" />
                              </c:forEach>
                         </table>
                        </td></tr>

                    </c:forEach>
                    <tr><td align="right">
                    <div id="Tables7">

                    </div>
                    <s:button class="btn" value="Tambah" name="add" onclick="addTable('Tables7')" disabled="true"/>
                    <s:button class="btn" value="Hapus" name="add" onclick="deleteTable()" disabled="true"/>
                    </c:if>--%>
                    <tr><td> &nbsp;</td></tr>
                    <tr><td><b>8. KEPUTUSAN</b></td></tr>
                    <tr><td><b> &nbsp;</b><font style="text-transform: uppercase"><s:textarea name="keputusan" rows="5" cols="150" id="keputusan" readonly="true"/></font></td></tr><br>

                </table>
            </div>
        </fieldset>
    </div>
                    <p align="center">
                        <s:hidden name="count" id="count" value="${actionBean.count}"/>
                        <s:hidden name="temp" id="temp" value="${actionBean.count}"/>
                        <s:hidden name="count5" id="count5" value="${actionBean.count5}"/>
                        <s:hidden name="temp5" id="temp5" value="${actionBean.count5}"/>
                        <s:hidden name="idKertas" value="${permohonanKertas.idKertas}"/>
                        <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>
                    </p>
    </c:if>
    <c:if test="${formPtg}">
        <%--<s:text name="formPtg" value="${formPtg}"/>--%>
    <%--<s:text name="form1" value="false"/>--%>
    <div class="subtitle">
        <fieldset class="aras1" id="locate">
            <div class="content" align="center">
                <table border="0" width="80%">
                    <div class="content" align="left">
                        <tr align="left"><td align="left">&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
                                <u><b>MAJLIS MESYUARAT KERAJAAN NEGERI SEMBILAN </b></u><br /></td></tr>
                        <tr><td> &nbsp;</td></tr>
                        <tr><td align="left"><b><label><font color="black">PERSIDANGAN :</font></label><s:text name="kertasBil"  size="10" style="text-align:left" id="kertasBil"/>/${actionBean.kertasTahun}<s:hidden name="kertasTahun" value="${actionBean.kertasTahun}"/></b></td></tr>
                        <%--<tr><td align="left"><b><label><font color="black">MASA        :</font></label><s:text name="masa.kandungan"  size="30" style="text-align:left" id="masa" readonly="true"/></b></td></tr>--%>
                        <tr>
                                   <td><label><font color="black">MASA :</font></label>
                                       <s:select name="jam" style="width:60px" id="jam">
                                        <s:option value="">Jam</s:option>
                                        <s:option value="00">00</s:option>
                                        <s:option value="01">01</s:option>
                                        <s:option value="02">02</s:option>
                                        <s:option value="03">03</s:option>
                                        <s:option value="04">04</s:option>
                                        <s:option value="05">05</s:option>
                                        <s:option value="06">06</s:option>
                                        <s:option value="07">07</s:option>
                                        <s:option value="08">08</s:option>
                                        <s:option value="09">09</s:option>
                                        <s:option value="10">10</s:option>
                                        <s:option value="11">11</s:option>
                                        <s:option value="12">12</s:option>
                                    </s:select>
                                    <s:select name="minit" style="width:62px" id="minit">
                                        <s:option value="">Minit</s:option>
                                        <s:option value="00">00</s:option>
                                        <s:option value="01">01</s:option>
                                        <s:option value="02">02</s:option>
                                        <s:option value="03">03</s:option>
                                        <s:option value="04">04</s:option>
                                        <s:option value="05">05</s:option>
                                        <s:option value="06">06</s:option>
                                        <s:option value="07">07</s:option>
                                        <s:option value="08">08</s:option>
                                        <s:option value="09">09</s:option>
                                        <s:option value="10">10</s:option>
                                        <s:option value="11">11</s:option>
                                        <s:option value="12">12</s:option>
                                        <s:option value="13">13</s:option>
                                        <s:option value="14">14</s:option>
                                        <s:option value="15">15</s:option>
                                        <s:option value="16">16</s:option>
                                        <s:option value="17">17</s:option>
                                        <s:option value="18">18</s:option>
                                        <s:option value="19">19</s:option>
                                        <s:option value="20">20</s:option>
                                        <s:option value="21">21</s:option>
                                        <s:option value="22">22</s:option>
                                        <s:option value="23">23</s:option>
                                        <s:option value="24">24</s:option>
                                        <s:option value="25">25</s:option>
                                        <s:option value="26">26</s:option>
                                        <s:option value="27">27</s:option>
                                        <s:option value="28">28</s:option>
                                        <s:option value="29">29</s:option>
                                        <s:option value="30">30</s:option>
                                        <s:option value="31">31</s:option>
                                        <s:option value="32">32</s:option>
                                        <s:option value="33">33</s:option>
                                        <s:option value="34">34</s:option>
                                        <s:option value="35">35</s:option>
                                        <s:option value="36">36</s:option>
                                        <s:option value="37">37</s:option>
                                        <s:option value="38">38</s:option>
                                        <s:option value="39">39</s:option>
                                        <s:option value="40">40</s:option>
                                        <s:option value="41">41</s:option>
                                        <s:option value="42">42</s:option>
                                        <s:option value="43">43</s:option>
                                        <s:option value="44">44</s:option>
                                        <s:option value="45">45</s:option>
                                        <s:option value="46">46</s:option>
                                        <s:option value="47">47</s:option>
                                        <s:option value="48">48</s:option>
                                        <s:option value="49">49</s:option>
                                        <s:option value="50">50</s:option>
                                        <s:option value="51">51</s:option>
                                        <s:option value="52">52</s:option>
                                        <s:option value="53">53</s:option>
                                        <s:option value="54">54</s:option>
                                        <s:option value="55">55</s:option>
                                        <s:option value="56">56</s:option>
                                        <s:option value="57">57</s:option>
                                        <s:option value="58">58</s:option>
                                        <s:option value="59">59</s:option>
                                        <s:option value="60">60</s:option>
                                    </s:select>
                                    <s:select name="pagiPetang" style="width:80px" id="pagiPetang">
                                    <s:option >Pilih</s:option>
                                    <s:option value="PAGI">Pagi</s:option>
                                    <s:option value="PETANG">Petang</s:option>
                                </s:select></td>
                                </tr>
                        <tr><td align="left"><b><label><font color="black">TARIKH :</font></label><s:text name="tarikhmesyuarat" id="tarikhMesyuarat" class="datepicker" size="30" style="text-align:left" /></b></td></tr>
                        <tr><td align="left"><b><label><font color="black">TEMPAT      :</font></label><s:textarea name="tempat" cols="50" rows="5" style="text-align:left" id="tempat" /></b></td></tr>
                    </div>
                    <tr><td></td></tr><tr><td></td></tr><tr><td></td></tr><tr><td></td></tr>

                    <%--<tr><td> &nbsp;</td></tr>
                    <tr><td><font style="text-transform: uppercase"><b>PERMOHONAN PENARIKAN BALIK DARIPADA PENGAMBILAN SEBAHAGIAN TANAH DI LOT ${actionBean.hakmilik.noLot}, ${actionBean.hakmilik.kodHakmilik.kod} ${actionBean.hakmilik.luas}, ${actionBean.hakmilik.bandarPekanMukim.nama}, DAERAH ${actionBean.hakmilik.daerah.nama}, NEGERI SEMBILAN BAGI ${actionBean.permohonan.sebab}.</b></font><br /><br /><hr /><br /></td></tr>--%>

                    <tr><td><b><s:hidden name="heading" />
                        ${actionBean.heading}</b></td></tr><br/>

                    <tr><td><b>1. TUJUAN</b></td></tr>
                    <tr><td><b>&nbsp;&nbsp;&nbsp;1.1</b></td></tr>
                    <tr><tr><tr><td colspan="2"><b> &nbsp;</b><font style="text-transform: uppercase"><s:textarea rows="5" cols="140" name="tujuan" readonly="true"/></font><td>

                    <tr><td> &nbsp;</td></tr>
                    <tr><td><b>2. LATAR BELAKANG</b></td></tr>
                    <tr><td>
                            <table id ="dataTable1">
                                <c:set var="i" value="1" />
                                <c:forEach items="${actionBean.senaraiKertasKandungan1}" var="line">
                                    <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                        <td><font style="text-transform: uppercase"><s:textarea readonly="true" name="kandungan1${i}" id="kandungan1${i}" rows="5" cols="150" onkeyup="this.value=this.value.toUpperCase();">${line.kandungan}</s:textarea></font></td>
                                    </tr>
                                    <s:hidden name="idKandungan1${i}" id="idKandungan1${i}" value="${line.idKandungan}" />
                                    <c:set var="i" value="${i+1}" />
                                </c:forEach>
                                    <s:hidden name="rowCount1" value="${i-1}" id="rowCount1"/>
                            </table>
                    <tr><td align="right"><s:button name="latarBelakang" value="Tambah" class="btn" onclick="addRow1('dataTable1')" disabled="true"/>
                            <s:button name="latarBelakang" value="Hapus" class="btn" onclick="deleteRow1('${formPtg}','${form1}')" disabled="true"/>

                    <tr><td> &nbsp;</td></tr>
                    <tr><td><b>3. PERUNTUKAN KEWANGAN(INJURIES)</b></td></tr>
                    <tr><td><b> &nbsp;</b><font style="text-transform: uppercase"><s:textarea name="peruntukanKewangan.kandungan" rows="5" cols="150" id="peruntukanKewangan" readonly="true"/></font></td></tr><br>


                    <tr><td> &nbsp;</td></tr>
                    <tr><td><b><font style="text-transform:uppercase">4. ULASAN PENTADBIR TANAH ${actionBean.hakmilik.daerah.nama}</font></b></td></tr>
                    <tr><td><b> &nbsp;</b><font style="text-transform: uppercase"><s:textarea name="ulasanPentadbir.kandungan" rows="5" cols="150" id="ulasanPentadbir" readonly="true"/></font></td></tr><br>

                    <tr><td> &nbsp;</td></tr>
                    <tr><td><b><font style="text-transform:uppercase">5. SYOR PENTADBIR TANAH ${actionBean.hakmilik.daerah.nama}</font></b></td></tr>

                    <c:forEach var="i" begin="1" end="${actionBean.count5}">
                    <tr><td>
                         <table  width="90%" id="table5${i}" >
                          <c:set var="recordCount5" value="0"/>
                             <c:forEach items="${actionBean.senaraiSyorPentadbir[i]}" var="senarai">
                                <c:set var="recordCount5" value="${recordCount5+1}"/>
                                   <c:if test="${recordCount5 eq 1}">
                                   <tr> <td><b>5.${i}</b></td>
                                       <td colspan="2"><s:textarea rows="5" readonly="true" cols="130" name="syorPentadbir${i}${recordCount5}" id="syorPentadbir${i}${recordCount5}" value="${senarai.kandungan}" /></td>
                                       <td><s:hidden name="count5${i}" id="count5${i}" value="${(fn:length(actionBean.senaraiSyorPentadbir[i]))}" /> </td>
                                       <td><s:button class="btn" value="Tambah 5.${i}" disabled="true" name="add" id="tambah5${i}" onclick="addDynamicRow5('table5${i}','count5${i}')"/></td>
                                      <td><s:button class="btn" value="Hapus 5.${i} sub" disabled="true" name="hapus" id="hapus5${i}" onclick="deleteDynamicRow5('table5${i}','count5${i}','${formPtg}','${form1}')"/></td>
                                   </c:if>
                                   <c:if test="${recordCount5 ne 1}">
                                   <tr>  <td></td>
                                       <td><b><c:out value="${actionBean.str[recordCount5-1]}"/>.</b></td>
                                       <td><s:textarea rows="5" cols="130" readonly="true" name="syorPentadbir${i}${recordCount5}" id="syorPentadbir${i}${recordCount5}" value="${senarai.kandungan}" /></td>
                                   </c:if>
                                  <s:hidden name="kand5${i}${recordCount5}" id="kand5${i}${recordCount5}" value="${senarai.idKandungan}" />
                              </c:forEach>
                         </table>
                        </td></tr>

                    </c:forEach>
                    <tr><td align="right">
                    <div id="Tables5">

                    </div>
                    <s:button class="btn" value="Tambah" disabled="true"name="add" onclick="addTable5('Tables5')"/>
                    <s:button class="longbtn" value="Hapus 5 rekod terakhir" disabled="true" name="add" onclick="deleteTable5('${formPtg}','${form1}')" style="font-size:11px"/>

                    <tr><td> &nbsp;</td></tr>
                    <tr><td><b>6. ULASAN PENGARAH TANAH DAN GALIAN</b></td></tr>
                    <tr><td><b> &nbsp;</b><font style="text-transform: uppercase">
                                <%--<c:if test="${ptg}">--%>
                                <s:textarea name="ulasanPengarah.kandungan" rows="5" cols="150" id="ulasanPengarah" />
                                <%--</c:if>--%>
                                <%--<c:if test="${!ptg}">
                                <s:textarea name="ulasanPengarah.kandungan" rows="5" cols="150" id="ulasanPengarah" readonly="true"/>
                                </c:if>--%>
                            </font></td></tr><br>

                    <tr><td> &nbsp;</td></tr>
                    <tr><td><b><font style="text-transform:uppercase">7. SYOR PENGARAH TANAH DAN GALIAN(SYARAT-SYARAT KELULUSAN) </font></b></td></tr>

                    <%--<c:if test="${ptg}">--%>

                    <c:forEach var="i" begin="1" end="${actionBean.count}">
                    <tr><td>
                         <table  width="90%" id="table${i}" >
                          <c:set var="recordCount" value="0"/>
                             <c:forEach items="${actionBean.senaraiSyor[i]}" var="senarai">
                                <c:set var="recordCount" value="${recordCount+1}"/>
                                   <c:if test="${recordCount eq 1}">
                                   <tr> <td><b>7.${i}</b></td>
                                       <td colspan="2"><s:textarea rows="5" cols="130" name="syor${i}${recordCount}" id="syor${i}${recordCount}" value="${senarai.kandungan}" /></td>
                                       <td><s:hidden name="count${i}" id="count${i}" value="${(fn:length(actionBean.senaraiSyor[i]))}" /> </td>
                                      <td><s:button class="btn" value="Tambah 7.${i}" name="add" id="tambah${i}" onclick="addDynamicRow('table${i}','count${i}')"/></td>
                                      <td><s:button class="longbtn" value="Hapus 7.${i} sub" name="hapus" id="hapus${i}" onclick="deleteDynamicRow('table${i}','count${i}','${formPtg}','${form1}')"/></td>
                                   </c:if>
                                   <c:if test="${recordCount ne 1}">
                                   <tr>  <td></td>
                                       <td><b><c:out value="${actionBean.str[recordCount-1]}"/>.</b></td>
                                       <td><s:textarea rows="5" cols="130" name="syor${i}${recordCount}" id="syor${i}${recordCount}" value="${senarai.kandungan}" /></td>
                                   </c:if>
                                  <s:hidden name="kand${i}${recordCount}" id="kand${i}${recordCount}" value="${senarai.idKandungan}" />
                              </c:forEach>
                         </table>
                        </td></tr>

                    </c:forEach>
                    <tr><td align="right">
                    <div id="Tables7">

                    </div>
                    <s:button class="btn" value="Tambah 7" name="add" onclick="addTable('Tables7')"/>
                    <s:button class="longbtn" value="Hapus 7 rekod terakhir" name="add" onclick="deleteTable('${formPtg}','${form1}')" style="font-size:12px"/>
                    <%--</c:if>--%>

                    <%--<c:if test="${!ptg}">
                    <c:forEach var="i" begin="1" end="${actionBean.count}">
                    <tr><td>
                         <table  width="90%" id="table${i}" >
                          <c:set var="recordCount" value="0"/>
                             <c:forEach items="${actionBean.senaraiSyor[i]}" var="senarai">
                                <c:set var="recordCount" value="${recordCount+1}"/>
                                   <c:if test="${recordCount eq 1}">
                                   <tr> <td><b>7.${i}</b></td>
                                       <td colspan="2"><s:textarea rows="5" cols="130" name="syor${i}${recordCount}" id="syor${i}${recordCount}" value="${senarai.kandungan}" readonly="true"/></td>
                                       <td><s:hidden name="count${i}" id="count${i}" value="${(fn:length(actionBean.senaraiSyor[i]))}" /> </td>
                                       <td><s:button class="btn" value="Tambah" name="add" id="tambah${i}" onclick="addDynamicRow('table${i}','count${i}')" disabled="true"/></td>
                                      <td><s:button class="btn" value="Hapus" name="hapus" id="hapus${i}" onclick="deleteDynamicRow('table${i}','count${i}')" disabled="true"/></td>
                                   </c:if>
                                   <c:if test="${recordCount ne 1}">
                                   <tr>  <td></td>
                                       <td><b><c:out value="${actionBean.str[recordCount-1]}"/>.</b></td>
                                       <td><s:textarea rows="5" cols="130" name="syor${i}${recordCount}" id="syor${i}${recordCount}" value="${senarai.kandungan}" readonly="true"/></td>
                                   </c:if>
                                  <s:hidden name="kand${i}${recordCount}" id="kand${i}${recordCount}" value="${senarai.idKandungan}" />
                              </c:forEach>
                         </table>
                        </td></tr>

                    </c:forEach>
                    <tr><td align="right">
                    <div id="Tables7">

                    </div>
                    <s:button class="btn" value="Tambah" name="add" onclick="addTable('Tables7')" disabled="true"/>
                    <s:button class="btn" value="Hapus" name="add" onclick="deleteTable()" disabled="true"/>
                    </c:if>--%>
                    <tr><td> &nbsp;</td></tr>
                    <tr><td><b>8. KEPUTUSAN</b></td></tr>
                    <tr><td><b> &nbsp;</b><font style="text-transform: uppercase"><s:textarea name="keputusan" rows="5" cols="150" id="keputusan"/></font></td></tr><br>

                </table>
            </div>
        </fieldset>
    </div>
                    <p align="center">
                        <s:hidden name="count" id="count" value="${actionBean.count}"/>
                        <s:hidden name="temp" id="temp" value="${actionBean.count}"/>
                        <s:hidden name="count5" id="count5" value="${actionBean.count5}"/>
                        <s:hidden name="temp5" id="temp5" value="${actionBean.count5}"/>
                        <s:hidden name="idKertas" value="${permohonanKertas.idKertas}"/>
                        <s:button name="simpanPtg" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>
                    </p>
    </c:if>
</s:form>