<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<script type="text/javascript">

    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = RemoveNonNumeric(content);
            return;
        }
    }

    function RemoveNonNumeric( strString )
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

    function validate(val) {
        
        var num = document.getElementById(val).value;
        num = num.toString().replace(/\$|\,/g,'');
        if (isNaN(num))
            num = "0";
        sign = (num == (num = Math.abs(num)));
        num = Math.floor(num * 100+0.50000000001);
        cents = num % 100;
        num = Math.floor(num / 100).toString();
        if(cents < 10)
            cents = "0" + cents;
        for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
            num = num.substring(0, num.length - (4 * i + 3))+','+
            num.substring(num.length-(4 * i + 3));
        $("#"+val).val((((sign)?'':'-') + num + '.' + cents));
    }

    function validateB(){
        if($('#namaPeminjam').val() == ""){
            alert("Sila Masukkan Nama Peminjam");
            $('#namaPeminjam').focus();
            return false;
        }
        if($('#amaunGadaian').val() == ""){
            alert("Sila Masukkan Jumlah Pinjaman");
            $('#amaunGadaian').focus();
            return false;
        }
        if($('#tujuanPinjam').val() == ""){
            alert("Sila Masukkan Tujuan Pinjam");
            $('#tujuanPinjam').focus();
            return false;
        }
        if($('#tarikhGadaian').val() == ""){
            alert("Sila Masukkan Tarikh Gadaian");
            $('#tarikhGadaian').focus();
            return false;
        }
        if($('#kadarFaedahGadaian').val() == ""){
            alert("Sila Masukkan Kadar Faedah Gadaian");
            $('#kadarFaedahGadaian').focus();
            return false;
        }
        if($('#tempohTunggakan').val() == ""){
            alert("Sila Masukkan Tempoh Tunggakan");
            $('#tempohTunggakan').focus();
            return false;
        }
        if($('#kadarBayaranBalik').val() == ""){
            alert("Sila Masukkan Kadar BayaranBalik");
            $('#kadarBayaranBalik').focus();
            return false;
        }
        if($('#tarikhMulaBayarPinjaman').val() == ""){
            alert("Sila Masukkan Tarikh Mula Bayar Pinjaman");
            $('#tarikhMulaBayarPinjaman').focus();
            return false;
        }
        if($('#amaunTunggakan').val() == ""){
            alert("Sila Masukkan Amaun Tunggakan");
            $('#amaunTunggakan').focus();
            return false;
        }
        if($('#tarikhMulaTunggakan').val() == ""){
            alert("Sila Masukkan Tarikh Mula Tunggakan");
            $('#tarikhMulaTunggakan').focus();
            return false;
        }
        if($('#tarikhWarta').val() == ""){
            alert("Sila Masukkan Tarikh 16D Dikeluarkan");
            $('#tarikhWarta').focus();
            return false;
        }
        if($('#amaunGadaianDilangsai').val() == ""){
            alert("Sila Masukkan Amaun Gadaian Dilangsai");
            $('#amaunGadaianDilangsai').focus();
            return false;
        }
        if($('#bakiGadaian').val() == ""){
            alert("Sila Masukkan Baki Gadaian");
            $('#bakiGadaian').focus();
            return false;
        }

        return true;
    }

    function validation(){
        if($('#namaPeminjam').val() == ""){
            alert("Sila Masukkan Nama Peminjam");
            $('#namaPeminjam').focus();
            return false;
        }
        if($('#amaunGadaian').val() == ""){
            alert("Sila Masukkan Amaun Gadaian");
            $('#amaunGadaian').focus();
            return false;
        }
        if($('#bakiGadaian').val() == ""){
            alert("Sila Masukkan Baki Hutang");
            $('#bakiGadaian').focus();
            return false;
        }
        return true;
    }

    function addRow() {
        var table = document.getElementById("add");
        var rowcount = table.rows.length;
        var rowNum = $('#rowCount3').val();
        if(rowNum == null){
            rowNum = 0;
        }
        var rowCount1 = (parseInt(rowNum)+1);
        var row = table.insertRow(rowcount);
        var cell2 = row.insertCell(0);
        cell2.innerHTML = "<p><label></label></p>";

        var cell1 = row.insertCell(1);
        var e1 = document.createElement("INPUT");
        e1.name = 'namaPeminjam' + rowCount1;
        e1.id = 'namaPeminjam' + rowCount1;
        e1.type = 'text';
        e1.style.textTransform = 'uppercase';
        e1.style.width = '296px';
        cell1.appendChild(e1);
        
        var cell2 = row.insertCell(2);
        cell2.innerHTML = "&nbsp;";

        var cell3 = row.insertCell(3);
        var e1 = document.createElement("img");
        e1.src = "${pageContext.request.contextPath}/images/not_ok.gif";
        e1.alt = "Klik Untuk Hapus";
        e1.align = "top"
        e1.value="Hapus";
        e1.height = "15";
        e1.width = "15";
        e1.id =rowcount;
        e1.onclick=function(){deleteRow(this.id);};
        cell3.appendChild(e1);

        $('#rowCount3').val(parseInt(rowCount1));

    }

    function deleteRow(elementId)
    {
        var table = document.getElementById("add");
        var rowcount = table.rows.length;
        if(rowcount > 1){
            document.getElementById("add").deleteRow(elementId);
        }
        if(rowcount ==1 ){
            document.getElementById("add").deleteRow(0);
        }
        var rowNum = $('#rowCount3').val();
        $('#rowCount3').val(parseInt(rowNum)-1);
        regenerateBill();
    }

    function deleteRow3(id)
    {
        if(confirm("Adakah Anda Pasti?")){
            var url = '${pageContext.request.contextPath}/lelong/butirPinjaman?delete&id='+id;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
    }


    function regenerateBill(){

        var table = document.getElementById("add");
        var rowCount = table.rows.length;
        for(var i=0;i<rowCount;i++){
            //  alert("id:"+(table.rows[i].cells[2].childNodes[0].id));
            //  alert(table.rows[i].cells[0].childNodes[0].data);
            //  table.rows[i].cells[0].childNodes[0].data= "4."+(i+1);
            table.rows[i].cells[2].childNodes[0].id= i;
        }
    }


    $(document).ready(function() {
        var num = parseInt(${actionBean.rowCount4});
        if(num != 0){
            $('#rowCount3').val(parseInt(num-1));
        }else{
            $('#rowCount3').val(0);
        }
    });
</script>

<s:form beanclass="etanah.view.stripes.lelong.ButirPinjamanActionBean">
    <s:messages/>
    <s:errors/>&nbsp;

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Butiran Pinjaman
            </legend><br>

            <c:if test="${fn:length(actionBean.listPeminjam)>0}">
                <c:set var="i" value="0" />
                <p>
                    <label><font color="red">*</font> Nama Peminjam : </label>
                    <s:text id="namaPeminjam" name="namaPeminjam${0}" value="${actionBean.listPeminjam[0].nama_peminjam}" style="width:300px;"/>&nbsp;
                    <img src="${pageContext.request.contextPath}/pub/images/tambah.png" height="15" width="15" alt="Tambah Nama Peminjam"
                         onmouseover="this.style.cursor='pointer';" onclick="addRow();return false;" title="Tambah Nama Peminjam">
                </p>
                <c:forEach items="${actionBean.listPeminjam}" var="line">
                    <c:if test="${i > 0}">
                        <p>
                            <label>&nbsp;</label>
                            <s:text id="namaPeminjam" name="namaPeminjam${i}" value="${line.nama_peminjam}" style="width:300px;"/>&nbsp;
                            <img src="${pageContext.request.contextPath}/images/not_ok.gif" height="15" width="15" alt="Klik Untuk Hapus"
                                 onmouseover="this.style.cursor='pointer';" onclick="deleteRow3('${line.idEnkuiriPeminjam}');return false;"  title="Klik Untuk Hapus">
                        </p>
                    </c:if>
                    <c:set var="i" value="${i+1}" />
                </c:forEach>
                <table id ="add" border="0"></table>
            </c:if>

            <c:if test="${fn:length(actionBean.listPeminjam) == 0}">
                <p>
                    <label><font color="red">*</font> Nama Peminjam : </label>
                    <s:text id="namaPeminjam" name="namaPeminjam${0}" style="width:300px;"/>&nbsp;
                    <img src="${pageContext.request.contextPath}/pub/images/tambah.png" height="15" width="15" alt="Tambah Nama Peminjam"
                         onmouseover="this.style.cursor='pointer';" onclick="addRow();return false;" title="Tambah Nama Peminjam">
                </p>
                <table id ="add" border="0"></table>
            </c:if>
            <s:hidden name="rowCount3"  id="rowCount3"/>
            <p>
                <label><font color="red">*</font> Jumlah Pinjaman (RM) : </label>
                <s:text id="amaunGadaian" name="enkuiri.amaunGadaian" onkeyup="validateNumber(this,this.value);" onblur="validate(this.id);" formatPattern="###,###,###.00"/>&nbsp;
            </p>
            <p>
                <label>Tujuan Pinjaman : </label>
                <s:textarea id="tujuanPinjam" name="enkuiri.tujuanPinjam" rows="3" cols="30" style="width:300px;"/>&nbsp;
            </p>
            <p>
                <label>Tarikh Pinjaman Diluluskan : </label>
                <s:text id="tarikhGadaian" name="tarikhGadaian" class="datepicker" />&nbsp;
                <font color="red" size="1">(cth : hh / bb / tttt)</font>
            </p>
            <p>
                <label>Kadar Faedah (%) : </label>
                <s:textarea id="kadarFaedahGadaian" name="enkuiri.kadarFaedahGadaian" rows="1" cols="3"/> setahun&nbsp;
            </p>
            <p>
                <label>Tempoh Pinjaman : </label>
                <s:text id="tempohTunggakan" name="enkuiri.tempohTunggakan"/> Bulan &nbsp;
            </p>
            <p>
                <label>Kadar Bayaran Balik Pinjaman (RM) : </label>
                <s:text id="kadarBayaranBalik" name="enkuiri.kadarBayaranBalik" onkeyup="validateNumber(this,this.value);" onblur="validate(this.id)" formatPattern="###,###,###.00"/> / Bulan&nbsp;
            </p>
            <p>
                <label>Tarikh Mula Bayar : </label>
                <s:text id="tarikhMulaBayarPinjaman" name="enkuiri.tarikhMulaBayarPinjaman" class="datepicker" />&nbsp;
                <font color="red" size="1">(cth : hh / bb / tttt)</font>
            </p><br>
        </fieldset>
    </div>
    <br>
    <div class="subtitle">
        <br>
        <fieldset class="aras1">
            <legend>
                Tunggakan Pinjaman
            </legend><br>
            <p>
                <label>Sejak Bila Tertunggak : </label>
                <s:text name="tarikhMulaTunggakan" id="tarikhMulaTunggakan" class="datepicker" />&nbsp;
                <font color="red" size="1">(cth : hh / bb / tttt)</font>
            </p>
            <p>
                <label>Jumlah Yang Dijelaskan oleh Penggadai (RM) : </label>
                <s:text name="enkuiri.amaunGadaianDilangsai" onkeyup="validateNumber(this,this.value);" id="amaunGadaianDilangsai" onblur="validate(this.id)" formatPattern="###,###,###.00"/>&nbsp;
            </p>
            <p>
                <label>Bayaran Tertunggak Sehingga 16D dikeluarkan (RM) : </label>
                <s:text id="amaunTunggakan" name="enkuiri.amaunTunggakan" onkeyup="validateNumber(this,this.value);" onblur="validate(this.id)" formatPattern="###,###,###.00"/>&nbsp;
            </p>
            <p>
                <label>Tarikh 16D Dikeluarkan : </label>
                <s:text id="tarikhWarta" name="tarikhWarta" class="datepicker" />&nbsp;
                <font color="red" size="1">(cth : hh / bb / tttt)</font>
            </p>
            <p>
                <label><font color="red">*</font> Baki Hutang Termasuk Kadar Faedah Hingga Hari Siasatan (RM) : </label>
                <s:text name="enkuiri.bakiGadaian" onkeyup="validateNumber(this,this.value);" id="bakiGadaian" onblur="validate(this.id)" formatPattern="###,###,###.00"/>&nbsp;
            </p><br>
            <p align="right">
                <s:button name="simpanPinjaman" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>
            </p>
        </fieldset>
    </div>
</s:form>

