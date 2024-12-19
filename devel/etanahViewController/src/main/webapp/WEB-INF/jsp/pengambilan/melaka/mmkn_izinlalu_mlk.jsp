<%-- 
    Document   : mmkn_izinlalu_mlk
    Created on : 30-Oct-2012, 15:14:48
    Author     : nordiyana
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
    function addTable4(divId){
        var id1 = document.getElementById(divId);
        var count4 = document.getElementById ("count4").value;
        // Increment table count by 1
        document.getElementById ("count4").value = parseInt(count4)+1;
        count4 = document.getElementById ("count4").value;

        // create New table
        var table4 = document.createElement("TABLE");
        table4.id = "table4"+count4;
        //table.className = "tablecloth";
        table4.width="100%";
        //table.border=2;
        var rowCount4 = table4.rows.length;
        var row4 = table4.insertRow(rowCount4);

        var cell0 = row4.insertCell(0);
        cell0.innerHTML = "<b>4."+(count4)+"</b>";
        cell0.style.textAlign = "center";
        <%--cell0.style.backgroundColor = ("#328aa4");--%>
        cell0.width="2%";

        var cell1 = row4.insertCell(1);
        var element1 = document.createElement("textarea");
        element1.t = "perakuanPengarah"+(count4)+(rowCount4+1);
        element1.rows = 5;
        element1.cols = 130;
        element1.name ="perakuanPengarah"+(count4)+(rowCount4+1);
        element1.id ="perakuanPengarah"+(count4)+(rowCount4+1);
        <%--element1.value ="perakuanPengarah"+(count4)+(rowCount4+1);--%>
        cell1.colSpan = 2;
        cell1.appendChild(element1);

        // Add hidden field
        var cell2 = row4.insertCell(2);
        var element2 = document.createElement ("input");
        element2.setAttribute("type", "hidden");
        element2.setAttribute("id", "count4"+(count4));
        element2.setAttribute("name", "count4"+(count4));
        element2.setAttribute("value", 1);
        cell2.appendChild(element2);

        // Add tambah button
        var cell3 = row4.insertCell(3);
        var button = document.createElement('input');
        var buttonName = "tambah4" +(count4);
        button.setAttribute('type','button');
        button.className = "btn";
        button.setAttribute('name',buttonName);
        button.setAttribute('value','Tambah 4.'+(count4));
        button.onclick=function(){addDynamicRow4(table4.id,element2.id);};
        cell3.appendChild(button);

        var button1 = document.createElement('input');
        var buttonName1 = "hapus4" +(count4);
        button1.setAttribute('type','button');
        button1.className = "btn";
        button1.setAttribute('name',buttonName1);
        button1.setAttribute('value','Hapus 4.'+(count4)+' sub');
        button1.onclick=function(){deleteDynamicRow4(table4.id,element2.id);};
        cell3.appendChild(button1);

        id1.appendChild(table4);
        id1.appendChild(document.createElement('br'));
    }

    function deleteTable4(formPtg,form1) {
        var bil = 4;
        var temp4 = $("#temp4").val();
        var count4 = $("#count4").val();

         var table4 = document.getElementById("table4"+count4);
             var rowCount4 = table4.rows.length;
             for(var i=rowCount4-1;i>=0;i--){
                table4.deleteRow(i);
                document.getElementById ("count4").value = parseInt(count4)-1;
             }

        if(parseInt(count4) <= parseInt(temp4)) {

            var url = '${pageContext.request.contextPath}/pengambilan/mmknizinlalu?deleteTable&bil='
            +bil+'&formPtg='+formPtg+'&form1='+form1;

        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');

      }


    }

     function addDynamicRow4(tableID,countID) {

        var table4 = document.getElementById(tableID);
        var rowCount4 = table4.rows.length;
        var row4 = table4.insertRow(rowCount4);

        document.getElementById(countID).value = parseInt(document.getElementById(countID).value)+1;

        var count4 = parseInt(tableID.substring(6));

        var cell0 = row4.insertCell(0);
         cell0.innerHTML = "";

         var cell1 = row4.insertCell(1);
          var arr=['a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'];
         cell1.innerHTML = "<b>"+arr[rowCount4-1]+".</b>";

        var cell2 = row4.insertCell(2);
        var element2 = document.createElement("textarea");
        element2.t = "perakuanPengarah"+(count4)+(rowCount4+1);
        element2.rows = 4;
        element2.cols = 130;
        element2.name ="perakuanPengarah"+(count4)+(rowCount4+1);
        element2.id ="perakuanPengarah"+(count4)+(rowCount4+1);
        <%--element2.value ="perakuanPengarah"+(count4)+(rowCount4+1);--%>
        cell2.appendChild(element2);

    }

    function deleteDynamicRow4(tableID,countID,formPtg,form1) {
        var table4 = document.getElementById(tableID);
        var rowCount4 = table4.rows.length;
        var i = tableID.substring(6);

        if(rowCount4 >1){
            var obj = document.getElementById("kand4"+(i)+(rowCount4));
            var idKandungan = $("#kand4"+(i)+(rowCount4)).val();
            table4.deleteRow(rowCount4-1);
            document.getElementById(countID).value = table4.rows.length;
            if (obj != null) {

                var url = '${pageContext.request.contextPath}/pengambilan/mmknizinlalu?deleteSingle&idKandungan='
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





    function addRow1(tableID1) {
        document.form2.rowCount1.value = 1;
        var table = document.getElementById(tableID1);

        var rowCount1 = table.rows.length;
        var row = table.insertRow(rowCount1);

        var cell2 = row.insertCell(0);
        cell2.innerHTML = "<b>"+"2.1." +(rowCount1+1)+"</b>";

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
        var form1Val = $('#form1').val();
        var formPtg = $('#formPtg').val();

        if (document.getElementById('idKandungan1'+(i)) != null) {
            var url = '${pageContext.request.contextPath}/pengambilan/mmknizinlalu_mlk?deleteSingle&form1='+form1Val+'&formPtg='+formPtg+'&idKandungan='
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
        cell2.innerHTML = "<b>"+"2.2." +(rowCount2+1)+"</b>";

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
        var form1Val = $('#form1').val();
        var formPtg = $('#formPtg').val();

        document.getElementById('dataTable2').deleteRow(j-1);
        document.form2.rowCount2.value = j -1;

        if (document.getElementById('idKandungan2'+(j)) != null) {
            var url = '${pageContext.request.contextPath}/pengambilan/mmknizinlalu_mlk?deleteSingle&form1='+form1Val+'&formPtg='+formPtg+'&idKandungan='
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
        cell2.innerHTML = "<b>"+"2.3." +(rowCount3+1)+"</b>";

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
        var j = document.form2.rowCount3.value;
        var x= document.getElementById('dataTable3').rows[j-1].cells;
        var idKandungan = x[0].innerHTML;
        var form1Val = $('#form1').val();
        var formPtg = $('#formPtg').val();

        document.getElementById('dataTable3').deleteRow(j-1);
        document.form2.rowCount3.value = j -1;

        if (document.getElementById('idKandungan3'+(j)) != null) {
            var url = '${pageContext.request.contextPath}/pengambilan/mmknizinlalu_mlk?deleteSingle&form1='+form1Val+'&formPtg='+formPtg+'&idKandungan='
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
        cell2.innerHTML = "<b>"+"2.4." +(rowCount4+1)+"</b>";

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
        var j = document.form2.rowCount4.value;
        var x= document.getElementById('dataTable4').rows[j-1].cells;
        var idKandungan = x[0].innerHTML;
        var form1Val = $('#form1').val();
        var formPtg = $('#formPtg').val();
        document.getElementById('dataTable4').deleteRow(j-1);
        document.form2.rowCount4.value = j -1;

        if (document.getElementById('idKandungan4'+(j)) != null) {
            var url = '${pageContext.request.contextPath}/pengambilan/mmknizinlalu_mlk?deleteSingle&form1='+form1Val+'&formPtg='+formPtg+'&idKandungan='
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
            alert('Sila pilih " 2.1 Perihal Permohonan " terlebih dahulu.');
            $("#kandungan1"+i).focus();
            return false;
        }
    }

    var count2=$("#rowCount2").val();
    for(var i=1;i<=count2;i++){
        var kandungan2= $("#kandungan2"+i).val();
        if(kandungan2 == ""){
            alert('Sila pilih " 2.2 Perihal Tanah " terlebih dahulu.');
            $("#kandungan2"+i).focus();
            return false;
        }
    }

    var count3=$("#rowCount3").val();
    for(var i=1;i<=count3;i++){
        var kandungan3= $("#kandungan3"+i).val();
        if(kandungan3 == ""){
            alert('Sila pilih " 2.3 Anggaran Nilaian Pampasan " terlebih dahulu.');
            $("#kandungan3"+i).focus();
            return false;
        }
    }

    var count3=$("#rowCount4").val();
    for(var i=1;i<=count3;i++){
        var kandungan4= $("#kandungan4"+i).val();
        if(kandungan4 == ""){
            alert('Sila pilih " 2.4 Perihal Pemohon " terlebih dahulu.');
            $("#kandungan4"+i).focus();
            return false;
        }
    }

    var count4 = $("#count4").val();
    for(var i=1;i<=count4;i++){
        var recordCount4 = $("#count4"+i).val();
        for(var j=1;j<=recordCount4;j++){
            var perakuanPengarah = $("#perakuanPengarah"+i+j).val();
            if(perakuanPengarah == ""){
                alert('Sila pilih " 4. PERAKUAN PENGARAH TANAH DAN GALIAN " terlebih dahulu.');
                $("#perakuanPengarah"+i+j).focus();
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
    if($("#ringkasanPermohonan").val() == ""){
            $("#ringkasanPermohonan").val("Tiada Data");
    }
    if($("#syorPengarah").val() == ""){
            $("#syorPengarah").val("Tiada Data");
    }

    return true;
    }
</script>

<s:form beanclass="etanah.view.stripes.pengambilan.mmkn_izinlalu_mlkActionBean" name="form2">
    <s:messages/>
    <s:errors/>
    <c:if test="${form1}">
        <s:hidden name="form1" id="form1" value="${form1}"/>
        <s:hidden name="formPtg" id="formPtg" value="false"/>
    <div class="subtitle">
        <fieldset class="aras1" id="locate">
            <div class="content" align="center">
                <table border="0" width="80%">
                    <div class="content" align="left">
                        <tr><td><b>
                                    <%--<s:hidden name="heading" class="normal_text"/>--%>
                                    <s:textarea rows="5" cols="140" style="font-weight:bold" value="${actionBean.heading}" name="heading" class="normal_text" />
                    </b></td></tr><br/>
                    </div>

                    <tr><td><b>1. TUJUAN</b></td></tr>
                    <tr><tr><tr><td colspan="2"><b> &nbsp;</b><s:textarea rows="5" cols="140" name="tujuan" class="normal_text"/><td>

                    <tr><td> &nbsp;</td></tr>
                    <tr><td><b>2. LATAR BELAKANG</b></td></tr>
                    <tr><td> &nbsp;</td></tr>
                    <tr><td><b>2.1 Perihal Permohonan</b></td></tr>
                    <tr><td>
                            <table id ="dataTable1">
                                <c:set var="i" value="1" />
                                <c:forEach items="${actionBean.senaraiKertasKandungan21}" var="line">
                                    <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                        <td><font><s:textarea name="kandungan1${i}" id="kandungan1${i}" rows="5" cols="150">${line.kandungan}</s:textarea></font></td>
                                    </tr>
                                    <s:hidden name="idKandungan1${i}" id="idKandungan1${i}" value="${line.idKandungan}" />
                                    <c:set var="i" value="${i+1}" />
                                </c:forEach>
                                    <s:hidden name="rowCount1" value="${i-1}" id="rowCount1"/>
                            </table>
                    <tr><td align="right"><s:button name="perihalPermohonan" value="Tambah" class="btn" onclick="addRow1('dataTable1')" />
                            <s:button name="perihalPermohonan" value="Hapus" class="btn" onclick="deleteRow1()" />

                    <tr><td> &nbsp;</td></tr>
                    <tr><td><b>2.2 Perihal Tanah</b></td></tr>
                    <tr><td>
                            <table id ="dataTable2">
                                <c:set var="i" value="1" />
                                <c:forEach items="${actionBean.senaraiKertasKandungan22}" var="line">
                                    <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                        <td><font><s:textarea name="kandungan2${i}" id="kandungan2${i}" rows="5" cols="150">${line.kandungan}</s:textarea></font></td>
                                    </tr>
                                    <s:hidden name="idKandungan2${i}" id="idKandungan2${i}" value="${line.idKandungan}" />
                                    <c:set var="i" value="${i+1}" />
                                </c:forEach>
                                    <s:hidden name="rowCount2" value="${i-1}" id="rowCount2"/>
                            </table>
                    <tr><td align="right"><s:button name="perihalTanah" value="Tambah" class="btn" onclick="addRow2('dataTable2')" />
                            <s:button name="perihalTanah" value="Hapus" class="btn" onclick="deleteRow2()" />

                    <tr><td> &nbsp;</td></tr>
                    <tr><td><b>2.3 Anggaran Nilaian Pampasan</b></td></tr>
                    <tr><td>
                            <table id ="dataTable3">
                                <c:set var="i" value="1" />
                                <c:forEach items="${actionBean.senaraiKertasKandungan23}" var="line">
                                    <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                        <td><font><s:textarea name="kandungan3${i}" id="kandungan3${i}" rows="5" cols="150">${line.kandungan}</s:textarea></font></td>
                                    </tr>
                                    <s:hidden name="idKandungan3${i}" id="idKandungan3${i}" value="${line.idKandungan}" />
                                    <c:set var="i" value="${i+1}" />
                                </c:forEach>
                                    <s:hidden name="rowCount3" value="${i-1}" id="rowCount3"/>
                            </table>
                    <tr><td align="right"><s:button name="anggaranNilaianPampasan" value="Tambah" class="btn" onclick="addRow3('dataTable3')" />
                            <s:button name="anggaranNilaianPampasan" value="Hapus" class="btn" onclick="deleteRow3()" />

                    <tr><td> &nbsp;</td></tr>
                    <tr><td><b>2.4 Perihal Pemohon</b></td></tr>
                    <tr><td>
                            <table id ="dataTable4">
                                <c:set var="i" value="1" />
                                <c:forEach items="${actionBean.senaraiKertasKandungan24}" var="line">
                                    <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                        <td><font><s:textarea name="kandungan4${i}" id="kandungan4${i}" rows="5" cols="150">${line.kandungan}</s:textarea></font></td>
                                    </tr>
                                    <s:hidden name="idKandungan4${i}" id="idKandungan4${i}" value="${line.idKandungan}" />
                                    <c:set var="i" value="${i+1}" />
                                </c:forEach>
                                    <s:hidden name="rowCount4" value="${i-1}" id="rowCount4"/>
                            </table>
                    <tr><td align="right"><s:button name="perihalPemohon" value="Tambah" class="btn" onclick="addRow4('dataTable4')" />
                            <s:button name="perihalPemohon" value="Hapus" class="btn" onclick="deleteRow4()" />


                    <tr><td> &nbsp;</td></tr>
                    <tr><td><b>2.5. Ringkasan Permohonan</b></td></tr>
                    <tr><td><b> &nbsp;</b><font><s:textarea name="ringkasanPermohonan.kandungan" rows="5" cols="150" id="ringkasanPermohonan" /></font></td></tr><br>

                    <tr><td> &nbsp;</td></tr>
                    <tr><td><b>3. PERAKUAN PENTADBIR TANAH ${actionBean.hakmilik.daerah.nama}</b></td></tr>
                    <tr><td><b> &nbsp;</b><font><s:textarea name="syorPengarah.kandungan" rows="5" cols="150" id="peruntukanKewangan" /></font></td></tr><br>
                    <tr><td> &nbsp;</td></tr>
                    
                   <%-- <tr><td><b>4. PERAKUAN PENGARAH TANAH DAN GALIAN</b></td></tr>
                        <tr><td><b> &nbsp;</b><font><s:textarea name="jawatanKuasa.kandungan" rows="5" readonly="false" cols="150" id="ulasan1"/>
                                </font></td></tr><br>

                        <tr><td> &nbsp;</td></tr>

                    <tr><td> &nbsp;</td></tr>--%>


                    <tr><td> &nbsp;</td></tr>
                    <tr><td><b><font style="text-transform:uppercase">4. PERAKUAN PENGARAH TANAH DAN GALIAN</font></b></td></tr>
                    <c:forEach var="i" begin="1" end="${actionBean.count4}">
                    <tr><td>
                         <table  width="90%" id="table4${i}" >
                          <c:set var="recordCount4" value="0"/>
                             <c:forEach items="${actionBean.senaraiPerakuanPengarah[i]}" var="senarai">
                                <c:set var="recordCount4" value="${recordCount4+1}"/>
                                   <c:if test="${recordCount4 eq 1}">
                                   <tr> <td><b>4.${i}</b></td>
                                       <td colspan="2"><s:textarea rows="5" cols="130" disabled="true" name="perakuanPengarah${i}${recordCount4}" id="perakuanPengarah${i}${recordCount4}" value="${senarai.kandungan}" /></td>
                                       <td><s:hidden name="count4${i}" id="count4${i}" value="${(fn:length(actionBean.senaraiPerakuanPengarah[i]))}" /> </td>
                                      <td><s:button class="btn" value="Tambah 4.${i}" disabled="true" name="add" id="tambah4${i}" onclick="addDynamicRow4('table4${i}','count4${i}')"/></td>
                                      <td><s:button class="btn" value="Hapus 4.${i}" disabled="true" name="hapus" id="hapus4${i}" onclick="deleteDynamicRow4('table4${i}','count4${i}','${formPtg}','${form1}')"/></td>
                                   </c:if>
                                   <c:if test="${recordCount4 ne 1}">
                                   <tr>  <td></td>
                                       <td><b><c:out value="${actionBean.str[recordCount4-1]}"/>.</b></td>
                                       <td><s:textarea rows="5" cols="130" name="perakuanPengarah${i}${recordCount4}" disabled="true" id="perakuanPengarah${i}${recordCount4}" value="${senarai.kandungan}" /></td>
                                   </c:if>
                                  <s:hidden name="kand4${i}${recordCount4}" id="kand4${i}${recordCount4}" value="${senarai.idKandungan}" />
                              </c:forEach>
                         </table>
                        </td></tr>

                    </c:forEach>
                    <tr><td align="right">
                    <div id="Tables4">

                    </div>
                    <s:button class="btn" value="Tambah" disabled="true" name="add" onclick="addTable4('Tables4')"/>
                    <s:button class="btn" value="Hapus" disabled="true" name="add" onclick="deleteTable4('${formPtg}','${form1}')" style="font-size:12px"/>

                </table>
            </div>
        </fieldset>
    </div>
                    <p align="center">
                        <s:hidden name="count4" id="count4" value="${actionBean.count4}"/>
                        <s:hidden name="temp4" id="temp4" value="${actionBean.count4}"/>
                        <s:hidden name="idKertas" value="${permohonanKertas.idKertas}"/>
                        <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </p>
    </c:if>
    <c:if test="${formPtg}">
        <s:hidden name="form1" id="form1" value="false"/>
    <s:hidden name="formPtg" id="formPtg" value="${formPtg}"/>
    <div class="subtitle">
        <fieldset class="aras1" id="locate">
            <div class="content" align="center">
                <table border="0" width="80%">
                    <div class="content" align="left">
                    <tr><td><b><s:hidden name="heading" class="normal_text"/>
                    ${actionBean.heading}</b></td></tr><br/>
                    </div>
                    <tr><td><b>1. TUJUAN</b></td></tr>
                    <tr><tr><tr><td colspan="2"><b> &nbsp;</b><s:textarea rows="5" cols="140" name="tujuan" readonly="true" class="normal_text"/><td>

                    <tr><td> &nbsp;</td></tr>
                    <tr><td><b>2. LATAR BELAKANG</b></td></tr>
                    <tr><td> &nbsp;</td></tr>
                    <tr><td><b>2.1 Perihal Permohonan</b></td></tr>
                    <tr><td>
                            <table id ="dataTable1">
                                <c:set var="i" value="1" />
                                <c:forEach items="${actionBean.senaraiKertasKandungan21}" var="line">
                                    <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                        <td><font><s:textarea name="kandungan1${i}" id="kandungan1${i}" rows="5" cols="150" disabled="true">${line.kandungan}</s:textarea></font></td>
                                    </tr>
                                    <s:hidden name="idKandungan1${i}" id="idKandungan1${i}" value="${line.idKandungan}" />
                                    <c:set var="i" value="${i+1}" />
                                </c:forEach>
                                    <s:hidden name="rowCount1" value="${i-1}" id="rowCount1"/>
                            </table>
                    <tr><td align="right"><s:button name="perihalPermohonan" value="Tambah" disabled="true" class="btn" onclick="addRow1('dataTable1')" />
                            <s:button name="perihalPermohonan" value="Hapus" disabled="true" class="btn" onclick="deleteRow1()" />

                    <tr><td> &nbsp;</td></tr>
                    <tr><td><b>2.2 Perihal Tanah</b></td></tr>
                    <tr><td>
                            <table id ="dataTable2">
                                <c:set var="i" value="1" />
                                <c:forEach items="${actionBean.senaraiKertasKandungan22}" var="line">
                                    <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                        <td><font style="text-transform: uppercase"><s:textarea name="kandungan2${i}" id="kandungan2${i}" rows="5" disabled="true" cols="150">${line.kandungan}</s:textarea></font></td>
                                    </tr>
                                    <s:hidden name="idKandungan2${i}" id="idKandungan2${i}" value="${line.idKandungan}" />
                                    <c:set var="i" value="${i+1}" />
                                </c:forEach>
                                    <s:hidden name="rowCount2" value="${i-1}" id="rowCount2"/>
                            </table>
                    <tr><td align="right"><s:button name="perihalTanah" value="Tambah" disabled="true" class="btn" onclick="addRow2('dataTable2')" />
                            <s:button name="perihalTanah" value="Hapus" class="btn" disabled="true" onclick="deleteRow2()" />

                    <tr><td> &nbsp;</td></tr>
                    <tr><td><b>2.3 Anggaran Nilaian Pampasan</b></td></tr>
                    <tr><td>
                            <table id ="dataTable3">
                                <c:set var="i" value="1" />
                                <c:forEach items="${actionBean.senaraiKertasKandungan23}" var="line">
                                    <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                        <td><font style="text-transform: uppercase"><s:textarea name="kandungan3${i}" id="kandungan3${i}" disabled="true" rows="5" cols="150">${line.kandungan}</s:textarea></font></td>
                                    </tr>
                                    <s:hidden name="idKandungan3${i}" id="idKandungan3${i}" value="${line.idKandungan}" />
                                    <c:set var="i" value="${i+1}" />
                                </c:forEach>
                                    <s:hidden name="rowCount3" value="${i-1}" id="rowCount3"/>
                            </table>
                    <tr><td align="right"><s:button name="anggaranNilaianPampasan" disabled="true" value="Tambah" class="btn" onclick="addRow3('dataTable3')" />
                            <s:button name="anggaranNilaianPampasan" value="Hapus" disabled="true" class="btn" onclick="deleteRow3()" />

                    <tr><td> &nbsp;</td></tr>
                    <tr><td><b>2.4 Perihal Pemohon</b></td></tr>
                    <tr><td>
                            <table id ="dataTable4">
                                <c:set var="i" value="1" />
                                <c:forEach items="${actionBean.senaraiKertasKandungan24}" var="line">
                                    <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                        <td><font style="text-transform: uppercase"><s:textarea name="kandungan4${i}" id="kandungan4${i}" disabled="true" rows="5" cols="150">${line.kandungan}</s:textarea></font></td>
                                    </tr>
                                    <s:hidden name="idKandungan4${i}" id="idKandungan4${i}" value="${line.idKandungan}" />
                                    <c:set var="i" value="${i+1}" />
                                </c:forEach>
                                    <s:hidden name="rowCount4" value="${i-1}" id="rowCount4"/>
                            </table>
                    <tr><td align="right"><s:button name="perihalPemohon" value="Tambah" disabled="true" class="btn" onclick="addRow4('dataTable4')" />
                            <s:button name="perihalPemohon" value="Hapus" class="btn" disabled="true" onclick="deleteRow4()" />


                    <tr><td> &nbsp;</td></tr>
                    <tr><td><b>2.5. Ringkasan Permohonan</b></td></tr>
                    <tr><td><b> &nbsp;</b><font style="text-transform: uppercase"><s:textarea name="ringkasanPermohonan.kandungan" disabled="true" rows="5" cols="150" id="ringkasanPermohonan" /></font></td></tr><br>

                    <tr><td> &nbsp;</td></tr>
                    <tr><td><b>3. PERAKUAN PENTADBIR TANAH ${actionBean.hakmilik.daerah.nama}</b></td></tr>
                    <tr><td><b> &nbsp;</b><font style="text-transform: uppercase"><s:textarea name="syorPengarah.kandungan" disabled="true" rows="5" cols="150" id="peruntukanKewangan" /></font></td></tr><br>
                    <tr><td> &nbsp;</td></tr>
                    <tr><td><b><font style="text-transform:uppercase">4. PERAKUAN PENGARAH TANAH DAN GALIAN</font></b></td></tr>

                    <c:forEach var="i" begin="1" end="${actionBean.count4}">
                    <tr><td>
                         <table  width="90%" id="table4${i}" >
                          <c:set var="recordCount4" value="0"/>
                             <c:forEach items="${actionBean.senaraiPerakuanPengarah[i]}" var="senarai">
                                <c:set var="recordCount4" value="${recordCount4+1}"/>
                                   <c:if test="${recordCount4 eq 1}">
                                   <tr> <td><b>4.${i}</b></td>
                                      <td colspan="2"><s:textarea rows="5" cols="130" name="perakuanPengarah${i}${recordCount4}" id="perakuanPengarah${i}${recordCount4}" value="${senarai.kandungan}" /></td>
                                      <td><s:hidden name="count4${i}" id="count4${i}" value="${(fn:length(actionBean.senaraiPerakuanPengarah[i]))}" /> </td>
                                      <td><s:button class="btn" value="Tambah 4.${i}" name="add" id="tambah4${i}" onclick="addDynamicRow4('table4${i}','count4${i}')"/></td>
                                      <td><s:button class="btn" value="Hapus 4.${i} sub" name="hapus" id="hapus4${i}" onclick="deleteDynamicRow4('table4${i}','count4${i}','${formPtg}','${form1}')"/></td>
                                   </c:if>
                                   <c:if test="${recordCount4 ne 1}">
                                   <tr>  <td></td>
                                       <td><b><c:out value="${actionBean.str[recordCount4-1]}"/>.</b></td>
                                       <td><s:textarea rows="5" cols="130" name="perakuanPengarah${i}${recordCount4}" id="perakuanPengarah${i}${recordCount4}" value="${senarai.kandungan}" /></td>
                                   </c:if>
                                  <s:hidden name="kand4${i}${recordCount4}" id="kand4${i}${recordCount4}" value="${senarai.idKandungan}" />
                              </c:forEach>
                         </table>
                        </td></tr>

                    </c:forEach>
                    <tr><td align="right">
                    <div id="Tables4">

                    </div>
                    <s:button class="btn" value="Tambah" name="add" onclick="addTable4('Tables4')"/>
                    <s:button class="btn" value="Hapus" name="add" onclick="deleteTable4('${formPtg}','${form1}')" style="font-size:12px"/>

                </table>
            </div>
        </fieldset>
    </div>
                    <p align="center">
                        <s:hidden name="count4" id="count4" value="${actionBean.count4}"/>
                        <s:hidden name="temp4" id="temp4" value="${actionBean.count4}"/>
                        <s:hidden name="idKertas" value="${permohonanKertas.idKertas}"/>
                        <s:button name="simpanPtg" id="save" value="Simpan PTG" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </p>
    </c:if>
</s:form>
