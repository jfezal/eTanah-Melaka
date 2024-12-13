<%-- 
    Document   : minitDPP
    Created on : Sep 18, 2012, 10:37:41 PM
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
  
        if(document.getElementById("recordCount").value == "" ){
            alert("Sila isikan  Butiran penyiasatan terlebih dahulu");
            return false;
        }

        for (var i = 0; i < bil; i++){

            var catatanMinit = document.getElementById('catatanMinit'+i);
            var catatanMinit = document.getElementById('tajuk'+i);
       

            if(catatanMinit.value == "" ){
                alert("Sila isikan Minit terlebih dahulu");
                $('#catatanMinit'+i).focus();
                return false;
            }

            if(tajuk.value == "" ){
                alert("Sila isikan Tajuk terlebih dahulu");
                $('#tajuk'+i).focus();
                return false;
            }
           

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
        e2.t = "catatanMinit"+(rowcount-1);
        e2.rows = 3;
        e2.cols = 30;
        e2.name ="catatanMinit"+(rowcount-1);
        e2.id ="catatanMinit"+(rowcount-1);
        cell3.appendChild(e2);

        var cell4 = row.insertCell(3);
        var e3 = document.createElement("textarea");
        e3.t = "tajuk"+(rowcount-1);
        e3.rows = 3;
        e3.cols = 40;
        e3.name ="tajuk"+(rowcount-1);
        e3.id ="tajuk"+(rowcount-1);
        cell4.appendChild(e3);

        var cell5 = row.insertCell(4);
        var e4 = document.createElement("text");
        e4.t = "idDokumen"+(rowcount-1);
        e4.rows = 3;
        e4.cols = 30;
        e4.name ="idDokumen"+(rowcount-1);
        e4.id ="idDokumen"+(rowcount-1);
        cell5.appendChild(e4);
   
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
        var idDokumen;
        try {
            var table = document.getElementById(tableid);
            var rowCount = table.rows.length;
            for(var i=0; i<rowCount; i++) {
                var row = table.rows[i];
                var chkbox = row.cells[0].childNodes[0];
                if(null != chkbox && true == chkbox.checked) {
                    idDokumen = $("#idDokumen"+(i-1)).val();
                    if(document.getElementById("idDokumen"+(i-1)) != null){
                        var url = '${pageContext.request.contextPath}/penguatkuasaan/minitDPP?deleteDokumen&idDokumen='
                            +idDokumen;

                        $.get(url,
                        function(data){
                            $('#page_div').html(data);
                        },'html');
                    }

                    table.deleteRow(i);
                    rowCount--;
                    i--;
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

    function removeDiari(idDokumen){

        if(confirm('Adakah anda pasti untuk hapus?')) {
            var url = '${pageContext.request.contextPath}/penguatkuasaan/minitDPP?deleteDokumen&idDokumen='+idDokumen;
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

    function refreshPageMinit(){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/minitDPP?refreshpage';
        $.get(url,
        function(data){
            $("#uploadMinit").replaceWith($('#uploadMinit', $(data)));
        },'html');
    }

    function refreshPage(){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/minitDPP?refreshpage';
        $.get(url,
        function(data){
            $('#page_div').html(data);
            //refreshPage();
        },'html');
    }


    function muatNaikForm(folderId, idPermohonan, dokumenKod, idDokumen) {
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/2)-(150/2);
        var url = '${pageContext.request.contextPath}/penguatkuasaan/minitDPP?popupUpload&folderId=' + folderId+ '&idPermohonan='
            + idPermohonan + '&dokumenKod=' + dokumenKod + '&idDokumen=' + idDokumen;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
    }

    function doViewReport(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=600");
    }

    function removeImej(idDokumen) {
        if(confirm('Adakah anda pasti untuk hapus?')) {
            var url = '${pageContext.request.contextPath}/penguatkuasaan/minitDPP?deleteSelected1&idDokumen='+idDokumen;
            $.get(url,
            function(data){
                $('#page_div').html(data);
                refreshPage();
            },'html');}
    }


</script>

<s:form beanclass="etanah.view.penguatkuasaan.MinitDPPActionBean" name="form">
    <s:messages/>
    <s:errors/>
    <%--<s:hidden name="permohonan.idPermohonan" />--%>
    <s:hidden name="permohonan.idPermohonan" />
    <c:if test="${edit}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Minit DPP</legend>

                <s:hidden name="recordCount" id="recordCount" value ="${actionBean.recordCount}"/>
                <div class="content" align="center">
                    <table  width="75%" id="tbl" class="tablecloth">
                        <tr>
                            <th  width="1%" align="center"><b>Pilih</b></th>
                            <th  width="1%" align="center"><b>Bil</b></th>
                            <th  width="3%" align="center"><b><em>*</em>Minit</b></th>
                            <th  width="3%" align="center"><b><em>*</em>Tajuk</b></th>
                            <th  width="3%" align="center"><b><em>*</em>Paparan</b></th>

                            <%--<th  width="5%" align="center"><b>Hapus</b></th>--%>
                        </tr>
                        <c:set value="0" var="i"/>
                        <c:forEach items="${actionBean.senaraiMinit}" var="line">
                            <tr style="font-weight:bold">

                                <td><s:checkbox name="pilih${i}" id="pilih${i}" /> </td>
                                <td><c:out value="${i+1}"/></td>
                                <td><font>
                                        <s:textarea name="catatanMinit${i}" id="catatanMinit${i}" value="${line.catatanMinit}" cols="30" rows="3" class="normal_text"/>
                                    </font>
                                </td>
                                <td>
                                    <font>
                                        <s:textarea name="tajuk${i}" id="tajuk${i}" value="${line.tajuk}" cols="30" rows="3" class="normal_text"/>
                                    </font><s:hidden name="idDokumen${i}" id="idDokumen${i}" value="${line.idDokumen}"/>
                                </td>

                                <td width="30%">
                                    <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png"
                                         onclick="muatNaikForm('${actionBean.permohonan.folderDokumen.folderId}',
                                             '${actionBean.permohonan.idPermohonan}','MDPP','${line.idDokumen}');return false;" height="30" width="30" alt="Muat Naik"
                                         onmouseover="this.style.cursor='pointer';" title="Muat Naik untuk Dokumen "/>
                                    <c:if test="${line.namaFizikal != null}">/
                                        <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                             onclick="doViewReport('${line.idDokumen}');" height="30" width="30" alt="papar"
                                             onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${line.kodDokumen.nama}"/>
                                    </c:if>
                                    <c:if test="${line.namaFizikal != null}">/
                                        <img src='${pageContext.request.contextPath}/images/not_ok.gif'
                                             onclick="removeImej('${line.idDokumen}');" height="15" width="15" alt="Hapus"
                                             onmouseover="this.style.cursor='pointer';" title="Hapus untuk Dokumen ${line.kodDokumen.nama}"/>
                                    </c:if>
                                </td>
                            </tr>
                            <c:set var="i" value="${i+1}" />
                        </c:forEach>

                    </table>
                    <br/>

                    <table width="80%">
                        <tr><td align="center">
                                <p align="center">** Sila tanda di petak Pilih untuk hapus.</p>
                                <br/>
                                <s:button class="btn" value="Tambah" name="add" onclick="addRow('tbl')" />
                                <s:button name="simpan" id="simpan" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                                <s:button class="btn" value="Hapus" name="deleteDokumen" onclick="deleteRow1('tbl')"/>


                    </table>

                </div>
            </fieldset>
        </div>
    </c:if>

    <c:if test="${view}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Minit DPP</legend>

                <div class="content" align="center">
                    <display:table name="${actionBean.senaraiMinit}" id="line" class="tablecloth" pagesize="10">
                        <display:column title="Bil">${line_rowNum}</display:column>
                        <display:column property="catatanMinit" title="Minit" />
                        <display:column property="tajuk" title="Tajuk" />
                        <display:column title="Paparan">
                            <c:if test="${line.namaFizikal != null}">
                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                     onclick="doViewReport('${line.idDokumen}');" height="30" width="30" alt="papar"
                                     onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${line.kodDokumen.nama}"/>
                            </c:if>

                        </display:column>
                    </display:table>
                </div>
            </fieldset>
        </div>

    </c:if>

</s:form>