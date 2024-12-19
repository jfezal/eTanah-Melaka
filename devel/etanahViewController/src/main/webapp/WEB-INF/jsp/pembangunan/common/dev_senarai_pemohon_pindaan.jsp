<%-- 
    Document   : dev_senarai_pemohon
    Created on : Jun 28, 2010, 11:28:51 AM
    Author     : nursyazwani
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $('.empty').remove();

        var len2 = $(".alamat").length;

        for (var i = 0; i < len2; i++) {
            var d = $('.x' + i).val();

            $('.nama' + i).bind('click', d, function() {
                window.open("${pageContext.request.contextPath}/pembangunan/pihak_berkepentingan?showEditPemohon&idPihak=" + d, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=750");
            });
        }
        $('#page_effect').fadeIn(5000);
    });

    
    function copyAdd(){
        if($('input[name=add1]').is(':checked')){
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


</script>
<div class="subtitle displaytag" id="page_effect" style="display:none;">
    <s:messages/>
    <s:errors/>
    
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<div class="subtitle" id="caw">
    <s:form beanclass="etanah.view.stripes.pembangunan.PihakBerkepentinganPindaanActionBean">
        <s:hidden name="idPihak"/>
        <s:hidden name="idPemohon"/>
        
            <fieldset class="aras1">
                <legend>
                        Kemasukan Maklumat Pemohon Pemegang Surat Kuasa Wakil

                </legend>
                <font color="red">Sila kosongkan ruang surat kuasa wakil diruang Maklumat Permohonan untuk Pemohon adalah Pemilik Tanah.</font>

                <br/>
                <br>
                   <p>
                            <label for="nama"><font color="red">*</font>Nama :</label>
                            <s:text name="nama" id="nama_pemohon" size="40" class="normal_text"/>
                        </p>

                        

                            <p>
                                <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                                <s:select name="jenisKp">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                                </s:select>
                            </p>


                        <p>
                            <label for="No Pengenalan"><font color="red">*</font>Nombor Pengenalan :</label>
                            <s:text name="noKp" id="kp" size="40" onkeyup="dodacheck(this.value);"/>
                        </p>
                        
                        <p>
                            <label for="alamat"><font color="red">*</font>Alamat Berdaftar :</label>
                            <s:text name="alamatDaftar1" size="40" id="alamat1" maxlength="40" class="normal_text"/>
                        </p>

                        <p>
                            <label> &nbsp;</label>
                            <s:text name="alamatDaftar2" size="40" id="alamat2" maxlength="40" class="normal_text"/>
                        </p>

                        <p>
                            <label> &nbsp;</label>
                            <s:text name="alamatDaftar3" size="40" id="alamat3" maxlength="40" class="normal_text"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="alamatDaftar4" size="40" id="alamat4" maxlength="40" class="normal_text"/>
                        <p>
                            <label>Poskod :</label>
                            <s:text name="poskodDaftar" size="40" maxlength="5" id="poskod" onkeyup="validateNumber(this,this.value);"/>
                        </p>

                        <p>
                            <label for="Negeri">Negeri :</label>
                            <s:select name="negeriDaftar" id="negeri">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                            </s:select>
                        </p>

                        
                         
                        <p>
                            <label>&nbsp;</label>
                            <input type="checkbox" id="add1" name="add1" value="" onclick="copyAdd();"/>
                            <font color="red">Alamat surat-menyurat sama dengan alamat berdaftar.</font>
                        </p>
                        <p>
                            <label for="alamat">Alamat Surat-Menyurat :</label>
                            <s:text name="suratAlamat1" id="suratAlamat1" size="40" maxlength="40" class="normal_text"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="suratAlamat2" id="suratAlamat2" size="40" maxlength="40" class="normal_text"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="suratAlamat3" id="suratAlamat3" size="40" maxlength="40" class="normal_text"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="suratAlamat4" id="suratAlamat4" size="40" maxlength="40" class="normal_text"/>
                        </p>
                        <p>
                            <label>Poskod :</label>
                            <s:text name="suratPoskod" id="suratPoskod" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                        </p>

                        <p>
                            <label for="Negeri">Negeri :</label>
                            <s:select name="suratNegeri" id="suratNegeri">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                            </s:select>
                        </p>
                                    

                 <p >
                     <label> &nbsp;</label>
                        <s:button class="btn" onclick="doSubmit(this.form, this.name, 'page_div');" name="simpan" value="Simpan"/>
                    </p>

               
                <br>
            </fieldset>


    </s:form>
</div>
</div>