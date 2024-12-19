<%--
    Document   : carian_diari_siasatan
    Created on :Aug 23, 2011, 12:23:22 PM
    Author     : latifah.iskak
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/src/main/webapp/pub/scripts/datepicker.js"></script>
<script language="javascript" type="text/javascript"src="${pageContext.request.contextPath}/src/main/webapp/pub/js/datetimepicker.js"></script>
<script language="javascript" type="text/javascript"src="${pageContext.request.contextPath}/JavaScript/datetimepicker.js"></script>



<script language="javascript" type="text/javascript">

    $(document).ready( function(){
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yyyy'});
    });

    function validateForm(){

        if(document.getElementById("idPermohonanCarian").value == ""){
            alert("Sila cari dahulu rekod dengan memasukkan Id Permohonan yang dikehendaki");
            $('#idMohon').focus();
            return false;
        }

        var bil =  document.getElementById("recordCount").value;
        var tajuk = document.getElementById('tajuk');

        if(tajuk.value == "" ){
            alert("Sila isikan Tajuk Kertas Butiran penyiasatan terlebih dahulu");
            $('#tajuk').focus();
            return false;
        }

        if(document.getElementById("recordCount").value == "" ){
            alert("Sila isikan  Butiran penyiasatan terlebih dahulu");
            return false;
        }

        for (var i = 0; i < bil; i++){

            var kandungan = document.getElementById('kandungan'+i);
            var tarikh = document.getElementById('tarikh'+i);
            var jam = document.getElementById('jam'+i);
            var minit = document.getElementById('minit'+i);
            var ampm = document.getElementById('ampm'+i);

            if(kandungan.value == "" ){
                alert("Sila isikan Butiran penyiasatan terlebih dahulu");
                $('#kandungan'+i).focus();
                return false;
            }
            if(tarikh.value == "" ){
                alert("Sila isikan Tarikh terlebih dahulu");
                $('#tarikh'+i).focus();
                return false;
            }
            if(jam.value == "" ){
                alert("Sila isikan masa laporan terlebih dahulu");
                $('#jam'+i).focus();
                return false;
            }

            if (jam.value >= 13){
                alert("Sila masukkan bilangan 1 sampai 12 sahaja.");
                $('#jam'+i).focus();
                return false;
            }

            if(minit.value == "" ){
                alert("Sila isikan masa laporan terlebih dahulu");
                $('#minit'+i).focus();
                return false;
            }

            if (minit.value >= 59){
                alert("Sila masukkan bilangan 0 sampai 59 sahaja.");
                $('#minit'+i).focus();
                return false;
            }

            if(ampm.value == "" ){
                alert("Sila pilih am atau pm pada bahagian masa laporan");
                $('#ampm'+i).focus();
                return false;
            }

        }
        return true;
    }

    function addRow (tableid){

        if(document.getElementById("idPermohonanCarian").value == ""){
            alert("Sila cari dahulu rekod dengan memasukkan Id Permohonan yang dikehendaki");
            $('#idMohon').focus();
            return false;
        }

        var table = document.getElementById (tableid);
        var rowcount = table.rows.length;
        var row = table.insertRow(rowcount);
        document.getElementById("recordCount").value =rowcount;

        var id="toDate"+(rowcount-1);

        var cell1 = row.insertCell(0);
        var e1 = document.createElement("INPUT");
        e1.setAttribute("type","checkbox");
        e1.setAttribute("name","pilih" +(rowcount-1));
        cell1.appendChild(e1);

        var cell2 = row.insertCell(1);
        var bil = document.createTextNode(rowcount);
        cell2.appendChild(bil);


        var cell3 = row.insertCell(2);
        var e2 = document.createElement("textarea");
        e2.t = "kandungan"+(rowcount-1);
        e2.rows = 3;
        e2.cols = 50;
        e2.name ="kandungan"+(rowcount-1);
        e2.id ="kandungan"+(rowcount-1);
        e2.onchange = function(){convertText(this,"kandungan" +(rowcount-1));};
        cell3.appendChild(e2);

        var cell4 = row.insertCell(3);
        var e3 = document.createElement("INPUT");
        e3.setAttribute("type","text");
        e3.setAttribute("name","tarikh"+(rowcount-1));
        e3.setAttribute("id","tarikh"+(rowcount-1));
        e3.setAttribute("formatPattern", "dd/mm/yyyy");
        e3.setAttribute("formatType", "date");
        e3.onmouseover = function(){setDatepicker(id,rowcount);};
        cell4.appendChild(e3);

        var cell5 = row.insertCell(4);
        var e7 =document.createTextNode(' ');
        var e8 =document.createTextNode(' ');
        var e4 = document.createElement("select");
        e4.setAttribute("name","jam"+(rowcount-1));
        e4.setAttribute("id","jam" +(rowcount-1));

            var option1 = document.createElement("option");
            option1.text = "Jam";
            option1.value ="";
            e4.options.add(option1);



    <c:forEach var="hour" begin="1" end="12">
            var option2 = document.createElement("option");
        <c:choose>
            <c:when test="${hour > 9}">
                    var textVal2=document.createTextNode("${hour}");
                    option2.appendChild(textVal2);
                    option2.setAttribute("value","${hour}");
            </c:when>
            <c:otherwise>
                    var textVal2=document.createTextNode("0${hour}");
                    option2.appendChild(textVal2);
                    option2.setAttribute("value","0${hour}");
            </c:otherwise>
        </c:choose>
                e4.appendChild(option2);
    </c:forEach>


            var e5 = document.createElement("select");
            e5.setAttribute("name","minit"+(rowcount-1));
            e5.setAttribute("id","minit" +(rowcount-1));

                var option3 = document.createElement("option");
                option3.text = "Minit";
                option3.value ="";
                e5.options.add(option3);


    <c:forEach var="minute" begin="0" end="59">
            var option4 = document.createElement("option");
        <c:choose>
            <c:when test="${minute > 9}">
                    var textVal2=document.createTextNode("${minute}");
                    option4.appendChild(textVal2);
                    option4.setAttribute("value","${minute}");
            </c:when>
            <c:otherwise>
                    var textVal2=document.createTextNode("0${minute}");
                    option4.appendChild(textVal2);
                    option4.setAttribute("value","0${minute}");
            </c:otherwise>
        </c:choose>
                e5.appendChild(option4);
    </c:forEach>



            var e6 = document.createElement("select");
                 e6.setAttribute("name","ampm"+(rowcount-1));
                 e6.setAttribute("id","ampm" +(rowcount-1));
                 e6.setAttribute("style","width:75px;");
               
            //for "sila pilih"
            var option5 = document.createElement("option");
                option5.text = " Pilih";
                option5.value ="";
                e6.options.add(option5);

            var option6 = document.createElement("option");
                option6.text = "Pagi";
                option6.value ="am";
                e6.options.add(option6);

            var option7 = document.createElement("option");
                option7.text = "Petang";
                option7.value ="pm";
                e6.options.add(option7);

            cell5.appendChild(e4);
            cell5.appendChild(e7);
            cell5.appendChild(e5);
            cell5.appendChild(e8);
            cell5.appendChild(e6);

        }

        function setDatepicker(id,rowcount){
            $("#tarikh"+(rowcount-1)).datepicker();
        }

        function deleteRow(r,tableid){
            alert(tableid);
            if(confirm('Adakah anda pasti untuk hapus?')) {
                var i=r.parentNode.parentNode.rowIndex;
                document.getElementById('tbl').deleteRow(i);
            }
            regenerateBill(tableid);

        }

        function deleteRow1(tableid) {
            var idKertas;
            var idPermohonan = $('#idPermohonanCarian').val();
            try {
                var table = document.getElementById(tableid);
                var rowCount = table.rows.length;
    <%--alert("rowcount : " +rowCount);--%>
                var j=0;
                for(var i=0; i<rowCount; i++) {
                    var row = table.rows[i];
                    var chkbox = row.cells[0].childNodes[0];
    <%--alert("chkbox to string : "+chkbox.toString());--%>
                    if(null != chkbox && true == chkbox.checked) {
    <%--alert("check box dipilih");--%>
                        idKertas = $("#idKertas"+(i+j-1)).val();
    <%--alert("id kertas : "+idKertas);--%>
                        if(document.getElementById("idKertas"+(i+j-1)) != null){
                            var url = '${pageContext.request.contextPath}/penguatkuasaan/utiliti_diari_siasatan?deleteDiari&idKandungan='
                                +idKertas;

                            $.get(url,
                            function(data){
                                $('#page_div').html(data);
                                refreshPageDiari(idPermohonan);
                            },'html');
                        }

                        table.deleteRow(i);
                        rowCount--;
                        i--;
                        j++;

                    }
                }
            }catch(e) {
                alert(e);
            }

            regenerateBill(tableid);
        }

        function regenerateBill(tableid){
            try{
                var table = document.getElementById(tableid);
                var rowCount = table.rows.length;
                document.getElementById("recordCount").value =rowCount-1;

                if(rowCount > 1){
                    for(var i=1;i<rowCount;i++){
                        table.rows[i].cells[1].childNodes[0].data= i;
                    }
                }

            }catch(e){
                alert("Error in regenerateBill : "+e);
            }
        }


        function removeDiari(idKandungan){
            var idPermohonan = $('#idPermohonanCarian').val();
            if(confirm('Adakah anda pasti untuk hapus kkk?')) {
                var url = '${pageContext.request.contextPath}/penguatkuasaan/utiliti_diari_siasatan?deleteDiari&idKandungan='+idKandungan;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
            }
        }

        function refreshPageDiari(idPermohonan){
    <%--alert("refreshPageDiari");--%>
            var url = '${pageContext.request.contextPath}/penguatkuasaan/utiliti_diari_siasatan?reload&idPermohonan='+idPermohonan;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }

        function convertText(r,t){
            var i = r.value;
            document.getElementById(t).value=i.toUpperCase();
        }


        function limitText(limitField, limitNum) {
            if (limitField.value.length > limitNum) {
                limitField.value = limitField.value.substring(0, limitNum);
            }
        }

        function doViewReport(v) {
            var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
            window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }
