<%--
    Document   : kertasPertimbangan_mmk_333
    Created on : Feb 1, 2010, 12:01:43 PM
    Author     : murali

--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>

<style type="text/css">
    #tdLabel {
        color:black;
        display:block;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        margin-left:250px;
        margin-right:0.5em;
        text-align:right;
        width:15em;
        vertical-align:top;
    }

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
        width:40em;
    }
</style>
<script type="text/javascript">
    function addRow(tableID,k) {

        //alert(tableID);
    <%-- document.getElementById("dataTable2").value = 1;--%>
               var table = document.getElementById(tableID);
               var rowCount = table.rows.length;
               var row = table.insertRow(rowCount);
               var tableNo = tableID.substring(9);

               var cell0 = row.insertCell(0);
               cell0.innerHTML = "<b>"+ tableNo  +"." +(rowCount+k+1)+"</b>";

               var cell1 = row.insertCell(1);
               var element1 = document.createElement("textarea");
               element1.t = "kandungan"+tableNo+""+(rowCount+1);
               element1.rows = 5;
               element1.cols = 100;
               element1.name ="kandungan"+tableNo+""+(rowCount+1);
               element1.id ="kandungan"+tableNo+""+(rowCount+1);
               cell1.appendChild(element1);
    <%--        if(parseInt(rowCount)+1 == 2){
              element1.value = "";
            }--%>

    <%-- document.getElementById("rowCount"+tableNo).value=rowCount+1;
     var cell2 = row.insertCell(2);
     var e1 = document.createElement("INPUT");
     e1.t = "button"+(rowCount+1);
     e1.setAttribute("type","button");
     e1.className="btn";
     e1.value="Hapus";
     e1.id =(rowCount+1);
     e1.onclick=function(){deleteRow(tableID,(e1.id));};
     cell2.appendChild(e1);--%>

                document.getElementById("rowCount"+tableNo).value=rowCount+1;
                var cell2 = row.insertCell(2);
                var e1 = document.createElement("img");
                e1.t = "button"+(rowCount+1);
                e1.src = "${pageContext.request.contextPath}/images/not_ok.gif";
                e1.alt = "klik untuk hapus";
                e1.align = "top"
                e1.value="Hapus";
                e1.id =(rowCount+1);
                e1.onclick=function(){deleteRow(tableID,(e1.id));};
                cell2.appendChild(e1);
            }



            function deleteRow(tableID,elementId)
            {
                var tableNo = tableID.substring(9);
                var i =  document.getElementById("rowCount"+tableNo).value;
                document.getElementById("rowCount"+tableNo).value = i-1;

    <%-- alert("tableNo:"+tableNo);
     alert("elementId:"+elementId);
     alert("count:"+document.getElementById("rowCount"+tableNo).value);--%>

                var x= document.getElementById(tableID).rows[elementId-1].cells;
                var idKandungan = x[0].innerHTML;
                //  alert("idKandungan:"+idKandungan);
                document.getElementById(tableID).deleteRow(elementId-1);
                var url = '${pageContext.request.contextPath}/strata/Kertas_pertimbangan_MMK?deleteSingle&idKandungan='
                    +idKandungan;


                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');

                regenerateBill(tableID);
            }

            function regenerateBill(tableID){

                try{
                    var table = document.getElementById(tableID);
                    var rowCount = table.rows.length;
                    for(var i=1;i<rowCount;i++){
                        //  alert("id:"+(table.rows[i].cells[2].childNodes[0].id));
                        //  alert(table.rows[i].cells[0].childNodes[0].data);
                        //  table.rows[i].cells[0].childNodes[0].data= "4."+(i+1);
                        table.rows[i].cells[2].childNodes[0].id= i+1;
                    }
                }catch(e){
    <%--alert("Error in regenerateBill");--%>
                        }
                    }


</script>

