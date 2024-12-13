<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252" language="java"%>
<link type="text/css" href="${pageContext.request.contextPath}/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ui.datetimepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">

    function validateNumber(elmnt, content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return true;
        }
    }

    function checkIdKaunter(f) {
        var caw = document.getElementById('pguna.kodCawangan.kod');
        $.get("${pageContext.request.contextPath}/uam/user_update?searchIdKaunter&kaunter=" + f + "&caw=" + caw.value,
                function (data) {
                    //alert(data);
                    if (data == '1') {
                        alert('ID Kaunter telah wujud. Sila pilih kaunter lain');
                        $("#idKaunter").val("");
                    }
                });
        return;
    }
    function removeNonNumeric(strString)
    {
        var strValidCharacters = "1234567890";
        var strReturn = "";
        var strBuffer = "";
        var intIndex = 0;
        // Loop through the string
        for (intIndex = 0; intIndex < strString.length; intIndex++)
        {
            strBuffer = strString.substr(intIndex, 1);
            // Is this a number
            if (strValidCharacters.indexOf(strBuffer) > -1)
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

        if (f.elements['pguna.nama'].value == '') {
            alert('Sila masukkan Nama.');
            return false;

        } else if (f.elements['pguna.noPengenalan'].value == '') {
            alert('Sila masukkan No. Kad Pengenalan.');
            return false;

        } else if (f.elements['pguna.kodCawangan.kod'].value == '') {
    <c:if test="${actionBean.melaka}">
            alert('Sila pilih Jabatan yang berkaitan.');
    </c:if>

    <c:if test="${!actionBean.melaka}">
            alert('Sila pilih Cawangan yang berkaitan.');
    </c:if>
            return false;

        } else if (f.elements['pguna.kodJabatan.kod'].value == '') {
    <c:if test="${actionBean.melaka}">
            alert('Sila pilih Unit yang berkaitan.');
    </c:if>

    <c:if test="${!actionBean.melaka}">
            alert('Sila pilih Jabatan yang berkaitan.');
    </c:if>
            return false;
        } else if (f.elements['pguna.kodJawatan.kod'].value == '') {
            alert('Sila pilih Jawatan yang berkaitan.');
            return false;

        } else if (f.elements['pguna.email'].value == '') {
            alert('Sila masukkan Emel.');
            return false;

        } else if (f.elements['pguna.tahapSekuriti.kod'].value == '') {
            alert('Sila masukkan Kod Klasifikasi.');
            return false;

        } else if (f.elements['pguna.perananUtama.kod'].value == '') {
            alert('Sila pilih Peranan Utama yang berkaitan.');
            return false;

        } else if (!f.elements['pguna.status.kod'][0].checked && !f.elements['pguna.status.kod'][1].checked) {
            alert('Sila set status pengguna sama ada aktif atau tidak.');
            return false;

        } else if (!f.elements['pguna.penyeliaFlag'][0].checked && !f.elements['pguna.penyeliaFlag'][1].checked) {
            alert('Sila set status penyelia sama ada ya atau tidak.');
            return false;

        } else if (!f.elements['pguna.pelulusBayaranKurang'][0].checked && !f.elements['pguna.pelulusBayaranKurang'][1].checked) {
            alert('Sila set status pelulus bayaran kurang sama ada ya atau tidak.');
            return false;

        }
    }

</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.uam.uploadTandatangan">
    <div class="subtitle">
        <s:errors/>
        <s:messages/>
    </div>
    <c:if test="${!papar}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Id Pengguna</legend>
                <font size="1" color="Red">Sila masukkan Id Pengguna untuk membuat carian.</font>
                <p>
                    <label><font color="red">*</font>Id Pengguna :</label>
                        <c:if test="${simpan}">
                            <s:text name="pguna.idPengguna" />
                            <s:submit name="searchUser" id="search" value="Cari" class="btn"/>
                        </c:if>
                        <c:if test="${!simpan}">
                            <s:text name="pguna.idPengguna" />
                            <s:submit name="searchUser" id="search" value="Cari" class="btn"/>
                        </c:if>
                    <font size="1" color="red">(cth: rahim.hamzah)</font>
                </p>
            </fieldset >
            <c:if test="${kemaskini}">
                <fieldset class="aras1">
                    <legend>Muat Naik Tanda Tangan</legend>
                    <p>
                        <label> Fail :</label>&nbsp;
                        <s:file name="fileToBeUpload" id="fileToBeUpload"/>
                    </p>
                    Tanda Tangan 
                    <br>
                    <c:if test="${actionBean.blob ne null}">
                        
                        <p>
                        <center>
                        <img src ="${pageContext.request.contextPath}/uam/uploadTT?viewTT&idParam=${actionBean.pguna.idPengguna}" height="100" >
                       
                        </center>
                        </p>
                    </c:if>
                    <%--<c:if test="${blob ne null}">--%>
                    <c:if test="${actionBean.blob eq null}">
                        <p>
                        <label> Tanda Tangan : <font size="2" color="red">Belum Dimuat Naik</font></label> 
                        </p>
                    </c:if>
                    <br/>
                    <p>
                        <label>&nbsp;</label>&nbsp;
                        <s:submit name="processUpload" value="Simpan" class="btn"/>
                        <s:button name="close" value="Tutup" onclick="self.close();" class="btn"/>
                    </p>
                </fieldset>
                <br /></c:if>
            </div>
    </c:if>

</s:form>