</script>

<s:form beanclass="etanah.view.penguatkuasaan.UtilitiDiariSiasatanIOActionBean" name="form1">
    <s:messages/>
    <s:errors/>
    <s:hidden name="permohonan.idPermohonan" />

    <div class="subtitle">
        <fieldset class="aras1">

            <legend>Carian diari siasatan</legend>
            <br>
            <p><label for="idPermohonan">Id Permohonan/ID Perserahan :</label>
                <input type="text" name="idPermohonan" id="idMohon" onkeyup="this.value=this.value.toUpperCase();"/>
                <s:submit name="searchDiariSiasatan" value="Cari" class="btn"/>
            </p>

            <legend>Diari Siasatan</legend>
            <%--   <p>
                   <label>Id Permohonan :</label>
                   <s:text name="idPermohonan" value="${actionBean.diariSiasatan.permohonan.idPermohonan}" disabled="true" id="idPermohonanCarian"/> &nbsp;
                   <s:hidden name="idPermohonan" value="${actionBean.diariSiasatan.permohonan.idPermohonan}" id="idPermohonanCarian"/>
               </p>--%>


            <p>
                <label>Id Permohonan :</label>
                ${actionBean.idPermohonan} &nbsp;
                ${actionBean.authorizedUser}
                <s:hidden name="idPermohonan" id="idPermohonanCarian"/>
            </p>
            <p>
                <label><em>*</em>Kertas Butiran Penyiasatan :</label>
                <s:textarea name="tajuk" id="tajuk" cols="70" rows="10" onkeydown="limitText(this,4000);" onkeyup="limitText(this,4000);" onchange="this.value=this.value.toUpperCase();"> </s:textarea>
                </p>

            <s:hidden name="recordCount" id="recordCount"/>
            <div class="content" align="center">
                <table  width="70%" id="tbl" class="tablecloth">
                    <tr>
                        <th  width="1%" align="center"><b>Pilih</b></th>
                        <th  width="1%" align="center"><b>Bil</b></th>
                        <th  width="1%" align="center"><b><em>*</em>Butiran Penyiasatan</b></th>
                        <th  width="1%" align="center"><b><em>*</em>Tarikh</b></th>
                        <th  width="3%" align="center"><b><em>*</em>Masa</b></th>
                    </tr>
                    <c:set value="0" var="i"/>
                    <c:forEach items="${actionBean.listButiranDiariSiasatan}" var="line">
                        <tr style="font-weight:bold">

                            <td>
                                <input type="checkbox" name="pilih${i}" id="pilih${i}">
                            </td>
                            <td><c:out value="${i+1}"/></td>
                            <td><font style="text-transform: uppercase">
                                    <textarea name="kandungan${i}" id="kandungan${i}" cols="50" rows="3" onchange="convertText(this,'kandungan${i}')">${line.kandungan}</textarea>
                                    <%--<s:textarea name="kandungan${i}" id="kandungan${i}" value="${line.kandungan}" cols="50" rows="3" onchange="convertText(this,'kandungan${i}')"/>--%>
                                </font>
                            </td>
                            <td>
                                <s:text name="tarikh${i}" class="datepicker" formatType="date" formatPattern="dd/MM/yyyy" id="tarikh${i}" value="${line.tarikhButiran}"/>&nbsp;
                            </td>
                            <%--to set value for AM/PM--%>
                            <c:set var="waktu" value="${fn:substring(line.tarikhButiran,11,13)}"/>
                            <c:if test="${waktu > 11}"><c:set var="time" value="PM"/></c:if>
                            <c:if test="${waktu <= 11}"><c:set var="time" value="AM"/></c:if>
                            <%--to set value for hour--%>
                            <fmt:formatDate var="hourVal" value="${line.tarikhButiran}" pattern="hh" />
                            <%--to set value for minute--%>
                            <fmt:formatDate var="minuteVal" value="${line.tarikhButiran}" pattern="mm" />
                            <td>
                                <s:select name="jam${i}" id="jam${i}" value="${hourVal}">
                                    <s:option value=""> Jam </s:option>
                                    <c:forEach var="hour" begin="1" end="12">
                                        <c:choose>
                                            <c:when test="${hour > 9}"><s:option value="${hour}">${hour}</s:option></c:when>
                                            <c:otherwise><s:option value="0${hour}">0${hour}</s:option></c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </s:select>
                                <s:select name="minit${i}" id="minit${i}" value="${minuteVal}">
                                    <s:option value=""> Minit </s:option>
                                    <c:forEach var="minute" begin="00" end="59" >
                                        <c:choose>
                                            <c:when test="${minute > 9}"><s:option value="${minute}">${minute}</s:option></c:when>
                                            <c:otherwise><s:option value="0${minute}">0${minute}</s:option></c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </s:select>
                                <s:select name="ampm${i}" id="ampm${i}" value="${time}" style="width:75px">
                                    <s:option value="">Pilih</s:option>
                                    <s:option value="AM">PAGI</s:option>
                                    <s:option value="PM">PETANG</s:option>
                                </s:select>
                            </td>

                            <%--<s:hidden name="idKertas${i}" id="idKertas${i}" value="${line.idKandungan}" />--%>

                        </tr>
                        <input type="hidden" name="idKertas${i}" id="idKertas${i}" value="${line.idKandungan}">

                        <c:set var="i" value="${i+1}" />
                    </c:forEach>

                </table>
                <br/>

                <table width="70%">
                    <tr><td align="center">
                            <p align="center">** Sila tanda di petak Pilih untuk hapus.</p>
                            <br/>
                            <c:choose>
                                <c:when test="${actionBean.idPermohonanNotExist eq true || actionBean.authorizedUser eq false}">
                                    <s:button class="btn" value="Tambah" name="add" disabled="true" onclick="addRow('tbl')" />
                                    <s:submit name="simpanDiariSiasatan" id="save" disabled="true" value="Simpan & Jana" class="longbtn" onclick="return validateForm()"/>
                                    <s:button class="btn" value="Hapus" name="delete" disabled="true" onclick="deleteRow1('tbl')"/>
                                </c:when>
                                <c:otherwise>
                                    <s:button class="btn" value="Tambah" name="add" onclick="addRow('tbl')" />
                                    <s:submit name="simpanDiariSiasatan" id="save" value="Simpan & Jana" class="longbtn" onclick="return validateForm()"/>
                                    <s:button name="" value="Papar Dokumen" class="longbtn" onclick="doViewReport('${actionBean.idDokumen}');"/>
                                    <s:button class="btn" value="Hapus" name="delete" onclick="deleteRow1('tbl')"/>
                                </c:otherwise>
                            </c:choose>

                </table>

            </div>
        </fieldset>
    </div>

</s:form>

