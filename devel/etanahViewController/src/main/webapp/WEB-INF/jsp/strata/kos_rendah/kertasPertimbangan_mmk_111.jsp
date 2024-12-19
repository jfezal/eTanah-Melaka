<%--
    Document   : kertasPertimbangan_mmk
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

        // alert(tableID);
     
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

                    <%--document.getElementById("rowCount"+tableNo).value=rowCount+1;
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
                    <%--e1.t = "button"+(rowCount+1);--%>
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

    <%--alert("tableNo:"+tableNo);
    alert("elementId:"+elementId);
    alert("count:"+document.getElementById("rowCount"+tableNo).value);--%>

            var x= document.getElementById(tableID).rows[elementId-1].cells;
            var idKandungan = x[0].innerHTML;
            // alert("idKandungan:"+idKandungan);
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
                <table border="0" width="80%">

                    <tr><td align="center"><b>(MAJLIS MESYUARAT KERAJAAN 
<!--                                PADA <font color="red">*</font> <s:text name="tarikhMesyuarat" id="datepicker" class="datepicker" size="12" /> -->
                                )</b></td></tr>

                    <tr><td><b><font style="text-transform: uppercase"><center>${actionBean.tajuk}</center></font></b></td></tr>

                    <tr><td>&nbsp;</td></tr>
                    <tr><td>&nbsp;</td></tr>
                    <tr><td><b>1. TUJUAN</b></td></tr>
                    <tr><td>&nbsp;</td></tr>
                    <tr><td>
                            <c:if test="${actionBean.senaraiLaporanKandungan1 eq null}">
                                <table>
                                    <tr>
                                        <td valign="top"><b>1.1 </b></td>
                                        <td><font style="text-transform:uppercase">${actionBean.tujuan}</font></td>
                                    </tr>
                                </table>
                            </c:if>
                        </td>
                    </tr>
                    <tr><td>&nbsp;</td></tr>
                    <tr><td>
                            <table id ="dataTable1">
                                <c:set var="i" value="1" />
                                <c:if test="${actionBean.senaraiLaporanKandungan1 eq null}"><c:set var="k" value="2" /></c:if>
                                <c:if test="${actionBean.senaraiLaporanKandungan1 ne null}"><c:set var="k" value="1" /></c:if>
                                <c:set var="index" value="2" />
                                <c:forEach items="${actionBean.senaraiLaporanKandungan1}" var="line">
                                    <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="1.${k}"/></td>
                                        <td><font style="text-transform: uppercase"><s:textarea name="kandungan1${i}" id="kandungan1${i}"  rows="5" cols="100" onkeyup="this.value=this.value.toUpperCase();">${line.kandungan}</s:textarea> </font>
                                        </td></tr>
                                        <c:set var="i" value="${i+1}" />
                                        <c:set var="k" value="${k+1}" />
                                    </c:forEach>

                            </table>
                        </td>
                    </tr>
                    <tr><td><s:hidden name="rowCount1" value="${i-1}" id="rowCount1"/>&nbsp;</td></tr>
                    <tr>
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <s:button class="btn" value="Tambah" name="add" onclick="addRow('dataTable1',1)"/>&nbsp;</td>
                    </tr><td>&nbsp;</td>
                    <tr><td><b>2. LATAR BELAKANG</b></td></tr>

                    <tr><td>&nbsp;</td></tr>
                    <c:if test="${actionBean.senaraiLaporanKandungan2 eq null}">
                        <tr>
                            <td>

                                <table>
                                    <tr><td valign="top"><b>2.1</b></td>
                                        <td><font style="text-transform:uppercase"> ${actionBean.latarbelakang1}</font></td>
                                    </tr>
                                    <tr><td>&nbsp;</td></tr>
                                    <tr><td>&nbsp;</td></tr>
                                    <tr><td valign="top"><b>2.2</b></td>
                                        <td><font style="text-transform:uppercase">
                                                ${actionBean.latarbelakang2}</font></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </c:if>
                    <tr><td>
                            <table id ="dataTable2" border="0">
                                <c:set var="i" value="1" />
                                <c:if test="${actionBean.senaraiLaporanKandungan2 eq null}"><c:set var="k" value="3" /></c:if>
                                <c:if test="${actionBean.senaraiLaporanKandungan2 ne null}"><c:set var="k" value="1" /></c:if>
                                <c:forEach items="${actionBean.senaraiLaporanKandungan2}" var="line">
                                    <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="2.${k}"/></td>
                                        <td><font style="text-transform: uppercase"><s:textarea name="kandungan2${i}" id="kandungan2${i}"  rows="5" cols="100" onkeyup="this.value=this.value.toUpperCase();">${line.kandungan}</s:textarea> <s:button class="btn" value="Hapus" name="delete" id="${i}" onclick="deleteRow('dataTable2',this.id)"/></font>
                                        </td></tr>
                                        <c:set var="i" value="${i+1}" />
                                        <c:set var="k" value="${k+1}" />
                                    </c:forEach>
                            </table>
                        </td>
                    </tr>
                    <tr><td> <s:hidden name="rowCount2" value="${i-1}" id="rowCount2"/>&nbsp;</td></tr>
                    <tr>
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                            <s:button class="btn" value="Tambah" name="add" onclick="addRow('dataTable2',2)"/>&nbsp;</td>

                    </tr><td>&nbsp;</td>
                    <tr><td><b>3. ASAS PERMOHONAN</b></td></tr>

                    <tr><td>&nbsp;</td></tr>
                    <c:if test="${actionBean.senaraiLaporanKandungan3 eq null}">
                        <tr>
                            <td>
                                <table>
                                    <tr><td valign="top"><b>3.1</b></td>
                                        <td><font style="text-transform:uppercase">${actionBean.asaspermohon}</font></td>
                                    </tr>
                                    <tr><td>&nbsp;</td></tr>
                                    <tr><td>&nbsp;</td></tr>
                                    <tr><td valign="top"><b>3.2</b></td>
                                        <td><font style="text-transform:uppercase">${actionBean.asaspermohon2}</font></td>
                                    </tr>
                                    <tr><td>&nbsp;</td></tr>
                                    <tr><td>&nbsp;</td></tr>
                                    <tr><td valign="top"><b>3.3</b></td>
                                        <td><font style="text-transform:uppercase">${actionBean.asaspermohon3}</font></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </c:if>
                    <tr><td>
                            <table id ="dataTable3">
                                <c:set var="i" value="1" />
                                <c:if test="${actionBean.senaraiLaporanKandungan3 eq null}"><c:set var="k" value="4" /></c:if>
                                <c:if test="${actionBean.senaraiLaporanKandungan3 ne null}"><c:set var="k" value="1" /></c:if>
                                <c:forEach items="${actionBean.senaraiLaporanKandungan3}" var="line">
                                    <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="3.${k}"/></td>
                                        <td><font style="text-transform: uppercase"><s:textarea name="kandungan3${i}" id="kandungan3${i}"  rows="5" cols="100" onkeyup="this.value=this.value.toUpperCase();">${line.kandungan}</s:textarea> <s:button class="btn" value="Hapus" name="delete" id="${i}" onclick="deleteRow('dataTable3',this.id)"/></font>
                                        </td></tr>
                                        <c:set var="i" value="${i+1}" />
                                        <c:set var="k" value="${k+1}" />
                                    </c:forEach>

                            </table>
                        </td>
                    </tr>  <tr><td><s:hidden name="rowCount3" value="${i-1}" id="rowCount3"/> &nbsp;</td></tr>

                    <tr>
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                            <s:button class="btn" value="Tambah" name="add" onclick="addRow('dataTable3',3)"/>&nbsp;</td>

                    </tr><td>&nbsp;</td>

                    <tr><td>&nbsp; </td></tr>
                    <tr><td align="center"><s:button name="simpan111" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/></td></tr>
                </table>
            </div>

        </fieldset>
    </div>
</s:form>
