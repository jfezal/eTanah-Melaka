<%--
    Document   : kertas_Ringkas_JKBB_Pelan_Kuota
    Created on : Jul 05, 2010, 9:30:53 AM
    Author     : NageswaraRao
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>


<style type="text/css">
    #tdLabel {
        color:#003194;
        text-align:center;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        margin-left:30px;
        margin-right:0.5em;
               
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
        <%--alert("rowIndex:"+rowIndex);--%>
         if(rowIndex > 0){
         
         document.getElementById(recordCount).value = parseInt(document.getElementById(recordCount).value)-1;
         document.getElementById(tableid).deleteRow((parseInt(rowIndex)+1));
         

         <%--alert("rowIndex:"+rowIndex);--%>
        var testCount = document.getElementById("testCount"+tableIndex).value;
         <%--alert("testCount:"+testCount);--%>
        if(parseInt(rowIndex) <= parseInt(testCount)){

           try{
            <%--alert("javacall");--%>
           var url = '${pageContext.request.contextPath}/pembangunan/melaka/kertasRingkasPertimbanganJKBB?deleteSingle';
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
         alert("Tiada Boleh Dihapuskan");
            return false;
      }
    }



function addRow8(tableId,countId){
//        alert("tableid:"+tableId);
//        alert("tableCountId:"+countId);

        var table = document.getElementById(tableId);
        var rowcount = table.rows.length;
        var row = table.insertRow(rowcount);
        var tableIndex = parseInt(countId.substring(5));
      // Increment record count value by 1
        document.getElementById(countId).value = parseInt(document.getElementById(countId).value)+1;
        var recordIndex = document.getElementById(countId).value;
            recordIndex = 1+(6*(parseInt(recordIndex)-1));

        var cell1 = row.insertCell(0);
        var bil = document.createTextNode(rowcount-1);
        cell1.appendChild(bil);

        var cell2 = row.insertCell (1);
        var e2 = document.createElement ("INPUT");
        e2.setAttribute("Type", "text");
        e2.setAttribute("size","20");
        e2.setAttribute("name",(tableIndex)+"."+(recordIndex));
        e2.setAttribute("id",(tableIndex)+"."+(recordIndex));
        <%--e2.setAttribute("value",(tableIndex)+"."+(recordIndex));--%>
        cell2.appendChild (e2);


        var cell3 = row.insertCell(2);
        var e3 = document.createElement("INPUT");
        e3.setAttribute("type","text");
        e3.setAttribute("size","20");
        e3.setAttribute("name",(tableIndex)+"."+(parseInt(recordIndex)+1));
        e3.setAttribute("id",(tableIndex)+"."+(parseInt(recordIndex)+1));
        <%--e3.setAttribute("value",(tableIndex)+"."+(parseInt(recordIndex)+1));--%>
        cell3.appendChild(e3);

        var cell4 = row.insertCell(3);
        var e4 = document.createElement("INPUT");
        e4.setAttribute("type","text");
        e4.setAttribute("size","20");
        e4.setAttribute("name",(tableIndex)+"."+(parseInt(recordIndex)+2));
        e4.setAttribute("id",(tableIndex)+"."+(parseInt(recordIndex)+2));
        <%--e4.setAttribute("value",(tableIndex)+"."+(parseInt(recordIndex)+2));--%>
        cell4.appendChild(e4);

        var cell5 = row.insertCell(4);
        var e5 = document.createElement("INPUT");
        e5.setAttribute("type","text");
        e5.setAttribute("size","20");
        e5.setAttribute("name",(tableIndex)+"."+(parseInt(recordIndex)+3));
        e5.setAttribute("id",(tableIndex)+"."+(parseInt(recordIndex)+3));
        <%--e5.setAttribute("value",(tableIndex)+"."+(parseInt(recordIndex)+3));--%>
        cell5.appendChild(e5);

        var cell6 = row.insertCell(5);
        var e6 = document.createElement("INPUT");
        e6.setAttribute("type","text");
        e6.setAttribute("size","20");
        e6.setAttribute("name",(tableIndex)+"."+(parseInt(recordIndex)+4));
        e6.setAttribute("id",(tableIndex)+"."+(parseInt(recordIndex)+4));
        <%--e6.setAttribute("value",(tableIndex)+"."+(parseInt(recordIndex)+4));--%>
        cell6.appendChild(e6);

        var cell7 = row.insertCell(6);
        var e7 = document.createElement("INPUT");
        e7.setAttribute("type","text");
        e7.setAttribute("size","20");
        e7.setAttribute("name",(tableIndex)+"."+(parseInt(recordIndex)+5));
        e7.setAttribute("id",(tableIndex)+"."+(parseInt(recordIndex)+5));
        <%--e7.setAttribute("value",(tableIndex)+"."+(parseInt(recordIndex)+5));--%>
        cell7.appendChild(e7);
    }


</script>

<s:form beanclass="etanah.view.stripes.pembangunan.KertasRingkasPertimbanganJKBBActionBean">
    <s:messages/>
    <s:errors/>
    <s:hidden name="kertasK.kertas.idKertas"/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend></legend>
            	<%-- <c:set scope="request" var="senaraiPengarah"  value="${actionBean.listPengarah}"/>
                <c:set scope="request" var="senaraiPemohon"  value="${actionBean.listPemohon}"/> --%>

            <div class="content" align="center">
                <table border="0" width="80%">
                   <tr><td align="center"><b>KERTAS RINGKASAN UNTUK PERTIMBANGAN JKBB BAGI KELULUSAN PENENTUAN PELAN KUOTA</b><br></td></tr>
                   <c:if test="${edit}">
                       <td align="center"><b>BIL.</b> <s:text name="bilangan" size="20" class="normal_text"/><br><br><br></td>
                   </c:if>
                   <c:if test="${!edit}">
                       <td align="center"><b>BIL.</b> <b>${actionBean.bilangan}</b><br><br><br></td>
                   </c:if>
                                  
                   <tr><td>
                            <table border="0" width="100%" cellspacing="10">
                                <c:if test="${actionBean.stageId eq 'sediajkbbrekodkpsn'}">
                                <tr>
                                    <td width="25%"><b>Tarikh Mesyuarat</b></td>
                                    <td><b>:</b></td>
                                    <td><s:text name="tarikhMesyuarat" id="datepicker" class="datepicker" size="12" /></td>
                                </tr>
                                </c:if>
                                <tr>
                                    <td width="25%"><b>1) Nama Pemohon</b></td>
                                    <td><b>:</b></td>
                                    <td><b>${actionBean.pemohon.pihak.nama} </b></td>
                                </tr>
                                <tr>
                                    <td width="25%"><b>2) Nama Jurukur/Jururancang</b></td>
                                    <td><b>:</b></td>
                                    <td><b>${actionBean.permohonan.penyerahNama} </b></td>
                                </tr>
                                <tr>
                                    <td><b>3) No. Lot/Pt.</b></td>
                                    <td><b>:</b></td>
                                    <td><b>${actionBean.hp.hakmilik.lot.nama}  <fmt:formatNumber  pattern="00" value="${actionBean.hp.hakmilik.noLot}"/></b></td>
                                </tr>
                                <tr>
                                    <td><b>4) Mukim </b></td>
                                    <td><b>:</b></td>
                                    <td><b>${actionBean.hp.hakmilik.bandarPekanMukim.nama}</b></td>
                                </tr>
                                <tr>
                                    <td><b>5) Lokasi </b></td>
                                    <td><b>:</b></td>
                                    <td><b>${actionBean.hp.hakmilik.lokasi}</b></td>
                                </tr>                                
                                <tr>
                                    <td><b>6) Komponen Pembangunan </b></td>
                                    <td><b>:</b></td>
                                    <c:if test="${edit && actionBean.stageId ne 'sediajkbbrekodkpsn'}">
                                        <td><b> <s:text name="komponenPembangunan" size="80" class="normal_text" /></b></td>
                                    </c:if>
                                    <c:if test="${!edit}">
                                        <td><b>${actionBean.komponenPembangunan}</b></td>
                                    </c:if>
                                </tr>
                                <tr>
                                    <td><b>7) Kuota </b></td>
                                    <td><b>:</b></td>
                                    <c:if test="${edit && actionBean.stageId ne 'sediajkbbrekodkpsn'}">
                                        <td><s:textarea name="kuota" rows="5" cols="110" class="normal_text"/></td>
                                        <%--<td><b> <s:text name="kuota" size="80" class="normal_text" /></b></td> --%>
                                    </c:if>
                                    <c:if test="${!edit}">
                                        <td><b>${actionBean.kuota}</b></td>
                                    </c:if>
                                </tr>
                        </table></td></tr> <br>
                  
                   <tr><td><b>&nbsp;&nbsp;8) Jenis Pembangunan Dan Syor Penentuan : </b></td></tr>
                            <tr><td>
                                    <table class="tablecloth" border="1" width="80%" id="tbl8">
                                        <tr>
                                          <th  width="5%" align="center" rowspan="2"><b>BIL</b></th>
                                          <th rowspan="2" width="25%" align="center"><b>JENIS</b></th>
                                          <th rowspan="2" width="15%" align="center"><b>JUMLAH UNIT</b></th>
                                          <th colspan="2" width="30%" align="center"><b>LOT ORANG MELAYU</b></th>
                                          <th colspan="2" width="30%" align="center"><b>LOT BUKAN ORANG MELAYU</b></th>
                                        </tr>
                                        <tr>
                                          <th width="15%" align="center"><b>UNIT</b> </th>
                                          <th align="center"><b>%</b></th>
                                          <th width="15%" align="center"><b>UNIT</b></th>
                                          <th align="center"><b>%</b></th>
                                        </tr>
                                         <c:set var="recordCount" value="0"/>
                                         <c:set var="billNo" value="0"/>                                         
                                          <c:forEach items="${actionBean.senaraiJenisPermohonan}" var="senarai">
                                               <c:set var="recordCount" value="${recordCount+1}"/>
                                               <c:if test="${actionBean.checkValue gt 4 && actionBean.checkValue eq recordCount-2}">
                                                   <tr> 
                                                       <td colspan="7"> <hr> </td>
                                                   </tr>
                                               </c:if>
                                               
                                                     <c:if test="${(recordCount-1)%6 eq 0}">
                                                         <c:set var="billNo" value="${billNo+1}"/>                                                        
                                                       <tr>
                                                        <td style="display:none">${senarai.idKandungan}</td>
                                                        <td width="4%">${billNo} </td>
                                                     </c:if>
                                                        
                                                     <c:if test="${edit && actionBean.stageId ne 'sediajkbbrekodkpsn'}">
                                                        <td width="16%"><s:text name="${senarai.subtajuk}" id="${senarai.subtajuk}" value="${senarai.kandungan}" class="normal_text"/> </td>
                                                     </c:if>
                                                        
                                                     <c:if test="${!edit}">
                                                        <td width="16%">${senarai.kandungan} </td>
                                                     </c:if>
                                                        
                                                        
                                          </c:forEach>
                                       </table>
                                       </td></tr>
                                       <tr><!-- Hidden field for recordCount -->
                                        <td><s:hidden name="count8" id="count8" value="${(fn:length(actionBean.senaraiJenisPermohonan))/6}" />
                                            <s:hidden name="testCount8" id="testCount8" value="${(fn:length(actionBean.senaraiJenisPermohonan))/6}" /></td>
                                       </tr>
                                       
                                       <c:if test="${edit}">
                                       <tr>
                                         <td><s:button class="btn" value="Tambah" name="add" id="tambah" onclick="addRow8('tbl8','count8')"/>
                                             <s:button class="btn" value="Hapus" name="Hapus" id="Hapus" onclick="deleteRow('tbl8','count8')"/></td>
                                       </tr>
                                       </c:if>

                           <%-- <tr>
                                <td> <s:button class="btn" value="Tambah" name="add" onclick="addRow('tbl')"/>&nbsp; </td>
                            </tr> <br>--%>

                            <%--<tr><td>
                                <table border="0" width="100%" cellspacing="10">                                    
                                   <tr><td width="25%"><b>9) Keputusan JKBB</b></td>
                                       <td><b>:</b></td>
                                        <c:if test="${edit}">
                                            <td><b> <s:radio name="keputusan" value="L"/>&nbsp;Setuju /
                                                    <s:radio name="keputusan" value="T"/>&nbsp;tidak setuju /
                                                    <s:radio name="keputusan" value="T"/>&nbsp;tangguh dengan pindaan </b></td>
                                        </c:if>
                                        <c:if test="${!edit}">
                                            <td><b>${actionBean.permohonanRujukanLuar.noSidang}</b></td>
                                        </c:if>
                                   </tr>
                                </table>
                                </td>
                            </tr>--%>
                        </table>
                      
                    <%--<c:if test="${edit}">
                        <br>
                        <p align="center">--%>
                    <c:if test="${edit || actionBean.stageId eq 'sediajkbbrekodkpsn'}">
                            <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </c:if>
                       <%-- </p>
                    </c:if>--%>
            </div>
        </fieldset>
    </div>
</s:form>