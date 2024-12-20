<%--
    Document   : maklumat_pemohon
    Created on : Jul 4, 2011, 10:28:20 AM
    Author     : zadhirul.farihim
    Modified by: Murali 16, April, 2012
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">

    function cariPemohon(){
        window.open("${pageContext.request.contextPath}/strata/kemasukan_pemohon?cariPemohonPopup", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,scrollbars=1,width=910,height=600");

    }

    function resetForm(){
        var url = "${pageContext.request.contextPath}/strata/kemasukan_pemohon?resetForm";
        $.post(url,
        function(data){
            $('#page_div').html(data);
        },'html');

    <%--$('#nama').val('');
    $('#jenisPengenalan').val('');
    $('#nomborPengenalan').val('');
    $('#alamat1').val('');
    $('#alamat2').val('');
    $('#alamat3').val('');
    $('#alamat4').val('');
    $('#poskod').val('');
    $('#negeri').val('');
    $('#suratAlamat1').val('');
    $('#suratAlamat2').val('');
    $('#suratAlamat3').val('');
    $('#suratAlamat4').val('');
    $('#suratPoskod').val('');
    $('#suratNegeri').val('');
    var checkbox = document.getElementById('checkAlamat');
    checkbox.checked = false;--%>
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

                 $(document).ready(function(){

                 });
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.strata.KemasukanPemohonActionBean" name="form1">
    <s:messages/>
    <s:errors/>

    <s:hidden name="idPihak"/>
    <c:if test="${!readOnly}">

        <div class="subtitle">
            <fieldset class="aras1">

                <legend>Maklumat Pemohon</legend>
                <div align="center">
                    <table align="center">
                    <tr><td> &nbsp;&nbsp;
                            <font color="red">*</font> <font color="#003194"><b> Nama &nbsp;:</b></font>
                            &nbsp;<s:text name="nama" id="nama" size="34"/>
                            <s:button class="btn" value="Cari" name="new" id="new" onclick="cariPemohon();"/>
                        </td></tr></table> </div>

                <c:if test="${!actionBean.penyerahAdalahPemohon}">
                    <p>
                        <label>Jenis Pengenalan :</label>
                        <s:select name="jenisPengenalan" id="jenisPengenalan" style="width:255px;">
                            <s:option value="" >Sila Pilih</s:option>
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
                    <label> &nbsp;</label>
                    <s:text name="alamat2" id="alamat2" size="40"/>
                </p>

                <p>
                    <label> &nbsp;</label>
                    <s:text name="alamat3" id="alamat3" size="40"/>
                </p>

                <p>
                    <label> Bandar :</label>
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
                <br>
                <%--<c:if test="${!actionBean.penyerahAdalahPemohon}">--%>
                    <c:if test="${actionBean.checkAlamat eq 'Yes'}">
                        <p>
                            <label>&nbsp;</label>
                            <input type="checkbox" id="checkAlamat" checked="checked" name="checkAlamat" value="Yes" onclick="copyAdd();"/>
                            <font color="red">Alamat surat-menyurat sama dengan alamat berdaftar.</font>
                        </p>
                    </c:if>
                    <c:if test="${actionBean.checkAlamat eq 'No'}">
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


                    <p>
                        <label for="alamat">Alamat Surat-Menyurat:</label>
                        <s:text name="suratAlamat1" id="suratAlamat1" size="40"/>
                    </p>
                    <p>
                        <label> &nbsp;</label>
                        <s:text name="suratAlamat2" id="suratAlamat2" size="40"/>
                    </p>
                    <p>
                        <label> &nbsp;</label>
                        <s:text name="suratAlamat3" id="suratAlamat3" size="40"/>
                    </p>
                    <p>
                        <label> Bandar :</label>
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
                <%--</c:if>--%>
                <p></p>

                <p>
                    <br>
                    <label>&nbsp;</label>
                    <c:if test="${actionBean.pihak eq null}">
                        <s:button class="btn" value="Simpan" name="simpanPemohon" id="new" onclick="if(validation()==true){doSubmit(this.form,this.name,'page_div')};"/>&nbsp;
                    </c:if>
                    <c:if test="${actionBean.pihak ne null}">
                        <s:button class="btn" value="Kemaskini" name="simpanPemohon" id="new" onclick="if(validation()==true){doSubmit(this.form,this.name,'page_div')};"/>&nbsp;
                    </c:if>

                    <s:button class="btn" value="Isi Semula" name="new" id="new" onclick="resetForm()"/>&nbsp;

                </p>
            </fieldset>

        </div>
    </c:if>
    <c:if test="${readOnly}">
        <div class="subtitle">
            <fieldset class="aras1">

                <legend>Maklumat Pemohon</legend>

                <p>
                    <label>Nama :</label>
                    ${actionBean.nama} &nbsp;
                </p>
                <%--<c:if test="${!actionBean.penyerahAdalahPemohon}">--%>
                    <p>
                        <label>Jenis Pengenalan :</label>
                        ${actionBean.jenisPengenalan} &nbsp;
                    </p>
                    <p>
                        <label>Nombor Pengenalan :</label>
                        ${actionBean.nomborPengenalan} &nbsp;
                    </p>
                <%--</c:if>--%>
                <p>
                    <label>Alamat :</label>
                    ${actionBean.alamat1} &nbsp;
                </p>
                <c:if test="${actionBean.alamat2 ne null}">
                    <p>
                        <label> &nbsp;</label>
                        ${actionBean.alamat2} &nbsp;
                    </p>
                </c:if>
                <c:if test="${actionBean.alamat3 ne null}">
                    <p>
                        <label> &nbsp;</label>
                        ${actionBean.alamat3} &nbsp;
                    </p>
                </c:if>
                    <p>
                        <label> Bandar :</label>
                        ${actionBean.alamat4} &nbsp;
                    </p>
                <p>
                    <label>Poskod :</label>
                    <c:if test="${actionBean.poskod ne null}">
                        ${actionBean.poskod}
                    </c:if>&nbsp;
                </p> 
                <p>
                    <label for="Negeri">Negeri :</label>
                    <font style="text-transform: uppercase">${actionBean.pihak.negeri.nama} &nbsp;</font>
                </p>
                
                <p>
                    <label>Alamat Surat-Menyurat :</label>
                    ${actionBean.suratAlamat1} &nbsp;
                </p>
                <c:if test="${actionBean.suratAlamat2 ne null}">
                    <p>
                        <label> &nbsp;</label>
                        ${actionBean.suratAlamat2} &nbsp;
                    </p>
                </c:if>
                <c:if test="${actionBean.suratAlamat3 ne null}">
                    <p>
                        <label> &nbsp;</label>
                        ${actionBean.suratAlamat3} &nbsp;
                    </p>
                </c:if>
                    <p>
                        <label> Bandar :</label>
                        ${actionBean.suratAlamat4} &nbsp;
                    </p>
                <p>
                    <label>Poskod :</label>
                    <c:if test="${actionBean.suratPoskod ne null}">
                        ${actionBean.suratPoskod}
                    </c:if>&nbsp;
                </p> 
                <p>
                    <label for="Negeri">Negeri :</label>
                    <font style="text-transform: uppercase">${actionBean.pihakAlamatTamb.alamatKetigaNegeri.nama} &nbsp;</font>
                </p>
                <br>
                <p>&nbsp;</p>
            </fieldset>
        </div>
    </c:if>
</s:form>