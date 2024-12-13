<%-- 
    Document   : utilitiPemohonUtamaEdit
    Created on : Dec 3, 2015, 12:57:08 PM
    Author     : Hazwan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Carian Kod Syarat Nyata</title>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script src="<%= request.getContextPath()%>/pub/js/ui.datepicker-ms.js" type="text/javascript"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>
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
    
    function save(idPihak,idPemohon,idMohon){
        alert("Maklumat Pemohon Di Simpan !!!");
        var url = '${pageContext.request.contextPath}/utiliti/utilitiPemohonUtama?simpanPihak&idPemohon=' + idPemohon + '&idPihak=' + idPihak + '&idPermohonan=' + idMohon;
        $.get(url,
        function(data) {
            $('#page_div',opener.document).html(data);
            self.opener.refreshDiv();
            self.close();
        }, 'html');
    }
    
</script>

<s:form beanclass="etanah.view.stripes.pembangunan.utiliti.utilitiPemohonUtamaActionBean">
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <s:hidden name="pemohon" id="pemohon" value="${actionBean.pemohon}"/>
    <s:hidden name="pihak" id="pihak" value="${actionBean.pihak}"/>
    <s:hidden name="idPemohon" id="idPemohon" value="${actionBean.idPemohon}"/>
    <s:hidden name="idPihak" id="idPihak" value="${actionBean.idPihak}"/>
    <s:hidden name="idPermohonan" id="idPermohonan" value="${actionBean.idPermohonan}"/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Pemohon
            </legend>
            <br/><br/>
            <p>
                <label>Nama :</label>
                <s:text name="nama" id="nama" size="40" readonly="true"/>
            </p>
            <p>
                <label>Kod Pengenalan :</label>
                <s:select name="kodP" id="kodP" style="width:255px;">
                    <s:option>Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod" />
                </s:select>
            </p>  
            <p>
                <label>Nombor Pengenalan :</label>
                <s:text name="noPengenalan" id="noPengenalan" size="40"/>
            </p>          
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
                <s:text name="alamat3"  id="alamat3" size="40"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:text name="alamat4"  id="alamat4" size="40"/>
            </p>
            <p>
                <label>Poskod :</label>
                <s:text name="poskod" id="poskod" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
            </p>
            <p>
                <label>Negeri :</label>
                <s:select name="negeri" id="negeri" style="width:255px;">
                    <s:option>Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                </s:select>
            </p>
            <p>
                <label>No. Telefon:</label>
                <s:text name="noTel1" id="noTel1" maxlength="11" onkeyup="validateNumber(this,this.value);"/>
            </p>           

            <br/><br/>
            <p align="center">
                <s:submit name="simpanPihak" value="Simpan" class="btn"/> <%--onclick="save('${actionBean.idPihak}','${actionBean.idPemohon}','${actionBean.idPermohonan}')"--%>
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close();"/>
            </p>

        </fieldset >
    </div>

</s:form>

