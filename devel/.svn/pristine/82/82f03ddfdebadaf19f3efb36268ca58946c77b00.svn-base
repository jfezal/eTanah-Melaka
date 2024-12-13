<%--
    Document   : mmk_ns_izinlalu
    Created on : Jul 8, 2011, 3:05:18 PM
    Author     : Rajesh
    Modified By: Murali Feb14, 2012
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
        document.form2.rowCount3.value = 1;
        var table = document.getElementById(tableID);

        var rowCount3 = table.rows.length;
        var row = table.insertRow(rowCount3);

        var cell2 = row.insertCell(0);
        cell2.innerHTML = "<b>"+"2." +(rowCount3+1)+"</b>";

        var cell1 = row.insertCell(1);
        var element1 = document.createElement("textarea");
        element1.t = "kandungan3"+(rowCount3+1);
        element1.rows = 5;
        element1.cols = 150;
        element1.name ="kandungan3"+(rowCount3+1);
        element1.id ="kandungan3"+(rowCount3+1);
        cell1.appendChild(element1);
        document.form2.rowCount3.value=rowCount3 +1;

        <%--alert(rowCount3+1);--%>
        var cell2 = row.insertCell(2);
        var e1 = document.createElement("INPUT");
        e1.t = "button"+(rowCount3+1);
        e1.setAttribute("type","button");
        e1.className="btn";
        e1.value="Hapus";
        e1.id =(rowCount3+1);
        e1.onclick=function(){deleteRow3('','','',(e1.id));};
        cell2.appendChild(e1);
    }

    function deleteRow3(formPtg,form1,idKandungan,index)
    {
    <%--alert("deleting LaterBelaking");
    alert("index:"+index);
    alert("idKandungan:"+idKandungan);--%>
            document.getElementById('dataTable3').deleteRow(index-1);
            if(idKandungan == ''){
                var rowCount = document.getElementById("rowCount3").value;
                document.getElementById("rowCount3").value = rowCount-1;
                regenerateBill3('dataTable3');
            }else{
                var url = '${pageContext.request.contextPath}/pengambilan/mmkizinlalu?deleteSingle&idKandungan='
                    +idKandungan+'&formPtg='+formPtg+'&form1='+form1;

                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
            }
        }

        function regenerateBill3(tableid){
            try{
                var table = document.getElementById(tableid);
                var rowCount = table.rows.length;
                if(rowCount > 1){
                    for(var i=0;i<rowCount;i++){
                        var a = table.rows[i].cells[0];
                        a.innerHTML= "<b>3."+(i+1)+"</b>";
                        if(i>0){
                            table.rows[i].cells[1].childNodes[0].name= 'kandungan3'+(i+1);
                            table.rows[i].cells[2].childNodes[0].id= i+1;
                        }
                    }
                }
            }catch(e){
                alert("Error in regenerateBill");
            }
        }

        function addRow4(tableID) {
            document.form2.rowCount4.value = 1;
            var table = document.getElementById(tableID);

            var rowCount4 = table.rows.length;
            var row = table.insertRow(rowCount4);

            var cell2 = row.insertCell(0);
            cell2.innerHTML = "<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+"4.1." +(rowCount4+1)+"</b>";

            var cell1 = row.insertCell(1);
            var element1 = document.createElement("textarea");
            element1.t = "kandungan4"+(rowCount4+1);
            element1.rows = 5;
            element1.cols = 135;
            element1.name ="kandungan4"+(rowCount4+1);
            element1.id ="kandungan4"+(rowCount4+1);
            cell1.appendChild(element1);
            document.form2.rowCount4.value=rowCount4 +1;

            var cell3 = row.insertCell(2);
            var e1 = document.createElement("INPUT");
            e1.t = "button"+(rowCount4+1);
            e1.setAttribute("type","button");
            e1.className="btn";
            e1.value="Hapus";
            e1.id =(rowCount4+1);
            e1.onclick=function(){deleteRow4('','','',(e1.id));};
            cell3.appendChild(e1);
        }

        function deleteRow4(formPtg,form1,idKandungan,index)
        {
           <%-- alert("deleting LaterBelaking");
            alert("index:"+index);
            alert("idKandungan:"+idKandungan);--%>
            document.getElementById('dataTable4').deleteRow(index-1);
            if(idKandungan == ''){
                var rowCount = document.getElementById("rowCount4").value;
                document.getElementById("rowCount4").value = rowCount-1;
                regenerateBill4('dataTable4');
            }else{
                var url = '${pageContext.request.contextPath}/pengambilan/mmkizinlalu?deleteSingle&idKandungan='
                    +idKandungan+'&formPtg='+formPtg+'&form1='+form1;;

                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
            }
        }

        function regenerateBill4(tableid){
            try{
                var table = document.getElementById(tableid);
                var rowCount = table.rows.length;
                if(rowCount > 1){
                    for(var i=0;i<rowCount;i++){
                        var a = table.rows[i].cells[0];
                        a.innerHTML= "<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.1."+(i+1)+"</b>";
                        if(i>0){
                            table.rows[i].cells[1].childNodes[0].name= 'kandungan4'+(i+1);
                            table.rows[i].cells[2].childNodes[0].id= i+1;
                        }
                    }
                }
            }catch(e){
                alert("Error in regenerateBill");
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
            element1.t = "rayuanTuan"+(count5)+(rowCount5+1);
            element1.rows = 5;
            element1.cols = 130;
            element1.name ="rayuanTuan"+(count5)+(rowCount5+1);
            element1.id ="rayuanTuan"+(count5)+(rowCount5+1);
    <%--element1.value ="rayuanTuan"+(count5)+(rowCount5+1);--%>
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

        function deleteTable5(formPtg,form1) {
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

                var url = '${pageContext.request.contextPath}/pengambilan/mmkizinlalu?deleteTable&bil='
                    +bil+'&formPtg='+formPtg+'&form1='+form1;

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
            cell1.innerHTML = "<b>5."+(count5)+"."+(rowCount7)+".</b>";

            var cell2 = row5.insertCell(2);
            var element2 = document.createElement("textarea");
            element2.t = "rayuanTuan"+(count5)+(rowCount7+1);
            element2.rows = 5;
            element2.cols = 130;
            element2.name ="rayuanTuan"+(count5)+(rowCount7+1);
            element2.id ="rayuanTuan"+(count5)+(rowCount7+1);
    <%--element2.value ="rayuanTuan"+(count5)+(rowCount7+1);--%>
            cell2.appendChild(element2);

        }

        function deleteDynamicRow5(tableID,countID,formPtg,form1) {
            var table5 = document.getElementById(tableID);
            var rowCount7 = table5.rows.length;
            var i = tableID.substring(6);

            if(rowCount7 >1){
                var obj = document.getElementById("kand5"+(i)+(rowCount7));
                var idKandungan = $("#kand5"+(i)+(rowCount7)).val();
                table5.deleteRow(rowCount7-1);
                document.getElementById(countID).value = table5.rows.length;
                if (obj != null) {

                    var url = '${pageContext.request.contextPath}/pengambilan/mmkizinlalu?deleteSingle&idKandungan='
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

        function addRow6(tableID4) {
            document.form2.rowCount6.value = 1;
            var table = document.getElementById(tableID4);

            var rowCount6 = table.rows.length;
            var row = table.insertRow(rowCount6);

            var cell2 = row.insertCell(0);
            cell2.innerHTML = "<b>"+"3." +(rowCount6+1)+"</b>";

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

            var cell3 = row.insertCell(2);
            var e1 = document.createElement("INPUT");
            e1.t = "button"+(rowCount6+1);
            e1.setAttribute("type","button");
            e1.className="btn";
            e1.value="Hapus";
            e1.id =(rowCount6+1);
            e1.onclick=function(){deleteRow6((e1.id));};
            cell3.appendChild(e1);
        }
       function deleteRow6(formPtg,form1,idKandungan,index)
    {
            document.getElementById('dataTable6').deleteRow(index-1);
            if(idKandungan == ''){
                var rowCount = document.getElementById("rowCount6").value;
                document.getElementById("rowCount6").value = rowCount-1;
                regenerateBill6('dataTable6');
            }else{
                var url = '${pageContext.request.contextPath}/pengambilan/mmkizinlalu?deleteSingle&idKandungan='
                    +idKandungan+'&formPtg='+formPtg+'&form1='+form1;

                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
            }
        }

        function regenerateBill6(tableid){
            try{
                var table = document.getElementById(tableid);
                var rowCount = table.rows.length;
                if(rowCount > 1){
                    for(var i=0;i<rowCount;i++){
                        var a = table.rows[i].cells[0];
                        a.innerHTML= "<b>3."+(i+1)+"</b>";
                        if(i>0){
                            table.rows[i].cells[1].childNodes[0].name= 'kandungan6'+(i+1);
                            table.rows[i].cells[2].childNodes[0].id= i+1;
                        }
                    }
                }
            }catch(e){
                alert("Error in regenerateBill");
            }
        }

        function addTable7(divId){

            var id1 = document.getElementById(divId);
            var count7 = document.getElementById ("count7").value;
            // Increment table count by 1
            document.getElementById ("count7").value = parseInt(count7)+1;
            count7 = document.getElementById ("count7").value;

            // create New table
            var table7 = document.createElement("TABLE");
            table7.id = "table7"+count7;
            //table.className = "tablecloth";
            table7.width="100%";
            //table.border=2;
            var rowCount7 = table7.rows.length;
            var row7 = table7.insertRow(rowCount7);

            var cell0 = row7.insertCell(0);
            cell0.innerHTML = "<b>4."+(count7)+"</b>";
            cell0.style.textAlign = "center";
    <%--cell0.style.backgroundColor = ("#328aa4");--%>
            cell0.width="2%";

            var cell1 = row7.insertCell(1);
            var element1 = document.createElement("textarea");
            element1.t = "syorPentadbir"+(count7)+(rowCount7+1);
            element1.rows = 5;
            element1.cols = 130;
            element1.name ="syorPentadbir"+(count7)+(rowCount7+1);
            element1.id ="syorPentadbir"+(count7)+(rowCount7+1);
    <%--element1.value ="syorPentadbir"+(count5)+(rowCount5+1);--%>
            cell1.colSpan = 2;
            cell1.appendChild(element1);

            // Add hidden field
            var cell2 = row7.insertCell(2);
            var element2 = document.createElement ("input");
            element2.setAttribute("type", "hidden");
            element2.setAttribute("id", "count7"+(count7));
            element2.setAttribute("name", "count7"+(count7));
            element2.setAttribute("value", 1);
            cell2.appendChild(element2);

            // Add tambah button
            var cell3 = row7.insertCell(3);
            var button = document.createElement('input');
            var buttonName = "tambah7" +(count7);
            button.setAttribute('type','button');
            button.className = "btn";
            button.setAttribute('name',buttonName);
            button.setAttribute('value','Tambah'+' 4.'+(count7));
            button.onclick=function(){addDynamicRow7(table7.id,element2.id);};
            cell3.appendChild(button);

            var button1 = document.createElement('input');
            var buttonName1 = "hapus7" +(count7);
            button1.setAttribute('type','button');
            button1.className = "btn";
            button1.setAttribute('name',buttonName1);
            button1.setAttribute('value','Hapus'+' 4.'+(count7));
            button1.onclick=function(){deleteDynamicRow7(table7.id,element2.id);};
            cell3.appendChild(button1);

            id1.appendChild(table7);
            id1.appendChild(document.createElement('br'));
        }

        function deleteTable7(formPtg,form1) {
            var bil = 4;
            var temp7 = $("#temp7").val();
            var count7 = $("#count7").val();

            var table7 = document.getElementById("table7"+count7);
            var rowCount7 = table7.rows.length;
            for(var i=rowCount7-1;i>=0;i--){
                table7.deleteRow(i);
                document.getElementById ("count7").value = parseInt(count7)-1;
            }

            if(parseInt(count7) <= parseInt(temp7)) {

                var url = '${pageContext.request.contextPath}/pengambilan/mmkizinlalu?deleteTable&bil='
                    +bil+'&formPtg='+formPtg+'&form1='+form1;

                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');

            }


        }

        function addDynamicRow7(tableID,countID) {

            var table7 = document.getElementById(tableID);
            var rowCount7 = table7.rows.length;
            var row7 = table7.insertRow(rowCount7);

            document.getElementById(countID).value = parseInt(document.getElementById(countID).value)+1;

            var count7 = parseInt(tableID.substring(6));

            var cell0 = row7.insertCell(0);
            cell0.innerHTML = "";

            var cell1 = row7.insertCell(1);
            var arr=['a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'];
    <%--cell1.innerHTML = "<b>"+arr[rowCount7-1]+".</b>";--%>
            cell1.innerHTML = "<b>4."+(count7)+"."+(rowCount7)+".</b>";

            var cell2 = row7.insertCell(2);
            var element2 = document.createElement("textarea");
            element2.t = "syorPentadbir"+(count7)+(rowCount7+1);
            element2.rows = 5;
            element2.cols = 130;
            element2.name ="syorPentadbir"+(count7)+(rowCount7+1);
            element2.id ="syorPentadbir"+(count7)+(rowCount7+1);
    <%--element2.value ="syorPentadbir"+(count7)+(rowCount7+1);--%>
            cell2.appendChild(element2);

        }

        function deleteDynamicRow7(tableID,countID,formPtg,form1) {
            var table7 = document.getElementById(tableID);
            var rowCount7 = table7.rows.length;
            var i = tableID.substring(6);

            if(rowCount7 >1){
                var obj = document.getElementById("kand7"+(i)+(rowCount7));
                var idKandungan = $("#kand7"+(i)+(rowCount7)).val();
                table7.deleteRow(rowCount7-1);
                document.getElementById(countID).value = table7.rows.length;
                if (obj != null) {

                    var url = '${pageContext.request.contextPath}/pengambilan/mmkizinlalu?deleteSingle&idKandungan='
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

        function addRow8(tableID4) {
            document.form2.rowCount8.value = 1;
            var table = document.getElementById(tableID4);

            var rowCount8 = table.rows.length;
            var row = table.insertRow(rowCount8);

            var cell2 = row.insertCell(0);
            cell2.innerHTML = "<b>"+"5." +(rowCount8+1)+"</b>";

            var cell1 = row.insertCell(1);
            var element1 = document.createElement("textarea");
            element1.t = "kandungan8"+(rowCount8+1);
            element1.rows = 5;
            element1.cols = 150;
            element1.name ="kandungan8"+(rowCount8+1);
            element1.id ="kandungan8"+(rowCount8+1);
            cell1.appendChild(element1);
            //alert(element1.name)
            document.form2.rowCount8.value=rowCount8 +1;

            var cell3 = row.insertCell(2);
            var e1 = document.createElement("INPUT");
            e1.t = "button"+(rowCount8+1);
            e1.setAttribute("type","button");
            e1.className="btn";
            e1.value="Hapus";
            e1.id =(rowCount8+1);
            e1.onclick=function(){deleteRow8((e1.id));};
            cell3.appendChild(e1);
        }
        
        
function deleteRow8(formPtg,form1,idKandungan,index)
    {
            document.getElementById('dataTable8').deleteRow(index-1);
            if(idKandungan == ''){
                var rowCount = document.getElementById("rowCount8").value;
                document.getElementById("rowCount8").value = rowCount-1;
                regenerateBill8('dataTable8');
            }else{
                var url = '${pageContext.request.contextPath}/pengambilan/mmkizinlalu?deleteSingle&idKandungan='
                    +idKandungan+'&formPtg='+formPtg+'&form1='+form1;

                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
            }
        }

        function regenerateBill8(tableid){
            try{
                var table = document.getElementById(tableid);
                var rowCount = table.rows.length;
                if(rowCount > 1){
                    for(var i=0;i<rowCount;i++){
                        var a = table.rows[i].cells[0];
                        a.innerHTML= "<b>5."+(i+1)+"</b>";
                        if(i>0){
                            table.rows[i].cells[1].childNodes[0].name= 'kandungan8'+(i+1);
                            table.rows[i].cells[2].childNodes[0].id= i+1;
                        }
                    }
                }
            }catch(e){
                alert("Error in regenerateBill");
            }
        }

        function addRow9(tableID4) {
            document.form2.rowCount9.value = 1;
            var table = document.getElementById(tableID4);

            var rowCount9 = table.rows.length;
            var row = table.insertRow(rowCount9);

            var cell2 = row.insertCell(0);
            cell2.innerHTML = "<b>"+"6." +(rowCount9+1)+"</b>";

            var cell1 = row.insertCell(1);
            var element1 = document.createElement("textarea");
            element1.t = "kandungan9"+(rowCount9+1);
            element1.rows = 5;
            element1.cols = 150;
            element1.name ="kandungan9"+(rowCount9+1);
            element1.id ="kandungan9"+(rowCount9+1);
            cell1.appendChild(element1);
            //alert(element1.name)
            document.form2.rowCount9.value=rowCount9 +1;

            var cell3 = row.insertCell(2);
            var e1 = document.createElement("INPUT");
            e1.t = "button"+(rowCount9+1);
            e1.setAttribute("type","button");
            e1.className="btn";
            e1.value="Hapus";
            e1.id =(rowCount9+1);
            e1.onclick=function(){deleteRow9((e1.id));};
            cell3.appendChild(e1);
        }
        function deleteRow9(formPtg,form1,idKandungan,index)
    {
            document.getElementById('dataTable9').deleteRow(index-1);
            if(idKandungan == ''){
                var rowCount = document.getElementById("rowCount9").value;
                document.getElementById("rowCount9").value = rowCount-1;
                regenerateBill9('dataTable9');
            }else{
                var url = '${pageContext.request.contextPath}/pengambilan/mmkizinlalu?deleteSingle&idKandungan='
                    +idKandungan+'&formPtg='+formPtg+'&form1='+form1;

                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
            }
        }

        function regenerateBill9(tableid){
            try{
                var table = document.getElementById(tableid);
                var rowCount = table.rows.length;
                if(rowCount > 1){
                    for(var i=0;i<rowCount;i++){
                        var a = table.rows[i].cells[0];
                        a.innerHTML= "<b>6."+(i+1)+"</b>";
                        if(i>0){
                            table.rows[i].cells[1].childNodes[0].name= 'kandungan9'+(i+1);
                            table.rows[i].cells[2].childNodes[0].id= i+1;
                        }
                    }
                }
            }catch(e){
                alert("Error in regenerateBill");
            }
        }

        function validation() {

            var count3=$("#rowCount3").val();
            for(var i=1;i<=count3;i++){
                var kandungan3= $("#kandungan3"+i).val();
                if(kandungan3 == ""){
                    alert('Sila pilih " 2. Latar Belakang Tanah" terlebih dahulu.');
                    $("#kandungan3"+i).focus();
                    return false;
                }
            }

            var count5 = $("#count5").val();
            for(var i=1;i<=count5;i++){
                var recordCount5 = $("#count5"+i).val();
                for(var j=1;j<=recordCount5;j++){
                    var rayuanTuan = $("#rayuanTuan"+i+j).val();
                    if(rayuanTuan == ""){
                        alert('Sila pilih " 5. Rayuan Tanah Tanah " terlebih dahulu.');
                        $("#rayuanTuan"+i+j).focus();
                        return false;
                    }
                }

            }

            var count6=$("#rowCount6").val();
            for(var i=1;i<=count6;i++){
                var kandungan6= $("#kandungan6"+i).val();
                if(kandungan6 == ""){
                    alert('Sila pilih " 6. Huraian Pentadbir Tanah Seremban" terlebih dahulu.');
                    $("#kandungan6"+i).focus();
                    return false;
                }
            }

            var count7 = $("#count7").val();
            for(var i=1;i<=count7;i++){
                var recordCount7 = $("#count7"+i).val();
                for(var j=1;j<=recordCount7;j++){
                    var syorPentadbir = $("#syorPentadbir"+i+j).val();
                    if(syorPentadbir == ""){
                        alert('Sila pilih " 3. Syor Pentadbir Tanah Seremban" terlebih dahulu.');
                        $("#syorPentadbir"+i+j).focus();
                        return false;
                    }
                }

            }

            var count8=$("#rowCount8").val();
            for(var i=1;i<=count8;i++){
                var kandungan8= $("#kandungan8"+i).val();
                if(kandungan8 == ""){
                    alert('Sila pilih " 4. Huraian Pengarah Tanah Dan Galian, Negeri Sembilan " terlebih dahulu.');
                    $("#kandungan8"+i).focus();
                    return false;
                }
            }

            var count9=$("#rowCount9").val();
            for(var i=1;i<=count9;i++){
                var kandungan9= $("#kandungan9"+i).val();
                if(kandungan9 == ""){
                    alert('Sila pilih " 6. Syor Pengarah Tanah Dan Galian, Negeri Sembilan" terlebih dahulu.');
                    $("#kandungan9"+i).focus();
                    return false;
                }
            }

            if($("#heading2").val() == ""){
                alert('Sila pilih sebahagian/keseluruhan di ruangan tajuk terlebih dahulu.');
                $("#heading2").focus();
                return false;
            }

            return true;
        }
</script>

<s:form beanclass="etanah.view.stripes.pengambilan.MmkIzinLaluActionBean" name="form2">
    <s:messages/>
    <s:errors/>
    <c:if test="${form1}">
        <s:hidden name="form1" value="${form1}"/>
        <s:hidden name="formPtg" value="false"/>
        <s:hidden name="count5" id="count5" value="${actionBean.count5}"/>
        <s:hidden name="temp5" id="temp5" value="${actionBean.count5}"/>
        <s:hidden name="count7" id="count7" value="${actionBean.count7}"/>
        <s:hidden name="temp7" id="temp7" value="${actionBean.count7}"/>
        <div class="subtitle">
            <fieldset class="aras1" id="locate">
                <div class="content" align="center">
                    <c:if test="${actionBean.kodUrusan eq 'PILDA'}">
                    <b>( MAJLIS MESYUARAT KERAJAAN NEGERI )</b>
                    </c:if>
                    <table border="0" width="80%">
                        <tr><td> &nbsp;</td></tr>
                        <tr><td align="left">
                                    <c:if test="${actionBean.kodUrusan eq 'PILDA'}">
                                    <b><label><font color="black">Kertas Mesyuarat No<font color="red">*</font> :</font></label><s:text name="kertasBil"  size="10" style="text-align:left" id="kertasBil" disabled="true"/>/${actionBean.kertasTahun}<s:hidden name="kertasTahun" value="${actionBean.kertasTahun}"/></b>
                                    </c:if>
                                    </td></tr>
                        <tr><td><b><s:hidden name="heading1" class="normal_text"/><s:hidden name="heading3" class="normal_text"/>
                                    ${actionBean.heading1}
                                    <c:forEach items="${actionBean.perbicaraanKehadiranList}" var="senarai">
                                            <c:out value="${senarai.nama}"/>&nbsp;&nbsp;
                                 </c:forEach>
                                    ${actionBean.heading3}</b></td></tr>
                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b>1. TUJUAN</b></td></tr>
                        <tr>
                            <td>
                                <table width="100%">
                                    <tr><td >&nbsp;&nbsp;&nbsp;</td><td valign="top">1.1.&nbsp;</td><td><s:textarea rows="7" cols="160" name="tujuan" class="normal_text"/></td></tr>
                                </table>
                            </td>
                        </tr>
                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b>2. LATAR BELAKANG</b></td></tr>
                        <tr><td>
                                <table id ="dataTable3">
                                    <c:set var="i" value="1" />
                                    <c:forEach items="${actionBean.senaraiKertasKandungan3}" var="line">
                                        <tr style="font-weight:bold">
                                            <td style="display:none">${line.idKandungan}</td>
                                            <td><c:out value="${line.subtajuk}"/></td>
                                            <td><font><s:textarea name="kandungan3${i}" id="kandungan3${i}" rows="5" cols="150" class="normal_text">${line.kandungan}</s:textarea></font></td>
                                            <td><s:button class="btn" value="Hapus" name="delete" id="${i}" onclick="deleteRow3('${formPtg}','${form1}',${line.idKandungan},${i})"/></td>
                                        </tr>
                                        <s:hidden name="idKandungan3${i}" id="idKandungan3${i}" value="${line.idKandungan}" />
                                        <c:set var="i" value="${i+1}" />
                                    </c:forEach>
                                    <s:hidden name="rowCount3" value="${i-1}" id="rowCount3"/>
                                </table>
                        <tr><td align="right"><s:button name="perihalPermohonan" value="Tambah" class="btn" onclick="addRow3('dataTable3')" />
                                <%--<s:button name="perihalPermohonan" value="Hapus" class="btn" onclick="deleteRow3()" />--%>
                            </td>
                        </tr>
                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b>3. ULASAN PENTADBIR TANAH SEREMBAN</b></td></tr>
                        <tr><td>
                                <table id ="dataTable6">
                                    <c:set var="k" value="1" />
                                    <c:forEach items="${actionBean.senaraiKertasKandungan6}" var="line">
                                        <tr style="font-weight:bold">
                                            <td style="display:none">${line.idKandungan}</td>
                                            <td><c:out value="${line.subtajuk}"/></td>
                                            <td><font><s:textarea name="kandungan6${k}" id="kandungan6${k}" rows="5" cols="150" readonly="true" class="normal_text">${line.kandungan}</s:textarea></font></td>
                                            <td><s:button class="btn" value="Hapus" name="delete" id="${k}" onclick="deleteRow6('${formPtg}','${form1}',${line.idKandungan},${i})" disabled="false"/></td>
                                        </tr>
                                        <s:hidden name="idKandungan6${k}" id="idKandungan6${k}" value="${line.idKandungan}" />
                                        <c:set var="k" value="${k+1}" />
                                    </c:forEach>
                                    <s:hidden name="rowCount6" value="${k-1}" id="rowCount6"/>
                                </table>
                        <tr><td align="right"><s:button name="ulasanPengarahTanah" value="Tambah" class="btn" onclick="addRow6('dataTable6')"/>
                                <%--<s:button name="ulasanPengarahTanah" value="Hapus" class="btn" onclick="deleteRow6()" />--%>
                            </td>
                        </tr>
                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b>4. HURAIAN PENTADBIR TANAH <kod daerah></b></td></tr>

                        <c:forEach var="i" begin="1" end="${actionBean.count7}">
                            <tr><td>
                                    <table  width="90%" id="table7${i}" >
                                        <c:set var="recordCount7" value="0"/>
                                        <c:forEach items="${actionBean.senaraiSyorPentadbir[i]}" var="senarai">
                                            <c:set var="recordCount7" value="${recordCount7+1}"/>
                                            <c:if test="${recordCount7 eq 1}">
                                                <tr> <td><b>4.${i}</b></td>
                                                    <td colspan="2"><s:textarea rows="5" cols="130" name="syorPentadbir${i}${recordCount7}" id="syorPentadbir${i}${recordCount7}" value="${senarai.kandungan}" class="normal_text"/></td>
                                                    <td><s:hidden name="count7${i}" id="count7${i}" value="${(fn:length(actionBean.senaraiSyorPentadbir[i]))}" /> </td>
                                                    <td><s:button class="btn"  value="Tambah 4.${i}" name="add" id="tambah7${i}" onclick="addDynamicRow7('table7${i}','count7${i}')"/></td>
                                                    <td><s:button class="btn" value="Hapus 4.${i}" name="hapus" id="hapus7${i}" onclick="deleteDynamicRow7('table7${i}','count7${i}','${formPtg}','${form1}')"/></td>
                                                </tr>
                                            </c:if>
                                            <c:if test="${recordCount7 ne 1}">
                                                <tr>  <td></td>
                                                    <td><b>4.${i}.<c:out value="${recordCount7-1}"/>.</b></td>
                                                    <%--<td><b><c:out value="${actionBean.str[recordCount7-1]}"/>.</b></td>--%>
                                                    <td><s:textarea rows="5" cols="130" name="syorPentadbir${i}${recordCount7}" id="syorPentadbir${i}${recordCount7}" value="${senarai.kandungan}" class="normal_text"/></td>
                                                </tr>
                                            </c:if>
                                            <s:hidden name="kand7${i}${recordCount7}" id="kand7${i}${recordCount7}" value="${senarai.idKandungan}" />
                                        </c:forEach>
                                    </table>
                                </td>
                            </tr>
                        </c:forEach>
                        <tr><td align="right">
                                <div id="Tables7">

                                </div>
                                <s:button class="btn" value="Tambah" name="add" onclick="addTable7('Tables7')"/>
                                <s:button class="btn" value="Hapus" name="add" onclick="deleteTable7('${formPtg}','${form1}')"/>
                            </td>
                        </tr>
                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b>5. SYOR PENTADBIR TANAH <kod daerah></b></td></tr>
                        <tr><td>
                                <table id ="dataTable8">
                                    <c:set var="k" value="1" />
                                    <c:forEach items="${actionBean.senaraiKertasKandungan8}" var="line">
                                        <tr style="font-weight:bold">
                                            <td style="display:none">${line.idKandungan}</td>
                                            <td><c:out value="${line.subtajuk}"/></td>
                                            <td><font><s:textarea name="kandungan8${k}" id="kandungan8${k}" rows="5" cols="150" class="normal_text">${line.kandungan}</s:textarea></font></td>
                                            <td><s:button class="btn" value="Hapus" name="delete" id="${k}" onclick="deleteRow8('${formPtg}','${form1}',${line.idKandungan},${i})" disabled="false"/></td>
                                        </tr>
                                        <s:hidden name="idKandungan8${k}" id="idKandungan8${k}" value="${line.idKandungan}" />
                                        <c:set var="k" value="${k+1}" />
                                    </c:forEach>
                                    <s:hidden name="rowCount8" value="${k-1}" id="rowCount8"/>
                                </table>
                        <tr><td align="right"><s:button name="ulasanPengarahTanah" value="Tambah" class="btn" onclick="addRow8('dataTable8')" disabled="false"/>
                                <%--<s:button name="ulasanPengarahTanah" value="Hapus" class="btn" onclick="deleteRow8()" />--%>
                            </td>
                        </tr>

                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b>6. SYOR PENGARAH TANAH DAN GALIAN, NEGERI SEMBILAN</b></td></tr>
                        <tr><td>
                                <table id ="dataTable9">
                                    <c:set var="k" value="1" />
                                    <c:forEach items="${actionBean.senaraiKertasKandungan9}" var="line">
                                        <tr style="font-weight:bold">
                                            <td style="display:none">${line.idKandungan}</td>
                                            <td><c:out value="${line.subtajuk}"/></td>
                                            <td><font><s:textarea name="kandungan9${k}" id="kandungan9${k}" rows="5" cols="150" class="normal_text">${line.kandungan}</s:textarea></font></td>
                                            <td><s:button class="btn" value="Hapus" name="delete" id="${k}" onclick="deleteRow9('${formPtg}','${form1}',${line.idKandungan},${i})" disabled="true"/></td>
                                        </tr>
                                        <s:hidden name="idKandungan9${k}" id="idKandungan9${k}" value="${line.idKandungan}" />
                                        <c:set var="k" value="${k+1}" />
                                    </c:forEach>
                                    <s:hidden name="rowCount9" value="${k-1}" id="rowCount9"/>
                                </table>
                        <tr><td align="right"><s:button name="ulasanPengarahTanah" value="Tambah" class="btn" onclick="addRow9('dataTable9')" disabled="true"/>
                                <%--<s:button name="ulasanPengarahTanah" value="Hapus" class="btn" onclick="deleteRow9()" />--%>
                            </td>
                        </tr>
                        
                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b>7. KEPUTUSAN</b></td></tr>
                        <c:if test="${actionBean.kodUrusan eq 'PILDA'}">
                        <tr>
                            <td width="100%"><b>7.1</b>
                                
                                Dikemukakan untuk mendapat pertimbangan dan keputusan Majlis Mesyuarat Kerajaan, Negeri Sembilan Darul Khusus.
                                
                            </td>
                        </tr>
                        </c:if>
                    </table>
                </div>
            </fieldset>
        </div>
        <p align="center">
            <s:hidden name="count5" id="count5" value="${actionBean.count5}"/>
            <s:hidden name="temp5" id="temp5" value="${actionBean.count5}"/>
            <s:hidden name="count7" id="count7" value="${actionBean.count7}"/>
            <s:hidden name="temp7" id="temp7" value="${actionBean.count7}"/>
            <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>
        </p>
    </c:if>
    <c:if test="${formPtg}">
        <s:hidden name="count5" id="count5" value="${actionBean.count5}"/>
        <s:hidden name="temp5" id="temp5" value="${actionBean.count5}"/>
        <s:hidden name="count7" id="count7" value="${actionBean.count7}"/>
        <s:hidden name="temp7" id="temp7" value="${actionBean.count7}"/>
        <div class="subtitle">
            <fieldset class="aras1" id="locate">
                <div class="content" align="center">
                    <c:if test="${actionBean.kodUrusan eq 'PILDA'}">
                    <b>( MAJLIS MESYUARAT KERAJAAN NEGERI )</b>
                    </c:if>
                    <table border="0" width="80%">
                        <tr><td> &nbsp;</td></tr>
                        <tr><td align="left">
                                    <c:if test="${actionBean.kodUrusan eq 'PILDA'}">
                                    <b><label><font color="black">Kertas Mesyuarat No<font color="red">*</font> :</font></label><s:text name="kertasBil"  size="10" style="text-align:left" id="kertasBil"/>/${actionBean.kertasTahun}<s:hidden name="kertasTahun" value="${actionBean.kertasTahun}"/></b>
                                    </c:if>
                                    </td></tr>
                        <tr><td><b><s:hidden name="heading1" class="normal_text"/><s:hidden name="heading3" class="normal_text"/>
                                    ${actionBean.heading1}
                                     <c:forEach items="${actionBean.perbicaraanKehadiranList}" var="senarai">
                                            <c:out value="${senarai.nama}"/>&nbsp;&nbsp;
                                 </c:forEach>
                                   ${actionBean.heading3}</b></td></tr>
                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b>1. TUJUAN</b></td></tr>
                        <tr>
                            <td>
                                <table width="100%">
                                    <tr><td >&nbsp;&nbsp;&nbsp;</td><td valign="top">1.1.&nbsp;</td><td><s:textarea rows="5" cols="140" name="tujuan" class="normal_text" readonly="true"/></td></tr>
                                </table>
                            </td>
                        </tr>

                        

                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b>2. LATAR BELAKANG </b></td></tr>
                        <tr><td>
                                <table id ="dataTable3">
                                    <c:set var="i" value="1" />
                                    <c:forEach items="${actionBean.senaraiKertasKandungan3}" var="line">
                                        <tr style="font-weight:bold">
                                            <td style="display:none">${line.idKandungan}</td>
                                            <td><c:out value="${line.subtajuk}"/></td>
                                            <td><font><s:textarea name="kandungan3${i}" id="kandungan3${i}" rows="5" cols="150" class="normal_text" readonly="true">${line.kandungan}</s:textarea></font></td>
                                            <td><s:button class="btn" value="Hapus" name="delete" id="${i}" onclick="deleteRow3('${formPtg}','${form1}')" disabled="true"/></td>
                                        </tr>
                                        <s:hidden name="idKandungan3${i}" id="idKandungan3${i}" value="${line.idKandungan}" />
                                        <c:set var="i" value="${i+1}" />
                                    </c:forEach>
                                    <s:hidden name="rowCount3" value="${i-1}" id="rowCount3"/>
                                </table>
                        <tr><td align="right"><s:button name="perihalPermohonan" value="Tambah" class="btn" onclick="addRow3('dataTable3')" disabled="true"/>
                                <%--<s:button name="perihalPermohonan" value="Hapus" class="btn" onclick="deleteRow3()" />--%>
                            </td>
                        </tr>
                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b>3. ULASAN PENTADBIR TANAH SEREMBAN</b></td></tr>
                        <tr><td>
                                <table id ="dataTable6">
                                    <c:set var="k" value="1" />
                                    <c:forEach items="${actionBean.senaraiKertasKandungan6}" var="line">
                                        <tr style="font-weight:bold">
                                            <td style="display:none">${line.idKandungan}</td>
                                            <td><c:out value="${line.subtajuk}"/></td>
                                            <td><font><s:textarea name="kandungan6${k}" id="kandungan6${k}" rows="5" cols="150" class="normal_text" readonly="true">${line.kandungan}</s:textarea></font></td>
                                            <td><s:button class="btn" value="Hapus" name="delete" id="${k}" onclick="deleteRow6('${formPtg}','${form1}')" disabled="true"/></td>
                                        </tr>
                                        <s:hidden name="idKandungan6${k}" id="idKandungan6${k}" value="${line.idKandungan}" />
                                        <c:set var="k" value="${k+1}" />
                                    </c:forEach>
                                    <s:hidden name="rowCount6" value="${k-1}" id="rowCount6"/>
                                </table>
                        <tr><td align="right"><s:button name="ulasanPengarahTanah" value="Tambah" class="btn" onclick="addRow6('dataTable6')" disabled="true"/>
                                <%--<s:button name="ulasanPengarahTanah" value="Hapus" class="btn" onclick="deleteRow6()" />--%>
                            </td>
                        </tr>
                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b>4. HURAIAN PENTADBIR TANAH SEREMBAN</b></td></tr>

                        <c:forEach var="i" begin="1" end="${actionBean.count7}">
                            <tr><td>
                                    <table  width="90%" id="table7${i}" >
                                        <c:set var="recordCount7" value="0"/>
                                        <c:forEach items="${actionBean.senaraiSyorPentadbir[i]}" var="senarai">
                                            <c:set var="recordCount7" value="${recordCount7+1}"/>
                                            <c:if test="${recordCount7 eq 1}">
                                                <tr> <td><b>4.${i}</b></td>
                                                    <td colspan="2"><s:textarea rows="5" cols="130" name="syorPentadbir${i}${recordCount7}" id="syorPentadbir${i}${recordCount7}" value="${senarai.kandungan}" readonly="true" class="normal_text"/></td>
                                                    <td><s:hidden name="count7${i}" id="count7${i}" value="${(fn:length(actionBean.senaraiSyorPentadbir[i]))}" /> </td>
                                                    <td><s:button class="btn"  value="Tambah 4.${i}" name="add" id="tambah7${i}" onclick="addDynamicRow7('table7${i}','count7${i}')" disabled="true"/></td>
                                                    <td><s:button class="btn" value="Hapus 4.${i}" name="hapus" id="hapus7${i}" onclick="deleteDynamicRow7('table7${i}','count7${i}','${formPtg}','${form1}')" disabled="true"/></td>
                                                </tr>
                                            </c:if>
                                            <c:if test="${recordCount7 ne 1}">
                                                <tr>  <td></td>
                                                    <td><b>4.${i}.<c:out value="${recordCount7-1}"/>.</b></td>
                                                    <%--<td><b><c:out value="${actionBean.str[recordCount7-1]}"/>.</b></td>--%>
                                                    <td><s:textarea rows="5" readonly="true" cols="130" name="syorPentadbir${i}${recordCount7}" id="syorPentadbir${i}${recordCount7}" value="${senarai.kandungan}" class="normal_text"/></td>
                                                </tr>
                                            </c:if>
                                            <s:hidden name="kand7${i}${recordCount7}" id="kand7${i}${recordCount7}" value="${senarai.idKandungan}" />
                                        </c:forEach>
                                    </table>
                                </td>
                            </tr>
                        </c:forEach>
                        <tr><td align="right">
                                <div id="Tables7">

                                </div>
                                <s:button class="btn" value="Tambah" name="add" onclick="addTable7('Tables7')" disabled="true"/>
                                <s:button class="btn" value="Hapus" name="add" onclick="deleteTable7('${formPtg}','${form1}')" disabled="true"/>
                            </td>
                        </tr>

                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b>5. SYOR PENTADBIR TANAH</b></td></tr>
                        <tr><td>
                                <table id ="dataTable8">
                                    <c:set var="k" value="1" />
                                    <c:forEach items="${actionBean.senaraiKertasKandungan8}" var="line">
                                        <tr style="font-weight:bold">
                                            <td style="display:none">${line.idKandungan}</td>
                                            <td><c:out value="${line.subtajuk}"/></td>
                                            <td><font><s:textarea name="kandungan8${k}" id="kandungan8${k}" rows="5" cols="150" class="normal_text">${line.kandungan}</s:textarea></font></td>
                                            <td><s:button class="btn" value="Hapus" name="delete" id="${k}" disabled="true" onclick="deleteRow8('${formPtg}','${form1}',${line.idKandungan},${k})"/></td>
                                        </tr>
                                        <s:hidden name="idKandungan8${k}" id="idKandungan8${k}" value="${line.idKandungan}" />
                                        <c:set var="k" value="${k+1}" />
                                    </c:forEach>
                                    <s:hidden name="rowCount8" value="${k-1}" id="rowCount8"/>
                                </table>
                        <tr><td align="right"><s:button name="ulasanPengarahTanah" value="Tambah" class="btn" onclick="addRow8('dataTable8')" disabled="true"/>
                                <%--<s:button name="ulasanPengarahTanah" value="Hapus" class="btn" onclick="deleteRow8()" />--%>
                            </td>
                        </tr>

                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b>6. SYOR PENGARAH TANAH DAN GALIAN, NEGERI SEMBILAN</b></td></tr>
                        <tr><td>
                                <table id ="dataTable9">
                                    <c:set var="k" value="1" />
                                    <c:forEach items="${actionBean.senaraiKertasKandungan9}" var="line">
                                        <tr style="font-weight:bold">
                                            <td style="display:none">${line.idKandungan}</td>
                                            <td><c:out value="${line.subtajuk}"/></td>
                                            <td><font><s:textarea name="kandungan9${k}" id="kandungan9${k}" rows="5" cols="150" class="normal_text">${line.kandungan}</s:textarea></font></td>
                                            <td><s:button class="btn" value="Hapus" name="delete" id="${k}" onclick="deleteRow9('${formPtg}','${form1}',${line.idKandungan},${k})"/></td>
                                        </tr>
                                        <s:hidden name="idKandungan9${k}" id="idKandungan9${k}" value="${line.idKandungan}" />
                                        <c:set var="k" value="${k+1}" />
                                    </c:forEach>
                                    <s:hidden name="rowCount9" value="${k-1}" id="rowCount9"/>
                                </table>
                        <tr><td align="right"><s:button name="ulasanPengarahTanah" value="Tambah" class="btn" onclick="addRow9('dataTable9')" />
                                <%--<s:button name="ulasanPengarahTanah" value="Hapus" class="btn" onclick="deleteRow9()" />--%>
                            </td>
                        </tr>

                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b>7. KEPUTUSAN</b></td></tr>
                        <tr>
                            
                            <td width="100%"><b>7.1</b>
                                Dikemukakan untuk mendapat pertimbangan dan keputusan Majlis Mesyuarat Kerajaan, Negeri Sembilan Darul Khusus.
                                
                            </td>
                         
                        </tr>
                    </table>
                </div>
            </fieldset>
        </div>
        <p align="center">
            <s:hidden name="count5" id="count5" value="${actionBean.count5}"/>
            <s:hidden name="temp5" id="temp5" value="${actionBean.count5}"/>
            <s:hidden name="count7" id="count7" value="${actionBean.count7}"/>
            <s:hidden name="temp7" id="temp7" value="${actionBean.count7}"/>
            <s:hidden name="idKertas" value="${permohonanKertas.idKertas}"/>
            <s:button name="simpanPtg" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>
        </p>
    </c:if>
</s:form>
