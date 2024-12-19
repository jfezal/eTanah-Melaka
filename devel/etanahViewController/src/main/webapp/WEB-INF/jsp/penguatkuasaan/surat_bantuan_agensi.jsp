<%-- 
    Document   : surat_bantuan_agensi
    Created on : Feb 22, 2010, 11:44:56 AM
    Author     : farah.shafilla
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<style type="text/css">

    .tablecloth{
        padding:0px;
        width:70%;
    }

    .infoLP{
        float: right;
        font-weight: bold;
        color:#003194;
        font-family:Tahoma;
        font-size: 13px;

    }
</style>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    $(document).ready(function(e){
    <c:if test="${actionBean.kodNegeri eq '05'}">
        <c:choose>
            <c:when test="${actionBean.hideTab}">
                    $("#page_id_7").hide();
                    $("#page_id_8").hide();
                    $("#page_id_10").hide();
            </c:when>
            <c:otherwise>
                    $("#page_id_7").show();
                    $("#page_id_8").show();
                    $("#page_id_10").show();
            </c:otherwise>
        </c:choose>
    </c:if>
        });
        function removeSingle(idOperasiAgensi)
        {
            if(confirm('Adakah anda pasti untuk hapus?')) {
                var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_agensi?deleteSingle&idOperasiAgensi='
                    +idOperasiAgensi;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');}
            self.opener.refreshPageOperasi();
        }

        function removeAgensi(idOperasiAgensi)
        {
            if(confirm('Adakah anda pasti untuk hapus?')) {
                var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_agensi?removeAgensi&idOperasiAgensi='
                    +idOperasiAgensi;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');}
        }
        function refreshPageOperasi(){
            var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_agensi?refreshpage';
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
        function validateForm(){

            if($('#agen').val()=="")
            {
                alert("Sila Pilih Kod Agensi");
                $('#agen').focus();
                return false;
            }

            if($('#tarikh').val()=="")
            {
                alert("Sila Pilih Tarikh Berkumpul");
                $('#tarikh').focus();
                return false;
            }

            if($('#jam').val()=="0" || $('#masa').val()=="0" || $('#ampm').val()=="0")
            {
                alert("Sila Pilih Masa");
                return false;
            }
      
            return true;
        }
        function validateForm1(){

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
            return true;
        }

        function ValidateEmail(){
            var emailID= $("#email").val();

            if ((emailID==null)||(emailID=="")){
                return true;
            }
            if ((emailID!=null)||(emailID!="")){
                if(emailcheck(emailID)==false){
                    $("#email").val("");
                    $("#email").focus();
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
    
        function validateFormMesyuarat(){

            if($('#tarikh').val()=="")
            {
                alert("Sila Pilih Tarikh Mesyuarat");
                $('#tarikh').focus();
                return false;
            }

            if($('#jam').val()=="0")
            {
                alert("Sila Pilih Jam");
                $('#jam').focus();
                return false;
            }

            if($('#masa').val()=="0")
            {
                alert("Sila Pilih Minit");
                $('#masa').focus();
                return false;
            }

            if($('#ampm').val()=="0")
            {
                alert("Sila Pilih AM/PM");
                $('#ampm').focus();
                return false;
            }
        
            if($('#tempat').val()=="")
            {
                alert("Sila Pilih Tempat Mesyuarat");
                $('#tempat').focus();
                return false;
            }
            return true;
        }


        function test(f) {
            $(f).clearForm();
        }
        function validateTelLength(value){
            var plength = value.length;
            if(plength < 7){
                alert('No. Telefon yang dimasukkan salah.');
                $('#noTel').val("");
                $('#noTel').focus();
            }
        }

        function limitText(limitField, limitNum) {
            if (limitField.value.length > limitNum) {
                limitField.value = limitField.value.substring(0, limitNum);
            }
        }

        function validateAddAgensi(){

            if($('#agen').val()=="")
            {
                alert("Sila Pilih Kod Agensi");
                $('#agen').focus();
                return false;
            }

            if($('#tarikh').val()=="")
            {
                alert("Sila Pilih Tarikh Mesyuarat");
                $('#tarikh').focus();
                return false;
            }

            if($('#jam').val()=="0")
            {
                alert("Sila Pilih Jam");
                $('#jam').focus();
                return false;
            }

            if($('#masa').val()=="0")
            {
                alert("Sila Pilih Minit");
                $('#masa').focus();
                return false;
            }

            if($('#ampm').val()=="0")
            {
                alert("Sila Pilih AM/PM");
                $('#ampm').focus();
                return false;
            }

            if($('#tempat').val()=="")
            {
                alert("Sila Pilih Tempat Mesyuarat");
                $('#tempat').focus();
                return false;
            }
            return true;
        }
    
        function addRecord(id){
        
            var url = "${pageContext.request.contextPath}/penguatkuasaan/maklumat_agensi?addRecord&idOp="+id;

            params  = 'width='+screen.width;
            params += ', height='+screen.height;
            params += ', top=0, left=0'
            params += ', fullscreen=no';
            params += ', directories=no';
            params += ', location=no';
            params += ', menubar=no';
            params += ', resizable=no';
            params += ', scrollbars=yes';
            params += ', status=no';
            params += ', toolbar=no';
            newwin=window.open(url,'PopUp', params);
            if (window.focus) {newwin.focus()}
            return false;
        }
    
        function refreshPageKpsnMesyuarat(){
            var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_agensi?refreshpageNotis';
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
    
        function viewRecordAgensi(id){
            var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_agensi?viewMaklumatAgensi&idAgensi='+id;
            window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=900,height=300,scrollbars=yes");
        }
    
    
        function removeRecordAgensi(idOperasiAgensi)
        {
            if(confirm('Adakah anda pasti untuk hapus?')) {
                var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_agensi?deleteAgensi&idOperasiAgensi='
                    +idOperasiAgensi;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');}
        }

</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.penguatkuasaan.MaklumatAgensiActionBean" name="form1" id="locate">
    <s:messages/>

    <div class="subtitle">
        <c:if test="${edit}">
            <fieldset class="aras1">
                <legend>
                    Surat Mohon Bantuan Agensi
                </legend>


                <div class="content">
                    <p>
                        <label><font color="red">*</font> Tarikh Berkumpul :</label>
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
                        <label>Tempat Berkumpul :</label>
                        <s:textarea name="operasiPenguatkuasaan.tempatBerkumpul" rows="5" cols="50" id="tempat"/>&nbsp;
                    </p>
                    <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>


                </div>
                <fieldset class="aras2">
                    <legend>
                        Maklumat Agensi Yang Terlibat
                    </legend>
                    <table>
                        <tr><td width="50%"><label> Nama Agensi : </label></td><td>
                                <s:select name="agensi.kod" id="agen">
                                    <s:option value="">Sila Pilih</s:option>
                                    <c:choose>
                                        <c:when test = "${actionBean.kodNegeri eq '05'}" >
                                            <s:options-collection collection="${listUtil.senaraiAgensiSemua}" label="nama" value="kod" sort="nama" />
                                        </c:when>
                                        <c:otherwise>
                                            <s:options-collection collection="${listUtil.senaraiAgensi}" label="nama" value="kod" sort="nama" />   
                                        </c:otherwise>
                                    </c:choose>

                                </s:select>
                                <%--                 <s:select name="agensi.kod" id="agen">
                                                     <s:option value="">Sila Pilih</s:option>
                                                     <s:option value="0209">TENAGA NASIONAL BERHAD (TNB)</s:option>
                                                     <s:option value="0213">SURUHANJAYA TENAGA MALAYSIA</s:option>
                                                     <s:option value="0302">POLIS DIRAJA MALAYSIA (PDRM)</s:option>
                                                     <s:option value="0316">SURUHANJAYA PASUKAN POLIS</s:option>
                                                     <s:option value="0909">JABATAN KEBAJIKAN MASYARAKAT</s:option>
                                                     <s:option value="0912">YAYASAN KEBAJIKAN MASYARAKAT</s:option>
                                                     <s:option value="1818">JABATAN PENGAIRAN DAN SALIRAN MALAYSIA</s:option>
                                                     <s:option value="1829">BAHAGIAN PENGAIRAN DAN SALIRAN</s:option>
                                                     <s:option value="3204">JABATAN IMIGRESEN MALAYSIA</s:option>
                                                     <s:option value="3209">BAHAGIAN IMIGRESEN DAN PENDAFTARAN NEGARA</s:option>
                                                 </s:select>--%>
                                <s:button class="btn" onclick="if(validateForm())doSubmit(this.form, this.name, 'page_div');" name="simpan" value="Tambah"/>
                                <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
                            </td></tr>
                    </table>

                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.senaraiOperasiAgensi}" pagesize="10" cellpadding="0" cellspacing="0" id="line" >
                            <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                            <display:column title="Agensi Yang Terlibat" property="agensi.nama"></display:column>
                            <display:column title="Alamat">
                                <s:textarea name="alamatAgensi${line_rowNum}" id="alamatAgensi" value="${line.alamatAgensi}" cols="30" rows="3" class="normal_text"/>
                            </display:column>
                            <display:column title="Nama" >
                                <s:text name="nama${line_rowNum}" id="nama" size="25" class="normal_text">${line.namaHubungan}</s:text>
                            </display:column>
                            <display:column title="No.Telefon" >
                                <s:text name="noTel${line_rowNum}" id="noTel" size="15" maxlength="12" onkeyup="validateNumber(this,this.value);" onchange="validateTelLength(this.value);">${line.noTelefonHubungan}</s:text>
                            </display:column>
                            <display:column title="Email" >
                                <s:text name="email${line_rowNum}" class="normal_text" id="email" size="15" onblur="return ValidateEmail()" >${line.emailHubungan} </s:text>
                            </display:column>
                            <c:if test="${actionBean.kodNegeri eq '05'}">
                                <display:column title="Tajuk Surat">
                                    <s:textarea name="tajukSurat${line_rowNum}" id="tajukSurat" value="${line.catatan}" cols="30" rows="3" class="normal_text"/>
                                </display:column>
                            </c:if>

                            <display:column title="Hapus">
                                <div align="center">
                                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeSingle('${actionBean.senaraiOperasiAgensi[line_rowNum-1].idOperasiAgensi}');" />
                                </div>
                            </display:column>
                        </display:table>

                    </div>                    
                </fieldset>
                <p align="right">
                    <s:button  name="reset" value="Isi Semula" class="btn" onclick="return test();"/>
                    <s:button class="btn" onclick="if(validateForm1())doSubmit(this.form, this.name, 'page_div');" name="simpanSurat" value="Simpan"/>

                </p>
            </fieldset>

        </c:if>

        <c:if test="${kpsn_mesyuarat}">
            <fieldset class="aras1">
                <legend>
                    Keputusan Mesyuarat
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
                        <s:textarea name="operasiPenguatkuasaan.tempatBerkumpul" rows="5" cols="50" id="tempat"/>&nbsp;
                    </p>

                    <p>
                        <label>Keputusan Mesyuarat :</label>
                        <s:textarea name="ulasanMesyuarat" rows="5" cols="50" id="tempat"/>&nbsp;
                    </p>
                    <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>


                </div>
                <fieldset class="aras2">
                    <legend>
                        Maklumat Agensi Yang Terlibat
                    </legend>
                    <table>
                        <tr><td width="50%"><label> Nama Agensi : </label></td><td>
                                <s:select name="agensi.kod" id="agen">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${listUtil.senaraiAgensi}" label="nama" value="kod" sort="nama" />
                                </s:select>

                                <s:button class="btn" onclick="if(validateAddAgensi())doSubmit(this.form, this.name, 'page_div');" name="simpanAgensi" value="Tambah"/>
                                <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
                            </td></tr>
                    </table>

                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.senaraiOperasiAgensi}" pagesize="10" cellpadding="0" cellspacing="0" id="line" >
                            <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                            <display:column title="Agensi Yang Terlibat" property="agensi.nama"></display:column>
                            <display:column title="Alamat">
                                <s:textarea name="alamatAgensi${line_rowNum}" id="alamatAgensi" value="${line.alamatAgensi}" cols="50" rows="3"/>
                            </display:column>
                            <display:column title="Nama" >
                                <s:text name="nama${line_rowNum}" id="nama" size="25">${line.namaHubungan}</s:text>
                            </display:column>
                            <display:column title="No.Telefon" >
                                <s:text name="noTel${line_rowNum}" id="noTel" size="15" maxlength="12" onkeyup="validateNumber(this,this.value);" onchange="validateTelLength(this.value);">${line.noTelefonHubungan}</s:text>
                            </display:column>
                            <display:column title="Email" >
                                <s:text name="email${line_rowNum}" class="normal_text" id="email" size="15" onblur="return ValidateEmail()">${line.emailHubungan}</s:text>
                            </display:column>
                            <display:column title="Hapus">
                                <div align="center">
                                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeSingle('${actionBean.senaraiOperasiAgensi[line_rowNum-1].idOperasiAgensi}');" />
                                </div>
                            </display:column>
                        </display:table>

                    </div>
                </fieldset>
                <p align="right">
                    <s:button  name="reset" value="Isi Semula" class="btn" onclick="return test();"/>
                    <s:button class="btn" onclick="if(validateFormMesyuarat())doSubmit(this.form, this.name, 'page_div');" name="simpanKeputusanMesyuarat" value="Simpan"/>

                </p>
            </fieldset>

        </c:if>

        <c:if test="${view}">
            <fieldset class="aras1">
                <legend>
                    Surat Mohon Bantuan Agensi
                </legend>
                <div class="content">
                    <p>
                        <label>Tarikh Berkumpul :</label>
                        <fmt:formatDate value="${actionBean.operasiPenguatkuasaan.tarikhBerkumpul}" pattern="dd/MM/yyyy" />&nbsp;
                    </p>
                    <p>
                        <label>Masa :</label>
                        <fmt:formatDate value="${actionBean.operasiPenguatkuasaan.tarikhBerkumpul}" pattern="hh:mm:ss aaa" />&nbsp;
                    </p>
                    <p>
                        <label>Tempat Berkumpul :</label>
                        ${actionBean.operasiPenguatkuasaan.tempatBerkumpul}&nbsp;
                    </p>
                    <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
                </div>
                <div class="subtitle">
                    <fieldset class="aras2">
                        <legend>
                            Maklumat Agensi Yang Terlibat
                        </legend>
                        <div class="content" align="center">
                            <display:table class="tablecloth" name="${actionBean.senaraiOperasiAgensi}" pagesize="10" cellpadding="0" cellspacing="0" id="line" >
                                <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                                <display:column title="Agensi Yang Terlibat" property="agensi.nama"></display:column>
                                <display:column title="Alamat">${line.agensi.alamat1}
                                    <c:if test="${line.agensi.alamat2 ne null}"> , </c:if>
                                    ${line.agensi.alamat2}
                                    <c:if test="${line.agensi.alamat3 ne null}"> , </c:if>
                                    ${line.agensi.alamat3}
                                    <c:if test="${line.agensi.alamat4 ne null}"> , </c:if>
                                    ${line.agensi.alamat4}
                                    <c:if test="${line.agensi.poskod ne null}"> , </c:if>
                                    ${line.agensi.poskod}
                                    <c:if test="${line.agensi.kodNegeri.nama ne null}"> , </c:if>
                                    ${line.agensi.kodNegeri.nama}</display:column>
                            </display:table>
                        </div>
                    </fieldset>
                </div>



            </fieldset>
        </c:if>

        <c:if test="${view_kpsn_mesyuarat}">
            <fieldset class="aras1">
                <legend>
                    Keputusan Mesyuarat
                </legend>
                <div class="content">
                    <p>
                        <label>Tarikh Mesyuarat :</label>
                        <fmt:formatDate value="${actionBean.operasiPenguatkuasaan.tarikhBerkumpul}" pattern="dd/MM/yyyy" />&nbsp;
                    </p>
                    <p>
                        <label>Masa :</label>
                        <fmt:formatDate value="${actionBean.operasiPenguatkuasaan.tarikhBerkumpul}" pattern="hh:mm:ss aaa" />&nbsp;
                    </p>
                    <p>
                        <label>Tempat Mesyuarat :</label>
                        ${actionBean.operasiPenguatkuasaan.tempatBerkumpul}&nbsp;
                    </p>
                    <p>
                        <label>Keputusan Mesyuarat :</label>
                        ${actionBean.ulasanMesyuarat}&nbsp;
                    </p>
                    <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
                </div>
                <div class="subtitle">
                    <fieldset class="aras2">
                        <legend>
                            Maklumat Agensi Yang Terlibat
                        </legend>
                        <div class="content" align="center">
                            <div class="content" align="center">
                                <display:table class="tablecloth" name="${actionBean.senaraiOperasiAgensi}" pagesize="10" cellpadding="0" cellspacing="0" id="line" >
                                    <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                                    <display:column title="Agensi Yang Terlibat" property="agensi.nama"/>
                                    <display:column title="Alamat" property="alamatAgensi"/>
                                    <display:column title="Nama" property="namaHubungan"/>
                                    <display:column title="No.Tel" property="noTelefonHubungan"/>
                                    <display:column title="Email" property="emailHubungan"/>
                                </display:table>
                            </div>
                        </div>
                    </fieldset>
                </div>
            </fieldset>
        </c:if>

        <c:if test="${addKpsnMesyuarat}">
            <fieldset class="aras1">
                <legend>
                    Keputusan Mesyuarat
                </legend>
                <div class="instr-fieldset"><br>
                    Klik butang tambah untuk masukkan maklumat keputusan mesyuarat
                </div>
                <br>
                <div class="content" align="center">
                    <display:table name="${actionBean.listOp}" id="line" class="tablecloth" cellpadding="0" cellspacing="0" style="width:90%;">
                        <display:column title="Bil" sortable="true" style="width:3px;">${line_rowNum}</display:column>
                        <display:column title="Maklumat Laporan Operasi" style="width:250px;">
                            <table width="10%" cellpadding="1">
                                <tr>
                                    <td width="20%"><font class="infoLP">Id Operasi :</font></td>
                                    <td width="20%"><u><a class="popup" onclick="viewRecordOP(${line.idOperasi})">${line.idOperasi}</a></u></td>
                                </tr>
                                <tr>
                                    <td width="20%"><font class="infoLP">Tarikh laporan :</font></td>
                                    <td width="20%">
                                        <fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhOperasi}"/></td>
                                </tr>
                                <tr>
                                    <td width="20%"><font class="infoLP">Masa laporan :</font></td>
                                    <td width="20%">
                                        <fmt:formatDate pattern="hh:mm" value="${line.tarikhOperasi}"/>
                                        <fmt:formatDate pattern="aaa" value="${line.tarikhOperasi}" var="time"/>
                                        <c:if test="${time eq 'AM'}">PAGI</c:if>
                                        <c:if test="${time eq 'PM'}">PETANG</c:if>
                                        </td>
                                    </tr>

                                </table>
                        </display:column>
                        <display:column title="Maklumat Agensi" style="width:300px;">
                            <c:set value="1" var="count"/>
                            <c:if test="${fn:length(line.senaraiAgensi) ne 0}">
                                <table width="40%" cellpadding="1" style="width:600px;">
                                    <tr>
                                        <th  width="1%" align="center"><b>Bil</b></th>
                                        <th  width="5%" align="center"><b>Agensi Yang Terlibat</b></th>
                                        <th  width="5%" align="center"><b>Alamat</b></th>
                                        <th  width="5%" align="center"><b>Hapus</b></th>
                                    </tr>
                                    <c:forEach items="${line.senaraiAgensi}" var="a">
                                        <tr>
                                            <td width="5%">${count}</td>
                                            <td width="50%"><u><a class="popup" onclick="viewRecordAgensi(${a.idOperasiAgensi})">${a.agensi.nama}</a></u></td>
                                            <td width="50%">${a.alamatAgensi}
                                            </td>
                                            <td width="5%">
                                                <div align="center">
                                                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" title="Hapus untuk Agensi ${a.agensi.kod}" onclick="removeRecordAgensi('${a.idOperasiAgensi}');"/>
                                                </div>
                                            </td>


                                        </tr>

                                        <c:set value="${count+1}" var="count"/>
                                    </c:forEach>
                                </table>
                            </c:if>
                        </display:column>
                        <display:column title="Tambah/Kemaskini Agensi" style="width:50px;">
                            <div align="center">
                                <img alt='Klik Untuk Tambah/Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" title="Tambah/Kemasikini Agensi" onclick="addRecord('${line.idOperasi}');"/>
                            </div>
                        </display:column>



                    </display:table>
                    <br>

                </div>
            </fieldset>

        </c:if>
    </div>
</s:form>