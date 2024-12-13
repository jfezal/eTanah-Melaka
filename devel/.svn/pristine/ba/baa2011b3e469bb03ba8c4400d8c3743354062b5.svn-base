<%-- 
    Document   : pilih_jabatan_teknikal_NS
    Created on : Apr 20, 2011, 2:20:25 PM
    Author     : Rohans
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
 <s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>

<head>

    <link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
    <script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>

    <script language="javascript" type="text/javascript"
    src="${pageContext.request.contextPath}/src/main/webapp/pub/scripts/datepicker.js"></script>

<script language="javascript" type="text/javascript"src="${pageContext.request.contextPath}/src/main/webapp/pub/scripts/datepicker.js"></script>

<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/src/main/webapp/pub/js/datetimepicker.js"></script>


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

        .goright{float:right; margin: 5px}

        .textBesa{ width:220px;height: 60px;}
        input {height: 40px; text-align:center;}
    </style>

    <script type = "text/javascript">


        $(document).ready(function() {
            var i = 0;
         <%--   <c:forEach var="tandatanganDok" items="${actionBean.tandatanganDokumen}">
                    $("#ditundatangan"+i).val(${tandatanganDok.diTandatangan});
                    $("#idDokTt"+i).val(${tandatanganDok.idDokTt});
                    i = i+1;
            </c:forEach>--%>

           checkAll();
         });

         function checkAll(){
             $("#a").checked = true;
              var table = document.getElementById("tbl");
                var rowCount = table.rows.length;
                 <%--alert("rowCount"+rowCount);--%>
                var j=0;
                for(var i=0; i<rowCount; i++) {
                    var row = table.rows[i];
                        var chkbox1 = document.getElementById("mandatori"+i);
                        if(chkbox1!=null && chkbox1.value == 'Y'){
                            chkbox1.checked = true;
                        }
                }
         }


        function search(index){
            // alert("search:"+index);
            var url = '${pageContext.request.contextPath}/pengambilan/jabatan_teknikal12?kodAgensiPopup&index='+index;
            window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=910,height=800,scrollbars=yes");
        }

        function search2(index){
            // alert("search:"+index);
            var url = '${pageContext.request.contextPath}/pengambilan/jabatan_teknikal12?kodAgensiPopup2&index='+index;
            window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=910,height=800,scrollbars=yes");
        }

        function addDok(val){
            var url = '${pageContext.request.contextPath}/pengambilan/jabatan_teknikal12?addDokumen&idRujukan='+val;
            window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=910,height=800,scrollbars=yes");
        }

        function addRow (tableid){

            var table = document.getElementById (tableid);
            var rowcount = table.rows.length;
            var row = table.insertRow(rowcount);
            document.getElementById("recordCount").value =rowcount;

    <%--    var id="toDate"+(rowcount-1);
            var cell1 = row.insertCell(0);
            var e1 = document.createElement("INPUT");
            e1.setAttribute("type","checkbox");
            e1.setAttribute("name","pilih" +(rowcount-1));
            cell1.appendChild(e1);--%>

            var cell2 = row.insertCell(1);
            var bil = document.createTextNode(rowcount);
            cell2.appendChild(bil);

        var cellKod = row.insertCell(2);
        var eleKod = document.createElement("INPUT");
        eleKod.setAttribute("type","text");
        eleKod.setAttribute("name","kod"+(rowcount-1));
        eleKod.setAttribute("id","kod" +(rowcount-1));
        eleKod.setAttribute("size","10");
        cellKod.appendChild(eleKod);

        var cell3 = row.insertCell(2);
        var e3 = document.createElement("INPUT");
        <%--alert(e3);--%>
        e3.setAttribute("type","textarea");
        e3.setAttribute("name","namaJabatan"+(rowcount-1));
        e3.setAttribute("id","namaJabatan" +(rowcount-1));
        e3.setAttribute("class","textBesa");
        cell3.appendChild(e3);
        var e13 = document.createElement("INPUT");
        <%--alert(e3);--%>
        e13.setAttribute("type","hidden");
        e13.setAttribute("name","kod"+(rowcount-1));
        e13.setAttribute("id","kod" +(rowcount-1));
        e13.setAttribute("size","10");
        cell3.appendChild(e13);

        var btn1 = document.createElement("INPUT");
        <%--alert(btn1)--%>
        btn1.setAttribute("type","button");
        btn1.onclick = function (){javascript:search(rowcount-1);};
        btn1.className="btn goright";
        btn1.value ="Cari"
        btn1.id1="button1";
        cell3.appendChild(btn1);


            var cell4 = row.insertCell(3);
            var eleKod = document.createElement("INPUT");
            eleKod.setAttribute("type","text");
            eleKod.setAttribute("name","salinanKod"+(rowcount-1));
            eleKod.setAttribute("id","salinanKod" +(rowcount-1));
            eleKod.setAttribute("size","10");
            cell4.appendChild(eleKod);



            var e4 = document.createElement("INPUT");
            e4.setAttribute("type","textarea");
            e4.setAttribute("name","salinanKepada"+(rowcount-1));
            e4.setAttribute("id","salinanKepada" +(rowcount-1));
            e4.setAttribute("class","textBesa");
            cell4.appendChild(e4);

            var btn2 = document.createElement("INPUT");
            btn2.setAttribute("type","button");
            btn2.setAttribute("name","button2" +(rowcount-1));
            btn2.onclick = function (){javascript:search2(rowcount-1);};
            btn2.className="btn goright";
            btn2.value ="Cari"
            btn2.id1="button2";
            cell4.appendChild(btn2);

            var cell10 = row.insertCell(4);
            var e10 = document.createElement("INPUT");
            e10.setAttribute("type","text");
            e10.setAttribute("name","catatan"+(rowcount-1));
            e10.setAttribute("id","catatan"+(rowcount-1));
            e10.setAttribute("size","15");
            cell10.appendChild(e10);

         <%--   var cell11 = row.insertCell(5);
            var e11 = document.createElement("INPUT");
            e11.setAttribute("selected", "selected");
            e11.setAttribute("name","ditundatangan"+(rowcount-1));
            e11.setAttribute("id","ditundatangan"+(rowcount-1));
            e11.setAttribute("size","15");
            cell11.appendChild(e11);--%>


            //////////////////////////

      <%--  var cell11 = row.insertCell(5);
        var selectbox = document.createElement("SELECT");
        selectbox.setAttribute("name", "ditundatangan"+(rowcount-1));
        selectbox.setAttribute("id","ditundatangan"+(rowcount-1));
        var optn1 = document.createElement("OPTION");
        optn1.text = "--Sila Pilih--";
        optn1.value = " ";
        selectbox.options.add(optn1);

        <c:forEach items="${list.senaraiPeranan}" var="list1" >
            var optn2 = document.createElement("OPTION");
            optn2.text = "${list1.nama}";
            optn2.value = "${list1.kod}";
            selectbox.options.add(optn2);
       </c:forEach>
        cell11.appendChild(selectbox);
--%>
            ///////////////////////////////

            var cell5 = row.insertCell(5);
            var e5 = document.createElement("INPUT");
            e5.setAttribute("type","text");
            e5.setAttribute("name","tarikhHantar" +(rowcount-1));
            e5.setAttribute("id","tarikhHantar" +(rowcount-1));
            e5.setAttribute("size","15");


            var todates=new Date();
            var date = todates.getDate();
            var month = todates.getMonth()+1;
            var year = todates.getFullYear();

            if(date < 10)
            {
                date = '0'+date;
            }
            if(month < 10)
            {
                month = '0'+month;
            }
            var result = date+"/"+month+"/"+year;
            e5.setAttribute("value", result);
            e5.setAttribute("formatPattern", "dd/mm/yyyy");
            e5.setAttribute("formatType", "date");
            cell5.appendChild(e5);

            todates=new Date();
            todates.setDate(todates.getDate()+8);
            date = todates.getDate();
            month = todates.getMonth()+1;
            year = todates.getFullYear();

            if(date < 10)
            {
                date = '0'+date;
            }
            if(month < 10)
            {
                month = '0'+month;
            }
            var result1 = date+"/"+month+"/"+year;

            var cell6 = row.insertCell(6);
            var e6 = document.createElement("INPUT");
            e6.setAttribute("type","text");
            e6.setAttribute("size","15");
            e6.setAttribute("name","jangTerima"+(rowcount-1));
            e6.setAttribute("id","jangTerima"+(rowcount-1));
            e6.setAttribute("value",result1);
            e6.onchange = function(){setDays(id,rowcount);};
            e6.onmouseover = function(){setDatepicker(id,rowcount);};
            cell6.appendChild(e6);

            var cell7 = row.insertCell(7);
            var e7 = document.createElement("INPUT");
            e7.setAttribute("type","text");
            e7.setAttribute("name","jangkamasa"+(rowcount-1));
            e7.setAttribute("id","jangkamasa"+(rowcount-1));
            e7.setAttribute("value","8 Hari");
            e7.setAttribute("size","15");
            cell7.appendChild(e7);

            var cell8 = row.insertCell(8);
            var e8 = document.createElement("INPUT");
            e8.setAttribute("type","checkbox");
            e8.setAttribute("name","mandatori" +(rowcount-1));
            e8.setAttribute("id","mandatori" +(rowcount-1));
            e8.setAttribute("value","Y");
            cell8.appendChild( e8);



        }


        function setDatepicker(id,rowcount){
            $("#jangTerima"+(rowcount-1)).datepicker();
        }


        function setDays(id,rowcount){
            var today=new Date();
            var selectedDate;
            //Set 1 day in milliseconds
            var one_day=1000*60*60*24
            var diffdays;
            t1=document.getElementById("jangTerima"+(rowcount-1)).value;
            var diffdays; var x=t1.split("/");
            selectedDate= new Date(x[2],(x[1]-1),x[0]);
            if(selectedDate == "" || selectedDate == null || selectedDate == "Invalid Date")return false;
            if(selectedDate != "") {
                //Calculate difference btw the two dates, and convert to days
                if((selectedDate.getTime()) < (today.getTime())){
                    document.getElementById("jangTerima"+(rowcount-1)).value="";
                    document.getElementById("jangkamasa"+(rowcount-1)).value="";
                    alert("Tarikh tidak sah. Sila pilih tarikh yang lain");
                    return false;
                }
                diffdays= Math.ceil(((selectedDate.getTime()) - (today.getTime()))/(one_day));
                document.getElementById("jangkamasa"+(rowcount-1)).value=(diffdays)+" Hari";
            }
        }




