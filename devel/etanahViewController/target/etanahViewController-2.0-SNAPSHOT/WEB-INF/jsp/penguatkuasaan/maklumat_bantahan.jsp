<%--
Document : maklumat_bantahan
Created on : Mar 3, 2011, 10:02:07 AM
Author : ctzainal
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<script type ="text/javascript">
    $(document).ready(function() {
        $('#kadPengenalan').hide();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    });

    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }

    function validateTelLength(value){
        var plength = value.length;
        if(plength < 7){
            alert('No. Telefon yang dimasukkan salah.');
            $('#noTelefon1').val("");
            $('#noTelefon1').focus();
            $('#noTelefon2').val("");
            $('#noTelefon2').focus();
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

    function validatePhkBerkepentingan(){
        
        if($('#cara').val()=="")
        {
            alert('Sila pilih Kategori Pembantah terlebih dahulu');
            return false;
        }
     
    <c:if test="${empty actionBean.pihak.kodJantina}">
            var jantina = document.getElementById('jantinaPihak');
            if(jantina.value == "" ){
                alert("Sila Masukkan Jantina");
                $('#jantinaPihak').focus();
                return false;
            }
    </c:if>
            if($('#kaitanPihak').val()=="")
            {
                alert('Sila masukkan Hubungan terlebih dahulu');
                return false;
            }


            return true;
        }

        function validateLain(){
            if($('#cara').val()=="P")
            {
                if($('#noPengenalan11').val()=="")
                    alert('Sila masukkan Nombor Pengenalan terlebih dahulu');
                $('#noPengenalan11').focus();
                return false;
            }

            if($('#cara').val()=="")
            {
                alert('Sila pilih Kategori Pembantah terlebih dahulu');
                $('#cara').focus();
                return false;
            }

            if($('#nama').val()=="")
            {
                alert('Sila masukkan Nama terlebih dahulu');
                $('#nama').focus();
                return false;
            }

            if($('#jenisPengenalan').val()!="B" && $('#noPengenalanLain').val()=="")
            {
                alert('Sila masukkan Nombor Pengenalan lain terlebih dahulu');
                $('#noPengenalanLain').focus();
                return false;
            }

            if($('#jenisPengenalan').val()=="B" && $('#noPengenalanLainBaru').val()=="")
            {
                alert('Sila masukkan Nombor Pengenalan baru terlebih dahulu');
                $('#noPengenalanLainBaru').focus();
                return false;
            }
        
            var jantinaLain = document.getElementById('jantina');
            if(jantinaLain.value == ""){
                alert("Sila Masukkan Jantina");
                $('#jantina').focus();
                return false;
            }

            if($('#kaitan1').val()=="")
            {
                alert('Sila masukkan Hubungan terlebih dahulu');
                $('#kaitan1').focus();
                return false;
            }

            return true;

        }


   

        function checking (value){
            if(value == "P"){
                $('#kadPengenalan').show();
                //reset value in lain-lain
                document.getElementById('kaitan1').value = '';
                document.getElementById('noPengenalanLain').value = '';
                document.getElementById('noPengenalan11').value = '';
                document.getElementById('nama').value = '';
                document.getElementById('jenisPengenalan').value = '';
                document.getElementById('alamat1').value = '';
                document.getElementById('alamat2').value = '';
                document.getElementById('alamat3').value = '';
                document.getElementById('alamat4').value = '';
                document.getElementById('poskod').value = '';
                document.getElementById('negeri').value = '';
                document.getElementById('jantina').value = '';
                document.getElementById('noTelefon1').value = '';
                document.getElementById('noTelefon2').value = '';
                document.getElementById('catatan').value = '';
                document.getElementById('tarikhBantahanLain').value = '';

                $("#kaitan1").attr("disabled", "disabled");
                $("#noPengenalanLain").attr("disabled", "disabled");
                $("#nama").attr("disabled", "disabled");
                $("#jenisPengenalan").attr("disabled", "disabled");
                $("#alamat1").attr("disabled", "disabled");
                $("#alamat2").attr("disabled", "disabled");
                $("#alamat3").attr("disabled", "disabled");
                $("#poskod").attr("disabled", "disabled");
                $("#negeri").attr("disabled", "disabled");
                $("#jantina").attr("disabled", "disabled");
                $("#noTelefon1").attr("disabled", "disabled");
                $("#noTelefon2").attr("disabled", "disabled");
                $("#catatan").attr("disabled", "disabled");
                $("#tarikhBantahanLain").attr("disabled", "disabled");

                $("#simpan").attr("disabled", "disabled");
            }
            if(value =="L"){
                $('#kadPengenalan').hide();

                if( $('#idLain').val() != ""){
                    var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_bantahan?redirectPage';
                    $.get(url,
                    function(data){
                        $('#page_div').html(data);
                    },'html');

                }
                
                $("#kaitan1").removeAttr("disabled");
                $("#noPengenalanLain").removeAttr("disabled");
                $("#nama").removeAttr("disabled");
                $("#jenisPengenalan").removeAttr("disabled");
                $("#alamat1").removeAttr("disabled");
                $("#alamat2").removeAttr("disabled");
                $("#alamat3").removeAttr("disabled");
                $("#poskod").removeAttr("disabled");
                $("#negeri").removeAttr("disabled");
                $("#jantina").removeAttr("disabled");
                $("#noTelefon1").removeAttr("disabled");
                $("#noTelefon2").removeAttr("disabled");
                $("#catatan").removeAttr("disabled");
                $("#tarikhBantahanLain").removeAttr("disabled");

                $("#simpan").removeAttr("disabled");
            }
        }

        function refreshPages(){
            var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_bantahan?redirectPage';
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }


        function findJenisPengenalan(){
   
            if($('#jenisPengenalan').val() == 'B'){
                document.getElementById("noPengenalanBaru").style.visibility = 'visible';
                document.getElementById("noPengenalanBaru").style.display = '';
                $('#noPengenalanLainLain').hide();
            }else{
                $('#noPengenalanLainLain').show();
                document.getElementById("noPengenalanBaru").style.visibility = 'hidden';
                document.getElementById("noPengenalanBaru").style.display = 'none';
            }
        }

        function validateNoPengenalan(){
    <c:if test="${actionBean.flagSearch eq true}">
            if ($('#noPengenalan').val() == '') {
                alert("Sila masukkan no pengenalan terlebih dahulu sebelum tekan butang cari.");
                $('#noPengenalan').focus();
                return false;
            }
    </c:if>

    <c:if test="${actionBean.flagSearch ne true}">
            if ($('#noPengenalan11').val() == '') {
                alert("Sila masukkan no pengenalan terlebih dahulu sebelum tekan butang cari.");
                $('#noPengenalan11').focus();
                return false;
            }
    </c:if>

            return true;
        }
   
</script>
<s:form beanclass="etanah.view.penguatkuasaan.MaklumatBantahanActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">

        <fieldset class="aras1">
            <legend>Maklumat Bantahan</legend>
            <div class="instr-fieldset">
                <font color="red">PERINGATAN:</font> Sila masukkan no.pengenalan dan tekan butang cari jika anda pilih pihak berkepentingan dalam kategori pembantah.
            </div>&nbsp;<br/>

            <p>
                <label><em>*</em>Kategori Pembantah :</label>
                <s:select name="kategoriPembantah"  onchange ="checking(this.value)" style="width:180px;" id="cara">
                    <s:option value="">--Sila Pilih--</s:option>
                    <s:option value="P">Pihak Berkepentingan</s:option>
                    <s:option value="L">Lain-lain</s:option>

                </s:select>&nbsp;
            </p>
            <c:if test="${actionBean.flagSearch eq true}">
                <p align="left">
                    <label><em>*</em>No.Pengenalan :</label>
                    <s:text name="noPengenalan" id="noPengenalan" maxlength="12" onkeyup="validateNumber(this,this.value);"/>&nbsp;&nbsp;
                    <s:button name="searchNoPengenalan" value="Cari" class="btn" onclick="if(validateNoPengenalan()) doSubmit(this.form, this.name, 'page_div')"/>&nbsp;&nbsp;
                </p>
            </c:if>
            <c:if test="${actionBean.flagSearch eq false}">
                <div id="kadPengenalan" >
                    <p align="left">
                        <label><em>*</em>No.Pengenalan :</label>
                        <s:text name="noPengenalan" id="noPengenalan11" maxlength="12" onkeyup="validateNumber(this,this.value);"/>&nbsp;&nbsp;
                        <s:button name="searchNoPengenalan" value="Cari" class="btn" onclick="if(validateNoPengenalan()) doSubmit(this.form, this.name, 'page_div')"/>&nbsp;&nbsp;
                    </p>
                </div>
            </c:if>


            <c:if test="${actionBean.flag eq true}">
                <div id="pihakBerkepentingan">
                    <p>
                        <label>Nama :</label>
                        <c:if test="${actionBean.pihak.nama ne null}"> ${actionBean.pihak.nama}&nbsp; </c:if>
                        <c:if test="${actionBean.pihak.nama eq null}"> Tiada Data </c:if>
                    </p>
                    <p>
                        <label>Jenis Pengenalan :</label>
                        <c:if test="${actionBean.pihak.jenisPengenalan.nama ne null}"> ${actionBean.pihak.jenisPengenalan.nama}&nbsp; </c:if>
                        <c:if test="${actionBean.pihak.jenisPengenalan.nama eq null}"> Tiada Data </c:if>
                    </p>
                    <p>
                        <label>No.Pengenalan :</label>
                        <c:if test="${actionBean.pihak.noPengenalan ne null}"> ${actionBean.pihak.noPengenalan}&nbsp; </c:if>
                        <c:if test="${actionBean.pihak.noPengenalan eq null}"> Tiada Data </c:if>
                    </p>
                    <p>
                        <label>Alamat :</label>
                        ${actionBean.pihak.alamat1}&nbsp;
                    </p>
                    <p>
                        <label> &nbsp;</label>
                        ${actionBean.pihak.alamat2}&nbsp;
                    </p>
                    <p>
                        <label> &nbsp;</label>
                        ${actionBean.pihak.alamat3}&nbsp;
                    </p>
                    <p>
                        <label> &nbsp;</label>
                        ${actionBean.pihak.alamat4}&nbsp;
                    </p>
                    <p>
                        <label>Poskod :</label>
                        ${actionBean.pihak.poskod}&nbsp;
                    </p>
                    <p>
                        <label>Negeri :</label>
                        ${actionBean.pihak.negeri.nama}&nbsp;

                    </p>
                    <p>
                        <c:choose>
                            <c:when test="${actionBean.pihak.kodJantina ne null}">

                                <label>Jantina :</label>
                                <c:if test="${actionBean.pihak.kodJantina eq '0'}">Tidak Berkenaan</c:if>
                                <c:if test="${actionBean.pihak.kodJantina eq '1'}">Lelaki</c:if>
                                <c:if test="${actionBean.pihak.kodJantina eq '2'}">Perempuan</c:if>
                                <c:if test="${actionBean.pihak.kodJantina eq '3'}">Tidak dikenalpasti</c:if>
                                &nbsp;
                            </c:when>
                            <c:otherwise>
                                <label><em>*</em>Jantina :</label>
                                <s:select name="jantinaPihak" id="jantinaPihak">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${listUtil.senaraiKodJantina}" label="nama" value="kod"/>
                                </s:select>
                            </c:otherwise>
                        </c:choose>
                    </p>
                    <p>
                        <label>Telefon Bimbit :</label>
                        <c:if test="${actionBean.pihak.noTelefon1 ne null}"> ${actionBean.pihak.noTelefon1}&nbsp; </c:if>
                        <c:if test="${actionBean.pihak.noTelefon1 eq null}"> Tiada Data </c:if>

                    </p>
                    <p>
                        <label>Telefon Pejabat :</label>
                        <c:if test="${actionBean.pihak.noTelefon2 ne null}"> ${actionBean.pihak.noTelefon2}&nbsp; </c:if>
                        <c:if test="${actionBean.pihak.noTelefon2 eq null}"> Tiada Data </c:if>
                    </p>
                    <p>
                        <label><em>*</em>Hubungan :</label>
                        <s:text name="kaitan" id="kaitanPihak" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label>Tarikh Bantahan:</label>
                        <s:text name="tarikhBantahan" class="datepicker" id="tarikhBantahan" formatPattern="dd/MM/yyyy" style="width:100px;"/>
                        &nbsp;
                    </p>
                    <p>
                        <label>Catatan :</label>
                        <s:textarea name="catatan" rows="3" cols="50" id="catatan"/>
                    </p>

                    <p align="right">
                        <s:button class="btn" onclick="if(validatePhkBerkepentingan()) doSubmit(this.form, this.name, 'page_div');" name="simpanPihakBerkepentingan" value="Simpan"/>
                    </p>
                </div>
            </c:if>

            <c:if test="${actionBean.flag eq false}">

                <div id="lainLain">
                    <p>
                        <label><em>*</em>Nama :</label>
                        <s:text name="nama" id="nama" size="40" onkeyup="dodacheck(this.value);this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label><em>*</em>Jenis Pengenalan :</label>
                        <s:select name="jenisPengenalan" id="jenisPengenalan" onchange="findJenisPengenalan()">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                        </s:select>
                    </p>

                    <p id="noPengenalanLainLain">
                        <label><em>*</em>No.Pengenalan :</label>
                        <s:text name="noPengenalan" id="noPengenalanLain" maxlength="12" />
                        <font color="red" size="1">cth : 860712335184 </font>
                        &nbsp;
                    </p>
                    <p id="noPengenalanBaru" style="visibility: hidden; display: none">
                        <label><em>*</em>No.Pengenalan :</label>
                        <s:text name="noPengenalan" id="noPengenalanLainBaru" maxlength="12" onkeyup="validateNumber(this,this.value);"/>
                        <font color="red" size="1">cth : 860712335184 </font>
                        &nbsp;
                    </p>

                    <%--<p>
                        <label><em>*</em>No.Pengenalan :</label>
                        <s:text name="noPengenalan" id="noPengenalanLain" maxlength="12" onkeyup="dodacheck(this.value);this.value=this.value.toUpperCase();"/>
                        <font color="red" size="1">cth : 860712335184 </font>&nbsp;
                    </p>--%>


                    <p>
                        <label>Alamat :</label>
                        <s:text name="alamat1" id="alamat1" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label> &nbsp;</label>
                        <s:text name="alamat2" id="alamat2" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label> &nbsp;</label>
                        <s:text name="alamat3" id="alamat3" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label> &nbsp;</label>
                        <s:text name="alamat4" id="alamat4" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label>Poskod :</label>
                        <s:text name="poskod" id="poskod" onkeyup="validateNumber(this,this.value);" maxlength="5"/>
                    </p>
                    <p>
                        <label>Negeri :</label>
                        <s:select name="kodNgri" id="negeri">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                        </s:select>&nbsp;
                    </p>
                    <p>
                        <label><em>*</em>Jantina :</label>
                        <s:select name="jantina" id="jantina">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodJantina}" label="nama" value="kod"/>
                        </s:select>
                    </p>
                    <p>
                        <label>Telefon Bimbit :</label>
                        <s:text name="noTelefon1" id="noTelefon1" size="19" maxlength="12" onkeyup="validateNumber(this,this.value);" onchange="validateTelLength(this.value);"/>
                    </p>
                    <p>
                        <label>Telefon Pejabat :</label>
                        <s:text name="noTelefon2" id="noTelefon2" size="19" maxlength="12" onkeyup="validateNumber(this,this.value);" onchange="validateTelLength(this.value);"/>
                    <p>
                        <label><em>*</em>Hubungan :</label>
                        <s:text name="kaitan" id="kaitan1" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label>Tarikh Bantahan:</label>
                        <s:text name="tarikhBantahan" class="datepicker" id="tarikhBantahanLain" formatPattern="dd/MM/yyyy" style="width:100px;"/>
                        &nbsp;
                    </p>
                    <p>
                        <label>Catatan :</label>
                        <s:textarea name="catatan" rows="3" cols="50" id="catatan"/>
                    </p>
                    <p align="right">
                        <s:button class="btn" onclick="if(validateLain()) doSubmit(this.form, this.name, 'page_div');" name="simpan" value="Simpan" id="simpan"/>
                        <s:hidden name = "permohonanPihakMembantah.idMohonPihakBantah" id="idLain"/>


                    </p>
                </div>
            </c:if>
        </fieldset>



    </div>
</s:form>