<%-- 
    Document   : surat_keputusan_JKBB
    Created on : Sep 25, 2012, 6:29:55 PM
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
        var row = table.insertRow(rowcount);
        <%--var cell0 = row.insertCell(0);
        cell0.innerHTML = "<b>" +(rowcount+1)+".</b>";--%>
        var cell1 = row.insertCell(0);
        var el = document.createElement('textarea');
        el.name = 'penyediaan' + (rowcount+1);
        el.rows = 5;
        el.cols = 150;
        el.id = 'penyediaan' + (rowcount+1);
        <%--el.value='latarbelakang' + (rowcount+1);--%>
        cell1.appendChild(el);
        document.getElementById("rowCount").value=rowcount+1;
    }

    function deleteRow()
    {
        var j = document.getElementById('rowCount').value;
        <%--alert(j);--%>
        if(j < 1 ){
            alert("Tiada Boleh Dihapuskan");
            return false;
        }
        var x= document.getElementById('dataTable').rows[j-1].cells;
        var idKandungan = x[0].innerHTML;
        <%--alert(idKandungan);--%>
        document.getElementById('dataTable').deleteRow(j-1);
        var rc1 = $('#rowCount').val();
        var c1 = $('#count').val();
        if(rc1 == c1){
            var url = '${pageContext.request.contextPath}/pembangunan/melaka/surat_keputusan_JKBB?deleteSingle&idKandungan='+idKandungan;
            <%--alert("url:"+url);--%>
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
        document.getElementById('rowCount').value= j -1;
    }


</script>

<s:form beanclass="etanah.view.stripes.pembangunan.SuratKeputusanJKBBActionBean">
    <s:messages/>
    <s:errors/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Surat Keputusan JKBB</legend>
            <div class="content" align="center">
                
                <table border="0" width="80%">  
                    <tr><td><b>TAJUK<b></td></tr>
                    <td><s:textarea name="tajuk" id="tajuk"  rows="5" cols="150" class="normal_text"></s:textarea></td>
                    
                    <tr><td>
                            <br>
                            <tr><td><b>TINDAKAN<b></td></tr>
                            <table id ="dataTable" cellspacing="10">
                                <c:set var="i" value="1" />
                                <c:forEach items="${actionBean.senaraiKandungan}" var="line">
                                    <tr>
                                        <td style="display:none">${line.idKandungan}</td>
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
                        <tr>
                            <td>
                                <br>
                                <div align ="left">
                                    <tr><td>
                                        <s:button class="btn" value="Tambah" name="add" onclick="addRow('dataTable')"/>&nbsp;
                                        <s:button name="hapus" value="Hapus" class="btn" onclick="deleteRow()" />
                                    </td></tr>
                                </div>
                            </td>
                        </tr>
            </table>

                        
                        <p align="center">
                            <br>
                            <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                        </p>

            </div>
        </fieldset>
    </div>
</s:form>