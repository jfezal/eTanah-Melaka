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
    });

    function save(event, f){

        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.opener.refreshPageKpsnMesyuarat();
            self.close();
        },'html');

    }

    function validateForm(){
        
        if($('#tarikh').val()=="")
        {
            alert("Sila Pilih Tarikh Berkumpul");
            return false;
        }

        if($('#jam').val()=="0" || $('#masa').val()=="0" || $('#ampm').val()=="0")
        {
            alert("Sila Pilih Masa");
            return false;
        }
        if($('#tempat').val()=="")
        {
            alert("Sila Pilih Tempat Berkumpul");
            return false;
        }
        
        var bil =  document.getElementById("rowCount").value;


        for (var i = 0; i < bil; i++){

            var kod = document.getElementById('kodAgensi'+i).value;

            if(kod == "" ){
                alert("Sila pilih agensi");
                $('#kod'+i).focus();
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
            document.getElementById("rowCount").value =rowcount;

            //alert(rowcount);
            var cell1 = row.insertCell(0);
            var e1 = document.createElement("INPUT");
            e1.setAttribute("type","checkbox");
            e1.setAttribute("name","pilih" +(rowcount-1));
            cell1.appendChild(e1);

            var cell2 = row.insertCell(1);
            var bil = document.createTextNode(rowcount);
            cell2.appendChild(bil);

        var cell2 = row.insertCell(2);
        var em = document.createElement("EM");
        var text1 = document.createTextNode("*");
        em.appendChild(text1);
        var e2 = document.createElement("select");
            e2.setAttribute("name","kodAgensi"+(rowcount-1));
            e2.setAttribute("id","kodAgensi" +(rowcount-1));

        //for "sila pilih"
        var option3 = document.createElement("option");
        option3.text = " Sila pilih ";
        option3.value ="";
        e2.options.add(option3);

    <c:forEach items="${listUtil.senaraiAgensi}" var="line1">
            var option4 = document.createElement("option");
            var textVal3=document.createTextNode("${line1.nama}");
            option4.appendChild(textVal3);
            option4.setAttribute("value","${line1.kod}");
            e2.appendChild(option4);
    </c:forEach>
            cell2.appendChild(em);
            cell2.appendChild(e2);
            
            var cell3 = row.insertCell(3);
            var e3 = document.createElement("textarea");
            e3.t = "alamatAgensi"+(rowcount-1);
            e3.rows = 3;
            e3.cols = 40;
            e3.name ="alamatAgensi"+(rowcount-1);
            e3.id ="alamatAgensi"+(rowcount-1);
            cell3.appendChild(e3);
            
            var cell4 = row.insertCell(4);
            var e4 = document.createElement("INPUT");
            e4.setAttribute("name","nama"+(rowcount-1));
            e4.setAttribute("id","nama" +(rowcount-1));
            e4.setAttribute("size","30");
            e4.onchange = function(){convertText(this,"nama" +(rowcount-1));};
            cell4.appendChild(e4);
            
            var cell5 = row.insertCell(5);
            var e5 = document.createElement("INPUT");
            e5.setAttribute("name","noTel"+(rowcount-1));
            e5.setAttribute("id","noTel" +(rowcount-1));
            e5.setAttribute("size","15");
            e5.setAttribute("maxLength","10");
            e5.onchange = function(){validateTelLength(this.value,(rowcount-1));};
            e5.onkeyup = function(){validateNumber(this,this.value);};
            cell5.appendChild(e5);
            
            var cell6 = row.insertCell(6);
            var e6 = document.createElement("INPUT");
            e6.setAttribute("name","email"+(rowcount-1));
            e6.setAttribute("id","email" +(rowcount-1));
            e6.onblur = function(){ValidateEmail((rowcount-1));};
            cell6.appendChild(e6);

        }
    
        function convertText(r,t){
            var i = r.value;
            document.getElementById(t).value=i.toUpperCase();
        }


        function deleteRow(tableid) {
        
            var idOperasiAgensi;
            try {
                var table = document.getElementById(tableid);
                var rowCount = table.rows.length;
                //alert("delete rowCount :"+rowCount);
                var j=0;
                for(var i=0; i<rowCount; i++) {
                    var row = table.rows[i];
                    var chkbox = row.cells[0].childNodes[0];
                    if(null != chkbox && true == chkbox.checked) {
                        idOperasiAgensi = $("#idOperasiAgensi"+(i+j-1)).val();

                        if(document.getElementById("idOperasiAgensi"+(i+j-1)) != null){

                            var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_agensi?deleteAgensi&idOperasiAgensi='
                                +idOperasiAgensi;

                            $.get(url,
                            function(data){
                                $('#page_div').html(data);
                            },'html');
                            
                            self.opener.refreshPageKpsnMesyuarat();
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
                document.getElementById("rowCount").value =rowCount-1;
                //alert("rowCount :"+rowCount);
                if(rowCount > 1){
                    for(var i=1;i<rowCount;i++){
                        //alert(i+" lebih kecil dari "+rowCount);
                        table.rows[i].cells[1].childNodes[0].data= i;
                    
                    }
                }

            }catch(e){
                alert("Error in regenerateBill : "+e);
            }
        }
        
        function ValidateEmail(i){
            var emailID= $("#email"+i).val();

            if ((emailID==null)||(emailID=="")){
                return true;
            }
            if ((emailID!=null)||(emailID!="")){
                if(emailcheck(emailID)==false){
                    $("#email"+i).val("");
                    $("#email"+i).focus();
                    return false;
                }
            }
            return true;
        }

        function emailcheck(str) {

            var at="@";
            var dot=".";
            var lat=str.indexOf(at);
            var lstr=str.length;
            var ldot=str.indexOf(dot);
            if (str.indexOf(at)==-1){
                alert('"Alamat Email" salah');
                return false;
            }

            if (str.indexOf(at)==-1 || str.indexOf(at)==0 || str.indexOf(at)==lstr){
                alert('"Alamat Email" salah');
                return false;
            }

            if (str.indexOf(dot)==-1 || str.indexOf(dot)==0 || str.indexOf(dot)==lstr){
                alert('"Alamat Email" salah');
                return false;
            }

            if (str.indexOf(at,(lat+1))!=-1){
                alert('"Alamat Email" salah');
                return false;
            }

            if (str.substring(lat-1,lat)==dot || str.substring(lat+1,lat+2)==dot){
                alert('"Alamat Email" salah');
                return false;
            }

            if (str.indexOf(dot,(lat+2))==-1){
                alert('"Alamat Email" salah');
                return false;
            }

            if (str.indexOf(" ")!=-1){
                alert('"Alamat Email" salah');
                return false;
            }

            return true;
        }


        function validateTelLength(value,i){
            var plength = value.length;
            if(plength < 7){
                alert('No. Telefon yang dimasukkan salah.');
                $('#noTel'+i).val("");
                $('#noTel'+i).focus();
            }else if(plength > 10){
                alert('No. Telefon yang dimasukkan salah.');
                $('#noTel'+i).val("");
                $('#noTel'+i).focus();
            }
        }

 
</script>
<s:form beanclass="etanah.view.penguatkuasaan.MaklumatAgensiActionBean">
    <s:messages />
    <div class="subtitle">
        <fieldset class="aras1">
            <c:if test="${!viewMaklumatAgensi}">
                <legend>
                    Maklumat Keputusan Mesyuarat
                </legend>
                <div class="content">
                    <p>
                        <label><font color="red">*</font> Tarikh Mesyuarat :</label>
                        <s:text name="tarikhBerkumpul" class="datepicker" formatType="date" formatPattern="dd/MM/yyyy" id="tarikh"/>
                        <font color="red" size="1">cth : hh / bb / tttt</font>&nbsp;
                    </p>
                    <p>
                        <label><font color="red">*</font> Masa :</label>
                        <s:select name="jam" style="width:61px" id="jam">
                            <s:option value="0">Pilih</s:option>
                            <s:option value="01">01</s:option>
                            <s:option value="02">02</s:option>
                            <s:option value="03">03</s:option>
                            <s:option value="04">04</s:option>
                            <s:option value="05">05</s:option>
                            <s:option value="06">06</s:option>
                            <s:option value="07">07</s:option>
                            <s:option value="08">08</s:option>
                            <s:option value="09">09</s:option>
                            <s:option value="10">10</s:option>
                            <s:option value="11">11</s:option>
                            <s:option value="12">12</s:option>
                        </s:select>:
                        <s:select name="minit" style="width:61px" id="masa">
                            <s:option value="0">Pilih</s:option>
                            <s:option value="00">00</s:option>
                            <s:option value="05">05</s:option>
                            <s:option value="10">10</s:option>
                            <s:option value="15">15</s:option>
                            <s:option value="20">20</s:option>
                            <s:option value="25">25</s:option>
                            <s:option value="30">30</s:option>
                            <s:option value="35">35</s:option>
                            <s:option value="40">40</s:option>
                            <s:option value="45">45</s:option>
                            <s:option value="50">50</s:option>
                            <s:option value="55">55</s:option>
                        </s:select>
                        <s:select name="ampm" style="width:80px" id="ampm">
                            <s:option value="0">Pilih</s:option>
                            <s:option value="AM">AM</s:option>
                            <s:option value="PM">PM</s:option>
                        </s:select>
                    <p>
                        <label><font color="red">*</font>Tempat Mesyuarat :</label>
                        <s:textarea name="tempatBerkumpul" rows="5" cols="50" id="tempat"/>&nbsp;
                    </p>

                    <p>
                        <label>Keputusan Mesyuarat :</label>
                        <s:textarea name="ulasanMesyuarat" rows="5" cols="50" id="tempat"/>&nbsp;
                    </p>

                    <fieldset class="aras2">
                        <legend>
                            Maklumat Agensi Yang Terlibat
                        </legend>
                        <div class="instr-fieldset">
                            <br><font color="red">MAKLUMAN:</font> Sila klik tambah untuk menambah senarai anggota.

                        </div>

                        <div align="center" >

                            <p>
                                <s:hidden name="rowCount" id="rowCount"/>
                            </p>

                            <table  width="100%" id="tbl" class="tablecloth" align="center">
                                <tr>
                                    <th  width="1%" align="center"><b>Pilih</b></th>
                                    <th  width="1%" align="center"><b>Bil</b></th>
                                    <th  width="3%" align="center"><b>Nama Agensi</b></th>
                                    <th  width="3%" align="center"><b>Alamat</b></th>
                                    <th  width="3%" align="center"><b>Nama</b></th>
                                    <th  width="3%" align="center"><b>No.Tel</b></th>
                                    <th  width="3%" align="center"><b>Email</b></th>
                                </tr>
                                <c:set value="0" var="i"/>
                                <c:forEach items="${actionBean.senaraiOperasiAgensi}" var="line">
                                    <tr style="font-weight:bold">
                                        <td><s:checkbox name="pilih${i}" id="pilih${i}" /> </td>
                                        <td><c:out value="${i+1}"/></td>
                                        <td><em>*</em>
                                            <s:select name="kodAgensi${i}" id="kodAgensi${i}" value="${line.agensi.kod}">
                                                <s:option value="">Sila Pilih</s:option>
                                                <s:options-collection collection="${listUtil.senaraiAgensi}" label="nama" value="kod" sort="nama" />
                                            </s:select>
                                        </td>
                                        <td><font>
                                                <s:textarea name="alamatAgensi${i}" id="alamatAgensi${i}" value="${line.alamatAgensi}" cols="40" rows="3" class="normal_text"/>
                                            </font>
                                        </td>
                                        <td>
                                            <input type="text" size="30" name="nama${i}" id="nama${i}" value="${line.namaHubungan}">
                                        </td>
                                        <td>
                                            <input type="text" size="15" name="noTel${i}" id="noTel${i}" value="${line.noTelefonHubungan}" maxlength="10" onkeyup="validateNumber(this,this.value);" onchange="validateTelLength(this.value,'${i}');">
                                        </td>
                                        <td>
                                            <input type="text" size="20" name="email${i}" id="email${i}" value="${line.emailHubungan}" onblur="return ValidateEmail('${i}')">
                                        </td>
                                        </td>
                                        <s:hidden name="idOperasiAgensi${i}" id="idOperasiAgensi${i}" value="${line.idOperasiAgensi}" />
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
                </div>
                <br/><br/>

                <p align="center">
                    <s:hidden name="idOp"/>
                    <s:button  name="reset" value="Isi Semula" class="btn" onclick="return test();"/>
                    <s:button class="btn" onclick="if(validateForm())save(this.name,this.form);" name="simpanKpsnMesyuarat" value="Simpan"/>
                    <s:button name="tutup" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>

                </p>
            </c:if>
            <c:if test="${viewMaklumatAgensi}">
                <legend>
                    Maklumat Agensi
                </legend>

                <p>
                    <label>Nama Agensi :</label>
                    ${actionBean.operasiAgensi.agensi.nama}&nbsp;
                </p>
                <p>
                    <label>Alamat Agensi :</label>
                    ${actionBean.operasiAgensi.alamatAgensi}&nbsp;
                </p>
                <p>
                    <label>Nama :</label>
                    ${actionBean.operasiAgensi.namaHubungan}&nbsp;
                </p>
                <p>
                    <label>Email :</label>
                    ${actionBean.operasiAgensi.emailHubungan}&nbsp;
                </p>
                <p>
                    <label>No.Telefon :</label>
                    ${actionBean.operasiAgensi.noTelefonHubungan}&nbsp;
                </p>
                <br><br>
                <p align="center">
                    <s:button name="tutup" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                </p>
            </c:if>
        </fieldset>
    </div>

</s:form>
