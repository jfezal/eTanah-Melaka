<%-- 
    Document   : kemasukan_laporan_operasi
    Created on : Aug 02, 2011, 4:14:05 PM
    Author     : latifah.iskak
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript">
    function validateForm(){
        
        if($('#datepicker').val()=="")
        {
            alert('Sila isikan Tarikh Laporan terlebih dahulu');
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
            alert('Sila pilih am atau pm pada bahagian masa laporan');
            $('#ampm').focus();
            return false;
        }
        if($('#lokasi').val()=="")
        {
            alert('Sila isikan Kawasan Rondaan terlebih dahulu');
            $('#lokasi').focus();
            return false;
        }
        if($('#catatan').val()=="")
        {
            alert('Sila isikan Laporan Operasi terlebih dahulu');
            $('#catatan').focus();
            return false;
        }
        if($('#noPengenalanKetua').val()=="")
        {
            alert('Sila isikan no.pengenalan ketua terlebih dahulu');
            $('#noPengenalanKetua').focus();
            return false;
        }
        if($('#namaKetuaDisabled').val()=="")
        {
            alert('Sila isikan nama ketua terlebih dahulu');
            $('#namaKetuaDisabled').focus();
            return false;
        }
        
        var bil =  document.getElementById("recordCount").value;

        for (var i = 0; i < bil; i++){
           
            var nama = document.getElementById('nama'+i).value;
            var peranan = document.getElementById('peranan'+i).value;
            var noKp = document.getElementById('noKp'+i).value;

            if(peranan == "" ){
                alert("Sila pilih peranan");
                $('#peranan'+i).focus();
                return false;
            }

            if(noKp == "" ){
                alert("Sila Masukkan No.Pengenalan");
                $('#noKp'+i).focus();
                return false;
            }
            if(nama == "" ){
                alert("Sila Masukkan Nama");
                $('#nama'+i).focus();
                return false;
            }
           
            
        }

        return true;
    }
    function test(f) {
        $(f).clearForm();
    }
    
    function muatNaikForm1(folderId, idPermohonan, dokumenKod, idOperasi) {
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/2)-(150/2);
        var url = '${pageContext.request.contextPath}/penguatkuasaan/upload?muatNaikForm1&folderId=' + folderId
            + '&idPermohonan=' + idPermohonan + '&dokumenKod=' + dokumenKod + '&idOperasi=' + idOperasi;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
    }
    function doViewReport(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }

    function removeImej(idImej) {
        var url = '${pageContext.request.contextPath}/penguatkuasaan/laporan_operasi_polis?deleteSelected&idImej='+idImej;
        $.get(url,
        function(data){
            $('#page_div').html(data);
            refreshPage();
        },'html');
    }
    function refreshPage(){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/laporan_operasi_polis?refreshpage';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function addRow (tableid){

            var table = document.getElementById (tableid);
            var rowcount = table.rows.length;
            var row = table.insertRow(rowcount);
            document.getElementById("recordCount").value =rowcount;

            //alert(rowcount);
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
             e3.setAttribute("name","peranan"+(rowcount-1));
             e3.setAttribute("id","peranan" +(rowcount-1));
             e3.setAttribute("style","width:210px;");

        //for "sila pilih"
            var option1 = document.createElement("option");
            option1.text = " Sila pilih ";
            option1.value ="";
            e3.options.add(option1);

    <c:forEach items="${listUtil.senaraiKodPerananOperasi}" var="line">
            var option2 = document.createElement("option");
            var textVal2=document.createTextNode("${line.nama}");
            option2.appendChild(textVal2);
            option2.setAttribute("value","${line.kod}");
            e3.appendChild(option2);
    </c:forEach>
                
            cell3.appendChild(e3);

            var cell4 = row.insertCell(3);
            var e7 = document.createElement("EM");
            var text1 = document.createTextNode("*");
            e7.appendChild(text1);
            var e4 = document.createElement("INPUT");
            e4.setAttribute("type","text");
            e4.setAttribute("name","noKp"+(rowcount-1));
            e4.setAttribute("id","noKp" +(rowcount-1));
            e4.setAttribute("size","20");
            e4.setAttribute("maxLength","14");
            e4.onkeyup = function(){validateNumber(this,this.value,"noKp" +(rowcount-1));};
            cell4.appendChild(e7);
            cell4.appendChild(e4);

            var cell5 = row.insertCell(4);
            var e6 = document.createElement("EM");
            var text = document.createTextNode("*");
            e6.appendChild(text);
            var e5 = document.createElement("INPUT");
            e5.setAttribute("type","text");
            e5.setAttribute("name","nama"+(rowcount-1));
            e5.setAttribute("id","nama" +(rowcount-1));
            e5.setAttribute("size","25");
            e5.onchange = function(){convertText(this,"nama" +(rowcount-1));};
            cell5.appendChild(e6);
            cell5.appendChild(e5);

            var cell6 = row.insertCell(5);
            var e8 = document.createElement("INPUT");
            e8.setAttribute("type","text");
            e8.setAttribute("name","kadKuasa"+(rowcount-1));
            e8.setAttribute("id","kadKuasa" +(rowcount-1));
            e8.setAttribute("size","15");
            e8.setAttribute("maxLength","10");
            e8.onchange = function(){convertText(this,"kadKuasa" +(rowcount-1));};
            cell6.appendChild(e8);

        }
    
        function convertText(r,t){
            var i = r.value;
            document.getElementById(t).value=i.toUpperCase();
        }


        function deleteRow(tableid) {
            var idOperasiPenguatkuasaanPasukan;
            try {
                var table = document.getElementById(tableid);
                var rowCount = table.rows.length;
                var j=0;
                for(var i=0; i<rowCount; i++) {
                    var row = table.rows[i];
                    var chkbox = row.cells[0].childNodes[0];
                    if(null != chkbox && true == chkbox.checked) {
                        idOperasiPenguatkuasaanPasukan = $("#idOperasiPenguatkuasaanPasukan"+(i+j-1)).val();

                        if(document.getElementById("idOperasiPenguatkuasaanPasukan"+(i+j-1)) != null){

                            var url = '${pageContext.request.contextPath}/penguatkuasaan/laporan_operasi_polis?deletePenguatkuasaanPasukan&idOperasiPenguatkuasaanPasukan='
                                +idOperasiPenguatkuasaanPasukan;

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

        function findNoPengenalan()
        {
            var ic = document.getElementById("noPengenalanKetua").value;
            if(ic == ""){
                alert("Sila masukkan no.pengenalan dahulu.");
                $('#noPengenalanKetua').focus();
                return false;

            }
            if(ic != ""){
                $.get('${pageContext.request.contextPath}/penguatkuasaan/laporan_operasi_polis?findNoPengenalan&noPengenalan='+ic,
                function(data){
                    if(data != "NotExist" && data != "Duplicate"){
                        document.getElementById("namaKetua").value = data;
                        document.getElementById("namaKetuaDisabled").value = data;
                    }
                    if(data == "NotExist"){
                        alert("No.pengenalan ini tidak wujud. Sila masukkan no.pengenalan yang betul");
                        document.getElementById("namaKetua").value = "";
                        document.getElementById("namaKetuaDisabled").value = "";
                    }
                    if(data == "Duplicate"){
                        alert("No.pengenalan ini tidak unik. Sila masukkan no.pengenalan yang unik");
                        document.getElementById("namaKetua").value = "";
                        document.getElementById("namaKetuaDisabled").value = "";
                    }

                }, 'html');

            }
        }


        function addPopupForm(sel){
            var agensiValue = sel.options[sel.selectedIndex].text;
            var agensiId = sel.options[sel.selectedIndex].value;
            if(agensiId == ""){
                alert("Sila pilih agensi yang hendak ditambah");
                $('#agen').focus();
                return false;
            }

            var bil =  ${fn:length(actionBean.senaraiAgensi)};

            for (var i = 1; i <= bil; i++){

                var kod = document.getElementById('kodAgensi'+i).value;

                if(agensiId == kod ){
                    alert("Agensi ini telah wujud di dalam senarai anda. Sili pilih agensi yang lain");
                    $('#agen'+i).focus();
                    return false;
                }
            }
            window.open("${pageContext.request.contextPath}/penguatkuasaan/kemasukan_laporan_operasi?addAgensi&idAgensi="+agensiId+"&namaAgensi="+agensiValue, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=910,height=500,scrollbars=yes");
        }

        function refreshPageKemasukanLaporanOperasi(){
            var url = '${pageContext.request.contextPath}/penguatkuasaan/kemasukan_laporan_operasi?refreshPage';
            $.get(url,
            function(data){
                $("#agensiDiv").replaceWith($('#agensiDiv', $(data)));
            },'html');

        }
        

        function editPopupForm(id,idAgen){
            window.open("${pageContext.request.contextPath}/penguatkuasaan/kemasukan_laporan_operasi?editPopupAgensi&id="+id+"&idOpsAgen="+idAgen, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=910,height=600,scrollbars=yes");
        }

        function removeAgensi(id,idAgen){
            window.open("${pageContext.request.contextPath}/penguatkuasaan/kemasukan_laporan_operasi?removeAgensi&id="+id+"&idOpsAgen="+idAgen, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=910,height=600,scrollbars=yes");
        }

        function removeAgensi(id,idAgen){
            if(confirm('Adakah anda pasti untuk hapus?')) {
                $.get('${pageContext.request.contextPath}/penguatkuasaan/kemasukan_laporan_operasi?removeAgensi&id='+id+"&idOpsAgen="+idAgen,
                function(html){
                    $("#agensiDiv").replaceWith($('#agensiDiv', $(html)));
                }, 'html');
            }
        }

</script>
<s:form beanclass="etanah.view.penguatkuasaan.KemasukanLaporanOperasi" name="form1">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Laporan Operasi
            </legend>
            <div class="content" >
                <p>
                    <label>Jenis Operasi:</label>
                    <s:textarea name="jenisTindakan" id="jenisTindakan" cols="80" rows="5" onchange="this.value=this.value.toUpperCase();" />&nbsp;
                </p>

                <p>
                    <label><em>*</em> Tarikh Operasi:</label>
                    <s:text name="tarikhOperasi" class="datepicker" formatType="date" formatPattern="dd/MM/yyyy" id="datepicker" />&nbsp;
                    <font color="red" size="1">cth : hh / bb / tttt</font>
                </p>
                <p>
                    <label><em>*</em>Masa Operasi:</label>
                    <s:select name="jam" id="jam">
                        <s:option value=""> Jam </s:option>
                        <c:forEach var="jam" begin="1" end="12">
                            <s:option value="${jam}">${jam}</s:option>
                        </c:forEach>
                    </s:select>
                    <s:select name="minit" id="minit">
                        <s:option value=""> Minit </s:option>
                        <c:forEach var="minit" begin="00" end="59">
                            <s:option value="${minit}">${minit}</s:option>
                        </c:forEach>
                    </s:select>
                    <s:select name="ampm" id="ampm" style="width:80px">
                        <s:option value="">Pilih</s:option>
                        <s:option value="AM">PAGI</s:option>
                        <s:option value="PM">PETANG</s:option>
                    </s:select>
                </p>
                <p>
                    <label><em>*</em> Kawasan Operasi:</label>
                    <s:text name="lokasi" id="lokasi" size="33" onchange="this.value=this.value.toUpperCase();"/>&nbsp;
                    <font color="red" size="1">cth : Balakong </font>
                </p>

                <p>
                    <label>Kenderaan Operasi:</label>
                    <s:text name="platKenderaan" id="platKenderaan" size="33" onchange="this.value=this.value.toUpperCase();"/>&nbsp;
                    <font color="red" size="1">cth : WDU 2011 </font>
                </p>

                <p>
                    <label>Jumlah Tangkapan :</label>
                    <s:text name="jumlahTangkapan" id="jumlahTangkapan" size="33" onkeyup="validateNumber(this,this.value);"/>&nbsp;
                    <font color="red" size="1">cth : 3 Orang </font>
                </p>

                <p>
                    <label><em>*</em> Laporan Operasi :</label>
                    <s:textarea name="catatan" id="catatan" rows="7" cols="80" onchange="this.value=this.value.toUpperCase();" />&nbsp;
                </p>

            </div>
        </fieldset>

    </div>

    <div class="subtitle">
        <div id="agensiDiv">
            <fieldset class="aras1">
                <legend>
                    Maklumat Agensi Yang Terlibat
                </legend>
                <div class="instr-fieldset">
                    <font color="red">PERINGATAN:</font> Sila pilih agensi yang dikehendaki.
                </div>&nbsp;

                <table>
                    <tr><td width="50%"><label> Nama Agensi : </label></td><td>
                            <s:select name="agensi" id="agen">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${listUtil.senaraiAgensi}" label="nama" value="kod" sort="nama" />
                            </s:select>

                            <s:button class="btn" onclick="addPopupForm(this.form.agen)" name="simpan" value="Tambah"/>
                            <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
                        </td></tr>
                </table>
                <br/>

                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.senaraiAgensi}" pagesize="10" cellpadding="0" cellspacing="0" id="line" >
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column title="Nama Agensi">
                            ${line.namaAgensi}
                            <input type="hidden" id="kodAgensi${line_rowNum}" value="${line.kodAgensi}">
                        </display:column>
                        <display:column title="Nama Ketua Agensi/Wakil" property="namaKetuaAgensi"/>
                        <display:column title="Jawatan" property="jawatan"/>
                        <display:column title="Hapus">
                            <div align="center">
                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeAgensi('${line_rowNum-1}','${line.idAgensi}');" />
                            </div>
                        </display:column>
                        <display:column title="Kemaskini">
                            <div align="center">
                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="editPopupForm('${line_rowNum-1}','${line.idAgensi}');"/>
                            </div>
                        </display:column>
                    </display:table>

                </div>
            </fieldset>
        </div>
    </div>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Senarai Pasukan
            </legend>
            <br/>
            <div class="instr-fieldset">
                <font color="red">PERINGATAN:</font> Sila masukkan no.pengenalan ketua operasi/ketua serbuan dengan betul.
            </div>&nbsp;
            <div class="content">

                <p align="center"><u><label>Ketua Operasi/Ketua Serbuan</label></u></p><br/>
                <p>
                    <label><em>*</em>No.pengenalan :</label>
                    <s:text name="noPengenalanKetua" id="noPengenalanKetua" maxlength="14" onkeyup="validateNumber(this,this.value);"/>&nbsp;
                    <s:button name="searchNoPengenalan" value="Cari" class="btn" onclick="findNoPengenalan()"/>&nbsp;&nbsp;
                </p>
                <p>
                    <label><em>*</em>Nama :</label>
                    <s:text name="namaKetuaDisabled" maxlength="150" id="namaKetuaDisabled" disabled="true"/>&nbsp;
                    <s:hidden name="namaKetua" id="namaKetua"/>&nbsp;
                </p>
                <p>
                    <label>No.Kad Kuasa :</label>
                    <s:text name="kadKuasaKetua" id="kadKuasaKetua" maxlength="10" onkeyup="this.value=this.value.toUpperCase();"/>&nbsp;
                    <s:hidden name="idKetua" id="idKetua"/>
                </p>

                <p>
                <fieldset class="aras2">
                    <legend>
                        Pasukan
                    </legend>
                    <div class="instr-fieldset">
                        Sila klik tambah untuk menambah senarai pasukan
                    </div>

                    <div align="center" >

                        <p>
                            <s:hidden name="recordCount" id="recordCount"/>
                        </p>

                        <table  width="60%" id="tbl" class="tablecloth" align="center">
                            <tr>
                                <th  width="1%" align="center"><b>Pilih</b></th>
                                <th  width="1%" align="center"><b>Bil</b></th>
                                <th  width="5%" align="center"><b>Peranan</b></th>
                                <th  width="10%" align="center"><b>No.KP</b></th>
                                <th  width="20%" align="center"><b>Nama</b></th>
                                <th  width="20%" align="center"><b>Kad Kuasa</b></th>

                            </tr>
                            <c:set value="0" var="i"/>
                            <c:forEach items="${actionBean.senaraiPasukanWithoutKetua}" var="line">
                                <tr style="font-weight:bold">
                                    <td><s:checkbox name="pilih${i}" id="pilih${i}" /> </td>
                                    <td><c:out value="${i+1}"/></td>
                                    <td><s:select name="peranan${i}" id="peranan${i}" value="${line.kodPerananOperasi.kod}">
                                            <s:option value="">Sila Pilih</s:option>
                                            <s:options-collection collection="${listUtil.senaraiKodPerananOperasi}" label="nama" value="kod" sort="nama" />
                                        </s:select>
                                    </td>
                                    <td><em>*</em><s:text name="noKp${i}" id="noKp${i}" value="${line.noKp}" size="20" maxlength="14" onchange="this.value=this.value.toUpperCase();" onkeyup="validateNumber(this,this.value);"/></td>
                                    <td><em>*</em><s:text name="nama${i}" id="nama${i}" value="${line.nama}" size="25" onchange="this.value=this.value.toUpperCase();"/></td>
                                    <td><s:text name="kadKuasa${i}" id="kadKuasa${i}" value="${line.kadKuasa}" maxlength="10" size="15" onchange="this.value=this.value.toUpperCase();"/></td>
                                    <s:hidden name="idOperasiPenguatkuasaanPasukan${i}" id="idOperasiPenguatkuasaanPasukan${i}" value="${line.idOperasiPenguatkuasaanPasukan}" />
                                </tr>
                                <c:set var="i" value="${i+1}" />
                            </c:forEach>

                        </table>
                        <br/>
                        <p align="center"><em>**</em> Sila tanda di petak Pilih untuk hapus.</p>

                        <table width="80%">
                            <tr><td align="right">

                                    <s:button class="btn" value="Tambah" name="add" onclick="addRow('tbl')" />
                                    <s:button class="btn" value="Hapus" name="delete" onclick="deleteRow('tbl')"/>
                        </table>
                    </div>
                    <p>&nbsp;</p>
                </fieldset>
                <br/>
                <p align="right">
                    <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
                    <s:button  name="reset" value="Isi Semula" class="btn" onclick="return test();"/>
                    <%--<s:submit name="simpan" id="simpan" class="btn" value="Simpan" onclick="return validateForm();"/>--%>
                    <c:if test="${actionBean.operasiPenguatkuasaan eq null}">
                        <s:button class="btn" onclick="if(validateForm())doSubmit(this.form, this.name, 'page_div');" name="simpan" value="Simpan"/>
                    </c:if>
                    <c:if test="${actionBean.operasiPenguatkuasaan ne null}">
                        <s:button class="btn" onclick="if(validateForm())doSubmit(this.form, this.name, 'page_div');" name="simpan" value="Kemaskini"/>
                    </c:if>
                </p>

            </div>
        </fieldset>
    </div>
</s:form>