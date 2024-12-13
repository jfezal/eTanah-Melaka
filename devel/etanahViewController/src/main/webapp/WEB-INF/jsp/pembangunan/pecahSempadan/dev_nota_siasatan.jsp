<%-- 
    Document   : dev_nota_siasatan
    Created on : May 31, 2011, 12:52:37 PM
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

    function addRow1(tableid){
        var table = document.getElementById(tableid);
        var rowcount = table.rows.length;
        var row = table.insertRow(rowcount);
        var cell0 = row.insertCell(0);
        cell0.innerHTML = "<b> 1." +(rowcount+1)+"</b>";
        var leftcell = row.insertCell(1);
        var el = document.createElement('textarea');
        el.name = 'kehadiran' + (rowcount+1);
        el.rows = 5;
        el.cols = 150;
        leftcell.appendChild(el);
        document.getElementById("rowCount1").value=rowcount+1;
    }

    function addRow2(tableid){
        var table = document.getElementById(tableid);
        var rowcount = table.rows.length;
        var row = table.insertRow(rowcount);
        var cell0 = row.insertCell(0);
        cell0.innerHTML = "<b> 2." +(rowcount+1)+"</b>";
        var leftcell = row.insertCell(1);
        var el = document.createElement('textarea');
        el.name = 'tidakHadir' + (rowcount+1);
        el.rows = 5;
        el.cols = 150;
        leftcell.appendChild(el);
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
        el.name = 'keterangan' + (rowcount+1);
        el.rows = 5;
        el.cols = 150;
        leftcell.appendChild(el);
        document.getElementById("rowCount3").value=rowcount+1;
    }

    function addRow4(tableid){
        var table = document.getElementById(tableid);
        var rowcount = table.rows.length;
        var row = table.insertRow(rowcount);
        var cell0 = row.insertCell(0);
        cell0.innerHTML = "<b> 4." +(rowcount+1)+"</b>";
        var leftcell = row.insertCell(1);
        var el = document.createElement('textarea');
        el.name = 'keadaan' + (rowcount+1);
        el.rows = 5;
        el.cols = 150;
        leftcell.appendChild(el);
        document.getElementById("rowCount4").value=rowcount+1;
    }


    function addRow5(tableid){
        var table = document.getElementById(tableid);
        var rowcount = table.rows.length;
        var row = table.insertRow(rowcount);
        var cell0 = row.insertCell(0);
        cell0.innerHTML = "<b> 5." +(rowcount+1)+"</b>";
        var leftcell = row.insertCell(1);
        var el = document.createElement('textarea');
        el.name = 'keputusan' + (rowcount+1);
        el.rows = 5;
        el.cols = 150;
        leftcell.appendChild(el);
        document.getElementById("rowCount5").value=rowcount+1;
    }

    function deleteRow1()
    {
        var j = document.getElementById('rowCount1').value;
        if(j == 1){
            alert("Tiada Boleh Dihapuskan");
            return false;
        }
        var x= document.getElementById('dataTable1').rows[j-1].cells;
        var idKandungan = x[0].innerHTML;
        document.getElementById('dataTable1').deleteRow(j-1);
        document.getElementById('rowCount1').value= j -1;
        <%--alert(document.getElementById('rowCount1').value);--%>
        var url = '${pageContext.request.contextPath}/pembangunan/notaSiasatan?deleteSingle&idKandungan='+idKandungan;
        <%--alert("url:"+url);--%>
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }


    function deleteRow2()
    {
        var j = document.getElementById('rowCount2').value;
        if(j == 1){
            alert("Tiada Boleh Dihapuskan");
            return false;
        }
        var x= document.getElementById('dataTable2').rows[j-1].cells;
        var idKandungan = x[0].innerHTML;
        document.getElementById('dataTable2').deleteRow(j-1);
        document.getElementById('rowCount2').value= j -1;
        <%--alert(document.getElementById('rowCount2').value);--%>
        var url = '${pageContext.request.contextPath}/pembangunan/notaSiasatan?deleteSingle&idKandungan='+idKandungan;
        <%--alert("url:"+url);--%>
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function deleteRow3()
    {
        var j = document.getElementById('rowCount3').value;
        if(j == 1){
            alert("Tiada Boleh Dihapuskan");
            return false;
        }
        var x= document.getElementById('dataTable3').rows[j-1].cells;
        var idKandungan = x[0].innerHTML;
        document.getElementById('dataTable3').deleteRow(j-1);
        document.getElementById('rowCount3').value= j -1;
        <%--alert(document.getElementById('rowCount3').value);--%>
        var url = '${pageContext.request.contextPath}/pembangunan/notaSiasatan?deleteSingle&idKandungan='+idKandungan;
        <%--alert("url:"+url);--%>
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function deleteRow4()
    {
        var j = document.getElementById('rowCount4').value;
        if(j == 1){
            alert("Tiada Boleh Dihapuskan");
            return false;
        }
        var x= document.getElementById('dataTable4').rows[j-1].cells;
        var idKandungan = x[0].innerHTML;
        document.getElementById('dataTable4').deleteRow(j-1);
        document.getElementById('rowCount4').value= j -1;
        <%--alert(document.getElementById('rowCount4').value);--%>
        var url = '${pageContext.request.contextPath}/pembangunan/notaSiasatan?deleteSingle&idKandungan='+idKandungan;
        <%--alert("url:"+url);--%>
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function deleteRow5()
    {
        var j = document.getElementById('rowCount5').value;
        if(j == 1){
            alert("Tiada Boleh Dihapuskan");
            return false;
        }
        var x= document.getElementById('dataTable5').rows[j-1].cells;
        var idKandungan = x[0].innerHTML;
        document.getElementById('dataTable5').deleteRow(j-1);
        document.getElementById('rowCount5').value= j -1;
        <%--alert(document.getElementById('rowCount5').value);--%>
        var url = '${pageContext.request.contextPath}/pembangunan/notaSiasatan?deleteSingle&idKandungan='+idKandungan;
        <%--alert("url:"+url);--%>
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    
</script>

<s:form beanclass="etanah.view.stripes.pembangunan.NotaSiasatanActionBean">
    <s:hidden name="kandunganK.kertas.idKertas"/>
    <s:errors/>
    <s:messages/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend></legend>
            <div class="content" align="center">
                <table border="0" width="80%">
                    <tr><td align="center"><b> NOTA SIASATAN </b></td></tr>
                    <tr><td align="center"><b>${actionBean.idPermohonan}</b></td></tr>
                    <tr><td><s:textarea name="persidangan" id="persidangan"  rows="5" cols="150" class="normal_text"></s:textarea></td></tr>
                </table>
                <table border="0" width="80%" cellspacing="10">
                    <c:if test="${edit}">
                    <tr><td>
                            <table border="0" width="80%" cellspacing="5">                                
                                <tr><td id="idLabel"><b>TARIKH  </b></td>
                                    <td><b>:</b></td>
                                    <td><s:text name="tarikhMesyuarat" id="datepicker" class="datepicker" size="12" /></td>
                                </tr>
                                <tr><td id="idLabel"><b>MASA </b></td>
                                    <td><b>:</b></td>
                                    <td><s:text name="masasidang" size="20"/> <font color="red" size="1"><b>(HH:MM  AM/PM)</b></font></td>
                                </tr>                                
                                <tr><td id="idLabel"><b>TEMPAT  </b></td>
                                    <td><b>:</b></td>
                                    <td><s:textarea name="tempatsidang" cols="50"/></td>
                                </tr>
                            </table>
                        </td></tr>
                    </c:if>
                    <c:if test="${!edit}">
                    <tr><td>
                            <table border="0" width="80%" cellspacing="5">                                
                                <tr>                                    
                                    <td colspan="3">${actionBean.persidangan}</td>
                                </tr>
                                <tr><td id="idLabel"><b>TARIKH  </b></td>
                                    <td><b>:</b></td>
                                    <td>${actionBean.tarikhMesyuarat}</td>
                                </tr>
                                <tr><td id="idLabel"><b>MASA  </b></td>
                                    <td><b>:</b></td>
                                    <td>${actionBean.masasidang}</td>
                                </tr>                                
                                <tr><td id="idLabel"><b>TEMPAT  </b></td>
                                    <td><b>:</b></td>
                                    <td>${actionBean.tempatsidang} </td>
                                </tr>
                            </table>
                        </td></tr>
                    </c:if>

                    <tr><td><b>1. KEHADIRAN</b></td></tr>
                    <c:if test="${edit}">
                            <tr><td>
                                    <table id ="dataTable1">
                                        <tr>
                                            <td><b>1.1 </b></td>
                                            <td><s:textarea name="kehadiran1" id="kehadiran1"  rows="5" cols="150" class="normal_text"></s:textarea></td>
                                        </tr>
                                        <c:set var="i" value="2" />
                                        <c:forEach items="${actionBean.senaraiKandungan1}" var="line" begin="1">
                                            <tr><td style="display:none">${line.idKandungan}</td>
                                                <td><b>1.${i}</b></td>
                                                <td><s:textarea name="kehadiran${i}" id="kehadiran${i}"  rows="5" cols="150" class="normal_text">${line.kandungan}</s:textarea></td>
                                            </tr>
                                            <c:set var="i" value="${i+1}" />
                                        </c:forEach>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td><s:hidden name="rowCount1"  id="rowCount1"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <s:button class="btn" value="Tambah" name="add" onclick="addRow1('dataTable1')"/>&nbsp;
                                    <s:button name="hapus" value="Hapus" class="btn" onclick="deleteRow1()" /></td>
                            </tr>
                        </c:if>
                        <c:if test="${!edit}">
                            <tr><td>
                                    <table cellspacing="10">
                                        <c:set var="i" value="1" />
                                        <c:forEach items="${actionBean.senaraiKandungan1}" var="line">
                                            <tr><td valign="top"><b> 1.${i} </b></td>
                                                <td>${line.kandungan} </td>
                                            </tr>
                                            <c:set var="i" value="${i+1}" />
                                        </c:forEach>
                                    </table>
                                </td>
                            </tr>
                        </c:if>

                   <tr><td><b>2. TIDAK HADIR</b></td></tr>
                    <c:if test="${edit}">
                            <tr><td>
                                    <table id="dataTable2">
                                        <tr>
                                            <td><b>2.1 </b></td>
                                            <td><s:textarea name="tidakHadir1" id="tidakHadir1"  rows="5" cols="150" class="normal_text"></s:textarea></td>
                                        </tr>
                                        <c:set var="i" value="2" />
                                        <c:forEach items="${actionBean.senaraiKandungan2}" var="line" begin="1">
                                            <tr><td style="display:none">${line.idKandungan}</td>
                                                <td><b>2.${i}</b></td>
                                                <td><s:textarea name="tidakHadir${i}" id="tidakHadir${i}"  rows="5" cols="150" class="normal_text">${line.kandungan}</s:textarea></td>
                                            </tr>
                                            <c:set var="i" value="${i+1}" />
                                        </c:forEach>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td><s:hidden name="rowCount2"  id="rowCount2"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <s:button class="btn" value="Tambah" name="add" onclick="addRow2('dataTable2')"/>&nbsp;
                                    <s:button name="hapus" value="Hapus" class="btn" onclick="deleteRow2()" /></td>
                            </tr>
                        </c:if>
                        <c:if test="${!edit}">
                            <tr><td>
                                    <table cellspacing="10">
                                        <c:set var="i" value="1" />
                                        <c:forEach items="${actionBean.senaraiKandungan2}" var="line">
                                            <tr><td valign="top"><b> 2.${i} </b></td>
                                                <td>${line.kandungan} </td>
                                            </tr>
                                            <c:set var="i" value="${i+1}" />
                                        </c:forEach>
                                    </table>
                                </td>
                            </tr>
                        </c:if>
                            
                    <tr><td><b>3. KETERANGAN TUAN TANAH</b></td></tr>
                    <c:if test="${edit}">
                            <tr><td>
                                    <table id="dataTable3">
                                        <tr>
                                            <td><b>3.1 </b></td>
                                            <td><s:textarea name="keterangan1" id="keterangan1"  rows="5" cols="150" class="normal_text"></s:textarea></td>
                                        </tr>
                                        <c:set var="i" value="2" />
                                        <c:forEach items="${actionBean.senaraiKandungan3}" var="line" begin="1">
                                            <tr><td style="display:none">${line.idKandungan}</td>
                                                <td><b>3.${i}</b></td>
                                                <td><s:textarea name="keterangan${i}" id="keterangan${i}"  rows="5" cols="150" class="normal_text">${line.kandungan}</s:textarea></td>
                                            </tr>
                                            <c:set var="i" value="${i+1}" />
                                        </c:forEach>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td><s:hidden name="rowCount3"  id="rowCount3"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <s:button class="btn" value="Tambah" name="add" onclick="addRow3('dataTable3')"/>&nbsp;
                                    <s:button name="hapus" value="Hapus" class="btn" onclick="deleteRow3()" /></td>
                            </tr>
                        </c:if>
                        <c:if test="${!edit}">
                            <tr><td>
                                    <table cellspacing="10">
                                        <c:set var="i" value="1" />
                                        <c:forEach items="${actionBean.senaraiKandungan3}" var="line">
                                            <tr><td valign="top"><b> 3.${i} </b></td>
                                                <td>${line.kandungan} </td>
                                            </tr>
                                            <c:set var="i" value="${i+1}" />
                                        </c:forEach>
                                    </table>
                                </td>
                            </tr>
                        </c:if>

                    <tr><td><b>4. KEADAAN ATAS TANAH</b></td></tr>
                    <c:if test="${edit}">
                            <tr><td>
                                    <table id="dataTable4">
                                        <tr>
                                            <td><b>4.1 </b></td>
                                            <td><s:textarea name="keadaan1" id="keadaan1"  rows="5" cols="150" class="normal_text"></s:textarea></td>
                                        </tr>
                                        <c:set var="i" value="2" />
                                        <c:forEach items="${actionBean.senaraiKandungan4}" var="line" begin="1">
                                            <tr><td style="display:none">${line.idKandungan}</td>
                                                <td><b>4.${i}</b></td>
                                                <td><s:textarea name="keadaan${i}" id="keadaan${i}"  rows="5" cols="150" class="normal_text">${line.kandungan}</s:textarea></td>
                                            </tr>
                                            <c:set var="i" value="${i+1}" />
                                        </c:forEach>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td><s:hidden name="rowCount4"  id="rowCount4"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <s:button class="btn" value="Tambah" name="add" onclick="addRow4('dataTable4')"/>&nbsp;
                                    <s:button name="hapus" value="Hapus" class="btn" onclick="deleteRow4()" /></td>
                            </tr>
                        </c:if>
                        <c:if test="${!edit}">
                            <tr><td>
                                    <table cellspacing="10">
                                        <c:set var="i" value="1" />
                                        <c:forEach items="${actionBean.senaraiKandungan4}" var="line">
                                            <tr><td valign="top"><b> 4.${i} </b></td>
                                                <td>${line.kandungan} </td>
                                            </tr>
                                            <c:set var="i" value="${i+1}" />
                                        </c:forEach>
                                    </table>
                                </td>
                            </tr>
                        </c:if>


                    <tr><td><b>5. KEPUTUSAN SIASATAN</b></td></tr>
                    <c:if test="${edit}">
                            <tr><td>
                                    <table id="dataTable5">
                                        <tr>
                                            <td><b>5.1 </b></td>
                                            <td><s:textarea name="keputusan1" id="keputusan1"  rows="5" cols="150" class="normal_text"></s:textarea></td>
                                        </tr>
                                        <c:set var="i" value="2" />
                                        <c:forEach items="${actionBean.senaraiKandungan5}" var="line" begin="1">
                                            <tr><td style="display:none">${line.idKandungan}</td>
                                                <td><b>5.${i}</b></td>
                                                <td><s:textarea name="keputusan${i}" id="keputusan${i}"  rows="5" cols="150" class="normal_text">${line.kandungan}</s:textarea></td>
                                            </tr>
                                            <c:set var="i" value="${i+1}" />
                                        </c:forEach>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td><s:hidden name="rowCount5"  id="rowCount5"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <s:button class="btn" value="Tambah" name="add" onclick="addRow5('dataTable5')"/>&nbsp;
                                    <s:button name="hapus" value="Hapus" class="btn" onclick="deleteRow5()" /></td>
                            </tr>
                        </c:if>
                        <c:if test="${!edit}">
                            <tr><td>
                                    <table cellspacing="10">
                                        <c:set var="i" value="1" />
                                        <c:forEach items="${actionBean.senaraiKandungan5}" var="line">
                                            <tr><td valign="top"><b> 5.${i} </b></td>
                                                <td>${line.kandungan} </td>
                                            </tr>
                                            <c:set var="i" value="${i+1}" />
                                        </c:forEach>
                                    </table>
                                </td>
                            </tr>
                        </c:if>

                       
              </table>
                 <c:if test="${edit}">
                            <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                        </c:if>
            </div>
        </fieldset>
    </div>
</s:form>
