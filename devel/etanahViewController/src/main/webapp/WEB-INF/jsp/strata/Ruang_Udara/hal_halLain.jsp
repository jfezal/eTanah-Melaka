<%--
    Document   : hal_halLain
    Created on : Sep 20, 2010, 4:29:43 PM
    Author     : Murali
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    function addRow3(event, f){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.get(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
                    self.close();
        },'html');
    }

    function regenerateBill(tableid){
        try{
            var table = document.getElementById(tableid);
            var rowCount = table.rows.length;
            for(var i=1;i<rowCount;i++){               
                table.rows[i].cells[2].childNodes[0].id= i+1;
            }

        }catch(e){
            alert("Error in regenerateBill");
        }
    }
    function deleteRow3(recordNo,idKandungan)
    {
        if(confirm('Adakah anda pasti untuk menghapus data ini?'))
        {

            var i = document.getElementById("rowCount").value;
            var x= document.getElementById('dataTable1').rows[i-1].cells;

            if(i==1){
                document.getElementById('dataTable1').deleteRow(i-1);
            }else{
                document.getElementById('dataTable1').deleteRow(recordNo-1);
            }

            document.getElementById("rowCount").value = i-1;

            var url = '${pageContext.request.contextPath}/strata/Hal_halLain?deleteKandungan&idKandungan='
                +idKandungan;

            $.get(url,
            function(data){
                        $('#page_div').html(data);
            },'html');
        }
    }
    function delete3(recordNo)
    {

        var i = document.getElementById("rowCount").value;
        var x= document.getElementById('dataTable1').rows[i-1].cells;

        if(i==1){
            document.getElementById('dataTable1').deleteRow(i-1);
        }else{
            document.getElementById('dataTable1').deleteRow(recordNo-1);
        }

        document.getElementById("rowCount").value = i-1;

        regenerateBill('dataTable1')

    }

    function deleteRo1(recordNo)
    {

        var i = document.getElementById("rowCount").value;
        var x= document.getElementById('dataTable1').rows[i-1].cells;
        var idKandungan = x[0].innerHTML;
        alert(idKandungan);
        if(i==1){
            document.getElementById('dataTable1').deleteRow(i-1);
        }else{
            document.getElementById('dataTable1').deleteRow(recordNo-1);
        }

        document.getElementById("rowCount").value = i-1;

        var url = '${pageContext.request.contextPath}/strata/Hal_halLain?deleteKandungan&idKandungan='
            +idKandungan;

        alert(url);

        $.get(url,
        function(data){
                    $('#page_div').html(data);
        },'html');
    }

    function addRow1(tableid){
        var table = document.getElementById(tableid);
        var rowcount = table.rows.length;
        if(rowcount < 3){
            var row = table.insertRow(rowcount);

            var leftcell = row.insertCell(0);
            var bil = document.createTextNode("6."+(rowcount+1));
            leftcell.appendChild(bil);

            var rightcell = row.insertCell(1);
            var el = document.createElement('textarea');
            el.name = 'ulasan' + (rowcount+1);
            el.rows = 5;
            el.cols = 100;
            el.className = "normal_text";
            //el.style.textTransform = 'uppercase';
            rightcell.appendChild(el);

        }else{
            alert("Batas Maksimum Baris");
            return true;
        }
    }
    function deleteRow1(tableid) {

        var table = document.getElementById(tableid);
        var rowCount = table.rows.length;
        table.deleteRo1(rowCount-1);
    }

    function clear1(){
        document.getElementById("ulasan").value = " ";
    }

</script>

<s:form beanclass="etanah.view.strata.HalhalLainActionBean" name="form1">
    <s:messages/>
    <s:errors/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Syor Penolong Pegawai Tanah</legend>
            <br /><p align="center">
                <s:radio name="syor" id="syorsokong" value="DI"> </s:radio>&nbsp;Disokong &nbsp;&nbsp;<s:radio id="syorsokong2" name="syor" value="DT"> </s:radio>&nbsp;Ditolak
                </p>
            </fieldset>
        </div>
        <br /><br />
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Hal-hal lain</legend>
                <div class="content">
                    <table>
                        <tr><td>
                                <table id ="dataTable1">
                                <c:set var="i" value="1" />
                                <c:if test="${fn:length(actionBean.senaraiLaporanKandungan) == 0 or actionBean.senaraiLaporanKandungan eq null}">
                                    <tr><td><b>6.1</b></td>
                                        <td><font style="text-transform:uppercase"><s:textarea name="ulasaan1" id="ulasaan1" rows="5" cols="100" class="normal_text"/></font></td>
                                        <td></td>
                                    </tr>
                                    <c:set var="i" value="${i+1}" />
                                </c:if>
                                <c:forEach items="${actionBean.senaraiLaporanKandungan}" var="line">
                                    <tr style="font-weight:bold">
                                        <td ></td><td><c:out value="6.${i}"/></td>
                                        <td><font style="text-transform: uppercase">
                                                <s:textarea name="ulasan${i}" id="ulasan${i}"  rows="5" cols="100" class="normal_text">${line.kand}</s:textarea>                                                
                                                </font>
                                            </td>                                       
                                            <td>
                                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                 id="${i}" onclick="deleteRow3(${i},${line.idKand})" onmouseover="this.style.cursor='pointer';">
                                        </td>
                                    </tr>
                                    <c:set var="i" value="${i+1}" />

                                </c:forEach>
                                <s:hidden name="rowCount" value="${i-1}" id="rowCount"/>
                            </table>
                        </td>
                    </tr>                   
                </table>               

                <p align="left">
                    <s:button class="btn" name="tambahRow" value="Tambah" onclick="doSubmit(this.form,this.name,'page_div')"/><s:button name="SimpanHahal" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form,this.name,'page_div')"/>
                </p>
            </div>
        </fieldset>
    </div>
</s:form>