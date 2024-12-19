<%--
    Document   : dev_Ringkasan_Rayuan
    Created on : Jul 5, 2010, 9:18:22 AM
    Author     : NageswaraRao
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

<script type = "text/javascript">

    function addJumlahHeader(id1,count){
         id1.appendChild(document.createElement('br'));
         id1.appendChild(document.createElement('br'));

         var e1 = document.createElement ("INPUT");
         e1.setAttribute("Type", "text");
         e1.setAttribute("size","80");
         e1.setAttribute("name","jenis"+(count)+".1");
         e1.setAttribute("id","jenis" +(count)+".1");
         //e1.setAttribute("value","jenis" +(count)+".1");
         id1.appendChild(e1);

         id1.appendChild(document.createElement('br'));
         id1.appendChild(document.createElement('br'));

         var e2 = document.createElement ("INPUT");
         e2.setAttribute("Type", "text");
         e2.setAttribute("size","63");
         e2.setAttribute("name","jenis"+(count)+".2");
         e2.setAttribute("id","jenis" +(count)+".2");
         //e2.setAttribute("value","jenis" +(count)+".2");
         id1.appendChild(e2);
         var t1 =document.createTextNode('  :  ');
         id1.appendChild(t1);

         var e3 = document.createElement ("INPUT");
         e3.setAttribute("Type", "text");
         e3.setAttribute("size","10");
         e3.setAttribute("name","jenis"+(count)+".3");
         e3.setAttribute("id","jenis" +(count)+".3");
         //e3.setAttribute("value","jenis" +(count)+".3");
         id1.appendChild(e3);

         id1.appendChild(document.createElement('br'));

         var e4 = document.createElement ("INPUT");
         e4.setAttribute("Type", "text");
         e4.setAttribute("size","63");
         e4.setAttribute("name","jenis"+(count)+".4");
         e4.setAttribute("id","jenis" +(count)+".4");
         //e4.setAttribute("value","jenis" +(count)+".4");
         id1.appendChild(e4);
         var t2 =document.createTextNode('  :  ');
         id1.appendChild(t2);

         var e5 = document.createElement ("INPUT");
         e5.setAttribute("Type", "text");
         e5.setAttribute("size","10");
         e5.setAttribute("name","jenis"+(count)+".5");
         e5.setAttribute("id","jenis" +(count)+".5");
         //e5.setAttribute("value","jenis" +(count)+".5");
         id1.appendChild(e5);

         id1.appendChild(document.createElement('br'));

         var e6 = document.createElement ("INPUT");
         e6.setAttribute("Type", "text");
         e6.setAttribute("size","63");
         e6.setAttribute("name","jenis"+(count)+".6");
         e6.setAttribute("id","jenis" +(count)+".6");
         //e6.setAttribute("value","jenis" +(count)+".6");
         id1.appendChild(e6);
         var t3 =document.createTextNode('  :  ');
         id1.appendChild(t3);

         var e7 = document.createElement ("INPUT");
         e7.setAttribute("Type", "text");
         e7.setAttribute("size","10");
         e7.setAttribute("name","jenis"+(count)+".7");
         e7.setAttribute("id","jenis" +(count)+".7");
         //e7.setAttribute("value","jenis" +(count)+".7");
         id1.appendChild(e7);

         id1.appendChild(document.createElement('br'));

         var e8 = document.createElement ("INPUT");
         e8.setAttribute("Type", "text");
         e8.setAttribute("size","63");
         e8.setAttribute("name","jenis"+(count)+".8");
         e8.setAttribute("id","jenis" +(count)+".8");
         //e8.setAttribute("value","jenis" +(count)+".8");
         id1.appendChild(e8);
         var t4 =document.createTextNode('  :  ');
         id1.appendChild(t4);

         var e9 = document.createElement ("INPUT");
         e9.setAttribute("Type", "text");
         e9.setAttribute("size","10");
         e9.setAttribute("name","jenis"+(count)+".9");
         e9.setAttribute("id","jenis" +(count)+".9");
         //e9.setAttribute("value","jenis" +(count)+".9");
         id1.appendChild(e9);

         id1.appendChild(document.createElement('br'));

         var e10 = document.createElement ("INPUT");
         e10.setAttribute("Type", "text");
         e10.setAttribute("size","63");
         e10.setAttribute("name","jenis"+(count)+".10");
         e10.setAttribute("id","jenis" +(count)+".10");
         //e10.setAttribute("value","jenis" +(count)+".10");
         id1.appendChild(e10);
         var t5 =document.createTextNode('  :  ');
         id1.appendChild(t5);

         var e11 = document.createElement ("INPUT");
         e11.setAttribute("Type", "text");
         e11.setAttribute("size","10");
         e11.setAttribute("name","jenis"+(count)+".11");
         e11.setAttribute("id","jenis" +(count)+".11");
         //e11.setAttribute("value","jenis" +(count)+".11");
         id1.appendChild(e11);

         id1.appendChild(document.createElement('br'));

         var e12 = document.createElement ("INPUT");
         e12.setAttribute("Type", "text");
         e12.setAttribute("size","63");
         e12.setAttribute("name","jenis"+(count)+".12");
         e12.setAttribute("id","jenis" +(count)+".12");
         //e12.setAttribute("value","jenis" +(count)+".12");
         id1.appendChild(e12);
         var t6 =document.createTextNode('  :  ');
         id1.appendChild(t6);

         var e13 = document.createElement ("INPUT");
         e13.setAttribute("Type", "text");
         e13.setAttribute("size","10");
         e13.setAttribute("name","jenis"+(count)+".13");
         e13.setAttribute("id","jenis" +(count)+".13");
         //e13.setAttribute("value","jenis" +(count)+".13");
         id1.appendChild(e13);
    }




     var i=1;
     function addTable(divId){
        i++;
        var id1 = document.getElementById(divId);
        var count = document.getElementById ("count").value;
        // Increment table count by 1
        document.getElementById ("count").value = parseInt(count)+1;
        count = document.getElementById ("count").value;
        
       // alert("table Count:"+count);
        // add Rumah Berkambar Tingkat
        addJumlahHeader(id1,count);

        // create New table
        var table = document.createElement("TABLE");
        table.className = "tablecloth";
        table.width="100%";
        table.border=2;
        var rowCount = table.rows.length;
        var row = table.insertRow(rowCount);

        var cell0 = row.insertCell(0);
        cell0.innerHTML = "<font color='white'><b>JUMLAH</b></font>";
        cell0.style.textAlign = "center";
        cell0.style.backgroundColor = ("#328aa4");
        cell0.width="10%";

        var cell1 = row.insertCell(1);
        cell1.innerHTML = "<font color='white'><b>PELEPASAN</b></font>";
        cell1.style.textAlign = "center";
        cell1.style.backgroundColor = "#328aa4";
        cell1.width="50%";

        var cell2 = row.insertCell(2);
        cell2.innerHTML = "<font color='white'><b>BUMI<br>(40%)</b></font>";
        cell2.style.textAlign = "center";
        cell2.style.backgroundColor = "#328aa4";
        cell2.width="20%";

        var cell3 = row.insertCell(3);
        cell3.innerHTML = "<font color='white'><b>BUKAN BUMI<br>(60%)</b></font>";
        cell3.style.textAlign = "center";
        cell3.style.backgroundColor = "#328aa4";
        cell3.width="20%";

        id1.appendChild(table);
        id1.appendChild(document.createElement('br'));

        // Hidden field to get the recordcount of each table
        var e4 = document.createElement ("input");
        e4.setAttribute("type", "hidden");
        e4.setAttribute("id", "count"+(count));
        e4.setAttribute("name", "count"+(count));
        e4.setAttribute("value", 1);
        id1.appendChild(e4);

        // Another hidden fields
        var e5 = document.createElement ("input");
        e5.setAttribute("type", "hidden");
        e5.setAttribute("id", "testCount"+(count));
        e5.setAttribute("name", "testCount"+(count));
        e5.setAttribute("value", 0);
        id1.appendChild(e5);


        id1.appendChild(document.createElement('br'));
        // Tambah button
        var e3 = document.createElement("INPUT");
        e3.setAttribute("type","button");
        e3.className="btn";
        e3.value ="Tambah";
        e3.onclick=function(){addRow('table'+(e4.id),(e4.id));};
        id1.appendChild(e3);
        // Hapus button
        var e5 = document.createElement("INPUT");
        e5.setAttribute("type","button");
        e5.className="btn";
        e5.value ="Hapus";
        e5.onclick=function(){deleteRow('table'+(e4.id),(e4.id));};
        id1.appendChild(e5);


        var row1 = table.insertRow(rowCount+1);
        var cell10 = row1.insertCell(0);
        var e11 = document.createElement ("textarea");
       // alert("jumlah:"+count+".1");
        e11.setAttribute("name",count+".1");
        e11.setAttribute("id",count+".1");
       // e11.setAttribute("value",count+".1");
        e11.style.height = "72px";
        e11.style.width = "120px";
        cell10.appendChild(e11);

        var cell11 = row1.insertCell(1);
        cell11.colSpan ="3";

        var table1 = document.createElement("TABLE");
        cell11.appendChild(table1);
        table1.border = 1;
        table1.className = "tablecloth";
        table1.width="100%";
        table1.border=2;
        table1.id = "table"+(e4.id);
        standardRow(table1.id);
    }


    function deleteRow(tableid,recordCount){

        var table = document.getElementById(tableid);
        var rowcount = table.rows.length;
      //  alert("rowcount:"+rowcount);

        var rowIndex = document.getElementById(recordCount).value;
        var tableIndex = parseInt(recordCount.substring(5));
      //  alert("tableid:"+tableid);
      //  alert("tableIndex:"+tableIndex);
      //  alert("rowIndex:"+rowIndex);

         if(rowIndex > 1){

         document.getElementById(tableid).deleteRow(rowIndex);        
       // alert(document.getElementById(tableid).id);        
        document.getElementById(recordCount).value = parseInt(document.getElementById(recordCount).value)-1;
       // alert("rowIndex:"+rowIndex);
        
        var testCount = document.getElementById("testCount"+tableIndex).value;
     //   alert("testCount:"+testCount);
        if(parseInt(rowIndex) <= parseInt(testCount)){       
       //     alert("javacall");
        
        var url = '${pageContext.request.contextPath}/pembangunan/pecahSempadan/dev_Ringkasan_Rayuan?deleteRecords&tableIndex='+tableIndex
            +'&rowIndex='+rowIndex;

           // alert(url);
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
        }
      }else{
         alert("Row can not be deleted");
      }
    }



    function addRow(tableid,recordCountId){

      //  alert("tableid:"+tableid);
      //  alert("recordCount1:"+recordCountId);
        var table = document.getElementById(tableid);
        var rowcount = table.rows.length;
        var row = table.insertRow(rowcount);

        var recordCount = document.getElementById(recordCountId).value;
        document.getElementById(recordCountId).value = parseInt(recordCount)+1;
        recordCount = (parseInt(recordCount)*3)+2;
      //  alert("Record count:"+recordCount);
        var tableCount = parseInt(recordCountId.substring(5));

        var cell2 = row.insertCell (0);
        var e2 = document.createElement ("INPUT");
        e2.setAttribute("Type", "text");
        e2.setAttribute("size","74");
        e2.setAttribute("name",tableCount+"."+recordCount);
        e2.setAttribute("id",tableCount+"."+recordCount);
        //e2.setAttribute("value",tableCount+"."+recordCount);
        cell2.appendChild (e2);

        recordCount = parseInt(recordCount)+1;
        var cell3 = row.insertCell(1);
        var e3 = document.createElement("INPUT");
        e3.setAttribute("type","text");
        e3.setAttribute("size","28");
        e3.setAttribute("name",tableCount+"."+recordCount);
        e3.setAttribute("id",tableCount+"."+recordCount);
        //e3.setAttribute("value",tableCount+"."+recordCount);
        cell3.appendChild(e3);

        recordCount = parseInt(recordCount)+1;
        var cell4 = row.insertCell(2);
        var e4 = document.createElement("INPUT");
        e4.setAttribute("type","text");
        e4.setAttribute("size","28");
        e4.setAttribute("name",tableCount+"."+recordCount);
        e4.setAttribute("id",tableCount+"."+recordCount);
        //e4.setAttribute("value",tableCount+"."+recordCount);
        cell4.appendChild(e4);

        var obj=document.getElementById(tableCount+".1");
        if(rowcount > 6)
            obj.setAttribute("rows", tableCount*2-3);
        else
            obj.setAttribute("rows", tableCount*2);
    }
    function standardRow (tableid){
       // alert("tableid: Standard"+tableid);
        
        var table = document.getElementById (tableid);
        var rowcount1 = table.rows.length;
        var row1 = table.insertRow(rowcount1);
        var rowcount = table.rows.length;
        var row = table.insertRow(rowcount);

        var count = document.getElementById("count").value;
       // alert("tableIndex:"+count);
        
        var cell2 = row.insertCell (0);
        var e2 = document.createElement ("INPUT");
        e2.setAttribute("Type", "text");
        e2.setAttribute("size","74");
        e2.setAttribute("name",count+".2");
        e2.setAttribute("id",count+".2");
       // e2.setAttribute("value",count+".2");
        cell2.width="40%";
        cell2.appendChild (e2);

        var cell3 = row.insertCell(1);
        var e3 = document.createElement("INPUT");
        e3.setAttribute("type","text");
        e3.setAttribute("size","28");
        e3.setAttribute("name",count+".3");
        e3.setAttribute("id",count+".3");
       // e3.setAttribute("value",count+".3");
        cell3.width="25%";
        cell3.appendChild(e3);

        var cell4 = row.insertCell(2);
        var e4 = document.createElement("INPUT");
        e4.setAttribute("type","text");
        e4.setAttribute("size","28");
        e4.setAttribute("name",count+".4");
        e4.setAttribute("id",count+".4");
        //e4.setAttribute("value",count+".4");
        cell4.width="25%";
        cell4.appendChild(e4);


        var obj=document.getElementById(count+".1");
        if(rowcount > 6)
            obj.setAttribute("rows", count*2-3);
        else
            obj.setAttribute("rows", count*2);
    }


    function addRow3 (tableid){
        var table = document.getElementById (tableid);
        var rowcount = table.rows.length;
        var row = table.insertRow(rowcount);

        var cell1 = row.insertCell (0);
        var e1 = document.createElement ("INPUT");
        e1.setAttribute("Type", "text");
        e1.setAttribute("Size", "40");
        cell1.appendChild (e1);

        var cell2 = row.insertCell (1);
        var e2 = document.createElement ("INPUT");
        e2.setAttribute("Type", "text");
        e2.setAttribute("Size", "40");
        cell2.appendChild (e2);

        var cell3 = row.insertCell(2);
        var e3 = document.createElement("INPUT");
        e3.setAttribute("type","text");
        e3.setAttribute("size","40");
        cell3.appendChild(e3);

        var cell4 = row.insertCell(3);
        var e4 = document.createElement("INPUT");
        e4.setAttribute("type","text");
        e4.setAttribute("size","40");
        cell4.appendChild(e4);
    }
