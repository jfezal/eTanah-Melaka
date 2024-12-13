<%--
    Document   : edit_pemohon
    Created on : Nov 10, 2009, 4:23:38 PM
    Author     : muhammad.amin
--%>

<%--<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    function save(event, f){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.close();
        },'html');
    }

    function validatePoskod(elmnt,content) {
        //if it is character, then remove it..        
            if (isNaN(content)) {
                elmnt.value = removeNonNumeric(content);
                return;
            }      
    }

    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if($('#jenisPengenalan').val()== 'B'){            
            $('#kp').attr('maxlength', 12);
            if (isNaN(content)) {                
                elmnt.value = removeNonNumeric(content);
                return;
            }
        }else{            
            $('#kp').attr('maxlength', 20);
        }
    }
    function validation(){

        if($("#nama").val() == ""){
            alert('Sila masukan " nama " terlebih dahulu.');
            $("#nama").focus();
            return true;
        }

        if($("#alamat1").val() == ""){
            alert('Sila masukan " alamat1 " terlebih dahulu.');
            $("#alamat1").focus();
            return true;
        }

        if($("#poskod").val() == ""){
            alert('Sila masukan " poskod " terlebih dahulu.');
            $("#poskod").focus();
            return true;
        }

        if($("#negeri").val() == 0){
            alert('Sila masukan " negeri " terlebih dahulu.');
            $("#negeri").focus();
            return true;
        }
        if($("#jenisPengenalan").val() == ""){
            alert('Sila Pilih " Jenis Pengenalan " terlebih dahulu.');
            $("#jenisPengenalan").focus();
            return true;
        }
        if($("#kp").val() == ""){
            alert('Sila masukkan " Nombor Pengenalan " terlebih dahulu.');
            $("#kp").focus();
            return true;
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


    $(document).ready( function(){
        $('.alphanumeric').alphanumeric();

    });
    function save(event, f){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.get(url,q,
        function(data){
            if(data == '1')
            {
                alert('Sila masukan data pada label yang bertanda *. ');
            }else{$('#page_div',opener.document).html(data);

                self.close();}

        },'html');
    }

    function savePemohon(event, f){
        if(validation()){

        }else{
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
                $('#page_div',opener.document).html(data);
                self.close();
                //   refreshPage();
            },'html');
        }
    }

    function copyAddCaw(){
        if($('input[name=addCaw]').is(':checked')){
            $('#suratAlamatCaw1').val($('#alamatCaw1').val());
            $('#suratAlamatCaw2').val($('#alamatCaw2').val());
            $('#suratAlamatCaw3').val($('#alamatCaw3').val());
            $('#suratAlamatCaw4').val($('#alamatCaw4').val());
            $('#suratPoskodCaw').val($('#poskodCaw').val());
            $('#suratNegeriCaw').val($('#negeriCaw').val());
        }else{
            $('#suratAlamatCaw1').val('');
            $('#suratAlamatCaw2').val('');
            $('#suratAlamatCaw3').val('');
            $('#suratAlamatCaw4').val('');
            $('#suratPoskodCaw').val('');
            $('#suratNegeriCaw').val('');

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

</script>
<style type="text/css">
    input.error { background-color: yellow; }
</style>

<%--End--%>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>

<div class="subtitle">
    <s:form name="form1" beanclass="etanah.view.strata.MaklumatPemohonanActionBean">
        <s:messages/>
        <s:errors/>
        
        <s:hidden name="pihak.idPihak"/>

        <%--   <s:hidden name="pihak.nama"/>
           <s:hidden name="pihak.jenisPengenalan.kod"/>
           <s:hidden name="pihak.jenisPengenalan.nama"/>
           <s:hidden name="pihak.noPengenalan"/>
           <s:hidden name="pihak.bangsa.kod"/>
           <s:hidden name="pihak.suku.kod"/>
           <s:hidden name="pihak.alamat1"/>
           <s:hidden name="pihak.alamat2"/>
           <s:hidden name="pihak.alamat3"/>
           <s:hidden name="pihak.alamat4"/>
           <s:hidden name="pihak.poskod"/>
           <s:hidden name="pihak.negeri.kod"/>
           <s:hidden name="pihak.negeri.nama"/>
           <s:hidden name="permohonanPihak.jenis.kod"/>
           <s:hidden name="pihak.suratAlamat1"/>
           <s:hidden name="pihak.suratAlamat2"/>
           <s:hidden name="pihak.suratAlamat3"/>
           <s:hidden name="pihak.suratAlamat4"/>
           <s:hidden name="pihak.suratNegeri.kod"/>
           <s:hidden name="pihak.suratNegeri.nama"/>
           <s:hidden name="pihak.suratPoskod"/>  --%>
        <s:hidden name="urusan" value="${urusan}"/>


        <p>
            <label for="nama"><font color="red">*</font>Nama :</label>
            <s:text name="pihak.nama" id="nama" size="40"/>
        </p>

        <p>
            <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
            <s:select name="jenis" id="jenisPengenalan">
                <s:option value="">Sila Pilih</s:option>
                <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
            </s:select>
        </p>
        <p>
            <label for="No Pengenalan"><font color="red">*</font>No Pengenalan :</label>
            <s:text name="pihak.noPengenalan" id="kp" size="40" onkeyup="validateNumber(this,this.value);"/>
        </p>

        <p>
            <label for="alamat">Alamat:</label>
            <s:text name="pihak.alamat1" size="40" id="alamat1"/>
        </p>

        <p>
            <label> &nbsp;</label>
            <s:text name="pihak.alamat2" size="40" id="alamat2"/>
        </p>

        <p>
            <label> &nbsp;</label>
            <s:text name="pihak.alamat3" size="40" id="alamat3"/>
        </p>

        <p>
            <label>Bandar :</label>
            <s:text name="pihak.alamat4" size="40" id="alamat4"/>
        <p>
            <label>Poskod :</label>
            <s:text name="pihak.poskod" size="40" maxlength="5" id="poskod" onkeyup="validatePoskod(this,this.value);"/>
        </p>

        <p>
            <label for="Negeri">Negeri :</label>
            <s:select name="negeri" id="negeri">
                <s:option value="0">Sila Pilih</s:option>
                <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
            </s:select>
        </p>

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

        <p>
            <label for="alamat">Alamat Surat-Menyurat:</label>
            <s:text name="pihak.suratAlamat1" id="suratAlamat1" size="40"/>
        </p>

        <p>
            <label> &nbsp;</label>
            <s:text name="pihak.suratAlamat2" id="suratAlamat2" size="40"/>
        </p>

        <p>
            <label> &nbsp;</label>
            <s:text name="pihak.suratAlamat3" id="suratAlamat3" size="40"/>
        </p>

        <p>
            <label>Bandar :</label>
            <s:text name="pihak.suratAlamat4" id="suratAlamat4" size="40"/>
        </p>

        <p>
            <label>Poskod :</label>
            <s:text name="pihak.suratPoskod" id="suratPoskod" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
        </p>

        <p>
            <label for="Negeri">Negeri :</label>
            <s:select name="suratNegeri" id="suratNegeri">
                <s:option value="0">Sila Pilih</s:option>
                <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
            </s:select>
        </p> <br>

        <p>
            <label>&nbsp;</label>
            <%-- onclick="savePemohon(this.name, this.form);"--%>
            <s:button name="simpanPemohonPopup1" id="simpan" value="Simpan" class="btn" onclick="savePemohon(this.name, this.form);"  onmouseover="this.style.cursor='pointer';"/>
            <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()" onmouseover="this.style.cursor='pointer';"/>

            <br>
        </s:form>
</div>