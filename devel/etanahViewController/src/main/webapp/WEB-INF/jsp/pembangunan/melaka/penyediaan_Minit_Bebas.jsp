<%--
    Document   : penyediaan_Minit_Bebas
    Created on : Jul 12, 2010, 9:55:07 AM
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
        if(rowcount < 8){
            var row = table.insertRow(rowcount);
            var cell0 = row.insertCell(0);
            cell0.innerHTML = "<b>" +(rowcount+1)+".</b>";
            var cell1 = row.insertCell(1);
            var el = document.createElement('textarea');
            el.name = 'penyediaan' + (rowcount+1);
            el.rows = 5;
            el.cols = 150;
            el.id = 'penyediaan' + (rowcount+1);
    <%--el.value='latarbelakang' + (rowcount+1);--%>
                        cell1.appendChild(el);
                        document.getElementById("rowCount").value=rowcount+1;
                    } else{
                        alert('Huraian Pentadbir Tanah telah lengkap.');
                        return true;
                    }
                }

                function deleteRow()
                {
                    var j = document.getElementById('rowCount').value;        
                    if(j <= 1 ){
                        alert("Tiada Boleh Dihapuskan");
                        return false;
                    }
                    var x= document.getElementById('dataTable').rows[j-1].cells;
                    var idKandungan = x[0].innerHTML;
                    document.getElementById('dataTable').deleteRow(j-1);
                    var rc1 = $('#rowCount').val();
                    var c1 = $('#count').val();
    <%--alert("rc3:"+rc1);--%>
    <%--alert("c3:"+c1);--%>
                    if(rc1 == c1){            
                        var url = '${pageContext.request.contextPath}/pembangunan/melaka/penyediaanMinitBebas?deleteSingle&idKandungan='+idKandungan;
                        $.get(url,
                        function(data){
                            $('#page_div').html(data);
                        },'html');
                    }      
                    document.getElementById('rowCount').value= j -1;

                }


</script>

<s:form beanclass="etanah.view.stripes.pembangunan.PenyediaanMinitBebasActionBean">
    <s:messages/>
    <s:errors/>
    <s:hidden name="kertasK.kertas.idKertas"/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Minit Bebas</legend>
            <div class="content" align="center">
                <table border="0" width="80%">
                    <tr>
                        <td align="center"> <b>${actionBean.permohonan.kodUrusan.nama}</b></td>
                    </tr>
                    <c:if test="${edit}">
                        <tr><td>
                                <table id ="dataTable" cellspacing="10">
                                    <c:set var="i" value="1" />
                                    <c:forEach items="${actionBean.senaraiKandungan}" var="line">
                                        <tr><td style="display:none">${line.idKandungan}</td>
                                            <td><b><c:out value="${i}"/>.</b></td>
                                            <td><s:textarea name="penyediaan${i}" id="penyediaan${i}"  rows="5" cols="150" class="normal_text">${line.kandungan}</s:textarea></td>
                                            </tr>
                                        <c:set var="i" value="${i+1}" />
                                    </c:forEach>
                                </table>
                            </td>
                        </tr>


                        <tr><td><s:hidden name="rowCount"  id="rowCount"/>
                                <s:hidden name="count" id="count" value="${fn:length(actionBean.senaraiKandungan)}"/>
                            </td> </tr>
                            <%--<c:if test="${actionBean.btn}">--%>
                        <tr>
                            <td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <s:button class="btn" value="Tambah" name="add" onclick="addRow('dataTable')"/>&nbsp;
                                <s:button name="hapus" value="Hapus" class="btn" onclick="deleteRow()" />
                            </td>
                        </tr>
                        <%--</c:if>--%>
                    </c:if>
                        
                    <c:if test="${!edit}">
                        <tr><td>
                                <table border="0" cellspacing="10">
                                    <c:set var="i" value="1" />
                                    <c:forEach items="${actionBean.senaraiKandungan}" var="line">
                                        <tr><td valign="top"><b>${i}.</b></td>
                                            <td>${line.kandungan} </td>
                                        </tr>
                                        <c:set var="i" value="${i+1}" />
                                    </c:forEach>
                                </table>
                            </td>
                        </tr>
                    </c:if>
                    <tr>
                        <td><br></td>
                    </tr>

                </table>
                <c:if test="${edit}">
                    <br>
                    <p align="center">
                        <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </p>
                </c:if>
            </div>
        </fieldset>
    </div>
</s:form>