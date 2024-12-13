<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252" language="java"%>
<link type="text/css" href="${pageContext.request.contextPath}/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ui.datetimepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">

    function validateNumber(elmnt,content) {
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

    function test(f) {
        $(f).clearForm();
    }

    function validateForm(f) {
        alert(f.elements['searchUser'].value);
        return false;

        if(f.elements['pguna.idPengguna'].value == '') {
            alert('Sila masukkan Id Pengguna.');
            return false;

        } else if(f.elements['pguna.password'].value == '') {
            alert('Sila masukkan Kata Laluan.');
            return false;

        } else if(f.elements['pKataLaluan'].value == '') {
            alert('Sila masukkan Pengesahan Kata Laluan.');
            return false;

        } else if(f.elements['pguna.nama'].value == '') {
            alert('Sila masukkan Nama.');
            return false;

        } else if(f.elements['pguna.noPengenalan'].value == '') {
            alert('Sila masukkan Kad Pengenalan.');
            return false;

        } else if(f.elements['pguna.jawatan'].value == '') {
            alert('Sila masukkan Jawatan.');
            return false;

        } else if(f.elements['pguna.email'].value == '') {
            alert('Sila masukkan Email.');
            return false;

        } else if(f.elements['pguna.alamat1'].value == '') {
            alert('Sila masukkan Alamat.');
            return false;

        } else if(f.elements['pguna.alamat2'].value == '') {
            alert('Sila masukkan Alamat.');
            return false;

        } else if(f.elements['pguna.alamat3'].value == '') {
            alert('Sila masukkan Alamat.');
            return false;

        } else if(f.elements['pguna.poskod'].value == '') {
            alert('Sila masukkan Poskod.');
            return false;

        } else if(f.elements['pguna.negeri.kod'].value == 0) {
            alert('Sila pilih Negeri yang berkaitan.');
            return false;

        } else if(f.elements['pguna.kodCawangan.kod'].value == '') {
            alert('Sila pilih Cawangan yang berkaitan.');
            return false;

        } else if(f.elements['pguna.perananUtama.kod'].value == '') {
            alert('Sila pilih Peranan Utama yang berkaitan.');
            return false;

        } else if(f.elements['pguna.noTelefon'].value == '') {
            alert('Sila masukkan No Telefon.');
            return false;

        } else if(f.elements['pguna.noTelefonBimbit'].value == '') {
            alert('Sila masukkan No Telefon bimbit.');
            return false;

        } else if(!f.elements['pguna.status.kod'][0].checked && !f.elements['pguna.status.kod'][1].checked) {
            alert('Sila set status pengguna sama ada aktif atau tidak.');
            return false;
        }

    }

</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.uam.UserRegistrationBean_Test">
    <div class="subtitle">
        <s:errors/>
        <s:messages/>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Id Pengguna</legend>
            <p>
                <label><font color="red">*</font>Id Pengguna :</label>
                ${actionBean.pguna.idPengguna}
            </p>
            <br>
        </fieldset>
        <fieldset class="aras1">
            <legend>Maklumat Asas Pengguna</legend>
            <p>
                <label><font color="red">*</font>Nama :</label>
                ${actionBean.pguna.nama}
            </p>
            <p>
                <label><font color="red">*</font>Kad Pengenalan :</label>
                ${actionBean.pguna.noPengenalan}
            </p>
            <p>
                <label><font color="red">*</font>Jawatan :</label>
                ${actionBean.pguna.jawatan}
            </p>
            <br>
            <p>
                <label><font color="red">*</font>Alamat :</label>
                ${actionBean.pguna.alamat1}
            </p>
            <p><label>&nbsp;</label>
                ${actionBean.pguna.alamat2}
            </p>
            <p><label>&nbsp;</label>
                ${actionBean.pguna.alamat3}
            </p>
            <p>
                <label><font color="red">*</font>Poskod :</label>
                ${actionBean.pguna.poskod}
            </p>
            <br>
            <p>
                <label><font color="red">*</font>Negeri :</label>
                ${actionBean.namaNegeri}
            </p>
            <p>
                <label><font color="red">*</font>Cawangan :</label>
                 ${actionBean.namaCaw}
            </p>
            <p>
                <label>Peranan Utama :</label>
                ${actionBean.namaPeranan}
            </p>
            <br>
            <p>
                <label><font color="red">*</font>No Telefon :</label>
                ${actionBean.pguna.noTelefon}
            </p>
            <p>
                <label><font color="red">*</font>No Telefon Bimbit :</label>
                ${actionBean.pguna.noTelefonBimbit}
            </p>
            <p>
                <label><font color="red">*</font>Email :</label>
                ${actionBean.pguna.email}
            </p>
            <p>
                <label><font color="red">*</font>Penyelia :</label>
                ${actionBean.pguna.penyelia.idPengguna}
            </p>
            <br>
        </fieldset>
        <br />
        <p>
            <label>&nbsp;</label>
            <s:button name="" value="Cetak" class="btn" onclick="window.print();"/>
            <s:submit name="kembali" value="Kembali" class="btn"/>
        </p>
        <br><br>
    </div>
</s:form>