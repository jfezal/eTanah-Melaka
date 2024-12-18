<%-- 
    Document   : fakta_kes
    Created on : Sep 11, 2012, 2:49:21 PM
    Author     : Siti Fariza Hanim
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

        var bil =  document.getElementById("recordCount").value;
        var tempatSidang = document.getElementById('tempatSidang');
        var nomborRujukan = document.getElementById('nomborRujukan');
        var tajuk = document.getElementById('tajuk');
        var diTandatanganOleh = document.getElementById('diTandatanganOleh');
        
        if(tempatSidang.value == "" ){
            alert("Sila isikan Mahkamah terlebih dahulu");
            $('#tempatSidang').focus();
            return false;
        }

        if(nomborRujukan.value == "" ){
            alert("Sila isikan Kes Saman terlebih dahulu");
            $('#nomborRujukan').focus();
            return false;
        }

        if(tajuk.value == "" ){
            alert("Sila isikan Tajuk terlebih dahulu");
            $('#tajuk').focus();
            return false;
        }

        if(document.getElementById("recordCount").value == 0 ){
            alert("Sila isikan  Fakta Kes terlebih dahulu");
            return false;
        }

        for (var i = 0; i < bil; i++){

            var kandungan = document.getElementById('kandungan'+i);

            if(kandungan.value == "" ){
                alert("Sila isikan Fakta Kes terlebih dahulu");
                $('#kandungan'+i).focus();
                return false;
            }


        }

        if(diTandatanganOleh.value == "" ){
            alert("Sila pilih di tandatangan oleh terlebih dahulu");
            $('#tajuk').focus();
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
        var e2 = document.createElement("textarea");
        e2.t = "kandungan"+(rowcount-1);
        e2.rows = 3;
        e2.cols = 64;
        e2.name ="kandungan"+(rowcount-1);
        e2.id ="kandungan"+(rowcount-1);

        cell3.appendChild(e2);

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
        var idKandungan;
        try {
            var table = document.getElementById(tableid);
            var rowCount = table.rows.length;
            var j=0;
            for(var i=0; i<rowCount; i++) {
                var row = table.rows[i];
                var chkbox = row.cells[0].childNodes[0];
                if(null != chkbox && true == chkbox.checked) {
                    idKandungan = $("#idKertas"+(i+j-1)).val();
                    if(document.getElementById("idKertas"+(i+j-1)) != null){
                        var url = '${pageContext.request.contextPath}/penguatkuasaan/fakta_kes?deleteFaktaKes&idKandungan='
                            +idKandungan;

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


    function removeDiari(idKandungan){

        if(confirm('Adakah anda pasti untuk hapus?')) {
            var url = '${pageContext.request.contextPath}/penguatkuasaan/fakta_kes?deleteFaktaKes&idKandungan='+idKandungan;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
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

<s:form beanclass="etanah.view.penguatkuasaan.FaktaKesActionBean" name="form2">
    <s:messages/>
    <s:errors/>
    <%--<s:hidden name="permohonan.idPermohonan" />--%>
    <s:hidden name="permohonan.idPermohonan" />
    <c:if test="${edit}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Fakta Kes</legend>
                <p>
                    <label><em>*</em>MAHKAMAH MAJISTRET :</label>
                    <s:text name="tempatSidang" id="tempatSidang" maxlength="150" size="50" class="normal_text"> </s:text>
                </p>
                <p>
                    <label><em>*</em>KES SAMAN :</label>
                    <s:textarea name="nomborRujukan" id ="nomborRujukan" rows="7" cols="50" onkeydown="limitText(this,499);" onkeyup="limitText(this,499);" class="normal_text"/>&nbsp;
                </p>
                <p>
                    <label class="center"><em>*</em>PENDAKWA RAYA Lwn</label>
                    <s:text name="tajuk" id="tajuk" maxlength="50" size="50" class="normal_text"> </s:text>
                    </p>
                <s:hidden name="recordCount" id="recordCount"/>
                <div class="content" align="center">
                    <table  width="45%" id="tbl" class="tablecloth">
                        <tr>
                            <th  width="1%" align="center"><b>PILIH</b></th>
                            <th  width="1%" align="center"><b>BIL</b></th>
                            <th  width="20%" align="center"><b><em>*</em>FAKTA KES</b></th>
                        </tr>
                        <c:set value="0" var="i"/>
                        <c:forEach items="${actionBean.senaraiKertas}" var="line">
                            <tr style="font-weight:bold">

                                <td><s:checkbox name="pilih${i}" id="pilih${i}" /> </td>
                                <td><c:out value="${i+1}"/></td>
                                <td><font>
                                        <s:textarea name="kandungan${i}" id="kandungan${i}" value="${line.kandungan}" cols="56" rows="3" class="normal_text"/>
                                    </font>
                                </td>

                                <s:hidden name="idKertas${i}" id="idKertas${i}" value="${line.idKandungan}" />
                            </tr>
                            <c:set var="i" value="${i+1}" />
                        </c:forEach>

                    </table>
                    <br/>
                    <br/>
                    <div id="oleh">
                        <p>
                            <label>Ditandatangan Oleh :</label>
                            <s:select name="diTandatanganOleh" id="diTandatanganOleh">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${actionBean.senaraiPengguna}" label="nama" value="idPengguna" />
                            </s:select>
                        </p>
                    </div>
                    <table width="80%">
                        <tr><td align="center">
                                <p align="center">** Sila tanda di petak Pilih untuk hapus.</p>
                                <br/>
                                <s:button class="btn" value="Tambah" name="add" onclick="addRow('tbl')" />
                                <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="if(validateForm())doSubmit(this.form, this.name, 'page_div')"/>
                                <s:button class="btn" value="Hapus" name="delete" onclick="deleteRow1('tbl')"/>


                    </table>

                </div>
            </fieldset>
        </div>
    </c:if>

    <c:if test="${view}">
        <div class="subtitle">
            <fieldset class="aras1">
                <p><label>MAHKAMAH MAJISTRET :</label>
                    ${actionBean.tempatSidang}&nbsp;
                </p>
                <p><label>KES SAMAN :</label>
                    ${actionBean.nomborRujukan}&nbsp;
                </p>
                <p><label>PENDAKWA RAYA Lwn</label>
                    ${actionBean.tajuk}&nbsp;
                </p>

                <div class="content" align="center">
                    <display:table name="${actionBean.senaraiKertas}" id="line" class="tablecloth" pagesize="10">
                        <display:column title="BIL">${line_rowNum}</display:column>
                        <display:column property="kandungan" title="FAKTA KES" />
                    </display:table>
                </div>

                <div id="oleh">
                    <p>
                        <label>Ditandatangan Oleh :</label>
                        <s:select name="diTandatanganOleh" id="diTandatanganOleh" disabled="true">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${actionBean.senaraiPengguna}" label="nama" value="idPengguna" />
                        </s:select>
                    </p>
                </div>
            </fieldset>
        </div>
    </c:if>

</s:form>

