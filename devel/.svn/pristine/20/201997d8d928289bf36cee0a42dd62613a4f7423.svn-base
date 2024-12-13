<%-- 
    Document   : maklumat_wakill
    Created on : Jan 19, 2011, 6:14:40 PM
    Author     : muhammad.amin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="list" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    function save(event, f){
        if(doValidation()){
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
                $('#page_div',opener.document).html(data);
                self.close();
            },'html');
        }
    }

    function doValidation(){
        var noKp = $('#noKp').val();
        var jenisKp = $('#jenisKp').val();
        var nama = $('#nama').val();

        if(nama == ''){
            alert('Sila Masukkan Nama.');
            return false;
        }else if(jenisKp == ''){
            alert('Sila Masukkan Jenis Pengenalan.');
            return false;
        }else if(noKp == ''){
            alert('Sila Masukkan Nombor Pengenalan.');
            return false;
        }
        return true;
    }

    function hapusWakil(event, f){
        if(confirm('Adakah anda pasti?')) {
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
                $('#page_div',opener.document).html(data);
                self.close();
            },'html');
        }
    }
    
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

</script>

<s:form beanclass="etanah.view.stripes.consent.MaklumatKehadiranActionBean">
    <s:hidden name="perbicaraanKehadiran.idKehadiran"/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Wakil</legend>
            <p>
                <label for="nama">Nama :</label>
                <c:if test="${actionBean.perbicaraanKehadiran.pihak ne null}">
                    ${actionBean.perbicaraanKehadiran.pihak.pihak.nama}
                </c:if>
                <c:if test="${actionBean.perbicaraanKehadiran.pihak eq null}">
                    ${actionBean.perbicaraanKehadiran.nama}
                </c:if>
            </p>
            <c:if test="${!edit}">
                <p>
                    <label for="nama">Nama Wakil :</label>
                    ${actionBean.perbicaraanKehadiran.wakilNama}
                </p>
                <p>
                    <label for="Jenis Pengenalan">Jenis Pengenalan :</label>
                    ${actionBean.perbicaraanKehadiran.wakilKodPengenalan.nama}
                </p>
                <p>
                    <label for="No Pengenalan">Nombor Pengenalan :</label>
                    ${actionBean.perbicaraanKehadiran.wakilNoPengenalan}
                </p>
            </c:if>
            <c:if test="${edit}">
                <p>
                    <label for="nama"><font color="red">*</font>Nama Wakil :</label>
                    <s:text name="perbicaraanKehadiran.wakilNama" id="nama" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                    <s:select name="perbicaraanKehadiran.wakilKodPengenalan.kod" id="jenisKp">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                    </s:select>
                </p>
                <p>
                    <label for="No Pengenalan"><font color="red">*</font>Nombor Pengenalan :</label>
                    <s:text name="perbicaraanKehadiran.wakilNoPengenalan" id="noKp" size="40" onkeyup="validateNumber(this,this.value);"/>
                </p>
            </c:if>
            <p align="center">
                <c:if test="${edit}">
                    <s:button name="simpanWakil" id="simpan" value="Simpan" class="btn" onclick="save(this.name, this.form);"/>
                    <s:button name="deleteWakil" id="hapus" value="Hapus" class="btn" onclick="hapusWakil(this.name, this.form);"/>
                </c:if>
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>
        </fieldset>
    </div>
</s:form>
