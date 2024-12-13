<%--
    Document   : edit_pemohon
    Created on : Nov 10, 2009, 4:23:38 PM
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

<s:form beanclass="etanah.view.strata.RTHSMaklumatPemohonActionBean">

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Pemohon
            </legend>
            <p>
                <label>Nama :</label>
                ${actionBean.pihak.nama}&nbsp;<br/>
                <s:hidden name="pihak.idPihak" value="${actionBean.pihak.idPihak}" />
                <s:hidden name="pihak.idPihak" value="${actionBean.pihak.idPihak}" />
            </p>
            <p>
                <label>Jenis Pengenalan :</label>
                ${actionBean.pihak.jenisPengenalan.nama}&nbsp;
            </p>
            <p>
                <label>Nombor Pengenalan :</label>
                ${actionBean.pihak.noPengenalan}&nbsp;
            </p>


            <p>
                <label>Alamat Surat-Menyurat :</label>
                <s:text name="pihak.alamat1" size="40" value="${actionBean.pihak.alamat1}"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="pihak.alamat2" size="40" value="${actionBean.pihak.alamat2}"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="pihak.alamat3" size="40" value="${actionBean.pihak.alamat3}"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="pihak.alamat4" size="40" value="${actionBean.pihak.alamat4}"/>
            </p>
            <p>
                <label>Poskod :</label>
                <s:text name="pihak.suratPoskod" size="40" value="${actionBean.pihak.suratPoskod}" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
            </p>
            <p>
                <label>Negeri :</label>
                <s:select name="pihak.suratNegeri.kod" value="${actionBean.pihak.suratNegeri.kod}">
                    <s:option value="">Pilih ...</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                </s:select>
            </p>
            <p>

                <label>&nbsp;</label>
                <s:button name="updatePihak" id="simpan" value="Simpan" class="btn" onclick="save(this.name, this.form);"/>
                

                <label>&nbsp;</label>
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>

        </fieldset >
    </div>

</s:form>