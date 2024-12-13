<%--
    Document   : pengambilan_MMKN_PenarikanBalik
    Created on : Jul 30, 2010, 10:50:08 AM
    Author     : Massita
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

    function deleteRow1()
    {
        var i = document.form2.rowCount1.value;
        var x= document.getElementById('dataTable1').rows[i-1].cells;
        var idKandungan = x[0].innerHTML;

        if (document.getElementById('idKandungan1'+(i)) != null) {
            var url = '${pageContext.request.contextPath}/pengambilan/borang_mmkn_penarikanBalik?deleteSingle&idKandungan='
            +idKandungan;

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
        cell2.innerHTML = "<b>"+"3." +(rowCount2+1)+"</b>";

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
            var url = '${pageContext.request.contextPath}/pengambilan/borang_mmkn_penarikanBalik?deleteSingle&idKandungan='
            +idKandungan;

        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
        }
    }

    function addRow3(tableID3) {
        document.form2.rowCount3.value = 1;
        var table = document.getElementById(tableID3);

        var rowCount3 = table.rows.length;
        var row = table.insertRow(rowCount3);

        var cell2 = row.insertCell(0);
        cell2.innerHTML = "<b>"+"4." +(rowCount3+1)+"</b>";

        var cell1 = row.insertCell(1);
        var element1 = document.createElement("textarea");
        element1.t = "kandungan3"+(rowCount3+1);
        element1.rows = 5;
        element1.cols = 150;
        element1.name ="kandungan3"+(rowCount3+1);
        element1.id ="kandungan3"+(rowCount3+1);
        cell1.appendChild(element1);
        //alert(element1.name)
        document.form2.rowCount3.value=rowCount3 +1;
    }
    function deleteRow3()
    {
        var k = document.form2.rowCount3.value;
        var x= document.getElementById('dataTable3').rows[k-1].cells;
        var idKandungan = x[0].innerHTML;

        document.getElementById('dataTable3').deleteRow(k-1);
        document.form2.rowCount3.value = k -1;

        if (document.getElementById('idKandungan3'+(k)) != null) {
            var url = '${pageContext.request.contextPath}/pengambilan/borang_mmkn_penarikanBalik?deleteSingle&idKandungan='
            +idKandungan;

        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
        }
    }

    function addRow4(tableID4) {
        document.form2.rowCount4.value = 1;
        var table = document.getElementById(tableID4);

        var rowCount4 = table.rows.length;
        var row = table.insertRow(rowCount4);

        var cell2 = row.insertCell(0);
        cell2.innerHTML = "<b>"+"4." +(rowCount4+1)+"</b>";

        var cell1 = row.insertCell(1);
        var element1 = document.createElement("textarea");
        element1.t = "kandungan4"+(rowCount4+1);
        element1.rows = 5;
        element1.cols = 150;
        element1.name ="kandungan4"+(rowCount4+1);
        element1.id ="kandungan4"+(rowCount4+1);
        cell1.appendChild(element1);
        //alert(element1.name)
        document.form2.rowCount4.value=rowCount4 +1;
    }
    function deleteRow4()
    {
        var k = document.form2.rowCount4.value;
        var x= document.getElementById('dataTable4').rows[k-1].cells;
        var idKandungan = x[0].innerHTML;

        document.getElementById('dataTable4').deleteRow(k-1);
        document.form2.rowCount4.value = k -1;

        if (document.getElementById('idKandungan4'+(k)) != null) {
            var url = '${pageContext.request.contextPath}/pengambilan/borang_mmkn_penarikanBalik?deleteSingle&idKandungan='
            +idKandungan;

        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
        }
    }

   <%-- function addRow5(tableID5) {
        document.form2.rowCount5.value = 1;
        var table = document.getElementById(tableID5);

        var rowCount5 = table.rows.length;
        var row = table.insertRow(rowCount5);

        var cell2 = row.insertCell(0);
        cell2.innerHTML = "<b>"+"7." +(rowCount5+1)+"</b>";

        var cell1 = row.insertCell(1);
        var element1 = document.createElement("textarea");
        element1.t = "kandungan5"+(rowCount5+1);
        element1.rows = 5;
        element1.cols = 150;
        element1.name ="kandungan5"+(rowCount5+1);
        element1.id ="kandungan5"+(rowCount5+1);
        cell1.appendChild(element1);
        document.form2.rowCount5.value=rowCount5 +1;
    }--%>
    <%--function deleteRow5()
    {
        var k = document.form2.rowCount5.value;
        var x= document.getElementById('dataTable5').rows[k-1].cells;
        var idKandungan = x[0].innerHTML;

        document.getElementById('dataTable5').deleteRow(k-1);
        document.form2.rowCount5.value = k -1;

        if (document.getElementById('idKandungan5'+(k)) != null) {
            var url = '${pageContext.request.contextPath}/pengambilan/borang_mmkn_penarikanBalik?deleteSingle&idKandungan='
            +idKandungan;

        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
        }
    }--%>

    function validation() {
     var count1=$("#rowCount1").val();
    for(var i=1;i<=count1;i++){
        var kandungan1= $("#kandungan1"+i).val();
        if(kandungan1 == ""){
            alert('Sila pilih " 2.1 Latar Belakang " terlebih dahulu.');
            $("#kandungan1"+i).focus();
            return false;
        }
    }

    var count2=$("#rowCount2").val();
    for(var i=1;i<=count2;i++){
        var kandungan2= $("#kandungan2"+i).val();
        if(kandungan2 == ""){
            alert('Sila pilih " 3. Perihal Tanah " terlebih dahulu.');
            $("#kandungan2"+i).focus();
            return false;
        }
    }

    <%--var count3=$("#rowCount3").val();
    for(var i=1;i<=count3;i++){
        var kandungan3= $("#kandungan3"+i).val();
        if(kandungan3 == ""){
            alert('Sila pilih " 4. PANDANGAN PENTADBIR TANAH " terlebih dahulu.');
            $("#kandungan3"+i).focus();
            return false;
        }
    }--%>

    var count4=$("#rowCount4").val();
    for(var i=1;i<=count4;i++){
        var kandungan4= $("#kandungan4"+i).val();
        if(kandungan4 == ""){
            alert('Sila pilih " 4. PERAKUAN PENTADBIR TANAH " terlebih dahulu.');
            $("#kandungan4"+i).focus();
            return false;
        }
    }

    <%--var count5=$("#rowCount5").val();
    for(var i=1;i<=count5;i++){
        var kandungan5= $("#kandungan5"+i).val();
        if(kandungan5 == ""){
            alert('Sila pilih " 5. PERAKUAN PENGARAH TANAH DAN GALIAN " terlebih dahulu.');
            $("#kandungan5"+i).focus();
            return false;
        }
    }--%>

    if($("#kertasBil").val() == ""){
            $("#kertasBil").val("Tiada Data");
    }

    if($("#masa").val() == ""){
            $("#masa").val("Tiada Data");
    }

    if($("#tarikhmesyuarat").val() == ""){
            $("#tarikhmesyuarat").val("Tiada Data");
    }

    if($("#tempat").val() == ""){
            $("#tempat").val("Tiada Data");
    }

    if($("#syorPtg").val() == ""){
            $("#syorPtg").val("Tiada Data");
    }
   
    if($("#jam").val() == ""){
    alert('Sila pilih " JAM " terlebih dahulu.');
    $("#jam").focus();
    return false;
    }
    if($("#minit").val() == ""){
        alert('Sila pilih " MINIT " terlebih dahulu.');
        $("#minit").focus();
        return false;
    }
    if($("#pagiPetang").val() == ""){
        alert('Sila pilih " PAGI/PETANG " terlebih dahulu.');
        $("#pagiPetang").focus();
        return false; 
    }
    return true;
}
</script>

