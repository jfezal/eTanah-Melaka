<%--
    Document   : pengambilan_MMKN_Sek4
    Created on : Jul 30, 2010, 10:50:08 AM
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
    function addRow1(tableID) {
        document.getElementById('rowCount1').value = 1
        <%--document.form2.rowCount1.value = 1;--%>
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

    function deleteRow1()
    {
         var i = document.getElementById('rowCount1').value;
        <%--var i = document.form2.rowCount1.value;--%>
        var x= document.getElementById('dataTable1').rows[i-1].cells;
        var idKandungan = x[0].innerHTML;

        if (document.getElementById('idKandungan1'+(i)) != null) {
            var url = '${pageContext.request.contextPath}/pengambilan/borang_mmkn_sek4?deleteSingle&idKandungan='
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
    document.getElementById('rowCount2').value = 1;
        <%--document.form2.rowCount2.value = 1;--%>
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
        var j = document.getElementById('rowCount2').value;
        <%--var j = document.form2.rowCount2.value;--%>
        var x= document.getElementById('dataTable2').rows[j-1].cells;
        var idKandungan = x[0].innerHTML;

        document.getElementById('dataTable2').deleteRow(j-1);
        document.form2.rowCount2.value = j -1;

        if (document.getElementById('idKandungan2'+(j)) != null) {
            var url = '${pageContext.request.contextPath}/pengambilan/borang_mmkn_sek4?deleteSingle&idKandungan='
            +idKandungan;

        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
        }
    }

    function addRow3(tableID3) {
    document.getElementById('rowCount3').value = 1;
        <%--document.form2.rowCount3.value = 1;--%>
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
        var k = document.getElementById('rowCount3').value;
        <%--var k = document.form2.rowCount3.value;--%>
        var x= document.getElementById('dataTable3').rows[k-1].cells;
        var idKandungan = x[0].innerHTML;

        document.getElementById('dataTable3').deleteRow(k-1);
        document.form2.rowCount3.value = k -1;

        if (document.getElementById('idKandungan3'+(k)) != null) {
            var url = '${pageContext.request.contextPath}/pengambilan/borang_mmkn_sek4?deleteSingle&idKandungan='
            +idKandungan;

        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
        }
    }

    function addRow4(tableID4) {
    document.getElementById('rowCount4').value = 1;
        <%--document.form2.rowCount4.value = 1;--%>
        var table = document.getElementById(tableID4);

        var rowCount4 = table.rows.length;
        var row = table.insertRow(rowCount4);

        var cell2 = row.insertCell(0);
        cell2.innerHTML = "<b>"+"5." +(rowCount4+1)+"</b>";

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
        var k = document.getElementById('rowCount4').value;
        <%--var k = document.form2.rowCount4.value;--%>
        var x= document.getElementById('dataTable4').rows[k-1].cells;
        var idKandungan = x[0].innerHTML;

        document.getElementById('dataTable4').deleteRow(k-1);
        document.form2.rowCount4.value = k -1;

        if (document.getElementById('idKandungan4'+(k)) != null) {
            var url = '${pageContext.request.contextPath}/pengambilan/borang_mmkn_sek4?deleteSingle&idKandungan='
            +idKandungan;

        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
        }
    }

    function addRow5(tableID5) {
    document.getElementById('rowCount5').value = 1;
        <%--document.form.rowCount5.value = 1;--%>
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
        //alert(element1.name)
        document.form2.rowCount5.value=rowCount5 +1;
    }
    function deleteRow5()
    {
        var k = document.getElementById('rowCount5').value;
        <%--var k = document.form2.rowCount5.value;--%>
        var x= document.getElementById('dataTable5').rows[k-1].cells;
        var idKandungan = x[0].innerHTML;

        document.getElementById('dataTable5').deleteRow(k-1);
        document.form2.rowCount5.value = k -1;

        if (document.getElementById('idKandungan5'+(k)) != null) {
            var url = '${pageContext.request.contextPath}/pengambilan/borang_mmkn_sek4?deleteSingle&idKandungan='
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
            alert('Sila pilih " 3. Perihal Tanah " terlebih dahulu.');
            $("#kandungan2"+i).focus();
            return false;
        }
    }

    var count3=$("#rowCount3").val();
    for(var i=1;i<=count3;i++){
        var kandungan3= $("#kandungan3"+i).val();
        if(kandungan3 == ""){
            alert('Sila pilih " 4. PANDANGAN PENTADBIR TANAH " terlebih dahulu.');
            $("#kandungan3"+i).focus();
            return false;
        }
    }

    var count4=$("#rowCount4").val();
    for(var i=1;i<=count4;i++){
        var kandungan4= $("#kandungan4"+i).val();
        if(kandungan4 == ""){
            alert('Sila pilih " 5. PERAKUAN PENTADBIR TANAH " terlebih dahulu.');
            $("#kandungan4"+i).focus();
            return false;
        }
    }

    var count5=$("#rowCount5").val();
    for(var i=1;i<=count5;i++){
        var kandungan5= $("#kandungan5"+i).val();
        if(kandungan5 == ""){
            alert('Sila pilih " 7. PERAKUAN PENGARAH TANAH DAN GALIAN " terlebih dahulu.');
            $("#kandungan5"+i).focus();
            return false;
        }
    }

    if($("#ulasan1").val() == ""){
            <%--alert('Sila pilih " 6. KEPUTUSAN JAWATANKUASA KHAS PENGAMBILAN TANAH " terlebih dahulu.');--%>
            $("#ulasan1").val("Tiada Data");
            <%--return false;--%>
    }

    return true;

    }
</script>

            <s:form beanclass="etanah.view.stripes.pengambilan.PengambilanMMKNSek4ActionBean" >
    <s:messages/>
    <s:errors/>
    <c:if test="${form1}">
        <div class="subtitle">
            <fieldset class="aras1" id="locate">
                <legend></legend>
                <div class="content" align="center">
                    <table border="0" width="80%">

                       <tr><td><b><font style="text-transform: uppercase">PERMOHONAN PENGAMBILAN TANAH DI ATAS LOT
                                        <c:forEach items="${actionBean.hakmilikPermohonanList}" var="senaraiHakmilik" >
                                            <c:out value="${senaraiHakmilik.hakmilik.noLot}"/>,SELUAS
                                            <c:out value="${senaraiHakmilik.hakmilik.luas}"/> <c:out value="${senaraiHakmilik.hakmilik.kodUnitLuas.nama}"/>DI
                                            <c:out value="${senaraiHakmilik.hakmilik.bandarPekanMukim.nama}"/>, DAERAH
                                            <c:out value="${senaraiHakmilik.hakmilik.daerah.nama}"/>,
                                        </c:forEach>

                                           <%--SELUAS ${actionBean.hakmilik.luas} ${actionBean.hakmilik.kodUnitLuas.nama} , DI ${actionBean.hakmilik.bandarPekanMukim.nama}, DAERAH ${actionBean.hakmilik.daerah.nama},--%> MELAKA UNTUK TUJUAN ${actionBean.permohonan.sebab}, DI BAWAH SEKSYEN 4 AKTA PENGAMBILAN TANAH 1960.</font></b><br /><br /><hr /><br /></td></tr>





                                               <tr><td><br/><b>1. <s:hidden name="heading" />${actionBean.heading}</b><br /><br /></td></tr>






                        <tr><td><b>1. TUJUAN</b></td></tr>
                        <tr><tr><tr><td colspan="2"><b> &nbsp;</b><font style="text-transform: uppercase"><s:textarea rows="5" cols="140" name="tujuan"/></font><td>

                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b>2. LATAR BELAKANG</b></td></tr>

                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b>2.1 Perihal Permohonan</b></td></tr>
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
                        <tr><td align="right"><s:button name="perihalPermohonan" value="Tambah" class="btn" onclick="addRow1('dataTable1')" />
                                <s:button name="perihalPermohonan" value="Hapus" class="btn" onclick="deleteRow1()" />

                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b>3. Perihal Tanah</b></td></tr>
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
                        <tr><td><b><font style="text-transform:uppercase">4. PANDANGAN PENTADBIR TANAH ${actionBean.hakmilik.daerah.nama}</font></b></td></tr>
                        <tr><td>
                                <table id ="dataTable3">
                                    <c:set var="k" value="1" />
                                    <%--<c:if test="${actionBean.senaraiKertasKandungan3 eq null}">
                                        <s:hidden name="rowCount" value="1"/>
                                        <tr><td><b>4.1</b></td>
                                            <td><s:textarea name="kandungan3" id="kandungan3" rows="5" cols="150"/></td></tr></c:if>
                                      --%>  <c:forEach items="${actionBean.senaraiKertasKandungan3}" var="line">
                                           <%-- <s:hidden name="rowCount" value="${i}"/>--%>
                                        <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                            <td><font style="text-transform:uppercase"><s:textarea name="kandungan3${k}" id="kandungan3${k}" rows="5" cols="150" onkeyup="this.value=this.value.toUpperCase();">${line.kandungan}</s:textarea></font>
                                            </td></tr>
                                        <s:hidden name="idKandungan3${k}" id="idKandungan3${k}" value="${line.idKandungan}" />
                                            <c:set var="k" value="${k+1}" />
                                        </c:forEach>
                                        <s:hidden name="rowCount3" value="${k-1}" id="rowCount3"/>
                                </table>
                        <tr><td align="right"><s:button name="pandanganPentadbirTanah" value="Tambah" class="btn" onclick="addRow3('dataTable3')" />
                                <s:button name="pandanganPentadbirTanah" value="Hapus" class="btn" onclick="deleteRow3()" />

                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b><font style="text-transform:uppercase">5. PERAKUAN PENTADBIR TANAH ${actionBean.hakmilik.daerah.nama}</font></b></td></tr>
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
                        <tr><td><b>6. KEPUTUSAN JAWATANKUASA KHAS PENGAMBILAN TANAH NEGERI MELAKA</b></td></tr>
                        <tr><td><b> &nbsp;</b><font style="text-transform: uppercase"><s:textarea name="ulasan1" rows="5" readonly="true" cols="150" id="ulasan1"/>
                                </font></td></tr><br>

                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b>7. PERAKUAN PENGARAH TANAH DAN GALIAN</b></td></tr>
                        <tr><td>
                                <table id ="dataTable5">
                                    <c:set var="k" value="1" />
                                    <%--<c:if test="${actionBean.senaraiKertasKandungan5 eq null}">
                                        <s:hidden name="rowCount" value="1"/>
                                        <tr><td><b>7.1</b></td>
                                            <td><s:textarea name="kandungan5" id="kandungan5" rows="5" cols="150"/></td></tr></c:if>
                                        --%><c:forEach items="${actionBean.senaraiKertasKandungan5}" var="line">
                                            <%--<s:hidden name="rowCount" value="${i}"/>--%>
                                        <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                            <td><font style="text-transform:uppercase"><s:textarea name="kandungan5${k}" id="kandungan5${k}" rows="5" cols="150" onkeyup="this.value=this.value.toUpperCase();" readonly="true">${line.kandungan}</s:textarea></font>
                                            </td></tr>
                                        <s:hidden name="idKandungan5${k}" id="idKandungan5${k}" value="${line.idKandungan}" />
                                            <c:set var="k" value="${k+1}" />
                                        </c:forEach>
                                        <s:hidden name="rowCount5" value="${k-1}" id="rowCount5"/>
                                </table>
                        <tr><td align="right"><s:button name="perakuanPengarahTanah" value="Tambah" class="btn" onclick="addRow5('dataTable5')" disabled="true"/>
                                <s:button name="perakuanPengarahTanah" value="Hapus" class="btn" onclick="deleteRow5()" disabled="true"/>

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
    <c:if test="${form2}">
        <div class="subtitle">
            <fieldset class="aras1" id="locate">
                <legend></legend>
                <div class="content" align="center">
                    <table border="0" width="80%">

                        <tr><td><b><c:if test="${actionBean.permohonan.kodUrusan.kod == '425'}"> DRAF KERTAS MMKN NEGERI MELAKA </c:if>
                                    <c:if test="${actionBean.permohonan.kodUrusan.kod != '425'}">
                                    <font style="text-transform: uppercase">PERMOHONAN PENGAMBILAN TANAH DI ATAS LOT
                                        <c:forEach items="${actionBean.hakmilikPermohonanList}" var="senaraiHakmilik" >
                                            <c:out value="${senaraiHakmilik.hakmilik.noLot}"/>,
                                        </c:forEach>

                                            SELUAS ${actionBean.hakmilik.luas} ${actionBean.hakmilik.kodUnitLuas.nama} , DI ${actionBean.hakmilik.bandarPekanMukim.nama}, DAERAH ${actionBean.hakmilik.daerah.nama}, MELAKA UNTUK TUJUAN RIZAB PARIT, DI BAWAH SEKSYEN 4 AKTA PENGAMBILAN TANAH 1960.</font>
                                </c:if></b><br /><br /><hr /><br /></td></tr>

                        <tr><td><b>1. TUJUAN</b></td></tr>
                        <tr><tr><tr><td colspan="2"><b> &nbsp;</b><font style="text-transform: uppercase"><s:textarea rows="5" cols="140" name="tujuan"/></font><td>

                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b>2. LATAR BELAKANG</b></td></tr>

                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b>2.1 Perihal Permohonan</b></td></tr>
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
                        <tr><td align="right"><s:button name="perihalPermohonan" value="Tambah" class="btn" onclick="addRow1('dataTable1')" disabled="true"/>
                                <s:button name="perihalPermohonan" value="Hapus" class="btn" onclick="deleteRow1()" disabled="true"/>

                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b>3. Perihal Tanah</b></td></tr>
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
                        <tr><td><b><font style="text-transform:uppercase">4. PANDANGAN PENTADBIR TANAH ${actionBean.hakmilik.daerah.nama}</font></b></td></tr>
                        <tr><td>
                                <table id ="dataTable3">
                                    <c:set var="k" value="1" />
                                    <%--<c:if test="${actionBean.senaraiKertasKandungan3 eq null}">
                                        <s:hidden name="rowCount" value="1"/>
                                        <tr><td><b>4.1</b></td>
                                            <td><s:textarea name="kandungan3" id="kandungan3" rows="5" cols="150"/></td></tr></c:if>
                                      --%>  <c:forEach items="${actionBean.senaraiKertasKandungan3}" var="line">
                                           <%-- <s:hidden name="rowCount" value="${i}"/>--%>
                                        <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                            <td><font style="text-transform:uppercase"><s:textarea name="kandungan3${k}" id="kandungan3${k}" rows="5" cols="150" onkeyup="this.value=this.value.toUpperCase();" readonly="true">${line.kandungan}</s:textarea></font>
                                            </td></tr>
                                        <s:hidden name="idKandungan3${k}" id="idKandungan3${k}" value="${line.idKandungan}" />
                                            <c:set var="k" value="${k+1}" />
                                        </c:forEach>
                                        <s:hidden name="rowCount3" value="${k-1}" id="rowCount3"/>
                                </table>
                        <tr><td align="right"><s:button name="pandanganPentadbirTanah" value="Tambah" class="btn" onclick="addRow3('dataTable3')" disabled="true"/>
                                <s:button name="pandanganPentadbirTanah" value="Hapus" class="btn" onclick="deleteRow3()" disabled="true"/>

                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b><font style="text-transform:uppercase">5. PERAKUAN PENTADBIR TANAH ${actionBean.hakmilik.daerah.nama}</font></b></td></tr>
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
                        <tr><td><b>6. KEPUTUSAN JAWATANKUASA KHAS PENGAMBILAN TANAH NEGERI MELAKA</b></td></tr>
                        <tr><td><b> &nbsp;</b><font style="text-transform: uppercase"><s:textarea name="ulasan1" rows="5" cols="150" id="ulasan1"/></font></td></tr><br>

                        <tr><td> &nbsp;</td></tr>
                        <tr><td><b>7. PERAKUAN PENGARAH TANAH DAN GALIAN</b></td></tr>
                        <tr><td>
                                <table id ="dataTable5">
                                    <c:set var="k" value="1" />
                                    <%--<c:if test="${actionBean.senaraiKertasKandungan5 eq null}">
                                        <s:hidden name="rowCount" value="1"/>
                                        <tr><td><b>7.1</b></td>
                                            <td><s:textarea name="kandungan5" id="kandungan5" rows="5" cols="150"/></td></tr></c:if>
                                        --%><c:forEach items="${actionBean.senaraiKertasKandungan5}" var="line">
                                            <%--<s:hidden name="rowCount" value="${i}"/>--%>
                                        <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                            <td><font style="text-transform:uppercase"><s:textarea name="kandungan5${k}" id="kandungan5${k}" rows="5" cols="150" onkeyup="this.value=this.value.toUpperCase();" >${line.kandungan}</s:textarea></font>
                                            </td></tr>
                                        <s:hidden name="idKandungan5${k}" id="idKandungan5${k}" value="${line.idKandungan}" />
                                            <c:set var="k" value="${k+1}" />
                                        </c:forEach>
                                        <s:hidden name="rowCount5" value="${k-1}" id="rowCount5"/>
                                </table>
                        <tr><td align="right"><s:button name="perakuanPengarahTanah" value="Tambah" class="btn" onclick="addRow5('dataTable5')" />
                                <s:button name="perakuanPengarahTanah" value="Hapus" class="btn" onclick="deleteRow5()" />

                    </table>
                </div>
            </fieldset>
        </div>
        <p align="center">
            <%--<s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>--%>
            <s:hidden name="idKertas" value="${permohonanKertas.idKertas}"/>
            <%--<s:hidden name="idKandungan" value="${permohonanKertasKandungan.idKandungan}"/>--%>
            <s:button name="simpan2" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>
        </p>
    </c:if>
</s:form>
