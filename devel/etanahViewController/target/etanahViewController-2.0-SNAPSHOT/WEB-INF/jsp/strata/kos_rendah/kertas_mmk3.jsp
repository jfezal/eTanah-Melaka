<%-- 
    Document   : kertas_mmk3
    Created on : Jun 14, 2011, 10:16:35 PM
    Author     : zadhirul.farihim
--%>


<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<script type="text/javascript">

    
    function regenerateBill(tableid){
        try{
            var table = document.getElementById(tableid);
            var rowCount = table.rows.length;
            for(var i=1;i<rowCount;i++){
               
                //alert(table.rows[i].cells[0].childNodes[0].data);
                //table.rows[i].cells[0].childNodes[0].data= "4."+(i+1);
                table.rows[i].cells[2].childNodes[0].id= i+1;
            }
        
        }catch(e){
            alert("Error in regenerateBill");
        }
    }
   
    function addRow4(tableID2) {
        if (validateRow4(tableID2)==true){
            document.getElementById("rowCount4").value = 1;
            var table = document.getElementById(tableID2);

            var rowCount1 = table.rows.length;
     
            var row = table.insertRow(rowCount1);

            var cell2 = row.insertCell(0);
            cell2.innerHTML = "<b>"+"4." +(rowCount1+1)+"</b>";

            var cell1 = row.insertCell(1);
            var element1 = document.createElement("textarea");

            element1.t = "ulasanpengarah"+(rowCount1+1);
            element1.rows = 5;
            element1.cols = 140;

            element1.name ="ulasanpengarah"+(rowCount1+1);
            element1.id ="ulasanpengarah"+(rowCount1+1);
            cell1.appendChild(element1);

            document.getElementById("rowCount4").value=rowCount1+1;
            var cell2 = row.insertCell(2);
            var e1 = document.createElement("img");
            e1.t = "button"+(rowCount1+1);
            e1.src = "${pageContext.request.contextPath}/images/not_ok.gif";
            e1.alt = "klik untuk batal";
            e1.align = "top"
            e1.value="Hapus";
            e1.id =(rowCount1+1);
            e1.onclick=function(){delete4(tableID2);};
            cell2.appendChild(e1);

        }
    }
    function validateRow4(tableID2){
        var table = document.getElementById(tableID2);
        var rowCount = table.rows.length;
        if(rowCount>0){
            var txtarea = "ulasanpengarah"+rowCount;
            var test = document.getElementById(txtarea).value;
            if(test==""){
                var count = rowCount;
                alert("Sila Simpan Sebelum Menambah Maklumat Berikutnya.");
                return false;
            }else{return true;}
        }else{
            return true;
        }

    }
    function delete4(recordNo)
    {
        if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
            var i = document.getElementById("rowCount4").value;
            var x= document.getElementById('dataTable4').rows[i-1].cells;

            if(i==1){
                document.getElementById('dataTable4').deleteRow(i-1);
            }else{
                document.getElementById('dataTable4').deleteRow(recordNo-1);
            }

            document.getElementById("rowCount4").value = i-1;

            var url = '${pageContext.request.contextPath}/strata/kertas_MMK?showForm3';
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');


            regenerateBill('dataTable4');
        }

    }
    function deleteRow4(recordNo,idKandungan)
    {
        // alert("recordNo:"+recordNo);
        if(confirm('Adakah anda pasti untuk menghapus data ini?')) {

            var i = document.getElementById("rowCount4").value;
            var x= document.getElementById('dataTable4').rows[i-1].cells;
            var idKandungan = x[0].innerHTML;
            // alert(idKandungan);

            if(i==1){
                document.getElementById('dataTable4').deleteRow(i-1);
            }else{
                document.getElementById('dataTable4').deleteRow(recordNo-1);
            }

            document.getElementById("rowCount4").value = i-1;
            var url = '${pageContext.request.contextPath}/strata/kertas_MMK?deleteUlasanPengarah&idKandungan='
                +idKandungan;

            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');

            regenerateBill('dataTable4');
        }
    }
    
    function addRow5(tableID1) {
        if(validateRow5(tableID1)==true){
            document.getElementById("rowCount5").value = 1;
            var table = document.getElementById(tableID1);

            var rowCount1 = table.rows.length;
            var row = table.insertRow(rowCount1);

            var cell2 = row.insertCell(0);
            cell2.innerHTML = "<b>"+"5." +(rowCount1+1)+"</b>";

            var cell1 = row.insertCell(1);
            var element1 = document.createElement("textarea");
            element1.t = "syorpngarah"+(rowCount1+1);
            element1.rows = 5;
            element1.cols = 140;
            element1.name ="syorpngarah"+(rowCount1+1);
            element1.id ="syorpngarah"+(rowCount1+1);
            cell1.appendChild(element1);

            document.getElementById("rowCount5").value=rowCount1+1;
            var cell2 = row.insertCell(2);
            var e1 = document.createElement("img");
            e1.t = "button"+(rowCount1+1);
            e1.src = "${pageContext.request.contextPath}/images/not_ok.gif";
            e1.alt = "klik untuk batal";
            e1.align = "top";
            e1.value="Batal";
            e1.id =(rowCount1+1);
            e1.onclick=function(){delete5(e1.id);};
            cell2.appendChild(e1);
            
        }

    }
    function validateRow5(tableID2){
        var table = document.getElementById(tableID2);
        var rowCount = table.rows.length;
        if(rowCount>0){
            var txtarea = "syorpngarah"+rowCount;
            var test = document.getElementById(txtarea).value;
            if(test==""){
                alert("Sila Simpan Sebelum Menambah Maklumat Berikutnya.");
                return false;
            }else{return true;}
        }else{
            return true;
        }
    }
    function delete5(recordNo)
    {
        if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
            var i = document.getElementById("rowCount5").value;
            var x= document.getElementById('dataTable5').rows[i-1].cells;

            if(i==1){
                document.getElementById('dataTable5').deleteRow(i-1);
            }else{
                document.getElementById('dataTable5').deleteRow(recordNo-1);
            }

            document.getElementById("rowCount5").value = i-1;

            var url = '${pageContext.request.contextPath}/strata/kertas_MMK?showForm3';
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');


            regenerateBill('dataTable5');
        }

    }


    function deleteRow5(recordNo,idKandungan)
    {

        if(confirm('Adakah anda pasti untuk menghapus data ini?')) {

            var i = document.getElementById("rowCount5").value;
            var x= document.getElementById('dataTable5').rows[i-1].cells;
            // var idKandungan = x[0].innerHTML;
            //  alert(idKandungan);
            if(i==1){
                document.getElementById('dataTable5').deleteRow(i-1);
            }else{
                document.getElementById('dataTable5').deleteRow(recordNo-1);
            }

            document.getElementById("rowCount5").value = i-1;

            var url = '${pageContext.request.contextPath}/strata/kertas_MMK?deleteSyorPengarah&idKandungan='
                +idKandungan;

            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');

            regenerateBill('dataTable5');
        }

    }
    function addRow6(tableID1) {
        if(validateRow6(tableID1)==true){
            document.getElementById("rowCount6").value = 1;
            var table = document.getElementById(tableID1);

            var rowCount1 = table.rows.length;
            var row = table.insertRow(rowCount1);

            var cell2 = row.insertCell(0);
            cell2.innerHTML = "<b>"+"6." +(rowCount1+1)+"</b>";

            var cell1 = row.insertCell(1);
            var element1 = document.createElement("textarea");
            element1.t = "kputusan"+(rowCount1+1);
            element1.rows = 5;
            element1.cols = 140;
            element1.name ="kputusan"+(rowCount1+1);
            element1.id ="kputusan"+(rowCount1+1);
            cell1.appendChild(element1);

            document.getElementById("rowCount6").value=rowCount1+1;
            var cell2 = row.insertCell(2);
            var e1 = document.createElement("img");
            e1.t = "button"+(rowCount1+1);
            e1.src = "${pageContext.request.contextPath}/images/not_ok.gif";
            e1.alt = "klik untuk batal";
            e1.align = "top";
            e1.value="Batal";
            e1.id =(rowCount1+1);
            e1.onclick=function(){deleteRow6(e1.id);};
            cell2.appendChild(e1);
        }

    }
    function validateRow6(tableID2){
        var table = document.getElementById(tableID2);
        var rowCount = table.rows.length;
        if(rowCount>0){
            var txtarea = "kputusan"+rowCount;
            var test = document.getElementById(txtarea).value;
            if(test==""){
                alert("Sila Simpan Sebelum Menambah Maklumat Berikutnya.");
                return false;
            }else{return true;}
        }else{
            return true;
        }
    }
    function delete6(recordNo)
    {
        if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
            var i = document.getElementById("rowCount6").value;
            var x= document.getElementById('dataTable6').rows[i-1].cells;

            if(i==1){
                document.getElementById('dataTable6').deleteRow(i-1);
            }else{
                document.getElementById('dataTable6').deleteRow(recordNo-1);
            }

            document.getElementById("rowCount6").value = i-1;

            var url = '${pageContext.request.contextPath}/strata/kertas_MMK?showForm3';
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');


            regenerateBill('dataTable6');
        }

    }

    function deleteRow6(recordNo,idKandungan)
    {

        if(confirm('Adakah anda pasti untuk menghapus data ini?')) {

            var i = document.getElementById("rowCount6").value;
            var x= document.getElementById('dataTable6').rows[i-1].cells;
            // var idKandungan = x[0].innerHTML;
            //  alert(idKandungan);
            if(i==1){
                document.getElementById('dataTable6').deleteRow(i-1);
            }else{
                document.getElementById('dataTable6').deleteRow(recordNo-1);
            }

            document.getElementById("rowCount6").value = i-1;

            var url = '${pageContext.request.contextPath}/strata/kertas_MMK?deleteKeputusan&idKandungan='
                +idKandungan;

            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');

            regenerateBill('dataTable6');
        }

    }

    function simpanData(event, f){
        if(confirm('Adakah anda pasti untuk simpan data ini?')){
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
                $('#page_div').html(data);

            },'html');
        }

    }
 

