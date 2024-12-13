<%-- 
    Document   : laporan_operasi
    Created on : Jan 19, 2010, 6:18:05 PM
    Author     : nurshahida.radzi
    modify by  : Latifah.iskak
               : ctzainal-add new column-tempatKerja
               : sitifariza.hanim (30122011)
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

    $(document).ready(function() {
        if($('#kodNegeri').val() == '05'){
            if($('#kodUrusan').val() == '427' || $('#kodUrusan').val()=='422' || $('#kodUrusan').val()=='423' || $('#kodUrusan').val()=='424'){
                document.getElementById("427").style.visibility = 'visible';
                document.getElementById("427").style.display = '';
                document.getElementById("lainlainSeksyen").style.visibility = 'hidden';
                document.getElementById("lainlainSeksyen").style.display = 'none';
                $('#datepicker').attr("disabled", true);
                $('#jam').attr("disabled", true);
                $('#minit').attr("disabled", true);
                $('#ampm').attr("disabled", true);
                $('#lokasi').attr("disabled", true);
                $('#nilaiKecurian').attr("disabled", true);
                $('#catatan').attr("disabled", true);
            } else{
                document.getElementById("427").style.visibility = 'hidden';
                document.getElementById("427").style.display = 'none';
                document.getElementById("lainlainSeksyen").style.visibility = 'visible';
                document.getElementById("lainlainSeksyen").style.display = '';
            }

        }else {
            document.getElementById("427").style.visibility = 'hidden';
            document.getElementById("427").style.display = 'none';
            document.getElementById("lainlainSeksyen").style.visibility = 'visible';
            document.getElementById("lainlainSeksyen").style.display = '';
        }

        if($('#jumlahTangkapan').val()=="0"){
            document.getElementById("jumlahTangkapan").value = "";
        }

       
       

    });

    function validateFormNew(){

        if($('#datepickerNew').val()=="")
        {
            alert('Sila isikan Tarikh Laporan terlebih dahulu');
            $('#datepickerNew').focus();
            return false;
        }
        if($('#jamNew').val()=="")
        {
            alert('Sila isikan masa laporan terlebih dahulu');
            $('#jamNew').focus();
            return false;
        }
        if($('#minitNew').val()=="")
        {
            alert('Sila isikan masa laporan terlebih dahulu');
            $('#minitNew').focus();
            return false;
        }
        if($('#ampmNew').val()=="")
        {
            alert('Sila pilih pagi atau petang pada bahagian masa laporan');
            $('#ampmNew').focus();
            return false;
        }
        if($('#lokasiNew').val()=="")
        {
            alert('Sila isikan Lokasi Siasatan terlebih dahulu');
            $('#lokasiNew').focus();
            return false;
        }
        if($('#catatanNew').val()=="")
        {
            alert('Sila isikan Laporan Keseluruhan Siasatan terlebih dahulu');
            $('#catatanNew').focus();
            return false;
        }

        return true;
    }
        
    function validateForm(){
        
    <c:choose>
        <c:when test="${actionBean.kodNegeri eq '05' && actionBean.permohonan.kodUrusan.kod eq '429'}">
                if($('#catatan').val()=="")
                {
                    alert('Sila isikan Laporan Siasatan terlebih dahulu');
                    $('#catatan').focus();
                    return false;
                }
                /*
                if($("#namaKetuaPasukan").val() == ""){
                    alert("Sila isikan Nama Ketua Siasata");
                    $("#namaKetuaPasukan").focus();
                    return false;
                }
                if($("#noPengenalanKetua").val() == ""){
                    alert("Sila isikan No.pengenalan Ketua Siasatan");
                    $("#noPengenalanKetua").focus();
                    return false;
                }
                if($("#tempatKerjaKetua").val() == ""){
                    alert("Sila isikan Jawatan Ketua Siasatan");
                    $("#tempatKerjaKetua").focus();
                    return false;
                }
                if($("#kadKuasaKetua").val() == ""){
                    alert("Sila isikan No.Kad Kuasa Ketua Siasatan");
                    $("#kadKuasaKetua").focus();
                    return false;
                }
                 */
        </c:when>
        <c:otherwise>
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
                    alert('Sila pilih pagi atau petang pada bahagian masa laporan');
                    $('#ampm').focus();
                    return false;
                }
                if($('#lokasi').val()=="")
                {
                    alert('Sila isikan Kawasan Rondaan / Operasi terlebih dahulu');
                    $('#lokasi').focus();
                    return false;
                }
                if($('#catatan').val()=="")
                {
                    alert('Sila isikan Laporan Operasi terlebih dahulu');
                    $('#catatan').focus();
                    return false;
                }
        </c:otherwise>
    </c:choose>
        
        

            if($('#tarikhPenahanan').val()!= "")
            {
                if($('#hour').val()=="")
                {
                    alert('Sila isikan masa penahanan');
                    $('#hour').focus();
                    return false;
                }
                if($('#minute').val()=="")
                {
                    alert('Sila isikan masa penahanan');
                    $('#minute').focus();
                    return false;
                }
                if($('#ampm1').val()=="")
                {
                    alert('Sila pilih pagi atau petang pada bahagian masa penahanan');
                    $('#ampm1').focus();
                    return false;
                }
            }
            
            if($('#kodNegeri').val() == '05'){
                alert("Sila pastikan hakmilik terlibat telah dimasukkan jika ada");
    
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

        function muatNaikForm(folderId, idPermohonan, dokumenKod) {
            var left = (screen.width/2)-(1000/2);
            var top = (screen.height/2)-(150/2);
            var url = '${pageContext.request.contextPath}/penguatkuasaan/upload_action?popupUpload&folderId=' + folderId+ '&idPermohonan=' + idPermohonan + '&dokumenKod=' + dokumenKod;
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
        }

        function doViewReport(v) {
            var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }

        function removeImej(idImej) {
            if(confirm('Adakah anda pasti untuk hapus?')) {
                var url = '${pageContext.request.contextPath}/penguatkuasaan/laporan_operasi_polis?deleteSelected&idImej='+idImej;
                $.get(url,
                function(data){
                    $("#uploadLO").replaceWith($('#uploadLO', $(data)));
                },'html');
            }
        }
        function refreshPageLO(){
            var url = '${pageContext.request.contextPath}/penguatkuasaan/laporan_operasi_polis?refreshpage';
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }

        function refreshPage(){
            var url = '${pageContext.request.contextPath}/penguatkuasaan/laporan_operasi_polis?refreshpage';
            $.get(url,
            function(data){
                $("#uploadLO").replaceWith($('#uploadLO', $(data)));
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
                e3.setAttribute("name","pengguna"+(rowcount-1));
                e3.setAttribute("id","pengguna" +(rowcount-1));
                e3.setAttribute("style","width:161px;");
            e3.onchange = function(){findPengguna(this.value,(rowcount-1));};

            //for "sila pilih"
                var option1 = document.createElement("option");
                option1.text = " Sila pilih ";
                option1.value ="";
                e3.options.add(option1);

    <c:forEach items="${actionBean.pengguna}" var="line">
            var option2 = document.createElement("option");
            var textVal2=document.createTextNode("${line.nama}(${line.jawatan} : ${line.perananUtama.kod})");
            option2.appendChild(textVal2);
            option2.setAttribute("value","${line.idPengguna}");
            e3.appendChild(option2);
    </c:forEach>
                
            cell3.appendChild(e3);

            var cell4 = row.insertCell(3);
            var e4 = document.createElement("INPUT");
            e4.setAttribute("type","text");
            e4.setAttribute("name","nama"+(rowcount-1));
            e4.setAttribute("id","nama" +(rowcount-1));
            e4.setAttribute("size","24");
            e4.setAttribute("maxLength","30");
            e4.setAttribute("readonly","true");
            e4.onchange = function(){convertText(this,"nama" +(rowcount-1));};
            cell4.appendChild(e4);

            var cell5 = row.insertCell(4);
            var e5 = document.createElement("INPUT");
            e5.setAttribute("type","text");
            e5.setAttribute("name","noKp"+(rowcount-1));
            e5.setAttribute("id","noKp" +(rowcount-1));
            e5.setAttribute("size","16");
            e5.setAttribute("maxLength","14");
            e5.setAttribute("readonly","true");
            e5.onkeyup = function(){validateNumber(this,this.value,"noKp" +(rowcount-1));};
            cell5.appendChild(e5);

            var cell6 = row.insertCell(5);
            var e6 = document.createElement("textarea");
            e6.t = "tempatKerja"+(rowcount-1);
            e6.name ="tempatKerja"+(rowcount-1);
            e6.id ="tempatKerja"+(rowcount-1);
            e6.onchange = function(){convertText(this,"tempatKerja" +(rowcount-1));};
            e6.setAttribute("readonly","true");
            cell6.appendChild(e6);

            var cell7 = row.insertCell(6);
            var e7 = document.createElement("INPUT");
            e7.setAttribute("type","text");
            e7.setAttribute("name","kadKuasa"+(rowcount-1));
            e7.setAttribute("id","kadKuasa" +(rowcount-1));
            e7.setAttribute("size","10");
            e7.setAttribute("maxLength","10");
            e7.onchange = function(){convertText(this,"kadKuasa" +(rowcount-1));};
            cell7.appendChild(e7);

            var cell8 = row.insertCell(7);
            var em = document.createElement("EM");
            var text1 = document.createTextNode("*");
            em.appendChild(text1);
            var e8 = document.createElement("select");
                e8.setAttribute("name","peranan"+(rowcount-1));
                e8.setAttribute("id","peranan" +(rowcount-1));
                e8.setAttribute("style","width:180px;");

            //for "sila pilih"
            var option3 = document.createElement("option");
            option3.text = " Sila pilih ";
            option3.value ="";
            e8.options.add(option3);

    <c:forEach items="${listUtil.senaraiKodPerananOperasi}" var="line1">
            var option4 = document.createElement("option");
            var textVal3=document.createTextNode("${line1.nama}");
            option4.appendChild(textVal3);
            option4.setAttribute("value","${line1.kod}");
            e8.appendChild(option4);
    </c:forEach>
            cell8.appendChild(em);
            cell8.appendChild(e8);

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

        function limitText(limitField, limitNum) {
            if (limitField.value.length > limitNum) {
                limitField.value = limitField.value.substring(0, limitNum);
            }
        }

  
        function findPengguna(id,section){
            //check pengguna already selected or not
            var bil =  document.getElementById("recordCount").value;

            $.get('${pageContext.request.contextPath}/penguatkuasaan/laporan_operasi_polis?findPengguna&id='+id,
            function(data){
                $("#selectedPenggunaDiv").replaceWith($('#selectedPenggunaDiv', $(data)));
                var noPengenalan =  $('#noPengenalanCarian').val();
                var jabatan = $('#jawatanCarian').val();
                var namaCarian = $('#namaCarian').val();
                var kadKuasaCarian = $('#kadKuasaCarian').val();

                if(section == 'ketua'){
                    for (var i = 0; i < bil; i++){
                        var namaList = document.getElementById('nama'+i).value;
                        var noKpList = document.getElementById('noKp'+i).value;
                        if(namaCarian == namaList){
                            alert("Pengguna ini telah dipilih. Sila pilih pengguna yang lain.");
                            var idKetua = $('#idKetua').val();
                            if(idKetua == ''){
                                document.getElementById("noPengenalanKetua").value = '';
                                document.getElementById("tempatKerjaKetua").value = '';
                                document.getElementById("namaKetuaPasukan").value = '';
                                document.getElementById("kadKuasaKetua").value = '';
                            }else{
                                document.getElementById("namaKetua").value = '';
                            }
                            return false;
                        }

                        if(noPengenalan == noKpList){
                            alert("Pengguna ini telah dipilih. Sila pilih pengguna yang lain.");
                            var idKetua = $('#idKetua').val();
                            if(idKetua == ''){
                                document.getElementById("noPengenalanKetua").value = '';
                                document.getElementById("tempatKerjaKetua").value = '';
                                document.getElementById("namaKetuaPasukan").value = '';
                                document.getElementById("kadKuasaKetua").value = '';
                            }
                            return false;
                        }
                    }
                }

                if(section == 'ketua'){
                    document.getElementById("noPengenalanKetua").value = noPengenalan;
                    document.getElementById("tempatKerjaKetua").value = jabatan;
                    document.getElementById("namaKetuaPasukan").value = namaCarian;
                    document.getElementById("kadKuasaKetua").value = kadKuasaCarian;
                }else{
                    var index = section;
                    document.getElementById("noKp"+index).value = noPengenalan;
                    document.getElementById("tempatKerja"+index).value = jabatan;
                    document.getElementById("nama"+index).value = namaCarian;
                }
            }, 'html');
        }

        function addPasukan(){
            var left = (screen.width/2)-(1000/2);
            var top = (screen.height/4)-(150/4);
            var url = '${pageContext.request.contextPath}/penguatkuasaan/laporan_operasi_polis?addRecordPasukan';
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1050,height=300, left=" + left + ",top=" + top);
        }

        function refreshLaporanOperasiPolis(){
            var url = '${pageContext.request.contextPath}/penguatkuasaan/laporan_operasi_polis?refreshpage';
            $.get(url,
            function(data){
                $("#senaraiPasukanDiv").replaceWith($('#senaraiPasukanDiv', $(data)));
                $("#buttonFunctionDiv").replaceWith($('#buttonFunctionDiv', $(data)));
            },'html');
        }

        function editPasukan(id){
            var left = (screen.width/2)-(1000/2);
            var top = (screen.height/6)-(150/6);
            var url = '${pageContext.request.contextPath}/penguatkuasaan/laporan_operasi_polis?addRecordPasukan&idPasukan='+id;
            window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=400,left=" + left + ",top=" + top);
        }


        function deletePasukan(id){
            if(confirm('Adakah anda pasti untuk hapus?')) {
                var url = '${pageContext.request.contextPath}/penguatkuasaan/laporan_operasi_polis?deletePenguatkuasaanPasukan&idOperasiPenguatkuasaanPasukan='+id;
                $.get(url,
                function(data){
                    $("#senaraiPasukanDiv").replaceWith($('#senaraiPasukanDiv', $(data)));
                    $("#buttonFunctionDiv").replaceWith($('#buttonFunctionDiv', $(data)));
                },'html');}
        }

        function returnCurrency(amount){
            document.getElementById('nilaiKecurian').value = CurrencyFormatted(amount);
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
        
        function addHakmilik(){
            var left = (screen.width/2)-(1000/2);
            var top = (screen.height/4)-(150/4);
            var url = '${pageContext.request.contextPath}/penguatkuasaan/laporan_operasi_polis?addHakmilik';
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=900,height=500, left=" + left + ",top=" + top);
        }
        
        function refreshPageAddHakmilik(){
            var url = '${pageContext.request.contextPath}/penguatkuasaan/laporan_operasi_polis?refreshpage';
            $.get(url,
            function(data){
                $("#multipleHakmilikDiv").replaceWith($('#multipleHakmilikDiv', $(data)));
            },'html');
        }
        
        function removeHakmilik(id){
            if(confirm('Adakah anda pasti untuk hapus?')) {
                var url = '${pageContext.request.contextPath}/penguatkuasaan/laporan_operasi_polis?deleteHakmilik&id='+id;
                $.get(url,
                function(data){
                    $("#multipleHakmilikDiv").replaceWith($('#multipleHakmilikDiv', $(data)));
                },'html');}
        }


</script>
<s:form beanclass="etanah.view.penguatkuasaan.LaporanOperasiPolisActionBean" name="form1">
    <s:messages/>
    <s:errors/>
    <s:hidden name="permohonan.kodUrusan.kod" id="kodUrusan"/>
    <s:hidden name="kodNegeri" id="kodNegeri"/>
    <c:if test="${actionBean.senaraiMohonHakmilik eq true}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Maklumat Hakmilik
                </legend>

                <div class="content" align="left">
                    <p>Klik butang tambah untuk masukkan maklumat hakmilik</p>
                </div>
                <br/>

                <div class="content">
                    <div class="subtitle">
                        <fieldset class="aras1">
                            <div class="content" align="center">
                                <div id="multipleHakmilikDiv">
                                    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0"
                                                   id="line">
                                        <display:column title="Bil" sortable="true" style="vertical-align:baseline">
                                            <c:if test="${line.hakmilik.idHakmilik eq null}">
                                                Tiada rekod
                                            </c:if>
                                            <c:if test="${line.hakmilik.idHakmilik ne null}">
                                                ${line_rowNum}
                                            </c:if>
                                        </display:column>
                                        <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="popup hakmilik${line_rowNum}" style="vertical-align:baseline"/>
                                        <display:column property="hakmilik.noLot" title="Nombor Lot/PT" style="vertical-align:baseline"/>
                                        <display:column title="Luas" style="vertical-align:baseline"><fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                                        <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah" style="vertical-align:baseline"/>
                                        <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" style="vertical-align:baseline"/>
                                        <display:column property="hakmilik.maklumatAtasTanah" title="Jenis Penggunaan Tanah" />
                                        <display:column title="Hapus">
                                            <div align="center">
                                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeHakmilik('${line.id}');"/>
                                            </div>
                                        </display:column>  
                                    </display:table>
                                    <p align="center">
                                        <s:button name="addRecord" id="addRecord" value="Tambah" class="btn" onclick="addHakmilik();"/>
                                    </p>
                                    <br>

                                    Senarai Pemilik  <br>
                                    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" id="line">
                                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                                        <display:column title="No. Hakmilik" >${line.hakmilik.kodHakmilik.kod}${line.hakmilik.noHakmilik}</display:column>
                                        <display:column title="Nama">
                                            <c:set value="1" var="count"/>
                                            <c:forEach items="${actionBean.listPemilik}" var="senarai">
                                                <c:if test="${line.hakmilik.idHakmilik eq senarai.hakmilik.idHakmilik}">
                                                    <c:out value="${count}"/>)&nbsp;
                                                    <c:out value="${senarai.pihak.nama}"/>&nbsp;&nbsp;<br>
                                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;No.KP/Co :&nbsp;<c:out value="${senarai.pihak.noPengenalan}"/>
                                                    <c:set value="${count + 1}" var="count"/>
                                                    <br>   
                                                </c:if>
                                            </c:forEach>
                                        </display:column>
                                        <display:column title="Jenis Pihak Berkepentingan">
                                            <c:set value="1" var="count"/>
                                            <c:forEach items="${actionBean.listPemilik}" var="senarai">
                                                <c:if test="${line.hakmilik.idHakmilik eq senarai.hakmilik.idHakmilik}">
                                                    <c:out value="${count}"/>)&nbsp;
                                                    <c:out value="${senarai.jenis.nama}"/><br>
                                                    <c:set value="${count + 1}" var="count"/><br>
                                                </c:if>
                                            </c:forEach>
                                        </display:column>
                                        <display:column title="Syer yang dimiliki">
                                            <c:set value="1" var="count"/>
                                            <c:forEach items="${actionBean.listPemilik}" var="senarai">
                                                <c:if test="${line.hakmilik.idHakmilik eq senarai.hakmilik.idHakmilik}">
                                                    <c:out value="${count}"/>)&nbsp;
                                                    <c:out value="${senarai.syerPembilang}/${senarai.syerPenyebut}"/><br>
                                                    <c:set value="${count + 1}" var="count"/><br>
                                                </c:if>
                                            </c:forEach>
                                        </display:column>
                                        <display:column title="Tarikh Pemilikan Didaftar">
                                            <fmt:formatDate pattern="dd/MM/yyyy" value="${line.hakmilik.tarikhDaftar}"/>
                                        </display:column>
                                    </display:table>
                                    <br>

                                    Senarai Pihak Berkepentingan <br>
                                    <div id="DocHakmilikDiv">
                                        <display:table class="tablecloth" name="${actionBean.listPihakBerkepentingan}" id="line">
                                            <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                                            <display:column title="No. Hakmilik" >${line.hakmilik.kodHakmilik.kod}${line.hakmilik.noHakmilik}</display:column>
                                            <display:column title="Nama">
                                                <c:set value="1" var="count"/>
                                                <c:if test="${line.jenis.kod ne 'PM'}">
                                                    <c:out value="${count}"/>)&nbsp;
                                                    <c:out value="${line.pihak.nama}"/>&nbsp;&nbsp;<br>
                                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;No.KP/Co :&nbsp;<c:out value="${line.pihak.noPengenalan}"/>
                                                    <c:set value="${count + 1}" var="count"/>
                                                    <br>
                                                </c:if>
                                            </display:column>
                                            <display:column title="Jenis Pihak Berkepentingan">
                                                <c:set value="1" var="count"/>
                                                <c:if test="${line.jenis.kod ne 'PM'}">
                                                    <c:out value="${count}"/>)&nbsp;
                                                    <c:out value="${line.jenis.nama}"/><br>
                                                    <c:set value="${count + 1}" var="count"/><br>
                                                </c:if>
                                            </display:column>
                                            <display:column title="Syer yang dimiliki">
                                                <c:set value="1" var="count"/>
                                                <c:out value="${count}"/>)&nbsp;
                                                <c:out value="${line.syerPembilang}/${line.syerPenyebut}"/><br>
                                                <c:set value="${count + 1}" var="count"/><br>
                                            </display:column>
                                            <display:column title="Tarikh Pemilikan Didaftar">
                                                <fmt:formatDate pattern="dd/MM/yyyy" value="${line.hakmilik.tarikhDaftar}"/>
                                            </display:column>
                                        </display:table>
                                    </div>

                                    Senarai Waris <br>


                                    <display:table name="${actionBean.listWaris}" id="line2" class="tablecloth">
                                        <display:column title="Bil">
                                            ${line2_rowNum}
                                            <s:hidden name="x" class="x${line2_rowNum -1}" value=""/>
                                        </display:column>
                                        <display:column property="nama" title="Nama" />
                                        <display:column property="noPengenalan" title="No. Pengenalan" />
                                        <display:column title="Syer"> ${line2.syerPembilang }/${line2.syerPenyebut} </display:column>
                                    </display:table>

                                    Tanah Milik<br>
                                    <div class="content" align="center">
                                        <display:table  name="${actionBean.permohonan.senaraiHakmilik}" id="line" class="tablecloth">
                                            <display:column title="Jenis Hakmilik">
                                                <c:if test="${line.hakmilik.kodHakmilik.nama ne null}"> ${line.hakmilik.kodHakmilik.nama}&nbsp; </c:if>
                                                <c:if test="${line.hakmilik.kodHakmilik.nama eq null}"> Tiada </c:if>
                                            </display:column>

                                            <display:column title="Nombor Hakmilik">
                                                <c:if test="${line.hakmilik.idHakmilik ne null}"> ${line.hakmilik.idHakmilik}&nbsp; </c:if>
                                                <c:if test="${line.hakmilik.idHakmilik eq null}"> Tiada </c:if>
                                            </display:column>

                                            <display:column title="Nombor Lot/PT" >
                                                <c:if test="${line.hakmilik.noLot ne null}"> ${line.hakmilik.noLot}&nbsp; </c:if>
                                                <c:if test="${line.hakmilik.noLot eq null}"> Tiada </c:if>
                                            </display:column>
                                            <display:column title="Luas">
                                                <c:if test="${line.hakmilik.luas ne null}"> <fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</c:if>
                                                <c:if test="${line.hakmilik.luas eq null}"> Tiada </c:if>
                                            </display:column>
                                            <display:column property="hakmilik.kategoriTanah.nama" title="Kegunaan" >
                                            </display:column>
                                            <display:column title="Syarat Nyata">
                                                <c:if test="${line.hakmilik.syaratNyata.syarat ne null}"> ${line.hakmilik.syaratNyata.syarat}&nbsp; </c:if>
                                                <c:if test="${line.hakmilik.syaratNyata.syarat eq null}"> Tiada </c:if>
                                            </display:column>
                                            <display:column property="hakmilik.rizab.nama" title="Rizab" >
                                                <c:if test="${actionBean.hakmilik.rizab.nama ne null}"> ${actionBean.hakmilik.rizab.nama}&nbsp; </c:if>
                                                <c:if test="${actionBean.hakmilik.rizab.nama eq null}"> Tiada </c:if>
                                            </display:column>

                                            <display:column title="Cukai (RM)">
                                                <c:if test="${line.hakmilik.cukai ne null}"> <fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.cukai}"/>&nbsp; </c:if>
                                                <c:if test="${line.hakmilik.cukai eq null}"> Tiada </c:if>
                                            </display:column>


                                        </display:table>
                                    </div>


                                </div>
                            </div>
                        </fieldset>
                    </div>
                </div>
            </fieldset>
        </div>
    </c:if>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Laporan Siasatan
            </legend>

            <div class="content" >
                <div id="lainlainSeksyen">
                    <c:choose>
                        <c:when test="${actionBean.kodNegeri eq '05' && actionBean.permohonan.kodUrusan.kod ne '429'}">
                            <p>
                                <label>Jenis Tindakan:</label>
                                <s:textarea name="jenisTindakan" id="jenisTindakan" cols="80" rows="5" class="normal_text" onkeydown="limitText(this,200);" onkeyup="limitText(this,200);"/>&nbsp;
                            </p>
                        </c:when>
                    </c:choose>


                    <p>
                        <c:choose>
                            <c:when test="${actionBean.kodNegeri eq '05' && actionBean.permohonan.kodUrusan.kod eq '429'}">
                                <label> Tarikh Laporan:</label>
                            </c:when>
                            <c:otherwise>
                                <label><em>*</em> Tarikh Laporan:</label>
                            </c:otherwise>
                        </c:choose>

                        <s:text name="tarikhOperasi" class="datepicker"  formatPattern="dd/MM/yyyy" id="datepicker" />&nbsp;
                        <font color="red" size="1">cth : hh / bb / tttt</font>
                    </p>
                    <p>
                        <c:choose>
                            <c:when test="${actionBean.kodNegeri eq '05' && actionBean.permohonan.kodUrusan.kod eq '429'}">
                                <label>Masa Laporan:</label>
                            </c:when>
                            <c:otherwise>
                                <label><em>*</em> Masa Laporan:</label>
                            </c:otherwise>
                        </c:choose>

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
                        <label>Tarikh Penahanan:</label>
                        <s:text name="tarikhPenahanan" class="datepicker" id="tarikhPenahanan" value="${actionBean.tarikhPenahanan}" formatPattern="dd/MM/yyyy" />&nbsp;
                        <font color="red" size="1">cth : hh / bb / tttt</font>
                    </p>
                    <p>
                        <label>Masa Penahanan:</label>
                        <s:select name="hour" id="hour">
                            <s:option value=""> Jam </s:option>
                            <c:forEach var="hour" begin="1" end="12">
                                <s:option value="${hour}">${hour}</s:option>
                            </c:forEach>
                        </s:select>
                        <s:select name="minute" id="minute">
                            <s:option value=""> Minit </s:option>
                            <c:forEach var="minute" begin="00" end="59" >
                                <c:choose>
                                    <c:when test="${minute > 9}"><s:option value="${minute}">${minute}</s:option></c:when>
                                    <c:otherwise><s:option value="0${minute}">0${minute}</s:option></c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </s:select>
                        <s:select name="ampm1" id="ampm1" style="width:80px">
                            <s:option value="">Pilih</s:option>
                            <s:option value="AM">PAGI</s:option>
                            <s:option value="PM">PETANG</s:option>
                        </s:select>
                    </p>
                    <p>
                        <c:choose>
                            <c:when test="${actionBean.kodNegeri eq '05' && actionBean.permohonan.kodUrusan.kod eq '429'}">
                                <label> Lokasi Kejadian :</label>
                            </c:when>
                            <c:otherwise>
                                <label><em>*</em> Kawasan Rondaan / Operasi:</label>
                            </c:otherwise>
                        </c:choose>

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
                        <s:text name="jumlahTangkapan" id="jumlahTangkapan" size="10" onkeyup="validateNumber(this,this.value);"/>&nbsp;Orang.
                    </p>
                    <p>
                        <label>Nilai Anggaran : RM </label>
                        <s:text name="nilaiKecurian" id="nilaiKecurian" size="15" class="number" formatPattern="0.00" onblur="returnCurrency(this.value);"  maxlength="10" onkeyup="validateNumber(this,this.value);"/>&nbsp;
                        <font color="red" size="1">cth : RM 3000.00 </font>
                    </p>
                    <c:if test="${actionBean.kodNegeri eq '05'}">
                        <p>
                            <label>Jumlah Rampasan/Sitaan :</label>
                            <s:text name="nilaiKecurian" id="nilaiKecurianNew" size="15" class="number" formatPattern="0.00" onblur="returnCurrency(this.value);"  maxlength="10" onkeyup="validateNumber(this,this.value);"/>&nbsp;
                            <font color="red" size="1">cth : RM 3000.00 </font>
                        </p>
                    </c:if>

                    <p>
                        <c:choose>
                            <c:when test="${actionBean.kodNegeri eq '05' && actionBean.permohonan.kodUrusan.kod eq '429'}">
                                <label><em>*</em> Laporan Siasatan :</label>
                            </c:when>
                            <c:otherwise>
                                <label><em>*</em> Laporan Operasi :</label>
                            </c:otherwise>
                        </c:choose>

                        <s:textarea name="catatan" id="catatan" rows="7" cols="80" class="normal_text" onkeydown="limitText(this,4000);" onkeyup="limitText(this,4000);" />&nbsp;
                    </p>

                </div>

                <%--new div to separate from other seksyen-this only for seksyen 427 seremban--%>
                <div id="427" >
                    <p>
                        <label><em>*</em> Tarikh Siasatan:</label>
                        <s:text name="tarikhOperasi" class="datepicker"  formatPattern="dd/MM/yyyy" id="datepickerNew" />&nbsp;
                        <font color="red" size="1">cth : hh / bb / tttt</font>
                    </p>
                    <p>
                        <label><em>*</em>Masa Siasatan:</label>
                        <s:select name="jam" id="jamNew">
                            <s:option value=""> Jam </s:option>
                            <c:forEach var="jam" begin="1" end="12">
                                <s:option value="${jam}">${jam}</s:option>
                            </c:forEach>
                        </s:select>
                        <s:select name="minit" id="minitNew">
                            <s:option value=""> Minit </s:option>
                            <c:forEach var="minit" begin="00" end="59" >
                                <c:choose>
                                    <c:when test="${minit > 9}"><s:option value="${minit}">${minit}</s:option></c:when>
                                    <c:otherwise><s:option value="0${minit}">0${minit}</s:option></c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </s:select>
                        <s:select name="ampm" id="ampmNew" style="width:80px">
                            <s:option value="">Pilih</s:option>
                            <s:option value="AM">PAGI</s:option>
                            <s:option value="PM">PETANG</s:option>
                        </s:select>
                    </p>
                    <p>
                        <label><em>*</em> Lokasi Siasatan :</label>
                        <s:text name="lokasi" id="lokasiNew" size="33" onchange="this.value=this.value.toUpperCase();"/>&nbsp;
                        <font color="red" size="1">cth : Balakong </font>
                    </p>
                    <p>
                        <label><em>*</em> Laporan Keseluruhan Siasatan :</label>
                        <s:textarea name="catatan" id="catatanNew" rows="7" cols="80" class="normal_text" onkeydown="limitText(this,4000);" onkeyup="limitText(this,4000);" />&nbsp;
                    </p>

                </div>

                <c:if test="${actionBean.operasiPenguatkuasaan eq null}">
                    ${actionBean.operasiPenguatkuasaan}
                </c:if>
                <div id="uploadLO">
                    <c:if test="${actionBean.operasiPenguatkuasaan ne null}">
                        <p>
                            <label>Lampiran :</label>
                            <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png"
                                 onclick="muatNaikForm('${actionBean.permohonan.folderDokumen.folderId}',
                                     '${actionBean.permohonan.idPermohonan}','LO');return false;" height="30" width="30" alt="Muat Naik"
                                 onmouseover="this.style.cursor='pointer';" title="Muat Naik untuk Dokumen"/>
                        <p align="center">
                            <c:set value="1" var="count"/>
                            <c:forEach items="${actionBean.permohonan.folderDokumen.senaraiKandungan}" var="senarai">
                                <c:if test="${senarai.dokumen.kodDokumen.kod eq 'LO'}">
                                    <c:if test="${senarai.dokumen.namaFizikal != null}">
                                        <%--${count})--%>
                                        <br>
                                        -
                                        <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                             onclick="doViewReport('${senarai.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                             onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${senarai.dokumen.kodDokumen.nama}"/>
                                        <%--${senarai.dokumen.kodDokumen.nama} ${count} (--%>${senarai.dokumen.tajuk}<%--)--%>/
                                        <img src='${pageContext.request.contextPath}/images/not_ok.gif'
                                             onclick="removeImej('${senarai.dokumen.idDokumen}');" height="15" width="15" alt="Hapus"
                                             onmouseover="this.style.cursor='pointer';" title="Hapus untuk Dokumen ${line.dokumen.kodDokumen.nama}"/>

                                        <c:set value="${count+1}" var="count"/>
                                    </c:if>
                                </c:if>

                            </c:forEach>
                        </p>
                    </c:if>
                </div>
            </div>
        </fieldset>

    </div>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Senarai Pasukan
            </legend>
            <br/>
            <div class="instr-fieldset">
                <c:choose>
                    <c:when test="${actionBean.kodNegeri eq '05' && actionBean.permohonan.kodUrusan.kod eq '429'}">
                        <font color="red">PERINGATAN:</font> Ketua Siasatan adalah pegawai yang mengambil tugasan kes ini.
                    </c:when>
                    <c:otherwise>
                        <font color="red">PERINGATAN:</font> Sila masukkan no.pengenalan ketua operasi/ketua serbuan dengan betul.
                    </c:otherwise>
                </c:choose>

            </div>&nbsp;
            <div class="content">

                <c:choose>
                    <c:when test="${actionBean.kodNegeri eq '05' && actionBean.permohonan.kodUrusan.kod eq '429'}">
                        <p align="center"><u><label>Ketua Siasatan</label></u></p><br/>
                    </c:when>
                    <c:otherwise>
                        <p align="center"><u><label>Ketua Operasi/Ketua Serbuan</label></u></p><br/>
                    </c:otherwise>
                </c:choose>

                <c:choose>
                    <c:when test="${actionBean.kodNegeri eq '05'}">
                        <c:choose>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq '429'}">
                                <p>
                                    <label>Sila Pilih ketua:</label>
                                    <s:select name="namaKetua" id="namaKetua" onchange="findPengguna(this.value,'ketua');">
                                        <s:option value="">Sila Pilih</s:option>
                                        <c:forEach items="${actionBean.pengguna}" var="line">
                                            <s:option value="${line.idPengguna}">${line.nama} (${line.jawatan})</s:option>
                                        </c:forEach>
                                    </s:select>&nbsp;
                                </p>
                            </c:when>
                        </c:choose>
                    </c:when>
                    <c:otherwise>
                        <p>
                            <label>Sila Pilih ketua:</label>
                            <s:select name="namaKetua" id="namaKetua" onchange="findPengguna(this.value,'ketua');">
                                <s:option value="">Sila Pilih</s:option>
                                <c:forEach items="${actionBean.pengguna}" var="line">
                                    <s:option value="${line.idPengguna}">${line.nama} (${line.jawatan})</s:option>
                                </c:forEach>
                            </s:select>&nbsp;
                        </p>
                    </c:otherwise>
                </c:choose>


                <p>
                    <label>Nama :</label>
                    <s:text name="namaKetuaPasukan" maxlength="150" size="28" id="namaKetuaPasukan" readonly="true"/>&nbsp;
                    <s:hidden name="namaKetua" id="namaKetua"/>
                    <%--<s:text name="namaKetua" id="namaKetua"/>&nbsp;--%>
                </p>
                <p>
                    <label>No.pengenalan :</label>
                    <s:text name="noPengenalanKetua" id="noPengenalanKetua" maxlength="12" onkeyup="validateNumber(this,this.value);" readonly="true"/>&nbsp;
                </p>
                <p>
                    <label>Jawatan :</label>
                    <s:text name="tempatKerjaKetua" maxlength="150" size="40" id="tempatKerjaKetua" readonly="true" class="normal_text"/>&nbsp;
                </p>
                <p>
                    <label>No.Kad Kuasa :</label>
                    <s:text name="kadKuasaKetua" id="kadKuasaKetua" maxlength="10" readonly="true"/>&nbsp;
                    <s:hidden name="idKetua" id="idKetua"/>
                </p>
                <div id="selectedPenggunaDiv" style="visibility: hidden; display:none ">
                    <s:text name="namaCarian" id="namaCarian"/>
                    <s:text name="noPengenalanCarian" id="noPengenalanCarian"/>
                    <s:text name="jawatanCarian" id="jawatanCarian"/>
                    <s:text name="kadKuasaCarian" id="kadKuasaCarian"/>
                </div>



                <p>
                <fieldset class="aras2">
                    <legend>
                        Pasukan
                    </legend>
                    <div class="instr-fieldset">
                        <br><!--<font color="red">PERINGATAN:</font> Sila klik tambah untuk menambah senarai pasukan.-->

                    </div>

                    <div align="center" >
                        <div id="senaraiPasukanDiv">
                            <p>
                                <s:hidden name="recordCount" id="recordCount"/>
                            </p>
                            <div id="senaraiPasukanDivHidden" style="visibility: hidden; display: none">
                                <%--<div id="senaraiPasukanDivHidden">--%>
                                <c:set value="0" var="i"/>
                                <c:forEach items="${actionBean.senaraiPasukanWithoutKetua}" var="senarai">
                                    <input name="nama${i}" id="nama${i}" value="${senarai.nama}">
                                    <input name="noKp${i}" id="noKp${i}" value="${senarai.noKp}">
                                    <c:set var="i" value="${i+1}" />
                                </c:forEach>
                            </div>
                            <div class="subtitle">
                                <fieldset class="aras1">
                                    <div class="content" align="center">
                                        <display:table  name="${actionBean.senaraiPasukanWithoutKetua}" id="line" class="tablecloth">
                                            <display:column title="Nama" >
                                                <c:if test="${line.nama ne null}"> ${line.nama}&nbsp; </c:if>
                                                <c:if test="${line.nama eq null}"> Tiada Data </c:if>
                                            </display:column>

                                            <display:column title="No.Pengenalan" >
                                                <c:if test="${line.noKp ne null}"> ${line.noKp}&nbsp; </c:if>
                                                <c:if test="${line.noKp eq null}"> Tiada Data </c:if>
                                            </display:column>

                                            <display:column title="Jawatan" >
                                                <c:if test="${line.tempatKerja ne null}"> ${line.tempatKerja}&nbsp; </c:if>
                                                <c:if test="${line.tempatKerja eq null}"> Tiada Data </c:if>
                                            </display:column>

                                            <display:column title="Kad Kuasa">
                                                <c:if test="${line.kadKuasa ne null}"> ${line.kadKuasa}</c:if>
                                                <c:if test="${line.kadKuasa eq null}"> Tiada Data </c:if>
                                            </display:column>

                                            <display:column title="Peranan">
                                                <c:if test="${line.kodPerananOperasi.kod ne null}"> ${line.kodPerananOperasi.nama}&nbsp; </c:if>
                                                <c:if test="${line.kodPerananOperasi.kod eq null}"> Tiada Data </c:if>
                                            </display:column>
                                            <display:column title="Kemaskini">
                                                <div align="center">
                                                    <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="editPasukan('${line.idOperasiPenguatkuasaanPasukan}')"/>
                                                </div>
                                            </display:column>
                                            <display:column title="Hapus">
                                                <div align="center">
                                                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="deletePasukan('${line.idOperasiPenguatkuasaanPasukan}');"/>
                                                </div>
                                            </display:column>

                                        </display:table>
                                    </div>

                                    <p align="center">
                                        <s:button class="btn" value="Tambah" name="add" onclick="addPasukan()" />
                                    </p>
                                </fieldset>
                            </div>

                        </div>
                    </div>
                    <p>&nbsp;</p>
                </fieldset>
                <br/>
                <div id="buttonFunctionDiv">
                    <p align="right">
                        <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
                        <s:button  name="reset" value="Isi Semula" class="btn" onclick="return test();"/>

                        <%--melaka--%>
                        <c:if test = "${actionBean.kodNegeri eq '04'}">
                            <s:button class="btn" onclick="if(validateForm())doSubmit(this.form, this.name, 'page_div');" name="simpan" value="Simpan"/>
                        </c:if>

                        <%--seremban--%>
                        <c:if test = "${actionBean.kodNegeri eq '05'}">
                            <c:choose>
                                <c:when test="${actionBean.permohonan.kodUrusan.kod eq '422' || actionBean.permohonan.kodUrusan.kod eq '427' || actionBean.permohonan.kodUrusan.kod eq '423' || actionBean.permohonan.kodUrusan.kod eq '424'}">
                                    <s:button class="btn" onclick="if(validateFormNew())doSubmit(this.form, this.name, 'page_div');" name="simpan" value="Simpan"/>
                                </c:when>

                                <c:otherwise>
                                    <s:button class="btn" onclick="if(validateForm())doSubmit(this.form, this.name, 'page_div');" name="simpan" value="Simpan"/>
                                </c:otherwise>

                            </c:choose>
                        </c:if>

                    </p>
                </div>


            </div>
        </fieldset>
    </div>

</s:form>