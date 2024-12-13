<%-- 
    Document   : kertasRingkas_PTG
    Created on : Jun 29, 2010, 9:58:34 AM
    Author     : Imran Khan
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%--<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />--%>

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
    // Row Latar Belakang
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
        function deleteRow2(idKandungan,index)
    {
        document.getElementById('dataTable2').deleteRow(index);
        if(idKandungan == ''){
            var rowCount = document.getElementById("rowCount2").value;
            document.getElementById("rowCount2").value = rowCount-1;
            regenerateBill2('dataTable2');
        }else{
            var url = '${pageContext.request.contextPath}/pembangunan/KertasRingkasPTG?deleteSingle&idKandungan='
                +idKandungan;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
    }
    
    </script>

<script type="text/javascript">
    // Row Latar Belakang
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
        el.name = 'huraianPtg' + (rowcount+1);
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
        el.name = 'syorPtg' + (rowcount+1);
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
            var url = '${pageContext.request.contextPath}/pembangunan/KertasRingkasPTG?deleteSingle&idKandungan='
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
            var url = '${pageContext.request.contextPath}/pembangunan/KertasRingkasPTG?deleteSingle&idKandungan='
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
            alert(rowCount);
            document.getElementById("rowCount6").value = rowCount-1;
            regenerateBill6('dataTable6');
        }else{
            var url = '${pageContext.request.contextPath}/pembangunan/KertasRingkasPTG?deleteSingle&idKandungan='
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
            var url = '${pageContext.request.contextPath}/pembangunan/KertasRingkasPTG?deleteSingle&idKandungan='
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
            var url = '${pageContext.request.contextPath}/pembangunan/KertasRingkasPTG?deleteSingle&idKandungan='
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
                        table.rows[i].cells[1].childNodes[0].name= 'huraianPtg'+(i+1);
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
                        table.rows[i].cells[1].childNodes[0].name= 'syorPtg'+(i+1);
                        table.rows[i].cells[2].childNodes[0].id= i;
                    }
                }
            }
        }catch(e){
            alert("Error in regenerateBill");
        }
    }
</script>

