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
            self.opener.refreshPageBorangBongkar();
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

            var cell3 = row.insertCell(2);
            var e3 = document.createElement("INPUT");
            e3.setAttribute("name","namaAnggota"+(rowcount-1));
            e3.setAttribute("id","namaAnggota" +(rowcount-1));
            e3.setAttribute("size","30");
        e3.onchange = function(){convertText(this,"namaAnggota" +(rowcount-1));};
        cell3.appendChild(e3);

    }
    
    function addRowKehadiran (tableid){

            var table = document.getElementById (tableid);
            var rowcount = table.rows.length;
            var row = table.insertRow(rowcount);
            document.getElementById("rowCountOrangHadir").value =rowcount;

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
            var e3 = document.createElement("INPUT");
            e3.setAttribute("name","namaKehadiran"+(rowcount-1));
            e3.setAttribute("id","namaKehadiran" +(rowcount-1));
            e3.setAttribute("size","30");
        e3.onchange = function(){convertText(this,"namaKehadiran" +(rowcount-1));};
        cell3.appendChild(e3);
        
        var cell4 = row.insertCell(3);
        var e4 = document.createElement("select");
            e4.setAttribute("name","jenisPengenalan"+(rowcount-1));
            e4.setAttribute("id","jenisPengenalan" +(rowcount-1));
            e4.setAttribute("style","width:180px;");

        //for "sila pilih"
        var option1 = document.createElement("option");
        option1.text = " Sila pilih ";
        option1.value ="";
        e4.options.add(option1);

    <c:forEach items="${listUtil.senaraiKodJenisPengenalan}" var="line1">
            var option2 = document.createElement("option");
            var textVal3=document.createTextNode("${line1.nama}");
            option2.appendChild(textVal3);
            option2.setAttribute("value","${line1.kod}");
            e4.appendChild(option2);
    </c:forEach>
            cell4.appendChild(e4);
            
              var cell5 = row.insertCell(4);
                var e5 = document.createElement("INPUT");
                e5.setAttribute("name","noPengenalanKehadiran"+(rowcount-1));
                e5.setAttribute("id","noPengenalanKehadiran" +(rowcount-1));
                e5.setAttribute("size","30");
            cell5.appendChild(e5);
            
              var cell6 = row.insertCell(5);
                var e6 = document.createElement("INPUT");
                e6.setAttribute("name","hubunganKehadiran"+(rowcount-1));
                e6.setAttribute("id","hubunganKehadiran" +(rowcount-1));
                e6.setAttribute("size","30");
            cell6.appendChild(e6);

        }
    
        function convertText(r,t){
            var i = r.value;
            document.getElementById(t).value=i.toUpperCase();
        }


        function deleteRow(tableid) {
        
            var idRujukan;
            try {
                var table = document.getElementById(tableid);
                var rowCount = table.rows.length;
                //alert("delete rowCount :"+rowCount);
                var j=0;
                for(var i=0; i<rowCount; i++) {
                    var row = table.rows[i];
                    var chkbox = row.cells[0].childNodes[0];
                    if(null != chkbox && true == chkbox.checked) {
                        idRujukan = $("#idRujukan"+(i+j-1)).val();

                        if(document.getElementById("idRujukan"+(i+j-1)) != null){

                            var url = '${pageContext.request.contextPath}/penguatkuasaan/borang_bongkar?deleteAnggota&idRujukan='
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
            regenerateBill(tableid ,idRujukan);
        }

        function regenerateBill(tableid, id){
            try{
                var table = document.getElementById(tableid);
                var rowCount = table.rows.length;
                document.getElementById("rowCount").value =rowCount-1;
                //alert("rowCount :"+rowCount);
                if(rowCount > 1){
                    for(var i=1;i<rowCount;i++){
                        //alert(i+" lebih kecil dari "+rowCount);
                        table.rows[i].cells[1].childNodes[0].data= i;
                    
                        table.rows[i].cells[2].childNodes[0].name= 'namaAnggota'+(i-1);
                        table.rows[i].cells[2].childNodes[0].id= 'namaAnggota'+(i-1);
                    }
                }

            }catch(e){
                alert("Error in regenerateBill : "+e);
            }
        }
        
        
        function deleteRowKehadiran(tableid) {
        
            var idBongkarKehadiran;
            try {
                var table = document.getElementById(tableid);
                var rowCount = table.rows.length;
                //alert("delete rowCount :"+rowCount);
                var j=0;
                for(var i=0; i<rowCount; i++) {
                    var row = table.rows[i];
                    var chkbox = row.cells[0].childNodes[0];
                    if(null != chkbox && true == chkbox.checked) {
                        idBongkarKehadiran = $("#idBongkarKehadiran"+(i+j-1)).val();

                        if(document.getElementById("idBongkarKehadiran"+(i+j-1)) != null){

                            var url = '${pageContext.request.contextPath}/penguatkuasaan/borang_bongkar?deleteKehadiran&idBongkarKehadiran='
                                +idBongkarKehadiran;

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
            regenerateBill(tableid ,idBongkarKehadiran);
        }



 
</script>
<s:form beanclass="etanah.view.penguatkuasaan.MaklumatBorangBongkarActionBean">
    <s:messages />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Bongkar
            </legend>
            <div class="content">
                <p>
                    <b>1.&emsp;Butir-butir kenderaan yang dibongkar: </b> &nbsp;
                </p>
                <div class="content" align="center">
                    <display:table name="${actionBean.senaraiKenderaanOks}" id="line" class="tablecloth" cellpadding="0" cellspacing="0" style="width:50%;">
                        <display:column title="Bil" sortable="true" style="width:5px;">${line_rowNum}</display:column>
                        <display:column title="No.Pendaftaran" property="nomborPendaftaran"/>
                        <display:column title="Warna" property="warna"/>
                        <display:column title="Jenis" property="item"/>
                        <display:column title="No.Casis" property="nomborCasis"/>
                        <display:column title="No.Enjin" property="nomborEnjin"/>
                        <display:column title="No.Siri" property="noSiri"/>
                    </display:table>

                </div>

                <p>
                    <b>2.&emsp;Nama dan alamat suspek kenderaan: </b> &nbsp;
                </p>
                <div class="content" align="center">
                    <display:table name="${actionBean.senaraiKenderaanOks}" id="line" class="tablecloth" cellpadding="0" cellspacing="0" style="width:50%;">
                        <display:column title="Bil" sortable="true" style="width:5px;">${line_rowNum}</display:column>
                        <display:column title="Nama Suspek" property="namaSuspek"/>
                        <display:column title="Alamat">
                            <c:if test="${line.alamat1Suspek ne null}">${line.alamat1Suspek}</c:if>
                            <c:if test="${line.alamat2Suspek ne null}">${line.alamat2Suspek}</c:if>
                            <c:if test="${line.poskodSuspek ne null}">${line.poskodSuspek}</c:if>
                            <c:if test="${line.alamat3Suspek ne null}">${line.alamat3Suspek}</c:if>
                        </display:column>
                        <display:column title="No.Pengenalan" property="noPengenalanSuspek"/>
                        <display:column title="No.Telefon" property="noTelSuspek"/>

                    </display:table>

                </div>

                <p>
                    <b>3.&emsp;Anggota polis yang hadir </b> &nbsp;
                </p>

                <fieldset class="aras2">
                    <legend>
                        Pasukan
                    </legend>
                    <div class="instr-fieldset">
                        <br><font color="red">MAKLUMAN:</font> Sila klik tambah untuk menambah senarai anggota.

                    </div>

                    <div align="center" >

                        <p>
                            <s:hidden name="rowCount" id="rowCount"/>
                        </p>

                        <table  width="30%" id="tbl" class="tablecloth" align="center">
                            <tr>
                                <th  width="1%" align="center"><b>Pilih</b></th>
                                <th  width="1%" align="center"><b>Bil</b></th>
                                <th  width="5%" align="center"><b>Nama</b></th>
                            </tr>
                            <c:set value="0" var="i"/>
                            <c:forEach items="${actionBean.senaraiPermohonanRujLuar}" var="line">
                                <tr style="font-weight:bold">
                                    <td><s:checkbox name="pilih${i}" id="pilih${i}" /> </td>
                                    <td><c:out value="${i+1}"/></td>
                                    <td>
                                        <input type="text" size="30" name="namaAnggota${i}" id="namaAnggota${i}" value="${line.namaPenyedia}" onchange="this.value=this.value.toUpperCase();">
                                    </td>
                                    <s:hidden name="idRujukan${i}" id="idRujukan${i}" value="${line.idRujukan}" />
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

                <p>
                    <b>4.&emsp;Orang yang hadir </b> &nbsp;
                </p>

                <fieldset class="aras2">
                    <legend>
                        Kehadiran
                    </legend>
                    <div class="instr-fieldset">
                        <br><font color="red">MAKLUMAN:</font> Sila klik tambah untuk menambah senarai kehadiran.

                    </div>

                    <div align="center" >

                        <p>
                            <s:hidden name="rowCountOrangHadir" id="rowCountOrangHadir"/>
                        </p>

                        <table  width="70%" id="tbl1" class="tablecloth" align="center">
                            <tr>
                                <th  width="1%" align="center"><b>Pilih</b></th>
                                <th  width="1%" align="center"><b>Bil</b></th>
                                <th  width="5%" align="center"><b>Nama</b></th>
                                <th  width="10%" align="center"><b>Kod Pengenalan</b></th>
                                <th  width="5%" align="center"><b>No.Pengenalan</b></th>
                                <th  width="5%" align="center"><b>Hubungan</b></th>
                            </tr>
                            <c:set value="0" var="i"/>
                            <c:forEach items="${actionBean.listBongkarKehadiran}" var="line">
                                <tr style="font-weight:bold">
                                    <td><s:checkbox name="pilih${i}" id="pilih${i}" /> </td>
                                    <td><c:out value="${i+1}"/></td>
                                    <td>
                                        <input type="text" size="30" name="namaKehadiran${i}" id="namaKehadiran${i}" value="${line.nama}" onchange="this.value=this.value.toUpperCase();">
                                    </td>
                                    <td>
                                        <s:select name="jenisPengenalan${i}" id="jenisPengenalan${i}" value="${line.jenisPengenalan.kod}">
                                            <s:option value="">Sila Pilih</s:option>
                                            <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod" sort="nama" />
                                        </s:select>
                                        <input type="hidden" size="10" name="idBongkarKehadiran${i}" id="idBongkarKehadiran${i}" value="${line.idBongkarKehadiran}">
                                    </td>
                                    <td>
                                        <input type="text" size="30" name="noPengenalanKehadiran${i}" id="noPengenalanKehadiran${i}" value="${line.noPengenalan}" onchange="this.value=this.value.toUpperCase();">
                                    </td>
                                    <td>
                                        <input type="text" size="30" name="hubunganKehadiran${i}" id="hubunganKehadiran${i}" value="${line.hubungan}" onchange="this.value=this.value.toUpperCase();">
                                    </td>
                                </tr>
                                <c:set var="i" value="${i+1}" />
                            </c:forEach>

                        </table>

                        <br/>
                        <p align="center"><em>**</em> Sila tanda di petak Pilih untuk hapus.</p>

                        <table width="80%">
                            <tr><td align="center">

                                    <s:button class="btn" value="Tambah" name="add" onclick="addRowKehadiran('tbl1')" />
                                    <s:button class="btn" value="Hapus" name="delete" onclick="deleteRowKehadiran('tbl1')"/>
                        </table>
                    </div>
                    <p>&nbsp;</p>
                </fieldset>

                <p>
                    <b>5.&emsp;Tempat, Tarikh dan Masa Bongkar </b> &nbsp;
                </p>
                <p>
                    <label>Tempat Bongkar :</label>
                    <s:text name="tempatBongkar" id="tempatBongkar" size="33" onchange="this.value=this.value.toUpperCase();"/>&nbsp;
                </p>

                <p>
                    <label>Tarikh Bongkar:</label>
                    <s:text name="tarikhBongkar" class="datepicker"  formatPattern="dd/MM/yyyy" id="datepicker" />&nbsp;
                    <font color="red" size="1">cth : hh / bb / tttt</font>
                </p>
                <p>
                    <label>Masa Bongkar:</label>
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

                <p>
                    <b>6.&emsp;Barang-barang Diambil </b> &nbsp;
                </p>
                <div class="content" align="center">
                    <display:table name="${actionBean.senaraiBarangOks}" id="line" class="tablecloth" cellpadding="0" cellspacing="0" style="width:50%;">
                        <display:column title="Bil" sortable="true" style="width:5px;">${line_rowNum}</display:column>
                        <display:column title="Item" property="item"/>
                        <display:column title="Lokasi Tangkapan" property="tempatTangkap"/>
                        <display:column title="Lokasi Bongkar" property="tempatBongkar"/>
                    </display:table>

                </div>


            </div>
            <br/><br/>

        </fieldset>
    </div>

    <p align="center">
        <s:hidden name="idOks"/>
        <s:button  name="reset" value="Isi Semula" class="btn" onclick="return test();"/>
        <s:button class="btn" onclick="if(validateForm())save(this.name,this.form);" name="simpan" value="Simpan"/>
        <s:button name="tutup" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>

    </p>
</s:form>
