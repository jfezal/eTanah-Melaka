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
    function addRow(tableid){
        var table = document.getElementById(tableid);
        var rowcount = table.rows.length;
        var row = table.insertRow(rowcount);

        var cell1 = row.insertCell(0);
        var e1 = document.createElement("INPUT");
        e1.setAttribute("type","text");
        e1.setAttribute("name","jenis[" +(rowcount-2)+"]");
        e1.setAttribute("size","34");
        <%--e1.setAttribute("value", rowcount-1);--%>
        cell1.appendChild(e1);

        var cell2 = row.insertCell(1);
        var e2 = document.createElement("INPUT");
        e2.setAttribute("type","text");
        e2.setAttribute("name","jumlahUnit[" +(rowcount-2)+"]");
        e2.setAttribute("size","20");
        <%--e2.setAttribute("value", rowcount-1);--%>
        cell2.appendChild(e2);

        var cell3 = row.insertCell(2);
        var e3 = document.createElement("INPUT");
        e3.setAttribute("type","text");
        e3.setAttribute("name","bumiputraUnit[" +(rowcount-2)+"]");
        e3.setAttribute("size","20");
        <%--e3.setAttribute("value", rowcount-1);--%>
        cell3.appendChild(e3);

        var cell4 = row.insertCell(3);
        var e4 = document.createElement("INPUT");
        e4.setAttribute("type","text");
        e4.setAttribute("name","bumiputraPeratus["+(rowcount-2)+"]");
        e4.setAttribute("size","20");
        <%--e4.setAttribute("value", rowcount-1);--%>
        cell4.appendChild(e4);

        var cell5 = row.insertCell(4);
        var e5 = document.createElement("INPUT");
        e5.setAttribute("type","text");
        e5.setAttribute("name","bukanBumputUnit["+(rowcount-2)+"]");
        e5.setAttribute("size","20");
        <%--e5.setAttribute("value", rowcount-1);--%>
        cell5.appendChild(e5);

        var cell6 = row.insertCell(5);
        var e6 = document.createElement("INPUT");
        e6.setAttribute("type","text");
        e6.setAttribute("name","bukanBumptPeratus[" +(rowcount-2)+"]");
        e6.setAttribute("size","20");
        <%--e6.setAttribute("value", rowcount-1);--%>
        cell6.appendChild(e6);

    }

</script>

