<%--
    Document   : nilaian_jpph
    Created on : Jul 15, 2010, 2:56:33 PM
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
</style><script type="text/javascript">
    function addRow(tableid){
        var table = document.getElementById(tableid);
        var rowcount = table.rows.length;

       if(rowcount < 11){
        var row = table.insertRow(rowcount);

        var cell1 = row.insertCell(0);
        var bil = document.createTextNode(rowcount);
        cell1.appendChild(bil);

        var cell2 = row.insertCell(1);
         var e2 = document.createElement("INPUT");
        e2.setAttribute("type","text");
        e2.setAttribute("name","lot[" +(rowcount-1)+"]");
        e2.setAttribute("size","40");
        cell2.appendChild(e2);

        var cell3 = row.insertCell(2);
        var e3 = document.createElement("INPUT");
        e3.setAttribute("type","text");
        e3.setAttribute("name","keterangan[" +(rowcount-1)+"]");
        e3.setAttribute("size","40");
        cell3.appendChild(e3);

        var cell4 = row.insertCell(3);
        var e4 = document.createElement("INPUT");
        e4.setAttribute("type","text");
        e4.setAttribute("name","jpph[" +(rowcount-1)+"]");
        e4.setAttribute("size","40");
        cell4.appendChild(e4);
        } else{
            alert('Maximum Row Limit.');
            <%--$("#syorptd").focus();--%>
            return true;
        }
    }

</script>

<s:form beanclass="etanah.view.stripes.pembangunan.NilaianJPPHActionBean">
    <s:messages/>
    <s:errors/>
    <%--<s:hidden name="kandunganK.kertas.idKertas"/>--%>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend></legend>
            <div class="content" align="center">
                <table border="0" width="80%">
                       <%--<c:if test="${edit}">--%>
                           <%--<tr><td>
                                   <table border="0" width="100%" id="tblNilaianTanah" class="tablecloth">
                                       <tr><th> No. </th>
                                           <th> Lot/Plot </th>
                                           <th> Nilaian JPPH (RM) </th></tr>
                                    </table>
                               </td></tr>--%>
                           <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0" id="tbl1" >
                               <display:column title="No." sortable="true" style="vertical-align:baseline">${tbl1_rowNum}</display:column>
                               <display:column title="No.Lot/PT." style="vertical-align:baseline">
                                        ${tbl1.hakmilik.lot.nama} ${tbl1.hakmilik.noLot}
                               </display:column>
                               <display:column title=" Nilaian JPPH (RM)" style="vertical-align:baseline">
                                   <s:text name="nilaianJpph${tbl1_rowNum-1}" id="nilaianJpph${tbl1_rowNum-1}" value="${tbl1.nilaianJpph}" size="15" formatPattern="######.00"/>
                               </display:column>
                          </display:table>
                           <%--<tr><td align="right" width="80%">
                                       <s:button class="btn" value="Tambah" name="add" onclick="addRow('tblNilaianTanah')"/>&nbsp;

                               </td>

                           </tr><br>--%>
                           <br>
                           <tr>
                               <td align="center" width="80%">
                                    <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>

                               </td>
                           </tr>
                        <%--</c:if>--%>
                        <%--<c:if test="${!edit}">
                            <table border="0" width="96%" id="tblNilaianTanah">
                                       <tr><td> No. </td></tr>
                                       <tr><td> Lot/Plot </td></tr>
                                       <tr><td> Nilaian JPPH (RM) </td></tr>
                            </table>
                         </c:if>--%>
                        </table>

            </div>
        </fieldset>
    </div>
</s:form>