</script>


<s:form beanclass="etanah.view.stripes.pembangunan.RingkasanRayuanActionBean">
    <s:messages/>
    <s:errors/>
    <s:hidden name="kertasK.kertas.idKertas"/>
    <s:hidden name="count" id="count" value="${actionBean.count}"/>

    <div class="subtitle">
        <fieldset class="aras1">
            <div class="content" align="center">
                <table border="0" width="80%" cellspacing="5">
                    <tr><td align="center"><b> RINGKASAN RAYUAN PELAN KUOTA </b></td></tr>
                    <tr>
                        <c:if test="${edit}">
                            <td align="right" colspan="2"><b>KERTAS BIL: <s:text name="keritasBill" id="datepicker" class="datepicker" size="20" /></b></td>
                        </c:if>
                        <c:if test="${!edit}">
                            <td align="right" colspan="2"><b>KERTAS BIL: ${actionBean.keritasBill}</b></td>
                        </c:if>
                    </tr>

                    <tr><td><b><u> PERIHAL PERMOHONAN</u></b></td></tr>

                    <tr>
                        <c:if test="${edit}">
                            <td colspan="2"><b>NAMA: <s:text name="name" size="30" /></b></td>
                            <%--value="${actionBean.permohonan.kodUrusan.nama}"--%>
                        </c:if>
                        <c:if test="${!edit}">
                            <td colspan="2"><b>NAMA: ${actionBean.name} </b></td>
                        </c:if>
                    </tr>

                    <tr><td><b>JENIS RAYUAN:</b></td></tr>
                    <tr> <c:if test="${edit}">
                            <td colspan="2"><b> <s:textarea name="jenisRayuan" rows="5" cols="150"  /></b></td>
                        </c:if>
                        <c:if test="${!edit}">
                            <td colspan="2"><b>JENIS RAYUAN: ${actionBean.jenisRayuan}</b></td>
                        </c:if></tr>

                    <tr><td><b>ALAMAT HARTANAH DAN KELUASAN:</b></td></tr>
                    <tr>  <c:if test="${edit}">
                            <td colspan="2"><b><s:textarea name="alamat" rows="5" cols="150" /></b></td>
                        </c:if>
                        <c:if test="${!edit}">
                            <td colspan="2"><b>ALAMAT HARTANAH DAN KELUASAN: ${actionBean.alamat} </b></td>
                        </c:if>
                    </tr>

                    <tr><td><b> PELEPASAN KUOTA TERDAHULU:</b></td></tr>
                    <tr>
                        <c:if test="${edit}">
                            <td colspan="2"><s:textarea name="pelepasan" rows="5" cols="150" /></td>
                        </c:if>
                        <c:if test="${!edit}">
                            <td colspan="2"><b> PELEPASAN KUOTA TERDAHULU: ${actionBean.pelepasan}</b></td>
                        </c:if>
                    </tr>

                    <tr><td><b>ALASAN RAYUAN:</b></td></tr>
                    <tr>
                        <c:if test="${edit}">
                            <td colspan="2"><s:textarea name="alasan" rows="5" cols="150" /></td>
                        </c:if>
                        <c:if test="${!edit}">
                            <td colspan="2"><b> ALASAN RAYUAN: ${actionBean.alasan}</b></td>
                        </c:if>
                    </tr>

                    <tr><td><b>PERAKUAN PTG:</b></td></tr>
                    <tr>
                        <c:if test="${edit}">
                        <tr><td><s:textarea name="perakuanPtg" rows="5" cols="150"/></td></tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td><font style="text-transform:capitalize"> ${actionBean.perakuanPtg} &nbsp; </font></td></tr>
                    </c:if>
                    </tr><br>


                    <c:set var="tableCount" value="${actionBean.count}" />

                    <c:set var="a" value="1" />
                   <%-- Integer tableCount = (Integer)request.getAttribute("count");--%>

                    <%
                       Integer tableCount = (Integer)request.getAttribute("count");
                      // out.println("tableCount:"+tableCount);
                      for(int p=1;p<=tableCount;p++){ %>
                      
                      <tr><td>

                      <c:set var="recordCount" value="0"/>
                      <c:forEach items="${actionBean.senaraiTingkat[a]}" var="senarai">
                       <c:set var="recordCount" value="${recordCount+1}"/>

                        <c:if test="${recordCount eq 1}">
                          <s:text name="${senarai.subtajuk}" id="${senarai.subtajuk}" value="${senarai.kandungan}" size="80"/>
                      <br/><br/>
                        </c:if>
                      <c:if test="${recordCount eq 2}">
                          <s:text name="${senarai.subtajuk}" id="${senarai.subtajuk}" value="${senarai.kandungan}" size="63"/>
                          &nbsp; : &nbsp;
                      </c:if>
                      <c:if test="${recordCount eq 3}">
                          <s:text name="${senarai.subtajuk}" id="${senarai.subtajuk}" value="${senarai.kandungan}" size="10"/><br/>
                        </c:if>
                      <c:if test="${recordCount eq 4}">
                          <s:text name="${senarai.subtajuk}" id="${senarai.subtajuk}" value="${senarai.kandungan}" size="63"/>
                          &nbsp; : &nbsp;
                      </c:if>
                      <c:if test="${recordCount eq 5}">
                          <s:text name="${senarai.subtajuk}" id="${senarai.subtajuk}" value="${senarai.kandungan}" size="10"/><br/>
                      </c:if>
                      <c:if test="${recordCount eq 6}">
                          <s:text name="${senarai.subtajuk}" id="${senarai.subtajuk}" value="${senarai.kandungan}" size="63"/>
                          &nbsp; : &nbsp;
                      </c:if>
                      <c:if test="${recordCount eq 7}">
                          <s:text name="${senarai.subtajuk}" id="${senarai.subtajuk}" value="${senarai.kandungan}" size="10"/><br/>
                      </c:if>
                      <c:if test="${recordCount eq 8}">
                          <s:text name="${senarai.subtajuk}" id="${senarai.subtajuk}" value="${senarai.kandungan}" size="63"/>
                          &nbsp; : &nbsp;
                      </c:if>
                      <c:if test="${recordCount eq 9}">
                          <s:text name="${senarai.subtajuk}" id="${senarai.subtajuk}" value="${senarai.kandungan}" size="10"/><br/>
                      </c:if>
                      <c:if test="${recordCount eq 10}">
                          <s:text name="${senarai.subtajuk}" id="${senarai.subtajuk}" value="${senarai.kandungan}" size="63"/>
                          &nbsp; : &nbsp;
                      </c:if>
                      <c:if test="${recordCount eq 11}">
                          <s:text name="${senarai.subtajuk}" id="${senarai.subtajuk}" value="${senarai.kandungan}" size="10"/><br/>
                      </c:if>
                      <c:if test="${recordCount eq 12}">
                          <s:text name="${senarai.subtajuk}" id="${senarai.subtajuk}" value="${senarai.kandungan}" size="63"/>
                          &nbsp; : &nbsp;
                      </c:if>
                      <c:if test="${recordCount eq 13}">
                          <s:text name="${senarai.subtajuk}" id="${senarai.subtajuk}" value="${senarai.kandungan}" size="10"/>
                      </c:if>
                      </c:forEach>
                            </td></tr>

                <tr><td>                       
                    <table  width="90%" id="tbl" class="tablecloth">
                         <tr>
                             <th  width="10%" align="center"><b>JUMLAH</b></th>
                             <th  width="50%" align="center"><b>PELEPASAN</b></th>
                             <th  width="20%" align="center"><b>BUMI<br>(40%)</b></th>
                             <th  width="20%" align="center"><b>BUKAN BUMI<br>(60%)</b></th>
                         </tr>
                         <c:set var="recordCount" value="0"/>
                         <tr><c:forEach items="${actionBean.senaraiRingkas[a]}" var="senarai">
                               <c:set var="recordCount" value="${recordCount+1}"/>
                               <c:if test="${recordCount eq 1}">
                                   <td><s:textarea rows="5" cols="13" name="${senarai.subtajuk}" id="${senarai.subtajuk}" value="${senarai.kandungan}" />  </td>
                               </c:if>
                             </c:forEach>
                             <td colspan="3">
                                 <table id="tablecount${a}">
                                     
                                    <tr> <c:set var="recordCount" value="0"/>
                                         <c:set var="billNo" value="0"/>
                                  <c:forEach items="${actionBean.senaraiRingkas[a]}" var="senarai">
                                      
                                      <c:set var="recordCount" value="${recordCount+1}"/>

                                      <c:if test="${(recordCount-2)%3 eq 0}">
                                         <c:set var="billNo" value="${billNo+1}"/>
                                      </c:if>

                                      <c:if test="${recordCount ne 1}">
                                      <td style="display:none">${senarai.idKandungan}</td>
                                      <c:if test="${recordCount eq 2+(3*(billNo-1))}">
                                          <td width="40%"><s:text name="${senarai.subtajuk}" id="${senarai.subtajuk}" value="${senarai.kandungan}" size="74"/> </td>
                                      </c:if>
                                       <c:if test="${recordCount ne 2+(3*(billNo-1))}">
                                          <td width="25%"><s:text name="${senarai.subtajuk}" id="${senarai.subtajuk}" value="${senarai.kandungan}" size="28"/> </td>
                                      </c:if>
                                      <%--<td><s:text name="${senarai.subtajuk}" id="${senarai.subtajuk}" value="${senarai.kandungan}" /> </td>--%>
                                      </c:if>

                                      <c:if test="${(recordCount-1)%3 eq 0}">
                                           </tr>
                                      </c:if>
                                  </c:forEach>
                                      
                                   </table>                                  
                                 
                             </td>
                         </tr>
                         <tr>
                              <!-- hidden field -->
                              <td><s:hidden name="count${a}" id="count${a}" value="${(fn:length(actionBean.senaraiRingkas[a])-1)/3}" /></td>
                              <td><s:hidden name="testCount${a}" id="testCount${a}" value="${(fn:length(actionBean.senaraiRingkas[a])-1)/3}" /></td>
                          </tr>
                         
                         <tr>

                             <td><s:button class="btn" value="Tambah" name="add" id="tambah${a}" onclick="addRow('tablecount${a}','count${a}')"/></td>
                             <td><s:button class="btn" value="Hapus" name="delete" id="hapus${a}" onclick="deleteRow('tablecount${a}','count${a}')"/></td>
                         </tr>
                         <c:set var="a" value="${a+1}"/>
                    </table>
                   <% } %>
                    </td></tr>


                    <tr><td>

                    <div id="allTables">

                    </div>
                    <s:button class="btn" value="Add Table" name="add" onclick="addTable('allTables')"/>
                    <br><br>
                        </td></tr>

                    <tr><td><table border="1" width="100%" class="tablecloth">
                                <tr>
                                    <th width="20%" align="center"><b>% KESELURUHAN UNIT KEDIAMAN</b></th>
                                    <th width="60%" align="center"><b>Bumiputra - <s:text name="bumiputra" id="bumiputra" size="5" /></b></th>
                                    <th width="20%" align="center"><b>Bukan Bumiputra - <s:text name="bukanBumiputra" id="bukanBumiputra" size="5"/></b></th>
                                </tr>
                                <tr>
                                    <th width="20%" align="center"><b>JUMLAH PENGUNDI<s:textarea name="jumlahPengundi" rows="12" cols="23" id="jumlahPengundi" /></b></th>
                                    <td colspan="2">
                                        <table border="1" width="100%" id="tb3">
                                            <tr>
                                                <th colspan="3" width="60%" align="center"><b>SEBELUM PELEPASAN</b></th>
                                                <th width="20%" align="center"><b>SELEPAS PELEPASAN <s:textarea name="selepasPelepasan" id="selepasPelepasan" rows="2" cols="25"/></b></th>
                                            </tr>
                                            <tr>
                                                <th width="20%" align="center"><b>PENGUNDI</b> </h>
                                                <th align="center"><b>JUMLAH</b></th>
                                                <th width="20%" align="center"><b>%</b></th>
                                                <th align="center"><b>%</b>
                                            </tr>
                                            <tr>
                                                <td><b>Melayu</b></td>
                                                <td><s:text name="melayu1" id="melayu1" /></td>
                                                <td><s:text name="melayu2" id="melayu2" /></td>
                                                <td><s:text name="melayu3" id="melayu3" /></td>
                                            </tr>
                                            <tr>
                                                <td><b>Cina</b></td>
                                                <td><s:text name="cina1" id="cina1" /></td>
                                                <td><s:text name="cina2" id="cina2" /></td>
                                                <td><s:text name="cina3" id="cina3" /></td>
                                            </tr>
                                            <tr>
                                                <td><b>India</b></td>
                                                <td><s:text name="india1" id="india1" /></td>
                                                <td><s:text name="india2" id="india2" /></td>
                                                <td><s:text name="india3" id="india3" /></td>
                                            </tr>
                                            <tr>
                                                <td><b>Lain-lain</b></td>
                                                <td><s:text name="lainLain1" id="lainLain1" /></td>
                                                <td><s:text name="lainLain2" id="lainLain2" /></td>
                                                <td><s:text name="lainLain3" id="lainLain3" /></td>
                                            </tr>
                                            <tr>
                                                <td><b>Jumlah</b></td>
                                                <td><s:text name="jumlahTotal1" id="jumlahTotal1" /></td>
                                                <td><s:text name="jumlahTotal2" id="jumlahTotal2" /></td>
                                                <td><s:text name="jumlahTotal3" id="jumlahTotal3" /></td>
                                            </tr>

                                        </table>
                                    </td>
                                </tr>

                            </table>
                        </td>
                    </tr>
                    <tr><td>
                            <s:button class="btn" value="Tambah" name="add" onclick="addRow3('tb3')"/>&nbsp;</td></tr><br>
                    <tr><td> <b>KEPUTUSAN JKBB :</b></td></tr>
                    <tr><td><s:radio name="keputusan" value="L"/>LULUS  <s:radio name="keputusan" value="T"/>TOLAK <s:radio name="keputusan" value="S"/>TANGGUH <s:radio name="keputusan" value="SI"/>SIASAT </td></tr>
                    <c:if test="${edit}">
                        <br>
                    </table>
                    <tr align="center">
                        <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </tr>
                </c:if>
            </div>
        </fieldset>
    </div>
</s:form>