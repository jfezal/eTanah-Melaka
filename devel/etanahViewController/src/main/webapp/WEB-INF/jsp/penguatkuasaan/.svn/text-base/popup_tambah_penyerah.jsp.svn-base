<%-- 
    Document   : popup_tambah_laporan_operasi
    Created on : Sept 9, 2012, 11:24:21 AM
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
        var bil =  ${fn:length(actionBean.senaraiBaranganRampasan)};
        for (var i = 0; i < bil; i++){
            if($("#statusSerahan"+i).val() == "Y"){
                document.getElementById("pilihBarang"+i).checked = true;
            }
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
        if($('#idPenyerah').val()=="")
        {
            alert('Sila pilih penyerah terlebih dahulu');
            $('#idPenyerah').focus();
            return false;
        }
        if($('#noSerahan').val()=="")
        {
            alert('Sila isikan no. serahan terlebih dahulu');
            $('#noSerahan').focus();
            return false;
        }
        
        var bil =  ${fn:length(actionBean.senaraiBaranganRampasan)};
        var j = 0;

        for (var i = 0; i < bil; i++){

            var pilih = document.getElementById('pilihBarang'+i).checked;

            if( pilih == true){
                j++;
            }

        }
        
        if(j==0){
            alert("Sila pilih barang serahan");
            return false;
        }
        
        if($('#diTandatanganOleh').val()=="")
        {
            alert('Sila pilih ditandatangan oleh terlebih dahulu');
            $('#diTandatanganOleh').focus();
            return false;
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
        var e3 = document.createElement("input");
        e3.setAttribute("type","text");
        e3.setAttribute("name","namaBarangan"+(rowcount-1));
        e3.setAttribute("id","namaBarangan" +(rowcount-1));
        e3.setAttribute("size","30");
        e3.setAttribute("maxLength","50");
        cell3.appendChild(e3);

        var cell4 = row.insertCell(3);
        var e4 = document.createElement("INPUT");
        e4.setAttribute("type","text");
        e4.setAttribute("name","jenisBarangan"+(rowcount-1));
        e4.setAttribute("id","jenisBarangan" +(rowcount-1));
        e4.setAttribute("size","30");
        e4.setAttribute("maxLength","20");
        e4.onchange = function(){convertText(this,"jenisBarangan" +(rowcount-1));};
        cell4.appendChild(e4);
       

        var cell5 = row.insertCell(4);
        var e5 = document.createElement("INPUT");
        e5.setAttribute("type","text");
        e5.setAttribute("name","modelBarangan"+(rowcount-1));
        e5.setAttribute("id","modelBarangan" +(rowcount-1));
        e5.setAttribute("size","30");
        e5.setAttribute("maxLength","20");
        e5.onchange = function(){convertText(this,"modelBarangan" +(rowcount-1));};
        cell5.appendChild(e5);

        var cell6 = row.insertCell(5);
        var e6 = document.createElement("INPUT");
        e6.setAttribute("type","text");
        e6.setAttribute("name","kuantitiBarangan"+(rowcount-1));
        e6.setAttribute("id","kuantitiBarangan" +(rowcount-1));
        e6.setAttribute("size","5");
        e6.setAttribute("maxLength","5");
        e6.onchange = function(){convertText(this,"kuantitiBarangan" +(rowcount-1));};
        cell6.appendChild(e6);
        
        var cell7 = row.insertCell(6);
        //var em = document.createElement("EM");
        var text1 = document.createTextNode("*");
        //em.appendChild(text1);
        var e7 = document.createElement("select");
        e7.setAttribute("name","penggunaSerahan"+(rowcount-1));
        e7.setAttribute("id","penggunaSerahan" +(rowcount-1));
        //e7.setAttribute("style","width:180px;");

        var option3 = document.createElement("option");
        option3.text = " Sila pilih ";
        option3.value ="";
        e7.options.add(option3);

    <c:forEach items="${actionBean.senaraiPengguna}" var="line1">
            var option4 = document.createElement("option");
            var textVal3=document.createTextNode("${line1.nama}");
            option4.appendChild(textVal3);
            option4.setAttribute("value","${line1.idPengguna}");
            e7.appendChild(option4);
    </c:forEach>
            //cell7.appendChild(em);
            cell7.appendChild(e7);
        }

        function convertText(r,t){
            var i = r.value;
            document.getElementById(t).value=i.toUpperCase();
        }


        function deleteRow(tableid) {
            var idBarangOperasi;
            try {
                var table = document.getElementById(tableid);
                var rowCount = table.rows.length;
                var j=0;
                for(var i=0; i<rowCount; i++) {
                    var row = table.rows[i];
                    var chkbox = row.cells[0].childNodes[0];
                    if(null != chkbox && true == chkbox.checked) {
                        idBarangOperasi = $("#idBarangOperasi"+(i+j-1)).val();

                        if(document.getElementById("idBarangOperasi"+(i+j-1)) != null){

                            var url = '${pageContext.request.contextPath}/penguatkuasaan/serahan_barang_rampasan?deleteBarangOperasi&idBarangOperasi='
                                +idBarangOperasi;

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
            regenerateBill(tableid,idBarangOperasi);
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
                        
                        table.rows[i].cells[2].childNodes[0].name= 'namaBarangan'+(i-1);
                        table.rows[i].cells[2].childNodes[0].id= 'namaBarangan'+(i-1);
                        
                        table.rows[i].cells[3].childNodes[0].name= 'jenisBarangan'+(i-1);
                        table.rows[i].cells[3].childNodes[0].id= 'jenisBarangan'+(i-1);
                        
                        table.rows[i].cells[4].childNodes[0].name= 'modelBarangan'+(i-1);
                        table.rows[i].cells[4].childNodes[0].id= 'modelBarangan'+(i-1);
                        
                        table.rows[i].cells[5].childNodes[0].name= 'kuantitiBarangan'+(i-1);
                        table.rows[i].cells[5].childNodes[0].id= 'kuantitiBarangan'+(i-1);
                        
                        table.rows[i].cells[6].childNodes[1].name= 'penggunaSerahan'+(i-1);
                        table.rows[i].cells[6].childNodes[1].id= 'penggunaSerahan'+(i-1);
                        
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

       

        function findPengguna(id){
            $.get('${pageContext.request.contextPath}/penguatkuasaan/serahan_barang_rampasan?findPengguna&id='+id,
            function(data){
                $("#penggunaDiv").replaceWith($('#penggunaDiv', $(data)));
            }, 'html');

        }
        
        function checkExisting(val){
            //alert(val);
            if(val != ""){
                $.get('${pageContext.request.contextPath}/penguatkuasaan/serahan_barang_rampasan?findNoSerahan&noSerahan='+val,
                function(data){
                    //alert(data);
                    if(data == "exist"){
                        alert("No.Serahan ini sudah wujud. Sila masukkan no.serahan yang betul.");
                        document.getElementById("noSerahan").value = '';
                    }
                }, 'html');

            }
        }

 
</script>
<s:form beanclass="etanah.view.penguatkuasaan.SerahanBarangRampasanActionBean">
    <s:messages />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Serahan Barang Rampasan
            </legend>
            <c:if test="${edit}">
                <div class="content">
                    <p>
                        <label>Penyerah :</label>
                        <s:select name="idPenyerah" id="idPenyerah" onchange="findPengguna(this.value);">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${actionBean.senaraiPengguna}" label="nama" value="idPengguna" />
                        </s:select>
                    </p>

                    <div id="penggunaDiv">
                        <p>
                            <label>No.kad Pengenalan :</label>
                            <s:text name="noPengenalanPenyerah" id="noPengenalanPenyerah" size="40" readonly="true"/>&nbsp;
                        </p>
                        <p>
                            <label>Pekerjaan :</label>
                            <s:text name="pekerjaanPenyerah" maxlength="150" size="40" id="pekerjaanPenyerah" readonly="true"/>&nbsp;
                        </p>
                        <p>
                            <label>Tempat Kerja :</label>
                            <s:textarea name="tempatKerjaPenyerah" id="tempatKerjaPenyerah" rows="1" cols="37" class="normal_text" readonly="true"/>&nbsp;
                        </p>
                    </div>
                    <p>
                        <label>No. serahan :</label>
                        <s:text name="noSerahan" maxlength="150" size="40" id="noSerahan" onblur="checkExisting(this.value);"/>&nbsp;
                    </p>
                </div>
                <div class="subtitle">
                    <fieldset class="aras1">
                        <legend>
                            Barangan Serahan
                        </legend>
                        <br/>
                        <div class="instr-fieldset">
                            <font color="red">MAKLUMAN:</font> Sila pilih barang serahan
                        </div>&nbsp;
                        <div class="content">
                            <div align="center" >
                                <table  width="80%" id="tbl" class="tablecloth" align="center">
                                    <tr>
                                        <th  width="1%" align="center"><b>Pilih</b></th>
                                        <th  width="1%" align="center"><b>Bil</b></th>
                                        <th  width="5%" align="center"><b>Nama</b></th>
                                        <th  width="5%" align="center"><b>No.Kenderaan</b></th>
                                        <th  width="5%" align="center"><b>No.Enjin</b></th>
                                        <th  width="8%" align="center"><b>No.Casis</b></th>

                                    </tr>
                                    <c:set value="0" var="i"/>
                                    <c:forEach items="${actionBean.senaraiBaranganRampasan}" var="line">
                                        <tr style="font-weight:bold">
                                            <td>
                                                <s:checkbox name="pilihBarang${i}" id="pilihBarang${i}" value="${line.idBarang}"/>
                                                <input type="hidden" id="pilihBarangTemp${i}" name="pilihBarangTemp${i}" value="${line.idBarang}">
                                                <input type="hidden" id="statusSerahan${i}" name="statusSerahan${i}" value="${line.statusSerah}">
                                            </td>
                                            <td><c:out value="${i+1}"/></td>
                                            <td>${line.item}</td>
                                            <td>${line.nomborPendaftaran}</td>
                                            <td>${line.nomborEnjin}</td>
                                            <td>${line.nomborCasis}</td>
                                            <s:hidden name="idBarang${i}" id="idBarang${i}" value="${line.idBarang}" />

                                        </tr>
                                        <c:set var="i" value="${i+1}" />
                                    </c:forEach>

                                </table>

                                <br/>

                            </div>
                        </div>

                        <p>
                            <label>Ditandatangan Oleh :</label>
                            <s:select name="diTandatanganOleh" id="diTandatanganOleh">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${actionBean.senaraiTandatanganPengguna}" label="nama" value="idPengguna" />
                            </s:select>
                        </p>
                        <br> <br>
                        <p align="center">
                            <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
                            <s:hidden name="idOperasi"/>
                            <s:hidden name="idPenyerahBarangOperasi"/>
                            <s:button  name="reset" value="Isi Semula" class="btn" onclick="return test();"/>
                            <s:button class="btn" onclick="if(validateForm())save(this.name,this.form);" name="simpan" value="Simpan"/>
                            <s:submit class="btn"  value="Tutup" name="closePopup" onclick="self.close();" />
                        </p>
                    </fieldset>
                </div>
            </c:if>

            <c:if test="${view}">
                <div class="content">
                    <p>
                        <label>Penyerah :</label>
                        ${actionBean.penyerahBarangOperasi.pengguna.nama}&nbsp;
                    </p>

                    <p>
                        <label>No.kad Pengenalan :</label>
                        ${actionBean.penyerahBarangOperasi.pengguna.noPengenalan}&nbsp;
                    </p>
                    <p>
                        <label>Pekerjaan :</label>
                        ${actionBean.penyerahBarangOperasi.pengguna.jawatan}&nbsp;
                    </p>
                    <p>
                        <label>Tempat Kerja :</label>
                        ${actionBean.penyerahBarangOperasi.pengguna.kodCawangan.alamat.alamat1}&nbsp;
                    </p>
                    <p>
                        <label>No. serahan :</label>
                        ${actionBean.penyerahBarangOperasi.noPerserahan}&nbsp;
                    </p>
                </div>
                <div class="subtitle">
                    <fieldset class="aras1">
                        <legend>
                            Barangan Serahan
                        </legend>
                        <br/>
                        <div class="content">
                            <div align="center" >
                                <table  width="80%" id="tbl" class="tablecloth" align="center">
                                    <tr>
                                        <th  width="1%" align="center"><b>Pilih</b></th>
                                        <th  width="1%" align="center"><b>Bil</b></th>
                                        <th  width="5%" align="center"><b>Nama</b></th>
                                        <th  width="5%" align="center"><b>No.Kenderaan</b></th>
                                        <th  width="5%" align="center"><b>No.Enjin</b></th>
                                        <th  width="8%" align="center"><b>No.Casis</b></th>

                                    </tr>
                                    <c:set value="0" var="i"/>
                                    <c:forEach items="${actionBean.senaraiBaranganRampasan}" var="line">
                                        <tr style="font-weight:bold">
                                            <td>
                                                <s:checkbox name="pilihBarang${i}" id="pilihBarang${i}" value="${line.idBarang}" disabled="true"/>
                                                <input type="hidden" id="pilihBarangTemp${i}" name="pilihBarangTemp${i}" value="${line.idBarang}">
                                                <input type="hidden" id="statusSerahan${i}" name="statusSerahan${i}" value="${line.statusSerah}">
                                            </td>
                                            <td><c:out value="${i+1}"/></td>
                                            <td>${line.item}</td>
                                            <td>${line.nomborPendaftaran}</td>
                                            <td>${line.nomborEnjin}</td>
                                            <td>${line.nomborCasis}</td>
                                            <s:hidden name="idBarang${i}" id="idBarang${i}" value="${line.idBarang}" />

                                        </tr>
                                        <c:set var="i" value="${i+1}" />
                                    </c:forEach>

                                </table>

                                <br/>

                            </div>
                        </div>

                        <p>
                            <label>Ditandatangan Oleh :</label>
                            <c:set var="oleh" value=""/>
                            <c:forEach items="${actionBean.senaraiTandatanganPengguna}" var="p">
                                <c:if test="${p.idPengguna eq actionBean.mohonTandatanganDokumen.diTandatangan}">
                                    <c:set var="oleh" value="${p.nama}"/>
                                </c:if>

                            </c:forEach>
                            ${oleh}&nbsp;
                        </p>
                        <br> <br>
                        <p align="center">
                            <s:submit class="btn"  value="Tutup" name="closePopup" onclick="self.close();" />
                        </p>
                    </fieldset>
                </div>
            </c:if>

            <br/><br/>

        </fieldset>
    </div>


</s:form>