<s:form beanclass="etanah.view.stripes.pembangunan.KertasRingkasPTGActionBean">
    <s:messages/>
    <s:errors/>
    <s:hidden name="kandunganK.kertas.idKertas"/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend></legend>
            <%-- <c:set scope="request" var="senaraiPengarah"  value="${actionBean.listPengarah}"/>
            <c:set scope="request" var="senaraiPemohon"  value="${actionBean.listPemohon}"/> --%>

            <div class="content" align="center">
                <table border="0" width="80%">
                    <tr><td align="center"><b>(KERTAS PERTIMBANGAN PENGARAH TANAH DAN GALIAN NEGERI SEMBILAN DARUL KHUSUS)</b></td></tr><br>
                    <c:if test="${edit}">
                        <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:textarea name="tajuk" rows="5" cols="150"/></td></tr><br>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td><b><font style="text-transform:none">${actionBean.tajuk} &nbsp;</font></b></td></tr><br>
                    </c:if>
                                
                    <tr><td><b>1. TUJUAN</b></td></tr>
                    <c:if test="${edit}">
                        <tr><td>
                                <table>
                                    <tr>
                                        <td><b>1.1 </b></td>
                                        <td><s:textarea rows="5" cols="150" name="tujuan" class="normal_text"/></td>
                                    </tr>
                                </table>
                        </tr></td>       
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td>
                                <table cellspacing="10">
                                    <tr>
                                        <td><b>1.1 </b></td>
                                        <td>${actionBean.tujuan}</td>
                                    </tr>
                                </table>
                        </td></tr>
                    </c:if>
                        
                    <tr><td><b>2. LATAR BELAKANG</b></td></tr>
     <%--               <c:if test="${edit}">
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
                        <s:hidden name="rowCount2"  id="rowCount2"/>
                        <tr><td>
                                <table cellspacing="10">
                                    <c:set var="i" value="1" />
                                    <c:forEach items="${actionBean.senaraiKandungan2}" var="line">
                                        <tr><td valign="top"><b> 2.${i} </b></td>
                                            <td><font style="text-transform:uppercase">${line.kandungan}</font></td>
                                        </tr>
                                        <c:set var="i" value="${i+1}" />
                                    </c:forEach>
                                </table>
                            </td>
                        </tr>
                    </c:if>
                        
     --%>
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
                        
                        
                    <tr><td><b>3. ASAS - ASAS</b></td></tr>
                    <c:if test="${edit}">
                        <tr><td>
                                <table>
                                    <tr>
                                        <td><b>3.1 </b></td>
                                        <td><s:textarea rows="5" cols="150" name="asas" class="normal_text"/></td>
                                    </tr>
                                </table>
                        </td></tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td>
                                <table cellspacing="10">
                                    <tr>
                                        <td><b>3.1 </b></td>
                                        <td><font style=" ">${actionBean.asas}</font></td>
                                    </tr>
                                </table>
                                
                        </td></tr>
                    </c:if>
                    
                   <tr><td><b><font style="text-transform: uppercase">4. HURAIAN PENTADBIR TANAH ${actionBean.pejTanah}&nbsp;</font></b></td></tr>
                    <c:if test="${edit}">
                        <tr><td>
                                <table id ="dataTable5">
                                    <tr>
                                        <td><b>4.1 </b></td>
                                        <td><s:textarea name="huraianPentadbir1" id="huraianPentadbir1"  rows="5" cols="150" class="normal_text"></s:textarea></td>
                                    </tr>
                                    <c:set var="i" value="2" />
                                    <c:forEach items="${actionBean.senaraiKandungan5}" var="line" begin="1">
                                        <tr><td style="display:none">${line.idKandungan}</td>
                                            <td><b>4.${i}</b></td>
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
                                </c:if>
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <s:hidden name="rowCount5"  id="rowCount5"/>
                        <tr><td>
                                <table cellspacing="10">
                                    <c:set var="i" value="1" />
                                    <c:forEach items="${actionBean.senaraiKandungan5}" var="line">
                                        <tr><td valign="top"><b> 4.${i} </b></td>
                                            <td><font style=" ">${line.kandungan}</font></td>
                                        </tr>
                                        <c:set var="i" value="${i+1}" />
                                    </c:forEach>
                                </table>
                            </td>
                        </tr>
                    </c:if>

                    <tr><td><b><font style="text-transform: uppercase">5. SYOR PENTADBIR TANAH ${actionBean.pejTanah}&nbsp;</font></b></td></tr>
                    <c:if test="${edit}">
                        <tr>
                            <td>
                                <table id ="dataTable6">
                                    <tr>
                                        <td><b>5.1 </b></td>
                                        <td><s:textarea name="syorPentadbir1" id="syorPentadbir1"  rows="5" cols="150" class="normal_text"></s:textarea></td>
                                    </tr>
                                    <c:set var="i" value="2" />
                                    <c:forEach items="${actionBean.senaraiKandungan6}" var="line" begin="1">
                                        <tr><td style="display:none">${line.idKandungan}</td>
                                            <td><b>5.${i}</b></td>
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
                                </c:if>
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <s:hidden name="rowCount6"  id="rowCount6"/>
                        <tr><td>
                                <table cellspacing="10">
                                    <c:set var="i" value="1" />
                                    <c:forEach items="${actionBean.senaraiKandungan6}" var="line">
                                        <tr><td valign="top"><b> 5.${i} </b></td>
                                            <td><font style="text-transform:none">${line.kandungan}</font></td>
                                        </tr>
                                        <c:set var="i" value="${i+1}" />
                                    </c:forEach>
                                </table>
                            </td>
                        </tr>
                    </c:if>
                   <tr><td><b><font style="text-transform: uppercase">6. PENUTUP</font></b></td></tr>
                    <c:if test="${edit}">
                        <tr><td>
                                <table>
                                    <tr>
                                        <td><b><font style="text-transform:uppercase">6.1</font></b></td>
                                        <td><s:textarea name="penutup" rows="5" cols="150" class="normal_text"/></td>
                                    </tr>
                                </table>
                            </td></tr>
                     </c:if>
                     <c:if test="${!edit}">
                        <tr><td>
                                <table cellspacing="10">
                                    <tr>
                                        <td><b><font style="text-transform:uppercase">6.1</font></b></td>
                                        <td><font style="text-transform:none">${actionBean.penutup}&nbsp;</font></td>
                                    </tr>
                                </table>
                        </td></tr>
                   </c:if>    
                    <tr><td><b><font style="text-transform: uppercase">7. HURAIAN PENGARAH TANAH DAN GALIAN NEGERI SEMBILAN DARUL KHUSUS</font></b></td></tr>
                    <c:if test="${edit1}">
                        <tr>
                            <td>
                                <table id ="dataTable7">
                                    <tr>
                                        <td><b>7.1 </b></td>
                                        <td><s:textarea name="huraianPtg1" id="huraianPtg1"  rows="5" cols="150" class="normal_text"></s:textarea></td>
                                    </tr>
                                    <c:set var="i" value="2" />
                                    <c:forEach items="${actionBean.senaraiKandungan7}" var="line" begin="1">
                                        <tr><td style="display:none">${line.idKandungan}</td>
                                            <td><b>7.${i}</b></td>
                                            <td><s:textarea name="huraianPtg${i}" id="huraianPtg${i}"  rows="5" cols="150" class="normal_text">${line.kandungan}</s:textarea></td>
                                            <c:if test="${actionBean.btn}">
                                                <td><s:button value="Hapus" id="${i-1}" class="btn" name="tambah" onclick="deleteRow7(${line.idKandungan},${i-1})"></s:button> </td>
                                            </c:if>
                                        </tr>
                                        <c:set var="i" value="${i+1}" />
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
                    <c:if test="${!edit1}">
                        <s:hidden name="rowCount7"  id="rowCount7"/>
                        <tr><td>
                                <table cellspacing="10">
                                    <c:set var="i" value="1" />
                                    <c:forEach items="${actionBean.senaraiKandungan7}" var="line">
                                        <tr><td valign="top"><b>7.${i} </b></td>
                                            <td><font style="text-transform:none">${line.kandungan}</font></td>
                                        </tr>
                                        <c:set var="i" value="${i+1}" />
                                    </c:forEach>
                                </table>
                            </td>
                        </tr>
                    </c:if>
                        
                    <tr><td><b><font style="text-transform: uppercase">8. SYOR PENGARAH TANAH DAN GALIAN NEGERI SEMBILAN DARUL KHUSUS</font></b></td></tr>
                    <c:if test="${edit1}">
                        <tr>
                            <td>
                                <table id ="dataTable8">
                                    <tr>
                                        <td><b>8.1 </b></td>
                                        <td><s:textarea name="syorPtg1" id="syorPtg1"  rows="5" cols="150" class="normal_text"></s:textarea></td>
                                    </tr>
                                    <c:set var="i" value="2" />
                                    <c:forEach items="${actionBean.senaraiKandungan8}" var="line" begin="1">
                                        <tr><td style="display:none">${line.idKandungan}</td>
                                            <td><b>8.${i}</b></td>
                                            <td><s:textarea name="syorPtg${i}" id="syorPtg${i}"  rows="5" cols="150" class="normal_text">${line.kandungan}</s:textarea></td>
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
                                </c:if>
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${!edit1}">
                        <s:hidden name="rowCount8"  id="rowCount8"/>
                        <tr><td>
                                <table cellspacing="10">
                                    <c:set var="i" value="1" />
                                    <c:forEach items="${actionBean.senaraiKandungan8}" var="line">
                                        <tr><td valign="top"><b>8.${i} </b></td>
                                            <td><font style="text-transform:none">${line.kandungan}</font></td>
                                        </tr>
                                        <c:set var="i" value="${i+1}" />
                                    </c:forEach>
                                </table>
                            </td>
                        </tr>
                    </c:if> 
                     
                    
                    <tr><td><b><font style="text-transform: uppercase">9. KEPUTUSAN</font></b></td></tr>
                    <c:if test="${edit1}">
                        <tr><td>
                                <table>
                                    <tr>
                                        <td><b><font style="text-transform:none">9.1</font></b></td>
                                        <td><s:textarea name="ulasanPTG" rows="5" cols="150" class="normal_text"/></td>
                                    </tr>
                                </table>
                            </td></tr>
                     </c:if>
                     <c:if test="${!edit1}">
                        <tr><td>
                                <table cellspacing="10">
                                    <tr>
                                        <td><b><font style="text-transform:uppercase">9.1</font></b></td>
                                        <td><font style="text-transform:none">${actionBean.ulasanPTG}&nbsp;</font></td>
                                    </tr>
                                </table>
                        </td></tr>
                   </c:if>
                </table>
                <c:if test="${actionBean.btn}">
                    <p align="center">
                        <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </p>
                </c:if>
            </div>
        </fieldset>
    </div>
</s:form>
