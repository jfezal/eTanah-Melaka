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
        document.form2.rowCount3.value = 1;
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
        document.form2.rowCount3.value=rowCount3 +1;
    }

    function deleteRow3()
    {
        var i = document.form2.rowCount3.value;
        var x= document.getElementById('dataTable3').rows[i-1].cells;
        var idKandungan = x[0].innerHTML;

        document.getElementById('dataTable3').deleteRow(i-1);
        document.form2.rowCount3.value = i -1;

        var url = '${pageContext.request.contextPath}/pengambilan/borang_mmkn_31a_ns?deleteSingle&idKandungan='
            +idKandungan;
         
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function addRow4(tableID4) {
        document.form2.rowCount4.value = 1;
        var table = document.getElementById(tableID4);

        var rowCount4 = table.rows.length;
        var row = table.insertRow(rowCount4);

        var cell2 = row.insertCell(0);
        cell2.innerHTML = "<b>"+"4." +((rowCount4/2)+1)+"</b>";

        var cell1 = row.insertCell(1);
        var selectbox = document.createElement("SELECT");
        selectbox.setAttribute("name", "kandungan4"+(rowCount4+1));

        var optn1 = document.createElement("OPTION");
        optn1.text = "Sila Pilih";
        optn1.value = " ";
        selectbox.options.add(optn1);

        var optn2 = document.createElement("OPTION");
        optn2.text = "Jabatan Kebajikan Masyarakat";
        optn2.value = "Jabatan Kebajikan Masyarakat";
        selectbox.options.add(optn2);

        var optn3 = document.createElement("OPTION");
        optn3.text = "Jabatan Kerja Raya";
        optn3.value = "Jabatan Kerja Raya";
        selectbox.options.add(optn3);

        var optn4 = document.createElement("OPTION");
        optn4.text = "Jabatan Pengairan dan Saliran";
        optn4.value = "Jabatan Pengairan dan Saliran";
        selectbox.options.add(optn4);

        var optn5 = document.createElement("OPTION");
        optn5.text = "Jabatan Perancang Bandar dan Desa";
        optn5.value = "Jabatan Perancang Bandar dan Desa";
        selectbox.options.add(optn5);
        cell1.appendChild(selectbox);


        var rowCount4 = table.rows.length;
        var row = table.insertRow(rowCount4);

         var cell3 = row.insertCell(0);
         cell3.innerHTML = " ";

        var cell4 = row.insertCell(1);

        var element1 = document.createElement("textarea");
        element1.t = "kandungan4"+(rowCount4+1);
        element1.rows = 5;
        element1.cols = 150;
        element1.name ="kandungan4"+(rowCount4+1);
        element1.id ="kandungan4"+(rowCount4+1);
        cell4.appendChild(element1);
        document.form2.rowCount4.value=rowCount4 +1;
    }
    function deleteRow4()
    {
        var k = document.form2.rowCount4.value;
        var x= document.getElementById('dataTable4').rows[k-1].cells;
        var y= document.getElementById('dataTable4').rows[k-2].cells;
        var idKandungan1 = x[0].innerHTML;
        var idKandungan2 = y[0].innerHTML;

        document.getElementById('dataTable4').deleteRow(k-1);
        document.getElementById('dataTable4').deleteRow(k-2);
        document.form2.rowCount4.value = k -2;

        if (document.getElementById('idKandungan4'+(k)) != null) {
            var url = '${pageContext.request.contextPath}/pengambilan/borang_mmkn_31a_ns?deleteJabatanTeknikal&idKandungan1='
            +idKandungan1+'&idKandungan2='+idKandungan2;

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
    function deleteRow5()
    {
        var j = document.form2.rowCount5.value;
        var x= document.getElementById('dataTable5').rows[j-1].cells;
        var idKandungan = x[0].innerHTML;

        document.getElementById('dataTable5').deleteRow(j-1);
        document.form2.rowCount5.value = j -1;

        var url = '${pageContext.request.contextPath}/pengambilan/borang_mmkn_31a_ns?deleteSingle&idKandungan='
            +idKandungan;
        
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    


    function addRow7(tableID4) {
        document.form2.rowCount7.value = 1;
        var table = document.getElementById(tableID4);

        var rowCount7 = table.rows.length;
        var row = table.insertRow(rowCount7);

        var cell2 = row.insertCell(0);
        cell2.innerHTML = "<b>"+"7." +(rowCount7+1)+"</b>";

        var cell1 = row.insertCell(1);
        var element1 = document.createElement("textarea");
        element1.t = "kandungan7"+(rowCount7+1);
        element1.rows = 5;
        element1.cols = 150;
        element1.name ="kandungan7"+(rowCount7+1);
        element1.id ="kandungan7"+(rowCount7+1);
        cell1.appendChild(element1);
        //alert(element1.name)
        document.form2.rowCount7.value=rowCount7 +1;
    }
    function deleteRow7()
    {
        var k = document.form2.rowCount7.value;
        var x= document.getElementById('dataTable7').rows[k-1].cells;
        var idKandungan = x[0].innerHTML;

        document.getElementById('dataTable7').deleteRow(k-1);
        document.form2.rowCount7.value = k -1;

        var url = '${pageContext.request.contextPath}/pengambilan/borang_mmkn_31a_ns?deleteSingle&idKandungan='
            +idKandungan;
        
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

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
        element1.t = "syorPentadbir"+(count6)+(rowCount5+1);
        element1.rows = 5;
        element1.cols = 130;
        element1.name ="syorPentadbir"+(count6)+(rowCount5+1);
        element1.id ="syorPentadbir"+(count6)+(rowCount5+1);
        <%--element1.value ="syorPentadbir"+(count5)+(rowCount5+1);--%>
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
        var temp6 = $("#temp6").val();
        var count6 = $("#count6").val();

         var table6 = document.getElementById("table6"+count6);
             var rowCount7 = table6.rows.length;
             for(var i=rowCount7-1;i>=0;i--){
                table6.deleteRow(i);
                document.getElementById ("count6").value = parseInt(count6)-1;
             }

        if(parseInt(count6) <= parseInt(temp6)) {

            var url = '${pageContext.request.contextPath}/pengambilan/borang_mmkn_31a_ns?deleteTable&bil='
            +bil;

        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');

      }


    }

     function addDynamicRow6(tableID,countID) {

        var table6 = document.getElementById(tableID);
        var rowCount7 = table6.rows.length;
        var row6 = table6.insertRow(rowCount7);

        document.getElementById(countID).value = parseInt(document.getElementById(countID).value)+1;

        var count6 = parseInt(tableID.substring(6));

        var cell0 = row6.insertCell(0);
         cell0.innerHTML = "";

         var cell1 = row6.insertCell(1);
          var arr=['a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'];
         cell1.innerHTML = "<b>"+arr[rowCount7-1]+".</b>";

        var cell2 = row6.insertCell(2);
        var element2 = document.createElement("textarea");
        element2.t = "syorPentadbir"+(count6)+(rowCount7+1);
        element2.rows = 5;
        element2.cols = 130;
        element2.name ="syorPentadbir"+(count6)+(rowCount7+1);
        element2.id ="syorPentadbir"+(count6)+(rowCount7+1);
        <%--element2.value ="syorPentadbir"+(count6)+(rowCount7+1);--%>
        cell2.appendChild(element2);

    }

    function deleteDynamicRow6(tableID,countID) {
        var table6 = document.getElementById(tableID);
        var rowCount7 = table6.rows.length;
        var i = tableID.substring(6);

        if(rowCount7 >1){
            var obj = document.getElementById("kand6"+(i)+(rowCount7));
            var idKandungan = $("#kand6"+(i)+(rowCount7)).val();
            table6.deleteRow(rowCount7-1);
            document.getElementById(countID).value = table6.rows.length;
            if (obj != null) {

                var url = '${pageContext.request.contextPath}/pengambilan/borang_mmkn_31a_ns?deleteSingle&idKandungan='
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
        <%--cell0.style.backgroundColor = ("#328aa4");--%>
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

            var url = '${pageContext.request.contextPath}/pengambilan/borang_mmkn_31a_ns?deleteTable&bil='
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

                var url = '${pageContext.request.contextPath}/pengambilan/borang_mmkn_31a_ns?deleteSingle&idKandungan='
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

    if($("#kertasBil").val() == ""){
            $("#kertasBil").val("Tiada Data");
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

    var count4=$("#rowCount4").val();
    for(var i=1;i<=count4;i++){
        var kandungan4= $("#kandungan4"+i).val();
        if(kandungan4 == ""){
            alert('Sila pilih " 4. ULASAN JABATAN TEKNIKAL " terlebih dahulu.');
            $("#kandungan4"+i).focus();
            return false;
        }
    }

    var count2=$("#rowCount5").val();
    for(var i=1;i<=count2;i++){
        var kandungan5= $("#kandungan5"+i).val();
        if(kandungan5 == ""){
            alert('Sila pilih " 5. NILAIAN TANAH DAN PERUNTUKAN " terlebih dahulu.');
            $("#kandungan5"+i).focus();
            return false;
        }
    }

    var count6 = $("#count6").val();
    for(var i=1;i<=count6;i++){
        var recordCount6 = $("#count6"+i).val();
        for(var j=1;j<=recordCount6;j++){
            var syorPentadbir = $("#syorPentadbir"+i+j).val();
            if(syorPentadbir == ""){
                alert('Sila pilih " 6. SYOR PENTADBIR DAERAH " terlebih dahulu.');
                $("#syorPentadbir"+i+j).focus();
                return false;
            }
        }

    }

    var count3=$("#rowCount7").val();
    for(var i=1;i<=count3;i++){
        var kandungan7= $("#kandungan7"+i).val();
        if(kandungan7 == ""){
            alert('Sila pilih " 7. ULASAN PENGARAH TANAH DAN GALIAN " terlebih dahulu.');
            $("#kandungan7"+i).focus();
            return false;
        }
    }

    var count8 = $("#count8").val();
    for(var i=1;i<=count8;i++){
        var recordCount = $("#count8"+i).val();
        for(var j=1;j<=recordCount;j++){
            var syorPengarah = $("#syorPengarah"+i+j).val();
            if(syorPengarah == ""){
                alert('Sila pilih " 8. SYOR PENGARAH TANAH DAN GALIAN " terlebih dahulu.');
                $("#syorPengarah"+i+j).focus();
                return false;
            }
        }

    }

    return true;

    }
</script>

<s:form beanclass="etanah.view.stripes.pengambilan.PengambilanMMKN31aNSActionBean" name="form2">
    <s:messages/>
    <s:errors/>
    <s:hidden name="count6" id="count6" value="${actionBean.count6}"/>
    <s:hidden name="temp6" id="temp6" value="${actionBean.count6}"/>
    <s:hidden name="count8" id="count8" value="${actionBean.count8}"/>
    <s:hidden name="temp8" id="temp8" value="${actionBean.count8}"/>
    <c:if test="${form}">
        <div class="subtitle">
            <fieldset class="aras1" id="locate">
                <legend></legend>
                <div class="content" align="center">
                    <table border="0" width="80%">

                        <div class="content" align="left">
                            <tr align="left"><td align="center">
                                    <u><b>KERTAS PERTIMBANGAN MENTERI BESAR</b></u><br /></td></tr>
                            <tr><td> &nbsp;</td></tr>
                            <tr><td align="left"><b><label><font color="black">KERTAS BIL<font color="red">*</font> :</font></label><s:text name="kertasBil.kandungan"  size="12" style="text-align:left" id="kertasBil"/>/${actionBean.kertasTahun.kandungan}<s:hidden name="kertasTahun.kandungan" value="${actionBean.kertasTahun.kandungan}"/></b></td></tr>
                            <%--<tr><td align="left"><b><label><font color="black">TARIKH<font color="red">*</font> :</font></label><s:text name="tarikhmesyuarat.kandungan" id="datepicker" class="datepicker" size="12" style="text-align:left"/></b></td></tr><br>--%>
                            
                        </div>

                        <tr><td><br/><b>1. <%--<font style="text-transform: uppercase">PERMOHONAN PENGAMBILAN TANAH DI ATAS LOT
                                        <c:forEach items="${actionBean.hakmilikPermohonanList}" var="senaraiHakmilik" >
                                            <c:out value="${senaraiHakmilik.hakmilik.noLot}"/>,
                                        </c:forEach>

                                           SELUAS ${actionBean.hakmilik.luas} ${actionBean.hakmilik.kodUnitLuas.nama} , DI ${actionBean.hakmilik.bandarPekanMukim.nama}, DAERAH ${actionBean.hakmilik.daerah.nama}, NEGERI SEMBILAN UNTUK TUJUAN RIZAB PARIT, DI BAWAH SEKSYEN 3(1)(a) AKTA PENGAMBILAN TANAH 1960.</font>--%>
                        <s:hidden name="heading" />
                        ${actionBean.heading}</b><br /><br /></td></tr>

                        <tr><td><b>2. TUJUAN</b></td></tr>
                        <tr><tr><tr><td colspan="2"><b> &nbsp;</b><font ><s:textarea rows="5" cols="140" name="tujuan"/></font><td>

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
                                            <c:set var="i" value="${i+1}" />
                                        </c:forEach>
                                        <s:hidden name="rowCount3" value="${i-1}" id="rowCount3"/>
                                </table>
                        <tr><td align="right"><s:button name="perihalPermohonan" value="Tambah" class="btn" onclick="addRow3('dataTable3')" />
                                <s:button name="perihalPermohonan" value="Hapus" class="btn" onclick="deleteRow3()" />
                            </td>
                        </tr>

                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b>4. ULASAN JABATAN TEKNIKAL</b></td></tr>
                        <tr><td>
                                <table id ="dataTable4">
                                    <c:set var="k" value="1" />
                                    <c:set var="p" value="1" />
                                    <c:forEach items="${actionBean.senaraiKertasKandungan4}" var="line">
                                        <c:if test="${k%2 ne 0}">
                                            <tr style="font-weight:bold">
                                                <td style="display:none">${line.idKandungan}</td><td>4.<c:out value="${p}"/></td>
                                                <td><s:select name="kandungan4${k}" style="width:400px" id="kandungan4${k}" value="${line.kandungan}">
                                                        <s:option value="">Sila Pilih</s:option>
                                                        <s:option value="Jabatan Kebajikan Masyarakat">Jabatan Kebajikan Masyarakat</s:option>
                                                        <s:option value="Jabatan Kerja Raya">Jabatan Kerja Raya</s:option>
                                                        <s:option value="Jabatan Pengairan dan Saliran">Jabatan Pengairan dan Saliran</s:option>
                                                        <s:option value="Jabatan Perancang Bandar dan Desa">Jabatan Perancang Bandar dan Desa</s:option>
                                                    </s:select> </td></tr>
                                            <c:set var="p" value="${p+1}" />
                                            <s:hidden name="idKandungan4${k}" id="idKandungan4${k}" value="${line.idKandungan}" />
                                        </c:if>
                                        <c:if test="${k%2 eq 0}">
                                                <tr><td style="display:none">${line.idKandungan}</td><td></td>
                                                    <td><font style="text-transform:uppercase">
                                                            <s:textarea name="kandungan4${k}"  id="kandungan4${k}" rows="5" cols="150" onkeyup="this.value=this.value.toUpperCase();">${line.kandungan}</s:textarea></font></td>
                                                </tr>
                                                <s:hidden name="idKandungan4${k}" id="idKandungan4${k}" value="${line.idKandungan}" />
                                        </c:if>
                                        <c:set var="k" value="${k+1}" />
                                    </c:forEach>
                                </table>
                        <tr><td>
                                <s:hidden name="rowCount4" value="${k-1}" id="rowCount4"/>
                            </td></tr>
                        <tr><td align="right">
                                <s:button name="jabatanTeknikal" value="Tambah" class="btn" onclick="addRow4('dataTable4')" />
                                <s:button name="jabatanTeknikal" value="Hapus" class="btn" onclick="deleteRow4()" />


                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b>5. NILAIAN TANAH DAN PERUNTUKAN</b></td></tr>
                        <tr><td>
                                <table id ="dataTable5">
                                    <c:set var="i" value="1" /><c:forEach items="${actionBean.senaraiKertasKandungan5}" var="line">
                                        <tr style="font-weight:bold">
                                            <td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                            <td><font style="text-transform: uppercase"><s:textarea name="kandungan5${i}" id="kandungan5${i}" rows="5" cols="150" onkeyup="this.value=this.value.toUpperCase();">${line.kandungan}</s:textarea></font></td>
                                        </tr>
                                            <c:set var="i" value="${i+1}" />
                                        </c:forEach>
                                        <s:hidden name="rowCount5" value="${i-1}" id="rowCount5"/>
                                </table>
                        <tr><td align="right"><s:button name="nilaianTanah" value="Tambah" class="btn" onclick="addRow5('dataTable5')" />
                                <s:button name="nilaianTanah" value="Hapus" class="btn" onclick="deleteRow5()" />
                            </td>
                        </tr>

                        
                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b><font style="text-transform:uppercase">6. SYOR PENTADBIR TANAH DAERAH ${actionBean.hakmilik.daerah.nama}</font></b></td></tr>
                       
                        <c:forEach var="i" begin="1" end="${actionBean.count6}">
                            <tr><td>
                                    <table  width="90%" id="table6${i}" >
                                        <c:set var="recordCount6" value="0"/>
                                        <c:forEach items="${actionBean.senaraiSyorPentadbir[i]}" var="senarai">
                                            <c:set var="recordCount6" value="${recordCount6+1}"/>
                                            <c:if test="${recordCount6 eq 1}">
                                                <tr> <td><b>6.${i}</b></td>
                                                    <td colspan="2"><s:textarea rows="5" cols="130" name="syorPentadbir${i}${recordCount6}" id="syorPentadbir${i}${recordCount6}" value="${senarai.kandungan}" /></td>
                                                    <td><s:hidden name="count6${i}" id="count6${i}" value="${(fn:length(actionBean.senaraiSyorPentadbir[i]))}" /> </td>
                                                    <td><s:button class="btn" value="Tambah 6.${i}" name="add" id="tambah6${i}" onclick="addDynamicRow6('table6${i}','count6${i}')"/></td>
                                                    <td><s:button class="btn" value="Hapus 6.${i}" name="hapus" id="hapus6${i}" onclick="deleteDynamicRow6('table6${i}','count6${i}')"/></td>
                                            </c:if>
                                            <c:if test="${recordCount6 ne 1}">
                                            <tr>  <td></td>
                                                <td><b><c:out value="${actionBean.str[recordCount6-1]}"/>.</b></td>
                                                <td><s:textarea rows="5" cols="130" name="syorPentadbir${i}${recordCount6}" id="syorPentadbir${i}${recordCount6}" value="${senarai.kandungan}" /></td>
                                            </c:if>
                                            <s:hidden name="kand6${i}${recordCount6}" id="kand6${i}${recordCount6}" value="${senarai.idKandungan}" />
                                        </c:forEach>
                                    </table>
                                </td></tr>

                        </c:forEach>
                            <tr><td align="right">
                                    <div id="Tables6">

                                    </div>
                                    <s:button class="btn" value="Tambah" name="add" onclick="addTable6('Tables6')"/>
                                    <s:button class="btn" value="Hapus" name="add" onclick="deleteTable6()"/>

                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b><font style="text-transform:uppercase">7. ULASAN PENGARAH TANAH DAN GALIAN,NEGERI SEMBILAN</font></b></td></tr>
                        <tr><td>
                                <table id ="dataTable7">
                                    <c:set var="k" value="1" />
                                      <c:forEach items="${actionBean.senaraiKertasKandungan7}" var="line">
                                        <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                            <td><font style="text-transform:uppercase"><s:textarea name="kandungan7${k}" id="kandungan7${k}" rows="5" cols="150" onkeyup="this.value=this.value.toUpperCase();">${line.kandungan}</s:textarea></font>
                                            </td></tr>
                                            <c:set var="k" value="${k+1}" />
                                        </c:forEach>
                                        <s:hidden name="rowCount7" value="${k-1}" id="rowCount7"/>
                                </table>
                        <tr><td align="right"><s:button name="ulasanPengarahTanah" value="Tambah" class="btn" onclick="addRow7('dataTable7')" />
                                <s:button name="ulasanPengarahTanah" value="Hapus" class="btn" onclick="deleteRow7()" />

                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b><font style="text-transform:uppercase">8. SYOR PENGARAH TANAH DAN GALIAN(SYARAT-SYARAT KELULUSAN),NEGERI SEMBILAN</font></b></td></tr>
                        
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
                        <tr><td><b>9. KEPUTUSAN</b></td></tr>
                        <tr>
                            <td width="100%"><b>9.1</b> Dikemukan kertas Bil. <b>${actionBean.kertasBil.kandungan}/2009 </b>permohonan pengambilan tanah seluas lebih 
                                kurang <fmt:formatNumber pattern="#,##0.0000" value="${actionBean.totalLuas}"/>&nbsp;Meter Persegi daripada Judual Borang C di
                                ${actionBean.noLotList} daerah ${actionBean.hakmilik.daerah.nama} untuk tujuan ${actionBean.permohonan.sebab} dibawah Seksyen 831a Akta Pengambilan Tanah 1960 serperti bertanda merah dalam pelan di Lampiran A.
                                                 
                            </td>
                        </tr>

                        
                    </table>
                </div>
            </fieldset>
        </div>
        <p align="center">
            <s:hidden name="idKertas" value="${permohonanKertas.idKertas}"/>
            <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>
        </p>
    </c:if>
</s:form>
