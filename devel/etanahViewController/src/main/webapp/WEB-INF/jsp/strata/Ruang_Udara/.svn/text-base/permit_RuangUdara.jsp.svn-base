<%-- 
    Document   : permit_RuangUdara
    Created on : Aug 21, 2013, 12:42:10 AM
    Author     : Murali
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript">

    function clear1(){
        document.getElementById("lot").value = " ";
        document.getElementById("blok").value = " ";
        document.getElementById("tingkat").value = " ";
        document.getElementById("bstruktur").value = " ";
        document.getElementById("jst").value = " ";
        document.getElementById("bst").value = " ";
        document.getElementById("do").value = " ";
        document.getElementById("kst").value = " ";
        document.getElementById("bp").value = " ";
    }

    function getbs()
    {
        //alert('test');
    

        var str = document.getElementById('bilStrukTambah').value;
        //bilPermit
        document.getElementById('bilPermit').value = str;
    }
    function test(val){
        if(confirm("Adakah anda ingin kemaskini data ini?")){
            var url = '${pageContext.request.contextPath}/strata/permit_RuangUdara?showEdit&id=' + val;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
            //refreshPage4();
        }
    }
     function kemaskiniPermit(val){

            var url = '${pageContext.request.contextPath}/strata/permit_RuangUdara?showEdit&id='+val;

            window.open(url, "etanah", "status=0,toolbar=0,location=0,menubar=0,width=800,height=350");

        }

    function deletePermit(val){
        if(confirm('Adakah anda pasti untuk memadam data ini?')) {
            var url = '${pageContext.request.contextPath}/strata/permit_RuangUdara?deletePermit&id=' + val;
            
             $.blockUI({
            message: $('#displayBox'),
            css: {
                top:  ($(window).height() - 50) /2 + 'px',
                left: ($(window).width() - 50) /2 + 'px',
                width: '50px'
            }
        });            
            
            $.get(url,
            function(data){
                $('#page_div').html(data);
                 $.unblockUI();
            },'html');
            //refreshPage4();
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

    function resetForm(){
        var url = "${pageContext.request.contextPath}/strata/permit_RuangUdara?rehydrate";
        $.post(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function simpanPermit(event, f){       
        var noBlok = document.getElementById("noBlok").value;
        var noTingkat = document.getElementById("noTingkat").value;
        var noPetak = document.getElementById("noPetak").value;
        var jenisStrukTambah = document.getElementById("jenisStrukTambah").value;        
        var kedudukan = document.getElementById("kedudukanStrukTambah").value;
        var bilStruktur = document.getElementById("bilStrukTambah").value;
        
        if ((jenisStrukTambah == 0))
        {
            alert('Sila masukkan jenis struktur tambah ');
            document.getElementById("jenisStrukTambah").focus();
            return false;
        }
        
        else if ((noBlok == ""))
        {
            alert('Sila Isikan Nombor Blok ');
            document.getElementById("noBlok").focus();
            return false;
        }
        else if ((noTingkat == ""))
        {
            alert('Sila masukkan nombor tingkat');
            document.getElementById("noTingkat").focus();
            return false;
        }
       <%-- if(jenisStrukTambah != "Bumbung" ){
            if ((noPetak == ""))
            {
                alert('Sila masukkan nombor petak ');
                document.getElementById("noPetak").focus();
                return false;
            }
        }  --%>
        
        if ((bilStruktur == ""))
        {
            alert('Sila masukkan bil struktur tambah ');
            document.getElementById("bilStrukTambah").focus();
            return false;
        }
        else
        {
            $.blockUI({
            message: $('#displayBox'),
            css: {
                top:  ($(window).height() - 50) /2 + 'px',
                left: ($(window).width() - 50) /2 + 'px',
                width: '50px'
            }
        });            
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
                $('#page_div').html(data);
                 $.unblockUI();
            },'html');
        }



    }
    function getbumbung() {
        var bumbung = document.getElementById("jenisStrukTambah").value;
        if (bumbung == 'Bumbung')
        {

            $('#petak').hide();
        }else{
            $('#petak').show();
        }
    }

</script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
<s:form name="form1" beanclass="etanah.view.strata.PermitRuangUdaraActionBean">
    <s:messages/>
    <s:errors/>
 <c:if test="${!DI}">
        <div class="subtitle">
            <fieldset class="aras1">
                <br /><br /> <p><font size="3"><b>Makluman:</b> </font><font color="red" size="3"><b>Maaf. Permohonan ini tidak mempunyai Permit Ruang Udara </b></font></p>
            </fieldset>
        </div>
    </c:if>
    <br /> <br />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Senarai Permit
            </legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.permohanPermitList}" cellpadding="0" cellspacing="0" id="line">
                    <%--<display:column property="bilPermit" title="Bil" sortable="true" style="vertical-align:top;">${line_rowNum}</display:column >--%>
                    <display:column title="Bil" style="text-align:center">${line_rowNum}</display:column>
                    <%--<display:column property="hakmilikPermohonan.hakmilik.noLot" title="No.Lot" style="vertical-align:top;"/>--%>            
                    <display:column property="noBlok" title="Blok (M)" style="vertical-align:top;"/>
                    <display:column property="noTingkat" title="No. Tingkat" style="width:80px;vertical-align:top;"></display:column>
                    <display:column property="noPetak" title="No. Petak" style="width:90px;vertical-align:top;"></display:column>
                     <display:column property="jenisStrukTambah" title="Jenis Struktur Tambahan" style="vertical-align:top;"/>
                    <display:column property="bilPermit" title="Bil. Permit" style="vertical-align:top;"></display:column>
                    <%--<display:column title="Semak" sortable="true"><div align="center"><s:checkbox name="noAkaun" value="" onclick="" /></div></display:column>--%>
                    <display:column title="Padam"><div align="center">
                            <%--  <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                   id='rem_${line_rowNum}' title="Klik Untuk Kemaskini"
                                   onclick="test('${line.idMpermitBtr}');"
                                   onmouseover="this.style.cursor='pointer';">--%>
                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/pub/images/not_ok.gif' class='rem'
                                 id='rem_${line_rowNum}' title="Klik Untuk Hapus"
                                 onclick="deletePermit('${line.idMpermitBtr}');"
                                 onmouseover="this.style.cursor='pointer';"></div>
                        </display:column>
                        <display:column title="Kemaskini"><div align="center">
                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                 id='rem_${line_rowNum}' title="Klik Untuk Kemaskini" onclick="kemaskiniPermit('${line.idMpermitBtr}')" onmouseover="this.style.cursor='pointer';">
                        </div>
                    </display:column>


                </display:table>
            </div></fieldset></div>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Permit Ruang Udara</legend><br>
            <div class="content" align="center">
                <table border="0" width="80%">

                    <tr><td width="20%"><b>Jenis Struktur Tambahan</b></td>
                        <td><b>:</b></td>
<!--                        <td><s:text name="permohanPermit.jenisStrukTambah" id="jenisStrukTambah" value="" class="normal_text"></s:text></td>-->
                        <td> 
                            <s:select name="permohanPermit.jenisStrukTambah"  id="jenisStrukTambah" style="width:150px;" onchange="getbumbung();" >
                                <s:option  value="0">Sila Pilih</s:option>
                                <s:options-collection collection="${actionBean.senaraikodGunaRuangUdara}" label="nama" value="nama"/>
                            </s:select>
                        </td>
                    </tr>
                    <!--                    <tr><td width="20%"><b>Blok (M)</b></td>
                                            <td><b>:</b></td>
                                            <td><s:text name="permohanPermit.noBlok" id="noBlok" onkeyup="validateNumber(this,this.value);"/></td>
                                        </tr>
                                        <tr><td width="20%"><b>No. Tingkat</b></td>
                                            <td><b>:</b></td>
                                            <td><s:text name="permohanPermit.noTingkat" id="noTingkat" value="" class="normal_text" onkeyup="validateNumber(this,this.value);"></s:text></td>
                                        </tr>
                                        <tr><td width="20%"><b>No. Petak (Bilangan Struktur)</b></td>
                                            <td><b>:</b></td>
                                            <td><s:text name="permohanPermit.noPetak" id="noPetak" value="" class="normal_text" onkeyup="validateNumber(this,this.value);"></s:text></td>
                                        </tr>-->



                    <!--                    <tr><td width="20%"><b>Keadaan Struktur Tambahan</b></td>
                                            <td><b>:</b></td>
                                            <td><s:text name="permohanPermit.kedudukanStrukTambah" id="kedudukanStrukTambah" value="" class="normal_text"></s:text></td>
                                        </tr>-->
                    <tr><td width="20%" valign="top"><b>Kedudukan Struktur</b></td>
                        <td valign="top"><b></b></td>
                        <td>

                        </td>
                    </tr>

                    <tr>
                        <td>
                            <table width="100%">
                                <tr>
                                    <td width="40%">
                                    </td>
                                    <td align="left">
                                        <b> 1) No Blok (M)</b>
                                    </td>
                                </tr>
                            </table>
                        </td>
                        <td><b>:</b></td>
                        <td><s:text name="permohanPermit.noBlok" id="noBlok" onkeyup="validateNumber(this,this.value);"/></td>
                    </tr>
                    <tr>
                        <td>
                            <table width="100%">
                                <tr>
                                    <td width="40%">
                                    </td>
                                    <td align="left">
                                        <b>2) No Tingkat</b>
                                    </td>
                                </tr>
                            </table>
                        </td>
                        <td><b>:</b></td>
                        <td>
                            <s:text name="permohanPermit.noTingkat" id="noTingkat" value="" class="normal_text" onkeyup="validateNumber(this,this.value);"></s:text>
                        </td>
                    </tr>
                    <tr id="petak">

                        <td>
                            <table width="100%">
                                <tr>
                                    <td width="40%">
                                    </td>
                                    <td align="left">
                                        <b>3) No Petak</b>
                                    </td>

                                </tr>

                            </table>
                        </td>
                        <td><b>:</b></td>
                        <td>
                            <s:text name="permohanPermit.noPetak" id="noPetak" value="" class="normal_text" onkeyup="validateNumber(this,this.value);"></s:text>
                        </td>
                        </div>
                    </tr>
                    <tr><td width="20%"><b>Dibina oleh (nama pemaju)</b></td>
                        <td><b>:</b></td>
                        <td><s:text name="permohanPermit.dibinaOleh" id="dibinaOleh" value="${actionBean.pemohan.pihak.nama}" class="normal_text" style="width=300px" ></s:text></td>
                        <%--<td>${actionBean.pemohan.pihak.nama}</td>--%>
                        <td></td>
                    </tr>
                    <tr>
                        <td width="20%"><b>Kedudukan Terunjur</b></td>
                        <td><b>:</b></td>
                        <td><s:radio name="kedudukanStrukTambah" id="kedudukanStrukTambah" value="Simpanan Jalan" /> Simpanan Jalan  
                        </td>
                    </tr>
                    <tr>
                        <td width="20%"></td>
                        <td></td>
                        <td><s:radio name="kedudukanStrukTambah" id="kedudukanStrukTambah" value="Simpanan Laluan" /> Simpanan Laluan
                        </td>
                    </tr>
                    <tr>
                        <td width="20%"><b>Bil. Struktur Tambahan</b></td>
                        <td><b>:</b></td>
                        <td><s:text name="permohanPermit.bilStrukTambah" id="bilStrukTambah" value="" class="normal_text" onchange="getbs()" onkeyup="validateNumber(this,this.value);"></s:text></td>
                    </tr>
                    <tr>
                        <td width="20%"><b>Bil. Permit</b></td>
                        <td><b>:</b></td>
                        <td><s:text name="permohanPermit.bilPermit" id="bilPermit" value="" class="normal_text" readonly="readonly" ></s:text></td>
                    </tr>
                </table> <br>
            </div></fieldset></div>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Bayaran Permit</legend><br>
            <div class="content" align="center">
                <table border="0" width="80%">
                    <tr><td width="20%"><b>Jumlah Bilangan Permit</b></td>
                        <td><b>:</b></td>
                        <td><s:text name="p" value="" id="jbp" class="normal_text" readonly="readonly"></s:text></td>
                    </tr>
                    <tr><td width="20%"><b>Jumlah Bayaran (RM)</b></td>
                        <td><b>:</b></td>
                        <td><s:text name="jumlahBayaran" value="" id="jb" class="normal_text" readonly="readonly"></s:text></td>
                    </tr>
                </table></div>
        </fieldset></div><br>
   <c:if test="${DI}">
        <p align="center">
            <s:button name="simpanPermitRuang" id="save" value="Simpan" class="btn" onclick="simpanPermit(this.name, this.form);" />
            <s:button class="btn" value="Isi Semula" name="new" id="new" onclick="resetForm()"/>
        </p>
    </c:if>
</s:form>