<%--
    Document   : pilih_jabatan_teknikal_2
    Created on : Jul 6, 2010, 11:54:41 AM
    Author     : Siti Fariza Hanim
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

                var id="toDate"+(rowcount-1);
                var cell1 = row.insertCell(0);
                var e1 = document.createElement("INPUT");
                e1.setAttribute("type","checkbox");
                e1.setAttribute("name","pilih" +(rowcount-1));
                cell1.appendChild(e1);

                var cell2 = row.insertCell(1);
                var bil = document.createTextNode(rowcount);
                cell2.appendChild(bil);

         <%--       var cellKod = row.insertCell(2);
                var eleKod = document.createElement("INPUT");
                eleKod.setAttribute("type","text");
                eleKod.setAttribute("name","kod"+(rowcount-1));
                eleKod.setAttribute("id","kod" +(rowcount-1));
                eleKod.setAttribute("size","10");
                cellKod.appendChild(eleKod);--%>

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

                var cell6 = row.insertCell(6);
                var e7 = document.createElement("INPUT");
                e7.setAttribute("type","text");
                e7.setAttribute("name","jangkamasa"+(rowcount-1));
                e7.setAttribute("id","jangkamasa"+(rowcount-1));
                e7.setAttribute("value","14 Hari");
                e7.setAttribute("size","15");
                cell6.appendChild(e7);

          var d = new Date();
          var d = new Date(d.getTime());
          var n=14;
          var day = d.getDay();

           d.setDate(
           d.getDate() + n +
               (day === 6 ? 2 : +!day) +
               (Math.floor((n - 1 + (day % 6 || 1)) / 5) * 2));

        date = d.getDate();
        month = d.getMonth()+1;
        year = d.getFullYear();

        if(date < 10)
        {
            date = '0'+date;
        }
        if(month < 10)
        {
            month = '0'+month;
        }
        var result1 = (date)+"/"+month+"/"+year;

                var cell7 = row.insertCell(7);
                var e6 = document.createElement("INPUT");
                e6.setAttribute("type","text");
                e6.setAttribute("size","15");
                e6.setAttribute("name","jangTerima"+(rowcount-1));
                e6.setAttribute("id","jangTerima"+(rowcount-1));
                e6.setAttribute("value",result1);
                e6.onchange = function(){setDays(id,rowcount);};
                e6.onmouseover = function(){setDatepicker(id,rowcount);};
                cell7.appendChild(e6);

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
               <%--alert(t1+'----------------'+today);--%>
               var diffdays; var x=t1.split("/");
               <%--alert(x[2]+'-'+(x[1])+'-'+x[0]);--%>
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
                   diffdays= calcBusinessDays(today,selectedDate);
                   document.getElementById("jangkamasa"+(rowcount-1)).value=(diffdays)+" Hari";
               }
           }

    function calcBusinessDays(dDate1, dDate2) { // input given as Date objects
       var iWeeks, iDateDiff, iAdjust = 0;

       if (dDate2 < dDate1) return -1; // error code if dates transposed
       var iWeekday1 = dDate1.getDay(); // day of week
       var iWeekday2 = dDate2.getDay();
       iWeekday1 = (iWeekday1 == 0) ? 7 : iWeekday1; // change Sunday from 0 to 7
       iWeekday2 = (iWeekday2 == 0) ? 7 : iWeekday2;
       if ((iWeekday1 > 5) && (iWeekday2 > 5)) iAdjust = 1; // adjustment if both days on weekend
       iWeekday1 = (iWeekday1 > 5) ? 5 : iWeekday1; // only count weekdays
       iWeekday2 = (iWeekday2 > 5) ? 5 : iWeekday2;

       // calculate differnece in weeks (1000mS * 60sec * 60min * 24hrs * 7 days = 604800000)

       iWeeks = Math.floor((dDate2.getTime() - dDate1.getTime()) / 604800000)
       if (iWeekday1 <= iWeekday2) {
         iDateDiff = (iWeeks * 5) + (iWeekday2 - iWeekday1)
       } else {
         iDateDiff = ((iWeeks + 1) * 5) - (iWeekday1 - iWeekday2)
       }
     if(iWeekday1 == iWeekday2){
     iDateDiff = (1 * 5) +iDateDiff ;
      }
     iDateDiff -= iAdjust // take into account both days on weekend
     return (iDateDiff); // add 1 because dates are inclusive
   }

