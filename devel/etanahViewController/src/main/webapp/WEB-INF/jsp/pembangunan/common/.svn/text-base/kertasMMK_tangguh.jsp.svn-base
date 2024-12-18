<%--
    Document   : KertasMMK_tangguh
    Created on : DEC 20, 2011, 12:44:34 PM
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

    function addRow2(tableid){
        var table = document.getElementById(tableid);
        var rowcount = table.rows.length;
        var row = table.insertRow(rowcount);
        var cell0 = row.insertCell(0);
        cell0.innerHTML = "<b> 2." +(rowcount+1)+"</b>";

        var leftcell = row.insertCell(1);
        var el = document.createElement('textarea');
        el.name = 'latarBelakang' + (rowcount+1);
        el.rows = 5;
        el.cols = 150;
        leftcell.appendChild(el);

        // Add Hapus button
        var rightcell = row.insertCell(2);
        var e2 = document.createElement("INPUT");
        e2.setAttribute("type","button");
        e2.className="btn";
        e2.value ="Hapus";
        e2.setAttribute("id", ""+(rowcount));
        e2.onclick=function(){deleteRow2('',(e2.id));};
        rightcell.appendChild(e2);
        document.getElementById("rowCount2").value=rowcount+1;
    }

    function addRow3(tableid){
        var table = document.getElementById(tableid);
        var rowcount = table.rows.length;
        var row = table.insertRow(rowcount);
        var cell0 = row.insertCell(0);
        cell0.innerHTML = "<b> 3." +(rowcount+1)+"</b>";

        var leftcell = row.insertCell(1);
        var el = document.createElement('textarea');
        el.name = 'pengarahTanah' + (rowcount+1);
        el.rows = 5;
        el.cols = 150;
        leftcell.appendChild(el);

        // Add Hapus button
        var rightcell = row.insertCell(2);
        var e2 = document.createElement("INPUT");
        e2.setAttribute("type","button");
        e2.className="btn";
        e2.value ="Hapus";
        e2.setAttribute("id", ""+(rowcount));
        e2.onclick=function(){deleteRow3('',(e2.id));};
        rightcell.appendChild(e2);
        document.getElementById("rowCount3").value=rowcount+1;
    }

    function deleteRow2(idKandungan,index)
    {
        document.getElementById('dataTable2').deleteRow(index);
        if(idKandungan == ''){
            var rowCount = document.getElementById("rowCount2").value;
            document.getElementById("rowCount2").value = rowCount-1;
            regenerateBill2('dataTable2');
        }else{
            var url = '${pageContext.request.contextPath}/pembangunan/kertas_mmk_tangguh?deleteSingle&idKandungan='
                +idKandungan;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
    }


    function deleteRow3(idKandungan,index)
    {
        document.getElementById('dataTable3').deleteRow(index);
        if(idKandungan == ''){
            var rowCount = document.getElementById("rowCount3").value;
            document.getElementById("rowCount3").value = rowCount-1;
            regenerateBill3('dataTable3');
        }else{
            var url = '${pageContext.request.contextPath}/pembangunan/kertas_mmk_tangguh?deleteSingle&idKandungan='
                +idKandungan;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
    }


    function regenerateBill2(tableid){
        try{
            var table = document.getElementById(tableid);
            var rowCount = table.rows.length;
            if(rowCount > 1){
                for(var i=0;i<rowCount;i++){
                    var a = table.rows[i].cells[0];
                    a.innerHTML= "<b>2."+(i+1)+"</b>";
                    if(i>0){
                        table.rows[i].cells[1].childNodes[0].name= 'latarBelakang'+(i+1);
                        table.rows[i].cells[2].childNodes[0].id= i;
                    }
                }
            }
        }catch(e){
            alert("Error in regenerateBill");
        }
    }


    function regenerateBill3(tableid){
        try{
            var table = document.getElementById(tableid);
            var rowCount = table.rows.length;
            if(rowCount > 1){
                for(var i=0;i<rowCount;i++){
                    var a = table.rows[i].cells[0];
                    a.innerHTML= "<b>3."+(i+1)+"</b>";
                    if(i>0){
                        table.rows[i].cells[1].childNodes[0].name= 'pengarahTanah'+(i+1);
                        table.rows[i].cells[2].childNodes[0].id= i;
                    }
                }
            }
        }catch(e){
            alert("Error in regenerateBill");
        }
    }



    function validation(event, f){
        $('#dialog-confirm').dialog('open')
        $(function() {
            $( "#dialog-confirm" ).dialog({
                resizable: false,
                height:140,
                modal: true,
                buttons: {
                    "Tidak": function() {
                        //  alert('tidak selected');
                        $( this ).dialog( "close" );
                        return false;
                    },
                    "Ya": function() {
                        // alert('ya selected');
                        $( this ).dialog( "close" );

                        var q = $(f).formSerialize();
                        var url = f.action + '?' + event;
                        $.post(url,q,
                        function(data){
                            $('#page_div',this.document).html(data);
                        },'html');
                        return true;
                    }
                }
            });
        });

    }

</script>

<s:form beanclass="etanah.view.stripes.pembangunan.KertasMMKTangguhActionBean">
    <s:hidden name="kes:textrtasK.kertas.idKertas"/>
    <s:hidden name="btn"/>
    <s:errors/>
    <s:messages/>    

    <div class="subtitle">
        <fieldset class="aras1">
            <legend></legend>
            <div class="content" align="center">
                <table border="0" width="80%">
                    <c:if test="${edit}">
                        <tr>
                            <td>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${actionBean.idPermohonan}
                            </td>
                        </tr>
                        <tr>
                            <td>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${actionBean.noRujukan}
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr>
                            <td>
                                ${actionBean.idPermohonan}
                            </td>
                        </tr>
                        <tr>
                            <td>
                                ${actionBean.noRujukan}
                            </td>
                        </tr>
                    </c:if>

                    <tr><td>&nbsp;</td></tr>
                    <tr><td>&nbsp;</td></tr>
                    <tr><td align="center"><b>( KERTAS MAKLUMAN MAJLIS MESYUARAT KERAJAAN NEGERI SEMBILAN DARUL KHUSUS PADA ....................<!--<//s:text name="permohonanKertas.tarikhSidang" id="datepicker" class="datepicker" formatPattern="dd/MM/yyyy" />--> )</b></td></tr>
                    <tr><td>&nbsp;</td></tr>
                    <c:if test="${edit}">
                        <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font style="text-transform: uppercase"><s:textarea rows="5" cols="150" name="tajuk"/></font></td></tr>
                            </c:if>
                            <c:if test="${!edit}">
                        <tr><td style="text-align: justify"><b> ${actionBean.tajuk} </b></td></tr>
                    </c:if>
                    <tr><td>&nbsp;</td></tr>
                    <tr><td><b>1. TUJUAN</b></td></tr>
                    <c:if test="${edit}">
                        <tr>
                            <td>
                                <table>
                                    <tr>
                                        <td><b>1.1 </b></td>
                                        <td><s:textarea rows="5" cols="150" name="tujuan" class="normal_text"/></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr>
                            <td>
                                <table>
                                    <tr>
                                        <td valign="top"><b>&nbsp;&nbsp;<b>1.1</b>&nbsp;&nbsp;</b></td>
                                        <td style="text-align: justify">${actionBean.tujuan} </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </c:if>
                    <tr><td>&nbsp;</td></tr>
                    <tr><td><b>2. LATAR BELAKANG</b></td></tr>
                    <c:if test="${edit}">
                        <tr><td>
                                <table id ="dataTable2">
                                    <c:set var="i" value="1" />
                                    <c:forEach items="${actionBean.senaraiKandungan2}" var="line" begin="0">
                                        <tr><td style="display:none">${line.idKandungan}</td>
                                            <td><b>2.${i}</b></td>
                                            <td><s:textarea name="latarBelakang${i}" id="latarBelakang${i}"  rows="5" cols="150" class="normal_text">${line.kandungan}</s:textarea>
                                            </td>
                                            <c:if test="${i>1}">
                                                <td><s:button value="Hapus" id="${i-1}" class="btn" name="hapus" onclick="deleteRow2(${line.idKandungan},${i-1})"></s:button> </td>
                                            </c:if>
                                        </tr>
                                        <c:set var="i" value="${i+1}" />
                                    </c:forEach>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td><s:hidden name="rowCount2"  id="rowCount2"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <s:button class="btn" value="Tambah" name="tambah" onclick="addRow2('dataTable2')"/>&nbsp;
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr>
                            <td>
                                <table>
                                    <c:set var="i" value="1" />
                                    <c:forEach items="${actionBean.senaraiKandungan2}" var="line">
                                        <tr><td valign="top">&nbsp;&nbsp;<b><c:out value="2.${i}"/></b>&nbsp;&nbsp;</td>
                                            <td style="text-align: justify">${line.kandungan}</td>
                                        </tr>
                                        <c:set var="i" value="${i+1}" />
                                    </c:forEach>
                                </table>
                            </td>
                        </tr>
                    </c:if>
                    <tr><td>&nbsp;</td></tr>
                    <tr><td><b>3. MAKLUMAN PENGARAH TANAH DAN GALIAN NEGERI SEMBILAN DARUL KHUSUS</b></td></tr>
                    <c:if test="${edit}">
                        <tr><td>
                                <table id ="dataTable3">
                                    <c:set var="i" value="1" />
                                    <c:forEach items="${actionBean.senaraiKandungan3}" var="line" begin="0">
                                        <tr><td style="display:none">${line.idKandungan}</td>
                                            <td><b>3.${i}</b></td>
                                            <td><s:textarea name="pengarahTanah${i}" id="pengarahTanah${i}"  rows="5" cols="150" class="normal_text">${line.kandungan}</s:textarea></td>
                                            <c:if test="${i>1}">
                                                <td><s:button value="Hapus" id="${i-1}" class="btn" name="hapus" onclick="deleteRow3(${line.idKandungan},${i-1})"></s:button> </td>
                                            </c:if>
                                        </tr>
                                        <c:set var="i" value="${i+1}" />
                                    </c:forEach>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td><s:hidden name="rowCount3"  id="rowCount3"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <s:button class="btn" value="Tambah" name="tambah" onclick="addRow3('dataTable3')"/>&nbsp;
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td>
                                <table>
                                    <c:set var="i" value="1" />
                                    <c:forEach items="${actionBean.senaraiKandungan3}" var="line">
                                        <tr><td valign="top">&nbsp;&nbsp; <b><c:out value="3.${i}"/></b>&nbsp;&nbsp;</td>
                                            <td style="text-align: justify">${line.kandungan}</td>
                                        </tr>
                                        <c:set var="i" value="${i+1}" />
                                    </c:forEach>
                                </table>
                            </td>
                        </tr>
                    </c:if>
                    <tr><td>&nbsp;</td></tr>
                    <tr><td><b><font style="text-transform:uppercase">4. KEPUTUSAN </font></b></td></tr>
                                <c:if test="${edit}">
                        <tr>
                            <td>
                                <table>
                                    <tr>
                                        <td><b>4.1 </b></td>
                                        <td><s:textarea rows="5" cols="150" name="keputusan" class="normal_text"/></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr>
                            <td>
                                <table>
                                    <tr><td valign="top">&nbsp;&nbsp;<b>4.1</b>&nbsp;&nbsp;</td><td style="text-align: justify">${actionBean.keputusan} </td> </tr>
                                </table>    
                            </td>
                        </tr>
                    </c:if>
                </table>
                <c:if test="${edit}">
                    <p align="center">                        
                        <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </p>
                </c:if>
            </div>
        </fieldset>
    </div>
</s:form>