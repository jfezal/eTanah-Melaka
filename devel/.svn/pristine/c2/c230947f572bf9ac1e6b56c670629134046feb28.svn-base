<%-- 
    Document   : AffidavitPtsp
    Created on : 20-Mar-2011, 19:17:53
    Author     : nordiyana @hazirah
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
        cell2.innerHTML = "<b>&nbsp;&nbsp;&nbsp;&nbsp;"+"1." +(rowCount3+1)+"</b>";

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
                var url = '${pageContext.request.contextPath}/pengambilan/affidavitMahkamahPtsp?deleteSingle&idKandungan='
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
                var url = '${pageContext.request.contextPath}/pengambilan/borang_mmkn_sek4?deleteSingle&idKandungan='
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
                var url = '${pageContext.request.contextPath}/pengambilan/borang_mmkn_sek4?deleteSingle&idKandungan='
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

                var url = '${pageContext.request.contextPath}/pengambilan/borang_mmkn_sek4?deleteTable&bil='
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

                    var url = '${pageContext.request.contextPath}/pengambilan/borang_mmkn_sek4?deleteSingle&idKandungan='
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

                var url = '${pageContext.request.contextPath}/pengambilan/borang_mmkn_sek4?deleteTable&bil='
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

                    var url = '${pageContext.request.contextPath}/pengambilan/borang_mmkn_sek4?deleteSingle&idKandungan='
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

        function ajaxLink(link, update) {
            $.get(link, function(data) {
                $(update).html(data);
                $(update).show();
            });
            return false;
        }
</script>