<s:form beanclass="etanah.view.stripes.pembangunan.KertasPertimbanganPTGActionBean">
    <s:messages/>
    <s:errors/>
    <s:hidden name="kandunganK.kertas.idKertas"/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend></legend>
            	<%-- <c:set scope="request" var="senaraiPengarah"  value="${actionBean.listPengarah}"/>
                <c:set scope="request" var="senaraiPemohon"  value="${actionBean.listPemohon}"/> --%>

            <div class="content" align="center">
                <table border="0" width="80%">
                   <tr><td align="center"><b><u>KERTAS RINGKASAN UNTUK PERTIMBANGAN JKBB BAGI KELULUSAN PENENTUAN PELAN KUOTA </u></b></td></tr><br>
                   <tr><td>
                            <table border="0" width="80%" cellspacing="10">
                                <tr><td width="30%"><b>1) Nama Pemaju</b></td>
                                <td><b>:</b></td>
                                <c:if test="${edit}">
                                    <td><b> <%--<s:text name="permohonanRujukanLuar.noSidang" size="20"/>--%></b></td>
                                </c:if>
                                <c:if test="${!edit}">
                                    <td><b><%--${actionBean.permohonanRujukanLuar.noSidang}--%></b></td>
                                </c:if>
                            </tr>
                            <tr><td><b>2) Nama Jurukur/Jururancang</b></td>
                                <td><b>:</b></td>
                                <c:if test="${edit}">
                                    <td><b><%-- <s:text name="permohonanRujukanLuar.tarikhSidang" id="datepicker" class="datepicker" size="20" />--%></b></td>
                                </c:if>
                                <c:if test="${!edit}">
                                    <td><b><%--${actionBean.permohonanRujukanLuar.tarikhSidang}--%></b></td>
                                </c:if>
                            </tr>
                            <tr><td><b>3) No. Lot/Pt.</b></td>
                                <td><b>:</b></td>
                                <c:if test="${edit}">
                                    <td><b> <%--<s:text name="tarikhMesyuarat" id="datepicker" class="datepicker" size="20" />--%></b></td> 
                                </c:if>
                                <c:if test="${!edit}">
                                    <td><b><%--${actionBean.tarikhMesyuarat}--%></b></td>
                                </c:if>
                            </tr>
                            <tr><td><b>4) Mukim </b></td>
                                <td><b>:</b></td>
                                <c:if test="${edit}">
                                    <td><b> <%--<s:textarea name="permohonanRujukanLuar.lokasiAgensi" rows="3" cols="50"/>--%></b></td>
                                </c:if>
                                <c:if test="${!edit}">
                                    <td><b><%--${actionBean.permohonanRujukanLuar.lokasiAgensi}--%></b></td>
                                </c:if>
                            </tr>
                            <tr><td><b>5) Lokasi </b></td>
                                <td><b>:</b></td>
                                <c:if test="${edit}">
                                    <td><b> <%--<s:textarea name="permohonanRujukanLuar.lokasiAgensi" rows="3" cols="50"/>--%></b></td>
                                </c:if>
                                <c:if test="${!edit}">
                                    <td><b><%--${actionBean.permohonanRujukanLuar.lokasiAgensi}--%></b></td>
                                </c:if>
                            </tr>
                            <tr><td><b>6) Komponen Pembangunan </b></td>
                                <td><b>:</b></td>
                                <c:if test="${edit}">
                                    <td><b> <s:text name="komponenPembangunan" size="20" /></b></td>
                                </c:if>
                                <c:if test="${!edit}">
                                    <td><b>${actionBean.komponenPembangunan}</b></td>
                                </c:if>
                            </tr>
                            <tr><td><b>7) Kuota </b></td>
                                <td><b>:</b></td>
                                <c:if test="${edit}">
                                    <td><b> <s:text name="kuota" size="20" /></b></td>
                                </c:if>
                                <c:if test="${!edit}">
                                    <td><b>${actionBean.kuota}</b></td>
                                </c:if>
                            </tr>
                        </table></td></tr> <br>
                             <tr><td><b>8) Jenis Pembangunan Dan Syor Penentuan : </b></td></tr>
                            <tr><td>
                                    <table class="tablecloth" border="1" width="100%" id="tbl">
                                        <tr>
                                          <th rowspan="2" width="25%" align="center"><b>JENIS</b></th>
                                          <th rowspan="2" width="15%" align="center"><b>JUMLAH UNIT</b></th>
                                          <th colspan="2" width="30%" align="center"><b>LOT BUMIPUTRA</b></th>
                                          <th colspan="2" width="30%" align="center"><b>LOT BUKAN BUMIPUTRA</b></th>
                                        </tr>
                                        <tr>
                                          <th width="15%" align="center"><b>UNIT</b> </th>
                                          <th align="center"><b>PERATUS</b></th>
                                          <th width="15%" align="center"><b>UNIT</b></th>
                                          <th align="center"><b>PERATUS</b></th>
                                        </tr>
                                        
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td> <s:button class="btn" value="Tambah" name="add" onclick="addRow('tbl')"/>&nbsp; </td>
                            </tr> <br>

                            <tr><td>
                                <table border="0" width="100%" cellspacing="10">
                                    <%--<tr><td width="25%"><b>9) Keputusan JKBB</b></td>
                                        <td><b>:</b></td>
                                        <c:if test="${edit}">
                                            <td><b> <s:text name="keputusan" size="20"/></b></td>
                                        </c:if>
                                        <c:if test="${!edit}">
                                            <td><b>${actionBean.permohonanRujukanLuar.noSidang}</b></td>
                                        </c:if>
                                   </tr>--%>
                                   <tr><td width="25%"><b>9) Keputusan JKBB</b></td>
                                       <td><b>:</b></td>
                                        <c:if test="${edit}">
                                            <td><b> <s:radio name="keputusan" value="L"/>&nbsp;Setuju /
                                                    <s:radio name="keputusan" value="T"/>&nbsp;tidak setuju /
                                                    <s:radio name="keputusan" value="T"/>&nbsp;tangguh dengan pindaan </b></td>
                                        </c:if>
                                        <c:if test="${!edit}">
                                            <td><b><%--${actionBean.permohonanRujukanLuar.noSidang}--%></b></td>
                                        </c:if>


                                   </tr>
                                </table>
                                </td>
                            </tr>
                        </table>
                       <%--<c:if test="${actionBean.btn}">--%>
                                
                          <%--  </c:if>--%>
                   <%-- <c:if test="${edit}">
                        <br>
                        <p align="center">
                            <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                        </p>
                    </c:if>--%>
            </div>
        </fieldset>
    </div>
</s:form>