function deleteRow(tableid) {
    alert("tableid:"+tableid)
           var idRujukan;
           try {
               var table = document.getElementById(tableid);
               var rowCount = table.rows.length;
               alert(rowCount);
               var j=0;
               for(var i=0; i<rowCount; i++) {
                   var row = table.rows[i];
                   var chkbox = row.cells[0].childNodes[0];
                   if(null != chkbox && true == chkbox.checked) {
                       idRujukan = $("#idRujukan"+(i+j-1)).val();

                            if(document.getElementById("idRujukan"+(i+j-1)) != null){

                               var url = '${pageContext.request.contextPath}/pengambilan/jabatan_teknikal12?deleteRujukan&idRujukan='
                                   +idRujukan;
                                   alert("url"+url)
                               $.get(url,
                               function(data){
                                   $('#page_div').html(data);
                               },'html');
                           }

                           table.deleteRow(i);
                           rowCount--;
                           i--;
                           j++;

                       }
                   }
               }catch(e) {
                   alert(e);
               }
               regenerateBill(tableid);
           }

            function regenerateBill(tableid){
                try{
                    var table = document.getElementById(tableid);
                    var rowCount = table.rows.length;
                    document.getElementById("recordCount").value =rowCount-1;

                    if(rowCount > 1){
                        for(var i=1;i<rowCount;i++){
                            table.rows[i].cells[1].childNodes[0].data= i;
                        }
                    }

                }catch(e){
                    alert("Error in regenerateBill");
                }
            }


            function removePermohonanRujukanLuar(idRujukan,row){
                if(confirm('Adakah anda     pasti?')) {
                    var url = '${pageContext.request.contextPath}/pengambilan/jabatan_teknikal12?deleteRujukan&idRujukan='
                        +idRujukan;
                    $.get(url,
                    function(data){
                        $('#page_div').html(data);
                    },'html');
                }
            }


            $(document).ready(function() {

                $("#datepicker123").datepicker();

            });

    function toggleSelectDocument(selectInput, i){
                if(selectInput.checked){
                        if ($('#bilSalinan' + i).val() == ''){
                                $('#bilSalinan' + i).val(1);
                        }
                } else{
                        var c = document.getElementById("semua");
                        if (c.checked) c.checked = false;

                        if ($('#bilSalinan' + i).val() != null){
                                $('#bilSalinan' + i).val('');
                        }
                }
        }

    function  setValue(i){
        $('#mandatori' + i).val('Y');
    }




    </script>