<s:form beanclass="etanah.view.stripes.pengambilan.AffidavitMahkamahPtspActionBean"  id="form2">
    <s:messages/>
    <s:errors/>
    <s:hidden name="count5" id="count5" value="${actionBean.count5}"/>
    <s:hidden name="temp5" id="temp5" value="${actionBean.count5}"/>
    <s:hidden name="count8" id="count8" value="${actionBean.count8}"/>
    <s:hidden name="temp8" id="temp8" value="${actionBean.count8}"/>
    <div id="hakmilik_details">
        <s:hidden name="idPihak" id="idPihak"/>
        <s:errors/>
        <fieldset class="aras1"><br/>
            <legend align="left"><b>Maklumat Hakmilik Permohonan</b></legend>
            <div align="center">
                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="5" cellpadding="0" cellspacing="0"
                               requestURI="/pengambilan/maklumatPampasan" id="line">
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column title="ID Hakmilik">
                        <s:link beanclass="etanah.view.stripes.pengambilan.AffidavitMahkamahPtspActionBean"
                                event="hakmilikDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                            <s:param name="idHakmilik" value="${line.hakmilik.idHakmilik}"/>${line.hakmilik.idHakmilik}</s:link>
                    </display:column>
                    <display:column property="hakmilik.noLot" title="No Lot/No PT" />
                    <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                    <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                </display:table>
            </div>
        </fieldset>
        <br /><br />

        <c:if test="${actionBean.hakmilik ne null}">
            <s:errors/>
            <c:if test="${showTuanTanah}">

                <fieldset class="aras1">
                    <legend>Tuan Tanah</legend><br />
                    <div align="center">Sila klik nama tuan tanah.</div>
                    <div align="center">

                        <display:table class="tablecloth" name="${actionBean.hakmilik}" cellpadding="0" cellspacing="0" id="tbl1">
                            <display:column title="No" sortable="true" style="vertical-align:baseline">${tbl1_rowNum}</display:column>
                            <display:column property="idHakmilik" title="ID Hakmilik" />
                            <display:column property="noLot" title="Nombor Lot/PT" />
                            <display:column title="Daerah" property="daerah.nama" class="daerah" />
                            <display:column property="bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                            <display:column title="Orang Berkepentingan" style="vertical-align:baseline">
                                <c:forEach items="${actionBean.hakmilik.senaraiPihakBerkepentingan}" var="senarai">

                                    <%--<c:if test="${actionBean.perbicaraanKehadiran.bantahElektrik eq '0'}">--%>
                                    <s:link beanclass="etanah.view.stripes.pengambilan.AffidavitMahkamahPtspActionBean"
                                            event="pihakDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                                        <s:hidden name="idPihak" value="${senarai.pihak.idPihak}"/>
                                        <s:param name="idPihak" value="${senarai.pihak.idPihak}"/>${senarai.pihak.nama}
                                    </s:link>
                                    <br/>
                                    No KP ${senarai.pihak.noPengenalan}
                                    <%--</c:if>--%>

                                    <%--<c:if test="${actionBean.perbicaraanKehadiran.bantahElektrik eq '1'}">

                                            <s:param name="idPihak" value="${senarai.pihak.idPihak}"/>${senarai.pihak.nama}

                                            <br/>
                                            No KP ${senarai.pihak.noPengenalan}

                                        </c:if>--%>

                                    <br/>
                                </c:forEach>
                            </display:column>
                            <display:column title="Luas Diambil" style="vertical-align:baseline"><fmt:formatNumber pattern="#,##0.0000" value="${actionBean.hakmilikPermohonan.luasTerlibat}"/>&nbsp;${actionBean.hakmilik.kodUnitLuas.nama}</display:column>
                        </display:table>
                    </div>
                    <br /><br />
                </fieldset>
            </c:if>
        </c:if>
        <br />

        <c:if test="${showDetails}">
            <s:errors/>
            <s:hidden name="idHakmilik" />
            <s:hidden name="count5" id="count5" value="${actionBean.count5}"/>
            <s:hidden name="temp5" id="temp5" value="${actionBean.count5}"/>
            <s:hidden name="count8" id="count8" value="${actionBean.count8}"/>
            <s:hidden name="temp8" id="temp8" value="${actionBean.count8}"/>
            <div class="subtitle">
                <fieldset class="aras1" id="locate">
                    <div class="content" align="center">
                        <table border="0" width="80%">
                            <tr align="left">
                                <td>No Fail</td>
                                <td>:</td>
                                <td>${actionBean.permohonan.idPermohonan}</td>
                            </tr>
                            <tr align="left">
                                <td>Saman Pemula</td>
                                <td>:</td>
                                <td>${actionBean.permohonanMahkamah.samanPemulaBil}<s:hidden name="samanPemulaBil" id="samanPemulaBil"/></td>
                            </tr>
                            <table align="center">
                                <tr align="center">
                                    <td align="center">
                                        <b><u>DALAM MAHKAMAH TINGGI MALAYA MELAKA</u></b>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="center">Di Dalam Perkara ${actionBean.hakmilik.lot.nama} ${actionBean.hakmilik.noLot} ${actionBean.hakmilik.kodHakmilik.kod} ${actionBean.hakmilik.noHakmilik} ${actionBean.hakmilik.bandarPekanMukim.nama}, Daerah ${actionBean.hakmilik.daerah.nama}, Melaka</td>
                                </tr>
                                <tr>
                                    <td align="center">DAN</td>
                                </tr>
                                <tr>
                                    <td align="center">Di Dalam Perkara A.7 k.2(1) Kaedah-Kaedah Mahkamah Tinggi Tahun 1980</td>
                                </tr>
                                <tr>
                                    <td>    Pentadbir Tanah Daerah ${actionBean.hakmilik.daerah.nama} - PEMOHON</td>
                                </tr>
                                <tr>
                                    <td>Saya <font style="text-transform: uppercase">${actionBean.peng.nama}</font>, bagi pihak Pentadbir Tanah ${actionBean.hakmilik.daerah.nama}, telah bersumpah dan menyebut seperti berikut :-</td>
                                </tr>
                            </table>
                            <br><br>

                            <table align="center">

                                <tr><td><font style="text-transform: capitalize"><s:textarea name="perakuan1.kandungan" rows="5" cols="140" id="perakuan1"/></font></td></tr>

                                <tr><td> &nbsp;</td></tr>
                                <tr><td>
                                        <table id ="dataTable3">
                                            <c:set var="i" value="1" />
                                            <c:forEach items="${actionBean.senaraiKertasKandungan3}" var="line">
                                                <tr style="font-weight:bold">
                                                    <td style="display:none">${line.idKandungan}</td>
                                                    <td>&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${line.subtajuk}"/></td>
                                                    <td><font><s:textarea name="kandungan3${i}" id="kandungan3${i}" rows="5" cols="150" class="normal_text">${line.kandungan}</s:textarea></font></td>
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

                            </table>
                        </table>
                    </div>
                </fieldset>
            </div>
            <p align="center">
                <s:hidden name="idPihak" value="${actionBean.idPihak}"/>
                <s:hidden name="idHakmilik" value="${actionBean.idHakmilik}"/>
                <s:hidden name="idKertas" value="${permohonanKertas.idKertas}"/>
                <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>
            </p>
        </c:if>
        <%--</c:if>--%>
    </div>

</s:form>
