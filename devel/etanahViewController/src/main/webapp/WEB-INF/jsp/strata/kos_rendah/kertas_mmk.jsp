<%-- 
    Document   : kertas_mmk
    Created on : Jun 14, 2011, 4:20:01 PM
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
   
    function addRow3(tableID1) {
        if(validateRow3(tableID1)==true){
            document.getElementById("rowCount3").value = 1;
            var table = document.getElementById(tableID1);

            var rowCount1 = table.rows.length;

            var row = table.insertRow(rowCount1);

            var cell2 = row.insertCell(0);
            cell2.innerHTML = "<b>"+"3." +(rowCount1+1)+"</b>";

            var cell1 = row.insertCell(1);
            var element1 = document.createElement("textarea");


            element1.t = "asaspermohonan"+(rowCount1+1);
            element1.rows = 5;
            element1.cols = 140;
            element1.className="normal_text"

            element1.name ="asaspermohonan"+(rowCount1+1);
            element1.id ="asaspermohonan"+(rowCount1+1);
            cell1.appendChild(element1);

            document.getElementById("rowCount3").value=rowCount1+1;
            var cell2 = row.insertCell(2);
            var e1 = document.createElement("img");
            e1.t = "button"+(rowCount1+1);
            e1.src = "${pageContext.request.contextPath}/images/not_ok.gif";
            e1.alt = "klik untuk batal";
            e1.align = "top"
            e1.value="Hapus";
            e1.id =(rowCount1+1);
            e1.onclick=function(){delete3(tableID1);};
            cell2.appendChild(e1);

        }
    }
   function validateRow3(tableID2){
        var table = document.getElementById(tableID2);
        var rowCount = table.rows.length;
        if(rowCount>0){
            var txtarea = "asaspermohonan"+rowCount;
            var test = document.getElementById(txtarea).value;
            if(test==""){
                alert("Sila Simpan Sebelum Menambah Maklumat Berikutnya.");
                return false;
            }else{return true;}
        }else{
            return true;
        }
    }
    function delete3(recordNo)
    {
        if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
            var i = document.getElementById("rowCount3").value;
            var x= document.getElementById('dataTable3').rows[i-1].cells;

            if(i==1){
                document.getElementById('dataTable3').deleteRow(i-1);
            }else{
                document.getElementById('dataTable3').deleteRow(recordNo-1);
            }

            document.getElementById("rowCount3").value = i-1;

            var url = '${pageContext.request.contextPath}/strata/kertas_MMK';
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');


            regenerateBill('dataTable3');
        }

    }
    function deleteRow3(recordNo,idKandungan)
    {
        // alert("recordNo:"+recordNo);
        if(confirm('Adakah anda pasti untuk menghapus data ini?')) {

            var i = document.getElementById("rowCount3").value;
            var x= document.getElementById('dataTable3').rows[i-1].cells;
            var idKandungan = x[0].innerHTML;
            // alert(idKandungan);

            if(i==1){
                document.getElementById('dataTable3').deleteRow(i-1);
            }else{
                document.getElementById('dataTable3').deleteRow(recordNo-1);
            }

            document.getElementById("rowCount3").value = i-1;
            var url = '${pageContext.request.contextPath}/strata/kertas_MMK?deleteAsasMohon&idKandungan='
                +idKandungan;

            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');

            regenerateBill('dataTable3');
        }
    }
    
    function addRow1(tableID1) {
        if(validateRow1(tableID1)==true){
            document.getElementById("rowCount1").value = 1;
            var table = document.getElementById(tableID1);

            var rowCount1 = table.rows.length;
            var row = table.insertRow(rowCount1);

            var cell2 = row.insertCell(0);
            cell2.innerHTML = "<b>"+"1." +(rowCount1+1)+"</b>";

            var cell1 = row.insertCell(1);
            var element1 = document.createElement("textarea");
            element1.t = "tujua"+(rowCount1+1);
            element1.rows = 5;
            element1.cols = 140;
            element1.name ="tujua"+(rowCount1+1);
            element1.id ="tujua"+(rowCount1+1);
            cell1.appendChild(element1);

            document.getElementById("rowCount1").value=rowCount1+1;
            var cell2 = row.insertCell(2);
            var e1 = document.createElement("img");
            e1.t = "button"+(rowCount1+1);
            e1.src = "${pageContext.request.contextPath}/images/not_ok.gif";
            e1.alt = "klik untuk batal";
            e1.align = "top";
            e1.value="Batal";
            e1.id =(rowCount1+1);
            e1.onclick=function(){delete1(e1.id);};
            cell2.appendChild(e1);
            
        }

    }
    function validateRow1(tableID2){
        var table = document.getElementById(tableID2);
        var rowCount = table.rows.length;
        if(rowCount>0){
            var txtarea = "tujua"+rowCount;
            var test = document.getElementById(txtarea).value;
            if(test==""){
                alert("Sila Simpan Sebelum Menambah Maklumat Berikutnya.");
                return false;
            }else{return true;}
        }else{
            return true;
        }
    }
    function delete1(recordNo)
    {
        if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
            var i = document.getElementById("rowCount1").value;
            var x= document.getElementById('dataTable1').rows[i-1].cells;

            if(i==1){
                document.getElementById('dataTable1').deleteRow(i-1);
            }else{
                document.getElementById('dataTable1').deleteRow(recordNo-1);
            }

            document.getElementById("rowCount1").value = i-1;

            var url = '${pageContext.request.contextPath}/strata/kertas_MMK';
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');


            regenerateBill('dataTable1');
        }

    }


    function deleteRow1(recordNo,idKandungan)
    {

        if(confirm('Adakah anda pasti untuk menghapus data ini?')) {

            var i = document.getElementById("rowCount1").value;
            var x= document.getElementById('dataTable1').rows[i-1].cells;
            // var idKandungan = x[0].innerHTML;
            //  alert(idKandungan);
            if(i==1){
                document.getElementById('dataTable1').deleteRow(i-1);
            }else{
                document.getElementById('dataTable1').deleteRow(recordNo-1);
            }

            document.getElementById("rowCount1").value = i-1;

            var url = '${pageContext.request.contextPath}/strata/kertas_MMK?deleteTujuan&idKandungan='
                +idKandungan;

            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');

            regenerateBill('dataTable1');
        }

    }
    function addRow2(tableID1) {
        if(validateRow2(tableID1)==true){
            document.getElementById("rowCount2").value = 1;
            var table = document.getElementById(tableID1);

            var rowCount1 = table.rows.length;
            var row = table.insertRow(rowCount1);

            var cell2 = row.insertCell(0);
            cell2.innerHTML = "<b>"+"2." +(rowCount1+1)+"</b>";

            var cell1 = row.insertCell(1);
            var element1 = document.createElement("textarea");
            element1.t = "latarblkg"+(rowCount1+1);
            element1.rows = 5;
            element1.cols = 140;
            element1.name ="latarblkg"+(rowCount1+1);
            element1.id ="latarblkg"+(rowCount1+1);
            cell1.appendChild(element1);

            document.getElementById("rowCount2").value=rowCount1+1;
            var cell2 = row.insertCell(2);
            var e1 = document.createElement("img");
            e1.t = "button"+(rowCount1+1);
            e1.src = "${pageContext.request.contextPath}/images/not_ok.gif";
            e1.alt = "klik untuk batal";
            e1.align = "top";
            e1.value="Batal";
            e1.id =(rowCount1+1);
            e1.onclick=function(){deleteRow2(e1.id);};
            cell2.appendChild(e1);
        }

    }
    function validateRow2(tableID2){
        var table = document.getElementById(tableID2);
        var rowCount = table.rows.length;
        if(rowCount>0){
            var txtarea = "latarblkg"+rowCount;
            var test = document.getElementById(txtarea).value;
            if(test==""){
                alert("Sila Simpan Sebelum Menambah Maklumat Berikutnya.");
                return false;
            }else{return true;}
        }else{
            return true;
        }
    }
    function delete2(recordNo)
    {
        if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
            var i = document.getElementById("rowCount2").value;
            var x= document.getElementById('dataTable2').rows[i-1].cells;

            if(i==1){
                document.getElementById('dataTable2').deleteRow(i-1);
            }else{
                document.getElementById('dataTable2').deleteRow(recordNo-1);
            }

            document.getElementById("rowCount2").value = i-1;

            var url = '${pageContext.request.contextPath}/strata/kertas_MMK';
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');


            regenerateBill('dataTable2');
        }

    }

    function deleteRow2(recordNo,idKandungan)
    {

        if(confirm('Adakah anda pasti untuk menghapus data ini?')) {

            var i = document.getElementById("rowCount2").value;
            var x= document.getElementById('dataTable2').rows[i-1].cells;
            // var idKandungan = x[0].innerHTML;
            //  alert(idKandungan);
            if(i==1){
                document.getElementById('dataTable2').deleteRow(i-1);
            }else{
                document.getElementById('dataTable2').deleteRow(recordNo-1);
            }

            document.getElementById("rowCount2").value = i-1;

            var url = '${pageContext.request.contextPath}/strata/kertas_MMK?deleteLatarbelakang&idKandungan='
                +idKandungan;

            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');

            regenerateBill('dataTable2');
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
                                <c:if test="${fn:length(actionBean.senaraiLaporanKandungan1) == 0 or actionBean.senaraiLaporanKandungan1 eq null}">
                                    <tr>
                                        <td valign="top"><b>1.1</b></td>
                                        <td>
                                            <font style="text-transform: uppercase">
                                            <s:textarea class="normal_text" name="tujua1" id="tujua1"  rows="5" cols="140" >${actionBean.tujuan1}</s:textarea>
                                            </font>

                                        </td>
                                        <td>
<!--                                            <img alt='Klik Untuk Hapus' title='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                 id="${i}" onclick="deleteRow1(${i},${line.idKandungan})" onmouseover="this.style.cursor='pointer';">-->
                                        </td>
                                    </tr>
                                    <s:hidden name="rowCount1" value="${i}" id="rowCount1"/>

                                </c:if>

                                <c:forEach items="${actionBean.senaraiLaporanKandungan1}" var="line">
                                    <tr style="font-weight:bold">
                                        <td style="display:none">${line.idKandungan}</td>
                                        <td><c:out value="1.${bil+1}"/></td>
                                        <td>

                                            <font style="text-transform: uppercase">
                                            <s:textarea class="normal_text" name="tujua${i}" id="tujua${i}"  rows="5" cols="140" >${line.kandungan}</s:textarea>
                                            </font>

                                        </td>

                                        <td>
                                            <img alt='Klik Untuk Hapus' title='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                 id="${i}" onclick="deleteRow1(${i},${line.idKandungan})" onmouseover="this.style.cursor='pointer';">
                                        </td>

                                    </tr>

                                    <c:set var="i" value="${i+1}" />
                                    <c:set var="bil" value="${bil+1}" />
                                </c:forEach>
                                <s:hidden name="rowCount1" value="${i-1}" id="rowCount1"/>
                            </table></td>
                    </tr>
                    <tr valign="top">
                        <td height="23">&nbsp;</td>
                        <td colspan="3">
                            <s:button class="btn" value="Tambah" name="add" onclick="addRow1('dataTable1')"/>
                            <s:button name="simpanTujuan" id="save" value="Simpan" class="btn" onclick="simpanData(this.name,this.form)"/>
                        </td>
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
<!--                            <s:errors/> -->
                            <table id ="dataTable2" border="0">

                                <c:set var="i" value="1" />
                                <c:set var="bil" value="0" />
                                <c:if test="${fn:length(actionBean.senaraiLaporanKandungan2) == 0 or actionBean.senaraiLaporanKandungan2 eq null}">
                                    <tr>
                                        <td valign="top"><b>2.1</b></td>
                                        <td>
                                            <font style="text-transform: uppercase">
                                            <s:textarea class="normal_text" name="latarblkg1" id="latarblkg1"  rows="5" cols="140" >${actionBean.latarbelakang1}</s:textarea>
                                            </font>

                                        </td>
                                        <td>
<!--                                            <img alt='Klik Untuk Hapus' title='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                 id="${i}" onclick="deleteRow2(${i},${line.idKandungan})" onmouseover="this.style.cursor='pointer';">-->
                                        </td>
                                    </tr>
                                    <tr>
                                        <td valign="top"><b>2.2</b></td>
                                        <td>
                                            <font style="text-transform: uppercase">
                                            <s:textarea class="normal_text" name="latarblkg2" id="latarblkg2"  rows="5" cols="140" >${actionBean.latarbelakang2}</s:textarea>
                                            </font>

                                        </td>
                                        <td>
<!--                                            <img alt='Klik Untuk Hapus' title='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                 id="${i}" onclick="deleteRow2(${i},${line.idKandungan})" onmouseover="this.style.cursor='pointer';">-->
                                        </td>
                                    </tr>

                                </c:if>
                                <c:forEach items="${actionBean.senaraiLaporanKandungan2}" var="line">
                                    <tr style="font-weight:bold">
                                        <td style="display:none">${line.idKandungan}</td>
                                        <td><c:out value="2.${bil+1}"/></td>
                                        <td>
                                            <font style="text-transform: uppercase">
                                            <s:textarea class="normal_text" name="latarblkg${i}" id="latarblkg${i}"  rows="5" cols="140" >${line.kandungan}</s:textarea>
                                            </font>

                                        </td>

                                        <td>
                                            <img alt='Klik Untuk Hapus' title='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                 id="${i}" onclick="deleteRow2(${i},${line.idKandungan})" onmouseover="this.style.cursor='pointer';">
                                        </td>

                                    </tr>

                                    <c:set var="i" value="${i+1}" />
                                    <c:set var="bil" value="${bil+1}" />
                                </c:forEach>
                                <s:hidden name="rowCount2" value="${i-1}" id="rowCount2"/>
                            </table></td>
                    </tr>
                    <tr valign="top">
                        <td height="23">&nbsp;</td>
                        <td colspan="3">
                            <s:button class="btn" value="Tambah" name="add" onclick="addRow2('dataTable2')"/>
                            <s:button name="simpanLatar" id="save" value="Simpan" class="btn" onclick="simpanData(this.name,this.form)"/>
                        </td>
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
<!--                            <s:errors/> -->
                            <table id ="dataTable3" border="0">

                                <c:set var="i" value="1" />
                                <c:set var="bil" value="0" />
                                <c:if test="${fn:length(actionBean.senaraiLaporanKandungan3) == 0 or actionBean.senaraiLaporanKandungan3 eq null}">
                                    <tr>
                                        <td valign="top"><b>3.1</b></td>
                                        <td>
                                            <font style="text-transform: uppercase">
                                            <s:textarea class="normal_text" name="asaspermohonan1" id="asaspermohonan1"  rows="5" cols="140" >${actionBean.asaspermohon1}</s:textarea>
                                                </font>

                                            </td>
                                            <td>
<!--                                                <img alt='Klik Untuk Hapus' title='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                 id="${i}" onclick="deleteRow3(${i},${line.idKandungan})" onmouseover="this.style.cursor='pointer';">-->
                                        </td>
                                    </tr>
                                    <tr>
                                        <td valign="top"><b>3.2</b></td>
                                        <td>
                                            <font style="text-transform: uppercase">
                                            <s:textarea class="normal_text" name="asaspermohonan2" id="asaspermohonan2"  rows="8" cols="140" >${actionBean.asaspermohon2}</s:textarea>
                                            </font>

                                        </td>
                                        <td>
<!--                                            <img alt='Klik Untuk Hapus' title='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                 id="${i}" onclick="deleteRow3(${i},${line.idKandungan})" onmouseover="this.style.cursor='pointer';">-->
                                        </td>
                                    </tr>
                                    <tr>
                                        <td valign="top"><b>3.3</b></td>
                                        <td>
                                            <font style="text-transform: uppercase">
                                            <s:textarea class="normal_text" name="asaspermohonan3" id="asaspermohonan3"  rows="5" cols="140">${actionBean.asaspermohon3}</s:textarea>
                                            </font>

                                        </td>
                                        <td>
<!--                                            <img alt='Klik Untuk Hapus' title='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                 id="${i}" onclick="deleteRow3(${i},${line.idKandungan})" onmouseover="this.style.cursor='pointer';">-->
                                        </td>
                                    </tr>

                                </c:if>
                                <c:forEach items="${actionBean.senaraiLaporanKandungan3}" var="line">
                                    <tr style="font-weight:bold">
                                        <td style="display:none">${line.idKandungan}</td>
                                        <td><c:out value="3.${bil+1}"/></td>
                                        <c:if test="${i ne 2}">
                                        <td>
                                            <font style="text-transform: uppercase">
                                            <s:textarea class="normal_text" name="asaspermohonan${i}" id="asaspermohonan${i}"  rows="5" cols="140" >${line.kandungan}</s:textarea>
                                            </font>

                                        </td>
                                        </c:if>
                                        <c:if test="${i eq 2}">
                                        <td>
                                            <font style="text-transform: uppercase">
                                            <s:textarea class="normal_text" name="asaspermohonan${i}" id="asaspermohonan${i}"  rows="8" cols="140" >${line.kandungan}</s:textarea>
                                            </font>

                                        </td>
                                        </c:if>

                                        <td>
                                            <img alt='Klik Untuk Hapus' title='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                 id="${i}" onclick="deleteRow3(${i},${line.idKandungan})" onmouseover="this.style.cursor='pointer';">
                                        </td>

                                    </tr>

                                    <c:set var="i" value="${i+1}" />
                                    <c:set var="bil" value="${bil+1}" />
                                </c:forEach>
                                <s:hidden name="rowCount3" value="${i-1}" id="rowCount3"/>
                            </table></td>
                    </tr>
                    <tr valign="top">
                        <td height="23">&nbsp;</td>
                        <td colspan="3">
                            <s:button class="btn" value="Tambah" name="add" onclick="addRow3('dataTable3')"/>
                            <s:button name="simpanAsasMohon" id="save" value="Simpan" class="btn" onclick="simpanData(this.name,this.form)"/>
                        </td>
                    </tr>
                </table>
            </div>
        </fieldset>
    </div>

</s:form>