<%--
    Document   : maklumat_pemohon
    Created on : nov 27, 2013, 10:28:20 AM
    Author     : murali   
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    function resetForm(){
        $('#nama').val('');
        $('#alamat1').val('');
        $('#alamat2').val('');
        $('#alamat3').val('');
        $('#alamat4').val('');
        $('#poskod').val('');
        $('#negeri').val('');
        $('#jenisPengenalan').val('');
        $('#nomborPengenalan').val('');
        document.getElementById("checkAlamat").checked =false;
        $('#suratAlamat1').val('');
        $('#suratAlamat2').val('');
        $('#suratAlamat3').val('');
        $('#suratAlamat4').val('');
        $('#suratPoskod').val('');
        $('#suratNegeri').val('');
        
    }
    function validation(){

        if($("#nama").val() == ""){
            alert('Sila masukan " nama " terlebih dahulu.');
            $("#nama").focus();
            return false;
        }else{
            return true;
        }
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
    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if($('#jenisPengenalan').val()== 'B'){
            if (isNaN(content)) {
                elmnt.value = removeNonNumeric(content);
                return;
            }
        }
    }

    function validateNumber2(elmnt,content) {
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
    function noPengenalanhide(){
        if($('#jenisPengenalan').val() == 'B'){
            $('#nomborPengenalan').val("");
        }
    }   
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.strata.PechaPetakPemohonActionBean" name="form1">    
    <s:messages/>
    <s:errors/>
    <s:hidden name="idPihak"/>
    <c:if test="${!readOnly}">
        <div class="subtitle">
            <fieldset class="aras1">              
                <div id="pemohon">
                    <legend>Maklumat Pemohon</legend>               
                    <p>
                        <label>Nama :</label><s:text name="nama" id="nama" size="40"/>
                    </p>                
                    <p>
                        <label>Jenis Pengenalan :</label>
                        <s:select name="jenisPengenalan" id="jenisPengenalan" style="width:260px;">
                            <s:option value="" >Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                        </s:select>
                    </p>
                    <p id="noPeng1">
                        <label>No.Pengenalan :</label>
                        <s:text name="nomborPengenalan" id="nomborPengenalan" size="40" onkeyup="validateNumber(this,this.value);"/>
                    </p> 
                    <p><label>Alamat :</label><s:text name="alamat1" id="alamat1" size="40"/></p>
                    <p><label> &nbsp;</label><s:text name="alamat2" id="alamat2" size="40"/></p>
                    <p><label> &nbsp;</label><s:text name="alamat3" id="alamat3" size="40"/></p>
                    <p><label>Bandar :</label><s:text name="alamat4" id="alamat4" size="40"/></p>
                    <p><label>Poskod :</label><s:text name="poskod" id="poskod" size="40" maxlength="5" onkeyup="validateNumber2(this,this.value);"/></p>
                    <p>
                        <label for="Negeri">Negeri :</label>
                        <s:select name="negeri" id="negeri" style="width:260px;">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                        </s:select>
                    </p><br>               

                    <p>
                        <label>&nbsp;</label>
                        <c:if test="${actionBean.checkAlamat eq 'Yes'}">
                            <input type="checkbox" id="checkAlamat" name="checkAlamat" checked="true" onclick="copyAdd();"/>
                        </c:if>
                        <c:if test="${actionBean.checkAlamat eq 'No'}">
                            <input type="checkbox" id="checkAlamat" name="checkAlamat" onclick="copyAdd();"/>
                        </c:if>
                        <c:if test="${actionBean.checkAlamat eq null}">
                            <input type="checkbox" id="checkAlamat" name="checkAlamat" onclick="copyAdd();"/>
                        </c:if>
                        <font color="red">Alamat surat-menyurat sama dengan alamat berdaftar.</font>
                    </p>
                    <p><label for="alamat">Alamat Surat-Menyurat:</label><s:text name="suratAlamat1" id="suratAlamat1" size="40"/></p>
                    <p><label> &nbsp;</label><s:text name="suratAlamat2" id="suratAlamat2" size="40"/></p>
                    <p><label> &nbsp;</label><s:text name="suratAlamat3" id="suratAlamat3" size="40"/></p>
                    <p><label> Bandar :</label><s:text name="suratAlamat4" id="suratAlamat4" size="40"/></p>
                    <p><label>Poskod :</label><s:text name="suratPoskod" id="suratPoskod" size="40" maxlength="5" onkeyup="validateNumber2(this,this.value);"/></p>
                    <p>
                        <label for="Negeri">Negeri :</label>
                        <s:select name="suratNegeri" id="suratNegeri" style="width:260px;">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                        </s:select>
                    </p>         

                    <p>
                        <br>
                        <label>&nbsp;</label>
                        <s:hidden name="pemohon1.idPemohon" id="pemohon1.idPemohon" value="${actionBean.pemohon1.idPemohon}"/>
                        <s:button class="btn" value="Kemaskini" name="simpanPemohon" id="new" onclick="if(validation()==true){doSubmit(this.form,this.name,'page_div')};"/>&nbsp;
                        <s:button class="btn" value="Isi Semula" name="new" id="new" onclick="resetForm()"/>&nbsp;
                    </p>     
                </div>
            </fieldset>
        </div>    
</c:if>
</s:form>