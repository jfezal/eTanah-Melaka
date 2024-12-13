<%-- 
    Document   : edit_pihak_mati
    Created on : Apr 1, 2010, 10:24:13 PM
    Author     : muhammad.amin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.2.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ageCalculator.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>

<script type="text/javascript">

    $(document).ready( function(){
        $('.alphanumeric').alphanumeric();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    });

    function save(event, f){
        if(doValidation()){
            doBlockUI();
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
                $('#page_div',opener.document).html(data);
                $.unblockUI();
                self.close();
            },'html');
        }
    }

    function doValidation(){
    
        var suku = $('#selectSuku').val();
        var alamat = $('#alamat1').val();
        var alamatSurat = $('#suratAlamat1').val();

        if (suku == ''){
            alert('Sila masukkan maklumat suku.');
            return false;
        }
        else if(alamat == ''){
            alert('Sila masukkan alamat berdaftar.');
            return false;
        }
        else if (alamatSurat == ''){
            alert('Sila Masukkan Alamat Surat-menyurat.');
            return false;
        }

        var kodNegeri = '${actionBean.kodNegeri}';
        var kodUrusan = '${actionBean.permohonan.kodUrusan.kod}';

        if(kodNegeri == "05"){
            if(kodUrusan == 'TMADT' || kodUrusan == 'TMWNA' || kodUrusan == 'BTADT' ||
                kodUrusan == 'CGADT' || kodUrusan == 'PMADT' || kodUrusan == 'TTADT'  ||
                kodUrusan == 'PJADT' || kodUrusan == 'PMWNA' || kodUrusan == 'PMWWA'){

                var luak = $('#luakData').val();
                if (luak == ''){
                    alert('Sila Masukkan Maklumat Luak.');
                    return false;
                }
            }
        }

        if((kodNegeri == '05' && kodUrusan == 'TMADT') || (kodNegeri == '05' && kodUrusan == 'TMWNA') || (kodNegeri == '04' && kodUrusan == 'PMMK2')){

            var tarikhMati =  $('#datepicker').val();
            var sijilMati =  $('#sijilMati').val();

            if(tarikhMati == ''){
                alert('Sila masukkan tarikh mati.');
                return false;
            }
            else if(sijilMati == ''){
                alert('Sila masukkan nombor sijil mati.');
                return false;
            }
        }
        return true;
    }

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

    function doBlockUI () {
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top:  ($(window).height() - 50) /2 + 'px',
                left: ($(window).width() - 50) /2 + 'px',
                width: '50px'
            }
        });
    }

