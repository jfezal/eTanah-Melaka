<%-- 
    Document   : popup_tambah_laporan_operasi
    Created on : Nov 11, 2011, 11:24:21 AM
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
<script type="text/javascript">
    $(document).ready( function(){
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        if($('#jumlahTangkapan').val()=="0"){
            document.getElementById("jumlahTangkapan").value = "";
        }
    });

    function save(event, f){

        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.opener.refreshPage();
            self.close();
        },'html');

    }

    function validateForm(){
        if($('#jenisTindakan').val()=="")
        {
            alert('Sila isikan Jenis Operasi terlebih dahulu');
            $('#jenisTindakan').focus();
            return false;
        }
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
        
        //validation for tarikh penahanan
        if($('#datepicker1').val()!="")
        {
            if($('#hour').val()==""){
                alert("Sila masukkan jam");
                $('#hour').focus();
                return false;
            }
            if($('#minute').val()==""){
                alert("Sila masukkan minit");
                $('#minute').focus();
                return false;
            }
            if($('#ampm1').val()==""){
                alert("Sila masukkan pagi/petang");
                $('#ampm1').focus();
                return false;
            }

        }
        
        if($('#datepicker1').val()=="")
        {
            if($('#hour').val()!="" || $('#minute').val()!="" || $('#ampm1').val()!=""){
                alert("Sila pilih tarikh penahanan");
                $('#datepicker1').focus();
                return false;
            }
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

        var bil =  document.getElementById("recordCount").value;


        for (var i = 0; i < bil; i++){

            var peranan = document.getElementById('peranan'+i).value;

            if(peranan == "" ){
                alert("Sila pilih peranan");
                $('#peranan'+i).focus();
                return false;
            }
        }
        return true;
    }

    function test(f) {
        $(f).clearForm();
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
        e1.setAttribute("id","pilih" +(rowcount-1));
            cell1.appendChild(e1);

            var cell2 = row.insertCell(1);
            var bil = document.createTextNode(rowcount);
            cell2.appendChild(bil);

            var cell3 = row.insertCell(2);
            var e3 = document.createElement("select");
            e3.setAttribute("name","nama"+(rowcount-1));
            e3.setAttribute("id","nama" +(rowcount-1));
            e3.setAttribute("style","width:161px;");
        e3.onchange = function(){findPenggunaPasukan(this.value,(rowcount-1));};

        //for "sila pilih"
            var option1 = document.createElement("option");
            option1.text = " Sila pilih ";
            option1.value ="";
            e3.options.add(option1);

    <c:forEach items="${actionBean.senaraiPengguna}" var="line">
            var option2 = document.createElement("option");
            var textVal2=document.createTextNode("${line.nama}(${line.jawatan})");
            option2.appendChild(textVal2);
            option2.setAttribute("value","${line.idPengguna}");
            e3.appendChild(option2);
    </c:forEach>
                
            cell3.appendChild(e3);

            var cell4 = row.insertCell(3);
            var e4 = document.createElement("INPUT");
            e4.setAttribute("type","text");
            e4.setAttribute("name","noKp"+(rowcount-1));
            e4.setAttribute("id","noKp" +(rowcount-1));
            e4.setAttribute("size","13");
            e4.setAttribute("maxLength","12");
            e4.setAttribute("readOnly","true");
            e4.onkeyup = function(){validateNumber(this,this.value,"noKp" +(rowcount-1));};

            var element1 = document.createElement("input");
            element1.setAttribute("name","namaTemp"+(rowcount-1));
            element1.setAttribute("id","namaTemp"+(rowcount-1));
            element1.setAttribute("type","hidden");
            cell4.appendChild(e4);
            cell4.appendChild(element1);

            var cell5 = row.insertCell(4);
            var e5 = document.createElement("INPUT");
            e5.setAttribute("type","text");
            e5.setAttribute("name","tempatKerja"+(rowcount-1));
            e5.setAttribute("id","tempatKerja" +(rowcount-1));
            e5.setAttribute("size","37");
            e5.setAttribute("readOnly","true");
            e5.onchange = function(){convertText(this,"tempatKerja" +(rowcount-1));};
            cell5.appendChild(e5);

            var cell6 = row.insertCell(5);
            var e6 = document.createElement("INPUT");
            e6.setAttribute("type","text");
            e6.setAttribute("name","kadKuasa"+(rowcount-1));
            e6.setAttribute("id","kadKuasa" +(rowcount-1));
            e6.setAttribute("size","15");
            e6.setAttribute("maxLength","10");
            e6.setAttribute("readOnly","true");
            e6.onchange = function(){convertText(this,"kadKuasa" +(rowcount-1));};
            cell6.appendChild(e6);

            var cell7 = row.insertCell(6);
            var em = document.createElement("EM");
            var text1 = document.createTextNode("*");
            em.appendChild(text1);
            var e7 = document.createElement("select");
                e7.setAttribute("name","peranan"+(rowcount-1));
                e7.setAttribute("id","peranan" +(rowcount-1));
                e7.setAttribute("style","width:180px;");

            //for "sila pilih"
            var option3 = document.createElement("option");
            option3.text = " Sila pilih ";
            option3.value ="";
            e7.options.add(option3);

    <c:forEach items="${listUtil.senaraiKodPerananOperasi}" var="line1">
            var option4 = document.createElement("option");
            var textVal3=document.createTextNode("${line1.nama}");
            option4.appendChild(textVal3);
            option4.setAttribute("value","${line1.kod}");
            e7.appendChild(option4);
    </c:forEach>
            cell7.appendChild(em);
            cell7.appendChild(e7);



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

                            var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_operasi?deletePenguatkuasaanPasukan&idOperasiPenguatkuasaanPasukan='
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
            regenerateBill(tableid,idOperasiPenguatkuasaanPasukan);
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
                        
                        table.rows[i].cells[2].childNodes[0].name= 'nama'+(i-1);
                        table.rows[i].cells[2].childNodes[0].id= 'nama'+(i-1);
                        
                        table.rows[i].cells[3].childNodes[0].name= 'noKp'+(i-1);
                        table.rows[i].cells[3].childNodes[0].id= 'noKp'+(i-1);
                        
                        table.rows[i].cells[3].childNodes[1].name= 'namaTemp'+(i-1);
                        table.rows[i].cells[3].childNodes[1].id= 'namaTemp'+(i-1);

                        table.rows[i].cells[4].childNodes[0].name= 'tempatKerja'+(i-1);
                        table.rows[i].cells[4].childNodes[0].id= 'tempatKerja'+(i-1);
                        
                        table.rows[i].cells[5].childNodes[0].name= 'kadKuasa'+(i-1);
                        table.rows[i].cells[5].childNodes[0].id= 'kadKuasa'+(i-1);

                        table.rows[i].cells[6].childNodes[1].name= 'peranan'+(i-1);
                        table.rows[i].cells[6].childNodes[1].id= 'peranan'+(i-1);
                        
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

        function limitText(limitField, limitNum) {
            if (limitField.value.length > limitNum) {
                limitField.value = limitField.value.substring(0, limitNum);
            }
        }

        function findPengguna(id,section){
            //check pengguna already selected or not

            alert("id : "+id);
            var bil =  document.getElementById("recordCount").value;
            var namaKetua = document.getElementById('namaKetua').value;

            if(section == 'ketua'){
                for (var i = 0; i < bil; i++){
                    var nama = document.getElementById('nama'+i).value;
                    if(id == nama){
                        alert("Pengguna ini telah dipilih. Sila pilih pengguna yang lain.");
                        document.getElementById("noPengenalanKetua").value = '';
                        document.getElementById("tempatKerjaKetua").value = '';
                        document.getElementById("namaKetua").value = '';
                        return false;
                    }
                }
            }else{
                if(id == namaKetua){
                    alert("Pengguna ini telah dipilih. Sila pilih pengguna yang lain.");
                    document.getElementById("nama"+section).value = '';
                    return false;
                }

                for (var i = 0; i < bil; i++){
                    var nama = document.getElementById('nama'+i).value;
                    if(i != section){
                        if(id == nama){
                            alert("Pengguna ini telah dipilih. Sila pilih pengguna yang lain.");
                            document.getElementById("nama"+section).value = '';
                            return false;
                        }
                    }
                   
                }

            }

            
            $.get('${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_operasi?findPengguna&id='+id,
            function(data){
                $("#selectedPenggunaDiv").replaceWith($('#selectedPenggunaDiv', $(data)));
                var noPengenalan =  $('#noPengenalanCarian').val();
                var jabatan = $('#jawatanCarian').val();
                var idPengguna = $('#idPenggunaCarian').val();
                var nama = $('#namaCarian').val();

                if(section == 'ketua'){
                    document.getElementById("noPengenalanKetua").value = noPengenalan;
                    document.getElementById("tempatKerjaKetua").value = jabatan;
                    document.getElementById("namaKetua").value = nama;
                }else{
                    var index = section;
                    document.getElementById("noKp"+index).value = noPengenalan;
                    document.getElementById("tempatKerja"+index).value = jabatan;
                    document.getElementById("nama"+index).value = idPengguna;
                }
            }, 'html');
        }

        function findPenggunaPasukan(id,section){
            $.get('${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_operasi?findPengguna&id='+id,
            function(data){
                $("#selectedPenggunaDiv").replaceWith($('#selectedPenggunaDiv', $(data)));
                var noPengenalan =  $('#noPengenalanCarian').val();
                var jabatan = $('#jawatanCarian').val();
                var idPengguna = $('#idPenggunaCarian').val();
                var nama = $('#namaCarian').val();
                var namaKetua = document.getElementById('namaKetua').value;
                var kadKuasaCarian = $('#kadKuasaCarian').val();;

                //checking existing pengguna

                var bil =  document.getElementById("recordCount").value;
                if(section == 'ketua'){
                    for (var i = 0; i < bil; i++){
                        var namaList = document.getElementById('namaTemp'+i).value;
                        if(nama == namaList){
                            alert("Pengguna ini telah dipilih. Sila pilih pengguna yang lain.");
                            document.getElementById("noPengenalanKetua").value = '';
                            document.getElementById("tempatKerjaKetua").value = '';
                            document.getElementById("namaKetua").value = '';
                            document.getElementById("pilihPengguna").value = '';
                            return false;
                        }
                    }
                }else{
                    if(nama == namaKetua){
                        alert("Pengguna ini telah dipilih. Sila pilih pengguna yang lain.");
                        document.getElementById("nama"+section).value = '';
                        document.getElementById("noKp"+section).value = '';
                        document.getElementById("namaTemp"+section).value = '';
                        document.getElementById("tempatKerja"+section).value = '';
                        return false;
                    }

                    for (var i = 0; i < bil; i++){
                        var namaTemp = document.getElementById('namaTemp'+i).value;
                        if(i != section){
                            if(nama == namaTemp){
                                alert("Pengguna ini telah dipilih. Sila pilih pengguna yang lain.");
                                document.getElementById("nama"+section).value = '';
                                document.getElementById("noKp"+section).value = '';
                                document.getElementById("namaTemp"+section).value = '';
                                document.getElementById("tempatKerja"+section).value = '';
                                return false;
                            }
                        }

                    }
                }

                if(section == 'ketua'){
                    document.getElementById("noPengenalanKetua").value = noPengenalan;
                    document.getElementById("tempatKerjaKetua").value = jabatan;
                    document.getElementById("namaKetua").value = nama;
                    document.getElementById("kadKuasaKetua").value = kadKuasaCarian;
                }else{
                    var index = section;
                    //alert("index : "+index);
                    document.getElementById("noKp"+index).value = noPengenalan;
                    document.getElementById("tempatKerja"+index).value = jabatan;
                    document.getElementById("nama"+index).value = idPengguna;
                    document.getElementById("namaTemp"+index).value = nama;
                    document.getElementById("kadKuasa"+index).value = kadKuasaCarian;
                }
            }, 'html');

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
//            alert("s :"+s);
            return s;
        }
        
//        function formatDollar(num) {
//            alert(num);
//            var p = num.toFixed(2).split(".");
//            return "$" + p[0].split("").reverse().reduce(function(acc, num, i, orig) {
//                return  num + (i && !(i % 3) ? "," : "") + acc;
//            }, "") + "." + p[1];
//        }
 
</script>
<s:form beanclass="etanah.view.penguatkuasaan.MaklumatLaporanOperasiActionBean">
    <s:messages />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Laporan Operasi
            </legend>
            <div class="content">
                <p>
                    <label><em>*</em>Jenis Operasi:</label>
                    <s:textarea name="jenisTindakan" id="jenisTindakan" cols="80" rows="5" class="normal_text" onkeydown="limitText(this,400);" onkeyup="limitText(this,400);"/>&nbsp;
                </p>

                <p>
                    <label><em>*</em> Tarikh Laporan:</label>
                    <s:text name="tarikhOperasi" class="datepicker"  formatPattern="dd/MM/yyyy" id="datepicker" />&nbsp;
                    <font color="red" size="1">cth : hh / bb / tttt</font>
                </p>
                <p>
                    <label><em>*</em>Masa Laporan:</label>
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
                    <s:text name="tarikhPenahanan" class="datepicker" formatPattern="dd/MM/yyyy" id="datepicker1" />&nbsp;
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
                    <label><em>*</em> Kawasan Rondaan :</label>
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
                    <s:text name="nilaiKecurian" id="nilaiKecurian" size="15" class="number" formatPattern="#,##0.00"  maxlength="10" onblur="returnCurrency(this.value);" onkeyup="validateNumber(this,this.value);"/>&nbsp;
                    <font color="red" size="1">cth : RM 3000.00 </font>
                </p>

                <p>
                    <label><em>*</em> Laporan Operasi Pemantauan :</label>
                    <s:textarea name="catatan" id="catatan" rows="7" cols="80" class="normal_text" onkeydown="limitText(this,4000);" onkeyup="limitText(this,4000);" />&nbsp;
                </p>

            </div>
            <br/><br/>

        </fieldset>
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

                <p align="center"><label><u>Ketua Operasi/Ketua Serbuan</u></label></p><br/>
                <p>
                    <label>Sila Pilih :</label>
                    <s:select name="pilihPengguna" id="pilihPengguna" onchange="findPenggunaPasukan(this.value,'ketua');">
                        <s:option value="">Sila Pilih</s:option>
                        <c:forEach items="${actionBean.senaraiPengguna}" var="line">
                            <s:option value="${line.idPengguna}">${line.nama} (${line.jawatan})</s:option>
                        </c:forEach>
                    </s:select>&nbsp;
                </p>
                <p>
                    <label>Ketua Operasi :</label>
                    <s:text name="namaKetua" id="namaKetua" size="40" onkeyup="validateNumber(this,this.value);" readonly="true"/>&nbsp;
                </p>
                <p>
                    <label>Jawatan :</label>
                    <s:text name="tempatKerjaKetua" maxlength="150" size="40" id="tempatKerjaKetua" readonly="true"/>&nbsp;
                </p>
                <p>
                    <label>No.pengenalan :</label>
                    <s:text name="noPengenalanKetua" id="noPengenalanKetua" maxlength="12" onkeyup="validateNumber(this,this.value);" readonly="true"/>&nbsp;
                </p>
                <p>
                    <label>No.Kad Kuasa :</label>
                    <s:text name="kadKuasaKetua" id="kadKuasaKetua" maxlength="10" readonly="true" onkeyup="this.value=this.value.toUpperCase();"/>&nbsp;
                    <s:hidden name="idKetua" id="idKetua"/>
                </p>

                <div id="selectedPenggunaDiv" style="visibility: hidden; display:none ">
                    <s:text name="namaCarian" id="namaCarian"/>
                    <s:text name="noPengenalanCarian" id="noPengenalanCarian"/>
                    <s:text name="jawatanCarian" id="jawatanCarian"/>
                    <s:text name="idPenggunaCarian" id="idPenggunaCarian"/>
                    <s:text name="kadKuasaCarian" id="kadKuasaCarian"/>
                </div>

                <p>
                <fieldset class="aras2">
                    <legend>
                        Pasukan
                    </legend>
                    <div class="instr-fieldset">
                        <br><font color="red">PERINGATAN:</font> Sila klik tambah untuk menambah senarai pasukan.

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
                                <th  width="8%" align="center"><b>Kad Kuasa</b></th>
                                <th  width="15%" align="center"><b>Peranan</b></th>

                            </tr>
                            <c:set value="0" var="i"/>
                            <c:forEach items="${actionBean.senaraiPasukanWithoutKetua}" var="line">
                                <tr style="font-weight:bold">
                                    <td><s:checkbox name="pilih${i}" id="pilih${i}" /> </td>
                                    <td><c:out value="${i+1}"/></td>
                                    <td>${line.nama}
                                        <input type="hidden" id="namaTemp${i}" name="namaTemp${i}" value="${line.nama}">
                                    </td>
                                    <td>${line.noKp}
                                        <input type="hidden" id="noKp${i}" name="noKp${i}" value="${line.noKp}">
                                    </td>
                                    <td>${line.tempatKerja}
                                        <input type="hidden" id="tempatKerja${i}" name="tempatKerja${i}" value="${line.tempatKerja}">
                                    </td>
                                    <td>${line.kadKuasa}
                                        <input type="hidden" id="kadKuasa${i}" name="kadKuasa${i}" value="${line.kadKuasa}" style="text-transform: uppercase"></td>
                                    <td>
                                        <s:select name="peranan${i}" id="peranan${i}" value="${line.kodPerananOperasi.kod}">
                                            <s:option value="">Sila Pilih</s:option>
                                            <s:options-collection collection="${listUtil.senaraiKodPerananOperasi}" label="nama" value="kod" sort="nama" />
                                        </s:select>
                                        <input type="hidden" name="idOperasiPenguatkuasaanPasukan${i}" id="idOperasiPenguatkuasaanPasukan${i}" value="${line.idOperasiPenguatkuasaanPasukan}">
                                    </td>
                                    <%--<s:hidden name="idOperasiPenguatkuasaanPasukan${i}" id="idOperasiPenguatkuasaanPasukan${i}" value="${line.idOperasiPenguatkuasaanPasukan}" />--%>

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
                    <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
                    <s:hidden name="idOperasi"/>
                    <s:button  name="reset" value="Isi Semula" class="btn" onclick="return test();"/>
                    <s:button class="btn" onclick="if(validateForm())save(this.name,this.form);" name="simpan" value="Simpan"/>
                    <s:submit class="btn"  value="Tutup" name="closePopup" onclick="self.close();" />
                    <%--<c:if test="${actionBean.operasiPenguatkuasaan eq null}">
                        <s:button class="btn" onclick="if(validateForm())save(this.name,this.form);" name="simpan" value="Simpan"/>
                    </c:if>
                    <c:if test="${actionBean.operasiPenguatkuasaan ne null}">
                        <s:button class="btn" onclick="if(validateForm())save(this.name,this.form);" name="simpan" value="Kemaskini"/>
                    </c:if>--%>
                </p>

            </div>
        </fieldset>
    </div>
</s:form>