<s:form beanclass="etanah.view.strata.KertasPertimbanganMMkActionBean">
    <s:messages/>
    <s:errors/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend></legend>
            <div class="content" align="center">
                <table border="0" width="70%">

                    <tr><td align="center"><b>(MAJLIS MESYUARAT KERAJAAN 
<!--                                PADA <font color="red">*</font> <s:text name="tarikhMesyuarat" id="datepicker" class="datepicker" size="12" />)-->
                           ) </b></td></tr>

                    <tr><td><b><font style="text-transform: uppercase"><center>${actionBean.tajuk}</center></font></b></td></tr>

                    <tr><td>&nbsp;</td></tr>
                    <tr><td>&nbsp;</td></tr>
                    <tr><td><b>1. TUJUAN</b></td></tr>

                    <tr><td>&nbsp;</td></tr>
                    <tr><td>
                            <table id ="dataTable1">
                                <c:set var="i" value="1" />
                                <c:set var="k" value="1" />
                                <c:set var="index" value="2" />
                                <c:forEach items="${actionBean.senaraiLaporanKandungan1}" var="line">
                                    <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="1.${k}"/></td>
                                        <td><font style="text-transform: uppercase"><s:textarea name="kandungan1${i}" id="kandungan1${i}" readonly="readonly"  rows="5" cols="100" onkeyup="this.value=this.value.toUpperCase();">${line.kandungan}</s:textarea> <%--<s:button class="btn" value="Hapus" name="delete" id="${i}" onclick="deleteRow('dataTable1',this.id)"/>--%></font>
                                        </td></tr>
                                        <c:set var="i" value="${i+1}" />
                                        <c:set var="k" value="${k+1}" />
                                    </c:forEach>

                            </table>
                        </td>
                    </tr>
                    <tr><td><s:hidden name="rowCount1" value="${i-1}" id="rowCount1"/>&nbsp;</td></tr>
                    <%--<tr>
                       <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                       <s:button class="btn" value="Tambah" name="add" onclick="addRow('dataTable1',1)"/>&nbsp;</td>
                    </tr>--%>
                    <tr><td><b>2. LATAR BELAKANG</b></td></tr>

                    <tr><td>&nbsp;</td></tr>

                    <tr><td>
                            <table id ="dataTable2" border="0">
                                <c:set var="i" value="1" />
                                <c:set var="k" value="1" />
                                <c:forEach items="${actionBean.senaraiLaporanKandungan2}" var="line">
                                    <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="2.${k}"/></td>
                                        <td><font style="text-transform: uppercase"><s:textarea name="kandungan2${i}" id="kandungan2${i}" readonly="readonly"  rows="5" cols="100" onkeyup="this.value=this.value.toUpperCase();">${line.kandungan}</s:textarea> <%--<s:button class="btn" value="Hapus" name="delete" onclick="deleteRow('dataTable2',i)"/>--%></font>
                                        </td></tr>
                                        <c:set var="i" value="${i+1}" />
                                        <c:set var="k" value="${k+1}" />
                                    </c:forEach>
                            </table>
                        </td>
                    </tr>
                    <tr><td> <s:hidden name="rowCount2" value="${i-1}" id="rowCount2"/>&nbsp;</td></tr>
                    <%--<tr>
                       <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                        <s:button class="btn" value="Tambah" name="add" onclick="addRow('dataTable2',2)"/>&nbsp;</td>

                    </tr>--%><td>&nbsp;</td>
                    <tr><td><b>3. ASAS PERMOHONAN</b></td></tr>

                    <tr><td>&nbsp;</td></tr>

                    <tr><td>
                            <table id ="dataTable3">
                                <c:set var="i" value="1" />
                                <c:set var="k" value="1" />
                                <c:forEach items="${actionBean.senaraiLaporanKandungan3}" var="line">
                                    <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="3.${k}"/></td>
                                        <td><font style="text-transform: uppercase"><s:textarea name="kandungan3${i}" id="kandungan3${i}" readonly="readonly"  rows="5" cols="100" onkeyup="this.value=this.value.toUpperCase();">${line.kandungan}</s:textarea> <%--<s:button class="btn" value="Hapus" name="delete" onclick="delete3(rowCount3)"/>--%></font>
                                        </td></tr>
                                        <c:set var="i" value="${i+1}" />
                                        <c:set var="k" value="${k+1}" />
                                    </c:forEach>

                            </table>
                        </td>
                    </tr>  <tr><td><s:hidden name="rowCount3" value="${i-1}" id="rowCount3"/> &nbsp;</td></tr>

                    <%--<tr>
                       <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                        <s:button class="btn" value="Tambah" name="add" onclick="addRow('dataTable3',3)"/>&nbsp;</td>

                    </tr>--%><td>&nbsp;</td>
                    <tr><td><b>4. ULASAN PENGARAH TANAH DAN GALIAN, NEGERI SEMBILAN DARUL KHUSUS</b></td></tr>

                    <tr><td>&nbsp;</td></tr>
                    <c:if test="${actionBean.emptySenarai}">
                        <tr>
                            <td>
                                <table>
                                    <tr><td valign="top"><b>4.1</b></td>
                                        <td><font style="text-transform:uppercase">
                                            <s:textarea name="kandungan4" id="kandungan4"  rows="5" cols="100" onkeyup="this.value=this.value.toUpperCase();">${actionBean.ulasapengarah}</s:textarea></font>
