<%-- 
    Document   : butiran_pembatalan
    Created on : Oct 26, 2010, 3:16:04 PM
    Author     : massita
--%>



<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252" language="java"%>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>



<script type="text/javascript">
 function addRow(tableID1) {
       document.getElementById("rowCount").value = 1;
       var table = document.getElementById(tableID1);

       var rowCount1 = table.rows.length;
       var row = table.insertRow(rowCount1);

       var cell2 = row.insertCell(0);
       cell2.innerHTML = "<b>"+(rowCount1+1)+"</b>";

       var cell1 = row.insertCell(1);
       var element1 = document.createElement("textarea");
       element1.t = "text"+(rowCount1+1);
       element1.rows = 4;
       element1.cols = 40;
       element1.name ="text"+(rowCount1+1);
       element1.id ="text"+(rowCount1+1);
       cell1.appendChild(element1);
       document.getElementById("rowCount").value=rowCount1 +1;

   }

    function delRow(tableid) {
       var table = document.getElementById(tableid);
       var rowCount = table.rows.length;
       table.deleteRow(rowCount-1);
   }

</script>

<s:form beanclass="etanah.view.stripes.pengambilan.ButiranPembatalanHakLaluLangActionBean">
    <div class="subtitle">
        <s:errors/>
        <s:messages/>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Sebab Pembatalan Hak Lalu Lalang</legend>
            <s:hidden name="rowCount" id="rowCount" value="0"></s:hidden>
            <table name ="dataTable1" id ="dataTable1" align="center">
             <tr>
                <td><b>1</b></td>
                <td><s:textarea name="text1" id="text1" rows="4" cols="40"></s:textarea></td>
             </tr>
             </table>
               </fieldset><br />
                 <p align="center">

                    <s:button name="add" id="add" value="Tambah" class="btn" onclick="addRow('dataTable1')"/>
                    <s:button name="" id="" value="Hapus" class="btn" onclick="delRow('dataTable1')"/>
                    <s:button name="" id="" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </p>
             
      

    </div>
 
 
</s:form>
 