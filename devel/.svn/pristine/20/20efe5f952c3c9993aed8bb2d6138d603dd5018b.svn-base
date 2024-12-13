<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<!DOCTYPE html>

<script type="text/javascript">
    function validation(){
       
        if($("#nama").val() == ""){
            alert('Sila masukan " nama " terlebih dahulu.');
            $("#nama").focus();
            return false;
        }else{
            return true;
        }
    }
    
    function resetForm(){
        alert("masukkk");
        form.reset();
       
        
    }
            
    function doSubmit(e,f,g) {
        var q = $(f).formSerialize();
        f.action = f.action + '?' + e + '&idPermohonan=' + g + '&popup&' + q;
        f.submit();
    }
            
    function cariPemohon(){
        window.open("${pageContext.request.contextPath}/strata/kemaskini_maklumat_pemohon?cariPemohonPopup", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,scrollbars=1,width=910,height=600");
    }
            
    function resetForm(){
        var url = "${pageContext.request.contextPath}/strata/kemaskini_maklumat_pemohon?resetForm";
        $.post(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }
            
    function copyAdd(){
        if($('input[name=checkAlamat]').is(':checked')){
            $('#suratAlamat1').val($('#alamat1').val());
            $('#suratAlamat2').val($('#alamat2').val());
            $('#suratAlamat3').val($('#alamat3').val());
            $('#suratAlamat4').val($('#alamat4').val());
            $('#suratPoskod').val($('#poskod').val());
            $('#suratNegeri').val($('#negeri').val());
        }else{
            $('#suratAlamat1').val('');
            $('#suratAlamat2').val('');
            $('#suratAlamat3').val('');
            $('#suratAlamat4').val('');
            $('#suratPoskod').val('');
            $('#suratNegeri').val('');
        }
    }
            
    function validateNumber(elmnt,content){
        //if it is character, then remove it..
        if($('#jenisPengenalan').val()== 'B'){
            if (isNaN(content)) {
                elmnt.value = removeNonNumeric(content);
                return;
            }
        }
    }
            
    function validateNumber2(elmnt,content){
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }
            
    function removeNonNumeric( strString ){
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
            
    function noPengenalanhide(){
        if($('#jenisPengenalan').val() == 'B'){
            $('#nomborPengenalan').val("");
        }
    }
            
    $(document).ready(function(){

    });
</script>


<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>

<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none" alt=""/>

<s:form beanclass="etanah.view.strata.utiliti.KemaskiniDataPermohonanStrataActionBean">



    <s:hidden name="idPihak"/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Pemohon</legend>
            <p>
                <label><font color="red">*</font> Nama : </label>
                <s:text name="nama" id="nama" size="50"/>
                <%--<s:button class="btn" value="Cari" name="new" id="new" onclick="cariPemohon();"/>--%>
            </p>
            <c:if test="${!actionBean.penyerahAdalahPemohon}">
                <p>
                    <label>Jenis Pengenalan :</label>
                    <s:select name="jenisPengenalan" id="jenisPengenalan" style="width:255px;">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                    </s:select>
                </p>
                <p id="noPeng1">
                    <label>No.Pengenalan :</label>
                    <s:text name="nomborPengenalan" id="nomborPengenalan" size="40" onkeyup="validateNumber(this,this.value);"/>
                </p>                    
            </c:if>
            <p>
                <label>Alamat :</label>
                <s:text name="alamat1" id="alamat1" size="40"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:text name="alamat2" id="alamat2" size="40"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:text name="alamat3" id="alamat3" size="40"/>
            </p>
            <p>
                <label>Bandar :</label>
                <s:text name="alamat4" id="alamat4" size="40"/>
            </p>
            <p>
                <label>Poskod :</label>
                <s:text name="poskod" id="poskod" size="40" maxlength="5" onkeyup="validateNumber2(this,this.value);"/>
            </p>
            <p>
                <label for="Negeri">Negeri :</label>
                <s:select name="negeri" id="negeri" style="width:255px;">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                </s:select>
            </p>
            <br/>

            <c:if test="${actionBean.kodNegeri eq '05'}">

                <c:if test="${actionBean.checkAlamat eq 'Yes' && actionBean.suratMenyurat eq 'Yes'}">
                    <p>
                        <label>&nbsp;</label>
                        <input type="checkbox" id="checkAlamat" checked="checked" name="checkAlamat" value="Yes" onclick="copyAdd();"/>
                        <font color="red">Alamat surat-menyurat sama dengan alamat berdaftar.</font>
                    </p>
                </c:if>
                <c:if test="${actionBean.checkAlamat eq 'Yes' && actionBean.suratMenyurat eq 'No'}">
                    <p>
                        <label>&nbsp;</label>
                        <input type="checkbox" id="checkAlamat" name="checkAlamat" value="No" onclick="copyAdd();"/>
                        <font color="red">Alamat surat-menyurat sama dengan alamat berdaftar.</font>
                    </p>
                </c:if>
                <c:if test="${actionBean.checkAlamat eq 'No' && actionBean.suratMenyurat eq 'Yes'}">
                    <p>
                        <label>&nbsp;</label>
                        <input type="checkbox" id="checkAlamat" name="checkAlamat" value="No" onclick="copyAdd();"/>
                        <font color="red">Alamat surat-menyurat sama dengan alamat berdaftar.</font>
                    </p>
                </c:if>
                <c:if test="${actionBean.checkAlamat eq 'No' && actionBean.suratMenyurat eq 'No'}">
                    <p>
                        <label>&nbsp;</label>
                        <input type="checkbox" id="checkAlamat" name="checkAlamat" value="No" onclick="copyAdd();"/>
                        <font color="red">Alamat surat-menyurat sama dengan alamat berdaftar.</font>
                    </p>
                </c:if>

                <c:if test="${emptypemohon}">
                    <p>
                        <label>&nbsp;</label>
                        <input type="checkbox" id="checkAlamat" name="checkAlamat" value="No" onclick="copyAdd();"/>
                        <font color="red">Alamat surat-menyurat sama dengan alamat berdaftar.</font>
                    </p>
                </c:if>
            </c:if>

            <c:if test="${actionBean.kodNegeri eq '04'}">
                <p>
                    <label>&nbsp;</label>
                    <input type="checkbox" id="checkAlamat" name="checkAlamat" value="No" onclick="copyAdd();"/>
                    <font color="red">Alamat surat-menyurat sama dengan alamat berdaftar.</font>
                </p>
            </c:if>

            <p>
                <label for="alamat">Alamat Surat-Menyurat :</label>
                <s:text name="suratAlamat1" id="suratAlamat1" size="40"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:text name="suratAlamat2" id="suratAlamat2" size="40"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:text name="suratAlamat3" id="suratAlamat3" size="40"/>
            </p>
            <p>
                <label>Bandar :</label>
                <s:text name="suratAlamat4" id="suratAlamat4" size="40"/>
            </p>
            <p>
                <label>Poskod :</label>
                <s:text name="suratPoskod" id="suratPoskod" size="40" maxlength="5" onkeyup="validateNumber2(this,this.value);"/>
            </p>
            <p>
                <label for="Negeri">Negeri :</label>
                <s:select name="suratNegeri" id="suratNegeri" style="width:255px;">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                </s:select>
            </p>
            <p></p>

            <p>
                <label>&nbsp;</label>
                <c:if test="${actionBean.pihak eq null}">
                    <s:button class="btn" value="Simpan" name="simpanPemohon" onclick="if(validation()==true){doSubmit('simpanPemohon',this.form,'${actionBean.permohonan.idPermohonan}')};"/>&nbsp;
                </c:if>
                <c:if test="${actionBean.pihak ne null}">
                    <s:button class="btn" value="Kemaskini" name="simpanPemohon" onclick="if(validation()==true){doSubmit('simpanPemohon',this.form,'${actionBean.permohonan.idPermohonan}')};"/>&nbsp;
                </c:if>
                <s:button class="btn" value="Isi Semula" name="resetPemohon" onclick="if (confirm('Pasti untuk memadam rekod?')){doSubmit('resetPemohon',this.form,'${actionBean.permohonan.idPermohonan}')};"/>&nbsp;
            </p>

        </fieldset>
    </div>

</s:form>
