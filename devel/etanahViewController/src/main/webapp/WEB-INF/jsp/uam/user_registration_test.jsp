<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page import="java.util.List;" %>
<link type="text/css" rel="stylesheet"
      href="${pageContext.request.contextPath}/pub/styles/jquery.tooltip.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.tools.min.js?select=full&debug=true"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
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

    function checkUrusan(m){
        var xx = document.getElementById("kodTr"+m);
        if(xx.value != '0'){
            $('#nxt').removeAttr("disabled");
            $('#amt'+m).removeAttr("disabled");
            $('#amt'+m).focus();
        }
    }


</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.uam.UserRegistrationBean_Test">
    <div class="subtitle">
        <s:errors/>
        <s:messages/>
    </div>
    <div class="subtitle" style="">
        <fieldset class="aras1">
            <legend>Maklumat Log Masuk</legend>
            <font size="1" color="Red"><em>Sila masukkan id dan no Pengenalan</em></font>
            <p>
                <label><font color="red">*</font>Id Pengguna :</label>
                <c:if test="${simpan}">
                    <s:text name="pguna.idPengguna"/>
                </c:if>
                <c:if test="${!simpan}">
                    ${actionBean.pguna.idPengguna}
                </c:if>
            </p>
            <p>
                <label><font color="red">*</font>No Pengenalan :</label>
                <c:if test="${simpan}">
                    <s:text name="pguna.noPengenalan"/>
                    <font size="1" color="red">(cth: 840706045393)</font>
                </c:if>
                <c:if test="${!simpan}">
                    ${actionBean.pguna.noPengenalan}
                </c:if>
            </p>
            <p>
                <label>&nbsp;</label>
                <c:if test="${simpan}">
                    <s:submit name="searchUser" id="search" value="Cari" class="btn" />
                </c:if>
                <c:if test="${!simpan}">
                    <s:submit name="kembali1" value="Kembali" class="btn"/>
                </c:if>
            </p>
        </fieldset>
        <c:if test="${simpan}">
            <fieldset class="aras1">
                <legend>Maklumat Asas Pengguna</legend>
                <font size="1" color="Red"><em>Sila masukkan data Pengguna untuk membuat pendaftaran</em></font>
                <p>
                    <label><font color="red">*</font>Nama :</label>
                    <s:text name="pguna.nama" style="width:250px"/>
                </p>
                <p>
                    <label>Jawatan :</label>
                    <s:select name="pguna.jawatan" style="width:250px">
                        <s:option value="" >Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodJawatan}" value="nama" label="nama"/>
                    </s:select>
                </p>
                <p>
                    <label><font color="red">*</font>Alamat :</label>
                    <s:text name="pguna.alamat1" style="width:250px"/>
                </p>
                <p><label>&nbsp;</label>
                    <s:text name="pguna.alamat2" style="width:250px"/>
                </p>
                <p><label>&nbsp;</label>
                    <s:text name="pguna.alamat3" style="width:250px"/>
                </p>
                <p>
                    <label><font color="red">*</font>Poskod :</label>
                    <s:text name="pguna.poskod" style="width:250px"/>
                </p>
                <br>
                <%--<p>
                    <label><font color="red">*</font>Daerah :</label>
                    <s:select name="pguna..kod" style="width:250px">
                        <s:option value="" >Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodBPMByDaerah}" value="kod" label="nama"/>
                    </s:select>
                </p>--%>
                <br>
                <p>
                    <label><font color="red">*</font>Negeri :</label>
                    <s:select name="pguna.negeri.kod" style="width:250px">
                        <s:option value="" >Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodNegeri}" value="kod" label="nama"/>
                    </s:select>
                </p>
                <p>
                    <label><font color="red">*</font>Cawangan :</label>
                    <s:select name="pguna.kodCawangan.kod" style="width:250px">
                        <s:option value="" >Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodCawanganAktif}" value="kod" label="name"/>
                    </s:select>
                </p>
                <p>
                    <label>Peranan Utama :</label>
                    <s:select name="pguna.perananUtama.kod" style="width:250px">
                        <s:option value="" >Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodPeranan}" value="kod" label="nama"/>
                    </s:select>
                </p>
                <br>
                <p>
                    <label><font color="red">*</font>No Telefon Pejabat :</label>
                    <s:text name="pguna.noTelefon" style="width:250px"/>
                    <font size="1" color="red">(cth: 062843057)</font>
                </p>
                <p>
                    <label>No Telefon Bimbit :</label>
                    <s:text name="pguna.noTelefonBimbit" style="width:250px"/>
                    <font size="1" color="red">(cth: 0132164338)</font>
                </p>
                <p>
                    <label><font color="red">*</font>Email :</label>
                    <s:text name="pguna.email" style="width:250px"/>
                </p>
                <p>
                    <label><font color="red">*</font>Penyelia :</label>
                    <s:select name="pguna.penyelia.idPengguna" id="kodTr${row_rowNum-1}" onchange="checkUrusan('${row_rowNum-1}');">
                        <s:option value="0" label="Sila Pilih"/>
                        <c:forEach items="${list.senaraiPenggunaAktif}" var="c">
                            <s:option value="${c.idPengguna}" >${c.idPengguna} - ${c.nama}></s:option>
                        </c:forEach>
                    </s:select>
                   <%-- <s:select name="listPenyelia[${row_rowNum - 1}].pguna" id="kodTr${row_rowNum-1}" onchange="checkUrusan('${row_rowNum-1}');">
                        <s:option value="0" label="Sila Pilih"/>
                        <c:forEach items="${list.senaraiPenggunaAktif}" var="c">
                            <s:option value="${c.idPengguna}" >${c.idPengguna} - ${c.nama}></s:option>
                        </c:forEach>
                    </s:select>--%>
                    <%--<s:select name="pguna.penyelia.idPengguna" style="width:250px">
                        <s:option value="" >Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiPenggunaAktif}" value="idPengguna" label="nama"/>
                    </s:select>--%>
                </p>
                <br>
                <p>
                    <label>&nbsp;</label>

                    <c:if test="${simpan}">
                        <s:submit name="newPengguna" id="save" value="Simpan" class="btn"/>
                        <s:submit name="showForm" value="Isi Semula" class="btn" onclick="test(this.form);"/>
                        <s:submit name="kembali" value="Kembali" class="btn"/>
                    </c:if>

                </p>
            </fieldset>
        </c:if>
    </div>
</s:form>