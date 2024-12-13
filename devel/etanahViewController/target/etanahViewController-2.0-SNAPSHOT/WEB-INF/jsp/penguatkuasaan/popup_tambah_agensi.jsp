<%--
   Document   : popup_tambah_agensi
   Created on : Aug 4, 2011, 10:32:52 AM
   Author     : latifah.iskak

--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<html><head>
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
        <script type="text/javascript">

            function limitText(limitField, limitNum) {
                if (limitField.value.length > limitNum) {
                    limitField.value = limitField.value.substring(0, limitNum);
                }
            }
            
            function save(event, f){

                var q = $(f).formSerialize();
                var url = f.action + '?' + event;
                $.post(url,q,
                function(data){
                    $('#agensiDiv',opener.document).html(data);
                    self.opener.refreshPageKemasukanLaporanOperasi();
                    alert("Maklumat telah berjaya disimpan.");
                    self.close();
                },'html');

            }
            <%--function validateNumber(elmnt,content) {
                //if it is character, then remove it..
                if (isNaN(content)) {
                    elmnt.value = removeNonNumeric(content);
                    return;
                }
            }--%>

                function validateNumber(elmnt,content) {

                    //if it is fullstop (.) , then remove it..
                    for( var i = 0; i < content.length; i++ )
                    {
                        var str = "";
                        str = content.substr( i, 1 );
                        if(str == "."){
                            elmnt.value = removeNonNumeric(content);
                            return;
                        }
                    }

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
                function validateForm(){
                    if(confirm('Adakah anda pasti untuk simpan?')) {
                        return true;
                    }
                }
                function test(f) {
                    $(f).clearForm();
                }
                function validateTelLength(value){
                    var plength = value.length;
                    if(plength < 7){
                        alert('No. Telefon yang dimasukkan salah.');
                        $('#telefon').val("");
                        $('#telefon').focus();
                    }
                }

                function validatePoskodLength(value){
                    var plength = value.length;
                    if(plength != 5){
                        alert('Poskod yang dimasukkan salah.');
                        $('#poskod').val("");
                        $('#poskod').focus();
                    }
                }

                function jenisPengenalanAgensi(){
                    if($('#pengenalanAgensi').val() == 'B'){
                        document.getElementById("noPengenalanBaruAgensi").style.visibility = 'visible';
                        document.getElementById("noPengenalanBaruAgensi").style.display = '';
                        $('#noPengenalanLainAgensi').hide();
                         $('#penyerahNoPengenalanLainAgensi').attr("disabled", true);
                         $('#penyerahNoPengenalanBaruAgensi').attr("disabled", false);
                    }else{
                        $('#noPengenalanLainAgensi').show();
                        document.getElementById("noPengenalanBaruAgensi").style.visibility = 'hidden';
                        document.getElementById("noPengenalanBaruAgensi").style.display = 'none';
                        $('#penyerahNoPengenalanBaruAgensi').attr("disabled", true);
                        $('#penyerahNoPengenalanLainAgensi').attr("disabled", false);
                       }
                }

                function findDOBW(){
                    var noPengenalanAgensi = $('#penyerahNoPengenalanBaruAgensi').val();
                    if(noPengenalanAgensi.length =="12"){

                        //tahun
                        var year = noPengenalanAgensi.substr(0, 2);
                        var tahun = "19"+year;
                        var selectKodUrusan = document.getElementById('tahun');
                           selectKodUrusan.selectedIndex = 0;
                          for (var i = 0; i < selectKodUrusan.options.length; ++i){
                            if (selectKodUrusan.options[i].value == tahun){
                                selectKodUrusan.selectedIndex = i;
                                break;
                            }
                          }

                        //month
                        var month = noPengenalanAgensi.substr(2, 1);
                        var bulan = "";
                        if( month == "0"){
                            bulan = noPengenalanAgensi.substr(3, 1);
                        }
                        else{
                            bulan= noPengenalanAgensi.substr(2, 2);
                            if(bulan >12){
                                alert("Sila masukkan bulan yang betul.");
                            }
                        }
                        var selectKodUrusan = document.getElementById('bulan');
                           selectKodUrusan.selectedIndex = 0;
                             for (var i = 0; i < selectKodUrusan.options.length; ++i){
                                               if (selectKodUrusan.options[i].value == bulan){
                                                       selectKodUrusan.selectedIndex = i;
                                                       break;
                                               }
                                       }
                        //day
                        var hari = noPengenalanAgensi.substr(4,1);
                        var day ="";
                        if(hari == "0"){

                            day=noPengenalanAgensi.substr(5,1);
                        }
                        else{
                            day=noPengenalanAgensi.substr(4,2);
                            if(day>31){
                                alert("Sila masukkan hari yang betul.");
                            }

                        }

                        var selectKodUrusan = document.getElementById('hari');
                                       selectKodUrusan.selectedIndex = 0;
                                       for (var i = 0; i < selectKodUrusan.options.length; ++i){
                                               if (selectKodUrusan.options[i].value == day){
                                                       selectKodUrusan.selectedIndex = i;
                                                       break;
                                               }
                                       }
                    }else{
                        alert("Sila masukkan 12 digit nombor pengenalan");
                    }
                           }

        </script>

    </head><body>
        <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>
        <s:form  beanclass="etanah.view.penguatkuasaan.KemasukanLaporanOperasi" name="form1">

            <s:messages/>
            <div class="instr" align="center">
                <s:errors/>
            </div>
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend> Maklumat Agensi</legend>
                    <div class="instr-fieldset">
                        <font color="red">PERINGATAN:</font>Sila Masukkan Maklumat Berikut.
                    </div>&nbsp;

                    <p>
                        <label>Nama Agensi:</label>
                        ${actionBean.namaAgensi}&nbsp;
                        <s:hidden name="kodAgensi" id="kodAgensi"/>
                        <s:hidden name="namaAgensi" id="namaAgensi"/>
                        <s:hidden name="id" id="id"/>
                        <s:hidden name="idOpsAgen"/>
                    </p>

                    <p>
                        <label><em>*</em>Nama Ketua Agensi/Wakil:</label>
                        <s:text name="namaKetuaAgensi" id="namaKetuaAgensi" size="30" onchange="this.value=this.value.toUpperCase();"/>&nbsp;
                    </p>

                    <p>
                        <label>Jenis Pengenalan :</label>
                        <s:select name="jenisPengenalan"  style="width:139px;" id="pengenalanAgensi" onchange="jenisPengenalanAgensi()">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod" sort="nama" />
                        </s:select>
                        &nbsp;
                    </p>
                    <%-- <p>
                         <label>No.Pengenalan :</label>
                         <s:text name="noPengenalan" maxlength="12" />
                         <font color="red" size="1">cth : 850510075342 </font>&nbsp;
                     </p>--%>
                    <p id="noPengenalanLainAgensi">
                        <label>No.Pengenalan :</label>
                        <s:text name="noPengenalan" id="penyerahNoPengenalanLainAgensi" maxlength="12" onkeyup="this.value=this.value.toUpperCase();" />
                        <%--<font color="red" size="1">cth : 850510075342 </font>--%>
                        &nbsp;
                    </p>

                    <p id="noPengenalanBaruAgensi" style="visibility: hidden; display: none">
                        <label>No.Pengenalan :</label>
                        <s:text name="noPengenalan" id="penyerahNoPengenalanBaruAgensi" maxlength="12" onkeyup="validateNumber(this,this.value);" onblur="javascript:findDOBW(this.value)"/>
                        <font color="red" size="1">cth : 850510071213 </font>
                        &nbsp;
                    </p>
                    <p>
                        <label>Jawatan :</label>
                        <s:text name="jawatan" id="jawatan" size="30" onchange="this.value=this.value.toUpperCase();"/>&nbsp;
                    </p>
                    <p>
                        <label>No Telefon :</label>
                        <s:text name="noTelefon" maxlength="12" onkeyup="validateNumber(this,this.value);" onchange="validateTelLength(this.value);"/>&nbsp;
                    </p>
                    <p>
                        <label>Email :</label>
                        <s:text name="email" id="email" size="40" maxlength="100" onblur="return ValidateEmail()"/>

                    </p>

                </fieldset >
                <c:if test="${edit}">
                    <p align="center">
                        <s:button  name="reset" value="Isi Semula" class="btn" onclick="return test();"/>
                        <s:button class="btn" value="Kemaskini" name="editAgensi"  onclick="if(validateForm())save(this.name,this.form);" />
                        <s:submit class="btn"  value="Tutup" name="closePopup" onclick="self.opener.refreshPage();self.close();" />
                    </p>
                </c:if>

                <c:if test="${add}">
                    <p align="center">
                        <s:button  name="reset" value="Isi Semula" class="btn" onclick="return test();"/>
                        <s:button class="btn" value="Simpan" name="saveAgensi"  onclick="if(validateForm())save(this.name,this.form);" />
                        <s:submit class="btn"  value="Tutup" name="closePopup" onclick="self.opener.refreshPage();self.close();" />
                    </p>
                </c:if>

                <br>
            </div>
        </s:form>
    </body>
</html>