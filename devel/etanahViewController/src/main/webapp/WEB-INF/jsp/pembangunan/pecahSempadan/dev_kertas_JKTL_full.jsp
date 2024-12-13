<%-- 
    Document   : dev_kertas_JKTL_full
    Created on : Apr 13, 2010, 10:10:56 AM
    Author     : nursyazwani
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>

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
    function addRow(tableid){
        var table = document.getElementById(tableid);
        var rowcount = table.rows.length;

        if(rowcount < 12){
            var row = table.insertRow(rowcount);

            var leftcell = row.insertCell(0);
            var bil = document.createTextNode(rowcount);
            leftcell.appendChild(bil);

            var middlecell = row.insertCell(1);
            var sel = document.createElement('select');
            sel.name = 'subtajuk' + rowcount;
            sel.style.width = '400px';
            sel.options[0] = new Option('Sila Pilih','');
            sel.options[1] = new Option('Jabatan Kebajikan Masyarakat, Negeri Sembilan','Jabatan Kebajikan Masyarakat, Negeri Sembilan');
            sel.options[2] = new Option('Jabatan Kerja Raya, Negeri Sembilan','Jabatan Kerja Raya, Negeri Sembilan');
            sel.options[3] = new Option('Jabatan Pengairan dan Saliran, Negeri Sembilan','Jabatan Pengairan dan Saliran, Negeri Sembilan');
            sel.options[4] = new Option('Jabatan Perancang Bandar dan Desa, Negeri Sembilan','Jabatan Perancang Bandar dan Desa, Negeri Sembilan');
            sel.options[5] = new Option('Jabatan Tenaga Kerja, Negeri Sembilan','Jabatan Tenaga Kerja, Negeri Sembilan');
            sel.options[6] = new Option('Jabatan Kesihatan, Negeri Sembilan','Jabatan Kesihatan, Negeri Sembilan');
            sel.options[7] = new Option('Jabatan Pertanian, Negeri Sembilan','Jabatan Pertanian, Negeri Sembilan');
            sel.options[8] = new Option('Jabatan Alam Sekitar, Negeri Sembilan','Jabatan Alam Sekitar, Negeri Sembilan');
            sel.options[9] = new Option('Tenaga Nasional Berhad (TNB)','Tenaga Nasional Berhad (TNB)');
            sel.options[10] = new Option('Syarikat Air Negeri Sembilan','Syarikat Air Negeri Sembilan');
            sel.options[11] = new Option('Petronas','Petronas');
            middlecell.appendChild(sel);

            var rightcell = row.insertCell(2);
            var el = document.createElement('textarea');
            el.name = 'ulasan' + rowcount;
            el.rows = 3;
            el.cols = 90;
            el.style.textTransform = 'uppercase';
            rightcell.appendChild(el);
        } else{
            alert('Semua Jabatan Teknikal telah membuat ulasan.');
            $("#huraianptd").focus();
            return true;
        }

    }
    function addHuraian(tableid){
        var table = document.getElementById(tableid);
        var rowcount = table.rows.length;

        if(rowcount < 5){
            var row = table.insertRow(rowcount);

            var leftcell = row.insertCell(0);
            var el = document.createElement('textarea');
            el.name = 'huraianPentadbir' + rowcount;
            el.rows = 5;
            el.cols = 150;
            el.style.textTransform = 'uppercase';
            leftcell.appendChild(el);
        } else{
            alert('Huraian Pentadbir Tanah telah melebihi had yang dibenarkan.');
            $("#syorptd").focus();
            return true;
        }

    }


    function addRow5(tableid){
        var table = document.getElementById(tableid);
        var rowcount = table.rows.length;
        var row = table.insertRow(rowcount);
        var cell0 = row.insertCell(0);
        cell0.innerHTML = "<b> 4." +(rowcount+1)+"</b>";

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
        cell0.innerHTML = "<b>5." +(rowcount+1)+"</b>";

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
        cell0.innerHTML = "<b> 6." +(rowcount+1)+"</b>";

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
        cell0.innerHTML = "<b> 7." +(rowcount+1)+"</b>";

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


    function deleteRow5(idKandungan,index)
    {
        document.getElementById('dataTable5').deleteRow(index);
        if(idKandungan == '' && index > 0){
            var rowCount = document.getElementById("rowCount5").value;
            document.getElementById("rowCount5").value = rowCount-1;
            regenerateBill5('dataTable5');
        }else{
            var url = '${pageContext.request.contextPath}/pembangunan/kertasJKTL?deleteSingle&idKandungan='+idKandungan;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }       
    }
        
    function deleteRow6(idKandungan,index)
    {
        document.getElementById('dataTable6').deleteRow(index);
        if(idKandungan == '' && index > 0){
            var rowCount = document.getElementById("rowCount6").value;
            document.getElementById("rowCount6").value = rowCount-1;
            regenerateBill6('dataTable6');
        }else{
            var url = '${pageContext.request.contextPath}/pembangunan/kertasJKTL?deleteSingle&idKandungan='+idKandungan;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }       
    }


    function regenerateBill5(tableid){
        try{
            var table = document.getElementById(tableid);
            var rowCount = table.rows.length;            
            if(rowCount > 1){
                for(var i=0;i<rowCount;i++){                    
                    var a = table.rows[i].cells[0];
                    a.innerHTML= "<b>4."+(i+1)+"</b>";
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
                    a.innerHTML= "<b>5."+(i+1)+"</b>";
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


    function deleteRow7(idKandungan,index)
    {
        document.getElementById('dataTable7').deleteRow(index);
        if(idKandungan == '' && index > 0){
            var rowCount = document.getElementById("rowCount7").value;
            document.getElementById("rowCount7").value = rowCount-1;
            regenerateBill7('dataTable7');
        }else{
            var url = '${pageContext.request.contextPath}/pembangunan/kertasJKTL?deleteSingle&idKandungan='+idKandungan;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
        regenerateBill7('dataTable7');
    }


    function regenerateBill7(tableid){
        try{
            var table = document.getElementById(tableid);
            var rowCount = table.rows.length;
            if(rowCount > 1){
                for(var i=0;i<rowCount;i++){
                    var a = table.rows[i].cells[0];
                    a.innerHTML= "<b>6."+(i+1)+"</b>";
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



    function deleteRow8(idKandungan,index)
    {
        document.getElementById('dataTable8').deleteRow(index);
        if(idKandungan == '' && index > 0){
            var rowCount = document.getElementById("rowCount8").value;
            document.getElementById("rowCount8").value = rowCount-1;
            regenerateBill8('dataTable8');
        }else{
            var url = '${pageContext.request.contextPath}/pembangunan/kertasJKTL?deleteSingle&idKandungan='+idKandungan;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
        regenerateBill8('dataTable8');
    }


    function regenerateBill8(tableid){
        try{
            var table = document.getElementById(tableid);
            var rowCount = table.rows.length;
            if(rowCount > 1){
                for(var i=0;i<rowCount;i++){
                    var a = table.rows[i].cells[0];
                    a.innerHTML= "<b>7."+(i+1)+"</b>";
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
<s:form beanclass="etanah.view.stripes.pembangunan.KertasJKTLActionBean">
    <s:messages/>
    <s:errors/>
    <s:hidden name="kandunganK.kertas.idKertas"/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend></legend>
            <div class="content" align="center">
                <table border="0" width="80%" cellspacing="10">
                    <%--<c:if test="${edit}">
                        <tr><td align="left"><b>Bil <s:text name="rujPtd" id="rujPtd" size="30"/></b></td></tr>
                    </c:if>--%>
                    <tr><td align="left"><b>${actionBean.permohonan.idPermohonan}</b></td></tr>
                    <c:if test="${edit1}">
                        <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                            <tr>
                                <td align="right">
                                    <b>KM No: ...............&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></td>
                            </tr>
                        </c:if>
                        <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                            <tr>
                                <td align="right">
                                    <b>KM No: <s:text name="kmno" id="kmno" size="12"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>
                                </td>
                            </tr>
                        </c:if>
                    </c:if>
                    <c:if test="${!edit1}">
                        <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                            <tr>
                                <td align="right">
                                    <b>KM No: ...............&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></td>
                            </tr>
                        </c:if>
                        <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                            <tr>
                                <td align="right">
                                    <b>KM No: <s:text name="kmno" id="kmno" size="12"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></td>

                            </tr>
                        </c:if>
                    </c:if>
                    <c:if test="${edit1}">
                        <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                            <tr><td align="center"><b>(MESYUARAT JAWATANKUASA LEMBAGA TANAH LADANG NEGERI SEMBILAN DARUL KHUSUS PADA .......................)</b></td></tr>
                        </c:if>
                        <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                            <tr><td align="center"><b>( MESYUARAT JAWATANKUASA LEMBAGA TANAH LADANG NEGERI SEMBILAN DARUL KHUSUS PADA  <s:text name="tarikhMesyuarat" id="datepicker" class="datepicker" size="12" /> )</b></td></tr>
                        </c:if>
                    </c:if>
                    <c:if test="${!edit1}">
                        <c:if test="${actionBean.peng.kodCawangan.kod ne '00'}">
                            <tr><td align="center"><b>(MESYUARAT JAWATANKUASA LEMBAGA TANAH LADANG NEGERI SEMBILAN DARUL KHUSUS PADA .......................)</b></td></tr>
                        </c:if>
                        <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                            <tr><td align="center"><b>( MESYUARAT JAWATANKUASA LEMBAGA TANAH LADANG NEGERI SEMBILAN DARUL KHUSUS PADA  <s:text name="tarikhMesyuarat" id="datepicker" class="datepicker" size="12" /> )</b></td></tr>
                        </c:if>
                    </c:if>
                    <c:if test="${edit}">
                        <tr><td><b><s:textarea name="tajuk" rows="5" cols="150"/></b></td></tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td><b>${actionBean.tajuk}&nbsp;</b></td></tr>
                    </c:if>

                    <tr><td><b>1. TUJUAN</b></td></tr>
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
                        <c:if test="${edit}">
                        <tr><td><s:textarea rows="5" cols="150" name="tujuan" class="normal_text"/>
                            </c:if>

                    <tr><td><b>2. LATAR BELAKANG</b></td></tr>
                    <c:if test="${edit}">
                        <tr><td><s:textarea name="latarBelakang" rows="5" cols="150" class="normal_text"/></td></tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td>
                                <table cellspacing="10">
                                    <tr>
                                        <td><b>2.1 </b></td>
                                        <td>${actionBean.latarBelakang}</td>
                                    </tr>
                                </table>
                            </td></tr>
                        </c:if>

                    <tr><td><b>3. BUTIR-BUTIR HAKMILIK </b></td></tr>
                    <tr><td>
                            <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0" id="tbl1">
                                <display:column title="Bil" sortable="true" style="vertical-align:top">${tbl1_rowNum}</display:column>
                                <display:column title="Daerah" property="hakmilik.daerah.nama" class="daerah" style="vertical-align:top"/>
                                <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/ Mukim" style="vertical-align:top"/>
                                <display:column title="Nombor Lot/PT" style="vertical-align:top">
                                    ${tbl1.hakmilik.lot.nama} ${tbl1.hakmilik.noLot}
                                </display:column>
                                <display:column title="Jenis & No Hakmilik" style="vertical-align:top">
                                    ${tbl1.hakmilik.kodHakmilik.kod} ${tbl1.hakmilik.noHakmilik}
                                </display:column>
                                <display:column title="Tempoh Hakmilik" style="vertical-align:top">
                                    <c:if test="${tbl1.hakmilik.tempohPegangan ne null && tbl1.hakmilik.tempohPegangan ne 0}">${tbl1.hakmilik.tempohPegangan} tahun</c:if>
                                    <c:if test="${tbl1.hakmilik.tempohPegangan eq null || tbl1.hakmilik.tempohPegangan eq 0}">Tiada</c:if>
                                </display:column>
                                <display:column title="Luas" style="vertical-align:top"><fmt:formatNumber pattern="#,##0.0000" value="${tbl1.hakmilik.luas}"/>&nbsp;${tbl1.hakmilik.kodUnitLuas.nama}</display:column>
                                <display:column title="Kawasan Rizab" style="vertical-align:top">
                                    <c:if test="${tbl1.hakmilik.rizab.nama ne null}">${tbl1.hakmilik.rizab.nama}</c:if>
                                    <c:if test="${tbl1.hakmilik.rizab.nama eq null}">Tiada</c:if>
                                </display:column>
                                <display:column title="Tuan Tanah" style="vertical-align:top">
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
                                <display:column title="Kategori Kegunaan Tanah" style="vertical-align:top">
                                    ${tbl1.hakmilik.kategoriTanah.nama}
                                </display:column>
                                <display:column title="Syarat Nyata" style="vertical-align:top">
                                    <c:if test="${tbl1.hakmilik.syaratNyata.syarat ne null}">${tbl1.hakmilik.syaratNyata.syarat}</c:if>
                                    <c:if test="${tbl1.hakmilik.syaratNyata.syarat eq null}">Tiada</c:if>
                                </display:column>
                                <display:column title="Sekatan Kepentingan" style="vertical-align:top">
                                    <c:if test="${tbl1.hakmilik.sekatanKepentingan.sekatan ne null}">${tbl1.hakmilik.sekatanKepentingan.sekatan}</c:if>
                                    <c:if test="${tbl1.hakmilik.sekatanKepentingan.sekatan eq null}">Tiada</c:if>
                                </display:column>
                                <display:column title="Bebanan Berdaftar" style="vertical-align:top">
                                    <c:set var="flag" value="true" />
                                    <c:forEach items="${tbl1.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                                        <c:if test="${(((senarai.jenis.kod eq 'PG') ||(senarai.jenis.kod eq 'PJ') ||(senarai.jenis.kod eq 'PJK') ||
                                                      (senarai.jenis.kod eq 'PKA') ||(senarai.jenis.kod eq 'PKD') ||(senarai.jenis.kod eq 'PKL') ||
                                                      (senarai.jenis.kod eq 'PKS') ||(senarai.jenis.kod eq 'PMG') ||(senarai.jenis.kod eq 'PI') ||
                                                      (senarai.jenis.kod eq 'PY')) && senarai.aktif eq 'Y') }">
                                            <c:out value="${senarai.jenis.nama}"/><br/><br/>
                                            <c:set var="flag" value="false" />
                                        </c:if>
                                    </c:forEach>
                                    <c:if test="${flag eq 'true'}">
                                        Tiada
                                    </c:if>
                                </display:column>
                            </display:table>

                        </td>
                    </tr>
                    <c:set var="j" value="4" />
                    <c:if test = "${actionBean.permohonan.kodUrusan.kod eq 'TSPSS' || actionBean.permohonan.kodUrusan.kod eq 'SBMS'}">                       
                        <tr><td><b>${j}. ULASAN JABATAN-JABATAN TEKNIKAL</b></td></tr>
                        <tr>
                            <td>
                                <display:table name="${actionBean.listUlasan2}" id="line1" class="tablecloth" size="100">
                                    <display:column title="Bil" sortable="true">${line1_rowNum}</display:column>
                                    <display:column title="Jabatan" style="width:400px" property="agensi.nama"></display:column>
                                    <display:column title="Ulasan" style="width:600px" property="catatan"></display:column>
                                </display:table>
                            </td>
                        </tr>
                        <c:set var="j" value="${j+1}" />
                    </c:if>

                   
                    <tr><td><b><font style="text-transform:uppercase">${j}. HURAIAN PENTADBIR TANAH  ${actionBean.pejTanah}</font></b></td></tr>

                    <c:if test="${edit}">
                        <tr><td>
                                <table id ="dataTable5">
                                    <tr>
                                        <td><b>${j}.1 </b></td>
                                        <td><s:textarea name="huraianPentadbir1" id="huraianPentadbir1" class="normal_text"  rows="5" cols="150"></s:textarea></td>
                                        </tr>
                                    <c:set var="i" value="2" />
                                    <c:forEach items="${actionBean.senaraiKandungan5}" var="line" begin="1">
                                        <tr><td style="display:none">${line.idKandungan}</td>
                                            <td><b>${j}.${i}</b></td>
                                            <td><s:textarea name="huraianPentadbir${i}" class="normal_text" id="huraianPentadbir${i}"  rows="5" cols="150">${line.kandungan}</s:textarea></td>
                                            <td><s:button value="Hapus" id="${i-1}" class="btn" name="hapus" onclick="deleteRow5(${line.idKandungan},${i-1})"></s:button> </td>
                                            </tr>
                                        <c:set var="i" value="${i+1}" />
                                    </c:forEach>
                                </table>
                            </td>
                        </tr>
                        <c:set var="j" value="${j+1}" />
                        <tr>
                            <td><s:hidden name="rowCount5"  id="rowCount5"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <s:button class="btn" value="Tambah" name="add" onclick="addRow5('dataTable5')"/>&nbsp;                                    
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${!edit}" >
                        <tr><td>
                                <table cellspacing="10">
                                    <c:set var="i" value="1" />
                                    <c:forEach items="${actionBean.senaraiKandungan5}" var="line">
                                        <tr><td valign="top"><b>${j}.${i} </b></td>
                                            <td>${line.kandungan}</td>
                                        </tr>
                                        <c:set var="i" value="${i+1}" />
                                    </c:forEach>
                                </table>
                            </td>
                        </tr>
                        <c:set var="j" value="${j+1}" />
                    </c:if>                  

                    <tr><td><b><font style="text-transform:uppercase">${j}. SYOR PENTADBIR TANAH ${actionBean.pejTanah}&nbsp;</font></b></td></tr>

                    <c:if test="${edit}">
                        <tr><td>
                                <table id ="dataTable6">
                                    <tr>
                                        <td><b>${j}.1 </b></td>
                                        <td><s:textarea name="syorPentadbir1" id="syorPentadbir1" class="normal_text"  rows="5" cols="150"></s:textarea></td>
                                        </tr>
                                    <c:set var="i" value="2" />
                                    <c:forEach items="${actionBean.senaraiKandungan6}" var="line" begin="1">
                                        <tr><td style="display:none">${line.idKandungan}</td>
                                            <td><b>${j}.${i}</b></td>
                                            <td><s:textarea name="syorPentadbir${i}" id="syorPentadbir${i}" class="normal_text"  rows="5" cols="150">${line.kandungan}</s:textarea></td>
                                            <td><s:button value="Hapus" id="${i-1}" class="btn" name="hapus" onclick="deleteRow6(${line.idKandungan},${i-1})"></s:button> </td>
                                            </tr>
                                        <c:set var="i" value="${i+1}" />
                                    </c:forEach>
                                </table>
                            </td>
                        </tr>
                        <c:set var="j" value="${j+1}" />
                        <tr>
                            <td><s:hidden name="rowCount6"  id="rowCount6"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <s:button class="btn" value="Tambah" name="add" onclick="addRow6('dataTable6')"/>&nbsp;                                    
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${!edit}" >
                        <tr><td>
                                <table cellspacing="10">
                                    <c:set var="i" value="1" />
                                    <c:forEach items="${actionBean.senaraiKandungan6}" var="line">
                                        <tr><td valign="top"><b>${j}.${i} </b></td>
                                            <td>${line.kandungan}</td>
                                        </tr>
                                        <c:set var="i" value="${i+1}" />
                                    </c:forEach>
                                </table>
                            </td>
                        </tr>
                        <c:set var="j" value="${j+1}" />
                    </c:if>
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                        <tr><td><b>${j}. HURAIAN PENGARAH TANAH DAN GALIAN NEGERI SEMBILAN DARUL KHUSUS</b></td></tr>
                        <tr><td>
                                <table id ="dataTable7">
                                    <tr>
                                        <td><b>${j}.1 </b></td>
                                        <td><s:textarea name="huraianPtg1" id="huraianPtg1" class="normal_text" rows="5" cols="150"></s:textarea></td>
                                        </tr>
                                    <c:set var="i" value="2" />
                                    <c:forEach items="${actionBean.senaraiKandungan7}" var="line" begin="1">
                                        <tr><td style="display:none">${line.idKandungan}</td>
                                            <td><b>${j}.${i}</b></td>
                                            <td><s:textarea name="huraianPtg${i}" id="huraianPtg${i}" class="normal_text" rows="5" cols="150">${line.kandungan}</s:textarea></td>
                                            <td><s:button value="Hapus" id="${i-1}" class="btn" name="tambah" onclick="deleteRow7(${line.idKandungan},${i-1})"></s:button> </td>
                                            </tr>
                                        <c:set var="i" value="${i+1}" />
                                    </c:forEach>
                                </table>
                            </td>
                        </tr>
                        <c:set var="j" value="${j+1}" />
                        <tr>
                            <td><s:hidden name="rowCount7"  id="rowCount7"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <s:button class="btn" value="Tambah" name="add" onclick="addRow7('dataTable7')"/>&nbsp;                                   
                            </td>
                        </tr>

                        <tr><td><b>${j}. SYOR PENGARAH TANAH DAN GALIAN NEGERI SEMBILAN DARUL KHUSUS</b></td></tr>
                        <tr><td>
                                <table id ="dataTable8">
                                    <tr>
                                        <td><b>${j}.1 </b></td>
                                        <td><s:textarea name="syorPtg1" id="syorPtg1"  class="normal_text"  rows="5" cols="150"></s:textarea></td>
                                        </tr>
                                    <c:set var="i" value="2" />
                                    <c:forEach items="${actionBean.senaraiKandungan8}" var="line" begin="1">
                                        <tr><td style="display:none">${line.idKandungan}</td>
                                            <td><b>${j}.${i}</b></td>
                                            <td><s:textarea name="syorPtg${i}" id="syorPtg${i}" class="normal_text" rows="5" cols="150">${line.kandungan}</s:textarea></td>
                                            <td><s:button value="Hapus" id="${i-1}" class="btn" name="hapus" onclick="deleteRow8(${line.idKandungan},${i-1})"></s:button> </td>
                                            </tr>
                                        <c:set var="i" value="${i+1}" />
                                    </c:forEach>
                                </table>
                            </td>
                        </tr>
                        <c:set var="j" value="${j+1}" />
                        <tr>
                            <td><s:hidden name="rowCount8"  id="rowCount8"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <s:button class="btn" value="Tambah" name="tambah" onclick="addRow8('dataTable8')"/>&nbsp;
                            </td>
                        </tr>

                        <tr><td><b>${j}. KEPUTUSAN</b></td></tr>
                        <tr><td>
                                <table cellspacing="10">
                                    <tr>
                                        <td><b>${j}.1 </b></td>
                                        <c:if test = "${actionBean.permohonan.kodUrusan.kod eq 'SBMS' || actionBean.permohonan.kodUrusan.kod eq 'TSPSS'}">
                                            <td> Dikemukakan untuk pertimbangan dan keputusan Lembaga Tanah Ladang, Negeri Sembilan. </td>
                                        </c:if>

                                        <c:if test = "${!(actionBean.permohonan.kodUrusan.kod eq 'SBMS' || actionBean.permohonan.kodUrusan.kod eq 'TSPSS')}">
                                            <td>Pengarah Tanah Dan Galian Negeri Sembilan Darul Khusus dengan ini 
                                                <c:if test="${actionBean.fasaPermohonan.keputusan.kod eq 'L'}">
                                                    <b>meluluskan</b> 
                                                </c:if>
                                                <c:if test="${actionBean.fasaPermohonan.keputusan.kod eq 'T'}">
                                                    <b>menolak</b> 
                                                </c:if>
                                                <c:if test="${actionBean.fasaPermohonan.keputusan.kod eq null}">
                                                    <b>meluluskan / menolak</b> 
                                                </c:if>
                                                permohonan ini.
                                            </td>
                                        </c:if>
                                    </tr>
                                </table>
                            </td></tr>
                            <c:set var="j" value="${j+1}" />
                        </c:if>
                </table>
                <%--<c:if test="${edit}">--%>
                <br>
                <p>
                    <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </p>
                <%--</c:if>--%>
            </div>
        </fieldset>
    </div>
</s:form>



