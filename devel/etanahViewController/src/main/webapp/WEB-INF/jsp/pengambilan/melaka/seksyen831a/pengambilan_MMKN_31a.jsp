<%--
    Document   : pengambilan_MMKN_31a
    Created on : Jul 30, 2010, 10:35:08 AM
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


     function addRow3(tableID) {
        $("#rowCount3").val(1);
        var table = document.getElementById(tableID);

        var rowCount3 = table.rows.length;
        var row = table.insertRow(rowCount3);

        var cell2 = row.insertCell(0);
        cell2.innerHTML = "<b>&nbsp;&nbsp;&nbsp;&nbsp;"+"3.1." +(rowCount3+1)+"</b>";

        var cell1 = row.insertCell(1);
        var element1 = document.createElement("textarea");
        element1.t = "kandungan3"+(rowCount3+1);
        element1.rows = 5;
        element1.cols = 150;
        element1.name ="kandungan3"+(rowCount3+1);
        element1.id ="kandungan3"+(rowCount3+1);
        cell1.appendChild(element1);
        <%--document.form2.rowCount3.value=rowCount3 +1;--%>
        $("#rowCount3").val(rowCount3+1);
    }

    function deleteRow3()
    {
        var i = $("#rowCount3").val();
        <%--var i = document.form2.rowCount3.value;--%>
        var x= document.getElementById('dataTable3').rows[i-1].cells;
        var idKandungan = x[0].innerHTML;

        document.getElementById('dataTable3').deleteRow(i-1);
        <%--document.form2.rowCount3.value = i -1;--%>
        $("#rowCount3").val(i -1);

        if (document.getElementById('idKandungan3'+(i)) != null) {
            var url = '${pageContext.request.contextPath}/pengambilan/borang_mmkn_31a?deleteSingle&idKandungan='
            +idKandungan;

        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
        }
    }


    function addRow4(tableID2) {
        $("#rowCount4").val(1);
        <%--document.form2.rowCount4.value = 1;--%>
        var table = document.getElementById(tableID2);

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
        <%--document.form2.rowCount4.value=rowCount4 +1;--%>
        $("#rowCount4").val(rowCount4+1);
    }
    function deleteRow4()
    {
        var j = $("#rowCount4").val();
        <%--var j = document.form2.rowCount4.value;--%>
        var x= document.getElementById('dataTable4').rows[j-1].cells;
        var idKandungan = x[0].innerHTML;

        document.getElementById('dataTable4').deleteRow(j-1);
        <%--document.form2.rowCount4.value = j -1;--%>
        $("#rowCount4").val(j -1);

        if (document.getElementById('idKandungan4'+(j)) != null) {
            var url = '${pageContext.request.contextPath}/pengambilan/borang_mmkn_31a?deleteSingle&idKandungan='
            +idKandungan;

        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
        }
    }




    function addRow6(tableID4) {
        $("#rowCount6").val(1);
        <%--document.form2.rowCount6.value = 1;--%>
        var table = document.getElementById(tableID4);

        var rowCount6 = table.rows.length;
        var row = table.insertRow(rowCount6);

        var cell2 = row.insertCell(0);
        cell2.innerHTML = "<b>"+"6." +(rowCount6+1)+"</b>";

        var cell1 = row.insertCell(1);
        var element1 = document.createElement("textarea");
        element1.t = "kandungan6"+(rowCount6+1);
        element1.rows = 5;
        element1.cols = 150;
        element1.name ="kandungan6"+(rowCount6+1);
        element1.id ="kandungan6"+(rowCount6+1);
        cell1.appendChild(element1);
        //alert(element1.name)
        <%--document.form2.rowCount6.value=rowCount6 +1;--%>
         $("#rowCount6").val(rowCount6+1);
    }
    function deleteRow6()
    {
        var k = $("#rowCount6").val();
        <%--var k = document.form2.rowCount6.value;--%>
        var x= document.getElementById('dataTable6').rows[k-1].cells;
        var idKandungan = x[0].innerHTML;

        document.getElementById('dataTable6').deleteRow(k-1);
        document.form2.rowCount6.value = k -1;
        $("#rowCount6").val(k -1);

        if (document.getElementById('idKandungan6'+(k)) != null) {
            var url = '${pageContext.request.contextPath}/pengambilan/borang_mmkn_31a?deleteSingle&idKandungan='
            +idKandungan;

        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
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
        cell0.style.backgroundColor = ("#328aa4");
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
        button.setAttribute('value','Tambah'+' 5.'+(count5));
        button.onclick=function(){addDynamicRow5(table5.id,element2.id);};
        cell3.appendChild(button);

        var button1 = document.createElement('input');
        var buttonName1 = "hapus5" +(count5);
        button1.setAttribute('type','button');
        button1.className = "btn";
        button1.setAttribute('name',buttonName1);
        button1.setAttribute('value','Hapus'+' 5.'+(count5));
        button1.onclick=function(){deleteDynamicRow5(table5.id,element2.id);};
        cell3.appendChild(button1);

        id1.appendChild(table5);
        id1.appendChild(document.createElement('br'));
    }

    function deleteTable5() {
        var bil = 5;
        var temp5 = $("#temp5").val();
        var count5 = $("#count5").val();

         var table5 = document.getElementById("table5"+count5);
             var rowCount7 = table5.rows.length;
             for(var i=rowCount7-1;i>=0;i--){
                table5.deleteRow(i);
                document.getElementById ("count5").value = parseInt(count5)-1;
             }

        if(parseInt(count5) <= parseInt(temp5)) {

            var url = '${pageContext.request.contextPath}/pengambilan/borang_mmkn_31a?deleteTable&bil='
            +bil;

        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');

      }


    }

     function addDynamicRow5(tableID,countID) {

        var table5 = document.getElementById(tableID);
        var rowCount7 = table5.rows.length;
        var row5 = table5.insertRow(rowCount7);

        document.getElementById(countID).value = parseInt(document.getElementById(countID).value)+1;

        var count5 = parseInt(tableID.substring(6));

        var cell0 = row5.insertCell(0);
         cell0.innerHTML = "";

         var cell1 = row5.insertCell(1);
          var arr=['a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'];
         cell1.innerHTML = "<b>"+arr[rowCount7-1]+".</b>";

        var cell2 = row5.insertCell(2);
        var element2 = document.createElement("textarea");
        element2.t = "syorPentadbir"+(count5)+(rowCount7+1);
        element2.rows = 5;
        element2.cols = 130;
        element2.name ="syorPentadbir"+(count5)+(rowCount7+1);
        element2.id ="syorPentadbir"+(count5)+(rowCount7+1);
        <%--element2.value ="syorPentadbir"+(count5)+(rowCount7+1);--%>
        cell2.appendChild(element2);

    }

    function deleteDynamicRow5(tableID,countID) {
        var table5 = document.getElementById(tableID);
        var rowCount7 = table5.rows.length;
        var i = tableID.substring(6);

        if(rowCount7 >1){
            var obj = document.getElementById("kand5"+(i)+(rowCount7));
            var idKandungan = $("#kand5"+(i)+(rowCount7)).val();
            table5.deleteRow(rowCount7-1);
            document.getElementById(countID).value = table5.rows.length;
            if (obj != null) {

                var url = '${pageContext.request.contextPath}/pengambilan/borang_mmkn_31a?deleteSingle&idKandungan='
                    +idKandungan;

                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
            }

        }else{
            alert("Cannot delete row .")
        }

    }

    function addTable8(divId){

        var id1 = document.getElementById(divId);
        var count8 = document.getElementById ("count8").value;
        // Increment table count by 1
        document.getElementById ("count8").value = parseInt(count8)+1;
        count8 = document.getElementById ("count8").value;

        // create New table
        var table8 = document.createElement("TABLE");
        table8.id = "table8"+count8;
        //table.className = "tablecloth";
        table8.width="100%";
        //table.border=2;
        var rowCount7 = table8.rows.length;
        var row7 = table8.insertRow(rowCount7);

        var cell0 = row7.insertCell(0);
        cell0.innerHTML = "<b>8."+(count8)+"</b>";
        cell0.style.textAlign = "center";
        cell0.style.backgroundColor = ("#328aa4");
        cell0.width="2%";

        var cell1 = row7.insertCell(1);
        var element1 = document.createElement("textarea");
        element1.t = "syorPengarah"+(count8)+(rowCount7+1);
        element1.rows = 5;
        element1.cols = 130;
        element1.name ="syorPengarah"+(count8)+(rowCount7+1);
        element1.id ="syorPengarah"+(count8)+(rowCount7+1);
        <%--element1.value ="syorPentadbir"+(count5)+(rowCount5+1);--%>
        cell1.colSpan = 2;
        cell1.appendChild(element1);

        // Add hidden field
        var cell2 = row7.insertCell(2);
        var element2 = document.createElement ("input");
        element2.setAttribute("type", "hidden");
        element2.setAttribute("id", "count8"+(count8));
        element2.setAttribute("name", "count8"+(count8));
        element2.setAttribute("value", 1);
        cell2.appendChild(element2);

        // Add tambah button
        var cell3 = row7.insertCell(3);
        var button = document.createElement('input');
        var buttonName = "tambah8" +(count8);
        button.setAttribute('type','button');
        button.className = "btn";
        button.setAttribute('name',buttonName);
        button.setAttribute('value','Tambah'+' 8.'+(count8));
        button.onclick=function(){addDynamicRow8(table8.id,element2.id);};
        cell3.appendChild(button);

        var button1 = document.createElement('input');
        var buttonName1 = "hapus8" +(count8);
        button1.setAttribute('type','button');
        button1.className = "btn";
        button1.setAttribute('name',buttonName1);
        button1.setAttribute('value','Hapus'+' 8.'+(count8));
        button1.onclick=function(){deleteDynamicRow8(table8.id,element2.id);};
        cell3.appendChild(button1);

        id1.appendChild(table8);
        id1.appendChild(document.createElement('br'));
    }

    function deleteTable8() {
        var bil = 8;
        var temp8 = $("#temp8").val();
        var count8 = $("#count8").val();

         var table8 = document.getElementById("table8"+count8);
             var rowCount8 = table8.rows.length;
             for(var i=rowCount8-1;i>=0;i--){
                table8.deleteRow(i);
                document.getElementById ("count8").value = parseInt(count8)-1;
             }

        if(parseInt(count8) <= parseInt(temp8)) {

            var url = '${pageContext.request.contextPath}/pengambilan/borang_mmkn_31a?deleteTable&bil='
            +bil;

        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');

      }


    }

     function addDynamicRow8(tableID,countID) {

        var table8 = document.getElementById(tableID);
        var rowCount8 = table8.rows.length;
        var row8 = table8.insertRow(rowCount8);

        document.getElementById(countID).value = parseInt(document.getElementById(countID).value)+1;

        var count8 = parseInt(tableID.substring(6));

        var cell0 = row8.insertCell(0);
         cell0.innerHTML = "";

         var cell1 = row8.insertCell(1);
          var arr=['a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'];
         cell1.innerHTML = "<b>"+arr[rowCount8-1]+".</b>";

        var cell2 = row8.insertCell(2);
        var element2 = document.createElement("textarea");
        element2.t = "syorPengarah"+(count8)+(rowCount8+1);
        element2.rows = 5;
        element2.cols = 130;
        element2.name ="syorPengarah"+(count8)+(rowCount8+1);
        element2.id ="syorPengarah"+(count8)+(rowCount8+1);
        <%--element2.value ="syorPentadbir"+(count8)+(rowCount8+1);--%>
        cell2.appendChild(element2);

    }

    function deleteDynamicRow8(tableID,countID) {
        var table8 = document.getElementById(tableID);
        var rowCount8 = table8.rows.length;
        var i = tableID.substring(6);

        if(rowCount8 >1){
            var obj = document.getElementById("kand8"+(i)+(rowCount8));
            var idKandungan = $("#kand8"+(i)+(rowCount8)).val();
            table8.deleteRow(rowCount8-1);
            document.getElementById(countID).value = table8.rows.length;
            if (obj != null) {

                var url = '${pageContext.request.contextPath}/pengambilan/borang_mmkn_31a?deleteSingle&idKandungan='
                    +idKandungan;

                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
            }

        }else{
            alert("Cannot delete row .")
        }

    }

    function validation() {
     if($("#ulasan1").val() == ""){
                $("#ulasan1").val("Tiada Data");

        }

    var count1=$("#rowCount3").val();
    for(var i=1;i<=count1;i++){
        var kandungan3= $("#kandungan3"+i).val();
        if(kandungan3 == ""){
            alert('Sila pilih " 3.1 Perihal Permohonan " terlebih dahulu.');
            $("#kandungan3"+i).focus();
            return false;
        }
    }

    var count2=$("#rowCount4").val();
    for(var i=1;i<=count2;i++){
        var kandungan4= $("#kandungan4"+i).val();
        if(kandungan4 == ""){
            alert('Sila pilih " 4. NILAIAN TANAH DAN PERUNTUKAN " terlebih dahulu.');
            $("#kandungan4"+i).focus();
            return false;
        }
    }

    var count5 = $("#count5").val();
    for(var i=1;i<=count5;i++){
        var recordCount5 = $("#count5"+i).val();
        for(var j=1;j<=recordCount5;j++){
            var syorPentadbir = $("#syorPentadbir"+i+j).val();
            if(syorPentadbir == ""){
                alert('Sila pilih " 5. SYOR PENTADBIR DAERAH " terlebih dahulu.');
                $("#syorPentadbir"+i+j).focus();
                return false;
            }
        }

    }

    var count3=$("#rowCount6").val();
    for(var i=1;i<=count3;i++){
        var kandungan6= $("#kandungan6"+i).val();
        if(kandungan6 == ""){
            alert('Sila pilih " 6. ULASAN PENGARAH TANAH DAN GALIAN " terlebih dahulu.');
            $("#kandungan6"+i).focus();
            return false;
        }
    }

    var count8 = $("#count8").val();
    for(var i=1;i<=count8;i++){
        var recordCount = $("#count8"+i).val();
        for(var j=1;j<=recordCount;j++){
            var syorPengarah = $("#syorPengarah"+i+j).val();
            if(syorPengarah == ""){
                <%--alert('Sila pilih " 8. SYOR PENGARAH TANAH DAN GALIAN " terlebih dahulu.');--%>
                $("#syorPengarah"+i+j).val("Tiada Data");
                return false;
            }
        }

    }

    return true;

    }
</script>


<s:form beanclass="etanah.view.stripes.pengambilan.PengambilanMMKN31aActionBean" name="form2">
    <s:messages/>
    <s:errors/>
     <s:hidden name="count5" id="count5" value="${actionBean.count5}"/>
    <s:hidden name="temp5" id="temp5" value="${actionBean.count5}"/>
    <s:hidden name="count8" id="count8" value="${actionBean.count8}"/>
    <s:hidden name="temp8" id="temp8" value="${actionBean.count8}"/>
    <c:if test="${form}">
        <div class="subtitle">
            <fieldset class="aras1" id="locate">
                <legend></legend>
                <div class="content" align="center">
                    <table border="0" width="80%">

                       <tr><td><br/><b>1. <%--<font style="text-transform: uppercase">PERMOHONAN PENGAMBILAN TANAH DI ATAS LOT
                                        <c:forEach items="${actionBean.hakmilikPermohonanList}" var="senaraiHakmilik" >
                                            <c:out value="${senaraiHakmilik.hakmilik.noLot}"/>,
                                        </c:forEach>

                                           SELUAS ${actionBean.hakmilik.luas} ${actionBean.hakmilik.kodUnitLuas.nama} , DI ${actionBean.hakmilik.bandarPekanMukim.nama}, DAERAH ${actionBean.hakmilik.daerah.nama}, NEGERI SEMBILAN UNTUK TUJUAN RIZAB PARIT, DI BAWAH SEKSYEN 3(1)(a) AKTA PENGAMBILAN TANAH 1960.</font>--%>
                        <s:hidden name="heading" />
                        ${actionBean.heading}</b><br /><br /></td></tr>

                        <tr><td><b>2. TUJUAN</b></td></tr>
                        <tr><tr><tr><td colspan="2"><b> &nbsp;</b><s:textarea rows="5" cols="140" name="tujuan"/><td>

                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b>3. LATAR BELAKANG</b></td></tr>

                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b>&nbsp;&nbsp;&nbsp;&nbsp;3.1 Perihal Permohonan</b></td></tr>
                        <tr><td>
                                <table id ="dataTable3">
                                    <c:set var="i" value="1" />
                                    <c:forEach items="${actionBean.senaraiKertasKandungan3}" var="line">
                                        <tr style="font-weight:bold">
                                            <td style="display:none">${line.idKandungan}</td>
                                            <td>&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${line.subtajuk}"/></td>
                                            <td><font style="text-transform: uppercase"><s:textarea name="kandungan3${i}" id="kandungan3${i}" rows="5" cols="150" onkeyup="this.value=this.value.toUpperCase();">${line.kandungan}</s:textarea></font></td>
                                        </tr>
                                        <s:hidden name="idKandungan3${i}" id="idKandungan3${i}" value="${line.idKandungan}" />
                                            <c:set var="i" value="${i+1}" />
                                        </c:forEach>
                                        <s:hidden name="rowCount3" value="${i-1}" id="rowCount3"/>
                                </table>
                        <tr><td align="right"><s:button name="perihalPermohonan" value="Tambah" class="btn" onclick="addRow3('dataTable3')" />
                                <s:button name="perihalPermohonan" value="Hapus" class="btn" onclick="deleteRow3()" />
                            </td>
                        </tr>

                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b>4. Perihal Tanah</b></td></tr>
                        <tr><td>
                                <table id ="dataTable4">
                                    <c:set var="i" value="1" /><c:forEach items="${actionBean.senaraiKertasKandungan4}" var="line">
                                        <tr style="font-weight:bold">
                                            <td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                            <td><font style="text-transform: uppercase"><s:textarea name="kandungan4${i}" id="kandungan4${i}" rows="5" cols="150" onkeyup="this.value=this.value.toUpperCase();">${line.kandungan}</s:textarea></font></td>
                                        </tr>
                                        <s:hidden name="idKandungan4${i}" id="idKandungan4${i}" value="${line.idKandungan}" />
                                            <c:set var="i" value="${i+1}" />
                                        </c:forEach>
                                        <s:hidden name="rowCount4" value="${i-1}" id="rowCount4"/>
                                </table>
                        <tr><td align="right"><s:button name="nilaianTanah" value="Tambah" class="btn" onclick="addRow4('dataTable4')" />
                                <s:button name="nilaianTanah" value="Hapus" class="btn" onclick="deleteRow4()" />
                            </td>
                        </tr>
                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b><font style="text-transform:uppercase">5. SYOR PENTADBIR TANAH DAERAH ${actionBean.hakmilik.daerah.nama}</font></b></td></tr>

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
                                                    <td><s:button class="btn" value="Hapus 5.${i}" name="hapus" id="hapus5${i}" onclick="deleteDynamicRow5('table5${i}','count5${i}')"/></td>
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
                                    <s:button class="btn" value="Tambah" name="add" onclick="addTable5('Tables5')"/>
                                    <s:button class="btn" value="Hapus" name="add" onclick="deleteTable5()"/>

                        <tr><td> &nbsp;</td></tr>
                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b><font style="text-transform:uppercase">6. PERAKUAN PENTADBIR TANAH ${actionBean.hakmilik.daerah.nama}</font></b></td></tr>
                        <tr><td>
                                <table id ="dataTable6">
                                    <c:set var="k" value="1" />
                                      <c:forEach items="${actionBean.senaraiKertasKandungan6}" var="line">
                                        <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                            <td><font style="text-transform:uppercase"><s:textarea name="kandungan6${k}" id="kandungan6${k}" rows="5" cols="150" onkeyup="this.value=this.value.toUpperCase();">${line.kandungan}</s:textarea></font>
                                            </td></tr>
                                        <s:hidden name="idKandungan6${k}" id="idKandungan6${k}" value="${line.idKandungan}" />
                                            <c:set var="k" value="${k+1}" />
                                        </c:forEach>
                                        <s:hidden name="rowCount6" value="${k-1}" id="rowCount6"/>
                                </table>
                        <tr><td align="right"><s:button name="ulasanPengarahTanah" value="Tambah" class="btn" onclick="addRow6('dataTable6')" />
                                <s:button name="ulasanPengarahTanah" value="Hapus" class="btn" onclick="deleteRow6()" />

                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b>7. KEPUTUSAN JAWATANKUASA KHAS PENGAMBILAN TANAH NEGERI MELAKA</b></td></tr>
                        <tr><td><b> &nbsp;</b><font style="text-transform: uppercase"><s:textarea name="ulasan1.kandungan" rows="5" readonly="false" cols="150" id="ulasan1"/>
                                </font></td></tr><br>

                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b><font style="text-transform:uppercase">8. PERAKUAN PENGARAH TANAH DAN GALIAN</font></b></td></tr>

                        <c:forEach var="i" begin="1" end="${actionBean.count8}">
                            <tr><td>
                                    <table  width="90%" id="table8${i}" >
                                        <c:set var="recordCount8" value="0"/>
                                        <c:forEach items="${actionBean.senaraiSyorPengarah[i]}" var="senarai">
                                            <c:set var="recordCount8" value="${recordCount8+1}"/>
                                            <c:if test="${recordCount8 eq 1}">
                                                <tr> <td><b>8.${i}</b></td>
                                                    <td colspan="2"><s:textarea rows="5" cols="130" readonly="true" name="syorPengarah${i}${recordCount8}" id="syorPengarah${i}${recordCount8}" value="${senarai.kandungan}" /></td>
                                                    <td><s:hidden name="count8${i}" id="count8${i}" value="${(fn:length(actionBean.senaraiSyorPengarah[i]))}" /> </td>
                                                    <td><s:button class="btn" value="Tambah 8.${i}" name="add" id="tambah8${i}" onclick="addDynamicRow8('table8${i}','count8${i}')"/></td>
                                                    <td><s:button class="btn" value="Hapus 8.${i}" name="hapus" id="hapus8${i}" onclick="deleteDynamicRow8('table8${i}','count8${i}')"/></td>
                                            </c:if>
                                            <c:if test="${recordCount8 ne 1}">
                                            <tr>  <td></td>
                                                  <td><b><c:out value="${actionBean.str[recordCount8-1]}"/>.</b></td>
                                                  <td><s:textarea rows="5" cols="130" name="syorPengarah${i}${recordCount8}" id="syorPengarah${i}${recordCount8}" value="${senarai.kandungan}" /></td>
                                            </c:if>
                                            <s:hidden name="kand8${i}${recordCount8}" id="kand8${i}${recordCount8}" value="${senarai.idKandungan}" />
                                        </c:forEach>
                                    </table>
                                </td></tr>

                        </c:forEach>
                        <tr><td align="right">
                        <div id="Tables8">

                        </div>
                        <s:button class="btn" disabled="true" value="Tambah" name="add" onclick="addTable8('Tables8')"/>
                        <s:button class="btn" disabled="true" value="Hapus" name="add" onclick="deleteTable8()"/>



                        <tr><td> &nbsp;</td></tr>

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


    <c:if test="${view}">
        <div class="subtitle">
            <fieldset class="aras1" id="locate">
                <legend></legend>
                <div class="content" align="center">
                    <table border="0" width="80%">

                       <tr><td><br/><b>1. <%--<font style="text-transform: uppercase">PERMOHONAN PENGAMBILAN TANAH DI ATAS LOT
                                        <c:forEach items="${actionBean.hakmilikPermohonanList}" var="senaraiHakmilik" >
                                            <c:out value="${senaraiHakmilik.hakmilik.noLot}"/>,
                                        </c:forEach>

                                           SELUAS ${actionBean.hakmilik.luas} ${actionBean.hakmilik.kodUnitLuas.nama} , DI ${actionBean.hakmilik.bandarPekanMukim.nama}, DAERAH ${actionBean.hakmilik.daerah.nama}, NEGERI SEMBILAN UNTUK TUJUAN RIZAB PARIT, DI BAWAH SEKSYEN 3(1)(a) AKTA PENGAMBILAN TANAH 1960.</font>--%>
                        <s:hidden name="heading" />
                        ${actionBean.heading}</b><br /><br /></td></tr>

                        <tr><td><b>2. TUJUAN</b></td></tr>
                        <tr><tr><tr><td colspan="2"><b> &nbsp;</b>
                            <%--<TEXTAREA NAME="tujuan" COLS=140 ROWS=5>${actionBean.tujuan}</TEXTAREA>--%>
                            <s:textarea rows="5" cols="140" name="tujuan" readonly="true"/><td>

                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b>3. LATAR BELAKANG</b></td></tr>

                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b>&nbsp;&nbsp;&nbsp;&nbsp;3.1 Perihal Permohonan</b></td></tr>
                        <tr><td>
                                <table id ="dataTable3">
                                    <c:set var="i" value="1" />
                                    <c:forEach items="${actionBean.senaraiKertasKandungan3}" var="line">
                                        <tr style="font-weight:bold">
                                            <td style="display:none">${line.idKandungan}</td>
                                            <td>&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${line.subtajuk}"/></td>
                                            <td><font style="text-transform: uppercase"><s:textarea name="kandungan3${i}" readonly="true" id="kandungan3${i}" rows="5" cols="150" onkeyup="this.value=this.value.toUpperCase();">${line.kandungan}</s:textarea></font></td>
                                        </tr>
                                        <s:hidden name="idKandungan3${i}" id="idKandungan3${i}" value="${line.idKandungan}" />
                                            <c:set var="i" value="${i+1}" />
                                        </c:forEach>
                                        <s:hidden name="rowCount3" value="${i-1}" id="rowCount3"/>
                                </table>
                        <tr><td align="right"><s:button  disabled="true" name="perihalPermohonan" value="Tambah" class="btn" onclick="addRow3('dataTable3')" />
                                <s:button name="perihalPermohonan" disabled="true" value="Hapus" class="btn" onclick="deleteRow3()" />
                            </td>
                        </tr>

                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b>4. Perihal Tanah</b></td></tr>
                        <tr><td>
                                <table id ="dataTable4">
                                    <c:set var="i" value="1" /><c:forEach items="${actionBean.senaraiKertasKandungan4}" var="line">
                                        <tr style="font-weight:bold">
                                            <td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                            <td><font style="text-transform: uppercase"><s:textarea name="kandungan4${i}" readonly="true" id="kandungan4${i}" rows="5" cols="150" onkeyup="this.value=this.value.toUpperCase();">${line.kandungan}</s:textarea></font></td>
                                        </tr>
                                        <s:hidden name="idKandungan4${i}" id="idKandungan4${i}" value="${line.idKandungan}" />
                                            <c:set var="i" value="${i+1}" />
                                        </c:forEach>
                                        <s:hidden name="rowCount4" value="${i-1}" id="rowCount4"/>
                                </table>
                        <tr><td align="right"><s:button name="nilaianTanah" disabled="true" value="Tambah" class="btn" onclick="addRow4('dataTable4')" />
                                <s:button name="nilaianTanah" disabled="true" value="Hapus" class="btn" onclick="deleteRow4()" />
                            </td>
                        </tr>
                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b><font style="text-transform:uppercase">5. SYOR PENTADBIR TANAH DAERAH ${actionBean.hakmilik.daerah.nama}</font></b></td></tr>

                        <c:forEach var="i" begin="1" end="${actionBean.count5}">
                            <tr><td>
                                    <table  width="90%" id="table5${i}" >
                                        <c:set var="recordCount5" value="0"/>
                                        <c:forEach items="${actionBean.senaraiSyorPentadbir[i]}" var="senarai">
                                            <c:set var="recordCount5" value="${recordCount5+1}"/>
                                            <c:if test="${recordCount5 eq 1}">
                                                <tr> <td><b>5.${i}</b></td>
                                                    <td colspan="2"><s:textarea rows="5" cols="130" name="syorPentadbir${i}${recordCount5}" readonly="true" id="syorPentadbir${i}${recordCount5}" value="${senarai.kandungan}" /></td>
                                                    <td><s:hidden name="count5${i}" id="count5${i}" value="${(fn:length(actionBean.senaraiSyorPentadbir[i]))}" /> </td>
                                                    <td><s:button class="btn" disabled="true" value="Tambah 5.${i}" name="add" id="tambah5${i}" onclick="addDynamicRow5('table5${i}','count5${i}')"/></td>
                                                    <td><s:button class="btn" disabled="true" value="Hapus 5.${i}" name="hapus" id="hapus5${i}" onclick="deleteDynamicRow5('table5${i}','count5${i}')"/></td>
                                            </c:if>
                                            <c:if test="${recordCount5 ne 1}">
                                            <tr>  <td></td>
                                                <td><b><c:out value="${actionBean.str[recordCount5-1]}"/>.</b></td>
                                                <td><s:textarea rows="5" readonly="true" cols="130" name="syorPentadbir${i}${recordCount5}" id="syorPentadbir${i}${recordCount5}" value="${senarai.kandungan}" /></td>
                                            </c:if>
                                            <s:hidden name="kand5${i}${recordCount5}" id="kand5${i}${recordCount5}" value="${senarai.idKandungan}" />
                                        </c:forEach>
                                    </table>
                                </td></tr>

                        </c:forEach>
                            <tr><td align="right">
                                    <div id="Tables5">

                                    </div>
                                    <s:button class="btn" disabled="true" value="Tambah" name="add" onclick="addTable5('Tables5')"/>
                                    <s:button class="btn" disabled="true" value="Hapus" name="add" onclick="deleteTable5()"/>

                        <tr><td> &nbsp;</td></tr>
                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b><font style="text-transform:uppercase">6. PERAKUAN PENTADBIR TANAH ${actionBean.hakmilik.daerah.nama}</font></b></td></tr>
                        <tr><td>
                                <table id ="dataTable6">
                                    <c:set var="k" value="1" />
                                      <c:forEach items="${actionBean.senaraiKertasKandungan6}" var="line">
                                        <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                            <td><font style="text-transform:uppercase"><s:textarea name="kandungan6${k}" readonly="true" id="kandungan6${k}" rows="5" cols="150" onkeyup="this.value=this.value.toUpperCase();">${line.kandungan}</s:textarea></font>
                                            </td></tr>
                                        <s:hidden name="idKandungan6${k}" id="idKandungan6${k}" value="${line.idKandungan}" />
                                            <c:set var="k" value="${k+1}" />
                                        </c:forEach>
                                        <s:hidden name="rowCount6" value="${k-1}" id="rowCount6"/>
                                </table>
                        <tr><td align="right"><s:button name="ulasanPengarahTanah" disabled="true" value="Tambah" class="btn" onclick="addRow6('dataTable6')" />
                                <s:button name="ulasanPengarahTanah" disabled="true" value="Hapus" class="btn" onclick="deleteRow6()" />

                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b>7. KEPUTUSAN JAWATANKUASA KHAS PENGAMBILAN TANAH NEGERI MELAKA</b></td></tr>
                        <tr><td><b> &nbsp;</b><font style="text-transform: uppercase"><s:textarea name="ulasan1.kandungan" rows="5" readonly="true" cols="150" id="ulasan1"/>
                                </font></td></tr><br>

                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b><font style="text-transform:uppercase">8. PERAKUAN PENGARAH TANAH DAN GALIAN</font></b></td></tr>

                        <c:forEach var="i" begin="1" end="${actionBean.count8}">
                            <tr><td>
                                    <table  width="90%" id="table8${i}" >
                                        <c:set var="recordCount8" value="0"/>
                                        <c:forEach items="${actionBean.senaraiSyorPengarah[i]}" var="senarai">
                                            <c:set var="recordCount8" value="${recordCount8+1}"/>
                                            <c:if test="${recordCount8 eq 1}">
                                                <tr> <td><b>8.${i}</b></td>
                                                    <td colspan="2"><s:textarea rows="5" cols="130" name="syorPengarah${i}${recordCount8}" id="syorPengarah${i}${recordCount8}" value="${senarai.kandungan}" /></td>
                                                    <td><s:hidden name="count8${i}" id="count8${i}" value="${(fn:length(actionBean.senaraiSyorPengarah[i]))}" /> </td>
                                                    <td><s:button class="btn" value="Tambah 8.${i}" name="add" id="tambah8${i}" onclick="addDynamicRow8('table8${i}','count8${i}')"/></td>
                                                    <td><s:button class="btn" value="Hapus 8.${i}" name="hapus" id="hapus8${i}" onclick="deleteDynamicRow8('table8${i}','count8${i}')"/></td>
                                            </c:if>
                                            <c:if test="${recordCount8 ne 1}">
                                            <tr>  <td></td>
                                                  <td><b><c:out value="${actionBean.str[recordCount8-1]}"/>.</b></td>
                                                  <td><s:textarea rows="5" cols="130" name="syorPengarah${i}${recordCount8}" id="syorPengarah${i}${recordCount8}" value="${senarai.kandungan}" /></td>
                                            </c:if>
                                            <s:hidden name="kand8${i}${recordCount8}" id="kand8${i}${recordCount8}" value="${senarai.idKandungan}" />
                                        </c:forEach>
                                    </table>
                                </td></tr>

                        </c:forEach>
                        <tr><td align="right">
                        <div id="Tables8">

                        </div>
                        <s:button class="btn" value="Tambah" name="add" onclick="addTable8('Tables8')"/>
                        <s:button class="btn" value="Hapus" name="add" onclick="deleteTable8()"/>



                        <tr><td> &nbsp;</td></tr>

                    </table>
                </div>
            </fieldset>
        </div>
        <p align="center">
            <%--<s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>--%>
            <s:hidden name="idKertas" value="${permohonanKertas.idKertas}"/>
            <%--<s:hidden name="idKandungan" value="${permohonanKertasKandungan.idKandungan}"/>--%>
            <s:button name="simpan2" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>
            <%--|| if(validateForm2()) || if(validateForm3()) || if(validateForm4()) || if(validateForm5()) || if(validateForm6()) || if(validateForm7())--%>
        </p>
    </c:if>
</s:form>
