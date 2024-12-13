<%-- 
    Document   : surat_kpsn_5a
    Created on : Feb 12, 2014, 11:52:07 AM
    Author     : khairul.hazwan
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
        cell0.innerHTML = "<b> 2." +(rowcount+1)+"</b>";
        var cell1 = row.insertCell(1);
        var el = document.createElement('textarea');
        el.name = 'perihal2' + (rowcount+1);
        el.rows = 5;
        el.cols = 150;
        el.id = 'perihal2' + (rowcount+1);
        cell1.appendChild(el);
        document.getElementById("rowCount1").value=rowcount+1;
    }

    function addRow2(tableid){
        var table = document.getElementById(tableid);
        var rowcount = table.rows.length;
        var row = table.insertRow(rowcount);
        var cell0 = row.insertCell(0);
        cell0.innerHTML = "<b> 3." +(rowcount+1)+"</b>";
        var cell1 = row.insertCell(1);
        var el = document.createElement('textarea');
        el.name = 'perihal3' + (rowcount+1);
        el.rows = 5;
        el.cols = 150;
        cell1.appendChild(el);
        document.getElementById("rowCount2").value=rowcount+1;
    }

    function addRow3(tableid){
        var table = document.getElementById(tableid);
        var rowcount = table.rows.length;
        var row = table.insertRow(rowcount);
        var cell0 = row.insertCell(0);
        cell0.innerHTML = "<b> 4." +(rowcount+1)+"</b>";
        var cell1 = row.insertCell(1);
        var el = document.createElement('textarea');
        el.name = 'perihal4' + (rowcount+1);
        el.rows = 20;
        el.cols = 150;
        cell1.appendChild(el);
        document.getElementById("rowCount3").value=rowcount+1;
    }


    function addRow4(tableid){
        var table = document.getElementById(tableid);
        var rowcount = table.rows.length;
        var row = table.insertRow(rowcount);
        var cell0 = row.insertCell(0);
        cell0.innerHTML = "<b>5." +(rowcount+1)+"</b>";
        var cell1 = row.insertCell(1);
        var el = document.createElement('textarea');
        el.name = 'perihal4' + (rowcount+1);
        el.rows = 5;
        el.cols = 150;
        cell1.appendChild(el);
        document.getElementById("rowCount4").value=rowcount+1;
    }
    
    
    function addRow5(tableid){
        var table = document.getElementById(tableid);
        var rowcount = table.rows.length;
        var row = table.insertRow(rowcount);
        var cell0 = row.insertCell(0);
        cell0.innerHTML = "<b>6." +(rowcount+1)+"</b>";
        var cell1 = row.insertCell(1);
        var el = document.createElement('textarea');
        el.name = 'perihal6' + (rowcount+1);
        el.rows = 5;
        el.cols = 150;
        cell1.appendChild(el);
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
        var rc1 = $('#rowCount1').val();
        var c1 = $('#count1').val();
    <%--alert("rc1:"+rc1);
    alert("c1:"+c1);--%>
            if(rc1 == c1){
                var url = '${pageContext.request.contextPath}/pembangunan/melaka/srtKpsn5A?deleteSingle&idKandungan='
                    +idKandungan;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
            }
            document.getElementById('rowCount1').value= j -1;
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
            var rc2 = $('#rowCount2').val();
            var c2 = $('#count2').val();
    <%--alert("rc1:"+rc2);
    alert("c1:"+c2);--%>
            if(rc2 == c2){
                var url = '${pageContext.request.contextPath}/pembangunan/melaka/srtKpsn5A?deleteSingle&idKandungan='
                    +idKandungan;
    <%--alert(url);--%>
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
            }
            document.getElementById('rowCount2').value= j -1;
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
            var rc3 = $('#rowCount3').val();
            var c3 = $('#count3').val();
    <%--alert("rc3:"+rc3);
    alert("c3:"+c3);--%>
            if(rc3 == c3){
                var url = '${pageContext.request.contextPath}/pembangunan/melaka/srtKpsn5A?deleteSingle&idKandungan='
                    +idKandungan;
    <%--alert("url:"+url);--%>
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
            }
            document.getElementById('rowCount3').value= j -1;
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
            var rc4 = $('#rowCount4').val();
            var c4 = $('#count4').val();
    <%--alert("rc4:"+rc4);
    alert("c4:"+c4);--%>
            if(rc4 == c4){
                var url = '${pageContext.request.contextPath}/pembangunan/melaka/srtKpsn5A?deleteSingle&idKandungan='
                    +idKandungan;
    <%--alert("url:"+url);--%>
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
            }
            document.getElementById('rowCount4').value= j -1;
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
            var rc5 = $('#rowCount5').val();
            var c5 = $('#count5').val();
    <%--alert("rc4:"+rc4);
    alert("c4:"+c4);--%>
            if(rc5 == c5){
                var url = '${pageContext.request.contextPath}/pembangunan/melaka/srtKpsn5A?deleteSingle&idKandungan='
                    +idKandungan;
    <%--alert("url:"+url);--%>
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
            }
            document.getElementById('rowCount5').value= j -1;
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

<s:form beanclass="etanah.view.stripes.pembangunan.SuratKpsn5aActionBean">
    <s:hidden name="kertasK.kertas.idKertas"/>
    <s:hidden name="btn"/>

    <s:errors/>
    <s:messages/>

    <div class="subtitle">
        <fieldset class="aras1">
            
            <div class="content" align="center">
                <table border="0" width="80%">
                    <tr><td align="center"><b>SURAT KEPUTUSAN 5A</b></td></tr>
                </table> 

                <table border="0" width="80%" cellspacing="10">        
                   
                    <c:if test="${edit}">
                        <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:textarea name="tajuk" rows="5" cols="150" class="normal_text"/></td></tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td><b>${actionBean.tajuk} &nbsp;</b></td></tr>
                    </c:if>


                    <br></br>
                    <tr><td>Dengan hormatnya saya diarah merujuk kepada perkara di atas.</td></tr>
                    <br></br>

                    <!--no.2-->
                    <c:if test="${edit}">
                        <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:textarea name="perihal1" rows="5" cols="150" class="normal_text"/></td></tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td>${actionBean.perihal1}</td></tr>
                    </c:if>


                    <!--no.3-->
                    <c:if test="${edit}">
                        <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:textarea name="perihal2" rows="5" cols="150" class="normal_text"/></td></tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td>${actionBean.perihal2}</td></tr>
                    </c:if>


                    <!--no.4-->                  
                    <c:if test="${edit}">
                        <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:textarea name="perihal3" rows="5" cols="150" class="normal_text"/></td></tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td>${actionBean.perihal3}</td></tr>
                    </c:if>


                    <!--no.5-->    
                    <tr><td>5. SYARAT-SYARAT AM</td></tr>
                    <c:if test="${edit}">                      
                        <tr><td>
                                <table id ="dataTable4">                                    
                                    <c:set var="i" value="1" />
                                    <c:forEach items="${actionBean.senaraiKandungan4}" var="line">
                                        <tr><td style="display:none">${line.idKandungan}</td>                                           
                                            <td><b><c:out value="5.${i}"/></b></td>                                                                                
                                            <td><s:textarea name="perihal4${i}" id="perihal4${i}"  rows="5" cols="150" class="normal_text">${line.kandungan}</s:textarea></td>
                                        </tr>
                                        <c:set var="i" value="${i+1}" />
                                    </c:forEach>
                                </table>
                            </td>
                        </tr>                        
                    </c:if>
                    <tr><td><s:hidden name="rowCount4" id="rowCount4"/>
                            <s:hidden name="count4" id="count4" value="${fn:length(actionBean.senaraiKandungan4)}"/>
                        </td></tr>
                    <c:if test="${edit}">
                        <c:if test="${actionBean.btn}">
                        <tr>
                            <td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <s:button class="btn" value="Tambah" name="add" onclick="addRow4('dataTable4')"/>&nbsp;
                                <s:button name="hapus" value="Hapus" class="btn" onclick="deleteRow4()" /></td>
                        </tr>
                        </c:if>                      
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td>
                                <table>
                                    <c:set var="i" value="1" />
                                    <c:forEach items="${actionBean.senaraiKandungan4}" var="line">
                                        <tr>                                          
                                            <td>&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="5.${i}"/></td>                                         
                                            <td>&nbsp;&nbsp; ${line.kandungan} </td>
                                        </tr>
                                        <c:set var="i" value="${i+1}" />
                                    </c:forEach>
                                </table>
                            </td>
                        </tr>
                    </c:if>                    


                    <!--no.6-->    
                    <c:if test="${edit}">
                        <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:textarea name="perihal5" rows="5" cols="150" class="normal_text"/></td></tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td>${actionBean.perihal5}</td></tr>
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