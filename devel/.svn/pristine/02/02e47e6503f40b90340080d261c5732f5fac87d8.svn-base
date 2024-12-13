<%-- 
    Document   : maklumat_agensi_luar
    Created on : July 11, 2014, 2:49:21 PM
    Author     : admin
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
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
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
    p{
        color:black;
        font-weight:normal;
        font-size:13px;
        padding-top:2px;
        padding-bottom:2px;
        line-height: 15pt ;
        text-align: justify;
    }
</style>

<script language="javascript" type="text/javascript">

    function validateForm(){
 
        var tajuk = document.getElementById('tajuk');
  
        if(tajuk.value == "" ){
            alert("Sila isikan Tajuk terlebih dahulu");
            $('#tajuk').focus();
            return false;
        }
                
        if($('#datepicker').val()=="")
        {
            alert('Sila isikan Tarikh Mesyuarat terlebih dahulu');
            $('#datepicker').focus();
            return false;
        }
        if($('#jam').val()=="")
        {
            alert('Sila isikan masa laporan terlebih dahulu');
            $('#jam').focus();
            return false;
        }
        if($('#minit').val()=="")
        {
            alert('Sila isikan masa laporan terlebih dahulu');
            $('#minit').focus();
            return false;
        }
        if($('#ampm').val()=="")
        {
            alert('Sila pilih pagi atau petang pada bahagian masa laporan');
            $('#ampm').focus();
            return false;
        }

        return true;
    }

    function addRow (tableid){

        var table = document.getElementById (tableid);
        var rowcount = table.rows.length;
        var row = table.insertRow(rowcount);
        document.getElementById("recordCount").value =rowcount;


        var cell1 = row.insertCell(0);
        var e1 = document.createElement("INPUT");
        e1.setAttribute("type","checkbox");
        e1.setAttribute("name","pilih" +(rowcount-1));
        cell1.appendChild(e1);

        var cell2 = row.insertCell(1);
        var bil = document.createTextNode(rowcount);
        cell2.appendChild(bil);
        
          var cell3 = row.insertCell(2);
          var e3 = document.createElement("select");
            e3.setAttribute("name","kodAgensi"+(rowcount-1));
            e3.setAttribute("id","kodAgensi" +(rowcount-1));

        //for "sila pilih"
            var option1 = document.createElement("option");
            option1.text = " Sila pilih ";
            option1.value ="";
            e3.options.add(option1);

    <c:forEach items="${listUtil.senaraiAgensiSemua}" var="line">
            var option2 = document.createElement("option");
            var textVal2=document.createTextNode("${line.nama}");
            option2.appendChild(textVal2);
            option2.setAttribute("value","${line.kod}");
            e3.appendChild(option2);
    </c:forEach>
                
            cell3.appendChild(e3);




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
            var idRujukan;
            try {
                var table = document.getElementById(tableid);
                var rowCount = table.rows.length;
                var j=0;
                for(var i=0; i<rowCount; i++) {
                    var row = table.rows[i];
                    var chkbox = row.cells[0].childNodes[0];
                    if(null != chkbox && true == chkbox.checked) {
                        idRujukan = $("#idRujukan"+(i+j-1)).val();
                        if(document.getElementById("idRujukan"+(i+j-1)) != null){
                            var url = '${pageContext.request.contextPath}/pelupusan/maklumat_agensi?deleteAgensi&idRujukan='
                                +idRujukan;

                            $.get(url,
                            function(data){
                                $('#page_div').html(data);
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


   

        function convertText(r,t){
            var i = r.value;
            document.getElementById(t).value=i.toUpperCase();
        }

        function limitText(limitField, limitNum) {
            if (limitField.value.length > limitNum) {
                limitField.value = limitField.value.substring(0, limitNum);
            }
        }

       
</script>

<s:form beanclass="etanah.view.stripes.pelupusan.MaklumatAgensiActionBean" name="form2">

    <s:messages/>
    <s:errors/>
    <s:hidden name="permohonan.idPermohonan" />
    <c:if test="${edit}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Panggilan Mesyuarat</legend>
                <p>
                    <label><em>*</em>Tajuk :</label>
                    <s:text name="tajuk" id="tajuk" maxlength="150" size="50" class="normal_text"> </s:text>
                </p>
                <p>
                    <label><em>*</em>Tarikh Mesyuarat :</label>
                    <s:text name="tarikhMesyuarat" class="datepicker" formatType="date" formatPattern="dd/MM/yyyy" id="datepicker" />&nbsp;
                </p>
                <p>
                    <label><em>*</em>Masa Mesyuarat :</label>
                    <s:select name="jam" id="jam">
                        <s:option value=""> Jam </s:option>
                        <c:forEach var="jam" begin="1" end="12">
                            <s:option value="${jam}">${jam}</s:option>
                        </c:forEach>
                    </s:select>
                    <s:select name="minit" id="minit">
                        <s:option value=""> Minit </s:option>
                        <c:forEach var="minit" begin="00" end="59" >
                            <c:choose>
                                <c:when test="${minit > 9}"><s:option value="${minit}">${minit}</s:option></c:when>
                                <c:otherwise><s:option value="0${minit}">0${minit}</s:option></c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </s:select>
                    <s:select name="ampm" id="ampm" style="width:80px">
                        <s:option value="">Pilih</s:option>
                        <s:option value="AM">PAGI</s:option>
                        <s:option value="PM">PETANG</s:option>
                    </s:select>
                </p>
                <p>
                    <label>Tempat :</label>
                    <s:text name="tempatMesyuarat" id="tempatMesyuarat" maxlength="150" size="50" class="normal_text"> </s:text>
                </p>
                <p>
                    <label>Bilangan Mesyuarat :</label>
                    <s:text name="bilanganMesyuarat" id="bilanganMesyuarat" maxlength="150" size="50" class="normal_text"> </s:text>
                </p>
                <s:hidden name="recordCount" id="recordCount"/>
                <div class="content" align="center">
                    <table  width="45%" id="tbl" class="tablecloth">
                        <tr>
                            <th  width="1%" align="center"><b>PILIH</b></th>
                            <th  width="1%" align="center"><b>BIL</b></th>
                            <th  width="20%" align="center"><b>TAJUK</b></th>
                        </tr>
                        <c:set value="0" var="i"/>
                        <c:forEach items="${actionBean.senaraiAgensiLuar}" var="line">
                            <tr style="font-weight:bold">

                                <td><s:checkbox name="pilih${i}" id="pilih${i}" /> </td>
                                <td><c:out value="${i+1}"/></td>
                                <td><font>
                                        <s:select name="kodAgensi${i}" id="kodAgensi${i}" value="${line.agensi.kod}">
                                            <s:option value="">Sila Pilih</s:option>
                                            <s:options-collection collection="${listUtil.senaraiAgensiSemua}" label="nama" value="kod" sort="nama" />
                                        </s:select>
                                    </font>
                                </td>

                                <s:hidden name="idRujukan${i}" id="idRujukan${i}" value="${line.idRujukan}" />
                            </tr>
                            <c:set var="i" value="${i+1}" />
                        </c:forEach>

                    </table>
                    <br/>
                    <br/>

                    <table width="80%">
                        <tr><td align="center">
                                <p align="center">** Sila tanda di petak Pilih untuk hapus.</p>
                                <br/>
                                <s:button class="btn" value="Tambah" name="add" onclick="addRow('tbl')" />
                                <s:button class="btn" value="Hapus" name="delete" onclick="deleteRow1('tbl')"/>
                                <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="if(validateForm())doSubmit(this.form, this.name, 'page_div')"/>


                    </table>

                </div>
            </fieldset>
        </div>
    </c:if>

    <c:if test="${view}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Panggilan Mesyuarat</legend>
                <p>
                    <label>Tajuk :</label>
                    ${actionBean.tajuk}&nbsp;
                </p>
                <p>
                    <label><em>*</em>Tarikh Mesyuarat :</label>
                    <c:if test="${actionBean.tarikhMesyuarat ne null}">${actionBean.tarikhMesyuarat}&nbsp;</c:if>
                </p>
                <p>
                    <label><em>*</em>Masa Mesyuarat :</label>
                    <c:if test="${actionBean.tarikhMesyuarat ne null}">${actionBean.jam}:${actionBean.minit}&nbsp;${actionBean.ampm}&nbsp;</c:if>
                </p>
                <p>
                    <label>Tempat :</label>
                    ${actionBean.tempatMesyuarat}&nbsp;
                </p>
                <p>
                    <label>Bilangan Mesyuarat :</label>
                    ${actionBean.bilanganMesyuarat}&nbsp;
                </p>
                <s:hidden name="recordCount" id="recordCount"/>
                <div class="content" align="center">
                    <table  width="45%" id="tbl" class="tablecloth">
                        <tr>
                            <th  width="1%" align="center"><b>BIL</b></th>
                            <th  width="20%" align="center"><b>TAJUK</b></th>
                        </tr>
                        <c:set value="0" var="i"/>
                        <c:forEach items="${actionBean.senaraiAgensiLuar}" var="line">
                            <tr style="font-weight:bold">
                                <td><c:out value="${i+1}"/></td>
                                <td><font>
                                        <s:select name="kodAgensi${i}" id="kodAgensi${i}" value="${line.agensi.kod}" disabled="true">
                                            <s:option value="">Sila Pilih</s:option>
                                            <s:options-collection collection="${listUtil.senaraiAgensiSemua}" label="nama" value="kod" sort="nama" />
                                        </s:select>
                                    </font>
                                </td>

                                <s:hidden name="idRujukan${i}" id="idRujukan${i}" value="${line.idRujukan}" />
                            </tr>
                            <c:set var="i" value="${i+1}" />
                        </c:forEach>

                    </table>
                    <br/>
                    <br/>



                </div>
            </fieldset>
        </div>
        
    </c:if>

</s:form>