</script>

<s:form name="form1" beanclass="etanah.view.strata.KertasMMKActionBean">
    <s:messages/>
    <s:errors/> 
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Tajuk</legend>
            <div class="content" align="left">
                <table border="0">
                    <tr><td colspan="4" align="center"><b>( MAJLIS MESYUARAT KERAJAAN )</b></td></tr>
                    <tr>
                        <td colspan="4"><strong>&nbsp;</strong></td>
                    </tr>
                    <tr><td width="100px"><strong>&nbsp;</strong></td>
                        <td colspan="2"><b><font style="text-transform: uppercase"><center>${actionBean.tajuk}</center></font></b></td>
                        <td width="100px"><strong>&nbsp;</strong></td>
                    </tr>
                    <tr>
                        <td colspan="4"><strong>&nbsp;</strong></td>
                    </tr>
                </table>
            </div>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>1. Tujuan</legend>
            <div class="content" align="left">
                <table border="0">

                    <tr valign="top">
                        <td height="23">&nbsp;</td>
                        <td colspan="3">

                            <table id ="dataTable1" border="0">

                                <c:set var="i" value="1" />
                                <c:set var="bil" value="0" />
                                <c:forEach items="${actionBean.senaraiLaporanKandungan1}" var="line">
                                    <tr style="font-weight:bold">
                                        <td style="display:none">${line.idKandungan}</td>
                                        <td><c:out value="1.${bil+1}"/></td>
                                        <td>

                                            <font style="text-transform: uppercase">
                                            <s:textarea class="normal_text" readonly="true" name="tujua${i}" id="tujua${i}"  rows="5" cols="140" >${line.kandungan}</s:textarea>
                                            </font>

                                        </td>
                                        <td>        
                                        </td>
                                    </tr>
                                    <c:set var="i" value="${i+1}" />
                                    <c:set var="bil" value="${bil+1}" />
                                </c:forEach>
                                <s:hidden name="rowCount1" value="${i-1}" id="rowCount1"/>
                            </table></td>
                    </tr>

                </table>
            </div>
        </fieldset>
    </div>
    <p></p>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>2. Latar Belakang</legend>
            <div class="content" align="left">
                <table border="0">

                    <tr valign="top">
                        <td height="23">&nbsp;</td>
                        <td colspan="3">

                            <table id ="dataTable2" border="0">

                                <c:set var="i" value="1" />
                                <c:set var="bil" value="0" />
                                <c:forEach items="${actionBean.senaraiLaporanKandungan2}" var="line">
                                    <tr style="font-weight:bold">
                                        <td style="display:none">${line.idKandungan}</td>
                                        <td><c:out value="2.${bil+1}"/></td>
                                        <td>
                                            <font style="text-transform: uppercase">
                                            <s:textarea class="normal_text" readonly="true" name="latarblkg${i}" id="latarblkg${i}"  rows="5" cols="140" >${line.kandungan}</s:textarea>
                                            </font>

                                        </td>

                                        <td>
                                        </td>

                                    </tr>

                                    <c:set var="i" value="${i+1}" />
                                    <c:set var="bil" value="${bil+1}" />
                                </c:forEach>
                                <s:hidden name="rowCount2" value="${i-1}" id="rowCount2"/>
                            </table></td>
                    </tr>
                </table>
            </div>
        </fieldset>
    </div>
    <p></p>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>3. Asas Permohonan</legend>
            <div class="content" align="left">
                <table border="0">

                    <tr valign="top">
                        <td height="23">&nbsp;</td>
                        <td colspan="3">

                            <table id ="dataTable3" border="0">

                                <c:set var="i" value="1" />
                                <c:set var="bil" value="0" />

                                <c:forEach items="${actionBean.senaraiLaporanKandungan3}" var="line">
                                    <tr style="font-weight:bold">
                                        <td style="display:none">${line.idKandungan}</td>
                                        <td><c:out value="3.${bil+1}"/></td>
                                         <c:if test="${i ne 2}">
                                        <td>
                                            <font style="text-transform: uppercase">
                                            <s:textarea class="normal_text" readonly="true" name="asaspermohonan${i}" id="asaspermohonan${i}"  rows="5" cols="140" >${line.kandungan}</s:textarea>
                                            </font>

                                        </td>
                                        </c:if>
                                        <c:if test="${i eq 2}">
                                        <td>
                                            <font style="text-transform: uppercase">
                                            <s:textarea class="normal_text" readonly="true" name="asaspermohonan${i}" id="asaspermohonan${i}"  rows="8" cols="140" >${line.kandungan}</s:textarea>
                                            </font>

                                        </td>
                                        </c:if>

                                        <td>

                                        </td>

                                    </tr>

                                    <c:set var="i" value="${i+1}" />
                                    <c:set var="bil" value="${bil+1}" />
                                </c:forEach>
                                <s:hidden name="rowCount3" value="${i-1}" id="rowCount3"/>
                            </table></td>
                    </tr>
                </table>
            </div>
        </fieldset>
    </div>
    <p></p>
    <c:if test="${edit}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>4. Ulasan Pengarah Tanah Dan Galian</legend>
                <div class="content" align="left">
                    <table border="0">

                        <tr valign="top">
                            <td height="23">&nbsp;</td>
                            <td colspan="3">

                                <table id ="dataTable4" border="0">

                                    <c:set var="i" value="1" />
                                    <c:set var="bil" value="0" />
                                    <c:if test="${fn:length(actionBean.senaraiLaporanKandungan4) == 0 or actionBean.senaraiLaporanKandungan4 eq null}">
                                        <tr>
                                            <td valign="top"><b>4.1</b></td>
                                            <td>
                                                <font style="text-transform: uppercase">
                                                <s:textarea class="normal_text" name="ulasanpengarah1" id="ulasanpengarah1"  rows="8" cols="140" >${actionBean.ulasapengarah1}</s:textarea>
                                                </font>

                                            </td>
                                            <td>

                                            </td>
                                        </tr>
                                        <s:hidden name="rowCount4" value="${i}" id="rowCount4"/>

                                    </c:if>
                                    <c:forEach items="${actionBean.senaraiLaporanKandungan4}" var="line">
                                        <tr style="font-weight:bold">
                                            <td style="display:none">${line.idKandungan}</td>
                                            <td><c:out value="4.${bil+1}"/></td>
                                            <c:if test="${i ne 1}">
                                            <td>
                                                <font style="text-transform: uppercase">
                                                <s:textarea class="normal_text" name="ulasanpengarah${i}" id="ulasanpengarah${i}"  rows="5" cols="140">${line.kandungan}</s:textarea>
                                                </font>

                                            </td>
                                            </c:if>
                                            <c:if test="${i eq 1}">
                                            <td>
                                                <font style="text-transform: uppercase">
                                                <s:textarea class="normal_text" name="ulasanpengarah${i}" id="ulasanpengarah${i}"  rows="8" cols="140">${line.kandungan}</s:textarea>
                                                </font>

                                            </td>
                                            </c:if>

                                            <td>
                                                <img alt='Klik Untuk Hapus' title='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                     id="${i}" onclick="deleteRow4(${i},${line.idKandungan})" onmouseover="this.style.cursor='pointer';">
                                            </td>

                                        </tr>

                                        <c:set var="i" value="${i+1}" />
                                        <c:set var="bil" value="${bil+1}" />
                                    </c:forEach>
                                    <s:hidden name="rowCount4" value="${i-1}" id="rowCount4"/>
                                </table></td>
                        </tr>
                        <tr valign="top">
                            <td height="23">&nbsp;</td>
                            <td colspan="3">
                                <s:button class="btn" value="Tambah" name="add" onclick="addRow4('dataTable4')"/>
                                <s:button name="simpanUlasanPengarah" id="save" value="Simpan" class="btn" onclick="simpanData(this.name,this.form)"/>
                            </td>
                        </tr>
                    </table>
                </div>
            </fieldset>
        </div>
        <p></p>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>5. Syor Pengarah Tanah Dan Galian</legend>
                <div class="content" align="left">
                    <table border="0">

                        <tr valign="top">
                            <td height="23">&nbsp;</td>
                            <td colspan="3">

                                <table id ="dataTable5" border="0">

                                    <c:set var="i" value="1" />
                                    <c:set var="bil" value="0" />
                                    <c:if test="${fn:length(actionBean.senaraiLaporanKandungan5) == 0 or actionBean.senaraiLaporanKandungan5 eq null}">
                                        <tr>
                                            <td valign="top"><b>5.1</b></td>
                                            <td>
                                                <font style="text-transform: uppercase">
                                                <s:textarea class="normal_text" name="syorpngarah1" id="syorpngarah1"  rows="5" cols="140" >${actionBean.syorpengarah1}</s:textarea>
                                                </font>

                                            </td>
                                            <td>

                                            </td>
                                        </tr>
                                        <s:hidden name="rowCount5" value="${i}" id="rowCount5"/>

                                    </c:if>
                                    <c:forEach items="${actionBean.senaraiLaporanKandungan5}" var="line">
                                        <tr style="font-weight:bold">
                                            <td style="display:none">${line.idKandungan}</td>
                                            <td><c:out value="5.${bil+1}"/></td>
                                            <td>
                                                <font style="text-transform: uppercase">
                                                <s:textarea class="normal_text" name="syorpngarah${i}" id="syorpngarah${i}"  rows="5" cols="140">${line.kandungan}</s:textarea>
                                                </font>

                                            </td>

                                            <td>
                                                <img alt='Klik Untuk Hapus' title='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                     id="${i}" onclick="deleteRow5(${i},${line.idKandungan})" onmouseover="this.style.cursor='pointer';">
                                            </td>

                                        </tr>

                                        <c:set var="i" value="${i+1}" />
                                        <c:set var="bil" value="${bil+1}" />
                                    </c:forEach>
                                    <s:hidden name="rowCount5" value="${i-1}" id="rowCount5"/>
                                </table></td>
                        </tr>
                        <tr valign="top">
                            <td height="23">&nbsp;</td>
                            <td colspan="3">
                                <s:button class="btn" value="Tambah" name="add" onclick="addRow5('dataTable5')"/>
                                <s:button name="simpanSyorPengarah" id="save" value="Simpan" class="btn" onclick="simpanData(this.name,this.form)"/>
                            </td>
                        </tr>
                    </table>
                </div>
            </fieldset>
        </div>
        <p></p>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>6. Keputusan</legend>
                <div class="content" align="left">
                    <table border="0">

                        <tr valign="top">
                            <td height="23">&nbsp;</td>
                            <td colspan="3">

                                <table id ="dataTable6" border="0">

                                    <c:set var="i" value="1" />
                                    <c:set var="bil" value="0" />
                                    <c:if test="${fn:length(actionBean.senaraiLaporanKandungan6) == 0 or actionBean.senaraiLaporanKandungan6 eq null}">
                                        <tr>
                                            <td valign="top"><b>6.1</b></td>
                                            <td>
                                                <font style="text-transform: uppercase">
                                                <s:textarea class="normal_text" name="kputusan1" id="kputusan1"  rows="5" cols="140">${actionBean.keputusan1}</s:textarea>
                                                </font>

                                            </td>
                                            <td>

                                            </td>
                                        </tr>
                                        <s:hidden name="rowCount6" value="${i}" id="rowCount6"/>

                                    </c:if>
                                    <c:forEach items="${actionBean.senaraiLaporanKandungan6}" var="line">
                                        <tr style="font-weight:bold">
                                            <td style="display:none">${line.idKandungan}</td>
                                            <td><c:out value="6.${bil+1}"/></td>
                                            <td>
                                                <font style="text-transform: uppercase">
                                                <s:textarea class="normal_text" name="kputusan${i}" id="kputusan${i}"  rows="5" cols="140" >${line.kandungan}</s:textarea>
                                                </font>

                                            </td>

                                            <td>
                                                <img alt='Klik Untuk Hapus' title='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                     id="${i}" onclick="deleteRow6(${i},${line.idKandungan})" onmouseover="this.style.cursor='pointer';">
                                            </td>

                                        </tr>

                                        <c:set var="i" value="${i+1}" />
                                        <c:set var="bil" value="${bil+1}" />
                                    </c:forEach>
                                    <s:hidden name="rowCount6" value="${i-1}" id="rowCount6"/>
                                </table></td>
                        </tr>
                        <tr valign="top">
                            <td height="23">&nbsp;</td>
                            <td colspan="3">
                                <s:button class="btn" value="Tambah" name="add" onclick="addRow6('dataTable6')"/>
                                <s:button name="simpanKeputusan" id="save" value="Simpan" class="btn" onclick="simpanData(this.name,this.form)"/>
                            </td>
                        </tr>
                    </table>
                </div>
            </fieldset>
        </div>
    </c:if>
    <c:if test="${display}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>4. Ulasan Pengarah Tanah Dan Galian</legend>
                <div class="content" align="left">
                    <table border="0">

                        <tr valign="top">
                            <td height="23">&nbsp;</td>
                            <td colspan="3">

                                <table id ="dataTable4" border="0">

                                    <c:set var="i" value="1" />
                                    <c:set var="bil" value="0" />
                                    <c:forEach items="${actionBean.senaraiLaporanKandungan4}" var="line">
                                        <tr style="font-weight:bold">
                                            <td style="display:none">${line.idKandungan}</td>
                                            <td><c:out value="4.${bil+1}"/></td>
                                            <td>
                                                <font style="text-transform: uppercase">
                                                <s:textarea class="normal_text" readonly="true" name="ulasanpengarah${i}" id="ulasanpengarah${i}"  rows="5" cols="140">${line.kandungan}</s:textarea>
                                                </font>

                                            </td>

                                            <td>
                                            </td>

                                        </tr>

                                        <c:set var="i" value="${i+1}" />
                                        <c:set var="bil" value="${bil+1}" />
                                    </c:forEach>
                                    <s:hidden name="rowCount4" value="${i-1}" id="rowCount4"/>
                                </table></td>
                        </tr>

                    </table>
                </div>
            </fieldset>
        </div>
        <p></p>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>5. Syor Pengarah Tanah Dan Galian</legend>
                <div class="content" align="left">
                    <table border="0">

                        <tr valign="top">
                            <td height="23">&nbsp;</td>
                            <td colspan="3">

                                <table id ="dataTable5" border="0">

                                    <c:set var="i" value="1" />
                                    <c:set var="bil" value="0" />
                                    <c:forEach items="${actionBean.senaraiLaporanKandungan5}" var="line">
                                        <tr style="font-weight:bold">
                                            <td style="display:none">${line.idKandungan}</td>
                                            <td><c:out value="5.${bil+1}"/></td>
                                            <td>
                                                <font style="text-transform: uppercase">
                                                <s:textarea class="normal_text" readonly="true" name="syorpngarah${i}" id="syorpngarah${i}"  rows="5" cols="140">${line.kandungan}</s:textarea>
                                                </font>

                                            </td>

                                            <td>

                                            </td>

                                        </tr>

                                        <c:set var="i" value="${i+1}" />
                                        <c:set var="bil" value="${bil+1}" />
                                    </c:forEach>
                                    <s:hidden name="rowCount5" value="${i-1}" id="rowCount5"/>
                                </table></td>
                        </tr>
                    </table>
                </div>
            </fieldset>
        </div>
        <p></p>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>6. Keputusan</legend>
                <div class="content" align="left">
                    <table border="0">

                        <tr valign="top">
                            <td height="23">&nbsp;</td>
                            <td colspan="3">

                                <table id ="dataTable6" border="0">

                                    <c:set var="i" value="1" />
                                    <c:set var="bil" value="0" />
                                    <c:forEach items="${actionBean.senaraiLaporanKandungan6}" var="line">
                                        <tr style="font-weight:bold">
                                            <td style="display:none">${line.idKandungan}</td>
                                            <td><c:out value="6.${bil+1}"/></td>
                                            <td>
                                                <font style="text-transform: uppercase">
                                                <s:textarea class="normal_text" readonly="true" name="kputusan${i}" id="kputusan${i}"  rows="5" cols="140" >${line.kandungan}</s:textarea>
                                                </font>

                                            </td>

                                            <td>

                                            </td>

                                        </tr>

                                        <c:set var="i" value="${i+1}" />
                                        <c:set var="bil" value="${bil+1}" />
                                    </c:forEach>
                                    <s:hidden name="rowCount6" value="${i-1}" id="rowCount6"/>
                                </table></td>
                        </tr>

                    </table>
                </div>
            </fieldset>
        </div>
    </c:if>

</s:form>