</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none" alt=""/>
<div class="subtitle">
    <s:form beanclass="etanah.view.stripes.consent.PihakKepentinganActionBean" name="form1">
        <s:hidden name="hakmilik" id="idHakmilik"/>
        <s:hidden name="pihak.idPihak"/>
        <s:hidden name="pihak.nama"/>
        <s:hidden name="pihak.jenisPengenalan.kod"/>
        <s:hidden name="pihak.noPengenalan"/>
        <s:hidden name="permohonanPihak.idPermohonanPihak"/>

        <fieldset class="aras1">
            <legend>Maklumat Pihak Terlibat</legend>
            <p>
                <label>Nama :</label>
                ${actionBean.permohonanPihak.nama}&nbsp;
            </p>
            <p>
                <label>Jenis Pengenalan :</label>
                ${actionBean.permohonanPihak.jenisPengenalan.nama}&nbsp;
            </p>
            <p>
                <label>Nombor Pengenalan :</label>
                ${actionBean.permohonanPihak.noPengenalan}&nbsp;
            </p>

            <c:if test="${fn:endsWith(actionBean.permohonan.kodUrusan.idWorkflow,'Consent/ADAT') 
                          || fn:endsWith(actionBean.permohonan.kodUrusan.idWorkflow,'Consent/pmwna')
                          || actionBean.permohonan.kodUrusan.kod eq 'BTADT'
                          || actionBean.permohonan.kodUrusan.kod eq 'TMWNA'
                          || fn:endsWith(actionBean.permohonan.kodUrusan.idWorkflow,'Project1/mmk2')}">
                <c:if test="${actionBean.kodNegeri eq '05'}">
                    <p id="suku">
                        <label for="Suku"><font color="red">*</font>Jenis Suku :</label>
                        <s:select name="pihak.suku.kod" style="width:200px" id="selectSuku">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiKodSuku}" label="nama" value="kod"/>
                        </s:select>
                    </p>
                    <p id="subSuku">
                        <label for="subSuku">Pecahan Suku/Lengkongan :</label>
                        <s:text name="pihak.subSuku" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p id="perut">
                        <label for="perut">Perut :</label>
                        <s:text name="pihak.keturunan" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p id="luak">
                        <c:choose>
                            <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'TMADT' || actionBean.permohonan.kodUrusan.kod eq 'TMWNA' ||
                                            actionBean.permohonan.kodUrusan.kod eq 'BTADT' || actionBean.permohonan.kodUrusan.kod eq 'CGADT' ||
                                            actionBean.permohonan.kodUrusan.kod eq 'PMADT'  || actionBean.permohonan.kodUrusan.kod eq 'TTADT' ||
                                            actionBean.permohonan.kodUrusan.kod eq 'PJADT'  || actionBean.permohonan.kodUrusan.kod eq 'PMWNA' ||
                                            actionBean.permohonan.kodUrusan.kod eq 'PMWWA' }">
                                    <label><font color="red">*</font>Luak :</label>
                            </c:when>
                            <c:otherwise>
                                <label>Luak :</label>
                            </c:otherwise>
                        </c:choose>
                        <s:select name="pihak.tempatSuku" style="width:250px" id="luakData">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiKodLuak}" label="nama" value="nama"/>
                        </s:select>
                    </p>
                </c:if>

                <p>
                    <c:choose>
                        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'TMADT' || actionBean.permohonan.kodUrusan.kod eq 'TMWNA'
                                        || fn:endsWith(actionBean.permohonan.kodUrusan.idWorkflow,'Project1/mmk2')}">
                                <label><font color="red">*</font>Tarikh Mati :</label>
                        </c:when>
                        <c:otherwise>
                            <label>Tarikh Mati :</label>
                        </c:otherwise>
                    </c:choose>
                    <s:text name="tarikhMati" size="40" id="datepicker" class="datepicker"/>
                </p>
                <p>
                    <c:choose>
                        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'TMADT' || actionBean.permohonan.kodUrusan.kod eq 'TMWNA'
                                        || fn:endsWith(actionBean.permohonan.kodUrusan.idWorkflow,'Project1/mmk2')}">
                                <label><font color="red">*</font>Nombor Sijil Mati :</label>
                        </c:when>
                        <c:otherwise>
                            <label>Nombor Sijil Mati :</label>
                        </c:otherwise>
                    </c:choose>
                    <s:text name="pihak.noSijilMati" size="40" id="sijilMati" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
            </c:if>
            <p>
                <label for="alamat"><font color="red">*</font>Alamat Berdaftar :</label>
                <s:text name="pihak.alamat1" size="40" id="alamat1" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>

            <p>
                <label> &nbsp;</label>
                <s:text name="pihak.alamat2" size="40" id="alamat2" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>

            <p>
                <label> &nbsp;</label>
                <s:text name="pihak.alamat3" size="40" id="alamat3" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label> Bandar :</label>
                <s:text name="pihak.alamat4" size="40" id="alamat4" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label>Poskod :</label>
                <s:text name="pihak.poskod" size="40" maxlength="5" id="poskod" onkeyup="validateNumber(this,this.value);"/>
            </p>

            <p>
                <label for="Negeri">Negeri :</label>
                <s:select name="pihak.negeri.kod" id="negeri">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                </s:select>
            </p>
            <p>
                <label>&nbsp;</label>
                <input type="checkbox" id="add1" name="add1" value="" onclick="copyAdd();"/>
                <font color="red">Alamat surat sama dengan alamat berdaftar.</font>
            </p>
            <p>
                <label for="alamat"><font color="red">*</font>Alamat Surat-Menyurat :</label>
                <s:text name="pihakAlamatTamb.alamatKetiga1" id="suratAlamat1" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="pihakAlamatTamb.alamatKetiga2" id="suratAlamat2" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="pihakAlamatTamb.alamatKetiga3" id="suratAlamat3" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label> Bandar :</label>
                <s:text name="pihakAlamatTamb.alamatKetiga4" id="suratAlamat4" size="40" maxlength="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label>Poskod :</label>
                <s:text name="pihakAlamatTamb.alamatKetigaPoskod" id="suratPoskod" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
            </p>
            <p>
                <label for="Negeri">Negeri :</label>
                <s:select name="pihakAlamatTamb.alamatKetigaNegeri.kod" id="suratNegeri">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                </s:select>
            </p>
            <p align="center">
                <s:button name="simpanSiMati" class="btn" value="Simpan" onclick="save(this.name, this.form);"/>
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>
        </fieldset>
    </s:form>
</div>



