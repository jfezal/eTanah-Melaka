<%-- 
    Document   : popup_pembelian_barang
    Created on : March 15, 2014, 11:24:21 AM
    Author     : latifah.iskak
--%>


<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<style type="text/css">

    .tablecloth{
        padding:0px;
        width:90%;
    }

    .infoLP{
        float: right;
        font-weight: bold;
        color:#003194;
        font-family:Tahoma;
        font-size: 13px;

    }
</style>
<script type="text/javascript">
    $(document).ready( function(){
        var bil =  ${fn:length(actionBean.senaraiPembelian)};
        for (var i = 0; i < bil; i++){
            returnCurrency($("#nilaiJualan"+i).val(),i);
        }
        
        jenisPengenalan();
    <c:if test="${editPembelian}">
            totalAmount();
    </c:if>

        });

        function save(event, f){
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
                $('#page_div',opener.document).html(data);
                self.opener.refreshPageBarangOperasi();
                self.close();
            },'html');

        }

        function validateForm(){
               
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
    
        function returnCurrency(amount, i){
            //        alert(i);
            document.getElementById('nilaiJualan'+i).value = CurrencyFormatted(amount);
            //        totalAmount();
        }
    
     
        function returnCurrency1(amount){
            alert(amount);
            document.getElementById('jumCaraBayar').value = CurrencyFormatted(amount);
        }

        function CurrencyFormatted(amount)
        {
            var i = parseFloat(amount);
            if(isNaN(i)) { i = 0.00; }
            var minus = '';
            if(i < 0) { minus = '-'; }
            i = Math.abs(i);
            i = parseInt((i + .005) * 100);
            i = i / 100;
            s = new String(i);
            if(s.indexOf('.') < 0) { s += '.00'; }
            if(s.indexOf('.') == (s.length - 2)) { s += '0'; }
            s = minus + s;
            return s;
        }
    
    
        function validatePoskodLength(value){
            var plength = value.length;
            if(plength != 5){
                alert('Poskod yang dimasukkan salah.');
                $('#poskod').val("");
                $('#poskod').focus();
            }
        }
    
        function jenisPengenalan(){
            if($('#pengenalan').val() == 'B'){
                document.getElementById("noPengenalanBaru").style.visibility = 'visible';
                document.getElementById("noPengenalanBaru").style.display = '';
                $('#noPengenalanLain').hide();

            }else{
                $('#noPengenalanLain').show();
                document.getElementById("noPengenalanBaru").style.visibility = 'hidden';
                document.getElementById("noPengenalanBaru").style.display = 'none';

            }
        }
    
        function totalAmount(){
            var total = 0;
            var bil = ${fn:length(actionBean.senaraiJualanBarangPemohon)};
            for (var i = 0; i < bil; i++){
                var amount = document.getElementById('nilaiJualan'+i).value;
                if(amount !=""){
                    total += parseFloat(amount);
    <%--alert("total : "+total);--%>
                    document.getElementById('jumCaraBayar').value=total;
                }
            }
            
            returnCurrency1(document.getElementById('jumCaraBayar').value);
        }
        
        function totalAmountEdit(){
            var total = 0;
            var bil = document.getElementById("recordCount").value;
            for (var i = 0; i < bil; i++){
                var amount = document.getElementById('nilaiJualan'+i).value;
                if(amount !=""){
                    total += parseFloat(amount);
    <%--alert("total : "+total);--%>
                    document.getElementById('jumCaraBayar').value=total;
                }
            }
            
            returnCurrency1(document.getElementById('jumCaraBayar').value);
        }
        
        function validateKPLength(value){
            var plength = value.length;
            if(plength != 12){
                alert('Kad Pengenalan Baru yang dimasukkan salah.');
                $('#noPengenalanBaru').val("");
                $('#noPengenalanBaru').focus();
            }
        }
        
        function addRow (tableid){

                var table = document.getElementById (tableid);
                var rowcount = table.rows.length;
                var row = table.insertRow(rowcount);
                document.getElementById("recordCount").value =rowcount;
        
               var cellPilih = row.insertCell(0);
                var ePilih = document.createElement("INPUT");
                ePilih.setAttribute("type","checkbox");
                ePilih.setAttribute("name","pilih" +(rowcount-1));
            ePilih.setAttribute("id","pilih" +(rowcount-1));
                cellPilih.appendChild(ePilih);
            
                var cell1 = row.insertCell(1);
                var bil = document.createTextNode(rowcount);
                cell1.appendChild(bil);

                var cell2 = row.insertCell(2);
                var e1 = document.createElement("select");
                e1.setAttribute("name","pilihBarang"+(rowcount-1));
                e1.setAttribute("id","pilihBarang" +(rowcount-1));
                e1.setAttribute("style","width:170px;");
            e1.onchange = function(){findBarang(this.value,(rowcount-1));};

            //for "sila pilih"
                var option1 = document.createElement("option");
                option1.text = " Sila pilih ";
                option1.value ="";
                e1.options.add(option1);
            
    <c:forEach items="${actionBean.listOp}" var="l">
        <c:forEach items="${l.senaraiBarangRampasan}" var="line">
                var option2 = document.createElement("option");
                var textVal2=document.createTextNode("${line.item}");
                option2.appendChild(textVal2);
                option2.setAttribute("value","${line.idBarang}");
                e1.appendChild(option2);
        </c:forEach>
    </c:forEach>
    
                
            cell2.appendChild(e1);

            var cell3 = row.insertCell(3);
            var e2 = document.createElement("INPUT");
            e2.setAttribute("type","text");
            e2.setAttribute("name","nilaiJualan"+(rowcount-1));
            e2.setAttribute("id","nilaiJualan" +(rowcount-1));
            e2.setAttribute("size","13");
            e2.onkeyup = function(){validateNumber(this,this.value,"nilaiJualan" +(rowcount-1));};
            e2.onblur = function(){returnCurrency1(this.value,(rowcount-1));};
            cell3.appendChild(e2);

            var cell4 = row.insertCell(4);
            
            var e4 = document.createElement("INPUT");
            e4.setAttribute("type","text");
            e4.setAttribute("name","kuantitiBarang"+(rowcount-1));
            e4.setAttribute("id","kuantitiBarang" +(rowcount-1));
            e4.setAttribute("size","5");
            e4.onkeyup = function(){validateNumber(this,this.value,"kuantitiBarang" +(rowcount-1));};
            e4.onblur = function(){checkAvailable(this.value,(rowcount-1));};
            cell4.appendChild(e4);
            
            var e5 = document.createElement("select");
             e5.setAttribute("name","kuantitiUnit"+(rowcount-1));
            e5.setAttribute("id","kuantitiUnit" +(rowcount-1));
            
            //for "sila pilih"
            var option5 = document.createElement("option");
            option5.text = " Sila pilih ";
            option5.value ="";
            e5.options.add(option5);

    <c:forEach items="${listUtil.kodUOMByJenis}" var="line">
            var option3 = document.createElement("option");
            var textVal2=document.createTextNode("${line.nama}");
            option3.appendChild(textVal2);
            option3.setAttribute("value","${line.kod}");
            e5.appendChild(option3);
    </c:forEach>
        
            cell4.appendChild(e5);
            
            var cellCatatan = row.insertCell(5);
            var eCatatan = document.createElement("textarea");
            eCatatan.t = "catatan"+(rowcount-1);
            eCatatan.rows = 3;
            eCatatan.cols = 30;
            eCatatan.name ="catatan"+(rowcount-1);
            eCatatan.id ="catatan"+(rowcount-1);
            cellCatatan.appendChild(eCatatan);
            
            var cell4 = row.insertCell(6);
            var e5 = document.createElement("div");
            e5.setAttribute("name","divStatus"+(rowcount-1));
            e5.setAttribute("id","divStatus" +(rowcount-1));
            
            var e6 = document.createElement("INPUT");
            e6.setAttribute("type","hidden");
            e6.setAttribute("name","jumAsal"+(rowcount-1));
            e6.setAttribute("id","jumAsal" +(rowcount-1));
            
            var e7 = document.createElement("INPUT");
            e7.setAttribute("type","hidden");
            e7.setAttribute("name","jumSelepas"+(rowcount-1));
            e7.setAttribute("id","jumSelepas" +(rowcount-1));
            
            cell4.appendChild(e5);
            cell4.appendChild(e6);
            cell4.appendChild(e7);
           

    
    
    
        }

        function findBarang(value, row){
            
            $.get('${pageContext.request.contextPath}/penguatkuasaan/pembeli_barang_rampasan?findBarang&id='+value,
            function(data){
                $("#selectedBarangDiv").replaceWith($('#selectedBarangDiv', $(data)));
                $("#divStatus"+row).html("Baki barang : "+ $('#statusBarang').val());
                
                document.getElementById("jumAsal"+row).value = $('#jum').val();
                document.getElementById("jumSelepas"+row).value = $('#statusBarang').val();
                
                document.getElementById("kuantitiUnit"+row).value = $('#unitBarang').val();
                
                
            }, 'html');
     
        }
        
        function checkAvailable(value, row){
            var s = document.getElementById("jumSelepas"+row).value;
            if(parseInt(value) > parseInt(s)){
                alert("Sila masukkan jumlah yang lebih kecil dari baki iaitu "+s);
                document.getElementById("kuantitiBarang"+row).value = '';
                $('#kuantitiBarang').focus();
            }
            
        }
        
        function removeMaklumatBarang(id){
            if(confirm('Adakah anda pasti untuk hapus?')) {
                var url = '${pageContext.request.contextPath}/penguatkuasaan/pembeli_barang_rampasan?deleteBarang&id='+id;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');}
        }
        
        function deleteRow(tableid) {
            alert("tableid : "+tableid);
            var idBarangJual;
            try {
                var table = document.getElementById(tableid);
                var rowCount = table.rows.length;
                alert("rowCount : "+rowCount);
                var j=0;
                for(var i=0; i<rowCount; i++) {
                    var row = table.rows[i];
                    alert("rowuu : "+row.cells[0]);
                    var chkbox = row.cells[0].childNodes[0];
                    alert("chkbox1 : "+chkbox.checked);
                    if(null != chkbox && true == chkbox.checked) {
                        alert("chkbox : "+chkbox);
                        idBarangJual = $("#idBarangJual"+(i+j-1)).val();

                        if(document.getElementById("idBarangJual"+(i+j-1)) != null){

                            var url = '${pageContext.request.contextPath}/penguatkuasaan/pembeli_barang_rampasan?deleteBarang&id='
                                +idBarangJual;

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

 
</script>
<s:form beanclass="etanah.view.penguatkuasaan.PembelianBarangRampasanActionBean">
    <s:messages />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Pembeli
            </legend>

            <!--<div id="selectedBarangDiv" style="visibility: hidden; display:none ">-->
            <div id="selectedBarangDiv">
                <s:hidden name="unitBarang" id="unitBarang"/>
                <s:hidden name="statusBarang" id="statusBarang"/>
                <input type="hidden" id="jum" value="${actionBean.barang.kuantiti}">

            </div>

            <c:if test="${edit}">
                <div class="content">
                    <p>
                        <label>Nama  :</label>
                        <s:text name="namaPembeli" id="namaPembeli" size="40" maxlength="50"/>&nbsp;
                    </p>
                    <p>
                        <label>Jenis Pengenalan :</label>
                        <s:select name="kodPengenalanPembeli.kod"  value="${actionBean.permohonan.kodPengenalanPenerima.kod}"  style="width:139px;" id="pengenalan" onchange="jenisPengenalan()">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod" sort="nama" />
                        </s:select>
                        &nbsp;
                    </p>
                    <p id="noPengenalanLain">
                        <label>No.Pengenalan :</label>
                        <s:text name="noPengenalanPembeliLain" id="noPengenalanPenerimaLain" maxlength="20" onkeyup="this.value=this.value.toUpperCase();" />
                        &nbsp;
                    </p>
                    <p id="noPengenalanBaru" style="visibility: hidden; display: none">
                        <label>No.Pengenalan :</label>
                        <s:text name="noPengenalanPembeliBaru" id="noPengenalanPenerimaBaru" maxlength="12" onkeyup="validateNumber(this,this.value);" onchange="validateKPLength(this.value);"/>
                        <font color="red" size="1">cth : 850510075342 </font>
                        &nbsp;
                    </p>
                    <p>
                        <label>Alamat :</label>
                        <s:text name="alamatPembeli1"  size="30" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label> &nbsp;</label>
                        <s:text name="alamatPembeli2"  size="30" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>

                    </p>
                    <p>
                        <label> &nbsp;</label>
                        <s:text name="alamatPembeli3"  size="30" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>

                    </p>
                    <p>
                        <label> &nbsp;</label>
                        <s:text name="alamatPembeli4"  size="30" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>

                        &nbsp;
                    </p>
                    <p>
                        <label>Poskod :</label>
                        <s:text name="poskodPembeli" id="poskod" size="19" maxlength="5" onkeyup="validateNumber(this,this.value);" onchange="validatePoskodLength(this.value);"/>

                    </p>
                    <p>
                        <label>Negeri :</label>
                        <s:select name="kodNegeriPembeli.kod"  style="width:139px;">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" sort="nama" />
                        </s:select>
                    </p>
                    <br>
                    <legend>
                        Barang Jualan
                    </legend>
                    <div align="center" >

                        <p>
                            <s:hidden name="recordCount" id="recordCount"/>
                        </p>

                        <table  width="80%" id="tbl" class="tablecloth" align="center">

                            <tr>
                                <th  width="1%" align="center"><b>Bil</b></th>
                                <th  width="1%" align="center"><b>Pilih</b></th>
                                <th  width="3%" align="center"><b>Barang</b></th>
                                <th  width="1%" align="center"><b>Amaun</b></th>
                                <th  width="3%" align="center"><b>Kuantiti</b></th>
                                <th  width="1%" align="center"><b>Catatan</b></th>
                                <th  width="1%" align="center"><b>Status Barang</b></th>
                            </tr>


                            <c:set value="0" var="i"/>



                        </table>

                        <br/>
                        <p align="center"><em>**</em> Sila tanda di petak Pilih untuk hapus.</p>

                        <table width="80%">
                            <tr><td align="center">

                                    <s:button class="btn" value="Tambah" name="add" onclick="addRow('tbl')" />
                                    <s:button class="btn" value="Hapus" name="delete" onclick="deleteRow('tbl')"/>
                        </table>

                    </div>


                </div>
                <p align="center">
                    <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
                    <s:hidden name="idOp"/>
                    <s:hidden name="idBarangOperasi"/>
                    <s:button  name="reset" value="Isi Semula" class="btn" onclick="return test();"/>
                    <s:button class="btn" onclick="if(validateForm())save(this.name,this.form);" name="simpan" value="Simpan"/>
                    <s:submit class="btn"  value="Tutup" name="closePopup" onclick="self.close();" />
                </p>
                <br/><br/>
            </c:if>

            <c:if test="${editPembelian}">
                <input type="hidden" id="idPemohon" name="idPemohon" value="${actionBean.pemohon.idPemohon}">
                <div class="content">
                    <p>
                        <label>Nama  :</label>
                        <s:text name="namaPembeli" id="namaPembeli" size="40" maxlength="50"/>&nbsp;
                    </p>
                    <p>
                        <label>Jenis Pengenalan :</label>
                        <s:select name="kodPengenalanPembeli.kod"  value="${actionBean.permohonan.kodPengenalanPenerima.kod}"  style="width:139px;" id="pengenalan" onchange="jenisPengenalan()">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod" sort="nama" />
                        </s:select>
                        &nbsp;
                    </p>
                    <p id="noPengenalanLain">
                        <label>No.Pengenalan :</label>
                        <s:text name="noPengenalanPembeliLain" id="noPengenalanPenerimaLain" maxlength="20" onkeyup="this.value=this.value.toUpperCase();" />
                        &nbsp;
                    </p>
                    <p id="noPengenalanBaru" style="visibility: hidden; display: none">
                        <label>No.Pengenalan :</label>
                        <s:text name="noPengenalanPembeliBaru" id="noPengenalanPenerimaBaru" maxlength="12" onkeyup="validateNumber(this,this.value);" onchange="validateKPLength(this.value);"/>
                        <font color="red" size="1">cth : 850510075342 </font>
                        &nbsp;
                    </p>
                    <p>
                        <label>Alamat :</label>
                        <s:text name="alamatPembeli1"  size="30" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label> &nbsp;</label>
                        <s:text name="alamatPembeli2"  size="30" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>

                    </p>
                    <p>
                        <label> &nbsp;</label>
                        <s:text name="alamatPembeli3"  size="30" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>

                    </p>
                    <p>
                        <label> &nbsp;</label>
                        <s:text name="alamatPembeli4"  size="30" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>

                        &nbsp;
                    </p>
                    <p>
                        <label>Poskod :</label>
                        <s:text name="poskodPembeli" id="poskod" size="19" maxlength="5" onkeyup="validateNumber(this,this.value);" onchange="validatePoskodLength(this.value);"/>

                    </p>
                    <p>
                        <label>Negeri :</label>
                        <s:select name="kodNegeriPembeli.kod"  style="width:139px;">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" sort="nama" />
                        </s:select>
                    </p>
                    <br>
                    <legend>
                        Barang Jualan
                    </legend>
                    <s:text name="recordCount" id="recordCount"/>
                    <div class="content" align="center">
                        <display:table name="${actionBean.senaraiJualanBarangPemohon}" id="line" class="tablecloth" cellpadding="0" cellspacing="0">
                            <display:column title="Pilih">
                                <s:checkbox name="pilih${line_rowNum-1}" id="pilih${line_rowNum-1}" />
                                <input type="hidden" id="idBarangJual${line_rowNum-1}" value="${line.idBarangJual}" name="idBarangJual${line_rowNum-1}"/>
                            </display:column>
                            <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                            <display:column title="Barang">
                                <c:forEach items="${actionBean.listOp}" var="l">
                                    <c:forEach items="${l.senaraiBarangRampasan}" var="br">
                                        <c:if test="${br.idBarang == line.rampasan.idBarang}">
                                            ${br.item}<br>
                                        </c:if>
                                    </c:forEach>
                                </c:forEach>
                            </display:column>
                            <display:column title="Amaun(RM)">
                                <input id="nilaiJualan${line_rowNum-1}" size="10" value="${line.amaun}" name="nilaiJualan${line_rowNum-1}" formatPattern="#,##0.00" onblur="totalAmountEdit()" onkeyup="validateNumber(this,this.value);"/>&nbsp;
                            </display:column>
                            <display:column title="Kuantiti & Unit">
                                <input id="kuantitiBarang${line_rowNum-1}" value="${line.kuantiti}" name="kuantitiBarang${line_rowNum-1}" onkeyup="validateNumber(this,this.value);" size="10" />&nbsp;
                                <s:select name="kuantitiUnit${line_rowNum-1}" id="kuantitiUnit${line_rowNum-1}" value="${line.unitKuantiti.kod}">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${listUtil.kodUOMByJenis}" label="nama" value="kod" sort="nama" />
                                </s:select>&nbsp;
                            </display:column>
                            <display:column title="Catatan">
                                <s:textarea name="catatan${line_rowNum-1}" cols="30" rows="5" class="normal_text" value="${line.catatan}"/>
                            </display:column>
                            <display:column title="Status Barang">

                            </display:column>
                        </display:table>
                        <br> <b>Jumlah (RM)
                            <s:text name="jumCaraBayar" value="0.00" id="jumCaraBayar" size="12" formatPattern="0.00"
                                    class="number" readonly="readonly" style="background:transparent;border:0px;"/> </b>
                    </div>


                </div>
                <p align="center"><em>**</em> Sila tanda di petak Pilih untuk hapus.<br/>
                    <s:button class="btn" value="Tambah" name="add" onclick="addRow('line')" />
                    <s:button class="btn" value="Hapus" name="delete" onclick="deleteRow('line')"/>
                </p>
                <br/>
                <p align="center">
                    <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
                    <s:button  name="reset" value="Isi Semula" class="btn" onclick="return test();"/>
                    <s:button class="btn" onclick="if(validateForm())save(this.name,this.form);" name="editBarangBelian" value="Simpan"/>
                    <s:submit class="btn"  value="Tutup" name="closePopup" onclick="self.close();" />
                </p>
                <br/><br/>
            </c:if>

            <c:if test="${view}">
                <div class="content">

                </div>
            </c:if>

        </fieldset>
    </div>


</s:form>

