<%-- 
    Document   : mmk_IzinLaluAktaBekalanLetrik
    Created on : Jan 29, 2011, 10:40:36 AM
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

    function deleteRow1(){
        var i = document.form2.rowCount1.value;
        var x= document.getElementById('dataTable1').rows[i-1].cells;
        var idKandungan = x[0].innerHTML;

        if (document.getElementById('idKandungan1'+(i)) != null) {
            var url = '${pageContext.request.contextPath}/pengambilan/mmk_LaluLetrik?deleteSingle&idKandungan='
            +idKandungan;

        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
        }
       
        document.getElementById('dataTable1').deleteRow(i-1);
        document.form2.rowCount1.value = i -1;
    }

    function addRow5(tableID2) {
        document.form2.rowCount5.value = 1;
        var table = document.getElementById(tableID2);

        var rowCount5 = table.rows.length;
        var row = table.insertRow(rowCount5);

        var cell2 = row.insertCell(0);
        cell2.innerHTML = "<b>"+"3." +(rowCount5+1)+"</b>";

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
            var url = '${pageContext.request.contextPath}/pengambilan/mmk_LaluLetrik?deleteSingle&idKandungan='
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
        cell0.innerHTML = "<b>4."+(count6)+"</b>";
        cell0.style.textAlign = "center";
        <%--cell0.style.backgroundColor = ("#328aa4");--%>
        cell0.width="2%";

        var cell1 = row6.insertCell(1);
        var element1 = document.createElement("textarea");
        element1.t = "syorPentadbir"+(count6)+(rowCount6+1);
        element1.rows = 5;
        element1.cols = 130;
        element1.name ="syorPentadbir"+(count6)+(rowCount6+1);
        element1.id ="syorPentadbir"+(count6)+(rowCount6+1);
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
        button.setAttribute('value','Tambah 4.'+(count6));
        button.onclick=function(){addDynamicRow6(table6.id,element2.id);};
        cell3.appendChild(button);

        var button1 = document.createElement('input');
        var buttonName1 = "hapus6" +(count6);
        button1.setAttribute('type','button');
        button1.className = "btn";
        button1.setAttribute('name',buttonName1);
        button1.setAttribute('value','Hapus 4.'+(count6)+' sub');
        button1.onclick=function(){deleteDynamicRow6(table6.id,element2.id);};
        cell3.appendChild(button1);

        id1.appendChild(table6);
        id1.appendChild(document.createElement('br'));
    }

    function deleteTable6(ptg, ptPengambilanPtg) {
        var bil = 4;
        var temp6 = $("#temp6").val();
        var count6 = $("#count6").val();

         var table6 = document.getElementById("table6"+count6);
             var rowCount6 = table6.rows.length;
             for(var i=rowCount6-1;i>=0;i--){
                table6.deleteRow(i);
                document.getElementById ("count6").value = parseInt(count6)-1;
             }

        if(parseInt(count6) <= parseInt(temp6)) {

            var url = '${pageContext.request.contextPath}/pengambilan/mmk_LaluLetrik?deleteTable&bil='
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
        element2.t = "syorPentadbir"+(count6)+(rowCount6+1);
        element2.rows = 5;
        element2.cols = 130;
        element2.name ="syorPentadbir"+(count6)+(rowCount6+1);
        element2.id ="syorPentadbir"+(count6)+(rowCount6+1);
        <%--element2.value ="syor"+(count)+(rowCount+1);--%>
        cell2.appendChild(element2);
    }

    function deleteDynamicRow6(tableID,countID,ptg,ptPengambilanPtg) {
        var table6 = document.getElementById(tableID);
        var rowCount6 = table6.rows.length;
        var i = tableID.substring(6);

        if(rowCount6 >1){
            var obj = document.getElementById("kand6"+(i)+(rowCount6));
            var idKandungan = $("#kand6"+(i)+(rowCount6)).val();
            table6.deleteRow(rowCount6-1);
            document.getElementById(countID).value = table6.rows.length;
            if (obj != null) {

                var url = '${pageContext.request.contextPath}/pengambilan/mmk_LaluLetrik?deleteSingle&idKandungan='
                    +idKandungan+'&ptg='+ptg+'&ptPengambilanPtg='+ptPengambilanPtg;

                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
            }

        }else{
            alert("Cannot delete row .");
        }

    }

    function addTable8(divId){
        var id1 = document.getElementById(divId);
        var count8 = document.getElementById("count8").value;
        // Increment table count by 1
        document.getElementById ("count8").value = parseInt(count8)+1;
        count8 = document.getElementById ("count8").value;
        // create New table
        var table8 = document.createElement("TABLE");
        table8.id = "table8"+count8;
        //table.className = "tablecloth";
        table8.width="100%";
        //table.border=2;
        var rowCount8 = table8.rows.length;
        var row8 = table8.insertRow(rowCount8);

        var cell0 = row8.insertCell(0);
        cell0.innerHTML = "<b>6."+(count8)+"</b>";
        cell0.style.textAlign = "center";
        <%--cell0.style.backgroundColor = ("#328aa4");--%>
        cell0.width="2%";

        var cell1 = row8.insertCell(1);
        var element1 = document.createElement("textarea");
        element1.t = "syorPengarah"+(count8)+(rowCount8+1);
        element1.rows = 5;
        element1.cols = 130;
        element1.name ="syorPengarah"+(count8)+(rowCount8+1);
        element1.id ="syorPengarah"+(count8)+(rowCount8+1);
        <%--element1.value ="syor"+(count)+(rowCount+1);--%>
        cell1.colSpan = 2;
        cell1.appendChild(element1);

        // Add hidden field
        var cell2 = row8.insertCell(2);
        var element2 = document.createElement ("input");
        element2.setAttribute("type", "hidden");
        element2.setAttribute("id", "count8"+(count8));
        element2.setAttribute("name", "count8"+(count8));
        element2.setAttribute("value", 1);
        cell2.appendChild(element2);

        // Add tambah button
        var cell3 = row8.insertCell(3);
        var button = document.createElement('input');
        var buttonName = "tambah8" +(count8);
        button.setAttribute('type','button');
        button.className = "btn";
        button.setAttribute('name',buttonName);
        button.setAttribute('value','Tambah 6.'+(count8));
        button.onclick=function(){addDynamicRow8(table8.id,element2.id);};
        cell3.appendChild(button);

        var button1 = document.createElement('input');
        var buttonName1 = "hapus8" +(count8);
        button1.setAttribute('type','button');
        button1.className = "btn";
        button1.setAttribute('name',buttonName1);
        button1.setAttribute('value','Hapus 6.'+(count8)+' sub');
        button1.onclick=function(){deleteDynamicRow8(table8.id,element2.id);};
        cell3.appendChild(button1);

        id1.appendChild(table8);
        id1.appendChild(document.createElement('br'));
    }

    function deleteTable8(ptg, ptPengambilanPtg) {
        var bil = 6;
        var temp8 = $("#temp8").val();
        var count8 = $("#count8").val();

         var table8 = document.getElementById("table8"+count8);
             var rowCount8 = table8.rows.length;
             for(var i=rowCount8-1;i>=0;i--){
                table8.deleteRow(i);
                document.getElementById ("count8").value = parseInt(count8)-1;
             }

        if(parseInt(count8) <= parseInt(temp8)) {

            var url = '${pageContext.request.contextPath}/pengambilan/mmk_LaluLetrik?deleteTable&bil='
            +bil+'&ptg='+ptg+'&ptPengambilanPtg='+ptPengambilanPtg;

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
        <%--element2.value ="syor"+(count)+(rowCount+1);--%>
        cell2.appendChild(element2);

    }

    function deleteDynamicRow8(tableID,countID,ptg, ptPengambilanPtg) {
        var table8 = document.getElementById(tableID);
        var rowCount8 = table8.rows.length;
        var i = tableID.substring(6);
        
        if(rowCount8 >1){
            var obj = document.getElementById("kand8"+(i)+(rowCount8));
            var idKandungan = $("#kand8"+(i)+(rowCount8)).val();
            table8.deleteRow(rowCount8-1);
            document.getElementById(countID).value = table8.rows.length;
            if (obj != null) {

                var url = '${pageContext.request.contextPath}/pengambilan/mmk_LaluLetrik?deleteSingle&idKandungan='
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
    var count1=$("#rowCount5").val();
    for(var i=1;i<=count1;i++){
        var kandungan5= $("#kandungan5"+i).val();
        if(kandungan5 == ""){
            alert('Sila pilih " 3. HURAIAN PENTADBIR DEARAH " terlebih dahulu.');
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
                alert('Sila pilih " 4. SYOR PENTADBIR DAERAH " terlebih dahulu.');
                $("#syorPentadbir"+i+j).focus();
                return false;
            }
        }

    }

    var count8 = $("#count8").val();
    for(var i=1;i<=count8;i++){
        var recordCount = $("#count8"+i).val();
        for(var j=1;j<=recordCount;j++){
            var syorPengarah = $("#syorPengarah"+i+j).val();
            if(syorPengarah == ""){
                alert('Sila pilih " 6. SYOR PENGARAH TANAH DAN GALIAN NEGERI " terlebih dahulu.');
                $("#syorPengarah"+i+j).focus();
                return false;
            }
        }

    }
 }
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

    if($("#kertasBil").val() == ""){
            $("#kertasBil").val("Tiada Data");
    }

    if($("#tarikhMesyuarat").val() == ""){
            $("#tarikhMesyuarat").val("Tiada Data");
    }

    if($("#tujuan").val() == ""){
            $("#tujuan").val("Tiada Data");
    }

    if($("#huraianPtg").val() == ""){
            $("#huraianPtg").val("Tiada Data");
    }

    return true;
    }

</script>

<s:form beanclass="etanah.view.stripes.pengambilan.Mmk_IzinLaluAktaBekalanLetrikActionBean" name="form2">
    <s:messages/>
    <s:errors/>
    <s:hidden name="count6" id="count6" value="${actionBean.count6}"/>
     <s:hidden name="temp6" id="temp6" value="${actionBean.count6}"/>
    <s:hidden name="count8" id="count8" value="${actionBean.count8}"/>
     <s:hidden name="temp8" id="temp8" value="${actionBean.count8}"/>
    <s:hidden name="kandunganK.kertas.idKertas"/>
    <s:hidden name="idKertas" value="${actionBean.permohonanKertas.idKertas}"/>

    <c:if test="${ptg}">
    <s:hidden name="ptg" value="${ptg}"/>
    <s:hidden name="ptPengambilanPtg" value="false"/>

    <div class="subtitle">
        <fieldset class="aras1">
            <div class="content" align="center">
                <table border="0" width="80%">
                    <div class="content" align="left">
                                <%--<table align="left">--%>
                                <tr align="left"><td align="left">&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
                                        <u><b>MAJLIS MESYUARAT KERAJAAN NEGERI SEMBILAN</b></u><br /></td></tr>
                                <tr><td> &nbsp;</td></tr>
                                <tr><td align="left"><b><label><font color="black">PERSIDANGAN :</font></label><s:text name="kertasBil.kandungan"  size="10" style="text-align:left" id="kertasBil"/>/${actionBean.kertasTahun.kandungan}<s:hidden name="kertasTahun.kandungan" value="${actionBean.kertasTahun.kandungan}"/></b></td></tr>
                                <tr><td align="left"><b><label><font color="black">TARIKH :</font></label><s:text name="tarikhMesyuarat" id="tarikhMesyuarat" class="datepicker" size="30" style="text-align:left" /></b></td></tr>
                         </div>
                         <tr><td></td></tr><tr><td></td></tr><tr><td></td></tr><tr><td></td></tr>
                                    
                    <tr><td><b><s:hidden name="heading" />
                        ${actionBean.heading}</b></td></tr>

                    <tr><td><b>1. TUJUAN</b></td></tr>
                    <tr><td><font style="text-transform: uppercase"><s:textarea rows="5" cols="160" name="tujuan" id="tujuan"/></font></td></tr><br>

                    <tr><td> &nbsp;</td></tr>
                        <tr><td><b>2. LATAR BELAKANG</b></td></tr>

                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b>2.1 Perihal Permohonan</b></td></tr>
                        <tr><td>
                                <table id ="dataTable1">
                                    <c:set var="i" value="1" />
                                   <c:forEach items="${actionBean.senaraiKertasKandungan1}" var="line">
                                        <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                            <td><font style="text-transform: uppercase"><s:textarea name="kandungan1${i}"  id="kandungan1${i}" rows="5" cols="150" onkeyup="this.value=this.value.toUpperCase();">${line.kandungan}</s:textarea></font>
                                            </td></tr>
                                            <c:set var="i" value="${i+1}" />
                                        </c:forEach>
                                        <s:hidden name="rowCount1" value="${i-1}" id="rowCount1"/>
                                </table>
                        <tr><td align="right">
                                <s:button name="perihalPermohonan" value="Tambah" class="btn" onclick="addRow1('dataTable1')" disabled="true"/>
                                <s:button name="perihalPermohonan" value="Hapus" class="btn" onclick="deleteRow1()" disabled="true"/>


                    <tr><td><b><font style="text-transform: uppercase">3. HURAIAN PENTADBIR DAERAH&nbsp;</font></b></td></tr>
                   <tr><td>
                           <table border="0" width="96%" id="dataTable5">
                               <c:set var="i" value="1" />
                               <c:forEach items="${actionBean.senaraiKertasKandungan5}" var="line">
                                <tr style="font-weight:bold">
                                    <td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                    <td><font style="text-transform: uppercase"><s:textarea name="kandungan5${i}" id="kandungan5${i}" rows="5" cols="150" readonly="true" onkeyup="this.value=this.value.toUpperCase(); ">${line.kandungan}</s:textarea></font></td>
                                </tr>
                                <s:hidden name="idKandungan5${i}" id="idKandungan5${i}" value="${line.idKandungan}" />
                                    <c:set var="i" value="${i+1}" />
                                </c:forEach>
                                <s:hidden name="rowCount5" value="${i-1}" id="rowCount5"/>
                           </table>
                       </td>
                   </tr><br>
                   <tr><td align="right"><s:button name="hurianPentadbir" value="Tambah" class="btn" onclick="addRow5('dataTable5')" disabled="true"/>
                           <s:button name="hurianPentadbir" value="Hapus" class="btn" onclick="deleteRow5('${ptg}','${ptPengambilanPtg}')" disabled="true"/>
                    </td>
                    </tr>

                        <tr><td><b><font style="text-transform: uppercase">4. SYOR PENTADBIR DAERAH&nbsp;</font></b></td></tr>
                            <c:forEach var="i" begin="1" end="${actionBean.count6}">
                            <tr><td>
                                <table  width="90%" id="table6${i}" >
                                            <c:set var="recordCount6" value="0"/>
                                            <c:forEach items="${actionBean.senaraiSyorPentadbir[i]}" var="senarai">
                                                <c:set var="recordCount6" value="${recordCount6+1}"/>
                                                <c:if test="${recordCount6 eq 1}">
                                                    <tr> <td><b>4.${i}</b></td>
                                                        <td colspan="2"><s:textarea rows="5" cols="130" name="syorPentadbir${i}${recordCount6}" id="syorPentadbir${i}${recordCount6}" value="${senarai.kandungan}" readonly="true"/></td>
                                                        <td><s:hidden name="count6${i}" id="count6${i}" value="${(fn:length(actionBean.senaraiSyorPentadbir[i]))}" /> </td>
                                                        <td><s:button class="btn" value="Tambah 4.${i}" name="add" id="tambah6${i}" onclick="addDynamicRow6('table6${i}','count6${i}')" disabled="true"/></td>
                                                        <td><s:button class="btn" value="Hapus 4.${i} sub" name="hapus" id="hapus6${i}" onclick="deleteDynamicRow6('table6${i}','count6${i}','${ptg}','${ptPengambilanPtg}')" disabled="true"/></td>
                                                </c:if>
                                                <c:if test="${recordCount6 ne 1}">
                                                <tr>  <td></td>
                                                    <td><b><c:out value="${actionBean.str[recordCount6-1]}"/>.</b></td>
                                                    <td><s:textarea rows="5" cols="130" name="syorPentadbir${i}${recordCount6}" id="syorPentadbir${i}${recordCount6}" value="${senarai.kandungan}" readonly="true"/></td>
                                                </c:if>
                                                <s:hidden name="kand6${i}${recordCount6}" id="kand6${i}${recordCount6}" value="${senarai.idKandungan}" />
                                             </c:forEach>
                                        </table>
                                    </td></tr>
                            </c:forEach>
                                <tr><td align="right">
                                        <div id="Tables6">

                                        </div>
                                        <s:button class="btn" value="Tambah 4" name="add" onclick="addTable6('Tables6')" disabled="true"/>
                                        <s:button class="longbtn" value="Hapus 4 rekod terakhir" name="add" onclick="deleteTable6('${ptg}','${ptPengambilanPtg}')" disabled="true" style="font-size:11px"/>

                            <tr><td><b>5. HURAIAN PENGARAH TANAH DAN GALIAN NEGERI</b></td></tr>
                            <tr><td><s:textarea name="huraianPtg" id="huraianPtg" rows="5" cols="160"/></td></tr><br>

                            <tr><td><b>6. SYOR PENGARAH TANAH DAN GALIAN NEGERI</b></td></tr>
                            <c:forEach var="i" begin="1" end="${actionBean.count8}">
                                <tr><td>
                                        <table  width="90%" id="table8${i}" >
                                            <c:set var="recordCount8" value="0"/>
                                            <c:forEach items="${actionBean.senaraiSyorPengarah[i]}" var="senarai">
                                                <c:set var="recordCount8" value="${recordCount8+1}"/>
                                                <c:if test="${recordCount8 eq 1}">
                                                    <tr> <td><b>6.${i}</b></td>
                                                        <td colspan="2"><s:textarea rows="5" cols="130" name="syorPengarah${i}${recordCount8}" id="syorPengarah${i}${recordCount8}" value="${senarai.kandungan}" /></td>
                                                        <td><s:hidden name="count8${i}" id="count8${i}" value="${(fn:length(actionBean.senaraiSyorPengarah[i]))}" /> </td>
                                                        <td><s:button class="btn" value="Tambah 6.${i}" name="add" id="tambah8${i}" onclick="addDynamicRow8('table8${i}','count8${i}')"/></td>
                                                        <td><s:button class="btn" value="Hapus 6.${i} sub" name="hapus" id="hapus8${i}" onclick="deleteDynamicRow8('table8${i}','count8${i}','${ptg}','${ptPengambilanPtg}')"/></td>
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
                                        <s:button class="btn" value="Tambah 6" name="add" onclick="addTable8('Tables8')"/>
                                        <s:button class="longbtn" value="Hapus 6 rekod terakhir" name="add" onclick="deleteTable8('${ptg}','${ptPengambilanPtg}')" style="font-size:11px"/>


                            <tr><td><b>7. KEPUTUSAN</b></td></tr>
                            <tr><td width="100%">7.1 Dibentangkan untuk pertimbangan dan keputusan Majlis Mesyuarat Kerajaan Negeri.</td></tr><br>
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
          <legend></legend>

            <div class="content" align="center">
                <table border="0" width="80%">

                    <div class="content" align="left">
                                <%--<table align="left">--%>
                                <tr align="left"><td align="left">&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
                                        <u><b>MAJLIS MESYUARAT KERAJAAN NEGERI SEMBILAN</b></u><br /></td></tr>
                                <tr><td> &nbsp;</td></tr>
                                <tr><td align="left"><b><label><font color="black">PERSIDANGAN :</font></label><s:text name="kertasBil.kandungan"  size="10" style="text-align:left" id="kertasBil" disabled="true"/>/${actionBean.kertasTahun.kandungan}<s:hidden name="kertasTahun.kandungan" value="${actionBean.kertasTahun.kandungan}"/></b></td></tr>
                                <tr><td align="left"><b><label><font color="black">TARIKH :</font></label><s:text name="tarikhMesyuarat" id="datepicker" class="datepicker" size="30" style="text-align:left" disabled="true"/></b></td></tr><br>
                          </div>
                          <tr><td></td></tr><tr><td></td></tr><tr><td></td></tr><tr><td></td></tr>

                    <tr><td><b><s:hidden name="heading" />
                        ${actionBean.heading}</b></td></tr>

                    <tr><td></td></tr><tr><td></td></tr><tr><td></td></tr><tr><td></td></tr><tr><td></td></tr><tr><td></td></tr>
                    <tr><td><b>1. TUJUAN</b></td></tr>
                    <tr><td><font style="text-transform: uppercase"><s:textarea rows="5" cols="160" name="tujuan" id="tujuan"/></font></td></tr><br>

                   <tr><td> &nbsp;</td></tr>
                        <tr><td><b>2. LATAR BELAKANG</b></td></tr>

                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b>2.1 Perihal Permohonan</b></td></tr>
                        <tr><td>
                                <table id ="dataTable1">
                                    <c:set var="i" value="1" />
                                        <c:forEach items="${actionBean.senaraiKertasKandungan1}" var="line">
                                        <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                            <td><font style="text-transform: uppercase"><s:textarea name="kandungan1${i}"  id="kandungan1${i}" rows="5" cols="150" onkeyup="this.value=this.value.toUpperCase();">${line.kandungan}</s:textarea></font></td>
                                        </tr>
                                            <c:set var="i" value="${i+1}" />
                                        </c:forEach>
                                        <s:hidden name="rowCount1" value="${i-1}" id="rowCount1"/>
                                </table>
                        <tr><td align="right"><s:button name="perihalPermohonan" value="Tambah" class="btn" onclick="addRow1('dataTable1')" />
                                <s:button name="perihalPermohonan" value="Hapus" class="btn" onclick="deleteRow1()" />
                    
                    <tr><td><b><font style="text-transform: uppercase">3. HURAIAN PENTADBIR DAERAH&nbsp;</font></b></td></tr>
                   <tr><td>
                           <table border="0" width="96%" id="dataTable5">
                               <c:set var="i" value="1" />
                               <c:forEach items="${actionBean.senaraiKertasKandungan5}" var="line">
                                <tr style="font-weight:bold">
                                    <td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                    <td><font style="text-transform: uppercase"><s:textarea name="kandungan5${i}" id="kandungan5${i}" rows="5" cols="150" onkeyup="this.value=this.value.toUpperCase();">${line.kandungan}</s:textarea></font></td>
                                </tr>
                                <s:hidden name="idKandungan5${i}" id="idKandungan5${i}" value="${line.idKandungan}" />
                                    <c:set var="i" value="${i+1}" />
                                </c:forEach>
                                <s:hidden name="rowCount5" value="${i-1}" id="rowCount5"/>
                           </table>
                       </td>
                   </tr><br>
                   <tr><td align="right"><s:button name="hurianPentadbir" value="Tambah" class="btn" onclick="addRow5('dataTable5')" />
                        <s:button name="hurianPentadbir" value="Hapus" class="btn" onclick="deleteRow5('${ptg}','${ptPengambilanPtg}')" />
                        </td>
                    </tr>

                        <tr><td><b><font style="text-transform: uppercase">4. SYOR PENTADBIR DAERAH&nbsp;</font></b></td></tr>
                            <c:forEach var="i" begin="1" end="${actionBean.count6}">
                            <tr><td>
                                <table  width="90%" id="table6${i}" >
                                            <c:set var="recordCount6" value="0"/>
                                            <c:forEach items="${actionBean.senaraiSyorPentadbir[i]}" var="senarai">
                                                <c:set var="recordCount6" value="${recordCount6+1}"/>
                                                <c:if test="${recordCount6 eq 1}">
                                                    <tr> <td><b>4.${i}</b></td>
                                                        <td colspan="2"><s:textarea rows="5" cols="130" name="syorPentadbir${i}${recordCount6}" id="syorPentadbir${i}${recordCount6}" value="${senarai.kandungan}" /></td>
                                                        <td><s:hidden name="count6${i}" id="count6${i}" value="${(fn:length(actionBean.senaraiSyorPentadbir[i]))}" /> </td>
                                                        <td><s:button class="btn" value="Tambah 4.${i}" name="add" id="tambah6${i}" onclick="addDynamicRow6('table6${i}','count6${i}')"/></td>
                                                        <td><s:button class="btn" value="Hapus 4.${i} sub" name="hapus" id="hapus6${i}" onclick="deleteDynamicRow6('table6${i}','count6${i}','${ptg}','${ptPengambilanPtg}')"/></td>
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
                                        <s:button class="btn" value="Tambah 4" name="add" onclick="addTable6('Tables6')"/>
                                        <s:button class="longbtn" value="Hapus 4 rekod terakhir" name="add" onclick="deleteTable6('${ptg}','${ptPengambilanPtg}')" style="font-size:11px"/>

                            <tr><td><b>5. HURAIAN PENGARAH TANAH DAN GALIAN NEGERI</b></td></tr>
                            <tr><td><s:textarea name="huraianPtg" id="huraianPtg" rows="5" cols="160" disabled="true"/></td></tr><br>

                            <tr><td><b>6. SYOR PENGARAH TANAH DAN GALIAN NEGERI</b></td></tr>
                            <c:forEach var="i" begin="1" end="${actionBean.count8}">
                                <tr><td>
                                        <table  width="90%" id="table8${i}" >
                                            <c:set var="recordCount8" value="0"/>
                                            <c:forEach items="${actionBean.senaraiSyorPengarah[i]}" var="senarai">
                                                <c:set var="recordCount8" value="${recordCount8+1}"/>
                                                <c:if test="${recordCount8 eq 1}">
                                                    <tr> <td><b>6.${i}</b></td>
                                                        <td colspan="2"><s:textarea rows="5" cols="130" name="syorPengarah${i}${recordCount8}" id="syorPengarah${i}${recordCount8}" value="${senarai.kandungan}" readonly="true"/></td>
                                                        <td><s:hidden name="count8${i}" id="count8${i}" value="${(fn:length(actionBean.senaraiSyorPengarah[i]))}" /> </td>
                                                        <td><s:button class="btn" value="Tambah" name="add" id="tambah8${i}" disabled="true" onclick="addDynamicRow8('table8${i}','count8${i}')"/></td>
                                                        <td><s:button class="btn" value="Hapus" name="hapus" id="hapus8${i}" disabled="true" onclick="deleteDynamicRow8('table8${i}','count8${i}','${ptg}','${ptPengambilanPtg}')"/></td>
                                                </c:if>
                                                <c:if test="${recordCount8 ne 1}">
                                                <tr>  <td></td>
                                                    <td><b><c:out value="${actionBean.str[recordCount8-1]}"/>.</b></td>
                                                    <td><s:textarea rows="5" cols="130" name="syorPengarah${i}${recordCount8}" id="syorPengarah${i}${recordCount8}" value="${senarai.kandungan}" readonly="true"/></td>
                                                </c:if>
                                                <s:hidden name="kand8${i}${recordCount8}" id="kand8${i}${recordCount8}" value="${senarai.idKandungan}" />
                                             </c:forEach>
                                        </table>
                                    </td></tr>
                            </c:forEach>
                                <tr><td align="right">
                                        <div id="Tables8">

                                        </div>
                                        <s:button class="btn" value="Tambah" name="add" onclick="addTable8('Tables8')" disabled="true"/>
                                        <s:button class="btn" value="Hapus" name="add" onclick="deleteTable8('${ptg}','${ptPengambilanPtg}')" disabled="true"/>


                            <tr><td><b>7. KEPUTUSAN</b></td></tr>
                            <tr><td width="100%">7.1 Dibentangkan untuk pertimbangan dan keputusan Majlis Mesyuarat Kerajaan Negeri.</td></tr><br>
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