<s:form beanclass="etanah.view.stripes.pengambilan.PengambilanMMKNPenarikanBalikActionBean" name="form2">
    <s:messages/>
    <s:errors/>
    <%--<c:if test="${actionBean.opFlag == true}">--%>
    <c:if test="${form1}">
        <div class="subtitle">
            <fieldset class="aras1" id="locate">
                <legend></legend>
                <div class="content" align="center">
                    <table border="0" width="80%">

                         <div class="content" align="left">
                                <%--<table align="left">--%>
                                <tr align="left"><td align="left">&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
                                        <u><b>MAJLIS MESYUARAT KERAJAAN NEGERI MELAKA</b></u><br /></td></tr>
                                <tr><td> &nbsp;</td></tr>
                                <tr><td align="left"><b><label><font color="black">PERSIDANGAN :</font></label><s:text name="kertasBil"  size="10" style="text-align:left" id="kertasBil" disabled="true"/>/${actionBean.kertasTahun}<s:hidden name="kertasTahun" value="${actionBean.kertasTahun}"/></b></td></tr>
                                <%--<tr><td align="left"><b><label><font color="black">MASA        :</font></label><s:text name="masa"  size="30" style="text-align:left" id="masa" readonly="true"/></b></td></tr>--%>
                               <tr>
                                   <td><label><font color="black">MASA :</font></label>
                                       <s:select disabled="true" name="jam" id="jam" style="width:60px">
                                        <s:option value="00">Jam</s:option>
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
                                    <s:select disabled="true" name="minit" id="minit" style="width:62px">
                                        <s:option value="00">Minit</s:option>
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
                                    <s:select disabled="true" name="pagiPetang" id="pagiPetang" style="width:80px">
                                    <s:option value="00">Pilih</s:option>
                                    <s:option value="PAGI">Pagi</s:option>
                                    <s:option value="PETANG">Petang</s:option>
                                </s:select></td>
                                </tr>
                                <tr><td align="left"><b><label><font color="black">TARIKH :</font></label><s:text name="tarikhmesyuarat" id="tarikhmesyuarat" class="datepicker" size="30" style="text-align:left" disabled="true"/></b></td></tr><br>
                                <tr><td align="left"><b><label><font color="black">TEMPAT      :</font></label><s:textarea name="tempat" cols="50" rows="5" style="text-align:left" id="tempat" disabled="true"/></b></td></tr>
                                <%--</table>--%>
                         </div>
                         <tr><td></td></tr><tr><td></td></tr><tr><td></td></tr><tr><td></td></tr>

                       <tr><td> &nbsp;</td></tr>
                       <%--<tr><td><font style="text-transform: uppercase"><b>PERMOHONAN PENARIKAN BALIK DARIPADA PENGAMBILAN SEBAHAGIAN TANAH DI LOT ${actionBean.hakmilik.noLot}<s:hidden name="hakmilik.noLot" value="${actionBean.hakmilik.noLot}"/>, ${actionBean.hakmilik.kodHakmilik.kod}<s:hidden name="hakmilik.kodHakmilik.kod" value="${hakmilik.kodHakmilik.kod}"/> ${actionBean.hakmilik.luas}<s:hidden name="hakmilik.luas" value="${actionBean.hakmilik.luas}"/>, ${actionBean.hakmilik.bandarPekanMukim.nama}<s:hidden name="hakmilik.bandarPekanMukim.nama" value="${actionBean.hakmilik.bandarPekanMukim.nama}"/>, DAERAH ${actionBean.hakmilik.daerah.nama}<s:hidden name="hakmilik.daerah.nama" value="${actionBean.hakmilik.daerah.nama}"/>, MELAKA BAGI ${actionBean.namaProjek}<s:hidden name="namaProjek" value="${actionBean.namaProjek}"/>.</b></font><br /><br /><hr /><br /></td></tr>--%>
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
                                    <%--<c:if test="${actionBean.senaraiKertasKandungan1 eq null}">
                                        <s:hidden name="rowCount1" value="1"/>
                                        <tr><td><b>2.1.1</b></td>
                                            <td><font style="text-transform:uppercase"><s:textarea name="kandungan1" id="kandungan1" rows="5" cols="150"/></font></td></tr></c:if>
                                        --%><c:forEach items="${actionBean.senaraiKertasKandungan1}" var="line">
                                            <%--<s:hidden name="rowCount1" value="${i}"/>--%>
                                        <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                            <td><font style="text-transform: uppercase"><s:textarea name="kandungan1${i}" id="kandungan1${i}" rows="5" cols="150" onkeyup="this.value=this.value.toUpperCase();">${line.kandungan}</s:textarea></font>
                                            </td></tr>
                                        <s:hidden name="idKandungan1${i}" id="idKandungan1${i}" value="${line.idKandungan}" />
                                            <c:set var="i" value="${i+1}" />
                                        </c:forEach>
                                        <s:hidden name="rowCount1" value="${i-1}" id="rowCount1"/>
                                </table>
                        <tr><td align="right"><s:button name="latarBelakang" value="Tambah" class="btn" onclick="addRow1('dataTable1')" />
                                <s:button name="latarBelakang" value="Hapus" class="btn" onclick="deleteRow1()" />

                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b>3. PERIHAL TANAH</b></td></tr>
                        <tr><td>
                                <table id ="dataTable2">
                                    <c:set var="i" value="1" />
                                    <%--<c:if test="${actionBean.senaraiKertasKandungan2 eq null}">
                                        <s:hidden name="rowCount" value="1"/>
                                        <tr><td><b>3.1</b></td>
                                            <td><font style="text-transform:uppercase"><s:textarea name="kandungan2" id="kandungan2" rows="5" cols="150"/></font></td></tr></c:if>
                                   --%>     <c:forEach items="${actionBean.senaraiKertasKandungan2}" var="line">
                                            <%--<s:hidden name="rowCount" value="${i}"/>--%>
                                        <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                            <td><font style="text-transform: uppercase"><s:textarea name="kandungan2${i}" id="kandungan2${i}" rows="5" cols="150" onkeyup="this.value=this.value.toUpperCase();">${line.kandungan}</s:textarea></font>
                                            </td></tr>
                                        <s:hidden name="idKandungan2${i}" id="idKandungan2${i}" value="${line.idKandungan}" />
                                            <c:set var="i" value="${i+1}" />
                                        </c:forEach>
                                        <s:hidden name="rowCount2" value="${i-1}" id="rowCount2"/>
                                </table>
                        <tr><td align="right"><s:button name="perihalTanah" value="Tambah" class="btn" onclick="addRow2('dataTable2')" />
                                <s:button name="perihalTanah" value="Hapus" class="btn" onclick="deleteRow2()" />

                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b><font style="text-transform:uppercase">4. PERAKUAN PENTADBIR TANAH ${actionBean.hakmilik.daerah.nama}</font></b></td></tr>
                        <tr><td>
                                <table id ="dataTable4">
                                    <c:set var="k" value="1" />
                                   <%-- <c:if test="${actionBean.senaraiKertasKandungan4 eq null}">
                                        <s:hidden name="rowCount" value="1"/>
                                        <tr><td><b>5.1</b></td>
                                            <td><s:textarea name="kandungan4" id="kandungan4" rows="5" cols="150"/></td></tr></c:if>
                                      --%>  <c:forEach items="${actionBean.senaraiKertasKandungan4}" var="line">
                                           <%-- <s:hidden name="rowCount" value="${i}"/>--%>
                                        <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                            <td><font style="text-transform:uppercase"><s:textarea name="kandungan4${k}" id="kandungan4${k}" rows="5" cols="150" onkeyup="this.value=this.value.toUpperCase();">${line.kandungan}</s:textarea></font>
                                            </td></tr>
                                        <s:hidden name="idKandungan4${k}" id="idKandungan4${k}" value="${line.idKandungan}" />
                                            <c:set var="k" value="${k+1}" />
                                        </c:forEach>
                                        <s:hidden name="rowCount4" value="${k-1}" id="rowCount4"/>
                                </table>
                        <tr><td align="right"><s:button name="perakuanPentadbirTanah" value="Tambah" class="btn" onclick="addRow4('dataTable4')" />
                                <s:button name="perakuanPentadbirTanah" value="Hapus" class="btn" onclick="deleteRow4()" />

                                <tr><td> &nbsp;</td></tr>
                        <tr><td><b>5. PERAKUAN PENGARAH TANAH DAN GALIAN</b></td></tr>
                        <tr><td><b> &nbsp;</b><font style="text-transform: uppercase"><s:textarea name="syorPtg" rows="5" cols="150" id="syorPtg" disabled="true"/></font></td></tr><br>

                        <%--<tr><td> &nbsp;</td></tr>
                        <tr><td><b>5. PERAKUAN PENGARAH TANAH DAN GALIAN</b></td></tr>
                        <tr><td>
                                <table id ="dataTable5">
                                    <c:set var="k" value="1" />
                                   <c:forEach items="${actionBean.senaraiKertasKandungan5}" var="line">
                                        <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                            <td><font style="text-transform:uppercase"><s:textarea name="kandungan5${k}" id="kandungan5${k}" rows="5" cols="150" onkeyup="this.value=this.value.toUpperCase();" readonly="true">${line.kandungan}</s:textarea></font>
                                            </td></tr>
                                        <s:hidden name="idKandungan5${k}" id="idKandungan5${k}" value="${line.idKandungan}" />
                                            <c:set var="k" value="${k+1}" />
                                        </c:forEach>
                                        <s:hidden name="rowCount5" value="${k-1}" id="rowCount5"/>
                                </table>
                        <tr><td align="right"><s:button name="perakuanPengarahTanah" value="Tambah" class="btn" onclick="addRow5('dataTable5')" disabled="true"/>
                                <s:button name="perakuanPengarahTanah" value="Hapus" class="btn" onclick="deleteRow5()" disabled="true"/>--%>

                    </table>
                </div>
            </fieldset>
        </div>
        <p align="center">
            <%--<s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>--%>
            <s:hidden name="idKertas" value="${permohonanKertas.idKertas}"/>
            <%--<s:hidden name="idKandungan" value="${permohonanKertasKandungan.idKandungan}"/>--%>
            <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>
            <%--|| if(validateForm2()) || if(validateForm3()) || if(validateForm4()) || if(validateForm5()) || if(validateForm6()) || if(validateForm7())--%>
        </p>
    </c:if>
    <c:if test="${formPtg}">
        <div class="subtitle">
            <fieldset class="aras1" id="locate">
                <legend></legend>
                <div class="content" align="center">
                    <table border="0" width="80%">

                         <div class="content" align="left">
                                <%--<table align="left">--%>
                                <tr align="left"><td align="left">&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
                                        <u><b>MAJLIS MESYUARAT KERAJAAN NEGERI MELAKA</b></u><br /></td></tr>
                                <tr><td> &nbsp;</td></tr>
                                <tr><td align="left"><b><label><font color="black">PERSIDANGAN :</font></label><s:text name="kertasBil"  size="10" style="text-align:left" id="kertasBil" />/${actionBean.kertasTahun}<s:hidden name="kertasTahun" value="${actionBean.kertasTahun}"/></b></td></tr>
                                <%--<tr><td align="left"><b><label><font color="black">MASA        :</font></label><s:text name="masa"  size="30" style="text-align:left" id="masa"/></b></td></tr>--%>
                                <tr>
                                   <td><label><font color="black">MASA :</font></label>
                                    <s:select name="jam" style="width:60px" id="jam">
                                        <s:option>Jam</s:option>
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
                                        <s:option>Minit</s:option>
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
                                        <s:option>Pilih</s:option>
                                        <s:option value="PAGI">PAGI</s:option>
                                        <s:option value="PETANG">PETANG</s:option>
                                    </s:select></td>
                                </tr>
                                <tr><td align="left"><b><label><font color="black">TARIKH :</font></label><s:text name="tarikhmesyuarat" id="datepicker" class="datepicker" size="30" style="text-align:left"/></b></td></tr><br>
                                <tr><td align="left"><b><label><font color="black">TEMPAT      :</font></label><s:textarea name="tempat" cols="50" rows="5" style="text-align:left" id="tempat"/></b></td></tr>
                                <%--</table>--%>
                          </div>
                          <tr><td></td></tr><tr><td></td></tr><tr><td></td></tr><tr><td></td></tr>

                        <%--<tr><td> &nbsp;</td></tr>
                        <tr><td><font style="text-transform: uppercase"><b>PERMOHONAN PENARIKAN BALIK DARIPADA PENGAMBILAN SEBAHAGIAN TANAH DI LOT ${actionBean.hakmilik.noLot}, ${actionBean.hakmilik.kodHakmilik.kod} ${actionBean.hakmilik.luas}, ${actionBean.hakmilik.bandarPekanMukim.nama}, DAERAH ${actionBean.hakmilik.daerah.nama}, MELAKA BAGI ${actionBean.namaProjek}.</b></font><br /><br /><hr /><br /></td></tr>--%>
                        <tr><td><b><s:hidden name="heading" />
                        ${actionBean.heading}</b></td></tr><br/>
                        
                        <tr><td><b>1. TUJUAN</b></td></tr>
                        <tr><tr><tr><td colspan="2"><b> &nbsp;</b><font style="text-transform: uppercase"><s:textarea rows="5" cols="140" name="tujuan"/></font><td>

                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b>2. LATAR BELAKANG</b></td></tr>
                        <tr><td>
                                <table id ="dataTable1">
                                    <c:set var="i" value="1" />
                                    <%--<c:if test="${actionBean.senaraiKertasKandungan1 eq null}">
                                        <s:hidden name="rowCount1" value="1"/>
                                        <tr><td><b>2.1.1</b></td>
                                            <td><font style="text-transform:uppercase"><s:textarea name="kandungan1" id="kandungan1" rows="5" cols="150"/></font></td></tr></c:if>
                                        --%><c:forEach items="${actionBean.senaraiKertasKandungan1}" var="line">
                                            <%--<s:hidden name="rowCount1" value="${i}"/>--%>
                                        <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                            <td><font style="text-transform: uppercase"><s:textarea name="kandungan1${i}" id="kandungan1${i}" rows="5" cols="150" onkeyup="this.value=this.value.toUpperCase();" readonly="true">${line.kandungan}</s:textarea></font>
                                            </td></tr>
                                        <s:hidden name="idKandungan1${i}" id="idKandungan1${i}" value="${line.idKandungan}" />
                                            <c:set var="i" value="${i+1}" />
                                        </c:forEach>
                                        <s:hidden name="rowCount1" value="${i-1}" id="rowCount1"/>
                                </table>
                        <tr><td align="right"><s:button name="latarBelakang" value="Tambah" class="btn" onclick="addRow1('dataTable1')" disabled="true"/>
                                <s:button name="latarBelakang" value="Hapus" class="btn" onclick="deleteRow1()" disabled="true"/>

                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b>3. PERIHAL TANAH</b></td></tr>
                        <tr><td>
                                <table id ="dataTable2">
                                    <c:set var="i" value="1" />
                                    <%--<c:if test="${actionBean.senaraiKertasKandungan2 eq null}">
                                        <s:hidden name="rowCount" value="1"/>
                                        <tr><td><b>3.1</b></td>
                                            <td><font style="text-transform:uppercase"><s:textarea name="kandungan2" id="kandungan2" rows="5" cols="150"/></font></td></tr></c:if>
                                   --%>     <c:forEach items="${actionBean.senaraiKertasKandungan2}" var="line">
                                            <%--<s:hidden name="rowCount" value="${i}"/>--%>
                                        <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                            <td><font style="text-transform: uppercase"><s:textarea name="kandungan2${i}" id="kandungan2${i}" rows="5" cols="150" onkeyup="this.value=this.value.toUpperCase();" readonly="true">${line.kandungan}</s:textarea></font>
                                            </td></tr>
                                        <s:hidden name="idKandungan2${i}" id="idKandungan2${i}" value="${line.idKandungan}" />
                                            <c:set var="i" value="${i+1}" />
                                        </c:forEach>
                                        <s:hidden name="rowCount2" value="${i-1}" id="rowCount2"/>
                                </table>
                        <tr><td align="right"><s:button name="perihalTanah" value="Tambah" class="btn" onclick="addRow2('dataTable2')" disabled="true"/>
                                <s:button name="perihalTanah" value="Hapus" class="btn" onclick="deleteRow2()" disabled="true"/>

                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b><font style="text-transform:uppercase">4. PERAKUAN PENTADBIR TANAH ${actionBean.hakmilik.daerah.nama}</font></b></td></tr>
                        <tr><td>
                                <table id ="dataTable4">
                                    <c:set var="k" value="1" />
                                   <%-- <c:if test="${actionBean.senaraiKertasKandungan4 eq null}">
                                        <s:hidden name="rowCount" value="1"/>
                                        <tr><td><b>5.1</b></td>
                                            <td><s:textarea name="kandungan4" id="kandungan4" rows="5" cols="150"/></td></tr></c:if>
                                      --%>  <c:forEach items="${actionBean.senaraiKertasKandungan4}" var="line">
                                           <%-- <s:hidden name="rowCount" value="${i}"/>--%>
                                        <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                            <td><font style="text-transform:uppercase"><s:textarea name="kandungan4${k}" id="kandungan4${k}" rows="5" cols="150" onkeyup="this.value=this.value.toUpperCase();" readonly="true">${line.kandungan}</s:textarea></font>
                                            </td></tr>
                                        <s:hidden name="idKandungan4${k}" id="idKandungan4${k}" value="${line.idKandungan}" />
                                            <c:set var="k" value="${k+1}" />
                                        </c:forEach>
                                        <s:hidden name="rowCount4" value="${k-1}" id="rowCount4"/>
                                </table>
                        <tr><td align="right"><s:button name="perakuanPentadbirTanah" value="Tambah" class="btn" onclick="addRow4('dataTable4')" disabled="true"/>
                                <s:button name="perakuanPentadbirTanah" value="Hapus" class="btn" onclick="deleteRow4()" disabled="true"/>

                       <tr><td> &nbsp;</td></tr>
                        <tr><td><b>5. PERAKUAN PENGARAH TANAH DAN GALIAN</b></td></tr>
                        <tr><td><b> &nbsp;</b><font style="text-transform: uppercase">
                                        <s:textarea name="syorPtgObj.kandungan" rows="5" cols="150" id="syorPtg"/>
                        </font></td></tr><br>
                                
                        <%-- <tr><td> &nbsp;</td></tr>
                        <tr><td><b>5. PERAKUAN PENGARAH TANAH DAN GALIAN</b></td></tr>
                        <tr><td>
                                <table id ="dataTable5">
                                    <c:set var="k" value="1" />
                                   <c:forEach items="${actionBean.senaraiKertasKandungan5}" var="line">
                                        <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                            <td><font style="text-transform:uppercase"><s:textarea name="kandungan5${k}" id="kandungan5${k}" rows="5" cols="150" onkeyup="this.value=this.value.toUpperCase();" >${line.kandungan}</s:textarea></font>
                                            </td></tr>
                                        <s:hidden name="idKandungan5${k}" id="idKandungan5${k}" value="${line.idKandungan}" />
                                            <c:set var="k" value="${k+1}" />
                                        </c:forEach>
                                        <s:hidden name="rowCount5" value="${k-1}" id="rowCount5"/>
                                </table>
                                 <tr><td align="right"><s:button name="perakuanPengarahTanah" value="Tambah" class="btn" onclick="addRow5('dataTable5')" />
                                <s:button name="perakuanPengarahTanah" value="Hapus" class="btn" onclick="deleteRow5()" />--%>

                    </table>
                </div>
            </fieldset>
        </div>
        <p align="center">
            <%--<s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>--%>
            <s:hidden name="idKertas" value="${permohonanKertas.idKertas}"/>
            <%--<s:hidden name="idKandungan" value="${permohonanKertasKandungan.idKandungan}"/>--%>
            <s:button name="simpanPtg" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>
        </p>
    </c:if>
    <%--</c:if>--%>
</s:form>
