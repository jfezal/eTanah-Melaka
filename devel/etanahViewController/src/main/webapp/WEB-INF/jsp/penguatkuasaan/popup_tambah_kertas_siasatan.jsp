<%-- 
    Document   : popup_tambah_kertas_siasatan
    Created on : Aug 14, 2012, 11:24:21 AM
    Author     : latifah.iskak
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/popup.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-impromptu.js"></script>
<script src="<%= request.getContextPath()%>/js/ui.datepicker-ms.js" type="text/javascript"></script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />


<style type="text/css">
    .tablecloth{
        padding:0px;
        width:70%;
    }

    .infoLP{
        float: right;
        font-weight: bold;
        color:#003194;
        font-family:Tahoma;
        font-size: 13px;

    }

    .infoHeader{
        font-weight: bold;
        color:#003194;
        font-family:Tahoma;
        font-size: 13px;
        text-align: center;

    }

    .infoOks td {
        font: 90% Arial,Helvetica,sans-serif;
    }

</style>
<script type="text/javascript">
    $(document).ready( function(){
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        
        //disabled checkbox
        var idMohonBaru = $("#idMohonBaru").val();
        //alert("idMohonBaru : "+idMohonBaru);
        
        if(idMohonBaru == ""){
            $("#statusOperation").val("add");
        }else{
            $("#statusOperation").val("edit");
        }
        
        var statusOperation = $("#statusOperation").val();
        //alert("statusOperation : "+statusOperation);
        
        var bilOp =  ${fn:length(actionBean.listOp)}; //list op
        var currentIndex = "none";
        for (var p = 0; p < bilOp; p++){
            //alert("bil : "+bilOp);
            var listOks = document.getElementById('totalSenaraiOks'+p).value;
            
            for (var b = 0; b < listOks; b++){
                var idMohonBaruEachOks = document.getElementById("idMohonBaruEachOks"+p+b).value;
                //alert("idMohonBaruEachOks ["+p+b+"]: "+idMohonBaruEachOks);
                if(statusOperation == "edit"){
                    if($("#statusIp"+p+b).val() == "Y"){
                        //alert("p semasa Y : "+p);
                        document.getElementById("pilihCheckBox"+p+b).checked = true;
                    }
                    if(idMohonBaruEachOks != idMohonBaru){
                        document.getElementById("pilihCheckBox"+p+b).disabled = true;
                    }else{
                        currentIndex = p;
                    }
                }else{
                    //for add new 
                    if($("#statusIp"+p+b).val() == "Y"){
                        //alert("p semasa Y : "+p);
                        document.getElementById("pilihCheckBox"+p+b).checked = true;
                        document.getElementById("pilihCheckBox"+p+b).disabled = true;
                    }
                }
            }
        }
        
        if(statusOperation == "edit"){
            //alert("currentIndex :"+currentIndex);
            for (var k = 0; k < bilOp; k++){
                if(currentIndex != k){
                    var listOks = document.getElementById('totalSenaraiOks'+k).value;
                    //alert("currentIndex ["+currentIndex+"] tak sama dengan k ["+k+"]");
                    for (var j = 0; j < listOks; j++){
                        //alert("disable kan :"+k+j);
                        document.getElementById("pilihCheckBox"+k+j).disabled = true;
                    } 
                }else{
                    var listOks = document.getElementById('totalSenaraiOks'+k).value;
                    //alert("currentIndex ["+currentIndex+"] sama dengan k ["+k+"]");
                    for (var j = 0; j < listOks; j++){
                        var idMohonBaruEachOks = document.getElementById("idMohonBaruEachOks"+k+j).value;
                        //alert("enable kan :"+k+j);
                        if(idMohonBaruEachOks == idMohonBaru){
                            document.getElementById("pilihCheckBox"+k+j).disabled = false;
                        }
                    } 
                }
                
            } 
        }
     
        
       
    });

    function save(event, f){

        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.opener.refreshPageIP();
            self.close();
        },'html');

    }

    function validateForm(){
       
        var bilOp =  ${fn:length(actionBean.listOp)}; //list op
        //alert("bilOp : "+bilOp);

        var bilOks= 0;

        for (var i = 0; i < bilOp; i++){
            //check for list barang
            var listOks = document.getElementById('totalSenaraiOks'+i).value;
            for (var b = 0; b < listOks; b++){
                //alert("p[id operasi] : "+i +". b[row] :"+b);
                var pilihCheckBox = document.getElementById('pilihCheckBox'+i+b).checked;
                //alert("pilih barang untuk id op "+i+" :"+pilihBarangDakwa);

                if( pilihCheckBox == true){
                    bilOks++;
                }

            }
        }

        //alert("bilBarang : "+bilBarang);

        if(bilOks==0 ){
            alert("Sila pilih orang yang disyaki");
            return false;
        }
        if($("#pilihPengguna").val() == ""){
            alert("Sila pilih ketua pegawai penyiasat");
            $("#pilihPengguna").focus();
            return false;
            
        }
        
        var statusOperation = $("#statusOperation").val();
        var idMohonBaru = $("#idMohonBaru").val();
        if(statusOperation == "edit"){
            var bilCurrentOks= 0;

            for (var i = 0; i < bilOp; i++){
                //check for list barang
                var listOks = document.getElementById('totalSenaraiOks'+i).value;
                for (var b = 0; b < listOks; b++){
                    var idMohonBaruEachOks = document.getElementById("idMohonBaruEachOks"+i+b).value;
                   
                    if(idMohonBaruEachOks == idMohonBaru){
                        var pilihCheckBox = document.getElementById('pilihCheckBox'+i+b).checked;
                        //alert("current status : "+pilihCheckBox+"["+i+b+"]");
                        if( pilihCheckBox == true){
                            bilCurrentOks++;
                        }

                    }
                   
                }
            }
            //alert("bilCurrentOks :"+bilCurrentOks);
            if(bilCurrentOks==0 ){
                alert("Sila pilih orang yang disyaki");
                return false;
            }
        }

        return true;
    }

    function test(f) {
        $(f).clearForm();
    }


    function convertText(r,t){
        var i = r.value;
        document.getElementById(t).value=i.toUpperCase();
    }


    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }

    function removeNonNumeric( strString )
    {
        var strValidCharacters = "1234567890";
        var strReturn = "";
        var strBuffer = "";
        var intIndex = 0;
        // Loop through the string
        for( intIndex = 0; intIndex < strString.length; intIndex++ )
        {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                strReturn += strBuffer;
            }
        }
        return strReturn;
    }


    function limitText(limitField, limitNum) {
        if (limitField.value.length > limitNum) {
            limitField.value = limitField.value.substring(0, limitNum);
        }
    }

    function addRow (tableid){

            var table = document.getElementById (tableid);
            var rowcount = table.rows.length;
            var row = table.insertRow(rowcount);
            document.getElementById("recordCount").value =rowcount;
        
        var id="toDate"+(rowcount-1);

            //alert(rowcount);
            var cell1 = row.insertCell(0);
            var e1 = document.createElement("INPUT");
            e1.setAttribute("type","checkbox");
            e1.setAttribute("name","pilih" +(rowcount-1));
        e1.setAttribute("id","pilih" +(rowcount-1));
            cell1.appendChild(e1);

            var cell2 = row.insertCell(1);
            var bil = document.createTextNode(rowcount);
            cell2.appendChild(bil);

            var cell3 = row.insertCell(2);
            var e3 = document.createElement("select");
            e3.setAttribute("name","pilihPembantu"+(rowcount-1));
            e3.setAttribute("id","pilihPembantu" +(rowcount-1));
            e3.setAttribute("style","width:170px;");
        e3.onchange = function(){findPenggunaPasukan(this.value,(rowcount-1));};

        //for "sila pilih"
            var option1 = document.createElement("option");
            option1.text = " Sila pilih ";
            option1.value ="";
            e3.options.add(option1);

    <c:forEach items="${actionBean.senaraiPengguna}" var="line">
            var option2 = document.createElement("option");
            var textVal2=document.createTextNode("${line.nama}");
            option2.appendChild(textVal2);
            option2.setAttribute("value","${line.idPengguna}");
            e3.appendChild(option2);
    </c:forEach>
                
            cell3.appendChild(e3);

            var cell4 = row.insertCell(3);
            var e4 = document.createElement("INPUT");
            e4.setAttribute("type","text");
            e4.setAttribute("name","noPengenalan"+(rowcount-1));
            e4.setAttribute("id","noPengenalan" +(rowcount-1));
            e4.setAttribute("size","13");
            e4.setAttribute("maxLength","12");
            e4.setAttribute("readOnly","true");
            e4.onkeyup = function(){validateNumber(this,this.value,"noPengenalan" +(rowcount-1));};
            cell4.appendChild(e4);

            var cell5 = row.insertCell(4);
            var e5 = document.createElement("INPUT");
            e5.setAttribute("type","text");
            e5.setAttribute("name","jawatan"+(rowcount-1));
            e5.setAttribute("id","jawatan" +(rowcount-1));
            e5.setAttribute("size","37");
            e5.setAttribute("readOnly","true");
            e5.onchange = function(){convertText(this,"jawatan" +(rowcount-1));};
            cell5.appendChild(e5);
            
            var cell6 = row.insertCell(5);
            var e6 = document.createElement("INPUT");
            e6.setAttribute("type","text");
            e6.setAttribute("name","tarikhLantikPembantu"+(rowcount-1));
            e6.setAttribute("id","tarikhLantik"+(rowcount-1));
            e6.setAttribute("formatPattern", "dd/MM/yyyy");
            e6.setAttribute("formatType", "datepicker");
            e6.setAttribute("size", '12');
            e6.onmouseover = function(){setDatepicker(id,rowcount);};
            e6.onfocus = function(){setDatepicker(id,rowcount);};
            cell6.appendChild(e6);
        }
        
        function setDatepicker(id,rowcount){
            $("#tarikhLantik"+(rowcount-1)).datepicker({dateFormat: 'dd/mm/yy'});
        }
    
        function convertText(r,t){
            var i = r.value;
            document.getElementById(t).value=i.toUpperCase();
        }


        function deleteRow(tableid) {
            var rowBil = document.getElementById("recordCount").value;
            var idPegawai;
            try {
                var table = document.getElementById(tableid);
                var rowCount = table.rows.length;
                //alert("delete rowCount :"+rowCount);
                var j=0;
                for(var i=0; i<rowCount; i++) {
                    var row = table.rows[i];
                    var chkbox = row.cells[0].childNodes[0];
                    if(null != chkbox && true == chkbox.checked) {
                        idPegawai = $("#idPelaksanaWaran"+(i+j-1)).val();

                        if(document.getElementById("idPelaksanaWaran"+(i+j-1)) != null){

                            var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_kertas_siasatan?deletePegawai&idPegawai='+idPegawai;

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
            regenerateBill(tableid ,idPegawai);
        }

        function regenerateBill(tableid,id){
            try{
                var table = document.getElementById(tableid);
                var rowCount = table.rows.length;
                document.getElementById("recordCount").value =rowCount-1;

                if(rowCount > 1){
                    for(var i=1;i<rowCount;i++){
                        
                        table.rows[i].cells[0].childNodes[0].name= 'pilih'+(i-1);
                        table.rows[i].cells[0].childNodes[0].id= 'pilih'+(i-1);
                        
                        table.rows[i].cells[1].childNodes[0].data= i;
                        
                        table.rows[i].cells[2].childNodes[0].name= 'pilihPembantu'+(i-1);
                        table.rows[i].cells[2].childNodes[0].id= 'pilihPembantu'+(i-1);
                        
                        table.rows[i].cells[3].childNodes[0].name= 'noPengenalan'+(i-1);
                        table.rows[i].cells[3].childNodes[0].id= 'noPengenalan'+(i-1);
                        
                        table.rows[i].cells[4].childNodes[0].name= 'jawatan'+(i-1);
                        table.rows[i].cells[4].childNodes[0].id= 'jawatan'+(i-1);

                        table.rows[i].cells[5].childNodes[0].name= 'tarikhLantikPembantu'+(i-1);
                        table.rows[i].cells[5].childNodes[0].id= 'tarikhLantikPembantu'+(i-1);
                        
                    }
                }

            }catch(e){
                alert("Error in regenerateBill : "+e);
            }
        }
        
        function findPenggunaPasukan(id,section){
            //check pengguna already selected or not

            //alert("id : "+id); // id pengguna
            var bil =  document.getElementById("recordCount").value;
            var namaKetua = document.getElementById('pilihPengguna').value;

            if(section == 'ketua'){
                for (var i = 0; i < bil; i++){
                    var nama = document.getElementById('pilihPembantu'+i).value;
                    if(id == nama){
                        alert("Pengguna ini telah dipilih. Sila pilih pengguna yang lain.");
                        document.getElementById("noPengenalanKetua").value = '';
                        document.getElementById("jawatanKetua").value = '';
                        document.getElementById("pilihPengguna").value = '';
                        return false;
                    }
                }
            }else{
                if(id == namaKetua){
                    alert("Pengguna ini telah dipilih sebagai ketua. Sila pilih pengguna yang lain.");
                    document.getElementById("pilihPembantu"+section).value = '';
                    document.getElementById("noPengenalan"+section).value = '';
                    document.getElementById("jawatan"+section).value = '';
                    return false;
                }

                for (var i = 0; i < bil; i++){
                    var nama = document.getElementById('pilihPembantu'+i).value;
                    if(i != section){
                        if(id == nama){
                            alert("Pengguna ini telah dipilih. Sila pilih pengguna yang lain.");
                            document.getElementById("pilihPembantu"+section).value = '';
                            document.getElementById("noPengenalan"+section).value = '';
                            document.getElementById("jawatan"+section).value = '';
                            return false;
                        }
                    }
                   
                }

            }

            
            $.get('${pageContext.request.contextPath}/penguatkuasaan/maklumat_kertas_siasatan?findPengguna&id='+id,
            function(data){
                $("#selectedPenggunaDiv").replaceWith($('#selectedPenggunaDiv', $(data)));
                var noPengenalan =  $('#noPengenalanCarian').val();
                var jabatan = $('#jawatanCarian').val();
                var idPengguna = $('#idPenggunaCarian').val();

                if(section == 'ketua'){
                    document.getElementById("noPengenalanKetua").value = noPengenalan;
                    document.getElementById("jawatanKetua").value = jabatan;
                    document.getElementById("pilihPengguna").value = idPengguna;
                }else{
                    var index = section;
                    document.getElementById("noPengenalan"+index).value = noPengenalan;
                    document.getElementById("jawatan"+index).value = jabatan;
                    document.getElementById("pilihPembantu"+index).value = idPengguna;
                }
            }, 'html');
        }
        
        function addPembantu(){
            $.get('${pageContext.request.contextPath}/penguatkuasaan/maklumat_kertas_siasatan?addPegawaiPenyiasatList',
            function(data){
                $("#SecondaryPegawaiDiv").replaceWith($('#SecondaryPegawaiDiv', $(data)));
              
             
            }, 'html');
        }
        
        function checkAvailability(lineIndex,row){
            //alert(lineIndex);
            var bilOp =  ${fn:length(actionBean.listOp)}; //list op
            var bilUncheck = 0;
            var status = false;
            for (var p = 0; p < bilOp; p++){
                var pilih = document.getElementById('pilihCheckBox'+row).checked;
                //alert("pilih : "+pilih);
                if(pilih == true){
                    if(p != lineIndex){ 
                        //disable checkbox at others row (others id op)
                        var listOks = document.getElementById('totalSenaraiOks'+p).value;
                        for (var b = 0; b < listOks; b++){
                            document.getElementById("pilihCheckBox"+p+b).disabled = true;
                        }
                    }
                }else{
                    //for false
                    //alert("false");
                    if(p == lineIndex){
                        //alert("p["+p+"] tak sama dengan ["+lineIndex+"]");
                        var listOks = document.getElementById('totalSenaraiOks'+p).value;
                        for (var b = 0; b < listOks; b++){
                            //alert("status per row : "+document.getElementById("pilihCheckBox"+p+b).checked);
                            if(document.getElementById("pilihCheckBox"+p+b).checked == false || document.getElementById("pilihCheckBox"+p+b).checked == true){
                                bilUncheck++;
                            }
                        } 
                        //alert("bilUncheck :"+bilUncheck);
                        //alert("listOks :"+listOks);
                        if(bilUncheck == listOks){
                            status = true;
                        }
                    }
                }
            }
            
            //alert("status : "+status);
            if(status == true && $("#statusOperation").val() != "edit"){
                for (var j = 0; j < bilOp; j++){
                    var listOks = document.getElementById('totalSenaraiOks'+j).value;
                    for (var k = 0; k < listOks; k++){
                        if(document.getElementById("pilihCheckBox"+j+k).checked == false){
                            //alert("sedia utk di uncheck : "+j+k);
                            document.getElementById("pilihCheckBox"+j+k).disabled = false;
                        }
                         
                    }
                }
            }
        }



 
</script>
<s:form beanclass="etanah.view.penguatkuasaan.MaklumatKertasSiasatanActionBean">
    <s:messages />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Buka Kertas Siasatan
            </legend>
            <div class="content">
                <legend>
                    Maklumat Orang Yang Disyaki
                </legend>
                <div class="content" align="center">
                    <c:set value="1" var="countidMohonBaru"/>
                    <c:set value="0" var="idMohonBaruEachRow"/>
                    <display:table name="${actionBean.listOp}" id="line" class="tablecloth" cellpadding="0" cellspacing="0">

                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column title="Maklumat Laporan Operasi">
                            <table width="20%" cellpadding="1" class="infoOks">
                                <tr>
                                    <td width="20%"><font class="infoLP">Id Operasi :</font></td>
                                    <td width="20%">${line.idOperasi}</td>
                                </tr>
                                <tr>
                                    <td width="20%"><font class="infoLP">Tarikh laporan :</font></td>
                                    <td width="20%">
                                        <fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhOperasi}"/></td>
                                </tr>
                                <tr>
                                    <td width="20%"><font class="infoLP">Masa laporan :</font></td>
                                    <td width="20%">
                                        <fmt:formatDate pattern="hh:mm" value="${line.tarikhOperasi}"/>
                                        <fmt:formatDate pattern="aaa" value="${line.tarikhOperasi}" var="time"/>
                                        <c:if test="${time eq 'AM'}">PAGI</c:if>
                                        <c:if test="${time eq 'PM'}">PETANG</c:if>
                                    </td>
                                </tr>

                            </table>
                        </display:column>
                        <display:column title="Maklumat Orang Disyaki">
                            <input type="hidden" id="totalSenaraiOks${line_rowNum-1}" value="${fn:length(line.senaraiAduanOrangKenaSyak)}">
                            <c:set value="0" var="count"/>
                            <c:if test="${fn:length(line.senaraiAduanOrangKenaSyak) ne 0}">
                                <table width="100%" cellpadding="1" class="infoOks">
                                    <tr>
                                        <th  width="1%" align="center"><b>Bil</b></th>
                                        <th  width="5%" align="center"><b>Pilih</b></th>
                                        <th  width="1%" align="center"><b>Nama</b></th>
                                        <th  width="1%" align="center"><b>ID Kertas Siasatan</b></th>
                                    </tr>

                                    <c:forEach items="${line.senaraiAduanOrangKenaSyak}" var="oks">
                                        <tr>
                                            <td width="5%">${count+1}</td>
                                            <td width="5%">
                                                <input type="checkbox" name="pilihCheckBox${line_rowNum-1}${count}" id="pilihCheckBox${line_rowNum-1}${count}" value="${oks.idOrangKenaSyak}" onclick="checkAvailability('${line_rowNum-1}','${line_rowNum-1}${count}');">
                                                <input type="hidden" id="statusIp${line_rowNum-1}${count}" value="${oks.statusIp}">
                                            </td>
                                            <td width="50%">
                                                ${oks.nama}
                                            </td>
                                            <td width="50%">
                                                ${oks.permohonanAduanOrangKenaSyak.idPermohonan}
                                                <input type="hidden" id="idMohonBaruEachOks${line_rowNum-1}${count}" value="${oks.permohonanAduanOrangKenaSyak.idPermohonan}">
                                            </td>

                                        </tr>
                                        <c:set value="${count+1}" var="count"/>
                                    </c:forEach>
                                    <%--<input type="hidden" id="idMohonBaruEachRow${line_rowNum-1}" value="${idMohonBaruEachRow}">   
                                    <b><font style="color: #003194;font-size: 13px;font-family:Tahoma">Id Permohonan Baru : </font></b>
                                    <c:choose>
                                        <c:when test="${idMohonBaruEachRow eq 0}"></c:when>
                                        <c:otherwise>${idMohonBaruEachRow}</c:otherwise>
                                    </c:choose>
                                    <c:set value="0" var="idMohonBaruEachRow"/>--%>
                                </table>
                            </c:if>                          
                        </display:column>

                    </display:table>
                </div>


                <br>

                <div class="subtitle">
                    <fieldset class="aras1">
                        <legend>
                            Maklumat Pegawai Penyiasat
                        </legend>
                        <br/>
                        <div class="instr-fieldset">
                            <font color="red">PERINGATAN:</font> Sila pilih ketua pegawai penyiasat
                        </div>&nbsp;
                        <div class="content">

                            <p align="center"><label><u>Ketua Pegawai Penyiasat</u></label></p><br/>
                            <p>
                                <label><em>*</em>Senarai Pengguna :</label>
                                <s:select name="pilihPengguna" id="pilihPengguna" onchange="findPenggunaPasukan(this.value,'ketua');">
                                    <s:option value="">Sila Pilih</s:option>
                                    <c:forEach items="${actionBean.senaraiPengguna}" var="line">
                                        <s:option value="${line.idPengguna}">${line.nama} (${line.jawatan})</s:option>
                                    </c:forEach>
                                </s:select>&nbsp;
                            </p>
                            <p>
                                <label>No.pengenalan :</label>
                                <s:text name="noPengenalanKetua" id="noPengenalanKetua" maxlength="12" onkeyup="validateNumber(this,this.value);" readonly="true"/>&nbsp;
                            </p>
                            <p>
                                <label>Jawatan :</label>
                                <s:text name="jawatanKetua" maxlength="150" size="40" id="jawatanKetua" readonly="true"/>&nbsp;
                            </p>
                            <p>
                                <label>Tarikh Lantik:</label>
                                <s:text name="tarikhLantikKetua"  id="tarikhLantikKetua" class="datepicker" formatPattern="dd/MM/yyyy" />&nbsp;
                                <font color="red" size="1">cth : hh / bb / tttt</font>
                            </p>


                            <div id="selectedPenggunaDiv" style="visibility: hidden; display:none ">
                                <!--<div id="selectedPenggunaDiv">-->
                                <s:text name="noPengenalanCarian" id="noPengenalanCarian"/>
                                <s:text name="jawatanCarian" id="jawatanCarian"/>
                                <s:text name="idPenggunaCarian" id="idPenggunaCarian"/>
                            </div>

                            <p>
                            <fieldset class="aras2">
                                <legend>
                                    Pasukan
                                </legend>
                                <div class="instr-fieldset">
                                    <br><font color="red">PERINGATAN:</font> Sila klik tambah untuk menambah pembantu pegawai penyiasat.

                                </div>

                                <div align="center" >

                                    <p>
                                        <s:hidden name="recordCount" id="recordCount"/>
                                    </p>

                                    <table  width="80%" id="tbl" class="tablecloth" align="center">
                                        <tr>
                                            <th  width="1%" align="center"><b>Pilih</b></th>
                                            <th  width="1%" align="center"><b>Bil</b></th>
                                            <th  width="5%" align="center"><b>Nama</b></th>
                                            <th  width="5%" align="center"><b>No.Pengenalan</b></th>
                                            <th  width="8%" align="center"><b>Jawatan</b></th>
                                            <th  width="15%" align="center"><b>Tarikh Lantik</b></th>

                                        </tr>
                                        <c:set value="0" var="i"/>
                                        <c:forEach items="${actionBean.senaraiPembantuPegawaiPenyiasat}" var="line">
                                            <tr style="font-weight:bold">
                                                <td><s:checkbox name="pilih${i}" id="pilih${i}" /> </td>
                                                <td><c:out value="${i+1}"/></td>
                                                <td>
                                                    <s:select name="pilihPembantu${i}" id="pilihPembantu${i}" value="${line.namaJabatan}">
                                                        <s:option value="">Sila Pilih</s:option>
                                                        <s:options-collection collection="${actionBean.senaraiPengguna}" label="nama" value="idPengguna" sort="nama" />
                                                    </s:select>
                                                </td>
                                                <td>${line.noPengenalan}
                                                    <input type="hidden" id="noPengenalan${i}" name="noPengenalan${i}" value="${line.noPengenalan}">
                                                </td>
                                                <td>${line.jawatan}
                                                    <input type="hidden" id="jawatan${i}" name="jawatan${i}" value="${line.jawatan}">
                                                </td>
                                                <td>
                                                    <s:text name="tarikhLantikPembantu${i}" id="tarikhLantikPembantu${i}" value="${line.tarikhLantikan}" size="12" class="datepicker" formatPattern="dd/MM/yyyy"/>&nbsp;    
                                                </td>

                                                <s:hidden name="idPelaksanaWaran${i}" id="idPelaksanaWaran${i}" value="${line.idPelaksanaWaran}" />

                                            </tr>
                                            <c:set var="i" value="${i+1}" />
                                        </c:forEach>


                                    </table>

                                    <br/>
                                    <p align="center"><em>**</em> Sila tanda di petak Pilih untuk hapus.</p>

                                    <table width="80%">
                                        <tr><td align="center">

                                                <s:button class="btn" value="Tambah" name="add" onclick="addRow('tbl')" />
                                                <s:button class="btn" value="Hapus" name="delete" onclick="deleteRow('tbl')"/>
                                    </table>

                                </div>
                                <p>&nbsp;</p>
                            </fieldset>
                            <br/>
                            <p align="right">
                                <s:button  name="reset" value="Isi Semula" class="btn" onclick="return test();"/>
                                <s:button class="btn" onclick="if(validateForm())save(this.name,this.form);" name="simpan" value="Simpan"/>
                                <s:submit class="btn"  value="Tutup" name="closePopup" onclick="self.close();" />
                                <s:hidden name="idMohonBaru" id="idMohonBaru"/>
                                <input type="hidden" name="statusOperation" id="statusOperation" value="">

                            </p>

                        </div>
                    </fieldset>
                </div>




            </div>
            <br/><br/>

        </fieldset>
    </div>

</s:form>

