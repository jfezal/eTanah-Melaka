<%--
    Document   : kertas_pertimbangan_PTG
    Created on : Mar 1, 2010, 6:23:50 PM
    Author     : nursyazwani
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page import="java.util.List;" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes-dynattr.tld"%>
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
        width:50em;
    }
</style>

<script type="text/javascript">

    function addRow2(tableid){
        var table = document.getElementById(tableid);
        var rowcount = table.rows.length;
        var row = table.insertRow(rowcount);
        var cell0 = row.insertCell(0);
        cell0.innerHTML = "<b> 2." +(rowcount+1)+"</b>";

        var leftcell = row.insertCell(1);
        var el = document.createElement('textarea');
        el.name = 'latarBelakang' + (rowcount+1);
        el.rows = 5;
        el.cols = 150;
        leftcell.appendChild(el);

        // Add Hapus button
        var rightcell = row.insertCell(2);
        var e2 = document.createElement("INPUT");
        e2.setAttribute("type","button");
        e2.className="btn";
        e2.value ="Hapus";
        e2.setAttribute("id", ""+(rowcount));
        e2.onclick=function(){deleteRow2('',(e2.id));};
        rightcell.appendChild(e2);
        document.getElementById("rowCount2").value=rowcount+1;
    }
    
    function addRow3(tableid){
        var table = document.getElementById(tableid);
        var rowcount = table.rows.length;
        var row = table.insertRow(rowcount);
        var cell0 = row.insertCell(0);
        cell0.innerHTML = "<b>3." +(rowcount+1)+"</b>";

        var leftcell = row.insertCell(1);
        var el = document.createElement('textarea');
        el.name = 'butirButirPembangunan' + (rowcount+1);
        el.rows = 5;
        el.cols = 150;
        leftcell.appendChild(el);

        // Add Hapus button
        var rightcell = row.insertCell(2);
        var e2 = document.createElement("INPUT");
        e2.setAttribute("type","button");
        e2.className="btn";
        e2.value ="Hapus";
        e2.setAttribute("id", ""+(rowcount));
        e2.onclick=function(){deleteRow3('',(e2.id));};
        rightcell.appendChild(e2);
        document.getElementById("rowCount3").value=rowcount+1;
    }

    function addRow5(tableid){
        var table = document.getElementById(tableid);
        var rowcount = table.rows.length;
        var row = table.insertRow(rowcount);
        var cell0 = row.insertCell(0);
        cell0.innerHTML = "<b> 5." +(rowcount+1)+"</b>";

        var leftcell = row.insertCell(1);
        var el = document.createElement('textarea');
        el.name = 'huraianPentadbir' + (rowcount+1);
        el.rows = 5;
        el.cols = 150;
        leftcell.appendChild(el);

        // Add Hapus button
        var rightcell = row.insertCell(2);
        var e2 = document.createElement("INPUT");
        e2.setAttribute("type","button");
        e2.className="btn";
        e2.value ="Hapus";
        e2.setAttribute("id", ""+(rowcount));
        e2.onclick=function(){deleteRow5('',(e2.id));};
        rightcell.appendChild(e2);
        document.getElementById("rowCount5").value=rowcount+1;
    }
    
    function addRow6(tableid){
        var table = document.getElementById(tableid);
        var rowcount = table.rows.length;
        var row = table.insertRow(rowcount);
        var cell0 = row.insertCell(0);
        cell0.innerHTML = "<b> 6." +(rowcount+1)+"</b>";

        var leftcell = row.insertCell(1);
        var el = document.createElement('textarea');
        el.name = 'syorPentadbir' + (rowcount+1);
        el.rows = 5;
        el.cols = 150;
        leftcell.appendChild(el);

        // Add Hapus button
        var rightcell = row.insertCell(2);
        var e2 = document.createElement("INPUT");
        e2.setAttribute("type","button");
        e2.className="btn";
        e2.value ="Hapus";
        e2.setAttribute("id", ""+(rowcount));
        e2.onclick=function(){deleteRow6('',(e2.id));};
        rightcell.appendChild(e2);
        document.getElementById("rowCount6").value=rowcount+1;
    }
    
    function addRow7(tableid){
        var table = document.getElementById(tableid);
        var rowcount = table.rows.length;
        var row = table.insertRow(rowcount);
        var cell0 = row.insertCell(0);
        cell0.innerHTML = "<b> 7." +(rowcount+1)+"</b>";

        var leftcell = row.insertCell(1);
        var el = document.createElement('textarea');
        el.name = 'huraianTimbalan' + (rowcount+1);
        el.rows = 5;
        el.cols = 150;
        leftcell.appendChild(el);

        // Add Hapus button
        var rightcell = row.insertCell(2);
        var e2 = document.createElement("INPUT");
        e2.setAttribute("type","button");
        e2.className="btn";
        e2.value ="Hapus";
        e2.setAttribute("id", ""+(rowcount));
        e2.onclick=function(){deleteRow7('',(e2.id));};
        rightcell.appendChild(e2);
        document.getElementById("rowCount7").value=rowcount+1;
    }
    
    function addRow8(tableid){
        var table = document.getElementById(tableid);
        var rowcount = table.rows.length;
        var row = table.insertRow(rowcount);
        var cell0 = row.insertCell(0);
        cell0.innerHTML = "<b> 8." +(rowcount+1)+"</b>";

        var leftcell = row.insertCell(1);
        var el = document.createElement('textarea');
        el.name = 'syorTimbalan' + (rowcount+1);
        el.rows = 5;
        el.cols = 150;
        leftcell.appendChild(el);

        // Add Hapus button
        var rightcell = row.insertCell(2);
        var e2 = document.createElement("INPUT");
        e2.setAttribute("type","button");
        e2.className="btn";
        e2.value ="Hapus";
        e2.setAttribute("id", ""+(rowcount));
        e2.onclick=function(){deleteRow8('',(e2.id));};
        rightcell.appendChild(e2);
        document.getElementById("rowCount8").value=rowcount+1;
    }

    function deleteRow2(idKandungan,index)
    {
        document.getElementById('dataTable2').deleteRow(index);
        if(idKandungan == ''){
            var rowCount = document.getElementById("rowCount2").value;
            document.getElementById("rowCount2").value = rowCount-1;
            regenerateBill2('dataTable2');
        }else{
            var url = '${pageContext.request.contextPath}/pembangunan/kertasPertimbanganPTG?deleteSingle&idKandungan='
                +idKandungan;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
    }
        
    function deleteRow3(idKandungan,index)
    {
        document.getElementById('dataTable3').deleteRow(index);
        if(idKandungan == ''){
            var rowCount = document.getElementById("rowCount3").value;
            document.getElementById("rowCount3").value = rowCount-1;
            regenerateBill3('dataTable3');
        }else{
            var url = '${pageContext.request.contextPath}/pembangunan/kertasPertimbanganPTG?deleteSingle&idKandungan='
                +idKandungan;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
    }


    function deleteRow5(idKandungan,index)
    {
        document.getElementById('dataTable5').deleteRow(index);
        if(idKandungan == ''){
            var rowCount = document.getElementById("rowCount5").value;
            document.getElementById("rowCount5").value = rowCount-1;
            regenerateBill5('dataTable5');
        }else{
            var url = '${pageContext.request.contextPath}/pembangunan/kertasPertimbanganPTG?deleteSingle&idKandungan='
                +idKandungan;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
    }
        
    function deleteRow6(idKandungan,index)
    {
        document.getElementById('dataTable6').deleteRow(index);
        if(idKandungan == ''){
            var rowCount = document.getElementById("rowCount6").value;
            document.getElementById("rowCount6").value = rowCount-1;
            regenerateBill6('dataTable6');
        }else{
            var url = '${pageContext.request.contextPath}/pembangunan/kertasPertimbanganPTG?deleteSingle&idKandungan='
                +idKandungan;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
    }
        
    function deleteRow7(idKandungan,index)
    {
        document.getElementById('dataTable7').deleteRow(index);
        if(idKandungan == ''){
            var rowCount = document.getElementById("rowCount7").value;
            document.getElementById("rowCount7").value = rowCount-1;
            regenerateBill7('dataTable7');
        }else{
            var url = '${pageContext.request.contextPath}/pembangunan/kertasPertimbanganPTG?deleteSingle&idKandungan='
                +idKandungan;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
    }
        
    function deleteRow8(idKandungan,index)
    {
        document.getElementById('dataTable8').deleteRow(index);
        if(idKandungan == ''){
            var rowCount = document.getElementById("rowCount8").value;
            document.getElementById("rowCount8").value = rowCount-1;
            regenerateBill8('dataTable8');
        }else{
            var url = '${pageContext.request.contextPath}/pembangunan/kertasPertimbanganPTG?deleteSingle&idKandungan='
                +idKandungan;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
    }


    function regenerateBill2(tableid){
        try{
            var table = document.getElementById(tableid);
            var rowCount = table.rows.length;
            if(rowCount > 1){
                for(var i=0;i<rowCount;i++){
                    var a = table.rows[i].cells[0];
                    a.innerHTML= "<b>2."+(i+1)+"</b>";
                    if(i>0){
                        table.rows[i].cells[1].childNodes[0].name= 'latarBelakang'+(i+1);
                        table.rows[i].cells[2].childNodes[0].id= i;
                    }
                }
            }
        }catch(e){
            alert("Error in regenerateBill");
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
                        table.rows[i].cells[1].childNodes[0].name= 'butirButirPembangunan'+(i+1);
                        table.rows[i].cells[2].childNodes[0].id= i;
                    }
                }
            }
        }catch(e){
            alert("Error in regenerateBill");
        }
    }

    function regenerateBill5(tableid){
        try{
            var table = document.getElementById(tableid);
            var rowCount = table.rows.length;
            if(rowCount > 1){
                for(var i=0;i<rowCount;i++){
                    var a = table.rows[i].cells[0];
                    a.innerHTML= "<b>5."+(i+1)+"</b>";
                    if(i>0){
                        table.rows[i].cells[1].childNodes[0].name= 'huraianPentadbir'+(i+1);
                        table.rows[i].cells[2].childNodes[0].id= i;
                    }
                }
            }
        }catch(e){
            alert("Error in regenerateBill");
        }
    }
        
    function regenerateBill6(tableid){
        try{
            var table = document.getElementById(tableid);
            var rowCount = table.rows.length;
            if(rowCount > 1){
                for(var i=0;i<rowCount;i++){
                    var a = table.rows[i].cells[0];
                    a.innerHTML= "<b>6."+(i+1)+"</b>";
                    if(i>0){
                        table.rows[i].cells[1].childNodes[0].name= 'syorPentadbir'+(i+1);
                        table.rows[i].cells[2].childNodes[0].id= i;
                    }
                }
            }
        }catch(e){
            alert("Error in regenerateBill");
        }
    }
        
    function regenerateBill7(tableid){
        try{
            var table = document.getElementById(tableid);
            var rowCount = table.rows.length;
            if(rowCount > 1){
                for(var i=0;i<rowCount;i++){
                    var a = table.rows[i].cells[0];
                    a.innerHTML= "<b>7."+(i+1)+"</b>";
                    if(i>0){
                        table.rows[i].cells[1].childNodes[0].name= 'huraianTimbalan'+(i+1);
                        table.rows[i].cells[2].childNodes[0].id= i;
                    }
                }
            }
        }catch(e){
            alert("Error in regenerateBill");
        }
    }
        
    function regenerateBill8(tableid){
        try{
            var table = document.getElementById(tableid);
            var rowCount = table.rows.length;
            if(rowCount > 1){
                for(var i=0;i<rowCount;i++){
                    var a = table.rows[i].cells[0];
                    a.innerHTML= "<b>8."+(i+1)+"</b>";
                    if(i>0){
                        table.rows[i].cells[1].childNodes[0].name= 'syorTimbalan'+(i+1);
                        table.rows[i].cells[2].childNodes[0].id= i;
                    }
                }
            }
        }catch(e){
            alert("Error in regenerateBill");
        }
    }
        

</script>
<s:form beanclass="etanah.view.stripes.pembangunan.KertasPertimbanganPTGActionBean">
    <s:messages/>
    <s:errors/>

    <s:hidden name="kertasK.kertas.idKertas"/>
    <s:hidden name="btn"/>

    <%--     <div id="dialog-confirm" title="Kertas Pertimbangan PTG" style="display:none">
            <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 40px 0;"></span>
                <font size="3"> Adakah anda pasti anda mahu memadam rekod?</font>
            </p>
        </div>--%>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend></legend>
            <div class="content" align="center">
                <table border="0" width="80%" cellspacing="10">
                        <tr>
                            <td>
                                <table width="100%">
                                    <tr>
                                        <td align="left">${actionBean.permohonan.idPermohonan}</td>
                                    </tr>
                                    <c:if test="${actionBean.peng.kodCawangan.kod eq '00' && edit}">
                                    <tr>
                                        <td align="left"><s:text name="noRuj"  id="noRuj"/> &nbsp;</td>
                                    </tr>
                                    </c:if>
                                </table>
                            </td>
                        </tr>
                    <tr><td align="center"><b>(KERTAS UNTUK PERTIMBANGAN PENGARAH TANAH DAN GALIAN NEGERI SEMBILAN DARUL KHUSUS)</b></td></tr>
                    <%--<c:if test="${edit}">
                        <tr><td><b><font style="text-transform:uppercase">KERTAS UNTUK PERTIMBANGAN PENGARAH TANAH DAN GALIAN BAGI PERMOHONAN UNTUK ${actionBean.permohonan.kodUrusan.nama} BAGI ${actionBean.hakmilik.noLot} ${actionBean.hakmilik.bandarPekanMukim.nama}, ${actionBean.hakmilik.daerah.nama} MENGIKUT <s:text name="kanunTanah" size="20"/> KANUN TANAH NEGARA.</font></b></td></tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td><b><font style="text-transform:uppercase">KERTAS UNTUK PERTIMBANGAN PENGARAH TANAH DAN GALIAN BAGI PERMOHONAN UNTUK ${actionBean.permohonan.kodUrusan.nama} BAGI ${actionBean.hakmilik.noLot} ${actionBean.hakmilik.bandarPekanMukim.nama}, ${actionBean.hakmilik.daerah.nama} MENGIKUT ${actionBean.kanunTanah} KANUN TANAH NEGARA.</font></b></td></tr>
                    </c:if>--%>
                    <c:if test="${edit}">
                        <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:textarea name="tajuk" rows="5" cols="150" class="normal_text"/></td></tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td><b>${actionBean.tajuk}</b></td></tr>
                    </c:if>

                    <tr><td><b>1. TUJUAN</b></td></tr>
                    <c:if test="${edit}">
                        <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:textarea name="tujuan" rows="5" cols="150" class="normal_text"/></td></tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr>
                            <td><table cellspacing="10">
                                    <tr>
                                        <td valign="top"><b> 1.1 </b></td>
                                        <td valign="top"> ${actionBean.tujuan}</td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </c:if>

                    <tr><td><b>2. LATAR BELAKANG</b></td></tr>
                    <c:if test="${edit}">
                        <tr><td>
                                <table id ="dataTable2">
                                    <tr>
                                        <td><b>2.1 </b></td>
                                        <td><s:textarea name="latarBelakang1" id="latarBelakang1"  rows="5" cols="150" class="normal_text"></s:textarea></td>
                                        </tr>
                                    <c:set var="i" value="2" />
                                    <c:forEach items="${actionBean.senaraiKandungan2}" var="line" begin="1">
                                        <tr><td style="display:none">${line.idKandungan}</td>
                                            <td><b>2.${i}</b></td>
                                            <td><s:textarea name="latarBelakang${i}" id="latarBelakang${i}"  rows="5" cols="150" class="normal_text">${line.kandungan}</s:textarea></td>
                                            <c:if test="${actionBean.btn}">
                                                <td><s:button value="Hapus" id="${i-1}" class="btn" name="hapus" onclick="deleteRow2(${line.idKandungan},${i-1})"></s:button> </td>
                                            </c:if>
                                        </tr>
                                        <c:set var="i" value="${i+1}" />
                                    </c:forEach>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td><s:hidden name="rowCount2"  id="rowCount2"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <c:if test="${actionBean.btn}">
                                    <s:button class="btn" value="Tambah" name="add" onclick="addRow2('dataTable2')"/>&nbsp;
                                </c:if>
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td>
                                <table cellspacing="10">
                                    <c:set var="i" value="1" />
                                    <c:forEach items="${actionBean.senaraiKandungan2}" var="line">
                                        <tr><td valign="top"><b> 2.${i} </b></td>
                                            <td>${line.kandungan} </td>
                                        </tr>
                                        <c:set var="i" value="${i+1}" />
                                    </c:forEach>
                                </table>
                            </td>
                        </tr>
                    </c:if>
                    <tr><td><b>3. BUTIR-BUTIR PEMBANGUNAN YANG DICADANGKAN</b></td></tr>
                    <c:if test="${edit}">
                        <tr><td>
                                <table id ="dataTable3">
                                    <tr>
                                        <td><b>3.1 </b></td>
                                        <td><s:textarea name="butirButirPembangunan1" id="butirButirPembangunan1"  rows="5" cols="150" class="normal_text"></s:textarea></td>
                                        </tr>
                                    <c:set var="i" value="2" />
                                    <c:forEach items="${actionBean.senaraiKandungan3}" var="line" begin="1">
                                        <tr><td style="display:none">${line.idKandungan}</td>
                                            <td><b>3.${i}</b></td>
                                            <td><s:textarea name="butirButirPembangunan${i}" id="butirButirPembangunan${i}"  rows="5" cols="150" class="normal_text">${line.kandungan}</s:textarea></td>
                                            <c:if test="${actionBean.btn}">
                                                <td><s:button value="Hapus" id="${i-1}" class="btn" name="hapus" onclick="deleteRow3(${line.idKandungan},${i-1})"></s:button> </td>
                                            </c:if>
                                        </tr>
                                        <c:set var="i" value="${i+1}" />
                                    </c:forEach>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td><s:hidden name="rowCount3"  id="rowCount3"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <c:if test="${actionBean.btn}">
                                    <s:button class="btn" value="Tambah" name="add" onclick="addRow3('dataTable3')"/>&nbsp;
                                </c:if>
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td>
                                <table cellspacing="10">
                                    <c:set var="i" value="1" />
                                    <c:forEach items="${actionBean.senaraiKandungan3}" var="line">
                                        <tr><td valign="top"><b>3.${i} </b></td>
                                            <td>${line.kandungan} </td>
                                        </tr>
                                        <c:set var="i" value="${i+1}" />
                                    </c:forEach>
                                </table>
                            </td>
                        </tr>
                    </c:if>

                    <tr><td><b>4. BUTIR-BUTIR TANAH </b></td></tr>
                    <tr><td>
                            <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0" id="tbl1">
                                <display:column title="Bil" sortable="true">${tbl1_rowNum}</display:column>
                                <display:column title="Daerah" property="hakmilik.daerah.nama" class="daerah"/>
                                <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/ Mukim"/>
                                <display:column title="Nombor Lot/PT">
                                    ${tbl1.hakmilik.lot.nama} ${tbl1.hakmilik.noLot}
                                </display:column>
                                <display:column title="Jenis & No Hakmilik">
                                    ${tbl1.hakmilik.kodHakmilik.kod} ${tbl1.hakmilik.noHakmilik}
                                </display:column>
                                <display:column title="Luas"><fmt:formatNumber pattern="#,##0.0000" value="${tbl1.hakmilik.luas}"/>&nbsp;${tbl1.hakmilik.kodUnitLuas.nama}</display:column>
                                <display:column title="Kawasan Rizab">
                                    <c:if test="${tbl1.hakmilik.rizab.nama ne null}">${tbl1.hakmilik.rizab.nama}</c:if>
                                    <c:if test="${tbl1.hakmilik.rizab.nama eq null}">Tiada</c:if>
                                </display:column>
                                <%--<display:column title="Tuan Tanah/Syer" style="vertical-align:baseline">
                                    <c:set var="flag1" value="true" />
                                    <c:forEach items="${tbl1.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                                        <c:if test="${senarai.jenis.kod eq 'PM'}">
                                            <c:out value="${senarai.pihak.nama}"/><br/>
                                            <c:out value="${senarai.syerPembilang}/${senarai.syerPenyebut}"/><br/>
                                            <c:set var="flag1" value="false" />
                                        </c:if>
                                    </c:forEach>
                                    <c:if test="${flag1 eq 'true'}">
                                             Tiada
                                    </c:if>
                                </display:column>--%>
                                <display:column title="Tuan Tanah">
                                    <display:table class="tablecloth" name="${tbl1.hakmilik.senaraiPihakBerkepentingan}" cellpadding="0" cellspacing="0" id="line2">
                                        <display:column title="Nama">
                                            <c:if test="${line2.jenis.kod eq 'PM' && line2.aktif eq 'Y'}">
                                                ${line2.pihak.nama}<br>No.KP/Syarikat: ${line2.pihak.noPengenalan}
                                            </c:if>
                                        </display:column>
                                        <display:column title="Bahagian Dimiliki">
                                            <c:if test="${line2.jenis.kod eq 'PM' && line2.aktif eq 'Y'}">
                                                ${line2.syerPembilang}/${line2.syerPenyebut}
                                            </c:if>
                                        </display:column>
                                    </display:table>
                                </display:column>

                                <display:column title="Kategori Kegunaan Tanah">
                                    <c:if test="${tbl1.hakmilik.kategoriTanah.nama ne null}">${tbl1.hakmilik.kategoriTanah.nama}</c:if>
                                    <c:if test="${tbl1.hakmilik.kategoriTanah.nama eq null}">Tiada</c:if>
                                </display:column>
                                <display:column title="Cukai Tanah">
                                    <c:if test="${tbl1.hakmilik.cukai ne null}">
                                        RM <fmt:formatNumber pattern="#,##0.00" value="${actionBean.hakmilik.cukai}"/>
                                    </c:if>
                                    <c:if test="${tbl1.hakmilik.cukai eq null}">Tiada</c:if>
                                </display:column>
                                <display:column title="Syarat Nyata">
                                    <c:if test="${tbl1.hakmilik.syaratNyata.syarat ne null}">${tbl1.hakmilik.syaratNyata.syarat}</c:if>
                                    <c:if test="${tbl1.hakmilik.syaratNyata.syarat eq null}">Tiada</c:if>
                                </display:column>
                                <display:column title="Sekatan Kepentingan">
                                    <c:if test="${tbl1.hakmilik.sekatanKepentingan.sekatan ne null}">${tbl1.hakmilik.sekatanKepentingan.sekatan}</c:if>
                                    <c:if test="${tbl1.hakmilik.sekatanKepentingan.sekatan eq null}">Tiada</c:if>
                                </display:column>
                                <display:column title="Bebanan Berdaftar">
                                    <c:set var="flag" value="true" />
                                    <c:forEach items="${tbl1.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                                        <c:if test="${(((senarai.jenis.kod eq 'PG') ||(senarai.jenis.kod eq 'PJ') ||(senarai.jenis.kod eq 'PJK') ||
                                                      (senarai.jenis.kod eq 'PKA') ||(senarai.jenis.kod eq 'PKD') ||(senarai.jenis.kod eq 'PKL') ||
                                                      (senarai.jenis.kod eq 'PKS') ||(senarai.jenis.kod eq 'PMG') ||(senarai.jenis.kod eq 'PI') ||
                                                      (senarai.jenis.kod eq 'PY')) && senarai.jenis.aktif eq 'Y') }">
                                            <c:out value="${senarai.jenis.nama}"/><br/><br/>
                                            <c:set var="flag" value="false" />
                                        </c:if>
                                    </c:forEach>
                                    <c:if test="${flag eq 'true'}">
                                        Tiada
                                    </c:if>
                                </display:column>
                            </display:table>
                        </td></tr>
                    <tr><td><b><font style="text-transform: uppercase">5. HURAIAN PENTADBIR TANAH ${actionBean.pejTanah}&nbsp;</font></b></td></tr>
                    <c:if test="${edit}">
                        <tr><td>
                                <table id ="dataTable5">
                                    <tr>
                                        <td><b>5.1 </b></td>
                                        <td><s:textarea name="huraianPentadbir1" id="huraianPentadbir1"  rows="5" cols="150" class="normal_text"></s:textarea></td>
                                        </tr>
                                    <c:set var="i" value="2" />
                                    <c:forEach items="${actionBean.senaraiKandungan5}" var="line" begin="1">
                                        <tr><td style="display:none">${line.idKandungan}</td>
                                            <td><b>5.${i}</b></td>
                                            <td><s:textarea name="huraianPentadbir${i}" id="huraianPentadbir${i}"  rows="5" cols="150" class="normal_text">${line.kandungan}</s:textarea></td>
                                            <c:if test="${actionBean.btn}">
                                                <td><s:button value="Hapus" id="${i-1}" class="btn" name="tambah" onclick="deleteRow5(${line.idKandungan},${i-1})"></s:button> </td>
                                            </c:if>
                                        </tr>
                                        <c:set var="i" value="${i+1}" />
                                    </c:forEach>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td><s:hidden name="rowCount5"  id="rowCount5"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <c:if test="${actionBean.btn}">
                                    <s:button class="btn" value="Tambah" name="add" onclick="addRow5('dataTable5')"/>&nbsp;
                                    <%--<s:button name="hapus" value="Hapus" class="btn" onclick="deleteRow5()" />--%>
                                </c:if>
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td>
                                <table cellspacing="10">
                                    <c:set var="i" value="1" />
                                    <c:forEach items="${actionBean.senaraiKandungan5}" var="line">
                                        <tr><td valign="top"><b> 5.${i} </b></td>
                                            <td>${line.kandungan} </td>
                                        </tr>
                                        <c:set var="i" value="${i+1}" />
                                    </c:forEach>
                                </table>
                            </td>
                        </tr>
                    </c:if>

                    <tr><td><b><font style="text-transform: uppercase">6. SYOR PENTADBIR TANAH ${actionBean.pejTanah}&nbsp;</font></b></td></tr>
                    <c:if test="${edit}">
                        <tr>
                            <td>
                                <table id ="dataTable6">
                                    <tr>
                                        <td><b>6.1 </b></td>
                                        <td><s:textarea name="syorPentadbir1" id="syorPentadbir1"  rows="5" cols="150" class="normal_text"></s:textarea></td>
                                        </tr>
                                    <c:set var="i" value="2" />
                                    <c:forEach items="${actionBean.senaraiKandungan6}" var="line" begin="1">
                                        <tr><td style="display:none">${line.idKandungan}</td>
                                            <td><b>6.${i}</b></td>
                                            <td><s:textarea name="syorPentadbir${i}" id="syorPentadbir${i}"  rows="5" cols="150" class="normal_text">${line.kandungan}</s:textarea></td>
                                            <c:if test="${actionBean.btn}">
                                                <td><s:button value="Hapus" id="${i-1}" class="btn" name="tambah" onclick="deleteRow6(${line.idKandungan},${i-1})"></s:button> </td>
                                            </c:if>
                                        </tr>
                                        <c:set var="i" value="${i+1}" />
                                    </c:forEach>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td><s:hidden name="rowCount6"  id="rowCount6"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <c:if test="${actionBean.btn}">
                                    <s:button class="btn" value="Tambah" name="add" onclick="addRow6('dataTable6')"/>&nbsp;
                                    <%--<s:button name="hapus" value="Hapus" class="btn" onclick="deleteRow5()" />--%>
                                </c:if>
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td>
                                <table cellspacing="10">
                                    <c:set var="i" value="1" />
                                    <c:forEach items="${actionBean.senaraiKandungan6}" var="line">
                                        <tr><td valign="top"><b> 6.${i} </b></td>
                                            <td>${line.kandungan} </td>
                                        </tr>
                                        <c:set var="i" value="${i+1}" />
                                    </c:forEach>
                                </table>
                            </td>
                        </tr>
                    </c:if>
                    <!-- PTD can view only -->
                    <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                        <c:set var="i" value="6"/>
                        <c:set var="i" value="${i+1}"/>
                        <tr><td><b><font style="text-transform: uppercase">${i}.HURAIAN TIMBALAN / PENOLONG PENGARAH TANAH DAN GALIAN NEGERI SEMBILAN DARUL KHUSUS&nbsp;</font></b></td></tr>
                        <c:if test="${!edit}">
                            <!--<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<//s:textarea name="huraianTimbalan" rows="5" cols="150" class="normal_text"/></td></tr>-->
                            <tr><td>
                                    <table id ="dataTable7">
                                        <tr>
                                            <td><b>7.1 </b></td>
                                            <td><s:textarea name="huraianTimbalan1" id="huraianTimbalan1"  rows="5" cols="150" class="normal_text"></s:textarea></td>
                                            </tr>
                                        <c:set var="a" value="2" />
                                        <c:forEach items="${actionBean.senaraiKandungan7}" var="line" begin="1">
                                            <tr><td style="display:none">${line.idKandungan}</td>
                                                <td><b>7.${a}</b></td>
                                                <td><s:textarea name="huraianTimbalan${a}" id="huraianTimbalan${a}"  rows="5" cols="150" class="normal_text">${line.kandungan}</s:textarea></td>
                                                <c:if test="${actionBean.btn}">
                                                    <td><s:button value="Hapus" id="${a-1}" class="btn" name="tambah" onclick="deleteRow7(${line.idKandungan},${a-1})"></s:button> </td>
                                                </c:if>
                                            </tr>
                                            <c:set var="a" value="${a+1}" />
                                        </c:forEach>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td><s:hidden name="rowCount7"  id="rowCount7"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <c:if test="${actionBean.btn}">
                                        <s:button class="btn" value="Tambah" name="add" onclick="addRow7('dataTable7')"/>&nbsp;
                                    </c:if>
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${edit}">
                            <tr><td>
                                    <table cellspacing="10">
                                        <c:set var="i" value="1" />
                                        <c:forEach items="${actionBean.senaraiKandungan7}" var="line">
                                            <tr><td valign="top"><b> 7.${i} </b></td>
                                                <td>${line.kandungan} </td>
                                            </tr>
                                            <c:set var="i" value="${i+1}" />
                                        </c:forEach>
                                    </table>
                                </td>
                            </tr>
                        </c:if>

                        <c:set var="i" value="7"/>
                        <c:set var="i" value="${i+1}"/>
                        <tr><td><b><font style="text-transform: uppercase">${i}. SYOR TIMBALAN / PENOLONG PENGARAH TANAH DAN GALIAN NEGERI SEMBILAN DARUL KHUSUS&nbsp;</font></b></td></tr>

                        <!-------baru-------->
                        <c:if test="${!edit}">
                            <tr>
                                <td>
                                    <table id ="dataTable8">
                                        <tr>
                                            <td><b>8.1 </b></td>
                                            <td><s:textarea name="syorTimbalan1" id="syorTimbalan1"  rows="5" cols="150" class="normal_text"></s:textarea></td>
                                            </tr>
                                        <c:set var="i" value="2" />
                                        <c:forEach items="${actionBean.senaraiKandungan8}" var="line" begin="1">
                                            <tr><td style="display:none">${line.idKandungan}</td>
                                                <td><b>8.${i}</b></td>
                                                <td><s:textarea name="syorTimbalan${i}" id="syorTimbalan${i}"  rows="5" cols="150" class="normal_text">${line.kandungan}</s:textarea></td>
                                                <c:if test="${actionBean.btn}">
                                                    <td><s:button value="Hapus" id="${i-1}" class="btn" name="tambah" onclick="deleteRow8(${line.idKandungan},${i-1})"></s:button> </td>
                                                </c:if>
                                            </tr>
                                            <c:set var="i" value="${i+1}" />
                                        </c:forEach>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td><s:hidden name="rowCount8"  id="rowCount8"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <c:if test="${actionBean.btn}">
                                        <s:button class="btn" value="Tambah" name="add" onclick="addRow8('dataTable8')"/>&nbsp;
                                        <%--<s:button name="hapus" value="Hapus" class="btn" onclick="deleteRow5()" />--%>
                                    </c:if>
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${edit}">
                            <tr><td>
                                    <table cellspacing="10">
                                        <c:set var="i" value="1" />
                                        <c:forEach items="${actionBean.senaraiKandungan8}" var="line">
                                            <tr><td valign="top"><b> 8.${i} </b></td>
                                                <td>${line.kandungan} </td>
                                            </tr>
                                            <c:set var="i" value="${i+1}" />
                                        </c:forEach>
                                    </table>
                                </td>
                            </tr>
                        </c:if>

                        <c:set var="i" value="${i+1}"/>
                        <tr>
                            <td><b>9. KEPUTUSAN</b></td>
                        </tr>
                        <tr>
                            <td>
                                <table>
                                    <tr>
                                        <td valign="middle"><b> 9.1 </b></td>
                                        <td><s:textarea name="ulasanPTG" rows="5" cols="150" class="normal_text"/></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </c:if>

                    <!-- PTG can modify -->
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                        <c:set var="i" value="6"/>
                        <c:set var="i" value="${i+1}"/>
                        <tr><td><b><font style="text-transform: uppercase">${i}.HURAIAN TIMBALAN / PENOLONG PENGARAH TANAH DAN GALIAN NEGERI SEMBILAN DARUL KHUSUS&nbsp;</font></b></td></tr>
                        <c:if test="${edit}">
                            <!--<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<//s:textarea name="huraianTimbalan" rows="5" cols="150" class="normal_text"/></td></tr>-->
                            <tr><td>
                                    <table id ="dataTable7">
                                        <tr>
                                            <td><b>7.1 </b></td>
                                            <td><s:textarea name="huraianTimbalan1" id="huraianTimbalan1"  rows="5" cols="150" class="normal_text"></s:textarea></td>
                                            </tr>
                                        <c:set var="a" value="2" />
                                        <c:forEach items="${actionBean.senaraiKandungan7}" var="line" begin="1">
                                            <tr><td style="display:none">${line.idKandungan}</td>
                                                <td><b>7.${a}</b></td>
                                                <td><s:textarea name="huraianTimbalan${a}" id="huraianTimbalan${a}"  rows="5" cols="150" class="normal_text">${line.kandungan}</s:textarea></td>
                                                <c:if test="${actionBean.btn}">
                                                    <td><s:button value="Hapus" id="${a-1}" class="btn" name="tambah" onclick="deleteRow7(${line.idKandungan},${a-1})"></s:button> </td>
                                                </c:if>
                                            </tr>
                                            <c:set var="a" value="${a+1}" />
                                        </c:forEach>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <s:hidden name="rowCount7"  id="rowCount7"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <c:if test="${actionBean.btn}">
                                        <s:button class="btn" value="Tambah" name="add" onclick="addRow7('dataTable7')"/>&nbsp;
                                    </c:if>
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${!edit}">
                            <tr><td>
                                    <table cellspacing="10">
                                        <c:set var="i" value="1" />
                                        <c:forEach items="${actionBean.senaraiKandungan7}" var="line">
                                            <tr><td valign="top"><b> 7.${i} </b></td>
                                                <td>${line.kandungan} </td>
                                            </tr>
                                            <c:set var="i" value="${i+1}" />
                                        </c:forEach>
                                    </table>
                                </td>
                            </tr>
                        </c:if>

                        <c:set var="i" value="7"/>
                        <c:set var="i" value="${i+1}"/>
                        <tr><td><b><font style="text-transform: uppercase">${i}. SYOR TIMBALAN / PENOLONG PENGARAH TANAH DAN GALIAN NEGERI SEMBILAN DARUL KHUSUS&nbsp;</font></b></td></tr>
                        <c:if test="${edit}">
                            <tr>
                                <td>
                                    <table id ="dataTable8">
                                        <tr>
                                            <td><b>8.1 </b></td>
                                            <td><s:textarea name="syorTimbalan1" id="syorTimbalan1"  rows="5" cols="150" class="normal_text"></s:textarea></td>
                                            </tr>
                                        <c:set var="i" value="2" />
                                        <c:forEach items="${actionBean.senaraiKandungan8}" var="line" begin="1">
                                            <tr><td style="display:none">${line.idKandungan}</td>
                                                <td><b>5.${i}</b></td>
                                                <td><s:textarea name="syorTimbalan${i}" id="syorTimbalan${i}"  rows="5" cols="150" class="normal_text">${line.kandungan}</s:textarea></td>
                                                <c:if test="${actionBean.btn}">
                                                    <td><s:button value="Hapus" id="${i-1}" class="btn" name="tambah" onclick="deleteRow8(${line.idKandungan},${i-1})"></s:button> </td>
                                                </c:if>
                                            </tr>
                                            <c:set var="i" value="${i+1}" />
                                        </c:forEach>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td><s:hidden name="rowCount8"  id="rowCount8"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <c:if test="${actionBean.btn}">
                                        <s:button class="btn" value="Tambah" name="add" onclick="addRow8('dataTable8')"/>&nbsp;
                                        <%--<s:button name="hapus" value="Hapus" class="btn" onclick="deleteRow5()" />--%>
                                    </c:if>
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${!edit}">
                            <tr><td>
                                    <table cellspacing="10">
                                        <c:set var="i" value="1" />
                                        <c:forEach items="${actionBean.senaraiKandungan8}" var="line">
                                            <tr><td valign="top"><b> 8.${i} </b></td>
                                                <td>${line.kandungan} </td>
                                            </tr>
                                            <c:set var="i" value="${i+1}" />
                                        </c:forEach>
                                    </table>
                                </td>
                            </tr>
                        </c:if>
                        <c:set var="i" value="${i+1}"/>
                        <tr>
                            <td><b>9. KEPUTUSAN</b></td>
                        </tr>
                        <tr>
                            <td>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:textarea name="ulasanPTG" rows="5" cols="150" class="normal_text"/></td>
                                        </tr>
                                    </c:if>
                                    </table>
                                    </div>
                                    <c:if test="${edit}">
                                        <br>
                                        <p>
                                            <label>&nbsp;</label>
                                            <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                                        </p>
                                    </c:if>
                                    </fieldset>
                                    </div>
                                </s:form>
