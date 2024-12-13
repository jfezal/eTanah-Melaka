<%--
    Document   : affidavit
    Created on : May 13, 2011, 9:59:55 AM
    Author     : massita @modified hazirah
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html;charset=windows-1252" import="java.util.*" import="java.text.SimpleDateFormat"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script type="text/javascript">
    $(document).ready( function(){
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    });

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
            cell0.innerHTML = "<b>1."+(count5)+"</b>";
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
            button.setAttribute('value','Tambah'+' 1.'+(count5));
            button.onclick=function(){addDynamicRow5(table5.id,element2.id);};
            cell3.appendChild(button);

            var button1 = document.createElement('input');
            var buttonName1 = "hapus5" +(count5);
            button1.setAttribute('type','button');
            button1.className = "btn";
            button1.setAttribute('name',buttonName1);
            button1.setAttribute('value','Hapus'+' 1.'+(count5));
            button1.onclick=function(){deleteDynamicRow5(table5.id,element2.id);};
            cell3.appendChild(button1);

            id1.appendChild(table5);
            id1.appendChild(document.createElement('br'));
        }

        function deleteTable5() {
            var bil = 1;
            var temp5 = $("#temp5").val();
            var count5 = $("#count5").val();

            var table5 = document.getElementById("table5"+count5);
            var rowCount7 = table5.rows.length;
            for(var i=rowCount7-1;i>=0;i--){
                table5.deleteRow(i);
                document.getElementById ("count5").value = parseInt(count5)-1;
            }

            if(parseInt(count5) <= parseInt(temp5)) {

                var url = '${pageContext.request.contextPath}/pengambilan/affidavitMahkamah?deleteTable&bil='
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

                    var url = '${pageContext.request.contextPath}/pengambilan/affidavitMahkamah?deleteSingle&idKandungan='
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

        function addRow1(tableID) {
            document.getElementById('rowCount1').value = 1;
            var table = document.getElementById(tableID);

            var rowCount1 = table.rows.length;
            var row = table.insertRow(rowCount1);
            var cell2 = row.insertCell(0);
            eDIV = document.createElement("b");
            // add the text "hello world" to the div with createTextNode
            eDIV.appendChild(document.createTextNode("1." +(rowCount1+1)));
            // append your newly created DIV element to an already existing element.
            cell2.appendChild(eDIV);

            var cell1 = row.insertCell(1);
            var element1 = document.createElement("textarea");
            element1.t = "kandungan1"+(rowCount1+1);
            element1.rows = 5;
            element1.cols = 150;
            element1.name ="kandungan1"+(rowCount1+1);
            element1.id ="kandungan1"+(rowCount1+1);
            cell1.appendChild(element1);
            document.getElementById('rowCount1').value=rowCount1 +1;
        }

        function deleteRow1(formPtg,form1)
        {
            var i = document.getElementById('rowCount1').value;
            var x= document.getElementById('dataTable1').rows[i-1].cells;
            var idKandungan = x[0].innerHTML;

            if (document.getElementById('idKandungan1'+(i)) != null) {
                var url = '${pageContext.request.contextPath}/pengambilan/affidavitMahkamah?deleteSingle&idKandungan='
                    +idKandungan+'&formPtg='+formPtg+'&form1='+form1;

                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
            }

            document.getElementById('dataTable1').deleteRow(i-1);
            document.getElementById('rowCount1').value = i -1;
        }

        function validation() {
            var count1=$("#rowCount1").val();
            for(var i=1;i<=count1;i++){
                var kandungan1= $("#kandungan1"+i).val();
                if(kandungan1 == ""){
                    alert('Sila pilih " 1. perakuan " terlebih dahulu.');
                    $("#kandungan1"+i).focus();
                    return false;
                }
            }

            var count5 = $("#count5").val();
            for(var i=1;i<=count5;i++){
                var recordCount5 = $("#count5"+i).val();
                for(var j=1;j<=recordCount5;j++){
                    var syorPentadbir = $("#syorPentadbir"+i+j).val();
                    if(syorPentadbir == ""){
                        alert('Sila pilih " 5. SYOR PENTADBIR TANAH " terlebih dahulu.');
                        $("#syorPentadbir"+i+j).focus();
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

            if($("#heading2").val() == ""){
                $("#heading2").val("Tiada Data");
            }

            if($("#perakuan").val() == ""){
                $("#perakuan").val("Tiada Data");
            }

            if($("#perakuan1").val() == ""){
                $("#perakuan1").val("Tiada Data");
            }

            if($("#tarikhIkrar").val() == ""){
                alert('Sila pilih " PADA " terlebih dahulu.');
                $("#tarikhIkrar").focus();
                return false;
            }
            if($("#diIkrarOleh").val() == ""){
                $("#diIkrarOleh").val("Tiada Data");
            }
            if($("#ulasanPengarah").val() == ""){
                $("#ulasanPengarah").val("Tiada Data");
            }

            if($("#samanPemulaBil").val() == ""){
                $("#samanPemulaBil").val("Tiada Data");
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
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.pengambilan.AffidavitMahkamahActionBean" name="form2">
    <s:messages/>
    <c:if test="${showView}">
        <%--<s:hidden name="showView" value="${showView}"/>
        <s:hidden name="showEdit" value="false"/>--%>
        <div id="hakmilik_details">
            <s:errors/>
            <fieldset class="aras1"><br/>
                <legend align="left"><b>Maklumat Hakmilik Permohonan</b></legend>
                <div align="center">
                    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="5" cellpadding="0" cellspacing="0"
                                   requestURI="/pengambilan/maklumatPampasan" id="line">
                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                        <display:column title="ID Hakmilik">
                            <s:link beanclass="etanah.view.stripes.pengambilan.AffidavitMahkamahActionBean"
                                    event="hakmilikDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                                <s:param name="display" value="true"/>
                                <s:param name="idHakmilik" value="${line.hakmilik.idHakmilik}"/>${line.hakmilik.idHakmilik}</s:link>
                        </display:column>
                        <display:column property="hakmilik.noLot" title="No Lot/No PT" />
                        <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                        <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                    </display:table>
                </div>
            </fieldset>
            <br /><br />

            <%--${showView}::view1--%>
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
                                        <c:if test="${senarai.aktif eq 'Y'}">
                                            <%--<c:if test="${actionBean.perbicaraanKehadiran.bantahElektrik eq '0'}">--%>
                                            <s:link beanclass="etanah.view.stripes.pengambilan.AffidavitMahkamahActionBean"
                                                    event="pihakDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                                                <s:param name="idPihak" value="${senarai.pihak.idPihak}"/>${senarai.pihak.nama}
                                                <s:param name="display" value="true"/>
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
                                        </c:if>
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

            <%--${showView}::view2--%>
            <%--<c:if test="${view}">--%>
            <c:if test="${showDetails}">
                <div class="subtitle">
                    <fieldset class="aras1" id="locate">
                        <div class="content" align="center">
                            <table border="0" width="80%">
                                <tr align="left">
                                    <td align="center"> 
                                        <b>DALAM MAHKAMAH TINGGI MALAYA DI SEREMBAN</b>
                                    </td>
                                </tr>
                                <tr align="left">
                                    <td align="center"><b>SAMAN PEMULA BIL: ${actionBean.permohonanMahkamah.samanPemulaBil}<s:hidden name="samanPemulaBil" id="samanPemulaBil"/></b><hr></td>
                                </tr>

                                <table align="right" width="30%">
                                    <tr>
                                        <td>Dalam perkara seksyen 29 Akta Pengambilan Tanah 1960</td>
                                    </tr><br />
                                    <tr>
                                        <td align="center"><u>DAN</u></td>
                                    </tr>
                                    <tr>
                                        <td>Dalam perkara mengenai tanah yang terkandung dalam ${actionBean.hakmilik.lot.nama} ${actionBean.hakmilik.noLot} Hakmilik ${actionBean.hakmilik.kodHakmilik.kod} ${actionBean.hakmilik.noHakmilik} di ${actionBean.hakmilik.bandarPekanMukim.nama}, Daerah ${actionBean.hakmilik.daerah.nama}.</td>
                                    </tr>
                                </table>
                                <br><br><br><br><br><br>
                                <table align="left">
                                    <tr>
                                        <td><b>DISEDIAKAN OLEH</b></td>
                                        <td><b>:</b></td>
                                        <td>
                                            <s:select name="permohonanKertas.penyediaSidang" id="penyediaSidang" disabled="true">
                                                <s:option value="">--Sila Pilih--</s:option>
                                                <s:options-collection collection="${actionBean.senaraiPengguna}" label="nama" value="nama" sort="nama"/>
                                            </s:select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>NAMA</b></td>
                                        <td><b>:</b></td>
                                        <td><s:text name="wakilSidang" size="40" id="wakilSidang" onkeyup="this.value=this.value.toUpperCase();" disabled="true"/></td>
                                    </tr>
                                </table>
                                <br><br>

                                <table align="center">
                                    <tr align="center"><td><br /><br /><b><u>AFFIDAVIT</u></b></td></tr>

                                    <tr><td><font style="text-transform: capitalize"><s:textarea name="perakuan1.kandungan" rows="5" cols="140" id="perakuan1" disabled="true"/></font></td></tr>

                                    <c:forEach var="i" begin="1" end="${actionBean.count5}">
                                        <tr><td>
                                                <table  width="90%" id="table5${i}" >
                                                    <c:set var="recordCount5" value="0"/>
                                                    <c:forEach items="${actionBean.senaraiPentadbir[i]}" var="senarai">
                                                        <c:set var="recordCount5" value="${recordCount5+1}"/>
                                                        <c:if test="${recordCount5 eq 1}">
                                                            <tr> <td><b>1.${i}</b></td>
                                                                <td colspan="2"><s:textarea rows="5" cols="130" name="senaraiPentadbir${i}${recordCount5}" class="normal_text" id="senaraiPentadbir${i}${recordCount5}" value="${senarai.kandungan}" disabled="true"/></td>
                                                                <td><s:hidden name="count5${i}" id="count5${i}" value="${(fn:length(actionBean.senaraiPentadbir[i]))}" /> </td>
                                                                <td><s:button class="btn" disabled="true" value="Tambah 1.${i}" name="add" id="tambah5${i}" onclick="addDynamicRow5('table5${i}','count5${i}')"/></td>
                                                                <td><s:button class="btn" disabled="true" value="Hapus 1.${i}" name="hapus" id="hapus5${i}" onclick="deleteDynamicRow5('table5${i}','count5${i}')"/></td>
                                                            </c:if>
                                                            <c:if test="${recordCount5 ne 1}">
                                                            <tr>  <td></td>
                                                                <td><b><c:out value="${actionBean.str[recordCount5-1]}"/>.</b></td>
                                                                <td><s:textarea rows="5" cols="130" name="senaraiPentadbir${i}${recordCount5}" class="normal_text" id="senaraiPentadbir${i}${recordCount5}" value="${senarai.kandungan}" disabled="true"/></td>
                                                            </c:if>
                                                            <s:hidden name="kand5${i}${recordCount5}" id="kand5${i}${recordCount5}" value="${senarai.idKandungan}" />
                                                        </c:forEach>
                                                </table>
                                            </td></tr>
                                        </c:forEach>
                                    <tr><td align="right">
                                            <div id="Tables5">

                                            </div>
                                            <s:button disabled="true" class="btn" value="Tambah" name="add" onclick="addTable5('Tables5')"/>
                                            <s:button disabled="true" class="btn" value="Hapus" name="add" onclick="deleteTable5()"/>
                                        </td></tr>
                                </table>
                                <table align="left">
                                    <tr><td><b>TARIKH IKRAR : </b>&nbsp;&nbsp;<s:text disabled="true" class="datepicker" name="permohonanMahkamah.tarikhIkrar" id="tarikhIkrar" formatPattern="dd/MM/yyyy" style="width:110px;" /></td</tr>
                                </table>
                            </table>
                        </div>
                    </fieldset>
                </div>
                <p align="center">
                    <s:hidden name="idPihak" value="${actionBean.idPihak}"/>
                    <s:hidden name="idHakmilik" value="${actionBean.idHakmilik}"/>
                    <s:hidden name="idKertas" value="${permohonanKertas.idKertas}"/>
                    <s:button disabled="true" name="simpan" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>
                </p>
            </c:if>
            <%--</c:if>--%>
        </div>
    </c:if>

    <c:if test="${showEdit}">
        <%--<s:hidden name="showEdit" value="${showEdit}"/>
        <s:hidden name="showView" value="false"/>--%>
        <div id="hakmilik_details">
            <s:errors/>
            <fieldset class="aras1"><br/>
                <legend align="left"><b>Maklumat Hakmilik Permohonan</b></legend>
                <div align="center">
                    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="5" cellpadding="0" cellspacing="0"
                                   requestURI="/pengambilan/maklumatPampasan" id="line">
                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                        <display:column title="ID Hakmilik">
                            <s:link beanclass="etanah.view.stripes.pengambilan.AffidavitMahkamahActionBean"
                                    event="hakmilikDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                                <s:param name="display" value="false"/>
                                <s:param name="idHakmilik" value="${line.hakmilik.idHakmilik}"/>${line.hakmilik.idHakmilik}</s:link>
                        </display:column>
                        <display:column property="hakmilik.noLot" title="No Lot/No PT" />
                        <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                        <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                    </display:table>
                </div>
            </fieldset>
            <br /><br />

            <%--${showEdit}::edit1--%>
            <c:if test="${actionBean.hakmilik ne null}">
                <s:errors/>
                <c:if test="${showTuanTanah}">

                    <fieldset class="aras1">
                        <legend>Tuan Tanah</legend><br />
                        <div align="center">*Sila Klik nama tuan tanah.</div>
                        <div align="center">

                            <display:table class="tablecloth" name="${actionBean.hakmilik}" cellpadding="0" cellspacing="0" id="tbl1">
                                <display:column title="No" sortable="true" style="vertical-align:baseline">${tbl1_rowNum}</display:column>
                                <display:column property="idHakmilik" title="ID Hakmilik" />
                                <display:column property="noLot" title="Nombor Lot/PT" />
                                <display:column title="Daerah" property="daerah.nama" class="daerah" />
                                <display:column property="bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                                <display:column title="Orang Berkepentingan" style="vertical-align:baseline">
                                    <c:forEach items="${actionBean.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                                        <c:if test="${senarai.aktif eq 'Y'}">
                                            <%--<c:if test="${actionBean.perbicaraanKehadiran.bantahElektrik eq '0'}">--%>
                                            <s:link beanclass="etanah.view.stripes.pengambilan.AffidavitMahkamahActionBean"
                                                    event="pihakDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                                                <s:param name="idPihak" value="${senarai.pihak.idPihak}"/>${senarai.pihak.nama}
                                                <s:param name="display" value="false"/>
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
                                        </c:if>
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

            <%--${showEdit}::edit2--%>
            <c:if test="${showDetails}">
                <s:hidden name="count5" id="count5" value="${actionBean.count5}"/>
                <s:hidden name="display"/>
                <div class="subtitle">
                    <fieldset class="aras1" id="locate">
                        <div class="content" align="center">
                            <table border="0" width="80%">
                                <tr align="left">
                                    <td align="center"> 
                                        <b>DALAM MAHKAMAH TINGGI MALAYA DI SEREMBAN</b>
                                    </td>
                                </tr>
                                <tr align="left">
                                    <td align="center"><b>SAMAN PEMULA BIL: ${actionBean.permohonanMahkamah.samanPemulaBil}<s:hidden name="samanPemulaBil" id="samanPemulaBil"/></b><hr></td>
                                </tr>

                                <table align="right" width="30%">
                                    <tr>
                                        <td>Dalam perkara seksyen 29 Akta Pengambilan Tanah 1960</td>
                                    </tr><br />
                                    <tr>
                                        <td align="center"><u>DAN</u></td>
                                    </tr>
                                    <tr>
                                        <td>Dalam perkara mengenai tanah yang terkandung dalam ${actionBean.hakmilik.lot.nama} ${actionBean.hakmilik.noLot} Hakmilik ${actionBean.hakmilik.kodHakmilik.kod} ${actionBean.hakmilik.noHakmilik} di ${actionBean.hakmilik.bandarPekanMukim.nama}, Daerah ${actionBean.hakmilik.daerah.nama}.</td>
                                    </tr>
                                </table>
                                <br><br><br><br><br><br>
                                <table align="left">
                                    <tr>
                                        <td><b>DISEDIAKAN OLEH</b></td>
                                        <td><b>:</b></td>
                                        <td>
                                            <s:select name="permohonanKertas.penyediaSidang" id="penyediaSidang">
                                                <s:option value="">--Sila Pilih--</s:option>
                                                <s:options-collection collection="${actionBean.senaraiPengguna}" label="nama" value="nama" sort="nama" />
                                            </s:select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>NAMA</b></td>
                                        <td><b>:</b></td>
                                        <td><s:text name="wakilSidang" value="${actionBean.permohonanKertas.wakilSidang}" size="40" id="wakilSidang" onkeyup="this.value=this.value.toUpperCase();"/></td>
                                    </tr>
                                </table>
                                <br><br>

                                <table align="center">
                                    <tr align="center"><td><br /><br /><b><u>AFFIDAVIT</u></b></td></tr>

                                    <tr><td><font style="text-transform: capitalize"><s:textarea name="perakuan1.kandungan" rows="5" cols="140" id="perakuan1"/></font></td></tr>

                                    <c:forEach var="i" begin="1" end="${actionBean.count5}">
                                        <tr><td>
                                                <table  width="90%" id="table5${i}" >
                                                    <c:set var="recordCount5" value="0"/>
                                                    <c:forEach items="${actionBean.senaraiPentadbir[i]}" var="senarai">
                                                        <c:set var="recordCount5" value="${recordCount5+1}"/>
                                                        <c:if test="${recordCount5 eq 1}">
                                                            <tr> <td><b>1.${i}</b></td>
                                                                <td colspan="2"><s:textarea rows="5" cols="130" name="senaraiPentadbir${i}${recordCount5}" class="normal_text" id="senaraiPentadbir${i}${recordCount5}" value="${senarai.kandungan}"/></td>
                                                                <td><s:hidden name="count5${i}" id="count5${i}" value="${(fn:length(actionBean.senaraiPentadbir[i]))}" /> </td>
                                                                <td><s:button class="btn" value="Tambah 1.${i}" name="add" id="tambah5i${i}" onclick="addDynamicRow5('table5${i}','count5${i}')"/></td>
                                                                <td><s:button class="btn" value="Hapus 1.${i}" name="hapus" id="hapus5i${i}" onclick="deleteDynamicRow5('table5${i}','count5${i}')"/></td>
                                                            </c:if>
                                                            <c:if test="${recordCount5 ne 1}">
                                                            <tr>  <td></td>
                                                                <td><b><c:out value="${actionBean.str[recordCount5-1]}"/>.</b></td>
                                                                <td><s:textarea rows="5" cols="130" name="senaraiPentadbir${i}${recordCount5}" class="normal_text" id="senaraiPentadbir${i}${recordCount5}" value="${senarai.kandungan}"/></td>
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
                                        </td></tr>
                                </table>
                                <table align="left">
                                    <tr><td><b>TARIKH IKRAR : </b>&nbsp;&nbsp;<s:text class="datepicker" name="permohonanMahkamah.tarikhIkrar" value="${actionBean.permohonanMahkamah.tarikhIkrar}" id="tarikhIkrar" formatPattern="dd/MM/yyyy" style="width:110px;" /></td</tr>
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
        </div>
    </c:if>

</s:form>