<!--                                                ${actionBean.ulasapengarah}</font>-->
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </c:if>
                    <tr><td>
                            <table id ="dataTable4" border="0">
                                <c:set var="i" value="1" />
                                <c:if test="${actionBean.senaraiLaporanKandungan4 eq null}"><c:set var="k" value="2" /></c:if>
                                <c:if test="${actionBean.senaraiLaporanKandungan4 ne null}"><c:set var="k" value="1" /></c:if>
                                <c:forEach items="${actionBean.senaraiLaporanKandungan4}" var="line">
                                    <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="4.${k}"/></td>
                                        <td><font style="text-transform: uppercase"><s:textarea name="kandungan4${i}" id="kandungan4${i}"  rows="5" cols="100" onkeyup="this.value=this.value.toUpperCase();">${line.kandungan}</s:textarea>
                                                <%--<s:button class="btn" value="Hapus" name="delete" id="${i}" onclick="deleteRow('dataTable4',this.id)"/>--%>
                                            </font>
                                        </td>
                                        <td>
                                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                 id="${i}" onclick="deleteRow('dataTable4',this.id)" onmouseover="this.style.cursor='pointer';">
                                        </td></tr>
                                        <c:set var="i" value="${i+1}" />
                                        <c:set var="k" value="${k+1}" />
                                    </c:forEach>

                            </table>
                        </td>
                    </tr> <tr><td><s:hidden name="rowCount4" value="${i-1}" id="rowCount4"/> &nbsp;</td></tr>
                    <tr>
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                            <s:button class="btn" value="Tambah" name="add" onclick="addRow('dataTable4',1)"/>&nbsp;</td>

                    </tr><td>&nbsp;</td>
                    <tr><td><b>5. SYOR PENGARAH TANAH DAN GALIAN NEGERI SEMBILAN</b></td></tr>

                    <tr><td>&nbsp;</td></tr>
                    <c:if test="${actionBean.emptySenarai5}">
                        <tr>
                            <td>
                                <table>
                                    <tr><td valign="top"><b>5.1</b></td>
                                        <td><font style="text-transform:uppercase">
                                                ${actionBean.syorpengarah}</font></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </c:if>
                    <tr><td>
                            <table id ="dataTable5" border="0">
                                <c:set var="i" value="1" />
                                <c:if test="${actionBean.senaraiLaporanKandungan5 eq null}"> <c:set var="k" value="2" /></c:if>
                                <c:if test="${actionBean.senaraiLaporanKandungan5 ne null}"> <c:set var="k" value="1" /></c:if>
                                <c:forEach items="${actionBean.senaraiLaporanKandungan5}" var="line">
                                    <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="5.${k}"/></td>
                                        <td><font style="text-transform: uppercase"><s:textarea name="kandungan5${i}" id="kandungan5${i}"  rows="5" cols="100" onkeyup="this.value=this.value.toUpperCase();">${line.kandungan}</s:textarea> <s:button class="btn" value="Hapus" name="delete" id="${i}" onclick="deleteRow('dataTable5',this.id)"/></font>
                                        </td></tr>
                                        <c:set var="i" value="${i+1}" />
                                        <c:set var="k" value="${k+1}" />
                                    </c:forEach>

                            </table>
                        </td>
                    </tr> <tr><td><s:hidden name="rowCount5" value="${i-1}" id="rowCount5"/>&nbsp;</td></tr>
                    <tr>
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                            <s:button class="btn" value="Tambah" name="add" onclick="addRow('dataTable5',1)"/>&nbsp;</td>

                    </tr><td>&nbsp;</td>
                    <tr><td><b>6. KEPUTUSAN</b></td></tr>
                    <tr><td>&nbsp;</td></tr>
                    <c:if test="${actionBean.emptySenarai6}">
                        <tr>
                            <td>
                                <table>
                                    <tr><td valign="top"><b>6.1</b></td>
                                        <td><font style="text-transform:uppercase">
                                                &nbsp; ${actionBean.keputusan}</font></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </c:if>
                    <tr><td>
                            <table id ="dataTable6">
                                <c:set var="i" value="1" />
                                <c:if test="${actionBean.senaraiLaporanKandungan6 eq null}"><c:set var="k" value="2" /></c:if>
                                <c:if test="${actionBean.senaraiLaporanKandungan6 ne null}"><c:set var="k" value="1" /></c:if>
                                <c:forEach items="${actionBean.senaraiLaporanKandungan6}" var="line">
                                    <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="6.${k}"/></td>
                                        <td><font style="text-transform: uppercase"><s:textarea name="kandungan6${i}" id="kandungan6${i}"  rows="5" cols="100" onkeyup="this.value=this.value.toUpperCase();">${line.kandungan}</s:textarea> <s:button class="btn" value="Hapus" name="delete" id="${i}" onclick="deleteRow('dataTable6',this.id)"/></font>
                                        </td></tr>
                                        <c:set var="i" value="${i+1}" />
                                        <c:set var="k" value="${k+1}" />
                                    </c:forEach>

                            </table>
                        </td>
                    </tr> <tr><td><s:hidden name="rowCount6" value="${i-1}" id="rowCount6"/>&nbsp;</td></tr>
                    <tr>
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                            <s:button class="btn" value="Tambah" name="add" onclick="addRow('dataTable6',1)"/>&nbsp;</td>
                    </tr>
                    <tr><td>&nbsp; </td></tr>
                    <tr><td align="center"><s:button name="simpan333" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/></td></tr>
                </table>
            </div>

        </fieldset>
    </div>
</s:form>
