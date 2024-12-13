<%--
    Document   : dev_Rayuan_Pelepasan_Quota
    Created on : Oct 11, 2010, 4:27:21 PM
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
<script type="text/javascript">


        function deleteRow(tableid,recordCount){

        var table = document.getElementById(tableid);
        var rowcount = table.rows.length;
        var rowIndex = document.getElementById(recordCount).value;
        var tableIndex = parseInt(recordCount.substring(5));
 
         if(rowIndex > 1){
         if(parseInt(tableIndex) == 3){             
             document.getElementById(tableid).deleteRow((parseInt(rowIndex)+1));
         }else{
         document.getElementById(tableid).deleteRow(rowIndex);    
         }
           
        // alert("rowIndex:"+rowIndex);
        var testCount = document.getElementById("testCount"+tableIndex).value;
        // alert("testCount:"+testCount);
        if(parseInt(rowIndex) <= parseInt(testCount)){

           try{
//            alert("javacall");
           var url = '${pageContext.request.contextPath}/pembangunan/pecahSempadan/rayuanPelepasanKuota?delete&tableIndex='+tableIndex;
          //   var url = '${pageContext.request.contextPath}/pembangunan/pecahSempadan/rayuanPelepasanKuota?delete&tableIndex='+3;
       
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');

            }catch(e){
                alert(e);
            }
        }
      }else{
         alert("Row can not be deleted");
      }
    }


    function addRow1(tableId,countId){
  //      alert("tableid:"+tableId);
  //      alert("tableCountId:"+countId);

        var table = document.getElementById(tableId);
        var rowcount = table.rows.length;
        var row = table.insertRow(rowcount);

         var tableCount = document.getElementById(tableId).value;
         var tableIndex = parseInt(countId.substring(5));

        //alert("tableIndex:"+tableIndex);
        //alert("val:"+(document.getElementById(countId).value));

        // Increment record count value by 1
        document.getElementById(countId).value = parseInt(document.getElementById(countId).value)+1;

        //alert("val:"+(document.getElementById(countId).value));

        var recordIndex = document.getElementById(countId).value;
            recordIndex = 1+(4*(parseInt(recordIndex)-1));

          //  alert("recordIndex:"+recordIndex);

        var cell1 = row.insertCell(0);
        var bil = document.createTextNode(rowcount);
        cell1.appendChild(bil);

        var cell2 = row.insertCell (1);
        var e2 = document.createElement ("INPUT");
        e2.setAttribute("Type", "text");
        e2.setAttribute("size","70");
        e2.setAttribute("name",(tableIndex)+"."+(recordIndex));
        e2.setAttribute("id",(tableIndex)+"."+(recordIndex));
    //    e2.setAttribute("value",(tableIndex)+"."+(recordIndex));
        cell2.appendChild (e2);


        var cell3 = row.insertCell(2);
        var e3 = document.createElement("INPUT");
        e3.setAttribute("type","text");
        e3.setAttribute("size","25");
        e3.setAttribute("name",(tableIndex)+"."+(parseInt(recordIndex)+1));
        e3.setAttribute("id",(tableIndex)+"."+(parseInt(recordIndex)+1));
     //   e3.setAttribute("value",(tableIndex)+"."+(parseInt(recordIndex)+1));
        cell3.appendChild(e3);

        var cell4 = row.insertCell(3);
        var e4 = document.createElement("INPUT");
        e4.setAttribute("type","text");
        e4.setAttribute("size","25");
        e4.setAttribute("name",(tableIndex)+"."+(parseInt(recordIndex)+2));
        e4.setAttribute("id",(tableIndex)+"."+(parseInt(recordIndex)+2));
     //   e4.setAttribute("value",(tableIndex)+"."+(parseInt(recordIndex)+2));
        cell4.appendChild(e4);

        var cell5 = row.insertCell(4);
        var e5 = document.createElement("INPUT");
        e5.setAttribute("type","text");
        e5.setAttribute("size","25");
        e5.setAttribute("name",(tableIndex)+"."+(parseInt(recordIndex)+3));
        e5.setAttribute("id",(tableIndex)+"."+(parseInt(recordIndex)+3));
     //   e5.setAttribute("value",(tableIndex)+"."+(parseInt(recordIndex)+3));
        cell5.appendChild(e5);


    }


    function addRow2(tableId,countId){
//        alert("tableid:"+tableId);
//        alert("tableCountId:"+countId);

        var table = document.getElementById(tableId);
        var rowcount = table.rows.length;
        var row = table.insertRow(rowcount);

       // var tableCount = document.getElementById(tableId).value;
  //      alert("countId:"+countId);
        var tableIndex = parseInt(countId.substring(5));
     // Increment record count value by 1
        document.getElementById(countId).value = parseInt(document.getElementById(countId).value)+1;
        var recordIndex = document.getElementById(countId).value;
        recordIndex = 1+(3*(parseInt(recordIndex)-1));

        var cell1 = row.insertCell(0);
        var bil = document.createTextNode(rowcount);
        cell1.appendChild(bil);

        var cell2 = row.insertCell (1);
        var e2 = document.createElement ("INPUT");
        e2.setAttribute("Type", "text");
        e2.setAttribute("size","50");
        e2.setAttribute("name",(tableIndex)+"."+(recordIndex));
        e2.setAttribute("id",(tableIndex)+"."+(recordIndex));
   //     e2.setAttribute("value",(tableIndex)+"."+(recordIndex));
        cell2.appendChild (e2);


        var cell3 = row.insertCell(2);
        var e3 = document.createElement("INPUT");
        e3.setAttribute("type","text");
        e3.setAttribute("size","50");
        e3.setAttribute("name",(tableIndex)+"."+(parseInt(recordIndex)+1));
        e3.setAttribute("id",(tableIndex)+"."+(parseInt(recordIndex)+1));
    //    e3.setAttribute("value",(tableIndex)+"."+(parseInt(recordIndex)+1));
        cell3.appendChild(e3);

        var cell4 = row.insertCell(3);
        var e4 = document.createElement("INPUT");
        e4.setAttribute("type","text");
        e4.setAttribute("size","50");
        e4.setAttribute("name",(tableIndex)+"."+(parseInt(recordIndex)+2));
        e4.setAttribute("id",(tableIndex)+"."+(parseInt(recordIndex)+2));
    //    e4.setAttribute("value",(tableIndex)+"."+(parseInt(recordIndex)+2));
        cell4.appendChild(e4);

    }


    function addRow3(tableId,countId){
//        alert("tableid:"+tableId);
//        alert("tableCountId:"+countId);

        var table = document.getElementById(tableId);
        var rowcount = table.rows.length;
        var row = table.insertRow(rowcount);
        var tableIndex = parseInt(countId.substring(5));
      // Increment record count value by 1
        document.getElementById(countId).value = parseInt(document.getElementById(countId).value)+1;
        var recordIndex = document.getElementById(countId).value;
            recordIndex = 1+(8*(parseInt(recordIndex)-1));

        var cell1 = row.insertCell(0);
        var bil = document.createTextNode(rowcount-1);
        cell1.appendChild(bil);

        var cell2 = row.insertCell (1);
        var e2 = document.createElement ("INPUT");
        e2.setAttribute("Type", "text");
        e2.setAttribute("size","35");
        e2.setAttribute("name",(tableIndex)+"."+(recordIndex));
        e2.setAttribute("id",(tableIndex)+"."+(recordIndex));
        <%--e2.setAttribute("value",(tableIndex)+"."+(recordIndex));--%>
        cell2.appendChild (e2);


        var cell3 = row.insertCell(2);
        var e3 = document.createElement("INPUT");
        e3.setAttribute("type","text");
        e3.setAttribute("size","14");
        e3.setAttribute("name",(tableIndex)+"."+(parseInt(recordIndex)+1));
        e3.setAttribute("id",(tableIndex)+"."+(parseInt(recordIndex)+1));
        <%--e3.setAttribute("value",(tableIndex)+"."+(parseInt(recordIndex)+1));--%>
        cell3.appendChild(e3);

        var cell4 = row.insertCell(3);
        var e4 = document.createElement("INPUT");
        e4.setAttribute("type","text");
        e4.setAttribute("size","14");
        e4.setAttribute("name",(tableIndex)+"."+(parseInt(recordIndex)+2));
        e4.setAttribute("id",(tableIndex)+"."+(parseInt(recordIndex)+2));
        <%--e4.setAttribute("value",(tableIndex)+"."+(parseInt(recordIndex)+2));--%>
        cell4.appendChild(e4);

        var cell5 = row.insertCell(4);
        var e5 = document.createElement("INPUT");
        e5.setAttribute("type","text");
        e5.setAttribute("size","14");
        e5.setAttribute("name",(tableIndex)+"."+(parseInt(recordIndex)+3));
        e5.setAttribute("id",(tableIndex)+"."+(parseInt(recordIndex)+3));
        <%--e5.setAttribute("value",(tableIndex)+"."+(parseInt(recordIndex)+3));--%>
        cell5.appendChild(e5);

        var cell6 = row.insertCell(5);
        var e6 = document.createElement("INPUT");
        e6.setAttribute("type","text");
        e6.setAttribute("size","14");
        e6.setAttribute("name",(tableIndex)+"."+(parseInt(recordIndex)+4));
        e6.setAttribute("id",(tableIndex)+"."+(parseInt(recordIndex)+4));
        <%--e6.setAttribute("value",(tableIndex)+"."+(parseInt(recordIndex)+4));--%>
        cell6.appendChild(e6);

        var cell7 = row.insertCell(6);
        var e7 = document.createElement("INPUT");
        e7.setAttribute("type","text");
        e7.setAttribute("size","14");
        e7.setAttribute("name",(tableIndex)+"."+(parseInt(recordIndex)+5));
        e7.setAttribute("id",(tableIndex)+"."+(parseInt(recordIndex)+5));
        <%--e7.setAttribute("value",(tableIndex)+"."+(parseInt(recordIndex)+5));--%>
        cell7.appendChild(e7);

        var cell8 = row.insertCell(7);
        var e8 = document.createElement("INPUT");
        e8.setAttribute("type","text");
        e8.setAttribute("size","14");
        e8.setAttribute("name",(tableIndex)+"."+(parseInt(recordIndex)+6));
        e8.setAttribute("id",(tableIndex)+"."+(parseInt(recordIndex)+6));
        <%--e8.setAttribute("value",(tableIndex)+"."+(parseInt(recordIndex)+6));--%>
        cell8.appendChild(e8);

        var cell9 = row.insertCell(8);
        var e9 = document.createElement("INPUT");
        e9.setAttribute("type","text");
        e9.setAttribute("size","14");
        e9.setAttribute("name",(tableIndex)+"."+(parseInt(recordIndex)+7));
        e9.setAttribute("id",(tableIndex)+"."+(parseInt(recordIndex)+7));
        <%--e9.setAttribute("value",(tableIndex)+"."+(parseInt(recordIndex)+7));--%>
        cell9.appendChild(e9);

    }

    function addRow6(tableId,countId){
        //alert("tableid:"+tableId);
        //alert("tableCountId:"+countId);

        var table = document.getElementById(tableId);
        var rowcount = table.rows.length;
        var row = table.insertRow(rowcount);
        var tableIndex = parseInt(countId.substring(5));
        // Increment record count value by 1
        document.getElementById(countId).value = parseInt(document.getElementById(countId).value)+1;
        var recordIndex = document.getElementById(countId).value;
            recordIndex = 1+(2*(parseInt(recordIndex)-1));

        var cell1 = row.insertCell(0);
        var bil = document.createTextNode(rowcount);
        cell1.appendChild(bil);

        var cell2 = row.insertCell (1);
        var e2 = document.createElement ("INPUT");
        e2.setAttribute("Type", "text");
        e2.setAttribute("size","75");
        e2.setAttribute("name",(tableIndex)+"."+(recordIndex));
        e2.setAttribute("id",(tableIndex)+"."+(recordIndex));
        <%--e2.setAttribute("value",(tableIndex)+"."+(recordIndex));--%>
        cell2.appendChild (e2);


        var cell3 = row.insertCell(2);
        var e3 = document.createElement("INPUT");
        e3.setAttribute("type","text");
        e3.setAttribute("size","75");
        e3.setAttribute("name",(tableIndex)+"."+(parseInt(recordIndex)+1));
        e3.setAttribute("id",(tableIndex)+"."+(parseInt(recordIndex)+1));
        <%--e3.setAttribute("value",(tableIndex)+"."+(parseInt(recordIndex)+1));--%>
        cell3.appendChild(e3);

    }


      function addRow4(tableId,countId){

       // alert("tableid:"+tableId);
       // alert("tableCountId:"+countId);

        var table = document.getElementById(tableId);
        var rowcount = table.rows.length;
        var row = table.insertRow(rowcount);

        var tableIndex = parseInt(countId.substring(5));

        // Increment record count value by 1
        document.getElementById(countId).value = parseInt(document.getElementById(countId).value)+1;
        var recordIndex = document.getElementById(countId).value;
            recordIndex = 1+(4*(parseInt(recordIndex)-1));


        var cell1 = row.insertCell(0);
        var bil = document.createTextNode(rowcount);
        cell1.appendChild(bil);

        var cell2 = row.insertCell (1);
        var e2 = document.createElement ("INPUT");
        e2.setAttribute("Type", "text");
        e2.setAttribute("size","35");
        e2.setAttribute("name",(tableIndex)+"."+(recordIndex));
        e2.setAttribute("id",(tableIndex)+"."+(recordIndex));
        <%--e2.setAttribute("value",(tableIndex)+"."+(recordIndex));--%>
        cell2.appendChild (e2);


        var cell3 = row.insertCell(2);
        var e3 = document.createElement("INPUT");
        e3.setAttribute("type","text");
        e3.setAttribute("size","35");
        e3.setAttribute("name",(tableIndex)+"."+(parseInt(recordIndex)+1));
        e3.setAttribute("id",(tableIndex)+"."+(parseInt(recordIndex)+1));
        <%--e3.setAttribute("value",(tableIndex)+"."+(parseInt(recordIndex)+1));--%>
        cell3.appendChild(e3);

        var cell4 = row.insertCell(3);
        var e4 = document.createElement("INPUT");
        e4.setAttribute("type","text");
        e4.setAttribute("size","35");
        e4.setAttribute("name",(tableIndex)+"."+(parseInt(recordIndex)+2));
        e4.setAttribute("id",(tableIndex)+"."+(parseInt(recordIndex)+2));
        <%--e4.setAttribute("value",(tableIndex)+"."+(parseInt(recordIndex)+2));--%>
        cell4.appendChild(e4);

        var cell5 = row.insertCell(4);
        var e5 = document.createElement("INPUT");
        e5.setAttribute("type","text");
        e5.setAttribute("size","35");
        e5.setAttribute("name",(tableIndex)+"."+(parseInt(recordIndex)+3));
        e5.setAttribute("id",(tableIndex)+"."+(parseInt(recordIndex)+3));
        <%--e5.setAttribute("value",(tableIndex)+"."+(parseInt(recordIndex)+3));--%>
        cell5.appendChild(e5);

    }

