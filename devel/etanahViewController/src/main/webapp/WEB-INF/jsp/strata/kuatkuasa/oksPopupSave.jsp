 <%--
    Document   : oksPopupSave
    Created on : Apr 19, 2010, 11:24:52 AM
    Author     : aminah.abdmutalib

--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<html><head>
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
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/popup.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-impromptu.js"></script>
<script src="<%= request.getContextPath()%>/js/ui.datepicker-ms.js" type="text/javascript"></script>
<script type="text/javascript">
    function save(event, f){

        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.opener.refreshPage();
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
    function validateForm(){

    if($('#nama').val() == ''){
        alert("Sila Isi Nama");
        return false;
    }
   return true;
}
 </script>
<c:if test="${actionBean.beanFlag=='kemasukan_aduan'}">
    <c:set var="beanC" value="etanah.view.strata.PenguatkuasaanStrataActionBean"/>
</c:if>
</head><body>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>
    <s:form beanclass="${beanC}">
     <s:hidden name="idPermohonan" value="actionBean.idPermohonan" />
    <s:messages/>
    <div class="instr" align="center">
            <s:errors/>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Orang Disyaki
            </legend>
            <div class="content">

                 <p>
                        <label>Nombor Pengenalan :</label>
                        <s:text name="noPengenalan" onkeyup="this.value=this.value.toUpperCase();"/>&nbsp;</p>

                <p>
                        <label>Nama :</label>
                        <s:text name="nama" id="nama" onkeyup="this.value=this.value.toUpperCase();"/>&nbsp;
                       </p>

                    <p>
                        <label>Alamat :</label>
                        <s:text name="alamat1" onkeyup="this.value=this.value.toUpperCase();"/>&nbsp;
                      </p>
                    <p>
                        <label>&nbsp;</label>
                        <s:text name="alamat2" onkeyup="this.value=this.value.toUpperCase();"/>&nbsp;</p>
                    <p>
                        <label> &nbsp; </label>
                        <s:text name="alamat3" onkeyup="this.value=this.value.toUpperCase();"/>&nbsp;</p>
                    <p>
                        <label> &nbsp; </label>
                        <s:text name="alamat4" onkeyup="this.value=this.value.toUpperCase();"/>&nbsp; </p>

                    <p>
                        <label>Poskod :</label>
                        <s:text name="poskod" onkeyup="validateNumber(this,this.value);" maxlength="5"/>&nbsp;
                    </p>
                    <p>
                        <label>Negeri :</label>
                        &nbsp;

                        <s:select name="negeriO">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                        </s:select>&nbsp;
                    </p>
                    <p>
                        <label>No Telefon :</label>
                        <s:text name="noTelefon1" />&nbsp;
                    </p>
                        <p align="right">
                            <s:button class="btn" value="Simpan" name="oksSave"  onclick="if(validateForm())save(this.name,this.form);" />
                            <s:submit class="btn"  value="Tutup" name="closePopup" onclick="self.opener.refreshPage();self.close();" />
                         
                        </p>
                <br>
            </div>
        </fieldset>
     </div>
</s:form>
</body></html>