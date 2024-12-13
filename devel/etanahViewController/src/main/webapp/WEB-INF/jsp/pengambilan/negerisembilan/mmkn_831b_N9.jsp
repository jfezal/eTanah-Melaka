<%-- 
    Document   : mmkn_831a_N9
    Created on : 15-Sep-2011, 10:03:25
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
        cell0.innerHTML = "<b>3."+(count4)+"</b>";
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
    <%--   id1.appendChild(table4);--%>
    <%--  id1.appendChild(document.createElement('br'));--%>
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

                var url = '${pageContext.request.contextPath}/pengambilan/mmkn831a?deleteTable&bil='
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

                    var url = '${pageContext.request.contextPath}/pengambilan/mmkn831a?deleteSingle&idKandungan='
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

            if (document.getElementById('idKandungan1'+(i)) != null) {
                var url = '${pageContext.request.contextPath}/pengambilan/mmkn831a?deleteSingle&idKandungan='
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
            cell2.innerHTML = "<b>"+"2." +(rowCount2+1)+"</b>";

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

            if(confirm('Adakah anda pasti?')) {



                if (document.getElementById('idKandungan2'+(j)) != null) {
                    var url = '${pageContext.request.contextPath}/pengambilan/mmkn831a?deleteSingle&idKandungan='
                        +idKandungan;
                    $.get(url,
                    function(data){
                        $('#page_div').html(data);
                    },'html');



                }
                document.getElementById('dataTable2').deleteRow(j-1);
                document.form2.rowCount2.value = j-1;
            }

        }
        function addRow3(tableID3) {
            document.form2.rowCount3.value = 1;
            var table = document.getElementById(tableID3);

            var rowCount3 = table.rows.length;
            var row = table.insertRow(rowCount3);

            var cell2 = row.insertCell(0);
            cell2.innerHTML = "<b>"+"3." +(rowCount3+1)+"</b>";

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
    <%--alert(idKandungan)--%>
            if(confirm('Adakah anda pasti?')) {
                if (document.getElementById('idKandungan3'+(k)) != null) {
    <%--alert('idKandungan3')--%>

                    var url = '${pageContext.request.contextPath}/pengambilan/mmkn831a?deleteSingle&idKandungan='
                        +idKandungan;
                    $.get(url,
                    function(data){
                        $('#page_div').html(data);
                    },'html');

                }else{
                    document.getElementById('dataTable3').deleteRow(k-1);
                    document.form2.rowCount3.value = k-1;
                }
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
            var j = document.form2.rowCount4.value;
            var x= document.getElementById('dataTable4').rows[j-1].cells;
            var idKandungan = x[0].innerHTML;

            document.getElementById('dataTable4').deleteRow(j-1);
            document.form2.rowCount4.value = j -1;

            if (document.getElementById('idKandungan4'+(j)) != null) {
                var url = '${pageContext.request.contextPath}/pengambilan/mmkn831a?deleteSingle&idKandungan='
                    +idKandungan;

                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
            }
        }
        function addRow5(tableID5) {
            document.form2.rowCount9.value = 1;
            var table = document.getElementById(tableID5);

            var rowCount9 = table.rows.length;
            var row = table.insertRow(rowCount9);

            var cell2 = row.insertCell(0);
            cell2.innerHTML = "<b>"+"5." +(rowCount9+1)+"</b>";

            var cell1 = row.insertCell(1);
            var element1 = document.createElement("textarea");
            element1.t = "kandungan5"+(rowCount9+1);
            element1.rows = 5;
            element1.cols = 150;
            element1.name ="kandungan5"+(rowCount9+1);
            element1.id ="kandungan5"+(rowCount9+1);
            cell1.appendChild(element1);
            //alert(element1.name)
            document.form2.rowCount9.value=rowCount9 +1;
        }
        function deleteRow5()
        {
            var y = document.form2.rowCount9.value;
            var x= document.getElementById('dataTable5').rows[y-1].cells;
            var idKandungan = x[0].innerHTML;

            if(confirm('Adakah anda pasti?')) {


                if (document.getElementById('idKandungan5'+(y)) != null) {
                    var url = '${pageContext.request.contextPath}/pengambilan/mmkn831a?deleteSinglePTGB&idKandungan='
                        +idKandungan;
                    $.get(url,
                    function(data){
                        $('#page_div').html(data);
                    },'html');

                }
                document.getElementById('dataTable5').deleteRow(y-1);
                document.form2.rowCount9.value = y-1;
            }
        }




        function addRow6(tableID6) {
            document.form2.rowCount6.value = 1;
            var table = document.getElementById(tableID6);

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
            document.form2.rowCount6.value=rowCount6 +1;
        }
        function deleteRow6()
        {
            var z = document.form2.rowCount6.value;
            var x= document.getElementById('dataTable6').rows[z-1].cells;
            var idKandungan = x[0].innerHTML;

            if(confirm('Adakah anda pasti?')) {


                if (document.getElementById('idKandungan6'+(z)) != null) {
                    var url = '${pageContext.request.contextPath}/pengambilan/mmkn831a?deleteSinglePTGB&idKandungan='
                        +idKandungan;
                    $.get(url,
                    function(data){
                        $('#page_div').html(data);
                    },'html');

                }
                document.getElementById('dataTable6').deleteRow(z-1);
                document.form2.rowCount6.value = z-1;
            }
        }

        function validation() {

    <%--var count1=$("#rowCount1").val();
    for(var i=1;i<=count1;i++){
        var kandungan1= $("#kandungan1"+i).val();
        if(kandungan1 == ""){
            alert('Sila pilih " 2.1 Perihal Permohonan " terlebih dahulu.');
            $("#kandungan1"+i).focus();
            return false;
        }
    }--%>

            var count2=$("#rowCount2").val();
            for(var i=1;i<=count2;i++){
                var kandungan2= $("#kandungan2"+i).val();
                if(kandungan2 == ""){
                    alert('Sila pilih " 2.0 Latar Belakang " terlebih dahulu.');
                    $("#kandungan2"+i).focus();
                    return false;
                }
            }

            var count3=$("#rowCount3").val();
            for(var i=1;i<=count3;i++){
                var kandungan3= $("#kandungan3"+i).val();
                if(kandungan3 == ""){
                    alert('Sila pilih " 3.0 Nilaian Tanah Dan Peruntukan " terlebih dahulu.');
                    $("#kandungan3"+i).focus();
                    return false;
                }
            }

            var count3=$("#rowCount9").val();
            for(var i=1;i<=count3;i++){
                var kandungan4= $("#kandungan5"+i).val();
                if(kandungan4 == ""){
                    alert('Sila pilih " 5.0 Ulasan Pengarah Tanah Dan Galian terlebih dahulu.');
                    $("#kandungan5"+i).focus();
                    return false;
                }
            }

            var count4=$("#rowCount6").val();
            for(var i=1;i<=count4;i++){
                var kandungan6= $("#kandungan6"+i).val();
                if(kandungan6 == ""){
                    alert('Sila pilih " 6.0 Syor Pengarah Tanah Dan Galian terlebih dahulu.');
                    $("#kandungan6"+i).focus();
                    return false;
                }
            }

            var count4 = $("#count4").val();
            for(var i=1;i<=count4;i++){
                var recordCount4 = $("#count4"+i).val();
                for(var j=1;j<=recordCount4;j++){
                    var perakuanPengarah = $("#perakuanPengarah"+i+j).val();
                    if(perakuanPengarah == ""){
                        alert('Sila pilih " 5. PERAKUAN PENGARAH TANAH DAN GALIAN " terlebih dahulu.');
                        $("#perakuanPengarah"+i+j).focus();
                        return false;
                    }
                }

            }
            var count5 = $("#count5").val();
            for(var i=1;i<=count5;i++){
                var recordCount5 = $("#count5"+i).val();
                for(var j=1;j<=recordCount5;j++){
                    var senaraiPentadbir = $("#senaraiPentadbir"+i+j).val();
                    if(senaraiPentadbir == ""){
                        alert('Sila pilih " 4. Syor Pentadbir Daerah " terlebih dahulu.');
                        $("#senaraiPentadbir"+i+j).focus();
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

            return true;
        }
        function addTable5(divId){
    <%--alert("add dynamic row")--%>
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
            cell0.innerHTML = "<b>4."+(count5)+"</b>";
            cell0.style.textAlign = "center";
    <%--cell0.style.backgroundColor = ("#328aa4");--%>
            cell0.width="2%";

            var cell1 = row5.insertCell(1);
            var element1 = document.createElement("textarea");
            element1.t = "senaraiPentadbir"+(count5)+(rowCount5+1);
            element1.rows = 5;
            element1.cols = 130;
            element1.name ="senaraiPentadbir"+(count5)+(rowCount5+1);
            element1.id ="senaraiPentadbir"+(count5)+(rowCount5+1);
    <%--element1.value ="senaraiPentadbir"+(count5)+(rowCount5+1);--%>
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
            button.setAttribute('value','Tambah'+' 4.'+(count5));
            button.onclick=function(){addDynamicRow5(table5.id,element2.id);};
            cell3.appendChild(button);

            var button1 = document.createElement('input');
            var buttonName1 = "hapus5" +(count5);
            button1.setAttribute('type','button');
            button1.className = "btn";
            button1.setAttribute('name',buttonName1);
            button1.setAttribute('value','Hapus'+' 4.'+(count5));
            button1.onclick=function(){deleteDynamicRow5(table5.id,element2.id);};
            cell3.appendChild(button1);

            id1.appendChild(table5);
            id1.appendChild(document.createElement('br'));
        }

        function deleteTable5() {
            var bil = 4;
            var temp5 = $("#temp5").val();
            var count5 = $("#count5").val();

            var table5 = document.getElementById("table5"+count5);
            var rowCount7 = table5.rows.length;
            for(var i=rowCount7-1;i>=0;i--){
                table5.deleteRow(i);
                document.getElementById ("count5").value = parseInt(count5)-1;
            }

            if(parseInt(count5) <= parseInt(temp5)) {

                var url = '${pageContext.request.contextPath}/pengambilan/mmkn831a?deleteTableB&bil='
                    +bil;

                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');

            }


        }
    
    
        function addTable5a(divId){
            var id1 = document.getElementById(divId);
            var count5a = document.getElementById ("count5a").value;
            // Increment table count by 1
            document.getElementById ("count5a").value = parseInt(count5a)+1;
            count5a = document.getElementById ("count5a").value;

            // create New table
            var table5a = document.createElement("TABLE");
            table5a.id = "table5a"+count5a;
            //table.className = "tablecloth";
            table5a.width="100%";
            //table.border=2;
            var rowCount5 = table5a.rows.length;
            var row5 = table5a.insertRow(rowCount5);

            var cell0 = row5.insertCell(0);
            cell0.innerHTML = "<b>3."+(count5a)+"</b>";
            cell0.style.textAlign = "center";
    <%--cell0.style.backgroundColor = ("#328aa4");--%>
            cell0.width="2%";

            var cell1 = row5.insertCell(1);
            var element1 = document.createElement("textarea");
            element1.t = "senaraiPentadbir3"+(count5a)+(rowCount5+1);
            element1.rows = 5;
            element1.cols = 130;
            
            element1.name ="senaraiPentadbir3"+(count5a)+(rowCount5+1);
            element1.id ="senaraiPentadbir3"+(count5a)+(rowCount5+1);
    <%--element1.value ="senaraiPentadbir"+(count5)+(rowCount5+1);--%>
            cell1.colSpan = 2;
            cell1.appendChild(element1);

            // Add hidden field
            var cell2 = row5.insertCell(2);
            var element2 = document.createElement ("input");
            element2.setAttribute("type", "hidden");
            element2.setAttribute("id", "count5a"+(count5a));
            element2.setAttribute("name", "count5a"+(count5a));
            element2.setAttribute("value", 1);
            cell2.appendChild(element2);

            // Add tambah button
            var cell3 = row5.insertCell(3);
            var button = document.createElement('input');
            var buttonName = "tambah5a" +(count5a);
            button.setAttribute('type','button');
            button.className = "btn";
            button.setAttribute('name',buttonName);
            button.setAttribute('value','Tambah'+' 3.'+(count5a));
            button.onclick=function(){addDynamicRow5a(table5a.id,element2.id);};
            cell3.appendChild(button);

            var button1 = document.createElement('input');
            var buttonName1 = "hapus5a" +(count5a);
            button1.setAttribute('type','button');
            button1.className = "btn";
            button1.setAttribute('name',buttonName1);
            button1.setAttribute('value','Hapus'+' 3.'+(count5a));
            button1.onclick=function(){deleteDynamicRow5a(table5a.id,element2.id);};
            cell3.appendChild(button1);

            id1.appendChild(table5a);
            id1.appendChild(document.createElement('br'));
        }

        function deleteTable5a() {
            var bil = 3;
            var temp5 = $("#temp5a").val();
            var count5 = $("#count5a").val();

            var table5 = document.getElementById("table5a"+count5);
            var rowCount7 = table5.rows.length;
            for(var i=rowCount7-1;i>=0;i--){
                table5.deleteRow(i);
                document.getElementById ("count5a").value = parseInt(count5)-1;
            }
            if(parseInt(count5) <= parseInt(temp5)) {

                var url = '${pageContext.request.contextPath}/pengambilan/mmkn831a?deleteTableB&bil='
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
            var arr=['1','2','3','4','5','6','7','8','9','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24','25','26'];
            cell1.innerHTML = "<b>"+arr[rowCount7-1]+".</b>";

            var cell2 = row5.insertCell(2);
            var element2 = document.createElement("textarea");
            element2.t = "senaraiPentadbir"+(count5)+(rowCount7+1);
            element2.rows = 5;
            element2.cols = 130;
            element2.name ="senaraiPentadbir"+(count5)+(rowCount7+1);
            element2.id ="senaraiPentadbir"+(count5)+(rowCount7+1);
    <%--element2.value ="senaraiPentadbir"+(count5)+(rowCount7+1);--%>
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

                    var url = '${pageContext.request.contextPath}/pengambilan/mmkn831a?deleteSingleB&idKandungan='
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
    
    
        function addDynamicRow5a(tableID,countID) {
            var table5 = document.getElementById(tableID);
            var rowCount7 = table5.rows.length;
            var row5 = table5.insertRow(rowCount7);

            document.getElementById(countID).value = parseInt(document.getElementById(countID).value)+1;

            var count5 = parseInt(tableID.substring(7));

            var cell0 = row5.insertCell(0);
            cell0.innerHTML = "";

            var cell1 = row5.insertCell(1);
            var arr=['1','2','3','4','5','6','7','8','9','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24','25','26'];
            cell1.innerHTML = "<b>"+arr[rowCount7-1]+".</b>";

            var cell2 = row5.insertCell(2);
            var element2 = document.createElement("textarea");
            element2.t = "senaraiPentadbir3"+(count5)+(rowCount7+1);
            element2.rows = 5;
            element2.cols = 130;
            element2.name ="senaraiPentadbir3"+(count5)+(rowCount7+1);
            element2.id ="senaraiPentadbir3"+(count5)+(rowCount7+1);
    <%--element2.value ="senaraiPentadbir"+(count5)+(rowCount7+1);--%>
            cell2.appendChild(element2);

        }

        function deleteDynamicRow5a(tableID,countID) {
            var table5 = document.getElementById(tableID);
            var rowCount7 = table5.rows.length;
            var i = tableID.substring(7);
            if(rowCount7 >1){
                var obj = document.getElementById("kand5a"+(i)+(rowCount7));
                var idKandungan = $("#kand5a"+(i)+(rowCount7)).val();
                table5.deleteRow(rowCount7-1);
                document.getElementById(countID).value = table5.rows.length;
                if (obj != null) {

                    var url = '${pageContext.request.contextPath}/pengambilan/mmkn831a?deleteSingleB&idKandungan='
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
        //new
        function addTable6(divId){
            var id1 = document.getElementById(divId);
            var count6 = document.getElementById ("count6").value;
            // Increment table count by 1
            document.getElementById ("count6").value = parseInt(count6)+1;
            count6 = document.getElementById ("count6").value;

            // create New table
            var table6 = document.createElement("TABLE");
            table6.id = "table6"+count6;
            //table.className = "tablecloth";
            table6.width="100%";
            //table.border=2;
            var rowCount5 = table6.rows.length;
            var row5 = table6.insertRow(rowCount5);

            var cell0 = row5.insertCell(0);
            cell0.innerHTML = "<b>6."+(count6)+"</b>";
            cell0.style.textAlign = "center";
    <%--cell0.style.backgroundColor = ("#328aa4");--%>
            cell0.width="2%";

            var cell1 = row5.insertCell(1);
            var element1 = document.createElement("textarea");
            element1.t = "senaraiPentadbir6"+(count6)+(rowCount5+1);
            element1.rows = 5;
            element1.cols = 130;
            element1.name ="senaraiPentadbir6"+(count6)+(rowCount5+1);
            element1.id ="senaraiPentadbir6"+(count6)+(rowCount5+1);
    <%--element1.value ="senaraiPentadbir"+(count5)+(rowCount5+1);--%>
            cell1.colSpan = 2;
            cell1.appendChild(element1);

            // Add hidden field
            var cell2 = row5.insertCell(2);
            var element2 = document.createElement ("input");
            element2.setAttribute("type", "hidden");
            element2.setAttribute("id", "count6"+(count6));
            element2.setAttribute("name", "count6"+(count6));
            element2.setAttribute("value", 1);
            cell2.appendChild(element2);

            // Add tambah button
            var cell3 = row5.insertCell(3);
            var button = document.createElement('input');
            var buttonName = "tambah6" +(count6);
            button.setAttribute('type','button');
            button.className = "btn";
            button.setAttribute('name',buttonName);
            button.setAttribute('value','Tambah'+' 6.'+(count6));
            button.onclick=function(){addDynamicRow6(table6.id,element2.id);};
            cell3.appendChild(button);

            var button1 = document.createElement('input');
            var buttonName1 = "hapus6" +(count6);
            button1.setAttribute('type','button');
            button1.className = "btn";
            button1.setAttribute('name',buttonName1);
            button1.setAttribute('value','Hapus'+' 6.'+(count6));
            button1.onclick=function(){deleteDynamicRow6(table6.id,element2.id);};
            cell3.appendChild(button1);

            id1.appendChild(table6);
            id1.appendChild(document.createElement('br'));
        }
        function deleteTable6() {
            var bil = 6;
            var temp5 = $("#temp6").val();
            var count5 = $("#count6").val();

            var table5 = document.getElementById("table6"+count5);
            var rowCount7 = table5.rows.length;
            for(var i=rowCount7-1;i>=0;i--){
                table5.deleteRow(i);
                document.getElementById ("count6").value = parseInt(count5)-1;
            }

            if(parseInt(count5) <= parseInt(temp5)) {

                var url = '${pageContext.request.contextPath}/pengambilan/mmkn831a?deleteTableB&bil='
                    +bil;

                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');

            }
        }
        function addDynamicRow6(tableID,countID) {
            var table5 = document.getElementById(tableID);
            var rowCount7 = table5.rows.length;
            var row5 = table5.insertRow(rowCount7);

            document.getElementById(countID).value = parseInt(document.getElementById(countID).value)+1;

            var count6= parseInt(tableID.substring(6));

            var cell0 = row5.insertCell(0);
            cell0.innerHTML = "";

            var cell1 = row5.insertCell(1);
            var arr=['1','2','3','4','5','6','7','8','9','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24','25','26'];
            cell1.innerHTML = "<b>"+arr[rowCount7-1]+".</b>";

            var cell2 = row5.insertCell(2);
            var element2 = document.createElement("textarea");
            element2.t = "senaraiPentadbir6"+(count6)+(rowCount7+1);
            element2.rows = 5;
            element2.cols = 130;
            element2.name ="senaraiPentadbir6"+(count6)+(rowCount7+1);
            element2.id ="senaraiPentadbir6"+(count6)+(rowCount7+1);
    <%--element2.value ="kandungan3"+(count5)+(rowCount7+1);--%>
            cell2.appendChild(element2);

        }

        function deleteDynamicRow6(tableID,countID) {
            var table5 = document.getElementById(tableID);
            var rowCount7 = table5.rows.length;
            var i = tableID.substring(7);

            if(rowCount7 >1){
                var obj = document.getElementById("kand6"+(i)+(rowCount7));
                var idKandungan = $("#kand6"+(i)+(rowCount7)).val();
                table5.deleteRow(rowCount7-1);
                document.getElementById(countID).value = table5.rows.length;
                if (obj != null) {

                    var url = '${pageContext.request.contextPath}/pengambilan/mmkn831a?deleteSingleB&idKandungan='
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
    <%--tambah 6. --%>
        function addTable7(divId){
            var id1 = document.getElementById(divId);
            var count7 = document.getElementById ("count7").value;
            // Increment table count by 1
            document.getElementById ("count7").value = parseInt(count7)+1;
            count7 = document.getElementById ("count7").value;

            // create New table
            var table4 = document.createElement("TABLE");
            table4.id = "table4"+count4;
            //table.className = "tablecloth";
            table4.width="100%";
            //table.border=2;
            var rowCount4 = table4.rows.length;
            var row4 = table4.insertRow(rowCount4);

            var cell0 = row4.insertCell(0);
            cell0.innerHTML = "<b>3."+(count4)+"</b>";
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
            button.setAttribute('value','Tambah 3.'+(count4));
            button.onclick=function(){addDynamicRow4(table4.id,element2.id);};
            cell3.appendChild(button);

            var button1 = document.createElement('input');
            var buttonName1 = "hapus4" +(count4);
            button1.setAttribute('type','button');
            button1.className = "btn";
            button1.setAttribute('name',buttonName1);
            button1.setAttribute('value','Hapus 3.'+(count4));
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

                var url = '${pageContext.request.contextPath}/pengambilan/mmkn831a?deleteTable&bil='
                    +bil+'&formPtg='+formPtg+'&form1='+form1;

                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');

            }


        }

        function addDynamicRow7(tableID,countID) {

            var table4 = document.getElementById(tableID);
            var rowCount4 = table4.rows.length;
            var row4 = table4.insertRow(rowCount4);

            document.getElementById(countID).value = parseInt(document.getElementById(countID).value)+1;

            var count4 = parseInt(tableID.substring(6));

            var cell0 = row4.insertCell(0);
            cell0.innerHTML = "";

            var cell1 = row4.insertCell(1);
            var arr=['1','2','3','4','5','6','7','8','9','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24','25','26'];
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

        function deleteDynamicRow7(tableID,countID,formPtg,form1) {
            var table4 = document.getElementById(tableID);
            var rowCount4 = table4.rows.length;
            var i = tableID.substring(6);

            if(rowCount4 >1){
                var obj = document.getElementById("kand4"+(i)+(rowCount4));
                var idKandungan = $("#kand4"+(i)+(rowCount4)).val();
                table4.deleteRow(rowCount4-1);
                document.getElementById(countID).value = table4.rows.length;
                if (obj != null) {

                    var url = '${pageContext.request.contextPath}/pengambilan/mmkn831a?deleteSingle&idKandungan='
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
</script>

<s:form beanclass="etanah.view.stripes.pengambilan.MMKN831AActionBean" name="form2">
    <s:messages/>
    <s:errors/>
    <s:hidden name="count5" id="count5" value="${actionBean.count5}"/>
    <s:hidden name="temp5" id="temp5" value="${actionBean.count5}"/>
    <s:hidden name="count5a" id="count5a" value="${actionBean.count5a}"/>
    <s:hidden name="temp5a" id="temp5a" value="${actionBean.count5a}"/>
    <s:hidden name="count6" id="count6" value="${actionBean.count6}"/>
    <s:hidden name="temp6" id="temp6" value="${actionBean.count6}"/>
    <c:if test="${form1}">
        <s:hidden name="form1" value="${form1}"/>
        <s:hidden name="formPtg" value="false"/>
        <div class="subtitle">
            <fieldset class="aras1" id="locate">
                <div class="content" align="center">
                    <table border="0" width="80%">
                        <div class="content" align="left">
                            <tr align="left"><td align="left">&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
                                    <u><b>MESYUARAT JAWATANKUASA KHAS PENGAMBILAN TANAH NEGERI SEMBILAN</b></u><br /></td></tr>
                                    <%--<tr><td align="left"><b><label><font color="black">BIL MESYUARAT :</font></label><s:text name="mesyuaratBil"  size="10" style="text-align:left" id="mesyuaratBil" class="normal_text"/></td></tr>--%>
                            <tr><td> &nbsp;</td></tr>
                            <tr><td align="left"><b><label><font color="black">KERTAS MESYUARAT :</font></label><s:text name="kertasBil" disabled="false" size="10" style="text-align:left" id="kertasBil" class="normal_text"/>/${actionBean.kertasTahun}<s:hidden name="kertasTahun" value="${actionBean.kertasTahun}"/></b></td></tr>
                            <%--<tr>
                                <td><label><font color="black">MASA :</font></label>
                                    <s:select name="jam" style="width:60px" disabled="true">
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
                                     <s:select name="minit" style="width:62px" disabled="true">
                                        <s:option value="">Minit</s:option>
                                        <s:option value="00">00</s:option>
                                        <s:option value="05">05</s:option>
                                        <s:option value="10">10</s:option>
                                        <s:option value="15">15</s:option>
                                        <s:option value="20">20</s:option>
                                        <s:option value="25">25</s:option>
                                        <s:option value="30">30</s:option>
                                        <s:option value="35">35</s:option>
                                        <s:option value="40">40</s:option>
                                        <s:option value="45">45</s:option>
                                        <s:option value="50">50</s:option>
                                        <s:option value="55">55</s:option>
                                    </s:select>
                                    <s:select name="pagiPetang" style="width:80px" disabled="true">
                                        <s:option >Pilih</s:option>
                                        <s:option value="PAGI">Pagi</s:option>
                                        <s:option value="PETANG">Petang</s:option>
                                    </s:select>
                                </td>
                            </tr>
                            --%> <tr><td align="left"><b><label><font color="black">TARIKH :</font></label><s:text name="tarikhmesyuarat"  id="tarikhMesyuarat" class="datepicker" size="15" style="text-align:left" /></b></td></tr>
                            <%--<tr><td align="left"><b><label><font color="black">TEMPAT      :</font></label><s:textarea name="tempat" disabled="true" cols="50" rows="5" style="text-align:left" id="tempat" class="normal_text"/></b></td></tr>--%>
                        </div>
                        <tr><td></td></tr><tr><td></td></tr><tr><td></td></tr><tr><td></td></tr>

                        <%--<tr><td> &nbsp;</td></tr>
                        <tr><td><font style="text-transform: uppercase"><b>PERMOHONAN PENARIKAN BALIK DARIPADA PENGAMBILAN SEBAHAGIAN TANAH DI LOT ${actionBean.hakmilik.noLot}, ${actionBean.hakmilik.kodHakmilik.kod} ${actionBean.hakmilik.luas}, ${actionBean.hakmilik.bandarPekanMukim.nama}, DAERAH ${actionBean.hakmilik.daerah.nama}, NEGERI SEMBILAN BAGI ${actionBean.permohonan.sebab}.</b></font><br /><br /><hr /><br /></td></tr>--%>

                        <tr><td><b><s:hidden name="heading" class="normal_text"/>
                                    ${actionBean.heading}</b></td></tr><br/>

                        <tr><td><b>1. TUJUAN</b></td></tr>
                        <tr><tr><tr><td colspan="2"><b> &nbsp;</b><s:textarea rows="5" class="normal_text" cols="140" name="tujuan"/><td>

                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b>2. LATAR BELAKANG PEMOHON</b></td></tr>
                        <tr><td> &nbsp;</td></tr>


                        <tr><td>
                                <table id ="dataTable2">
                                    <c:set var="j" value="1" />
                                    <c:forEach items="${actionBean.senaraiKertasKandungan2}" var="line">
                                        <tr style="font-weight:bold">
                                            <td style="display:none">${line.idKandungan}</td>
                                            <td>&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${line.subtajuk}"/></td>
                                            <td><font class="normal_text"><s:textarea name="kandungan2${j}" class="normal_text" id="kandungan2${j}" rows="5" cols="150">${line.kandungan}</s:textarea></font></td>
                                            </tr>
                                        <s:hidden name="idKandungan2${j}" id="idKandungan2${j}" value="${line.idKandungan}" />
                                        <c:set var="j" value="${j+1}" />
                                    </c:forEach>
                                    <s:hidden name="rowCount2" value="${j-1}" id="rowCount2"/>
                                </table>
                        <tr><td align="right"><s:button name="perihal2" value="Tambah" class="btn" onclick="addRow2('dataTable2')" />
                                <s:button name="perihal2" value="Hapus" class="btn" onclick="deleteRow2()" />
                            </td>
                        </tr>
                        <tr><td> &nbsp;</td></tr>

                        <tr><td><b>3. ULASAN JABATAN-JABATAN TEKNIKAL </b></td></tr>
                        <tr><td>
                                <table id ="dataTable3">
                                    <c:set var="k" value="1" />
                                    <c:forEach items="${actionBean.senaraiKertasKandungan3}" var="line">
                                        <tr style="font-weight:bold">
                                            <td style="display:none">${line.idKandungan}</td>
                                            <td>&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${line.subtajuk}"/></td>
                                            <td><font class="normal_text"><s:textarea name="kandungan3${k}" class="normal_text" id="kandungan3${k}" rows="5" cols="150">${line.kandungan}</s:textarea></font></td>
                                            </tr>
                                        <s:hidden name="idKandungan3${k}" id="idKandungan3${k}" value="${line.idKandungan}" />
                                        <c:set var="k" value="${k+1}" />
                                    </c:forEach>
                                    <s:hidden name="rowCount3" value="${k-1}" id="rowCount3"/>
                                </table>
                        <tr><td align="right"><s:button name="perihal3" value="Tambah" class="btn" onclick="addRow3('dataTable3')" />
                                <s:button name="perihal3" value="Hapus" class="btn" onclick="deleteRow3()" />
                            </td>
                        </tr>
                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b><font>4. HURAIAN PENGARAH TANAH DAN GALIAN,NEGERI SEMBILAN</font></b></td></tr>

                        <c:forEach var="i" begin="1" end="${actionBean.count5}">
                            <tr><td>
                                    <table  width="90%" id="table5${i}" >
                                        <c:set var="recordCount5" value="0"/>
                                        <c:forEach items="${actionBean.senaraiPentadbir[i]}" var="senarai">
                                            <c:set var="recordCount5" value="${recordCount5+1}"/>
                                            <c:if test="${recordCount5 eq 1}">
                                                <tr> <td><b>4.${i}</b></td>
                                                    <td colspan="2"><s:textarea rows="5" cols="130" name="senaraiPentadbir${i}${recordCount5}" class="normal_text" id="senaraiPentadbir${i}${recordCount5}" value="${senarai.kandungan}" /></td>
                                                    <td><s:hidden name="count5${i}" id="count5${i}" value="${(fn:length(actionBean.senaraiPentadbir[i]))}" /> </td>
                                                    <td><s:button class="btn" value="Tambah 4.${i}" name="add" id="tambah5${i}" onclick="addDynamicRow5('table5${i}','count5${i}')"/></td>
                                                    <td><s:button class="btn" value="Hapus 4.${i}" name="hapus" id="hapus5${i}" onclick="deleteDynamicRow5('table5${i}','count5${i}')"/></td>
                                                </c:if>
                                                <c:if test="${recordCount5 ne 1}">
                                                <tr>  <td></td>
                                                    <td><b>bb><c:out value="${actionBean.strB[recordCount5-1]}"/>.</b></td>
                                                    <td><s:textarea rows="5" cols="130" name="senaraiPentadbir${i}${recordCount5}" class="normal_text" id="senaraiPentadbir${i}${recordCount5}" value="${senarai.kandungan}" /></td>
                                                </c:if>
                                                <s:text name="kand5${i}${recordCount5}" id="kand5${i}${recordCount5}" value="${senarai.idKandungan}" />
                                            </c:forEach>
                                    </table>
                                </td></tr>

                        </c:forEach>
                        <tr><td align="right">
                                <div id="Tables5">

                                </div>
                                <s:button class="btn" value="Tambah" name="add" onclick="addTable5('Tables5')"/>
                                <s:button class="btn" value="Hapus" name="add" onclick="deleteTable5()"/>
                            </td></tr>
                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b><font>5. SYOR PENGARAH TANAH DAN GALIAN, NEGERI SEMBILAN</font></b></td></tr>

                        <tr><td>
                                <table id ="dataTable5">
                                    <c:set var="y" value="1" />
                                    <c:forEach items="${actionBean.senaraiKertasKandungan5}" var="line">
                                        <tr style="font-weight:bold">
                                            <td style="display:none">${line.idKandungan}</td>
                                            <td>&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${line.subtajuk}"/></td>
                                            <td><font><s:textarea name="kandungan5${y}" disabled="true" id="kandungan5${y}" rows="5" cols="150">${line.kandungan}</s:textarea></font></td>
                                            </tr>
                                        <s:hidden name="idKandungan5${y}" id="idKandungan5${y}" value="${line.idKandungan}" />
                                        <c:set var="y" value="${y+1}" />
                                    </c:forEach>
                                    <s:hidden name="rowCount9" value="${y-1}" id="rowCount9"/>
                                </table>
                        <tr><td align="right"><s:button name="perihal5" disabled="true" value="Tambah" class="btn" onclick="addRow5('dataTable5')" />
                                <s:button name="perihal5" disabled="true" value="Hapus" class="btn" onclick="deleteRow5()" />
                            </td>
                        </tr>
                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b><font>6. PERAKUAN JAWATANKUASA KHAS PENGAMBILAN TANAH</font></b></td></tr>

                        <tr><td>
                                <table id ="dataTable6">
                                    <c:set var="z" value="1" />
                                    <c:forEach items="${actionBean.senaraiKertasKandungan6}" var="line">
                                        <tr style="font-weight:bold">
                                            <td style="display:none">${line.idKandungan}</td>
                                            <td>&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${line.subtajuk}"/></td>
                                            <td><font><s:textarea name="kandungan6${z}" disabled="true" id="kandungan6${z}" rows="5" cols="150">${line.kandungan}</s:textarea></font></td>
                                            </tr>
                                        <s:hidden name="idKandungan6${z}" id="idKandungan6${z}" value="${line.idKandungan}" />
                                        <c:set var="z" value="${z+1}" />
                                    </c:forEach>
                                    <s:hidden name="rowCount6" value="${z-1}" id="rowCount6"/>
                                </table>
                        <tr><td align="right"><s:button name="perihal6" disabled="true" value="Tambah" class="btn" onclick="addRow6('dataTable6')" />
                                <s:button name="perihal6" disabled="true" value="Hapus" class="btn" onclick="deleteRow6()" />
                            </td>
                        </tr>
                        <tr><td align="right">
                                <div id="Tables4">

                                </div>
                                <%-- <s:button class="btn" value="Tambah" name="add" onclick="addTable4('Tables4')"/>
                                 <s:button class="btn" value="Hapus" name="add" onclick="deleteTable4('${formPtg}','${form1}')" style="font-size:12px"/>--%>
                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b>7. KEPUTUSAN</b></td></tr>
                        <tr>
                            <td width="100%"><b>7.1</b> Dikemukakan untuk mendapat pertimbangan dan keputusan Majlis Mesyuarat Kerajaan Negeri Sembilan Darul Khusus.

                            </td>
                        </tr>

                    </table>
                </div>
            </fieldset>
        </div>
        <p align="center">
            <s:hidden name="count4" id="count4" value="${actionBean.count4}"/>
            <s:hidden name="temp4" id="temp4" value="${actionBean.count4}"/>
            <s:hidden name="idKertas" value="${permohonanKertas.idKertas}"/>
            <s:button name="simpanb" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>
        </p>
    </c:if>
    <c:if test="${ptgform1}">
        <s:hidden name="ptgform1" value="${ptgform1}"/>
        <s:hidden name="formPtg" value="false"/>
        <div class="subtitle">
            <fieldset class="aras1" id="locate">
                <div class="content" align="center">
                    <table border="0" width="80%">
                        <div class="content" align="left">
                            <tr align="left"><td align="left">&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
                                    <u><b>KERTAS MAJLIS MESYUARAT KERAJAAN NEGERI SEMBILAN</b></u><br /></td></tr>
                                    <%--<tr><td align="left"><b><label><font color="black">BIL MESYUARAT :</font></label><s:text name="mesyuaratBil"  size="10" style="text-align:left" id="mesyuaratBil" class="normal_text"/></td></tr>--%>
                                    <c:if test=''></c:if>
                            <tr><td> &nbsp;</td></tr>
                            <tr><td align="left"><b><label><font color="black">KERTAS BIL :</font></label><s:text name="kertasBil"  size="10" style="text-align:left" id="kertasBil" class="normal_text"/>/${actionBean.kertasTahun}<s:hidden name="kertasTahun" value="${actionBean.kertasTahun}"/></b></td></tr>
                            <%-- <tr>
                                 <td><label><font color="black">MASA :</font></label>
                                     <s:select name="jam" style="width:60px">
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
                                     <s:select name="minit" style="width:62px">
                                         <s:option value="">Minit</s:option>
                                         <s:option value="00">00</s:option>
                                         <s:option value="05">05</s:option>
                                         <s:option value="10">10</s:option>
                                         <s:option value="15">15</s:option>
                                         <s:option value="20">20</s:option>
                                         <s:option value="25">25</s:option>
                                         <s:option value="30">30</s:option>
                                         <s:option value="35">35</s:option>
                                         <s:option value="40">40</s:option>
                                         <s:option value="45">45</s:option>
                                         <s:option value="50">50</s:option>
                                         <s:option value="55">55</s:option>

                                </s:select>
                                <s:select name="pagiPetang" style="width:80px">
                                    <s:option >Pilih</s:option>
                                    <s:option value="PAGI">Pagi</s:option>
                                    <s:option value="PETANG">Petang</s:option>
                                </s:select>
                            </td>
                        </tr>
                            --%>  <tr><td align="left"><b><label><font color="black">TARIKH :</font></label><s:text name="tarikhmesyuarat" id="tarikhMesyuarat" class="datepicker" size="15" style="text-align:left" /></b></td></tr>
                            <%-- <tr><td align="left"><b><label><font color="black">TEMPAT      :</font></label><s:textarea name="tempat" cols="50" rows="5" style="text-align:left" id="tempat" class="normal_text"/></b></td></tr>--%>
                        </div>
                        <tr><td></td></tr><tr><td></td></tr><tr><td></td></tr><tr><td></td></tr>



                        <tr><td><b><s:hidden name="heading" class="normal_text"/>
                                    ${actionBean.heading}</b></td></tr><br/>

                        <tr><td><b>1. TUJUAN</b></td></tr>
                        <tr><tr><tr><td colspan="2"><b> &nbsp;</b><s:textarea rows="5" cols="140" name="tujuan" class="normal_text" /><td>

                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b>2. LATAR BELAKANG</b></td></tr>
                        <tr><td> &nbsp;</td></tr>


                        <tr><td>
                                <table id ="dataTable2">
                                    <c:set var="i" value="1" />
                                    <c:forEach items="${actionBean.senaraiKertasKandungan2}" var="line">
                                        <tr style="font-weight:bold">
                                            <td style="display:none">${line.idKandungan}</td>
                                            <td>&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${line.subtajuk}"/></td>
                                            <td><font><s:textarea name="kandungan2${i}" class="normal_text" id="kandungan2${i}"  rows="5" cols="150">${line.kandungan}</s:textarea></font></td>
                                            </tr>
                                        <c:set var="i" value="${i+1}" />
                                    </c:forEach>
                                    <s:hidden name="rowCount2" value="${i-1}" id="rowCount2"/>
                                </table>
                        <tr><td align="right"><s:button name="perihal2" disabled="false" value="Tambah" class="btn" onclick="addRow2('dataTable2')" />
                                <s:button name="perihal2" disabled="false" value="Hapus" class="btn" onclick="deleteRow2()" />
                            </td>
                        </tr>
                        <tr><td> &nbsp;</td></tr>

                        <tr><td><b>3. UNIT PERANCANG EKONOMI NEGERI, NEGERI SEMBILAN</b></td></tr>
                        <c:forEach var="i" begin="1" end="${actionBean.count5a}">
                            <tr><td>
                                    <table  width="90%" id="table5a${i}" >
                                        <c:set var="recordCount5a" value="0"/>
                                        <c:forEach items="${actionBean.senaraiPentadbir3[i]}" var="senaraia">
                                            <c:set var="recordCount5a" value="${recordCount5a+1}"/>
                                            <c:if test="${recordCount5a eq 1}">
                                                <tr> <td><b>3.${i}</b></td>
                                                    <td colspan="2"><s:textarea rows="5" class="normal_text" cols="130" name="senaraiPentadbir3${i}${recordCount5a}" id="senaraiPentadbir3${i}${recordCount5a}" value="${senaraia.kandungan}" /></td>
                                                    <td><s:hidden name="count5a${i}" id="count5a${i}" value="${(fn:length(actionBean.senaraiPentadbir3[i]))}" /> </td>
                                                    <td><s:button class="btn" disabled="false" value="Tambah 3.${i}" name="add" id="tambah5a${i}" onclick="addDynamicRow5a('table5a${i}','count5a${i}')"/></td>
                                                    <td> <s:button class="btn" disabled="false" value="Hapus 3.${i}" name="hapus" id="hapus5a${i}" onclick="deleteDynamicRow5a('table5a${i}','count5a${i}')"/></td>
                                                </c:if>
                                                <c:if test="${recordCount5a ne 1}">
                                                <tr>  <td></td>
                                                    <td><b><c:out value="${actionBean.strB[recordCount5a-1]}"/>.</b></td>
                                                    <td><s:textarea rows="5" cols="130"   class="normal_text" name="senaraiPentadbir3${i}${recordCount5a}" id="senaraiPentadbir3${i}${recordCount5a}" value="${senaraia.kandungan}" /></td>
                                                </c:if>
                                                <s:hidden name="kand5a${i}${recordCount5a}" id="kand5a${i}${recordCount5a}" value="${senaraia.idKandungan}" />
                                            </c:forEach>
                                    </table>
                                </td></tr>

                        </c:forEach>
                        <tr><td align="right">
                                <div id="Tables5a">

                                </div>
                                <s:button class="btn" disabled="false" value="Tambah" name="add" onclick="addTable5a('Tables5a')"/>
                                <s:button class="btn" disabled="false" value="Hapus" name="add" onclick="deleteTable5a()"/>
                            </td></tr>
                        <tr><td> &nbsp;</td></tr>

                        <tr><td><b><%--<font style="text-transform:uppercase">--%>4. JABATAN PENILAIAN DAN PERKHIDMATAN HARTA, NEGERI SEMBILAN <%--</font>--%></b></td></tr>

                        <c:forEach var="i" begin="1" end="${actionBean.count5}">
                            <tr><td>
                                    <table  width="90%" id="table5${i}" >
                                        <c:set var="recordCount5" value="0"/>
                                        <c:forEach items="${actionBean.senaraiPentadbir[i]}" var="senarai">
                                            <c:set var="recordCount5" value="${recordCount5+1}"/>
                                            <c:if test="${recordCount5 eq 1}">
                                                <tr> <td><b>4.${i}</b></td>
                                                    <td colspan="2"><s:textarea rows="5" class="normal_text" cols="130" name="senaraiPentadbir${i}${recordCount5}" id="senaraiPentadbir${i}${recordCount5}" value="${senarai.kandungan}" /></td>
                                                    <td><s:hidden name="count5${i}" id="count5${i}" value="${(fn:length(actionBean.senaraiPentadbir[i]))}" /> </td>
                                                    <td><s:button class="btn" disabled="false" value="Tambah 4.${i}" name="add" id="tambah5${i}" onclick="addDynamicRow5('table5${i}','count5${i}')"/></td>
                                                    <td> <s:button class="btn" disabled="false" value="Hapus 4.${i}" name="hapus" id="hapus5${i}" onclick="deleteDynamicRow5('table5${i}','count5${i}')"/></td>
                                                </c:if>
                                                <c:if test="${recordCount5 ne 1}">
                                                <tr>  <td></td>
                                                    <td><b><c:out value="${actionBean.strB[recordCount5-1]}"/>.</b></td>
                                                    <td><s:textarea rows="5" cols="130"   class="normal_text" name="senaraiPentadbir${i}${recordCount5}" id="senaraiPentadbir${i}${recordCount5}" value="${senarai.kandungan}" /></td>
                                                </c:if>
                                                <s:hidden name="kand5${i}${recordCount5}" id="kand5${i}${recordCount5}" value="${senarai.idKandungan}" />
                                            </c:forEach>
                                    </table>
                                </td></tr>

                        </c:forEach>
                        <tr><td align="right">
                                <div id="Tables5">

                                </div>
                                <s:button class="btn" disabled="false" value="Tambah" name="add" onclick="addTable5('Tables5')"/>
                                <s:button class="btn" disabled="false" value="Hapus" name="add" onclick="deleteTable5()"/>
                            </td></tr>
                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b><%--<font style="text-transform:uppercase">--%>5. ULASAN PENGARAH TANAH DAN GALIAN, NEGERI SEMBILAN<%--</font>--%></b></td></tr>

                        <tr><td>
                                <table id ="dataTable5">
                                    <c:set var="y" value="1" />
                                    <c:forEach items="${actionBean.senaraiKertasKandungan5}" var="line">
                                        <tr style="font-weight:bold">
                                            <td style="display:none">${line.idKandungan}</td>
                                            <td>&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${line.subtajuk}"/></td>
                                            <%--onkeyup="this.value=this.value.toUpperCase();"--%>
                                            <td><font><s:textarea class="normal_text" name="kandungan5${y}" id="kandungan5${y}" rows="5" cols="150">${line.kandungan}</s:textarea></font></td>
                                            </tr>
                                        <s:hidden name="idKandungan5${y}" id="idKandungan5${y}" value="${line.idKandungan}" />
                                        <c:set var="y" value="${y+1}" />
                                    </c:forEach>
                                    <s:hidden name="rowCount9" value="${y-1}" id="rowCount9"/>
                                </table>
                        <tr><td align="right"><s:button name="perihal5" value="Tambah" class="btn" onclick="addRow5('dataTable5')" />
                                <s:button name="perihal5" value="Hapus" class="btn" onclick="deleteRow5()" />
                            </td>
                        </tr>
                        <tr><td> &nbsp;</td></tr>
                        <%--      <tr><td><b><%--<font style="text-transform:uppercase">6. PERAKUAN JAWATANKUASA KHAS PENGAMBILAN TANAH<%--</font></b></td></tr>

                        <tr><td>
                                <table id ="dataTable6">
                                    <c:set var="z" value="1" />
                                    <c:forEach items="${actionBean.senaraiKertasKandungan6}" var="line">
                                        <tr style="font-weight:bold">
                                            <td style="display:none">${line.idKandungan}</td>
                                            <td>&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${line.subtajuk}"/></td>
                        <%--onkeyup="this.value=this.value.toUpperCase();"
                        <td><font><s:textarea class="normal_text" name="kandungan6${z}" id="kandungan6${z}" rows="5" cols="150">${line.kandungan}</s:textarea></font></td>
                        </tr>
                    <s:hidden name="idKandungan6${z}" id="idKandungan6${z}" value="${line.idKandungan}" />
                    <c:set var="z" value="${z+1}" />
                </c:forEach>
                <s:hidden name="rowCount6" value="${z-1}" id="rowCount6"/>
            </table>
    <tr><td align="right"><s:button name="perihal6" value="Tambah" class="btn" onclick="addRow6('dataTable6')" />
            <s:button name="perihal6" value="Hapus" class="btn" onclick="deleteRow6()" />
        </td>
    </tr>
    <tr><td align="right">
            <div id="Tables4">

                                </div>
                        <tr><td> &nbsp;</td></tr>--%>

                        <tr><td> &nbsp;</td></tr>

                        <tr><td><b>6. PERAKUAN JAWATANKUASA KHAS PENGAMBILAN TANAH</b></td></tr>
                        <c:forEach var="i" begin="1" end="${actionBean.count6}">
                            <tr><td>
                                    <table  width="90%" id="table6${i}" >
                                        <c:set var="recordCount6" value="0"/>
                                        <c:forEach items="${actionBean.senaraiPentadbir6[i]}" var="senarai6">
                                            <c:set var="recordCount6" value="${recordCount6+1}"/>
                                            <c:if test="${recordCount6 eq 1}">
                                                <tr> <td><b>6.${i}</b></td>
                                                    <td colspan="2"><s:textarea rows="5" class="normal_text" cols="130" name="senaraiPentadbir6${i}${recordCount6}" id="senaraiPentadbir6${i}${recordCount6}" value="${senarai6.kandungan}" /></td>
                                                    <td><s:hidden name="count6${i}" id="count6${i}" value="${(fn:length(actionBean.senaraiPentadbir6[i]))}" /> </td>
                                                    <td><s:button class="btn" disabled="false" value="Tambah 6.${i}" name="add" id="tambah6${i}" onclick="addDynamicRow6('table6${i}','count6${i}')"/></td>
                                                    <td> <s:button class="btn" disabled="false" value="Hapus 6.${i}" name="hapus" id="hapus6${i}" onclick="deleteDynamicRow6('table6${i}','count6${i}')"/></td>
                                                </c:if>
                                                <c:if test="${recordCount6 ne 1}">
                                                <tr>  <td></td>
                                                    <td><b><c:out value="${actionBean.strB[recordCount6-1]}"/>.</b></td>
                                                    <td><s:textarea rows="5" cols="130"   class="normal_text" name="senaraiPentadbir6${i}${recordCount6}" id="senaraiPentadbir6${i}${recordCount6}" value="${senarai6.kandungan}" /></td>
                                                </c:if>
                                                <s:hidden name="kand6${i}${recordCount6}" id="kand6${i}${recordCount6}" value="${senarai6.idKandungan}" />
                                            </c:forEach>
                                    </table>
                                </td></tr>

                        </c:forEach>
                        <tr><td align="right">
                                <div id="Tables6">

                                </div>
                                <s:button class="btn" disabled="false" value="Tambah" name="add" onclick="addTable6('Tables6')"/>
                                <s:button class="btn" disabled="false" value="Hapus" name="add" onclick="deleteTable6()"/>
                            </td></tr>
                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b>7. KEPUTUSAN</b></td></tr>
                        <tr>
                            <td width="100%"><b>7.1</b> Dikemukakan untuk mendapat pertimbangan dan keputusan Majlis Mesyuarat Kerajaan Negeri Sembilan Darul Khusus.

                            </td>
                        </tr>

                    </table>
                </div>
            </fieldset>
        </div>
        <p align="center">
            <s:hidden name="count4" id="count4" value="${actionBean.count4}"/>
            <s:hidden name="temp4" id="temp4" value="${actionBean.count4}"/>
            <s:hidden name="idKertas" value="${permohonanKertas.idKertas}"/>
          <%--  <s:button name="simpanPTGb" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>--%>
            <s:button name="simpanPTGb" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
        </p>
    </c:if>
</s:form>