<%--    function showReport(){
        window.open("${pageContext.request.contextPath}/pembangunan/melaka/kertasPertimbanganPTG?genReport", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=700");
         $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
                    width: '50px'
                }
            });
            var url = '${pageContext.request.contextPath}/pembangunan/melaka/kertasPertimbanganPTG?genReport';
            $.ajax({
                   type:"GET",
                   url : url,
                   dataType : 'html',
                   error : function(xhr, ajaxOptions, thrownError) {
                        alert('Terdapat masalah teknikal. Sila hubungi Pentadbir Sistem.');
                        $("#folder_div").html('<div class=errors>Terdapat Masalah Teknikal. Sila Hubungi Pentadbir Sistem.</div><div id=t><a href="#">[ Lihat Terperinci ]</a></div><br/><div id=error class=b>' + xhr.responseText + '</div>');
                        $.unblockUI();
                    },
                   success : function(data) {
                            if(data.indexOf('Laporan telah dijana')==0){
                            alert(data);
                            $('#folder').click();
                            }else {
                                alert(data);
                                $('#urusan').click();
                            }
                        $.unblockUI();
                   }
            });
    }--%>
</script>

<s:form beanclass="etanah.view.stripes.pembangunan.RayuanPelepasanKuotaActionBean">
    <s:messages/>
    <s:errors/>
    <s:hidden name="kertasK.kertas.idKertas"/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend> </legend>
               <div class="content" align="center">
                    <table border="0" width="80%">
                        <tr><td align="center"><b>MESYUARAT JAWATANKUASA PECAH SEMPADAN DAN BELAH BAHAGI NEGERI MELAKA</b></td></tr>
                        <tr><td>
                                <table border="0" width="50%">
                                    <c:if test="${edit}">
                                        <tr><td><b id="idLabel">PERSIDANGAN : </b></td>
                                            <td><s:text name="persidangan" size="20"/></td>
                                        </tr>
                                    </c:if>
                                    <c:if test="${!edit}">
                                        <tr><td id="idLabel"><b>PERSIDANGAN : </b></td>
                                            <td id="idDisplay">${actionBean.persidangan} &nbsp;</td>
                                        </tr>
                                    </c:if>
                                    <c:if test="${edit}">
                                        <tr><td id="idLabel"><b>MASA : </b></td>
                                            <td><s:text name="masasidang" size="20" /></td>
                                        </tr>
                                    </c:if>
                                    <c:if test="${!edit}">
                                        <tr><td id="idLabel"><b>MASA : </b></td>
                                            <td id="idDisplay">${actionBean.masasidang} &nbsp;</td>
                                        </tr>
                                    </c:if>
                                    <c:if test="${edit}">
                                        <tr><td id="idLabel"><b>TARIKH : </b></td>
                                             <td><s:text name="tarikhMesyuarat" id="datepicker" class="datepicker" size="12" /></td>
                                        </tr>
                                    </c:if>
                                    <c:if test="${!edit}">
                                        <tr><td id="idLabel"><b>TARIKH : </b></td>
                                            <td id="idDisplay">${actionBean.tarikhMesyuarat} &nbsp;</td>
                                        </tr>
                                    </c:if>
                                    <c:if test="${edit}">
                                        <tr><td id="idLabel"><b>TEMPAT : </b></td>
                                            <td><s:textarea name="tempatsidang" cols="50"/></td>
                                        </tr>
                                    </c:if>
                                    <c:if test="${!edit}">
                                       <tr><td id="idLabel"><b>TEMPAT : </b></td>
                                           <td id="idDisplay">${actionBean.tempatsidang} &nbsp;</td>
                                       </tr>
                                    </c:if>
                                </table>
                            </td></tr>
                        <tr><td><b>1. JENIS RAYUAN</b></td></tr>
                        <c:if test="${edit}">
                            <tr><td><s:textarea name="jenisRayuan" rows="5" cols="165"/></td></tr>
                        </c:if>
                        <c:if test="${!edit}">
                            <tr><td><font style="text-transform:capitalize">${actionBean.jenisRayuan} &nbsp;</font></td></tr>
                        </c:if>
                        <tr><td><b>2. JENIS PERMOHONAN</b></td></tr>
                        <c:if test="${edit}">
                            <tr><td><s:textarea name="jenisPermohonan" rows="5" cols="165"/></td></tr>
                        </c:if>
                        <c:if test="${!edit}">
                            <tr><td><font style="text-transform:capitalize">${actionBean.jenisPermohonan} &nbsp;</font></td></tr>
                        </c:if>
                        <tr><td><b>3. BUTIR-BUTIR HAKMILIK </b></td></tr>
                        <tr><td>
                        <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0" id="tbl">
                            <display:column title="Bil" sortable="true" style="vertical-align:baseline">${tbl1_rowNum}</display:column>
                            <display:column title="No.Lot/PT" style="vertical-align:baseline">
                                ${tbl1.hakmilik.lot.nama} ${tbl1.hakmilik.noLot}
                            </display:column>
                            <display:column title="Jenis & No Hakmilik" style="vertical-align:baseline">
                                ${tbl1.hakmilik.lot.nama} ${tbl1.hakmilik.kodHakmilik.kod} ${tbl1.hakmilik.noHakmilik}
                            </display:column>
                            <display:column title="Bandar/Pekan/Mukim" property="hakmilik.bandarPekanMukim.nama"  style="vertical-align:baseline"/>
                            <display:column title="Tempoh" property="hakmilik.tempohPegangan" style="vertical-align:baseline" />
                            <display:column title="Luas" style="vertical-align:baseline"><fmt:formatNumber pattern="#,##0.0000" value="${tbl1.hakmilik.luas}"/>&nbsp;${tbl1.hakmilik.kodUnitLuas.nama}</display:column>
                            <display:column title="Tuan Tanah" style="vertical-align:baseline">
                                <c:forEach items="${tbl1.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                                    <c:if test="${senarai.jenis.kod eq 'PM'}">
                                        <c:out value="${senarai.pihak.nama}"/>
                                    </c:if>
                                </c:forEach>
                            </display:column>
                            <display:column title="Jenis Kegunaan" property="hakmilik.kegunaanTanah.nama" style="vertical-align:baseline"/>
                            <display:column title="Syarat Nyata Tanah" property="hakmilik.syaratNyata.syarat" style="vertical-align:baseline"/>
                            <display:column title="Bebanan Berdaftar" style="vertical-align:baseline"></display:column>
                            <display:column title="Sekatan Kepentingan" property="hakmilik.sekatanKepentingan.sekatan" style="vertical-align:baseline"/>
                            <display:column title="Surat Persetujuan Daripada" style="vertical-align:baseline"></display:column>
                        </display:table>
                    </td></tr>

                    <tr><td><b>4. LOKASI</b></td></tr>
                <c:if test="${edit}">
                    <tr><td><s:textarea rows="5" cols="165" name="lokasi"/></td></tr>
                </c:if>
                <c:if test="${!edit}">
                    <tr><td><font style="text-transform:capitalize">${actionBean.lokasi} &nbsp;</font></td></tr>
                </c:if>
                    <tr><td><b>5. TARIKH KELULUSAN JKBB TERMIWAL</b></td></tr>
                <c:if test="${edit}">
                    <tr><td><s:textarea rows="5" cols="165" name="tarikhKelulusan"/></td></tr>
                </c:if>
                <c:if test="${!edit}">
                    <tr><td><font style="text-transform:capitalize">${actionBean.tarikhKelulusan} &nbsp;</font></td></tr>
                </c:if>
                <tr><td><b>6. PERIHAL PEMOHON</b></td></tr>
                    <c:if test="${edit}">
                        <tr><td><s:textarea name="perihalPemohon" rows="5" cols="165"/></td></tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td><font style="text-transform:capitalize">${actionBean.perihalPemohon} &nbsp;</font></td></tr>
                    </c:if>
                    <tr><td><b>7. TARIKH TERIMA PERMOHONAN </b></td></tr>
                    <c:if test="${edit}">
                        <tr><td><s:textarea name="tarikhTerima" rows="5" cols="165"/></td></tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td><font style="text-transform:capitalize">${actionBean.tarikhTerima} &nbsp;</font></td></tr>
                    </c:if>
                        <tr><td><b>8. BAYARAN/NO. RESIT </b></td></tr>
                   <c:if test="${edit}">
                        <tr><td><s:textarea name="bayaran" rows="5" cols="165"/></td></tr>
                   </c:if>
                   <c:if test="${!edit}">
                       <tr><td><font style="text-transform:capitalize">${actionBean.bayaran}&nbsp;</font></td></tr>
                   </c:if>
                       <tr><td><b>9. LATAR BELAKANG </b></td></tr>
                   <c:if test="${edit}">
                        <tr><td><s:textarea name="latarBelakang" rows="5" cols="165"/></td></tr>
                   </c:if>
                   <c:if test="${!edit}">
                       <tr><td><font style="text-transform:capitalize">${actionBean.latarBelakang}&nbsp;</font></td></tr>
                   </c:if>
                       <tr><td><b>9.1 Butir-butir Pembangunan Yang Diluluskan dan Harga Jualan </b></td></tr>
                   <c:if test="${edit}">
                     <tr><td>
                             <table  width="100%" id="tbl1" class="tablecloth" border="1">
                         <tr>                             
                             <th  width="10%" align="center"><b>Bill</b></th>
                             <th  width="40%" align="center"><b>Pembangunan</b></th>
                             <th  width="16%" align="center"><b>Unit</b></th>
                             <th  width="17%" align="center"><b>Keluasan</b></th>
                             <th  width="17%" align="center"><b>Harga Seunit</b></th>
                         </tr>
                         <c:set var="recordCount" value="0"/>
                         <c:set var="billNo" value="0"/>
                          <c:forEach items="${actionBean.senaraiButirButir}" var="senarai">
                               <c:set var="recordCount" value="${recordCount+1}"/>
                                     <c:if test="${(recordCount-1)%4 eq 0}">
                                         <c:set var="billNo" value="${billNo+1}"/>
                                       <tr>
                                          <td style="display:none">${senarai.idKandungan}</td>
                                        <td>${billNo} </td>
                                      </c:if>
                                      <c:if test="${recordCount eq 1+(4*(billNo-1))}">
                                          <td width="40%"><s:text name="${senarai.subtajuk}" id="${senarai.subtajuk}" value="${senarai.kandungan}" size="70"/> </td>
                                      </c:if>
                                      <c:if test="${recordCount ne 1+(4*(billNo-1))}">
                                          <td width="20%" align="center"><s:text name="${senarai.subtajuk}" id="${senarai.subtajuk}" value="${senarai.kandungan}" size="25" style="text-align:center"/> </td>
                                      </c:if>

                          </c:forEach>
                         </table>                               
                            </td></tr>
                        <tr><!-- Hidden field for recordCount -->
                            <td><s:hidden name="count1" id="count1" value="${(fn:length(actionBean.senaraiButirButir))/4}" />
                                <s:hidden name="testCount1" id="testCount1" value="${(fn:length(actionBean.senaraiButirButir))/4}" /></td>
                        </tr>
                         <tr>
                             <td><s:button class="btn" value="Tambah" name="add" id="tambah" onclick="addRow1('tbl1','count1')"/>
                                 <s:button class="btn" value="Hapus" name="Hapus" id="Hapus" onclick="deleteRow('tbl1','count1')"/></td>
                         </tr>
                   </c:if>
                         <tr><td><b>9.2 Permohonan Rayuan </b></td></tr>
                   <c:if test="${edit}">
                     <tr><td>
                             <table  width="100%" id="tbl2" class="tablecloth" border="1">
                         <tr>
                             <th  width="10%" align="center"><b>Bill</b></th>
                             <th  width="30%" align="center"><b>Tarikh</b></th>
                             <th  width="30%" align="center"><b>Jenis Rayuan</b></th>
                             <th  width="30%" align="center"><b>Keputusan</b></th>
                         </tr>
                         <c:set var="recordCount" value="0"/>
                         <c:set var="billNo" value="0"/>
                          <c:forEach items="${actionBean.senaraiPermohonanRayuan}" var="senarai">
                               <c:set var="recordCount" value="${recordCount+1}"/>
                                     <c:if test="${(recordCount-1)%3 eq 0}">
                                         <c:set var="billNo" value="${billNo+1}"/>
                                       <tr>
                                        <td style="display:none">${senarai.idKandungan}</td>
                                        <td>${billNo} </td>
                                      </c:if>
                                          <td width="30%"><s:text name="${senarai.subtajuk}" id="${senarai.subtajuk}" value="${senarai.kandungan}" size="50"/> </td>
                          </c:forEach>
                         </table>                             
                            </td></tr>
                         <tr><!-- Hidden field for recordCount -->
                            <td><s:hidden name="count2" id="count2" value="${(fn:length(actionBean.senaraiPermohonanRayuan))/3}" />
                                <s:hidden name="testCount2" id="testCount2" value="${(fn:length(actionBean.senaraiPermohonanRayuan))/3}" /></td>
                        </tr>
                         <tr>
                             <td><s:button class="btn" value="Tambah" name="add" id="tambah" onclick="addRow2('tbl2','count2')"/>
                                 <s:button class="btn" value="Hapus" name="Hapus" id="Hapus" onclick="deleteRow('tbl2','count2')"/></td>
                         </tr>
                   </c:if>


                   <tr><td><b>10. CARTA JUALAN </b></td></tr>
                   <c:if test="${edit}">
                     <tr><td>
                             <table  width="100%" id="tbl3" class="tablecloth" border="1">
                         <tr>
                             <th  width="5%" align="center" rowspan="2"><b>BIL</b></th>
                             <th  width="25%" align="center" rowspan="2"><b>JENIS BANGUNAN</b></th>
                             <th  width="10%" align="center" rowspan="2"><b>JUMLAH</b></th>
                             <th  width="30%" align="center" colspan="3"><b>KUOTA ORANG MELAYU</b></th>
                             <th  width="30%" align="center" colspan="3"><b>KUOTA ORANG MELAYU</b></th>
                         </tr>
                         <tr>
                             <th  width="10%" align="center" ><b>UNIT</b></th>
                             <th  width="10%" align="center"><b>JUAL</b></th>
                             <th  width="10%" align="center"><b>BAKI</b></th>
                             <th  width="10%" align="center"><b>UNIT</b></th>
                             <th  width="10%" align="center"><b>JUAL</b></th>
                             <th  width="10%" align="center"><b>BAKI</b></th>
                         </tr>

                         <c:set var="recordCount" value="0"/>
                         <c:set var="billNo" value="0"/>
                          <c:forEach items="${actionBean.senaraiCartaJualan}" var="senarai">
                               <c:set var="recordCount" value="${recordCount+1}"/>
                                     <c:if test="${(recordCount-1)%8 eq 0}">
                                         <c:set var="billNo" value="${billNo+1}"/>
                                       <tr>
                                        <td style="display:none">${senarai.idKandungan}</td>
                                        <td>${billNo} </td>
                                      </c:if>
                                      <c:if test="${recordCount eq 1+(8*(billNo-1))}">
                                          <td width="25%"><s:text name="${senarai.subtajuk}" id="${senarai.subtajuk}" value="${senarai.kandungan}" size="35"/> </td>
                                      </c:if>
                                      <c:if test="${recordCount ne 1+(8*(billNo-1))}">
                                          <td width="10%" align="center"><s:text name="${senarai.subtajuk}" id="${senarai.subtajuk}" value="${senarai.kandungan}" size="14" style="text-align:center"/> </td>
                                      </c:if>

                          </c:forEach>
                           </table>                          
                         </td></tr>
                         <tr><!-- Hidden field for recordCount -->
                            <td><s:hidden name="count3" id="count3" value="${(fn:length(actionBean.senaraiCartaJualan))/8}" />
                                <s:hidden name="testCount3" id="testCount3" value="${(fn:length(actionBean.senaraiCartaJualan))/8}" /></td>
                        </tr>                         
                         <tr>
                             <td><s:button class="btn" value="Tambah" name="add" id="tambah" onclick="addRow3('tbl3','count3')"/>
                                <s:button class="btn" value="Hapus" name="Hapus" id="Hapus" onclick="deleteRow('tbl3','count3')"/></td>
                         </tr>
                   </c:if>


                   <tr><td><b>11. IKLAN DI AKHBAR TEMPATAN </b></td></tr>
                   <c:if test="${edit}">
                     <tr><td>
                             <table  width="100%" id="tbl4" class="tablecloth" border="1">
                         <tr>
                             <th  width="10%" align="center"><b>Bill</b></th>
                             <th  width="30%" align="center"><b>Tarikh Iklan</b></th>
                             <th  width="30%" align="center"><b>Nama Akhbar</b></th>
                             <th  width="30%" align="center"><b>Catitan</b></th>
                         </tr>
                         <%--<c:if test="${(fn:length(actionBean.senaraiTempatan)) eq 0}">
                         <tr>
                            <td>1 </td>
                            <td width="30%"><s:text name="4.1" id="4.1" value="4.1" size="50"/> </td>
                            <td width="30%"><s:text name="4.2" id="4.2" value="4.2" size="50"/> </td>
                            <td width="30%"><s:text name="4.3" id="4.3" value="4.3" size="50"/> </td>
                         </tr>
                         </c:if>--%>
                         <c:set var="recordCount" value="0"/>
                         <c:set var="billNo" value="0"/>
                          <c:forEach items="${actionBean.senaraiTempatan}" var="senarai">
                               <c:set var="recordCount" value="${recordCount+1}"/>
                                     <c:if test="${(recordCount-1)%3 eq 0}">
                                         <c:set var="billNo" value="${billNo+1}"/>
                                       <tr>
                                         <td style="display:none">${senarai.idKandungan}</td>
                                        <td>${billNo} </td>
                                      </c:if>
                                          <td width="30%"><s:text name="${senarai.subtajuk}" id="${senarai.subtajuk}" value="${senarai.kandungan}" size="50"/> </td>
                          </c:forEach>
                         </table> 
                             </td></tr>
                         <tr><!-- Hidden field for recordCount -->
                            <td><s:hidden name="count4" id="count4" value="${(fn:length(actionBean.senaraiTempatan))/3}" />
                                <s:hidden name="testCount4" id="testCount4" value="${(fn:length(actionBean.senaraiTempatan))/3}" /></td>
                        </tr>
                         <tr>
                             <td><s:button class="btn" value="Tambah" name="add" id="tambah" onclick="addRow2('tbl4','count4')"/>
                                <s:button class="btn" value="Hapus" name="Hapus" id="Hapus" onclick="deleteRow('tbl4','count4')"/></td>
                         </tr>
                   </c:if>

                   <tr><td><b>12. IKLAN DI AKHBAR MINGGUAN MELAKA </b></td></tr>
                   <c:if test="${edit}">
                     <tr><td>
                             <table  width="100%" id="tbl5" class="tablecloth" border="1">
                         <tr>
                             <th  width="10%" align="center"><b>Bill</b></th>
                             <th  width="30%" align="center"><b>Tarikh Iklan</b></th>
                             <th  width="30%" align="center"><b>Nama Akhbar</b></th>
                             <th  width="30%" align="center"><b>Catitan</b></th>
                         </tr>
                         <c:set var="recordCount" value="0"/>
                         <c:set var="billNo" value="0"/>
                          <c:forEach items="${actionBean.senaraiMingguan}" var="senarai">
                               <c:set var="recordCount" value="${recordCount+1}"/>
                                     <c:if test="${(recordCount-1)%3 eq 0}">
                                         <c:set var="billNo" value="${billNo+1}"/>
                                       <tr>
                                        <td style="display:none">${senarai.idKandungan}</td>
                                        <td>${billNo} </td>
                                      </c:if>
                                          <td width="30%"><s:text name="${senarai.subtajuk}" id="${senarai.subtajuk}" value="${senarai.kandungan}" size="50"/> </td>
                          </c:forEach>
                         </table> 
                            </td></tr>
                         <tr><!-- Hidden field for recordCount -->
                            <td><s:hidden name="count5" id="count5" value="${(fn:length(actionBean.senaraiMingguan))/3}" />
                                <s:hidden name="testCount5" id="testCount5" value="${(fn:length(actionBean.senaraiMingguan))/3}" /></td>
                         </tr>
                         <tr>
                             <td><s:button class="btn" value="Tambah" name="add" id="tambah" onclick="addRow2('tbl5','count5')"/>
                                <s:button class="btn" value="Hapus" name="Hapus" id="Hapus" onclick="deleteRow('tbl5','count5')"/></td>
                         </tr>
                   </c:if>

                  <tr><td><b>13. IKLAN DI RADIO</b></td></tr>
                   <c:if test="${edit}">
                     <tr><td>
                             <table  width="100%" id="tbl6" class="tablecloth" border="1">
                         <tr>
                             <th  width="10%" align="center"><b>BIL</b></th>
                             <th  width="45%" align="center"><b>TARIKH SIARAM</b></th>
                             <th  width="45%" align="center"><b>SALINAN FOTOSTAT BUKTI <br/> PENGIKLANAN DI RADIO</b></th>
                         </tr>
                         <c:set var="recordCount" value="0"/>
                         <c:set var="billNo" value="0"/>
                          <c:forEach items="${actionBean.senaraiIkaranDIRadio}" var="senarai">
                               <c:set var="recordCount" value="${recordCount+1}"/>
                                     <c:if test="${(recordCount-1)%2 eq 0}">
                                         <c:set var="billNo" value="${billNo+1}"/>
                                       <tr>
                                        <td style="display:none">${senarai.idKandungan}</td>
                                        <td>${billNo} </td>
                                      </c:if>
                                          <td width="45%"><s:text name="${senarai.subtajuk}" id="${senarai.subtajuk}" value="${senarai.kandungan}" size="75"/> </td>
                          </c:forEach>
                         </table>
                            </td></tr>
                         <tr><!-- Hidden field for recordCount -->
                            <td><s:hidden name="count6" id="count6" value="${(fn:length(actionBean.senaraiIkaranDIRadio))/2}" />
                                <s:hidden name="testCount6" id="testCount6" value="${(fn:length(actionBean.senaraiIkaranDIRadio))/2}" /></td>
                         </tr>
                         <tr>
                             <td><s:button class="btn" value="Tambah" name="add" id="tambah" onclick="addRow6('tbl6','count6')"/>
                                 <s:button class="btn" value="Hapus" name="Hapus" id="Hapus" onclick="deleteRow('tbl6','count6')"/></td>
                         </tr>
                   </c:if>


                   <tr><td><b>14. KELULUSAN MELEPASKAN KUOTA YANG TELAH DIPEROLEHI DAN BAYARAN DEPADA KERAJAAN</b></td></tr>
                   <c:if test="${edit}">
                     <tr><td>
                             <table  width="100%" id="tbl7" class="tablecloth" border="1">
                         <tr>
                             <th  width="10%" align="center"><b>BIL</b></th>
                             <th  width="22%" align="center"><b>TARIKH</b></th>
                             <th  width="22%" align="center"><b>UNIT YANG DILEPASKAN</b></th>
                             <th  width="23%" align="center"><b>NO. RUJUKAN SURAT KELULUSAN</b></th>
                             <th  width="23%" align="center"><b>NO. RUJUKAN, RESIT BAYARAN DAN JUMLAH BAYARAN</b></th>
                         </tr>
                         <c:set var="recordCount" value="0"/>
                         <c:set var="billNo" value="0"/>
                          <c:forEach items="${actionBean.senaraiKelulusan}" var="senarai">
                               <c:set var="recordCount" value="${recordCount+1}"/>
                                     <c:if test="${(recordCount-1)%4 eq 0}">
                                         <c:set var="billNo" value="${billNo+1}"/>
                                       <tr>
                                        <td style="display:none">${senarai.idKandungan}</td>
                                        <td>${billNo} </td>
                                      </c:if>
                                          <td width="22%"><s:text name="${senarai.subtajuk}" id="${senarai.subtajuk}" value="${senarai.kandungan}" size="35"/> </td>
                          </c:forEach>
                         </table>                              
                            </td></tr>
                         <tr><!-- Hidden field for recordCount -->
                            <td><s:hidden name="count7" id="count7" value="${(fn:length(actionBean.senaraiKelulusan))/4}" />
                                <s:hidden name="testCount7" id="testCount7" value="${(fn:length(actionBean.senaraiKelulusan))/4}" /></td>
                         </tr>
                         <tr>
                             <td><s:button class="btn" value="Tambah" name="add" id="tambah" onclick="addRow4('tbl7','count7')"/>
                                 <s:button class="btn" value="Hapus" name="Hapus" id="Hapus" onclick="deleteRow('tbl7','count7')"/></td>
                         </tr>
                   </c:if>

                    <tr><td><b>15. PENDAFTARAN DENGAN PERTAN PROPERTIES </b></td></tr>
                   <c:if test="${edit}">
                        <tr><td><s:textarea name="pendaftaran" rows="5" cols="165"/></td></tr>
                   </c:if>
                   <c:if test="${!edit}">
                       <tr><td><font style="text-transform:capitalize">${actionBean.pendaftaran}&nbsp;</font></td></tr>
                   </c:if>
                    <tr><td><b>16. HARGA JUALAN </b></td></tr>
                   <c:if test="${edit}">
                        <tr><td><s:textarea name="hargaJualan" rows="5" cols="165"/></td></tr>
                   </c:if>
                   <c:if test="${!edit}">
                       <tr><td><font style="text-transform:capitalize">${actionBean.hargaJualan}&nbsp;</font></td></tr>
                   </c:if>
                     <tr><td><b>17. ULASAN PERTAM PROPERTIES </b></td></tr>
                   <c:if test="${edit}">
                        <tr><td><s:textarea name="ulasan" rows="5" cols="165"/></td></tr>
                   </c:if>
                   <c:if test="${!edit}">
                       <tr><td><font style="text-transform:capitalize">${actionBean.ulasan}&nbsp;</font></td></tr>
                   </c:if>

                     <tr><td><b>18. MAKLUMAT-MAKLUMAT LOT ORANG MELAYU YANG HENDAK DILEPASKAN DENGAN CFO</b></td></tr>
                   <c:if test="${edit}">
                     <tr><td>
                             <table  width="100%" id="tbl8" class="tablecloth" border="1">
                         <tr>
                             <th  width="10%" align="center"><b>BIL</b></th>
                             <th  width="23%" align="center"><b>JENIS BANGUNAN</b></th>
                             <th  width="23%" align="center"><b>TARIKH C.F DIKELUARKAN</b></th>
                             <th  width="22%" align="center"><b>%SIAP</b></th>
                             <th  width="22%" align="center"><b>NO. LOT/PT</b></th>
                         </tr>
                         <c:set var="recordCount" value="0"/>
                         <c:set var="billNo" value="0"/>
                          <c:forEach items="${actionBean.senaraiMaklumat}" var="senarai">
                               <c:set var="recordCount" value="${recordCount+1}"/>
                                     <c:if test="${(recordCount-1)%4 eq 0}">
                                         <c:set var="billNo" value="${billNo+1}"/>
                                       <tr>
                                        <td style="display:none">${senarai.idKandungan}</td>
                                        <td>${billNo} </td>
                                      </c:if>
                                          <td width="22%"><s:text name="${senarai.subtajuk}" id="${senarai.subtajuk}" value="${senarai.kandungan}" size="35"/> </td>
                          </c:forEach>
                         </table>                              
                            </td></tr>
                         <tr><!-- Hidden field for recordCount -->
                            <td><s:hidden name="count8" id="count8" value="${(fn:length(actionBean.senaraiMaklumat))/4}" />
                                <s:hidden name="testCount8" id="testCount8" value="${(fn:length(actionBean.senaraiMaklumat))/4}" /></td>
                         </tr>
                         <tr>
                             <td><s:button class="btn" value="Tambah" name="add" id="tambah" onclick="addRow4('tbl8','count8')"/>
                                 <s:button class="btn" value="Hapus" name="Hapus" id="Hapus" onclick="deleteRow('tbl8','count8')"/></td>
                         </tr>
                   </c:if>
                       <tr><td><b>19. RAYUAN PEMOHON </b></td></tr>
                   <c:if test="${edit}">
                        <tr><td><s:textarea name="rayuanPemohon" rows="5" cols="165"/></td></tr>
                   </c:if>
                   <c:if test="${!edit}">
                       <tr><td><font style="text-transform:capitalize">${actionBean.rayuanPemohon}&nbsp;</font></td></tr>
                   </c:if>
                       <tr><td><b>20. PERAKUAN PTG </b></td></tr>
                   <c:if test="${edit}">
                        <tr><td><s:textarea name="perakuanPTG" rows="5" cols="165"/></td></tr>
                   </c:if>
                   <c:if test="${!edit}">
                       <tr><td><font style="text-transform:capitalize">${actionBean.perakuanPTG}&nbsp;</font></td></tr>
                   </c:if>
                       <tr><td><b>21. KEPUTUSAN DIPOHON </b></td></tr>
                   <c:if test="${edit}">
                        <tr><td><s:textarea name="keputusan" rows="5" cols="165"/></td></tr>
                   </c:if>
                   <c:if test="${!edit}">
                       <tr><td><font style="text-transform:capitalize">${actionBean.keputusan}&nbsp;</font></td></tr>
                   </c:if>



                </table>
                    <c:if test="${edit}">
                        
                        <p align="center">
                            <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                            <%--<c:if test="${actionBean.pengguna.idPengguna eq 'jkbb1'}">
                                <s:button name="genReport" id="report" value="Jana Dokumen" class="btn" onclick="showReport();"/>&nbsp;
                            </c:if>--%>
                        </p>
                    </c:if>
            </div>
        </fieldset>
    </div>
</s:form>