function deleteRow(tableid) {
         <%--alert("tableid:"+tableid)--%>
           var idRujukan;
           try {
               var table = document.getElementById(tableid);
               var rowCount = table.rows.length;
               <%--alert(rowCount);--%>
               var j=0;
               for(var i=1; i<rowCount; i++) {
                   var row = table.rows[i];

                   <%--var chkbox = row.cells[0].childNodes[0];--%>

                    var chkbox = document.getElementById("pilih"+(i+j-1));

                   if(null != chkbox && true == chkbox.checked) {
                       idRujukan = $("#idRujukan"+(i+j-1)).val();
                      <%--alert("idRujukan:"+idRujukan);--%>
                       <%--var idDokTt = $("#idDokTt"+(i+j-1)).val();--%>
                       <%--alert("idDokTt:"+idDokTt);--%>
                       <%-- alert(i+j-1);
                        alert(document.getElementById("idRujukan"+(i+j-1)));
                        alert(idRujukan);--%>

                            if(document.getElementById("idRujukan"+(i+j-1)) != null){

                         <%--alert("javacall");--%>
                               var url = '${pageContext.request.contextPath}/pengambilan/jabatan_teknikal12?deleteRujukan&idRujukan='
                                   +idRujukan;
                                   <%--alert("url"+url)--%>
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
        <%--var idDokTt = $("#idDokTt"+row).val();--%>
        <%--alert('idDokTt--'+idDokTt);--%>
        <%--alert(idRujukan);--%>
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

               <s:select name="ditundatangan" id="namapguna" >
                           <s:option label="Sila Pilih" value="" />                          
                           <c:forEach items="${list.senaraiPengguna}" var="i" >                              
                               <s:option value="${i.idPengguna}" >${i.jawatan} - ${i.nama}</s:option>
                           </c:forEach>
                       </s:select>
                     </td></tr><br/><br/>


            <legend>Senarai Jabatan Teknikal  <c:if test="${actionBean.kNegeri eq '04'}">Dan YB Adun</c:if> yang terlibat</legend>

            <p>Klik butang Tambah untuk memilih Jabatan Teknikal  <c:if test="${actionBean.kNegeri eq '04'}">Dan YB Adun</c:if> yang terlibat.</p>


            <s:hidden name="recordCount" id="recordCount"/>
            <div class="content" align="center">
                <table  width="90%" id="tbl" class="tablecloth">
                    <tr>
                        <th  width="5%" align="center"><b>Pilih</b></th>
                        <th  width="5%" align="center"><b>Bil</b></th>
                        <%--<th  width="8%" align="center"><b>Kod</b></th>--%>
                        <th  width="40%" height="50px" align="center"><b>Nama Jabatan</b></th>
                        <th  width="15%" align="center"><b>Salinan Kepada</b></th>
                        <th  width="15%" align="center"><b>Catatan</b></th>
                        <%--<th  width="15%" align="center"><b>Ditundatangan oleh</b></th>--%>
                        <th  width="15%" align="center"><b>Tarikh Hantar</b></th>
                        <th  width="15%" align="center"><b>Jangka Masa (Hari)</b></th>
                        <th  width="15%" align="center"><b>Tarikh Jangka Terima</b></th>


                        <th width="5%" align="center"><b>Mandatori</b></th>
                        <th width="5%" align="center"><b>Tambah Dokumen</b></th>
                        <th width="5%" align="center"><b>Hapus</b></th>
                    </tr>
                    <c:set value="0" var="i"/>
                    <c:forEach items="${actionBean.senaraiRujukanLuar}" var="line">
                        <tr style="font-weight:bold">
                            <td><div align="center"><s:checkbox name="pilih${i}" id="pilih${i}" /> </div></td>
                            <td><c:out value="${i+1}"/></td>
                            <%--<td><s:text name="kod${i}" id="kod${i}" value="${line.agensi.kod}" size="8" /></td>--%>
                            <td><font style="text-transform: uppercase">
                                    <s:textarea class="textBesa" name="namaJabatan${i}" id="namaJabatan${i}" onkeyup="this.value=this.value.toUpperCase();" value="${line.namaAgensi}" ></s:textarea>
                                    <s:button class="btn goright" value="Cari" name="add" onclick="javascript:search(${i})" />
                                    <s:hidden name="kod${i}" id="kod${i}" value="${line.agensi.kod}" />
                                </font></td>
                                <c:if test="${fn:length(line.senaraiSalinan) == 0}">
                                    <td width="15"><s:text id="salinanKod${i}" name="salinanKod${i}" value="" size="8" />
                                       <s:textarea class="textBesa" name="salinanKepada${i}" id="salinanKepada${i}" onkeyup="this.value=this.value.toUpperCase();" value=""></s:textarea>
                                       <s:button class="btn goright" value="Cari" name="button2" onclick="javascript:search2(${i})" /></td>
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
                            <td><s:text name="jangkamasa${i}" id="jangkamasa${i}" value="${line.tempohHari} Hari" size="15"/></td>
                            <td><s:text name="jangTerima${i}" id="jangTerima${i}" formatPattern="dd/MM/yyyy" size="15" value="${line.tarikhJangkaTerima}" onmouseover="javascript:setDatepicker(id,${i+1})" onchange="javascript:setDays(id,${i+1})"/></td>
                            <td><div align="center"> <input type="checkbox" name="mandatori${i}" id="mandatori${i}" value="${line.ulasanMandatori}" onchange="setValue(${i})" /> </div></td>
                            <td>
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
                    <s:button class="btn" value="Tambah" name="add" onclick="addRow('tbl')" />
                    <s:button class="btn" value="Hapus" name="delete" onclick="deleteRow('tbl')"/>
                    <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>

                </p>
            </div>
        </fieldset>
    </div>
    <br>
</s:form>



