<%-- 
    Document   : projek_edit
    Created on : Jul 29, 2013, 4:50:17 PM
    Author     : mohd.azhar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $('form').submit(function() {
            doBlockUI();
        });
    });

    function doBlockUI() {
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top: ($(window).height() - 50) / 2 + 'px',
                left: ($(window).width() - 50) / 2 + 'px',
                width: '50px'
            }
        });
    }

    function isNumberKey(evt)
    {
        var charCode = (evt.which) ? evt.which : event.keyCode;
        if (charCode !== 46 && charCode > 31
            && (charCode < 48 || charCode > 57))
            return false;

        return true;
    }

    function test(f) {
        $(f).clearForm();
    }

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
        var namaPemaju = $('#namaPemaju').val();
        var noRujukan = $('#noRujukan').val();
        var jenisProjek = $('#jenisProjek').val();
        var jumlahSemuaUnit = $('#jumlahSemuaUnit').val();
        var maksimumUnit = $('#maksimumUnit').val();

        if(namaPemaju == ''){
            alert('Sila Masukkan Nama Pemaju.');
            return false;
        }
        else if(noRujukan == ''){
            alert('Sila Masukkan Nombor Rujukan Projek.');
            return false;
        }
        else if(jenisProjek == ''){
            alert('Sila Masukkan Jenis Projek.');
            return false;
        }else if(jumlahSemuaUnit == ''){
            alert('Sila Masukkan Jumlah Semua Unit.');
            return false;
        }
        else if(maksimumUnit == ''){
            alert('Sila Masukkan Jumlah Maksimum Unit.');
            return false;
        }

        return true;
    }
</script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="list" />
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none" alt=""/>
<s:form beanclass="etanah.view.stripes.consent.ProjekActionBean">

    <s:hidden name="penghantarNotis.idPenghantarNotis"/>

    <div class="subtitle">
        <fieldset class="aras1"><legend>Maklumat Projek Hartanah</legend>
            <p>
                <s:hidden name="projek.idProjek" id="idProjek"/>
            </p>

            <p>
                <label><em>*</em>Nama Pemaju :</label>
                <s:text name="projek.namaPemaju" size="50" maxlength="50" id="namaPemaju" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label><em>*</em>Nombor Rujukan Projek :</label>
                <s:text name="projek.noRujukanProjek" size="50" maxlength="50" id="noRujukan" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label><em>*</em>Jenis Projek :</label>
                <s:text name="projek.jenisProjek" size="50" maxlength="50" id="jenisProjek" onkeyup="this.value=this.value.toUpperCase();" />
            </p>

            <p>
                <label><em>*</em>Jumlah unit dalam projek :</label>
                <s:text name="projek.jumlahSemuaUnit" size="50" maxlength="5" id="jumlahSemuaUnit" onkeypress="return isNumberKey(event)" />
            </p>

            <p>
                <label><em>*</em>Jumlah unit yang layak diberi pertimbangan :</label>
                <s:textarea name="projek.maksimumUnit" cols="51" rows="3" id="maksimumUnit" onkeyup="this.value=this.value.toUpperCase();" />
            </p>

            <p align="center">
                <s:button name="simpanEdit" id="simpan" value="Simpan" class="btn" onclick="save(this.name, this.form);"/>
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>
        </fieldset>
    </div>
</s:form>