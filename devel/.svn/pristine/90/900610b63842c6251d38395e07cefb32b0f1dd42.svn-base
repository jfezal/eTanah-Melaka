<%--
    Document   : ulasan_mmk
    Created on : Nov 29, 2010, 10:40:36 AM
    Author     : Rajesh
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

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
<script type="text/javascript">
    function addRow4(tableID2) {
        document.form2.rowCount4.value = 1;
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
        document.form2.rowCount4.value=rowCount4 +1;
    }
    function deleteRow4(ptg,ptPengambilanPtg)
    {
        var j = document.form2.rowCount4.value;
        var x= document.getElementById('dataTable4').rows[j-1].cells;
        var idKandungan = x[0].innerHTML;

        document.getElementById('dataTable4').deleteRow(j-1);
        document.form2.rowCount4.value = j -1;

        if (document.getElementById('idKandungan4'+(j)) != null) {
            var url = '${pageContext.request.contextPath}/pengambilan/ulasan_mmk_NS?deleteSingle&idKandungan='
                +idKandungan+'&ptg='+ptg+'&ptPengambilanPtg='+ptPengambilanPtg;

            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
    }

    function addRow5(tableID2) {
        document.form2.rowCount5.value = 1;
        var table = document.getElementById(tableID2);

        var rowCount5 = table.rows.length;
        var row = table.insertRow(rowCount5);

        var cell2 = row.insertCell(0);
        cell2.innerHTML = "<b>"+"5." +(rowCount5+1)+"</b>";

        var cell1 = row.insertCell(1);
        var element1 = document.createElement("textarea");
        element1.t = "kandungan5"+(rowCount5+1);
        element1.rows = 5;
        element1.cols = 150;
        element1.name ="kandungan5"+(rowCount5+1);
        element1.id ="kandungan5"+(rowCount5+1);
        cell1.appendChild(element1);
        //alert(element1.name)
        document.form2.rowCount5.value=rowCount5 +1;
    }
    function deleteRow5(ptg,ptPengambilanPtg)
    {
        var j = document.form2.rowCount5.value;
        var x= document.getElementById('dataTable5').rows[j-1].cells;
        var idKandungan = x[0].innerHTML;

        document.getElementById('dataTable5').deleteRow(j-1);
        document.form2.rowCount5.value = j -1;

        if (document.getElementById('idKandungan5'+(j)) != null) {
            var url = '${pageContext.request.contextPath}/pengambilan/ulasan_mmk_NS?deleteSingle&idKandungan='
                +idKandungan+'&ptg='+ptg+'&ptPengambilanPtg='+ptPengambilanPtg;

            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
    }

    function addTable5(divId){
        var id1 = document.getElementById(divId);
        var count5 = document.getElementById("count5").value;
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
    <%--element1.value ="syor"+(count)+(rowCount+1);--%>
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

        function deleteTable5(ptg,ptPengambilanPtg) {
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

                var url = '${pageContext.request.contextPath}/pengambilan/ulasan_mmk_NS?deleteTable&bil='
                    +bil+'&ptg='+ptg+'&ptPengambilanPtg='+ptPengambilanPtg;

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
    <%--element2.value ="syor"+(count)+(rowCount+1);--%>
            cell2.appendChild(element2);

        }

        function deleteDynamicRow5(tableID,countID,ptg,ptPengambilanPtg) {
            var table5 = document.getElementById(tableID);
            var rowCount5 = table5.rows.length;
            var i = tableID.substring(6);

            if(rowCount5 >1){
                var obj = document.getElementById("kand5"+(i)+(rowCount5));
                var idKandungan = $("#kand5"+(i)+(rowCount5)).val();
                table5.deleteRow(rowCount5-1);
                document.getElementById(countID).value = table5.rows.length;
                if (obj != null) {

                    var url = '${pageContext.request.contextPath}/pengambilan/ulasan_mmk_NS?deleteSingle&idKandungan='
                        +idKandungan+'&ptg='+ptg+'&ptPengambilanPtg='+ptPengambilanPtg;

                    $.get(url,
                    function(data){
                        $('#page_div').html(data);
                    },'html');
                }

            }else{
                alert("Cannot delete row .")
            }

        }

        function addRow6(tableID2) {
        document.form2.rowCount6.value = 1;
        var table = document.getElementById(tableID2);

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
    function deleteRow6(ptg,ptPengambilanPtg)
    {
        var j = document.form2.rowCount6.value;
        var x= document.getElementById('dataTable6').rows[j-1].cells;
        var idKandungan = x[0].innerHTML;

        document.getElementById('dataTable6').deleteRow(j-1);
        document.form2.rowCount6.value = j -1;

        if (document.getElementById('idKandungan6'+(j)) != null) {
            var url = '${pageContext.request.contextPath}/pengambilan/ulasan_mmk_NS?deleteSingle&idKandungan='
                +idKandungan+'&ptg='+ptg+'&ptPengambilanPtg='+ptPengambilanPtg;

            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
    }

        function addTable6(divId){
            var id1 = document.getElementById(divId);
            var count6 = document.getElementById("count6").value;
            // Increment table count by 1
            document.getElementById ("count6").value = parseInt(count6)+1;
            count6 = document.getElementById ("count6").value;
            // create New table
            var table6 = document.createElement("TABLE");
            table6.id = "table6"+count6;
            //table.className = "tablecloth";
            table6.width="100%";
            //table.border=2;
            var rowCount6 = table6.rows.length;
            var row6 = table6.insertRow(rowCount6);

            var cell0 = row6.insertCell(0);
            cell0.innerHTML = "<b>6."+(count6)+"</b>";
            cell0.style.textAlign = "center";
    <%--cell0.style.backgroundColor = ("#328aa4");--%>
            cell0.width="2%";

            var cell1 = row6.insertCell(1);
            var element1 = document.createElement("textarea");
            element1.t = "syorPengarah"+(count6)+(rowCount6+1);
            element1.rows = 5;
            element1.cols = 130;
            element1.name ="syorPengarah"+(count6)+(rowCount6+1);
            element1.id ="syorPengarah"+(count6)+(rowCount6+1);
    <%--element1.value ="syor"+(count)+(rowCount+1);--%>
            cell1.colSpan = 2;
            cell1.appendChild(element1);

            // Add hidden field
            var cell2 = row6.insertCell(2);
            var element2 = document.createElement ("input");
            element2.setAttribute("type", "hidden");
            element2.setAttribute("id", "count6"+(count6));
            element2.setAttribute("name", "count6"+(count6));
            element2.setAttribute("value", 1);
            cell2.appendChild(element2);

            // Add tambah button
            var cell3 = row6.insertCell(3);
            var button = document.createElement('input');
            var buttonName = "tambah6" +(count6);
            button.setAttribute('type','button');
            button.className = "btn";
            button.setAttribute('name',buttonName);
            button.setAttribute('value','Tambah 6.'+(count6));
            button.onclick=function(){addDynamicRow6(table6.id,element2.id);};
            cell3.appendChild(button);

            var button1 = document.createElement('input');
            var buttonName1 = "hapus6" +(count6);
            button1.setAttribute('type','button');
            button1.className = "btn";
            button1.setAttribute('name',buttonName1);
            button1.setAttribute('value','Hapus 6.'+(count6)+' sub');
            button1.onclick=function(){deleteDynamicRow6(table6.id,element2.id);};
            cell3.appendChild(button1);

            id1.appendChild(table6);
            id1.appendChild(document.createElement('br'));
        }

        function deleteTable6(ptg,ptPengambilanPtg) {
            var bil = 6;
            var temp6 = $("#temp6").val();
            var count6 = $("#count6").val();

            var table6 = document.getElementById("table6"+count6);
            var rowCount6 = table6.rows.length;
            for(var i=rowCount6-1;i>=0;i--){
                table6.deleteRow(i);
                document.getElementById ("count6").value = parseInt(count6)-1;
            }

            if(parseInt(count6) <= parseInt(temp6)) {

                var url = '${pageContext.request.contextPath}/pengambilan/ulasan_mmk_NS?deleteTable&bil='
                    +bil+'&ptg='+ptg+'&ptPengambilanPtg='+ptPengambilanPtg;

                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');

            }


        }

        function addDynamicRow6(tableID,countID) {

            var table6 = document.getElementById(tableID);
            var rowCount6 = table6.rows.length;
            var row6 = table6.insertRow(rowCount6);

            document.getElementById(countID).value = parseInt(document.getElementById(countID).value)+1;

            var count6 = parseInt(tableID.substring(6));

            var cell0 = row6.insertCell(0);
            cell0.innerHTML = "";

            var cell1 = row6.insertCell(1);
            var arr=['a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'];
            cell1.innerHTML = "<b>"+arr[rowCount6-1]+".</b>";

            var cell2 = row6.insertCell(2);
            var element2 = document.createElement("textarea");
            element2.t = "syorPengarah"+(count6)+(rowCount6+1);
            element2.rows = 5;
            element2.cols = 130;
            element2.name ="syorPengarah"+(count6)+(rowCount6+1);
            element2.id ="syorPengarah"+(count6)+(rowCount6+1);
    <%--element2.value ="syor"+(count)+(rowCount+1);--%>
            cell2.appendChild(element2);

        }

        function deleteDynamicRow6(tableID,countID,ptg, ptPengambilanPtg) {
            var table6 = document.getElementById(tableID);
            var rowCount6 = table6.rows.length;
            var i = tableID.substring(6);

            if(rowCount6 >1){
                var obj = document.getElementById("kand6"+(i)+(rowCount6));
                var idKandungan = $("#kand6"+(i)+(rowCount6)).val();
                table6.deleteRow(rowCount6-1);
                document.getElementById(countID).value = table6.rows.length;
                if (obj != null) {

                    var url = '${pageContext.request.contextPath}/pengambilan/ulasan_mmk_NS?deleteSingle&idKandungan='
                        +idKandungan+'&ptg='+ptg+'&ptPengambilanPtg='+ptPengambilanPtg;

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
            var count1=$("#rowCount4").val();
            for(var i=1;i<=count1;i++){
                var kandungan4= $("#kandungan4"+i).val();
                if(kandungan4 == ""){
                    alert('Sila pilih " 4. Huraian Pentadbir Tanah " terlebih dahulu.');
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
                        alert('Sila pilih " 5. Syor Pentadbir Tanah " terlebih dahulu.');
                        $("#syorPentadbir"+i+j).focus();
                        return false;
                    }
                }

            }

            var count6 = $("#count6").val();
            for(var i=1;i<=count6;i++){
                var recordCount = $("#count6"+i).val();
                for(var j=1;j<=recordCount;j++){
                    var syorPengarah = $("#syorPengarah"+i+j).val();
                    if(syorPengarah == ""){
                        alert('Sila pilih " 6. Huraian Pengarah Tanah Dan Galian Negeri " terlebih dahulu.');
                        $("#syorPengarah"+i+j).focus();
                        return false;
                    }
                }

            }

            if($("#kmno").val() == ""){
                $("#kmno").val("Tiada Data");
            }

            if($("#tujuan").val() == ""){
                $("#tujuan").val("Tiada Data");
            }

            if($("#huraianPtg").val() == ""){
                $("#huraianPtg").val("Tiada Data");
            }

            if($("#syorPentadbir").val() == ""){
                $("#syorPentadbir").val("Tiada Data");
            }

            return true;
        }

</script>

<s:form beanclass="etanah.view.stripes.pengambilan.UlasanMMKNSPendudukanSementaraActionBean" name="form2">
    <s:messages/>
    <s:errors/>
    <s:hidden name="count5" id="count5" value="${actionBean.count5}"/>
    <s:hidden name="temp5" id="temp5" value="${actionBean.count5}"/>
    <s:hidden name="count6" id="count6" value="${actionBean.count6}"/>
    <s:hidden name="temp6" id="temp6" value="${actionBean.count6}"/>
    <s:hidden name="kandunganK.kertas.idKertas"/>
    <s:hidden name="idKertas" value="${actionBean.permohonanKertas.idKertas}"/>

    <c:if test="${ptg}">
        <s:hidden name="ptg" value="${ptg}"/>
        <s:hidden name="ptPengambilanPtg" value="false"/>

        <div class="subtitle">
            <fieldset class="aras1">
                <div class="content" align="center">
                    <table border="0" width="80%">
                        <tr><td align="center"><b>KM No: <s:text name="kmno" id="kmno" size="12"/></b></td></tr>

                        <tr><td align="center"><b>(MAJLIS MESYUARAT KERAJAAN PADA <s:text name="tarikhMesyuarat" id="tarikhMesyuarat" class="datepicker" size="12" />)</b></td></tr>

                        <tr><td><b><s:hidden name="heading" />
                                    ${actionBean.heading}</b></td></tr>

                        <%--<tr><td><b></b></td></tr>--%>

                        <tr><td><b>1. TUJUAN</b></td></tr>
                        <tr>
                            <td>
                                <table width="100%">
                                    <tr><td >&nbsp;&nbsp;&nbsp;</td><td valign="top">1.1.&nbsp;</td><td><s:textarea rows="5" cols="160" name="tujuan" id="tujuan" class="normal_text"/></td></tr>
                                </table>
                            </td>
                        </tr>

                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b>2. BUTIR-BUTIR PEMOHON</b></td></tr>
                        <tr><td>
                                <display:table class="tablecloth" name="${actionBean.listPemohon}" cellpadding="0" cellspacing="0" id="line">
                                    <display:column title="Bil" sortable="true" style="vertical-align:baseline">${line_rowNum}
                                        <s:hidden name="x" class="x${line_rowNum -1}" value="${line.pihak.idPihak}"/>
                                    </display:column>
                                    <display:column property="pihak.nama" title="Nama" style="vertical-align:baseline"/>
                                    <display:column title="Alamat" style="vertical-align:baseline">${line.pihak.suratAlamat1}
                                        <c:if test="${line.pihak.suratAlamat2 ne null}"> , ${line.pihak.suratAlamat2} </c:if>
                                        <c:if test="${line.pihak.suratAlamat3 ne null}"> , ${line.pihak.suratAlamat3} </c:if>
                                        <c:if test="${line.pihak.suratAlamat4 ne null}"> , ${line.pihak.suratAlamat4} </c:if>
                                        ${line.pihak.suratPoskod}
                                        ${line.pihak.suratNegeri.nama}
                                    </display:column>
                                    <display:column title="Tarikh Permohonan" property="permohonan.infoAudit.tarikhMasuk" format="{0,date,dd/MM/yyyy}" style="vertical-align:baseline"/>
                                    <display:column title="No. Pendaftaran" style="vertical-align:baseline">
                                        <c:if test="${line.pihak.noPengenalan ne null}">${line.pihak.noPengenalan}</c:if>
                                        <c:if test="${line.pihak.noPengenalan eq null}">Tiada</c:if>
                                    </display:column>
                                </display:table>
                            </td></tr>

                        <tr><td><b>3. BUTIR-BUTIR HAKMILIK </b></td></tr>
                        <tr><td>
                                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0" id="tbl1">
                                    <display:column title="Bil" sortable="true" style="vertical-align:baseline">${tbl1_rowNum}</display:column>
                                    <display:column title="Daerah" property="hakmilik.daerah.nama" class="daerah" style="vertical-align:baseline"/>
                                    <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" style="vertical-align:baseline"/>
                                    <display:column title="Nombor Lot/PT" style="vertical-align:baseline">
                                        ${tbl1.hakmilik.lot.nama} ${tbl1.hakmilik.noLot}
                                    </display:column>
                                    <display:column title="Jenis & No Hakmilik" style="vertical-align:baseline">
                                        ${tbl1.hakmilik.kodHakmilik.kod} ${tbl1.hakmilik.noHakmilik}
                                    </display:column>
                                    <display:column title="Luas" style="vertical-align:baseline"><fmt:formatNumber pattern="#,##0.0000" value="${tbl1.hakmilik.luas}"/>&nbsp;${tbl1.hakmilik.kodUnitLuas.nama}</display:column>
                                    <display:column title="Luas Diperlukan" style="vertical-align:baseline"><fmt:formatNumber pattern="#,##0.0000" value="${tbl1.luasTerlibat}"/>&nbsp;${tbl1.kodUnitLuas.nama}</display:column>
                                    <display:column title="Tuan Tanah" style="vertical-align:baseline">
                                        <c:forEach items="${tbl1.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                                            <c:if test="${senarai.jenis.kod eq 'PM'}">
                                                <c:out value="${senarai.pihak.nama}"/>
                                            </c:if>
                                        </c:forEach>
                                    </display:column>
                                    <display:column title="Jenis Kegunaan" style="vertical-align:baseline">
                                        <c:if test="${tbl1.hakmilik.kegunaanTanah.nama ne null}">${tbl1.hakmilik.kegunaanTanah.nama}</c:if>
                                        <c:if test="${tbl1.hakmilik.kegunaanTanah.nama eq null}">Tiada</c:if>
                                    </display:column>
                                    <display:column title="Syarat Nyata" style="vertical-align:baseline">
                                        <c:if test="${tbl1.hakmilik.syaratNyata.syarat ne null}">${tbl1.hakmilik.syaratNyata.syarat}</c:if>
                                        <c:if test="${tbl1.hakmilik.syaratNyata.syarat eq null}">Tiada</c:if>
                                    </display:column>
                                    <display:column title="Sekatan Kepentingan" style="vertical-align:baseline">
                                        <c:if test="${tbl1.hakmilik.sekatanKepentingan.sekatan ne null}">${tbl1.hakmilik.sekatanKepentingan.sekatan}</c:if>
                                        <c:if test="${tbl1.hakmilik.sekatanKepentingan.sekatan eq null}">Tiada</c:if>
                                    </display:column>
                                </display:table>
                            </td></tr>

                        <tr><td><b><font style="text-transform: uppercase">4. HURAIAN PENTADBIR TANAH ${actionBean.hakmilik.daerah.nama}&nbsp;</font></b></td></tr>
                        <tr><td>
                                <table border="0" width="96%" id="dataTable4">
                                    <c:set var="i" value="1" />
                                    <c:forEach items="${actionBean.senaraiKertasKandungan4}" var="line">
                                        <tr style="font-weight:bold">
                                            <td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                            <td><s:textarea name="kandungan4${i}" id="kandungan4${i}" rows="5" cols="150" readonly="true" class="normal_text">${line.kandungan}</s:textarea></td>
                                        </tr>
                                        <s:hidden name="idKandungan4${i}" id="idKandungan4${i}" value="${line.idKandungan}" />
                                        <c:set var="i" value="${i+1}" />
                                    </c:forEach>
                                    <s:hidden name="rowCount4" value="${i-1}" id="rowCount4"/>
                                </table>
                            </td>
                        </tr>
                        <tr><td align="right"><s:button name="hurianPentadbir" value="Tambah" class="btn" onclick="addRow4('dataTable4')" disabled="true"/>
                                <s:button name="hurianPentadbir" value="Hapus" class="btn" onclick="deleteRow4('${ptg}','${ptPengambilanPtg}')" disabled="true"/>
                            </td>
                        </tr>

                        <tr><td><b><font style="text-transform: uppercase">5. SYOR PENTADBIR TANAH ${actionBean.hakmilik.daerah.nama}&nbsp;</font></b></td></tr>
                        <tr>
                            <td>
                                <table width="100%">
                                    <tr><td>&nbsp;&nbsp;&nbsp;</td><td valign="top">5.1.&nbsp;</td>
                                        <td><s:textarea name="syorPentadbir" id="syorPentadbir" rows="5" cols="160" class="normal_text" readonly="true"/></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>

                        <%--<tr><td><b><font style="text-transform: uppercase">5. SYOR PENTADBIR DAERAH&nbsp;</font></b></td></tr>
                        <tr><td>
                                <table border="0" width="96%" id="dataTable5">
                                    <c:set var="i" value="1" />
                                    <c:forEach items="${actionBean.senaraiKertasKandungan5}" var="line">
                                        <tr style="font-weight:bold">
                                            <td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                            <td><s:textarea name="kandungan5${i}" id="kandungan5${i}" rows="5" cols="150" readonly="true" class="normal_text">${line.kandungan}</s:textarea></td>
                                        </tr>
                                        <s:hidden name="idKandungan5${i}" id="idKandungan5${i}" value="${line.idKandungan}" />
                                        <c:set var="i" value="${i+1}" />
                                    </c:forEach>
                                    <s:hidden name="rowCount5" value="${i-1}" id="rowCount5"/>
                                </table>
                            </td>
                        </tr><br>
                        <tr><td align="right"><s:button name="syorPentadbir" value="Tambah" class="btn" onclick="addRow5('dataTable5')" disabled="true"/>
                                <s:button name="syorPentadbir" value="Hapus" class="btn" onclick="deleteRow5('${ptg}','${ptPengambilanPtg}')" disabled="true"/>
                            </td>
                        </tr>--%>

                        <%--<tr><td><b><font style="text-transform: uppercase">5. SYOR PENTADBIR DAERAH&nbsp;</font></b></td></tr>
                        <c:forEach var="i" begin="1" end="${actionBean.count5}">
                            <tr><td>
                                    <table  width="90%" id="table5${i}" >
                                        <c:set var="recordCount5" value="0"/>
                                        <c:forEach items="${actionBean.senaraiSyorPentadbir[i]}" var="senarai">
                                            <c:set var="recordCount5" value="${recordCount5+1}"/>
                                            <c:if test="${recordCount5 eq 1}">
                                                <tr> <td><b>5.${i}</b></td>
                                                    <td colspan="2"><s:textarea rows="5" cols="130" name="syorPentadbir${i}${recordCount5}" id="syorPentadbir${i}${recordCount5}" value="${senarai.kandungan}" readonly="true" class="normal_text"/></td>
                                                    <td><s:hidden name="count5${i}" id="count5${i}" value="${(fn:length(actionBean.senaraiSyorPentadbir[i]))}" /> </td>
                                                    <td><s:button class="btn" value="Tambah 5.${i}" name="add" id="tambah5${i}" onclick="addDynamicRow5('table5${i}','count5${i}')" disabled="true"/></td>
                                                    <td><s:button class="btn" value="Hapus 5.${i} sub" name="hapus" id="hapus5${i}" onclick="deleteDynamicRow5('table5${i}','count5${i}','${ptg}','${ptPengambilanPtg}')" disabled="true"/></td>
                                                </c:if>
                                                <c:if test="${recordCount5 ne 1}">
                                                <tr>  <td></td>
                                                    <td><b><c:out value="${actionBean.str[recordCount5-1]}"/>.</b></td>
                                                    <td><s:textarea rows="5" cols="130" name="syorPentadbir${i}${recordCount5}" id="syorPentadbir${i}${recordCount5}" value="${senarai.kandungan}" readonly="true" class="normal_text"/></td>
                                                </c:if>
                                                <s:hidden name="kand5${i}${recordCount5}" id="kand5${i}${recordCount5}" value="${senarai.idKandungan}" />
                                            </c:forEach>
                                    </table>
                                </td></tr>
                            </c:forEach>
                        <tr><td align="right">
                                <div id="Tables5">

                                </div>
                                <s:button class="btn" value="Tambah 5" name="add" onclick="addTable5('Tables5')" disabled="true"/>
                                <s:button class="btn" value="Hapus" name="add" onclick="deleteTable5('${ptg}','${ptPengambilanPtg}')" disabled="true" />
                            </td>
                        </tr>--%>

                        <tr><td><b>6. HURAIAN PENGARAH TANAH DAN GALIAN NEGERI</b></td></tr>
                        <tr><td>
                                <table border="0" width="96%" id="dataTable6">
                                    <c:set var="i" value="1" />
                                    <c:forEach items="${actionBean.senaraiKertasKandungan6}" var="line">
                                        <tr style="font-weight:bold">
                                            <td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                            <td><s:textarea name="kandungan6${i}" id="kandungan6${i}" rows="5" cols="150" class="normal_text">${line.kandungan}</s:textarea></td>
                                        </tr>
                                        <s:hidden name="idKandungan6${i}" id="idKandungan6${i}" value="${line.idKandungan}" />
                                        <c:set var="i" value="${i+1}" />
                                    </c:forEach>
                                    <s:hidden name="rowCount6" value="${i-1}" id="rowCount6"/>
                                </table>
                            </td>
                        </tr>
                        <tr><td align="right"><s:button name="huraianPengarah" value="Tambah" class="btn" onclick="addRow6('dataTable6')"/>
                                <s:button name="huraianPengarah" value="Hapus" class="btn" onclick="deleteRow6('${ptg}','${ptPengambilanPtg}')"/>
                            </td>
                        </tr>

                        <tr><td><b>7. SYOR PENGARAH TANAH DAN GALIAN NEGERI</b></td></tr>
                        <tr>
                            <td>
                                <table width="100%">
                                    <tr><td>&nbsp;&nbsp;&nbsp;</td><td valign="top">7.1.&nbsp;</td>
                                        <td><s:textarea name="syorPengarah" id="syorPengarah" rows="5" cols="160" class="normal_text"/></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>

                        <tr><td><b>8. KEPUTUSAN</b></td></tr>
                        <tr><td width="100%">8.1 Dibentangkan untuk pertimbangan dan keputusan Majlis Mesyuarat Kerajaan Negeri.</td></tr><br>
                    </table>

                    <br>

                    <p align="center">
                        <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>
                    </p>

                </div>
            </fieldset>
        </div>

    </c:if>

    <c:if test="${ptPengambilanPtg}">
        <s:hidden name="ptPengambilanPtg" value="${ptPengambilanPtg}"/>
        <s:hidden name="ptg" value="false"/>

        <div class="subtitle">
            <fieldset class="aras1">
                <div class="content" align="center">
                    <table border="0" width="70%">
                        <tr><td align="center"><b>KM No: <s:text name="kmno" id="kmno" size="12" readonly="true" disabled="true"/></b></td></tr>

                        <tr><td align="center"><b>(MAJLIS MESYUARAT KERAJAAN PADA <s:text name="tarikhMesyuarat" id="tarikhMesyuarat" class="datepicker" size="12" disabled="true"/>)</b></td></tr>

                        <tr><td><b><s:hidden name="heading" />
                                    ${actionBean.heading}</b></td></tr>

                        <tr><td></td></tr>

                        <tr><td><b>1. TUJUAN</b></td></tr>
                        <tr>
                            <td>
                                <table width="100%">
                                    <tr><td >&nbsp;&nbsp;&nbsp;</td><td valign="top">1.1.&nbsp;</td><td><s:textarea rows="5" cols="160" name="tujuan" id="tujuan" class="normal_text"/></td></tr>
                                </table>
                            </td>
                        </tr>

                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b>2. BUTIR-BUTIR PEMOHON</b></td></tr>
                        <tr><td>
                                <display:table class="tablecloth" name="${actionBean.listPemohon}" cellpadding="0" cellspacing="0" id="line">
                                    <display:column title="Bil" sortable="true" style="vertical-align:baseline">${line_rowNum}
                                        <s:hidden name="x" class="x${line_rowNum -1}" value="${line.pihak.idPihak}"/>
                                    </display:column>
                                    <display:column property="pihak.nama" title="Nama" style="vertical-align:baseline"/>
                                    <display:column title="Alamat" style="vertical-align:baseline">${line.pihak.suratAlamat1}
                                        <c:if test="${line.pihak.suratAlamat2 ne null}"> , ${line.pihak.suratAlamat2} </c:if>
                                        <c:if test="${line.pihak.suratAlamat3 ne null}"> , ${line.pihak.suratAlamat3} </c:if>
                                        <c:if test="${line.pihak.suratAlamat4 ne null}"> , ${line.pihak.suratAlamat4} </c:if>
                                        ${line.pihak.suratPoskod}
                                        ${line.pihak.suratNegeri.nama}
                                    </display:column>
                                    <display:column title="Tarikh Permohonan" property="permohonan.infoAudit.tarikhMasuk" format="{0,date,dd/MM/yyyy}" style="vertical-align:baseline"/>
                                    <display:column title="No. Pendaftaran" style="vertical-align:baseline">
                                        <c:if test="${line.pihak.noPengenalan ne null}">${line.pihak.noPengenalan}</c:if>
                                        <c:if test="${line.pihak.noPengenalan eq null}">Tiada</c:if>
                                    </display:column>
                                </display:table>
                            </td></tr>

                        <tr><td><b>3. BUTIR-BUTIR HAKMILIK </b></td></tr>
                        <tr><td>
                                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0" id="tbl1">
                                    <display:column title="Bil" sortable="true" style="vertical-align:baseline">${tbl1_rowNum}</display:column>
                                    <display:column title="Daerah" property="hakmilik.daerah.nama" class="daerah" style="vertical-align:baseline"/>
                                    <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" style="vertical-align:baseline"/>
                                    <display:column title="Nombor Lot/PT" style="vertical-align:baseline">
                                        ${tbl1.hakmilik.lot.nama} ${tbl1.hakmilik.noLot}
                                    </display:column>
                                    <display:column title="Jenis & No Hakmilik" style="vertical-align:baseline">
                                         ${tbl1.hakmilik.kodHakmilik.kod} ${tbl1.hakmilik.noHakmilik}
                                    </display:column>
                                    <%--<display:column title="Tempoh Hakmilik" style="vertical-align:baseline" property="hakmilik.tempohPegangan">
                                        <c:if test="${tbl1.hakmilik.tempohPegangan ne null}">${tbl1.hakmilik.tempohPegangan}</c:if>
                                        <c:if test="${tbl1.hakmilik.tempohPegangan eq null}">Tiada</c:if>
                                    </display:column>--%>
                                    <display:column title="Luas" style="vertical-align:baseline"><fmt:formatNumber pattern="#,##0.0000" value="${tbl1.hakmilik.luas}"/>&nbsp;${tbl1.hakmilik.kodUnitLuas.nama}</display:column>
                                    <display:column title="Luas Diperlukan" style="vertical-align:baseline"><fmt:formatNumber pattern="#,##0.0000" value="${tbl1.luasTerlibat}"/>&nbsp;${tbl1.kodUnitLuas.nama}</display:column>
                                    <display:column title="Tuan Tanah" style="vertical-align:baseline">
                                        <c:forEach items="${tbl1.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                                            <c:if test="${senarai.jenis.kod eq 'PM'}">
                                                <c:out value="${senarai.pihak.nama}"/>
                                            </c:if>
                                        </c:forEach>
                                    </display:column>
                                    <display:column title="Jenis Kegunaan" style="vertical-align:baseline">
                                        <c:if test="${tbl1.hakmilik.kegunaanTanah.nama ne null}">${tbl1.hakmilik.kegunaanTanah.nama}</c:if>
                                        <c:if test="${tbl1.hakmilik.kegunaanTanah.nama eq null}">Tiada</c:if>
                                    </display:column>
                                    <display:column title="Syarat Nyata" style="vertical-align:baseline">
                                        <c:if test="${tbl1.hakmilik.syaratNyata.syarat ne null}">${tbl1.hakmilik.syaratNyata.syarat}</c:if>
                                        <c:if test="${tbl1.hakmilik.syaratNyata.syarat eq null}">Tiada</c:if>
                                    </display:column>
                                    <display:column title="Sekatan Kepentingan" style="vertical-align:baseline">
                                        <c:if test="${tbl1.hakmilik.sekatanKepentingan.sekatan ne null}">${tbl1.hakmilik.sekatanKepentingan.sekatan}</c:if>
                                        <c:if test="${tbl1.hakmilik.sekatanKepentingan.sekatan eq null}">Tiada</c:if>
                                    </display:column>
                                </display:table>
                            </td></tr>

                        <tr><td><b><font style="text-transform: uppercase">4. HURAIAN PENTADBIR TANAH ${actionBean.hakmilik.daerah.nama}&nbsp;</font></b></td></tr>
                        <tr><td>
                                <table border="0" width="96%" id="dataTable4">
                                    <c:set var="i" value="1" />
                                    <c:forEach items="${actionBean.senaraiKertasKandungan4}" var="line">
                                        <tr style="font-weight:bold">
                                            <td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                            <td><s:textarea name="kandungan4${i}" id="kandungan4${i}" rows="5" cols="150" class="normal_text">${line.kandungan}</s:textarea></td>
                                        </tr>
                                        <s:hidden name="idKandungan4${i}" id="idKandungan4${i}" value="${line.idKandungan}" />
                                        <c:set var="i" value="${i+1}" />
                                    </c:forEach>
                                    <s:hidden name="rowCount4" value="${i-1}" id="rowCount4"/>
                                </table>
                            </td>
                        </tr>
                        <tr><td align="right"><s:button name="hurianPentadbir" value="Tambah" class="btn" onclick="addRow4('dataTable4')" />
                                <s:button name="hurianPentadbir" value="Hapus" class="btn" onclick="deleteRow4('${ptg}','${ptPengambilanPtg}')" />
                            </td>
                        </tr>

                        <tr><td><b><font style="text-transform: uppercase">5. SYOR PENTADBIR TANAH ${actionBean.hakmilik.daerah.nama}&nbsp;</font></b></td></tr>
                        <tr>
                            <td>
                                <table width="100%">
                                    <tr><td>&nbsp;&nbsp;&nbsp;</td><td valign="top">5.1.&nbsp;</td>
                                        <td><s:textarea name="syorPentadbir" id="syorPentadbir" rows="5" cols="160" class="normal_text"/></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>

                        <%--<tr><td><b><font style="text-transform: uppercase">5. SYOR PENTADBIR DAERAH&nbsp;</font></b></td></tr>
                        <tr><td>
                                <table border="0" width="96%" id="dataTable5">
                                    <c:set var="i" value="1" />
                                    <c:forEach items="${actionBean.senaraiKertasKandungan5}" var="line">
                                        <tr style="font-weight:bold">
                                            <td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                            <td><s:textarea name="kandungan5${i}" id="kandungan5${i}" rows="5" cols="150" class="normal_text">${line.kandungan}</s:textarea></td>
                                        </tr>
                                        <s:hidden name="idKandungan5${i}" id="idKandungan5${i}" value="${line.idKandungan}" />
                                        <c:set var="i" value="${i+1}" />
                                    </c:forEach>
                                    <s:hidden name="rowCount5" value="${i-1}" id="rowCount5"/>
                                </table>
                            </td>
                        </tr>
                        <tr><td align="right"><s:button name="syorPentadbir" value="Tambah" class="btn" onclick="addRow5('dataTable5')" />
                                <s:button name="syorPentadbir" value="Hapus" class="btn" onclick="deleteRow5('${ptg}','${ptPengambilanPtg}')" />
                            </td>
                        </tr>--%>

                        <%--<tr><td><b><font style="text-transform: uppercase">5. SYOR PENTADBIR DAERAH&nbsp;</font></b></td></tr>
                        <c:forEach var="i" begin="1" end="${actionBean.count5}">
                            <tr><td>
                                    <table  width="90%" id="table5${i}" >
                                        <c:set var="recordCount5" value="0"/>
                                        <c:forEach items="${actionBean.senaraiSyorPentadbir[i]}" var="senarai">
                                            <c:set var="recordCount5" value="${recordCount5+1}"/>
                                            <c:if test="${recordCount5 eq 1}">
                                                <tr> <td><b>5.${i}</b></td>
                                                    <td colspan="2"><s:textarea rows="5" cols="130" name="syorPentadbir${i}${recordCount5}" id="syorPentadbir${i}${recordCount5}" value="${senarai.kandungan}" class="normal_text"/></td>
                                                    <td><s:hidden name="count5${i}" id="count5${i}" value="${(fn:length(actionBean.senaraiSyorPentadbir[i]))}" /> </td>
                                                    <td><s:button class="btn" value="Tambah 5.${i}" name="add" id="tambah5${i}" onclick="addDynamicRow5('table5${i}','count5${i}')"/></td>
                                                    <td><s:button class="btn" value="Hapus 5.${i} sub" name="hapus" id="hapus5${i}" onclick="deleteDynamicRow5('table5${i}','count5${i}','${ptg}','${ptPengambilanPtg}')"/></td>
                                                </c:if>
                                                <c:if test="${recordCount5 ne 1}">
                                                <tr>  <td></td>
                                                    <td><b><c:out value="${actionBean.str[recordCount5-1]}"/>.</b></td>
                                                    <td><s:textarea rows="5" cols="130" name="syorPentadbir${i}${recordCount5}" id="syorPentadbir${i}${recordCount5}" value="${senarai.kandungan}" class="normal_text"/></td>
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
                                <s:button class="btn" value="Hapus" name="add" onclick="deleteTable5('${ptg}','${ptPengambilanPtg}')"/>
                            </td>
                        </tr>--%>

                        <tr><td><b>6. HURAIAN PENGARAH TANAH DAN GALIAN NEGERI</b></td></tr>
                        <tr><td>
                                <table border="0" width="96%" id="dataTable6">
                                    <c:set var="i" value="1" />
                                    <c:forEach items="${actionBean.senaraiKertasKandungan6}" var="line">
                                        <tr style="font-weight:bold">
                                            <td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                            <td><s:textarea name="kandungan6${i}" id="kandungan6${i}" rows="5" cols="150" class="normal_text">${line.kandungan}</s:textarea></td>
                                        </tr>
                                        <s:hidden name="idKandungan6${i}" id="idKandungan6${i}" value="${line.idKandungan}" />
                                        <c:set var="i" value="${i+1}" />
                                    </c:forEach>
                                    <s:hidden name="rowCount6" value="${i-1}" id="rowCount6"/>
                                </table>
                            </td>
                        </tr>
                        <tr><td align="right"><s:button name="huraianPengarah" value="Tambah" class="btn" onclick="addRow6('dataTable6')" disabled="true"/>
                                <s:button name="huraianPengarah" value="Hapus" class="btn" onclick="deleteRow6('${ptg}','${ptPengambilanPtg}')" disabled="true"/>
                            </td>
                        </tr>

                        <tr><td><b>7. SYOR PENGARAH TANAH DAN GALIAN NEGERI</b></td></tr>
                        <tr>
                            <td>
                                <table width="100%">
                                    <tr><td>&nbsp;&nbsp;&nbsp;</td><td valign="top">7.1.&nbsp;</td>
                                        <td><s:textarea name="syorPengarah" id="syorPengarah" rows="5" cols="160" disabled="true" class="normal_text"/></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>

                        <tr><td><b>8. KEPUTUSAN</b></td></tr>
                        <tr><td width="100%">8.1 Dibentangkan untuk pertimbangan dan keputusan Majlis Mesyuarat Kerajaan Negeri.</td></tr><br>
                    </table>

                    <br>
                    <p align="center">
                        <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>
                    </p>
                </div>
            </fieldset>
        </div>
    </c:if>
</s:form>