</head>
<s:form beanclass="etanah.view.stripes.pengambilan.JabatanTeknikal2ActionBean" name="teknikal">

    <s:messages/>
    <s:errors/>

    <div class="subtitle">
        <fieldset class="aras1">

            &nbsp;&nbsp;&nbsp;&nbsp;<tr><td><b>Ditandatangan oleh :</b></td></tr>
            <tr><td align="left">
                    <s:select name="ditundatangan1" id="ditundatangan" >
                   <s:option value="0">--Sila Pilih--</s:option>
                    <s:options-collection collection="${list.senaraiPeranan}" label="nama" value="kod" />
                </s:select>
                    <s:hidden name="idDokTt" id="idDokTt"/>
            </td></tr><br/><br/>

            <legend>Senarai Jabatan Teknikal yang terlibat</legend>

            <p>Klik butang Tambah untuk memilih Jabatan Teknikal yang terlibat.</p>


            <s:hidden name="recordCount" id="recordCount"/>
            <div class="content" align="center">
                <table  width="90%" id="tbl" class="tablecloth">
                    <tr>
                        <%--<th  width="5%" align="center"><b>Pilih</b></th>--%>
                        <th  width="5%" align="center"><b>Bil</b></th>
                        <%--<th  width="8%" align="center"><b>Kod</b></th>--%>
                        <th  width="40%" height="50px" align="center"><b>Nama Jabatan</b></th>
                        <th  width="15%" align="center"><b>Salinan Kepada</b></th>
                        <th  width="15%" align="center"><b>Catatan</b></th>
                        <%--<th  width="15%" align="center"><b>Ditundatangan oleh</b></th>--%>
                        <th  width="15%" align="center"><b>Tarikh Hantar</b></th>
                        <th  width="15%" align="center"><b>Tarikh Jangka Terima</b></th>
                        <th  width="15%" align="center"><b>Jangka Masa (Hari)</b></th>

                        <th width="5%" align="center"><b>Mandatori</b></th>
                        <th width="5%" align="center"><b>Tambah Dokumen</b></th>
                        <th width="5%" align="center"><b>Hapus</b></th>
                    </tr>
                    <c:set value="0" var="i"/>
                    <c:forEach items="${actionBean.senaraiRujukanLuar}" var="line">
                        <tr style="font-weight:bold">
                            <%--<td><div align="center"><s:checkbox name="pilih${i}" id="pilih${i}" /> </div></td>--%>
                            <td><c:out value="${i+1}"/></td>
                            <%--<td><s:text name="kod${i}" id="kod${i}" value="${line.agensi.kod}" size="8" /></td>--%>
                            <td><font style="text-transform: uppercase">
                                    <s:textarea class="textBesa" name="namaJabatan${i}" id="namaJabatan${i}" onkeyup="this.value=this.value.toUpperCase();" value="${line.namaAgensi}" ></s:textarea>
                                    <%--<s:button class="btn goright" value="Cari" name="add" onclick="javascript:search(${i})" />--%>
                                    <s:hidden name="kod${i}" id="kod${i}" value="${line.agensi.kod}" />
                                </font></td>
                                 <c:if test="${fn:length(line.senaraiSalinan) == 0}">
                                    <td>&nbsp;</td>
                                </c:if>
                                <c:if test="${line.senaraiSalinan ne ' '}">
                                <c:forEach items="${line.senaraiSalinan}" var="sal">
                                             <td>
                                    <s:text id="salinanKod${i}" name="salinanKod${i}"  value="${sal.kodAgensi.kod}"  size="8" />
                                    <s:textarea class="textBesa" name="salinanKepada${i}" id="salinanKepada${i}" onkeyup="this.value=this.value.toUpperCase();" value="${sal.kodAgensi.nama}"></s:textarea>
                                    <s:button class="btn goright" value="Cari" name="button2" onclick="javascript:search2(${i})" /></td>

                                </c:forEach>
                                </c:if>
                                <td><s:text name="catatan${i}" id="catatan${i}" value="${line.catatan}"  size="15" /></td>

                            <td><s:text name="tarikhHantar${i}" id="tarikhHantar${i}" value="${line.tarikhDisampai}" formatPattern="dd/MM/yyyy" size="15" /></td>
                            <td><s:text name="jangTerima${i}" id="jangTerima${i}" formatPattern="dd/MM/yyyy" size="15" value="${line.tarikhJangkaTerima}" onmouseover="javascript:setDatepicker(id,${i+1})" onchange="javascript:setDays(id,${i+1})"/></td>
                            <td><s:text name="jangkamasa${i}" id="jangkamasa${i}" value="${line.tempohHari} Hari" size="15"/></td>
                            <td ><div align="center"> <input type="checkbox" name="mandatori${i}" id="mandatori${i}" value="${line.ulasanMandatori}" onchange="setValue(${i})" /> </div></td>
                            <td >
                                <div align="center">
                                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/tambah.png' class='rem'
                                         id='rem_${line_rowNum}' onclick="addDok('${line.idRujukan}')"  onmouseover="this.style.cursor='pointer';">
                                </div></td>
                            <td>
                                <div align="center">
                                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/pub/images/not_ok.gif' class='rem'
                                         id='rem_${line_rowNum}' onclick="removePermohonanRujukanLuar('${line.idRujukan}','${i}')"  onmouseover="this.style.cursor='pointer';">
                                </div>
                            </td>

                            <s:hidden name="idRujukan${i}" id="idRujukan${i}" value="${line.idRujukan}" />
                        </tr>
                        <c:set var="i" value="${i+1}" />
                    </c:forEach>

                </table>

                <table width="80%">
                    <tr><td align="right">
                </table>
                <p align="center">
                    <%--<s:button class="btn" value="Tambah" name="add" onclick="addRow('tbl')" />--%>
                    <%--<s:button class="btn" value="Hapus" name="delete" onclick="deleteRow('tbl')"/>--%>
                    <s:button name="simpanNS" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>

                </p>
            </div>
        </fieldset>
    </div>
    <br>
   
</s:form>





